/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Atendentes;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ronal
 */
public class AtendetesDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    Atendentes objAtende = new Atendentes();

    String pSTATUS_ATENDENTE = "Ativo";

    public Atendentes incluirAtendente(Atendentes objAtende) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO ATENDENTES (StatusAtendente,DataCadastro,NomeAtendente,Funcao,Tipo,ObservacaoAtendente,UsuarioInsert,DataInsert,HorarioInsert) VALUES(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, objAtende.getStatusAtendente());
            pst.setTimestamp(2, new java.sql.Timestamp(objAtende.getDataCadastro().getTime()));
            pst.setString(3, objAtende.getNomeAtendente());
            pst.setString(4, objAtende.getFuncao());
            pst.setString(5, objAtende.getTipo());
            pst.setString(6, objAtende.getObservacaoAtendente());
            pst.setString(7, objAtende.getUsuarioInsert());
            pst.setString(8, objAtende.getDataInsert());
            pst.setString(9, objAtende.getHorarioInsert());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objAtende;
    }

    public Atendentes alterarAtendente(Atendentes objAtende) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE ATENDENTES SET StatusAtendente=?,DataCadastro=?,NomeAtendente=?,Funcao=?,Tipo=?,ObservacaoAtendente=?,UsuarioUp=?,DataUp=?,HorarioUp=? WHERE IdAtendente='" + objAtende.getIdAtendente() + "'");
            pst.setString(1, objAtende.getStatusAtendente());
            pst.setTimestamp(2, new java.sql.Timestamp(objAtende.getDataCadastro().getTime()));
            pst.setString(3, objAtende.getNomeAtendente());
            pst.setString(4, objAtende.getFuncao());
            pst.setString(5, objAtende.getTipo());
            pst.setString(6, objAtende.getObservacaoAtendente());
            pst.setString(7, objAtende.getUsuarioUp());
            pst.setString(8, objAtende.getDataUp());
            pst.setString(9, objAtende.getHorarioUp());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objAtende;
    }

    public Atendentes excluirAtendente(Atendentes objAtende) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("DELETE FROM ATENDENTES WHERE IdAtendente='" + objAtende.getIdAtendente() + "'");
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel EXCLUIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objAtende;
    }

    public List<Atendentes> read() throws Exception {
        conecta.abrirConexao();
        List<Atendentes> listaCursos = new ArrayList<Atendentes>();
        try {
            conecta.executaSQL("SELECT * FROM ATENDENTES "
                    + "WHERE StatusAtendnte='" + pSTATUS_ATENDENTE + "'");
            while (conecta.rs.next()) {
                Atendentes pDigiAtendentes = new Atendentes();
                pDigiAtendentes.setIdAtendente(conecta.rs.getInt("IdAtendente"));
                pDigiAtendentes.setStatusAtendente(conecta.rs.getString("StatusAtendente"));
                pDigiAtendentes.setNomeAtendente(conecta.rs.getString("NomeAtendente"));
                listaCursos.add(pDigiAtendentes);
            }
            return listaCursos;
        } catch (SQLException ex) {
            Logger.getLogger(AtendetesDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }
}
