/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gcom.gui.financeiro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.financeiro.FiltroPerdasTipo;
import gcom.financeiro.PerdasTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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
 * Gerar resumo dos devedores duvidosos.
 * 
 * @author Pedro Alexandre
 * @date 08/06/2007
 */
public class ExibirGerarResumoDevedoresDuvidososAction extends GcomAction {
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
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// seta o retorno para a página de gerar resumo dos devedores duvidosos
		ActionForward retorno = actionMapping.findForward("exibirGerarResumoDevedoresDuvidosos");

		GerarResumoDevedoresDuvidososActionForm form = (GerarResumoDevedoresDuvidososActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// O usuario informa o tipo de perda
		if (httpServletRequest.getParameter("idTipoPerda") != null && !httpServletRequest.getParameter("idTipoPerda").equals("")) {

			String idTipoPerda = (String) httpServletRequest.getParameter("idTipoPerda");

			//Caso o tipo de perda seja igual a vazio.
			if ( idTipoPerda.equals("desfazer") ) {
				
				sessao.removeAttribute("baixaPerdaSocietaria");
				sessao.removeAttribute("baixaPerdaOrgaoPublico");
				limparForm(form);
				
			} else if (idTipoPerda.equals(PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS + "")) {

				// exibe dados na tela para simulação/geração das perdas
				// societárias
				sessao.setAttribute("baixaPerdaSocietaria", idTipoPerda);
				sessao.removeAttribute("baixaPerdaOrgaoPublico");
				limparForm(form);
				//Real 
				form.setIndicadorTipoGeracao("1");

			} else if (idTipoPerda.equals(PerdasTipo.PERDAS_ORGAO_PUBLICO + "")) {

			
					// exibe dados na tela para geração das perdas públicas
					sessao.setAttribute("baixaPerdaOrgaoPublico", idTipoPerda);
					sessao.removeAttribute("baixaPerdaSocietaria");
					limparForm(form);
					form.setNumeroMesesContasVencidas("60");

			} else if (idTipoPerda.equals(PerdasTipo.RECUPERACAO_DA_PROVISAO_DE_PERDAS_SOCIETARIAS + "")) {
				
				sessao.removeAttribute("baixaPerdaSocietaria");
				sessao.removeAttribute("baixaPerdaOrgaoPublico");
				limparForm(form);
			
			
				//[FS0014] - Pesquisar Referência do Faturamento/Arrecadação 
	            SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
	            if( sistemaParametro.getAnoMesFaturamento().toString().equals(form.getAnoMesReferenciaContabil() )) {
	            	  throw new ActionServletException("atencao.referencia_faturamento_nao_encerrada");
	            }
	            if( sistemaParametro.getAnoMesArrecadacao().toString().equals(form.getAnoMesReferenciaContabil()) ) {
	            	  throw new ActionServletException("atencao.referencia_arrecadacao_nao_encerrada");
	            }
			} else if (idTipoPerda.equals(PerdasTipo.PERDAS_FISCAIS + "")) {
				
				sessao.removeAttribute("baixaPerdaSocietaria");
				sessao.removeAttribute("baixaPerdaOrgaoPublico");
				limparForm(form);
			}

		} else {

			// Pesquisa os tipos de perdas.
			FiltroPerdasTipo filtroPerdasTipo = new FiltroPerdasTipo();
			filtroPerdasTipo.adicionarParametro(new ParametroSimples(	FiltroPerdasTipo.INDICADORUSO,
																		ConstantesSistema.SIM));

			Collection<PerdasTipo> colecaPerdasTipos = fachada.pesquisar(filtroPerdasTipo, PerdasTipo.class.getName());

			if (colecaPerdasTipos != null && !colecaPerdasTipos.isEmpty()) {

				sessao.setAttribute("colecaoPerdasTipo", colecaPerdasTipos);

			}
			
			form.setMesAnoAtual(Util.recuperaMesAnoComBarraDaData(new Date()));
		}

		return retorno;
	}
	
	/**
	 * metodo responsavel por limpar os valores do form.
	 * @param form
	 */
	private void limparForm(GerarResumoDevedoresDuvidososActionForm form) {
		
		    form.setPeriodoBaixaInicial("");
		    form.setPeriodoBaixaFinal("");
		    form.setNumeroMesesContasInferiores("");
		    form.setIndicadorCategoriaResidencial("");
		    form.setIndicadorCategoriaComercial("");
		    form.setIndicadorCategoriaIndustrial("");
		    form.setIndicadorCategoriaPublica("");
		    form.setIndicadorEsferaParticular("");
		    form.setIndicadorEsferaMunicipal("");
		    form.setIndicadorEsferaEstadual("");
		    form.setIndicadorEsferaFederal("");
		    form.setIndicadorTipoGeracao("");
		    form.setNumeroMesesContasVencidas("");
		    form.setIndicadorEsferaPoderMunicipal("");
		    form.setIndicadorEsferaPoderFederal("");
		
	}
}
