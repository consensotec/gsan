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
package gsan.cobranca.contratoparcelamento;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import gsan.cobranca.CobrancaDocumentoItem;
import gsan.cobranca.CobrancaForma;
import gsan.cobranca.bean.ContaValoresHelper;
import gsan.faturamento.debito.DebitoACobrar;
import gsan.util.ErroRepositorioException;


/**
 * @author Paulo Diniz
 * @created 18 de mar�o de 2011
 */
public interface IRepositorioContratoParcelamento {
	
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
	 * @throws ErroRepositorioException
	 */
	public ContratoParcelamentoRD pesquisarContratoParcelamentoRDPorNumero(String numero)
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa ContratoParcelamentoRD por id
	 * 
	 * [UC1134] Manter Resolu��o de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param numero do contrato
	 * @throws ErroRepositorioException
	 */
	public ContratoParcelamentoRD pesquisarContratoParcelamentoRDPorID(Integer id) throws ErroRepositorioException;
	
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
		throws ErroRepositorioException;
	
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
		throws ErroRepositorioException;
	
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
	public boolean verificaContratoParcelComPagamentoFeito(Integer idContrato) throws ErroRepositorioException;
	
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
	public boolean verificaPrestacaoPaga(Integer idPrestacao) throws ErroRepositorioException;
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento
	 * [FS0003] � Validar contrato
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Object[] pesquisarDadosContratoParcelamento(String numeroContrato) throws ErroRepositorioException;
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento
	 * [FS0003] � Validar contrato
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Integer pesquisarDadosContratoParcelamentoNumeroParcelasPagas(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento por cliente
	 * [FS0005] � Verificar exist�ncia de contratos para o cliente
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Collection<Object[]> pesquisarDadosContratoParcelamentoPorCliente(Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento por cliente
	 * [SB0002] � Informar Dados do Pagamento
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public Object[] pesquisarDadosContratoParcelamentoPorClienteSelecionado(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public ContratoParcelamento pesquisarContratoParcelamento(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0005] � Gerar Pagamento Guias Juros de Parcelamento e Guias de Acr�scimos
	 * 
	 * Seleciona a guia de juros ou de acr�scimos sobre contrato de parcelamento correspondente � parcela paga
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public Object[] pesquisarGuiaDeJurosOuComAcrescimos(Integer idContrato, Integer debitoTipo, Integer idPrestacao) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0007] � Gerar Pagamento
	 * 
	 * Calcula o valor j� pago para o item
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public BigDecimal pesquisarValorPagoItem(Integer idContrato, Integer idItem) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0011] � Atualizar Item da Negativa��o
	 * 
	 * Verifica se a negativa��o est� exclu�da
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Integer pesquisarCodigoExclusaoNegativacao(Integer idItemNegativacao) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] � Gerar Pagamento para Itens de D�bito do Contrato
	 * 
	 * Pesquisa itens de d�bito com valor pago a menor
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Object[] pesquisarItemDebitoValorPagoAMenor(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] � Gerar Pagamento para Itens de D�bito do Contrato
	 * 
	 * Pesquisa a conta com valor pago a menor
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Object[] pesquisarContaValorPagoAMenor(Integer idItem) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] � Gerar Pagamento para Itens de D�bito do Contrato
	 * 
	 * Pesquisa o valor da menor presta��o
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Object[] pesquisarValoMinimoPrestacoes(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] � Gerar Pagamento para Itens de D�bito do Contrato
	 * 
	 * Pesquisa os itens de d�bitos do tipo conta para gera��o de pagamento
	 * 
	 * @author Mariana Victor
	 * @data 07/06/2011
	 */
	public Collection<Object[]> pesquisarItensDebitoConta(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0003] � Efetuar Pagamento da Parcela do Contrato de Parcelamento Por Cliente
	 * 
	 * 1.8.1. Seleciona as guias de pagamento do contrato, atuais e sem pagamento 
	 * 
	 * @author Mariana Victor
	 * @data 08/06/2011
	 */
	public Collection<Object[]> pesquisarGuiasPagamentoContratoAtuaisSemPagamento(Integer idContrato) throws ErroRepositorioException;

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
	public void atualizarContratoParcelamentoItemDesvincularGuiaContrato(String[] idsGuias) throws ErroRepositorioException;

	/**
	 * [UC1141] Emitir Contrato de Parcelamento por Cliente
	 * 
	 * [SB0003] � Emitir Dados do Parcelamento
	 * 
	 * Pesquisa se a parcela j� foi paga 
	 * 
	 * @author Mariana Victor
	 * @data 13/06/2011
	 */
	public Boolean pesquisarParcelaPaga(Integer idPrestacao) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta Os Clientes Vinculados a um ContratoParcelamento
	 * 
	 * @author Paulo Diniz
	 * @data 13/06/2011
	 */
	public Boolean removerClientesVinculadosAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta Os Itens de D�bitos Atuais do Contrato de Parcelamento por Cliente
	 * 
	 * @author Paulo Diniz
	 * @data 13/06/2011
	 */
	public Boolean removerItensDebitosVinculadosAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta as PrestacaoContratoParcelamento vinculadas ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Paulo Diniz
	 * @data 13/06/2011
	 */
	public Boolean removerPrestacoesVinculadasAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta as Guias de Pagamento de 'Acrescimos por Impontualidade' vinculadas ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Paulo Diniz
	 * @data 13/06/2011
	 */
	public Boolean removerGuiasDePagtoDeAcrescImpontualidadeVinculadasAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta as Guias de Pagamento de 'Juros sobre Contrato' vinculadas ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Paulo Diniz
	 * @data 13/06/2011
	 */
	public Boolean removerGuiasDePagtoJurosSobreContratoVinculadasAContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta o id da RD relacionada ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 29/06/2011
	 */
	public Integer pesquisarRDContratoParcelamento(Integer idContratoParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta os dados das contas vinculadas ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 02/07/2011
	 */
	public Collection<ContaValoresHelper> pesquisarDadosDasContasContratoParcelamento(Integer idContratoParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * [FS0008] � Verificar exist�ncia do contrato anterior
	 * 
	 * @author Mariana Victor
	 * @data 08/07/2011
	 */
	public Boolean verificarExistenciaContratoAnterior(String numeroContratoAnterior) 
		throws ErroRepositorioException;
	
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
	public Boolean verificarSituacaoContratoAnterior(String numeroContratoAnterior) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] � Gerar Pagamento para Itens de D�bito do Contrato
	 * 
	 * Pesquisa o d�bito a cobrar com valor pago a menor
	 * 
	 * @author Mariana Victor
	 * @data 21/07/2011
	 */
	public Object[] pesquisarDebitoACobrarValorPagoAMenor(Integer idItem) throws ErroRepositorioException;

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0006] � Gerar Pagamento para Itens de D�bito do Contrato
	 * 
	 * Pesquisa os itens de d�bitos do tipo d�bito a cobrar para gera��o de pagamento
	 * 
	 * @author Mariana Victor
	 * @data 21/07/2011
	 */
	public Collection<Object[]> pesquisarItensDebitoACobrar(Integer idContrato) throws ErroRepositorioException;

	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta os dados dos d�bitos a cobrar vinculados ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 26/07/2011
	 */
	public Collection<DebitoACobrar> pesquisarDadosDosDebitosACobrarContratoParcelamento(Integer idContratoParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0184] Manter D�bito A Cobrar
	 * 
	 * Verifica se o d�bito a cobrar est� vinculado a um Contrato Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @date 28/07/2011
	 */
	public boolean verificaDebitoACobrarVinculadoContratoParcelamentoCliente(Integer idDebitoACobrar) 
		throws ErroRepositorioException;

	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * 1. O sistema obt�m os dados do contrato de parcelamento por cliente
	 * 
	 * @author Mariana Victor
	 * @data 30/07/2011
	 */
	public ContratoParcelamento obterDadosContratoParcelamento(Integer numeroContratoParcelamento) 
		throws ErroRepositorioException;

	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * 2.2.	Dados do Parcelamento 
	 * 
	 * @author Mariana Victor
	 * @data 30/07/2011
	 */
	public Collection<PrestacaoContratoParcelamento> obterDadosPrestacoesContratoParcelamento(Integer idContratoParcelamento) 
		throws ErroRepositorioException;

	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 30/07/2011
	 */
	public CobrancaDocumentoItem obterDocumentoCobrancaPrestacao(Integer idPrestacao) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta o Documento de Cobran�a vinculado ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 01/08/2011
	 */
	public Boolean removerDocumentoCobrancaVinculadoAContratoParcelamento(Integer idDocumentoCobranca) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Deleta os Itens de Documento de Cobran�a vinculados ao Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 01/08/2011
	 */
	public Boolean removerItemDocumentoCobrancaVinculadoAContratoParcelamento(Integer idItem) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * 
	 * Retorna os dados do d�bito a cobrar caso exista algum pagamento para o mesmo.
	 * 
	 * @author Mariana Victor
	 * @data 03/08/2011
	 */
	public Object[] obterDadosDebitoACobrarPagoAMenor(Integer idDebitoACobrar) 
		throws ErroRepositorioException;

	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 05/08/2011
	 */
	public ContratoParcelamento pesquisarContratoParcelamentoCompleto(Integer idContrato, String numeroContrato) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 06/08/2011
	 */
	public Integer pesquisarIdClientecontrato(Integer idContratoParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 06/08/2011
	 */
	public ContratoParcelamentoCliente pesquisarClienteContrato(Integer idContratoParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 12/08/2011
	 */
	public ContratoParcelamentoCliente pesquisarClienteContrato(Integer idContratoParcelamento, 
			Integer idCliente) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 15/08/2011
	 */
	public Collection<ContratoParcelamentoCliente> pesquisarClienteContrato(
			Integer idContratoParcelamento,
			Short indicadorClienteSuperior) 
		throws ErroRepositorioException;

	/**
	 * [UC1141] Emitir Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @created 29/08/2011
	 * 
	 * @param idContratoParcelamento
	 * @exception ErroRepositorioException
	 */
	public Collection<ContratoParcelamentoItem> pesquisarContratoParcelamentoItem(
			Integer idContratoParcelamento, Integer idDocumentoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 13/09/2011
	 */
	public CobrancaForma pesquisarFormaPagamentoRD(
			Integer idRD) 
		throws ErroRepositorioException;

	/**
	 * [UC1348] Excluir D�bito Autom�tico de Parcelamento por Cliente
	 * 
	 * [FS0005] - Verificar se o contrato parcelamento � d�bito autom�tico para os dados informados
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2012
	 */
	public Boolean verificarDebitoAutomaticoContratoParcelamento(Integer idContratoParcelamento)
			throws ErroRepositorioException;

	/**
	 * [UC1348] Excluir D�bito Autom�tico de Parcelamento por Cliente
	 * 
	 * [FS0005] - Verificar se o contrato parcelamento � d�bito autom�tico para os dados informados
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2012
	 */
	public Boolean verificarDebitoAutomaticoContratoParcelamentoBancoAgencia(Integer idContratoParcelamento,
			String codigoBanco, String codigoAgencia)
			throws ErroRepositorioException;

	/**
	 * [UC1348] Excluir D�bito Autom�tico de Parcelamento por Cliente
	 * 
	 * [FS0004] - Verificar data de op��o posterior j� informada
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2012
	 */
	public Boolean verificarDebitoAutomaticoContratoParcelamentoDataOpcao(Integer idContratoParcelamento,
			Date dataOpcao, boolean incluirIgual)
			throws ErroRepositorioException;
	
	/**
	 * [UC1348] Excluir D�bito Autom�tico de Parcelamento por Cliente
	 * 
	 * 2.1.	Remover os movimentos de d�bitos autom�ticos que ainda n�o foram enviados ao banco 
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2012
	 */
	public void removerMovimentosDebitoAutomaticoContratoParcelamento(Integer idContratoParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1348] Excluir D�bito Autom�tico de Parcelamento por Cliente
	 * 
	 * 2.2.	Atualiza a data da exclus�o com a data corrente 
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2012
	 */
	public void atualizarDataExclusaoDebitoAutomaticoContratoParcelamento(Integer idContratoParcelamento)
			throws ErroRepositorioException;
	

	/**
	 * [UC1348] Excluir D�bito Autom�tico de Parcelamento por Cliente
	 * 
	 * 2.3.	Atualiza o indicador de d�bito autom�tico da tabela CONTRATO_PARCELAMENTO para N�O 
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2012
	 */
	public void atualizarIndicadorDebitoAutomaticoContratoParcelamento(
			Integer idContratoParcelamento, Short indicadorDebitoAutomatico)
			throws ErroRepositorioException;

	/**
	 * [UC1348] Excluir D�bito Autom�tico de Parcelamento por Cliente
	 * 
	 * [FS0001] - Verificar exist�ncia de contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2012
	 */
	public Boolean verificarExistenciaContratoParcelamento(Integer idContratoParcelamento)
			throws ErroRepositorioException;

	/**
	 * [UC1347] Inserir D�bito Autom�tico de Parcelamento por Cliente
	 * 
	 * [FS0006] - Verificar data de op��o j� exclu�da
	 * 
	 * @author Mariana Victor
	 * @data 22/06/2012
	 */
	public Boolean verificarDebitoAutomaticoContratoParcelamentoDataOpcaoExcluida(
			Integer idContratoParcelamento, Date dataOpcao)
			throws ErroRepositorioException;

	/**
	 * [UC1347] Inserir D�bito Autom�tico de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 22/06/2012
	 */
	public Collection<Object[]> obterPrestacoesContratoParcelamento(
			Integer idContratoParcelamento) 
			throws ErroRepositorioException;
	
}
