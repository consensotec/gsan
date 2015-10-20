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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovelSimplificado;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.FiltroEntidadeBeneficente;
import gcom.cadastro.imovel.FiltroImovelDoacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelDoacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

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
 * [UC0389] - Inserir Im�vel Doa��o Action respons�vel pela pre-exibi��o da
 * pagina de inserir ImovelDoacao
 * 
 * @author C�sar Ara�jo
 * @created 22 de agosto de 2006
 */
public class ExibirManterImovelDoacaoAction extends GcomAction {
	/**
	 * Description of the Method
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

		/*** Declara e inicializa vari�veis ***/
		String idImovel                                            = null;
		Fachada fachada                                            = null;
		String nomeCliente                                         = null;
		String situacaoAgua                                        = null;
		String situacaoEsgoto                                      = null;
		ActionForward retorno                                      = null;
		Imovel imovelEncontrado                                    = null;
		FiltroImovelDoacao filtroImovelDoacao                      = null;
		FiltroClienteImovel filtroClienteImovel                    = null;
		Collection<ImovelDoacao> colecaoImovelDoacao               = null;
		Collection<ClienteImovelSimplificado> colecaoClienteImovel = null;

		/*** Procedimentos b�sicos para execu��o do m�todo ***/
		retorno = actionMapping.findForward("manterImovelDoacao");
		fachada = Fachada.getInstancia();
		
		/*** Testa se � um reload ou se est� vindo da tela de sucesso de inser��o de imovel doacao ***/
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			idImovel = httpServletRequest.getParameter("idRegistroAtualizacao"); 	
		} else {
			idImovel = (String)httpServletRequest.getParameter("idImovel");	
		}
		
		if (idImovel != null && !idImovel.trim().equals("")) {
			imovelEncontrado = (Imovel)fachada.pesquisarImovelDigitado(new Integer(idImovel));
			if (imovelEncontrado != null) {
				httpServletRequest.setAttribute("idImovelEncontrado", imovelEncontrado.getId().toString());
				httpServletRequest.setAttribute("inscricaoImovelEncontrado", imovelEncontrado.getInscricaoFormatada());

				/*** Prepara o filtro de cliente imovel para a pesquisa ***/
				filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imovelEncontrado.getId()));
				colecaoClienteImovel = (Collection<ClienteImovelSimplificado>)fachada.pesquisarClienteImovel(filtroClienteImovel);
				
				/*** Percorre a cole��o para identificar o cliente v�lido, 
				 *   ou seja, que tem data de fim rela�ao nula ***/				
				for (ClienteImovelSimplificado clienteImovel: colecaoClienteImovel) {
					if (clienteImovel.getDataFimRelacao() == null) {
					    nomeCliente = clienteImovel.getCliente().getNome();
					}
				}
								
				
				/*** Define filtro pra pesquisar e alimenta a colecao de entidade beneficente ***/	
				
				//--------------------------------------------------------------
				// CRC933 
				// Autor: Yara T. Souza
				// Data : 06/01/2009
				//--------------------------------------------------------------
				
				HttpSession sessao = httpServletRequest.getSession(false);				
				Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");		
				
				FiltroEntidadeBeneficente filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();		
				filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("cliente");		
				filtroEntidadeBeneficente.adicionarParametro(new ParametroSimples(FiltroEntidadeBeneficente.ID_EMPRESA, usuarioLogado.getEmpresa().getId()));
				Collection collEntidadeBeneficente =  fachada.pesquisar(filtroEntidadeBeneficente, EntidadeBeneficente.class.getName());
				
				Collection<Integer> colecaoIdsEntidades = null;
				
				if(collEntidadeBeneficente != null && !collEntidadeBeneficente.isEmpty()){
					colecaoIdsEntidades = new ArrayList<Integer>();
					
					Iterator<EntidadeBeneficente> iteratorEntidades = collEntidadeBeneficente.iterator();
					
					while(iteratorEntidades.hasNext()){
						colecaoIdsEntidades.add(new Integer(iteratorEntidades.next().getId()));
					}
				}
				//--------------------------------------------------------------
				
				/*** Atribui os de situa��o �gua esgoto***/
				situacaoAgua   = imovelEncontrado.getLigacaoAguaSituacao().getDescricao();
				situacaoEsgoto = imovelEncontrado.getLigacaoEsgotoSituacao().getDescricao();
				/*** Seta um atributo no request para cor do campo de inscricao do im�vel para preto***/
				httpServletRequest.setAttribute("corInscricao", "#000000");
				/*** Prepara o filtro de im�vel doacao para a pesquisa ***/
				filtroImovelDoacao = new FiltroImovelDoacao();
				filtroImovelDoacao.adicionarParametro(new ParametroSimples(FiltroImovelDoacao.ID_IMOVEL, imovelEncontrado.getId()));
				
				if(colecaoIdsEntidades != null && !colecaoIdsEntidades.isEmpty()){
					filtroImovelDoacao.adicionarParametro(new ParametroSimplesIn(FiltroImovelDoacao.ID_ENTIDADE_BENEFICENTE, colecaoIdsEntidades));
					
				}
				
				filtroImovelDoacao.adicionarCaminhoParaCarregamentoEntidade("entidadeBeneficente.cliente");
				filtroImovelDoacao.adicionarCaminhoParaCarregamentoEntidade("usuarioAdesao");
				filtroImovelDoacao.adicionarCaminhoParaCarregamentoEntidade("usuarioCancelamento");
				
				colecaoImovelDoacao = (Collection<ImovelDoacao>)fachada.pesquisar(filtroImovelDoacao, ImovelDoacao.class.getName());
				
				/*** Valida se a cole��o est� preenchida ***/
				if (colecaoImovelDoacao != null && colecaoImovelDoacao.size() == 0) {
					throw new ActionServletException("atencao.naocadastrado", null, "Im�vel(eis) Doa��o(�os)");		
				}
				
		        httpServletRequest.setAttribute("colecaoImovelDoacao", colecaoImovelDoacao);
				
			} else {
				/*** Caso a pesquisa de im�vel tenha n�o tenha um resultado adequado ***/
				/*** Seta um atributo no request para cor do campo de inscricao do im�vel para preto***/
				httpServletRequest.setAttribute("corInscricao", "#ff0000");
				httpServletRequest.setAttribute("inscricaoImovelEncontrado", ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			}
		} else {
			httpServletRequest.setAttribute("idImovelEncontrado", null);
			httpServletRequest.setAttribute("inscricaoImovelEncontrado", null);
		}
		/*** Atribui os dados do im�vel ***/
		httpServletRequest.setAttribute("nomeCliente", nomeCliente);
		httpServletRequest.setAttribute("situacaoAgua", situacaoAgua);
		httpServletRequest.setAttribute("situacaoEsgoto", situacaoEsgoto);

		return retorno;
	}
}
