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
package gcom.gui.micromedicao;

import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
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
 * Action que define o pr�-processamento da p�gina de inserir Roteiro Empresa
 * 
 * @author Francisco Nascimento
 * @created 24/07/2007
 */
public class ExibirInserirRoteiroEmpresaAction extends GcomAction {
	/**
	 * Este caso de uso permite a inclus�o de um novo roteiro empresa
	 * 
	 * [UC0038] Inserir Roteiro Empresa
	 * 
	 * 
	 * @author Francisco Nascimento
	 * @date 24/07/2007
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

        ActionForward retorno = actionMapping.findForward("inserirRoteiroEmpresa");
        InserirRoteiroEmpresaActionForm inserirRoteiroEmpresaActionForm = (InserirRoteiroEmpresaActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);       
        
        //Pesquisando empresas
        if (sessao.getAttribute("colecaoEmpresa") == null){
        	
        	FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEsferaPoder.DESCRICAO);
        	filtroEmpresa.setConsultaSemLimites(true);
        	
        	filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO,
        			ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, 
        			Empresa.class.getName());
        	
        	if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "EMPRESA");
        	}
        	
        	sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
        }
        //Fim de pesquisando empresas
        
        //Pesquisando faturamento grupo
        if (sessao.getAttribute("colecaoFaturamentoGrupo") == null){
        	
			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(
					FiltroFaturamentoGrupo.DESCRICAO);

			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoFaturamentoGrupo = fachada.pesquisar(
					filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

			sessao.setAttribute("colecaoFaturamentoGrupo",
					colecaoFaturamentoGrupo);
        }
        // Fim de pesquisa de Faturamento Grupo
                      
        String carregarQuadras = httpServletRequest.getParameter("carregarQuadras");
        String removerSetor = httpServletRequest.getParameter("removerSetor");
        
        Collection colecaoSetorComercial = (Collection) sessao.getAttribute("colecaoSetorComercial");        
        String idLocalidade = inserirRoteiroEmpresaActionForm.getIdLocalidade();
        
        // Pesquisando Setor Comercial
		if (idLocalidade != null
				&& !idLocalidade.trim().equalsIgnoreCase("")) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			
			// Adiciona o id da localidade que est� no formul�rio para
			// compor a pesquisa.
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna cole��o de setor comercial
			colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial,
					SetorComercial.class.getName());

			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {			
				sessao.setAttribute("colecaoSetorComercial",
						colecaoSetorComercial);
				sessao.setAttribute("colecaoSetoresSelecionados",
						new ArrayList());					
				
			} 
		} 
		if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {
			sessao.setAttribute("colecaoSetorComercial", new ArrayList());			
		}
		
		// Preencher lista de setores selecionados
		String setoresSelecionados[] = inserirRoteiroEmpresaActionForm.getIdSetorComercialSelecionados();
		Collection colecaoSetoresSelecionados = (Collection) sessao.getAttribute("colecaoSetoresSelecionados");
		if (colecaoSetoresSelecionados == null){
			colecaoSetoresSelecionados = new ArrayList();
			sessao.setAttribute("colecaoSetoresSelecionados", colecaoSetoresSelecionados);			
		}
		
		ArrayList quadras = (ArrayList) sessao.getAttribute("colecaoQuadras");
		
		if (setoresSelecionados != null && setoresSelecionados.length > 0){
			if (removerSetor != null && !removerSetor.equalsIgnoreCase("")){			
				for (int i = 0; i < setoresSelecionados.length; i++) {
					Iterator iter = colecaoSetoresSelecionados.iterator();
					while (iter.hasNext()) {
						SetorComercial setor = (SetorComercial) iter.next();
						if (setor.getId().intValue() == Integer.parseInt(setoresSelecionados[i])){
							colecaoSetorComercial.add(setor);
							colecaoSetoresSelecionados.remove(setor);
							break;
						}
					}
					// situa��o onde est� sendo removido algum setor selecionados,
					// ent�o deve-se provocar o carregar de quadras para atualizar
					// isso s� ser� preciso se j� tiver sido carregadas as quadras
					if(quadras != null && quadras.size() > 0){
						carregarQuadras = "Sim";						
					}
				}
			} else if (carregarQuadras != null && !carregarQuadras.equals("")){
				//colecaoSetoresSelecionados = new ArrayList();
				for (int i = 0; i < setoresSelecionados.length; i++) {
					Iterator iter = colecaoSetorComercial.iterator();
					while (iter.hasNext()) {
						SetorComercial setor = (SetorComercial) iter.next();
						if (setor.getId().intValue() == Integer.parseInt(setoresSelecionados[i])){
							colecaoSetoresSelecionados.add(setor);
							colecaoSetorComercial.remove(setor);
							break;
						}
					}
				}				
			}		
			sessao.setAttribute("colecaoSetorComercial", colecaoSetorComercial);			
			sessao.setAttribute("colecaoSetoresSelecionados", colecaoSetoresSelecionados);
		}
		
	    if (carregarQuadras != null && !carregarQuadras.equalsIgnoreCase("")){  			
			// Pesquisando quadras do setores comerciais selecionados
			String faturGrp = inserirRoteiroEmpresaActionForm.getFaturamentoGrupo();
			quadras = new ArrayList();
			if (faturGrp != null && !faturGrp.trim().equalsIgnoreCase("") && 
					colecaoSetoresSelecionados.size() > 0 && idLocalidade != null &&
					!idLocalidade.trim().equals("")){
				
				int[] idsSetores = new int[colecaoSetoresSelecionados.size()];
								
				int i = 0;
				for (Iterator iter = colecaoSetoresSelecionados.iterator(); iter.hasNext();) {
					SetorComercial setor = (SetorComercial) iter.next();
					idsSetores[i++] = setor.getId().intValue();					
				}
				Integer intFaturGrupo = new Integer(faturGrp);
				// Retorna quadras
				quadras = (ArrayList) fachada.pesquisarQuadrasPorSetorComercialFaturamentoGrupo(
						Integer.parseInt(idLocalidade), idsSetores, intFaturGrupo);

			} 
			sessao.setAttribute("colecaoQuadras", quadras);
        } 
	    if (quadras == null || quadras.isEmpty()){
	    	sessao.setAttribute("colecaoQuadras", new ArrayList());
	    }
	                  
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	//-------------- bt DESFAZER ---------------
        	
        	inserirRoteiroEmpresaActionForm.setIdLocalidade("");
        	inserirRoteiroEmpresaActionForm.setDescricaoLocalidade("");
        	inserirRoteiroEmpresaActionForm.setEmpresa("");
        	inserirRoteiroEmpresaActionForm.setFaturamentoGrupo("");
        	inserirRoteiroEmpresaActionForm.setIdLeiturista("");
        	inserirRoteiroEmpresaActionForm.setNomeLeiturista("");
        	inserirRoteiroEmpresaActionForm.setIdQuadraAdicionar(null);
    		sessao.setAttribute("colecaoSetorComercial", new ArrayList());
    		sessao.setAttribute("colecaoSetoresSelecionados", new ArrayList());
    		sessao.setAttribute("colecaoQuadras", new ArrayList());
    		sessao.setAttribute("quadrasRemovidas", new ArrayList());        
        	
        }
          
        //-------Parte que trata do c�digo quando o usu�rio tecla enter     
        String idDigitadoEnterLocalidade = inserirRoteiroEmpresaActionForm.getIdLocalidade();
    	if (idDigitadoEnterLocalidade != null &&
    			!idDigitadoEnterLocalidade.equalsIgnoreCase("")&&
    			Util.validarValorNaoNumerico(idDigitadoEnterLocalidade)){
			//Localidade n�o num�rico.
			httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Localidade ");		
		} else {
	        verificaExistenciaCodLocalidade(idDigitadoEnterLocalidade,inserirRoteiroEmpresaActionForm,
	       			httpServletRequest,fachada);			
		}

        String idDigitadoEnterLeiturista = inserirRoteiroEmpresaActionForm.getIdLeiturista();
    	if (idDigitadoEnterLeiturista != null &&
    			!idDigitadoEnterLeiturista.equalsIgnoreCase("")&&
    			Util.validarValorNaoNumerico(idDigitadoEnterLeiturista)){
			//Leiturista n�o num�rico.
			httpServletRequest.setAttribute("nomeCampo","idLeiturista");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Leiturista ");		
		} else {
	        verificaExistenciaCodLeiturista(idDigitadoEnterLeiturista,inserirRoteiroEmpresaActionForm,
	       			httpServletRequest,fachada);       			
		}
       //-------Fim de parte que trata do c�digo quando o usu�rio tecla enter
                          
        
        sessao.removeAttribute("caminhoRetornoTelaPesquisa");
		// DEFINI��O QUE IR� AUXILIAR O RETORNO DOS POPUPS
		sessao.setAttribute("UseCase", "INSERIRROTEIROEMPRESA");
        
       return retorno;
    }
    
    private void verificaExistenciaCodLocalidade(String idDigitadoEnterLocalidade, 
    											InserirRoteiroEmpresaActionForm inserirRoteiroEmpresaActionForm,
    											HttpServletRequest httpServletRequest,
    											Fachada fachada) {
    	
        //Verifica se o c�digo da Localidade foi digitado
        if (idDigitadoEnterLocalidade != null
				&& !idDigitadoEnterLocalidade.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterLocalidade) > 0) {
	        
	        //Recupera a localidade informada pelo usu�rio
	        Localidade localidadeEncontrada = fachada.pesquisarLocalidadeDigitada(new Integer(idDigitadoEnterLocalidade));
	        
	        //Caso a localidade informada pelo usu�rio esteja cadastrada no sistema
	        //Seta os dados da localidade no form
	        //Caso contr�rio seta as informa��es da localidade para vazio 
	        //e indica ao usu�rio que a localidade n�o existe 
	
			if (localidadeEncontrada != null) {
				//a localidade foi encontrada
				inserirRoteiroEmpresaActionForm.setIdLocalidade("" + (localidadeEncontrada.getId()));
				inserirRoteiroEmpresaActionForm
				.setDescricaoLocalidade(localidadeEncontrada.getDescricao());
				httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
				"true");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			} else {
				//a localidade n�o foi encontrada
				inserirRoteiroEmpresaActionForm.setIdLocalidade("");
				httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
						"exception");
				inserirRoteiroEmpresaActionForm
						.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
	
			}
        }

    }
    
    private void verificaExistenciaCodLeiturista(String idDigitadoEnterLeiturista, 
			InserirRoteiroEmpresaActionForm inserirRoteiroEmpresaActionForm,
			HttpServletRequest httpServletRequest,
			Fachada fachada) {
		
		//Verifica se o c�digo do Leiturista foi digitado
		if (idDigitadoEnterLeiturista != null
			&& !idDigitadoEnterLeiturista.trim().equals("")
			&& Integer.parseInt(idDigitadoEnterLeiturista) > 0) {
			
			//Recupera o leiturista informado pelo usu�rio
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.ID, idDigitadoEnterLeiturista));
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection leituristaEncontrado = fachada.pesquisar(filtroLeiturista,
					Leiturista.class.getName());
			
			//Caso o leiturista informado pelo usu�rio esteja cadastrado no sistema
			//Seta os dados do leiturista no form
			//Caso contr�rio seta as informa��es de leiturista para vazio 
			//e indica ao usu�rio que o leiturista n�o existe 
			
			if (leituristaEncontrado != null && leituristaEncontrado.size() > 0) {
				//leiturista foi encontrado
				Leiturista leiturista = (Leiturista) ((List) leituristaEncontrado).get(0); 
				inserirRoteiroEmpresaActionForm.setIdLeiturista("" + 
					leiturista.getId());
				if (leiturista.getFuncionario() != null){
					inserirRoteiroEmpresaActionForm.setNomeLeiturista(leiturista.getFuncionario().getNome());					
				} else if (leiturista.getCliente() != null){
					inserirRoteiroEmpresaActionForm.setNomeLeiturista(leiturista.getCliente().getNome());
				}
				httpServletRequest.setAttribute("idLeituristaEncontrado","true");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			} else {
				//o leiturista n�o foi encontrado
				inserirRoteiroEmpresaActionForm.setIdLeiturista("");
				inserirRoteiroEmpresaActionForm.setNomeLeiturista("LEITURISTA INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo","idLeiturista");
			}
		}
		
    }

    
}
 