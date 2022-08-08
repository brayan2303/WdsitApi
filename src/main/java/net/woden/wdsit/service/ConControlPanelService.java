package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ConControlPanelEntity;
import net.woden.wdsit.entity.ConControlPanelPersonEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConControlPanelService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(ConControlPanelEntity c){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_ConControlPanelCreate(?,?,?,?,?)}");
            cs.setString(1,c.getName());
            cs.setString(2,c.getLink());
            cs.setInt(3,c.getCustomerId());
            cs.setInt(4,c.getSectionId());
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
    public ResponseModel update(ConControlPanelEntity c){
        int updates=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_ConControlPanelUpdate(?,?,?,?,?,?,?)}");
            cs.setInt(1,c.getId());
            cs.setString(2,c.getName());
            cs.setString(3,c.getLink());
            cs.setInt(4,c.getCustomerId());
            cs.setInt(5,c.getSectionId());
            cs.setBoolean(6,c.isActive());
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
    public ResponseModel delete(int ControlPanelId){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_ConControlPanelDelete(?)}");
            cs.setInt(1,ControlPanelId);
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
    public ResponseModel add(ConControlPanelPersonEntity c){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_ConControlPanelAdd(?,?,?)}");
            cs.setInt(1,c.getControlPanelId());
            cs.setInt(2,c.getPersonId());
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
    public ResponseModel remove(int personId,int controlPanelId){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_ConControlPanelRemove(?,?)}");
            cs.setInt(1,personId);
            cs.setInt(2,controlPanelId);
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
        ArrayList<ConControlPanelEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_ConControlPanelFindAll(?)}");
            cs.setInt(1,personId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                ConControlPanelEntity c=new ConControlPanelEntity();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setLink(rs.getString("link"));
                c.setCreationDate(rs.getDate("creationDate"));
                c.setCustomerId(rs.getInt("customerId"));
                c.setSectionId(rs.getInt("sectionId"));
                c.setActive(rs.getBoolean("active"));
                list.add(c);
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
    public ResponseModel findById(int ControlPanelId) {
        ConControlPanelEntity c = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ConControlPanelFindById(?)}");
            cs.setInt(1, ControlPanelId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                c = new ConControlPanelEntity();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setLink(rs.getString("link"));
                c.setCreationDate(rs.getDate("creationDate"));
                c.setCustomerId(rs.getInt("customerId"));
                c.setSectionId(rs.getInt("sectionId"));
                c.setActive(rs.getBoolean("active"));
            }
            cn.commit();
            cs.close();
            rs.close();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", c, 200);
    }
    public ResponseModel findByPersonId(int personId) {
        ArrayList<ConControlPanelEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ConControlPanelFindByPersonId(?)}");
            cs.setInt(1, personId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ConControlPanelEntity c = new ConControlPanelEntity();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setLink(rs.getString("link"));
                c.setCreationDate(rs.getDate("creationDate"));
                c.setCustomerId(rs.getInt("customerId"));
                c.setSectionId(rs.getInt("sectionId"));
                c.setActive(rs.getBoolean("active"));
                list.add(c);
            }
            cn.commit();
            cs.close();
            rs.close();
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
    public ResponseModel list(){
        ArrayList<ConControlPanelEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_ConControlPanelList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                ConControlPanelEntity c=new ConControlPanelEntity();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setLink(rs.getString("link"));
                c.setCreationDate(rs.getDate("creationDate"));
                c.setCustomerId(rs.getInt("customerId"));
                c.setCustomer(rs.getString("customer"));
                c.setSectionId(rs.getInt("sectionId"));
                c.setSection(rs.getString("section"));
                c.setActive(rs.getBoolean("active"));
                list.add(c);
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
