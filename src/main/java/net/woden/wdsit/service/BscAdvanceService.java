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
import net.woden.wdsit.entity.BscAdvanceEntity;
import net.woden.wdsit.model.BscAdvanceFileModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BscAdvanceService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(BscAdvanceEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscAdvanceCreate(?,?,?,?)}");
            cs.setString(1, b.getDescription());
            cs.setInt(2, b.getActivityId());
            cs.setInt(3, b.getCreationUserId());
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

    public ResponseModel update(BscAdvanceEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscAdvanceUpdate(?,?,?,?,?)}");
            cs.setInt(1, b.getId());
            cs.setString(2, b.getDescription());
            cs.setInt(3, b.getActivityId());
            cs.setInt(4, b.getCreationUserId());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(5);
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

    public ResponseModel delete(int advanceId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscAdvanceDelete(?)}");
            cs.setInt(1, advanceId);
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

    public ResponseModel list(int activityId) {
        ArrayList<BscAdvanceEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscAdvanceList(?)}");
            cs.setInt(1, activityId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                BscAdvanceEntity b = new BscAdvanceEntity();
                b.setId(rs.getInt("id"));
                b.setDescription(rs.getString("description"));
                b.setActivityId(rs.getInt("activityId"));
                b.setActivity(rs.getString("activity"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setCreationUserId(rs.getInt("creationUserId"));
                b.setCreationUser(rs.getString("creationUser"));
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

    public ResponseModel loadFile(int activityId, int advanceId, MultipartFile file) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("balanceScoreCard.avances.url") + "\\" + activityId);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        File directory2 = new File(this.en.getProperty("balanceScoreCard.avances.url") + "\\" + activityId + "\\" + advanceId);
        if (!directory2.isDirectory()) {
            directory2.mkdir();
        }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", load, 200);
    }

    public ResponseModel listFile(int activityId) {
        ArrayList<BscAdvanceFileModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("balanceScoreCard.avances.url") + "\\" + activityId);
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (String f1 : directory.list()) {
                File subDirectory = new File(directory + "/" + f1);
                for (String f2 : subDirectory.list()) {
                    File file = new File(subDirectory + "/" + f2);
                    try {
                        fis = new FileInputStream(file);
                        byte[] bytes = new byte[(int) file.length()];
                        fis.read(bytes);
                        fis.close();
                        BscAdvanceFileModel b = new BscAdvanceFileModel();
                        b.setAdvanceId(Integer.valueOf(f1));
                        b.setName(f2.split("\\.", 2)[0]);
                        b.setFile(bytes);
                        b.setType(f2.split("\\.", 2)[1]);
                        list.add(b);
                    } catch (FileNotFoundException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    } catch (IOException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    }
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel deleteFile(int activityId, int advanceId, String fileName) {
        int deletes = 0;
        File directory = new File(this.en.getProperty("balanceScoreCard.avances.url") + "\\" + activityId + "\\" + advanceId);

        for (String f : directory.list()) {
            if (f.split("\\.",2)[0].equals(fileName)) {
                File file = new File(directory + "/" + f);
                if (file.delete()) {
                    deletes = 1;
                }
                break;
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }

    public ResponseModel deleteFileItem(int activityId, int itemId) {
        int deletes = 0;
        File directory = new File(this.en.getProperty("balanceScoreCard.avances.url") + "\\" + activityId + "\\" + itemId);
        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                f.delete();
            }
            if(directory.delete()){
                deletes=1;
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }
    public ResponseModel deleteFileActivity(int activityId) {
        int deletes = 0;
        File directory = new File(this.en.getProperty("balanceScoreCard.avances.url") + "\\" + activityId);
        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                for(File fs:f.listFiles()){
                    fs.delete();
                }
                f.delete();
            }
            if(directory.delete()){
                deletes=1;
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }
}
