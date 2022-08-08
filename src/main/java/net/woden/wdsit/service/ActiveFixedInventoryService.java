/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ActiveFixedAssigmentEntity;
import net.woden.wdsit.model.ActiveFixedInventoryModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ActiveFixedInventoryService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel listEntryTotal() {
        int total = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedEntryCompanyTotal()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ActiveFixedInventoryModel a = new ActiveFixedInventoryModel();
                total = rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);

    }

    public ResponseModel listEntry() {
        int total = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedEntryCompany()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);

    }

    public ResponseModel listEntryExit() {
        int total = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedExitPermanentCompany}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }

    public ResponseModel listAnswerAproved() {
        int total = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedInventoryAprovedRejected}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }
    public ResponseModel listFlow() {
        int total = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedInventoryFlow}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }
    
    
    

    public ResponseModel listAnswerRejected() {
        int total = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedInventoryRejected}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }
    public ResponseModel listRejected(){
    ArrayList<ActiveFixedAssigmentEntity>list = new ArrayList();
    Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedInventoryListRejected}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                ActiveFixedAssigmentEntity a = new ActiveFixedAssigmentEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getInt("identification"));
                a.setName(rs.getString("name"));
                a.setPersonRes(rs.getString("personRes"));
                a.setSerial(rs.getString("serial"));
                a.setApprovedRejected(rs.getBoolean("approvedRejected"));
                a.setAnswer(rs.getString("answer"));
                a.setProduct(rs.getString("product"));
            list.add(a);
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return  new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
    public ResponseModel listAproved(){
    ArrayList<ActiveFixedAssigmentEntity>list = new ArrayList();
    Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedInventoryListAproved}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                ActiveFixedAssigmentEntity a = new ActiveFixedAssigmentEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getInt("identification"));
                a.setName(rs.getString("name"));
                a.setPersonRes(rs.getString("personRes"));
                a.setSerial(rs.getString("serial"));
                a.setApprovedRejected(rs.getBoolean("approvedRejected"));
                a.setAnswer(rs.getString("answer"));
                a.setProduct(rs.getString("product"));
            list.add(a);
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return  new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
     public ResponseModel listCompany(){
    ArrayList<ActiveFixedAssigmentEntity>list = new ArrayList();
    Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedInventoryListCompany}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                ActiveFixedAssigmentEntity a = new ActiveFixedAssigmentEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getInt("identification"));
                a.setName(rs.getString("name"));
                a.setPersonRes(rs.getString("personRes"));
                a.setSerial(rs.getString("serial"));
                a.setApprovedRejected(rs.getBoolean("approvedRejected"));
                a.setAnswer(rs.getString("answer"));
                a.setProduct(rs.getString("product"));
            list.add(a);
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return  new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
     
      public ResponseModel countAys() {
        int total = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedInventoryCountSupplier}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }
     public ResponseModel countAlqui() {
        int total = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedInventoryCountSupplierAlquilando}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }
     public ResponseModel countRenta() {
        int total = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedInventoryCountSupplierRentasistemas}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }
     public ResponseModel countWoden() {
        int total = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedInventoryCountSupplierWoden}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }
    
    public ResponseModel listAys(){
    ArrayList<ActiveFixedAssigmentEntity>list = new ArrayList();
    Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedInventorySupplierAys}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                ActiveFixedAssigmentEntity a = new ActiveFixedAssigmentEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getInt("identification"));
                a.setName(rs.getString("name"));
                a.setPersonRes(rs.getString("personRes"));
                a.setSerial(rs.getString("serial"));
                a.setProduct(rs.getString("product"));
            list.add(a);
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return  new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
     public ResponseModel listRent(){
    ArrayList<ActiveFixedAssigmentEntity>list = new ArrayList();
    Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedInventorySupplierRenta}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                ActiveFixedAssigmentEntity a = new ActiveFixedAssigmentEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getInt("identification"));
                a.setName(rs.getString("name"));
                a.setPersonRes(rs.getString("personRes"));
                a.setSerial(rs.getString("serial"));
                a.setProduct(rs.getString("product"));
            list.add(a);
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return  new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
      public ResponseModel listAlqui(){
    ArrayList<ActiveFixedAssigmentEntity>list = new ArrayList();
    Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedInventorySupplierAlquilando}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                ActiveFixedAssigmentEntity a = new ActiveFixedAssigmentEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getInt("identification"));
                a.setName(rs.getString("name"));
                a.setPersonRes(rs.getString("personRes"));
                a.setSerial(rs.getString("serial"));
                a.setProduct(rs.getString("product"));
            list.add(a);
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return  new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
       public ResponseModel listWoden(){
    ArrayList<ActiveFixedAssigmentEntity>list = new ArrayList();
    Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedInventorySupplierWoden}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                ActiveFixedAssigmentEntity a = new ActiveFixedAssigmentEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getInt("identification"));
                a.setName(rs.getString("name"));
                a.setPersonRes(rs.getString("personRes"));
                a.setSerial(rs.getString("serial"));
                a.setProduct(rs.getString("product"));
            list.add(a);
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return  new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

}
