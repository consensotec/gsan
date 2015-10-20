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
package gcom.gui.relatorio.faturamento;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Fl�vio Cordeiro
 */
public class ExibirFiltrarRelatorioAnaliticoFaturamentoAction extends GcomAction {

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
	
	private Collection colecaoPesquisa = null;

	private String localidadeID = null;

	private String setorComercialCD = null;

	private String setorComercialID = null;

	private String quadraNM = null;
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirFiltrarRelatorioAnaliticoFaturamento");

        Fachada fachada = Fachada.getInstancia();

        RelatorioAnaliticoFaturamentoActionForm form = (RelatorioAnaliticoFaturamentoActionForm) actionForm;
        
        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

//-----------Parte 1 Grupo de Faturamento
//-------------FATURAMENTO_GRUPO com FTGR_AMREFERENCIA = mes/ano informado + 1
        
   //s� recarrega o combo dos grupos caso a data mude
        if(form != null && form.getIdGrupoFaturamento() != null && !form.getIdGrupoFaturamento().equalsIgnoreCase("-1")){
		        
		        //Carrega Cole�ao de Faturamento Grupos
		        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		        filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
		                FiltroFaturamentoGrupo.INDICADOR_USO,
		                ConstantesSistema.INDICADOR_USO_ATIVO));
		        filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, 
		        		form.getIdGrupoFaturamento()));
		        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
		        Collection faturamentoGrupos = fachada.pesquisar(
		                filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		        
		        FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)faturamentoGrupos.iterator().next();
		        
        	   	Integer anoMes =faturamentoGrupo.getAnoMesReferencia();
	        	sessao.setAttribute("anoMes", anoMes);
	        	//form.setMesAno(Util.formatarAnoMesParaMesAno(faturamentoGrupo.getAnoMesReferencia()));
			
        }else{
        	if(sessao.getAttribute("faturamentoGrupos") == null){
//        		Carrega Cole�ao de Faturamento Grupos
		        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		        filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
		                FiltroFaturamentoGrupo.INDICADOR_USO,
		                ConstantesSistema.INDICADOR_USO_ATIVO));
		        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
		        
		        
		        Collection faturamentoGrupos = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		        sessao.setAttribute("faturamentoGrupos", faturamentoGrupos);
        		form.setIndicadorLocalidadeInformatizada("1");
        	}
        }

//-----------Parte 2 Pesqusia Localidade
        if((form != null && form.getLocalidadeFiltro() != null && !form.getLocalidadeFiltro().equals(""))
        		&&(form.getNomeLocalidade() == null || form.getNomeLocalidade().equals(""))){
        	pesquisarLocalidade(form, fachada, httpServletRequest);
    	}
//-----------Parte 3 Pesquisa Setor Comercial
        if((form != null && form.getSetorComercialFiltro() != null && !form.getSetorComercialFiltro().equals(""))
        		&&(form.getSetorComercialNome() == null || form.getSetorComercialNome().equals(""))){
        	pesquisarSetorComercial(form,fachada,httpServletRequest);
        }
//-----------Parte 4 Pesquisa Quadra
        if((form != null && form.getQuadraFiltro() != null && !form.getQuadraFiltro().equals(""))
        		&&(form.getQuadraNome() == null || form.getQuadraNome().equals(""))){
        	pesquisarQuadra(form,fachada,httpServletRequest);
        }
//----------Parte 5 Adicionar
        String adicionar = httpServletRequest.getParameter("adicionar");
        if(adicionar != null && !adicionar.trim().equals("")){
        	if(adicionar.trim().equalsIgnoreCase("localidade") 
        			&& form.getLocalidadeFiltro() != null && !form.getLocalidadeFiltro().equals("")){
        		Collection colecaoLocalidades = (Collection) sessao.getAttribute("colecaoLocalidades");
        		Localidade localidade = new Localidade();
        		localidade.setId(new Integer(form.getLocalidadeFiltro()));
        		localidade.setDescricao(form.getNomeLocalidade());
        		if(!colecaoLocalidades.contains(localidade)){
	    			colecaoLocalidades.add(localidade);   
	        		form.setLocalidadeFiltro("");
	        		form.setNomeLocalidade("");
	        		if(colecaoLocalidades.size() > 1){
	        			sessao.setAttribute("bloqueiaSetor", "s");
	        			sessao.setAttribute("bloqueiaQuadra", "s");
	        		}else if(colecaoLocalidades.size() == 1){
	        			sessao.removeAttribute("bloqueiaSetor");
	        			sessao.removeAttribute("bloqueiaQuadra");
	        		}
        		}
        	}else if(adicionar.trim().equalsIgnoreCase("setor")
        			&& form.getSetorComercialFiltro() != null && !form.getSetorComercialFiltro().equals("")){
        		Collection colecaoSetor = (Collection)sessao.getAttribute("colecaoSetor");
        		SetorComercial setorComercial = new SetorComercial();
        		setorComercial.setCodigo(new Integer(form.getSetorComercialFiltro()));
        		setorComercial.setDescricao(form.getSetorComercialNome());
        		if(!colecaoSetor.contains(setorComercial)){
	        		colecaoSetor.add(setorComercial);
	        		form.setSetorComercialFiltro("");
	        		form.setSetorComercialID("");
	        		form.setSetorComercialNome("");
	        		if(colecaoSetor.size() > 1){
	        			sessao.setAttribute("bloqueiaQuadra", "s");
	        			sessao.setAttribute("bloqueiaLocalidade", "s");
	        		}else if(colecaoSetor.size() == 1){
	        			sessao.removeAttribute("bloqueiaQuadra");
	        			sessao.setAttribute("bloqueiaLocalidade", "s");
	        		}
        		}
        	}else if(adicionar.trim().equalsIgnoreCase("quadra")){
        		Collection colecaoQuadras = (Collection) sessao.getAttribute("colecaoQuadras");
        		Quadra quadra = new Quadra();
        		quadra.setId(new Integer(form.getQuadraID()));
        		quadra.setNumeroQuadra(new Integer(form.getQuadraFiltro()));
        		if(!colecaoQuadras.contains(quadra)){
        			colecaoQuadras.add(quadra);
        			form.setQuadraFiltro("");
        			sessao.setAttribute("bloqueiaLocalidade", "s");
        		}
        	}
        }else{
        	if(sessao.getAttribute("colecaoLocalidades") == null){
        		sessao.setAttribute("colecaoLocalidades", new ArrayList());
        	}
        	if(sessao.getAttribute("colecaoSetor") == null){
        		sessao.setAttribute("colecaoSetor", new ArrayList());
        	}
        	if(sessao.getAttribute("colecaoQuadras") == null){
        		sessao.setAttribute("colecaoQuadras", new ArrayList());
        	}
        }
        
        String remover = httpServletRequest.getParameter("remover");
        if(remover != null && !remover.trim().equals("")){
        	if(remover.equalsIgnoreCase("localidade")){
        		Collection localidades = (Collection) sessao.getAttribute("colecaoLocalidades");
        		Iterator iterator = localidades.iterator();
        		String idRemocaoLocalidade = httpServletRequest.getParameter("idLocalidadeRemocao");
        		while(iterator.hasNext()){
        			Localidade localidade = (Localidade) iterator.next();
        			if(localidade.getId().toString().equals(idRemocaoLocalidade)){
        				iterator.remove();
        			}
        		}        			
        	}
        }
        
        return retorno;

    }
    
    private void pesquisarLocalidade(RelatorioAnaliticoFaturamentoActionForm form, 
    		Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		localidadeID = (String) form.getLocalidadeFiltro();
		String nomeLocalidade = form.getNomeLocalidade();

		if (localidadeID != null && !localidadeID.equals("")
				&& (nomeLocalidade == null || nomeLocalidade.equals(""))) {

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formul�rio
				form.setLocalidadeFiltro("");
				form.setNomeLocalidade("localidade inexistente");
				httpServletRequest.setAttribute(
						"codigoLocalidadeNaoEncontrada", "exception");
				httpServletRequest
						.setAttribute("nomeCampo", "localidadeFiltro");
				// httpServletRequest.setAttribute("corLocalidadeOrigem","exception");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				form.setLocalidadeFiltro(String
						.valueOf(objetoLocalidade.getId()));
				form.setNomeLocalidade(objetoLocalidade
						.getDescricao());
				form.setIdLocalidadeSetor(objetoLocalidade.getId().toString());
			}
		}
	}
    
    //PESQUISAR DE SETOR COMERCIAL
    private void pesquisarSetorComercial(RelatorioAnaliticoFaturamentoActionForm form, 
    		Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		// Recebe o valor do campo localidadeOrigemID do formul�rio.
		localidadeID = (String) form.getLocalidadeFiltro();

		setorComercialCD = (String) form
				.getSetorComercialFiltro();
		String nomeSetorComercial = form
				.getSetorComercialNome();

		// O campo localidadeOrigemID ser� obrigat�rio
		if (setorComercialCD != null
				&& !setorComercialCD.trim().equalsIgnoreCase("")
				&& (nomeSetorComercial == null || nomeSetorComercial.equals(""))) {

			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {
				// Adiciona o id da localidade que est� no formul�rio para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));
			}
			// Adiciona o c�digo do setor comercial que esta no formul�rio
			// para compor a pesquisa.
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
					setorComercialCD));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna setorComercial
			colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
					SetorComercial.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Setor Comercial nao encontrado
				// Limpa os campos setorComercialOrigemCD,
				// nomeSetorComercialOrigem e setorComercialOrigemID do
				// formul�rio
				form.setSetorComercialFiltro("");
				form.setSetorComercialID("");
				form.setSetorComercialNome("Setor comercial inexistente.");
				httpServletRequest.setAttribute(
						"codigoSetorComercialNaoEncontrada", "exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialFiltro");
			} else {
				SetorComercial objetoSetorComercial = (SetorComercial) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				form.setSetorComercialFiltro(String
						.valueOf(objetoSetorComercial.getCodigo()));
				form.setSetorComercialID(String
						.valueOf(objetoSetorComercial.getId()));
				form.setSetorComercialNome(objetoSetorComercial
								.getDescricao());
				form.setIdSetorQuadra(objetoSetorComercial.getCodigo()+"");
			}
		}
	}
    
    //PESQUISA DE QUADRA
    private void pesquisarQuadra(RelatorioAnaliticoFaturamentoActionForm form,
    		Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroQuadra filtroQuadra = new FiltroQuadra();

		// Recebe os valores dos campos setorComercialOrigemCD e
		// setorComercialOrigemID do formul�rio.
		setorComercialCD = (String) form
				.getIdSetorQuadra();

		setorComercialID = (String) form
				.getSetorComercialID();

		quadraNM = (String) form.getQuadraFiltro();
		String nomeQuadra = form.getQuadraNome();
		// Os campos setorComercialOrigemCD e setorComercialID ser�o
		// obrigat�rios
		if (quadraNM != null && !quadraNM.trim().equalsIgnoreCase("")
				&& (nomeQuadra == null || nomeQuadra.equals(""))) {

			if (setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {
				// Adiciona o id do setor comercial que est� no formul�rio
				// para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));
			}
			// Adiciona o n�mero da quadra que esta no formul�rio para
			// compor a pesquisa.
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA, quadraNM));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna quadra
			colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
					.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Quadra nao encontrada
				// Limpa os campos quadraOrigemNM e quadraOrigemID do
				// formul�rio
				form.setQuadraNome("");
				form.setQuadraFiltro("");
				// Mensagem de tela
				form.setQuadraMensagem("QUADRA INICIAL INEXISTENTE.");
				httpServletRequest.setAttribute(
						"codigoQuadraInicialNaoEncontrada", "exception");
				httpServletRequest.setAttribute("nomeCampo",
						"quadraInicialFiltro");
			} else {
				Quadra objetoQuadra = (Quadra) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				form.setQuadraFiltro(String
						.valueOf(objetoQuadra.getNumeroQuadra()));
				form.setQuadraID(String
						.valueOf(objetoQuadra.getId()));
				//Mensagem de tela
				form.setQuadraMensagem("");
				// -----------quadra final recebe o mesmo q a inicial
				
			}
		}
    }
}
