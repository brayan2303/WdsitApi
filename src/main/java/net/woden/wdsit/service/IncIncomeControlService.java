package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.IncIncomeControlEntity;
import net.woden.wdsit.model.IncIncomeControlModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncIncomeControlService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(IncIncomeControlEntity i){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_IncIncomeControlCreate(?,?,?,?)}");
            cs.setInt(1,i.getPersonId());
            cs.setInt(2,i.getCenterCostId());
            cs.setInt(3,i.getCustomerId());
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(4);
            cn.commit();
            cs.close();
            cn.close();
        }catch(SQLException ex){
            return new ResponseModel(getClass().getSimpleName(),ex.getMessage(),null,200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(),ex.getMessage(),null,200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(),"OK",inserts,200);
    }
    public ResponseModel findLast(int personId){
        IncIncomeControlModel i=null;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_IncIncomeControlFindLast(?)}");
            cs.setInt(1,personId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                i=new IncIncomeControlModel();
                i.setId(rs.getInt("id"));
                i.setPersonId(rs.getInt("personId"));
                i.setCustomerId(rs.getInt("customerId"));
                i.setInitialDate(rs.getDate("initialDate"));
                i.setFinalDate(rs.getDate("finalDate"));
                i.setCustomer(rs.getString("description"));
            }
            cn.commit();
            cs.close();
            cn.close();
        }catch(SQLException ex){
            return new ResponseModel(getClass().getSimpleName(),ex.getMessage(),null,200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(),ex.getMessage(),null,200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(),"OK",i,200);
    }
    public ResponseModel update(int personId){
        int updates=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_IncIncomeControlUpdate(?,?)}");
            cs.setInt(1,personId);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            updates=cs.getInt(2);
            cn.commit();
            cs.close();
            cn.close();
        }catch(SQLException ex){
            return new ResponseModel(getClass().getSimpleName(),ex.getMessage(),null,200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(),ex.getMessage(),null,200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(),"OK",updates,200);
    }
}
