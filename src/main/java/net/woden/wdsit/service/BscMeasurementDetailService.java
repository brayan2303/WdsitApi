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
import net.woden.wdsit.entity.BscMeasurementDetailEntity;
import net.woden.wdsit.model.BscMeasurementDetailFileModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BscMeasurementDetailService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(BscMeasurementDetailEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscMeasurementDetailCreate(?,?,?,?)}");
            cs.setInt(1, b.getMeasurementId());
            cs.setInt(2, b.getMonthId());
            cs.setDouble(3, b.getGoal());
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(4);
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

    public ResponseModel update(int id, String type, double value) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscMeasurementDetailUpdate(?,?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, type);
            cs.setDouble(3, value);
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

    public ResponseModel delete(int measurementDetailId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscMeasurementDetailDelete(?)}");
            cs.setInt(1, measurementDetailId);
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

    public ResponseModel openClose(int measurementDetailId, String status) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscMeasurementDetailOpenClose(?,?,?)}");
            cs.setInt(1, measurementDetailId);
            cs.setString(2, status);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(3);
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

    public ResponseModel list(int measurementId) {
        ArrayList<BscMeasurementDetailEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscMeasurementDetailList(?)}");
            cs.setInt(1, measurementId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                BscMeasurementDetailEntity b = new BscMeasurementDetailEntity();
                b.setId(rs.getInt("id"));
                b.setMeasurementId(rs.getInt("measurementId"));
                b.setMonthId(rs.getInt("monthId"));
                b.setMonth(rs.getString("month"));
                b.setGoal(rs.getDouble("goal"));
                b.setResult(rs.getDouble("result"));
                b.setCompliance(rs.getDouble("compliance"));
                b.setStatusId(rs.getInt("statusId"));
                b.setStatus(rs.getString("status"));
                list.add(b);
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

    public ResponseModel listClose(int measurementId) {
        ArrayList<BscMeasurementDetailEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscMeasurementDetailListClose(?)}");
            cs.setInt(1, measurementId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                BscMeasurementDetailEntity b = new BscMeasurementDetailEntity();
                b.setId(rs.getInt("id"));
                b.setMeasurementId(rs.getInt("measurementId"));
                b.setMonthId(rs.getInt("monthId"));
                b.setMonth(rs.getString("month"));
                b.setGoal(rs.getDouble("goal"));
                b.setResult(rs.getDouble("result"));
                b.setCompliance(rs.getDouble("compliance"));
                b.setStatusId(rs.getInt("statusId"));
                b.setStatus(rs.getString("status"));
                list.add(b);
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

    public ResponseModel loadFile(int measurementId, int measurementDetailId, MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("balanceScoreCard.mediciones.url") + "\\" + measurementId);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        File directory2 = new File(this.en.getProperty("balanceScoreCard.mediciones.url") + "\\" + measurementId + "\\" + measurementDetailId);
        if (!directory2.isDirectory()) {
            directory2.mkdir();
        }
        for (MultipartFile file : files) {
            if (file.getOriginalFilename().split("\\.", 2)[1].equals("png") || file.getOriginalFilename().split("\\.", 2)[1].equals("jpg")) {
                try {
                    bi = ImageIO.read(file.getInputStream());
                    ImageIO.write(bi, file.getOriginalFilename().split("\\.", 2)[1], new File(directory2 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]));
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            } else {
                try {
                    File f = new File(directory2 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]);
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

    public ResponseModel listFile(int measurementId, int measurementDetailId) {
        ArrayList<BscMeasurementDetailFileModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("balanceScoreCard.mediciones.url") + "\\" + measurementId + "\\" + measurementDetailId);
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    BscMeasurementDetailFileModel b = new BscMeasurementDetailFileModel();
                    b.setName(f.getName());
                    b.setFile(bytes);
                    b.setType(f.getName().split("\\.", 2)[1]);
                    list.add(b);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel deleteFile(int measurementId, int measurementDetailId, String fileName) {
        int deletes = 0;
        File directory = new File(this.en.getProperty("balanceScoreCard.mediciones.url") + "\\" + measurementId + "\\" + measurementDetailId);

        for (String f : directory.list()) {
            if (f.split("\\.", 2)[0].equals(fileName)) {
                File file = new File(directory + "/" + f);
                if (file.delete()) {
                    deletes = 1;
                }
                break;
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }

    public ResponseModel deleteFileMeasurement(int measurementId) {
        int deletes = 0;
        File directory = new File(this.en.getProperty("balanceScoreCard.mediciones.url") + "\\" + measurementId);
        if (directory.isDirectory()) {
            File directoryMeasurementDetail = new File(this.en.getProperty("balanceScoreCard.mediciones.url") + "\\" + measurementId);
            if (directoryMeasurementDetail.isDirectory()) {
                for (File f : directoryMeasurementDetail.listFiles()) {
                    f.delete();
                }
                directoryMeasurementDetail.delete();
            }
            if (directory.delete()) {
                deletes = 1;
            }
        } else {
            deletes = 1;
        }

        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }
}
