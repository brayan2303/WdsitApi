package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.MeeGroupEntity;
import net.woden.wdsit.entity.MeeMeetingEntity;
import net.woden.wdsit.model.MeeGroupListPersonModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeeMeetingService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(MeeMeetingEntity m) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeMeetingCreate(?,?,?,?,?,?,?,?)}");
            cs.setString(1, m.getName());
            cs.setString(2, m.getPeriodicity());
            cs.setString(3, m.getStartHour());
            cs.setString(4, m.getEndHour());
            cs.setString(5, m.getCreationDate());
            cs.setString(6, m.getGroupId());
            cs.setInt(7, m.getCreationUserId());
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(8);
            cn.commit();
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

    public ResponseModel update(MeeMeetingEntity m) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeMeetingUpdate(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, m.getId());
            cs.setString(2, m.getName());
            cs.setString(3, m.getPeriodicity());
            cs.setString(4, m.getStartHour());
            cs.setString(5, m.getEndHour());
            cs.setString(6, m.getCreationDate());
            cs.setString(7, m.getGroupId());
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(8);
            cn.commit();
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
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeMeetingDelete(?)}");
            cs.setInt(1, id);
            deletes = cs.executeUpdate();
            cn.commit();
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

    public ResponseModel list(String startDate, String endDate, String groupId) {
        ArrayList<MeeMeetingEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeMeetingList(?,?,?)}");
            cs.setString(1, startDate);
            cs.setString(2, endDate);
            cs.setString(3, groupId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                MeeMeetingEntity m = new MeeMeetingEntity();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setPeriodicity(rs.getString("periodicity"));
                m.setStartHour(rs.getString("startHour"));
                m.setEndHour(rs.getString("endHour"));
                m.setCreationDate(rs.getString("creationDate"));
                m.setCreationUserId(rs.getInt("creationUserId"));
                m.setCreationUser(rs.getString("creationUser"));
                m.setGroupId(rs.getString("groupId"));
                m.setNameGroup(rs.getString("nameGroup"));
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

    public ResponseModel listGroup(int groupId, int userId) {
        ArrayList<MeeGroupListPersonModel> listAll = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_MeeGruopFindById(?,?)}");
            cs.setInt(1, groupId);
            cs.setInt(2, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                MeeGroupListPersonModel m = new MeeGroupListPersonModel();
                m.setId(rs.getInt("id"));
                m.setNameUser(rs.getString("nameUser"));
                listAll.add(m);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", listAll, 200);
    }
    
    public ResponseModel listByUser(String userId){
    ArrayList<MeeGroupEntity> listUser = new ArrayList();
    Connection cn = this.ds.openConnection();
    
        try {
            CallableStatement cs = cn.prepareCall("{call sp_MeeGroupListByUserId(?)}");
            cs.setString(1, userId);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
            MeeGroupEntity mg = new MeeGroupEntity();
            mg.setId(rs.getInt("id"));
            mg.setName(rs.getString("name"));
            mg.setDescription(rs.getString("description"));
            mg.setActive(rs.getBoolean("active"));
            listUser.add(mg);
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
      return  new ResponseModel(getClass().getSimpleName(), "OK", listUser, 200);
    }
}
