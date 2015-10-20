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
package gcom.gui.relatorio.cobranca;

import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.fachada.Fachada;
import gcom.gui.cobranca.ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.RelatorioEmitirProtocoloDocumentoCobranca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o do relat�rio 
 * [UC0580]Emitir Protocolo de Documento de Cobran�a do Cronogrma
 * 
 * @author Ana Maria
 * @date 15/05/2007
 */
public class GerarRelatorioEmitirProtocoloDocumentoCobrancaAction extends
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

		Fachada fachada =  Fachada.getInstancia();
		
		 HttpSession sessao = httpServletRequest.getSession(false);    
		
		// cria uma inst�ncia da classe do relat�rio
		RelatorioEmitirProtocoloDocumentoCobranca relatorioEmitirProtocoloDocumentoCobranca = new RelatorioEmitirProtocoloDocumentoCobranca((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));		
		
		Collection protocoloDocumentoCobranca = null;

		if(sessao.getAttribute("cobrancaAcaoAtividadeCronograma")!= null){
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma)sessao.getAttribute("cobrancaAcaoAtividadeCronograma");
			Integer idCobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma.getId();
			protocoloDocumentoCobranca = fachada.pesquisarProtocoloDocumentoCobrancaCronograma(idCobrancaAcaoAtividadeCronograma);
			String primeiroTitulo = "COBRAN�A: "+ Util.formatarAnoMesParaMesAno(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getAnoMesReferencia())+"    "+
									"DATA DA REALIZA��O: "+ Util.formatarData(cobrancaAcaoAtividadeCronograma.getRealizacao())+"    "+
									"HORA DA REALIZA��O: "+ Util.formatarHoraSemData(cobrancaAcaoAtividadeCronograma.getRealizacao());
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("grupo", cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getCobrancaGrupo().getDescricao());
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("primeiroTitulo",primeiroTitulo);
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("acaoCobranca", "RESUMO DAS ORDENS DE COBRAN�A EMITIDAS "+cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao());
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("R0000","R0580");
		}else{
			ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm form = (ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm)actionForm;
			Integer idCobrancaAcaoAtividadeComand = new Integer(form.getIdCobrancaAcaoAtividadeComando());
			protocoloDocumentoCobranca = fachada.pesquisarProtocoloDocumentoCobrancaEventual(idCobrancaAcaoAtividadeComand);
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("grupo", null);
			String primeiroTitulo = "DATA DA REALIZA��O: "+ form.getDataRealizacao()+"    "+
									"HORA DA REALIZA��O: "+ form.getHoraRealizacao();
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("primeiroTitulo",primeiroTitulo);
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("acaoCobranca", "RESUMO DAS ORDENS DE COBRAN�A EMITIDAS "+form.getAcaoCobranca()+ " - EVENTUAL");
			relatorioEmitirProtocoloDocumentoCobranca.addParametro("R0000","R0581");
		}
		
		relatorioEmitirProtocoloDocumentoCobranca.addParametro("protocoloDocumentoCobranca",protocoloDocumentoCobranca);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioEmitirProtocoloDocumentoCobranca.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		try {
			
			retorno = processarExibicaoRelatorio(relatorioEmitirProtocoloDocumentoCobranca,
					tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
			
		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}
