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

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/ 
package gcom.gui.relatorio.micromedicao;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroFaixaIdade;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroFaixaIdade;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
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
 * [UC1682] - Relatório Sintético de Hidrômetros por Faixa de Idade
 * @author Cesar Medeiros
 * @date 04/06/2015
 */
public class ExibirGerarRelatorioHidrometroPorIdadeAction extends GcomAction {

	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirGerarRelatorioHidrometroPorIdadeAction");
		
		GerarRelatorioHidrometroPorIdadeActionForm form = (GerarRelatorioHidrometroPorIdadeActionForm) actionForm;
		HttpSession sessao = request.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		sessao.setAttribute("colecaoGerenciaRegional", fachada.pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName()));

		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		sessao.setAttribute("colecaoUnidadeNegocio", fachada.pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName()));

		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
		sessao.setAttribute("colecaoImovelPerfil", fachada.pesquisar(filtroImovelPerfil,ImovelPerfil.class.getName()));
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		Collection<Categoria> colecaoCategoria = fachada.pesquisar(filtroCategoria,Categoria.class.getName());
		sessao.setAttribute("colecaoCategoria", colecaoCategoria);
						
		Collection colecaoSubCategoria = new ArrayList();
		if(form.getCategoria()!= null && form.getCategoria().length == 1){			
			String[] categoria = form.getCategoria();		

			if(!categoria[0].equals("-1")){
				FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
				filtroSubCategoria.setCampoOrderBy(filtroSubCategoria.DESCRICAO);
				filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID, categoria[0]));
				colecaoSubCategoria =  fachada.pesquisar(filtroSubCategoria,Subcategoria.class.getName());				
				sessao.setAttribute("colecaoSubCategoria",colecaoSubCategoria);		
			}else{
				sessao.setAttribute("colecaoSubCategoria", colecaoSubCategoria);
			}
		}else{
			sessao.setAttribute("colecaoSubCategoria", colecaoSubCategoria);
		}
		
		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		filtroHidrometroCapacidade.setCampoOrderBy(FiltroHidrometroCapacidade.DESCRICAO);
		sessao.setAttribute("colecaoCapacidade", fachada.pesquisar(filtroHidrometroCapacidade,HidrometroCapacidade.class.getName()));		
		
		FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();
		filtroHidrometroMarca.setCampoOrderBy(FiltroHidrometroMarca.DESCRICAO);
		sessao.setAttribute("colecaoMarca", fachada.pesquisar(filtroHidrometroMarca,HidrometroMarca.class.getName()));		

		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
		filtroLeituraAnormalidade.setCampoOrderBy(FiltroLeituraAnormalidade.DESCRICAO);
		filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_RELATIVO_HIDROMETRO,ConstantesSistema.INDICADOR_RELATIVO_HIDROMETRO));
		sessao.setAttribute("colecaoAnormalidade", fachada.pesquisar(filtroLeituraAnormalidade,LeituraAnormalidade.class.getName()));
	
		String codigoLocalidade = form.getCodigoLocalidade();
		if (codigoLocalidade != null && !codigoLocalidade.trim().equals("")) {
			pesquisarLocalidade(sessao, form);
		}
		// Localidade
		if (form.getCodigoLocalidade() != null && 
				!form.getCodigoLocalidade().equals("") &&
				form.getDescricaoLocalidade() != null && 
				!form.getDescricaoLocalidade().equals("")) {
			sessao.setAttribute("localidadeEncontrada", true);
		}
		
		String codigoSetorComercial = form.getIdSetorComercial();
		if (codigoSetorComercial != null && !codigoSetorComercial.trim().equals("")) {
			pesquisarSetorComercial(sessao, form);
		}
		
		if (form.getIdSetorComercial() != null && 
				!form.getIdSetorComercial().equals("") &&
				form.getDescricaoSetorComercial() != null && 
				!form.getDescricaoSetorComercial().equals("")) {
			sessao.setAttribute("setorComercialEncontrado", true);
		}			
			
		Collection colecaoHidrometroFaixas =  (Collection) sessao.getAttribute("colecaoHidrometroFaixaIdade");
		
		if(colecaoHidrometroFaixas == null){
			FiltroHidrometroFaixaIdade filtro = new FiltroHidrometroFaixaIdade(FiltroHidrometroFaixaIdade.ID);
			colecaoHidrometroFaixas = fachada.pesquisar(filtro, HidrometroFaixaIdade.class.getName());
		}	
		
		sessao.removeAttribute("faixaRemover");			
		sessao.setAttribute("colecaoHidrometroFaixaIdade", colecaoHidrometroFaixas);
		request.setAttribute("reload", "");
			
		return retorno;
	}
	
	private void pesquisarLocalidade(HttpSession sessao, 
			GerarRelatorioHidrometroPorIdadeActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getCodigoLocalidade()));
		
		Collection pesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if (pesquisa != null && !pesquisa.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(pesquisa);
			
			form.setCodigoLocalidade("" + localidade.getId());
			form.setDescricaoLocalidade(localidade.getDescricao());
		} else {
			form.setCodigoLocalidade("");
			form.setDescricaoLocalidade("Localidade Inexistente");
			sessao.removeAttribute("localidadeEncontrada");
		}
	}
	
	private void pesquisarSetorComercial(HttpSession sessao, 
			GerarRelatorioHidrometroPorIdadeActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
			FiltroSetorComercial.LOCALIDADE_ID,form.getCodigoLocalidade()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
			FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercial()));
		
		Collection pesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
		if (pesquisa != null && !pesquisa.isEmpty()) {
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
			
			form.setIdSetorComercial("" + setorComercial.getCodigo());
			form.setDescricaoSetorComercial(setorComercial.getDescricao());
		} else {
			form.setIdSetorComercial("");
			form.setDescricaoSetorComercial("Setor Comercial Inexistente");
			sessao.removeAttribute("setorComercialEncontrado");
		}
	}	
}
