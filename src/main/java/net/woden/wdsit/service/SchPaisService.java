package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceScheduleConnection;
import net.woden.wdsit.entity.SchPaisEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchPaisService {
    @Autowired
    private DataSourceScheduleConnection dss;
    
    public ResponseModel create(SchPaisEntity s){
        int inserts=0;
        Connection cn=this.dss.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PaisCreate(?,?,?,?)}");
            cs.setString(1,s.getCodigo());
            cs.setString(2,s.getCodigo1());
            cs.setString(3,s.getNombre());
            cs.registerOutParameter(4,Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(4);
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
    public ResponseModel update(SchPaisEntity s){
        int updates=0;
        Connection cn=this.dss.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PaisUpdate(?,?,?,?,?,?)}");
            cs.setInt(1,s.getId());
            cs.setString(2,s.getCodigo());
            cs.setString(3,s.getCodigo1());
            cs.setString(4,s.getNombre());
            cs.setBoolean(5,s.isActivo());
            cs.registerOutParameter(6,Types.INTEGER);
            cs.execute();
            updates=cs.getInt(6);
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
    public ResponseModel delete(int paisId){
        int deletes=0;
        Connection cn=this.dss.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PaisDelete(?)}");
            cs.setInt(1,paisId);
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
    public ResponseModel list(){
        ArrayList<SchPaisEntity>lista=new ArrayList();
        Connection cn=this.dss.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_PaisList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                SchPaisEntity s=new SchPaisEntity();
                s.setId(rs.getInt("id"));
                s.setCodigo(rs.getString("codigo"));
                s.setCodigo1(rs.getString("codigo1"));
                s.setNombre(rs.getString("nombre"));
                s.setActivo(rs.getBoolean("activo"));
                lista.add(s);
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
