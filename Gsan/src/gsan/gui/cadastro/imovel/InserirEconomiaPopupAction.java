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
package gsan.gui.cadastro.imovel;

import gsan.cadastro.imovel.AreaConstruidaFaixa;
import gsan.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelEconomia;
import gsan.cadastro.imovel.ImovelSubcategoria;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.MaiorQue;
import gsan.util.filtro.MenorQue;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsavel para inserir o im�vel economia na cole��o do cliente
 * im�vel economia
 * 
 * @author S�vio Luiz
 * @created 20 de Maio de 2004
 */
public class InserirEconomiaPopupAction extends GcomAction {
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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("inserirEconomiaPopup");

		// Obt�m o action form
		EconomiaPopupActionForm economiaPopupActionForm = (EconomiaPopupActionForm) actionForm;

		// Obt�m a inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoClientesImoveisEconomia = null;

		//Collection colecaoImovelEconomiaClientes = null;

		Collection colecaoImovelEconomiasModificadas = null;

		ImovelSubcategoria imovelSubCategoria = (ImovelSubcategoria) sessao
				.getAttribute("imovelSubCategoria");

		Fachada fachada = Fachada.getInstancia();

		Collection imovelEconomias = null;

		if (imovelSubCategoria.getImovelEconomias() == null
				|| imovelSubCategoria.getImovelEconomias().equals("")) {

			imovelEconomias = new ArrayList();

		} else {
			imovelEconomias = imovelSubCategoria.getImovelEconomias();
		}

		if (imovelEconomias.size() <= imovelSubCategoria
				.getQuantidadeEconomias()) {

			// Cole��o vinda do imovel_inserir_economia_popup
			// com a cole��o de cliente, imovel e tipo da rela��o
			if (sessao.getAttribute("colecaoClientesImoveisEconomia") != null) {
				colecaoClientesImoveisEconomia = (Collection) sessao
						.getAttribute("colecaoClientesImoveisEconomia");

			} else {
				colecaoClientesImoveisEconomia = new ArrayList();
			}
			// pega da sess�o a cole��o para os imoveis economias inseridos e/ou
			// modificados
			colecaoImovelEconomiasModificadas = (Collection) sessao
					.getAttribute("colecaoImovelEconomiasModificadas");

			Imovel imovel = (Imovel) sessao.getAttribute("imovel");

			// Cria objeto areaConstruida
			AreaConstruidaFaixa areaConstruidaFaixa = null;
			BigDecimal areaConstruida = null;
			Integer areaContruidaFaixaForm = (new Integer(
					economiaPopupActionForm.getIdAreaConstruidaFaixa()
							.toString()));

			if (areaContruidaFaixaForm != null
					&& areaContruidaFaixaForm.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				areaConstruidaFaixa = new AreaConstruidaFaixa();
				areaConstruidaFaixa.setId(areaContruidaFaixaForm);
				/*if (economiaPopupActionForm.getAreaConstruida() != null
						&& !economiaPopupActionForm.getAreaConstruida().equals(
								"")) {
					
				}*/
			} else {
				if (economiaPopupActionForm.getAreaConstruida() != null
						&& !economiaPopupActionForm.getAreaConstruida().equals(
								"")) {
					Collection areasConstruidasFaixas = null;
					
					
					String areaConstuidaPrrv = economiaPopupActionForm.getAreaConstruida().replace(".", "");
					areaConstruida = (new BigDecimal(areaConstuidaPrrv.replace(",",".")));

					// Filtro AreaConstruidaFaixa
					FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = new FiltroAreaConstruidaFaixa();

					// fazer a parte de filtro adicionar parametro maior que
					// e filtro adicionar parametro menos que
					filtroAreaConstruidaFaixa
							.adicionarParametro(new ParametroSimples(
									FiltroAreaConstruidaFaixa.INDICADOR_USO,
									ConstantesSistema.INDICADOR_USO_ATIVO));

					filtroAreaConstruidaFaixa.adicionarParametro(new MaiorQue(
							FiltroAreaConstruidaFaixa.MAIOR_FAIXA,
							areaConstruida));

					filtroAreaConstruidaFaixa.adicionarParametro(new MenorQue(
							FiltroAreaConstruidaFaixa.MENOR_FAIXA,
							areaConstruida));

					areasConstruidasFaixas = fachada.pesquisar(
							filtroAreaConstruidaFaixa,
							AreaConstruidaFaixa.class.getName());

					if (areasConstruidasFaixas != null
							&& !areasConstruidasFaixas.isEmpty()) {
						Iterator areaContruidaFaixaIterator = areasConstruidasFaixas
								.iterator();

						areaConstruidaFaixa = (AreaConstruidaFaixa) areaContruidaFaixaIterator
								.next();

					}
				}
			}

			// recupera os dados do form
			String complementoEndereco = (String) economiaPopupActionForm
					.getComplementoEndereco();
			
			Short numeroMoradores = null;
			
			if (economiaPopupActionForm.getNumeroMorador() != null
					&& !economiaPopupActionForm.getNumeroMorador().equalsIgnoreCase("")){
				numeroMoradores = (new Short(economiaPopupActionForm
						.getNumeroMorador().toString()));
			}
			
			Short numeroPontoUtilizacao = null;
			if (economiaPopupActionForm.getNumeroPontosUtilizacao() != null 
					&& !economiaPopupActionForm.getNumeroPontosUtilizacao().equalsIgnoreCase("")){
				numeroPontoUtilizacao = (new Short(economiaPopupActionForm
						.getNumeroPontosUtilizacao().toString()));
			}
			
			BigDecimal numeroIptu = null;
			if (economiaPopupActionForm.getNumeroIptu() != null
					&& !economiaPopupActionForm.getNumeroIptu().equals("")) {
				// verifica se existe no imovel, no imovelEconomia ou na cole��o
				// que ser�
				// inserida se existe algum iptu cadastrado com o numero do iptu
				// digitado no
				// mesmo municipio
				fachada.verificarExistenciaIPTU(
						colecaoImovelEconomiasModificadas, imovel,
						economiaPopupActionForm.getNumeroIptu(), new Date());

				numeroIptu = (new BigDecimal(economiaPopupActionForm
						.getNumeroIptu().toString()));

			}

			Long numeroContratoEnergia = null;
			if (economiaPopupActionForm.getNumeroCelpe() != null
					&& !economiaPopupActionForm.getNumeroCelpe().equals("")) {

				// verifica se existe no imovel, no imovelEconomia ou na cole��o
				// que ser�
				// inserida se existe algum iptu cadastrado com o numero da
				// celpe digitado
				fachada.verificarExistenciaCelpe(
						colecaoImovelEconomiasModificadas, imovel,
						economiaPopupActionForm.getNumeroCelpe(), new Date());
				numeroContratoEnergia = (new Long(economiaPopupActionForm
						.getNumeroCelpe().toString()));

			}

			ImovelEconomia imovelEconomia = new ImovelEconomia(
					complementoEndereco, numeroMoradores,
					numeroPontoUtilizacao, numeroIptu, numeroContratoEnergia,
					areaConstruida, new Date(), areaConstruidaFaixa,
					imovelSubCategoria, new HashSet(
							colecaoClientesImoveisEconomia));

			// seta um codigo modificado para quando for atualizado esse imovel
			// economia
			// n�o colocar na cole��o de colecaoImovelEconomiasModificadas
			imovelEconomia.setCodigoModificado(imovelEconomia.getHashCode());

			if (!imovelEconomias.contains(imovelEconomia)) {
				imovelEconomias.add(imovelEconomia);
				imovelSubCategoria.setImovelEconomias(new HashSet(
						imovelEconomias));
				colecaoImovelEconomiasModificadas.add(imovelEconomia);

			} else {
				throw new ActionServletException(
						"atencao.ja_cadastrado.imovel_economia");
			}
			// para incluir mais rela��es entre cliente e imoveis, se preciso
			sessao.setAttribute("colecaoClientesImoveisEconomia",
					new ArrayList());

			// manda a cole��o para a sess�o
			sessao.setAttribute("colecaoImovelEconomiasModificadas",
					colecaoImovelEconomiasModificadas);

			// inicializa o property idClienteImovelUsuarioEconomias para a
			// inclus�o de
			// novas rela��es entre cliente imovel do tipo usu�rio
			economiaPopupActionForm.setIdClienteImovelUsuario(null);

			// inicializa todos os propertys para nulo
			economiaPopupActionForm.setComplementoEndereco(null);
			economiaPopupActionForm.setNumeroPontosUtilizacao(null);
			economiaPopupActionForm.setNumeroMorador(null);
			economiaPopupActionForm.setNumeroIptu(null);
			economiaPopupActionForm.setNumeroCelpe(null);
			economiaPopupActionForm.setAreaConstruida(null);
			economiaPopupActionForm.setIdAreaConstruidaFaixa(null);
			economiaPopupActionForm.setIdCliente(null);
			economiaPopupActionForm.setNomeCliente(null);

			if (httpServletRequest.getParameter("testeInserir")
					.equalsIgnoreCase("true")) {
				httpServletRequest.setAttribute("testeInserir", "true");
			}

			// Manda a cole��o do imovel_inserir_economia_popup
			// com a cole��o de cliente, imovel e tipo da rela��o
			sessao.setAttribute("contIdentificadorTemp", new Integer(
					imovelEconomias.size() + 1));

		} else {
			throw new ActionServletException(
					"atencao.quantidade_ultrapassada.imovel_economia", null,
					new Integer(imovelSubCategoria.getImovelEconomias().size())
							.toString());
		}

		return retorno;
	}
}
