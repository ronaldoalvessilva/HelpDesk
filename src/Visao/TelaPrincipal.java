/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Dao.ConexaoBancoDados;
import Dao.telasSistemaDao;
import Modelo.CadastroTelasSistema;
import static Visao.LoginHD.nameUser;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
    //    
    private TelaEmpresa objEmp = null;
    private TelaOcorrenciasHD objOcr = null;
    private TelaSolicitantes objSoli = null;
    private TelaUsuarios objUser = null;
    private TelaSoftware objSoftware = null;
    private TelaModuloSistema objModulo = null;
    
    private TelaAgendaCompromissos objAgendaComp = null;
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
    public static String telaChamadosSuporte = "Suporte Técnico:Chamados Suporte:Manutenção";
    public static String telaChamadosDesenvolvimento = "Desenvolvimento:Chamados Desenvolvimento:Manutenção";
    public static String telaConsultasSql = "Desenvolvimento:Consultas SQL:Manutenção";
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
    //CHAMADOS DE SUPORTE
    String pNomeCSU = "";
    //CHAMADOS DESENVOLVIMENTO
    String pNomeCD = "";
    //CONSULTAS SQL
    String pNomeCSQL = "";

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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jHoraSistema = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
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
        jButton8 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jEmpresa = new javax.swing.JMenuItem();
        jOcorrencias = new javax.swing.JMenuItem();
        jSolicitantes = new javax.swing.JMenuItem();
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
        jMenu2 = new javax.swing.JMenu();
        jChamadosSuporte = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("...::: Sistema de Suporte Técnico :::...");

        javax.swing.GroupLayout jPainelPrincipalLayout = new javax.swing.GroupLayout(jPainelPrincipal);
        jPainelPrincipal.setLayout(jPainelPrincipalLayout);
        jPainelPrincipalLayout.setHorizontalGroup(
            jPainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPainelPrincipalLayout.setVerticalGroup(
            jPainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204))));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("HelpDesk");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Hora:");

        jHoraSistema.setEditable(false);
        jHoraSistema.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Sistema de Controle de Chamados (Help Desk)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(jHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204))));
        jPanel1.setToolTipText("Cadastros");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/250119203502_32.png"))); // NOI18N
        jButton2.setToolTipText("Ocorrências");
        jButton2.setContentAreaFilled(false);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119171503_32.png"))); // NOI18N
        jButton5.setToolTipText("Empresa");
        jButton5.setContentAreaFilled(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191013_32.png"))); // NOI18N
        jButton1.setToolTipText("Usuários");
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119192047_32.png"))); // NOI18N
        jButton9.setContentAreaFilled(false);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191527_32.png"))); // NOI18N
        jButton10.setContentAreaFilled(false);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191721_32.png"))); // NOI18N
        jButton11.setContentAreaFilled(false);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119193958_32.png"))); // NOI18N
        jButton12.setContentAreaFilled(false);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119194051_32.png"))); // NOI18N
        jButton13.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton10, jButton11, jButton12, jButton2, jButton5, jButton9});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9)
                    .addComponent(jButton10)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12)
                    .addComponent(jButton13))
                .addGap(55, 55, 55))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton10, jButton11, jButton12, jButton13, jButton2, jButton5, jButton9});

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

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119192810_32.png"))); // NOI18N
        jButton8.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton8)
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204))));
        jPanel6.setToolTipText("Desenvolvimento");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Database 3 32x32.png"))); // NOI18N
        jButton3.setToolTipText("Consultas SQL");
        jButton3.setContentAreaFilled(false);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119193229_32.png"))); // NOI18N
        jButton6.setToolTipText("Relatórios");
        jButton6.setContentAreaFilled(false);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119173806_32.png"))); // NOI18N
        jButton7.setToolTipText("Chamados Desnvolvimento");
        jButton7.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton6)
            .addComponent(jButton7)
        );

        jMenu1.setMnemonic('C');
        jMenu1.setText("Cadastros");

        jEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119190812_16.png"))); // NOI18N
        jEmpresa.setText("Empresa/Unidade Prisional");
        jEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEmpresaActionPerformed(evt);
            }
        });
        jMenu1.add(jEmpresa);

        jOcorrencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/250119203457_16.png"))); // NOI18N
        jOcorrencias.setText("Ocorrências");
        jOcorrencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOcorrenciasActionPerformed(evt);
            }
        });
        jMenu1.add(jOcorrencias);

        jSolicitantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119192043_16.png"))); // NOI18N
        jSolicitantes.setText("Solicitantes");
        jSolicitantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSolicitantesActionPerformed(evt);
            }
        });
        jMenu1.add(jSolicitantes);
        jMenu1.add(jSeparator2);

        jUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191009_16.png"))); // NOI18N
        jUsuarios.setText("Usuários");
        jUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUsuariosActionPerformed(evt);
            }
        });
        jMenu1.add(jUsuarios);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191524_16.png"))); // NOI18N
        jMenu4.setText("Sistemas e Módulos");

        jSoftware.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191524_16.png"))); // NOI18N
        jSoftware.setText("Sistema (Software)");
        jSoftware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSoftwareActionPerformed(evt);
            }
        });
        jMenu4.add(jSoftware);

        jModulosSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191718_16.png"))); // NOI18N
        jModulosSistema.setText("Módulos do Sistema");
        jModulosSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jModulosSistemaActionPerformed(evt);
            }
        });
        jMenu4.add(jModulosSistema);

        jMenu1.add(jMenu4);
        jMenu1.add(jSeparator1);

        jAgendaRecados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119190439_16.png"))); // NOI18N
        jAgendaRecados.setText("Agenda de Recados");
        jAgendaRecados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAgendaRecadosActionPerformed(evt);
            }
        });
        jMenu1.add(jAgendaRecados);

        jAgendaCompromisso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119190604_16.png"))); // NOI18N
        jAgendaCompromisso.setText("Agenda de Compromissos");
        jAgendaCompromisso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAgendaCompromissoActionPerformed(evt);
            }
        });
        jMenu1.add(jAgendaCompromisso);
        jMenu1.add(jSeparator3);

        jSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/shutdown-icone-6920-16.png"))); // NOI18N
        jSair.setText("Sair");
        jSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSairActionPerformed(evt);
            }
        });
        jMenu1.add(jSair);

        jMenuBar1.add(jMenu1);

        jMenu2.setMnemonic('h');
        jMenu2.setText("Suporte Técnico");

        jChamadosSuporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119192807_16.png"))); // NOI18N
        jChamadosSuporte.setText("Chamados");
        jChamadosSuporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChamadosSuporteActionPerformed(evt);
            }
        });
        jMenu2.add(jChamadosSuporte);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Desenvolvimento");

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Database 3 16x16.png"))); // NOI18N
        jMenuItem7.setText("Consultas SQL");
        jMenu5.add(jMenuItem7);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119191331_16.png"))); // NOI18N
        jMenuItem8.setText("Chamados");
        jMenu5.add(jMenuItem8);

        jMenuBar1.add(jMenu5);

        jMenu3.setText("Relatórios");

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/gtklp-icone-3770-16.png"))); // NOI18N
        jMenuItem13.setText("Chamados Suporte Técnico");
        jMenu3.add(jMenuItem13);

        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/240119193225_16.png"))); // NOI18N
        jMenuItem14.setText("Chamados Desenvolvimento");
        jMenu3.add(jMenuItem14);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("Sobre");
        jMenuBar1.add(jMenu6);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPainelPrincipal)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
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
//        mostrarTelaTrocaSenha();
    }//GEN-LAST:event_jBtTrocarSenhaActionPerformed

    private void jBtLogoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtLogoffActionPerformed
        // Sair e voltar para troca de usuário
//        dispose();
//        TelaLoginSenhaCPK tls = new TelaLoginSenhaCPK(this, true);
//        tls.setVisible(true);
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
    }//GEN-LAST:event_jAgendaRecadosActionPerformed

    private void jAgendaCompromissoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAgendaCompromissoActionPerformed
        // TODO add your handling code here:
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
    }//GEN-LAST:event_jAgendaCompromissoActionPerformed

    private void jSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSairActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jSairActionPerformed

    private void jChamadosSuporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChamadosSuporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jChamadosSuporteActionPerformed

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
    private javax.swing.JButton jBtLogoff;
    private javax.swing.JButton jBtTrocarSenha;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JMenuItem jChamadosSuporte;
    public static javax.swing.JTextField jDataSistema;
    private javax.swing.JMenuItem jEmpresa;
    public static javax.swing.JTextField jHoraSistema;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLoginConectado;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jModulosSistema;
    private javax.swing.JMenuItem jOcorrencias;
    public static javax.swing.JDesktopPane jPainelPrincipal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JMenuItem jSair;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem jSoftware;
    private javax.swing.JMenuItem jSolicitantes;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JMenuItem jUsuarios;
    // End of variables declaration//GEN-END:variables

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
}
