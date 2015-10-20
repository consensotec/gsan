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
package gcom.gui.micromedicao.hidrometro;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ConsultarHistoricoInstalacaoHidrometroAction extends GcomAction {
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

		// Seta a a��o de retorno
		ActionForward retorno = null;
		ConsultarHistoricoInstalacaoHidrometroActionForm form = (ConsultarHistoricoInstalacaoHidrometroActionForm) actionForm;
        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

		String codigoImovel = null;
		if(httpServletRequest.getParameter("codigoImovel")!=null){
			codigoImovel = httpServletRequest.getParameter("codigoImovel");
		}else{
			if (form != null) {
				codigoImovel = form.getCodigoImovel();
			}
		}
		
		String codigoHidrometro = null;
		
		if(httpServletRequest.getParameter("codigoHidrometro")!=null){
			codigoHidrometro = httpServletRequest
				.getParameter("codigoHidrometro");
		}else{
			if (form != null) {
				codigoHidrometro =form.getCodigoHidrometro();
			}
		}

		// Verifica se o usu�rio digitou os dois c�digos
		if ((codigoImovel != null && !codigoImovel.equals(""))
				&& (codigoHidrometro != null && !codigoHidrometro.equals(""))) {
			throw new ActionServletException(
					"atencao.verificar.informado.imovel_hidrometro.ambos");
		}

		// Verifica se o usu�rio n�o digitou c�digo
		if ((codigoImovel != null && codigoImovel.equals(""))
				&& (codigoHidrometro != null && codigoHidrometro.equals(""))) {
			throw new ActionServletException(
					"atencao.verificar.informado.imovel_hidrometro");
		}

		// Obt�m a facahda
		Fachada fachada = Fachada.getInstancia();
		
		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			codigoImovel = codigoImovel.trim();
			// Seta o retorno para a p�gina que vai detalhar o imovel
			retorno = actionMapping.findForward("consultarImovel");

			// Pesquisa o imovel na base
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel
					.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
							codigoImovel, FiltroParametro.CONECTOR_OR));
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.LIGACAO_AGUA_ID, codigoImovel));

			
	
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial.municipio.unidadeFederacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
						
			Collection<Imovel> imovelPesquisado = fachada.pesquisar(
					filtroImovel, Imovel.class.getName());

			// obtem o imovel pesquisado
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				// Manda o Imovel pelo request
				httpServletRequest.setAttribute("imovel", imovel);

				// Faz uma busca do historico de instalacao do hidrometro para
				// mostrar na pagina
				FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();

				if (imovel.getLigacaoAgua() != null) {
					filtroHidrometroInstalacaoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroHidrometroInstalacaoHistorico.IMOVEL_ID,
									imovel.getId(), FiltroParametro.CONECTOR_OR));
					filtroHidrometroInstalacaoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroHidrometroInstalacaoHistorico.LIGACAO_AGUA_ID,
									imovel.getLigacaoAgua().getId()));
				} else {
					filtroHidrometroInstalacaoHistorico
							.adicionarParametro(new ParametroSimples(
									FiltroHidrometroInstalacaoHistorico.IMOVEL_ID,
									imovel.getId()));
				}

											
				filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");
				filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalInstalacao");
				filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("hidrometroProtecao");
				filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade("hidrometro");

				Collection<HidrometroInstalacaoHistorico> hidrometrosInstalacaoHistorico = fachada
						.pesquisar(filtroHidrometroInstalacaoHistorico,
								HidrometroInstalacaoHistorico.class.getName());
							
				// Manda a colecao do historico de instalacao do hidrometro para
				// a pagina
				httpServletRequest.setAttribute(
						"hidrometrosInstalacaoHistorico",
						hidrometrosInstalacaoHistorico);
				//((HidrometroInstalacaoHistorico) hidrometrosInstalacaoHistorico.iterator().next()).getHidrometro().getNumero();
				//((HidrometroInstalacaoHistorico) hidrometrosInstalacaoHistorico.iterator().next()).getHidrometro().getId();
				sessao.setAttribute("codigoImovel",codigoImovel);
				sessao.setAttribute("codigoHidrometro","");
			}

			// Se nenhum imovel for encontrado a mensagem � enviada para a
			// p�gina
			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Matr�cula");

			}

		}

		if (codigoHidrometro != null && !codigoHidrometro.trim().equals("")) {
			codigoHidrometro = codigoHidrometro.trim();
			codigoHidrometro = codigoHidrometro.toUpperCase();
			// Seta o retorno para a p�gina que vai detalhar o hidrometro
			retorno = actionMapping.findForward("consultarHidrometro");

			// Pesquisa o hidrometro na base
			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroMarca");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroSituacao");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroMotivoBaixa");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroClasseMetrologica");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroDiametro");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroTipo");

			filtroHidrometro.adicionarParametro(new ParametroSimples(
					FiltroHidrometro.NUMERO_HIDROMETRO, codigoHidrometro));

			Collection<Hidrometro> hidrometroPesquisado = fachada.pesquisar(
					filtroHidrometro, Hidrometro.class.getName());

			// Se nenhum hidrometro for encontrado a mensagem � enviada para a
			// p�gina
			if (hidrometroPesquisado != null && hidrometroPesquisado.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Hidr�metro");

			}

			// obtem o hidrometro pesquisado
			if (hidrometroPesquisado != null && !hidrometroPesquisado.isEmpty()) {
				Hidrometro hidrometro = hidrometroPesquisado.iterator().next();

				// Manda o Hidrometro pelo request
				httpServletRequest.setAttribute("hidrometro", hidrometro);

				// Faz uma busca do historico de instalacao do hidrometro para
				// mostrar na pagina
				FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
				filtroHidrometroInstalacaoHistorico
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroInstalacaoHistorico.HIDROMETRO_ID,
								hidrometro.getId()));
				filtroHidrometroInstalacaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");
				filtroHidrometroInstalacaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalInstalacao");
				filtroHidrometroInstalacaoHistorico
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroProtecao");
				filtroHidrometroInstalacaoHistorico
					.adicionarCaminhoParaCarregamentoEntidade("hidrometro");

				Collection<HidrometroInstalacaoHistorico> hidrometrosInstalacaoHistorico = fachada
						.pesquisar(filtroHidrometroInstalacaoHistorico,
								HidrometroInstalacaoHistorico.class.getName());
				// Manda a colecao do historico de instalacao do hidrometro para
				// a pagina
				httpServletRequest.setAttribute(
						"hidrometrosInstalacaoHistorico",
						hidrometrosInstalacaoHistorico);
				sessao.setAttribute("codigoHidrometro",codigoHidrometro);
				sessao.setAttribute("codigoImovel","");
				
				/** [MA2011061011]
				 * 	Autor: Paulo Diniz
				 * 	Data: 12/06/2011
				 *  Inclusão do campo Data da Última Movimentação
				 **/
				Date dataHidrometroMovimentado = fachada.pesquisarMaiorDataHidrometroMovimentado(hidrometro.getId());
				if(dataHidrometroMovimentado != null){
					httpServletRequest.setAttribute(
							"dataHidrometroMovimentacao",
							dataHidrometroMovimentado);
				}
				
			}

		}

		return retorno;

	}
}
