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
package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela inser��o do logradouro
 * 
 * @author S�vio Luiz, Raphael Rossiter
 * @created 15 de Julho de 2005
 */
public class InserirLogradouroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		LogradouroActionForm logradouroActionForm = (LogradouroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Recupera a vari�vel para indicar se o usu�rio apertou o bot�o de
		// confirmar da tela de
		// confirma��o do wizard
		String confirmado = httpServletRequest.getParameter("confirmado");

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_LOGRADOURO_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		/*
		 * [UC0107] Registrar Transa��o Operacao operacao = new Operacao();
		 * operacao.setId(Operacao.OPERACAO_LOGRADOURO_INSERIR);
		 * 
		 * OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		 * operacaoEfetuada.setOperacao(operacao);
		 */
		
		//[FS0012] - Verificar exist�ncia de Logradouro com mesmo nome
		String confirmarLogradouroMesmoNome = httpServletRequest.getParameter("confirmarLogradouroMesmoNome");
		if (confirmado == null){
			if (confirmarLogradouroMesmoNome == null || !confirmarLogradouroMesmoNome.trim().equalsIgnoreCase("ok")) {
				Collection<Logradouro> colLogradouros = fachada.pesquisarLogradouroMesmoNome(logradouroActionForm.getNome(), 
						new Integer(logradouroActionForm.getIdMunicipio()));
				if (colLogradouros != null && !colLogradouros.equals(null)){
					sessao.setAttribute("telaInserir", "ok");
					return retorno = actionMapping.findForward("filtrarLogradouroMesmoNomeInserir");
				}
			}else{
				sessao.removeAttribute("colecaoLogradouro");
				sessao.removeAttribute("telaInserir");
				retorno = actionMapping.findForward("telaSucesso");
			}
		}
		
		// Bairro bairro = null;
		Municipio municipio = null;

		String idMunicipio = (String) logradouroActionForm.getIdMunicipio();
		// String codigoBairro = (String)
		// logradouroActionForm.getCodigoBairro();

		if (idMunicipio != null && !idMunicipio.trim().equals("")
				&& Integer.parseInt(idMunicipio) > 0) {

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idMunicipio));

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				municipio = (Municipio) ((List) municipioEncontrado).get(0);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null, "munic�pio");
			}
		}

		// Verifica se o c�digo foi digitado
		/*
		 * if (codigoBairro != null && !codigoBairro.trim().equals("") &&
		 * Integer.parseInt(codigoBairro) > 0) { FiltroBairro filtroBairro = new
		 * FiltroBairro();
		 * 
		 * filtroBairro.adicionarParametro(new ParametroSimples(
		 * FiltroBairro.CODIGO, codigoBairro));
		 * filtroBairro.adicionarParametro(new ParametroSimples(
		 * FiltroBairro.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		 *  // verifica se o bairro pesquisado � de um municipio existente if
		 * (idMunicipio != null && !idMunicipio.trim().equals("") &&
		 * Integer.parseInt(idMunicipio) > 0) {
		 * 
		 * filtroBairro.adicionarParametro(new ParametroSimples(
		 * FiltroBairro.MUNICIPIO_ID, idMunicipio)); }
		 * 
		 * Collection bairroEncontrado = fachada.pesquisar(filtroBairro,
		 * Bairro.class.getName());
		 * 
		 * if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) { //O
		 * bairro foi encontrado bairro = ((Bairro) ((List)
		 * bairroEncontrado).get(0));
		 *  } else { throw new ActionServletException(
		 * "atencao.pesquisa.nenhumresultado", null, "bairro"); } }
		 */

		LogradouroTipo logradouroTipo = new LogradouroTipo();

		if (logradouroActionForm.getIdTipo() != null
				&& !logradouroActionForm.getIdTipo().equals(0)) {

			logradouroTipo.setId(new Integer(""
					+ logradouroActionForm.getIdTipo()));
		} else {
			throw new ActionServletException("atencao.required", null, "Tipo");
		}

		LogradouroTitulo logradouroTitulo = null;

		if (logradouroActionForm.getIdTitulo() != null
				&& !logradouroActionForm.getIdTitulo().equals(0)) {

			logradouroTitulo = new LogradouroTitulo();
			logradouroTitulo.setId(new Integer(""
					+ logradouroActionForm.getIdTitulo()));
		}

		Short indicadorDeUso = ConstantesSistema.INDICADOR_USO_ATIVO;

		Collection colecaoBairros = (Collection) sessao
				.getAttribute("colecaoBairrosSelecionadosUsuario");
		Collection colecaoCeps = (Collection) sessao
				.getAttribute("colecaoCepSelecionadosUsuario");

		if (colecaoBairros == null || colecaoBairros.isEmpty()) {
			throw new ActionServletException("atencao.required", null,
					"Bairro(s)");
		}

		if (colecaoCeps == null || colecaoCeps.isEmpty()) {
			throw new ActionServletException("atencao.required", null, "CEP(s)");
		}

		Logradouro logradouro = new Logradouro(logradouroActionForm.getNome(),
				logradouroActionForm.getNomePopular(), indicadorDeUso,
				new Date(), municipio, logradouroTitulo, logradouroTipo);

		/*
		 * [UC0107] Registrar Transa��o
		 * logradouro.setOperacaoEfetuada(operacaoEfetuada);
		 * logradouro.adicionarUsuario(Usuario.USUARIO_TESTE,
		 * UsuarioAcao.USUARIO_ACAO_TESTE);
		 */

		registradorOperacao.registrarOperacao(logradouro);

		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

		filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.ID_LOGRADOUROTIPO, logradouro
						.getLogradouroTipo().getId()));

		filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.ID_MUNICIPIO, logradouro.getMunicipio()
						.getId()));

		filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.NOME, logradouro.getNome()));

		if (logradouro.getLogradouroTitulo() == null
				|| logradouro.getLogradouroTitulo().equals("")) {
			filtroLogradouro.adicionarParametro(new ParametroNulo(
					FiltroLogradouro.ID_LOGRADOUROTITULO));
		} else {
			filtroLogradouro.adicionarParametro(new ParametroSimples(
					FiltroLogradouro.ID_LOGRADOUROTITULO, logradouro
							.getLogradouroTitulo().getId()));
		}

		Collection logradouros = fachada.pesquisar(filtroLogradouro,
				Logradouro.class.getName());

		if (logradouros != null && !logradouros.isEmpty()) {
			if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {

				httpServletRequest.setAttribute("caminhoActionConclusao",
						"/gsan/inserirLogradouroAction.do");
				// Monta a p�gina de confirma��o para perguntar se o usu�rio
				// quer inserir
				// o logradouro cadastrado com este Tipo, T�tulo e Nome para
				// este Munic�pio
				return montarPaginaConfirmacao(
						"atencao.logradouro_ja_existente.confirmacao",
						httpServletRequest, actionMapping);
			}
		}

		Integer codigoLogradouro = fachada.inserirLogradouro(logradouro,
				colecaoBairros, colecaoCeps);

		montarPaginaSucesso(httpServletRequest, "Logradouro de c�digo "
				+ codigoLogradouro + " inserido com sucesso.",
				"Inserir outro Logradouro",
				"exibirInserirLogradouroAction.do?menu=sim",
				"exibirAtualizarLogradouroAction.do?idRegistroAtualizacao="
						+ codigoLogradouro, "Atualizar Logradouro Inserido");

		return retorno;
	}
}
