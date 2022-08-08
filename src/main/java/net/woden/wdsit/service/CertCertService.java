/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import net.woden.wdsit.connection.DataSourcConnectionIntegration;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaTigoConnection;
import net.woden.wdsit.entity.CertCertEntity;
import net.woden.wdsit.model.CertCertRouteModel;
import net.woden.wdsit.model.CertCertUserModel;
import net.woden.wdsit.model.EP5_HU1Model;
import net.woden.wdsit.model.EP8_HU1Model;
import net.woden.wdsit.model.EP8_HU3Model;
import net.woden.wdsit.model.EP8_HU5Model;
import net.woden.wdsit.model.EP8_HU7Model;
import net.woden.wdsit.model.FTPSendMailModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

/**
 *
 * @author m.pulido
 */
@Service
public class CertCertService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private DataSourceSapColombiaTigoConnection dsc;
    @Autowired
    private DataSourcConnectionIntegration dsi;

    @Autowired
    private Environment en;
    @Autowired
    private EncryptUtility eu;
    @Autowired
    private GenPersonService genPersonS;

    /**
     * Crear un nuevo aÃ±o
     *
     * @param c de CertCertEntity c
     * @return Confirmacion de Repositorio
     */
    public ResponseModel create(CertCertEntity c) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertCertCreate(?,?,?)}");
            cs.setString(1, c.getName());
            cs.setInt(2, c.getPorcentaje());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(3);
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

    public ResponseModel update(CertCertEntity c) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertCertUpdate(?,?,?,?,?)}");
            cs.setInt(1, c.getId());
            cs.setString(2, c.getName());
            cs.setInt(3, c.getPorcentaje());
            cs.setBoolean(4, c.isActive());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(5);
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

    public ResponseModel delete(int certCertId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertCertDelete(?)}");
            cs.setInt(1, certCertId);
            deletes = cs.executeUpdate();
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

    public ResponseModel list() {
        ArrayList<CertCertEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertCertList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                CertCertEntity b = new CertCertEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setPorcentaje(rs.getInt("porcentaje"));
                b.setActive(rs.getBoolean("active"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setModificationDate(rs.getString("modificationDate"));
                list.add(b);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel findById(int certCertId) {
        CertCertEntity b = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertCertFindById(?)}");
            cs.setInt(1, certCertId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                b = new CertCertEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setPorcentaje(rs.getInt("porcentaje"));
                b.setActive(rs.getBoolean("active"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setModificationDate(rs.getString("modificationDate"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", b, 200);
    }

    public ResponseModel findAll(int certPersonId) {
        ArrayList<CertCertEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertCertPersonFindAll(?)}");
            cs.setInt(1, certPersonId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                CertCertEntity b = new CertCertEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setPorcentaje(rs.getInt("porcentaje"));
                b.setActive(rs.getBoolean("active"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setModificationDate(rs.getString("modificationDate"));
                list.add(b);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel findAllByPersonId(int certPersonId) {
        ArrayList<CertCertEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertCertPersonFindAllById(?)}");
            cs.setInt(1, certPersonId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                CertCertEntity b = new CertCertEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setPorcentaje(rs.getInt("porcentaje"));
                b.setActive(rs.getBoolean("active"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setModificationDate(rs.getString("modificationDate"));
                list.add(b);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    /**
     * Conexion SFTP --> Proximo parametro RUTA DEL DOCUMENTO
     *
     * @return
     * @throws JSchException
     * @throws IllegalAccessException
     * @throws SftpException
     */
        public ResponseModel FTPTIGO() throws JSchException, IllegalAccessException, SftpException, IOException {
        
        //Cargando archivos
        this.archivos();
        
        //Inicializar la conexion FTP
        JSch jsch = new JSch();
        ChannelSftp sftp = null;
        InputStream inputStream = null;
        Connection cn = this.ds.openConnection();
        String timeStamp = "";
        String nameFiles = "";
        int destinario = 336;
        int listDestinatario = 1879;
        /**
         * Obtener la fecha del dia anterior, es mas sencillo por SQL ya que
         * facilita la obtencion de esta.
         */
        try {
            CallableStatement cs = cn.prepareCall("{call sp_FtpGetDate()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                timeStamp = rs.getString("Fecha");
            }
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }

        try {
            //Datos de conexion
            String server = this.en.getProperty("tigo.ftp.cert.server");
            int port = Integer.parseInt(this.en.getProperty("tigo.ftp.cert.port"));
            String user = this.en.getProperty("tigo.ftp.cert.user");
            String paswd = this.eu.decode(this.en.getProperty("tigo.ftp.cert.paswd"));
            String document = this.en.getProperty("tigo.ftp.cert.document");
            //Datos de carpeta
            String folder = this.en.getProperty("tigo.ftp.cert.folder");

            Session sessions;
            sessions = jsch.getSession(user, server, port);
            sessions.setConfig("PreferredAuthentications", "password");
            sessions.setConfig("StrictHostKeyChecking", "no");
            sessions.setPassword(paswd);
            sessions.connect();
            Channel channels = sessions.openChannel("sftp");
            sftp = (ChannelSftp) channels;
            sftp.connect();
            sftp.cd(folder);

            try {
                sftp.mkdir(timeStamp);
                sftp.cd(timeStamp);
            } catch (Exception ex) {
                sftp.cd(timeStamp);
            }
            //Obtencion del archivo
            File directory = new File(document);

            if (directory.isDirectory()) {
                for (File f : directory.listFiles()) {
                    inputStream = new FileInputStream(f);
                    String name = f.getName();
                    String nameCount = f.getName();
                    if (name.equals("REPORTE DE AGENTES EXTERNOS.xlsx") == true || name.equals("REPORTES DE AGENTES EXTERNOS.xlsx") == true) {
                        name = "EP3_HU1_Identificar_agendas_pactadas_por_call_center_Tigo.xlsx";
                    } else if (name.equals("REPORTE DE TAREAS.xlsx") == true || name.equals("REPORTES DE TAREAS.xlsx") == true) {
                        name = "EP6_HU1_Identificar_gestion_trabajo_campo_proveedor_principal.xlsx";
                    } else if (name.equals("REPORTE DE SERIALES RECUPERADOS.xlsx") == true || name.equals("REPORTES DE SERIALES RECUPERADOS.xlsx") == true) {
                        name = "EP7_HU1_Identificar_dispositivos_recogidos_proveedor_principal.xlsx";
                    }
                    sftp.put(inputStream, name);
                    inputStream.close();
                    FileReader fr = new FileReader(this.en.getProperty("tigo.ftp.cert.document")+ "\\"+nameCount);
                    BufferedReader bf = new BufferedReader(fr);
                    long lNumeroLineas = 0;
                    while ((nameCount = bf.readLine()) != null) {
                        lNumeroLineas++;
                    }
                    fr.close();
                    nameFiles = nameFiles + "* " + name + " registros " + lNumeroLineas + "\n";

                }
                sftp.disconnect();
                inputStream.close();
                
            }
                

        } catch (FileNotFoundException ex) {
            inputStream.close();
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } catch (IOException ex) {
            sftp.disconnect();
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        
        //Generacion de archivos de log (Fecha -> Archivos)
        File directorio = new File(this.en.getProperty("ftpTigo.service.url.bakcup")+"\\"+ timeStamp);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
        
        File directory = new File(this.en.getProperty("ftpTigo.service.url"));
        
        List<String> lista = new ArrayList();

        if (directory.isDirectory()) {
                for (File f : directory.listFiles()) {
                    
                     if(f.renameTo(new File(this.en.getProperty("ftpTigo.service.url.bakcup")+"\\"+ timeStamp+"\\"+f.getName()))){
                        f.createNewFile();
                        lista.add(f.getName());
                         System.out.println("Filed moved successfully");
                     }else{
                          System.out.println("Failed to moved the file");
                     }
                }
        }
        
        for (int i = 0; i < lista.size(); i++) {
            File a = new File(this.en.getProperty("ftpTigo.service.url")+"\\"+ lista.get(i));
            a.delete();
        }

        return new ResponseModel(getClass().getSimpleName(), "OK", null, 200);

    }

    /**
     * Generacion de documento
     */
    public ResponseModel getCert(int yearId, int certId, int periodicityId, int monthId, int userId) {

        CertCertRouteModel b = null;
        Connection cn = this.ds.openConnection();
        int inserts = 0;

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertCertFindRouteCert(?,?,?,?,?)}");
            cs.setInt(1, yearId);
            cs.setInt(2, certId);
            cs.setInt(3, periodicityId);
            cs.setInt(4, monthId);
            cs.setInt(5, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                b = new CertCertRouteModel();
                b.setYearId(rs.getInt("yearId"));
                b.setYear(rs.getString("year"));
                b.setCertificateId(rs.getInt("certificadoId"));
                b.setCertificate(rs.getString("certificado"));
                b.setPorcentajeCertificado(rs.getInt("PorcentajeCertificado"));
                b.setPeriodicityId(rs.getInt("periodicityId"));
                b.setPeriodicity(rs.getString("periodicity"));
                b.setMonthId(rs.getInt("monthId"));
                b.setMonth(rs.getString("month"));
                b.setUserId(rs.getInt("userId"));
                b.setUser(rs.getString("usuario"));
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

        //Registro de busqueda de certificado
        try {
            cn = this.ds.openConnection();
            CallableStatement cs = cn.prepareCall("{call sp_CertCertSearchHistoryCreate(?,?,?,?,?,?)}");
            cs.setInt(1, yearId);
            cs.setInt(2, certId);
            cs.setInt(3, periodicityId);
            cs.setInt(4, monthId);
            cs.setInt(5, userId);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(6);
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

        //Obtencion del archivo
        File directory = new File(this.en.getProperty("certificado.url") + "\\" + b.getYear() + "\\" + b.getCertificate() + "\\" + b.getPorcentajeCertificado() + "\\" + b.getPeriodicity() + "\\" + b.getMonth() + "\\" + b.getUser());
        FileInputStream fis;
        CertCertUserModel p = null;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    p = new CertCertUserModel();
                    p.setName(b.getUser());
                    p.setFile(bytes);
                    p.setType(f.getName().split("\\.", 2)[1]);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", p, 200);
    }
    
    public void archivos() throws IOException {
        
        this.EP8_HU1();
        this.EP8_HU5();
        this.EP8_HU7();
        this.EP8_HU3();
        this.EP5_HU1();
        this.WFSM();
    }
    
    public void EP8_HU1() throws IOException{
        List<EP8_HU1Model>EP8_HU1M=new ArrayList();
        Connection cn = this.dsc.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call wdn.pa_InformeIngresoIT(?,?,?)}");
            cs.setString(1,"");
            cs.setString(2,"");
            cs.setString(3,"");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                EP8_HU1Model b=new EP8_HU1Model();
                b.setSerial(rs.getString("serial"));
                b.setMac(rs.getString("mac"));
                b.setCodigoSap(rs.getString("codigoSap"));
                b.setDescripcion(rs.getString("descripcion"));
                b.setTramite(rs.getString("tramite"));
                b.setPedido(rs.getString("pedido"));
                b.setTipologia(rs.getString("tipologia"));
                b.setPallet(rs.getString("pallet"));
                b.setTipoOrigen(rs.getString("tipoOrigen"));
                b.setOrigen(rs.getString("origen"));
                b.setFalla(rs.getString("falla"));
                b.setFechaIngreso(rs.getString("fechaIngreso"));
                b.setUsuario(rs.getString("usuario"));
                b.setCiudad(rs.getString("ciudad"));
                EP8_HU1M.add(b);
            }
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        List<List<String>> rows = new ArrayList();
        for (int i = 0; i < EP8_HU1M.size(); i++) {
            List<String> cargar = new  ArrayList<String>();
            cargar.add(EP8_HU1M.get(i).getSerial());
            cargar.add(EP8_HU1M.get(i).getMac());
            cargar.add(EP8_HU1M.get(i).getCodigoSap());
            cargar.add(EP8_HU1M.get(i).getDescripcion());
            cargar.add(EP8_HU1M.get(i).getTramite());
            cargar.add(EP8_HU1M.get(i).getPedido());
            cargar.add(EP8_HU1M.get(i).getTipologia());
            cargar.add(EP8_HU1M.get(i).getPallet());
            cargar.add(EP8_HU1M.get(i).getTipoOrigen());
            cargar.add(EP8_HU1M.get(i).getOrigen());
            cargar.add(EP8_HU1M.get(i).getFalla());
            cargar.add(EP8_HU1M.get(i).getFechaIngreso());
            cargar.add(EP8_HU1M.get(i).getUsuario());
            cargar.add(EP8_HU1M.get(i).getCiudad());
            rows.add(cargar);
        }
        
        FileWriter csvWriter = new FileWriter(this.en.getProperty("ftpTigo.service.url") + "\\" +"EP8_HU1_Identificar_dispositivos_ingresados_proveedor_principal.csv");
        csvWriter.append("Serial");
        csvWriter.append("\\");
        csvWriter.append("Mac");
        csvWriter.append("\\");
        csvWriter.append("CodigoSap");
        csvWriter.append("\\");
        csvWriter.append("Descripcion");
        csvWriter.append("\\");
        csvWriter.append("Tramite");
        csvWriter.append("\\");
        csvWriter.append("Pedido");
        csvWriter.append("\\");
        csvWriter.append("Tipologia");
        csvWriter.append("\\");
        csvWriter.append("Pallet");
        csvWriter.append("\\");
        csvWriter.append("TipoOrigen");
        csvWriter.append("\\");
        csvWriter.append("Origen");
        csvWriter.append("\\");
        csvWriter.append("Falla");
        csvWriter.append("\\");
        csvWriter.append("FechaIngreso");
        csvWriter.append("\\");
        csvWriter.append("Usuario");
        csvWriter.append("\\");
        csvWriter.append("Ciudad");
        csvWriter.append("\\");
        csvWriter.append("\n");
        
        for (List<String> rowData : rows) {
            csvWriter.append(String.join("\\", rowData));
            csvWriter.append("\n");
        }
        
        csvWriter.flush();
        csvWriter.close();
    }
    
    
    public void EP8_HU3() throws IOException{
        List<EP8_HU3Model>EP8_HU3M=new ArrayList();
        Connection cn = this.dsc.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call wdn.pa_InformeWipIT(?,?,?)}");
            cs.setString(1,"");
            cs.setString(2,"");
            cs.setString(3,"");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                EP8_HU3Model b=new EP8_HU3Model();
                b.setCardCode(rs.getString("CardCode"));
                b.setCardName(rs.getString("CardName"));
                b.setSerial(rs.getString("serial"));
                b.setMac(rs.getString("mac"));
                b.setCodigoSap(rs.getString("codigoSap"));
                b.setDescripcion(rs.getString("descripcion"));
                b.setPallet(rs.getString("pallet"));
                b.setTipologia(rs.getString("tipologia"));
                b.setTipoOrigen(rs.getString("tipoOrigen"));
                b.setOrigen(rs.getString("origen"));
                b.setGarantiaFabricante(rs.getString("GarantiaFabricante"));
                b.setEstado(rs.getString("Estado"));
                b.setUbicacion(rs.getString("Ubicacion"));
                b.setCantidadIngreso(rs.getString("CantidadIngreso"));
                b.setFechaIngreso(rs.getString("fechaIngreso"));
                b.setFalla(rs.getString("fallas"));
                b.setCiudad(rs.getString("ciudad"));
                EP8_HU3M.add(b);
            }
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        List<List<String>> rows = new ArrayList();
        for (int i = 0; i < EP8_HU3M.size(); i++) {
            List<String> cargar = new  ArrayList<String>();
            cargar.add(EP8_HU3M.get(i).getCardCode());
            cargar.add(EP8_HU3M.get(i).getCardName());
            cargar.add(EP8_HU3M.get(i).getSerial());
            cargar.add(EP8_HU3M.get(i).getMac());
            cargar.add(EP8_HU3M.get(i).getCodigoSap());
            cargar.add(EP8_HU3M.get(i).getDescripcion());
            cargar.add(EP8_HU3M.get(i).getPallet());
            cargar.add(EP8_HU3M.get(i).getTipologia());
            cargar.add(EP8_HU3M.get(i).getTipoOrigen());
            cargar.add(EP8_HU3M.get(i).getOrigen());
            cargar.add(EP8_HU3M.get(i).getGarantiaFabricante());
            cargar.add(EP8_HU3M.get(i).getEstado());
            cargar.add(EP8_HU3M.get(i).getUbicacion());
            cargar.add(EP8_HU3M.get(i).getCantidadIngreso());
            cargar.add(EP8_HU3M.get(i).getFechaIngreso());
            cargar.add(EP8_HU3M.get(i).getFalla());
            cargar.add(EP8_HU3M.get(i).getCiudad());
            rows.add(cargar);
        }
        
        FileWriter csvWriter = new FileWriter(this.en.getProperty("ftpTigo.service.url") + "\\" +"EP8_HU3_Identificar_dispositivos_irreparables_proveedor_principal.csv");
        csvWriter.append("CardCode");
        csvWriter.append("\\");
        csvWriter.append("CardName");
        csvWriter.append("\\");
        csvWriter.append("Serial");
        csvWriter.append("\\");
        csvWriter.append("Mac");
        csvWriter.append("\\");
        csvWriter.append("CodigoSap");
        csvWriter.append("\\");
        csvWriter.append("Descripcion");
        csvWriter.append("\\");
        csvWriter.append("Pallet");
        csvWriter.append("\\");
        csvWriter.append("Tipologia");
        csvWriter.append("\\");
        csvWriter.append("TipoOrigen");
        csvWriter.append("\\");
        csvWriter.append("Origen");
        csvWriter.append("\\");
        csvWriter.append("GarantiaFabricante");
        csvWriter.append("\\");
        csvWriter.append("Estado");
        csvWriter.append("\\");
        csvWriter.append("Ubicacion");
        csvWriter.append("\\");
        csvWriter.append("CantidadIngreso");
        csvWriter.append("\\");
        csvWriter.append("FechaIngreso");
        csvWriter.append("\\");
        csvWriter.append("Fallas");
        csvWriter.append("\\");
        csvWriter.append("Ciudad");
        csvWriter.append("\\");
        csvWriter.append("\n");
        
        for (List<String> rowData : rows) {
            csvWriter.append(String.join("\\", rowData));
            csvWriter.append("\n");
        }
        
        csvWriter.flush();
        csvWriter.close();
    }
    
    public void EP8_HU5() throws IOException{
        List<EP8_HU5Model>EP8_HU5M=new ArrayList();
        Connection cn = this.dsc.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call wdn.pa_InformeEmpaqueDiarioIT(?,?,?)}");
            cs.setString(1,"");
            cs.setString(2,"");
            cs.setString(3,"");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                EP8_HU5Model b=new EP8_HU5Model();
                b.setSerial(rs.getString("serial"));
                b.setMac(rs.getString("mac"));
                b.setCodigoSap(rs.getString("codigoSap"));
                b.setDescripcion(rs.getString("descripcion"));
                b.setPallet(rs.getString("pallet"));
                b.setnCajaEmpaque(rs.getString("NCajaEmpaque"));
                b.setFallaDiagnostico(rs.getString("FallaDiagnostico"));
                b.setFallas(rs.getString("fallas"));
                b.setFechaEmpaque(rs.getString("FechaEmpaque"));
                b.setUsuario(rs.getString("Usuario"));
                b.setTipologia(rs.getString("tipologia"));
                b.setNivel(rs.getString("Nivel"));
                b.setCiudad(rs.getString("ciudad"));
                b.setSociedad(rs.getString("Sociedad"));
                EP8_HU5M.add(b);
            }
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        List<List<String>> rows = new ArrayList();
        for (int i = 0; i < EP8_HU5M.size(); i++) {
            List<String> cargar = new  ArrayList<String>();
            cargar.add(EP8_HU5M.get(i).getSerial());
            cargar.add(EP8_HU5M.get(i).getMac());
            cargar.add(EP8_HU5M.get(i).getCodigoSap());
            cargar.add(EP8_HU5M.get(i).getDescripcion());
            cargar.add(EP8_HU5M.get(i).getPallet());
            cargar.add(EP8_HU5M.get(i).getnCajaEmpaque());
            cargar.add(EP8_HU5M.get(i).getFallaDiagnostico());
            cargar.add(EP8_HU5M.get(i).getFallas());
            cargar.add(EP8_HU5M.get(i).getFechaEmpaque());
            cargar.add(EP8_HU5M.get(i).getUsuario());
            cargar.add(EP8_HU5M.get(i).getTipologia());
            cargar.add(EP8_HU5M.get(i).getNivel());
            cargar.add(EP8_HU5M.get(i).getCiudad());
            cargar.add(EP8_HU5M.get(i).getSociedad());
            rows.add(cargar);
        }
        
        FileWriter csvWriter = new FileWriter(this.en.getProperty("ftpTigo.service.url") + "\\" +"EP8_HU5_Identificar_dispositivos_rehabilitados_proveedor_principal.csv");
        csvWriter.append("Serial");
        csvWriter.append("\\");
        csvWriter.append("Mac");
        csvWriter.append("\\");
        csvWriter.append("CodigoSap");
        csvWriter.append("\\");
        csvWriter.append("Descripcion");
        csvWriter.append("\\");
        csvWriter.append("Pallet");
        csvWriter.append("\\");
        csvWriter.append("NCajaEmpaque");
        csvWriter.append("\\");
        csvWriter.append("FallaDiagnostico");
        csvWriter.append("\\");
        csvWriter.append("Fallas");
        csvWriter.append("\\");
        csvWriter.append("FechaEmpaque");
        csvWriter.append("\\");
        csvWriter.append("Usuario");
        csvWriter.append("\\");
        csvWriter.append("Tipologia");
        csvWriter.append("\\");
        csvWriter.append("Nivel");
        csvWriter.append("\\");
        csvWriter.append("Ciudad");
        csvWriter.append("\\");
        csvWriter.append("Sociedad");
        csvWriter.append("\\");
        csvWriter.append("\n");
        
        for (List<String> rowData : rows) {
            csvWriter.append(String.join("\\", rowData));
            csvWriter.append("\n");
        }
        
        csvWriter.flush();
        csvWriter.close();
    }
    
    public void EP8_HU7() throws IOException{
        List<EP8_HU7Model>EP8_HU7M=new ArrayList();
        Connection cn = this.dsc.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call wdn.pa_InformeRemisionIT(?,?,?)}");
            cs.setString(1,"");
            cs.setString(2,"");
            cs.setString(3,"");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                EP8_HU7Model b=new EP8_HU7Model();
                b.setId(rs.getString("id"));
                b.setNumeroRemision(rs.getString("NumeroRemision"));
                b.setNombreCliente(rs.getString("NombreCliente"));
                b.setAlmacenRemision(rs.getString("AlmacenRemision"));
                b.setFechaRemision(rs.getString("FechaRemision"));
                b.setTipoContrato(rs.getString("TipoContrato"));
                b.setEstado(rs.getString("Estado"));
                b.setNumeroGuia(rs.getString("NumeroGuia"));
                b.setNumPedidoCliente(rs.getString("NumPedidoCliente"));
                b.setPlacaVehiculo(rs.getString("PlacaVehiculo"));
                b.setPeriodoFacturacion(rs.getString("PeriodoFacturacion"));
                b.setUsuarioCreacion(rs.getString("UsuarioCreacion"));
                b.setSerial(rs.getString("serial"));
                b.setMac(rs.getString("mac"));
                b.setCodigoSap(rs.getString("codigoSap"));
                b.setDescripcion(rs.getString("descripcion"));
                b.setPallet(rs.getString("pallet"));
                b.setCiudad(rs.getString("ciudad"));
                b.setSociedad(rs.getString("Sociedad"));
                EP8_HU7M.add(b);
            }
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        List<List<String>> rows = new ArrayList();
        for (int i = 0; i < EP8_HU7M.size(); i++) {
            List<String> cargar = new  ArrayList<String>();
            cargar.add(EP8_HU7M.get(i).getId());
            cargar.add(EP8_HU7M.get(i).getNumeroRemision());
            cargar.add(EP8_HU7M.get(i).getNombreCliente());
            cargar.add(EP8_HU7M.get(i).getAlmacenRemision());
            cargar.add(EP8_HU7M.get(i).getFechaRemision());
            cargar.add(EP8_HU7M.get(i).getTipoContrato());
            cargar.add(EP8_HU7M.get(i).getEstado());
            cargar.add(EP8_HU7M.get(i).getNumeroGuia());
            cargar.add(EP8_HU7M.get(i).getNumPedidoCliente());
            cargar.add(EP8_HU7M.get(i).getPlacaVehiculo());
            cargar.add(EP8_HU7M.get(i).getPeriodoFacturacion());
            cargar.add(EP8_HU7M.get(i).getUsuarioCreacion());
            cargar.add(EP8_HU7M.get(i).getSerial());
            cargar.add(EP8_HU7M.get(i).getMac());
            cargar.add(EP8_HU7M.get(i).getCodigoSap());
            cargar.add(EP8_HU7M.get(i).getDescripcion());
            cargar.add(EP8_HU7M.get(i).getPallet());
            cargar.add(EP8_HU7M.get(i).getCiudad());
            cargar.add(EP8_HU7M.get(i).getSociedad());
            rows.add(cargar);
        }
        
        FileWriter csvWriter = new FileWriter(this.en.getProperty("ftpTigo.service.url") + "\\" +"EP8_HU7_Identificar_dispositivos_despachados_proveedor_principal.csv");
        csvWriter.append("Id");
        csvWriter.append("\\");
        csvWriter.append("NumeroRemision");
        csvWriter.append("\\");
        csvWriter.append("NombreCliente");
        csvWriter.append("\\");
        csvWriter.append("AlmacenRemision");
        csvWriter.append("\\");
        csvWriter.append("FechaRemision");
        csvWriter.append("\\");
        csvWriter.append("TipoContrato");
        csvWriter.append("\\");
        csvWriter.append("Estado");
        csvWriter.append("\\");
        csvWriter.append("NumeroGuia");
        csvWriter.append("\\");
        csvWriter.append("NumPedidoCliente");
        csvWriter.append("\\");
        csvWriter.append("PlacaVehiculo");
        csvWriter.append("\\");
        csvWriter.append("PeriodoFacturacion");
        csvWriter.append("\\");
        csvWriter.append("UsuarioCreacion");
        csvWriter.append("\\");
        csvWriter.append("Serial");
        csvWriter.append("\\");
        csvWriter.append("Mac");
        csvWriter.append("\\");
        csvWriter.append("CodigoSap");
        csvWriter.append("\\");
        csvWriter.append("Descripcion");
        csvWriter.append("\\");
        csvWriter.append("Pallet");
        csvWriter.append("\\");
        csvWriter.append("Ciudad");
        csvWriter.append("\\");
        csvWriter.append("Sociedad");
        csvWriter.append("\\");
        csvWriter.append("\n");
        
        for (List<String> rowData : rows) {
            csvWriter.append(String.join("\\", rowData));
            csvWriter.append("\n");
        }
        
        csvWriter.flush();
        csvWriter.close();
    }
    
    public void EP5_HU1() throws IOException{
        List<EP5_HU1Model>EP5_HU1M=new ArrayList();
        Connection cn = this.dsi.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call ftpTIGO()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                EP5_HU1Model b=new EP5_HU1Model();
                b.setIdCall(rs.getString("IdCall"));
                b.setAgentName(rs.getString("AgentName"));
                b.setCliente(rs.getString("Cliente"));
                b.setFecha(rs.getString("Fecha"));
                b.setHora(rs.getString("Hora"));
                b.setDescription(rs.getString("Description"));
                b.setComments(rs.getString("Comments"));
                b.setTypeCall(rs.getString("typeCall"));
                b.setTelephone(rs.getString("Telephone"));
                b.setDuration(rs.getString("Duration"));
                b.setMedio(rs.getString("Medio"));
                b.setOpt_12(rs.getString("OPT12"));
                EP5_HU1M.add(b);
                System.out.println(EP5_HU1M.add(b));
            }
            cs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        List<List<String>> rows = new ArrayList();
        for (int i = 0; i < EP5_HU1M.size(); i++) {
            List<String> cargar = new  ArrayList<String>();
            cargar.add(EP5_HU1M.get(i).getIdCall());
            cargar.add(EP5_HU1M.get(i).getAgentName());
            cargar.add(EP5_HU1M.get(i).getCliente());
            cargar.add(EP5_HU1M.get(i).getFecha());
            cargar.add(EP5_HU1M.get(i).getHora());
            cargar.add(EP5_HU1M.get(i).getDescription());
            cargar.add(EP5_HU1M.get(i).getComments());
            cargar.add(EP5_HU1M.get(i).getTypeCall());
            cargar.add(EP5_HU1M.get(i).getTelephone());
            cargar.add(EP5_HU1M.get(i).getDuration());
            cargar.add(EP5_HU1M.get(i).getMedio());
            rows.add(cargar);
        }
        
        //Segunda Validacion
        cn = this.ds.openConnection();
        List<FTPSendMailModel> correo = new ArrayList();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_FTPMailSend()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                FTPSendMailModel b = new FTPSendMailModel();
                b.setCorreo(rs.getString("mail"));
                b.setFecha(rs.getString("fecha"));
                b.setHora(rs.getString("hora"));
                correo.add(b);
            }
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        for(int i = 0; i < correo.size(); i++) {
           
            for(int j = 0; j<EP5_HU1M.size(); j ++)
            {
                String correoEnvio = correo.get(i).getCorreo();
                String correoMaestra = EP5_HU1M.get(j).getOpt_12();
                if(correoEnvio.equals(correoMaestra) == true )
                {
                    if(rows.equals(correoEnvio) == false)
                    {
                        List<String> cargar = new  ArrayList<String>();
                        cargar.add("");
                        cargar.add("BOT MENSAJERIA");
                        cargar.add(EP5_HU1M.get(j).getCliente());
                        cargar.add(correo.get(i).getFecha());
                        cargar.add(correo.get(i).getHora());
                        cargar.add("ENVIADO");
                        cargar.add("ENVIADO");
                        cargar.add("out_pre");
                        cargar.add(EP5_HU1M.get(j).getTelephone());
                        cargar.add("0");
                        cargar.add("EMAIL");
                        rows.add(cargar);
                    }
                }
            }
        }
        
        
        FileWriter csvWriter = new FileWriter(this.en.getProperty("ftpTigo.service.url") + "\\" +"EP5_HU1_Identificar_gestion_contacto_proveedor_principal.csv");
        csvWriter.append("IdCall");
        csvWriter.append("\\");
        csvWriter.append("AgentName");
        csvWriter.append("\\");
        csvWriter.append("Cliente");
        csvWriter.append("\\");
        csvWriter.append("Fecha");
        csvWriter.append("\\");
        csvWriter.append("Hora");
        csvWriter.append("\\");
        csvWriter.append("Description");
        csvWriter.append("\\");
        csvWriter.append("Comments");
        csvWriter.append("\\");
        csvWriter.append("TypeCall");
        csvWriter.append("\\");
        csvWriter.append("Telephone");
        csvWriter.append("\\");
        csvWriter.append("Duration");
        csvWriter.append("\\");
        csvWriter.append("Medio");
        csvWriter.append("\n");
        
        for (List<String> rowData : rows) {
            csvWriter.append(String.join("\\", rowData));
            csvWriter.append("\n");
        }
        
        csvWriter.flush();
        csvWriter.close();
    }
    
    public void WFSM(){
        
        File directory = new File(this.en.getProperty("ftpTigo.service.url.wfsm"));
        
        if (directory.isDirectory()) {
                for (File f : directory.listFiles()) {
                    if(f.renameTo(new File(this.en.getProperty("ftpTigo.service.url")+"\\"+f.getName())))
                     {
                         f.delete();
                     }
                     else
                     {
                         System.out.println("Failed to move the file");
                     }
                }
                
        }
        
    }
}
