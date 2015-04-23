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
package gsan.gui.atualizacaocadastral;


import gsan.atualizacaocadastral.bean.DadosImovelPreGsanHelper;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1447] - Atualizar Imóvel Ambiente PRÉ-GSAN - Tela principal
 * @author Bruno Sá Barreto
 * @since 29/09/2014
 */

public class AtualizarImovelPreGsanAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();

		ConsultarImoveisPreGsanActionForm form = (ConsultarImoveisPreGsanActionForm) actionForm;
		
		String imoveisModificados = form.getImoveisParaAtualizar();
		
		if(!Util.verificarNaoVazio(imoveisModificados)) {
			throw new ActionServletException("atencao.nao_existe_imovel_para_ser_atualizado");	
		}
		
		//O atributo "imoveisModificados" contém uma espécie de "HashSet<IdImovel, AcaoASerTomada>"
		//feita com strings com os imóveis que tiveram seus selects alterados na página de consulta.
		//as strings estão no seguinte formato: "id|ação;"
		StringTokenizer st1 = new StringTokenizer(imoveisModificados, ";"); 
		while(st1.hasMoreTokens()){
			StringTokenizer st2 = new StringTokenizer(st1.nextToken(), "|");
			String idImovelAtlzCadastral = st2.nextToken();
			String acaoSelecionada = st2.nextToken();
			String matriculaGsan = this.pesquisarMatriculaGsanAssociada(sessao, idImovelAtlzCadastral);
			fachada.atualizarImovelAtualizacaoCadastralPreGsan(idImovelAtlzCadastral, acaoSelecionada, matriculaGsan);
		}
		
		montarPaginaSucesso(httpServletRequest, "Atualizado com sucesso.",
				"Realizar outra Manutenção",
				"exibirConsultarImoveisPreGsanAction.do?menu=sim");
		
		return retorno;
		
	}
	
	@SuppressWarnings("unchecked")
	private String pesquisarMatriculaGsanAssociada(HttpSession sessao, String idImovelAtlzCadastral){
		String resultado = "";
		ArrayList<DadosImovelPreGsanHelper> imoveisAtlzCadastralHelpers = (ArrayList<DadosImovelPreGsanHelper>) sessao.getAttribute("imoveisNovosPreGsan");
		if(!Util.isVazioOrNulo(imoveisAtlzCadastralHelpers)){					
			for(DadosImovelPreGsanHelper itemAtual : imoveisAtlzCadastralHelpers){
				if(itemAtual.getIdImovelAtualizacaoCadastral().equals(idImovelAtlzCadastral)){
					resultado = String.valueOf(itemAtual.getMatriculaGsan());
					break;
				}
			}
		}
		return resultado;
	}
	
}
