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
 * Rômulo Aurélio de Melo Souza Filho
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
package gsan.gui.atendimentopublico.registroatendimento;

import gsan.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacional;
import gsan.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacionalMotivo;
import gsan.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacionalTipo;
import gsan.atendimentopublico.registroatendimento.OcorrenciaOperacional;
import gsan.atendimentopublico.registroatendimento.OcorrenciaOperacionalMotivo;
import gsan.atendimentopublico.registroatendimento.OcorrenciaOperacionalTipo;
import gsan.cadastro.geografico.Bairro;
import gsan.cadastro.geografico.FiltroBairro;
import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1529] - Manter Ocorrencia Operacional
 * 
 * @author Rômulo Aurélio
 * @date 15/07/2013
 */
public class ExibirAtualizarOcorrenciaOperacionalAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	@SuppressWarnings({ "rawtypes" })
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("atualizarOcorrenciaOperacional");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoLocalidade = new ArrayList();
		Collection colecaoBairro = new ArrayList();
		Collection colecaoOcorrenciaOperacionalMotivo = new ArrayList();

		AtualizarOcorrenciaOperacionalActionForm form = (AtualizarOcorrenciaOperacionalActionForm) actionForm;

		if (httpServletRequest.getParameter("pesquisar") == null) {

			this.carregarDadosAtualizar(form, sessao, httpServletRequest, fachada);
		}
		
		if (form.getCodigoMunicipio() != null && !form.getCodigoMunicipio().equals("")) {

			// Dados Municipio

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID,
																	form.getCodigoMunicipio()));

			Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());

			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				Municipio municipio = (Municipio) colecaoMunicipio.iterator().next();
				form.setDescricaoMunicipio(municipio.getNome());

				httpServletRequest.setAttribute("nomeCampo", "codigoMunicipio");

				// Dados Localidade
				colecaoLocalidade = fachada.obterLocalidadesdoMunicipio(new Integer(form.getCodigoMunicipio()));

				// Dados Bairro
				FiltroBairro filtroBairro = new FiltroBairro();
				filtroBairro.adicionarParametro(new ParametroSimples(	FiltroBairro.MUNICIPIO_ID,
																		form.getCodigoMunicipio()));
				filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO.toString()));
				filtroBairro.setCampoOrderBy(FiltroBairro.NOME);

				colecaoBairro = fachada.pesquisar(filtroBairro, Bairro.class.getName());

			} else {
				form.setCodigoMunicipio("");
				form.setDescricaoMunicipio("Município Inexistente");

				httpServletRequest.setAttribute("idMunicipioNaoEncontrado", "exception");
				httpServletRequest.setAttribute("nomeCampo", "codigoMunicipio");
			}

		}

		// Carregamento de Ocorrencia Operacional Motivo
		if (form.getOcorrenciaTipo() != null
				&& !form.getOcorrenciaTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			FiltroOcorrenciaOperacionalMotivo filtroOcorrenciaOperacionalMotivo = new FiltroOcorrenciaOperacionalMotivo();
			filtroOcorrenciaOperacionalMotivo.adicionarParametro(new ParametroSimples(	FiltroOcorrenciaOperacionalMotivo.OCORRENCIA_OPERACIONAL_TIPO_ID,
																						form.getOcorrenciaTipo()));
			filtroOcorrenciaOperacionalMotivo.adicionarParametro(new ParametroSimples(	FiltroOcorrenciaOperacionalMotivo.INDICADOR_USO,
																						ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoOcorrenciaOperacionalMotivo = fachada.pesquisar(filtroOcorrenciaOperacionalMotivo, OcorrenciaOperacionalMotivo.class.getName());

		}

		// Dados ComboBox
		// Ocorrencia Operacional Tipo
		FiltroOcorrenciaOperacionalTipo filtroOcorrenciaOperacionalTipo = new FiltroOcorrenciaOperacionalTipo();
		filtroOcorrenciaOperacionalTipo.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacionalTipo.INDICADOR_USO,
																				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoOcorrenciaOperacionalTipo = fachada.pesquisar(filtroOcorrenciaOperacionalTipo, OcorrenciaOperacionalTipo.class.getName());

		httpServletRequest.setAttribute("colecaoLocalidade", colecaoLocalidade);
		httpServletRequest.setAttribute("colecaoBairro", colecaoBairro);
		httpServletRequest.setAttribute("colecaoOcorrenciaOperacionalMotivo", colecaoOcorrenciaOperacionalMotivo);
		httpServletRequest.setAttribute("colecaoOcorrenciaOperacionalTipo", colecaoOcorrenciaOperacionalTipo);

		return retorno;

	}

	private void carregarDadosAtualizar(AtualizarOcorrenciaOperacionalActionForm form, HttpSession sessao,
			HttpServletRequest httpServletRequest, Fachada fachada) {

		// Verifica se o ocorrenciaOperacional já está na sessão, em caso
		// afirmativo
		// significa que o usuário já entrou na tela e apenas selecionou algum
		// item que deu um reload na tela e em caso negativo significa que ele
		// está entrando pela primeira vez

		if (sessao.getAttribute("objetoOcorrenciaOperacional") != null) {

			OcorrenciaOperacional ocorrenciaOperacional = (OcorrenciaOperacional) sessao.getAttribute("objetoOcorrenciaOperacional");
			sessao.setAttribute("idOcorrenciaOperacional", ocorrenciaOperacional.getId().toString());

			this.setarDadosFormulario(form, sessao, ocorrenciaOperacional);

			sessao.removeAttribute("objetoocorrenciaOperacional");

		} else {

			String idOcorrenciaOperacional = null;

			if (httpServletRequest.getParameter("idOcorrenciaOperacional") == null
					|| httpServletRequest.getParameter("idOcorrenciaOperacional").equals("")) {
				idOcorrenciaOperacional = (String) sessao.getAttribute("idOcorrenciaOperacional");
			} else {
				idOcorrenciaOperacional = (String) httpServletRequest.getParameter("idOcorrenciaOperacional");
				sessao.setAttribute("idOcorrenciaOperacional", idOcorrenciaOperacional);
			}

			httpServletRequest.setAttribute("idOcorrenciaOperacional", idOcorrenciaOperacional);
			sessao.setAttribute("idOcorrenciaOperacional", idOcorrenciaOperacional);

			FiltroOcorrenciaOperacional filtroOcorrenciaOperacional = new FiltroOcorrenciaOperacional();
			filtroOcorrenciaOperacional.adicionarCaminhoParaCarregamentoEntidade(FiltroOcorrenciaOperacional.MUNICIPIO);
			filtroOcorrenciaOperacional.adicionarCaminhoParaCarregamentoEntidade(FiltroOcorrenciaOperacional.BAIRRO);
			filtroOcorrenciaOperacional.adicionarCaminhoParaCarregamentoEntidade(FiltroOcorrenciaOperacional.LOCALIDADE);
			filtroOcorrenciaOperacional.adicionarCaminhoParaCarregamentoEntidade(FiltroOcorrenciaOperacional.OCORRENCIA_OPERACIONAL_MOTIVO);
			filtroOcorrenciaOperacional.adicionarCaminhoParaCarregamentoEntidade(FiltroOcorrenciaOperacional.OCORRENCIA_OPERACIONAL_TIPO);

			filtroOcorrenciaOperacional.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacional.ID,
																				idOcorrenciaOperacional));

			Collection<OcorrenciaOperacional> colecaoOcorrenciaOperacional = fachada.pesquisar(filtroOcorrenciaOperacional, OcorrenciaOperacional.class.getName());

			if (colecaoOcorrenciaOperacional == null || colecaoOcorrenciaOperacional.isEmpty()) {
				throw new ActionServletException("atencao.atualizacao.timestamp");
			}

			httpServletRequest.setAttribute("colecaoOcorrenciaOperacional", colecaoOcorrenciaOperacional);

			OcorrenciaOperacional ocorrenciaOperacional = (OcorrenciaOperacional) colecaoOcorrenciaOperacional.iterator().next();

			this.setarDadosFormulario(form, sessao, ocorrenciaOperacional);

		}

	}

	private void setarDadosFormulario(AtualizarOcorrenciaOperacionalActionForm form, HttpSession sessao,
			OcorrenciaOperacional ocorrenciaOperacional) {
		form.setId(ocorrenciaOperacional.getId().toString());
		form.setCodigoMunicipio(ocorrenciaOperacional.getMunicipio().getId().toString());
		form.setDescricaoMunicipio(ocorrenciaOperacional.getMunicipio().getNome());

		form.setLocalidade(ocorrenciaOperacional.getLocalidade().getId().toString());

		form.setBairro(ocorrenciaOperacional.getBairro().getId().toString());

		form.setOcorrenciaTipo(ocorrenciaOperacional.getOcorrenciaOperacionalTipo().getId().toString());

		form.setMotivoOcorrencia(ocorrenciaOperacional.getOcorrenciaOperacionalMotivo().getId().toString());

		form.setDescricao(ocorrenciaOperacional.getDescricao());

		form.setDataOcorrencia(Util.formatarData(ocorrenciaOperacional.getDataOcorrencia()));

		form.setHoraOcorrencia((Util.formatarHoraSemData(ocorrenciaOperacional.getDataOcorrencia())).substring(0, 5));

		form.setAreasAfetadas(ocorrenciaOperacional.getAreasAfetadas());

		if (ocorrenciaOperacional.getDataPrevisao() != null) {
			form.setDataPrevisao(Util.formatarData(ocorrenciaOperacional.getDataPrevisao()));
		} else {
			form.setDataPrevisao("");
		}

		if (ocorrenciaOperacional.getDataReprogramacao() != null) {
			form.setDataReprogramacao(Util.formatarData(ocorrenciaOperacional.getDataReprogramacao()));
		} else {
			form.setDataReprogramacao("");
		}

		if (ocorrenciaOperacional.getDataConclusao() != null) {
			form.setDataConclusao(Util.formatarData(ocorrenciaOperacional.getDataConclusao()));
		} else {
			form.setDataConclusao("");
		}

		if (ocorrenciaOperacional.getCodigoPeriodoPrevisao() != null
				&& !ocorrenciaOperacional.getCodigoPeriodoPrevisao().equals("")) {
			form.setCodigoPeriodoPrevisao(ocorrenciaOperacional.getCodigoPeriodoPrevisao().toString());
		} else {
			form.setCodigoPeriodoPrevisao("");
		}

		if (ocorrenciaOperacional.getCodigoPeriodoReprogramacao() != null
				&& !ocorrenciaOperacional.getCodigoPeriodoReprogramacao().equals("")) {
			form.setCodigoPeriodoReprogramacao(ocorrenciaOperacional.getCodigoPeriodoReprogramacao().toString());
		} else {
			form.setCodigoPeriodoReprogramacao("");
		}

		if (ocorrenciaOperacional.getCodigoPeriodoConclusao() != null
				&& !ocorrenciaOperacional.getCodigoPeriodoConclusao().equals("")) {
			form.setCodigoPeriodoConclusao(ocorrenciaOperacional.getCodigoPeriodoConclusao().toString());
		} else {
			form.setCodigoPeriodoConclusao("");
		}

		if (ocorrenciaOperacional.getObservacao() != null && !ocorrenciaOperacional.getObservacao().equals("")) {
			form.setObservacao(ocorrenciaOperacional.getObservacao());
		} else {
			form.setObservacao("");
		}

		sessao.setAttribute("ocorrenciaOperacionalAtualizar", ocorrenciaOperacional);
	}

}
