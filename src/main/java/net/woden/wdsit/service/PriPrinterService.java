package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.PriPrinterEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriPrinterService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(PriPrinterEntity p){
        int insert=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PriPrinterCreate(?,?,?,?,?)}");
            cs.setString(1, p.getName());
            cs.setString(2, p.getIp());
            cs.setString(3, p.getLocation());
            cs.setInt(4, p.getCustomerId());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            insert=cs.getInt(5);
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
        return new ResponseModel(getClass().getSimpleName(),"OK",insert,200);
    }
    public ResponseModel update(PriPrinterEntity p){
        int updates=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PriPrinterUpdate(?,?,?,?,?,?,?)}");
            cs.setInt(1, p.getId());
            cs.setString(2, p.getName());
            cs.setString(3, p.getIp());
            cs.setString(4, p.getLocation());
            cs.setInt(5, p.getCustomerId());
            cs.setBoolean(6, p.isActive());
            cs.registerOutParameter(7, Types.INTEGER);
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
    public ResponseModel delete(int id){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PriPrinterDelete(?)}");
            cs.setInt(1, id);
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
    public ResponseModel findById(int id){
        PriPrinterEntity p=null;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PriPrinterFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                p=new PriPrinterEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setIp(rs.getString("ip"));
                p.setLocation(rs.getString("location"));
                p.setCustomerId(rs.getInt("customerId"));
                p.setCustomer(rs.getString("customer"));
                p.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(),"OK",p,200);
    }
    public ResponseModel list(){
        ArrayList<PriPrinterEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PriPrinterList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                PriPrinterEntity p=new PriPrinterEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setIp(rs.getString("ip"));
                p.setLocation(rs.getString("location"));
                p.setCustomerId(rs.getInt("customerId"));
                p.setCustomer(rs.getString("customer"));
                p.setActive(rs.getBoolean("active"));
                list.add(p);
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
    public ResponseModel listActive(int customerId,String location){
        ArrayList<PriPrinterEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PriPrinterListActive(?,?)}");
            cs.setInt(1, customerId);
            cs.setString(2, location);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                PriPrinterEntity p=new PriPrinterEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setIp(rs.getString("ip"));
                p.setLocation(rs.getString("location"));
                p.setCustomerId(rs.getInt("customerId"));
                p.setCustomer(rs.getString("customer"));
                p.setActive(rs.getBoolean("active"));
                list.add(p);
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
