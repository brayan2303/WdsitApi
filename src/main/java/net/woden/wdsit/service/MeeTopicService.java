package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.MeeTopicEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeeTopicService {

    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel list(String periodicity) {
        ArrayList<MeeTopicEntity> list = new ArrayList();
        Connection cn = ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeTopicList(?)}");
            cs.setString(1, periodicity);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                MeeTopicEntity m = new MeeTopicEntity();
                m.setId(rs.getInt("id"));
                m.setTitle(rs.getString("title"));
                m.setDetail(rs.getString("detail"));
                m.setDescription(rs.getString("description"));
                m.setType(rs.getString("type"));
                m.setRequired(rs.getBoolean("required"));
                m.setActive(rs.getBoolean("active"));
                list.add(m);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
}
