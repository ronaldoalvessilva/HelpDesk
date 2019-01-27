/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.util.Date;

/**
 *
 * @author Ronaldo
 */
public class Usuarios {
    
    private int IdUsuario;   
    private String Status;
    private Date dataCadastro;
    private String NomeUsuario;
    private String LoginUsuario;
    private String SenhaUsuario;
    private String SenhaUsuario1;
    private int NivelUsuario;   
    private String SetorUsuario; 
    private String CargoUsuario;      
    private byte [] senhaCriptografada;
    private byte [] contraSenhaCriptografada;

    public Usuarios() {
    }

    public Usuarios(int IdUsuario, String Status, Date dataCadastro, String NomeUsuario, String LoginUsuario, String SenhaUsuario, String SenhaUsuario1, int NivelUsuario, String SetorUsuario, String CargoUsuario, byte[] senhaCriptografada, byte[] contraSenhaCriptografada) {
        this.IdUsuario = IdUsuario;
        this.Status = Status;
        this.dataCadastro = dataCadastro;
        this.NomeUsuario = NomeUsuario;
        this.LoginUsuario = LoginUsuario;
        this.SenhaUsuario = SenhaUsuario;
        this.SenhaUsuario1 = SenhaUsuario1;
        this.NivelUsuario = NivelUsuario;
        this.SetorUsuario = SetorUsuario;
        this.CargoUsuario = CargoUsuario;
        this.senhaCriptografada = senhaCriptografada;
        this.contraSenhaCriptografada = contraSenhaCriptografada;
    }

    /**
     * @return the IdUsuario
     */
    public int getIdUsuario() {
        return IdUsuario;
    }

    /**
     * @param IdUsuario the IdUsuario to set
     */
    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
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
     * @return the LoginUsuario
     */
    public String getLoginUsuario() {
        return LoginUsuario;
    }

    /**
     * @param LoginUsuario the LoginUsuario to set
     */
    public void setLoginUsuario(String LoginUsuario) {
        this.LoginUsuario = LoginUsuario;
    }

    /**
     * @return the SenhaUsuario
     */
    public String getSenhaUsuario() {
        return SenhaUsuario;
    }

    /**
     * @param SenhaUsuario the SenhaUsuario to set
     */
    public void setSenhaUsuario(String SenhaUsuario) {
        this.SenhaUsuario = SenhaUsuario;
    }

    /**
     * @return the SenhaUsuario1
     */
    public String getSenhaUsuario1() {
        return SenhaUsuario1;
    }

    /**
     * @param SenhaUsuario1 the SenhaUsuario1 to set
     */
    public void setSenhaUsuario1(String SenhaUsuario1) {
        this.SenhaUsuario1 = SenhaUsuario1;
    }

    /**
     * @return the NivelUsuario
     */
    public int getNivelUsuario() {
        return NivelUsuario;
    }

    /**
     * @param NivelUsuario the NivelUsuario to set
     */
    public void setNivelUsuario(int NivelUsuario) {
        this.NivelUsuario = NivelUsuario;
    }

    /**
     * @return the SetorUsuario
     */
    public String getSetorUsuario() {
        return SetorUsuario;
    }

    /**
     * @param SetorUsuario the SetorUsuario to set
     */
    public void setSetorUsuario(String SetorUsuario) {
        this.SetorUsuario = SetorUsuario;
    }

    /**
     * @return the CargoUsuario
     */
    public String getCargoUsuario() {
        return CargoUsuario;
    }

    /**
     * @param CargoUsuario the CargoUsuario to set
     */
    public void setCargoUsuario(String CargoUsuario) {
        this.CargoUsuario = CargoUsuario;
    }

    /**
     * @return the senhaCriptografada
     */
    public byte[] getSenhaCriptografada() {
        return senhaCriptografada;
    }

    /**
     * @param senhaCriptografada the senhaCriptografada to set
     */
    public void setSenhaCriptografada(byte[] senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }

    /**
     * @return the contraSenhaCriptografada
     */
    public byte[] getContraSenhaCriptografada() {
        return contraSenhaCriptografada;
    }

    /**
     * @param contraSenhaCriptografada the contraSenhaCriptografada to set
     */
    public void setContraSenhaCriptografada(byte[] contraSenhaCriptografada) {
        this.contraSenhaCriptografada = contraSenhaCriptografada;
    }
}
