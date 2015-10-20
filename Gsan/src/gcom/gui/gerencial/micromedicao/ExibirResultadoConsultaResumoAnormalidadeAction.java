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
package gcom.gui.gerencial.micromedicao;

import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.micromedicao.ResumoAnormalidadeConsultaHelper;
import gcom.gui.GcomAction;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Administrador
 * @date 07/03/2006
 * 
 */
public class ExibirResultadoConsultaResumoAnormalidadeAction extends GcomAction {

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descri��o sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descri��o sobre o fluxo secund�rio>
	 * 
	 * <Identificador e nome do fluxo secund�rio>
	 * 
	 * @author Administrador
	 * @date 07/03/2006
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

		ActionForward retorno = actionMapping.findForward("resultadoResumoAnormalidade");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando implementar a parte de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		List listResumoAnormalidadeAgua = (List) sessao
				.getAttribute("colecaoResumoAnormalidadeAgua");
		List listResumoAnormalidadePoco = (List) sessao
				.getAttribute("colecaoResumoAnormalidadePoco");
		
		InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = 
        	(InformarDadosGeracaoRelatorioConsultaHelper)sessao.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");
		
		if(informarDadosGeracaoRelatorioConsultaHelper.getOpcaoTotalizacao().intValue() ==
				ConstantesSistema.CODIGO_SETOR_COMERCIAL_QUADRA){
			retorno = actionMapping.findForward("resultadoResumoAnormalidadeEloPoloLocalidade");
		}
		
		List colecaoResumoAnormalidadeAgua = new ArrayList();
		List colecaoResumoAnormalidadePoco = new ArrayList();
		List colecaoResumoAnormalidadeRelatorio = new ArrayList();

		Iterator iteratorAgua = listResumoAnormalidadeAgua.iterator();
		Iterator iteratorPoco = listResumoAnormalidadePoco.iterator();
		
		Object[] resumoAnormalidadeAgua = null;
		Object[] resumoAnormalidadePoco = null;
		
//		ResumoAnormalidadeLeitura resumoAnormalidadeLeitura = null;
		ResumoAnormalidadeConsultaHelper resumoAnormalidadeConsultaHelper = null;
		//Totais
		//ResumoAnormalidadeConsultaHelper resumoAnormalidadeAguaTotal = null;
		//ResumoAnormalidadeConsultaHelper resumoAnormalidadePocoTotal = null;

		int somatorioAnormalidadeAgua = 0;
		int somatorioAnormalidadePoco = 0;
		/**
		 * Divide o resultado da cole��o em dois subresultados
		 * um por liga��o agua e outro por liga��o esgoto
		 */
		boolean primeiraVez = true;
		
		while(iteratorAgua.hasNext()){
			
//			if(informarDadosGeracaoRelatorioConsultaHelper.getOpcaoTotalizacao().intValue() == 
//				ConstantesSistema.CODIGO_ESTADO){
				
				resumoAnormalidadeAgua = (Object[])iteratorAgua.next();
				
				resumoAnormalidadeConsultaHelper = new ResumoAnormalidadeConsultaHelper();
				
				resumoAnormalidadeConsultaHelper.setDescricaoLeituraAnormalidadeFaturada((String)resumoAnormalidadeAgua[0]);
				resumoAnormalidadeConsultaHelper.setQuantidadeMedicao((Integer)resumoAnormalidadeAgua[1] + "");
				resumoAnormalidadeConsultaHelper.setIdMedicaoTipo(MedicaoTipo.LIGACAO_AGUA+"");
				
			
//			}
			if(primeiraVez){
				// Pega o total q esta no primeiro registro
				//resumoAnormalidadeAguaTotal = resumoAnormalidadeConsultaHelper;
				primeiraVez = false;
				colecaoResumoAnormalidadeRelatorio.add(resumoAnormalidadeConsultaHelper);
			}else{
				somatorioAnormalidadeAgua += Integer.parseInt(resumoAnormalidadeConsultaHelper.getQuantidadeMedicao());
				colecaoResumoAnormalidadeAgua.add(resumoAnormalidadeConsultaHelper);
				
				colecaoResumoAnormalidadeRelatorio.add(resumoAnormalidadeConsultaHelper);
			}
		}
		
		primeiraVez = true;
		while(iteratorPoco.hasNext()){
			
						
				resumoAnormalidadePoco = (Object[])iteratorPoco.next();
				
				resumoAnormalidadeConsultaHelper = new ResumoAnormalidadeConsultaHelper();
				
				resumoAnormalidadeConsultaHelper.setDescricaoLeituraAnormalidadeFaturada((String)resumoAnormalidadePoco[0]);
				resumoAnormalidadeConsultaHelper.setQuantidadeMedicao((Integer)resumoAnormalidadePoco[1] + "");
				resumoAnormalidadeConsultaHelper.setIdMedicaoTipo(MedicaoTipo.POCO+"");
			
			if(primeiraVez){
				// Pega o total q esta no primeiro registro
				//resumoAnormalidadePocoTotal = resumoAnormalidadeConsultaHelper;
				primeiraVez = false;
				colecaoResumoAnormalidadeRelatorio.add(resumoAnormalidadeConsultaHelper);
			}else{
				somatorioAnormalidadePoco += Integer.parseInt(resumoAnormalidadeConsultaHelper.getQuantidadeMedicao());
				colecaoResumoAnormalidadePoco.add(resumoAnormalidadeConsultaHelper);
				
				colecaoResumoAnormalidadeRelatorio.add(resumoAnormalidadeConsultaHelper);
			}
		}
		
		/**
		 * Cria cole��o de agrupamntos(uma cole��o de object[3], agrupamento, id, descricao)
		 */
		Collection colecaoAgrupamento = fachada.criarColecaoAgrupamentoResumos(informarDadosGeracaoRelatorioConsultaHelper);
		
		sessao.setAttribute("colecaoResumoAnormalidadeRelatorio", colecaoResumoAnormalidadeRelatorio);
		httpServletRequest.setAttribute("somatorioAnormalidadeAgua",somatorioAnormalidadeAgua+"");
		httpServletRequest.setAttribute("somatorioAnormalidadePoco",somatorioAnormalidadePoco+"");
		sessao.setAttribute("colecaoAgrupamento", colecaoAgrupamento);
		sessao.setAttribute("mesAnoReferencia", Util.formatarAnoMesParaMesAno(informarDadosGeracaoRelatorioConsultaHelper.getAnoMesReferencia()));
		httpServletRequest.setAttribute("colecaoResumoAnormalidadeAgua", colecaoResumoAnormalidadeAgua);
		httpServletRequest.setAttribute("colecaoResumoAnormalidadePoco", colecaoResumoAnormalidadePoco);
		
		return retorno;
	}

}
