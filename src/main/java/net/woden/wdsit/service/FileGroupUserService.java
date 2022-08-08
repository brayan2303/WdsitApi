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
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.FileGroupEntity;
import net.woden.wdsit.entity.FileGroupUserEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author A.PULIDO
 */
@Service
public class FileGroupUserService {
    @Autowired
    private DataSourceConnection ds;
    public ResponseModel create(FileGroupUserEntity fgu){
        int insertCount = 0;
        Connection cn = this.ds.openConnection();
        CallableStatement cs;
        try {
            cs = cn.prepareCall("{call sp_FileGroupUserCreate(?,?,?)}");
            cs.setInt(1, fgu.getUserId());
            cs.setInt(2, fgu.getGroupId());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            insertCount=cs.getInt(3);
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
            return new ResponseModel(getClass().getSimpleName(), "OK", insertCount, 200);
    }
    
    public ResponseModel delete(int userId, int groupId){
        int deleteCount = 0;
        Connection cn = this.ds.openConnection();
        CallableStatement cs;
        try {
            
            cs = cn.prepareCall("{call sp_FileGroupUserDelete(?,?)}");
            cs.setInt(1, userId);
            cs.setInt(2, groupId);
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
    
    public ResponseModel list(int groupId){
        ArrayList<FileGroupEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_FileGroupUserList(?)}");
            cs.setInt(1, groupId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                FileGroupEntity fg = new FileGroupEntity();
                fg.setId(rs.getInt("id"));
                fg.setName(rs.getString("name"));
                fg.setDescription(rs.getString("description"));
                fg.setActive(rs.getBoolean("active")); 
                list.add(fg);
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
}
