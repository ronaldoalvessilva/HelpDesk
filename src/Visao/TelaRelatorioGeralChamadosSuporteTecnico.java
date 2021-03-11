/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Dao.ConexaoBancoDados;
import static Visao.LoginHD.nameUser;
import static Visao.TelaPrincipal.tipoServidor;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ronaldo.silva7
 */
public class TelaRelatorioGeralChamadosSuporteTecnico extends javax.swing.JInternalFrame {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    //
    String pDATA_PESQUISA_inicial;
    String pDATA_PESQUISA_final;

    /**
     * Creates new form TelaRelatorioGeralChamadosSuporteTecnico
     */
    public TelaRelatorioGeralChamadosSuporteTecnico() {
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
        jDataPesqInicial = new com.toedter.calendar.JDateChooser();
        jDataPesFinal = new com.toedter.calendar.JDateChooser();
        jBtConfirmar = new javax.swing.JButton();
        jBtSair = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("...::: Relatório Geral de Chamados Suporte Técnico :::...");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Data Inicial");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Data Final");

        jDataPesqInicial.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jDataPesFinal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDataPesqInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDataPesFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jDataPesqInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDataPesFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jBtConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtSair))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtConfirmar, jBtSair});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtConfirmar)
                    .addComponent(jBtSair))
                .addGap(20, 20, 20))
        );

        setBounds(500, 120, 399, 139);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtConfirmarActionPerformed
        // TODO add your handling code here:
        if (tipoServidor == null || tipoServidor.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "É necessário definir o parâmtero para o sistema operacional utilizado no servidor, (UBUNTU-LINUX ou WINDOWS SERVER).");
        } else if (tipoServidor.equals("Servidor Linux (Ubuntu)/MS-SQL Server")) {
            SimpleDateFormat formatoAmerica = new SimpleDateFormat("yyyy/MM/dd");
            pDATA_PESQUISA_inicial = formatoAmerica.format(jDataPesqInicial.getDate().getTime());
            pDATA_PESQUISA_final = formatoAmerica.format(jDataPesFinal.getDate().getTime());
            final ViewAguardeProcessando carregando = new ViewAguardeProcessando(); //Teste tela aguarde
            carregando.setVisible(true);//Teste tela aguarde
            Thread t = new Thread() { //Teste tela aguarde
                public void run() { //Teste
                    try {
                        conecta.abrirConexao();
                        String path = "reports/RelatorioChamadosSuporte.jasper";
                        conecta.executaSQL("SELECT "
                                + "CHAMADOS_SUPORTE.IdCHSup, "
                                + "CHAMADOS_SUPORTE.StatusCha, "
                                + "CHAMADOS_SUPORTE.DataCha, "
                                + "CHAMADOS_SUPORTE.AssuntoSuporte, "
                                + "CHAMADOS_SUPORTE.TipoChamadoSuporte, "
                                + "CHAMADOS_SUPORTE.IdUsuario, "
                                + "CHAMADOS_SUPORTE.IdSolicitante, "
                                + "SOLICITANTES.NomeSolicitante, "
                                + "CHAMADOS_SUPORTE.IdAtendente, "
                                + "ATENDENTES.NomeAtendente, "
                                + "ITENS_CHAMADOS_SUPORTE.IdSoftware, "
                                + "SOFTWARE.DescricaoSoftware, "
                                + "ITENS_CHAMADOS_SUPORTE.IdModulo, "
                                + "MODULOS.DescricaoModulo, "
                                + "ITENS_CHAMADOS_SUPORTE.DataItemCh, "
                                + "ITENS_CHAMADOS_SUPORTE.HorarioInicio, "
                                + "ITENS_CHAMADOS_SUPORTE.HorarioTermino, "
                                + "ITENS_CHAMADOS_SUPORTE.TextoSuporte, "
                                + "ITENS_CHAMADOS_SUPORTE.TextoDesenvol "
                                + "FROM CHAMADOS_SUPORTE "
                                + "INNER JOIN ITENS_CHAMADOS_SUPORTE "
                                + "ON CHAMADOS_SUPORTE.IdCHSup=ITENS_CHAMADOS_SUPORTE.IdCHSup "
                                + "INNER JOIN USUARIOS "
                                + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                                + "INNER JOIN SOLICITANTES "
                                + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                                + "INNER JOIN ATENDENTES "
                                + "ON CHAMADOS_SUPORTE.IdAtendente=ATENDENTES.IdAtendente "
                                + "INNER JOIN SOFTWARE "
                                + "ON ITENS_CHAMADOS_SUPORTE.IdSoftware=SOFTWARE.IdSoftware "
                                + "INNER JOIN MODULOS "
                                + "ON ITENS_CHAMADOS_SUPORTE.IdModulo=MODULOS.IdModulo "
                                + "WHERE CHAMADOS_SUPORTE.DataCha BETWEEN'" + pDATA_PESQUISA_inicial + "' "
                                + "AND'" + pDATA_PESQUISA_final + "'");
                        HashMap parametros = new HashMap();
                        parametros.put("pNOME_usuario", nameUser);
                        parametros.put("dataInicial", pDATA_PESQUISA_inicial);
                        parametros.put("dataFinal", pDATA_PESQUISA_final);
                        JRResultSetDataSource relatResul = new JRResultSetDataSource(conecta.rs); // Passa o resulSet Preenchido para o relatorio                                   
                        JasperPrint jpPrint = JasperFillManager.fillReport(path, parametros, relatResul); // indica o caminmho do relatório
                        JasperViewer jv = new JasperViewer(jpPrint, false); // Cria instancia para impressao          
                        jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH); // Maximizar o relatório
                        jv.setTitle("Relatório Geral de Chamados de Suporte Técnico"); // Titulo do relatório
                        jv.setVisible(true); // Chama o relatorio para ser visualizado                                    
                        jv.toFront(); // Traz o relatorio para frente da aplicação   
                        carregando.dispose(); //Teste tela aguarde
                        conecta.desconecta();
                    } catch (JRException e) {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao chamar o Relatório.\n\nERRO :" + e);
                    }
                }
            }; //Teste tela aguarde
            t.start(); //Teste tela aguarde
        } else if (tipoServidor.equals("Servidor Windows/MS-SQL Server")) {
            SimpleDateFormat formatoAmerica = new SimpleDateFormat("dd/MM/yyyy");
            pDATA_PESQUISA_inicial = formatoAmerica.format(jDataPesqInicial.getDate().getTime());
            pDATA_PESQUISA_final = formatoAmerica.format(jDataPesFinal.getDate().getTime());
            final ViewAguardeProcessando carregando = new ViewAguardeProcessando(); //Teste tela aguarde
            carregando.setVisible(true);//Teste tela aguarde
            Thread t = new Thread() { //Teste tela aguarde
                public void run() { //Teste
                    try {
                        conecta.abrirConexao();
                        String path = "reports/RelatorioChamadosSuporte.jasper";
                        conecta.executaSQL("SELECT "
                                + "CHAMADOS_SUPORTE.IdCHSup, "
                                + "CHAMADOS_SUPORTE.StatusCha, "
                                + "CHAMADOS_SUPORTE.DataCha, "
                                + "CHAMADOS_SUPORTE.AssuntoSuporte, "
                                + "CHAMADOS_SUPORTE.TipoChamadoSuporte, "
                                + "CHAMADOS_SUPORTE.IdUsuario, "
                                + "CHAMADOS_SUPORTE.IdSolicitante, "
                                + "SOLICITANTES.NomeSolicitante, "
                                + "CHAMADOS_SUPORTE.IdAtendente, "
                                + "ATENDENTES.NomeAtendente, "
                                + "ITENS_CHAMADOS_SUPORTE.IdSoftware, "
                                + "SOFTWARE.DescricaoSoftware, "
                                + "ITENS_CHAMADOS_SUPORTE.IdModulo, "
                                + "MODULOS.DescricaoModulo, "
                                + "ITENS_CHAMADOS_SUPORTE.DataItemCh, "
                                + "ITENS_CHAMADOS_SUPORTE.HorarioInicio, "
                                + "ITENS_CHAMADOS_SUPORTE.HorarioTermino, "
                                + "ITENS_CHAMADOS_SUPORTE.TextoSuporte, "
                                + "ITENS_CHAMADOS_SUPORTE.TextoDesenvol "
                                + "FROM CHAMADOS_SUPORTE "
                                + "INNER JOIN ITENS_CHAMADOS_SUPORTE "
                                + "ON CHAMADOS_SUPORTE.IdCHSup=ITENS_CHAMADOS_SUPORTE.IdCHSup "
                                + "INNER JOIN USUARIOS "
                                + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                                + "INNER JOIN SOLICITANTES "
                                + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                                + "INNER JOIN ATENDENTES "
                                + "ON CHAMADOS_SUPORTE.IdAtendente=ATENDENTES.IdAtendente "
                                + "INNER JOIN SOFTWARE "
                                + "ON ITENS_CHAMADOS_SUPORTE.IdSoftware=SOFTWARE.IdSoftware "
                                + "INNER JOIN MODULOS "
                                + "ON ITENS_CHAMADOS_SUPORTE.IdModulo=MODULOS.IdModulo "
                                + "WHERE CHAMADOS_SUPORTE.DataCha BETWEEN'" + pDATA_PESQUISA_inicial + "' "
                                + "AND'" + pDATA_PESQUISA_final + "'");
                        HashMap parametros = new HashMap();
                        parametros.put("pNOME_usuario", nameUser);
                        parametros.put("dataInicial", pDATA_PESQUISA_inicial);
                        parametros.put("dataFinal", pDATA_PESQUISA_final);
                        JRResultSetDataSource relatResul = new JRResultSetDataSource(conecta.rs); // Passa o resulSet Preenchido para o relatorio                                   
                        JasperPrint jpPrint = JasperFillManager.fillReport(path, parametros, relatResul); // indica o caminmho do relatório
                        JasperViewer jv = new JasperViewer(jpPrint, false); // Cria instancia para impressao          
                        jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH); // Maximizar o relatório
                        jv.setTitle("Relatório Geral de Chamados de Suporte Técnico"); // Titulo do relatório
                        jv.setVisible(true); // Chama o relatorio para ser visualizado                                    
                        jv.toFront(); // Traz o relatorio para frente da aplicação   
                        carregando.dispose(); //Teste tela aguarde
                        conecta.desconecta();
                    } catch (JRException e) {
                        JOptionPane.showMessageDialog(rootPane, "Erro ao chamar o Relatório.\n\nERRO :" + e);
                    }
                }
            }; //Teste tela aguarde
            t.start(); //Teste tela aguarde
        }
    }//GEN-LAST:event_jBtConfirmarActionPerformed

    private void jBtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSairActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jBtSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtConfirmar;
    private javax.swing.JButton jBtSair;
    private com.toedter.calendar.JDateChooser jDataPesFinal;
    private com.toedter.calendar.JDateChooser jDataPesqInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
