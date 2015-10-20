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
package gcom.gui.relatorio.financeiro;

import gcom.arrecadacao.FiltroResumoArrecadacao;
import gcom.arrecadacao.ResumoArrecadacao;
import gcom.fachada.Fachada;
import gcom.financeiro.FiltroResumoFaturamento;
import gcom.financeiro.ResumoFaturamento;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.financeiro.RelatorioParametrosContabeisArrecadacao;
import gcom.relatorio.financeiro.RelatorioParametrosContabeisFaturamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Gera��o do relat�rio [UC0824] Gerar Relat�rio dos Par�metros Cont�beis
 * 
 * @author Bruno Barros
 * @data 07/07/2008
 */

public class GerarRelatorioParametrosContabeisAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

        ActionForward retorno;
        GerarRelatorioParametrosContabeisActionForm form = (GerarRelatorioParametrosContabeisActionForm) actionForm;

        String referenciaContabil = form.getReferenciaContabil();
        
        // Verificar tipo de relat�rio
        if (form.getSelecaoRelatorio() == null || form.getSelecaoRelatorio().equalsIgnoreCase("")) {
          throw new ActionServletException("atencao.required", null, "Tipo de Relat�rio");
        }
        
        //[FS0001] - Validar Referencia Contabil
        String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio"); 
        String selecaoRelatorio = httpServletRequest.getParameter("selecaoRelatorio");    
        
        validarRefereciaContabil( form, selecaoRelatorio );        
        
		TarefaRelatorio relatorio;
        
        //String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio"); 
        //String selecaoRelatorio = httpServletRequest.getParameter("selecaoRelatorio");        
		
		// Verificamos qual o tipo do relatorio a ser chamado se o de faturamento ou o de arrecada��o
		if ( selecaoRelatorio.equals( GerarRelatorioParametrosContabeisActionForm.SELECAO_RELATORIO_FATURAMENTO ) ){
			relatorio = new RelatorioParametrosContabeisFaturamento( (Usuario)( httpServletRequest.getSession(false)).getAttribute("usuarioLogado") );
		} else {
			relatorio = new RelatorioParametrosContabeisArrecadacao( (Usuario)( httpServletRequest.getSession(false)).getAttribute("usuarioLogado") );
		}			
		
        relatorio.addParametro("referenciaContabil", referenciaContabil);
       
        
        if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoRelatorio", Integer.parseInt( tipoRelatorio ) );
        relatorio.addParametro("selecaoRelatorio",  selecaoRelatorio );
		
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;        
	}
	
	// FS00001 validar Referencia Contabil
	private void validarRefereciaContabil( GerarRelatorioParametrosContabeisActionForm form, String selecaoRelatorio ) throws ActionServletException{
		
		Fachada fachada = Fachada.getInstancia();
		
//		// Validados se o ano mes de referencia est� v�lido
//		if ( Util.validarAnoMes( form.getReferenciaContabil() ) ){
//			throw new ActionServletException( "atencao.ano_mes_referencia_invalido" );
//		}
		
		// Verificamos se foi selecionado faturamento ou arrecada��o
		if ( form.getSelecaoRelatorio().equals( GerarRelatorioParametrosContabeisActionForm.SELECAO_RELATORIO_FATURAMENTO ) || selecaoRelatorio.equals(GerarRelatorioParametrosContabeisActionForm.SELECAO_RELATORIO_FATURAMENTO ) ){
            if ( form.getReferenciaContabil() != null && !form.getReferenciaContabil().equals( "" ) ) {
    			// Validamos se existe o ano mes de referencia na tabela de resumo de faturamento
    			FiltroResumoFaturamento filtro = new FiltroResumoFaturamento();
    			filtro.adicionarParametro( new ParametroSimples( FiltroResumoFaturamento.ANO_MES_REFERENCIA, Util.formatarMesAnoParaAnoMesSemBarra( form.getReferenciaContabil() ) ) );
    			Collection<ResumoFaturamento> colResumo = fachada.pesquisar( filtro, 1, ResumoFaturamento.class.getName() );
    			
    			if ( colResumo == null || colResumo.size() == 0 ){
    				throw new ActionServletException( "atencao.ano_mes_referencia_arrecadacao_inexistente", null, form.getReferenciaContabil() );
    			}
            }
		} else {
			// Validamos se existe o ano mes de referencia na tabela de resumo de arrecadacao
            if ( form.getReferenciaContabil() != null && !form.getReferenciaContabil().equals( "" ) ) {
    			FiltroResumoArrecadacao filtro = new FiltroResumoArrecadacao();
    			filtro.adicionarParametro( new ParametroSimples( FiltroResumoArrecadacao.ANO_MES_REFERENCIA, Util.formatarMesAnoParaAnoMesSemBarra( form.getReferenciaContabil() ) ) );
    			Collection<ResumoArrecadacao> colResumo = fachada.pesquisar( filtro, 1, ResumoArrecadacao.class.getName() );
    			
    			if ( colResumo == null || colResumo.size() == 0 ){
    				throw new ActionServletException( "atencao.ano_mes_referencia_arrecadacao_inexistente", null, form.getReferenciaContabil() );
    			}
            }			
		}		
	}	
}
