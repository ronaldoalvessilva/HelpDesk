/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.SolicitacaoAtendimentoUsuarios;
import static Visao.LoginHD.nameUser;
import static Visao.LoginHD.nomeUserRegistro;
import static Visao.LoginHD.pCODIGO_unidade;
import static Visao.TelaSolicitacaoAtendimentoUsuarios.pTOTAL_REGISTROS_aberto;
import static Visao.TelaSolicitacaoAtendimentoUsuarios.pTOTAL_REGISTROS_fechado;
import static Visao.TelaSolicitacaoUsuario.dataFinal;
import static Visao.TelaSolicitacaoUsuario.dataInicial;
import static Visao.TelaSolicitacaoUsuario.idSolicitacaoTabela;
import static Visao.TelaSolicitacaoUsuario.jComboBoxNomeTecnico;
import static Visao.TelaSolicitacaoUsuario.jComboBoxStatusPesquisa;
import static Visao.TelaSolicitacaoUsuario.jComboBoxTipoSolicitacaoPesquisa;
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
import static Visao.TelaSolicitacaoUsuario.jIdRegistroSolicitante;
import static Visao.TelaSolicitacaoUsuario.jIdRegistroSolicitantePesquisa;

/**
 *
 * @author ronal
 */
public class SolicitacaoAtendimentoDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    SolicitacaoAtendimentoUsuarios objSolicita = new SolicitacaoAtendimentoUsuarios();

    Integer pCODIGO_usuario = null;
    Integer pCODIGO_solicitante = null;
    String pCARGO_tecnico = "TECNICO EM INFORMATICA";
    String pSTATUS_usuario = "Ativo";
    //
    String pSTATUS_atendente = "Ativo";
    String pSTATUS_CHAMADO_aberto = "ABERTO";
    String pSTATUS_CHAMADO_fechado = "ENCERRADO";
    String pSTATUS_CHAMADO_EM_atendimento = "EM ATENDIMENTO NO SUPORTE TÉCNICO";

    public SolicitacaoAtendimentoUsuarios incluirSolicitacaoAtencimento(SolicitacaoAtendimentoUsuarios objSolicita) {
        pBUSCAR_USUARIO_tecnico(objSolicita.getNomeTecnico());
//        pBUSCAR_USUARIO_solicitante(objSolicita.getNomeSolicitante());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO SOLICITACAO_ATENDIMENTO_USUARIOS (StatusSolicitacao,DataSolicitacao,NomeSolicitante,IdUsuario,IdUnidEmp,NomeComputadorSolicitante,IPComputadorSolicitante,DepartamentoSolicitante,TipoSolicitacao,TextoSolicitacao,UsuarioInsert,DataInsert,HorarioInsert) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, objSolicita.getStatusSolicitacao());
            pst.setTimestamp(2, new java.sql.Timestamp(objSolicita.getDataSolicitacao().getTime()));
            pst.setString(3, objSolicita.getNomeSolicitante());
            pst.setInt(4, pCODIGO_usuario);
            pst.setInt(5, objSolicita.getIdUnidade());
            pst.setString(6, objSolicita.getNomeComputadorSolicitante());
            pst.setString(7, objSolicita.getiPComputadorSolicitante());
            pst.setString(8, objSolicita.getDepartamentoSolicitante());
            pst.setString(9, objSolicita.getTipoSolicitacao());
            pst.setString(10, objSolicita.getTextoSolicitacao());
            pst.setString(11, objSolicita.getUsuarioInsert());
            pst.setString(12, objSolicita.getDataInsert());
            pst.setString(13, objSolicita.getHorarioInsert());
            pst.execute();
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
            JOptionPane.showMessageDialog(null, "Não Foi possivel INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objSolicita;
    }

    public SolicitacaoAtendimentoUsuarios alterarSolicitacaoAtencimento(SolicitacaoAtendimentoUsuarios objSolicita) {
        pBUSCAR_USUARIO_tecnico(objSolicita.getNomeTecnico());
        pBUSCAR_USUARIO_solicitante(objSolicita.getNomeSolicitante());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE SOLICITACAO_ATENDIMENTO_USUARIOS SET StatusSolicitacao=?,DataSolicitacao=?,NomeSolicitante=?,IdUsuario=?,IdUnidEmp=?,NomeComputadorSolicitante=?,IPComputadorSolicitante=?,DepartamentoSolicitante=?,TipoSolicitacao=?,TextoSolicitacao=?,UsuarioUp=?,DataUp=?,HorarioUp=? WHERE IdRegistroSolicitante='" + objSolicita.getIdRegistroSolicitante() + "'");
            pst.setString(1, objSolicita.getStatusSolicitacao());
            pst.setTimestamp(2, new java.sql.Timestamp(objSolicita.getDataSolicitacao().getTime()));
            pst.setString(3, objSolicita.getNomeSolicitante());
            pst.setInt(4, pCODIGO_usuario);
            pst.setInt(5, objSolicita.getIdUnidade());
            pst.setString(6, objSolicita.getNomeComputadorSolicitante());
            pst.setString(7, objSolicita.getiPComputadorSolicitante());
            pst.setString(8, objSolicita.getDepartamentoSolicitante());
            pst.setString(9, objSolicita.getTipoSolicitacao());
            pst.setString(10, objSolicita.getTextoSolicitacao());
            pst.setString(11, objSolicita.getUsuarioUp());
            pst.setString(12, objSolicita.getDataUp());
            pst.setString(13, objSolicita.getHorarioUp());
            pst.executeUpdate();
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objSolicita;
    }

    public SolicitacaoAtendimentoUsuarios excluirSolicitacaoAtencimento(SolicitacaoAtendimentoUsuarios objSolicita) {
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("DELETE FROM SOLICITACAO_ATENDIMENTO_USUARIOS WHERE IdRegistroSolicitante='" + objSolicita.getIdRegistroSolicitante() + "'");
            pst.executeUpdate();
            pRESPOSTA = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA = "Não";
            JOptionPane.showMessageDialog(null, "Não Foi possivel EXCLUIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objSolicita;
    }

    public void pBUSCAR_USUARIO_solicitante(String nome) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "NomeUsuario, "
                    + "SetorUsuario "
                    + "FROM USUARIOS "
                    + "WHERE NomeUsuario='" + nome + "'");
            conecta.rs.first();
            pCODIGO_solicitante = conecta.rs.getInt("IdUsuario");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    public void pBUSCAR_USUARIO_tecnico(String nome) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdSolicitante, "
                    + "NomeSolicitante "
                    + "FROM SOLICITANTES "
                    + "WHERE NomeSolicitante='" + nome + "' ");
            conecta.rs.first();
            pCODIGO_usuario = conecta.rs.getInt("IdSolicitante");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    public SolicitacaoAtendimentoUsuarios VERIFICAR_ORIGEM_usuario(SolicitacaoAtendimentoUsuarios objCHSup) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante, "
                    + "UsuarioInsert "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE IdRegistroSolicitante='" + jIdRegistroSolicitante.getText() + "'");
            conecta.rs.first();
            nomeUserRegistro = conecta.rs.getString("UsuarioInsert");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o usuário.");
        }
        conecta.desconecta();
        return objCHSup;
    }

    public SolicitacaoAtendimentoUsuarios LISTAR_TECNICOS_unidade(SolicitacaoAtendimentoUsuarios objSolicita) {
        jComboBoxNomeTecnico.removeAllItems();
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "StatusUsuario, "
                    + "NomeUsuario, "
                    + "SetorUsuario, "
                    + "CargoUsuario "
                    + "FROM USUARIOS "
                    + "WHERE CargoUsuario='" + pCARGO_tecnico + "' "
                    + "AND StatusUsuario='" + pSTATUS_usuario + "' "
                    + "AND IdUnidEmp='" + pCODIGO_unidade + "'");
            conecta.rs.first();
            do {
                jComboBoxNomeTecnico.addItem(conecta.rs.getString("NomeUsuario"));
            } while (conecta.rs.next());
            jComboBoxNomeTecnico.updateUI();
        } catch (Exception e) {
        }
        conecta.desconecta();

        return objSolicita;
    }

    public void pBUSCAR_EMPRESA_usuario(String desc) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUnidEmp, "
                    + "DescricaoUnidade "
                    + "FROM UNIDADE_PENAL_EMPRESA "
                    + "WHERE DescricaoUnidade='" + desc + "'");
            conecta.rs.first();
            pCODIGO_unidade = conecta.rs.getInt("IdUnidEmp");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    public SolicitacaoAtendimentoUsuarios pBUSCAR_codigo(SolicitacaoAtendimentoUsuarios objSolicita) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS");
            conecta.rs.last();
            jIdRegistroSolicitante.setText(conecta.rs.getString("IdRegistroSolicitante"));
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objSolicita;
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
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
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
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
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
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE NomeSolicitante='" + nomeSolicitante + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
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
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE DataSolicitacao BETWEEN'" + dataInicial + "' "
                    + "AND'" + dataFinal + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
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
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE DataSolicitacao BETWEEN'" + dataInicial + "' "
                    + "AND'" + dataFinal + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
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
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE DataSolicitacao BETWEEN'" + dataInicial + "' "
                    + "AND'" + dataFinal + "' "
                    + "AND NomeSolicitante='" + nomeSolicitante + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
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

    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_CODIGO_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_codigo01 = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE IdRegistroSolicitante='" + jIdRegistroSolicitantePesquisa.getText() + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
                pTODAS_solicitacoes.setDataSolicitacao(conecta.rs.getDate("DataSolicitacao"));
                pTODAS_solicitacoes.setStatusSolicitacao(conecta.rs.getString("StatusSolicitacao"));
                pTODAS_solicitacoes.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                LISTA_codigo01.add(pTODAS_solicitacoes);
                pTOTAL_registros++;
            }
            return LISTA_codigo01;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoAtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_CODIGO_01_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_codigo01 = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE IdRegistroSolicitante='" + jIdRegistroSolicitantePesquisa.getText() + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
                pTODAS_solicitacoes.setDataSolicitacao(conecta.rs.getDate("DataSolicitacao"));
                pTODAS_solicitacoes.setStatusSolicitacao(conecta.rs.getString("StatusSolicitacao"));
                pTODAS_solicitacoes.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                LISTA_codigo01.add(pTODAS_solicitacoes);
                pTOTAL_registros++;
            }
            return LISTA_codigo01;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoAtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_CODIGO_02_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_codigo02 = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE IdRegistroSolicitante='" + jIdRegistroSolicitantePesquisa.getText() + "' "
                    + "AND NomeSolicitante='" + nameUser + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
                pTODAS_solicitacoes.setDataSolicitacao(conecta.rs.getDate("DataSolicitacao"));
                pTODAS_solicitacoes.setStatusSolicitacao(conecta.rs.getString("StatusSolicitacao"));
                pTODAS_solicitacoes.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                LISTA_codigo02.add(pTODAS_solicitacoes);
                pTOTAL_registros++;
            }
            return LISTA_codigo02;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoAtendimentoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public SolicitacaoAtendimentoUsuarios MOSTRA_DADOS_tabela(SolicitacaoAtendimentoUsuarios objSolicita) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante, "
                    + "NomeUsuario, "
                    + "NomeComputadorSolicitante, "
                    + "IPcomputadorSolicitante, "
                    + "DepartamentoSolicitante, "
                    + "TipoSolicitacao, "
                    + "TextoSolicitacao "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "INNER JOIN USUARIOS "
                    + "ON SOLICITACAO_ATENDIMENTO_USUARIOS.IdUsuario=USUARIOS.IdUsuario "
                    + "WHERE IdRegistroSolicitante='" + idSolicitacaoTabela + "'");
            conecta.rs.first();
            objSolicita.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
            objSolicita.setStatusSolicitacao(conecta.rs.getString("StatusSolicitacao"));
            objSolicita.setDataSolicitacao(conecta.rs.getDate("DataSolicitacao"));
            objSolicita.setNomeSolicitante(conecta.rs.getString("NomeUsuario"));
            objSolicita.setNomeTecnico(conecta.rs.getString("NomeSolicitante"));
            objSolicita.setNomeComputadorSolicitante(conecta.rs.getString("NomeComputadorSolicitante"));
            objSolicita.setiPComputadorSolicitante(conecta.rs.getString("IPcomputadorSolicitante"));
            objSolicita.setDepartamentoSolicitante(conecta.rs.getString("DepartamentoSolicitante"));
            objSolicita.setTipoSolicitacao(conecta.rs.getString("TipoSolicitacao"));
            objSolicita.setTextoSolicitacao(conecta.rs.getString("TextoSolicitacao"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível exibir os dados, tente novamente.\n" + ex);
        }
        conecta.desconecta();
        return objSolicita;
    }

    public SolicitacaoAtendimentoUsuarios MOSTRA_PESQUISA_CANCELAR_operacao(SolicitacaoAtendimentoUsuarios objSolicita) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante, "
                    + "NomeUsuario, "
                    + "NomeComputadorSolicitante, "
                    + "IPcomputadorSolicitante, "
                    + "DepartamentoSolicitante, "
                    + "TipoSolicitacao, "
                    + "TextoSolicitacao "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "INNER JOIN USUARIOS "
                    + "ON SOLICITACAO_ATENDIMENTO_USUARIOS.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN SOLICITANTES "
                    + "ON SOLICITACAO_ATENDIMENTO_USUARIOS.IdSolicitante=SOLICITANTES.IdSolicitante "
                    + "WHERE IdRegistroSolicitante='" + jIdRegistroSolicitantePesquisa.getText() + "'");
            conecta.rs.first();
            objSolicita.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
            objSolicita.setStatusSolicitacao(conecta.rs.getString("StatusSolicitacao"));
            objSolicita.setDataSolicitacao(conecta.rs.getDate("DataSolicitacao"));
            objSolicita.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
            objSolicita.setNomeTecnico(conecta.rs.getString("NomeUsuario"));
            objSolicita.setNomeComputadorSolicitante(conecta.rs.getString("NomeComputadorSolicitante"));
            objSolicita.setiPComputadorSolicitante(conecta.rs.getString("IPcomputadorSolicitante"));
            objSolicita.setDepartamentoSolicitante(conecta.rs.getString("DepartamentoSolicitante"));
            objSolicita.setTipoSolicitacao(conecta.rs.getString("TipoSolicitacao"));
            objSolicita.setTextoSolicitacao(conecta.rs.getString("TextoSolicitacao"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível exibir os dados, tente novamente.\n" + ex);
        }
        conecta.desconecta();
        return objSolicita;
    }

    //---------------------------------- PESQUISA POR TIPO DE SOLICITAÇÃO --------------------------------------------
    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_TODOS_TIPO_SOLICITACAO_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_todos = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE TipoSolicitacao='" + jComboBoxTipoSolicitacaoPesquisa.getSelectedItem() + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
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

    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_TODOS_TIPO_SOLICITACAO01_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_soli01 = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE TipoSolicitacao='" + jComboBoxTipoSolicitacaoPesquisa.getSelectedItem() + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
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

    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_TODOS_TIPO_SOLICITACAO02_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_soli02 = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE NomeSolicitante='" + nomeSolicitante + "' "
                    + "TipoSolicitacao='" + jComboBoxTipoSolicitacaoPesquisa.getSelectedItem() + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
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

    //----------------------------------- PESQUISA POR STATUS ------------------------------------------------
    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_TODOS_STATUS_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_todos = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE StatusSolicitacao='" + jComboBoxStatusPesquisa.getSelectedItem() + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
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

    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_TODOS_STATUS01_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_soli01 = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE StatusSolicitacao='" + jComboBoxStatusPesquisa.getSelectedItem() + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
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

    public List<SolicitacaoAtendimentoUsuarios> PESQUISAR_TODOS_STATUS02_read() throws Exception {
        pTOTAL_registros = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_soli02 = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante, "
                    + "DataSolicitacao, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE NomeSolicitante='" + nomeSolicitante + "' "
                    + "StatusSolicitacao='" + jComboBoxStatusPesquisa.getSelectedItem() + "'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios pTODAS_solicitacoes = new SolicitacaoAtendimentoUsuarios();
                pTODAS_solicitacoes.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
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

    //----------------------------------- CHAMADOS EM ABERTO E ENCERRADOS DO USUÁRIO SOLICITANTE NAS UNIDADES 
    public List<SolicitacaoAtendimentoUsuarios> QUANDIDADE_CHAMADOS_USER_ABERTO_read() throws Exception {
        pTOTAL_REGISTROS_aberto = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_ITENS_chamado = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE NomeSolicitante='" + nameUser + "' "
                    + "AND StatusSolicitacao LIKE'%" + pSTATUS_CHAMADO_aberto + "%'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios CHAMADOS_usuario = new SolicitacaoAtendimentoUsuarios();
                CHAMADOS_usuario.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
                CHAMADOS_usuario.setStatusSolicitacao(conecta.rs.getString("StatusSolicitacao"));
                CHAMADOS_usuario.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                LISTA_ITENS_chamado.add(CHAMADOS_usuario);
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

    public List<SolicitacaoAtendimentoUsuarios> QUANDIDADE_CHAMADOS_USER_FECHADO_read() throws Exception {
        pTOTAL_REGISTROS_fechado = 0;
        conecta.abrirConexao();
        List<SolicitacaoAtendimentoUsuarios> LISTA_ITENS_chamado = new ArrayList<SolicitacaoAtendimentoUsuarios>();
        try {
            conecta.executaSQL("SELECT "
                    + "IdRegistroSolicitante, "
                    + "StatusSolicitacao, "
                    + "NomeSolicitante "
                    + "FROM SOLICITACAO_ATENDIMENTO_USUARIOS "
                    + "WHERE NomeSolicitante='" + nameUser + "' "
                    + "AND StatusCha LIKE'%" + pSTATUS_CHAMADO_fechado + "%'");
            while (conecta.rs.next()) {
                SolicitacaoAtendimentoUsuarios CHAMADOS_usuario = new SolicitacaoAtendimentoUsuarios();
                CHAMADOS_usuario.setIdRegistroSolicitante(conecta.rs.getInt("IdRegistroSolicitante"));
                CHAMADOS_usuario.setStatusSolicitacao(conecta.rs.getString("StatusSolicitacao"));
                CHAMADOS_usuario.setNomeSolicitante(conecta.rs.getString("NomeSolicitante"));
                LISTA_ITENS_chamado.add(CHAMADOS_usuario);
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
}
