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
package gcom.gui.relatorio.cadastro;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioResumoQtdeImoveisExcluidosTarifaSocial;
import gcom.relatorio.cadastro.RelatorioResumoQtdeImoveisExcluidosTarifaSocialTipo2;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1164] Gerar Resumo dos Im�veis Exclu�dos da Tarifa Social
 * 
 * @author Vivianne Sousa
 * @date 07/04/2011
 */
public class GerarResumoImoveisExcluidosTarifaSocialAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
        GerarResumoImoveisExcluidosTarifaSocialActionForm form = (GerarResumoImoveisExcluidosTarifaSocialActionForm) actionForm;

        validarCamposObrigatorios(form);

        Integer idGerenciaRegional = null;
        Integer idUnidadeNegocio = null;
        Integer idLocalidade = null;   
        Integer anoMesPesquisaInicial = Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaInicial());
        Integer anoMesPesquisaFinal = Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaFinal());
        String motivoExclusao = form.getMotivoExclusao();
        
		if(form.getGerenciaRegionalId() != null && !form.getGerenciaRegionalId().equals("")
		&& !form.getGerenciaRegionalId().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			idGerenciaRegional = new Integer(form.getGerenciaRegionalId());
		}
		if(form.getUnidadeNegocioId() != null && !form.getUnidadeNegocioId().equals("")
		&& !form.getUnidadeNegocioId().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			idUnidadeNegocio= new Integer(form.getUnidadeNegocioId());
		}
		if(form.getCodigoLocalidade() != null && !form.getCodigoLocalidade().equals("")){
			idLocalidade = new Integer(form.getCodigoLocalidade());
		}
    
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		
        if(motivoExclusao.equals("1") || motivoExclusao.equals("2")){
        	RelatorioResumoQtdeImoveisExcluidosTarifaSocial relatorio = new RelatorioResumoQtdeImoveisExcluidosTarifaSocial
			((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
			relatorio.addParametro("anoMesPesquisaInicial", anoMesPesquisaInicial);
			relatorio.addParametro("anoMesPesquisaFinal", anoMesPesquisaFinal);
			relatorio.addParametro("motivoExclusao", motivoExclusao);
			relatorio.addParametro("idGerenciaRegional", idGerenciaRegional);
			relatorio.addParametro("idUnidadeNegocio", idUnidadeNegocio);
			relatorio.addParametro("idLocalidade", idLocalidade);
			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			
			try {
				retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

			} catch (RelatorioVazioException ex) {
				// manda o erro para a p�gina no request atual
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

				// seta o mapeamento de retorno para a tela de aten��o de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}
        } else if(motivoExclusao.equals("3")){
        
        	RelatorioResumoQtdeImoveisExcluidosTarifaSocialTipo2 relatorio = new RelatorioResumoQtdeImoveisExcluidosTarifaSocialTipo2
			((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
			relatorio.addParametro("anoMesPesquisaInicial", anoMesPesquisaInicial);
			relatorio.addParametro("anoMesPesquisaFinal", anoMesPesquisaFinal);
			relatorio.addParametro("motivoExclusao", motivoExclusao);
			relatorio.addParametro("idGerenciaRegional", idGerenciaRegional);
			relatorio.addParametro("idUnidadeNegocio", idUnidadeNegocio);
			relatorio.addParametro("idLocalidade", idLocalidade);
			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			
			try {
				retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

			} catch (RelatorioVazioException ex) {
				// manda o erro para a p�gina no request atual
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

				// seta o mapeamento de retorno para a tela de aten��o de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}
        }
		
		return retorno;
	}
	

	private void validarCamposObrigatorios(GerarResumoImoveisExcluidosTarifaSocialActionForm form) {
		
		if(form.getAnoMesPesquisaInicial() == null || form.getAnoMesPesquisaInicial().equals("")){
			throw new ActionServletException("atencao.campo.informado", null, "Per�odo do Comando inicial");
		}
		  
		if(form.getAnoMesPesquisaFinal() == null || form.getAnoMesPesquisaFinal().equals("")){
			throw new ActionServletException("atencao.campo.informado", null, "Per�odo do Comando final");
		}
        if(form.getMotivoExclusao() == null || form.getMotivoExclusao().equals("")){
        	throw new ActionServletException("atencao.campo.informado", null, "Motivo da Exclus�o");
        }
		
        if((Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaInicial()).compareTo(Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaFinal()))) == 1){
        	throw new ActionServletException("atencao.referencia.final.menor.referencia.inicial");
        }
        
        if((Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaInicial()).compareTo(Util.recuperaAnoMesDaData(new Date()))) == 1){
        	throw new ActionServletException("atencao.ano_mes_comando.maior.ano_mes_atual");
        }
        if((Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaFinal()).compareTo(Util.recuperaAnoMesDaData(new Date()))) == 1){
        	throw new ActionServletException("atencao.ano_mes_comando.maior.ano_mes_atual");
        }
        
        if(form.getCodigoLocalidade() != null && !form.getCodigoLocalidade().equals("")){
        	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
    		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getCodigoLocalidade()));

    		Collection<Localidade> localidadePesquisada = Fachada.getInstancia().pesquisar(
    				filtroLocalidade, Localidade.class.getName());

    		// Se nenhuma localidade for encontrada a mensagem � enviada para a p�gina
    		if (localidadePesquisada == null || localidadePesquisada.isEmpty()) {
    			throw new ActionServletException("atencao.seituacao_servico_tipo_invalida", null, "localidade");
    		}
    
        }
        
	}

	
}
