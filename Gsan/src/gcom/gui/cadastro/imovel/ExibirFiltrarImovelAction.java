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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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
public class ExibirFiltrarImovelAction extends GcomAction {
	
	private HttpSession sessao;
	
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

		ActionForward retorno = actionMapping.findForward("filtrarImovel");

		sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("idClienteImovelResponsavel");

		FiltrarImovelActionForm filtrarImovelFiltrarActionForm = (FiltrarImovelActionForm) actionForm;

		String redirecionar = (String) httpServletRequest.getParameter("redirecionar");
		
		if (redirecionar == null ) {
			redirecionar = (String) getSessao(httpServletRequest).getAttribute("redirecionar");
		}

		
		if(filtrarImovelFiltrarActionForm.getAtualizarFiltro()== null){
			filtrarImovelFiltrarActionForm.setAtualizarFiltro("1");
			httpServletRequest.setAttribute("nomeCampo",
			"matriculaFiltro");
		}
		
		if(filtrarImovelFiltrarActionForm.getAtualizarFiltro().equals("")){
			filtrarImovelFiltrarActionForm.setAtualizarFiltro("1");
		}

		if (redirecionar != null && !redirecionar.trim().equalsIgnoreCase("") && "ManterVinculoImoveisRateioConsumo".equals(redirecionar)) {
			sessao.setAttribute("redirecionar", "ManterVinculoImoveisRateioConsumo");
		} else if (redirecionar != null && !redirecionar.trim().equalsIgnoreCase("") && "ManterDadosTarifaSocial".equals(redirecionar)) {
			sessao.setAttribute("redirecionar", "ManterDadosTarifaSocial");
		} else {
		//	sessao.removeAttribute("redirecionar");
		}
 
		if (httpServletRequest.getParameter("limpar") != null) {
			if (!httpServletRequest.getParameter("limpar").trim()
					.equalsIgnoreCase("S")) {
				// ---Limpar sessao
				sessao.removeAttribute("InserirImovelActionForm");
				sessao.removeAttribute("colecaoEnderecos");
				sessao.removeAttribute("imovelClientesNovos");
				sessao.removeAttribute("imoveisPrincipal");
			}
		}

		// cria as variaveis
		String idLocalidade = null;
		String codigoSetorComercial = null;
		String numeroQuadra = null;
		String idCliente = null;
		String idBairroFiltro = null;
		String idMunicipioFiltro = null;
		String idCep = null;

		// atribui os valores q vem pelo request as variaveis
		idLocalidade = (String) filtrarImovelFiltrarActionForm
				.getIdLocalidadeFiltro();
		codigoSetorComercial = (String) filtrarImovelFiltrarActionForm
				.getIdSetorComercialFiltro();
		numeroQuadra = (String) filtrarImovelFiltrarActionForm.getIdQuadraFiltro();
		idCliente = (String) filtrarImovelFiltrarActionForm.getIdClienteFiltro();
		idBairroFiltro = (String) filtrarImovelFiltrarActionForm.getIdBairroFiltro();
		idMunicipioFiltro = (String) filtrarImovelFiltrarActionForm
				.getIdMunicipioFiltro();
		String idLogradouroFiltro = (String) filtrarImovelFiltrarActionForm
				.getIdLogradouroFiltro();
		idCep = filtrarImovelFiltrarActionForm.getCepFiltro();
		
		// cria a colecao para receber a pesquisa
		
		Collection bairros = new HashSet();
		
		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// pesquisar localidade
		if (idLocalidade != null
				&& !idLocalidade.toString().trim().equalsIgnoreCase("")) {
			this.pesquisarLocalidade(idLocalidade,filtrarImovelFiltrarActionForm,fachada,httpServletRequest);
		}

		// pesquisar setor comercial
		if ((codigoSetorComercial != null && !codigoSetorComercial.toString()
				.trim().equalsIgnoreCase(""))
				&& (idLocalidade != null && !idLocalidade.toString().trim()
						.equalsIgnoreCase(""))) {
			//pesquisar o municpio do setor comercial
			this.pesquisarSetorComercial( idLocalidade,codigoSetorComercial,filtrarImovelFiltrarActionForm,fachada,httpServletRequest);
		}

		// pesquisar quadra
		if ((numeroQuadra != null && !numeroQuadra.toString().trim()
				.equalsIgnoreCase(""))
				&& (codigoSetorComercial != null && !codigoSetorComercial
						.toString().trim().equalsIgnoreCase(""))
						&& (idLocalidade != null && !idLocalidade
								.toString().trim().equalsIgnoreCase(""))) {

			this.pesquisarQuadra(numeroQuadra,codigoSetorComercial,idLocalidade,filtrarImovelFiltrarActionForm,fachada,httpServletRequest);
		}

		// PESQUISAR CLIENTE
		if (idCliente != null
				&& !idCliente.toString().trim().equalsIgnoreCase("")) {
			this.pesquisarCliente(idCliente,filtrarImovelFiltrarActionForm,fachada,httpServletRequest);
		}

		// PESQUISAR CEP
		if (idCep != null
				&& !idCep.toString().trim().equalsIgnoreCase("")) {
			this.pesquisarCep(idCep,filtrarImovelFiltrarActionForm,fachada,httpServletRequest);
		} 
		
		
		// PESQUISAR MUNICIPIO
		if (idMunicipioFiltro != null
				&& !idMunicipioFiltro.toString().trim().equalsIgnoreCase("")) {
			this.pesquisarMunicipio(idMunicipioFiltro,codigoSetorComercial,filtrarImovelFiltrarActionForm,fachada,httpServletRequest);
		} 

		// PESQUISAR BAIRRO
		if (idBairroFiltro != null
				&& !idBairroFiltro.toString().trim().equalsIgnoreCase("")) {
			this.pesquisarBairro(idMunicipioFiltro,idBairroFiltro,bairros,filtrarImovelFiltrarActionForm,fachada,httpServletRequest);
		}

		// Verifica se o c�digo do logradouro foi digitado
		if (idLogradouroFiltro != null
				&& !idLogradouroFiltro.trim().equals("")
				&& Integer.parseInt(idLogradouroFiltro) > 0) {
			this.pesquisarLogradouro(idLogradouroFiltro,filtrarImovelFiltrarActionForm,fachada,httpServletRequest);
		}

		
		sessao.setAttribute("FiltrarImovelActionForm",filtrarImovelFiltrarActionForm);

		return retorno;
	}

	/**
	 * Pesquisar Localidade
	 * @param filtroLocalidade
	 * @param idLocalidade
	 * @param localidades
	 * @param filtrarImovelFiltrarActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarLocalidade(
			String idLocalidade,
			FiltrarImovelActionForm filtrarImovelFiltrarActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		// coloca parametro no filtro
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, new Integer(idLocalidade)));

		// coloca parametro no filtro
		//filtroLocalidade.adicionarParametro(new ParametroSimples(
			//	FiltroLocalidade.INDICADORUSO,
				//ConstantesSistema.INDICADOR_USO_ATIVO));
		// pesquisa
		Collection localidades = fachada.pesquisar(filtroLocalidade,
				Localidade.class.getName());
		if (localidades != null && !localidades.isEmpty()) {
			// A localidade foi encontrada
			filtrarImovelFiltrarActionForm.setIdLocalidadeFiltro(
					((Localidade) ((List) localidades).get(0)).getId()
							.toString());
			filtrarImovelFiltrarActionForm.setLocalidadeDescricaoFiltro(
					((Localidade) ((List) localidades).get(0)).getDescricao());
			httpServletRequest.setAttribute("idLocalidadeFiltroNaoEncontrada",
					"true");

			httpServletRequest.setAttribute("nomeCampo",
					"idSetorComercialFiltro");
		} else {
			httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
					"exception");
			filtrarImovelFiltrarActionForm.setIdLocalidadeFiltro("");
			filtrarImovelFiltrarActionForm.setLocalidadeDescricaoFiltro(
					"Localidade inexistente");

			httpServletRequest.setAttribute("nomeCampo", "idLocalidadeFiltro");

		}
	}

	/**
	 * Pesquisar Setor Comercial
	 * @param filtroSetorComercial
	 * @param idLocalidadeFiltroFiltro
	 * @param codigoSetorComercial
	 * @param setorComerciais
	 * @param filtrarImovelFiltrarActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarSetorComercial( 
			String idLocalidadeFiltroFiltro,
			String codigoSetorComercial,  
			FiltrarImovelActionForm filtrarImovelFiltrarActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (idLocalidadeFiltroFiltro != null
				&& !idLocalidadeFiltroFiltro.toString().trim().equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, new Integer(
							idLocalidadeFiltroFiltro)));
		}
		
		/*filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.DESCRICAO_MUNICIPIO);
		filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.ID_MUNICIPIO);*/
		
		filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("municipio");
		
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
						codigoSetorComercial)));

		//filtroSetorComercial.adicionarParametro(new ParametroSimples(
			//	FiltroSetorComercial.INDICADORUSO,
				//ConstantesSistema.INDICADOR_USO_ATIVO));

		// pesquisa
		Collection setorComerciais = fachada.pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());
		if (setorComerciais != null && !setorComerciais.isEmpty()) {
			// O cliente foi encontrado
			filtrarImovelFiltrarActionForm.setIdSetorComercialFiltro(""
					+ ((SetorComercial) ((List) setorComerciais).get(0))
							.getCodigo());
			filtrarImovelFiltrarActionForm.setSetorComercialDescricaoFiltro(
					((SetorComercial) ((List) setorComerciais).get(0))
							.getDescricao());
			
			filtrarImovelFiltrarActionForm.setIdMunicipioFiltro(""
					+ ((SetorComercial) ((List) setorComerciais).get(0)).getMunicipio().getId().toString());
			
			filtrarImovelFiltrarActionForm.setMunicipioFiltro(
					((SetorComercial) ((List) setorComerciais).get(0)).getMunicipio().getNome());
			
			httpServletRequest.setAttribute(
					"idSetorComercialNaoEncontrada", "true");
			httpServletRequest.setAttribute(
					"idMunicipioFiltroImovelNaoEncontrado", "true");

			httpServletRequest.setAttribute("nomeCampo", "idQuadraFiltro");
		} else {
			filtrarImovelFiltrarActionForm.setIdSetorComercialFiltro( "");
			httpServletRequest.setAttribute(
					"idSetorComercialNaoEncontrada", "exception");
			filtrarImovelFiltrarActionForm.setSetorComercialDescricaoFiltro(
					"Setor comercial inexistente");
			httpServletRequest.setAttribute("nomeCampo",
					"idSetorComercial");

		}	}

	/**
	 * Pesquisar Quadra
	 * 
	 * @param filtroQuadra
	 * @param numeroQuadra
	 * @param codigoSetorComercial
	 * @param quadras
	 * @param filtrarImovelFiltrarActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarQuadra(String numeroQuadra,
			String codigoSetorComercial, 
			String idLocalidadeFiltroFiltro,
			FiltrarImovelActionForm filtrarImovelFiltrarActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {
			FiltroQuadra filtroQuadra = new FiltroQuadra();		
		if (idLocalidadeFiltroFiltro != null
				&& !idLocalidadeFiltroFiltro.toString().trim()
						.equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_LOCALIDADE, new Integer(
							idLocalidadeFiltroFiltro)));
		}

		if (codigoSetorComercial != null
				&& !codigoSetorComercial.toString().trim()
						.equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_LOCALIDADE, new Integer(
							idLocalidadeFiltroFiltro)));
		}

		if (codigoSetorComercial != null
				&& !codigoSetorComercial.toString().trim()
						.equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(
							codigoSetorComercial)));
		}
		filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.NUMERO_QUADRA, new Integer(numeroQuadra)));

		//filtroQuadra.adicionarParametro(new ParametroSimples(
			//	FiltroQuadra.INDICADORUSO,
				//ConstantesSistema.INDICADOR_USO_ATIVO));
		//filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");
		// pesquisa
		Collection quadras = fachada.pesquisar(filtroQuadra, Quadra.class
				.getName());
		if (quadras != null && !quadras.isEmpty()) {
			// O cliente foi encontrado
			filtrarImovelFiltrarActionForm.setIdQuadraFiltro(""
					+ ((Quadra) ((List) quadras).get(0)).getNumeroQuadra());
			httpServletRequest
					.setAttribute("idQuadraNaoEncontrada", "true");
			httpServletRequest.setAttribute("nomeCampo", "loteFiltro");

		} else {
            httpServletRequest.setAttribute(
                    "codigoQuadraNaoEncontrada", "true");

			httpServletRequest.setAttribute("idQuadraNaoEncontrada",
					"exception");
			filtrarImovelFiltrarActionForm.setIdQuadraFiltro("");
			httpServletRequest.setAttribute
					("msgQuadra", "QUADRA INEXISTENTE");
			httpServletRequest.setAttribute("nomeCampo", "idQuadraFiltro");
		}
	}

	/**
	 * Pesquisar Clientes
	 * 
	 * @param filtroCliente
	 * @param idCliente
	 * @param clientes
	 * @param filtrarImovelFiltrarActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarCliente( String idCliente,
			FiltrarImovelActionForm filtrarImovelFiltrarActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.ID, idCliente));
		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
				Cliente.class.getName());

		if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
			// O municipio foi encontrado
			filtrarImovelFiltrarActionForm
					.setIdClienteFiltro(""
							+ ((Cliente) ((List) clienteEncontrado).get(0))
									.getId());
			filtrarImovelFiltrarActionForm
					.setNomeClienteFiltro(
							((Cliente) ((List) clienteEncontrado).get(0))
									.getNome());
			httpServletRequest.setAttribute("idClienteNaoEncontrado",
					"true");
			httpServletRequest.setAttribute("nomeCampo", "cepFiltro");

		} else {
			filtrarImovelFiltrarActionForm.setIdClienteFiltro("");
			httpServletRequest.setAttribute("idClienteNaoEncontrado",
					"exception");
			filtrarImovelFiltrarActionForm.setNomeClienteFiltro("Cliente inexistente");

			httpServletRequest.setAttribute("nomeCampo", "idClienteFiltro");

		}
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
			FiltrarImovelActionForm filtrarImovelFiltrarActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		FiltroCep filtroCep = new FiltroCep();

		filtroCep.adicionarParametro(new ParametroSimples(
				FiltroCep.CODIGO, idCepFiltro));
		//filtroCep.adicionarParametro(new ParametroSimples(
			//	FiltroCep.INDICADORUSO,
				//ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection cepEncontrado = null;

       //pesquisa
       	cepEncontrado = fachada.pesquisar(filtroCep, Cep.class
                    .getName());
		
		if (cepEncontrado != null && !cepEncontrado.isEmpty()) {
			// O municipio foi encontrado
			filtrarImovelFiltrarActionForm.setCepFiltro(""
					+ ((Cep) ((List) cepEncontrado).get(0))
							.getCodigo());
			filtrarImovelFiltrarActionForm.setCepDescricaoFiltro(
					((Cep) ((List) cepEncontrado).get(0))
							.getDescricaoLogradouroFormatada());
			
			httpServletRequest.setAttribute(
					"cepImovelNaoEncontrado", "true");

			httpServletRequest.setAttribute("nomeCampo",
					"Button");

		} else {
			filtrarImovelFiltrarActionForm.setCepFiltro("");
			httpServletRequest.setAttribute(
					"cepImovelNaoEncontrado", "exception");
			
			filtrarImovelFiltrarActionForm.setCepDescricaoFiltro(
					"Cep inexistente");

			httpServletRequest.setAttribute("nomeCampo",
					"cepFiltro");

		}
	}
	
	
	/**
	 * Pesquisar Municipio
	 * @param filtroMunicipio
	 * @param idMunicipioFiltro
	 * @param codigoSetorComercial
	 * @param municipios
	 * @param filtrarImovelFiltrarActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarMunicipio(
			String idMunicipioFiltro, String codigoSetorComercial,
			FiltrarImovelActionForm filtrarImovelFiltrarActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.ID, idMunicipioFiltro));
		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection municipioEncontrado = null;

       //pesquisa
       	municipioEncontrado = fachada.pesquisar(filtroMunicipio, Municipio.class
                    .getName());
		
		if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
			// O municipio foi encontrado
			filtrarImovelFiltrarActionForm.setIdMunicipioFiltro(""
					+ ((Municipio) ((List) municipioEncontrado).get(0))
							.getId());
			filtrarImovelFiltrarActionForm.setMunicipioFiltro(
					((Municipio) ((List) municipioEncontrado).get(0))
							.getNome());
			httpServletRequest.setAttribute(
					"idMunicipioFiltroImovelNaoEncontrado", "true");

			httpServletRequest.setAttribute("nomeCampo",
					"idBairroFiltro");

		} else {
			filtrarImovelFiltrarActionForm.setIdMunicipioFiltro("");
			httpServletRequest.setAttribute(
					"idMunicipioFiltroImovelNaoEncontrado", "exception");
			filtrarImovelFiltrarActionForm.setMunicipioFiltro(
					"Munic�pio inexistente");

			httpServletRequest.setAttribute("nomeCampo",
					"idMunicipioFiltro");

		}
	}

	
	/**
	 * Pesquisar Bairro
	 * @param filtroBairro
	 * @param idMunicipioFiltro
	 * @param idBairroFiltro
	 * @param bairros
	 * @param filtrarImovelFiltrarActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarBairro(
			String idMunicipioFiltro, String idBairroFiltro,
			Collection bairros,
			FiltrarImovelActionForm filtrarImovelFiltrarActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest){
		
		FiltroBairro filtroBairro = new FiltroBairro();

		if (idMunicipioFiltro != null
				&& !idMunicipioFiltro.trim().equals("")
				&& Integer.parseInt(idMunicipioFiltro) > 0) {

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.MUNICIPIO_ID, idMunicipioFiltro));
		}

		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.CODIGO, idBairroFiltro));
		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection bairroEncontrado = fachada.pesquisar(filtroBairro,
				Bairro.class.getName());

		if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) {
			// O municipio foi encontrado
			filtrarImovelFiltrarActionForm.setIdBairroFiltro( ""
					+ ((Bairro) ((List) bairroEncontrado).get(0))
							.getCodigo());
			filtrarImovelFiltrarActionForm.setBairroFiltro(
					((Bairro) ((List) bairroEncontrado).get(0)).getNome());
			httpServletRequest.setAttribute(
					"codigoBairroImovelNaoEncontrado", "true");

			httpServletRequest.setAttribute("nomeCampo", "idLogradouroFiltro");

		} else {
			filtrarImovelFiltrarActionForm.setIdBairroFiltro( "");
			httpServletRequest.setAttribute(
					"codigoBairroImovelNaoEncontrado", "exception");
			filtrarImovelFiltrarActionForm.setBairroFiltro(
					"Bairro inexistente");

			httpServletRequest.setAttribute("nomeCampo",
					"idBairroFiltro");

		}

		
		
	}
	
	/**
	 * Pesquisar pelo logradouro
	 * @param codigoLogradouro
	 * @param idBairroFiltro
	 * @param bairros
	 * @param filtrarImovelFiltrarActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarLogradouro(String codigoLogradouro,FiltrarImovelActionForm filtrarImovelFiltrarActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest){
		
			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

			filtroLogradouro
					.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
			filtroLogradouro
					.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");

			filtroLogradouro.adicionarParametro(new ParametroSimples(
					FiltroLogradouro.ID, codigoLogradouro));
			//filtroLogradouro.adicionarParametro(new ParametroSimples(
				//	FiltroLogradouro.INDICADORUSO,
					//ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection logradouroEncontrado = fachada.pesquisar(
					filtroLogradouro, Logradouro.class.getName());

			if (logradouroEncontrado != null && !logradouroEncontrado.isEmpty()) {
				// O municipio foi encontrado

				Logradouro logradouro = (Logradouro) ((List) logradouroEncontrado)
						.get(0);
				String logradouroFormatado = "";
				if(logradouro.getLogradouroTipo() != null){
					logradouroFormatado = logradouro.getLogradouroTipo().getDescricaoAbreviada();	
				}
				if(logradouro.getLogradouroTitulo() != null){
					logradouroFormatado = logradouroFormatado + " " + logradouro.getLogradouroTitulo().getDescricaoAbreviada(); 
				}
					
				logradouroFormatado = logradouroFormatado + " " + logradouro.getNome();
						
				filtrarImovelFiltrarActionForm.setIdLogradouroFiltro(""+
						((Logradouro) ((List) logradouroEncontrado).get(0))
								.getId());
				filtrarImovelFiltrarActionForm.setLogradouroFiltro(logradouroFormatado);

				httpServletRequest.setAttribute("idLogradouroFiltroNaoEncontrado",
						"true");

				httpServletRequest.setAttribute("nomeCampo", "numeroImovelInicialFiltro");

			} else {
				filtrarImovelFiltrarActionForm.setIdLogradouroFiltro("");
				httpServletRequest.setAttribute("idLogradouroNaoEncontrado",
						"exception");
				filtrarImovelFiltrarActionForm.setLogradouroFiltro(
						"Logradouro inexistente");

				httpServletRequest.setAttribute("nomeCampo", "idLogradouroFiltro");
			}
	}
}
