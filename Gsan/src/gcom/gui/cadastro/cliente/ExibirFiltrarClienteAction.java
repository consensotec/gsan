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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0000] Filtrar Cliente
 * Pr�-processamento para a p�gina de filtro de cliente
 * 
 * @author Rodrigo Silveira, Roberta Costa
 * @created 23/07/2005, 12/07/2006
 */
public class ExibirFiltrarClienteAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("filtrarCliente");

		FiltrarClienteActionForm filtrarClienteActionForm = (FiltrarClienteActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		if(filtrarClienteActionForm.getAtualizarFiltro()== null
				|| filtrarClienteActionForm.getAtualizarFiltro().equals("") ){
			filtrarClienteActionForm.setAtualizarFiltro("1");
			httpServletRequest.setAttribute("nomeCampo", "cpfClienteFiltro");
		}

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if (sessao.getAttribute("consultaCliente") != null) {
			sessao.removeAttribute("consultaCliente");
		}
		
		if (httpServletRequest.getParameter("menu") != null && !httpServletRequest.getParameter("menu").trim().equals("")) {
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
			filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
		
			Collection colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
		
			sessao.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
			
		}

		// C�digo do Munic�pio
		String codigoDigitadoMunicipioEnter = (String) filtrarClienteActionForm
			.getMunicipioClienteFiltro();

		// Verifica se o c�digo foi digitado
		if (codigoDigitadoMunicipioEnter != null
				&& !codigoDigitadoMunicipioEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoMunicipioEnter) > 0) {

			// Manda para a p�gina a informa��o do campo para que seja
			// focado no retorno da pesquisa
			httpServletRequest.setAttribute("nomeCampo", "municipioClienteFiltro");

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, codigoDigitadoMunicipioEnter));

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				// O cliente foi encontrado
				filtrarClienteActionForm.setMunicipioClienteFiltro( 
						""+ ((Municipio) ((List) municipioEncontrado).get(0)).getId());
				filtrarClienteActionForm.setDescricaoMunicipioClienteFiltro(
						((Municipio) ((List) municipioEncontrado).get(0)).getNome());
				httpServletRequest.setAttribute("codigoMunicipioNaoEncontrado",
						"true");
				httpServletRequest.setAttribute("nomeCampo", "bairroClienteFiltro");

			} else {
				filtrarClienteActionForm.setMunicipioClienteFiltro("");
				httpServletRequest.setAttribute("codigoMunicipioNaoEncontrado",
						"exception");
				filtrarClienteActionForm.setDescricaoMunicipioClienteFiltro("Munic�pio Inexistente");

			}
		}

		// C�digo do Munic�pio
		String codigoDigitadoLogradouroEnter = (String) filtrarClienteActionForm
				.getLogradouroClienteFiltro();

		// C�digo do Bairro
		String codigoDigitadoBairroEnter = (String) filtrarClienteActionForm
				.getBairroClienteFiltro();

		// Verifica se o c�digo foi digitado
		if (codigoDigitadoBairroEnter != null
				&& !codigoDigitadoBairroEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoBairroEnter) > 0) {

			// Manda para a p�gina a informa��o do campo para que seja
			// focado no retorno da pesquisa
			httpServletRequest.setAttribute("nomeCampo", "bairroClienteFiltro");

			FiltroBairro filtroBairro = new FiltroBairro();

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, codigoDigitadoBairroEnter));

			// Adiciona a busca por munic�pio se ele foi digitado na p�gina
			if (codigoDigitadoMunicipioEnter != null
					&& !codigoDigitadoMunicipioEnter.equalsIgnoreCase("")) {
				filtroBairro
						.adicionarParametro(new ParametroSimples(
								FiltroBairro.MUNICIPIO_ID,
								codigoDigitadoMunicipioEnter));
			}

			Collection bairroEncontrado = fachada.pesquisar(filtroBairro,
					Bairro.class.getName());

			if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) {
				// O cliente foi encontrado
				filtrarClienteActionForm.setBairroClienteFiltro(
						""+ ((Bairro) ((List) bairroEncontrado).get(0)).getCodigo());
				filtrarClienteActionForm.setDescricaoBairroClienteFiltro(
						((Bairro) ((List) bairroEncontrado).get(0)).getNome());
				httpServletRequest.setAttribute("codigoBairroNaoEncontrado",
						"true");
				httpServletRequest.setAttribute("nomeCampo", "logradouroClienteFiltro");

			} else {
				filtrarClienteActionForm.setBairroClienteFiltro("");
				httpServletRequest.setAttribute("codigoBairroNaoEncontrado",
						"exception");
				filtrarClienteActionForm.setDescricaoBairroClienteFiltro(
						"Bairro Inexistente");

			}
		}

		// Verifica se o c�digo foi digitado
		if (codigoDigitadoLogradouroEnter != null
				&& !codigoDigitadoLogradouroEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoLogradouroEnter) > 0) {

			// Manda para a p�gina a informa��o do campo para que seja
			// focado no retorno da pesquisa
			httpServletRequest.setAttribute("nomeCampo", "logradouroClienteFiltro");

			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

			filtroLogradouro
					.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
			filtroLogradouro
					.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");

			filtroLogradouro.adicionarParametro(new ParametroSimples(
					FiltroLogradouro.ID, codigoDigitadoLogradouroEnter));

			Collection logradouroEncontrado = fachada.pesquisar(
					filtroLogradouro, Logradouro.class.getName());

			if (logradouroEncontrado != null && !logradouroEncontrado.isEmpty()) {
				// O logradouro foi encontrado
				filtrarClienteActionForm.setLogradouroClienteFiltro(
						""+ ((Logradouro) ((List) logradouroEncontrado).get(0)).getId());
				filtrarClienteActionForm.setDescricaoLogradouroClienteFiltro(
						((Logradouro) ((List) logradouroEncontrado).get(0)).getDescricaoFormatada());
				httpServletRequest.setAttribute("idLogradouroNaoEncontrado",
						"true");

			} else {
				filtrarClienteActionForm.setLogradouroClienteFiltro("");
				httpServletRequest.setAttribute("idLogradouroNaoEncontrado",
						"exception");
				filtrarClienteActionForm.setDescricaoLogradouroClienteFiltro(
						"Logradouro Inexistente");

			}
		}

		// C�digo do Munic�pio
		String idCep = (String) filtrarClienteActionForm.getCepClienteFiltro();
		
		// PESQUISAR CEP
		if (idCep != null
				&& !idCep.toString().trim().equalsIgnoreCase("")) {
			this.pesquisarCep(idCep, filtrarClienteActionForm, fachada, httpServletRequest);
		} 

		
		if (filtrarClienteActionForm.getTipoPesquisa() == null
				|| filtrarClienteActionForm.getTipoPesquisa().equals("")) {

			filtrarClienteActionForm.setTipoPesquisa(
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		}
		
		if (filtrarClienteActionForm.getTipoPesquisaNomeMae() == null
				|| filtrarClienteActionForm.getTipoPesquisaNomeMae().equals("")) {

			filtrarClienteActionForm.setTipoPesquisaNomeMae(
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		}
		
		
		return retorno;
	}
	
	/**
	 * Pesquisar Cep
	 * @param filtroMunicipio
	 * @param idMunicipioFiltro
	 * @param codigoSetorComercial
	 * @param municipios
	 * @param filtrarImovelFiltrarActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarCep(
			String idCepFiltro, 
			FiltrarClienteActionForm filtrarClienteActionForm,
			Fachada fachada, 
			HttpServletRequest httpServletRequest) {
		FiltroCep filtroCep = new FiltroCep();

		filtroCep.adicionarParametro(new ParametroSimples(
				FiltroCep.CODIGO, idCepFiltro));
		filtroCep.adicionarParametro(new ParametroSimples(
				FiltroCep.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection cepEncontrado = null;

       //pesquisa
       	cepEncontrado = fachada.pesquisar(filtroCep, Cep.class
                    .getName());
		
		if (cepEncontrado != null && !cepEncontrado.isEmpty()) {
			// O municipio foi encontrado
			filtrarClienteActionForm.setCepClienteFiltro(""
					+ ((Cep) ((List) cepEncontrado).get(0))
							.getCodigo());
			filtrarClienteActionForm.setCepDescricaoClienteFiltro(
					((Cep) ((List) cepEncontrado).get(0))
							.getDescricaoLogradouroFormatada());
			
			httpServletRequest.setAttribute(
					"cepImovelNaoEncontrado", "true");

			httpServletRequest.setAttribute("nomeCampo",
					"idMunicipioFiltro");

		} else {
			filtrarClienteActionForm.setCepClienteFiltro("");
			httpServletRequest.setAttribute(
					"cepImovelNaoEncontrado", "exception");
			
			filtrarClienteActionForm.setCepDescricaoClienteFiltro(
					"Cep inexistente");

			httpServletRequest.setAttribute("nomeCampo",
					"cepFiltro");

		}
	}
}