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
* Yara Taciane de Souza
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

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action que define o pr�-processamento da p�gina de filtrar do Movimento do Negativador
 * 
 * @author Yara Taciane de Souza
 * @created 21/01/2008
 */
public class ExibirFiltrarNegativadorMovimentoAction extends GcomAction {
	/**
	 * Este caso de uso permite o filtro de um  Movimento do Negativador
	 * 
	 * [UC0682] Filtrar Movimento do Negativador
	 * 
	 * 
	 * @author Yara Taciane de Souza
	 * @date 21/01/2008
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


        ActionForward retorno = actionMapping.findForward("filtrarNegativadorMovimento");
        
        FiltrarNegativadorMovimentoActionForm filtrarNegativadorMovimentoActionForm = (FiltrarNegativadorMovimentoActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoNegativador = (Collection) sessao.getAttribute("colecaoNegativador");

		if (colecaoNegativador == null) {

			FiltroNegativador filtroNegativador = new FiltroNegativador();			
			filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);			
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");				
			filtroNegativador.setConsultaSemLimites(true);

			colecaoNegativador = fachada.pesquisar(filtroNegativador,
					Negativador.class.getName());

			if (colecaoNegativador == null || colecaoNegativador.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"NEGATIVADOR");
			} else {
				sessao.setAttribute("colecaoNegativador", colecaoNegativador);
			}
		}
		
		if(httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equalsIgnoreCase("sim")) {
			filtrarNegativadorMovimentoActionForm.setTipoMovimento(ConstantesSistema.TODOS.toString());
			filtrarNegativadorMovimentoActionForm.setMovimento(ConstantesSistema.TODOS.toString());
			filtrarNegativadorMovimentoActionForm.setMovimentoRegistro(ConstantesSistema.TODOS.toString());
			filtrarNegativadorMovimentoActionForm.setMovimentoCorrigido(ConstantesSistema.TODOS.toString());
			
        	filtrarNegativadorMovimentoActionForm.setIdNegativador("" + ConstantesSistema.NUMERO_NAO_INFORMADO); 
        	filtrarNegativadorMovimentoActionForm.setTipoMovimento("");  
        	filtrarNegativadorMovimentoActionForm.setNumeroSequencialArquivo("");
        	filtrarNegativadorMovimentoActionForm.setDataProcessamentoFinal("");   
        	filtrarNegativadorMovimentoActionForm.setDataProcessamentoInicial("");        	   
        	filtrarNegativadorMovimentoActionForm.setArrayGerenciaRegional(null);
        	filtrarNegativadorMovimentoActionForm.setArrayMotivoRejeicao(null);
        	filtrarNegativadorMovimentoActionForm.setArrayUnidadeNegocio(null);
        	filtrarNegativadorMovimentoActionForm.setDescricaoEloPolo("");
        	filtrarNegativadorMovimentoActionForm.setDescricaoLocalidade("");
        	filtrarNegativadorMovimentoActionForm.setGerenciaRegional("");
        	filtrarNegativadorMovimentoActionForm.setIdEloPolo("");
        	filtrarNegativadorMovimentoActionForm.setIdLocalidade("");
        	filtrarNegativadorMovimentoActionForm.setMotivoRejeicao("");
        	filtrarNegativadorMovimentoActionForm.setUnidadeNegocio("");
        	sessao.removeAttribute("imovelEncontrado");
		}
		
		
//		 se tem id do Imovel entao pega o nome
        if (!"".equals(filtrarNegativadorMovimentoActionForm.getIdImovel()) && filtrarNegativadorMovimentoActionForm.getIdImovel() != null) {
        	
        	sessao.setAttribute("manterDadosSesao", "false");
        	
        	FiltroImovel filtroImovel = new  FiltroImovel();
            
            //coloca parametro no filtro
            filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(filtrarNegativadorMovimentoActionForm.getIdImovel())));
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);

            //pesquisa
            Collection coll = Fachada.getInstancia().pesquisar(filtroImovel, Imovel.class.getName());
            if (coll != null && !coll.isEmpty()) {
                //O Imovel foi encontrado
                //inserirImovelFiltrarActionForm.set("idImovel", ((Imovel) ((List) Imovels).get(0)).getId().toString());
            	filtrarNegativadorMovimentoActionForm.setNomeImovel(((Imovel) ((List) coll).get(0)).getInscricaoFormatada());
            	sessao.setAttribute("nomeImovel", ((Imovel) ((List) coll).get(0)).getInscricaoFormatada());
            	sessao.setAttribute("idImovel", filtrarNegativadorMovimentoActionForm.getIdImovel());
            	
            	filtrarNegativadorMovimentoActionForm.setImovelNaoEncontrado("false");
            	sessao.setAttribute("imovelEncontrado", true);
            } else {
            	filtrarNegativadorMovimentoActionForm.setImovelNaoEncontrado("true");
            	filtrarNegativadorMovimentoActionForm.setNomeImovel("Im�vel Inexistente");
            	sessao.removeAttribute("imovelEncontrado");
            }
        } else {
        	filtrarNegativadorMovimentoActionForm.setNomeImovel("");
        	sessao.removeAttribute("idImovel");
        	sessao.removeAttribute("nomeImovel");
        	sessao.removeAttribute("imovelEncontrado");
        }
		
		
				
		
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	//-------------- bt DESFAZER ---------------        	
        	filtrarNegativadorMovimentoActionForm.setIdNegativador("" + ConstantesSistema.NUMERO_NAO_INFORMADO); 
        	filtrarNegativadorMovimentoActionForm.setTipoMovimento("");  
        	filtrarNegativadorMovimentoActionForm.setNumeroSequencialArquivo("");
        	filtrarNegativadorMovimentoActionForm.setDataProcessamentoFinal("");   
        	filtrarNegativadorMovimentoActionForm.setDataProcessamentoInicial("");
        	filtrarNegativadorMovimentoActionForm.setMovimento("");
        	filtrarNegativadorMovimentoActionForm.setMovimentoRegistro("");    
            sessao.removeAttribute("caminhoRetornoTelaPesquisa");
        }        
     

        
        if (sessao.getAttribute("collGerenciaRegional") == null){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroGerenciaRegional.setCampoOrderBy("nome");
			Collection collGerenciaRegional = Fachada.getInstancia().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			sessao.setAttribute("collGerenciaRegional", collGerenciaRegional);
		}
		
		if (sessao.getAttribute("collUnidadeNegocio") == null){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroUnidadeNegocio.setCampoOrderBy("nome");
			Collection collUnidadeNegocio = Fachada.getInstancia().pesquisar(filtroUnidadeNegocio , UnidadeNegocio.class.getName());
			sessao.setAttribute("collUnidadeNegocio", collUnidadeNegocio);
		}
		
		if (filtrarNegativadorMovimentoActionForm.getIdEloPolo() != null && !filtrarNegativadorMovimentoActionForm.getIdEloPolo().trim().equals("")){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, filtrarNegativadorMovimentoActionForm.getIdEloPolo()));
			
			Collection collEloPolo = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (collEloPolo != null && !collEloPolo.isEmpty()) {
				if (((Localidade) ((List) collEloPolo).get(0))
						.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.localidade_inativa",
							null, "" + ((Localidade) ((List) collEloPolo).get(0)).getId());
				}

				filtrarNegativadorMovimentoActionForm.setIdEloPolo(((Localidade) ((List) collEloPolo)
								.get(0)).getId().toString());
				filtrarNegativadorMovimentoActionForm.setDescricaoEloPolo(((Localidade) ((List) collEloPolo)
								.get(0)).getDescricao());
			} else {
				httpServletRequest.setAttribute("corEloPolo","exception");
				filtrarNegativadorMovimentoActionForm.setDescricaoEloPolo(ConstantesSistema.CODIGO_ELO_POLO_INEXISTENTE);
				filtrarNegativadorMovimentoActionForm.setIdEloPolo("");
			}
		}
		
		if (filtrarNegativadorMovimentoActionForm.getIdLocalidade() != null && !filtrarNegativadorMovimentoActionForm.getIdLocalidade().trim().equals("")){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, filtrarNegativadorMovimentoActionForm.getIdLocalidade()));
			
			Collection collLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (collLocalidade != null && !collLocalidade.isEmpty()) {
				if (((Localidade) ((List) collLocalidade).get(0))
						.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.localidade_inativa",
							null, "" + ((Localidade) ((List) collLocalidade).get(0)).getId());
				}

				filtrarNegativadorMovimentoActionForm.setIdLocalidade(((Localidade) ((List) collLocalidade)
								.get(0)).getId().toString());
				filtrarNegativadorMovimentoActionForm.setDescricaoLocalidade(((Localidade) ((List) collLocalidade)
								.get(0)).getDescricao());
			} else {
				httpServletRequest.setAttribute("corLocalidade","exception");
				filtrarNegativadorMovimentoActionForm.setDescricaoLocalidade(ConstantesSistema.CODIGO_LOCALIDADE_INEXISTENTE);
				filtrarNegativadorMovimentoActionForm.setIdLocalidade("");
			}
		}
		
		if ( filtrarNegativadorMovimentoActionForm.getMovimentoRegistro() != null && 
			filtrarNegativadorMovimentoActionForm.getMovimentoRegistro().equals("2") &&	
			filtrarNegativadorMovimentoActionForm.getIdNegativador() != null && 
			!filtrarNegativadorMovimentoActionForm.getIdNegativador().equals("") && 
			!filtrarNegativadorMovimentoActionForm.getIdNegativador().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			if (sessao.getAttribute("collMotivoRejeicao") == null){
				
				FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
				filtroNegativadorRetornoMotivo.adicionarParametro(
					new ParametroSimples(FiltroNegativadorRetornoMotivo.INDICADOR_REGISTRO_ACEITO, new Short("2") ));
				filtroNegativadorRetornoMotivo.adicionarParametro(
					new ParametroSimples(FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR, new Integer(filtrarNegativadorMovimentoActionForm.getIdNegativador())));
				filtroNegativadorRetornoMotivo.adicionarParametro(
					new ParametroSimples(FiltroNegativadorRetornoMotivo.INDICADOR_USO, new Short("1") ));
				filtroNegativadorRetornoMotivo.setCampoOrderBy("descricaoRetornocodigo");
				
				Collection collMotivoRejeicao = 
					this.getFachada().pesquisar(filtroNegativadorRetornoMotivo , NegativadorRetornoMotivo.class.getName());
				
				sessao.setAttribute("collMotivoRejeicao", collMotivoRejeicao);
			}

		}else{
			sessao.removeAttribute("collMotivoRejeicao");
		}
        
        
        
        
        sessao.removeAttribute("caminhoRetornoTelaPesquisa");
	
		
        return retorno;
    }
    
   

}
 