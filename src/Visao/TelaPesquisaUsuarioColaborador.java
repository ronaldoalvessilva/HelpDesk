/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Dao.ColaboradoresDao;
import Modelo.Colaboradores;
import static Visao.TelaCadastroColaboradorCP.jCargoColaborador;
import static Visao.TelaCadastroColaboradorCP.jIdUsuarioColaborador;
import static Visao.TelaCadastroColaboradorCP.jNomeUsuarioColaborador;
import static Visao.TelaCadastroColaboradorCP.jSetorColaborador;
import static Visao.TelaCadastroColaboradorCP.jUnidadeColaborador;
import static Visao.TelaCadastroColaboradorCP.pTOTAL_colaboradores;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ronal
 */
public class TelaPesquisaUsuarioColaborador extends javax.swing.JInternalFrame {

    Colaboradores objColabora = new Colaboradores();
    ColaboradoresDao colaboradorDAO = new ColaboradoresDao();

    int flag = 0;

    /**
     * Creates new form TelaPesquisaUsuarioColaborador
     */
    public TelaPesquisaUsuarioColaborador() {
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
        jPesquisarUsuarioColaborador = new javax.swing.JTextField();
        jBtPesquisarUsuarioNome = new javax.swing.JButton();
        jCheckBoxTodos = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaUsuarios = new javax.swing.JTable();
        jBtConfirmar = new javax.swing.JButton();
        jBtSair = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jtotalRegistros = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("...::: Usuários :::...");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Nome do Usuário");

        jPesquisarUsuarioColaborador.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jBtPesquisarUsuarioNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Lupas_1338_05.gif"))); // NOI18N
        jBtPesquisarUsuarioNome.setContentAreaFilled(false);
        jBtPesquisarUsuarioNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtPesquisarUsuarioNomeActionPerformed(evt);
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
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPesquisarUsuarioColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtPesquisarUsuarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxTodos)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCheckBoxTodos)
                    .addComponent(jBtPesquisarUsuarioNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPesquisarUsuarioColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabelaUsuarios.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTabelaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome do Usuário"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTabelaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabelaUsuarios);
        if (jTabelaUsuarios.getColumnModel().getColumnCount() > 0) {
            jTabelaUsuarios.getColumnModel().getColumn(0).setMinWidth(70);
            jTabelaUsuarios.getColumnModel().getColumn(0).setMaxWidth(70);
            jTabelaUsuarios.getColumnModel().getColumn(1).setMinWidth(400);
            jTabelaUsuarios.getColumnModel().getColumn(1).setMaxWidth(400);
        }

        jBtConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/tick.png"))); // NOI18N
        jBtConfirmar.setText("Confirmar");
        jBtConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtConfirmarActionPerformed(evt);
            }
        });

        jBtSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/shutdown-icone-6920-16.png"))); // NOI18N
        jBtSair.setText("Sair");
        jBtSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtSairActionPerformed(evt);
            }
        });

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));

        jLabel67.setText("Total de Registros:");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel67))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel67)
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBtConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtSair)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtConfirmar, jBtSair});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtConfirmar)
                    .addComponent(jBtSair))
                .addGap(4, 4, 4))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtConfirmar, jBtSair});

        setBounds(300, 60, 480, 289);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtPesquisarUsuarioNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtPesquisarUsuarioNomeActionPerformed
        // TODO add your handling code here:
        if (jPesquisarUsuarioColaborador.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Informe o nome para pesquisa.");
        } else {
            LIMPAR_tabela();
            PESQUISAR_nome();
            if (pTOTAL_colaboradores == 0) {
                JOptionPane.showMessageDialog(rootPane, "Não existe dados a serem exibidos.");
            }
        }
    }//GEN-LAST:event_jBtPesquisarUsuarioNomeActionPerformed

    private void jCheckBoxTodosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxTodosItemStateChanged
        // TODO add your handling code here:
        flag = 1;
        if (evt.getStateChange() == evt.SELECTED) {
            LIMPAR_tabela();
            PESQUISAR_todos();
            if (pTOTAL_colaboradores == 0) {
                JOptionPane.showMessageDialog(rootPane, "Não existe dados a serem exibidos.");
            }
        } else {
            LIMPAR_tabela();
        }
    }//GEN-LAST:event_jCheckBoxTodosItemStateChanged

    private void jTabelaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaUsuariosMouseClicked
        // TODO add your handling code here:
        flag = 1;
        if (flag == 1) {
            String idUser = "" + jTabelaUsuarios.getValueAt(jTabelaUsuarios.getSelectedRow(), 0);
            String nomeUser = "" + jTabelaUsuarios.getValueAt(jTabelaUsuarios.getSelectedRow(), 1);
            jPesquisarUsuarioColaborador.setText(nomeUser);
        }
    }//GEN-LAST:event_jTabelaUsuariosMouseClicked

    private void jBtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtConfirmarActionPerformed
        // TODO add your handling code here:
        if (flag == 1) {
            colaboradorDAO.MOSTRAR_dados(objColabora);
            jIdUsuarioColaborador.setText(String.valueOf(objColabora.getIdColaborador()));
            jNomeUsuarioColaborador.setText(objColabora.getNomeUsuario());
            jSetorColaborador.setText(objColabora.getDepartamentoColaborador());
            jCargoColaborador.setText(objColabora.getCargoColaborador());
            jUnidadeColaborador.setText(objColabora.getDescricaoUnidade());
            dispose();
        }
    }//GEN-LAST:event_jBtConfirmarActionPerformed

    private void jBtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSairActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jBtSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtConfirmar;
    private javax.swing.JButton jBtPesquisarUsuarioNome;
    private javax.swing.JButton jBtSair;
    private javax.swing.JCheckBox jCheckBoxTodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    public static javax.swing.JTextField jPesquisarUsuarioColaborador;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabelaUsuarios;
    private javax.swing.JLabel jtotalRegistros;
    // End of variables declaration//GEN-END:variables

    public void PESQUISAR_nome() {
        DefaultTableModel dadosUsuarios = (DefaultTableModel) jTabelaUsuarios.getModel();
        try {
            for (Colaboradores dd : colaboradorDAO.pPESQUISA_USUARIO_nome()) {

                jtotalRegistros.setText(Integer.toString(pTOTAL_colaboradores)); // Converter inteiro em string para exibir na tela
                dadosUsuarios.addRow(new Object[]{dd.getIdColaborador(), dd.getNomeUsuario()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaUsuarios.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PESQUISAR_todos() {
        DefaultTableModel dadosUsuarios = (DefaultTableModel) jTabelaUsuarios.getModel();
        try {
            for (Colaboradores dd : colaboradorDAO.pPESQUISA_USUARIO_todos()) {

                jtotalRegistros.setText(Integer.toString(pTOTAL_colaboradores)); // Converter inteiro em string para exibir na tela
                dadosUsuarios.addRow(new Object[]{dd.getIdColaborador(), dd.getNomeUsuario()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaUsuarios.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LIMPAR_tabela() {
        // APAGAR DADOS DA TABELA
        while (jTabelaUsuarios.getModel().getRowCount() > 0) {
            ((DefaultTableModel) jTabelaUsuarios.getModel()).removeRow(0);
        }
        jtotalRegistros.setText("");
    }
}
