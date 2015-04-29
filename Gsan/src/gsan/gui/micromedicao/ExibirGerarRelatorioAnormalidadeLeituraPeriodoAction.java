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
package gsan.gui.micromedicao;

import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.FiltroFaturamentoGrupo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.leitura.FiltroLeituraAnormalidade;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.util.ConstantesInterfaceGSAN;
import gsan.util.ConstantesSistema;
import gsan.util.Internacionalizador;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Pedro Alexandre
 * @date 21/05/2007
 */
public class ExibirGerarRelatorioAnormalidadeLeituraPeriodoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm = (GerarRelatorioAnormalidadeLeituraPeriodoActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null) {
			relatorioForm.limparForm();
			carregarColecoes(httpServletRequest.getSession(false));
		}

		consultarLocalidadeInicial(relatorioForm);

		consultarLocalidadeFinal(relatorioForm);

		consultarSetorComercialInicial(relatorioForm, httpServletRequest);

		consultarSetorComercialFinal(relatorioForm, httpServletRequest);
		
		if(relatorioForm.getOrdenacao() == null){
			relatorioForm.setOrdenacao("2");
		}

		return actionMapping.findForward("exibirGerarRelatorioAnormalidadeLeituraPeriodo");
	}

	/**
	 * Esse m�todo consulta a localidade final, caso o usu�rio a tenha informado.
	 * Se por acaso ela n�o existir, � enviada a mensagem informando isso.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void consultarLocalidadeFinal(GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm) {

		if ( Util.verificarNaoVazio(relatorioForm.getIdLocalidadeFinal())) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,relatorioForm.getIdLocalidadeFinal()));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO
					,ConstantesSistema.INDICADOR_USO_ATIVO));

			boolean isUnidadeNegocioInformado = false;

			if(Util.isCampoComboboxInformado(relatorioForm.getIdUnidadeNegocio())){
				isUnidadeNegocioInformado = true;

				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID,relatorioForm.getIdUnidadeNegocio()));
			}

			Collection<Localidade> colecaoLocalidade = 
				this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

			if ( Util.isVazioOrNulo(colecaoLocalidade)) {
				relatorioForm.setIdLocalidadeFinal("");

				if(isUnidadeNegocioInformado){
					relatorioForm.setNomeLocalidadeFinal(
							Internacionalizador.getMensagem(ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
									new String[]{ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE,ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO}));
				}else{
					relatorioForm.setNomeLocalidadeFinal(Internacionalizador.getMensagem(
							ConstantesInterfaceGSAN.ATENCAO_PESQUISA_INEXISTENTE, ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE));
				}

				return;
			}

			Localidade localidade = colecaoLocalidade.iterator().next();

			relatorioForm.setIdLocalidadeFinal(localidade.getId().toString());
			relatorioForm.setNomeLocalidadeFinal(localidade.getDescricao());
		}
	}

	/**
	 * Esse m�todo consulta a localidade inicial, caso o usu�rio a tenha informado.
	 * Se por acaso ela n�o existir, � enviada a mensagem informando isso.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void consultarLocalidadeInicial(GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm) {

		if ( Util.verificarNaoVazio(relatorioForm.getIdLocalidadeInicial())) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,relatorioForm.getIdLocalidadeInicial()));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO
					,ConstantesSistema.INDICADOR_USO_ATIVO));

			boolean isUnidadeNegocioInformado = false;

			if(Util.isCampoComboboxInformado(relatorioForm.getIdUnidadeNegocio())){
				isUnidadeNegocioInformado = true;

				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID,relatorioForm.getIdUnidadeNegocio()));
			}

			Collection<Localidade> colecaoLocalidade = 
				this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

			if ( Util.isVazioOrNulo(colecaoLocalidade)) {
				relatorioForm.setIdLocalidadeInicial("");

				if(isUnidadeNegocioInformado){
					relatorioForm.setNomeLocalidadeFinal(
							Internacionalizador.getMensagem(ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
									new String[]{ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE,ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO}));
				}else{
					relatorioForm.setNomeLocalidadeFinal(Internacionalizador.getMensagem(
							ConstantesInterfaceGSAN.ATENCAO_PESQUISA_INEXISTENTE, ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE));
				}

				return;
			}

			Localidade localidade = colecaoLocalidade.iterator().next();

			relatorioForm.setIdLocalidadeInicial(localidade.getId().toString());
			relatorioForm.setNomeLocalidadeInicial(localidade.getDescricao());
		}
	}

	/**
	 * Esse m�todo consulta o setor inicial, caso o usu�rio o tenha informado.
	 * Se por acaso ele n�o existir, � enviada a mensagem informando isso.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void consultarSetorComercialInicial(GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm,
			HttpServletRequest httpServletRequest) {

		if ( Util.verificarNaoVazio(relatorioForm.getCodigoSetorComercialInicial())) {

			Integer codigoSetorInicial = new Integer(relatorioForm.getCodigoSetorComercialInicial());

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,codigoSetorInicial));
			filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID,new Integer(relatorioForm.getIdLocalidadeInicial())));

			Collection<SetorComercial> colecaoSetorComercial = 
				this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if ( Util.isVazioOrNulo(colecaoSetorComercial)) {

				relatorioForm.setNomeSetorComercialInicial(Internacionalizador.getMensagem(ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
						new String[]{ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL,ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE}));

				httpServletRequest.setAttribute("setorComercialInicialInexistente",true);

			} else {
				SetorComercial setor = colecaoSetorComercial.iterator().next();
				relatorioForm.setNomeSetorComercialInicial(setor.getDescricao());
			}

		} else {
			relatorioForm.setNomeSetorComercialInicial("");
		}
	}

	/**
	 * Esse m�todo consulta o setor final, caso o usu�rio o tenha informado.
	 * Se por acaso ele n�o existir, � enviada a mensagem informando isso.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void consultarSetorComercialFinal(GerarRelatorioAnormalidadeLeituraPeriodoActionForm relatorioForm,
			HttpServletRequest httpServletRequest) {

		if ( Util.verificarNaoVazio(relatorioForm.getCodigoSetorComercialFinal())) {

			Integer codigoSetorFinal = new Integer(relatorioForm.getCodigoSetorComercialFinal());

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,codigoSetorFinal));
			filtroSetorComercial.adicionarParametro(
					new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID,new Integer(relatorioForm.getIdLocalidadeFinal())));

			Collection<SetorComercial> colecaoSetorComercial = 
				this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if ( Util.isVazioOrNulo(colecaoSetorComercial)) {
				relatorioForm.setNomeSetorComercialFinal(Internacionalizador.getMensagem(ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
						new String[]{ConstantesInterfaceGSAN.LABEL_GSAN_SETOR_COMERCIAL,ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE}));

				httpServletRequest.setAttribute("setorComercialFinalInexistente",true);

			} else {
				SetorComercial setor = colecaoSetorComercial.iterator().next();
				relatorioForm
				.setNomeSetorComercialFinal(setor.getDescricao());
			}

		} else {
			relatorioForm.setNomeSetorComercialFinal("");
		}
	}

	/**
	 * Esse m�todo carrega todas as cole��es a serem exibidas na tela
	 * na sess�o. (Anormalidades de Leitura e Grupos de Faturamento)
	 *
	 *@since 29/10/2009
	 *@author Marlon Patrick
	 */
	private void carregarColecoes(HttpSession sessao) {

		Fachada fachada = Fachada.getInstancia();

		FiltroLeituraAnormalidade filtroConsumoAnormalidade = new FiltroLeituraAnormalidade(FiltroLeituraAnormalidade.DESCRICAO);				
		
		Collection<LeituraAnormalidade> colecaoAnormalidadeleitura = fachada.pesquisar(filtroConsumoAnormalidade, LeituraAnormalidade.class.getName());

		if(Util.isVazioOrNulo(colecaoAnormalidadeleitura)){
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_ENTIDADE_SEM_DADOS_PARA_SELECAO,
					ConstantesInterfaceGSAN.LABEL_GSAN_ANORMALIDADE_LEITURA);
		}

		sessao.setAttribute("colecaoAnormalidadesLeitura",colecaoAnormalidadeleitura);

		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(FiltroFaturamentoGrupo.DESCRICAO);
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<FaturamentoGrupo> colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

		if(Util.isVazioOrNulo(colecaoFaturamentoGrupo)){
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_ENTIDADE_SEM_DADOS_PARA_SELECAO,
					ConstantesInterfaceGSAN.LABEL_GSAN_GRUPO_FATURAMENTO);
		}

		sessao.setAttribute("colecaoGruposFaturamento",colecaoFaturamentoGrupo);

		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio(FiltroUnidadeNegocio.NOME);
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		if(Util.isVazioOrNulo(colecaoUnidadeNegocio)){
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_ENTIDADE_SEM_DADOS_PARA_SELECAO,
					ConstantesInterfaceGSAN.LABEL_GSAN_GRUPO_FATURAMENTO);
		}

		sessao.setAttribute("colecaoUnidadeNegocio", 
				fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName()));
	}
}