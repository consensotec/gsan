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
package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.bean.DadosImovelPreGsanHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.FiltroCadastroOcorrencia;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;
import gcom.util.filtro.ParametroSimplesNotIn;

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
 * [UC1447] - Consultar Im�veis no Ambiente Pr�-GSAN
 * 
 * @author Bruno S� Barreto
 * @created 22/09/2014
 */
public class ExibirConsultarImoveisPreGsanAction extends GcomAction {
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
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarImoveisPreGsanAction");

		ConsultarImoveisPreGsanActionForm form = (ConsultarImoveisPreGsanActionForm) actionForm;

		HttpSession sessao = request.getSession(false);
		
		//Atribui os valores iniciais no formulario
		if ( request.getParameter("menu") != null && request.getParameter("menu").equals("sim") ) {
			form.setIndicadorTipoSelecao("2");
		}
		
		//[IT0001] - Exibir rela��o de empresas
		this.pesquisarEmpresa(request);
		
		//[IT0002] - Exibir rela��o de ocorr�ncia de cadastro
		this.pesquisarOcorrenciaCadastro(request);
		
		if ( request.getParameter("limparQuadras") != null){
			form.setIdQuadraSelecionados(null);
		}
		
		if ( request.getParameter("limpar")!=null && request.getParameter("limpar").equals("localidade") ) {
			form.setIdLocalidade(null);
			form.setDescricaoLocalidade(null);
			form.setIdSetorComercial(null);
			form.setDescricaoSetorComercial(null);
			form.setIdQuadra(null);
			form.setIdQuadraSelecionados(null);	
		} else if ( request.getParameter("limpar")!=null && request.getParameter("limpar").equals("setor") ) {
			form.setIdSetorComercial(null);
			form.setDescricaoSetorComercial(null);
			form.setIdQuadra(null);
			form.setIdQuadraSelecionados(null);
		}
		
		if( request.getParameter("pesquisarCadastradores") != null ){
			if(form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")){
				pesquisarCadastradores(form, request);
			}else{
				sessao.removeAttribute("colecaoLeiturista");
			}
		}		
		if ( request.getParameter("objetoConsulta") != null && !request.getParameter("objetoConsulta").equals("") ) {
			String objetoConsulta = request.getParameter("objetoConsulta");
			if ( objetoConsulta.equals("2") ) {
				this.pesquisarLocalidade( form, request);
			} else if ( objetoConsulta.equals("3") ) {
				this.pesquisarSetorComercial(form, request);
			} else if ( objetoConsulta.equals("4") ) {
				this.pesquisarQuadra(form, request);
			}  else if ( objetoConsulta.equals("limpar") ) {
				form.limpar();
				sessao.removeAttribute("imoveisNovosPreGsan");
				sessao.removeAttribute("imoveisCadastradosPreGsan");
				form.setIndicadorTipoSelecao("2");
				form.setIdCadastroOcorrencia("-1");				
			}
		} 
		this.pesquisarQuadra(form, request);
		
		if(request.getParameter("objetoConsulta") != null && request.getParameter("objetoConsulta").equals("pesquisar")){
			pesquisarImoveis(form , sessao);
		}
	
		//esta condi��o � para tratar a associa��o de alguma matr�cula de im�vel que j� est� cadastrado no gsan ao im�velAtualizacaoCadastral
		//esta situa��o ocorrer� em 2 fluxos, 1. Selecionar a matricula indicada, 2. indicar im�vel duplicado (popup)
		if(request.getParameter("idImovelAtlzCadastral")!=null && request.getParameter("idImovel")!=null){
			associarMatriculaGsanImovelAtlzCadastral(sessao, request.getParameter("idImovelAtlzCadastral"), request.getParameter("idImovel"));
			//adicionar o valor no helper do imovelAtlzCadstral
			//verificar se a matricula informada j� existe no atributo colecaoMatriculas
				//caso n�o exista, adiciona o valor indicado.
		}
		
		return retorno;	
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarEmpresa(HttpServletRequest request) {
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);
		filtroEmpresa.adicionarParametro( new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.SIM));
		filtroEmpresa.adicionarParametro( new ParametroSimples(FiltroEmpresa.INDICADOR_ATUALIZA_CADASTRO, ConstantesSistema.SIM));
		Collection<Empresa> colecaoEmpresa = Fachada.getInstancia().pesquisar(filtroEmpresa, Empresa.class.getName());
		if ( colecaoEmpresa != null && !colecaoEmpresa.isEmpty() ) {
			request.setAttribute("colecaoEmpresa", colecaoEmpresa);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente", null,"Empresa");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarOcorrenciaCadastro(HttpServletRequest request) {
		FiltroCadastroOcorrencia filtroCadastroOcorrencia = new FiltroCadastroOcorrencia(FiltroCadastroOcorrencia.DESCRICAO);
		filtroCadastroOcorrencia.adicionarParametro( new ParametroSimples(FiltroCadastroOcorrencia.INDICADOR_USO, ConstantesSistema.SIM));
		Collection<CadastroOcorrencia> colecaoCadastroOcorrencia = Fachada.getInstancia().pesquisar(filtroCadastroOcorrencia, CadastroOcorrencia.class.getName());
		if ( colecaoCadastroOcorrencia != null && !colecaoCadastroOcorrencia.isEmpty() ) {
			request.setAttribute("colecaoCadastroOcorrencia", colecaoCadastroOcorrencia);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarLocalidade(ConsultarImoveisPreGsanActionForm form , HttpServletRequest request) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, Integer.valueOf(form.getIdLocalidade())));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<Localidade> colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			form.setIdLocalidade(localidade.getId().toString());
			form.setDescricaoLocalidade(localidade.getDescricao());
		} else {
			form.setIdLocalidade(null);
			form.setDescricaoLocalidade("Localidade Inexistente");
			request.setAttribute("localidadeEncontrada",true);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarSetorComercial(ConsultarImoveisPreGsanActionForm form , HttpServletRequest request) {
		if ( form.getIdLocalidade()  != null ) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection<Localidade> colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				form.setIdLocalidade(localidade.getId().toString());
				form.setDescricaoLocalidade(localidade.getDescricao());
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null,"Localidade");
			}
		}
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercial()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.LOCALIDADE, Integer.valueOf(form.getIdLocalidade())));
		Collection<SetorComercial> colecaoSetorComercial = this.getFachada().pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());
		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			form.setIdSetorComercial("" + setorComercial.getCodigo());
			form.setDescricaoSetorComercial(setorComercial.getDescricao());
			this.pesquisarQuadra(form, request);
		} else {
			form.setIdSetorComercial(null);
			form.setDescricaoSetorComercial("Setor Comercial Inexistente");
			request.setAttribute("setorEncontrado", true);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarQuadra(ConsultarImoveisPreGsanActionForm form , HttpServletRequest request) {
		if ( form.getIdLocalidade() != null && !form.getIdLocalidade().equals("") && form.getIdSetorComercial() != null && !form.getIdSetorComercial().equals("") ) {
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			FiltroQuadra filtroQuadraSelecionadas = new FiltroQuadra();
			if ( form.getIdQuadraSelecionados() != null && form.getIdQuadraSelecionados().length > 0 ) {
				Collection<Integer> colecaoQuadrasSel = new ArrayList<Integer>();
				for (int i = 0; i < form.getIdQuadraSelecionados().length; i++) {
					colecaoQuadrasSel.add(form.getIdQuadraSelecionados()[i]);
				}
				filtroQuadra.adicionarParametro(new ParametroSimplesNotIn(FiltroQuadra.NUMERO_QUADRA, colecaoQuadrasSel));
				filtroQuadraSelecionadas.adicionarParametro(new ParametroSimplesIn(FiltroQuadra.NUMERO_QUADRA, colecaoQuadrasSel));
				filtroQuadraSelecionadas.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(form.getIdLocalidade())));
				filtroQuadraSelecionadas.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroQuadraSelecionadas.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(form.getIdSetorComercial())));
				Collection<Quadra> colecaoQuadrasSelecionadas = Fachada.getInstancia().pesquisar(filtroQuadraSelecionadas, Quadra.class.getName());	
				if (colecaoQuadrasSelecionadas != null && !colecaoQuadrasSelecionadas.isEmpty()) {
					request.setAttribute("colecaoQuadrasSelecionadas", colecaoQuadrasSelecionadas);
				} 
			}
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(form.getIdLocalidade())));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(form.getIdSetorComercial())));
			filtroQuadra.setCampoOrderBy(FiltroQuadra.NUMERO_QUADRA);
			Collection<Quadra> colecaoQuadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
			if (colecaoQuadras != null && !colecaoQuadras.isEmpty()) {
				request.setAttribute("colecaoQuadras", colecaoQuadras);
			} 
		}	
	}
	
	private void pesquisarCadastradores(ConsultarImoveisPreGsanActionForm form, HttpServletRequest request){
		Collection<DadosLeiturista> colecaoLeiturista = new ArrayList<DadosLeiturista>();	
		FiltroLeiturista filtroLeiturista = new FiltroLeiturista(FiltroLeiturista.USUARIO_NOME);
		filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.INDICADOR_USO, ConstantesSistema.SIM));
		filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ATUALIZACAO_CADASTRAL, ConstantesSistema.SIM));
		filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.EMPRESA_ID, form.getIdEmpresa()));
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.USUARIO);
		Collection<?> colecao = Fachada.getInstancia().pesquisar(filtroLeiturista,	Leiturista.class.getName());
		if (!Util.isVazioOrNulo(colecao)) {
			Iterator<?> it = colecao.iterator();
			while (it.hasNext()) {
				Leiturista leitu = (Leiturista) it.next();
				DadosLeiturista dadosLeiu = null;
				if(leitu.getUsuario() != null){
					dadosLeiu = new DadosLeiturista(leitu.getId(), leitu.getUsuario().getNomeUsuario());
				}
				if(!colecaoLeiturista.contains(dadosLeiu)){
					if(dadosLeiu!=null){						
						colecaoLeiturista.add(dadosLeiu);
					}
				}
			}
		}
		request.getSession().setAttribute("colecaoLeiturista", colecaoLeiturista);
	}
	
	private void pesquisarImoveis(ConsultarImoveisPreGsanActionForm form , HttpSession sessao) {

		DadosImovelPreGsanHelper parametros = new DadosImovelPreGsanHelper();
		parametros.setParametroEmpresa(form.getIdEmpresa());
		parametros.setParametroLocalidade(form.getIdLocalidade());
		parametros.setParametroSetorComercial(form.getIdSetorComercial());
		parametros.setParametroQuadra(form.getIdQuadraSelecionados());
		parametros.setParametroCadastroOcorrencia(form.getIdCadastroOcorrencia());
		parametros.setParametroTipoSelecao(form.getIndicadorTipoSelecao());
		parametros.setParametroCadastrador(form.getCadastrador());
		
		Collection<DadosImovelPreGsanHelper> colecaoImovelPreGsan = Fachada.getInstancia().pesquisarImoveisPreGsan(parametros);
		
		if ( colecaoImovelPreGsan.size() > 0 ) {
			 
			//verifica o tipo de sele��o
			if ( form.getIndicadorTipoSelecao().equals("1") ) {// im�veis novos
				sessao.setAttribute("imoveisNovosPreGsan", colecaoImovelPreGsan);
				sessao.removeAttribute("imoveisCadastradosPreGsan");
			} else {// im�veis com ocorr�ncia de cadastro
				sessao.setAttribute("imoveisCadastradosPreGsan", colecaoImovelPreGsan);
				sessao.removeAttribute("imoveisNovosPreGsan");
			}
			
		} else {
			throw new ActionServletException( "atencao.pesquisa.nenhumresultado", "exibirConsultarImoveisPreGsanAction.do", null, new String[] {} );
		}
		 
	}
	
	@SuppressWarnings("unchecked")
	private void associarMatriculaGsanImovelAtlzCadastral(HttpSession sessao, String idImovelAtlzCadastral, String idImovelGsan){
		
		Collection<DadosImovelPreGsanHelper> colecaoImovelPreGsan = (Collection<DadosImovelPreGsanHelper>) sessao.getAttribute("imoveisNovosPreGsan");
		
		for(DadosImovelPreGsanHelper itemAtual : colecaoImovelPreGsan){
			
			if(itemAtual.getIdImovelAtualizacaoCadastral().trim().equalsIgnoreCase(idImovelAtlzCadastral.trim())){
				//adicionar o valor no helper do imovelAtlzCadstral
				itemAtual.setMatriculaGsan(idImovelGsan);
				
				//verificar se a matricula informada j� existe no atributo colecaoMatriculas
				//caso n�o exista, adiciona o valor indicado.
				if(itemAtual.getColecaoMatriculaGsan()==null){
					ArrayList<Integer> colecaoMatricula = new ArrayList<Integer>();
					if(!colecaoMatricula.contains(Integer.parseInt(idImovelGsan))){						
						colecaoMatricula.add(Integer.parseInt(idImovelGsan));
					}
					itemAtual.setColecaoMatriculaGsan(colecaoMatricula);
				}else{
					if(!itemAtual.getColecaoMatriculaGsan().contains(Integer.parseInt(idImovelGsan))){						
						itemAtual.getColecaoMatriculaGsan().add(Integer.parseInt(idImovelGsan));
					}
				}
				
			} 
			
		}
		
		//adicionando uma lista de idsImovelAtlzCadastral para n�o ter que varrer a lista de helpers toda na hora de associar uma matricula
		List<Integer> idsImoveisNovosParaAssociarMatriculaExistente = (List<Integer>) sessao.getAttribute("idsImoveisNovosParaAssociarMatriculaExistente");
		if(idsImoveisNovosParaAssociarMatriculaExistente!=null){
			idsImoveisNovosParaAssociarMatriculaExistente.add(Integer.parseInt(idImovelAtlzCadastral));
		}else{
			idsImoveisNovosParaAssociarMatriculaExistente = new ArrayList<Integer>();
			idsImoveisNovosParaAssociarMatriculaExistente.add(Integer.parseInt(idImovelAtlzCadastral));
		}
		sessao.setAttribute("idsImoveisNovosParaAssociarMatriculaExistente", idsImoveisNovosParaAssociarMatriculaExistente);
		
	}
	
}