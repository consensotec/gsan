/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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

package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.FiltroLogradouroAtlzCadDM;
import gcom.atualizacaocadastral.FiltroLogradouroBairroAtlzCadDM;
import gcom.atualizacaocadastral.FiltroLogradouroCepAtlzCadDM;
import gcom.atualizacaocadastral.LogradouroAtlzCadDM;
import gcom.atualizacaocadastral.LogradouroBairroAtlzCadDM;
import gcom.atualizacaocadastral.LogradouroCepAtlzCadDM;
import gcom.atualizacaocadastral.bean.AtualizacaoCadastralLogradouroHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para inserir os logradouros adicionados pelo tablet
 * 
 * @author Anderson Cabral
 * @author Bruno Sá Barreto
 * @date 06/03/2012
 */
public class ExibirInserirNovosLogradourosAtualizacaoCadastralAction extends GcomAction{
	

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);		
		ActionForward retorno = actionMapping.findForward("exibirInserirNovosLogradourosAtualizacaoCadastralAction");
		
		InserirNovosLogradourosAtualizacaoCadastralActionForm inserirNovosLogradourosActionForm = (InserirNovosLogradourosAtualizacaoCadastralActionForm) actionForm;
		
		//Caso tenha sido a primeira vez que carrega a tela
		if (httpServletRequest.getParameter("pesquisarLogradouros") == null && 
				httpServletRequest.getParameter("substituirLogradouro") == null){
			
			//Carregar lista de Empresas
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();		
			filtroEmpresa.adicionarParametro( new ParametroSimples(FiltroEmpresa.INDICADOR_ATUALIZA_CADASTRO, ConstantesSistema.SIM));
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
			Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
			
			//Pesquisar Localidade
			if (inserirNovosLogradourosActionForm.getIdLocalidade() != null && !inserirNovosLogradourosActionForm.getIdLocalidade().trim().equals("")) {			
				pesquisarLocalidade( sessao, inserirNovosLogradourosActionForm);
			}
			
		}
		
		//Pesquisa os logradouros 
		if (httpServletRequest.getParameter("pesquisarLogradouros") != null){			
			montarColecaoLogradouros(inserirNovosLogradourosActionForm, fachada, sessao);
		}
		
		//Substituir Logradouro
		if(httpServletRequest.getParameter("substituirLogradouro") != null){
			String[] idLogradourosSelecionados = inserirNovosLogradourosActionForm.getIdsRegistros();
			String idLogradouroSubstituto  	   = inserirNovosLogradourosActionForm.getIdLogradouroSubstituto();
			
			ArrayList<AtualizacaoCadastralLogradouroHelper> colecaoLogradouroHelper = (ArrayList<AtualizacaoCadastralLogradouroHelper>) sessao.getAttribute("colecaoLogradouroHelper");
			
			//pesquisa o logradouro a ser substituido
			for(AtualizacaoCadastralLogradouroHelper logradouroHelper : colecaoLogradouroHelper){
				for(String idLogradouroSelecionado : idLogradourosSelecionados){
					if(logradouroHelper.getLogradouroAtlzCad().getId().toString().equalsIgnoreCase(idLogradouroSelecionado)){
						logradouroHelper.setIdSubstituirLogra(idLogradouroSubstituto);
						break;
					}
				}
			}
			
			sessao.setAttribute("colecaoLogradouroHelper", colecaoLogradouroHelper);
		
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisa a localidade pelo id
	 * 
	 * @author Anderson Cabral
	 * @date 06/03/2012
	 */
	private void pesquisarLocalidade(HttpSession session, 
			InserirNovosLogradourosAtualizacaoCadastralActionForm form) {

		Fachada fachada = Fachada.getInstancia();
		
		// Pesquisa a localidade na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));	
		
		Collection<Localidade> localidadePesquisada = 
				fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		
		// Se nenhuma localidade for encontrada a mensagem e enviada para a pagina
		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
			form.setIdLocalidade(""+localidade.getId());
			form.setNomeLocalidade(localidade.getDescricao());
			session.setAttribute("localidadeEncontrada",true);
		} else {
			form.setIdLocalidade("");
			form.setNomeLocalidade("LOCALIDADE INEXISTENTE");
			session.removeAttribute("localidadeEncontrada");
		}
	}
	
	private void montarColecaoLogradouros(InserirNovosLogradourosAtualizacaoCadastralActionForm inserirNovosLogradourosActionForm, Fachada fachada, HttpSession sessao){
		if(inserirNovosLogradourosActionForm.getIdLocalidade()!=null && Util.verificaSeNumeroNatural(inserirNovosLogradourosActionForm.getIdLocalidade())){
			
			FiltroLogradouroAtlzCadDM filtroLogradouroAtlzCad = new FiltroLogradouroAtlzCadDM("logradouroTipo.descricao, objeto.nome ");			
			filtroLogradouroAtlzCad.adicionarParametro(new ParametroSimples(FiltroLogradouroAtlzCadDM.ID_EMPRESA,
					inserirNovosLogradourosActionForm.getIdEmpresa()));
			filtroLogradouroAtlzCad.adicionarParametro(new ParametroSimples(FiltroLogradouroAtlzCadDM.ID_LOCALIDADE,
					inserirNovosLogradourosActionForm.getIdLocalidade()));
			filtroLogradouroAtlzCad.adicionarParametro(new ParametroSimples(FiltroLogradouroAtlzCadDM.INDICADOR_ATULIZADO, 2));
			filtroLogradouroAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroAtlzCadDM.LOGRADOUROTIPO);
			filtroLogradouroAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroAtlzCadDM.LOGRADOUROTITULO);
			filtroLogradouroAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroAtlzCadDM.MUNICIPIO);
			filtroLogradouroAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroAtlzCadDM.UF);
			
			Collection<LogradouroAtlzCadDM> colecaoLogradouroAtlzCad = fachada.pesquisar(filtroLogradouroAtlzCad, LogradouroAtlzCadDM.class.getName());
			
			//caso exista LogradouroAtlzCad para as informacoes passada, monta o helper
			if(colecaoLogradouroAtlzCad != null && colecaoLogradouroAtlzCad.size() != 0){
				AtualizacaoCadastralLogradouroHelper logradouroHelper;
				ArrayList<AtualizacaoCadastralLogradouroHelper> colecaoLogradouroHelper = new ArrayList<AtualizacaoCadastralLogradouroHelper>();				
				
				for(LogradouroAtlzCadDM logradouroAtlzCad : colecaoLogradouroAtlzCad){
					logradouroHelper = new AtualizacaoCadastralLogradouroHelper();
					logradouroHelper.setLogradouroAtlzCad(logradouroAtlzCad);
					
					//Pesquisa os LogradouroBairroAtlzCad do LogradouroAtlzCad
					ArrayList<LogradouroBairroAtlzCadDM> colecaoLogradouroBairroAtlzCad = pesquisarLogradouroBairroAtlzCad(logradouroAtlzCad.getId().toString(), fachada);
					logradouroHelper.setColecaoLogardouroBairroAtlzCad(colecaoLogradouroBairroAtlzCad);
					
					//caso exista LogradouroBairroAtlzCad para o LogradouroAtlzCad, monta a String com os nomes dos bairros para exibir no grid
					if(colecaoLogradouroBairroAtlzCad !=  null){
						String bairros = "";
						for(LogradouroBairroAtlzCadDM logradouroBairroAtlzCad : colecaoLogradouroBairroAtlzCad){							
							bairros = bairros + logradouroBairroAtlzCad.getBairro().getNome() + "<br>";
						}
						logradouroHelper.setBairros(bairros);
					}
					
					//Pesquisa os colecaoLogradouroCepAtlzCad do LogradouroAtlzCad
					ArrayList<LogradouroCepAtlzCadDM> colecaoLogradouroCepAtlzCad = pesquisarLogradouroCepAtlzCad(logradouroAtlzCad.getId().toString(), fachada);
					logradouroHelper.setColecaoLogradouroCepAtlzCad(colecaoLogradouroCepAtlzCad);
					
					colecaoLogradouroHelper.add(logradouroHelper);
				}
				
				sessao.setAttribute("colecaoLogradouroHelper", colecaoLogradouroHelper);
			}else{
				throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Logradouros");
			}
		}else{
			throw new ActionServletException("atencao.inteiroZeroPositivo", null, "Localidade");
		}
	}
	
	private ArrayList<LogradouroBairroAtlzCadDM> pesquisarLogradouroBairroAtlzCad(String idLogradouro, Fachada fachada){
		FiltroLogradouroBairroAtlzCadDM filtroLogradouroBairroAtlzCad = new FiltroLogradouroBairroAtlzCadDM();
		filtroLogradouroBairroAtlzCad.adicionarParametro(new ParametroSimples(FiltroLogradouroBairroAtlzCadDM.ID_LOGRADOURO_ATLZ_CAD,
				idLogradouro));
		filtroLogradouroBairroAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairroAtlzCadDM.BAIRRO);
		
		ArrayList<LogradouroBairroAtlzCadDM> colecaoLogradouroCepAtlzCad = (ArrayList<LogradouroBairroAtlzCadDM>) fachada.pesquisar(filtroLogradouroBairroAtlzCad, LogradouroBairroAtlzCadDM.class.getName());
		
		return colecaoLogradouroCepAtlzCad;
	}
	
	private ArrayList<LogradouroCepAtlzCadDM> pesquisarLogradouroCepAtlzCad(String idLogradouro, Fachada fachada){
		FiltroLogradouroCepAtlzCadDM filtroLogradouroCepAtlzCad = new FiltroLogradouroCepAtlzCadDM();
		filtroLogradouroCepAtlzCad.adicionarParametro(new ParametroSimples(FiltroLogradouroCepAtlzCadDM.ID_LOGRADOURO_ATLZ_CAD, idLogradouro));
		filtroLogradouroCepAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroCepAtlzCadDM.CEP_ATLZ_CAD);
		
		ArrayList<LogradouroCepAtlzCadDM> colecaoLogradouroCepAtlzCad = (ArrayList<LogradouroCepAtlzCadDM>) fachada.pesquisar(filtroLogradouroCepAtlzCad, LogradouroCepAtlzCadDM.class.getName());
		
		return colecaoLogradouroCepAtlzCad;
	}
}
