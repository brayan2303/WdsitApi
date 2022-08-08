/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ComCommodityEntryArticlesLogEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author m.pulido
 */
@Service
public class ComCommodityEntryArticlesLogService {
    
    @Autowired
    private DataSourceConnection ds;
    
    
    public ResponseModel create(ComCommodityEntryArticlesLogEntity c) {
        String inserts = ""; 
       Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryArticlesLogCreate(?,?,?,?,?)}");
            cs.setInt(1, c.getArticleId());
            cs.setInt(2, c.getQuantityPrevious());
            cs.setInt(3, c.getQuantityNew());
            cs.setInt(4, c.getUserId());
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.execute();
            inserts = cs.getString(5);
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
