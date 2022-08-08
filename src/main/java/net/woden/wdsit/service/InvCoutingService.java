package net.woden.wdsit.service;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.InvCoutingEntity;
import net.woden.wdsit.entity.InvSerialEntity;
import net.woden.wdsit.model.ResponseModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class InvCoutingService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment e;

    public ResponseModel create(int cardId,InvCoutingEntity i) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingCreate(?,?,?,?,?,?)}");
            cs.setInt(1,i.getPalletId());
            cs.setInt(2,cardId);
            cs.setInt(3,i.getSampling());
            cs.setString(4,i.getType());
            cs.setInt(5,i.getCreationUserId());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(6);
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
    public ResponseModel update(InvCoutingEntity i) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingUpdate(?,?,?,?,?)}");
            cs.setInt(1,i.getId());
            cs.setInt(2,i.getSampling());
            cs.setString(3,i.getStatus());
            cs.setString(4,i.getType());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            updates=cs.getInt(5);
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
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingDelete(?)}");
            cs.setInt(1,id);
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
    public ResponseModel addQuantity(int coutingId,int quantity) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingAddQuantity(?,?,?)}");
            cs.setInt(1,coutingId);
            cs.setInt(2,quantity);
            cs.registerOutParameter(3, Types.INTEGER);
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
    public ResponseModel deleteQuantity(int coutingId) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingDeleteQuantity(?,?)}");
            cs.setInt(1,coutingId);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            updates=cs.getInt(2);
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
    public ResponseModel startEnd(int cyclicCoutingId,String status) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingStartEnd(?,?,?)}");
            cs.setInt(1,cyclicCoutingId);
            cs.setString(2,status);
            cs.registerOutParameter(3, Types.INTEGER);
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
    public ResponseModel openClose(int cyclicCoutingId,String status) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingOpenClose(?,?,?)}");
            cs.setInt(1,cyclicCoutingId);
            cs.setString(2,status);
            cs.registerOutParameter(3, Types.INTEGER);
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
    public ResponseModel findQuantity(int coutingId) {
        ArrayList<InvCoutingEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingFindQuantity(?)}");
            cs.setInt(1,coutingId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvCoutingEntity i=new InvCoutingEntity();
                i.setId(rs.getInt("id"));
                i.setPalletId(rs.getInt("palletId"));
                i.setSampling(rs.getInt("sampling"));
                i.setStatus(rs.getString("status"));
                i.setType(rs.getString("type"));
                i.setQuantity(rs.getInt("quantity"));
                i.setCreationDate(rs.getString("creationDate").substring(0, 16));
                i.setStartDate(rs.getString("startDate")!=null?rs.getString("startDate").substring(0, 16):"");
                i.setEndDate(rs.getString("endDate")!=null?rs.getString("endDate").substring(0, 16):"");
                i.setAssistant(rs.getString("assistant"));
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
    public ResponseModel validatePallet(int cyclicId,String pallet,int creationUserId) {
        ArrayList<InvSerialEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingValidatePallet(?,?,?)}");
            cs.setInt(1,cyclicId);
            cs.setString(2, pallet);
            cs.setInt(3,creationUserId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvSerialEntity i = new InvSerialEntity();
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
    public ResponseModel findAll(String customer,int palletId) {
        ArrayList<InvCoutingEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingFindAll(?,?)}");
            cs.setString(1,customer);
            cs.setInt(2, palletId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                InvCoutingEntity i=new InvCoutingEntity();
                i.setId(rs.getInt("id"));
                i.setCoutingType(rs.getString("coutingType"));
                i.setPalletId(rs.getInt("palletId"));
                i.setSampling(rs.getInt("sampling"));
                i.setTotal(rs.getInt("total"));
                i.setStatus(rs.getString("status"));
                i.setType(rs.getString("type"));
                i.setCreationDate(rs.getString("creationDate").substring(0, 16));
                i.setStartDate(rs.getString("startDate")!=null?rs.getString("startDate").substring(0, 16):"");
                i.setEndDate(rs.getString("endDate")!=null?rs.getString("endDate").substring(0, 16):"");
                i.setCreationUser(rs.getString("creationUser"));
                i.setAssistant(rs.getString("assistant"));
                i.setCyclic(rs.getString("cyclic"));
                i.setPallet(rs.getString("pallet"));
                i.setCardId(rs.getInt("cardId"));
                i.setCard(rs.getString("card"));
                i.setSapCode(rs.getString("sapCode"));
                i.setLocation(rs.getString("location"));
                i.setQuantity(rs.getInt("quantity"));
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
    public ResponseModel findPending(int cyclicPalletId) {
        int total=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingFindPending(?)}");
            cs.setInt(1,cyclicPalletId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
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
    public ResponseModel list(int customerId,int personId) {
        ArrayList<InvCoutingEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingList(?,?)}");
            cs.setInt(1, customerId);
            cs.setInt(2, personId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                InvCoutingEntity i=new InvCoutingEntity();
                i.setId(rs.getInt("id"));
                i.setCoutingType(rs.getString("coutingType"));
                i.setPalletId(rs.getInt("palletId"));
                i.setSampling(rs.getInt("sampling"));
                i.setStatus(rs.getString("status"));
                i.setType(rs.getString("type"));
                i.setCreationDate(rs.getString("creationDate").substring(0, 16));
                i.setStartDate(rs.getString("startDate")!=null?rs.getString("startDate").substring(0, 16):"");
                i.setEndDate(rs.getString("endDate")!=null?rs.getString("endDate").substring(0, 16):"");
                i.setCreationUser(rs.getString("creationUser"));
                i.setAssistant(rs.getString("assistant"));
                i.setCyclicId(rs.getInt("cyclicId"));
                i.setCyclic(rs.getString("cyclic"));
                i.setPallet(rs.getString("pallet"));
                i.setPalletType(rs.getString("palletType"));
                i.setCustomer(rs.getString("customer"));
                i.setSapCode(rs.getString("sapCode"));
                i.setLocation(rs.getString("location"));
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
    public ResponseModel findByPalletId(int palletId) {
        int quantity=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingFindByPalletId(?)}");
            cs.setInt(1,palletId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                quantity=rs.getInt("quantity");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", quantity, 200);
    }
    public ResponseModel total(int coutingId,String number) {
        int total=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingTotal(?,?)}");
            cs.setInt(1, coutingId);
            cs.setString(2,number);
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
    public ResponseModel income(int cyclicId,String pallet) {
        int income=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingIncome(?,?)}");
            cs.setInt(1, cyclicId);
            cs.setString(2, pallet);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                income=rs.getInt("income");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", income, 200);
    }
    public ResponseModel remaining(int coutingId,String number) {
        int remaining=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingRemaining(?,?)}");
            cs.setInt(1, coutingId);
            cs.setString(2,number);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                remaining=rs.getInt("remaining");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", remaining, 200);
    }
    public ResponseModel sapCode(int cyclicId) {
        ArrayList<String>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvCoutingSapCode(?)}");
            cs.setInt(1,cyclicId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                list.add(rs.getString("value"));
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
    public ResponseModel print(String customer,InvCoutingEntity i) {
        String bytes=null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fileName=null;
        File directory=new File(this.e.getProperty("inventario.conteos.url")+"\\"+customer);
        if(!directory.isDirectory()){
            directory.mkdir();
        }
        Map<String, Object> parameters = new HashMap();
        parameters.put("Cliente", customer);
        parameters.put("Posicion", i.getLocation());
        parameters.put("Fecha", format.format(new Date()));
        parameters.put("Pallet", i.getPallet());
        parameters.put("CodigoSap", i.getSapCode());
        parameters.put("Estado", i.getStatus());
        if(i.getCard().split("-",2)[1].substring(0,1).equals("P")){
            parameters.put("Conteo", "Primer Conteo");
            fileName="Primer Conteo";
        }else if(i.getCard().split("-",2)[1].substring(0,1).equals("S")){
            parameters.put("Conteo", "Segundo Conteo");
            fileName="Segundo Conteo";
        }else if(i.getCard().split("-",2)[1].substring(0,1).equals("T")){
            parameters.put("Conteo", "Tercer Conteo");
            fileName="Tercer Conteo";
        }
        parameters.put("Cantidad", i.getQuantity());
        parameters.put("Usuario", i.getAssistant());
        try {
            JasperReport report = JasperCompileManager.compileReport(this.e.getProperty("inventario.conteos.url") + "\\Conteo.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(print, directory + "\\" + i.getPallet()+"_"+fileName + ".pdf");
            File file = new File(directory + "\\" + i.getPallet()+"_"+fileName + ".pdf");
            bytes=Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
        } catch (JRException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } catch (IOException ex) {
            Logger.getLogger(InvPalletService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", bytes, 200);
    }
}
