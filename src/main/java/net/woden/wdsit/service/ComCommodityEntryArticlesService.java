package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ComCommodityEntryArticlesEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author m.pulido
 */
@Service
public class ComCommodityEntryArticlesService {
    
    
    @Autowired
    private DataSourceConnection ds;
    
    
    public ResponseModel listByEntryId(int entryId) {
        ArrayList<ComCommodityEntryArticlesEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_comCommodityEntryArticlesListByEntryId(?)}");
            cs.setInt(1, entryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComCommodityEntryArticlesEntity s = new ComCommodityEntryArticlesEntity();
                s.setId(rs.getInt("id"));
                s.setIdCommodityEntry(rs.getInt("idCommodityEntry"));
                s.setSapCode(rs.getString("sapCode"));
                s.setSapCodeDescription(rs.getString("sapCodeDescription"));
                s.setQuantity(rs.getInt("quantity"));
                s.setUserId(rs.getInt("userId"));
                s.setCreationDate(rs.getString("creationDate"));
                list.add(s);
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
    
    public ResponseModel listByEntryNumber(String entryNumber) {
        ArrayList<ComCommodityEntryArticlesEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_comCommodityEntryArticlesListByEntryNumber(?)}");
            cs.setString(1, entryNumber);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComCommodityEntryArticlesEntity s = new ComCommodityEntryArticlesEntity();
                s.setId(rs.getInt("id"));
                s.setIdCommodityEntry(rs.getInt("idCommodityEntry"));
                s.setSapCode(rs.getString("sapCode"));
                s.setSapCodeDescription(rs.getString("sapCodeDescription"));
                s.setQuantity(rs.getInt("quantity"));
                s.setUserId(rs.getInt("userId"));
                s.setCreationDate(rs.getString("creationDate"));
                list.add(s);
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
    
    public ResponseModel update(ComCommodityEntryArticlesEntity e) {
        Connection cn = this.ds.openConnection();
        int update = 0;
        try {
            CallableStatement cs = cn.prepareCall("{call sp_comCommodityEntryArticlesUpdate(?,?,?,?)}");
            cs.setInt(1, e.getId());
            cs.setInt(2, e.getQuantity());
            cs.setInt(3, e.getUserId());
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            update = cs.getInt(4);
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
    
}
