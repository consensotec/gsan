/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.micromedicao.FiltrarRelatorioBoletimMedicaoHelper;
import gcom.relatorio.micromedicao.RelatorioBoletimMedicao;
import gcom.relatorio.micromedicao.RelatorioBoletimMedicaoArquivoTxt;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1054] - Gerar Relat�rio Boletim de Medi��o
 * 
 * @author Hugo Leonardo
 *
 * @date 04/08/2010
 */

public class GerarRelatorioBoletimMedicaoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioBoletimMedicaoForm form = (GerarRelatorioBoletimMedicaoForm) actionForm;
		
		FiltrarRelatorioBoletimMedicaoHelper helper = new FiltrarRelatorioBoletimMedicaoHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		boolean peloMenosUmParametroInformado = false;
		
		// Ano Mes
		if ( form.getMesAnoReferencia() != null && 
				!form.getMesAnoReferencia().equals("")) {
			
			String anoMes = Util.formatarMesAnoParaAnoMesSemBarra(form.getMesAnoReferencia());
			helper.setMesAnoReferencia(anoMes);
			peloMenosUmParametroInformado = true;
		}
		
		// Empresa
		if(form.getEmpresa() != null && !form.getEmpresa().equals("-1")){
			
			helper.setEmpresa(form.getEmpresa());
			peloMenosUmParametroInformado = true;
		}
		
		// N�mero Contrato
		if(form.getNumeroContrato() != null && 
				!form.getNumeroContrato().equals("-1")){
			
			helper.setNumeroContrato(form.getNumeroContrato());
			peloMenosUmParametroInformado = true;
		}
		
		// ger�ncia regional
		if(form.getGerenciaRegional() != null && 
				!form.getGerenciaRegional().equals("-1")){
			
			helper.setGerenciaRegional(form.getGerenciaRegional());
			peloMenosUmParametroInformado = true;
		}
		
		// localidade inicial
		if(form.getLocalidadeInicial() != null && 
				!form.getLocalidadeInicial().equals("")){
			
			helper.setLocalidadeInicial(form.getLocalidadeInicial());
			peloMenosUmParametroInformado = true;
		}

		// localidade final
		if(form.getLocalidadeFinal() != null && 
				!form.getLocalidadeFinal().equals("")){
			
			helper.setLocalidadeFinal(form.getLocalidadeFinal());
			peloMenosUmParametroInformado = true;
		}
		
		// forma de gera��o
		if(form.getFormaGeracao() != null && 
				!form.getFormaGeracao().equals("")){
			
			helper.setFormaGeracao(form.getFormaGeracao());
			peloMenosUmParametroInformado = true;
		}

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		TarefaRelatorio relatorio = null;
		
		try {
			// Gerar Arquivo TXT
			if (form.getFormaGeracao().equals("2")
					|| form.getFormaGeracao().equals("3")) {
				
				relatorio = new RelatorioBoletimMedicaoArquivoTxt((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

				String tipoRelatorioTxt = TarefaRelatorio.TIPO_HTML + "";

				relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorioTxt));
				
				relatorio.addParametro("filtrarRelatorioBoletimMedicaoHelper", helper);

				relatorio.addParametro("mesAno", form.getMesAnoReferencia());
				
				retorno = processarExibicaoRelatorio(relatorio, tipoRelatorioTxt, httpServletRequest, 
							httpServletResponse, actionMapping);
				
			}
			
			
			// Gerar Relat�rio
			if (form.getFormaGeracao().equals("1")
				|| form.getFormaGeracao().equals("3")) {
				
				relatorio = new RelatorioBoletimMedicao((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				if (tipoRelatorio  == null) {
					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
				}
				
				relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
				
				relatorio.addParametro("filtrarRelatorioBoletimMedicaoHelper", helper);
				
				relatorio.addParametro("mesAno", form.getMesAnoReferencia());
				
				retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
							httpServletResponse, actionMapping);
				
			}
			
	
		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
			
			return retorno;
		}
	
}