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
package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOSReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioOSFiscalizacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
 * [SB0005] � Gerar Formul�rio em formato pdf
 * @author Vivianne Sousa
 * @date 02/06/2011
 */
public class GerarRelatorioOSFiscalizacaoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoOSFiscalizacaoGeradas = null;
		if (sessao.getAttribute("colecaoOSFiscalizacao") != null 
				&& !sessao.getAttribute("colecaoOSFiscalizacao").equals("")){

			colecaoOSFiscalizacaoGeradas = (Collection)sessao.getAttribute("colecaoOSFiscalizacao");
		}
		
		if(colecaoOSFiscalizacaoGeradas == null || colecaoOSFiscalizacaoGeradas.isEmpty()){
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		Integer idGrupoCobranca = null;
		if(httpServletRequest.getParameter("idGrupoCobranca") != null
				&& !httpServletRequest.getParameter("idGrupoCobranca").equals("null")
				&& !httpServletRequest.getParameter("idGrupoCobranca").equals("")){
			idGrupoCobranca = new Integer((String)httpServletRequest.getParameter("idGrupoCobranca"));
		}
		
		FiltroOSReferidaRetornoTipo filtroOSReferidaRetornoTipo = 
			new FiltroOSReferidaRetornoTipo(FiltroOSReferidaRetornoTipo.DESCRICAO);
		filtroOSReferidaRetornoTipo.adicionarParametro(new ParametroSimples(
			FiltroOSReferidaRetornoTipo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroOSReferidaRetornoTipo.adicionarParametro(new ParametroSimples(
			FiltroOSReferidaRetornoTipo.ID_SERVICO_TIPO_REFERENCIA,new Integer(2)));

		Collection colecaoOSReferidaRetornoTipo = Fachada.getInstancia().pesquisar(
				filtroOSReferidaRetornoTipo, OsReferidaRetornoTipo.class.getName());
		
		RelatorioOSFiscalizacao relatorioOSFiscalizacao = new RelatorioOSFiscalizacao(
			(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
		// Parte que vai mandar o relat�rio para a tela
		// cria uma inst�ncia da classe do relat�rio
		
		relatorioOSFiscalizacao.addParametro("colecaoOSFiscalizacaoGeradas", colecaoOSFiscalizacaoGeradas);
		relatorioOSFiscalizacao.addParametro("qtdeOSFiscalizacaoGeradas", colecaoOSFiscalizacaoGeradas.size());
		relatorioOSFiscalizacao.addParametro("idGrupoCobranca", idGrupoCobranca);
		relatorioOSFiscalizacao.addParametro("colecaoOSReferidaRetornoTipo", colecaoOSReferidaRetornoTipo);
		
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		relatorioOSFiscalizacao.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		
		try {
			retorno = processarExibicaoRelatorio(relatorioOSFiscalizacao,
				tipoRelatorio, httpServletRequest, httpServletResponse,	actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
	
		return retorno;
	}

}
