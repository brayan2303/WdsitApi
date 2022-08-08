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
import net.woden.wdsit.connection.DataSourceSapColombiaConnection;
import net.woden.wdsit.entity.DryIceEntity;
import net.woden.wdsit.model.DryIceModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DryIceService {
    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private DataSourceSapColombiaConnection dsc;
    
   public ResponseModel create (DryIceEntity d){
       int insert = 0;
       Connection cnSap= this.dsc.openConnection();
       DryIceModel dm = null;
       try {
           CallableStatement cs=  cnSap.prepareCall("{call wdn.sp_DryIceSearchSerial(?)}");
          cs.setString(1, d.getSerial());
          ResultSet rs=cs.executeQuery();
           while(rs.next()){
           dm= new DryIceModel();
           dm.setCodSap(rs.getString("CodigoSap"));
           dm.setDescription(rs.getString("Descripcion"));
           dm.setSerial(rs.getString("Serial"));
           }
          cs.close();
          cnSap.close();
         
       } catch (SQLException ex) {
           return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
       }finally{
           try {
               cnSap.close();
           } catch (SQLException ex) {
               return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
           }
       }
       
       if(dm != null){
            Connection cn= this.ds.openConnection();
            try {
               CallableStatement cs=  cn.prepareCall("{call sp_DryIceCreate(?,?,?,?,?)}");
               cs.setString(1, d.getSerial());
               cs.setInt(2, d.getUserId());
               cs.setString(3, dm.getDescription());
               cs.setString(4, dm.getCodSap());
               cs.registerOutParameter(5, Types.INTEGER);
               cs.execute();
               insert = cs.getInt(5);
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
             return new ResponseModel(getClass().getSimpleName(), "OK", insert, 200);
       } else {
           return new ResponseModel(getClass().getSimpleName(), "El serial no se encuentra diagnosticado", null, 200);
       }
   }
   public ResponseModel list(){
     ArrayList<DryIceModel> list = new ArrayList();
     Connection cn=this.ds.openConnection();
       try {
           CallableStatement cs=cn.prepareCall("{call sp_DryIceList()}");
           ResultSet rs=cs.executeQuery();
           while(rs.next()){
           DryIceModel d= new DryIceModel();
           d.setId(rs.getInt("id"));
           d.setCodSap(rs.getString("codSap"));
           d.setCreationDate(rs.getString("creationDate"));
           d.setDescription(rs.getString("description"));
           d.setActive(rs.getBoolean("active"));
           d.setModel(rs.getString("model"));
           d.setCreationDatefinish(rs.getString("creationDatefinish"));
           d.setSerial(rs.getString("serial"));
           d.setUserId(rs.getInt("userId"));
           d.setUserName(rs.getString("userName"));
           
           list.add(d);
           }
       } catch (SQLException ex) {
           return new ResponseModel(getClass().getSimpleName(),ex.getMessage(),null,200);
       }finally{
           try {
               cn.close();
              
           } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(),ex.getMessage(),null, 200);
           }
       }
       return new ResponseModel(getClass().getSimpleName(), "OK",list, 200);
       
   }
    public ResponseModel update() {
        int update = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call  sp_DryIceUpdateDate(?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            update = cs.getInt(1);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", update, 200);
    }
     public ResponseModel listFinish(){
     ArrayList<DryIceModel> list = new ArrayList();
     Connection cn=this.ds.openConnection();
       try {
           CallableStatement cs=cn.prepareCall("{call sp_DryIceListFinish()}");
           ResultSet rs=cs.executeQuery();
           while(rs.next()){
           DryIceModel d= new DryIceModel();
           d.setId(rs.getInt("id"));
           d.setCodSap(rs.getString("codSap"));
           d.setCreationDate(rs.getString("creationDate"));
           d.setDescription(rs.getString("description"));
           d.setActive(rs.getBoolean("active"));
           d.setModel(rs.getString("model"));
           d.setCreationDatefinish(rs.getString("creationDatefinish"));
           d.setSerial(rs.getString("serial"));
           d.setUserId(rs.getInt("userId"));
           d.setUserName(rs.getString("userName"));
           
           list.add(d);
           }
       } catch (SQLException ex) {
           return new ResponseModel(getClass().getSimpleName(),ex.getMessage(),null,200);
       }finally{
           try {
               cn.close();
              
           } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(),ex.getMessage(),null, 200);
           }
       }
       return new ResponseModel(getClass().getSimpleName(), "OK",list, 200);
       
   }
          
    
}
