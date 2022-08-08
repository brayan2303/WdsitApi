package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinDataService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(String data) {
        int inserts = 1;
        Connection cn = this.ds.openConnection();

        try {
            
            String []transform = data.split(",");
            // Laitud
            double datoLongitud = Double.parseDouble((transform[2])) / 100 ;
            //Entero para suma
            int norte = (int) datoLongitud;
            //Latitud deciman
            double latitud = Math.abs((((norte-datoLongitud)*100) / 60));
            latitud = latitud + norte; //Unificacion da LALITUD
            
            //Longitud
            double datoOeste = Double.parseDouble((transform[4])) / 100 ;
            //Entero para suma
            int oeste = (int) datoOeste;
            //Longitud deciman
            double longitud = Math.abs((((oeste-datoOeste)*100) / 60));
            longitud=(longitud+oeste)*(-1);
            
            //SERIAL
            String []trama = transform[15].split("@");
            String serial1 = trama[0];
            String serial2 = trama[1];
            
            
            CallableStatement cs = cn.prepareCall("{call sp_FinDataCreate(?,?,?,?,?,?)}");
            cs.setString(1, latitud+"");
            cs.setString(2, longitud+"");
            cs.setString(3, serial1);
            cs.setString(4, serial2);
            cs.setString(5, data);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(6);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }
}
