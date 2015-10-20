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
import gcom.seguranca.acesso.FiltroOperacaoTabela;
import gcom.seguranca.acesso.FiltroOperacaoTipo;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoTabela;
import gcom.seguranca.acesso.OperacaoTipo;
import gcom.seguranca.transacao.FiltroTabelaColuna;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action respons�vel pelo pre-processamento da tela de atualizar
 *
 * @author Pedro Alexandre
 * @date 01/08/2006
 */
public class ExibirAtualizarOperacaoAction extends GcomAction {

	/**
	 * [UC0281] - Manter Opera��o
	 *
	 * @author Pedro Alexandre
	 * @date 01/08/2006
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

		
		//Seta o mapeamento para o action de atualizar
		ActionForward retorno = actionMapping.findForward("atualizarOperacao");

		//Cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		//Cria uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera o form de atualizar
		AtualizarOperacaoActionForm atualizarOperacaoActionForm = (AtualizarOperacaoActionForm) actionForm;

		//Recupera o objeto de consulta
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		
		//Recupera a flag pra indicar a remo��o da tabela 
		String removerTabela = (String) httpServletRequest.getParameter("removerTabela");
		
		//Recupera o tipo de retorno 
		String tipoRetorno = (String) sessao.getAttribute("tipoPesquisaRetorno");
		
		//Cria a vari�vel que vai armazenar o c�digo da opera��o
		String idOperacao = null;
		
		/*
		 * Caso seja a primeira vez que o usu�rio esteja entrando na p�gina do atualizar
		 */
		 if ((objetoConsulta == null || objetoConsulta.equalsIgnoreCase(""))
	    	 && (removerTabela == null || removerTabela.equalsIgnoreCase(""))
		 	 && (httpServletRequest.getParameter("desfazer") == null)
		 	 && (tipoRetorno == null || !tipoRetorno.equalsIgnoreCase("localidade"))){
	           //Recupera o id da Localidade que vai ser atualizada
			
			 
			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar")!= null){
				idOperacao = httpServletRequest.getParameter("idRegistroInseridoAtualizar");
	           	//Definindo a volta do bot�o voltar para filtrar opera��o
				sessao.setAttribute("voltar", "filtrar");
	   	    	sessao.setAttribute("idRegistroAtualizar",idOperacao);
	   	    	sessao.setAttribute("tipoPesquisa",ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
	        }else if(httpServletRequest.getParameter("idRegistroAtualizar") == null){
	        	idOperacao = (String)sessao.getAttribute("idRegistroAtualizar");
	   			//Definindo a volta do bot�o voltar para filtrar opera��o
	        	sessao.setAttribute("voltar", "filtrar");
	        }else if (httpServletRequest.getParameter("idRegistroAtualizar")!= null) {
	        	idOperacao = httpServletRequest.getParameter("idRegistroAtualizar");
		        //Definindo a volta do bot�o voltar para o manter opera��o
	        	sessao.setAttribute("voltar", "manter");
		        sessao.setAttribute("idRegistroAtualizar",idOperacao);
	        }
		}else{
			//Recupera o c�digo da opera��o que vai ser atualizada
			idOperacao = (String)sessao.getAttribute("idRegistroAtualizar");
		}
		 
		 //Seta a flag do voltar no request
		 httpServletRequest.setAttribute("voltar",sessao.getAttribute("voltar"));

		 String atualizarEndereco = (String) httpServletRequest.getParameter("limparCampos");

		if ((objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase(""))
			 || (removerTabela != null && !removerTabela.trim().equalsIgnoreCase(""))
			 || (atualizarEndereco != null && !atualizarEndereco.trim().equalsIgnoreCase(""))) {

			//Recupera o c�digo da funcionalidade se ela for digitada
			String idFuncionalidadeDigitada = atualizarOperacaoActionForm.getIdFuncionalidade();
			
			//Caso o c�digo da funcionalidade tenha sido informado 
			if (idFuncionalidadeDigitada != null && !idFuncionalidadeDigitada.trim().equalsIgnoreCase("")) {
				//Pesquisa a funcionalidade digitada na base de dados
				Funcionalidade funcionalidade = this.pesquisarFuncionalidade(idFuncionalidadeDigitada);
				
				//Caso exista a funcionalidade digitada na base de dados 
				//seta as informa��es da funcionalidade no form 
				//Caso contr�rio indica que a funcionalidade digitada n�o existe 
				if(funcionalidade != null){	
					atualizarOperacaoActionForm.setIdFuncionalidade(String.valueOf(funcionalidade.getId()));
					atualizarOperacaoActionForm.setDescricaoFuncionalidade(funcionalidade.getDescricao());
					httpServletRequest.setAttribute("funcionalidadeEncontrada", "true");
	
				} else {
					atualizarOperacaoActionForm.setIdFuncionalidade("");
					atualizarOperacaoActionForm.setDescricaoFuncionalidade("FUNCIONALIDADE INEXISTENTE");
					httpServletRequest.setAttribute("funcionalidadeNaoEncontrada","exception");
				}
			}
			
			//Recupera o c�digo do argumento de pesquisa se ele for digitado
			String idArgumentoPesquisaDigitado = atualizarOperacaoActionForm.getIdArgumentoPesquisa();
			
			//Caso o c�digo do argumento de pesquisa tenha sido informado 
			if (idArgumentoPesquisaDigitado != null && !idArgumentoPesquisaDigitado.trim().equalsIgnoreCase("")) {
					
				//Pesquisa o argumento de pesquisa digitado na base de dados
				TabelaColuna argumentoPesquisa = this.pesquisarArgumentoPesquisa(idArgumentoPesquisaDigitado);

				//Caso exista o argumento de pesquisa digitado na base de dados 
				//seta as informa��es do argumento de pesquisa no form 
				//Caso contr�rio indica que o argumento de pesquisa digitado n�o existe 
				if(argumentoPesquisa != null){	
					atualizarOperacaoActionForm.setIdArgumentoPesquisa(String.valueOf(argumentoPesquisa.getId()));
					atualizarOperacaoActionForm.setDescricaoArgumentoPesquisa(argumentoPesquisa.getDescricaoColuna());
					httpServletRequest.setAttribute("argumentoPesquisaEncontrado", "true");
	
				} else {
					atualizarOperacaoActionForm.setIdArgumentoPesquisa("");
					atualizarOperacaoActionForm.setDescricaoArgumentoPesquisa("ARGUMENTO DE PESQUISA INEXISTENTE");
					httpServletRequest.setAttribute("argumentoPesquisaNaoEncontrado","exception");
				}
			}
			
			//Recupera o c�digo da opera��o de pesquisa se ele for digitado
			String idOperacaoPesquisaDigitado = atualizarOperacaoActionForm.getIdOperacaoPesquisa();
			
			//Caso o c�digo da opera��o de pesquisa tenha sido informado 
			if (idOperacaoPesquisaDigitado != null && !idOperacaoPesquisaDigitado.trim().equalsIgnoreCase("")) {
					
				//Pesquisa a opera��o de pesquisa digitada na base de dados
				Operacao operacaoPesquisa = this.pesquisarOperacaoPesquisa(idOperacaoPesquisaDigitado);
				
				//Caso exista a opera��o de pesquisa digitada na base de dados 
				//seta as informa��es da opera��o de pesquisa no form 
				//Caso contr�rio indica que a opera��o de pesquisa digitada n�o existe 
				if(operacaoPesquisa != null){	
					//FS0008] - Verificar tipo da opera��o -R�mulo Aur�lio 11/05/2007
					if(operacaoPesquisa.getOperacaoTipo() != null){
						if(operacaoPesquisa.getOperacaoTipo().getId().intValue() != OperacaoTipo.PESQUISAR){
							throw new ActionServletException("atencao.operacao_pesquisa_invalida");
						}
					}
					atualizarOperacaoActionForm.setIdOperacaoPesquisa(String.valueOf(operacaoPesquisa.getId()));
					atualizarOperacaoActionForm.setDescricaoOperacaoPesquisa(operacaoPesquisa.getDescricao());
					httpServletRequest.setAttribute("operacaoPesquisaEncontrado", "true");
	
				} else {
					atualizarOperacaoActionForm.setIdOperacaoPesquisa("");
					atualizarOperacaoActionForm.setDescricaoOperacaoPesquisa("OPERA��O DE PESQUISA INEXISTENTE");
					httpServletRequest.setAttribute("operacaoPesquisaNaoEncontrado","exception");
				}
			}
			
			/*
			 * Caso o bot�o de desfazer esteja vazio
			 */
		} else if (httpServletRequest.getParameter("desfazer") == null) {
			
			//Recupera o codigo da opera��o do form
			String idOperacaoForm = atualizarOperacaoActionForm.getIdOperacao();
			
			//Caso n�o esteja informado o id da opera��o
			if ((idOperacao == null || idOperacao.equalsIgnoreCase(""))&& (idOperacaoForm == null	|| idOperacaoForm.equalsIgnoreCase(""))) {
				throw new ActionServletException("atencao.codigo_localidade_nao_informado");
			} else {

				// Carregamento inicial do formul�rio.
				// ===================================================================

				//Se o c�digo da opera��o tenha sido informado	
				if (idOperacao != null && !idOperacao.equalsIgnoreCase("")) {

					if (sessao.getAttribute("colecaoTabelaOperacao") == null) {

						//Pesquisa os tipos de opera��o e seta a cole��o no request
						FiltroOperacaoTipo filtroOperacaoTipo = new FiltroOperacaoTipo();
						Collection<OperacaoTipo> colecaoTipoOperacao = fachada.pesquisar(filtroOperacaoTipo,OperacaoTipo.class.getName());
						if (colecaoTipoOperacao == null || colecaoTipoOperacao.isEmpty()) {
							throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Tabela Opera��o");
						}
						httpServletRequest.setAttribute("colecaoTipoOperacao", colecaoTipoOperacao);
					}
					//Chama o met�do para exibir a opera��o
					this.exibirOperacao( idOperacao,atualizarOperacaoActionForm,fachada, sessao,httpServletRequest);
				}
			}
		}

		
		/*
		 * Caso o usu�rio tenha apertado o bot�o de desfazer, retira as cole��es de tipo de opera��o e
		 * a cole��o de tabela de opera��o da sess�o
		 * e chama o met�do para exibir a opera��o 
		 * da forma que ela est� cadastrada na base
		 */
		 if (httpServletRequest.getParameter("desfazer") != null && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
			 sessao.removeAttribute("tipoPesquisaRetorno");
			 sessao.removeAttribute("colecaoOperacaoTabela");
			 this.exibirOperacao( idOperacao, atualizarOperacaoActionForm,fachada, sessao, httpServletRequest);
		 }
	        	
		// Devolve o mapeamento de retorno
		return retorno;
	}


	/**
	 * Pesquisa a funcionalidade digitada na base de dados de acordo com o c�digo passado
	 *
	 * [FS0004 - Pesquisar Funcionalidade]
	 *
	 * @author Pedro Alexandre
	 * @date 11/05/2006
	 *
	 * @param idFuncionalidade
	 * @return
	 */
	private Funcionalidade pesquisarFuncionalidade(String idFuncionalidade){
		//Cria a vari�vel que vai armazenar a funcionalidade pesquisada
		Funcionalidade funcionalidade = null;
		
		//Cria o filtro para pesquisa e seta o c�digo da funcionalidade informada no filtro
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(new ParametroSimples(FiltroFuncionalidade.ID, idFuncionalidade));
		
		//Pesquisa a funcionalidade na base de dados
		Collection colecaoFuncionalidade = Fachada.getInstancia().pesquisar(filtroFuncionalidade,Funcionalidade.class.getName());
		
		//Caso exista a funcionalidade cadastrada na base de dados 
		//recupera a funcionalidade da cole��o
		if(colecaoFuncionalidade != null && !colecaoFuncionalidade.isEmpty()){
			funcionalidade = (Funcionalidade) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
		}
		
		//Retorna a funcionalidade pesquisa ou nulo se a funcionalidade n�o for encontrada
		return funcionalidade;
		
	}
	
	/**
	 * Pesquisa a opera��o de pesquisa digitada na base de dados de acordo com o c�digo passado
	 *
	 * [FS0007 - Verificar exist�ncia da opera��o]
	 *
	 * @author Pedro Alexandre
	 * @date 11/05/2006
	 *
	 * @param idOperacaoPesquisa
	 * @return
	 */
	private Operacao pesquisarOperacaoPesquisa(String idOperacaoPesquisa){
		//Cria a vari�vel que vai armazenar a opera��o de pesquisa pesquisada
		Operacao operacaoPesquisa = null;
		
		//Cria o filtro para pesquisa e seta o c�digo da opera��o de pesquisa informada no filtro
		FiltroOperacao filtroOperacao = new FiltroOperacao();
		filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, idOperacaoPesquisa));
		
		//Pesquisa a opera��o de pesquisa na base de dados
		Collection colecaoOperacao = Fachada.getInstancia().pesquisar(filtroOperacao,Operacao.class.getName());
		
		//Caso exista a opera��o de pesquisa cadastrada na base de dados 
		//recupera a opera��o de pesquisa da cole��o
		if(colecaoOperacao != null && !colecaoOperacao.isEmpty()){
			operacaoPesquisa = (Operacao) Util.retonarObjetoDeColecao(colecaoOperacao);
		}
		
		//Retorna a opera��o de pesquisa ou nulo se a opera��o de pesquisa n�o for encontrada
		return operacaoPesquisa;
	}
	
	/**
	 * Pesquisa o argumento de pesquisa digitado na base de dados de acordo com o c�digo passado
	 *
	 * [FS0005 - Verificar argumento de pesquisa]
	 *
	 * @author Pedro Alexandre
	 * @date 11/05/2006
	 *
	 * @param idArgumentoPesquisa
	 * @return
	 */
	private TabelaColuna pesquisarArgumentoPesquisa(String idArgumentoPesquisa){
		
		//Cria a vari�vel que vai armazenar o argumento de pesquisa pesquisado
		TabelaColuna argumentoPesquisa = null;
		
		//Cria o filtro para pesquisa e seta o c�digo do argumento de pesquisa informado no filtro
		FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
		filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.ID, idArgumentoPesquisa));
		
		//Pesquisa o argumento de pesquisa na base de dados
		Collection colecaoTabelaColuna = Fachada.getInstancia().pesquisar(filtroTabelaColuna,TabelaColuna.class.getName());

		//Caso exista o argumento de pesquisa cadastrado na base de dados 
		//recupera o argumento de pesquisa da cole��o
		if(colecaoTabelaColuna != null && !colecaoTabelaColuna.isEmpty()){
			argumentoPesquisa = (TabelaColuna) Util.retonarObjetoDeColecao(colecaoTabelaColuna);
			
			//Caso exista o argumento de pesquisa cadastrado na base de dados 
			//recupera o argumento de pesquisa da cole��o
			
			/*[FS0011] - Verificar argumento de pesquisa 
			 * Caso 1
			 */	
			
			// Retirando a cr�tica de obrigar o argumento de pesquisa ser chave-prim�ria - Francisco
//				if(argumentoPesquisa.getIndicadorPrimaryKey().intValue() == new Integer("2")){
//					throw new ActionServletException("atencao.argumento_pesquisa_nao_chave_primaria");
//				}
			}
			
			/*[FS0011] - Verificar argumento de pesquisa 
			 * Caso 2
			 */	
			FiltroOperacao filtroOperacao = new FiltroOperacao();
			
			filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.TABELA_COLUNA_ID,idArgumentoPesquisa));
			
			Collection colecaoVerificacaoArgumentoPesquisa = Fachada.getInstancia().
																pesquisar(filtroOperacao,Operacao.class.getName());
			
			if(colecaoVerificacaoArgumentoPesquisa != null && !colecaoVerificacaoArgumentoPesquisa.isEmpty()){
				
				Operacao operacao = (Operacao) Util.retonarObjetoDeColecao(colecaoVerificacaoArgumentoPesquisa);
				
			throw new ActionServletException("atencao.argumento_de_outra_operacao", null,operacao.getDescricao());
			
		}
		
		//Retorna o argumento de pesquisa ou nulo se o argumento de pesquisa n�o for encontrado
		return argumentoPesquisa;
	}
	
	/**
	 * Met�do para exibir os dados da opera��o na p�gina de atualizar 
	 *
	 * @author Administrador
	 * @date 04/08/2006
	 *
	 * @param idOperacao
	 * @param atualizarOperacaoActionForm
	 * @param fachada
	 * @param sessao
	 * @param httpServletRequest
	 */
	private void exibirOperacao(String idOperacao, 
    		AtualizarOperacaoActionForm atualizarOperacaoActionForm,
			Fachada fachada,HttpSession sessao,
			HttpServletRequest httpServletRequest) {
		
		//Cria a vari�vel que vai armazenar a cole��o de opera��o
		Collection colecaoPesquisa = null;

		//Pesquisa a opera��o de acordo com o id informado
		FiltroOperacao filtroOperacao = new FiltroOperacao();
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("operacaoTipo");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("idOperacaoPesquisa");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.ARGUMENTO_PESQUISA);
		filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, idOperacao));
		

		//Atribui a cole��o de opera��o a vari�vel
		colecaoPesquisa = fachada.pesquisar(filtroOperacao,Operacao.class.getName());

		/*
		 * Cason�o exista nenhuma opera��o cadastrada com o id informado 
		 * levanta uma exce��o para o usu�rio indicando que a opera��o com o
		 * id informado n�o est� cadastrada
		 * Caso contr�rio exibir os dados da opera��o na p�ginado atualizar
		 */
		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado",null,"Opera��o");
		} else {
			//Recupera a opera��o da cole��o
			Operacao operacao = (Operacao) Util.retonarObjetoDeColecao(colecaoPesquisa);
			
			//Recupera o tipo da opera��o 
			OperacaoTipo operacaoTipoSelecionada = operacao.getOperacaoTipo();
			Integer idOperacaoTipo = operacaoTipoSelecionada.getId();

			//Recupera o c�digo do tipo da opera��o no form caso o usu�rio 
			//tenha selecionado outro tipo de opera��o
			String idOperacaoTipoForm = atualizarOperacaoActionForm.getIdTipoOperacao();
			
			/*
			 * Caso o usu�rio tenha informado o tipo de opre��o na p�gina do 
			 * atualizar, atribui o id do tipo da opera��o do form a vari�vel 
			 * que armazena qual o tipo da opera��o.
			 */
			if(idOperacaoTipoForm != null && !idOperacaoTipoForm.trim().equalsIgnoreCase("-1") ){
				idOperacaoTipo = new Integer(idOperacaoTipoForm);
				FiltroOperacaoTipo  filtroOperacaoTipoForm = new FiltroOperacaoTipo();
				filtroOperacaoTipoForm.adicionarParametro(new ParametroSimples(FiltroOperacaoTipo.ID,idOperacaoTipo));
				operacaoTipoSelecionada = (OperacaoTipo)Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtroOperacaoTipoForm, OperacaoTipo.class.getName()));
			}
			
			//Joga na sess�o a opera��o que vai ser atualizada 
			sessao.setAttribute("operacaoAtualizar", operacao);

			//Seta no form as informa��es da opera��o para ser atualizada
			atualizarOperacaoActionForm.setIdOperacao(idOperacao);
			atualizarOperacaoActionForm.setDescricao(operacao.getDescricao());
			atualizarOperacaoActionForm.setDescricaoAbreviada(operacao.getDescricaoAbreviada());
			atualizarOperacaoActionForm.setCaminhoUrl(operacao.getCaminhoUrl());
			atualizarOperacaoActionForm.setIdTipoOperacao(""+idOperacaoTipo);
			atualizarOperacaoActionForm.setIdFuncionalidade(""+operacao.getFuncionalidade().getId());
			atualizarOperacaoActionForm.setDescricaoFuncionalidade(""+operacao.getFuncionalidade().getDescricao());			
			
			if (operacao.getArgumentoPesquisa() != null){
				atualizarOperacaoActionForm.setIdArgumentoPesquisa(operacao.getArgumentoPesquisa().getId() + "");
				atualizarOperacaoActionForm.setDescricaoArgumentoPesquisa(operacao.getArgumentoPesquisa().getDescricaoColuna());
			}
			
			/*
			 * Pesquisa na base as colunas tabelas de opera��o cadastradas
			 * para a opera��o que vai ser atualizada
			 */
			
//			if(operacao.getTabelaColuna() != null){
//			FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
//			
//			filtroTabelaColuna.adicionarParametro(new ParametroSimples
//					(FiltroTabelaColuna.ID,operacao.getTabelaColuna().getId()));
//			
//			Collection colecaoTabelaColuna = fachada.pesquisar(filtroTabelaColuna,TabelaColuna.class.getName());
//			
//				if(colecaoTabelaColuna != null && !colecaoTabelaColuna.isEmpty()){
//					
//					TabelaColuna tabelaColuna = (TabelaColuna)colecaoTabelaColuna.iterator().next();
//					
//					atualizarOperacaoActionForm.setIdArgumentoPesquisa(tabelaColuna.getId().toString());
//					
//					atualizarOperacaoActionForm.setDescricaoArgumentoPesquisa(tabelaColuna.getDescricaoColuna());
//					
//				}
//			}
			/*
			 * Pesquisa na base as tabelas de opera��o cadastradas
			 * para a opera��o que vai ser atualizada
			 */
			FiltroOperacaoTabela filtroOperacaoTabela = new FiltroOperacaoTabela();
			filtroOperacaoTabela.adicionarParametro(new ParametroSimples(FiltroOperacaoTabela.OPERACAO_ID,idOperacao));
			filtroOperacaoTabela.adicionarCaminhoParaCarregamentoEntidade("tabela");
			Collection colecaoOperacaoTabelaCadastradas = fachada.pesquisar(filtroOperacaoTabela,OperacaoTabela.class.getName());
			
			/*
			 * Caso a cole��o de tabela de opera��o n�o esteja vazia 
			 * coloca a cole��o no itrator para recuperar todos os objetos tabela 
			 * da cole��o para ser colados na cole��o de tabela e setados 
			 * na sess�o 
			 */
			if(colecaoOperacaoTabelaCadastradas != null && !colecaoOperacaoTabelaCadastradas.isEmpty()){
				//Vari�vel que vai armazenar as tabelas para a opera��o
				Collection<Tabela> colecaoOperacaoTabela = new ArrayList();
				
				//Coloca a cole�a� no iterator
				Iterator<OperacaoTabela> iteratorOperacaoTabela = colecaoOperacaoTabelaCadastradas.iterator();
				
				//La�o para recuperar as tabelas da opera��o
				while (iteratorOperacaoTabela.hasNext()) {
					OperacaoTabela operacaoTabela = iteratorOperacaoTabela.next();
					colecaoOperacaoTabela.add(operacaoTabela.getTabela());
				}
				
				//Caso n�o exista a cole��o na sess�o seta a cole��o de tabelasna sess�o
				if(sessao.getAttribute("colecaoOperacaoTabela") == null){
					sessao.setAttribute("colecaoOperacaoTabela",colecaoOperacaoTabela);
				}
			}
			
			/*
			 * [FS0003] - Habilitar/Desabilitar Argumento de Pesquisa e Lista de Tabelas	 
			 * Caso o tipo de opera��o seja "pesquisar"
			 * habilita o campo argumento de pesquisa
			 * Caso contr�rio desabilita o campo argumento de pesquisa
			 */
			if(idOperacaoTipo.intValue() == OperacaoTipo.PESQUISAR){
//				sessao.setAttribute("habilitarArgumentoPesquisa","true");
				sessao.setAttribute("habilitarOperacaoPesquisa","false");
				sessao.setAttribute("colecaoOperacaoTabela",null);
				atualizarOperacaoActionForm.setIdOperacaoPesquisa("");
				atualizarOperacaoActionForm.setDescricaoOperacaoPesquisa("");
				
			}else{
//				sessao.setAttribute("habilitarArgumentoPesquisa","false");
//				atualizarOperacaoActionForm.setIdArgumentoPesquisa("");
//				atualizarOperacaoActionForm.setDescricaoArgumentoPesquisa("");
			  
			  //Caso o tipo de opera��o tenha o indicador de atualiza a base de dados 
			  //setado para sim habilita o campo opera��o de pesquisa
			  //Caso contr�rio desabilita o campo opera��o de pesquisa	
			  if(operacaoTipoSelecionada.getIndicadorAtualiza() == ConstantesSistema.SIM){
				  
				  sessao.setAttribute("habilitarOperacaoPesquisa","true");
				  if(operacao.getIdOperacaoPesquisa()!=null){
				  atualizarOperacaoActionForm.setIdOperacaoPesquisa(
						  operacao.getIdOperacaoPesquisa().getId().toString());
				  
				  atualizarOperacaoActionForm.setDescricaoOperacaoPesquisa(
						  operacao.getIdOperacaoPesquisa().getDescricao());
				  }
				   
				  
			  }else{
				  sessao.setAttribute("habilitarOperacaoPesquisa","false");
				  sessao.setAttribute("colecaoOperacaoTabela",null);
				  atualizarOperacaoActionForm.setIdOperacaoPesquisa("");
				  atualizarOperacaoActionForm.setDescricaoOperacaoPesquisa("");
			  }
			}

			
			
		}
	}
}
