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
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacionalMunicipio;
import gcom.cadastro.unidade.FiltroUnidadeTipo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacionalMunicipio;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
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
 * @author Ana Maria
 * @date 22/11/2006
 */
public class AtualizarUnidadeOrganizacionalAction extends GcomAction {
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
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Obt�m a fachada
		Fachada fachada = Fachada.getInstancia();
		
        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
		
		/*// ------------ REGISTRAR TRANSA��O ----------------
        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_HIDROMETRO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
        
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_HIDROMETRO_ATUALIZAR);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSA��O ----------------*/

    	AtualizarUnidadeOrganizacionalActionForm form = (AtualizarUnidadeOrganizacionalActionForm) actionForm;
		
    	//UnidadeOrganizacional unidadeNova = new UnidadeOrganizacional();
    	
    	UnidadeOrganizacional unidadeNova = (UnidadeOrganizacional)sessao.getAttribute("unidadeOrganizacional");
    	
    	//unidadeNova.setId(new Integer(form.getId()));
    	 
    	unidadeNova.setIndicadorEsgoto(new Short(form.getUnidadeEsgoto()).shortValue());
		
    	unidadeNova.setIndicadorTramite(new Short(form.getUnidadeAceita()).shortValue());
    	
    	unidadeNova.setIndicadorCentralAtendimento(new Short(form.getIndicadorUnidadeCentralAtendimento()).shortValue());
		
    	unidadeNova.setIndicadorAberturaRa(new Short(form.getUnidadeAbreRegistro()).shortValue());
		
    	unidadeNova.setIndicadorUso(new Short(form.getIndicadorUso()).shortValue());
		
    	if(form.getSigla()!= null){
    	  unidadeNova.setSigla(form.getSigla());
    	}else{
    	  unidadeNova.setSigla(null);	
    	}
    	
    	unidadeNova.setDescricao(form.getDescricao());
		
		String idUnidadeTipo = form.getUnidadeTipo();
		unidadeNova.setUnidadeTipo(this.pesquisarUnidadeTipo(idUnidadeTipo));
		
		String idEmpresa = form.getIdEmpresa();
		unidadeNova.setEmpresa(this.pesquisarEmpresa(idEmpresa));
		
		String idUnidadeSuperior = form.getIdUnidadeSuperior();
		if(idUnidadeSuperior != null && !idUnidadeSuperior.equals("")){
			unidadeNova.setUnidadeSuperior(this.pesquisarUnidadeOrganizacional(idUnidadeSuperior));	
		}else{
			unidadeNova.setUnidadeSuperior(null);		
		}

		String idUnidadeCentralizadora = form.getIdUnidadeCentralizadora();
		if(idUnidadeCentralizadora != null && 
			!idUnidadeCentralizadora.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) && 
			!idUnidadeCentralizadora.equals("")){
			
			unidadeNova.setUnidadeCentralizadora(this.pesquisarUnidadeOrganizacional(idUnidadeCentralizadora));	
		}else{
			unidadeNova.setUnidadeCentralizadora(null);	
		}
		
		
		//...........................................................................................
		// 06/03/2008 - Altera��o solicitada por Fab�ola Ara�jo. 
		// Yara Taciane de Souza.
		//Inclus�o de op��o de tratamento pra Unidade Repavimentadora.
		
		String idUnidadeRepavimentadora = form.getIdUnidadeRepavimentadora();
		if(idUnidadeRepavimentadora != null && 
			!idUnidadeRepavimentadora.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) && 
			!idUnidadeRepavimentadora.equals("")){
			
			unidadeNova.setUnidadeRepavimentadora(this.pesquisarUnidadeOrganizacional(idUnidadeRepavimentadora));	
		}else{
			unidadeNova.setUnidadeRepavimentadora(null);	
		}
	
		//......................................................................................

		String idMeioSolicitacao = form.getMeioSolicitacao();
		if(idMeioSolicitacao != null && 
				!idMeioSolicitacao.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) && 
				!idMeioSolicitacao.equals("")){
				
			unidadeNova.setMeioSolicitacao(this.pesquisarMeioSolicitacao(idMeioSolicitacao));	
		}

		
		if(unidadeNova.getGerenciaRegional() != null){
			
			String idGerenciaRegional = String.valueOf(unidadeNova.getGerenciaRegional().getId());
			
			if(idGerenciaRegional != null && !idGerenciaRegional.equals("")){
				unidadeNova.setGerenciaRegional(this.pesquisarGerenciaRegional(idGerenciaRegional));
			}
		}
		
		
		
		
		fachada.atualizarUnidadeOrganizacional(unidadeNova, this.getUsuarioLogado(httpServletRequest));
		
		//Inserir os novos municipios 
		if ( sessao.getAttribute("colecaoMunicipioSelecionado") != null &&
				!sessao.getAttribute("colecaoMunicipioSelecionado").equals("") ) {
			ArrayList colecaoMunicipioSelecionado = (ArrayList) sessao.getAttribute("colecaoMunicipioSelecionado");
			
			Iterator iteratorColecaoMunicipiosSelecionados = colecaoMunicipioSelecionado.iterator();
			while (iteratorColecaoMunicipiosSelecionados.hasNext() ) {
			
				Municipio municipio = (Municipio) iteratorColecaoMunicipiosSelecionados.next();
				UnidadeOrganizacional unidadeRepav = new UnidadeOrganizacional();
				unidadeRepav.setId(new Integer(unidadeNova.getId()));
				
				UnidadeOrganizacionalMunicipio unidadeOrgMunicipio = new UnidadeOrganizacionalMunicipio();
	
				unidadeOrgMunicipio.setIdMunicipio(municipio);
				unidadeOrgMunicipio.setIdUnidadeRepavimentadora(unidadeRepav);
				unidadeOrgMunicipio.setDataVinculacao(new Date());
				unidadeOrgMunicipio.setDataDesvinculacao(null);
				unidadeOrgMunicipio.setUltimaAlteracao(new Date());
				
				
				FiltroUnidadeOrganizacionalMunicipio filtro = new FiltroUnidadeOrganizacionalMunicipio();
				filtro.adicionarParametro(new ParametroSimples(
						FiltroUnidadeOrganizacionalMunicipio.ID_MUNICIPIO, municipio.getId()));
				filtro.adicionarParametro( new ParametroNulo(
						FiltroUnidadeOrganizacionalMunicipio.DATA_DESVINCULACAO));
				Collection colecao = fachada.pesquisar(filtro , UnidadeOrganizacionalMunicipio.class.getName());
				if (colecao != null && !colecao.isEmpty()) {
					UnidadeOrganizacionalMunicipio unid = (UnidadeOrganizacionalMunicipio) 
						Util.retonarObjetoDeColecao(colecao);
					
					unidadeOrgMunicipio.setId(unid.getId());
					
					fachada.atualizar(unidadeOrgMunicipio);	
				} else {
					fachada.inserir(unidadeOrgMunicipio);
				}
			}
		}
		//Atauliza os Municipios Desvinculados
		if ( sessao.getAttribute("colecaoMunicipiosDesvinculados") != null &&
				!sessao.getAttribute("colecaoMunicipiosDesvinculados").equals("") ) {
			
			ArrayList colecaoMunicipiosDesvinculados = (ArrayList) sessao.getAttribute("colecaoMunicipiosDesvinculados");
			Iterator iteratorColecaoMunicipiosDesvinculados = colecaoMunicipiosDesvinculados.iterator();
			while (iteratorColecaoMunicipiosDesvinculados.hasNext() ) {
			
				UnidadeOrganizacionalMunicipio unidade = (UnidadeOrganizacionalMunicipio)
					iteratorColecaoMunicipiosDesvinculados.next();
				unidade.setDataDesvinculacao(new Date());
				unidade.setUltimaAlteracao(new Date());
				fachada.atualizar(unidade);
			}
		}
		
		montarPaginaSucesso(httpServletRequest, "Unidade Organizacional "+unidadeNova.getDescricao()+" atualizada com sucesso.",
				"Realizar outra Manuten��o de Unidade Organizacional", "exibirFiltrarUnidadeOrganizacionalAction.do?menu=sim");

		return retorno;
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
