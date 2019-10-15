/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.SQL;

import Dao.ConexaoBancoDados;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Socializa TI 02
 */
public class EnviarRecuperarArquivoBancoDados {

    ConexaoBancoDados conecta = new ConexaoBancoDados();

    //Enviando o arquivo para o banco de dados
    public boolean insertFile(File f) {
//    Connection c = this.getConnection();//busca uma conexao com o banco
        try {
            PreparedStatement pst = conecta.con.prepareStatement("INSERT INTO arquivo( id, nome, arquivo ) VALUES ( nextval('seq_arquivo'), ?, ? )");
            //converte o objeto file em array de bytes
            InputStream is = new FileInputStream(f);
            byte[] bytes = new byte[(int) f.length()];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            pst.setString(1, f.getName());
            pst.setBytes(2, bytes);
            pst.execute();
            pst.close();
//            c.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //Recuperando o arquivo do banco de dados
    public File getFile(int id) {
//    Connection c = this.getConnection();//busca uma conexao com o banco
        File f = null;
        try {
            PreparedStatement pst = conecta.con.prepareStatement("SELECT id, nome, arquivo FROM arquivo WHERE id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                byte[] bytes = rs.getBytes("arquivo");
                String nome = rs.getString("nome");
                //converte o array de bytes em file
                f = new File("/local_a_ser_salvo/" + nome);
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(bytes);
                fos.close();
            }
            rs.close();
            pst.close();
//            c.close();
            return f;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
