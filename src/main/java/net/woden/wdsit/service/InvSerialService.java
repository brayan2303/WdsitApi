package net.woden.wdsit.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.InvSerialEntity;
import net.woden.wdsit.model.InvCyclicSerialModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class InvSerialService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(String coutingType, InvSerialEntity i) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvSerialCreate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, coutingType);
            cs.setInt(2, i.getCoutingId());
            cs.setString(3, i.getSerial());
            cs.setString(4, i.getMac());
            cs.setString(5, i.getSapCode());
            cs.setString(6, i.getSapCodeSap());
            cs.setString(7, i.getSapCodeWms());
            cs.setString(8, i.getSapCodeBase());
            cs.setString(9, i.getStatus());
            cs.setString(10, i.getStatusSap());
            cs.setString(11, i.getStatusWms());
            cs.setString(12, i.getStatusBase());
            cs.setString(13, i.getPallet());
            cs.setString(14, i.getPalletSap());
            cs.setString(15, i.getPalletWms());
            cs.setString(16, i.getAdjustment());
            cs.setInt(17, i.getCreationUserId());
            cs.registerOutParameter(18, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(18);
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

    public ResponseModel createAll(String coutingType, int coutingId, int creationUserId,ArrayList<InvSerialEntity> list) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        File directory = new File(this.en.getProperty("inventario.proceso.url"));
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(directory + "\\Seriales.txt"));
            for (InvSerialEntity i:list) {
                bw.write("1");
                bw.write("\t");
                bw.write(String.valueOf(coutingId));
                bw.write("\t");
                bw.write(i.getSerial());
                bw.write("\t");
                bw.write(i.getMac());
                bw.write("\t");
                bw.write(i.getSapCode()==null?"":i.getSapCode());
                bw.write("\t");
                bw.write(i.getSapCodeSap()==null?"":i.getSapCodeSap());
                bw.write("\t");
                bw.write(i.getSapCodeWms()==null?"":i.getSapCodeWms());
                bw.write("\t");
                bw.write(i.getSapCodeBase()==null?"":i.getSapCodeBase());
                bw.write("\t");
                bw.write(i.getStatus()==null?"":i.getStatus());
                bw.write("\t");
                bw.write(i.getStatusSap()==null?"":i.getStatusSap());
                bw.write("\t");
                bw.write(i.getStatusWms()==null?"":i.getStatusWms());
                bw.write("\t");
                bw.write(i.getStatusBase()==null?"":i.getStatusBase());
                bw.write("\t");
                bw.write(i.getPallet()==null?"":i.getPallet());
                bw.write("\t");
                bw.write(i.getPalletSap()==null?"":i.getPalletSap());
                bw.write("\t");
                bw.write(i.getPalletWms()==null?"":i.getPalletWms());
                bw.write("\t");
                bw.write("0");
                bw.write("\t");
                bw.write(format.format(new Date()));
                bw.write("\t");
                bw.write(String.valueOf(creationUserId));
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvSerialCreateAll(?,?)}");
            cs.setString(1, coutingType);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(2);
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

    public ResponseModel delete(String coutingType,int serialId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvSerialDelete(?,?)}");
            cs.setString(1, coutingType);
            cs.setInt(2, serialId);
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

    public ResponseModel list(int coutingId, String coutingType) {
        ArrayList<InvSerialEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvSerialList(?,?)}");
            cs.setInt(1, coutingId);
            cs.setString(2, coutingType);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvSerialEntity i = new InvSerialEntity();
                i.setId(rs.getInt("id"));
                i.setCoutingId(rs.getInt("coutingId"));
                i.setSerial(rs.getString("serial"));
                i.setMac(rs.getString("mac"));
                i.setSapCode(rs.getString("sapCode"));
                i.setSapCodeSap(rs.getString("sapCodeSap"));
                i.setSapCodeWms(rs.getString("sapCodeWms"));
                i.setSapCodeBase(rs.getString("sapCodeBase"));
                i.setStatus(rs.getString("status"));
                i.setStatusSap(rs.getString("statusSap"));
                i.setStatusWms(rs.getString("statusWms"));
                i.setStatusBase(rs.getString("statusBase"));
                i.setPallet(rs.getString("pallet"));
                i.setPalletSap(rs.getString("palletSap"));
                i.setPalletWms(rs.getString("palletWms"));
                i.setAdjustment(rs.getString("adjustment"));
                i.setCreationDate(rs.getString("creationDate"));
                i.setCreationUserId(rs.getInt("creationUserId"));
                i.setCreationUser(rs.getString("creationUser"));
                list.add(i);
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel findSap(String serial, String customer) {
        InvCyclicSerialModel i = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvSerialFindSap(?,?)}");
            cs.setString(1, serial);
            cs.setString(2, customer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvCyclicSerialModel();
                i.setSerial(rs.getString("serial"));
                i.setSapCode(rs.getString("sapCode"));
                i.setPallet(rs.getString("pallet"));
                i.setStatus(rs.getString("status"));
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK_SAP", i, 200);
    }

    public ResponseModel findWms(String serial, String customer) {
        InvCyclicSerialModel i = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvSerialFindWms(?,?)}");
            cs.setString(1, serial);
            cs.setString(2, customer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvCyclicSerialModel();
                i.setSerial(rs.getString("serial"));
                i.setSapCode(rs.getString("sapCode"));
                i.setPallet(rs.getString("pallet"));
                i.setStatus(rs.getString("status"));
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK_WMS", i, 200);
    }

    public ResponseModel find(int coutingId, String coutingType, String serial) {
        InvSerialEntity i = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvSerialFind(?,?,?)}");
            cs.setInt(1, coutingId);
            cs.setString(2, coutingType);
            cs.setString(3, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvSerialEntity();
                i.setSerial(rs.getString("serial"));
                i.setPallet(rs.getString("pallet"));
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", i, 200);
    }
}
