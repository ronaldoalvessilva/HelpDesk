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
public class ChamadoSuporte {

    private int idCHSup;
    private int idCHDes;
    private String statusCha;
    private Date dataCha;
    private String assunto;
    private String horarioInicio;
    private String horarioTermino;
    private int idUsuario;
    private String NomeUsuario;
    private int idAtendente;
    private String nomeAtendente;
    private int idEmpresa;
    private String descricaoEmpresa;
    private int idUnidEmp;
    private String descricaoUnidade;
    private int idSoftware;
    private String descricaoSoftware;
    private int idModulo;
    private String descricaoModulo;
    private int idSolicitante;
    private String nomeSolicitante;
    private String textoSuporte;
    private String textoDesenvol;
    private String tipoChamado;
    private String usuarioInsert;
    private String usuarioUp;
    private String dataInsert;
    private String dataUp;
    private String horarioInsert;
    private String horarioUp;
    private int idItemCh;
    private Date dataItemCh;
    private String utiliza;
    private byte[] imagemDocumento;
    private byte[] imagemDocumento1;
    private byte[] imagemDocumento2;
    private byte[] imagemDocumento3;

    public ChamadoSuporte() {
    }

    public ChamadoSuporte(int idCHSup, int idCHDes, String statusCha, Date dataCha, String assunto, String horarioInicio, String horarioTermino, int idUsuario, String NomeUsuario, int idAtendente, String nomeAtendente, int idEmpresa, String descricaoEmpresa, int idUnidEmp, String descricaoUnidade, int idSoftware, String descricaoSoftware, int idModulo, String descricaoModulo, int idSolicitante, String nomeSolicitante, String textoSuporte, String textoDesenvol, String tipoChamado, String usuarioInsert, String usuarioUp, String dataInsert, String dataUp, String horarioInsert, String horarioUp, int idItemCh, Date dataItemCh, String utiliza, byte[] imagemDocumento, byte[] imagemDocumento1, byte[] imagemDocumento2, byte[] imagemDocumento3) {
        this.idCHSup = idCHSup;
        this.idCHDes = idCHDes;
        this.statusCha = statusCha;
        this.dataCha = dataCha;
        this.assunto = assunto;
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
        this.idUsuario = idUsuario;
        this.NomeUsuario = NomeUsuario;
        this.idAtendente = idAtendente;
        this.nomeAtendente = nomeAtendente;
        this.idEmpresa = idEmpresa;
        this.descricaoEmpresa = descricaoEmpresa;
        this.idUnidEmp = idUnidEmp;
        this.descricaoUnidade = descricaoUnidade;
        this.idSoftware = idSoftware;
        this.descricaoSoftware = descricaoSoftware;
        this.idModulo = idModulo;
        this.descricaoModulo = descricaoModulo;
        this.idSolicitante = idSolicitante;
        this.nomeSolicitante = nomeSolicitante;
        this.textoSuporte = textoSuporte;
        this.textoDesenvol = textoDesenvol;
        this.tipoChamado = tipoChamado;
        this.usuarioInsert = usuarioInsert;
        this.usuarioUp = usuarioUp;
        this.dataInsert = dataInsert;
        this.dataUp = dataUp;
        this.horarioInsert = horarioInsert;
        this.horarioUp = horarioUp;
        this.idItemCh = idItemCh;
        this.dataItemCh = dataItemCh;
        this.utiliza = utiliza;
        this.imagemDocumento = imagemDocumento;
        this.imagemDocumento1 = imagemDocumento1;
        this.imagemDocumento2 = imagemDocumento2;
        this.imagemDocumento3 = imagemDocumento3;
    }

    /**
     * @return the idCHSup
     */
    public int getIdCHSup() {
        return idCHSup;
    }

    /**
     * @param idCHSup the idCHSup to set
     */
    public void setIdCHSup(int idCHSup) {
        this.idCHSup = idCHSup;
    }

    /**
     * @return the idCHDes
     */
    public int getIdCHDes() {
        return idCHDes;
    }

    /**
     * @param idCHDes the idCHDes to set
     */
    public void setIdCHDes(int idCHDes) {
        this.idCHDes = idCHDes;
    }

    /**
     * @return the statusCha
     */
    public String getStatusCha() {
        return statusCha;
    }

    /**
     * @param statusCha the statusCha to set
     */
    public void setStatusCha(String statusCha) {
        this.statusCha = statusCha;
    }

    /**
     * @return the dataCha
     */
    public Date getDataCha() {
        return dataCha;
    }

    /**
     * @param dataCha the dataCha to set
     */
    public void setDataCha(Date dataCha) {
        this.dataCha = dataCha;
    }

    /**
     * @return the assunto
     */
    public String getAssunto() {
        return assunto;
    }

    /**
     * @param assunto the assunto to set
     */
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    /**
     * @return the horarioInicio
     */
    public String getHorarioInicio() {
        return horarioInicio;
    }

    /**
     * @param horarioInicio the horarioInicio to set
     */
    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    /**
     * @return the horarioTermino
     */
    public String getHorarioTermino() {
        return horarioTermino;
    }

    /**
     * @param horarioTermino the horarioTermino to set
     */
    public void setHorarioTermino(String horarioTermino) {
        this.horarioTermino = horarioTermino;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the NomeUsuario
     */
    public String getNomeUsuario() {
        return NomeUsuario;
    }

    /**
     * @param NomeUsuario the NomeUsuario to set
     */
    public void setNomeUsuario(String NomeUsuario) {
        this.NomeUsuario = NomeUsuario;
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
     * @return the idUnidEmp
     */
    public int getIdUnidEmp() {
        return idUnidEmp;
    }

    /**
     * @param idUnidEmp the idUnidEmp to set
     */
    public void setIdUnidEmp(int idUnidEmp) {
        this.idUnidEmp = idUnidEmp;
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
     * @return the idSoftware
     */
    public int getIdSoftware() {
        return idSoftware;
    }

    /**
     * @param idSoftware the idSoftware to set
     */
    public void setIdSoftware(int idSoftware) {
        this.idSoftware = idSoftware;
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
     * @return the idModulo
     */
    public int getIdModulo() {
        return idModulo;
    }

    /**
     * @param idModulo the idModulo to set
     */
    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    /**
     * @return the descricaoModulo
     */
    public String getDescricaoModulo() {
        return descricaoModulo;
    }

    /**
     * @param descricaoModulo the descricaoModulo to set
     */
    public void setDescricaoModulo(String descricaoModulo) {
        this.descricaoModulo = descricaoModulo;
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
     * @return the textoSuporte
     */
    public String getTextoSuporte() {
        return textoSuporte;
    }

    /**
     * @param textoSuporte the textoSuporte to set
     */
    public void setTextoSuporte(String textoSuporte) {
        this.textoSuporte = textoSuporte;
    }

    /**
     * @return the textoDesenvol
     */
    public String getTextoDesenvol() {
        return textoDesenvol;
    }

    /**
     * @param textoDesenvol the textoDesenvol to set
     */
    public void setTextoDesenvol(String textoDesenvol) {
        this.textoDesenvol = textoDesenvol;
    }

    /**
     * @return the tipoChamado
     */
    public String getTipoChamado() {
        return tipoChamado;
    }

    /**
     * @param tipoChamado the tipoChamado to set
     */
    public void setTipoChamado(String tipoChamado) {
        this.tipoChamado = tipoChamado;
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

    /**
     * @return the idItemCh
     */
    public int getIdItemCh() {
        return idItemCh;
    }

    /**
     * @param idItemCh the idItemCh to set
     */
    public void setIdItemCh(int idItemCh) {
        this.idItemCh = idItemCh;
    }

    /**
     * @return the dataItemCh
     */
    public Date getDataItemCh() {
        return dataItemCh;
    }

    /**
     * @param dataItemCh the dataItemCh to set
     */
    public void setDataItemCh(Date dataItemCh) {
        this.dataItemCh = dataItemCh;
    }

    /**
     * @return the utiliza
     */
    public String getUtiliza() {
        return utiliza;
    }

    /**
     * @param utiliza the utiliza to set
     */
    public void setUtiliza(String utiliza) {
        this.utiliza = utiliza;
    }

    /**
     * @return the imagemDocumento
     */
    public byte[] getImagemDocumento() {
        return imagemDocumento;
    }

    /**
     * @param imagemDocumento the imagemDocumento to set
     */
    public void setImagemDocumento(byte[] imagemDocumento) {
        this.imagemDocumento = imagemDocumento;
    }

    /**
     * @return the imagemDocumento1
     */
    public byte[] getImagemDocumento1() {
        return imagemDocumento1;
    }

    /**
     * @param imagemDocumento1 the imagemDocumento1 to set
     */
    public void setImagemDocumento1(byte[] imagemDocumento1) {
        this.imagemDocumento1 = imagemDocumento1;
    }

    /**
     * @return the imagemDocumento2
     */
    public byte[] getImagemDocumento2() {
        return imagemDocumento2;
    }

    /**
     * @param imagemDocumento2 the imagemDocumento2 to set
     */
    public void setImagemDocumento2(byte[] imagemDocumento2) {
        this.imagemDocumento2 = imagemDocumento2;
    }

    /**
     * @return the imagemDocumento3
     */
    public byte[] getImagemDocumento3() {
        return imagemDocumento3;
    }

    /**
     * @param imagemDocumento3 the imagemDocumento3 to set
     */
    public void setImagemDocumento3(byte[] imagemDocumento3) {
        this.imagemDocumento3 = imagemDocumento3;
    }
}
