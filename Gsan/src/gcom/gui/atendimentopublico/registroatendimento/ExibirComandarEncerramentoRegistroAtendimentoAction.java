/**
 * 
 */
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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que faz a exibi��o da tela para o usu�rio setar os campos e permitir
 * que ele comande o encerramento dos registros de atendimentos 
 * [UC0735] Comandar Encerramento de Registros de Atendimento
 * 
 * @author Rafael Corr�a, Pedro Alexandre
 * @since 25/01/2008, 10/06/2008
 */
public class ExibirComandarEncerramentoRegistroAtendimentoAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirComandarEncerramentoRA");
		
		Fachada fachada = Fachada.getInstancia();
		
		ComandarEncerramentoRegistroAtendimentoActionForm form = (ComandarEncerramentoRegistroAtendimentoActionForm)actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Date dataAtual = new Date();
		if(form.getDataAtendimentoFinal() == null || form.getDataAtendimentoFinal().trim().equals("")){
			form.setDataAtendimentoFinal(Util.formatarData(dataAtual));
		}
		httpServletRequest.setAttribute("dataAtual",Util.formatarData(dataAtual));
		
		//-------[FS0001 - VERIFICAR EXISTENCIA DE DADOS]  ----  Motivo do Encerramento
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroAtendimentoMotivoEncerramento.INDICADOR_DUPLICIDADE, AtendimentoMotivoEncerramento.INDICADOR_DUPLICIDADE_ATIVO));
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroAtendimentoMotivoEncerramento.INDICADOR_EXECUCAO, AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM));
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<AtendimentoMotivoEncerramento> colecaoMotivoEncerramento = fachada.pesquisar(filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());

		if (colecaoMotivoEncerramento == null || colecaoMotivoEncerramento.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Motivo de Encerramento");
		}
		
		sessao.setAttribute("colecaoMotivoEncerramento", colecaoMotivoEncerramento);
		
		//-------[FS0001 - VERIFICAR EXISTENCIA DE DADOS]  ----  Motivo do Encerramento
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		filtroSolicitacaoTipoEspecificacao.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_ENCERRAMENTO_AUTOMATICO, SolicitacaoTipoEspecificacao.INDICADOR_SEM_ENCERRAMENTO_AUTOMATICO));
		
		Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = fachada
				.pesquisar(filtroSolicitacaoTipoEspecificacao,
						SolicitacaoTipoEspecificacao.class.getName());

		if (colecaoSolicitacaoTipoEspecificacao == null || colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Especifica��o do Tipo da Solicita��o");
		}
		
		sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao", colecaoSolicitacaoTipoEspecificacao);
		
		String idUsuario = form.getIdUsuario();
		
		if (idUsuario != null && !idUsuario.trim().equals("")) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idUsuario));
			
			Collection<Usuario> colecaoUsuarios = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuarios != null && !colecaoUsuarios.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuarios);
				form.setIdUsuario(usuario.getId().toString());
				form.setNomeUsuario(usuario.getNomeUsuario());
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeAtendimento");
				
			} else {
				form.setIdUsuario("");
				form.setNomeUsuario("USUARIO INEXISTENTE");
				httpServletRequest.setAttribute("usuarioInexistente",true);
				httpServletRequest.setAttribute("nomeCampo", "idUsuario");
			}
		}
		
		String idUnidadeAtendimento = form.getIdUnidadeAtendimento();
		
		if (idUnidadeAtendimento != null && !idUnidadeAtendimento.trim().equals("")) {
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeAtendimento));
			
			Collection<UnidadeOrganizacional> colecaoUnidadesAtendimento = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadesAtendimento != null && !colecaoUnidadesAtendimento.isEmpty()) {
				UnidadeOrganizacional unidadeAtendimento = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadesAtendimento);
				form.setIdUnidadeAtendimento(unidadeAtendimento.getId().toString());
				form.setNomeUnidadeAtendimento(unidadeAtendimento.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeAtual");
				
			} else {
				form.setIdUnidadeAtendimento("");
				form.setNomeUnidadeAtendimento("UNIDADE INEXISTENTE");
				httpServletRequest.setAttribute("unidadeAtendimentoInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeAtendimento");
			}
		}
		
		String idUnidadeAtual = form.getIdUnidadeAtual();
		
		if (idUnidadeAtual != null && !idUnidadeAtual.trim().equals("")) {
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeAtual));
			
			Collection<UnidadeOrganizacional> colecaoUnidadesAtual = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadesAtual != null && !colecaoUnidadesAtual.isEmpty()) {
				UnidadeOrganizacional unidadeAtual = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadesAtual);
				form.setIdUnidadeAtual(unidadeAtual.getId().toString());
				form.setNomeUnidadeAtual(unidadeAtual.getDescricao());
			} else {
				form.setIdUnidadeAtual("");
				form.setNomeUnidadeAtual("UNIDADE INEXISTENTE");
				httpServletRequest.setAttribute("unidadeAtualInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeAtual");
			}
		}
		
		String idUnidadeSuperior = form.getIdUnidadeSuperior();
		
		if (idUnidadeSuperior != null && !idUnidadeSuperior.trim().equals("")) {
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeSuperior));
			
			Collection<UnidadeOrganizacional> colecaoUnidadeSuperior = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadeSuperior != null && !colecaoUnidadeSuperior.isEmpty()) {
				UnidadeOrganizacional unidadeSuperior = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeSuperior);
				form.setIdUnidadeSuperior(unidadeSuperior.getId().toString());
				form.setNomeUnidadeSuperior(unidadeSuperior.getDescricao());
			} else {
				form.setIdUnidadeSuperior("");
				form.setNomeUnidadeSuperior("UNIDADE SUPERIOR INEXISTENTE");
				httpServletRequest.setAttribute("unidadeSuperiorInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeSuperior");
			}
		}

		return retorno;

	}
	

}
