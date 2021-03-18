/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Ocorrencias;
import Modelo.Solicitantes;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ronal
 */
public class solicitantesDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    Solicitantes objSoli = new Solicitantes();

    int codigoEmp;
    int codigoUni;

    public Solicitantes incluirSolicitante(Solicitantes objSoli) {
        buscarEmpresa(objSoli.getIdEmpresa(), objSoli.getDescricaoEmpresa());
        buscarUnidade(objSoli.getIdUnidade(), objSoli.getDescricaoUnidade());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO SOLICITANTES (StatusSolicitante,DataCadastro,NomeSolicitante,IdEmpresa,IdUnidEmp,Observacao,UsuarioInsert,DataInsert,HorarioInsert) VALUES(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, objSoli.getStatusSolicitante());
            pst.setTimestamp(2, new java.sql.Timestamp(objSoli.getDataCadastro().getTime()));
            pst.setString(3, objSoli.getNomeSolicitante());
            pst.setInt(4, codigoEmp);
            pst.setInt(5, codigoUni);
            pst.setString(6, objSoli.getObservacao());
            pst.setString(7, objSoli.getUsuarioInsert());
            pst.setString(8, objSoli.getDataInsert());
            pst.setString(9, objSoli.getHorarioInsert());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objSoli;
    }

    public Solicitantes alterarSolicitante(Solicitantes objSoli) {
        buscarEmpresa(objSoli.getIdEmpresa(), objSoli.getDescricaoEmpresa());
        buscarUnidade(objSoli.getIdUnidade(), objSoli.getDescricaoUnidade());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE SOLICITANTES SET StatusSolicitante=?,DataCadastro=?,NomeSolicitante=?,IdEmpresa=?,IdUnidEmp=?,Observacao=?,UsuarioUp=?,DataUp=?,HorarioUp=? WHERE IdSolicitante='" + objSoli.getIdSolicitante() + "'");
            pst.setString(1, objSoli.getStatusSolicitante());
            pst.setTimestamp(2, new java.sql.Timestamp(objSoli.getDataCadastro().getTime()));
            pst.setString(3, objSoli.getNomeSolicitante());
            pst.setInt(4, codigoEmp);
            pst.setInt(5, codigoUni);
            pst.setString(6, objSoli.getObservacao());
            pst.setString(7, objSoli.getUsuarioUp());
            pst.setString(8, objSoli.getDataUp());
            pst.setString(9, objSoli.getHorarioUp());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objSoli;
    }

    public Solicitantes excluirSolicitante(Solicitantes objSoli) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("DELETE SOLICITANTES WHERE IdSolicitante='" + objSoli.getIdSolicitante() + "'");
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel EXCLUIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objSoli;
    }

    public void buscarEmpresa(int id, String desc) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdEmpresa, "
                    + "RazaoSocial "
                    + "FROM EMPRESA "
                    + "WHERE IdEmpresa='" + id + "' "
                    + "AND RazaoSocial='" + desc + "'");
            conecta.rs.first();
            codigoEmp = conecta.rs.getInt("IdEmpresa");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    public void buscarUnidade(int cod, String unid) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUnidEmp, "
                    + "DescricaoUnidade "
                    + "FROM UNIDADE_PENAL_EMPRESA "
                    + "WHERE IdUnidEmp='" + cod + "' "
                    + "AND DescricaoUnidade='" + unid + "'");
            conecta.rs.first();
            codigoUni = conecta.rs.getInt("IdUnidEmp");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }
}
