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

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atualizacaocadastral.bean.AtualizacaoCadastralLogradouroHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC1443] Gerar Relatorio de Novos Logradouros
 *  
 * @author Anderson Cabral
 * @author Bruno Sá Barreto
 * 
 * @date 15/03/2013
 */
public class GerarRelatorioNovosLogradouros extends ExibidorProcessamentoTarefaRelatorio{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);

		InserirNovosLogradourosAtualizacaoCadastralActionForm form = (InserirNovosLogradourosAtualizacaoCadastralActionForm) actionForm;
		
		String idEmpresa 	= form.getIdEmpresa();
		String idLocalidade = form.getIdLocalidade();
		String nomeLocalidade = form.getNomeLocalidade();
		String idsSelecionados = this.obterIdsLogradourosPesquisados(httpServletRequest);
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
		ArrayList<Empresa> colecaoEmpresas = (ArrayList<Empresa>) fachada.pesquisar(filtroEmpresa, Empresa.class.getName());		
		Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresas);
		
		RelatorioNovosLogradouros relatorioNovosLogradouros = new RelatorioNovosLogradouros(
				(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioNovosLogradouros.addParametro("idEmpresa", idEmpresa);
		relatorioNovosLogradouros.addParametro("idLocalidade", idLocalidade);
		relatorioNovosLogradouros.addParametro("nomeLocalidade", nomeLocalidade);
		relatorioNovosLogradouros.addParametro("nomeEmpresa", empresa.getDescricao());
		relatorioNovosLogradouros.addParametro("idsSelecionados", idsSelecionados);
		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorioNovosLogradouros.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		
		try {
			retorno = processarExibicaoRelatorio(relatorioNovosLogradouros, tipoRelatorio, 
					httpServletRequest, httpServletResponse, actionMapping);

		} catch (RelatorioVazioException ex) {
	
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");
	
			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencao");
		}
				
		return retorno;
	}
	
	/**
	 * [UC1443] Gerar Relatorio de Novos Logradouros
	 * este método é responsável por coletar os ids de todos
	 * os resultados que foram retornados na pesquisa. e 
	 * retornar em uma string separados por vírgula.
	 *  
	 * @author Bruno Sá Barreto
	 * @since 24/11/2014
	 * 
	 * @return String 
	 */
	@SuppressWarnings("unchecked")
	private String obterIdsLogradourosPesquisados(HttpServletRequest httpServletRequest){
		ArrayList<AtualizacaoCadastralLogradouroHelper> colecaoLogradouroHelper = 
				(ArrayList<AtualizacaoCadastralLogradouroHelper>) httpServletRequest.getSession().getAttribute("colecaoLogradouroHelper");
		
		String ids = ""; int count=0;
		if(colecaoLogradouroHelper!=null && !colecaoLogradouroHelper.isEmpty()){
			for(AtualizacaoCadastralLogradouroHelper atual : colecaoLogradouroHelper){
				ids += atual.getLogradouroAtlzCad().getId();
				if(count!=colecaoLogradouroHelper.size()-1){
					ids+=",";
				}
				count++;
			}
		}else{
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		return ids;
	}
	
}
