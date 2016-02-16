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
package gcom.cobranca.contratoparcelamento;

import gcom.cobranca.CobrancaForma;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.gui.cobranca.contratoparcelamento.DebitosClienteHelper;
import gcom.relatorio.cobranca.RelatorioEmitirContratoParcelamentoPorClienteBean;
import gcom.relatorio.cobranca.contratoparcelamento.FiltrarRelatorioEmitirContratoParcelamentoPorClienteHelper;
import gcom.relatorio.cobranca.contratoparcelamento.RelatorioEmitirExtratoContratoParcelamentoPorClienteBean;
import gcom.relatorio.cobranca.contratoparcelamento.EmitirExtratoContratoParcelamentoPorClienteHelper;
import gcom.relatorio.cobranca.contratoparcelamento.RelatorioManterContratoParcelamentoPorClienteBean;
import gcom.relatorio.cobranca.contratoparcelamento.RelatorioManterContratoParcelamentoRDBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * Declara��o p�blica de servi�os do Session Bean de ControladorCliente
 * 
 * @author Paulo Diniz
 * @created 18 de mar�o 2011
 */
public interface ControladorContratoParcelamentoLocal extends javax.ejb.EJBLocalObject {


	/**
	 * Inseri um ContratoParcelamento e suas QuantidadesPrestacoes associadas
	 * 
	 * [UC1133] Inserir Resolu��o de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param contrato
	 * @param parcelas que o contrato possui
	 * @throws ControladorException
	 */
	public Integer inserirContratoParcelamentoRD(ContratoParcelamentoRD contrato, List<QuantidadePrestacoes> parcelas, Usuario usuarioLogados)
			throws ControladorException;
	
	/**
	 * Atualizar um ContratoParcelamento e suas QuantidadesPrestacoes associadas
	 * 
	 * [UC1134] Manter Resolu��o de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param contrato
	 * @param parcelas que o contrato possui
	 * @throws ControladorException
	 */
	public void atualizarContratoParcelamentoRD(ContratoParcelamentoRD contrato, List<QuantidadePrestacoes> parcelas, Usuario usuarioLogado)
		throws ControladorException;
	
	/**
	 * Pesquisa ContratoParcelamentoRD por numero
	 * 
	 * [UC1133] Inserir Resolu��o de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param numero do contrato
	 * @throws ControladorException
	 */
	public ContratoParcelamentoRD pesquisarContratoParcelamentoRDPorNumero(String numero)
		throws ControladorException;
	
	/**
	 * Pesquisa ContratoParcelamentoRD por id
	 * 
	 * [UC1133] Inserir Resolu��o de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param numero do contrato
	 * @throws ControladorException
	 */
	public ContratoParcelamentoRD pesquisarContratoParcelamentoRDPorID(Integer id)
		throws ControladorException;
	
	/**
	 * Atualizar um ContratoParcelamento e suas QuantidadesPrestacoes associadas
	 * 
	 * [UC1134] Manter Resolu��o de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param contrato
	 * @param parcelas que o contrato possui
	 * @throws ControladorException
	 */
	public boolean excluirContratosParcelamentoRD(String[] ids, Usuario usuarioLogado)
		throws ControladorException;
	
	/**
	 * Verifica Resolu��o de Diretoria associada a um Contrato Parcelamento n�o Encerrado
	 * 
	 * [UC1134]  Atualizar Resolu��o de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 */
	public boolean verificaResolucaoDiretoriaAssociadaContratoParcelamentoNaoEncerrado(Integer id)
		throws ControladorException;
	
	/**
	 * Gerar Relatorio dos ContratoParcelamentoRD selecionados
	 * 
	 * [UC1134] Manter Resolu��o de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param ids dos Contratos
	 * @param filtro da Busca
	 * @throws ControladorException
	 */
	public List<RelatorioManterContratoParcelamentoRDBean> gerarRelatorioManterContratoParcelamentoRD(FiltroContratoParcelamentoRD filtro)
		throws ControladorException;
	
	/**
	 * Permite a inclus�o de contrato de parcelamento por cliente.
	 * 
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param contrato
	 * @throws ControladorException
	 */
	public Integer inserirContratoParcelamentoPorCliente(ContratoParcelamento contrato, Usuario usuarioLogado, 
			ContratoParcelamentoCliente clienteContrato, ContratoParcelamentoCliente clienteSuperiorContrato, 
			List<DebitosClienteHelper> listaDebitos, List<PrestacaoContratoParcelamento> listaDeParcelas, String indicadorDebitoAutomaticoAnterior)
			throws ControladorException;
	
	/**
	 * Verifica Conta Vinculada a um Contrato Parcelamento por Cliente Item
	 * 
	 * [UC1143]  Consultar Dados do Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/05/2011
	 * 
	 */
	public boolean verificaContaVinculadaAContratoParcel(Integer idConta, Integer idContrato) 
		throws ControladorException ;
	
	/**
	 * Gerar Relatorio dos ContratoParcelamentoPorCLiente selecionados
	 * 
	 * [UC1137] Manter Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param ids dos Contratos
	 * @param filtro da Busca
	 * @throws ControladorException
	 */
	public List<RelatorioManterContratoParcelamentoPorClienteBean> gerarRelatorioManterContratoParcelamentoPorCliente(FiltroContratoParcelamentoCliente filtro) throws ControladorException;
	
	/**
	 * Consultar Contrato de Parcelamento por Cliente Com Pagamento jah efetuado
	 * 
	 * [UC1143]  Consultar Dados do Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/05/2011
	 * 
	 */
	public boolean verificaContratoParcelComPagamentoFeito(Integer idContrato) throws ControladorException;
	
	/**
	 * Consultar Se Prestacao Ja Esta Paga
	 * 
	 * [UC1143]  Consultar Dados do Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/05/2011
	 * 
	 */
	public boolean verificaPrestacaoPaga(Integer idPrestacao) throws ControladorException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento
	 * [FS0003] � Validar contrato
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Object[] pesquisarDadosContratoParcelamento(String numeroContrato)
		throws ControladorException;
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento
	 * [FS0003] � Validar contrato
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Integer pesquisarDadosContratoParcelamentoNumeroParcelasPagas(Integer idContrato)
		throws ControladorException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento por cliente
	 * [FS0005] � Verificar exist�ncia de contratos para o cliente
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Collection<Object[]> pesquisarDadosContratoParcelamentoPorCliente(Integer idCliente)
		throws ControladorException;
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento por cliente
	 * [SB0002] � Informar Dados do Pagamento
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public Object[] pesquisarDadosContratoParcelamentoPorClienteSelecionado(Integer idContrato)
		throws ControladorException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Desvincula a guia de pagamento do contrato de parcelamento, 
	 * atualizando dados do contrato na tabela cobranca.CONTRATO_PARCEL_ITEM  
	 * 
	 * @author Mariana Victor
	 * @data 08/06/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarContratoParcelamentoItemDesvincularGuiaContrato(String[] idsGuias)
		throws ControladorException;
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * [SB0003] � Efetuar Pagamento da Parcela do Contrato de Parcelamento Por Cliente
	 * 
	 * @author Mariana Victor
	 * @since 06/06/2011
	 * */
	public Object[] efetuarPagamentoParcelaContratoParcelamentoPorCliente(InformarPagamentoContratoParcelamentoHelper helper, 
			Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * [UC1141] Emitir Contrato de Parcelamento por Cliente
	 * 
	 * Retorna os registros com os dados a serem impressos no relat�rio 
	 * 
	 * @author Mariana Victor
	 * @since 10/06/2011
	 * */
	public List<RelatorioEmitirContratoParcelamentoPorClienteBean> pesquisarDadosRelatorioContratoParcelamentoPorCliente(
			FiltrarRelatorioEmitirContratoParcelamentoPorClienteHelper helper) throws ControladorException;
	
	/**
	 * Permite a atualiza��o de contrato de parcelamento por cliente.
	 * 
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param contrato
	 * @throws ControladorException
	 */
	public void atualizaContratoParcelamentoPorCliente(ContratoParcelamento contrato, Usuario usuarioLogado, 
			ContratoParcelamentoCliente clienteContrato, ContratoParcelamentoCliente clienteSuperiorContrato, List<DebitosClienteHelper> listaDebitos, List<DebitosClienteHelper> listaDebitosAnterior, List<PrestacaoContratoParcelamento> listaDeParcelas)
			throws ControladorException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta o id da RD relacionada ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 29/06/2011
	 */
	public Integer pesquisarRDContratoParcelamento(Integer idContratoParcelamento) throws ControladorException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta os dados das contas vinculadas ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 02/07/2011
	 */
	public Collection<ContaValoresHelper> pesquisarDadosDasContasContratoParcelamento(Integer idContratoParcelamento) throws ControladorException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * [FS0008] � Verificar exist�ncia do contrato anterior
	 * 
	 * @author Mariana Victor
	 * @data 08/07/2011
	 */
	public Boolean verificarExistenciaContratoAnterior(String numeroContratoAnterior) throws ControladorException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * [FS0037] - Verificar situa��o do contrato anterior
	 * 
	 * Retorna a situa��o de parcelamento do contrato
	 * 
	 * @author Mariana Victor
	 * @data 08/07/2011
	 */
	public Boolean verificarSituacaoContratoAnterior(String numeroContratoAnterior) throws ControladorException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta os dados dos d�bitos a cobrar vinculados ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 26/07/2011
	 */
	public Collection<DebitoACobrar> pesquisarDadosDosDebitosACobrarContratoParcelamento(
			Integer idContratoParcelamento) throws ControladorException;
	
	/**
	 * [UC0184] Manter D�bito A Cobrar
	 * 
	 * Verifica se o d�bito a cobrar est� vinculado a um Contrato Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @date 28/07/2011
	 */
	public boolean verificaDebitoACobrarVinculadoContratoParcelamentoCliente(
			Integer idDebitoACobrar) throws ControladorException;
	
	/**
	 * [UC1200] Gerar Extrato de Contrato Parcelamento por Cliente
	 * 
	 * Este caso de uso permite gerar o extrato do contrato de parcelamento do cliente.
	 * 
	 * @author Mariana Victor
	 * @date 28/07/2011
	 */
	public void gerarExtratoDeContratoParcelamentoPorCliente (
			Integer idCliente, 
			List<PrestacaoContratoParcelamento> colecaoPrestacaoContratoParcelamento) 
		throws ControladorException;

	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * 1. O sistema obt�m os dados do contrato de parcelamento por cliente
	 * 
	 * @author Mariana Victor
	 * @data 30/07/2011
	 */
	public ContratoParcelamento obterDadosContratoParcelamento(Integer idContratoParcelamento) 
		throws ControladorException;

	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * 2.2.	Dados do Parcelamento 
	 * 
	 * @author Mariana Victor
	 * @data 30/07/2011
	 */
	public Collection<PrestacaoContratoParcelamento> obterDadosPrestacoesContratoParcelamento(Integer idContratoParcelamento)
		throws ControladorException;
	
	/**
	 *  [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * Retorna os registros com os dados a serem impressos no relat�rio 
	 * 
	 * @author Mariana Victor
	 * @throws ControladorException 
	 * @data 30/07/2011
	 * */
	public List<RelatorioEmitirExtratoContratoParcelamentoPorClienteBean> pesquisarDadosRelatorioEmitirExtratoContratoParcelamentoPorCliente(
			EmitirExtratoContratoParcelamentoPorClienteHelper helper,
			Collection<PrestacaoContratoParcelamentoHelper> colecaoHelper) throws ControladorException;
	
	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * 
	 * Retorna os dados do d�bito a cobrar caso exista algum pagamento para o mesmo.
	 * 
	 * @author Mariana Victor
	 * @data 03/08/2011
	 */
	public Object[] obterDadosDebitoACobrarPagoAMenor(Integer idDebitoACobrar) 
		throws ControladorException;

	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 05/08/2011
	 */
	public ContratoParcelamento pesquisarContratoParcelamentoCompleto(Integer idContrato, String numeroContrato) 
		throws ControladorException;
	
	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 06/08/2011
	 */
	public Integer pesquisarIdClientecontrato(Integer idContratoParcelamento) 
		throws ControladorException;
	
	/**
	 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 06/08/2011
	 */
	public ContratoParcelamentoCliente pesquisarClienteContrato(Integer idContratoParcelamento) 
		throws ControladorException;

	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 12/08/2011
	 */
	public ContratoParcelamentoCliente pesquisarClienteContrato(Integer idContratoParcelamento, Integer idCliente)
		throws ControladorException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 15/08/2011
	 */
	public Collection<ContratoParcelamentoCliente> pesquisarClienteContrato(
			Integer idContratoParcelamento,
			Short indicadorClienteSuperior) 
			throws ControladorException;

	/**
	 * [UC1141] Emitir Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @created 29/08/2011
	 * 
	 * @param idContratoParcelamento
	 * @exception ControladorException
	 */
	public Collection<ContratoParcelamentoItem> pesquisarContratoParcelamentoItem(
			Integer idContratoParcelamento, Integer idDocumentoTipo) throws ControladorException;
	
	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Calcula e retorna os valores das parcelas do contrato de parcelamento.
	 * 
	 * @author Mariana Victor
	 * @data 29/08/2011
	 */
	public InserirContratoParcelamentoValoresParcelasHelper calcularValoresParcelasContratoParcelamento(
			BigDecimal valorContaSelecaoTotal, BigDecimal acrescimo, BigDecimal valorDebitoACobrar, String indicadorDebitoAcresc,
			String indicadorParcelJuros, BigDecimal jurosBigDec, int numeroParcelInicial, int numeroParcelFinal) 
		throws ControladorException;

	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Calcula e retorna os valores das parcelas do contrato de parcelamento a partir de uma RD.
	 * 
	 * @author Mariana Victor
	 * @data 29/08/2011
	 */
	public InserirContratoParcelamentoValoresParcelasHelper calcularValoresParcelasContratoParcelamentoRD(
			BigDecimal valorContaSelecaoTotal, BigDecimal valorContaComAcrescimo, BigDecimal valorDebitoACobrar, String indicadorDebitoAcresc,
			String indicadorParcelJuros, ContratoParcelamento contratoParcelamento, 
			QuantidadePrestacoes quantidadePrestacoes) 
		throws ControladorException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 13/09/2011
	 */
	public CobrancaForma pesquisarFormaPagamentoRD(
			Integer idRD) 
		throws ControladorException;


	/**
	 * [UC1348] Excluir D�bito Autom�tico de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2012
	 */
	public String excluirDebitoAutomaticoParcelamentoPorCliente(Integer idContratoParcelamento,
			String codigoBanco, String codigoAgencia, Date dataOpcao)
			throws ControladorException;


	/**
	 * [UC1347] Inserir D�bito Autom�tico de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2012
	 */
	public String inserirDebitoAutomaticoParcelamentoPorCliente(Integer idContratoParcelamento,
			String codigoBanco, String codigoAgencia, String identificacaoCliente, Date dataOpcao)
			throws ControladorException;
	
}
