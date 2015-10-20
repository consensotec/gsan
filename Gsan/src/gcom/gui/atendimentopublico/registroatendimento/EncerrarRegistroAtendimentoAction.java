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

import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
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
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0435] Encerrar Registro Atendimento
 * 
 * @author Leonardo Regis
 * @created 24 de Julho de 2006
 */
public class EncerrarRegistroAtendimentoAction extends GcomAction {

	// Fachada
	Fachada fachada = Fachada.getInstancia();
	
	/**
	 * [UC0435] Encerrar Registro Atendimento
	 * 
	 * [UC0107] Registrar Transa��o
	 * 
	 * @author Leonardo Regis
	 * @date   18/08/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta Retorno (Forward = Sucesso)
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Form
		EncerrarRegistroAtendimentoActionForm encerrarRegistroAtendimentoActionForm = (EncerrarRegistroAtendimentoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        
		// [UC0107] Registrar Transa��o
		RegistradorOperacao registradorOperacao = 
			new RegistradorOperacao(
				Operacao.OPERACAO_REGISTRO_ATENDIMENTO_ENCERRAR,
				new UsuarioAcaoUsuarioHelper(usuario,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_REGISTRO_ATENDIMENTO_ENCERRAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		
		// Tramite
		RegistroAtendimento registroAtendimento = null;
		RegistroAtendimentoUnidade registroAtendimentoUnidade = null;

		if (new Integer(encerrarRegistroAtendimentoActionForm.getMotivoEncerramentoId()).intValue() != 
			ConstantesSistema.NUMERO_NAO_INFORMADO) {
		
			// Recupera informa��es do tr�mite
			registroAtendimento = getDadosEncerramento(encerrarRegistroAtendimentoActionForm);
            
            // Validamos se possivel criar um novo RA
            Object[] arrayValidaGeracaoNovoRA = validarGeracaoNovoRA( 
                    registroAtendimento,
                    httpServletRequest,
                    actionMapping );
            
            if ( ( Boolean ) arrayValidaGeracaoNovoRA[2] ){
                return ( ActionForward ) arrayValidaGeracaoNovoRA[0];
            }
            
            Boolean confirmadoGeracaoNovoRA = ( Boolean )arrayValidaGeracaoNovoRA[1];
            
			registroAtendimentoUnidade = getDadosRegistroAtendimentoUnidade(usuario, registroAtendimento, encerrarRegistroAtendimentoActionForm);
			
			// Faz as valida��es de tramita��o
			fachada.validarEncerramentoRA(registroAtendimento);
            
            // Caso exista a possibilidade de gera��o de um novo registro de 
            // atendimento
			
			
			Integer idDebitoTipo = null;
			BigDecimal valorDebito = null;
			Integer qtdParcelas = null;
			String percentualCobranca = null;
			
			if (encerrarRegistroAtendimentoActionForm.getMotivoEncerramentoId() != null &&
				encerrarRegistroAtendimentoActionForm.getMotivoEncerramentoId().equals(String.valueOf(AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO)) &&
				encerrarRegistroAtendimentoActionForm.getIdTipoDebito() != null &&
				!encerrarRegistroAtendimentoActionForm.getIdTipoDebito().equals("")){
				
				
				if(encerrarRegistroAtendimentoActionForm.getMotivoNaoCobranca() == null || 
				encerrarRegistroAtendimentoActionForm.getMotivoNaoCobranca().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ){

					idDebitoTipo = 
						new Integer(encerrarRegistroAtendimentoActionForm.getIdTipoDebito());
					
					valorDebito = 
						Util.formatarMoedaRealparaBigDecimal(encerrarRegistroAtendimentoActionForm.getValorDebito());
					
					qtdParcelas = 
						new Integer(encerrarRegistroAtendimentoActionForm.getQuantidadeParcelas());
					
					percentualCobranca = encerrarRegistroAtendimentoActionForm.getPercentualCobranca();
					
				}
				else{
					ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
					servicoNaoCobrancaMotivo.setId(
							new Integer(encerrarRegistroAtendimentoActionForm.getMotivoNaoCobranca()));
					
					registroAtendimento.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
				}
				
			}
			
			// Encerrar RA
			fachada.encerrarRegistroAtendimento(registroAtendimento, 
				registroAtendimentoUnidade,
				usuario,
				idDebitoTipo, 
				valorDebito, 
				qtdParcelas, 
				percentualCobranca,
                confirmadoGeracaoNovoRA,null,false);
			
			// Setando Opera��o
			registroAtendimento.setOperacaoEfetuada(operacaoEfetuada);
			registroAtendimento.adicionarUsuario(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(registroAtendimento);
			
			// Setando Opera��o
			registroAtendimentoUnidade.setOperacaoEfetuada(operacaoEfetuada);
			registroAtendimentoUnidade.adicionarUsuario(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(registroAtendimentoUnidade);			
			
			// [FS008] Monta a p�gina de sucesso
			montarPaginaSucesso(httpServletRequest,	
				"Registro de Atendimento "+registroAtendimento.getId()+" encerrado com sucesso!", 
				"Manter Registro de Atendimento",
				"exibirFiltrarRegistroAtendimentoAction.do?menu=sim",
				"exibirConsultarRegistroAtendimentoAction.do?numeroRA="+ registroAtendimento.getId().toString(), 
				"Voltar");
		}

		return retorno;
	}

	/**
	 * Carrega Tr�mite com informa��es vindas da tela 
	 *
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 *
	 * @param form
	 */
	private RegistroAtendimento getDadosEncerramento(EncerrarRegistroAtendimentoActionForm form) {
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();
		
		// Registro de Atendimento
		registroAtendimento.setId(new Integer(form.getNumeroRA()));
		registroAtendimento.setRegistroAtendimento(
			Util.converteStringParaDateHora(form.getDataAtendimento()+" "+form.getHoraAtendimento()));
		
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = 
			this.consultaAtendimentoMotivoEncerramento(new Integer(form.getMotivoEncerramentoId()));

		registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
		
		RegistroAtendimento registroAtendimentoDuplicidade = null;
		if (form.getNumeroRAReferencia() != null && !form.getNumeroRAReferencia().equals("")) {
			registroAtendimentoDuplicidade = new RegistroAtendimento();
			registroAtendimentoDuplicidade.setId(new Integer(form.getNumeroRAReferencia()));
		}
		
		registroAtendimento.setRegistroAtendimentoDuplicidade(registroAtendimentoDuplicidade);
		registroAtendimento.setDataEncerramento(Util.converteStringParaDateHora(form.getDataEncerramento()+" "+form.getHoraEncerramento()+":00"));
		
		if(form.getParecerEncerramento() != null && 
			!form.getParecerEncerramento().equals("") && 
			form.getParecerEncerramento().length() > 400){
			
			String[] msg = new String[2];
			msg[0]="Parecer";
			msg[1]="400";
			
			throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
			
		}else if (form.getParecerEncerramento() != null && 
			!form.getParecerEncerramento().equals("")) {
			
			registroAtendimento.setParecerEncerramento(form.getParecerEncerramento().toUpperCase());	
		}
		
		registroAtendimento.setUltimaAlteracao(new Date());
        registroAtendimento.setSolicitacaoTipoEspecificacao( new SolicitacaoTipoEspecificacao() );
        registroAtendimento.getSolicitacaoTipoEspecificacao().setId( new Integer ( form.getEspecificacaoId() ) );
		
		return registroAtendimento;
	}

	/**
	 * Carrega Registro Atendimento Unidade a partir do usu�rio logado. 
	 *
	 * @author Leonardo Regis
	 * @date 26/08/2006
	 *
	 * @param usuario
	 * @param registroAtendimento
	 */
	private RegistroAtendimentoUnidade getDadosRegistroAtendimentoUnidade(Usuario usuario, RegistroAtendimento registroAtendimento, EncerrarRegistroAtendimentoActionForm form) {
		RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
		
		registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
		registroAtendimentoUnidade.setUnidadeOrganizacional(usuario.getUnidadeOrganizacional());
		registroAtendimentoUnidade.setUsuario(usuario);
		AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
		atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
		registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
		registroAtendimentoUnidade.setUltimaAlteracao(form.getDataConcorrenciaRA());

		return registroAtendimentoUnidade;
	}

	/**
	 * Consulta Atendimento Motivo Encerramento 
	 *
	 * @author Rafael Pinto
	 * @date 18/10/2006
	 *
	 * @param id do Atendimento Motivo Encerramento
	 */
	private AtendimentoMotivoEncerramento consultaAtendimentoMotivoEncerramento(Integer id){
		
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = 
			new FiltroAtendimentoMotivoEncerramento();

		filtroAtendimentoMotivoEncerramento.adicionarParametro(
			new ParametroSimples(FiltroAtendimentoMotivoEncerramento.ID,id));

		Collection colecao = 
			Fachada.getInstancia().pesquisar(filtroAtendimentoMotivoEncerramento, 
					AtendimentoMotivoEncerramento.class.getName());
		
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento =
			(AtendimentoMotivoEncerramento) Util.retonarObjetoDeColecao(colecao);
		
		return atendimentoMotivoEncerramento;
	}
    
    /**
     * 
     * [UC0435] - Encerrar Registro de Atendimento
     * 
     * M�todo verifica se existe a possibilidade de ser gerada um novo RA.
     * Caso positivo, retorna uma tela de pergunta ao usu�rio para verificar
     * se ele deseja gerar esse novo RA ou n�o. Caso n�o seja necess�ria a pegun-
     * ta, retorna com a tela de sucesso normal ao fluxo.
     *
     * @author bruno
     * @date 15/04/2009
     *
     * @param registroAtendimento: Registro do atendimento.
     * @param request: Onde se ser�o informados os parametros para gera��o da p�gina
     * @param actionMapping: Necess�rio para gera��o da p�gina
     * 
     * @return Object[3]
     * 
     *      Object[0]: ActionFoward com a tela a ser mostrada
     *      Object[1]: Se o usu�rio confimou ou n�o a inser��o do novo ra
     *      Object[2]: Se ser� redirecionado ao usu�rio perguntando se ser�
     *                 inserido ou n�o o novo RA.
     */
    private Object[] validarGeracaoNovoRA( 
            RegistroAtendimento registroAtendimento, 
            HttpServletRequest request,
            ActionMapping actionMapping ){
        
        Object[] retorno = new Object[3];
        
        // Verificamos se ja foi confimado...
        retorno[1] = 
            request.getParameter("confirmado") != null ? 
                    request.getParameter("confirmado").equals("ok") : 
                        null;
        
        retorno[2] = new Boolean( Boolean.FALSE );
        
        if ( retorno[1] == null ){
            Fachada fachada = Fachada.getInstancia();
            
            FiltroSolicitacaoTipoEspecificacao filtro = 
                new FiltroSolicitacaoTipoEspecificacao();
            
            filtro.adicionarParametro( new ParametroSimples( 
                    FiltroSolicitacaoTipoEspecificacao.ID, 
                    registroAtendimento.
                        getSolicitacaoTipoEspecificacao().getId() ) );
            
            filtro.adicionarCaminhoParaCarregamentoEntidade( 
                    "solicitacaoTipoEspecificacaoNovoRA" );
            Collection<SolicitacaoTipoEspecificacao> 
                colSolicitacaoTipoEspecificacao = 
                    fachada.pesquisar( 
                            filtro, 
                            SolicitacaoTipoEspecificacao.class.getName() );
            
            SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = 
                ( SolicitacaoTipoEspecificacao ) 
                    Util.retonarObjetoDeColecao( 
                            colSolicitacaoTipoEspecificacao );
            
            if ( solicitacaoTipoEspecificacao.
                    getSolicitacaoTipoEspecificacaoNovoRA()!= null ){
                retorno[2] = new Boolean( Boolean.TRUE );
                
                registroAtendimento.setSolicitacaoTipoEspecificacao( 
                        solicitacaoTipoEspecificacao );
                
                request.setAttribute("caminhoActionConclusao",
                        "/gsan/encerrarRegistroAtendimentoAction.do");
                request.setAttribute("cancelamento", "TRUE");
                request.setAttribute("nomeBotao1", "Sim");
                request.setAttribute("nomeBotao2", "N�o");
    
                retorno[0] = montarPaginaConfirmacao(
                        "atencao.encerrar_ra_confirma_geracao_novo_ra",
                        request, 
                        actionMapping, 
                        solicitacaoTipoEspecificacao.
                            getSolicitacaoTipoEspecificacaoNovoRA().
                                getDescricao() );
                return retorno;
            }
        }       
        
        retorno[0] = actionMapping.findForward("telaSucesso");
        return retorno;
    }	
    
}