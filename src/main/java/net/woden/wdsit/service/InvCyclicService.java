package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.InvCyclicEntity;
import net.woden.wdsit.model.InvCyclicLayoutModel;
import net.woden.wdsit.model.InvCyclicLocationModel;
import net.woden.wdsit.model.InvLocationCoutingModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvCyclicService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(InvCyclicEntity i) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicCreate(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1,i.getName());
            cs.setString(2,i.getTypeSampling());
            cs.setInt(3,i.getSampling());
            cs.setString(4,i.getSystem());
            cs.setBoolean(5,i.isCrossSap());
            cs.setBoolean(6,i.isCrossWms());
            cs.setBoolean(7,i.isCrossBase());
            cs.setString(8,i.getPrincipalSystem());
            cs.setInt(9,i.getCountryId());
            cs.setInt(10,i.getCustomerId());
            cs.setInt(11,i.getPersonId());
            cs.registerOutParameter(12, Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(12);
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
    public ResponseModel update(InvCyclicEntity i) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1,i.getId());
            cs.setString(2,i.getName());
            cs.setString(3,i.getTypeSampling());
            cs.setInt(4,i.getSampling());
            cs.setString(5,i.getSystem());
            cs.setBoolean(6,i.isCrossSap());
            cs.setBoolean(7,i.isCrossWms());
            cs.setBoolean(8,i.isCrossBase());
            cs.setString(9,i.getPrincipalSystem());
            cs.setInt(10,i.getCustomerId());
            cs.setInt(11,i.getPersonId());
            cs.setBoolean(12,i.isActive());
            cs.registerOutParameter(13, Types.INTEGER);
            cs.execute();
            updates=cs.getInt(13);
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
    public ResponseModel delete(int cyclicId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicDelete(?)}");
            cs.setInt(1,cyclicId);
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
    public ResponseModel approveReject(int cyclicId,String status) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicApproveReject(?,?,?)}");
            cs.setInt(1,cyclicId);
            cs.setString(2,status);
            cs.registerOutParameter(3,Types.INTEGER);
            cs.execute();
            updates=cs.getInt(3);
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
    public ResponseModel findById(int cyclicId) {
        InvCyclicEntity i=null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicFindById(?)}");
            cs.setInt(1, cyclicId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                i=new InvCyclicEntity();
                i.setId(rs.getInt("id"));
                i.setName(rs.getString("name"));
                i.setTypeSampling(rs.getString("typeSampling"));
                i.setSampling(rs.getInt("sampling"));
                i.setSystem(rs.getString("system"));
                i.setCrossSap(rs.getBoolean("crossSap"));
                i.setCrossWms(rs.getBoolean("crossWms"));
                i.setCrossBase(rs.getBoolean("crossBase"));
                i.setPrincipalSystem(rs.getString("principalSystem"));
                i.setCreationDate(rs.getString("creationDate"));
                i.setStatus(rs.getString("status"));
                i.setCustomerId(rs.getInt("customerId"));
                i.setCustomer(rs.getString("customer"));
                i.setPersonId(rs.getInt("personId"));
                i.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", i, 200);
    }
    public ResponseModel findLocation(int cyclicId,String system,String typeSampling,String type,String customer) {
        ArrayList<InvCyclicLocationModel>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicFindLocation(?,?,?,?,?)}");
            cs.setInt(1,cyclicId);
            cs.setString(2, system);
            cs.setString(3,typeSampling);
            cs.setString(4,type);
            cs.setString(5,customer);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                InvCyclicLocationModel i=new InvCyclicLocationModel();
                i.setNumber(rs.getString("number"));
                i.setPallets(rs.getInt("pallets"));
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
    public ResponseModel list(int customerId,int personId,String type) {
        ArrayList<InvCyclicEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicList(?,?,?)}");
            cs.setInt(1,customerId);
            cs.setInt(2,personId);
            cs.setString(3,type);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                InvCyclicEntity i=new InvCyclicEntity();
                i.setId(rs.getInt("id"));
                i.setName(rs.getString("name"));
                i.setTypeSampling(rs.getString("typeSampling"));
                i.setSampling(rs.getInt("sampling"));
                i.setTotal(rs.getInt("total"));
                i.setSystem(rs.getString("system"));
                i.setCrossSap(rs.getBoolean("crossSap"));
                i.setCrossWms(rs.getBoolean("crossWms"));
                i.setCrossBase(rs.getBoolean("crossBase"));
                i.setPrincipalSystem(rs.getString("principalSystem"));
                i.setCreationDate(rs.getString("creationDate").substring(0,10));
                i.setStatus(rs.getString("status"));
                i.setCustomerId(rs.getInt("customerId"));
                i.setCustomer(rs.getString("customer"));
                i.setPersonId(rs.getInt("personId"));
                i.setActive(rs.getBoolean("active"));
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
    public ResponseModel listByCustomerId(int customerId) {
        ArrayList<InvCyclicEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicListByCustomerId(?)}");
            cs.setInt(1,customerId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                InvCyclicEntity i=new InvCyclicEntity();
                i.setId(rs.getInt("id"));
                i.setName(rs.getString("name"));
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
    public ResponseModel totalPallets(int cyclicId) {
        int total=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicTotalPallets(?)}");
            cs.setInt(1, cyclicId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                total=rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }
    public ResponseModel totalSerials(int cyclicId) {
        int total=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicTotalSerials(?)}");
            cs.setInt(1, cyclicId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                total=rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }
    public ResponseModel totalAccesories(int cyclicId) {
        int total=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicTotalAccesories(?)}");
            cs.setInt(1, cyclicId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                total=rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }
    public ResponseModel samplingPallets(int cyclicId) {
        int total=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicSamplingPallets(?)}");
            cs.setInt(1, cyclicId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                total=rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }
    public ResponseModel samplingSerials(int cyclicId) {
        int total=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicSamplingSerials(?)}");
            cs.setInt(1, cyclicId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                total=rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }
    public ResponseModel samplingAccesories(int cyclicId) {
        int total=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicSamplingAccesories(?)}");
            cs.setInt(1, cyclicId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                total=rs.getInt("total");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }
    public ResponseModel audited(int cyclicId,String type) {
        int audited=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicAudited(?,?)}");
            cs.setInt(1, cyclicId);
            cs.setString(2,type);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                audited=rs.getInt("audited");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", audited, 200);
    }
    public ResponseModel layout(String system,String customer) {
        InvCyclicLayoutModel i=null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicLayout(?,?)}");
            cs.setString(1, system);
            cs.setString(2,customer);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                i=new InvCyclicLayoutModel();
                i.setPallets(rs.getInt("pallets"));
                i.setSerials(rs.getInt("serials"));
                i.setAccesories(rs.getInt("accesories"));
                i.setSapCodes(rs.getInt("sapCodes"));
                i.setLocations(rs.getInt("locations"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", i, 200);
    }
    public ResponseModel locationCouting(int cyclicId) {
        ArrayList<InvLocationCoutingModel>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicLocationCouting(?)}");
            cs.setInt(1, cyclicId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                InvLocationCoutingModel i=new InvLocationCoutingModel();
                i.setLocation(rs.getString("location"));
                i.setCoutingFirst(rs.getInt("coutingFirst"));
                i.setCoutingSecond(rs.getInt("coutingSecond"));
                i.setCoutingThird(rs.getInt("coutingThird"));
                i.setCoutingFourth(rs.getInt("coutingFourth"));
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
    public ResponseModel sapCodeSerial(int cyclicId) {
        ArrayList<InvLocationCoutingModel>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCyclicSapCodeSerial(?)}");
            cs.setInt(1, cyclicId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                InvLocationCoutingModel i=new InvLocationCoutingModel();
                i.setLocation(rs.getString("location"));
                i.setSapCode(rs.getString("sapCode"));
                i.setCoutingFirst(rs.getInt("coutingFirst"));
                i.setCoutingSecond(rs.getInt("coutingSecond"));
                i.setCoutingThird(rs.getInt("coutingThird"));
                i.setCoutingFourth(rs.getInt("coutingFourth"));
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
}
