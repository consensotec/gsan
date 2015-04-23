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
package gsan.gui.faturamento.conta;

import gsan.fachada.Fachada;
import gsan.faturamento.conta.ContaComunicadoHelper;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.ErroRepositorioException;
import gsan.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC ] Filtrar Conta Comunicado
* 
* @author Flávio Leonardo C Cordeiro
* @date 09/02/2015
* 
* @throws ErroRepositorioException 
*/
public class FiltrarContaComunicadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarContaComunicadoActionForm form = (FiltrarContaComunicadoActionForm) actionForm;
		
		ActionForward retorno = actionMapping.findForward("exibirManterContaComunicado");
		
		boolean peloMenosUmParametroInformado = false;

		Fachada fachada = Fachada.getInstancia();
		
		ContaComunicadoHelper helper = new ContaComunicadoHelper();
		
		helper.setAbrangencia(form.getAbrangencia());
		
		if(form.getReferenciaInicial()!= null && !form.getReferenciaInicial().equals("")){
			if(form.getReferenciaFinal() != null && !form.getReferenciaFinal().equals("")){
				if((new Integer(Util.formatarMesAnoParaAnoMesSemBarra(form.getReferenciaFinal()))) < (new Integer(Util.formatarMesAnoParaAnoMesSemBarra(form.getReferenciaInicial())))){
					throw new ActionServletException("atencao.ano_mes_inicio_menor_que_final");
				}
				helper.setReferenciaInicial(Util.formatarMesAnoParaAnoMesSemBarra(form.getReferenciaInicial()));
				helper.setReferenciaFinal(Util.formatarMesAnoParaAnoMesSemBarra(form.getReferenciaFinal()));
				peloMenosUmParametroInformado = true;
			}
		}
		
		if(form.getTitulo()!= null && !form.getTitulo().equals("")){
			helper.setTitulo(form.getTitulo());
			peloMenosUmParametroInformado = true;
		}
		helper.setTipoPesquisaTitulo(form.getTipoPesquisaTitulo());
		
		if((form.getGrupoFaturamento() != null && (form.getGrupoFaturamento().length > 1 || (form.getGrupoFaturamento().length == 1 && !form.getGrupoFaturamento()[0].equals(""))))){
			helper.setGrupoFaturamento(form.getGrupoFaturamento());
			peloMenosUmParametroInformado = true;
		}
		
		if(form.getGerenciaRegional() != null && form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			helper.setGerenciaRegional(form.getGerenciaRegional());
			peloMenosUmParametroInformado = true;
		}
		
		if(form.getLocalidade() != null && !form.getLocalidade().equals("")){
			helper.setLocalidade(form.getLocalidade());
			peloMenosUmParametroInformado = true;
		}
		
		if((form.getSetorComercial() != null && (form.getSetorComercial().length > 1 || (form.getSetorComercial().length == 1 && !form.getSetorComercial()[0].equals(""))))){
			helper.setSetorComercial(form.getSetorComercial());
			peloMenosUmParametroInformado = true;
		}
		
		if((form.getRota() != null && (form.getRota().length > 1 || (form.getRota().length == 1 && !form.getRota()[0].equals(""))))){
			helper.setRota(form.getRota());
			peloMenosUmParametroInformado = true;
		}
		
		if((form.getQuadra()!= null && (form.getQuadra().length > 1 || (form.getQuadra().length == 1 && !form.getQuadra()[0].equals(""))))){
			helper.setQuadra(form.getQuadra());
			peloMenosUmParametroInformado = true;
		}
		
		if(form.getIcUso() != null && !form.getIcUso().equals("")){
			helper.setIcUso(form.getIcUso());
			peloMenosUmParametroInformado = true;
		}
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		Collection<ContaComunicadoHelper> colecaoPesquisa = fachada.pesquisarContaComando(helper);
		
		if(colecaoPesquisa != null && !colecaoPesquisa.isEmpty()){
			
			sessao.setAttribute("colecaoComunicadoHelper", colecaoPesquisa);
			
		}else{
			throw new ActionServletException("atencao.colecao_vazia");
		}
		
		return retorno;
	}
	
	
	
}