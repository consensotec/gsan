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
package gcom.interceptor;

import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

/**
 * 
 *  
 *
 * @author Thiago Toscano
 * @date 29/04/2006
 */
public class RegistradorOperacao {

	/** operacao realizada pelo usuario */
	private OperacaoEfetuada operacaoEfetuada = null;

	private UsuarioAcaoUsuarioHelper[] usuarioAcaoUsuarioHelper;

	/** 
	 * que recebe o id da op
	 * Construtor de RegistradorOperacao 
	 * 
	 * @param idOperacao
	 * @param usuarioAcaoUsuarioHelper
	 */
	public RegistradorOperacao(Integer idOperacao, UsuarioAcaoUsuarioHelper... usuarioAcaoUsuarioHelper) {
		Operacao operacao = new Operacao();
		operacao.setId(idOperacao);

		operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		this.usuarioAcaoUsuarioHelper = usuarioAcaoUsuarioHelper; 
	}
	
	public RegistradorOperacao(Integer idOperacao, Integer valorArgumento,
			Integer idObjetoPrincipal,
			UsuarioAcaoUsuarioHelper... usuarioAcaoUsuarioHelper) {
		Operacao operacao = new Operacao();
		operacao.setId(idOperacao);

		operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		operacaoEfetuada.setArgumentoValor(valorArgumento);
		operacaoEfetuada.setIdObjetoPrincipal(idObjetoPrincipal);

		this.usuarioAcaoUsuarioHelper = usuarioAcaoUsuarioHelper; 
	}

	/**
	 * M�todo que registra os parametros a cada objeto a ser registrado 
	 *
	 * @author Administrador
	 * @date 29/04/2006
	 *
	 * @param objetoTransacao
	 */
	public void registrarOperacao(ObjetoTransacao objetoTransacao) {
		// seta a operacao efetuada
		objetoTransacao.setOperacaoEfetuada(this.operacaoEfetuada);
			
		// pra cada usuario com sua acao seta no objeto que deseja registra a operacao
		if (usuarioAcaoUsuarioHelper != null) {
			for (int i = 0; i < usuarioAcaoUsuarioHelper.length; i++) {
				UsuarioAcaoUsuarioHelper uAUH = usuarioAcaoUsuarioHelper[i];
				if (uAUH.getUsuario() != null){
					objetoTransacao.adicionarUsuario(uAUH.getUsuario(),uAUH.getUsuarioAcao());
				}
			}
		}
	}
}