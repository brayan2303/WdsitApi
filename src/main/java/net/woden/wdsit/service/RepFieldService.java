package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.RepFieldEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepFieldService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(RepFieldEntity r){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_RepFieldCreate(?,?,?)}");
            cs.setString(1,r.getName());
            cs.setInt(2,r.getReportId());
            cs.registerOutParameter(3,Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(3);
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
    public ResponseModel update(RepFieldEntity r){
        int updates=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_RepFieldUpdate(?,?,?,?,?)}");
            cs.setInt(1,r.getId());
            cs.setString(2,r.getName());
            cs.setInt(3,r.getReportId());
            cs.setBoolean(4,r.isActive());
            cs.registerOutParameter(5,Types.INTEGER);
            cs.execute();
            updates=cs.getInt(5);
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
    public ResponseModel delete(int fieldId){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_RepFieldDelete(?)}");
            cs.setInt(1,fieldId);
            deletes=cs.executeUpdate();
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
        return new ResponseModel(getClass().getSimpleName(),"OK",deletes,200);
    }
    public ResponseModel findByReportId(int reportId){
        ArrayList<RepFieldEntity>lista=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_RepFieldFindByReportId(?)}");
            cs.setInt(1, reportId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                RepFieldEntity p=new RepFieldEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setReportId(rs.getInt("reportId"));
                p.setActive(rs.getBoolean("active"));
                lista.add(p);
            }
            cn.commit();
            cs.close();
            rs.close();
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
        return new ResponseModel(getClass().getSimpleName(),"OK",lista,200);
    }
    public ResponseModel findByReportName(String reportName){
        ArrayList<RepFieldEntity>lista=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_RepFieldFindByReportName(?)}");
            cs.setString(1, reportName);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                RepFieldEntity p=new RepFieldEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setReportId(rs.getInt("reportId"));
                p.setActive(rs.getBoolean("active"));
                lista.add(p);
            }
            cn.commit();
            cs.close();
            rs.close();
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
        return new ResponseModel(getClass().getSimpleName(),"OK",lista,200);
    }
    public ResponseModel list(int reportId){
        ArrayList<RepFieldEntity>lista=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_RepFieldList(?)}");
            cs.setInt(1, reportId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                RepFieldEntity p=new RepFieldEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setReportId(rs.getInt("reportId"));
                p.setActive(rs.getBoolean("active"));
                lista.add(p);
            }
            cn.commit();
            cs.close();
            rs.close();
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
        return new ResponseModel(getClass().getSimpleName(),"OK",lista,200);
    }
}
