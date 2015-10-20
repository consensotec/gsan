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
package gcom.gui.micromedicao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidadeAcao;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Thiago Ten�rio
 * @date 05/08/2006
 */
public class FiltrarConsumoAnormalidadeAcaoAction extends GcomAction {

	/**
	 * 
	 * 
	 * [UC1059] Filtrar Consumo Anormalidade e A��o
	 * 
	 * 
	 * @author Rodrigo Cabral
	 * @date 01/10/2010
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

		ActionForward retorno = actionMapping
				.findForward("exibirManterConsumoAnormalidadeAcao");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarConsumoAnormalidadeAcaoActionForm form = (FiltrarConsumoAnormalidadeAcaoActionForm) actionForm;

		FiltroConsumoAnormalidadeAcao filtroConsumoAnormalidadeAcao = new FiltroConsumoAnormalidadeAcao();

		// Fachada fachada = Fachada.getInstancia();

		// Indicador Atualizar
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {

			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			
				sessao.removeAttribute("indicadorAtualizar");
			
		}
		
		boolean peloMenosUmParametroInformado = false;

		filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroConsumoAnormalidadeAcao.CONSUMO_ANORMALIDADE);
		
		filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroConsumoAnormalidadeAcao.CATEGORIA);

		filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroConsumoAnormalidadeAcao.IMOVEL_PERFIL);
		
		filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroConsumoAnormalidadeAcao.CONSUMO_COBRAR_MES1);
		
		filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroConsumoAnormalidadeAcao.CONSUMO_COBRAR_MES2);
		
		filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroConsumoAnormalidadeAcao.CONSUMO_COBRAR_MES3);
		
		filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroConsumoAnormalidadeAcao.SERVICO_TIPO_MES1);
		
		filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroConsumoAnormalidadeAcao.SERVICO_TIPO_MES2);
		
		filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroConsumoAnormalidadeAcao.SERVICO_TIPO_MES3);
		
		filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroConsumoAnormalidadeAcao.SOLICITACAO_TIPO_ESPECIFICACAO_MES1);
		
		filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroConsumoAnormalidadeAcao.SOLICITACAO_TIPO_ESPECIFICACAO_MES2);
		
		filtroConsumoAnormalidadeAcao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroConsumoAnormalidadeAcao.SOLICITACAO_TIPO_ESPECIFICACAO_MES3);
		
		
		// Verifica se o campo Consumo Anormalidade foi informado

		if (form.getConsumoAnormalidade() != null && !form.getConsumoAnormalidade().equals("-1")) {

			peloMenosUmParametroInformado = true;

				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.CONSUMO_ANORMALIDADE, form.getConsumoAnormalidade()));
			
		
		}

		// Verifica se o campo Categoria foi informado

		if (form.getCategoria() != null && !form.getCategoria().equals("-1")) {

			peloMenosUmParametroInformado = true;

		
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.CATEGORIA, form.getCategoria()));
			
		
		}

		// Verifica se o campo Perfil de Im�vel foi informado

		if (form.getImovelPerfil() != null && !form.getImovelPerfil().equals("-1")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.IMOVEL_PERFIL, form.getImovelPerfil()));
			
		
		}

		// Verifica se o campo Consumo a Cobrar para o 1� M�s foi informado

		if (form.getLeituraAnormalidadeConsumoMes1() != null && !form.getLeituraAnormalidadeConsumoMes1().equals("-1")) {

			peloMenosUmParametroInformado = true;

		
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.CONSUMO_COBRAR_MES1, form.getLeituraAnormalidadeConsumoMes1()));
			
		
		}

		// Verifica se o campo Consumo a Cobrar para o 2� M�s foi informado

		if (form.getLeituraAnormalidadeConsumoMes2() != null && !form.getLeituraAnormalidadeConsumoMes2().equals("-1")) {

		
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.CONSUMO_COBRAR_MES2, form.getLeituraAnormalidadeConsumoMes2()));
			
	
		}
		// Verifica se o campo Consumo a Cobrar para o 3� M�s foi informado

		if (form.getLeituraAnormalidadeConsumoMes3() != null && !form.getLeituraAnormalidadeConsumoMes3().equals("-1")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.CONSUMO_COBRAR_MES3, form.getLeituraAnormalidadeConsumoMes3()));
			
		
		}
		
		// Verifica se o campo Fator de Consumo para c�lculo do 1� M�s foi informado

		if (form.getNumerofatorConsumoMes1() != null && !form.getNumerofatorConsumoMes1().equals("")) {

			peloMenosUmParametroInformado = true;

		
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.FATOR_CONSUMO_CALCULO_MES1, form.getNumerofatorConsumoMes1()));
			
		
		}

		// Verifica se o campo Fator de Consumo para c�lculo do 2� M�s foi informado

		if (form.getNumerofatorConsumoMes2() != null && !form.getNumerofatorConsumoMes2().equals("")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.FATOR_CONSUMO_CALCULO_MES2, form.getNumerofatorConsumoMes2()));
			
		
		}
		
		// Verifica se o campo Fator de Consumo para c�lculo do 3� M�s foi informado

		if (form.getNumerofatorConsumoMes3() != null && !form.getNumerofatorConsumoMes3().equals("")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.FATOR_CONSUMO_CALCULO_MES3, form.getNumerofatorConsumoMes3()));
			
		
		}
		
		// Verifica se o campo Indicador de Gera��o de Carta do 1� M�s foi informado

		if (form.getIndicadorGeracaoCartaMes1() != null && !form.getIndicadorGeracaoCartaMes1().equals("")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.INDICADOR_GERACAO_CARTA_MES1, form.getIndicadorGeracaoCartaMes1()));
			
		
		}

		// Verifica se o campo Indicador de Gera��o de Carta do 2� M�s foi informado

		if (form.getIndicadorGeracaoCartaMes2() != null && !form.getIndicadorGeracaoCartaMes2().equals("")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.INDICADOR_GERACAO_CARTA_MES2, form.getIndicadorGeracaoCartaMes2()));
			
		
		}
		
		// Verifica se o campo Indicador de Gera��o de Carta do 3� M�s foi informado

		if (form.getIndicadorGeracaoCartaMes3() != null && !form.getIndicadorGeracaoCartaMes3().equals("")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.INDICADOR_GERACAO_CARTA_MES3, form.getIndicadorGeracaoCartaMes3()));
			
		
		}
		
		// Verifica se o campo Tipo de Servi�o para o 1� M�s foi informado

		if (form.getIdServicoTipoMes1() != null && !form.getIdServicoTipoMes1().equals("")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.SERVICO_TIPO_MES1, form.getIdServicoTipoMes1()));
			
		
		}
		
		// Verifica se o campo Tipo de Servi�o para o 2� M�s foi informado

		if (form.getIdServicoTipoMes2() != null && !form.getIdServicoTipoMes2().equals("")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.SERVICO_TIPO_MES2, form.getIdServicoTipoMes2()));
			
		
		}
		
		// Verifica se o campo Tipo de Servi�o para o 3� M�s foi informado

		if (form.getIdServicoTipoMes3() != null && !form.getIdServicoTipoMes3().equals("")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.SERVICO_TIPO_MES3, form.getIdServicoTipoMes3()));
			
		
		}
		
		// Verifica se o campo Tipo de Especifica��o para o 1� M�s foi informado

		if (form.getSolicitacaoTipoEspecificacaoMes1() != null && !form.getSolicitacaoTipoEspecificacaoMes1().equals("-1")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.SOLICITACAO_TIPO_ESPECIFICACAO_MES1, form.getSolicitacaoTipoMes1()));
			
		
		}
		
		// Verifica se o campo Tipo de Especifica��o para o 2� M�s foi informado

		if (form.getSolicitacaoTipoEspecificacaoMes2() != null && !form.getSolicitacaoTipoEspecificacaoMes2().equals("")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.SOLICITACAO_TIPO_ESPECIFICACAO_MES2, form.getSolicitacaoTipoMes2()));
			
		
		}
		
		// Verifica se o campo Tipo de Especifica��o para o 3� M�s foi informado

		if (form.getSolicitacaoTipoEspecificacaoMes3() != null && !form.getSolicitacaoTipoEspecificacaoMes3().equals("")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.SOLICITACAO_TIPO_ESPECIFICACAO_MES3, form.getSolicitacaoTipoMes3()));
			
		
		}
		
		// Verifica se o campo Mensagem da Conta no 1� M�s foi informado

		if (form.getDescricaoContaMensagemMes1() != null && !form.getDescricaoContaMensagemMes1().equals("")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.MENSAGEM_CONTA_MES1, form.getDescricaoContaMensagemMes1()));
			
		
		}
		
		// Verifica se o campo Mensagem da Conta no 2� M�s foi informado

		if (form.getDescricaoContaMensagemMes2() != null && !form.getDescricaoContaMensagemMes2().equals("")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.MENSAGEM_CONTA_MES2, form.getDescricaoContaMensagemMes2()));
			
		
		}
		
		// Verifica se o campo Mensagem da Conta no 3� M�s foi informado

		if (form.getDescricaoContaMensagemMes3() != null && !form.getDescricaoContaMensagemMes3().equals("")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.MENSAGEM_CONTA_MES3, form.getDescricaoContaMensagemMes3()));
			
		
		}
		
		
		// Verifica se o campo Indicador de Uso foi informado

		if (form.getIndicadorUso() != null && !form.getIndicadorUso().equals("")) {

			peloMenosUmParametroInformado = true;

			
				filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(
						FiltroConsumoAnormalidadeAcao.INDICADOR_USO, form.getIndicadorUso()));
			
		
		}
		// Erro caso o usu�rio mandou Pesquisar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

//		// filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");

		sessao.setAttribute("filtroConsumoAnormalidadeAcao",
				filtroConsumoAnormalidadeAcao);

		 return retorno;
	}

}