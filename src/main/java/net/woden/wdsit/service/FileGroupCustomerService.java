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
import net.woden.wdsit.entity.FileGroupCustomerEntity;
import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author A.PULIDO
 */
@Service
public class FileGroupCustomerService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(FileGroupCustomerEntity fgc) {
        int insertCount = 0;
        Connection cn = this.ds.openConnection();
        CallableStatement cs;
        try {
            cs = cn.prepareCall("{call sp_FileGroupCustomerCreate(?,?,?)}");
            cs.setInt(1, fgc.getGroupId());
            cs.setInt(2, fgc.getCustomerId());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            insertCount = cs.getInt(3);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", insertCount, 200);
    }

    public ResponseModel delete(int groupId, int customerId) {
        int deleteCount = 0;
        Connection cn = this.ds.openConnection();
        CallableStatement cs;
        try {
            cn.setAutoCommit(false);
            cs = cn.prepareCall("{call sp_FileGroupCustomerDelete(?,?)}");
            cs.setInt(1, groupId);
            cs.setInt(2, customerId);
            deleteCount = cs.executeUpdate();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", deleteCount, 200);
    }

    public ResponseModel list(int customerId) {
        ArrayList<GenCustomerEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {

            CallableStatement cs = cn.prepareCall("{call sp_FileGroupCustomerList(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenCustomerEntity gce = new GenCustomerEntity();
                gce.setId(rs.getInt("id"));
                gce.setCode(rs.getString("code"));
                gce.setDescription(rs.getString("description"));
                gce.setIncomeActive(rs.getBoolean("incomeActive"));
                gce.setActive(rs.getBoolean("active"));
                list.add(gce);
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
}
