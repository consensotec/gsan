/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.cobranca;

import gcom.arrecadacao.ArrecadadorContratoTarifa;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.AvisoDeducoes;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gcom.batch.auxiliarbatch.CobrancaDocumentoControleGeracao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ConsultarTransferenciasDebitoHelper;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.cobranca.bean.DadosPesquisaCobrancaDocumentoHelper;
import gcom.cobranca.bean.EmitirDocumentoCobrancaBoletimCadastroHelper;
import gcom.cobranca.bean.EmitirDocumentoCobrancaHelper;
import gcom.cobranca.bean.FiltrarDocumentoCobrancaHelper;
import gcom.cobranca.bean.FiltrarRelacaoParcelamentoHelper;
import gcom.cobranca.bean.FiltroSupressoesReligacoesReestabelecimentoHelper;
import gcom.cobranca.bean.PesquisarQtdeRotasSemCriteriosParaAcoesCobranca;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.parcelamento.ParcDesctoInativVista;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gcom.cobranca.parcelamento.ParcelamentoFaixaValor;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.FaturaItem;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoGerarOSHelper;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper;
import gcom.gui.relatorio.cobranca.FiltroRelatorioDocumentosAReceberHelper;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.relatorio.cobranca.RelatorioAcompanhamentoAcoesCobrancaHelper;
import gcom.relatorio.cobranca.RelatorioBoletimMedicaoCobrancaHelper;
import gcom.relatorio.cobranca.parcelamento.RelacaoParcelamentoRelatorioHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.filtro.Filtro;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Interface para o repositório de cobranca
 * 
 * @author Rafael Santos
 * @since 02/01/2006
 */
public interface IRepositorioCobranca {

	/**
	 * Faz parte de [UC0178] Religar Automaticamente Imóvel Cortado Author:
	 * Rafael Santos Data: 02/01/2006 Pesquisa os imoveis cortados há 60 dias ou
	 * mais a data do último dia do mês de faturamento
	 * 
	 * @return Colecção de Matriculas
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarImoveisCortados(String situacaoEsgotoLigado,
			String situacaoAguaCortado, Date anoMesReferenciaFaturamento)
			throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0178] Religar Automaticamente Imóvel Cortado Author:
	 * Rafael Santos Data: 02/01/2006 Caso o imovel possua hidrometro na ligação
	 * de água e o tipo do ultimo consumo faturado tenha sido real
	 * 
	 * @param id
	 *            Matricula do Imovel
	 * @param anoMesFaturamento
	 *            Ano Mes Faturamento
	 * @param consumoTipoReal
	 *            Tipo de Consumo Real
	 * @param ligacaoTipoLigacaoAgua
	 *            Tipo de Ligação Agua
	 * @return Consumo Historico do Imovel
	 * @throws ErroRepositorioException
	 *             Erro no Repositorio
	 */
	public String pesquisarImoveisHidrometroAguaConsumoFaturadoReal(String id,
			String anoMesFaturamento, String consumoTipoReal,
			String ligacaoTipoLigacaoAgua) throws ErroRepositorioException;

	/**
	 * [UC0178] Religar Automaticamente Imóvel Cortado Auhtor: Rafael Santos
	 * Data: 03/01/2006
	 * 
	 * @param id
	 *            Matricula do Imovel
	 * @param situacaoAguaLigado
	 *            Situação Agua
	 * @param dataReligacaoAgua
	 *            Data Religacao Agua
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void religarImovelCortado(String id, String situacaoAguaLigado,
			Date dataReligacaoAgua) throws ErroRepositorioException;

	/**
	 * [UC0067] Obter Débito do Imóvel ou Cliente Obtem os débitos de um imóvel
	 * Author: Rafael Santos Data: 03/01/2006
	 * 
	 * @param id
	 *            Matricula do Imovel
	 * @param contaSituacaoNormal
	 *            Situação Normal de Conta
	 * @param contaSituacaoRetificada
	 *            Situação Retificada de Conta
	 * @param contaSituacaoIncluida
	 *            Situação Incluída de Conta
	 * @param anoMesInicialReferenciaDebito
	 *            Ano Mes Inicial Referencia Debito
	 * @param anoMesFinalReferenciaDebito
	 *            Ano Mes Final Referencia Debito
	 * @param anoMesInicialVecimentoDebito
	 *            Ano Mes Inicial Vencimento Debito
	 * @param anoMesFinalVencimentoDebito
	 *            Ano Mes Inicial Vencimento Debito
	 * @return Coleção de Contas do Imovel
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasImoveis(Collection idsImoveis,
			int indicadorPagamento, int indicadorConta,
			String contaSituacaoNormal, String contaSituacaoRetificada,
			String contaSituacaoIncluida, String contaSituacaoParcelada,
			String anoMesInicialReferenciaDebito,
			String anoMesFinalReferenciaDebito,
			Date anoMesInicialVecimentoDebito, Date anoMesFinalVencimentoDebito,
			int indicadorDividaAtiva)
			throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter Débito do Imóvel ou Cliente Obtem os débitos
	 * de um cliente Author: Rafael Santos Data: 05/01/2006
	 * 
	 * @param idsContas
	 *            Coleção de Ids das Contas
	 * @param contaSituacaoNormal
	 *            Situação Normal de Conta
	 * @param contaSituacaoRetificada
	 *            Situação Retificada de Conta
	 * @param contaSituacaoIncluida
	 *            Situação Incluída de Conta
	 * @param anoMesInicialReferenciaDebito
	 *            Ano Mes Inicial Referencia Debito
	 * @param anoMesFinalReferenciaDebito
	 *            Ano Mes Final Referencia Debito
	 * @param anoMesInicialVecimentoDebito
	 *            Ano Mes Inicial Vencimento Debito
	 * @param anoMesFinalVencimentoDebito
	 *            Ano Mes Inicial Vencimento Debito
	 * @return Coleção de Contas do Imovel
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasCliente(Integer idCliente,
			Short relacaoTipo, int indicadorPagamento, int indicadorConta,
			String contaSituacaoNormal, String contaSituacaoRetificada,
			String contaSituacaoIncluida, String contaSituacaoParcelada,
			String anoMesInicialReferenciaDebito,
			String anoMesFinalReferenciaDebito,
			Date anoMesInicialVecimentoDebito, Date anoMesFinalVencimentoDebito, 
			int indicadorDividaAtiva)
			throws ErroRepositorioException;
	
	/**
	 * [UC0067] Obter Débito do Imóvel ou Cliente Obtem os débitos de um imóvel
	 * Author: Rafael Santos Data: 03/01/2006
	 * 
	 * @param id
	 *            Matricula do Imovel
	 * @param contaSituacaoNormal
	 *            Situação Normal de Conta
	 * @param contaSituacaoRetificada
	 *            Situação Retificada de Conta
	 * @param contaSituacaoIncluida
	 *            Situação Incluída de Conta
	 * @param anoMesInicialReferenciaDebito
	 *            Ano Mes Inicial Referencia Debito
	 * @param anoMesFinalReferenciaDebito
	 *            Ano Mes Final Referencia Debito
	 * @param anoMesInicialVecimentoDebito
	 *            Ano Mes Inicial Vencimento Debito
	 * @param anoMesFinalVencimentoDebito
	 *            Ano Mes Inicial Vencimento Debito
	 * @return Coleção de Contas do Imovel
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasImovel(Integer idImovel,
				int indicadorPagamento, int indicadorConta,
				String contaSituacaoNormal, String contaSituacaoRetificada,
				String contaSituacaoIncluida, String contaSituacaoParcelada,
				String anoMesInicialReferenciaDebito,
				String anoMesFinalReferenciaDebito,
				Date anoMesInicialVecimentoDebito, Date anoMesFinalVencimentoDebito, int indicadorDividaAtiva)
				throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter Débito do Imóvel ou Cliente Obtem o Valor
	 * Total dos Pagamentos da Conta Author: Rafael Santos Data: 05/01/2006
	 * 
	 * @param idConta
	 *            Id Conta
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarValorTotalPagamentoMenorDataPagamento(
			String idConta) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter Débito do Imóvel ou Cliente Obtem o Valor
	 * Total dos Pagamentos da Guia de Pagamento Author: Rafael Santos Data:
	 * 07/01/2006
	 * 
	 * @param idGuiaPagamento
	 *            Id Guia Pagamento
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarValorTotalGuiaPagamentoMenorDataGuiaPagamento(
			String idGuiaPagamento) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter Débito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 05/01/2006
	 * 
	 * @param idImovel
	 *            Matricula do Imovel
	 * @param situacaoNormal
	 *            situacao de debito credito
	 * @return Coleção de Debitos A Cobrar
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitosACobrarImovel(String idImovel,
			String situacaoNormal) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter Débito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 05/01/2006 Pesquisa os ID dos imoveis dos cliente
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @param relacaoTipo
	 *            Relação Tipo Cliente Imovel
	 * @return Coleção de Debitos A Cobrar do Cliente
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIDImoveisClienteImovel(String codigoCliente,
			Short relacaoTipo) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter Débito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 07/01/2006 Pesquisa os ID dos clientes contas
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @param relacaoTipo
	 *            Relação Tipo Cliente Imovel
	 * @return ID dos Imvoeis Cliente Conta
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIDImoveisClienteConta(String codigoCliente,
			Short relacaoTipo) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter Débito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 06/01/2006 Colecção de Debitos a Cobrar de Cliente
	 * 
	 * @param colecaoIdImoveis
	 *            Coleção de ID dos Imoveis
	 * @param situacaoNormal
	 *            Situação Normal
	 * @return Coleção de Debitos A Cobrar do Cliente
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitosACobrarCliente(Collection idsImoveis,
			String situacaoNormal) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter Débito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 05/01/2006 Colecção de Creditos a Realizar de Cliente
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @param situacaoNormal
	 *            Situação Normal
	 * @return Coleção de Creditos A Realizar do Cliente
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCreditosARealizarCliente(Collection idsImoveis,
			String situacaoNormal) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter Débito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 05/01/2006
	 * 
	 * @param idImovel
	 *            Matricula do Imovel
	 * @return Coleção de Creditos A Realizar
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCreditosARealizarImovel(String idImovel,
			String situacaoNormal) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter Débito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 07/01/2006 Colecção de Guias de Pagamento do Cliente
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @prarm dataVencimentoInicial Data Vencimento Inicial
	 * @parm dataVencimentoFinal Data Vecimento Final
	 * @param situacaoNormal
	 *            Situação Normal
	 * @param clienteRelacaoTipo
	 *            Relação Cliente Tipo
	 * @return Coleção de Guias de Pagamento do Cliente
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiasPagamentoCliente(Integer idCliente, int indicadorPagamento,
			String situacaoNormal, Short clienteRelacaoTipo,
			Date dataVencimentoInicial, Date dataVencimentoFinal)
			throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0216] Calcular Acréscimo por Impontualidade Rafael Santos
	 * Data: 09/01/2006 Dados do Indices Acrescimo Impontualidade
	 * 
	 * @param anoMesReferenciaDebito
	 *            Ano Mês de Referencia de Débito
	 * @return O Indices Acrescimos por Impontualidade
	 * @throws ErroRepositorioException
	 */
	public IndicesAcrescimosImpontualidade pesquisarIndiceAcrescimoImpontualidade(
			int anoMesReferenciaDebito) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0216] Calcular Acréscimo por Impontualidade Rafael Santos
	 * Data: 09/01/2006 Pesquisa os dados do Indices Acrescimo Impontualidade
	 * menor ao ano mes referencia
	 * 
	 * @param anoMesReferenciaDebito
	 *            Ano Mês de Referencia de Débito
	 * @return O Indices Acrescimos por Impontualidade
	 * @throws ErroRepositorioException
	 */
	public IndicesAcrescimosImpontualidade pesquisarMenorIndiceAcrescimoImpontualidade(
			int anoMesReferenciaDebito) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter Débito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 07/01/2006
	 * 
	 * @param idImovel
	 *            Matricula do Imovel
	 * @prarm dataVencimentoInicial Data Vencimento Inicial
	 * @param situacaoNormal
	 *            Situação Normal
	 * @parm dataVencimentoFinal Data Vecimento Final
	 * @return Coleção de Guias de Pagamentos
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiasPagamentoImovel(Integer idImovel, int indicadorPagamento,
			String situacaoNormal, Date dataVencimentoInicial,
			Date dataVencimentoFinal) throws ErroRepositorioException;

	/**
	 * [UC0200] Inserir Débito Automático [FS0004] Verificar Data de Opção
	 * posterior já informada
	 * 
	 * @author Roberta Costa
	 * @created 04/01/2006
	 * 
	 * @param matriculaImovel
	 *            Matrícula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public String verificarDataOpcao(String matriculaImovel, Date dataOpcao,
			String identificadorCliente, String codigoAgencia)
			throws ErroRepositorioException;

	/**
	 * [UC0201] Excluir Débito Automático [FS0004] Verificar Data de Opção
	 * posterior já informada
	 * 
	 * @author Roberta Costa
	 * @created 05/01/2006
	 * 
	 * @param matriculaImovel
	 *            Matrícula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public String verificarDataOpcaoExclusao(String matriculaImovel,
			Date dataOpcao, String identificadorCliente)
			throws ErroRepositorioException;

	/**
	 * [UC0200] Inserir Débito Automático Verificar se o Imóvel já é Débito
	 * Automático
	 * 
	 * @author Roberta Costa
	 * @created 05/01/2006
	 * 
	 * @param matriculaImovel
	 *            Matrícula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public String verificarDebitoAutomatico(String matriculaImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0200] Inserir Débito Automático Atualiza a data da exclusão com a data
	 * corrente em Débio Automático
	 * 
	 * @author Roberta Costa
	 * @created 05/01/2006
	 * 
	 * @param matriculaImovel
	 *            Matrícula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void atualizarDataExclusao(String matriculaImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0200] Inserir Débito Automático
	 * 
	 * @author Roberta Costa
	 * @created 04/01/2006
	 * 
	 * @param matriculaImovel
	 *            Matrícula do Imovel
	 * @param codigoBanco
	 *            Código do Banco
	 * @param codigoAgencia
	 *            Código da Agência
	 * @param identificacaoCliente
	 *            Identificação do Cliente no Banco
	 * @param dataOpcao
	 *            Data da Opção
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void inserirDebitoAutomatico(DebitoAutomatico debitoAutomatic)
			throws ErroRepositorioException;

	/**
	 * [UC0200] Inserir Débito Automático Atualiza o indicador de débio
	 * automático em Imóvel
	 * 
	 * @author Roberta Costa
	 * @created 05/01/2006
	 * 
	 * @param matriculaImovel
	 *            Matrícula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void atualizarIndicadorDebitoAutomatico(String matriculaImovel,
			Integer indicadorDebito) throws ErroRepositorioException;

	/**
	 * [UC0201] Remover Débito Automático Verificar se o Imóvel já é Débito
	 * Automático para o mesmo Banco e Agência
	 * 
	 * @author Roberta Costa
	 * @created 09/01/2006
	 * 
	 * @param matriculaImovel
	 *            Matrícula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public String verificarDebitoAutomaticoBancoAgencia(String codigoBanco,
			String codigoAgencia) throws ErroRepositorioException;

	/**
	 * [UC0246] Executar Atividade de Ação de Cobrança Pesquisa uma coleção de
	 * CobrancaAcaoAtividadeCronograma
	 * 
	 * @author Pedro Alexandre
	 * @created 01/02/2006
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<CobrancaAcaoAtividadeCronograma> pesquisarCobrancaAcaoAtividadeCronograma()
			throws ErroRepositorioException;

	/**
	 * [UC0246] Executar Atividade de Ação de Cobrança Pesquisa uma coleção de
	 * CobrancaAcaoAtividadeComando
	 * 
	 * @author Pedro Alexandre
	 * @created 01/02/2006
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<CobrancaAcaoAtividadeComando> pesquisarCobrancaAcaoAtividadeComando()
			throws ErroRepositorioException;

	/**
	 * Consultar Dados do Cliente Imovel Vinculado Auhtor: Rafael Santos Data:
	 * 23/01/2006
	 * 
	 * @param inscricaoImovel
	 *            Inscrição do Imovel
	 * @return Dados do Imovel Vinculado
	 * @throws ControladorException
	 */
	public Object[] consultarDadosClienteImovelUsuario(Imovel imovel)
			throws ErroRepositorioException;

	/**
	 * Consultar Matriculas dos Imoveis Vinculados do Imovel condominio Auhtor:
	 * Rafael Santos Data: 23/01/2006 [UC0179] Consultar Historico Medição
	 * Indiviualizada
	 * 
	 * @param consumoHistorico
	 *            Consumo Historico
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarConsumoHistoricoImoveisVinculados(
			ConsumoHistorico consumoHistorico) throws ErroRepositorioException;

	/**
	 * Consultar Consumo Tipo do Consumo Historico Auhtor: Rafael Santos Data:
	 * 23/01/2006
	 * 
	 * @param consumoHistorico
	 *            Consumo Historico
	 * @return Dados do Consumo Tipo
	 * @throws ControladorException
	 */
	public Object[] consultarDadosConsumoTipoConsumoHistorico(
			ConsumoHistorico consumoHistorico) throws ErroRepositorioException;

	/**
	 * Consultar Consumo Historico da Medicao Individualizada Auhtor: Rafael
	 * Santos Data: 23/01/2006 [UC0179] Consultar Historico Medição
	 * Indiviualizada
	 * 
	 * @param imovel
	 *            Imovel
	 * @param ligcaoTipo
	 *            Tipo de Ligacação
	 * @param anoMesFaturamento
	 *            Ano Mes Faturamento
	 * @return
	 * @throws ControladorException
	 */
	public Object[] obterConsumoHistoricoMedicaoIndividualizada(Imovel imovel,
			LigacaoTipo ligacaoTipo, int anoMesReferencia)
			throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito Atualiza o debitocreditosituacao em
	 * conta
	 * 
	 * @author Fernanda Paiva
	 * @created 14/02/2006
	 * 
	 * @param DebitoCreditoSituacaoAnterior
	 *            DebitoCreditoSituacaoAtual idConta
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void atualizarSituacaoConta(String codigoConta, int situacaoAtual,
			int anoMesReferenciaContabil) throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito Atualiza o debitocreditosituacao em
	 * guia pagamento
	 * 
	 * @author Fernanda Paiva
	 * @created 15/02/2006
	 * 
	 * @param DebitoCreditoSituacaoAtual
	 *            idGuiaPagamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void atualizarSituacaoGuiaPagamento(String codigoGuiaPagamento,
			int situacaoAtualGuia, int anoMesReferenciaContabil)
			throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito Atualiza o parcelamento
	 * 
	 * @author Fernanda Paiva
	 * @created 15/02/2006
	 * 
	 * @param idParcelamento
	 *            motivo parcelamentoSituacao
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void atualizarParcelamento(Integer codigoParcelamento,
			Integer parcelamentoSituacao, String motivo, Integer usuarioId)
			throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito Atualiza o debitocreditosituacao em
	 * debitoacobrar
	 * 
	 * @author Fernanda Paiva
	 * @created 16/02/2006
	 * 
	 * @param DebitoCreditoSituacaoAtual
	 *            idDebitoACobrar
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void atualizarSituacaoDebitoACobrar(String codigoDebitoACobrar,
			int situacaoAtualDebito, int anoMesReferenciaContabil)
			throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Debito Atualiza o debitocreditosituacao em
	 * creditoarealizar
	 * 
	 * @author Fernanda Paiva
	 * @created 16/02/2006
	 * 
	 * @param DebitoCreditoSituacaoAtual
	 *            idCreditoARealizar
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void atualizarSituacaoCreditoARealizar(
			String codigoCreditoARealizar, int situacaoAtualCredito,
			int anoMesReferenciaContabil) throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Débitos Remove débitos a cobrar categoria
	 * referentes ao parcelamento
	 * 
	 * @author Fernanda Karla
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void removerDebitoACobrarCategoriaDoParcelamento(Integer idDebito)
			throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Débitos Remove débitos a cobrar referentes
	 * ao parcelamento
	 * 
	 * @author Fernanda Karla
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void removerDebitoACobrarDoParcelamento(Integer codigoImovel,
			Integer codigoParcelamento) throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Débitos Pesquisa débito a cobrar
	 * referentes ao parcelamento
	 * 
	 * @author Fernanda Karla
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarDoParcelamento(
			Integer codigoImovel, Integer codigoParcelamento)
			throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Débitos Remove creditos a realizar
	 * referentes ao parcelamento
	 * 
	 * @author Fernanda Karla
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void removerCreditoARealizarDoParcelamento(Integer codigoImovel,
			Integer codigoParcelamento) throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Débitos Remove guia pagamento referente ao
	 * parcelamento
	 * 
	 * @author Fernanda Karla
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void removerGuiaPagamentoDoParcelamento(Integer codigoParcelamento) throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Débitos Remove guia pagamento referentes
	 * ao parcelamento
	 * 
	 * @author Fernanda Karla
	 * @created 20/02/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void removerGuiaPagamentoCobrancaDoParcelamento(
			Integer idGuiaPagamento) throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * [SF0003] - Processar Pagamento de Documento de Cobrança
	 * 
	 * @author Sávio Luiz
	 * @created 16/02/2006
	 * 
	 * @param matriculaImovel
	 *            Matrícula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarCobrancaDocumentoItem(Integer idImovel,
			int numeroSequencialDocumento) throws ErroRepositorioException;
	
	/**
	 * [UCXXXX] - Emitir Documento de Cobrança
	 *
	 * @author Rafael Corrêa
	 * @date 02/09/2009
	 *
	 * @param idCobrancaDocumento
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<ContaGeral> pesquisarCobrancaDocumentoItem(Integer idCobrancaDocumento) throws ErroRepositorioException;

	/**
	 * Inseri a cobrança situação historico na base passando a coleção de
	 * cobrança situação historico
	 * 
	 * [UC0177] Informar Situacao Especial de Cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 20/03/2006
	 * 
	 * @param collectionCobrancaSituacaoHistorico
	 * @return
	 */
	public void inserirCobrancaSituacaoHistorico(
			Collection collectionCobrancaSituacaoHistorico)
			throws ErroRepositorioException;

	/**
	 * Atualiza o ano mes de cobranca situação historico
	 * 
	 * [UC0156] Informar Situacao Especial Faturamento
	 * 
	 * @author Sávio Luiz
	 * @date 17/03/2006
	 * 
	 * @param situacaoEspecialFaturamentoHelper
	 * @throws ErroRepositorioException
	 */
	public void atualizarAnoMesCobrancaSituacaoHistorico(
			SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper,
			Integer anoMesReferencia, Collection colecaoImoveis, Integer idFaturamentoSituacaoConsumo)
			throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter Débito do Imovel ou Cliente Author: Rafael
	 * Santos Data: 05/01/2006 Pesquisa os ID das Contas dos cliente
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @param relacaoTipo
	 *            Relação Tipo Cliente Imovel
	 * @return Coleção de Debitos A Cobrar do Cliente
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIDContasClienteConta(String codigoCliente,
			Short relacaoTipo) throws ErroRepositorioException;

	/**
	 * Atualiza o numero de parcelamento e reparcelamento na tabela de imovel
	 * 
	 * Desfazer Parcelamento de Debitos
	 * 
	 * @author Fernanda Paiva
	 * @date 29/04/2006
	 * 
	 * @param imovel
	 * @param numeroParcelamento
	 * @param numeroReparcelamento
	 * @param numeroReparcelamentoConsecutivo
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosParcelamentoImovel(Integer codigoImovel,
			Short numeroParcelamento, Short numeroReparcelamento,
			Short numeroReparcelamentoConsecutivo)
			throws ErroRepositorioException;

	/**
	 * [UC0314] - Desfazer Parcelamentos por Entrada Não Paga Author: Fernanda
	 * Paiva Data: 02/05/2006
	 * 
	 * Obtem os parcelamentos de débitos efetuados no mês de faturamento
	 * corrente e que estejam com situação normal
	 * 
	 * @param situacaoNormal,
	 *            anoMesFaturamento
	 * @return
	 */
	public Collection pesquisarParcelamentosSituacaoNormalNoMes(
			String parcelamentoSituacao, Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	/**
	 * [UC0314] - Desfazer Parcelamentos por Entrada Não Paga Author: Fernanda
	 * Paiva Data: 02/05/2006
	 * 
	 * Obtem as guias de pagamentos dos parcelamentos de débitos efetuados no
	 * mês de faturamento corrente e que estejam com situação normal
	 * 
	 * @param situacaoNormal,
	 *            anoMesFaturamento
	 * @return
	 */
	public Collection pesquisarGuiaPagamentoDoParcelamento(String parcelamento)
			throws ErroRepositorioException;

	/**
	 * [UC0314] - Desfazer Parcelamentos por Entrada Não Paga Author: Fernanda
	 * Paiva Data: 02/05/2006
	 * 
	 * Obtem os pagamentos para a guia de pagamentos dos parcelamentos de
	 * débitos efetuados no mês de faturamento corrente e que estejam com
	 * situação normal
	 * 
	 * @param situacaoNormal,
	 *            anoMesFaturamento
	 * @return
	 */
	public Collection pesquisarPagamentoParaGuiaPagamentoDoParcelamento(
			String pagamento,Integer idImovel) throws ErroRepositorioException;

	/**
	 * [UC0317] Manter Critério de Cobrança
	 * 
	 * Este caso de uso remove as linhas da cobrança critério passando a colecao
	 * de cobrança criterio
	 * 
	 * [SB0002] Excluir Critério de Cobrança
	 * 
	 * @author Sávio luiz
	 * @created 11/05/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void removerCobrancaCriterioLinha(String[] idscobrancaCriterio)
			throws ErroRepositorioException;

	public Collection gerarRelacaoDebitos(String idImovelCondominio,
			String idImovelPrincipal, String idNomeConta,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,

			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal,

			String idImovelPerfil, String idPocoTipo,
			String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo,
			String idSituacaoEspecialCobranca, String idEloAnormalidade,
			String areaConstruidaInicial, String areaConstruidaFinal,
			String idCadastroOcorrencia, String idConsumoTarifa,
			String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial,
			String setorComercialFinal, String quadraInicial,
			String quadraFinal, String loteOrigem, String loteDestno,
			String cep, String logradouro, String bairro, String municipio,
			String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa, String ordenacao,
			int quantidadeImovelInicio, String indicadorCpfCnpj, String cpfCnpj

	) throws ErroRepositorioException;

	/**
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * O sistema ordena a lista de documentos de cobrança por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), quadra (CBDO_NNQUADRA), lote e sublote
	 * (IMOV_NNLOTE e IMOV_SUBLOTE da tabela IMOVEL com IMOV_ID da tabela
	 * DOCUMENTO_COBRANCA)
	 * 
	 * @author Raphael Rossiter
	 * @data 26/05/2006
	 * 
	 * @param idBairro,
	 *            idLogradouro
	 * @return Collection<CobrancaDocumento>
	 */
	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaEmitir(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * O sistema ordena a lista de documentos de cobrança por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), rota (ROTA_ID), sequencial da rota (IMOV_NNSEQUENCIALROTA)e 
	 * cobranca documento (CBDO_ID)
	 * 
	 * @author Raphael Rossiter
	 * @data 27/06/2007
	 * 
	 * @param idBairro,
	 *            idLogradouro
	 * @return Collection<CobrancaDocumento>
	 */
	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaEmitirPorRota(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio)
			throws ErroRepositorioException;

	/**
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * Seleciona os ítns do documento de cobrança correspondentes a conta e
	 * ordenar por ano/mês de referência da conta
	 * 
	 * @author Raphael Rossiter
	 * @data 26/05/2006
	 * 
	 * @param CobrancaDocumento
	 * @return Collection<CobrancaDocumentoItem>
	 */
	public Collection<CobrancaDocumentoItem> selecionarCobrancaDocumentoItemReferenteConta(
			CobrancaDocumento cobrancaDocumento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * O sistema ordena a lista de documentos de cobrança por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), quadra (CBDO_NNQUADRA), lote e sublote
	 * (IMOV_NNLOTE e IMOV_SUBLOTE da tabela IMOVEL com IMOV_ID da tabela
	 * DOCUMENTO_COBRANCA)
	 * 
	 * @author Rafael Corrêa
	 * @data 02/09/2009
	 * 
	 * @return Collection<CobrancaDocumento>
	 */
	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaRelatorio(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando)
			throws ErroRepositorioException;

	/**
	 * 
	 * Gerar Relação de Débitos
	 * 
	 * [UC0227] Gerar Relação de Débitos
	 * 
	 * @author Rafael Santos
	 * @date 12/06/2006
	 * 
	 */
	public Integer obterQuantidadaeRelacaoImoveisDebitos(
			String idImovelCondominio, String idImovelPrincipal,
			String idNomeConta, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua,
			String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa)
			throws ErroRepositorioException;

	/**
	 * Retorna o count do resultado da pesquisa de Cobranca Cronograma
	 * 
	 * pesquisarCobrancaCronogramaCount
	 * 
	 * @author Flávio Cordeiro
	 * @date 14/06/2006
	 * 
	 * @return Integer retorno
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarCobrancaCronogramaCount(Filtro filtro)
			throws ErroRepositorioException;

	public void removerCobrancaCronograma(Integer idGrupoCronogramaMes)
			throws ErroRepositorioException;

	/**
	 * 
	 * Consultar Relação de Debitos do Imovel Consulta o Consumo Medio do Imovel
	 * 
	 * [UC0227] - Gerar Relção de Débitos
	 * 
	 * @author Rafael Santos
	 * @date 15/06/2006
	 * 
	 * @param imovelId
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarConsumoMedioConsumoHistoricoImovel(Integer imovelId)
			throws ErroRepositorioException;

	/**
	 * Gerar Relatório de Critério de Cobrança
	 * 
	 * Pesquisa as linhas de critério de cobrança através do id do critério de
	 * cobrança
	 * 
	 * @author Rafael Corrêa
	 * @data 09/08/2006
	 * 
	 */
	public Collection pesquisarCobrancaCriterioLinha(Integer idCriterioCobranca)
			throws ErroRepositorioException;

	/**
	 * Gerar Relatório de Perfil de Parcelamento
	 * 
	 * Pesquisa os Parcelamentos Desconto Antiguidade através do id de Perfil de
	 * Parcelamento
	 * 
	 * @author Rafael Corrêa
	 * @data 22/08/2006
	 */
	public Collection pesquisarParcelamentoDescontoAntiguidade(
			Integer idParcelamentoPerfil) throws ErroRepositorioException;

	/**
	 * Gerar Relatório de Perfil de Parcelamento
	 * 
	 * Pesquisa os Parcelamentos Desconto Inatividade através do id de Perfil de
	 * Parcelamento
	 * 
	 * @author Rafael Corrêa
	 * @data 22/08/2006
	 */
	public Collection pesquisarParcelamentoDescontoInatividade(
			Integer idParcelamentoPerfil) throws ErroRepositorioException;

	/**
	 * Gerar Relatório de Perfil de Parcelamento
	 * 
	 * Pesquisa os Reparcelamentos Consecutivos através do id de Perfil de
	 * Parcelamento
	 * 
	 * @author Rafael Corrêa
	 * @data 22/08/2006
	 */
	public Collection pesquisarReparcelamentoConsecutivo(
			Integer idParcelamentoPerfil) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite iniciar processos batch de faturamento ou
	 * cobrança previdamento comandados e processos mensais ou eventuais
	 * 
	 * [UC0001] - Iniciar Processo
	 * 
	 * Este subfluxo inicia os processo batch de cobrança do sistema
	 * 
	 * [SB0002] - Iniciar Process de Cobrança Comandado
	 * 
	 * @author Rodrigo Silveira
	 * @date 17/08/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CobrancaAcaoAtividadeCronograma> pesquisarCobrancaAcaoAtividadeCronogramaComandadosNaoRealizados()
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite iniciar processos batch de faturamento ou
	 * cobrança previdamento comandados e processos mensais ou eventuais
	 * 
	 * [UC0001] - Iniciar Processo
	 * 
	 * Este subfluxo inicia os processo batch de cobrança do sistema
	 * 
	 * [SB0002] - Iniciar Process de Cobrança Comandado
	 * 
	 * @author Rodrigo Silveira
	 * @date 17/08/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CobrancaAcaoAtividadeComando> pesquisarCobrancaAcaoAtividadeCronogramaEventuaisComandadosNaoRealizados()
			throws ErroRepositorioException;

	/**
	 * [UC0476] Emitir Documento de Cobrança - Ordem de Corte
	 * 
	 * O sistema ordena a lista de documentos de cobrança por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), quadra (CBDO_NNQUADRA), lote e sublote
	 * (IMOV_NNLOTE e IMOV_SUBLOTE da tabela IMOVEL com IMOV_ID da tabela
	 * DOCUMENTO_COBRANCA)
	 * 
	 * @author Ana Maria
	 * @data 07/09/2006
	 * 
	 * @param Collection
	 *            <CobrancaDocumento>
	 * @return Collection<CobrancaDocumento>
	 */
	public Collection<EmitirDocumentoCobrancaHelper> pesquisarCobrancaDocumentoOrdemCorte(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0476] Emitir Documento de Cobrança
	 * 
	 * O sistema ordena a lista de documentos de cobrança por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), rota (ROTA_ID), sequencial da rota
	 * (IMOV_NNSEQUENCIALROTA)e cobranca documento (CBDO_ID)
	 * 
	 * @author Raphael Rossiter
	 * @data 07/08/2007
	 * 
	 * @return Collection<EmitirDocumentoCobrancaHelper>
	 */
	public Collection<EmitirDocumentoCobrancaHelper> pesquisarCobrancaDocumentoOrdemCortePorRota(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio)
			throws ErroRepositorioException ;
	
	
	
	/**
	 * [UC0582] Emitir Boletim de Cadastro
	 * 
	 * O sistema ordena a lista de documentos de cobrança por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), quadra (CBDO_NNQUADRA), lote e sublote
	 * (IMOV_NNLOTE e IMOV_SUBLOTE da tabela IMOVEL com IMOV_ID da tabela
	 * DOCUMENTO_COBRANCA)
	 * 
	 * @author Rafael Corrêa
	 * @data 16/05/2007
	 * 
	 * @param Collection
	 *            <CobrancaDocumento>
	 * @return Collection<CobrancaDocumento>
	 */
	public Collection<EmitirDocumentoCobrancaBoletimCadastroHelper> pesquisarCobrancaDocumentoBoletimCadastro(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio)
			throws ErroRepositorioException;

	/**
	 * Este caso de consulta os dados do imovel, esse metodo consulta os
	 * documentos de cobrança do imovel
	 * 
	 * [UC0472] - Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 18/09/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarImovelDocumentosCobranca(Integer idImovel,
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * Este caso de consulta os dados do imovel, esse metodo consulta a
	 * quantidade de documentos de cobrança do imovel
	 * 
	 * [UC0472] - Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 18/09/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer consultarQuantidadeImovelDocumentosCobranca(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Este caso de consulta os dados do imovel, esse metodo consulta a
	 * quantidade de documentos de itens de cobrança do imovel
	 * 
	 * [UC0472] - Consultar Imovel
	 * 
	 * @author Rafael Santos
	 * @date 18/09/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer consultarQuantidadeImovelDocumentosItemCobranca(
			Integer idImovel) throws ErroRepositorioException;

	/**
	 * Pesquisa os dados do parcelamento necessários para o relatório através do
	 * id do parcelamento
	 * 
	 * @author Rafael Corrêa
	 * @date 25/09/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarParcelamentoRelatorio(Integer idParcelamento)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os itens do parcelamento necessários para o relatório através do
	 * id do parcelamento
	 * 
	 * @author Rafael Corrêa
	 * @date 25/09/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarParcelamentoItemPorIdParcelamentoRelatorio(
			Integer idParcelamento) throws ErroRepositorioException;

	/**
	 * Obtem os percentuais de desconto por tempo de inatividade
	 * 
	 * [UC0214] - Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 2/10/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParcelamentoDescontoInatividade obterPercentualDescontoInatividade(
			Integer idPerfilParc, int qtdeMeses)
			throws ErroRepositorioException;

	/**
	 * Consultar Serviços/Atualizações do documento de cobraça
	 * 
	 * 
	 * [UC0349] - Emitir Documento de cobrança - Ordem de Fiscalização
	 * 
	 * @author Ana Maria
	 * @date 18/10/2006
	 * 
	 * @param idDocumentoCobranca
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarServioAtualizacao(Integer idDocumentoCobranca)
			throws ErroRepositorioException;

	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @created 23/10/2006
	 * 
	 * @param conta
	 * @throws ErroRepositorioException
	 */
	public void associarContaParcelamento(Conta conta)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * Retorna os CBCM_ID da tabela COBRANCA_GRUPO_CRONOGRAMA_MES
	 * 
	 * @author Rafael Santos
	 * @date 16/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaGrupoCronogramaMes()
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * Retorna os CBCR_ID da tabela COBRANCA_ACAO_CRONOGRAMA com CBCM_ID da
	 * tabela COBRANCA_GRUPO_CRONOGRAMA_MES
	 * 
	 * @author Rafael Santos
	 * @date 16/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaAcaoCronograma(
			int idCobrancaGrupoCronogramaMes) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * Retorna CAAC_TMREALIZACAO do COBRANCA_ATIVIDADE_ACAO_CRONOGRAMA
	 * 
	 * @author Rafael Santos
	 * @date 16/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(
			int idCobrancaAcaoCronograma, int idCobrancaAtividade)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * Retorna os DOTP_ID da tabela COBRANCA_ACAO com CBAC_ID de
	 * COBRANCA_ACAO_CRONOGRAMA
	 * 
	 * @author Rafael Santos
	 * @date 16/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaAcao(int idCobrancaAcao)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0001] - Processar Documentos Cobrança
	 * 
	 * Retorna os CBDO_ID da tabela COBRANCA_DOCUMENTO com CAAC_ID da tabela
	 * COBRANCA_ATIVIDADE_ACAO_CRONOGRAMA
	 * 
	 * @author Rafael Santos,Sávio Luiz
	 * @date 17/10/2006,28/05/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection<DadosPesquisaCobrancaDocumentoHelper> pesquisarCobrancaDocumento(
			int idCobrancaAtividadeAcaoCronograma)
			throws ErroRepositorioException; 

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0002] - Determinar Situação da Ação de Cobrança
	 * 
	 * Retorna os ORSE_ID da tabela ORDEM_SERVICO com CBDO_ID da tabela
	 * COBRANCA_DOCUMENTO
	 * 
	 * @author Rafael Santos,Sávio Luiz
	 * @date 17/10/2006,28/05/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarOrdemServico(
			int idDocumentoCobranca)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0007] - Determinar Situação da Ordem de Serviço
	 * 
	 * Retorna os AMEN_ICEXECUCAO da tabela ATENDIMENTO_MOTIVO_ENCERRAMENTO com
	 * AMEN_ID da tabela ORDEM_SERVIÇO
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAtendimentoMotivoEncerramento(
			int idAtendimentoMotivoEncerramento)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna os CNTA_ID,GPAG_ID,DBAC_ID da tabela COBRANCA_DOCUMENTO_ITEM com
	 * CBDO_ID da tabela COBRANCA_DOCUMENTO
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaDocumentoItem(int idCobrancaDocumento)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna os CNTG_ID da tabela COBRANCA_GERAL com CNTA_ID da tabela
	 * COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaGeral(int idConta)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna os DCST_IDATUAL,CNHI_DTCANCELAMENTO da tabela CONTA_HISTORICO com
	 * CNTA_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarContaHistorico(int idContaHistorico)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna os DCST_IDATUAL,CNTA_DTCANCELAMENTO da tabela CONTA com CNTA_ID
	 * da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarConta(int idConta)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna os PARC_TM_PARCELAMENTO da tabela PARCELAMENTO com PARCcom
	 * CNTA_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarParcelamentoConta(int idConta,
			int idParcelamentoSituacao) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna os GPGE_ID da tabela GUIA_PAGAMENTO_GERAL com GPAG_ID da tabela
	 * COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiaPagamentoGeral(int idGuiaPagamento)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna os DCST_IDATUAL,GPGE_DTCANCELAMENTO da tabela
	 * GUIA_PAGAMENTO_HISTORICO com GPAG_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarGuiaPagamentoHistorico(
			int idGuiaPagamentoHistorico) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna os DCST_IDATUAL,GPAG_DTCANCELAMENTO da tabela GUIA_PAGAMENTO com
	 * CNTA_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarGuiaPagamento(int idGuiaPagamento)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna os PARC_TM_PARCELAMENTO da tabela PARCELAMENTO com GPAG_ID da
	 * tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarParcelamentoGuiaPagamento(int idGuiaPagamento,
			int idParcelamentoSituacao) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna os DAGE_ID da tabela DEBITO_A_COBRAR_GERAL com DBAC_ID da tabela
	 * COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoACobrarGeral(int idDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna os DCST_IDATUAL,DAGE_DTCANCELAMENTO da tabela
	 * DEBITO_A_COBRAR_HISTORICO com DBAC_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDebitoACobrarHistorico(
			int idDebitoACobrarHistorico) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna os DCST_IDATUAL,GPAG_DTCANCELAMENTO da tabela DEBITO_A_COBRAR com
	 * DBAC_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDebitoACobrar(int idDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna os PARC_TM_PARCELAMENTO da tabela PARCELAMENTO com DBAC_ID da
	 * tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos,Sávio Luiz
	 * @date 17/10/2006,29/05/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarParcelamentoDebitoACobrar(int idDebitoACobrar,
			int idParcelamentoSituacao) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna o Menor PGHI_DTPAGAMENTO da tabela PAGAMENTO_HISTORICO com
	 * CNTA_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMenorDataPagamentosContaHistorico(int idContaHistorico)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna o Menor PGMT_DTPAGAMENTO da tabela PAGAMENTO com CNTA_ID da
	 * tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMenorDataPagamentosConta(int idConta)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna o Menor PGHI_DTPAGAMENTO da tabela PAGAMENTO_HISTORICO com
	 * GPAG_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMenorDataPagamentosGuiaPagamentoHistorico(
			int idGuiaPagamentoHistorico) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna o Menor PGMT_DTPAGAMENTO da tabela PAGAMENTO com GPAG_ID da
	 * tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMenorDataPagamentosGuiaPagamento(int idGuiaPagamento)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna o Menor PGHI_DTPAGAMENTO da tabela PAGAMENTO_HISTORICO com
	 * DBAC_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMenorDataPagamentosDebitoACobrarHistorico(
			int idDebitoACobrarHistorico) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0003] - Determinar Situação do Débito do Item do Documento de Cobrança
	 * 
	 * Retorna o Menor PGMT_DTPAGAMENTO da tabela PAGAMENTO_HISTORICO com
	 * DBAC_ID da tabela COBRANCA_DOCUMENTO_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMenorDataPagamentosDebitoACobrar(int idDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0005] - Determinar Situação Predominante do Débito do Documento de
	 * Cobrança
	 * 
	 * Retorna o CBCT_PCVLMINIMOPGPARCCANC, CBCBT_PCQTMINIMOPGPARCCANC da tabela
	 * COBRANCA_CRITERIO com CBCT_ID da tabela COBRANCA_DOCUMENTO
	 * 
	 * @author Rafael Santos
	 * @date 18/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaCriterio(int idCobrancaCriterio)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0004] - Atualizar item do documento de cobrança
	 * 
	 * Atualizar os COBRANCA_DOCUMENT_ITEM
	 * 
	 * @author Rafael Santos
	 * @date 19/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public void atualizarCobrancaDocumentoItem(
			Collection colecaoCobrancaDocumentoItem)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0004] - Processar Documento de Cobrança
	 * 
	 * Atualizar os COBRANCA_DOCUMENTO
	 * 
	 * @author Rafael Santos
	 * @date 19/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public void atualizarCobrancaDocumento(Collection colecaoCobrancaDocumento)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0001] - Processar Documentos de Cobrança
	 * 
	 * Retorna os dados do Imovel
	 * 
	 * @author Rafael Santos
	 * @date 19/10/2006
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosImovel(int idImovel)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0001] - Processar Documento de Cobrança
	 * 
	 * Atualizar os COBRANCA_ACAO_ATIVIDADE_CRONOGRAMA
	 * 
	 * @author Rafael Santos
	 * @date 25/10/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarCobrancaAcaoAtividadeCronograma(
			int idCobrancaAcaoAtividadeCrongrama)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0006] - Processar Ação com Ordens de Serviço
	 * 
	 * Retorna os ORSE_ID da tabela ORDEM_SERVICO com SVTP_ID da tabela
	 * COBRANCA_ACAO e ORSE_TMGERACAO entre CAAC_DTPREVISTA do Emitir e do
	 * Encerrar
	 * 
	 * @author Rafael Santos
	 * @date 25/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicos(int idServicoTipo,
			Date dataPrevistaAtividadeEncerrar,
			Date dataPrevistaAtividadeEmitir, int indice)
			throws ErroRepositorioException;

	/**
	 * [UC0214] - Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 31/10/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParcelamentoFaixaValor obterParcelamentoFaixaValor(
			Integer idParcelamentoQtdePrestacao, BigDecimal valorFaixa)
			throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * [SF0003] - Processar Pagamento de Documento de Cobrança
	 * 
	 * @author Sávio Luiz
	 * @created 16/02/2006
	 * 
	 * @param matriculaImovel
	 *            Matrícula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Object[] pesquisarParmsCobrancaDocumento(Integer idImovel,
			int numeroSequencialDocumento) throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com código de Barras [SB0005] - Processar
	 * Recebimento de Acréscimos por Inpontualidade Autor: Sávio Luiz
	 * Data:06/11/2006
	 */
	public Collection pesquisarCobrancaDocumentoItemComConta(
			Integer idCobrancaDocumento) throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com código de Barras [SB0005] - Processar
	 * Recebimento de Acréscimos por Inpontualidade Autor: Sávio Luiz
	 * Data:06/11/2006
	 */
	public Collection pesquisarCobrancaDocumentoItemComGuiaPagamento(
			Integer idCobrancaDocumento) throws ErroRepositorioException;

	/**
	 * [UC0178] Processar Pagamentos com código de Barras Auhtor: Sácio Luiz
	 * Data: 06/11/2006
	 * 
	 */
	public void atualizarGuiaPagamento(Collection idsGuiaPagamento,
			Date dataVencimento) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * O sistema exclui o resumo das ações de cobrança correspondente ao
	 * cronograma de ação de cobrança que esta sendo processado
	 * 
	 * @author Rafael Santos
	 * @date 08/11/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public void deletarResumoCobrancaAcao(int idCobrancaAcaoCronograma)
			throws ErroRepositorioException;

	/**
	 * retorna o objeto ResolucaoDiretoria com a maior data Vigência inicial
	 * 
	 * [UC0214] - Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 08/11/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ResolucaoDiretoria> pesquisarResolucaoDiretoriaMaiorDataVigenciaInicio()
			throws ErroRepositorioException;

	/**
	 * Obtem a condição referente à qtde de reparcelamentos consecutivos já
	 * realizadas para o perfil do parcelamento para o imóvel
	 * 
	 * a partir da tabela PARCELAMENTO_QUANTIDADE_REPARCELAMENTO com
	 * PCPF_ID=PCPF_ID da tabela PARCELAMENTO_PERFIL e
	 * PQTR_QTMAXIMAREPARCELAMENTO igual ou menor que
	 * IMOV_NNREPARCELAMENTOCONSECUTVOS, caso mais de uma ocorrencia seja
	 * selecionada, escolher a que tiver o maior valor de
	 * PQTR_QTMAXIMAREPARCELAMENTO
	 * 
	 * [UC0214] - Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 28/11/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParcelamentoQuantidadeReparcelamento obterQtdeReparcelamentoPerfil(
			Integer idPerfilParc, Short numeroReparcelamentoConsecutivos)
			throws ErroRepositorioException;

	/**
	 * [UC0213] Desfazer Parcelamento Débitos Remove ClienteGuiaPagamento
	 * referentes ao parcelamento
	 * 
	 * @author Vivianne Sousa
	 * @created 28/11/2006
	 * 
	 * @param idImovel
	 *            idParcelamento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public void removerClienteGuiaPagamentoDoParcelamento(
			Integer idGuiaPagamento) throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisa o critério de cobrança linha definido para a rota
	 * 
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0001] Gerar Atividade de
	 * Ação de Cobrança para os Imóveis do Cliente
	 * 
	 * @author Leonardo Vieira
	 * @created 12/12/2006
	 * 
	 * @param idRota
	 * @param idCobrancaAcao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public CobrancaCriterio pesquisarCriterioCobrancaRota(Integer idRota,
			Integer idCobrancaAcao) throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisa o critério de cobrança linha definido para o critério de
	 * cobrança
	 * 
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0001] Gerar Atividade de
	 * Ação de Cobrança para os Imóveis do Cliente
	 * 
	 * 
	 * @author Leonardo Vieira
	 * @created 12/12/2006
	 * 
	 * @param idCobrancaCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CobrancaCriterioLinha> pesquisarCobrancaCriterioLinhaCriterio(
			Integer idCobrancaCriterio) throws ErroRepositorioException;
		
	public CobrancaCriterio pesquisarCobrancaCriterioIdCriterio(
			Integer idCobrancaCriterio) throws ErroRepositorioException;

	/**
	 * Pesquisa o documento de cobranca da acao precedente (2.3.1)
	 * 
	 * [UC0251] Gerar Atividade de Ação de Cobrança 
	 * [SB0003] Gerar Atividade de Ação de Cobrança o Imóvel
	 * 
	 * @author Francisco do Nascimento
	 * @date 22/12/2008
	 *
	 * @param idImovel
	 * @param idServicoTipo
	 * @param indicadorExecucao
	 * @param dataEncerramento
	 * @return Id do documento de cobrança
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDocumentoCobrancaAcaoPrecedente(Integer idDocumentoCobrancaAcaoPrecedente,
			Date dataMinimaEmissaoRealizacaoAcaoPrecente) throws ErroRepositorioException;

	public Collection<Integer> pesquisarDocumentoCobrancaRelativoAcaoPrecedente(
			Integer idImovel, Integer idDocumentoTipo, Date dataEmissao,
			Date dataEmissaoValidade) throws ErroRepositorioException;

	public Collection pesquisarImovelCobrancaSituacao(Integer idImovel)
			throws ErroRepositorioException;

	public DebitoTipo pesquisarDebitoTipo(Integer idDebitoTipo)
			throws ErroRepositorioException;

	public CobrancaAcaoAtividadeCronograma pesquisarCobrancaAcaoAtividadeCronograma(
			Integer idCronogramaAtividadeAcaoCobranca)
			throws ErroRepositorioException;

	public CobrancaAcaoAtividadeComando pesquisarCobrancaAcaoAtividadeComando(
			Integer idCobrancaAcaoAtividadeComando)
			throws ErroRepositorioException;

	public Collection pesquisarCobrancaDocumentoItemContaGuiaPagamentoDebitoACobrar(
			Integer idCobrancaDocumento) throws ErroRepositorioException;

	public CobrancaDocumento pesquisarCobrancaDocumento(Integer idImovel,
			Integer idDocumentoTipo) throws ErroRepositorioException;

	public CobrancaAcaoAtividadeCronograma pesquisarCobrancaAcaoAtividadeCronogramaId(
			Integer idCobrancaAcaoAtividadeCronograma)
			throws ErroRepositorioException;

	/**
	 * [UC0314] - Desfazer Parcelamentos por Entrada Não Paga Author: Raphael
	 * Rossiter Data: 28/12/2006
	 * 
	 * Obtem as contas dos parcelamentos de débitos efetuados no mês de
	 * faturamento corrente e que estejam com situação normal
	 * 
	 * @param situacaoNormal,
	 *            anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaDoParcelamento(Integer parcelamento)
			throws ErroRepositorioException;

	/**
	 * [UC0314] - Desfazer Parcelamentos por Entrada Não Paga Author: Raphael
	 * Rossiter Data: 28/12/2006
	 * 
	 * Obtem os pagamentos para a conta dos parcelamentos de débitos efetuados
	 * no mês de faturamento corrente e que estejam com situação normal
	 * 
	 * @param conta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentoParaContaDoParcelamento(String idConta)
			throws ErroRepositorioException;

	/**
	 * verifica se conta tem debito cobrado (CNTA_ID ocorre na tabela
	 * DEBITO_COBRADO)
	 * 
	 * [UC0214] Efetuar Parcelamento Debito [SB0011] Verificar Única Fatura
	 * 
	 * @author Vivianne Sousa
	 * @created 15/02/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Conta verificarContaDebitoCobrado(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * obtem o consumo médio do imovel CSHI_NNCONSUMOCONSUMOMEDIO da tabla
	 * CONSUMO_HISTORICO com IMOV_ID = IMOV_ID da tabela CONTA e o maior mês/ano
	 * de consumo(CSHI_AMFATURAMENTO)
	 * 
	 * [UC0214] Efetuar Parcelamento Debito [SB0011] Verificar Única Fatura
	 * 
	 * @author Vivianne Sousa
	 * @created 15/02/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterConsumoMedioImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * (DBTP_ID da tabela DEBITO_COBRADO com CNTA_ID = CNTA_ID da conta a ser
	 * parcelada ocorrendo na tabela FISCALIZACAO_SITUACAO_SERVICO_A_COBRAR)
	 * 
	 * [UC0214] Efetuar Parcelamento Debito [SB0011] Verificar Única Fatura
	 * 
	 * @author Vivianne Sousa
	 * @created 15/02/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterIdDebitoTipoDeFiscalizacaoSituacaoServicoACobrar(
			Integer idConta) throws ErroRepositorioException;

	/**
	 * DBCB_NNPRESTACAO da tabela DEBITO_COBRADO com CNTA_ID = CNTA_ID da conta
	 * e DBTP_ID da tabela DEBITO_COBRADO com CNTA_ID = CNTA_ID ocorrendo na
	 * tabela FISCALIZACAO_SITUACAO_SERVICO_A_COBRAR
	 * 
	 * 
	 * [UC0214] Efetuar Parcelamento Debito [SB0011] Verificar Única Fatura
	 * 
	 * @author Vivianne Sousa
	 * @created 15/02/2007
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterNumeroPrestacaoDebitoCobrado(Integer idConta)
			throws ErroRepositorioException;

	/**
	 * Metodo criado para pesquisar os parcelamentos q tenham juros e nao tenha
	 * criado o debito dos juros DBTP_ID = 44
	 * 
	 * @author Flávio Cordeiro
	 * @date 23/02/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarParcelamentosSemDebitos()
			throws ErroRepositorioException;

	/**
	 * Pesquisa a colecao de ação de cobrança passando o id da acao precedente
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * @created 27/02/2007
	 * 
	 * @param idCobracaoAcao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesqsuisarAcaoCobrancaPelaPrecedente(
			Integer idCobracaoAcao) throws ErroRepositorioException;

	/**
	 * Pesquisa a colecao de ação de cobrança passando o id da acao precedente
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * @created 27/02/2007
	 * 
	 * @param idCobracaoAcao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarIdDocumentoCobranca(Integer idImovel,
			Integer idDocumentoTipo, Date dataEmissao)
			throws ErroRepositorioException;

	/**
	 * Obtém a menor data de pagamento para as guias de pagamento
	 * 
	 * [UC0302] Gerar Débitos a Cobrar de Acréscimos por Impontualidade
	 * 
	 * @author Pedro Alexandre
	 * @date 19/03/2007
	 * 
	 * @param idGuiaPagamento
	 * @param idImovel
	 * @param idDebitoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Date pesquisarMenorDataPagamentoGuiaPagamento(
			Integer idGuiaPagamento, Integer idImovel, Integer idDebitoTipo)
			throws ErroRepositorioException;

	/**
	 * obtem o numero de Consumo Faturado Mes CSHI_NNCONSUMOFATURADOMES da
	 * tabela CONSUMO_HISTORICO com IMOV_ID = IMOV_ID da tabela CONTA e o maior
	 * mês/ano de consumo(CSHI_AMFATURAMENTO)
	 * 
	 * [UC0214] Efetuar Parcelamento Debito [SB0011] Verificar Única Fatura
	 * 
	 * @author Vivianne Sousa
	 * @created 19/03/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterNumeroConsumoFaturadoMes(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * verificar se a(s) conta(s) parceladas já estão no histórico [UC0252]
	 * Desfazer Parcelamentos de Debito
	 * 
	 * @author Vivianne Sousa
	 * @created 09/04/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection verificarContaHistoricoParcelamento(Integer idImovel,Integer idParcelamento)
			throws ErroRepositorioException;

	/**
	 * verificar se a(s) debito(s) a cobrar parcelados já estão no histórico
	 * [UC0252] Desfazer Parcelamentos de Debito
	 * 
	 * @author Vivianne Sousa
	 * @created 09/04/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection verificarDebitoACobrarHistoricoParcelamento(
			Integer idImovel,Integer idParcelamento) throws ErroRepositorioException;

	/**
	 * verificar se a(s) credito(s) a realizar utilizados no parcelados já estão
	 * no histórico [UC0252] Desfazer Parcelamentos de Debito
	 * 
	 * @author Vivianne Sousa
	 * @created 09/04/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection verificarCreditoARealizarHistoricoParcelamento(
			Integer idImovel,Integer idParcelamento) throws ErroRepositorioException;

	/**
	 * [UC0XXX] Emitir Aviso de Cobrança
	 * 
	 * Seleciona os itens do documento de cobrança correspondentes a guia
	 * pagamento
	 * 
	 * @author Sávio Luiz
	 * @data 09/04/2007
	 * 
	 * @param CobrancaDocumento
	 * @return Collection<CobrancaDocumentoItem>
	 */
	public Collection<Object[]> selecionarDadosCobrancaDocumentoItemReferenteGuiaPagamento(
			CobrancaDocumento cobrancaDocumento)
			throws ErroRepositorioException;

	/**
	 * retorna referenciaContabil da conta cancelada por retificação [UC0252]
	 * Desfazer Parcelamentos de Debito
	 * 
	 * @author Vivianne Sousa
	 * @created 13/04/2007
	 * 
	 * @param idImovel
	 * @param anoMesReferencia
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaCanceladaRetificacao(Integer idImovel,
			int anoMesReferencia) throws ErroRepositorioException;

	/**
	 * retorna o objeto ParcelamentoFaixaValor com o valor do debito(valorFaixa)
	 * com desconto maior q o da faixa e menor que próxima faixa
	 * 
	 * [UC0575] - Emitir Parcelamento em Atraso
	 * 
	 * @author Sávio Luiz
	 * @date 14/04/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosParcelamentoComMaiorTimestemp(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * [SF0003] - Processar Pagamento de Documento de Cobrança
	 * 
	 * @author Ana Maria
	 * @created 13/04/2007
	 * 
	 * @param idCliente
	 * @param numeroSequencialDocumento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarCobrancaDocumentoItemCliente(Integer idCliente,
			int numeroSequencialDocumento) throws ErroRepositorioException;

	/**
	 * Consulta o id e a situação da ordem de serviço associada ao documento de
	 * cobrança passado como parâmetro
	 * 
	 * @author Sávio Luiz
	 * @created 13/04/2007
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Object[] pesquisarDadosOrdemServicoDocumentoCobranca(
			Integer idDocumentoCobranca) throws ErroRepositorioException;

	/**
	 * [UC0394] - Gerar Débitos a Cobrar de Doações
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 09/05/2007
	 * 
	 * @param colecaoRotas,
	 *            Integer anoMesReferenciaDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdsParcelamentosItemDebitoACobrar(
			Collection idsDebitoACobrar) throws ErroRepositorioException;

	/**
	 * [UC0394] - Gerar Débitos a Cobrar de Doações
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 09/05/2007
	 * 
	 * @param colecaoRotas,
	 *            Integer anoMesReferenciaDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdsCobrancaDocumentoItemDebitoACobrar(
			Collection idsDebitoACobrar) throws ErroRepositorioException;

	/**
	 * [UC0394] - Gerar Débitos a Cobrar de Doações
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 09/05/2007
	 * 
	 * @param colecaoRotas,
	 *            Integer anoMesReferenciaDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarCobrancaDocumentoItemDebitoACobrar(
			Collection idsDocumentoItemDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC0394] - Gerar Débitos a Cobrar de Doações
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 09/05/2007
	 * 
	 * @param colecaoRotas,
	 *            Integer anoMesReferenciaDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarParcelamentosItemDebitoACobrar(
			Collection idsParcelamentosItemDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * Pesquisa os dados de cobranca documento agrupado para pegar a quantidade
	 * e o valor dos documentos
	 * 
	 * @author Sávio Luiz
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosCobrancaDocumentoAgrupadoPorDataPrevista(
			int idCobrancaAtividadeAcaoCronograma)
			throws ErroRepositorioException;

	/**
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * Pesquisa os dados de cobranca documento agrupado para pegar a quantidade
	 * e o valor dos documentos
	 * 
	 * @author Sávio Luiz
	 * @date 17/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosCobrancaDocumentoAgrupadoPorDataComando(
			int idCobrancaAtividadeAcaoCronograma)
			throws ErroRepositorioException;

	/**
	 * 
	 * atualiza o sequencial de conta impressão
	 * 
	 * @author Sávio Luiz
	 * @date 18/05/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarSequencialCobrancaDocumentoImpressao(
			Map<Integer, Integer> mapAtualizaSequencial)
			throws ErroRepositorioException;

	/**
	 * Pesquisar relação de protocolos de documentos de cobrança do cronograma
	 * 
	 * @author Ana Maria
	 * @date 15/05/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarProtocoloDocumentoCobrancaCronograma(
			Integer idCobrancaAcaoAtividadeCronograma)
			throws ErroRepositorioException;

	/**
	 * Pesquisar relação de protocolos de documentos de cobrança do cronograma
	 * 
	 * @author Ana Maria
	 * @date 21/05/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarProtocoloDocumentoCobrancaEventual(
			Integer idCobrancaAcaoAtividadeComand)
			throws ErroRepositorioException;
	
	/**
	 * Recupera a coleção de OS para o encerramento
	 * 
	 * [UC0478] - Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0007] - Determinar Situação da Ordem de Serviço
	 * 
	 * @author Sávio Luiz
	 * @date 28/05/2007
	 * 
	 * @throws ControladorException
	 */
	public void atualizarParmsOS(Collection colecaoIdsOS,Integer idMotivoEncerramento)
			throws ErroRepositorioException;
	
	/**
	 * Recupera os dados de documento item
	 * 
	 * @author Sávio Luiz
	 * @created 29/05/2006
	 * 
	 * @param matriculaImovel
	 *            Matrícula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarDadosCobrancaDocumentoItem(
			Integer idDocumentoCobranca) throws ErroRepositorioException;

	
	
	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * [FS0004] Validar Registro Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @created 05/06/2007
	 * 
	 * @param idRA
	 * @exception ErroRepositorioException
	 */
	public Object[] pesquisarRegistroAtendimentoTransferenciaDebitoCredito(
			Integer idRA) throws ErroRepositorioException ;
	
	
	/**
	 * [UC00609] Transferencia de Debitos/Creditos
	 * 
	 * [FS0004] Validar Registro Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @created 05/06/2007
	 * 
	 * @param idSolicitacaoTipoEspecificacao
	 * @exception ErroRepositorioException
	 */
	public EspecificacaoTipoValidacao pesquisarEspecificacaoTipoValidacaoTransferenciaDebitoCredito(
			Integer idSolicitacaoTipoEspecificacao) throws ErroRepositorioException ;
	
	/**
	 * Pesquisar relação de parcelamento 
	 * 
	 * @author Ana Maria
	 * @date 01/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<RelacaoParcelamentoRelatorioHelper> pesquisarRelacaoParcelamento(FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento)
				throws ErroRepositorioException;
	
	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0001] - Processar Documentos de Cobrança
	 * 
	 * Retorna os dados do Imovel
	 * 
	 * @author Sávio Luiz
	 * @date 11/06/2007
	 * 
	 * @return Date retorno
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosImovelPorOS(int idOrdemServico)
			throws ErroRepositorioException;
	
	/**
	 * [UC0XXXX] Gerar Resumo das Ações de Cobrança Eventuais
	 * 
	 * @author Sávio Luiz, Anderson Italo
	 * @created 15/06/2006, 25/02/2010
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<Object[]> pesquisarCobrancaAcaoAtividadeComandoSemRealizacao()
			throws ErroRepositorioException;
	
	/**
	 * Este caso de uso permite gerar os resumos das ações de cobrança eventuais.
	 * 
	 * [UC0XXXX] Gerar Resumo das Ações de Cobrança Eventuais
	 * 
	 * [SB0001] - Processar Documentos Cobrança
	 * 
	 * Retorna os CBDO_ID da tabela COBRANCA_DOCUMENTO com CAAC_ID da tabela
	 * COBRANCA_ATIVIDADE_ACAO_CRONOGRAMA
	 * 
	 * @author Sávio Luiz
	 * @date 18/06/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection<DadosPesquisaCobrancaDocumentoHelper> pesquisarCobrancaDocumentoEventual(
			int idCobrancaAtividadeAcaoComando)
			throws ErroRepositorioException;
	
	/**
	 * Este caso de uso permite gerar os resumos das ações de cobrança eventuais.
	 * 
	 * [UC0XXXX] Gerar Resumo das Ações de Cobrança Eventuais
	 * 
	 * [SB0001] - Processar Documentos Cobrança
	 * 
	 * Retorna os CBDO_ID da tabela COBRANCA_DOCUMENTO com CAAC_ID da tabela
	 * COBRANCA_ATIVIDADE_ACAO_CRONOGRAMA
	 * 
	 * @author Sávio Luiz
	 * @date 18/06/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarOrdemServicoEventual(int idCobrancaAtividadeAcaoComando)
			throws ErroRepositorioException;
	
	/**
	 * Este caso de uso permite gerar os resumos das ações de cobrança eventuais.
	 * 
	 * [UC0XXXX] Gerar Resumo das Ações de Cobrança Eventuais
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 19/06/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public void deletarResumoCobrancaAcaoEventual(int idCobrancaAcaoCronograma)
			throws ErroRepositorioException;
	
	/**
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * Pesquisa os dados de cobranca documento agrupado para pegar a quantidade
	 * e o valor dos documentos
	 * 
	 * @author Sávio Luiz
	 * @date 19/10/2006
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosCobrancaDocumentoEventualAgrupadoPorDataPrevista(
			int idCobrancaAtividadeAcaoComando)
			throws ErroRepositorioException;
	
	/**
	 * Este caso de uso permite gerar os resumos das ações de cobrança eventuais.
	 * 
	 * [UC0XXXX] Gerar Resumo das Ações de Cobrança Eventuais
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 19/06/2007
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public void atualizarCobrancaAcaoAtividadeComando(
			int idCobrancaAtividadeAcaoComando)
			throws ErroRepositorioException;

	/**
	 * Ana Maria Data: 08/07/2007 Pesquisa os ID dos Imovéis pelo Cliente
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @return Coleção de Ids dos Imóveis
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdsImoveisCliente(String codigoCliente) throws ErroRepositorioException;

	/**
	 * Faz parte de [UC0067] Obter Débito do Imovel ou Cliente 
	 * Ana Maria Data: 08/08/2007
	 * 
	 * @param idImoveis
	 *            Matriculas dos Imoveis
	 * @prarm dataVencimentoInicial Data Vencimento Inicial
	 * @param situacaoNormal
	 *            Situação Normal
	 * @parm dataVencimentoFinal Data Vecimento Final
	 * @return Coleção de Guias de Pagamentos
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiasPagamentoIdsImoveis(Integer idCliente, Collection idsImoveis, int indicadorPagamento,
			String situacaoNormal, Date dataVencimentoInicial,
			Date dataVencimentoFinal) throws ErroRepositorioException;
	
	/**
	 * Faz parte de [UC0067] Obter Débito do Imovel ou Cliente Ana Maria Data:
	 * 08/08/2007
	 * 
	 * @param idImoveis
	 *            Matriculas dos Imoveis
	 * @prarm dataVencimentoInicial Data Vencimento Inicial
	 * @param situacaoNormal
	 *            Situação Normal
	 * @parm dataVencimentoFinal Data Vecimento Final
	 * @return Coleção de Guias de Pagamentos
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiasPagamentoIdsImoveis(Integer idCliente, Collection idsImoveis, int indicadorPagamento,
			String situacaoNormal, Short clienteRelacaoTipo, Date dataVencimentoInicial,
			Date dataVencimentoFinal) throws ErroRepositorioException;
	
	/**
	 * Faz parte de [UC0067] Obter Débito do Imovel ou Cliente
	 *  Pesquisa os ID dos imoveis dos cliente sem ralação fim
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @param relacaoTipo
	 *            Relação Tipo Cliente Imovel
	 * @return Coleção de Debitos A Cobrar do Cliente
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdImoveisClienteSemRelacaoFim(String codigoCliente,
			Short relacaoTipo) throws ErroRepositorioException;
	
	/**
	 * Faz parte de [UC0067] Obter Débito do Imovel ou Cliente Pesquisa os ID
	 * dos imoveis dos cliente sem ralação fim
	 * 
	 * @param codigoCliente
	 *            Codigo Cliente
	 * @param relacaoTipo
	 *            Relação Tipo Cliente Imovel
	 * @return Coleção de Debitos A Cobrar do Cliente
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdImoveisClienteSuperiorSemRelacaoFim(
			Collection<Integer> idsCliente, Short clienteRelacaoTipo)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * Gerar Curva ABC de Debitos
	 * 
	 * [UC0621] Gerar Curva ABC de Debitos
	 * 
	 * @author Ivan Sérgio
	 * @date 01/08/2007
	 * 
	 */
	public Collection gerarCurvaAbcDebitos(
			String classificacao,
			String indicadorImovelMedicaoIndividualizada,
			String indicadorImovelParalizacaoFaturamentoCobranca,
			String[] gerenciaRegional,
			String idLocalidadeInicial,
			String idLocalidadeFinal,
			String idSetorComercialInicial,
			String idSetorComercialFinal,
			String idMunicipio,
			String[] situacaoLigacaoAgua,
			String[] situacaoLigacaoEsgoto,
			String intervaloConsumoMinimoFixadoEsgotoInicial,
			String intervaloConsumoMinimoFixadoEsgotoFinal,
			String indicadorMedicao,
			String idTipoMedicao,
			String idPerfilImovel,
			String idTipoCategoria,
			String[] categoria,
			String idSubCategoria) throws ErroRepositorioException;

	/**
	 * [UC0630] - Solicitar Emissão do Extrato de Débitos
	 * Author: Vivianne Sousa 
	 * Data: 22/08/2007
	 * 
	 * Obtem os parcelamentos de débitos efetuados que estejam com situação normal
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarParcelamentosSituacaoNormal(Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0630] - Solicitar Emissão do Extrato de Débitos
	 * Author: Vivianne Sousa 
	 * Data: 22/08/2007
	 * 
	 * @param idParcelamento
	 * 
	 * @return Coleção de Debitos A Cobrar
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitosACobrarImovelParcelamento(Integer idParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0630] - Solicitar Emissão do Extrato de Débitos
	 * Author: Vivianne Sousa 
	 * Data: 22/08/2007
	 * 
	 * @param idParcelamento
	 * 
	 * @return Coleção de Creditos A Realizar
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCreditosARealizarParcelamento(Integer idParcelamento) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * O sistema ordena a lista de documentos de cobrança por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), quadra (CBDO_NNQUADRA), lote e sublote
	 * (IMOV_NNLOTE e IMOV_SUBLOTE da tabela IMOVEL com IMOV_ID da tabela
	 * DOCUMENTO_COBRANCA)
	 * 
	 * @author Sávio Luiz
	 * @data 26/05/2006
	 * 
	 * @param idBairro,
	 *            idLogradouro
	 * @return Collection<CobrancaDocumento>
	 */
	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaEmitirCAER(
			Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio)
			throws ErroRepositorioException;
	
	/**
	 * [UC0214] - Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 01/09/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificarRDUtilizadaPeloImovel(
			Integer idRD, Integer idImovel)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0214] Efetuar Parcelamento Debito 
	 * 
	 * @author Vivianne Sousa
	 * @created 01/09/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorDebitoACobrarSancoes(Integer idImovel, Integer anoMesInicialReferenciaDebito, Integer anoMesFinalReferenciaDebito) throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento Debito 
	 * 
	 * @author Vivianne Sousa
	 * @created 06/09/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorDebitoACobrar(Integer idImovel, Integer anoMesInicialReferenciaDebito, Integer anoMesFinalReferenciaDebito) throws ErroRepositorioException ;
	
	/**
	 * Faz parte de [UC0067] Obter Débito do Imóvel ou Cliente Obtem os débitos total
	 * de um cliente 
	 */
	public Collection pesquisarDebitosCliente(Integer idCliente, Short relacaoTipo, Collection idsImoveis,
			int indicadorPagamento, int indicadorConta, 
			String contaSituacaoNormal, String contaSituacaoRetificada,
			String contaSituacaoIncluida, String contaSituacaoParcelada,
			String anoMesInicialReferenciaDebito,
			String anoMesFinalReferenciaDebito,
			Date anoMesInicialVecimentoDebito, Date anoMesFinalVencimentoDebito,
			int indicadorDividaAtiva)
			throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento Debito 
	 * 
	 * @author Vivianne Sousa
	 * @created 14/09/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorDebitoCobradoContas(Integer idImovel,	
			Integer anoMesInicialReferenciaDebito,
			Integer anoMesFinalReferenciaDebito, int indicadorDividaAtiva) throws ErroRepositorioException ;
	
	/**
	 * [UC0214] Efetuar Parcelamento Debito 
	 * 
	 * @author Vivianne Sousa
	 * @created 20/09/2007
	 * 
	 * @param idImovel
	 * @param anoMesInicialReferenciaDebito
	 * @param anoMesFinalReferenciaDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorDebitoCobradoSancoes(
			Integer idImovel,
			Integer anoMesInicialReferenciaDebito,
			Integer anoMesFinalReferenciaDebito, int indicadorDividaAtiva) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0701] Informar Índices dos Acréscimos de Impontualidade 
	 * 
	 * @author Sávio Luiz
	 * @created 26/09/2007
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaximoAnoMesIndicesAcerscimosImpontualidade() 
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0216] Calcular Acréscimo por Impontualidade
	 * 
	 * @autor: Raphael Rossiter
	 * 
	 * Pesquisa os dados do Indices Acrescimo Impontualidade menor ao
	 * ano mes referencia
	 * 
	 * @param anoMesReferenciaDebito
	 * @return O Indices Acrescimos por Impontualidade
	 * @throws ErroRepositorioException
	 */
	public IndicesAcrescimosImpontualidade pesquisarMenorIndiceAcrescimoImpontualidade() 
		throws ErroRepositorioException ;
	
	/**
	 * Retorna uma colecao de Debitos
	 * por Faixa de Valores dos Imoveis
	 * 
	 * @author Ivan Sergio
	 * @created 20/09/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoImovelPorFaixaValores(
			String idImovel,
			String valorMinimoDebito,
			String anoMesReferenciaInicial,
			String anoMesReferenciaFinal,
			String classificacao,
			boolean pesquisaMunicipio) throws ErroRepositorioException;
	
//	Flávio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloUnidadeNegocio(String idUnidadeNegocio,
			String idCobrancaAcao) 
			throws ErroRepositorioException;
	
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloGrupo(String idGrupoCobranca,
			String idCobrancaAcao) 
			throws ErroRepositorioException;
	
	//Flávio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloGerencia(String idGerenciaRegional,
			String idCobrancaAcao) 
			throws ErroRepositorioException;
	
	//Flávio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloLocalidade(String idLocalidadeInicial, 
			String idLocalidadeFinal, String idCobrancaAcao) 
			throws ErroRepositorioException;
	
	//Flávio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotasIntervaloSetor(String codigoSetorComercialInicial, 
			String codigoSetorComercialFinal, String idLocalidade,
			String idCobrancaAcao) 
			throws ErroRepositorioException;
	
	//Flávio Cordeiro
//	caso de uso [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	public Collection pesquisarRotas(String codigoSetorComercial, 
			String rotaInicial, String rotaFinal, String idLocalidade,
			String idCobrancaAcao) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0543] Associar Conjunto de Rotas a Criterio de Cobranca
	 * @author Francisco do Nascimento
	 * @date 27/02/08
	 */
	public Collection pesquisarRotasPorCobrancaAcao(String idCobrancaAcao) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0067] Obter Débito do Imóvel ou Cliente
	 * 
	 * @autor: Raphael Rossiter
	 * 
	 * Verifica se existe uma devolução associada ao credito
	 * 
	 * @param creditoARealizar
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean existeDevolucao(CreditoARealizar creditoARealizar) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0067] Inserir Comando Negaivação
	 * 
	 * @autor: Ana Maria
	 * 
	 * [FS0019] Verificar existência de Parcelamento
	 * 
	 * @param idImovel
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarClienteResponsavelParcelamento(Integer idImovel) 
		throws ErroRepositorioException;
    
    /**
     * [UC0737] Atualiza Quantdade de Parcela Paga Consecutiva e Parcela Bônus
     * 
     * Retorna dados dos parcelamentos com RD = 8 que estejam com situação normal 
     * e que não exista outro parcelamento com data posterior  
     * 
     * @author Vivianne Sousa
     * @date 31/01/2008
     * 
     * @return Collection retorno
     * @throws ErroRepositorioException
     */
    public Collection<Object[]> pesquisarParcelamentoRDEspecial(
            Integer situacaoParcelamento, Integer idLocalidade)
            throws ErroRepositorioException ;
    

	/**
	 * [UC0200] Inserir Débito Automático
	 * 
	 * @autor Rodrigo Silveira
	 * @date 28/01/2008
	 * [FS0006] Verificar Data de Opção já Excluída
	 * 
	 * @param matriculaImovel
	 * @param dataOpcao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String verificarDataOpcaoJaExcluida(String matriculaImovel, Date dataOpcao) throws ErroRepositorioException;
    
    /**
     * [UC0737] Atualiza Quantidade de Parcela Paga Consecutiva e Parcela Bônus
     * 
     * @author Vivianne Sousa
     * @created 07/02/2008
     * 
     * @param idParcelamento
     * @exception ErroRepositorioException
     *                Repositorio Exception
     */
    public void atualizarNumeroParcelasPagasConsecutivasParcelamento(Integer idParcelamento,
            Short numeroParcelas)throws ErroRepositorioException;
    
    /**
     *[UC0676] - Consultar Resumo da Negativacao
	 * 
	 * Pesquisa resumo Negativacao
	 * 
	 * @author Marcio Roberto
	 * @date 28/02/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarNegativacao(
			DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper, int tipo)
			throws ErroRepositorioException;
	
	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 * 
	 * [SB0001] - Processar Documentos Cobrança
	 * Consultar os documentos de cobranca sem carregar os criterios de cobranca
	 * 
	 * Retorna os CBDO_ID da tabela COBRANCA_DOCUMENTO com CAAC_ID da tabela
	 * COBRANCA_ATIVIDADE_ACAO_CRONOGRAMA
	 * 
	 * @author Rafael Santos,Sávio Luiz, Francisco do Nascimento
	 * @date 17/10/2006,28/05/2007, 22/05/2008
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaDocumentoResumoAcaoCobranca(
			int idCobrancaAtividadeAcaoCronograma, int idCobrancaAtividadeAcaoComando)
			throws ErroRepositorioException;	
	
	/**
	 * Método para atualizar a situacao do documento item de acordo com a situacao da 
	 *    conta / guia de pagamento / debito a cobrar.
	 * Este método será usado nos métodos cancelar/desfazer cancelamento conta,  incluir / desfazer parcelamento,
	 * incluir / excluir pagamento, movimento de arrecadadores
	 * 
	 * @param situacaoDebito
	 * @param dataSituacao
	 * @param idConta
	 * @param idGuiaPagamento
	 * @param idDebitoACobrar
	 * @throws ErroRepositorioException
	 * 
	 * @author Francisco do Nascimento
	 * @date 26/05/2008
	 * 
	 */
	public void atualizarSituacaoCobrancaDocumentoItem(Integer situacaoDebito, Date dataSituacao, 
			Integer idConta, Integer idGuiaPagamento, Integer idDebitoACobrar) throws ErroRepositorioException;
	
	/**
	 * Recupera os dados de documento item com situacao atualizada 
	 * 
	 * @author Francisco do Nascimento
	 * @created 19/05/08
	 * 
	 * @param idDocumentoCobranca id do documento de cobranca
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection pesquisarDadosCobrancaDocumentoItemSituacaoJaAtualizada(
			Integer idDocumentoCobranca) throws ErroRepositorioException;
	
	/**
	 * Este caso de uso permite gerar o resumo das ações de cobrança com a
	 * atividade emitir já realizada e a atividade encerrar ainda não realizada
	 * e realizar a atividade encerrar das ações que estejam comandadas.
	 * 
	 * [UC0478] Gerar Resumo das Ações de Cobrança Eventuais
	 * 
	 * [SB0001] - Processar Documentos Cobrança Eventual 
	 * Consultar os documentos de cobranca sem carregar os criterios de cobranca
	 * 
	 * Retorna os CBDO_ID da tabela COBRANCA_DOCUMENTO com CACM_ID da tabela
	 * COBRANCA_ATIVIDADE_ACAO_COMANDO
	 * 
	 * @author Rafael Santos,Sávio Luiz, Francisco do Nascimento
	 * @date 17/10/2006,28/05/2007, 04/06/2008
	 * 
	 * @return Collection retorno
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaDocumentoEventualSemCriterio(
			int idCobrancaAtividadeAcaoComando)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa o critério de situacao de cobrança definido para o critério de
	 * cobrança
	 * 
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0001] Gerar Atividade de
	 * Ação de Cobrança para os Imóveis do Cliente
	 * 
	 * @author Francisco do Nascimento
	 * @created 10/06/2008
	 * 
	 * @param idCobrancaCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CriterioSituacaoCobranca> pesquisarCobrancaCriterioSituacaoCobranca(
			Integer idCobrancaCriterio) throws ErroRepositorioException;
	
	/**
	 * Pesquisa o critério de situacao de ligacao de agua definido para o critério de
	 * cobrança
	 * 
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0001] Gerar Atividade de
	 * Ação de Cobrança para os Imóveis do Cliente
	 * 
	 * @author Francisco do Nascimento
	 * @created 10/06/2008
	 * 
	 * @param idCobrancaCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CriterioSituacaoLigacaoAgua> pesquisarCobrancaCriterioSituacaoLigacaoAgua(
			Integer idCobrancaCriterio) throws ErroRepositorioException;
	
	/**
	 * Pesquisa o critério de situacao de ligacao de esgoto definido para o critério de
	 * cobrança
	 * 
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0001] Gerar Atividade de
	 * Ação de Cobrança para os Imóveis do Cliente
	 * 
	 * @author Francisco do Nascimento
	 * @created 10/06/2008
	 * 
	 * @param idCobrancaCriterio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CriterioSituacaoLigacaoEsgoto> pesquisarCobrancaCriterioSituacaoLigacaoEsgoto(
			Integer idCobrancaCriterio) throws ErroRepositorioException;	
			
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - AVISO DE DÉBITOS
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param idImovel
	 * @param dataEmissao
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarParmsCobrancaDocumento(Integer idImovel,
			Date dataEmissao) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - AVISO DE DÉBITOS
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param idImovel
	 * @param dataEmissao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaDocumentoItem(Integer idImovel,
			Date dataEmissao) throws ErroRepositorioException ;
	
	/**
	 * Verificar se existe pagamento para contas de um imovel as quais foram utilizadas
	 *  como entrada num parcelamento 
	 * @param idImovel
	 * @param dataParcelamento
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * @author Francisco do Nascimento
	 * @date 03/07/2008
	 * 
	 */
	public boolean exitePagamentoContaEntradaParcelamento(Integer idImovel, 
		Date dataParcelamento) throws ErroRepositorioException;	
	
	/**
	 * Consulta as contas transferidas
	 * 
	 * [UC0204] - Consultar Transferências do Débito
	 * 
	 * @author Rafael Corrêa
	 * @date 22/08/2008
	 */
	public Collection<Object[]> consultarContasTransferidas(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ErroRepositorioException;
	
	/**
	 * Consulta os débitos a cobrar transferidos
	 * 
	 * [UC0204] - Consultar Transferências do Débito
	 * 
	 * @author Rafael Corrêa
	 * @date 22/08/2008
	 */
	public Collection<Object[]> consultarDebitosACobrarTransferidos(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ErroRepositorioException;
	
	/**
	 * Consulta as guias de pagamento transferidas
	 * 
	 * [UC0204] - Consultar Transferências do Débito
	 * 
	 * @author Rafael Corrêa
	 * @date 22/08/2008
	 */
	public Collection<Object[]> consultarGuiasDePagamentoTransferidas(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ErroRepositorioException;
	
	/**
	 * Consulta os créditos a realizar transferidos
	 * 
	 * [UC0204] - Consultar Transferências do Débito
	 * 
	 * @author Rafael Corrêa
	 * @date 22/08/2008
	 */
	public Collection<Object[]> consultarCreditosARealizarTransferidos(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ErroRepositorioException;
	
	/**
	 * [UC0870] Gerar Movimento de Contas em Cobrança por Empresa
	 * 
	 * Pesquisa as contas associadas ao imóvel
	 * 
	 * @author: Rafael Corrêa
	 * @date: 28/10/2008
	 */
	public Collection<Object[]> pesquisarContasInformarContasEmCobranca(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, 
			Collection<Integer> idsImoveis, SistemaParametro sistemaParametro) throws ErroRepositorioException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 15/07/2008
	 */
	public Collection<Object[]> obterNomeCPFTestemunhas(Integer unidadeUsuario) throws ErroRepositorioException;
	
	/**
	 * [UC0852] Incluir Débito a Cobrar de Entrada de Parcelamento Não Paga
	 *
	 * Cancela uma guia de pagamento
	 *
	 * @author Raphael Rossiter
	 * @date 26/08/2008
	 *
	 * @param idGuiaPagamento
	 * @throws ErroRepositorioException
	 */
	public void cancelarGuiaPagamento(Integer idGuiaPagamento, Integer anoMesReferenciaContabil) throws ErroRepositorioException ;
	
	/**
	 * [UC0852] Incluir Débito a Cobrar de Entrada de Parcelamento Não Paga
	 *
	 * Desassociar a conta do parcelamento
	 *
	 * @author Raphael Rossiter
	 * @date 26/08/2008
	 *
	 * @param idConta
	 * @throws ErroRepositorioException
	 */
	public void desassociarContaParcelamento(Integer idConta) throws ErroRepositorioException ;
	
	/**
	 * [UC0852] Incluir Débito a Cobrar de Entrada de Parcelamento Não Paga
	 *
	 * @author Raphael Rossiter
	 * @date 26/08/2008
	 *
	 * @param parcelamento
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContaDoParcelamentoNaoPago(Integer parcelamento)
			throws ErroRepositorioException ;
	
	/**
	 * Recupera os itens do parcelamento a partir do parcelamento
	 * 
	 * @author Sávio Luiz
	 * @date 23/10/2008
	 */
	public Collection<Object[]> pesquisarItensParcelamentos(Integer idParcelamento) throws ErroRepositorioException; 
	
	/**
	 * Pesquisa se a conta está na tabela de empresa cobranca conta
	 * 
	 * @author Sávio Luiz
	 * @date 23/10/2008
	 */
	public Integer pesquisarEmpresaCobrancaConta(Integer idConta, Integer idImovel, Date dataPagamentoParcelamento) throws ErroRepositorioException;
	
	/**
	 * Recupera os dados do debito a cobrar
	 * 
	 * @author Sávio Luiz
	 * @date 23/10/2008
	 */
	public Object[] pesquisarDadosDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException; 
	
	/**
	 * Recupera os itens do parcelamento a partir do parcelamento
	 * 
	 * @author Sávio Luiz
	 * @date 23/10/2008
	 */
	public Collection<Object[]> pesquisarItensParcelamentosNivel2(Integer idPagamento) throws ErroRepositorioException;
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * 
	 * 
	 * @author: Rômulo Aurélio
	 * @date: 29/10/2008
	 */
	
	public Collection pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaParaCobranca(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal, int numeroIndice,int quantidadeRegistros)
			throws ErroRepositorioException;
	public Collection pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaParaCobrancaResumido(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal, int numeroIndice,int quantidadeRegistros)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * 
	 * 
	 * @author: Rômulo Aurélio
	 * @date: 29/10/2008
	 */
	public Collection<Object[]> pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaParaCriterio(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal,int numeroIndice,int quantidadeRegistros)
			throws ErroRepositorioException ;
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * 
	 * 
	 * @author: Rômulo Aurélio
	 * @date: 29/10/2008
	 */
	public Integer pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaParaCobrancaCount(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal)
			throws ErroRepositorioException;
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * 
	 * 
	 * @author: Rômulo Aurélio
	 * @date: 29/10/2008
	 */
	public Collection<Object[]> pesquisarDadosArquivoTextoContasCobrancaEmpresa(
			Collection ids, 
			Integer idUnidadeNegocio,
			Integer numeroPagina, 
			int quantidadeRegistros,
			Integer idProgramaEspecial) throws ErroRepositorioException;
	
	public Collection obterUnidadeNegocioEmpresaCobrancaConta(Integer[] ids) throws ErroRepositorioException;
	
	/**
	 * Pesquisa se a conta está na tabela de empresa cobranca conta
	 * 
	 * @author Sávio Luiz
	 * @date 23/10/2008
	 */
	public void removerEmpresaCobrancaContaPagamentos(Integer anoMesReferenciaArrecadacao,Integer idLocalidade) throws ErroRepositorioException;
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - AVISO DE DÉBITOS
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param idImovel
	 * @param dataEmissao
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarParmsCobrancaDocumento(Integer idImovel,
			BigDecimal valorPagamento) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - AVISO DE DÉBITOS
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param idImovel
	 * @param dataEmissao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaDocumentoItem(Integer idImovel,
			BigDecimal valorPagamento) throws ErroRepositorioException ;
	
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 *
	 * @author Raphael Rossiter
	 * @date 13/11/2008
	 *
	 * @return Collection<ResolucaoDiretoria>
	 * @throws ErroRepositorioException
	 */
	public  Collection<ResolucaoDiretoria>  pesquisarResolucaoDiretoriaMaiorDataVigenciaInicioPermissaoEspecial()
	throws ErroRepositorioException ;
	
	/**
	 * Pesquisa a quantidade de Rotas que nao possui um Criterio definido para cada uma das Acoes de Cobrancas passadas no filtro
	 * 
	 * @author Victor Cisneiros
	 * @date 10/12/2008
	 */
	public Integer pesquisarQtdeRotasSemCriteriosParaAcoesCobranca(
			PesquisarQtdeRotasSemCriteriosParaAcoesCobranca filtro) throws ErroRepositorioException;
	
	/**
	 * Deleta as OrdemServicoUnidade geradas pelos DocumentosCobranca
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public Integer deletarOrdemServicoUnidadeGeradasPelosDocumentosCobranca(
			Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;
	
	/**
	 * Remover os debitos a cobrar das ordens de servico geradas pelos documentos
	 * 
	 * @author Francisco do Nascimento
	 * @date 18/11/2009
	 */
	public void removerDebitoACobrarOrdemServicoGeradasPelosDocumentosCobranca(
			Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;
	
	/**
	 * Deleta as OrdemServico geradas pelos DocumentosCobranca
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public Integer deletarOrdemServicoGeradasPelosDocumentosCobranca(
			Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;
	
	/**
	 * Deleta as DebitoACobrar geradas pelos DocumentosCobranca
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public Integer deletarDebitoACobrarGeradasPelosDocumentosCobranca(
			Collection<Integer> idsDocumentosCobranca, Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * Deleta as CobrancaDocumentoItem geradas pelos DocumentosCobranca
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public Integer deletarCobrancaDocumentoItemGeradasPelosDocumentosCobranca(
			Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;
	
	/**
	 * Deleta os Documentos de Cobranca com os Ids passados como parametro
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public Integer deletarCobrancaDocumentos(
			Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;
	
	/**
	 * Deletar os registros de imoveis nao gerados 
	 * 
	 * @author Francisco do Nascimento
	 * @date 17/11/2009
	 */
	public void removerImoveisNaoGerados(
			Integer idCobrancaComandoCronograma, Integer idCobrancaComandoEventual)  throws ErroRepositorioException;
	
	/**
	 * Inserir os registros de imoveis nao gerados dos documentos excedentes 
	 * 
	 * @author Francisco do Nascimento
	 * @date 18/11/2009
	 */
	public void inserirImoveisNaoGeradosParaDocumentosExcedentes(Collection<Integer> idsDocumentosCobranca) 
		throws ErroRepositorioException;
	
	/**
	 * Pesquisar Quantidade de Ordens de Serviço Encerradas
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public Integer pesquisarQuantidadeOrdensServicoEncerradasPorCobrancaAcaoAtividade(
			Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando) throws ErroRepositorioException;
	
	/**
	 * Pesquisar Quantidade de Pagamentos por Documentos de Cobrança
	 * 
	 * @author Victor Cisneiros
	 * @date 19/12/2008
	 */
	public Integer pesquisarQuantidadePagamentosPorDocumentosCobranca(
			Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando) throws ErroRepositorioException;
	
	/**
	 * Pesquisar Quantidade de Comandos Sucessores de um Comando
	 * 
	 * @author Victor Cisneiros
	 * @date 29/12/2008
	 */
	public Integer pesquisarQuantidadeComandosSucessores(
			Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando) throws ErroRepositorioException;
	
	/**
	 * [UC0868] Gerar Relatorio de Pagamentos das Contas em Cobranca por Empresa
	 * 
	 * @author: Rômulo Aurélio
	 * @date: 08/01/2009
	 */
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresa(
			RelatorioPagamentosContasCobrancaEmpresaHelper helper) 
			throws ErroRepositorioException;
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaOpcaoTotalizacao(
			RelatorioPagamentosContasCobrancaEmpresaHelper helper) 
			throws ErroRepositorioException;
	
	
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaCount(
			Integer idEmpresa, Integer referenciaPagamentoInicial, Integer referenciaPagamentoFinal
			) throws ErroRepositorioException;
	
	/**
	 * [UC0879] Gerar Extensão de Comando de das Contas em Cobranca por Empresa
	 * 
	 * @author: Rômulo Aurélio
	 * @date: 03/02/2009
	 * 
	 * 
	 */
	
	public Collection pesquisarDadosGerarExtensaoComandoContasCobrancaEmpresaParaCobranca(
			Integer idComandoEmpresaCobrancaConta) throws ErroRepositorioException ;
	
	public Integer retornaAnoMesContaUltimaExtensao(Integer idComandoEmpresaCobrancaConta) 
			throws ErroRepositorioException;

	
	
		
	/**
	 * [UC0878] Gerar Relação de Parcelamento - Visão Analitica
	 * 
	 * @author Bruno Barros
	 * 
	 * @date 04/02/2009
	 */
	public Collection<Object[]> filtrarRelacaoParcelamentoAnalitico(
			FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento, Integer anoMesReferenciaFaturamento) throws ErroRepositorioException;	
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * [FS0028] Verifica se existeParcelas em atraso
	 * [FS0029] Verificar se existe parcelamento em andamento
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterParcelamentoMaisAtualDoImovel(Integer idImovel)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * [FS0028] Verifica se existeParcelas em atraso
	 * [FS0029] Verificar se existe parcelamento em andamento
	 * 
	 * verificar se existe parcelas a serem cobradas 
	 * (selecionar na tabela de debito a cobrar (FATURAMENTO.DEBITO_A_COBRAR),
	 * a linha que tenha o PARC_ID = PARC_ID da tabela COBRANCA.PARCELAMENTO e 
	 * o tipo de débito (DBTP_ID) = 40) 
	 * e caso o número de prestações do débito não seja igual ao número de prestações cobradas  
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2009
	 * 
	 * @param idParcelamento
	 * 
	 * @return Coleção de Debitos A Cobrar
	 * @throws ErroRepositorioException
	 */
	public Collection obterDebitoACobrarDoParcelamento(Integer idParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * [FS0028] Verifica se existeParcelas em atraso
	 * 
	 * verificar se existe parcelas em atraso, ou seja, 
	 * verificar nas contas que irão fazer parte do parcelamento, 
	 * se existe algum débito de parcelamento da RDIR_ID = RDIR_IDPARCELASEMATRASO
	 * 
	 * @author Vivianne Sousa
	 * @date 10/02/2009
	 * 
	 * @param idParcelamento
	 * 
	 * @return Coleção de Debitos A Cobrar
	 * @throws ErroRepositorioException
	 */
	public Collection obterContasComParcelasEmAtrasoDoParcelamento(
			Integer idImovel,Integer refInicialInformada,
			Integer refFinalInformada) throws ErroRepositorioException;
	/**
	 * [UC0880] Gerar Movimento de Extensão de Contas em Cobrança por Empresa
	 * 
	 * Pesquisa as contas associadas ao imóvel
	 * 
	 * @author: Rômulo Aurélio 
	 * @param idPerfil 
	 * @throws SQLException 
	 * @date: 13/02/2009
	 */
	public void inserirMovimentoExtensaoContasEmCobranca(
			Integer idLocalidade, Integer idPerfil) throws ErroRepositorioException;
	
	/**
	 * [UC0891] Gerar Relatorio de Imóveis com Acordo
	 * 
	 * @author: Rômulo Aurélio
	 * @date: 01/04/2009
	 */
	public Collection pesquisarDadosGerarRelatorioImoveisComAcordo(
			Integer idUnidadeNegocio, Integer idLocalidadeInicial,
			Integer idLocalidadeFinal, Integer idGerenciaRegional,
			Date dataInicialAcordo, Date dataFinalAcordo, Integer rotaInicial,
			Integer rotaFinal, Integer sequencialRotaInicial,
			Integer sequencialRotaFinal, Integer idSetorComercialInicial,
			Integer idSetorComercialFinal) throws ErroRepositorioException ;
	
	public Integer pesquisarDadosGerarRelatorioImoveisComAcordoCount(
			Integer idUnidadeNegocio, Integer idLocalidadeInicial,
			Integer idLocalidadeFinal, Integer idGerenciaRegional,
			Date dataInicialAcordo, Date dataFinalAcordo, Integer rotaInicial,
			Integer rotaFinal, Integer sequencialRotaInicial,
			Integer sequencialRotaFinal, Integer idSetorComercialInicial,
			Integer idSetorComercialFinal) throws ErroRepositorioException;
		
	public CicloMetaGrupo pesquisarCicloMetaGrupoPorGrupoLocalidade(int anoMes, int idGrupo,
			int idLocalidade) throws ErroRepositorioException;
	
	public CicloMeta pesquisarMetaCiclo(int anoMes, int idCobrancaAcao) throws ErroRepositorioException;
	
	public Integer pesquisarQuantidadeDocumentosGeradosAcimaValorLimite(Integer idCAAC, Integer idCACM,
			Integer idLocalidade, BigDecimal valorLimite) throws ErroRepositorioException;
	
	public Integer pesquisarQuantidadeDocumentosGerados(Integer idCAAC, Integer idCACM, Integer idLocalidade)
		throws ErroRepositorioException;
	
	public Collection pesquisarDocumentosCobrancaExcedentes(Integer idCAAC, Integer idCACM, int quantidadeParaRemover,
		Integer idLocalidade) throws ErroRepositorioException;
	
	public void adicionarMetaCicloLocalidade(int idMetaCiclo, int idGrupo,
		int idLocalidade, int quantidadeASerAdicionada) throws ErroRepositorioException;
	
	public Collection<CicloMetaGrupo> pesquisarCicloMetaGrupoPorCicloMeta(Integer idCicloMeta, Integer idGrupo) 
		throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0000] - Gerar Metas do Cilo
	 * Pesquisar a quantidade de imoveis em cada grupo/localidade
	 *
	 * @author Francisco do Nascimento
	 * @date 23/04/2009
	 *
	 * @param idsLast Colecao de ids de situacao de ligação de água
	 * @return Colecao no formato [idGrupo, idLocalidade, qtdImoveis]
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarQuantidadeImoveisPorGrupoLocalidade(Collection idsLast)
		throws ErroRepositorioException;

	/**
	 * [UC0216] Calcular Acrescimo por Impontualidade
	 *
	 * Caso o imóvel tenha débito automático e o recebimento, mesmo em atraso, tenha sido através
	 * de débito automático, o sistema não calcula os acréscimos de impontualidade
	 * 
	 * (ARMV_DSIDENTIFICACAOSERVICO da tabela ARRECADADOR_MOVIMENTO com valor igual a "DEBITO 
	 * AUTOMATICO" com ARMV_ID = ARMV_ID da tabela ARRECADADOR_MOVIMENTO_ITEM com AMIT_ID = 
	 * AMIT_ID da tabela PAGAMENTO com CNTA_ID = CNTA_ID recebido)
	 *
	 * @author Raphael Rossiter
	 * @date 12/05/2009
	 *
	 * @param idConta
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarPagamentoDeContaPorDebitoAutomatico(Integer idConta) throws ErroRepositorioException ;
	
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * Author: Vivianne Sousa 
	 * Data: 13/05/2009
	 * 
	 * @param idParcelamento
	 * 
	 * @return Coleção de Debitos A Cobrar
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitosACobrarParcelamento(Integer idParcelamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * Author: Vivianne Sousa 
	 * Data: 13/05/2009
	 * 
	 * @param idImovel
	 * @param referencia
	 * 
	 * @return idParcelamento
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdParcelamentoNormal(Integer idImovel, Integer referencia) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * Author: Vivianne Sousa 
	 * Data: 18/05/2009
	 */
	public BigDecimal pesquisarValorDebitoCobradoParcelamentoConta(Integer idConta)
	throws ErroRepositorioException;
	
	/**
	 * [UC0878] Gerar Relação de Parcelamento - Visão Analitica
	 * 
	 * @author Bruno Barros
	 * 
	 * @date 05/06/2009
	 */
	public Integer pesquisarQuantidadeContasNaoPagasParcelamento(
			Integer idParcelamento,Integer idDebitoACobrar ) throws ErroRepositorioException;	
	/**
	 * retorna id da ResolucaoDiretoria 
	 * 
	 * [UC0214] - Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 08/11/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public  Integer  pesquisarResolucaoDiretoriaComPercentualDoacao()
			throws ErroRepositorioException;
	
	/**
	 * retorna coleção de ids de Rota de um Grupo de faturamento 
	 * 
	 * [UC0911] - Gerar Cartas da Campanha de Solidariedade da Criança para Negociação a Vista
	 * 
	 * @author Vivianne Sousa
	 * @date 11/06/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRotasPorGrupoFaturamento(
			Integer idGrupoFaturamento) throws ErroRepositorioException;
	
	/**
	 * retorna coleção de idImovel de uma Rota
	 * 
	 * [UC0911] - Gerar Cartas da Campanha de Solidariedade da Criança para Negociação a Vista
	 * 
	 * @author Vivianne Sousa
	 * @date 12/06/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosImoveisPorRota(Integer idRota) throws ErroRepositorioException ;
	
	/**
	 * [UC0911] - Gerar Cartas da Campanha de Solidariedade da Criança para Negociação a Vista
	 * 
	 * @author Vivianne Sousa
	 * @date 17/06/2009
	 */
	public void deletarCobrancaDocumentoECobrancaDocumentoItem(
			Integer idRota,Integer documentoTipo)throws ErroRepositorioException;
	
	 /**
	 * Seleciona os itens do documento de cobrança correspondentes a debito a cobrar
	 * 
	 * [UC0910] Emitir Cartas da Campanha de Solidariedade da criança para Negociação a Vista
	 * 
	 * @author Vivianne Sousa
	 * @data 17/06/2009
	 */
	public BigDecimal selecionarValorTotalCobrancaDocumentoItemReferenteDebito(
			CobrancaDocumento cobrancaDocumento)throws ErroRepositorioException;
	
	/**
	 * [UC0910] Emitir Cartas da Campanha de Solidariedade da criança para Negociação a Vista
	 * 
	 * O sistema ordena a lista de documentos de cobrança por empresa (EMPR_ID
	 * da tabela DOCUMENTO_COBRANCA), localidade (LOCA_ID), setor
	 * (CBDO_CDSETORCOMERCIAL), quadra (CBDO_NNQUADRA), lote e sublote
	 * (IMOV_NNLOTE e IMOV_SUBLOTE da tabela IMOVEL com IMOV_ID da tabela
	 * DOCUMENTO_COBRANCA)
	 * 
	 * @author Vivianne Sousa
	 * @data 17/06/2009
	 * 
	 * @param idRota,
	 *            idDocumentoTipo
	 * @return Collection<CobrancaDocumento>
	 */
	 public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaEmitir(
			Integer idRota, Integer idDocumentoTipo)throws ErroRepositorioException;
	 
	/**
	 * retorna a Data de Vencimento da Rota 
	 * (FACR_DTCONTAVENCIMENTO da tabela FATURAMENTO_ATIV_CRON_ROTA com ROTA_ID = ROTA_ID já pesquisado antes 
	 * e FTAC_ID = FTAC_ID da tabela FATURAMENTO_ATIVIDADE_CRONOGRAMA e FTAT_ID = 5 
	 * e FTCM_ID = FTCM_ID da tabela FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL com FTGR_ID = ao grupo informado 
	 * e FTCM_AMREFERENCIA = anoMesFaturamento da tabela SISTEMA_PARAMETROS )
	 * 
	 * [UC0910] Emitir Cartas da Campanha de Solidariedade da criança para Negociação a Vista
	 * 
	 * @author Vivianne Sousa
	 * @data 22/06/2009
	 */
	public Date pesquisarDataVencimentoRota(
			Integer idRota, Integer anoMesFaturamento, Integer grupoFaturamento)
			throws ErroRepositorioException;
	
	/**
	 * 
	 *Esse metodo pesquisa as faturas de um cliente responsável federal, de acordo
	 *com o Mes/Ano, de acordo com o id do Cliente
	 * 
	 * @author Jose Guilherme Macedo Vieira
	 * @date 08/07/2009
	 *
	 * @param Integer anoMes
	 * @param Integer clienteID
	 * @return Collection - uma colecao de objetos Fatura
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisaFaturaClienteResponsavelFederal(
			Integer anoMes,
			Integer clienteID) throws ErroRepositorioException ;
	
	
	/**
	 * 
	 *Pesquisa por todos os impostos a partir de uma fatura de um cliente responsavel federal
	 *
	 *OBS: O id da fatura passado tem que ser de uma fatura de um cliente responsavel federal
	 *
	 * @author Jose Guilherme Macedo Vieira
	 * @param clienteID 
	 * @date 08/07/2009
	 *
	 * @param Integer idFatura
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisaImpostoFaturaClienteResponsavelFederal(Integer anoMesInicial, Integer anoMesFinal, Integer clienteID) 
						throws ErroRepositorioException ;	
	
	/**
	 * 
	 * Esse método está de acordo com o UC0919 - Gerar Relatório de Impostos Por Cliente Responsável,
	 * no caso do Relatório Analítico o qual necessita recuperar os Imóveis que estão associados a
	 * uma Fatura (cujo id é passado como parâmetro Integer), a partir da tabela fatura_item.  
	 *
	 * @author Jose Guilherme Macedo Vieira
	 * @date 13/07/2009
	 *
	 * @param Integer idFatura
	 * @return Collection<Imovel> - a colecao de imoveis associadas a uma fatura
	 * @throws ErroRepositorioException
	 */
	public Collection<Imovel> pesquisarImoveisFaturaClienteResponsavel(Integer idFatura)
	 throws ErroRepositorioException;
	
	/**
	 * 
	 * Esse método está de acordo com o UC0258 - Filtrar Documentos de Cobrança,
	 * retorna conjunto de CAAC_ID(ações do ciclo) selecionados 
	 *
	 * @author Anderson Italo
	 * @date 03/08/2009
	 *
	 * @param Collection<Integer> idsAcao
	 * @param Integer anoMesReferencia
	 * @return Collection<CobrancaAcaoAtividadeCronograma> - a colecao de cobrancaAcaoAtividadeCronograma associados 
	 * as acoes e anoMesReferencia Informados
	 * @throws ErroRepositorioException
	 */
	public Collection<CobrancaAcaoAtividadeCronograma> pesquisarAcoesCiclo(Collection<Integer> idsAcao, Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * Atualizar total de documentos, itens e valores realizados nos comandos
	 * de acao de cobranca
	 *  
	 * Data: 28/07/2009
	 * @author Francisco do Nascimento
	 * 
	 * @param idCAAC Identificador de CobrancaAcaoAtividadeCronograma
	 */
	public Object[] calcularTotaisCronogramaAcaoCobranca(Integer idCAAC) 
		throws ErroRepositorioException;	

	/**
	 * 
	 *Pesquisa todas as localidades do cicloMeta agrupando por localidade, unidade e gerencia.
	 *
	 *
	 * @author Genival Barbosa
	 * @date 03/08/2009
	 *
	 * @param Integer idFatura
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public List consultarColecaoCicloMetaGrupoRelatorio(Integer idCicloMeta) 
	throws ErroRepositorioException;
	
	/**
	 * [UC0879] Gerar Extensão de Comando de Contas em Cobrança por Empresa
	 * - Pesquisa dados da cobrança 
	 * @author Hugo Amorim
	 * @throws ErroRepositorioException 
	 */
	public Collection pesquisarValorTotalCobranca
		(Integer idComando,Date dateInicial, Date dateFinal) throws ErroRepositorioException;
	
	/**
	 * [UC0879] Gerar Extensão de Comando de Contas em Cobrança por Empresa
	 * - Pesquisa dados da cobrança criterio
	 * @author Hugo Amorim
	 * @throws ErroRepositorioException 
	 */
	public Collection pesquisarValorTotalCobrancaCriterio
		(Integer idComando,Date dateInicial, Date dateFinal) throws ErroRepositorioException;
	
	/**
	 *  Pesquisa o acréscimo da maneira correta
	 *  
	 * Data: 07/09/2009 
	 * Author: Rafael Corrêa
	 * 
	 * @param anoMesReferenciaDebito
	 *            Ano Mês de Referencia de Débito
	 * @return O Indices Acrescimos por Impontualidade
	 * @throws ErroRepositorioException
	 */
	public IndicesAcrescimosImpontualidade pesquisarMenorIgualIndiceAcrescimoImpontualidade(
			int anoMesReferenciaDebito) throws ErroRepositorioException;
	
	/**
	 * [UC0879] Gerar Extensão de Comando de Contas em Cobrança por Empresa
	 * - Pesquisa dados do popup da cobrança
	 * @author Hugo Amorim
	 * @throws ErroRepositorioException  
	 */
	public Collection pesquisarDadosPopup
		(Integer idComando) throws ErroRepositorioException;
	
	/**
	 * Este método está de acordo com o UC[0258]Filtrar Documento de Cobranca,
	 * é utilizado pelo relatório filtrar documentos de cobrança
	 * 
	 * @author Anderson Italo
	 * @date 19/08/2009
	 *
	 * @param FiltrarDocumentoCobrancaHelper filtro
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List filtrarCobrancaDocumento(FiltrarDocumentoCobrancaHelper filtro) throws ErroRepositorioException;
	
	/**
	 * Este método está de acordo com o [UC0906] Gerar Relatório de Acompanhamento das Supressões, 
	 * Religações e Reestabelecimentos. É utilizado pelo relatório filtrar os registros do relatorio
	 * 
	 * @author Anderson Italo
	 * @date 28/08/2009
	 *
	 * @param FiltroSupressoesReligacoesReestabelecimentoHelper filtro
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List filtrarSupressoesReligacoesReestabelecimentos(FiltroSupressoesReligacoesReestabelecimentoHelper filtro) throws ErroRepositorioException;
	
	/**
	 * Este método está de acordo com o UC[0258]Filtrar Documento de Cobranca,
	 * é utilizado pelo relatório filtrar documentos de cobrança para totalizar
	 * os registros filtrados
	 * 
	 * @author Anderson Italo
	 * @date 19/08/2009
	 *
	 * @param FiltrarDocumentoCobrancaHelper filtro
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer filtrarCobrancaDocumentoCount(FiltrarDocumentoCobrancaHelper filtro) throws ErroRepositorioException;

	/**
	 * 
	 * UC0905 - Gerar Relatorio Acompanhamento Acao Cobrança
	 * 
	 * @author Genival Barbosa
	 * @date 26/08/2009
	 * 
	 * @param RelatorioAcompanhamentoAcoesCobrancaHelper helper
	 *            
	 * @return Lista de acoes de cobranca
	 */
	public List consultarColecaoAcaoCobranca(RelatorioAcompanhamentoAcoesCobrancaHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * Este método está de acordo com o UC[0258]Filtrar Documento de Cobranca
	 * 
	 * @author Anderson Italo
	 * @date 16/09/2009
	 *
	 * @param FiltrarDocumentoCobrancaHelper filtro
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List consultarDocumentosCobranca(FiltrarDocumentoCobrancaHelper filtro) throws ErroRepositorioException;
	
	/**
	 * [UC0959] Gerar Arquivo Texto de Pagamentos das Contas em Cobrança por Empresa
	 * 
	 * @author: Hugo Amorim
	 * @date: 05/10/2009
	 */
	public Collection pesquisarDadosArquivoTextoPagamentosContasCobrancaEmpresa(
			Integer idEmpresa,Integer referenciaInicial, Integer referenciaFinal, 
			Integer quantidadeRegistros , Integer numeroIndice,
			Integer idUnidadeNegocio) throws ErroRepositorioException;
	
	public Collection obterUnidadeNegocioPagamentosEmpresaCobrancaConta() throws ErroRepositorioException ;

	/**
	 * [UC0745] Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Sávio Luiz
	 */
	public CobrancaDocumento pesquisarCobrancaDocumentoImpressaoSimultanea(
			Date dataEmissao,Integer idImovel )
			throws ErroRepositorioException;

	/**
	 * [UC????] Relatorio Comando Documento Cobranca
	 * Alterado para verificar tipo da ação a partir da tabela documento_tipo
	 * @author Rômulo Aurélio, Anderson Italo
	 * 
	 * @data 20/10/2009, 04/05/2010
	 */
	public DocumentoTipo pesquisarTipoAcaoCobrancaParaRelatorio(
			Integer cobrancaAcaoAtividadeComando, Integer cobrancaAcaoAtividadeCronograma)
			throws ErroRepositorioException ;

	public Integer obterOrdemServicoAssociadaDocumentoCobranca(
			Integer idCobrancaDocumento) throws ErroRepositorioException;
	
	/**
	 *
	 * @author Rômulo Aurélio
	 * @date 20/10/2009
	 *
	 * @param idCobrancaDocumento
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public CobrancaDocumento pesquisarCobrancaDocumento(Integer idCobrancaDocumento) throws ErroRepositorioException ;
	
	/**
	 * Este método está de acordo com o [UC0901] Gerar Metas do Ciclo
	 * 
	 * @author Anderson Italo
	 * @date 21/09/2009
	 *
	 * @param Integer idCicloMeta
	 * @throws ErroRepositorioException
	 */
	public void removerCicloMetaGrupo(Integer idCicloMeta) throws ErroRepositorioException;

	
	/**
	 * retorna coleção de idEmpresaCobranca de uma Rota
	 * 
	 * @author Arthur Carvalho
	 * @date 06/11/2009
	 * 
	 * @param id da localidade 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarEmpresaCobrancaDaRota(Integer idLocalidade) throws ErroRepositorioException;

	
	/**
	 * [UC0630] - Solicitar Emissão do Extrato de Débitos
	 * Author: Rômulo Aurélio
	 * Data: 05/11/2009
	 * 
	 * Obtem os débitos a cobrar que estejam com PARC_ID = NULL,
	 * com SITUACAO NORMAL E FINANCIAMENTO_TIPO = 2
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoACobrarParceladoComIDNulo(Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * 
	 * atualiza  o valor do documento e o valor de desconto do documento de cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 12/11/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorDocumentoEValorDescontoCobrancaDocumento(
			Integer idCobrancaDocumento, BigDecimal valorDocumento,
			BigDecimal valorDesconto)throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * 
	 * pesquisa o documento de cobranca do imovel 
	 * e do documento tipo passado como parametro
	 * 
	 * @author Vivianne Sousa
	 * @date 19/11/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoDoImovel(
			Integer idImovel, Integer idDocumentoTipo)throws ErroRepositorioException;
	
	
	public void inserirCartaFinalAno(Integer idCobrancaGrupo,Integer idEmpresa,Integer idLocalidade,
			Integer codigoSetor,Integer numeroQuadra,Integer lote, Integer subLote,
			Integer sequencial,	String txt_parte1,String txt_parte2, Integer idRota) 
			throws ErroRepositorioException;
	
	public Collection pesquisarCartaFinalAnoGrupo(Integer idCobrancaGrupo) 
	throws ErroRepositorioException ;
	
	/**
	 * @author Vivianne Sousa
	 * @date 20/11/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarCartaFinalAno(Integer idRota) 
			throws ErroRepositorioException;
	
	/**
	 * @author Anderson Italo
	 * @date 26/11/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisPorGrupoCobranca(Integer idCobrancaGrupo, Integer gerencia, Integer unidade, Integer localidade, Integer setorComercial, Integer quadra) throws ErroRepositorioException;
	
	/**
	 * @author Davi Menezes
	 * @date 17/11/2011
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarQuantidadeImoveisPorGrupoCobrancaAgrupadosTitulo(Integer gerencia, Integer unidade, Integer localidade, Integer setorComercial, Integer quadra) throws ErroRepositorioException;
	
	/**
	 * @author Anderson Italo
	 * @date 30/11/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisPorComandoEventual(Integer idCobrancaAcaoAtividadeComando, Integer gerencia, Integer unidade, Integer localidade, Integer setorComercial, Integer quadra) throws ErroRepositorioException;
	
	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * [FS0008] – Verificar validade da data
	 *
	 * @author Raphael Rossiter
	 * @date 07/01/2010
	 *
	 * @param idCliente
	 * @param idArrecadacaoForma
	 * @return Short
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarNumeroDiasFloatCartao(Integer idCliente, Integer idArrecadacaoForma) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 *
	 * @author Raphael Rossiter
	 * @date 11/01/2010
	 *
	 * @param idCliente
	 * @param dataVencimento
	 * @return GuiaPagamento
	 * @throws ErroRepositorioException
	 */
	public GuiaPagamento pesquisarGuiaPagamentoCartaoCredito(Integer idCliente, Date dataVencimento) throws ErroRepositorioException ;
	
	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 *
	 * @author Raphael Rossiter
	 * @date 12/01/2010
	 *
	 * @param guiaPagamento
	 * @throws ErroRepositorioException
	 */
	public void atualizarGuiaPagamentoCartaoCredito(GuiaPagamento guiaPagamento) throws ErroRepositorioException ;
	
	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 *
	 * @author Raphael Rossiter
	 * @date 12/01/2010
	 *
	 * @return Localidade
	 * @throws ErroRepositorioException
	 */
	public Localidade pesquisarLocalidadeSede() throws ErroRepositorioException ;
	
	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 *
	 * @author Raphael Rossiter
	 * @date 12/01/2010
	 *
	 * @return DebitoTipo
	 * @throws ErroRepositorioException
	 */
	public DebitoTipo pesquisarDebitoTipoCartaoCredito() throws ErroRepositorioException ;
	
	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * [SB0004] – Incluir Dados da Confirmação dos Pagamentos
	 *
	 * @author Raphael Rossiter
	 * @date 18/01/2010
	 *
	 * @param idArrecadador
	 * @param dataLancamento
	 * @return AvisoBancario
	 * @throws ErroRepositorioException
	 */
	public AvisoBancario pesquisarAvisoBancario(Integer idArrecadador, Date dataLancamento) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * [SB0005  – Calcular Valor da  Dedução]
	 *
	 * @author Raphael Rossiter
	 * @date 19/01/2010
	 *
	 * @param idArrecadador
	 * @param idArrecadacaoForma
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ArrecadadorContratoTarifa pesquisarArrecadadorContratoTarifa(Integer idArrecadador, 
			Integer idArrecadacaoForma) throws ErroRepositorioException ;
	
	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * [SB0004] – Incluir Dados da Confirmação dos Pagamentos
	 *
	 * @author Raphael Rossiter
	 * @date 28/04/2010
	 *
	 * @param avisoBancario
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorAvisoBancario(AvisoBancario avisoBancario) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * [SB0004] – Incluir Dados da Confirmação dos Pagamentos 
	 *
	 * @author Raphael Rossiter
	 * @date 19/01/2010
	 *
	 * @param idAvisoBancario
	 * @return
	 * @throws ErroRepositorioException
	 */
	public AvisoDeducoes pesquisarAvisoDeducoes(Integer idAvisoBancario) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * [SB0004] – Incluir Dados da Confirmação dos Pagamentos 
	 *
	 * @author Raphael Rossiter
	 * @date 19/01/2010
	 *
	 * @param idAvisoBancario
	 * @param valorTotalAvisoDeducoes
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorAvisoDeducoes(Integer idAvisoBancario, BigDecimal valorTotalAvisoDeducoes) 
		throws ErroRepositorioException ;
	/**
	 *  [UC0349] Emitir Documento de Cobrança – Aviso de Corte
	 * 
	 * inserir na tabela temporaria os dados para gerar os arquivos do aviso de corte
	 * 
	 * @author Vivianne Sousa
	 * @date 11/12/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirDocumentoCobrancaImpressao(Integer idCobrancaDocumento,
			String linhaTxt, Integer idCobrancaAcaoAtividadeComando,
			Integer idCobrancaAcaoAtividadeCronograma, Integer sequencialImpressao)throws ErroRepositorioException;
	
	/**
	 * [UC0349] Emitir Documento de Cobrança – Aviso de Corte
	 * 
	 * @author Vivianne Sousa
	 * @date 11/12/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDocumentoCobrancaImpressao(
			Integer idCobrancaAcaoAtividadeComando,
			Integer idCobrancaAcaoAtividadeCronograma) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0349] Emitir Documento de Cobrança – Aviso de Corte
	 * 
	 * @author Vivianne Sousa
	 * @date 11/12/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarDocumentoCobrancaImpressao(Integer idCobrancaAcaoAtividadeComando,
			Integer idCobrancaAcaoAtividadeCronograma) throws ErroRepositorioException;
	
	/**
	 * [UC0349] Emitir Documento de Cobrança – Aviso de Corte
	 * 
	 * @author Vivianne Sousa
	 * @date 11/12/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarDocumentoCobrancaImpressao(
			Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException ;
	
	/**
	 * 
	 * [UC0987] Inserir Faixa de Dias Vencidos para Documentos a Receber
	 * 
	 * Verificar se existe Faixa inicial já cadastrada.
	 * 
	 * @author Hugo Leonardo
	 * @param  valorInicialFaixa
     * @throws ControladorException 
	 * @data  22/02/2010
	 *
	 * @return String
	 */
	public String verificarExistenciaFaixaInicial(Integer valorInicialFaixa) 
		throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0987] Inserir Faixa de Dias Vencidos para Documentos a Receber
	 * 
	 * Verificar se existe Faixa final já cadastrada.
	 * 
	 * @author Hugo Leonardo
	 * @param  valorFinalFaixa
     * @throws ControladorException 
	 * @data  22/02/2010
	 *
	 * @return Boolean
	 */
	public Integer verificarExistenciaFaixaFinal(Integer valorFinalFaixa) 
		throws ErroRepositorioException;
	
	
	/** 
	 * [UC990] Gerar Relatório de Documentos a Receber
	 *
	 * @author Hugo Amorim
	 * @param quantidadeMaxima 
	 * @param quantidadeInicio 
	 * @date 22/02/2010
	 *
	 */
	public Collection pesquisarRelatorioDocumentosAReceber(
			FiltroRelatorioDocumentosAReceberHelper helper,String tipoTotalizacao, int quantidadeInicio, int quantidadeMaxima
			) throws ErroRepositorioException;
	/** 
	 * [UC990] Count Relatório de Documentos a Receber
	 *
	 * @author Hugo Amorim
	 * @date 22/02/2010
	 *
	 */
	public Integer countRelatorioDocumentosAReceber(
			FiltroRelatorioDocumentosAReceberHelper helper)
			throws ErroRepositorioException;
	
	/**
	 * [UC????] Relatorio Comando Documento Cobranca
	 * Retorna a ação de cobrança para exibição 
	 * de parametros do relatório
	 * 
	 * @author Anderson Italo
	 * @data 04/05/2010
	 */
	public CobrancaAcao pesquisarAcaoCobrancaParaRelatorio(
			Integer cobrancaAcaoAtividadeComando, Integer cobrancaAcaoAtividadeCronograma)
			throws ErroRepositorioException;


//	/**
//	 *  [UC0251] Gerar Atividade de Ação de Cobrança
//	 * 
//	 * @author Vivianne Sousa
//	 * @date 07/04/2010
//	 */
//	public void inserirCobrancaDocumentoControleGeracao(
//			Integer idProcesso,
//			Integer quantidadeCobrancaDocumento, 
//			Integer quantidadeCobrancaDocumentoItem,
//			BigDecimal valorTotalCobrancaDocumento) 
//			throws ErroRepositorioException;

	/**
	 *  [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public void atualizarQuantidadeCobrancaDocumento(
			Integer idCobrancaDocumentoControleGeracao, 
			Integer quantidadeCobrancaDocumento) throws ErroRepositorioException;

	
	/**
	 *  [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public void atualizarCobrancaDocumentoControleGeracaoSomar(
			Integer idCobrancaDocumentoControleGeracao,
			Integer quantidadeCobrancaDocumento, 
			Integer quantidadeCobrancaDocumentoItem,
			BigDecimal valorTotalCobrancaDocumento)  throws ErroRepositorioException;

	/**
	 *  [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public Integer pesquisarQuantidadeCobrancaDocumento(
			Integer idCobrancaDocumentoControleGeracao) throws ErroRepositorioException;
	
	/**
	 *  [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public void atualizarCobrancaDocumentoControleGeracaoSomar(
			Integer idCobrancaDocumentoControleGeracao,
			Integer quantidadeCobrancaDocumentoItem,
			BigDecimal valorTotalCobrancaDocumento)  throws ErroRepositorioException;
	
	/**
	 *  [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public CobrancaDocumentoControleGeracao pesquisarCobrancaDocumentoControleGeracao(
			Integer idCobrancaDocumentoControleGeracao) throws ErroRepositorioException;
	/**
	 *  [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 19/04/2010
	 */
	public void atualizarCobrancaDocumentoControleGeracao(
			Integer quantidadeCobrancaDocumento, 
			Integer quantidadeCobrancaDocumentoItem,
			BigDecimal valorTotalCobrancaDocumento,
			Integer idCobrancaAcaoAtividadeComando,
			Integer idCobrancaAcaoAtividadeCronograma)
	throws ErroRepositorioException;

	/**
	 *  [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 19/04/2010
	 */
	public void atualizarCobrancaDocumentoControleGeracaoSubtrair(
			Integer idCobrancaDocumentoControleGeracao,
			Integer quantidadeCobrancaDocumento, 
			Integer quantidadeCobrancaDocumentoItem,
			BigDecimal valorTotalCobrancaDocumento)
	throws ErroRepositorioException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 29/04/2010
	 */
	public Collection pesquisarImovelCobrancaSituacaoPorImovel(
			Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 03/05/2010
	 */
	public Collection pesquisarDadosImovelCobrancaSituacaoPorImovel(
			Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * 	Atualiza valores do Documento de Cobrança
	 * de cartas.
	 * 
	 * @author Hugo Amorim
	 * @data 29/04/2010
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarValoresDocumentoCobrancaCartas(Integer id,
			BigDecimal descontoTotalPagamentoAVista,
			BigDecimal valorTotalImpostosConta) throws ErroRepositorioException;
	
	/**
	 * Author: Arthur Carvalho
	 * Data: 14/05/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTodosParcelamentosSituacaoNormal(String situacao) 
		throws ErroRepositorioException;

	
	
	/**
	 * [UC0998] Gerar Relação de Parcelamento - Visão Cartão de Crédito
	 * 
	 * Bean que preencherá o relatorio
	 * 
	 * @author Hugo Amorim
	 * @date 11/06/2010
	 *
	 */
	public Collection<Object[]> filtrarRelacaoParcelamentoCartaoCredito(
			FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1038] Prescrever Débitos de Imóveis
	 * 
	 * @author Hugo Leonardo
	 * @date 07/07/2010
	 * 
	 * @param idFuncionalidadeIniciada
	 * @param anoMesFaturamento
	 * @throws ErroRepositorioException
	 */
	public void prescreverDebitosDeImoveis(Integer anoMesFaturamento, Integer anoMesPrescricao, 
			Integer usuario, String idsCobrancaSituacao) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1038] Prescrever Débitos de Imóveis
	 * 
	 * @author Hugo Leonardo
	 * @date 07/07/2010
	 * 
	 * @param idFuncionalidadeIniciada
	 * @param anoMesFaturamento
	 * @throws ErroRepositorioException
	 */
	public Collection obterCobrancaSituacaoParaPrescreverDebitos() throws ErroRepositorioException;
	

	/**
	 * [UC0244] Manter Comando Ação de Cobrança
	 * 
	 * @author Hugo Amorim
	 * @created 14/07/2010
	 *
	 * @exception ErroRepositorioException
	 *             
	 */
	public void removerCobrancaAcaoAtividadeComandoFiscalizacaoSituacao(
			Integer idComando) throws ErroRepositorioException;
	
	/**
	 * Obtem os percentuais de desconto por tempo de inatividade a vista
	 * 
	 * [UC0214] - Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 20/07/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParcDesctoInativVista obterPercentualDescontoInatividadeAVista(
			Integer idPerfilParc, int qtdeMeses)throws ErroRepositorioException;
	
	/**
	 * [UC1038] Prescrever Débitos de Imóveis
	 * Pesquisa imoveis para execução do batch
	 * 
	 * @author Hugo Leonardo
	 * @date 19/07/2010
	 */
	public Collection obterContasPrescreverDebitosDeImoveis(Integer idLocalidade, 
			Integer anoMesFaturamento, String idsCobrancaSituacao, 
			int numeroIndice, int quantidadeRegistros) throws ErroRepositorioException;
	
	

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * Atualizar total de documentos, itens e valores realizados nos comandos
	 * de acao de cobranca
	 *  
	 * Data: 19/07/2010
	 * @author Arthur Carvalho
	 * 
	 * @param idCACM Identificador de CobrancaAcaoAtividadeComando
	 */
	public Object[] calcularTotaisComandoAcaoCobranca(Integer idCAAC) 
			throws ErroRepositorioException;
	
	/**
	 * 
	 * atualiza a forma da emissão do documento de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 12/08/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarFormaEmissaoCobrancaDocumento(
			Integer idCobrancaDocumento)throws ErroRepositorioException;
	
	/**
	 * Pesquisa Documentos de cobranças validos para imovel para determinado tipo de documento
	 * 
	 * @author Hugo Amorim
	 * @date 09/09/2010
	 */
	public Collection<CobrancaDocumento> pesquisarDadosCobrancaDocumentoValidoImovel(Integer idImovel,Integer idDocumentoTipo,
			Integer idAcaoCobranca)throws ErroRepositorioException;
	

	/**
	* [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	*
	* Data: 16/09/2010
	* @author Vivianne Sousa
	*/
	public Integer pesquisarCobrancaDocumentoFisc(
		Integer idFiscalizacaoSituacao,
		Integer idOrdemServico,
		Integer idCobrancaDocumento) throws ErroRepositorioException;
	
	/**
	 * [UC0852] – Incluir Débito a Cobrar de Entrada de Parcelamento Não Paga
	 * 
	 * @author Arthur Carvalho
	 * @date 22/09/2010
	 * @param parcelamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiaPagamentoDoParcelamentoPorEntradaNaoPaga(String parcelamento)
			throws ErroRepositorioException;
	
	/**
	 * 
	 *Pesquisa por todos os impostos a partir de uma fatura de um cliente responsavel federal
	 *
	 *OBS: O id da fatura passado tem que ser de uma fatura de um cliente responsavel federal
	 *
	 * @author Fernando Fontelles
	 * @param clienteID 
	 * @date 24/09/2010
	 *
	 * @param Integer anoMes
	 * @param Integer idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisaImpostoFaturaClienteResponsavelFederalAnalitico(Integer anoMesInicial, Integer anoMesFinal, Integer clienteID) 
						throws ErroRepositorioException ;

	/**
	 * [UC1112] Processar  Encerramento Ordens de Serviço da Ação de Cobrança
	 * 
	 * @author Mariana Victor
	 * @created 02/12/2010
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<Integer> pesquisarOrdemServicoParaEncerrar(Integer idCobrancaAcaoCronograma)
			throws ErroRepositorioException;
	
	/**
	 * [UC1112] Processar  Encerramento Ordens de Serviço da Ação de Cobrança
	 * 
	 * @author Mariana Victor
	 * @created 07/12/2010
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<Object[]> pesquisarAtividadeCronograma(Integer idCobrancaAcao)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarLocalidade(int idOrdemServico)
			throws ErroRepositorioException;
	
	/**
	 * [UC1112] Processar  Encerramento Ordens de Serviço da Ação de Cobrança
	 * 
	 * @author Mariana Victor
	 * @created 10/12/2010
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<Object[]> pesquisarAtivCronogOrdemServicoParaEncerrar(Integer idAtividadeCronograma)
			throws ErroRepositorioException;
	
	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança 
	 * [SB0003] Gerar Atividade de Ação de Cobrança para Imóvel
	 * 
	 * @author Vivianne Sousa
	 * @created 22/12/2010
	**/
	public Collection pesquisarIdDocumentoCobrancaParaImovel(Integer idImovel, 
			Integer idDocumentoTipo, CobrancaAcao acaoCobranca)throws ErroRepositorioException;
	
	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança 
	 * [SB0003] Gerar Atividade de Ação de Cobrança para Imóvel
	 * 
	 * @author Vivianne Sousa
	 * @created 22/12/2010
	**/
	public Integer pesquisarQtdeDocumentoCobrancaItemConta(Collection idsCobrancaDocumento, 
			Collection colecaoConta)throws ErroRepositorioException;
	
	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança 
	 * [SB0003] Gerar Atividade de Ação de Cobrança para Imóvel
	 * 
	 * @author Vivianne Sousa
	 * @created 22/12/2010
	**/
	public Integer pesquisarQtdeDocumentoCobrancaItemDebitoACobrar(Collection idsCobrancaDocumento, 
			Collection colecaoDebitoACobrar)throws ErroRepositorioException ;
	/**
	 * [UC676] Consultar Resumo Negativação
	 * 
	 * @author Ivan Sergio
	 * @date 14/01/2011
	 * 
	 * @param dadosConsultaNegativacaoHelper
	 * @param idSituacaoDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarNegativacaoLigacaoAguaPorSituacaoDebito(
			DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper, int idSituacaoDebito)
			throws ErroRepositorioException;
	
	/**
	 *  [UC0xxx] Emitir Documentos de Cobrança Em Lote
	 * 
	 * @author Mariana Victor
	 * @created 20/01/2011
	**/
	public Integer pesquisarQuantidadeContasDebito(Integer idCobrancaDocumento)
		throws ErroRepositorioException;
	
	/**
	 *  [UC0xxx] Emitir Documentos de Cobrança Em Lote
	 * 
	 * @author Mariana Victor
	 * @created 20/01/2011
	**/
	public List<String> pesquisarTipoDeCorte()
		throws ErroRepositorioException;
	

	/**
	 *  [UC0xxx] Emitir Documentos de Cobrança Em Lote
	 * 
	 * @author Mariana Victor
	 * @created 26/01/2011
	**/
	public List<String> pesquisarOcorrenciasFiscalizacao()
		throws ErroRepositorioException;
	
	
	/**
	 *  [UC0xxx] Emitir Documentos de Cobrança Em Lote
	 * 
	 * @author Rômulo Aurélio
	 * @created 17/02/2011
	**/
	
	public void atualizarDataRealizacaoCobrancaAcaoAtivCronograma(Integer idCobAcaoAtivCron) throws ErroRepositorioException;
	

	/**
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * Seleciona os itens do documento de cobrança correspondentes a guia
	 * 
	 * @author Mariana Victor
	 * @data 16/03/2011
	 * 
	 * @param CobrancaDocumento
	 * @return Collection<CobrancaDocumentoItem>
	 */
	public Collection<CobrancaDocumentoItem> selecionarCobrancaDocumentoItemReferenteGuia(
			CobrancaDocumento cobrancaDocumento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * Seleciona os itens do documento de cobrança correspondentes a débito à cobrar
	 * 
	 * @author Mariana Victor
	 * @data 16/03/2011
	 * 
	 * @param CobrancaDocumento
	 * @return Collection<CobrancaDocumentoItem>
	 */
	public Collection<CobrancaDocumentoItem> selecionarCobrancaDocumentoItemReferenteDebitoACobrar(
			CobrancaDocumento cobrancaDocumento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * Seleciona os itens do documento de cobrança correspondentes a credito à cobrar
	 * 
	 * @author Mariana Victor
	 * @data 16/03/2011
	 * 
	 * @param CobrancaDocumento
	 * @return Collection<CobrancaDocumentoItem>
	 */
	public Collection<CobrancaDocumentoItem> selecionarCobrancaDocumentoItemReferenteCreditoACobrar(
			CobrancaDocumento cobrancaDocumento)
			throws ErroRepositorioException;
	
	/**
	 *  [UC0968] Emitir Cartas da Campanha de Final de Ano 2009
	 * 
	 * inserir na tabela temporaria os dados para gerar os arquivos do aviso de corte
	 * 
	 * @author Mariana Victor
	 * @date 17/03/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirDocumentoCobrancaImpressaoFichaCompensasao(Integer idCobrancaDocumento,
			String linhaTxt, String conteudoFichaCompensacao, Integer idCobrancaAcaoAtividadeComando,
			Integer idCobrancaAcaoAtividadeCronograma, Integer sequencialImpressao) 
			throws ErroRepositorioException;

	/**
	 * [UC0968] Emitir Cartas da Campanha de Final de Ano 2009
	 * 
	 * @author Mariana Victor
	 * @date 17/03/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDocumentoCobrancaImpressaoFichaCompensacao(
			Integer idCobrancaAcaoAtividadeComando,
			Integer idCobrancaAcaoAtividadeCronograma) 
			throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisa por todos os impostos arrecadados a partir de um mês de referência
	 * de um determinado cliente federal ou de TODOS os clientes federais.
	 *
	 * @author Diogo Peixoto
	 * @date 23/03/2011
	 *
	 * @param Integer anoMesFerencia
	 * @param Integer idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImpostosArrecadacaoClienteResponsavelFederal(Integer anoMesInicial,Integer anoMesFinal, Integer idCliente) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 22/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarItemServicoContrato(
			Integer idGrupoCobranca, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletim(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimento(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento, Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepAsfalto(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepParalalo(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepCalcada(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	

	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimPorDesconto(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimPorDescontoSemDecursoPrazo(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarSituacaoAtualContaPeloCronogramaCobranca(Integer idGrupoCobranca, Integer referencia, Integer idContrato)
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public void atualizaIndicadorBoletimOS(Collection idsOS)
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public void atualizaIndicadorCobrancaAcaoOSNaoAceitas(Collection idsOSNaoAceitas)
			throws ErroRepositorioException; 
	
	/**
	 * 
	 * Pesquisa por todos os impostos arrecadados a partir de um mês de referência
	 * de um determinado cliente federal ou de TODOS os clientes federais.
	 *
	 * @author Diogo Peixoto
	 * @date 24/03/2011
	 *
	 * @param Integer anoMesFerencia
	 * @param Integer idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImpostosArrecadacaoClienteResponsavelFederalAnalitico(Integer anoMesInicial,Integer anoMesFinal, Integer idCliente) 
		throws ErroRepositorioException;
	
	/**
	 *  [UC1153] Solicitar Geração/Emissão Boletim de Medição de Cobrança
	 *  
	 *  [FS0002] – Ações não encerradas no cronograma.
	 * 
	 * @author Mariana Victor
	 * @created 21/03/2011
	**/
	public Integer pesquisarAcoesEncerradasCronograma(Integer anoMesReferencia, Integer idCobrancaGrupo, Integer idContrato)
	 		throws ErroRepositorioException;
	
	/**
	 *  [UC1152] Emissão Boletim Medição Cobrança
	 *  
	 *  Pesquisa os Itens de Serviço relacionados ao boletim de medição de cobrança selecionado
	 * 
	 * @author Mariana Victor
	 * @created 21/03/2011
	**/
	public Collection<Object[]> pesquisarItensServicoCobrancaBoletimDesconto(Integer idCobrancaGrupo,
			Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal)
		throws ErroRepositorioException;
	
	/**
	 *  [UC1152] Emissão Boletim Medição Cobrança
	 *  
	 *  Pesquisa os Itens de Serviço relacionados ao boletim de medição de cobrança selecionado
	 * 
	 * @author Mariana Victor
	 * @created 21/03/2011
	**/
	public Collection<Object[]> pesquisarItensServicoCobrancaBoletimExecutados(Integer idCobrancaGrupo,
			Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal)
		throws ErroRepositorioException;
	
	/**
	 *  [UC1152] Emissão Boletim Medição Cobrança
	 *  
	 *  Pesquisa os Itens de Serviço relacionados ao boletim de medição de cobrança selecionado
	 * 
	 * @author Mariana Victor
	 * @created 21/03/2011
	**/
	public Collection<Object[]> pesquisarItensServicoCobrancaBoletimSucesso(Integer idCobrancaGrupo,
			Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal)
		throws ErroRepositorioException;
	
	/**
	 *  [UC1152] Emissão Boletim Medição Cobrança
	 *  
	 *  De acordo com o código da constate do item, pesquisa os valores do mesmo.
	 * 
	 * @author Mariana Victor
	 * @created 22/03/2011
	**/
	public Object[] obterQuantidadeOSBoletimMedicaoCobranca(RelatorioBoletimMedicaoCobrancaHelper helper)
		throws ErroRepositorioException;

	/**
	 *  [UC1152] Emissão Boletim Medição Cobrança
	 *  
	 *  De acordo com o código da constate do item, pesquisa os valores do mesmo.
	 * 
	 * @author Mariana Victor
	 * @created 22/03/2011
	**/
	public Object[] obterSomatorioOSBoletimMedicaoCobranca(RelatorioBoletimMedicaoCobrancaHelper helper)
		throws ErroRepositorioException;

	/**
	 *  [UC1152] Emissão Boletim Medição Cobrança
	 *  
	 *  De acordo com o código da constate do item, pesquisa os valores do mesmo.
	 * 
	 * @author Mariana Victor
	 * @created 23/03/2011
	**/
	public Object[] obterQuantidadeOSBoletimMedicaoCobrancaDesconto(RelatorioBoletimMedicaoCobrancaHelper helper)
		throws ErroRepositorioException;

	/**
	 *  [UC1152] Emissão Boletim Medição Cobrança
	 *  
	 *  Consulta os valores da totalização da taxa de sucesso.
	 * 
	 * @author Mariana Victor
	 * @created 23/03/2011
	**/
	public Object[] obterTotalizacaoOSBoletimMedicaoCobrancaSucesso(RelatorioBoletimMedicaoCobrancaHelper helper)
		throws ErroRepositorioException;

	/**
	 *  [UC1152] Emissão Boletim Medição Cobrança
	 *  
	 *  Pesquisa dados da empresa e do contrado do boletim de cobrança
	 * 
	 * @author Mariana Victor
	 * @created 24/03/2011
	**/
	public Object[] pesquisarDadosBoletimMedicaoCobranca(Integer anoMesReferencia, Integer idCobrancaGrupo, Integer idContratoEmpresaServico)
		throws ErroRepositorioException;
	
	/**
	 * Gerar Relatório de Análise de Perdas com Crédito
	 * 
	 * [UC1155] Gerar Relatório de Análise de Perdas com Crédito
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param mesAno para análise
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> gerarRelatorioAnalisePerdasCreditos(String anoMesReferencia)
		throws ErroRepositorioException;
	
	/**
	 * Retorna o maior ano mesReferencia da tabela docs_a_rec_resumo
	 * 
	 * [UC1155] Gerar Relatório de Análise de Perdas com Crédito
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param mesAno para análise
	 * @throws ErroRepositorioException 
	 * @throws ErroRepositorioException
	 */
	public int maiorAnoMesReferenciaDocumentosAReceberResumo()throws ErroRepositorioException;
	

	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection<BigDecimal> pesquisarValorContaouContaHistorico(Integer idImovel, Integer referencia)
			throws ErroRepositorioException;
	
	/**
	 * [UC0870] Gerar Movimento de Contas em Cobrança por Empresa
	 * 
	 * Pesquisa a quantidade de contas associadas ao imóvel
	 * 
	 * @author: Mariana Victor
	 * @date: 13/04/2011
	 */
	public Integer pesquisarQuantidadeContasEmCobrancaPorImovel(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, Integer idImovel, SistemaParametro sistemaParametro) throws ErroRepositorioException;

	/**
	 * [UC0879] Gerar Extensão de Comando de Contas em Cobrança por Empresa -
	 * Pesquisa dados do popup
	 * 
	 * @author Mariana Victor
	 * @date 13/04/2011
	 */
	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoImovelPerfil(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC0879] Gerar Extensão de Comando de Contas em Cobrança por Empresa -
	 * Pesquisa dados do popup
	 * 
	 * @author Mariana Victor
	 * @date 13/04/2011
	 */
	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoGerenciaRegional(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC0879] Gerar Extensão de Comando de Contas em Cobrança por Empresa -
	 * Pesquisa dados do popup
	 * 
	 * @author Mariana Victor
	 * @date 13/04/2011
	 */
	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoUnidadeNegocio(Integer idComando) throws ErroRepositorioException;
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * @author: Mariana Victor
	 * @date: 13/04/2011
	 */
	public Integer pesquisarQuantidadeContasArquivoTextoContasCobrancaEmpresa(
			Collection ids, Integer idImovel) throws ErroRepositorioException;

	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * Pesquisa a Situação de cobrança a partir do código constante.
	 * 
	 * @author: Mariana Victor
	 * @date: 18/04/2011
	 */
	public Integer pesquisarCobrancaSituacao(Integer codigoConstante) throws ErroRepositorioException;
	public boolean pesquisarDebitoCobradoParcelamento(Integer codigoParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
	 * @author Rômulo Aurélio
	 * @throws ErroRepositorioException 
	 * @date 12/05/2011
	 */
	public ContratoParcelamento pesquisarContratoParcelamento(
			String numeroParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
	 * @author Rômulo Aurélio
	 * @throws ErroRepositorioException 
	 * @date 12/05/2011
	 */
	public Collection pesquisarDebitoContratoParcelamentoPorTipoDocumento(
			ContratoParcelamento contratoParcelamento, Integer idDocumentoTipo) throws ErroRepositorioException;

	/**
	 * [UC1167] Consultar Comandos de Cobrança por Empresa
	 * 
	 * Pesquisa os dados dos comandos
	 * 
	 * @author: Mariana Victor
	 * @date: 04/05/2011
	 */
	public Collection pesquisarDadosConsultarComandosContasCobrancaEmpresaResumido(
			Integer idEmpresa, Date cicloInicial, Date cicloFinal, int numeroIndice,int quantidadeRegistros)
			throws ErroRepositorioException;

	/**
	 * [UC1167] Consultar Comandos de Cobrança por Empresa
	 * 
	 * Pesquisa os dados de um comando para exibir no popup
	 * 
	 * @author: Mariana Victor
	 * @date: 04/05/2011
	 * @throws ErroRepositorioException  
	 */
	public Collection pesquisarDadosPopupExtensaoComandoCobranca
		(Integer idComando) throws ErroRepositorioException;
	
	/**
	 * [UC1167] Consultar Comandos de Cobrança por Empresa
	 * 
	 * - Pesquisa dados da cobrança
	 * 
	 * @author: Mariana Victor
	 * @date: 06/05/2011
	 * @throws ErroRepositorioException 
	 * 
	 */
	public Collection pesquisarValorTotalCobrancaComandoEmpresa
		(Integer idComando) throws ErroRepositorioException;
	
	/**
	 * [UC1167] Consultar Comandos de Cobrança por Empresa
	 * 
	 * Pesquisa a quantidade de contas, agrupando por imóvel
	 * 
	 * @author: Mariana Victor
	 * @date: 06/05/2011
	 * @throws ErroRepositorioException 
	 * 
	 */
	public Collection pesquisarValorTotalCobrancaComandoEmpresaPorImovel
		(Integer idComando) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobrança por Empresa
	 * 
	 * @author Mariana Victor
	 * @data 09/05/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarIndicadorGeracaoTxt(Collection idsComandos)
		throws ErroRepositorioException;
	
	/**
	 * [UC1168] Encerrar Comandos de Cobrança por Empresa
	 *
	 * Pesquisa os ids dos imóveis e das ordens de serviços geradas para um determinado comando 
	 *
	 * @author Mariana Victor
	 * @created 09/05/2011
	 * @throws ErroRepositorioException 
	 * 
	 */
	public Collection<Object[]> pesquisarImovelOrdemServicoParaEncerrarComando
		(int quantidadeInicio, Integer idComando) throws ErroRepositorioException;

	/**
	 * 
	 * [UC1168] Encerrar Comandos de Cobrança por Empresa
	 * 
	 * @author Mariana Victor
	 * @data 09/05/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarDataEncerramentoComando(Integer idComando) throws ErroRepositorioException;

	/**
	 * 
	 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
	 * 
	 * Gerar OS
	 * 
	 * @author Mariana Victor
	 * @data 17/05/2011
	 */
	public Collection<Integer> pesquisarIdsImoveis(MovimentarOrdemServicoGerarOSHelper helper) throws ErroRepositorioException;

	/**
	 * [UC1136] Inserir Contrato Parcelamento Por Cliente
	 * 
	 * Pesquisa a Referencia da conta por ID
	 * 
	 * @author: Paulo Diniz
	 * @date: 14/05/2011
	 */
	public Integer pesquisarReferenciaContaPorId(Integer idConta) throws ErroRepositorioException;
	
	/**
	 * [UC1136] Inserir Contrato Parcelamento Por Cliente
	 * 
	 * Verifica Conta Vinculada A Contrato Parcelamento Ativo
	 * 
	 * @author: Paulo Diniz
	 * @date: 14/05/2011
	 */
	public boolean verificaContaVinculadaAContratoParcelAtivo(Integer idConta) throws ErroRepositorioException;
	
	/**
	 * [UC1136] Inserir Contrato Parcelamento Por Cliente
	 * 
	 * Verifica Guia Vinculada A Contrato Parcelamento Ativo
	 * 
	 * @author: Paulo Diniz
	 * @date: 14/05/2011
	 */
	public boolean verificaGuiaVinculadaAContratoParcelAtivo(Integer idGuia) throws ErroRepositorioException;
	/**
	 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
	 * 
	 * Emitir OS Gerada pela Empresa
	 * 
	 * @author Mariana Victor
	 * @data 18/05/2011
	 */
	public Collection<Object[]> pesquisarDadosOSGeradasPelaEmpresa(Integer idComando,
			Integer idTipoServico) throws ErroRepositorioException;

	/**
	 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
	 * 
	 * Emitir OS de Registro de Atendimento
	 * 
	 * @author Mariana Victor
	 * @data 18/05/2011
	 */
	public Collection<Object[]> pesquisarDadosOSRegistroAtendimento(Integer idComando, Integer idTipoServico) throws ErroRepositorioException;
	
	/**
	 * obtem contas em débito do imóvel, comparando a data de vencimento original
	 * usado no emitir contas da CAEMA
	 * 
	 * Author: Vivianne Sousa
	 * Data: 15/06/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasImovelDataVencimentoOriginal(
			Integer idImovel,
			int indicadorPagamento, 
			int indicadorConta,
			String contaSituacaoNormal, 
			String contaSituacaoRetificada,
			String contaSituacaoIncluida, 
			String anoMesInicialReferenciaDebito,
			String anoMesFinalReferenciaDebito,
			Date anoMesInicialVecimentoDebito, 
			Date anoMesFinalVencimentoDebito, 
			int indicadorDividaAtiva)
			throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0006 – Validar Comando]" 
	 * 
	 * @author Mariana Victor
	 * @data 20/06/2011
	 */
	public ComandoEmpresaCobrancaConta pesquisarComandoEmpresaCobrancaConta(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0007 – Validar OS]" 
	 * 
	 * @author Mariana Victor
	 * @data 20/06/2011
	 */
	public Short pesquisarSituacaoOrdemServico(Integer numeroOS) throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0007 – Validar OS]" 
	 * 
	 * @author Mariana Victor
	 * @data 20/06/2011
	 */
	public Boolean verificarOrdemServicoComando(Integer numeroOS, Integer idComando) throws ErroRepositorioException;
	
	/**
	 * [UC1183] Gerar Arquivo Txt OS Contas Pagas Parceladas
	 * 
	 * @author Paulo Diniz
	 * @throws ErroRepositorioException 
	 * @data 30/06/2011
	 */
	public List<Object[]> pesquisarOrdensServicoContasPagasParceladas() throws ErroRepositorioException;
	
	/**
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
	 * Pesquisar EmpresaCobrancaConta a partir do imóvel
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	public Collection obterColecaoEmpresaCobrancaContaResultadoporImovel(Integer id, Integer tipoServico) throws ErroRepositorioException;
	
	/**
	 * [UC1183] Gerar Arquivo Txt OS Contas Pagas Parceladas	
	 * @author Mariana Victor, Ana Maria	 
	 * @throws ErroRepositorioException 	 
	 * @data 02/07/2011	 
	 * */	
	public Collection<Object[]> pesquisarEmpresasComandosCobrancaAtivosExecutados() throws ErroRepositorioException;	
	
	/**	
	 * [UC1183] Gerar Arquivo Txt OS Contas Pagas Parceladas
	 * 	 
	 * 2. O sistema deverá verificar todos os comandos de cobrança por resultados ativos e executados 	
	 * 
	 * @author Mariana Victor, Ana Maria	 
	 * @throws ErroRepositorioException 	 
	 * @data 02/07/2011	
	 * */	
	public Collection<Integer> pesquisarComandosCobrancaAtivosExecutados(Integer idEmpresa) throws ErroRepositorioException;		
	
	/**	
	 * [UC1183] Gerar Arquivo Txt OS Contas Pagas Parceladas	
	 * 
	 * 2.1.	Para cada comando selecionado o sistema deverá selecionar os ordens de serviço ativas associadas ao mesmo 	 
	 * 
	 * @author Mariana Victor, Ana Maria	 
	 * @param contadorSuperior 
	 * @param contadorInferior 
	 * @throws ErroRepositorioException 	 
	 * @data 02/07/2011	 */	
	public Collection<Object[]> pesquisarOrdensServicoAtivasComando(Integer idComando) throws ErroRepositorioException;	
	
	/**	 
	 * [UC1183] Gerar Arquivo Txt OS Contas Pagas Parceladas	 
	 * 2.1.1. Para cada Ordem de Serviço selecionada, o sistema deverá verificar se as contas associadas ao imóvel da ordem de serviço estão quitadas ou parceladas  	 
	 * 
	 * @author Mariana Victor, Ana Maria	 
	 * @throws ErroRepositorioException 	 
	 * @data 02/07/2011	 
	 * */	
	public Boolean verificarExisteContasEmAberto(Integer idOS) throws ErroRepositorioException;

	/**
     * [UC0067] Obter Débito do Imóvel ou Cliente
	 * 
	 * @author Mariana Victor
	 * @date 21/07/2011
	 * 
	 * @param idDebitoACobrar
	 * 
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean verificaDebitoACobrarVinculadoAContratoParcelAtivo(Integer idDebitoACobrar) throws ErroRepositorioException;
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * [SB0020] - Processar Pagamento de Contrato Parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 03/08/2011
	 */
	public Object[] obterDadosDocumentoCobrancaItemContratoParcelamento(Integer idPrestacao) 
		throws ErroRepositorioException;

	/**
	 * [UC 0869] Gerar Arqv Texto das Contas em Cobrança por Empresa
	 * 
	 * @author Paulo Diniz
	 * @data 03/08/2011
	 * 
	 */
	public Object[] pesquisarDadosQtdContasEDiasVencidos(Integer idComando) throws ErroRepositorioException;
	
	
	/**
	 * [UC1167] Consultar Comandos de Cobrança por Empresa
	 * Pesquisa dados do popup
	 * 
	 * @author Hugo Azevedo
	 * @date 25/08/2011
	 */
	
	public Collection pesquisarDadosPopupExtensaoComandoAguaSituacao(
			Integer idComando) throws ErroRepositorioException;

	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento, Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepAsfaltoRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepParalaloRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepCalcadaRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimPorDescontoRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException 
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimPorDescontoSemDecursoPrazoRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC 0869] Gerar Arqv Texto das Contas em Cobrança por Empresa
	 * 
	 * @author Paulo Diniz
	 * @data 03/08/2011
	 * 
	 */
	public Collection<CmdEmpresaCobrancaContaLigacaoAguaSituacao> pesquisarColecaoLigacaoAguaSituacaoPorComandoEmpresaCobrancaConta(Integer idComando)
	throws ErroRepositorioException;
	
	
	/**
	 * 
	 * [UC1233] - Encerrar Ordem de Servico de Visita de Cobrança
	 * 
	 * @author Hugo Azevedo
	 * @date 23/09/2011
	 */
	public Collection<Integer> obterColecaoOrdemServicoVisitaCobranca(String idGrupo,
			String mesAno) throws ErroRepositorioException;
	
	/**
	 * [UC0067] Obter Débito do Imóvel ou Cliente
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2011
	 * 
	 * @param idImovel
	 * @return Short
	 * @throws ErroRepositorioException
	 */
	public Short obterIndicadorAcrescimosClienteResponsavel(Integer idImovel) throws ErroRepositorioException ;
	
	/**
	 * [UC0067] Obter Débito do Imóvel ou Cliente
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2011
	 * 
	 * @param idImovel
	 * @return Short
	 * @throws ErroRepositorioException
	 */
	public Short obterIndicadorAcrescimosCliente(Integer idCliente) throws ErroRepositorioException ;
	
	/**
	 * [UC1156] Emitir Documentos de Cobrança Em Lote
	 * 
	 * Método que pesquisa o endereço da unidade de negocio
	 * a partir da localidade do imovel
	 * 
	 * @author Raimundo Martins
	 * @return id da localidade da unidade de negocio
	 * */
	
	public Integer obterEnderecoUnNegocioLocalImovel(Integer idLocalidadeImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0243] Inserir Comando de Ação de Cobrança
	 * 
	 * @author Raphael Rossiter
	 * @date 21/10/2011
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeRotasPorGrupoCobranca(Integer idGrupoCobranca) throws ErroRepositorioException ;
	
	/**
	 * [UC0214] Inserir Ação de Cobrança
	 * 
	 * @author Raphael Rossiter
	 * @created 07/11/2011
	 * 
	 * @param idCobrancaAcao
	 * @param textoPersonalizado
	 * @throws ErroRepositorioException
	 */
	public void atualizarCobrancaAcaoTextoPersonalizado(Integer idCobrancaAcao, String textoPersonalizado)
	throws ErroRepositorioException ;
	
	/**
	 * [UC0214] Consultar Comando de Ação de Cobrança
	 * 
	 * @author Erivan Sousa
	 * @date 04/11/2011
	 * 
	 * @return String
	 * @throws ErroRepositorioException
	 */
	public String buscarTextoPersonalizadoAcaoCobranca(Integer idAcaoCobranca) throws ErroRepositorioException ;
	
	/**	 
	 *  [UC0867] Atualizar Pagamentos das Contas em Cobrança	 
	 * Metodo que pesquisa contas canceladas associadas	 
	 * a comandos de cobrança no mes de arrecadação
	 * @author Raimundo Martins	 
	 * @date 03/10/2011	 
	 **/
	public Collection<Object[]> pesquisarContasCanceladasMesAssocComandosCobranca(Integer anoMesArrecadacao, 
	 Integer debitoCreditoSituacao)throws ErroRepositorioException;	
 
	 /**	 
	  * [UC0867] Atualizar Pagamentos das Contas em Cobrança	 
	  * Metodo que retorna a soma data de encerramento do contrato 	 
	  * mais quantidade de meses válido para pagamento de uma 	 
	  * determinada conta	 
	  * @author Raimundo Martins	 
	  * @date 06/10/2011	 
	  **/		
 	public Date pesquisarDataEncerContratoMaisQtdMesValPgmt(Integer idConta) throws ErroRepositorioException;
 	
 	/**
 	 * [UC0867] Atualizar Pagamentos das Contas em Cobrança
 	 * 
 	 * metodo que pesquisa os comandos encerrados sem
 	 * penalidades geradas
 	 * 
 	 * @author Raimundo Martins
 	 * @date 20/10/2011
 	 * */ 	
 	public Collection<Object[]> pesquisarComandosEncerradosSemPenalidades() throws ErroRepositorioException;
 	
 	/**
	 * [UC0867] Atualizar Pagamentos das Contas em Cobrança
	 * 
	 * Metodo que pesquisa o ComandoEmpresaCobrancaConta
	 * que possui o id informado
	 * 
	 * @author Raimundo Martins
	 * @date 20/10/2011
	 * */	
	public ComandoEmpresaCobrancaConta pesquisarDadosComandoEmpresaCobrancaConta(Integer idComando)throws ErroRepositorioException;
	
	/**
	 * [UC120] Gerar Boletim de Cobrança por Resultado
	 * 
	 * Método que pesquisa os dados para a geração
	 * do boletim de cobrança por resultado
	 * 
	 * @author Raimundo Martins
	 * @date 24/10/2011
	 * */	
	public Collection<Object[]> pesquisarDadosBoletimCobrancaResultado() throws ErroRepositorioException;
	
	/**
	 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
	 * 
	 * @author Hugo Azevedo
	 * @date 13/10/2011
	 */
	public Collection<Empresa> obterColecaoEmpresasContratadasCobranca() throws ErroRepositorioException ;
	
	/**
	 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
	 * 
	 * @author Hugo Azevedo
	 * @date 13/10/2011
	 */
	public Collection<Regiao> obterColecaoRegioes() throws ErroRepositorioException;
	
	/**
	 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
	 * 
	 * @author Hugo Azevedo
	 * @date 13/10/2011
	 */
	public Collection<Microrregiao> obterColecaoMicroRegioes(String[] idsRegiao) throws ErroRepositorioException;
	
	/**
	 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
	 * 
	 * @author Hugo Azevedo
	 * @date 13/10/2011
	 */
	public Collection<Municipio> obterColecaoMunicipios(String[] idsRegiao, String[] idsMicroRegiao) throws ErroRepositorioException;
	
	/**
	 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
	 * 
	 * @author Hugo Azevedo
	 * @date 13/10/2011
	 */
	public Collection<UnidadeNegocio> obterColecaoUnidadeNegocio(String[] idsGerencias) throws ErroRepositorioException;
	
	/**
	 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
	 * 
	 * @author Hugo Azevedo
	 * @date 13/10/2011
	 */
	public Collection<Localidade> obterColecaoLocalidade(Integer idLocalidade, String[] idsGerencias,String[] idsUnidadeNegocio) throws ErroRepositorioException;
	
	/**
	 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
	 * 
	 * @author Hugo Azevedo
	 * @date 17/10/2011
	 */
	public Collection<Object[]> gerarDadosRelatorioBoletimMedicaoAcompanhamentoGeralResumido (
			Integer idEmpresa,
			String periodoApuracao,
			Integer idLocalidade,
			String[] idsGerenciaRegional,
			String[] idsUnidadeNegocio,
			String[] idsRegiao,
			String[] idsMicroRegiao,
			String[] idsMunicipio,
			short indicadorOperacao,
			short indicadorLocalidade,
			ArrayList colecaoEmpresaCobrancaFaixa
		) throws ErroRepositorioException;
	
	
	
	/**
	 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
	 * 
	 * @author Hugo Azevedo
	 * @date 17/10/2011
	 */
	public Collection<Object[]> gerarDadosRelatorioBoletimMedicaoAcompanhamentoGeralAnalitico (
			Integer idEmpresa,
			String periodoApuracao,
			Integer idLocalidade,
			String[] idsGerenciaRegional,
			String[] idsUnidadeNegocio,
			String[] idsRegiao,
			String[] idsMicroRegiao,
			String[] idsMunicipio,
			short indicadorOperacao,
			short indicadorLocalidade,
			ArrayList colecaoEmpresaCobrancaFaixa
		) throws ErroRepositorioException;
	
	
	/**
	 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
	 * [SB0004] - Emitir Relatório de Acompanhamento dos Parcelamentos - Recuperação de Créditos
	 * 
	 * 2.4.5. Quantidade de Parcelas Pagas
	 * 2.4.6. Valor Pago
	 * 
	 * Retorna a quantidade de parcelas pagas, bem como o valor total quitado.
	 * 
	 * @author Hugo Azevedo
	 * @date 24/10/2011
	 */
	
	public Collection obterParcelasPagasRecuperacaoCreditos(Integer idImovel) throws ErroRepositorioException;
	
	
	/**
	 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
	 * [SB0004] - Emitir Relatório de Acompanhamento dos Parcelamentos - Recuperação de Créditos
	 * 
	 * 2.4.7. Quantidade de Parcelas Em Aberto
	 * 2.4.8. Saldo em Aberto
	 * 
	 * @author Hugo Azevedo
	 * @date 24/10/2011
	 */
	
	public Collection obterDebitosQtdParcelasACobrarRecuperacaoCreditos(Integer idImovel) throws ErroRepositorioException;
	
	
	/**
	 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
	 * [SB0005] - Emitir Relatório de Acompanhamento dos Parcelamentos em Atraso - Recuperação de Créditos
	 * 
	 * 2.4.6. Quantidade de Parcelas Em Atraso
	 * 
	 * @author Hugo Azevedo
	 * @date 24/10/2011
	 */
	public Collection obterParcelasEmAtrasoRecuperacaoCreditos(Integer idImovel) throws ErroRepositorioException;
	
	
	
	/**
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
	 * Pesquisar Valor enviado e quantidade de contas enviadas na cobranca por Resultado
	 * 
	 * @author Rômulo Aurélio
	 * @data 24/10/2011
	 */
	
	public Collection obterValorEnviadoCobrancaPorResultado(OrdemServico os) throws ErroRepositorioException;

	
	/**
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
	 * Pesquisar Valor Pago e quantidade de contas Pagas na cobranca por Resultado
	 * 
	 * @author Rômulo Aurélio
	 * @data 24/10/2011
	 */
	
	public Collection obterValorPagoCobrancaPorResultado(OrdemServico os) throws ErroRepositorioException;
	
	/**
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
	 * Pesquisar Valor Parcelado na cobranca por Resultado
	 * 
	 * @author Rômulo Aurélio
	 * @data 24/10/2011
	 */
	
	public Collection obterValorParceladoCobrancaPorResultado(OrdemServico os)throws ErroRepositorioException;
	
	
	/**
	 * [UC1239] Gerar Relatório de Penalidades por Índice de Atuação e Sucesso Financeiro
	 * 
	 * @author Hugo Azevedo
	 * @date 27/10/2011
	 */
	
	public Collection<Object[]> obterComandosEmPenalidades(Integer idEmpresa, Date dataInicial, Date dataFinal) throws ErroRepositorioException;
	
	/**
	 * [UC1156] Emitir Documentos de Cobrança Em Lote
	 * 
	 * [SB0005] - Obter Motivo de Encerramento da Ação de Cobrança
	 * 
	 * 1. Seleciona os motivos de encerramento da ação de cobrança 
	 * 
	 * @author Mariana Victor
	 * @date 26/10/2011
	 * 
	 */
	public Collection<AtendimentoMotivoEncerramento> selecionaMotivosEncerramentoAcaoCobranca(
		Integer idAcaoCobranca) throws ErroRepositorioException;
	
	/**	 
	 *  [UC0867] Atualizar Pagamentos das Contas em Cobrança	 
	 * Metodo que remove contas canceladas associadas	 
	 * a comandos de cobrança no mes de arrecadação
	 * @author Raimundo Martins	 
	 * @date 28/10/2011	 
	 **/	
	public void removerContasCanceladasMesAssocComandosCobranca(Integer anoMesArrecadacao) 
			throws ErroRepositorioException;
	
	/**
	 * [UC120] Gerar Boletim de Cobrança por Resultado
	 * 
	 * Método que remove os dados para a geração
	 * do boletim de cobrança por resultado a partir
	 * do mes de arrecadação
	 * 
	 * @author Raimundo Martins
	 * @date 28/10/2011
	 * */	
	public void removerDadosBoletimCobrancaResultado(Integer anoMesArrecadacao) throws ErroRepositorioException;
	
	/**
	 * [UC0867] Atualizar Pagamentos das Contas em Cobrança
	 * 
	 * Método que pesquisa o comando a partir do ID
	 * de EmpresaCobrancaConta
	 *
	 * @author Raimundo Martins
	 * @date 28/10/2011
	 * */
	
	public ComandoEmpresaCobrancaConta pesquisarComandoEmpresaCobrancaContaPorEmpresaCobrancaConta(Integer idEmpresaCobrancaConta)
			throws ErroRepositorioException;

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * [SB0003] - Gerar Atividade de Ação de Cobrança o Imóvel
	 * 
	 * 2.3.1. Caso exista ação de cobrança para o motivo de encerramento 
	 * 
	 * @author Mariana Victor
	 * @date 28/10/2011
	 */
	public boolean existeCobrancaAcaoMotivoEncerramento(
		Integer idAcaoCobrancaPrecedente) throws ErroRepositorioException;
	
	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * [SB0003] - Gerar Atividade de Ação de Cobrança o Imóvel
	 * 
	 * 2.3.1.1.	Caso não exista ordem de serviço, relativa à ação precedente, 
	 *  realizada até a data mínima de emissão/realização da ação precedente
	 * 
	 * @author Mariana Victor
	 * @date 28/10/2011
	 */
	public Integer documentoCobrancaOrdemServicoAcaoPrecedente(Integer idDocumentoCobrancaAcaoPrecedente, Integer idAcaoPrecedente,
			Date dataMinimaEmissaoRealizacaoAcaoPrecente) 
			throws ErroRepositorioException;


	/**
	 * [UC1238] Gerar Relatório de Acompanhamento dos Comandos de Cobrança
	 * 
	 * Pesquisa a quantidade de comandos
	 * 
	 * @author: Mariana Victor
	 * @date: 04/05/2011
	 */
	public Integer pesquisarQuantidadeComandosContasCobrancaEmpresa(
			Integer idEmpresa, Date cicloInicial, Date cicloFinal) throws ErroRepositorioException;

	/**
	 * [UC1238] Gerar Relatório de Acompanhamento dos Comandos de Cobrança
	 * 
	 * Pesquisa os dados dos comandos
	 * 
	 * @author: Mariana Victor
	 * @date: 04/05/2011
	 */
	public Collection<Object[]> pesquisarTotalizacaoComandoContasCobrancaEmpresa(
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa,
			Integer idEmpresa, Date cicloInicial, Date cicloFinal) 
			throws ErroRepositorioException;
	
	
	/**
	 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
	 * 
	 * @author Hugo Azevedo
	 * @date 17/10/2011
	 */
	public Collection<Object[]> gerarDadosRelatorioBoletimMedicaoAcompanhamentoResumidoParcelamento (
			Integer idEmpresa,
			String periodoApuracao,
			Integer idLocalidade,
			String[] idsGerenciaRegional,
			String[] idsUnidadeNegocio,
			String[] idsRegiao,
			String[] idsMicroRegiao,
			String[] idsMunicipio,
			short indicadorOperacao,
			short indicadorLocalidade,
			ArrayList colecaoEmpresaCobrancaFaixa
			
		) throws ErroRepositorioException;
	
	
	/**
	 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
	 * 
	 * @author Hugo Azevedo
	 * @date 17/10/2011
	 */
	public Collection<Object[]> gerarDadosRelatorioBoletimMedicaoAcompanhamentoParcAnalitico (
			Integer idEmpresa,
			String periodoApuracao,
			Integer idLocalidade,
			String[] idsGerenciaRegional,
			String[] idsUnidadeNegocio,
			String[] idsRegiao,
			String[] idsMicroRegiao,
			String[] idsMunicipio,
			short indicadorOperacao,
			short indicadorLocalidade,
			ArrayList colecaoEmpresaCobrancaFaixa
			
		) throws ErroRepositorioException;
	
	
	/**
	 * [UC1237] Gerar Relatório de Boletim de Medição e Acompanhamento
	 * 
	 * @author Hugo Azevedo
	 * @date 17/10/2011
	 */
	public Collection<Object[]> gerarDadosRelatorioBoletimMedicaoAcompanhamentoParcEmAtrasoAnalitico (
			Integer idEmpresa,
			String periodoApuracao,
			Integer idLocalidade,
			String[] idsGerenciaRegional,
			String[] idsUnidadeNegocio,
			String[] idsRegiao,
			String[] idsMicroRegiao,
			String[] idsMunicipio,
			short indicadorOperacao,
			short indicadorLocalidade,
			ArrayList colecaoEmpresaCobrancaFaixa
			
		) throws ErroRepositorioException;
	/**
	 * [UC1153] Solicitar Geração/Emissão Boletim de Medição de Cobrança
	 * 
	 * Metódo que pesquisa as ações de penalidade
	 * a partir do grupo e o mês de referencia
	 * 
	 * @author Raimundo Martins
	 *
	 * @date 10/11/2011
	 */	
	public Collection<Object[]> pesquisarAcoesPenalidadesPorGrupoMes(Integer idGrupo, Integer anoMes, Integer idContrato)throws ErroRepositorioException;
	
	/**
	 * [UC 1153] Solicitar Geração / Emissão Boletim de Medição
	 * 
	 * Metodo que retorna a CobrancaAçãoCronograma a partir
	 * da Ação, Ano/Mes e Grupo de ação de cobrança
	 * 
	 * @author Raimundo Martins
	 * @date 17/11/2011
	 * */
	public CobrancaAcaoCronograma pesquisarCobrancaAcaoCronograma(Integer idGrupo, Integer anoMes, Integer idAcaoCobranca, Integer idContratoEmpresaServico)
			throws ErroRepositorioException;
	
	/**
	 * [UC 1151] Gerar Boletim Medição
	 * 
	 * Metodo que pesquisa as justificativas de
	 * não penalidades para o grupo de cobrança
	 * a partir do ano / mês informado
	 * 
	 * @author Raimundo Martins
	 * @date 17/11/2011
	 * */
	public Collection<Integer> pesquisarNaoPenalidades(Integer idGrupo, Integer anoMes, Integer idContrato)throws ErroRepositorioException;
	
	/**
	 * [UC 1151] Gerar Boletim Medição
	 * 
	 * Metodo que atualiza a tabela cobranca.bol_med_ac_pen_just
	 * inserindo o id do boletim gerado para as justificativas
	 * de não penalidades geradas para um grupo de cobrança 
	 * 
	 * @author Raimundo Martins
	 * @date 17/11/2011
	 * */
	public void atualizarBoletimGeradoEmBolMedAcPenJust(Integer idBmpj, Integer idBoletimGerado) throws ErroRepositorioException;
	
	/**
	 * [UC 1152] Emitir Boletim Medição Cobrança
	 * 
	 * Metodo que pesquisa a justificativa da penalidade
	 * de um boletim de medição
	 * 
	 * @author Raimundo Martins
	 * @date 22/11/2011
	 * */
	public BoletimMedicaoJustificativaPenalidade pesquisarBoletimMedicaoJustificativaPenalidade(Integer idBoletim) 
			throws ErroRepositorioException;

	/**
	 * [UC1250] Solicitar Geração/Emissão Boletim de Medição de Contratos
	 * 
	 *  1.5.1. O usuário informa o filtro e ao clicar no botão "Selecionar" o sistema deverá exibir, 
	 * em uma tabela, todos os boletins de medição 
	 * 
	 * @author Mariana Victor
	 * @date 21/11/2011
	 * */
	public Collection<Object[]> pesquisarBoletimMedicaoContrato(Integer idContrato, String descricaoContrato ,Integer anoMes)
			throws ErroRepositorioException;

	/**
	 * [UC1250] Solicitar Geração/Emissão Boletim de Medição de Contratos
	 * 
	 * [SB0001] - Gerar Boletim Medição de Contrato
	 * 
	 *  2. E, para cada boletim de medição selecionado, atualiza os dados da tabela COBR_BOLETIM_MEDICAO:
	 * 
	 * @author Mariana Victor
	 * @data 21/11/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarBoletimMedicaoCobranca(String[] idsBoletim, Integer idCobrancaBoletimContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1250] Solicitar Geração/Emissão Boletim de Medição de Contratos
	 * 
	 * [SB0002] - Emitir Boletim de Contrato
	 * 
	 * @author Mariana Victor
	 * @date 22/11/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @param relatorioDefinitivo
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioAcompanhamentoBoletimMedicaoContrato(
			FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) throws ErroRepositorioException;

	/**
	 * 
	 * [UC1250] Solicitar Geração/Emissão Boletim de Medição de Contratos
	 * 
	 * [SB0002] - Emitir Boletim de Contrato
	 * 
	 * Método que vai retornar as quantidades acumuladas e os valores acumulados
	 * no período para geração do relatório de acompanhamento do boletim de medição de cobrança.
	 * 
	 * @author Mariana Victor
	 * @date 22/11/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioAcompanhamentoBoletimMedicaoContratoAcumuladas(
		FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1250] Solicitar Geração/Emissão Boletim de Medição de Contratos
	 * 
	 * [SB0002] - Emitir Boletim de Contrato
	 * 
	 * @author Mariana Victor
	 * @date 22/11/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarTaxaSucessoBoletimMedicaoContrato(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1250] Solicitar Geração/Emissão Boletim de Medição de Contratos
	 * 
	 * [SB0002] - Emitir Boletim de Contrato
	 * 
	 * @author Mariana Victor
	 * @date 22/11/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 *
	 * @return Collection<BigDecimal>
	 * @throws ErroRepositorioException
	 */
	public Collection<BigDecimal> filtrarRelatorioAcompanhamentoBoletimMedicaoContratoPenalidades(FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * Pesquisa os dados dos imóveis
	 * 
	 * @author Mariana Victor
	 * @date 25/11/2011
	 */
	public Collection<Object[]> pesquisarDadosImoveisArquivoTextoContasCobrancaEmpresa(
			Collection ids, 
			Integer idUnidadeNegocio, 
			Integer numeroPagina, 
			int quantidadeRegistros,
			Integer idProgramaEspecial) throws ErroRepositorioException;
	
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * Pesquisa os dados das contas do imóvel
	 * 
	 * @author Mariana Victor
	 * @date 25/11/2011
	 */
	public Collection<Object[]> pesquisarDadosContasArquivoTextoContasCobrancaEmpresa(
			Integer idImovel, Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 23/11/2011
	 */
	public boolean existeParcelamentoQuantidadePrestacaoSituacaoLigacaoAgua(
		Integer idSituacaoLigacaoAgua,Integer idParcelamentoQuantidadePrestacao) throws ErroRepositorioException;
	
	/**
	 * [UC0214] - Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 28/11/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public  ResolucaoDiretoria  pesquisarResolucaoDiretoriaPortal()
			throws ErroRepositorioException;

	/**
	 * [UC 0870] Gerar Movimento de Contas em Cobrança por Empresa
	 * 
	 * Método que pesquisa se o comando possui setores associados
	 * na tabela cobranca.cmd_empr_cobr_conta_stcm
	 * 
	 * @author Raimundo Martins
	 * @date 02/12/2011
	 * 
	 * */
	public Collection<SetorComercial> pesquisarSetoresComerciaisComandoEmpresaCobrancaContaSetorComercial(Integer idComando)
			throws ErroRepositorioException;
	/**
	 * [UC1250] Solicitar Geração/Emissão Boletim de Medição de Contratos
	 * 
	 * Verifica a quantidade de boletim de contrato para o mês/ano 
	 * 
	 * @author Mariana Victor
	 * @date 15/12/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeBoletimMedicaoContrato(Integer idContrato) 
			throws ErroRepositorioException;

	/**
	 * [UC1250] Solicitar Geração/Emissão Boletim de Medição de Contratos
	 * 
	 * 1.6.2. O usuário informa o filtro e ao clicar no botão "Selecionar" o sistema deverá exibir,
	 *   em uma tabela, todos os boletins de contrato 
	 * 
	 * @author Mariana Victor
	 * @date 16/12/2011
	 * */
	public Collection<Object[]> pesquisarBoletimMedicaoContratoEmitir(Integer idContrato, Integer anoMes)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC 1256] Retirar Imoveis e COntas das Empresas de Cobrança
	 * 
	 * Metodo que pesquisa os comandos que podem ser retirados
	 * imoveis e cobrança
	 * 
	 * @author Raimundo Martins
	 * @date 13/12/2011
	 * */
	public Collection<ComandoEmpresaCobrancaConta> pesquisarDadosRetirarImoveisContasEmpresaCobranca(Integer idEmpresa, 
		Date periodoIni, Date periodoFin, Integer numPaginas, Integer quantidadeRegistros) throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imoveis e COntas das Empresas de Cobrança
	 * 
	 * Metodo que pesquisa a quantidade de contas por comando
	 * cujo a data de retirada seja nula
	 * 
	 * @author Raimundo Martins
	 * @date 13/12/2011
	 * */
	public Integer pesquisarqtdContasPorComandoDtRetiradaComandoNulo(Integer idComando)throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Metodo que pesquisa a quantidade de comandos que podem ser
	 * retirados das empresas de cobrança
	 * 
	 * @author Raimundo Martins
	 * @date 14/12/2011
	 * */
	
	public Integer pesquisarDadosRetirarImoveisContasEmpresaCobrancaCount(Integer idEmpresa, 
			Date periodoIni, Date periodoFin) throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Metodo que pesquisa os imóveis que vão ser retirados
	 * de cobrança por empresa a partir do comando
	 * 
	 * @author Raimundo Martins
	 * @date 15/12/2011
	 * */
	
	public Collection<Imovel> pesquisarImoveisRetirarComandoEmpresaCobrancaConta(Integer idComando)throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * [SB0005 - Verificar vencimento da conta]
	 * 
	 * Metodo que pesquisa as contas do imóveis
	 * que podem ser retirados
	 * 
	 * @author Raimundo Martins
	 * @date 16/12/2011
	 * */
	
	public Collection<Integer> pesquisarContasNaoVencidas(Integer idComando, Integer idImovel)throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * [SB0004 - Retirar Conta de Cobrança]
	 * 
	 * Metodo que retira as contas de cobrança
	 * 
	 * @author Raimundo Martins
	 * @date 16/12/2011
	 * */
	public void retirarContasDeCobranca(Integer idComando, Integer idConta, Integer idMotivoRetirada)throws ErroRepositorioException;
	
		
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 *  
	 * Metodo que verifica a quantidade de contas por 
	 * comando e imovel
	 * 
	 * @author Raimundo Martins
	 * @date 16/12/2011
	 * */	
	public Integer quantidadeContasPorImovelEComando(Integer idComando, Integer idImovel)throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Metodo que verifica se as contas de determinado
	 * imóvel estão pagas ou em parcelamento
	 * 
	 * @author Raimundo Martins
	 * @date 16/12/2011
	 * */	
	public Collection<Integer> pesquisarContasPagasOuParceladas(Integer idComando, Integer idImovel)throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Metodo que verifica se a conta esta paga
	 * 
	 * @author Raimundo Martins
	 * @date 16/12/2011
	 * */	
	public Boolean contaPaga(Integer idConta)throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Metodo que pesquisa a data de envio do imóvel para cobrança
	 * 
	 * @author Raimundo Martins
	 * @date 19/12/2011
	 * */
	
	public Date pesquisarDataEnvioImovelCobranca(Integer idComando, Integer idImovel)throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Metodo que pesquisa a data de envio do imóvel para cobrança
	 * 
	 * @author Raimundo Martins
	 * @date 19/12/2011
	 * */
	public Collection<Integer> pesquisarContasEmpresaCobrancaContaPorImovelComando(Integer idComando, Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Metodo que pesquisa a quantidade de imovéis em cobrança
	 * para aquele comando 
	 * 
	 * @author Raimundo Martins
	 * @date 19/12/2011
	 * */
	public Integer pesquisarqtdImoveisPorComandoDtRetiradaComandoNulo(Integer idComando)throws ErroRepositorioException;
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * [SB0004 - Retirar Conta de Cobrança]
	 * 
	 * Metodo que retira as contas de cobrança através do imovel
	 * 
	 * @author Raimundo Martins
	 * @date 27/12/2011
	 * */
	public void retirarContasDeCobrancaPorImovel(Integer idComando, Integer idImovel, Integer idMotivoRetirada)throws ErroRepositorioException;

	
	/**
	 * [UC1257] - Gerar Relatório dos Imóveis e Contas Retirados das Empresas de Cobrança
	 * 
	 * @author: Hugo Azevedo
	 * @date: 19/12/2011
	 */
	public Collection obterRelatorioImoveisRetiradosEmpresasCobranca(
			Integer idEmpresa, int amReferenciaInicial,
			int amReferenciaFinal) throws ErroRepositorioException;
	
	/**
	 * [UC1257] - Gerar Relatório dos Imóveis e Contas Retirados das Empresas de Cobrança
	 * 
	 * @author: Hugo Azevedo
	 * @date: 19/12/2011
	 */
	public Integer obterQtdRelatorioImoveisRetiradosEmpresasCobranca(
			Integer idEmpresa, int amReferenciaInicial,
			int amReferenciaFinal) throws ErroRepositorioException;
	
	
	/**
	 * [UC1257] - Gerar Relatório dos Imóveis e Contas Retirados das Empresas de Cobrança
	 * 
	 * @author: Hugo Azevedo
	 * @date: 19/12/2011
	 */
	public Collection obterRelatorioContasRetiradasEmpresasCobranca(
			Integer idEmpresa, Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC0473] Consultar Dados Complementares do Imóvell
	 * 
	 * Metodo que pesquisa as contas enviadas para cobrança
	 * por imóvel
	 * 
	 * @author Raimundo Martins
	 * @date 27/12/2011
	 * */
	public Collection<Object[]> pesquisarContasEnviadasCobrancaPorImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0473] Consultar Dados Complementares do Imóvell
	 * 
	 * Metodo que pesquisa as contas pagas em cobrança
	 * por imóvel
	 * 
	 * @author Raimundo Martins
	 * @date 28/12/2011
	 * */
	public Collection<Object[]> pesquisarContasPagasEmCobrancaPorImovel(Integer idImovel) throws ErroRepositorioException;

	/**
	 * [UC1264] Incluir Contas em Cobrança
	 * 
	 * 1.1.	Para cada parcelamento efetuado no mês 
	 * 1.1.1. O sistema deverá verificar se o imóvel do parcelamento está associado a uma cobrança 
	 *   por resultado ou o parcelamento foi efetuado antes da data de retirada da cobrança por resultado
	 * 1.1.1.1.	Para cada conta parcelada, o sistema deverá verificar se a mesma está em cobrança
	 * 
	 * @author Mariana Victor
	 * @date 27/12/2011
	 * 
	 * @param idLocalidade
	 * @param anoMesArrecadacao
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarContasParceladasIncluirCobranca(
		Integer idLocalidade, Integer anoMesArrecadacao) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1264] Incluir Contas em Cobrança
	 * 
	 * @author Mariana Victor
	 * @date 27/12/2011
	 * 
	 * @param idImovel
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosImoveisIncluirCobranca(
		Integer idImovel) throws ErroRepositorioException;

	/**
	 * [UC1264] Incluir Contas em Cobrança
	 * 
	 * 2.1.1. Para cada pagamento de conta efetuado no mês da arrecadação 
	 *   para os imóveis em situação de cobrança durante o mês 
	 * 2.1.1.1.	O sistema deverá verificar se a quantidade de dias do vencimento é diferente de nulo ou zero, caso seja, 
	 *   o sistema deverá verificar se o pagamento foi efetuado após a data de vencimento somando a quantidade de dias informada 
	 * 2.1.1.2.	Para cada conta que não exista na tabela EMPRESA_COBRANCA_CONTA
	 * 
	 * @author Mariana Victor
	 * @date 27/12/2011
	 * 
	 * @param idLocalidade
	 * @param anoMesArrecadacao
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarContasPagasIncluirCobranca(
		Integer idLocalidade, Integer anoMesArrecadacao, Integer numeroDiasVencimentoCobrancaResultado,
		Integer numeroDiasEnvioContaEmpresaCobranca) 
			throws ErroRepositorioException;
	/**	 
	 * [UC 0867] Atualizar Pagamentos Contas em Cobrança
	 * Metodo que verifica o Documento de Cobranca pra	 
	 * uma conta paga	
	 * @author Raimundo Martins	  
	 * @date 28/12/2011	 
	 * */		
	public Collection<Integer> pesquisarDocumentoCobrancaConta(Integer idPagamento) throws ErroRepositorioException;		
	
	/**	 
	 * [UC 0867] Atualizar Pagamentos Contas em Cobrança	
	 * Metodo que pesquisa a data de retirada da	 
	 * conta de EmpresaCobrancaConta	
	 * @author Raimundo Martins	 
	 * @date 28/12/2011	 
	 * */	
	public Date pesquisarDataRetiradaConta(Integer idEmpresaCobrancaConta) throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 *  
	 * Metodo que verifica se o imovel ja foi retirado
	 * para aquele comando
	 * 
	 * @author Raimundo Martins
	 * @date 30/12/2011
	 * */
	public Boolean isImovelRetirado(Integer idImovel, Integer idComando) throws ErroRepositorioException;	
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Método que atualiza a situação especial de cobrança do imovel
	 * 
	 * @author Raimundo Martins
	 * @date 02/02/2012
	 * */
	
	public void atualizarImovelSituacaoEspecialCobranca(Integer idImovel, String motivoRetirada, Integer anoMes)throws ErroRepositorioException;
	
	/**
	 * [UC0896] Gerar Arquivo Texto das Contas em Cobrança por Empresa
	 *  
	 * Metodo que verifica a quantidade de contas para um determinado comando
	 * 
	 * @author Mariana Victor
	 * @date 09/02/2012
	 * */	
	public Integer pesquisarQuantidadeContasComando(Integer idComando) throws ErroRepositorioException;
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobrança por Empresa
	 *  
	 * [FS0006] - Verificar existência de dados para os comandos
	 *  Caso algum dos comandos não tenha sido executado 
	 * 
	 * @author Mariana Victor
	 * @date 09/02/2012
	 * */	
	public boolean verificarComandoExecutado(Integer idComando) throws ErroRepositorioException;
	
	/**
	 * Verifica se existem dados para serem gerados
	 * [UC1350] Gerar Arquivo Texto das Faturas Agrupadas
	 * @author Amelia Pessoa
	 * @date 13/06/2012
	 */
	public Collection<Integer> verificarDadosFaturasAgrupadas(Integer anoMesInicial, Integer anoMesFinal, ArrayList<Integer> arvoreClientes) throws ErroRepositorioException;

	/**
	 * Busca os dados para serem gerados
	 * [UC1350] Gerar Arquivo Texto das Faturas Agrupadas
	 * @author Amelia Pessoa
	 * @date 13/06/2012
	 */
	public Collection<FaturaItem> pesquisarFaturasAgrupadas(Integer anoMesInicial, Integer anoMesFinal, ArrayList<Integer> faturas) throws ErroRepositorioException;

	
	/**
	 * Obter nome do cliente
	 * [UC1350] Gerar Arquivo Texto das Faturas Agrupadas
	 * @author Amelia Pessoa
	 * @date 13/06/2012
	 */
	public String obterNomeCliente(Integer clienteId) throws ErroRepositorioException;

	/**
	 * Obtem os valores e percentuais de desconto por tipo de débito
	 * 
	 * [UC0214] - Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 26/06/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterValorEPercDescontoPorTipoDebitoPagParcelado(
			Integer idPerfilParc, Collection<Integer> colecaoIdContas)throws ErroRepositorioException;
	
	/**
	 * [UC0214] - Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 27/06/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorDebitoCobradoParcelamento(Integer idConta,
			Collection<Integer> idsTipoFinanciamento)throws ErroRepositorioException;
	
	
	/**
	 * [UC0214] - Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 27/06/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorCreditoRealizadoParcelamento(Integer idConta,
			Collection<Integer> idsCreditoTipo)	throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @data 06/07/2012
	 */
	public CobrancaDocumento pesquisarCobrancaDocumentoParaEmitirEntradaParcelamento(
			Integer idParcelamento)throws ErroRepositorioException ;
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @data 06/07/2012
	 */
	public Cliente pesquisarClienteDoParcelamento(
			 Integer idParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @data 06/07/2012
	 */
	public Integer existeCobrancaDocumentoParaEntradaParcelamento(
			Integer idParcelamento)throws ErroRepositorioException;

	/**
	 * [UC 1351] Retirar Contas Revisão Extrato Entrada de Parcelamento
	 * 
	 * @author Davi Menezes
	 * @date  02/07/2012
	 */
	public Collection<Object []> pesquisarContasRevisaoEntradaParcelamentoDataLimite(Date novaData,
			Integer idLocalidade) throws ErroRepositorioException;
	
	public void atualizarSituacaoDebitoDocumentoCobranca (Integer idDocumentoCobranca) throws ErroRepositorioException;
	
	/**
	 * Obtem os valores e percentuais de desconto por tipo de débito
	 * 
	 * [UC0214] - Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 26/06/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterValorEPercDescontoPorTipoDebitoPagAVista(
			Integer idPerfilParc, Collection<Integer> colecaoIdContas)throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 14/07/2012
	 */
	public BigDecimal pesquisarDescontoParaEntradaParcelamentoCobrancaDocumento(
			Integer idParcelamento)throws ErroRepositorioException;
	
	/**
	 * [UC0242] - Registrar Movimento Arrecadador
	 * 
	 * [SB0020] - Processar Registro código F do Cliente Responsável
	 * 
	 * @author Ana Maria
	 * @data 20/07/2012
	 */
	public Object[] obterDadosPrestacaoContratoParcelamento(Integer idPrestacao) 
		throws ErroRepositorioException;
	
 

	/**
	 * Pesquisa quais os clientes existentes dado um intervalo de codigos
	 * [UC1350] Gerar Arquivo Texto das Faturas Agrupadas
	 * @author Amelia Pessoa
	 * @date 13/06/2012
	 * @return Collection<Integer> com os ids dos clientes existentes
	 */
	public Collection<Integer> pesquisarClientes(Integer idClienteInicial, Integer idClienteFinal) throws ErroRepositorioException;

	
	/**
	 * 
	 * [UC1367] Registrar Movimento do Programa Especial
	 * [SB0003] Suspender Imóveis em Programa Especial
	 * 
	 * @author Hugo Azevedo
	 * @date 21/08/2012
	 * 
	 */
	public CobrancaSituacaoHistorico obterSituacaoEspecialCobrancaHistorico(Integer idImovel) throws ErroRepositorioException;

	
	/**
	 * Pesquisar as Ações de Cobrança 
	 * 
	 * [UC 1370] Consultar Ações de Cobrança por Imóvel
	 * 
	 * @author Davi Menezes
	 * @date 15/08/2012
	 *
	 */
	public Collection pesquisarAcoesCobrancaImovel(
			Integer idImovel, String periodoInicialAcao, String periodoFinalAcao) throws ErroRepositorioException;
	
	/**
	 * Pesquisar Valor Taxa Serviço 
	 * 
	 * [UC 1370] Consultar Ações de Cobrança por Imóvel
	 * 
	 * @author Davi Menezes
	 * @date 16/08/2012
	 *
	 */
	public Object[] pesquisarValorTaxaServico(Integer idImovel, Integer idCobrancaDocumento) throws ErroRepositorioException;
	
	/**
	 * Pesquisar Ordem Serviço da Ação de Cobrança
	 * 
	 * [UC 1370] Consultar Ações de Cobrança por Imóvel
	 * 
	 * @author Davi Menezes
	 * @date 20/08/2012
	 */
	public Object[] pesquisarOrdemServicoAcaoCobranca(Integer idCobrancaDocumento, Integer idAcaoCobranca) throws ErroRepositorioException;
	
	/**
	 * Pesquisar Ação de Cobrança Cronograma
	 * 
	 * [UC 1370] Consultar Ações de Cobrança por Imóvel
	 * 
	 * @author Davi Menezes
	 * @date 20/08/2012
	 */
	public Object[] pesquisarAcoesCobrancaCronograma(Integer idCobrancaDocumento) throws ErroRepositorioException;
	
	/**
	 * Pesquisar Usuario e a unidade de encerramento da Ordem de Servico
	 * 
	 * [UC 1370] Consultar Ações de Cobrança por Imóvel
	 * 
	 * @author Arthur Carvalho
	 * @date 06/09/2012
	 * 
	 * @param idCobrancaDocumento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarUsuarioEmpresaDaOrdemServicoEncerrada(Integer idOrdemServico) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * [SF0003] - Processar Pagamento de Documento de Cobrança
	 * 
	 * @author Arthur Carvalho
	 * @created 12/12/2012
	 * 
	 * @param matriculaImovel
	 *            Matrícula do Imovel
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Object[] pesquisarParmsCobrancaDocumentoCliente(Integer idCliente, int numeroSequencialDocumento) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * [SF0003] - Processar Pagamento de Documento de Cobrança
	 * 
	 * @author Arthur Carvalho
	 * @created 12/12/2012
	 * 
	 * @param idCliente
	 * @param numeroSequencialDocumento
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Object[] pesquisarLocalidadeDaGuiaPagamentoCliente(Integer idCliente,int numeroSequencialDocumento) throws ErroRepositorioException;

	/**
	 * 
	 * [UC0243] - Inserir Comando Ação de Cobrança
	 * [SB0007] - Inserir cobranca acao atividade comando
	 * 
	 * @author Hugo Azevedo
	 * @date 25/09/2012
	 * 
	 */
	public Collection obterImoveisComandoAtividadeImovel(Integer idImovel, Integer idComando)throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0244] - Manter Comando Ação de Cobrança
	 * [SB0003] - Excluir Comando de Atividade Eventual de Ação de Cobrança
	 * 
	 * @author Hugo Azevedo
	 * @date 04/10/2012
	 * 
	 */
	public void removerImoveisComandoAtividadeImovel(Integer idComando)throws ErroRepositorioException;
	
	/**
	 * Gerar Relatório de Análise de Perdas com Crédito
	 * 
	 * [UC1155] Gerar Relatório de Análise de Perdas com Crédito
	 * 
	 * 
	 * @author Paulo Diniz,Vivianne Sousa
	 * @date 16/03/2011, 14/01/2012
	 * 
	 * @param mesAno para análise
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> gerarRelatorioAnalisePerdasCreditosTotal(String anoMesReferencia)
		throws ErroRepositorioException;
	
	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança 
	 * [SB0004] Verificar Critério de Cobrança para Imóvel
	 * 
	 * @author Hugo Azevedo
	 * @created 17/10/12
	 * 
	 */
	public Collection obterImoveisSituacaoLigacaoNaoAlteradaDebito(Integer idImovel,Date dataInicialFisc, Date dataFinalFisc)throws ErroRepositorioException;
	
	/**
	 * RN2013066779
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * 
	 * @author Sávio Luiz
	 * @created 11/09/2013
	 * 
	 */
	public Integer obterContasEmRevisaoComIndicadorBloqueioConta(Integer idImovel)throws ErroRepositorioException;
	
	/**
	 * [UC XXXX] - Processar amortização de dívida ativa
	 * 
	 * Pesquisa o id do parcelamento associado ao débito cobrado
	 * 
	 * @author Rafael Corrêa
	 * @date 16/02/2014
	 */
	public Integer obterIdParcelamentoAssociadoDebitoCobrado(Integer idDebitoCobrado) throws ErroRepositorioException;
	
	/**
	 * [UC XXXX] - Processar amortização de dívida ativa
	 * 
	 * Pesquisa o parcelamento associado a conta
	 * 
	 * @author Rafael Corrêa
	 * @date 17/02/2014
	 */
	public Parcelamento pesquisarParcelamentoConta(Integer idConta) throws ErroRepositorioException;
	
	/**
	 * [UC XXXX] - Processar amortização de dívida ativa
	 * 
	 * Pesquisa o parcelamento associado ao débito a cobrar
	 * 
	 * @author Rafael Corrêa
	 * @date 18/02/2014
	 */
	public Parcelamento pesquisarParcelamentoDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException;
	
	/**
	 * [UC XXXX] - Processar amortização de dívida ativa
	 * 
	 * Pesquisa o parcelamento associado a guia de pagamento
	 * 
	 * @author Rafael Corrêa
	 * @date 18/02/2014
	 */
	public Parcelamento pesquisarParcelamentoGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;
	
	/**
	 * [UC XXXX] - Processar amortização de dívida ativa
	 * 
	 * Pesquisa as contas que tiveram vencimento alterado para o próximo ano
	 * 
	 * @author Rafael Corrêa
	 * @date 09/04/2014
	 */
	public Collection<Conta> pesquisarContasVencimentoAlterado(
			Date dataVencimento, Integer idLocalidade, 
			Integer numeroIndice, Integer quantidadeRegistros)
			throws ErroRepositorioException;
	
	/**
	 * [UC1585] - Emitir Relatório Dívida Ativa Amortizada.
	 * 
	 * @author Anderson Cabral
	 * @created 17/02/2014
	 * 
	 */
	public Collection<Object[]> obterDadosAmortizacoesDividaAtiva(Date dataInscricaoInicial, Date dataInscricaoFinal, Date dataAmortizacaoInicial, Date dataAmortizacaoFinal, 
																	Integer idImovel, Short indicadorIntra)throws ErroRepositorioException;
	
	/**
	 * [UC1586] - Emitir Relatório Dívida Ativa Parcelada.
	 * 
	 * @author Anderson Cabral
	 * @created 19/02/2014
	 * 
	 */
	public Collection<Object[]> obterDadosParcelamentosDividaAtiva(Date dataInscricaoInicial, Date dataInscricaoFinal, Date dataAmortizacaoInicial, Date dataAmortizacaoFinal, 
																	Integer idImovel, Short indicadorIntra)throws ErroRepositorioException;
	
	/**
	 * [UC1588] Gerar Divida Ativa dos Imóveis
	 * 
	 * @author Ana Maria
	 * @throws ErroRepositorioException
	 * @date 14/02/2014
	 */
	public Collection<DividaAtivaCriterio> pesquisarComandosDividaAtiva() 
			throws ErroRepositorioException;
	
	/**
	 * [UC1588] Gerar Divida Ativa dos Imóveis
	 * 
	 * @author Ana Maria
	 * @throws ErroRepositorioException
	 * @date 14/02/2014
	 */
	public Collection<DividaAtivaCriterioEsferaPoder> pesquisarDividaAtivaEsferaPoderComando(Integer idDividaAtivaCriterio) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1588] Gerar Divida Ativa dos Imóveis
	 * 
	 * @author Ana Maria
	 * @throws ErroRepositorioException
	 * @date 14/02/2014
	 */
	public Collection<DividaAtivaCriterioClienteTipo> pesquisarDividaAtivaClienteTipoComando(Integer idDividaAtivaCriterio) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1588] Gerar Divida Ativa dos Imóveis
	 * 
	 * @author Ana Maria
	 * @throws ErroRepositorioException
	 * @date 14/02/2014
	 */
	public Collection pesquisarContasImoveisDividaAtiva(DividaAtivaCriterio dividaAtivaCriterio, 
			Collection <DividaAtivaCriterioEsferaPoder> dividaAtivaCriterioEsferaPoder,
			Collection<DividaAtivaCriterioClienteTipo> dividaAtivaCriterioClienteTipo, int numeroIndice, int quantidadeRegistros) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0204] Consultar Conta
	 * [SB0002] Pesquisar Conta na Divida Ativa
	 * 
	 * @author Anderson Cabral
	 * @throws ErroRepositorioException
	 * @date 16/02/2014
	 */
	public Date pesquisarDataInscricaoContaDividaAtiva(Integer idConta) throws ErroRepositorioException;
		
	/**
	 * [UC1590] - Gerar Relatório Demonstrativo Dívida Ativa 
	 * 
	 * @author Anderson Cabral
	 * @throws ErroRepositorioException
	 * @date 11/03/2014
	 */
	public Collection<Object[]> obterDadosDemonstrativoDividaAtiva(short indicadorIntra, String anoMesDemonstrativo) throws ErroRepositorioException;

	/**
	 * [UC1590] - Gerar Relatório Demonstrativo Dívida Ativa 
	 * [IT0002] Obter Município da Empresa
	 * 
	 * @author Anderson Cabral
	 * @throws ErroRepositorioException
	 * @date 11/03/2014
	 */
	public String obterMunicipioDaEmpresa() throws ErroRepositorioException;
	
	/**
	 * [UC1591] Gerar Resumo Divida Atida Anual
	 * 
	 * [FE0003] Verificar Registros na Tabela para Resumo Anual
	 * 
	 * @author Ana Maria
	 * @date 13/03/2014
	 * */	
	public void removerDadosResumoDividaAtivaAnual(Integer anoMesDividaAtiva, Integer idSetorComercial)
			throws ErroRepositorioException;
	
	/**
	 * [UC1591] Gerar Resumo Divida Atida Anual
	 * 
	 * [IT0001] Obter Comandos Dívida Ativa 
	 * 
	 * @author Ana Maria
	 * @date 13/03/2014
	 * */	
	public Collection<DividaAtivaCriterio> pesquisarComandosDividaAtivaProcessadosDataReferencia(String anoMesDataReferencia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC XXXX] - Processar amortização de dívida ativa
	 * 
	 * Atualiza os débitos de dívida ativa para voltar a situação antes da execução do batch
	 * 
	 * @author Rafael Corrêa
	 * @date 04/04/2014
	 */
	public void atualizarAmortizacoesDebitosDividaAtivaArrecadacao(Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;
	
	/**
	 * [UC XXXX] - Processar amortização de dívida ativa
	 * 
	 * Deleta as amortizações dos débitos de dívida ativa para voltar a situação antes da execução do batch
	 * 
	 * @author Rafael Corrêa
	 * @date 04/04/2014
	 */
	public void deletarAmortizacoesDebitosDividaAtivaArrecadacao(Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;
	
	/**
	 * [UC XXXX] - Processar amortização de dívida ativa
	 * 
	 * Atualiza os débitos de dívida ativa para voltar a situação antes da execução do batch
	 * 
	 * @author Rafael Corrêa
	 * @date 04/04/2014
	 */
	public void atualizarAmortizacoesDebitosDividaAtivaFaturamento(Integer anoMesReferenciaFaturamento)
			throws ErroRepositorioException;
	
	/**
	 * [UC XXXX] - Processar amortização de dívida ativa
	 * 
	 * Deleta as amortizações dos débitos de dívida ativa para voltar a situação antes da execução do batch
	 * 
	 * @author Rafael Corrêa
	 * @date 04/04/2014
	 */
	public void deletarAmortizacoesDebitosDividaAtivaFaturamento(Integer anoMesReferenciaFaturamento)
			throws ErroRepositorioException;
	
	/**
	 * [UC1591] Gerar Resumo Divida Atida Anual
	 * 
	 * [IT0002] Pesquisar Debitos em Divida Ativa
	 * 
	 * @author Ana Maria
	 * @throws ErroRepositorioException
	 * @date 13/03/2014
	 */
	public Collection pesquisarDebitosDividaAtiva(DividaAtivaCriterio dividaAtivaCriterio, Date dataReferencia, Integer idSetorComercial) 
			throws ErroRepositorioException;
	
	public Collection<Empresa> obterEmpresasCobranca(Short indicadorUso,String dataCorrente)
		throws ErroRepositorioException;
	
	public Collection<CobrancaAcaoGrupoContrato> obterCobrancaAcaoGrupoContrato(Integer idGrupo,Integer idEmpresa,Integer idContrato,Integer idAcaoCobranca)
			throws ErroRepositorioException;

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * [SB0005] Gerar Documento de Cobrança
	 * [SB0010] Obter Empresa do Contrato
	 * 
	 * @author Vivianne Sousa
	 * @date 13/06/2014
	 */
	public Empresa obterEmpresaContrato(Integer idCobrancaAcaoAtividadeCronograma)throws ErroRepositorioException;
	
	/**
	 * [UC0312] Inserir Cronograma de Cobrança
	 * [SB0001] Obter Contrato da Ação
	 * 
	 * @author Ana Maria
	 * @date 13/06/2014
	 */
	public ContratoEmpresaServico obterContratoAcaoCobranca(Integer idGrupoCobranca, Integer idAcaoCobranca)
			throws ErroRepositorioException; 
	
	/**
	 * [UC0312] Inserir Cronograma de Cobrança
	 * [SB0001] Obter Contrato ddo Grupo
	 * 
	 * @author Ana Maria
	 * @date 19/06/2014
	 */
	public ContratoEmpresaServico obterContratoGrupo(Integer idGrupoCobranca)
			throws ErroRepositorioException;
	
	/**
	 * MA20140610677 - Alterar vencimentos para contas negativadas
	 * @author Diogo Luiz
	 * @date 23/06/2014
	 * RM11231 - [UC0146] - Manter Conta		
	 */
	public Short pesquisarIndicadorAlterarVencimentoConta(Conta conta) throws ErroRepositorioException;
	
	
	/**
	 * [UC1615] Prepara dados SMS/EMAIL cobrança de conta
	 * 
	 * @author Hugo Azevedo
	 * @date 08/08/2014
	 */
	public Collection<Object[]> selecionarDocumentosCobrancaSMSEmail(Integer idAcaoCobranca, Integer idCobrancaAcaoAtividadeComando) throws ErroRepositorioException; 
	
	
	/**
	 * [UC1615] Prepara dados SMS/EMAIL cobrança de conta
	 * 
	 * @author Hugo Azevedo
	 * @date 08/08/2014
	 */
	public void excluirMensagensReferencia(Integer idComando) throws ErroRepositorioException;
	
	public Collection pesquisarDataPrevista(CobrancaGrupo cobrancaGrupo) throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno
	 * @throws ControladorException 
	 * @data 01/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorSemPavimentoSemCalcada(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento,Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno	
	 * @throws ControladorException 
	 * @data 01/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoAsfaltico(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento,Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno	
	 * @throws ControladorException 
	 * @data 01/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoParalelo(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento,Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno
	 * @throws ControladorException 
	 * @data 01/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorSemPavimentoSemCalcadaRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento, Integer referencia,
			Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno
	 * @throws ControladorException 
	 * @data 01/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoAsfalticoRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento, Integer referencia,
			Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno
	 * @throws ControladorException 
	 * @data 01/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoParaleloRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento, Integer referencia,
			Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno	
	 * @throws ControladorException 
	 * @data 01/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoParaleloSemCalcada(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento,Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno	
	 * @throws ControladorException 
	 * @data 01/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoParaleloComCalcada(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento,Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno	
	 * @throws ControladorException 
	 * @data 01/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoParaleloSemCalcadaRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento, Integer referencia,
			Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno
	 * @throws ControladorException 
	 * @data 01/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoParaleloComCalcadaRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento, Integer referencia,
			Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno	
	 * @throws ControladorException 
	 * @data 01/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoAsfalticoSemCalcada(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento,Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno
	 * @throws ControladorException 
	 * @data 01/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoAsfalticoSemCalcadaRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento, Integer referencia,
			Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno	
	 * @throws ControladorException 
	 * @data 01/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoAsfalticoComCalcada(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento,Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno
	 * @throws ControladorException 
	 * @data 01/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoAsfalticoComCalcadaRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento, Integer referencia,
			Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz, Mariana Victor
	 * @throws ControladorException 
	 * @data 23/03/2011, 31/10/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasNaoExecutadasPorBoletim(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz, Mariana Victor
	 * @throws ControladorException 
	 * @data 23/03/2011, 31/10/2011
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasNaoExecutadasPorBoletimRotaAlternativa(
			Integer idGrupoCobranca,Integer idItemServicoContrato, Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno	
	 * @throws ControladorException 
	 * @data 28/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimento2(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento,Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno	
	 * @throws ControladorException 
	 * @data 28/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoRotaAlternativa2(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento, Integer referencia,
			Integer idContrato) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Tiago Moreno	
	 * @throws ControladorException 
	 * @data 28/10/2014
	 * 
	 * @throws ControladorException 
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimento3(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento,Integer referencia, Integer idContrato) 
			throws ErroRepositorioException;
	
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoRotaAlternativa3(
			Integer idGrupoCobranca,Integer idItemServicoContrato,Short indicadorPavimento, Integer referencia,
			Integer idContrato) 
			throws ErroRepositorioException;
	
	
	/**
	 * [UC1668] Atualizar Dados nas Tabelas Resumos Gerenciais Arrecadação
	 * @author Fábio Aguiar
	 * @throws ControladorException 
	 * @data 29/01/2015
	 * 
	 * @throws ControladorException 
	 * */
	public void gerarResumoCobrancaAtualizaDados()  throws ErroRepositorioException, SQLException;
	
	/**
	 * [UC1669] Atualizar Dados nas Tabelas Resumos Gerenciais Faturamento
	 * @author Fábio Aguiar
	 * @throws ControladorException 
	 * @data 04/02/2015
	 * 
	 * @throws ControladorException 
	 * */
	public void gerarResumoParcelamentoAtualizaDados()  throws ErroRepositorioException, SQLException;
	
	
	/**
	 * [UC0214] [SB0021] Exibir de Tela de Sucesso
	 * 
	 * 
	 * @author Fábio Aguiar	
	 * @throws ControladorException 
	 * @data 31/03/2015
	 * 
	 * @throws ControladorException 
	 * */
	public Integer pesquisaUsuarioLogadoPossuiAcessoFuncionalidade(Integer idUsuario) throws ErroRepositorioException;
	
	/**
	 * [UC1675] - Gerar relatório consultar Débitos
	 * 
	 * 
	 * @author Fábio Aguiar	
	 * @throws ControladorException 
	 * @data 14/04/2015
	 * 
	 * @throws ControladorException 
	 * */
	public Object[] pesquisarClienteResponsavel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * Obtém os parcelamentos ativos de um imóvel
	 * 
	 * [UC0214] - Efetuar Parcelamento de Débitos
	 * 
	 * @author Rafael Corrêa
	 * @date 01/06/2015
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterParcelamentosAtivosImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0927] - Confirmar Cartão de Crédito/Débito
	 * [SB0003] - Inserir Guia de Pagamento Cliente 
	 *
	 * @author Vivianne Sousa
	 * @date 04/05/2015
	 */
	public Collection<DebitoACobrar> pesquisarDebitoACobrarParcelamento(Integer idParcelamento,Integer idDebitoCreditoSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0927] - Confirmar Cartão de Crédito/Débito
	 * [SB0003] - Inserir Guia de Pagamento Cliente 
	 *
	 * @author Vivianne Sousa
	 * @date 04/05/2015
	 */
	public Collection<CreditoARealizar> pesquisarCreditoARealizarParcelamento(Integer idParcelamento,Integer idDebitoCreditoSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0927] - Confirmar Cartão de Crédito/Débito
	 * [SB0001] - Incluir Dados da Confirmação do Parcelamento
	 *
	 * @author Vivianne Sousa
	 * @date 07/05/2015
	 */
	public Parcelamento pesquisarParcelamento(Integer idParcelamento) throws ErroRepositorioException;
	
	/**
	 * [UC0213] Desfazer Parcelamentos Débito
	 * 
	 * @author Vivianne Sousa
	 * @date 11/05/2015
	 */
	public void deletarGuiaPagamentoParcelamentoCartao(Integer idParcelamento)throws ErroRepositorioException;
	
	/**
	 * [UC0213] Desfazer Parcelamentos Débito
	 * 
	 * @author Vivianne Sousa
	 * @date 11/05/2015
	 */
	public void deletarParcelamentoPagamentoCartaoCredito(Integer idParcelamento)throws ErroRepositorioException;
	
	/**
	 * [UC0213] Desfazer Parcelamentos Débito
	 * 
	 * @author Vivianne Sousa
	 * @date 11/05/2015
	 */
	public Collection pesquisarIdGuiaPagamentoParcelamentoCartao(Integer idParcelamento)throws ErroRepositorioException;
	
	/**
	 * [UC0213] Desfazer Parcelamentos Débito
	 * 
	 * @author Vivianne Sousa
	 * @date 11/05/2015
	 */
	public void removerGuiaPagamento(Integer idGuiaPagamento)throws ErroRepositorioException;
	
	/**
	 * [UC0927] - Confirmar Cartão de Crédito/Débito
	 *
	 * @author Vivianne Sousa
	 * @date 16/09/2015
	 */
	public ParcelamentoPagamentoCartaoCredito pesquisarParcelamentoPagamentoCartaoCredito(Integer idParcelamento)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0213] Desfazer Parcelamentos Débito
	 * 
	 * @author Vivianne Sousa
	 * @date 06/10/2015
	 */
	public void deletarPagamentoCartaoCreditoItem(Integer idParcPagCartaoCredito)
			throws ErroRepositorioException;
	
	/**
	 * [UC1691] Confirmar Pagamento Cartão de Crédito
	 * 
	 * @author Jean Varela
	 * @date 07/10/2015
	 */
	public Date pesquisarDataParcelamentoGuiaPagamento(int idGuiaPagamento) throws ErroRepositorioException;
	
	/**
	 * [UC1691] Confirmar Pagamento Cartão de Crédito
	 * 
	 * @author Jean Varela
	 * @date 07/10/2015
	 */
	public String pesquisarIdentificadorTransacaoParaPagamentoGuiaPagamento(int idGuiaPagamento) throws ErroRepositorioException;


	/**
	 * [UC1700] Relatório Consultar Arquivo Retorno Cobranca
	 *
	 * @author João Pedro Medeiros
	 * @date 24/11/2015
	 */
	public Collection<Object[]> obterRelatorioConsultarArquivoRetornoCobranca(Date dataVencimentoInicial, Date dataVencimentoFinal)
		throws ErroRepositorioException;
	
	/**
	 *[UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
	 *[IT0018] Exibir Lista de Grupos de Cobrança
	 *
	 * @author Jean Varela
	 * @date 08/12/2015
	 */
	public Collection<Object[]> pesquisaGrupoCobrancaPorEmpresa(Integer idEmpresa) throws ErroRepositorioException;

	
	/**
	 * [UC1585] - Emitir Relatório Sintetico Dívida Ativa Amortizada.
	 * 
	 * @author Joao Pedro Medeiros
	 * @created 04/01/2016
	 * 
	 */
	public Collection<Object[]> obterDadosAmortizacoesDividaAtivaSintetico(Date dataInscricaoInicial,
			Date dataInscricaoFinal, Date dataAmortizacaoInicial, Date dataAmortizacaoFinal, Integer idImovel,
			Short indicadorIntra) throws ErroRepositorioException;
}