/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de
 * Saneamento This file is part of GSAN, an integrated service management system
 * for Sanitation GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License. GSAN is distributed in
 * the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details. You should have received a
 * copy of the GNU General Public License along with this program; if not, write
 * to the Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston,
 * MA 02111-1307, USA
 */
/*
 * GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento Copyright (C)
 * <2007> Adriano Britto Siqueira Alexandre Santos Cabral Ana Carolina Alves
 * Breda Ana Maria Andrade Cavalcante Aryed Lins de Ara�jo Bruno Leonardo
 * Rodrigues Barros Carlos Elmano Rodrigues Ferreira Cl�udio de Andrade Lira
 * Denys Guimar�es Guenes Tavares Eduardo Breckenfeld da Rosa Borges Fab�ola
 * Gomes de Ara�jo Fl�vio Leonardo Cavalcanti Cordeiro Francisco do Nascimento
 * J�nior Homero Sampaio Cavalcanti Ivan S�rgio da Silva J�nior Jos� Edmar de
 * Siqueira Jos� Thiago Ten�rio Lopes K�ssia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva M�rcio Roberto Batista da Silva Maria de F�tima
 * Sampaio Leite Micaela Maria Coelho de Ara�jo Nelson Mendon�a de Carvalho
 * Newton Morais e Silva Pedro Alexandre Santos da Silva Filho Rafael Corr�a
 * Lima e Silva Rafael Francisco Pinto Rafael Koury Monteiro Rafael Palermo de
 * Ara�jo Raphael Veras Rossiter Roberto Sobreira Barbalho Rodrigo Avellar
 * Silveira Rosana Carvalho Barbosa S�vio Luiz de Andrade Cavalcante Tai Mu Shih
 * Thiago Augusto Souza do Nascimento Tiago Moreno Rodrigues Vivianne Barbosa
 * Sousa Este programa � software livre; voc� pode redistribu�-lo e/ou
 * modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme publicada
 * pela Free Software Foundation; vers�o 2 da Licen�a. Este programa �
 * distribu�do na expectativa de ser �til, mas SEM QUALQUER GARANTIA; sem mesmo
 * a garantia impl�cita de COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO
 * EM PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais detalhes.
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU junto com este
 * programa; se n�o, escreva para Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307, USA.
 */

package gsan.gui.cadastro;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.EmpresaCobrancaFaixa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Arthur Carvalho
 * @created 14 de maio de 2008
 */
public class ExibirInserirEmpresaAction extends GcomAction {

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

		HttpSession sessao = httpServletRequest.getSession(false);

		ActionForward retorno = actionMapping.findForward("inserirEmpresa");

		InserirEmpresaActionForm inserirEmpresaActionForm = (InserirEmpresaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();	
		
		List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa = new ArrayList();
		
		if (sessao.getAttribute("colecaoEmpresaCobrancaFaixa") != null
				&& !sessao.getAttribute("colecaoEmpresaCobrancaFaixa").equals("")){
			colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>) sessao.getAttribute("colecaoEmpresaCobrancaFaixa");
		}

		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {

			inserirEmpresaActionForm
					.setIndicadorEmpresaPrincipal(ConstantesSistema.INDICADOR_USO_DESATIVO);

			inserirEmpresaActionForm.setIndicadorEmpresaCobranca(""
					+ ConstantesSistema.INDICADOR_USO_DESATIVO);

			inserirEmpresaActionForm.setIndicadorAtualizaCadastro(""
					+ ConstantesSistema.INDICADOR_USO_DESATIVO);
			
			inserirEmpresaActionForm
					.setIndicadorLeitura(ConstantesSistema.SIM);
			
		}
		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirEmpresaActionForm.setDescricao("");

			if (inserirEmpresaActionForm.getDescricao() == null
					|| inserirEmpresaActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

				filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);

				colecaoPesquisa = fachada.pesquisar(filtroEmpresa,
						Empresa.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(

					"atencao.pesquisa.nenhum_registro_tabela", null, "Empresa");

				} else {

					sessao.setAttribute("colecaoEmpresa", colecaoPesquisa);
				}
				// Cole��o de Empresa
				filtroEmpresa = new FiltroEmpresa();

				filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);

				Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
						Empresa.class.getName());

				sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
			}
		}

		// Adicionar EmpresaCobrancaFaixa
		if (httpServletRequest.getParameter("adicionarFaixa") != null
				&& httpServletRequest.getParameter("adicionarFaixa").equals("sim")
				&& inserirEmpresaActionForm.getQuantidadeMinimaContas() != null
				&& !inserirEmpresaActionForm.getQuantidadeMinimaContas().equals("")
				&& inserirEmpresaActionForm.getPercentualDaFaixa() != null
				&& !inserirEmpresaActionForm.getPercentualDaFaixa().equals("")) {

			Integer quantidadeMinimaContas = new Integer(inserirEmpresaActionForm.getQuantidadeMinimaContas());
			BigDecimal percentualFaixa = Util.formatarMoedaRealparaBigDecimal(inserirEmpresaActionForm.getPercentualDaFaixa());
			
			if (colecaoEmpresaCobrancaFaixa != null && !colecaoEmpresaCobrancaFaixa.isEmpty()) {
				Iterator iterator = colecaoEmpresaCobrancaFaixa.iterator();
				
				while(iterator.hasNext()) {
					EmpresaCobrancaFaixa empresaCobrancaFaixa = (EmpresaCobrancaFaixa) iterator.next();
					
					if (empresaCobrancaFaixa.getNumeroMinimoContasFaixa().compareTo(quantidadeMinimaContas) >= 0) {
						throw new ActionServletException(
								"atencao.quantidade.maior.que.quantidade.anterior", null, "Quantidade M�nima de Contas");
					}
				}
			}
			
			EmpresaCobrancaFaixa empresaCobrancaFaixa = new EmpresaCobrancaFaixa();
			empresaCobrancaFaixa.setNumeroMinimoContasFaixa(quantidadeMinimaContas);
			empresaCobrancaFaixa.setPercentualFaixa(percentualFaixa);
			
			colecaoEmpresaCobrancaFaixa.add(empresaCobrancaFaixa);
			sessao.setAttribute("colecaoEmpresaCobrancaFaixa", colecaoEmpresaCobrancaFaixa);

			inserirEmpresaActionForm.setPercentualDaFaixaInformado("sim");
			inserirEmpresaActionForm.setQuantidadeMinimaContas("");
			inserirEmpresaActionForm.setPercentualDaFaixa("");
		}
		
		// Remover EmpresaCobrancaFaixa
		if (httpServletRequest.getParameter("removerEmpresaCobrancaFaixa") != null
				&& !httpServletRequest.getParameter("removerEmpresaCobrancaFaixa").equals("")) {
			
			Integer indice = new Integer(httpServletRequest.getParameter("removerEmpresaCobrancaFaixa"));
        	
        	if (colecaoEmpresaCobrancaFaixa != null
        			&& !colecaoEmpresaCobrancaFaixa.isEmpty()
        			&& colecaoEmpresaCobrancaFaixa.size() >= indice) {
        		colecaoEmpresaCobrancaFaixa.remove(indice-1);
				sessao.setAttribute("colecaoEmpresaCobrancaFaixa", colecaoEmpresaCobrancaFaixa);
				if (colecaoEmpresaCobrancaFaixa != null
						&& !colecaoEmpresaCobrancaFaixa.isEmpty()) {
					inserirEmpresaActionForm.setPercentualDaFaixaInformado("sim");
				} else {
					inserirEmpresaActionForm.setPercentualDaFaixaInformado("");
				}
        	}
        	
		}

		// Limpar Formul�rio
		if (httpServletRequest.getParameter("limparFaixa") != null
				&& httpServletRequest.getParameter("limparFaixa").equals("sim")) {

			sessao.removeAttribute("colecaoEmpresaCobrancaFaixa");
			inserirEmpresaActionForm.setPercentualDaFaixaInformado("");
			
		}
		
		return retorno;
	}
}
