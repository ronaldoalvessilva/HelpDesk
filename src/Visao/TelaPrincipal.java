/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Controle.ModeloTabela;
import Controle.converterDataStringDataDate;
import static Controle.converterDataStringDataDate.dataSisConvert;
import Dao.ConexaoBancoDados;
import Dao.telasSistemaDao;
import Modelo.CadastroTelasSistema;
import Util.SQL.TableExample;
import static Visao.LoginHD.nameUser;
import static Visao.TelaAgendaCompromissos.jAssunto;
import static Visao.TelaAgendaCompromissos.jBtAlterarComp;
import static Visao.TelaAgendaCompromissos.jBtCancelarComp;
import static Visao.TelaAgendaCompromissos.jBtConfirmarCompromisso;
import static Visao.TelaAgendaCompromissos.jBtExcluirComp;
import static Visao.TelaAgendaCompromissos.jBtNovoComp;
import static Visao.TelaAgendaCompromissos.jBtSalvarComp;
import static Visao.TelaAgendaCompromissos.jCodigoAgendaComp;
import static Visao.TelaAgendaCompromissos.jComboBoxConclusao;
import static Visao.TelaAgendaCompromissos.jComboBoxPrioridade;
import static Visao.TelaAgendaCompromissos.jComboBoxStatusComp;
import static Visao.TelaAgendaCompromissos.jComboBoxTipoEvento;
import static Visao.TelaAgendaCompromissos.jDataEvento;
import static Visao.TelaAgendaCompromissos.jDataInicio;
import static Visao.TelaAgendaCompromissos.jDataLembrete;
import static Visao.TelaAgendaCompromissos.jDataTermino;
import static Visao.TelaAgendaCompromissos.jHoraInicio;
import static Visao.TelaAgendaCompromissos.jHoraLembrete;
import static Visao.TelaAgendaCompromissos.jHoraTermino;
import static Visao.TelaAgendaCompromissos.jNomeUsuarioAgenda;
import static Visao.TelaAgendaCompromissos.jTabelaAgendaEventos;
import static Visao.TelaAgendaCompromissos.jTextoEvento;
import static Visao.TelaAgendaCompromissos.jtotalRegistros;
import static Visao.TelaRecados.jBtAlterar;
import static Visao.TelaRecados.jBtCancelar;
import static Visao.TelaRecados.jBtConfirmar;
import static Visao.TelaRecados.jBtExcluir;
import static Visao.TelaRecados.jBtNovo;
import static Visao.TelaRecados.jBtResponder;
import static Visao.TelaRecados.jBtSalvar;
import static Visao.TelaRecados.jComboBoxStatus;
import static Visao.TelaRecados.jDataLanc;
import static Visao.TelaRecados.jHoraRecado;
import static Visao.TelaRecados.jIDLanc;
import static Visao.TelaRecados.jNomeDestinatario;
import static Visao.TelaRecados.jNomeRementente;
import static Visao.TelaRecados.jRecado;
import static Visao.TelaRecados.jTabelaTodosRecados;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ronal
 */
public class TelaPrincipal extends javax.swing.JFrame {

    ConexaoBancoDados conecta = new ConexaoBancoDados();
    CadastroTelasSistema objCadastroTela = new CadastroTelasSistema();
    telasSistemaDao controle = new telasSistemaDao();
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss"); // HORAIO DE 24 HORAS, PARA O DE 12 HORAS UTILIZAR hh:mm:ss
    SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
    converterDataStringDataDate convertedata = new converterDataStringDataDate();
    //    
    private TelaEmpresa objEmp = null;
    private TelaOcorrenciasHD objOcr = null;
    private TelaSolicitantes objSoli = null;
    private TelaUsuarios objUser = null;
    private TelaSoftware objSoftware = null;
    private TelaModuloSistema objModulo = null;
    private TelaRecados objjAgendaRec = null;
    private TelaAgendaCompromissos objAgendaComp = null;
    private TelaChamadoSuporte objChamaSup = null;
    private TelaChamadoDesenvolvimento objChamaSupDesn = null;
    private TelaAtendentesSuporte objAtendente = null;
    //
    String statusAgenda = "Pendente";
    String usuarioLogado, dataLanc;
    int codUsuario;
    int flag;
    int count = 0;
    // VARIÁVEIS PARA AGENDA DE COMPROMISSO.
    String dataAgenda;
    String nomeUsuarioCompromisso; // USUÁRIO DA AGENDA DE COMPROMISSO.
    String horaLembrete;
    String usuarioAgenda;
    String codigoAgendaComp;
    //VARIAVEL QUE IMPEDI OUTRO USUÁRIO A EDITAR OU EXCLUIR O REGISTRO CRIADO PELO USUÁRIO QUE CRIOU
    public static String nomeUserRegistro;
    //
    int tempo = (1000 * 60) * 10;   // 5 min.  
    int periodo = 1;  // quantidade de vezes a ser executado.  
    //
    //
    public static int codigoUser = 0;
    public static int codUserAcesso = 0;
    public static int codigoUserGroup = 0;
    public static int codAbrir = 0;
    public static int codIncluir = 0;
    public static int codAlterar = 0;
    public static int codExcluir = 0;
    public static int codGravar = 0;
    public static int codConsultar = 0;
    public static int codigoGrupo = 0;
    public static String nomeGrupo = "";
    public static String nomeTela = "";
    // MENU CADASTRO    
    public static String telaCadastroEmpresa = "Cadastro:Empresas:Manutenção";
    public static String telaCadastroUnidades = "Cadastro:Unidades Prisionais:Manutenção";
    public static String telaOcorrenciaManu = "Cadastro:Ocorrências:Manutenção";
    public static String telaCadastroSolicitantes = "Cadastro:Solicitantes:Manutenção";
    public static String telaCadastroUsuarios = "Cadastro:Usuarios:Manutenção";
    public static String telaCadastroAcessos = "Cadastro:Usuarios:Acessos";
    public static String telaCadastroCopiar = "Cadastro:Usuarios:Copiar";
    public static String telaCadastroSistemas = "Cadastro:Sistemas:Manutenção";
    public static String telaCadastroModulos = "Cadastro:Módulos:Manutenção";
    public static String telaAgendaRecado = "Cadastro Agenda Recados:Manutenção";
    public static String telaAgendaCompromisso = "Cadastro Agenda de Compromisso:Manutenção";
    //SUPORTE CHAMADOS
    public static String telaChamadosSuporte = "Suporte Técnico:Chamados Suporte:Manutenção";
    public static String telaItensChamadoSuporte = "Suporte Técnico:Chamados Suporte:Itens";
    public static String botaoEncerrarSup = "Encerrar Chamado no Suporte";
    public static String botaoImprimirSup = "Imprimir Chamado no Suporte";
    public static String botaoEnviarSup = "Enviar Chamado no Suporte";
    public static String botaoReabrirSup = "Reabrir Chamado no Suporte";
    //SUPORTE DESENVOLVIMENTO
    public static String telaChamadosDesenvolvimento = "Desenvolvimento:Chamados Desenvolvimento:Manutenção";
    public static String telaItensChamadoDesenvolvimento = "Desenvolvimento:Chamados Desenvolvimento:Itens";
    public static String botaoEncerrarDes = "Encerrar Chamado no Desenvolvedor";
    public static String botaoImprimirDes = "Imprimir Chamado no Desenvolvedor";
    public static String botaoEnviarDes = "Enviar Chamado no Desenvolvedor";
    public static String botaoReabrirDes = "Reabrir Chamado no Desenvolvedor";
    public static String botaoBuscarCH = "Buscar Chamados no Suporte Técnico";
    public static String telaConsultasSql = "Desenvolvimento:Consultas SQL:Manutenção";
    public static String telaCadastroAtendentes = "Cadastro:Atendentes:Manutenção";
    // MENU CADASTRO
    //EMPRESA
    String pNomeCE = "";
    //UNIDADES
    String pNomeCUN = "";
    //OCORRÊNCIAS
    String pNomeOCR = "";
    //SOLICITANTES
    String pNomeCS = "";
    //USUÁRIOS
    String pNomeCU = "";
    String pNomeCAU = "";
    String pNomeCCAU = "";
    //SISTEMAS
    String pNomeSI = "";
    //MÓDULOS
    String pNomeCM = "";
    // AGENDAS
    String pNomeARM = "";
    String pNomeACM = "";
    //CHAMADOS DE SUPORTE
    String pNomeCSU = "";
    String pNomeCSI = "";
    String pNomeBTE = "";
    String pNomeBTI = "";
    String pNomeBTV = "";
    String pNomeBTR = "";
    //CHAMADOS DESENVOLVIMENTO
    String pNomeCD = "";
    String pNomeCDI = "";
    String pNomeCSUD = "";
    String pNomeBTED = "";
    String pNomeBTID = "";
    String pNomeBTVD = "";
    String pNomeBTRD = "";
    String pNomeBCSD = "";
    //CONSULTAS SQL
    String pNomeCSQL = "";
    //ATENDENTES
    String pNomeAT = "";
    //    
    public static String tipoServidor = "";
    public static String tipoBancoDados = "";

    public static TelaTrocaSenha telaTrocaSenha;

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        jLoginConectado.setText(nameUser);
        setExtendedState(MAXIMIZED_BOTH); // Maximnizar a tela prinicpal
        Thread threadRelogio = new Thread() {

            @Override
            public void run() {
                rodaRelogio();
            }
        };
        threadRelogio.start();
        Date data = new Date();
        String hora = formatter.format(data); // Data da conexão
        String date = formatter2.format(data); // Hora da conexão
        jHoraSistema.setText(String.valueOf(hora));    // no lugar do label, por seu JTextField    
        jDataSistema.setText(String.valueOf(date));
        //     
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Impedir que a janela seja fechada pelo X    
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (e.getID() == WindowEvent.WINDOW_CLOSING) {
                    int selectedOption = JOptionPane.showConfirmDialog(null, "Deseja realmente sair do Sistema?", "Sistema informa:", JOptionPane.YES_NO_OPTION);
                    if (selectedOption == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });
        pesquisarTelasAcessos();
        verificarParametrosSRV();
        threadMensagem(); // A cada 5 minutos verifica mensagem   

    }

    public void mostrarTelaTrocaSenha() {
        telaTrocaSenha = new TelaTrocaSenha(this, true);
        telaTrocaSenha.setVisible(true);
    }

    TelaPrincipal(String text, String nameUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPainelPrincipal = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jBtOcorrencias = new javax.swing.JButton();
        jBtEmpresa = new javax.swing.JButton();
        jBtUsuarios = new javax.swing.JButton();
        jBtSolicitantes = new javax.swing.JButton();
        jBtSistemas = new javax.swing.JButton();
        jBtModulos = new javax.swing.JButton();
        jBtAgendaRecados = new javax.swing.JButton();
        jBtAgendaCompromisso = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel3 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jLoginConectado = new javax.swing.JLabel();
        jToolBar4 = new javax.swing.JToolBar();
        jLabel5 = new javax.swing.JLabel();
        jToolBar3 = new javax.swing.JToolBar();
        jDataSistema = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jBtTrocarSenha = new javax.swing.JButton();
        jBtLogoff = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jBtChamadosSuporte = new javax.swing.JButton();
        jBtRelatoriosSuporteTecnico = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jBtConsultasDB = new javax.swing.JButton();
        jBtRelatorios = new javax.swing.JButton();
        jBtChamadosDesenvol = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jHoraSistema = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jCadastro = new javax.swing.JMenu();
        jEmpresa = new javax.swing.JMenuItem();
        jOcorrencias = new javax.swing.JMenuItem();
        jSolicitantes = new javax.swing.JMenuItem();
        jatendentes = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jUsuarios = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jSoftware = new javax.swing.JMenuItem();
        jModulosSistema = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jAgendaRecados = new javax.swing.JMenuItem();
        jAgendaCompromisso = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jSair = new javax.swing.JMenuItem();
        jSuporteTecnico = new javax.swing.JMenu();
        jChamadosSuporte = new javax.swing.JMenuItem();
        jDesenvolvimento = new javax.swing.JMenu();
        jConsultasSQL = new javax.swing.JMenuItem();
        jChamadosDesenvolvimento = new javax.swing.JMenuItem();
        jRelatorios = new javax.swing.JMenu();
        listagemChamadosSuporteTecnico = new javax.swing.JMenuItem();
        listagemChamadosDesenvolvimento = new javax.swing.JMenuItem();
        jSobre = new javax.swing.JMenu();
        jInformacoes = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("...::: Sistema de Suporte Técnico :::...");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagens/HelpDeskMas_16.png")));

        javax.swing.GroupLayout jPainelPrincipalLayout = new javax.swing.GroupLayout(jPainelPrincipal);
        jPainelPrincipal.setLayout(jPainelPrincipalLayout);
        jPainelPrincipalLayout.setHorizontalGroup(
            jPainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPainelPrincipalLayout.setVerticalGroup(
            jPainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204))));
        jPanel1.setToolTipText("Cadastros");

        jBtOcorrencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/250119203502_32.png"))); // NOI18N
        jBtOcorrencias.setToolTipText("Ocorrências");
        jBtOcorrencias.setContentAreaFilled(false);
        jBtOcorrencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtOcorrenciasActionPerformed(evt);
            }
        });

        jBtEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119171503_32.png"))); // NOI18N
        jBtEmpresa.setToolTipText("Empresa");
        jBtEmpresa.setContentAreaFilled(false);
        jBtEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtEmpresaActionPerformed(evt);
            }
        });

        jBtUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191013_32.png"))); // NOI18N
        jBtUsuarios.setToolTipText("Usuários");
        jBtUsuarios.setContentAreaFilled(false);
        jBtUsuarios.setFocusable(false);
        jBtUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBtUsuarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBtUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtUsuariosActionPerformed(evt);
            }
        });

        jBtSolicitantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119192047_32.png"))); // NOI18N
        jBtSolicitantes.setToolTipText("Solicitantes");
        jBtSolicitantes.setContentAreaFilled(false);
        jBtSolicitantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtSolicitantesActionPerformed(evt);
            }
        });

        jBtSistemas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191527_32.png"))); // NOI18N
        jBtSistemas.setToolTipText("Sistemas");
        jBtSistemas.setContentAreaFilled(false);
        jBtSistemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtSistemasActionPerformed(evt);
            }
        });

        jBtModulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191721_32.png"))); // NOI18N
        jBtModulos.setToolTipText("Módulos");
        jBtModulos.setContentAreaFilled(false);
        jBtModulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtModulosActionPerformed(evt);
            }
        });

        jBtAgendaRecados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119193958_32.png"))); // NOI18N
        jBtAgendaRecados.setToolTipText("Agenda de Recados");
        jBtAgendaRecados.setContentAreaFilled(false);
        jBtAgendaRecados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtAgendaRecadosActionPerformed(evt);
            }
        });

        jBtAgendaCompromisso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119194051_32.png"))); // NOI18N
        jBtAgendaCompromisso.setToolTipText("Agenda de Compromissos");
        jBtAgendaCompromisso.setContentAreaFilled(false);
        jBtAgendaCompromisso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtAgendaCompromissoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jBtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtOcorrencias, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtSolicitantes, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtSistemas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtModulos, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtAgendaRecados, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtAgendaCompromisso, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtAgendaRecados, jBtEmpresa, jBtModulos, jBtOcorrencias, jBtSistemas, jBtSolicitantes, jBtUsuarios});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtOcorrencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtSolicitantes)
                    .addComponent(jBtSistemas)
                    .addComponent(jBtModulos, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtAgendaRecados)
                    .addComponent(jBtAgendaCompromisso))
                .addGap(55, 55, 55))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtAgendaCompromisso, jBtAgendaRecados, jBtEmpresa, jBtModulos, jBtOcorrencias, jBtSistemas, jBtSolicitantes, jBtUsuarios});

        jToolBar1.setRollover(true);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Usuário:");
        jToolBar1.add(jLabel3);

        jToolBar2.setRollover(true);

        jLoginConectado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLoginConectado.setText("jLabel6");
        jToolBar2.add(jLoginConectado);

        jToolBar4.setRollover(true);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Data:  ");
        jToolBar4.add(jLabel5);

        jToolBar3.setRollover(true);

        jDataSistema.setEditable(false);
        jDataSistema.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jToolBar3.add(jDataSistema);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204))));

        jBtTrocarSenha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtTrocarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Button_Refresh_Icon_32.png"))); // NOI18N
        jBtTrocarSenha.setToolTipText("Trocar Senha");
        jBtTrocarSenha.setContentAreaFilled(false);
        jBtTrocarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtTrocarSenhaActionPerformed(evt);
            }
        });

        jBtLogoff.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtLogoff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/refresh-reload-icone-6258-32.png"))); // NOI18N
        jBtLogoff.setToolTipText("Fazer logoff");
        jBtLogoff.setContentAreaFilled(false);
        jBtLogoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtLogoffActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/shutdown-icone-6920-32.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtTrocarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtLogoff, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jBtTrocarSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtLogoff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204))));
        jPanel4.setToolTipText("Suporte Técnico");

        jBtChamadosSuporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/HelpDeskFem_32.png"))); // NOI18N
        jBtChamadosSuporte.setToolTipText("Chamados Suporte");
        jBtChamadosSuporte.setContentAreaFilled(false);
        jBtChamadosSuporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtChamadosSuporteActionPerformed(evt);
            }
        });

        jBtRelatoriosSuporteTecnico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119173544_32.png"))); // NOI18N
        jBtRelatoriosSuporteTecnico.setContentAreaFilled(false);
        jBtRelatoriosSuporteTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtRelatoriosSuporteTecnicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jBtRelatoriosSuporteTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtChamadosSuporte, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtChamadosSuporte, jBtRelatoriosSuporteTecnico});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtChamadosSuporte)
                    .addComponent(jBtRelatoriosSuporteTecnico))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBtChamadosSuporte, jBtRelatoriosSuporteTecnico});

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204))));
        jPanel6.setToolTipText("Desenvolvimento");

        jBtConsultasDB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Database 3 32x32.png"))); // NOI18N
        jBtConsultasDB.setToolTipText("Consultas SQL");
        jBtConsultasDB.setContentAreaFilled(false);
        jBtConsultasDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtConsultasDBActionPerformed(evt);
            }
        });

        jBtRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119193229_32.png"))); // NOI18N
        jBtRelatorios.setToolTipText("Relatórios");
        jBtRelatorios.setContentAreaFilled(false);
        jBtRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtRelatoriosActionPerformed(evt);
            }
        });

        jBtChamadosDesenvol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/HelpDeskMas_32.png"))); // NOI18N
        jBtChamadosDesenvol.setToolTipText("Chamados Desnvolvimento");
        jBtChamadosDesenvol.setContentAreaFilled(false);
        jBtChamadosDesenvol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtChamadosDesenvolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtConsultasDB, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtChamadosDesenvol, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jBtConsultasDB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jBtRelatorios)
            .addComponent(jBtChamadosDesenvol)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("HelpDesk");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Hora:");

        jHoraSistema.setEditable(false);
        jHoraSistema.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Sistema de Controle de Chamados (Help Desk) - Versão 1.0");

        jCadastro.setMnemonic('C');
        jCadastro.setText("Cadastros");
        jCadastro.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jCadastroMenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });

        jEmpresa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119190812_16.png"))); // NOI18N
        jEmpresa.setMnemonic('E');
        jEmpresa.setText("Empresa/Unidade Prisional");
        jEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEmpresaActionPerformed(evt);
            }
        });
        jCadastro.add(jEmpresa);

        jOcorrencias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jOcorrencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/250119203457_16.png"))); // NOI18N
        jOcorrencias.setMnemonic('O');
        jOcorrencias.setText("Ocorrências");
        jOcorrencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOcorrenciasActionPerformed(evt);
            }
        });
        jCadastro.add(jOcorrencias);

        jSolicitantes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jSolicitantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119192043_16.png"))); // NOI18N
        jSolicitantes.setMnemonic('L');
        jSolicitantes.setText("Solicitantes");
        jSolicitantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSolicitantesActionPerformed(evt);
            }
        });
        jCadastro.add(jSolicitantes);

        jatendentes.setText("Atendentes de Suporte");
        jatendentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jatendentesActionPerformed(evt);
            }
        });
        jCadastro.add(jatendentes);
        jCadastro.add(jSeparator2);

        jUsuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191009_16.png"))); // NOI18N
        jUsuarios.setMnemonic('U');
        jUsuarios.setText("Usuários");
        jUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUsuariosActionPerformed(evt);
            }
        });
        jCadastro.add(jUsuarios);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191524_16.png"))); // NOI18N
        jMenu4.setText("Sistemas e Módulos");

        jSoftware.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jSoftware.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191524_16.png"))); // NOI18N
        jSoftware.setMnemonic('F');
        jSoftware.setText("Sistema (Software)");
        jSoftware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSoftwareActionPerformed(evt);
            }
        });
        jMenu4.add(jSoftware);

        jModulosSistema.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jModulosSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191718_16.png"))); // NOI18N
        jModulosSistema.setMnemonic('M');
        jModulosSistema.setText("Módulos do Sistema");
        jModulosSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jModulosSistemaActionPerformed(evt);
            }
        });
        jMenu4.add(jModulosSistema);

        jCadastro.add(jMenu4);
        jCadastro.add(jSeparator1);

        jAgendaRecados.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jAgendaRecados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119190439_16.png"))); // NOI18N
        jAgendaRecados.setMnemonic('A');
        jAgendaRecados.setText("Agenda de Recados");
        jAgendaRecados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAgendaRecadosActionPerformed(evt);
            }
        });
        jCadastro.add(jAgendaRecados);

        jAgendaCompromisso.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jAgendaCompromisso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119190604_16.png"))); // NOI18N
        jAgendaCompromisso.setMnemonic('G');
        jAgendaCompromisso.setText("Agenda de Compromissos");
        jAgendaCompromisso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAgendaCompromissoActionPerformed(evt);
            }
        });
        jCadastro.add(jAgendaCompromisso);
        jCadastro.add(jSeparator3);

        jSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/shutdown-icone-6920-16.png"))); // NOI18N
        jSair.setText("Sair");
        jSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSairActionPerformed(evt);
            }
        });
        jCadastro.add(jSair);

        jMenuBar1.add(jCadastro);

        jSuporteTecnico.setMnemonic('S');
        jSuporteTecnico.setText("Suporte Técnico");
        jSuporteTecnico.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jSuporteTecnicoMenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });

        jChamadosSuporte.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jChamadosSuporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/HelpDeskFem_16.png"))); // NOI18N
        jChamadosSuporte.setMnemonic('H');
        jChamadosSuporte.setText("Chamados");
        jChamadosSuporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChamadosSuporteActionPerformed(evt);
            }
        });
        jSuporteTecnico.add(jChamadosSuporte);

        jMenuBar1.add(jSuporteTecnico);

        jDesenvolvimento.setMnemonic('D');
        jDesenvolvimento.setText("Desenvolvimento");
        jDesenvolvimento.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jDesenvolvimentoMenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });

        jConsultasSQL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jConsultasSQL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Database 3 16x16.png"))); // NOI18N
        jConsultasSQL.setMnemonic('Q');
        jConsultasSQL.setText("Consultas SQL");
        jConsultasSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsultasSQLActionPerformed(evt);
            }
        });
        jDesenvolvimento.add(jConsultasSQL);

        jChamadosDesenvolvimento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jChamadosDesenvolvimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/HelpDeskMas_16.png"))); // NOI18N
        jChamadosDesenvolvimento.setMnemonic('D');
        jChamadosDesenvolvimento.setText("Chamados");
        jChamadosDesenvolvimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChamadosDesenvolvimentoActionPerformed(evt);
            }
        });
        jDesenvolvimento.add(jChamadosDesenvolvimento);

        jMenuBar1.add(jDesenvolvimento);

        jRelatorios.setMnemonic('R');
        jRelatorios.setText("Relatórios");

        listagemChamadosSuporteTecnico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/gtklp-icone-3770-16.png"))); // NOI18N
        listagemChamadosSuporteTecnico.setText("Chamados Suporte Técnico");
        listagemChamadosSuporteTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listagemChamadosSuporteTecnicoActionPerformed(evt);
            }
        });
        jRelatorios.add(listagemChamadosSuporteTecnico);

        listagemChamadosDesenvolvimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119193225_16.png"))); // NOI18N
        listagemChamadosDesenvolvimento.setText("Chamados Desenvolvimento");
        listagemChamadosDesenvolvimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listagemChamadosDesenvolvimentoActionPerformed(evt);
            }
        });
        jRelatorios.add(listagemChamadosDesenvolvimento);

        jMenuBar1.add(jRelatorios);

        jSobre.setMnemonic('O');
        jSobre.setText("Sobre");
        jSobre.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jSobreMenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });

        jInformacoes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jInformacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Ajuda_8446_16x16.png"))); // NOI18N
        jInformacoes.setText("Informações");
        jInformacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInformacoesActionPerformed(evt);
            }
        });
        jSobre.add(jInformacoes);

        jMenuBar1.add(jSobre);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPainelPrincipal))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addComponent(jHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPainelPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtTrocarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtTrocarSenhaActionPerformed
        // TODO add your handling code here:
        mostrarTelaTrocaSenha();
    }//GEN-LAST:event_jBtTrocarSenhaActionPerformed

    private void jBtLogoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtLogoffActionPerformed
        // Sair e voltar para troca de usuário
        dispose();
        LoginHD tls = new LoginHD(this, true);
        tls.setVisible(true);
    }//GEN-LAST:event_jBtLogoffActionPerformed

    private void jEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEmpresaActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaCadastroEmpresa);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroEmpresa) && codAbrir == 1) {
            if (objEmp == null || objEmp.isClosed()) {
                objEmp = new TelaEmpresa();
                TelaPrincipal.jPainelPrincipal.add(objEmp);
                objEmp.setVisible(true);
            } else {
                if (objEmp.isVisible()) {
                    if (objEmp.isIcon()) { // Se esta minimizado
                        try {
                            objEmp.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objEmp.toFront(); // traz para frente
                        objEmp.pack();//volta frame 
                    }
                } else {
                    objEmp = new TelaEmpresa();
                    TelaPrincipal.jPainelPrincipal.add(objEmp);//adicona frame ao JDesktopPane  
                    objEmp.setVisible(true);
                }
            }
            try {
                objEmp.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jEmpresaActionPerformed

    private void jOcorrenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOcorrenciasActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaOcorrenciaManu);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaOcorrenciaManu) && codAbrir == 1) {
            if (objOcr == null || objOcr.isClosed()) {
                objOcr = new TelaOcorrenciasHD();
                TelaPrincipal.jPainelPrincipal.add(objOcr);
                objOcr.setVisible(true);
            } else {
                if (objOcr.isVisible()) {
                    if (objOcr.isIcon()) { // Se esta minimizado
                        try {
                            objOcr.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objOcr.toFront(); // traz para frente
                        objOcr.pack();//volta frame 
                    }
                } else {
                    objOcr = new TelaOcorrenciasHD();
                    TelaPrincipal.jPainelPrincipal.add(objOcr);//adicona frame ao JDesktopPane  
                    objOcr.setVisible(true);
                }
            }
            try {
                objOcr.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jOcorrenciasActionPerformed

    private void jSolicitantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSolicitantesActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaCadastroSolicitantes);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroSolicitantes) && codAbrir == 1) {
            if (objSoli == null || objSoli.isClosed()) {
                objSoli = new TelaSolicitantes();
                TelaPrincipal.jPainelPrincipal.add(objSoli);
                objSoli.setVisible(true);
            } else {
                if (objSoli.isVisible()) {
                    if (objSoli.isIcon()) { // Se esta minimizado
                        try {
                            objSoli.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objSoli.toFront(); // traz para frente
                        objSoli.pack();//volta frame 
                    }
                } else {
                    objSoli = new TelaSolicitantes();
                    TelaPrincipal.jPainelPrincipal.add(objSoli);//adicona frame ao JDesktopPane  
                    objSoli.setVisible(true);
                }
            }
            try {
                objSoli.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jSolicitantesActionPerformed

    private void jUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUsuariosActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaCadastroUsuarios);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroUsuarios) && codAbrir == 1) {
            if (objUser == null || objUser.isClosed()) {
                objUser = new TelaUsuarios();
                TelaPrincipal.jPainelPrincipal.add(objUser);
                objUser.setVisible(true);
            } else {
                if (objUser.isVisible()) {
                    if (objUser.isIcon()) { // Se esta minimizado
                        try {
                            objUser.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objUser.toFront(); // traz para frente
                        objUser.pack();//volta frame 
                    }
                } else {
                    objUser = new TelaUsuarios();
                    TelaPrincipal.jPainelPrincipal.add(objUser);//adicona frame ao JDesktopPane  
                    objUser.setVisible(true);
                }
            }
            try {
                objUser.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jUsuariosActionPerformed

    private void jSoftwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSoftwareActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaCadastroSistemas);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroSistemas) && codAbrir == 1) {
            if (objSoftware == null || objSoftware.isClosed()) {
                objSoftware = new TelaSoftware();
                TelaPrincipal.jPainelPrincipal.add(objSoftware);
                objSoftware.setVisible(true);
            } else {
                if (objSoftware.isVisible()) {
                    if (objSoftware.isIcon()) { // Se esta minimizado
                        try {
                            objSoftware.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objSoftware.toFront(); // traz para frente
                        objSoftware.pack();//volta frame 
                    }
                } else {
                    objSoftware = new TelaSoftware();
                    TelaPrincipal.jPainelPrincipal.add(objSoftware);//adicona frame ao JDesktopPane  
                    objSoftware.setVisible(true);
                }
            }
            try {
                objSoftware.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jSoftwareActionPerformed

    private void jModulosSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jModulosSistemaActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaCadastroModulos);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroModulos) && codAbrir == 1) {
            if (objModulo == null || objModulo.isClosed()) {
                objModulo = new TelaModuloSistema();
                TelaPrincipal.jPainelPrincipal.add(objModulo);
                objModulo.setVisible(true);
            } else {
                if (objModulo.isVisible()) {
                    if (objModulo.isIcon()) { // Se esta minimizado
                        try {
                            objModulo.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objModulo.toFront(); // traz para frente
                        objModulo.pack();//volta frame 
                    }
                } else {
                    objModulo = new TelaModuloSistema();
                    TelaPrincipal.jPainelPrincipal.add(objModulo);//adicona frame ao JDesktopPane  
                    objModulo.setVisible(true);
                }
            }
            try {
                objModulo.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jModulosSistemaActionPerformed

    private void jAgendaRecadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAgendaRecadosActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaAgendaRecado);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaAgendaRecado) && codAbrir == 1) {
            if (objjAgendaRec == null || objjAgendaRec.isClosed()) {
                objjAgendaRec = new TelaRecados();
                jPainelPrincipal.add(objjAgendaRec);
                objjAgendaRec.setVisible(true);
            } else {
                if (objjAgendaRec.isVisible()) {
                    if (objjAgendaRec.isIcon()) { // Se esta minimizado
                        try {
                            objjAgendaRec.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objjAgendaRec.toFront(); // traz para frente
                        objjAgendaRec.pack();//volta frame 
                    }
                } else {
                    objjAgendaRec = new TelaRecados();
                    TelaPrincipal.jPainelPrincipal.add(objjAgendaRec);//adicona frame ao JDesktopPane  
                    objjAgendaRec.setVisible(true);
                }
            }
            try {
                objjAgendaRec.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jAgendaRecadosActionPerformed

    private void jAgendaCompromissoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAgendaCompromissoActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaAgendaCompromisso);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaAgendaCompromisso) && codAbrir == 1) {
            if (objAgendaComp == null || objAgendaComp.isClosed()) {
                objAgendaComp = new TelaAgendaCompromissos();
                TelaPrincipal.jPainelPrincipal.add(objAgendaComp);
                objAgendaComp.setVisible(true);
            } else {
                if (objAgendaComp.isVisible()) {
                    if (objAgendaComp.isIcon()) { // Se esta minimizado
                        try {
                            objAgendaComp.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objAgendaComp.toFront(); // traz para frente
                        objAgendaComp.pack();//volta frame 
                    }
                } else {
                    objAgendaComp = new TelaAgendaCompromissos();
                    TelaPrincipal.jPainelPrincipal.add(objAgendaComp);//adicona frame ao JDesktopPane  
                    objAgendaComp.setVisible(true);
                }
            }
            try {
                objAgendaComp.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jAgendaCompromissoActionPerformed

    private void jSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSairActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jSairActionPerformed

    private void jChamadosSuporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChamadosSuporteActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaChamadosSuporte);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaChamadosSuporte) && codAbrir == 1) {
            if (objChamaSup == null || objChamaSup.isClosed()) {
                objChamaSup = new TelaChamadoSuporte();
                TelaPrincipal.jPainelPrincipal.add(objChamaSup);
                objChamaSup.setVisible(true);
            } else {
                if (objChamaSup.isVisible()) {
                    if (objChamaSup.isIcon()) { // Se esta minimizado
                        try {
                            objChamaSup.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objChamaSup.toFront(); // traz para frente
                        objChamaSup.pack();//volta frame 
                    }
                } else {
                    objChamaSup = new TelaChamadoSuporte();
                    TelaPrincipal.jPainelPrincipal.add(objChamaSup);//adicona frame ao JDesktopPane  
                    objChamaSup.setVisible(true);
                }
            }
            try {
                objChamaSup.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jChamadosSuporteActionPerformed

    private void jConsultasSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsultasSQLActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaConsultasSql);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaConsultasSql) && codAbrir == 1) {
            TableExample objSQL = new TableExample();
            objSQL.createConnectionDialog();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jConsultasSQLActionPerformed

    private void jChamadosDesenvolvimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChamadosDesenvolvimentoActionPerformed
        // TODO add your handling code here:TelaChamadoDesenvolvimento
        buscarAcessoUsuario(telaChamadosDesenvolvimento);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaChamadosDesenvolvimento) && codAbrir == 1) {
            if (objChamaSupDesn == null || objChamaSupDesn.isClosed()) {
                objChamaSupDesn = new TelaChamadoDesenvolvimento();
                TelaPrincipal.jPainelPrincipal.add(objChamaSupDesn);
                objChamaSupDesn.setVisible(true);
            } else {
                if (objChamaSup.isVisible()) {
                    if (objChamaSupDesn.isIcon()) { // Se esta minimizado
                        try {
                            objChamaSupDesn.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objChamaSupDesn.toFront(); // traz para frente
                        objChamaSupDesn.pack();//volta frame 
                    }
                } else {
                    objChamaSupDesn = new TelaChamadoDesenvolvimento();
                    TelaPrincipal.jPainelPrincipal.add(objChamaSupDesn);//adicona frame ao JDesktopPane  
                    objChamaSupDesn.setVisible(true);
                }
            }
            try {
                objChamaSupDesn.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jChamadosDesenvolvimentoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jBtEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtEmpresaActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaCadastroEmpresa);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroEmpresa) && codAbrir == 1) {
            if (objEmp == null || objEmp.isClosed()) {
                objEmp = new TelaEmpresa();
                TelaPrincipal.jPainelPrincipal.add(objEmp);
                objEmp.setVisible(true);
            } else {
                if (objEmp.isVisible()) {
                    if (objEmp.isIcon()) { // Se esta minimizado
                        try {
                            objEmp.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objEmp.toFront(); // traz para frente
                        objEmp.pack();//volta frame 
                    }
                } else {
                    objEmp = new TelaEmpresa();
                    TelaPrincipal.jPainelPrincipal.add(objEmp);//adicona frame ao JDesktopPane  
                    objEmp.setVisible(true);
                }
            }
            try {
                objEmp.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtEmpresaActionPerformed

    private void jBtOcorrenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtOcorrenciasActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaOcorrenciaManu);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaOcorrenciaManu) && codAbrir == 1) {
            if (objOcr == null || objOcr.isClosed()) {
                objOcr = new TelaOcorrenciasHD();
                TelaPrincipal.jPainelPrincipal.add(objOcr);
                objOcr.setVisible(true);
            } else {
                if (objOcr.isVisible()) {
                    if (objOcr.isIcon()) { // Se esta minimizado
                        try {
                            objOcr.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objOcr.toFront(); // traz para frente
                        objOcr.pack();//volta frame 
                    }
                } else {
                    objOcr = new TelaOcorrenciasHD();
                    TelaPrincipal.jPainelPrincipal.add(objOcr);//adicona frame ao JDesktopPane  
                    objOcr.setVisible(true);
                }
            }
            try {
                objOcr.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtOcorrenciasActionPerformed

    private void jBtSolicitantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSolicitantesActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaCadastroSolicitantes);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroSolicitantes) && codAbrir == 1) {
            if (objSoli == null || objSoli.isClosed()) {
                objSoli = new TelaSolicitantes();
                TelaPrincipal.jPainelPrincipal.add(objSoli);
                objSoli.setVisible(true);
            } else {
                if (objSoli.isVisible()) {
                    if (objSoli.isIcon()) { // Se esta minimizado
                        try {
                            objSoli.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objSoli.toFront(); // traz para frente
                        objSoli.pack();//volta frame 
                    }
                } else {
                    objSoli = new TelaSolicitantes();
                    TelaPrincipal.jPainelPrincipal.add(objSoli);//adicona frame ao JDesktopPane  
                    objSoli.setVisible(true);
                }
            }
            try {
                objSoli.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtSolicitantesActionPerformed

    private void jBtUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtUsuariosActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaCadastroUsuarios);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroUsuarios) && codAbrir == 1) {
            if (objUser == null || objUser.isClosed()) {
                objUser = new TelaUsuarios();
                TelaPrincipal.jPainelPrincipal.add(objUser);
                objUser.setVisible(true);
            } else {
                if (objUser.isVisible()) {
                    if (objUser.isIcon()) { // Se esta minimizado
                        try {
                            objUser.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objUser.toFront(); // traz para frente
                        objUser.pack();//volta frame 
                    }
                } else {
                    objUser = new TelaUsuarios();
                    TelaPrincipal.jPainelPrincipal.add(objUser);//adicona frame ao JDesktopPane  
                    objUser.setVisible(true);
                }
            }
            try {
                objUser.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtUsuariosActionPerformed

    private void jBtSistemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtSistemasActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaCadastroSistemas);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroSistemas) && codAbrir == 1) {
            if (objSoftware == null || objSoftware.isClosed()) {
                objSoftware = new TelaSoftware();
                TelaPrincipal.jPainelPrincipal.add(objSoftware);
                objSoftware.setVisible(true);
            } else {
                if (objSoftware.isVisible()) {
                    if (objSoftware.isIcon()) { // Se esta minimizado
                        try {
                            objSoftware.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objSoftware.toFront(); // traz para frente
                        objSoftware.pack();//volta frame 
                    }
                } else {
                    objSoftware = new TelaSoftware();
                    TelaPrincipal.jPainelPrincipal.add(objSoftware);//adicona frame ao JDesktopPane  
                    objSoftware.setVisible(true);
                }
            }
            try {
                objSoftware.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtSistemasActionPerformed

    private void jBtModulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtModulosActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaCadastroModulos);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroModulos) && codAbrir == 1) {
            if (objModulo == null || objModulo.isClosed()) {
                objModulo = new TelaModuloSistema();
                TelaPrincipal.jPainelPrincipal.add(objModulo);
                objModulo.setVisible(true);
            } else {
                if (objModulo.isVisible()) {
                    if (objModulo.isIcon()) { // Se esta minimizado
                        try {
                            objModulo.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objModulo.toFront(); // traz para frente
                        objModulo.pack();//volta frame 
                    }
                } else {
                    objModulo = new TelaModuloSistema();
                    TelaPrincipal.jPainelPrincipal.add(objModulo);//adicona frame ao JDesktopPane  
                    objModulo.setVisible(true);
                }
            }
            try {
                objModulo.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtModulosActionPerformed

    private void jBtAgendaRecadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtAgendaRecadosActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaAgendaRecado);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaAgendaRecado) && codAbrir == 1) {
            if (objjAgendaRec == null || objjAgendaRec.isClosed()) {
                objjAgendaRec = new TelaRecados();
                jPainelPrincipal.add(objjAgendaRec);
                objjAgendaRec.setVisible(true);
            } else {
                if (objjAgendaRec.isVisible()) {
                    if (objjAgendaRec.isIcon()) { // Se esta minimizado
                        try {
                            objjAgendaRec.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objjAgendaRec.toFront(); // traz para frente
                        objjAgendaRec.pack();//volta frame 
                    }
                } else {
                    objjAgendaRec = new TelaRecados();
                    TelaPrincipal.jPainelPrincipal.add(objjAgendaRec);//adicona frame ao JDesktopPane  
                    objjAgendaRec.setVisible(true);
                }
            }
            try {
                objjAgendaRec.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtAgendaRecadosActionPerformed

    private void jBtAgendaCompromissoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtAgendaCompromissoActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaAgendaCompromisso);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaAgendaCompromisso) && codAbrir == 1) {
            if (objAgendaComp == null || objAgendaComp.isClosed()) {
                objAgendaComp = new TelaAgendaCompromissos();
                TelaPrincipal.jPainelPrincipal.add(objAgendaComp);
                objAgendaComp.setVisible(true);
            } else {
                if (objAgendaComp.isVisible()) {
                    if (objAgendaComp.isIcon()) { // Se esta minimizado
                        try {
                            objAgendaComp.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objAgendaComp.toFront(); // traz para frente
                        objAgendaComp.pack();//volta frame 
                    }
                } else {
                    objAgendaComp = new TelaAgendaCompromissos();
                    TelaPrincipal.jPainelPrincipal.add(objAgendaComp);//adicona frame ao JDesktopPane  
                    objAgendaComp.setVisible(true);
                }
            }
            try {
                objAgendaComp.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtAgendaCompromissoActionPerformed

    private void jBtChamadosSuporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtChamadosSuporteActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaChamadosSuporte);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaChamadosSuporte) && codAbrir == 1) {
            if (objChamaSup == null || objChamaSup.isClosed()) {
                objChamaSup = new TelaChamadoSuporte();
                TelaPrincipal.jPainelPrincipal.add(objChamaSup);
                objChamaSup.setVisible(true);
            } else {
                if (objChamaSup.isVisible()) {
                    if (objChamaSup.isIcon()) { // Se esta minimizado
                        try {
                            objChamaSup.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objChamaSup.toFront(); // traz para frente
                        objChamaSup.pack();//volta frame 
                    }
                } else {
                    objChamaSup = new TelaChamadoSuporte();
                    TelaPrincipal.jPainelPrincipal.add(objChamaSup);//adicona frame ao JDesktopPane  
                    objChamaSup.setVisible(true);
                }
            }
            try {
                objChamaSup.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtChamadosSuporteActionPerformed

    private void jBtConsultasDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtConsultasDBActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaConsultasSql);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaConsultasSql) && codAbrir == 1) {
            TableExample objSQL = new TableExample();
            objSQL.createConnectionDialog();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtConsultasDBActionPerformed

    private void jBtChamadosDesenvolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtChamadosDesenvolActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaChamadosDesenvolvimento);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaChamadosDesenvolvimento) && codAbrir == 1) {
            if (objChamaSupDesn == null || objChamaSupDesn.isClosed()) {
                objChamaSupDesn = new TelaChamadoDesenvolvimento();
                TelaPrincipal.jPainelPrincipal.add(objChamaSupDesn);
                objChamaSupDesn.setVisible(true);
            } else {
                if (objChamaSup.isVisible()) {
                    if (objChamaSupDesn.isIcon()) { // Se esta minimizado
                        try {
                            objChamaSupDesn.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objChamaSupDesn.toFront(); // traz para frente
                        objChamaSupDesn.pack();//volta frame 
                    }
                } else {
                    objChamaSupDesn = new TelaChamadoDesenvolvimento();
                    TelaPrincipal.jPainelPrincipal.add(objChamaSupDesn);//adicona frame ao JDesktopPane  
                    objChamaSupDesn.setVisible(true);
                }
            }
            try {
                objChamaSupDesn.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jBtChamadosDesenvolActionPerformed

    private void jBtRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtRelatoriosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtRelatoriosActionPerformed

    private void listagemChamadosSuporteTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listagemChamadosSuporteTecnicoActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(rootPane, "Em desenvolvimento...");
    }//GEN-LAST:event_listagemChamadosSuporteTecnicoActionPerformed

    private void listagemChamadosDesenvolvimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listagemChamadosDesenvolvimentoActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(rootPane, "Em desenvolvimento...");
    }//GEN-LAST:event_listagemChamadosDesenvolvimentoActionPerformed

    private void jBtRelatoriosSuporteTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtRelatoriosSuporteTecnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtRelatoriosSuporteTecnicoActionPerformed

    private void jCadastroMenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jCadastroMenuKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_E:
                buscarAcessoUsuario(telaCadastroEmpresa);
                if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroEmpresa) && codAbrir == 1) {
                    if (objEmp == null || objEmp.isClosed()) {
                        objEmp = new TelaEmpresa();
                        TelaPrincipal.jPainelPrincipal.add(objEmp);
                        objEmp.setVisible(true);
                    } else {
                        if (objEmp.isVisible()) {
                            if (objEmp.isIcon()) { // Se esta minimizado
                                try {
                                    objEmp.setIcon(false); // maximiniza
                                } catch (PropertyVetoException ex) {
                                }
                            } else {
                                objEmp.toFront(); // traz para frente
                                objEmp.pack();//volta frame 
                            }
                        } else {
                            objEmp = new TelaEmpresa();
                            TelaPrincipal.jPainelPrincipal.add(objEmp);//adicona frame ao JDesktopPane  
                            objEmp.setVisible(true);
                        }
                    }
                    try {
                        objEmp.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
                }
                break;
            case KeyEvent.VK_O:
                buscarAcessoUsuario(telaOcorrenciaManu);
                if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaOcorrenciaManu) && codAbrir == 1) {
                    if (objOcr == null || objOcr.isClosed()) {
                        objOcr = new TelaOcorrenciasHD();
                        TelaPrincipal.jPainelPrincipal.add(objOcr);
                        objOcr.setVisible(true);
                    } else {
                        if (objOcr.isVisible()) {
                            if (objOcr.isIcon()) { // Se esta minimizado
                                try {
                                    objOcr.setIcon(false); // maximiniza
                                } catch (PropertyVetoException ex) {
                                }
                            } else {
                                objOcr.toFront(); // traz para frente
                                objOcr.pack();//volta frame 
                            }
                        } else {
                            objOcr = new TelaOcorrenciasHD();
                            TelaPrincipal.jPainelPrincipal.add(objOcr);//adicona frame ao JDesktopPane  
                            objOcr.setVisible(true);
                        }
                    }
                    try {
                        objOcr.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
                }
                break;
            case KeyEvent.VK_S:
                buscarAcessoUsuario(telaCadastroSolicitantes);
                if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroSolicitantes) && codAbrir == 1) {
                    if (objSoli == null || objSoli.isClosed()) {
                        objSoli = new TelaSolicitantes();
                        TelaPrincipal.jPainelPrincipal.add(objSoli);
                        objSoli.setVisible(true);
                    } else {
                        if (objSoli.isVisible()) {
                            if (objSoli.isIcon()) { // Se esta minimizado
                                try {
                                    objSoli.setIcon(false); // maximiniza
                                } catch (PropertyVetoException ex) {
                                }
                            } else {
                                objSoli.toFront(); // traz para frente
                                objSoli.pack();//volta frame 
                            }
                        } else {
                            objSoli = new TelaSolicitantes();
                            TelaPrincipal.jPainelPrincipal.add(objSoli);//adicona frame ao JDesktopPane  
                            objSoli.setVisible(true);
                        }
                    }
                    try {
                        objSoli.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
                }
                break;
            case KeyEvent.VK_U:
                buscarAcessoUsuario(telaCadastroUsuarios);
                if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroUsuarios) && codAbrir == 1) {
                    if (objUser == null || objUser.isClosed()) {
                        objUser = new TelaUsuarios();
                        TelaPrincipal.jPainelPrincipal.add(objUser);
                        objUser.setVisible(true);
                    } else {
                        if (objUser.isVisible()) {
                            if (objUser.isIcon()) { // Se esta minimizado
                                try {
                                    objUser.setIcon(false); // maximiniza
                                } catch (PropertyVetoException ex) {
                                }
                            } else {
                                objUser.toFront(); // traz para frente
                                objUser.pack();//volta frame 
                            }
                        } else {
                            objUser = new TelaUsuarios();
                            TelaPrincipal.jPainelPrincipal.add(objUser);//adicona frame ao JDesktopPane  
                            objUser.setVisible(true);
                        }
                    }
                    try {
                        objUser.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
                }
                break;
            case KeyEvent.VK_F:
                buscarAcessoUsuario(telaCadastroSistemas);
                if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroSistemas) && codAbrir == 1) {
                    if (objSoftware == null || objSoftware.isClosed()) {
                        objSoftware = new TelaSoftware();
                        TelaPrincipal.jPainelPrincipal.add(objSoftware);
                        objSoftware.setVisible(true);
                    } else {
                        if (objSoftware.isVisible()) {
                            if (objSoftware.isIcon()) { // Se esta minimizado
                                try {
                                    objSoftware.setIcon(false); // maximiniza
                                } catch (PropertyVetoException ex) {
                                }
                            } else {
                                objSoftware.toFront(); // traz para frente
                                objSoftware.pack();//volta frame 
                            }
                        } else {
                            objSoftware = new TelaSoftware();
                            TelaPrincipal.jPainelPrincipal.add(objSoftware);//adicona frame ao JDesktopPane  
                            objSoftware.setVisible(true);
                        }
                    }
                    try {
                        objSoftware.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
                }
                break;
            case KeyEvent.VK_M:
                buscarAcessoUsuario(telaCadastroModulos);
                if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroModulos) && codAbrir == 1) {
                    if (objModulo == null || objModulo.isClosed()) {
                        objModulo = new TelaModuloSistema();
                        TelaPrincipal.jPainelPrincipal.add(objModulo);
                        objModulo.setVisible(true);
                    } else {
                        if (objModulo.isVisible()) {
                            if (objModulo.isIcon()) { // Se esta minimizado
                                try {
                                    objModulo.setIcon(false); // maximiniza
                                } catch (PropertyVetoException ex) {
                                }
                            } else {
                                objModulo.toFront(); // traz para frente
                                objModulo.pack();//volta frame 
                            }
                        } else {
                            objModulo = new TelaModuloSistema();
                            TelaPrincipal.jPainelPrincipal.add(objModulo);//adicona frame ao JDesktopPane  
                            objModulo.setVisible(true);
                        }
                    }
                    try {
                        objModulo.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
                }
                break;
            case KeyEvent.VK_A:
                buscarAcessoUsuario(telaAgendaRecado);
                if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaAgendaRecado) && codAbrir == 1) {
                    if (objjAgendaRec == null || objjAgendaRec.isClosed()) {
                        objjAgendaRec = new TelaRecados();
                        jPainelPrincipal.add(objjAgendaRec);
                        objjAgendaRec.setVisible(true);
                    } else {
                        if (objjAgendaRec.isVisible()) {
                            if (objjAgendaRec.isIcon()) { // Se esta minimizado
                                try {
                                    objjAgendaRec.setIcon(false); // maximiniza
                                } catch (PropertyVetoException ex) {
                                }
                            } else {
                                objjAgendaRec.toFront(); // traz para frente
                                objjAgendaRec.pack();//volta frame 
                            }
                        } else {
                            objjAgendaRec = new TelaRecados();
                            TelaPrincipal.jPainelPrincipal.add(objjAgendaRec);//adicona frame ao JDesktopPane  
                            objjAgendaRec.setVisible(true);
                        }
                    }
                    try {
                        objjAgendaRec.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
                }
                break;
            case KeyEvent.VK_G:
                buscarAcessoUsuario(telaAgendaCompromisso);
                if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaAgendaCompromisso) && codAbrir == 1) {
                    if (objAgendaComp == null || objAgendaComp.isClosed()) {
                        objAgendaComp = new TelaAgendaCompromissos();
                        TelaPrincipal.jPainelPrincipal.add(objAgendaComp);
                        objAgendaComp.setVisible(true);
                    } else {
                        if (objAgendaComp.isVisible()) {
                            if (objAgendaComp.isIcon()) { // Se esta minimizado
                                try {
                                    objAgendaComp.setIcon(false); // maximiniza
                                } catch (PropertyVetoException ex) {
                                }
                            } else {
                                objAgendaComp.toFront(); // traz para frente
                                objAgendaComp.pack();//volta frame 
                            }
                        } else {
                            objAgendaComp = new TelaAgendaCompromissos();
                            TelaPrincipal.jPainelPrincipal.add(objAgendaComp);//adicona frame ao JDesktopPane  
                            objAgendaComp.setVisible(true);
                        }
                    }
                    try {
                        objAgendaComp.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
                }
                break;
            case KeyEvent.VK_X:
                System.exit(0);
        }
    }//GEN-LAST:event_jCadastroMenuKeyPressed

    private void jSuporteTecnicoMenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jSuporteTecnicoMenuKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_C:
                buscarAcessoUsuario(telaChamadosSuporte);
                if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaChamadosSuporte) && codAbrir == 1) {
                    if (objChamaSup == null || objChamaSup.isClosed()) {
                        objChamaSup = new TelaChamadoSuporte();
                        TelaPrincipal.jPainelPrincipal.add(objChamaSup);
                        objChamaSup.setVisible(true);
                    } else {
                        if (objChamaSup.isVisible()) {
                            if (objChamaSup.isIcon()) { // Se esta minimizado
                                try {
                                    objChamaSup.setIcon(false); // maximiniza
                                } catch (PropertyVetoException ex) {
                                }
                            } else {
                                objChamaSup.toFront(); // traz para frente
                                objChamaSup.pack();//volta frame 
                            }
                        } else {
                            objChamaSup = new TelaChamadoSuporte();
                            TelaPrincipal.jPainelPrincipal.add(objChamaSup);//adicona frame ao JDesktopPane  
                            objChamaSup.setVisible(true);
                        }
                    }
                    try {
                        objChamaSup.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
                }
                break;
        }
    }//GEN-LAST:event_jSuporteTecnicoMenuKeyPressed

    private void jDesenvolvimentoMenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jDesenvolvimentoMenuKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_Q:
                buscarAcessoUsuario(telaConsultasSql);
                if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaConsultasSql) && codAbrir == 1) {
                    TableExample objSQL = new TableExample();
                    objSQL.createConnectionDialog();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
                }
                break;
            case KeyEvent.VK_D:
                buscarAcessoUsuario(telaChamadosDesenvolvimento);
                if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaChamadosDesenvolvimento) && codAbrir == 1) {
                    if (objChamaSupDesn == null || objChamaSupDesn.isClosed()) {
                        objChamaSupDesn = new TelaChamadoDesenvolvimento();
                        TelaPrincipal.jPainelPrincipal.add(objChamaSupDesn);
                        objChamaSupDesn.setVisible(true);
                    } else {
                        if (objChamaSup.isVisible()) {
                            if (objChamaSupDesn.isIcon()) { // Se esta minimizado
                                try {
                                    objChamaSupDesn.setIcon(false); // maximiniza
                                } catch (PropertyVetoException ex) {
                                }
                            } else {
                                objChamaSupDesn.toFront(); // traz para frente
                                objChamaSupDesn.pack();//volta frame 
                            }
                        } else {
                            objChamaSupDesn = new TelaChamadoDesenvolvimento();
                            TelaPrincipal.jPainelPrincipal.add(objChamaSupDesn);//adicona frame ao JDesktopPane  
                            objChamaSupDesn.setVisible(true);
                        }
                    }
                    try {
                        objChamaSupDesn.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
                }
                break;
        }
    }//GEN-LAST:event_jDesenvolvimentoMenuKeyPressed

    private void jInformacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInformacoesActionPerformed
        // TODO add your handling code here:
        TelaSobre objSobre = new TelaSobre();
        jPainelPrincipal.add(objSobre);
        objSobre.show();
    }//GEN-LAST:event_jInformacoesActionPerformed

    private void jSobreMenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jSobreMenuKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_F1:
                TelaSobre objSobre = new TelaSobre();
                jPainelPrincipal.add(objSobre);
                objSobre.show();
                break;
        }
    }//GEN-LAST:event_jSobreMenuKeyPressed

    private void jatendentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jatendentesActionPerformed
        // TODO add your handling code here:
        buscarAcessoUsuario(telaCadastroAtendentes);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaCadastroAtendentes) && codAbrir == 1) {
            if (objAtendente == null || objAtendente.isClosed()) {
                objAtendente = new TelaAtendentesSuporte();
                TelaPrincipal.jPainelPrincipal.add(objAtendente);
                objAtendente.setVisible(true);
            } else {
                if (objAtendente.isVisible()) {
                    if (objAtendente.isIcon()) { // Se esta minimizado
                        try {
                            objAtendente.setIcon(false); // maximiniza
                        } catch (PropertyVetoException ex) {
                        }
                    } else {
                        objAtendente.toFront(); // traz para frente
                        objAtendente.pack();//volta frame 
                    }
                } else {
                    objAtendente = new TelaAtendentesSuporte();
                    TelaPrincipal.jPainelPrincipal.add(objAtendente);//adicona frame ao JDesktopPane  
                    objAtendente.setVisible(true);
                }
            }
            try {
                objAtendente.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Usuário não tem acesso ao registro.");
        }
    }//GEN-LAST:event_jatendentesActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jAgendaCompromisso;
    private javax.swing.JMenuItem jAgendaRecados;
    private javax.swing.JButton jBtAgendaCompromisso;
    private javax.swing.JButton jBtAgendaRecados;
    private javax.swing.JButton jBtChamadosDesenvol;
    private javax.swing.JButton jBtChamadosSuporte;
    private javax.swing.JButton jBtConsultasDB;
    private javax.swing.JButton jBtEmpresa;
    private javax.swing.JButton jBtLogoff;
    private javax.swing.JButton jBtModulos;
    private javax.swing.JButton jBtOcorrencias;
    private javax.swing.JButton jBtRelatorios;
    private javax.swing.JButton jBtRelatoriosSuporteTecnico;
    private javax.swing.JButton jBtSistemas;
    private javax.swing.JButton jBtSolicitantes;
    private javax.swing.JButton jBtTrocarSenha;
    private javax.swing.JButton jBtUsuarios;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenu jCadastro;
    private javax.swing.JMenuItem jChamadosDesenvolvimento;
    private javax.swing.JMenuItem jChamadosSuporte;
    private javax.swing.JMenuItem jConsultasSQL;
    public static javax.swing.JTextField jDataSistema;
    private javax.swing.JMenu jDesenvolvimento;
    private javax.swing.JMenuItem jEmpresa;
    public static javax.swing.JTextField jHoraSistema;
    private javax.swing.JMenuItem jInformacoes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLoginConectado;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jModulosSistema;
    private javax.swing.JMenuItem jOcorrencias;
    public static javax.swing.JDesktopPane jPainelPrincipal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JMenu jRelatorios;
    private javax.swing.JMenuItem jSair;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenu jSobre;
    private javax.swing.JMenuItem jSoftware;
    private javax.swing.JMenuItem jSolicitantes;
    private javax.swing.JMenu jSuporteTecnico;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JMenuItem jUsuarios;
    private javax.swing.JMenuItem jatendentes;
    private javax.swing.JMenuItem listagemChamadosDesenvolvimento;
    private javax.swing.JMenuItem listagemChamadosSuporteTecnico;
    // End of variables declaration//GEN-END:variables

    // Verificar a cada 5 minutos se o recado foi lido (10/01/2015)
    public void threadMensagem() {
        final Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                verificarRecado(); // Verificar recados a cada 5 minutos               
                verificarAgendaCompromisso();
            }
        }, periodo, tempo);
    }

    public void rodaRelogio() {
        try {
            while (true) {
                Date data = new Date();
                String hora = formatter.format(data);
                String date = formatter2.format(data);
                jHoraSistema.setText(String.valueOf(hora));    // no lugar do label, por seu JTextField    
                jDataSistema.setText(String.valueOf(date));
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
        }
    }

    // PESQUISA E CADASTRO DAS TELAS DO MÓDULO ENFERMARIA PARA CONTROLE DE ACESSO DE USUÁRIOS.
    public void pesquisarTelasAcessos() {
        //CADASTRO
        //EMPRESAS
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaCadastroEmpresa + "'");
            conecta.rs.first();
            pNomeCE = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        //UNIDADES
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaCadastroUnidades + "'");
            conecta.rs.first();
            pNomeCUN = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        //OCORRÊNCIAS
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaOcorrenciaManu + "'");
            conecta.rs.first();
            pNomeOCR = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        //SOLICITANTES
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaCadastroSolicitantes + "'");
            conecta.rs.first();
            pNomeCS = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        //USUÁRIOS
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaCadastroUsuarios + "'");
            conecta.rs.first();
            pNomeCU = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaCadastroAcessos + "'");
            conecta.rs.first();
            pNomeCAU = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaCadastroCopiar + "'");
            conecta.rs.first();
            pNomeCCAU = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        //SOFTWARE E MÓDULOS  
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaCadastroSistemas + "'");
            conecta.rs.first();
            pNomeSI = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaCadastroModulos + "'");
            conecta.rs.first();
            pNomeCM = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        // AGENDAS
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaAgendaRecado + "'");
            conecta.rs.first();
            pNomeARM = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaAgendaCompromisso + "'");
            conecta.rs.first();
            pNomeACM = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        //CHAMADOS SUPORTE
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaChamadosSuporte + "'");
            conecta.rs.first();
            pNomeCSU = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaItensChamadoSuporte + "'");
            conecta.rs.first();
            pNomeCSI = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + botaoEncerrarSup + "'");
            conecta.rs.first();
            pNomeBTE = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + botaoImprimirSup + "'");
            conecta.rs.first();
            pNomeBTI = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + botaoEnviarSup + "'");
            conecta.rs.first();
            pNomeBTV = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + botaoReabrirSup + "'");
            conecta.rs.first();
            pNomeBTR = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        //CHAMADOS DO DESENVOLVIMENTO
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaChamadosDesenvolvimento + "'");
            conecta.rs.first();
            pNomeCD = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaItensChamadoDesenvolvimento + "'");
            conecta.rs.first();
            pNomeCDI = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }

        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaConsultasSql + "'");
            conecta.rs.first();
            pNomeCSQL = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + botaoEncerrarDes + "'");
            conecta.rs.first();
            pNomeBTED = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + botaoImprimirDes + "'");
            conecta.rs.first();
            pNomeBTID = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + botaoEnviarDes + "'");
            conecta.rs.first();
            pNomeBTVD = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + botaoReabrirDes + "'");
            conecta.rs.first();
            pNomeBTRD = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + botaoBuscarCH + "'");
            conecta.rs.first();
            pNomeBCSD = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        //ATENDENTES
        try {
            conecta.executaSQL("SELECT * FROM TELAS "
                    + "WHERE NomeTela='" + telaCadastroAtendentes + "'");
            conecta.rs.first();
            pNomeAT = conecta.rs.getString("NomeTela");
        } catch (SQLException ex) {
        }
        // CADASTRO
        //EMPRESA/UNIDADES
        if (!pNomeCE.equals(telaCadastroEmpresa) || pNomeCE == null || pNomeCE.equals("")) {
            objCadastroTela.setNomeTela(telaCadastroEmpresa);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        //UNIDADES
        if (!pNomeCUN.equals(telaCadastroUnidades) || pNomeCUN == null || pNomeCUN.equals("")) {
            objCadastroTela.setNomeTela(telaCadastroUnidades);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        //OCORRÊNCIAS
        if (!pNomeOCR.equals(telaOcorrenciaManu) || pNomeOCR == null || pNomeOCR.equals("")) {
            objCadastroTela.setNomeTela(telaOcorrenciaManu);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        //SOLICITANTES
        if (!pNomeCS.equals(telaCadastroSolicitantes) || pNomeCS == null || pNomeCS.equals("")) {
            objCadastroTela.setNomeTela(telaCadastroSolicitantes);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        //USUÁRIOS
        if (!pNomeCU.equals(telaCadastroUsuarios) || pNomeCU == null || pNomeCU.equals("")) {
            objCadastroTela.setNomeTela(telaCadastroUsuarios);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeCAU.equals(telaCadastroAcessos) || pNomeCAU == null || pNomeCAU.equals("")) {
            objCadastroTela.setNomeTela(telaCadastroAcessos);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeCCAU.equals(telaCadastroCopiar) || pNomeCCAU == null || pNomeCCAU.equals("")) {
            objCadastroTela.setNomeTela(telaCadastroCopiar);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        //SOFTWARE E MÓDULOS 
        if (!pNomeSI.equals(telaCadastroSistemas) || pNomeSI == null || pNomeSI.equals("")) {
            objCadastroTela.setNomeTela(telaCadastroSistemas);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeCM.equals(telaCadastroModulos) || pNomeCM == null || pNomeCM.equals("")) {
            objCadastroTela.setNomeTela(telaCadastroModulos);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        //AGENDAS
        if (!pNomeARM.equals(telaAgendaRecado) || pNomeARM == null || pNomeARM.equals("")) {
            objCadastroTela.setNomeTela(telaAgendaRecado);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeACM.equals(telaAgendaCompromisso) || pNomeACM == null || pNomeACM.equals("")) {
            objCadastroTela.setNomeTela(telaAgendaCompromisso);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        //CHAMADOS SUPORTE  
        if (!pNomeCSU.equals(telaChamadosSuporte) || pNomeCSU == null || pNomeCSU.equals("")) {
            objCadastroTela.setNomeTela(telaChamadosSuporte);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeCSI.equals(telaItensChamadoSuporte) || pNomeCSI == null || pNomeCSI.equals("")) {
            objCadastroTela.setNomeTela(telaItensChamadoSuporte);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeBTE.equals(botaoEncerrarSup) || pNomeBTE == null || pNomeBTE.equals("")) {
            objCadastroTela.setNomeTela(botaoEncerrarSup);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeBTI.equals(botaoImprimirSup) || pNomeBTI == null || pNomeBTI.equals("")) {
            objCadastroTela.setNomeTela(botaoImprimirSup);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeBTV.equals(botaoEnviarSup) || pNomeBTV == null || pNomeBTV.equals("")) {
            objCadastroTela.setNomeTela(botaoEnviarSup);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeBTR.equals(botaoReabrirSup) || pNomeBTR == null || pNomeBTR.equals("")) {
            objCadastroTela.setNomeTela(botaoReabrirSup);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        //DESENVOLVIMENTO
        if (!pNomeCD.equals(telaChamadosDesenvolvimento) || pNomeCD == null || pNomeCD.equals("")) {
            objCadastroTela.setNomeTela(telaChamadosDesenvolvimento);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeCDI.equals(telaItensChamadoDesenvolvimento) || pNomeCDI == null || pNomeCDI.equals("")) {
            objCadastroTela.setNomeTela(telaItensChamadoDesenvolvimento);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeCSQL.equals(telaConsultasSql) || pNomeCSQL == null || pNomeCSQL.equals("")) {
            objCadastroTela.setNomeTela(telaConsultasSql);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeBTED.equals(botaoEncerrarDes) || pNomeBTED == null || pNomeBTED.equals("")) {
            objCadastroTela.setNomeTela(botaoEncerrarDes);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeBTID.equals(botaoImprimirDes) || pNomeBTID == null || pNomeBTID.equals("")) {
            objCadastroTela.setNomeTela(botaoImprimirDes);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeBTVD.equals(botaoEnviarDes) || pNomeBTVD == null || pNomeBTVD.equals("")) {
            objCadastroTela.setNomeTela(botaoEnviarDes);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeBTRD.equals(botaoReabrirDes) || pNomeBTRD == null || pNomeBTRD.equals("")) {
            objCadastroTela.setNomeTela(botaoReabrirDes);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeBCSD.equals(botaoBuscarCH) || pNomeBCSD == null || pNomeBCSD.equals("")) {
            objCadastroTela.setNomeTela(botaoBuscarCH);
            controle.incluirTelaAcesso(objCadastroTela);
        }
        if (!pNomeAT.equals(telaCadastroAtendentes) || pNomeAT == null || pNomeAT.equals("")) {
            objCadastroTela.setNomeTela(telaCadastroAtendentes);
            controle.incluirTelaAcesso(objCadastroTela);
        }
    }

    public void buscarAcessoUsuario(String nomeTelaAcesso) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT * FROM USUARIOS "
                    + "WHERE NomeUsuario='" + nameUser + "'");
            conecta.rs.first();
            codigoUser = conecta.rs.getInt("IdUsuario");
        } catch (Exception e) {
        }
        conecta.abrirConexao();
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

    public void verificarRecado() {
        buscarAcessoUsuario(telaAgendaRecado);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaAgendaRecado) && codAbrir == 1) {
            buscarUsuario(nameUser);
            conecta.abrirConexao();
            try {
                conecta.executaSQL("SELECT * FROM AGENDA_RECADOS "
                        + "WHERE IdUsuario='" + codUsuario + "' "
                        + "AND StatusAgenda='" + statusAgenda + "'");
                conecta.rs.first();
                if (codUsuario == conecta.rs.getInt("IdUsuario")) {

                    if (objjAgendaRec == null || objjAgendaRec.isClosed()) {
                        objjAgendaRec = new TelaRecados();
                        jPainelPrincipal.add(objjAgendaRec);
                        objjAgendaRec.setVisible(true);
                    } else {
                        if (objjAgendaRec.isVisible()) {
                            if (objjAgendaRec.isIcon()) { // Se esta minimizado
                                try {
                                    objjAgendaRec.setIcon(false); // maximiniza
                                } catch (PropertyVetoException ex) {
                                }
                            } else {
                                objjAgendaRec.toFront(); // traz para frente
                                objjAgendaRec.pack();//volta frame 
                            }
                        } else {
                            objjAgendaRec = new TelaRecados();
                            TelaPrincipal.jPainelPrincipal.add(objjAgendaRec);//adicona frame ao JDesktopPane  
                            objjAgendaRec.setVisible(true);
                        }
                    }
                    try {
                        objjAgendaRec.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }

                    flag = 1;
                    preencherTabelaTodosRecados("SELECT * FROM AGENDA_RECADOS "
                            + "INNER JOIN USUARIOS "
                            + "ON AGENDA_RECADOS.IdUsuario=USUARIOS.IdUsuario "
                            + "WHERE NomeUsuario='" + nameUser + "' "
                            + "AND StatusAgenda='" + statusAgenda + "'");
                    if (flag == 1) {
                        jBtNovo.setEnabled(true);
                        jBtAlterar.setEnabled(true);
                        jBtExcluir.setEnabled(true);
                        jBtSalvar.setEnabled(!true);
                        jBtCancelar.setEnabled(true);
                        jBtResponder.setEnabled(true);
                        jBtConfirmar.setEnabled(true);
                        conecta.abrirConexao();
                        try {
                            conecta.executaSQL("SELECT * FROM AGENDA_RECADOS "
                                    + "INNER JOIN USUARIOS "
                                    + "ON AGENDA_RECADOS.IdUsuario=USUARIOS.IdUsuario "
                                    + "WHERE NomeUsuario='" + nameUser + "' "
                                    + "AND StatusAgenda='" + statusAgenda + "'");
                            conecta.rs.last();
                            jIDLanc.setText(String.valueOf(conecta.rs.getInt("IdLanc")));
                            jDataLanc.setDate(conecta.rs.getDate("DataLanc"));
                            jHoraRecado.setText(conecta.rs.getString("Horario"));
                            jComboBoxStatus.setSelectedItem(conecta.rs.getString("StatusAgenda"));
                            jNomeRementente.setText(conecta.rs.getString("NomeUsuarioLogado"));
                            jNomeDestinatario.setText(conecta.rs.getString("NomeUsuario"));
                            jRecado.setText(conecta.rs.getString("Recados"));
                            conecta.desconecta();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(rootPane, "ERRO na pesquisa dos dados...\nERRO: " + e);
                        }
                        conecta.desconecta();
                    }
                }

            } catch (SQLException ex) {
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Existe mensagem para esse usuário, entretanto o usuário não tem acesso a agenda de recado.\nSolicite liberação do administrador do sistema.");
        }
    }

    public void buscarUsuario(String nomeUser) {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT * FROM USUARIOS "
                    + "WHERE NomeUsuario='" + nomeUser + "'");
            conecta.rs.first();
            codUsuario = conecta.rs.getInt("IdUsuario");
            nomeUsuarioCompromisso = conecta.rs.getString("NomeUsuario");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Não foi possível encontrar o usuário.\nERRO: " + ex);
        }
    }

    public void preencherTabelaTodosRecados(String sql) {
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{"Código", "Data", "Remetente", "Destinatário"};
        conecta.abrirConexao();
        try {
            conecta.executaSQL(sql);
            conecta.rs.last();
            do {
                // Formatar a data Entrada
                dataLanc = conecta.rs.getString("DataLanc");
                String dia = dataLanc.substring(8, 10);
                String mes = dataLanc.substring(5, 7);
                String ano = dataLanc.substring(0, 4);
                dataLanc = dia + "/" + mes + "/" + ano;
                dados.add(new Object[]{conecta.rs.getInt("IdLanc"), dataLanc, conecta.rs.getString("NomeUsuarioLogado"), conecta.rs.getString("NomeUsuario")});
            } while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não existe dados a ser exibido!!!");
        }
        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        jTabelaTodosRecados.setModel(modelo);
        jTabelaTodosRecados.getColumnModel().getColumn(0).setPreferredWidth(52);
        jTabelaTodosRecados.getColumnModel().getColumn(0).setResizable(false);
        jTabelaTodosRecados.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTabelaTodosRecados.getColumnModel().getColumn(1).setResizable(false);
        jTabelaTodosRecados.getColumnModel().getColumn(2).setPreferredWidth(280);
        jTabelaTodosRecados.getColumnModel().getColumn(2).setResizable(false);
        jTabelaTodosRecados.getColumnModel().getColumn(3).setPreferredWidth(280);
        jTabelaTodosRecados.getColumnModel().getColumn(3).setResizable(false);
        jTabelaTodosRecados.getTableHeader().setReorderingAllowed(false);
        jTabelaTodosRecados.setAutoResizeMode(jTabelaTodosRecados.AUTO_RESIZE_OFF);
        jTabelaTodosRecados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conecta.desconecta();
    }

    public void verificarAgendaCompromisso() {
        buscarAcessoUsuario(telaAgendaRecado);
        if (nameUser.equals("ADMINISTRADOR DO SISTEMA") || codigoUser == codUserAcesso && nomeTela.equals(telaAgendaRecado) && codAbrir == 1) {
            convertedata.converter(jDataSistema.getText());
            if (tipoServidor == null || tipoServidor.equals("")) {
                JOptionPane.showMessageDialog(rootPane, "É necessário definir o parâmtero para o sistema operacional utilizado no servidor, (UBUNTU-LINUX ou WINDOWS SERVER).");
            } else if (tipoServidor.equals("Servidor Linux (Ubuntu)/MS-SQL Server")) {
                buscarUsuario(nameUser);
                conecta.abrirConexao();
                try {
                    conecta.executaSQL("SELECT * FROM AGENDA_COMPROMISSOS "
                            + "WHERE UsuarioAgenda='" + nameUser + "' "
                            + "AND StatusAgenda='" + statusAgenda + "' "
                            + "AND DataLembrete='" + dataSisConvert + "' "
                            + "AND HoraLembrete<='" + jHoraSistema.getText() + "'");
                    conecta.rs.first();
                    horaLembrete = conecta.rs.getString("HoraLembrete");
                    usuarioAgenda = conecta.rs.getString("UsuarioAgenda");
                    codigoAgendaComp = conecta.rs.getString("IdAgenda");
                    //
                    if (nomeUsuarioCompromisso.equals(usuarioAgenda)) {
                        TelaAgendaCompromissos objAgendaComp = new TelaAgendaCompromissos();
                        jPainelPrincipal.add(objAgendaComp);
                        objAgendaComp.show();
                        //
                        flag = 1;
                        preencherTabelaAgendaCompromisso("SELECT * FROM AGENDA_COMPROMISSOS "
                                + "WHERE AGENDA_COMPROMISSOS.UsuarioAgenda='" + nameUser + "' "
                                + "AND AGENDA_COMPROMISSOS.StatusAgenda='" + statusAgenda + "' "
                                + "AND DataLembrete='" + dataSisConvert + "' "
                                + "AND HoraLembrete<='" + jHoraSistema.getText().toString() + "' "
                                + "AND IdAgenda='" + codigoAgendaComp + "'");
                        if (flag == 1) {
                            jBtNovoComp.setEnabled(true);
                            jBtAlterarComp.setEnabled(true);
                            jBtExcluirComp.setEnabled(true);
                            jBtSalvarComp.setEnabled(!true);
                            jBtCancelarComp.setEnabled(true);
                            jBtConfirmarCompromisso.setEnabled(true);
                            conecta.abrirConexao();
                            try {
                                conecta.executaSQL("SELECT * FROM AGENDA_COMPROMISSOS "
                                        + "WHERE AGENDA_COMPROMISSOS.UsuarioAgenda='" + nomeUsuarioCompromisso + "' "
                                        + "AND AGENDA_COMPROMISSOS.StatusAgenda='" + statusAgenda + "' "
                                        + "AND HoraLembrete<='" + jHoraSistema.getText().toString() + "' "
                                        + "AND IdAgenda='" + codigoAgendaComp + "'");
                                conecta.rs.first();
                                jCodigoAgendaComp.setText(String.valueOf(conecta.rs.getInt("IdAgenda")));
                                jComboBoxStatusComp.setSelectedItem(conecta.rs.getString("StatusAgenda"));
                                jComboBoxTipoEvento.setSelectedItem(conecta.rs.getString("TipoEvento"));
                                jDataEvento.setDate(conecta.rs.getDate("DataAgenda"));
                                jAssunto.setText(conecta.rs.getString("Assunto"));
                                jComboBoxPrioridade.setSelectedItem(conecta.rs.getString("Prioridade"));
                                jComboBoxConclusao.setSelectedItem(conecta.rs.getString("Conclusao"));
                                jDataInicio.setDate(conecta.rs.getDate("DataInicio"));
                                jDataTermino.setDate(conecta.rs.getDate("DataTermino"));
                                jHoraInicio.setText(conecta.rs.getString("HoraInicio"));
                                jHoraTermino.setText(conecta.rs.getString("HoraTermino"));
                                jDataLembrete.setDate(conecta.rs.getDate("DataLembrete"));
                                jHoraLembrete.setText(conecta.rs.getString("HoraLembrete"));
                                jTextoEvento.setText(conecta.rs.getString("Texto"));
                                jNomeUsuarioAgenda.setText(conecta.rs.getString("UsuarioAgenda"));
                                conecta.desconecta();
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(rootPane, "ERRO na pesquisa dos dados...\nERRO: " + e);
                            }
                            conecta.desconecta();
                        }
                    }
                } catch (SQLException ex) {
                }
            } else if (tipoServidor.equals("Servidor Windows/MS-SQL Server")) {
                buscarUsuario(nameUser);
                conecta.abrirConexao();
                try {
                    conecta.executaSQL("SELECT * FROM AGENDA_COMPROMISSOS "
                            + "WHERE UsuarioAgenda='" + nameUser + "' "
                            + "AND StatusAgenda='" + statusAgenda + "' "
                            + "AND DataLembrete='" + jDataSistema.getText() + "' "
                            + "AND HoraLembrete<='" + jHoraSistema.getText() + "'");
                    conecta.rs.first();
                    horaLembrete = conecta.rs.getString("HoraLembrete");
                    usuarioAgenda = conecta.rs.getString("UsuarioAgenda");
                    codigoAgendaComp = conecta.rs.getString("IdAgenda");
                    //
                    if (nomeUsuarioCompromisso.equals(usuarioAgenda)) {
                        TelaAgendaCompromissos objAgendaComp = new TelaAgendaCompromissos();
                        jPainelPrincipal.add(objAgendaComp);
                        objAgendaComp.show();
                        //
                        flag = 1;
                        preencherTabelaAgendaCompromisso("SELECT * FROM AGENDA_COMPROMISSOS "
                                + "WHERE AGENDA_COMPROMISSOS.UsuarioAgenda='" + nameUser + "' "
                                + "AND AGENDA_COMPROMISSOS.StatusAgenda='" + statusAgenda + "' "
                                + "AND DataLembrete='" + jDataSistema.getText() + "' "
                                + "AND HoraLembrete<='" + jHoraSistema.getText() + "' "
                                + "AND IdAgenda='" + codigoAgendaComp + "'");
                        if (flag == 1) {
                            jBtNovoComp.setEnabled(true);
                            jBtAlterarComp.setEnabled(true);
                            jBtExcluirComp.setEnabled(true);
                            jBtSalvarComp.setEnabled(!true);
                            jBtCancelarComp.setEnabled(true);
                            jBtConfirmarCompromisso.setEnabled(true);
                            conecta.abrirConexao();
                            try {
                                conecta.executaSQL("SELECT * FROM AGENDA_COMPROMISSOS "
                                        + "WHERE AGENDA_COMPROMISSOS.UsuarioAgenda='" + nomeUsuarioCompromisso + "' "
                                        + "AND AGENDA_COMPROMISSOS.StatusAgenda='" + statusAgenda + "' "
                                        + "AND HoraLembrete<='" + jHoraSistema.getText().toString() + "' "
                                        + "AND IdAgenda='" + codigoAgendaComp + "'");
                                conecta.rs.first();
                                jCodigoAgendaComp.setText(String.valueOf(conecta.rs.getInt("IdAgenda")));
                                jComboBoxStatusComp.setSelectedItem(conecta.rs.getString("StatusAgenda"));
                                jComboBoxTipoEvento.setSelectedItem(conecta.rs.getString("TipoEvento"));
                                jDataEvento.setDate(conecta.rs.getDate("DataAgenda"));
                                jAssunto.setText(conecta.rs.getString("Assunto"));
                                jComboBoxPrioridade.setSelectedItem(conecta.rs.getString("Prioridade"));
                                jComboBoxConclusao.setSelectedItem(conecta.rs.getString("Conclusao"));
                                jDataInicio.setDate(conecta.rs.getDate("DataInicio"));
                                jDataTermino.setDate(conecta.rs.getDate("DataTermino"));
                                jHoraInicio.setText(conecta.rs.getString("HoraInicio"));
                                jHoraTermino.setText(conecta.rs.getString("HoraTermino"));
                                jDataLembrete.setDate(conecta.rs.getDate("DataLembrete"));
                                jHoraLembrete.setText(conecta.rs.getString("HoraLembrete"));
                                jTextoEvento.setText(conecta.rs.getString("Texto"));
                                jNomeUsuarioAgenda.setText(conecta.rs.getString("UsuarioAgenda"));
                                conecta.desconecta();
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(rootPane, "ERRO na pesquisa dos dados...\nERRO: " + e);
                            }
                            conecta.desconecta();
                        }
                    }
                } catch (SQLException ex) {
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Existe mensagem para esse usuário, entretanto o usuário não tem acesso a agenda de recado.\nSolicite liberação do administrador do sistema.");
        }
    }

    public void preencherTabelaAgendaCompromisso(String sql) {
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{"Código ", "Data", "Status", "Assunto", "Usuário"};
        conecta.abrirConexao();
        try {
            conecta.executaSQL(sql);
            conecta.rs.first();
            do {
                count = count + 1;
                // Formatar a data no formato Brasil
                dataAgenda = conecta.rs.getString("DataAgenda");
                String dia = dataAgenda.substring(8, 10);
                String mes = dataAgenda.substring(5, 7);
                String ano = dataAgenda.substring(0, 4);
                dataAgenda = dia + "/" + mes + "/" + ano;
                jtotalRegistros.setText(Integer.toString(count));
                dados.add(new Object[]{conecta.rs.getInt("IdAgenda"), dataAgenda, conecta.rs.getString("StatusAgenda"), conecta.rs.getString("Assunto"), conecta.rs.getString("UsuarioAgenda")});
            } while (conecta.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Não existem dados a serem EXIBIDOS!!!");
        }
        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        jTabelaAgendaEventos.setModel(modelo);
        jTabelaAgendaEventos.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTabelaAgendaEventos.getColumnModel().getColumn(0).setResizable(false);
        jTabelaAgendaEventos.getColumnModel().getColumn(1).setPreferredWidth(70);
        jTabelaAgendaEventos.getColumnModel().getColumn(1).setResizable(false);
        jTabelaAgendaEventos.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTabelaAgendaEventos.getColumnModel().getColumn(2).setResizable(false);
        jTabelaAgendaEventos.getColumnModel().getColumn(3).setPreferredWidth(250);
        jTabelaAgendaEventos.getColumnModel().getColumn(3).setResizable(false);
        jTabelaAgendaEventos.getColumnModel().getColumn(4).setPreferredWidth(300);
        jTabelaAgendaEventos.getColumnModel().getColumn(4).setResizable(false);
        jTabelaAgendaEventos.setAutoResizeMode(jTabelaAgendaEventos.AUTO_RESIZE_OFF);
        jTabelaAgendaEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        alinharCamposTabela();
        conecta.desconecta();
    }

    public void alinharCamposTabela() {
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        //
        jTabelaAgendaEventos.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        jTabelaAgendaEventos.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        jTabelaAgendaEventos.getColumnModel().getColumn(2).setCellRenderer(centralizado);
    }

    // PARAMETRO PARA IDENTIFICAR O OS DO SERVIDOR DE BANCO DE DADOS.
    public void verificarParametrosSRV() {
        conecta.abrirConexao();
        try {
            conecta.executaSQL("SELECT * FROM SOFTWARE");
            conecta.rs.first();
            tipoServidor = conecta.rs.getString("TipoServidor");
            tipoBancoDados = conecta.rs.getString("TipoBancoDados");
        } catch (Exception e) {
        }
        conecta.desconecta();
    }
}
