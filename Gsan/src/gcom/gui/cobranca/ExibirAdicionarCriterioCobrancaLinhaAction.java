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
package gcom.gui.cobranca;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cobranca.CobrancaCriterioLinha;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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
 * Pre- processamento para adicionar a linha do criterio da cobran�a
 * 
 * @author S�vio Luiz
 * @date 02/05/2006
 */
public class ExibirAdicionarCriterioCobrancaLinhaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("adicionarCriterioCobrancaLinha");

		CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// par�metro respons�vel pelo redirecionamento do adicionar criterio
		// cobran�a linha
		// se retornarTela estiver o valor inserir retorna para o action de
		// inserir
		// se retornarTela estiver o valor atualizar retorna para o action de
		// atualizar
		String retornarTela = httpServletRequest.getParameter("retornarTela");
		sessao.setAttribute("retornarTela", retornarTela);

		Fachada fachada = Fachada.getInstancia();

		// cria as vari�veis para recuperar os par�metros do request e jogar
		// no objeto
		// cobran�a crit�rio linha
		String vlMinDebito = "";
		String vlMaxDebito = "";
		String qtdMinContas = "";
		String qtdMaxContas = "";
		String vlMinDebCliente = "";
		String qtdMinConCliente = "";
		String vlMinConMes = "";
		String qdtParcelasMinimas = "";

		if (sessao.getAttribute("colecaoCobrancaCriterioLinha") != null
				&& !sessao.getAttribute("colecaoCobrancaCriterioLinha").equals(
						"")) {
			Collection colecaoCobrancaCriterioLinha = (Collection) sessao
					.getAttribute("colecaoCobrancaCriterioLinha");

			int tamanhoColecao = colecaoCobrancaCriterioLinha.size();

			if (tamanhoColecao != 0) {

				// recupera o ultimo criterio de cobran�a linha
				CobrancaCriterioLinha cobrancaCriterioLinha = (CobrancaCriterioLinha) ((List) colecaoCobrancaCriterioLinha)
						.get(tamanhoColecao - 1);

				// formata os valores para jogar no form
				if (cobrancaCriterioLinha.getValorMinimoDebito() != null
						&& !cobrancaCriterioLinha.getValorMinimoDebito()
								.equals("")) {
					vlMinDebito = Util.formatarMoedaReal(cobrancaCriterioLinha
							.getValorMinimoDebito());
				}
				if (cobrancaCriterioLinha.getValorMaximoDebito() != null
						&& !cobrancaCriterioLinha.getValorMaximoDebito()
								.equals("")) {
					vlMaxDebito = Util.formatarMoedaReal(cobrancaCriterioLinha
							.getValorMaximoDebito());
				}
				if (cobrancaCriterioLinha.getQuantidadeMinimaContas() != null
						&& !cobrancaCriterioLinha.getQuantidadeMinimaContas()
								.equals("")) {
					qtdMinContas = ""
							+ cobrancaCriterioLinha.getQuantidadeMinimaContas();
				}
				if (cobrancaCriterioLinha.getQuantidadeMaximaContas() != null
						&& !cobrancaCriterioLinha.getQuantidadeMaximaContas()
								.equals("")) {
					qtdMaxContas = ""
							+ cobrancaCriterioLinha.getQuantidadeMaximaContas();
				}
				if (cobrancaCriterioLinha
						.getValorMinimoDebitoDebitoAutomatico() != null
						&& !cobrancaCriterioLinha
								.getValorMinimoDebitoDebitoAutomatico().equals(
										"")) {
					vlMinDebCliente = Util
							.formatarMoedaReal(cobrancaCriterioLinha
									.getValorMinimoDebitoDebitoAutomatico());
				}
				if (cobrancaCriterioLinha
						.getQuantidadeMinimaContasDebitoAutomatico() != null
						&& !cobrancaCriterioLinha
								.getQuantidadeMinimaContasDebitoAutomatico()
								.equals("")) {
					qtdMinConCliente = ""
							+ cobrancaCriterioLinha
									.getQuantidadeMinimaContasDebitoAutomatico();
				}
				if (cobrancaCriterioLinha.getValorMinimoContaMes() != null
						&& !cobrancaCriterioLinha.getValorMinimoContaMes()
								.equals("")) {
					vlMinConMes = Util.formatarMoedaReal(cobrancaCriterioLinha
							.getValorMinimoContaMes());
				}
				
				if (criterioCobrancaActionForm.getQuantidadeMinimaParcelasAtraso() != null
						&& !criterioCobrancaActionForm
								.getQuantidadeMinimaParcelasAtraso().equals("")) {
					qdtParcelasMinimas = cobrancaCriterioLinha
							.getQuantidadeMinimaContasParcelamento().toString();

				} else {
					qdtParcelasMinimas = "0";
				}

			}

		}

		
		if (httpServletRequest.getParameter("limparPopup") != null){
			criterioCobrancaActionForm.setValorDebitoMinimo("");
			criterioCobrancaActionForm.setValorDebitoMaximo("");
			criterioCobrancaActionForm.setQtdContasMinima("");
			criterioCobrancaActionForm.setQtdContasMaxima("");
			criterioCobrancaActionForm.setVlMinimoDebitoCliente("");
			criterioCobrancaActionForm.setQtdMinContasCliente("");
			criterioCobrancaActionForm.setVlMinimoContasMes("");
			criterioCobrancaActionForm.setQuantidadeMinimaParcelasAtraso("");
		}else{
			//seta os valores da ultima linha da cobran�a criterio
			criterioCobrancaActionForm.setValorDebitoMinimo(vlMinDebito);
			criterioCobrancaActionForm.setValorDebitoMaximo(vlMaxDebito);
			criterioCobrancaActionForm.setQtdContasMinima(qtdMinContas);
			criterioCobrancaActionForm.setQtdContasMaxima(qtdMaxContas);
			criterioCobrancaActionForm.setVlMinimoDebitoCliente(vlMinDebCliente);
			criterioCobrancaActionForm.setQtdMinContasCliente(qtdMinConCliente);
			criterioCobrancaActionForm.setVlMinimoContasMes(vlMinConMes);
			criterioCobrancaActionForm.setQuantidadeMinimaParcelasAtraso(qdtParcelasMinimas);
		}
		
		

		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				FiltroImovelPerfil.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil,
				ImovelPerfil.class.getName());
		if (colecaoImovelPerfil == null || colecaoImovelPerfil.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Imovel Perfil");
		}

		FiltroCategoria filtroCategoria = new FiltroCategoria();
		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoCategoria = fachada.pesquisar(filtroCategoria,
				Categoria.class.getName());
		if (colecaoCategoria == null || colecaoCategoria.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Categoria");
		}

		
		sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		sessao.setAttribute("colecaoCategoria", colecaoCategoria);

		httpServletRequest.setAttribute("fechaPopup", "false");

		return retorno;
	}
}
