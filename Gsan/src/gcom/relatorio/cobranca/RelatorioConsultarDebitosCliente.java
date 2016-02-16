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
package gcom.relatorio.cobranca;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.CompareToBuilder;


/**
 * classe responsável por criar o Relatório de Débitos
 * 
 * @author Rafael Corrêa
 * @date 14/02/2007
 * 
 */
public class RelatorioConsultarDebitosCliente extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	private Map<Integer, Object[]> responsavelImovel = new HashMap<Integer, Object[]>();

	public RelatorioConsultarDebitosCliente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS_CLIENTE);
	}

	@Deprecated
	public RelatorioConsultarDebitosCliente() {
		super(null, "");
	}

	private Collection<RelatorioConsultarDebitosClienteBean> inicializarBeanRelatorio(
			Collection<ContaValoresHelper> colecaoContas,
			Collection<DebitoACobrar> colecaoDebitosACobrar,
			Collection<CreditoARealizar> colecaoCreditosARealizar,
			Collection<GuiaPagamento> colecaoGuiasPagamento,
			Boolean clienteSuperior) {

		Collection<RelatorioConsultarDebitosClienteBean> retorno = new ArrayList<RelatorioConsultarDebitosClienteBean>();

		String pesquisaCliente = (String) getParametro("pesquisaCliente");
		String relatorioEndereco = (String) getParametro("relatorioEndereco");
		String codigoCliente = (String) getParametro("codigoCliente");
		String nomeCliente = (String) getParametro("nomeCliente");

		String codigoClienteInformado= codigoCliente;
		String nomeClienteInformado = nomeCliente;

		Fachada fachada = Fachada.getInstancia();
		
		// Conta
		if (colecaoContas != null && !colecaoContas.isEmpty()) {

			Integer idImovelAnterior = null;
			
			//Informações do Cliente Responsavel
			Integer idResponsavel = clienteSuperior ? 99999999 : 0;
			String cpfCnpjResponsavel = "";
			String nomeResponsavel = clienteSuperior ? "SEM RESPONSÁVEL" : null;
			String enderecoResponsavel = "";
			
			Iterator<ContaValoresHelper> colecaoContasIterator = colecaoContas.iterator();
			while (colecaoContasIterator.hasNext()) {

				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) colecaoContasIterator.next();
				
				String imprimirEndereco = "";
				
				// Seta o imóvel anterior para verificar, posteriormente, se vai criar um subtotal para o imóvel.
				if (idImovelAnterior == null) {
					idImovelAnterior = contaValoresHelper.getConta().getImovel().getId();
					imprimirEndereco = "SIM";
					
					if(clienteSuperior){
						//Informação do Cliente Responsável 
						Object[] object = recuperarResponsavelImovel(contaValoresHelper.getConta().getImovel().getId(), clienteSuperior);
						idResponsavel = (Integer) object[0];
						cpfCnpjResponsavel = (String) object[1];
						nomeResponsavel = (String) object[2];
						enderecoResponsavel = (String) object[3];
						cpfCnpjResponsavel = cpfCnpjResponsavel == null ?  "": cpfCnpjResponsavel;
					}
				}

				// Mês/Ano
				String mesAnoConta = "" + contaValoresHelper.getFormatarAnoMesParaMesAno();
				
				// Vencimento
				String vencimento = "";
				if (contaValoresHelper.getConta().getDataVencimentoConta() != null) {
					vencimento = Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta());
				}

				// Valor Água
				BigDecimal valorAgua = BigDecimal.ZERO;
				if (contaValoresHelper.getConta().getValorAgua() != null) {
					valorAgua = contaValoresHelper.getConta().getValorAgua();
				}

				// Consumo Água
				String consumoAgua = "";
				if (contaValoresHelper.getConta().getConsumoAgua() != null) {
					consumoAgua = contaValoresHelper.getConta().getConsumoAgua().toString();
				}

				// Valor Esgoto
				BigDecimal valorEsgoto = BigDecimal.ZERO;
				if (contaValoresHelper.getConta().getValorEsgoto() != null) {
					valorEsgoto = contaValoresHelper.getConta().getValorEsgoto();
				}
				
				// Valor dos Impostos
				BigDecimal valorImpostos = BigDecimal.ZERO;
				if (contaValoresHelper.getConta().getValorImposto() != null) {
					valorImpostos = contaValoresHelper.getConta().getValorImposto();
				}
				
				// Valor dos Acréscimos por Impontualidade
				BigDecimal valorMulta = BigDecimal.ZERO;
				if (contaValoresHelper.getValorMulta() != null) {
					valorMulta = contaValoresHelper.getValorMulta();
				}
				
				// Valor dos Acréscimos por Impontualidade
				BigDecimal valorJurosMora = BigDecimal.ZERO;
				if (contaValoresHelper.getValorJurosMora() != null) {
					valorJurosMora = contaValoresHelper.getValorJurosMora();
				}
				
				// Valor dos Acréscimos por Impontualidade
				BigDecimal valorAtualizacaoMonetaria = BigDecimal.ZERO;
				if (contaValoresHelper.getValorAtualizacaoMonetaria() != null) {
					valorAtualizacaoMonetaria = contaValoresHelper.getValorAtualizacaoMonetaria();
				}

				// Consumo Esgoto
				String consumoEsgoto = "";
				if (contaValoresHelper.getConta().getConsumoEsgoto() != null) {
					consumoEsgoto = contaValoresHelper.getConta().getConsumoEsgoto().toString();
				}

				// Valor dos Débitos
				BigDecimal valorDebitos = BigDecimal.ZERO;
				if (contaValoresHelper.getConta().getDebitos() != null) {
					valorDebitos = contaValoresHelper.getConta().getDebitos();
				}

				// Valor dos Créditos
				BigDecimal valorCreditos = BigDecimal.ZERO;
				if (contaValoresHelper.getConta().getValorCreditos() != null) {
					valorCreditos = contaValoresHelper.getConta().getValorCreditos();
				}

				// Valor da Conta
				BigDecimal valorConta = BigDecimal.ZERO;
				if (contaValoresHelper.getConta().getValorTotal() != null) {
					valorConta = contaValoresHelper.getConta().getValorTotal();
				}

				// Valor dos Acréscimos por Impontualidade
				BigDecimal acrescImpont = BigDecimal.ZERO;
				if (contaValoresHelper.getValorTotalContaValores() != null) {
					acrescImpont = contaValoresHelper.getValorTotalContaValores();
				}

				// Situação
				String situacao = "";
				if (contaValoresHelper.getConta().getDebitoCreditoSituacaoAtual() != null) {
					situacao = "" + contaValoresHelper.getConta().getDebitoCreditoSituacaoAtual().getDescricaoDebitoCreditoSituacao();
				}
				
				if (clienteSuperior && !idImovelAnterior.equals(contaValoresHelper.getConta().getImovel().getId())) {
					
					Object[] object = recuperarResponsavelImovel(contaValoresHelper.getConta().getImovel().getId(), clienteSuperior);
					
					idResponsavel = (Integer) object[0];
					cpfCnpjResponsavel = (String) object[1];
					nomeResponsavel = (String) object[2];
					enderecoResponsavel = (String) object[3];
					
					cpfCnpjResponsavel = cpfCnpjResponsavel == null ?  "": cpfCnpjResponsavel;
				}
				
				RelatorioConsultarDebitosClienteBean bean = null;

				if (pesquisaCliente != null && !pesquisaCliente.trim().equals("")) {

					// Caso tenha mudado o imóvel, verifica se teve mais de uma conta para o ele. Em caso afirmativo muda o valor da
					// variável para criar um subtotal, e em caso negativo reinicializa as variáveis de totalização.
					if (!idImovelAnterior.equals(contaValoresHelper.getConta().getImovel().getId())) {
						imprimirEndereco = "SIM";
					}
					
					if (relatorioEndereco != null && !relatorioEndereco.trim().equals("")) {

						String endereco = fachada.pesquisarEndereco(contaValoresHelper.getConta().getImovel().getId());
						Integer idUsuario = null;	
						String nomeUsuario = "";
						String cpfCnpjUsuario = "";
						Object[] dadosCliente = null;
						dadosCliente = fachada.consultarDadosClienteUsuarioImovel(contaValoresHelper.getConta().getImovel().getId() +"");

						if ( dadosCliente[0] != null ) {
							nomeUsuario = (String) dadosCliente[0];
						}
						
						if ( dadosCliente[1] != null ) {
							cpfCnpjUsuario = Util.formatarCpf((String)dadosCliente[1]);
						}else if ( dadosCliente[2] != null ) {
							cpfCnpjUsuario = Util.formatarCnpj((String) dadosCliente[2]);
						}
						
						if ( dadosCliente[3] != null ) {
							idUsuario = ((Integer) dadosCliente[3]);
						}
						
						bean = new RelatorioConsultarDebitosClienteBean(
								contaValoresHelper.getConta().getId(), // Id Conta
								mesAnoConta, // Mês/Ano
								vencimento, // Data de Vencimento
								valorAgua, // Valor Água
								consumoAgua, // Consumo Água
								valorEsgoto, // Valor Esgoto
								consumoEsgoto, // Consumo Esgoto
								valorDebitos, // Valor Débitos
								valorCreditos, // Valor Créditos
								valorConta, // Valor
								acrescImpont, // Acréscimo Impontualidade
								situacao, // Situação
								null, // Id Débito a Cobrar
								null, // Tipo Débito
								null, // Mês/Ano Referência
								null, // Mês/Ano Cobrança
								null, // Parcelas a Cobrar
								null, // Valor a Cobrar
								null, // Id Crédito a Realizar
								null, // Tipo Crédito
								null, // Mês/Ano Referência
								null, // Mês/Ano Cobrança
								null, // Parcelas a Creditar
								null, // Valor
								null, // Id Guia Pagamento
								null, // Tipo Débito
								null, // Data Emissão
								null, // Data Vencimento
								null, // Valor
								null, // Valor Total Débitos
								null, // Valor Atualizado
								contaValoresHelper.getConta().getImovel().getId(), // Matrícula do Imóvel
								endereco, // Endereco
								nomeUsuario, // Nome Cliente Usuário
								imprimirEndereco, // Verificação de Impressão Endereço
								cpfCnpjUsuario, idUsuario.toString(),
								contaValoresHelper.getConta().getReferencia(),
								codigoClienteInformado,
								nomeClienteInformado
						);
						
						bean.setIdResponsavel(idResponsavel.toString());
						bean.setNomeResponsavel(nomeResponsavel);
						bean.setCpfCnpjResponsavel(cpfCnpjResponsavel);
						bean.setEnderecoResponsavel(enderecoResponsavel);
						
						bean.setValorImpostos(valorImpostos);
						bean.setValorMulta(valorMulta);
						bean.setValorJurosMora(valorJurosMora);
						bean.setValorAtualizacaoMonetaria(valorAtualizacaoMonetaria);						
						
						imprimirEndereco = "";

					} else {

						bean = new RelatorioConsultarDebitosClienteBean(
								contaValoresHelper.getConta().getId(), // Id Conta
								mesAnoConta, // Mês/Ano
								vencimento, // Data de Vencimento
								valorAgua, // Valor Água
								consumoAgua, // Consumo Água
								valorEsgoto, // Valor Esgoto
								consumoEsgoto, // Consumo Esgoto
								valorDebitos, // Valor Débitos
								valorCreditos, // Valor Créditos
								valorConta, // Valor
								acrescImpont, // Acréscimo Impontualidade
								situacao, // Situação
								null, // Id Débito a Cobrar
								null, // Tipo Débito
								null, // Mês/Ano Referência
								null, // Mês/Ano Cobrança
								null, // Parcelas a Cobrar
								null, // Valor a Cobrar
								null, // Id Crédito a Realizar
								null, // Tipo Crédito
								null, // Mês/Ano Referência
								null, // Mês/Ano Cobrança
								null, // Parcelas a Creditar
								null, // Valor
								null, // Id Guia Pagamento
								null, // Tipo Débito
								null, // Data Emissão
								null, // Data Vencimento
								null, // Valor
								null, // Valor Total Débitos
								null, // Valor Atualizado
								contaValoresHelper.getConta().getImovel().getId(), // Matrícula do Imóvel
								contaValoresHelper.getConta().getReferencia(),
								codigoClienteInformado,
								nomeClienteInformado
						);

						bean.setIdResponsavel(idResponsavel.toString());
						bean.setNomeResponsavel(nomeResponsavel);
						bean.setCpfCnpjResponsavel(cpfCnpjResponsavel);
						bean.setEnderecoResponsavel(enderecoResponsavel);

						bean.setValorImpostos(valorImpostos);
						bean.setValorMulta(valorMulta);
						bean.setValorJurosMora(valorJurosMora);
						bean.setValorAtualizacaoMonetaria(valorAtualizacaoMonetaria);
					}

				} else {

					bean = new RelatorioConsultarDebitosClienteBean(
							mesAnoConta, // Mês/Ano Conta
							vencimento, // Data de Vencimento
							valorAgua, // Valor Água
							consumoAgua, // Consumo Água
							valorEsgoto, // Valor Esgoto
							consumoEsgoto, // Consumo Esgoto
							valorDebitos, // Valor Débitos
							valorCreditos, // Valor Créditos
							valorConta, // Valor
							acrescImpont, // Acréscimo Impontualidade
							situacao, // Situação
							null, // Id Débito a Cobrar
							null, // Tipo Débito
							null, // Mês/Ano Referência
							null, // Mês/Ano Cobrança
							null, // Parcelas a Cobrar
							null, // Valor a Cobrar
							null, // Id Crédito a Realizar
							null, // Tipo Crédito
							null, // Mês/Ano Referência
							null, // Mês/Ano Cobrança
							null, // Parcelas a Creditar
							null, // Valor
							null, // Id Guia Pagamento
							null, // Tipo Débito
							null, // Data Emissão
							null, // Data Vencimento
							null, // Valor
							null, // Valor Total Débitos
							null, // Valor Atualizado
							contaValoresHelper.getConta().getReferencia(),
							codigoClienteInformado,
							nomeClienteInformado
					);
					
					bean.setIdResponsavel(idResponsavel.toString());
					bean.setNomeResponsavel(nomeResponsavel);
					bean.setCpfCnpjResponsavel(cpfCnpjResponsavel);
					bean.setEnderecoResponsavel(enderecoResponsavel);
					
					bean.setValorImpostos(valorImpostos);
					bean.setValorMulta(valorMulta);
					bean.setValorJurosMora(valorJurosMora);
					bean.setValorAtualizacaoMonetaria(valorAtualizacaoMonetaria);
				}

				retorno.add(bean);

				idImovelAnterior = contaValoresHelper.getConta().getImovel().getId();
				
			}

		}

		// Débito a Cobrar
		if (colecaoDebitosACobrar != null && !colecaoDebitosACobrar.isEmpty()) {

			//Informações do Cliente Responsavel
			Integer idResponsavel = clienteSuperior ? 99999999 : 0;
			String cpfCnpjResponsavel = "";
			String nomeResponsavel = clienteSuperior ? "SEM RESPONSÁVEL" : null;
			String enderecoResponsavel = "";

			Iterator colecaoDebitosACobrarIterator = colecaoDebitosACobrar.iterator();
			Integer idImovelAnterior = null;

			while (colecaoDebitosACobrarIterator.hasNext()) {

				DebitoACobrar debitoACobrar = (DebitoACobrar) colecaoDebitosACobrarIterator.next();
				
				String imprimirEndereco = "";

				// Seta o imóvel anterior para verificar, posteriormente, se vai
				// criar um subtotal para o imóvel.
				if (idImovelAnterior == null) {
					idImovelAnterior = debitoACobrar.getImovel().getId();
					imprimirEndereco = "SIM";
					
					if(clienteSuperior){
						//Informação do Cliente Responsável 
						Object[] object = recuperarResponsavelImovel(debitoACobrar.getImovel().getId(), clienteSuperior);
						idResponsavel = (Integer) object[0];
						cpfCnpjResponsavel = (String) object[1];
						nomeResponsavel = (String) object[2];
						enderecoResponsavel = (String) object[3];
						cpfCnpjResponsavel = cpfCnpjResponsavel == null ?  "": cpfCnpjResponsavel;
					}

				}

				String tipoDebito = debitoACobrar.getDebitoTipo().getDescricao();

				String mesAnoReferenciaDebito = "";
				if (debitoACobrar.getAnoMesReferenciaDebito() != null) {
					mesAnoReferenciaDebito = Util.formatarAnoMesParaMesAno(debitoACobrar.getAnoMesReferenciaDebito());
				}

				String mesAnoCobrancaDebito = "";
				if (debitoACobrar.getAnoMesCobrancaDebito() != null) {
					mesAnoCobrancaDebito = Util.formatarAnoMesParaMesAno(debitoACobrar.getAnoMesCobrancaDebito());
				}

				String parcelasCobrar = "";
				if (debitoACobrar.getParcelasRestanteComBonus() != 0) {
					parcelasCobrar = "" + debitoACobrar.getParcelasRestanteComBonus();
				}

				BigDecimal valorCobrar = BigDecimal.ZERO;
				if (debitoACobrar.getValorTotalComBonus() != null) {
					valorCobrar = debitoACobrar.getValorTotalComBonus();
				}
				
				if (clienteSuperior && !idImovelAnterior.equals(debitoACobrar.getImovel().getId())) {
					Object[] object = recuperarResponsavelImovel(debitoACobrar.getImovel().getId(), clienteSuperior);
					idResponsavel = (Integer) object[0];
					cpfCnpjResponsavel = (String) object[1];
					nomeResponsavel = (String) object[2];
					enderecoResponsavel = (String) object[3];
					cpfCnpjResponsavel = cpfCnpjResponsavel == null ?  "": cpfCnpjResponsavel;
				}


				RelatorioConsultarDebitosClienteBean bean = null;

				if (pesquisaCliente != null && !pesquisaCliente.trim().equals("")) {

					// Caso tenha mudado o imóvel, verifica se teve mais de uma conta para o ele. Em caso afirmativo muda o valor da
					// variável para criar um subtotal, e em caso negativo reinicializa as variáveis de totalização.
					if (!idImovelAnterior.equals(debitoACobrar.getImovel().getId())) {
						imprimirEndereco = "SIM";
					}

					if (relatorioEndereco != null && !relatorioEndereco.trim().equals("")) {

						String endereco = fachada.pesquisarEndereco(debitoACobrar.getImovel().getId());
						Integer idUsuario = null;
						String nomeUsuario = "";
						String cpfCnpjUsuario = "";
						Object[] dadosCliente = null;
						dadosCliente = fachada.consultarDadosClienteUsuarioImovel(debitoACobrar.getImovel().getId() +"");
						
						if ( dadosCliente[0] != null ) {
							nomeUsuario = (String) dadosCliente[0];
						}
						
						if ( dadosCliente[1] != null ) {
							cpfCnpjUsuario = Util.formatarCpf((String)dadosCliente[1]);
						}else if ( dadosCliente[2] != null ) {
							cpfCnpjUsuario = Util.formatarCnpj((String) dadosCliente[2]);
						}
						
						if ( dadosCliente[3] != null ) {
							idUsuario = ((Integer) dadosCliente[3]);
						}
						
						bean = new RelatorioConsultarDebitosClienteBean(
								null, // Id Conta
								null, // Mês/Ano
								null, // Data de Vencimento
								null, // Valor Água
								null, // Consumo Água
								null, // Valor Esgoto
								null, // Consumo Esgoto
								null, // Valor Débitos
								null, // Valor Créditos
								null, // Valor
								null, // Acréscimo Impontualidade
								null, // Situação
								debitoACobrar.getId(), // Id Débito a Cobrar
								tipoDebito, // Tipo Débito
								mesAnoReferenciaDebito, // Mês/Ano Referência
								mesAnoCobrancaDebito, // Mês/Ano Cobrança
								parcelasCobrar, // Parcelas a Cobrar
								valorCobrar, // Valor a Cobrar
								null, // Id Crédito a Realizar
								null, // Tipo Crédito
								null, // Mês/Ano Referência
								null, // Mês/Ano Cobrança
								null, // Parcelas a Creditar
								null, // Valor
								null, // Id Guia Pagamento
								null, // Tipo Débito
								null, // Data Emissão
								null, // Data Vencimento
								null, // Valor
								null, // Valor Total Débitos
								null, // Valor Atualizado
								debitoACobrar.getImovel().getId(), // Matrícula do Imóvel
								endereco, // Endereço
								nomeUsuario, // Nome Usuário
								imprimirEndereco, // Verifica a Impressão do Endereço
								cpfCnpjUsuario,
								idUsuario.toString(),
								debitoACobrar.getAnoMesReferenciaContabil(),
								codigoClienteInformado,
								nomeClienteInformado
						);
						
						bean.setIdResponsavel(idResponsavel.toString());
						bean.setNomeResponsavel(nomeResponsavel);
						bean.setCpfCnpjResponsavel(cpfCnpjResponsavel);
						bean.setEnderecoResponsavel(enderecoResponsavel);
						
						imprimirEndereco = "";

					} else {

						bean = new RelatorioConsultarDebitosClienteBean(
								null, // Id Conta
								null, // Mês/Ano
								null, // Data de Vencimento
								null, // Valor Água
								null, // Consumo Água
								null, // Valor Esgoto
								null, // Consumo Esgoto
								null, // Valor Débitos
								null, // Valor Créditos
								null, // Valor
								null, // Acréscimo Impontualidade
								null, // Situação
								debitoACobrar.getId(), // Id Débito a Cobrar
								tipoDebito, // Tipo Débito
								mesAnoReferenciaDebito, // Mês/Ano Referência
								mesAnoCobrancaDebito, // Mês/Ano Cobrança
								parcelasCobrar, // Parcelas a Cobrar
								valorCobrar, // Valor a Cobrar
								null, // Id Crédito a Realizar
								null, // Tipo Crédito
								null, // Mês/Ano Referência
								null, // Mês/Ano Cobrança
								null, // Parcelas a Creditar
								null, // Valor
								null, // Id Guia Pagamento
								null, // Tipo Débito
								null, // Data Emissão
								null, // Data Vencimento
								null, // Valor
								null, // Valor Total Débitos
								null, // Valor Atualizado
								debitoACobrar.getImovel().getId(), // Matrícula do Imóvel
								debitoACobrar.getAnoMesReferenciaContabil(),
								codigoClienteInformado,
								nomeClienteInformado
						);
						
						bean.setIdResponsavel(idResponsavel.toString());
						bean.setNomeResponsavel(nomeResponsavel);
						bean.setCpfCnpjResponsavel(cpfCnpjResponsavel);
						bean.setEnderecoResponsavel(enderecoResponsavel);
					}

				} else {

					bean = new RelatorioConsultarDebitosClienteBean(
							null, // Mês/Ano Conta
							null, // Data de Vencimento
							null, // Valor Água
							null, // Consumo Água
							null, // Valor Esgoto
							null, // Consumo Esgoto
							null, // Valor Débitos
							null, // Valor Créditos
							null, // Valor
							null, // Acréscimo Impontualidade
							null, // Situação
							debitoACobrar.getId(), // Id Débito a Cobrar
							tipoDebito, // Tipo Débito
							mesAnoReferenciaDebito, // Mês/Ano Referência
							mesAnoCobrancaDebito, // Mês/Ano Cobrança
							parcelasCobrar, // Parcelas a Cobrar
							valorCobrar, // Valor a Cobrar
							null, // Id Crédito a Realizar
							null, // Tipo Crédito
							null, // Mês/Ano Referência
							null, // Mês/Ano Cobrança
							null, // Parcelas a Creditar
							null, // Valor
							null, // Id Guia Pagamento
							null, // Tipo Débito
							null, // Data Emissão
							null, // Data Vencimento
							null, // Valor
							null, // Valor Total Débitos
							null, // Valor Atualizado
							debitoACobrar.getAnoMesReferenciaContabil(),
							codigoClienteInformado,
							nomeClienteInformado
					);
					
					bean.setIdResponsavel(idResponsavel.toString());
					bean.setNomeResponsavel(nomeResponsavel);
					bean.setCpfCnpjResponsavel(cpfCnpjResponsavel);
					bean.setEnderecoResponsavel(enderecoResponsavel);
				}

				retorno.add(bean);
				idImovelAnterior = debitoACobrar.getImovel().getId();
			}
		}

		// Crédito a Realizar
		if (colecaoCreditosARealizar != null && !colecaoCreditosARealizar.isEmpty()) {

			//Informações do Cliente Responsavel
			Integer idResponsavel = clienteSuperior ? 99999999 : 0;
			String cpfCnpjResponsavel = "";
			String nomeResponsavel = clienteSuperior ? "SEM RESPONSÁVEL" : null;
			String enderecoResponsavel = "";

			Iterator colecaoCreditosARealizarIterator = colecaoCreditosARealizar.iterator();

			Integer idImovelAnterior = null;

			while (colecaoCreditosARealizarIterator.hasNext()) {

				CreditoARealizar creditoARealizar = (CreditoARealizar) colecaoCreditosARealizarIterator.next();
				
				String imprimirEndereco = "";

				// Seta o imóvel anterior para, no relatório, verificar se vai
				// exibir o endereço
				if (idImovelAnterior == null) {
					idImovelAnterior = creditoARealizar.getImovel().getId();
					imprimirEndereco = "SIM";
					
					if(clienteSuperior){
						Object[] object = recuperarResponsavelImovel(creditoARealizar.getImovel().getId(), clienteSuperior);
						idResponsavel = (Integer) object[0];
						cpfCnpjResponsavel = (String) object[1];
						nomeResponsavel = (String) object[2];
						enderecoResponsavel = (String) object[3];
						cpfCnpjResponsavel = cpfCnpjResponsavel == null ?  "": cpfCnpjResponsavel;
					}
				}

				String tipoCredito = creditoARealizar.getCreditoTipo().getDescricao();

				String mesAnoReferenciaCredito = "";
				if (creditoARealizar.getAnoMesReferenciaCredito() != null) {
					mesAnoReferenciaCredito = Util.formatarAnoMesParaMesAno(creditoARealizar.getAnoMesReferenciaCredito());
				}

				String mesAnoCobrancaCredito = "";
				if (creditoARealizar.getAnoMesCobrancaCredito() != null) {
					mesAnoCobrancaCredito = Util.formatarAnoMesParaMesAno(creditoARealizar.getAnoMesCobrancaCredito());
				}

				String parcelasCreditar = "";
				if (creditoARealizar.getParcelasRestanteComBonus() != 0) {
					parcelasCreditar = "" + creditoARealizar.getParcelasRestanteComBonus();
				}

				BigDecimal valorCreditar = BigDecimal.ZERO;
				if (creditoARealizar.getValorTotalComBonus() != null) {
					valorCreditar = creditoARealizar.getValorTotalComBonus();
				}

				if(clienteSuperior && !creditoARealizar.getImovel().getId().equals(idImovelAnterior)){
					Object[] object = recuperarResponsavelImovel(creditoARealizar.getImovel().getId(), clienteSuperior);
					idResponsavel = (Integer) object[0];
					cpfCnpjResponsavel = (String) object[1];
					nomeResponsavel = (String) object[2];
					enderecoResponsavel = (String) object[3];
					cpfCnpjResponsavel = cpfCnpjResponsavel == null ?  "": cpfCnpjResponsavel;
				}
				
				RelatorioConsultarDebitosClienteBean bean = null;

				if (pesquisaCliente != null && !pesquisaCliente.trim().equals("")) {

					if (relatorioEndereco != null && !relatorioEndereco.trim().equals("")) {

						String endereco = fachada.pesquisarEndereco(creditoARealizar.getImovel().getId());
						
						Integer idUsuario = null;
						String nomeUsuario = "";
						String cpfCnpjUsuario = "";
						Object[] dadosCliente = null;
						dadosCliente = fachada.consultarDadosClienteUsuarioImovel(creditoARealizar.getImovel().getId() +"");
						
						if ( dadosCliente[0] != null ) {
							nomeUsuario = (String) dadosCliente[0];
						}
						
						if ( dadosCliente[1] != null ) {
							cpfCnpjUsuario = Util.formatarCpf((String)dadosCliente[1]);
						}else if ( dadosCliente[2] != null ) {
							cpfCnpjUsuario = Util.formatarCnpj((String) dadosCliente[2]);
						}
						
						if ( dadosCliente[3] != null ) {
							idUsuario = ((Integer) dadosCliente[3]);
						}
						
						if (!creditoARealizar.getImovel().getId().equals(idImovelAnterior)) {
							imprimirEndereco = "SIM";
						}

						bean = new RelatorioConsultarDebitosClienteBean(
								null, // Id Conta
								null, // Mês/Ano
								null, // Data de Vencimento
								null, // Valor Água
								null, // Consumo Água
								null, // Valor Esgoto
								null, // Consumo Esgoto
								null, // Valor Débitos
								null, // Valor Créditos
								null, // Valor
								null, // Acréscimo Impontualidade
								null, // Situação
								null, // Id Débito a Cobrar
								null, // Tipo Débito
								null, // Mês/Ano Referência
								null, // Mês/Ano Cobrança
								null, // Parcelas a Cobrar
								null, // Valor a Cobrar
								creditoARealizar.getId(), // Id Crédito a Realizar
								tipoCredito, // Tipo Crédito
								mesAnoReferenciaCredito, // Mês/Ano Referência
								mesAnoCobrancaCredito, // Mês/Ano Cobrança
								parcelasCreditar, // Parcelas a Creditar
								valorCreditar, // Valor
								null, // Id Guia Pagamento
								null, // Tipo Débito
								null, // Data Emissão
								null, // Data Vencimento
								null, // Valor
								null, // Valor Total Débitos
								null, // Valor Atualizado
								creditoARealizar.getImovel().getId(), // Matrícula do Imóvel
								endereco, // Endereço
								nomeUsuario, // Nome Usuário
								imprimirEndereco, // Verifica a Impressão do Endereço
								cpfCnpjUsuario,
								idUsuario.toString(),
								Util.getAnoMesComoInteger(creditoARealizar.getGeracaoCredito()),
								codigoClienteInformado,
								nomeClienteInformado
						);

						idImovelAnterior = creditoARealizar.getImovel().getId();
						
						imprimirEndereco = "";
						
						bean.setIdResponsavel(idResponsavel.toString());
						bean.setNomeResponsavel(nomeResponsavel);
						bean.setCpfCnpjResponsavel(cpfCnpjResponsavel);
						bean.setEnderecoResponsavel(enderecoResponsavel);

					} else {

						bean = new RelatorioConsultarDebitosClienteBean(
								null, // Id Conta
								null, // Mês/Ano
								null, // Data de Vencimento
								null, // Valor Água
								null, // Consumo Água
								null, // Valor Esgoto
								null, // Consumo Esgoto
								null, // Valor Débitos
								null, // Valor Créditos
								null, // Valor
								null, // Acréscimo Impontualidade
								null, // Situação
								null, // Id Débito a Cobrar
								null, // Tipo Débito
								null, // Mês/Ano Referência
								null, // Mês/Ano Cobrança
								null, // Parcelas a Cobrar
								null, // Valor a Cobrar
								creditoARealizar.getId(), // Id Crédito a Realizar
								tipoCredito, // Tipo Crédito
								mesAnoReferenciaCredito, // Mês/Ano Referência
								mesAnoCobrancaCredito, // Mês/Ano Cobrança
								parcelasCreditar, // Parcelas a Creditar
								valorCreditar, // Valor
								null, // Id Guia Pagamento
								null, // Tipo Débito
								null, // Data Emissão
								null, // Data Vencimento
								null, // Valor
								null, // Valor Total Débitos
								null, // Valor Atualizado
								creditoARealizar.getImovel().getId(), // Matrícula do Imóvel
								Util.getAnoMesComoInteger(creditoARealizar.getGeracaoCredito()),
								codigoClienteInformado,
								nomeClienteInformado
						);

						bean.setIdResponsavel(idResponsavel.toString());
						bean.setNomeResponsavel(nomeResponsavel);
						bean.setCpfCnpjResponsavel(cpfCnpjResponsavel);
						bean.setEnderecoResponsavel(enderecoResponsavel);
					}

				} else {

					bean = new RelatorioConsultarDebitosClienteBean(
							null, // Mês/Ano Conta
							null, // Data de Vencimento
							null, // Valor Água
							null, // Consumo Água
							null, // Valor Esgoto
							null, // Consumo Esgoto
							null, // Valor Débitos
							null, // Valor Créditos
							null, // Valor
							null, // Acréscimo Impontualidade
							null, // Situação
							null, // Id Débito a Cobrar
							null, // Tipo Débito
							null, // Mês/Ano Referência
							null, // Mês/Ano Cobrança
							null, // Parcelas a Cobrar
							null, // Valor a Cobrar
							creditoARealizar.getId(), // Id Crédito a Realizar
							tipoCredito, // Tipo Crédito
							mesAnoReferenciaCredito, // Mês/Ano Referência
							mesAnoCobrancaCredito, // Mês/Ano Cobrança
							parcelasCreditar, // Parcelas a Creditar
							valorCreditar, // Valor
							null, // Id Guia Pagamento
							null, // Tipo Débito
							null, // Data Emissão
							null, // Data Vencimento
							null, // Valor
							null, // Valor Total Débitos
							null, // Valor Atualizado
							Util.getAnoMesComoInteger(creditoARealizar.getGeracaoCredito()),
							codigoClienteInformado,
							nomeClienteInformado
					);

					bean.setIdResponsavel(idResponsavel.toString());
					bean.setNomeResponsavel(nomeResponsavel);
					bean.setCpfCnpjResponsavel(cpfCnpjResponsavel);
					bean.setEnderecoResponsavel(enderecoResponsavel);
				}

				retorno.add(bean);
				idImovelAnterior = creditoARealizar.getImovel().getId();
			}

		}

		// Guia Pagamento
		if (colecaoGuiasPagamento != null && !colecaoGuiasPagamento.isEmpty()) {

			Integer idImovelAnterior = null;
			
			//Informações do Cliente Responsavel
			Integer idResponsavel = clienteSuperior ? 99999999 : 0;
			String cpfCnpjResponsavel = "";
			String nomeResponsavel = clienteSuperior ? "SEM RESPONSÁVEL" : null;
			String enderecoResponsavel = "";

			Iterator colecaoGuiasPagamentoIterator = colecaoGuiasPagamento.iterator();

			while (colecaoGuiasPagamentoIterator.hasNext()) {

				GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiasPagamentoIterator.next();
				
				FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
				filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, guiaPagamentoValoresHelper.getGuiaPagamento().getId()));
				
				Collection colecaoGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
				
				GuiaPagamento guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(colecaoGuiaPagamento);
				
				String imprimirEndereco = "";

				String tipoDebito = guiaPagamentoValoresHelper.getGuiaPagamento().getDebitoTipo().getDescricao();

				String dataEmissaoGuia = "";
				if (guiaPagamentoValoresHelper.getGuiaPagamento().getDataEmissao() != null) {
					dataEmissaoGuia = Util.formatarData(guiaPagamentoValoresHelper.getGuiaPagamento().getDataEmissao());
				}

				String dataVencimentoGuia = "";
				if (guiaPagamentoValoresHelper.getGuiaPagamento().getDataVencimento() != null) {
					dataVencimentoGuia = Util.formatarData(guiaPagamentoValoresHelper.getGuiaPagamento().getDataVencimento());
				}

				BigDecimal valorGuia = BigDecimal.ZERO;
				if (guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito() != null) {
					valorGuia = guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito();
				}

				RelatorioConsultarDebitosClienteBean bean = null;

				if (pesquisaCliente != null && !pesquisaCliente.trim().equals("")) {

					Integer idImovel = 0;
					String endereco = "";
					String nomeUsuario = "";
					String cpfCnpjUsuario = "";
					Integer idUsuario = null;
					
					if (guiaPagamento.getImovel() != null && guiaPagamento.getImovel().getId() != null) {
						
						idImovel = guiaPagamento.getImovel().getId();

						// Seta o imóvel anterior para, no relatório, verificar se vai exibir o endereço
						if (idImovelAnterior == null) {
							idImovelAnterior = guiaPagamento.getImovel().getId();
							imprimirEndereco = "SIM";
							
							if(clienteSuperior){
								Object[] object = recuperarResponsavelImovel(guiaPagamento.getImovel().getId(), clienteSuperior);
								idResponsavel = (Integer) object[0];
								cpfCnpjResponsavel = (String) object[1];
								nomeResponsavel = (String) object[2];
								enderecoResponsavel = (String) object[3];
								cpfCnpjResponsavel = cpfCnpjResponsavel == null ?  "": cpfCnpjResponsavel;
							}
						}

						endereco = fachada.pesquisarEndereco(idImovel);
						
						Object[] dadosCliente = null;
						
						dadosCliente = fachada.consultarDadosClienteUsuarioImovel(idImovel.toString());
						
						if ( dadosCliente[0] != null ) {
							nomeUsuario = (String) dadosCliente[0];
						}
						
						if ( dadosCliente[1] != null ) {
							cpfCnpjUsuario = Util.formatarCpf((String)dadosCliente[1]);
						}else if ( dadosCliente[2] != null ) {
							cpfCnpjUsuario = Util.formatarCnpj((String) dadosCliente[2]);
						}

						if ( dadosCliente[3] != null ) {
							idUsuario = ((Integer) dadosCliente[3]);
						}
						
						if(clienteSuperior && !guiaPagamento.getImovel().getId().equals(idImovelAnterior)){
							Object[] object = recuperarResponsavelImovel(guiaPagamento.getImovel().getId(), clienteSuperior);
							idResponsavel = (Integer) object[0];
							cpfCnpjResponsavel = (String) object[1];
							nomeResponsavel = (String) object[2];
							enderecoResponsavel = (String) object[3];
							cpfCnpjResponsavel = cpfCnpjResponsavel == null ?  "": cpfCnpjResponsavel;
						}
						
						
					} else {
						endereco = fachada.pesquisarEnderecoClienteAbreviado(new Integer(codigoCliente));
						nomeUsuario = nomeCliente;
						
						FiltroCliente filtroCliente = new FiltroCliente();
						filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, codigoCliente));
						Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
						
						if ( colecaoCliente != null && !colecaoCliente.equals("") ) {
							Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
							
							idUsuario = cliente.getId();
							
							if(cpfCnpjUsuario != null ) {
								cpfCnpjUsuario = cliente.getCpfFormatado();
							} else {
								cpfCnpjUsuario = cliente.getCnpjFormatado();
							}
						}
						
						// Seta o cliente anterior para, no relatório, verificar se vai exibir o endereço
						if (idImovelAnterior == null) {
								idImovelAnterior = guiaPagamento.getCliente().getId();
							imprimirEndereco = "SIM";
						}
						
						
						idResponsavel = clienteSuperior ? 99999999 : 0;
						cpfCnpjResponsavel = "";
						nomeResponsavel = clienteSuperior ? "SEM RESPONSÁVEL" : null;
						enderecoResponsavel = "";
						
					}

					if (relatorioEndereco != null && !relatorioEndereco.trim().equals("")) {
						
						if(idImovel.compareTo(idImovelAnterior) != 0){
							imprimirEndereco = "SIM";
						}

						bean = new RelatorioConsultarDebitosClienteBean(
								null, // Id Conta
								null, // Mês/Ano
								null, // Data de Vencimento
								null, // Valor Água
								null, // Consumo Água
								null, // Valor Esgoto
								null, // Consumo Esgoto
								null, // Valor Débitos
								null, // Valor Créditos
								null, // Valor
								null, // Acréscimo Impontualidade
								null, // Situação
								null, // Id Débito a Cobrar
								null, // Tipo Débito
								null, // Mês/Ano Referência
								null, // Mês/Ano Cobrança
								null, // Parcelas a Cobrar
								null, // Valor a Cobrar
								null, // Id Crédito a Realizar
								null, // Tipo Crédito
								null, // Mês/Ano Referência
								null, // Mês/Ano Cobrança
								null, // Parcelas a Creditar
								null, // Valor
								guiaPagamentoValoresHelper.getGuiaPagamento().getId(), // Id Guia Pagamento
								tipoDebito, // Tipo Débito
								dataEmissaoGuia, // Data Emissão
								dataVencimentoGuia, // Data Vencimento
								valorGuia, // Valor
								null, // Valor Total Débitos
								null, // Valor Atualizado
								idImovel, // Matrícula do Imóvel
								endereco, // Endereço
								nomeUsuario, // Nome Usuário
								imprimirEndereco, // Verifica a Impressão do Endereço
								cpfCnpjUsuario,
								idUsuario.toString(),
								guiaPagamentoValoresHelper.getGuiaPagamento().getAnoMesReferenciaContabil(),
								codigoClienteInformado,
								nomeClienteInformado
						);
						
						bean.setIdResponsavel(idResponsavel.toString());
						bean.setNomeResponsavel(nomeResponsavel);
						bean.setCpfCnpjResponsavel(cpfCnpjResponsavel);
						bean.setEnderecoResponsavel(enderecoResponsavel);
						
						imprimirEndereco = "";

						if (guiaPagamento.getImovel() != null && guiaPagamento.getImovel().getId() != null) {
							idImovelAnterior = guiaPagamento.getImovel().getId();
						} else {
							idImovelAnterior = guiaPagamento.getCliente().getId();
						}

					} else {

						bean = new RelatorioConsultarDebitosClienteBean(
								null, // Id Conta
								null, // Mês/Ano
								null, // Data de Vencimento
								null, // Valor Água
								null, // Consumo Água
								null, // Valor Esgoto
								null, // Consumo Esgoto
								null, // Valor Débitos
								null, // Valor Créditos
								null, // Valor
								null, // Acréscimo Impontualidade
								null, // Situação
								null, // Id Débito a Cobrar
								null, // Tipo Débito
								null, // Mês/Ano Referência
								null, // Mês/Ano Cobrança
								null, // Mês/Ano Cobrança
								null, // Valor a Cobrar
								null, // Id Crédito a Realizar
								null, // Tipo Crédito
								null, // Mês/Ano Referência
								null, // Mês/Ano Cobrança
								null, // Parcelas a Creditar
								null, // Valor
								guiaPagamentoValoresHelper.getGuiaPagamento().getId(), // Id Guia Pagamento
								tipoDebito, // Tipo Débito
								dataEmissaoGuia, // Data Emissão
								dataVencimentoGuia, // Data Vencimento
								valorGuia, // Valor
								null, // Valor Total Débitos
								null, // Valor Atualizado
								idImovel, // Matrícula do Imóvel
								guiaPagamentoValoresHelper.getGuiaPagamento().getAnoMesReferenciaContabil(),
								codigoClienteInformado,
								nomeClienteInformado
						);
						
						bean.setIdResponsavel(idResponsavel.toString());
						bean.setNomeResponsavel(nomeResponsavel);
						bean.setCpfCnpjResponsavel(cpfCnpjResponsavel);
						bean.setEnderecoResponsavel(enderecoResponsavel);

					}

				} else {

					bean = new RelatorioConsultarDebitosClienteBean(
							null, // Mês/Ano Conta
							null, // Data de Vencimento
							null, // Valor Água
							null, // Consumo Água
							null, // Valor Esgoto
							null, // Consumo Esgoto
							null, // Valor Débitos
							null, // Valor Créditos
							null, // Valor
							null, // Acréscimo Impontualidade
							null, // Situação
							null, // Id Débito a Cobrar
							null, // Tipo Débito
							null, // Mês/Ano Referência
							null, // Mês/Ano Cobrança
							null, // Parcelas a Cobrar
							null, // Valor a Cobrar
							null, // Id Crédito a Realizar
							null, // Tipo Crédito
							null, // Mês/Ano Referência
							null, // Mês/Ano Cobrança
							null, // Parcelas a Creditar
							null, // Valor
							guiaPagamentoValoresHelper.getGuiaPagamento().getId(), // Id Guia Pagamento
							tipoDebito, // Tipo Débito
							dataEmissaoGuia, // Data Emissão
							dataVencimentoGuia, // Data Vencimento
							valorGuia, // Valor
							null, // Valor Total Débitos
							null, // Valor Atualizado
							guiaPagamentoValoresHelper.getGuiaPagamento().getAnoMesReferenciaContabil(),
							codigoClienteInformado,
							nomeClienteInformado
					);
				}
				
				bean.setIdResponsavel(idResponsavel.toString());
				bean.setNomeResponsavel(nomeResponsavel);
				bean.setCpfCnpjResponsavel(cpfCnpjResponsavel);
				bean.setEnderecoResponsavel(enderecoResponsavel);

				retorno.add(bean);

				if (guiaPagamento.getImovel() != null && guiaPagamento.getImovel().getId() != null) {
					idImovelAnterior = guiaPagamento.getImovel().getId();
				} else {
					idImovelAnterior = guiaPagamento.getCliente().getId();
				}
				
			}

		}
		
		 Collections.sort((List<RelatorioConsultarDebitosClienteBean>) retorno, new Comparator<RelatorioConsultarDebitosClienteBean>() {
			 public int compare(RelatorioConsultarDebitosClienteBean a, RelatorioConsultarDebitosClienteBean b) {
			   	Integer x = 0;
			   	Integer y = 1;

			       return new CompareToBuilder()
			       .append(a.getIdResponsavel(), b.getIdResponsavel())

			       .append(a.getIdConta() == null ? y : x, b.getIdConta() == null ? y : x)
			       .append(a.getIdDebitoACobrar() == null ? y : x, b.getIdDebitoACobrar() == null ? y : x)
			       .append(a.getIdCreditoARealizar() == null ? y : x, b.getIdCreditoARealizar() == null ? y : x)
			       .append(a.getIdGuiaPagamento() == null ? y : x, b.getIdGuiaPagamento() == null ? y : x)

			       	.append(a.getIdImovel(), b.getIdImovel())
			       	.append(a.getAnoMes(), b.getAnoMes())

			       .toComparison();
			   }
			});
		 
		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {

		Collection colecaoContaValores = (Collection) getParametro("colecaoContaValores");
		Collection colecaoDebitoACobrar = (Collection) getParametro("colecaoDebitoACobrar");
		Collection colecaoCreditoARealizar = (Collection) getParametro("colecaoCreditoARealizar");
		Collection colecaoGuiaPagamento = (Collection) getParametro("colecaoGuiasPagamento");
		String pesquisaCliente = (String) getParametro("pesquisaCliente");
		String relatorioEndereco = (String) getParametro("relatorioEndereco");
		String codigoCliente = (String) getParametro("codigoCliente");
		String nomeCliente = (String) getParametro("nomeCliente");
		String cpfCnpj = (String) getParametro("cpfCnpj");
		String idImovel = (String) getParametro("idImovel");
		String inscricao = (String) getParametro("inscricao");
		String clienteUsuario = (String) getParametro("clienteUsuario");
		String cpfCnpjCliente = (String) getParametro("cpfCnpjCliente");
		String endereco = (String) getParametro("endereco");
		ClienteRelacaoTipo tipoRelacao = (ClienteRelacaoTipo) getParametro("tipoRelacao");
		String periodoReferenciaInicial = (String) getParametro("periodoReferenciaInicial");
		String periodoReferenciaFinal = (String) getParametro("periodoReferenciaFinal");
		String dataVencimentoInicial = (String) getParametro("dataVencimentoInicial");
		String dataVencimentoFinal = (String) getParametro("dataVencimentoFinal");
		String valorTotalDebitos = (String) getParametro("valorTotalDebitos");
		String valorTotalDebitosAtualizado = (String) getParametro("valorTotalDebitosAtualizado");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Boolean clienteSuperior = (Boolean)getParametro("clienteSuperior");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		parametros.put("clienteSuperior", clienteSuperior);
		parametros.put("valorTotalDebitos", valorTotalDebitos);
		parametros.put("valorTotalDebitosAtualizado", valorTotalDebitosAtualizado);

		//Usuario que emite o relatorio
		Usuario usuario = this.getUsuario();
		String nomeUsuario = usuario.getNomeUsuario();
		parametros.put("nomeUsuario", nomeUsuario);
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		parametros.put("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		
		if (pesquisaCliente != null && !pesquisaCliente.trim().equals("")) {
			parametros.put("codigoCliente", codigoCliente);
			parametros.put("nomeCliente", nomeCliente);
			if( cpfCnpj != null && !cpfCnpj.equals("") ) {
				parametros.put("cpfCnpj", cpfCnpj);
			} else {
				cpfCnpj = "";
				parametros.put("cpfCnpj", cpfCnpj);
			}

 			if (periodoReferenciaInicial != null && !periodoReferenciaInicial.trim().equals("")) {
				parametros.put("periodoReferenciaDebito", periodoReferenciaInicial + " a " + periodoReferenciaFinal);
			}

			if (dataVencimentoInicial != null && !dataVencimentoInicial.trim().equals("")) {
				parametros.put("periodoVencimentoDebito", dataVencimentoInicial + " a " + dataVencimentoFinal);
			}

			if (tipoRelacao != null) {
				parametros.put("tipoRelacao", tipoRelacao.getDescricao());
			}
		} else {
			parametros.put("idImovel", idImovel);
			parametros.put("inscricao", inscricao);
			parametros.put("clienteUsuario", clienteUsuario);
			parametros.put("endereco", endereco);
			parametros.put("cpfCnpjCliente", cpfCnpjCliente);
		}

		Collection<RelatorioConsultarDebitosClienteBean> colecaoBean = this.inicializarBeanRelatorio(
				colecaoContaValores, colecaoDebitoACobrar, colecaoCreditoARealizar,colecaoGuiaPagamento,clienteSuperior);

//		String nomeRelatorio = ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS;
//		if (relatorioEndereco != null && !relatorioEndereco.trim().isEmpty()) {
//			nomeRelatorio = ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS;
//		} else if (pesquisaCliente != null && !pesquisaCliente.trim().isEmpty()) {
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS_CLIENTE;
//		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);
		return this.gerarRelatorio(nomeRelatorio, parametros, ds, tipoFormatoRelatorio);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioConsultarDebitos", this);
	}
	
	
	private Object[] recuperarResponsavelImovel(Integer idImovel, Boolean clienteSuperior){
		Object[] retorno = responsavelImovel.get(idImovel);
		
		if(retorno != null){
			return retorno;
		}
		
		retorno = new Object[4];
		Object[] objectClienteResponsavel = Fachada.getInstancia().pesquisarClienteResponsavel(idImovel);
		String cpfCnpj = "";
		
		if(objectClienteResponsavel != null){
			retorno[0] = objectClienteResponsavel[0];
			
			if(objectClienteResponsavel[1] != null){
				cpfCnpj = (String)objectClienteResponsavel[1];
				
				if(cpfCnpj.length() <= 11){
					cpfCnpj = Util.formatarCpf(cpfCnpj);
				}else{
					cpfCnpj = Util.formatarCnpj(cpfCnpj);
				}
			}
			
			retorno[1] = cpfCnpj;
			retorno[2] = objectClienteResponsavel[2];
			retorno[3] = "";

		    Collection<?> colecao = Fachada.getInstancia().pesquisarClientesEnderecosAbreviado((Integer)retorno[0]);
			Object endereco = Util.retonarObjetoDeColecao(colecao);
			if(endereco != null) retorno[3] = endereco.toString();
		}else{
			retorno[0] = clienteSuperior ? 99999999 : 0;
			retorno[1] = "";
			retorno[2] = clienteSuperior ? "SEM RESPONSÁVEL" : null;
			retorno[3] = "";
		}

		responsavelImovel.put(idImovel, retorno);

		return retorno;
	}
}