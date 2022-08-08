package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SchClienteEntity implements Serializable {
    @Id
    private int id;
    private String nombre;
    private String sigla;
    private int paisId;
    private String pais;
    private String telefono;
    private String correo;
    private String pie1;
    private String pie2;
    private String chat;
    private String whatsapp;
    private String color;
    private boolean publicidad;
    private boolean politicaDato;
    private String urlPolitica;
    private String urlTyC;
    private boolean envioInformacion;
    private boolean envioNotificacion;
    private String tipoCampana;
    private String campana;
    private boolean activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getPaisId() {
        return paisId;
    }

    public void setPaisId(int paisId) {
        this.paisId = paisId;
    }
    
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPie1() {
        return pie1;
    }

    public void setPie1(String pie1) {
        this.pie1 = pie1;
    }

    public String getPie2() {
        return pie2;
    }

    public void setPie2(String pie2) {
        this.pie2 = pie2;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isPublicidad() {
        return publicidad;
    }

    public void setPublicidad(boolean publicidad) {
        this.publicidad = publicidad;
    }

    public boolean isPoliticaDato() {
        return politicaDato;
    }

    public void setPoliticaDato(boolean politicaDato) {
        this.politicaDato = politicaDato;
    }
    
    public String getUrlPolitica() {
        return urlPolitica;
    }

    public void setUrlPolitica(String urlPolitica) {
        this.urlPolitica = urlPolitica;
    }

    public String getUrlTyC() {
        return urlTyC;
    }

    public void setUrlTyC(String urlTyC) {
        this.urlTyC = urlTyC;
    }

    public boolean isEnvioInformacion() {
        return envioInformacion;
    }

    public void setEnvioInformacion(boolean envioInformacion) {
        this.envioInformacion = envioInformacion;
    }

    public boolean isEnvioNotificacion() {
        return envioNotificacion;
    }

    public void setEnvioNotificacion(boolean envioNotificacion) {
        this.envioNotificacion = envioNotificacion;
    }
    
    public String getTipoCampana() {
        return tipoCampana;
    }

    public void setTipoCampana(String tipoCampana) {
        this.tipoCampana = tipoCampana;
    }

    public String getCampana() {
        return campana;
    }

    public void setCampana(String campana) {
        this.campana = campana;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
