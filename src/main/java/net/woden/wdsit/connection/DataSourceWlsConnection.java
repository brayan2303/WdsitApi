package net.woden.wdsit.connection;

import java.sql.SQLException;
import javax.sql.DataSource;
import net.woden.wdsit.entity.WlsServerEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.WlsConnectionModel;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

@Service
public class DataSourceWlsConnection {

    WlsConnectionModel cm = new WlsConnectionModel();
    @Autowired
    private EncryptUtility eu;

    public DataSource sourceWls(WlsServerEntity w,String dataBase) {
        if (w != null) {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dataSource.setUrl("jdbc:sqlserver://" + w.getIp() +":"+w.getPort()+";databaseName=" + dataBase);
            dataSource.setUsername(w.getUserName());
            dataSource.setPassword(w.getPassword());
            return dataSource;
        } else {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dataSource.setUrl("jdbc:sqlserver://" + this.eu.decode(this.cm.getIp()) + ";" + this.eu.decode(this.cm.getDataBase()));
            dataSource.setUsername(this.eu.decode(this.cm.getUserName()));
            dataSource.setPassword(this.eu.decode(this.cm.getPassword()));
            return dataSource;
        }
    }

    public ResponseModel openConnection(WlsServerEntity w,String dataBase) {
        try {
            return new ResponseModel(getClass().getSimpleName(),"OK", sourceWls(w,dataBase).getConnection(), 200);
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
    }
}
