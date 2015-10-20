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
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.bean.PesquisarQtdeRotasSemCriteriosParaAcoesCobranca;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0243] Inserir Comando de A��o de Conbran�a - Tipo de Comando Cronograma
 * @author Rafael Santos
 * @since 24/01/2006
 */
public class InserirComandoAcaoCobrancaCronogramaAction  extends GcomAction{
	
	/**
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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("telaSucesso");

        //Mudar isso quando implementar a parte de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
        
        InserirComandoAcaoCobrancaCronogramaActionForm form = (InserirComandoAcaoCobrancaCronogramaActionForm) actionForm;
        
		// -- Validar se todas as rotas do grupo informado possui um criterio definido para a a�ao de cobran�a
        Collection<Integer> idsAcoesCobranca = new ArrayList<Integer>();
        idsAcoesCobranca.add(new Integer(form.getCobrancaAcao()));
        
        PesquisarQtdeRotasSemCriteriosParaAcoesCobranca filtro = new PesquisarQtdeRotasSemCriteriosParaAcoesCobranca();
        filtro.setIdGrupoCobranca(new Integer(form.getCobrancaGrupo()));
        filtro.setIdsAcoesCobranca(idsAcoesCobranca);
        
        Integer qtdeRotas = fachada.pesquisarQuantidadeRotasPorGrupoCobranca(filtro);
		if (qtdeRotas == null || qtdeRotas.intValue() == 0) { 
			throw new ActionServletException("atencao.cobranca_grupo_sem_rotas", null, form.getCobrancaGrupo());
		}
        
        Integer qtdeRotasSemCriterios = fachada.pesquisarQtdeRotasSemCriteriosParaAcoesCobranca(filtro);
		if (qtdeRotasSemCriterios != null && qtdeRotasSemCriterios.intValue() > 0) { 
			throw new ActionServletException("atencao.rotas.sem.criterio.para.acao.cobranca");
		}
        // ---------
        
        if(sessao.getAttribute("cobrancaAcaoAtividadeCronograma") != null){
        	
        	CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = 
        		(CobrancaAcaoAtividadeCronograma) sessao.getAttribute("cobrancaAcaoAtividadeCronograma");
        	
        	cobrancaAcaoAtividadeCronograma.setComando(new Date());
        	
        	
            //------------ REGISTRAR TRANSA��O ----------------
            RegistradorOperacao registradorOperacao = new RegistradorOperacao(
    				Operacao.OPERACAO_INSERIR_COMANDO_ACAO_COBRANCA_CRONOGRAMA,
    				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
    						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
            
            Operacao operacao = new Operacao();
            operacao.setId(Operacao.OPERACAO_INSERIR_COMANDO_ACAO_COBRANCA_CRONOGRAMA);

            OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
            operacaoEfetuada.setOperacao(operacao);
            cobrancaAcaoAtividadeCronograma.setOperacaoEfetuada(operacaoEfetuada);
            cobrancaAcaoAtividadeCronograma.adicionarUsuario(Usuario.USUARIO_TESTE, 
     		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
            registradorOperacao.registrarOperacao(cobrancaAcaoAtividadeCronograma);
            //------------ REGISTRAR TRANSA��O ----------------  
        	
        	//atualizar a data e hora do comandoi
        	fachada.atualizar(cobrancaAcaoAtividadeCronograma);
        	
        	
        }
        CobrancaGrupo cobrancaGrupo = null;
        if(sessao.getAttribute("cobrancaGrupo") != null ){
        	cobrancaGrupo = (CobrancaGrupo) sessao.getAttribute("cobrancaGrupo");
        }
        CobrancaAcao cobrancaAcao = null;
		if(sessao.getAttribute("cobrancaAcao") != null ){
			cobrancaAcao  = (CobrancaAcao)sessao.getAttribute("cobrancaAcao");
		}
		
		CobrancaAtividade cobrancaAtividade = null;
		if(sessao.getAttribute("cobrancaAtividade") != null ){
			cobrancaAtividade  = (CobrancaAtividade)sessao.getAttribute("cobrancaAtividade");
		}

        montarPaginaSucesso(httpServletRequest, "A A��o " + cobrancaAcao.getDescricaoCobrancaAcao()  
                + " do grupo "  
                + cobrancaGrupo.getDescricao()
                + ", para a atividade " 
                + cobrancaAtividade.getDescricaoCobrancaAtividade() 
                + " comandada com sucesso.", "Inserir outro Comando de A��o de Cobran�a",
                "exibirInserirComandoAcaoCobrancaAction.do?menu=sim");

   		if(sessao.getAttribute("colecaoCobrancaGrupo") != null ){
   			sessao.removeAttribute("colecaoCobrancaGrupo");
   		}
		if(sessao.getAttribute("cobrancaGrupo") != null ){
			sessao.removeAttribute("cobrancaGrupo");
		}
		if(sessao.getAttribute("colecaoCobrancaGrupoCronogramaMensal") != null ){
			sessao.removeAttribute("colecaoCobrancaGrupoCronogramaMensal");
		}
		if(sessao.getAttribute("colecaoCobrancaAcao") != null ){
			sessao.removeAttribute("colecaoCobrancaAcao");
		}
		if(sessao.getAttribute("cobrancaAcao") != null ){
			sessao.removeAttribute("cobrancaAcao");
		}
		if(sessao.getAttribute("cobrancaAcaoCronogama") != null ){
			sessao.removeAttribute("cobrancaAcaoCronogama");
		}
		if(sessao.getAttribute("colecaoCobrancaAtividade") != null ){
			sessao.removeAttribute("colecaoCobrancaAtividade");
		}
		if(sessao.getAttribute("cobrancaAcaoAtividadeCronograma") != null ){
			sessao.removeAttribute("cobrancaAcaoAtividadeCronograma");
		}
		if(sessao.getAttribute("cobrancaAtividade") != null ){
			sessao.removeAttribute("cobrancaAtividade");
		}
        return retorno;
    }

}
