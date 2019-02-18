/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author ronal
 */
public class Software {
    private int codigo;
    private String statusSoft;
    private Date dataCadastro;
    private String descricaoSoftware;
    private String versaoSoft;
    private String observacaoSoft;
    private String usuarioInsert;
    private String dataInsert;
    private String horarioInsert;
    private String usuarioUp;
    private String dataUp;
    private String horarioUp;
    private String tipoServidor;
    private String tipoBanco;

    public Software() {
    }

    public Software(int codigo, String statusSoft, Date dataCadastro, String descricaoSoftware, String versaoSoft, String observacaoSoft, String usuarioInsert, String dataInsert, String horarioInsert, String usuarioUp, String dataUp, String horarioUp, String tipoServidor, String tipoBanco) {
        this.codigo = codigo;
        this.statusSoft = statusSoft;
        this.dataCadastro = dataCadastro;
        this.descricaoSoftware = descricaoSoftware;
        this.versaoSoft = versaoSoft;
        this.observacaoSoft = observacaoSoft;
        this.usuarioInsert = usuarioInsert;
        this.dataInsert = dataInsert;
        this.horarioInsert = horarioInsert;
        this.usuarioUp = usuarioUp;
        this.dataUp = dataUp;
        this.horarioUp = horarioUp;
        this.tipoServidor = tipoServidor;
        this.tipoBanco = tipoBanco;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the statusSoft
     */
    public String getStatusSoft() {
        return statusSoft;
    }

    /**
     * @param statusSoft the statusSoft to set
     */
    public void setStatusSoft(String statusSoft) {
        this.statusSoft = statusSoft;
    }

    /**
     * @return the dataCadastro
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the descricaoSoftware
     */
    public String getDescricaoSoftware() {
        return descricaoSoftware;
    }

    /**
     * @param descricaoSoftware the descricaoSoftware to set
     */
    public void setDescricaoSoftware(String descricaoSoftware) {
        this.descricaoSoftware = descricaoSoftware;
    }

    /**
     * @return the versaoSoft
     */
    public String getVersaoSoft() {
        return versaoSoft;
    }

    /**
     * @param versaoSoft the versaoSoft to set
     */
    public void setVersaoSoft(String versaoSoft) {
        this.versaoSoft = versaoSoft;
    }

    /**
     * @return the observacaoSoft
     */
    public String getObservacaoSoft() {
        return observacaoSoft;
    }

    /**
     * @param observacaoSoft the observacaoSoft to set
     */
    public void setObservacaoSoft(String observacaoSoft) {
        this.observacaoSoft = observacaoSoft;
    }

    /**
     * @return the usuarioInsert
     */
    public String getUsuarioInsert() {
        return usuarioInsert;
    }

    /**
     * @param usuarioInsert the usuarioInsert to set
     */
    public void setUsuarioInsert(String usuarioInsert) {
        this.usuarioInsert = usuarioInsert;
    }

    /**
     * @return the dataInsert
     */
    public String getDataInsert() {
        return dataInsert;
    }

    /**
     * @param dataInsert the dataInsert to set
     */
    public void setDataInsert(String dataInsert) {
        this.dataInsert = dataInsert;
    }

    /**
     * @return the horarioInsert
     */
    public String getHorarioInsert() {
        return horarioInsert;
    }

    /**
     * @param horarioInsert the horarioInsert to set
     */
    public void setHorarioInsert(String horarioInsert) {
        this.horarioInsert = horarioInsert;
    }

    /**
     * @return the usuarioUp
     */
    public String getUsuarioUp() {
        return usuarioUp;
    }

    /**
     * @param usuarioUp the usuarioUp to set
     */
    public void setUsuarioUp(String usuarioUp) {
        this.usuarioUp = usuarioUp;
    }

    /**
     * @return the dataUp
     */
    public String getDataUp() {
        return dataUp;
    }

    /**
     * @param dataUp the dataUp to set
     */
    public void setDataUp(String dataUp) {
        this.dataUp = dataUp;
    }

    /**
     * @return the horarioUp
     */
    public String getHorarioUp() {
        return horarioUp;
    }

    /**
     * @param horarioUp the horarioUp to set
     */
    public void setHorarioUp(String horarioUp) {
        this.horarioUp = horarioUp;
    }

    /**
     * @return the tipoServidor
     */
    public String getTipoServidor() {
        return tipoServidor;
    }

    /**
     * @param tipoServidor the tipoServidor to set
     */
    public void setTipoServidor(String tipoServidor) {
        this.tipoServidor = tipoServidor;
    }

    /**
     * @return the tipoBanco
     */
    public String getTipoBanco() {
        return tipoBanco;
    }

    /**
     * @param tipoBanco the tipoBanco to set
     */
    public void setTipoBanco(String tipoBanco) {
        this.tipoBanco = tipoBanco;
    }
}
