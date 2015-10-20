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
package gcom.gui.cobranca;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.batch.Processo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [UC1233] - Encerrar Ordem de Servico de Visita de Cobran�a
 * 
 * @author Hugo Azevedo
 * @date 23/09/2011
 */
public class EncerrarOrdensVisitaCobrancaAction extends GcomAction {
 
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		EncerrarOrdensVisitaCobrancaActionForm form = (EncerrarOrdensVisitaCobrancaActionForm) actionForm;
		
		Collection<OrdemServico> colecaoOrdemServico = new ArrayList<OrdemServico>();
		
		String idGrupo = form.getIdGrupo();
		String mesAno = form.getMesano();
		
		//Indicador de confirma��o
		String confirmado = httpServletRequest.getParameter("confirmado");
		
		//Retirando a barra do mes/ano
		mesAno = Util.formatarMesAnoParaAnoMesSemBarra(mesAno);
		
		if( confirmado == null	|| !confirmado.trim().equalsIgnoreCase("ok")){
			//Busca das Ordens de Servico de Visita de Cobranca
			colecaoOrdemServico = fachada.obterColecaoOrdemServicoVisitaCobranca(idGrupo,mesAno);
			
			
			if(colecaoOrdemServico.size() > 0){
	
				httpServletRequest.setAttribute("caminhoActionConclusao",
						"/gsan/encerrarOrdensVisitaCobrancaAction.do");
				
				sessao.setAttribute("colecaoOrdemServico", colecaoOrdemServico);
				
				//Tela de confirma��o
				return montarPaginaConfirmacao(
						"atencao.encerrar_os_visita_cobranca.confirmar",
						httpServletRequest, actionMapping,colecaoOrdemServico.size()+"");
			}
			else{
				throw new ActionServletException("atencao.nenhuma_os_encontrada");
			}
		}
	
		colecaoOrdemServico = (Collection<OrdemServico>)sessao.getAttribute("colecaoOrdemServico");
		sessao.removeAttribute("colecaoOrdemServico");
		
		Map parametros = new HashMap();
		parametros.put("colecaoOrdemServico", colecaoOrdemServico);
	
		
		fachada.inserirProcessoIniciadoParametrosLivres(parametros, 
          		Processo.ENCERRAR_OS_ORDEM_VISITA, usuarioLogado);
		
		
		//montando p�gina de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Encerramento do Comando de Cobran�a por Empresa Enviado para Processamento", 
			"Voltar",
			"exibirEncerrarOrdensVisitaCobrancaAction.do?menu=sim");
		
		return retorno;
	}
}