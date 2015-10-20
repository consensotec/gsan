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
package gcom.gui.relatorio.transacao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.transacao.RelatorioConsultarOperacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAcao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o do relat�rio de opera��o consultar
 * 
 * @author Rafael Corr�a
 * @created 06/04/2006
 */
public class GerarRelatorioConsultarOperacaoAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// cria a vari�vel de retorno
		ActionForward retorno = null;
		
		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		//ConsultarOperacaoEfetuadaActionForm form = (ConsultarOperacaoEfetuadaActionForm) actionForm; 

//		Collection colecaoOperacoesEfetuadas = (Collection) sessao
//				.getAttribute("operacaoEfetuada");

		// Inicio da parte que vai mandar os parametros para o relat�rio
 		String[] idOperacoes          = null;
		String unidadeNegocio         = "";
		String idUsuario              = "";
		Usuario usuario               = null;
		String idUsuarioAcao          = "";
		UsuarioAcao usuarioAcao       = null;
		Date periodoRealizacaoInicial = null;
		Date periodoRealizacaoFinal   = null;
		Date horarioRealizacaoInicial = null;
		Date horarioRealizacaoFinal   = null;
		Integer idTabela              = null;
		Integer[] idTabelaColuna      = null;
		Integer id1                   = null;
		Integer numeroPaginasPesquisa = null;
		Integer idFuncionalidade      = null;
		Hashtable<String,String> argumentos = new Hashtable<String, String>();
		

		if (sessao.getAttribute("idOperacoes") != null) {
			idOperacoes = (String[]) sessao.getAttribute("idOperacoes");
		}

//		Operacao operacao = null;
//
//		if (idOperacao != null && !idOperacao.equals("")) {
//			FiltroOperacao filtroOperacao = new FiltroOperacao();
//
//			filtroOperacao.adicionarParametro(new ParametroSimples(
//					FiltroOperacao.ID, idOperacao));
//
//			Collection colecaoOperacoes = fachada.pesquisar(filtroOperacao,
//					Operacao.class.getName());
//
//			if (colecaoOperacoes != null && !colecaoOperacoes.isEmpty()) {
//				// A opera��o foi encontrada
//
//				operacao = (Operacao) colecaoOperacoes.iterator().next();
//
//			} else {
//				throw new ActionServletException(
//						"atencao.pesquisa_inexistente", null, "Opera��o");
//			}
//
//		} else {
//			operacao = new Operacao();
//		}

		

		if (sessao.getAttribute("idUsuario") != null) {
			idUsuario = (String) sessao.getAttribute("idUsuario");
		}


		if (idUsuario != null && !idUsuario.equals("")) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();

  filtroUsuario.adicionarParametro( new ParametroSimples( FiltroUsuario.LOGIN, idUsuario ) );

  Collection colecaoUsuarios = fachada.pesquisar( filtroUsuario,Usuario.class.getName() );

			if (colecaoUsuarios != null && !colecaoUsuarios.isEmpty()) {
				// O usu�rio foi encontrado
				usuario = (Usuario) colecaoUsuarios.iterator().next();
			} 
			else {
	throw new ActionServletException("atencao.pesquisa_inexistente", null, "Usu�rio");
			}

		}
		else {
			usuario = new Usuario();
		}


		if (sessao.getAttribute("idUsuarioAcao") != null) {
	idUsuarioAcao = ((Integer) sessao.getAttribute("idUsuarioAcao")).toString();
		}
		if( sessao.getAttribute("numeroPaginasPesquisa") != null ){
	numeroPaginasPesquisa = (Integer) sessao.getAttribute("numeroPaginasPesquisa");
		}

		

		if ( idUsuarioAcao != null && !idUsuarioAcao.equals("") ) {
			
			FiltroUsuarioAcao filtroUsuarioAcao = new FiltroUsuarioAcao();

	filtroUsuarioAcao.adicionarParametro( new ParametroSimples( FiltroUsuarioAcao.ID, idUsuario) );

	Collection colecaoUsuariosAcoes = fachada.pesquisar( filtroUsuarioAcao, UsuarioAcao.class.getName() );

			if (colecaoUsuariosAcoes != null && !colecaoUsuariosAcoes.isEmpty()) {
				// A a��o do usu�rio foi encontrado
	usuarioAcao = (UsuarioAcao) colecaoUsuariosAcoes.iterator().next();
			}
			else {
	throw new ActionServletException( "atencao.pesquisa_inexistente", null, "A��o do Usu�rio" );
			}

		}
		else {
			usuarioAcao = new UsuarioAcao();
		}



		if (sessao.getAttribute("dataInicial") != null) {
	periodoRealizacaoInicial =(Date)  sessao.getAttribute("dataInicial");
		}
		if( sessao.getAttribute("unidadeNegocio") != null ){
			unidadeNegocio = (String) sessao.getAttribute("unidadeNegocio");
		}

		if (sessao.getAttribute("dataFinal") != null) {
	periodoRealizacaoFinal = (Date) sessao.getAttribute("dataFinal");
		}



		if (sessao.getAttribute("horaInicial") != null) {
	horarioRealizacaoInicial = (Date) sessao.getAttribute("horaInicial");
		}

		if (sessao.getAttribute("horaFinal") != null) {
	horarioRealizacaoFinal =(Date) sessao.getAttribute("horaFinal");
		}
		
		
		
		if(sessao.getAttribute("idTabela") != null){
	idTabela = (Integer) sessao.getAttribute("idTabela");
		}
		
		
		
		if(sessao.getAttribute("idTabelaColuna") != null){
			idTabelaColuna = (Integer[]) sessao.getAttribute("idTabelaColuna");
		}
		
		if(sessao.getAttribute("id1") != null){
			id1 = (Integer) sessao.getAttribute("id1");
		}
		if(sessao.getAttribute("idFuncionalidade") != null){
			idFuncionalidade = (Integer) sessao.getAttribute("idFuncionalidade");
		}
		
		if (sessao.getAttribute("argumentos") != null) {
			argumentos = (Hashtable<String, String>) sessao.getAttribute("argumentos");
		}

		// cria uma inst�ncia da classe do relat�rio
		RelatorioConsultarOperacao relatorioConsultarOperacao = new RelatorioConsultarOperacao(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

//		relatorioConsultarOperacao.addParametro("colecaoOperacoesEfetuadas",
//				colecaoOperacoesEfetuadas);
relatorioConsultarOperacao.addParametro("idOperacoes"              , idOperacoes              );
relatorioConsultarOperacao.addParametro("usuario"                  , usuario                  );
relatorioConsultarOperacao.addParametro("usuarioAcao"              , usuarioAcao              );
relatorioConsultarOperacao.addParametro("periodoRealizacaoInicial" , periodoRealizacaoInicial );
relatorioConsultarOperacao.addParametro("periodoRealizacaoFinal"   , periodoRealizacaoFinal   );
relatorioConsultarOperacao.addParametro("horarioRealizacaoInicial" , horarioRealizacaoInicial );
relatorioConsultarOperacao.addParametro("horarioRealizacaoFinal"   , horarioRealizacaoFinal   );
relatorioConsultarOperacao.addParametro("idTabela"                 , idTabela                 );
relatorioConsultarOperacao.addParametro("idTabelaColuna"           , idTabelaColuna           );
relatorioConsultarOperacao.addParametro("id1"                      , id1                      );
relatorioConsultarOperacao.addParametro("argumentos"               , argumentos               );
relatorioConsultarOperacao.addParametro("unidadeNegocio"           , unidadeNegocio           );
relatorioConsultarOperacao.addParametro("numeroPaginasPesquisa"    , numeroPaginasPesquisa    );
relatorioConsultarOperacao.addParametro("idFuncionalidade"         , idFuncionalidade         );
		
		
		
		String tipo = httpServletRequest.getParameter("tipo");
		
		relatorioConsultarOperacao.addParametro("tipoRelatorio", tipo);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioConsultarOperacao.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioConsultarOperacao,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}

		return retorno;
	}

}
