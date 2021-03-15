/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Visao.TelaBiometriaColaboradores.pRESPOSTA_user;
import static Visao.TelaBiometriaColaboradores.codigoColaborador;
import static Visao.TelaBiometriaColaboradores.caminhoBiometria1;
import static Visao.TelaBiometriaColaboradores.caminhoBiometria2;
import static Visao.TelaBiometriaColaboradores.caminhoBiometria3;
import static Visao.TelaBiometriaColaboradores.caminhoBiometria4;
import Modelo.BiometriaColaboradores;
import static Visao.TelaUsuarios.jIdUsuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ronaldo
 */
public class BiometriaColaboradoresDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    BiometriaColaboradores objBio = new BiometriaColaboradores();

    int codigoFunc;

    public BiometriaColaboradores incluirBiometriaColaborador(BiometriaColaboradores objBio) {
        BUSCAR_COLABORADOR_biometria(objBio.getIdFunc(), objBio.getNomeColabordor());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO BIOMETRIA_USUARIOS(DataCadastro,IdFunc,BiometriaDedo1,BiometriaDedo2,BiometriaDedo3,BiometriaDedo4,CaminhoImagemDedo1,CaminhoImagemDedo2,CaminhoImagemDedo3,CaminhoImagemDedo4,UsuarioInsert,DataInsert,HorarioInsert) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setTimestamp(1, new java.sql.Timestamp(objBio.getDataCadastro().getTime()));
            pst.setInt(2, codigoFunc);
            pst.setBytes(3, objBio.getBiometriaDedo1());
            pst.setBytes(4, objBio.getBiometriaDedo2());
            pst.setBytes(5, objBio.getBiometriaDedo3());
            pst.setBytes(6, objBio.getBiometriaDedo4());
            pst.setString(7, objBio.getCaminhoImagemDedo1());
            pst.setString(8, objBio.getCaminhoImagemDedo2());
            pst.setString(9, objBio.getCaminhoImagemDedo3());
            pst.setString(10, objBio.getCaminhoImagemDedo4());
            pst.setString(11, objBio.getUsuarioInsert());
            pst.setString(12, objBio.getDataInsert());
            pst.setString(13, objBio.getHorarioInsert());
            pst.executeUpdate();
            pRESPOSTA_user = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA_user = "Não";
            JOptionPane.showMessageDialog(null, "Não Foi possivel gravar biometria.\n\nERRO: " + ex);
        }
        conecta.desconecta();
        return objBio;
    }

    public BiometriaColaboradores alterarBiometriaColaborador(BiometriaColaboradores objBio) {
        BUSCAR_COLABORADOR_biometria(objBio.getIdFunc(), objBio.getNomeColabordor());
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("UPDATE BIOMETRIA_USUARIOS SET DataCadastro=?,IdFunc=?,BiometriaDedo1=?,BiometriaDedo2=?,BiometriaDedo3=?,BiometriaDedo4=?,CaminhoImagemDedo1=?,CaminhoImagemDedo2=?,CaminhoImagemDedo3=?,CaminhoImagemDedo4=?,UsuarioUp=?,DataUp=?,HorarioUp=? WHERE IdFunc='" + objBio.getIdFunc() + "'");
            pst.setTimestamp(1, new java.sql.Timestamp(objBio.getDataCadastro().getTime()));
            pst.setInt(2, codigoFunc);
            pst.setBytes(3, objBio.getBiometriaDedo1());
            pst.setBytes(4, objBio.getBiometriaDedo2());
            pst.setBytes(5, objBio.getBiometriaDedo3());
            pst.setBytes(6, objBio.getBiometriaDedo4());
            pst.setString(7, objBio.getCaminhoImagemDedo1());
            pst.setString(8, objBio.getCaminhoImagemDedo2());
            pst.setString(9, objBio.getCaminhoImagemDedo3());
            pst.setString(10, objBio.getCaminhoImagemDedo4());
            pst.setString(11, objBio.getUsuarioUp());
            pst.setString(12, objBio.getDataUp());
            pst.setString(13, objBio.getHorarioUp());
            pst.executeUpdate();
            pRESPOSTA_user = "Sim";
        } catch (SQLException ex) {
            pRESPOSTA_user = "Não";
            JOptionPane.showMessageDialog(null, "Não Foi possivel gravar biometria.\n\nERRO: " + ex);
        }
        conecta.desconecta();
        return objBio;
    }

    public void BUSCAR_COLABORADOR_biometria(int codigo, String nomeInterno) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "NomeUsuario "
                    + "FROM COLABORADOR "
                    + "WHERE IdUsuario='" + codigo + "' "
                    + "AND NomeUsuario='" + nomeInterno + "'");
            conecta.rs.first();
            codigoFunc = conecta.rs.getInt("IdUsuario");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível localizar o registro do usuário.");
        }
        conecta.desconecta();
    }

    public BiometriaColaboradores VERIFICAR_BIOMETRIA_usuario(BiometriaColaboradores objBio) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdFunc "
                    + "FROM BIOMETRIA_COLABORADORES "
                    + "WHERE IdFunc='" + jIdUsuario.getText() + "'");
            conecta.rs.first();
            codigoColaborador = conecta.rs.getString("IdFunc");
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objBio;
    }

    public BiometriaColaboradores BUSCAR_CAMINHO_TEMPLATE_Imagem(BiometriaColaboradores objBio) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "CaminhoImagemColaboradores, "
                    + "CaminhoImagemColaboradores, "
                    + "CaminhoImagemColaboradores, "
                    + "CaminhoImagemColaboradores "
                    + "FROM PARAMETROSCRC");
            conecta.rs.first();
            // CAMINHOS DAS IMAGENS
            caminhoBiometria1 = conecta.rs.getString("CaminhoImagemColaboradores");
            caminhoBiometria2 = conecta.rs.getString("CaminhoImagemColaboradores");
            caminhoBiometria3 = conecta.rs.getString("CaminhoImagemColaboradores");
            caminhoBiometria4 = conecta.rs.getString("CaminhoImagemColaboradores");
        } catch (Exception e) {
        }
        conecta.desconecta();
        return objBio;
    }
}
