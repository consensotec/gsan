/**
 * 
 */
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
package gcom.gui.cadastro.funcionario;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.FiltroFuncionarioCargo;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.funcionario.FuncionarioCargo;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author R�mulo Aur�lio
 *
 */
public class ExibirAtualizarFuncionarioAction extends GcomAction{
	/**
	 * 
	 * [UC0844] Manter Funcion�rio
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 16/04/2007
	 * 
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
		ActionForward retorno = actionMapping
				.findForward("atualizarFuncionario");

		AtualizarFuncionarioActionForm form = (AtualizarFuncionarioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		// Verifica se veio do filtrar ou do manter
		
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}


		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
				Empresa.class.getName());

		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Empresa");
		}

		httpServletRequest.setAttribute("colecaoEmpresa", colecaoEmpresa);
		
		FiltroFuncionarioCargo filtroFuncionarioCargo = new FiltroFuncionarioCargo();
		filtroFuncionarioCargo.setCampoOrderBy(FiltroFuncionarioCargo.DESCRICAO);
		filtroFuncionarioCargo.adicionarParametro(new ParametroSimples(FiltroFuncionarioCargo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<FuncionarioCargo> colecaoFuncionarioCargo = fachada.pesquisar(filtroFuncionarioCargo, FuncionarioCargo.class.getName());
		httpServletRequest.setAttribute("colecaoFuncionarioCargo", colecaoFuncionarioCargo);

		//-------Parte que trata do c�digo quando o usu�rio tecla enter        

        String idDigitadoEnterUnidadeEmpresa = form.getIdUnidade();
       
        //Verifica se o c�digo da Unidade Empresa foi digitado
        if (idDigitadoEnterUnidadeEmpresa != null
				&& !idDigitadoEnterUnidadeEmpresa.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterUnidadeEmpresa) > 0) {
        	FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();

        	filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
        			FiltroUnidadeOrganizacional.ID, idDigitadoEnterUnidadeEmpresa));
			
			Collection<UnidadeOrganizacional> unidadeEmpresaEncontrada = fachada.pesquisar(filtroUnidadeEmpresa,
					UnidadeOrganizacional.class.getName());

			if (unidadeEmpresaEncontrada != null && !unidadeEmpresaEncontrada.isEmpty()) {
				//a unidade de Unidade Empresa foi encontrado
				form.setIdUnidade(""
						+ ((UnidadeOrganizacional) ((List) unidadeEmpresaEncontrada).get(0))
								.getId());
				form
						.setNomeUnidade(((UnidadeOrganizacional) ((List) unidadeEmpresaEncontrada)
								.get(0)).getDescricao());
				httpServletRequest.setAttribute("idUnidadeEmpresaNaoEncontrada",
						"true");
				httpServletRequest.setAttribute("nomeCampo", "descricaoUnidadeEmpresa");
				

			} else {

				form.setIdUnidade("");
				httpServletRequest.setAttribute("idUnidadeEmpresaNaoEncontrada",
						"exception");
				form
						.setNomeUnidade("UNIDADE EMPRESA INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeEmpresa");

			}

        }
        //-------Fim de parte que trata do c�digo quando o usu�rio tecla enter
		
        //Verifica se o usuario informou a unidade organizacional e teclou enter
        if(httpServletRequest.getParameter("enter") == null){
        	
		// Verifica se o funcionario j� est� na sess�o, em caso afirmativo
		// significa que o usu�rio j� entrou na tela e apenas selecionou algum
		// item que deu um reload na tela e em caso negativo significa que ele
		// est� entrando pela primeira vez

			if (sessao.getAttribute("objetoFuncionario") != null) {

				Funcionario funcionario = (Funcionario) sessao
						.getAttribute("objetoFuncionario");
				sessao.setAttribute("idFuncionario", funcionario.getId()
						.toString());

				form.setMatricula(funcionario.getId().toString());
				form.setIdFuncionario(funcionario.getId().toString());
				
				form.setFuncionarioCargo(funcionario.getFuncionarioCargo().getId().toString());
				
				form.setNome(funcionario.getNome());
				
				form.setEmpresa(funcionario.getEmpresa().getId().toString());
				
				form.setIdUnidade(funcionario.getUnidadeOrganizacional().getId().toString());
				
				form.setNomeUnidade(funcionario.getUnidadeOrganizacional().getDescricao());
				
				form.setNumeroCpf(funcionario.getNumeroCpf());
				
				form.setDataNascimento(Util.formatarData(funcionario.getDataNascimento()));
                
				sessao.setAttribute("funcionarioAtualizar", funcionario);
				
				sessao.removeAttribute("objetoFuncionario");

			} else {

				String idFuncionario = null;

				if (httpServletRequest.getParameter("idFuncionario") == null
						|| httpServletRequest.getParameter("idFuncionario")
								.equals("")) {
					idFuncionario = (String) sessao
							.getAttribute("idFuncionario");
				} else {
					idFuncionario = (String) httpServletRequest
							.getParameter("idFuncionario");
					sessao.setAttribute("idFuncionario", idFuncionario);
				}

				httpServletRequest.setAttribute("idFuncionario",
						idFuncionario);
				sessao.setAttribute("idFuncionario",idFuncionario);
				
				FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
				filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("empresa");
				filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
				
				filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, idFuncionario));

				Collection<Funcionario> colecaoFuncionario = fachada
						.pesquisar(filtroFuncionario, Funcionario.class
								.getName());

				if (colecaoFuncionario == null
						|| colecaoFuncionario.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoFuncionario",
						colecaoFuncionario);

				Funcionario funcionario = (Funcionario) colecaoFuncionario
						.iterator().next();

				form.setMatricula(funcionario.getId().toString());
				
				form.setIdFuncionario(funcionario.getId().toString());
				
				form.setNome(funcionario.getNome());
				
				form.setFuncionarioCargo(funcionario.getFuncionarioCargo().getId().toString());
				
				form.setEmpresa(funcionario.getEmpresa().getId().toString());
				
				form.setIdUnidade(funcionario.getUnidadeOrganizacional().getId().toString());
				
				form.setNomeUnidade(funcionario.getUnidadeOrganizacional().getDescricao());
				
				form.setNumeroCpf(funcionario.getNumeroCpf());
				
				form.setDataNascimento(Util.formatarData(funcionario.getDataNascimento()));
				
				sessao.setAttribute("funcionarioAtualizar", funcionario);
							
			}

        }
		// -------------- bt DESFAZER ---------------

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			String idFuncionario = null;

			if (httpServletRequest.getParameter("idFuncionario") == null
					|| httpServletRequest.getParameter("idFuncionario")
							.equals("")) {
				idFuncionario = (String) sessao
						.getAttribute("idFuncionario");
			} else {
				idFuncionario = (String) httpServletRequest
						.getParameter("idFuncionario");
				sessao.setAttribute("idFuncionario", idFuncionario);
			}

			if (idFuncionario.equalsIgnoreCase("")) {
				idFuncionario = null;
			}

			if (idFuncionario == null) {

				Funcionario funcionario = (Funcionario) sessao
						.getAttribute("objetoFuncionario");

				form.setMatricula(funcionario.getId().toString());

				form.setFuncionarioCargo(funcionario.getFuncionarioCargo().getId().toString());
				
				form.setNome(funcionario.getNome());
				
				form.setEmpresa(funcionario.getEmpresa().getId().toString());
				
				form.setIdUnidade(funcionario.getUnidadeOrganizacional().getId().toString());
				
				form.setNomeUnidade(funcionario.getUnidadeOrganizacional().getDescricao());
				
				form.setNumeroCpf(funcionario.getNumeroCpf());
				
				form.setDataNascimento(Util.formatarData(funcionario.getDataNascimento()));
				
				sessao.setAttribute("funcionarioAtualizar", funcionario);
				sessao.removeAttribute("funcionario");
			}



			if (idFuncionario != null) {

				FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
				
				filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("empresa");
				
				filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
				
				
				filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, idFuncionario));

				Collection<Funcionario> colecaoFuncionario = fachada.pesquisar(filtroFuncionario, Funcionario.class
								.getName());

				if (colecaoFuncionario == null
						|| colecaoFuncionario.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoFuncionario",
						colecaoFuncionario);

				Funcionario funcionario = (Funcionario) colecaoFuncionario
						.iterator().next();

				form.setMatricula(funcionario.getId().toString());

				form.setFuncionarioCargo(funcionario.getFuncionarioCargo().getId().toString());
		
				form.setNome(funcionario.getNome());
		
				form.setEmpresa(funcionario.getEmpresa().getId().toString());
		
				form.setIdUnidade(funcionario.getUnidadeOrganizacional().getId().toString());
		
				form.setNomeUnidade(funcionario.getUnidadeOrganizacional().getDescricao());
				
				form.setNumeroCpf(funcionario.getNumeroCpf());
				
				form.setDataNascimento(Util.formatarData(funcionario.getDataNascimento()));
			}
		}
		// -------------- bt DESFAZER ---------------
			
		//DATA CORRENTE
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        httpServletRequest.setAttribute("dataAtual", formatoData
        .format(dataCorrente.getTime()));
        
        //IDADE M�NIMA
        httpServletRequest.setAttribute("idadeMinimaFuncionario", ConstantesSistema.IDADE_MINIMA_FUNCIONARIO);
        
		return retorno;

	}

	
}
