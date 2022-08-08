package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenModuleEntity;
import net.woden.wdsit.entity.GenProfileModuleEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenModuleService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(GenModuleEntity g){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenModuleCreate(?,?,?,?)}");
            cs.setString(1,g.getName());
            cs.setString(2,g.getLink());
            cs.setInt(3,g.getSectionId());
            cs.registerOutParameter(4,Types.INTEGER);
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
    public ResponseModel update(GenModuleEntity g){
        int updates=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenModuleUpdate(?,?,?,?,?,?)}");
            cs.setInt(1,g.getId());
            cs.setString(2,g.getName());
            cs.setString(3,g.getLink());
            cs.setInt(4,g.getSectionId());
            cs.setBoolean(5,g.isActive());
            cs.registerOutParameter(6,Types.INTEGER);
            cs.execute();
            updates=cs.getInt(6);
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
    public ResponseModel delete(int moduleId){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenModuleDelete(?)}");
            cs.setInt(1,moduleId);
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
    public ResponseModel add(GenProfileModuleEntity g){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenModuleAdd(?,?,?)}");
            cs.setInt(1,g.getProfileId());
            cs.setInt(2,g.getModuleId());
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
    public ResponseModel remove(int profileId,int moduleId){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenModuleRemove(?,?)}");
            cs.setInt(1,profileId);
            cs.setInt(2,moduleId);
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
    public ResponseModel findAll(int sectionId,int profileId){
        ArrayList<GenModuleEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenModuleFindAll(?,?)}");
            cs.setInt(1,sectionId);
            cs.setInt(2,profileId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenModuleEntity g=new GenModuleEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setLink(rs.getString("link"));
                g.setSectionId(rs.getInt("sectionId"));
                g.setActive(rs.getBoolean("active"));
                list.add(g);
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
        return new ResponseModel(getClass().getSimpleName(),"OK",list,200);
    }
    public ResponseModel findById(int moduleId){
        GenModuleEntity g=null;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenModuleFindById(?)}");
            cs.setInt(1,moduleId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                g=new GenModuleEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setLink(rs.getString("link"));
                g.setSectionId(rs.getInt("sectionId"));
                g.setSection(rs.getString("section"));
                g.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(),"OK",g,200);
    }
    public ResponseModel findByProfileId(int profileId,String applicationName){
        ArrayList<GenModuleEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenModuleFindByProfileId(?,?)}");
            cs.setInt(1,profileId);
            cs.setString(2,applicationName);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenModuleEntity g=new GenModuleEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setLink(rs.getString("link"));
                g.setSectionId(rs.getInt("sectionId"));
                g.setActive(rs.getBoolean("active"));
                list.add(g);
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
        return new ResponseModel(getClass().getSimpleName(),"OK",list,200);
    }
    public ResponseModel list(){
        ArrayList<GenModuleEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenModuleList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenModuleEntity g=new GenModuleEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setLink(rs.getString("link"));
                g.setSectionId(rs.getInt("sectionId"));
                g.setSection(rs.getString("section"));
                g.setActive(rs.getBoolean("active"));
                list.add(g);
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
        return new ResponseModel(getClass().getSimpleName(),"OK",list,200);
    }
}
