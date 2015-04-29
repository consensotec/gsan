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
package gsan.gui.micromedicao.hidrometro;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.hidrometro.FiltroHidrometroMovimentacao;
import gsan.micromedicao.hidrometro.FiltroHidrometroMovimentado;
import gsan.micromedicao.hidrometro.HidrometroMovimentacao;
import gsan.micromedicao.hidrometro.HidrometroMovimentado;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Fernanda Paiva
 */
public class ExibirConsultarMovimentacaoHidrometroAction extends GcomAction {

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

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("consultarMovimentacaoHidrometro");

		// Obt�m a fachada
		Fachada fachada = Fachada.getInstancia();

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		// remove objetos da sess�o vindos do filtro
		sessao.removeAttribute("colecaoHidrometroMotivoMovimentacao");
		sessao.removeAttribute("ManutencaoRegistroActionForm");
		
		// Cria cole��o
		Collection colecaoHidrometroMovimentacao = null;

		Collection colecaoHidrometroMovimentado = null;

		FiltroHidrometroMovimentacao filtroHidrometroMovimentacao = (FiltroHidrometroMovimentacao) httpServletRequest
				.getAttribute("filtroMovimentacaoHidrometro");

		if (sessao.getAttribute("fixo") != null && 
        	!sessao.getAttribute("fixo").equals("")) {
            
        	String fixo = (String) sessao.getAttribute("fixo");
            String faixaInicial = (String) sessao.getAttribute("faixaInicial");
            String faixaFinal = (String) sessao.getAttribute("faixaFinal");
            
            // 1� Passo - Pegar o total de registros atrav�s de um count da consulta
    		// que aparecer� na tela

            Integer totalRegistros = 
            	this.getFachada().pesquisarNumeroHidrometroMovimentacaoPorFaixaCount(fixo,
            		fixo + faixaInicial, 
            		fixo + faixaFinal);
            
            // 2� Passo - Chamar a fun��o de Pagina��o passando o total de registros
    		retorno = 
    			this.controlarPaginacao(httpServletRequest, retorno,totalRegistros);
            
    		colecaoHidrometroMovimentacao = 
    			this.getFachada().pesquisarNumeroHidrometroMovimentacaoPorFaixaPaginacao(
    					fixo + faixaInicial, 
    					fixo + faixaFinal, 
    				((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa")));
    	
        }else{
        	
        	// Aciona o controle de pagina��o para que sejam pesquisados apenas
        	// os registros que aparecem na p�gina
        	Map resultado = controlarPaginacao(httpServletRequest, retorno,
        			filtroHidrometroMovimentacao, HidrometroMovimentacao.class.getName());
        	colecaoHidrometroMovimentacao = (Collection) resultado.get("colecaoRetorno");
        	retorno = (ActionForward) resultado.get("destinoActionForward");
        }
		
		

		if (!colecaoHidrometroMovimentacao.isEmpty()) {

			Iterator hidrometroMovimentacaoIterator = colecaoHidrometroMovimentacao
					.iterator();

			while (hidrometroMovimentacaoIterator.hasNext()) {

				HidrometroMovimentacao hidrometroMovimentacao = (HidrometroMovimentacao) hidrometroMovimentacaoIterator
						.next();

				FiltroHidrometroMovimentado filtroHidrometroMovimentado = new FiltroHidrometroMovimentado();

				filtroHidrometroMovimentado
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroMovimentado.HIDROMETRO_MOVIMENTACAO_ID,
								hidrometroMovimentacao.getId()));

				colecaoHidrometroMovimentado = fachada.pesquisar(
						filtroHidrometroMovimentado,
						HidrometroMovimentado.class.getName());

				Integer quantidade = colecaoHidrometroMovimentado.size();

				hidrometroMovimentacao.setQuantidade(quantidade.toString());
			}
		}
		// Caso a cole��o seja null
		if (colecaoHidrometroMovimentacao == null
				|| colecaoHidrometroMovimentacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		// Envia objeto na sess�o
		sessao.setAttribute("colecaoHidrometroMovimentacao",
				colecaoHidrometroMovimentacao);

		// devolve o mapeamento de retorno
		return retorno;
	}
}
