/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.ChamadoSuporte;
import static Visao.LoginHD.nameUser;
import static Visao.LoginHD.pTOTAL_REGISTROS_DSV_EM_atendimento;
import static Visao.LoginHD.pTOTAL_REGISTROS_DSV_aberto;
import static Visao.LoginHD.pTOTAL_REGISTROS_DSV_dia;
import static Visao.LoginHD.pTOTAL_REGISTROS_DSV_fechado;
import static Visao.TelaBuscarChamadosSuporte.jIdChamadoBuscaPesquisa;
import static Visao.TelaBuscarChamadosSuporte.pTOTAL_REGISTROS_busca;
import static Visao.TelaChamadoDesenvolvimento.dataFinal;
import static Visao.TelaChamadoDesenvolvimento.dataInicial;
import static Visao.TelaChamadoDesenvolvimento.pTOTAL_registros;
import static Visao.TelaChamadoDesenvolvimento.jIdChamadoPesquisa;
import static Visao.TelaChamadoDesenvolvimento.jPesqSolicitante;
import static Visao.TelaChamadoDesenvolvimento.jPesquisarAssunto;
import static Visao.TelaChamadoDesenvolvimento.nomeAtendente;
import static Visao.TelaChamadoDesenvolvimento.jComboBoxStatus;
import static Visao.TelaChamadoDesenvolvimento.jIdChamado;
import static Visao.TelaChamadoDesenvolvimento.jIdItem;
import static Visao.TelaChamadoDesenvolvimento.pRESPOSTA;
import static Visao.TelaChamadoDesenvolvimento.idItemCHSup;
import static Visao.TelaChamadoDesenvolvimento.idItem;
import static Visao.TelaPrincipal.pDATA_DSV_pesquisa;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static Visao.TelaBuscarChamadosSuporte.pDATA_BUSCA_inicial;
import static Visao.TelaBuscarChamadosSuporte.pDATA_BUSCA_final;

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
    //
    String pSTATUS_atendente = "Ativo";
    String pSTATUS_CHAMADO_aberto = "ABERTO";
    String pSTATUS_CHAMADO_fechado = "ENCERRADO";
    String pSTATUS_CHAMADO_EM_atendimento = "EM ATENDIMENTO NO DESENVOLVIMENTO";
    //BUSCAR CHAMADOS DO SUPORTE PARA O DESENVOLVIMENTO
    String utilizado = "Não";

    public ChamadoSuporte incluirChamadoDes(ChamadoSuporte objCHSup) {
        pesquisarUsuario(objCHSup.getNomeUsuario());
        pesquisarSolicitante(objCHSup.getNomeSolicitante(), objCHSup.getIdSolicitante());
        pesquisarUnidade(objCHSup.getDescricaoUnidade());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO CHAMADOS_DESENVOLVIMENTO (StatusCha,DataCha,IdUsuario,IdSolicitante,IdUnidEmp,UsuarioInsert,DataInsert,HorarioInsert,AssuntoDesenvolvimento) VALUES(?,?,?,?,?,?,?,?,?)");
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
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
            JOptionPane.showMessageDialog(null, "Não Foi possivel INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte alterarChamadoDes(ChamadoSuporte objCHSup) {
        pesquisarUsuario(objCHSup.getNomeUsuario());
        pesquisarSolicitante(objCHSup.getNomeSolicitante(), objCHSup.getIdSolicitante());
        pesquisarUnidade(objCHSup.getDescricaoUnidade());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE CHAMADOS_DESENVOLVIMENTO SET StatusCha=?,DataCha=?,IdUsuario=?,IdSolicitante=?,IdUnidEmp=?,UsuarioUp=?,DataUp=?,HorarioUp=?,AssuntoDesenvolvimento=? WHERE IdCHDes='" + objCHSup.getIdCHDes() + "'");
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
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
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
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
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
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
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
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
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
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE ITENS_CHAMADOS_DESENVOLVIMENTO SET IdCHDes=?,IdItem=?,DataItemCh=?,HorarioInicio=?,HorarioTermino=?,IdSoftware=?,IdModulo=?,TextoSuporte=?,TextoDesenvol=?,TipoChamado=?,UsuarioUp=?,DataUp=?,HorarioUp=? WHERE IdItemDes='" + objCHSup.getIdItemDes() + "'");
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
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
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
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
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
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
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
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados da TABELA ITENS_SUPORTE_DESENVOLVIMENTO.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    // ATUALIZA A TABELA ITENS_CHAMADOS_SUPORTE_DESENVOLVIMENTO
    // RESPONDENDO AO CAMPO Utilizado "Sim" PARA QUE NÃO POSSA BUSCAR O MESMO REGISTRO.
    public ChamadoSuporte repostaItensSupDes(ChamadoSuporte objCHSup) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE ITENS_CHAMADOS_SUPORTE_DESENVOLVIMENTO SET Utilizado=? WHERE IdItem='" + objCHSup.getIdItemCh() + "'");
            pst.setString(1, objCHSup.getUtiliza());
            pst.executeUpdate();
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR resposta da TABELA ITENS_SUPORTE_DESENVOLVIMENTO.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte atualizatTextoItensSuporte(ChamadoSuporte objCHSup) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE ITENS_CHAMADOS_SUPORTE SET TextoDesenvol=? WHERE IdItem='" + objCHSup.getIdItemCh() + "'");
            pst.setString(1, objCHSup.getTextoDesenvol());
            pst.executeUpdate();
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados da TABELA ITENS_CHAMADOS_SUPORTE.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    //PESQUISAR USUARIO
    public void pesquisarUsuario(String nome) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "NomeUsuario "
                    + "FROM USUARIOS "
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
            conecta.executaSQL("SELECT "
                    + "IdUnidEmp, "
                    + "DescricaoUnidade "
                    + "FROM UNIDADE_PENAL_EMPRESA "
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
            conecta.executaSQL("SELECT "
                    + "IdSoftware, "
                    + "DescricaoSoftware "
                    + "FROM SOFTWARE "
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
            conecta.executaSQL("SELECT "
                    + "IdModulo, "
                    + "DescricaoModulo "
                    + "FROM MODULOS "
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
            conecta.executaSQL("SELECT IdSolicitante, "
                    + "NomeSolicitante "
                    + "FROM SOLICITANTES "
                    + "WHERE NomeSolicitante='" + desc + "' "
                    + "AND IdSolicitante='" + cod + "'");
            conecta.rs.first();
            codSoli = conecta.rs.getInt("IdSolicitante");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    //------------------------------------------ PESQUISAS EM GERAL ---------------------------------------------
    //PESQUISA DE CHAMADOS POR CÓDIGO - ADMINISTRADOR
    public List<ChamadoSuporte> PESQUISAR_CODIGO_ADM_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "d.IdCHDes, "
                    + "d.DataCha, "
                    + "d.StatusCha, "
                    + "d.AssuntoDesenvolvimento, "
                    + "s.NomeSolicitante, "
                    + "e.DescricaoUnidade "
                    + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON d.IdUsuario=u.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                    + "ON d.IdUnidEmp=e.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES AS s "
                    + "ON d.IdSolicitante=s.IdSolicitante "
                    + "WHERE d.IdCHDes='" + jIdChamadoPesquisa.getText() + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoAdm = new ChamadoSuporte();
                pCodigoAdm.setIdCHDes(conecta.rs.getInt("IdCHDes"));
                pCodigoAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoAdm.setAssunto(conecta.rs.getString("AssuntoDesenvolvimento"));
                pCodigoAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_CODIGO_adm.add(pCodigoAdm);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    //PESQUISA DE DADOS POR CÓDIGO DESENVOLVEDOR
    public List<ChamadoSuporte> PESQUISAR_CODIGO_DSV_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_dsv = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "d.IdCHDes, "
                    + "d.DataCha, "
                    + "d.StatusCha, "
                    + "d.AssuntoDesenvolvimento, "
                    + "s.NomeSolicitante, "
                    + "e.DescricaoUnidade "
                    + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON d.IdUsuario=u.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                    + "ON d.IdUnidEmp=e.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES AS s "
                    + "ON d.IdSolicitante=s.IdSolicitante "
                    + "WHERE d.IdCHDes='" + jIdChamadoPesquisa.getText() + "' "
                    + "AND u.NomeUsuario='" + nomeAtendente + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoDsv = new ChamadoSuporte();
                pCodigoDsv.setIdCHDes(conecta.rs.getInt("IdCHDes"));
                pCodigoDsv.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoDsv.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoDsv.setAssunto(conecta.rs.getString("AssuntoDesenvolvimento"));
                pCodigoDsv.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoDsv.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_CODIGO_dsv.add(pCodigoDsv);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_dsv;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    //PESQUISA DE DADOS POR DATA - ADMINISTRADOR
    public List<ChamadoSuporte> PESQUISAR_DATA_ADM_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_DATA_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "d.IdCHDes, "
                    + "d.DataCha, "
                    + "d.StatusCha, "
                    + "d.AssuntoDesenvolvimento, "
                    + "s.NomeSolicitante, "
                    + "e.DescricaoUnidade "
                    + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON d.IdUsuario=u.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                    + "ON d.IdUnidEmp=e.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES AS s "
                    + "ON d.IdSolicitante=s.IdSolicitante "
                    + "WHERE d.DataCha BETWEEN'" + pDATA_BUSCA_inicial + "' "
                    + "AND '" + pDATA_BUSCA_final + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pDataAdm = new ChamadoSuporte();
                pDataAdm.setIdCHDes(conecta.rs.getInt("IdCHDes"));
                pDataAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pDataAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pDataAdm.setAssunto(conecta.rs.getString("AssuntoDesenvolvimento"));
                pDataAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pDataAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_DATA_adm.add(pDataAdm);
                pTOTAL_registros++;
            }
            return LISTA_DATA_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    //PESQUISA DE DADOS POR DATA - DESENVOLVEDOR
    public List<ChamadoSuporte> PESQUISAR_DATA_DSV_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_DATA_dsv = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "d.IdCHDes, "
                    + "d.DataCha, "
                    + "d.StatusCha, "
                    + "d.AssuntoDesenvolvimento, "
                    + "s.NomeSolicitante, "
                    + "e.DescricaoUnidade "
                    + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON d.IdUsuario=u.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                    + "ON d.IdUnidEmp=e.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES AS s "
                    + "ON d.IdSolicitante=s.IdSolicitante "
                    + "WHERE d.DataCha BETWEEN'" + pDATA_BUSCA_inicial + "' "
                    + "AND '" + pDATA_BUSCA_final + "' "
                    + "AND u.NomeUsuario='" + nomeAtendente + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pDataDsv = new ChamadoSuporte();
                pDataDsv.setIdCHDes(conecta.rs.getInt("IdCHDes"));
                pDataDsv.setDataCha(conecta.rs.getDate("DataCha"));
                pDataDsv.setStatusCha(conecta.rs.getString("StatusCha"));
                pDataDsv.setAssunto(conecta.rs.getString("AssuntoDesenvolvimento"));
                pDataDsv.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pDataDsv.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_DATA_dsv.add(pDataDsv);
                pTOTAL_registros++;
            }
            return LISTA_DATA_dsv;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    //PESQUISA DE DADOS POR SOLICITANTE - ADMINISTRADOR
    public List<ChamadoSuporte> PESQUISAR_SOLI_ADM_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_SOLI_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "d.IdCHDes, "
                    + "d.DataCha, "
                    + "d.StatusCha, "
                    + "d.AssuntoDesenvolvimento, "
                    + "s.NomeSolicitante, "
                    + "e.DescricaoUnidade "
                    + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON d.IdUsuario=u.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                    + "ON d.IdUnidEmp=e.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES AS s "
                    + "ON d.IdSolicitante=s.IdSolicitante "
                    + "WHERE s.NomeSolicitante LIKE'%" + jPesqSolicitante.getText() + "%'");
            while (conecta.rs.next()) {
                ChamadoSuporte pSoliAdm = new ChamadoSuporte();
                pSoliAdm.setIdCHDes(conecta.rs.getInt("IdCHDes"));
                pSoliAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pSoliAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pSoliAdm.setAssunto(conecta.rs.getString("AssuntoDesenvolvimento"));
                pSoliAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pSoliAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_SOLI_adm.add(pSoliAdm);
                pTOTAL_registros++;
            }
            return LISTA_SOLI_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    //PESQUISA DE DADOS POR SOLICITANTE - DESENVOLVEDOR
    public List<ChamadoSuporte> PESQUISAR_SOLI_DSV_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_SOLI_dsv = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "d.IdCHDes, "
                    + "d.DataCha, "
                    + "d.StatusCha, "
                    + "d.AssuntoDesenvolvimento, "
                    + "s.NomeSolicitante, "
                    + "e.DescricaoUnidade "
                    + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON d.IdUsuario=u.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                    + "ON d.IdUnidEmp=e.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES AS s "
                    + "ON d.IdSolicitante=s.IdSolicitante "
                    + "WHERE s.NomeSolicitante LIKE'%" + jPesqSolicitante.getText() + "%' "
                    + "AND u.NomeUsuario='" + nomeAtendente + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pSoliDsv = new ChamadoSuporte();
                pSoliDsv.setIdCHDes(conecta.rs.getInt("IdCHDes"));
                pSoliDsv.setDataCha(conecta.rs.getDate("DataCha"));
                pSoliDsv.setStatusCha(conecta.rs.getString("StatusCha"));
                pSoliDsv.setAssunto(conecta.rs.getString("AssuntoDesenvolvimento"));
                pSoliDsv.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pSoliDsv.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_SOLI_dsv.add(pSoliDsv);
                pTOTAL_registros++;
            }
            return LISTA_SOLI_dsv;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    //PESQUISA DE DADOS TODOS - ADMINISTRADOR
    public List<ChamadoSuporte> PESQUISAR_TODOS_ADM_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_TODOS_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "d.IdCHDes, "
                    + "d.DataCha, "
                    + "d.StatusCha, "
                    + "d.AssuntoDesenvolvimento, "
                    + "s.NomeSolicitante, "
                    + "e.DescricaoUnidade "
                    + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON d.IdUsuario=u.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                    + "ON d.IdUnidEmp=e.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES AS s "
                    + "ON d.IdSolicitante=s.IdSolicitante");
            while (conecta.rs.next()) {
                ChamadoSuporte pTodosAdm = new ChamadoSuporte();
                pTodosAdm.setIdCHDes(conecta.rs.getInt("IdCHDes"));
                pTodosAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pTodosAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pTodosAdm.setAssunto(conecta.rs.getString("AssuntoDesenvolvimento"));
                pTodosAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pTodosAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_TODOS_adm.add(pTodosAdm);
                pTOTAL_registros++;
            }
            return LISTA_TODOS_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    //PESQUISA DE DADOS TODOS - DESENVOLVEDOR
    public List<ChamadoSuporte> PESQUISAR_TODOS_DSV_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_TODOS_dsv = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "d.IdCHDes, "
                    + "d.DataCha, "
                    + "d.StatusCha, "
                    + "d.AssuntoDesenvolvimento, "
                    + "s.NomeSolicitante, "
                    + "e.DescricaoUnidade "
                    + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON d.IdUsuario=u.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                    + "ON d.IdUnidEmp=e.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES AS s "
                    + "ON d.IdSolicitante=s.IdSolicitante "
                    + "WHERE u.NomeUsuario='" + nomeAtendente + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pTodosDsv = new ChamadoSuporte();
                pTodosDsv.setIdCHDes(conecta.rs.getInt("IdCHDes"));
                pTodosDsv.setDataCha(conecta.rs.getDate("DataCha"));
                pTodosDsv.setStatusCha(conecta.rs.getString("StatusCha"));
                pTodosDsv.setAssunto(conecta.rs.getString("AssuntoDesenvolvimento"));
                pTodosDsv.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pTodosDsv.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_TODOS_dsv.add(pTodosDsv);
                pTOTAL_registros++;
            }
            return LISTA_TODOS_dsv;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    //PESQUISA DE DADOS POR ASSUNTO - ADMINISTRADOR
    public List<ChamadoSuporte> PESQUISAR_ASSUM_ADM_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ASSUM_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "d.IdCHDes, "
                    + "d.DataCha, "
                    + "d.StatusCha, "
                    + "d.AssuntoDesenvolvimento, "
                    + "s.NomeSolicitante, "
                    + "e.DescricaoUnidade "
                    + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON d.IdUsuario=u.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                    + "ON d.IdUnidEmp=e.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES AS s "
                    + "ON d.IdSolicitante=s.IdSolicitante "
                    + "WHERE d.AssuntoDesenvolvimento LIKE '%" + jPesquisarAssunto.getText() + "%'");
            while (conecta.rs.next()) {
                ChamadoSuporte pAssumAdm = new ChamadoSuporte();
                pAssumAdm.setIdCHDes(conecta.rs.getInt("IdCHDes"));
                pAssumAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pAssumAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pAssumAdm.setAssunto(conecta.rs.getString("AssuntoDesenvolvimento"));
                pAssumAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pAssumAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_ASSUM_adm.add(pAssumAdm);
                pTOTAL_registros++;
            }
            return LISTA_ASSUM_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    //PESQUISA DE DADOS TODOS - DESENVOLVEDOR
    public List<ChamadoSuporte> PESQUISAR_ASSUM_DSV_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ASSUM_dsv = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "d.IdCHDes, "
                    + "d.DataCha, "
                    + "d.StatusCha, "
                    + "d.AssuntoDesenvolvimento, "
                    + "s.NomeSolicitante, "
                    + "e.DescricaoUnidade "
                    + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON d.IdUsuario=u.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                    + "ON d.IdUnidEmp=e.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES AS s "
                    + "ON d.IdSolicitante=s.IdSolicitante "
                    + "WHERE d.AssuntoDesenvolvimento LIKE '%" + jPesquisarAssunto.getText() + "%' "
                    + "AND u.NomeUsuario='" + nomeAtendente + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pAssumDsv = new ChamadoSuporte();
                pAssumDsv.setIdCHDes(conecta.rs.getInt("IdCHDes"));
                pAssumDsv.setDataCha(conecta.rs.getDate("DataCha"));
                pAssumDsv.setStatusCha(conecta.rs.getString("StatusCha"));
                pAssumDsv.setAssunto(conecta.rs.getString("AssuntoDesenvolvimento"));
                pAssumDsv.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pAssumDsv.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_ASSUM_dsv.add(pAssumDsv);
                pTOTAL_registros++;
            }
            return LISTA_ASSUM_dsv;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    //PESQUISA DE DADOS POR STATUS - ADMINISTRADOR
    public List<ChamadoSuporte> PESQUISAR_STATUS_ADM_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_STATUS_adm = new ArrayList<ChamadoSuporte>();
        try {
            if (jComboBoxStatus.getSelectedItem().equals("Aberto")) {
                conecta.executaSQL("SELECT "
                        + "d.IdCHDes, "
                        + "d.DataCha, "
                        + "d.StatusCha, "
                        + "d.AssuntoDesenvolvimento, "
                        + "s.NomeSolicitante, "
                        + "e.DescricaoUnidade "
                        + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                        + "INNER JOIN USUARIOS AS u "
                        + "ON d.IdUsuario=u.IdUsuario "
                        + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                        + "ON d.IdUnidEmp=e.IdUnidEmp "
                        + "INNER JOIN SOLICITANTES AS s "
                        + "ON d.IdSolicitante=s.IdSolicitante "
                        + "WHERE d.StatusCha LIKE '%" + pSTATUS_CHAMADO_aberto + "%'");
            } else if (jComboBoxStatus.getSelectedItem().equals("Fechado")) {
                conecta.executaSQL("SELECT "
                        + "d.IdCHDes, "
                        + "d.DataCha, "
                        + "d.StatusCha, "
                        + "d.AssuntoDesenvolvimento, "
                        + "s.NomeSolicitante, "
                        + "e.DescricaoUnidade "
                        + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                        + "INNER JOIN USUARIOS AS u "
                        + "ON d.IdUsuario=u.IdUsuario "
                        + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                        + "ON d.IdUnidEmp=e.IdUnidEmp "
                        + "INNER JOIN SOLICITANTES AS s "
                        + "ON d.IdSolicitante=s.IdSolicitante "
                        + "WHERE d.StatusCha LIKE '%" + pSTATUS_CHAMADO_fechado + "%'");
            } else if (jComboBoxStatus.getSelectedItem().equals("Atendimento")) {
                conecta.executaSQL("SELECT "
                        + "d.IdCHDes, "
                        + "d.DataCha, "
                        + "d.StatusCha, "
                        + "d.AssuntoDesenvolvimento, "
                        + "s.NomeSolicitante, "
                        + "e.DescricaoUnidade "
                        + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                        + "INNER JOIN USUARIOS AS u "
                        + "ON d.IdUsuario=u.IdUsuario "
                        + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                        + "ON d.IdUnidEmp=e.IdUnidEmp "
                        + "INNER JOIN SOLICITANTES AS s "
                        + "ON d.IdSolicitante=s.IdSolicitante "
                        + "WHERE d.StatusCha LIKE '%" + pSTATUS_CHAMADO_EM_atendimento + "%'");
            }
            while (conecta.rs.next()) {
                ChamadoSuporte pStatusAdm = new ChamadoSuporte();
                pStatusAdm.setIdCHDes(conecta.rs.getInt("IdCHDes"));
                pStatusAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pStatusAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pStatusAdm.setAssunto(conecta.rs.getString("AssuntoDesenvolvimento"));
                pStatusAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pStatusAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_STATUS_adm.add(pStatusAdm);
                pTOTAL_registros++;
            }
            return LISTA_STATUS_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    //PESQUISA DE DADOS POR STATUS - DESENVOLVEDOR
    public List<ChamadoSuporte> PESQUISAR_STATUS_DSV_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_STATUS_dsv = new ArrayList<ChamadoSuporte>();
        try {
            if (jComboBoxStatus.getSelectedItem().equals("Aberto")) {
                conecta.executaSQL("SELECT "
                        + "d.IdCHDes, "
                        + "d.DataCha, "
                        + "d.StatusCha, "
                        + "d.AssuntoDesenvolvimento, "
                        + "s.NomeSolicitante, "
                        + "e.DescricaoUnidade "
                        + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                        + "INNER JOIN USUARIOS AS u "
                        + "ON d.IdUsuario=u.IdUsuario "
                        + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                        + "ON d.IdUnidEmp=e.IdUnidEmp "
                        + "INNER JOIN SOLICITANTES AS s "
                        + "ON d.IdSolicitante=s.IdSolicitante "
                        + "WHERE d.StatusCha LIKE '%" + pSTATUS_CHAMADO_aberto + "%'"
                        + "AND u.NomeUsuario='" + nomeAtendente + "'");
            } else if (jComboBoxStatus.getSelectedItem().equals("Fechado")) {
                conecta.executaSQL("SELECT "
                        + "d.IdCHDes, "
                        + "d.DataCha, "
                        + "d.StatusCha, "
                        + "d.AssuntoDesenvolvimento, "
                        + "s.NomeSolicitante, "
                        + "e.DescricaoUnidade "
                        + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                        + "INNER JOIN USUARIOS AS u "
                        + "ON d.IdUsuario=u.IdUsuario "
                        + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                        + "ON d.IdUnidEmp=e.IdUnidEmp "
                        + "INNER JOIN SOLICITANTES AS s "
                        + "ON d.IdSolicitante=s.IdSolicitante "
                        + "WHERE d.StatusCha LIKE '%" + pSTATUS_CHAMADO_fechado + "%'"
                        + "AND u.NomeUsuario='" + nomeAtendente + "'");
            } else if (jComboBoxStatus.getSelectedItem().equals("Atendimento")) {
                conecta.executaSQL("SELECT "
                        + "d.IdCHDes, "
                        + "d.DataCha, "
                        + "d.StatusCha, "
                        + "d.AssuntoDesenvolvimento, "
                        + "s.NomeSolicitante, "
                        + "e.DescricaoUnidade "
                        + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                        + "INNER JOIN USUARIOS AS u "
                        + "ON d.IdUsuario=u.IdUsuario "
                        + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                        + "ON d.IdUnidEmp=e.IdUnidEmp "
                        + "INNER JOIN SOLICITANTES AS s "
                        + "ON d.IdSolicitante=s.IdSolicitante "
                        + "WHERE d.StatusCha LIKE '%" + pSTATUS_CHAMADO_EM_atendimento + "%'"
                        + "AND u.NomeUsuario='" + nomeAtendente + "'");
            }
            while (conecta.rs.next()) {
                ChamadoSuporte pStatusDsv = new ChamadoSuporte();
                pStatusDsv.setIdCHDes(conecta.rs.getInt("IdCHDes"));
                pStatusDsv.setDataCha(conecta.rs.getDate("DataCha"));
                pStatusDsv.setStatusCha(conecta.rs.getString("StatusCha"));
                pStatusDsv.setAssunto(conecta.rs.getString("AssuntoDesenvolvimento"));
                pStatusDsv.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pStatusDsv.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_STATUS_dsv.add(pStatusDsv);
                pTOTAL_registros++;
            }
            return LISTA_STATUS_dsv;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public ChamadoSuporte MOSTRAR_DADOS_chamado(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "d.IdCHDes, "
                    + "d.StatusCha, "
                    + "d.DataCha, "
                    + "NomeUsuario, "
                    + "d.IdSolicitante, "
                    + "s.NomeSolicitante, "
                    + "s.UsuarioInsert, "
                    + "e.IdUnidEmp, "
                    + "e.DescricaoUnidade, "
                    + "AssuntoDesenvolvimento "
                    + "FROM CHAMADOS_DESENVOLVIMENTO AS d "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON d.IdUsuario=d.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                    + "ON d.IdUnidEmp=e.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES AS s "
                    + "ON d.IdSolicitante=s.IdSolicitante "
                    + "WHERE d.IdCHDes='" + jIdChamadoPesquisa.getText() + "'");
            conecta.rs.first();
            objCHSup.setIdCHDes(conecta.rs.getInt("IdCHDes"));
            objCHSup.setStatusCha(conecta.rs.getString("StatusCha"));
            objCHSup.setDataCha(conecta.rs.getDate("DataCha"));
            objCHSup.setNomeAtendente(conecta.rs.getString("UsuarioInsert"));
            objCHSup.setIdSolicitante(conecta.rs.getInt("IdSolicitante"));
            objCHSup.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
            objCHSup.setIdUnidEmp(conecta.rs.getInt("IdUnidEmp"));
            objCHSup.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
            objCHSup.setAssunto(conecta.rs.getString("AssuntoDesenvolvimento"));
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objCHSup;
    }

    // ---------------------------------------- ITENS DO CHAMADO DO DESENVOLVIMENTO -----------------------------
    public List<ChamadoSuporte> PESQUISAR_ITENS_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_itens = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "i.IdItemDes, "
                    + "i.DataItemCh, "
                    + "i.HorarioInicio, "
                    + "i.HorarioTermino, "
                    + "i.TextoDesenvol, "
                    + "i.TextoSuporte "
                    + "FROM ITENS_CHAMADOS_DESENVOLVIMENTO AS i "
                    + "INNER JOIN CHAMADOS_DESENVOLVIMENTO AS d "
                    + "ON i.IdCHDes=d.IdCHDes "
                    + "WHERE i.IdCHDes='" + jIdChamado.getText() + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pItens = new ChamadoSuporte();
                pItens.setIdItemDes(conecta.rs.getInt("IdItemDes"));
                pItens.setDataItemCh(conecta.rs.getDate("DataItemCh"));
                pItens.setHorarioInicio(conecta.rs.getString("HorarioInicio"));
                pItens.setHorarioTermino(conecta.rs.getString("HorarioTermino"));
                pItens.setTextoDesenvol(conecta.rs.getString("TextoDesenvol"));
                pItens.setTextoSuporte(conecta.rs.getString("TextoSuporte"));
                LISTA_itens.add(pItens);
                pTOTAL_registros++;
            }
            return LISTA_itens;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public ChamadoSuporte MOSTRAR_ITENS_desenvolvimento(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "i.IdItemDes, "
                    + "i.IdItem, "
                    + "i.DataItemCh, "
                    + "i.HorarioInicio, "
                    + "i.HorarioTermino, "
                    + "s.DescricaoSoftware, "
                    + "m.DescricaoModulo, "
                    + "i.TextoDesenvol "
                    + "FROM ITENS_CHAMADOS_DESENVOLVIMENTO AS i "
                    + "INNER JOIN SOFTWARE AS s "
                    + "ON i.IdSoftware=s.IdSoftware "
                    + "INNER JOIN MODULOS m "
                    + "ON i.IdModulo=m.IdModulo "
                    + "INNER JOIN ITENS_CHAMADOS_SUPORTE AS p "
                    + "ON i.IdItem=p.IdItem "
                    + "WHERE i.IdItemDes='" + jIdItem.getText() + "'");
            conecta.rs.first();
            objCHSup.setIdItemDes(conecta.rs.getInt("IdItemDes"));
            objCHSup.setIdItemCh(conecta.rs.getInt("IdItem"));
            objCHSup.setDataItemCh(conecta.rs.getDate("DataItemCh"));
            objCHSup.setHorarioInicio(conecta.rs.getString("HorarioInicio"));
            objCHSup.setHorarioTermino(conecta.rs.getString("HorarioTermino"));
            objCHSup.setDescricaoSoftware(conecta.rs.getString("DescricaoSoftware"));
            objCHSup.setDescricaoModulo(conecta.rs.getString("DescricaoModulo"));
            objCHSup.setTextoDesenvol(conecta.rs.getString("TextoDesenvol"));
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte MOSTRAR_ITENS_suporte(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "p.IdItem, "
                    + "p.DataItemCh, "
                    + "p.HorarioInicio, "
                    + "p.HorarioTermino, "
                    + "s.DescricaoSoftware, "
                    + "m.DescricaoModulo, "
                    + "p.TextoSuporte "
                    + "FROM ITENS_CHAMADOS_SUPORTE AS p "
                    + "INNER JOIN SOFTWARE AS s "
                    + "ON p.IdSoftware=s.IdSoftware "
                    + "INNER JOIN MODULOS AS m "
                    + "ON p.IdModulo=m.IdModulo "
                    + "INNER JOIN ITENS_CHAMADOS_DESENVOLVIMENTO AS i "
                    + "ON p.IdItem=i.IdItem "
                    + "WHERE p.IdItem='" + idItemCHSup + "'");
            conecta.rs.first();
            objCHSup.setIdItemCh(conecta.rs.getInt("IdItem"));
            objCHSup.setDataItemCh(conecta.rs.getDate("DataItemCh"));
            objCHSup.setHorarioInicio(conecta.rs.getString("HorarioInicio"));
            objCHSup.setHorarioTermino(conecta.rs.getString("HorarioTermino"));
            objCHSup.setDescricaoSoftware(conecta.rs.getString("DescricaoSoftware"));
            objCHSup.setDescricaoModulo(conecta.rs.getString("DescricaoModulo"));
            objCHSup.setTextoSuporte(conecta.rs.getString("TextoSuporte"));
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte VERIFICAR_NIVEL_acesso(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "NomeUsuario, "
                    + "NivelUsuario "
                    + "FROM USUARIOS "
                    + "WHERE NomeUsuario='" + nameUser + "'");
            conecta.rs.first();
            objCHSup.setNivelAcessoUsuario(conecta.rs.getInt("NivelUsuario"));
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte BUSCAR_CODIGO_chamado(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHDes "
                    + "FROM CHAMADOS_DESENVOLVIMENTO");
            conecta.rs.last();
            jIdChamado.setText(conecta.rs.getString("IdCHDes"));
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte BUSCAR_CODIGO_item(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdItem, "
                    + "IdItemDes "
                    + "FROM ITENS_CHAMADOS_DESENVOLVIMENTO");
            conecta.rs.last();
            objCHSup.setIdItemCh(conecta.rs.getInt("IdItem"));
            objCHSup.setIdItemDes(conecta.rs.getInt("IdItemDes"));
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte VERIFICAR_GRAVACAO_Item(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHDes, "
                    + "IdItemDes "
                    + "FROM ITENS_CHAMADOS_DESENVOLVIMENTO "
                    + "WHERE IdCHDes='" + jIdChamado.getText() + "' "
                    + "AND IdItemDes='" + jIdItem.getText() + "'");
            conecta.rs.first();
            objCHSup.setIdCHDes(conecta.rs.getInt("IdCHDes"));
            objCHSup.setIdItemDes(conecta.rs.getInt("IdItemDes"));
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte VERIFICAR_SOFTWARE_Mododulo(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "i.IdCHDes, "
                    + "s.IdSoftware, "
                    + "s.DescricaoSoftware, "
                    + "m.IdModulo, "
                    + "m.DescricaoModulo "
                    + "FROM ITENS_CHAMADOS_DESENVOLVIMENTO AS i "
                    + "INNER JOIN SOFTWARE AS s "
                    + "ON i.IdSoftware=s.IdSoftware "
                    + "INNER JOIN MODULOS AS m "
                    + "ON i.IdModulo=m.IdModulo "
                    + "WHERE i.IdCHDes='" + jIdChamado.getText() + "'");
            conecta.rs.first();
            objCHSup.setIdSoftware(conecta.rs.getInt("IdSoftware"));
            objCHSup.setDescricaoSoftware(conecta.rs.getString("DescricaoSoftware"));
            objCHSup.setIdModulo(conecta.rs.getInt("IdModulo"));
            objCHSup.setDescricaoModulo(conecta.rs.getString("DescricaoModulo"));
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objCHSup;
    }

    //CONTABILIZAÇÃO DOS CHAMADOS DO DESENVOLVIMENTO 
    public List<ChamadoSuporte> QUANDIDADE_CHAMADOS_ABERTO_DSV_ATENDENTE_read() throws Exception {
        pTOTAL_REGISTROS_DSV_aberto = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ITENS_ABERTO_DSV_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHDes, "
                    + "StatusCha, "
                    + "DataCha, "
                    + "NomeUsuario "
                    + "FROM CHAMADOS_DESENVOLVIMENTO "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_DESENVOLVIMENTO.IdUsuario=USUARIOS.IdUsuario "
                    + "WHERE NomeUsuario='" + nameUser + "' "
                    + "AND StatusCha LIKE'%" + pSTATUS_CHAMADO_aberto + "%'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                pITENS_chamado.setIdCHSup(conecta.rs.getInt("IdCHDes"));
                pITENS_chamado.setStatusCha(conecta.rs.getString("StatusCha"));
                pITENS_chamado.setNomeSolicitante(conecta.rs.getString("NomeUsuario"));
                LISTA_ITENS_ABERTO_DSV_chamado.add(pITENS_chamado);
                pTOTAL_REGISTROS_DSV_aberto++;
            }
            return LISTA_ITENS_ABERTO_DSV_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> QUANDIDADE_CHAMADOS_FECHADO_DSV_ATENDENTE_read() throws Exception {
        pTOTAL_REGISTROS_DSV_fechado = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ITENS_FECHADO_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHDes, "
                    + "StatusCha, "
                    + "DataCha, "
                    + "NomeUsuario "
                    + "FROM CHAMADOS_DESENVOLVIMENTO "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_DESENVOLVIMENTO.IdUsuario=USUARIOS.IdUsuario "
                    + "WHERE NomeUsuario='" + nameUser + "' "
                    + "AND StatusCha LIKE'%" + pSTATUS_CHAMADO_fechado + "%'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                pITENS_chamado.setIdCHSup(conecta.rs.getInt("IdCHDes"));
                pITENS_chamado.setStatusCha(conecta.rs.getString("StatusCha"));
                pITENS_chamado.setNomeSolicitante(conecta.rs.getString("NomeUsuario"));
                LISTA_ITENS_FECHADO_chamado.add(pITENS_chamado);
                pTOTAL_REGISTROS_DSV_fechado++;
            }
            return LISTA_ITENS_FECHADO_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> QUANDIDADE_CHAMADOS_DSV_EM_ATENDENTE_read() throws Exception {
        pTOTAL_REGISTROS_DSV_EM_atendimento = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ITENS_ATENDIMENTO_DSV_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHDes, "
                    + "StatusCha, "
                    + "DataCha, "
                    + "NomeUsuario "
                    + "FROM CHAMADOS_DESENVOLVIMENTO "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_DESENVOLVIMENTO.IdUsuario=USUARIOS.IdUsuario "
                    + "WHERE NomeUsuario='" + nameUser + "' "
                    + "AND StatusCha LIKE'%" + pSTATUS_CHAMADO_EM_atendimento + "%'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                pITENS_chamado.setIdCHSup(conecta.rs.getInt("IdCHDes"));
                pITENS_chamado.setStatusCha(conecta.rs.getString("StatusCha"));
                pITENS_chamado.setNomeSolicitante(conecta.rs.getString("NomeUsuario"));
                LISTA_ITENS_ATENDIMENTO_DSV_chamado.add(pITENS_chamado);
                pTOTAL_REGISTROS_DSV_EM_atendimento++;
            }
            return LISTA_ITENS_ATENDIMENTO_DSV_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> QUANDIDADE_CHAMADOS_DSV_ATENDIDOS_DIA_read() throws Exception {
        pTOTAL_REGISTROS_DSV_dia = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ITENS_ATENDIDO_DSV_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHDes, "
                    + "StatusCha, "
                    + "DataCha, "
                    + "NomeUsuario "
                    + "FROM CHAMADOS_DESENVOLVIMENTO "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_DESENVOLVIMENTO.IdUsuario=USUARIOS.IdUsuario "
                    + "WHERE NomeUsuario='" + nameUser + "' "
                    + "AND StatusCha LIKE'%" + pSTATUS_CHAMADO_fechado + "%' "
                    + "AND DataCha>='" + pDATA_DSV_pesquisa + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                pITENS_chamado.setIdCHSup(conecta.rs.getInt("IdCHDes"));
                pITENS_chamado.setStatusCha(conecta.rs.getString("StatusCha"));
                pITENS_chamado.setNomeSolicitante(conecta.rs.getString("NomeUsuario"));
                LISTA_ITENS_ATENDIDO_DSV_chamado.add(pITENS_chamado);
                pTOTAL_REGISTROS_DSV_dia++;
            }
            return LISTA_ITENS_ATENDIDO_DSV_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    //----------------------------------- BUSCAR CHAMADOS DO SUPORTE ------------------------------------------
    public List<ChamadoSuporte> BUSCAR_CHAMADO_SUPORTE_codigo() throws Exception {
        pTOTAL_REGISTROS_busca = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ATENDIMENTO_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "i.IdCHSup, "
                    + "i.Item, "
                    + "i.DataItemCh, "
                    + "i.HorarioInicio, "
                    + "i.HorarioTermino, "
                    + "s.NomeSolicitante, "
                    + "i.TextoSuporte "
                    + "FROM ITENS_CHAMADOS_SUPORTE_DESENVOLVIMENTO AS i "
                    + "INNER JOIN CHAMADOS_SUPORTE AS c "
                    + "ON i.IdCHSup=i.IdCHSup "
                    + "INNER JOIN SOLICITANTES AS s"
                    + "ON c.IdSolicitante=s.IdSolicitante "
                    + "INNER JOIN MODULOS AS m "
                    + "ON i.IdModulo=m.IdModulo "
                    + "INNER JOIN SOFTWARE AS o "
                    + "ON i.IdSoftware=o.IdSoftware  "
                    + "WHERE i.IdCHSup='" + jIdChamadoPesquisa.getText() + "' "
                    + "AND i.Utilizado='" + utilizado + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                objCHSup.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                objCHSup.setIdItemCh(conecta.rs.getInt("IdItem"));
                objCHSup.setDataItemCh(conecta.rs.getDate("DataItemCh"));
                objCHSup.setHorarioInicio(conecta.rs.getString("HorarioInicio"));
                objCHSup.setHorarioTermino(conecta.rs.getString("HorarioTermino"));
                objCHSup.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                objCHSup.setTextoSuporte(conecta.rs.getString("TextoSuporte"));
                LISTA_ATENDIMENTO_chamado.add(pITENS_chamado);
                pTOTAL_REGISTROS_busca++;
            }
            return LISTA_ATENDIMENTO_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> BUSCAR_CHAMADO_SUPORTE_todos() throws Exception {
        pTOTAL_REGISTROS_busca = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ATENDIMENTO_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "i.IdCHSup, "
                    + "i.Item, "
                    + "i.DataItemCh, "
                    + "i.HorarioInicio, "
                    + "i.HorarioTermino, "
                    + "s.NomeSolicitante, "
                    + "i.TextoSuporte "
                    + "FROM ITENS_CHAMADOS_SUPORTE_DESENVOLVIMENTO AS i "
                    + "INNER JOIN CHAMADOS_SUPORTE AS c "
                    + "ON i.IdCHSup=i.IdCHSup "
                    + "INNER JOIN SOLICITANTES AS s"
                    + "ON c.IdSolicitante=s.IdSolicitante "
                    + "INNER JOIN MODULOS AS m "
                    + "ON i.IdModulo=m.IdModulo "
                    + "INNER JOIN SOFTWARE AS o "
                    + "ON i.IdSoftware=o.IdSoftware  "
                    + "WHERE i.Utilizado='" + utilizado + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                objCHSup.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                objCHSup.setIdItemCh(conecta.rs.getInt("IdItem"));
                objCHSup.setDataItemCh(conecta.rs.getDate("DataItemCh"));
                objCHSup.setHorarioInicio(conecta.rs.getString("HorarioInicio"));
                objCHSup.setHorarioTermino(conecta.rs.getString("HorarioTermino"));
                objCHSup.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                objCHSup.setTextoSuporte(conecta.rs.getString("TextoSuporte"));
                LISTA_ATENDIMENTO_chamado.add(pITENS_chamado);
                pTOTAL_REGISTROS_busca++;
            }
            return LISTA_ATENDIMENTO_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> BUSCAR_CHAMADO_SUPORTE_data() throws Exception {
        pTOTAL_REGISTROS_busca = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ATENDIMENTO_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "i.IdCHSup, "
                    + "i.Item, "
                    + "i.DataItemCh, "
                    + "i.HorarioInicio, "
                    + "i.HorarioTermino, "
                    + "s.NomeSolicitante, "
                    + "i.TextoSuporte "
                    + "FROM ITENS_CHAMADOS_SUPORTE_DESENVOLVIMENTO AS i "
                    + "INNER JOIN CHAMADOS_SUPORTE AS c "
                    + "ON i.IdCHSup=i.IdCHSup "
                    + "INNER JOIN SOLICITANTES AS s"
                    + "ON c.IdSolicitante=s.IdSolicitante "
                    + "INNER JOIN MODULOS AS m "
                    + "ON i.IdModulo=m.IdModulo "
                    + "INNER JOIN SOFTWARE AS o "
                    + "ON i.IdSoftware=o.IdSoftware "
                    + "WHERE i.DataItemCh BETWEEN'" + pDATA_BUSCA_inicial + "' "
                    + "AND '" + pDATA_BUSCA_final + "' "
                    + "AND i.Utilizado='" + utilizado + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                objCHSup.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                objCHSup.setIdItemCh(conecta.rs.getInt("IdItem"));
                objCHSup.setDataItemCh(conecta.rs.getDate("DataItemCh"));
                objCHSup.setHorarioInicio(conecta.rs.getString("HorarioInicio"));
                objCHSup.setHorarioTermino(conecta.rs.getString("HorarioTermino"));
                objCHSup.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                objCHSup.setTextoSuporte(conecta.rs.getString("TextoSuporte"));
                LISTA_ATENDIMENTO_chamado.add(pITENS_chamado);
                pTOTAL_REGISTROS_busca++;
            }
            return LISTA_ATENDIMENTO_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> BUSCAR_CHAMADO_SUPORTE_solicitante() throws Exception {
        pTOTAL_REGISTROS_busca = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ATENDIMENTO_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "i.IdCHSup, "
                    + "i.Item, "
                    + "i.DataItemCh, "
                    + "i.HorarioInicio, "
                    + "i.HorarioTermino, "
                    + "s.NomeSolicitante, "
                    + "i.TextoSuporte "
                    + "FROM ITENS_CHAMADOS_SUPORTE_DESENVOLVIMENTO AS i "
                    + "INNER JOIN CHAMADOS_SUPORTE AS c "
                    + "ON i.IdCHSup=i.IdCHSup "
                    + "INNER JOIN SOLICITANTES AS s"
                    + "ON c.IdSolicitante=s.IdSolicitante "
                    + "INNER JOIN MODULOS AS m "
                    + "ON i.IdModulo=m.IdModulo "
                    + "INNER JOIN SOFTWARE AS o "
                    + "ON i.IdSoftware=o.IdSoftware "
                    + "WHERE SOLICITANTES.NomeSolicitante LIKE'%" + jPesqSolicitante.getText() + "%' "
                    + "AND i.Utilizado='" + utilizado + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                objCHSup.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                objCHSup.setIdItemCh(conecta.rs.getInt("IdItem"));
                objCHSup.setDataItemCh(conecta.rs.getDate("DataItemCh"));
                objCHSup.setHorarioInicio(conecta.rs.getString("HorarioInicio"));
                objCHSup.setHorarioTermino(conecta.rs.getString("HorarioTermino"));
                objCHSup.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                objCHSup.setTextoSuporte(conecta.rs.getString("TextoSuporte"));
                LISTA_ATENDIMENTO_chamado.add(pITENS_chamado);
                pTOTAL_REGISTROS_busca++;
            }
            return LISTA_ATENDIMENTO_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public ChamadoSuporte MOSTRAR_RESULTADO_busca(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "i.IdItem, "
                    + "i.DataItemCh, "
                    + "i.HorarioInicio, "
                    + "i.HorarioTermino, "
                    + "i.IdSoftware, "
                    + "o.DescricaoSoftware, "
                    + "i.IdModulo, "
                    + "m.DescricaoModulo, "
                    + "i.TextoSuporte "
                    + "FROM ITENS_CHAMADOS_SUPORTE_DESENVOLVIMENTO AS i "
                    + "INNER JOIN CHAMADOS_SUPORTE AS c "
                    + "ON i.IdCHSup=c.IdCHSup "
                    + "INNER JOIN SOLICITANTES AS s "
                    + "ON c.IdSolicitante=s.IdSolicitante "
                    + "INNER JOIN MODULOS AS m "
                    + "ON i.IdModulo=m.IdModulo "
                    + "INNER JOIN SOFTWARE AS o "
                    + "ON i.IdSoftware=o.IdSoftware "
                    + "WHERE i.IdItem='" + jIdChamadoBuscaPesquisa.getText() + "'");
            conecta.rs.first();
            objCHSup.setIdItemCh(conecta.rs.getInt("IdItem"));
            objCHSup.setDataItemCh(conecta.rs.getDate("DataItemCh"));
            objCHSup.setHorarioInicio(conecta.rs.getString("HorarioInicio"));
            objCHSup.setHorarioTermino(conecta.rs.getString("HorarioTermino"));
            objCHSup.setIdSoftware(conecta.rs.getInt("IdSoftware"));
            objCHSup.setDescricaoSoftware(conecta.rs.getString("DescricaoSoftware"));
            objCHSup.setIdModulo(conecta.rs.getInt("IdModulo"));
            objCHSup.setDescricaoModulo(conecta.rs.getString("DescricaoModulo"));
            objCHSup.setTextoSuporte(conecta.rs.getString("TextoSuporte"));
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosDesenvolvimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        conecta.desconecta();
        return objCHSup;
    }
}
