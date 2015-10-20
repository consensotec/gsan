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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0443] Pesquisar Registro Atendimento - Exibir
 * 
 * @author Leonardo Regis 
 * @date 12/08/2006
 */
public class ExibirPesquisarRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("registroAtendimentoPesquisar");
		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();
		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		// Form
		PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm = (PesquisarRegistroAtendimentoActionForm) actionForm;
		// Reseta Pesquisa
		if (pesquisarRegistroAtendimentoActionForm.getResetarPesquisaRA().equalsIgnoreCase("true")) {
			pesquisarRegistroAtendimentoActionForm.resetRA();
		}
		//set o per�odo de atendimento
		if(httpServletRequest.getParameter("validaImovel") == null && 
				httpServletRequest.getParameter("validaUnidadeAtendimento") == null &&
				httpServletRequest.getParameter("validaUnidadeAtual") == null &&
				httpServletRequest.getParameter("validaUnidadeSuperior") == null &&
				httpServletRequest.getParameter("validaMunicipio") == null &&
				httpServletRequest.getParameter("validaBairro") == null &&
				httpServletRequest.getParameter("validaLogradouro") == null &&
				httpServletRequest.getParameter("validaEspecificacao") == null){
			//Sugerindo um per�odo para realiza��o da filtragem de um RA
			Integer qtdDias = 30;
			
			Date dataAtual = new Date();
			Date dataSugestao = Util.subtrairNumeroDiasDeUmaData(dataAtual, qtdDias);
			
			pesquisarRegistroAtendimentoActionForm.setPeriodoAtendimentoInicial(Util.formatarData(dataSugestao));
			pesquisarRegistroAtendimentoActionForm.setPeriodoAtendimentoFinal(Util.formatarData(dataAtual));			
		}
		// Matr�cula
		if (pesquisarRegistroAtendimentoActionForm.getMatriculaImovel() != null &&
				!pesquisarRegistroAtendimentoActionForm.getMatriculaImovel().equals("")) {
			getImovel(pesquisarRegistroAtendimentoActionForm, httpServletRequest);
		}
		
		//Usu�rio
		if (pesquisarRegistroAtendimentoActionForm.getLoginUsuario() != null &&
			!pesquisarRegistroAtendimentoActionForm.getLoginUsuario().equals("")) {
			
			getUsuario(pesquisarRegistroAtendimentoActionForm, fachada,
					pesquisarRegistroAtendimentoActionForm.getLoginUsuario(), sessao);
		}		
		
		// Tipo Solicita��o
		getSolicitacaoTipoCollection(sessao, fachada);
		// Especifica��o
		if (pesquisarRegistroAtendimentoActionForm.getTipoSolicitacao() != null &&
				pesquisarRegistroAtendimentoActionForm.getTipoSolicitacao().length > 0) {
			getSolicitacaoTipoEspecificacaoCollection(sessao, fachada, pesquisarRegistroAtendimentoActionForm);
		} else {
			pesquisarRegistroAtendimentoActionForm.setSelectedTipoSolicitacaoSize("0");
		}
		// Unidade de Atendimento
	    if (pesquisarRegistroAtendimentoActionForm.getUnidadeAtendimentoId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getUnidadeAtendimentoId().equals("")) {
			getUnidade(pesquisarRegistroAtendimentoActionForm, httpServletRequest, 1);
		}
	    // Unidade Atual
		if (pesquisarRegistroAtendimentoActionForm.getUnidadeAtualId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getUnidadeAtualId().equals("")) {
			getUnidade(pesquisarRegistroAtendimentoActionForm, httpServletRequest, 2);
		}
		// Unidade Superior
		if (pesquisarRegistroAtendimentoActionForm.getUnidadeSuperiorId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getUnidadeSuperiorId().equals("")) {
			getUnidade(pesquisarRegistroAtendimentoActionForm, httpServletRequest, 3);
		}
		// Munic�pio
		if (pesquisarRegistroAtendimentoActionForm.getMunicipioId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getMunicipioId().equals("")) {
			getMunicipio(pesquisarRegistroAtendimentoActionForm, httpServletRequest);
		}
		// Bairro & �rea do Bairro
		if (pesquisarRegistroAtendimentoActionForm.getBairroId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getBairroId().equals("")) {
			// [FS009] Verificar informa��o do munic�pio
			if (pesquisarRegistroAtendimentoActionForm.getMunicipioId() != null &&
					!pesquisarRegistroAtendimentoActionForm.getMunicipioId().equals("")) {
				getBairro(pesquisarRegistroAtendimentoActionForm, httpServletRequest);
			} else {
				throw new ActionServletException("atencao.filtrar_informar_municipio");
			}
		}
		// Logradouro
		if (pesquisarRegistroAtendimentoActionForm.getLogradouroId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getLogradouroId().equals("")) {
			getLogradouro(pesquisarRegistroAtendimentoActionForm, httpServletRequest);
		}
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaRegistroAtendimento") != null) {
			
			sessao.setAttribute("caminhoRetornoTelaPesquisaRegistroAtendimento",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaRegistroAtendimento"));
			
		}
		
		
		//Pesquisar PopUps
		
		//Imovel
		if (httpServletRequest.getParameter("pesquisarImovel") != null){
			retorno = actionMapping.findForward("pesquisarImovel");
		}
		
		//Unidade Atendimento
		if (httpServletRequest.getParameter("pesquisarUnidadeAtendimento") != null){
			retorno = actionMapping.findForward("pesquisarUnidadeAtendimento");
		}
		
		//Unidade Atual
		if (httpServletRequest.getParameter("pesquisarUnidadeAtual") != null){
			retorno = actionMapping.findForward("pesquisarUnidadeAtual");
		}
		
		//Municipio
		if (httpServletRequest.getParameter("pesquisarMunicipio") != null){
			retorno = actionMapping.findForward("pesquisarMunicipio");
		}
		
		
		//Retorno de PopUps
		if (httpServletRequest.getParameter("idCampoEnviarDados") != null &&
			httpServletRequest.getParameter("descricaoCampoEnviarDados") != null &&
			httpServletRequest.getParameter("tipoConsulta") != null){
			
			String idCampoEnviarDados = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricaoCampoEnviarDados = httpServletRequest.getParameter("descricaoCampoEnviarDados");
			String tipoConsulta = httpServletRequest.getParameter("tipoConsulta");
			
			if (tipoConsulta.equalsIgnoreCase("Municipio")){
				
				pesquisarRegistroAtendimentoActionForm.setMunicipioId(idCampoEnviarDados);
				pesquisarRegistroAtendimentoActionForm.setMunicipioDescricao(descricaoCampoEnviarDados);
				
				sessao.setAttribute("municipioEncontrada", "true");
			}
			
			if (tipoConsulta.equalsIgnoreCase("Bairro")){
				
				pesquisarRegistroAtendimentoActionForm.setBairroCodigo(idCampoEnviarDados);
				getBairro(pesquisarRegistroAtendimentoActionForm, httpServletRequest);
				
			}
			
			if (tipoConsulta.equalsIgnoreCase("Logradouro")){
				
				pesquisarRegistroAtendimentoActionForm.setLogradouroId(idCampoEnviarDados);
				pesquisarRegistroAtendimentoActionForm.setLogradouroDescricao(descricaoCampoEnviarDados);
				
				sessao.setAttribute("logradouroEncontrada", "true");
				
			}
			
			if (tipoConsulta.equalsIgnoreCase("Imovel")){
				
				pesquisarRegistroAtendimentoActionForm.setMatriculaImovel(idCampoEnviarDados);
				pesquisarRegistroAtendimentoActionForm.setInscricaoImovel(descricaoCampoEnviarDados);
				
				sessao.setAttribute("inscricaoImovelEncontrada", "true");
				
			}
			
			if (tipoConsulta.equalsIgnoreCase("unidadeAtendimento")){
				
				pesquisarRegistroAtendimentoActionForm.setUnidadeAtendimentoId(idCampoEnviarDados);
				pesquisarRegistroAtendimentoActionForm.setUnidadeAtendimentoDescricao(descricaoCampoEnviarDados);
				
				sessao.setAttribute("unidadeAtendimentoEncontrada", "true");
				
			}
			
			if (tipoConsulta.equalsIgnoreCase("unidadeAtual")){
				
				pesquisarRegistroAtendimentoActionForm.setUnidadeAtualId(idCampoEnviarDados);
				pesquisarRegistroAtendimentoActionForm.setUnidadeAtualDescricao(descricaoCampoEnviarDados);
				
				sessao.setAttribute("unidadeAtualEncontrada", "true");
				
			}
			
		}

		return retorno;
	}
	
	/**
	 * Recupera Im�vel 
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param pesquisarRegistroAtendimentoActionForm
	 * @param fachada
	 */
	private void getImovel(PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0001] Valida Imovel
		//if (isValidateObject(filtrarRegistroAtendimentoActionForm)) {
			// Filtra Imovel
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, 
					pesquisarRegistroAtendimentoActionForm.getMatriculaImovel()));
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			// Recupera Im�vel
			Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
				sessao.setAttribute("inscricaoImovelEncontrada", "true");
				Imovel imovel = colecaoImovel.iterator().next();
				pesquisarRegistroAtendimentoActionForm.setInscricaoImovel(imovel.getInscricaoFormatada());
			} else {
				sessao.removeAttribute("inscricaoImovelEncontrada");
				pesquisarRegistroAtendimentoActionForm.setMatriculaImovel("");
				pesquisarRegistroAtendimentoActionForm.setInscricaoImovel("Matr�cula inexistente");
			}
			pesquisarRegistroAtendimentoActionForm.setValidaImovel("false");
		//}
	}
	/**
	 * Carrega cole��o de solicita��o tipo
	 *
	 * @author Leonardo Regis
	 * @date 03/08/2006
	 *
	 * @param sessao
	 * @param fachada
	 */
	private void getSolicitacaoTipoCollection(HttpSession sessao, Fachada fachada) {
		// Filtra Solicita��o Tipo
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
		filtroSolicitacaoTipo.setCampoOrderBy(FiltroSolicitacaoTipo.DESCRICAO);

		Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = fachada.pesquisar(filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());
		if (colecaoSolicitacaoTipo != null && !colecaoSolicitacaoTipo.isEmpty()) {
			sessao.setAttribute("colecaoTipoSolicitacao",	colecaoSolicitacaoTipo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Especifica��o");
		}
	}	
	/**
	 * Carrega cole��o de solicita��o tipo especifica��o.
	 *
	 * @author Leonardo Regis
	 * @date 03/08/2006
	 *
	 * @param sessao
	 * @param fachada
	 */
	private void getSolicitacaoTipoEspecificacaoCollection(HttpSession sessao, Fachada fachada, PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm) {
		String[] solicitacaoTipo = pesquisarRegistroAtendimentoActionForm.getTipoSolicitacao();
		pesquisarRegistroAtendimentoActionForm.setSelectedTipoSolicitacaoSize(solicitacaoTipo.length+"");
		if (solicitacaoTipo.length == 1) {
			// Filtra Solicita��o Tipo Especifica��o
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID, solicitacaoTipo[0]));
			filtroSolicitacaoTipoEspecificacao.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);

			Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar( filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			if (colecaoSolicitacaoTipoEspecificacao != null && !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
				sessao.setAttribute("colecaoEspecificacao",	colecaoSolicitacaoTipoEspecificacao);
			} else {
				sessao.setAttribute("colecaoEspecificacao",	new ArrayList<SolicitacaoTipoEspecificacao>());
			}
			
		} else {
			sessao.setAttribute("colecaoEspecificacao",	new ArrayList<SolicitacaoTipoEspecificacao>());
		}
	}	
	/**
	 * Recupera Unidade
	 *
	 * [FS007] Verificar exist�ncia de unidades subordinadas
	 * 
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param pesquisarRegistroAtendimentoActionForm
	 * @param fachada
	 * @param tipo
	 */
	private void getUnidade(PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest, int tipo) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0006] Valida Unidade Atendimento
		//if (isValidateObject(filtrarRegistroAtendimentoActionForm)) {
			String unidadeId = "";
			switch (tipo) {
			case 1:
				unidadeId = pesquisarRegistroAtendimentoActionForm.getUnidadeAtendimentoId();
				pesquisarRegistroAtendimentoActionForm.setUnidadeAtendimentoDescricao(getUnidadeDescricao(pesquisarRegistroAtendimentoActionForm, fachada, unidadeId));
				if(pesquisarRegistroAtendimentoActionForm.getUnidadeAtendimentoDescricao().equalsIgnoreCase("Unidade Inexistente")) {
					sessao.removeAttribute("unidadeAtendimentoEncontrada");
					pesquisarRegistroAtendimentoActionForm.setUnidadeAtendimentoId("");
				} else {
					sessao.setAttribute("unidadeAtendimentoEncontrada","true");
					pesquisarRegistroAtendimentoActionForm.setValidaUnidadeAtendimento("false");
				}
				break;
			case 2:
				unidadeId = pesquisarRegistroAtendimentoActionForm.getUnidadeAtualId();
				pesquisarRegistroAtendimentoActionForm.setUnidadeAtualDescricao(getUnidadeDescricao(pesquisarRegistroAtendimentoActionForm, fachada, unidadeId));				
				if (pesquisarRegistroAtendimentoActionForm.getUnidadeAtualDescricao().equalsIgnoreCase("Unidade Inexistente")) {
					sessao.removeAttribute("unidadeAtualEncontrada");
					pesquisarRegistroAtendimentoActionForm.setUnidadeAtualId("");
				} else {
					sessao.setAttribute("unidadeAtualEncontrada","true");
					pesquisarRegistroAtendimentoActionForm.setValidaUnidadeAtual("false");
				}
				break;
			case 3:
				unidadeId = pesquisarRegistroAtendimentoActionForm.getUnidadeSuperiorId();
				UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
				unidadeOrganizacional.setId(new Integer(unidadeId));
				pesquisarRegistroAtendimentoActionForm.setUnidadeSuperiorDescricao(getUnidadeDescricao(pesquisarRegistroAtendimentoActionForm, fachada, unidadeId));
				if (pesquisarRegistroAtendimentoActionForm.getUnidadeSuperiorDescricao().equalsIgnoreCase("Unidade Inexistente")) {
					sessao.removeAttribute("unidadeSuperiorEncontrada");
					pesquisarRegistroAtendimentoActionForm.setUnidadeSuperiorId("");
				} else {
					// [FS007] Verificar exist�ncia de unidades subordinadas
					fachada.verificarExistenciaUnidadesSubordinadas(unidadeOrganizacional);
					sessao.setAttribute("unidadeSuperiorEncontrada","true");
					pesquisarRegistroAtendimentoActionForm.setValidaUnidadeSuperior("false");
				}
				break;
			default:
				break;
			}
		//}
	}
	/**
	 * Recupera a Unidade Organizacional (Atendimento, Atual e Superior)
	 *
	 * [F0006] Valida Unidade
	 * 
	 * @author Leonardo Regis
	 * @date 04/08/2006
	 *
	 * @param pesquisarRegistroAtendimentoActionForm
	 * @param fachada
	 * @param unidadeId
	 * @return Descri��o da Unidade Filtrada
	 */
	private String getUnidadeDescricao(PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm, Fachada fachada, String unidadeId) {
		// Filtra Unidade
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, unidadeId));
		// Recupera Unidade Organizacional
		Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
			return colecaoUnidadeOrganizacional.iterator().next().getDescricao();
		}
		return "Unidade Inexistente";
	}
	/**
	 * Recupera Munic�pio
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param pesquisarRegistroAtendimentoActionForm
	 * @param fachada
	 */
	private void getMunicipio(PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0008] Valida Munic�pio
		//if (isValidateObject(pesquisarRegistroAtendimentoActionForm)) {
			// Filtra Imovel
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, 
															pesquisarRegistroAtendimentoActionForm.getMunicipioId()));
			//filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("nome");
			// Recupera Munic�pio
			Collection<Municipio> colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				sessao.setAttribute("municipioEncontrada", "true");
				Municipio municipio = colecaoMunicipio.iterator().next();
				pesquisarRegistroAtendimentoActionForm.setMunicipioDescricao(municipio.getNome());
			} else {
				sessao.removeAttribute("municipioEncontrada");
				pesquisarRegistroAtendimentoActionForm.setMunicipioId("");
				pesquisarRegistroAtendimentoActionForm.setMunicipioDescricao("Munic�pio inexistente");
			}
			pesquisarRegistroAtendimentoActionForm.setValidaMunicipio("false");
		//}
	}

	/**
	 * Recupera Bairro
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param pesquisarRegistroAtendimentoActionForm
	 * @param fachada
	 * @param sessao
	 */
	private void getBairro(PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0010] Valida Bairro
		//if (isValidateObject(pesquisarRegistroAtendimentoActionForm)) {
			// Filtra Bairro
			FiltroBairro filtroBairro = new FiltroBairro();
			filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.CODIGO, 
															pesquisarRegistroAtendimentoActionForm.getBairroCodigo()));
			filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.MUNICIPIO_ID, 
															pesquisarRegistroAtendimentoActionForm.getMunicipioId()));
			//filtroBairro.adicionarCaminhoParaCarregamentoEntidade("nome");
			// Recupera Munic�pio
			Collection<Bairro> colecaoBairro = fachada.pesquisar(filtroBairro, Bairro.class.getName());
			if (colecaoBairro != null && !colecaoBairro.isEmpty()) {
				sessao.setAttribute("bairroEncontrada", "true");
				Bairro bairro = colecaoBairro.iterator().next();
				pesquisarRegistroAtendimentoActionForm.setBairroId(bairro.getId().toString());
				pesquisarRegistroAtendimentoActionForm.setBairroDescricao(bairro.getNome());
				carregaBairroArea(bairro.getId(), fachada, sessao);
			} else {
				sessao.removeAttribute("bairroEncontrada");
				pesquisarRegistroAtendimentoActionForm.setBairroId("");
				pesquisarRegistroAtendimentoActionForm.setBairroCodigo("");
				pesquisarRegistroAtendimentoActionForm.setBairroDescricao("Bairro inexistente");
			}
			pesquisarRegistroAtendimentoActionForm.setValidaBairro("false");
		//}
	}

	/**
	 * Recupera Logradouro
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param pesquisarRegistroAtendimentoActionForm
	 * @param fachada
	 */
	private void getLogradouro(PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0011] Valida Logradouro
		//if (isValidateObject(pesquisarRegistroAtendimentoActionForm)) {
			// Filtra Logradouro
			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
			filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, 
															pesquisarRegistroAtendimentoActionForm.getLogradouroId()));
			
			//filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("nome");
			// Recupera Logradouro
			Collection<Logradouro> colecaoLogradouro = fachada.pesquisar(filtroLogradouro, Logradouro.class.getName());
			if (colecaoLogradouro != null && !colecaoLogradouro.isEmpty()) {
				sessao.setAttribute("logradouroEncontrada", "true");
				Logradouro logradouro = colecaoLogradouro.iterator().next();
				pesquisarRegistroAtendimentoActionForm.setLogradouroDescricao(logradouro.getNome());
			} else {
				sessao.removeAttribute("logradouroEncontrada");
				pesquisarRegistroAtendimentoActionForm.setLogradouroId("");
				pesquisarRegistroAtendimentoActionForm.setLogradouroDescricao("Logradouro inexistente");
			}
			pesquisarRegistroAtendimentoActionForm.setValidaLogradouro("false");
		//}
	}
	/**
	 * Carrega �rea do Bairro de acordo com o bairro informado 
	 *
	 * @author Leonardo Regis
	 * @date 04/08/2006
	 *
	 * @param bairroId
	 * @param fachada
	 * @param sessao
	 */
	private void carregaBairroArea(int bairroId, Fachada fachada, HttpSession sessao) {
		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
		filtroBairroArea.adicionarParametro(new ParametroSimples(FiltroBairroArea.ID_BAIRRO, bairroId));
		// Recupera �rea do Bairro
		Collection<BairroArea> colecaoBairroArea = fachada.pesquisar(filtroBairroArea, BairroArea.class.getName());
		if (colecaoBairroArea != null && !colecaoBairroArea.isEmpty()) {
			sessao.setAttribute("colecaoBairroArea", colecaoBairroArea);
		} else {
			sessao.removeAttribute("colecaoBairroArea");
		}
	}
	/**
	 * Valida Objeto com <Enter> da tela
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @return est� validando algum objeto atrav�s do enter?
	 */
	/*private boolean isValidateObject(PesquisarRegistroAtendimentoActionForm form) {
		boolean toReturn = false;
		if (form.getValidaImovel().equalsIgnoreCase("true") || 
				form.getValidaUnidadeAtendimento().equalsIgnoreCase("true") || 
				form.getValidaUnidadeAtual().equalsIgnoreCase("true") || 
				form.getValidaUnidadeSuperior().equalsIgnoreCase("true") || 
				form.getValidaMunicipio().equalsIgnoreCase("true") || 
				form.getValidaBairro().equalsIgnoreCase("true") || 
				form.getValidaLogradouro().equalsIgnoreCase("true")) {
			toReturn = true;
		}
		return toReturn;		
	}*/
	
	
	/**
	 * Recupera o Usu�rio
	 *
	 * @author Raphael Rossiter
	 * @date 11/12/2006
	 *
	 * @param filtrarRegistroAtendimentoActionForm
	 * @param fachada
	 * @param idUsuario
	 * @return Descri��o da Unidade Filtrada
	 */
	private void getUsuario(PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm, 
			Fachada fachada, String idUsuario, HttpSession sessao) {
		
		// Filtra Usuario
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, idUsuario));
		
		
		// Recupera Usu�rio
		Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
			Usuario usuario = colecaoUsuario.iterator().next();
			
			sessao.setAttribute("usuarioEncontrado","true");
			pesquisarRegistroAtendimentoActionForm.setNomeUsuario(usuario.getNomeUsuario());
		} else {
			
			sessao.removeAttribute("usuarioEncontrado");
			pesquisarRegistroAtendimentoActionForm.setLoginUsuario("");
			pesquisarRegistroAtendimentoActionForm.setNomeUsuario("Usu�rio Inexistente");
		}
	}
}