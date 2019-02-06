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
public class ChamadosDesenvolvimentoDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    ChamadoSuporte objCHSup = new ChamadoSuporte();
    int codUser;
    int codEmp;
    int codUnidEmp;
    int codSoft;
    int codModu;
    int codSoli;

    public ChamadoSuporte incluirChamadoDes(ChamadoSuporte objCHSup) {
        pesquisarUsuario(objCHSup.getNomeUsuario());
        pesquisarSolicitante(objCHSup.getNomeSolicitante(), objCHSup.getIdSolicitante());
        pesquisarUnidade(objCHSup.getDescricaoUnidade());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO CHAMADOS_DESENVOLVIMENTO (StatusCha,DataCha,IdUsuario,IdSolicitante,IdUnidEmp,UsuarioInsert,DataInsert,HorarioInsert) VALUES(?,?,?,?,?,?,?,?)");
            pst.setString(1, objCHSup.getStatusCha());
            pst.setTimestamp(2, new java.sql.Timestamp(objCHSup.getDataCha().getTime()));
            pst.setInt(3, codUser);
            pst.setInt(4, codSoli);
            pst.setInt(5, codUnidEmp);
            pst.setString(6, objCHSup.getUsuarioInsert());
            pst.setString(7, objCHSup.getDataInsert());
            pst.setString(8, objCHSup.getHorarioInsert());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte alterarChamadoDes(ChamadoSuporte objCHSup) {
        pesquisarUsuario(objCHSup.getNomeUsuario());
        pesquisarSolicitante(objCHSup.getNomeSolicitante(), objCHSup.getIdSolicitante());
        pesquisarUnidade(objCHSup.getDescricaoUnidade());
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE CHAMADOS_DESENVOLVIMENTO SET StatusCha=?,DataCha=?,IdUsuario=?,IdSolicitante=?,IdUnidEmp=?,UsuarioUp=?,DataUp=?,HorarioUp=? WHERE IdCHDes='" + objCHSup.getIdCHDes() + "'");
            pst.setString(1, objCHSup.getStatusCha());
            pst.setTimestamp(2, new java.sql.Timestamp(objCHSup.getDataCha().getTime()));
            pst.setInt(3, codUser);
            pst.setInt(4, codSoli);
            pst.setInt(5, codUnidEmp);
            pst.setString(6, objCHSup.getUsuarioUp());
            pst.setString(7, objCHSup.getDataUp());
            pst.setString(8, objCHSup.getHorarioUp());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte encerrarChamadoDes(ChamadoSuporte objCHSup) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE CHAMADOS_DESENVOLVIMENTO SET StatusCha=?,UsuarioUp=?,DataUp=?,HorarioUp=? "
                    + "WHERE IdCHDes='" + objCHSup.getIdCHDes() + "'");
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

    public ChamadoSuporte excluirChamadoDesc(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("DELETE FROM CHAMADOS_DESENVOLVIMENTO "
                    + "WHERE IdCHDes='" + objCHSup.getIdCHDes() + "'");
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel EXCLUIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte incluirItensSupDes(ChamadoSuporte objCHSup) {
        pesquisarSoftware(objCHSup.getDescricaoSoftware());
        pesquisarModulo(objCHSup.getDescricaoModulo());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO ITENS_CHAMADOS_DESENVOLVIMENTO (IdCHDes,IdItem,DataItemCh,HorarioInicio,HorarioTermino,IdSoftware,IdModulo,TextoSuporte,TextoDesenvol,TipoChamado,UsuarioInsert,DataInsert,HorarioInsert)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, objCHSup.getIdCHDes());
            pst.setInt(2, objCHSup.getIdItemCh());
            pst.setTimestamp(3, new java.sql.Timestamp(objCHSup.getDataItemCh().getTime()));
            pst.setString(4, objCHSup.getHorarioInicio());
            pst.setString(5, objCHSup.getHorarioTermino());
            pst.setInt(6, codSoft);
            pst.setInt(7, codModu);
            pst.setString(8, objCHSup.getTextoSuporte());
            pst.setString(9, objCHSup.getTextoDesenvol());
            pst.setString(10, objCHSup.getTipoChamado());
            pst.setString(11, objCHSup.getUsuarioInsert());
            pst.setString(12, objCHSup.getDataInsert());
            pst.setString(13, objCHSup.getHorarioInsert());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte alterarItensSupDes(ChamadoSuporte objCHSup) {
        pesquisarSoftware(objCHSup.getDescricaoSoftware());
        pesquisarModulo(objCHSup.getDescricaoModulo());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE ITENS_CHAMADOS_DESENVOLVIMENTO SET IdCHDes=?,IdItem=?,DataItemCh=?,HorarioInicio=?,HorarioTermino=?,IdSoftware=?,IdModulo=?,TextoSuporte=?,TextoDesenvol=?,TipoChamado=?,UsuarioUp=?,DataUp=?,HorarioUp=? WHERE IdItemDes='" + objCHSup.getIdItemCh() + "'");
            pst.setInt(1, objCHSup.getIdCHDes());
            pst.setInt(2, objCHSup.getIdItemCh());
            pst.setTimestamp(3, new java.sql.Timestamp(objCHSup.getDataItemCh().getTime()));
            pst.setString(4, objCHSup.getHorarioInicio());
            pst.setString(5, objCHSup.getHorarioTermino());
            pst.setInt(6, codSoft);
            pst.setInt(7, codModu);
            pst.setString(8, objCHSup.getTextoSuporte());
            pst.setString(9, objCHSup.getTextoDesenvol());
            pst.setString(10, objCHSup.getTipoChamado());
            pst.setString(11, objCHSup.getUsuarioUp());
            pst.setString(12, objCHSup.getDataUp());
            pst.setString(13, objCHSup.getHorarioUp());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte excluirItensSupDes(ChamadoSuporte objCHSup) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("DELETE FROM ITENS_CHAMADOS_DESENVOLVIMENTO WHERE IdItemDes='" + objCHSup.getIdItemCh() + "'");
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte enviarItensSupDes(ChamadoSuporte objCHSup) {
        pesquisarSoftware(objCHSup.getDescricaoSoftware());
        pesquisarModulo(objCHSup.getDescricaoModulo());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO ITENS_CHAMADOS_SUPORTE_DESENVOLVIMENTO (IdCHSup,IdItem,DataItemCh,HorarioInicio,HorarioTermino,IdSoftware,IdModulo,TextoSuporte,Utilizado,UsuarioInsert,DataInsert,HorarioInsert)VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, objCHSup.getIdCHSup());
            pst.setInt(1, objCHSup.getIdCHSup());
            pst.setInt(2, objCHSup.getIdItemCh());
            pst.setTimestamp(3, new java.sql.Timestamp(objCHSup.getDataItemCh().getTime()));
            pst.setString(4, objCHSup.getHorarioInicio());
            pst.setString(5, objCHSup.getHorarioTermino());
            pst.setInt(6, codSoft);
            pst.setInt(7, codModu);
            pst.setString(8, objCHSup.getTextoSuporte());
            pst.setString(9, objCHSup.getUtiliza());
            pst.setString(10, objCHSup.getUsuarioInsert());
            pst.setString(11, objCHSup.getDataInsert());
            pst.setString(12, objCHSup.getHorarioInsert());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte alterarEnviarItensSupDes(ChamadoSuporte objCHSup) {
        pesquisarSoftware(objCHSup.getDescricaoSoftware());
        pesquisarModulo(objCHSup.getDescricaoModulo());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE ITENS_CHAMADOS_SUPORTE_DESENVOLVIMENTO SET IdCHSup=?,IdItem=?,DataItemCh=?,HorarioInicio=?,HorarioTermino=?,IdSoftware=?,IdModulo=?,TextoSuporte=?,Utilizado=?,UsuarioUp=?,DataUp=?,HorarioUp=? WHERE IdCHSup='" + objCHSup.getIdCHSup() + "' AND IdItem='" + objCHSup.getIdItemCh() + "'");
            pst.setInt(1, objCHSup.getIdCHSup());
            pst.setInt(2, objCHSup.getIdItemCh());
            pst.setTimestamp(3, new java.sql.Timestamp(objCHSup.getDataItemCh().getTime()));
            pst.setString(4, objCHSup.getHorarioInicio());
            pst.setString(5, objCHSup.getHorarioTermino());
            pst.setInt(6, codSoft);
            pst.setInt(7, codModu);
            pst.setString(8, objCHSup.getTextoSuporte());
            pst.setString(9, objCHSup.getUtiliza());
            pst.setString(10, objCHSup.getUsuarioUp());
            pst.setString(11, objCHSup.getDataUp());
            pst.setString(12, objCHSup.getHorarioUp());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    // ATUALIZA A TABELA ITENS_CHAMADOS_SUPORTE_DESENVOLVIMENTO
    // RESPONDENDO AO CAMPO Utilizado "Sim" PARA QUE NÃO POSSA BUSCAR O MESMO REGISTRO.
    public ChamadoSuporte repostaItensSupDes(ChamadoSuporte objCHSup) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE ITENS_CHAMADOS_SUPORTE_DESENVOLVIMENTO SET Utilizado=? WHERE IdCHSup='" + objCHSup.getIdCHSup() + "' AND IdItem='" + objCHSup.getIdItemCh() + "'");
            pst.setString(1, objCHSup.getUtiliza());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados.\nERRO: " + ex);
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
