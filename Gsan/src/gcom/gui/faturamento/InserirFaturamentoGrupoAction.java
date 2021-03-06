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
package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
public class InserirFaturamentoGrupoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de um grupo de faturamento
	 * 
	 * [UC0770] Inserir Grupo de Faturamento
	 * 
	 * 
	 * @author Vin�cius Medeiros
	 * @date 17/04/2008
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

		InserirFaturamentoGrupoActionForm inserirFaturamentoGrupoActionForm = (InserirFaturamentoGrupoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		
		String descricao = inserirFaturamentoGrupoActionForm.getDescricao();
		
		String anoMesReferencia = inserirFaturamentoGrupoActionForm.getAnoMesReferencia();
		
		String indicadorVencimentoMesFatura = inserirFaturamentoGrupoActionForm
				.getIndicadorVencimentoMesFatura();

		FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
		Collection colecaoPesquisa = null;

		String mes = anoMesReferencia.substring(0, 2);
		String ano = anoMesReferencia.substring(3, 7);

		anoMesReferencia = ano + "" + mes;
		faturamentoGrupo.setAnoMesReferencia(new Integer(anoMesReferencia));
		
		//C�digo do grupo de faturamento - referente ao ftgr_id na tabela
		if(!"".equals(inserirFaturamentoGrupoActionForm.getCodigo())){
			if(fachada.verificarExistenciaIdGrupoFaturamento(Integer.parseInt(inserirFaturamentoGrupoActionForm.getCodigo()))){
				throw new ActionServletException("atencao.id.existente.grupo.faturamento", null,
				"Faturamento Grupo");			
			}else{
				faturamentoGrupo.setId(Integer.parseInt(inserirFaturamentoGrupoActionForm.getCodigo()));	
			}
			
		}
		
		// Descri��o
		if (!"".equals(inserirFaturamentoGrupoActionForm.getDescricao())) {
			faturamentoGrupo.setDescricao(inserirFaturamentoGrupoActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descri��o");
		}
		
		//Descri��o Abreviada
		if (!"".equals(inserirFaturamentoGrupoActionForm
				.getDescricaoAbreviada())) {
			faturamentoGrupo
					.setDescricaoAbreviada(inserirFaturamentoGrupoActionForm
							.getDescricaoAbreviada());
		} else {
			throw new ActionServletException("atencao.required", null,
					"Descri��o Abreviada");
		}

		// Dia do Vencimento
		if (!"".equals(inserirFaturamentoGrupoActionForm
				.getDiaVencimento())) {
			faturamentoGrupo
					.setDiaVencimento(new Short(inserirFaturamentoGrupoActionForm
							.getDiaVencimento()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Dia do Vencimento");
		}
		
		
		// Indicador de Vencimento
        if (indicadorVencimentoMesFatura == null 
        		|| indicadorVencimentoMesFatura.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de Vencimento");
        }else{  
        	faturamentoGrupo.setIndicadorVencimentoMesFatura(new Short(indicadorVencimentoMesFatura));
        }
		
		//RM 3071 - Indicafor de Mensagem Quita��o
        faturamentoGrupo.setIndicadorMensagemQuitacao(new Short(inserirFaturamentoGrupoActionForm.getIndicadorMensagemQuitacao()));
        
		faturamentoGrupo.setUltimaAlteracao(new Date());

		Short iu = 1;
		faturamentoGrupo.setIndicadorUso(iu);

		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();

		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.DESCRICAO, faturamentoGrupo
						.getDescricao()));
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.DESCRICAO_ABREVIADA, faturamentoGrupo
						.getDescricaoAbreviada()));

		
		colecaoPesquisa = (Collection) fachada.pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(
					"atencao.faturamento_grupo_ja_cadastrada", null,
					faturamentoGrupo.getDescricao());
		} else {
			faturamentoGrupo.setDescricao(descricao);

			Integer idFaturamentoGrupo = (Integer) fachada
					.inserir(faturamentoGrupo);

			montarPaginaSucesso(httpServletRequest,
					"Grupo de Faturamento " + descricao
							+ " inserido com sucesso.",
					"Inserir outro Grupo de Faturamento",
					"exibirInserirFaturamentoGrupoAction.do?menu=sim",
					"exibirAtualizarFaturamentoGrupoAction.do?idRegistroAtualizacao="
							+ idFaturamentoGrupo,
					"Atualizar Grupo de Faturamento Inserido");

			sessao.removeAttribute("InserirFaturamentoGrupoActionForm");

			return retorno;
		}

	}
}