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
package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.bean.IntegracaoComercialHelper;
import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
import gsan.atendimentopublico.ordemservico.FiltroServicoTipoOperacao;
import gsan.atendimentopublico.ordemservico.FiltroTipoServico;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.OrdemServicoBoletim;
import gsan.atendimentopublico.ordemservico.OrdemServicoFoto;
import gsan.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipoOperacao;
import gsan.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gsan.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gsan.cadastro.imovel.PavimentoCalcada;
import gsan.cadastro.imovel.PavimentoRua;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de encerra OS caso precise
 * entrar na tela
 * 
 * @author S�vio Luiz
 * @created 18/09/2006
 */
public class EncerrarOrdemServicoPopupAction extends GcomAction {
	/**
	 * Execute do Consultar OS Popup
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("encerrarOrdemServicoPopup");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		EncerrarOrdemServicoActionForm encerrarOrdemServicoActionForm = (EncerrarOrdemServicoActionForm) actionForm;

		String valorConfirmacao = httpServletRequest.getParameter("confirmado");
		OrdemServicoBoletim ordemServicoBoletim = null;
		
        ////////////////////////////////////////////////////////////////////////////////////////////////
        if ( "confirmaBoletimValorZero".equals( httpServletRequest.getParameter("tipoRelatorio") ) ){
            sessao.setAttribute( "confirmaBoletimValorZero", httpServletRequest.getParameter( "confirmado" ) );
        }
        
        String valorConfirmacaoBoletimValorZero = ( String ) sessao.getAttribute( "confirmaBoletimValorZero" ); 
        ///////////////////////////////////////////////////////////////////////////////////////////////
		Short indicadorServicoAceito = null;
		if(encerrarOrdemServicoActionForm.getIndicadorServicoAceito() != null &&
				!encerrarOrdemServicoActionForm.getIndicadorServicoAceito().equals("")){
			indicadorServicoAceito = new Short(encerrarOrdemServicoActionForm.getIndicadorServicoAceito());
		}
		/*
		 * // caso j� tenha sido perguntado se deseja gerar OS fiscaliza��o e a //
		 * pessoa escolheu que n�o quer ent�o n�o entra nesse if if
		 * (sessao.getAttribute("canceladoOSFiscalizacao") == null ||
		 * sessao.getAttribute("canceladoOSFiscalizacao").equals("")) { // parte
		 * de ordem de servi�o de fiscaliza��o if ((osFiscalizacao == null ||
		 * osFiscalizacao.equals("")) && (valorConfirmacao == null ||
		 * valorConfirmacao.equals(""))) {
		 * httpServletRequest.setAttribute("caminhoActionConclusao",
		 * "/gsan/encerrarOrdemServicoPopupAction.do");
		 * httpServletRequest.setAttribute("cancelamento", "TRUE");
		 * httpServletRequest.setAttribute("nomeBotao1", "Sim");
		 * httpServletRequest.setAttribute("nomeBotao2", "N�o");
		 * 
		 * return montarPaginaConfirmacao("atencao.gerar_OS_Fiscalizacao",
		 * httpServletRequest, actionMapping); } else { if ((osFiscalizacao ==
		 * null || osFiscalizacao.equals("")) && valorConfirmacao.equals("ok")) {
		 * retorno = actionMapping
		 * .findForward("exibirEncerrarOrdemPopupServico"); return retorno; }
		 * else { valorConfirmacao = null;
		 * sessao.setAttribute("canceladoOSFiscalizacao", "SIM"); } } }
		 */

		
		// parte da integra��o com o sistema comercial
		IntegracaoComercialHelper integracaoComercialHelper = (IntegracaoComercialHelper) sessao.getAttribute("integracaoComercialHelper");
		if (valorConfirmacao == null || valorConfirmacao.equals("")) {

			// valida enter
			String idServicoTipo = encerrarOrdemServicoActionForm.getIdServicoTipo();
			String descricaoServicoTipo = encerrarOrdemServicoActionForm.getDescricaoServicoTipo();

			Collection colecaoManterDadosAtividadesOrdemServicoHelper = (Collection) sessao.getAttribute("colecaoManutencao");
			if ((idServicoTipo != null && !idServicoTipo.equals(""))
					&& (descricaoServicoTipo == null || descricaoServicoTipo.equals(""))) {

				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

				filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, idServicoTipo));
				filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection servicoTipoEncontrado = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());

				if (servicoTipoEncontrado != null && !servicoTipoEncontrado.isEmpty()) {
					ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(servicoTipoEncontrado);
					// O servi�o tipo foi encontrado
					encerrarOrdemServicoActionForm.setIdServicoTipo(""	+ servicoTipo.getId());
					encerrarOrdemServicoActionForm.setDescricaoServicoTipo(servicoTipo.getDescricao());

				} else {

					throw new ActionServletException("atencao.label_inexistente", null, "Servi�o Tipo");

				}

			}
			// caso seja a primeira vez
			if (integracaoComercialHelper == null || integracaoComercialHelper.equals("")) {
				
				// [FS0002] - Validar Tipo Servi�o
				// [FS0004] - Verificar preenchimento dos campos
				// [FS0007] - Validar Data de Encerramento
				// [FS0008] - Validar Data do roteiro
				fachada.validarCamposEncerrarOSPopup(
                        encerrarOrdemServicoActionForm.getIndicadorExecucao(), 
                        encerrarOrdemServicoActionForm.getNumeroOS(), 
                        encerrarOrdemServicoActionForm.getIdMotivoEncerramento(),
						encerrarOrdemServicoActionForm.getDataEncerramento(),
						colecaoManterDadosAtividadesOrdemServicoHelper,
						encerrarOrdemServicoActionForm.getTipoServicoReferenciaId(),
						encerrarOrdemServicoActionForm.getIndicadorPavimento(),
						encerrarOrdemServicoActionForm.getTipoServicoOSId(),
						encerrarOrdemServicoActionForm.getIdTipoRetornoReferida(),
						encerrarOrdemServicoActionForm.getIndicadorDeferimento(),
						encerrarOrdemServicoActionForm.getIndicadorTrocaServico(), 
                        idServicoTipo,
						encerrarOrdemServicoActionForm.getDataRoteiro(),
						encerrarOrdemServicoActionForm.getNumeroRA(),
						encerrarOrdemServicoActionForm.getIndicadorVistoriaServicoTipo(),
						encerrarOrdemServicoActionForm.getCodigoRetornoVistoriaOs(),
                        encerrarOrdemServicoActionForm.getIndicadorDiagnostico(),
                        encerrarOrdemServicoActionForm.getObservacaoEncerramento(),
                        usuarioLogado,
                        encerrarOrdemServicoActionForm.getIdPavimentoRua(),
                        encerrarOrdemServicoActionForm.getMetragemPavimentoRua(),
                        encerrarOrdemServicoActionForm.getIdPavimentoCalcada(),
                        encerrarOrdemServicoActionForm.getMetragemPavimentoCalcada(),
                        encerrarOrdemServicoActionForm.getIdUnidadeRepavimentadora());
				
				Map validacao = validarInformacoesBoletimMedicao(
			    		new Integer(encerrarOrdemServicoActionForm.getNumeroOS()),
			    		encerrarOrdemServicoActionForm);
				
				ordemServicoBoletim = (OrdemServicoBoletim)validacao.get("ordemServicoBoletim");
				Boolean exibirMsgConfirmacao = (Boolean)validacao.get("exibirMsgConfirmacao");				
				
				if(exibirMsgConfirmacao && (valorConfirmacaoBoletimValorZero == null || 
						!valorConfirmacaoBoletimValorZero.equalsIgnoreCase("ok"))){
					
					httpServletRequest.setAttribute("caminhoActionConclusao","/gsan/encerrarOrdemServicoPopupAction.do");
//					httpServletRequest.setAttribute("cancelamento", "TRUE");
					httpServletRequest.setAttribute("nomeBotao1", "Sim");
//					httpServletRequest.setAttribute("nomeBotao2", "N�o");
					httpServletRequest.setAttribute("tipoRelatorio", "confirmaBoletimValorZero");
					
					return montarPaginaConfirmacao("atencao.encerrar_OS_boletim",httpServletRequest, actionMapping);
				}
			}else {
				
				Map validacao = validarInformacoesBoletimMedicao(new Integer(encerrarOrdemServicoActionForm.getNumeroOS()), encerrarOrdemServicoActionForm);
				ordemServicoBoletim = (OrdemServicoBoletim) validacao.get("ordemServicoBoletim");
			}

			// indicador execu��o seja diferente de nulo
			if (encerrarOrdemServicoActionForm.getIndicadorExecucao() != null
					&& !encerrarOrdemServicoActionForm.getIndicadorExecucao().equals("")) {

				short indicadorExecucao = Short.parseShort(encerrarOrdemServicoActionForm.getIndicadorExecucao());
				Integer numeroOS = Util
						.converterStringParaInteger(encerrarOrdemServicoActionForm.getNumeroOS());
				
				Date dataEncerramento = null;
				if(encerrarOrdemServicoActionForm.getHoraEncerramento() != null && 	!encerrarOrdemServicoActionForm.getHoraEncerramento().equals("")){
					dataEncerramento = Util.converteStringParaDateHora(encerrarOrdemServicoActionForm
								.getDataEncerramento()+ " "+ encerrarOrdemServicoActionForm.getHoraEncerramento() + ":00");
				}else{
					dataEncerramento = Util.converteStringParaDateHora(
							encerrarOrdemServicoActionForm.getDataEncerramento()+ " " +
							Util.formatarHoraSemSegundos(new Date()) + ":00");	
				}		
				
				Date dataUltimaAlteracao = encerrarOrdemServicoActionForm.getUltimaAlteracao();
				// indicador execu��o seja igual a n�o(2)
				if (indicadorExecucao == AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_NAO) {
					OrdemServico osFiscalizacao = null;
					
					if( sessao.getAttribute("ordemServicoFiscalizacao") !=null ){
						osFiscalizacao = (OrdemServico) sessao.getAttribute("ordemServicoFiscalizacao");
					}
					Collection<OrdemServicoFoto> colecaoOrdemServicoFoto = (Collection<OrdemServicoFoto>) sessao.getAttribute("colecaoOrdemServicoFoto");
                    // [SB0001] - Encerrar sem execu��o
					fachada.encerrarOSSemExecucao(numeroOS, dataEncerramento, usuarioLogado, 
                       encerrarOrdemServicoActionForm.getIdMotivoEncerramento(), dataUltimaAlteracao, 
                       encerrarOrdemServicoActionForm.getObservacaoEncerramento(), osFiscalizacao, 
                       encerrarOrdemServicoActionForm.getIndicadorVistoriaServicoTipo(),
							encerrarOrdemServicoActionForm.getCodigoRetornoVistoriaOs(),
							ordemServicoBoletim,indicadorServicoAceito,colecaoOrdemServicoFoto);
					
				} else {
					// indicador execu��o seja igual a sim(1)
					if (indicadorExecucao == AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM) {
						if (encerrarOrdemServicoActionForm.getIndicadorAtualizaComercial() != null
								&& !encerrarOrdemServicoActionForm.getIndicadorAtualizaComercial().equals("")) {
							Short indicadorComercialAtualiza = new Short(
									encerrarOrdemServicoActionForm.getIndicadorAtualizaComercial());

							if (indicadorComercialAtualiza.equals(ServicoTipo.INDICADOR_ATUALIZA_COMERCIAL_SIM)) {
								// caso n�o exista o objeto helper na sess�o
								// ent�o � a primeira vez
								if (integracaoComercialHelper == null || integracaoComercialHelper.equals("")) {
									// caso exista tipo de servi�o
									if (encerrarOrdemServicoActionForm.getTipoServicoOSId() != null
											&& !encerrarOrdemServicoActionForm.getTipoServicoOSId().equals("")) {

										FiltroServicoTipoOperacao filtroServicoTipoOperacao = new FiltroServicoTipoOperacao();
										filtroServicoTipoOperacao.adicionarCaminhoParaCarregamentoEntidade("operacao");
										filtroServicoTipoOperacao.adicionarParametro(new ParametroSimples(
											FiltroServicoTipoOperacao.ID_SERVICO_TIPO, encerrarOrdemServicoActionForm.getTipoServicoOSId()));
										Collection colecaoServicoTipoOperacao = fachada.pesquisar(
												filtroServicoTipoOperacao, ServicoTipoOperacao.class.getName());
										// caso exista uma funcionalidade
										// assiciada
										// ao servi�o tipo
										if (colecaoServicoTipoOperacao != null && !colecaoServicoTipoOperacao.isEmpty()) {
											ServicoTipoOperacao servicoTipoOperacao = (ServicoTipoOperacao) Util
													.retonarObjetoDeColecao(colecaoServicoTipoOperacao);
											String caminhoOperacao = servicoTipoOperacao.getOperacao().getCaminhoUrl();
											// caso exista o caminho da opera��o
											if (caminhoOperacao != null	&& !caminhoOperacao.equals("")) {
												int tamanhoOperacao = caminhoOperacao.length();
												// seta o caminho no mapeamento
												// para ser chamado
												String caminhoRetorno = caminhoOperacao.substring(
																0,	tamanhoOperacao - 3);
												httpServletRequest.setAttribute("veioEncerrarOSFuncManterRA",
													encerrarOrdemServicoActionForm.getNumeroOS());
												httpServletRequest.setAttribute("veioEncerrarOS",
														encerrarOrdemServicoActionForm.getNumeroOS() );
												httpServletRequest.setAttribute("semMenu","true" );
												httpServletRequest.setAttribute("dataEncerramento",
													encerrarOrdemServicoActionForm.getDataEncerramento());
												httpServletRequest.setAttribute("caminhoRetornoIntegracaoComercial",
													"exibirEncerrarOrdemServicoPopupAction.do?retornoConsulta=1");
												retorno = actionMapping.findForward(caminhoRetorno);
												if (retorno == null) {
													throw new ActionServletException(
															"atencao.caminho_url_indisponivel");
												} else {
													// caso seja chamado a
													// integra��o ent�o seta a
													// OS na sess�o com outro
													// nome e remove a OS da
													// sess�o visto que a
													// integra��o usa o mesmo
													// nome da OS que est� na
													// sess�o
													sessao.setAttribute("osFiscalizacao",
																	sessao.getAttribute("ordemServicoFiscalizacao"));
													sessao.removeAttribute("ordemServicoFiscalizacao");
												}

											}
										}
									}
								}
							}
						}
						if (retorno.getName().equalsIgnoreCase("encerrarOrdemServicoPopup")) {
							OrdemServico osFiscalizacao = null;

							if (integracaoComercialHelper == null
									|| integracaoComercialHelper.equals("") && sessao
									.getAttribute("ordemServicoFiscalizacao") != null ) {
								
								osFiscalizacao = (OrdemServico) sessao.getAttribute("ordemServicoFiscalizacao");
							}
							// caso a os fiscaliza��o n�o esteja na sess�o ent�o
							// n�o foi para integra��o e ent�o pode pegar o OS mesmo
							if (sessao.getAttribute("osFiscalizacao") != null
									&& !sessao.getAttribute("osFiscalizacao").equals("")) {
								
								osFiscalizacao = (OrdemServico) sessao.getAttribute("osFiscalizacao");
								
								sessao.removeAttribute("osFiscalizacao");
							}
							
							if ( sessao.getAttribute("ordemServicoFiscalizacao") != null ) {
								osFiscalizacao = (OrdemServico) sessao.getAttribute("ordemServicoFiscalizacao");
							}

							// se o servi�o tipo referencia seja igual a nulo
							if (encerrarOrdemServicoActionForm.getTipoServicoReferenciaId() == null
									|| encerrarOrdemServicoActionForm.getTipoServicoReferenciaId().equals("")) {
								
								//-----------------------------------------------------------------------------------------
//								 Alterado por Yara Taciane - 29/05/08, por conta do [UC457 - Encerrar Ordem de Servi�o]
								// 4.2. Caso o indicador de conserto do pavimento de RUA esteja indicado como sim, sistema dever� inserir 
								//a tabela ORDEM_SERVICO_PAVIMENTO, com ORSE_ID da tabela que est� sendo encerrada.
								// Analista: Fab�ola Ara�jo.	

								OrdemServicoPavimento ordemServicoPavimento = new OrdemServicoPavimento();								
								
								if(!"".equals(encerrarOrdemServicoActionForm.getIdPavimentoRua()) && encerrarOrdemServicoActionForm.getIdPavimentoRua()!=null){
									PavimentoRua pavimentoRua = new PavimentoRua();
									pavimentoRua.setId(Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getIdPavimentoRua()));
									ordemServicoPavimento.setPavimentoRua(pavimentoRua);
								}
								if(!"".equals(encerrarOrdemServicoActionForm.getMetragemPavimentoRua()) && encerrarOrdemServicoActionForm.getMetragemPavimentoRua()!=null){
									ordemServicoPavimento.setAreaPavimentoRua( Util.formatarMoedaRealparaBigDecimal( encerrarOrdemServicoActionForm.getMetragemPavimentoRua() ));
									
								}
								
								if(!"".equals(encerrarOrdemServicoActionForm.getIdPavimentoCalcada()) && encerrarOrdemServicoActionForm.getIdPavimentoCalcada()!=null){
									PavimentoCalcada pavimentoCalcada = new PavimentoCalcada();
									pavimentoCalcada.setId(Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getIdPavimentoCalcada()));
									ordemServicoPavimento.setPavimentoCalcada(pavimentoCalcada);
								}
								if(!"".equals(encerrarOrdemServicoActionForm.getMetragemPavimentoCalcada()) && encerrarOrdemServicoActionForm.getMetragemPavimentoCalcada()!=null){
									ordemServicoPavimento.setAreaPavimentoCalcada(Util.formatarMoedaRealparaBigDecimal(encerrarOrdemServicoActionForm.getMetragemPavimentoCalcada()));
									
								}
								
//								Unidade Repavimentadora
								if (!"-1".equals(encerrarOrdemServicoActionForm.getIdUnidadeRepavimentadora()) &&
										encerrarOrdemServicoActionForm.getIdUnidadeRepavimentadora() != null ) {
									
									//[FS0011] � Verificar exist�ncia da unidade repavimentadora
									fachada.verificaUnidadeTipoRepavimentadora( 
											encerrarOrdemServicoActionForm.getIdUnidadeRepavimentadora());
									
									UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
									unidadeOrganizacional.setId(new Integer(
											encerrarOrdemServicoActionForm.getIdUnidadeRepavimentadora()));

									ordemServicoPavimento.setUnidadeRepavimentadora(unidadeOrganizacional);
								} 
								//-------------------------------------------------------------------------------------------------------
								Collection<OrdemServicoFoto> colecaoOrdemServicoFoto = (Collection<OrdemServicoFoto>) sessao.getAttribute("colecaoOrdemServicoFoto");
//								[SB0002] - Encerrar com execu��o e sem refer�ncia
								fachada.encerrarOSComExecucaoSemReferencia(
										numeroOS, dataEncerramento, usuarioLogado,
										encerrarOrdemServicoActionForm.getIdMotivoEncerramento(),
										dataUltimaAlteracao,
                                        encerrarOrdemServicoActionForm.getObservacaoEncerramento(),
										encerrarOrdemServicoActionForm.getIndicadorPavimento(),
										encerrarOrdemServicoActionForm.getPavimento(),
										colecaoManterDadosAtividadesOrdemServicoHelper,
										integracaoComercialHelper,
										encerrarOrdemServicoActionForm.getTipoServicoOSId(),
										osFiscalizacao,	encerrarOrdemServicoActionForm.getIndicadorVistoriaServicoTipo(),
										encerrarOrdemServicoActionForm.getCodigoRetornoVistoriaOs(),
										ordemServicoPavimento, ordemServicoBoletim,indicadorServicoAceito,colecaoOrdemServicoFoto);

							} else {
								Collection<OrdemServicoFoto> colecaoOrdemServicoFoto = (Collection<OrdemServicoFoto>) sessao.getAttribute("colecaoOrdemServicoFoto");
								
								// [SB0003] - Encerrar com execu��o e com refer�ncia
								fachada.encerrarOSComExecucaoComReferencia(
										numeroOS,
										dataEncerramento,
										usuarioLogado,
										encerrarOrdemServicoActionForm.getIdMotivoEncerramento(),
										dataUltimaAlteracao,
										encerrarOrdemServicoActionForm.getObservacaoEncerramento(),
										encerrarOrdemServicoActionForm.getIndicadorPavimento(),
										encerrarOrdemServicoActionForm.getPavimento(),
										encerrarOrdemServicoActionForm.getIdTipoRetornoReferida(),
										encerrarOrdemServicoActionForm.getIndicadorDeferimento(),
										encerrarOrdemServicoActionForm.getIndicadorTrocaServico(),
										encerrarOrdemServicoActionForm.getIdServicoTipo(),
										encerrarOrdemServicoActionForm.getNumeroOSReferencia(),
										encerrarOrdemServicoActionForm.getServicoTipoReferenciaOS(),
										colecaoManterDadosAtividadesOrdemServicoHelper,
										integracaoComercialHelper,
										encerrarOrdemServicoActionForm.getTipoServicoOSId(),
										osFiscalizacao,
										encerrarOrdemServicoActionForm.getIndicadorVistoriaServicoTipo(),
										encerrarOrdemServicoActionForm.getCodigoRetornoVistoriaOs(),
										ordemServicoBoletim,indicadorServicoAceito,colecaoOrdemServicoFoto);

							}
						}

					}
				}
			}

			if (retorno.getName().equalsIgnoreCase("encerrarOrdemServicoPopup")) {
				 /**
			     * [UC0457] Encerrar Ordem de Servi�o
			     * 		5.4 do Fluxo Principal
			     * 
			     * @author Hugo Leonardo
			     * @created 17/02/2011
			     */
				
				/**
				 * [RM5185] 
				 * @author Magno Gouveia
				 * @since 08/08/2011
				 */
				FiltroTipoServico filtroTipoServico = new FiltroTipoServico();
				filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.ID, encerrarOrdemServicoActionForm.getTipoServicoOSId()));
				ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroTipoServico, ServicoTipo.class.getName()));
				
				if(servicoTipo.getIndicadorEncAutomaticoRAQndEncOS().compareTo(ConstantesSistema.NAO) == 0){
					
					boolean telaConfirmacao = fachada.tramitarOuEncerrarRADaOSEncerrada(
														Util.converterStringParaInteger(encerrarOrdemServicoActionForm.getNumeroOS()), usuarioLogado,
														encerrarOrdemServicoActionForm.getIdMotivoEncerramento(), 
									                    encerrarOrdemServicoActionForm.getNumeroRA(),
														encerrarOrdemServicoActionForm.getDataRoteiro());

					// se for para ir para a tela de confirma��o
					if (telaConfirmacao) {
						httpServletRequest.setAttribute("caminhoActionConclusao", "/gsan/encerrarOrdemServicoPopupAction.do");
						httpServletRequest.setAttribute("cancelamento", "TRUE");
						httpServletRequest.setAttribute("nomeBotao1", "Sim");
						httpServletRequest.setAttribute("nomeBotao2", "N�o");

						return montarPaginaConfirmacao("atencao.encerrar_RA_da_OS",	httpServletRequest, actionMapping);
					}
				}else{
					
					if ( encerrarOrdemServicoActionForm.getNumeroRA() != null && !encerrarOrdemServicoActionForm.getNumeroRA().equals("") ) {
					
					RegistroAtendimento registroAtendimento = new RegistroAtendimento();
					registroAtendimento.setId(Util
						.converterStringParaInteger(encerrarOrdemServicoActionForm.getNumeroRA()));
					AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
					atendimentoMotivoEncerramento.setId(Util
						.converterStringParaInteger(encerrarOrdemServicoActionForm.getIdMotivoEncerramento()));
					registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
					registroAtendimento.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
					
					if (encerrarOrdemServicoActionForm.getHoraEncerramento() != null
							&& !encerrarOrdemServicoActionForm.getHoraEncerramento().equals("")) {
						registroAtendimento.setDataEncerramento(Util
							.converteStringParaDateHora(encerrarOrdemServicoActionForm.getDataEncerramento()+ " "
							+ encerrarOrdemServicoActionForm.getHoraEncerramento() + ":00"));
					} else {
						registroAtendimento.setDataEncerramento(Util
								.converteStringParaDateHora(encerrarOrdemServicoActionForm
										.getDataEncerramento()+ " " +
										Util.formatarHoraSemSegundos(new Date()) + ":00"));
					}
					
//					registroAtendimento.setDataEncerramento(Util.converteStringParaDateHora(
//							encerrarOrdemServicoActionForm.getDataEncerramento()+ " " +
//							Util.formatarHoraSemSegundos(new Date()) + ":00"));
					if (encerrarOrdemServicoActionForm.getObservacaoEncerramento() != null
							&& !encerrarOrdemServicoActionForm.getObservacaoEncerramento().equals("")) {
						registroAtendimento.setObservacao(encerrarOrdemServicoActionForm.getObservacaoEncerramento());
					} else {
					    registroAtendimento.setObservacao("ENCERRADO ATRAV�S DA FUNCIONALIDADE ENCERRAMENTO DA ORDEM DE SERVI�O");
					}
					registroAtendimento.setUltimaAlteracao(new Date());

					// cria o objeto para a inser��o do registro de atendimento unidade
					RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
					registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);

					if (usuarioLogado.getUnidadeOrganizacional() != null
							&& !usuarioLogado.getUnidadeOrganizacional().equals("")) {
						registroAtendimentoUnidade.setUnidadeOrganizacional(usuarioLogado.getUnidadeOrganizacional());
					}
					registroAtendimentoUnidade.setUsuario(usuarioLogado);
					// atendimento rela��o tipo
					AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
					atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
					registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
					registroAtendimentoUnidade.setUltimaAlteracao(new Date());
					
					//Colocado por Raphael Rossiter em 10/03/2008
					ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = 
					fachada.obterDadosRegistroAtendimento(registroAtendimento.getId());
					
					SolicitacaoTipoEspecificacao especificacao = registroAtendimentoHelper
					.getRegistroAtendimento().getSolicitacaoTipoEspecificacao();
					
					if (especificacao.getDebitoTipo() != null){
						
						fachada.encerrarRegistroAtendimento(registroAtendimento,registroAtendimentoUnidade, 
						usuarioLogado, especificacao.getDebitoTipo().getId(), especificacao.getValorDebito(), 1, "100", false,null,false);
					}
					else{
						fachada.encerrarRegistroAtendimento(registroAtendimento,registroAtendimentoUnidade, 
						usuarioLogado, null, null, null, null, false,null,false);
					}
					
				}
				}	
			}
		} else {
			// se o usu�rio confirmar o encerramento da RA da OS que est� sendo encerrada
			if (valorConfirmacao.equals("ok")) {
				RegistroAtendimento registroAtendimento = new RegistroAtendimento();
				registroAtendimento.setId(Util
					.converterStringParaInteger(encerrarOrdemServicoActionForm.getNumeroRA()));
				AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
				atendimentoMotivoEncerramento.setId(Util
					.converterStringParaInteger(encerrarOrdemServicoActionForm.getIdMotivoEncerramento()));
				registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
				registroAtendimento.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
				
				if (encerrarOrdemServicoActionForm.getHoraEncerramento() != null
						&& !encerrarOrdemServicoActionForm.getHoraEncerramento().equals("")) {
					registroAtendimento.setDataEncerramento(Util
						.converteStringParaDateHora(encerrarOrdemServicoActionForm.getDataEncerramento()+ " "
						+ encerrarOrdemServicoActionForm.getHoraEncerramento() + ":00"));
				} else {
					registroAtendimento.setDataEncerramento(Util
							.converteStringParaDateHora(encerrarOrdemServicoActionForm
									.getDataEncerramento()+ " " +
									Util.formatarHoraSemSegundos(new Date()) + ":00"));
				}
				
//				registroAtendimento.setDataEncerramento(Util.converteStringParaDateHora(
//						encerrarOrdemServicoActionForm.getDataEncerramento()+ " " +
//						Util.formatarHoraSemSegundos(new Date()) + ":00"));
				if (encerrarOrdemServicoActionForm.getObservacaoEncerramento() != null
						&& !encerrarOrdemServicoActionForm.getObservacaoEncerramento().equals("")) {
					registroAtendimento.setObservacao(encerrarOrdemServicoActionForm.getObservacaoEncerramento());
				} else {
				    registroAtendimento.setObservacao("ENCERRADO ATRAV�S DA FUNCIONALIDADE ENCERRAMENTO DA ORDEM DE SERVI�O");
				}
				registroAtendimento.setUltimaAlteracao(new Date());

				// cria o objeto para a inser��o do registro de atendimento unidade
				RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
				registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);

				if (usuarioLogado.getUnidadeOrganizacional() != null
						&& !usuarioLogado.getUnidadeOrganizacional().equals("")) {
					registroAtendimentoUnidade.setUnidadeOrganizacional(usuarioLogado.getUnidadeOrganizacional());
				}
				registroAtendimentoUnidade.setUsuario(usuarioLogado);
				// atendimento rela��o tipo
				AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
				atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
				registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
				registroAtendimentoUnidade.setUltimaAlteracao(new Date());
				
				//Colocado por Raphael Rossiter em 10/03/2008
				ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = 
				fachada.obterDadosRegistroAtendimento(registroAtendimento.getId());
				
				SolicitacaoTipoEspecificacao especificacao = registroAtendimentoHelper
				.getRegistroAtendimento().getSolicitacaoTipoEspecificacao();
				
				if (especificacao.getDebitoTipo() != null){
					
					fachada.encerrarRegistroAtendimento(registroAtendimento,registroAtendimentoUnidade, 
					usuarioLogado, especificacao.getDebitoTipo().getId(), especificacao.getValorDebito(), 1, "100", false,null,false);
				}
				else{
					fachada.encerrarRegistroAtendimento(registroAtendimento,registroAtendimentoUnidade, 
					usuarioLogado, null, null, null, null, false,null,false);
				}
			}
		}
		sessao.removeAttribute("ordemServico");
		//sessao.removeAttribute("osFiscalizacao");
		sessao.removeAttribute("ordemServicoFiscalizacao");
		sessao.getAttribute("retornoTela");
		// volta para tela e limpa o popup
		httpServletRequest.setAttribute("fecharPopup", "SIM");

		return retorno;
	}
	
	 /**
     * [UC0457] Encerrar Ordem de Servi�o
     * [SB0007]- Gerar Informa��es para Boletim de Medi��o.
     * 
     * @author Vivianne Sousa
     * @created 28/01/2011
     */
	 private Map validarInformacoesBoletimMedicao(
	    		Integer idOrdemServico,EncerrarOrdemServicoActionForm form) {
	    		
	    	OrdemServicoBoletim ordemServicoBoletim = null;
	    	Boolean exibirMsgConfirmacao = false;
	    	
	    	if(form.getExibeIndicadorExistePavimento().equals("1")
			   || form.getExibeQtdeReposicaoAsfalto().equals("1")
			   || form.getExibeQtdeReposicaoCalcada().equals("1")	
			   || form.getExibeQtdeReposicaoParalelo().equals("1")){
	    		
	    		ordemServicoBoletim = new OrdemServicoBoletim();
	    		ordemServicoBoletim.setId(idOrdemServico);
	    		OrdemServico os = new OrdemServico();
	    		os.setId(idOrdemServico);
	    		ordemServicoBoletim.setOrdemServico(os);
	    		
	    		if(form.getExibeIndicadorExistePavimento().equals("1")){
	    			
	    			if(form.getIndicadorExistePavimento() == null){
	    				
	    				throw new ActionServletException("atencao.campo_selecionado.obrigatorio",
	    	 					null,"Existe Pavimento");
	    			}else{
	    				ordemServicoBoletim.setIndicadorPavimento(
	    					new Short(form.getIndicadorExistePavimento()));
	    			}
	    			
	    		}
	    		
	    		if(form.getExibeQtdeReposicaoAsfalto().equals("1") && form.getIndicadorExistePavimento() != null && !form.getIndicadorExistePavimento().equals("2")){
	    			
	    			if(form.getQtdeReposicaoAsfalto() == null || form.getQtdeReposicaoAsfalto().equals("")){
	    				
	    				form.setQtdeReposicaoAsfalto("0");
	    				ordemServicoBoletim.setNumeroReposicaoAsfalto(new BigDecimal(0));
	    				exibirMsgConfirmacao = true;
	    				
	    			}else if(form.getQtdeReposicaoAsfalto().equals("0") ||
	    					form.getQtdeReposicaoAsfalto().equals("00") ||
	    					form.getQtdeReposicaoAsfalto().equals("0,00")){
	    				ordemServicoBoletim.setNumeroReposicaoAsfalto(new BigDecimal(0));
	    				exibirMsgConfirmacao = true;
	    				
	    			}else{
	    				
	    				ordemServicoBoletim.setNumeroReposicaoAsfalto(Util.
	    					formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoAsfalto()));
	    			}
	    			
	    			
	    		}
	    		
	    		if(form.getExibeQtdeReposicaoParalelo().equals("1") && form.getIndicadorExistePavimento() != null && !form.getIndicadorExistePavimento().equals("2")){
	    			
	    			if(form.getQtdeReposicaoParalelo() == null || form.getQtdeReposicaoParalelo().equals("") ){
	    				
	    				form.setQtdeReposicaoParalelo("0");
	    				ordemServicoBoletim.setNumeroReposicaoParalelo(new BigDecimal(0));
	    				exibirMsgConfirmacao = true;
	    				
	    			}else if(form.getQtdeReposicaoParalelo().equals("0") ||
	    					form.getQtdeReposicaoParalelo().equals("00")||
	    					form.getQtdeReposicaoParalelo().equals("0,00")){
	    				ordemServicoBoletim.setNumeroReposicaoParalelo(new BigDecimal(0));
	    				exibirMsgConfirmacao = true;
	    				
	    			}else{
	    				
	    				ordemServicoBoletim.setNumeroReposicaoParalelo(Util.
	    					formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoParalelo()));
	    			}
	    			
	    			
	    		}
	    		
	    		if(form.getExibeQtdeReposicaoCalcada().equals("1") && form.getIndicadorExistePavimento() != null && !form.getIndicadorExistePavimento().equals("2")){
	    			
	    			if(form.getQtdeReposicaoCalcada() == null || form.getQtdeReposicaoCalcada().equals("")){
	    				
	    				form.setQtdeReposicaoCalcada("0");
	    				ordemServicoBoletim.setNumeroReposicaoCalcada(new BigDecimal(0));
	    				exibirMsgConfirmacao = true;
	    				
	    			}else if(form.getQtdeReposicaoCalcada().equals("0")||
	    					form.getQtdeReposicaoCalcada().equals("00")||
	    					form.getQtdeReposicaoCalcada().equals("0,00")){
	    				ordemServicoBoletim.setNumeroReposicaoCalcada(new BigDecimal(0));
	    				exibirMsgConfirmacao = true;
	    				
	    			}else{
	    				
	    				ordemServicoBoletim.setNumeroReposicaoCalcada(Util.
	    					formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoCalcada()));
	    			}
	    			
	    		}
	    		
	    		if(ordemServicoBoletim.getIndicadorPavimento().equals(ConstantesSistema.SIM)){
	    			// � obrigatorio informar "Tipo de Pavimento" e "Com Cal�ada?" 
	    			
	    			if(form.getTipoPavimento() == null || form.getTipoPavimento().equals("")){
	    				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Tipo de Pavimento");
	    			}
	    			
	    			if(form.getIndicadorExisteCalcada() == null || form.getIndicadorExisteCalcada().equals("")){
	    				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Com Cal�ada");
	    			}
	    			
	    			ordemServicoBoletim.setCodigotipoPavimento(new Short(form.getTipoPavimento()));
	    			ordemServicoBoletim.setIndicadorExisteCalcada(new Short(form.getIndicadorExisteCalcada()));
	    		}
	    	
	    		ordemServicoBoletim.setUltimaAlteracao(new Date());
	    	}
	    	
	    	Map retorno = new HashMap();
	    	retorno.put("ordemServicoBoletim",ordemServicoBoletim);
	    	retorno.put("exibirMsgConfirmacao",exibirMsgConfirmacao);
	    	
			return retorno;
		}
   
}