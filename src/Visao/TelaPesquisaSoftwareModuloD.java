/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Controle.ModeloTabela;
import Dao.ConexaoBancoDados;
import static Visao.TelaChamadoDesenvolvimento.jSoftware;
import static Visao.TelaChamadoDesenvolvimento.idSoftware;
import static Visao.TelaChamadoDesenvolvimento.jModulo;
import static Visao.TelaChamadoDesenvolvimento.idModulo;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ronal
 */
public class TelaPesquisaSoftwareModuloD extends javax.swing.JDialog {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    //
    int count = 0;
    int flag;

    /**
     * Creates new form TelaPesquisaSolicitanteCH
     */
    public static TelaChamadoDesenvolvimento chamaSupD;

    public TelaPesquisaSoftwareModuloD(TelaChamadoDesenvolvimento parent, boolean modal) {
        this.chamaSupD = parent;
        this.setModal(modal);
        setLocationRelativeTo(chamaSupD);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCodigo = new javax.swing.JTextField();
        jNomeSolicitantePes = new javax.swing.JTextField();
        jBtSoftware = new javax.swing.JButton();
        jBtCodigo = new javax.swing.JButton();
        jCheckBoxTodos = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaSoftwareModulo = new javax.swing.JTable();
        jPanel31 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jtotalRegistros = new javax.swing.JLabel();
        jBtConfirmar = new javax.swing.JButton();
        jBtSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("...::: Solicitante de Suporte Técnico :::...");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Código:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nome do Software:");

        jCodigo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jNomeSolicitantePes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jBtSoftware.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Lupas_1338_05.gif"))); // NOI18N
        jBtSoftware.setToolTipText("Pesquisar Solicitante pelo nome.");
        jBtSoftware.setContentAreaFilled(false);
        jBtSoftware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtSoftwareActionPerformed(evt);
            }
        });

        jBtCodigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Lupas_1338_05.gif"))); // NOI18N
        jBtCodigo.setToolTipText("Pesquisar solicitante pelo código");
        jBtCodigo.setContentAreaFilled(false);
        jBtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtCodigoActionPerformed(evt);
            }
        });

        jCheckBoxTodos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBoxTodos.setText("Todos");
        jCheckBoxTodos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxTodosItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jNomeSolicitantePes, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtSoftware, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxTodos)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtCodigo))
                    .addComponent(jCheckBoxTodos, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(jNomeSolicitantePes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtSoftware))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabelaSoftwareModulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTabelaSoftwareModulo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome do Software", "Versão", "Nome do Módulo"
            }
        ));
        jTabelaSoftwareModulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaSoftwareModuloMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabelaSoftwareModulo);
        if (jTabelaSoftwareModulo.getColumnModel().getColumnCount() > 0) {
            jTabelaSoftwareModulo.getColumnModel().getColumn(0).setMinWidth(60);
            jTabelaSoftwareModulo.getColumnModel().getColumn(0).setMaxWidth(60);
            jTabelaSoftwareModulo.getColumnModel().getColumn(1).setMinWidth(350);
            jTabelaSoftwareModulo.getColumnModel().getColumn(1).setMaxWidth(350);
            jTabelaSoftwareModulo.getColumnModel().getColumn(2).setMinWidth(100);
            jTabelaSoftwareModulo.getColumnModel().getColumn(2).setMaxWidth(100);
            jTabelaSoftwareModulo.getColumnModel().getColumn(3).setMinWidth(250);
            jTabelaSoftwareModulo.getColumnModel().getColumn(3).setMaxWidth(250);
        }

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));

        jLabel63.setText("Total de Registros:");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel63))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel63)
        );

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));

        jtotalRegistros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtotalRegistros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtotalRegistros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
        );

        jBtConfirmar.setForeground(new java.awt.Color(0, 102, 0));
        jBtConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/accept.png"))); // NOI18N
        jBtConfirmar.setText("Confirmar");
        jBtConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtConfirmarActionPerformed(evt);
            }
        });

        jBtSair.setForeground(new java.awt.Color(204, 0, 0));
        jBtSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/shutdown-icone-6920-16.png"))); // NOI18N
        jBtSair.setText("Sair");
        jBtSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBtConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtSair)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtConfirmar, jBtSair});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtConfirmar)
                    .addComponent(jBtSair))
                .addGap(6, 6, 6))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtCodigoActionPerformed
        // TODO add your handling code here:
        count = 0;
        flag = 1;
        if (jCodigo.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Informe o código para pesquisa.");
        } else {
            pesquisarSolicitantes("SELECT * FROM SOFTWARE "
                    + "INNER JOIN MODULOS "
                    + "ON SOFTWARE.IdSoftware=MODULOS.IdSoftware  "
                    + "WHERE SOFTWARE.IdSoftware='" + jCodigo.getText() + "'");
        }
    }//GEN-LAST:event_jBtCodigoActionPerformed

    private void jCheckBoxTodosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxTodosItemStateChanged
        // TODO add your handling code here:
        count = 0;
        flag = 1;
        if (evt.getStateChange() == evt.SELECTED) {
            this.pesquisarSolicitantes("SELECT * FROM SOFTWARE "
                    + "INNER JOIN MODULOS "
                    + "ON SOFTWARE.IdSoftware=MODULOS.IdSoftware ");
        } else {
            jtotalRegistros.setText("");
            limparTabela();
        }
    }//GEN-LAST:event_jCheckBoxTodosItemStateChanged

    private void jBtSoftwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSoftwareActionPerformed
        // TODO add your handling code here:
        if (jNomeSolicitantePes.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Informe o nome do solicitante para pesquisa.");
        } else {
            pesquisarSolicitantes("SELECT * FROM SOFTWARE "
                    + "INNER JOIN MODULOS "
                    + "ON SOFTWARE.IdSoftware=MODULOS.IdSoftware "
                    + "WHERE SOFTWARE.DescricaoSoftware "
                    + "LIKE'%" + jNomeSolicitantePes + "%'");
        }
    }//GEN-LAST:event_jBtSoftwareActionPerformed

    private void jBtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtConfirmarActionPerformed
        // TODO add your handling code here:
        flag = 1;
        if (flag == 1) {
            String idSoft = "" + jTabelaSoftwareModulo.getValueAt(jTabelaSoftwareModulo.getSelectedRow(), 0);
            jCodigo.setText(idSoft);
            //
            String nomeMod = "" + jTabelaSoftwareModulo.getValueAt(jTabelaSoftwareModulo.getSelectedRow(), 3);
            //
            conecta.abrirConexao();
            try {
                conecta.executaSQL("SELECT * FROM SOFTWARE "
                        + "INNER JOIN MODULOS "
                        + "ON SOFTWARE.IdSoftware=MODULOS.IdSoftware  "
                        + "WHERE SOFTWARE.IdSoftware='" + idSoft + "' "
                        + "AND MODULOS.DescricaoModulo='" + nomeMod + "'");
                conecta.rs.first();
                idSoftware = conecta.rs.getInt("IdSoftware");
                jSoftware.setText(conecta.rs.getString("DescricaoSoftware"));
                idModulo = conecta.rs.getInt("IdModulo");
                jModulo.setText(conecta.rs.getString("DescricaoModulo"));
                conecta.desconecta();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(rootPane, "ERRO na pesquisa dos dados.\nERROR: " + e);
            }
            dispose();
        }
    }//GEN-LAST:event_jBtConfirmarActionPerformed

    private void jBtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSairActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jBtSairActionPerformed

    private void jTabelaSoftwareModuloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaSoftwareModuloMouseClicked
        // TODO add your handling code here:
        flag = 1;
        if (flag == 1) {
            String idSoft = "" + jTabelaSoftwareModulo.getValueAt(jTabelaSoftwareModulo.getSelectedRow(), 0);
            jCodigo.setText(idSoft);
            //
            String nomeSoft = "" + jTabelaSoftwareModulo.getValueAt(jTabelaSoftwareModulo.getSelectedRow(), 1);
            jNomeSolicitantePes.setText(nomeSoft);
            //
            conecta.abrirConexao();
            try {
                conecta.executaSQL("SELECT * FROM SOFTWARE "
                        + "INNER JOIN MODULOS "
                        + "ON SOFTWARE.IdSoftware=MODULOS.IdSoftware  "
                        + "WHERE SOFTWARE.IdSoftware='" + idSoft + "' "
                        + "AND SOFTWARE.DescricaoSoftware='" + nomeSoft + "'");
                conecta.rs.first();
                idSoftware = conecta.rs.getInt("IdSoftware");
                jSoftware.setText(conecta.rs.getString("DescricaoSoftware"));
                idModulo = conecta.rs.getInt("IdModulo");
                jModulo.setText(conecta.rs.getString("DescricaoModulo"));
                conecta.desconecta();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(rootPane, "ERRO na pesquisa dos dados.\nERROR: " + e);
            }
            conecta.desconecta();
        }
    }//GEN-LAST:event_jTabelaSoftwareModuloMouseClicked

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
            java.util.logging.Logger.getLogger(TelaPesquisaSoftwareModuloD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaSoftwareModuloD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaSoftwareModuloD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisaSoftwareModuloD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaPesquisaSoftwareModuloD dialog = new TelaPesquisaSoftwareModuloD(chamaSupD, true);
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
    private javax.swing.JButton jBtCodigo;
    private javax.swing.JButton jBtConfirmar;
    private javax.swing.JButton jBtSair;
    private javax.swing.JButton jBtSoftware;
    private javax.swing.JCheckBox jCheckBoxTodos;
    private javax.swing.JTextField jCodigo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JTextField jNomeSolicitantePes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabelaSoftwareModulo;
    private javax.swing.JLabel jtotalRegistros;
    // End of variables declaration//GEN-END:variables

    public void pesquisarSolicitantes(String sql) {
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{"Código", "Nome do Software", "Versão", "Nome do Módulo"};
        conecta.abrirConexao();
        try {
            conecta.executaSQL(sql);
            conecta.rs.first();
            do {
                count = count + 1;
                jtotalRegistros.setText(Integer.toString(count)); // Converter inteiro em string para exibir na tela
                dados.add(new Object[]{conecta.rs.getInt("IdSoftware"), conecta.rs.getString("DescricaoSoftware"), conecta.rs.getString("VersaoSoftware"), conecta.rs.getString("DescricaoModulo")});
            } while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Não existem dados a serem EXIBIDOS !!!");
        }
        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        jTabelaSoftwareModulo.setModel(modelo);
        jTabelaSoftwareModulo.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTabelaSoftwareModulo.getColumnModel().getColumn(0).setResizable(false);
        jTabelaSoftwareModulo.getColumnModel().getColumn(1).setPreferredWidth(350);
        jTabelaSoftwareModulo.getColumnModel().getColumn(1).setResizable(false);
        jTabelaSoftwareModulo.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTabelaSoftwareModulo.getColumnModel().getColumn(2).setResizable(false);
        jTabelaSoftwareModulo.getColumnModel().getColumn(3).setPreferredWidth(250);
        jTabelaSoftwareModulo.getColumnModel().getColumn(3).setResizable(false);
        jTabelaSoftwareModulo.getTableHeader().setReorderingAllowed(false);
        jTabelaSoftwareModulo.setAutoResizeMode(jTabelaSoftwareModulo.AUTO_RESIZE_OFF);
        jTabelaSoftwareModulo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        alinharCamposTabela();
        conecta.desconecta();
    }

    public void limparTabela() {
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{"Código", "Nome do Software", "Versão", "Nome do Módulo"};
        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        jTabelaSoftwareModulo.setModel(modelo);
        jTabelaSoftwareModulo.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTabelaSoftwareModulo.getColumnModel().getColumn(0).setResizable(false);
        jTabelaSoftwareModulo.getColumnModel().getColumn(1).setPreferredWidth(350);
        jTabelaSoftwareModulo.getColumnModel().getColumn(1).setResizable(false);
        jTabelaSoftwareModulo.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTabelaSoftwareModulo.getColumnModel().getColumn(2).setResizable(false);
        jTabelaSoftwareModulo.getColumnModel().getColumn(3).setPreferredWidth(250);
        jTabelaSoftwareModulo.getColumnModel().getColumn(3).setResizable(false);
        jTabelaSoftwareModulo.getTableHeader().setReorderingAllowed(false);
        jTabelaSoftwareModulo.setAutoResizeMode(jTabelaSoftwareModulo.AUTO_RESIZE_OFF);
        jTabelaSoftwareModulo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelo.getLinhas().clear();
    }

    public void alinharCamposTabela() {
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        //
        jTabelaSoftwareModulo.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        jTabelaSoftwareModulo.getColumnModel().getColumn(2).setCellRenderer(direita);
    }
}
