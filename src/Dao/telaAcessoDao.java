/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.ConexaoBancoDados;
import Modelo.TelaAcessos;
import static Visao.TelaUsuarios.idTela;
import static Visao.TelaUsuarios.jComboBoxTelaAcesso;
import static Visao.TelaUsuarios.jIdUsuario;
import static Visao.TelaUsuarios.pTOTAL_acessos;
import static Visao.TelaUsuarios.pTOTAL_usuarios;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ronaldo.silva7
 */
public class telaAcessoDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    TelaAcessos objTelaAcesso = new TelaAcessos();
    int idCod;

    public TelaAcessos incluirTelaAcesso(TelaAcessos objTelaAcesso) {
        buscarUsuarios(objTelaAcesso.getNomeUsuario(), objTelaAcesso.getIdUsuario());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO TELAS_ACESSO (IdUsuario,NomeTela,Abrir,Incluir,Alterar,Excluir,Gravar,Consultar,UsuarioInsert,DataInsert,HorarioInsert,NomeModulo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, idCod);
            pst.setString(2, objTelaAcesso.getNomeTela());
            pst.setInt(3, objTelaAcesso.getAbrir());
            pst.setInt(4, objTelaAcesso.getIncluir());
            pst.setInt(5, objTelaAcesso.getAlterar());
            pst.setInt(6, objTelaAcesso.getExcluir());
            pst.setInt(7, objTelaAcesso.getGravar());
            pst.setInt(8, objTelaAcesso.getConsultar());
            pst.setString(9, objTelaAcesso.getUsuarioInsert());
            pst.setString(10, objTelaAcesso.getDataInsert());
            pst.setString(11, objTelaAcesso.getHorarioInsert());
            pst.setString(12, objTelaAcesso.getNomeModulo());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possível INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objTelaAcesso;
    }

    public TelaAcessos alterarTelaAcesso(TelaAcessos objTelaAcesso) {
        buscarUsuarios(objTelaAcesso.getNomeUsuario(), objTelaAcesso.getIdUsuario());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE TELAS_ACESSO SET IdUsuario=?,NomeTela=?,Abrir=?,Incluir=?,Alterar=?,Excluir=?,Gravar=?,Consultar=?,UsuarioUp=?,DataUp=?,HorarioUp=?,NomeModulo=? WHERE IdTela='" + objTelaAcesso.getIdTela() + "'");
            pst.setInt(1, idCod);
            pst.setString(2, objTelaAcesso.getNomeTela());
            pst.setInt(3, objTelaAcesso.getAbrir());
            pst.setInt(4, objTelaAcesso.getIncluir());
            pst.setInt(5, objTelaAcesso.getAlterar());
            pst.setInt(6, objTelaAcesso.getExcluir());
            pst.setInt(7, objTelaAcesso.getGravar());
            pst.setInt(8, objTelaAcesso.getConsultar());
            pst.setString(9, objTelaAcesso.getUsuarioUp());
            pst.setString(10, objTelaAcesso.getDataUp());
            pst.setString(11, objTelaAcesso.getHorarioUp());
            pst.setString(12, objTelaAcesso.getNomeModulo());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possível ALTERAR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objTelaAcesso;
    }

    public TelaAcessos excluirTelaAcesso(TelaAcessos objTelaAcesso) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("DELETE FROM TELAS_ACESSO WHERE IdTela='" + objTelaAcesso.getIdTela() + "'");
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possível EXCLUIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objTelaAcesso;
    }

    public void buscarUsuarios(String nome, int codigo) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "NomeUsuario "
                    + "FROM USUARIOS "
                    + "WHERE NomeUsuario='" + nome + "' "
                    + "AND IdUsuario='" + codigo + "'");
            conecta.rs.first();
            idCod = conecta.rs.getInt("IdUsuario");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Existe dados (USUÁRIOS) a serem exibidos !!!");
        }
        conecta.desconecta();
    }

    // ---------------------------- SEGUNDA ABA -------------------------------------
    public List<TelaAcessos> pPESQUISA_ACESSO_USUARIO_read() throws Exception {
        pTOTAL_acessos = 0;
        List<TelaAcessos> LISTA_acessos = new ArrayList<TelaAcessos>();
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdTela, "
                    + "NomeTela, "
                    + "Abrir, "
                    + "Incluir, "
                    + "Alterar, "
                    + "Excluir, "
                    + "Gravar, "
                    + "Consultar, "
                    + "NomeModulo, "
                    + "IdUsuario "
                    + "FROM TELAS_ACESSO "
                    + "WHERE IdUsuario='" + jIdUsuario.getText() + "'");
            while (conecta.rs.next()) {
                TelaAcessos objTelaAcesso = new TelaAcessos();
                objTelaAcesso.setIdTela(conecta.rs.getInt("IdTela"));
                objTelaAcesso.setNomeTela(conecta.rs.getString("NomeTela"));
                objTelaAcesso.setAbrir(conecta.rs.getInt("Abrir"));
                objTelaAcesso.setIncluir(conecta.rs.getInt("Incluir"));
                objTelaAcesso.setAlterar(conecta.rs.getInt("Alterar"));
                objTelaAcesso.setExcluir(conecta.rs.getInt("Excluir"));
                objTelaAcesso.setGravar(conecta.rs.getInt("Gravar"));
                objTelaAcesso.setConsultar(conecta.rs.getInt("Consultar"));
                objTelaAcesso.setNomeModulo(conecta.rs.getString("NomeModulo"));
                objTelaAcesso.setIdUsuario(conecta.rs.getInt("IdUsuario"));
                LISTA_acessos.add(objTelaAcesso);
                pTOTAL_acessos++;
            }
            return LISTA_acessos;
        } catch (SQLException ex) {
            Logger.getLogger(telaAcessoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<TelaAcessos> pPESQUISA_ACESSO_CONSULTA_read() throws Exception {
        pTOTAL_acessos = 0;
        List<TelaAcessos> LISTA_acessos = new ArrayList<TelaAcessos>();
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "TELAS_ACESSO.IdTela, "
                    + "TELAS_ACESSO.NomeTela, "
                    + "TELAS_ACESSO.Abrir, "
                    + "TELAS_ACESSO.Incluir, "
                    + "TELAS_ACESSO.Alterar, "
                    + "TELAS_ACESSO.Excluir, "
                    + "TELAS_ACESSO.Gravar, "
                    + "TELAS_ACESSO.Consultar, "
                    + "TELAS_ACESSO.NomeModulo, "
                    + "TELAS_ACESSO.IdUsuario, "
                    + "USUARIOS.NomeUsuario "
                    + "FROM TELAS_ACESSO "
                    + "INNER JOIN USUARIOS "
                    + "ON TELAS_ACESSO.IdUsuario=USUARIOS.IdUsuario "
                    + "WHERE TELAS_ACESSO.IdTela='" + idTela + "'");
            while (conecta.rs.next()) {
                TelaAcessos objTelaAcesso = new TelaAcessos();
                objTelaAcesso.setIdTela(conecta.rs.getInt("IdTela"));
                objTelaAcesso.setNomeTela(conecta.rs.getString("NomeTela"));
                objTelaAcesso.setAbrir(conecta.rs.getInt("Abrir"));
                objTelaAcesso.setIncluir(conecta.rs.getInt("Incluir"));
                objTelaAcesso.setAlterar(conecta.rs.getInt("Alterar"));
                objTelaAcesso.setExcluir(conecta.rs.getInt("Excluir"));
                objTelaAcesso.setGravar(conecta.rs.getInt("Gravar"));
                objTelaAcesso.setConsultar(conecta.rs.getInt("Consultar"));
                objTelaAcesso.setNomeModulo(conecta.rs.getString("NomeModulo"));
                objTelaAcesso.setIdUsuario(conecta.rs.getInt("IdUsuario"));
                objTelaAcesso.setNomeUsuario(conecta.rs.getString("NomeUsuario"));
                LISTA_acessos.add(objTelaAcesso);
                pTOTAL_acessos++;
            }
            return LISTA_acessos;
        } catch (SQLException ex) {
            Logger.getLogger(telaAcessoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public TelaAcessos PESQUISAR_telas(TelaAcessos objTelaAcesso) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "NomeTela "
                    + "FROM TELAS_ACESSO "
                    + "WHERE NomeTela='" + jComboBoxTelaAcesso.getSelectedItem() + "' "
                    + "AND IdUsuario='" + jIdUsuario.getText() + "'");
            conecta.rs.first();
            objTelaAcesso.setNomeTela(conecta.rs.getString("NomeTela"));
            objTelaAcesso.setIdUsuario(conecta.rs.getInt("IdUsuario"));
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objTelaAcesso;
    }
}
