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
package gsan.gui.cobranca;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cobranca.CobrancaCriterio;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite consultar comandos de a��o de cobran�a [UC0326] Filtrar Comandos de
 * A��o de Conbran�a - Eventual
 * 
 * @author Rafael Santos
 * @since 08/05/2006
 */
public class ExibirFiltrarComandosAcaoCobrancaEventualAction extends GcomAction {

	private String localidadeID = null;

	private String setorComercialCD = null;

	private HttpSession sessao;

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarComandosAcaoCobrancaEventual");

		Fachada fachada = Fachada.getInstancia();

		FiltrarComandosAcaoCobrancaEventualActionForm filtrarComandosAcaoCobrancaEventualActionForm = (FiltrarComandosAcaoCobrancaEventualActionForm) actionForm;

		String situacaoComando = filtrarComandosAcaoCobrancaEventualActionForm
		.getSituacaoComando();
		
		if((situacaoComando == null) || (situacaoComando.equals(""))){
			filtrarComandosAcaoCobrancaEventualActionForm
			.setSituacaoComando("Todos");	
		}
		
		String indicadorCriterio = filtrarComandosAcaoCobrancaEventualActionForm
		.getIndicadorCriterio();
		
		if(indicadorCriterio == null){
			filtrarComandosAcaoCobrancaEventualActionForm
			.setIndicadorCriterio("Todos");
		}

		sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("filtroCobrancaAcaoAtividadeComando");
		
		String carregando = httpServletRequest.getParameter("carregando");
		
		if(carregando != null && !carregando.equals("")){
			
			if (sessao.getAttribute("filtrarComandosAcaoCobrancaEventualActionForm") != null) {
				
				FiltrarComandosAcaoCobrancaEventualActionForm filtrarComandosAcaoCobrancaEventualActionFormRecarregar = 
		        	(FiltrarComandosAcaoCobrancaEventualActionForm) sessao.getAttribute("filtrarComandosAcaoCobrancaEventualActionForm");

				
				filtrarComandosAcaoCobrancaEventualActionForm.setAcaoCobranca(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getAcaoCobranca());
				filtrarComandosAcaoCobrancaEventualActionForm.setIndicadorCriterio(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIndicadorCriterio());
				filtrarComandosAcaoCobrancaEventualActionForm.setCriterioCobranca(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getCriterioCobranca());
				filtrarComandosAcaoCobrancaEventualActionForm.setAtividadeCobranca(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getAtividadeCobranca());
				filtrarComandosAcaoCobrancaEventualActionForm.setGrupoCobranca(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getGrupoCobranca());
				filtrarComandosAcaoCobrancaEventualActionForm.setGerenciaRegional(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getGerenciaRegional());
				filtrarComandosAcaoCobrancaEventualActionForm.setLocalidadeOrigemID(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getLocalidadeOrigemID());
				filtrarComandosAcaoCobrancaEventualActionForm.setLocalidadeDestinoID(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getLocalidadeDestinoID());
				filtrarComandosAcaoCobrancaEventualActionForm.setNomeLocalidadeOrigem(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getNomeLocalidadeOrigem());				
				filtrarComandosAcaoCobrancaEventualActionForm.setNomeLocalidadeDestino(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getNomeLocalidadeDestino());
				filtrarComandosAcaoCobrancaEventualActionForm.setSetorComercialOrigemCD(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getSetorComercialOrigemCD());
				filtrarComandosAcaoCobrancaEventualActionForm.setSetorComercialOrigemID(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getSetorComercialOrigemID());
				filtrarComandosAcaoCobrancaEventualActionForm.setSetorComercialDestinoCD(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getSetorComercialDestinoCD());
				filtrarComandosAcaoCobrancaEventualActionForm.setSetorComercialDestinoID(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getSetorComercialDestinoID());
				filtrarComandosAcaoCobrancaEventualActionForm.setNomeSetorComercialOrigem(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getNomeSetorComercialOrigem());
				filtrarComandosAcaoCobrancaEventualActionForm.setNomeSetorComercialDestino(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getNomeSetorComercialDestino());
				filtrarComandosAcaoCobrancaEventualActionForm.setUnidadeNegocio(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getUnidadeNegocio());
				
				filtrarComandosAcaoCobrancaEventualActionForm.setDataEmissaoInicio(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getDataEmissaoInicio());
				filtrarComandosAcaoCobrancaEventualActionForm.setDataEmissaoFim(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getDataEmissaoFim());
				filtrarComandosAcaoCobrancaEventualActionForm.setIdCobrancaAcaoAtividadeComando(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIdCobrancaAcaoAtividadeComando());
				
				if(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getRotaInicial() != null &&
						!filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getRotaInicial().equals("")){
					carregarRota(
							filtrarComandosAcaoCobrancaEventualActionForm,
							fachada, filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getSetorComercialOrigemCD(),
							filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getLocalidadeOrigemID());	
					filtrarComandosAcaoCobrancaEventualActionForm.setRotaInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getRotaInicial());
					
				}else{
					filtrarComandosAcaoCobrancaEventualActionForm.setRotaInicial(null);
					sessao.setAttribute("colecaoRota", null);
				}
				if(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getRotaFinal() != null &&
						!filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getRotaFinal().equals("")){
				
					filtrarComandosAcaoCobrancaEventualActionForm.setRotaFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getRotaFinal());
				}else{
					filtrarComandosAcaoCobrancaEventualActionForm.setRotaFinal(null);
					sessao.setAttribute("colecaoRota", null);
				}
				
				
				filtrarComandosAcaoCobrancaEventualActionForm.setClienteRelacaoTipo(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getClienteRelacaoTipo());
				filtrarComandosAcaoCobrancaEventualActionForm.setIdCliente(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIdCliente());
				filtrarComandosAcaoCobrancaEventualActionForm.setNomeCliente(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getNomeCliente());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoComandoInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoComandoInicial());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoComandoFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoComandoFinal());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoRealizacaoComandoInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoRealizacaoComandoInicial());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoRealizacaoComandoFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoRealizacaoComandoFinal());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoReferenciaContasInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoReferenciaContasInicial());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoReferenciaContasFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoReferenciaContasFinal());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoVencimentoContasInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoVencimentoContasInicial());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoVencimentoContasFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoVencimentoContasFinal());
				filtrarComandosAcaoCobrancaEventualActionForm.setIntervaloValorDocumentosInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIntervaloValorDocumentosInicial());
		        filtrarComandosAcaoCobrancaEventualActionForm.setIntervaloValorDocumentosFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIntervaloValorDocumentosFinal());
		        filtrarComandosAcaoCobrancaEventualActionForm.setIntervaloQuantidadeDocumentosInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIntervaloQuantidadeDocumentosInicial());
		        filtrarComandosAcaoCobrancaEventualActionForm.setIntervaloQuantidadeDocumentosFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIntervaloQuantidadeDocumentosFinal());
		        filtrarComandosAcaoCobrancaEventualActionForm.setIntervaloQuantidadeItensDocumentosInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIntervaloQuantidadeItensDocumentosInicial());
		        filtrarComandosAcaoCobrancaEventualActionForm.setIntervaloQuantidadeItensDocumentosFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIntervaloQuantidadeItensDocumentosFinal());
		        filtrarComandosAcaoCobrancaEventualActionForm.setSituacaoComando(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getSituacaoComando());
		        filtrarComandosAcaoCobrancaEventualActionForm.setQuantidadeDiasVencimento(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getQuantidadeDiasVencimento());
			}
			
		}
		
		//sessao = httpServletRequest.getSession(false);

		// CARREGAR AS COBRAN�AS GRUPO
		if (sessao.getAttribute("colecaoGrupoCobranca") == null) {
			sessao.setAttribute("colecaoGrupoCobranca", fachada
					.obterColecaoCobrancaGrupo());
		}

		// CARREGAR AS COBRAN�AS ATIVIDADE
		if (sessao.getAttribute("colecaoAtividadeCobranca") == null) {
			sessao.setAttribute("colecaoAtividadeCobranca", fachada
					.obterColecaoCobrancaAtividade());
		}

		// CARREGAR AS COBRAN�AS ACAO
		if (sessao.getAttribute("colecaoAcaoCobranca") == null) {
			sessao.setAttribute("colecaoAcaoCobranca", fachada
					.obterColecaoCobrancaAcao());
		}

		// CARREGAR AS GERENCIAIS REGIONAIS
		if (sessao.getAttribute("colecaoGerenciaRegional") == null) {
			sessao.setAttribute("colecaoGerenciaRegional", fachada
					.obterColecaoGerenciaRegional());
		}

		// CARREGAR AS UNIDADE NEGOCIO
		if (sessao.getAttribute("colecaoUnidadeNegocio") == null) {
			sessao.setAttribute("colecaoUnidadeNegocio", fachada
					.obterColecaoGerenciaRegional());
		}
		
		
		// CARREGAR OS CLIENTE RELACAO TIPO
		if (sessao.getAttribute("colecaoClienteRelacaoTipo") == null) {
			sessao.setAttribute("colecaoClienteRelacaoTipo", fachada
					.obterColecaoClienteRelacaoTipo());
		}
		
		//CARREGAR OS TITULOS DE COBRANCA ACAO ATIVIDAD COMAND
		if (sessao.getAttribute("colecaoCobrancaAcaoAtividadeComando") == null) {
			
			Collection colecaoAtividadesEventuaisAcaoCobrancaComandadas = fachada.obterListaAtividadesEventuaisAcaoCobrancaComandadas();
			
			sessao.setAttribute("colecaoCobrancaAcaoAtividadeComando", colecaoAtividadesEventuaisAcaoCobrancaComandadas);
		}

		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		String inscricaoTipo = (String) httpServletRequest
				.getParameter("inscricaoTipo");

		String rota = (String) httpServletRequest
		.getParameter("rota");
		
		
		//String idAcaoCobranca = (String) httpServletRequest
		//.getParameter("idAcaoCobranca");

		//String limparCriterioCobranca = (String) httpServletRequest
		//.getParameter("limparCriterioCobranca");
		
		//carregar as rotas
		if (rota != null && !rota.trim().equalsIgnoreCase("")){
			
			carregarRota(
					filtrarComandosAcaoCobrancaEventualActionForm,
					fachada, filtrarComandosAcaoCobrancaEventualActionForm.getSetorComercialOrigemCD(),
					filtrarComandosAcaoCobrancaEventualActionForm.getLocalidadeOrigemID());
			
		}
		
		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")
				&& inscricaoTipo != null
				&& !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:

				pesquisarLocalidade(inscricaoTipo,
						filtrarComandosAcaoCobrancaEventualActionForm, fachada,
						httpServletRequest);

				break;
			// Setor Comercial
			case 2:

				pesquisarLocalidade(inscricaoTipo,
						filtrarComandosAcaoCobrancaEventualActionForm, fachada,
						httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
						filtrarComandosAcaoCobrancaEventualActionForm, fachada,
						httpServletRequest);

				break;
			case 3:
				pesquisarCliente(inscricaoTipo,
						filtrarComandosAcaoCobrancaEventualActionForm, fachada,
						httpServletRequest);
				break;

			default:
				break;
			}
		}

		String criterioCobranca = filtrarComandosAcaoCobrancaEventualActionForm
				.getCriterioCobranca();
		// pesquisar o crit�rio de cobran�a
		if (criterioCobranca != null && !criterioCobranca.equals("")) {

			if (temLetra(criterioCobranca) == true){
				filtrarComandosAcaoCobrancaEventualActionForm.setCriterioCobranca("");
				filtrarComandosAcaoCobrancaEventualActionForm.setNomeCriterioCobranca("Crit�rio de Cobran�a Inexistente");
				httpServletRequest.setAttribute("corCriterioCobranca", "exception");
				httpServletRequest.setAttribute("nomeCampo", "criterioCobranca");
			} else {
			
			CobrancaCriterio cobrancaCriterio = fachada
					.obterCobrancaCriterio(criterioCobranca);

			if (cobrancaCriterio == null) {

				filtrarComandosAcaoCobrancaEventualActionForm
						.setCriterioCobranca("");
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeCriterioCobranca("Crit�rio de Cobran�a Inexistente");
				httpServletRequest.setAttribute("corCriterioCobranca",
						"exception");
				httpServletRequest
						.setAttribute("nomeCampo", "criterioCobranca");

			} else {
				filtrarComandosAcaoCobrancaEventualActionForm
						.setCriterioCobranca(String.valueOf(cobrancaCriterio
								.getId()));
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeCriterioCobranca(cobrancaCriterio
								.getDescricaoCobrancaCriterio());
				httpServletRequest.setAttribute("corCriterioCobranca", "valor");

				httpServletRequest.setAttribute("nomeCampo", "grupoCobranca");

			}

			}
		}

		return retorno;
	}

	/**
	 * Pesquisa a Localidade
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarLocalidade(
			String inscricaoTipo,
			FiltrarComandosAcaoCobrancaEventualActionForm filtrarComandosAcaoCobrancaEventualActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			filtrarComandosAcaoCobrancaEventualActionForm
					.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			localidadeID = (String) filtrarComandosAcaoCobrancaEventualActionForm
					.getLocalidadeOrigemID();

			Localidade objetoLocalidade = fachada
					.obterLocalidadeGerenciaRegional(localidadeID);

			if (objetoLocalidade == null) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formul�rio
				filtrarComandosAcaoCobrancaEventualActionForm
						.setLocalidadeOrigemID("");
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeLocalidadeOrigem("Localidade Inexistente");
				httpServletRequest.setAttribute("corLocalidadeOrigem",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeOrigemID");

			} else {
				filtrarComandosAcaoCobrancaEventualActionForm
						.setLocalidadeOrigemID(String.valueOf(objetoLocalidade
								.getId()));
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeLocalidadeOrigem(objetoLocalidade
								.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");

				String localidadeDestinoID = (String) filtrarComandosAcaoCobrancaEventualActionForm
						.getLocalidadeDestinoID();
				// verifica o valor das localidades, origem e final
				if (localidadeDestinoID != null) {

					if (localidadeDestinoID.equals("")) {
						filtrarComandosAcaoCobrancaEventualActionForm
								.setLocalidadeDestinoID(String
										.valueOf(objetoLocalidade.getId()));
						filtrarComandosAcaoCobrancaEventualActionForm
								.setNomeLocalidadeDestino(objetoLocalidade
										.getDescricao());
					} else {
						int localidadeDestino = new Integer(localidadeDestinoID)
								.intValue();
						int localidadeOrigem = objetoLocalidade.getId()
								.intValue();
						if (localidadeOrigem > localidadeDestino) {
							filtrarComandosAcaoCobrancaEventualActionForm
									.setLocalidadeDestinoID(String
											.valueOf(objetoLocalidade.getId()));
							filtrarComandosAcaoCobrancaEventualActionForm
									.setNomeLocalidadeDestino(objetoLocalidade
											.getDescricao());
						}
					}
				}
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeDestinoID");

			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formul�rio.
			localidadeID = (String) filtrarComandosAcaoCobrancaEventualActionForm
					.getLocalidadeDestinoID();

			Localidade objetoLocalidade = fachada
					.obterLocalidadeGerenciaRegional(localidadeID);

			filtrarComandosAcaoCobrancaEventualActionForm
					.setInscricaoTipo("destino");

			if (objetoLocalidade == null) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formul�rio
				filtrarComandosAcaoCobrancaEventualActionForm
						.setLocalidadeDestinoID("");
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeLocalidadeDestino("Localidade inexistente");
				httpServletRequest.setAttribute("corLocalidadeDestino",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeDestinoID");
			} else {
				int localidadeDestino = objetoLocalidade.getId().intValue();

				String localidade = (String) filtrarComandosAcaoCobrancaEventualActionForm
						.getLocalidadeOrigemID();

				if (localidade != null && !localidade.equals("")) {

					int localidadeOrigem = new Integer(localidade).intValue();
					if (localidadeDestino < localidadeOrigem) {
						filtrarComandosAcaoCobrancaEventualActionForm
								.setLocalidadeDestinoID("");
						// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						// .setNomeLocalidadeDestino("Loc. Final maior que a
						// Inicial");
						httpServletRequest.setAttribute("mensagem",
								"Localidae Final menor que o Inicial");
						filtrarComandosAcaoCobrancaEventualActionForm
								.setNomeLocalidadeDestino("");
						httpServletRequest.setAttribute("corLocalidadeDestino",
								"valor");

						httpServletRequest.setAttribute("nomeCampo",
								"localidadeDestinoID");

					} else {
						filtrarComandosAcaoCobrancaEventualActionForm
								.setLocalidadeDestinoID(String
										.valueOf(objetoLocalidade.getId()));
						filtrarComandosAcaoCobrancaEventualActionForm
								.setNomeLocalidadeDestino(objetoLocalidade
										.getDescricao());
						httpServletRequest.setAttribute("corLocalidadeDestino",
								"valor");
						httpServletRequest.setAttribute("nomeCampo",
								"setorComercialOrigemCD");

						pesquisarLocalidade("origem",
								filtrarComandosAcaoCobrancaEventualActionForm,
								fachada, httpServletRequest);
					}
				} else {
					filtrarComandosAcaoCobrancaEventualActionForm
							.setLocalidadeDestinoID(String
									.valueOf(objetoLocalidade.getId()));
					filtrarComandosAcaoCobrancaEventualActionForm
							.setNomeLocalidadeDestino(objetoLocalidade
									.getDescricao());
					httpServletRequest.setAttribute("corLocalidadeDestino",
							"valor");
					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialOrigemCD");
					pesquisarLocalidade("origem",
							filtrarComandosAcaoCobrancaEventualActionForm,
							fachada, httpServletRequest);

				}
			}
		}

	}

	/**
	 * Pesquisa o Setor Comercial para ser usado no Filtrar Comandos de A��o de
	 * Cobran�a
	 * 
	 * @param inscricaoTipo
	 * @param filtrarComandosAcaoCobrancaEventualActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarSetorComercial(
			String inscricaoTipo,
			FiltrarComandosAcaoCobrancaEventualActionForm filtrarComandosAcaoCobrancaEventualActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			filtrarComandosAcaoCobrancaEventualActionForm
					.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			localidadeID = (String) filtrarComandosAcaoCobrancaEventualActionForm
					.getLocalidadeOrigemID();

			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			setorComercialCD = (String) filtrarComandosAcaoCobrancaEventualActionForm
					.getSetorComercialOrigemCD();

			// O campo localidadeOrigemID ser� obrigat�rio
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				SetorComercial objetoSetorComercial = fachada
						.obterSetorComercialLocalidade(localidadeID,
								setorComercialCD);

				if (objetoSetorComercial == null) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formul�rio
					filtrarComandosAcaoCobrancaEventualActionForm
							.setSetorComercialOrigemCD("");
					filtrarComandosAcaoCobrancaEventualActionForm
							.setSetorComercialOrigemID("");
					filtrarComandosAcaoCobrancaEventualActionForm
							.setNomeSetorComercialOrigem("Setor Comercial Inexistente");
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"exception");
					filtrarComandosAcaoCobrancaEventualActionForm
							.setRotaInicial(null);
					filtrarComandosAcaoCobrancaEventualActionForm
							.setRotaFinal(null);

					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialOrigemCD");

				} else {
					// setorComercialID =
					// objetoSetorComercial.getId().toString();
					// setorComercialOrigem
					filtrarComandosAcaoCobrancaEventualActionForm
							.setSetorComercialOrigemCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					filtrarComandosAcaoCobrancaEventualActionForm
							.setSetorComercialOrigemID(String
									.valueOf(objetoSetorComercial.getId()));
					filtrarComandosAcaoCobrancaEventualActionForm
							.setNomeSetorComercialOrigem(objetoSetorComercial
									.getDescricao());
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"valor");

					String setorComercialDestinoCD = (String) filtrarComandosAcaoCobrancaEventualActionForm
							.getSetorComercialDestinoCD();

					// verifica o valor dos setores comerciais, origem e final
					if (setorComercialDestinoCD != null) {

						if (setorComercialDestinoCD.equals("")) {

							// setorComercialDestino
							filtrarComandosAcaoCobrancaEventualActionForm
									.setSetorComercialDestinoCD(String
											.valueOf(objetoSetorComercial
													.getCodigo()));
							filtrarComandosAcaoCobrancaEventualActionForm
									.setSetorComercialDestinoID(String
											.valueOf(objetoSetorComercial
													.getId()));
							filtrarComandosAcaoCobrancaEventualActionForm
									.setNomeSetorComercialDestino(objetoSetorComercial
											.getDescricao());

							carregarRota(
									filtrarComandosAcaoCobrancaEventualActionForm,
									fachada, objetoSetorComercial.getCodigo()
											+ "",localidadeID);

						} else {

							int setorDestino = new Integer(
									setorComercialDestinoCD).intValue();
							int setorOrigem = objetoSetorComercial.getCodigo();
							if (setorOrigem > setorDestino) {

								// setorComercialDestino
								filtrarComandosAcaoCobrancaEventualActionForm
										.setSetorComercialDestinoCD(String
												.valueOf(objetoSetorComercial
														.getCodigo()));
								filtrarComandosAcaoCobrancaEventualActionForm
										.setSetorComercialDestinoID(String
												.valueOf(objetoSetorComercial
														.getId()));
								filtrarComandosAcaoCobrancaEventualActionForm
										.setNomeSetorComercialDestino(objetoSetorComercial
												.getDescricao());

								carregarRota(
										filtrarComandosAcaoCobrancaEventualActionForm,
										fachada, objetoSetorComercial
												.getCodigo()
												+ "",localidadeID);
							}
						}
						httpServletRequest.setAttribute("nomeCampo",
								"setorComercialDestinoCD");
					}
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formul�rio
				filtrarComandosAcaoCobrancaEventualActionForm
						.setSetorComercialOrigemCD("");
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeSetorComercialOrigem("Informe a localidade da inscri��o de origem.");
				httpServletRequest.setAttribute("corSetorComercialOrigem",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialOrigemCD");
			}
		} else {

			filtrarComandosAcaoCobrancaEventualActionForm
					.setInscricaoTipo("destino");

			// Recebe o valor do campo localidadeDestinoID do formul�rio.
			localidadeID = (String) filtrarComandosAcaoCobrancaEventualActionForm
					.getLocalidadeDestinoID();

			// O campo localidadeOrigem ser� obrigat�rio
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) filtrarComandosAcaoCobrancaEventualActionForm
						.getSetorComercialDestinoCD();

				SetorComercial objetoSetorComercial = fachada
						.obterSetorComercialLocalidade(localidadeID,
								setorComercialCD);

				if (objetoSetorComercial == null) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formul�rio
					filtrarComandosAcaoCobrancaEventualActionForm
							.setSetorComercialDestinoCD("");
					filtrarComandosAcaoCobrancaEventualActionForm
							.setSetorComercialDestinoID("");
					filtrarComandosAcaoCobrancaEventualActionForm
							.setNomeSetorComercialDestino("Setor Comercial Inexistente");
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"exception");
					filtrarComandosAcaoCobrancaEventualActionForm
							.setRotaInicial(null);
					filtrarComandosAcaoCobrancaEventualActionForm
							.setRotaFinal(null);
					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialDestinoCD");
				} else {
					int setorDestino = objetoSetorComercial.getCodigo();

					String setor = (String) filtrarComandosAcaoCobrancaEventualActionForm
							.getSetorComercialOrigemCD();

					if (setor != null && !setor.equals("")) {

						int setorOrigem = new Integer(setor).intValue();
						if (setorDestino < setorOrigem) {

							filtrarComandosAcaoCobrancaEventualActionForm
									.setSetorComercialDestinoCD("");
							filtrarComandosAcaoCobrancaEventualActionForm
									.setSetorComercialDestinoID("");
							// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							// .setNomeSetorComercialDestino("Setor Final maior
							// que Inicial");
							httpServletRequest
									.setAttribute("mensagem",
											"Setor Comercial Final menor que o Inicial");
							filtrarComandosAcaoCobrancaEventualActionForm
									.setNomeSetorComercialDestino("");
							httpServletRequest.setAttribute(
									"corSetorComercialDestino", "valor");

							filtrarComandosAcaoCobrancaEventualActionForm
									.setRotaInicial(null);
							filtrarComandosAcaoCobrancaEventualActionForm
									.setRotaFinal(null);
							httpServletRequest.setAttribute("nomeCampo",
									"setorComercialDestinoCD");

						} else {
							// rota
							carregarRota(
									filtrarComandosAcaoCobrancaEventualActionForm,
									fachada, objetoSetorComercial.getCodigo()
											+ "",localidadeID);

							// setor comercial destino
							filtrarComandosAcaoCobrancaEventualActionForm
									.setSetorComercialDestinoCD(String
											.valueOf(objetoSetorComercial
													.getCodigo()));
							filtrarComandosAcaoCobrancaEventualActionForm
									.setSetorComercialDestinoID(String
											.valueOf(objetoSetorComercial
													.getId()));
							filtrarComandosAcaoCobrancaEventualActionForm
									.setNomeSetorComercialDestino(objetoSetorComercial
											.getDescricao());
							httpServletRequest.setAttribute(
									"corSetorComercialDestino", "valor");
							httpServletRequest.setAttribute("nomeCampo",
									"rotaFinal");
						}
					} else {

						carregarRota(
								filtrarComandosAcaoCobrancaEventualActionForm,
								fachada, objetoSetorComercial.getCodigo() + "",localidadeID);

						// setor comercial destino
						filtrarComandosAcaoCobrancaEventualActionForm
								.setSetorComercialDestinoCD(String
										.valueOf(objetoSetorComercial
												.getCodigo()));
						filtrarComandosAcaoCobrancaEventualActionForm
								.setSetorComercialDestinoID(String
										.valueOf(objetoSetorComercial.getId()));
						filtrarComandosAcaoCobrancaEventualActionForm
								.setNomeSetorComercialDestino(objetoSetorComercial
										.getDescricao());
						httpServletRequest.setAttribute(
								"corSetorComercialDestino", "valor");
						httpServletRequest.setAttribute("nomeCampo",
								"rotaFinal");

					}
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formul�rio
				filtrarComandosAcaoCobrancaEventualActionForm
						.setSetorComercialDestinoCD("");
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeSetorComercialDestino("Informe a localidade da inscri��o de destino.");
				httpServletRequest.setAttribute("corSetorComercialDestino",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialDestinoCD");

			}
		}

	}

	/**
	 * Inicializa a Rota
	 * 
	 * @param inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
	 * @param fachada
	 * @param objetoSetorComercial
	 */
	public void carregarRota(
			FiltrarComandosAcaoCobrancaEventualActionForm filtrarComandosAcaoCobrancaEventualActionForm,
			Fachada fachada, String codigoSetorComercial,String idLocalidade) {

		Collection colecaoRota = fachada
				.obterColecaoRotaSetorComercial(codigoSetorComercial,idLocalidade);

		sessao.setAttribute("colecaoRota", colecaoRota);
		filtrarComandosAcaoCobrancaEventualActionForm.setRotaInicial("");
		filtrarComandosAcaoCobrancaEventualActionForm.setRotaFinal("");

	}

	/**
	 * Pesquisa o Setor Comercial
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarCliente(
			String inscricaoTipo,
			FiltrarComandosAcaoCobrancaEventualActionForm filtrarComandosAcaoCobrancaEventualActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		String idCliente = filtrarComandosAcaoCobrancaEventualActionForm
				.getIdCliente();

		// -------Parte que trata do c�digo quando o usu�rio tecla enter
		// se o id do cliente for diferente de nulo
		if (idCliente != null
				&& !idCliente.toString().trim().equalsIgnoreCase("")) {

			Cliente cliente = fachada.obterCliente(idCliente);

			if (cliente != null) {
				// O cliente foi encontrado
				filtrarComandosAcaoCobrancaEventualActionForm
						.setIdCliente(cliente.getId().toString());
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeCliente(cliente.getNome());

				sessao.setAttribute("clienteObj", cliente);
			} else {
				httpServletRequest.setAttribute("codigoClienteNaoEncontrado",
						"true");
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeCliente("Cliente Inexistente");
				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}

		}

	}
	
	private Boolean temLetra(String texto){
        String numeros = "0123456789";
        Boolean retorno = null;	
        texto = texto.toLowerCase();
        for(int i=0; i<texto.length(); i++){
             if (numeros.indexOf(texto.charAt(i),0)!=-1){
             retorno = false;
             }else{
             retorno = true;
             i = texto.length();
             }
        }
        return retorno;
   }


}
