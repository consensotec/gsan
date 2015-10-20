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
 * R�mulo Aur�lio de Melo Souza Filho
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
 * [UC1529] - Manter Ocorr�ncia Operacional
 * 
 * @author R�mulo Aurelio
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

		montarPaginaSucesso(httpServletRequest, "Ocorr�ncia Operacional de c�digo "
				+ ocorrenciaOperacional.getId()
				+ " atualizada com sucesso.", "Manter outra Ocorr�ncia Operacional", 
				"exibirFiltrarOcorrenciaOperacionalAction.do?menu=sim", 
				null, null);

		return retorno;
	}

	/**
	 * [UC1527] Inserir Ocorr�ncia Operacional
	 * 
	 * Carregando os dados da ocorrencia operacional a partir do que foi
	 * informado no formul�rio
	 * 
	 * @author R�mulo Aur�lio
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

		// Observa��o
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

		// Previs�o

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

		// Data de Conclus�o

		if (form.getDataConclusao() != null && !form.getDataConclusao().equals("")) {
			ocorrenciaOperacional.setDataConclusao(Util.converteStringParaDate(form.getDataConclusao()));
		}

		// Codigo Periodo Conclusao
		if (form.getCodigoPeriodoConclusao() != null && !form.getDataConclusao().equals("")) {
			ocorrenciaOperacional.setCodigoPeriodoConclusao(new Short(form.getCodigoPeriodoConclusao()));
		}

		// �LTIMA ALTERA��O
		ocorrenciaOperacional.setUltimaAlteracao(new Date());

		return ocorrenciaOperacional;

	}
}
