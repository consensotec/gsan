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
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarResolucaoDiretoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = request.getSession(false);

		AtualizarResolucaoDiretoriaActionForm form = (AtualizarResolucaoDiretoriaActionForm) actionForm;

		ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) sessao.getAttribute("resolucaoDiretoriaAtualizar");

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

		if (Util.verificarNaoVazio(form.getIdParcelasEmAtraso())) {
			FiltroResolucaoDiretoria filtro = new FiltroResolucaoDiretoria();
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.CODIGO, new Integer(form
					.getIdParcelasEmAtraso())));
			Collection<ResolucaoDiretoria> colecaoRD = getFachada().pesquisar(filtro,
					ResolucaoDiretoria.class.getName());

			if (!Util.isVazioOrNulo(colecaoRD)) {
				// RD Parcelas em Atraso inexistente.
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

			Collection<ResolucaoDiretoria> colecaoRD = getFachada().pesquisar(filtro,
					ResolucaoDiretoria.class.getName());

			if (Util.isVazioOrNulo(colecaoRD)) {
				// RD Parcelamento em Andamento inexistente.
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "RD Parcelamento em Andamento");
			}

			ResolucaoDiretoria rdParcelamentoEmAndamento = new ResolucaoDiretoria();
			rdParcelamentoEmAndamento.setId(new Integer(form.getIdParcelamentoEmAndamento()));
			resolucaoDiretoria.setRdParcelamentoEmAndamento(rdParcelamentoEmAndamento);
		}

		if (Util.verificarNaoVazio(form.getDataFim())) {
			resolucaoDiretoria.setDataVigenciaFim(Util.converteStringParaDate(form.getDataFim()));
		}

		// Validar indicadores que só podem ser habilidatos para apenas uma RD
		validarIndicadores(resolucaoDiretoria);

		getFachada().atualizarResolucaoDiretoria(resolucaoDiretoria, this.getUsuarioLogado(request));

		montarPaginaSucesso(request, "Resolução de Diretoria "
				+ resolucaoDiretoria.getNumeroResolucaoDiretoria()
				+ " atualizado com sucesso.",
				"Realizar outra Manutenção de Resolução de Diretoria",
				"exibirFiltrarResolucaoDiretoriaAction.do?menu=sim");

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
			ResolucaoDiretoria rdExistente = (ResolucaoDiretoria) Util.retonarObjetoDeColecao(colecaoRD);
			if (rdExistente != null && !rdExistente.getId().equals(rd.getId())) {
				throw new ActionServletException("atencao.indicador_resolucao_ja_utilizado",
						"Parcelamento para Loja Virtual", rdExistente.getDescricaoAssunto());
			}
		}

		if (ConstantesSistema.SIM.equals(rd.getIndicadorParcelamentoCartaoCredito())) {
			filtro.limparListaParametros();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_CARTAO_CREDITO, ConstantesSistema.SIM));

			colecaoRD = getFachada().pesquisar(filtro, ResolucaoDiretoria.class.getName());
			ResolucaoDiretoria rdExistente = (ResolucaoDiretoria) Util.retonarObjetoDeColecao(colecaoRD);

			if (rdExistente != null && !rdExistente.getId().equals(rd.getId())) {
				throw new ActionServletException("atencao.indicador_resolucao_ja_utilizado",
						" Parcelamento com Cartão de Crédito", rdExistente.getDescricaoAssunto());
			}
		}
	}
}
