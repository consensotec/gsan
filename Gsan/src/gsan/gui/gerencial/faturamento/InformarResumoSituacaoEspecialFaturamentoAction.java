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
package gsan.gui.gerencial.faturamento;

import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaFinalHelper;
import gsan.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper;
import gsan.gerencial.faturamento.bean.ConsultarResumoSituacaoEspecialHelper;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Iterator;

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
public class InformarResumoSituacaoEspecialFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirConsultarResumoSituacaoEspecialFaturamento");

		InformarResumoSituacaoEspecialFaturamentoActionForm informarResumoSituacaoEspecialFaturamentoActionForm = (InformarResumoSituacaoEspecialFaturamentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		if (sessao.getAttribute("totalGeral") != null) {
			sessao.removeAttribute("totalGeral");
		}

		if (sessao.getAttribute("totalQtLigacoesGeral") != null) {
			sessao.removeAttribute("totalQtLigacoesGeral");
		}

		if (sessao.getAttribute("totalPercentualGeral") != null) {
			sessao.removeAttribute("totalPercentualGeral");
		}

		if (sessao.getAttribute("totalFatEstimadoGeral") != null) {
			sessao.removeAttribute("totalFatEstimadoGeral");
		}
		
		if (sessao.getAttribute("relatorioAnalitico") != null) {
			sessao.removeAttribute("relatorioAnalitico");
		}

/*		FiltroResumoFaturamentoSituacaoEspecial filtroResumoFaturamentoSituacaoEspecial = new FiltroResumoFaturamentoSituacaoEspecial();

		// recupera os ids de situacao tipo e ja gera o filtro.
		int i = 0;
		if (idsSituacaoTipo != null) {
			while (i < idsSituacaoTipo.length) {
				if (!idsSituacaoTipo[i].equals("")) {
					if (i + 1 < idsSituacaoTipo.length) {
						filtroResumoFaturamentoSituacaoEspecial
								.adicionarParametro(new ParametroSimples(
										FiltroResumoFaturamentoSituacaoEspecial.FATURAMENTO_SITUCACAO_TIPO_ID,
										idsSituacaoTipo[i],
										ConectorOr.CONECTOR_OR));
					} else {
						filtroResumoFaturamentoSituacaoEspecial
								.adicionarParametro(new ParametroSimples(
										FiltroResumoFaturamentoSituacaoEspecial.FATURAMENTO_SITUCACAO_TIPO_ID,
										idsSituacaoTipo[i]));
					}
				}
				i++;
			}
		}
		// recupera os ids de situacao motivo e ja gera o filtro.
		if (idsSituacaoMotivo != null) {
			while (i < idsSituacaoMotivo.length) {
				if (!idsSituacaoMotivo[i].equals("")) {
					if (i + 1 < idsSituacaoMotivo.length) {
						filtroResumoFaturamentoSituacaoEspecial
								.adicionarParametro(new ParametroSimples(
										FiltroResumoFaturamentoSituacaoEspecial.FATURAMENTO_SITUCACAO_MOTIVO_ID,
										idsSituacaoMotivo[i],
										ConectorOr.CONECTOR_OR));
					} else {
						filtroResumoFaturamentoSituacaoEspecial
								.adicionarParametro(new ParametroSimples(
										FiltroResumoFaturamentoSituacaoEspecial.FATURAMENTO_SITUCACAO_MOTIVO_ID,
										idsSituacaoMotivo[i]));
					}
				}
				i++;
			}
		}

		filtroResumoFaturamentoSituacaoEspecial
				.setCampoOrderBy(FiltroResumoFaturamentoSituacaoEspecial.GERENCIA_REGIONAL_ID);
		filtroResumoFaturamentoSituacaoEspecial
				.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroResumoFaturamentoSituacaoEspecial
				.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoTipo");
		filtroResumoFaturamentoSituacaoEspecial
				.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoMotivo");*/
		
		this.validarCampos(informarResumoSituacaoEspecialFaturamentoActionForm);
		ConsultarResumoSituacaoEspecialHelper helper = this.montarHelper(informarResumoSituacaoEspecialFaturamentoActionForm);
		
		Collection<ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper> colecaoRFSE = fachada
				.pesquisarResumoFatSitEspecial(helper);

		if (colecaoRFSE == null || colecaoRFSE.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		
		BigDecimal totalGeral = new BigDecimal("0");
		Integer totalQtLigacoesGeral = new Integer("0");
		BigDecimal totalFatEstimadoGeral = new BigDecimal("0.00");
		for (Iterator iter = colecaoRFSE.iterator(); iter.hasNext();) {
			ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper = (ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper) iter.next();

			totalGeral = totalGeral.add(new BigDecimal(
							resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper
							.getTotalGerenciaRegional().toString()));
			
			totalQtLigacoesGeral = totalQtLigacoesGeral
					+ resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper
					.getTotalQtLigacoesGerencia();
			
			totalFatEstimadoGeral = totalFatEstimadoGeral.add(new BigDecimal(
							resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper
							.getTotalFatEstimadoGerencia().toString()));
		}


/*		for (Iterator iter = colecaoRFSE.iterator(); iter.hasNext();) {
			ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper = (ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper) iter
					.next();


		}*/

		BigDecimal totalPercentualGeral = new BigDecimal("0.00");
		
		
		BigDecimal p = new BigDecimal("100");
		if (totalGeral != null && totalQtLigacoesGeral != null) {			
			if (totalQtLigacoesGeral.compareTo(0) == 1){
				totalPercentualGeral= (totalGeral.multiply(p));
				totalPercentualGeral = totalPercentualGeral.divide(new BigDecimal(totalQtLigacoesGeral), 2, RoundingMode.HALF_UP);
			}
		}


/*		for (Iterator iter = colecaoRFSE.iterator(); iter.hasNext();) {
			ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper = (ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper) iter
					.next();


		}*/

		sessao.setAttribute("totalGeral", totalGeral);
		sessao.setAttribute("totalQtLigacoesGeral", totalQtLigacoesGeral);
		sessao.setAttribute("totalPercentualGeral", totalPercentualGeral);
		sessao.setAttribute("totalFatEstimadoGeral", totalFatEstimadoGeral);

		sessao.setAttribute("colecaoRFSE", colecaoRFSE);
		
		ResumoFaturamentoSituacaoEspecialConsultaFinalHelper resumoFaturamentoSituacaoEspecialConsultaFinalHelper = new ResumoFaturamentoSituacaoEspecialConsultaFinalHelper(colecaoRFSE, new Integer(totalGeral.intValue()), totalPercentualGeral, totalFatEstimadoGeral, totalQtLigacoesGeral);
		sessao.setAttribute("resumoFaturamentoSituacaoEspecialConsultaFinalHelper", resumoFaturamentoSituacaoEspecialConsultaFinalHelper);

		if(informarResumoSituacaoEspecialFaturamentoActionForm.getTipoRelatorio().equals(ConstantesSistema.ANALITICO.toString())){
			sessao.setAttribute("relatorioAnalitico", "true");
		}
		
		
		return retorno;
	}
	
	private void validarCampos(InformarResumoSituacaoEspecialFaturamentoActionForm form) {
		
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		
		verificarLocalidade(idLocalidadeInicial, "Inicial");
		verificarLocalidade(idLocalidadeFinal, "Final");
		
		String codigoSetorComericialInicial = form.getCodigoSetorComercialInicial();
		String codigoSetorComericialFinal = form.getCodigoSetorComercialFinal();
		
		verificarSetorComercial(idLocalidadeInicial, codigoSetorComericialInicial, "Inicial");
		verificarSetorComercial(idLocalidadeFinal, codigoSetorComericialFinal, "Final");
		
		if ( ( codigoSetorComericialInicial != null && !codigoSetorComericialInicial.trim().equals("") ) ||
			 ( codigoSetorComericialFinal != null && !codigoSetorComericialFinal.trim().equals("") ) ) {
			
			if (!idLocalidadeInicial.equals(idLocalidadeFinal)) {
				throw new ActionServletException("atencao.localidade_diferente");
			}
			
		}
		
		String codigoRotaInicial = form.getCodigoRotaInicial();
		String codigoRotaFinal = form.getCodigoRotaFinal();
		
		if ( ( codigoRotaInicial != null && !codigoRotaInicial.trim().equals("") ) ||
				 ( codigoRotaFinal != null && !codigoRotaFinal.trim().equals("") ) ) {
			
			if (codigoSetorComericialInicial == null || codigoSetorComericialInicial.trim().equals("")) {
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Setor Comercial");
			}
			
			if (!codigoSetorComericialInicial.equals(codigoSetorComericialFinal)) {
				throw new ActionServletException("atencao.setor_diferente");
			}
				
		}
		
		

	}
	
	private void verificarLocalidade(String idLocalidade, String tipo) {
		Fachada fachada = Fachada.getInstancia();
		
		if (idLocalidade != null && !idLocalidade.trim().equals("")) {
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(new Integer(idLocalidade));
			
			if (localidade == null) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade " + tipo);
			}
			
		}
	}
	
	private void verificarSetorComercial(String idLocalidade, String codigoSetorComercial, String tipo) {
		Fachada fachada = Fachada.getInstancia();
		
		
		if (codigoSetorComercial != null && !codigoSetorComercial.trim().equals("")) {
			
			if (idLocalidade == null || idLocalidade.trim().equals("")) {
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Localidade");
			}
			
			FiltroSetorComercial filtro = new FiltroSetorComercial();
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
			
			Collection colecaoSetoresComercias = fachada.pesquisar(filtro, SetorComercial.class.getName());
			
			if (colecaoSetoresComercias == null || colecaoSetoresComercias.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial " + tipo);
			}
			
		}
	}

	private ConsultarResumoSituacaoEspecialHelper montarHelper(InformarResumoSituacaoEspecialFaturamentoActionForm form) {
		
		ConsultarResumoSituacaoEspecialHelper retorno = new ConsultarResumoSituacaoEspecialHelper();
		
		if (form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			retorno.setIdGerenciaRegional(form.getIdGerenciaRegional());
		}
		
		if (form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			retorno.setIdUnidadeNegocio(form.getIdUnidadeNegocio());
		}
		
		if (form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().trim().equals("")) {
			retorno.setIdLocalidadeInicial(form.getIdLocalidadeInicial());
		}
		
		if (form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().trim().equals("")) {
			retorno.setIdLocalidadeFinal(form.getIdLocalidadeFinal());
		}
		
		if (form.getCodigoSetorComercialInicial() != null && !form.getCodigoSetorComercialInicial().trim().equals("")) {
			retorno.setCodigoSetorComercialInicial(form.getCodigoSetorComercialInicial());
		}
		
		if (form.getCodigoSetorComercialFinal() != null && !form.getCodigoSetorComercialFinal().trim().equals("")) {
			retorno.setCodigoSetorComercialFinal(form.getCodigoSetorComercialFinal());
		}
		
		if (form.getCodigoRotaInicial() != null && !form.getCodigoRotaInicial().trim().equals("")) {
			retorno.setCodigoRotaInicial(form.getCodigoRotaInicial());
		}
		
		if (form.getCodigoRotaFinal() != null && !form.getCodigoRotaFinal().trim().equals("")) {
			retorno.setCodigoRotaFinal(form.getCodigoRotaFinal());
		}
		
		if (form.getSituacaoTipo() != null) {
			retorno.setSituacaoTipo(form.getSituacaoTipo());
		}
		
		if (form.getSituacaoMotivo() != null) {
			retorno.setSituacaoMotivo(form.getSituacaoMotivo());
		}
		
		if (form.getTipoRelatorio() != null) {
			retorno.setTipoRelatorio(new Short(form.getTipoRelatorio()));
		}
		
		return retorno;
	}
}