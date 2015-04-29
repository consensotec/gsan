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

import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoSituacaoMotivo;
import gsan.faturamento.FaturamentoSituacaoTipo;
import gsan.faturamento.FiltroFaturamentoSituacaoMotivo;
import gsan.faturamento.FiltroFaturamentoSituacaoTipo;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInformarResumoSituacaoEspecialFaturamentoAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInformarResumoSituacaoEspecialFaturamentoAction");

		Fachada fachada = Fachada.getInstancia();
		
		InformarResumoSituacaoEspecialFaturamentoActionForm form = (InformarResumoSituacaoEspecialFaturamentoActionForm) actionForm;

		form.setTipoRelatorio(ConstantesSistema.SINTETICO.toString());
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME);
		Collection colecaoGerenciaRegional = fachada.pesquisar(
				filtroGerenciaRegional, GerenciaRegional.class
				.getName()); 
		
		httpServletRequest.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio(FiltroUnidadeNegocio.NOME);
		Collection colecaoUnidadeNegocio = fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class
				.getName()); 
		
		httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		
		FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo(FiltroFaturamentoSituacaoTipo.DESCRICAO);
		Collection colecaoFatSitTipo = fachada.pesquisar(
				filtroFaturamentoSituacaoTipo, FaturamentoSituacaoTipo.class
						.getName());

		httpServletRequest.setAttribute("colecaoFatSitTipo", colecaoFatSitTipo);

		FiltroFaturamentoSituacaoMotivo filtroFaturamentoSituacaoMotivo = new FiltroFaturamentoSituacaoMotivo(FiltroFaturamentoSituacaoMotivo.DESCRICAO);
		Collection colecaoFatSitMotivo = fachada.pesquisar(
				filtroFaturamentoSituacaoMotivo,
				FaturamentoSituacaoMotivo.class.getName());

		httpServletRequest.setAttribute("colecaoFatSitMotivo", colecaoFatSitMotivo);

		pesquisarLocalidadeInicial(httpServletRequest, form);
		pesquisarLocalidadeFinal(httpServletRequest, form);
		pesquisarSetorComercialInicial(httpServletRequest, form);
		pesquisarSetorComercialFinal(httpServletRequest, form);
		
		return retorno;

	}

	private void pesquisarLocalidadeInicial(HttpServletRequest httpServletRequest, InformarResumoSituacaoEspecialFaturamentoActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		
		if (idLocalidadeInicial != null && !idLocalidadeInicial.trim().equals("")) {
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(new Integer (idLocalidadeInicial));
			
			if (localidade != null) {
				form.setNomeLocalidadeInicial(localidade.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialInicial");
			} else {
				form.setIdLocalidadeInicial("");
				form.setNomeLocalidadeInicial("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "idLocalidadeInicial");
				httpServletRequest.setAttribute("localidadeInicialInexistente",	true);
			}
			
		} else {
			form.setNomeLocalidadeInicial("");
		}
	}
	
	private void pesquisarLocalidadeFinal(HttpServletRequest httpServletRequest, InformarResumoSituacaoEspecialFaturamentoActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		
		if (idLocalidadeFinal != null && !idLocalidadeFinal.trim().equals("")) {
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(new Integer (idLocalidadeFinal));
			
			if (localidade != null) {
				form.setNomeLocalidadeFinal(localidade.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialFinal");
			} else {
				form.setIdLocalidadeFinal("");
				form.setNomeLocalidadeFinal("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "idLocalidadeFinal");
				httpServletRequest.setAttribute("localidadeFinalInexistente", true);
			}
			
		} else {
			form.setNomeLocalidadeFinal("");
		}
	}
	
	private void pesquisarSetorComercialInicial(HttpServletRequest httpServletRequest, InformarResumoSituacaoEspecialFaturamentoActionForm form) {
		
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		String codigoSetorComercialInicial = form.getCodigoSetorComercialInicial();
		
		if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.trim().equals("")) {
			SetorComercial setorComercial = pesquisarSetorComercial(idLocalidadeInicial, codigoSetorComercialInicial);
			
			if (setorComercial != null) {
				form.setDescricaoSetorComercialInicial(setorComercial.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "codigoRotaInicial");
			} else {
				form.setCodigoSetorComercialInicial("");
				form.setDescricaoSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialInicial");
				httpServletRequest.setAttribute("setorComercialInicialInexistente", true);
			}
		} else {
			form.setDescricaoSetorComercialInicial("");
		}
	}
	
	private void pesquisarSetorComercialFinal(HttpServletRequest httpServletRequest, InformarResumoSituacaoEspecialFaturamentoActionForm form) {
		
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		String codigoSetorComercialFinal = form.getCodigoSetorComercialFinal();
		
		if (codigoSetorComercialFinal != null && !codigoSetorComercialFinal.trim().equals("")) {
			SetorComercial setorComercial = pesquisarSetorComercial(idLocalidadeFinal, codigoSetorComercialFinal);
			
			if (setorComercial != null) {
				form.setDescricaoSetorComercialFinal(setorComercial.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "codigoRotaInicial");
			} else {
				form.setCodigoSetorComercialFinal("");
				form.setDescricaoSetorComercialFinal("SETOR COMERCIAL INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialInicial");
				httpServletRequest.setAttribute("setorComercialFinalInexistente", true);
			}
		} else {
			form.setDescricaoSetorComercialFinal("");
		}
	}
	
	private SetorComercial pesquisarSetorComercial(String idLocalidade, String codigoSetorComercial) {
		
		SetorComercial retorno = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
		
		Collection colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
			retorno = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
		}
		
		return retorno;
		
	}

}
