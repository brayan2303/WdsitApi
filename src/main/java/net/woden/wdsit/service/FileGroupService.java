/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.FileGroupEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author A.PULIDO
 */
@Service
public class FileGroupService {
    @Autowired
    private DataSourceConnection ds;
    public ResponseModel create(FileGroupEntity fg, int userId){
        int insertCount = 0;
        Connection cn = this.ds.openConnection();
        CallableStatement cs;
        try {
            cs = cn.prepareCall("{call sp_FileGroupCreate(?,?,?,?)}");
            cs.setString(1, fg.getName());
            cs.setString(2, fg.getDescription());
            cs.setInt(3, userId);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            insertCount=cs.getInt(4);
            cn.commit();
            cs.close();
            cn.close();
            
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", insertCount, 200);
    }
    
    public ResponseModel update(FileGroupEntity fg, int userId){
        int updateCount = 0;
        Connection cn = this.ds.openConnection();
                
        try {
        CallableStatement cs = cn.prepareCall("{call sp_FileGroupUpdate(?,?,?,?,?,?)}");
            cs.setInt(1, fg.getId());
            cs.setString(2, fg.getName());
            cs.setString(3, fg.getDescription());
            cs.setInt(4, userId);
            cs.setBoolean(5, fg.isActive());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            updateCount=cs.getInt(6);
            cs.close();
            cn.close();            
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", updateCount, 200);
    }
    
    public ResponseModel delete(int id){
        int deleteCount = 0;
        Connection cn = this.ds.openConnection();
        CallableStatement cs;
        try {
            cs = cn.prepareCall("{call sp_FileGroupDelete(?)}");
            cs.setInt(1, id);
            deleteCount = cs.executeUpdate();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", deleteCount, 200);
    }
    
    public ResponseModel list(){
        ArrayList<FileGroupEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_FileGroupList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                FileGroupEntity fg = new FileGroupEntity();
                fg.setId(rs.getInt("id"));
                fg.setName(rs.getString("name"));
                fg.setDescription(rs.getString("description"));
                fg.setActive(rs.getBoolean("active"));
                fg.setDateCreate(rs.getString("dateCreate"));       
                list.add(fg);
            }
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
    
    public ResponseModel findById(int Id){
         FileGroupEntity fg = null;
         Connection cn = this.ds.openConnection();
         try {
           CallableStatement cs = cn.prepareCall("{call sp_FileGroupFindById(?)}"); 
           cs.setInt(1, Id);
           ResultSet rs = cs.executeQuery();
           while (rs.next()) {
                fg = new FileGroupEntity();
                fg.setId(rs.getInt("id"));
                fg.setName(rs.getString("name"));
                fg.setDescription(rs.getString("description"));
                fg.setActive(rs.getBoolean("active"));
                fg.setDateCreate(rs.getString("dateCreate"));       
           }
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
        return new ResponseModel(getClass().getSimpleName(), fg == null ? "El grupo no existe" : "OK", fg, 200);
    }
}