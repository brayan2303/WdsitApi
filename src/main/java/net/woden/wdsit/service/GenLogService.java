package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenLogEntity;
import net.woden.wdsit.model.DataModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenLogService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(GenLogEntity g) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenLogCreate(?,?)}");
            cs.setInt(1, g.getPersonId());
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(2);
            cn.commit();
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
    public ResponseModel loginDay() {
        DataModel data=new DataModel();
        ArrayList<String>dataX=new ArrayList();
        ArrayList<Integer>dataY=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenLogLoginDay()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                dataX.add(rs.getString("dataX"));
                dataY.add(rs.getInt("dataY"));
            }
            data.setDataX(dataX);
            data.setDataY(dataY);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", data, 200);
    }
    public ResponseModel loginPerson() {
        DataModel data=new DataModel();
        ArrayList<String>dataX=new ArrayList();
        ArrayList<Integer>dataY=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenLogLoginPerson()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                dataX.add(rs.getString("dataX"));
                dataY.add(rs.getInt("dataY"));
            }
            data.setDataX(dataX);
            data.setDataY(dataY);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", data, 200);
    }
}
