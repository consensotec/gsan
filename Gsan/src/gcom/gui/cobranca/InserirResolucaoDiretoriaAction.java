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

import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado para inserir uma resolu��o de diretoria no banco
 * 
 * [UC0217] Inserir Resolu��o de Diretoria Permite inserir uma
 * ResolucaoDiretoria
 * 
 * @author Rafael Corr�a
 * @since 30/03/2006
 */
public class InserirResolucaoDiretoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirResolucaoDiretoriaActionForm form = (InserirResolucaoDiretoriaActionForm) actionForm;

		// Cria uma resolu��o de diretoria setando os valores informados pelo
		// usu�rio na pagina para ser inserido no banco
		ResolucaoDiretoria resolucaoDiretoria = new ResolucaoDiretoria();

		resolucaoDiretoria.setNumeroResolucaoDiretoria(form.getNumero());
		resolucaoDiretoria.setDescricaoAssunto(form.getAssunto());
		resolucaoDiretoria.setDataVigenciaInicio(Util.converteStringParaDate(form.getDataInicio()));
		resolucaoDiretoria.setIndicadorParcelamentoUnico(new Short(form.getIndicadorParcelamentoUnico()));
		resolucaoDiretoria.setIndicadorUtilizacaoLivre(new Short(form.getIndicadorUtilizacaoLivre()));
		resolucaoDiretoria.setIndicadorDescontoSancoes(new Short(form.getIndicadorDescontoSancoes()));
		resolucaoDiretoria.setIndicadorParcelasEmAtraso(new Short(form.getIndicadorParcelasEmAtraso()));
		resolucaoDiretoria.setIndicadorParcelamentoEmAndamento(new Short(form.getIndicadorParcelamentoEmAndamento()));
		resolucaoDiretoria.setIndicadorNegociacaoSoAVista(new Short(form.getIndicadorNegociacaoSoAVista()));
		resolucaoDiretoria.setIndicadorDescontoSoEmContaAVista(new Short(form.getIndicadorDescontoSoEmContaAVista()));
		resolucaoDiretoria.setIndicadorParcelamentoLojaVirtual(new Short(form.getIndicadorParcelamentoLojaVirtual()));
		resolucaoDiretoria.setIndicadorParcelamentoCartaoCredito(new Short(form.getIndicadorParcelamentoCartaoCredito()));

		if (Util.verificarNaoVazio(form.getIdParcelasEmAtraso())){
			FiltroResolucaoDiretoria filtro = new FiltroResolucaoDiretoria();
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.CODIGO, new Integer(form
					.getIdParcelasEmAtraso())));

			Collection<ResolucaoDiretoria> colecaoRD = getFachada().pesquisar(filtro, ResolucaoDiretoria.class.getName());
			if(Util.isVazioOrNulo(colecaoRD)) {
				//RD Parcelamento em Andamento inexistente.
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "RD Parcelas em Atraso");
			}

			ResolucaoDiretoria rdParcelasEmAtraso = new ResolucaoDiretoria();
			rdParcelasEmAtraso.setId(new Integer(form.getIdParcelasEmAtraso()));
			resolucaoDiretoria.setRdParcelasEmAtraso(rdParcelasEmAtraso);
		}

		if (Util.verificarNaoVazio(form.getIdParcelamentoEmAndamento())) {
			FiltroResolucaoDiretoria filtro = new FiltroResolucaoDiretoria();
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.CODIGO, new Integer(form
					.getIdParcelamentoEmAndamento())));

			Collection<ResolucaoDiretoria> colecaoRD = getFachada().pesquisar(filtro, ResolucaoDiretoria.class.getName());
			if(Util.isVazioOrNulo(colecaoRD)) {
				//RD Parcelamento em Andamento inexistente.
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "RD Parcelamento em Andamento");
			}

			ResolucaoDiretoria rdParcelamentoEmAndamento = new ResolucaoDiretoria();
			rdParcelamentoEmAndamento.setId(new Integer(form.getIdParcelamentoEmAndamento()));
			resolucaoDiretoria.setRdParcelamentoEmAndamento(rdParcelamentoEmAndamento);
		}

		// Verifica se a data final foi digitada em caso afirmativo seta-a no objeto
		if (Util.verificarNaoVazio(form.getDataFim())) {
			resolucaoDiretoria.setDataVigenciaFim(Util.converteStringParaDate(form.getDataFim()));
		}

		// Validar indicadores que s� podem ser habilidatos para apenas uma RD
		validarIndicadores(resolucaoDiretoria);

		// Insere uma Resolu��o de Diretoria na base, mas fazendo, antes,
		// algumas verifica��es no ControladorCobrancaSEJB.
		Integer id = (Integer) getFachada().inserirResolucaoDiretoria(resolucaoDiretoria,
				this.getUsuarioLogado(request));

		// Monta a p�gina de sucesso de acordo com o padr�o do sistema.
		montarPaginaSucesso(request, "Resolu��o de Diretoria "
				+ resolucaoDiretoria.getNumeroResolucaoDiretoria()
				+ " inserida com sucesso.",
				"Inserir outra Resolu��o de Diretoria",
				"exibirInserirResolucaoDiretoriaAction.do?menu=sim",
				"exibirAtualizarResolucaoDiretoriaAction.do?inserir=sim&resolucaoDiretoriaID="
						+ id, "Atualizar Resolu��o de Diretoria inserida");

		return retorno;
	}

	private void validarIndicadores(ResolucaoDiretoria rd) {
		Collection<ResolucaoDiretoria> colecaoRD;
		FiltroResolucaoDiretoria filtro = new FiltroResolucaoDiretoria();

		if (ConstantesSistema.SIM.equals(rd.getIndicadorParcelamentoLojaVirtual())) {
			filtro.limparListaParametros();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_LOJA_VIRTUAL, ConstantesSistema.SIM));

			colecaoRD = getFachada().pesquisar(filtro, ResolucaoDiretoria.class.getName());
			if (!Util.isVazioOrNulo(colecaoRD)) {
				ResolucaoDiretoria rdExistente = (ResolucaoDiretoria) Util.retonarObjetoDeColecao(colecaoRD);
				throw new ActionServletException("atencao.indicador_resolucao_ja_utilizado",
						"Parcelamento para Loja Virtual", rdExistente.getDescricaoAssunto());
			}
		}

		if (ConstantesSistema.SIM.equals(rd.getIndicadorParcelamentoCartaoCredito())) {
			filtro.limparListaParametros();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_CARTAO_CREDITO, ConstantesSistema.SIM));

			colecaoRD = getFachada().pesquisar(filtro, ResolucaoDiretoria.class.getName());
			if (!Util.isVazioOrNulo(colecaoRD)) {
				ResolucaoDiretoria rdExistente = (ResolucaoDiretoria) Util.retonarObjetoDeColecao(colecaoRD);
				throw new ActionServletException("atencao.indicador_resolucao_ja_utilizado",
						" Parcelamento com Cart�o de Cr�dito", rdExistente.getDescricaoAssunto());
			}
		}
	}
}
