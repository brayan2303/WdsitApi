package net.woden.wdsit.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.FilSerialEntity;
import net.woden.wdsit.model.FilSerialModel;
import net.woden.wdsit.model.FileSerialSearchModel;
import net.woden.wdsit.model.PqrPqrsFileModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilSerialService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(FilSerialEntity f) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_FilSerialCreate(?,?,?,?,?)}");
            cs.setString(1, f.getSerial());
            cs.setInt(2, f.getCountryId());
            cs.setInt(3, f.getCustomerId());
            cs.setInt(4, f.getUserId());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(5);
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

    public ResponseModel update(FilSerialEntity f) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_FilSerialUpdate(?,?,?,?)}");
            cs.setInt(1, f.getId());
            cs.setString(2, f.getSerial());
            cs.setInt(3, f.getCustomerId());
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(4);
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

    public ResponseModel delete(int serialId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_FilSerialDelete(?)}");
            cs.setInt(1, serialId);
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

    public ResponseModel list(int countryId, int customerId) {
        ArrayList<FilSerialEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_FilSerialList(?,?)}");
            cs.setInt(1, countryId);
            cs.setInt(2, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                FilSerialEntity f = new FilSerialEntity();
                f.setId(rs.getInt("id"));
                f.setSerial(rs.getString("serial"));
                f.setCountryId(rs.getInt("countryId"));
                f.setCountry(rs.getString("country"));
                f.setCustomerId(rs.getInt("customerId"));
                f.setCustomer(rs.getString("customer"));
                f.setUserId(rs.getInt("userId"));
                f.setUser(rs.getString("user"));
                f.setCreationDate(rs.getString("creationDate").substring(0, 10));
                list.add(f);
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

    public ResponseModel loadFile(String serialId, String customer, String type, MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("archivosSap.seriales.url") + "\\" + customer);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        File directory2 = new File(this.en.getProperty("archivosSap.seriales.url") + "\\" + customer + "\\" + type);
        if (!directory2.isDirectory()) {
            directory2.mkdir();
        }
        File directory3 = new File(this.en.getProperty("archivosSap.seriales.url") + "\\" + customer + "\\" + type + "\\" + serialId);
        if (!directory3.isDirectory()) {
            directory3.mkdir();
        }
        for (MultipartFile file : files) {
            if (file.getOriginalFilename().split("\\.", 2)[1].equals("png") || file.getOriginalFilename().split("\\.", 2)[1].equals("jpg")) {
                try {
                    bi = ImageIO.read(file.getInputStream());
                    ImageIO.write(bi, file.getOriginalFilename().split("\\.", 2)[1], new File(directory3 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]));
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            } else {
                try {
                    File f = new File(directory3 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]);
                    f.createNewFile();
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(file.getBytes());
                    fos.close();
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", load, 200);
    }

    public ResponseModel listFile(String serial, String customer, String type) {
        ArrayList<PqrPqrsFileModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("archivosSap.seriales.url") + "\\" + customer + "\\" + type + "\\" + serial);
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    PqrPqrsFileModel p = new PqrPqrsFileModel();
                    p.setName(f.getName());
                    p.setFile(bytes);
                    p.setType(f.getName().split("\\.", 2)[1]);
                    list.add(p);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel deleteFile(String serialId, String customer, String type, String fileName) {
        int deletes = 0;
        File directory = new File(this.en.getProperty("archivosSap.seriales.url") + "\\" + customer + "\\" + type + "\\" + serialId);

        for (String f : directory.list()) {
            if (f.equals(fileName)) {
                File file = new File(directory + "/" + f);
                if (file.delete()) {
                    deletes = 1;
                }
                break;
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }

    public ResponseModel deleteFileBySerial(String customer, String serialId) {
        int deletes = 0;
        File directoryCustomer = new File(this.en.getProperty("archivosSap.seriales.url") + "\\" + customer);
        if (directoryCustomer.isDirectory()) {
            File directoryStart = new File(this.en.getProperty("archivosSap.seriales.url") + "\\" + customer + "\\INICIO");
            if (directoryStart.isDirectory()) {
                File directorySerial = new File(this.en.getProperty("archivosSap.seriales.url") + "\\" + customer + "\\INICIO\\" + serialId);
                if (directorySerial.isDirectory()) {
                    for (File f : directorySerial.listFiles()) {
                        f.delete();
                    }
                    directorySerial.delete();
                }
                directoryStart.delete();
            }
            File directoryEnd = new File(this.en.getProperty("archivosSap.seriales.url") + "\\" + customer + "\\FIN");
            if (directoryEnd.isDirectory()) {
                File directorySerial = new File(this.en.getProperty("archivosSap.seriales.url") + "\\" + customer + "\\FIN\\" + serialId);
                if (directorySerial.isDirectory()) {
                    for (File f : directorySerial.listFiles()) {
                        f.delete();
                    }
                    directorySerial.delete();
                }
                directoryEnd.delete();
            }
            directoryCustomer.delete();
            deletes = 1;
        } else {
            deletes = 1;
        }

        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }

    public ResponseModel serialStatus(String serial) {
        FilSerialModel f = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_FilSerialSearch(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                f = new FilSerialModel();
                f.setId(rs.getInt("id"));
                f.setSerial(rs.getString("serial"));
                f.setEstado(rs.getString("estado"));
                f.setCustomer(rs.getString("customer"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", f, 200);
    }
    
    public ResponseModel serialSearch(String serial) {
        FileSerialSearchModel f = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_fileSerialUpdateSearch(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                f = new FileSerialSearchModel();
                f.setId(rs.getInt("id"));
                f.setCardName(rs.getString("CardName"));
                f.setCardNameId(rs.getInt("cardNameId"));
                f.setSerial(rs.getString("Serial"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", f, 200);

    }
}
