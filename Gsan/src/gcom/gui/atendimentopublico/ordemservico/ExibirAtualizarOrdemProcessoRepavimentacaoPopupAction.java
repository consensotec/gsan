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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0503]Tramitar Conjunto de Registro de Atendimento
 * 
 * @author Ana Maria		
 * @date 10/01/2007
 */
public class ExibirAtualizarOrdemProcessoRepavimentacaoPopupAction extends GcomAction {
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
		
		// Seta a a��o de retorno
		ActionForward retorno = actionMapping.findForward("exibirAtualizarOrdemProcessoRepavimentacaoPopUp");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarOrdemProcessoRepavimentacaoPopUpActionForm form = (AtualizarOrdemProcessoRepavimentacaoPopUpActionForm) actionForm;
			
		FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
		filtroPavimentoRua.adicionarParametro(new ParametroSimples(FiltroPavimentoRua.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));		
		Collection colecaoPavimentoRua = fachada.pesquisar(filtroPavimentoRua, PavimentoRua.class.getName());		
		httpServletRequest.setAttribute("colecaoPavimentoRua", colecaoPavimentoRua);
	
	    Collection collOrdemServicoPavimento = (Collection) sessao.getAttribute("collOrdemServicoPavimento");	    
	  
	   	//Perguntar se posso encontrar apenas pelo numero da Os ?
       	String numeroOS = null;
       	Integer idOrdemServico = null;
        if (httpServletRequest.getParameter("numeroOS") != null) {
        	numeroOS = httpServletRequest.getParameter("numeroOS");        	
        	idOrdemServico= Util.converterStringParaInteger(numeroOS);   
        	sessao.setAttribute("numeroOS",idOrdemServico);
		}else{
			idOrdemServico = (Integer) sessao.getAttribute("numeroOS");
		}
        
        SistemaParametro sistemaParametro = null; 
        sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        if(form.getNomeEmpresa() == null || form.getNomeEmpresa().equals("")){
        	
        	form.setNomeEmpresa(sistemaParametro.getNomeAbreviadoEmpresa());
        }
        OrdemServico os = fachada.pesquisarOrdemServico(idOrdemServico);
        
        OrdemServicoPavimento ordemServicoPavimento = null;
		boolean desabilitaBotaoAlterar = true;
		boolean habilitaCampos = false;
		if (sessao.getAttribute("collOrdemServicoPavimento") != null
			&& !sessao.getAttribute("collOrdemServicoPavimento").equals("")) {
			
				OSPavimentoRetornoHelper oSPavimentoRetornoHelper = null;
	
				Iterator iterator = collOrdemServicoPavimento.iterator();
				
				while (iterator.hasNext()) {
				
					oSPavimentoRetornoHelper = (OSPavimentoRetornoHelper) iterator.next();
				
					
					if(oSPavimentoRetornoHelper.getOrdemServico().getId().equals(os.getId())){	
						//1.3.4.1.	Dados da Tela
						ordemServicoPavimento = oSPavimentoRetornoHelper.getOrdemServicoPavimento();	
						
						sessao.setAttribute("ordemServicoPavimento", ordemServicoPavimento);
						
						//1.3.4.1.1
						if ( ordemServicoPavimento.getPavimentoRuaRetorno() != null ) {
							//exibir com os dados de retorno ja cadastrados, desabilitar o botao "alterar".
							form.setDataExecucao(Util.formatarData(ordemServicoPavimento.getDataExecucao()));
							form.setIdPavimentoRuaRet(ordemServicoPavimento.getPavimentoRuaRetorno().getId().toString());							
							form.setAreaPavimentoRuaRet(Util.formatarMoedaReal(ordemServicoPavimento.getAreaPavimentoRuaRetorno()));
							form.setObservacao(ordemServicoPavimento.getObservacao());
							desabilitaBotaoAlterar = false;
							habilitaCampos = true;
							sessao.setAttribute("habilitaAtualizar", "true");
							
						} else {
							form.setDataExecucao(Util.formatarData(new Date()));
							form.setIdPavimentoRuaRet(ordemServicoPavimento.getPavimentoRua().getId().toString());
							form.setAreaPavimentoRuaRet(Util.formatarMoedaReal(ordemServicoPavimento.getAreaPavimentoRua()));
							form.setObservacao(ordemServicoPavimento.getObservacao());
							
						}
						
						/*
						if (httpServletRequest.getParameter("acao") != null 
								&& httpServletRequest.getParameter("acao").equals("bloquear")){
							
							sessao.removeAttribute("desabilitaBotaoAlterar");
						}
						*/
						
						if(sessao.getAttribute("desabilitaBotaoAlterar") != null 
								&& sessao.getAttribute("desabilitaBotaoAlterar").equals("true")){
							
							sessao.removeAttribute("desabilitaBotaoAlterar");
						}
						
						if ( httpServletRequest.getParameter("acao") == null && desabilitaBotaoAlterar && (sessao.getAttribute("desabilitaBotaoAlterar") == null || sessao.getAttribute("desabilitaBotaoAlterar").equals("false"))) {
							sessao.setAttribute("desabilitaBotaoAlterar", "false");
						
						} else if (httpServletRequest.getParameter("acao") != null 
								&& httpServletRequest.getParameter("acao").equals("bloquear")){
							
							sessao.setAttribute("desabilitaBotaoAlterar", "true");
						}
						
						if(httpServletRequest.getParameter("habilitaConfirmar") != null 
								&& httpServletRequest.getParameter("habilitaConfirmar").equals("true") ){
							
							sessao.setAttribute("habilitaAtualizar", "true");
						}else{
							if (!habilitaCampos) {
								sessao.removeAttribute("habilitaAtualizar");
							}
							httpServletRequest.setAttribute("ordemServicoPavimento", ordemServicoPavimento);
							sessao.setAttribute("ordemServicoPavimento", ordemServicoPavimento);
						}				
					}
				}
					
			}	 
			
			if (httpServletRequest.getParameter("page.offset") != null 
					&& !httpServletRequest.getParameter("page.offset").equals("")) {
				
	        	String numeroPagina = httpServletRequest.getParameter("page.offset");   
	        	form.setManterPaginaAux(numeroPagina);
			}

 
		return retorno;
	}
}
