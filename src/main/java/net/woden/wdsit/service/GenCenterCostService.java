package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenCenterCostEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenCenterCostService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(GenCenterCostEntity g){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCenterCostCreate(?,?,?,?,?)}");
            cs.setString(1,g.getCode());
            cs.setString(2,g.getDescription());
            cs.setInt(3,g.getSegmentId());
            cs.setBoolean(4,g.isIncomeActive());
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
    public ResponseModel update(GenCenterCostEntity g){
        int updates=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCenterCostUpdate(?,?,?,?,?,?,?)}");
            cs.setInt(1,g.getId());
            cs.setString(2,g.getCode());
            cs.setString(3,g.getDescription());
            cs.setInt(4,g.getSegmentId());
            cs.setBoolean(5,g.isIncomeActive());
            cs.setBoolean(6,g.isActive());
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
    public ResponseModel delete(int centerCostId){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCenterCostDeletes(?)}");
            cs.setInt(1,centerCostId);
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
        ArrayList<GenCenterCostEntity>lista=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCenterCostFindAll()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenCenterCostEntity g=new GenCenterCostEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
                g.setSegmentId(rs.getInt("segmentId"));
                g.setIncomeActive(rs.getBoolean("incomeActive"));
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
    public ResponseModel findBySegmentId(int segmentId){
        ArrayList<GenCenterCostEntity>lista=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCenterCostFindBySegmentId(?)}");
            cs.setInt(1,segmentId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenCenterCostEntity g=new GenCenterCostEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
                g.setSegmentId(rs.getInt("segmentId"));
                g.setIncomeActive(rs.getBoolean("incomeActive"));
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
    public ResponseModel findById(int centerCostId){
        GenCenterCostEntity g=null;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCenterCostFindById(?)}");
            cs.setInt(1,centerCostId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                g=new GenCenterCostEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
                g.setSegmentId(rs.getInt("segmentId"));
                g.setIncomeActive(rs.getBoolean("incomeActive"));
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
    public ResponseModel list(){
        ArrayList<GenCenterCostEntity>lista=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenCenterCostList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenCenterCostEntity g=new GenCenterCostEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
                g.setSegmentId(rs.getInt("segmentId"));
                g.setIncomeActive(rs.getBoolean("incomeActive"));
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
}
