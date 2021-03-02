/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Usuarios;
import Util.SQL.Utilitarios.Criptografia;
import static Visao.LoginHD.jLogin;
import static Visao.LoginHD.pCODIGO_status;
import static Visao.LoginHD.pID_USUARIO_acesso;
import static Visao.LoginHD.pLOGIN_usuario;
import static Visao.LoginHD.pSENHA_usuario;
import static Visao.LoginHD.nameUser;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static Visao.TelaTrocaSenha.pSENHA_anterior;
import static Visao.TelaTrocaSenha.pCODIGO_usuario;

/**
 *
 * @author ronal
 */
public class UsuarioDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    Usuarios user = new Usuarios();

    public Usuarios incluirUsuarios(Usuarios objUser) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO USUARIOS (StatusUsuario,DataCadastro,NomeUsuario,NivelUsuario,SetorUsuario,CargoUsuario,LoginUsuario,SenhaUsuario,SenhaUsuario1,SenhaCriptografada)VALUES(?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, objUser.getStatus());
            if (objUser.getDataCadastro() != null) {
                pst.setTimestamp(2, new java.sql.Timestamp(objUser.getDataCadastro().getTime()));
            } else {
                pst.setDate(2, null);
            }
            pst.setString(3, objUser.getNomeUsuario());
            pst.setInt(4, objUser.getNivelUsuario());
            pst.setString(5, objUser.getSetorUsuario());
            pst.setString(6, objUser.getCargoUsuario());
            pst.setString(7, objUser.getLoginUsuario());
            pst.setString(8, Criptografia.criptografar(objUser.getSenhaUsuario()));
            pst.setString(9, Criptografia.criptografar(objUser.getSenhaUsuario1()));
            pst.setBytes(10, objUser.getSenhaCriptografada());
            pst.execute(); // Executa a inserção
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possível INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objUser;
    }

    public Usuarios alterarUsuarios(Usuarios objUser) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE USUARIOS SET StatusUsuario=?,DataCadastro=?,NomeUsuario=?,NivelUsuario=?,SetorUsuario=?,CargoUsuario=?,LoginUsuario=?,SenhaUsuario=?,SenhaUsuario1=?,SenhaCriptografada=? WHERE IdUsuario='" + objUser.getIdUsuario() + "'");
            pst.setString(1, objUser.getStatus());
            if (objUser.getDataCadastro() != null) {
                pst.setTimestamp(2, new java.sql.Timestamp(objUser.getDataCadastro().getTime()));
            } else {
                pst.setDate(2, null);
            }
            pst.setString(3, objUser.getNomeUsuario());
            pst.setInt(4, objUser.getNivelUsuario());
            pst.setString(5, objUser.getSetorUsuario());
            pst.setString(6, objUser.getCargoUsuario());
            pst.setString(7, objUser.getLoginUsuario());
            pst.setString(8, Criptografia.criptografar(objUser.getSenhaUsuario()));
            pst.setString(9, Criptografia.criptografar(objUser.getSenhaUsuario1()));
            pst.setBytes(10, objUser.getSenhaCriptografada());
            pst.executeUpdate(); // Executa a inserção
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados.\n\nERRO: " + ex);
        }
        conecta.desconecta();
        return objUser;
    }

    public Usuarios excluirUsuarios(Usuarios objUser) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("DELETE FROM USUARIOS WHERE IdUsuario='" + objUser.getIdUsuario() + "'");
            pst.executeUpdate(); // Executa a inserção
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel EXCLUIR os Dados\n\nERRO: " + ex);
        }
        conecta.desconecta();
        return objUser;
    }

    public Usuarios trocarSenhaUsuario(Usuarios objUser) {

        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE USUARIOS SET SenhaUsuario=?,SenhaUsuario1=? WHERE IdUsuario='" + objUser.getIdUsuario() + "'");
            pst.setString(1, Criptografia.criptografar(objUser.getSenhaUsuario()));
            pst.setString(2, Criptografia.criptografar(objUser.getSenhaUsuario1()));
            pst.executeUpdate(); // Executa a inserção
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possivel ALTERAR os Dados.\nERRO:" + ex);
        }
        conecta.desconecta();
        return objUser;
    }

    public Usuarios pBUSCAR_usuario(Usuarios objUser) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "LoginUsuario, "
                    + "SenhaUsuario, "
                    + "StatusUsuario, "
                    + "NomeUsuario, "
                    + "SenhaCriptografada "
                    + "FROM USUARIOS "
                    + "WHERE LoginUsuario='" + jLogin.getText() + "' ");
            conecta.rs.first();
            pCODIGO_status = conecta.rs.getString("StatusUsuario");
            pID_USUARIO_acesso = conecta.rs.getString("IdUsuario");
            pLOGIN_usuario = conecta.rs.getString("LoginUsuario");
            pSENHA_usuario = conecta.rs.getString("SenhaUsuario");
            nameUser = conecta.rs.getString("NomeUsuario");
        } catch (SQLException e) {

        }
        conecta.desconecta();
        return objUser;
    }

    public Usuarios pBUSCA_CODIGO_usuario(Usuarios objUser) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "SenhaUsuario "
                    + "FROM USUARIOS "
                    + "WHERE NomeUsuario='" + nameUser + "'");
            conecta.rs.first();
            pCODIGO_usuario = conecta.rs.getInt("IdUsuario");
            pSENHA_anterior = conecta.rs.getString("SenhaUsuario");
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objUser;
    }
}
