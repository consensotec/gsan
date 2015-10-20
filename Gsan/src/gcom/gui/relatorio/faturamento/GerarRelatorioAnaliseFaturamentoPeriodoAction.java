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
package gcom.gui.relatorio.faturamento;

import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaPeriodoHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioAnaliseFaturamentoPeriodo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioAnaliseFaturamentoPeriodoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoAnaliseFaturamento = (Collection) sessao.getAttribute("colecaoAnaliseFaturamento");

		InformarDadosGeracaoRelatorioConsultaPeriodoHelper informarDadosGeracaoRelatorioConsultaPeriodoHelper = (InformarDadosGeracaoRelatorioConsultaPeriodoHelper) sessao
				.getAttribute("informarDadosGeracaoRelatorioConsultaPeriodoHelper");

		// Inicio da parte que vai mandar os parametros para o relatório

		String mesAnoInicialFaturamento = (String) informarDadosGeracaoRelatorioConsultaPeriodoHelper.getAnoMesInicialReferencia().toString();
		String mesAnoFinalFaturamento = (String) informarDadosGeracaoRelatorioConsultaPeriodoHelper.getAnoMesFinalReferencia().toString();
		String mesAnoFaturamento = (String) informarDadosGeracaoRelatorioConsultaPeriodoHelper.getAnoMesInicialReferencia().toString();
		String descricao = (String) informarDadosGeracaoRelatorioConsultaPeriodoHelper.getDescricaoOpcaoTotalizacao();
		Integer tipoAnalise = (Integer) informarDadosGeracaoRelatorioConsultaPeriodoHelper.getTipoAnaliseFaturamento();
		

		// cria uma instância da classe do relatório
		RelatorioAnaliseFaturamentoPeriodo relatorioAnaliseFaturamento = new RelatorioAnaliseFaturamentoPeriodo(
				(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioAnaliseFaturamento.addParametro("mesAnoInicialFaturamento", Util.formatarAnoMesParaMesAno(mesAnoInicialFaturamento));
		relatorioAnaliseFaturamento.addParametro("mesAnoFinalFaturamento", Util.formatarAnoMesParaMesAno(mesAnoFinalFaturamento));
		relatorioAnaliseFaturamento.addParametro("mesAnoFaturamento", Util.formatarAnoMesParaMesAno(mesAnoFaturamento));
		relatorioAnaliseFaturamento.addParametro("descricao", descricao);		
		relatorioAnaliseFaturamento.addParametro("tipoAnalise", tipoAnalise);		
		relatorioAnaliseFaturamento.addParametro("tipoQuebra", descricao);
		relatorioAnaliseFaturamento.addParametro("colecaoAnaliseFaturamento",colecaoAnaliseFaturamento);
		relatorioAnaliseFaturamento.addParametro("informarDadosGeracaoRelatorioConsultaPeriodoHelper",informarDadosGeracaoRelatorioConsultaPeriodoHelper);
		
		//----------------------------------------------------------------------------------------------------------------
        // Alterado por : Yara T. Souza.
		// Data : 26/08/2008
		//----------------------------------------------------------------------------------------------------------------
	     
		String localidade = "";
		if(informarDadosGeracaoRelatorioConsultaPeriodoHelper.getLocalidade()!= null){
			 localidade =  (Integer) informarDadosGeracaoRelatorioConsultaPeriodoHelper.getLocalidade().getId() + " - " + (String) informarDadosGeracaoRelatorioConsultaPeriodoHelper.getLocalidade().getDescricao();
		}
			
		String gerenciaRegional = "";
		if(informarDadosGeracaoRelatorioConsultaPeriodoHelper.getGerenciaRegional()!= null){
			gerenciaRegional = (String) informarDadosGeracaoRelatorioConsultaPeriodoHelper.getGerenciaRegional().getNome();
		}
		String unidadeNegocio = "";
		if(informarDadosGeracaoRelatorioConsultaPeriodoHelper.getUnidadeNegocio() != null){
			unidadeNegocio = (String) informarDadosGeracaoRelatorioConsultaPeriodoHelper.getUnidadeNegocio().getNome();
		}
		String setorComercial = "";
		if(informarDadosGeracaoRelatorioConsultaPeriodoHelper.getSetorComercial()!= null){
			setorComercial = informarDadosGeracaoRelatorioConsultaPeriodoHelper.getSetorComercial().getDescricao();
		}
		
		String quadra = "";
		if(informarDadosGeracaoRelatorioConsultaPeriodoHelper.getQuadra()!= null){
			quadra = informarDadosGeracaoRelatorioConsultaPeriodoHelper.getQuadra().getDescricao();
		}
		
		String grupoFaturamento = "";
		if ( informarDadosGeracaoRelatorioConsultaPeriodoHelper.getFaturamentoGrupo() != null ) {
			grupoFaturamento = informarDadosGeracaoRelatorioConsultaPeriodoHelper.getFaturamentoGrupo().getDescricao();
		}
		
		String rota = "";
		if(informarDadosGeracaoRelatorioConsultaPeriodoHelper.getRota()!= null){
			rota = informarDadosGeracaoRelatorioConsultaPeriodoHelper.getRota().getCodigo().intValue()+"";
		}
		
		Collection colecaoPerfilImovel = (Collection) informarDadosGeracaoRelatorioConsultaPeriodoHelper.getColecaoImovelPerfil();
		Collection colecaoLigacaoAgua = (Collection) informarDadosGeracaoRelatorioConsultaPeriodoHelper.getColecaoLigacaoAguaSituacao();
		Collection colecaoLigacaoEsgoto = (Collection) informarDadosGeracaoRelatorioConsultaPeriodoHelper.getColecaoLigacaoEsgotoSituacao();
		Collection colecaoCategoria = (Collection) informarDadosGeracaoRelatorioConsultaPeriodoHelper.getColecaoCategoria();
		Collection colecaoEsferaPoder = (Collection) informarDadosGeracaoRelatorioConsultaPeriodoHelper.getColecaoEsferaPoder();
    			
		relatorioAnaliseFaturamento.addParametro("localidade", localidade);
		relatorioAnaliseFaturamento.addParametro("gerenciaRegional", gerenciaRegional);
		relatorioAnaliseFaturamento.addParametro("unidadeNegocio", unidadeNegocio);
		relatorioAnaliseFaturamento.addParametro("setorComercial", setorComercial);
		relatorioAnaliseFaturamento.addParametro("quadra", quadra);
		relatorioAnaliseFaturamento.addParametro("grupoFaturamento", grupoFaturamento);
		relatorioAnaliseFaturamento.addParametro("rota", rota);

		relatorioAnaliseFaturamento.addParametro("colecaoPerfilImovel", colecaoPerfilImovel);
		relatorioAnaliseFaturamento.addParametro("colecaoLigacaoAgua", colecaoLigacaoAgua);
		relatorioAnaliseFaturamento.addParametro("colecaoLigacaoEsgoto", colecaoLigacaoEsgoto);
		relatorioAnaliseFaturamento.addParametro("colecaoCategoria",colecaoCategoria);
		relatorioAnaliseFaturamento.addParametro("colecaoEsferaPoder",colecaoEsferaPoder);
		
		//----------------------------------------------------------------------------------------------------------------
		

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioAnaliseFaturamento.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioAnaliseFaturamento,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
			
			montarPaginaSucesso(httpServletRequest, "Relatório Gerado com Sucesso!", 
				"Gerar outro Relatorio", "exibirInformarDadosGeracaoRelatorioConsultaPeriodoAction.do?menu=sim&analiseFaturamento=ok&tipoResumo=ANALISE&id=464");

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}
		
		return retorno;
	}

}