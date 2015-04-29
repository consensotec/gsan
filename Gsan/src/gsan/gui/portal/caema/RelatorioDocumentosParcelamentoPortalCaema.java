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
 * Mila Maria Coelho de Ara�jo
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
package gsan.gui.portal.caema;

import gsan.arrecadacao.pagamento.GuiaPagamento;
import gsan.arrecadacao.pagamento.GuiaPagamentoItem;
import gsan.batch.Relatorio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.cobranca.bean.ParcelamentoRelatorioHelper;
import gsan.cobranca.parcelamento.ParcelamentoItem;
import gsan.fachada.Fachada;
import gsan.faturamento.FiltroGuiaPagamentoItem;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.credito.CreditoARealizar;
import gsan.faturamento.debito.DebitoACobrar;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.arrecadacao.pagamento.GuiaPagamentoRelatorioHelper;
import gsan.relatorio.arrecadacao.pagamento.RelatorioEmitirGuiaPagamentoDetailBean;
import gsan.relatorio.cobranca.parcelamento.RelatorioParcelamentoDetalhamentoBean;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Classe respons�vel por criar o relat�rio dos documentos
 * de parcelamento para loja virtual (termo e a guia de pagamento)
 * Os m�todos chamados foram os m�otods j� existentes do GSAN.
 * O arquivo .jasper � a jun��o dos relat�rios j� existentes
 * do GSAN. (Relat�rio Emitir Guia e o Termo de Parcelamento.)
 * 
 * @author Diogo Peixoto
 * @since 11/07/2011
 */
public class RelatorioDocumentosParcelamentoPortalCaema extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioDocumentosParcelamentoPortalCaema(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DOCUMENTOS_PARCELAMENTO_LOJA_VIRTUAL);
	}

	@Deprecated
	public RelatorioDocumentosParcelamentoPortalCaema() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		String idParcelamento = (String) getParametro("idParcelamento");
		SistemaParametro sistemaParametro = (SistemaParametro) getParametro("sistemaParametro");
		UnidadeOrganizacional unidadeUsuario = (UnidadeOrganizacional) getParametro("unidadeUsuario");
		Usuario usuarioLogado = (Usuario) getParametro("usuario");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		String descricaoUnidadeUsuario = "";
		if(unidadeUsuario != null && unidadeUsuario.getDescricao() != null){
			descricaoUnidadeUsuario = unidadeUsuario.getDescricao();
		}

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List<RelatorioDocumentosParcelamentoPortalCaemaBean> relatorioParcelamentosBeans = new ArrayList<RelatorioDocumentosParcelamentoPortalCaemaBean>();

		Fachada fachada = Fachada.getInstancia();

		RelatorioDocumentosParcelamentoPortalCaemaBean relatorioParcelamentoBean = null;

		ParcelamentoRelatorioHelper parcelamentoRelatorioHelper = fachada
		.pesquisarParcelamentoRelatorio(Integer.parseInt( idParcelamento ) );

		String idFuncionario = "";
		if (parcelamentoRelatorioHelper.getIdFuncionario() != null){
			idFuncionario = parcelamentoRelatorioHelper.getIdFuncionario().toString();
		}

		// se a cole��o de par�metros da analise n�o for vazia
		if (parcelamentoRelatorioHelper != null) {

			String idImovel = parcelamentoRelatorioHelper.getIdImovel().toString();
			String nomeCliente = "";

			if (Util.verificarNaoVazio(parcelamentoRelatorioHelper.getNomeClienteParcelamento())){
				nomeCliente = parcelamentoRelatorioHelper.getNomeClienteParcelamento();
			}else{
				nomeCliente = parcelamentoRelatorioHelper.getNomeCliente();
			}

			String cpfCnpjCliente = "";
			if (Util.verificarNaoVazio(parcelamentoRelatorioHelper.getCpfClienteParcelamento())){
				cpfCnpjCliente = parcelamentoRelatorioHelper.getCpfClienteParcelamento();
			}else{
				cpfCnpjCliente = parcelamentoRelatorioHelper.getCpfCnpj();
			}

			Collection colecaoRelatorioParcelamentoDetalhamentosBeans = new ArrayList();

			// Guardaremos o maior e menor ano mes de referencia do documento processado
			Integer maiorAnoMesReferenciaDocumento = null;
			Integer menorAnoMesReferenciaDocumento = null;			

			Collection colecaoRelatorioParcelamentoItens = fachada
			.pesquisarParcelamentoItemPorIdParcelamentoRelatorio(Integer.parseInt(idParcelamento));

			if (colecaoRelatorioParcelamentoItens != null && !colecaoRelatorioParcelamentoItens.isEmpty()) {
				Iterator colecaoRelatorioParcelamentoItensIterator = colecaoRelatorioParcelamentoItens.iterator();

				RelatorioParcelamentoDetalhamentoBean relatorioParcelamentoDetalhamentoBean = null;

				BigDecimal totalFaturas = new BigDecimal("0.00");
				BigDecimal totalServicos = new BigDecimal("0.00");
				BigDecimal totalGuias = new BigDecimal("0.00");
				BigDecimal totalCreditos = new BigDecimal("0.00");

				ParcelamentoItem parcelamentoItem = null;
				ParcelamentoItem parcelamentoItem2 = null;

				Object tipoAnterior = null;
				Object tipoAtual = null;

				while (colecaoRelatorioParcelamentoItensIterator.hasNext()) {
					if (tipoAnterior == null) {
						tipoAnterior = new Conta();
					} else {
						if (parcelamentoItem.getContaGeral().getConta().getReferencia() != 0) {
							tipoAnterior = parcelamentoItem.getContaGeral().getConta();
						} else if (parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getId() != null) {
							tipoAnterior = parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar();
						} else if (parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getId() != null) {
							tipoAnterior = parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento();
						} else {
							tipoAnterior = parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizar();
						}
					}

					if (parcelamentoItem2 != null && parcelamentoItem.getContaGeral().getConta().getReferencia() != 0) {
						parcelamentoItem = parcelamentoItem2;
						parcelamentoItem2 = null;
					} else {

						parcelamentoItem = (ParcelamentoItem) colecaoRelatorioParcelamentoItensIterator.next();
					}

					if (parcelamentoItem.getContaGeral().getConta().getReferencia() != 0) {
						tipoAtual = parcelamentoItem.getContaGeral().getConta();
					} else if (parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getId() != null) {
						tipoAtual = parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar();
					} else if (parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getId() != null) {
						tipoAtual = parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento();
					} else {
						tipoAtual = parcelamentoItem.getCreditoARealizarGeral().getCreditoARealizar().getCreditoTipo();
					}

					if (tipoAnterior instanceof Conta) {
						if (!(tipoAtual instanceof Conta)) {
							relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

									// Matr�cula do Im�vel
									idImovel,
									// Nome do Cliente
									nomeCliente,
									// Faturas em Aberto Refer�ncia 1
									"TOTAL",
									// Valor Fatura 1
									"",
									// Refer�ncia 2
									"",
									// Valor Fatura 2
									Util.formatarMoedaReal(totalFaturas),
									// Servi�os a Cobrar
									// C�digo
									"",
									// Descri��o
									"",
									// Valor
									"",
									// Guias de Pagamento
									// N�mero
									"",
									// Descri��o
									"",
									// Valor
									"",
									// Cr�ditos a Realizar
									// C�digo
									"",
									// Descri��o
									"",
									// Valor
									""
							);

							// adiciona o bean a cole��o
							colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
						}
					} else if (tipoAnterior instanceof DebitoACobrar) {
						if (!(tipoAtual instanceof DebitoACobrar)) {
							relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

									// Matr�cula do Im�vel
									idImovel,
									// Nome do Cliente
									nomeCliente,
									// Faturas em Aberto
									// Refer�ncia 1
									"",
									// Valor Fatura 1
									"",
									// Refer�ncia 2
									"",
									// Valor Fatura 2
									"",
									// Servi�os a Cobrar
									// C�digo
									"TOTAL",
									// Descri��o
									"",
									// Valor
									Util.formatarMoedaReal(totalServicos),
									// Guias de Pagamento
									// N�mero
									"",
									// Descri��o
									"",
									// Valor
									"",
									// Cr�ditos a Realizar
									// C�digo
									"",
									// Descri��o
									"",
									// Valor
									""
							);

							// adiciona o bean a cole��o
							colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
						}
					} else if (tipoAnterior instanceof GuiaPagamento) {
						if (!(tipoAtual instanceof GuiaPagamento)) {
							relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

									// Matr�cula do Im�vel
									idImovel,
									// Nome do Cliente
									nomeCliente,
									// Faturas em Aberto
									// Refer�ncia 1
									"",
									// Valor Fatura 1
									"",
									// Refer�ncia 2
									"",
									// Valor Fatura 2
									"",
									// Servi�os a Cobrar
									// C�digo
									"",
									// Descri��o
									"",
									// Valor
									"",
									// Guias de Pagamento
									// N�mero
									"TOTAL",
									// Descri��o
									"",
									// Valor
									Util.formatarMoedaReal(totalGuias),
									// Cr�ditos a Realizar
									// C�digo
									"",
									// Descri��o
									"",
									// Valor
									""
							);

							// adiciona o bean a cole��o
							colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
						}
					}

					if (parcelamentoItem.getContaGeral().getConta().getReferencia() != 0) {                     
						Conta conta = parcelamentoItem.getContaGeral().getConta();

						// Verificamos o maior ano mes de ferencia para o tipo conta
						if ( maiorAnoMesReferenciaDocumento == null || maiorAnoMesReferenciaDocumento < conta.getReferencia() ){
							maiorAnoMesReferenciaDocumento = conta.getReferencia() ;
						}

						// Verificamos o menor ano mes de ferencia para o tipo conta
						if ( menorAnoMesReferenciaDocumento == null || menorAnoMesReferenciaDocumento > conta.getReferencia() ){
							menorAnoMesReferenciaDocumento = conta.getReferencia() ;
						}                               


						totalFaturas = totalFaturas.add(conta.getValorTotal());

						if (colecaoRelatorioParcelamentoItensIterator.hasNext()) {

							parcelamentoItem2 = (ParcelamentoItem) colecaoRelatorioParcelamentoItensIterator.next();

							if (parcelamentoItem2.getContaGeral().getConta().getReferencia() != 0) {

								Conta conta2 = parcelamentoItem2.getContaGeral().getConta();

								// Verificamos o maior ano mes de ferencia para o tipo conta
								if ( maiorAnoMesReferenciaDocumento == null || maiorAnoMesReferenciaDocumento < conta2.getReferencia() ){
									maiorAnoMesReferenciaDocumento = conta2.getReferencia() ;
								}

								// Verificamos o menor ano mes de ferencia para o tipo conta
								if ( menorAnoMesReferenciaDocumento == null || menorAnoMesReferenciaDocumento > conta2.getReferencia() ){
									menorAnoMesReferenciaDocumento = conta2.getReferencia() ;
								}                               

								parcelamentoItem2 = null;

								totalFaturas = totalFaturas.add(conta2.getValorTotal());

								relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

										// Matr�cula do Im�vel
										idImovel,
										// Nome do Cliente
										nomeCliente,
										// Faturas em Aberto
										// Refer�ncia 1
										conta.getFormatarAnoMesParaMesAno(),
										// Valor Fatura 1
										Util.formatarMoedaReal(conta.getValorTotal()),
										// Refer�ncia 2
										conta2.getFormatarAnoMesParaMesAno(),
										// Valor Fatura 2
										Util.formatarMoedaReal(conta2.getValorTotal()),
										// Servi�os a Cobrar
										// C�digo
										"",
										// Descri��o
										"",
										// Valor
										"",
										// Guias de Pagamento
										// N�mero
										"",
										// Descri��o
										"",
										// Valor
										"",
										// Cr�ditos a Realizar
										// C�digo
										"",
										// Descri��o
										"",
										// Valor
										""
								);

								// adiciona o bean a cole��o
								colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);

							} else {
								relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

										// Matr�cula do Im�vel
										idImovel,
										// Nome do Cliente
										nomeCliente,
										// Faturas em Aberto
										// Refer�ncia 1
										conta.getFormatarAnoMesParaMesAno(),
										// Valor Fatura 1
										Util.formatarMoedaReal(conta.getValorTotal()),
										// Refer�ncia 2
										"",
										// Valor Fatura 2
										"",
										// Servi�os a Cobrar
										// C�digo
										"",
										// Descri��o
										"",
										// Valor
										"",
										// Guias de Pagamento
										// N�mero
										"",
										// Descri��o
										"",
										// Valor
										"",
										// Cr�ditos a Realizar
										// C�digo
										"",
										// Descri��o
										"",
										// Valor
										""
								);

								// adiciona o bean a cole��o
								colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);

							}
						} else {
							relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

									// Matr�cula do Im�vel
									idImovel,
									// Nome do Cliente
									nomeCliente,
									// Faturas em Aberto
									// Refer�ncia 1
									conta.getFormatarAnoMesParaMesAno(),
									// Valor Fatura 1
									Util.formatarMoedaReal(conta.getValorTotal()),
									// Refer�ncia 2
									"",
									// Valor Fatura 2
									"",
									// Servi�os a Cobrar
									// C�digo
									"",
									// Descri��o
									"",
									// Valor
									"",
									// Guias de Pagamento
									// N�mero
									"",
									// Descri��o
									"",
									// Valor
									"",
									// Cr�ditos a Realizar
									// C�digo
									"",
									// Descri��o
									"",
									// Valor
									""
							);

							// adiciona o bean a cole��o
							colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
						}

					} else if (parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getId() != null) {
						DebitoACobrar debitoACobrar = (DebitoACobrar) parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar();
						totalServicos = totalServicos.add(debitoACobrar.getValorTotalComBonus());

						relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

								// Matr�cula do Im�vel
								idImovel,
								// Nome do Cliente
								nomeCliente,
								// Faturas em Aberto
								// Refer�ncia 1
								"",
								// Valor Fatura 1
								"",
								// Refer�ncia 2
								"",
								// Valor Fatura 2
								"",
								// Servi�os a Cobrar
								// C�digo
								debitoACobrar.getDebitoTipo().getId().toString(),
								// Descri��o
								debitoACobrar.getDebitoTipo().getDescricao(),
								// Valor
								Util.formatarMoedaReal(debitoACobrar.getValorTotalComBonus()),
								// Guias de Pagamento
								// N�mero
								"",
								// Descri��o
								"",
								// Valor
								"",
								// Cr�ditos a Realizar
								// C�digo
								"",
								// Descri��o
								"",
								// Valor
								""
						);

						// adiciona o bean a cole��o
						colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);

					} else if (parcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getId() != null) {
						GuiaPagamento guiaPagamento = (GuiaPagamento) parcelamentoItem
						.getGuiaPagamentoGeral().getGuiaPagamento();

						totalGuias = totalGuias.add(guiaPagamento.getValorDebito());

						relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

								// Matr�cula do Im�vel
								idImovel,
								// Nome do Cliente
								nomeCliente,
								// Faturas em Aberto
								// Refer�ncia 1
								"",
								// Valor Fatura 1
								"",
								// Refer�ncia 2
								"",
								// Valor Fatura 2
								"",
								// Servi�os a Cobrar
								// C�digo
								"",
								// Descri��o
								"",
								// Valor
								"",
								// Guias de Pagamento
								// N�mero
								guiaPagamento.getId().toString(),
								// Descri��o
								guiaPagamento.getDebitoTipo().getDescricao(),
								// Valor
								Util.formatarMoedaReal(guiaPagamento.getValorDebito()),
								// Cr�ditos a Realizar
								// C�digo
								"",
								// Descri��o
								"",
								// Valor
								""
						);

						// adiciona o bean a cole��o
						colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
					} else if (parcelamentoItem.getCreditoARealizarGeral()
							.getCreditoARealizar().getCreditoTipo().getId() != null) {
						CreditoARealizar creditoARealizar = (CreditoARealizar) parcelamentoItem
						.getCreditoARealizarGeral().getCreditoARealizar();

						totalCreditos = totalCreditos.add(creditoARealizar.getValorTotalComBonus());

						relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

								// Matr�cula do Im�vel
								idImovel,
								// Nome do Cliente
								nomeCliente,
								// Faturas em Aberto
								// Refer�ncia 1
								"",
								// Valor Fatura 1
								"",
								// Refer�ncia 2
								"",
								// Valor Fatura 2
								"",
								// Servi�os a Cobrar
								// C�digo
								"",
								// Descri��o
								"",
								// Valor
								"",
								// Guias de Pagamento
								// N�mero
								"",
								// Descri��o
								"",
								// Valor
								"",
								// Cr�ditos a Realizar
								// C�digo
								creditoARealizar.getCreditoTipo().getId().toString(),
								// Descri��o
								creditoARealizar.getCreditoTipo().getDescricao(),
								// Valor
								Util.formatarMoedaReal(creditoARealizar.getValorTotalComBonus())
						);

						// adiciona o bean a cole��o
						colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);

					}

				}

				if (tipoAtual instanceof Conta) {
					relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

							// Matr�cula do Im�vel
							idImovel,
							// Nome do Cliente
							nomeCliente,
							// Faturas em Aberto
							// Refer�ncia 1
							"TOTAL",
							// Valor Fatura 1
							"",
							// Refer�ncia 2
							"",
							// Valor Fatura 2
							Util.formatarMoedaReal(totalFaturas),
							// Servi�os a Cobrar
							// C�digo
							"",
							// Descri��o
							"",
							// Valor
							"",
							// Guias de Pagamento
							// N�mero
							"",
							// Descri��o
							"",
							// Valor
							"",
							// Cr�ditos a Realizar
							// C�digo
							"",
							// Descri��o
							"",
							// Valor
							""
					);

					// adiciona o bean a cole��o
					colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
				} else if (tipoAtual instanceof DebitoACobrar) {
					relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

							// Matr�cula do Im�vel
							idImovel,
							// Nome do Cliente
							nomeCliente,
							// Faturas em Aberto
							// Refer�ncia 1
							"",
							// Valor Fatura 1
							"",
							// Refer�ncia 2
							"",
							// Valor Fatura 2
							"",
							// Servi�os a Cobrar
							// C�digo
							"TOTAL",
							// Descri��o
							"",
							// Valor
							Util.formatarMoedaReal(totalServicos),
							// Guias de Pagamento
							// N�mero
							"",
							// Descri��o
							"",
							// Valor
							"",
							// Cr�ditos a Realizar
							// C�digo
							"",
							// Descri��o
							"",
							// Valor
							""
					);

					// adiciona o bean a cole��o
					colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);

				} else if (tipoAtual instanceof GuiaPagamento) {
					relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

							// Matr�cula do Im�vel
							idImovel,
							// Nome do Cliente
							nomeCliente,
							// Faturas em Aberto
							// Refer�ncia 1
							"",
							// Valor Fatura 1
							"",
							// Refer�ncia 2
							"",
							// Valor Fatura 2
							"",
							// Servi�os a Cobrar
							// C�digo
							"",
							// Descri��o
							"",
							// Valor
							"",
							// Guias de Pagamento
							// N�mero
							"TOTAL",
							// Descri��o
							"",
							// Valor
							Util.formatarMoedaReal(totalGuias),
							// Cr�ditos a Realizar
							// C�digo
							"",
							// Descri��o
							"",
							// Valor
							""
					);

					// adiciona o bean a cole��o
					colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);

				} else if (tipoAtual instanceof CreditoARealizar) {

					relatorioParcelamentoDetalhamentoBean = new RelatorioParcelamentoDetalhamentoBean(

							// Matr�cula do Im�vel
							idImovel,
							// Nome do Cliente
							nomeCliente,
							// Faturas em Aberto
							// Refer�ncia 1
							"",
							// Valor Fatura 1
							"",
							// Refer�ncia 2
							"",
							// Valor Fatura 2
							"",
							// Servi�os a Cobrar
							// C�digo
							"",
							// Descri��o
							"",
							// Valor
							"",
							// Guias de Pagamento
							// N�mero
							"",
							// Descri��o
							"",
							// Valor
							"",
							// Cr�ditos a Realizar
							// C�digo
							"TOTAL",
							// Descri��o
							"",
							// Valor
							Util.formatarMoedaReal(totalCreditos)
					);

					// adiciona o bean a cole��o
					colecaoRelatorioParcelamentoDetalhamentosBeans.add(relatorioParcelamentoDetalhamentoBean);
				}

			}
			
			String municipioData = parcelamentoRelatorioHelper.getLocalidade();
			Locale loc = new Locale("pt");
			DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, loc);
			municipioData += " - " + df.format(new Date());
			
			relatorioParcelamentoBean = new RelatorioDocumentosParcelamentoPortalCaemaBean(

					// Matr�cula do Im�vel
					parcelamentoRelatorioHelper.getIdImovel().toString(),
					// Nome Cliente
					nomeCliente,
					// Endere�o
					parcelamentoRelatorioHelper.getEndereco(),
					// CPF/CNPJ
					cpfCnpjCliente,
					// Telefone
					parcelamentoRelatorioHelper.getTelefone(),
					// Data Parcelamento
					Util.formatarData(parcelamentoRelatorioHelper.getDataParcelamento()),
					// D�bitos
					// Faturas em Aberto
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorFaturasEmAberto()),
					// Servi�os a Cobrar
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorServicosACobrar()),
					// Atualiza��o Monet�ria
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorAtualizacaoMonetaria()),
					// Juros/Mora
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorJurosMora()),
					// Multa
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorMultas()),
					// Guia Pagamento
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorGuiaPagamento()),
					// Parcelamento a Cobrar
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorParcelamentoACobrar()),
					// Total D�bitos
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDebitos()),
					// Descontos
					// Desconto de Acr�scimos
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAcrescimo()),
					// Descontos de Antiguidade
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoAntiguidade()),
					// Desconto de Inatividade
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoInatividade()),
					// Cr�ditos a Realizar
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorCreditosRealizar()),
					// Total Descontos
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorTotalDescontos()),
					// Valor a Ser Negociado
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorASerNegociado()),
					// Condi��es de Negocia��o
					// Valor da Entrada
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorEntrada()),
					// N�mero de Parcelas
					parcelamentoRelatorioHelper.getNumeroParcelas().toString(),
					// Valor da Parcela
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorParcela()),
					// Valor a Ser Parcelado
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorASerParcelado()),
					// Solicita��o de Restabelecimento
					parcelamentoRelatorioHelper.getSolicitacaoRestabelecimento(),
					// Munic�pio e Data
					municipioData,
					// Id do Parcelamento
					idParcelamento.toString(),
					// Unidade do Usu�rio
					descricaoUnidadeUsuario,
					// Id do Funcion�rio
					idFuncionario,
					// Nome do Cliente do Parcelamento
					parcelamentoRelatorioHelper.getNomeClienteParcelamento(),
					// Cpf do Cliente do Parcelamento
					parcelamentoRelatorioHelper.getCpfClienteParcelamento(),
					// P�gina
					"1",
					// Cole��o de Detalhamentos
					colecaoRelatorioParcelamentoDetalhamentosBeans,
					//codigo da empresa
					sistemaParametro.getCodigoEmpresaFebraban().toString(),
					//rgCliente
					"",
					//nome usuario
					"",
					//matricula usuario
					"",
					//municipio
					"",
					//inicio parcelamento
					"",
					//final parcelamento
					"",
					parcelamentoRelatorioHelper.getTaxaJuros(),
					//Desconto de San��es Regulamentares
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoSancoesRegulamentares()),
					//Desconto Tarifa Social
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoTarifaSocial()),
					// CPF do usu�rio logado
					usuarioLogado.getCpf(),
					parcelamentoRelatorioHelper.getNomeDiretorComercial(),
					parcelamentoRelatorioHelper.getCpfDiretorComercial(),
					parcelamentoRelatorioHelper.getProfissao(),
					parcelamentoRelatorioHelper.getNomeDevedor(),
					parcelamentoRelatorioHelper.getCnpjDevedor(),
					parcelamentoRelatorioHelper.getEnderecoDevedor(),
					parcelamentoRelatorioHelper.getTelefoneDevedor(),
					parcelamentoRelatorioHelper.getIndicadorPessoaJuridica(),
					maiorAnoMesReferenciaDocumento,
					menorAnoMesReferenciaDocumento,
					//nome do usuario que efetuou o parcelamento 
					parcelamentoRelatorioHelper.getNomeUsuarioParcelamento(),
					Util.formatarMoedaReal(parcelamentoRelatorioHelper.getValorDescontoDebitoTotal()));

			
			//Adiociona os campos referentes a guia de pagamento.
			
			//adiciona o bean a cole��o
			relatorioParcelamentosBeans.add(relatorioParcelamentoBean);     

		}

		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("contrato", idParcelamento);
		// Empresa
		if (sistemaParametro.getNomeAbreviadoEmpresa() != null) {
			parametros.put("empresa", sistemaParametro.getNomeAbreviadoEmpresa());
		} else {
			parametros.put("empresa", "");
		}

		//Titulo Pagina
		if (sistemaParametro.getTituloPagina() != null) {
			parametros.put("tituloPagina", sistemaParametro.getTituloPagina());
		} else {
			parametros.put("tituloPagina", "");
		}

		// CNPJ da Empresa
		if (sistemaParametro.getCnpjEmpresa() != null) {

			String cnpjFormatado = sistemaParametro.getCnpjEmpresa().toString();
			String zeros = "";

			if (cnpjFormatado != null) {

				for (int a = 0; a < (14 - cnpjFormatado.length()); a++) {
					zeros = zeros.concat("0");
				}
				// concatena os zeros ao numero
				// caso o numero seja diferente de nulo
				cnpjFormatado = zeros.concat(cnpjFormatado);

				cnpjFormatado = cnpjFormatado.substring(0, 2) + "."
				+ cnpjFormatado.substring(2, 5) + "."
				+ cnpjFormatado.substring(5, 8) + "/"
				+ cnpjFormatado.substring(8, 12) + "-"
				+ cnpjFormatado.substring(12, 14);
			}

			parametros.put("cnpj", cnpjFormatado);

		} else {
			parametros.put("cnpj", "");
		}

		// Inscri��o Estadual da Companhia de �gua
		if (sistemaParametro.getInscricaoEstadual() != null) {
			parametros.put("inscricaoEstadual", sistemaParametro.getInscricaoEstadual().toString());
		} else {
			parametros.put("inscricaoEstadual", "");
		}

		String[] ids = (String[]) getParametro("ids");
		Collection<GuiaPagamentoRelatorioHelper> dadosRelatorio = fachada.pesquisarGuiaPagamentoRelatorio(ids);
		relatorioParcelamentosBeans.set(0, this.inicializarBeanRelatorio(dadosRelatorio, relatorioParcelamentosBeans.get(0)));
		
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		String idUsuario = "";
		Usuario usuario = this.getUsuario();
		
		if (usuario != null) {
			idUsuario = usuario.getId().toString();
		} else {
			idUsuario = "INTERNET";
		}
		
		String nomeUsuario = "";
		if ( usuario != null ) {
			nomeUsuario = usuario.getNomeUsuario();
		}
		
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
		
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("idUsuario", idUsuario);
		parametros.put("nomeUsuario", nomeUsuario);
		Integer anoAtual = Util.getAno(new Date());
		parametros.put("anoGuiaPagamento", ""+anoAtual);
		parametros.put("indicadorExibirMsgNaoReceberPagamento", sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento().toString());
		
		List listArrayJRDetail = new ArrayList();
		RelatorioDataSource arrayJRDetailSub = new RelatorioDataSource(listArrayJRDetail);
		parametros.put("arrayJRDetailSub", arrayJRDetailSub);
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioParcelamentosBeans);
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_DOCUMENTOS_PARCELAMENTO_LOJA_VIRTUAL, parametros, ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_DOCUMENTOS_PARCELAMENTO_LOJA_VIRTUAL, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		return retorno;
	}

	private RelatorioDocumentosParcelamentoPortalCaemaBean inicializarBeanRelatorio(Collection<GuiaPagamentoRelatorioHelper> dadosRelatorio,
			RelatorioDocumentosParcelamentoPortalCaemaBean relatorio) {

		Collection<RelatorioEmitirGuiaPagamentoDetailBean> colecaoDetail = new ArrayList<RelatorioEmitirGuiaPagamentoDetailBean>();
		Iterator<GuiaPagamentoRelatorioHelper> iterator = dadosRelatorio.iterator();
		colecaoDetail.clear();

		while (iterator.hasNext()) {

			GuiaPagamentoRelatorioHelper guiaPagamentoRelatorioHelper = iterator.next();

			//nov parte de guia pagamento item
			//Fl�vio Leonardo
			//07/11/2008
			String descricaoServicosTarifas = "";
			String valor = "";
			RelatorioEmitirGuiaPagamentoDetailBean relatorioEmitirGuiaPagamentoDetailBean = null;

			FiltroGuiaPagamentoItem filtroGuiaPagamentoItem = new FiltroGuiaPagamentoItem();
			filtroGuiaPagamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoItem.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItem.GUIA_PAGAMENTO_GERAL_ID, 
					guiaPagamentoRelatorioHelper.getIdGuiaPagamento()));

			filtroGuiaPagamentoItem.setCampoOrderBy(new String[]{"guiaPagamentoGeral","debitoTipo"});

			Collection<GuiaPagamentoItem> colecaoGuiaPagamentoItem = Fachada.getInstancia().pesquisar(filtroGuiaPagamentoItem, GuiaPagamentoItem.class.getName());
			Iterator<GuiaPagamentoItem> iteratorGuiaPagamentoitem = colecaoGuiaPagamentoItem.iterator();

			if(!colecaoGuiaPagamentoItem.isEmpty()){
				while(iteratorGuiaPagamentoitem.hasNext()){
					GuiaPagamentoItem guiaPagamentoItem = (GuiaPagamentoItem)iteratorGuiaPagamentoitem.next();
					descricaoServicosTarifas = guiaPagamentoItem
					.getDebitoTipo().getDescricao() + "     " +  guiaPagamentoRelatorioHelper.getPrestacaoFormatada();

					valor = Util.formatarMoedaReal(guiaPagamentoItem
							.getValorDebito());

					relatorioEmitirGuiaPagamentoDetailBean = new RelatorioEmitirGuiaPagamentoDetailBean(
							descricaoServicosTarifas, valor);

					colecaoDetail.add(relatorioEmitirGuiaPagamentoDetailBean);
				}
			}else{
				descricaoServicosTarifas = guiaPagamentoRelatorioHelper
				.getDescTipoDebito() + "     " +  guiaPagamentoRelatorioHelper.getPrestacaoFormatada();
				valor = Util.formatarMoedaReal(guiaPagamentoRelatorioHelper.getValorDebito());
				relatorioEmitirGuiaPagamentoDetailBean = new RelatorioEmitirGuiaPagamentoDetailBean(descricaoServicosTarifas, valor);
				colecaoDetail.add(relatorioEmitirGuiaPagamentoDetailBean);
			}

			String valorTotal = Util.formatarMoedaReal(guiaPagamentoRelatorioHelper.getValorDebito());

			String matricula = guiaPagamentoRelatorioHelper.getMatriculaFormatada();
			String nomeCliente = "";
			if (guiaPagamentoRelatorioHelper.getNomeCliente() != null){
				nomeCliente = guiaPagamentoRelatorioHelper.getNomeCliente();
			}
			String dataVencimento = Util.formatarData(guiaPagamentoRelatorioHelper.getDataVencimento());
			String inscricao = guiaPagamentoRelatorioHelper.getInscricao();
			String enderecoImovel = guiaPagamentoRelatorioHelper.getEnderecoImovel();
			String enderecoClienteResponsavel = guiaPagamentoRelatorioHelper.getEnderecoClienteResponsavel();
			String representacaoNumericaCodBarraFormatada = guiaPagamentoRelatorioHelper.getRepresentacaoNumericaCodBarraFormatada();
			String representacaoNumericaCodBarraSemDigito = guiaPagamentoRelatorioHelper.getRepresentacaoNumericaCodBarraSemDigito();
			String dataValidade = guiaPagamentoRelatorioHelper.getDataValidade();
			String idGuiaPagamento = guiaPagamentoRelatorioHelper.getIdGuiaPagamento();

			String observacao = "";

			Short indicadorEmitirObservacao = guiaPagamentoRelatorioHelper.getIndicadorEmitirObservacao();
			if (indicadorEmitirObservacao != null && indicadorEmitirObservacao.equals(ConstantesSistema.SIM)) {
				observacao = guiaPagamentoRelatorioHelper.getObservacao();
			}

			String cpfCnpjCliente = "";

			if (Util.verificarIdNaoVazio(guiaPagamentoRelatorioHelper.getCpfCliente())){
				cpfCnpjCliente = Util.formatarCpf(guiaPagamentoRelatorioHelper.getCpfCliente());
			} else if (Util.verificarNaoVazio(guiaPagamentoRelatorioHelper.getCnpjCliente())) {
				cpfCnpjCliente = Util.formatarCnpj(guiaPagamentoRelatorioHelper.getCnpjCliente());
			}

			String idImovel = "";
			if (guiaPagamentoRelatorioHelper.getIdImovel() != null && !guiaPagamentoRelatorioHelper.getIdImovel().equals("")) {
				idImovel = guiaPagamentoRelatorioHelper.getIdImovel().toString();
			} else if (guiaPagamentoRelatorioHelper.getIdCliente() != null) {
				idImovel = guiaPagamentoRelatorioHelper.getIdCliente().toString();
			}

			String nossoNumero = guiaPagamentoRelatorioHelper.getNossoNumero();
			String sacadoParte01 = guiaPagamentoRelatorioHelper.getSacadoParte01();
			String sacadoParte02 = guiaPagamentoRelatorioHelper.getSacadoParte02();

			String subRelatorio = guiaPagamentoRelatorioHelper.getSubRelatorio();

			relatorio.inserirDadosRelatorioEmitirGuia(
					colecaoDetail, matricula, nomeCliente, dataVencimento,
					inscricao, enderecoImovel, enderecoClienteResponsavel,
					representacaoNumericaCodBarraFormatada,
					representacaoNumericaCodBarraSemDigito, dataValidade,
					valorTotal, idGuiaPagamento, observacao, cpfCnpjCliente, idImovel,
					nossoNumero, sacadoParte01, sacadoParte02, subRelatorio, colecaoDetail);

			colecaoDetail.clear();
		}
		return relatorio;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioParcelamento", this);
	}
}