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
package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.GrupoFuncionalidadeOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action respons�vel por exibir a p�gina de definir o acesso inicial que o
 * grupo vai possuir
 * 
 * @author Pedro Alexandre
 * @date 28/06/2006
 */
public class ExibirInserirGrupoAcessosGrupoAction extends GcomAction {

	/**
	 * [UC0278] - Inserir Grupo
	 * 
	 * @author Pedro Alexandre
	 * @date 29/06/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm, 
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {

		//Recupera o from de inserir grupo
		DynaValidatorForm inserirGrupoActionForm = (DynaValidatorForm) actionForm;

		//Seta o mapeamento de retorno para a tela de inserir grupo acessos do grupo
		ActionForward retorno = actionMapping.findForward("inserirGrupoAcessosGrupo");

		//Recupera o usu�rio que est� logado no sistema
		Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);

		//Cria o filtro para pesquisar todas as funcionalidades cadastradas no sistema
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(
			new ParametroSimples(FiltroFuncionalidade.INDICADOR_PONTO_ENTRADA, new Integer(1)));
		
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.OPERACOES);
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.FUNCIONALIDADE_DEPENDENCIAS_BY_ID_INDEPENDENCIA);
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.FUNCIONALIDADE_DEPENDENCIAS);
		filtroFuncionalidade.setConsultaSemLimites(true);
		
		//Cria o filtro para a pesquisa de opera��es
		FiltroOperacao filtroOperacao = new FiltroOperacao();

		//Cria as vari�veis para armazenar as cole��es de funcionalidade e opera��es 
		Collection funcionalidades = null;
		Collection operacoes = null;

		//Cria a vari�vel que vai armazenar as funcionalidades armazenadas para o usu�rio
		List funcionalidadesCadastradas = new ArrayList();

		//Recupera os acessos do usu�rio na sess�o
		Collection grupoFuncionalidades = 
			(Collection) this.getSessao(httpServletRequest).getAttribute("grupoFuncionalidades");
		/*
		 * Caso os acessos do usu�rio n�o esteja na sess�o
		 * inst�ncia a cole��o e coloca na sess�o a cole��o vazia
		 */  
		if (grupoFuncionalidades == null) {
			grupoFuncionalidades = new ArrayList();
			
			this.getSessao(httpServletRequest).setAttribute("grupoFuncionalidades", grupoFuncionalidades);
		}

		//Cria a vari�vel que vai armazenar as opera��es cadastradas para o usu�rio 
		Collection operacoesCadastradas = new ArrayList();

		//Recupera o c�digo da funcionalidade do request
		String codigoFuncionalidade = httpServletRequest.getParameter("codigoFuncionalidade");

		/*
		 * Caso o c�digo da funcionalidade tenha sido informado
		 * pesquisa os acessos ao que o usu�rio queest� logado tem 
		 * de acordo com os grupos a que ele pertence
		 */ 
		if (codigoFuncionalidade != null && 
			!"".equals(codigoFuncionalidade) && 
			"true".equalsIgnoreCase(httpServletRequest.getParameter("cadastrarOperacao"))) {
			
			//Cria o objeto funcionalidade e seta o id no objeto 
			Funcionalidade funcionalidade = new Funcionalidade();
			funcionalidade.setId(new Integer(codigoFuncionalidade));

			//Cria o iterator das permiss�es do usu�rio
			Iterator iteratorGrupoFuncionalidades = grupoFuncionalidades.iterator();
			
			//La�o para remover todas as permiss�es
			while (iteratorGrupoFuncionalidades.hasNext()) {
				GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = 
					(GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidades.next();
				
				if (grupoFuncionalidadeOperacao.getFuncionalidade().getId().equals(funcionalidade.getId())) {
					iteratorGrupoFuncionalidades.remove();
				}
			}

			//Recupera os ids das opera��es que foram marcadas pelo usu�rio 
			String[] operacoesAInserir = (String[]) inserirGrupoActionForm.get("operacoes");
			
			/*
			 * Caso esxista opera��es marcadas para ser adicionadas as permiss�es
			 * cria o relacionamento entre a funcionalidade principal e as opera��es selecionadas
			 */
			if (operacoesAInserir != null) {

				//La�o para gerar as permiss�es selecionadas 
				for (int i = 0; i < operacoesAInserir.length; i++) {
					
					//Recupera o id da opera��o
					String id = operacoesAInserir[i];
					
					//Flag para indicar que j� existe a permiss�o na cole��o da sess�o
					boolean jaTem = false;
					
					//Cria o iterator das permiss�es
					iteratorGrupoFuncionalidades = grupoFuncionalidades.iterator();
					
					//La�o para verificar se j� tem a permiss�o
					while (iteratorGrupoFuncionalidades.hasNext()) {
						GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = 
							(GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidades.next();
						if (grupoFuncionalidadeOperacao.getOperacao().getId().toString().equalsIgnoreCase(id)) {
							jaTem = true;
						}
					}

					/*
					 * Caso n�o tenha a permiss�o 
					 * cria o relacionamento entre a opera��o e a funcionalidade e seta 
					 * nas permiss�es do usu�rio
					 */
					if (!jaTem) {
						//Pesquisa as opera��es da funcionalidade
						filtroOperacao = new FiltroOperacao();
						filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.FUNCIONALIDADE);
						filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, new Integer(id)));
						Collection colecaoOperacoes = Fachada.getInstancia().pesquisar(filtroOperacao,Operacao.class.getSimpleName());
						
						/*
						 * Caso a funcionalidade tenha opera��es cadastradas
						 * cria a permiss�o para a opera��o e seta na cole��o
						 */
						if (colecaoOperacoes != null && !colecaoOperacoes.isEmpty()) {
							Operacao operacao = (Operacao) colecaoOperacoes.iterator().next();
							GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = new GrupoFuncionalidadeOperacao();
							grupoFuncionalidadeOperacao.setOperacao(operacao);
							grupoFuncionalidadeOperacao.setFuncionalidade(funcionalidade);
							grupoFuncionalidades.add(grupoFuncionalidadeOperacao);
						}
					}
				}
			}
		}else if(codigoFuncionalidade != null && !codigoFuncionalidade.equals("") ){
			filtroFuncionalidade.adicionarParametro(
				new ParametroSimples(FiltroFuncionalidade.ID, codigoFuncionalidade));
		}

		//Pesquisa as funcionalidades cadastradas no sistema
		funcionalidades = 
			this.getFachada().pesquisar(filtroFuncionalidade,Funcionalidade.class.getName());

		//Caso n�o tenha nenhuma funcionalidade cadastrada no sistema levanta uma exce��o para o usu�rio
		if (funcionalidades == null || funcionalidades.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado.funcionalidade");
		}

		//Cria o iterator das permiss�es do usu�rio 
		Iterator iteratorGrupoFuncionalidades = grupoFuncionalidades.iterator();

		//La�o para adicionar a permiss�o a cole��o caso ainda n�o tenha 
		//a permiss�o para a funcionalidade
		while (iteratorGrupoFuncionalidades.hasNext()) {
			GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidades.next();
			Funcionalidade funcionalidadeCadastrada = grupoFuncionalidadeOperacao.getFuncionalidade();
			
			//Se ainda n�o tenha a permiss�o cadastrada adiciona ela a cole��o
			if (!funcionalidadesCadastradas.contains(funcionalidadeCadastrada)) {
				funcionalidadesCadastradas.add(funcionalidadeCadastrada);
			}
		}

		//Cria a string que vai conter o link de retorno na constru��o do menu
		String linkRetorno = "inserirGrupoWizardAction.do?action=exibirInserirGrupoAcessosGrupoAction";
		
		//Cria a arvore contendo as funcionalidades a que o usu�rio tenha acesso
		//e seta a arvore no request
		String arvoreFuncionalidades = 
			this.getFachada().construirMenuAcesso(usuarioLogado, linkRetorno,null);
		
		httpServletRequest.setAttribute("arvoreFuncionalidades",arvoreFuncionalidades);

		/*
		 * Recupera a descri�a� da funcionalidade e seta a descri��o no request
		 */
		if (codigoFuncionalidade != null && !codigoFuncionalidade.equalsIgnoreCase("")) {
			String descricaoFuncionalidade = null;
			Iterator iteratorFuncionalidades = funcionalidades.iterator();
			while (iteratorFuncionalidades.hasNext()) {
				Funcionalidade funcionalidade = (Funcionalidade) iteratorFuncionalidades.next();
				if (codigoFuncionalidade.equalsIgnoreCase(funcionalidade.getId().toString())) {
					descricaoFuncionalidade = funcionalidade.getDescricao();
					break;
				}
			}

			//Pesquisa as opera��es registradas para a funcionalidade
			operacoes = 
				this.getFachada().recuperarOperacoesFuncionalidadesEDependentes(new Integer(codigoFuncionalidade));
			
			/*
			 * Pegando todas as FuncionalidadeOperacao e vendo as operacoes que
			 * o usuario tem acesso da funcionalidade escolhida
			 */
			if (grupoFuncionalidades != null && !grupoFuncionalidades.isEmpty()) {
				Iterator iteratorGrupoFuncionalidadeOperacao = grupoFuncionalidades.iterator();
				while (iteratorGrupoFuncionalidadeOperacao.hasNext()) {
					GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidadeOperacao.next();
					if (grupoFuncionalidadeOperacao.getFuncionalidade().getId().toString().equalsIgnoreCase(codigoFuncionalidade)) {
						operacoesCadastradas.add(grupoFuncionalidadeOperacao.getOperacao());
					}
				}
			}

			//Remove as opera��es j� cadastradas para a funcionalidade para 
			//recuperar as opera��es que ainda n�o foram cadastradas   
			operacoes.removeAll(operacoesCadastradas);
			Collection operacoesNaoCadastradas = new ArrayList(operacoes);

			/*
			 * Seta descri��o da funcionalidade selecionada no request
			 * assim como o c�digo da fucnionalidade e as cole��es de opera��es
			 * cadastradas e n�o cadastradas.   
			 */
			httpServletRequest.setAttribute("descricaoFuncionalidade",descricaoFuncionalidade);
			httpServletRequest.setAttribute("idFuncionalidade",codigoFuncionalidade);
			httpServletRequest.setAttribute("operacoesCadastradas",operacoesCadastradas);
			httpServletRequest.setAttribute("operacoesNaoCadastradas",operacoesNaoCadastradas);
		}
		//Retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}
}