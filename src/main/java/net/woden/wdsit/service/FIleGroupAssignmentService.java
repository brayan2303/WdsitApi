/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.model.FileGroupAssignmentModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author A.PULIDO
 */
@Service
public class FIleGroupAssignmentService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel assignment(int userId){
         
         ArrayList<FileGroupAssignmentModel> list = new ArrayList();         
         Connection cn = this.ds.openConnection();
         
         try {
           CallableStatement cs = cn.prepareCall("{call sp_FileGroupAssigmentList(?)}"); 
           cs.setInt(1, userId);
           ResultSet rs = cs.executeQuery();
           while (rs.next()) {
                FileGroupAssignmentModel fga = new FileGroupAssignmentModel();
                fga.setDescription(rs.getString("description"));
                list.add(fga);
           }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    } 
}