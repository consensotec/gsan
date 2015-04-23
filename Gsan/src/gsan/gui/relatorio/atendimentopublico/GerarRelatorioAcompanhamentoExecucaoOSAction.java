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
package gsan.gui.relatorio.atendimentopublico;

import gsan.atendimentopublico.ordemservico.Equipe;
import gsan.atendimentopublico.ordemservico.FiltroEquipe;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.atendimentopublico.ordemservico.GerarRelatorioAcompanhamentoExecucaoOSActionForm;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.atendimentopublico.RelatorioAcompanhamentoExecucaoOS;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o do relat�rio de bairro manter
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioAcompanhamentoExecucaoOSAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		GerarRelatorioAcompanhamentoExecucaoOSActionForm form = (GerarRelatorioAcompanhamentoExecucaoOSActionForm) actionForm;

		// Recupera os valores do form para serem passados como par�metros para
		// o RelatorioAcompanhamentoExecucaoOS e nele executar a pesquisa
		// solicitada pelo usu�rio
		String origemServico = form.getOrigemServico();
		String situacaoOS = form.getSituacaoOrdemServico();
		String[] idsServicosTipos = form.getTipoServicoSelecionados();
		String idUnidadeAtendimento = form.getIdUnidadeAtendimento();
		String idUnidadeAtual = form.getIdUnidadeAtual();
		String idUnidadeEncerramento = form.getIdUnidadeEncerramento();
		String periodoAtendimentoInicial = form.getPeriodoAtendimentoInicial();
		String periodoAtendimentoFinal = form.getPeriodoAtendimentoFinal();
		String periodoEncerramentoInicial = form.getPeriodoEncerramentoInicial();
		String periodoEncerramentoFinal = form.getPeriodoEncerramentoFinal();
		String idEquipeProgramacao = form.getIdEquipeProgramacao();
		String idEquipeExecucao = form.getIdEquipeExecucao();
		String tipoOrdenacao = form.getTipoOrdenacao();
		
		if(periodoAtendimentoInicial != null && !periodoAtendimentoInicial.equals("")){
			if(periodoAtendimentoFinal==null || periodoAtendimentoFinal.equals("")){
				throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"atendimento");
			}else{
				Date ini = Util.converteStringParaDate(periodoAtendimentoInicial);
				Calendar calendario = new GregorianCalendar();
				calendario.setTime(ini);
				Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
				numeroDias = new Integer(numeroDias-1);
				Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(periodoAtendimentoFinal),numeroDias); 
				if(dataLimite.after(ini)){
					throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"atendimento");
				}
			}
		}
		
		if(periodoEncerramentoInicial != null && !periodoEncerramentoInicial.equals("")){
			if(periodoEncerramentoFinal==null || periodoEncerramentoFinal.equals("")){
				throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"encerramento");
			}else{
				Date ini = Util.converteStringParaDate(periodoEncerramentoInicial);
				Calendar calendario = new GregorianCalendar();
				calendario.setTime(ini);
				Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
				numeroDias = new Integer(numeroDias-1);
				Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(periodoEncerramentoFinal),numeroDias); 
				if(dataLimite.after(ini)){
					throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"encerramento");
				}
			}
		}

		// Formata as datas para Date
		Date periodoAtendimentoInicialFormatado = null;

		if (periodoAtendimentoInicial != null
				&& !periodoAtendimentoInicial.trim().equals("")) {
			periodoAtendimentoInicialFormatado = Util
					.converteStringParaDate(periodoAtendimentoInicial);
		}

		Date periodoAtendimentoFinalFormatado = null;

		if (periodoAtendimentoFinal != null
				&& !periodoAtendimentoFinal.trim().equals("")) {
			periodoAtendimentoFinalFormatado = Util
					.converteStringParaDate(periodoAtendimentoFinal);
		}

		Date periodoEncerramentoInicialFormatado = null;

		if (periodoEncerramentoInicial != null
				&& !periodoEncerramentoInicial.trim().equals("")) {
			periodoEncerramentoInicialFormatado = Util
					.converteStringParaDate(periodoEncerramentoInicial);
		}

		Date periodoEncerramentoFinalFormatado = null;

		if (periodoEncerramentoFinal != null
				&& !periodoEncerramentoFinal.trim().equals("")) {
			periodoEncerramentoFinalFormatado = Util
					.converteStringParaDate(periodoEncerramentoFinal);
		}

		validarGeracaoRelatorio(origemServico, situacaoOS, idsServicosTipos,
				idUnidadeAtendimento, idUnidadeAtual, idUnidadeEncerramento,
				periodoAtendimentoInicialFormatado,
				periodoAtendimentoFinalFormatado,
				periodoEncerramentoInicialFormatado,
				periodoEncerramentoFinalFormatado, idEquipeProgramacao,
				idEquipeExecucao);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioAcompanhamentoExecucaoOS relatorioAcompanhamentoExecucaoOS = new RelatorioAcompanhamentoExecucaoOS(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

		relatorioAcompanhamentoExecucaoOS.addParametro("origemServico",
				origemServico);
		relatorioAcompanhamentoExecucaoOS
				.addParametro("situacaoOS", situacaoOS);
		relatorioAcompanhamentoExecucaoOS.addParametro("idsServicosTipos",
				idsServicosTipos);
		relatorioAcompanhamentoExecucaoOS.addParametro("idUnidadeAtendimento",
				idUnidadeAtendimento);
		relatorioAcompanhamentoExecucaoOS.addParametro("idUnidadeAtual",
				idUnidadeAtual);
		relatorioAcompanhamentoExecucaoOS.addParametro("idUnidadeEncerramento",
				idUnidadeEncerramento);
		relatorioAcompanhamentoExecucaoOS
				.addParametro("periodoAtendimentoInicial",
						periodoAtendimentoInicialFormatado);
		relatorioAcompanhamentoExecucaoOS.addParametro(
				"periodoAtendimentoFinal", periodoAtendimentoFinalFormatado);
		relatorioAcompanhamentoExecucaoOS.addParametro(
				"periodoEncerramentoInicial",
				periodoEncerramentoInicialFormatado);
		relatorioAcompanhamentoExecucaoOS.addParametro(
				"periodoEncerramentoFinal", periodoEncerramentoFinalFormatado);
		relatorioAcompanhamentoExecucaoOS.addParametro("idEquipeProgramacao",
				idEquipeProgramacao);
		relatorioAcompanhamentoExecucaoOS.addParametro("idEquipeExecucao",
				idEquipeExecucao);
		relatorioAcompanhamentoExecucaoOS.addParametro("tipoOrdenacao",
				tipoOrdenacao);

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		httpServletRequest.setAttribute("telaSucessoRelatorio", true);
		relatorioAcompanhamentoExecucaoOS.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioAcompanhamentoExecucaoOS,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;

	}

	private void validarGeracaoRelatorio(String origemServico,
			String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual,
			String idUnidadeEncerramento, Date periodoAtendimentoInicial,
			Date periodoAtendimentoFinal, Date periodoEncerramentoInicial,
			Date periodoEncerramentoFinal, String idEquipeProgramacao,
			String idEquipeExecucao) {

		Fachada fachada = Fachada.getInstancia();

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = null;
		Collection colecaoUnidade = null;
		
		//[FS0006]
//		if((idUnidadeAtendimento == null || idUnidadeAtendimento.equals(""))
//				&& (idUnidadeAtual == null || idUnidadeAtual.equals(""))){
//			throw new ActionServletException(
//					"atencao.sem.unidade.atendimento.nem.atual", null);
//		}

		// Verifica se a unidade de atendimento existe
		if (idUnidadeAtendimento != null && !idUnidadeAtendimento.equals("")) {

			filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID,
							idUnidadeAtendimento));

			colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());

			if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Unidade de Atendimento");
			}

		}

		// Verifica se a unidade atual existe
		if (idUnidadeAtual != null && !idUnidadeAtual.equals("")) {

			colecaoUnidade = null;

			filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID, idUnidadeAtual));

			colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());

			if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Unidade Atual");
			}

		}

		// Verifica se a unidade de encerramento existe
		if (idUnidadeEncerramento != null && !idUnidadeEncerramento.equals("")) {

			colecaoUnidade = null;

			filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID,
							idUnidadeEncerramento));

			colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional,
					UnidadeOrganizacional.class.getName());

			if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Unidade de Encerramento");
			}

		}

		FiltroEquipe filtroEquipe = null;
		Collection colecaoEquipe = null;

		// Verifica se a equipe de programa��o existe
		if (idEquipeProgramacao != null && !idEquipeProgramacao.equals("")) {

			filtroEquipe = new FiltroEquipe();
			filtroEquipe.adicionarParametro(new ParametroSimples(
					FiltroEquipe.ID, idEquipeProgramacao));

			colecaoEquipe = fachada.pesquisar(filtroEquipe, Equipe.class
					.getName());

			if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Equipe de Programa��o");
			}

		}

		// Verifica se a equipe de execu��o existe
		if (idEquipeExecucao != null && !idEquipeExecucao.equals("")) {

			colecaoEquipe = null;

			filtroEquipe = new FiltroEquipe();
			filtroEquipe.adicionarParametro(new ParametroSimples(
					FiltroEquipe.ID, idEquipeExecucao));

			colecaoEquipe = fachada.pesquisar(filtroEquipe, Equipe.class
					.getName());

			if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Equipe de Execu��o");
			}

		}

		// Verifica se a pesquisa retorno algum resultado
		int qtdeResultados = fachada
				.pesquisarOSGerarRelatorioAcompanhamentoExecucaoCount(
						origemServico, situacaoOS, idsServicosTipos,
						idUnidadeAtendimento, idUnidadeAtual,
						idUnidadeEncerramento, periodoAtendimentoInicial,
						periodoAtendimentoFinal, periodoEncerramentoInicial,
						periodoEncerramentoFinal, idEquipeProgramacao,
						idEquipeExecucao);
		
		if (qtdeResultados == 0) {
			// Caso a pesquisa n�o retorne nenhum resultado comunica ao usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

	}
}
