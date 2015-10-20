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


import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.conta.ContaMensagem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirMensagemContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {


		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirMensagemContaActionForm inserirMensagemContaActionForm = (InserirMensagemContaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		//HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio
		String referenciaFaturamento = inserirMensagemContaActionForm.getReferenciaFaturamento();
		String mensagemConta01 = inserirMensagemContaActionForm.getMensagemConta01();
		String mensagemConta02 = inserirMensagemContaActionForm.getMensagemConta02();
		String mensagemConta03 = inserirMensagemContaActionForm.getMensagemConta03();
		String grupoFaturamento = inserirMensagemContaActionForm.getGrupoFaturamento();
		String gerenciaRegional = inserirMensagemContaActionForm.getGerenciaRegional();
		String localidade = inserirMensagemContaActionForm.getLocalidade();
		String[] setorComercial = inserirMensagemContaActionForm.getSetorComercial();
		String[] quadra = inserirMensagemContaActionForm.getQuadra();
		
		ContaMensagem contaMensagem = new ContaMensagem();
		
		HttpSession sessao = httpServletRequest.getSession(false);				
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		/*
		 * [UC0107] Registrar Transa��o
		 * 
		 */

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_CONTA_MENSAGEM_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_CONTA_MENSAGEM_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		// [UC0107] -Fim- Registrar Transa��o
		
		if (Util.validarAnoMes(referenciaFaturamento)){
			throw new ActionServletException(
				"atencao.ano_mes_invalido");
		}else{
			Integer mes = new Integer (referenciaFaturamento.substring(0, 2));
			Integer ano = new Integer (referenciaFaturamento.substring(3, 7));
			
			if (mes <= 0 || mes > 12){
				throw new ActionServletException(
					"atencao.ano_mes_invalido");
			}
			
			if (ano < 1900){
				throw new ActionServletException(
					"atencao.ano_mes_invalido");
			}
			
			if (referenciaFaturamento != null && !referenciaFaturamento.equalsIgnoreCase("")){
				Integer referenciaFaturametoTratado = new Integer(Util.formatarMesAnoParaAnoMesSemBarra(referenciaFaturamento));
				
					contaMensagem.setAnoMesRreferenciaFaturamento(referenciaFaturametoTratado);
			}
		}
		
		if (referenciaFaturamento != null && !referenciaFaturamento.trim().equals("")) {
			if (Util.validarAnoMes(referenciaFaturamento)) {
				new ActionServletException(
						"atencao.ano_mes_invalido",
						null, referenciaFaturamento);
			}
		}
		
		if (mensagemConta01 != null && !mensagemConta01.equalsIgnoreCase("")){
			contaMensagem.setDescricaoContaMensagem01(mensagemConta01);
		}
		if (mensagemConta02 != null && !mensagemConta02.equalsIgnoreCase("")){
			contaMensagem.setDescricaoContaMensagem02(mensagemConta02);
		}
		if (mensagemConta03 != null && !mensagemConta03.equalsIgnoreCase("")){
			contaMensagem.setDescricaoContaMensagem03(mensagemConta03);
		}
		
		if (grupoFaturamento != null && !grupoFaturamento.equalsIgnoreCase("")){
			FaturamentoGrupo fg = new FaturamentoGrupo();
			fg.setId(new Integer(grupoFaturamento));
			contaMensagem.setFaturamentoGrupo(fg);
		}
		
		if (gerenciaRegional != null && !gerenciaRegional.equalsIgnoreCase("")){
			GerenciaRegional gr = new GerenciaRegional();
			gr.setId(new Integer(gerenciaRegional));
			contaMensagem.setGerenciaRegional(gr);
		}
		
		if (localidade != null && !localidade.equalsIgnoreCase("")){
			Localidade loc = new Localidade();
			loc.setId(new Integer(localidade));
			contaMensagem.setLocalidade(loc);
		}
		
//		if (setorComercial != null && !setorComercial.equalsIgnoreCase("")){
//			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
//			
//			filtroSetorComercial.adicionarParametro(
//					new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidade));
//			filtroSetorComercial.adicionarParametro(
//					new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercial));
//			filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");
//			Collection colecaoSC =  fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
//			SetorComercial sc = (SetorComercial) colecaoSC.iterator().next();
//			
//			/*SetorComercial sc = new SetorComercial();
//			sc.setId(new Integer(setorComercial));*/
//			contaMensagem.setSetorComercial(sc);
//			
//		}
		
		contaMensagem.setUltimaAlteracao(new java.util.Date());
		
//		 regitrando operacao
		contaMensagem.setOperacaoEfetuada(operacaoEfetuada);
		contaMensagem.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(contaMensagem);

//		Integer i = 0;
		
//		if (contaMensagem.getSetorComercial() == null){
//			fachada.inserirMensagemConta(contaMensagem, null);
//		} else {
			fachada.inserirMensagemConta(contaMensagem, setorComercial, quadra);
//	    }
		
		//Integer i = (Integer) fachada.inserirMensagemConta(contaMensagem, contaMensagem.getSetorComercial().getId());
		
		montarPaginaSucesso(httpServletRequest, "Mensagem da Conta com refer�ncia " + referenciaFaturamento +" inclu�da com sucesso.",
				"Inserir outra Mensagem de Conta",
				"exibirInserirMensagemContaAction.do?menu=sim" );
		
		
//		montarPaginaSucesso(httpServletRequest, "Mensagem da Conta com refer�ncia " + referenciaFaturamento +" inclu�da com sucesso.",
//				"Inserir outra Mensagem de Conta",
//				"exibirInserirMensagemContaAction.do?menu=sim",
//				"exibirAtualizarMensagemContaAction.do?idMensagemConta="+ i,
//				"Atualizar Mensagem da Conta Inserida");
		
		
		return retorno;
	}
}