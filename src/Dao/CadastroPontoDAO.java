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
import static Visao.TelaRegistroPontoTrabalho.pTABELA_vazia;
import static Visao.TelaRegistroPontoTrabalho.pDATA_cadastro;
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
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO HISTORICO_COLABORADORES (DataCadastro,IdUsuario,Periodo,DataEntrada,HorarioEntrada,AssinaturaBiometriacaE)VALUES(?,?,?,?,?,?)");
            if (objCadPonto.getDataCadastro() != null) {
                pst.setTimestamp(1, new java.sql.Timestamp(objCadPonto.getDataCadastro().getTime()));
            } else {
                pst.setDate(1, null);
            }
            pst.setInt(2, pCODIGO_usuario);
            pst.setString(3, objCadPonto.getPeriodo());
            if (objCadPonto.getDataEntrada() != null) {
                pst.setTimestamp(4, new java.sql.Timestamp(objCadPonto.getDataEntrada().getTime()));
            } else {
                pst.setDate(4, null);
            }
            pst.setString(5, objCadPonto.getHorarioEntrada());
            pst.setBytes(6, objCadPonto.getAssinaturaBiometriaca());
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
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO HISTORICO_COLABORADORES (DataCadastro,IdUsuario,Periodo,DataSaida,HorarioSaida,AssinaturaBiometriacaE)VALUES(?,?,?,?,?,?)");
            if (objCadPonto.getDataCadastro() != null) {
                pst.setTimestamp(1, new java.sql.Timestamp(objCadPonto.getDataCadastro().getTime()));
            } else {
                pst.setDate(1, null);
            }
            pst.setInt(2, pCODIGO_usuario);
            pst.setString(3, objCadPonto.getPeriodo());
            if (objCadPonto.getDataSaida() != null) {
                pst.setTimestamp(4, new java.sql.Timestamp(objCadPonto.getDataSaida().getTime()));
            } else {
                pst.setDate(4, null);
            }
            pst.setString(5, objCadPonto.getHorarioSaida());
            pst.setBytes(6, objCadPonto.getAssinaturaBiometriaca());
            pst.execute(); // Executa a inserção
            pRESPOSTA_ponto = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA_ponto = "Não";
        }
        conecta.desconecta();
        return objCadPonto;
    }

    public CadastroPonto alterarPonto(CadastroPonto objCadPonto) {
        pBUSCA_CODIGO_usuario(objCadPonto.getNomeColaborador());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE HISTORICO_COLABORADORES SET DataSaida=?,"
                    + "Periodo=?,HorarioSaida=?,AssinaturaBiometriacaS=? WHERE IdHistoricoCU='" + objCadPonto.getIdHistoricoCU() + "'");
            if (objCadPonto.getDataSaida() != null) {
                pst.setTimestamp(1, new java.sql.Timestamp(objCadPonto.getDataSaida().getTime()));
            } else {
                pst.setDate(1, null);
            }
            pst.setString(2, objCadPonto.getPeriodo());
            pst.setString(3, objCadPonto.getHorarioSaida());
            pst.setBytes(4, objCadPonto.getAssinaturaBiometriaca());
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
            pCODIGO_PESQUISA_colaborador = conecta.rs.getInt("IdUsuario");
            pNOME_USUARIO_colaborador = conecta.rs.getString("NomeUsuario");
        } catch (SQLException ex) {
            Logger.getLogger(CadastroPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conecta.desconecta();
        return objCadPonto;
    }

    public CadastroPonto VERIFICAR_TABELA_vazia(CadastroPonto objCadPonto) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario "
                    + "FROM HISTORICO_COLABORADORES "
                    + "WHERE IdUsuario='" + pCODIGO_PESQUISA_colaborador + "'");
            conecta.rs.first();
            if (conecta.rs.next()) {
                // vazio
                pTABELA_vazia = "Sim";
            }else{
                pTABELA_vazia = "Não";
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conecta.desconecta();
        return objCadPonto;
    }

    public CadastroPonto VERIFICAR_TABELA_historico(CadastroPonto objCadPonto) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "DataEntrada "
                    + "FROM HISTORICO_COLABORADORES "
                    + "WHERE IdUsuario='" + pCODIGO_PESQUISA_colaborador + "' "
                    + "AND CONVERT(DATE,DataEntrada)='" + pDATA_cadastro + "'");
            conecta.rs.first();
            pCODIGO_ENCONTRADO_colaborador = conecta.rs.getInt("IdUsuario");
            pDATA_pesquisa = conecta.rs.getDate("DataEntrada");
            JOptionPane.showMessageDialog(null, "DATA PESQUISA: " + pDATA_pesquisa);
        } catch (SQLException ex) {
            Logger.getLogger(CadastroPontoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        conecta.desconecta();
        return objCadPonto;
    }
}
