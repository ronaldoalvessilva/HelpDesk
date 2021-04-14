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
public class Colaboradores {

    private Integer idColaborador;
    private String StatusColaborador;
    private Date dataCadastroColaborador;
    private Integer idUsuario;
    private String nomeUsuario;
    private String departamentoColaborador;
    private String cargoColaborador;
    private Integer idUnidade;
    private String descricaoUnidade;
    private String turmaColaborador;
    private String turnoColaborador;
    private String cargaHorariaColaborador;
    private String horarioInicialColaborador;
    private String horarioTerminoColaborador;
    private String usuarioInsert;
    private String usuarioUp;
    private String dataInsert;
    private String dataUp;
    private String horarioInsert;
    private String horarioUp;

    public Colaboradores() {
    }

    public Colaboradores(Integer idColaborador, String StatusColaborador, Date dataCadastroColaborador, Integer idUsuario, String nomeUsuario, String departamentoColaborador, String cargoColaborador, Integer idUnidade, String descricaoUnidade, String turmaColaborador, String turnoColaborador, String cargaHorariaColaborador, String horarioInicialColaborador, String horarioTerminoColaborador, String usuarioInsert, String usuarioUp, String dataInsert, String dataUp, String horarioInsert, String horarioUp) {
        this.idColaborador = idColaborador;
        this.StatusColaborador = StatusColaborador;
        this.dataCadastroColaborador = dataCadastroColaborador;
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.departamentoColaborador = departamentoColaborador;
        this.cargoColaborador = cargoColaborador;
        this.idUnidade = idUnidade;
        this.descricaoUnidade = descricaoUnidade;
        this.turmaColaborador = turmaColaborador;
        this.turnoColaborador = turnoColaborador;
        this.cargaHorariaColaborador = cargaHorariaColaborador;
        this.horarioInicialColaborador = horarioInicialColaborador;
        this.horarioTerminoColaborador = horarioTerminoColaborador;
        this.usuarioInsert = usuarioInsert;
        this.usuarioUp = usuarioUp;
        this.dataInsert = dataInsert;
        this.dataUp = dataUp;
        this.horarioInsert = horarioInsert;
        this.horarioUp = horarioUp;
    }

    /**
     * @return the idColaborador
     */
    public Integer getIdColaborador() {
        return idColaborador;
    }

    /**
     * @param idColaborador the idColaborador to set
     */
    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    /**
     * @return the StatusColaborador
     */
    public String getStatusColaborador() {
        return StatusColaborador;
    }

    /**
     * @param StatusColaborador the StatusColaborador to set
     */
    public void setStatusColaborador(String StatusColaborador) {
        this.StatusColaborador = StatusColaborador;
    }

    /**
     * @return the dataCadastroColaborador
     */
    public Date getDataCadastroColaborador() {
        return dataCadastroColaborador;
    }

    /**
     * @param dataCadastroColaborador the dataCadastroColaborador to set
     */
    public void setDataCadastroColaborador(Date dataCadastroColaborador) {
        this.dataCadastroColaborador = dataCadastroColaborador;
    }

    /**
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nomeUsuario
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * @param nomeUsuario the nomeUsuario to set
     */
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    /**
     * @return the departamentoColaborador
     */
    public String getDepartamentoColaborador() {
        return departamentoColaborador;
    }

    /**
     * @param departamentoColaborador the departamentoColaborador to set
     */
    public void setDepartamentoColaborador(String departamentoColaborador) {
        this.departamentoColaborador = departamentoColaborador;
    }

    /**
     * @return the cargoColaborador
     */
    public String getCargoColaborador() {
        return cargoColaborador;
    }

    /**
     * @param cargoColaborador the cargoColaborador to set
     */
    public void setCargoColaborador(String cargoColaborador) {
        this.cargoColaborador = cargoColaborador;
    }

    /**
     * @return the idUnidade
     */
    public Integer getIdUnidade() {
        return idUnidade;
    }

    /**
     * @param idUnidade the idUnidade to set
     */
    public void setIdUnidade(Integer idUnidade) {
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
     * @return the turmaColaborador
     */
    public String getTurmaColaborador() {
        return turmaColaborador;
    }

    /**
     * @param turmaColaborador the turmaColaborador to set
     */
    public void setTurmaColaborador(String turmaColaborador) {
        this.turmaColaborador = turmaColaborador;
    }

    /**
     * @return the turnoColaborador
     */
    public String getTurnoColaborador() {
        return turnoColaborador;
    }

    /**
     * @param turnoColaborador the turnoColaborador to set
     */
    public void setTurnoColaborador(String turnoColaborador) {
        this.turnoColaborador = turnoColaborador;
    }

    /**
     * @return the cargaHorariaColaborador
     */
    public String getCargaHorariaColaborador() {
        return cargaHorariaColaborador;
    }

    /**
     * @param cargaHorariaColaborador the cargaHorariaColaborador to set
     */
    public void setCargaHorariaColaborador(String cargaHorariaColaborador) {
        this.cargaHorariaColaborador = cargaHorariaColaborador;
    }

    /**
     * @return the horarioInicialColaborador
     */
    public String getHorarioInicialColaborador() {
        return horarioInicialColaborador;
    }

    /**
     * @param horarioInicialColaborador the horarioInicialColaborador to set
     */
    public void setHorarioInicialColaborador(String horarioInicialColaborador) {
        this.horarioInicialColaborador = horarioInicialColaborador;
    }

    /**
     * @return the horarioTerminoColaborador
     */
    public String getHorarioTerminoColaborador() {
        return horarioTerminoColaborador;
    }

    /**
     * @param horarioTerminoColaborador the horarioTerminoColaborador to set
     */
    public void setHorarioTerminoColaborador(String horarioTerminoColaborador) {
        this.horarioTerminoColaborador = horarioTerminoColaborador;
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
