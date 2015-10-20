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
package gcom.gui.atendimentopublico.registroatendimento.popprocedimentooperacionalpadrao;

import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC1258] Consultar Procedimento Operacional Padr�o
 * @author Am�lia Pessoa
 * @date 16/12/2011
 */
public class ExibirFiltrarProcedimentoOperacionalPadraoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarProcedimentoOperacionalPadrao");
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltrarProcedimentoOperacionalPadraoActionForm filtrarProcedimentoOperacionalPadraoActionForm = (FiltrarProcedimentoOperacionalPadraoActionForm) actionForm;
		
		// limpa os campos da sess�o
		if (httpServletRequest.getParameter("limpar") != null
				&& !httpServletRequest.getParameter("limpar").equals("")) {
			filtrarProcedimentoOperacionalPadraoActionForm.setIdEspecificacao("");
			filtrarProcedimentoOperacionalPadraoActionForm.setIdTipoSolicitacao("");
			filtrarProcedimentoOperacionalPadraoActionForm.setIndicadorProcedimento("3");
			sessao.removeAttribute("colecaoArquivosReserva");
			sessao.removeAttribute("colecaoArquivos");
			
			
		}
		Boolean trocou = false;
        
        if (httpServletRequest.getParameter("trocou") != null ){
            trocou = (Boolean) httpServletRequest.getParameter("trocou").equals("sim");
        }
        
        adicionarEspecificacao(filtrarProcedimentoOperacionalPadraoActionForm, new SolicitacaoTipoEspecificacao(),
                sessao, fachada, trocou);
        
		// remove o parametro de retorno
		sessao.removeAttribute("retornarTela");

		return retorno;
	}
	
	/**
	 * [UC0401] Manter tipo de solicitacao com especifica��es
	 * Mostra os dados necess�rios para a inclus�o do novo RA
	 * @author bruno
	 * @date 13/04/2009
	 * @param atualizarAdicionarSolicitacaoEspecificacaoActionForm
	 * @param solicitacaoTipoEspecificacao
	 * @param sessao
	 */
	private void adicionarEspecificacao( 
			FiltrarProcedimentoOperacionalPadraoActionForm adicionarSolicitacaoEspecificacaoActionForm,
	        SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao,
	        HttpSession sessao,
	        Fachada fachada,
	        boolean trocou ){
	    
	    if ( trocou ){
	        if ( solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA() != null ){
	        	adicionarSolicitacaoEspecificacaoActionForm.setIdEspecificacao( "" + solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA().getId() );
	        } else {
	        	adicionarSolicitacaoEspecificacaoActionForm.setIdEspecificacao( "" );
	        	adicionarSolicitacaoEspecificacaoActionForm.setIdTipoSolicitacao( "" );
	        }
	    }
	    
	   FiltroSolicitacaoTipo filtro = new FiltroSolicitacaoTipo();
	   filtro.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA, 2 ) );
	   filtro.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.INDICADOR_USO, 1 ) );
	   filtro.setCampoOrderBy( "descricao" );
	   Collection<SolicitacaoTipo> colSolTip = fachada.pesquisar( filtro, SolicitacaoTipo.class.getName() );
	   
	   sessao.setAttribute( "colecaoTipoSolicitacao", colSolTip );                            
	
	   // Verificamos se o tipo de especifica��o j� foi informado
	   if ( solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA() != null ){
	       
	       // Pesquisamos qual o tipo de solicitacao desta especifica��o
	       filtro.limparCamposOrderBy();
	       filtro.limparListaParametros();
	       filtro.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.ID, solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA().getSolicitacaoTipo().getId() ) );
	       colSolTip = fachada.pesquisar( filtro, SolicitacaoTipo.class.getName() );
	       
	       SolicitacaoTipo solicitacaoTipo = ( SolicitacaoTipo ) Util.retonarObjetoDeColecao( colSolTip );                                
	       adicionarSolicitacaoEspecificacaoActionForm.setIdTipoSolicitacao( solicitacaoTipo.getId()+"" );
	   }
	   
	   Collection<SolicitacaoTipoEspecificacao> colSolTipEsp = new ArrayList();
	   
	   if (adicionarSolicitacaoEspecificacaoActionForm.getIdTipoSolicitacao()!=null &&
			   !adicionarSolicitacaoEspecificacaoActionForm.getIdTipoSolicitacao().equals( "" ) ){
	       FiltroSolicitacaoTipoEspecificacao filtro2 = new FiltroSolicitacaoTipoEspecificacao();
	       filtro2.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO, adicionarSolicitacaoEspecificacaoActionForm.getIdTipoSolicitacao() ) );
	       filtro2.setCampoOrderBy( "descricao" );
	       colSolTipEsp = fachada.pesquisar( filtro2, SolicitacaoTipoEspecificacao.class.getName() );
	   }
	   
	   sessao.setAttribute( "colecaoEspecificacao", colSolTipEsp );        
	}
}
