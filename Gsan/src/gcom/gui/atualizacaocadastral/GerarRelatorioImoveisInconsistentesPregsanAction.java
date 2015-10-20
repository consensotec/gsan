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

import gcom.atualizacaocadastral.bean.DadosImovelPreGsanHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.FiltroCadastroOcorrencia;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Gerar Relatorio Imoveis Inconsistentes para Imoveis novos e Imoveis com Ocorrencia de Cadastro
 * 
 * @author Anderson Cabral
 * @date 08/08/2013
 */
public class GerarRelatorioImoveisInconsistentesPregsanAction extends ExibidorProcessamentoTarefaRelatorio{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();
		ConsultarImoveisPreGsanActionForm form = (ConsultarImoveisPreGsanActionForm) actionForm;
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		//Pesquisa od imoveis de acordo com os parametros informados
		ArrayList<DadosImovelPreGsanHelper> colecaoImovelPreGsan = this.pesquisarImoveis(form, httpServletRequest);

		if(colecaoImovelPreGsan != null && !colecaoImovelPreGsan.isEmpty()){
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			
			if (tipoRelatorio  == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
			
			//Empresa
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, form.getIdEmpresa()));
			Collection<Empresa> empresas = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			Empresa empresa = (Empresa)Util.retonarObjetoDeColecao(empresas);
			
			//Localidade
			Localidade localidade = new Localidade();
			localidade.setDescricao(form.getDescricaoLocalidade());			
			if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
				Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
				localidade = (Localidade)Util.retonarObjetoDeColecao(colecaoLocalidade);
			}
			
			//Setor Comercial
			SetorComercial setorComercial = new SetorComercial();
			setorComercial.setDescricao(form.getDescricaoSetorComercial());
			if(form.getIdSetorComercial() != null && !form.getIdSetorComercial().equals("")){
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidade.getId()));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercial()));
				Collection<SetorComercial> colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);			
			}
			
			//Quadras
			String numeroQuadras = "";
			Integer[] idsQuadras = form.getIdQuadraSelecionados();			
			numeroQuadras = this.limitarNumeroQuadras(idsQuadras);
			
			//Ocorrencia de Cadastro
			CadastroOcorrencia cadastroOcorrencia = new CadastroOcorrencia();
			cadastroOcorrencia.setDescricao("");
			if(form.getIdCadastroOcorrencia() != null && !form.getIdCadastroOcorrencia().equals("-1")){
				FiltroCadastroOcorrencia filtroCadastroOcorrencia = new FiltroCadastroOcorrencia();
				filtroCadastroOcorrencia.adicionarParametro(new ParametroSimples(FiltroCadastroOcorrencia.ID, form.getIdCadastroOcorrencia()));
				ArrayList<CadastroOcorrencia> colecaoCadastroOcorrencia = (ArrayList<CadastroOcorrencia>) fachada.pesquisar(filtroCadastroOcorrencia, CadastroOcorrencia.class.getName());
				cadastroOcorrencia = (CadastroOcorrencia) Util.retonarObjetoDeColecao(colecaoCadastroOcorrencia);				
			}
			
			if(form.getIndicadorTipoSelecao().equals("2")){
				if(httpServletRequest.getParameter("resumo") != null){
					RelatorioResumoImoveisInconsistentesComOcorrencia relatorioResumoImoveisInconsistentesComOcorrencia = new RelatorioResumoImoveisInconsistentesComOcorrencia(
						(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("empresa", empresa.getDescricao());			
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("tipoSelecao", "IMÓVEIS COM OCORRÊNCIA CADASTRO");
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("idLocalidade", form.getIdLocalidade());
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("descricaoLocalidade", localidade.getDescricao());
					if(setorComercial.getCodigo() != 0){
						relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("codigoSetorComercial", String.valueOf(setorComercial.getCodigo()));
					}else{
						relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("codigoSetorComercial", "");
					}
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("descricaoSetorComecial", setorComercial.getDescricao());
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("numeroQuadras", numeroQuadras);
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("descricaoCadastroOcorrencia", cadastroOcorrencia.getDescricao());
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("colecaoImoveisInconsistentes", colecaoImovelPreGsan);
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
					
					relatorioResumoImoveisInconsistentesComOcorrencia.addParametro("nomeCadastrador", obterNomeCadastrador(form.getCadastrador()));
					
					retorno = processarExibicaoRelatorio(relatorioResumoImoveisInconsistentesComOcorrencia, tipoRelatorio, 
							httpServletRequest, httpServletResponse, actionMapping);
				}else{
					RelatorioImoveisInconsistentesComOcorrencia relatorioImoveisInconsistentesComOcorrencia = new RelatorioImoveisInconsistentesComOcorrencia(
							(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
					
					relatorioImoveisInconsistentesComOcorrencia.addParametro("empresa", empresa.getDescricao());			
					relatorioImoveisInconsistentesComOcorrencia.addParametro("tipoSelecao", "IMÓVEIS COM OCORRÊNCIA CADASTRO");
					relatorioImoveisInconsistentesComOcorrencia.addParametro("idLocalidade", form.getIdLocalidade());
					relatorioImoveisInconsistentesComOcorrencia.addParametro("descricaoLocalidade", localidade.getDescricao());
					if(setorComercial.getCodigo() != 0){
						relatorioImoveisInconsistentesComOcorrencia.addParametro("codigoSetorComercial", String.valueOf(setorComercial.getCodigo()));
					}else{
						relatorioImoveisInconsistentesComOcorrencia.addParametro("codigoSetorComercial", "");
					}
					relatorioImoveisInconsistentesComOcorrencia.addParametro("descricaoSetorComecial", setorComercial.getDescricao());
					relatorioImoveisInconsistentesComOcorrencia.addParametro("numeroQuadras", numeroQuadras);
					relatorioImoveisInconsistentesComOcorrencia.addParametro("descricaoCadastroOcorrencia", cadastroOcorrencia.getDescricao());
					relatorioImoveisInconsistentesComOcorrencia.addParametro("colecaoImoveisInconsistentes", colecaoImovelPreGsan);
					relatorioImoveisInconsistentesComOcorrencia.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
					
					relatorioImoveisInconsistentesComOcorrencia.addParametro("nomeCadastrador", obterNomeCadastrador(form.getCadastrador()));
					
					retorno = processarExibicaoRelatorio(relatorioImoveisInconsistentesComOcorrencia, tipoRelatorio, 
							httpServletRequest, httpServletResponse, actionMapping);
				}
			}else{
				
				RelatorioImoveisNovosInconsistentes relatorioImoveisNovosInconsistentes = new RelatorioImoveisNovosInconsistentes(
						(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				
				relatorioImoveisNovosInconsistentes.addParametro("empresa", empresa.getDescricao());			
				relatorioImoveisNovosInconsistentes.addParametro("tipoSelecao", "IMÓVEIS NOVOS");
				relatorioImoveisNovosInconsistentes.addParametro("idLocalidade", form.getIdLocalidade());
				relatorioImoveisNovosInconsistentes.addParametro("descricaoLocalidade", localidade.getDescricao());
				if(setorComercial.getCodigo() != 0){
					relatorioImoveisNovosInconsistentes.addParametro("codigoSetorComercial", String.valueOf(setorComercial.getCodigo()));
				}else{
					relatorioImoveisNovosInconsistentes.addParametro("codigoSetorComercial", "");
				}
				relatorioImoveisNovosInconsistentes.addParametro("descricaoSetorComecial", setorComercial.getDescricao());
				relatorioImoveisNovosInconsistentes.addParametro("numeroQuadras", numeroQuadras);
				relatorioImoveisNovosInconsistentes.addParametro("descricaoCadastroOcorrencia", cadastroOcorrencia.getDescricao());
				relatorioImoveisNovosInconsistentes.addParametro("colecaoImoveisInconsistentes", colecaoImovelPreGsan);
				relatorioImoveisNovosInconsistentes.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
				
				relatorioImoveisNovosInconsistentes.addParametro("nomeCadastrador", obterNomeCadastrador(form.getCadastrador()));
				
				retorno = processarExibicaoRelatorio(relatorioImoveisNovosInconsistentes, tipoRelatorio, 
						httpServletRequest, httpServletResponse, actionMapping);
			}
		}else{
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");
	
			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencao");
		}
		
		return retorno;	
	}
	
	/**
	 * [UC1447] - Consultar Imóvel Ambiente PRÉ-GSAN - Tela principal (Geração de relatórios)
	 * Este método, limita a string formatada e adiciona ... caso todas as quadras não caibam
	 * no parâmetro que será passado pro relatório. 	
	 *
	 * @author Bruno Sá Barreto
	 * @date 30/12/2014
	 *
	 * @param idsQuadras
	 * @return numeroQuadrasFormatado
	 */
	private String limitarNumeroQuadras(Integer[] idsQuadras) {
		String total = "";
		if(idsQuadras[0] != -1){			
			for(int i=0; i<idsQuadras.length; i++){
				total+=idsQuadras[i];
				if(i<idsQuadras.length-1)total+=", ";
			}
		}
		if(total.length()>45){
			total = total.substring(0, 44);
			int ultimaVirgula = total.lastIndexOf(",");
			total = total.substring(0, ultimaVirgula);
			total+="...";
		}
		return total;
	}

	/**
	 * @author Bruno Sá Barreto
	 * @date 29/12/2014
	 *
	 * @param idCadastrador
	 * @return nomeCadastrador
	 */
	private String obterNomeCadastrador(String idCadastrador) {
		String result="";
		if(!"-1".equals(idCadastrador)){
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista(FiltroLeiturista.USUARIO_NOME);
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.INDICADOR_USO, ConstantesSistema.SIM));
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ATUALIZACAO_CADASTRAL, ConstantesSistema.SIM));
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID, idCadastrador));
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.USUARIO);
			Collection<?> colecao = Fachada.getInstancia().pesquisar(filtroLeiturista,	Leiturista.class.getName());
			if (!Util.isVazioOrNulo(colecao)) {
				Leiturista leitu = (Leiturista) Util.retonarObjetoDeColecao(colecao);
				result =leitu.getUsuario().getNomeUsuario();
			}
		}
		return result;
	}

	private ArrayList<DadosImovelPreGsanHelper> pesquisarImoveis(ConsultarImoveisPreGsanActionForm form, HttpServletRequest httpServletRequest) {
		
		DadosImovelPreGsanHelper parametros = new DadosImovelPreGsanHelper();
		//monta os parametros informados na tela.
		parametros.setParametroEmpresa(form.getIdEmpresa());
		parametros.setParametroLocalidade(form.getIdLocalidade());
		parametros.setParametroSetorComercial(form.getIdSetorComercial());
		parametros.setParametroQuadra(form.getIdQuadraSelecionados());
		parametros.setParametroCadastroOcorrencia(form.getIdCadastroOcorrencia());
		parametros.setParametroTipoSelecao(form.getIndicadorTipoSelecao());
		parametros.setParametroCadastrador(form.getCadastrador());
		
		ArrayList<DadosImovelPreGsanHelper> colecaoImovelPreGsan = null;
		
		if(httpServletRequest.getParameter("resumo") == null){
			colecaoImovelPreGsan = (ArrayList<DadosImovelPreGsanHelper>) Fachada.getInstancia().pesquisarImoveisPreGsanRelatorioCadastroOcorrencia(parametros);
		}else{
			colecaoImovelPreGsan = (ArrayList<DadosImovelPreGsanHelper>) Fachada.getInstancia().pesquisarImovelPreGsanResumoPorCadastroOcorrencia(parametros);
		}
		return colecaoImovelPreGsan;		 
	}
}
