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
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.tarifasocial.FiltroRendaTipo;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.RendaTipo;
import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author rodrigo
 */
public class ExibirInserirTarifaSocialDadosCartaoUmaEconomiaAction extends
		GcomAction {
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("inserirTarifaSocialDadosCartao");

		// Instancia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		TarifaSocialCartaoActionForm tarifaSocialCartaoActionForm = (TarifaSocialCartaoActionForm) actionForm;

		FiltroTarifaSocialCartaoTipo filtro = new FiltroTarifaSocialCartaoTipo();

		filtro.setCampoOrderBy(FiltroTarifaSocialCartaoTipo.DESCRICAO);
		
		filtro.adicionarParametro(new ParametroSimples(FiltroTarifaSocialCartaoTipo.INDICADOR_USO,ConstantesSistema.SIM));

		FiltroRendaTipo filtroRendaTipo = new FiltroRendaTipo();

		filtroRendaTipo.setCampoOrderBy(FiltroRendaTipo.DESCRICAO);

		Collection colecaoTarifaSocialCartaoTipo = fachada.pesquisar(filtro,
				TarifaSocialCartaoTipo.class.getName());

		Collection colecaoRendaTipo = fachada.pesquisar(filtroRendaTipo,
				RendaTipo.class.getName());

		// A cole��o de tipos do cart�o � obrigat�ria na p�gina
		// [FS0004]
		if (colecaoTarifaSocialCartaoTipo == null
				|| colecaoTarifaSocialCartaoTipo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"tipo do cart�o");
		}

		// A cole��o de tipos de renda � obrigat�ria na p�gina
		// [FS0004]
		if (colecaoRendaTipo == null || colecaoRendaTipo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"tipo de renda");
		}

		httpServletRequest.setAttribute("colecaoTarifaSocialCartaoTipo",
				colecaoTarifaSocialCartaoTipo);

		httpServletRequest.setAttribute("colecaoRendaTipo", colecaoRendaTipo);
		
		FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = new FiltroAreaConstruidaFaixa();
		filtroAreaConstruidaFaixa.adicionarParametro(new ParametroSimples(
				FiltroAreaConstruidaFaixa.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoAreaConstruidaFaixa = fachada.pesquisar(
				filtroAreaConstruidaFaixa,
				AreaConstruidaFaixa.class.getName());
		
		sessao.setAttribute("colecaoAreaConstruidaFaixa", colecaoAreaConstruidaFaixa);

		// Pega o clienteImovel da sess�o e extrai o tarifaSocialDadoEconomia
		ClienteImovel clienteImovel = (ClienteImovel) sessao
				.getAttribute("clienteImovel");

		Collection colecaotarifaSocialDadoEconomia = fachada
				.pesquisarClientesUsuarioExistenteTarifaSocial(clienteImovel
						.getCliente().getId());

		if (colecaotarifaSocialDadoEconomia != null
				&& !colecaotarifaSocialDadoEconomia.isEmpty()) {
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) colecaotarifaSocialDadoEconomia
					.iterator().next();
			
			sessao.setAttribute("idTarifaSocialDadoEconomia", tarifaSocialDadoEconomia.getId().toString());

			if (tarifaSocialDadoEconomia.getImovel() != null) {
				tarifaSocialCartaoActionForm
						.setIdImovel(tarifaSocialDadoEconomia.getImovel()
								.getId().toString());
			}
			
			if (tarifaSocialDadoEconomia.getTarifaSocialRevisaoMotivo() != null) {
				tarifaSocialCartaoActionForm
						.setMotivoRevisao(tarifaSocialDadoEconomia
								.getTarifaSocialRevisaoMotivo().getDescricao());
			}
			
			FiltroTarifaSocialExclusaoMotivo filtroTarifaSocialExclusaoMotivo = new FiltroTarifaSocialExclusaoMotivo();
			filtroTarifaSocialExclusaoMotivo
					.adicionarParametro(new ParametroSimples(
							FiltroTarifaSocialExclusaoMotivo.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroTarifaSocialExclusaoMotivo.setCampoOrderBy(FiltroTarifaSocialExclusaoMotivo.DESCRICAO);
			
			Collection colecaoTarifaSocialExclusaoMotivo = fachada.pesquisar(
					filtroTarifaSocialExclusaoMotivo,
					TarifaSocialExclusaoMotivo.class.getName());
			
			httpServletRequest.setAttribute("colecaoTarifaSocialExclusaoMotivo", colecaoTarifaSocialExclusaoMotivo);
			

		}

		// TarifaSocialDado tarifaSocialDado = clienteImovel.getImovel()
		// .getTarifaSocialDado();

		if (sessao.getAttribute("colecaoDadosTarifaSocial") != null) {
			Collection colecaoDadosTarifaSocialMemoria = (Collection) sessao
					.getAttribute("colecaoDadosTarifaSocial");

			if (!colecaoDadosTarifaSocialMemoria.isEmpty()) {
				httpServletRequest
						.setAttribute(
								"tarifaSocialDadoEconomia",
								Util
										.retonarObjetoDeColecao(colecaoDadosTarifaSocialMemoria));
			}
		} else {
			FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();
			filtroTarifaSocialDadoEconomia
					.adicionarParametro(new ParametroSimples(
							FiltroTarifaSocialDadoEconomia.IMOVEL_ID,
							clienteImovel.getImovel().getId()));
			// Pesquisa na fachada a cole��o
			Collection colecaoTarifaSocialDadoEconomia = fachada.pesquisar(
					filtroTarifaSocialDadoEconomia,
					TarifaSocialDadoEconomia.class.getName());

			// Se existir, � colocado no request para a p�gina
			if (!colecaoTarifaSocialDadoEconomia.isEmpty()) {
				httpServletRequest.setAttribute("tarifaSocialDadoEconomia",
						colecaoTarifaSocialDadoEconomia.iterator().next());
			}
		}
		
		Imovel imovel = null;
		
		if (sessao.getAttribute("imovelAtualizado") != null) {
			
			imovel = (Imovel) sessao.getAttribute("imovelAtualizado");
			
		} else {
			
			imovel = clienteImovel.getImovel();
			
		}
		
		if (imovel != null) {
		
			// N�mero de Contrato da Companhia El�trica
			if (imovel.getNumeroCelpe() != null) {
				tarifaSocialCartaoActionForm
						.setNumeroContratoCelpe(imovel
								.getNumeroCelpe().toString());
			} else {
				tarifaSocialCartaoActionForm.setNumeroContratoCelpe("");
			}

			// N�mero do IPTU
			if (imovel.getNumeroIptu() != null) {
				tarifaSocialCartaoActionForm
						.setNumeroIPTU(imovel.getNumeroIptu().toString());
			} else {
				tarifaSocialCartaoActionForm.setNumeroIPTU("");
			}

			// �rea Constru�da
			if (imovel.getAreaConstruida() != null) {
				String areaConstruidaFormatada = Util
						.formatarMoedaReal(imovel
								.getAreaConstruida());
				tarifaSocialCartaoActionForm
						.setAreaConstruida(areaConstruidaFormatada);
			} else {
				tarifaSocialCartaoActionForm.setAreaConstruida("");
			}

			// �rea Constru�da Faixa
			if (imovel.getAreaConstruidaFaixa() != null) {
				tarifaSocialCartaoActionForm
						.setAreaConstruidaFaixa(imovel
								.getAreaConstruidaFaixa().getId().toString());
			} else {
				tarifaSocialCartaoActionForm.setAreaConstruidaFaixa(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO);
			}
			
			// N�mero de Moradores
			if (imovel.getNumeroMorador() != null) {
				tarifaSocialCartaoActionForm.setNumeroMoradores(imovel.getNumeroMorador().toString());
			} else {
				tarifaSocialCartaoActionForm.setNumeroMoradores("");
			}
			
		}
		
		// Carregar a data corrente do sistema
		// ====================================
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();

		// Data Corrente
		httpServletRequest.setAttribute("dataAtual", formatoData
				.format(dataCorrente.getTime()));

		return retorno;
	}
}
