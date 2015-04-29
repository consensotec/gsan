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
package gsan.gui.relatorio.faturamento.conta;

import gsan.cadastro.cliente.EsferaPoder;
import gsan.cadastro.cliente.FiltroEsferaPoder;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.FiltroCategoria;
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.FiltroFaturamentoGrupo;
import gsan.faturamento.FiltroMotivoCancelamento;
import gsan.faturamento.conta.ContaMotivoCancelamento;
import gsan.faturamento.conta.ContaMotivoRetificacao;
import gsan.faturamento.conta.FiltroContaMotivoCancelamento;
import gsan.faturamento.conta.FiltroMotivoRetificacaoConta;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.relatorio.faturamento.RelatorioContasCanceladasRetificadasActionForm;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

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
public class ExibirFiltrarRelatorioContasCanceladasRetificadasAction extends GcomAction {

	private Collection colecaoPesquisa = null;
	private String localidadeID = null;
	private String responsavelID = null;

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirFiltrarRelatorioContasCanceladasRetificadas");

        Fachada fachada = Fachada.getInstancia();
        
        RelatorioContasCanceladasRetificadasActionForm form = 
        	(RelatorioContasCanceladasRetificadasActionForm) actionForm;
        
        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);
        String tipoContaVar = (String) httpServletRequest.getParameter("tipoContaVar");
        String menu = (String) httpServletRequest.getParameter("menu");
        
        sessao.setAttribute("mostrarLogin",true);
        
        //----------- Pesqusia Localidade
        if((form != null && form.getLocalidadeFiltro() != null && !form.getLocalidadeFiltro().equals(""))
        		&&(form.getNomeLocalidade() == null || form.getNomeLocalidade().equals(""))){
        	
        	pesquisarLocalidade(form, fachada, httpServletRequest);
    	}
        
     
        //----------- Pesquisa Motivo
        Collection colecaoMotivo = new ArrayList(); 
        if(sessao.getAttribute("colecaoCategoria") == null){
        	form.setTipoConta("1");
        }
        
        
    	//verifica se foi clicado num bot�o de sele��o de tipo de relat�rio de contas canceladas ou retificadas
    	if(tipoContaVar != null && tipoContaVar.equals("sim")){
    		
    		//verifica se o tipo de relatorio eh diferente de nulo
    		if(form.getRelatorioTipo() != null){
    			//se o relatorio for sintetico passa o parametro visibilidade como hidden para nao exibir 
    			//os tipos de ordenacao
    			if(form.getRelatorioTipo().equals("2")){
    			  form.setOrdenacaoTipo(null);
    			  form.setPeriodoInicial("");
    			  form.setPeriodoFinal("");
    			  sessao.setAttribute("visibilidade","hidden");  
    			  sessao.setAttribute("visibilidade2","hidden");  
    			  
    			}else if(form.getRelatorioTipo().equals("1")){
    		      
    			   //seta o relatorio como anal�tico
    			  form.setRelatorioTipo("1");
    		      
    			  //verifica se o tipo de ordena��o selecionado eh por data, se for mostra as datas
    		      if(form.getOrdenacaoTipo().equals("2")){
    		    	sessao.setAttribute("visibilidade","visible");  
    		    	sessao.setAttribute("visibilidade2","visible");  
    		      }else {
    		    	  form.setOrdenacaoTipo("1"); 
    		    	  sessao.setAttribute("visibilidade","visible");  
    		    	  sessao.setAttribute("visibilidade2","hidden");  
    		      }
    			}
    		}
    	}else if(form.getRelatorioTipo() != null){
    			//se o relatorio for sintetico passa o parametro visibilidade como hidden para nao exibir 
    			//os tipos de ordenacao
    			if(form.getRelatorioTipo().equals("2")){
    			  form.setOrdenacaoTipo(null);
    			  form.setPeriodoInicial("");
    			  form.setPeriodoFinal("");
    			  sessao.setAttribute("visibilidade","hidden");   			  
    			}else if(form.getRelatorioTipo().equals("1")){
    		      
    			   //seta o relatorio como anal�tico
    			  form.setRelatorioTipo("1");
    		      
    			  //verifica se o tipo de ordena��o selecionado eh por data, se for mostra as datas
    		      if(form.getOrdenacaoTipo().equals("2")){
    		    	sessao.setAttribute("visibilidade","visible");  
    		    	sessao.setAttribute("visibilidade2","visible"); 
    		      }else {
    		    	  form.setOrdenacaoTipo("1"); 
    		    	  sessao.setAttribute("visibilidade","hidden");  
    		      }
    			}
    	}else {
    		form.setRelatorioTipo("2");    		
    	}
    	
    	if(menu != null && menu.equals("sim")){
    	  sessao.setAttribute("visibilidade","hidden");
    	  sessao.setAttribute("visibilidade2","hidden"); 
    	}
        
        //se motico canceladas
        if(form.getTipoConta() == null || form.getTipoConta().equals("") || form.getTipoConta().equals("1")){
        	FiltroContaMotivoCancelamento filtroContaMotivoCancelamento = new FiltroContaMotivoCancelamento();
        	filtroContaMotivoCancelamento.setCampoOrderBy(FiltroContaMotivoCancelamento.DESCRICAO);
        	colecaoMotivo = fachada.pesquisar(filtroContaMotivoCancelamento, ContaMotivoCancelamento.class.getName());
        	sessao.setAttribute("colecaoMotivoCancelamento", colecaoMotivo);
        	sessao.removeAttribute("colecaoMotivoRetificacao");
        
        }else{
        //se motivo retificadas
        	FiltroMotivoRetificacaoConta filtroMotivoRetificacaoConta = new FiltroMotivoRetificacaoConta();
        	filtroMotivoRetificacaoConta.setCampoOrderBy(FiltroMotivoRetificacaoConta.DESCRICAO_MOTIVO_RETIFICACAO_CONTA);
        	colecaoMotivo = fachada.pesquisar(filtroMotivoRetificacaoConta, ContaMotivoRetificacao.class.getName());
        	sessao.setAttribute("colecaoMotivoRetificacao", colecaoMotivo);
        	sessao.removeAttribute("colecaoMotivoCancelamento");
        	
        }
        
        
        //Pesquisa Categoria
        if(sessao.getAttribute("colecaoCategoria") == null){
        	Collection colecaoCategoria = new ArrayList();
        	FiltroCategoria filtroCategoria = new FiltroCategoria();
        	filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
        	colecaoCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
        	sessao.setAttribute("colecaoCategoria", colecaoCategoria);
        }
        
        //Pesquisa Perfil do Imovel
        if(sessao.getAttribute("colecaoPerfil") == null){
        	Collection colecaoPerfil = new ArrayList();
        	FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
        	filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
        	colecaoPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
        	sessao.setAttribute("colecaoPerfil", colecaoPerfil);
        }
        
        //Pesquisar Esfera Poder
        if(sessao.getAttribute("colecaoEsferaPoder") == null){
        	Collection colecaoEsferaPoder = new ArrayList();
        	FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
        	filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
        	colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
        	sessao.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
        }
        Collection colecaoUN = (Collection)sessao.getAttribute("colecaoUnidadeNegocio");
        if ( colecaoUN == null || colecaoUN.isEmpty()  ) {
        
        	FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();	
            filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME_ABREVIADO,FiltroUnidadeNegocio.NOME);
            
        	filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
                    FiltroUnidadeNegocio.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna Localidade_Classe
            Collection colecaoUnidadeNegocio = 
            	this.getFachada().pesquisar(filtroUnidadeNegocio,
                    UnidadeNegocio.class.getName());

            if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
                //Nenhum registro na tabela gerencia_regional foi
                // encontrada
                throw new ActionServletException(
                        "atencao.pesquisa.nenhum_registro_tabela", null,
                        "Unidade de Neg�cio");
            } else {
            	
            	UnidadeNegocio unidadeNegocio = null;
    			Iterator iterator = colecaoUnidadeNegocio.iterator();
    		
    			while (iterator.hasNext()) {
    				unidadeNegocio = (UnidadeNegocio) iterator.next();
    				
    				String descUnidadeNegocio = unidadeNegocio.getNomeAbreviado() + "-" + unidadeNegocio.getNome();
    				unidadeNegocio.setNome(descUnidadeNegocio);
    				
    			}
            	
                sessao.setAttribute("colecaoUnidadeNegocio",
                		colecaoUnidadeNegocio);
            }
        }
        
        //Pesquisar Responsavel
        if((form != null && form.getResponsavelFiltro() != null && !form.getResponsavelFiltro().equals(""))
        		&&(form.getNomeResponsavel() == null || form.getNomeResponsavel().equals(""))){
        	
        	pesquisarResponsavel(form, fachada, httpServletRequest);
    	}
        
      //this.pesquisarUnidadeNegocio(httpServletRequest,form);
		this.pesquisarGrupoFaturamento(httpServletRequest);
		
		//CRC1550 - adicionado por Vivianne Sousa - 16/06/2010 - analista:Ana Cristina
		this.pesquisarGerenciaRegional(httpServletRequest);
        
        return retorno;

    }
    
    private void pesquisarLocalidade(RelatorioContasCanceladasRetificadasActionForm form, 
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
			}
		}
	}
    
    private void pesquisarResponsavel(RelatorioContasCanceladasRetificadasActionForm form, 
    		Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroUsuario filtroUsuario = new FiltroUsuario();

		responsavelID = (String) form.getResponsavelFiltro();
		String nomeResponsavel = form.getNomeResponsavel();

		if (responsavelID != null && 
			!responsavelID.equals("") && 
			(nomeResponsavel == null || nomeResponsavel.equals(""))) {

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.LOGIN, responsavelID));

			// Retorna o usu�rio
			colecaoPesquisa = 
				this.getFachada().pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {

				form.setResponsavelFiltro("");
				form.setNomeResponsavel("Respons�vel inexistente");
				
				httpServletRequest.setAttribute("codigoResponsavelNaoEncontrada", "exception");
				httpServletRequest.setAttribute("nomeCampo", "responsavelFiltro");
			} else {
				
				Usuario objetoUsuario = (Usuario) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				form.setResponsavelFiltro(objetoUsuario.getLogin());
				form.setNomeResponsavel(objetoUsuario.getNomeUsuario());
			}
		}
	}
    
    private void pesquisarGrupoFaturamento(HttpServletRequest httpServletRequest) {

		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();

		filtroFaturamentoGrupo.setConsultaSemLimites(true);

		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO ));
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);

		Collection colecaoFaturamentoGrupo = this.getFachada().pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

		if (colecaoFaturamentoGrupo == null
				|| colecaoFaturamentoGrupo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Grupo Faturamento");
		} else {
			httpServletRequest.setAttribute("colecaoGrupo",
					colecaoFaturamentoGrupo);
		}
	}
    
    private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest) {

    	FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
    	filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
    			FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO ));
    	filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
    	Collection<GerenciaRegional> colecaoGerenciaRegional = this.getFachada().pesquisar(
    			filtroGerenciaRegional, GerenciaRegional.class.getName());

    	if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
    		
    		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Ger�ncia Regional");
    	}
    	
    	httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
    	
//    	sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);

	}
    
}
