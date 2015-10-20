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

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroGrupoFuncionalidadeOperacao;
import gcom.seguranca.acesso.FiltroModulo;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.GrupoFuncionalidadeOperacao;
import gcom.seguranca.acesso.GrupoFuncionalidadeOperacaoPK;
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
 * Action respons�vel pelo pr�-processamento da p�gina de definir os acessos do grupo
 *
 * @author Pedro Alexandre
 * @date 03/07/2006
 */
public class ExibirAtualizarGrupoAcessosGrupoAction extends GcomAction {

	
	/**
	 * <Breve descri��o sobre o caso de uso>
	 *
	 * [UC0279] - Manter Grupo
	 *
	 * @author Pedro Alexandre
	 * @date 03/07/2006
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

		//Recupera o form de atualizar grupo
		DynaValidatorForm atualizarGrupoActionForm = (DynaValidatorForm) actionForm;

		//Seta o mapeamento para a tela de definir acessos do grupo
		ActionForward retorno = actionMapping.findForward("atualizarGrupoAcessosGrupo");

		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
		
		//Recupera o grupo da sess�o 
		Grupo grupo = (Grupo) this.getSessao(httpServletRequest).getAttribute("grupo");

		// Cria o filtro de modulo
		FiltroModulo filtroModulo = new FiltroModulo();

		// Seta a ordena��o da pesquisa
		filtroModulo.setCampoOrderBy(FiltroModulo.DESCRICAO_MODULO);

		// Cria o filtro de funcionalidade
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();

		// Seta o filtro para retornar todas as funcionalidades que s�o ponto de entrada
		filtroFuncionalidade.adicionarParametro(new ParametroSimples(FiltroFuncionalidade.INDICADOR_PONTO_ENTRADA, new Integer(1)));
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.OPERACOES);
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.FUNCIONALIDADE_DEPENDENCIAS_BY_ID_INDEPENDENCIA);
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.FUNCIONALIDADE_DEPENDENCIAS);
		filtroFuncionalidade.setConsultaSemLimites(true);

		// Cria o filtro de opera��o
		FiltroOperacao filtroOperacao = new FiltroOperacao();

		// Cria a cole��o de funcionalidades
		Collection funcionalidades = null;

		// Cria a cole��o de opera��es
		Collection operacoes = null;

		// Cria a cole��o de funcionalidades cadastradas
		List funcionalidadesCadastradas = new ArrayList();

		// Cria a cole��o de grupo e funcionalidades
		Collection grupoFuncionalidades = (Collection) this.getSessao(httpServletRequest).getAttribute("grupoFuncionalidades");
		if (grupoFuncionalidades == null) {
			FiltroGrupoFuncionalidadeOperacao filtroGrupoFuncionalidadeOperacao = new FiltroGrupoFuncionalidadeOperacao();
			filtroGrupoFuncionalidadeOperacao.adicionarParametro(new ParametroSimples(FiltroGrupoFuncionalidadeOperacao.GRUPO_ID, grupo.getId()));
			filtroGrupoFuncionalidadeOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroGrupoFuncionalidadeOperacao.FUNCIONALIDADE);
			filtroGrupoFuncionalidadeOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroGrupoFuncionalidadeOperacao.GRUPO);
			filtroGrupoFuncionalidadeOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroGrupoFuncionalidadeOperacao.OPERACAO);
			filtroGrupoFuncionalidadeOperacao.setConsultaSemLimites(true);
			
			grupoFuncionalidades = this.getFachada().pesquisar(filtroGrupoFuncionalidadeOperacao,GrupoFuncionalidadeOperacao.class.getSimpleName());
			
			if (grupoFuncionalidades == null) {
				grupoFuncionalidades = new ArrayList();
			}
			this.getSessao(httpServletRequest).setAttribute("grupoFuncionalidades", grupoFuncionalidades);
		}

		// Cria a cole��o de opera��es cadastradas
		Collection operacoesCadastradas = new ArrayList();

		// Pega o c�digo da funcionalidade selecionada na p�gina
		String codigoFuncionalidade = httpServletRequest.getParameter("codigoFuncionalidade");

		// Cadastrando as operacoes selecionadas
		if (codigoFuncionalidade != null && !"".equals(codigoFuncionalidade) && "true".equalsIgnoreCase(httpServletRequest.getParameter("cadastrarOperacao"))) {

			Funcionalidade funcionalidade = new Funcionalidade();
			funcionalidade.setId(new Integer(codigoFuncionalidade));

			grupoFuncionalidades = (Collection) this.getSessao(httpServletRequest).getAttribute("grupoFuncionalidades");
			if (grupoFuncionalidades == null) {
				grupoFuncionalidades = new ArrayList();
				this.getSessao(httpServletRequest).setAttribute("grupoFuncionalidades",	grupoFuncionalidades);
			}

			// Apaga todas as operacoes da funcionalidade
			// para ser carregadas um a um novamente
			// isso eh no caso de excluir alguma funcionalidade
			Iterator iteratorGrupoFuncionalidades = grupoFuncionalidades.iterator();
			while (iteratorGrupoFuncionalidades.hasNext()) {
				GrupoFuncionalidadeOperacao gfo = (GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidades.next();
				if (gfo.getFuncionalidade().getId().equals(funcionalidade.getId())) {
					iteratorGrupoFuncionalidades.remove();
				}
			}

			// Pegando as operacoes escolhidas
			String[] operacoesAInserir = (String[]) atualizarGrupoActionForm.get("operacoes");
			if (operacoesAInserir != null) {
				for (int i = 0; i < operacoesAInserir.length; i++) {
					// Para todas as operacoes escolhidas
					String id = operacoesAInserir[i];
					boolean jaTem = false;
					// Verifica se ja tem foi selecionado
					iteratorGrupoFuncionalidades = grupoFuncionalidades.iterator();
					while (iteratorGrupoFuncionalidades.hasNext()) {
						// Verifica se eh a mesma funcionalidade e a mesma
						// operacao para ser excluida
						GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidades.next();
						if (grupoFuncionalidadeOperacao.getFuncionalidade().getId().equals(funcionalidade.getId()) && grupoFuncionalidadeOperacao.getOperacao().getId().toString().equalsIgnoreCase(id)) {
							jaTem = true;
						}
					}

					if (!jaTem) {
						// Se nao tem cria o filtro para adicionar a operacao
						filtroOperacao = new FiltroOperacao();
						filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.FUNCIONALIDADE);
						filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, new Integer(id)));
						filtroOperacao.setConsultaSemLimites(true);
						
						Collection colecaoOperacao = this.getFachada().pesquisar(filtroOperacao, Operacao.class.getSimpleName());
						if (colecaoOperacao != null && !colecaoOperacao.isEmpty()) {
							Operacao operacao = (Operacao) colecaoOperacao.iterator().next();
							GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = new GrupoFuncionalidadeOperacao();
							GrupoFuncionalidadeOperacaoPK grupoFuncionalidadeOperacaoPK = new GrupoFuncionalidadeOperacaoPK();
							
							grupoFuncionalidadeOperacaoPK.setFuncionalidadeId(funcionalidade.getId());
							grupoFuncionalidadeOperacaoPK.setOperacaoId(operacao.getId());
							grupoFuncionalidadeOperacaoPK.setGrupoId(grupo.getId());
							grupoFuncionalidadeOperacao.setOperacao(operacao);
							grupoFuncionalidadeOperacao.setFuncionalidade(funcionalidade);
							grupoFuncionalidadeOperacao.setGrupo(grupo);
							grupoFuncionalidadeOperacao.setComp_id(grupoFuncionalidadeOperacaoPK);
							grupoFuncionalidades.add(grupoFuncionalidadeOperacao);
						}
					}
				}
			}
		}

		// Pesquisa a cole��o de funcionalidades cadastradas no sistema
		funcionalidades = this.getFachada().pesquisarTabelaAuxiliar(filtroFuncionalidade,Funcionalidade.class.getName());

		// Se nenhuma funcionalidade cadastrada no sistema
		if (funcionalidades == null || funcionalidades.isEmpty()) {
			// Manda o erro no request atual
			throw new ActionServletException("atencao.naocadastrado.funcionalidade");
		}

		// Coloca a cole��o de grupo e funcionalidades no iterator
		Iterator iteratorGrupoFuncionalidades = grupoFuncionalidades.iterator();

		// La�o para adicionar as funcionalidades do grupo na cole��o
		while (iteratorGrupoFuncionalidades.hasNext()) {
			// Pega o objeto GrupoFuncionalidadeOperacao da cole��o
			GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidades.next();

			// Pega o objeto funcionalidade
			Funcionalidade funcionalidadeCadastrada = grupoFuncionalidadeOperacao.getFuncionalidade();

			// Se n�o existe o objeto na cole��o
			if (!funcionalidadesCadastradas.contains(funcionalidadeCadastrada)) {
				// Se a funcionalidade for ponto de entrada
				// adiciona o objeto funcionalidade na cole��o
				funcionalidadesCadastradas.add(funcionalidadeCadastrada);
			}
		}

		// Chama o met�do para criar a �rvore das funcionalidades
		String arvoreFuncionalidades = 
			this.getFachada().construirMenuAcesso(
					usuarioLogado,
					"atualizarGrupoWizardAction.do?action=exibirAtualizarGrupoAcessosGrupoAction",
					grupo.getId());

		// Manda o javascript da �rvore para a p�gina no request
		httpServletRequest.setAttribute("arvoreFuncionalidades", arvoreFuncionalidades);

		/**
		 * Com a funcionalidade passada pesquisa todas as funcionalidade Ocultas
		 * com as funcionalidade Ocultas retira as operacoes
		 */

		// Se for informado o c�digo da funcionalidade no request
		if (codigoFuncionalidade != null && !codigoFuncionalidade.equalsIgnoreCase("")) {
			// Cria a vari�vel que vai conter a descri��o da funcionalidade
			String descricaoFuncionalidade = null;

			// Coloca a cole��o de funcionalidades no iterator
			Iterator iteratorFuncionalidades = funcionalidades.iterator();

			// Cria a cole��o de funcionalidades ocultas(dependentes)
			//Collection funcionalidadesOcultas = new ArrayList();

			// La�o para encontrar a funcionalidade informada
			while (iteratorFuncionalidades.hasNext()) {
				// Recupera a funcionalidade do iterator
				Funcionalidade funcionalidade = (Funcionalidade) iteratorFuncionalidades.next();

				// Se o c�digo da funcionalidade for igual ao c�digo da
				// funcionalidade informada
				if (codigoFuncionalidade.equalsIgnoreCase(funcionalidade.getId().toString())) {
					// Recupera a descri��o da funcionalidade
					descricaoFuncionalidade = funcionalidade.getDescricao();

					// Recupera a cole��o de funcionalidades ocultas
					//funcionalidadesOcultas = funcionalidade.getFuncionalidadeDependencias();

					// Para a pesquisa
					break;
				}
			}
			operacoes = 
				this.getFachada().recuperarOperacoesFuncionalidadesEDependentes(new Integer(codigoFuncionalidade));
			
			
			/**
			 * Pegando todas as FuncionalidadeOperacao e vendo as operacoes que
			 * o usuario tem acesso da funcionlidade escolhido
			 */
			if (grupoFuncionalidades != null && !grupoFuncionalidades.isEmpty()) {
				Iterator iterator = grupoFuncionalidades.iterator();
				while (iterator.hasNext()) {
					GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iterator.next();
					if (grupoFuncionalidadeOperacao.getFuncionalidade().getId().toString().equalsIgnoreCase(codigoFuncionalidade)) {
						operacoesCadastradas.add(grupoFuncionalidadeOperacao.getOperacao());
					}
				}
			}

			// Remove a cole��o de opera��es cadastradas da cole��o
			operacoes.removeAll(operacoesCadastradas);

			// Cria a cole��o de opera��es n�o cadastradas
			Collection operacoesNaoCadastradas = new ArrayList(operacoes);

			// Manda a descri��o da funcionalidade no request
			httpServletRequest.setAttribute("descricaoFuncionalidade", descricaoFuncionalidade);

			// Manda a c�digo da funcionalidade no request
			httpServletRequest.setAttribute("idFuncionalidade", codigoFuncionalidade);

			// Manda a cole��o de opera��es cadastradas no request
			httpServletRequest.setAttribute("operacoesCadastradas",	operacoesCadastradas);

			// Manda a cole��o de opera��es n�o cadastradas no request
			httpServletRequest.setAttribute("operacoesNaoCadastradas", operacoesNaoCadastradas);
		}

		// Retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}
}