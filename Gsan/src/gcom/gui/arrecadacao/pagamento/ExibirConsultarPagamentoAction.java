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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
 * Permite a consulta de pagamentos de um im�vel ou de um cliente ou de um aviso
 * banc�rio ou de um movimento arrecadador. Permite tamb�m gera��o do relat�rio
 * dos pagamentos de um intervalo de localidades
 * 
 * [UC0247] Consultar Pagamentos
 * 
 * @author Tiago Moreno, Roberta Costa
 * @date 31/01/2006, 05/05/2003
 */
public class ExibirConsultarPagamentoAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        // Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("consultarClientePagamentos");

        // Instacia a fachada
        Fachada fachada = Fachada.getInstancia();

        // Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        ConsultarPagamentoActionForm consultarPagamentoActionForm = (ConsultarPagamentoActionForm) actionForm;

        // Im�vel - Pagamento
        Collection<Pagamento> colecaoPagamentosImovelConta = new ArrayList();
        Collection<Pagamento> colecaoPagamentosImovelGuiaPagamento = new ArrayList();
        Collection<Pagamento> colecaoPagamentosImovelDebitoACobrar = new ArrayList();
        // Im�vel - Pagamento Historico
        Collection<PagamentoHistorico> colecaoPagamentosHistoricoImovelConta = new ArrayList();
        Collection<PagamentoHistorico> colecaoPagamentosHistoricoImovelGuiaPagamento = new ArrayList();
        Collection<PagamentoHistorico> colecaoPagamentosHistoricoImovelDebitoACobrar = new ArrayList();

        // Cliente
        Collection<Pagamento> colecaoPagamentosClienteConta = (Collection<Pagamento>) sessao.getAttribute("colecaoPagamentosClienteConta");
        Collection<Pagamento> colecaoPagamentosClienteGuiaPagamento = (Collection<Pagamento>) sessao.getAttribute("colecaoPagamentosClienteGuiaPagamento");
        Collection<Pagamento> colecaoPagamentosClienteDebitoACobrar = (Collection<Pagamento>) sessao.getAttribute("colecaoPagamentosClienteDebitoACobrar");

        // Cliente - Pagamento Historico
        Collection<PagamentoHistorico> colecaoPagamentosHistoricoClienteConta = (Collection<PagamentoHistorico>) sessao.getAttribute("colecaoPagamentosHistoricoClienteConta");
        Collection<PagamentoHistorico> colecaoPagamentosHistoricoClienteGuiaPagamento = (Collection<PagamentoHistorico>) sessao.getAttribute("colecaoPagamentosHistoricoClienteGuiaPagamento");
        Collection<PagamentoHistorico> colecaoPagamentosHistoricoClienteDebitoACobrar = (Collection<PagamentoHistorico>) sessao.getAttribute("colecaoPagamentosHistoricoClienteDebitoACobrar");

        // Aviso Banc�rio
        Collection<Pagamento> colecaoPagamentosAvisoBancario = (Collection<Pagamento>) sessao.getAttribute("colecaoPagamentosAvisoBancario");
        Collection<Pagamento> colecaoPagamentosAvisoBancarioConta = new ArrayList();
        Collection<Pagamento> colecaoPagamentosAvisoBancarioGuiaPagamento = new ArrayList();
        Collection<Pagamento> colecaoPagamentosAvisoBancarioDebitoACobrar = new ArrayList();

        // Aviso Banc�rio - Pagamento Historico
        Collection<PagamentoHistorico> colecaoPagamentosHistoricoAvisoBancario = (Collection<PagamentoHistorico>) sessao.getAttribute("colecaoPagamentosHistoricoAvisoBancario");
        Collection<PagamentoHistorico> colecaoPagamentosHistoricoAvisoBancarioConta = new ArrayList();
        Collection<PagamentoHistorico> colecaoPagamentosHistoricoAvisoBancarioGuiaPagamento = new ArrayList();
        Collection<PagamentoHistorico> colecaoPagamentosHistoricoAvisoBancarioDebitoACobrar = new ArrayList();

        // Movimento Arrecadador
        Collection<Pagamento> colecaoPagamentosMovimentoArrecadador = (Collection<Pagamento>) sessao.getAttribute("colecaoPagamentosMovimentoArrecadador");
        Collection<Pagamento> colecaoPagamentosMovimentoArrecadadorConta = new ArrayList();
        Collection<Pagamento> colecaoPagamentosMovimentoArrecadadorGuiaPagamento = new ArrayList();
        Collection<Pagamento> colecaoPagamentosMovimentoArrecadadorDebitoACobrar = new ArrayList();

        // Movimento Arrecadador - Pagamento Historico
        Collection<PagamentoHistorico> colecaoPagamentosHistoricoMovimentoArrecadador = (Collection<PagamentoHistorico>) sessao.getAttribute("colecaoPagamentosHistoricoMovimentoArrecadador");
        Collection<PagamentoHistorico> colecaoPagamentosHistoricoMovimentoArrecadadorConta = new ArrayList();
        Collection<PagamentoHistorico> colecaoPagamentosHistoricoMovimentoArrecadadorGuiaPagamento = new ArrayList();
        Collection<PagamentoHistorico> colecaoPagamentosHistoricoMovimentoArrecadadorDebitoACobrar = new ArrayList();

        // Vari�veis para definir tamanho das tabelas
        // se necess�rio ou naum scroll
        Integer qtdePagContas = 0;
        Integer qtdePagGuiaPagamento = 0;
        Integer qtdePagDebitoACobrar = 0;

        Integer qtdePagContasAtual = 0;
        Integer qtdePagContasHistorico = 0;
        Integer qtdePagGuiaPagamentoAtual = 0;
        Integer qtdePagGuiaPagamentoHistorico = 0;
        Integer qtdePagDebitoACobrarAtual = 0;
        Integer qtdePagDebitoACobrarHistorico = 0;

        BigDecimal vlPagContasAtual = BigDecimal.ZERO;
        BigDecimal vlPagContasHistorico = BigDecimal.ZERO;
        BigDecimal vlPagGuiaPagamentoAtual = BigDecimal.ZERO;
        BigDecimal vlPagGuiaPagamentoHistorico = BigDecimal.ZERO;
        BigDecimal vlPagDebitoACobrarAtual = BigDecimal.ZERO;
        BigDecimal vlPagDebitoACobrarHistorico = BigDecimal.ZERO;

        // Consultar Pagamentos do Im�vel
        if (sessao.getAttribute("colecaoImoveisPagamentos") != null) {

            retorno = actionMapping.findForward("consultarImovelPagamentos");

            Collection<Pagamento> colecaoImoveisPagamentos = (Collection) sessao.getAttribute("colecaoImoveisPagamentos");

            sessao.removeAttribute("colecaoImoveisPagamentos");

            Imovel imovel = (Imovel) sessao.getAttribute("imovel");

            consultarPagamentoActionForm.setInscricao(imovel.getInscricaoFormatada());

            if (imovel.getLigacaoAguaSituacao() != null) {
                consultarPagamentoActionForm.setSituacaoLigacaoAgua(imovel.getLigacaoAguaSituacao().getDescricao());
            }
            if (imovel.getLigacaoEsgotoSituacao() != null) {
                consultarPagamentoActionForm.setSituacaoLigacaoEsgoto(imovel.getLigacaoEsgotoSituacao().getDescricao());
            }

            Iterator colecaoPagamentoIterator = colecaoImoveisPagamentos.iterator();

            // Divide os pagamentos do im�vel pelo tipo de pagamento
            while (colecaoPagamentoIterator.hasNext()) {
                Pagamento pagamento = (Pagamento) colecaoPagamentoIterator.next();

                if (pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA)) {
                    colecaoPagamentosImovelConta.add(pagamento);
                    vlPagContasAtual = vlPagContasAtual.add(pagamento.getValorPagamento());
                } else if (pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
                    colecaoPagamentosImovelDebitoACobrar.add(pagamento);
                    vlPagDebitoACobrarAtual = vlPagDebitoACobrarAtual.add(pagamento.getValorPagamento());
                } else if (pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
                	vlPagGuiaPagamentoAtual = vlPagGuiaPagamentoAtual.add(pagamento.getValorPagamento());
                    colecaoPagamentosImovelGuiaPagamento.add(pagamento);
                }
            }

            // Organizar a cole��o de Conta
            if (colecaoPagamentosImovelConta != null && !colecaoPagamentosImovelConta.isEmpty()) {
                Collections.sort((List) colecaoPagamentosImovelConta, new Comparator() {
                    public int compare(Object a, Object b) {
                        Integer anoMesReferencia1 = ((Pagamento) a).getAnoMesReferencia();
                        Integer anoMesReferencia2 = ((Pagamento) b).getAnoMesReferencia();

                        return anoMesReferencia1.compareTo(anoMesReferencia2);

                    }
                });
                httpServletRequest.setAttribute("colecaoPagamentosImovelConta", colecaoPagamentosImovelConta);
                qtdePagContasAtual = colecaoPagamentosImovelConta.size();
                qtdePagContas = colecaoPagamentosImovelConta.size();
            }

            // Organizar a cole��o de Guia de Pagamento
            if (colecaoPagamentosImovelGuiaPagamento != null && !colecaoPagamentosImovelGuiaPagamento.isEmpty()) {
                Collections.sort((List) colecaoPagamentosImovelGuiaPagamento, new Comparator() {
                    public int compare(Object a, Object b) {
                        String tipoDebito1 = ((Pagamento) a).getDebitoTipo() == null ? "" : ((Pagamento) a).getDebitoTipo().getDescricao();
                        String tipoDebito2 = ((Pagamento) b).getDebitoTipo() == null ? "" : ((Pagamento) b).getDebitoTipo().getDescricao();

                        return tipoDebito1.compareTo(tipoDebito2);

                    }
                });
                httpServletRequest.setAttribute("colecaoPagamentosImovelGuiaPagamento", colecaoPagamentosImovelGuiaPagamento);
                
                qtdePagGuiaPagamentoAtual = colecaoPagamentosImovelGuiaPagamento.size();
                qtdePagGuiaPagamento = colecaoPagamentosImovelGuiaPagamento.size();
            }

            // Organizar a cole��o de D�bito a Cobrar
            if (colecaoPagamentosImovelDebitoACobrar != null && !colecaoPagamentosImovelDebitoACobrar.isEmpty()) {

                // Organizar a cole��o
                Collections.sort((List) colecaoPagamentosImovelDebitoACobrar, new Comparator() {
                    public int compare(Object a, Object b) {
                        Integer anoMesReferencia1 = ((Pagamento) a).getAnoMesReferencia();
                        Integer anoMesReferencia2 = ((Pagamento) b).getAnoMesReferencia();

                        return anoMesReferencia1.compareTo(anoMesReferencia2);

                    }
                });
                httpServletRequest.setAttribute("colecaoPagamentosImovelDebitoACobrar", colecaoPagamentosImovelDebitoACobrar);
                
                qtdePagDebitoACobrarAtual = colecaoPagamentosImovelDebitoACobrar.size();
                qtdePagDebitoACobrar = colecaoPagamentosImovelDebitoACobrar.size();
            }

            // Pesquisa os Clientes do Imovel

            Collection colecaoClienteImovel = fachada.pesquisarClientesImoveisPagamento(imovel.getId());

            if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
                httpServletRequest.setAttribute("colecaoClienteImovel", colecaoClienteImovel);
            }
        }

        // Consultar Pagamentos HISTORICOS do Im�vel
        if (sessao.getAttribute("colecaoImoveisPagamentosHistorico") != null) {

            retorno = actionMapping.findForward("consultarImovelPagamentos");

            Collection<PagamentoHistorico> colecaoImoveisPagamentosHistorico = (Collection) sessao.getAttribute("colecaoImoveisPagamentosHistorico");

            sessao.removeAttribute("colecaoImoveisPagamentosHistorico");

            Imovel imovel = (Imovel) sessao.getAttribute("imovel");

            consultarPagamentoActionForm.setInscricao(imovel.getInscricaoFormatada());

            if (imovel.getLigacaoAguaSituacao() != null) {
                consultarPagamentoActionForm.setSituacaoLigacaoAgua(imovel.getLigacaoAguaSituacao().getDescricao());
            }
            if (imovel.getLigacaoEsgotoSituacao() != null) {
                consultarPagamentoActionForm.setSituacaoLigacaoEsgoto(imovel.getLigacaoEsgotoSituacao().getDescricao());
            }
            Iterator colecaoPagamentoHistoricoIterator = colecaoImoveisPagamentosHistorico.iterator();

            // Divide os pagamentos do im�vel pelo tipo de pagamento
            while (colecaoPagamentoHistoricoIterator.hasNext()) {
                PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) colecaoPagamentoHistoricoIterator.next();

                if (pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA)) {
                    colecaoPagamentosHistoricoImovelConta.add(pagamentoHistorico);
                    
                    vlPagContasHistorico = vlPagContasHistorico.add(pagamentoHistorico.getValorPagamento());
                    
                } else if (pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
                    colecaoPagamentosHistoricoImovelDebitoACobrar.add(pagamentoHistorico);
                    
                    vlPagDebitoACobrarHistorico = vlPagDebitoACobrarHistorico.add(pagamentoHistorico.getValorPagamento());
                    
                } else if (pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
                    colecaoPagamentosHistoricoImovelGuiaPagamento.add(pagamentoHistorico);
                    
                    vlPagGuiaPagamentoHistorico = vlPagGuiaPagamentoHistorico.add(pagamentoHistorico.getValorPagamento());
                }
            }

            // Organizar a cole��o de Conta
            if (colecaoPagamentosHistoricoImovelConta != null && !colecaoPagamentosHistoricoImovelConta.isEmpty()) {

                httpServletRequest.setAttribute("colecaoPagamentosHistoricoImovelConta", colecaoPagamentosHistoricoImovelConta);

                qtdePagContasHistorico = colecaoPagamentosHistoricoImovelConta.size();
                qtdePagContas = qtdePagContas + colecaoPagamentosHistoricoImovelConta.size();
            }

            // Organizar a cole��o de Guia de Pagamento
            if (colecaoPagamentosHistoricoImovelGuiaPagamento != null && !colecaoPagamentosHistoricoImovelGuiaPagamento.isEmpty()) {

                httpServletRequest.setAttribute("colecaoPagamentosHistoricoImovelGuiaPagamento", colecaoPagamentosHistoricoImovelGuiaPagamento);
                
                qtdePagGuiaPagamentoHistorico = colecaoPagamentosHistoricoImovelGuiaPagamento.size();
                qtdePagGuiaPagamento = qtdePagGuiaPagamento + colecaoPagamentosHistoricoImovelGuiaPagamento.size();
            }

            // Organizar a cole��o de D�bito a Cobrar
            if (colecaoPagamentosHistoricoImovelDebitoACobrar != null && !colecaoPagamentosHistoricoImovelDebitoACobrar.isEmpty()) {

                httpServletRequest.setAttribute("colecaoPagamentosHistoricoImovelDebitoACobrar", colecaoPagamentosHistoricoImovelDebitoACobrar);
                
                qtdePagDebitoACobrarHistorico = colecaoPagamentosHistoricoImovelDebitoACobrar.size();
                qtdePagDebitoACobrar = qtdePagDebitoACobrar + colecaoPagamentosHistoricoImovelDebitoACobrar.size();
            }
        }

        // Consultar o Pagamento do CLIENTE
        if (sessao.getAttribute("colecaoPagamentosClienteConta") != null || sessao.getAttribute("colecaoPagamentosClienteGuiaPagamento") != null || sessao.getAttribute("colecaoPagamentosClienteDebitoACobrar") != null) {

            retorno = actionMapping.findForward("consultarClientePagamentos");

            colecaoPagamentosClienteConta = (Collection) sessao.getAttribute("colecaoPagamentosClienteConta");
            colecaoPagamentosClienteGuiaPagamento = (Collection) sessao.getAttribute("colecaoPagamentosClienteGuiaPagamento");
            colecaoPagamentosClienteDebitoACobrar = (Collection) sessao.getAttribute("colecaoPagamentosClienteDebitoACobrar");

            sessao.removeAttribute("colecaoPagamentosClienteConta");
            sessao.removeAttribute("colecaoPagamentosClienteGuiaPagamento");
            sessao.removeAttribute("colecaoPagamentosClienteDebitoACobrar");

            String idCliente = (String) consultarPagamentoActionForm.getIdCliente();

            Cliente cliente = fachada.pesquisarClientePagamento(new Integer(idCliente));

            // Pesquisa o endere�o de correspond�ncia do cliente pelo seu id
            ClienteEndereco clienteEndereco = fachada.pesquisarClienteEnderecoPagamento(cliente.getId());

            if (clienteEndereco != null) {
                sessao.setAttribute("clienteEndereco", clienteEndereco);
            }

            // Pesquisa o telefone padr�o do cliente pelo seu id
            ClienteFone clienteFone = fachada.pesquisarClienteFonePagamento(cliente.getId());

            if (clienteFone != null) {
                sessao.setAttribute("clienteFone", clienteFone);
            }

            consultarPagamentoActionForm.setNomeCliente(cliente.getNome());

            if (cliente.getClienteTipo() != null && cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() != null) {
                if (cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {
                    consultarPagamentoActionForm.setCpfCnpj(cliente.getCpfFormatado());
                    consultarPagamentoActionForm.setProfissao(cliente.getProfissao() == null ? "" : cliente.getProfissao().getDescricao());
                } else {
                    consultarPagamentoActionForm.setCpfCnpj(cliente.getCnpjFormatado());
                    consultarPagamentoActionForm.setRamoAtividade(cliente.getRamoAtividade() == null ? "" : cliente.getRamoAtividade().getDescricao());
                }
            } else {
                consultarPagamentoActionForm.setCpfCnpj("N�O INFORMADO");
            }

            if (colecaoPagamentosClienteConta != null) {
                httpServletRequest.setAttribute("colecaoPagamentosClienteConta", colecaoPagamentosClienteConta);

                qtdePagContasAtual = colecaoPagamentosClienteConta.size();
                qtdePagContas = colecaoPagamentosClienteConta.size();
                
                Iterator iteratorPagamentosClienteConta = colecaoPagamentosClienteConta.iterator();
                
                while(iteratorPagamentosClienteConta.hasNext()){
                	Pagamento pagamento = (Pagamento) iteratorPagamentosClienteConta.next();
                	vlPagContasAtual = vlPagContasAtual.add(pagamento.getValorPagamento());
                }
            }
            if (colecaoPagamentosClienteGuiaPagamento != null) {
                httpServletRequest.setAttribute("colecaoPagamentosClienteGuiaPagamento", colecaoPagamentosClienteGuiaPagamento);
                
                qtdePagGuiaPagamentoAtual = colecaoPagamentosClienteGuiaPagamento.size();
                qtdePagGuiaPagamento = colecaoPagamentosClienteGuiaPagamento.size();
                
                Iterator iteratorPagamentosClienteGuiaPagamento = colecaoPagamentosClienteGuiaPagamento.iterator();
                
                while(iteratorPagamentosClienteGuiaPagamento.hasNext()){
                	Pagamento pagamento = (Pagamento) iteratorPagamentosClienteGuiaPagamento.next();
                	vlPagGuiaPagamentoAtual = vlPagGuiaPagamentoAtual.add(pagamento.getValorPagamento());
                }
            }
            if (colecaoPagamentosClienteDebitoACobrar != null) {
                httpServletRequest.setAttribute("colecaoPagamentosClienteDebitoACobrar", colecaoPagamentosClienteDebitoACobrar);
                
                qtdePagDebitoACobrarAtual = colecaoPagamentosClienteDebitoACobrar.size();
                qtdePagDebitoACobrar = colecaoPagamentosClienteDebitoACobrar.size();
                
                Iterator iteratorPagamentosClienteDebitoACobrar = colecaoPagamentosClienteDebitoACobrar.iterator();
                
                while(iteratorPagamentosClienteDebitoACobrar.hasNext()){
                	Pagamento pagamento = (Pagamento) iteratorPagamentosClienteDebitoACobrar.next();
                	vlPagDebitoACobrarAtual = vlPagDebitoACobrarAtual.add(pagamento.getValorPagamento());
                }
            }
        }

        // Consultar o Pagamento Historico do CLIENTE
        if (sessao.getAttribute("colecaoPagamentosHistoricoClienteConta") != null || sessao.getAttribute("colecaoPagamentosHistoricoClienteGuiaPagamento") != null || sessao.getAttribute("colecaoPagamentosHistoricoClienteDebitoACobrar") != null) {

            retorno = actionMapping.findForward("consultarClientePagamentos");

            colecaoPagamentosHistoricoClienteConta = (Collection) sessao.getAttribute("colecaoPagamentosHistoricoClienteConta");
            colecaoPagamentosHistoricoClienteGuiaPagamento = (Collection) sessao.getAttribute("colecaoPagamentosHistoricoClienteGuiaPagamento");
            colecaoPagamentosHistoricoClienteDebitoACobrar = (Collection) sessao.getAttribute("colecaoPagamentosHistoricoClienteDebitoACobrar");

            sessao.removeAttribute("colecaoPagamentosHistoricoClienteConta");
            sessao.removeAttribute("colecaoPagamentosHistoricoClienteGuiaPagamento");
            sessao.removeAttribute("colecaoPagamentosHistoricoClienteDebitoACobrar");

            if (colecaoPagamentosHistoricoClienteConta != null) {
                httpServletRequest.setAttribute("colecaoPagamentosHistoricoClienteConta", colecaoPagamentosHistoricoClienteConta);
                
                qtdePagContasHistorico = colecaoPagamentosHistoricoClienteConta.size();
                qtdePagContas = qtdePagContas + colecaoPagamentosHistoricoClienteConta.size();
                
                Iterator iteratorPagamentosHistoricoClienteConta = colecaoPagamentosHistoricoClienteConta.iterator();
                
                while(iteratorPagamentosHistoricoClienteConta.hasNext()){
                	PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) iteratorPagamentosHistoricoClienteConta.next();
                	vlPagContasHistorico = vlPagContasHistorico.add(pagamentoHistorico.getValorPagamento());
                }
            }
            if (colecaoPagamentosHistoricoClienteGuiaPagamento != null) {
                httpServletRequest.setAttribute("colecaoPagamentosHistoricoClienteGuiaPagamento", colecaoPagamentosHistoricoClienteGuiaPagamento);
                
                qtdePagGuiaPagamentoHistorico = colecaoPagamentosHistoricoClienteGuiaPagamento.size();
                qtdePagGuiaPagamento = qtdePagGuiaPagamento + colecaoPagamentosHistoricoClienteGuiaPagamento.size();
                
                Iterator iteratorPagamentosHistoricoClienteGuiaPagamento = colecaoPagamentosHistoricoClienteGuiaPagamento.iterator();
                
                while(iteratorPagamentosHistoricoClienteGuiaPagamento.hasNext()){
                	PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) iteratorPagamentosHistoricoClienteGuiaPagamento.next();
                	vlPagGuiaPagamentoHistorico = vlPagGuiaPagamentoHistorico.add(pagamentoHistorico.getValorPagamento());
                }
            }
            if (colecaoPagamentosHistoricoClienteDebitoACobrar != null) {
                httpServletRequest.setAttribute("colecaoPagamentosHistoricoClienteDebitoACobrar", colecaoPagamentosHistoricoClienteDebitoACobrar);
                
                qtdePagDebitoACobrarHistorico = colecaoPagamentosHistoricoClienteDebitoACobrar.size();
                qtdePagDebitoACobrar = qtdePagDebitoACobrar + colecaoPagamentosHistoricoClienteDebitoACobrar.size();
                
                Iterator iteratorPagamentosHistoricoClienteDebitoACobrar = colecaoPagamentosHistoricoClienteDebitoACobrar.iterator();
                
                while(iteratorPagamentosHistoricoClienteDebitoACobrar.hasNext()){
                	PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) iteratorPagamentosHistoricoClienteDebitoACobrar.next();
                	vlPagDebitoACobrarHistorico = vlPagDebitoACobrarHistorico.add(pagamentoHistorico.getValorPagamento());
                }
            }

            if (sessao.getAttribute("colecaoPagamentosClienteConta") == null && sessao.getAttribute("colecaoPagamentosClienteGuiaPagamento") == null && sessao.getAttribute("colecaoPagamentosClienteDebitoACobrar") == null) {
                String idCliente = (String) consultarPagamentoActionForm.getIdCliente();

                // Pesquisa o cliente
                Cliente cliente = fachada.pesquisarClientePagamento(new Integer(idCliente));

                // Pesquisa o endere�o de correspond�ncia do cliente pelo seu id
                ClienteEndereco clienteEndereco = fachada.pesquisarClienteEnderecoPagamento(cliente.getId());

                if (clienteEndereco != null) {
                    sessao.setAttribute("clienteEndereco", clienteEndereco);
                }

                // Pesquisa o telefone padr�o do cliente pelo seu id
                ClienteFone clienteFone = fachada.pesquisarClienteFonePagamento(cliente.getId());

                if (clienteFone != null) {
                    sessao.setAttribute("clienteFone", clienteFone);
                }

                consultarPagamentoActionForm.setNomeCliente(cliente.getNome());

                if (cliente.getClienteTipo() != null && cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() != null) {
                    if (cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {
                        consultarPagamentoActionForm.setCpfCnpj(cliente.getCpfFormatado());
                        consultarPagamentoActionForm.setProfissao(cliente.getProfissao() == null ? "" : cliente.getProfissao().getDescricao());
                    } else {
                        consultarPagamentoActionForm.setCpfCnpj(cliente.getCnpjFormatado());
                        consultarPagamentoActionForm.setRamoAtividade(cliente.getRamoAtividade() == null ? "" : cliente.getRamoAtividade().getDescricao());
                    }
                } else {
                    consultarPagamentoActionForm.setCpfCnpj("N�O INFORMADO");
                }

            }

        }

        // Consultar Pagamento do AVISO BANC�RIO
        if (sessao.getAttribute("colecaoPagamentosAvisoBancario") != null) {

            retorno = actionMapping.findForward("consultarAvisoBancarioPagamentos");

            colecaoPagamentosAvisoBancario = (Collection) sessao.getAttribute("colecaoPagamentosAvisoBancario");

            sessao.removeAttribute("colecaoPagamentosAvisoBancario");

            AvisoBancario avisoBancario = (AvisoBancario) sessao.getAttribute("avisoBancario");

            consultarPagamentoActionForm.setSequencialAviso("" + avisoBancario.getNumeroSequencial());

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String dataLancamento = "";
            if (avisoBancario.getDataLancamento() != null && !avisoBancario.getDataLancamento().equals("")) {
                dataLancamento = format.format(avisoBancario.getDataLancamento());
            }

            consultarPagamentoActionForm.setDataLancamento(dataLancamento);

            if (avisoBancario.getArrecadador() != null) {
                consultarPagamentoActionForm.setIdArrecadador("" + avisoBancario.getArrecadador().getCodigoAgente());
                consultarPagamentoActionForm.setDescricaoArrecadador(avisoBancario.getArrecadador().getCliente().getNome());
            }
            if (avisoBancario.getIndicadorCreditoDebito() == AvisoBancario.INDICADOR_CREDITO.shortValue()) {
                consultarPagamentoActionForm.setTipoAviso("CR�DITO");
            } else {
                consultarPagamentoActionForm.setTipoAviso("D�BITO");
            }

            Iterator colecaoPagamentoIterator = colecaoPagamentosAvisoBancario.iterator();

            while (colecaoPagamentoIterator.hasNext()) {

                Pagamento pagamento = (Pagamento) colecaoPagamentoIterator.next();

                if (pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA)) {
                    colecaoPagamentosAvisoBancarioConta.add(pagamento);
                    
                    vlPagContasAtual = vlPagContasAtual.add(pagamento.getValorPagamento());
                    
                } else if (pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
                    colecaoPagamentosAvisoBancarioDebitoACobrar.add(pagamento);
                    
                    vlPagDebitoACobrarAtual = vlPagDebitoACobrarAtual.add(pagamento.getValorPagamento());
                } else if (pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
                    colecaoPagamentosAvisoBancarioGuiaPagamento.add(pagamento);
                    
                    vlPagGuiaPagamentoAtual = vlPagGuiaPagamentoAtual.add(pagamento.getValorPagamento());
                }

            }
            if (colecaoPagamentosMovimentoArrecadadorConta != null) {
                httpServletRequest.setAttribute("colecaoPagamentosAvisoBancarioConta", colecaoPagamentosAvisoBancarioConta);
                
                qtdePagContasAtual = colecaoPagamentosAvisoBancarioConta.size();
                qtdePagContas = colecaoPagamentosAvisoBancarioConta.size();
            }
            if (colecaoPagamentosAvisoBancarioGuiaPagamento != null) {
                httpServletRequest.setAttribute("colecaoPagamentosAvisoBancarioGuiaPagamento", colecaoPagamentosAvisoBancarioGuiaPagamento);
               
                qtdePagGuiaPagamentoAtual = colecaoPagamentosAvisoBancarioGuiaPagamento.size();
                qtdePagGuiaPagamento = colecaoPagamentosAvisoBancarioGuiaPagamento.size();
            }
            if (colecaoPagamentosAvisoBancarioDebitoACobrar != null) {
                httpServletRequest.setAttribute("colecaoPagamentosAvisoBancarioDebitoACobrar", colecaoPagamentosAvisoBancarioDebitoACobrar);
                
                qtdePagDebitoACobrarAtual = colecaoPagamentosAvisoBancarioDebitoACobrar.size();
                qtdePagDebitoACobrar = colecaoPagamentosAvisoBancarioDebitoACobrar.size();
            }
        }

        // Consultar Pagamento Historico do AVISO BANC�RIO
        if (sessao.getAttribute("colecaoPagamentosHistoricoAvisoBancario") != null) {

            retorno = actionMapping.findForward("consultarAvisoBancarioPagamentos");

            AvisoBancario avisoBancario = (AvisoBancario) sessao.getAttribute("avisoBancario");

            consultarPagamentoActionForm.setSequencialAviso("" + avisoBancario.getNumeroSequencial());

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String dataLancamento = "";
            if (avisoBancario.getDataLancamento() != null && !avisoBancario.getDataLancamento().equals("")) {
                dataLancamento = format.format(avisoBancario.getDataLancamento());
            }

            consultarPagamentoActionForm.setDataLancamento(dataLancamento);

            if (avisoBancario.getArrecadador() != null) {
                consultarPagamentoActionForm.setIdArrecadador("" + avisoBancario.getArrecadador().getCodigoAgente());
                consultarPagamentoActionForm.setDescricaoArrecadador(avisoBancario.getArrecadador().getCliente().getNome());
            }
            if (avisoBancario.getIndicadorCreditoDebito() == AvisoBancario.INDICADOR_CREDITO.shortValue()) {
                consultarPagamentoActionForm.setTipoAviso("CR�DITO");
            } else {
                consultarPagamentoActionForm.setTipoAviso("D�BITO");
            }

            colecaoPagamentosHistoricoAvisoBancario = (Collection) sessao.getAttribute("colecaoPagamentosHistoricoAvisoBancario");

            sessao.removeAttribute("colecaoPagamentosHistoricoAvisoBancario");

            Iterator colecaoPagamentoHistoricoIterator = colecaoPagamentosHistoricoAvisoBancario.iterator();

            while (colecaoPagamentoHistoricoIterator.hasNext()) {

                PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) colecaoPagamentoHistoricoIterator.next();

                if (pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA)) {
                    colecaoPagamentosHistoricoAvisoBancarioConta.add(pagamentoHistorico);
                    
                    vlPagContasHistorico = vlPagContasHistorico.add(pagamentoHistorico.getValorPagamento());
                } else if (pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
                    colecaoPagamentosHistoricoAvisoBancarioDebitoACobrar.add(pagamentoHistorico);
                    
                    vlPagDebitoACobrarHistorico = vlPagDebitoACobrarHistorico.add(pagamentoHistorico.getValorPagamento());
                } else if (pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
                    colecaoPagamentosHistoricoAvisoBancarioGuiaPagamento.add(pagamentoHistorico);
                    
                    vlPagGuiaPagamentoHistorico = vlPagGuiaPagamentoHistorico.add(pagamentoHistorico.getValorPagamento());
                }

            }
            if (colecaoPagamentosHistoricoAvisoBancarioConta != null) {
                httpServletRequest.setAttribute("colecaoPagamentosHistoricoAvisoBancarioConta", colecaoPagamentosHistoricoAvisoBancarioConta);
                
                qtdePagContasHistorico = colecaoPagamentosHistoricoAvisoBancarioConta.size();
                qtdePagContas = qtdePagContas + colecaoPagamentosHistoricoAvisoBancarioConta.size();
            }
            if (colecaoPagamentosHistoricoAvisoBancarioGuiaPagamento != null) {
                httpServletRequest.setAttribute("colecaoPagamentosHistoricoAvisoBancarioGuiaPagamento", colecaoPagamentosHistoricoAvisoBancarioGuiaPagamento);
                
                qtdePagGuiaPagamentoHistorico = colecaoPagamentosHistoricoAvisoBancarioGuiaPagamento.size();
                qtdePagGuiaPagamento = qtdePagGuiaPagamento + colecaoPagamentosHistoricoAvisoBancarioGuiaPagamento.size();
            }
            if (colecaoPagamentosHistoricoAvisoBancarioDebitoACobrar != null) {
                httpServletRequest.setAttribute("colecaoPagamentosHistoricoAvisoBancarioDebitoACobrar", colecaoPagamentosHistoricoAvisoBancarioDebitoACobrar);
                
                qtdePagDebitoACobrarHistorico = colecaoPagamentosHistoricoAvisoBancarioDebitoACobrar.size();
                qtdePagDebitoACobrar = qtdePagDebitoACobrar + colecaoPagamentosHistoricoAvisoBancarioDebitoACobrar.size();
            }

        }

        // Consultar Pagamento do MOVIMENTO ARRECADADOR
        if (sessao.getAttribute("colecaoPagamentosMovimentoArrecadador") != null) {

            retorno = actionMapping.findForward("consultarMovimentoArrecadadorPagamentos");

            colecaoPagamentosMovimentoArrecadador = (Collection) sessao.getAttribute("colecaoPagamentosMovimentoArrecadador");

            sessao.removeAttribute("colecaoMovimentosArrecadadorPagamentos");

            ArrecadadorMovimento arrecadadorMovimento = (ArrecadadorMovimento) sessao.getAttribute("arrecadadorMovimento");
            consultarPagamentoActionForm.setIdArrecadador(arrecadadorMovimento.getCodigoBanco().toString());
            consultarPagamentoActionForm.setDescricaoArrecadador(arrecadadorMovimento.getNomeBanco().toString());
            consultarPagamentoActionForm.setCodigoRemessa(arrecadadorMovimento.getCodigoRemessa().toString());
            consultarPagamentoActionForm.setIdentificacaoServico(arrecadadorMovimento.getDescricaoIdentificacaoServico());
            consultarPagamentoActionForm.setNumeroSequencial(arrecadadorMovimento.getNumeroSequencialArquivo().toString());
            consultarPagamentoActionForm.setDataGeracao(Util.formatarData(arrecadadorMovimento.getDataGeracao()));
            consultarPagamentoActionForm.setNumeroRegistrosMovimento(arrecadadorMovimento.getNumeroRegistrosMovimento().toString());
            consultarPagamentoActionForm.setValorTotalMovimento(arrecadadorMovimento.getValorTotalMovimento().toString());
            consultarPagamentoActionForm.setDataProcessamento(Util.formatarData(arrecadadorMovimento.getUltimaAlteracao()));
            consultarPagamentoActionForm.setHoraProcessamento(Util.formatarHoraSemData(arrecadadorMovimento.getUltimaAlteracao()));

            Iterator colecaoPagamentoIterator = colecaoPagamentosMovimentoArrecadador.iterator();

            while (colecaoPagamentoIterator.hasNext()) {

                Pagamento pagamento = (Pagamento) colecaoPagamentoIterator.next();

                if (pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA)) {
                    colecaoPagamentosMovimentoArrecadadorConta.add(pagamento);
                    
                    vlPagContasAtual = vlPagContasAtual.add(pagamento.getValorPagamento());
                } else if (pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
                    colecaoPagamentosMovimentoArrecadadorDebitoACobrar.add(pagamento);
                    
                    vlPagDebitoACobrarAtual = vlPagDebitoACobrarAtual.add(pagamento.getValorPagamento());
                } else if (pagamento.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
                    colecaoPagamentosMovimentoArrecadadorGuiaPagamento.add(pagamento);
                    
                    vlPagGuiaPagamentoAtual = vlPagGuiaPagamentoAtual.add(pagamento.getValorPagamento());
                }

            }

            if (colecaoPagamentosMovimentoArrecadadorConta != null) {
                httpServletRequest.setAttribute("colecaoPagamentosMovimentoArrecadadorConta", colecaoPagamentosMovimentoArrecadadorConta);
                
                qtdePagContasAtual = colecaoPagamentosMovimentoArrecadadorConta.size();
                qtdePagContas = colecaoPagamentosMovimentoArrecadadorConta.size();
            }
            if (colecaoPagamentosMovimentoArrecadador != null) {
                httpServletRequest.setAttribute("colecaoPagamentosMovimentoArrecadadorDebitoACobrar", colecaoPagamentosMovimentoArrecadador);
                
                qtdePagGuiaPagamentoAtual = colecaoPagamentosMovimentoArrecadador.size();
                qtdePagGuiaPagamento = colecaoPagamentosMovimentoArrecadador.size();
            }
            if (colecaoPagamentosMovimentoArrecadadorGuiaPagamento != null) {
                httpServletRequest.setAttribute("colecaoPagamentosMovimentoArrecadadorGuiaPagamento", colecaoPagamentosMovimentoArrecadadorGuiaPagamento);
                
                qtdePagDebitoACobrarAtual = colecaoPagamentosMovimentoArrecadadorGuiaPagamento.size();
                qtdePagDebitoACobrar = colecaoPagamentosMovimentoArrecadadorGuiaPagamento.size();
            }
        }

        // Consultar Pagamento Historico do MOVIMENTO ARRECADADOR
        if (sessao.getAttribute("colecaoPagamentosHistoricoMovimentoArrecadador") != null) {

            retorno = actionMapping.findForward("consultarMovimentoArrecadadorPagamentos");

            colecaoPagamentosHistoricoMovimentoArrecadador = (Collection) sessao.getAttribute("colecaoPagamentosHistoricoMovimentoArrecadador");

            sessao.removeAttribute("colecaoPagamentosMovimentoArrecadador");

            Iterator colecaoPagamentoHistoricoIterator = colecaoPagamentosHistoricoMovimentoArrecadador.iterator();

            while (colecaoPagamentoHistoricoIterator.hasNext()) {

                PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) colecaoPagamentoHistoricoIterator.next();

                if (pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA)) {
                    colecaoPagamentosHistoricoMovimentoArrecadadorConta.add(pagamentoHistorico);
                    
                    vlPagContasHistorico = vlPagContasHistorico.add(pagamentoHistorico.getValorPagamento());
                } else if (pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
                    colecaoPagamentosHistoricoMovimentoArrecadadorDebitoACobrar.add(pagamentoHistorico);
                    
                    vlPagDebitoACobrarHistorico = vlPagDebitoACobrarHistorico.add(pagamentoHistorico.getValorPagamento());
                } else if (pagamentoHistorico.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
                    colecaoPagamentosHistoricoMovimentoArrecadadorGuiaPagamento.add(pagamentoHistorico);
                    
                    vlPagGuiaPagamentoHistorico = vlPagGuiaPagamentoHistorico.add(pagamentoHistorico.getValorPagamento());
                }

            }

            if (colecaoPagamentosHistoricoMovimentoArrecadadorConta != null) {
                httpServletRequest.setAttribute("colecaoPagamentosHistoricoMovimentoArrecadadorConta", colecaoPagamentosHistoricoMovimentoArrecadadorConta);
                
                qtdePagContasHistorico = colecaoPagamentosHistoricoMovimentoArrecadadorConta.size();
                qtdePagContas = qtdePagContas + colecaoPagamentosHistoricoMovimentoArrecadadorConta.size();
            }
            if (colecaoPagamentosHistoricoMovimentoArrecadador != null) {
                httpServletRequest.setAttribute("colecaoPagamentosHistoricoMovimentoArrecadadorDebitoACobrar", colecaoPagamentosHistoricoMovimentoArrecadador);
                
                qtdePagGuiaPagamentoHistorico = colecaoPagamentosHistoricoMovimentoArrecadador.size();
                qtdePagGuiaPagamento = qtdePagGuiaPagamento + colecaoPagamentosHistoricoMovimentoArrecadador.size();
            }
            if (colecaoPagamentosHistoricoMovimentoArrecadadorGuiaPagamento != null) {
                httpServletRequest.setAttribute("colecaoPagamentosHistoricoMovimentoArrecadadorGuiaPagamento", colecaoPagamentosHistoricoMovimentoArrecadadorGuiaPagamento);
                
                qtdePagDebitoACobrarHistorico = colecaoPagamentosHistoricoMovimentoArrecadadorGuiaPagamento.size();
                qtdePagDebitoACobrar = qtdePagDebitoACobrar + colecaoPagamentosHistoricoMovimentoArrecadadorGuiaPagamento.size();
            }
        }

        
        sessao.setAttribute("qtdePagContas", qtdePagContas);
        sessao.setAttribute("qtdePagContasAtual", qtdePagContasAtual);
        sessao.setAttribute("qtdePagContasHistorico", qtdePagContasHistorico);
        
        sessao.setAttribute("qtdePagGuiaPagamento", qtdePagGuiaPagamento);
        sessao.setAttribute("qtdePagGuiaPagamentoAtual", qtdePagGuiaPagamentoAtual);
        sessao.setAttribute("qtdePagGuiaPagamentoHistorico", qtdePagGuiaPagamentoHistorico);
        
        sessao.setAttribute("qtdePagDebitoACobrar", qtdePagDebitoACobrar);
        sessao.setAttribute("qtdePagDebitoACobrarAtual", qtdePagDebitoACobrarAtual);
        sessao.setAttribute("qtdePagDebitoACobrarHistorico", qtdePagDebitoACobrarHistorico);

        sessao.setAttribute("vlPagContasAtual", vlPagContasAtual);
        sessao.setAttribute("vlPagContasHistorico", vlPagContasHistorico);
        sessao.setAttribute("vlPagGuiaPagamentoAtual", vlPagGuiaPagamentoAtual);
        sessao.setAttribute("vlPagGuiaPagamentoHistorico", vlPagGuiaPagamentoHistorico);
        sessao.setAttribute("vlPagDebitoACobrarAtual", vlPagDebitoACobrarAtual);
        sessao.setAttribute("vlPagDebitoACobrarHistorico", vlPagDebitoACobrarHistorico);
        
        return retorno;
    }
}