
<table border="0" class="layerMenu">
<tr>
    <td>
	  <bean:write name="menuGCOM" scope="session" filter="false"/>
    </td>
  </tr>
  <%-- 
	<tr>
		<td>
		<div class="dtree"><script><!-- -
          d = new dTree('d');
          d.add(0,-1,'Menu GSAN');
          d.add(1,0,'GSAN','#');
          d.add(2,1,'Cadastro','#');
          d.add(3,2,'Cliente','#');
          d.add(4,2,'Im�vel','#');
          d.add(5,3,'Inserir Cliente','exibirInserirClienteAction.do?menu=sim');
          d.add(6,3,'Manter Cliente','exibirManterClienteAction.do?menu=sim');
          d.add(7,4,'Inserir Im�vel','exibirInserirImovelAction.do?menu=sim');
          d.add(8,4,'Informar Economia','exibirInformarEconomiaAction.do?menu=sim');
          d.add(9,1,'Micromedi��o','#');
          d.add(10,2,'Localidade','#');
          d.add(11,10,'Inserir Localidade','exibirInserirLocalidadeAction.do?menu=sim');
          d.add(12,10,'Manter Localidade','exibirFiltrarLocalidadeAction.do?menu=sim');
          d.add(13,10,'Inserir Setor Comercial','exibirInserirSetorComercialAction.do?menu=sim');
          d.add(14,10,'Manter Setor Comercial','exibirFiltrarSetorComercialAction.do?menu=sim');
          d.add(15,10,'Inserir Quadra','exibirInserirQuadraAction.do?menu=sim');
          d.add(16,9,'Consistir Leituras e Calcular Consumos','exibirConsistirLeiturasCalcularConsumosAction.do?menu=sim'  '');
          d.add(17,4,'Alterar Inscri��o Im�vel','exibirAlterarImovelInscricaoAction.do?menu=sim');
          d.add(18,2,'Geogr�fico','#');
          d.add(19,22,'Inserir Bairro','exibirInserirBairroAction.do?menu=sim');
          d.add(20,22,'Manter Bairro','exibirManterBairroAction.do?menu=sim');
          d.add(21,10,'Manter Quadra','exibirFiltrarQuadraAction.do?menu=sim');
          d.add(22,2,'Endere�o','#');
          d.add(23,22,'Inserir Logradouro','exibirInserirLogradouroAction.do?menu=sim');
          d.add(24,22,'Manter Logradouro','exibirManterLogradouroAction.do?menu=sim');
		  d.add(25,4,'Manter Im�vel','exibirFiltrarImovelAction.do?menu=sim');
		  d.add(26,2,'Tarifa Social','#');
		  d.add(27,26,'Inserir Tipo de Cart�o da Tarifa Social','exibirInserirTarifaSocialCartaoTipoAction.do?menu=sim');
          d.add(28,26,'Manter Tipo de Cart�o da Tarifa Social','filtrarTarifaSocialCartaoTipoAction.do?menu=sim');
          d.add(29,9,'Hidr�metro','#')
          d.add(30,29,'Inserir Hidr�metro','exibirInserirHidrometroAction.do?menu=sim');
          d.add(31,29,'Manter Hidr�metro','exibirManterHidrometroAction.do?menu=sim');
          d.add(32,29,'Movimentar Hidr�metro','exibirFiltrarHidrometroAction.do?menu=sim&tela=movimentarHidrometro');
		  d.add(33,35,'Inserir Comando de Atividade de Faturamento','exibirInserirComandoAtividadeFaturamentoAction.do?menu=sim');
		  d.add(34,35,'Manter Comando de Atividade de Faturamento','filtrarComandoAtividadeFaturamentoAction.do?menu=sim');		  
		  d.add(35,1,'Faturamento','#');
		  d.add(36,37,'Inserir Conta','exibirInserirContaAction.do?menu=sim');
		  d.add(37,35,'Conta','#');
		  d.add(38,29,'Consultar Movimenta��o de Hidr�metro','exibirFiltrarMovimentacaoHidrometroAction.do?menu=sim&tela=consultarMovimentacaoHidrometro');
		  d.add(39,26,'Inserir Dados Tarifa Social','exibirInserirTarifaSocialAction.do?menu=sim');		  
		  d.add(40,26,'Manter Dados Tarifa Social','exibirFiltrarImovelAction.do?menu=sim&acao=exibir&redirecionar=ManterDadosTarifaSocial');
		  d.add(41,37,'Informar Vencimento Alternativo','exibirInformarVencimentoAlternativoAction.do?menu=sim');
		  d.add(42,37,'Manter Conta','exibirManterContaAction.do?menu=sim');
 		  d.add(43,37,'Simular C�lculo da Conta','exibirSimularCalculoContaAction.do?menu=sim');
		  d.add(43,22,'Importar Cep Correios','exibirImportarCepAction.do?menu=sim');
		  d.add(44,111,'Inserir D�bito a Cobrar','exibirInserirDebitoACobrarAction.do?menu=sim');
		  d.add(45,111,'Cancelar D�bito a Cobrar','exibirManterDebitoACobrarAction.do?menu=sim');
          d.add(46,109,'Inserir Categoria','exibirInserirCategoriaAction.do?menu=sim'); 
          d.add(47,35, 'Tarifa de Consumo', '#');
          d.add(48,47, 'Inserir Tarifa de Consumo', 'exibirInserirConsumoTarifaAction.do?menu=sim');
          d.add(49,109, 'Manter Categoria', 'exibirManterCategoriaAction.do?menu=sim');
          d.add(50,109, 'Inserir Subcategoria', 'exibirInserirSubcategoriaAction.do?menu=sim');
          d.add(51,109, 'Manter Subcategoria', 'exibirManterSubcategoriaAction.do?menu=sim');
          d.add(52,35,'Consultar Hist�rico de Faturamento','exibirConsultarHistoricoFaturamentoAction.do?menu=sim');
		  d.add(53,1,'Relat�rio','#');
		  d.add(54,53,'Cadastro','#');
		  d.add(55,54,'Relat�rio de Im�veis','exibirFiltrarImovelOutrosCriterios.do?menu=sim&gerarRelatorio=RelatorioImoveis&limpar=S');
		  d.add(56,54,'Relat�rio dos Dados das Economias dos Im�veis','exibirFiltrarImovelOutrosCriterios.do?menu=sim&gerarRelatorio=RelatorioEconomia&limpar=S');
		  d.add(57,54,'Relat�rio Dados Tarifa Social','exibirFiltrarImovelOutrosCriterios.do?menu=sim&gerarRelatorio=RelatorioTarifaSocial&limpar=S');
		  d.add(58,35,'Inserir Cronograma Faturamento','exibirInserirFaturamentoCronogramaAction.do?menu=sim');
		  d.add(59,35,'Manter Cronograma Faturamento','exibirFiltrarFaturamentoCronogramaAction.do?menu=sim');
		  d.add(60,54,'Gerar Relat�rio de Clientes','exibirFiltrarClienteOutrosCriteriosAction.do?menu=sim');
		  d.add(61,35,'Alterar Dados para Faturamento','exibirDadosFaturamentoAction.do?menu=sim');
		  d.add(62,1,'Cobran�a','#');
		  d.add(63,62,'Consultar D�bitos','exibirConsultarDebitoAction.do?menu=sim');
		  d.add(64,9,'Manter V�nculos de Im�veis para Rateio de Consumo','exibirFiltrarImovelAction.do?menu=sim&redirecionar=ManterVinculoImoveisRateioConsumo');		  
		  d.add(65,35,'Cr�dito','#');
		  d.add(66,65,'Inserir Cr�dito a Realizar','exibirInserirCreditoARealizarAction.do?menu=sim');		  
		  d.add(67,47, 'Manter Tarifa de Consumo', 'exibirFiltrarConsumoTarifaAction.do?menu=sim');
		  d.add(68,65,'Cancelar Cr�dito a Realizar','exibirManterCreditoARealizarAction.do?menu=sim');
		  d.add(69,35,'Inserir Guia de Pagamento', 'exibirInserirGuiaPagamentoAction.do?menu=sim');
		  d.add(70,35,'Cancelar Guia de Pagamento', 'exibirManterGuiaPagamentoAction.do?menu=sim');
  		  d.add(71,9,'Consultar Hist�rico de Medi��o Individualizada', 'exibirConsultarHistoricoMedicaoIndividualizadaAction.do?menu=sim');
  		  d.add(72,9,'Analisar Exce��es de Leituras e Consumos','exibirFiltrarExcecoesLeiturasConsumosAction.do?nomeCaminhoMapping=efetuarAnaliseExcecoesLeiturasConsumos&menu=sim');
		  d.add(73,35, 'Informar Situa��o Especial de Faturamento', 'exibirSituacaoEspecialFaturamentoInformarAction.do?menu=sim' );	
		  d.add(74,29,'Consultar Hist�rico de Instala��o','exibirConsultarHistoricoInstalacaoHidrometroInformarAction.do?menu=sim');
		  d.add(75,9,'Registrar Leituras e Anormalidades','exibirRegistrarLeiturasAnormalidadesAction.do?menu=sim');
		  d.add(76,200,'Inserir Comando de A��o de Cobran�a','exibirInserirComandoAcaoCobrancaAction.do?menu=sim');		  
		  d.add(77,53,'Faturamento','#');		  
		  d.add(78,77,'Relat�rio Resumo Faturamento','exibirGerarRelatorioResumoFaturamentoAction.do?menu=sim');		  		  
		  d.add(79,200,'Executar Atividade de A��o de Cobran�a','exibirExecutarAtividadeAcaoCobrancaAction.do?menu=sim');
		  d.add(80,202,'Efetuar Parcelamento de D�bitos','exibirEfetuarParcelamentoDebitosAction.do?menu=sim');
		  d.add(81,202,'Consultar Parcelamento de D�bitos','exibirConsultarListaParcelamentoDebitoAction.do?menu=sim');
		  d.add(82,1,'Arrecada��o','#');
		  d.add(83,82,'Aviso Banc�rio','#');
		  d.add(84,82,'Pagamento','#');		  		  		  
		  d.add(85,84,'Inserir Pagamentos','exibirInserirPagamentosAction.do?menu=sim');
		  d.add(86,1,'Seguran�a','#');
		  d.add(87,86,'Transa��o','#');
		  d.add(88,87,'Consultar Opera��o','ExibirFiltrarOperacaoEfetuadaAction.do?acao=consulta&menu=sim');
		  d.add(89,137,'Inserir Devolu��es','exibirInserirDevolucoesAction.do?menu=sim');
		  d.add(90,137,'Manter Devolu��es','exibirFiltrarDevolucaoAction.do?tela=manterDevolucao&menu=sim');
		  d.add(91,53,'Transa��o','#');		  
		  d.add(92,91,'Relat�rio Tran��es Efetuada','ExibirFiltrarOperacaoAction.do?acao=relatorio&menu=sim');
		  d.add(93,9,'Consultar Im�veis Medi��o Individualizada','exibirConsultarImoveisMedicaoIndividualizadaAction.do?menu=sim');
		  d.add(94,82,'Registrar Movimento Arrecadadores','exibirRegistrarMovimentoArredadadoresAction.do?menu=sim');
		  d.add(95,83,'Inserir Aviso Banc�rio','exibirInserirAvisoBancarioAction.do?menu=sim');	          
          d.add(96,62, 'Informar Situa��o Especial de Cobran�a', 'exibirSituacaoEspecialCobrancaInformarAction.do?menu=sim' );
          d.add(97,84,'Manter Pagamentos','exibirFiltrarPagamentoAction.do?tela=manterPagamento&menu=sim');
          d.add(98,82,'Efetuar An�lise do Movimento dos Arrecadadores','exibirFiltrarMovimentoArrecadadoresAction.do?menu=sim');
          d.add(99,83,'Manter Aviso Bancario','exibirFiltrarAvisoBancarioAction.do?menu=sim');
          d.add(100,200,'Manter Comando de A��o de Cobran�a','exibirManterComandoAcaoCobrancaAction.do?menu=sim');
          d.add(101,26,'Consultar Im�veis Exclu�dos da Tarifa Social','exibirFiltrarImovelOutrosCriterios.do?menu=sim&gerarRelatorio=consultarTarifaExcluida&limpar=S');
          d.add(102,83,'Efetuar An�lise Aviso Banc�rio','exibirFiltrarAvisoBancarioAction.do?menu=sim&acao=efetuar');
          d.add(103,137,'Consultar Devolu��es','exibirFiltrarDevolucaoAction.do?menu=sim');
          d.add(104,35,'Consultar Posi��o Faturamento','PesquisarPosicaoFaturamentoAction.do?menu=sim');
          d.add(105,4,'Rela��o Cliente e Im�vel','ExibirConsultarRelacaoClienteImovelAction.do?menu=sim');
          d.add(106,35,'Executar Atividade do Faturamento','exibirExecutarAtividadeFaturamentoAction.do?menu=sim');
          d.add(107,62,'Inserir Resolu��o de Diretoria','exibirInserirResolucaoDiretoriaAction.do?menu=sim');
          d.add(108,62,'Consultar Documentos de Cobran�a','exibirFiltrarDocumentosCobrancaAction.do?menu=sim');
          d.add(109,2,'Categoria','#');
          d.add(110,84,'Consultar Pagamentos','exibirFiltrarPagamentoAction.do?menu=sim');		  
          d.add(111,35,'D�bito','#');
          d.add(112,62,'Manter Resolu��o de Diretoria','exibirFiltrarResolucaoDiretoriaAction.do?menu=sim');	  
          d.add(113,9,'Rota','#');
          d.add(114,113,'Inserir Rota','exibirInserirRotaAction.do?menu=sim');
          d.add(115,113,'Manter Rota','exibirFiltrarRotaAction.do?menu=sim');
          d.add(116,4,'Inserir Situa��o do Im�vel ','exibirInserirImovelSituacaoAction.do?menu=sim');
          d.add(117,4,'Consultar Situa��o do Im�vel ','exibirFiltrarImovelSituacaoAction.do?menu=sim');
          d.add(118,82,'Gerar Movimento do D�bito Autom�tico para os Bancos','exibirGerarMovimentoDebitoAutomaticoBancoAction.do?menu=sim');
          d.add(119,62,'Inserir Cronograma de Cobran�a ','exibirInserirCronogramaCobrancaAction.do?menu=sim');
          d.add(120,62,'Inserir Crit�rio de Cobran�a ','exibirInserirCriterioCobrancaAction.do?menu=sim');
          d.add(121,137,'Inserir Guia de Devolu��o ','exibirInserirGuiaDevolucaoAction.do?menu=sim');
		  d.add(122,37,'Desfazer Cancelamento e/ou Retifica��o','exibirManterDesfazerCancelamentoRetificacaoContaAction.do?menu=sim');
          d.add(123,86,'Acesso','#');
          d.add(124,123,'Inserir Grupo','exibirInserirGrupoAction.do?menu=sim');
          d.add(125,123,'Manter Grupo','exibirManterGrupoAction.do?menu=sim');
          d.add(126,37,'Inserir Mensagem da Conta','exibirInserirMensagemContaAction.do?menu=sim');
          d.add(127,37,'Manter Mensagem da Conta','exibirFiltrarMensagemContaAction.do?menu=sim');
          d.add(128,62,'Manter Crit�rio de Cobran�a ','exibirFiltrarCriterioCobrancaAction.do?menu=sim');
          d.add(129,77,'Relat�rio de Acompanhamento de Faturamento','exibirFiltrarImovelOutrosCriterios.do?menu=sim&gerarRelatorio=GerarRelatorioAcompanhamentoFaturamento&limpar=S');		  		  
          d.add(130,200,'Consultar Comandos de A��o de Cobran�a','exibirConsultarComandosAcaoCobrancaAction.do?menu=sim');		  		  
          d.add(131,123,'Inserir Funcionalidade','exibirInserirFuncionalidadeAction.do?menu=sim');
          d.add(132,123,'Usu�rio','#');
          d.add(133,132,'Inserir Usu�rio','exibirInserirUsuarioAction.do?menu=sim');
          d.add(134,132,'Manter Usu�rio','exibirFiltrarUsuarioAction.do?menu=sim');
          d.add(135,202,'Inserir Perfil de Parcelamento','exibirInserirPerfilParcelamentoAction.do?menu=sim');
          d.add(136,137,'Manter Guia de Devolu��o','exibirFiltrarGuiaDevolucaoAction.do?menu=sim');
          d.add(137,82,'Devolu��o','#');
          d.add(138,123,'Inserir Opera��o','exibirInserirOperacaoAction.do?menu=sim');
          d.add(139,202,'Manter Perfil de Parcelamento','exibirFiltrarPerfilParcelamentoAction.do?menu=sim');
		  d.add(140,123,'Manter Funcionalidade','exibirFiltrarFuncionalidadeAction.do?menu=sim');	
	  	  d.add(141,62,'Manter Cronograma de Cobran�a','exibirFiltrarCobrancaCronogramaAction.do?menu=sim');
	  	  d.add(142,82,'Consultar Dados Di�rios da Arrecada��o','exibirFiltrarDadosDiariosArrecadacaoAction.do?menu=sim');
	  	  d.add(143,171,'Consultar Resumo das Anormalidades','exibirInformarDadosGeracaoRelatorioConsultaAction.do?menu=sim&tipoResumo=ANORMALIDADE');
          d.add(144,53,'Cobran�a','#');
	  	  d.add(145,144,'Gerar Rela��o de D�bitos','exibirFiltrarImovelOutrosCriterios.do?menu=sim&gerarRelatorio=GerarRelacaoDebito&limpar=S');	  	  
	  	  d.add(146,148,'Consultar Resumo de Pend�ncia','exibirInformarDadosGeracaoRelatorioConsultaAction.do?menu=sim&tipoResumo=PENDENCIA');
	  	  d.add(147,1,'Gerencial','#');
	  	  d.add(148,147,'Cobranca','#');
	  	  d.add(149,147,'Arrecada��o','#');
	  	  d.add(151,147,'Faturamento');
	  	  d.add(152,151,'Consultar Resumo das Situa��es Especiais de Faturamento','exibirInformarResumoSituacaoEspecialFaturamentoAction.do?menu=sim');
          d.add(153,147,'Cadastro');
	  	  d.add(154,153,'Consultar Resumo das Ligacoes / Economias','exibirInformarDadosGeracaoRelatorioConsultaAction.do?menu=sim&tipoResumo=LIGACAO_EC0NOMIA');
	  	  d.add(155,1,'Execu��o Batch','#');
	  	  d.add(156,155,'UC0302 - Gerar D�bitos a Cobrar de Acr�scimos por Impontualidade', 'execucaoBatchAction.do?casoUso=UC0302'  '');
	  	  d.add(157,155,'UC0275 - Gerar Resumo das Liga��es/Economias', 'execucaoBatchAction.do?casoUso=UC0275'  '');
	  	  d.add(158,155,'UC0209 - Gerar Taxa de Entrega em Outro Endere�o',execucaoBatchAction.do?casoUso=UC0209&idRotas=3,984&anoMes=20045' '');
	  	  d.add(159,155,'UC0341 - Gerar Resumo da Situa��o Especial de Faturamento','execucaoBatchAction.do?casoUso=UC0341' '');
	  	  d.add(160,155,'UC0346 - Gerar Resumo da Situa��o Especial de Cobran�a','execucaoBatchAction.do?casoUso=UC0346' '');
	  	  d.add(161,155,'UC0335 - Gerar Resumo da Pend�ncia','execucaoBatchAction.do?casoUso=UC0335' '');
	  	  d.add(162,155,'UC0276 - Encerrar a Arrecada��o do M�s','execucaoBatchAction.do?casoUso=UC0276' '');
	  	  d.add(163,155,'UC0348 - Gerar Lan�amentos Cont�beis da Arrecada��o','execucaoBatchAction.do?casoUso=UC0348&anoMes=200601' '');
	  	  d.add(164,155,'UC0300 - Classificar Pagamentos e Devolu��es','execucaoBatchAction.do?casoUso=UC0300' '');
	  	  d.add(165,155,'UC0301 - Gerar Dados Di�rios da Arrecada��o','execucaoBatchAction.do?casoUso=UC0301' '');
	  	  d.add(166,155,'UC0343 - Gerar Resumo das Anormalidades','execucaoBatchAction.do?casoUso=UC0343' '');
	  	  d.add(167,155,'UC0352 - Emitir Contas','execucaoBatchAction.do?casoUso=UC0352&idContas=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15' '');
	  	  d.add(168,155,'UC0349 - Emitir Documento de Cobran�a','execucaoBatchAction.do?casoUso=UC0349' '');
	  	  d.add(169,155,'UC0320 - Gerar Fatura de Cliente Respons�vel','execucaoBatchAction.do?casoUso=UC0320' '');
	  	  d.add(170,155,'UC0321 - Emitir Fatura de Cliente Respons�vel','execucaoBatchAction.do?casoUso=UC0321' '');
	  	  d.add(171,147,'Micromedi��o');
	  	  d.add(173,155,'UC0343 - Gerar Resumo de Anormalidades','execucaoBatchAction.do?casoUso=UC0343' '');
          d.add(174,148,'Consultar Resumo das Situa��es Especiais de Cobran�a','exibirInformarResumoSituacaoEspecialCobrancaAction.do?menu=sim');
          d.add(175,151,'Consultar Resumo da An�lise do Faturamento','exibirInformarDadosGeracaoRelatorioConsultaAction.do?menu=sim&analiseFaturamento=ok&tipoResumo=ANALISE');
          d.add(176,155,'UC0343 - Gerar Resumo Anormalidade Consumo','execucaoBatchAction.do?casoUso=UC0343C' '');
          d.add(177,53,'Arrecada��o','#');
          d.add(178,177,'Relat�rio Resumo Arrecada��o','exibirGerarRelatorioResumoArrecadacaoAction.do?menu=sim');
          d.add(179,132,'Inserir Situa��o do Usu�rio','exibirInserirSituacaoUsuarioAction.do?menu=sim');
          d.add(180,132,'Manter Situa��o do Usu�rio','exibirManterSituacaoUsuarioAction.do?menu=sim');
          d.add(181,132,'Inserir Abrang�ncia do Usu�rio','exibirInserirAbrangenciaUsuarioAction.do?menu=sim');
          d.add(182,132,'Manter Abrang�ncia do Usu�rio','exibirManterAbrangenciaUsuarioAction.do?menu=sim');
          d.add(183,149,'Consultar Comparativo entre os Resumos do Faturamento, Arrecada��o e da Pend�ncia','exibirInformarDadosGeracaoRelatorioConsultaAction.do?menu=sim&tipoResumo=COMPARATIVORESUMOS');
          d.add(184,1,'Atendimento ao P�blico','#');
          d.add(185,184,'Liga��o �gua','#');
          d.add(186,185,'Efetuar Liga��o de �gua','exibirEfetuarLigacaoAguaAction.do?menu=sim');
          d.add(187,185,'Efetuar Corte de Liga��o de �gua','exibirEfetuarCorteLigacaoAguaAction.do?menu=sim');
          d.add(188,184,'Liga��o Esgoto','#');
          d.add(189,188,'Efetuar Liga��o de Esgoto','exibirEfetuarLigacaoEsgotoAction.do?menu=sim');
          d.add(190,155,'UC0213 - Desfazer Parcelamentos Por Entrada N�o Paga','execucaoBatchAction.do?casoUso=UC0213');
          d.add(191,132,'Bloquear Desbloquear Acesso','exibirBloquearDesbloquearAcessoUsuarioAction.do?menu=sim');
          d.add(192,184,'Hidr�metro','#');
          d.add(193,192,'Efetuar Instala��o de Hidr�metro','exibirEfetuarInstalacaoHidrometroAction.do?menu=sim');
	      d.add(194,192,'Efetuar Retirada de Hidr�metro','exibirEfetuarRetiradaHidrometroAction.do?menu=sim');
	      d.add(195,192,'Efetuar Remanejamento de Hidr�metro','exibirEfetuarRemanejamentoHidrometroAction.do?menu=sim');
	      d.add(196,185,'Efetuar Religa��o de �gua','exibirEfetuarReligacaoAguaAction.do?menu=sim');
	      d.add(197,185,'Efetuar Restabelecimento da Liga��o de �gua','exibirEfetuarRestabelecimentoLigacaoAguaAction.do?menu=sim');
	      d.add(198,188,'Efetuar Mudan�a de Situa��o de Faturamento da Liga��o de Esgoto','exibirEfetuarMudancaSituacaoFaturamentoLigacaoEsgotoAction.do?menu=sim');		
	      d.add(199,188,'Atualizar Liga��o de Esgoto','exibirAtualizarLigacaoEsgotoAction.do?menu=sim');			 
	      d.add(200,62,'Comando de A��o de Cobran�a','#');	
   	      d.add(201,9,'Substituir Consumos Anteriores','exibirSubstituirConsumoAnteriorAction.do?menu=sim&peloMenu=true');
   	      d.add(202,62,'Parcelamento','#');	
   	      d.add(203,4,'Consultar Im�vel','exibirConsultarImovelAction.do?menu=sim');
   	      d.add(204,184,'Ordem de Servi�o', '#');
		  d.add(205,204,'Inserir Equipe', 'exibirInserirEquipeAction.do?menu=sim');
	 	  d.add(206,184,'Registro Atendimento', '#');
		  d.add(207,206,'Manter Registro de Atendimento','exibirFiltrarRegistroAtendimentoAction.do?menu=sim');
		  d.add(208,185,'Atualizar Consumo M�nimo da Liga��o de �gua','exibirAtualizarConsumoMinimoLigacaoAguaAction.do?menu=sim');
		  d.add(209,204,'Manter Ordem de Servi�o','exibirFiltrarOrdemServicoAction.do?menu=sim');
		  d.add(210,192,'Atualizar Instala��o do Hidr�metro','exibirAtualizarInstalacaoHidrometroAction.do?menu=sim');
		  d.add(211,204,'Inserir Tipo Servi�o','exibirInserirServicoTipoAction.do?menu=sim');	 	  		  
		  d.add(212,204,'Inserir Tipo de Retorno da Ordem de Servi�o Referida','exibirInserirTipoRetornoOrdemServicoReferidaAction.do?menu=sim');
		  d.add(213,206,'Inserir Registro de Atendimento','exibirInserirRegistroAtendimentoAction.do?menu=sim');
		  d.add(214,204,'Inserir Tipo Perfil Servi�o','exibirInserirTipoPerfilServicoAction.do?menu=sim');
		  d.add(215,206,'Inserir Tipo Solocita��o Especifica��o','exibirInserirTipoSolicitacaoEspecificacaoAction.do?menu=sim');
		  d.add(216,192,'Efetuar Substitui��o de Hidr�metro','exibirEfetuarSubstituicaoHidrometroAction.do?menu=sim');		  
		  d.add(217,188,'Atualizar Volume M�nimo da Liga��o de Esgoto','exibirAtualizarVolumeMinimoLigacaoEsgotoAction.do?menu=sim');		  
		  d.add(218,204,'Inserir Tipo de Servi�o de Refer�ncia','exibirInserirTipoServicoReferenciaAction.do?menu=sim');
		  d.add(219,204,'Inserir Prioridade do Tipo de Servi�o','exibirInserirPrioridadeTipoServicoAction.do?menu=sim'); 
		  d.add(220,185,'Efetuar Supress�o da Liga��o de �gua', 'exibirEfetuarSupressaoLigacaoAguaAction.do?menu=sim');
		  d.add(221,204,'Inserir Material','exibirInserirMaterialAction.do?menu=sim');
		  d.add(222,185,'Efetuar Corte Administrativo da Liga��o de �gua', 'exibirEfetuarCorteAdministrativoLigacaoAguaAction.do?menu=sim');
		  d.add(223,204,'Inserir Valor de Cobran�a de Servi�o', 'exibirEfetuarValorCobrancaServicoAction.do?menu=sim');
		  d.add(224,185,'Atualizar Liga��o de �gua', 'exibirAtualizarLigacaoAguaAction.do?menu=sim');
  		  d.add(225,206,'Inserir Especifica��o Situa��o Im�vel', 'exibirInserirEspecificacaoSituacaoImovelAction.do?menu=sim');

		  d.draw();

     //-->     </script></div>
		</td>
	</tr>--%>
</table>



<%--
<menu:ddmenu x="10" y="63" name="menu" isVertical="false" selBckColor="#90c7fc" fntSize="2" fntface="Arial" selFntColor="#000000">

   <menu:ddmenu text="Tab. Auxiliares " selBckColor="#cbe5fe" colWidth="120">

      <menu:ddmenu text="Tipo Pavimento ?" rowHeight="25" colWidth="140">
        <menu:ddmenu text="Cal�ada" rowHeight="25">
           <menu:ddmenu text="Inserir" rowHeight="25" link="exibirInserirTabelaAuxiliarAction.do?tela=tipoPavimentoCalcada"/>
           <menu:ddmenu text="Manter" rowHeight="25" link="exibirManterTabelaAuxiliarAction.do?tela=tipoPavimentoCalcada"/>
        </menu:ddmenu>

        <menu:ddmenu text="Rua" rowHeight="25">
            <menu:ddmenu text="Inserir" rowHeight="25" link="exibirInserirTabelaAuxiliarAction.do?tela=tipoPavimentoRua"/>
           <menu:ddmenu text="Manter" rowHeight="25" link="exibirManterTabelaAuxiliarAction.do?tela=tipoPavimentoRua"/>
        </menu:ddmenu>
      </menu:ddmenu>

      <menu:ddmenu text="Categoria ?" rowHeight="25" colWidth="140">
           <menu:ddmenu text="Inserir" rowHeight="25" link="exibirInserirTabelaAuxiliarAbreviadaAction.do?tela=categoria"/>
           <menu:ddmenu text="Manter" rowHeight="25" link="exibirManterTabelaAuxiliarAbreviadaAction.do?tela=categoria"/>
      </menu:ddmenu>

    <menu:ddmenu text="�rea Constru�da ?" rowHeight="25" colWidth="140">
           <menu:ddmenu text="Inserir" rowHeight="25" link="exibirInserirTabelaAuxiliarFaixaAction.do?tela=areaConstruida"/>
           <menu:ddmenu text="Manter" rowHeight="25" link="exibirManterTabelaAuxiliarFaixaAction.do?tela=areaConstruida"/>
    </menu:ddmenu>

    <menu:ddmenu text="Bacia ?" rowHeight="25" colWidth="140">
           <menu:ddmenu text="Inserir" rowHeight="25" link="exibirInserirTabelaAuxiliarTipoAction.do?tela=bacia"/>
           <menu:ddmenu text="Manter" rowHeight="25" link="exibirManterTabelaAuxiliarTipoAction.do?tela=bacia"/>
    </menu:ddmenu>


   </menu:ddmenu>

   <menu:ddmenu text="Consulta" selBckColor="#cbe5fe">
      <menu:ddmenu text="Presen�a dos T�cnicos" rowHeight="35" link="exibirTelaPresencaTecnicosAction.do"/>
   </menu:ddmenu>

   <menu:ddmenu text="Relat�rios" selBckColor="#cbe5fe">
     <menu:ddmenu text="Controle de Horas" rowHeight="35" selBckColor="#cbe5fe" link="exibirFiltroRelatorioControleFrequenciaHoraTrabalhadaAction.do"/>
     <menu:ddmenu text="Horas Autorizadas" rowHeight="35" selBckColor="#cbe5fe" link="exibirFiltroRelatorioControleFrequenciaHoraAutorizadaAction.do"/>
   </menu:ddmenu>

   <menu:ddmenu text="Sair" selBckColor="#cbe5fe" link="sairAction.do" />

   <%--
   <menu:ddmenu text="Item2"  link="link2.htm" selBckColor="#cbe5fe">
      <menu:ddmenu text="Item21"  link="link21.htm" />
      <menu:ddmenu text="Item22"  link="link22.htm" />
   </menu:ddmenu>
   <menu:ddmenu text="Item2"  link="link2.htm" />

</menu:ddmenu>
--%>
