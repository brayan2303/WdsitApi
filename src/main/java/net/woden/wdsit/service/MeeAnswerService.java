package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.MeeAnswerEntity;
import net.woden.wdsit.model.MeeAnswerModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeeAnswerService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ArrayList<MeeAnswerEntity> array) {
        int inserts = 0;
        Connection cn = ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeAnswerCreate(?,?,?,?)}");
            for (MeeAnswerEntity m : array) {
                cs.setInt(1, m.getMeetingId());
                cs.setInt(2, m.getTopicId());
                cs.setString(3, m.getAnswer());
                cs.setInt(4, m.getAnswerUserId());
                cs.addBatch();
            }
            inserts = cs.executeBatch().length;
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

    public ResponseModel list(int meetingId) {
        ArrayList<MeeAnswerModel> list = new ArrayList();
        Connection cn = ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeAnswerList(?)}");
            cs.setInt(1, meetingId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                MeeAnswerModel m = new MeeAnswerModel();
                m.setId(rs.getInt("id"));
                m.setIdentification(rs.getString("identification"));
                m.setFirstName(rs.getString("firstName"));
                m.setLastName(rs.getString("lastName"));
                m.setUserName(rs.getString("userName"));
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

    public ResponseModel listByUserId(int meetingId, String peroidicty, String answerDate, int answerUserId) {
        ArrayList<MeeAnswerEntity> list = new ArrayList();
        Connection cn = ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeAnswerListByUserId(?,?,?,?)}");
            cs.setInt(1, meetingId);
            cs.setString(2, peroidicty);
            cs.setString(3, answerDate);
            cs.setInt(4, answerUserId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                MeeAnswerEntity m = new MeeAnswerEntity();
                m.setId(rs.getInt("id"));
                m.setMeetingId(rs.getInt("meetingId"));
                m.setMeeting(rs.getString("meeting"));
                m.setTopicId(rs.getInt("topicId"));
                m.setTopic(rs.getString("topic"));
                m.setAnswer(rs.getString("answer"));
                m.setAnswerDate(rs.getString("answerDate"));
                m.setAnswerUserId(rs.getInt("answerUserId"));
                m.setAnswerUser(rs.getString("answerUser"));
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

    public ResponseModel listWeek(int meetingId, int answerUserId) {
        ArrayList<String> list = new ArrayList();
        Connection cn = ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeAnswerListByUserId(?,?)}");
            cs.setInt(1, meetingId);
            cs.setInt(2, answerUserId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("answerDate"));
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

    public ResponseModel update(MeeAnswerEntity m) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("call sp_MeeAnswerUpdate(?,?,?)");
            cs.setInt(1, m.getId());
            cs.setString(2, m.getAnswer());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(3);
            cn.close();
            cs.close();

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
}
