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
package gsan.gui.cobranca.spcserasa;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.ClienteTipo;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.cliente.FiltroClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroClienteTipo;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.FiltroSubCategoria;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.imovel.Subcategoria;
import gsan.cobranca.CobrancaSituacao;
import gsan.cobranca.CobrancaSituacaoTipo;
import gsan.cobranca.FiltroCobrancaSituacao;
import gsan.cobranca.FiltroCobrancaSituacaoTipo;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os par�metros para realiza��o
 * da inser��o de um Comando de Negativa��o (Aba n� 03 - Dados do Im�vel) 
 *
 * @author Ana Maria
 * @date 06/11/2007
 */
public class ExibirAtualizarComandoNegativacaoDadosImovelAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("atualizarComandoNegativacaoDadosImovel");
        
    	Fachada fachada = Fachada.getInstancia();
    	
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	AtualizarComandoNegativacaoPorCriterioActionForm form = (AtualizarComandoNegativacaoPorCriterioActionForm) actionForm;
		
        //Pesquisa Cliente
        String idCliente = form.getIdCliente();
        if(idCliente != null && !idCliente.equals("")){
        	
        	FiltroCliente filtroCliente = new FiltroCliente();  
            
        	filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
            
            Collection colecaoCliente = fachada.pesquisar(
            		filtroCliente,Cliente.class.getName());
            
            if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
            	httpServletRequest.setAttribute("funcionalidadeEncontrada", "valor");
            	
            	form.setIdCliente(""
						+ ((Cliente) ((List) colecaoCliente).get(0)).getId());
            	form.setDescricaoCliente(""
						+ ((Cliente) ((List) colecaoCliente).get(0)).getNome());
			} else {
				httpServletRequest.setAttribute("funcionalidadeEncontrada","exception");
				form.setIdCliente(null);
				form.setDescricaoCliente("Cliente Inexistente");
			}
        }
        
        //Pesquisar Tipo Rela��o
        if(sessao.getAttribute("colecaoClienteRelacaoTipo") == null){
        	FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
    		
    		Collection colecaoClienteRelacaoTipo = fachada.pesquisar(filtroClienteRelacaoTipo,ClienteRelacaoTipo.class.getName());
    		
    		sessao.setAttribute("colecaoClienteRelacaoTipo", colecaoClienteRelacaoTipo);
        }
        
        if(form.getImovSitEspecialCobranca() == null){
        	//Im�vel com Sit. Especial de Cobran�a - exibir com op��o "Sim" selecionada    		
        	form.setImovSitEspecialCobranca(ConstantesSistema.CONFIRMADA);   
        }
        
        if(form.getImovSitCobranca() == null){
        	//Im�vel com Sit. de Cobran�a - exibir com op��o "N�o" selecionada    		
        	form.setImovSitCobranca(ConstantesSistema.NAO_CONFIRMADA);   
        }
        
        if(form.getIndicadorBaixaRenda() == null){
        	//CRC4496 - adicionado por Vivianne Sousa - analista:Adriana - 29/06/2010
        	//Im�vel com Baixa Renda - exibir com op��o "SIM" selecionada    		
        	form.setIndicadorBaixaRenda(ConstantesSistema.CONFIRMADA);   
        }
    	
       
        //Pesquisar Situacao Especial de Cobranca
        if(sessao.getAttribute("colecaoCobrancaoSituacaoTipo") == null){
        	FiltroCobrancaSituacaoTipo filtroCobrancaSituacaoTipo = new FiltroCobrancaSituacaoTipo();
        	filtroCobrancaSituacaoTipo.adicionarParametro(
        		new ParametroSimples(FiltroCobrancaSituacaoTipo.INDICADORUSO, ConstantesSistema.SIM));
    		
    		Collection cobrancaSituacaoTipo = 
    			fachada.pesquisar(filtroCobrancaSituacaoTipo,CobrancaSituacaoTipo.class.getName());
    		
    		sessao.setAttribute("colecaoCobrancaoSituacaoTipo", cobrancaSituacaoTipo);
        } 

        //Pesquisar Situacao Cobranca
        if(sessao.getAttribute("colecaoCobrancaoSituacao") == null){
        	FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
        	filtroCobrancaSituacao.adicionarParametro(
            		new ParametroSimples(FiltroCobrancaSituacao.INDICADOR_USO, ConstantesSistema.SIM));

    		Collection cobrancaSituacao = 
    			fachada.pesquisar(filtroCobrancaSituacao,CobrancaSituacao.class.getName());
    		
    		sessao.setAttribute("colecaoCobrancaoSituacao", cobrancaSituacao);
        }         
        
        
        //Pesquisar Situa��o da Liga��o de �gua 
        if(sessao.getAttribute("colecaoLigAguaSituacao") == null){
        	FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
    		
    		Collection colecaoLigAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao,LigacaoAguaSituacao.class.getName());
    		
    		sessao.setAttribute("colecaoLigAguaSituacao", colecaoLigAguaSituacao);
        } 
        
        //Pesquisar Situa��o da Liga��o de Esgoto
        if(sessao.getAttribute("colecaoLigEsgotoSituacao") == null){
        	FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
    		
    		Collection colecaoLigEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao,LigacaoEsgotoSituacao.class.getName());
    		
    		sessao.setAttribute("colecaoLigEsgotoSituacao", colecaoLigEsgotoSituacao);
        } 
        
        //Pesquisar SubCategoria
        if(sessao.getAttribute("colecaoSubcategoria") == null || 
        		(httpServletRequest.getParameter("atualizaListSubcategoria") != null && 
        				httpServletRequest.getParameter("atualizaListSubcategoria").equals("s")) ){
        	FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
    		
        	if (form.getIndicadorImovelCategoriaPublico() != null && form.getIndicadorImovelCategoriaPublico().equals("1")) {
        		filtroSubCategoria.adicionarParametro(new ParametroSimples(
        				FiltroSubCategoria.CATEGORIA_ID, Categoria.PUBLICO));
        	}
        	
    		Collection colecaoSubcategoria = fachada.pesquisar(filtroSubCategoria,Subcategoria.class.getName());
    		
    		sessao.setAttribute("colecaoSubcategoria", colecaoSubcategoria);
        }
        
        //Pesquisar Perfil do Im�vel
        if(sessao.getAttribute("colecaoPerfilImovel") == null){
        	FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
    		
    		Collection colecaoPerfilImovel = fachada.pesquisar(filtroImovelPerfil,ImovelPerfil.class.getName());
    		
    		sessao.setAttribute("colecaoPerfilImovel", colecaoPerfilImovel);
        }   
        
        //Pesquisar Tipo do Cliente
        if(sessao.getAttribute("colecaoTipoCliente") == null){
        	FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
    		
    		Collection colecaoTipoCliente = fachada.pesquisar(filtroClienteTipo,ClienteTipo.class.getName());
    		
    		sessao.setAttribute("colecaoTipoCliente", colecaoTipoCliente);
        }        
    		
		//Caso informe o id da simula��o, os campos da Aba 5 - Dados da Localiza��o devem ser desabilitados
        if(form.getIdComandoSimulado() != null && !form.getIdComandoSimulado().equals("")){
        	form.setIdCliente(null);
        	form.setDescricaoCliente(null);
        	form.setTipoRelacao(null);
        	form.setImovSitEspecialCobranca(null);
        	form.setImovSitCobranca(null);
        	form.setLigacaoAguaSituacao(null);
        	form.setLigacaoEsgotoSituacao(null);
        	form.setSubCategoria(null);
        	form.setPerfilImovel(null);
        	form.setTipoCliente(null);       	
        	httpServletRequest.setAttribute("desabilitar", "ok");
        }
    	return retorno;
    }    	   

}
