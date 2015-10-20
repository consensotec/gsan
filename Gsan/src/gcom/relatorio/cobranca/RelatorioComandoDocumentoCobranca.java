package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.RelatorioComandoDocumentoCobrancaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioComandoDocumentoCobranca extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioComandoDocumentoCobranca(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITO);
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Integer idCobrancaAcaoCronograma = (Integer) getParametro("idCobrancaAcaoCronograma");
		Integer idCobrancaAcaoComando = (Integer)  getParametro("idCobrancaAcaoComando");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		CobrancaAcao cobrancaAcao = (CobrancaAcao) getParametro("cobrancaAcao");
		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		// RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		RelatorioComandoDocumentoCobrancaBean relatorioComandoDocumentoCobrancaBean = null;
		Collection colecaoComandoDocumentoCobrancaHelper = fachada
				.gerarRelatorioComandoDocumentoCobranca(
						idCobrancaAcaoCronograma, idCobrancaAcaoComando);

		Iterator colecaoComandoDocumentoCobrancaHelperIterator = colecaoComandoDocumentoCobrancaHelper
				.iterator();

		boolean flagTerminou = false;

		int totalContas = colecaoComandoDocumentoCobrancaHelper.size();
		int quantidadeContas = 0;

		while (!flagTerminou) {

			RelatorioComandoDocumentoCobrancaHelper comandoDocumentoCobrancaHelperPrimeiraConta = null;
			RelatorioComandoDocumentoCobrancaHelper comandoDocumentoCobrancaHelperSegundaConta = null;

			while (colecaoComandoDocumentoCobrancaHelperIterator.hasNext()) {

				RelatorioComandoDocumentoCobrancaHelper comandoDocumentoCobrancaHelper = (RelatorioComandoDocumentoCobrancaHelper) colecaoComandoDocumentoCobrancaHelperIterator
						.next();

				if (comandoDocumentoCobrancaHelperPrimeiraConta == null) {
					comandoDocumentoCobrancaHelperPrimeiraConta = comandoDocumentoCobrancaHelper;
				} else {
					comandoDocumentoCobrancaHelperSegundaConta = comandoDocumentoCobrancaHelper;
				}

				if (comandoDocumentoCobrancaHelperPrimeiraConta != null
						&& comandoDocumentoCobrancaHelperSegundaConta != null) {

					relatorioComandoDocumentoCobrancaBean = new RelatorioComandoDocumentoCobrancaBean(
							comandoDocumentoCobrancaHelperPrimeiraConta,
							comandoDocumentoCobrancaHelperSegundaConta);

					relatorioBeans.add(relatorioComandoDocumentoCobrancaBean);

					comandoDocumentoCobrancaHelperPrimeiraConta = null;
					comandoDocumentoCobrancaHelperSegundaConta = null;
				}

				quantidadeContas++;

				if (quantidadeContas == totalContas) {
					break;
				}

			}

			// Caso tenha sobrado apenas uma conta
			if (comandoDocumentoCobrancaHelperPrimeiraConta != null) {

				comandoDocumentoCobrancaHelperSegundaConta = new RelatorioComandoDocumentoCobrancaHelper();
				comandoDocumentoCobrancaHelperSegundaConta.setInscricao(null);

				relatorioComandoDocumentoCobrancaBean = new RelatorioComandoDocumentoCobrancaBean(
						comandoDocumentoCobrancaHelperPrimeiraConta,
						comandoDocumentoCobrancaHelperSegundaConta);

				relatorioBeans.add(relatorioComandoDocumentoCobrancaBean);
			}

			if (quantidadeContas == totalContas) {
				flagTerminou = true;
			}

		}
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		
		if ( relatorioBeans.size() <= 0  ) {
		
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");
		
		
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (sistemaParametro.getNumeroTelefone() != null
				&& !sistemaParametro.getNumeroTelefone().equals("")) {
			parametros.put("telefoneGeral", Util
					.formatarTelefone(sistemaParametro.getNumeroTelefone()));

		} else {
			parametros.put("telefoneGeral", "");
		}
		
		if (sistemaParametro.getCep() != null
				&& sistemaParametro.getCep().getCodigo() != null) {
			parametros.put("cep", Util.formatarCEP(sistemaParametro.getCep()
					.getCodigo().toString()));
		} else {
			parametros.put("cep", "");
		}

		if (sistemaParametro.getCnpjEmpresa() != null
				&& !sistemaParametro.getCnpjEmpresa().equals("")) {

			parametros.put("cnpj", Util.formatarCnpj(sistemaParametro
					.getCnpjEmpresa()));
		} else {
			parametros.put("cnpj", "");
		}
		
		if(colecaoComandoDocumentoCobrancaHelper!=null && !colecaoComandoDocumentoCobrancaHelper.isEmpty()){

		String endereco = sistemaParametro.getEnderecoFormatado();
			parametros.put("endereco", endereco);
		}
		
		parametros.put("nomeEmpresa", sistemaParametro.getNomeEmpresa());

		if (sistemaParametro.getNomeAbreviadoEmpresa() != null) {
			parametros.put("nomeAbreviadoEmpresa", sistemaParametro
					.getNomeAbreviadoEmpresa());
		} else {
			parametros.put("nomeAbreviadoEmpresa", "");
		}

		if (sistemaParametro.getNumero0800Empresa() != null) {
			parametros.put("numeroAtendimento", sistemaParametro
					.getNumero0800Empresa());
		}else{
			parametros.put("numeroAtendimento", "");
		}
		if (cobrancaAcao != null) {
			parametros.put("acaoCobranca", cobrancaAcao
					.getDescricaoCobrancaAcao());
		}else{
			parametros.put("acaoCobranca", "");
		}
		
		String informacao = "";

		if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
				SistemaParametro.EMPRESA_SAAE)) {
			informacao = "	Nosso objetivo � fornecer o que h� de melhor em atendimento e qualidade no "
					+ "fornecimento de �gua para seu im�vel, o que somente ser� alcan�ado com o pagamento em dia "
					+ "das contas de �gua. \n"
					+ "	Ocorre que, o atraso no pagamento de sua conta de �gua, bem como a falta "
					+ "de atendimento aos nossos comunicados quanto ao referido atraso, nos levaram "
					+ "a adotar a medida cab�vel no caso, ou seja o corte do fornecimento de �gua para seu im�vel. \n"
					+ "	Lembramos que a religa��o indevida do ramal predial � infra��o prevista no "
					+ "Regulamento de Servi�o prestados pelo SAAE, pass�vel de multa. "
					+ "Al�m disso a referida infra��o constitui crimes de furto e dano, "
					+ "previstos nos artigos 155 e 163 do C�digo Penal, o que os levar� a "
					+ "providenciar a imediata lavraturado do Boletim de Ocorr�ncia, com vistas a "
					+ "ado��o das medidas cab�veis ao caso. "
					+ "	Face ao exposto, gentilmente, solicitamos, mais uma vez, o seu "
					+ "comparecimento nesta Empresa, a fim de firmarmos um acordo que lhe "
					+ "permita efetuar o pagamento dos d�bitos em atraso. "
					+ "Atenciosamente, ";
		} else if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
				SistemaParametro.EMPRESA_CAER)) {
			informacao = "   Nosso objetivo � fornecer o que h� de melhor em atendimento e qualidade no fornecimento de �gua para seu im�vel, "
					+ "o que somente ser� alcan�ado com o pagamento em dia das contas de �gua. \n"
					+ "	Ocorre que, o atraso no pagamento de sua conta de �gua, bem como a falta de atendimento aos nossos comunicados quanto ao "
					+ "referido atraso, nos levaram a adotar a medida cab�vel no caso, ou seja o corte do fornecimento de �gua para seu im�vel. \n"
					+ "	Lembramos que a religa��o indevida do ramal predial � infra��o prevista no decreto n.� 4.784-E de 23 de maio de 2002 "
					+ "do Regulamento de Servi�o prestados pela CAER, pass�vel de multa equivalente a 12(doze) vezes a taxa m�nima, hoje "
					+ "perfazendo o total de R$ 90,72(noventa reais e setenta e dois centavos). Al�m disso a referida infra��o constitui crimes "
					+ "de furto e dano, previstos nos artigos 155 e 163 do C�digo Penal, o que os levar� a providenciar a imediata lavraturado do "
					+ "	Boletim de Ocorr�ncia, com vistas a ado��o das medidas cab�veis ao caso. \n"
					+ "	Face ao exposto, gentilmente, solicitamos, mais uma vez, o seu comparecimento "
					+ "nesta Empresa, a fim de firmarmos um acordo que lhe permita efetuar o pagamento dos d�bitos em atraso. "
					+ "Atenciosamente, ";
		}else if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
			SistemaParametro.EMPRESA_COSANPA)) {
			informacao = "  A viola��o do dispositivo de suspens�o (corte) do ramal predial de �gua ou de esgoto constitui infra��o cujo "
				+ "valor multa � de R$ 86,40 e, em caso de reincid�ncia, supress�o imediata do ramal predial de abastecimento de "
				+ "�gua ou coleta de esgoto sanit�rio. \n"
				+ "	Lembramos que a religa��o indevida do ramal predial � infra��o prevista no "
				+ "Regulamento de Servi�o prestados pela COSANPA. "
				+ "Al�m disso a referida infra��o constitui crimes de furto e dano, "
				+ "previstos nos artigos 155 e 163 do C�digo Penal, o que nos levar� a "
				+ "providenciar a imediata lavratura do Boletim de Ocorr�ncia Policial, com vistas a "
				+ "ado��o das medidas cab�veis ao caso. \n"
				+ "	Face ao exposto, gentilmente, solicitamos, mais uma vez, o seu "
				+ "comparecimento nesta Empresa, a fim de firmarmos um acordo que lhe "
				+ "permita efetuar o pagamento dos d�bitos em atraso. \n"
				+ " Em caso de Execu��o de Corte em que houver a necessidade de intervir na cal�ada ou meio fio o custo de "
				+ "recomposi��o ser� cobrado do cliente. \n \n"
				+ "Atenciosamente, ";
		} else if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
			SistemaParametro.EMPRESA_COSAMA)) {
			
			informacao = " Considerando o n�o pagamento do(s) d�bito(s) do im�vel supracitado at� a presente data, em cumprimento �s disposi��es regulamentares vigentes, "
					+ " procedemos o desligamento da liga��o de �gua. Em consequ�ncia, avisamos que: \n"
					+ " a) O n�o pagamento desta fatura at� a data do vencimento, poder� ocasionar a inscri��o do nome do respons�vel no SPC/SERASA; \n"
					+ " b) O prazo para religa��o � de at� 5 dias �teis; \n"
					+ " c) O prazo para retirada do nome inscrito no SPC/SERASA � de, no m�ximo 5 dias �teis ap�s a compensa��o do pagamento. ";
		}

		parametros.put("informacao", informacao);

		
		if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(SistemaParametro.EMPRESA_COSANPA)) {
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_COMANDO_DOCUMENTO_COBRANCA_COSANPA,
			parametros, ds, tipoFormatoRelatorio);
		}
		else{
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_COMANDO_DOCUMENTO_COBRANCA,
			parametros, ds, tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_COMANDO_DOCUMENTO_COBRANCA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioComandoDocumentoCobranca",
				this);
	}

}