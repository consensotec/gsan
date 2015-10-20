/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.WizardAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC0651] Inserir Comando de Negativação<br>
 * [SB0010] Comandar Negativação Por Guia de Pagamento<br>
 * 
 * Esta classe tem por finalidade gerar as abas que serão responsáveis pelo processo de inserção de um
 * Comando de Negativação por Guia de Pagamento
 *
 * @author André Miranda
 * @date 16/03/2015
 */
public class InserirComandoNegativacaoPorGuiaPagamentoWizardAction extends WizardAction {
	/*
	 * ABA Nº 01 - DADOS GERAIS
	 */
	public ActionForward exibirInserirComandoNegativacaoPorGuiaPagamentoDadosGeraisAction(ActionMapping actionMapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return new ExibirInserirComandoNegativacaoPorGuiaPagamentoDadosGeraisAction().execute(actionMapping, form,
				request, response);
	}

	public ActionForward inserirComandoNegativacaoPorGuiaPagamentoDadosGeraisAction(ActionMapping actionMapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		validarDadosGerais(form);
		return this.redirecionadorWizard(actionMapping, form, request, response);
	}

	/*
	 * ABA Nº 02 - DADOS CLIENTE
	 */
	public ActionForward exibirInserirComandoNegativacaoPorGuiaPagamentoDadosClienteAction(ActionMapping actionMapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return new ExibirInserirComandoNegativacaoPorGuiaPagamentoDadosClienteAction().execute(actionMapping, form,
				request, response);
	}

	public ActionForward inserirComandoNegativacaoPorGuiaPagamentoDadosClienteAction(ActionMapping actionMapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		validarDadosCliente(form);
		return this.redirecionadorWizard(actionMapping, form, request, response);
	}

	/*
	 * CONCLUIR
	 */
	public ActionForward concluirInserirComandoNegativacaoPorGuiaPagamentoAction(ActionMapping actionMapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return new ConcluirInserirComandoNegativacaoPorGuiaPagamentoAction().execute(actionMapping, form, request, response);
	}

	/*
	 * CANCELAR
	 */	 
	public ActionForward cancelarInserirComandoNegativacaoPorGuiaPagamentoAction(ActionMapping actionMapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return new CancelarInserirComandoNegativacaoAction().execute(actionMapping, form, request, response);
	}

	public void validarDadosGerais(ActionForm actionForm) {
		Fachada fachada = Fachada.getInstancia();

		InserirComandoNegativacaoActionForm form = (InserirComandoNegativacaoActionForm) actionForm;

		// [FS0026] Verificar existência de comando para o negativador na data
		boolean existeComando = fachada.verificarExistenciaComandoNegativador(form.getIdNegativador(),
				Util.converteStringParaDate(form.getDataPrevista()));

		if (existeComando) {
			throw new ActionServletException(
					"atencao.existe_comando_negativado_data",
					"",
					new Exception(), form.getDataPrevista(), form.getNomeNegativador());
		}

		// Verificar existência Usuário
		String idUsuario = form.getUsuario();
		if (Util.verificarNaoVazio(idUsuario)) {
			FiltroUsuario filtro = new FiltroUsuario();
			filtro.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idUsuario));
			Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtro, Usuario.class.getName());

			if (Util.isVazioOrNulo(colecaoUsuario)) {
				throw new ActionServletException("atencao.pesquisa.usuario.inexistente");
			}

			Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
			form.setUsuario(usuario.getId().toString());
			form.setNomeUsuario(usuario.getNomeUsuario());
		}
	}

	private void validarDadosCliente(ActionForm actionForm) {
        Fachada fachada = Fachada.getInstancia();
        InserirComandoNegativacaoActionForm form = (InserirComandoNegativacaoActionForm) actionForm;

		// Pesquisa Cliente
		String idCliente = form.getIdCliente();
		if (Util.verificarIdNaoVazio(idCliente)) {
			FiltroCliente filtro = new FiltroCliente();
			filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
			Collection<Cliente> colecaoCliente = fachada.pesquisar(filtro, Cliente.class.getName());

			if (Util.isVazioOrNulo(colecaoCliente)) {
				throw new ActionServletException("atencao.cliente.inexistente");
			}

			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
			form.setIdCliente(cliente.getId().toString());
			form.setDescricaoCliente(cliente.getNome());
        }
	}
}
