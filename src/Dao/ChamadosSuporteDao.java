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
        pesquisarSolicitante(objCHSup.getNomeSolicitante(), objCHSup.getIdSolicitante());
        pesquisarUnidade(objCHSup.getDescricaoUnidade());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO CHAMADOS_SUPORTE (StatusCha,DataCha,IdUsuario,IdSolicitante,IdUnidEmp,UsuarioInsert,DataInsert,HorarioInsert,AssuntoSuporte) VALUES(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, objCHSup.getStatusCha());
            pst.setTimestamp(2, new java.sql.Timestamp(objCHSup.getDataCha().getTime()));
            pst.setInt(3, codUser);
            pst.setInt(4, codSoli);
            pst.setInt(5, codUnidEmp);
            pst.setString(6, objCHSup.getUsuarioInsert());
            pst.setString(7, objCHSup.getDataInsert());
            pst.setString(8, objCHSup.getHorarioInsert());
            pst.setString(9, objCHSup.getAssunto());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte alterarChamadoSup(ChamadoSuporte objCHSup) {
        pesquisarUsuario(objCHSup.getNomeUsuario());
        pesquisarSolicitante(objCHSup.getNomeSolicitante(), objCHSup.getIdSolicitante());
        pesquisarUnidade(objCHSup.getDescricaoUnidade());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE CHAMADOS_SUPORTE SET StatusCha=?,DataCha=?,IdUsuario=?,IdSolicitante=?,IdUnidEmp=?,UsuarioUp=?,DataUp=?,HorarioUp=?,AssuntoSuporte=? WHERE IdCHSup='" + objCHSup.getIdCHSup() + "'");
            pst.setString(1, objCHSup.getStatusCha());
            pst.setTimestamp(2, new java.sql.Timestamp(objCHSup.getDataCha().getTime()));
            pst.setInt(3, codUser);
            pst.setInt(4, codSoli);
            pst.setInt(5, codUnidEmp);
            pst.setString(6, objCHSup.getUsuarioUp());
            pst.setString(7, objCHSup.getDataUp());
            pst.setString(8, objCHSup.getHorarioUp());
            pst.setString(9, objCHSup.getAssunto());
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
            pst.setString(2, objCHSup.getUsuarioUp());
            pst.setString(3, objCHSup.getDataUp());
            pst.setString(4, objCHSup.getHorarioUp());
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

    public ChamadoSuporte incluirItensSup(ChamadoSuporte objCHSup) {
        pesquisarSoftware(objCHSup.getDescricaoSoftware());
        pesquisarModulo(objCHSup.getDescricaoModulo());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO ITENS_CHAMADOS_SUPORTE (IdCHSup,DataItemCh,HorarioInicio,HorarioTermino,IdSoftware,IdModulo,TextoSuporte,TextoDesenvol,TipoChamado,UsuarioInsert,DataInsert,HorarioInsert,ImagemDocumento,ImagemDocumento1,ImagemDocumento2,ImagemDocumento3)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, objCHSup.getIdCHSup());
            pst.setTimestamp(2, new java.sql.Timestamp(objCHSup.getDataItemCh().getTime()));
            pst.setString(3, objCHSup.getHorarioInicio());
            pst.setString(4, objCHSup.getHorarioTermino());
            pst.setInt(5, codSoft);
            pst.setInt(6, codModu);
            pst.setString(7, objCHSup.getTextoSuporte());
            pst.setString(8, objCHSup.getTextoDesenvol());
            pst.setString(9, objCHSup.getTipoChamado());
            pst.setString(10, objCHSup.getUsuarioInsert());
            pst.setString(11, objCHSup.getDataInsert());
            pst.setString(12, objCHSup.getHorarioInsert());
            pst.setBytes(13, objCHSup.getImagemDocumento());
            pst.setBytes(14, objCHSup.getImagemDocumento1());
            pst.setBytes(15, objCHSup.getImagemDocumento2());
            pst.setBytes(16, objCHSup.getImagemDocumento3());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte alterarItensSup(ChamadoSuporte objCHSup) {
        pesquisarSoftware(objCHSup.getDescricaoSoftware());
        pesquisarModulo(objCHSup.getDescricaoModulo());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE ITENS_CHAMADOS_SUPORTE SET DataItemCh=?,HorarioInicio=?,HorarioTermino=?,IdSoftware=?,IdModulo=?,TextoSuporte=?,TextoDesenvol=?,TipoChamado=?,UsuarioUp=?,DataUp=?,HorarioUp=?,ImagemDocumento=?,ImagemDocumento1=?,ImagemDocumento2=?,ImagemDocumento3=? WHERE IdItem='" + objCHSup.getIdItemCh() + "'");
            pst.setTimestamp(1, new java.sql.Timestamp(objCHSup.getDataItemCh().getTime()));
            pst.setString(2, objCHSup.getHorarioInicio());
            pst.setString(3, objCHSup.getHorarioTermino());
            pst.setInt(4, codSoft);
            pst.setInt(5, codModu);
            pst.setString(6, objCHSup.getTextoSuporte());
            pst.setString(7, objCHSup.getTextoDesenvol());
            pst.setString(8, objCHSup.getTipoChamado());
            pst.setString(9, objCHSup.getUsuarioUp());
            pst.setString(10, objCHSup.getDataUp());
            pst.setString(11, objCHSup.getHorarioUp());
            pst.setBytes(12, objCHSup.getImagemDocumento());
            pst.setBytes(13, objCHSup.getImagemDocumento1());
            pst.setBytes(14, objCHSup.getImagemDocumento2());
            pst.setBytes(15, objCHSup.getImagemDocumento3());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte excluirItensChamadoSup(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("DELETE FROM ITENS_CHAMADOS_SUPORTE "
                    + "WHERE IdItem='" + objCHSup.getIdItemCh() + "'");
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
