package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.RepWolkboxEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepWolkboxService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel list() {
        ArrayList<RepWolkboxEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepWolkboxList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                RepWolkboxEntity r=new RepWolkboxEntity();
                r.setIdCall(rs.getString("idCall"));
                r.setAgentName(rs.getString("agentName"));
                r.setId(rs.getString("id"));
                r.setDate(rs.getString("date"));
                r.setDescription(rs.getString("description"));
                r.setComments(rs.getString("comments"));
                r.setTypeCall(rs.getString("typeCall"));
                r.setTelephone(rs.getString("telephone"));
                r.setDuration(rs.getString("duration"));
                r.setIdCampaing(rs.getString("idCampaing"));
                list.add(r);
            }
            cn.commit();
            rs.close();
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
}
