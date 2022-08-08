package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenApplicationEntity;
import net.woden.wdsit.entity.GenApplicationPersonEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenApplicationService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(GenApplicationEntity g){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenApplicationCreate(?,?,?,?,?)}");
            cs.setString(1,g.getName());
            cs.setString(2,g.getIcon());
            cs.setString(3,g.getColor());
            cs.setString(4,g.getLink());
            cs.registerOutParameter(5,Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(5);
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
    
    public ResponseModel update(GenApplicationEntity g){
        int updates=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenApplicationUpdate(?,?,?,?,?,?,?)}");
            cs.setInt(1,g.getId());
            cs.setString(2,g.getName());
            cs.setString(3,g.getIcon());
            cs.setString(4,g.getColor());
            cs.setString(5,g.getLink());
            cs.setBoolean(6,g.isActive());
            cs.registerOutParameter(7,Types.INTEGER);
            cs.execute();
            updates=cs.getInt(7);
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
    public ResponseModel delete(int applicationId){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenApplicationDelete(?)}");
            cs.setInt(1,applicationId);
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
    public ResponseModel add(GenApplicationPersonEntity g){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenApplicationAdd(?,?,?)}");
            cs.setInt(1,g.getPersonId());
            cs.setInt(2,g.getApplicationId());
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
    public ResponseModel remove(int personId,int applicationId){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenApplicationRemove(?,?)}");
            cs.setInt(1,personId);
            cs.setInt(2,applicationId);
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
    public ResponseModel findAll(int personId){
        ArrayList<GenApplicationEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenApplicationFindAll(?)}");
            cs.setInt(1,personId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenApplicationEntity g=new GenApplicationEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setIcon(rs.getString("icon"));
                g.setColor(rs.getString("color"));
                g.setLink(rs.getString("link"));
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
    public ResponseModel findByPersonId(int personId){
        ArrayList<GenApplicationEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenApplicationFindByPersonId(?)}");
            cs.setInt(1,personId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenApplicationEntity g=new GenApplicationEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setIcon(rs.getString("icon"));
                g.setColor(rs.getString("color"));
                g.setLink(rs.getString("link"));
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
    public ResponseModel findById(int applicationId){
        GenApplicationEntity g=null;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenApplicationFindById(?)}");
            cs.setInt(1,applicationId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                g=new GenApplicationEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setIcon(rs.getString("icon"));
                g.setColor(rs.getString("color"));
                g.setLink(rs.getString("link"));
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
    public ResponseModel list(){
        ArrayList<GenApplicationEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenApplicationList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenApplicationEntity g=new GenApplicationEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setIcon(rs.getString("icon"));
                g.setColor(rs.getString("color"));
                g.setLink(rs.getString("link"));
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
        ArrayList<GenApplicationEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenApplicationListAll()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenApplicationEntity g=new GenApplicationEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setIcon(rs.getString("icon"));
                g.setColor(rs.getString("color"));
                g.setLink(rs.getString("link"));
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
