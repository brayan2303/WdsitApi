package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenProfileSectionEntity;
import net.woden.wdsit.entity.GenSectionEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenSectionService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(GenSectionEntity g){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenSectionCreate(?,?,?,?,?,?)}");
            cs.setString(1,g.getName());
            cs.setString(2,g.getIcon());
            cs.setString(3,g.getColor());
            cs.setInt(4,g.getApplicationId());
            cs.setInt(5,g.getPosition());
            cs.registerOutParameter(6,Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(6);
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
    public ResponseModel update(GenSectionEntity g){
        int updates=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenSectionUpdate(?,?,?,?,?,?,?,?)}");
            cs.setInt(1,g.getId());
            cs.setString(2,g.getName());
            cs.setString(3,g.getIcon());
            cs.setString(4,g.getColor());
            cs.setInt(5,g.getApplicationId());
            cs.setInt(6,g.getPosition());
            cs.setBoolean(7,g.isActive());
            cs.registerOutParameter(8,Types.INTEGER);
            cs.execute();
            updates=cs.getInt(8);
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
    public ResponseModel delete(int sectionId){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenSectionDelete(?)}");
            cs.setInt(1,sectionId);
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
    public ResponseModel add(GenProfileSectionEntity g){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenSectionAdd(?,?,?)}");
            cs.setInt(1,g.getProfileId());
            cs.setInt(2,g.getSectionId());
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
    public ResponseModel remove(int profileId,int sectionId){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenSectionRemove(?,?)}");
            cs.setInt(1,profileId);
            cs.setInt(2,sectionId);
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
    public ResponseModel findAll(String applicationName,int profileId){
        ArrayList<GenSectionEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenSectionFindAll(?,?)}");
            cs.setString(1,applicationName);
            cs.setInt(2,profileId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenSectionEntity g=new GenSectionEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setIcon(rs.getString("icon"));
                g.setColor(rs.getString("color"));
                g.setApplicationId(rs.getInt("applicationId"));
                g.setPosition(rs.getInt("position"));
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
    public ResponseModel findById(int sectionId){
        GenSectionEntity g=null;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenSectionFindById(?)}");
            cs.setInt(1,sectionId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                g=new GenSectionEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setIcon(rs.getString("icon"));
                g.setColor(rs.getString("color"));
                g.setApplicationId(rs.getInt("applicationId"));
                g.setApplication(rs.getString("application"));
                g.setPosition(rs.getInt("position"));
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
    public ResponseModel findByProfileId(int profileId){
        ArrayList<GenSectionEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenSectionFindByProfileId(?)}");
            cs.setInt(1,profileId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenSectionEntity g=new GenSectionEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setIcon(rs.getString("icon"));
                g.setColor(rs.getString("color"));
                g.setApplicationId(rs.getInt("applicationId"));
                g.setApplication(rs.getString("application"));
                g.setPosition(rs.getInt("position"));
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
    public ResponseModel list(String applicationName){
        ArrayList<GenSectionEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenSectionList(?)}");
            cs.setString(1,applicationName);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenSectionEntity g=new GenSectionEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setIcon(rs.getString("icon"));
                g.setColor(rs.getString("color"));
                g.setApplicationId(rs.getInt("applicationId"));
                g.setPosition(rs.getInt("position"));
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
    public ResponseModel listAll(){
        ArrayList<GenSectionEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenSectionListAll()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenSectionEntity g=new GenSectionEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setIcon(rs.getString("icon"));
                g.setColor(rs.getString("color"));
                g.setApplicationId(rs.getInt("applicationId"));
                g.setApplication(rs.getString("application"));
                g.setPosition(rs.getInt("position"));
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
