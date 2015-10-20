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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.FiltroOrgaoExpedidorRg;
import gcom.cadastro.cliente.FiltroPessoaSexo;
import gcom.cadastro.cliente.FiltroProfissao;
import gcom.cadastro.cliente.FiltroRamoAtividade;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.cliente.PessoaSexo;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class ExibirInserirClientePessoaAction extends GcomAction {

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
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession session = httpServletRequest.getSession(false);

		// Verifica se o usu�rio escolheu algum tipo de pessoa para que seja
		// mostrada a p�gina de pessoa f�sica ou de jur�dica, se nada estiver 
		// especificado a p�gina selecionada ser� a de pessoa f�sica
		Short tipoPessoa = (Short) clienteActionForm.get("tipoPessoa");
		String tipoPessoaForm =  tipoPessoa.toString() ;

		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

		filtroClienteTipo.adicionarParametro(new ParametroSimples(
				FiltroClienteTipo.ID, tipoPessoaForm));
		tipoPessoa = ((ClienteTipo) fachada.pesquisar(filtroClienteTipo,
				ClienteTipo.class.getName()).iterator().next())
				.getIndicadorPessoaFisicaJuridica();

		ActionForward retorno = actionMapping
				.findForward("inserirClientePessoaFisica");
		
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		
		
		// -------------------------------------------------------------------------------------------
		// Alterado por :  Hugo Leonardo - data : 20/07/2010 
		// Analista :  Adriana Ribeiro.
    	// [UC0007] - [FS0013] - Verificar permiss�o especial
    	// -------------------------------------------------------------------------------------------
		
		// Se o usu�rio n�o tenha permiss�o especial.
		boolean temPermissaoParaIncluirClienteSemCpf = 
			fachada.verificarPermissaoEspecial(PermissaoEspecial.INCLUIR_CLIENTE_SEM_CPF, usuarioLogado);		

		SistemaParametro sistemaParametro = this.getSistemaParametro();
		
		if(sistemaParametro.getIndicadorDocumentoObrigatorio() == ConstantesSistema.NAO.shortValue()){
			temPermissaoParaIncluirClienteSemCpf = true;
		}

		httpServletRequest.setAttribute("temPermissaoParaIncluirClienteSemCpf", temPermissaoParaIncluirClienteSemCpf);
		// -------------------------------------------------------------------------------------------
		
		// GerenciadorPaginas gerenciadorPaginas = (GerenciadorPaginas)
		// sessao.getAttribute("gerenciadorPaginas");
		if (tipoPessoa != null
				&& tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_JURIDICA)) {

			// Pagina pagina = gerenciadorPaginas.getPaginaCorrente();

			// Limpa os atributos do hint da p�gina
			// pagina.removerAtributosPagina();
			// pagina.addAtributoPagina("cnpj", "CNPJ");
			// pagina.addAtributoPagina("idRamoAtividade", "Ramo Atividade");
			// pagina.addAtributoPagina("codigoClienteResponsavel", "C�digo do
			// Cliente Respons�vel");
			// pagina.addAtributoPagina("nomeClienteResponsavel", "Nome do
			// Cliente Respons�vel");

			// Limpar todo o conte�do da p�gina de pessoa f�sica
			clienteActionForm.set("cpf", "");
			clienteActionForm.set("rg", "");
			clienteActionForm.set("dataEmissao", "");
			clienteActionForm.set("idOrgaoExpedidor", new Integer(
					ConstantesSistema.NUMERO_NAO_INFORMADO));
			clienteActionForm.set("idUnidadeFederacao", new Integer(
					ConstantesSistema.NUMERO_NAO_INFORMADO));
			clienteActionForm.set("dataNascimento", "");
			clienteActionForm.set("idProfissao", new Integer(
					ConstantesSistema.NUMERO_NAO_INFORMADO));
			clienteActionForm.set("idPessoaSexo", new Integer(
					ConstantesSistema.NUMERO_NAO_INFORMADO));
			clienteActionForm.set("nomeMae","");

			
			// Prepara a p�gina para Pessoa Jur�dica
			retorno = actionMapping.findForward("inserirClientePessoaJuridica");

			// -------Parte que trata do c�digo quando o usu�rio tecla enter
			String codigoDigitadoEnter = (String) clienteActionForm
					.get("codigoClienteResponsavel");

			// Verifica se o c�digo foi digitado
			if (codigoDigitadoEnter != null
					&& !codigoDigitadoEnter.trim().equals("")) {

				// Manda para a p�gina a informa��o do campo para que seja
				// focado no retorno da pesquisa
				httpServletRequest.setAttribute("nomeCampo",
						"codigoClienteResponsavel");

				FiltroCliente filtroCliente = new FiltroCliente();

				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, codigoDigitadoEnter));
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroCliente
						.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");

				Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
						Cliente.class.getName());

				if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
					// O cliente foi encontrado
					Cliente encontrado = (Cliente) ((List) clienteEncontrado)
							.get(0);

					if (encontrado.getClienteTipo()
							.getIndicadorPessoaFisicaJuridica().equals(
									ClienteTipo.INDICADOR_PESSOA_JURIDICA)) {
						// Verifica se o usu�rio digitou uma pessoa jur�dica
						clienteActionForm.set("codigoClienteResponsavel", ""
								+ encontrado.getId());
						clienteActionForm.set("nomeClienteResponsavel",
								encontrado.getNome());
						httpServletRequest.setAttribute(
								"codigoClienteNaoEncontrado", "true");
					} else {
						// O usu�rio digitou uma pessoa f�sica
						clienteActionForm.set("codigoClienteResponsavel", "");

						throw new ActionServletException(
								"atencao.responsavel.pessoa_juridica");

						// httpServletRequest.setAttribute(
						// "codigoClienteNaoEncontrado", "exception");
						// clienteActionForm.set("nomeClienteResponsavel",
						// "O respons�vel n�o � uma pessoa jur�dica");
					}

				} else {
					clienteActionForm.set("codigoClienteResponsavel", "");
					httpServletRequest.setAttribute(
							"codigoClienteNaoEncontrado", "exception");
					clienteActionForm.set("nomeClienteResponsavel",
							"Cliente inexistente");

				}
			}
			// -------Fim da Parte que trata do c�digo quando o usu�rio tecla
			// enter

			FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade(
					FiltroRamoAtividade.DESCRICAO);

			filtroRamoAtividade.adicionarParametro(new ParametroSimples(
					FiltroRamoAtividade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection ramoAtividades = fachada.pesquisar(filtroRamoAtividade,
					RamoAtividade.class.getName());

			httpServletRequest.setAttribute("ramoAtividades", ramoAtividades);

		} else {

			// Pagina pagina = gerenciadorPaginas.getPaginaCorrente();

			// Limpa os atributos do hint da p�gina
			// pagina.removerAtributosPagina();

			// pagina.addAtributoPagina("cpf", "CPF");
			// pagina.addAtributoPagina("rg", "RG");
			// pagina.addAtributoPagina("dataEmissao", "Data de Emiss�o");
			// pagina.addAtributoPagina("idOrgaoExpedidor", "�rg�o Expedidor");
			// pagina.addAtributoPagina("idUnidadeFederacao", "UF");
			// pagina.addAtributoPagina("dataNascimento", "Data de Nascimento");
			// pagina.addAtributoPagina("idProfissao", "Profiss�o");
			// pagina.addAtributoPagina("idPessoaSexo", "Sexo");

			// Limpa os dados da p�gina de pessoa
			clienteActionForm.set("cnpj", "");
			clienteActionForm.set("idRamoAtividade", new Integer(
					ConstantesSistema.NUMERO_NAO_INFORMADO));
			clienteActionForm.set("codigoClienteResponsavel", "");
			clienteActionForm.set("nomeClienteResponsavel", "");

			// Prepara a p�gina para Pessoa F�sica
			FiltroOrgaoExpedidorRg filtroOrgaoExpedidor = new FiltroOrgaoExpedidorRg(
					FiltroOrgaoExpedidorRg.DESCRICAO_ABREVIADA);
			FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao(
					FiltroUnidadeFederacao.SIGLA);
			FiltroProfissao filtroProfissao = new FiltroProfissao(
					FiltroProfissao.DESCRICAO);
			FiltroPessoaSexo filtroPessoaSexo = new FiltroPessoaSexo(
					FiltroPessoaSexo.DESCRICAO);

			// S� vai mostrar os registros ativos
			filtroOrgaoExpedidor.adicionarParametro(new ParametroSimples(
					FiltroOrgaoExpedidorRg.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroProfissao.adicionarParametro(new ParametroSimples(
					FiltroProfissao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroPessoaSexo.adicionarParametro(new ParametroSimples(
					FiltroPessoaSexo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Faz a pesquisa das cole��es
			Collection orgaosExpedidores = fachada.pesquisar(
					filtroOrgaoExpedidor, OrgaoExpedidorRg.class.getName());

			Collection unidadesFederacao = fachada.pesquisar(
					filtroUnidadeFederacao, UnidadeFederacao.class.getName());

			Collection profissoes = fachada.pesquisar(filtroProfissao,
					Profissao.class.getName());

			Collection pessoasSexos = fachada.pesquisar(filtroPessoaSexo,
					PessoaSexo.class.getName());

			// A cole��o de pessoasSexos � obrigat�ria na p�gina
			if (!(pessoasSexos != null && !pessoasSexos.isEmpty())) {

				throw new ActionServletException("erro.naocadastrado", null,
						"sexo");

			}

			// Seta no request as cole��es
			httpServletRequest.setAttribute("orgaosExpedidores",
					orgaosExpedidores);
			httpServletRequest.setAttribute("unidadesFederacao",
					unidadesFederacao);
			httpServletRequest.setAttribute("profissoes", profissoes);
			httpServletRequest.setAttribute("pessoasSexos", pessoasSexos);

		}
		
		//**********************************************************************
		// CRC2103
		// Autor: Ivan Sergio
		// Data: 02/07/2009
		// Verifica se a tela deve ser exibida como um PopUp
		//**********************************************************************
		if (httpServletRequest.getParameter("POPUP") != null) {
			if (httpServletRequest.getParameter("POPUP").equals("true")) {
				session.setAttribute("POPUP", "true");
			}else {
				session.setAttribute("POPUP", "false");
			}
		}else if (session.getAttribute("POPUP") == null) {
			session.setAttribute("POPUP", "false");
		}
		//**********************************************************************

		return retorno;
	}
}
