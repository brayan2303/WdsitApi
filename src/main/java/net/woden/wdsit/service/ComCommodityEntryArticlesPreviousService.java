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
import net.woden.wdsit.entity.ComCommodityEntryArticlesPreviousEntity;
import net.woden.wdsit.entity.ComCommodityEntryEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author m.pulido
 */

@Service
public class ComCommodityEntryArticlesPreviousService {
    
    
    @Autowired
    private DataSourceConnection ds;
    
    @Autowired
    private ComCommodityEntryService ComCommodityEntryS;
    
    public ResponseModel listByEntryId(int entryId) {
        ArrayList<ComCommodityEntryArticlesPreviousEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_comCommodityEntryArticlesPreviousListByEntryId(?)}");
            cs.setInt(1, entryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComCommodityEntryArticlesPreviousEntity s = new ComCommodityEntryArticlesPreviousEntity();
                s.setId(rs.getInt("id"));
                s.setIdCommodityEntry(rs.getInt("idCommodityEntry"));
                s.setIdCommodityEntryName(rs.getString("idCommodityEntryName"));
                s.setSapCode(rs.getString("sapCode"));
                s.setSapCodeDescription(rs.getString("sapCodeDescription"));
                s.setQuantity(rs.getInt("quantity"));
                s.setUserId(rs.getInt("userId"));
                s.setUserName(rs.getString("userName"));
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
    
    public ResponseModel create(ComCommodityEntryArticlesPreviousEntity c) {
        String inserts = ""; 
       Connection cn = this.ds.openConnection();
       
        try {
            CallableStatement cs = cn.prepareCall("{call sp_comCommodityEntryArticlesPreviousCreate(?,?,?,?,?,?)}");
            cs.setInt(1, c.getIdCommodityEntry());
            cs.setString(2, c.getSapCode());
            cs.setString(3, c.getSapCodeDescription());
            cs.setInt(4, c.getQuantity());
            cs.setInt(5, c.getUserId());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            inserts = cs.getString(6);
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
    
    public ResponseModel delete(int articleId) {
        int inserts = 0; 
       Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_comCommodityEntryArticlesPreviousDelete(?)}");
            cs.setInt(1, articleId);
            inserts = 1; 
            cs.execute();
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
    
}
