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
package gcom.gui.cadastro.unidade;

import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.FiltroUnidadeTipo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacionalMunicipio;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Rafael Pinto
 * @created 31/07/2006
 */
public class InserirUnidadeOrganizacionalAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();
		
        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
        
		InserirUnidadeOrganizacionalActionForm inserirUnidadeOrganizacionalActionForm = 
			(InserirUnidadeOrganizacionalActionForm) actionForm;

		short indicadorEsgoto = 
			new Short(inserirUnidadeOrganizacionalActionForm.getUnidadeEsgoto()).shortValue();
		
		short indicadorTramite = 
			new Short(inserirUnidadeOrganizacionalActionForm.getUnidadeAceita()).shortValue();
		
		short indicadorAberturaRa = 
			new Short(inserirUnidadeOrganizacionalActionForm.getUnidadeAbreRegistro()).shortValue();
		
		short indicadorCentralAtendimento = 
			new Short(inserirUnidadeOrganizacionalActionForm.getUnidadeCentralAtendimento());
		
		short indicadorTarifaSocial = 
			new Short(inserirUnidadeOrganizacionalActionForm.getUnidadeTarifaSocial()).shortValue();
		
		short indicadorUso = ConstantesSistema.INDICADOR_USO_ATIVO;
		
		String sigla = inserirUnidadeOrganizacionalActionForm.getSigla();
		
		String descricao = inserirUnidadeOrganizacionalActionForm.getDescricao();
		Date ultimaAlteracao = new Date(); 
		
		String idUnidadeTipo = inserirUnidadeOrganizacionalActionForm.getUnidadeTipo();
		UnidadeTipo unidadeTipo = this.pesquisarUnidadeTipo(idUnidadeTipo);
		
		Empresa empresa = null;
		
		if(unidadeTipo.getCodigoTipo().equals(UnidadeTipo.UNIDADE_TIPO_LOCALIDADE) ||
				unidadeTipo.getCodigoTipo().equals(UnidadeTipo.UNIDADE_TIPO_GERENCIA_REGIONAL) || 
				unidadeTipo.getCodigoTipo().equals(UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA) ||
				unidadeTipo.getCodigoTipo().equals(UnidadeTipo.UNIDADE_TIPO_UNIDADE_NEGOCIO)){
				
				FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
				filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADOR_EMPRESA_PRINCIPAL, ConstantesSistema.SIM));
				
				Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
				empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
		} else {
			String idEmpresa = inserirUnidadeOrganizacionalActionForm.getIdEmpresa();
			empresa = this.pesquisarEmpresa(idEmpresa);
		}
		
		String idUnidadeSuperior = inserirUnidadeOrganizacionalActionForm.getIdUnidadeSuperior();
		UnidadeOrganizacional unidadeSuperior = null;
		if(idUnidadeSuperior != null && !idUnidadeSuperior.equals("")){
			unidadeSuperior = this.pesquisarUnidadeOrganizacional(idUnidadeSuperior);	
		}

		String idUnidadeCentralizadora = inserirUnidadeOrganizacionalActionForm.getIdUnidadeCentralizadora();
		UnidadeOrganizacional unidadeCentralizadora = null;
		if(idUnidadeCentralizadora != null && 
			!idUnidadeCentralizadora.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) && 
			!idUnidadeCentralizadora.equals("")){
			
			unidadeCentralizadora = this.pesquisarUnidadeOrganizacional(idUnidadeCentralizadora);	
		}
		
		//...........................................................................................
		//06/03/2008 - Altera��o solicitada por Fab�ola Ara�jo. 
		// Yara Taciane de Souza.
		//8.0 -  Inclus�o de op��o de tratamento pra Unidade Repavimentadora.		
		String idUnidadeRepavimentadora = inserirUnidadeOrganizacionalActionForm.getIdUnidadeRepavimentadora();
		UnidadeOrganizacional unidadeRepavimentadora = null;
		if(idUnidadeRepavimentadora != null && 
			!idUnidadeRepavimentadora.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) && 
			!idUnidadeRepavimentadora.equals("")){
			
			unidadeRepavimentadora = this.pesquisarUnidadeOrganizacional(idUnidadeRepavimentadora);	
		}
		//...........................................................................................
		
		

		String idMeioSolicitacao = inserirUnidadeOrganizacionalActionForm.getMeioSolicitacao();
		MeioSolicitacao meioSolicitacao = null;
		if(idMeioSolicitacao != null && 
				!idMeioSolicitacao.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) && 
				!idMeioSolicitacao.equals("")){
				
			meioSolicitacao = this.pesquisarMeioSolicitacao(idMeioSolicitacao);	
		}

		String idLocalidade = inserirUnidadeOrganizacionalActionForm.getIdLocalidade();
		Localidade localidade = null;
		if(idLocalidade != null && !idLocalidade.equals("")){
			localidade = this.pesquisarLocalidade(idLocalidade);
		}
		
		//...........................................................................................
		//11/12/2008 - Altera��o para inclusao de UnidadeNegocio
		// Victor Cisneiros
		String idUnidadeNegocio = inserirUnidadeOrganizacionalActionForm.getUnidadeNegocio();
		UnidadeNegocio unidadeNegocio = null;
		if(idUnidadeNegocio != null && !idUnidadeNegocio.equals("")){
			unidadeNegocio = this.pesquisarUnidadeNegocio(idUnidadeNegocio);
		}
		//...........................................................................................

		String idGerenciaRegional = inserirUnidadeOrganizacionalActionForm.getGerenciaRegional();
		GerenciaRegional gerenciaRegional = null;
		if(idGerenciaRegional != null && !idGerenciaRegional.equals("")){
			gerenciaRegional = this.pesquisarGerenciaRegional(idGerenciaRegional);
		}
		
		UnidadeOrganizacional unidadeOrganizacional = 
			new UnidadeOrganizacional(
				indicadorEsgoto,
				indicadorTramite,
				descricao,
				sigla,
				ultimaAlteracao,
				indicadorAberturaRa,
				indicadorUso,
				unidadeTipo,
				meioSolicitacao,
				empresa,
				localidade,
				gerenciaRegional,
				unidadeCentralizadora,
				unidadeRepavimentadora,
				unidadeSuperior,
				indicadorCentralAtendimento,
				indicadorTarifaSocial);
		
		unidadeOrganizacional.setUnidadeNegocio(unidadeNegocio);
		
		ArrayList colecaoMunicipioSelecionado = (ArrayList) sessao.getAttribute("colecaoMunicipioSelecionado");
		
		Integer codigoUnidadeOrganizacionalInserido = 
			(Integer) fachada.inserirUnidadeOrganizacional(unidadeOrganizacional, 
					this.getUsuarioLogado(httpServletRequest));
		if ( colecaoMunicipioSelecionado != null ) {
			
			Iterator iteratorHelper = colecaoMunicipioSelecionado.iterator();
			while ( iteratorHelper.hasNext() ) {
				
				Municipio municipio = (Municipio) iteratorHelper.next();
				
				UnidadeOrganizacionalMunicipio unidadeOrgMunicipio = new UnidadeOrganizacionalMunicipio();
				
				UnidadeOrganizacional unidadeRepav = new UnidadeOrganizacional();
				unidadeRepav.setId(codigoUnidadeOrganizacionalInserido);
	
				unidadeOrgMunicipio.setIdMunicipio(municipio);
				unidadeOrgMunicipio.setIdUnidadeRepavimentadora(unidadeRepav);
				unidadeOrgMunicipio.setDataVinculacao(new Date());
				unidadeOrgMunicipio.setDataDesvinculacao(null);
				unidadeOrgMunicipio.setUltimaAlteracao(new Date());
				
				fachada.inserir(unidadeOrgMunicipio);
			}
		}
		
		sessao.removeAttribute("filtrar_manter");

		montarPaginaSucesso(httpServletRequest, "Unidade Organizacional de c�digo " + 
		 		codigoUnidadeOrganizacionalInserido + " inserido com sucesso.",
				"Inserir outra Unidade", "exibirInserirUnidadeOrganizacionalAction.do?menu=sim",
				"exibirAtualizarUnidadeOrganizacionalAction.do?idRegistroAtualizacao="+ 
				codigoUnidadeOrganizacionalInserido, "Atualizar Unidade Inserida");

		return retorno;
	}
	
	private Localidade pesquisarLocalidade(String id) {
		
		Localidade localidade = null;
		
		// Filtro para obter o localidade ativo de id informado
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.ID, 
				new Integer(id)));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoLocalidade = Fachada.getInstancia()
				.pesquisar(filtroLocalidade,Localidade.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

		}
		
		return localidade;
	}
	
	private UnidadeNegocio pesquisarUnidadeNegocio(String id) {
		
		UnidadeNegocio unidadeNegocio = null;
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

		filtroUnidadeNegocio.adicionarParametro(
			new ParametroSimples(FiltroUnidadeNegocio.ID, id));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoUnidadeNegocio = 
			Fachada.getInstancia().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);

		}
		
		return unidadeNegocio;
	}
	
	private UnidadeOrganizacional pesquisarUnidadeOrganizacional(String id) {
		
		UnidadeOrganizacional unidadeOrganizacional = null;
		
		// Filtro para obter unidade organizacional ativo de id informado
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.adicionarParametro(
			new ParametroSimples(FiltroUnidadeOrganizacional.ID, id));

		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
		
		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoUnidade = Fachada.getInstancia()
				.pesquisar(filtroUnidadeOrganizacional,UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			unidadeOrganizacional = 
				(UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);

		}
		
		return unidadeOrganizacional;
	}

	private Empresa pesquisarEmpresa(String id) {
		
		Empresa empresa = null;
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		filtroEmpresa.adicionarParametro(
			new ParametroSimples(FiltroEmpresa.ID, id));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoEmpresa = 
			Fachada.getInstancia().pesquisar(filtroEmpresa,Empresa.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			empresa = 
				(Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);

		}
		
		return empresa;
	}

	private GerenciaRegional pesquisarGerenciaRegional(String id) {
		
		GerenciaRegional gerenciaRegional = null;
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

		filtroGerenciaRegional.adicionarParametro(
			new ParametroSimples(FiltroGerenciaRegional.ID, id));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoGerenciaRegional = 
			Fachada.getInstancia().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoGerenciaRegional != null && !colecaoGerenciaRegional.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			gerenciaRegional = 
				(GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerenciaRegional);

		}
		
		return gerenciaRegional;
	}

	private UnidadeTipo pesquisarUnidadeTipo(String id) {
		
		UnidadeTipo unidadeTipo = null;
		
		FiltroUnidadeTipo filtroUnidadeTipo = new FiltroUnidadeTipo();

		filtroUnidadeTipo.adicionarParametro(
			new ParametroSimples(FiltroGerenciaRegional.ID, id));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoUnidadeTipo = 
			Fachada.getInstancia().pesquisar(filtroUnidadeTipo,UnidadeTipo.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoUnidadeTipo != null && !colecaoUnidadeTipo.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			unidadeTipo = 
				(UnidadeTipo) Util.retonarObjetoDeColecao(colecaoUnidadeTipo);

		}
		
		return unidadeTipo;
	}

	private MeioSolicitacao pesquisarMeioSolicitacao(String id) {
		
		MeioSolicitacao meioSolicitacao = null;
		
		FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();

		filtroMeioSolicitacao.adicionarParametro(
			new ParametroSimples(FiltroMeioSolicitacao.ID, id));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoMeioSolicitacao = 
			Fachada.getInstancia().pesquisar(filtroMeioSolicitacao,MeioSolicitacao.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoMeioSolicitacao != null && !colecaoMeioSolicitacao.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			meioSolicitacao = 
				(MeioSolicitacao) Util.retonarObjetoDeColecao(colecaoMeioSolicitacao);

		}
		
		return meioSolicitacao;
	}
	
}
