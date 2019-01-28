/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Modulos;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ronal
 */
public class modulosDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    Modulos objMod = new Modulos();

    int codigo;

    public Modulos incluirModulo(Modulos objMod) {
        buscarCodigoSoftware(objMod.getIdSoftware(), objMod.getNomeSoftware());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO MODULOS (StatusModulo,DataCadastro,DescricaoModulo,IdSoftware,ObservacaoModulo,UsuarioInsert,DataInsert,HorarioInsert)VALUES(?,?,?,?,?,?,?,?)");
            pst.setString(1, objMod.getStatusModulo());
            if (objMod.getDataCadastro() != null) {
                pst.setTimestamp(2, new java.sql.Timestamp(objMod.getDataCadastro().getTime()));
            } else {
                pst.setDate(2, null);
            }
            pst.setString(3, objMod.getNomeModulo());
            pst.setInt(4, codigo);
            pst.setString(5, objMod.getObservacao());
            pst.setString(6, objMod.getUsuarioInsert());
            pst.setString(7, objMod.getDataInsert());
            pst.setString(8, objMod.getHorarioInsert());
            pst.execute(); // Executa a inserção
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possível INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objMod;
    }

    public Modulos alterarModulo(Modulos objMod) {
        buscarCodigoSoftware(objMod.getIdSoftware(), objMod.getNomeSoftware());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE MODULOS SET StatusModulo=?,DataCadastro=?,DescricaoModulo=?,IdSoftware=?,ObservacaoModulo=?,UsuarioUp=?,DataUp=?,HorarioUp=? WHERE IdModulo='" + objMod.getIdModulo() + "'");
            pst.setString(1, objMod.getStatusModulo());
            if (objMod.getDataCadastro() != null) {
                pst.setTimestamp(2, new java.sql.Timestamp(objMod.getDataCadastro().getTime()));
            } else {
                pst.setDate(2, null);
            }
            pst.setString(3, objMod.getNomeModulo());
            pst.setInt(4, codigo);
            pst.setString(5, objMod.getObservacao());
            pst.setString(6, objMod.getUsuarioUp());
            pst.setString(7, objMod.getDataUp());
            pst.setString(8, objMod.getHorarioUp());
            pst.executeUpdate(); // Executa a inserção
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possível ALTERAR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objMod;
    }

    public Modulos excluirModulo(Modulos objMod) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("DELETE FROM MODULOS WHERE IdModulo='" + objMod.getIdModulo() + "'");
            pst.execute(); // Executa a inserção
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possível EXCLUIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objMod;
    }

    public void buscarCodigoSoftware(int cod, String nome) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT * FROM SOFTWARE "
                    + "WHERE IdSoftware='" + cod + "' "
                    + "AND DescricaoSoftware='" + nome + "'");
            conecta.rs.first();
            codigo = conecta.rs.getInt("IdSoftware");
        } catch (SQLException ex) {
            Logger.getLogger(modulosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        conecta.desconecta();
    }
}
