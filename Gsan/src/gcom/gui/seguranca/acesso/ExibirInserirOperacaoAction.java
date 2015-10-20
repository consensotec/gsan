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
package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.FiltroOperacaoTipo;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoTipo;
import gcom.seguranca.transacao.FiltroTabelaColuna;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela exibi��o da p�gina de inserir opera��o para uma funcionalidade 
 * 
 * @author Pedro Alexandre
 * @date 05/05/2006
 */
public class ExibirInserirOperacaoAction extends GcomAction {

	
	/**
	 * Inseri uma opera��o de uma funcionalida de no sistema
	 *
	 * [UC0284] Inserir Opera��o
	 *
	 * @author Pedro Alexandre
	 * @date 05/05/2006
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

		//Seta o mapeamento de retorno para a tela de inserir opera��o 
		ActionForward retorno = actionMapping.findForward("operacaoInserir");

		//Recupera o form de inserir opera��o
		InserirOperacaoActionForm inserirOperacaoActionForm = (InserirOperacaoActionForm) actionForm; 
		
		//Cria uma inst�ncia da fachada 
		Fachada fachada = Fachada.getInstancia();

		//Cria uma inst�ncia da sess�o 
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Cria o filtro de tipo de opera��o e pesquisa os tipos de opera��es no sistema
		FiltroOperacaoTipo filtroOperacaoTipo = new FiltroOperacaoTipo();
		Collection<OperacaoTipo> colecaoOperacaoTipo = fachada.pesquisar(filtroOperacaoTipo,OperacaoTipo.class.getName());

		//Caso n�o exista nenhum tipo de opera��o cadastrado no sistema 
		//levanta a exce��o para o usu�rio
		if (colecaoOperacaoTipo == null || colecaoOperacaoTipo.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Opera��o Tipo");
		}

		//Coloca a cole��o de tipo de opera��es na sess�o
		sessao.setAttribute("colecaoOperacaoTipo", colecaoOperacaoTipo);
		
		//Recupera a flag para indicar se o usu�rio apertou o bot�ode desfazer
		String desfazer = httpServletRequest.getParameter("desfazer");
			
		//Caso o usu�rio n�o tenha apertado o bot�o de desfazer na tela
		//verifica todas as valida��es e pesquisas
		//Caso contr�rio limpa o form de inserir opera��o
		if(desfazer == null){
			//Cria a varia�vel que vai armazenar o c�digo do tipo da opera��o
			Integer idOperacaoTipo = null;
			
			//Caso o tipo de opera��o tenha sido informado
			//armazena o c�digo do tipo da opera��o na vari�vel
			if(inserirOperacaoActionForm.getIdTipoOperacao() != null){
				idOperacaoTipo = new Integer(inserirOperacaoActionForm.getIdTipoOperacao());
			}
			
			//Recupera o c�digo da funcionalidade se ela for digitada
			String idFuncionalidadeDigitada = inserirOperacaoActionForm.getIdFuncionalidade();
			
			//Caso o c�digo da funcionalidade tenha sido informado 
			if (idFuncionalidadeDigitada != null && !idFuncionalidadeDigitada.trim().equalsIgnoreCase("")) {
				
				//Pesquisa a funcionalidade digitada na base de dados
				Funcionalidade funcionalidade = this.pesquisarFuncionalidade(idFuncionalidadeDigitada);
				
				//Caso exista a funcionalidade digitada na base de dados 
				//seta as informa��es da funcionalidade no form 
				//Caso contr�rio indica que a funcionalidade digitada n�o existe 
				if(funcionalidade != null){	
					inserirOperacaoActionForm.setIdFuncionalidade(String.valueOf(funcionalidade.getId()));
					inserirOperacaoActionForm.setDescricaoFuncionalidade(funcionalidade.getDescricao());
					httpServletRequest.setAttribute("funcionalidadeEncontrada", "true");
	
				} else {
					inserirOperacaoActionForm.setIdFuncionalidade("");
					inserirOperacaoActionForm.setDescricaoFuncionalidade("FUNCIONALIDADE INEXISTENTE");
					httpServletRequest.setAttribute("funcionalidadeNaoEncontrada","exception");
				}
			}
			
			//Recupera o c�digo do argumento de pesquisa se ele for digitado
			String idArgumentoPesquisaDigitado = inserirOperacaoActionForm.getIdArgumentoPesquisa();
			
			//Caso o c�digo do argumento de pesquisa tenha sido informado 
			if (idArgumentoPesquisaDigitado != null && !idArgumentoPesquisaDigitado.trim().equalsIgnoreCase("")) {
					
				//Pesquisa o argumento de pesquisa digitado na base de dados
				TabelaColuna argumentoPesquisa = this.pesquisarArgumentoPesquisa(idArgumentoPesquisaDigitado);

				//Caso exista o argumento de pesquisa digitado na base de dados 
				//seta as informa��es do argumento de pesquisa no form 
				//Caso contr�rio indica que o argumento de pesquisa digitado n�o existe 
				if(argumentoPesquisa != null){	
					inserirOperacaoActionForm.setIdArgumentoPesquisa(String.valueOf(argumentoPesquisa.getId()));
					inserirOperacaoActionForm.setDescricaoArgumentoPesquisa(argumentoPesquisa.getDescricaoColuna());
					httpServletRequest.setAttribute("argumentoPesquisaEncontrado", "true");
	
				} else {
					inserirOperacaoActionForm.setIdArgumentoPesquisa("");
					inserirOperacaoActionForm.setDescricaoArgumentoPesquisa("ARGUMENTO DE PESQUISA INEXISTENTE");
					httpServletRequest.setAttribute("argumentoPesquisaNaoEncontrado","exception");
				}
			}
			
			//Recupera o c�digo da opera��o de pesquisa se ele for digitado
			String idOperacaoPesquisaDigitado = inserirOperacaoActionForm.getIdOperacaoPesquisa();
			
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
					inserirOperacaoActionForm.setIdOperacaoPesquisa(String.valueOf(operacaoPesquisa.getId()));
					inserirOperacaoActionForm.setDescricaoOperacaoPesquisa(operacaoPesquisa.getDescricao());
					httpServletRequest.setAttribute("operacaoPesquisaEncontrado", "true");
	
				} else {
					inserirOperacaoActionForm.setIdOperacaoPesquisa("");
					inserirOperacaoActionForm.setDescricaoOperacaoPesquisa("OPERA��O DE PESQUISA INEXISTENTE");
					httpServletRequest.setAttribute("operacaoPesquisaNaoEncontrado","exception");
				}
			}
			
			
			
			//[SB0001] Habilitar/Desabilitar Argumento de Pesquisa e Lista de Tabelas 
			//Caso o tipo de opera��o n�o tenha sido informado ou seja em branco
			//desabilita os campos de argumento de pesquisa e opera��o de pesquisa limpando-os
			//Caso contr�rio verifica qual o tipo de opera��o para habilitar os campos
			if(idOperacaoTipo == null || idOperacaoTipo.intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO){
				sessao.setAttribute("habilitarArgumentoPesquisa","false");
				sessao.setAttribute("habilitarOperacaoPesquisa","false");
				sessao.setAttribute("colecaoOperacaoTabela",null);
	
				inserirOperacaoActionForm.setIdArgumentoPesquisa("");
				inserirOperacaoActionForm.setDescricaoArgumentoPesquisa("");
				inserirOperacaoActionForm.setIdOperacaoPesquisa("");
				inserirOperacaoActionForm.setDescricaoOperacaoPesquisa("");
			}else{
				
				//Cria a vari�vel que vai armazenar o tipo de opera��o
				//e pesquisa a opera��o na cole��o pesquisada
				OperacaoTipo operacaoTipoSelecionada = null;
				lacoOperacaoTipo : for(OperacaoTipo operacaoTipoColecao : colecaoOperacaoTipo){
					if(operacaoTipoColecao.getId().intValue() == idOperacaoTipo){
						operacaoTipoSelecionada = operacaoTipoColecao;
						break lacoOperacaoTipo;
					}
				}

				//Caso o tipo de opera��o seja "pesquisar"
				//habilita o campo argumento de pesquisa
				//Caso contr�rio desabilita o campo argumento de pesquisa
				if(idOperacaoTipo.intValue() == OperacaoTipo.PESQUISAR){
					sessao.setAttribute("habilitarArgumentoPesquisa","true");
					sessao.setAttribute("habilitarOperacaoPesquisa","false");
					sessao.setAttribute("colecaoOperacaoTabela",null);
					inserirOperacaoActionForm.setIdOperacaoPesquisa("");
					inserirOperacaoActionForm.setDescricaoOperacaoPesquisa("");
					
				}else{
					sessao.setAttribute("habilitarArgumentoPesquisa","false");
					inserirOperacaoActionForm.setIdArgumentoPesquisa("");
					inserirOperacaoActionForm.setDescricaoArgumentoPesquisa("");
				  
				  //Caso o tipo de opera��o tenha o indicador de atualiza a base de dados 
				  //setado para sim habilita o campo opera��o de pesquisa
				  //Caso contr�rio desabilita o campo opera��o de pesquisa	
				  if(operacaoTipoSelecionada.getIndicadorAtualiza() == ConstantesSistema.SIM){
					  sessao.setAttribute("habilitarOperacaoPesquisa","true");
				  }else{
					  sessao.setAttribute("habilitarOperacaoPesquisa","false");
					  sessao.setAttribute("colecaoOperacaoTabela",null);
					  inserirOperacaoActionForm.setIdOperacaoPesquisa("");
					  inserirOperacaoActionForm.setDescricaoOperacaoPesquisa("");
				  }
				}
			}
			
		}else{
			//Caso o usu�rio tenha apertado o bot�o de desfazer na tela 
			//volta o form ao estado inicial
			inserirOperacaoActionForm.setDescricao("");
			inserirOperacaoActionForm.setDescricaoAbreviada("");
			inserirOperacaoActionForm.setCaminhoUrl("");
			inserirOperacaoActionForm.setIdFuncionalidade("");
			inserirOperacaoActionForm.setDescricaoFuncionalidade("");
			inserirOperacaoActionForm.setIdTipoOperacao("-1");
			inserirOperacaoActionForm.setIdArgumentoPesquisa("");
			inserirOperacaoActionForm.setDescricaoArgumentoPesquisa("");
			inserirOperacaoActionForm.setIdOperacaoPesquisa("");
			inserirOperacaoActionForm.setDescricaoOperacaoPesquisa("");
			sessao.removeAttribute("colecaoOperacaoTabela");
			sessao.setAttribute("habilitarArgumentoPesquisa","false");
			sessao.setAttribute("habilitarOperacaoPesquisa","false");
		}
		
		//Retorna o mapeamento contido na vari�vel retorno
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
		//filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.INDICADOR_CHAVE_PRIMARIA, "2"));
		
		//Pesquisa o argumento de pesquisa na base de dados
		Collection colecaoTabelaColuna = Fachada.getInstancia().pesquisar(filtroTabelaColuna,TabelaColuna.class.getName());

		//Caso exista o argumento de pesquisa cadastrado na base de dados 
		//recupera o argumento de pesquisa da cole��o
		
		/*[FS0011] - Verificar argumento de pesquisa 
		 * Caso 1
		 */	
		
		if(colecaoTabelaColuna != null && !colecaoTabelaColuna.isEmpty()){
			argumentoPesquisa = (TabelaColuna) Util.retonarObjetoDeColecao(colecaoTabelaColuna);
			
			if(argumentoPesquisa.getIndicadorPrimaryKey().intValue() == new Integer("2")){
				throw new ActionServletException("atencao.argumento_pesquisa_nao_chave_primaria");
			}
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
	
}