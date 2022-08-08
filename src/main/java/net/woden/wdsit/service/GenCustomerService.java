package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenCustomerService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(GenCustomerEntity g){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCustomerCreate(?,?,?)}");
            cs.setString(1,g.getCode());
            cs.setString(2,g.getDescription());
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
    public ResponseModel update(GenCustomerEntity g){
        int updates=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCustomerUpdate(?,?,?,?,?)}");
            cs.setInt(1,g.getId());
            cs.setString(2,g.getCode());
            cs.setString(3,g.getDescription());
            cs.setBoolean(4,g.isActive());
            cs.registerOutParameter(5,Types.INTEGER);
            cs.execute();
            updates=cs.getInt(5);
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
    public ResponseModel delete(int customerId){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCustomerDelete(?)}");
            cs.setInt(1,customerId);
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
    public ResponseModel findAll(){
        ArrayList<GenCustomerEntity>lista=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCustomerFindAll()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenCustomerEntity g=new GenCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
                g.setActive(rs.getBoolean("active"));
                lista.add(g);
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
        return new ResponseModel(getClass().getSimpleName(),"OK",lista,200);
    }
    public ResponseModel findById(int customerId){
        GenCustomerEntity g=null;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCustomerFindById(?)}");
            cs.setInt(1,customerId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                g=new GenCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
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
    public ResponseModel findByIncomeActive(){
        ArrayList<GenCustomerEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCustomerFindByIncomeActive()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenCustomerEntity g=new GenCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
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
    public ResponseModel list(){
        ArrayList<GenCustomerEntity>lista=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCustomerList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenCustomerEntity g=new GenCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
                g.setActive(rs.getBoolean("active"));
                lista.add(g);
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
        return new ResponseModel(getClass().getSimpleName(),"OK",lista,200);
    }
    public ResponseModel findByCustomer(String customer){
        GenCustomerEntity g=null;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCustomerFindByCustomer(?)}");
            cs.setString(1,customer);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                g=new GenCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
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
    
}
