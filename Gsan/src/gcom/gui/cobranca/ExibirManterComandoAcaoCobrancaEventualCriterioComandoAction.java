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
package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0244] Manter Comando de A��o de Conbran�a - Tipo de Comando Cronograma
 * @author Rafael Santos
 * @since 24/04/2006
 */
public class ExibirManterComandoAcaoCobrancaEventualCriterioComandoAction  extends GcomAction{
	
	/**
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
                .findForward("exibirManterComandoAcaoCobrancaEventualCriterioComando");

        ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm = (ManterComandoAcaoCobrancaDetalhesActionForm) actionForm;        
        
        //Mudar isso quando implementar a parte de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);
        Fachada fachada = Fachada.getInstancia();
        
		if(httpServletRequest.getParameter("cobrancaGrupo")== null){
			manterComandoAcaoCobrancaDetalhesActionForm.setCobrancaGrupo("-1");	
		}
		if(httpServletRequest.getParameter("gerenciaRegional")== null){
			manterComandoAcaoCobrancaDetalhesActionForm.setGerenciaRegional("-1");
		}
		if(httpServletRequest.getParameter("unidadeNegocio") == null){
			manterComandoAcaoCobrancaDetalhesActionForm.setUnidadeNegocio("-1");
		}
		if(httpServletRequest.getParameter("clienteRelacaoTipo") == null){
			manterComandoAcaoCobrancaDetalhesActionForm.setClienteRelacaoTipo("-1");
		}
        
        
        
        String idCobrancaAtividade = httpServletRequest.getParameter("idCobrancaAtividade");
        String idCobrancaAcao = httpServletRequest.getParameter("idCobrancaAcao");
        
		String anoMesContaInicial = manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoInicialConta(); 
		String anoMesContaFinal = manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoFinalConta();
		
		String anoMesVencimentoInicial = manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoVencimentoContaInicial();  
		String anoMesVencimentoFinal  = manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoVencimentoContaFinal();

		
		CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = null;
		if(sessao.getAttribute("cobrancaAcaoAtividadeComando") != null){
			cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando) sessao.getAttribute("cobrancaAcaoAtividadeComando");
			httpServletRequest.setAttribute("idCobrancaAcaoAtividadeComando", cobrancaAcaoAtividadeComando.getId().toString());
		}
		
		String idComandoSelecionado = httpServletRequest.getParameter("idComandoSelecionado");
		if(idComandoSelecionado != null){
			if(cobrancaAcaoAtividadeComando.getCobrancaCriterio() != null){
				cobrancaAcaoAtividadeComando.getCobrancaCriterio().setId(new Integer(idComandoSelecionado));
				sessao.setAttribute("cobrancaAcaoAtividadeComando",cobrancaAcaoAtividadeComando);
			}else{
				CobrancaCriterio cobrancaCriterio = new CobrancaCriterio();
				cobrancaCriterio.setId(new Integer(idComandoSelecionado));
				cobrancaAcaoAtividadeComando.setCobrancaCriterio(cobrancaCriterio);
				sessao.setAttribute("cobrancaAcaoAtividadeComando",cobrancaAcaoAtividadeComando);

				
			}
		}
			
			
		//id criterio de cobran�a
		//if(cobrancaAcaoAtividadeComando.getCobrancaCriterio() != null){
			//manterComandoAcaoCobrancaDetalhesActionForm.setIdCriterioCobranca(cobrancaAcaoAtividadeComando.getCobrancaCriterio().getId().toString());
		//}

		
		//id criterio de cobranca
		///if(manterComandoAcaoCobrancaDetalhesActionForm.getIdCriterioCobranca() != null 
			//	&& !manterComandoAcaoCobrancaDetalhesActionForm.getIdCriterioCobranca().equals("")){
			//httpServletRequest.setAttribute("idCriterioConbrancaSelecionado",manterComandoAcaoCobrancaDetalhesActionForm.getIdCriterioCobranca());	
		//}
		
		// [FS0012] - Verificar refer�ncia final menor que refer�ncia inicial
		fachada.validarAnoMesInicialFinalComandoAcaoCobranca(anoMesContaInicial,
				anoMesContaFinal);

		// [FS0014] - Verificar data final menos que data inicial
		fachada.verificarVencimentoContaComandoAcaoCobranca(
				anoMesVencimentoInicial, anoMesVencimentoFinal);
		
        
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
        	Collection colecaoCriterioCobranca = null;
        	
        	FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
        	filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID,idCobrancaAcao));
        	
        	Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao,CobrancaAcao.class.getName());

        	if(colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()){
        		
        		CobrancaAcao cobrancaAcao = (CobrancaAcao)colecaoCobrancaAcao.iterator().next();
        		manterComandoAcaoCobrancaDetalhesActionForm.setDescricaoAcaoCobranca(cobrancaAcao.getDescricaoCobrancaAcao());
        	}
        
        	FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
        	//filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterio.ID_COBRANCA_ACAO,idCobrancaAcao));
        	
        	colecaoCriterioCobranca = fachada.pesquisar(filtroCobrancaCriterio,CobrancaCriterio.class.getName());
        	
        	
        	if(colecaoCriterioCobranca == null || colecaoCriterioCobranca.isEmpty()){
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",
						null, "Tabela Cobran�a Crit�rio");
        	}
        	
            //carregar criterios de cobranca
            sessao.setAttribute("colecaoCriterioCobranca",colecaoCriterioCobranca);        	
        	
        }
        

        sessao.setAttribute("manterComandoAcaoCobrancaDetalhesActionForm",
        		manterComandoAcaoCobrancaDetalhesActionForm);
        return retorno;
    }

}
