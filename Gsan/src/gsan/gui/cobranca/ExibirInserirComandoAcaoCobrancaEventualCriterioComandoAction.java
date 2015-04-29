/**
 * 
 */
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
package gsan.gui.cobranca;

import gsan.cobranca.CobrancaAcao;
import gsan.cobranca.CobrancaAtividade;
import gsan.cobranca.CobrancaCriterio;
import gsan.cobranca.FiltroCobrancaAcao;
import gsan.cobranca.FiltroCobrancaAtividade;
import gsan.cobranca.FiltroCobrancaCriterio;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0243] Inserir Comando de A��o de Conbran�a - Tipo de Comando Cronograma
 * @author Rafael Santos
 * @since 24/01/2006
 */
public class ExibirInserirComandoAcaoCobrancaEventualCriterioComandoAction  extends GcomAction{
	
	
	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirInserirComandoAcaoCobrancaEventualCriterioComando");

        InserirComandoAcaoCobrancaEventualCriterioComandoActionForm inserirComandoAcaoCobrancaEventualCriterioComandoActionForm = (InserirComandoAcaoCobrancaEventualCriterioComandoActionForm) actionForm;        
        
        //Mudar isso quando implementar a parte de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);
        Fachada fachada = Fachada.getInstancia();
        
        String idCobrancaAtividade = httpServletRequest.getParameter("idCobrancaAtividade");
        String idCobrancaAcao = httpServletRequest.getParameter("idCobrancaAcao");
        
		String anoMesContaInicial = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPeriodoInicialConta(); 
		String anoMesContaFinal = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPeriodoFinalConta();
		
		String anoMesVencimentoInicial = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPeriodoVencimentoContaInicial();  
		String anoMesVencimentoFinal  = inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.getPeriodoVencimentoContaFinal();

		
		String idComandoSelecionado = httpServletRequest.getParameter("idComandoSelecionado");
		if(idComandoSelecionado != null){
			sessao.setAttribute("idCobrancaCriterio",idComandoSelecionado);
		}		
		
		//[FS0012] - Verificar refer�ncia final menor que refer�ncia inicial
		if( (anoMesContaInicial != null && !anoMesContaInicial.equals("")) && (anoMesContaFinal != null && !anoMesContaFinal.equals("")) ){
			
			String anoInicial = anoMesContaInicial.substring(3,7); 
			String mesInicial = anoMesContaInicial.substring(0,2);
			
			String anoFinal = anoMesContaFinal.substring(3,7);
			String mesFinal = anoMesContaFinal.substring(0,2);
			
			boolean valida = Util.validarAnoMes(anoMesContaInicial);
			if(valida){
				throw new ActionServletException(
                "errors.invalid",null,"Per�odo Inicial da Conta");			
			}
			
			valida = Util.validarAnoMes(anoMesContaFinal);
			if(valida){
				throw new ActionServletException(
                "errors.invalid",null,"Per�odo Final da Conta");				
			}			
			
			Calendar periodoInicial = new GregorianCalendar();
			periodoInicial.set(Calendar.DATE,1);	
			periodoInicial.set(Calendar.MONTH,(new Integer(mesInicial).intValue()+1));
			periodoInicial.set(Calendar.YEAR,new Integer(anoInicial).intValue());
			
			Calendar periodoFinal = new GregorianCalendar();
			periodoFinal.set(Calendar.DATE,1);	
			periodoFinal.set(Calendar.MONTH,(new Integer(mesFinal).intValue()+1));
			periodoFinal.set(Calendar.YEAR,new Integer(anoFinal).intValue());
			
			if(periodoInicial.compareTo(periodoFinal) > 0){
	        	throw new ActionServletException(//Refer�ncia Final do Per�odo  � anterior � Refer�ncia Inicial do Per�odo
	                    "atencao.referencia_inicial.maior.referencia_final");				
			}
		}

		//[FS0014] - Verificar data final menos que data inicial 
		if( (anoMesVencimentoInicial != null && !anoMesVencimentoInicial.equals("")) && (anoMesVencimentoFinal != null && !anoMesVencimentoFinal.equals("")) ){
			
			String anoInicial = anoMesVencimentoInicial.substring(6,10); 
			String mesInicial = anoMesVencimentoInicial.substring(3,5);
			String diaInicial = anoMesVencimentoInicial.substring(0,2);
			
			String anoFinal = anoMesVencimentoFinal.substring(6,10);
			String mesFinal = anoMesVencimentoFinal.substring(3,5);
			String diaFinal = anoMesVencimentoInicial.substring(0,2);
			
			boolean valida = Util.validarDiaMesAno(anoMesVencimentoInicial);
			if(valida){
				throw new ActionServletException(
                "errors.invalid",null,"Per�odo Inicial do Vencimento da Conta");				
			}
			valida = Util.validarDiaMesAno(anoMesVencimentoFinal);
			if(valida){
				throw new ActionServletException(
                "errors.invalid",null,"Per�odo Final do Vencimento da Conta");				
			}			
			
			Calendar periodoInicial = new GregorianCalendar();
			periodoInicial.set(Calendar.DATE,new Integer(diaInicial).intValue());	
			periodoInicial.set(Calendar.MONTH,(new Integer(mesInicial).intValue()+1));
			periodoInicial.set(Calendar.YEAR,new Integer(anoInicial).intValue());
			
			Calendar periodoFinal = new GregorianCalendar();
			periodoFinal.set(Calendar.DATE,new Integer(diaFinal).intValue());	
			periodoFinal.set(Calendar.MONTH,(new Integer(mesFinal).intValue()+1));
			periodoFinal.set(Calendar.YEAR,new Integer(anoFinal).intValue());
			
			if(periodoInicial.compareTo(periodoFinal) > 0){
	        	throw new ActionServletException(//Data Final do Per�odo � anterior  � Data Inicial do Per�odo
	                    "atencao.data_inicial.maior.data_final");				
			}
		}
        
        //[SB0003] - Selecionar Cret�rio do Comando
        //pesquisar cobranca atividade
        if(idCobrancaAtividade != null && !idCobrancaAtividade.equals("")){
        	FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
        	filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.ID,idCobrancaAtividade));
        	
        	Collection colecaoCobrancaAtividade = fachada.pesquisar(filtroCobrancaAtividade,CobrancaAtividade.class.getName());

        	if(colecaoCobrancaAtividade != null && !colecaoCobrancaAtividade.isEmpty()){
        		
        		CobrancaAtividade cobrancaAtividade = (CobrancaAtividade)colecaoCobrancaAtividade.iterator().next();
        		if(cobrancaAtividade.getIndicadorExecucao().intValue() == 1){
        			httpServletRequest.setAttribute("habilitarExecutar","true");
        		}else{
        			httpServletRequest.setAttribute("habilitarExecutar","false");
        		}
        	}
        	
        }
        
        
        
        //pesquisar cobranca a��o
        if(idCobrancaAcao != null && !idCobrancaAcao.equals("")){
        	FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
        	filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID,idCobrancaAcao));
        	filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.INDICADOR_EXIBE_EVENTUAL,ConstantesSistema.SIM));
        	
        	Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao,CobrancaAcao.class.getName());

        	if(colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()){
        		
        		CobrancaAcao cobrancaAcao = (CobrancaAcao)colecaoCobrancaAcao.iterator().next();
        		inserirComandoAcaoCobrancaEventualCriterioComandoActionForm.setDescricaoAcaoCobranca(cobrancaAcao.getDescricaoCobrancaAcao());
        	}
        
        	FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
        	//filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterio.ID_COBRANCA_ACAO,idCobrancaAcao));
        	
        	Collection colecaoCriterioCobranca = null;
        	colecaoCriterioCobranca = fachada.pesquisar(filtroCobrancaCriterio,CobrancaCriterio.class.getName());
        	
        	if(colecaoCriterioCobranca == null || colecaoCriterioCobranca.isEmpty()){
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",
						null, "Tabela Cobran�a Crit�rio");
        	}
        	
        	sessao.setAttribute("colecaoCriterioCobranca",colecaoCriterioCobranca);
        }
        
        //carregar criterios de cobranca
        
        if(sessao.getAttribute("inserirComandoAcaoCobrancaEventualCriterioComandoActionForm") == null)
        		sessao.setAttribute("inserirComandoAcaoCobrancaEventualCriterioComandoActionForm",
        				inserirComandoAcaoCobrancaEventualCriterioComandoActionForm);
        return retorno;
    }

}
