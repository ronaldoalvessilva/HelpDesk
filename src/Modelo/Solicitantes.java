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
public class Solicitantes {

    private int idSolicitante;
    private String statusSolicitante;
    private Date dataCadastro;
    private String nomeSolicitante;
    private int idEmpresa;
    private String descricaoEmpresa;
    private int idUnidade;
    private String descricaoUnidade;
    private String observacao;
    private String usuarioInsert;
    private String usuarioUp;
    private String dataInsert;
    private String dataUp;
    private String horarioInsert;
    private String horarioUp;

    public Solicitantes() {
    }

    public Solicitantes(int idSolicitante, String statusSolicitante, Date dataCadastro, String nomeSolicitante, int idEmpresa, String descricaoEmpresa, int idUnidade, String descricaoUnidade, String observacao, String usuarioInsert, String usuarioUp, String dataInsert, String dataUp, String horarioInsert, String horarioUp) {
        this.idSolicitante = idSolicitante;
        this.statusSolicitante = statusSolicitante;
        this.dataCadastro = dataCadastro;
        this.nomeSolicitante = nomeSolicitante;
        this.idEmpresa = idEmpresa;
        this.descricaoEmpresa = descricaoEmpresa;
        this.idUnidade = idUnidade;
        this.descricaoUnidade = descricaoUnidade;
        this.observacao = observacao;
        this.usuarioInsert = usuarioInsert;
        this.usuarioUp = usuarioUp;
        this.dataInsert = dataInsert;
        this.dataUp = dataUp;
        this.horarioInsert = horarioInsert;
        this.horarioUp = horarioUp;
    }

    /**
     * @return the idSolicitante
     */
    public int getIdSolicitante() {
        return idSolicitante;
    }

    /**
     * @param idSolicitante the idSolicitante to set
     */
    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    /**
     * @return the statusSolicitante
     */
    public String getStatusSolicitante() {
        return statusSolicitante;
    }

    /**
     * @param statusSolicitante the statusSolicitante to set
     */
    public void setStatusSolicitante(String statusSolicitante) {
        this.statusSolicitante = statusSolicitante;
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
     * @return the nomeSolicitante
     */
    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    /**
     * @param nomeSolicitante the nomeSolicitante to set
     */
    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    /**
     * @return the idEmpresa
     */
    public int getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the descricaoEmpresa
     */
    public String getDescricaoEmpresa() {
        return descricaoEmpresa;
    }

    /**
     * @param descricaoEmpresa the descricaoEmpresa to set
     */
    public void setDescricaoEmpresa(String descricaoEmpresa) {
        this.descricaoEmpresa = descricaoEmpresa;
    }

    /**
     * @return the idUnidade
     */
    public int getIdUnidade() {
        return idUnidade;
    }

    /**
     * @param idUnidade the idUnidade to set
     */
    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }

    /**
     * @return the descricaoUnidade
     */
    public String getDescricaoUnidade() {
        return descricaoUnidade;
    }

    /**
     * @param descricaoUnidade the descricaoUnidade to set
     */
    public void setDescricaoUnidade(String descricaoUnidade) {
        this.descricaoUnidade = descricaoUnidade;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
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
}
