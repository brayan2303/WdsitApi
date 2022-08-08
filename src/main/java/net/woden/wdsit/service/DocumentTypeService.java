package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.DocumentTypeEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author f.casallas
 */
@Service
public class DocumentTypeService {
    
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(DocumentTypeEntity d) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentTypeCreate(?,?,?,?,?,?)}");
            cs.setString(1, d.getGroupDocument());
            cs.setString(2, d.getDescription());
            cs.setInt(3, d.getIdGroup());
            cs.setInt(4, d.getCreateUserId());
            cs.setInt(5, d.getIdCode());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(6);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }
    
    public ResponseModel update(int updateUserId ,DocumentTypeEntity d) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentTypeUpdate (?,?,?,?,?,?,?,?)}");
            cs.setInt(1, d.getId()); 
            cs.setString(2, d.getGroupDocument());
            cs.setString(3, d.getDescription());
            cs.setInt(4, d.getIdGroup());
            cs.setInt(5, updateUserId);
            cs.setBoolean(6, d.getActive());
            cs.setInt(7, d.getIdCode());
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(8);
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
            CallableStatement cs = cn.prepareCall("{call sp_DocumentTypeDelete(?)}");
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
        ArrayList<DocumentTypeEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentTypeList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                DocumentTypeEntity d = new DocumentTypeEntity();
                d.setId(rs.getInt("id"));
                d.setGroupDocument(rs.getString("groupDocument"));
                d.setDescription(rs.getString("description"));
                d.setIdGroup(rs.getInt("groupId"));
                d.setGroupName(rs.getString("groupName"));
                d.setActive(rs.getBoolean("active"));
                d.setIdCode(rs.getInt("idCode"));
                d.setCodeName(rs.getString("codeName"));
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
    
    public ResponseModel listByLevelAccess(int userId) {
        ArrayList<DocumentTypeEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentTypeByLevelAccessList(?)}");
            cs.setInt(1, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                DocumentTypeEntity d = new DocumentTypeEntity();
                d.setId(rs.getInt("id"));
                d.setGroupDocument(rs.getString("groupDocument"));
                d.setDescription(rs.getString("description"));
                d.setIdGroup(rs.getInt("groupId"));
                d.setGroupName(rs.getString("groupName"));
                d.setActive(rs.getBoolean("active"));
                d.setIdCode(rs.getInt("idCode"));
                d.setCodeName(rs.getString("codeName"));
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
    
    public ResponseModel findById(int id) {
        DocumentTypeEntity d = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentTypeFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                d = new DocumentTypeEntity();
                d.setId(rs.getInt("id"));
                d.setGroupDocument(rs.getString("groupDocument"));
                d.setDescription(rs.getString("description"));
                d.setIdGroup(rs.getInt("groupId"));
                d.setGroupName(rs.getString("groupName"));
                d.setActive(rs.getBoolean("active"));
                d.setIdCode(rs.getInt("idCode"));
                d.setCodeName(rs.getString("codeName"));
            }
            cn.commit();
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
    
    /*public ResponseModel listType() {
        ArrayList<DocumentTypeModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_DocumentTypeListType()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                DocumentTypeModel d = new DocumentTypeModel();
                d.setId(rs.getInt("id"));
                d.setGroupDocument(rs.getString("groupDocument"));
                d.setDescription(rs.getString("description"));
                d.setGroupName(rs.getString("groupName"));
                d.setIdCode(rs.getString("idCode"));
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
    }    */    
    
    
}
