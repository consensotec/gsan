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
package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
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
 * @date 15/05/2008
 */
public class InserirLigacaoAguaSituacaoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de uma Situacao de Ligacao de Agua
	 * 
	 * [UC0785] Inserir Situacao de Ligacao de Agua
	 * 
	 * 
	 * @author Vin�cius Medeiros
	 * @date 15/05/2008
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

		InserirLigacaoAguaSituacaoActionForm inserirLigacaoAguaSituacaoActionForm = (InserirLigacaoAguaSituacaoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirLigacaoAguaSituacaoActionForm.getDescricao();
		String descricaoAbreviado = inserirLigacaoAguaSituacaoActionForm.getDescricaoAbreviado();
		String consumoMinimoFaturamento = inserirLigacaoAguaSituacaoActionForm.getConsumoMinimoFaturamento();
		String indicadorExistenciaLigacao = inserirLigacaoAguaSituacaoActionForm.getIndicadorExistenciaLigacao();
		String indicadorExistenciaRede = inserirLigacaoAguaSituacaoActionForm.getIndicadorExistenciaRede();
		String indicadorFaturamentoSituacao = inserirLigacaoAguaSituacaoActionForm.getIndicadorFaturamentoSituacao();
		String indicadorAbastecimento = inserirLigacaoAguaSituacaoActionForm.getIndicadorAbastecimento();
		String indicadorAguaAtiva = inserirLigacaoAguaSituacaoActionForm.getIndicadorAguaAtiva();
		String indicadorAguaDesligada = inserirLigacaoAguaSituacaoActionForm.getIndicadorAguaDesligada();
		String indicadorAguaCadastrada = inserirLigacaoAguaSituacaoActionForm.getIndicadorAguaCadastrada();
		String indicadorAnalizeAgua = inserirLigacaoAguaSituacaoActionForm.getIndicadorAnalizeAgua();
		String indicadorPermissaoCorteEsgoto = inserirLigacaoAguaSituacaoActionForm.getIndicadorPermissaoCorteEsgoto();
		String indicadorFaturarLeituraReal = inserirLigacaoAguaSituacaoActionForm.getIndicadorFaturarLeituraReal();
		String indicadorConsumoReal = inserirLigacaoAguaSituacaoActionForm.getIndicadorFaturarConsumoReal();

		LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
		Collection colecaoPesquisa = null;

		
		// Descri��o
		if (!"".equals(inserirLigacaoAguaSituacaoActionForm.getDescricao())) {
			ligacaoAguaSituacao.setDescricao(inserirLigacaoAguaSituacaoActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"Descri��o");
		}
		
		// Descri��o Abreviada
		if (!"".equals(inserirLigacaoAguaSituacaoActionForm.getDescricaoAbreviado())) {
			ligacaoAguaSituacao.setDescricaoAbreviado(inserirLigacaoAguaSituacaoActionForm
					.getDescricaoAbreviado());
		} else {
			throw new ActionServletException("atencao.required", null,
					"Descri��o Abreviada");
		}
		
		// Volume M�nimo da Situa��o de Liga��o
        if (consumoMinimoFaturamento == null 
        		|| consumoMinimoFaturamento.equals("")){
        	throw new ActionServletException("atencao.required",null,"Consumo M�nimo");
        }else{  
        	ligacaoAguaSituacao.setConsumoMinimoFaturamento(new Integer(consumoMinimoFaturamento));
        }
		
		
		// Indicador de Exist�ncia de Liga��o
        if (indicadorExistenciaLigacao == null 
        		|| indicadorExistenciaLigacao.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de Exist�ncia de Liga��o");
        }else{  
        	ligacaoAguaSituacao.setIndicadorExistenciaLigacao(new Short(indicadorExistenciaLigacao));
        }
        
		// Indicador de Exist�ncia de Rede
        if (indicadorExistenciaRede == null 
        		|| indicadorExistenciaRede.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de Exist�ncia de Rede");
        }else{  
        	ligacaoAguaSituacao.setIndicadorExistenciaRede(new Short(indicadorExistenciaRede));
        }
        
		// Indicador de Faturamento
        if (indicadorFaturamentoSituacao == null 
        		|| indicadorFaturamentoSituacao.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de Faturamento");
        }else{  
        	ligacaoAguaSituacao.setIndicadorFaturamentoSituacao(new Short(indicadorFaturamentoSituacao));
        }
        
        //Indicador de Abastecimento
        if (indicadorAbastecimento == null 
        		|| indicadorAbastecimento.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de Abastecimento");
        }else{  
        	ligacaoAguaSituacao.setIndicadorAbastecimento(new Short(indicadorAbastecimento));
        }
        
        //Indicador �gua Ativa
        if (indicadorAguaAtiva == null 
        		|| indicadorAguaAtiva.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador �gua ativa");
        }else{  
        	ligacaoAguaSituacao.setIndicadorAguaAtiva(new Short(indicadorAguaAtiva));
        }
        
        //Indicador �gua Desligada
        if (indicadorAguaDesligada == null 
        		|| indicadorAguaDesligada.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador �gua Desligada");
        }else{  
        	ligacaoAguaSituacao.setIndicadorAguaDesligada(new Short(indicadorAguaDesligada));
        }
        
        //Indicador �gua Cadastrada
        if (indicadorAguaCadastrada == null 
        		|| indicadorAguaCadastrada.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador �gua Cadastrada");
        }else{  
        	ligacaoAguaSituacao.setIndicadorAguaCadastrada(new Short(indicadorAguaCadastrada));
        }
        
        //Indicado Analize de �gua
        if (indicadorAnalizeAgua == null 
        		|| indicadorAnalizeAgua.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de Analize de �gua");
        }else{  
        	ligacaoAguaSituacao.setIndicadorAnalizeAgua(new Short(indicadorAnalizeAgua));
        }
        
        //Indicador de Permiss�o para Corte de Esgoto
        if (indicadorPermissaoCorteEsgoto == null 
        		|| indicadorPermissaoCorteEsgoto.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de Permiss�o para Corte de Esgoto");
        }else{
        	ligacaoAguaSituacao.setIndicadorPermissaoCorteEsgoto(new Short(indicadorPermissaoCorteEsgoto));
        }
        
        //Indicador de s� faturar com Consumo Real
        if (indicadorConsumoReal == null 
        		|| indicadorConsumoReal.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de s� faturar com Consumo Real");
        }else{
        	ligacaoAguaSituacao.setIndicadorConsumoReal(new Short(indicadorConsumoReal));
        }
        
        //Indicador de faturar com Leitura Real
        if (indicadorFaturarLeituraReal == null 
        		|| indicadorFaturarLeituraReal.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de faturar com Leitura Real");
        }else{
        	ligacaoAguaSituacao.setIndicadorFaturarLeituraReal(new Short(indicadorFaturarLeituraReal));
        }
		
		
		// Ultima altera��o
        ligacaoAguaSituacao.setUltimaAlteracao(new Date());
		// Indicador de uso
		Short iu = 1;
		ligacaoAguaSituacao.setIndicadorUso(iu);

		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAguaSituacao.DESCRICAO, ligacaoAguaSituacao.getDescricao()));

		colecaoPesquisa = (Collection) fachada.pesquisar(filtroLigacaoAguaSituacao,
				LigacaoAguaSituacao.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(
					"atencao.ligacao_situacao_agua_ja_cadastrada", null, ligacaoAguaSituacao
							.getDescricao());
		} else {
			ligacaoAguaSituacao.setDescricao(descricao);
			ligacaoAguaSituacao.setDescricaoAbreviado(descricaoAbreviado);
			ligacaoAguaSituacao.setConsumoMinimoFaturamento(new Integer (consumoMinimoFaturamento));
			ligacaoAguaSituacao.setIndicadorExistenciaLigacao(new Short (indicadorExistenciaLigacao));
			ligacaoAguaSituacao.setIndicadorExistenciaRede(new Short (indicadorExistenciaRede));
			ligacaoAguaSituacao.setIndicadorFaturamentoSituacao(new Short (indicadorFaturamentoSituacao));
			ligacaoAguaSituacao.setIndicadorAbastecimento(new Short (indicadorAbastecimento));
			ligacaoAguaSituacao.setIndicadorAguaAtiva(new Short(indicadorAguaAtiva));
			ligacaoAguaSituacao.setIndicadorAguaDesligada(new Short(indicadorAguaDesligada));
			ligacaoAguaSituacao.setIndicadorAguaCadastrada(new Short(indicadorAguaCadastrada));
			ligacaoAguaSituacao.setIndicadorAnalizeAgua(new Short(indicadorAnalizeAgua));			
			ligacaoAguaSituacao.setIndicadorPermissaoCorteEsgoto(new Short(indicadorPermissaoCorteEsgoto));
			ligacaoAguaSituacao.setIndicadorConsumoReal(new Short(indicadorConsumoReal));
			ligacaoAguaSituacao.setIndicadorFaturarLeituraReal(new Short(indicadorFaturarLeituraReal));
			
			
			Integer idSituacaoAguaLigacao = (Integer) fachada.inserir(ligacaoAguaSituacao);

			montarPaginaSucesso(httpServletRequest,
					"Situa��o de Liga��o de �gua " + descricao
							+ " inserida com sucesso.",
					"Inserir outra Situa��o de Liga��o de �gua",
					"exibirInserirLigacaoAguaSituacaoAction.do?menu=sim",
					"exibirAtualizarLigacaoAguaSituacaoAction.do?idRegistroAtualizacao="
							+ idSituacaoAguaLigacao,
					"Atualizar Situa��o de Liga��o de �gua Inserida");

			sessao.removeAttribute("InserirLigacaoAguaSituacaoActionForm");

			return retorno;
		}

	}
}