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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.NegativacaoCriterioCpfTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * Esta classe tem por finalidade validar as informa��es da 1� aba do processo de inser��o
 * de um Comando de Negativa��o
 *
 * @author Ana Maria
 * @date 06/11/2007
 */
public class InserirComandoNegativacaoDadosGeraisAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;
		
		//[FS0026] Verificar exist�ncia de comando para o negativador na data
		boolean existeComando = fachada.verificarExistenciaComandoNegativador(inserirComandoNegativacaoActionForm.getIdNegativador(),Util.converteStringParaDate(inserirComandoNegativacaoActionForm.getDataPrevista()));
	          
	    if(existeComando){
	    	throw new ActionServletException("atencao.existe_comando_negativado_data", "inserirComandoNegativacaPorCriterioWizardAction.do?voltar=ok&entrou=ok&action=exibirInserirComandoNegativacaoDadosGeraisAction"
    				,new Exception(), inserirComandoNegativacaoActionForm.getDataPrevista(),inserirComandoNegativacaoActionForm.getNomeNegativador());
	    }
		
        //Verificar exist�ncia Usu�rio 
        if(inserirComandoNegativacaoActionForm.getUsuario() != null && !inserirComandoNegativacaoActionForm.getUsuario().equals("")){        	
	        String usuario = inserirComandoNegativacaoActionForm.getUsuario();
	        	
	        	FiltroUsuario filtroUsuario = new FiltroUsuario();  	            
	        	filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, usuario));
	            
	            Collection colecaoUsuario = fachada.pesquisar(
	                    filtroUsuario,Usuario.class.getName());
	            
	            if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {	            	
	            	inserirComandoNegativacaoActionForm.setUsuario(""
							+ ((Usuario) ((List) colecaoUsuario).get(0)).getId());
	            	inserirComandoNegativacaoActionForm.setNomeUsuario(""
							+ ((Usuario) ((List) colecaoUsuario).get(0)).getNomeUsuario());
				} else {
					throw new ActionServletException("atencao.pesquisa.usuario.inexistente");
				}
        }
        
		//Verificar exist�ncia de Titularidades adicionadas
		Collection colecaoNegativacaoCriterioCpfTipo = (Collection)sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo"); 
		if(colecaoNegativacaoCriterioCpfTipo == null || colecaoNegativacaoCriterioCpfTipo.isEmpty()){
			throw new ActionServletException("atencao.campo.informado", null, "Titularidade do CPF/CNPJ da Negativa��o");
		}else{ //Verificar ordem e coincidente das Titularidade
			Integer tamanhoColecao =colecaoNegativacaoCriterioCpfTipo.size();
			if(tamanhoColecao.equals(1)){
				NegativacaoCriterioCpfTipo ncCpfTipo = (NegativacaoCriterioCpfTipo) Util.retonarObjetoDeColecao(colecaoNegativacaoCriterioCpfTipo);
				ncCpfTipo.setIndicadorCoincidente(new Short("2"));
			}
		}
        
		atualizaColecoesNaSessao( sessao, httpServletRequest);
	
        return retorno;
	}
	
	
	private void atualizaColecoesNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){
    	     	
		//colecaoNegativacaoCriterioCpfTipo
		if (sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo") != null
		&& !sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo").equals("")) {
		
			Collection colecaoNegativacaoCriterioCpfTipo = (Collection) sessao
			.getAttribute("colecaoNegativacaoCriterioCpfTipo");
			
			Integer tamanhoColecao = colecaoNegativacaoCriterioCpfTipo.size();
			if(!tamanhoColecao.equals(1)){		
				// cria as vari�veis para recuperar os par�metros do request e jogar
				// no objeto  NegativacaoCriterioCpfTipo
				String ordem = null;
				String coincidente = null;
				
				Integer qtdOrdem = 0;
				Integer qtdCoincidente = 0;
				
				Iterator iteratorNegativacaoCriterioCpfTipo = colecaoNegativacaoCriterioCpfTipo.iterator();
				
				while (iteratorNegativacaoCriterioCpfTipo.hasNext()) {
					NegativacaoCriterioCpfTipo negativacaoCriterioCpfTipo = 
						(NegativacaoCriterioCpfTipo) iteratorNegativacaoCriterioCpfTipo.next();
					
					Integer idTitularidade = negativacaoCriterioCpfTipo.getCpfTipo().getId();
	
					ordem = (String) httpServletRequest.getParameter("ordem" + idTitularidade);
					coincidente = (String) httpServletRequest.getParameter("coincidente" + idTitularidade);
					
					if((ordem == null || ordem.equals("")) && (coincidente == null || coincidente.equals(""))){
						throw new ActionServletException("atencao.informe_ordem");	
					}
					
					if (ordem != null && !ordem.equals("")) {
						qtdOrdem = qtdOrdem + 1;
						negativacaoCriterioCpfTipo.setNumeroOrdemSelecao(new Short(ordem));
					}
					
					if(coincidente != null && !coincidente.equals("")){
						qtdCoincidente = qtdCoincidente + 1;
						negativacaoCriterioCpfTipo.setIndicadorCoincidente(new Short("1"));	
					}else{
						negativacaoCriterioCpfTipo.setIndicadorCoincidente(new Short("2"));			
					}

				}
				
				if(qtdCoincidente.equals(tamanhoColecao) && !qtdOrdem.equals(0)){
					throw new ActionServletException("atencao.nao_informe_oredem");						
				}
				if(!qtdCoincidente.equals(tamanhoColecao) && !qtdOrdem.equals(tamanhoColecao)){
					throw new ActionServletException("atencao.informe_ordem");				
				}
			}			
		}	        	
    }


}
