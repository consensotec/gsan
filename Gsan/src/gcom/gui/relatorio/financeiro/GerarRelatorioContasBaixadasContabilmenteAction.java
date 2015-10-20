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

import gcom.batch.Processo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.financeiro.RelatorioContasBaixadasContabilmente;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Gera��o do relat�rio [UC0726] Gerar Relat�rio das Contas Baixadas Contabilmente
 * 
 * @author Vivianne Sousa
 * @data 08/04/2008
 */

public class GerarRelatorioContasBaixadasContabilmenteAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

//		ActionForward retorno = null;
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        GerarRelatorioContasBaixadasContabilmenteActionForm form = (GerarRelatorioContasBaixadasContabilmenteActionForm) actionForm;

        //Este map levar� todos os par�metros para a inicializa��o do relat�rio
        Map parametros = new HashMap();
        
               
        String referenciaInicial = form.getReferenciaInicial();
        String referenciaFinal = form.getReferenciaFinal();
        Integer referenciaInicialInt = 0;
        Integer referenciaFinalInt = 0;
        String tipoFormato = form.getTipoFormato();
        
        if (referenciaInicial != null && !referenciaInicial.equals("") &&
                !Util.validarMesAno(referenciaInicial)) {
            throw new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido",null,"Inicial");
        }
        if (referenciaFinal != null && !referenciaFinal.equals("") &&
                !Util.validarMesAno(referenciaFinal)) {
            throw new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido",null,"Final");
        }
        
        //[FS0001] - Verificar tipo de relat�rio
        if (form.getTipo() == null || form.getTipo().equalsIgnoreCase("")) {
          throw new ActionServletException("atencao.required", null, "Tipo de Relat�rio");
        }
        
        //[FS0002] - Verificar a periodicidade
        if (form.getPeriodicidade() == null || form.getPeriodicidade().equalsIgnoreCase("")) {
          throw new ActionServletException("atencao.required", null, "Periodicidade");
        }
        
        Short tipo = new Short (form.getTipo());
        Short periodicidade = new Short (form.getPeriodicidade());
        //[FS0003] - Verificar atributos inicial e final
        if ((periodicidade.equals(ConstantesSistema.ACUMULADO) || (periodicidade.equals(ConstantesSistema.MENSAL))) &&
            (referenciaFinal == null || referenciaFinal.equalsIgnoreCase(""))) {
          throw new ActionServletException("atencao.required", null, "Refer�ncia das Faturas Final");
        }else{
            referenciaFinalInt = new Integer(Util
                    .formatarMesAnoParaAnoMesSemBarra(referenciaFinal));
        }
        
        if (periodicidade.equals(ConstantesSistema.MENSAL)) {
            
            if((referenciaInicial != null && !referenciaInicial.equalsIgnoreCase("")) &&
               (referenciaFinal == null || referenciaFinal.equalsIgnoreCase(""))){
                
                throw new ActionServletException("atencao.required", null, "Refer�ncia das Faturas Final");
            }
            
            if((referenciaFinal != null && !referenciaFinal.equalsIgnoreCase("")) &&
                (referenciaInicial == null || referenciaInicial.equalsIgnoreCase(""))){
                 
                 throw new ActionServletException("atencao.required", null, "Refer�ncia das Faturas Inicial");
            }
            
            if (referenciaInicial != null && !referenciaInicial.equalsIgnoreCase("")&&
                referenciaFinal != null && !referenciaFinal.equalsIgnoreCase("")) {

                referenciaInicialInt = new Integer(Util
                        .formatarMesAnoParaAnoMesSemBarra(referenciaInicial));

                referenciaFinalInt = new Integer(Util
                        .formatarMesAnoParaAnoMesSemBarra(referenciaFinal));
                
                if (Util.compararAnoMesReferencia(referenciaInicialInt, referenciaFinalInt, ">")) {
                    throw new ActionServletException(
                    "atencao.referencia_fatura_final_menor_referencia_fatura_inicial");
                }

            }
           
        }
        
        if (tipoFormato == null || tipoFormato.equals("")){
        	throw new ActionServletException(
            "atencao.relatorio_tipo_nao_informado");
        }
        
        parametros.put("tipo",tipo);
        parametros.put("periodicidade",periodicidade);
        parametros.put("referenciaInicial",referenciaInicialInt);
        parametros.put("referenciaFinal",referenciaFinalInt);
        parametros.put("tipoFormatoRelatorio", tipoFormato);
        

        if (tipoFormato.equals("TXT")){
        
        Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
        		Processo.GERAR_TXT_CONTAS_BAIXADAS_CONTABILMENTE ,
        		this.getUsuarioLogado(httpServletRequest));
        
        
        } else 
        	if (tipoFormato.equals("PDF")){
        	
        		RelatorioContasBaixadasContabilmente relatorioContasBaixadasContabilmente = new RelatorioContasBaixadasContabilmente((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
    			
        		relatorioContasBaixadasContabilmente.addParametro("tipo", tipo);
        		relatorioContasBaixadasContabilmente.addParametro("periodicidade", periodicidade);
        		relatorioContasBaixadasContabilmente.addParametro("referenciaInicial", referenciaInicialInt);
        		relatorioContasBaixadasContabilmente.addParametro("referenciaFinal", referenciaFinalInt);
        		relatorioContasBaixadasContabilmente.addParametro("tipoFormatoRelatorio", TarefaRelatorio.TIPO_PDF);
    			String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
    			
    			try {
    				retorno = processarExibicaoRelatorio(relatorioContasBaixadasContabilmente,
    						tipoRelatorio, httpServletRequest, httpServletResponse,
    						actionMapping);

    			} catch (RelatorioVazioException ex) {
    				// manda o erro para a p�gina no request atual
    				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

    				// seta o mapeamento de retorno para a tela de aten��o de popup
    				retorno = actionMapping.findForward("telaAtencaoPopup");
    			}
        	
        }
        
        montarPaginaSucesso(httpServletRequest, "Relat�rio foi para batch.",
                "",
                "");
        
		return retorno;
	}
	
}
