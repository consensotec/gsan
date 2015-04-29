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
package gsan.gui.faturamento.conta;


import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteConta;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.interceptor.RegistradorOperacao;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * UC1576-AssociarContasNovoClienteOuRemoverClienteConta
 * @author Arthur Carvalho
 * @date 08/05/08
 */

public class AtualizarContaNovoClienteAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarContaNovoClienteActionForm form = (AtualizarContaNovoClienteActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String operacao = httpServletRequest.getParameter("operacao");
		if ( operacao != null && !operacao.equals("") ) {
		
			String[] arrayIdContaSelecionada = form.getIdRegistros();
			for (int i = 0; i < arrayIdContaSelecionada.length; i++) {
				
				Integer idContaSelecionada = Integer.valueOf(arrayIdContaSelecionada[i]);
				//[SB0004] - Remover Cliente da Conta 
				if ( operacao.equals("removerClienteConta") ) {
						
					this.removerClienteResponsavel(idContaSelecionada, usuarioLogado);
				
				} else if ( operacao.equals("associarClienteResponsavelImovel") ) {
					
					this.associarContaClienteResponsavelImovel(idContaSelecionada, usuarioLogado);
					
				} else if ( operacao.equals("associarClienteResponsavelInformado") ) {
					
					//valida o cliente associado informado
					validarCliente(form);
					
					this.associarContaClienteResponsavelInformado(idContaSelecionada, Integer.valueOf(form.getIdClienteAssociado()), usuarioLogado);
				}
			}
		}
		
		montarPaginaSucesso(httpServletRequest, "Opera��o efetuada com sucesso.",
				"Realizar outra Manuten��o ",
				"exibirAtualizarContaNovoClienteAction.do?menu=sim");
		
		return retorno;
	}
	
	/**
	 * [SB0004] - Remover Cliente da Conta 
	 */
	private void removerClienteResponsavel( Integer idContaSelecionada , Usuario usuarioLogado) {

		ClienteConta clienteConta = Fachada.getInstancia().pesquisarClienteConta(idContaSelecionada, ClienteRelacaoTipo.RESPONSAVEL.intValue());
		
		if ( clienteConta != null ) { 
			
			
			if ( clienteConta.getIndicadorNomeConta() != null && clienteConta.getIndicadorNomeConta().equals(ConstantesSistema.SIM) ) {
				//atualiza o cliente usuario para indicador nome conta ativo.
				ClienteConta clienteContaUsuario = Fachada.getInstancia().pesquisarClienteConta(idContaSelecionada, ClienteRelacaoTipo.USUARIO.intValue());
				clienteContaUsuario.setIndicadorNomeConta(ConstantesSistema.SIM);
				Fachada.getInstancia().atualizar(clienteContaUsuario);
				
				// ------------ REGISTRAR TRANSA��O ----------------
				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_ASSOCIAR_CONTA_NOVO_CLIENTE_RESPONSAVEL, clienteConta.getCliente().getId(), clienteConta.getCliente().getId(), 
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
				// ------------ REGISTRAR TRANSA��O ----------------
				
				registradorOperacao.registrarOperacao(clienteConta);
				//remove o cliente conta do cliente responsavel
				Fachada.getInstancia().remover(clienteConta);
			} else {
				
				// ------------ REGISTRAR TRANSA��O ----------------
				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_ASSOCIAR_CONTA_NOVO_CLIENTE_RESPONSAVEL, clienteConta.getCliente().getId(), clienteConta.getCliente().getId(), 
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
				// ------------ REGISTRAR TRANSA��O ----------------
				
				registradorOperacao.registrarOperacao(clienteConta);
				Fachada.getInstancia().remover(clienteConta);
			}
		}
	}
	
	/**
	 * [SB0005] - Associar Conta ao Cliente Respons�vel do Im�vel
	 */
	private void associarContaClienteResponsavelImovel(Integer idContaSelecionada , Usuario usuarioLogado) {
		
		ClienteConta clienteConta = Fachada.getInstancia().pesquisarClienteConta(idContaSelecionada, ClienteRelacaoTipo.RESPONSAVEL.intValue());
		
		Integer idCliente = Fachada.getInstancia().obterIdClienteResponsavelImovel(idContaSelecionada);
		
		//verifica se o imovel possui cliente responsavel.
		if ( idCliente != null ) {
		
			if ( clienteConta != null ) {
			
				Cliente cliente = new Cliente();
				cliente.setId(idCliente);
				clienteConta.setCliente(cliente);
				clienteConta.setUltimaAlteracao( new Date() );
				
				// ------------ REGISTRAR TRANSA��O ----------------
				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_ASSOCIAR_CONTA_NOVO_CLIENTE_RESPONSAVEL, clienteConta.getCliente().getId(), clienteConta.getCliente().getId(), 
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
				// ------------ REGISTRAR TRANSA��O ----------------
				
				registradorOperacao.registrarOperacao(clienteConta);
				
				Fachada.getInstancia().atualizar(clienteConta);
			} 

		} else {
			this.removerClienteResponsavel(idContaSelecionada, usuarioLogado);	
		}
	}
	
	/**
	 * [SB0006] - Associar Conta ao Cliente Respons�vel Informado
	 */
	private void associarContaClienteResponsavelInformado(Integer idContaSelecionada, Integer idClienteInformado , Usuario usuarioLogado) {

		ClienteConta clienteConta = Fachada.getInstancia().pesquisarClienteConta(idContaSelecionada, ClienteRelacaoTipo.RESPONSAVEL.intValue());
		
		if ( clienteConta != null ) {
			
			Cliente cliente = new Cliente();
			cliente.setId(idClienteInformado);
			clienteConta.setCliente(cliente);
			clienteConta.setUltimaAlteracao( new Date() );
			// ------------ REGISTRAR TRANSA��O ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ASSOCIAR_CONTA_NOVO_CLIENTE_RESPONSAVEL, clienteConta.getCliente().getId(), clienteConta.getCliente().getId(), 
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// ------------ REGISTRAR TRANSA��O ----------------
			
			registradorOperacao.registrarOperacao(clienteConta);
			
			Fachada.getInstancia().atualizar(clienteConta);
			
		} 
	}
	
	private void validarCliente(AtualizarContaNovoClienteActionForm form) {

		if ( form.getIdClienteAssociado() != null && !form.getIdClienteAssociado().equals("")) {
			
			if ( Util.verificaSeNumeroNatural(form.getIdClienteAssociado()) ) {
				
				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro( new ParametroSimples(FiltroCliente.ID, Integer.valueOf(form.getIdClienteAssociado())));
				
				Collection<Cliente> colecaoCliente = Fachada.getInstancia().pesquisar(filtroCliente, Cliente.class.getName());
				if ( colecaoCliente == null || colecaoCliente.isEmpty() ) {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Novo Cliente Respons�vel Associado �s Contas");
				} 
			} else {
				throw new ActionServletException("atencao.campo_texto.numero_obrigatorio",null, "Novo Cliente Respons�vel Associado �s Contas");
			}
		}
	}
}
