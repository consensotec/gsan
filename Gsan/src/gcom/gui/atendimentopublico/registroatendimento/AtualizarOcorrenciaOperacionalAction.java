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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacional;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacionalMotivo;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacionalTipo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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
 * [UC1529] - Manter Ocorrência Operacional
 * 
 * @author Rômulo Aurelio
 * @date 15/07/2013
 */
public class AtualizarOcorrenciaOperacionalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarOcorrenciaOperacionalActionForm form = (AtualizarOcorrenciaOperacionalActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		// CARREGANDO O OBJETO ocorrenciaOperacional
		OcorrenciaOperacional ocorrenciaOperacional = this.getOcorrenciaOperacional(form, fachada);

		// Atualizando OCORRENCIA OPERACIONAL
		fachada.atualizarOcorrenciaOperacional(ocorrenciaOperacional, usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Ocorrência Operacional de código "
				+ ocorrenciaOperacional.getId()
				+ " atualizada com sucesso.", "Manter outra Ocorrência Operacional", 
				"exibirFiltrarOcorrenciaOperacionalAction.do?menu=sim", 
				null, null);

		return retorno;
	}

	/**
	 * [UC1527] Inserir Ocorrência Operacional
	 * 
	 * Carregando os dados da ocorrencia operacional a partir do que foi
	 * informado no formulário
	 * 
	 * @author Rômulo Aurélio
	 * @date 11/07/2013
	 * 
	 * @param form
	 * @param fachada
	 * @return Ocorrencia Operacional
	 */
	@SuppressWarnings("rawtypes")
	private OcorrenciaOperacional getOcorrenciaOperacional(AtualizarOcorrenciaOperacionalActionForm form, Fachada fachada) {

		OcorrenciaOperacional ocorrenciaOperacional = new OcorrenciaOperacional();

		ocorrenciaOperacional.setId(new Integer(form.getId()));
		
		// Descricao
		if (form.getDescricao() != null && !form.getDescricao().equals("")) {
			ocorrenciaOperacional.setDescricao(form.getDescricao().trim());
		}

		// Observação
		if (form.getObservacao() != null && !form.getObservacao().equals("")) {
			ocorrenciaOperacional.setObservacao(form.getObservacao().trim());
		}

		// Municipio
		if (form.getCodigoMunicipio() != null && !form.getCodigoMunicipio().equals("")) {

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID,
																	form.getCodigoMunicipio()));

			Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());

			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

				Municipio municipio = (Municipio) colecaoMunicipio.iterator().next();
				ocorrenciaOperacional.setMunicipio(municipio);

			} else {
				throw new ActionServletException("atencao.municipio.inexistente");
			}

			// Localidade
			if (form.getLocalidade() != null && !form.getLocalidade().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				Localidade localidade = new Localidade();
				localidade.setId(new Integer(form.getLocalidade()));
				ocorrenciaOperacional.setLocalidade(localidade);
			}

			// Bairro
			if (form.getBairro() != null && !form.getBairro().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				Bairro bairro = new Bairro();
				bairro.setId(new Integer(form.getBairro()));
				ocorrenciaOperacional.setBairro(bairro);
			}

		}

		// Tipo Ocorrencia Operacional
		if (form.getOcorrenciaTipo() != null
				&& !form.getOcorrenciaTipo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			OcorrenciaOperacionalTipo ocorrenciaOperacionalTipo = new OcorrenciaOperacionalTipo();
			ocorrenciaOperacionalTipo.setId(new Integer(form.getOcorrenciaTipo()));

			ocorrenciaOperacional.setOcorrenciaOperacionalTipo(ocorrenciaOperacionalTipo);
		}

		// Motivo Ocorrencia Operacional
		if (form.getMotivoOcorrencia() != null
				&& !form.getMotivoOcorrencia().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			OcorrenciaOperacionalMotivo ocorrenciaOperacionalMotivo = new OcorrenciaOperacionalMotivo();
			ocorrenciaOperacionalMotivo.setId(new Integer(form.getMotivoOcorrencia()));

			ocorrenciaOperacional.setOcorrenciaOperacionalMotivo(ocorrenciaOperacionalMotivo);
		}

		// Data Ocorrencia e Hora Ocorrencia
		if (form.getDataOcorrencia() != null && !form.getDataOcorrencia().equals("") && form.getHoraOcorrencia() != null
				&& !form.getHoraOcorrencia().equals("")) {

			Date dataOcorrencia = Util.converteStringParaDateHora(form.getDataOcorrencia() + " " + form.getHoraOcorrencia()
					+ ":00");

			ocorrenciaOperacional.setDataOcorrencia(dataOcorrencia);
		}

		// Areas Afetadas

		if (form.getAreasAfetadas() != null && !form.getAreasAfetadas().equals("")) {
			ocorrenciaOperacional.setAreasAfetadas(form.getAreasAfetadas().trim());
		}

		// Previsão

		if (form.getDataPrevisao() != null && !form.getDataPrevisao().equals("")) {
			ocorrenciaOperacional.setDataPrevisao(Util.converteStringParaDate(form.getDataPrevisao()));
		}

		// Codigo Periodo Previsao
		if (form.getCodigoPeriodoPrevisao() != null && !form.getDataPrevisao().equals("")) {
			ocorrenciaOperacional.setCodigoPeriodoPrevisao(new Short(form.getCodigoPeriodoPrevisao()));
		}

		// Data de Reprogramacao

		if (form.getDataReprogramacao() != null && !form.getDataReprogramacao().equals("")) {
			ocorrenciaOperacional.setDataReprogramacao(Util.converteStringParaDate(form.getDataReprogramacao()));
		}

		// Codigo Periodo Reprogramacao
		if (form.getCodigoPeriodoReprogramacao() != null && !form.getDataReprogramacao().equals("")) {
			ocorrenciaOperacional.setCodigoPeriodoReprogramacao(new Short(form.getCodigoPeriodoReprogramacao()));
		}

		// Data de Conclusão

		if (form.getDataConclusao() != null && !form.getDataConclusao().equals("")) {
			ocorrenciaOperacional.setDataConclusao(Util.converteStringParaDate(form.getDataConclusao()));
		}

		// Codigo Periodo Conclusao
		if (form.getCodigoPeriodoConclusao() != null && !form.getDataConclusao().equals("")) {
			ocorrenciaOperacional.setCodigoPeriodoConclusao(new Short(form.getCodigoPeriodoConclusao()));
		}

		// ÚLTIMA ALTERAÇÃO
		ocorrenciaOperacional.setUltimaAlteracao(new Date());

		return ocorrenciaOperacional;

	}
}
