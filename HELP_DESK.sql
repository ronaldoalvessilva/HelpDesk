USE HELP_DESK
IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('USUARIOS'))
	CREATE TABLE USUARIOS
	(
	IdUsuario INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	StatusUsuario VARCHAR(20) NULL,
	DataCadastro DATETIME NULL,
	NomeUsuario VARCHAR(200) NULL,
	NivelUsuario INT NULL,
	SetorUsuario VARCHAR(300) NULL,
	CargoUsuario VARCHAR(300) NULL,
	LoginUsuario VARCHAR(100) NULL,
	SenhaUsuario VARCHAR(200) NULL,
	SenhaUsuario1 VARCHAR(200) NULL,
	SenhaCriptografada VARCHAR(200) NULL,
	)

--INSERT INTO USUARIOS(StatusUsuario,DataCadastro,NomeUsuario,LoginUsuario,SenhaUsuario,SenhaUsuario1) VALUES('Ativo','15/10/2019','ADMINISTRADOR DO SISTEMA','admin',1,1)

-- TABELA DE LOG DO SISTEMA

IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('LOGSISTEMA'))
	CREATE TABLE LOGSISTEMA
	(
	IdLog INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	DataMov VARCHAR(10) NULL,
	HorarioMov VARCHAR(20) NULL,
	NomeModuloTela VARCHAR(300) NULL,
	IdLancMov INT NULL,
	NomeUsuarioLogado VARCHAR(50) NULL,
	StatusMov VARCHAR(10) NULL, 
	)

IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('EMPRESA'))
	CREATE TABLE EMPRESA
	(
	IdEmpresa INT IDENTITY (1,1) PRIMARY KEY NOT NULL,		
	StatusFor VARCHAR(20) NULL,
	DataCadastro DATETIME NULL,
	RazaoSocial VARCHAR(250) NULL,	
	NomeFantasia VARCHAR(250) NULL,	
	Cnpj VARCHAR(20) NULL,
	InsEstadual VARCHAR(20) NULL,	
	NomeContato VARCHAR(300) NULL,
	RgFor VARCHAR(20) NULL,
	Telefone VARCHAR(20) NULL,
	Telefone1 VARCHAR(20) NULL,
	Celular VARCHAR(20) NULL,
	Email VARCHAR(100) NULL,
	Fax VARCHAR(20) NULL,
	Endereco VARCHAR(300) NULL,
	Compl VARCHAR(200) NULL,
	Cep VARCHAR(20) NULL,
	Cidade VARCHAR(200) NULL,
	Estado VARCHAR(2) NULL,
	EnderecoCob VARCHAR(300) NULL,
	ComplCob VARCHAR(200) NULL,
	CepCob VARCHAR(20) NULL,
	CidadeCob VARCHAR(200) NULL,
	EstadoCob VARCHAR(2) NULL,
	UsuarioInsert VARCHAR(50) NULL,
	UsuarioUp VARCHAR(50) NULL,	
	DataInsert VARCHAR(20) NULL,
	DataUp VARCHAR(20) NULL,	
	HorarioInsert VARCHAR(10) NULL,
	HorarioUp VARCHAR(10) NULL,	
	DataAtualizacao DATETIME NULL,
	VersaoAtual VARCHAR(5) NULL,	
	)

-- TABELA DE UNIDADES PENAL ADMINISTRADA PELA EMPRESA	


IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('UNIDADE_PENAL_EMPRESA'))
	CREATE TABLE UNIDADE_PENAL_EMPRESA
	(
	IdUnidEmp INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	IdEmpresa INT FOREIGN KEY REFERENCES EMPRESA(IdEmpresa) NOT NULL,
	DescricaoUnidade VARCHAR(300) NULL,
	Endereco VARCHAR(300) NULL,
	Bairro VARCHAR(100) NULL,
	Cidade VARCHAR(250) NULL,
	Estado VARCHAR(15) NULL,
	Regime VARCHAR(150) NULL,
	CapacidadeMas INT NULL,
	CapacidadeFen INT NULL,
	CapacidadeTotal INT NULL,
	UsuarioInsert VARCHAR(50) NULL,
	UsuarioUp VARCHAR(50) NULL,	
	DataInsert VARCHAR(20) NULL,
	DataUp VARCHAR(20) NULL,	
	HorarioInsert VARCHAR(10) NULL,
	HorarioUp VARCHAR(10) NULL,
	)

IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('TELAS'))
	CREATE TABLE TELAS
	(
	IdTelas INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	NomeTela VARCHAR(600) NULL,
	)

IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('TELAS_ACESSO'))
	CREATE TABLE TELAS_ACESSO
	(
	IdTela INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	IdUsuario INT FOREIGN KEY REFERENCES USUARIOS(IdUsuario) NOT NULL,	
	NomeTela VARCHAR(500) NOT NULL,
	Abrir INT NULL,
	Incluir INT NULL,
	Alterar INT NULL,
	Excluir INT NULL,
	Gravar INT NULL,
	Consultar INT NULL,
    NomeModulo VARCHAR(100) NULL,
	UsuarioInsert VARCHAR(50) NULL,
	UsuarioUp VARCHAR(50) NULL,	
	DataInsert VARCHAR(20) NULL,
	DataUp VARCHAR(20) NULL,	
	HorarioInsert VARCHAR(10) NULL,
	HorarioUp VARCHAR(10) NULL,
	)

-- TABELA DE AGENDA DE RECADOS

IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('AGENDA_RECADOS'))
	CREATE TABLE AGENDA_RECADOS
	(
	IdLanc INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	DataLanc DATETIME NULL,
	Horario VARCHAR(10) NULL,
	StatusAgenda VARCHAR(30) NULL,
	NomeUsuarioLogado VARCHAR(300) NULL,
	IdUsuario INT FOREIGN KEY REFERENCES USUARIOS(IdUsuario) NOT NULL,
	Recados VARCHAR(MAX) NULL,
	MensagemENviada VARCHAR(10) NULL,
	MensagemRecebida VARCHAR(10) NULL,
	UsuarioInsert VARCHAR(50) NULL,
	UsuarioUp VARCHAR(50) NULL,
	UsuarioDelete VARCHAR(50) NULL,
	DataInsert VARCHAR(20) NULL,
	DataUp VARCHAR(20) NULL,
	DataDelete VARCHAR(20) NULL,
	HorarioInsert VARCHAR(10) NULL,
	HorarioUp VARCHAR(10) NULL,
	)

--- TABELA OCORR�NCIA P1

IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('OCORRENCIAS_P1'))
	CREATE TABLE OCORRENCIAS_P1
	(
	IdLanc INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	StatusLanc VARCHAR(20) NULL,
	DataLanc DATETIME NULL,
	Titulo VARCHAR(300) NULL,
	TextoArea VARCHAR(MAX) NULL,
	Fonte VARCHAR(200) NULL,
	Tamanho VARCHAR(3) NULL,
	BtEsq INT NULL,
	BtCen INT NULL,
	BtDir INT NULL,
	BtJus INT NULL,
	UsuarioInsert VARCHAR(50) NULL,
	UsuarioUp VARCHAR(50) NULL,
	DataInsert VARCHAR(20) NULL,
	DataUp VARCHAR(20) NULL,
	HorarioInsert VARCHAR(10) NULL,
	HorarioUp VARCHAR(10) NULL,
	)

IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('SOLICITANTES'))
	CREATE TABLE SOLICITANTES
	(
    IdSolicitante INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	StatusSolicitante VARCHAR(20) NULL,
	DataCadastro DATETIME NULL,
	NomeSolicitante VARCHAR(300) NULL,
	IdEmpresa INT FOREIGN KEY REFERENCES EMPRESA(IdEmpresa) NOT NULL,
	IdUnidEmp INT FOREIGN KEY REFERENCES UNIDADE_PENAL_EMPRESA(IdUnidEmp) NOT NULL,
	Observacao VARCHAR(MAX) NULL,
	UsuarioInsert VARCHAR(50) NULL,
	UsuarioUp VARCHAR(50) NULL,
	DataInsert VARCHAR(20) NULL,
	DataUp VARCHAR(20) NULL,
	HorarioInsert VARCHAR(10) NULL,
	HorarioUp VARCHAR(10) NULL,
	)

IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('SOFTWARE'))
	CREATE TABLE SOFTWARE
	(
    IdSoftware INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	StatusSoftware VARCHAR(20) NULL,
	DataCadastro DATETIME NULL,
	DescricaoSoftware VARCHAR(300) NULL,
	VersaoSoftware VARCHAR(50) NULL,
	ObservacaoSoftware VARCHAR(MAX) NULL,
	UsuarioInsert VARCHAR(50) NULL,
	UsuarioUp VARCHAR(50) NULL,
	DataInsert VARCHAR(20) NULL,
	DataUp VARCHAR(20) NULL,
	HorarioInsert VARCHAR(10) NULL,
	HorarioUp VARCHAR(10) NULL,
	)

IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('MODULOS'))
	CREATE TABLE MODULOS
	(
    IdModulo INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	StatusModulo VARCHAR(20) NULL,
	DataCadastro DATETIME NULL,
	DescricaoModulo VARCHAR(300) NULL,
	IdSoftware INT FOREIGN KEY REFERENCES SOFTWARE(IdSoftware) NOT NULL,
	ObservacaoModulo VARCHAR(MAX) NULL,
	UsuarioInsert VARCHAR(50) NULL,
	UsuarioUp VARCHAR(50) NULL,
	DataInsert VARCHAR(20) NULL,
	DataUp VARCHAR(20) NULL,
	HorarioInsert VARCHAR(10) NULL,
	HorarioUp VARCHAR(10) NULL,
	)

-- TABELA DE AGENDA DE COMPROMISSOS

IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('AGENDA_COMPROMISSOS'))
	CREATE TABLE AGENDA_COMPROMISSOS
	(
	IdAgenda INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	StatusAgenda VARCHAR(30) NULL,
	TipoEvento VARCHAR(50) NULL,
	DataAgenda DATE NULL,
	Assunto VARCHAR(250) NULL,
	Prioridade VARCHAR(40) NULL,
	Conclusao VARCHAR(40) NULL,
	DataInicio DATE NULL,
	DataTermino DATE NULL,
	HoraInicio VARCHAR(10) NULL,
	HoraTermino VARCHAR(10) NULL,
	DataLembrete DATE NULL,
	HoraLembrete VARCHAR(5) NULL,
	Texto VARCHAR(MAX) NULL,
	UsuarioAgenda VARCHAR(250) NULL,
	UsuarioInsert VARCHAR(50) NULL,
	UsuarioUp VARCHAR(50) NULL,
	DataInsert VARCHAR(20) NULL,
	DataUp VARCHAR(20) NULL,
	HorarioInsert VARCHAR(10) NULL,
	HorarioUp VARCHAR(10) NULL,
	)


IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('CHAMADOS_SUPORTE'))
	CREATE TABLE CHAMADOS_SUPORTE
	(
	IdCHSup INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	StatusCha VARCHAR(30) NULL,	
	DataCha DATE NULL,
	IdUsuario INT FOREIGN KEY REFERENCES USUARIOS(IdUsuario) NOT NULL,	
	IdSolicitante INT FOREIGN KEY REFERENCES SOLICITANTES(IdSolicitante) NOT NULL,
	IdUnidEmp INT FOREIGN KEY REFERENCES UNIDADE_PENAL_EMPRESA(IdUnidEmp) NOT NULL,
	UsuarioInsert VARCHAR(50) NULL,
	UsuarioUp VARCHAR(50) NULL,
	DataInsert VARCHAR(20) NULL,
	DataUp VARCHAR(20) NULL,
	HorarioInsert VARCHAR(10) NULL,
	HorarioUp VARCHAR(10) NULL,
	)

IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('ITENS_CHAMADOS_SUPORTE'))
	CREATE TABLE ITENS_CHAMADOS_SUPORTE
	(
	IdItem INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	IdCHSup INT FOREIGN KEY REFERENCES CHAMADOS_SUPORTE(IdCHSup) NOT NULL,	
	IdSoftware INT FOREIGN KEY REFERENCES SOFTWARE (IdSoftWare) NOT NULL,
	IdModulo INT FOREIGN KEY REFERENCES MODULOs(IdModulo) NOT NULL,	
	DataItemCh DATETIME NULL,
	HorarioInicio VARCHAR(20) NULL,
	HorarioTermino VARCHAR(20) NULL,
	TextoSuporte VARCHAR(MAX) NULL,
	TextoDesenvol VARCHAR(MAX) NULL,
	TipoChamado VARCHAR(20) NULL,
	UsuarioInsert VARCHAR(50) NULL,
	UsuarioUp VARCHAR(50) NULL,
	DataInsert VARCHAR(20) NULL,
	DataUp VARCHAR(20) NULL,
	HorarioInsert VARCHAR(10) NULL,
	HorarioUp VARCHAR(10) NULL,
	)


IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('CHAMADOS_DESENVOLVIMENTO'))
	CREATE TABLE CHAMADOS_DESENVOLVIMENTO
	(
	IdCHDes INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	StatusCha VARCHAR(30) NULL,	
	DataCha DATE NULL,
	IdUsuario INT FOREIGN KEY REFERENCES USUARIOS(IdUsuario) NOT NULL,	
	IdSolicitante INT FOREIGN KEY REFERENCES SOLICITANTES(IdSolicitante) NOT NULL,
	IdUnidEmp INT FOREIGN KEY REFERENCES UNIDADE_PENAL_EMPRESA(IdUnidEmp) NOT NULL,
	UsuarioInsert VARCHAR(50) NULL,
	UsuarioUp VARCHAR(50) NULL,
	DataInsert VARCHAR(20) NULL,
	DataUp VARCHAR(20) NULL,
	HorarioInsert VARCHAR(10) NULL,
	HorarioUp VARCHAR(10) NULL,
	)

IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('ITENS_CHAMADOS_DESENVOLVIMENTO'))
	CREATE TABLE ITENS_CHAMADOS_DESENVOLVIMENTO
	(
	IdItemDes INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	IdItem INT NULL,
	IdCHDes INT FOREIGN KEY REFERENCES CHAMADOS_DESENVOLVIMENTO(IdCHDes) NOT NULL,	
	IdSoftware INT FOREIGN KEY REFERENCES SOFTWARE (IdSoftWare) NOT NULL,
	IdModulo INT FOREIGN KEY REFERENCES MODULOs(IdModulo) NOT NULL,	
	DataItemCh DATETIME NULL,
	HorarioInicio VARCHAR(20) NULL,
	HorarioTermino VARCHAR(20) NULL,
	TextoSuporte VARCHAR(MAX) NULL,
	TextoDesenvol VARCHAR(MAX) NULL,
	TipoChamado VARCHAR(20) NULL,
	UsuarioInsert VARCHAR(50) NULL,
	UsuarioUp VARCHAR(50) NULL,
	DataInsert VARCHAR(20) NULL,
	DataUp VARCHAR(20) NULL,
	HorarioInsert VARCHAR(10) NULL,
	HorarioUp VARCHAR(10) NULL,
	)

	-- TABELA QUE EXPORTAR E IMPORTAR OS CHAMADOS DO SUPORTE T�CNICO PARA O DESENVOLVIMENTO

IF NOT EXISTS (SELECT NULL FROM SYSOBJECTS WHERE ID = OBJECT_ID('ITENS_CHAMADOS_SUPORTE_DESENVOLVIMENTO'))
	CREATE TABLE ITENS_CHAMADOS_SUPORTE_DESENVOLVIMENTO
	(
	IdItemCHSD INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	IdCHSup INT FOREIGN KEY REFERENCES CHAMADOS_SUPORTE(IdCHSup) NULL,
	IdCHDes INT NULL,	
	IdItem INT FOREIGN KEY REFERENCES ITENS_CHAMADOS_SUPORTE(IdItem) NULL,
	IdSoftware INT FOREIGN KEY REFERENCES SOFTWARE (IdSoftWare) NOT NULL,
	IdModulo INT FOREIGN KEY REFERENCES MODULOs(IdModulo) NOT NULL,	
	DataItemCh DATETIME NULL,
	HorarioInicio VARCHAR(20) NULL,
	HorarioTermino VARCHAR(20) NULL,
	TextoSuporte VARCHAR(MAX) NULL,	
	Utilizado VARCHAR(3) NULL,
	UsuarioInsert VARCHAR(50) NULL,
	UsuarioUp VARCHAR(50) NULL,
	DataInsert VARCHAR(20) NULL,
	DataUp VARCHAR(20) NULL,
	HorarioInsert VARCHAR(10) NULL,
	HorarioUp VARCHAR(10) NULL,
	)

IF NOT EXISTS(SELECT 1 FROM SYSCOLUMNS WHERE ID = OBJECT_ID('SOFTWARE') AND NAME = 'TipoServidor')
   ALTER TABLE SOFTWARE ADD TipoServidor VARCHAR(200) NULL
  
IF NOT EXISTS(SELECT 1 FROM SYSCOLUMNS WHERE ID = OBJECT_ID('SOFTWARE') AND NAME = 'TipoBanco')
   ALTER TABLE SOFTWARE ADD TipoBanco VARCHAR(200) NULL

-- CAMPOS PARA AS TABELAS CHAMADOS DE SUPORTE E DESENVOLVIMENTO

IF NOT EXISTS(SELECT 1 FROM SYSCOLUMNS WHERE ID = OBJECT_ID('CHAMADOS_SUPORTE') AND NAME = 'AssuntoSuporte')
   ALTER TABLE CHAMADOS_SUPORTE ADD AssuntoSuporte VARCHAR(200) NULL

IF NOT EXISTS(SELECT 1 FROM SYSCOLUMNS WHERE ID = OBJECT_ID('CHAMADOS_DESENVOLVIMENTO') AND NAME = 'AssuntoDesenvolvimento')
   ALTER TABLE CHAMADOS_DESENVOLVIMENTO ADD AssuntoDesenvolvimento VARCHAR(200) NULL