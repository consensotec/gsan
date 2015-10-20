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
package gcom.gui.relatorio.micromedicao;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.micromedicao.RelatorioNotificacaoDebitosImpressaoSimultanea;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1022] Relat�rio de Notifica��o de D�bitos para Impress�o Simult�nea 
 * 
 * @author Daniel Alves
 * @date 17/05/2010
 */
public class GerarRelatorioNotificacaoDebitosImpressaoSimultaneaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;

		// Form
		FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm form = 
			(FiltrarRelatorioNotificacaoDebitosImpressaoSimultaneaActionForm) actionForm;

		RelatorioNotificacaoDebitosImpressaoSimultaneaHelper helper = new
			RelatorioNotificacaoDebitosImpressaoSimultaneaHelper();

		//Trecho que converte o form para um helper
		
		//linha que vai modificar o formato da data, de mm/aaaa para aaaamm.
		helper.setAnoMesReferencia(
				String.valueOf(Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferencia()))
		);
		
		helper.setEmpresa(form.getEmpresa());
		helper.setGrupo(form.getGrupo());
		helper.setLocalidade(form.getLocalidade());
		helper.setCodigoSetorComercial(form.getCodigoSetorComercial());
		helper.setRota(form.getRota());

		//Modifica o cabe�alho do relatorio tem
		//localidade, setor e rota		
		helper.setCabecalhoTipo(0);

		//caso o Localidade for informada
	    //adicionar Localidade
		if(form.getLocalidade() != null &&
       		 !form.getLocalidade().equalsIgnoreCase("")){			

			helper.setCabecalhoTipo(1);       	 
			
			//caso o Setor for informada
   	    	//adicionar Localidade e Setor
       	    if(form.getCodigoSetorComercial() != null &&
           		 !form.getCodigoSetorComercial().equalsIgnoreCase("")){

       	    	helper.setCabecalhoTipo(2);        

       	    	//caso a Rota for informada
       	    	//adicionar Localidade, Setor e Rota
           	    if(form.getRota() != null &&
	            		 !form.getRota().equalsIgnoreCase("")){
           	    	helper.setCabecalhoTipo(3);
	            }
            }
        }


		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioNotificacaoDebitosImpressaoSimultanea relatorio =
			new RelatorioNotificacaoDebitosImpressaoSimultanea(this.getUsuarioLogado(httpServletRequest));

		relatorio.addParametro("relatorioNotificacaoDebitosImpressaoSimultaneaHelper", helper);
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
	    }

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));

		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}

}