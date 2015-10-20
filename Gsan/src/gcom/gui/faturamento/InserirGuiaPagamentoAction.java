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
package gcom.gui.faturamento;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirGuiaPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		InserirGuiaPagamentoActionForm inserirGuiaPagamentoActionForm = (InserirGuiaPagamentoActionForm) actionForm;

		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

		String idImovel = inserirGuiaPagamentoActionForm.getIdImovel();
		String codigoCliente = inserirGuiaPagamentoActionForm.getCodigoCliente();
		
		String idOrdemServico = inserirGuiaPagamentoActionForm.getOrdemServico();
		String dataVencimento = inserirGuiaPagamentoActionForm.getDataVencimento();
		
		String observacao = inserirGuiaPagamentoActionForm.getObservacao();
		String indicadorEmitirObservacao = inserirGuiaPagamentoActionForm.getIndicadorEmitirObservacao();
		
		if (inserirGuiaPagamentoActionForm.getObservacaoPagamentoParcial() != null &&
			!inserirGuiaPagamentoActionForm.getObservacaoPagamentoParcial().equals("")){
			
			if (observacao != null && !observacao.equals("")){
				
				observacao = "REFER�NCIA DA CONTA: " + inserirGuiaPagamentoActionForm.getObservacaoPagamentoParcial() + " - " +
				observacao;
			}
			else{
				observacao = "REFER�NCIA DA CONTA: " + inserirGuiaPagamentoActionForm.getObservacaoPagamentoParcial();
			}
			
			indicadorEmitirObservacao = ConstantesSistema.SIM.toString();
		}
		
		
        
        GuiaPagamento guiaPagamento = new GuiaPagamento();
        
		Imovel imovel = new Imovel();
		if (idImovel != null && !idImovel.equals("")) {
			imovel.setId(new Integer(idImovel));
		}
		guiaPagamento.setImovel(imovel);

		Cliente cliente = new Cliente();
		if (codigoCliente != null && !codigoCliente.equals("")) {
			cliente.setId(new Integer(codigoCliente));

		}
		guiaPagamento.setCliente(cliente);


		OrdemServico ordemServico = new OrdemServico();

		if (idOrdemServico != null && !idOrdemServico.equals("")) {

			ordemServico = 
				Fachada.getInstancia().recuperaOSPorId(new Integer(idOrdemServico));

			// Verifica se a pesquisa retornou algum objeto para a cole��o
			if (ordemServico != null) {
				Fachada.getInstancia().validarExibirInserirGuiaPagamento(null,ordemServico,
					imovel.getId(),cliente.getId());
				
				RegistroAtendimento ra = ordemServico.getRegistroAtendimento();
				
				inserirGuiaPagamentoActionForm.setRegistroAtendimento(""+ra.getId());
				inserirGuiaPagamentoActionForm.setNomeRegistroAtendimento(
					ra.getSolicitacaoTipoEspecificacao().getDescricao());
				
				if(ordemServico.getServicoTipo().getDebitoTipo() != null){
					
					inserirGuiaPagamentoActionForm.setIdTipoDebito(
						""+ordemServico.getServicoTipo().getDebitoTipo().getId());
					
					inserirGuiaPagamentoActionForm.setDescricaoTipoDebito(
						ordemServico.getServicoTipo().getDebitoTipo().getDescricao());
				}
				
			}else{
				throw new ActionServletException("atencao.naocadastrado", null,"Ordem de Servi�o");
			}

		}
		guiaPagamento.setOrdemServico(ordemServico);
		
		String idRegistroAtendimento = inserirGuiaPagamentoActionForm.getRegistroAtendimento();
		
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();
		if (idRegistroAtendimento != null && !idRegistroAtendimento.equals("")) {
			
			ObterDadosRegistroAtendimentoHelper obterDadosRegistroAtendimentoHelper = 
				Fachada.getInstancia().obterDadosRegistroAtendimento(new Integer(idRegistroAtendimento));
			
			if (obterDadosRegistroAtendimentoHelper.getRegistroAtendimento() != null) {

				registroAtendimento = obterDadosRegistroAtendimentoHelper.getRegistroAtendimento();
				fachada.validarExibirInserirGuiaPagamento(registroAtendimento,null,imovel.getId(),cliente.getId());
				
			}else{
				throw new ActionServletException("atencao.naocadastrado", null,"Registro Atendimento");				
			}
		}
		guiaPagamento.setRegistroAtendimento(registroAtendimento);
		
		Date dataVencimentoFormatada = null;

		try {
			dataVencimentoFormatada = dataFormatada.parse(dataVencimento);
		} catch (ParseException ex) {
			throw new ActionServletException("erro.sistema");
		}

		guiaPagamento.setDataVencimento(dataVencimentoFormatada);

        guiaPagamento.setNumeroPrestacaoTotal(new Short(inserirGuiaPagamentoActionForm.getNumeroTotalPrestacao()));
        
        // Adicionado por Rafael Corr�a em 17/12/2008
        if (observacao != null && !observacao.trim().equals("")) {
        	guiaPagamento.setObservacao(observacao.trim());
        } else {
        	if (indicadorEmitirObservacao != null && indicadorEmitirObservacao.trim().equals(ConstantesSistema.SIM.toString())) {
        		throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Observa��o");
        	}
        }        
        
        guiaPagamento.setIndicadorEmitirObservacao(new Short(indicadorEmitirObservacao));

        /** alterado por pedro alexandre dia 20/11/2006 
         * Recupera o usu�rio logado para passar no met�do de inserir guia de pagamento 
         * para verificar se o usu�rio tem abrang�ncia para inserir a guia de pagamento
         * para o im�vel caso a guia de pagamentoseja para o im�vel.
         */
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
        guiaPagamento.setUsuario(usuarioLogado);
        
        Collection colecaoGuiaPagamentoItem = (Collection)sessao.getAttribute("colecaoGuiaDebitoTipo");        
        
        if(colecaoGuiaPagamentoItem != null && !colecaoGuiaPagamentoItem.isEmpty()){
        	Iterator<GuiaPagamentoItem> itGuiaPagamentoItem = colecaoGuiaPagamentoItem.iterator();
        	//[UC0187]InserirGuiaPagamento
        	GuiaPagamentoItem guiaPagamentoItem = null;
        	while(itGuiaPagamentoItem.hasNext()){
        		guiaPagamentoItem = itGuiaPagamentoItem.next();
        		/* -Erivan Sousa- Quando o tipo e d�bito � igual a Pagamento parcial de conta (dbtp_id = 84)
        		 * verifica a quantidade de parcelas e se o imovel foi informado.*/
        		if(guiaPagamentoItem.getDebitoTipo().getId() == 84 ){
        			//[FS0022]Quantidade de presta��es inv�lida.
        			if(guiaPagamento.getNumeroPrestacaoTotal() > 1){
        				throw new ActionServletException("atencao.quantidade_prestacoes_nao_permitido");
        			}
        			//[FS0023]Tipo de d�bito tem ser associado ao im�vel.
        			if(guiaPagamento.getImovel().getId() == null){
        				throw new ActionServletException("atencao.pagamento_parcial_imovel_obrigatorio");
        			}
        		}
        	}
        	
        	
        }else{
        	throw new ActionServletException("atencao.nao.existe.debito.tipo.guia");
        }
        
        String[] idGuiaPagamento = fachada.inserirGuiaPagamento(guiaPagamento,usuarioLogado, 
                new Integer(inserirGuiaPagamentoActionForm.getQtdeDiasVencimento()),colecaoGuiaPagamentoItem, null);
        sessao.setAttribute("idGuiaPagamento",idGuiaPagamento);
        /** fim da altera��o ***************************************************/ 
        
		if (idImovel != null && !idImovel.equals("")) {

//			montarPaginaSucesso(httpServletRequest, "Guia de Pagamento de "
//					+ debitoTipo.getDescricao() + " para o im�vel " + idImovel
//					+ " inserida com sucesso.",
//					"Inserir outra Guia de Pagamento",
//					"exibirInserirGuiaPagamentoAction.do?menu=sim",
//					"exibirManterGuiaPagamentoAction.do?idImovel=" + idImovel,
//					"Cancelar Guia(s) de Pagamento do im�vel " + idImovel);
			
			montarPaginaSucesso(httpServletRequest ,
					"Guia de Pagamento para o im�vel " + idImovel
					+ " inserida com sucesso.", "Inserir outra Guia de Pagamento",
					"exibirInserirGuiaPagamentoAction.do?menu=sim",
					"exibirManterGuiaPagamentoAction.do?idImovel=" + idImovel,
					"Cancelar Guia(s) de Pagamento do im�vel " + idImovel,
					"Imprimir Guia de Pagamento",
					"gerarRelatorioEmitirGuiaPagamentoActionInserir.do");

		} else {
//			montarPaginaSucesso(httpServletRequest, "Guia de Pagamento de "
//					+ debitoTipo.getDescricao() + " para o cliente "
//					+ codigoCliente + " inserida com sucesso.",
//					"Inserir outra Guia de Pagamento",
//					"exibirInserirGuiaPagamentoAction.do?menu=sim",
//					"exibirManterGuiaPagamentoAction.do?idCliente="
//							+ codigoCliente,
//					"Cancelar Guia(s) de Pagamento do cliente " + codigoCliente);

			montarPaginaSucesso(httpServletRequest, "Guia de Pagamento para o cliente "
					+ codigoCliente + " inserida com sucesso.",
					"Inserir outra Guia de Pagamento",
					"exibirInserirGuiaPagamentoAction.do?menu=sim",
					"exibirManterGuiaPagamentoAction.do?idCliente="
							+ codigoCliente,
					"Cancelar Guia(s) de Pagamento do cliente " + codigoCliente,
					"Imprimir Guia de Pagamento",
					"gerarRelatorioEmitirGuiaPagamentoActionInserir.do");
			
		}

		return retorno;
	}
}
