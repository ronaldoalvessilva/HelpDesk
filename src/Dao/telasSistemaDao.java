/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.CadastroTelasSistema;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ronal
 */
public class telasSistemaDao {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    CadastroTelasSistema objCadTela = new CadastroTelasSistema();

    public CadastroTelasSistema incluirTelaAcesso(CadastroTelasSistema objCadTela) {
        conecta.abrirConexao();
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO TELAS (nomeTela) VALUES(?)");           
            pst.setString(1, objCadTela.getNomeTela());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não Foi possível INSERIR os Dados.\nERRO: " + ex);
        }
        conecta.desconecta();
        return objCadTela;
    }
}
