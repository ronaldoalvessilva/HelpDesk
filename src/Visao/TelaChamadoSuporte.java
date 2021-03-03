/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Controle.ModeloTabela;
import Dao.ChamadosSuporteDao;
import Dao.ConexaoBancoDados;
import Dao.LogSistemaDao;
import Modelo.ChamadoSuporte;
import Modelo.LogSistema;
import static Visao.LoginHD.nameUser;
import static Visao.TelaPrincipal.botaoEncerrarSup;
import static Visao.TelaPrincipal.botaoEnviarSup;
import static Visao.TelaPrincipal.botaoImprimirSup;
import static Visao.TelaPrincipal.botaoReabrirSup;
import static Visao.TelaPrincipal.codAbrir;
import static Visao.TelaPrincipal.codAlterar;
import static Visao.TelaPrincipal.codConsultar;
import static Visao.TelaPrincipal.codExcluir;
import static Visao.TelaPrincipal.codGravar;
import static Visao.TelaPrincipal.codIncluir;
import static Visao.TelaPrincipal.codUserAcesso;
import static Visao.TelaPrincipal.codigoUser;
import static Visao.TelaPrincipal.jDataSistema;
import static Visao.TelaPrincipal.jHoraSistema;
import static Visao.TelaPrincipal.jPainelPrincipal;
import static Visao.TelaPrincipal.nomeTela;
import static Visao.TelaPrincipal.telaChamadosSuporte;
import static Visao.TelaPrincipal.telaItensChamadoSuporte;
import static Visao.TelaPrincipal.tipoServidor;
import static Visao.TelaPrincipal.nomeUserRegistro;
import java.awt.Color;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ronal
 */
public class TelaChamadoSuporte extends javax.swing.JInternalFrame {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    ChamadoSuporte objCHSup = new ChamadoSuporte();
    ChamadosSuporteDao CONTROL = new ChamadosSuporteDao();
    //
    LogSistemaDao controlLog = new LogSistemaDao();
    LogSistema objLogSys = new LogSistema();
    // Variáveis para gravar o log
    String nomeModuloTela = "Suporte Técnico:Chamados:Manutenção";
    String statusMov;
    String horaMov;
    String dataModFinal;
    int flag, acao;
    int count;
    int count0;
    public static int idItemChamado;
    public static String dataInicial;
    public static String dataFinal;
    String dataBrasil;
    String dataEntrada;
    //
    public static int idUnidade;
    public static int idEmpresa;
    public static int idSolicitante;
    public static int idSoftware;
    public static int idModulo;
    //
    String statusEncerrado = "ENCERRADO NO SUPORTE TÉCNICO";
    String statusReabrir = "REABERTO NO SUPORTE TÉCNICO";
    String statusDesenvol = "ENVIADO PARA O DESENVOLVIMENTO";
    //
    String tipoSuporte = "SUPORTE TÉCNICO";
    //
    public static int codigoSoftware = 0;
    public static int codigoModulo = 0;
    // FILTRAR CHAMADOS POR ATENTENTE
    public static String nomeAtendente = "";
    int nivelUsuario = 0;
    String caminhoFigura1 = null;
    String caminhoFigura2 = null;
    String caminhoFigura3 = null;
    String caminhoFigura4 = null;
    //
    byte[] persona_imagem = null;
    byte[] persona_imagem1 = null;
    byte[] persona_imagem2 = null;
    byte[] persona_imagem3 = null;
    //
    String pSTATUS_atendente = "Ativo";
    String pDATA_Registros;
    String pDATA_itens;
    public static int pTOTAL_registros = 0;
    public static int pTOTAL_itens = 0;
    public static String idSoli;
    public static String idItem;

    /**
     * Creates new form TelaChamadoSuporte
     */
    public static TelaPesquisaSolicitanteCH pesqSoli;
    public static TelaPesquisaSoftwareModulo pesqSM;
    public static TelaEnvioChamadoSuporteDesenvolvimento enviaDadosDes;
    public static PdfView pPDF_ANEXO;
    public static TelaFotoHelpDesk1 pFOTO1;
    public static TelaFotoHelpDesk2 pFOTO2;
    public static TelaFotoHelpDesk3 pFOTO3;
    public static TelaFotoHelpDesk4 pFOTO4;

    public TelaChamadoSuporte() {
        initComponents();
        formatarCampos();
        corCampos();
    }

    public void mostrarPesquisa() {
        pesqSoli = new TelaPesquisaSolicitanteCH(this, true);
        pesqSoli.setVisible(true);
    }

    public void mostrarPesquisaSoft() {
        pesqSM = new TelaPesquisaSoftwareModulo(this, true);
        pesqSM.setVisible(true);
    }

    public void mostrarTelaEnvioChamdosDes() {
        enviaDadosDes = new TelaEnvioChamadoSuporteDesenvolvimento(this, true);
        enviaDadosDes.setVisible(true);
    }

    public void mostrarPdf() {
        pPDF_ANEXO = new PdfView(this, true);
        pPDF_ANEXO.setVisible(true);
    }

    public void mostraTelaFotoDoc1() {
        pFOTO1 = new TelaFotoHelpDesk1(this, true);
        pFOTO1.setVisible(true);
    }

    public void mostraTelaFotoDoc2() {
        pFOTO2 = new TelaFotoHelpDesk2(this, true);
        pFOTO2.setVisible(true);
    }

    public void mostraTelaFotoDoc3() {
        pFOTO3 = new TelaFotoHelpDesk3(this, true);
        pFOTO3.setVisible(true);
    }

    public void mostraTelaFotoDoc4() {
        pFOTO4 = new TelaFotoHelpDesk4(this, true);
        pFOTO4.setVisible(true);
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaChamdosSup = new javax.swing.JTable();
        jPanel31 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jtotalRegistros = new javax.swing.JLabel();
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
        jLabel19 = new javax.swing.JLabel();
        jPesquisarAssunto = new javax.swing.JTextField();
        jBtAssunto = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDataChamado = new com.toedter.calendar.JDateChooser();
        jIdChamado = new javax.swing.JTextField();
        jStatusChamado = new javax.swing.JTextField();
        jSolicitante = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jUnidadePrisional = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jBtPesquisaSoli = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jAssunto = new javax.swing.JTextField();
        jComboBoxAtendente = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jComboBoxTipoChamadoSuporte = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jBtEncerrar = new javax.swing.JButton();
        jBtImprimir = new javax.swing.JButton();
        jBtEnviar = new javax.swing.JButton();
        jBtReabrir = new javax.swing.JButton();
        jBtSair = new javax.swing.JButton();
        jBtAuditoriaItem = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jBtNovo = new javax.swing.JButton();
        jBtAlterar = new javax.swing.JButton();
        jBtExcluir = new javax.swing.JButton();
        jBtSalvar = new javax.swing.JButton();
        jBtCancelar = new javax.swing.JButton();
        jBtAuditoria = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTabelaItens = new javax.swing.JTable();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextoSuporte = new javax.swing.JTextArea();
        jHorarioInicio = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jHorarioTermino = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDataOcorrencia = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jIdItem = new javax.swing.JTextField();
        jSoftware = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jModulo = new javax.swing.JTextField();
        jBtPesquisaModulo = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jBtSalvarItem = new javax.swing.JButton();
        jBtCancelarItem = new javax.swing.JButton();
        jBtAlterarItem = new javax.swing.JButton();
        jBtNovoItem = new javax.swing.JButton();
        jBtExcluirItem = new javax.swing.JButton();
        jBtAnexarArquivo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jFigura1 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jFigura2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jFigura3 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jFigura4 = new javax.swing.JLabel();
        jBtNovaFigura1 = new javax.swing.JButton();
        jBtExcluirFigura1 = new javax.swing.JButton();
        jBtVisualizaFigura1 = new javax.swing.JButton();
        jBtNovaFigura2 = new javax.swing.JButton();
        jBtExcluirFigura2 = new javax.swing.JButton();
        jBtVisualizaFigura2 = new javax.swing.JButton();
        jBtNovaFigura3 = new javax.swing.JButton();
        jBtExcluirFigura3 = new javax.swing.JButton();
        jBtVisualizaFigura3 = new javax.swing.JButton();
        jBtNovaFigura4 = new javax.swing.JButton();
        jBtExcluirFigura4 = new javax.swing.JButton();
        jBtVisualizaFigura4 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextoDesenvolvimento = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setTitle("...::: Chamados de Suporte Técnico :::...");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/HelpDeskFem_16.png"))); // NOI18N

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jTabelaChamdosSup.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTabelaChamdosSup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Data", "Status", "Assunto", "Solicitante", "Unidade Prisional"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTabelaChamdosSup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaChamdosSupMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabelaChamdosSup);
        if (jTabelaChamdosSup.getColumnModel().getColumnCount() > 0) {
            jTabelaChamdosSup.getColumnModel().getColumn(0).setMinWidth(70);
            jTabelaChamdosSup.getColumnModel().getColumn(0).setMaxWidth(70);
            jTabelaChamdosSup.getColumnModel().getColumn(1).setMinWidth(70);
            jTabelaChamdosSup.getColumnModel().getColumn(1).setMaxWidth(70);
            jTabelaChamdosSup.getColumnModel().getColumn(2).setMinWidth(250);
            jTabelaChamdosSup.getColumnModel().getColumn(2).setMaxWidth(25);
            jTabelaChamdosSup.getColumnModel().getColumn(3).setMinWidth(300);
            jTabelaChamdosSup.getColumnModel().getColumn(3).setMaxWidth(300);
            jTabelaChamdosSup.getColumnModel().getColumn(4).setMinWidth(300);
            jTabelaChamdosSup.getColumnModel().getColumn(4).setMaxWidth(300);
            jTabelaChamdosSup.getColumnModel().getColumn(5).setMinWidth(300);
            jTabelaChamdosSup.getColumnModel().getColumn(5).setMaxWidth(300);
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

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Assunto:");

        jPesquisarAssunto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jBtAssunto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Lupas_1338_05.gif"))); // NOI18N
        jBtAssunto.setContentAreaFilled(false);
        jBtAssunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtAssuntoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jPesqSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jIdChamadoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtPesqCHCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBoxTodosCH)))
                        .addGap(114, 114, 114))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jDataPesqInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDataPesFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtPesqCHData, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jPesquisarAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPesqSolicitante, jPesquisarAssunto});

        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(jIdChamadoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtPesqCHCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxTodosCH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel15)
                                .addComponent(jDataPesFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)
                                .addComponent(jBtPesqCHData, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDataPesqInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jBtSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPesqSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jPesquisarAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jBtAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtPesqCHCodigo, jIdChamadoPesquisa});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jTabbedPane1.addTab("Listagem", jPanel3);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Código");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Status");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Data Chamado");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Atendente");

        jDataChamado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jDataChamado.setEnabled(false);

        jIdChamado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdChamado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jIdChamado.setEnabled(false);

        jStatusChamado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jStatusChamado.setForeground(new java.awt.Color(204, 0, 0));
        jStatusChamado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jStatusChamado.setDisabledTextColor(new java.awt.Color(204, 0, 0));
        jStatusChamado.setEnabled(false);

        jSolicitante.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jSolicitante.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Solicitante");

        jUnidadePrisional.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jUnidadePrisional.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Unidade Prisional");

        jBtPesquisaSoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Lupas_1338_05.gif"))); // NOI18N
        jBtPesquisaSoli.setContentAreaFilled(false);
        jBtPesquisaSoli.setEnabled(false);
        jBtPesquisaSoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtPesquisaSoliActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Assunto");

        jAssunto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jAssunto.setEnabled(false);

        jComboBoxAtendente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBoxAtendente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione..." }));
        jComboBoxAtendente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jComboBoxAtendente.setEnabled(false);
        jComboBoxAtendente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBoxAtendenteMouseEntered(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Tipos de Chamados");

        jComboBoxTipoChamadoSuporte.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBoxTipoChamadoSuporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "Avaliação", "Correções", "Dúvidas", "Erros de Sistema", "Implementação (SISCONP)", "Manutenção Hardware (CPU)", "Manutenção Software", "Manutenção em Servidores", "Manutenção Impressoras", "Manutenção em Rede", "Melhorias (Imp. Diversas)", "Problema(s)", "Reclamação(ões)", "Sugestão(ões)" }));
        jComboBoxTipoChamadoSuporte.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jComboBoxTipoChamadoSuporte.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jUnidadePrisional, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtPesquisaSoli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel17))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jIdChamado, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jStatusChamado, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDataChamado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxAtendente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jComboBoxTipoChamadoSuporte, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jDataChamado, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jStatusChamado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jIdChamado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxAtendente, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jUnidadePrisional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtPesquisaSoli))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jComboBoxTipoChamadoSuporte, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true)));

        jBtEncerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/061218140238_16.png"))); // NOI18N
        jBtEncerrar.setText("Encerrar");
        jBtEncerrar.setToolTipText("Encerrar Chamado");
        jBtEncerrar.setEnabled(false);
        jBtEncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtEncerrarActionPerformed(evt);
            }
        });

        jBtImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/gtklp-icone-3770-16.png"))); // NOI18N
        jBtImprimir.setText("Imprimir");
        jBtImprimir.setToolTipText("Imprimir Chamado");
        jBtImprimir.setEnabled(false);
        jBtImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtImprimirActionPerformed(evt);
            }
        });

        jBtEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/upload-16.png"))); // NOI18N
        jBtEnviar.setText("Enviar");
        jBtEnviar.setToolTipText("Enviar para Desenvolvimento");
        jBtEnviar.setEnabled(false);
        jBtEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtEnviarActionPerformed(evt);
            }
        });

        jBtReabrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fileopen.png"))); // NOI18N
        jBtReabrir.setText("Reabrir");
        jBtReabrir.setToolTipText("Reabrir Chamado");
        jBtReabrir.setEnabled(false);
        jBtReabrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtReabrirActionPerformed(evt);
            }
        });

        jBtSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/shutdown-icone-6920-16.png"))); // NOI18N
        jBtSair.setText("Sair");
        jBtSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtSairActionPerformed(evt);
            }
        });

        jBtAuditoriaItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/book_open.png"))); // NOI18N
        jBtAuditoriaItem.setToolTipText("Auditoria");
        jBtAuditoriaItem.setContentAreaFilled(false);
        jBtAuditoriaItem.setEnabled(false);
        jBtAuditoriaItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtAuditoriaItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtReabrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtEncerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtAuditoriaItem, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtEncerrar, jBtEnviar, jBtImprimir, jBtReabrir, jBtSair});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jBtEncerrar)
                .addGap(37, 37, 37)
                .addComponent(jBtEnviar)
                .addGap(1, 1, 1)
                .addComponent(jBtReabrir)
                .addGap(1, 1, 1)
                .addComponent(jBtImprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtAuditoriaItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jBtSair)
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true)));

        jBtNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/page_add.png"))); // NOI18N
        jBtNovo.setText("Novo");
        jBtNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtNovoActionPerformed(evt);
            }
        });

        jBtAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/8437_16x16.png"))); // NOI18N
        jBtAlterar.setText("Alterar");
        jBtAlterar.setEnabled(false);
        jBtAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtAlterarActionPerformed(evt);
            }
        });

        jBtExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/191216104515_16.png"))); // NOI18N
        jBtExcluir.setText("Excluir");
        jBtExcluir.setEnabled(false);
        jBtExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtExcluirActionPerformed(evt);
            }
        });

        jBtSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/1294_16x16.png"))); // NOI18N
        jBtSalvar.setText("Gravar");
        jBtSalvar.setEnabled(false);
        jBtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtSalvarActionPerformed(evt);
            }
        });

        jBtCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Button_Close_Icon_16.png"))); // NOI18N
        jBtCancelar.setText("Cancelar");
        jBtCancelar.setEnabled(false);
        jBtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtCancelarActionPerformed(evt);
            }
        });

        jBtAuditoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/book_open.png"))); // NOI18N
        jBtAuditoria.setToolTipText("Auditoria");
        jBtAuditoria.setContentAreaFilled(false);
        jBtAuditoria.setEnabled(false);
        jBtAuditoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtAuditoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtNovo)
                            .addComponent(jBtExcluir)
                            .addComponent(jBtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtCancelar)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jBtAuditoria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtAlterar, jBtCancelar, jBtExcluir, jBtNovo, jBtSalvar});

        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtNovo)
                .addGap(1, 1, 1)
                .addComponent(jBtAlterar)
                .addGap(1, 1, 1)
                .addComponent(jBtExcluir)
                .addGap(1, 1, 1)
                .addComponent(jBtSalvar)
                .addGap(1, 1, 1)
                .addComponent(jBtCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtAuditoria)
                .addGap(18, 18, 18))
        );

        jTabelaItens.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTabelaItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Data", "Hora Inicial", "Hora Termino", "Texto Chamado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTabelaItens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaItensMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTabelaItens);
        if (jTabelaItens.getColumnModel().getColumnCount() > 0) {
            jTabelaItens.getColumnModel().getColumn(0).setMinWidth(70);
            jTabelaItens.getColumnModel().getColumn(0).setMaxWidth(70);
            jTabelaItens.getColumnModel().getColumn(1).setMinWidth(70);
            jTabelaItens.getColumnModel().getColumn(1).setMaxWidth(70);
            jTabelaItens.getColumnModel().getColumn(2).setMinWidth(80);
            jTabelaItens.getColumnModel().getColumn(2).setMaxWidth(80);
            jTabelaItens.getColumnModel().getColumn(3).setMinWidth(80);
            jTabelaItens.getColumnModel().getColumn(3).setMaxWidth(80);
            jTabelaItens.getColumnModel().getColumn(4).setMinWidth(550);
            jTabelaItens.getColumnModel().getColumn(4).setMaxWidth(550);
        }

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true)));
        jTabbedPane2.setForeground(new java.awt.Color(204, 0, 0));
        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true)));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTextoSuporte.setColumns(20);
        jTextoSuporte.setRows(5);
        jTextoSuporte.setEnabled(false);
        jScrollPane2.setViewportView(jTextoSuporte);

        jHorarioInicio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jHorarioInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jHorarioInicio.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jHorarioInicio.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Horário Inicio");

        jHorarioTermino.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jHorarioTermino.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jHorarioTermino.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jHorarioTermino.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Horário Termino");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Data Ocorrência");

        jDataOcorrencia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jDataOcorrencia.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Código");

        jIdItem.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jIdItem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jIdItem.setEnabled(false);

        jSoftware.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jSoftware.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Software");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Módulo");

        jModulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jModulo.setEnabled(false);

        jBtPesquisaModulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Lupas_1338_05.gif"))); // NOI18N
        jBtPesquisaModulo.setContentAreaFilled(false);
        jBtPesquisaModulo.setEnabled(false);
        jBtPesquisaModulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtPesquisaModuloActionPerformed(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true)));

        jBtSalvarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/1294_16x16.png"))); // NOI18N
        jBtSalvarItem.setToolTipText("Gravar");
        jBtSalvarItem.setEnabled(false);
        jBtSalvarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtSalvarItemActionPerformed(evt);
            }
        });

        jBtCancelarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Button_Close_Icon_16.png"))); // NOI18N
        jBtCancelarItem.setToolTipText("Cancelar");
        jBtCancelarItem.setEnabled(false);
        jBtCancelarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtCancelarItemActionPerformed(evt);
            }
        });

        jBtAlterarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/8437_16x16.png"))); // NOI18N
        jBtAlterarItem.setToolTipText("Alterar");
        jBtAlterarItem.setEnabled(false);
        jBtAlterarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtAlterarItemActionPerformed(evt);
            }
        });

        jBtNovoItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/page_add.png"))); // NOI18N
        jBtNovoItem.setToolTipText("Novo");
        jBtNovoItem.setEnabled(false);
        jBtNovoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtNovoItemActionPerformed(evt);
            }
        });

        jBtExcluirItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/3630_16x16.png"))); // NOI18N
        jBtExcluirItem.setToolTipText("Excluir");
        jBtExcluirItem.setEnabled(false);
        jBtExcluirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtExcluirItemActionPerformed(evt);
            }
        });

        jBtAnexarArquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Pdf16.png"))); // NOI18N
        jBtAnexarArquivo.setToolTipText("Anexar Pdf");
        jBtAnexarArquivo.setEnabled(false);
        jBtAnexarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtAnexarArquivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jBtNovoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jBtAlterarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jBtExcluirItem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jBtSalvarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jBtCancelarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jBtAnexarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel11Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtAlterarItem, jBtCancelarItem, jBtExcluirItem, jBtNovoItem, jBtSalvarItem});

        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jBtNovoItem)
                    .addComponent(jBtAlterarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jBtExcluirItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtSalvarItem)
                    .addComponent(jBtCancelarItem)
                    .addComponent(jBtAnexarArquivo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtAlterarItem, jBtCancelarItem, jBtExcluirItem, jBtNovoItem, jBtSalvarItem});

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 256, Short.MAX_VALUE))
                            .addComponent(jSoftware))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jModulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtPesquisaModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jIdItem, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jDataOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jHorarioInicio)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jHorarioTermino, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel16))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jHorarioTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jHorarioInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDataOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jIdItem, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(9, 9, 9)
                                .addComponent(jSoftware, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(9, 9, 9)
                                .addComponent(jModulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtPesquisaModulo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Texto do Suporte", new javax.swing.ImageIcon(getClass().getResource("/Imagens/HelpDeskFem_16.png")), jPanel6); // NOI18N

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Figura 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jFigura1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFigura1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFigura1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Figura 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jFigura2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFigura2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFigura2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Figura 3", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jFigura3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFigura3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFigura3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Figura 4", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jFigura4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFigura4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFigura4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jBtNovaFigura1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/upload-16.png"))); // NOI18N
        jBtNovaFigura1.setToolTipText("Inserir Figura");
        jBtNovaFigura1.setEnabled(false);
        jBtNovaFigura1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtNovaFigura1ActionPerformed(evt);
            }
        });

        jBtExcluirFigura1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/191216104515_16.png"))); // NOI18N
        jBtExcluirFigura1.setToolTipText("REmover Figura");
        jBtExcluirFigura1.setEnabled(false);
        jBtExcluirFigura1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtExcluirFigura1ActionPerformed(evt);
            }
        });

        jBtVisualizaFigura1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/11985_16x16.png"))); // NOI18N
        jBtVisualizaFigura1.setToolTipText("Visualizar Figura");
        jBtVisualizaFigura1.setEnabled(false);
        jBtVisualizaFigura1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtVisualizaFigura1ActionPerformed(evt);
            }
        });

        jBtNovaFigura2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/upload-16.png"))); // NOI18N
        jBtNovaFigura2.setToolTipText("Inserir Figura");
        jBtNovaFigura2.setEnabled(false);
        jBtNovaFigura2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtNovaFigura2ActionPerformed(evt);
            }
        });

        jBtExcluirFigura2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/191216104515_16.png"))); // NOI18N
        jBtExcluirFigura2.setToolTipText("REmover Figura");
        jBtExcluirFigura2.setEnabled(false);
        jBtExcluirFigura2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtExcluirFigura2ActionPerformed(evt);
            }
        });

        jBtVisualizaFigura2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/11985_16x16.png"))); // NOI18N
        jBtVisualizaFigura2.setToolTipText("Visualizar Figura");
        jBtVisualizaFigura2.setEnabled(false);
        jBtVisualizaFigura2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtVisualizaFigura2ActionPerformed(evt);
            }
        });

        jBtNovaFigura3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/upload-16.png"))); // NOI18N
        jBtNovaFigura3.setToolTipText("Inserir Figura");
        jBtNovaFigura3.setEnabled(false);
        jBtNovaFigura3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtNovaFigura3ActionPerformed(evt);
            }
        });

        jBtExcluirFigura3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/191216104515_16.png"))); // NOI18N
        jBtExcluirFigura3.setToolTipText("REmover Figura");
        jBtExcluirFigura3.setEnabled(false);
        jBtExcluirFigura3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtExcluirFigura3ActionPerformed(evt);
            }
        });

        jBtVisualizaFigura3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/11985_16x16.png"))); // NOI18N
        jBtVisualizaFigura3.setToolTipText("Visualizar Figura");
        jBtVisualizaFigura3.setEnabled(false);
        jBtVisualizaFigura3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtVisualizaFigura3ActionPerformed(evt);
            }
        });

        jBtNovaFigura4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/upload-16.png"))); // NOI18N
        jBtNovaFigura4.setToolTipText("Inserir Figura");
        jBtNovaFigura4.setEnabled(false);
        jBtNovaFigura4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtNovaFigura4ActionPerformed(evt);
            }
        });

        jBtExcluirFigura4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/191216104515_16.png"))); // NOI18N
        jBtExcluirFigura4.setToolTipText("REmover Figura");
        jBtExcluirFigura4.setEnabled(false);
        jBtExcluirFigura4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtExcluirFigura4ActionPerformed(evt);
            }
        });

        jBtVisualizaFigura4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/11985_16x16.png"))); // NOI18N
        jBtVisualizaFigura4.setToolTipText("Visualizar Figura");
        jBtVisualizaFigura4.setEnabled(false);
        jBtVisualizaFigura4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtVisualizaFigura4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jBtNovaFigura1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jBtExcluirFigura1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jBtVisualizaFigura1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jBtNovaFigura2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jBtExcluirFigura2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jBtVisualizaFigura2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jBtNovaFigura3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jBtExcluirFigura3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jBtVisualizaFigura3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtNovaFigura4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jBtExcluirFigura4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jBtVisualizaFigura4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtExcluirFigura1, jBtNovaFigura1, jBtVisualizaFigura1});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtExcluirFigura2, jBtNovaFigura2, jBtVisualizaFigura2});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtExcluirFigura3, jBtNovaFigura3, jBtVisualizaFigura3});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtExcluirFigura4, jBtNovaFigura4, jBtVisualizaFigura4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtVisualizaFigura1)
                    .addComponent(jBtExcluirFigura1)
                    .addComponent(jBtNovaFigura1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtVisualizaFigura2)
                    .addComponent(jBtExcluirFigura2)
                    .addComponent(jBtNovaFigura2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtVisualizaFigura3)
                    .addComponent(jBtExcluirFigura3)
                    .addComponent(jBtNovaFigura3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtVisualizaFigura4)
                    .addComponent(jBtExcluirFigura4)
                    .addComponent(jBtNovaFigura4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtExcluirFigura1, jBtNovaFigura1, jBtVisualizaFigura1});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtExcluirFigura2, jBtNovaFigura2, jBtVisualizaFigura2});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtExcluirFigura3, jBtNovaFigura3, jBtVisualizaFigura3});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtExcluirFigura4, jBtNovaFigura4, jBtVisualizaFigura4});

        jTabbedPane2.addTab("Imagens", new javax.swing.ImageIcon(getClass().getResource("/Imagens/Copy-16x16.png")), jPanel1); // NOI18N

        jScrollPane3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTextoDesenvolvimento.setColumns(20);
        jTextoDesenvolvimento.setRows(5);
        jTextoDesenvolvimento.setEnabled(false);
        jScrollPane3.setViewportView(jTextoDesenvolvimento);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Texto do Desenvolvimento", new javax.swing.ImageIcon(getClass().getResource("/Imagens/HelpDeskMas_16.png")), jPanel7); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel2, jScrollPane4, jTabbedPane2});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manutenção", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        setBounds(250, 0, 792, 558);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtPesqCHCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtPesqCHCodigoActionPerformed
        // TODO add your handling code here:
        nomeAtendente = nameUser;
        buscarNivelUsuario();
        count = 0;
        flag = 1;
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
            if (jIdChamadoPesquisa.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Informe o código para pesquisa.");
            } else {
                pLIMPAR_TABELA_chamados();
                MOSTRAR_DADOS_CODIGO_adm();
            }
        } else if (nivelUsuario == 0 || nivelUsuario == 1) {
            pLIMPAR_TABELA_chamados();
            MOSTRAR_DADOS_NIVEL_01();
        } else if (nivelUsuario == 2) {
            if (jIdChamadoPesquisa.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Informe o código para pesquisa.");
            } else {
                pLIMPAR_TABELA_chamados();
                MOSTRAR_DADOS_NIVEL_2();
            }
        }
    }//GEN-LAST:event_jBtPesqCHCodigoActionPerformed

    private void jBtPesqCHDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtPesqCHDataActionPerformed
        // TODO add your handling code here:
        nomeAtendente = nameUser;
        count = 0;
        flag = 1;
        if (tipoServidor == null || tipoServidor.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "É necessário definir o parâmtero para o sistema operacional utilizado no servidor, (UBUNTU-LINUX ou WINDOWS SERVER).");
        } else if (tipoServidor.equals("Servidor Linux (Ubuntu)/MS-SQL Server")) {
            if (nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
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
                            SimpleDateFormat formatoAmerica = new SimpleDateFormat("yyyy/MM/dd");
                            dataInicial = formatoAmerica.format(jDataPesqInicial.getDate().getTime());
                            dataFinal = formatoAmerica.format(jDataPesFinal.getDate().getTime());
                            pLIMPAR_TABELA_chamados();
                            MOSTRAR_DADOS_DATAS_adm();
                        }
                    }
                }
            } else if (nivelUsuario == 0 || nivelUsuario == 1) {
                if (nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
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
                                SimpleDateFormat formatoAmerica = new SimpleDateFormat("yyyy/MM/dd");
                                dataInicial = formatoAmerica.format(jDataPesqInicial.getDate().getTime());
                                dataFinal = formatoAmerica.format(jDataPesFinal.getDate().getTime());
                                pLIMPAR_TABELA_chamados();
                                MOSTRAR_DADOS_DATAS_NIVEL_01();
                            }
                        }
                    }
                } else if (nivelUsuario == 0 || nivelUsuario == 1) {
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
                                SimpleDateFormat formatoAmerica = new SimpleDateFormat("yyyy/MM/dd");
                                dataInicial = formatoAmerica.format(jDataPesqInicial.getDate().getTime());
                                dataFinal = formatoAmerica.format(jDataPesFinal.getDate().getTime());
                                pLIMPAR_TABELA_chamados();
                                MOSTRAR_DADOS_DATAS_NIVEL_2();
                            }
                        }
                    }
                }
            } else if (tipoServidor.equals("Servidor Windows/MS-SQL Server")) {
                if (nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
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
                                pLIMPAR_TABELA_chamados();
                                MOSTRAR_DADOS_DATAS_adm();
                            }
                        }
                    }
                } else if (nivelUsuario == 0 || nivelUsuario == 1) {
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
                                pLIMPAR_TABELA_chamados();
                                MOSTRAR_DADOS_DATAS_NIVEL_01();
                            }
                        }
                    }
                } else if (nivelUsuario == 2) {
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
                                pLIMPAR_TABELA_chamados();
                                MOSTRAR_DADOS_DATAS_NIVEL_2();
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jBtPesqCHDataActionPerformed

    private void jBtSolicitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSolicitanteActionPerformed
        // TODO add your handling code here:
        nomeAtendente = nameUser;
        count = 0;
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
            if (jPesqSolicitante.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "É necessário informar um nome ou parte do nome para pesquuisa.");
            } else {
                pLIMPAR_TABELA_chamados();
                MOSTRAR_DADOS_SOLICITANTES_adm();
            }
        } else if (nivelUsuario == 0 || nivelUsuario == 1) {
            pLIMPAR_TABELA_chamados();
            MOSTRAR_DADOS_SOLICITANTES_NIVEL_01();
        } else if (nivelUsuario == 2) {
            pLIMPAR_TABELA_chamados();
            MOSTRAR_DADOS_SOLICITANTES_NIVEL_2();
        }
    }//GEN-LAST:event_jBtSolicitanteActionPerformed

    private void jCheckBoxTodosCHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxTodosCHItemStateChanged
        // TODO add your handling code here:
        nomeAtendente = nameUser;
        buscarNivelUsuario();
        count = 0;
        flag = 1;
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
            if (evt.getStateChange() == evt.SELECTED) {
                pLIMPAR_TABELA_chamados();
                MOSTRAR_DADOS_TODOS_adm();
            } else {
                jtotalRegistros.setText("");
                pLIMPAR_TABELA_chamados();
            }
        } else if (nivelUsuario == 0 || nivelUsuario == 1) {
            if (evt.getStateChange() == evt.SELECTED) {
                pLIMPAR_TABELA_chamados();
                MOSTRAR_DADOS_TODOS_NIVEL_01();
            } else {
                jtotalRegistros.setText("");
                pLIMPAR_TABELA_chamados();
            }
        } else if (nivelUsuario == 2) {
            if (evt.getStateChange() == evt.SELECTED) {
                pLIMPAR_TABELA_chamados();
                MOSTRAR_DADOS_TODOS_NIVEL_2();
            } else {
                jtotalRegistros.setText("");
                pLIMPAR_TABELA_chamados();
            }
        }
    }//GEN-LAST:event_jCheckBoxTodosCHItemStateChanged

    private void jBtNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtNovoActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaChamadosSuporte);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaChamadosSuporte) && codIncluir == 1) {
            statusMov = "Incluiu";
            horaMov = jHoraSistema.getText();
            dataModFinal = jDataSistema.getText();
            acao = 1;
            Novo();
            pPREENCHER_COMBO_atendentes();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtNovoActionPerformed

    private void jBtAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtAlterarActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaChamadosSuporte);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaChamadosSuporte) && codAlterar == 1) {
            CONTROL.VERIFICAR_ORIGEM_usuario(objCHSup);
            if (nomeUserRegistro == null ? nameUser == null : nomeUserRegistro.equals(nameUser) || nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
                if (jStatusChamado.getText().equals(statusEncerrado)) {
                    JOptionPane.showMessageDialog(rootPane, "Não é possível modificar esse registro. Registro já encerrado...");
                } else if (jStatusChamado.getText().equals("ENVIADO PARA O DESENVOLVIMENTO")) {
                    JOptionPane.showMessageDialog(rootPane, "Não é possível modificar esse registro. Registro já encontra-se no desenvolvimento...");
                } else {
                    statusMov = "Alterou";
                    horaMov = jHoraSistema.getText();
                    dataModFinal = jDataSistema.getText();
                    acao = 2;
                    Alterar();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Esse registro foi inserido pelo " + nomeUserRegistro + " só esse usuário ou administrador do sistema poderá modificar.");
                conecta.desconecta();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtAlterarActionPerformed

    private void jBtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtExcluirActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaChamadosSuporte);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaChamadosSuporte) && codExcluir == 1) {
            CONTROL.VERIFICAR_ORIGEM_usuario(objCHSup);
            if (nomeUserRegistro == null ? nameUser == null : nomeUserRegistro.equals(nameUser) || nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
                if (jStatusChamado.getText().equals(statusEncerrado)) {
                    JOptionPane.showMessageDialog(rootPane, "Não é possível excluir esse registro. Registro já encerrado...");
                } else {
                    Integer rows = jTabelaItens.getRowCount();
                    if (rows != 0) {
                        JOptionPane.showMessageDialog(rootPane, "Não é possível excluir o registro principal, existem registro na tabela abaixo, exclua primeiros os itens da tabela");
                    } else if (jStatusChamado.getText().equals("ENVIADO PARA O DESENVOLVIMENTO")) {
                        JOptionPane.showMessageDialog(rootPane, "Não é possível modificar esse registro. Registro já encontra-se no desenvolvimento...");
                    } else {
                        statusMov = "Excluiu";
                        horaMov = jHoraSistema.getText();
                        dataModFinal = jDataSistema.getText();
                        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir registro selecionado?", "Confirmação",
                                JOptionPane.YES_NO_OPTION);
                        if (resposta == JOptionPane.YES_OPTION) {
                            objCHSup.setIdCHSup(Integer.valueOf(jIdChamado.getText()));
                            CONTROL.excluirChamadoSup(objCHSup);
                            Excluir();
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Esse registro foi inserido pelo " + nomeUserRegistro + " só esse usuário ou administrador do sistema poderá modificar.");
                conecta.desconecta();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtExcluirActionPerformed

    private void jBtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSalvarActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaChamadosSuporte);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaChamadosSuporte) && codIncluir == 1) {
            if (jDataChamado.getDate() == null) {
                JOptionPane.showMessageDialog(rootPane, "Informe a data do chamado.");
            } else if (jUnidadePrisional.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Informe a unidade prisional.");
            } else if (jSolicitante.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Informe o solicitante.");
            } else if (jAssunto.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Informe o assunto do chamdo.");
            } else if (jComboBoxAtendente.getSelectedItem().equals("Selecione...")) {
                JOptionPane.showMessageDialog(rootPane, "Informe o nome do Atendente.");
            } else {
                objCHSup.setStatusCha(jStatusChamado.getText());
                objCHSup.setDataCha(jDataChamado.getDate());
                objCHSup.setNomeUsuario(nameUser);
                objCHSup.setIdSolicitante(idSolicitante);
                objCHSup.setNomeSolicitante(jSolicitante.getText());
                objCHSup.setDescricaoUnidade(jUnidadePrisional.getText());
                objCHSup.setAssunto(jAssunto.getText());
                objCHSup.setNomeAtendente((String) jComboBoxAtendente.getSelectedItem());
                objCHSup.setTipoChamadoSuporte((String) jComboBoxTipoChamadoSuporte.getSelectedItem());
                if (acao == 1) {
                    objCHSup.setUsuarioInsert(nameUser);
                    objCHSup.setDataInsert(dataModFinal);
                    objCHSup.setHorarioInsert(horaMov);
                    CONTROL.incluirChamadoSup(objCHSup);
                    jHorarioTermino.setText(objCHSup.getHorarioTermino());
                    CONTROL.pBUSCAR_Codigo(objCHSup);
                    //
                    objLog();
                    controlLog.incluirLogSistema(objLogSys); // Grava o log da operação
                    Salvar();
                    JOptionPane.showMessageDialog(rootPane, "Registro gravado com sucesso.");
                }
                if (acao == 2) {
                    objCHSup.setUsuarioUp(nameUser);
                    objCHSup.setDataUp(dataModFinal);
                    objCHSup.setHorarioUp(horaMov);
                    objCHSup.setIdCHSup(Integer.valueOf(jIdChamado.getText()));
                    CONTROL.alterarChamadoSup(objCHSup);
                    //
                    objLog();
                    controlLog.incluirLogSistema(objLogSys); // Grava o log da operação
                    Salvar();
                    JOptionPane.showMessageDialog(rootPane, "Registro gravado com sucesso.");
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

    private void jBtPesquisaModuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtPesquisaModuloActionPerformed
        // TODO add your handling code here:
        mostrarPesquisaSoft();
    }//GEN-LAST:event_jBtPesquisaModuloActionPerformed

    private void jBtPesquisaSoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtPesquisaSoliActionPerformed
        // TODO add your handling code here:
        mostrarPesquisa();
    }//GEN-LAST:event_jBtPesquisaSoliActionPerformed

    private void jBtEncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtEncerrarActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(botaoEncerrarSup);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(botaoEncerrarSup) && codAbrir == 1) {
            Integer row = jTabelaItens.getRowCount();
            CONTROL.VERIFICAR_ORIGEM_usuario(objCHSup);
            if (nomeUserRegistro == null ? nameUser == null : nomeUserRegistro.equals(nameUser) || nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
                if (jStatusChamado.getText().equals(statusEncerrado)) {
                    JOptionPane.showMessageDialog(rootPane, "Não é possível modificar esse registro. Registro já encerrado...");
                } else if (row == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Não é possível encerrar o chamado, não existem itens relacionados ao chamado.\nSó é possível incluir ou excluir o registro.");
                } else {
                    bloquearBotoes();
                    bloquearCampos();
                    int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente encerrar registro selecionado?", "Confirmação",
                            JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        objCHSup.setStatusCha(statusEncerrado);
                        objCHSup.setIdCHSup(Integer.valueOf(jIdChamado.getText()));
                        CONTROL.encerrarChamadoSup(objCHSup);
                        jStatusChamado.setText(statusEncerrado);
                        JOptionPane.showMessageDialog(rootPane, "Registro encerrado com sucesso.");
                        //
                        jBtNovo.setEnabled(true);
                        jBtAuditoria.setEnabled(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Esse registro foi inserido pelo " + nomeUserRegistro + " só esse usuário ou administrador do sistema poderá modificar.");
                conecta.desconecta();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtEncerrarActionPerformed

    private void jBtImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtImprimirActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(botaoImprimirSup);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(botaoImprimirSup) && codAbrir == 1) {
            if (jIdChamado.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Não existe registro a ser impresso, selecione um registro antes de imprimir.");
            } else {
                relatorioChamadosST();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtImprimirActionPerformed

    private void jBtEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtEnviarActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(botaoEnviarSup);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(botaoEnviarSup) && codAbrir == 1) {
            CONTROL.VERIFICAR_ORIGEM_usuario(objCHSup);
            if (nomeUserRegistro == null ? nameUser == null : nomeUserRegistro.equals(nameUser) || nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
                if (jStatusChamado.getText().equals(statusEncerrado)) {
                    JOptionPane.showMessageDialog(rootPane, "Não é possível modificar esse registro.Registro já encerrado...");
                } else {
                    bloquearBotoes();
                    bloquearCampos();
                    int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente enviar para o desenvolvimento o registro selecionado?", "Confirmação",
                            JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        mostrarTelaEnvioChamdosDes();
                    } else {
                        jBtNovo.setEnabled(true);
                        jBtAlterar.setEnabled(true);
                        jBtExcluir.setEnabled(true);
                        jBtAuditoria.setEnabled(true);
                        //
                        jBtEncerrar.setEnabled(true);
                        jBtImprimir.setEnabled(true);
                        jBtEnviar.setEnabled(true);
                        //
                        jBtNovoItem.setEnabled(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Esse registro foi inserido pelo " + nomeUserRegistro + " só esse usuário ou administrador do sistema poderá modificar.");
                conecta.desconecta();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtEnviarActionPerformed

    private void jBtReabrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtReabrirActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(botaoReabrirSup);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(botaoReabrirSup) && codAbrir == 1) {
            CONTROL.VERIFICAR_ORIGEM_usuario(objCHSup);
            if (nomeUserRegistro == null ? nameUser == null : nomeUserRegistro.equals(nameUser) || nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
                if (!jStatusChamado.getText().equals(statusEncerrado)) {
                    JOptionPane.showMessageDialog(rootPane, "Registro não se encontra encerrado.");
                } else if (jStatusChamado.getText().equals(statusDesenvol)) {
                    JOptionPane.showMessageDialog(rootPane, "Registro não pode ser reaberto, o mesmo encontra-se no desenvolvimento.");
                } else {
                    bloquearBotoes();
                    bloquearCampos();
                    int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente reabrir o registro selecionado?", "Confirmação",
                            JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        objCHSup.setStatusCha(statusReabrir);
                        objCHSup.setIdCHSup(Integer.valueOf(jIdChamado.getText()));
                        CONTROL.encerrarChamadoSup(objCHSup);
                        jStatusChamado.setText(statusReabrir);
                        JOptionPane.showMessageDialog(rootPane, "Registro encerrado com sucesso.");
                        //
                        jBtNovo.setEnabled(true);
                        jBtAlterar.setEnabled(true);
                        jBtExcluir.setEnabled(true);
                        //
                        jBtEncerrar.setEnabled(true);
                        jBtImprimir.setEnabled(true);
                        jBtEnviar.setEnabled(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Esse registro foi inserido pelo " + nomeUserRegistro + " só esse usuário ou administrador do sistema poderá modificar.");
                conecta.desconecta();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtReabrirActionPerformed

    private void jBtAuditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtAuditoriaActionPerformed
        // TODO add your handling code here:
        TelaAuditoriaCHSuporte objCHSup = new TelaAuditoriaCHSuporte();
        jPainelPrincipal.add(objCHSup);
        objCHSup.show();
    }//GEN-LAST:event_jBtAuditoriaActionPerformed

    private void jTabelaChamdosSupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaChamdosSupMouseClicked
        // TODO add your handling code here:
        flag = 1;
        if (flag == 1) {
            idSoli = "" + jTabelaChamdosSup.getValueAt(jTabelaChamdosSup.getSelectedRow(), 0);
            jIdChamadoPesquisa.setText(idSoli);
            //            
            limparTodosCampos();
            bloquearCampos();
            bloquearBotoes();
            jBtNovo.setEnabled(true);
            jBtAlterar.setEnabled(true);
            jBtExcluir.setEnabled(true);
            jBtAuditoria.setEnabled(true);
            //
            jBtEncerrar.setEnabled(true);
            jBtImprimir.setEnabled(true);
            jBtEnviar.setEnabled(true);
            jBtReabrir.setEnabled(true);
            //
            jBtNovoItem.setEnabled(true);
            jComboBoxAtendente.removeAllItems();
            try {
                for (ChamadoSuporte b : CONTROL.MOSTRAR_DADOS_PESQUISADOS_read()) {
                    jIdChamado.setText(String.valueOf(b.getIdCHSup()));
                    jStatusChamado.setText(b.getStatusCha());
                    jDataChamado.setDate(b.getDataCha());
                    jComboBoxAtendente.addItem(b.getNomeAtendente());
                    idSolicitante = b.getIdSolicitante();
                    jSolicitante.setText(b.getNomeSolicitante());
                    idUnidade = b.getIdUnidEmp();
                    jUnidadePrisional.setText(b.getDescricaoUnidade());
                    jAssunto.setText(b.getAssunto());
                    jComboBoxTipoChamadoSuporte.setSelectedItem(b.getTipoChamadoSuporte());
                }
            } catch (Exception ex) {
                Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
            }
            pLIMPAR_TABELA_itens();
            MOSTRAR_ITENS_chamado();
        }
    }//GEN-LAST:event_jTabelaChamdosSupMouseClicked

    private void jBtNovoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtNovoItemActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaItensChamadoSuporte);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaItensChamadoSuporte) && codIncluir == 1) {
            CONTROL.VERIFICAR_ORIGEM_usuario(objCHSup);
            if (nomeUserRegistro == null ? nameUser == null : nomeUserRegistro.equals(nameUser) || nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
                Integer rows = jTabelaItens.getRowCount();
                statusMov = "Incluiu";
                horaMov = jHoraSistema.getText();
                dataModFinal = jDataSistema.getText();
                if (jStatusChamado.getText().equals(statusEncerrado)) {
                    JOptionPane.showMessageDialog(rootPane, "Não é possível alterar o registro, o mesmo já foi encerrado.");
                } else {
                    acao = 3;
                    if (nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
                        NovoItem();
                        alterarAdm();
                    } else {
                        NovoItem();
                    }
                    if (rows != 0) {
                        try {
                            for (ChamadoSuporte p : CONTROL.VERIFICAR_SOF_mod_read()) {
                                idSoftware = p.getIdSoftware();
                                jSoftware.setText(p.getDescricaoSoftware());
                                idModulo = p.getIdModulo();
                                jModulo.setText(p.getDescricaoModulo());
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Esse registro foi inserido pelo " + nomeUserRegistro + " só esse usuário ou administrador do sistema poderá modificar.");
                conecta.desconecta();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtNovoItemActionPerformed

    private void jBtAlterarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtAlterarItemActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaItensChamadoSuporte);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaItensChamadoSuporte) && codAlterar == 1) {
            CONTROL.VERIFICAR_ORIGEM_usuario(objCHSup);
            if (nomeUserRegistro == null ? nameUser == null : nomeUserRegistro.equals(nameUser) || nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
                if (jStatusChamado.getText().equals(statusEncerrado)) {
                    JOptionPane.showMessageDialog(rootPane, "Não é possível alterar o registro, o mesmo já foi encerrado.");
                } else if (jStatusChamado.getText().equals(statusDesenvol)) {
                    JOptionPane.showMessageDialog(rootPane, "Não é possível alterar o registro, o mesmo já foi enviado para o desenvolvimento.");
                } else {
                    Integer rows = jTabelaItens.getRowCount();
                    if (nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
                        statusMov = "Alterou";
                        horaMov = jHoraSistema.getText();
                        dataModFinal = jDataSistema.getText();
                        acao = 4;
                        alterarAdm();
                    } else {
                        statusMov = "Alterou";
                        horaMov = jHoraSistema.getText();
                        dataModFinal = jDataSistema.getText();
                        acao = 4;
                        AlterarItem();
                    }
                    if (rows != 0) {
                        try {
                            for (ChamadoSuporte p : CONTROL.VERIFICAR_SOF_mod_read()) {
                                idSoftware = p.getIdSoftware();
                                jSoftware.setText(p.getDescricaoSoftware());
                                idModulo = p.getIdModulo();
                                jModulo.setText(p.getDescricaoModulo());
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Esse registro foi inserido pelo " + nomeUserRegistro + " só esse usuário ou administrador do sistema poderá modificar.");
                conecta.desconecta();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtAlterarItemActionPerformed

    private void jBtExcluirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtExcluirItemActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaItensChamadoSuporte);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaItensChamadoSuporte) && codExcluir == 1) {
            CONTROL.VERIFICAR_ORIGEM_usuario(objCHSup);
            if (nomeUserRegistro == null ? nameUser == null : nomeUserRegistro.equals(nameUser) || nameUser.equals("ADMINISTRADOR DO SISTEMA")) {
                statusMov = "Excluiu";
                horaMov = jHoraSistema.getText();
                dataModFinal = jDataSistema.getText();
                if (jStatusChamado.getText().equals(statusEncerrado)) {
                    JOptionPane.showMessageDialog(rootPane, "Não é possível excluir o registro, o mesmo já foi encerrado.");
                } else if (jStatusChamado.getText().equals(statusDesenvol)) {
                    JOptionPane.showMessageDialog(rootPane, "Não é possível excluir o registro, o mesmo já foi enviado para o desenvolvimento.");
                } else {
                    int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir registro selecionado?", "Confirmação",
                            JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        objCHSup.setIdItemCh(Integer.valueOf(jIdItem.getText()));
                        CONTROL.excluirItensChamadoSup(objCHSup);
                        ExcluirItem();
                        pLIMPAR_TABELA_itens();
                        MOSTRAR_ITENS_chamado();
                        JOptionPane.showMessageDialog(rootPane, "Registro excluido com sucesso.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Esse registro foi inserido pelo " + nomeUserRegistro + " só esse usuário ou administrador do sistema poderá modificar.");
                conecta.desconecta();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtExcluirItemActionPerformed

    private void jBtSalvarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSalvarItemActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaItensChamadoSuporte);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaItensChamadoSuporte) && codGravar == 1) {
            if (jDataOcorrencia.getDate() == null) {
                JOptionPane.showMessageDialog(rootPane, "Informe a data do chamado.");
            } else if (jHorarioInicio.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Informe o horário de inicio do chamado.");
            } else if (jSoftware.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Informe do software(sistema).");
            } else if (jModulo.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Informe o nome do módulo.");
            } else {
                objCHSup.setIdCHSup(Integer.valueOf(jIdChamado.getText()));
                objCHSup.setDataItemCh(jDataOcorrencia.getDate());
                objCHSup.setDescricaoSoftware(jSoftware.getText());
                objCHSup.setDescricaoModulo(jModulo.getText());
                objCHSup.setTextoSuporte(jTextoSuporte.getText());
                objCHSup.setTipoChamado(tipoSuporte);
                objCHSup.setHorarioInicio(jHorarioInicio.getText());
                objCHSup.setHorarioTermino(horaMov);
                // PREPARAR FOTO PARA GRAVAR NO BANCO DE DADOS - FOTO1   
                if (jFigura1.getIcon() != null) {
                    objCHSup.setImagemDocumento(persona_imagem);
                }
                // PREPARAR FOTO PARA GRAVAR NO BANCO DE DADOS - FOTO2   
                if (jFigura2.getIcon() != null) {
                    objCHSup.setImagemDocumento1(persona_imagem1);
                }
                // PREPARAR FOTO PARA GRAVAR NO BANCO DE DADOS - FOTO3   
                if (jFigura3.getIcon() != null) {
                    objCHSup.setImagemDocumento2(persona_imagem2);
                }
                // PREPARAR FOTO PARA GRAVAR NO BANCO DE DADOS - FOTO3   
                if (jFigura4.getIcon() != null) {
                    objCHSup.setImagemDocumento3(persona_imagem3);
                }
                if (acao == 3) {
                    objCHSup.setUsuarioInsert(nameUser);
                    objCHSup.setDataInsert(dataModFinal);
                    objCHSup.setHorarioInsert(horaMov);
                    if (jComboBoxAtendente.getSelectedItem().equals("ADMINISTRADOR DO SISTEMA")) {
                        jHorarioTermino.setEnabled(true);
                        objCHSup.setHorarioTermino(jHorarioTermino.getText());
                    } else {
                        objCHSup.setHorarioTermino(jHoraSistema.getText());
                    }
                    //PESQUISAR PRIMEIRO O MÓDULO PARA NÃO FAZER COM MÓDULO DIFERENTE
                    try {
                        for (ChamadoSuporte pp : CONTROL.VERIFICAR_SOFTWARE_modulo_read()) {
                            codigoSoftware = pp.getIdSoftware();
                            codigoModulo = pp.getIdModulo();
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (idSoftware == codigoSoftware && idModulo != codigoModulo) {
                        JOptionPane.showMessageDialog(rootPane, "O módulo não pode ser diferente do já cadastrado.");
                    } else {
                        CONTROL.incluirItensSup(objCHSup);
                        jHorarioTermino.setText(objCHSup.getHorarioTermino());
                        CONTROL.pBUSCAR_CODIGO_Item(objCHSup);
                        //
                        objLog1();
                        controlLog.incluirLogSistema(objLogSys); // Grava o log da operação
                        SalvarItem();
                        pLIMPAR_TABELA_itens();
                        MOSTRAR_ITENS_chamado();
                        JOptionPane.showMessageDialog(rootPane, "Registro gravado com sucesso.");
                    }
                }
                if (acao == 4) {
                    objCHSup.setUsuarioUp(nameUser);
                    objCHSup.setDataUp(dataModFinal);
                    objCHSup.setHorarioUp(horaMov);
                    if (jComboBoxAtendente.getSelectedItem().equals("ADMINISTRADOR DO SISTEMA")) {
                        jHorarioTermino.setEnabled(true);
                        objCHSup.setHorarioTermino(jHorarioTermino.getText());
                    } else {
                        objCHSup.setHorarioTermino(jHoraSistema.getText());
                    }
                    //PESQUISAR PRIMEIRO O MÓDULO PARA NÃO FAZER COM MÓDULO DIFERENTE
                    try {
                        for (ChamadoSuporte pp : CONTROL.VERIFICAR_SOFTWARE_modulo_read()) {
                            codigoSoftware = pp.getIdSoftware();
                            codigoModulo = pp.getIdModulo();
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (idSoftware == codigoSoftware && idModulo != codigoModulo) {
                        JOptionPane.showMessageDialog(rootPane, "O módulo não pode ser diferente do já cadastrado.");
                    } else {
                        objCHSup.setIdItemCh(Integer.valueOf(jIdItem.getText()));
                        CONTROL.alterarItensSup(objCHSup);
                        jHorarioTermino.setText(objCHSup.getHorarioTermino());
                        //
                        objLog1();
                        controlLog.incluirLogSistema(objLogSys); // Grava o log da operação
                        SalvarItem();
                        pLIMPAR_TABELA_itens();
                        MOSTRAR_ITENS_chamado();
                        JOptionPane.showMessageDialog(rootPane, "Registro gravado com sucesso.");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtSalvarItemActionPerformed

    private void jBtCancelarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtCancelarItemActionPerformed
        // TODO add your handling code here:
        CancelarItem();
    }//GEN-LAST:event_jBtCancelarItemActionPerformed

    private void jTabelaItensMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaItensMouseClicked
        // TODO add your handling code here:
        flag = 1;
        if (flag == 1) {
            idItem = "" + jTabelaItens.getValueAt(jTabelaItens.getSelectedRow(), 0);
            jIdItem.setText(idItem);
            //                       
            bloquearCampos();
            bloquearBotoes();
            jBtNovoItem.setEnabled(true);
            jBtAlterarItem.setEnabled(true);
            jBtExcluirItem.setEnabled(true);
            jBtCancelarItem.setEnabled(true);
            jBtAuditoriaItem.setEnabled(true);
            jBtAnexarArquivo.setEnabled(true);
            //
            jBtVisualizaFigura1.setEnabled(true);
            jBtVisualizaFigura2.setEnabled(true);
            jBtVisualizaFigura3.setEnabled(true);
            jBtVisualizaFigura4.setEnabled(true);
            //
            jBtEncerrar.setEnabled(true);
            jBtImprimir.setEnabled(true);
            jBtEnviar.setEnabled(true);
            jBtReabrir.setEnabled(true);
            try {
                for (ChamadoSuporte i : CONTROL.MOSTRAR_ITENS_CHAMADO_read()) {
                    jIdItem.setText(String.valueOf(i.getIdItemCh()));
                    jDataOcorrencia.setDate(i.getDataItemCh());
                    jHorarioInicio.setText(i.getHorarioInicio());
                    jHorarioTermino.setText(i.getHorarioTermino());
                    jSoftware.setText(i.getDescricaoSoftware());
                    jModulo.setText(i.getDescricaoModulo());
                    jTextoSuporte.setText(i.getTextoSuporte());
                    jTextoDesenvolvimento.setText(i.getTextoDesenvol());
                    // BUSCAR A FOTO DA FIGURA1 NO BANCO DE DADOS
                    byte[] imgBytes0 = ((byte[]) i.getImagemDocumento());
                    if (imgBytes0 != null) {
                        ImageIcon pic = null;
                        pic = new ImageIcon(imgBytes0);
                        Image scaled = pic.getImage().getScaledInstance(jFigura1.getWidth(), jFigura1.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(scaled);
                        jFigura1.setIcon(icon);
                        this.repaint();
                    }
                    // BUSCAR A FOTO DA FIGURA2 NO BANCO DE DADOS
                    byte[] imgBytes1 = ((byte[]) i.getImagemDocumento1());
                    if (imgBytes1 != null) {
                        ImageIcon pic1 = null;
                        pic1 = new ImageIcon(imgBytes1);
                        Image scaled1 = pic1.getImage().getScaledInstance(jFigura2.getWidth(), jFigura2.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon icon1 = new ImageIcon(scaled1);
                        jFigura2.setIcon(icon1);
                        this.repaint();
                    }
                    // BUSCAR A FOTO DA FIGURA3 NO BANCO DE DADOS
                    byte[] imgBytes2 = ((byte[]) i.getImagemDocumento2());
                    if (imgBytes2 != null) {
                        ImageIcon pic2 = null;
                        pic2 = new ImageIcon(imgBytes2);
                        Image scaled2 = pic2.getImage().getScaledInstance(jFigura3.getWidth(), jFigura3.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon icon2 = new ImageIcon(scaled2);
                        jFigura3.setIcon(icon2);
                        this.repaint();
                    }
                    // BUSCAR A FOTO DA FIGURA4 NO BANCO DE DADOS
                    byte[] imgBytes3 = ((byte[]) i.getImagemDocumento3());
                    if (imgBytes3 != null) {
                        ImageIcon pic3 = null;
                        pic3 = new ImageIcon(imgBytes3);
                        Image scaled3 = pic3.getImage().getScaledInstance(jFigura4.getWidth(), jFigura4.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon icon3 = new ImageIcon(scaled3);
                        jFigura4.setIcon(icon3);
                        this.repaint();
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTabelaItensMouseClicked

    private void jBtAuditoriaItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtAuditoriaItemActionPerformed
        // TODO add your handling code here:
        TelaAuditoriaCHSuporteItens objAudItens = new TelaAuditoriaCHSuporteItens();
        jPainelPrincipal.add(objAudItens);
        objAudItens.show();
    }//GEN-LAST:event_jBtAuditoriaItemActionPerformed

    private void jBtAnexarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtAnexarArquivoActionPerformed
        // TODO add your handling code here:
        if (jIdChamado.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Selecione primeiro o chamado abaixo para anexar o arquivo PDF.");
        } else {
            mostrarPdf();
        }
    }//GEN-LAST:event_jBtAnexarArquivoActionPerformed

    private void jBtNovaFigura1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtNovaFigura1ActionPerformed
        // TODO add your handling code here:      
        JFileChooser chooser = new JFileChooser();
        int acao = chooser.showOpenDialog(this);
        if (acao == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            caminhoFigura1 = f.getAbsolutePath();
            ImageIcon imagemicon = new ImageIcon(new ImageIcon(caminhoFigura1).getImage().getScaledInstance(jFigura1.getWidth(), jFigura1.getHeight(), Image.SCALE_SMOOTH));
            jFigura1.setIcon(imagemicon);
            try {
                File image = new File(caminhoFigura1);
                FileInputStream fis = new FileInputStream(image);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
                persona_imagem = bos.toByteArray();
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleção da figura cancelada.");
        }
    }//GEN-LAST:event_jBtNovaFigura1ActionPerformed

    private void jBtExcluirFigura1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtExcluirFigura1ActionPerformed
        // TODO add your handling code here:
        jFigura1.setIcon(null);
    }//GEN-LAST:event_jBtExcluirFigura1ActionPerformed

    private void jBtVisualizaFigura1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtVisualizaFigura1ActionPerformed
        // TODO add your handling code here:
        if (jIdChamado.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Selecione primeiro o registro de chamado para visualizar a imagem.");
        } else {
            mostraTelaFotoDoc1();
        }
    }//GEN-LAST:event_jBtVisualizaFigura1ActionPerformed

    private void jBtNovaFigura2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtNovaFigura2ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        int acao = chooser.showOpenDialog(this);
        if (acao == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            caminhoFigura2 = f.getAbsolutePath();
            ImageIcon imagemicon = new ImageIcon(new ImageIcon(caminhoFigura2).getImage().getScaledInstance(jFigura2.getWidth(), jFigura2.getHeight(), Image.SCALE_SMOOTH));
            jFigura2.setIcon(imagemicon);
            try {
                File image = new File(caminhoFigura2);
                FileInputStream fis = new FileInputStream(image);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
                persona_imagem1 = bos.toByteArray();
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleção da figura cancelada.");
        }
    }//GEN-LAST:event_jBtNovaFigura2ActionPerformed

    private void jBtExcluirFigura2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtExcluirFigura2ActionPerformed
        // TODO add your handling code here:
        jFigura2.setIcon(null);
    }//GEN-LAST:event_jBtExcluirFigura2ActionPerformed

    private void jBtVisualizaFigura2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtVisualizaFigura2ActionPerformed
        // TODO add your handling code here:
        if (jIdChamado.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Selecione primeiro o registro de chamado para visualizar a imagem.");
        } else {
            mostraTelaFotoDoc2();
        }
    }//GEN-LAST:event_jBtVisualizaFigura2ActionPerformed

    private void jBtNovaFigura3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtNovaFigura3ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        int acao = chooser.showOpenDialog(this);
        if (acao == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            caminhoFigura3 = f.getAbsolutePath();
            ImageIcon imagemicon = new ImageIcon(new ImageIcon(caminhoFigura3).getImage().getScaledInstance(jFigura3.getWidth(), jFigura3.getHeight(), Image.SCALE_SMOOTH));
            jFigura3.setIcon(imagemicon);
            try {
                File image = new File(caminhoFigura3);
                FileInputStream fis = new FileInputStream(image);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
                persona_imagem2 = bos.toByteArray();
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleção da figura cancelada.");
        }
    }//GEN-LAST:event_jBtNovaFigura3ActionPerformed

    private void jBtExcluirFigura3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtExcluirFigura3ActionPerformed
        // TODO add your handling code here:
        jFigura3.setIcon(null);
    }//GEN-LAST:event_jBtExcluirFigura3ActionPerformed

    private void jBtVisualizaFigura3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtVisualizaFigura3ActionPerformed
        // TODO add your handling code here:
        if (jIdChamado.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Selecione primeiro o registro de chamado para visualizar a imagem.");
        } else {
            mostraTelaFotoDoc3();
        }
    }//GEN-LAST:event_jBtVisualizaFigura3ActionPerformed

    private void jBtNovaFigura4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtNovaFigura4ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        int acao = chooser.showOpenDialog(this);
        if (acao == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            caminhoFigura4 = f.getAbsolutePath();
            ImageIcon imagemicon = new ImageIcon(new ImageIcon(caminhoFigura4).getImage().getScaledInstance(jFigura4.getWidth(), jFigura4.getHeight(), Image.SCALE_SMOOTH));
            jFigura4.setIcon(imagemicon);
            try {
                File image = new File(caminhoFigura4);
                FileInputStream fis = new FileInputStream(image);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
                persona_imagem3 = bos.toByteArray();
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleção da figura cancelada.");
        }
    }//GEN-LAST:event_jBtNovaFigura4ActionPerformed

    private void jBtExcluirFigura4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtExcluirFigura4ActionPerformed
        // TODO add your handling code here:
        jFigura4.setIcon(null);
    }//GEN-LAST:event_jBtExcluirFigura4ActionPerformed

    private void jBtVisualizaFigura4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtVisualizaFigura4ActionPerformed
        // TODO add your handling code here:
        if (jIdChamado.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Selecione primeiro o registro de chamado para visualizar a imagem.");
        } else {
            mostraTelaFotoDoc4();
        }
    }//GEN-LAST:event_jBtVisualizaFigura4ActionPerformed

    private void jComboBoxAtendenteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxAtendenteMouseEntered
        // TODO add your handling code here:
        if (acao == 2) {
            pPREENCHER_COMBO_atendentes();
        }
    }//GEN-LAST:event_jComboBoxAtendenteMouseEntered

    private void jBtAssuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtAssuntoActionPerformed
        // TODO add your handling code here:
        flag = 1;
        if (jPesquisarAssunto.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Informe o assunto para pesquisa.");
        } else {
            MOSTRAR_TABELA_assuntos();
        }
    }//GEN-LAST:event_jBtAssuntoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jAssunto;
    public static javax.swing.JButton jBtAlterar;
    public static javax.swing.JButton jBtAlterarItem;
    private javax.swing.JButton jBtAnexarArquivo;
    private javax.swing.JButton jBtAssunto;
    public static javax.swing.JButton jBtAuditoria;
    public static javax.swing.JButton jBtAuditoriaItem;
    public static javax.swing.JButton jBtCancelar;
    public static javax.swing.JButton jBtCancelarItem;
    public static javax.swing.JButton jBtEncerrar;
    public static javax.swing.JButton jBtEnviar;
    public static javax.swing.JButton jBtExcluir;
    private javax.swing.JButton jBtExcluirFigura1;
    private javax.swing.JButton jBtExcluirFigura2;
    private javax.swing.JButton jBtExcluirFigura3;
    private javax.swing.JButton jBtExcluirFigura4;
    public static javax.swing.JButton jBtExcluirItem;
    public static javax.swing.JButton jBtImprimir;
    private javax.swing.JButton jBtNovaFigura1;
    private javax.swing.JButton jBtNovaFigura2;
    private javax.swing.JButton jBtNovaFigura3;
    private javax.swing.JButton jBtNovaFigura4;
    public static javax.swing.JButton jBtNovo;
    public static javax.swing.JButton jBtNovoItem;
    private javax.swing.JButton jBtPesqCHCodigo;
    private javax.swing.JButton jBtPesqCHData;
    private javax.swing.JButton jBtPesquisaModulo;
    private javax.swing.JButton jBtPesquisaSoli;
    public static javax.swing.JButton jBtReabrir;
    private javax.swing.JButton jBtSair;
    public static javax.swing.JButton jBtSalvar;
    public static javax.swing.JButton jBtSalvarItem;
    private javax.swing.JButton jBtSolicitante;
    private javax.swing.JButton jBtVisualizaFigura1;
    private javax.swing.JButton jBtVisualizaFigura2;
    private javax.swing.JButton jBtVisualizaFigura3;
    private javax.swing.JButton jBtVisualizaFigura4;
    private javax.swing.JCheckBox jCheckBoxTodosCH;
    public static javax.swing.JComboBox<String> jComboBoxAtendente;
    private javax.swing.JComboBox<String> jComboBoxTipoChamadoSuporte;
    private com.toedter.calendar.JDateChooser jDataChamado;
    private com.toedter.calendar.JDateChooser jDataOcorrencia;
    public static com.toedter.calendar.JDateChooser jDataPesFinal;
    public static com.toedter.calendar.JDateChooser jDataPesqInicial;
    public static javax.swing.JLabel jFigura1;
    public static javax.swing.JLabel jFigura2;
    public static javax.swing.JLabel jFigura3;
    public static javax.swing.JLabel jFigura4;
    private javax.swing.JFormattedTextField jHorarioInicio;
    private javax.swing.JFormattedTextField jHorarioTermino;
    public static javax.swing.JTextField jIdChamado;
    public static javax.swing.JTextField jIdChamadoPesquisa;
    public static javax.swing.JTextField jIdItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JTextField jModulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public static javax.swing.JTextField jPesqSolicitante;
    public static javax.swing.JTextField jPesquisarAssunto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTextField jSoftware;
    public static javax.swing.JTextField jSolicitante;
    public static javax.swing.JTextField jStatusChamado;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTabelaChamdosSup;
    private javax.swing.JTable jTabelaItens;
    private javax.swing.JTextArea jTextoDesenvolvimento;
    private javax.swing.JTextArea jTextoSuporte;
    public static javax.swing.JTextField jUnidadePrisional;
    private javax.swing.JLabel jtotalRegistros;
    // End of variables declaration//GEN-END:variables

    public void formatarCampos() {
        jTextoSuporte.setLineWrap(true);
        jTextoSuporte.setWrapStyleWord(true);
        //
        jTextoDesenvolvimento.setLineWrap(true);
        jTextoDesenvolvimento.setWrapStyleWord(true);
    }

    public void corCampos() {
        jIdChamado.setBackground(Color.white);
        jStatusChamado.setBackground(Color.white);
        jDataChamado.setBackground(Color.white);
        jComboBoxTipoChamadoSuporte.setBackground(Color.white);
        jSolicitante.setBackground(Color.white);
        jUnidadePrisional.setBackground(Color.white);
        jAssunto.setBackground(Color.white);
        //
        jIdItem.setBackground(Color.white);
        jHorarioInicio.setBackground(Color.white);
        jHorarioTermino.setBackground(Color.white);
        jComboBoxAtendente.setBackground(Color.white);
        jSoftware.setBackground(Color.white);
        jModulo.setBackground(Color.white);
        jTextoSuporte.setBackground(Color.white);
        jTextoDesenvolvimento.setBackground(Color.white);
    }

    public void bloquearCampos() {
        jIdChamado.setEnabled(!true);
        jStatusChamado.setEnabled(!true);
        jDataChamado.setEnabled(!true);
        jComboBoxAtendente.setEnabled(!true);
        jComboBoxTipoChamadoSuporte.setEnabled(!true);
        jAssunto.setEnabled(!true);
        jIdItem.setEnabled(!true);
        jDataOcorrencia.setEnabled(!true);
        jHorarioInicio.setEnabled(!true);
        jHorarioTermino.setEnabled(!true);
        jComboBoxAtendente.setEnabled(!true);
        jAssunto.setEnabled(!true);
        jUnidadePrisional.setEnabled(!true);
        jSoftware.setEnabled(!true);
        jModulo.setEnabled(!true);
        jSolicitante.setEnabled(!true);
        jTextoSuporte.setEnabled(!true);
        jTextoDesenvolvimento.setEnabled(!true);
    }

    public void bloquearBotoes() {
        jBtNovo.setEnabled(!true);
        jBtAlterar.setEnabled(!true);
        jBtExcluir.setEnabled(!true);
        jBtSalvar.setEnabled(!true);
        jBtCancelar.setEnabled(!true);
        jBtAuditoria.setEnabled(!true);
        //
        jBtEncerrar.setEnabled(!true);
        jBtImprimir.setEnabled(!true);
        jBtEnviar.setEnabled(!true);
        jBtReabrir.setEnabled(!true);
        //        
        jBtPesquisaModulo.setEnabled(!true);
        jBtPesquisaSoli.setEnabled(!true);
        //
        jBtNovoItem.setEnabled(!true);
        jBtAlterarItem.setEnabled(!true);
        jBtExcluirItem.setEnabled(!true);
        jBtSalvarItem.setEnabled(!true);
        jBtCancelarItem.setEnabled(!true);
        jBtAuditoriaItem.setEnabled(!true);
        jBtAnexarArquivo.setEnabled(!true);
        //
        jBtNovaFigura1.setEnabled(!true);
        jBtExcluirFigura1.setEnabled(!true);
        jBtVisualizaFigura1.setEnabled(!true);
        //
        jBtNovaFigura2.setEnabled(!true);
        jBtExcluirFigura2.setEnabled(!true);
        jBtVisualizaFigura2.setEnabled(!true);
        //
        jBtNovaFigura3.setEnabled(!true);
        jBtExcluirFigura3.setEnabled(!true);
        jBtVisualizaFigura3.setEnabled(!true);
        //
        jBtNovaFigura4.setEnabled(!true);
        jBtExcluirFigura4.setEnabled(!true);
        jBtVisualizaFigura4.setEnabled(!true);
    }

    public void limparTodosCampos() {
        jIdChamado.setText("");
        jStatusChamado.setText("");
        jDataChamado.setDate(null);
        jComboBoxAtendente.setSelectedItem("Selecione...");
        jComboBoxTipoChamadoSuporte.setSelectedItem("Selecione...");
        jSolicitante.setText("");
        jUnidadePrisional.setText("");
        jAssunto.setText("");
        jFigura1.setIcon(null);
        jFigura2.setIcon(null);
        jFigura3.setIcon(null);
        jFigura4.setIcon(null);
        //
        jIdItem.setText("");
        jDataOcorrencia.setDate(null);
        jHorarioInicio.setText("");
        jHorarioTermino.setText("");
        jSoftware.setText("");
        jModulo.setText("");
        jTextoSuporte.setText("");
        jTextoDesenvolvimento.setText("");
    }

    public void limparCamposItens() {
        jIdItem.setText("");
        jDataOcorrencia.setDate(null);
        jHorarioInicio.setText("");
        jHorarioTermino.setText("");
        jSoftware.setText("");
        jModulo.setText("");
        jTextoSuporte.setText("");
        jTextoDesenvolvimento.setText("");
        jFigura1.setIcon(null);
        jFigura2.setIcon(null);
        jFigura3.setIcon(null);
        jFigura4.setIcon(null);
    }

    public void Novo() {
        limparTodosCampos();
        bloquearCampos();
        bloquearBotoes();
        pLIMPAR_TABELA_itens();
        jStatusChamado.setText("ABERTO NO SUPORTE TÉCNICO");
        jDataChamado.setCalendar(Calendar.getInstance());
        jComboBoxAtendente.setEnabled(true);
        jComboBoxTipoChamadoSuporte.setEnabled(true);
        jAssunto.setEnabled(true);
        jBtNovaFigura1.setEnabled(true);
        //BOTÕES DE PESQUISA        
        jBtPesquisaSoli.setEnabled(true);
        //
        jBtSalvar.setEnabled(true);
        jBtCancelar.setEnabled(true);
    }

    public void Alterar() {
        bloquearCampos();
        bloquearBotoes();
        //
        jComboBoxAtendente.setEnabled(true);
        jComboBoxTipoChamadoSuporte.setEnabled(true);
        jAssunto.setEnabled(true);
        jBtPesquisaSoli.setEnabled(true);
        //
        jBtSalvar.setEnabled(true);
        jBtCancelar.setEnabled(true);
    }

    public void alterarAdm() {
        bloquearCampos();
        bloquearBotoes();
        jHorarioInicio.setEnabled(true);
        jHorarioTermino.setEnabled(true);
        jTextoSuporte.setEnabled(true);
        jAssunto.setEnabled(true);
        jBtNovaFigura1.setEnabled(true);
        //
        jBtPesquisaModulo.setEnabled(true);
        //
        jBtSalvarItem.setEnabled(true);
        jBtCancelarItem.setEnabled(true);
    }

    public void Excluir() {
        limparTodosCampos();
        bloquearCampos();
        bloquearBotoes();
        //
        jBtNovo.setEnabled(true);
    }

    public void Salvar() {
        bloquearCampos();
        bloquearBotoes();
        jBtNovo.setEnabled(true);
        jBtAlterar.setEnabled(true);
        jBtExcluir.setEnabled(true);
        jBtAuditoria.setEnabled(true);
        //
        jBtEncerrar.setEnabled(true);
        jBtImprimir.setEnabled(true);
        jBtEnviar.setEnabled(true);
        //
        jBtNovoItem.setEnabled(true);
    }

    public void Cancelar() {
        if (jIdChamado.getText().equals("")) {
            limparTodosCampos();
            bloquearCampos();
            bloquearBotoes();
            //
            jBtNovo.setEnabled(true);
        } else {
            bloquearCampos();
            bloquearBotoes();
            cancelarEdicao();
            //
            jBtNovo.setEnabled(true);
            jBtAlterar.setEnabled(true);
            jBtExcluir.setEnabled(true);
            jBtAuditoria.setEnabled(true);
            //
            jBtEncerrar.setEnabled(true);
            jBtImprimir.setEnabled(true);
            jBtEnviar.setEnabled(true);
            //
            jBtNovoItem.setEnabled(true);
        }
    }

    public void pPREENCHER_COMBO_atendentes() {
        CONTROL.pBUSCAR_NOME_atendente(objCHSup);
    }

    public void NovoItem() {
        bloquearCampos();
        bloquearBotoes();
        limparCamposItens();
        jDataOcorrencia.setCalendar(Calendar.getInstance());
        jHorarioInicio.setText(jHoraSistema.getText());
        //
        jTextoSuporte.setEnabled(true);
        jBtAnexarArquivo.setEnabled(true);
        //
        jBtNovaFigura1.setEnabled(true);
        jBtExcluirFigura1.setEnabled(true);
        //
        jBtNovaFigura2.setEnabled(true);
        jBtExcluirFigura2.setEnabled(true);
        //
        jBtNovaFigura3.setEnabled(true);
        jBtExcluirFigura3.setEnabled(true);
        //
        jBtNovaFigura4.setEnabled(true);
        jBtExcluirFigura4.setEnabled(true);
        //
        jBtPesquisaModulo.setEnabled(true);
        //
        jBtSalvarItem.setEnabled(true);
        jBtCancelarItem.setEnabled(true);
    }

    public void AlterarItem() {
        bloquearCampos();
        bloquearBotoes();
        //
        jTextoSuporte.setEnabled(true);
        jHorarioTermino.setEnabled(true);
        //
        jBtPesquisaModulo.setEnabled(true);
        jBtAnexarArquivo.setEnabled(true);
        //
        jBtNovaFigura1.setEnabled(true);
        jBtExcluirFigura1.setEnabled(true);
        jBtVisualizaFigura1.setEnabled(true);
        //
        jBtNovaFigura2.setEnabled(true);
        jBtExcluirFigura2.setEnabled(true);
        jBtVisualizaFigura2.setEnabled(true);
        //
        jBtNovaFigura3.setEnabled(true);
        jBtExcluirFigura3.setEnabled(true);
        jBtVisualizaFigura3.setEnabled(true);
        //
        jBtNovaFigura4.setEnabled(true);
        jBtExcluirFigura4.setEnabled(true);
        jBtVisualizaFigura4.setEnabled(true);
        //
        jBtSalvarItem.setEnabled(true);
        jBtCancelarItem.setEnabled(true);
    }

    public void ExcluirItem() {
        bloquearCampos();
        bloquearBotoes();
        limparCamposItens();
        //
        jBtNovoItem.setEnabled(true);
        //
        jBtNovo.setEnabled(true);
        jBtAlterar.setEnabled(true);
        jBtExcluir.setEnabled(true);
        jBtAuditoria.setEnabled(true);
        //
        jBtEnviar.setEnabled(true);
        jBtEncerrar.setEnabled(true);
    }

    public void SalvarItem() {
        bloquearCampos();
        bloquearBotoes();
        limparCamposItens();
        //
        jBtNovoItem.setEnabled(true);
        //
        jBtNovo.setEnabled(true);
        jBtAlterar.setEnabled(true);
        jBtExcluir.setEnabled(true);
        jBtAuditoria.setEnabled(true);
        //
        jBtEnviar.setEnabled(true);
        jBtEncerrar.setEnabled(true);
    }

    public void CancelarItem() {
        bloquearCampos();
        bloquearBotoes();
        limparCamposItens();
        //
        jBtNovoItem.setEnabled(true);
        //
        jBtNovo.setEnabled(true);
        jBtAlterar.setEnabled(true);
        jBtExcluir.setEnabled(true);
        jBtAuditoria.setEnabled(true);
        //
        jBtEnviar.setEnabled(true);
        jBtEncerrar.setEnabled(true);
    }

    public void cancelarEdicao() {
        flag = 1;
        if (flag == 1) {
            String idSoli = "" + jTabelaChamdosSup.getValueAt(jTabelaChamdosSup.getSelectedRow(), 0);
            jIdChamadoPesquisa.setText(idSoli);
            //            
            limparTodosCampos();
            bloquearCampos();
            bloquearBotoes();
            jBtNovo.setEnabled(true);
            jBtAlterar.setEnabled(true);
            jBtExcluir.setEnabled(true);
            jBtAuditoria.setEnabled(true);
            //
            jBtEncerrar.setEnabled(true);
            jBtImprimir.setEnabled(true);
            jBtEnviar.setEnabled(true);
            jBtReabrir.setEnabled(true);
            //
            jBtNovoItem.setEnabled(true);
            jComboBoxAtendente.removeAllItems();
            try {
                for (ChamadoSuporte b : CONTROL.MOSTRAR_DADOS_PESQUISADOS_read()) {
                    jIdChamado.setText(String.valueOf(b.getIdCHSup()));
                    jStatusChamado.setText(b.getStatusCha());
                    jDataChamado.setDate(b.getDataCha());
                    jComboBoxAtendente.addItem(b.getNomeAtendente());
                    idSolicitante = b.getIdSolicitante();
                    jSolicitante.setText(b.getNomeSolicitante());
                    idUnidade = b.getIdUnidEmp();
                    jUnidadePrisional.setText(b.getDescricaoUnidade());
                    jAssunto.setText(b.getAssunto());
                    jComboBoxTipoChamadoSuporte.setSelectedItem(b.getTipoChamadoSuporte());
                }
            } catch (Exception ex) {
                Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
            }
            pLIMPAR_TABELA_itens();
            MOSTRAR_ITENS_chamado();
        }
    }

    public void relatorioChamadosST() {
        try {
            conecta.abrirConexao();
            String path = "reports/relatorioChamadoSuporteTecnico.jasper";
            conecta.executaSQL("SELECT * FROM CHAMADOS_SUPORTE "
                    + "INNER JOIN USUARIOS "
                    + "ON CHAMADOS_SUPORTE.IdUsuario=USUARIOS.IdUsuario "
                    + "INNER JOIN UNIDADE_PENAL_EMPRESA "
                    + "ON CHAMADOS_SUPORTE.IdUnidEmp=UNIDADE_PENAL_EMPRESA.IdUnidEmp "
                    + "INNER JOIN SOLICITANTES "
                    + "ON CHAMADOS_SUPORTE.IdSolicitante=SOLICITANTES.IdSolicitante "
                    + "WHERE CHAMADOS_SUPORTE.IdCHSup='" + jIdChamado.getText() + "'");
            HashMap parametros = new HashMap();
            parametros.put("idChamado", jIdChamado.getText());
            parametros.put("nomeUsuario", nameUser);
            // Sub Relatório
            try {
                parametros.put("REPORT_CONNECTION", conecta.stmt.getConnection());
            } catch (SQLException ex) {
            }
            JRResultSetDataSource relatResul = new JRResultSetDataSource(conecta.rs); // Passa o resulSet Preenchido para o relatorio                                   
            JasperPrint jpPrint = JasperFillManager.fillReport(path, parametros, relatResul); // indica o caminmhodo relatório
            JasperViewer jv = new JasperViewer(jpPrint, false); // Cria instancia para impressao          
            jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH); // Maximizar o relatório
            jv.setTitle("Relatório de Chamado no Suporte Técnico");
            jv.setVisible(true); // Chama o relatorio para ser visualizado                                    
            jv.toFront(); // Traz o relatorio para frente da aplicação            
            conecta.desconecta();
        } catch (JRException e) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao chamar o Relatório \n\nERRO :" + e);
        }
        conecta.desconecta();
    }

    public void pLIMPAR_TABELA_chamados() {
        // APAGAR DADOS DA TABELA
        while (jTabelaChamdosSup.getModel().getRowCount() > 0) {
            ((DefaultTableModel) jTabelaChamdosSup.getModel()).removeRow(0);
        }
        jtotalRegistros.setText("");
    }

    public void pLIMPAR_TABELA_itens() {
        // APAGAR DADOS DA TABELA
        while (jTabelaItens.getModel().getRowCount() > 0) {
            ((DefaultTableModel) jTabelaItens.getModel()).removeRow(0);
        }
        jtotalRegistros.setText("");
    }

    public void MOSTRAR_TABELA_assuntos() {
        DefaultTableModel dadosOrigem = (DefaultTableModel) jTabelaChamdosSup.getModel();
        ChamadoSuporte d = new ChamadoSuporte();
        try {
            for (ChamadoSuporte dd : CONTROL.PESQUISAR_ASSUNTOS_read()) {
                pDATA_Registros = String.valueOf(dd.getDataCha());
                String dia = pDATA_Registros.substring(8, 10);
                String mes = pDATA_Registros.substring(5, 7);
                String ano = pDATA_Registros.substring(0, 4);
                pDATA_Registros = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(pTOTAL_registros));
                dadosOrigem.addRow(new Object[]{dd.getIdCHSup(), pDATA_Registros, dd.getStatusCha(), dd.getAssunto(), dd.getNomeSolicitante(), dd.getDescricaoUnidade()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaChamdosSup.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaChamdosSup.getColumnModel().getColumn(0).setCellRenderer(centralizado);
                jTabelaChamdosSup.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MOSTRAR_DADOS_CODIGO_adm() {
        DefaultTableModel dadosOrigem = (DefaultTableModel) jTabelaChamdosSup.getModel();
        ChamadoSuporte d = new ChamadoSuporte();
        try {
            for (ChamadoSuporte dd : CONTROL.PESQUISAR_CODIGO_ADMINISTRADOR_read()) {
                pDATA_Registros = String.valueOf(dd.getDataCha());
                String dia = pDATA_Registros.substring(8, 10);
                String mes = pDATA_Registros.substring(5, 7);
                String ano = pDATA_Registros.substring(0, 4);
                pDATA_Registros = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(pTOTAL_registros));
                dadosOrigem.addRow(new Object[]{dd.getIdCHSup(), pDATA_Registros, dd.getStatusCha(), dd.getAssunto(), dd.getNomeSolicitante(), dd.getDescricaoUnidade()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaChamdosSup.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaChamdosSup.getColumnModel().getColumn(0).setCellRenderer(centralizado);
                jTabelaChamdosSup.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MOSTRAR_DADOS_NIVEL_01() {
        DefaultTableModel dadosOrigem = (DefaultTableModel) jTabelaChamdosSup.getModel();
        ChamadoSuporte d = new ChamadoSuporte();
        try {
            for (ChamadoSuporte dd : CONTROL.PESQUISAR_CODIGO_nivel01_read()) {
                pDATA_Registros = String.valueOf(dd.getDataCha());
                String dia = pDATA_Registros.substring(8, 10);
                String mes = pDATA_Registros.substring(5, 7);
                String ano = pDATA_Registros.substring(0, 4);
                pDATA_Registros = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(pTOTAL_registros));
                dadosOrigem.addRow(new Object[]{dd.getIdCHSup(), pDATA_Registros, dd.getStatusCha(), dd.getAssunto(), dd.getNomeSolicitante(), dd.getDescricaoUnidade()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaChamdosSup.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaChamdosSup.getColumnModel().getColumn(0).setCellRenderer(centralizado);
                jTabelaChamdosSup.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MOSTRAR_DADOS_NIVEL_2() {
        DefaultTableModel dadosOrigem = (DefaultTableModel) jTabelaChamdosSup.getModel();
        ChamadoSuporte d = new ChamadoSuporte();
        try {
            for (ChamadoSuporte dd : CONTROL.PESQUISAR_CODIGO_nivel2_read()) {
                pDATA_Registros = String.valueOf(dd.getDataCha());
                String dia = pDATA_Registros.substring(8, 10);
                String mes = pDATA_Registros.substring(5, 7);
                String ano = pDATA_Registros.substring(0, 4);
                pDATA_Registros = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(pTOTAL_registros));
                dadosOrigem.addRow(new Object[]{dd.getIdCHSup(), pDATA_Registros, dd.getStatusCha(), dd.getAssunto(), dd.getNomeSolicitante(), dd.getDescricaoUnidade()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaChamdosSup.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaChamdosSup.getColumnModel().getColumn(0).setCellRenderer(centralizado);
                jTabelaChamdosSup.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MOSTRAR_DADOS_SOLICITANTES_adm() {
        DefaultTableModel dadosOrigem = (DefaultTableModel) jTabelaChamdosSup.getModel();
        ChamadoSuporte d = new ChamadoSuporte();
        try {
            for (ChamadoSuporte dd : CONTROL.PESQUISAR_SOLICITANTES_ADM_read()) {
                pDATA_Registros = String.valueOf(dd.getDataCha());
                String dia = pDATA_Registros.substring(8, 10);
                String mes = pDATA_Registros.substring(5, 7);
                String ano = pDATA_Registros.substring(0, 4);
                pDATA_Registros = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(pTOTAL_registros));
                dadosOrigem.addRow(new Object[]{dd.getIdCHSup(), pDATA_Registros, dd.getStatusCha(), dd.getAssunto(), dd.getNomeSolicitante(), dd.getDescricaoUnidade()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaChamdosSup.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaChamdosSup.getColumnModel().getColumn(0).setCellRenderer(centralizado);
                jTabelaChamdosSup.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MOSTRAR_DADOS_SOLICITANTES_NIVEL_01() {
        DefaultTableModel dadosOrigem = (DefaultTableModel) jTabelaChamdosSup.getModel();
        ChamadoSuporte d = new ChamadoSuporte();
        try {
            for (ChamadoSuporte dd : CONTROL.PESQUISAR_SOLICITANTES_NIVEL_01_read()) {
                pDATA_Registros = String.valueOf(dd.getDataCha());
                String dia = pDATA_Registros.substring(8, 10);
                String mes = pDATA_Registros.substring(5, 7);
                String ano = pDATA_Registros.substring(0, 4);
                pDATA_Registros = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(pTOTAL_registros));
                dadosOrigem.addRow(new Object[]{dd.getIdCHSup(), pDATA_Registros, dd.getStatusCha(), dd.getAssunto(), dd.getNomeSolicitante(), dd.getDescricaoUnidade()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaChamdosSup.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaChamdosSup.getColumnModel().getColumn(0).setCellRenderer(centralizado);
                jTabelaChamdosSup.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MOSTRAR_DADOS_SOLICITANTES_NIVEL_2() {
        DefaultTableModel dadosOrigem = (DefaultTableModel) jTabelaChamdosSup.getModel();
        ChamadoSuporte d = new ChamadoSuporte();
        try {
            for (ChamadoSuporte dd : CONTROL.PESQUISAR_SOLICITANTES_NIVEL_01_read()) {
                pDATA_Registros = String.valueOf(dd.getDataCha());
                String dia = pDATA_Registros.substring(8, 10);
                String mes = pDATA_Registros.substring(5, 7);
                String ano = pDATA_Registros.substring(0, 4);
                pDATA_Registros = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(pTOTAL_registros));
                dadosOrigem.addRow(new Object[]{dd.getIdCHSup(), pDATA_Registros, dd.getStatusCha(), dd.getAssunto(), dd.getNomeSolicitante(), dd.getDescricaoUnidade()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaChamdosSup.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaChamdosSup.getColumnModel().getColumn(0).setCellRenderer(centralizado);
                jTabelaChamdosSup.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MOSTRAR_DADOS_TODOS_adm() {
        DefaultTableModel dadosOrigem = (DefaultTableModel) jTabelaChamdosSup.getModel();
        ChamadoSuporte d = new ChamadoSuporte();
        try {
            for (ChamadoSuporte dd : CONTROL.PESQUISAR_TODOS_ADM_read()) {
                pDATA_Registros = String.valueOf(dd.getDataCha());
                String dia = pDATA_Registros.substring(8, 10);
                String mes = pDATA_Registros.substring(5, 7);
                String ano = pDATA_Registros.substring(0, 4);
                pDATA_Registros = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(pTOTAL_registros));
                dadosOrigem.addRow(new Object[]{dd.getIdCHSup(), pDATA_Registros, dd.getStatusCha(), dd.getAssunto(), dd.getNomeSolicitante(), dd.getDescricaoUnidade()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaChamdosSup.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaChamdosSup.getColumnModel().getColumn(0).setCellRenderer(centralizado);
                jTabelaChamdosSup.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MOSTRAR_DADOS_TODOS_NIVEL_01() {
        DefaultTableModel dadosOrigem = (DefaultTableModel) jTabelaChamdosSup.getModel();
        ChamadoSuporte d = new ChamadoSuporte();
        try {
            for (ChamadoSuporte dd : CONTROL.PESQUISAR_TODOS_NIVEL01_read()) {
                pDATA_Registros = String.valueOf(dd.getDataCha());
                String dia = pDATA_Registros.substring(8, 10);
                String mes = pDATA_Registros.substring(5, 7);
                String ano = pDATA_Registros.substring(0, 4);
                pDATA_Registros = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(pTOTAL_registros));
                dadosOrigem.addRow(new Object[]{dd.getIdCHSup(), pDATA_Registros, dd.getStatusCha(), dd.getAssunto(), dd.getNomeSolicitante(), dd.getDescricaoUnidade()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaChamdosSup.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaChamdosSup.getColumnModel().getColumn(0).setCellRenderer(centralizado);
                jTabelaChamdosSup.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MOSTRAR_DADOS_TODOS_NIVEL_2() {
        DefaultTableModel dadosOrigem = (DefaultTableModel) jTabelaChamdosSup.getModel();
        ChamadoSuporte d = new ChamadoSuporte();
        try {
            for (ChamadoSuporte dd : CONTROL.PESQUISAR_TODOS_NIVEL2_read()) {
                pDATA_Registros = String.valueOf(dd.getDataCha());
                String dia = pDATA_Registros.substring(8, 10);
                String mes = pDATA_Registros.substring(5, 7);
                String ano = pDATA_Registros.substring(0, 4);
                pDATA_Registros = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(pTOTAL_registros));
                dadosOrigem.addRow(new Object[]{dd.getIdCHSup(), pDATA_Registros, dd.getStatusCha(), dd.getAssunto(), dd.getNomeSolicitante(), dd.getDescricaoUnidade()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaChamdosSup.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaChamdosSup.getColumnModel().getColumn(0).setCellRenderer(centralizado);
                jTabelaChamdosSup.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MOSTRAR_DADOS_DATAS_adm() {
        DefaultTableModel dadosOrigem = (DefaultTableModel) jTabelaChamdosSup.getModel();
        ChamadoSuporte d = new ChamadoSuporte();
        try {
            for (ChamadoSuporte dd : CONTROL.PESQUISAR_DATAS_ADM_read()) {
                pDATA_Registros = String.valueOf(dd.getDataCha());
                String dia = pDATA_Registros.substring(8, 10);
                String mes = pDATA_Registros.substring(5, 7);
                String ano = pDATA_Registros.substring(0, 4);
                pDATA_Registros = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(pTOTAL_registros));
                dadosOrigem.addRow(new Object[]{dd.getIdCHSup(), pDATA_Registros, dd.getStatusCha(), dd.getAssunto(), dd.getNomeSolicitante(), dd.getDescricaoUnidade()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaChamdosSup.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaChamdosSup.getColumnModel().getColumn(0).setCellRenderer(centralizado);
                jTabelaChamdosSup.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MOSTRAR_DADOS_DATAS_NIVEL_01() {
        DefaultTableModel dadosOrigem = (DefaultTableModel) jTabelaChamdosSup.getModel();
        ChamadoSuporte d = new ChamadoSuporte();
        try {
            for (ChamadoSuporte dd : CONTROL.PESQUISAR_DATAS_NIVEL01_read()) {
                pDATA_Registros = String.valueOf(dd.getDataCha());
                String dia = pDATA_Registros.substring(8, 10);
                String mes = pDATA_Registros.substring(5, 7);
                String ano = pDATA_Registros.substring(0, 4);
                pDATA_Registros = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(pTOTAL_registros));
                dadosOrigem.addRow(new Object[]{dd.getIdCHSup(), pDATA_Registros, dd.getStatusCha(), dd.getAssunto(), dd.getNomeSolicitante(), dd.getDescricaoUnidade()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaChamdosSup.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaChamdosSup.getColumnModel().getColumn(0).setCellRenderer(centralizado);
                jTabelaChamdosSup.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MOSTRAR_DADOS_DATAS_NIVEL_2() {
        DefaultTableModel dadosOrigem = (DefaultTableModel) jTabelaChamdosSup.getModel();
        ChamadoSuporte d = new ChamadoSuporte();
        try {
            for (ChamadoSuporte dd : CONTROL.PESQUISAR_DATAS_NIVEL2_read()) {
                pDATA_Registros = String.valueOf(dd.getDataCha());
                String dia = pDATA_Registros.substring(8, 10);
                String mes = pDATA_Registros.substring(5, 7);
                String ano = pDATA_Registros.substring(0, 4);
                pDATA_Registros = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(pTOTAL_registros));
                dadosOrigem.addRow(new Object[]{dd.getIdCHSup(), pDATA_Registros, dd.getStatusCha(), dd.getAssunto(), dd.getNomeSolicitante(), dd.getDescricaoUnidade()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaChamdosSup.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaChamdosSup.getColumnModel().getColumn(0).setCellRenderer(centralizado);
                jTabelaChamdosSup.getColumnModel().getColumn(1).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MOSTRAR_ITENS_chamado() {
        DefaultTableModel dadosItens = (DefaultTableModel) jTabelaItens.getModel();
        ChamadoSuporte d = new ChamadoSuporte();
        try {
            for (ChamadoSuporte dd : CONTROL.PESQUISAR_ITENS_CHAMADO_SUP_read()) {
                pDATA_itens = String.valueOf(dd.getDataItemCh());
                String dia = pDATA_Registros.substring(8, 10);
                String mes = pDATA_Registros.substring(5, 7);
                String ano = pDATA_Registros.substring(0, 4);
                pDATA_itens = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(pTOTAL_itens));
                dadosItens.addRow(new Object[]{dd.getIdItemCh(), pDATA_itens, dd.getHorarioInicio(), dd.getHorarioTermino(), dd.getTextoSuporte()});
                // BARRA DE ROLAGEM HORIZONTAL
                jTabelaItens.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                // ALINHAR TEXTO DA TABELA CENTRALIZADO
                DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
                centralizado.setHorizontalAlignment(SwingConstants.CENTER);
                //
                jTabelaItens.getColumnModel().getColumn(0).setCellRenderer(centralizado);
                jTabelaItens.getColumnModel().getColumn(1).setCellRenderer(centralizado);
                jTabelaItens.getColumnModel().getColumn(2).setCellRenderer(centralizado);
                jTabelaItens.getColumnModel().getColumn(3).setCellRenderer(centralizado);
            }
        } catch (Exception ex) {
            Logger.getLogger(TelaChamadoSuporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarAcessoUsuario(String nomeTelaAcesso) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "IdUsuario, "
                    + "NomeUsuario "
                    + "FROM USUARIOS "
                    + "WHERE NomeUsuario='" + nameUser + "'");
            conecta.rs.first();
            codigoUser = conecta.rs.getInt("IdUsuario");
        } catch (Exception e) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS_ACESSO "
                    + "WHERE IdUsuario='" + codigoUser + "' "
                    + "AND NomeTela='" + nomeTelaAcesso + "'");
            conecta.rs.first();
            codUserAcesso = conecta.rs.getInt("IdUsuario");
            codAbrir = conecta.rs.getInt("Abrir");
            codIncluir = conecta.rs.getInt("Incluir");
            codAlterar = conecta.rs.getInt("Alterar");
            codExcluir = conecta.rs.getInt("Excluir");
            codGravar = conecta.rs.getInt("Gravar");
            codConsultar = conecta.rs.getInt("Consultar");
            nomeTela = conecta.rs.getString("NomeTela");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    public void buscarNivelUsuario() {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT "
                    + "* "
                    + "FROM USUARIOS "
                    + "WHERE NomeUsuario='" + nameUser + "'");
            conecta.rs.first();
            nivelUsuario = conecta.rs.getInt("NivelUsuario");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }

    public void objLog() {
        objLogSys.setDataMov(dataModFinal);
        objLogSys.setHorarioMov(horaMov);
        objLogSys.setNomeModuloTela(nomeModuloTela);
        objLogSys.setIdLancMov(Integer.valueOf(jIdChamado.getText()));
        objLogSys.setNomeUsuarioLogado(nameUser);
        objLogSys.setStatusMov(statusMov);
    }

    public void objLog1() {
        objLogSys.setDataMov(dataModFinal);
        objLogSys.setHorarioMov(horaMov);
        objLogSys.setNomeModuloTela(nomeModuloTela);
        objLogSys.setIdLancMov(idItemChamado);
        objLogSys.setNomeUsuarioLogado(nameUser);
        objLogSys.setStatusMov(statusMov);
    }
}
