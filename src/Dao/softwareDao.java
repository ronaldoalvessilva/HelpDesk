/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Software;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ronal
 */
public class softwareDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    Software objSoft = new Software();

    public Software incluirSoftware(Software objSoft) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO SOFTWARE (StatusSoftware,DataCadastro,DescricaoSoftware,VersaoSoftware,ObservacaoSoftware,UsuarioInsert,DataInsert,HorarioInsert,TipoServidor,TipoBanco)VALUES(?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, objSoft.getStatusSoft());
            if (objSoft.getDataCadastro() != null) {
                pst.setTimestamp(2, new java.sql.Timestamp(objSoft.getDataCadastro().getTime()));
            } else {
                pst.setDate(2, null);
            }
            pst.setString(3, objSoft.getDescricaoSoftware());
            pst.setString(4, objSoft.getVersaoSoft());
            pst.setString(5, objSoft.getObservacaoSoft());
            pst.setString(6, objSoft.getUsuarioInsert());
            pst.setString(7, objSoft.getDataInsert());
            pst.setString(8, objSoft.getHorarioInsert());
            pst.setString(9, objSoft.getTipoServidor());
            pst.setString(10, objSoft.getTipoBanco());
            pst.execute(); // Executa a inserção
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possível INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objSoft;
    }

    public Software alterarSoftware(Software objSoft) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE SOFTWARE SET StatusSoftware=?,DataCadastro=?,DescricaoSoftware=?,VersaoSoftware=?,ObservacaoSoftware=?,UsuarioUp=?,DataUp=?,HorarioUp=?,TipoServidor=?,TipoBanco=? WHERE IdSoftware='" + objSoft.getCodigo() + "'");
            pst.setString(1, objSoft.getStatusSoft());
            if (objSoft.getDataCadastro() != null) {
                pst.setTimestamp(2, new java.sql.Timestamp(objSoft.getDataCadastro().getTime()));
            } else {
                pst.setDate(2, null);
            }
            pst.setString(3, objSoft.getDescricaoSoftware());
            pst.setString(4, objSoft.getVersaoSoft());
            pst.setString(5, objSoft.getObservacaoSoft());
            pst.setString(6, objSoft.getUsuarioUp());
            pst.setString(7, objSoft.getDataUp());
            pst.setString(8, objSoft.getHorarioUp());
            pst.setString(9, objSoft.getTipoServidor());
            pst.setString(10, objSoft.getTipoBanco());
            pst.executeUpdate(); // Executa a inserção
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possível ALTERAR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objSoft;
    }

    public Software excluirSoftware(Software objSoft) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("DELETE FROM SOFTWARE WHERE IdSoftware='" + objSoft.getCodigo() + "'");
            pst.executeUpdate(); // Executa a inserção
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possível EXCLUIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objSoft;
    }
}
