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
package gsan.gui.cobranca.spcserasa;

import gsan.cobranca.Negativador;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.parametrosistema.FiltroParametroSistema;
import gsan.seguranca.parametrosistema.ParametroSistema;
import gsan.spcserasa.FiltroNegativador;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0651] Inserir Comando de Negativação
 * Esta caso de uso permite a inclusão de um novo comando de negativação.
 * 
 * @author Ana Maria
 * @date 06/11/2007
 */
public class ExibirInserirComandoNegativacaoTipoComandoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("exibirInserirComandoNegativacaoTipoComando");
		InserirComandoNegativacaoActionForm form = (InserirComandoNegativacaoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = request.getSession(false);

		if (form.getTipo() == null) {
			// Tipo do Comando - exibir com a opção "Por Critério" selecionada
			form.setTipo("1");
		}

		//Pesquisar Negativador
		Collection colecaoNegativador = (Collection) sessao.getAttribute("colecaoNegativador");
		if (colecaoNegativador == null) {
			FiltroNegativador filtro = new FiltroNegativador();
			filtro.adicionarParametro(new ParametroSimples(FiltroNegativador.INDICADOR_USO, ConstantesSistema.SIM));
			filtro.setCampoOrderBy(FiltroNegativador.CLIENTE);
			filtro.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtro.setConsultaSemLimites(true);

			colecaoNegativador = fachada.pesquisar(filtro, Negativador.class.getName());

			if (colecaoNegativador == null || colecaoNegativador.isEmpty()) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "NEGATIVADOR");
			}

			sessao.setAttribute("colecaoNegativador", colecaoNegativador);
		}

		FiltroParametroSistema filtroParametroSistema = new FiltroParametroSistema();
		filtroParametroSistema.adicionarParametro(new ParametroSimples(FiltroParametroSistema.CODIGO_CONSTANTE, ParametroSistema.INDICADOR_NEGATIVACAO_POR_GUIA));
		Collection<?> colecaoParametroSistema = fachada.pesquisar(filtroParametroSistema, ParametroSistema.class.getName());

		if (!colecaoParametroSistema.isEmpty()) {
			ParametroSistema indicadorNegativacaoGuia = (ParametroSistema) Util.retonarObjetoDeColecao(colecaoParametroSistema);
			request.setAttribute("ICNEGATIVACAOPORGUIA", indicadorNegativacaoGuia.getValorParametro());
		} else {
			request.setAttribute("ICNEGATIVACAOPORGUIA", ConstantesSistema.NAO);
		}


		return retorno;
	}

}
