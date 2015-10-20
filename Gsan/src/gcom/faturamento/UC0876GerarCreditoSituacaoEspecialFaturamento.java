/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
 * GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * GSAN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Ara�jo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cl�udio de Andrade Lira
 * Denys Guimar�es Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fab�ola Gomes de Ara�jo
 * Fl�vio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento J�nior
 * Homero Sampaio Cavalcanti
 * Ivan S�rgio da Silva J�nior
 * Jos� Edmar de Siqueira
 * Jos� Thiago Ten�rio Lopes
 * K�ssia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * M�rcio Roberto Batista da Silva
 * Maria de F�tima Sampaio Leite
 * Micaela Maria Coelho de Ara�jo
 * Nelson Mendon�a de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corr�a Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Ara�jo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * S�vio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa � software livre; voc� pode redistribu�-lo e/ou
 * modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
 * publicada pela Free Software Foundation; vers�o 2 da
 * Licen�a.
 * Este programa � distribu�do na expectativa de ser �til, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
 * COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
 * PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
 * detalhes.
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
 * junto com este programa; se n�o, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gcom.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.bean.ApagarDadosFaturamentoHelper;
import gcom.faturamento.bean.DeterminarValoresFaturamentoAguaEsgotoHelper;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoARealizarCategoria;
import gcom.faturamento.credito.CreditoARealizarCategoriaPK;
import gcom.faturamento.credito.CreditoARealizarGeral;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;

/**
 * Esta classe tem como finalidade encapsular o caso de uso [UC0876] - Gerar Cr�dito Situa��o Especial 
 * de Faturamento, gerando maior facilidade na manuten��o do mesmo.  
 *
 * @author Raphael Rossiter
 * @date 22/01/2009
 */
public class UC0876GerarCreditoSituacaoEspecialFaturamento {

private static UC0876GerarCreditoSituacaoEspecialFaturamento instancia;
	
	@SuppressWarnings("unused")
	private IRepositorioFaturamento repositorioFaturamento;
	
	@SuppressWarnings("unused")
	private SessionContext sessionContext;

	
	private UC0876GerarCreditoSituacaoEspecialFaturamento(IRepositorioFaturamento repositorioFaturamento, 
			SessionContext sessionContext) {

		this.repositorioFaturamento = repositorioFaturamento;
		this.sessionContext = sessionContext;
	}

	public static UC0876GerarCreditoSituacaoEspecialFaturamento getInstancia(IRepositorioFaturamento repositorioFaturamento,
			SessionContext sessionContext) {
		
		if (instancia == null) {
			instancia = new UC0876GerarCreditoSituacaoEspecialFaturamento(repositorioFaturamento, sessionContext);
		}
		return instancia;
	}
	
	/**
	 * Controlador Imovel 
	 *
	 * @author Raphael Rossiter
	 * @date 22/01/2009
	 *
	 * @return ControladorImovelLocal
	 */
	protected ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Controlador Micromedicao 
	 *
	 * @author Raphael Rossiter
	 * @date 22/01/2009
	 *
	 * @return ControladorMicromedicaoLocal
	 */
	protected ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Controlador Faturamento 
	 *
	 * @author Raphael Rossiter
	 * @date 22/01/2009
	 *
	 * @return ControladorFaturamentoLocal
	 */
	protected ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Controlador Util 
	 *
	 * @author Raphael Rossiter
	 * @date 22/01/2009
	 *
	 * @return ControladorUtilLocal
	 */
	protected ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * [UC0876] - Gerar Cr�dito Situa��o Especial Faturamento
	 *
	 * @author Raphael Rossiter
	 * @date 23/01/2009
	 *
	 * @param imovel
	 * @param faturamentoGrupo
	 * @param sistemaParametro
	 * @throws ControladorException
	 */
	public void gerarCreditoSituacaoEspecialFaturamentoImovel(Imovel imovel, FaturamentoGrupo faturamentoGrupo, 
			SistemaParametro sistemaParametro,int atividade) throws ControladorException {

		/*
		 * Caso o im�vel j� tenha conta gerada para a refer�ncia de faturamento, o cr�dito n�o poder� ser gerado
		 */
		Integer idContaSituacaoAtual = this.getControladorFaturamento().
		pesquisarDebitoCreditoSituacaoAtualConta(imovel.getId(), faturamentoGrupo.getAnoMesReferencia());
		
		/*
		 * CASO O IM�VEL ESTEJA COM A SITUA��O ESPECIAL DE FATURAMENTO IGUAL 
		 * "Situa��o Especial de Nitrato" (C�digo 10) (FTST_ID da tabela IMOVEL com valor igual a 10)
		 */ 
		if (imovel.getFaturamentoSituacaoTipo() != null &&
			imovel.getFaturamentoSituacaoTipo().getId().equals(FaturamentoSituacaoTipo.NITRATO) 
			
			&& (idContaSituacaoAtual == null || idContaSituacaoAtual.equals(DebitoCreditoSituacao.PRE_FATURADA))) {

			Collection colecaoCategorias = null;
			Collection colecaoCategoriaOUSubcategoria = null;
			
			// [UC0108] - Obter Quantidade de Economias por Categoria
			colecaoCategorias = this.getControladorImovel()
			.obterQuantidadeEconomiasCategoria(imovel);
			
			//Verificando se a empresa fatura por CATEGORIA ou SUBCATEGORIA 
			if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA)){
				colecaoCategoriaOUSubcategoria = colecaoCategorias; 
			}
			else{
				
				//[UC0108] - Obter Quantidade de Economias por Subcategoria
				colecaoCategoriaOUSubcategoria = this.getControladorImovel()
				.obterQuantidadeEconomiasSubCategoria(imovel.getId());
			}
			
			//Caso a atividade seja gerar dados para leitura, � gerado um credito a realizar com o valor zerado, 
			//e a situa��o do d�bito cr�dito fica como pr�-faturada
			if(atividade == FaturamentoAtividade.GERAR_ARQUIVO_LEITURA){
				
				BigDecimal valorCredito = new BigDecimal("0.00");
				
				Integer idDebitoCreditoSituacaoAtual = DebitoCreditoSituacao.PRE_FATURADA;
					
				//GERANDO O CR�DITO A REALIZAR
				this.gerarCreditoARealizarImovel(imovel, faturamentoGrupo.getAnoMesReferencia(), valorCredito, 
				colecaoCategorias, sistemaParametro,idDebitoCreditoSituacaoAtual);
			}else{
				
				Integer idDebitoCreditoSituacaoAtual = DebitoCreditoSituacao.NORMAL;
				
				Object[] dadosCreditoARealizar = null;
				
				try{
					dadosCreditoARealizar = repositorioFaturamento.pesquisarCreditoARealizar(imovel.getId(),CreditoTipo.CREDITO_NITRATO,
							idDebitoCreditoSituacaoAtual,faturamentoGrupo.getAnoMesReferencia());
				
				} catch (ErroRepositorioException ex) {
					sessionContext.setRollbackOnly();
					new ControladorException("erro.sistema", ex);
				}
				
				//Caso o cr�dito n�o tenha sido atualizado, n�o tenha sido atualizado o cr�dito de nitrato por impress�o simult�nea,
				//atualiza o cr�dito de nitrato.
				if(dadosCreditoARealizar == null || dadosCreditoARealizar.equals("") ){

					//Inicializando o objeto que armazenar� as informa��es que ser�o utilizadas no c�lculo da conta
					DeterminarValoresFaturamentoAguaEsgotoHelper helperValoresAguaEsgoto = 
					new DeterminarValoresFaturamentoAguaEsgotoHelper();
					
					
					//Obtendo os consumos de �gua e esgoto do im�vel a ser faturado
					
					//LIGACAO_TIPO_AGUA
					LigacaoTipo ligacaoTipoAgua = new LigacaoTipo();
					ligacaoTipoAgua.setId(LigacaoTipo.LIGACAO_AGUA);
		
					// LIGACAO_TIPO_ESGOTO
					LigacaoTipo ligacaoTipoEsgoto = new LigacaoTipo();
					ligacaoTipoEsgoto.setId(LigacaoTipo.LIGACAO_ESGOTO);
					
					//CONSUMO_HISTORICO_AGUA
					ConsumoHistorico consumoHistoricoAgua = this.getControladorMicromedicao()
					.obterConsumoHistoricoMedicaoIndividualizada(imovel, ligacaoTipoAgua, 
					faturamentoGrupo.getAnoMesReferencia());
					
					Integer consumoAgua = null;
					ConsumoTipo consumoTipoAgua = null;
					
					if (consumoHistoricoAgua != null){
						consumoAgua = consumoHistoricoAgua.getNumeroConsumoFaturadoMes();
						consumoTipoAgua = consumoHistoricoAgua.getConsumoTipo(); 
					}
					
					//CONSUMO_HISTORICO_ESGOTO
					ConsumoHistorico consumoHistoricoEsgoto = this.getControladorMicromedicao()
					.obterConsumoHistoricoMedicaoIndividualizada(imovel, ligacaoTipoEsgoto, 
					faturamentoGrupo.getAnoMesReferencia());
					
					Integer consumoEsgoto = null;
					ConsumoTipo consumoTipoEsgoto = null;
					
					if (consumoHistoricoEsgoto != null){
						consumoEsgoto = consumoHistoricoEsgoto.getNumeroConsumoFaturadoMes();
						consumoTipoEsgoto = consumoHistoricoEsgoto.getConsumoTipo();
					}
					
					
					if (this.getControladorFaturamento().permiteFaturamentoParaAgua(imovel.getLigacaoAguaSituacao(), 
						consumoAgua, consumoTipoAgua) ||
						this.getControladorFaturamento().permiteFaturamentoParaEsgoto(imovel.getLigacaoEsgotoSituacao(), 
						consumoEsgoto, consumoTipoEsgoto)) {
						
						//Determinar Valores para Faturamento de �gua e/ou Esgoto
						helperValoresAguaEsgoto = this.getControladorFaturamento()
						.determinarValoresFaturamentoAguaEsgoto(imovel, faturamentoGrupo.getAnoMesReferencia(), 
						colecaoCategoriaOUSubcategoria, faturamentoGrupo, consumoHistoricoAgua, consumoHistoricoEsgoto);
						
					}
					
					/*
					 * O sistema calcula 50% do valor calculado da �gua e gera um cr�dito a realizar para cada 
					 * um dos im�veis selecionados
					 */
					if (helperValoresAguaEsgoto.getValorTotalAgua().compareTo(ConstantesSistema.VALOR_ZERO) == 1) {
							
						BigDecimal valorCredito = 
						helperValoresAguaEsgoto.getValorTotalAgua().divide(new BigDecimal(2));
						
						//GERANDO O CR�DITO A REALIZAR
						this.gerarCreditoARealizarImovel(imovel, faturamentoGrupo.getAnoMesReferencia(), valorCredito, 
						colecaoCategorias, sistemaParametro,idDebitoCreditoSituacaoAtual);
					}
				}
			}
		}
	}
	
	/**
	 * [UC0876] - Gerar Cr�dito Situa��o Especial Faturamento
	 *
	 * @author Raphael Rossiter
	 * @date 23/01/2009
	 *
	 * @param imovel
	 * @param anoMesFaturamento
	 * @param valorCredito
	 * @param colecaoCategoria
	 * @param sistemaParametro
	 * @throws ControladorException
	 */
	protected void gerarCreditoARealizarImovel(Imovel imovel, Integer anoMesFaturamento, 
			BigDecimal valorCredito, Collection colecaoCategoria, SistemaParametro sistemaParametro,int idDebitoCreditoSituacaoAtual) throws ControladorException{
		
		//CREDITO_A_REALIZAR_GERAL
		//=====================================================================================================
		CreditoARealizarGeral creditoARealizarGeral = new CreditoARealizarGeral();
		
		creditoARealizarGeral.setIndicadorHistorico(ConstantesSistema.NAO);
		creditoARealizarGeral.setUltimaAlteracao(new Date());
		
		//INSERINDO NA BASE
		Integer idCreditoARealizarGeral = (Integer) this
		.getControladorUtil().inserir(creditoARealizarGeral);
		
		creditoARealizarGeral.setId(idCreditoARealizarGeral);
		//=====================================================================================================
		
		//CREDITO_A_REALIZAR
		//=====================================================================================================
		CreditoARealizar creditoARealizar = new CreditoARealizar();
		
		creditoARealizar.setCreditoARealizarGeral(creditoARealizarGeral);
		creditoARealizar.setId(idCreditoARealizarGeral);
	
		//IMOVEL
		creditoARealizar.setImovel(imovel);
		
		//CREDITO_TIPO
		CreditoTipo creditoTipo = new CreditoTipo();
		creditoTipo.setId(CreditoTipo.CREDITO_NITRATO);
		
		creditoARealizar.setCreditoTipo(creditoTipo);
		
		//GERACAO_CREDITO
		creditoARealizar.setGeracaoCredito(new Date());
		
		//REFERENCIA_CREDITO
		creditoARealizar.setAnoMesReferenciaCredito(anoMesFaturamento);
		
		//REFERENCIA COBRANCA
		creditoARealizar.setAnoMesCobrancaCredito(sistemaParametro.getAnoMesArrecadacao());
		
		//REFERENCIA CONTABIL
		creditoARealizar.setAnoMesReferenciaContabil(sistemaParametro.getAnoMesFaturamento());
		
		//VALOR CREDITO
		creditoARealizar.setValorCredito(valorCredito);
		
		//VALOR RESIDUAL MES ANTERIOR
		creditoARealizar.setValorResidualMesAnterior(BigDecimal.ZERO);
		
		//NUMERO PRESTACAO
		creditoARealizar.setNumeroPrestacaoCredito(new Short("1"));
		
		//PRESTACOES REALIZADAS
		creditoARealizar.setNumeroPrestacaoRealizada(new Short("0"));
		
		//LOCALIDADE
		creditoARealizar.setLocalidade(imovel.getLocalidade());
		
		//QUADRA
		creditoARealizar.setQuadra(imovel.getQuadra());
		
		//CODIGO_SETOR_COMERCIAL
		creditoARealizar.setCodigoSetorComercial(imovel.getSetorComercial().getCodigo());
		
		//NUMERO_QUADRA
		creditoARealizar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
		
		//LOTE
		creditoARealizar.setNumeroLote(imovel.getLote());
		
		//SUBLOTE
		creditoARealizar.setNumeroSubLote(imovel.getSubLote());
		
		//LANCAMENTO_ITEM_CONTABIL
		LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
		lancamentoItemContabil.setId(LancamentoItemContabil.ACRESCIMOS_POR_IMPONTUALIDADE);
		
		creditoARealizar.setLancamentoItemContabil(lancamentoItemContabil);
		
		//DEBITO_CREDITO_SITUACAO_ATUAL
		DebitoCreditoSituacao debitoCreditoSituacaoAtual = new DebitoCreditoSituacao();
		debitoCreditoSituacaoAtual.setId(idDebitoCreditoSituacaoAtual);
		
		creditoARealizar.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtual);
		
		//CREDITO_ORIGEM
		CreditoOrigem creditoOrigem = new CreditoOrigem();
		creditoOrigem.setId(CreditoOrigem.SERVICOS_INDIRETOS_PAGOS_INDEVIDAMENTE);
		
		creditoARealizar.setCreditoOrigem(creditoOrigem);
		
		creditoARealizar.setUltimaAlteracao(new Date());
		
		//INSERINDO NA BASE
		this.getControladorUtil().inserir(creditoARealizar);
		//=====================================================================================================
		
		
		//CREDITO_A_REALIZAR_CATEGORIA
		//=====================================================================================================
		
		if (colecaoCategoria != null && !colecaoCategoria.isEmpty()){
			
			Iterator iteratorCategoria = colecaoCategoria.iterator();
			
			//[UC0185] - Obter Valor por Categoria
			Collection colecaoValorPorCategoria = this.getControladorImovel()
			.obterValorPorCategoria(colecaoCategoria, valorCredito);
			
			Iterator iteratorValorPorCategoria = colecaoValorPorCategoria.iterator();
			
			while (iteratorCategoria.hasNext()){
				
				Categoria categoria = (Categoria) iteratorCategoria.next();
				
				BigDecimal valorPorCategoria = (BigDecimal) iteratorValorPorCategoria.next();
				
				CreditoARealizarCategoria creditoARealizarCategoria = new CreditoARealizarCategoria();
				
				//PK
				CreditoARealizarCategoriaPK creditoARealizarCategoriaPK = new CreditoARealizarCategoriaPK();
				creditoARealizarCategoriaPK.setCategoriaId(categoria.getId());
				creditoARealizarCategoriaPK.setCreditoARealizarId(creditoARealizar.getId());
				
				creditoARealizarCategoria.setComp_id(creditoARealizarCategoriaPK);
				
				//CREDITO_A_REALIZAR
				creditoARealizarCategoria.setCreditoARealizar(creditoARealizar);
				
				//CATEGORIA
				creditoARealizarCategoria.setCategoria(categoria);
				
				//QUANTIDADE_ECONOMIAS_CATEGORIA
				creditoARealizarCategoria.setQuantidadeEconomia(categoria.getQuantidadeEconomiasCategoria());
				
				//VALOR_POR_CATEGORIA
				creditoARealizarCategoria.setValorCategoria(valorPorCategoria);
				
				creditoARealizarCategoria.setUltimaAlteracao(new Date());
				
				//INSERINDO NA BASE
				this.getControladorUtil().inserir(creditoARealizarCategoria);
				
			}
		}
		//=====================================================================================================
	}
	
	
	/**
	 * [UC0876] - Gerar Cr�dito Situa��o Especial Faturamento
	 *
	 * @author Raphael Rossiter
	 * @date 27/01/2009
	 *
	 * @param helper
	 * @throws ControladorException
	 */
	public void apagarDadosGeradosCreditoSituacaoEspecialFaturamento(ApagarDadosFaturamentoHelper helper) 
		throws ControladorException {

			
		/* 
		 * Retorna a quantidade de cr�ditos a realizar existentes para uma rota em um determinado anoM�s de 
		 * refer�ncia e de acordo com o tipo de cr�dito recebido.
		 */
		Integer quantidadeCreditosARealizar = null;
		try {
				
			quantidadeCreditosARealizar = repositorioFaturamento.quantidadeCreditosARealizarRota(
			helper.getAnoMesFaturamento(), helper.getRota(), helper.getIdCreditoTipo());
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (quantidadeCreditosARealizar != null && quantidadeCreditosARealizar.intValue() > 0) {

			/*
			 * Caso a situa��o atual N�O seja PR�-FATURAMENTO:
			 * 
			 * DELETAR apenas os cr�ditos com data de gera��o entre a data atual menos 3 dias e a data atual
			 * Desenvolvedor: Raphael Rossiter em 27/01/2009 
			 * Analista: Aryed Lins
			 */
			if (!helper.getIdDebitoCreditoSituacaoAtual().equals(DebitoCreditoSituacao.PRE_FATURADA)){
					
				helper.setDataEmissaoInicial(Util.subtrairNumeroDiasDeUmaData(new Date(), 3));
				helper.setDataEmissaoFinal(Util.adaptarDataFinalComparacaoBetween(new Date()));
			}
				
			try {
				
				// deleta CREDITO_REALIZADO_CATEGORIA
				repositorioFaturamento.apagarCreditoRealizadoCategoriaNitrato(helper);

				// deleta CREDITO_REALIZADO
				repositorioFaturamento.apagarCreditoRealizadoNitrato(helper);
					
				// deleta CREDITO_A_REALIZAR_CATEGORIA
				repositorioFaturamento.apagarCreditoARealizarCategoria(helper);
				
				// update CREDITO_A_REALIZAR_GERAL
				repositorioFaturamento.atualizarCreditoARealizarGeral(helper);

				// deleta CREDITO_A_REALIZAR
				repositorioFaturamento.apagarCreditoARealizar(helper);

				// delete CREDITO_A_REALIZAR_GERAL
				repositorioFaturamento.apagarCreditoARealizarGeral(helper);

			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}
		} 
	}
}
