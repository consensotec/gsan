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
* Anderson Italo Felinto de Lima
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

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cobranca.MotivoNaoGeracaoDocumentoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioMotivoNaoGeracaoDocumentoCobranca;
import gcom.relatorio.cobranca.RelatorioMotivoNaoGeracaoDocumentoCobrancaAgrupadosPorTitulo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;


/**
 * @author Anderson Italo
 * @date 30/11/2009
 * Classe respons�vel pelo pr�-precessamento 
 * da chamada do Relatorio Motivo de n�o gera�ao de Documentos de Cobran�a
 * UC9999 Consultar Motivo da n�o Gera��o de Documento de Cobran�a
 */
public class GerarRelatorioMotivoNaoGeracaoDocumentoEventualAction extends ExibidorProcessamentoTarefaRelatorio{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		MotivoNaoGeracaoDocumentoActionForm form = (MotivoNaoGeracaoDocumentoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = null;
		Collection colecaoCobrancaAcaoAtividadeComando = null;
		
		if(form.getDescricaoLocalidade().equalsIgnoreCase("Localidade Inexistente") 
			|| form.getDescricaoSetorComercial().equalsIgnoreCase("Setor Comercial Inexistente")
				|| form.getDescricaoQuadra().equalsIgnoreCase("Quadra Inexistente")){
			throw new ActionServletException("atencao.campos_invalidos");
		}
		
		/*5.	Caso Contr�rio
		 * 5.1.	O sistema exibe os dados para o Comando
		 * (Chamada do Relatorio Motivo de n�o gera�ao de Documentos de Cobran�a)*/
		if ((httpServletRequest.getParameter("filtroPorComandoSintetico") != null 
				&& httpServletRequest.getParameter("filtroPorComandoSintetico").equals("true"))
				||(httpServletRequest.getParameter("filtroPorComandoAnalitico") != null 
				&& httpServletRequest.getParameter("filtroPorComandoAnalitico").equals("true"))){
			
			FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = new FiltroCobrancaAcaoAtividadeComando();
			
			if(form.getIdCobrancaAcaoAtividadeComando() != null && !form.getIdCobrancaAcaoAtividadeComando().equals("")){
				filtroCobrancaAcaoAtividadeComando.adicionarParametro(new ParametroSimples(
					FiltroCobrancaAcaoAtividadeComando.ID, new Integer(form.getIdCobrancaAcaoAtividadeComando())));
			}
				
			filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.CLIENTE);
			filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.CLIENTE_RELACAO_TIPO);
			filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.CLIENTE_SUPERIOR);
			filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.COBRANCA_ACAO);
			filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.COBRANCA_ATIVIDADE);
			filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.USUARIO);
			
			colecaoCobrancaAcaoAtividadeComando = fachada.pesquisar(filtroCobrancaAcaoAtividadeComando, CobrancaAcaoAtividadeComando.class.getName());
			
			cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando)Util.retonarObjetoDeColecao(colecaoCobrancaAcaoAtividadeComando);
		}
		
		Usuario usuario = (Usuario) httpServletRequest.getSession(false).getAttribute("usuarioLogado");
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		
		if(form.getIdCobrancaAcaoAtividadeComando() != null && !form.getIdCobrancaAcaoAtividadeComando().equals("")){
			RelatorioMotivoNaoGeracaoDocumentoCobranca relatorio = new RelatorioMotivoNaoGeracaoDocumentoCobranca(usuario);
			relatorio.addParametro("cobrancaAcaoAtividadeComando", cobrancaAcaoAtividadeComando);
			relatorio.addParametro("tipoRelatorio", tipoRelatorio);
			relatorio.addParametro("indicadorCronograma", 2);
			relatorio.addParametro("form",form);
			
			if(httpServletRequest.getParameter("filtroPorComandoSintetico") != null 
					&& httpServletRequest.getParameter("filtroPorComandoSintetico").equals("true")){
				relatorio.addParametro("sintetico", 1);
			}else{
				relatorio.addParametro("sintetico", 2);
			}
			
			return processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		}else{
			
			RelatorioMotivoNaoGeracaoDocumentoCobrancaAgrupadosPorTitulo relatorio = new RelatorioMotivoNaoGeracaoDocumentoCobrancaAgrupadosPorTitulo(usuario);
			relatorio.addParametro("colCobrancaAcaoAtividadeComando", colecaoCobrancaAcaoAtividadeComando);
			relatorio.addParametro("tipoRelatorio", tipoRelatorio);
			relatorio.addParametro("form", form);
			
			if(httpServletRequest.getParameter("filtroPorComandoSintetico") != null 
					&& httpServletRequest.getParameter("filtroPorComandoSintetico").equals("true")){
				relatorio.addParametro("sintetico", 1);
			}else{
				relatorio.addParametro("sintetico", 2);
			}
			
			return processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		}
	}

}
