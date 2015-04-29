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
package gsan.gui.cadastro;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.EmpresaCobrancaFaixa;
import gsan.cadastro.empresa.EmpresaContratoCobranca;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.cadastro.empresa.FiltroEmpresaCobrancaFaixa;
import gsan.cadastro.empresa.FiltroEmpresaContratoCobranca;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

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
 * 
 * @author Arthur Carvalho
 * @date 14/05/2008
 */
public class ExibirAtualizarEmpresaAction extends GcomAction {

	/**
	 * M�todo responsavel por responder a requisicao
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

		ActionForward retorno = actionMapping.findForward("empresaAtualizar");

		AtualizarEmpresaActionForm atualizarEmpresaActionForm = (AtualizarEmpresaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = null;

		List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa = new ArrayList();
		List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixaRemover = new ArrayList();

		if (sessao.getAttribute("colecaoEmpresaCobrancaFaixa") != null
				&& !sessao.getAttribute("colecaoEmpresaCobrancaFaixa").equals("")){
			colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>) sessao.getAttribute("colecaoEmpresaCobrancaFaixa");
		} 
		if (sessao.getAttribute("colecaoEmpresaCobrancaFaixaRemover") != null
				&& !sessao.getAttribute("colecaoEmpresaCobrancaFaixaRemover").equals("")){
			colecaoEmpresaCobrancaFaixaRemover = (List<EmpresaCobrancaFaixa>) sessao.getAttribute("colecaoEmpresaCobrancaFaixaRemover");
		}
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
			
		} else if (sessao.getAttribute("empresa") != null
				&& !sessao.getAttribute("empresa").equals("")) {
			
			id = ((Empresa) sessao.getAttribute("empresa")).getId().toString();
			
		} else if (sessao.getAttribute("atualizarEmpresa") != null
				&& !sessao.getAttribute("atualizarEmpresa").equals("")){
			
			id = ((Empresa) sessao.getAttribute("atualizarEmpresa")).getId().toString();
		}

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		Empresa empresa = new Empresa();

		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0
				&& (httpServletRequest.getParameter("adicionarFaixa") == null
						|| !httpServletRequest.getParameter("adicionarFaixa").equalsIgnoreCase("sim"))
				&& (httpServletRequest.getParameter("removerEmpresaCobrancaFaixa") == null
						|| httpServletRequest.getParameter("removerEmpresaCobrancaFaixa").equals(""))
				&& (httpServletRequest.getParameter("limparFaixa") == null
						|| httpServletRequest.getParameter("limparFaixa").equals(""))) {
			
			sessao.removeAttribute("colecaoEmpresaCobrancaFaixaRemover");

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, id));
			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());
			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
				empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
			}

			if (id == null || id.trim().equals("")) {

				atualizarEmpresaActionForm.setId(empresa.getId().toString());

				atualizarEmpresaActionForm.setDescricao(empresa.getDescricao());

				atualizarEmpresaActionForm.setDescricaoAbreviada(empresa
						.getDescricaoAbreviada());

				atualizarEmpresaActionForm.setEmail(empresa.getEmail());

				atualizarEmpresaActionForm.setIndicadorEmpresaPrincipal(empresa
						.getIndicadorEmpresaPrincipal());

				atualizarEmpresaActionForm.setIndicadorUso(empresa
						.getIndicadorUso());

				atualizarEmpresaActionForm
				.setIndicadorAtualizaCadastro(empresa
						.getIndicadorAtualizaCadastro()
						.toString());

				atualizarEmpresaActionForm
				.setDataEncerramentoContratoCobranca(Util.formatarData(empresa
						.getDataEncerramentoContratoCobranca()));

				if (empresa.getQuantidadeMesValidoPagamento() != null
						&& !empresa.getQuantidadeMesValidoPagamento()
								.equals("")) {
					atualizarEmpresaActionForm
							.setQuantidadeMesValidoPagamento(""
									+ empresa.getQuantidadeMesValidoPagamento());
				} else {
					atualizarEmpresaActionForm
							.setQuantidadeMesValidoPagamento("");
				}
				
				
				atualizarEmpresaActionForm.setIndicadorLeitura( empresa.getIndicadorLeitura() );
				
				if (empresa.getIndicadorEmpresaContratadaCobranca()
								.intValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {

					FiltroEmpresaContratoCobranca filtroEmpresaCobranca = new FiltroEmpresaContratoCobranca();

					filtroEmpresaCobranca
							.adicionarParametro(new ParametroSimples(
									FiltroEmpresaContratoCobranca.EMPRESA_ID, empresa
											.getId().toString()));

					Collection colecaoEmpresaCobranca = fachada.pesquisar(
							filtroEmpresaCobranca, EmpresaContratoCobranca.class
									.getName());

					if (colecaoEmpresaCobranca != null
							&& !colecaoEmpresaCobranca.isEmpty()) {

						EmpresaContratoCobranca empresaCobranca = (EmpresaContratoCobranca) colecaoEmpresaCobranca
								.iterator().next();

						atualizarEmpresaActionForm
								.setDataInicioContratoCobranca(Util
										.formatarData(empresaCobranca
												.getDataInicioContrato()));

						atualizarEmpresaActionForm
								.setDataFimContratoCobranca(Util
										.formatarData(empresaCobranca
												.getDataFinalContrato()));

						atualizarEmpresaActionForm.setPercentualPagamento(Util
								.formatarMoedaReal(empresaCobranca
										.getPercentualContratoCobranca()));
						
						FiltroEmpresaCobrancaFaixa filtro = new FiltroEmpresaCobrancaFaixa();
						filtro.adicionarParametro(new ParametroSimples(
								FiltroEmpresaCobrancaFaixa.EMPRESA_CONTRATO_COBRANCA_ID, empresaCobranca.getId()));
						filtro.setCampoOrderBy(FiltroEmpresaCobrancaFaixa.NUMERO_MAXIMO_CONTAS_FAIXA);
						
						colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>)fachada.pesquisar(
								filtro, EmpresaCobrancaFaixa.class.getName());

						if (colecaoEmpresaCobrancaFaixa != null
								&& !colecaoEmpresaCobrancaFaixa.isEmpty()) {
							atualizarEmpresaActionForm.setPercentualDaFaixaInformado("sim");
							sessao.setAttribute("colecaoEmpresaCobrancaFaixa", colecaoEmpresaCobrancaFaixa);
						} else {
							atualizarEmpresaActionForm.setPercentualDaFaixaInformado("");
							sessao.removeAttribute("colecaoEmpresaCobrancaFaixa");
						}

					}

				}

			}

			atualizarEmpresaActionForm.setId(empresa.getId().toString());

			atualizarEmpresaActionForm.setDescricao(empresa.getDescricao());

			atualizarEmpresaActionForm.setDescricaoAbreviada(empresa
					.getDescricaoAbreviada());

			atualizarEmpresaActionForm.setEmail(empresa.getEmail());

			atualizarEmpresaActionForm.setIndicadorEmpresaPrincipal(empresa
					.getIndicadorEmpresaPrincipal());

			atualizarEmpresaActionForm.setIndicadorAtualizaCadastro(empresa
					.getIndicadorAtualizaCadastro().toString());
			
			atualizarEmpresaActionForm.setIndicadorLeitura( empresa.getIndicadorLeitura() );
			

			atualizarEmpresaActionForm
			.setDataEncerramentoContratoCobranca(Util.formatarData(empresa
					.getDataEncerramentoContratoCobranca()));

			
			if (empresa.getQuantidadeMesValidoPagamento() != null
					&& !empresa.getQuantidadeMesValidoPagamento()
							.equals("")) {
				atualizarEmpresaActionForm
						.setQuantidadeMesValidoPagamento(""
								+ empresa.getQuantidadeMesValidoPagamento());
			} else {
				atualizarEmpresaActionForm
						.setQuantidadeMesValidoPagamento("");
			}
			
			atualizarEmpresaActionForm.setIndicadorEmpresaCobranca(empresa
					.getIndicadorEmpresaContratadaCobranca().toString());
			if (empresa.getIndicadorEmpresaContratadaCobranca()
							.intValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {

				FiltroEmpresaContratoCobranca filtroEmpresaCobranca = new FiltroEmpresaContratoCobranca();

				filtroEmpresaCobranca.adicionarParametro(new ParametroSimples(
						FiltroEmpresaContratoCobranca.EMPRESA_ID, empresa.getId()
								.toString()));

				Collection colecaoEmpresaCobranca = fachada.pesquisar(
						filtroEmpresaCobranca, EmpresaContratoCobranca.class.getName());

				if (colecaoEmpresaCobranca != null
						&& !colecaoEmpresaCobranca.isEmpty()) {

					EmpresaContratoCobranca empresaCobranca = (EmpresaContratoCobranca) colecaoEmpresaCobranca
							.iterator().next();

					atualizarEmpresaActionForm
							.setDataInicioContratoCobranca(Util
									.formatarData(empresaCobranca
											.getDataInicioContrato()));

					atualizarEmpresaActionForm.setDataFimContratoCobranca(Util
							.formatarData(empresaCobranca
									.getDataFinalContrato()));

					atualizarEmpresaActionForm.setPercentualPagamento(Util
							.formatarMoedaReal(empresaCobranca
									.getPercentualContratoCobranca()));
					
					FiltroEmpresaCobrancaFaixa filtro = new FiltroEmpresaCobrancaFaixa();
					filtro.adicionarParametro(new ParametroSimples(
							FiltroEmpresaCobrancaFaixa.EMPRESA_CONTRATO_COBRANCA_ID, empresaCobranca.getId()));
					filtro.setCampoOrderBy(FiltroEmpresaCobrancaFaixa.NUMERO_MAXIMO_CONTAS_FAIXA);
					
					colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>)fachada.pesquisar(
							filtro, EmpresaCobrancaFaixa.class.getName());

					if (colecaoEmpresaCobrancaFaixa != null
							&& !colecaoEmpresaCobrancaFaixa.isEmpty()) {
						atualizarEmpresaActionForm.setPercentualDaFaixaInformado("sim");
						sessao.setAttribute("colecaoEmpresaCobrancaFaixa", colecaoEmpresaCobrancaFaixa);
					} else {
						atualizarEmpresaActionForm.setPercentualDaFaixaInformado("");
						sessao.removeAttribute("colecaoEmpresaCobrancaFaixa");
					}
				}
				
				Short numeroMaximoMeses = null;
				
				if (!atualizarEmpresaActionForm.getQuantidadeMesValidoPagamento()
						.equals("")) {
					 numeroMaximoMeses = new Short(
							atualizarEmpresaActionForm
									.getQuantidadeMesValidoPagamento());

					if (numeroMaximoMeses.intValue() == 0
							|| numeroMaximoMeses.intValue() > 99) {
						throw new ActionServletException(
								"atencao.numero.meses.fora.faixa.permitido");
					}
					empresa.setQuantidadeMesValidoPagamento(new Integer(numeroMaximoMeses));
					

				}
				

			}

			atualizarEmpresaActionForm.setIndicadorUso(empresa
					.getIndicadorUso());

			sessao.setAttribute("atualizarEmpresa", empresa);

			if (sessao.getAttribute("colecaoEmpresa") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarEmpresaAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarEmpresaAction.do");
			}

		}


		// Adicionar EmpresaCobrancaFaixa
		if (httpServletRequest.getParameter("adicionarFaixa") != null
				&& httpServletRequest.getParameter("adicionarFaixa").equals("sim")
				&& atualizarEmpresaActionForm.getQuantidadeMinimaContas() != null
				&& !atualizarEmpresaActionForm.getQuantidadeMinimaContas().equals("")
				&& atualizarEmpresaActionForm.getPercentualDaFaixa() != null
				&& !atualizarEmpresaActionForm.getPercentualDaFaixa().equals(""))
			
		{

			Integer quantidadeMinimaContas = new Integer(atualizarEmpresaActionForm.getQuantidadeMinimaContas());
			BigDecimal percentualFaixa = Util.formatarMoedaRealparaBigDecimal(atualizarEmpresaActionForm.getPercentualDaFaixa());
			
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
			atualizarEmpresaActionForm.setPercentualDaFaixaInformado("sim");

			atualizarEmpresaActionForm.setQuantidadeMinimaContas("");
			atualizarEmpresaActionForm.setPercentualDaFaixa("");
		}
		
		// Remover EmpresaCobrancaFaixa
		if (httpServletRequest.getParameter("removerEmpresaCobrancaFaixa") != null
				&& !httpServletRequest.getParameter("removerEmpresaCobrancaFaixa").equals("")) {
			
			Integer indice = new Integer(httpServletRequest.getParameter("removerEmpresaCobrancaFaixa"));
        	
        	if (colecaoEmpresaCobrancaFaixa != null
        			&& !colecaoEmpresaCobrancaFaixa.isEmpty()
        			&& colecaoEmpresaCobrancaFaixa.size() >= indice) {
        		EmpresaCobrancaFaixa empresaCobrancaFaixa = colecaoEmpresaCobrancaFaixa.get(indice - 1);
        		if (empresaCobrancaFaixa.getUltimaAlteracao() != null) {
        			colecaoEmpresaCobrancaFaixaRemover.add(empresaCobrancaFaixa);
    				sessao.setAttribute("colecaoEmpresaCobrancaFaixaRemover", colecaoEmpresaCobrancaFaixaRemover);
        		}
        		
        		colecaoEmpresaCobrancaFaixa.remove(indice-1);
        		if (colecaoEmpresaCobrancaFaixa != null
        				&& !colecaoEmpresaCobrancaFaixa.isEmpty()) {
        			sessao.setAttribute("colecaoEmpresaCobrancaFaixa", colecaoEmpresaCobrancaFaixa);
        			atualizarEmpresaActionForm.setPercentualDaFaixaInformado("sim");
        		} else {
        			sessao.removeAttribute("colecaoEmpresaCobrancaFaixa");
        			atualizarEmpresaActionForm.setPercentualDaFaixaInformado("");
        		}
        	}
        	
		}

		// Limpar Formul�rio
		if (httpServletRequest.getParameter("limparFaixa") != null
				&& httpServletRequest.getParameter("limparFaixa").equals("sim")) {
			
			sessao.removeAttribute("colecaoEmpresaCobrancaFaixa");
			atualizarEmpresaActionForm.setPercentualDaFaixaInformado("");
			
		}
		
		return retorno;
	}
}