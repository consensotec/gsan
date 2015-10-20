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

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesColecao;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class FiltrarClienteOutrosCriteriosAction extends GcomAction {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("gerarRelatorioCliente");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		sessao.removeAttribute("clientesNovos");

		String indicadorTela = httpServletRequest.getParameter("indicadorTela");

		// Recupera os par�metros do form
		String id = (String) pesquisarActionForm.get("idCliente");
		String nome = (String) pesquisarActionForm.get("nomeCliente");
		String nomeAbreviado = (String) pesquisarActionForm
				.get("nomeAbreviadoCliente");
		String matriculaImovel = (String) pesquisarActionForm.get("idImovel");
		String tipoCliente = (String) pesquisarActionForm.get("tipoCliente");
		String cpf = (String) pesquisarActionForm.get("cpf");
		String cnpj = (String) pesquisarActionForm.get("cnpj");
		String rg = (String) pesquisarActionForm.get("rg");
		String ramoAtividade = (String) pesquisarActionForm
				.get("ramoAtividade");
		String dataEmissao = (String) pesquisarActionForm.get("dataEmissao");
		String orgaoEmissor = (String) pesquisarActionForm.get("orgaoEmissor");
		String dataNascimento = (String) pesquisarActionForm
				.get("dataNascimento");
		String idClienteResponsavel = (String) pesquisarActionForm
				.get("idClienteResponsavel");
		String profissao = (String) pesquisarActionForm.get("profissao");
		String email = (String) pesquisarActionForm.get("email");
		String cep = (String) pesquisarActionForm.get("cep");
		String idMunicipioCliente = (String) pesquisarActionForm
				.get("idMunicipio");
		String codigoBairroCliente = (String) pesquisarActionForm
				.get("codigoBairro");
		String logradouroCliente = (String) pesquisarActionForm
				.get("idLogradouro");
		String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");
		String sexo = (String) pesquisarActionForm.get("sexo");

		// cria o filtro para Tabela Auxiliar
		FiltroCliente filtroCliente = new FiltroCliente();

		boolean peloMenosUmParametroInformado = false;

		// Insere os par�metros informados no filtro
		if (id != null && !id.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, id));
		}
		if (cpf != null && !cpf.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CPF, cpf));
		}
		if (rg != null && !rg.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.RG, rg));
		}
		if (cnpj != null && !cnpj.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CNPJ, cnpj));
		}
		if (nome != null && !nome.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ComparacaoTexto(
					FiltroCliente.NOME, nome));
		}
		if (idMunicipioCliente != null
				&& !idMunicipioCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
					FiltroCliente.MUNICIPIO_ID, idMunicipioCliente));
		}
		if (codigoBairroCliente != null
				&& !codigoBairroCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
					FiltroCliente.BAIRRO_CODIGO, codigoBairroCliente));
		}
		if (logradouroCliente != null
				&& !logradouroCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
					FiltroCliente.LOGRADOURO, logradouroCliente));
		}
		if (indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO, indicadorUso));
		}
		if (cep != null && !cep.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
					FiltroCliente.CEP, cep));
		}
		if (nomeAbreviado != null && !nomeAbreviado.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ComparacaoTexto(
					FiltroCliente.NOME_ABREVIADO, nomeAbreviado));
		}
		if (tipoCliente != null
				&& !tipoCliente.equals(String
						.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& !tipoCliente.equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.TIPOCLIENTE_ID, tipoCliente));
		}
		if (profissao != null
				&& !profissao.equals(String
						.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& !profissao.equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.PROFISSAO_ID, profissao));
		}
		if (matriculaImovel != null
				&& !matriculaImovel.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente
					.adicionarParametro(new ParametroSimplesColecao(
							FiltroCliente.IMOVEL_ID,
							matriculaImovel));
		}
		if (email != null && !email.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ComparacaoTexto(
					FiltroCliente.EMAIL, email));
		}
		if (ramoAtividade != null
				&& !ramoAtividade.equals(String
						.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& !ramoAtividade.equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.RAMO_ATIVIDADE_ID, ramoAtividade));
		}
		if (dataEmissao != null && !dataEmissao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			Date dataEmissaoParametro = Util.converteStringParaDate(dataEmissao);
			
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.DATA_EMISSAO_RG, dataEmissaoParametro));
		}
		if (dataNascimento != null
				&& !dataNascimento.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			Date dataNascimentoParametro = Util.converteStringParaDate(dataNascimento);
			
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.DATA_NASCIMENTO, dataNascimentoParametro));
		}
		if (orgaoEmissor != null
				&& !orgaoEmissor.equals(String
						.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& !orgaoEmissor.equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ORGAO_EXPEDIDOR_RG, orgaoEmissor));
		}
		if (idClienteResponsavel != null
				&& !idClienteResponsavel.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CLIENTE_RESPONSAVEL_ID,
					idClienteResponsavel));
		}
		if (sexo != null
				&& !sexo.equals(String
						.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& !sexo.equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.SEXO_ID, sexo));
		}
		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// if (fachada.registroMaximo(Cliente.class) >
		// ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
		// Se o limite de registros foi atingido, a p�gina de filtragem
		// � chamada
		// retorno = actionMapping.findForward("filtrarClienteOutrosCriterios");
		// }

		// A pesquisa de clientes s� ser� feita se o forward estiver direcionado
		// para a p�gina de manterEmpresa

		// Seta a ordena��o desejada do filtro
		filtroCliente.setCampoOrderBy(FiltroCliente.NOME);
//
//		filtroCliente
//				.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
//		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteEndereco.logradouroCep.cep");
//		filtroCliente
//				.adicionarCaminhoParaCarregamentoEntidade("clienteEndereco.logradouroCep.logradouro.municipio");
//		filtroCliente
//				.adicionarCaminhoParaCarregamentoEntidade("clienteEndereco.logradouroCep.logradouro.logradouroTipo");
//		filtroCliente
//				.adicionarCaminhoParaCarregamentoEntidade("clienteEndereco.logradouroCep.logradouro.logradouroTitulo");
//		filtroCliente
//				.adicionarCaminhoParaCarregamentoEntidade("clienteEndereco.enderecoReferencia");
//		filtroCliente
//				.adicionarCaminhoParaCarregamentoEntidade("clienteEndereco.logradouroBairro.bairro.municipio.unidadeFederacao");
//		filtroCliente
//				.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection<Cliente> clientesNovos = fachada
		.pesquisarClienteOutrosCriterios(filtroCliente);

		if (clientesNovos == null || clientesNovos.isEmpty()) {
			// Nenhum cliente cadastrado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null,
					"cliente");
		}

		// A cole��o fica na sess�o devido ao esquema de pagina��o

		sessao.setAttribute("clientesNovos", clientesNovos);
		sessao.setAttribute("indicadorTela", indicadorTela);
		
		sessao.setAttribute("filtroCliente", filtroCliente);
		sessao.setAttribute("clienteOutrosCriterios", true);

		// Manda o filtro pelo request para o ExibirManterClienteAction
		// httpServletRequest.setAttribute("filtroClienteEndereco",
		// filtroClienteEndereco);

		// Devolve o mapeamento de retorno
		return retorno;
	}
}
