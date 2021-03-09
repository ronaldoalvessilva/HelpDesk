/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.ChamadoSuporte;
import static Visao.LoginHD.nameUser;
import static Visao.LoginHD.pTOTAL_REGISTROS_EM_atendimento;
import static Visao.LoginHD.pTOTAL_REGISTROS_dia;
import static Visao.TelaChamadoSuporte.dataFinal;
import static Visao.TelaChamadoSuporte.dataInicial;
import static Visao.TelaChamadoSuporte.idItem;
import static Visao.TelaChamadoSuporte.idSoli;
import static Visao.TelaChamadoSuporte.pID_UNIDADE;
import static Visao.TelaChamadoSuporte.jIdChamado;
import static Visao.TelaChamadoSuporte.jIdChamadoPesquisa;
import static Visao.TelaChamadoSuporte.jPesqSolicitante;
import static Visao.TelaChamadoSuporte.jPesquisarAssunto;
import static Visao.TelaChamadoSuporte.pTOTAL_registros;
import static Visao.TelaChamadoSuporte.nomeAtendente;
import static Visao.TelaChamadoSuporte.pTOTAL_itens;
import static Visao.TelaChamadoSuporte.idItemChamado;
import static Visao.TelaChamadoSuporte.idSolicitante;
import static Visao.TelaChamadoSuporte.jComboBoxAtendente;
import static Visao.TelaChamadoSuporte.jSolicitante;
import static Visao.TelaChamadoSuporte.jUnidadePrisional;
import static Visao.TelaClienteChamadosSuporte.pTOTAL_REGISTROS_aberto;
import static Visao.TelaClienteChamadosSuporte.pTOTAL_REGISTROS_fechado;
import static Visao.TelaPrincipal.nomeUserRegistro;
import static Visao.TelaPrincipal.pDATA_pesquisa;
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
public class ChamadosSuporteDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    ChamadoSuporte objCHSup = new ChamadoSuporte();
    int codUser;
    int codEmp;
    int codUnidEmp;
    int codSoft;
    int codModu;
    int codSoli;
    int codAtendente;
    String pSTATUS_atendente = "Ativo";
    String pSTATUS_CHAMADO_aberto = "ABERTO";
    String pSTATUS_CHAMADO_fechado = "ENCERRADO";
    String pSTATUS_CHAMADO_EM_atendimento = "EM ATENDIMENTO NO SUPORTE TÉCNICO";

    public ChamadoSuporte incluirChamadoSup(ChamadoSuporte objCHSup) {
        pesquisarUsuario(objCHSup.getNomeUsuario());
        pesquisarSolicitante(objCHSup.getNomeSolicitante(), objCHSup.getIdSolicitante());
        pesquisarUnidade(objCHSup.getDescricaoUnidade());
        pesquisarAtendente(objCHSup.getNomeAtendente());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO CHAMADOS_SUPORTE (StatusCha,DataCha,IdUsuario,IdSolicitante,IdUnidEmp,UsuarioInsert,DataInsert,HorarioInsert,AssuntoSuporte,IdAtendente,TipoChamadoSuporte) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, objCHSup.getStatusCha());
            pst.setTimestamp(2, new java.sql.Timestamp(objCHSup.getDataCha().getTime()));
            pst.setInt(3, codUser);
            pst.setInt(4, codSoli);
            pst.setInt(5, codUnidEmp);
            pst.setString(6, objCHSup.getUsuarioInsert());
            pst.setString(7, objCHSup.getDataInsert());
            pst.setString(8, objCHSup.getHorarioInsert());
            pst.setString(9, objCHSup.getAssunto());
            pst.setInt(10, codAtendente);
            pst.setString(11, objCHSup.getTipoChamadoSuporte());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte alterarChamadoSup(ChamadoSuporte objCHSup) {
        pesquisarUsuario(objCHSup.getNomeUsuario());
        pesquisarAtendente(objCHSup.getNomeAtendente());
        pesquisarSolicitante(objCHSup.getNomeSolicitante(), objCHSup.getIdSolicitante());
        pesquisarUnidade(objCHSup.getDescricaoUnidade());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE CHAMADOS_SUPORTE SET StatusCha=?,DataCha=?,IdUsuario=?,IdSolicitante=?,IdUnidEmp=?,UsuarioUp=?,DataUp=?,HorarioUp=?,AssuntoSuporte=?,IdAtendente=?,TipoChamadoSuporte=? WHERE IdCHSup='" + objCHSup.getIdCHSup() + "'");
            pst.setString(1, objCHSup.getStatusCha());
            pst.setTimestamp(2, new java.sql.Timestamp(objCHSup.getDataCha().getTime()));
            pst.setInt(3, codUser);
            pst.setInt(4, codSoli);
            pst.setInt(5, codUnidEmp);
            pst.setString(6, objCHSup.getUsuarioUp());
            pst.setString(7, objCHSup.getDataUp());
            pst.setString(8, objCHSup.getHorarioUp());
            pst.setString(9, objCHSup.getAssunto());
            pst.setInt(10, codAtendente);
            pst.setString(11, objCHSup.getTipoChamadoSuporte());
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

    public void pesquisarAtendente(String nome) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdAtendente, "
                    + "NomeAtendente "
                    + "FROM ATENDENTES "
                    + "WHERE NomeAtendente='" + nome + "'");
            conecta.rs.first();
            codAtendente = conecta.rs.getInt("IdAtendente");
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
                    + " DescricaoSoftware "
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
            conecta.executaSQL("SELECT "
                    + "IdSolicitante, "
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

    public ChamadoSuporte VERIFICAR_ORIGEM_usuario(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "UsuarioInsert "
                    + "FROM CHAMADOS_SUPORTE "
                    + "WHERE IdCHSup='" + jIdChamado.getText() + "'");
            conecta.rs.first();
            nomeUserRegistro = conecta.rs.getString("UsuarioInsert");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o usuário.");
        }
        conecta.desconecta();
        return objCHSup;
    }

    public List<ChamadoSuporte> VERIFICAR_SOFTWARE_modulo_read() throws Exception {
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_software = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "IdSoftware, "
                    + "IdModulo "
                    + "FROM ITENS_CHAMADOS_SUPORTE "
                    + "WHERE IdCHSup='" + jIdChamado.getText() + "'");
            conecta.rs.first();
            while (conecta.rs.next()) {
                ChamadoSuporte pSoftware = new ChamadoSuporte();
                pSoftware.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pSoftware.setIdSoftware(conecta.rs.getInt("IdSoftware"));
                pSoftware.setIdModulo(conecta.rs.getInt("IdModulo"));
                LISTA_software.add(pSoftware);
                pTOTAL_itens++;
            }
            return LISTA_software;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public ChamadoSuporte pBUSCAR_Codigo(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup "
                    + "FROM CHAMADOS_SUPORTE");
            conecta.rs.last();
            jIdChamado.setText(conecta.rs.getString("IdCHSup"));
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte pBUSCAR_CODIGO_Item(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdItem "
                    + "FROM ITENS_CHAMADOS_SUPORTE");
            conecta.rs.last();
            idItemChamado = conecta.rs.getInt("IdItem");
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objCHSup;
    }

    public ChamadoSuporte pBUSCAR_NOME_atendente(ChamadoSuporte objCHSup) {
        jComboBoxAtendente.removeAllItems();
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "StatusAtendente, "
                    + "NomeAtendente "
                    + "FROM ATENDENTES "
                    + "WHERE StatusAtendente='" + pSTATUS_atendente + "' "
                    + "ORDER BY NomeAtendente");
            conecta.rs.first();
            do {
                jComboBoxAtendente.addItem(conecta.rs.getString("NomeAtendente"));
            } while (conecta.rs.next());
            jComboBoxAtendente.updateUI();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Existe dados a serem exibidos !!!");
        }
        conecta.desconecta();
        return objCHSup;
    }

    public List<ChamadoSuporte> VERIFICAR_SOF_mod_read() throws Exception {
        pTOTAL_itens = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_softwareMod = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "ITENS_CHAMADOS_SUPORTE.IdCHSup, "
                    + "ITENS_CHAMADOS_SUPORTE.IdSoftware, "
                    + "SOFTWARE.DescricaoSoftware, "
                    + "ITENS_CHAMADOS_SUPORTE.IdModulo, "
                    + "MODULOS.DescricaoModulo "
                    + "FROM ITENS_CHAMADOS_SUPORTE "
                    + "INNER JOIN SOFTWARE "
                    + "ON ITENS_CHAMADOS_SUPORTE.IdSoftware=SOFTWARE.IdSoftware "
                    + "INNER JOIN MODULOS "
                    + "ON ITENS_CHAMADOS_SUPORTE.IdModulo=MODULOS.IdModulo "
                    + "WHERE IdCHSup='" + jIdChamado.getText() + "'");
            conecta.rs.first();
            while (conecta.rs.next()) {
                ChamadoSuporte pSoftware = new ChamadoSuporte();
                pSoftware.setIdCHSup(conecta.rs.getInt("IdSoftware"));
                pSoftware.setDataCha(conecta.rs.getDate("DescricaoSoftware"));
                pSoftware.setStatusCha(conecta.rs.getString("IdModulo"));
                pSoftware.setAssunto(conecta.rs.getString("DescricaoModulo"));
                LISTA_softwareMod.add(pSoftware);
                pTOTAL_itens++;
            }
            return LISTA_softwareMod;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public ChamadoSuporte pBUSCAR_NOME_solicitante(ChamadoSuporte objCHSup) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "SOLICITANTES.IdSolicitante, "
                    + "SOLICITANTES.StatusSolicitante, "
                    + "SOLICITANTES.NomeSolicitante, "
                    + "SOLICITANTES.IdUnidEmp, "
                    + "DescricaoUnidade "
                    + "FROM SOLICITANTES  "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON SOLICITANTES.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "WHERE NomeSolicitante='" + nameUser + "' "
                    + "AND StatusSolicitante='" + pSTATUS_atendente + "'");
            conecta.rs.first();
            idSolicitante = conecta.rs.getInt("IdSolicitante");
            jSolicitante.setText(conecta.rs.getString("NomeSolicitante"));
            pID_UNIDADE = conecta.rs.getInt("IdUnidEmp");
            jUnidadePrisional.setText(conecta.rs.getString("DescricaoUnidade"));
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        conecta.desconecta();
        return objCHSup;
    }

    //--------------------------------- PESQUISAS DO CHAMADO DO SUPORTE -------------------------------------
    public List<ChamadoSuporte> PESQUISAR_ASSUNTOS_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_assuntos = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante  "
                    + "WHERE AssuntoSuporte LIKE'%" + jPesquisarAssunto.getText() + "%'");
            while (conecta.rs.next()) {
                ChamadoSuporte pAssuntos = new ChamadoSuporte();
                pAssuntos.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pAssuntos.setDataCha(conecta.rs.getDate("DataCha"));
                pAssuntos.setStatusCha(conecta.rs.getString("StatusCha"));
                pAssuntos.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                pAssuntos.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pAssuntos.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_assuntos.add(pAssuntos);
                pTOTAL_registros++;
            }
            return LISTA_assuntos;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> PESQUISAR_CODIGO_ADMINISTRADOR_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante  "
                    + "WHERE CHAMADOS_SUPORTE.IdCHSup='" + jIdChamadoPesquisa.getText() + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoAdm = new ChamadoSuporte();
                pCodigoAdm.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pCodigoAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoAdm.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                pCodigoAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_CODIGO_adm.add(pCodigoAdm);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> PESQUISAR_CODIGO_nivel01_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante  "
                    + "WHERE CHAMADOS_SUPORTE.IdCHSup='" + jIdChamadoPesquisa.getText() + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoAdm = new ChamadoSuporte();
                pCodigoAdm.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pCodigoAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoAdm.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                pCodigoAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_CODIGO_adm.add(pCodigoAdm);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> PESQUISAR_CODIGO_nivel2_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante  "
                    + "WHERE CHAMADOS_SUPORTE.IdCHSup='" + jIdChamadoPesquisa.getText() + "' "
                    + "AND USUARIOS.NomeUsuario='" + nomeAtendente + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoAdm = new ChamadoSuporte();
                pCodigoAdm.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pCodigoAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoAdm.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                pCodigoAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_CODIGO_adm.add(pCodigoAdm);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> PESQUISAR_SOLICITANTES_ADM_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante  "
                    + "WHERE SOLICITANTES.NomeSolicitante LIKE'%" + jPesqSolicitante.getText() + "%' ");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoAdm = new ChamadoSuporte();
                pCodigoAdm.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pCodigoAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoAdm.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                pCodigoAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_CODIGO_adm.add(pCodigoAdm);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> PESQUISAR_SOLICITANTES_NIVEL_01_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante  "
                    + "WHERE SOLICITANTES.NomeSolicitante LIKE'%" + jPesqSolicitante.getText() + "%' ");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoAdm = new ChamadoSuporte();
                pCodigoAdm.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pCodigoAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoAdm.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                pCodigoAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_CODIGO_adm.add(pCodigoAdm);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> PESQUISAR_SOLICITANTES_NIVEL_2_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante  "
                    + "WHERE SOLICITANTES.NomeSolicitante LIKE'%" + jPesqSolicitante.getText() + "%' "
                    + "AND USUARIOS.NomeUsuario='" + nomeAtendente + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoAdm = new ChamadoSuporte();
                pCodigoAdm.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pCodigoAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoAdm.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                pCodigoAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_CODIGO_adm.add(pCodigoAdm);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> PESQUISAR_TODOS_ADM_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoAdm = new ChamadoSuporte();
                pCodigoAdm.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pCodigoAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoAdm.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                pCodigoAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_CODIGO_adm.add(pCodigoAdm);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> PESQUISAR_TODOS_NIVEL01_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoAdm = new ChamadoSuporte();
                pCodigoAdm.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pCodigoAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoAdm.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                pCodigoAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_CODIGO_adm.add(pCodigoAdm);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> PESQUISAR_TODOS_NIVEL2_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                    + "WHERE USUARIOS.NomeUsuario='" + nomeAtendente + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoAdm = new ChamadoSuporte();
                pCodigoAdm.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pCodigoAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoAdm.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                pCodigoAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_CODIGO_adm.add(pCodigoAdm);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> PESQUISAR_DATAS_ADM_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                    + "WHERE DataCha BETWEEN'" + dataInicial + "' "
                    + "AND '" + dataFinal + "' ");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoAdm = new ChamadoSuporte();
                pCodigoAdm.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pCodigoAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoAdm.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                pCodigoAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_CODIGO_adm.add(pCodigoAdm);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> PESQUISAR_DATAS_NIVEL01_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                    + "WHERE DataCha BETWEEN'" + dataInicial + "' "
                    + "AND '" + dataFinal + "' ");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoAdm = new ChamadoSuporte();
                pCodigoAdm.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pCodigoAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoAdm.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                pCodigoAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_CODIGO_adm.add(pCodigoAdm);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> PESQUISAR_DATAS_NIVEL2_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                    + "WHERE DataCha BETWEEN'" + dataInicial + "' "
                    + "AND '" + dataFinal + "' "
                    + "AND USUARIOS.NomeUsuario='" + nomeAtendente + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoAdm = new ChamadoSuporte();
                pCodigoAdm.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pCodigoAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoAdm.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                pCodigoAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_CODIGO_adm.add(pCodigoAdm);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> MOSTRAR_DADOS_PESQUISADOS_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_CODIGO_adm = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "CHAMADOS_SUPORTE.IdCHSup, "
                    + "CHAMADOS_SUPORTE.DataCha, "
                    + "CHAMADOS_SUPORTE.StatusCha, "
                    + "ATENDENTES.NomeAtendente, "
                    + "CHAMADOS_SUPORTE.IdSolicitante, "
                    + "SOLICITANTES.NomeSolicitante, "
                    + "CHAMADOS_SUPORTE.IdUnidEmp, "
                    + "UNIDADE_PENAL_EMPRESA.DescricaoUnidade, "
                    + "CHAMADOS_SUPORTE.AssuntoSuporte, "
                    + "CHAMADOS_SUPORTE.TipoChamadoSuporte "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                    + "INNER JOIN ATENDENTES "
                    + "ON CHAMADOS_SUPORTE.IdAtendente=ATENDENTES.IdAtendente  "
                    + "WHERE CHAMADOS_SUPORTE.IdCHSup='" + idSoli + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pCodigoAdm = new ChamadoSuporte();
                pCodigoAdm.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pCodigoAdm.setStatusCha(conecta.rs.getString("StatusCha"));
                pCodigoAdm.setDataCha(conecta.rs.getDate("DataCha"));
                pCodigoAdm.setNomeAtendente(conecta.rs.getString("NomeAtendente"));
                pCodigoAdm.setIdSolicitante(conecta.rs.getInt("IdSolicitante"));
                pCodigoAdm.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                pCodigoAdm.setIdUnidEmp(conecta.rs.getInt("IdUnidEmp"));
                pCodigoAdm.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                pCodigoAdm.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                pCodigoAdm.setTipoChamadoSuporte(conecta.rs.getString("TipoChamadoSuporte"));
                LISTA_CODIGO_adm.add(pCodigoAdm);
                pTOTAL_registros++;
            }
            return LISTA_CODIGO_adm;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    //------------------------------------- ITENS DO CHAMDO SUPORTE -----------------------------------
    public List<ChamadoSuporte> PESQUISAR_ITENS_CHAMADO_SUP_read() throws Exception {
        pTOTAL_itens = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ITENS_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdItem, "
                    + "DataItemCh, "
                    + "HorarioInicio, "
                    + "HorarioTermino, "
                    + "TextoSuporte "
                    + "FROM ITENS_CHAMADOS_SUPORTE "
                    + "INNER JOIN CHAMADOS_SUPORTE "
                    + "ON ITENS_CHAMADOS_SUPORTE.IdCHSup=CHAMADOS_SUPORTE.IdCHSup "
                    + "WHERE ITENS_CHAMADOS_SUPORTE.IdCHSup='" + jIdChamado.getText() + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                pITENS_chamado.setIdItemCh(conecta.rs.getInt("IdItem"));
                pITENS_chamado.setDataItemCh(conecta.rs.getDate("DataItemCh"));
                pITENS_chamado.setHorarioInicio(conecta.rs.getString("HorarioInicio"));
                pITENS_chamado.setHorarioTermino(conecta.rs.getString("HorarioTermino"));
                pITENS_chamado.setTextoSuporte(conecta.rs.getString("TextoSuporte"));
                LISTA_ITENS_chamado.add(pITENS_chamado);
                pTOTAL_itens++;
            }
            return LISTA_ITENS_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> MOSTRAR_ITENS_CHAMADO_read() throws Exception {
        pTOTAL_itens = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ITENS_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdItem, "
                    + "DataItemCh, "
                    + "HorarioInicio, "
                    + "HorarioTermino, "
                    + "DescricaoSoftware, "
                    + "DescricaoModulo, "
                    + "TextoSuporte, "
                    + "TextoDesenvol, "
                    + "ImagemDocumento, "
                    + "ImagemDocumento1, "
                    + "ImagemDocumento2, "
                    + "ImagemDocumento3 "
                    + "FROM ITENS_CHAMADOS_SUPORTE "
                    + "INNER JOIN CHAMADOS_SUPORTE "
                    + "ON ITENS_CHAMADOS_SUPORTE.IdCHSup=CHAMADOS_SUPORTE.IdCHSup "
                    + "INNER JOIN SOFTWARE "
                    + "ON ITENS_CHAMADOS_SUPORTE.IdSoftware=SOFTWARE.IdSoftware "
                    + "INNER JOIN MODULOS "
                    + "ON ITENS_CHAMADOS_SUPORTE.IdModulo=MODULOS.IdModulo "
                    + "WHERE ITENS_CHAMADOS_SUPORTE.IdItem='" + idItem + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                pITENS_chamado.setIdItemCh(conecta.rs.getInt("IdItem"));
                pITENS_chamado.setDataItemCh(conecta.rs.getDate("DataItemCh"));
                pITENS_chamado.setHorarioInicio(conecta.rs.getString("HorarioInicio"));
                pITENS_chamado.setHorarioTermino(conecta.rs.getString("HorarioTermino"));
                pITENS_chamado.setDescricaoSoftware(conecta.rs.getString("DescricaoSoftware"));
                pITENS_chamado.setDescricaoModulo(conecta.rs.getString("DescricaoModulo"));
                pITENS_chamado.setTextoSuporte(conecta.rs.getString("TextoSuporte"));
                pITENS_chamado.setTextoDesenvol(conecta.rs.getString("TextoDesenvol"));
                pITENS_chamado.setImagemDocumento(conecta.rs.getBytes("ImagemDocumento"));
                pITENS_chamado.setImagemDocumento1(conecta.rs.getBytes("ImagemDocumento1"));
                pITENS_chamado.setImagemDocumento2(conecta.rs.getBytes("ImagemDocumento1"));
                pITENS_chamado.setImagemDocumento3(conecta.rs.getBytes("ImagemDocumento3"));
                LISTA_ITENS_chamado.add(pITENS_chamado);
                pTOTAL_itens++;
            }
            return LISTA_ITENS_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> QUANDIDADE_CHAMADOS_ABERTO_read() throws Exception {
        pTOTAL_REGISTROS_aberto = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ITENS_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "StatusCha, "
                    + "NomeSolicitante "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                    + "WHERE NomeSolicitante='" + nameUser + "' "
                    + "AND StatusCha LIKE'%" + pSTATUS_CHAMADO_aberto + "%'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                pITENS_chamado.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pITENS_chamado.setStatusCha(conecta.rs.getString("StatusCha"));
                pITENS_chamado.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                LISTA_ITENS_chamado.add(pITENS_chamado);
                pTOTAL_REGISTROS_aberto++;
            }
            return LISTA_ITENS_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> QUANDIDADE_CHAMADOS_FECHADO_read() throws Exception {
        pTOTAL_REGISTROS_fechado = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ITENS_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "StatusCha, "
                    + "NomeSolicitante "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                    + "WHERE NomeSolicitante='" + nameUser + "' "
                    + "AND StatusCha LIKE'%" + pSTATUS_CHAMADO_fechado + "%'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                pITENS_chamado.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pITENS_chamado.setStatusCha(conecta.rs.getString("StatusCha"));
                pITENS_chamado.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                LISTA_ITENS_chamado.add(pITENS_chamado);
                pTOTAL_REGISTROS_fechado++;
            }
            return LISTA_ITENS_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> QUANDIDADE_CHAMADOS_ABERTO_ATENDENTE_read() throws Exception {
        pTOTAL_REGISTROS_aberto = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ITENS_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "StatusCha, "
                    + "DataCha, "
                    + "NomeUsuario "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "WHERE NomeUsuario='" + nameUser + "' "
                    + "AND StatusCha LIKE'%" + pSTATUS_CHAMADO_aberto + "%'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                pITENS_chamado.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pITENS_chamado.setStatusCha(conecta.rs.getString("StatusCha"));
                pITENS_chamado.setNomeSolicitante(conecta.rs.getString("NomeUsuario"));
                LISTA_ITENS_chamado.add(pITENS_chamado);
                pTOTAL_REGISTROS_aberto++;
            }
            return LISTA_ITENS_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> QUANDIDADE_CHAMADOS_FECHADO_ATENDENTE_read() throws Exception {
        pTOTAL_REGISTROS_fechado = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ITENS_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "StatusCha, "
                    + "DataCha, "
                    + "NomeUsuario "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "WHERE NomeUsuario='" + nameUser + "' "
                    + "AND StatusCha LIKE'%" + pSTATUS_CHAMADO_fechado + "%'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                pITENS_chamado.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pITENS_chamado.setStatusCha(conecta.rs.getString("StatusCha"));
                pITENS_chamado.setNomeSolicitante(conecta.rs.getString("NomeUsuario"));
                LISTA_ITENS_chamado.add(pITENS_chamado);
                pTOTAL_REGISTROS_fechado++;
            }
            return LISTA_ITENS_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> QUANDIDADE_CHAMADOS_ATENDIDOS_DIA_read() throws Exception {
        pTOTAL_REGISTROS_dia = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ITENS_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "StatusCha, "
                    + "DataCha, "
                    + "NomeUsuario "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "WHERE NomeUsuario='" + nameUser + "' "
                    + "AND StatusCha LIKE'%" + pSTATUS_CHAMADO_fechado + "%' "
                    + "AND DataCha>='" + pDATA_pesquisa + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                pITENS_chamado.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pITENS_chamado.setStatusCha(conecta.rs.getString("StatusCha"));
                pITENS_chamado.setNomeSolicitante(conecta.rs.getString("NomeUsuario"));
                LISTA_ITENS_chamado.add(pITENS_chamado);
                pTOTAL_REGISTROS_dia++;
            }
            return LISTA_ITENS_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }
    
     public List<ChamadoSuporte> QUANDIDADE_CHAMADOS_EM_ATENDENTE_read() throws Exception {
        pTOTAL_REGISTROS_EM_atendimento = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_ITENS_chamado = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "StatusCha, "
                    + "DataCha, "
                    + "NomeUsuario "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "WHERE NomeUsuario='" + nameUser + "' "
                    + "AND StatusCha='" + pSTATUS_CHAMADO_EM_atendimento + "'");
            while (conecta.rs.next()) {
                ChamadoSuporte pITENS_chamado = new ChamadoSuporte();
                pITENS_chamado.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                pITENS_chamado.setStatusCha(conecta.rs.getString("StatusCha"));
                pITENS_chamado.setNomeSolicitante(conecta.rs.getString("NomeUsuario"));
                LISTA_ITENS_chamado.add(pITENS_chamado);
                pTOTAL_REGISTROS_EM_atendimento++;
            }
            return LISTA_ITENS_chamado;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> PESQUISAR_STATUS_aberto_read() throws Exception {
        pTOTAL_REGISTROS_dia = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_chamados = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                    + "WHERE NomeUsuario='" + nameUser + "' "
                    + "AND StatusCha LIKE'%" + pSTATUS_CHAMADO_aberto + "%' ");
            while (conecta.rs.next()) {
                ChamadoSuporte chamadoSuporte = new ChamadoSuporte();
                chamadoSuporte.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                chamadoSuporte.setDataCha(conecta.rs.getDate("DataCha"));
                chamadoSuporte.setStatusCha(conecta.rs.getString("StatusCha"));
                chamadoSuporte.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                chamadoSuporte.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                chamadoSuporte.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_chamados.add(chamadoSuporte);
                pTOTAL_REGISTROS_dia++;
            }
            return LISTA_chamados;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<ChamadoSuporte> PESQUISAR_STATUS_fechado_read() throws Exception {
        pTOTAL_REGISTROS_dia = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_chamados = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                    + "WHERE NomeUsuario='" + nameUser + "' "
                    + "AND StatusCha LIKE'%" + pSTATUS_CHAMADO_fechado + "%' ");
            while (conecta.rs.next()) {
                ChamadoSuporte chamadoSuporte = new ChamadoSuporte();
                chamadoSuporte.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                chamadoSuporte.setDataCha(conecta.rs.getDate("DataCha"));
                chamadoSuporte.setStatusCha(conecta.rs.getString("StatusCha"));
                chamadoSuporte.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                chamadoSuporte.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                chamadoSuporte.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_chamados.add(chamadoSuporte);
                pTOTAL_REGISTROS_dia++;
            }
            return LISTA_chamados;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }
    
    public List<ChamadoSuporte> PESQUISAR_STATUS_andamento_read() throws Exception {
        pTOTAL_REGISTROS_dia = 0;
        conecta.abrirConexao();
        List<ChamadoSuporte> LISTA_chamados = new ArrayList<ChamadoSuporte>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdCHSup, "
                    + "DataCha, "
                    + "StatusCha, "
                    + "AssuntoSuporte, "
                    + "NomeSolicitante, "
                    + "DescricaoUnidade "
                    + "FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                    + "WHERE NomeUsuario='" + nameUser + "' "
                    + "AND StatusCha='" + pSTATUS_CHAMADO_EM_atendimento + "' ");
            while (conecta.rs.next()) {
                ChamadoSuporte chamadoSuporte = new ChamadoSuporte();
                chamadoSuporte.setIdCHSup(conecta.rs.getInt("IdCHSup"));
                chamadoSuporte.setDataCha(conecta.rs.getDate("DataCha"));
                chamadoSuporte.setStatusCha(conecta.rs.getString("StatusCha"));
                chamadoSuporte.setAssunto(conecta.rs.getString("AssuntoSuporte"));
                chamadoSuporte.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                chamadoSuporte.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_chamados.add(chamadoSuporte);
                pTOTAL_REGISTROS_dia++;
            }
            return LISTA_chamados;
        } catch (SQLException ex) {
            Logger.getLogger(ChamadosSuporteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }
}
