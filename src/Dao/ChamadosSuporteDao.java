/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.ChamadoSuporte;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ronal
 */
public class ChamadosSuporteDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    ChamadoSuporte objCHSup = new ChamadoSuporte();
    int codUser;
    int codEmp;
    int codUnidEmp;
    int codSoft;
    int codModu;
    int codSoli;

    public ChamadoSuporte incluirChamadoSup(ChamadoSuporte objCHSup) {
        pesquisarUsuario(objCHSup.getNomeUsuario());
        pesquisarUnidade(objCHSup.getDescricaoUnidade());
        pesquisarSoftware(objCHSup.getDescricaoSoftware());
        pesquisarModulo(objCHSup.getDescricaoModulo());
        pesquisarSolicitante(objCHSup.getNomeSolicitante(), objCHSup.getIdSolicitante());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO CHAMADOS_SUPORTE (StatusCha,DataCha,HorarioInicio,HorarioTermino,IdUsuario,IdUnidEmp,IdSoftware,IdModulo,IdSolicitante,TextoSuporte,TextoDesenvol,UsuarioInsert,DataInsert,HorarioInsert) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, objCHSup.getStatusCha());
            pst.setTimestamp(2, new java.sql.Timestamp(objCHSup.getDataCha().getTime()));
            pst.setString(3, objCHSup.getHorarioInicio());
            pst.setString(4, objCHSup.getHorarioTermino());
            pst.setInt(5, codUser);
            pst.setInt(6, codUnidEmp);
            pst.setInt(7, codSoft);
            pst.setInt(8, codModu);
            pst.setInt(9, codSoli);
            pst.setString(10, objCHSup.getTextoSuporte());
            pst.setString(11, objCHSup.getTextoDesenvol());
            pst.setString(12, objCHSup.getUsuarioInsert());
            pst.setString(13, objCHSup.getDataInsert());
            pst.setString(14, objCHSup.getHorarioInsert());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte alterarChamadoSup(ChamadoSuporte objCHSup) {
        pesquisarUsuario(objCHSup.getNomeUsuario());
        pesquisarUnidade(objCHSup.getDescricaoUnidade());
        pesquisarSoftware(objCHSup.getDescricaoSoftware());
        pesquisarModulo(objCHSup.getDescricaoModulo());
        pesquisarSolicitante(objCHSup.getNomeSolicitante(), objCHSup.getIdSolicitante());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE CHAMADOS_SUPORTE SET StatusCha=?,DataCha=?,HorarioInicio=?,HorarioTermino=?,IdUsuario=?,IdUnidEmp=?,IdSoftware=?,IdModulo=?,IdSolicitante=?,TextoSuporte=?,TextoDesenvol=?,UsuarioUp=?,DataUp=?,HorarioUp=? WHERE IdCHSup='" + objCHSup.getIdCHSup() + "'");
            pst.setString(1, objCHSup.getStatusCha());
            pst.setTimestamp(2, new java.sql.Timestamp(objCHSup.getDataCha().getTime()));
            pst.setString(3, objCHSup.getHorarioInicio());
            pst.setString(4, objCHSup.getHorarioTermino());
            pst.setInt(5, codUser);
            pst.setInt(6, codUnidEmp);
            pst.setInt(7, codSoft);
            pst.setInt(8, codModu);
            pst.setInt(9, codSoli);
            pst.setString(10, objCHSup.getTextoSuporte());
            pst.setString(11, objCHSup.getTextoDesenvol());
            pst.setString(12, objCHSup.getUsuarioUp());
            pst.setString(13, objCHSup.getDataUp());
            pst.setString(14, objCHSup.getHorarioUp());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte encerrarChamadoSup(ChamadoSuporte objCHSup) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE CHAMADOS_SUPORTE SET StatusCha=?,UsuarioUp=?,DataUp=?,HorarioUp=? "
                    + "WHERE IdCHSup='" + objCHSup.getIdCHSup() + "'");
            pst.setString(1, objCHSup.getStatusCha());
            pst.setString(12, objCHSup.getUsuarioUp());
            pst.setString(13, objCHSup.getDataUp());
            pst.setString(14, objCHSup.getHorarioUp());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte excluirChamadoSup(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("DELETE FROM CHAMADOS_SUPORTE "
                    + "WHERE IdCHSup='" + objCHSup.getIdCHSup() + "'");
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel EXCLUIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    //PESQUISAR USUARIO
    public void pesquisarUsuario(String nome) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT * FROM USUARIOS "
                    + "WHERE NomeUsuario='" + nome + "'");
            conecta.rs.first();
            codUser = conecta.rs.getInt("IdUsuario");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    //PESQUISAR UNIDADE PRISIONAL
    public void pesquisarUnidade(String desc) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT * FROM UNIDADE_PENAL_EMPRESA "
                    + "WHERE DescricaoUnidade='" + desc + "'");
            conecta.rs.first();
            codUnidEmp = conecta.rs.getInt("IdUnidEmp");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    //PESQUISAR SOFTWARE
    public void pesquisarSoftware(String desc) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT * FROM SOFTWARE "
                    + "WHERE DescricaoSoftware='" + desc + "'");
            conecta.rs.first();
            codSoft = conecta.rs.getInt("IdSoftware");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    //PESQUISAR MÓDULO
    public void pesquisarModulo(String desc) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT * FROM MODULOS "
                    + "WHERE DescricaoModulo='" + desc + "'");
            conecta.rs.first();
            codModu = conecta.rs.getInt("IdModulo");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    //PESQUISAR SOLICITANTE
    public void pesquisarSolicitante(String desc, int cod) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT * FROM SOLICITANTES "
                    + "WHERE NomeSolicitante='" + desc + "' "
                    + "AND IdSolicitante='" + cod + "'");
            conecta.rs.first();
            codSoli = conecta.rs.getInt("IdSolicitante");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }
}
