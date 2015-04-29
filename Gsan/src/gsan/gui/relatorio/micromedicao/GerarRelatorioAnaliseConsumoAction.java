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
package gsan.gui.relatorio.micromedicao;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.FiltroCategoria;
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.imovel.bean.ImovelMicromedicao;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.FiltroFaturamentoGrupo;
import gsan.gui.micromedicao.LeituraConsumoActionForm;
import gsan.micromedicao.FiltroLeituraSituacao;
import gsan.micromedicao.FiltroLigacaoTipo;
import gsan.micromedicao.consumo.ConsumoAnormalidade;
import gsan.micromedicao.consumo.FiltroConsumoAnormalidade;
import gsan.micromedicao.consumo.LigacaoTipo;
import gsan.micromedicao.leitura.FiltroLeituraAnormalidade;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.micromedicao.leitura.LeituraSituacao;
import gsan.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gsan.micromedicao.medicao.FiltroMedicaoTipo;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.RelatorioVazioException;
import gsan.relatorio.micromedicao.FiltrarAnaliseExcecoesLeiturasHelper;
import gsan.relatorio.micromedicao.RelatorioAnaliseConsumo;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Vivianne Sousa
 * @date 06/11/2007
 */

public class GerarRelatorioAnaliseConsumoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		/*
		 * Colocado por Raphael Rossiter em 04/12/2007 - Analista: Claudio Lira
		 * OBJ: N�o perder os registros selecionados na pagina��o
		 */
		//========================================================================================
//		HashMap<String, String[]> idsImovelPorPagina = this.capturarSelecao(sessao, httpServletRequest);
//		String[] idsImovel = null;
//		
//		if (idsImovelPorPagina == null){
//			throw new ActionServletException("atencao.relatorio.vazio");
//		}
//		else{
//			
//			//ORDENANDO POR PAGINA
//			TreeMap<String, String[]> idsImovelOrdenadosPorPagina = new TreeMap(idsImovelPorPagina);
//			
//			idsImovel = this.gerarArray(idsImovelOrdenadosPorPagina);
//		}
//		
//		//========================================================================================
//		
//		String valoresIdsImovel = "";
//		
//		for (int i = 0; i < idsImovel.length; i++) {
//			if (!idsImovel[i].equals("")) {
//				valoresIdsImovel = valoresIdsImovel + idsImovel[i] + ",";
//			}
//		}
//			
//		valoresIdsImovel = valoresIdsImovel.substring(0, valoresIdsImovel.length() - 1);
		

		String mesAnoPesquisa = (String) sessao.getAttribute("mesAnoPesquisa");

		FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper = (FiltrarAnaliseExcecoesLeiturasHelper) sessao.getAttribute("filtrarAnaliseExcecoesLeiturasHelper");
		
		FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql = new FiltroMedicaoHistoricoSql();
		
		if (sessao.getAttribute("filtroMedicaoHistoricoSql") != null) {
			filtroMedicaoHistoricoSql = (FiltroMedicaoHistoricoSql) sessao.getAttribute("filtroMedicaoHistoricoSql");
		}
		
		Collection colecaoImoveisGerarRelatorio = (Collection) sessao.getAttribute("colecaoImoveisGerarRelatorio");
		
		Collection colecaoIdsImovel = (Collection)sessao.getAttribute("colecaoIdsImovelTotal");
		
		if (colecaoIdsImovel != null && !colecaoIdsImovel.isEmpty()) {

			int index = (Integer) sessao.getAttribute("index");

			Imovel imovel = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
					.get(index)).getImovel();

			// Verifica se o im�vel o im�vel atual foi selecionado, em caso
			// afirmativo adiciona-o a cole��o
			if (httpServletRequest.getParameter("gerarRelatorio") != null
					&& !httpServletRequest.getParameter("gerarRelatorio")
							.trim().equals("")) {

				if (colecaoImoveisGerarRelatorio == null) {
					colecaoImoveisGerarRelatorio = new ArrayList<Imovel>();
				}

				if (!colecaoImoveisGerarRelatorio.contains(imovel.getId())) {
					colecaoImoveisGerarRelatorio.add(imovel.getId());
				}
			} else {
				if (colecaoImoveisGerarRelatorio != null
						&& colecaoImoveisGerarRelatorio.contains(imovel.getId())) {
					colecaoImoveisGerarRelatorio.remove(imovel.getId());
				}
			}

		}
		
		
		// --- Faturamento Grupo
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.ID, new Integer(leituraConsumoActionForm.getIdGrupoFaturamentoFiltro())));
		Collection<FaturamentoGrupo> colecaoFaturamentoGrupo = fachada
				.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)Util.retonarObjetoDeColecao(colecaoFaturamentoGrupo);
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		// Parte que vai mandar o relat�rio para a tela
		// cria uma inst�ncia da classe do relat�rio
		RelatorioAnaliseConsumo relatorioAnaliseConsumo = new RelatorioAnaliseConsumo((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				
		relatorioAnaliseConsumo.addParametro("colecaoImoveisGerarRelatorio", colecaoImoveisGerarRelatorio);

		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		relatorioAnaliseConsumo.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioAnaliseConsumo.addParametro("mesAnoPesquisa", mesAnoPesquisa);
		relatorioAnaliseConsumo.addParametro("mesAnoArrecadacao", Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesArrecadacao().intValue()));
		relatorioAnaliseConsumo.addParametro("grupo", faturamentoGrupo.getDescricao());
		relatorioAnaliseConsumo.addParametro("filtrarAnaliseExcecoesLeiturasHelper", filtrarAnaliseExcecoesLeiturasHelper);
		relatorioAnaliseConsumo.addParametro("filtroMedicaoHistoricoSql", filtroMedicaoHistoricoSql);
		
		this.setarParametrosDoRelatorio(relatorioAnaliseConsumo,leituraConsumoActionForm,fachada);
		
		try {
			retorno = processarExibicaoRelatorio(relatorioAnaliseConsumo,
				tipoRelatorio, httpServletRequest, httpServletResponse,	actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}
	
	
	/**
	 * Capturar os im�veis selecionados pelo usu�rio
	 * 
	 * @author Raphael Rossiter
	 * @date 05/12/2007
	 * 
	 */
//	public HashMap<String, String[]> capturarSelecao(HttpSession sessao, HttpServletRequest httpServletRequest){
//		
//		HashMap<String, String[]> imoveisPorPagina = null;
//		
//		//CASO VENHA DA TELA DE ANALISE
//		String telaAnalise = httpServletRequest.getParameter("concluir");
//		
//		if (telaAnalise == null){
//		
//			String paginaCorrente = httpServletRequest.getParameter("paginaCorrente");
//			
//			String idsImoveisJuntos = httpServletRequest.getParameter("idRegistrosImovel");
//			String[] idsImovel = null;
//			
//			if (idsImoveisJuntos != null && idsImoveisJuntos.length() > 0){
//				idsImovel = idsImoveisJuntos.split(",");
//			}
//			
//			imoveisPorPagina = (HashMap) sessao.getAttribute("idsImoveisJaSelecionados");
//				
//			if (imoveisPorPagina != null && !imoveisPorPagina.isEmpty()){
//				
//				if (imoveisPorPagina.containsKey(paginaCorrente)){
//					
//					if (idsImovel != null && idsImovel.length > 0){
//						
//						//ATUALIZA��O
//						imoveisPorPagina.put(paginaCorrente, idsImovel);
//					}
//					else{
//						
//						//REMO��O
//						imoveisPorPagina.remove(paginaCorrente);
//					}
//				}
//				else if (idsImovel != null && idsImovel.length > 0){
//					//PAGINA NAO CADASTRADA 
//					imoveisPorPagina.put(paginaCorrente, idsImovel);
//				}
//				
//			}
//			else if (idsImovel != null && idsImovel.length > 0){
//				
//				//PRIMEIRA SELECAO
//				imoveisPorPagina = new HashMap<String, String[]>();
//				imoveisPorPagina.put(paginaCorrente, idsImovel);
//					
//				sessao.setAttribute("idsImoveisJaSelecionados", imoveisPorPagina);
//			}
//		}
//		
//		return imoveisPorPagina;
//	}
//	
//	
//	public String[] gerarArray(TreeMap<String, String[]> idsImovelOrdenadosPorPagina){
//		
//		String retorno[] = null;
//		String paginaAtual[] = null;
//		
//		Iterator it = idsImovelOrdenadosPorPagina.values().iterator();
//		
//		while(it.hasNext()){
//			
//			paginaAtual = (String[]) it.next();
//		
//			if (retorno == null){
//				retorno = paginaAtual; 
//			}
//			else{
//				
//				String temp[] = retorno;
//				String arrayPaginaAtual[] = paginaAtual;
//				
//				          
//				retorno = new String[temp.length + arrayPaginaAtual.length]; 
//				System.arraycopy(temp,0,retorno,0,temp.length);
//				
//				System.arraycopy(arrayPaginaAtual, 0, retorno, temp.length, arrayPaginaAtual.length);
//			}
//		}
//		
//		return retorno;
//	}
	/**
	 * @author Hugo Amorim
	 * @date 15/07/2009
	 */
	public void setarParametrosDoRelatorio(RelatorioAnaliseConsumo relatorioAnaliseConsumo,
			LeituraConsumoActionForm form,Fachada fachada){
		
		if(form.getImovelFiltro()!=null && !form.getImovelFiltro().equals("")){
			relatorioAnaliseConsumo.addParametro("matImovel",form.getImovelFiltro());
		}
		if(form.getImovelCondominioFiltro()!=null 
				&& !form.getImovelCondominioFiltro().equals("")){
			relatorioAnaliseConsumo.addParametro("matImovelCondominio",
					form.getImovelCondominioFiltro());
		}
		if(form.getIdEmpresaFiltro()!=null && !form.getIdEmpresaFiltro().equals("")){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID,form.getIdEmpresaFiltro()));
			
			Collection empresas = fachada.pesquisar(filtroEmpresa,Empresa.class.getName());
			
			Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(empresas);
			
			if(empresa!=null && !empresa.equals("")){
				relatorioAnaliseConsumo.addParametro("empresa",
					empresa.getDescricao());
			}
		}
		if(form.getLocalidadeFiltro()!=null && !form.getLocalidadeFiltro().equals("")){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID,form.getLocalidadeFiltro()));
			
			Collection localidades = fachada.pesquisar(filtroLocalidade,Localidade.class.getName());
			
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidades);
			
			if(localidade!=null && !localidade.equals("")){
			relatorioAnaliseConsumo.addParametro("localidadeCodigo",
					localidade.getId().toString());
			relatorioAnaliseConsumo.addParametro("localidadeDescricao",
					localidade.getDescricao().toString());
			}
		
		}
		if(form.getSetorComercialFiltro()!=null &&
				!form.getSetorComercialFiltro().equals("")){
			relatorioAnaliseConsumo.addParametro("setorComercial",
					form.getSetorComercialFiltro());
		}
		if(form.getQuadraInicialFiltro()!=null && !form.getQuadraInicialFiltro().equals("")){
			relatorioAnaliseConsumo.addParametro("quadraInicial",
					form.getQuadraInicialFiltro());
		}
		if(form.getQuadraFinalFiltro()!=null && !form.getQuadraFinalFiltro().equals("")){
			relatorioAnaliseConsumo.addParametro("quadraFinal",
					form.getQuadraFinalFiltro());
		}
		if(form.getRotaFiltro()!=null && !form.getRotaFiltro().equals("")){
			relatorioAnaliseConsumo.addParametro("rota",
					form.getRotaFiltro());
		}
		if(form.getIdUsuarioAlteracao()!=null && !form.getIdUsuarioAlteracao().equals("")){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			
			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.ID,form.getIdUsuarioAlteracao()));
			
			Collection usuarios = fachada.pesquisar(filtroUsuario,Usuario.class.getName());
			
			Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(usuarios);
			
			if(usuario!=null){
				relatorioAnaliseConsumo.addParametro("nomeUsuario",
						usuario.getNomeUsuario());
				relatorioAnaliseConsumo.addParametro("loginUsuario",
						usuario.getLogin());
			}
		}
		if(form.getIndicadorImovelCondominioFiltro()!=null){
			String valor = form.getIndicadorImovelCondominioFiltro();
			if(valor.equalsIgnoreCase("S")){
				relatorioAnaliseConsumo.addParametro("indicadorImovelCondominio",
					"SIM");
			}else if(valor.equalsIgnoreCase("N")){
				relatorioAnaliseConsumo.addParametro("indicadorImovelCondominio",
					"N�O");
			}else{
				relatorioAnaliseConsumo.addParametro("indicadorImovelCondominio",
					"TODOS");
			}
		}
		if(form.getIndicadorDebitoAutomatico()!=null){
			String valor = form.getIndicadorDebitoAutomatico();
			if(valor.equalsIgnoreCase("S")){
				relatorioAnaliseConsumo.addParametro("indicadorDebitoAutomatico",
					"SIM");
			}else if(valor.equalsIgnoreCase("N")){
				relatorioAnaliseConsumo.addParametro("indicadorDebitoAutomatico",
					"N�O");
			}else{
				relatorioAnaliseConsumo.addParametro("indicadorDebitoAutomatico",
					"TODOS");
			}
		}
		if(form.getIndicadorImovelCondominioFiltro()!=null){
			String valor = form.getIndicadorAnalisado();
			if(valor.equals("1")){
				relatorioAnaliseConsumo.addParametro("indicadorAnalisado",
					"SIM");
			}else if(valor.equals("2")){
				relatorioAnaliseConsumo.addParametro("indicadorAnalisado",
					"N�O");
			}else{
				relatorioAnaliseConsumo.addParametro("indicadorAnalisado",
					"TODOS");
			}
		}
		if(form.getPerfilImovelFiltro()!=null && !form.getPerfilImovelFiltro().equals("-1")){
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(
					FiltroImovelPerfil.ID,form.getPerfilImovelFiltro()[0]));
			
			Collection imovelperfis = fachada.pesquisar(filtroImovelPerfil,ImovelPerfil.class.getName());
		
			ImovelPerfil imovelPerfil = (ImovelPerfil) Util.retonarObjetoDeColecao(imovelperfis);
			
			if(imovelPerfil!=null){
				relatorioAnaliseConsumo.addParametro("imovelPerfil",
						imovelPerfil.getDescricao());
			}
		
		}
		if(form.getCategoriaImovelFiltro()!=null && !form.getCategoriaImovelFiltro().equals("-1")){
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			
			filtroCategoria.adicionarParametro(new ParametroSimples(
					FiltroCategoria.CODIGO,form.getCategoriaImovelFiltro()));
			
			Collection categorias = fachada.pesquisar(filtroCategoria,Categoria.class.getName());
		
			Categoria categoria = (Categoria) Util.retonarObjetoDeColecao(categorias);
			
			if(categoria!=null){
				relatorioAnaliseConsumo.addParametro("categoriaImovel",
						categoria.getDescricao());
			}
		
		}
		if(form.getQuantidadeEconomiaFiltro()!=null 
				&& !form.getQuantidadeEconomiaFiltro().equals("")){
			relatorioAnaliseConsumo.addParametro("quantidadeEconomias",
					form.getQuantidadeEconomiaFiltro());
		}
		if(form.getTipoMedicaoFiltro()!=null 
				&& !form.getTipoMedicaoFiltro().equals("-1")){
			FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();
			
			filtroMedicaoTipo.adicionarParametro(new ParametroSimples(
					FiltroMedicaoTipo.ID,form.getTipoMedicaoFiltro()));
			
			Collection medicoesTipo = fachada.pesquisar(
					filtroMedicaoTipo,MedicaoTipo.class.getName());
			
			MedicaoTipo medicaoTipo = (MedicaoTipo) Util.retonarObjetoDeColecao(medicoesTipo);
		
			if(medicaoTipo!=null){
				relatorioAnaliseConsumo.addParametro("medicaoTipo",
						medicaoTipo.getDescricao());
			}
		}
		if(form.getTipoLigacaoFiltro()!=null 
				&& !form.getTipoLigacaoFiltro().equals("-1")){
			FiltroLigacaoTipo filtroLigacaoTipo = new FiltroLigacaoTipo();

			filtroLigacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroLigacaoTipo.ID,form.getTipoLigacaoFiltro()));
			
			Collection ligacoesTipo = fachada.pesquisar(
					filtroLigacaoTipo,LigacaoTipo.class.getName());
			
			LigacaoTipo ligacaoTipo = (LigacaoTipo) Util.retonarObjetoDeColecao(ligacoesTipo);
		
			if(ligacaoTipo!=null){
				relatorioAnaliseConsumo.addParametro("ligacaoTipo",
						ligacaoTipo.getDescricao());
			}
		}
		
		FiltroLeituraAnormalidade filtroLeituraAnormalidad = new FiltroLeituraAnormalidade();
		filtroLeituraAnormalidad.adicionarParametro(new ParametroSimples(
				FiltroLeituraAnormalidade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroLeituraAnormalidad.setCampoOrderBy(FiltroLeituraAnormalidade.DESCRICAO);
		
		Collection<LeituraAnormalidade> colecaoLeituraAnormalidades = fachada
				.pesquisar(filtroLeituraAnormalidad, LeituraAnormalidade.class
					.getName());
		
		
		if(form.getAnormalidadeLeituraInformadaFiltro()!=null 
				&& !form.getAnormalidadeLeituraInformadaFiltro()[0].equals("-1")){
			FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID,form.getAnormalidadeLeituraInformadaFiltro()[0]));
			
			Collection leiturasAnormalidade  = fachada.pesquisar(
					filtroLeituraAnormalidade,LeituraAnormalidade.class.getName());
			
			LeituraAnormalidade leituraAnormalidade =
				(LeituraAnormalidade) Util.retonarObjetoDeColecao(leiturasAnormalidade);
		
			String leituraAnormalidadeInformada = "";
			if(form.getAnormalidadeLeituraInformadaFiltro().length > 1){
				if(form.getAnormalidadeLeituraInformadaFiltro().length == colecaoLeituraAnormalidades.size()){
					leituraAnormalidadeInformada = "TODOS";
				}else{
					leituraAnormalidadeInformada = "VARIOS";
				}
			}else{
				leituraAnormalidadeInformada = leituraAnormalidade.getDescricao();
			}
			if(leituraAnormalidade!=null){
				relatorioAnaliseConsumo.addParametro("leituraAnormalidadeInformada",
					leituraAnormalidadeInformada);
			}
		}
		if(form.getAnormalidadeLeituraFaturadaFiltro()!=null 
				&& !form.getAnormalidadeLeituraFaturadaFiltro()[0].equals("-1")){
			FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID,form.getAnormalidadeLeituraFaturadaFiltro()[0]));
			
			Collection leiturasAnormalidade  = fachada.pesquisar(
					filtroLeituraAnormalidade,LeituraAnormalidade.class.getName());
			
			LeituraAnormalidade leituraAnormalidade =
				(LeituraAnormalidade) Util.retonarObjetoDeColecao(leiturasAnormalidade);
		
			String leituraAnormalidadeFaturada = "";
			if(form.getAnormalidadeLeituraFaturadaFiltro().length > 1){
				if(form.getAnormalidadeLeituraFaturadaFiltro().length == colecaoLeituraAnormalidades.size()){
					leituraAnormalidadeFaturada = "TODOS";
				}else{
					leituraAnormalidadeFaturada = "VARIOS";
				}
			}else{
				leituraAnormalidadeFaturada = leituraAnormalidade.getDescricao();
			}
			
			if(leituraAnormalidade!=null){
				relatorioAnaliseConsumo.addParametro("leituraAnormalidadeFaturada",
					leituraAnormalidadeFaturada);
			}
		}
		if(form.getAnormalidadeConsumoFiltro()!=null 
				&& !form.getAnormalidadeConsumoFiltro()[0].equals("-1")){
			FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();

			filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroConsumoAnormalidade.ID,form.getAnormalidadeConsumoFiltro()[0]));
			
			Collection anormalidadesConsumo  = fachada.pesquisar(
					filtroConsumoAnormalidade,ConsumoAnormalidade.class.getName());
			
			ConsumoAnormalidade consumoAnormalidade =
				(ConsumoAnormalidade) Util.retonarObjetoDeColecao(anormalidadesConsumo);
		
			// -Erivan- verificar a quantidade de Anormalidades de Consumo;
			filtroConsumoAnormalidade.limparColecaoCaminhosParaCarregamentoEntidades();
			filtroConsumoAnormalidade.limparListaParametros();
			
			filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroConsumoAnormalidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroConsumoAnormalidade.setCampoOrderBy(FiltroConsumoAnormalidade.DESCRICAO);
			Collection<ConsumoAnormalidade> consumoAnormalidades = fachada
					.pesquisar(filtroConsumoAnormalidade, ConsumoAnormalidade.class
							.getName());
			
			String anormalidadeConsumo = "";
			/*Caso tenha selecionado mais de uma anormalidade de consumo verifica se foram selecionada todas,
			 * Caso tenham sido selecionadas todas mostra TODOS no filtro, caso contrario mostra VARIAS*/
			if(form.getAnormalidadeConsumoFiltro().length > 1){
				if(form.getAnormalidadeConsumoFiltro().length == consumoAnormalidades.size()){
					anormalidadeConsumo = "TODOS";
				}else{
					anormalidadeConsumo = "VARIOS";
				}
			}else{
				anormalidadeConsumo = consumoAnormalidade.getDescricao();
			}
			
			if(consumoAnormalidade!=null){
				relatorioAnaliseConsumo.addParametro("anormalidadeConsumo",
					anormalidadeConsumo);
			}
		}
		if(form.getLeituraSituacaoAtualFiltro()!=null 
				&& !form.getLeituraSituacaoAtualFiltro().equals("-1")){
			FiltroLeituraSituacao filtroLeituraSituacao = new FiltroLeituraSituacao();

			filtroLeituraSituacao.adicionarParametro(new ParametroSimples(
					FiltroLeituraSituacao.ID,form.getLeituraSituacaoAtualFiltro()));
			
			Collection situacoesLeitura  = fachada.pesquisar(
					filtroLeituraSituacao,LeituraSituacao.class.getName());
			
			LeituraSituacao leituraSituacao =
				(LeituraSituacao) Util.retonarObjetoDeColecao(situacoesLeitura);
		
			if(leituraSituacao!=null){
				relatorioAnaliseConsumo.addParametro("leituraSituacao",
						leituraSituacao.getDescricao());
			}
		}
		if(form.getConsumoFaturadoInicialFiltro()!= null 
				&& !form.getConsumoFaturadoInicialFiltro().equals("") ){
			relatorioAnaliseConsumo.addParametro("consumoFaturadoInicial",
					form.getConsumoFaturadoInicialFiltro());
		}
		if(form.getConsumoFaturadoFinalFiltro()!= null 
				&& !form.getConsumoFaturadoFinalFiltro().equals("") ){
			relatorioAnaliseConsumo.addParametro("consumoFaturadoFinal",
					form.getConsumoFaturadoFinalFiltro());
		}
		if(form.getConsumoMedidoInicialFiltro()!= null 
				&& !form.getConsumoMedidoInicialFiltro().equals("") ){
			relatorioAnaliseConsumo.addParametro("consumoMedidoInicial",
					form.getConsumoMedidoInicialFiltro());
		}
		if(form.getConsumoMedidoFinalFiltro()!= null 
				&& !form.getConsumoMedidoFinalFiltro().equals("") ){
			relatorioAnaliseConsumo.addParametro("consumoMedidoFinal",
					form.getConsumoMedidoFinalFiltro());
		}
		if(form.getConsumoMedioInicialFiltro()!= null 
				&& !form.getConsumoMedioInicialFiltro().equals("") ){
			relatorioAnaliseConsumo.addParametro("consumoMedioInicial",
					form.getConsumoMedioInicialFiltro());
		}
		if(form.getConsumoMedioFinalFiltro()!= null 
				&& !form.getConsumoMedioFinalFiltro().equals("") ){
			relatorioAnaliseConsumo.addParametro("consumoMedioFinal",
					form.getConsumoMedioFinalFiltro());
		}
  }
}
