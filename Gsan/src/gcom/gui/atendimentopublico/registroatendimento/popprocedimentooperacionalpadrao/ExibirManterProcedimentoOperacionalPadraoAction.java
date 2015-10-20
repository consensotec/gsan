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
package gcom.gui.atendimentopublico.registroatendimento.popprocedimentooperacionalpadrao;

import gcom.atendimentopublico.registroatendimento.ArquivoProcedimentoOperacionalPadrao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1258] Consultar Procedimento Operacional Padr�o
 * 
 * @author Am�lia Pessoa
 * @date 19/12/2011
 */
public class ExibirManterProcedimentoOperacionalPadraoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterProcedimentoOperacionalPadraoAction");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		//Visualiza��o de arquivo
		String visualizar = httpServletRequest.getParameter("visualizar");
		if (visualizar!=null && !visualizar.equals("")){
			Collection<ArquivoProcedimentoOperacionalPadrao> colecao = (Collection<ArquivoProcedimentoOperacionalPadrao>) sessao.getAttribute("colecaoArquivos");
			ArquivoProcedimentoOperacionalPadrao arquivo = null;
			for (ArquivoProcedimentoOperacionalPadrao pop : colecao){
				if (pop.getId()==Integer.parseInt(visualizar)){
					arquivo=pop;
					break;
				}
			}
		
			if (arquivo != null){
				
				OutputStream out = null;
				
				String mimeType = ConstantesSistema.CONTENT_TYPE_GENERICO;
				
				if (arquivo.getNomeExtensaoArquivo().equals(ConstantesSistema.EXTENSAO_DOC)){
					mimeType = ConstantesSistema.CONTENT_TYPE_MSWORD;
				}
				else if (arquivo.getNomeExtensaoArquivo().equals(ConstantesSistema.EXTENSAO_PDF)){
					mimeType = ConstantesSistema.CONTENT_TYPE_PDF;
				}
				else if (arquivo.getNomeExtensaoArquivo().equals(ConstantesSistema.EXTENSAO_JPG)){
					mimeType = ConstantesSistema.CONTENT_TYPE_JPEG;
				}
				
				try {
					httpServletResponse.setContentType(mimeType);
					httpServletResponse.addHeader("Content-Disposition", 
							"attachment; filename="+ arquivo.getNomeArquivo().replace(" ", "_"));
					out = httpServletResponse.getOutputStream();
					
					out.write(arquivo.getBytesArquivo());
					out.flush();
					out.close();
				} 
				catch (IOException e) {
					throw new ActionServletException("erro.sistema", e);
				}
				return retorno;
			}
		}
		Object[] colecaoArquivosReserva=null;
		Collection<ArquivoProcedimentoOperacionalPadrao> colecaoArquivos=new ArrayList<ArquivoProcedimentoOperacionalPadrao>();
		Collection<ArquivoProcedimentoOperacionalPadrao> colecaoAux = new ArrayList<ArquivoProcedimentoOperacionalPadrao>();
		int contador=0;
		
		if (httpServletRequest.getParameter("page.offset") == null || !httpServletRequest
				.getParameter("page.offset").equals("1")) {
			String page = httpServletRequest.getParameter("page.offset");
			Integer page2=1;
			
			if (page!=null){
				page2 = Integer.parseInt(page);
				httpServletRequest.setAttribute("page.offset", page2);
			
			sessao.removeAttribute("colecaoArquivos");
			colecaoArquivosReserva = (Object[]) sessao.getAttribute("colecaoArquivosReserva");
			colecaoArquivos = (Collection<ArquivoProcedimentoOperacionalPadrao>) colecaoArquivosReserva[page2-2];
			
				sessao.setAttribute("colecaoArquivos", colecaoArquivos);
				retorno = new ActionForward(retorno.getName(),
						retorno.getPath() + "?pager.offset="
								+ (((page2) * 10) - 10), false);
				return retorno;
			}
		}
		
		// Limpa o atributo se o usu�rio voltou para o manter
		if (sessao.getAttribute("colecaoArquivos") != null) {
			sessao.removeAttribute("colecaoArquivos");
			sessao.removeAttribute("colecaoArquivosReserva");
		}

		FiltrarArquivoProcedimentoOperacionalPadraoHelper helper = (FiltrarArquivoProcedimentoOperacionalPadraoHelper) sessao.getAttribute("helper");
		colecaoArquivos = fachada.pesquisarArquivoProcedimentoOperacionalPadrao(helper);
		colecaoAux = new ArrayList<ArquivoProcedimentoOperacionalPadrao>();
		Collection<ArquivoProcedimentoOperacionalPadrao> colecaoAuxReserva = new ArrayList<ArquivoProcedimentoOperacionalPadrao>();	
		
		retorno = controlarPaginacao(httpServletRequest, retorno, colecaoArquivos.size());
		contador=0;
		int qntPag=0;
		if (colecaoArquivos.size()>10){
			qntPag = ((Double) Math.ceil(Double.parseDouble(""+colecaoArquivos.size()) / 10)).intValue();
			colecaoArquivosReserva = new Object[qntPag-1];
			for (ArquivoProcedimentoOperacionalPadrao pop :colecaoArquivos){
				if(contador<10){
					colecaoAux.add(pop);
				} 
				else {
					colecaoAuxReserva.add(pop);
				}
				contador++;
			}
			for (int i=0;i<colecaoArquivosReserva.length;i++){
				colecaoArquivosReserva[i] = new ArrayList<ArquivoProcedimentoOperacionalPadrao>();

			}
			int x=0;
			int i=0;
			
			for (ArquivoProcedimentoOperacionalPadrao pop :colecaoAuxReserva){
				((Collection<ArquivoProcedimentoOperacionalPadrao>) colecaoArquivosReserva[i/10]).add(pop);
				i++;
			}
			colecaoArquivos = colecaoAux;
			
			
		}
		
		sessao.setAttribute("colecaoArquivosReserva", colecaoArquivosReserva);
		sessao.removeAttribute("colecaoArquivos");

		// Verifica se a cole��o retornada pela pesquisa � nula, em caso
		// afirmativo comunica ao usu�rio que n�o existe nenhuma fuuncionalidade
		// cadastrada
		// para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condi��es seta o retorno para o
		// ExibirAtualizarTipoSolicitacaoEspecificacaoAction, se n�o atender
		// manda a
		// cole��o pelo request para ser recuperado e exibido pelo jsp.

		if (colecaoArquivos != null && !colecaoArquivos.isEmpty()) {

			// Verifica se a cole��o cont�m apenas um objeto, se est� retornando
			// da pagina��o (devido ao esquema de pagina��o de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usu�rio selecione o link para ir para a
			// segunda p�gina ele n�o v� para tela de atualizar.

			if (colecaoArquivos.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {

				
				// Verifica se o usu�rio marcou o checkbox de atualizar no jsp
				// tipo_solicitacao_filtrar. Caso todas as condi��es sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarTipoSolicitacaoEspecificacaoAction e em caso
				// negativo
				// manda a cole��o pelo request.

				if (httpServletRequest.getParameter("atualizar") != null) {
					retorno = actionMapping
							.findForward("atualizarTipoSolicitacaoEspecificacao");
					ArquivoProcedimentoOperacionalPadrao arquivo = (ArquivoProcedimentoOperacionalPadrao) colecaoArquivos
							.iterator().next();
					sessao.setAttribute("objetoArquivo",
							arquivo);

				} else {
					sessao.setAttribute("colecaoArquivos",
							colecaoArquivos);
				}
				
				
			} else {
				sessao.setAttribute("colecaoArquivos",
						colecaoArquivos);
			}
		} else {
			// Nenhuma funcionalidade cadastrada
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
