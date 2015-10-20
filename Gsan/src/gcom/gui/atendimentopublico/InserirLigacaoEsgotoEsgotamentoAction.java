/* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.gui.atendimentopublico;


import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirLigacaoEsgotoEsgotamentoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de uma ligacao de esgoto esgotamento
	 * 
	 * [UC0834] Inserir Liga��o de esgoto esgotamento
	 * 
	 * @author Arthur Carvalho
	 * @date 25/08/2008
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

		InserirLigacaoEsgotoEsgotamentoActionForm form = (InserirLigacaoEsgotoEsgotamentoActionForm) actionForm;

		
		String descricao = form.getDescricao();
		String faturamentoSituacaoTipo = form.getFaturamentoSituacaoTipo();
		String faturamentoSituacaoMotivo = form.getFaturamentoSituacaoMotivo();
		
		LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento = new LigacaoEsgotoEsgotamento();
		
		Collection colecaoPesquisa = null;
		
		
		
		FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = new FiltroLigacaoEsgotoEsgotamento();
		filtroLigacaoEsgotoEsgotamento.adicionarParametro(
				new ParametroSimples(FiltroLigacaoEsgotoEsgotamento.DESCRICAO,descricao));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroLigacaoEsgotoEsgotamento, LigacaoEsgotoEsgotamento.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		//Descri��o
		if (!"".equals(form.getDescricao())) {
			ligacaoEsgotoEsgotamento.setDescricao(form
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,"Descri��o");
		}
		
		//Quantidade Meses Situacao Especial
		if (!"".equals(form.getQuantidadeMesesSituacaoEspecial())) {
			ligacaoEsgotoEsgotamento.setQuantidadeMesesSituacaoEspecial(new Integer(form
					.getQuantidadeMesesSituacaoEspecial()));
		} else {
			throw new ActionServletException("atencao.required", null,"Quantidade de Meses para Situa��o Especial de Faturamento");
		}
		
		//Tipo Situa��o Especial Faturamento
		if(faturamentoSituacaoTipo != null && 
			!faturamentoSituacaoTipo.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			FaturamentoSituacaoTipo faturamentoTipo = new FaturamentoSituacaoTipo();
			faturamentoTipo.setId(new Integer(form
					.getFaturamentoSituacaoTipo()));
			
			ligacaoEsgotoEsgotamento.setFaturamentoSituacaoTipo(faturamentoTipo);
			
		}
		
		//Motivo Situacao Especial Faturamento
		if(faturamentoSituacaoMotivo != null && 
			!faturamentoSituacaoMotivo.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			FaturamentoSituacaoMotivo faturamentoMotivo = new FaturamentoSituacaoMotivo();
			faturamentoMotivo.setId(new Integer(form
					.getFaturamentoSituacaoMotivo()));
			
			ligacaoEsgotoEsgotamento.setFaturamentoSituacaoMotivo(faturamentoMotivo);

		}

		ligacaoEsgotoEsgotamento.setDescricao(descricao);
		ligacaoEsgotoEsgotamento.setUltimaAlteracao(new Date());
		ligacaoEsgotoEsgotamento.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		Integer idLigacaoEsgotoEsgotamento = (Integer) this.getFachada().inserir(ligacaoEsgotoEsgotamento);

		montarPaginaSucesso(httpServletRequest,
				"Liga��o de Esgoto Esgotamento " + descricao
						+ " inserido com sucesso.",
				"Inserir outra Liga��o de Esgoto Esgotamento",
				"exibirInserirLigacaoEsgotoEsgotamentoAction.do?menu=sim",
				"exibirAtualizarLigacaoEsgotoEsgotamentoAction.do?idRegistroAtualizacao="
						+ idLigacaoEsgotoEsgotamento,
				"Atualizar Liga��o de Esgoto Esgotamento Inserida");

		return retorno;
		
		
	}
}		
