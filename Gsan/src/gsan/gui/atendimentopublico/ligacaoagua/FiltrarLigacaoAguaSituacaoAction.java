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
package gsan.gui.atendimentopublico.ligacaoagua;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ComparacaoTexto;
import gsan.util.filtro.ComparacaoTextoCompleto;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0786]Filtrar Situacao de Ligacao de Agua
 * 
 * @author Vin�cius Medeiros
 * @date 15/05/2008
 */

public class FiltrarLigacaoAguaSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("exibirManterLigacaoAguaSituacao");

		// Mudar isso quando houver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		FiltrarLigacaoAguaSituacaoActionForm filtrarLigacaoAguaSituacaoActionForm = (FiltrarLigacaoAguaSituacaoActionForm) actionForm;

		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarLigacaoAguaSituacaoActionForm.getId();
		String descricao = filtrarLigacaoAguaSituacaoActionForm.getDescricao();
		String descricaoAbreviada = filtrarLigacaoAguaSituacaoActionForm.getDescricaoAbreviada();
		String consumoMinimoFaturamento = filtrarLigacaoAguaSituacaoActionForm.getConsumoMinimoFaturamento();
		String tipoPesquisa = filtrarLigacaoAguaSituacaoActionForm.getTipoPesquisa();

		// Filtro pelo ID
		if (id != null && !id.trim().equals("")) {
			
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			
			if (achou) {
			
				peloMenosUmParametroInformado = true;
				filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
						FiltroLigacaoAguaSituacao.ID, id));
			
			}
		}
		
		// Filtro pela Descri��o
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null&& tipoPesquisa.equals(
					ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {

				filtroLigacaoAguaSituacao.adicionarParametro(
						new ComparacaoTextoCompleto(FiltroLigacaoAguaSituacao.DESCRICAO, 
							descricao));
			} else {

				filtroLigacaoAguaSituacao.adicionarParametro(new ComparacaoTexto(
						FiltroLigacaoAguaSituacao.DESCRICAO, descricao));
			}
		}
		
		// Filtro pela Descri��o Abreviada
		if (descricaoAbreviada != null && !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ComparacaoTextoCompleto(FiltroLigacaoAguaSituacao.DESCRICAO_ABREVIADA,
							descricaoAbreviada));
		} else {

			filtroLigacaoAguaSituacao.adicionarParametro(
					new ComparacaoTexto(FiltroLigacaoAguaSituacao.DESCRICAO_ABREVIADA,
							descricaoAbreviada));
		}

		// Filtro pelo Consumo Minimo Faturamento
		if (consumoMinimoFaturamento != null && !consumoMinimoFaturamento.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ComparacaoTextoCompleto(FiltroLigacaoAguaSituacao.CONSUMO_MINIMO,
							consumoMinimoFaturamento));
		} else {

			filtroLigacaoAguaSituacao.adicionarParametro(
					new ComparacaoTexto(FiltroLigacaoAguaSituacao.CONSUMO_MINIMO,
							consumoMinimoFaturamento));
		}
		
		// Verifica se o indicador de faturamento est� sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorFaturamentoSituacao().equals( ConstantesSistema.TODOS.toString() ) ){
			
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_FATURAMENTO, 
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorFaturamentoSituacao() ) );
		
		}
		
		// Verifica se o indicador de existencia de rede est� sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaRede().equals( ConstantesSistema.TODOS.toString() ) ){
		
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE, 
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaRede() ) );
		
		}
		
		// Verifica se o indicador de existencia de liga��o est� sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaLigacao().equals( ConstantesSistema.TODOS.toString() ) ){
			
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO, 
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaLigacao() ) );
						
		}
		
		//Verifica se o indicador abastecimento est� sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorAbastecimento().equals( ConstantesSistema.TODOS.toString() ) ){
		
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE, 
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorAbastecimento() ) );
		
		}
		
		//Verifica se o indicador �gua ativa est� sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaAtiva().equals( ConstantesSistema.TODOS.toString() ) ){
		
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_AGUA_ATIVA, 
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaAtiva() ) );
		
		}
		
		//Verifica se o indicador �gua desligada est� sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaDesligada().equals( ConstantesSistema.TODOS.toString() ) ){
		
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_AGUA_DESLIGADA,
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaDesligada() ) );
		
		}
		
		//Verifica se o indicador �gua cadastrada est� sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaCadastrada().equals( ConstantesSistema.TODOS.toString() ) ){
		
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_AGUA_CADASTRADA,
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaCadastrada() ) );
		
		}
		
		//Verifica se o indicador analise de �gua est� sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorAnalizeAgua().equals( ConstantesSistema.TODOS.toString() ) ){
		
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_ANALISE_AGUA,
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorAnalizeAgua() ) );
		
		}
		
		//Verifica se o indicador de permiss�o para corte de esgoto est� sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorPermissaoCorteEsgoto().equals( ConstantesSistema.TODOS.toString() ) ){
			
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_PERMISSAO_CORTE_ESGOTO,
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorPermissaoCorteEsgoto() ) );
		
		}
		
		// Verifica se o indicador de uso est� sendo usado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorUso().equals( ConstantesSistema.TODOS.toString() ) ){
			
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, 
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorUso() ) );
						
		}	
		
		Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = fachada.pesquisar(
				filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());

		// Verifica a existencia de uma Situa��o de Liga��o de �gua 
		if (colecaoLigacaoAguaSituacao.isEmpty()) {
		
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Situa��o de Liga��o de �gua");
		
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros
		if (colecaoLigacaoAguaSituacao == null|| colecaoLigacaoAguaSituacao.isEmpty()) {
			
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Situa��o de Liga��o de �gua");
		
		} else {
		
			httpServletRequest.setAttribute("colecaoLigacaoAguaSituacao",
					colecaoLigacaoAguaSituacao);
			LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
			ligacaoAguaSituacao = (LigacaoAguaSituacao) Util
					.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
			String idRegistroAtualizacao = ligacaoAguaSituacao.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		
		}

		sessao.setAttribute("filtroLigacaoAguaSituacao", filtroLigacaoAguaSituacao);
		httpServletRequest.setAttribute("filtroLigacaoAguaSituacao",filtroLigacaoAguaSituacao);

		return retorno;

	}
}
