/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.TelaAcessos;
import Modelo.Usuarios;
import Util.SQL.Utilitarios.Criptografia;
import static Visao.LoginHD.jLogin;
import static Visao.LoginHD.pCODIGO_status;
import static Visao.LoginHD.pID_USUARIO_acesso;
import static Visao.LoginHD.pLOGIN_usuario;
import static Visao.LoginHD.pSENHA_usuario;
import static Visao.LoginHD.pCLIENTE_servidor;
import static Visao.LoginHD.nameUser;
import static Visao.LoginHD.pCODIGO_unidade;
import static Visao.LoginHD.pSETOR_usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static Visao.TelaTrocaSenha.pSENHA_anterior;
import static Visao.TelaTrocaSenha.pCODIGO_usuario;
import static Visao.TelaUsuarios.jIdUsuario;
import static Visao.TelaUsuarios.jPesquisaUsuarioNome;
import static Visao.TelaUsuarios.pRESPOSTA_user;
import static Visao.TelaUsuarios.pTOTAL_usuarios;
import static Visao.TelaUsuarios.user;
import static Visao.TelaUsuarios.jComboBoxEmpresaUnidade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronal
 */
public class UsuarioDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    Usuarios usuario = new Usuarios();
    //
    int nivel = 0;
    String nivelNome = "";

    public Usuarios incluirUsuarios(Usuarios objUser) {
        pBUSCA_CODIGO_unidade(objUser.getNomeUnidade());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO USUARIOS (StatusUsuario,DataCadastro,NomeUsuario,NivelUsuario,SetorUsuario,CargoUsuario,LoginUsuario,SenhaUsuario,SenhaUsuario1,SenhaCriptografada,ClienteServidor,FotoPerfilUsuario,IdUnidEmp)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
            pst.setString(11, objUser.getClienteServidor());
            pst.setBytes(12, objUser.getFotoPerfilUsuario());
            pst.setInt(13, pCODIGO_unidade);
            pst.execute(); // Executa a inserção
            pRESPOSTA_user = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA_user = "Não";
            JOptionPane.showMessageDialog(null, "Não Foi possível INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objUser;
    }

    public Usuarios alterarUsuarios(Usuarios objUser) {
        pBUSCA_CODIGO_unidade(objUser.getNomeUnidade());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE USUARIOS SET StatusUsuario=?,DataCadastro=?,NomeUsuario=?,NivelUsuario=?,SetorUsuario=?,CargoUsuario=?,LoginUsuario=?,SenhaUsuario=?,SenhaUsuario1=?,SenhaCriptografada=?,ClienteServidor=?,FotoPerfilUsuario=?,IdUnidEmp=? WHERE IdUsuario='" + objUser.getIdUsuario() + "'");
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
            pst.setString(11, objUser.getClienteServidor());
            pst.setBytes(12, objUser.getFotoPerfilUsuario());
            pst.setInt(13, pCODIGO_unidade);
            pst.executeUpdate(); // Executa a inserção
            pRESPOSTA_user = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA_user = "Não";
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
            pRESPOSTA_user = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA_user = "Não";
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
            pRESPOSTA_user = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA_user = "Não";
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
                    + "SenhaCriptografada, "
                    + "ClienteServidor, "
                    + "IdUnidEmp, "
                    + "SetorUsuario "
                    + "FROM USUARIOS "
                    + "WHERE LoginUsuario='" + jLogin.getText() + "' ");
            conecta.rs.first();
            pCODIGO_status = conecta.rs.getString("StatusUsuario");
            pID_USUARIO_acesso = conecta.rs.getString("IdUsuario");
            pLOGIN_usuario = conecta.rs.getString("LoginUsuario");
            pSENHA_usuario = conecta.rs.getString("SenhaUsuario");
            nameUser = conecta.rs.getString("NomeUsuario");
            pCLIENTE_servidor = conecta.rs.getString("ClienteServidor");
            pCODIGO_unidade = conecta.rs.getInt("IdUnidEmp");
            pSETOR_usuario = conecta.rs.getString("SetorUsuario");
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

    //------------------------------ PESQUISAS ----------------------------------
    public Usuarios pBUSCAR_CODIGO_gravado(Usuarios objUser) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario "
                    + "FROM USUARIOS");
            conecta.rs.last();
            jIdUsuario.setText(conecta.rs.getString("IdUsuario"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível obter o código do usuário.");
        }
        conecta.desconecta();
        return objUser;
    }

    public Usuarios PESQUISAR_NOME_unidades(Usuarios objUser) {
        jComboBoxEmpresaUnidade.removeAllItems();
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "DescricaoUnidade "
                    + "FROM UNIDADE_PENAL_EMPRESA ");
            conecta.rs.first();
            do {
                jComboBoxEmpresaUnidade.addItem(conecta.rs.getString("DescricaoUnidade"));
            } while (conecta.rs.next());
            jComboBoxEmpresaUnidade.updateUI();
        } catch (SQLException ex) {
        }
        conecta.desconecta();
        return objUser;
    }

    public List<Usuarios> pPESQUISA_cancelar_read() throws Exception {
        pTOTAL_usuarios = 0;
        List<Usuarios> LISTA_usuarios = new ArrayList<Usuarios>();
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "DataCadastro, "
                    + "StatusUsuario, "
                    + "NomeUsuario, "
                    + "SetorUsuario, "
                    + "CargoUsuario, "
                    + "NivelUsuario, "
                    + "LoginUsuario, "
                    + "SenhaUsuario, "
                    + "SenhaUsuario1, "
                    + "ClienteServidor, "
                    + "FotoPerfilUsuario, "
                    + "DescricaoUnidade "
                    + "FROM USUARIOS "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON USUARIOS.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "WHERE IdUsuario='" + user.toString() + "'");
            while (conecta.rs.next()) {
                Usuarios objUser = new Usuarios();
                objUser.setIdUsuario(conecta.rs.getInt("IdUsuario"));
                objUser.setStatus(conecta.rs.getString("StatusUsuario"));
                objUser.setDataCadastro(conecta.rs.getDate("DataCadastro"));
                objUser.setNomeUsuario(conecta.rs.getString("NomeUsuario"));
                objUser.setSetorUsuario(conecta.rs.getString("SetorUsuario"));
                objUser.setCargoUsuario(conecta.rs.getString("CargoUsuario"));
                objUser.setNivelUsuario(conecta.rs.getInt("NivelUsuario"));
                objUser.setLoginUsuario(conecta.rs.getString("LoginUsuario"));
                objUser.setSenhaUsuario(conecta.rs.getString("SenhaUsuario"));
                objUser.setSenhaUsuario1(conecta.rs.getString("SenhaUsuario1"));
                objUser.setClienteServidor(conecta.rs.getString("ClienteServidor"));
                objUser.setFotoPerfilUsuario(conecta.rs.getBytes("FotoPerfilUsuario"));
                objUser.setNomeUnidade(conecta.rs.getString("DescricaoUnidade"));
                LISTA_usuarios.add(objUser);
                pTOTAL_usuarios++;
            }
            return LISTA_usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<Usuarios> pPESQUISA_todos_read() throws Exception {
        pTOTAL_usuarios = 0;
        List<Usuarios> LISTA_usuarios = new ArrayList<Usuarios>();
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "DataCadastro, "
                    + "StatusUsuario, "
                    + "NomeUsuario, "
                    + "SetorUsuario, "
                    + "CargoUsuario, "
                    + "NivelUsuario, "
                    + "LoginUsuario, "
                    + "SenhaUsuario, "
                    + "SenhaUsuario1, "
                    + "ClienteServidor, "
                    + "FotoPerfilUsuario "
                    + "FROM USUARIOS ");
            while (conecta.rs.next()) {
                Usuarios objUser = new Usuarios();
                objUser.setIdUsuario(conecta.rs.getInt("IdUsuario"));
                objUser.setStatus(conecta.rs.getString("StatusUsuario"));
                objUser.setDataCadastro(conecta.rs.getDate("DataCadastro"));
                objUser.setNomeUsuario(conecta.rs.getString("NomeUsuario"));
                objUser.setSetorUsuario(conecta.rs.getString("SetorUsuario"));
                objUser.setCargoUsuario(conecta.rs.getString("CargoUsuario"));
                objUser.setNivelUsuario(conecta.rs.getInt("NivelUsuario"));
                objUser.setLoginUsuario(conecta.rs.getString("LoginUsuario"));
                objUser.setSenhaUsuario(conecta.rs.getString("SenhaUsuario"));
                objUser.setSenhaUsuario1(conecta.rs.getString("SenhaUsuario1"));
                objUser.setClienteServidor(conecta.rs.getString("ClienteServidor"));
                objUser.setFotoPerfilUsuario(conecta.rs.getBytes("FotoPerfilUsuario"));
                LISTA_usuarios.add(objUser);
                pTOTAL_usuarios++;
            }
            return LISTA_usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public List<Usuarios> pPESQUISA_NOME_read() throws Exception {
        pTOTAL_usuarios = 0;
        List<Usuarios> LISTA_usuarios = new ArrayList<Usuarios>();
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT DISTINCT "
                    + "IdUsuario, "
                    + "DataCadastro, "
                    + "StatusUsuario, "
                    + "NomeUsuario, "
                    + "SetorUsuario, "
                    + "CargoUsuario, "
                    + "NivelUsuario, "
                    + "LoginUsuario, "
                    + "SenhaUsuario, "
                    + "SenhaUsuario1, "
                    + "ClienteServidor, "
                    + "FotoPerfilUsuario "
                    + "FROM USUARIOS "
                    + "WHERE NomeUsuario LIKE'%" + jPesquisaUsuarioNome.getText() + "%'");
            while (conecta.rs.next()) {
                Usuarios objUser = new Usuarios();
                objUser.setIdUsuario(conecta.rs.getInt("IdUsuario"));
                objUser.setStatus(conecta.rs.getString("StatusUsuario"));
                objUser.setDataCadastro(conecta.rs.getDate("DataCadastro"));
                objUser.setNomeUsuario(conecta.rs.getString("NomeUsuario"));
                objUser.setSetorUsuario(conecta.rs.getString("SetorUsuario"));
                objUser.setCargoUsuario(conecta.rs.getString("CargoUsuario"));
                objUser.setNivelUsuario(conecta.rs.getInt("NivelUsuario"));
                objUser.setLoginUsuario(conecta.rs.getString("LoginUsuario"));
                objUser.setSenhaUsuario(conecta.rs.getString("SenhaUsuario"));
                objUser.setSenhaUsuario1(conecta.rs.getString("SenhaUsuario1"));
                objUser.setClienteServidor(conecta.rs.getString("ClienteServidor"));
                objUser.setFotoPerfilUsuario(conecta.rs.getBytes("FotoPerfilUsuario"));
                LISTA_usuarios.add(objUser);
                pTOTAL_usuarios++;
            }
            return LISTA_usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conecta.desconecta();
        }
        return null;
    }

    public Usuarios VERIFICAR_NIVEL_usuario(Usuarios objUser) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "NomeUsuario, "
                    + "SetorUsuario, "
                    + "CargoUsuario, "
                    + "NivelUsuario, "
                    + "LoginUsuario "
                    + "FROM USUARIOS "
                    + "WHERE LoginUsuario='" + nameUser + "'");
            conecta.rs.first();
            objUser.setIdUsuario(conecta.rs.getInt("IdUsuario"));
            objUser.setNomeUsuario(conecta.rs.getString("NomeUsuario"));
            objUser.setSetorUsuario(conecta.rs.getString("SetorUsuario"));
            objUser.setCargoUsuario(conecta.rs.getString("CargoUsuario"));
            objUser.setNivelUsuario(conecta.rs.getInt("NivelUsuario"));
            objUser.setLoginUsuario(conecta.rs.getString("LoginUsuario"));
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objUser;
    }
}
