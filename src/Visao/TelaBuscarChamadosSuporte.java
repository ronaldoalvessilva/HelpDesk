/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Controle.ModeloTabela;
import Dao.ConexaoBancoDados;
import static Visao.TelaChamadoDesenvolvimento.jIdChamado;
import static Visao.TelaChamadoDesenvolvimento.jModulo;
import static Visao.TelaChamadoDesenvolvimento.jSoftware;
import static Visao.TelaChamadoDesenvolvimento.jTextoSuporte;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ronal
 */
public class TelaBuscarChamadosSuporte extends javax.swing.JDialog {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    int flag, acao;
    int count;
    String dataInicial, dataFinal, dataBrasil;
    String dataEntrada;
    String tipoSuporte = "SUPORTE TÉCNICO";

    /**
     * Creates new form TelaBuscarChamadosSuporte
     */
    public static TelaChamadoDesenvolvimento chamaDes;

    public TelaBuscarChamadosSuporte(TelaChamadoDesenvolvimento parent, boolean modal) {
        this.chamaDes = parent;
        this.setModal(modal);
        setLocationRelativeTo(chamaDes);
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

        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jIdChamadoPesquisa = new javax.swing.JTextField();
        jBtPesqCHCodigo = new javax.swing.JButton();
        jCheckBoxTodosCH = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jPesqSolicitante = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jDataPesqInicial = new com.toedter.calendar.JDateChooser();
        jDataPesFinal = new com.toedter.calendar.JDateChooser();
        jBtPesqCHData = new javax.swing.JButton();
        jBtSolicitante = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaChamdosSup = new javax.swing.JTable();
        jPanel32 = new javax.swing.JPanel();
        jtotalRegistros = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jBtConfirmar = new javax.swing.JButton();
        jBtSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("...::: Buscar Chamados no Suporte Técnico");
        setAlwaysOnTop(true);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true)));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Código:");

        jIdChamadoPesquisa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdChamadoPesquisa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jBtPesqCHCodigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Lupas_1338_05.gif"))); // NOI18N
        jBtPesqCHCodigo.setToolTipText("Pesquisa de Chamados por Código");
        jBtPesqCHCodigo.setContentAreaFilled(false);
        jBtPesqCHCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtPesqCHCodigoActionPerformed(evt);
            }
        });

        jCheckBoxTodosCH.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBoxTodosCH.setText("Todos");
        jCheckBoxTodosCH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxTodosCHItemStateChanged(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Solicitante:");

        jPesqSolicitante.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Data Inicial:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Data Final:");

        jDataPesqInicial.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jDataPesFinal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jBtPesqCHData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Lupas_1338_05.gif"))); // NOI18N
        jBtPesqCHData.setContentAreaFilled(false);
        jBtPesqCHData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtPesqCHDataActionPerformed(evt);
            }
        });

        jBtSolicitante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Lupas_1338_05.gif"))); // NOI18N
        jBtSolicitante.setContentAreaFilled(false);
        jBtSolicitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtSolicitanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jDataPesqInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDataPesFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtPesqCHData, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPesqSolicitante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jIdChamadoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtPesqCHCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxTodosCH)
                        .addGap(52, 52, 52))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(jIdChamadoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtPesqCHCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel14)
                    .addComponent(jDataPesqInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jDataPesFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtPesqCHData, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxTodosCH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(jPesqSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabelaChamdosSup.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTabelaChamdosSup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Data", "Solicitante", "Texto do Suporte Técnico"
            }
        ));
        jTabelaChamdosSup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaChamdosSupMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabelaChamdosSup);
        if (jTabelaChamdosSup.getColumnModel().getColumnCount() > 0) {
            jTabelaChamdosSup.getColumnModel().getColumn(0).setMinWidth(70);
            jTabelaChamdosSup.getColumnModel().getColumn(0).setMaxWidth(70);
            jTabelaChamdosSup.getColumnModel().getColumn(1).setMinWidth(80);
            jTabelaChamdosSup.getColumnModel().getColumn(1).setMaxWidth(80);
            jTabelaChamdosSup.getColumnModel().getColumn(2).setMinWidth(300);
            jTabelaChamdosSup.getColumnModel().getColumn(2).setMaxWidth(300);
            jTabelaChamdosSup.getColumnModel().getColumn(3).setMinWidth(550);
            jTabelaChamdosSup.getColumnModel().getColumn(3).setMaxWidth(550);
        }

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

        jBtConfirmar.setForeground(new java.awt.Color(0, 102, 0));
        jBtConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/tick.png"))); // NOI18N
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
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBtSair)
                                .addGap(0, 592, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtConfirmar, jBtSair, jPanel30});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtConfirmar)
                    .addComponent(jBtSair))
                .addGap(7, 7, 7))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtPesqCHCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtPesqCHCodigoActionPerformed
        // TODO add your handling code here:
        count = 0;
        flag = 1;
        if (jIdChamadoPesquisa.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Informe o código para pesquisa.");
        } else {
            preencherTabelaChamados("SELECT * FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOFTWARE "
                    + "ON CHAMADOS_SUPORTE.IdSoftware=SOFTWARE.IdSoftware "
                    + "INNER JOIN MODULOS "
                    + "ON CHAMADOS_SUPORTE.IdModulo=MODULOS.IdModulo "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante  "
                    + "WHERE CHAMADOS_SUPORTE.IdCHSup='" + jIdChamadoPesquisa.getText() + "' "
                    + "AND CHAMADOS_SUPORTE.TipoChamado='" + tipoSuporte + "'");
        }
    }//GEN-LAST:event_jBtPesqCHCodigoActionPerformed

    private void jCheckBoxTodosCHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxTodosCHItemStateChanged
        // TODO add your handling code here:
        count = 0;
        flag = 1;
        if (evt.getStateChange() == evt.SELECTED) {
            this.preencherTabelaChamados("SELECT * FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOFTWARE "
                    + "ON CHAMADOS_SUPORTE.IdSoftware=SOFTWARE.IdSoftware "
                    + "INNER JOIN MODULOS "
                    + "ON CHAMADOS_SUPORTE.IdModulo=MODULOS.IdModulo "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                    + "INNER JOIN ITENS_CHAMADOS_SUPORTE "
                    + "ON CHAMADOS_SUPORTE.IdCHSup=ITENS_CHAMADOS_SUPORTE.IdCHSup "
                    + "WHERE ITENS_CHAMADOS_SUPORTE.TipoChamado='" + tipoSuporte + "'");
        } else {
            jtotalRegistros.setText("");
            limparTabelaFornecedor();
        }
    }//GEN-LAST:event_jCheckBoxTodosCHItemStateChanged

    private void jBtPesqCHDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtPesqCHDataActionPerformed
        // TODO add your handling code here:
        count = 0;
        flag = 1;
        if (jDataPesqInicial.getDate() == null) {
            JOptionPane.showMessageDialog(rootPane, "Informe a data inicial para pesquisa.");
            jDataPesqInicial.requestFocus();
        } else {
            if (jDataPesFinal.getDate() == null) {
                JOptionPane.showMessageDialog(rootPane, "Informe a data final para pesquisa.");
                jDataPesFinal.requestFocus();
            } else {
                if (jDataPesqInicial.getDate().after(jDataPesFinal.getDate())) {
                    JOptionPane.showMessageDialog(rootPane, "Data Inicial não pode ser maior que data final");
                } else {
                    SimpleDateFormat formatoAmerica = new SimpleDateFormat("dd/MM/yyyy");
                    dataInicial = formatoAmerica.format(jDataPesqInicial.getDate().getTime());
                    dataFinal = formatoAmerica.format(jDataPesFinal.getDate().getTime());
                    preencherTabelaChamados("SELECT * FROM CHAMADOS_SUPORTE "
                            + "INNER JOIN USUARIOS "
                            + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                            + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                            + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                            + "INNER JOIN SOFTWARE "
                            + "ON CHAMADOS_SUPORTE.IdSoftware=SOFTWARE.IdSoftware "
                            + "INNER JOIN MODULOS "
                            + "ON CHAMADOS_SUPORTE.IdModulo=MODULOS.IdModulo "
                            + "INNER JOIN SOLICITANTES "
                            + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                            + "INNER JOIN ITENS_CHAMADOS_SUPORTE "
                            + "ON CHAMADOS_SUPORTE.IdCHSup=ITENS_CHAMADOS_SUPORTE.IdCHSup  "
                            + "WHERE DataCha BETWEEN'" + dataInicial + "' "
                            + "AND '" + dataFinal + "' "
                            + "AND ITENS_CHAMADOS_SUPORTE.TipoChamado='" + tipoSuporte + "'");
                }
            }
        }
    }//GEN-LAST:event_jBtPesqCHDataActionPerformed

    private void jBtSolicitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSolicitanteActionPerformed
        // TODO add your handling code here:
        count = 0;
        if (jPesqSolicitante.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "É necessário informar um nome ou parte do nome para pesquuisa.");
        } else {
            preencherTabelaChamados("SELECT * FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOFTWARE "
                    + "ON CHAMADOS_SUPORTE.IdSoftware=SOFTWARE.IdSoftware "
                    + "INNER JOIN MODULOS "
                    + "ON CHAMADOS_SUPORTE.IdModulo=MODULOS.IdModulo "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                    + "INNER JOIN ITENS_CHAMADOS_SUPORTE "
                    + "ON CHAMADOS_SUPORTE.IdCHSup=ITENS_CHAMADOS_SUPORTE.IdCHSup "
                    + "WHERE SOLICITANTES.NomeSolicitante LIKE'%" + jPesqSolicitante.getText() + "%' "
                    + "AND ITENS_CHAMADOS_SUPORTE.TipoChamado='" + tipoSuporte + "'");
        }
    }//GEN-LAST:event_jBtSolicitanteActionPerformed

    private void jTabelaChamdosSupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaChamdosSupMouseClicked
        // TODO add your handling code here:
        flag = 1;
        if (flag == 1) {
            String idSoli = "" + jTabelaChamdosSup.getValueAt(jTabelaChamdosSup.getSelectedRow(), 0);
            jIdChamadoPesquisa.setText(idSoli);
        }
    }//GEN-LAST:event_jTabelaChamdosSupMouseClicked

    private void jBtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtConfirmarActionPerformed
        // TODO add your handling code here:
        flag = 1;
        if (flag == 1) {
            String idSoli = "" + jTabelaChamdosSup.getValueAt(jTabelaChamdosSup.getSelectedRow(), 0);
            jIdChamadoPesquisa.setText(idSoli);
            //
            conecta.abrirConexao();
            try {
                conecta.executaSQL("SELECT * FROM CHAMADOS_SUPORTE "
                        + "INNER JOIN USUARIOS "
                        + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                        + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                        + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                        + "INNER JOIN SOFTWARE "
                        + "ON CHAMADOS_SUPORTE.IdSoftware=SOFTWARE.IdSoftware "
                        + "INNER JOIN MODULOS "
                        + "ON CHAMADOS_SUPORTE.IdModulo=MODULOS.IdModulo "
                        + "INNER JOIN SOLICITANTES "
                        + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                        + "INNER JOIN ITENS_CHAMADOS_SUPORTE "
                        + "ON CHAMADOS_SUPORTE.IdCHSup=ITENS_CHAMADOS_SUPORTE.IdCHSup "
                        + "WHERE CHAMADOS_SUPORTE.IdCHSup='" + idSoli + "'");
                conecta.rs.first();
                jIdChamado.setText(String.valueOf(conecta.rs.getInt("IdCHSup")));
                jSoftware.setText(conecta.rs.getString("DescricaoSoftware"));
                jModulo.setText(conecta.rs.getString("DescricaoModulo"));
                jTextoSuporte.setText(conecta.rs.getString("TextoSuporte"));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(rootPane, "ERRO na pesquisa dos dados.\nERROR: " + e);
            }
            conecta.desconecta();
        }
        dispose();
    }//GEN-LAST:event_jBtConfirmarActionPerformed

    private void jBtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSairActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jBtSairActionPerformed

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
            java.util.logging.Logger.getLogger(TelaBuscarChamadosSuporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaBuscarChamadosSuporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaBuscarChamadosSuporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaBuscarChamadosSuporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaBuscarChamadosSuporte dialog = new TelaBuscarChamadosSuporte(chamaDes, true);
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
    private javax.swing.JButton jBtConfirmar;
    private javax.swing.JButton jBtPesqCHCodigo;
    private javax.swing.JButton jBtPesqCHData;
    private javax.swing.JButton jBtSair;
    private javax.swing.JButton jBtSolicitante;
    private javax.swing.JCheckBox jCheckBoxTodosCH;
    private com.toedter.calendar.JDateChooser jDataPesFinal;
    private com.toedter.calendar.JDateChooser jDataPesqInicial;
    private javax.swing.JTextField jIdChamadoPesquisa;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JTextField jPesqSolicitante;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabelaChamdosSup;
    private javax.swing.JLabel jtotalRegistros;
    // End of variables declaration//GEN-END:variables

    public void preencherTabelaChamados(String sql) {
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{"Código", "Data", "Solicitante", "Texto do Suporte Técnico"};
        conecta.abrirConexao();
        conecta.executaSQL(sql);
        try {
            conecta.rs.first();
            do {
                // Formatar a data no formato Brasil
                dataBrasil = conecta.rs.getString("DataItemCh");
                String dia = dataBrasil.substring(8, 10);
                String mes = dataBrasil.substring(5, 7);
                String ano = dataBrasil.substring(0, 4);
                dataBrasil = dia + "/" + mes + "/" + ano;
                count = count + 1;
                jtotalRegistros.setText(Integer.toString(count)); // Converter inteiro em string para exibir na tela
                dados.add(new Object[]{conecta.rs.getInt("IdItem"), dataBrasil, conecta.rs.getString("NomeSolicitante"), conecta.rs.getString("TextoSuporte")});
            } while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Não existem dados a serem exibidos....");
        }
        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        jTabelaChamdosSup.setModel(modelo);
        jTabelaChamdosSup.getColumnModel().getColumn(0).setPreferredWidth(70);
        jTabelaChamdosSup.getColumnModel().getColumn(0).setResizable(false);        
        jTabelaChamdosSup.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTabelaChamdosSup.getColumnModel().getColumn(1).setResizable(false);
        jTabelaChamdosSup.getColumnModel().getColumn(2).setPreferredWidth(300);
        jTabelaChamdosSup.getColumnModel().getColumn(2).setResizable(false);
        jTabelaChamdosSup.getColumnModel().getColumn(3).setPreferredWidth(550);
        jTabelaChamdosSup.getColumnModel().getColumn(3).setResizable(false);
        jTabelaChamdosSup.getTableHeader().setReorderingAllowed(false);
        jTabelaChamdosSup.setAutoResizeMode(jTabelaChamdosSup.AUTO_RESIZE_OFF);
        jTabelaChamdosSup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // MÉTODO PARA ALINHAR AS COLUNAS DA TABELA
        alinhaCeluasTabelaFornecedor();
        conecta.desconecta();
    }

    public void alinhaCeluasTabelaFornecedor() {
        //
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        //
        jTabelaChamdosSup.getColumnModel().getColumn(0).setCellRenderer(centralizado);
    }

    public void limparTabelaFornecedor() {
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{"Código", "Data", "Solicitante", "Texto do Suporte Técnico"};
        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        jTabelaChamdosSup.setModel(modelo);
        jTabelaChamdosSup.getColumnModel().getColumn(0).setPreferredWidth(70);
        jTabelaChamdosSup.getColumnModel().getColumn(0).setResizable(false);        
        jTabelaChamdosSup.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTabelaChamdosSup.getColumnModel().getColumn(1).setResizable(false);
        jTabelaChamdosSup.getColumnModel().getColumn(2).setPreferredWidth(300);
        jTabelaChamdosSup.getColumnModel().getColumn(2).setResizable(false);
        jTabelaChamdosSup.getColumnModel().getColumn(3).setPreferredWidth(550);
        jTabelaChamdosSup.getColumnModel().getColumn(3).setResizable(false);
        jTabelaChamdosSup.getTableHeader().setReorderingAllowed(false);
        jTabelaChamdosSup.setAutoResizeMode(jTabelaChamdosSup.AUTO_RESIZE_OFF);
        jTabelaChamdosSup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelo.getLinhas().clear();
    }
}
