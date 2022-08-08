package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenDepartmentEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenDepartmentService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel listActive(int countryId){
        ArrayList<GenDepartmentEntity>lista=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_GenDepartmentListActive(?)}");
            cs.setInt(1,countryId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenDepartmentEntity g=new GenDepartmentEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setCountryId(rs.getInt("countryId"));
                g.setCountry(rs.getString("country"));
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
