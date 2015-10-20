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

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
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
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * [UC0000] Pesquisar Cliente
 * Realiza a pesquisa de cliente de acordo com os par�metros informados
 * 
 * @author S�vio Luiz, Roberta Costa
 * @created 21/07/2005, 11/07/2006
 */
public class PesquisarClienteAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("listaCliente");

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Recupera os par�metros do form
		Integer idTipoCliente = (Integer) pesquisarActionForm
				.get("idTipoCliente");
		String nomeCliente = (String) pesquisarActionForm.get("nomeCliente");
		String cpf = (String) pesquisarActionForm.get("cpf");
		String rg = (String) pesquisarActionForm.get("rg");
		String cnpj = (String) pesquisarActionForm.get("cnpj");
		String cep = (String) pesquisarActionForm.get("cepClienteEndereco");
		String idMunicipio = (String) pesquisarActionForm.get("idMunicipioCliente");
		String codigoBairro = (String) pesquisarActionForm.get("codigoBairroCliente");
		String idLogradouro = (String) pesquisarActionForm.get("idLogradouroCliente");
		String idEsferaPoder = (String) pesquisarActionForm.get("idEsferaPoder");
		String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");

		// filtro para a pesquisa de endereco do cliente
		/*FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.setCampoOrderBy(FiltroCliente.NOME);
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.ORGAO_EXPEDIDOR_RG);
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.UNIDADE_FEDERACAO);
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade("clienteTipo.indicadorPessoaFisicaJuridica");
*/
		boolean peloMenosUmParametroInformado = false;

		// Insere os par�metros informados no filtro
		if (idTipoCliente != null
				&& idTipoCliente.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
//			filtroCliente.adicionarParametro(new ParametroSimples(
//					FiltroCliente.TIPOCLIENTE_ID, new Integer(idTipoCliente)));
		}

		if (nomeCliente != null && !nomeCliente.trim().equalsIgnoreCase("")) {
            nomeCliente = nomeCliente.trim(); 
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
//				filtroCliente.adicionarParametro(new ComparacaoTextoCompleto(
//						FiltroCliente.NOME, nomeCliente));
			} else {
//				filtroCliente.adicionarParametro(new ComparacaoTexto(
//						FiltroCliente.NOME, nomeCliente));
			}
		}

		if (cnpj != null && !cnpj.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
//			filtroCliente.adicionarParametro(new ParametroSimples(
//					FiltroCliente.CNPJ, cnpj));
		}

		if (cpf != null && !cpf.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
//			filtroCliente.adicionarParametro(new ParametroSimples(
//					FiltroCliente.CPF, cpf));
		}

		if (rg != null && !rg.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
//			filtroCliente.adicionarParametro(new ParametroSimples(
//					FiltroCliente.RG, rg));
		}

		if (cep != null && !cep.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			FiltroCep filtroCep = new FiltroCep();
			filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CEPID, cep));
			
			Collection<?> colCep = this.getFachada().pesquisar(filtroCep, Cep.class.getName());
			if(Util.isVazioOrNulo(colCep)){
				throw new ActionServletException("atencao.cep.inexistente");
			}
			
//			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
//					FiltroCliente.CEP, cep));
		}

		if (idMunicipio != null && !idMunicipio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMunicipio));
			
			Collection<?> colMunicipio = this.getFachada().pesquisar(filtroMunicipio, Municipio.class.getName());
			if(Util.isVazioOrNulo(colMunicipio)){
				throw new ActionServletException("atencao.municipio.inexistente");
			}
			
//			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
//					FiltroCliente.MUNICIPIO_ID, new Integer(idMunicipio)));
		}

		if (codigoBairro != null && !codigoBairro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			FiltroBairro filtroBairro = new FiltroBairro();
			filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.CODIGO, codigoBairro));
			
			Collection<?> colBairro = this.getFachada().pesquisar(filtroBairro, Bairro.class.getName());
			if(Util.isVazioOrNulo(colBairro)){
				throw new ActionServletException("atencao.bairro.inexistente");
			}
			
//			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
//					FiltroCliente.BAIRRO_CODIGO, new Integer(codigoBairro)));
		}

		if (idLogradouro != null && !idLogradouro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
			filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, idLogradouro));
			
			Collection<?> colLogradouro = this.getFachada().pesquisar(filtroLogradouro, Logradouro.class.getName());
			if(Util.isVazioOrNulo(colLogradouro)){
				throw new ActionServletException("atencao.pesquisa.logradouro_inexistente");
			}
			
//			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
//					FiltroCliente.LOGRADOURO, new Integer(idLogradouro)));
		}
		
		if (idEsferaPoder != null && !idEsferaPoder.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
		}

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		//filtroCliente.setCampoOrderBy(FiltroCliente.NOME);
		
		// 1� Passo - Pegar o total de registros atrav�s de um count da consulta que aparecer� na tela
		Integer totalRegistros = (Integer) fachada.filtrarQuantidadeCliente(null,
				cpf,
				rg,
				cnpj,
				nomeCliente,
				null,		
				cep,
				idMunicipio,
				codigoBairro,
				idLogradouro,
				null,
				tipoPesquisa,
				null,
				idTipoCliente.toString(), idEsferaPoder);

		// 2� Passo - Chamar a fun��o de Pagina��o passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		// 3� Passo - Obter a cole��o da consulta que aparecer� na tela passando o numero de paginas
		// da pesquisa que est� no request
		Collection clientes = fachada.filtrarCliente(
				null,
				cpf,
				rg,
				cnpj,
				nomeCliente,
				null,		
				cep,
				idMunicipio,
				codigoBairro,
				idLogradouro,
				null,
				tipoPesquisa,
				null,
				idTipoCliente.toString(),
				idEsferaPoder,
				(Integer) httpServletRequest
						.getAttribute("numeroPaginasPesquisa"));
		

/*		// 1� Passo - Pegar o total de registros atrav�s de um count da consulta que aparecer� na tela
		Integer totalRegistros = fachada
				.pesquisarClienteDadosClienteEnderecoCount(filtroCliente);

		// 2� Passo - Chamar a fun��o de Pagina��o passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		// 3� Passo - Obter a cole��o da consulta que aparecer� na tela passando o numero de paginas
		// da pesquisa que est� no request
		Collection clientes = fachada
				.pesquisarClienteDadosClienteEndereco(filtroCliente, (Integer) httpServletRequest
						.getAttribute("numeroPaginasPesquisa"));
*/
		if (clientes == null || clientes.isEmpty()) {
			// Nenhuma cliente cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "cliente");
		} else {
			// Coloca a cole��o na sess�o
			sessao.setAttribute("colecaoCliente",clientes);
		}
		
		//Coloca na sessao o parametro tipoConsulta
		String tipoConsulta = httpServletRequest.getParameter("tipoConsulta");
		if(tipoConsulta == null || tipoConsulta.equals("")){
			tipoConsulta = (String) sessao.getAttribute("tipoConsulta");
		}
		if(tipoConsulta != null && !tipoConsulta.equals("")){
			sessao.setAttribute("tipoConsulta", tipoConsulta);
		}else{
			sessao.removeAttribute("tipoConsulta");
		}

		return retorno;
	}

}