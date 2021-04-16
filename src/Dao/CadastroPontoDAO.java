/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.CadastroPonto;
import static Visao.LoginHD.nameUser;
import static Visao.TelaRegistroPontoTrabalho.jIdHistoricoCU;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static Visao.TelaRegistroPontoTrabalho.pRESPOSTA_ponto;
import static Visao.TelaRegistroPontoTrabalho.pCODIGO_PESQUISA_colaborador;
import static Visao.TelaRegistroPontoTrabalho.pCODIGO_ENCONTRADO_colaborador;
import static Visao.TelaRegistroPontoTrabalho.pDATA_pesquisa;
import static Visao.TelaRegistroPontoTrabalho.pNOME_USUARIO_colaborador;
import static Visao.TelaRegistroPontoTrabalho.pDATA_cadastro;
import static Visao.TelaRegistroPontoTrabalho.qteDeRegistro;
import static Visao.TelaRegistroPontoTrabalho.pTABELA_vazia;
import static Visao.TelaRegistroPontoTrabalho.pTOTAL_registros;
import static Visao.TelaRegistroPontoTrabalho.jHoraInicial;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ronal
 */
public class CadastroPontoDAO {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    CadastroPonto objCadPonto = new CadastroPonto();

    int pCODIGO_usuario = 0;

    public CadastroPonto incluirEntradaPonto(CadastroPonto objCadPonto) {
        pBUSCA_CODIGO_usuario(objCadPonto.getNomeColaborador());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO HISTORICO_COLABORADORES (DataCadastro,IdUsuario,Periodo,StatusPonto,DataEntrada,HorarioEntrada,AssinaturaBiometriacaE,DataInsert,HorarioInsert)VALUES(?,?,?,?,?,?,?,?,?)");
            if (objCadPonto.getDataCadastro() != null) {
                pst.setTimestamp(1, new java.sql.Timestamp(objCadPonto.getDataCadastro().getTime()));
            } else {
                pst.setDate(1, null);
            }
            pst.setInt(2, pCODIGO_usuario);
            pst.setString(3, objCadPonto.getPeriodo());
            pst.setString(4, objCadPonto.getStatusPonto());
            if (objCadPonto.getDataEntrada() != null) {
                pst.setTimestamp(5, new java.sql.Timestamp(objCadPonto.getDataEntrada().getTime()));
            } else {
                pst.setDate(5, null);
            }
            pst.setString(6, objCadPonto.getHorarioEntrada());
            pst.setBytes(7, objCadPonto.getAssinaturaBiometriaca());
            pst.setString(8, objCadPonto.getDataInsert());
            pst.setString(9, objCadPonto.getHorarioInsert());
            pst.execute(); // Executa a inserção
            pRESPOSTA_ponto = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA_ponto = "Não";
        }
        conecta.desconecta();
        return objCadPonto;
    }

    public CadastroPonto incluirSaidaPonto(CadastroPonto objCadPonto) {
        pBUSCA_CODIGO_usuario(objCadPonto.getNomeColaborador());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO HISTORICO_COLABORADORES (DataCadastro,IdUsuario,Periodo,StatusPonto,DataSaida,HorarioSaida,AssinaturaBiometriacaE,DataInsert,HorarioInsert)VALUES(?,?,?,?,?,?,?,?,?)");
            if (objCadPonto.getDataCadastro() != null) {
                pst.setTimestamp(1, new java.sql.Timestamp(objCadPonto.getDataCadastro().getTime()));
            } else {
                pst.setDate(1, null);
            }
            pst.setInt(2, pCODIGO_usuario);
            pst.setString(3, objCadPonto.getPeriodo());
            pst.setString(4, objCadPonto.getStatusPonto());
            if (objCadPonto.getDataSaida() != null) {
                pst.setTimestamp(5, new java.sql.Timestamp(objCadPonto.getDataSaida().getTime()));
            } else {
                pst.setDate(5, null);
            }
            pst.setString(6, objCadPonto.getHorarioSaida());
            pst.setBytes(7, objCadPonto.getAssinaturaBiometriaca());
            pst.setString(8, objCadPonto.getDataInsert());
            pst.setString(9, objCadPonto.getHorarioInsert());
            pst.execute(); // Executa a inserção
            pRESPOSTA_ponto = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA_ponto = "Não";
        }
        conecta.desconecta();
        return objCadPonto;
    }

    public CadastroPonto alterarEntradaPonto(CadastroPonto objCadPonto) {
        pBUSCA_CODIGO_usuario(objCadPonto.getNomeColaborador());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE HISTORICO_COLABORADORES SET DataEntrada=?,"
                    + "StatusPonto=?,Periodo=?,HorarioEntrada=?,AssinaturaBiometriacaE=?,DataInsert=?,HorarioInsert=? WHERE IdHistoricoCU='" + objCadPonto.getIdHistoricoCU() + "'");
            if (objCadPonto.getDataSaida() != null) {
                pst.setTimestamp(1, new java.sql.Timestamp(objCadPonto.getDataSaida().getTime()));
            } else {
                pst.setDate(1, null);
            }
            pst.setString(2, objCadPonto.getStatusPonto());
            pst.setString(3, objCadPonto.getPeriodo());
            pst.setString(4, objCadPonto.getHorarioSaida());
            pst.setBytes(5, objCadPonto.getAssinaturaBiometriaca());
            pst.setString(6, objCadPonto.getDataInsert());
            pst.setString(7, objCadPonto.getHorarioInsert());
            pst.executeUpdate(); // Executa a inserção
            pRESPOSTA_ponto = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA_ponto = "Não";
        }
        conecta.desconecta();
        return objCadPonto;
    }
    
    public CadastroPonto alterarSaidaPonto(CadastroPonto objCadPonto) {
        pBUSCA_CODIGO_usuario(objCadPonto.getNomeColaborador());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE HISTORICO_COLABORADORES SET DataSaida=?,"
                    + "StatusPonto=?,Periodo=?,HorarioSaida=?,AssinaturaBiometriacaS=?,DataInsert=?,HorarioInsert=? WHERE IdHistoricoCU='" + objCadPonto.getIdHistoricoCU() + "'");
            if (objCadPonto.getDataSaida() != null) {
                pst.setTimestamp(1, new java.sql.Timestamp(objCadPonto.getDataSaida().getTime()));
            } else {
                pst.setDate(1, null);
            }
            pst.setString(2, objCadPonto.getStatusPonto());
            pst.setString(3, objCadPonto.getPeriodo());
            pst.setString(4, objCadPonto.getHorarioSaida());
            pst.setBytes(5, objCadPonto.getAssinaturaBiometriaca());
            pst.setString(6, objCadPonto.getDataInsert());
            pst.setString(7, objCadPonto.getHorarioInsert());
            pst.executeUpdate(); // Executa a inserção
            pRESPOSTA_ponto = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA_ponto = "Não";
        }
        conecta.desconecta();
        return objCadPonto;
    }

    public void pBUSCA_CODIGO_usuario(String nome) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "c.IdUsuario, "
                    + "u.NomeUsuario, "
                    + "c.IdColaborador "
                    + "FROM COLABORADORES AS c "
                    + "INNER JOIN USUARIOS AS u "
                    + "ON c.IdUsuario=u.IdUsuario "
                    + "WHERE u.NomeUsuario='" + nome + "'");
            conecta.rs.first();
            pCODIGO_usuario = conecta.rs.getInt("IdUsuario");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    public CadastroPonto BUSCAR_ULTIMO_codigo(CadastroPonto objCadPonto) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdHistoricoCU "
                    + "FROM HISTORICO_COLABORADORES");
            conecta.rs.last();
            jIdHistoricoCU.setText(conecta.rs.getString("IdHistoricoCU"));
        } catch (SQLException ex) {
            Logger.getLogger(CadastroPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conecta.desconecta();
        return objCadPonto;
    }

    public CadastroPonto PESQUISAR_COLABORADOR_usuario(CadastroPonto objCadPonto) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "u.IdUsuario, "
                    + "u.NomeUsuario "
                    + "FROM USUARIOS AS u "
                    + "WHERE u.NomeUsuario='" + nameUser + "'");
            conecta.rs.first();
            pCODIGO_PESQUISA_colaborador = conecta.rs.getString("IdUsuario");
            pNOME_USUARIO_colaborador = conecta.rs.getString("NomeUsuario");
        } catch (SQLException ex) {
            Logger.getLogger(CadastroPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conecta.desconecta();
        return objCadPonto;
    }

    public CadastroPonto VERIFICAR_TABELA_vazia(CadastroPonto objCadPonto) {
        conecta.abrirConexao();
        conecta.executaSQL("SELECT "
                + "IdUsuario "
                + "FROM HISTORICO_COLABORADORES "
                + "WHERE IdUsuario='" + pCODIGO_PESQUISA_colaborador + "'");
        qteDeRegistro++;
        if (qteDeRegistro == 0) {
            pTABELA_vazia = "Sim";
        } else if (qteDeRegistro > 0) {
            pTABELA_vazia = "Não";
        }
        conecta.desconecta();
        return objCadPonto;
    }

    public CadastroPonto VERIFICAR_TABELA_historico(CadastroPonto objCadPonto) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdHistoricoCU, "
                    + "IdUsuario, "
                    + "StatusPonto, "
                    + "DataCadastro, "
                    + "HorarioInsert "
                    + "FROM HISTORICO_COLABORADORES "
                    + "WHERE IdUsuario='" + pCODIGO_PESQUISA_colaborador + "' "
                    + "AND CONVERT(DATE,DataCadastro)='" + pDATA_cadastro + "'");
            conecta.rs.last();
            objCadPonto.setIdHistoricoCU(conecta.rs.getInt("IdHistoricoCU"));
            objCadPonto.setIdColaborador(conecta.rs.getInt("IdUsuario"));
            objCadPonto.setStatusPonto(conecta.rs.getString("StatusPonto"));
            objCadPonto.setDataEntrada(conecta.rs.getDate("DataCadastro"));
            objCadPonto.setHorarioInsert(conecta.rs.getString("HorarioInsert"));
        } catch (SQLException ex) {
            Logger.getLogger(CadastroPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conecta.desconecta();
        return objCadPonto;
    }
}
