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

import gsan.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaDocumentoItem;
import gsan.cobranca.FiltroCobrancaDocumentoItem;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.conta.ContaMotivoCancelamento;
import gsan.faturamento.conta.FiltroConta;
import gsan.faturamento.conta.FiltroMotivoCancelamentoConta;
import gsan.faturamento.debito.DebitoCreditoSituacao;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirCancelarContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirCancelarConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
        
        //Carregando o identificador das contas selecionadas
        String contaSelected = httpServletRequest.getParameter("conta");
        
    	String idImovel = httpServletRequest.getParameter("idImovel");
    	
		/*
		 * Colocado por Ana Maria em 22/04/2009				
		 */
		if (!fachada.verificarPermissaoRetificarContaImovelPefilBloqueado((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO))){
			Boolean imovelBloqueado = fachada.verificarImovelPerfilBloqueado(new Integer(httpServletRequest.getParameter("idImovel")));
			if(imovelBloqueado){						
                throw new ActionServletException(
                        "atencao.perfil_imovel_nao_permite_operacao");
			}
		}
		
        //Inst�ncia do formul�rio que est� sendo utilizado
        CancelarContaActionForm cancelarContaActionForm = (CancelarContaActionForm) actionForm;
        
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        StringTokenizer idsContas = new StringTokenizer(contaSelected, ",");
        
        while (idsContas.hasMoreElements()) {
        	String dadosConta = (String) idsContas.nextElement();
        	String idConta = dadosConta.split("-")[0];
        	
        	FiltroConta filtroConta = new FiltroConta();
        	filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, Integer.valueOf(idConta)));
        	filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.CONTA_MOTIVO_REVISAO);
        	
        	Collection colecaoConta = fachada.pesquisar(filtroConta, Conta.class.getName());
        	
        	if (colecaoConta != null && !colecaoConta.isEmpty()) {
        		Conta conta = (Conta) Util.retonarObjetoDeColecao(colecaoConta);
        		
        		if (conta.getContaMotivoRevisao() != null && conta.getContaMotivoRevisao().getIndicadorBloqueioAlteracaoConta().equals(ConstantesSistema.SIM)){
        			throw new ActionServletException("atencao.conta_motivo_revisao_indicador_bloqueio_alterar_conta",  
        					Util.formatarMesAnoReferencia(conta.getReferencia()), conta.getContaMotivoRevisao().getDescricaoMotivoRevisaoConta());
        		}
        		
        		if (conta.getDebitoCreditoSituacaoAtual().getId().equals(DebitoCreditoSituacao.RETIFICADA) || conta.getDebitoCreditoSituacaoAtual().getId().equals(DebitoCreditoSituacao.INCLUIDA)) {
        			
        			FiltroCobrancaDocumentoItem filtroCobrancaDocumentoItem = new FiltroCobrancaDocumentoItem();
        			filtroCobrancaDocumentoItem.adicionarParametro(new ParametroSimples(FiltroCobrancaDocumentoItem.CONTA_GERAL_ID, Integer.valueOf(idConta)));
        			
        			Collection colecaoCobrancaDocumentoItem = fachada.pesquisar(filtroCobrancaDocumentoItem, CobrancaDocumentoItem.class.getName());
        			
        			if (conta.getReferenciaContabil().intValue() >= sistemaParametro.getAnoMesFaturamento().intValue() && colecaoCobrancaDocumentoItem != null && !colecaoCobrancaDocumentoItem.isEmpty()) {
        				String contasComExtrato = "";
        				
        				if (cancelarContaActionForm.getContasEmExtratoDebito() != null) {
        					contasComExtrato = cancelarContaActionForm.getContasEmExtratoDebito() + ", ";
        				}
        				
        				contasComExtrato = contasComExtrato + Util.formatarAnoMesParaMesAno(conta.getReferencia());
        				cancelarContaActionForm.setContasEmExtratoDebito(contasComExtrato);
        				
        			}
        		}
        	}
        }
    	
        /******************************************************************
         * Verifica se o Usu�rio tem Permissao Especial para
         * Cancelar Conta sem RA
         * 
         * Ivan S�rgio
         * 28/01/2008
         ******************************************************************/
    	Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		boolean usuarioPermissaoCancelarSemRA = fachada.verificarPermissaoCancelarContaSemRA(usuario);
		
		if (!usuarioPermissaoCancelarSemRA) {
			// [FS0001] - Verificar Exist�ncia de RA
	        fachada.verificarExistenciaRegistroAtendimento(new Integer(idImovel), 
	        		"atencao.conta_existencia_registro_atendimento", EspecificacaoTipoValidacao.ALTERACAO_CONTA);
		}
    	
        //Carregar: Lista dos motivos de cancelamento da conta 
        if (sessao.getAttribute("colecaoMotivoCancelamentoConta") == null){
        
        	FiltroMotivoCancelamentoConta filtroMotivoCancelamentoConta = 
        	new FiltroMotivoCancelamentoConta(FiltroMotivoCancelamentoConta.DESCRICAO_MOTIVO_CANCELAMENTO_CONTA);
        	
        	filtroMotivoCancelamentoConta.adicionarParametro(
        	new ParametroSimples(FiltroMotivoCancelamentoConta.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        
        	Collection colecaoMotivoCancelamentoConta = fachada.pesquisar(filtroMotivoCancelamentoConta,
        			ContaMotivoCancelamento.class.getName());
        
        	if (colecaoMotivoCancelamentoConta == null || colecaoMotivoCancelamentoConta.isEmpty()){
        	throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "MOTIVO_CANCELAMENTO_CONTA");
        	}
        
        	// Disponibiliza a cole��o pela sess�o
        	sessao.setAttribute("colecaoMotivoCancelamentoConta", colecaoMotivoCancelamentoConta);
        
        }
        
        cancelarContaActionForm.setContaSelected(contaSelected);
        cancelarContaActionForm.setMotivoCancelamentoContaID( "" );

        return retorno;
    }

}
