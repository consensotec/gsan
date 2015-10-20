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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.fachada.Fachada;
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
 * @date 14/05/2008
 */
public class InserirLigacaoEsgotoSituacaoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de uma Situacao de Ligacao de Esgoto
	 * 
	 * [UC0788] Inserir Situacao de Ligacao de Esgoto
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

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirLigacaoEsgotoSituacaoActionForm inserirLigacaoEsgotoSituacaoActionForm = (InserirLigacaoEsgotoSituacaoActionForm) actionForm;

		// Mudar isso quando houver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirLigacaoEsgotoSituacaoActionForm.getDescricao();
		String descricaoAbreviado = inserirLigacaoEsgotoSituacaoActionForm.getDescricaoAbreviado();
		String volumeMinimoFaturamento = inserirLigacaoEsgotoSituacaoActionForm.getVolumeMinimoFaturamento();
		String indicadorExistenciaLigacao = inserirLigacaoEsgotoSituacaoActionForm.getIndicadorExistenciaLigacao();
		String indicadorExistenciaRede = inserirLigacaoEsgotoSituacaoActionForm.getIndicadorExistenciaRede();
		String indicadorFaturamentoSituacao = inserirLigacaoEsgotoSituacaoActionForm.getIndicadorFaturamentoSituacao();

		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
		Collection colecaoPesquisa = null;

		
		// Verifica se o campo Descri��o foi preenchido
		if (!"".equals(inserirLigacaoEsgotoSituacaoActionForm.getDescricao())) {
			
			ligacaoEsgotoSituacao.setDescricao(inserirLigacaoEsgotoSituacaoActionForm
					.getDescricao());
		
		} else {
		
			throw new ActionServletException("atencao.required", null,"Descri��o");
		}
		
		// Verifica se o campo Descri��o Abreviada foi preenchido
		if (!"".equals(inserirLigacaoEsgotoSituacaoActionForm.getDescricaoAbreviado())) {
			
			ligacaoEsgotoSituacao.setDescricaoAbreviado(inserirLigacaoEsgotoSituacaoActionForm
					.getDescricaoAbreviado());
			
		} else {
			
			throw new ActionServletException("atencao.required", null,
					"Descri��o Abreviada");
		}
		
		// Verifica se o campo Volume M�nimo da Situa��o de Liga��o foi preenchido
        if (volumeMinimoFaturamento == null || volumeMinimoFaturamento.equals("")){
        	
        	throw new ActionServletException("atencao.required",null,"Volume M�nimo da Situa��o de Liga��o");
        
        }else{  
        
        	ligacaoEsgotoSituacao.setVolumeMinimoFaturamento(new Integer(volumeMinimoFaturamento));
        
        }
		
		
		// Verifica se o campo Indicador de Exist�ncia de Liga��o foi preenchido
        if (indicadorExistenciaLigacao == null || indicadorExistenciaLigacao.equals("")){
        	
        	throw new ActionServletException("atencao.required",null,"Indicador de Exist�ncia de Liga��o");
        
        }else{  
        	
        	ligacaoEsgotoSituacao.setIndicadorExistenciaLigacao(new Short(indicadorExistenciaLigacao));
        
        }
        
		// Verifica se o campo Indicador de Exist�ncia de Rede foi preenchido
        
        if (indicadorExistenciaRede == null || indicadorExistenciaRede.equals("")){
        	
        	throw new ActionServletException("atencao.required",null,"Indicador de Exist�ncia de Rede");
        
        }else{  
        
        	ligacaoEsgotoSituacao.setIndicadorExistenciaRede(new Short(indicadorExistenciaRede));
        
        }
        
		// Verifica se o Indicador de Faturamento foi preenchido
        if (indicadorFaturamentoSituacao == null || indicadorFaturamentoSituacao.equals("")){
        	
        	throw new ActionServletException("atencao.required",null,"Indicador de Faturamento");
        
        }else{  
        
        	ligacaoEsgotoSituacao.setIndicadorFaturamentoSituacao(new Short(indicadorFaturamentoSituacao));
        
        }
		
		
		// Ultima altera��o
		ligacaoEsgotoSituacao.setUltimaAlteracao(new Date());
		
        // Indicador de uso
		Short iu = 1;
		ligacaoEsgotoSituacao.setIndicadorUso(iu);

		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

		filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgotoSituacao.DESCRICAO, ligacaoEsgotoSituacao.getDescricao()));

		colecaoPesquisa = (Collection) fachada.pesquisar(filtroLigacaoEsgotoSituacao,
				LigacaoEsgotoSituacao.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			
			// Caso j� tenha uma Situa��o de Liga��o de Esgoto cadastrada
			throw new ActionServletException(
					"atencao.ligacao_situacao_esgoto_ja_cadastrada", null, ligacaoEsgotoSituacao
							.getDescricao());
		
		} else {
			
			ligacaoEsgotoSituacao.setDescricao(descricao);
			ligacaoEsgotoSituacao.setDescricaoAbreviado(descricaoAbreviado);
			ligacaoEsgotoSituacao.setVolumeMinimoFaturamento(new Integer (volumeMinimoFaturamento));
			ligacaoEsgotoSituacao.setIndicadorExistenciaLigacao(new Short (indicadorExistenciaLigacao));
			ligacaoEsgotoSituacao.setIndicadorExistenciaRede(new Short (indicadorExistenciaRede));
			ligacaoEsgotoSituacao.setIndicadorFaturamentoSituacao(new Short (indicadorFaturamentoSituacao));
			
			Integer idSituacaoEsgotoLigacao = (Integer) fachada.inserir(ligacaoEsgotoSituacao);

			montarPaginaSucesso(httpServletRequest,
					"Situa��o de Liga��o de Esgoto " + descricao
							+ " inserida com sucesso.",
					"Inserir outra Situa��o de Liga��o de Esgoto",
					"exibirInserirLigacaoEsgotoSituacaoAction.do?menu=sim",
					"exibirAtualizarLigacaoEsgotoSituacaoAction.do?idRegistroAtualizacao="
							+ idSituacaoEsgotoLigacao,
					"Atualizar Situa��o de Liga��o de Esgoto Inserida");

			sessao.removeAttribute("InserirLigacaoEsgotoSituacaoActionForm");

			return retorno;
		}

	}
}