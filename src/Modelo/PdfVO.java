/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author ronal
 */
public class PdfVO {
    private int id;
    private int idCHSup;
    private String nomeInternoCrc;
    private String descricao;
    private byte[] documento;

    public PdfVO() {
    }

    public PdfVO(int id, int idCHSup, String nomeInternoCrc, String descricao, byte[] documento) {
        this.id = id;
        this.idCHSup = idCHSup;
        this.nomeInternoCrc = nomeInternoCrc;
        this.descricao = descricao;
        this.documento = documento;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the nomeInternoCrc
     */
    public String getNomeInternoCrc() {
        return nomeInternoCrc;
    }

    /**
     * @param nomeInternoCrc the nomeInternoCrc to set
     */
    public void setNomeInternoCrc(String nomeInternoCrc) {
        this.nomeInternoCrc = nomeInternoCrc;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the documento
     */
    public byte[] getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }
}
