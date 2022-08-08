package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.InvCardEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvCardService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(int total,InvCardEntity i) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCardCreate(?,?,?,?)}");
            cs.setInt(1, total);
            cs.setInt(2,i.getCyclicId());
            cs.setString(3,i.getCoutingType());
            cs.registerOutParameter(4,Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(4);
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
    public ResponseModel delete(int cardId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCardDelete(?)}");
            cs.setInt(1, cardId);
            deletes=cs.executeUpdate();
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
    public ResponseModel list(int customerId) {
        ArrayList<InvCardEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCardList(?)}");
            cs.setInt(1, customerId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                InvCardEntity i=new InvCardEntity();
                i.setId(rs.getInt("id"));
                i.setCode(rs.getString("code"));
                i.setCyclicId(rs.getInt("cyclicId"));
                i.setCoutingType(rs.getString("coutingType"));
                i.setDate(rs.getString("date")!=null?rs.getString("date").substring(0,10):"");
                i.setStatus(rs.getString("status"));
                i.setAuditorId(rs.getInt("auditorId"));
                i.setAuditor(rs.getString("auditor"));
                i.setCyclic(rs.getString("cyclic"));
                i.setCustomer(rs.getString("customer"));
                i.setPallet(rs.getString("pallet"));
                i.setLoadDate(rs.getString("loadDate")!=null?rs.getString("loadDate").substring(0,10):"");
                i.setLocation(rs.getString("location"));
                i.setTotal(rs.getInt("total"));
                i.setSampling(rs.getInt("sampling"));
                i.setSigned1(rs.getString("signed1"));
                i.setSigned2(rs.getString("signed2"));
                list.add(i);
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
    public ResponseModel findAvailable(int cyclicId,int quantity) {
        ArrayList<InvCardEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCardFindAvailable(?,?)}");
            cs.setInt(1, cyclicId);
            cs.setInt(2,quantity);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                InvCardEntity i=new InvCardEntity();
                i.setId(rs.getInt("id"));
                i.setCode(rs.getString("code"));
                list.add(i);
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
    public ResponseModel sign(int cardId,String signed,String file) {
        int updates=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCardSign(?,?,?,?)}");
            cs.setInt(1, cardId);
            cs.setString(2,signed);
            cs.setString(3,file);
            cs.registerOutParameter(4,Types.INTEGER);
            cs.execute();
            updates=cs.getInt(4);
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
}
