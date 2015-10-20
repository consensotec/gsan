
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
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel por gerar um filtro  de opera��o para pesquisa de acordo 
 * com os par�metros informados pelo usu�rio
 *
 * @author Pedro Alexandre 
 * @date 12/05/2006
 */
public class FiltrarOperacaoAction   extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
		
		//Cria a variavel de retorno e seta o mapeamento para o exibirManterOperacaoAction
    	ActionForward retorno = actionMapping.findForward("retornarFiltroOperacao");
    	
    	//Recupera o form de filtrar opera��o
        FiltrarOperacaoActionForm filtrarOperacaoActionForm = (FiltrarOperacaoActionForm) actionForm;
        
        //Cria uma inst�ncia da sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Cria uma inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //Cria uma flag que vai indicar se o usu�rio informou ao menos um campo para a filtragem
        Boolean peloMenosUmParametroInformado = false;
        
        //Recupera os campos informados pelo usu�rio na p�gina para filtrar as opera��es 
        String idOperacao = filtrarOperacaoActionForm.getIdOperacao();
		String descricaoOperacao = filtrarOperacaoActionForm.getDescricaoOperacao();
		Integer idTipoOperacao = new Integer(filtrarOperacaoActionForm.getIdTipoOperacao());
		String idFuncionalidade = filtrarOperacaoActionForm.getIdFuncionalidade();	
		String tipoPesquisa = filtrarOperacaoActionForm.getTipoPesquisa();
		
		//Recupera o indicador de atualiza��o do request
		// 1 check   --- null uncheck 
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");

		//Cria o filtro e carrega os objetos necess�rios no filtro para o exibirManterOperacaoAction
		FiltroOperacao filtroOperacao = new FiltroOperacao();
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("operacaoTipo");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("idOperacaoPesquisa");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("tabelaColuna");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.ARGUMENTO_PESQUISA);

		//Caso o us�rio tenha informado o c�digo da opera��o
		//seta o c�digo da opera��o no filtro e indica que o usu�rio 
		//selecionou um par�metro para a filtragem
		if (idOperacao != null && !idOperacao.equals("")){
			filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID,idOperacao)); 
			peloMenosUmParametroInformado = true;
		}		

		//Caso o us�rio tenha informado descri��o da opera��o
		//seta a descri��o da opera��o no filtro e indica que o usu�rio 
		//selecionou um par�metro para a filtragem
		if ((descricaoOperacao != null)&& (!descricaoOperacao.trim().equals(""))) {
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				filtroOperacao.adicionarParametro(new ComparacaoTextoCompleto(FiltroOperacao.DESCRICAO, descricaoOperacao));
			} else {
				filtroOperacao.adicionarParametro(new ComparacaoTexto(FiltroOperacao.DESCRICAO,descricaoOperacao));
			}
			peloMenosUmParametroInformado = true;
		}
		
		//Caso o us�rio tenha informado o tipo da opera��o
		//seta o c�digo do tipo da opera��o no filtro e indica que o usu�rio 
		//selecionou um par�metro para a filtragem
		if (idTipoOperacao != null && idTipoOperacao.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
    		filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.OPERACAO_TIPO_ID,idTipoOperacao.toString()));
			peloMenosUmParametroInformado = true;
		}
		

		//Caso o us�rio tenha informado o c�digo da funcionalidade
		//seta o c�digo da funcionalidade no filtro e indica que o usu�rio 
		//selecionou um par�metro para a filtragem
		if (idFuncionalidade != null && !idFuncionalidade.trim().equals("")) {

			//[FS0002] - Verificar exist�ncia da funcionalidade 
			//Pesquisa a funcionalidade informada pelo usu�rio no sistema 
			FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
			filtroFuncionalidade.adicionarParametro(new ParametroSimples(FiltroFuncionalidade.ID,idFuncionalidade));
			Collection colecaoFuncionalidade = fachada.pesquisar(filtroFuncionalidade,Funcionalidade.class.getName());
			
			//Caso a funcionalidade informada n�o exista no sistema 
			//levanta uma exece��o indicanf]do que a funcionalidade n�o existe
			//Caso contr�rio seta oc�digo da funcionalidade no filtro 
			if(colecaoFuncionalidade == null || colecaoFuncionalidade.isEmpty()){
				throw new ActionServletException("atencao.funcionalidade.inexistente");
			}else{
				filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.FUNCIONALIDADE_ID,idFuncionalidade));
				peloMenosUmParametroInformado = true;
			}
		}

		//[FS0003] - Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado){
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		//Seta na sess�o o filtro criado para pesquisa de opera��o e a flag 
		//indicando que o usu�rio que ir diretopara a p�gina de atualizar
		sessao.setAttribute("filtroOperacao",filtroOperacao);
		sessao.setAttribute("indicadorAtualizar",indicadorAtualizar );
	
	   //Retorna o mapeamento contido na vari�vel retorno 	
       return retorno;
    }
}
