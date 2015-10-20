/**
 * 
 */
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
package gcom.gui.seguranca.acesso;


import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.FiltroAlteracaoTipo;
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
 * Descri��o da classe
 * 
 * @author Vin�cius Medeiros
 * @date 17/04/2008
 */
public class InserirAlteracaoTipoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de um Tipo de Alteracao
	 * 
	 * Inserir Tipo de Alteracao
	 * 
	 * 
	 * @author Vin�cius Medeiros
	 * @date 14/05/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirAlteracaoTipoActionForm inserirAlteracaoTipoActionForm = (InserirAlteracaoTipoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirAlteracaoTipoActionForm.getDescricao();
		String descricaoAbreviada = inserirAlteracaoTipoActionForm
				.getDescricaoAbreviada();
		
		AlteracaoTipo alteracaoTipo = new AlteracaoTipo();
		Collection colecaoPesquisa = null;

		// Descri��o
		if (!"".equals(inserirAlteracaoTipoActionForm.getDescricao())) {
			alteracaoTipo.setDescricao(inserirAlteracaoTipoActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descri��o");
		}
		
		// Descri��o Abreviada
		if (!"".equals(inserirAlteracaoTipoActionForm.getDescricaoAbreviada())) {
			alteracaoTipo.setDescricaoAbreviada(inserirAlteracaoTipoActionForm
					.getDescricaoAbreviada());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descri��o abreviada");
		}
		
		alteracaoTipo.setUltimaAlteracao(new Date());

		Short iu = 1;
		alteracaoTipo.setIndicadorUso(iu);

		FiltroAlteracaoTipo filtroAlteracaoTipo = new FiltroAlteracaoTipo();

		filtroAlteracaoTipo.adicionarParametro(new ParametroSimples(
				FiltroAlteracaoTipo.DESCRICAO, alteracaoTipo.getDescricao()));
		filtroAlteracaoTipo.adicionarParametro(new ParametroSimples(
				FiltroAlteracaoTipo.DESCRICAOABREVIADA, alteracaoTipo.getDescricaoAbreviada()));
		
		colecaoPesquisa = (Collection) fachada.pesquisar(
				filtroAlteracaoTipo, AlteracaoTipo.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(
					"atencao.tipo_alteracao_ja_cadastrada", null,
					alteracaoTipo.getDescricao());
		} else {
			alteracaoTipo.setDescricao(descricao);
			alteracaoTipo.setDescricaoAbreviada(descricaoAbreviada);

			Integer idAlteracaoTipo = (Integer) fachada
					.inserir(alteracaoTipo);

			montarPaginaSucesso(httpServletRequest,
					"Tipo de Altera��o " + descricao
							+ " inserido com sucesso.",
					"Inserir outro tipo de altera��o",
					"exibirInserirAlteracaoTipoAction.do?menu=sim",
					"exibirAtualizarAlteracaoTipoAction.do?idRegistroAtualizacao="
							+ idAlteracaoTipo,
					"Atualizar Tipo de Altera��o Inserida");

			sessao.removeAttribute("InserirAlteracaoTipoActionForm");

			return retorno;
		}

	}
}