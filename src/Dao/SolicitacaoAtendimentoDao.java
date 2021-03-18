/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.SolicitacaoAtendimentoUsuarios;
import static Visao.LoginHD.nameUser;
import static Visao.TelaSolicitacaoUsuario.dataFinal;
import static Visao.TelaSolicitacaoUsuario.dataInicial;
import static Visao.TelaSolicitacaoUsuario.jIdSolicitacao;
import static Visao.TelaSolicitacaoUsuario.pTOTAL_registros;
import static Visao.TelaSolicitacaoUsuario.pRESPOSTA;
import static Visao.TelaSolicitacaoUsuario.nivelUsuario;
import static Visao.TelaSolicitacaoUsuario.nomeSolicitante;
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
public class SolicitacaoAtendimentoDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    SolicitacaoAtendimentoUsuarios objSolicita = new SolicitacaoAtendimentoUsuarios();

    public SolicitacaoAtendimentoUsuarios incluirSolicitacaoAtencimento(SolicitacaoAtendimentoUsuarios objSoli) {
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO SOLICITACAO_ATENDIMENTO_USUARIOS (StatusSolicitacao,DataSolicitacao,NomeSolicitante,IdSolicitacao,NomeComputadorSolicitante,IPComputadorSolicitante,DepartamentoSolicitante,TipoSolicitacao,TextoSolicitacao,UsuarioInsert,DataInsert,HorarioInsert) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, objSoli.getStatusSolicitacao());
            pst.setTimestamp(2, new java.sql.Timestamp(objSoli.getDataSolicitacao().getTime()));
            pst.setString(3, objSoli.getNomeSolicitante());
            pst.setInt(4, objSoli.getIdSolicitacao());
            pst.setString(5, objSoli.getNomeComputadorSolicitante());
            pst.setString(6, objSoli.getiPComputadorSolicitante());
            pst.setString(7, objSoli.getDepartamentoSolicitante());
            pst.setString(8, objSoli.getTipoSolicitacao());
            pst.setString(9, objSoli.getTextoSolicitacao());
            pst.setString(10, objSoli.getUsuarioInsert());
            pst.setString(11, objSoli.getDataInsert());
            pst.setString(12, objSoli.getHorarioInsert());
            pst.execute();
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
            JOptionPane.showMessageDialog(null, "Não Foi possivel INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objSoli;
    }

    public SolicitacaoAtendimentoUsuarios alterarSolicitacaoAtencimento(SolicitacaoAtendimentoUsuarios objSoli) {
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE SOLICITACAO_ATENDIMENTO_USUARIOS SET StatusSolicitacao=?,DataSolicitacao=?,NomeSolicitante=?,IdSolicitacao=?,NomeComputadorSolicitante=?,IPComputadorSolicitante=?,DepartamentoSolicitante=?,TipoSolicitacao=?,TextoSolicitacao=?,UsuarioUp=?,DataUp=?,HorarioUp=? WHERE IdSolicitacao='" + objSoli.getIdSolicitacao() + "'");
            pst.setString(1, objSoli.getStatusSolicitacao());
            pst.setTimestamp(2, new java.sql.Timestamp(objSoli.getDataSolicitacao().getTime()));
            pst.setString(3, objSoli.getNomeSolicitante());
            pst.setInt(4, objSoli.getIdSolicitacao());
            pst.setString(5, objSoli.getNomeComputadorSolicitante());
            pst.setString(6, objSoli.getiPComputadorSolicitante());
            pst.setString(7, objSoli.getDepartamentoSolicitante());
            pst.setString(8, objSoli.getTipoSolicitacao());
            pst.setString(9, objSoli.getTextoSolicitacao());
            pst.setString(10, objSoli.getUsuarioUp());
            pst.setString(11, objSoli.getDataUp());
            pst.setString(12, objSoli.getHorarioUp());
            pst.executeUpdate();
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objSoli;
    }

    public SolicitacaoAtendimentoUsuarios excluirSolicitacaoAtencimento(SolicitacaoAtendimentoUsuarios objSoli) {
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("DELETE FROM SOLICITACAO_ATENDIMENTO_USUARIOS WHERE IdSolicitacao='" + objSoli.getIdSolicitacao() + "'");
            pst.executeUpdate();
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
            JOptionPane.showMessageDialog(null, "Não Foi possivel EXCLUIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objSoli;
    }

    public SolicitacaoAtendimentoUsuarios pBUSCAR_codigo(SolicitacaoAtendimentoUsuarios objSoli) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdSolicitacao "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS");
            conecta.rs.last();
            jIdSolicitacao.setText(conecta.rs.getString("IdSolicitacao"));
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objSoli;
    }

    public void BUSCAR_NIVEL_acesso() {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "NivelUsuario, "
                    + "NomeUsuario "
                    + "FROM USUARIOS "
                    + "WHERE NomeUsuario='" + nameUser + "'");
            conecta.rs.first();
            nivelUsuario = conecta.rs.getInt("NivelUsuario");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_TODOS_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_todos = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdSolicitacao, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdSolicitacao(conecta.rs.getInt("IdSolicitacao"));
                pTODAS_solicitacoes.setDataSolicitacao(conecta.rs.getDate("DataSolicitacao"));
                pTODAS_solicitacoes.setStatusSolicitacao(conecta.rs.getString("StatusSolicitacao"));
                pTODAS_solicitacoes.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                LISTA_todos.add(pTODAS_solicitacoes);
                pTOTAL_registros++;
            }
            return LISTA_todos;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoAtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_TODOS_SOLICITANTE01_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_soli01 = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdSolicitacao, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdSolicitacao(conecta.rs.getInt("IdSolicitacao"));
                pTODAS_solicitacoes.setDataSolicitacao(conecta.rs.getDate("DataSolicitacao"));
                pTODAS_solicitacoes.setStatusSolicitacao(conecta.rs.getString("StatusSolicitacao"));
                pTODAS_solicitacoes.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                LISTA_soli01.add(pTODAS_solicitacoes);
                pTOTAL_registros++;
            }
            return LISTA_soli01;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoAtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_TODOS_SOLICITANTE02_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_soli02 = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdSolicitacao, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE NomeSolicitante='" + nomeSolicitante + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdSolicitacao(conecta.rs.getInt("IdSolicitacao"));
                pTODAS_solicitacoes.setDataSolicitacao(conecta.rs.getDate("DataSolicitacao"));
                pTODAS_solicitacoes.setStatusSolicitacao(conecta.rs.getString("StatusSolicitacao"));
                pTODAS_solicitacoes.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                LISTA_soli02.add(pTODAS_solicitacoes);
                pTOTAL_registros++;
            }
            return LISTA_soli02;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoAtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_DATA_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_data = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdSolicitacao, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE DataSolicitacao BETWEEN'" + dataInicial + "' "
                    + "AND'" + dataFinal + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdSolicitacao(conecta.rs.getInt("IdSolicitacao"));
                pTODAS_solicitacoes.setDataSolicitacao(conecta.rs.getDate("DataSolicitacao"));
                pTODAS_solicitacoes.setStatusSolicitacao(conecta.rs.getString("StatusSolicitacao"));
                pTODAS_solicitacoes.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                LISTA_data.add(pTODAS_solicitacoes);
                pTOTAL_registros++;
            }
            return LISTA_data;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoAtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_DATA_N01_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_data = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdSolicitacao, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE DataSolicitacao BETWEEN'" + dataInicial + "' "
                    + "AND'" + dataFinal + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdSolicitacao(conecta.rs.getInt("IdSolicitacao"));
                pTODAS_solicitacoes.setDataSolicitacao(conecta.rs.getDate("DataSolicitacao"));
                pTODAS_solicitacoes.setStatusSolicitacao(conecta.rs.getString("StatusSolicitacao"));
                pTODAS_solicitacoes.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                LISTA_data.add(pTODAS_solicitacoes);
                pTOTAL_registros++;
            }
            return LISTA_data;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoAtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_DATA_N02_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_data = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdSolicitacao, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE DataSolicitacao BETWEEN'" + dataInicial + "' "
                    + "AND'" + dataFinal + "' "
                    + "AND NomeSolicitante='" + nomeSolicitante + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdSolicitacao(conecta.rs.getInt("IdSolicitacao"));
                pTODAS_solicitacoes.setDataSolicitacao(conecta.rs.getDate("DataSolicitacao"));
                pTODAS_solicitacoes.setStatusSolicitacao(conecta.rs.getString("StatusSolicitacao"));
                pTODAS_solicitacoes.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                LISTA_data.add(pTODAS_solicitacoes);
                pTOTAL_registros++;
            }
            return LISTA_data;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoAtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }
}
