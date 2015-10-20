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
package gcom.gui.cobranca;

import gcom.batch.FiltroProcesso;
import gcom.batch.Processo;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
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
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class InserirAtividadeCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {


		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirAtividadeCobrancaActionForm inserirAtividadeCobrancaActionForm = (InserirAtividadeCobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		//Recuperando os dados do formul�rio
		String descricaoAtividadeCobranca = inserirAtividadeCobrancaActionForm.getDescricaoAtividadeCobranca();
		String atividadePredecessora = inserirAtividadeCobrancaActionForm.getIdAtividadePredecessora();
		String ordemCronograma = inserirAtividadeCobrancaActionForm.getOrdemCronograma();
		String processoAssociado = inserirAtividadeCobrancaActionForm.getIdProcessoAssociado();
		String opcaoCompoeCronograma = inserirAtividadeCobrancaActionForm.getOpcaoCompoeCronograda();
		String opcaoAtividadeObrigatoria = inserirAtividadeCobrancaActionForm.getOpcaoAtividadeObrigatoria();
		String opcaoComandada = inserirAtividadeCobrancaActionForm.getOpcaoPodeSerComandada();
		String opcaoExecutada = inserirAtividadeCobrancaActionForm.getOpcaoPodeSerExecutada();
		String quantidadeDiasExecucao = inserirAtividadeCobrancaActionForm.getQuantidadeDiasExecucao();
		String cobrancaAcao = inserirAtividadeCobrancaActionForm.getCobrancaAcao();
		
		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		
		/*
		 * [UC0107] Registrar Transa��o
		 * 
		 */

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_ATIVIDADE_COBRANCA,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_INSERIR_ATIVIDADE_COBRANCA);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		// [UC0107] -Fim- Registrar Transa��o
		
		//FS0002 - Numero de ordem j� utilizado
		filtroCobrancaAtividade.adicionarParametro(
				new ParametroSimples(FiltroCobrancaAtividade.ORDEM_REALIZACAO, ordemCronograma));
		
		Collection colecaoCobrancaAtividade = fachada.pesquisar(
				filtroCobrancaAtividade, CobrancaAtividade.class.getName());
		
		if (colecaoCobrancaAtividade != null && !colecaoCobrancaAtividade.isEmpty()){
			throw new ActionServletException("atencao.numero_ordem_informado");
		}
		//FS0002 - Fim do Bloco
		
		filtroCobrancaAtividade.limparListaParametros();
		CobrancaAtividade cobrancaAtividadePredecessora = new CobrancaAtividade();
		// FS0003 - Numero de ordem menor que o da predecessora
		if (atividadePredecessora != null && !atividadePredecessora.equalsIgnoreCase("")){
			filtroCobrancaAtividade.adicionarParametro(
					new ParametroSimples(FiltroCobrancaAtividade.ID, atividadePredecessora));
			cobrancaAtividadePredecessora = (CobrancaAtividade) fachada.pesquisar(
					filtroCobrancaAtividade, CobrancaAtividade.class.getName()).iterator().next();
			
			int ordemRealizacao = new Integer(ordemCronograma);
			int ordemPredecessora = cobrancaAtividadePredecessora.getOrdemRealizacao();
			
			if (ordemRealizacao < ordemPredecessora){
				throw new ActionServletException("atencao.predecessota_menor_atual");
			}
		}
		//FS0003 - Fim do Bloco
		
		//FS0005 - Atividade Obrigat�ria deve compor Cronograma
		if (opcaoCompoeCronograma.equals(ConstantesSistema.NAO.toString())){
			if (opcaoAtividadeObrigatoria.equals(ConstantesSistema.SIM.toString())){
				throw new ActionServletException("atencao.atividade_obrigatoria_compoe_cronograma");
			}
		}
		//FS0005 - Fim do Bloco
		
		//Montando o Objeto a ser Inserido
		CobrancaAtividade cobrancaAtividade = new CobrancaAtividade();
		if (descricaoAtividadeCobranca != null && !descricaoAtividadeCobranca.equalsIgnoreCase("")){
			cobrancaAtividade.setDescricaoCobrancaAtividade(descricaoAtividadeCobranca);
		}
		
		if (atividadePredecessora != null && !atividadePredecessora.equalsIgnoreCase("")){
			cobrancaAtividade.setCobrancaAtividadePredecessora(cobrancaAtividadePredecessora);	
		}
		
		if (ordemCronograma != null && !ordemCronograma.equalsIgnoreCase("")){
			cobrancaAtividade.setOrdemRealizacao(new Short(ordemCronograma));
		}
		
		if (processoAssociado != null && !processoAssociado.equalsIgnoreCase("")){
			FiltroProcesso filtroProcesso = new FiltroProcesso();
			filtroProcesso.adicionarParametro(new ParametroSimples(FiltroProcesso.ID, processoAssociado));
			
			Collection processoNaBase = fachada.pesquisar(filtroProcesso, Processo.class.getName());
			
			if (processoNaBase != null && !processoNaBase.isEmpty()) {
				Processo processo = new Processo();
				processo.setId(new Integer(processoAssociado));
				cobrancaAtividade.setProcesso(processo);
			} else {
				
			}
	
		}
		
		if (opcaoCompoeCronograma != null && !opcaoCompoeCronograma.equalsIgnoreCase("")){
			cobrancaAtividade.setIndicadorCronograma(new Short(opcaoCompoeCronograma));
		}
		
		if (opcaoAtividadeObrigatoria != null && !opcaoAtividadeObrigatoria.equalsIgnoreCase("")){
			cobrancaAtividade.setIndicadorObrigatoriedade(new Short(opcaoAtividadeObrigatoria));
		}
		
		if (opcaoComandada != null && !opcaoComandada.equalsIgnoreCase("")){
			cobrancaAtividade.setIndicadorComando(new Short(opcaoComandada));
		}
		
		if (opcaoExecutada != null && !opcaoExecutada.equalsIgnoreCase("")){
			cobrancaAtividade.setIndicadorExecucao(new Short(opcaoExecutada));
		}
		
		if (quantidadeDiasExecucao != null && !quantidadeDiasExecucao.equalsIgnoreCase("")){
			cobrancaAtividade.setNumeroDiasExecucao(new Integer(quantidadeDiasExecucao));
		}
		
		if (cobrancaAcao != null && !cobrancaAcao.equalsIgnoreCase("")){
			FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
			filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID, cobrancaAcao));
			
			Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
			
			if (colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()) {
				CobrancaAcao acaoCobranca = new CobrancaAcao();
				acaoCobranca.setId(new Integer(cobrancaAcao));
				cobrancaAtividade.setCobrancaAcao(acaoCobranca);
			}
		}
		
		cobrancaAtividade.setIndicadorUso(new Short("1"));
		
		cobrancaAtividade.setUltimaAlteracao(new Date());
		
		// Regitrando operacao
		cobrancaAtividade.setOperacaoEfetuada(operacaoEfetuada);
		cobrancaAtividade.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(cobrancaAtividade);
		
		//FS0007 - Inserindo na base
		Integer id = (Integer) fachada.inserir(cobrancaAtividade);
			
		montarPaginaSucesso(httpServletRequest, "Atividade de Cobranca "+ id + " inserida com sucesso.",
				"Inserir outra Atividade de Cobranca",
				"exibirInserirAtividadeCobrancaAction.do?menu=sim",
				"exibirAtualizarAtividadeCobrancaAction.do?idRegistroAtualizar=" + id,
				"Atualizar Atividade de Cobranca Inserida");
		
		
		return retorno;
	}
}