/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Dao.CadastroPontoDAO;
import Dao.ControleAcessoGeral;
import Dao.LogSistemaDao;
import Modelo.CadastroPonto;
import Modelo.CamposAcessos;
import Modelo.LogSistema;
import Util.SQL.Utilitarios.CompararHoras;
import static Visao.LoginHD.nameUser;
import static Visao.LoginHD.tipoServidor;
import static Visao.TelaPrincipal.jDataSistema;
import static Visao.TelaPrincipal.jHoraSistema;
import static Visao.TelaPrincipal.telaRegistroPonto;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ronal
 */
public class TelaRegistroPontoTrabalho extends javax.swing.JInternalFrame {

    CadastroPonto objCadPonto = new CadastroPonto();
    CompararHoras objCompara = new CompararHoras();
    CadastroPontoDAO DAOponto = new CadastroPontoDAO();
    ControleAcessoGeral pPESQUISAR_acessos = new ControleAcessoGeral();
    CamposAcessos objCampos = new CamposAcessos();
    LogSistemaDao controlLog = new LogSistemaDao();
    LogSistema objLogSys = new LogSistema();
    //
    int acao = 0;
    String statusMov;
    String horaMov;
    String dataModFinal;
    String nomeModuloTela = "Suporte Técnico:Registro de Ponto:Manutenção";
    public static String pRESPOSTA_ponto = "";
    //
    public final static SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
    public final static String PERIODO_matutino = "12:00";
    public final static String PERIODO_vespertino = "18:00";
    public final static String PERIODO_noturno = "00:00";
    //
    boolean p1 = checkIfClosedM(jHoraSistema.getText());
    boolean p2 = checkIfClosedV(jHoraSistema.getText());
    boolean p3 = checkIfClosedN(jHoraSistema.getText());
    //
    public static String pCODIGO_PESQUISA_colaborador = "";
    public static String pCODIGO_ENCONTRADO_colaborador = "";
    public static String pNOME_USUARIO_colaborador = "";
    public static Date pDATA_pesquisa;
    //
    public static String pDATA_cadastro = "";
    String pDATA_PESQUISADA_convertida = "";
    public static String pTABELA_vazia = "";
    public static int qteDeRegistro = 0;
    public static int pTOTAL_registros = 0;
    String pSTATUS_ponto = "";
    String pSTATUS_entrada = "Entrada";
    String pSTATUS_saida = "Saida";
    String pSTATUS_ENTRADA_saida = "Entrada/Saida";
    Integer pIDHistorico = null;

    /**
     * Creates new form TelaRegistroPontoTrabalho
     */
    public TelaRegistroPontoTrabalho() {
        initComponents();
        corCampos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jIdHistoricoCU = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDataCadastro = new com.toedter.calendar.JDateChooser();
        jComboBoxPeriodo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jHoraInicial = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jNomeColaborador = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jBtNovo = new javax.swing.JButton();
        jBtSalvar = new javax.swing.JButton();
        jBtCancelar = new javax.swing.JButton();
        jBtSair = new javax.swing.JButton();
        jBtBiometria = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("...::: Registro de Ponto :::...");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true)));

        jIdHistoricoCU.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdHistoricoCU.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jIdHistoricoCU.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Código");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Data Cadastro");

        jDataCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jDataCadastro.setEnabled(false);

        jComboBoxPeriodo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBoxPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "Matutino", "Vespertino", "Noturno", " " }));
        jComboBoxPeriodo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jComboBoxPeriodo.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Período");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Horário");

        jHoraInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jHoraInicial.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jHoraInicial.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Nome do Colaborador");

        jNomeColaborador.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jNomeColaborador.setEnabled(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jNomeColaborador)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jIdHistoricoCU, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jComboBoxPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jHoraInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))))
                        .addGap(0, 45, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jIdHistoricoCU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHoraInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jNomeColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBoxPeriodo, jDataCadastro, jHoraInicial, jIdHistoricoCU});

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true)));

        jBtNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/page_add.png"))); // NOI18N
        jBtNovo.setToolTipText("Novo");
        jBtNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtNovoActionPerformed(evt);
            }
        });

        jBtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/1294_16x16.png"))); // NOI18N
        jBtSalvar.setToolTipText("Gravar");
        jBtSalvar.setEnabled(false);
        jBtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtSalvarActionPerformed(evt);
            }
        });

        jBtCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Button_Close_Icon_16.png"))); // NOI18N
        jBtCancelar.setToolTipText("Cancelar");
        jBtCancelar.setEnabled(false);
        jBtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtCancelarActionPerformed(evt);
            }
        });

        jBtSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/shutdown-icone-6920-16.png"))); // NOI18N
        jBtSair.setToolTipText("Sair");
        jBtSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtSairActionPerformed(evt);
            }
        });

        jBtBiometria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Biometria16Vermelho.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jBtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jBtCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(jBtBiometria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtSair, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtBiometria, jBtCancelar, jBtNovo, jBtSair, jBtSalvar});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jBtSair)
                    .addComponent(jBtBiometria)
                    .addComponent(jBtCancelar)
                    .addComponent(jBtSalvar)
                    .addComponent(jBtNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtBiometria, jBtCancelar, jBtNovo, jBtSair, jBtSalvar});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(313, 313, 313))
        );

        jTabbedPane1.addTab("Manutenção", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBounds(360, 60, 471, 237);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtNovoActionPerformed
        // TODO add your handling code here:
        objCampos.setNomeUsuario(nameUser);
        objCampos.setNomeTelaAcesso(telaRegistroPonto);
        pPESQUISAR_acessos.pesquisarUsuario(objCampos);
        pPESQUISAR_acessos.pesquisarTelasAcesso(objCampos);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || objCampos.getCodigoUsuario() == objCampos.getCodigoUsuarioAcesso() && objCampos.getNomeTelaAcesso().equals(telaRegistroPonto) && objCampos.getCodigoIncluir() == 1) {
            acao = 1;
            Novo();
            statusMov = "Incluiu";
            horaMov = jHoraSistema.getText();
            dataModFinal = jDataSistema.getText();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtNovoActionPerformed

    private void jBtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSalvarActionPerformed
        // TODO add your handling code here:
        objCampos.setNomeUsuario(nameUser);
        objCampos.setNomeTelaAcesso(telaRegistroPonto);
        pPESQUISAR_acessos.pesquisarUsuario(objCampos);
        pPESQUISAR_acessos.pesquisarTelasAcesso(objCampos);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || objCampos.getCodigoUsuario() == objCampos.getCodigoUsuarioAcesso() && objCampos.getNomeTelaAcesso().equals(telaRegistroPonto) && objCampos.getCodigoIncluir() == 1) {
            objCadPonto.setDataCadastro(jDataCadastro.getDate());
            objCadPonto.setNomeColaborador(jNomeColaborador.getText());
            VERIFICAR_DATA_ENTRADA_saida();
            if (Objects.equals(pCODIGO_PESQUISA_colaborador, pCODIGO_ENCONTRADO_colaborador)
                    && pDATA_PESQUISADA_convertida.equals(pDATA_cadastro)
                    && pSTATUS_ponto.equals(pSTATUS_entrada)) {
                objCadPonto.setIdHistoricoCU(objCadPonto.getIdHistoricoCU());
                objCadPonto.setDataSaida(jDataCadastro.getDate());
                objCadPonto.setHorarioSaida(jHoraInicial.getText());
                objCadPonto.setStatusPonto(pSTATUS_ENTRADA_saida);
                objCadPonto.setDataInsert(dataModFinal);
                objCadPonto.setHorarioInsert(horaMov);
                DAOponto.alterarSaidaPonto(objCadPonto);
                BUSCAR_codigo();
                objLog();
                controlLog.incluirLogSistema(objLogSys); // Grava o log da operação 
                Salvar();
                if (pRESPOSTA_ponto.equals("Sim")) {
                    JOptionPane.showMessageDialog(rootPane, "Registro gravado com sucesso.");
                } else if (pRESPOSTA_ponto.equals("Não")) {
                    JOptionPane.showMessageDialog(rootPane, "Não foi possível gravar o registro, tente novamente.");
                }
            } else if (Objects.equals(pCODIGO_PESQUISA_colaborador, pCODIGO_ENCONTRADO_colaborador)
                    && pDATA_PESQUISADA_convertida.equals(pDATA_cadastro)
                    && pSTATUS_ponto.equals(pSTATUS_saida)) {
                objCadPonto.setIdHistoricoCU(objCadPonto.getIdHistoricoCU());
                objCadPonto.setStatusPonto(pSTATUS_entrada);
                objCadPonto.setDataEntrada(jDataCadastro.getDate());
                objCadPonto.setHorarioEntrada(jHoraInicial.getText());
                objCadPonto.setDataInsert(dataModFinal);
                objCadPonto.setHorarioInsert(horaMov);
                DAOponto.alterarEntradaPonto(objCadPonto);
                BUSCAR_codigo();
                objLog();
                controlLog.incluirLogSistema(objLogSys); // Grava o log da operação 
                Salvar();
                if (pRESPOSTA_ponto.equals("Sim")) {
                    JOptionPane.showMessageDialog(rootPane, "Registro gravado com sucesso.");
                } else if (pRESPOSTA_ponto.equals("Não")) {
                    JOptionPane.showMessageDialog(rootPane, "Não foi possível gravar o registro, tente novamente.");
                }
            } else {
                objCadPonto.setStatusPonto(pSTATUS_entrada);
                objCadPonto.setDataEntrada(jDataCadastro.getDate());
                objCadPonto.setHorarioEntrada(jHoraInicial.getText());
                objCadPonto.setDataInsert(dataModFinal);
                objCadPonto.setHorarioInsert(horaMov);
                DAOponto.incluirEntradaPonto(objCadPonto);
                BUSCAR_codigo();
                objLog();
                controlLog.incluirLogSistema(objLogSys); // Grava o log da operação 
                Salvar();
                if (pRESPOSTA_ponto.equals("Sim")) {
                    JOptionPane.showMessageDialog(rootPane, "Registro gravado com sucesso.");
                } else if (pRESPOSTA_ponto.equals("Não")) {
                    JOptionPane.showMessageDialog(rootPane, "Não foi possível gravar o registro, tente novamente.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtSalvarActionPerformed

    private void jBtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtCancelarActionPerformed
        // TODO add your handling code here:
        Cancelar();
    }//GEN-LAST:event_jBtCancelarActionPerformed

    private void jBtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSairActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jBtSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtBiometria;
    private javax.swing.JButton jBtCancelar;
    private javax.swing.JButton jBtNovo;
    private javax.swing.JButton jBtSair;
    private javax.swing.JButton jBtSalvar;
    private javax.swing.JComboBox<String> jComboBoxPeriodo;
    public static com.toedter.calendar.JDateChooser jDataCadastro;
    public static javax.swing.JTextField jHoraInicial;
    public static javax.swing.JTextField jIdHistoricoCU;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JTextField jNomeColaborador;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

    public void corCampos() {
        jIdHistoricoCU.setBackground(Color.white);
        jComboBoxPeriodo.setBackground(Color.white);
        jDataCadastro.setBackground(Color.white);
        jHoraInicial.setBackground(Color.white);
        jNomeColaborador.setBackground(Color.white);
    }

    public void bloquearCampos(boolean opcao) {
        jIdHistoricoCU.setEnabled(opcao);
        jComboBoxPeriodo.setEnabled(opcao);
        jDataCadastro.setEnabled(opcao);
        jHoraInicial.setEnabled(opcao);
        jNomeColaborador.setEnabled(opcao);
    }

    public void bloquearBotoes(boolean opcao) {
        jBtNovo.setEnabled(opcao);
        jBtSalvar.setEnabled(opcao);
    }

    public void limparCampos() {
        jIdHistoricoCU.setText("");
        jDataCadastro.setDate(null);
        jHoraInicial.setText("");
        jNomeColaborador.setText("");
    }

    public void Novo() {
        bloquearBotoes(!true);
        limparCampos();
        jDataCadastro.setCalendar(Calendar.getInstance());
        jNomeColaborador.setText(nameUser);
        jHoraInicial.setText(jHoraSistema.getText());
        jBtSalvar.setEnabled(true);
        jBtCancelar.setEnabled(true);
        VERIFICAR_periodos();
        objCadPonto.setPeriodo((String) jComboBoxPeriodo.getSelectedItem());
    }

    public void Salvar() {
        bloquearBotoes(!true);
        bloquearCampos(!true);
        jBtNovo.setEnabled(true);
    }

    public void Cancelar() {
        bloquearBotoes(!true);
        bloquearCampos(!true);
        limparCampos();
        jBtNovo.setEnabled(true);
    }

    public void BUSCAR_codigo() {
        DAOponto.BUSCAR_ULTIMO_codigo(objCadPonto);
    }

    public void VERIFICAR_DATA_ENTRADA_saida() {
        if (tipoServidor == null || tipoServidor.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "É necessário definir o parâmtero para o sistema operacional utilizado no servidor, (UBUNTU-LINUX ou WINDOWS SERVER).");
        } else if (tipoServidor.equals("Servidor Linux (Ubuntu)/MS-SQL Server")) {
            DAOponto.PESQUISAR_COLABORADOR_usuario(objCadPonto);
            DAOponto.VERIFICAR_TABELA_vazia(objCadPonto);
            if (pTABELA_vazia.equals("Não")) {
                SimpleDateFormat formatoAmerica = new SimpleDateFormat("yyyy/MM/dd");
                pDATA_cadastro = formatoAmerica.format(jDataCadastro.getDate().getTime());
                DAOponto.VERIFICAR_TABELA_historico(objCadPonto);
                pIDHistorico = objCadPonto.getIdHistoricoCU();
                pCODIGO_ENCONTRADO_colaborador = String.valueOf(objCadPonto.getIdColaborador());
                pDATA_pesquisa = objCadPonto.getDataEntrada();
                pSTATUS_ponto = objCadPonto.getStatusPonto();
                pDATA_PESQUISADA_convertida = formatoAmerica.format(pDATA_pesquisa);
            }
        } else if (tipoServidor.equals("Servidor Windows/MS-SQL Server")) {
            DAOponto.PESQUISAR_COLABORADOR_usuario(objCadPonto);
            DAOponto.VERIFICAR_TABELA_vazia(objCadPonto);
            if (pTABELA_vazia.equals("Não")) {
                SimpleDateFormat formatoAmerica = new SimpleDateFormat("yyyy/MM/dd");
                pDATA_cadastro = formatoAmerica.format(jDataCadastro.getDate().getTime());
                DAOponto.VERIFICAR_TABELA_historico(objCadPonto);
                pCODIGO_ENCONTRADO_colaborador = String.valueOf(objCadPonto.getIdColaborador());
                pDATA_pesquisa = objCadPonto.getDataEntrada();
                pSTATUS_ponto = objCadPonto.getStatusPonto();
                pDATA_PESQUISADA_convertida = formatoAmerica.format(pDATA_pesquisa);
            }
        }
    }

    public void objLog() {
        objLogSys.setDataMov(dataModFinal);
        objLogSys.setHorarioMov(horaMov);
        objLogSys.setNomeModuloTela(nomeModuloTela);
        objLogSys.setIdLancMov(Integer.valueOf(jIdHistoricoCU.getText()));
        objLogSys.setNomeUsuarioLogado(nameUser);
        objLogSys.setStatusMov(statusMov);
    }

    public void VERIFICAR_periodos() {
        if (p1 == true) {
            jComboBoxPeriodo.setSelectedItem("Matutino");
        } else if (p2 == true) {
            jComboBoxPeriodo.setSelectedItem("Vespertino");
        } else if (p3 == true) {
            jComboBoxPeriodo.setSelectedItem("Noturno");
        }
    }

    public static boolean checkIfClosedM(String time1) {
        try {
            Date present1 = parser.parse(time1);
            Date closed1 = parser.parse(PERIODO_matutino);
            if (present1.after(closed1)) {
                return false;
            }
        } catch (ParseException e) {
            // Invalid date was entered
        }
        return true;
    }

    public static boolean checkIfClosedV(String time2) {
        try {
            Date present2 = parser.parse(time2);
            Date closed2 = parser.parse(PERIODO_vespertino);
            if (present2.after(closed2)) {
                return false;
            }
        } catch (ParseException e) {
            // Invalid date was entered
        }
        return true;
    }

    public static boolean checkIfClosedN(String time3) {
        try {
            Date present3 = parser.parse(time3);
            Date closed3 = parser.parse(PERIODO_noturno);
            if (present3.after(closed3)) {
                return true;
            }
        } catch (ParseException e) {
            // Invalid date was entered
        }
        return false;
    }
}
