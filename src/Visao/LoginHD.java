/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Dao.ConexaoBancoDados;
import Dao.UsuarioDao;
import Modelo.Usuarios;
import Util.SQL.Utilitarios.Criptografia;
import java.awt.event.KeyEvent;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author ronal
 */
public class LoginHD extends javax.swing.JDialog {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    Usuarios user = new Usuarios();
    UsuarioDao USUARIO = new UsuarioDao();
    //
    public static String pCODIGO_status;
    public static String pID_USUARIO_acesso; // CÓDIGO DO USUÁRIO PERMISSÕES DE ACESSO (24/04/2016)
    public static String nameUser; // Variavel para o nome do usuário logado
    public static String pLOGIN_usuario;
    public static String pSENHA_usuario;
    public static long tamanhoOrigem, tamanhoDestino;
    public static long dataOrigem, dataDestino;
    // NOME DA EMPRESA E UNIDADE PENAL PARA SER UTILIZADO NA TELA PRINCIPAL E NOS RELATÓRIOS
    public static String razaoSocial;
    public static String descricaoUnidade;
    public static String versaoAtualSistema;
    //
    byte[] senhaCriptografada;
    String senhaDescriptografada = "";
    //
    String pSENHA1_CRIPTOGRAFA;
    public static String pCLIENTE_servidor;
    //
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss"); // HORAIO DE 24 HORAS, PARA O DE 12 HORAS UTILIZAR hh:mm:ss
    SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
    //
    public static String pDATA_sistema;
    public static String pHORA_sistema;
    //
    public static int pTOTAL_REGISTROS_dia = 0;
    public static int pTOTAL_REGISTROS_EM_atendimento = 0;
    //
    public static String tipoServidor = "";
    public static String tipoBancoDados = "";
    /**
     * Creates new form LoginHD
     */

    public static TelaEscolhaAcesso pTELA_acesso;

    public LoginHD(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
//             JFrame.setDefaultLookAndFeelDecorated(true);      
//            SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePack("/skins/coronaHthemepack.zip"));
//            UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel ");
//            UIManager.setLookAndFeel("javax.swing.plaf.mac.MacLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
        initComponents();
        Date data = new Date();
        String hora = formatter.format(data); // Data da conexão
        String date = formatter2.format(data); // Hora da conexão
        pDATA_sistema = String.valueOf(hora);    // no lugar do label, por seu JTextField    
        pHORA_sistema = String.valueOf(date);
        //
        verificarParametrosSRV();
    }

    public void mostrarTelaAmbos() {
        pTELA_acesso = new TelaEscolhaAcesso(this, true);
        pTELA_acesso.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBtConfirmar = new javax.swing.JButton();
        jBtCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jSenha = new javax.swing.JPasswordField();
        jLogin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("...::: Login de Acesso :::...");

        jBtConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N
        jBtConfirmar.setText("Confirmar");
        jBtConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtConfirmarActionPerformed(evt);
            }
        });

        jBtCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Button_Close_Icon_16.png"))); // NOI18N
        jBtCancelar.setText("Cancelar");
        jBtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtCancelarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true)));

        jSenha.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSenhaKeyPressed(evt);
            }
        });

        jLogin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLoginKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("Senha:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Usuário:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLogin)
                    .addComponent(jSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtConfirmar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtCancelar)
                .addGap(70, 70, 70))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtCancelar, jBtConfirmar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtConfirmar)
                    .addComponent(jBtCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtCancelar, jBtConfirmar});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtConfirmarActionPerformed
        // TODO add your handling code here:
        acessarSistema();
    }//GEN-LAST:event_jBtConfirmarActionPerformed

    private void jBtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtCancelarActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jBtCancelarActionPerformed

    private void jLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLoginKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jSenha.requestFocus();
        }
    }//GEN-LAST:event_jLoginKeyPressed

    private void jSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSenhaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            acessarSistema();
        }
    }//GEN-LAST:event_jSenhaKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginHD dialog = new LoginHD(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtCancelar;
    private javax.swing.JButton jBtConfirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JTextField jLogin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jSenha;
    // End of variables declaration//GEN-END:variables

    public void acessarSistema() {
        //BUSCAR DADOS DO USUÁRIO PARA COMPARAR ACESSO.
        USUARIO.pBUSCAR_usuario(user);
        //
        pSENHA1_CRIPTOGRAFA = Criptografia.criptografar(jSenha.getText());
        if (jLogin.getText().equals(pLOGIN_usuario) && (pSENHA1_CRIPTOGRAFA).equals(pSENHA_usuario) && pCODIGO_status.equals("Inativo")) {
            JOptionPane.showMessageDialog(null, "Usuário INATIVO !!!");
        } else if (jLogin.getText().equals(pLOGIN_usuario) && !pSENHA1_CRIPTOGRAFA.equals(pSENHA_usuario) && pCODIGO_status.equals("Ativo")) {
            JOptionPane.showMessageDialog(null, "Senha Inválida");
        } else if (!jLogin.getText().equals(pLOGIN_usuario) && pSENHA1_CRIPTOGRAFA.equals(pSENHA_usuario) && pCODIGO_status.equals("Ativo")) {
            JOptionPane.showMessageDialog(null, "Login Inválida");
        } else if (jLogin.getText().equals(pLOGIN_usuario) && (pSENHA1_CRIPTOGRAFA).equals(pSENHA_usuario) && pCODIGO_status.equals("Ativo")) {
            if (pCLIENTE_servidor.equals("Servidor")) {
                TelaPrincipal tlp = new TelaPrincipal();
                tlp.setVisible(true);
                this.dispose();
            } else if (pCLIENTE_servidor.equals("Cliente")) {
                TelaClienteChamadosSuporte objCliente = new TelaClienteChamadosSuporte();
                objCliente.setVisible(true);
                this.dispose();
            } else if (pCLIENTE_servidor.equals("Ambos")) {
                mostrarTelaAmbos();
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuario ou senha Inváldo, tente novamente !!!");
            jLogin.setText("");
            jSenha.setText("");
        }
    }
    // PARAMETRO PARA IDENTIFICAR O OS DO SERVIDOR DE BANCO DE DADOS.

    public void verificarParametrosSRV() {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "TipoServidor, "
                    + "TipoBanco "
                    + "FROM SOFTWARE");
            conecta.rs.first();
            tipoServidor = conecta.rs.getString("TipoServidor");
            tipoBancoDados = conecta.rs.getString("TipoBanco");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }
}
