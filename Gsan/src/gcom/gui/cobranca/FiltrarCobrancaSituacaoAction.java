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
package gcom.gui.cobranca;



import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0838]FILTRAR LIGACAO DE ESGOTO ESGOTAMENTO
 * 
 * @author Arthur Carvalho
 * @date 25/08/08
 */

public class FiltrarCobrancaSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterCobrancaSituacao");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarCobrancaSituacaoActionForm filtrarCobrancaSituacaoActionForm = (FiltrarCobrancaSituacaoActionForm) actionForm;

		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarCobrancaSituacaoActionForm.getId();
		String contaMotivoRevisao = filtrarCobrancaSituacaoActionForm.getContaMotivoRevisao();
		String descricao = filtrarCobrancaSituacaoActionForm.getDescricao();
		String indicadorUso = filtrarCobrancaSituacaoActionForm.getIndicadorUso();
		String indicadorExigenciaAdvogado =  filtrarCobrancaSituacaoActionForm.getIndicadorExigenciaAdvogado();
		String tipoPesquisa = filtrarCobrancaSituacaoActionForm.getTipoPesquisa();
		String indicadorBloqueioParcelamento = filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioParcelamento();
		String indicadorBloqueioRetirada = filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioRetirada();
		String indicadorBloqueioInclusao = filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioInclusao();
		String profissao = filtrarCobrancaSituacaoActionForm.getProfissao();
		String ramoAtividade = filtrarCobrancaSituacaoActionForm.getRamoAtividade();
		String indicadorSelecaoApenasComPermissao = filtrarCobrancaSituacaoActionForm.getIndicadorSelecaoApenasComPermissao();
		String indicadorPrescricaoImoveisParticulares = filtrarCobrancaSituacaoActionForm.getIndicadorPrescricaoImoveisParticulares();
		String indicadorAlterarVencimentoConta = filtrarCobrancaSituacaoActionForm.getIndicadorAlterarVencimentoConta();
		
		//Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		//CODIGO
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				
				filtroCobrancaSituacao.adicionarParametro(
					new ParametroSimples(
							FiltroCobrancaSituacao.ID, 
						id));
			}
		}
		
		//Motivo da situacal especial de faturamento
		if (contaMotivoRevisao != null && 
			!contaMotivoRevisao.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroCobrancaSituacao.adicionarParametro(
				new ParametroSimples(
						FiltroCobrancaSituacao.CONTA_MOTIVO_REVISAO, 
						contaMotivoRevisao));
		}
		
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroCobrancaSituacao
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroCobrancaSituacao.DESCRICAO, descricao));
			} else {

				filtroCobrancaSituacao.adicionarParametro(new ComparacaoTexto(
						FiltroCobrancaSituacao.DESCRICAO, descricao));
			}
		}
	
		// Exige Advogado
		if (indicadorExigenciaAdvogado != null && !indicadorExigenciaAdvogado.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_EXIGENCIA_ADVOGADO, indicadorExigenciaAdvogado));
		}
		
		//Bloqueia Parcelamento
		if (indicadorBloqueioParcelamento != null && !indicadorBloqueioParcelamento.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_BLOQUEIO_PARCELAMENTO, indicadorBloqueioParcelamento));
		}
		
		//Bloqueia Retirada
		if ( indicadorBloqueioRetirada != null && !indicadorBloqueioRetirada.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_BLOQUEIO_RETIRADA, indicadorBloqueioRetirada));
		}
		//Indicador Selecao Apenas Com Permissao
		if ( indicadorSelecaoApenasComPermissao != null && !indicadorSelecaoApenasComPermissao.trim().equals("") ) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_SELECAO_APENAS_COM_PERMISSAO, indicadorSelecaoApenasComPermissao));
		}
		
		// Indicador Prescricao Imoveis Particulares
		if(indicadorPrescricaoImoveisParticulares != null && 
				!indicadorPrescricaoImoveisParticulares.trim().equals("") ){
			
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro( 
				new ParametroSimples( FiltroCobrancaSituacao.INDICADOR_PRESCRICAO_IMOVEIS_PARTICULARES, 
						indicadorPrescricaoImoveisParticulares));
		}
		
		//Bloqueia Retirada
		if ( indicadorBloqueioInclusao != null && !indicadorBloqueioInclusao.trim().equals("") ) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_BLOQUEIO_INCLUSAO, indicadorBloqueioInclusao));
		}

		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_USO, indicadorUso));
		}

		//Profissao
		if (profissao != null && 
			!profissao.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroCobrancaSituacao.adicionarParametro(
				new ParametroSimples(
						FiltroCobrancaSituacao.PROFISSAO, 
						profissao));
		}
		

		//Ramo Atividade
		if (ramoAtividade != null && 
			!ramoAtividade.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroCobrancaSituacao.adicionarParametro(
				new ParametroSimples(
						FiltroCobrancaSituacao.RAMO_ATIVIDADE, 
						ramoAtividade));
		}
		
		filtroCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao"); 
		
		/**
		 * MA20140610677 - Alterar vencimentos para contas negativadas
		 * @author Diogo Luiz
		 * @date 23/06/2014
		 * RM11230 - [UC0858] - Manter Situa��o de Cobran�a		
		 */
		if(indicadorAlterarVencimentoConta != null && !indicadorAlterarVencimentoConta.equals("")){
			peloMenosUmParametroInformado = true;
			
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacao.INDICADOR_ALTERAR_VENCIMENTO_CONTA, 
				indicadorAlterarVencimentoConta));
		}
		
		Collection <CobrancaSituacao> colecaoCobrancaSituacao = fachada
				.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class
						.getName());
		
		
		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros
		if (colecaoCobrancaSituacao == null
				|| colecaoCobrancaSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Situa��o de Cobran�a");
		} else {
			httpServletRequest.setAttribute("colecaoCobrancaSituacao",
					colecaoCobrancaSituacao);
			CobrancaSituacao cobrancaSituacao= new CobrancaSituacao();
			cobrancaSituacao = (CobrancaSituacao) Util
					.retonarObjetoDeColecao(colecaoCobrancaSituacao);
			String idRegistroAtualizar = cobrancaSituacao.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroCobrancaSituacao", filtroCobrancaSituacao);

		httpServletRequest.setAttribute("filtroCobrancaSituacao",
				filtroCobrancaSituacao);
		
		return retorno;
	}
}
