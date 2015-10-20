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
package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gcom.cadastro.tarifasocial.FiltroRendaTipo;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialCarta;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialRevisaoMotivo;
import gcom.cadastro.tarifasocial.RendaTipo;
import gcom.cadastro.tarifasocial.TarifaSocialCarta;
import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialRevisaoMotivo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rafael Corr�a
 * @since 16/01/2007
 */
public class ExibirAtualizarTarifaSocialAction extends GcomAction {

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
				.findForward("exibirAtualizar");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarDadosTarifaSocialActionForm atualizarDadosTarifaSocialActionForm = (AtualizarDadosTarifaSocialActionForm) actionForm;
		
		FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo();
		filtroTarifaSocialCartaoTipo
				.adicionarParametro(new ParametroSimples(
						FiltroTarifaSocialCartaoTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroTarifaSocialCartaoTipo.setCampoOrderBy(FiltroTarifaSocialCartaoTipo.DESCRICAO);
		
		Collection colecaoTarifaSocialCartaoTipo = fachada.pesquisar(
				filtroTarifaSocialCartaoTipo,
				TarifaSocialCartaoTipo.class.getName());
		
		sessao.setAttribute("colecaoTarifaSocialCartaoTipo", colecaoTarifaSocialCartaoTipo);
		
		FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = new FiltroAreaConstruidaFaixa();
		filtroAreaConstruidaFaixa.adicionarParametro(new ParametroSimples(
				FiltroAreaConstruidaFaixa.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoAreaConstruidaFaixa = fachada.pesquisar(
				filtroAreaConstruidaFaixa,
				AreaConstruidaFaixa.class.getName());
		
		sessao.setAttribute("colecaoAreaConstruidaFaixa", colecaoAreaConstruidaFaixa);
		
		FiltroRendaTipo filtroRendaTipo = new FiltroRendaTipo();
		filtroRendaTipo.adicionarParametro(new ParametroSimples(
				FiltroRendaTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroRendaTipo.setCampoOrderBy(FiltroRendaTipo.DESCRICAO);
		
		Collection colecaoRendaTipo = fachada.pesquisar(
				filtroRendaTipo,
				RendaTipo.class.getName());
		
		sessao.setAttribute("colecaoRendaTipo", colecaoRendaTipo);
		
		FiltroTarifaSocialRevisaoMotivo filtroTarifaSocialRevisaoMotivo = new FiltroTarifaSocialRevisaoMotivo();
		filtroTarifaSocialRevisaoMotivo.adicionarParametro(new ParametroSimples(
				FiltroRendaTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroTarifaSocialRevisaoMotivo.setCampoOrderBy(FiltroTarifaSocialRevisaoMotivo.DESCRICAO);
		
		Collection colecaoMotivoRevisao = fachada.pesquisar(
				filtroTarifaSocialRevisaoMotivo,
				TarifaSocialRevisaoMotivo.class.getName());
		
		sessao.setAttribute("colecaoMotivoRevisao", colecaoMotivoRevisao);
		
		Collection colecaoTarifaSocialHelper = (Collection) sessao.getAttribute("colecaoTarifaSocialHelper");
		
		Iterator colecaoTarifaSocialHelperIterator = colecaoTarifaSocialHelper.iterator();
		
		TarifaSocialHelper tarifaSocialHelper = null;
		
		if (httpServletRequest.getAttribute("voltar") != null) {
			tarifaSocialHelper = (TarifaSocialHelper) sessao
					.getAttribute("tarifaSocialHelperAtualizar");
			httpServletRequest.setAttribute("voltar", true);
		} else {
			
			sessao.removeAttribute("colecaoClienteImovel");
			sessao.removeAttribute("colecaoClienteImovelEconomia");
			sessao.removeAttribute("tarifaSocialHelperAtualizar");

			// M�ltiplas Economias
			if (httpServletRequest.getParameter("idTarifaSocial") != null) {

				String idTarifaSocialDadoEconomia = httpServletRequest
						.getParameter("idTarifaSocial");

				while (colecaoTarifaSocialHelperIterator.hasNext()) {

					tarifaSocialHelper = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
							.next();

					if (tarifaSocialHelper.getTarifaSocialDadoEconomia()
							.getId().toString().equals(
									idTarifaSocialDadoEconomia)) {
						break;
					}

				}

			} else {
				// Uma Economia
				tarifaSocialHelper = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
						.next();
			}

			//	sessao.removeAttribute("colecaoClienteImovel");
			sessao.setAttribute("tarifaSocialHelper", tarifaSocialHelper);
		
		}
		
		// Seta os valores do objeto na tela
		TarifaSocialDadoEconomia tarifaSocialDadoEconomia = tarifaSocialHelper.getTarifaSocialDadoEconomia();
		ClienteImovel clienteImovel = tarifaSocialHelper.getClienteImovel();
		ClienteImovelEconomia clienteImovelEconomia = tarifaSocialHelper.getClienteImovelEconomia();
		
		// N�mero do Cart�o do Programa Social
		if (tarifaSocialDadoEconomia.getNumeroCartaoProgramaSocial() != null) {
			atualizarDadosTarifaSocialActionForm
					.setNumeroCartaoProgramaSocial(tarifaSocialDadoEconomia
							.getNumeroCartaoProgramaSocial().toString());
		} else {
			atualizarDadosTarifaSocialActionForm
					.setNumeroCartaoProgramaSocial("");
		}

		// Tipo do Cart�o do Programa Social
		if (tarifaSocialDadoEconomia.getTarifaSocialCartaoTipo() != null) {
			atualizarDadosTarifaSocialActionForm
					.setTipoCartao(tarifaSocialDadoEconomia
							.getTarifaSocialCartaoTipo().getId().toString());
		} else {
			atualizarDadosTarifaSocialActionForm.setTipoCartao(""
					+ ConstantesSistema.NUMERO_NAO_INFORMADO);
		}

		// Data de Validade do Cart�o
		if (tarifaSocialDadoEconomia.getDataValidadeCartao() != null) {
			String dataValidadeFormatada = Util
					.formatarData(tarifaSocialDadoEconomia
							.getDataValidadeCartao());
			atualizarDadosTarifaSocialActionForm
					.setDataValidadeCartao(dataValidadeFormatada);
		} else {
			atualizarDadosTarifaSocialActionForm.setDataValidadeCartao("");
		}
        FiltroTarifaSocialCarta filtro = new FiltroTarifaSocialCarta();
        filtro.adicionarParametro(
        	new ParametroSimples(
        		FiltroTarifaSocialCarta.IMOVEL, tarifaSocialDadoEconomia.getImovel().getId()));
        
        filtro.adicionarParametro(
            new ParametroNulo(
            		FiltroTarifaSocialCarta.DATA_EXECUCAO_COMANDO));

        filtro.adicionarParametro(
        	new ParametroNaoNulo(
            	FiltroTarifaSocialCarta.DATA_GERACAO_COMANDO));
        
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialCarta.TARIFA_SOCIAL_COMANDO_CARTA);
		
        Collection<TarifaSocialCarta> colecaoTarifaSocialCarta  = 
        	this.getFachada().pesquisar(filtro,TarifaSocialCarta.class.getName());
        
        if(colecaoTarifaSocialCarta != null && !colecaoTarifaSocialCarta.isEmpty()){
        	
        	TarifaSocialCarta tarifaSocialCarta = (TarifaSocialCarta) Util.retonarObjetoDeColecao(colecaoTarifaSocialCarta);
        	
        	String dataComparecimentoCarta = 
        		Util.formatarData(tarifaSocialCarta.getDataComparecimento());
        	
        	sessao.setAttribute("exibirDataComparecimentoCarta",true);
        	
        	
        	atualizarDadosTarifaSocialActionForm.setDataComparecimentoCarta(dataComparecimentoCarta);
        }else{
        	sessao.removeAttribute("exibirDataComparecimentoCarta");
        }
		

		// N�mero de Parcelas
		if (tarifaSocialDadoEconomia.getNumeroMesesAdesao() != null) {
			atualizarDadosTarifaSocialActionForm
					.setNumeroMesesAdesao(tarifaSocialDadoEconomia
							.getNumeroMesesAdesao().toString());
		} else {
			atualizarDadosTarifaSocialActionForm.setNumeroMesesAdesao("");
		}

		// Consumo M�dio da Companhia El�trica
		if (tarifaSocialDadoEconomia.getConsumoCelpe() != null) {
			atualizarDadosTarifaSocialActionForm
					.setConsumoCelpe(tarifaSocialDadoEconomia.getConsumoCelpe()
							.toString());
		} else {
			atualizarDadosTarifaSocialActionForm.setConsumoCelpe("");
		}

		// Valor Renda Familiar
		if (tarifaSocialDadoEconomia.getValorRendaFamiliar() != null) {
			String rendaFamiliarFormatada = Util
					.formatarMoedaReal(tarifaSocialDadoEconomia
							.getValorRendaFamiliar());
			atualizarDadosTarifaSocialActionForm
					.setValorRendaFamiliar(rendaFamiliarFormatada);
		} else {
			atualizarDadosTarifaSocialActionForm.setValorRendaFamiliar("");
		}

		// Tipo da Renda Familiar
		if (tarifaSocialDadoEconomia.getRendaTipo() != null) {
			atualizarDadosTarifaSocialActionForm
					.setRendaTipo(tarifaSocialDadoEconomia.getRendaTipo()
							.getId().toString());
		} else {
			atualizarDadosTarifaSocialActionForm.setRendaTipo(""
					+ ConstantesSistema.NUMERO_NAO_INFORMADO);
		}

		// Motivo de Revis�o
		if (tarifaSocialDadoEconomia.getTarifaSocialRevisaoMotivo() != null) {
			atualizarDadosTarifaSocialActionForm
					.setMotivoRevisao(tarifaSocialDadoEconomia
							.getTarifaSocialRevisaoMotivo().getId().toString());
		} else {
			atualizarDadosTarifaSocialActionForm.setMotivoRevisao(""
					+ ConstantesSistema.NUMERO_NAO_INFORMADO);
		}

		// Uma Economia
		if (clienteImovel != null) {

			// Nome do Cliente
			if (clienteImovel.getCliente() != null) {
				atualizarDadosTarifaSocialActionForm
						.setClienteNome(clienteImovel.getCliente().getNome());
			} else {
				atualizarDadosTarifaSocialActionForm.setClienteNome("");
			}

			// Complemento do Endere�o
			if (clienteImovel.getImovel().getComplementoEndereco() != null) {
				atualizarDadosTarifaSocialActionForm
						.setComplementoEndereco(clienteImovel.getImovel()
								.getComplementoEndereco());
			} else {
				atualizarDadosTarifaSocialActionForm.setComplementoEndereco("");
			}

			// N�mero de Contrato da Companhia El�trica
			if (clienteImovel.getImovel().getNumeroCelpe() != null) {
				atualizarDadosTarifaSocialActionForm
						.setNumeroContratoCelpe(clienteImovel.getImovel()
								.getNumeroCelpe().toString());
			} else {
				atualizarDadosTarifaSocialActionForm.setNumeroContratoCelpe("");
			}

			// N�mero do IPTU
			if (clienteImovel.getImovel().getNumeroIptu() != null) {
				atualizarDadosTarifaSocialActionForm
						.setNumeroIPTU(clienteImovel.getImovel().getNumeroIptu().toString());
			} else {
				atualizarDadosTarifaSocialActionForm.setNumeroIPTU("");
			}

			// �rea Constru�da
			if (clienteImovel.getImovel().getAreaConstruida() != null) {
				String areaConstruidaFormatada = Util
						.formatarMoedaReal(clienteImovel.getImovel()
								.getAreaConstruida());
				atualizarDadosTarifaSocialActionForm
						.setAreaConstruida(areaConstruidaFormatada);
			} else {
				atualizarDadosTarifaSocialActionForm.setAreaConstruida("");
			}

			// �rea Constru�da Faixa
			if (clienteImovel.getImovel().getAreaConstruidaFaixa() != null) {
				atualizarDadosTarifaSocialActionForm
						.setAreaConstruidaFaixa(clienteImovel.getImovel()
								.getAreaConstruidaFaixa().getId().toString());
			} else {
				atualizarDadosTarifaSocialActionForm.setAreaConstruidaFaixa(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO);
			}
			
			// N�mero de Moradores
			if (clienteImovel.getImovel().getNumeroMorador() != null) {
				atualizarDadosTarifaSocialActionForm
						.setNumeroMoradores(clienteImovel.getImovel().getNumeroMorador().toString());
			} else {
				atualizarDadosTarifaSocialActionForm.setNumeroMoradores("");
			}

		} 
		// M�ltiplas Economias
		else {
			// Nome do Cliente
			if (clienteImovelEconomia.getCliente() != null) {
				atualizarDadosTarifaSocialActionForm
						.setClienteNome(clienteImovelEconomia.getCliente()
								.getNome());
			} else {
				atualizarDadosTarifaSocialActionForm.setClienteNome("");
			}

			// Complemento do Endere�o
			if (clienteImovelEconomia.getImovelEconomia()
					.getComplementoEndereco() != null) {
				atualizarDadosTarifaSocialActionForm
						.setComplementoEndereco(clienteImovelEconomia
								.getImovelEconomia().getComplementoEndereco());
			} else {
				atualizarDadosTarifaSocialActionForm.setComplementoEndereco("");
			}

			// N�mero de Contrato da Companhia El�trica
			if (clienteImovelEconomia.getImovelEconomia().getNumeroCelpe() != null) {
				atualizarDadosTarifaSocialActionForm
						.setNumeroContratoCelpe(clienteImovelEconomia
								.getImovelEconomia().getNumeroCelpe()
								.toString());
			} else {
				atualizarDadosTarifaSocialActionForm.setNumeroContratoCelpe("");
			}

			// N�mero do IPTU
			if (clienteImovelEconomia.getImovelEconomia().getNumeroIptu() != null) {
				atualizarDadosTarifaSocialActionForm
						.setNumeroIPTU(clienteImovelEconomia.getImovelEconomia().getNumeroIptu().toString());
			} else {
				atualizarDadosTarifaSocialActionForm.setNumeroIPTU("");
			}

			// �rea Constru�da
			if (clienteImovelEconomia.getImovelEconomia().getAreaConstruida() != null) {
				String areaConstruidaFormatada = Util
						.formatarMoedaReal(clienteImovelEconomia
								.getImovelEconomia().getAreaConstruida());
				atualizarDadosTarifaSocialActionForm
						.setAreaConstruida(areaConstruidaFormatada);
			} else {
				atualizarDadosTarifaSocialActionForm.setAreaConstruida("");
			}

			// �rea Constru�da Faixa
			if (clienteImovelEconomia.getImovelEconomia()
					.getAreaConstruidaFaixa() != null) {
				atualizarDadosTarifaSocialActionForm
						.setAreaConstruidaFaixa(clienteImovelEconomia
								.getImovelEconomia().getAreaConstruidaFaixa()
								.getId().toString());
			} else {
				atualizarDadosTarifaSocialActionForm.setAreaConstruidaFaixa(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO);
			}
			
			// N�mero de Moradores
			if (clienteImovelEconomia.getImovelEconomia().getNumeroMorador() != null) {
				atualizarDadosTarifaSocialActionForm
						.setNumeroMoradores(clienteImovelEconomia
								.getImovelEconomia().getNumeroMorador()
								.toString());
			} else {
				atualizarDadosTarifaSocialActionForm.setNumeroMoradores("");
			}
		}
		
		return retorno;

	}

}
