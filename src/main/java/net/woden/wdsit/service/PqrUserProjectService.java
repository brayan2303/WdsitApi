/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.PqrMasterEntity;
import net.woden.wdsit.entity.PqrUserProjectEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class PqrUserProjectService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(PqrUserProjectEntity p) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrUserProjectCreate(?,?,?)}");
            cs.setInt(1, p.getUserId());
            cs.setInt(2, p.getProjectId());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(3);
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
    
    public ResponseModel list(){
        ArrayList<PqrUserProjectEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrUserProjectList()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
            PqrUserProjectEntity p = new PqrUserProjectEntity();
            p.setId(rs.getInt("id"));
            p.setUserId(rs.getInt("userId"));
            p.setProjectId(rs.getInt("projectdId"));
            list.add(p);
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
    
    public ResponseModel delete(int userId, int projectdId){
    int deletes = 0;
    Connection cn = this.ds.openConnection();
    
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrUserProjectDelete(?,?)}");
            cs.setInt(1, userId);
            cs.setInt(2, projectdId);
            deletes = cs.executeUpdate();
            cn.commit();
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
      return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }
    public ResponseModel findAll(int userId){
    ArrayList<PqrMasterEntity> lists = new ArrayList();
    Connection cn = this.ds.openConnection();
    
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrUserProjectFindAll(?)}");
            cs.setInt(1, userId);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
            PqrMasterEntity p = new PqrMasterEntity();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setActive(rs.getBoolean("active"));
            lists.add(p);
            }
            cn.commit();
            cs.close();
            rs.close();
            cn.close();
            
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }
}
