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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.bean.InserirPagamentoViaCanetaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;


/**
 * Action respons�vel por inseri os pagamentos no sistema 
 *
 * @author Pedro Alexandre
 * @date 15/03/2006
 */
public class InserirPagamentosAction extends GcomAction {

   
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
     * @author Pedro Alexandre
     * @date 16/02/2006
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Cria a vari�vel de retorno 
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        //Cria uma inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //Cria uma inst�ncia da sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        Integer idPagamento = null;

        //Recupera o form 
        DynaValidatorActionForm pagamentoActionForm = (DynaValidatorActionForm) actionForm;
        
        //Recupera o tipo de inclus�o
        String tipoInclusao = (String) pagamentoActionForm.get("tipoInclusao");
                
        //Recupera o aviso banc�rio e pequisa o objeto no sistema
        String idAvisoBancario = (String)pagamentoActionForm.get("idAvisoBancario");
        FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
        filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
        filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
        AvisoBancario avisoBancario = (AvisoBancario)(fachada.pesquisar(filtroAvisoBancario,AvisoBancario.class.getName())).iterator().next();
        
        //Caso o tipo de inclus�o tenha sido manual
        if(tipoInclusao.equalsIgnoreCase("manual")){
        	
        	/*
        	 * Alterado por Raphael Rossiter em 26/09/2007
        	 * OBJ: Inserir manualmente mais de um pagamento por sessao.
        	 */
        	
        	Collection<Pagamento> colecaoPagamento = (Collection) sessao.getAttribute("colecaoPagamento");
        	
        	idPagamento = fachada.inserirPagamentos(colecaoPagamento, usuarioLogado, avisoBancario);
        	
        	
          //Caso o tipo de inclus�o seja por caneta �ptica 	 
        }else if(tipoInclusao.equalsIgnoreCase("caneta") || tipoInclusao.equalsIgnoreCase("ficha")){
        	
        	/*
        	 * Alterado por Raphael Rossiter em 30/10/2007
        	 */
        	
        	Collection<Pagamento> colecaoPagamentos = new ArrayList();
        	Collection<Devolucao> colecaoDevolucoes = new ArrayList();
        	
        	//Recupera a cole�a� de documentos da sess�o contendo o c�digo de barras e o valor do pagamento
            Collection<InserirPagamentoViaCanetaHelper> colecaoInserirPagamentoViaCanetaHelper = 
            (Collection<InserirPagamentoViaCanetaHelper>) sessao.getAttribute("colecaoInserirPagamentoViaCanetaHelper");
        	
            //[FS0006] - Verificar exist�ncia de documento na lista
            //Caso n�o exista nenhum documento na lista, levanta uma exce��o 
            //para o usu�rio indicando que nenhum documento foi informado
            if(colecaoInserirPagamentoViaCanetaHelper == null || colecaoInserirPagamentoViaCanetaHelper.isEmpty()){
            	throw new ActionServletException("atencao.documento_naoinformado");
            }
            
            //[FS0025] � Verificar valor do aviso banc�rio
            //Caso o valor calculado do aviso banc�rio seja maior que valor informado 
            if (avisoBancario.getValorArrecadacaoCalculado()
            		.compareTo(avisoBancario.getValorArrecadacaoInformado()) == 1 ){
            	throw new ActionServletException("atencao.soma_dos_valores_maior_informado");
            }
        	
        	for(InserirPagamentoViaCanetaHelper pagamentoViaCanetaHelper : colecaoInserirPagamentoViaCanetaHelper){
            	
            	colecaoPagamentos.addAll(pagamentoViaCanetaHelper.getColecaoPagamento());
            	
            	if (pagamentoViaCanetaHelper.getColecaoDevolucao() != null &&
            		!pagamentoViaCanetaHelper.getColecaoDevolucao().isEmpty()){
            		
            		colecaoDevolucoes.addAll(pagamentoViaCanetaHelper.getColecaoDevolucao());
            	}
        	}
        	
        	idPagamento = fachada.inserirPagamentosCodigoBarras(colecaoPagamentos, colecaoDevolucoes, 
        	usuarioLogado, avisoBancario);
        	
        }else{
        	////Caso o tipo de inclus�o n�o tenha sido informado, levanta uma exce��o para o usu�rio 
        	//indicando que o tipo de inclus�o n�o foi informado 
        	throw new ActionServletException("atencao.naoinformado",null, "Tipo de Inclus�o");
        }
        
        //Caso o retorno seja para a telade sucesso,
        //Monta a tela de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
        	
        	//Limpa a sess�o
            sessao.removeAttribute("colecaoFormaArrecadacao");
            sessao.removeAttribute("PagamentoActionForm");
            sessao.removeAttribute("colecaoDocumentoTipo");
            sessao.removeAttribute("colecaoPagamentos");
            sessao.removeAttribute("colecaoInserirPagamentoViaCanetaHelper");
        	
           /* montarPaginaSucesso(httpServletRequest, mensagemSucesso,
            "Inserir outro Pagamento", "exibirInserirPagamentosAction.do");*/
            
            
            montarPaginaSucesso(httpServletRequest, "Pagamento inserido com sucesso."
            		, "Inserir outro Pagamento",
                    "exibirInserirPagamentosAction.do?menu=sim",
                    "exibirAtualizarPagamentosAction.do?idRegistroInseridoAtualizar="
					+ idPagamento,
					"Atualizar Pagamento Inserido");

        
        
            
            
        }

        //Retorna o mapeamento contido na vari�vel retorno
        return retorno;
    }
}
