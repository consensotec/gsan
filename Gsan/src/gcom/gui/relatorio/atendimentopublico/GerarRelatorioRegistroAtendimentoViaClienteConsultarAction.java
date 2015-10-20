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
package gcom.gui.relatorio.atendimentopublico;

import java.util.Collection;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.atendimentopublico.registroatendimento.ConsultarRegistroAtendimentoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioConsultarRegistroAtendimentoViaCliente;
import gcom.relatorio.atendimentopublico.RelatorioConsultarRegistroAtendimentoViaClienteCosanpa;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o do relat�rio de bairro manter
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioRegistroAtendimentoViaClienteConsultarAction extends
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
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		ConsultarRegistroAtendimentoActionForm consultarRegistroAtendimentoActionForm = (ConsultarRegistroAtendimentoActionForm) actionForm;

		String idRegistroAtendimento = "";

		if (sessao.getAttribute("idRegistroAtendimento") != null) {
			idRegistroAtendimento = (String) sessao
					.getAttribute("idRegistroAtendimento");
		} else if (httpServletRequest.getParameter("idRegistroAtendimento") != null) {
			idRegistroAtendimento = (String) httpServletRequest
					.getParameter("idRegistroAtendimento");
		} else {
			idRegistroAtendimento = consultarRegistroAtendimentoActionForm.getNumeroRAPesquisado();
		}

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
					
		String nomeEmpresa = sistemaParametro.getNomeAbreviadoEmpresa();
		
		/*
		 * @autor Hugo Leoanrdo
		 * @date 08/04/2010
		 * 
		 * CRC_3205 - Altera��o de Layout de PROTOCOLO DE ATENDIMENTO VIA CLIENTE
		 * 			  especifico para a COSANPA. 
		*/
		if(nomeEmpresa.equals("COSANPA")){
		
			RelatorioConsultarRegistroAtendimentoViaClienteCosanpa relatorioConsultarRegistroAtendimentoViaClienteCosanpa = 
				new RelatorioConsultarRegistroAtendimentoViaClienteCosanpa(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			relatorioConsultarRegistroAtendimentoViaClienteCosanpa.addParametro(
					"idRegistroAtendimento", new Integer(idRegistroAtendimento));
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
	
			relatorioConsultarRegistroAtendimentoViaClienteCosanpa.addParametro(
					"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			
			relatorioConsultarRegistroAtendimentoViaClienteCosanpa.addParametro("funcionario", consultarRegistroAtendimentoActionForm.getUsuario());
			
			retorno = processarExibicaoRelatorio(
					relatorioConsultarRegistroAtendimentoViaClienteCosanpa, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);
	
			// devolve o mapeamento contido na vari�vel retorno
			return retorno;
		}else{
			RelatorioConsultarRegistroAtendimentoViaCliente relatorioConsultarRegistroAtendimentoViaCliente = new RelatorioConsultarRegistroAtendimentoViaCliente(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			relatorioConsultarRegistroAtendimentoViaCliente.addParametro(
					"idRegistroAtendimento", new Integer(idRegistroAtendimento));
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
	
			relatorioConsultarRegistroAtendimentoViaCliente.addParametro(
					"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			retorno = processarExibicaoRelatorio(
					relatorioConsultarRegistroAtendimentoViaCliente, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);
	
			// devolve o mapeamento contido na vari�vel retorno
			return retorno;
		}
	}

}
