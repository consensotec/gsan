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
 * Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para inserir o Motivo da Exclus�o do Negativador
 * 
 * 
 * @author Yara Taciane de Souza
 * @date 18/12/2007
 */
public class InserirNegativadorExclusaoMotivoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirNegativadorExclusaoMotivoActionForm inserirNegativadorExclusaoMotivoActionForm = (InserirNegativadorExclusaoMotivoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_NEGATIVADOR_EXCLUSAO_MOTIVO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_INSERIR_NEGATIVADOR_EXCLUSAO_MOTIVO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSA��O ----------------
	
		Fachada fachada = Fachada.getInstancia();
		Integer idNegativadorExclusaoMotivo = null;

		// cria o objeto negativador contrato para ser inserido
		NegativadorExclusaoMotivo negativadorExclusaoMotivo = new NegativadorExclusaoMotivo();
		
        //-------------------------------------------------------------------------
		if (inserirNegativadorExclusaoMotivoActionForm.getIdNegativador() != null
				&& !inserirNegativadorExclusaoMotivoActionForm
						.getIdNegativador().equals("")) {

			Negativador negativador = new Negativador();
			negativador.setId(new Integer(
					inserirNegativadorExclusaoMotivoActionForm
							.getIdNegativador()));
			negativadorExclusaoMotivo.setNegativador(negativador);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Negativador");
		}

		// Verificar a exist�ncia de c�digo do motivo
		// -------------------------------------------------------------------------------------

		if (inserirNegativadorExclusaoMotivoActionForm.getCodigoMotivo() != null
				&& !inserirNegativadorExclusaoMotivoActionForm
						.getCodigoMotivo().equals("")) {
			
			String codigoExclusaoMotivo = inserirNegativadorExclusaoMotivoActionForm.getCodigoMotivo();
			

			FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo();

			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorExclusaoMotivo.CODIGO_EXCLUSAO_MOTIVO, codigoExclusaoMotivo));
			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorExclusaoMotivo.NEGATIVADOR_ID, negativadorExclusaoMotivo.getNegativador().getId()));
		
			
			Collection collNegativadorExclusaoMotivo= fachada.pesquisar(filtroNegativadorExclusaoMotivo,
					NegativadorExclusaoMotivo.class.getName());
			
			
			if(collNegativadorExclusaoMotivo != null && !collNegativadorExclusaoMotivo.isEmpty()){
			
				throw new ActionServletException("atencao.codigo_motivo_ja_existe_cadastro");
				
			}
			
			negativadorExclusaoMotivo.setCodigoExclusaoMotivo(Short.parseShort(codigoExclusaoMotivo));
			

		} else {
			throw new ActionServletException("atencao.required", null,"C�digo do Motivo");
		}
		
		//------------------------------------------------------------------------------------
		if (inserirNegativadorExclusaoMotivoActionForm.getDescricaoExclusaoMotivo() != null
				&& !inserirNegativadorExclusaoMotivoActionForm
						.getDescricaoExclusaoMotivo().equals("")) {
			
			String descricaoExlusaoMotivo = inserirNegativadorExclusaoMotivoActionForm.getDescricaoExclusaoMotivo();
			negativadorExclusaoMotivo.setDescricaoExclusaoMotivo(descricaoExlusaoMotivo);			
		
		}else{
			throw new ActionServletException("atencao.required", null,"Descri��o do Motivo Exclus�o");
		}

		//----------------------------------------------------------------------------------------

		if (inserirNegativadorExclusaoMotivoActionForm.getIdCobrancaDebitoSituacao() != null
				&& !inserirNegativadorExclusaoMotivoActionForm
						.getIdCobrancaDebitoSituacao().equals("")) {

			CobrancaDebitoSituacao cobrancaDebitoSituacao = new CobrancaDebitoSituacao();
			cobrancaDebitoSituacao.setId(new Integer(
					inserirNegativadorExclusaoMotivoActionForm.getIdCobrancaDebitoSituacao()));
			negativadorExclusaoMotivo.setCobrancaDebitoSituacao(cobrancaDebitoSituacao);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Cobran�a D�bito Situa��o");
		}

		
		short indicadorUso = 1;		
		negativadorExclusaoMotivo.setIndicadorUso(indicadorUso);
		
		
		if (negativadorExclusaoMotivo != null) {

			negativadorExclusaoMotivo.setUltimaAlteracao(new Date());

			 idNegativadorExclusaoMotivo = (Integer)
			 fachada.inserir(negativadorExclusaoMotivo);
		} else {
			throw new ActionServletException(
					"atencao.informar.linha.criterio.cobranca");
		}

		
		// ------------ REGISTRAR TRANSA��O ----------------
		negativadorExclusaoMotivo.setOperacaoEfetuada(operacaoEfetuada);
		negativadorExclusaoMotivo.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorExclusaoMotivo);
		// ------------ REGISTRAR TRANSA��O ----------------
		

		montarPaginaSucesso(httpServletRequest,
				"Motivo da Exclus�o do Negativador"
						+ idNegativadorExclusaoMotivo
						+ " inserido com sucesso.",
				"Inserir outro Motivo da Exclus�o do Negativador",
				"exibirInserirNegativadorExclusaoMotivoAction.do?menu=sim","exibirAtualizarNegativadorExclusaoMotivoAction.do?idRegistroAtualizacao="
				+ idNegativadorExclusaoMotivo, "Atualizar Motivo da Exclus�o do Negativador Inserido");
		
		

		return retorno;
	}

}
