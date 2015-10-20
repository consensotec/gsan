/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.gui.relatorio.arrecadacao;

import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.FiltroEntidadeBeneficente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pr�-processamento da primeira p�gina de [UC0978] Gerar Relat�rio de Pagamento para
 * Entidades Beneficentes
 * 
 * @author Daniel Alves
 * @created 13/01/2010
 */
public class ExibirGerarRelatorioPagamentoEntidadesBeneficentesAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioPagamentoEntidadesBeneficentes");

		
		GerarRelatorioPagamentoEntidadesBeneficentesActionForm form = (GerarRelatorioPagamentoEntidadesBeneficentesActionForm) actionForm;
		form.setTipo("sintetico");
		
		Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);
        
		FiltroEntidadeBeneficente filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();
		filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("cliente");
		
		Collection colecaoEntidadesBeneficentes = 
			fachada.pesquisar(filtroEntidadeBeneficente, EntidadeBeneficente.class.getName());
		
		httpServletRequest.setAttribute("colecaoEntidadesBeneficentes", colecaoEntidadesBeneficentes);		

		String mesAnoInicial = httpServletRequest.getParameter("mesAnoInicial");
		String mesAnoFinal = httpServletRequest.getParameter("mesAnoFinal");        
        
		//Verifica se a Data Final � maior que a Inicial
        if ( mesAnoFinal != null && !mesAnoFinal.equals("")
        		&& mesAnoInicial != null && !mesAnoInicial.equals("")){
        	
        	Date dtInicial = Util.converteStringParaDate(mesAnoInicial);
        	Date dtFinal = Util.converteStringParaDate(mesAnoFinal);
        	
        	if (Util.compararData(dtFinal, dtInicial)== -1){
        		
        		throw new ActionServletException("atencao.data.intervalo.invalido", null ,"Data Invalida" );
        		
        	}        	
        }			

		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		Collection<GerenciaRegional> colecaoGerenciaRegional = fachada.pesquisar(
				filtroGerenciaRegional, GerenciaRegional.class.getName());

		sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);


		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.setCampoOrderBy(FiltroLocalidade.DESCRICAO);
		Collection<Localidade> colecaoLocalidade = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());

		httpServletRequest.setAttribute("colecaoLocalidade", colecaoLocalidade);		

		return retorno;
	}
}