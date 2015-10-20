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

import gcom.cadastro.cliente.FiltroCliente;
import gcom.gui.ActionServletException;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesColecao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que filtra os clientes do sistema de acordo com os par�mentros informados
 * pelo usu�rio.
 *
 * @author Rodrigo
 * @date 23/07/2005
 */
public class FiltrarClienteAction extends Action {
	
	/**
	 * <Breve descri��o sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Rodrigo
	 * @date 23/07/2005
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
				.findForward("retornarFiltroCliente");
		
		// Mudar isso quando implementar a parte de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarClienteActionForm filtrarClienteActionForm = (FiltrarClienteActionForm) actionForm;

		// Recupera os par�metros do form
		String id = (String) filtrarClienteActionForm.getCodigoClienteFiltro();
		String cpf = (String) filtrarClienteActionForm.getCpfClienteFiltro();
		String rg = (String) filtrarClienteActionForm.getRgClienteFiltro();
		String cnpj = (String) filtrarClienteActionForm.getCnpjClienteFiltro();
		String nome = (String) filtrarClienteActionForm.getNomeClienteFiltro();
		String nomeMae = (String) filtrarClienteActionForm.getNomeMaeClienteFiltro();		
		String cep = (String) filtrarClienteActionForm.getCepClienteFiltro();
		String idMunicipioCliente = (String) filtrarClienteActionForm.getMunicipioClienteFiltro();
		String codigoBairroCliente = (String) filtrarClienteActionForm.getBairroClienteFiltro();
		String idLogradouro = (String) filtrarClienteActionForm.getLogradouroClienteFiltro();
		String indicadorUso = (String) filtrarClienteActionForm.getIndicadorUsoClienteFiltro();
		String tipoPesquisa = (String) filtrarClienteActionForm.getTipoPesquisa();
		String tipoPesquisaNomeMae = (String) filtrarClienteActionForm.getTipoPesquisaNomeMae();
		String idEsferaPoder = (String) filtrarClienteActionForm.getIdEsferaPoder();
		String atualizar = (String)httpServletRequest.getParameter("atualizarFiltro");
		
		sessao.setAttribute("atualizar", atualizar);
		
		if (atualizar != null && atualizar.equals("1")) {
			httpServletRequest.setAttribute("atualizar", atualizar);
		}else{
			filtrarClienteActionForm.setAtualizarFiltro("");
		}
		
		// cria o filtro para Tabela Auxiliar
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.setCampoOrderBy(FiltroCliente.NOME);
		/*filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.ORGAO_EXPEDIDOR_RG);*/
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.UNIDADE_FEDERACAO);
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
		filtroCliente
			.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");

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
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroCliente.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroCliente.NOME, nome));
			} else {
				filtroCliente.adicionarParametro(new ComparacaoTexto(
						FiltroCliente.NOME, nome));
			}

		}
		//Nome da M�e
		if (nomeMae  != null && !nomeMae.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisaNomeMae != null
					&& tipoPesquisaNomeMae
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroCliente.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroCliente.NOME_MAE, nomeMae));
			} else {
				filtroCliente.adicionarParametro(new ComparacaoTexto(
						FiltroCliente.NOME_MAE, nomeMae));
			}

		}
		
		if (codigoBairroCliente != null
				&& !codigoBairroCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
					FiltroCliente.BAIRRO_CODIGO, codigoBairroCliente));
		}
		if (idLogradouro != null && !idLogradouro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
					FiltroCliente.LOGRADOURO, idLogradouro));
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

		if (idMunicipioCliente != null
				&& !idMunicipioCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroCliente.adicionarParametro(new ParametroSimplesColecao(
					FiltroCliente.MUNICIPIO_ID, idMunicipioCliente));
		}
		
		if (idEsferaPoder != null && !idEsferaPoder.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ESFERA_PODER_ID, idEsferaPoder));
		}

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		filtroCliente.setCampoDistinct(FiltroCliente.NOME);

		// Manda o filtro pela sess�o para o ExibirManterClienteAction
		sessao.setAttribute("filtroCliente", filtroCliente);

		
		sessao.setAttribute("codigo",(String) filtrarClienteActionForm.getCodigoClienteFiltro());
		sessao.setAttribute("cpf",(String) filtrarClienteActionForm.getCpfClienteFiltro());
		sessao.setAttribute("rg",(String) filtrarClienteActionForm.getRgClienteFiltro());
		sessao.setAttribute("cnpj",(String) filtrarClienteActionForm.getCnpjClienteFiltro());
		sessao.setAttribute("nome",(String) filtrarClienteActionForm.getNomeClienteFiltro());
		sessao.setAttribute("nomeMae",(String) filtrarClienteActionForm.getNomeMaeClienteFiltro());		
		sessao.setAttribute("cep",(String) filtrarClienteActionForm.getCepClienteFiltro());
		sessao.setAttribute("idMunicipio",(String) filtrarClienteActionForm.getMunicipioClienteFiltro());
		sessao.setAttribute("codigoBairro",(String) filtrarClienteActionForm.getBairroClienteFiltro());
		sessao.setAttribute("idLogradouro",(String) filtrarClienteActionForm.getLogradouroClienteFiltro());
		sessao.setAttribute("indicadorUso",(String) filtrarClienteActionForm.getIndicadorUsoClienteFiltro());
		sessao.setAttribute("tipoPesquisa",(String) filtrarClienteActionForm.getTipoPesquisa());
		sessao.setAttribute("tipoPesquisaNomeMae",(String) filtrarClienteActionForm.getTipoPesquisaNomeMae());
		sessao.setAttribute("idEsferaPoder",(String) filtrarClienteActionForm.getIdEsferaPoder());

		
		// Devolve o mapeamento de retorno
		return retorno;
	}
}
