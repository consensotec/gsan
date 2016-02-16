
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
package gcom.relatorio.cobranca.spcserasa;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.NegativadorMovimentoRegParcelamento;
import gcom.cobranca.NegativadorMovimentoRegRetMot;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cobranca.spcserasa.RelatorioAcompanhamentoClientesNegativadosHelper;
import gcom.gui.cobranca.spcserasa.RelatorioAcompanhamentoClientesNegativadosSomatorioDadosParcelamentoHelper;
import gcom.relatorio.ConstantesExecucaoRelatorios;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.FiltroNegativadorMovimentoRegRetMot;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Yara Taciane
 * @created 18 de mar�o de 2008
 * @version 1.0
 */

public class RelatorioAcompanhamentoClientesNegativados extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioAcompanhamentoClientesNegativados object
	 */
	public RelatorioAcompanhamentoClientesNegativados(Usuario usuario, String constanteRelatorio) {
		super(usuario, constanteRelatorio);
	}

	@Deprecated
	public RelatorioAcompanhamentoClientesNegativados() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param Negativador Parametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// Recebe os par�metros que ser�o utilizados no relat�rio	
		DadosConsultaNegativacaoHelper parametrosHelper = (DadosConsultaNegativacaoHelper) getParametro("parametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio

		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioAcompanhamentoClientesNegativadosBean relatorioBean = null;

		// Nova consulta para trazer objeto completo
		Collection colecaoNovos = fachada.pesquisarRelatorioAcompanhamentoClientesNegativador(parametrosHelper);

		if (colecaoNovos != null && !colecaoNovos.isEmpty()) {
			// coloca a cole��o de par�metros da analise no iterator
			
			/////////////////////////////////////////////
			BigDecimal valorParceladoTotalLoc = BigDecimal.ZERO;
			BigDecimal valorParceladoEntradaTotalLoc = BigDecimal.ZERO;    
			BigDecimal valorParceladoEntradaPagoTotalLoc = BigDecimal.ZERO;
			Integer numeroPrestacoesTotalLoc = 0;
			Integer numeroPrestacoesCobradasPagasTotalLoc = 0;
			BigDecimal valorParceladoCobradoPagoTotalLoc = BigDecimal.ZERO;    
			Integer numeroPrestacoesCobradasNaoPagasTotalLoc = 0;
			BigDecimal valorParceladoCobradoNaoPagoTotalLoc = BigDecimal.ZERO;
			Integer numeroPrestacoesNaoCobradasTotalLoc = 0;
			BigDecimal valorParceladoNaoCobradoTotalLoc = BigDecimal.ZERO;
			BigDecimal valorParceladoCanceladoTotalLoc = BigDecimal.ZERO;
			String idLocalidadeAnterior = "";
			String periodoEnvioNegativacaoAnterior = "";
			String situacaoAnterior = "";
			String localidadeAnterior = "";
			////////////////////////////////////////////////////
			
			NegativadorMovimentoReg negativadorMovimentoReg = null;
			Short indicadorExisteDadosParcelamento = ConstantesSistema.NAO;
			String idNegativador = "";
			String negativador = "";
			String nomeCliente = "";
			String periodoEnvioNegativacao = "";
			String localidade = "";
			String idLocalidade = "";
			String situacao = "";
			String matricula = "";
			String cpfCnpj = "";
			Double valorNegativado = new Double(0);	
			BigDecimal valorPago = BigDecimal.ZERO;	
			BigDecimal valorCancelado = BigDecimal.ZERO;
			Collection colecaoDadosParcelamentoBean = new ArrayList();
			BigDecimal valorPendente = new BigDecimal(0);		
			boolean primeiraVez = true;
			
			String idGerenciaRegional = "";
			String gerenciaRegional = "";
			String idUnidadeNegocio = "";
			String unidadeNegocio = "";
			String idGerenciaRegionalAnterior = "";
			String idUnidadeNegocioAnterior = "";
			String gerenciaRegionalAnterior = "";
			String unidadeNegocioAnterior = "";
			
			RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean  parametrosSelecionadosBean = retornaParametrosSelecionaodos(parametrosHelper);
			
			Iterator it  = colecaoNovos.iterator();
			while(it.hasNext()){
				
				RelatorioAcompanhamentoClientesNegativadosHelper helper = (RelatorioAcompanhamentoClientesNegativadosHelper)it.next();
				negativadorMovimentoReg = helper.getNegativadorMovimentoReg();
				Short indicadorExcluidoNgim = helper.getIndicadorExcluidoNgim();
				
				Imovel imovel = new Imovel();
				imovel = negativadorMovimentoReg.getImovel();
				//matricula = imovel.getId().toString();
				
				boolean limpaValoresParc = false;
				// data de processamento
				periodoEnvioNegativacao = "";
				if (negativadorMovimentoReg != null	&& negativadorMovimentoReg.getNegativadorMovimento() != null) {
					periodoEnvioNegativacao = Util.formatarData(negativadorMovimentoReg.getNegativadorMovimento().getDataProcessamentoEnvio());
				}
				
				//localidade				
				localidade = "";
				idLocalidade = "";
				idGerenciaRegional = "";
				gerenciaRegional = "";
				idUnidadeNegocio = "";
				unidadeNegocio = "";
				if (negativadorMovimentoReg.getLocalidade() != null) {			
					idLocalidade = negativadorMovimentoReg.getLocalidade().getId().toString();
					localidade = negativadorMovimentoReg.getLocalidade().getDescricao();
					
					// Gerencia Regional
					if (negativadorMovimentoReg.getLocalidade().getGerenciaRegional() != null) {
						idGerenciaRegional = negativadorMovimentoReg.getLocalidade().getGerenciaRegional().getId().toString();
						gerenciaRegional = negativadorMovimentoReg.getLocalidade().getGerenciaRegional().getNome();
					}
					
					// Unidade de Negocio
					if (negativadorMovimentoReg.getLocalidade().getUnidadeNegocio() != null) {
						idUnidadeNegocio = negativadorMovimentoReg.getLocalidade().getUnidadeNegocio().getId().toString();
						unidadeNegocio = negativadorMovimentoReg.getLocalidade().getUnidadeNegocio().getNome();
					}
					
				}
				
				//se a data de processamento anterior for diferente da atual ou
				//se a localidade anterior for diferente da atual
				if(!periodoEnvioNegativacaoAnterior.equals(periodoEnvioNegativacao)||
						!idGerenciaRegionalAnterior.equals(idGerenciaRegional) ||
						!idUnidadeNegocioAnterior.equals(idUnidadeNegocio)||
						!idLocalidadeAnterior.equals(idLocalidade)){
					limpaValoresParc = true;
				}
				
				if(!primeiraVez){
					//Inicializa o construtor constitu�do dos campos
					// necess�rios para a impress�o do relatorio
					
					if(!limpaValoresParc){
						//dados da mesma localidade, os totais por localidade recebem zero
						relatorioBean = new RelatorioAcompanhamentoClientesNegativadosBean(
								nomeCliente, 
							    matricula,
							    cpfCnpj,
							    valorNegativado,
							    valorPago, 
							    valorCancelado,
							    valorPendente,
							    situacao,
							    periodoEnvioNegativacaoAnterior,
							    localidadeAnterior, 
							    idLocalidadeAnterior,
							    idNegativador,
							    negativador,
							    colecaoDadosParcelamentoBean,
							    indicadorExisteDadosParcelamento.toString(),
							    parametrosSelecionadosBean,
							    BigDecimal.ZERO,
							    BigDecimal.ZERO,
							    BigDecimal.ZERO, 
								0, 
								0,
								BigDecimal.ZERO, 
								0,
								BigDecimal.ZERO,
								0,
								BigDecimal.ZERO, 
								BigDecimal.ZERO);
						
						relatorioBean.setIdGerenciaRegional(idGerenciaRegionalAnterior);
						relatorioBean.setGerenciaRegional(gerenciaRegionalAnterior);
						relatorioBean.setIdUnidadeNegocio(idUnidadeNegocioAnterior);
						relatorioBean.setUnidadeNegocio(unidadeNegocioAnterior);
					}else{
						//mudou de localidade, totais por localidade recebem os valores calculados 
						relatorioBean = new RelatorioAcompanhamentoClientesNegativadosBean(
								nomeCliente, 
							    matricula,
							    cpfCnpj,
							    valorNegativado,
							    valorPago, 
							    valorCancelado,
							    valorPendente,
							    situacao,
							    periodoEnvioNegativacaoAnterior,
							    localidadeAnterior, 
							    idLocalidadeAnterior,
							    idNegativador,
							    negativador,
							    colecaoDadosParcelamentoBean,
							    indicadorExisteDadosParcelamento.toString(),
							    parametrosSelecionadosBean,
							    valorParceladoTotalLoc,
							    valorParceladoEntradaTotalLoc,
								valorParceladoEntradaPagoTotalLoc, 
								numeroPrestacoesTotalLoc, 
								numeroPrestacoesCobradasPagasTotalLoc,
								valorParceladoCobradoPagoTotalLoc, 
								numeroPrestacoesCobradasNaoPagasTotalLoc,
								valorParceladoCobradoNaoPagoTotalLoc,
								numeroPrestacoesNaoCobradasTotalLoc,
								valorParceladoNaoCobradoTotalLoc, 
								valorParceladoCanceladoTotalLoc);
						
						relatorioBean.setIdGerenciaRegional(idGerenciaRegionalAnterior);
						relatorioBean.setGerenciaRegional(gerenciaRegionalAnterior);
						relatorioBean.setIdUnidadeNegocio(idUnidadeNegocioAnterior);
						relatorioBean.setUnidadeNegocio(unidadeNegocioAnterior);
					}
					
					// --------------------------------
					if (negativadorMovimentoReg.getIndicadorAceito() != null && 
						negativadorMovimentoReg.getIndicadorAceito().equals(new Short("2"))) {
						
						FiltroNegativadorMovimentoRegRetMot filtroMotivoRejeicao = new FiltroNegativadorMovimentoRegRetMot();
						filtroMotivoRejeicao.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegRetMot.NEGATIVADOR_RETORNO_MOTIVO);
						filtroMotivoRejeicao.adicionarParametro(new ParametroSimples(
								FiltroNegativadorMovimentoRegRetMot.NEGATIVADOR_MOVIMENTO_REG_ID, negativadorMovimentoReg.getId()));
						
						if (parametrosHelper.getColecaoMotivoRejeicao() != null && !parametrosHelper.getColecaoMotivoRejeicao().isEmpty()) {
							boolean consulta = true;
							if(parametrosHelper.getColecaoMotivoRejeicao().size() == 1){
								Iterator iter = parametrosHelper.getColecaoMotivoRejeicao().iterator();
								while(iter.hasNext()){
									NegativadorRetornoMotivo obj = (NegativadorRetornoMotivo) iter.next();
									if(obj != null && obj.getId() == -1){
										consulta = false;
									}
								}	
							}
							
							if(consulta){	
								Collection<Integer> colecaoIdsNegativadorMovimentoRegRetMot = new ArrayList<Integer>();
								for (NegativadorRetornoMotivo motivo : (Collection<NegativadorRetornoMotivo>) parametrosHelper.getColecaoMotivoRejeicao() ) {
									if(motivo != null && motivo.getId() != -1){
										colecaoIdsNegativadorMovimentoRegRetMot.add(motivo.getId());
									}
								}
								filtroMotivoRejeicao.adicionarParametro(new ParametroSimplesIn(
										FiltroNegativadorMovimentoRegRetMot.NEGATIVADOR_RETORNO_MOTIVO_ID, 
													colecaoIdsNegativadorMovimentoRegRetMot));
							}
							
						}

						Collection<NegativadorMovimentoRegRetMot> pesquisa = fachada.pesquisar(filtroMotivoRejeicao, NegativadorMovimentoRegRetMot.class.getName());
						if (pesquisa != null && !pesquisa.isEmpty()) {
							String linhaMotivos = "";

							Iterator<NegativadorMovimentoRegRetMot> motivosRejeicao = pesquisa.iterator();
							while (motivosRejeicao.hasNext()) {
								NegativadorMovimentoRegRetMot motivoRejeicao = motivosRejeicao.next();
								linhaMotivos += motivoRejeicao.getNegativadorRetornoMotivo().getCodigoRetornoMotivo() +
										"-" + motivoRejeicao.getNegativadorRetornoMotivo().getDescricaoRetornocodigo();
								
								if (motivosRejeicao.hasNext()) {
									linhaMotivos += ", ";
								}
							}
							
							relatorioBean.setMotivosRejeicao(linhaMotivos);
						}
					}
					// --------------------------------
					
					//adiciona o bean a cole��o
					relatorioBeans.add(relatorioBean);
				}
				
				periodoEnvioNegativacaoAnterior = periodoEnvioNegativacao;
				idLocalidadeAnterior = idLocalidade;
				localidadeAnterior = localidade;
				idUnidadeNegocioAnterior = idUnidadeNegocio;
				idGerenciaRegionalAnterior = idGerenciaRegional;
				unidadeNegocioAnterior = unidadeNegocio;
				gerenciaRegionalAnterior = gerenciaRegional;
				primeiraVez = false;
				
				indicadorExisteDadosParcelamento = ConstantesSistema.NAO; 
				
				// Faz as valida��es dos campos necess�riose e formata a String
				// para a forma como ir� aparecer no relat�rio
			
				idNegativador = "";
				negativador = "";
				if (negativadorMovimentoReg.getNegativadorMovimento() != null && negativadorMovimentoReg.getNegativadorMovimento().getNegativador() != null) {
					
					Negativador negativ = negativadorMovimentoReg.getNegativadorMovimento().getNegativador();
						
					idNegativador = negativ.getId().toString();		
					
					if(negativ.getCliente()!=null){
						negativador = negativ.getCliente().getNome();
					}
					
				}
				
				// cliente nome
				nomeCliente = "";
				if (negativadorMovimentoReg.getCliente() != null) {
					nomeCliente = negativadorMovimentoReg.getCliente().getNome();
				}
				
				//situa��o
				situacao = "";
				if(negativadorMovimentoReg.getIndicadorAceito()== null){
					situacao = "NEGATIVA��ES SEM RETORNO DO NEGATIVADOR";
				}else if(negativadorMovimentoReg.getIndicadorAceito().intValue()== 2){
					situacao = "NEGATIVA��ES REJEITADAS";
				}else if(negativadorMovimentoReg.getIndicadorAceito().intValue()== 1){	
					if(indicadorExcluidoNgim != null && indicadorExcluidoNgim.intValue() == 1){
						situacao = "NEGATIVA��ES EXCLU�DAS";
					}else{
						if(negativadorMovimentoReg.getCobrancaSituacao() != null){
							situacao = negativadorMovimentoReg.getCobrancaSituacao().getDescricao();	
						}else{
							situacao = "NEGATIVA��ES ACEITAS E SEM SITUA��O DE COBRAN�A";
						}
					}
				}
				
				//se a situacao anterior for diferente da atual
				if(!situacaoAnterior.equals(situacao)){
					limpaValoresParc = true;
				}
				situacaoAnterior = situacao;
				
				if(limpaValoresParc){
					valorParceladoTotalLoc = BigDecimal.ZERO;
					valorParceladoEntradaTotalLoc = BigDecimal.ZERO;    
					valorParceladoEntradaPagoTotalLoc = BigDecimal.ZERO;
					numeroPrestacoesTotalLoc = 0;
					numeroPrestacoesCobradasPagasTotalLoc = 0;
					valorParceladoCobradoPagoTotalLoc = BigDecimal.ZERO;    
					numeroPrestacoesCobradasNaoPagasTotalLoc = 0;
					valorParceladoCobradoNaoPagoTotalLoc = BigDecimal.ZERO;
					numeroPrestacoesNaoCobradasTotalLoc = 0;
					valorParceladoNaoCobradoTotalLoc = BigDecimal.ZERO;
					valorParceladoCanceladoTotalLoc = BigDecimal.ZERO;
				}
				
				
				// matricula do Imovel
				matricula = "";
				if (negativadorMovimentoReg.getImovel() != null) {
					matricula = negativadorMovimentoReg.getImovel().getId().toString();
				}
				
				//cpf ou cnpj
				cpfCnpj = "";				
				if (negativadorMovimentoReg.getNumeroCnpj() != null ) {
					cpfCnpj = negativadorMovimentoReg.getNumeroCnpj();
				}else if(negativadorMovimentoReg.getNumeroCpf() != null){
					cpfCnpj = negativadorMovimentoReg.getNumeroCpf();
				}
								
				// valor negativado
				valorNegativado = new Double(0);				
				if (negativadorMovimentoReg.getValorDebito() != null) {
					valorNegativado = new Double(negativadorMovimentoReg.getValorDebito().toString());
				}
				
				Object[] valorPagoEValorCancelado = fachada.pesquisarSomatorioValorPagoEValorCancelado(negativadorMovimentoReg.getId());
				
				// valor Pago
				valorPago = BigDecimal.ZERO;		
				if(valorPagoEValorCancelado[0] != null){
					valorPago = (BigDecimal)valorPagoEValorCancelado[0];
				}
								 
				 //valor Cancelado
				valorCancelado = BigDecimal.ZERO;	
				if(valorPagoEValorCancelado[1] != null){
					valorCancelado = (BigDecimal)valorPagoEValorCancelado[1];
				}
				
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				colecaoDadosParcelamentoBean = new ArrayList();
				Object[] dadosParcelamento = fachada.pesquisarDadosParcelamentoRelatorioAcompanhamentoClientesNegativados(negativadorMovimentoReg.getId());
				
				if(dadosParcelamento != null){
					
					Collection colecaoNegativadorMovimentoRegParcelamento = (Collection)dadosParcelamento[0];
					
					if(colecaoNegativadorMovimentoRegParcelamento != null && !colecaoNegativadorMovimentoRegParcelamento.isEmpty()){
						
						BigDecimal valorParcelado = null;
						BigDecimal valorParceladoEntrada = null;
						BigDecimal valorParceladoEntradaPago = null;
						Short numeroPrestacoes = null;
						Short numeroPrestacoesCobradasPagas = null;
						BigDecimal valorParceladoCobradoPago = null;    
						Short numeroPrestacoesCobradasNaoPagas = null;
						BigDecimal valorParceladoCobradoNaoPago = null;
						Short numeroPrestacoesNaoCobradas = null;
						BigDecimal valorParceladoNaoCobrado = null;
						BigDecimal valorParceladoCancelado = null;

						BigDecimal valorParceladoTotalParc = null;
						BigDecimal valorParceladoEntradaTotalParc = null;    
						BigDecimal valorParceladoEntradaPagoTotalParc = null;
						Short numeroPrestacoesTotalParc = null;
						Short numeroPrestacoesCobradasPagasTotalParc = null;
						BigDecimal valorParceladoCobradoPagoTotalParc = null;    
						Short numeroPrestacoesCobradasNaoPagasTotalParc = null;
						BigDecimal valorParceladoCobradoNaoPagoTotalParc = null;
						Short numeroPrestacoesNaoCobradasTotalParc = null;
						BigDecimal valorParceladoNaoCobradoTotalParc = null;
						BigDecimal valorParceladoCanceladoTotalParc = null;
						
						int tamanhoColecao = colecaoNegativadorMovimentoRegParcelamento.size();
						int cont = 0;
						Iterator iterDadosParc = colecaoNegativadorMovimentoRegParcelamento.iterator();
						RelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean dadosParcelamentoBean = null;
						indicadorExisteDadosParcelamento = ConstantesSistema.SIM;
						
						boolean existeMaisDeUmParcelamento = false;
						if(tamanhoColecao > 1){
							existeMaisDeUmParcelamento = true;
						}
						
						while (iterDadosParc.hasNext()) {
							
							cont = cont + 1;
							valorParcelado = BigDecimal.ZERO;
							valorParceladoEntrada = BigDecimal.ZERO;
							valorParceladoEntradaPago = BigDecimal.ZERO;
							numeroPrestacoes = 0;
							numeroPrestacoesCobradasPagas = 0;
							valorParceladoCobradoPago = BigDecimal.ZERO;    
							numeroPrestacoesCobradasNaoPagas = 0;
							valorParceladoCobradoNaoPago = BigDecimal.ZERO;
							numeroPrestacoesNaoCobradas = 0;
							valorParceladoNaoCobrado = BigDecimal.ZERO;
							valorParceladoCancelado = BigDecimal.ZERO;
							
							NegativadorMovimentoRegParcelamento nmrp = (NegativadorMovimentoRegParcelamento) iterDadosParc.next();
							
							valorParcelado = nmrp.getValorParcelado();
							valorParceladoEntrada = nmrp.getValorParceladoEntrada();
							
							if(nmrp.getValorParceladoEntradaPago() != null){
								valorParceladoEntradaPago = nmrp.getValorParceladoEntradaPago();
							}
							numeroPrestacoes = nmrp.getNumeroPrestacoes();
							
							if(nmrp.getNumeroPrestacoesCobradasPagas() != null){
								numeroPrestacoesCobradasPagas = nmrp.getNumeroPrestacoesCobradasPagas();
							}
							if(nmrp.getValorParceladoCobradoPago() != null){
								valorParceladoCobradoPago = nmrp.getValorParceladoCobradoPago();    
							}
							if(nmrp.getNumeroPrestacoesCobradasNaoPagas() != null){
								numeroPrestacoesCobradasNaoPagas = nmrp.getNumeroPrestacoesCobradasNaoPagas();
							}
							if(nmrp.getValorParceladoCobradoNaoPago() != null){
								valorParceladoCobradoNaoPago = nmrp.getValorParceladoCobradoNaoPago();
							}
							if(nmrp.getNumeroPrestacoesNaoCobradas() != null){
								numeroPrestacoesNaoCobradas = nmrp.getNumeroPrestacoesNaoCobradas();
							}
							if(nmrp.getValorParceladoNaoCobrado() != null){
								valorParceladoNaoCobrado = nmrp.getValorParceladoNaoCobrado();
							}
							
							if(nmrp.getValorParceladoCancelado() != null){
								valorParceladoCancelado = nmrp.getValorParceladoCancelado();
							}	

							if(tamanhoColecao != cont || tamanhoColecao == 1){
								dadosParcelamentoBean = new RelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean(
										valorParceladoEntrada, valorParcelado, valorParceladoEntradaPago, valorParceladoCancelado,
										valorParceladoNaoCobrado, valorParceladoCobradoPago, valorParceladoCobradoNaoPago, 
										numeroPrestacoes, numeroPrestacoesNaoCobradas, numeroPrestacoesCobradasPagas,
										numeroPrestacoesCobradasNaoPagas, valorParceladoTotalParc,
										valorParceladoEntradaTotalParc, valorParceladoEntradaPagoTotalParc, 
										numeroPrestacoesTotalParc, numeroPrestacoesCobradasPagasTotalParc,
										valorParceladoCobradoPagoTotalParc, numeroPrestacoesCobradasNaoPagasTotalParc,	
										valorParceladoCobradoNaoPagoTotalParc, numeroPrestacoesNaoCobradasTotalParc,	
										valorParceladoNaoCobradoTotalParc, valorParceladoCanceladoTotalParc);
								colecaoDadosParcelamentoBean.add(dadosParcelamentoBean);
								
								if(!existeMaisDeUmParcelamento){
									valorParceladoTotalLoc = valorParceladoTotalLoc.add(valorParcelado);
									valorParceladoEntradaTotalLoc = valorParceladoEntradaTotalLoc.add(valorParceladoEntrada);    
									valorParceladoEntradaPagoTotalLoc = valorParceladoEntradaPagoTotalLoc.add(valorParceladoEntradaPago);
									numeroPrestacoesTotalLoc = numeroPrestacoesTotalLoc.intValue() + numeroPrestacoes.intValue();
									numeroPrestacoesCobradasPagasTotalLoc =	numeroPrestacoesCobradasPagasTotalLoc.intValue() + numeroPrestacoesCobradasPagas.intValue() ;
									valorParceladoCobradoPagoTotalLoc = valorParceladoCobradoPagoTotalLoc.add(valorParceladoCobradoPago);    
									numeroPrestacoesCobradasNaoPagasTotalLoc =	numeroPrestacoesCobradasNaoPagasTotalLoc.intValue() + numeroPrestacoesCobradasNaoPagas.intValue() ;
									valorParceladoCobradoNaoPagoTotalLoc = valorParceladoCobradoNaoPagoTotalLoc.add(valorParceladoCobradoNaoPago);
									numeroPrestacoesNaoCobradasTotalLoc = numeroPrestacoesNaoCobradasTotalLoc.intValue() + numeroPrestacoesNaoCobradas.intValue() ;
									valorParceladoNaoCobradoTotalLoc = valorParceladoNaoCobradoTotalLoc.add(valorParceladoNaoCobrado);
									valorParceladoCanceladoTotalLoc = valorParceladoCanceladoTotalLoc.add(valorParceladoCancelado);
								}

							}else{
								// tamanhoColecao > 1 e ultima linha do sub 'RelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean'
								
								RelatorioAcompanhamentoClientesNegativadosSomatorioDadosParcelamentoHelper helperSomatorioParc = 
									(RelatorioAcompanhamentoClientesNegativadosSomatorioDadosParcelamentoHelper)dadosParcelamento[1];
								
								valorParceladoTotalParc = helperSomatorioParc.getValorParceladoTotal();
								valorParceladoEntradaTotalParc = helperSomatorioParc.getValorParceladoEntradaTotal();    
								valorParceladoEntradaPagoTotalParc = helperSomatorioParc.getValorParceladoEntradaPagoTotal();
								numeroPrestacoesTotalParc = helperSomatorioParc.getNumeroPrestacoesTotal();
								numeroPrestacoesCobradasPagasTotalParc = helperSomatorioParc.getNumeroPrestacoesCobradasPagasTotal();
								valorParceladoCobradoPagoTotalParc = helperSomatorioParc.getValorParceladoCobradoPagoTotal();    
								numeroPrestacoesCobradasNaoPagasTotalParc = helperSomatorioParc.getNumeroPrestacoesCobradasNaoPagasTotal();
								valorParceladoCobradoNaoPagoTotalParc = helperSomatorioParc.getValorParceladoCobradoNaoPagoTotal();
								numeroPrestacoesNaoCobradasTotalParc = helperSomatorioParc.getNumeroPrestacoesNaoCobradasTotal();
								valorParceladoNaoCobradoTotalParc = helperSomatorioParc.getValorParceladoNaoCobradoTotal();
								valorParceladoCanceladoTotalParc = helperSomatorioParc.getValorParceladoCanceladoTotal();
								
								dadosParcelamentoBean = new RelatorioAcompanhamentoClientesNegativadosDadosParcelamentoBean(
										valorParceladoEntrada, valorParcelado, valorParceladoEntradaPago, valorParceladoCancelado,
										valorParceladoNaoCobrado, valorParceladoCobradoPago, valorParceladoCobradoNaoPago, 
										numeroPrestacoes, numeroPrestacoesNaoCobradas, numeroPrestacoesCobradasPagas,
										numeroPrestacoesCobradasNaoPagas, valorParceladoTotalParc,
										valorParceladoEntradaTotalParc, valorParceladoEntradaPagoTotalParc, 
										numeroPrestacoesTotalParc, numeroPrestacoesCobradasPagasTotalParc,
										valorParceladoCobradoPagoTotalParc, numeroPrestacoesCobradasNaoPagasTotalParc,	
										valorParceladoCobradoNaoPagoTotalParc, numeroPrestacoesNaoCobradasTotalParc,	
										valorParceladoNaoCobradoTotalParc, valorParceladoCanceladoTotalParc);
								colecaoDadosParcelamentoBean.add(dadosParcelamentoBean);
								
								valorParceladoTotalLoc = valorParceladoTotalLoc.add(valorParceladoTotalParc);
								valorParceladoEntradaTotalLoc = valorParceladoEntradaTotalLoc.add(valorParceladoEntradaTotalParc);    
								valorParceladoEntradaPagoTotalLoc = valorParceladoEntradaPagoTotalLoc.add(valorParceladoEntradaPagoTotalParc);
								numeroPrestacoesTotalLoc = numeroPrestacoesTotalLoc.intValue() + numeroPrestacoesTotalParc.intValue() ;
								numeroPrestacoesCobradasPagasTotalLoc = numeroPrestacoesCobradasPagasTotalLoc.intValue() + numeroPrestacoesCobradasPagasTotalParc.intValue();
								valorParceladoCobradoPagoTotalLoc = valorParceladoCobradoPagoTotalLoc.add(valorParceladoCobradoPagoTotalParc);    
								numeroPrestacoesCobradasNaoPagasTotalLoc = numeroPrestacoesCobradasNaoPagasTotalLoc.intValue() + numeroPrestacoesCobradasNaoPagasTotalParc.intValue();
								valorParceladoCobradoNaoPagoTotalLoc = valorParceladoCobradoNaoPagoTotalLoc.add(valorParceladoCobradoNaoPagoTotalParc);
								numeroPrestacoesNaoCobradasTotalLoc = numeroPrestacoesNaoCobradasTotalLoc.intValue() + numeroPrestacoesNaoCobradasTotalParc.intValue();
								valorParceladoNaoCobradoTotalLoc = valorParceladoNaoCobradoTotalLoc.add(valorParceladoNaoCobradoTotalParc);
								valorParceladoCanceladoTotalLoc = valorParceladoCanceladoTotalLoc.add(valorParceladoCanceladoTotalParc);
								

							}
							
						}
						
					}

				}
				
				// valor Pendente
				valorPendente = new BigDecimal(0);					
				CobrancaDebitoSituacao cobrancaDebitoSituacao= new CobrancaDebitoSituacao();
				cobrancaDebitoSituacao.setId(CobrancaDebitoSituacao.PENDENTE);				
				valorPendente = fachada.pesquisarSomatorioValorDebito(negativadorMovimentoReg,cobrancaDebitoSituacao);
				 if(valorPendente == null){
					 valorPendente = new BigDecimal(0);		
			     }
				
			}
			
			//insere a ultima linha do relatorio
			relatorioBean = new RelatorioAcompanhamentoClientesNegativadosBean(
					nomeCliente, 
				    matricula,
				    cpfCnpj,
				    valorNegativado,
				    valorPago, 
				    valorCancelado,
				    valorPendente,
				    situacao,
				    periodoEnvioNegativacaoAnterior,
				    localidadeAnterior, 
				    idLocalidadeAnterior,
				    idNegativador,
				    negativador,
				    colecaoDadosParcelamentoBean,
				    indicadorExisteDadosParcelamento.toString(),
				    parametrosSelecionadosBean,
				    valorParceladoTotalLoc,
				    valorParceladoEntradaTotalLoc,
					valorParceladoEntradaPagoTotalLoc, 
					numeroPrestacoesTotalLoc, 
					numeroPrestacoesCobradasPagasTotalLoc,
					valorParceladoCobradoPagoTotalLoc, 
					numeroPrestacoesCobradasNaoPagasTotalLoc,
					valorParceladoCobradoNaoPagoTotalLoc,
					numeroPrestacoesNaoCobradasTotalLoc,
					valorParceladoNaoCobradoTotalLoc, 
					valorParceladoCanceladoTotalLoc);
			
			relatorioBean.setIdGerenciaRegional(idGerenciaRegionalAnterior);
			relatorioBean.setGerenciaRegional(gerenciaRegionalAnterior);
			relatorioBean.setIdUnidadeNegocio(idUnidadeNegocioAnterior);
			relatorioBean.setUnidadeNegocio(unidadeNegocioAnterior);
			
			// --------------------------------
			if (negativadorMovimentoReg.getIndicadorAceito() != null && 
				negativadorMovimentoReg.getIndicadorAceito().equals(new Short("2"))) {
				
				FiltroNegativadorMovimentoRegRetMot filtroMotivoRejeicao = new FiltroNegativadorMovimentoRegRetMot();
				filtroMotivoRejeicao.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativadorMovimentoRegRetMot.NEGATIVADOR_RETORNO_MOTIVO);
				filtroMotivoRejeicao.adicionarParametro(new ParametroSimples(
						FiltroNegativadorMovimentoRegRetMot.NEGATIVADOR_MOVIMENTO_REG_ID, negativadorMovimentoReg.getId()));
				
				Collection<NegativadorMovimentoRegRetMot> pesquisa = fachada.pesquisar(filtroMotivoRejeicao, NegativadorMovimentoRegRetMot.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					String linhaMotivos = "";

					Iterator<NegativadorMovimentoRegRetMot> motivosRejeicao = pesquisa.iterator();
					while (motivosRejeicao.hasNext()) {
						NegativadorMovimentoRegRetMot motivoRejeicao = motivosRejeicao.next();
						linhaMotivos += motivoRejeicao.getNegativadorRetornoMotivo().getCodigoRetornoMotivo() +
								"-" + motivoRejeicao.getNegativadorRetornoMotivo().getDescricaoRetornocodigo();
						
						if (motivosRejeicao.hasNext()) {
							linhaMotivos += ", ";
						}
					}
					
					relatorioBean.setMotivosRejeicao(linhaMotivos);
				}
			}
			relatorioBeans.add(relatorioBean);
			// --------------------------------
				
				
		}else{
			throw new ActionServletException("atencao.relatorio.vazio");	
		}
			
		
		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		
		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		if (tipoFormatoRelatorio == TarefaRelatorio.TIPO_XLS) {
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS_EXCEL, 
					parametros, ds,
					tipoFormatoRelatorio);
		} else {
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS_PDF, 
					parametros, ds,
					tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.GERAR_RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS,
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
		int retorno = ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA;

        DadosConsultaNegativacaoHelper parametrosHelper = (DadosConsultaNegativacaoHelper) getParametro("parametros");
        
		retorno = Fachada.getInstancia().pesquisarRelatorioAcompanhamentoClientesNegativadorCount(parametrosHelper);

		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoClientesNegativados", this);
	}

	
	public RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean retornaParametrosSelecionaodos(DadosConsultaNegativacaoHelper parametrosHelper){
		Fachada fachada = Fachada.getInstancia();
		RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean retorno =
			new RelatorioAcompanhamentoClientesNegativadosParametrosSelecionadosBean();
		
		//*************************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 10/01/2011
		//*************************************************************
		retorno.setNegativador("");
		retorno.setPeriodoEnvioNegativacao("");
		retorno.setTituloComando("");
		retorno.setEloPolo("");
		retorno.setLocalidade("");
		retorno.setNumeroQuadra("");
		retorno.setCodigoSetorComercial("");
		retorno.setGrupoCobranca("");
		retorno.setGerenciaRegional("");
		retorno.setUnidadeNegocio("");
		retorno.setImovelPerfil("");
		retorno.setCategoria("");
		retorno.setTipoCliente("");
		retorno.setEsferaPoder("");
		retorno.setLigacaoAguaSituacao("");
		retorno.setLigacaoEsgotoSituacao("");
		
		if (!Util.isVazioOrNulo(parametrosHelper.getColecaoNegativador())) {
			FiltroNegativador filtroNegativador = new FiltroNegativador();
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");		
			filtroNegativador.adicionarParametro(new ParametroSimplesIn(FiltroNegativador.ID, parametrosHelper.getColecaoNegativador()));

			Collection collNegativador = fachada.pesquisar(filtroNegativador, Negativador.class.getName());
			Iterator itt = collNegativador.iterator();
			boolean primeiro = true;
			retorno.setNegativador("");
			while(itt.hasNext()){
				Negativador negativador = (Negativador)itt.next();
				if(negativador.getCliente()!=null){					
					retorno.setNegativador(retorno.getNegativador() + negativador.getCliente().getNome());
					if (collNegativador.size() > 1 && primeiro) {
						retorno.setNegativador(retorno.getNegativador() + " / ");
						primeiro = false;
					}
//					parametros.put("negativador", negativador.getCliente().getNome());
				}
			}			
		}
		//*****************************************************
			
		if (parametrosHelper.getPeriodoEnvioNegativacaoInicio() != null) {
			retorno.setPeriodoEnvioNegativacao(parametrosHelper.getPeriodoEnvioNegativacaoInicio() + " � " + parametrosHelper.getPeriodoEnvioNegativacaoFim());
		} 
		if (parametrosHelper.getTituloComando() != null) {
			retorno.setTituloComando(parametrosHelper.getTituloComando().toString());
		} 
		
		if (parametrosHelper.getIdEloPolo() != null) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();		
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_ELO,parametrosHelper.getIdEloPolo()));

			Collection collLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			Iterator itt = collLocalidade.iterator();
			while(itt.hasNext()){
				Localidade localidade = (Localidade)itt.next();
				if(localidade!=null){		
					retorno.setEloPolo(localidade.getDescricao());
				}
				break;
			}
		}
		
		if (parametrosHelper.getIdLocalidade() != null) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();		
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,parametrosHelper.getIdLocalidade()));

			Collection collLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			Iterator itt = collLocalidade.iterator();
			while(itt.hasNext()){
				Localidade localidade = (Localidade)itt.next();
				if(localidade!=null){	
					retorno.setLocalidade(localidade.getDescricao());
				}
				break;
			}

		} 
		
		if (parametrosHelper.getIdSetorComercial() != null) {
			retorno.setCodigoSetorComercial(parametrosHelper.getIdSetorComercial().toString());
		} 
		
		if (parametrosHelper.getIdQuadra() != null) {
			FiltroQuadra filtroQuadra = new FiltroQuadra();		
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID,parametrosHelper.getIdQuadra()));

			Collection collQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
			Iterator itt = collQuadra.iterator();
			while(itt.hasNext()){
				Quadra quadra = (Quadra)itt.next();
				if(quadra!=null){	
					retorno.setNumeroQuadra("" + quadra.getNumeroQuadra());
				}
				break;
			}
		} 
		
		if (!Util.isVazioOrNulo(parametrosHelper.getColecaoCobrancaGrupo())) {
			String gruposCobranca = "";
			
			Iterator itt=parametrosHelper.getColecaoCobrancaGrupo().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				CobrancaGrupo cobrancaGrupo = (CobrancaGrupo)itt.next();
				if(cobrancaGrupo!=null){
					if (primeiro) {
						gruposCobranca = gruposCobranca + cobrancaGrupo.getDescricao();
						primeiro = false;
					} else {
						gruposCobranca = gruposCobranca + ", " + cobrancaGrupo.getDescricao();
					}
				}		
			}
			retorno.setGrupoCobranca(gruposCobranca);

		}
		
		if (!Util.isVazioOrNulo(parametrosHelper.getColecaoGerenciaRegional())) {
			String gerenciasRegionais = "";
			
			Iterator itt=parametrosHelper.getColecaoGerenciaRegional().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				GerenciaRegional gerenciaRegional = (GerenciaRegional)itt.next();
				if(gerenciaRegional!=null){
					if (primeiro) {
						gerenciasRegionais = gerenciasRegionais + gerenciaRegional.getNome();
						primeiro = false;
					} else {
						gerenciasRegionais = gerenciasRegionais + ", " +  gerenciaRegional.getNome();
					}
				}		
			}
			retorno.setGerenciaRegional(gerenciasRegionais);

		}
		
		
		if (!Util.isVazioOrNulo(parametrosHelper.getColecaoUnidadeNegocio())) {
			String unidadesNegocio = "";
			
			Iterator itt=parametrosHelper.getColecaoUnidadeNegocio().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				UnidadeNegocio unidadeNegocio = (UnidadeNegocio)itt.next();
				if(unidadeNegocio!=null){
					if (primeiro) {
						unidadesNegocio = unidadesNegocio + unidadeNegocio.getNome();
						primeiro = false;
					} else {
						unidadesNegocio = unidadesNegocio + ", " +  unidadeNegocio.getNome();
					}
				}		
			}
			retorno.setUnidadeNegocio(unidadesNegocio);
		}
		
		if (!Util.isVazioOrNulo(parametrosHelper.getColecaoImovelPerfil())) {
			String imoveisPerfil = "";
			
			Iterator itt=parametrosHelper.getColecaoImovelPerfil().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				ImovelPerfil imovelPerfil = (ImovelPerfil)itt.next();
				if(imovelPerfil!=null){
					if (primeiro) {
						imoveisPerfil = imoveisPerfil + imovelPerfil.getDescricao();
						primeiro = false;
					} else {
						imoveisPerfil = imoveisPerfil + ", " +  imovelPerfil.getDescricao();
					}
				}		
			}
			retorno.setImovelPerfil(imoveisPerfil);

		} 
		if (!Util.isVazioOrNulo(parametrosHelper.getColecaoCategoria())) {
			String categorias = "";
			
			Iterator itt=parametrosHelper.getColecaoCategoria().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				Categoria categoria = (Categoria)itt.next();
				if(categoria!=null){
					if (primeiro) {
						categorias = categorias + categoria.getDescricao();
						primeiro = false;
					} else {
						categorias = categorias + ", " +  categoria.getDescricao();
					}
				}		
			}
			retorno.setCategoria(categorias);
		} 
		
		if (!Util.isVazioOrNulo(parametrosHelper.getColecaoClienteTipo())) {
			String tiposCliente = "";
			
			Iterator itt=parametrosHelper.getColecaoClienteTipo().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				ClienteTipo clienteTipo = (ClienteTipo)itt.next();
				if(clienteTipo!=null){
					if (primeiro) {
						tiposCliente = tiposCliente + clienteTipo.getDescricao();
						primeiro = false;
					} else {
						tiposCliente = tiposCliente + ", " +  clienteTipo.getDescricao();
					}
				}		
			}
			retorno.setTipoCliente(tiposCliente);
		}
		
		if (!Util.isVazioOrNulo(parametrosHelper.getColecaoEsferaPoder())) {
			String esferasPoder = "";
			
			Iterator itt=parametrosHelper.getColecaoEsferaPoder().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				EsferaPoder esferaPoder = (EsferaPoder)itt.next();
				if(esferaPoder!=null){
					if (primeiro) {
						esferasPoder = esferasPoder + esferaPoder.getDescricao();
						primeiro = false;
					} else {
						esferasPoder = esferasPoder + ", " +  esferaPoder.getDescricao();
					}
				}		
			}
			retorno.setEsferaPoder(esferasPoder);

		} 
		
		//*************************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 10/01/2011
		//*************************************************************
		if (!Util.isVazioOrNulo(parametrosHelper.getColecaoLigacaoAguaSituacao())) {
			String dados = "";
			
			Iterator itt=parametrosHelper.getColecaoLigacaoAguaSituacao().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao)itt.next();
				if(ligacaoAguaSituacao!=null){
					if (primeiro) {
						dados = dados + ligacaoAguaSituacao.getDescricao();
						primeiro = false;
					} else {
						dados = dados + ", " + ligacaoAguaSituacao.getDescricao();
					}
				}		
			}
			retorno.setLigacaoAguaSituacao(dados);
		} 
		
		if (!Util.isVazioOrNulo(parametrosHelper.getColecaoLigacaoEsgotoSituacao())) {
			String dados = "";
			
			Iterator itt = parametrosHelper.getColecaoLigacaoEsgotoSituacao().iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao)itt.next();
				if(ligacaoEsgotoSituacao != null){
					if (primeiro) {
						dados = dados + ligacaoEsgotoSituacao.getDescricao();
						primeiro = false;
					} else {
						dados = dados + ", " + ligacaoEsgotoSituacao.getDescricao();
					}
				}		
			}
			retorno.setLigacaoEsgotoSituacao(dados);
		}
		
		return retorno;
	}
	
}
