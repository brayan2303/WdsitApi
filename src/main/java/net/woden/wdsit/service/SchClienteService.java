package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceScheduleConnection;
import net.woden.wdsit.entity.SchClienteEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchClienteService {
    @Autowired
    private DataSourceScheduleConnection dss;
    
    public ResponseModel create(SchClienteEntity s){
        int inserts=0;
        Connection cn=this.dss.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_ClienteCreate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1,s.getNombre());
            cs.setString(2,s.getSigla());
            cs.setInt(3,s.getPaisId());
            cs.setString(4,s.getTelefono());
            cs.setString(5,s.getCorreo());
            cs.setString(6,s.getPie1());
            cs.setString(7,s.getPie2());
            cs.setString(8,s.getChat());
            cs.setString(9,s.getWhatsapp());
            cs.setString(10,s.getColor());
            cs.setBoolean(11,s.isPublicidad());
            cs.setBoolean(12,s.isPoliticaDato());
            cs.setString(13,s.getUrlPolitica());
            cs.setString(14,s.getUrlTyC());
            cs.setBoolean(15,s.isEnvioInformacion());
            cs.setBoolean(16,s.isEnvioNotificacion());
            cs.setString(17,s.getTipoCampana());
            cs.setString(18,s.getCampana());
            cs.registerOutParameter(19,Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(19);
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
    public ResponseModel update(SchClienteEntity s){
        int updates=0;
        Connection cn=this.dss.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_ClienteUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1,s.getId());
            cs.setString(2,s.getNombre());
            cs.setString(3,s.getSigla());
            cs.setInt(4,s.getPaisId());
            cs.setString(5,s.getTelefono());
            cs.setString(6,s.getCorreo());
            cs.setString(7,s.getPie1());
            cs.setString(8,s.getPie2());
            cs.setString(9,s.getChat());
            cs.setString(10,s.getWhatsapp());
            cs.setString(11,s.getColor());
            cs.setBoolean(12,s.isPublicidad());
            cs.setBoolean(13,s.isPoliticaDato());
            cs.setString(14,s.getUrlPolitica());
            cs.setString(15,s.getUrlTyC());
            cs.setBoolean(16,s.isEnvioInformacion());
            cs.setBoolean(17,s.isEnvioNotificacion());
            cs.setString(18,s.getTipoCampana());
            cs.setString(19,s.getCampana());
            cs.setBoolean(20,s.isActivo());
            cs.registerOutParameter(21,Types.INTEGER);
            cs.execute();
            updates=cs.getInt(21);
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
    public ResponseModel delete(int clienteId){
        int deletes=0;
        Connection cn=this.dss.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_ClienteDelete(?)}");
            cs.setInt(1,clienteId);
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
        ArrayList<SchClienteEntity>lista=new ArrayList();
        Connection cn=this.dss.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_ClienteList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                SchClienteEntity s=new SchClienteEntity();
                s.setId(rs.getInt("id"));
                s.setNombre(rs.getString("nombre"));
                s.setSigla(rs.getString("sigla"));
                s.setPaisId(rs.getInt("paisId"));
                s.setPais(rs.getString("pais"));
                s.setTelefono(rs.getString("telefono"));
                s.setCorreo(rs.getString("correo"));
                s.setPie1(rs.getString("pie1"));
                s.setPie2(rs.getString("pie2"));
                s.setChat(rs.getString("chat"));
                s.setWhatsapp(rs.getString("whatsapp"));
                s.setColor(rs.getString("color"));
                s.setPublicidad(rs.getBoolean("publicidad"));
                s.setPoliticaDato(rs.getBoolean("politicaDato"));
                s.setUrlPolitica(rs.getString("urlPolitica"));
                s.setUrlTyC(rs.getString("urlTyC"));
                s.setEnvioInformacion(rs.getBoolean("envioInformacion"));
                s.setEnvioNotificacion(rs.getBoolean("envioNotificacion"));
                s.setTipoCampana(rs.getString("tipoCampana"));
                s.setCampana(rs.getString("campana"));
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
