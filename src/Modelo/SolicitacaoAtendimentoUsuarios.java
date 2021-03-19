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
public class SolicitacaoAtendimentoUsuarios {

    private Integer idRegistroSolicitante;
    private String statusSolicitacao;
    private Date dataSolicitacao;
    private Integer idSolicitacao;
    private String nomeSolicitante;
    private Integer idTecnico;
    private String nomeTecnico;
    private String nomeComputadorSolicitante;
    private String iPComputadorSolicitante;
    private String departamentoSolicitante;
    private String tipoSolicitacao;
    private String textoSolicitacao;
    private String usuarioInsert;
    private String usuarioUp;
    private String dataInsert;
    private String dataUp;
    private String horarioInsert;
    private String horarioUp;

    public SolicitacaoAtendimentoUsuarios() {
    }

    public SolicitacaoAtendimentoUsuarios(Integer idRegistroSolicitante, String statusSolicitacao, Date dataSolicitacao, Integer idSolicitacao, String nomeSolicitante, Integer idTecnico, String nomeTecnico, String nomeComputadorSolicitante, String iPComputadorSolicitante, String departamentoSolicitante, String tipoSolicitacao, String textoSolicitacao, String usuarioInsert, String usuarioUp, String dataInsert, String dataUp, String horarioInsert, String horarioUp) {
        this.idRegistroSolicitante = idRegistroSolicitante;
        this.statusSolicitacao = statusSolicitacao;
        this.dataSolicitacao = dataSolicitacao;
        this.idSolicitacao = idSolicitacao;
        this.nomeSolicitante = nomeSolicitante;
        this.idTecnico = idTecnico;
        this.nomeTecnico = nomeTecnico;
        this.nomeComputadorSolicitante = nomeComputadorSolicitante;
        this.iPComputadorSolicitante = iPComputadorSolicitante;
        this.departamentoSolicitante = departamentoSolicitante;
        this.tipoSolicitacao = tipoSolicitacao;
        this.textoSolicitacao = textoSolicitacao;
        this.usuarioInsert = usuarioInsert;
        this.usuarioUp = usuarioUp;
        this.dataInsert = dataInsert;
        this.dataUp = dataUp;
        this.horarioInsert = horarioInsert;
        this.horarioUp = horarioUp;
    }

    /**
     * @return the idRegistroSolicitante
     */
    public Integer getIdRegistroSolicitante() {
        return idRegistroSolicitante;
    }

    /**
     * @param idRegistroSolicitante the idRegistroSolicitante to set
     */
    public void setIdRegistroSolicitante(Integer idRegistroSolicitante) {
        this.idRegistroSolicitante = idRegistroSolicitante;
    }

    /**
     * @return the statusSolicitacao
     */
    public String getStatusSolicitacao() {
        return statusSolicitacao;
    }

    /**
     * @param statusSolicitacao the statusSolicitacao to set
     */
    public void setStatusSolicitacao(String statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    /**
     * @return the dataSolicitacao
     */
    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    /**
     * @param dataSolicitacao the dataSolicitacao to set
     */
    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    /**
     * @return the idSolicitacao
     */
    public Integer getIdSolicitacao() {
        return idSolicitacao;
    }

    /**
     * @param idSolicitacao the idSolicitacao to set
     */
    public void setIdSolicitacao(Integer idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
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
     * @return the idTecnico
     */
    public Integer getIdTecnico() {
        return idTecnico;
    }

    /**
     * @param idTecnico the idTecnico to set
     */
    public void setIdTecnico(Integer idTecnico) {
        this.idTecnico = idTecnico;
    }

    /**
     * @return the nomeTecnico
     */
    public String getNomeTecnico() {
        return nomeTecnico;
    }

    /**
     * @param nomeTecnico the nomeTecnico to set
     */
    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    /**
     * @return the nomeComputadorSolicitante
     */
    public String getNomeComputadorSolicitante() {
        return nomeComputadorSolicitante;
    }

    /**
     * @param nomeComputadorSolicitante the nomeComputadorSolicitante to set
     */
    public void setNomeComputadorSolicitante(String nomeComputadorSolicitante) {
        this.nomeComputadorSolicitante = nomeComputadorSolicitante;
    }

    /**
     * @return the iPComputadorSolicitante
     */
    public String getiPComputadorSolicitante() {
        return iPComputadorSolicitante;
    }

    /**
     * @param iPComputadorSolicitante the iPComputadorSolicitante to set
     */
    public void setiPComputadorSolicitante(String iPComputadorSolicitante) {
        this.iPComputadorSolicitante = iPComputadorSolicitante;
    }

    /**
     * @return the departamentoSolicitante
     */
    public String getDepartamentoSolicitante() {
        return departamentoSolicitante;
    }

    /**
     * @param departamentoSolicitante the departamentoSolicitante to set
     */
    public void setDepartamentoSolicitante(String departamentoSolicitante) {
        this.departamentoSolicitante = departamentoSolicitante;
    }

    /**
     * @return the tipoSolicitacao
     */
    public String getTipoSolicitacao() {
        return tipoSolicitacao;
    }

    /**
     * @param tipoSolicitacao the tipoSolicitacao to set
     */
    public void setTipoSolicitacao(String tipoSolicitacao) {
        this.tipoSolicitacao = tipoSolicitacao;
    }

    /**
     * @return the textoSolicitacao
     */
    public String getTextoSolicitacao() {
        return textoSolicitacao;
    }

    /**
     * @param textoSolicitacao the textoSolicitacao to set
     */
    public void setTextoSolicitacao(String textoSolicitacao) {
        this.textoSolicitacao = textoSolicitacao;
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
