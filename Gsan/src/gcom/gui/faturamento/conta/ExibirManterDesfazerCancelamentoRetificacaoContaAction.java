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
package gcom.gui.faturamento.conta;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
 * Action respons�vel pela exibi��o dos dados na tela do 
 * 	desfazer cancelamento e/ou retifica��o da conta. 
 * 
 * [UC0327] Desfazer Cancelamento e/ou Retifica��o de Conta 
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
public class ExibirManterDesfazerCancelamentoRetificacaoContaAction extends
		GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("desfazerCancelamentoRetificacaoConta");

		DesfazerCancelamentoRetificacaoContaActionForm desfazerCancelamentoRetificacaoContaActionForm = (DesfazerCancelamentoRetificacaoContaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// -------Parte que trata do c�digo quando o usu�rio tecla enter
		// Matr�cula do Im�vel
		String codigoDigitadoImovelEnter = (String) desfazerCancelamentoRetificacaoContaActionForm
				.getIdImovel();

		// Se o c�digo do im�vel tiver sido digitado seta no form os dados do im�vel
		if (codigoDigitadoImovelEnter != null
				&& !codigoDigitadoImovelEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoImovelEnter) > 0) {

			Imovel imovel = fachada.pesquisarImovelRegistroAtendimento(new Integer(codigoDigitadoImovelEnter));
			
			if (imovel != null) {

				// O imovel foi encontrado
				desfazerCancelamentoRetificacaoContaActionForm.setIdImovel(""
						+ imovel.getId());

				desfazerCancelamentoRetificacaoContaActionForm
						.setInscricaoImovel(imovel.getInscricaoFormatada());

				desfazerCancelamentoRetificacaoContaActionForm
						.setSituacaoAgua(imovel.getLigacaoAguaSituacao()
								.getDescricao());

				desfazerCancelamentoRetificacaoContaActionForm
						.setSituacaoEsgoto(imovel.getLigacaoEsgotoSituacao()
								.getDescricao());

				
				Cliente cliente = fachada.pesquisarClienteUsuarioImovel(new Integer(codigoDigitadoImovelEnter));

				// Manda os dados do cliente para a p�gina
				if (cliente != null) {
					desfazerCancelamentoRetificacaoContaActionForm.setNomeClienteUsuario(cliente
							.getNome());
				}
				

				Collection contas = fachada.obterContasImovelManter(imovel, DebitoCreditoSituacao.CANCELADA,
						DebitoCreditoSituacao.CANCELADA, DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO);

				/**
				 * S� sera enviada contas que ta na situa��o retificada ou cancelada
				 * Alterado por Arthur Carvalho
				 * Analista Eduardo Rosa
				 * Data:14/05/2010
				 */
				Iterator iteratorContas = contas.iterator();
				Collection colecaoContas = new ArrayList();
				while ( iteratorContas.hasNext() ) {
					
					Conta conta = (Conta) iteratorContas.next();
					//RETIFICADA
					if ( conta.getDebitoCreditoSituacaoAtual().getId().equals(
							DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO ) ) {
					
						FiltroConta filtroConta = new FiltroConta();
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.REFERENCIA, 
								conta.getReferencia()));
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.IMOVEL_ID, 
								conta.getImovel().getId()));
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL,
								DebitoCreditoSituacao.RETIFICADA ));
						
						Collection colecaoConta = fachada.pesquisar(filtroConta, Conta.class.getName() ) ;
						
						if( colecaoConta != null && !colecaoConta.isEmpty() ) {
							
							colecaoContas.add(conta);
						}
						//CANCELADA
					} else if ( conta.getDebitoCreditoSituacaoAtual().getId().equals(
							DebitoCreditoSituacao.CANCELADA ) ) {
						
						FiltroConta filtroConta = new FiltroConta();
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.REFERENCIA, 
								conta.getReferencia()));
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.IMOVEL_ID, 
								conta.getImovel().getId()));
						filtroConta.adicionarParametro( new ParametroSimples( FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL,
								DebitoCreditoSituacao.CANCELADA ));
						
						Collection colecaoConta = fachada.pesquisar(filtroConta, Conta.class.getName() ) ;
						
						if( colecaoConta != null && !colecaoConta.isEmpty() ) {
							
							colecaoContas.add(conta);
						}
					}
					
				}
				// Manda os dados da conta para a p�gina
				if (colecaoContas != null && !colecaoContas.isEmpty()) {
					sessao.setAttribute("contas", colecaoContas);
				}
				else
				{
					throw new ActionServletException(
							"atencao.pesquisa.nenhuma.conta_cancelada_retificada_imovel", null, ""
									+ codigoDigitadoImovelEnter);
				}
			} else {
				httpServletRequest.setAttribute("corImovel", "exception");
				desfazerCancelamentoRetificacaoContaActionForm
						.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
				desfazerCancelamentoRetificacaoContaActionForm
						.setIdImovel("");
				desfazerCancelamentoRetificacaoContaActionForm
						.setNomeClienteUsuario("");
				desfazerCancelamentoRetificacaoContaActionForm
						.setSituacaoAgua("");
				desfazerCancelamentoRetificacaoContaActionForm
						.setSituacaoEsgoto("");
				sessao.removeAttribute("contas");
			}
		}
		return retorno;
	}
}
