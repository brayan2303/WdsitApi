package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.PqrMasterTypeEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PqrMasterTypeService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel list() {
        ArrayList<PqrMasterTypeEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMasterTypeList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                PqrMasterTypeEntity p=new PqrMasterTypeEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setActive(rs.getBoolean("active"));
                list.add(p);
            }
            cn.commit();
            rs.close();
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
}
