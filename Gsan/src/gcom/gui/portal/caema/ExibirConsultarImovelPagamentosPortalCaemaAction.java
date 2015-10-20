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
package gcom.gui.portal.caema;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * Consultar Pagamentos Portal Caema
 * 
 * @author Nathalia Santos
 * @since 30/01/2012
 */
public class ExibirConsultarImovelPagamentosPortalCaemaAction extends GcomAction {

    /**
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirConsultarImovelPagamentosPortalCaemaAction");
      
        //Obtendo uma instancia da Request
        HttpSession sessao = httpServletRequest.getSession(false);
        
        httpServletRequest.setAttribute("voltarServicos", true);

        Integer idImovelPagamentos = (Integer)  sessao.getAttribute("matricula");

     // 1. O sistema seleciona os pagamentos do im�vel 
		//(a partir da tabela PAGAMENTO com IMOV_ID = Id do im�vel informado 
		//e demais par�metros de sele��o informada)

		//pesquisa utilizada pelo do Consultar Pagamento
		Collection<Pagamento> colecaoImoveisPagamentos = 
			this.getFachada().pesquisarPagamentoImovel(idImovelPagamentos.toString().trim(), 
			null, null, null,
			null, null, null,
			null, null, null, 
			null, null, null,
			null, null, null, 
			null, null, null);

		Collection<PagamentoHistorico> colecaoImoveisPagamentosHistorico = 
			this.getFachada().pesquisarPagamentoHistoricoImovel(idImovelPagamentos.toString().trim(), 
				null, null, null,
				null, null, null,
				null, null, null, 
				null, null, null,
				null, null, null, null);

		// Im�vel
		Collection<Pagamento> colecaoPagamentosImovelConta = new ArrayList<Pagamento>();
		
		// Consultar Pagamentos do Im�vel
		if (colecaoImoveisPagamentos != null && !colecaoImoveisPagamentos.isEmpty()) {

			Iterator<Pagamento> colecaoPagamentoIterator = colecaoImoveisPagamentos.iterator();

			// Divide os pagamentos do im�vel pelo tipo de pagamento
			while (colecaoPagamentoIterator.hasNext()) {
				
				Pagamento pagamento = (Pagamento) colecaoPagamentoIterator.next();

				//Caso o pagamento possua uma conta que j� foi para historico, 
				//Pesquisa a conta na tabela de conta historico
				if (!pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.DEBITO_A_COBRAR) &&   
					!pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
					
					colecaoPagamentosImovelConta.add(pagamento);
				}
					
			}

			// Organizar a cole��o de Conta
			if (colecaoPagamentosImovelConta != null && !colecaoPagamentosImovelConta.isEmpty()) {
				Collections.sort((List) colecaoPagamentosImovelConta,
						new Comparator() {
							public int compare(Object a, Object b) {
								Integer anoMesReferencia1 = ((Pagamento) a).getAnoMesReferencia();
								Integer anoMesReferencia2 = ((Pagamento) b).getAnoMesReferencia();

								return anoMesReferencia1.compareTo(anoMesReferencia2);
							}
					});
				
				
				httpServletRequest.setAttribute("colecaoPagamentosImovelConta",colecaoPagamentosImovelConta);
			}else{
				httpServletRequest.removeAttribute("colecaoPagamentosImovelConta");
			}
			
		}else{
			sessao.removeAttribute("colecaoPagamentosImovelConta");
		}

	
		// Im�vel - Pagamento Historico
		Collection<PagamentoHistorico> colecaoPagamentosHistoricoImovelConta = new ArrayList<PagamentoHistorico> ();
		
		// Consultar Pagamentos do Im�vel
		if (colecaoImoveisPagamentosHistorico != null && !colecaoImoveisPagamentosHistorico.isEmpty()) {

			Iterator<PagamentoHistorico> colecaoPagamentoHistoricoIterator = colecaoImoveisPagamentosHistorico.iterator();
			
			// Divide os pagamentos do im�vel pelo tipo de pagamento
			while (colecaoPagamentoHistoricoIterator.hasNext()) {
				PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) colecaoPagamentoHistoricoIterator.next();
				
				if (pagamentoHistorico.getAvisoBancario() == null) {
					AvisoBancario avisoBancario = new AvisoBancario();
					Arrecadador arrecadador = new Arrecadador();
					Cliente cliente = new Cliente();
					
					String nomeCliente = this.getFachada().pesquisarNomeAgenteArrecadador(pagamentoHistorico.getId());
					
					if (nomeCliente != null){
						
						cliente.setNome(nomeCliente);
						arrecadador.setCliente(cliente);
						avisoBancario.setArrecadador(arrecadador);
						pagamentoHistorico.setAvisoBancario(avisoBancario);
					}
				}
				if ( !pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.DEBITO_A_COBRAR) && 
					!pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
						
					colecaoPagamentosHistoricoImovelConta.add(pagamentoHistorico);
				}
			}
		}

		// Organizar a cole��o de Conta
		if (colecaoPagamentosHistoricoImovelConta != null
				&& !colecaoPagamentosHistoricoImovelConta.isEmpty()) {
			
			httpServletRequest.setAttribute("colecaoPagamentosHistoricoImovelConta",colecaoPagamentosHistoricoImovelConta);
		} else{
			httpServletRequest.removeAttribute("colecaoPagamentosHistoricoImovelConta");
        }
           
		String ip = httpServletRequest.getRemoteAddr(); 
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.CONSULTAR_PAGAMENTOS, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		
		return retorno;
		
		}
    }