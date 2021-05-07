/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Colaboradores;
import static Visao.LoginHD.pCODIGO_unidade;
import static Visao.TelaCadastroColaboradorCP.jIdColaborador;
import static Visao.TelaCadastroColaboradorCP.jIdUsuarioColaborador;
import static Visao.TelaCadastroColaboradorCP.jPesquisaUsuarioNome;
import static Visao.TelaCadastroColaboradorCP.pRESPOSTA_colaborador;
import static Visao.TelaCadastroColaboradorCP.pTOTAL_colaboradores;
import static Visao.TelaCadastroColaboradorCP.pCODIGO_colaborador;
import static Visao.TelaCadastroColaboradorCP.pDATA_final;
import static Visao.TelaCadastroColaboradorCP.pDATA_inicial;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static Visao.TelaPesquisaUsuarioColaborador.jPesquisarUsuarioColaborador;

/**
 *
 * @author ronal
 */
public class ColaboradoresDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    Colaboradores objColabora = new Colaboradores();
    //
    Integer pCODIGO_usuario = 0;
    String pSTATUS_colaborador = "Ativo";

    public Colaboradores incluirColaborador(Colaboradores objColabora) {
        pBUSCA_CODIGO_unidade(objColabora.getDescricaoUnidade());
        pBUSCA_CODIGO_usuario(objColabora.getNomeUsuario());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO COLABORADORES (StatusColaborador,DataCadastroColaborador,IdUsuario,TurmaColaborador,TurnoColaborador,CargaHorariaColaborador,HorarioInicialColaborador,HorarioTerminoColaborador,IdUnidEmp,UsuarioInsert,DataInsert,HorarioInsert)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, objColabora.getStatusColaborador());
            if (objColabora.getDataCadastroColaborador() != null) {
                pst.setTimestamp(2, new java.sql.Timestamp(objColabora.getDataCadastroColaborador().getTime()));
            } else {
                pst.setDate(2, null);
            }
            pst.setInt(3, pCODIGO_usuario);
            pst.setString(4, objColabora.getTurmaColaborador());
            pst.setString(5, objColabora.getTurnoColaborador());
            pst.setString(6, objColabora.getCargaHorariaColaborador());
            pst.setString(7, objColabora.getHorarioInicialColaborador());
            pst.setString(8, objColabora.getHorarioTerminoColaborador());
            pst.setInt(9, pCODIGO_unidade);
            pst.setString(10, objColabora.getUsuarioInsert());
            pst.setString(11, objColabora.getDataInsert());
            pst.setString(12, objColabora.getHorarioInsert());
            pst.execute(); // Executa a inserção
            pRESPOSTA_colaborador = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA_colaborador = "Não";
        }
        conecta.desconecta();
        return objColabora;
    }

    public Colaboradores alterarColaborador(Colaboradores objColabora) {
        pBUSCA_CODIGO_unidade(objColabora.getDescricaoUnidade());
        pBUSCA_CODIGO_usuario(objColabora.getNomeUsuario());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE COLABORADORES SET StatusColaborador=?, "
                    + "DataCadastroColaborador=?,IdUsuario=?,TurmaColaborador=?,TurnoColaborador=?,CargaHorariaColaborador=?, "
                    + "HorarioInicialColaborador=?,HorarioTerminoColaborador=?,IdUnidEmp=?,UsuarioUp=?,DataUp=?,HorarioUp=? "
                    + "WHERE IdColaborador='" + objColabora.getIdColaborador() + "'");
            pst.setString(1, objColabora.getStatusColaborador());
            if (objColabora.getDataCadastroColaborador() != null) {
                pst.setTimestamp(2, new java.sql.Timestamp(objColabora.getDataCadastroColaborador().getTime()));
            } else {
                pst.setDate(2, null);
            }
            pst.setInt(3, pCODIGO_usuario);
            pst.setString(4, objColabora.getCargaHorariaColaborador());
            pst.setString(5, objColabora.getHorarioInicialColaborador());
            pst.setString(6, objColabora.getHorarioTerminoColaborador());
            pst.setInt(7, pCODIGO_unidade);
            pst.setString(4, objColabora.getTurmaColaborador());
            pst.setString(5, objColabora.getTurnoColaborador());
            pst.setString(6, objColabora.getCargaHorariaColaborador());
            pst.setString(7, objColabora.getHorarioInicialColaborador());
            pst.setString(8, objColabora.getHorarioTerminoColaborador());
            pst.setInt(9, pCODIGO_unidade);
            pst.setString(10, objColabora.getUsuarioUp());
            pst.setString(11, objColabora.getDataUp());
            pst.setString(12, objColabora.getHorarioUp());
            pst.executeUpdate(); // Executa a inserção
            pRESPOSTA_colaborador = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA_colaborador = "Não";
        }
        conecta.desconecta();
        return objColabora;
    }

    public Colaboradores excluirColaborador(Colaboradores objColabora) {
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("DELETE FROM COLABORADORES "
                    + "WHERE IdColaborador='" + objColabora.getIdColaborador() + "'");
            pst.executeUpdate(); // Executa a inserção
            pRESPOSTA_colaborador = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA_colaborador = "Não";
        }
        conecta.desconecta();
        return objColabora;
    }

    //------------------------------------- PESQUISAS GERAL ------------------------------------------------------
    public List<Colaboradores> pPESQUISA_todos_read() throws Exception {
        pTOTAL_colaboradores = 0;
        List<Colaboradores> LISTA_usuarios = new ArrayList<Colaboradores>();
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "c.IdColaborador, "
                    + "c.IdUsuario, "
                    + "c.DataCadastroColaborador, "
                    + "StatusColaborador, "
                    + "u.NomeUsuario "
                    + "FROM COLABORADORES AS c "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON c.IdUsuario=u.IdUsuario");
            while (conecta.rs.next()) {
                Colaboradores objColaborador = new Colaboradores();
                objColaborador.setIdColaborador(conecta.rs.getInt("IdColaborador"));
                objColaborador.setStatusColaborador(conecta.rs.getString("StatusColaborador"));
                objColaborador.setDataCadastroColaborador(conecta.rs.getDate("DataCadastroColaborador"));
                objColaborador.setNomeUsuario(conecta.rs.getString("NomeUsuario"));
                LISTA_usuarios.add(objColaborador);
                pTOTAL_colaboradores++;
            }
            return LISTA_usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(ColaboradoresDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<Colaboradores> pPESQUISA_nome_read() throws Exception {
        pTOTAL_colaboradores = 0;
        List<Colaboradores> LISTA_usuarios = new ArrayList<Colaboradores>();
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "c.IdColaborador, "
                    + "c.IdUsuario, "
                    + "c.DataCadastroColaborador, "
                    + "StatusColaborador, "
                    + "u.NomeUsuario "
                    + "FROM COLABORADORES AS c "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON c.IdUsuario=u.IdUsuario "
                    + "WHERE u.NomeUsuario LIKE'%" + jPesquisaUsuarioNome.getText() + "%'");
            while (conecta.rs.next()) {
                Colaboradores objColaborador = new Colaboradores();
                objColaborador.setIdColaborador(conecta.rs.getInt("IdColaborador"));
                objColaborador.setStatusColaborador(conecta.rs.getString("StatusColaborador"));
                objColaborador.setDataCadastroColaborador(conecta.rs.getDate("DataCadastroColaborador"));
                objColaborador.setNomeUsuario(conecta.rs.getString("NomeUsuario"));
                LISTA_usuarios.add(objColaborador);
                pTOTAL_colaboradores++;
            }
            return LISTA_usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(ColaboradoresDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<Colaboradores> pPESQUISA_USUARIO_nome() throws Exception {
        pTOTAL_colaboradores = 0;
        List<Colaboradores> LISTA_usuarios = new ArrayList<Colaboradores>();
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "StatusUsuario, "
                    + "NomeUsuario "
                    + "FROM USUARIOS "
                    + "WHERE NomeUsuario LIKE'%" + jPesquisarUsuarioColaborador.getText() + "%' "
                    + "AND StatusUsuario='" + pSTATUS_colaborador + "'");
            while (conecta.rs.next()) {
                Colaboradores objColaborador = new Colaboradores();
                objColaborador.setIdColaborador(conecta.rs.getInt("IdUsuario"));
                objColaborador.setNomeUsuario(conecta.rs.getString("NomeUsuario"));
                LISTA_usuarios.add(objColaborador);
                pTOTAL_colaboradores++;
            }
            return LISTA_usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(ColaboradoresDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<Colaboradores> pPESQUISA_USUARIO_todos() throws Exception {
        pTOTAL_colaboradores = 0;
        List<Colaboradores> LISTA_usuarios = new ArrayList<Colaboradores>();
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "StatusUsuario, "
                    + "NomeUsuario "
                    + "FROM USUARIOS "
                    + "WHERE StatusUsuario='" + pSTATUS_colaborador + "'");
            while (conecta.rs.next()) {
                Colaboradores objColaborador = new Colaboradores();
                objColaborador.setIdColaborador(conecta.rs.getInt("IdUsuario"));
                objColaborador.setNomeUsuario(conecta.rs.getString("NomeUsuario"));
                LISTA_usuarios.add(objColaborador);
                pTOTAL_colaboradores++;
            }
            return LISTA_usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(ColaboradoresDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public Colaboradores BUSCAR_ULTIMO_codigo(Colaboradores objColaborador) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdColaborador "
                    + "FROM COLABORADORES");
            conecta.rs.last();
            jIdColaborador.setText(conecta.rs.getString("IdColaborador"));
        } catch (SQLException ex) {
            Logger.getLogger(ColaboradoresDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        conecta.desconecta();
        return objColaborador;
    }

    public Colaboradores MOSTRAR_dados(Colaboradores objColaborador) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "u.IdUsuario, "
                    + "u.NomeUsuario, "
                    + "u.NomeUsuario, "
                    + "u.SetorUsuario, "
                    + "u.CargoUsuario, "
                    + "e.IdUnidEmp, "
                    + "e.DescricaoUnidade "
                    + "FROM USUARIOS AS u "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                    + "ON u.IdUnidEmp=e.IdUnidEmp "
                    + "WHERE u.NomeUsuario='" + jPesquisarUsuarioColaborador.getText() + "'");
            conecta.rs.first();
            objColaborador.setIdColaborador(conecta.rs.getInt("IdUsuario"));
            objColaborador.setNomeUsuario(conecta.rs.getString("NomeUsuario"));
            objColaborador.setDepartamentoColaborador(conecta.rs.getString("SetorUsuario"));
            objColaborador.setCargoColaborador(conecta.rs.getString("CargoUsuario"));
            objColaborador.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível pesquisar os dados do usuário.\nERROR: " + e);
        }
        conecta.desconecta();
        return objColaborador;
    }

    public Colaboradores MOSTRAR_DADOS_selecionado(Colaboradores objColaborador) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "c.IdColaborador, "
                    + "c.StatusColaborador, "
                    + "c.DataCadastroColaborador, "
                    + "c.TurmaColaborador, "
                    + "c.TurnoColaborador, "
                    + "c.CargaHorariaColaborador, "
                    + "c.HorarioInicialColaborador, "
                    + "c.HorarioTerminoColaborador, "
                    + "c.IdUsuario, "
                    + "u.NomeUsuario, "
                    + "u.SetorUsuario, "
                    + "u.CargoUsuario, "
                    + "e.IdUnidEmp, "
                    + "e.DescricaoUnidade "
                    + "FROM COLABORADORES AS c "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON c.IdUsuario=u.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA AS e "
                    + "ON u.IdUnidEmp=e.IdUnidEmp "
                    + "WHERE u.NomeUsuario='" + jPesquisaUsuarioNome.getText() + "'");
            conecta.rs.first();
            objColaborador.setIdColaborador(conecta.rs.getInt("IdColaborador"));
            objColaborador.setStatusColaborador(conecta.rs.getString("StatusColaborador"));
            objColaborador.setDataCadastroColaborador(conecta.rs.getDate("DataCadastroColaborador"));
            objColaborador.setIdUsuario(conecta.rs.getInt("IdUsuario"));
            objColaborador.setNomeUsuario(conecta.rs.getString("NomeUsuario"));
            objColaborador.setDepartamentoColaborador(conecta.rs.getString("SetorUsuario"));
            objColaborador.setCargoColaborador(conecta.rs.getString("CargoUsuario"));
            objColaborador.setDescricaoUnidade(conecta.rs.getString("DescricaoUnidade"));
            objColaborador.setTurmaColaborador(conecta.rs.getString("TurmaColaborador"));
            objColaborador.setTurnoColaborador(conecta.rs.getString("TurnoColaborador"));
            objColaborador.setCargaHorariaColaborador(conecta.rs.getString("CargaHorariaColaborador"));
            objColaborador.setHorarioInicialColaborador(conecta.rs.getString("HorarioInicialColaborador"));
            objColaborador.setHorarioTerminoColaborador(conecta.rs.getString("HorarioTerminoColaborador"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível pesquisar os dados do usuário.\nERROR: " + e);
        }
        conecta.desconecta();
        return objColaborador;
    }

    public void pBUSCA_CODIGO_unidade(String descricao) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUnidEmp, "
                    + "DescricaoUnidade "
                    + "FROM UNIDADE_PENAL_EMPRESA "
                    + "WHERE DescricaoUnidade='" + descricao + "'");
            conecta.rs.first();
            pCODIGO_unidade = conecta.rs.getInt("IdUnidEmp");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    public void pBUSCA_CODIGO_usuario(String nome) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "SenhaUsuario "
                    + "FROM USUARIOS "
                    + "WHERE NomeUsuario='" + nome + "'");
            conecta.rs.first();
            pCODIGO_usuario = conecta.rs.getInt("IdUsuario");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    public Colaboradores PESQUISAR_COLABORADOR_historico(Colaboradores objColaborador) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdColaborador "
                    + "FROM HISTORICO_COLABORADORES "
                    + "WHERE IdColaborador='" + jIdColaborador.getText() + "'");
            conecta.rs.first();
            pCODIGO_colaborador = conecta.rs.getString("IdColaborador");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível pesquisar os dados do usuário.\nERROR: " + e);
        }
        conecta.desconecta();
        return objColaborador;
    }

    //-------------------------------------- HISTÓRICO DE PONTO --------------------------------------------
    public List<Colaboradores> pPESQUISA_HISTORICO_read() throws Exception {
        pTOTAL_colaboradores = 0;
        List<Colaboradores> LISTA_usuarios = new ArrayList<Colaboradores>();
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT DISTINCT "
                    + "IdHistoricoCU, "
                    + "DataCadastro, "
                    + "IdUsuario, "
                    + "StatusPonto, "
                    + "Periodo, "
                    + "DataEntrada, "
                    + "HorarioEntrada, "
                    + "DataSaida, "
                    + "HorarioSaida "
                    + "FROM HISTORICO_COLABORADORES "
                    + "WHERE IdUsuario='" + jIdUsuarioColaborador.getText() + "' "
                    + "AND CONVERT(DATE,DataCadastro) BETWEEN'" + pDATA_inicial + "' "
                    + "AND '" + pDATA_final + "' "
                    + "ORDER BY DataCadastro");
            while (conecta.rs.next()) {
                Colaboradores objColaborador = new Colaboradores();
                objColaborador.setIdHistorico(conecta.rs.getInt("IdHistoricoCU"));
                objColaborador.setIdColaborador(conecta.rs.getInt("IdUsuario"));
                objColaborador.setStatusPonto(conecta.rs.getString("StatusPonto"));
                objColaborador.setPeriodo(conecta.rs.getString("Periodo"));
                objColaborador.setDataEntrada(conecta.rs.getDate("DataEntrada"));
                objColaborador.setHorarioEntrada(conecta.rs.getString("HorarioEntrada"));
                objColaborador.setDataSaida(conecta.rs.getDate("DataSaida"));
                objColaborador.setHorarioSaida(conecta.rs.getString("HorarioSaida"));
                LISTA_usuarios.add(objColaborador);
                pTOTAL_colaboradores++;
            }
            return LISTA_usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(ColaboradoresDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }
}
