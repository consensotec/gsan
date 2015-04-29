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
package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gsan.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gsan.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.endereco.FiltroLogradouro;
import gsan.cadastro.endereco.Logradouro;
import gsan.cadastro.geografico.Bairro;
import gsan.cadastro.geografico.BairroArea;
import gsan.cadastro.geografico.FiltroBairro;
import gsan.cadastro.geografico.FiltroBairroArea;
import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.projeto.FiltroProjeto;
import gsan.cadastro.projeto.Projeto;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.cobranca.CobrancaDocumento;
import gsan.cobranca.FiltroDocumentoCobranca;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0450] Pesquisar Ordem Servico - Exibir
 * 
 * @author Leonardo Regis
 *
 * @date 04/09/2006
 */
public class ExibirFiltrarOrdemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarOrdemServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		// Form
		FiltrarOrdemServicoActionForm filtrarOrdemServicoActionForm = 
			(FiltrarOrdemServicoActionForm) actionForm;
		
		//Colocado por Raphael Rossiter em 29/01/2007 
		String menu = httpServletRequest.getParameter("menu");
		  
		if (menu != null && !menu.equalsIgnoreCase("")){
			//Sugerindo um per�odo para realiza��o da filtragem de uma OS
			//SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			Date dataAtual = new Date();
			Calendar calendario = new GregorianCalendar();
			calendario.setTime(dataAtual);

			// ******************************************************************
			// Solicitado por Leonardo Vieira sem U.C. Executor: Marcio Roberto *
			// Pega quantidade de dias do m�s atual,  antes tinha fixo 30 dias. * 
			Integer qtdDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH), calendario.get(Calendar.YEAR)));
			
			Date dataSugestao = Util.subtrairNumeroDiasDeUmaData(dataAtual, qtdDias-1);
			
			filtrarOrdemServicoActionForm.setPeriodoGeracaoInicial(Util.formatarData(dataSugestao));
			filtrarOrdemServicoActionForm.setPeriodoGeracaoFinal(Util.formatarData(dataAtual));
			
		}

		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		//[UC0443] - Pesquisar Registro Atendimento
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
			objetoConsulta.trim().equals("1")) {

			// Faz a consulta de Registro Atendimento
			this.pesquisarRegistroAtendimento(filtrarOrdemServicoActionForm);
		}

		//[UC9999] - Pesquisar Documento de Cobran�a
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("2")) {

			// Faz a consulta de Documento Cobran�a
			this.pesquisarDocumentoCobranca(filtrarOrdemServicoActionForm);
		}
		
		//[UC0013] - Pesquisar Imovel
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("3")) {

			// Faz a consulta de Imovel
			this.pesquisarImovel(filtrarOrdemServicoActionForm);
		}

		//[UC0012] - Pesquisar Cliente
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("4")) {

			// Faz a consulta de Cliente
			this.pesquisarCliente(filtrarOrdemServicoActionForm);
		}

		//[UC0376 - Pesquisar Unidade
		if ( (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("5")) ||
			(objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("6")) ||
			(objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("7")) ) {

			// Faz a consulta de Cliente
			this.pesquisarUnidadeOrganizacional(filtrarOrdemServicoActionForm,objetoConsulta);
		}

		//[UC0075] - Pesquisar Municipio
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("8")) {

			// Faz a consulta de Municipio
			this.pesquisarMunicipio(filtrarOrdemServicoActionForm);
		}

		//[UC0141] - Pesquisar Bairro
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("9")) {

			// Faz a consulta de Bairro
			this.pesquisarBairro(filtrarOrdemServicoActionForm,httpServletRequest);
		}

		//[UC0004] - Pesquisar Logradouro
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("10")) {

			// Faz a consulta de logradouro
			this.pesquisarLogradouro(filtrarOrdemServicoActionForm);
		}
		
		//Pesquisar Localidade
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("11")) {

			// Faz a consulta de Localidade
			this.pesquisarLocalidade(filtrarOrdemServicoActionForm,httpServletRequest);
		}
		
		//Pesquisar Setor Comercial
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("12")) {

			// Faz a consulta de Setor Comercial
			this.pesquisarSetorComercial(filtrarOrdemServicoActionForm, httpServletRequest);
		}
		
		
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
			!httpServletRequest.getParameter("tipoConsulta").equals("")) {
			
			String id = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricao = httpServletRequest.getParameter("descricaoCampoEnviarDados");
			
			if (httpServletRequest.getParameter("tipoConsulta").equals("registroAtendimento")) {

				filtrarOrdemServicoActionForm.setNumeroRA(id);
				filtrarOrdemServicoActionForm.setDescricaoRA(descricao);

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("documentoCobranca")) {

				filtrarOrdemServicoActionForm.setDocumentoCobranca(id);
				filtrarOrdemServicoActionForm.setDescricaoDocumentoCobranca(descricao);

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("imovel")) {

				filtrarOrdemServicoActionForm.setMatriculaImovel(id);
				filtrarOrdemServicoActionForm.setInscricaoImovel(descricao);

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("cliente")) {

				filtrarOrdemServicoActionForm.setCodigoCliente(id);
				filtrarOrdemServicoActionForm.setNomeCliente(descricao);

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("unidadeOrganizacional")) {

				if(sessao.getAttribute("tipoUnidade").equals("unidadeGeracao")){
					filtrarOrdemServicoActionForm.setUnidadeGeracao(id);
					filtrarOrdemServicoActionForm.setDescricaoUnidadeGeracao(descricao);
					
				}else if(sessao.getAttribute("tipoUnidade").equals("unidadeAtual")){
					filtrarOrdemServicoActionForm.setUnidadeAtual(id);
					filtrarOrdemServicoActionForm.setDescricaoUnidadeAtual(descricao);
					
				}else{
					filtrarOrdemServicoActionForm.setUnidadeSuperior(id);
					filtrarOrdemServicoActionForm.setDescricaoUnidadeSuperior(descricao);
				}

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("municipio")) {

				filtrarOrdemServicoActionForm.setMunicipio(id);
				filtrarOrdemServicoActionForm.setDescricaoMunicipio(descricao);

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("bairro")) {

				filtrarOrdemServicoActionForm.setCodigoBairro(id);
				filtrarOrdemServicoActionForm.setDescricaoBairro(descricao);

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("logradouro")) {

				filtrarOrdemServicoActionForm.setLogradouro(id);
				filtrarOrdemServicoActionForm.setDescricaoLogradouro(descricao);

			}	
		}		
		
		// Atendimento Motivo Encerramento
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
		Collection<?> colecaoAtendimentoMotivoEncerramento = Fachada.getInstancia().pesquisar(
				filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());
		sessao.setAttribute("colecaoAtendimentoMotivoEncerramento", colecaoAtendimentoMotivoEncerramento);
		
		//Monta a colecao de tipos Servicos
		this.pesquisarTipoServico(httpServletRequest);
		
		// Perfil Imovel
		this.getPerfilImovelCollection(sessao, this.getFachada());
		
		//Seta os request�s encontrados
		this.setaRequest(httpServletRequest,filtrarOrdemServicoActionForm);
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaOrdemServico") != null) {
			
			sessao.setAttribute("caminhoRetornoTelaPesquisaOrdemServico",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaOrdemServico"));
			
		}
		
		if(filtrarOrdemServicoActionForm.getSituacaoProgramacao() == null || 
			filtrarOrdemServicoActionForm.getSituacaoProgramacao().equals("")){
			
			filtrarOrdemServicoActionForm.setSituacaoProgramacao(ConstantesSistema.SET_ZERO.toString());	
		}
		
		/*
		 * Colocado por Raphael Rossiter em 15/10/2009
		 * 
		 * Permitir efetuar a pesquisa das ordens de servi�o pelo projeto
		 */
		Fachada fachada = Fachada.getInstancia();
		
		if (sessao.getAttribute("colecaoProjeto") == null){
			
			FiltroProjeto filtroProjeto = new FiltroProjeto();
			
			Collection colecaoProjeto = fachada.pesquisar(filtroProjeto, Projeto.class.getName());
			
			if (colecaoProjeto != null && !colecaoProjeto.isEmpty()){
				
				sessao.setAttribute("colecaoProjeto", colecaoProjeto);
			}
		}
		
		
		return retorno;
	}
	

	private void pesquisarLocalidade(
			FiltrarOrdemServicoActionForm form, HttpServletRequest httpServletRequest) {
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		// Recebe o valor do campo localidadeOrigemID do formul�rio.
		String localidadeID = (String) form.getIdLocalidade();		
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, localidadeID));
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		// Retorna localidade
		Collection colecaoPesquisa = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {	
			
			form.setIdLocalidade("");
			form.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");		
			
		} else {
			Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
			form.setIdLocalidade(String.valueOf(objetoLocalidade.getId()));
			form.setDescricaoLocalidade(objetoLocalidade.getDescricao());
			httpServletRequest.setAttribute("localidadeEncontrada", "exception");
		}
		
	}
	
	
	private void pesquisarSetorComercial(
			FiltrarOrdemServicoActionForm form, HttpServletRequest httpServletRequest) {
		
		httpServletRequest.setAttribute("localidadeEncontrada", "exception");
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		// Recebe o valor do campo localidadeOrigemID do formul�rio.
		String localidadeID =  form.getIdLocalidade();

		// O campo localidadeOrigemID ser� obrigat�rio
		if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {

			String setorComercialCD =  form.getIdSetorComercial();

			// Adiciona o id da localidade que est� no formul�rio para
			// compor a pesquisa.
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

			// Adiciona o c�digo do setor comercial que esta no formul�rio
			// para compor a pesquisa.
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna setorComercial
			Collection colecaoPesquisa = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				
				form.setDescricaoSetorComercial("SETOR COMERCIAL INEXISTENTE");
				form.setIdSetorComercial("");
			} 
			else {
				
				httpServletRequest.setAttribute("setorComercialEncontrado", "exception");				
				SetorComercial objetoSetorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
				form.setDescricaoSetorComercial(objetoSetorComercial.getDescricao());

			}
		} else {
			// Limpa o campo setorComercialCD do formul�rio
			form.setIdSetorComercial("");
			form.setDescricaoSetorComercial("INFORME A LOCALIDADE");
			httpServletRequest.setAttribute("setorComercialEncontrado", "exception");
		}
		
	}
	

	/**
	 * Pesquisa Im�vel 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarImovel(FiltrarOrdemServicoActionForm form) {

		// Filtra Imovel
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(
				new ParametroSimples(FiltroImovel.ID, 
				form.getMatriculaImovel()));
		
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		
		// Recupera Im�vel
		Collection colecaoImovel = 
			Fachada.getInstancia().pesquisar(filtroImovel, Imovel.class.getName());
	
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			Imovel imovel = 
				(Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
			
			form.setMatriculaImovel(imovel.getId().toString());
			form.setInscricaoImovel(imovel.getInscricaoFormatada());
			
		} else {
			form.setMatriculaImovel("");
			form.setInscricaoImovel("Matr�cula inexistente");
		}
	}

	/**
	 * Pesquisa Registro Atendimento 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarRegistroAtendimento(FiltrarOrdemServicoActionForm form) {
		
		// Filtro para obter o localidade ativo de id informado
		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();

		filtroRegistroAtendimento.adicionarParametro(
			new ParametroSimples(FiltroRegistroAtendimento.ID, 
			new Integer(form.getNumeroRA())));
		
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao");
		

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoRegistros = Fachada.getInstancia()
				.pesquisar(filtroRegistroAtendimento,RegistroAtendimento.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoRegistros != null && !colecaoRegistros.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			RegistroAtendimento registroAtendimento = 
				(RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistros);
			
			form.setNumeroRA(registroAtendimento.getId().toString());
			form.setDescricaoRA(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
			

		} else {

			form.setDescricaoRA("Registro Atendimento inexistente");
			form.setNumeroRA("");

		}
	}

	/**
	 * Pesquisa Documento Cobran�a 
	 *
	 * @author Rafael Pinto
	 * @date 21/08/2006
	 */
	private void pesquisarDocumentoCobranca(FiltrarOrdemServicoActionForm form){

		FiltroDocumentoCobranca filtroDocumentoCobranca = new FiltroDocumentoCobranca();

		filtroDocumentoCobranca.adicionarParametro(
			new ParametroSimples(FiltroDocumentoCobranca.ID, 
			new Integer(form.getDocumentoCobranca())));
		
		filtroDocumentoCobranca.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
		
		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoDocumentoCobranca = Fachada.getInstancia()
				.pesquisar(filtroDocumentoCobranca,CobrancaDocumento.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoDocumentoCobranca != null && !colecaoDocumentoCobranca.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			CobrancaDocumento cobrancaDocumento = 
				(CobrancaDocumento) Util.retonarObjetoDeColecao(colecaoDocumentoCobranca);

			form.setDocumentoCobranca(cobrancaDocumento.getId().toString());
			form.setDescricaoDocumentoCobranca(cobrancaDocumento.getDocumentoTipo().getDescricaoDocumentoTipo());
			

		} else {
			form.setDocumentoCobranca("");
			form.setDescricaoDocumentoCobranca("Documento Cobran�a inexistente");
		}
	}
	
	/**
	 * Pesquisa Cliente 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarCliente(FiltrarOrdemServicoActionForm form) {
		
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(
			new ParametroSimples(FiltroCliente.ID, 
			new Integer(form.getCodigoCliente())));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoCliente = Fachada.getInstancia()
				.pesquisar(filtroCliente,Cliente.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Cliente cliente = 
				(Cliente) Util.retonarObjetoDeColecao(colecaoCliente);

			form.setCodigoCliente(cliente.getId().toString());
			form.setNomeCliente(cliente.getNome());
			

		} else {
			form.setCodigoCliente("");
			form.setNomeCliente("Cliente inexistente");
		}
	}
	
	/**
	 * Pesquisa Unidade Organizacional 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarUnidadeOrganizacional(FiltrarOrdemServicoActionForm form, String objetoConsulta) {
		
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		
		Integer idUnidade = null;

		if(objetoConsulta.equals("5")){
			idUnidade = new Integer(form.getUnidadeGeracao());
		}else if(objetoConsulta.equals("6")){
			idUnidade = new Integer(form.getUnidadeAtual());
		}else{
			idUnidade = new Integer(form.getUnidadeSuperior());
			
		}

		filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(FiltroUnidadeOrganizacional.ID,idUnidade));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoUnidade = Fachada.getInstancia()
				.pesquisar(filtroUnidadeOrganizacional,UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			UnidadeOrganizacional unidadeOrganizacional = 
				(UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);

			if(objetoConsulta.equals("5")){
			
				form.setUnidadeGeracao(unidadeOrganizacional.getId().toString());
				form.setDescricaoUnidadeGeracao(unidadeOrganizacional.getDescricao());
				
			}else if(objetoConsulta.equals("6")){

				form.setUnidadeAtual(unidadeOrganizacional.getId().toString());
				form.setDescricaoUnidadeAtual(unidadeOrganizacional.getDescricao());
				
			}else{
				
				//[FS0009] - Verificar exist�ncia de unidades subordinadas
				filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

				filtroUnidadeOrganizacional.adicionarParametro(
						new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR,idUnidade));
				
				colecaoUnidade = 
					Fachada.getInstancia().pesquisar(filtroUnidadeOrganizacional,UnidadeOrganizacional.class.getName());

				// Verifica se a pesquisa retornou algum objeto para a cole��o
				if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
					form.setUnidadeSuperior(unidadeOrganizacional.getId().toString());
					form.setDescricaoUnidadeSuperior(unidadeOrganizacional.getDescricao());
				}else{
					throw new ActionServletException("atencao.filtrar_ra_sem_unidades_subordinadas");
				}
				
				
			}

		} else {
			if(objetoConsulta.equals("5")){
				
				form.setUnidadeGeracao("");
				form.setDescricaoUnidadeGeracao("Unidade de Gera��o inexistente");
				
			}else if(objetoConsulta.equals("6")){

				form.setUnidadeAtual("");
				form.setDescricaoUnidadeAtual("Unidade Atual inexistente");
				
			}else{
				
				form.setUnidadeSuperior("");
				form.setDescricaoUnidadeSuperior("Unidade Superior inexistente");
				
			}
		}
	}
	
	/**
	 * Pesquisa Municipio 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void pesquisarMunicipio(FiltrarOrdemServicoActionForm form) {
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(
			new ParametroSimples(FiltroMunicipio.ID, 
			new Integer(form.getMunicipio())));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoMunicipio = Fachada.getInstancia()
				.pesquisar(filtroMunicipio,Municipio.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Municipio municipio = 
				(Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);

			form.setMunicipio(municipio.getId().toString());
			form.setDescricaoMunicipio(municipio.getNome());
			

		} else {
			form.setMunicipio("");
			form.setDescricaoMunicipio("Munic�pio inexistente");
		}
	}
	
	/**
	 * Pesquisa Bairro 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void pesquisarBairro(FiltrarOrdemServicoActionForm form,HttpServletRequest httpServletRequest) {
		
		//[FS0013] - Verificar informa��o do mun�cipio
		String codigoMunicipio = form.getMunicipio();
		if(codigoMunicipio == null || codigoMunicipio.equals("")){
			throw new ActionServletException("atencao.filtrar_informar_municipio");	
		}
		
		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.CODIGO, 
			new Integer(form.getCodigoBairro())));

		filtroBairro.adicionarParametro(
				new ParametroSimples(FiltroBairro.MUNICIPIO_ID,new Integer(codigoMunicipio)));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoBairro = Fachada.getInstancia()
				.pesquisar(filtroBairro,Bairro.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Bairro bairro = 
				(Bairro) Util.retonarObjetoDeColecao(colecaoBairro);

			this.montarAreaBairroPorId(httpServletRequest,new Integer(bairro.getId()));			
			
			form.setCodigoBairro(""+bairro.getCodigo());
			form.setIdBairro(""+bairro.getId());
			form.setDescricaoBairro(bairro.getNome());

		} else {
			form.setCodigoBairro(null);
			form.setDescricaoBairro("Bairro inexistente");
		}
	}

	/**
	 * Pesquisa Logradouro 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void pesquisarLogradouro(FiltrarOrdemServicoActionForm form) {
		
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

		filtroLogradouro.adicionarParametro(
			new ParametroSimples(FiltroLogradouro.ID, 
			new Integer(form.getLogradouro())));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoLogradouro = Fachada.getInstancia()
				.pesquisar(filtroLogradouro,Logradouro.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoLogradouro != null && !colecaoLogradouro.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Logradouro logradouro = 
				(Logradouro) Util.retonarObjetoDeColecao(colecaoLogradouro);

			form.setLogradouro(logradouro.getId().toString());
			form.setDescricaoLogradouro(logradouro.getNome());

		} else {
			form.setLogradouro("");
			form.setDescricaoLogradouro("Logradouro inexistente");
		}
	}

	/**
	 * Pesquisa Area do Bairro pelo Id 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void montarAreaBairroPorId(HttpServletRequest request,Integer id){

		
		// Parte que passa as cole��es necess�rias no jsp
		Collection colecaoAreaBairro = new ArrayList();
			
		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
		filtroBairroArea.adicionarParametro(new ParametroSimples(FiltroBairroArea.ID_BAIRRO,id));

		colecaoAreaBairro = 
			Fachada.getInstancia().pesquisar(filtroBairroArea, BairroArea.class.getName());

		if (colecaoAreaBairro != null && !colecaoAreaBairro.isEmpty()) {
			request.setAttribute("colecaoAreaBairro", colecaoAreaBairro);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"�rea do Bairro");
		}
		
	}

	/**
	 * Pesquisa Tipo Servico 
	 *
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 */
	private void pesquisarTipoServico(HttpServletRequest httpServletRequest){
		
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		
		filtroServicoTipo.setConsultaSemLimites(true);
		filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);

		Collection colecaoTipoServico = 
			Fachada.getInstancia().pesquisar(filtroServicoTipo,ServicoTipo.class.getName());


		if (colecaoTipoServico == null || colecaoTipoServico.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Tipo de Servi�o");
		} else {
			httpServletRequest.setAttribute("colecaoTipoServico",colecaoTipoServico);
		}
	}
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			FiltrarOrdemServicoActionForm form){
		
		//Imovel
		if(form.getMatriculaImovel() != null && 
			!form.getMatriculaImovel().equals("") && 
			form.getInscricaoImovel() != null && 
			!form.getInscricaoImovel().equals("")){
			
			httpServletRequest.setAttribute("matriculaImovelEncontrada","true");			
		}

		//Registro Atendimento
		if(form.getNumeroRA() != null && 
			!form.getNumeroRA().equals("") && 
			form.getDescricaoRA() != null && 
			!form.getDescricaoRA().equals("")){
					
			httpServletRequest.setAttribute("numeroRAEncontrada","true");
		}
		
		//Documento Cobran�a
		if(form.getDocumentoCobranca() != null && 
			!form.getDocumentoCobranca().equals("") && 
			form.getDescricaoDocumentoCobranca() != null && 
			!form.getDescricaoDocumentoCobranca().equals("")){
					
			httpServletRequest.setAttribute("documentoCobrancaEncontrada","true");
		}
		
		//Codigo Cliente
		if(form.getCodigoCliente() != null && 
			!form.getCodigoCliente().equals("") && 
			form.getNomeCliente() != null && 
			!form.getNomeCliente().equals("")){
							
			httpServletRequest.setAttribute("codigoClienteEncontrada","true");
		}

		//Unidade Gera��o
		if(form.getUnidadeGeracao() != null && 
			!form.getUnidadeGeracao().equals("") && 
			form.getDescricaoUnidadeGeracao() != null && 
			!form.getDescricaoUnidadeGeracao().equals("")){
								
			httpServletRequest.setAttribute("unidadeGeracaoEncontrada","true");
		}

		//Unidade Atual
		if(form.getUnidadeAtual() != null && 
			!form.getUnidadeAtual().equals("") && 
			form.getDescricaoUnidadeAtual() != null && 
			!form.getDescricaoUnidadeAtual().equals("")){
								
			httpServletRequest.setAttribute("unidadeAtualEncontrada","true");
		}
		
		//Unidade Superior
		if(form.getUnidadeSuperior() != null && 
			!form.getUnidadeSuperior().equals("") && 
			form.getDescricaoUnidadeSuperior() != null && 
			!form.getDescricaoUnidadeSuperior().equals("")){
								
			httpServletRequest.setAttribute("unidadeSuperiorEncontrada","true");
		}
		
		//Municipio
		if(form.getMunicipio() != null && 
			!form.getMunicipio().equals("") && 
			form.getDescricaoMunicipio() != null && 
			!form.getDescricaoMunicipio().equals("")){
								
			httpServletRequest.setAttribute("municipioEncontrada","true");
		}
		
		//Bairro
		if(form.getCodigoBairro() != null && 
			!form.getCodigoBairro().equals("") && 
			form.getDescricaoBairro() != null && 
			!form.getDescricaoBairro().equals("")){
								
			httpServletRequest.setAttribute("bairroEncontrada","true");
		}

		//Logradouro
		if(form.getLogradouro() != null && 
			!form.getLogradouro().equals("") && 
			form.getDescricaoLogradouro() != null && 
			!form.getDescricaoLogradouro().equals("")){
								
			httpServletRequest.setAttribute("logradouroEncontrado","true");
		}
		
		
		
	}
	
	
	/**
	 * Carrega cole��o de Perfil do Im�vel
	 *
	 * @author Daniel Alves
	 * @date 09/07/2010
	 *
	 * @param sessao
	 * @param fachada
	 */
	private void getPerfilImovelCollection(HttpSession sessao, Fachada fachada) {
		// Filtra Solicita��o Tipo
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

		Collection<ImovelPerfil> colecaoPerfilImovel= fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

		if (colecaoPerfilImovel != null && !colecaoPerfilImovel.isEmpty()) {
			sessao.setAttribute("colecaoPerfilImovel",	colecaoPerfilImovel);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Perfil do Imovel");
		}
	}

}