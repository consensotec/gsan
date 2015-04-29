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
package gsan.gui.atendimentopublico.registroatendimento;

import java.util.Collection;
import java.util.Date;

import gsan.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacional;
import gsan.atendimentopublico.registroatendimento.OcorrenciaOperacional;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ComparacaoTexto;
import gsan.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1528] - Filtrar Ocorr�ncia Operacional
 * 
 * @author R�mulo Aurelio
 * @date 12/07/2013
 */
public class FiltrarOcorrenciaOperacionalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirManterOcorrenciaOperacionalAction");

		FiltrarOcorrenciaOperacionalActionForm form = (FiltrarOcorrenciaOperacionalActionForm) actionForm;

		FiltroOcorrenciaOperacional filtroOcorrenciaOperacional = new FiltroOcorrenciaOperacional();
		
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		boolean peloMenosUmParametroInformado = false;

		String codigoMunicipio = form.getCodigoMunicipio();
		String localidade = form.getLocalidade();
		String bairro = form.getBairro();
		String ocorrenciaTipo = form.getOcorrenciaTipo();
		String motivoOcorrencia = form.getMotivoOcorrencia();
		Date dataPrevisaoConclusao = Util.converteStringParaDate(form.getDataPrevisao());
		Date dataReprogramacao = Util.converteStringParaDate(form.getDataReprogramacao());
		Date dataConclusao = Util.converteStringParaDate(form.getDataConclusao());
		String codigoPeriodoPrevisao = form.getCodigoPeriodoPrevisao();
		String codigoPeriodoReprogramacao = form.getCodigoPeriodoReprogramacao();
		String codigoPeriodoConclusao = form.getCodigoPeriodoConclusao();

		// Verifica se o campo municipio foi informado
		if (codigoMunicipio != null && !codigoMunicipio.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroOcorrenciaOperacional.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacional.MUNICIPIO_ID,
																				codigoMunicipio));

		}

		// Verifica se o localidade nome foi informado
		if (localidade != null && !localidade.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroOcorrenciaOperacional.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacional.LOCALIDADE_ID,
																				localidade));

		}

		// Verifica se o campo bairro foi informado
		if (bairro != null && !bairro.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroOcorrenciaOperacional.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacional.BAIRRO_ID,
																				bairro));

		}

		// Verifica se o campo Tipo Ocorrencia foi informado
		if (ocorrenciaTipo != null && !ocorrenciaTipo.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			filtroOcorrenciaOperacional.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacional.OCORRENCIA_OPERACIONAL_TIPO_ID,
																				ocorrenciaTipo));

		}

		// Verifica se o campo Motivo da Ocorrencia foi informado
		if (motivoOcorrencia != null && !motivoOcorrencia.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			filtroOcorrenciaOperacional.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacional.OCORRENCIA_OPERACIONAL_MOTIVO_ID,
																				motivoOcorrencia));

		}

		// Verifica se o campo Data de previsoa de conclusao foi informado
		if (dataPrevisaoConclusao != null && !dataPrevisaoConclusao.equals("")) {

			peloMenosUmParametroInformado = true;

			filtroOcorrenciaOperacional.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacional.DATA_PREVISAO,
																				dataPrevisaoConclusao));
		}

		// Verifica se o campo Data reprogramacao foi informado
		if (dataReprogramacao != null && !dataReprogramacao.equals("")) {

			peloMenosUmParametroInformado = true;

			filtroOcorrenciaOperacional.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacional.DATA_REPROGRAMACAO,
																				dataReprogramacao));
		}

		// Verifica se o campo Data reprogramacao foi informado
		if (dataConclusao != null && !dataConclusao.equals("")) {

			peloMenosUmParametroInformado = true;

			filtroOcorrenciaOperacional.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacional.DATA_CONCLUSAO,
																				dataConclusao));
		}

		// Verifica se o campo codigoPeriodoPrevisao foi informado
		if (codigoPeriodoPrevisao != null && !codigoPeriodoPrevisao.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroOcorrenciaOperacional.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacional.CODIGO_PERIODO_PREVISAO,
																				codigoPeriodoPrevisao));
		}

		// Verifica se o campo codigoPeriodoReprogramacao foi informado
		if (codigoPeriodoReprogramacao != null && !codigoPeriodoReprogramacao.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroOcorrenciaOperacional.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacional.CODIGO_PERIODO_REPROGRAMACAO,
																				codigoPeriodoReprogramacao));
		}

		// Verifica se o campo codigoPeriodoConclusao foi informado
		if (codigoPeriodoConclusao != null && !codigoPeriodoConclusao.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroOcorrenciaOperacional.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacional.CODIGO_PERIODO_CONCLUSAO,
																				codigoPeriodoConclusao));
		}
		// Erro caso o usu�rio mandou Pesquisar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		// Verifica se o checkbox Atualizar est� marcado e em caso afirmativo
		// manda pelo um request uma vari�vel para o
		// ExibirManterOcorrenciaOperacionalAction e nele verificar se ir� para o
		// atualizar ou para o manter
		if (form.getAtualizar() != null && form.getAtualizar().equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar", form.getAtualizar());

		}

		filtroOcorrenciaOperacional.adicionarCaminhoParaCarregamentoEntidade(FiltroOcorrenciaOperacional.BAIRRO);
		filtroOcorrenciaOperacional.adicionarCaminhoParaCarregamentoEntidade(FiltroOcorrenciaOperacional.LOCALIDADE);
		filtroOcorrenciaOperacional.adicionarCaminhoParaCarregamentoEntidade(FiltroOcorrenciaOperacional.MUNICIPIO);
		filtroOcorrenciaOperacional.adicionarCaminhoParaCarregamentoEntidade(FiltroOcorrenciaOperacional.OCORRENCIA_OPERACIONAL_MOTIVO);
		filtroOcorrenciaOperacional.adicionarCaminhoParaCarregamentoEntidade(FiltroOcorrenciaOperacional.OCORRENCIA_OPERACIONAL_TIPO);		

		// Manda o filtro pelo sessao para o
		// ExibirFuncionalidadeAction
		sessao.setAttribute("filtroOcorrenciaOperacional", filtroOcorrenciaOperacional);

		httpServletRequest.setAttribute("filtroOcorrenciaOperacional", filtroOcorrenciaOperacional);

		return retorno;

	}

}
