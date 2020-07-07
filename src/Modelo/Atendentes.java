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
public class Atendentes {

    private int idAtendente;
    private String statusAtendente;
    private Date dataCadastro;
    private String nomeAtendente;
    private String funcao;
    private String tipo;
    private String observacaoAtendente;
    private String usuarioInsert;
    private String usuarioUp;
    private String dataInsert;
    private String dataUp;
    private String horarioInsert;
    private String horarioUp;

    public Atendentes() {
    }

    public Atendentes(int idAtendente, String statusAtendente, Date dataCadastro, String nomeAtendente, String funcao, String tipo, String observacaoAtendente, String usuarioInsert, String usuarioUp, String dataInsert, String dataUp, String horarioInsert, String horarioUp) {
        this.idAtendente = idAtendente;
        this.statusAtendente = statusAtendente;
        this.dataCadastro = dataCadastro;
        this.nomeAtendente = nomeAtendente;
        this.funcao = funcao;
        this.tipo = tipo;
        this.observacaoAtendente = observacaoAtendente;
        this.usuarioInsert = usuarioInsert;
        this.usuarioUp = usuarioUp;
        this.dataInsert = dataInsert;
        this.dataUp = dataUp;
        this.horarioInsert = horarioInsert;
        this.horarioUp = horarioUp;
    }

    /**
     * @return the idAtendente
     */
    public int getIdAtendente() {
        return idAtendente;
    }

    /**
     * @param idAtendente the idAtendente to set
     */
    public void setIdAtendente(int idAtendente) {
        this.idAtendente = idAtendente;
    }

    /**
     * @return the statusAtendente
     */
    public String getStatusAtendente() {
        return statusAtendente;
    }

    /**
     * @param statusAtendente the statusAtendente to set
     */
    public void setStatusAtendente(String statusAtendente) {
        this.statusAtendente = statusAtendente;
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
     * @return the nomeAtendente
     */
    public String getNomeAtendente() {
        return nomeAtendente;
    }

    /**
     * @param nomeAtendente the nomeAtendente to set
     */
    public void setNomeAtendente(String nomeAtendente) {
        this.nomeAtendente = nomeAtendente;
    }

    /**
     * @return the funcao
     */
    public String getFuncao() {
        return funcao;
    }

    /**
     * @param funcao the funcao to set
     */
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the observacaoAtendente
     */
    public String getObservacaoAtendente() {
        return observacaoAtendente;
    }

    /**
     * @param observacaoAtendente the observacaoAtendente to set
     */
    public void setObservacaoAtendente(String observacaoAtendente) {
        this.observacaoAtendente = observacaoAtendente;
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

    @Override
    public String toString() {
        return getNomeAtendente(); //To change body of generated methods, choose Tools | Templates.        
    }
}
