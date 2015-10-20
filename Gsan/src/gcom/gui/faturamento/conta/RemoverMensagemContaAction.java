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

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMensagem;
import gcom.faturamento.conta.FiltroContaMensagem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverMensagemContaAction extends GcomAction {

	/**
	 * Autor Tiago Moreno Excluir Tarifa de consumo
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		// Obt�m os ids de remo��o
		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		HttpSession sessao = httpServletRequest.getSession(false);	
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Integer contadorQtMensagens = new Integer(ids.length);

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		//------------ REGISTRAR TRANSA��O ----------------
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_CONTA_MENSAGEM_REMOVER);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSA��O ----------------
		
		// mensagem de erro quando o usu�rio tenta excluir sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}

		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];

			FiltroContaMensagem filtroContaMensagem = new FiltroContaMensagem();
			filtroContaMensagem.adicionarParametro(new ParametroSimples(
					FiltroContaMensagem.ID, id));
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
			filtroContaMensagem
					.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");

			Collection colecaoContaMensagem = fachada.pesquisar(
					filtroContaMensagem, ContaMensagem.class.getName());
			if (colecaoContaMensagem != null && !colecaoContaMensagem.isEmpty()) {
				ContaMensagem contaMensagem = (ContaMensagem) colecaoContaMensagem.iterator().next();
				
				FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
				filtroSistemaParametro.adicionarParametro(new MaiorQue(FiltroSistemaParametro.ANO_MES_REFERECIA_ARRECADACAO, contaMensagem.getAnoMesRreferenciaFaturamento()));
				
				Collection sistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
				
				if (sistemaParametro != null && !sistemaParametro.isEmpty()){

					//String anoMes = Util.formatarAnoMesParaMesAno(((SistemaParametro) sistemaParametro.iterator().next()).getAnoMesFaturamento());
					
					FiltroSistemaParametro filtroSistemaParametro2 = new FiltroSistemaParametro();
					Collection colecaoFSP = fachada.pesquisar(filtroSistemaParametro2, SistemaParametro.class.getName());
					
					if (colecaoFSP != null && !colecaoFSP.isEmpty()){
						SistemaParametro sistemaParametro2 = (SistemaParametro) colecaoFSP.iterator().next();
					
						/*String mesAno =*/ Util.formatarAnoMesParaMesAno(sistemaParametro2.getAnoMesFaturamento());
						
						throw new ActionServletException(
							 	"atencao.mensagem_nao_inserida");
					}
				}
			}
		}

		//------------ REGISTRAR TRANSA��O ----------------
    	UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    	Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
    	colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
        //------------ REGISTRAR TRANSA��O ----------------
    	
		fachada.remover(ids, ContaMensagem.class.getName(), operacaoEfetuada, colecaoUsuarios);

		// Monta a p�gina de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest, contadorQtMensagens
					.toString()
					+ " Mensagem(ns) da Conta removida(s) com sucesso",
					"Realizar outra Manuten��o de Mensagem da Conta",
					"exibirFiltrarMensagemContaAction.do?menu=sim");
		}

		return retorno;
	}
}