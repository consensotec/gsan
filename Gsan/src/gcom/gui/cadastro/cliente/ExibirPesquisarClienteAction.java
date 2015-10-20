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

import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
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
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que define o pr�-processamento da p�gina de pesquisa de cliente
 * 
 * @author S�vio Luiz, Fernanda Paiva
 * @date 25/04/2005, 29/08/2006
 */
public class ExibirPesquisarClienteAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("pesquisarCliente");
		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Verifica se o pesquisar cliente foi chamado a partir do inserir cliente
 		// e em caso afirmativo recebe o par�metro e manda-o pela sess�o para
 		// ser verificado no cliente_resultado_pesquisa e depois retirado da
 		// sess�o no ExibirFiltrarClienteAction
 		if (httpServletRequest.getParameter("consultaCliente") != null) {
 			sessao.setAttribute("consultaCliente", httpServletRequest.getParameter("consultaCliente"));
 		}
 		if (httpServletRequest.getParameter("pesquisarClienteAssociado") != null) {
 			sessao.setAttribute("pesquisarClienteAssociado", httpServletRequest.getParameter("pesquisarClienteAssociado"));
 		}
 		

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		String limparForm = httpServletRequest.getParameter("limparForm");
		if(limparForm == null || limparForm.equals("")){
			if(httpServletRequest.getAttribute("limparForm") != null){
				limparForm = (String)httpServletRequest.getAttribute("limparForm");
			}
		}
		
		// Verifica se esta entrando a primeira vez no pesquisar e limpa o form e a sessao caso isso aconte�a.
		if (httpServletRequest.getParameter("objetoConsulta") == null
				&& httpServletRequest.getParameter("tipoConsulta") == null
				&& httpServletRequest.getParameter("voltarPesquisa") == null) 
		{

			pesquisarActionForm.set("idTipoCliente", null);
			pesquisarActionForm.set("nomeCliente", "");
			pesquisarActionForm.set("cepClienteEndereco", "");
			pesquisarActionForm.set("cpf", "");
			pesquisarActionForm.set("rg", "");
			pesquisarActionForm.set("cnpj", "");
			pesquisarActionForm.set("idMunicipioCliente", "");
			pesquisarActionForm.set("descricaoMunicipioClienTabelaAuxiliarIndicadorActionFormte", "");
			pesquisarActionForm.set("codigoBairroCliente", "");
			pesquisarActionForm.set("descricaoBairroCliente", "");
			pesquisarActionForm.set("idLogradouroCliente", "");
			pesquisarActionForm.set("tipoPesquisa",
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

			sessao.removeAttribute("caminhoRetornoTelaPesquisaMunicipio");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaBairro");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaLogradouro");
			sessao.removeAttribute("idMunicipio");
			if (httpServletRequest.getParameter("novaPesquisa") == null
					|| httpServletRequest.getParameter("novaPesquisa").equals(
							"")) {
				sessao.removeAttribute("caminhoRetorno");
			}
		}

		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {
			pesquisarActionForm.set("idTipoCliente", null);
			pesquisarActionForm.set("nomeCliente", "");
			pesquisarActionForm.set("cepClienteEndereco", "");
			pesquisarActionForm.set("cpf", "");
			pesquisarActionForm.set("rg", "");
			pesquisarActionForm.set("cnpj", "");
			pesquisarActionForm.set("idMunicipioCliente", "");
			pesquisarActionForm.set("descricaoMunicipioClienTabelaAuxiliarIndicadorActionFormte", "");
			pesquisarActionForm.set("codigoBairroCliente", "");
			pesquisarActionForm.set("descricaoBairroCliente", "");
			pesquisarActionForm.set("idLogradouroCliente", "");
			pesquisarActionForm.set("tipoPesquisa",
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

			sessao.removeAttribute("caminhoRetornoTelaPesquisaMunicipio");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaBairro");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaLogradouro");
			sessao.removeAttribute("idMunicipio");
		}

		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo(
				FiltroClienteTipo.DESCRICAO);
		if( httpServletRequest.getParameter("indicadorUsoTodos") == null ){
        	sessao.removeAttribute("indicadorUsoTodos");
        	filtroClienteTipo.adicionarParametro(new ParametroSimples(
    				FiltroClienteTipo.INDICADOR_USO,
    				ConstantesSistema.INDICADOR_USO_ATIVO));
        }
        else
        {
        	sessao.setAttribute("indicadorUsoTodos",
				httpServletRequest.getParameter("indicadorUsoTodos"));
        }
		
		FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder(
				FiltroEsferaPoder.DESCRICAO);
		
		Collection colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
		httpServletRequest.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
		
		// inicializa a cole��o
		Collection tiposClientes = null;

		// -------Parte que trata do c�digo quando o usu�rio tecla enter
		// caso seja o id do municipio
		String idDigitadoEnterMunicipio = (String) pesquisarActionForm
				.get("idMunicipioCliente");
		// caso seja o codigo do bairro
		String codigoDigitadoEnterBairro = (String) pesquisarActionForm
				.get("codigoBairroCliente");

		// caso seja o codigo do logradouro
		String codigoDigitadoEnterLogradouro = (String) pesquisarActionForm
				.get("idLogradouroCliente");

		// Verifica se o c�digo foi digitado
		if (idDigitadoEnterMunicipio != null
				&& !idDigitadoEnterMunicipio.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterMunicipio) > 0) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idDigitadoEnterMunicipio));
			
			if( httpServletRequest.getParameter("indicadorUsoTodos") == null ){
	        	sessao.removeAttribute("indicadorUsoTodos");
	        	filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
	        }
	        else
	        {
	        	sessao.setAttribute("indicadorUsoTodos",
					httpServletRequest.getParameter("indicadorUsoTodos"));
	        }

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				// O municipio foi encontrado
				pesquisarActionForm.set("idMunicipioCliente", ""
						+ ((Municipio) ((List) municipioEncontrado).get(0))
								.getId());
				pesquisarActionForm.set("descricaoMunicipioClienTabelaAuxiliarIndicadorActionFormte",
						((Municipio) ((List) municipioEncontrado).get(0))
								.getNome());
				httpServletRequest.setAttribute(
						"idMunicipioClienteNaoEncontrado", "true");

				httpServletRequest.setAttribute("nomeCampo",
						"codigoBairroCliente");

			} else {

				pesquisarActionForm.set("idMunicipioCliente", "");
				httpServletRequest.setAttribute("nomeCampo",
						"idMunicipioCliente");
				httpServletRequest.setAttribute(
						"idMunicipioClienteNaoEncontrado", "exception");
				pesquisarActionForm.set("descricaoMunicipioClienTabelaAuxiliarIndicadorActionFormte",
						"Munic�pio inexistente");

			}

		}

		// Verifica se o c�digo foi digitado
		if (codigoDigitadoEnterBairro != null
				&& !codigoDigitadoEnterBairro.trim().equals("")
				&& Integer.parseInt(codigoDigitadoEnterBairro) > 0) {
			FiltroBairro filtroBairro = new FiltroBairro();

			if (idDigitadoEnterMunicipio != null
					&& !idDigitadoEnterMunicipio.trim().equals("")
					&& Integer.parseInt(idDigitadoEnterMunicipio) > 0) {

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.MUNICIPIO_ID, idDigitadoEnterMunicipio));
			}

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, codigoDigitadoEnterBairro));
			if( httpServletRequest.getParameter("indicadorUsoTodos") == null ){
	        	sessao.removeAttribute("indicadorUsoTodos");
	        	filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
	        }
	        else
	        {
	        	sessao.setAttribute("indicadorUsoTodos",
					httpServletRequest.getParameter("indicadorUsoTodos"));
	        }

			// verifica se o bairro pesquisado � de um municipio existente
			if (idDigitadoEnterMunicipio != null
					&& !idDigitadoEnterMunicipio.trim().equals("")
					&& Integer.parseInt(idDigitadoEnterMunicipio) > 0) {

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.MUNICIPIO_ID, idDigitadoEnterMunicipio));
			}

			Collection bairroEncontrado = fachada.pesquisar(filtroBairro,
					Bairro.class.getName());

			if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) {
				// O municipio foi encontrado
				pesquisarActionForm.set("codigoBairroCliente", ""
						+ ((Bairro) ((List) bairroEncontrado).get(0))
								.getCodigo());
				pesquisarActionForm.set("descricaoBairroCliente",
						((Bairro) ((List) bairroEncontrado).get(0)).getNome());
				httpServletRequest.setAttribute(
						"codigoBairroClienteNaoEncontrado", "true");

				httpServletRequest.setAttribute("nomeCampo",
						"logradouroCliente");

			} else {

				pesquisarActionForm.set("codigoBairroCliente", "");
				httpServletRequest.setAttribute(
						"codigoBairroClienteNaoEncontrado", "exception");
				pesquisarActionForm.set("descricaoBairroCliente",
						"Bairro inexistente");

				httpServletRequest.setAttribute("nomeCampo",
						"codigoBairroCliente");

			}

		}

		// Verifica se o c�digo do logradouro foi digitado
		if (codigoDigitadoEnterLogradouro != null
				&& !codigoDigitadoEnterLogradouro.trim().equals("")
				&& Integer.parseInt(codigoDigitadoEnterLogradouro) > 0) {
			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

			filtroLogradouro
					.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
			filtroLogradouro
					.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");

			filtroLogradouro.adicionarParametro(new ParametroSimples(
					FiltroLogradouro.ID, codigoDigitadoEnterLogradouro));
			if( httpServletRequest.getParameter("indicadorUsoTodos") == null ){
	        	sessao.removeAttribute("indicadorUsoTodos");
	        	filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
	        }
	        

			Collection logradouroEncontrado = fachada.pesquisar(
					filtroLogradouro, Logradouro.class.getName());

			if (logradouroEncontrado != null && !logradouroEncontrado.isEmpty()) {
				// O municipio foi encontrado
				Logradouro logradouro = (Logradouro) ((List) logradouroEncontrado)
						.get(0);
				String logradouroFormatado = logradouro.getDescricaoFormatada();
				pesquisarActionForm.set("idLogradouroCliente", ""
						+ logradouro.getId());
				pesquisarActionForm.set("nomeLogradouroCliente",
						logradouroFormatado);
				httpServletRequest.setAttribute("idLogradouroNaoEncontrado",
						"true");

				httpServletRequest.setAttribute("nomeCampo", "Button");

			} else {
				pesquisarActionForm.set("idLogradouroCliente", "");
				httpServletRequest.setAttribute("idLogradouroNaoEncontrado",
						"exception");
				pesquisarActionForm.set("nomeLogradouroCliente",
						"Logradouro inexistente");

				httpServletRequest.setAttribute("nomeCampo", "idLogradouro");

			}

		}

		// -------Fim da Parte que trata do c�digo quando o usu�rio tecla enter
		tiposClientes = fachada.pesquisar(filtroClienteTipo, ClienteTipo.class
				.getName());

		if (tiposClientes == null || tiposClientes.isEmpty()) {
			// Nenhuma empresa cadastrada
			new ActionServletException("atencao.pesquisa.nenhumresultado",
					null, "tipo cliente");
		} else {
			sessao.setAttribute("tiposClientes", tiposClientes);
		}

		// Verifica se o tipoConsulta � diferente de nulo ou vazio esse tipo
		// consulta vem do
		// municipio_resultado_pesquisa.jsp ou do bairro_resultado_pesquisa.jsp.
		// � feita essa verifica��o pois pode ser que ainda n�o tenha
		// feito a pesquisa de municipio ou bairro.
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			// Verifica se o tipo da consulta de cliente � de municipio
			// se for os parametros de enviarDadosParametros ser�o mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"municipio")) {

				pesquisarActionForm.set("idMunicipioCliente",
						httpServletRequest.getParameter("idCampoEnviarDados"));

				pesquisarActionForm.set("descricaoMunicipioClienTabelaAuxiliarIndicadorActionFormte",
						httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

			}
			// Verifica se o tipo da consulta de cliente � de bairro
			// se for os parametros de enviarDadosParametros ser�o mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta")
					.equals("bairro")) {

				pesquisarActionForm.set("codigoBairroCliente",
						httpServletRequest.getParameter("idCampoEnviarDados"));

				pesquisarActionForm.set("descricaoBairroCliente",
						httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

			}
			// Verifica se o tipo da consulta de imovel � de logradouro
			// se for os parametros de enviarDadosParametros ser�o mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"logradouro")) {

				pesquisarActionForm.set("idLogradouroCliente",
						httpServletRequest.getParameter("idCampoEnviarDados"));

				pesquisarActionForm.set("nomeLogradouroCliente",
						httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

			}
		}

		// envia uma flag que ser� verificado no cliente_resultado_pesquisa.jsp
		// para saber se ir� usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaCliente") != null && 
				!"".equals(httpServletRequest.getParameter("caminhoRetornoTelaPesquisaCliente"))) {

			sessao.setAttribute("caminhoRetornoTelaPesquisaCliente",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaCliente"));
		} else {
			
			if ((httpServletRequest
					.getParameter("objetoConsulta") == null || 
					"".equals(httpServletRequest.getParameter("objetoConsulta"))) && (httpServletRequest
							.getParameter("voltarPesquisa") == null || 
							"".equals(httpServletRequest.getParameter("voltarPesquisa"))) && 
							(httpServletRequest.getParameter("tipoConsulta") == null 
									|| httpServletRequest.getParameter("tipoConsulta").equals(""))) {
				
				sessao.removeAttribute("caminhoRetornoTelaPesquisaCliente");
				
			}

		}

		if (pesquisarActionForm.get("tipoPesquisa") == null
				|| pesquisarActionForm.get("tipoPesquisa").equals("")) {

			pesquisarActionForm.set("tipoPesquisa",
					ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		}

		return retorno;
	}
}