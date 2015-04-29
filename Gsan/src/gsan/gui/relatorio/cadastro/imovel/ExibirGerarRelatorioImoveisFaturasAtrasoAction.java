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


package gsan.gui.relatorio.cadastro.imovel;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.ClienteTipo;
import gsan.cadastro.cliente.EsferaPoder;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.cliente.FiltroClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroClienteTipo;
import gsan.cadastro.cliente.FiltroEsferaPoder;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.FiltroCategoria;
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cobranca.CobrancaGrupo;
import gsan.cobranca.CobrancaSituacao;
import gsan.cobranca.FiltroCobrancaGrupo;
import gsan.cobranca.FiltroCobrancaSituacao;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0726] Gerar Relat�rio de Im�veis com Faturas em Atraso
 * 
 * @author Bruno Barros
 *
 * @date 03/12/2007
 */


public class ExibirGerarRelatorioImoveisFaturasAtrasoAction extends GcomAction {
	

//	private static final String CRITERIO_FILTRO_LOCALIZACAO_GEOGRAFICA = "1";
//	private static final String RESPONSAVEL_ATUAL_IMOVEL = "1";
//	private static final String TIPO_AGRUPADA = "A";

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		GerarRelatorioImoveisFaturasAtrasoActionForm form = 
			(GerarRelatorioImoveisFaturasAtrasoActionForm) actionForm;
		
		if(Util.verificarNaoVazio(httpServletRequest.getParameter("menu"))){
			form.setTipo("A");
			form.setCriterioFiltro("1");
			form.setResponsavel("1");
		}

		pesquisarLocalidadeOuSetorComercialOuCLiente(form, httpServletRequest);
		
		carregarComboboxEListbox(form);
		
		definirLocalidadeSetorClienteEncontrados(form,httpServletRequest);
		
		return actionMapping.findForward("exibirGerarRelatorioImoveisFaturasAtraso");
	}

	/**
	 * Esse m�todo tem a l�gica que verifica qual entidade consultar:
	 * Localidade,Setor Comercialou Cliente. Em seguida, chama o m�todo respons�vel
	 * por realizar a consulta correta.
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private void pesquisarLocalidadeOuSetorComercialOuCLiente(
			GerarRelatorioImoveisFaturasAtrasoActionForm form,
			HttpServletRequest httpServletRequest) {

		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		if ( Util.verificarNaoVazio(objetoConsulta)){
			if(isConsultarLocalidade(objetoConsulta)){
				this.pesquisarLocalidade(form,objetoConsulta);				
			}
		
			if(isConsultarSetorComercial(objetoConsulta)){
				this.pesquisarSetorComercial(form,objetoConsulta);				
			}
			
			if(isConsultarCliente(objetoConsulta)){
				this.pesquisarCliente(form,objetoConsulta);				
			}
		}
	}

	/**
	 * 
	 * Esse m�todo invoca os m�todos respons�veis por carregar as cole��es de
	 * todos os combo boxes e list boxes a serem exibidos na tela.
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private void carregarComboboxEListbox(GerarRelatorioImoveisFaturasAtrasoActionForm form) {
		carregarColecaoGerenciasRegionais(form);
		carregarColecaoUnidadesNegocios(form);
		carregarColecaoLigacoesAguaSituacao(form);
		carregarColecaoCategorias(form);
		carregarColecaoPerfisImovel(form);
		carregarColecaoEsferasPoder(form);
		carregarColecaoSituacoesCobranca(form);
		carregarColecaoTiposRelacoes(form);
		carregarColecaoGruposCobranca(form);
		carregarColecaoTiposCliente(form);
	}

	/**
	 *Caso o parametro tenha valor 5 ou 6 retorna true.
	 *
	 *@since 26/08/2009
	 *@author Marlon Patrick
	 */
	private boolean isConsultarCliente(String objetoConsulta) {
		return objetoConsulta.trim().equals("5") || objetoConsulta.trim().equals("6");
	}

	/**
	 *Caso o parametro tenha valor 2 ou 4 retorna true.
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private boolean isConsultarSetorComercial(String objetoConsulta) {
		return objetoConsulta.trim().equals("2") || objetoConsulta.trim().equals("4");
	}

	
	/**
	 *Caso o parametro tenha valor 1 ou 3 retorna true.
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private boolean isConsultarLocalidade(String objetoConsulta) {
		return objetoConsulta.trim().equals("1")|| objetoConsulta.trim().equals("3");
	}
	
	/**
	 * Este m�todo faz a consulta para verificar a existencia do Cliente informado pelo usu�rio,
	 * seja ele superior ou n�o.
	 * 
	 * @author Marlon Patrick
	 * @since 26/08/2009
	 */
	private void pesquisarCliente(GerarRelatorioImoveisFaturasAtrasoActionForm form,String objetoConsulta){

		boolean isConsultarClienteSuperior = objetoConsulta.trim().equals("5");
		
		String idCliente = form.getCodigoCliente();
		
		if(isConsultarClienteSuperior){
			idCliente = form.getCodigoClienteSuperior();
		}
		
    	FiltroCliente filtroCliente = new FiltroCliente();
		
		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.ID, idCliente));
		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<Cliente> colecaoCliente = getFachada().pesquisar(filtroCliente,
				Cliente.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoCliente)) {
			if(isConsultarClienteSuperior){
				form.setCodigoClienteSuperior(null);
				form.setNomeClienteSuperior(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);				
			}else{
				form.setCodigoCliente(null);
				form.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);
			}
			
			return;
		}
		
		Cliente cliente = colecaoCliente.iterator().next();
		
		if(isConsultarClienteSuperior){
			form.setCodigoClienteSuperior(cliente.getId().toString());
			form.setNomeClienteSuperior(cliente.getNome());
		}else{
			form.setCodigoCliente(cliente.getId().toString());
			form.setNomeCliente(cliente.getNome());
		}		
	}

	/**
	 * Este m�todo faz a consulta para verificar a existencia da Localidade informada pelo usu�rio,
	 * seja ela inicial ou final.
	 * 
	 * @ultimaModifica��o Marlon Patrick
	 * @author Bruno Barros
	 * @since 28/11/2007
	 */
	private void pesquisarLocalidade(GerarRelatorioImoveisFaturasAtrasoActionForm form,String objetoConsulta){

		boolean isConsultarLocalidadeInicial = objetoConsulta.trim().equals("1");
		
		String idLocalidade = form.getLocalidadeFinal();
		
		if(isConsultarLocalidadeInicial){
			idLocalidade = form.getLocalidadeInicial();
		}
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,idLocalidade));
		
		Collection<Localidade> colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoLocalidade)) {
			if(isConsultarLocalidadeInicial){
				form.setLocalidadeInicial(null);
				form.setNomeLocalidadeInicial("Localidade Inicial inexistente");
				
				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal(null);
			}else{
				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal("Localidade Final inexistente");
			}
			
			return;
		}
		
		Localidade localidade = colecaoLocalidade.iterator().next();
		
		if(isConsultarLocalidadeInicial){
			form.setLocalidadeInicial(localidade.getId().toString());
			form.setNomeLocalidadeInicial(localidade.getDescricao());
		}
		
		form.setLocalidadeFinal(localidade.getId().toString());
		form.setNomeLocalidadeFinal(localidade.getDescricao());		
	}
	
	
	/**
	 * Este m�todo faz a consulta para verificar a existencia do Setor Comercial informado pelo usu�rio,
	 * seja ele inicial ou final.
	 * 
	 * @ultimaModifica��o Marlon Patrick
	 * @author Bruno Barros
	 * @since 28/11/2007
	 */
	private void pesquisarSetorComercial(GerarRelatorioImoveisFaturasAtrasoActionForm form,
		String objetoConsulta) {

		boolean isConsultarSetorComercialInicial = objetoConsulta.trim().equals("2");

		String idLocalidade = form.getLocalidadeFinal();
		String idSetorComercial = form.getSetorComercialFinal();
		
		if(isConsultarSetorComercialInicial){
			idLocalidade = form.getLocalidadeInicial();
			idSetorComercial = form.getSetorComercialInicial();
		}

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,idSetorComercial));
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.LOCALIDADE,idLocalidade));
		

		Collection<SetorComercial> colecaoSetorComercial = 
			this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoSetorComercial)) {

			if(isConsultarSetorComercialInicial){
				form.setSetorComercialInicial(null);
				form.setNomeSetorComercialInicial("Setor Comercial Inicial inexistente");
				
				form.setSetorComercialFinal(null);
				form.setNomeSetorComercialFinal(null);
			}else{
				form.setSetorComercialFinal(null);
				form.setNomeSetorComercialFinal("Setor Comercial Final inexistente");
			}
			
			return;
		}
		
		SetorComercial setorComercial = colecaoSetorComercial.iterator().next();
		
		if(isConsultarSetorComercialInicial){
			form.setSetorComercialInicial(""+setorComercial.getCodigo());
			form.setNomeSetorComercialInicial(setorComercial.getDescricao());
		}

		form.setSetorComercialFinal(""+setorComercial.getCodigo());
		form.setNomeSetorComercialFinal(setorComercial.getDescricao());

	}
	
	/**
	 * M�todo consulta as Gerencias Regionais ativas
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 * @ultimaModifrica��o Marlon Patrick
	 * @author Bruno Barros
	 * @date 28/11/2007
	 */
	private void carregarColecaoGerenciasRegionais(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<GerenciaRegional> colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());


		if ( Util.isVazioOrNulo(colecaoGerenciaRegional)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Ger�ncia Regional");
		}
		
		form.setColecaoGerenciasRegionais(colecaoGerenciaRegional);
	}
	
	
	/**
	 * M�todo consulta as Unidades de Neg�cio ativas
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 * @ultimaModifrica��o Marlon Patrick
	 * @author Bruno Barros
	 * @since 28/11/2007
	 */
	private void carregarColecaoUnidadesNegocios(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		
		if(Util.verificarNaoVazio(form.getGerenciaRegional())
				&& !form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, 
					form.getGerenciaRegional()));		
		}

		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());


		if ( Util.isVazioOrNulo(colecaoUnidadeNegocio)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Unidade de Neg�cio");
		}
		
		form.setColecaoUnidadesNegocios(colecaoUnidadeNegocio);
	}
	
	/**
	 * M�todo consulta as Situa��es de Liga��o de �gua ativas
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 * @ultimaModifrica��o Marlon Patrick
	 * @author Bruno Barros
	 * @since 28/11/2007
	 */
	private void carregarColecaoLigacoesAguaSituacao(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
		
		filtroLigacaoAguaSituacao.setConsultaSemLimites(true);
		filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
		filtroLigacaoAguaSituacao.adicionarParametro(
				new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<LigacaoAguaSituacao> colecaoSituacaoLigacaoAgua = 
			this.getFachada().pesquisar(filtroLigacaoAguaSituacao,LigacaoAguaSituacao.class.getName());


		if ( Util.isVazioOrNulo(colecaoSituacaoLigacaoAgua)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Liga�ao de �gua");
		}
		
		form.setColecaoSituacoesLigacaoAgua(colecaoSituacaoLigacaoAgua);
	}	

	
	/**
	 * M�todo consulta as Situa��es de Cobran�a ativas
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private void carregarColecaoSituacoesCobranca(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroCobrancaSituacao filtroSituacaoCobranca = new FiltroCobrancaSituacao();
		
		filtroSituacaoCobranca.setConsultaSemLimites(true);
		filtroSituacaoCobranca.setCampoOrderBy(FiltroCobrancaSituacao.DESCRICAO);
		filtroSituacaoCobranca.adicionarParametro(
				new ParametroSimples(FiltroCobrancaSituacao.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<CobrancaSituacao> colecaoSituacoesCobranca = 
			this.getFachada().pesquisar(filtroSituacaoCobranca, CobrancaSituacao.class.getName());


		if ( Util.isVazioOrNulo(colecaoSituacoesCobranca)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Situa��o de Cobran�a");
		}
		
		form.setColecaoSituacoesCobranca(colecaoSituacoesCobranca);
	}

	/**
	 * M�todo consulta as Situa��es de Cobran�a ativas
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private void carregarColecaoTiposRelacoes(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
		
		filtroClienteRelacaoTipo.setConsultaSemLimites(true);
		filtroClienteRelacaoTipo.setCampoOrderBy(FiltroClienteRelacaoTipo.DESCRICAO);
		filtroClienteRelacaoTipo.adicionarParametro(
				new ParametroSimples(FiltroClienteRelacaoTipo.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<ClienteRelacaoTipo> colecaoTiposRelacoes = 
			this.getFachada().pesquisar(filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getName());


		if ( Util.isVazioOrNulo(colecaoTiposRelacoes)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Situa��o de Cobran�a");
		}
		
		form.setColecaoTiposRelacoes(colecaoTiposRelacoes);
	}
	
	
	/**
	 * M�todo consulta os Grupos de Cobran�a
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 *@since 19/06/2012
	 *@author Anderson Cabral
	 */
	private void carregarColecaoGruposCobranca(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
		filtroCobrancaGrupo.setConsultaSemLimites(true);
		filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
		filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.INDICADOR_USO, 
												ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<CobrancaGrupo> colecaoGruposCobranca = 
				this.getFachada().pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
		
		if(Util.isVazioOrNulo(colecaoGruposCobranca)){
			throw new ActionServletException("atencao.naocadastrado", null, "Grupo de cobran�a");
		}else{
			form.setColecaoGruposCobranca(colecaoGruposCobranca);
		}
	}

	/**
	 * M�todo consulta as categorias ativas
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 * @ultimaModifrica��o Marlon Patrick
	 * @author Bruno Barros
	 * @since 04/12/2007
	 */
	private void carregarColecaoCategorias(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.setConsultaSemLimites(true);
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		filtroCategoria.adicionarParametro(
				new ParametroSimples(FiltroCategoria.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<Categoria> colecaoCategoria = 
			this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());


		if ( Util.isVazioOrNulo(colecaoCategoria)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Categoria");
		}
		
		form.setColecaoCategorias(colecaoCategoria);
	}
	
	/**
	 * M�todo que consulta os perfis dos im�veis
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 * @author Diogo Peixoto
	 * @since 11/03/2011
	 */
	private void carregarColecaoPerfisImovel(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroImovelPerfil filtroPerfisImovel = new FiltroImovelPerfil();
		
		filtroPerfisImovel.setConsultaSemLimites(true);
		filtroPerfisImovel.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
		filtroPerfisImovel.adicionarParametro(
				new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<ImovelPerfil> colecaoPerfisImovel = 
			this.getFachada().pesquisar(filtroPerfisImovel, ImovelPerfil.class.getName());

		if ( Util.isVazioOrNulo(colecaoPerfisImovel)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Perfil Im�vel");
		}
		
		form.setColecaoPerfisImovel(colecaoPerfisImovel);
	}
	
	
	/**
	 * M�todo consulta os tipos de clientes
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 *
	 * @author Fernando L�cio
	 * @since 20/02/2013
	 */
	
	public void carregarColecaoTiposCliente(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroClienteTipo filtroClientesTipo = new FiltroClienteTipo();
		filtroClientesTipo.setConsultaSemLimites(true);
		filtroClientesTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);
		filtroClientesTipo.adicionarParametro(new ParametroSimples(filtroClientesTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection <ClienteTipo> colecaoTiposCliente = this.getFachada().pesquisar(filtroClientesTipo, ClienteTipo.class.getName());
		
		
		if(Util.isVazioOrNulo(colecaoTiposCliente)){
			throw new ActionServletException("atencao.naocadastrado", null , "Cliente Tipo");
		}
		
		form.setColecaoTiposCliente(colecaoTiposCliente);
		
		
	}
	
	/**
	 * M�todo consulta as esferas do poder ativas
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 * @ultimaModifrica��o Marlon Patrick
	 * @author Fl�vio Leonardo
	 * @since 28/11/2007
	 */
	private void carregarColecaoEsferasPoder(GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
		filtroEsferaPoder.setConsultaSemLimites(true);
		filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
		filtroEsferaPoder.adicionarParametro(
				new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<EsferaPoder> colecaoEsferaPoder = 
			this.getFachada().pesquisar(filtroEsferaPoder,EsferaPoder.class.getName());

		if ( Util.isVazioOrNulo(colecaoEsferaPoder)) {
			throw new ActionServletException("atencao.naocadastrado", null,"Esfera de Poder");
		}
		
		form.setColecaoEsferasPoder(colecaoEsferaPoder);
	}
	
	/**
	 * O m�todo seta no request os atributos que a JSP usar� para identificar
	 * se a Localidade, Setor e Cliente foram encontrados ou n�o.
	 *
	 * @ultimaModifica��o Marlon Patrick
	 * @author Bruno Barros
	 * @date 28/11/2007
	 */
	private void definirLocalidadeSetorClienteEncontrados(GerarRelatorioImoveisFaturasAtrasoActionForm form,
			HttpServletRequest httpServletRequest){
		
		if( Util.verificarNaoVazio(form.getLocalidadeInicial())
				&& Util.verificarNaoVazio(form.getNomeLocalidadeInicial())){
					
			httpServletRequest.setAttribute("localidadeInicialEncontrada","true");
		}
		
		if(Util.verificarNaoVazio(form.getLocalidadeFinal())
				&& Util.verificarNaoVazio(form.getNomeLocalidadeFinal())){								

			httpServletRequest.setAttribute("localidadeFinalEncontrada","true");
		}

		
		if(Util.verificarNaoVazio(form.getSetorComercialInicial())
				&& Util.verificarNaoVazio(form.getNomeSetorComercialInicial())){
					
			httpServletRequest.setAttribute("setorComercialInicialEncontrado","true");
		}
		
		if(Util.verificarNaoVazio(form.getSetorComercialFinal())
				&& Util.verificarNaoVazio(form.getNomeSetorComercialFinal())){
							
			httpServletRequest.setAttribute("setorComercialFinalEncontrado","true");
		}

		if(Util.verificarNaoVazio(form.getCodigoClienteSuperior())
				&& Util.verificarNaoVazio(form.getNomeClienteSuperior())){
							
			httpServletRequest.setAttribute("codigoClienteSuperiorEncontrado","true");
		}

		if(Util.verificarNaoVazio(form.getCodigoCliente())
				&& Util.verificarNaoVazio(form.getNomeCliente())){
							
			httpServletRequest.setAttribute("codigoClienteEncontrado","true");
		}

	}

}
