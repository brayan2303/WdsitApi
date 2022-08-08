package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DatBaseEntity implements Serializable {

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getTipoBase() {
        return tipoBase;
    }

    public void setTipoBase(String tipoBase) {
        this.tipoBase = tipoBase;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getPhone1() {
        return phone1;
    }

    public void setPhone1(int phone1) {
        this.phone1 = phone1;
    }

    public int getPhone2() {
        return phone2;
    }

    public void setPhone2(int phone2) {
        this.phone2 = phone2;
    }

    public int getPhone3() {
        return phone3;
    }

    public void setPhone3(int phone3) {
        this.phone3 = phone3;
    }

    public int getPhone4() {
        return phone4;
    }

    public void setPhone4(int phone4) {
        this.phone4 = phone4;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    public String getEmail4() {
        return email4;
    }

    public void setEmail4(String email4) {
        this.email4 = email4;
    }

    public String getInterfaz() {
        return interfaz;
    }

    public void setInterfaz(String interfaz) {
        this.interfaz = interfaz;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getAreaOperativa() {
        return areaOperativa;
    }

    public void setAreaOperativa(String areaOperativa) {
        this.areaOperativa = areaOperativa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getSerialEquipo() {
        return serialEquipo;
    }

    public void setSerialEquipo(String serialEquipo) {
        this.serialEquipo = serialEquipo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getSubMotivoRetiro() {
        return subMotivoRetiro;
    }

    public void setSubMotivoRetiro(String subMotivoRetiro) {
        this.subMotivoRetiro = subMotivoRetiro;
    }

    public String getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(String unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public String getFechaIngresoPedido() {
        return fechaIngresoPedido;
    }

    public void setFechaIngresoPedido(String fechaIngresoPedido) {
        this.fechaIngresoPedido = fechaIngresoPedido;
    }

    public String getFechaCitaPanel() {
        return fechaCitaPanel;
    }

    public void setFechaCitaPanel(String fechaCitaPanel) {
        this.fechaCitaPanel = fechaCitaPanel;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getFechaRealRet() {
        return fechaRealRet;
    }

    public void setFechaRealRet(String fechaRealRet) {
        this.fechaRealRet = fechaRealRet;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getMacroMotivo() {
        return macroMotivo;
    }

    public void setMacroMotivo(String macroMotivo) {
        this.macroMotivo = macroMotivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaEnvioIqe() {
        return fechaEnvioIqe;
    }

    public void setFechaEnvioIqe(String fechaEnvioIqe) {
        this.fechaEnvioIqe = fechaEnvioIqe;
    }

    public String getEstadoOss() {
        return estadoOss;
    }

    public void setEstadoOss(String estadoOss) {
        this.estadoOss = estadoOss;
    }

    public String getEnviadoA() {
        return enviadoA;
    }

    public void setEnviadoA(String enviadoA) {
        this.enviadoA = enviadoA;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }
    @Id
    private int customerId;
    private String tipoBase;
    private String pedido;
    private String identificador;
    private String origen;
    private int clienteId;
    private int phone1;
    private int phone2;
    private int phone3;
    private int phone4;
    private String email1;
    private String email2;
    private String email3;
    private String email4;
    private String interfaz;
    private String nombreCliente;
    private String areaOperativa;
    private String direccion;
    private String barrio;
    private String ciudad;
    private String departamento;
    private String marca;
    private String referencia;
    private int idEquipo;
    private String serialEquipo;
    private String producto;
    private String tecnologia;
    private String subMotivoRetiro;
    private String unidadNegocio;
    private String fechaIngresoPedido;
    private String fechaCitaPanel;
    private String horaCita;
    private String fechaRealRet;
    private int mes;
    private String macroMotivo;
    private String tipo;
    private String fechaEnvioIqe;
    private String estadoOss;
    private String enviadoA;
    private String regional;
    
}
