package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.PriFieldEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriFieldService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(PriFieldEntity p){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PriFieldCreate(?,?,?,?,?,?,?)}");
            cs.setString(1, p.getName());
            cs.setString(2, p.getCode());
            cs.setInt(3, p.getPosition());
            cs.setInt(4, p.getLabelId());
            cs.setBoolean(5, p.getAutomatic());
            cs.setBoolean(6, p.getManual());
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(7);
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
    public ResponseModel update(PriFieldEntity p){
        int updates=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PriFieldUpdate(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1,p.getId());
            cs.setString(2, p.getName());
            cs.setString(3, p.getCode());
            cs.setInt(4, p.getPosition());
            cs.setInt(5, p.getLabelId());
            cs.setBoolean(6, p.getAutomatic());
            cs.setBoolean(7, p.getManual());
            cs.setBoolean(8,p.isActive());
            cs.registerOutParameter(9, Types.INTEGER);
            cs.execute();
            updates=cs.getInt(9);
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
            CallableStatement cs=cn.prepareCall("{call sp_PriFieldDelete(?)}");
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
    public ResponseModel findByLabelId(int labelId){
        ArrayList<PriFieldEntity>lista=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PriFieldFindByLabelId(?)}");
            cs.setInt(1, labelId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                PriFieldEntity p=new PriFieldEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setCode(rs.getString("code"));
                p.setPosition(rs.getInt("position"));
                p.setLabelId(rs.getInt("labelId"));
                p.setAutomatic(rs.getBoolean("automatic"));
                p.setManual(rs.getBoolean("manual"));
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
        public ResponseModel findByLabelIdOrder(int labelId){
        ArrayList<PriFieldEntity>lista=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PriFieldFindByLabelIdOrder(?)}");
            cs.setInt(1, labelId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                PriFieldEntity p=new PriFieldEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setCode(rs.getString("code"));
                p.setPosition(rs.getInt("position"));
                p.setLabelId(rs.getInt("labelId"));
                p.setAutomatic(rs.getBoolean("automatic"));
                p.setManual(rs.getBoolean("manual"));
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
    public ResponseModel list(int labelId){
        ArrayList<PriFieldEntity>lista=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PriFieldList(?)}");
            cs.setInt(1, labelId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                PriFieldEntity p=new PriFieldEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setCode(rs.getString("code"));
                p.setPosition(rs.getInt("position"));
                p.setLabelId(rs.getInt("labelId"));
                p.setAutomatic(rs.getBoolean("automatic"));
                p.setManual(rs.getBoolean("manual"));
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
    
     public ResponseModel listAutomatic(int labelId){
        ArrayList<PriFieldEntity>lista=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PriFieldListAutomatic(?)}");
            cs.setInt(1, labelId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                PriFieldEntity p=new PriFieldEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setCode(rs.getString("code"));
                p.setPosition(rs.getInt("position"));
                p.setLabelId(rs.getInt("labelId"));
                p.setAutomatic(rs.getBoolean("automatic"));
                p.setManual(rs.getBoolean("manual"));
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
     
       public ResponseModel listAutomaticSmall(int labelId){
        ArrayList<PriFieldEntity>lista=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PriFieldListAutomaticSmall(?)}");
            cs.setInt(1, labelId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                PriFieldEntity p=new PriFieldEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setCode(rs.getString("code"));
                p.setPosition(rs.getInt("position"));
                p.setLabelId(rs.getInt("labelId"));
                p.setAutomatic(rs.getBoolean("automatic"));
                p.setManual(rs.getBoolean("manual"));
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
