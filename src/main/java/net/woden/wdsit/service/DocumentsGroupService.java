package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.DocumentsGroupEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author f.casallas
 */
@Service
public class DocumentsGroupService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(DocumentsGroupEntity d) {
        //ResponseModel = llamado de dato
        int inserts = 0;
        // Variable contadora usada para insertar los registros 
        Connection cn = this.ds.openConnection();
        // Conexión BD
        // Manejo de errores try - catch
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentsGroupCreate(?,?,?)}");
            // Llamada procedimiento almacenado de BD
            cs.setString(1, d.getCode());
            // Llamada datos BD
            cs.setString(2, d.getDescription());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            // Ejecución BD
            inserts = cs.getInt(3);
            // Insercción de registros
            cs.close();
            // Procedimiento cerrado
            cn.close();
            // Conexión BD cerrada
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }

    public ResponseModel update(DocumentsGroupEntity d) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentsGroupUpdate(?,?,?,?,?)}");
            cs.setInt(1, d.getId());
            cs.setString(2, d.getCode());
            cs.setString(3, d.getDescription());
            cs.setBoolean(4, d.getActive());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(5);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel delete(int id) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentsGroupDelete(?)}");
            cs.setInt(1, id);
            deletes = cs.executeUpdate();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }

    public ResponseModel list() {
        // Creación de array para mostrar la información
        ArrayList<DocumentsGroupEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentsGroupList()}");
            // Muestra las filas / columnas 
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                // Creación objeto d para obtener las variables de la clase DocumentsGroupEntity
                DocumentsGroupEntity d = new DocumentsGroupEntity();
                // Envio de información desde la BD
                d.setId(rs.getInt("id"));
                d.setCode(rs.getString("code"));
                d.setDescription(rs.getString("description"));
                d.setActive(rs.getBoolean("active"));
                list.add(d);
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

    public ResponseModel findyById(int id) {
        DocumentsGroupEntity d = null;
        Connection cn = this.ds.openConnection();

        try {
            //cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DocumentsGroupFindById (?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                d = new DocumentsGroupEntity();
                d.setId(rs.getInt("id"));
                d.setCode(rs.getString("code"));
                d.setDescription(rs.getString("description"));
                d.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", d, 200);
    }

}
