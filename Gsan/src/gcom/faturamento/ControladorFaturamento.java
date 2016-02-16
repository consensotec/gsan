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
package gcom.faturamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.atendimentopublico.FiscalizarParametroCalculoDebito;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteConta;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelDoacao;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelDoacao;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.RepositorioImovelHBM;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelCobrarDoacaoHelper;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.ParcelamentoGrupo;
import gcom.cobranca.bean.CalcularAcrescimoPorImpontualidadeHelper;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.fachada.Fachada;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.bean.ApagarDadosFaturamentoHelper;
import gcom.faturamento.bean.ArquivoExportacaoFaturasAgrupadasRegistro00Helper;
import gcom.faturamento.bean.ArquivoExportacaoFaturasAgrupadasRegistro01Helper;
import gcom.faturamento.bean.ArquivoExportacaoFaturasAgrupadasRegistro02Helper;
import gcom.faturamento.bean.ArquivoExportacaoFaturasAgrupadasRegistro03Helper;
import gcom.faturamento.bean.ArquivoExportacaoFaturasAgrupadasRegistro04Helper;
import gcom.faturamento.bean.ArquivoExportacaoFaturasAgrupadasRegistro09Helper;
import gcom.faturamento.bean.AtualizarContaPreFaturadaHelper;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.bean.DebitoCobradoAgrupadoHelper;
import gcom.faturamento.bean.DeclaracaoQuitacaoAnualDebitosHelper;
import gcom.faturamento.bean.DeclaracaoQuitacaoAnualDebitosItemHelper;
import gcom.faturamento.bean.DeterminarValoresFaturamentoAguaEsgotoHelper;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.bean.GerarCreditoRealizadoHelper;
import gcom.faturamento.bean.GerarDebitoCobradoHelper;
import gcom.faturamento.bean.GerarRelatorioAnormalidadePorAmostragemHelper;
import gcom.faturamento.bean.GerarResumoSimulacaoFaturamentoHelper;
import gcom.faturamento.bean.PrescreverDebitosImovelHelper;
import gcom.faturamento.bean.RemoverImovesJaProcessadorImpressaoSimultaneaHelper;
import gcom.faturamento.bean.RemoverImovesJaProcessadorImpressaoSimultaneaHelper.DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea;
import gcom.faturamento.bean.RetificarConjuntoContaConsumosHelper;
import gcom.faturamento.bean.RetornoAtualizarFaturamentoMovimentoCelularHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaCategoria;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.ContaComunicado;
import gcom.faturamento.conta.ContaComunicadoFaturamentoGrupo;
import gcom.faturamento.conta.ContaComunicadoHelper;
import gcom.faturamento.conta.ContaComunicadoQuadra;
import gcom.faturamento.conta.ContaComunicadoRota;
import gcom.faturamento.conta.ContaComunicadoSetor;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaImpostosDeduzidos;
import gcom.faturamento.conta.ContaImpressao;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.ContaTipo;
import gcom.faturamento.conta.FaturaItem;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaCategoria;
import gcom.faturamento.conta.FiltroContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.FiltroContaComunicado;
import gcom.faturamento.conta.FiltroContaComunicadoFaturamentoGrupo;
import gcom.faturamento.conta.FiltroContaComunicadoQuadra;
import gcom.faturamento.conta.FiltroContaComunicadoRota;
import gcom.faturamento.conta.FiltroContaComunicadoSetor;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.faturamento.conta.FiltroContaImpostosDeduzidos;
import gcom.faturamento.conta.FiltroContaImpressao;
import gcom.faturamento.conta.GerarImpostosDeduzidosContaHelper;
import gcom.faturamento.conta.UC0146ManterConta;
import gcom.faturamento.contratodemanda.ContratoDemandaFaixaConsumo;
import gcom.faturamento.contratodemanda.ContratoDemandaImovel;
import gcom.faturamento.contratodemanda.ContratoDemandaMotivoEncerramento;
import gcom.faturamento.contratodemanda.ContratoDemandaSituacao;
import gcom.faturamento.contratodemanda.FiltroContratoDemandaSituacao;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoARealizarCategoria;
import gcom.faturamento.credito.CreditoARealizarCategoriaPK;
import gcom.faturamento.credito.CreditoARealizarGeral;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoRealizadoHistorico;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoARealizar;
import gcom.faturamento.credito.FiltroCreditoARealizarCategoria;
import gcom.faturamento.credito.FiltroCreditoARealizarGeral;
import gcom.faturamento.credito.FiltroCreditoRealizado;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCobradoHistorico;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.faturamento.debito.DemonstrativoFormulaCalculoHelper;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.faturamento.debito.FiltroDebitoACobrarHistorico;
import gcom.faturamento.debito.FiltroDebitoCobrado;
import gcom.faturamento.debito.FiltroDebitoCobradoHistorico;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.FaturamentoImediatoAjusteHelper;
import gcom.gui.faturamento.bean.Emitir2ViaDeclaracaoAnualQuitacaoDebitosHelper;
import gcom.gui.faturamento.contratodemanda.ContratoDemandaCondominiosResidenciaisHelper;
import gcom.gui.faturamento.contratodemanda.FiltrarContratoDemandaCondominiosResidenciaisHelper;
import gcom.gui.micromedicao.DadosMovimentacao;
import gcom.gui.portal.caema.ConsultarEstruturaTarifariaPortalCaemaHelper;
import gcom.gui.portal.caer.ConsultarEstruturaTarifariaPortalCaerHelper;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.FiltroMovimentoRoteiroEmpresa;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.MovimentoRoteiroEmpresa;
import gcom.micromedicao.ReleituraMobile;
import gcom.micromedicao.RepositorioMicromedicaoHBM;
import gcom.micromedicao.Rota;
import gcom.micromedicao.RotaAtualizacaoSeq;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.FiltroMedicaoTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.faturamento.FiltrarRelatorioContasNaoImpressasHelper;
import gcom.relatorio.faturamento.FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper;
import gcom.relatorio.faturamento.FiltrarRelatorioJurosMultasDebitosCanceladosHelper;
import gcom.relatorio.faturamento.RelatorioContasNaoImpressasHelper;
import gcom.relatorio.faturamento.RelatorioDevolucaoPagamentosDuplicidadeHelper;
import gcom.relatorio.faturamento.RelatorioErrosMovimentosContaPreFaturadas;
import gcom.relatorio.faturamento.RelatorioErrosMovimentosContaPreFaturadasBean;
import gcom.relatorio.faturamento.RelatorioFaturasAgrupadasBean;
import gcom.relatorio.faturamento.RelatorioJurosMultasDebitosCanceladosHelper;
import gcom.relatorio.faturamento.RelatorioMultasAutosInfracaoPendentesBean;
import gcom.relatorio.faturamento.RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea;
import gcom.relatorio.faturamento.RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean;
import gcom.relatorio.faturamento.conta.RelatorioContasCanceladasRetificadasHelper;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.seguranca.parametrosistema.FiltroParametroSistema;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesAplicacao;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.IoUtil;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;
import gcom.util.serializacao.SerializadorPipe;
import gcom.util.serializacao.TarefaSerializar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.ejb.EJBException;

import org.hibernate.LazyInitializationException;

public class ControladorFaturamento extends ControladorFaturamentoFINAL {

	private static final long serialVersionUID = 1L;

	/**
	 * [UC0146] Manter Conta
	 * 
	 * [SB0008] Retificar Conjunto Conta
	 * 
	 * [FS0033] Verificar permissão especial para informar apenas volume de
	 * esgoto
	 * 
	 * @author Raphael Rossiter
	 * @date 02/07/2009
	 * 
	 * @param helper
	 * @throws ControladorException
	 */
	public void retificarConjuntoContaConsumos(
			Integer idFuncionalidadeIniciada, Map parametros)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			/*
			 * Registrar o início do processamento da Unidade de Processamento
			 * do Batch
			 */
			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.FUNCIONALIDADE, 0);

			System.out.println("***************************************");
			System.out.println("RETIFICAR CONJUNTO CONTA CONSUMOS");
			System.out.println("***************************************");

			RetificarConjuntoContaConsumosHelper helper = (RetificarConjuntoContaConsumosHelper) parametros
					.get("helper");

			// [FS0033] Verificar permissão especial para informar apenas volume
			// de esgoto
			boolean usuarioPodeRetificarContasApenasVolumeEsgoto = this
					.getControladorPermissaoEspecial()
					.verificarPermissaoEspecial(
							PermissaoEspecial.RETIFICAR_CONTA_APENAS_VOLUME_ESGOTO,
							helper.getUsuarioLogado());

			if (helper.getConsumoAgua().intValue() == 0
					&& helper.getVolumeEsgoto().intValue() > 0
					&& !usuarioPodeRetificarContasApenasVolumeEsgoto) {

				throw new ControladorException(
						"atencao.necessario_permissao_especial_para_retificar_apenas_volume_esgoto");
			}

			// PARÂMETROS DO SISTEMA
			SistemaParametro sistemaParametro = this.getControladorUtil()
					.pesquisarParametrosDoSistema();

			Collection colecaoContasManutencao = new ArrayList();
			List colecaoAuxiliar = new ArrayList();

			colecaoAuxiliar.addAll(helper.getColecaoImovel());

			int i = 0;
			int cont = 500;

			Collection colecao = new ArrayList();
			while (i <= helper.getColecaoImovel().size()) {

				// PAGINAÇÃO
				if (helper.getColecaoImovel().size() - i >= cont) {
					colecao = colecaoAuxiliar.subList(i, i + cont);
				} else {
					colecao = colecaoAuxiliar.subList(i, helper
							.getColecaoImovel().size());
				}

				i = i + cont;

				try {

					colecaoContasManutencao = repositorioFaturamento
							.obterContasImoveis(helper.getAnoMes(), colecao,
									helper.getDataVencimentoContaInicio(),
									helper.getDataVencimentoContaFim(), helper
											.getAnoMesFim(), helper
											.getIndicadorContaPaga());

					/**
					 * [UC0407] Filtrar Imóveis para Inserir ou Manter Conta 3.
					 * Caso o indicador de bloqueio de contas vinculadas a
					 * contrato de parcelamento no manter contas esteja ativo
					 * retirar da lista de contas selecionadas as contas
					 * vinculadas a algum contrato de parcelamento ativo
					 * 
					 * RM 1887 - Contrato Parcelamento por Cliente Adicionado
					 * por: Mariana Victor Data: 15/07/2011
					 * 
					 */
					if (sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta() != null
							&& sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta().equals(ConstantesSistema.SIM)) {
						colecaoContasManutencao = this.obterColecaoSemContasEmContratoParcelamentoRetificarConjuntoContas(
								colecaoContasManutencao);
					}
					/**
					 * FIM DA ALTERAÇÂO
					 */
					
					if (colecaoContasManutencao != null
							&& !colecaoContasManutencao.isEmpty()) {

						Iterator colecaoContasManutencaoIterator = colecaoContasManutencao
								.iterator();
						
						while (colecaoContasManutencaoIterator.hasNext()) {

							Object[] contaArray = (Object[]) colecaoContasManutencaoIterator
									.next();

							Conta conta = (Conta) contaArray[0];

							conta.setUltimaAlteracao(new Date());

							Imovel imovel = (Imovel) contaArray[1];
							
							Date dataVencimentoConta;
							if(helper.getDataVencimentoContaRetificacao() == null || helper.getDataVencimentoContaRetificacao().equals("")){
								dataVencimentoConta = conta.getDataVencimentoOriginal();
							}else{
								dataVencimentoConta = helper.getDataVencimentoContaRetificacao();
							}

							Collection colecaoCategoriaOUSubcategoria = null;

							if (sistemaParametro
									.getIndicadorTarifaCategoria()
									.equals(
											SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)) {

								if (helper.getIndicadorCategoriaEconomiaConta()
										.equals(ConstantesSistema.SIM)) {
									// [UC0108] - Quantidade de economias por
									// categoria
									colecaoCategoriaOUSubcategoria = this
											.getControladorImovel()
											.obterQuantidadeEconomiasContaCategoriaPorSubcategoria(
													conta);
								} else {
									colecaoCategoriaOUSubcategoria = this
											.getControladorImovel()
											.obterQuantidadeEconomiasSubCategoria(
													conta.getImovel().getId());
								}
							} else {

								if (helper.getIndicadorCategoriaEconomiaConta()
										.equals(ConstantesSistema.SIM)) {
									// [UC0108] - Quantidade de economias por
									// categoria
									colecaoCategoriaOUSubcategoria = this
											.getControladorImovel()
											.obterQuantidadeEconomiasContaCategoria(
													conta);
								} else {
									colecaoCategoriaOUSubcategoria = this
											.getControladorImovel()
											.obterQuantidadeEconomiasCategoria(
													conta.getImovel());
								}
							}

							// CRÉDITOS
							Collection colecaoCreditoRealizado = obterCreditosRealizadosConta(conta);

							// DÉBITOS
							Collection colecaoDebitoCobrado = obterDebitosCobradosConta(conta);

							/*
							 * Caso na conta a ser retificada o consumo de água
							 * seja igual ao volume de esgoto da conta os dois
							 * campos devem ser alterados (mesmo que o volume
							 * não tenha sido informado).
							 * 
							 * Caso contrário na conta a ser retificada o
							 * consumo de água seja diferente do volume de
							 * esgoto da conta só deve ser alterado o consumo de
							 * água.
							 */
							Integer volumeEsgoto = helper.getVolumeEsgoto();

							if (conta.getConsumoAgua() != null
									&& conta.getConsumoEsgoto() != null
									&& conta.getConsumoAgua().equals(
											conta.getConsumoEsgoto())
									&& helper.getVolumeEsgoto().intValue() == 0) {

								volumeEsgoto = conta.getConsumoAgua();
							}

							// [UC0120] - Calcular Valores de Água e/ou Esgoto
							Collection<CalcularValoresAguaEsgotoHelper> valoresConta = calcularValoresConta(
									Util.formatarAnoMesParaMesAno(conta
											.getReferencia()),
									imovel.getId().toString(),
									helper.getLigacaoAguaSituacao() != null ? helper
											.getLigacaoAguaSituacao().getId()
											: conta.getLigacaoAguaSituacao()
													.getId(),
									helper.getLigacaoEsgotoSituacao() != null ? helper
											.getLigacaoEsgotoSituacao().getId()
											: conta.getLigacaoEsgotoSituacao()
													.getId(),
									colecaoCategoriaOUSubcategoria, helper
											.getConsumoAgua().toString(),
									volumeEsgoto.toString(), conta
											.getPercentualEsgoto().toString(),
									conta.getConsumoTarifa().getId(), helper
											.getUsuarioLogado());
							
							/*
							 * RM 6873
							 * 
							 * OBJ: Não permitir que a conta retificada fique com valor negativo.
							 * 
							 * NEGÓCIO: Verifica se o valor da conta retificada será MAIOR ou IGUAL a zero, caso o retorno seja positivo,
							 * continuar com a retificação da conta, caso contrário, passar para a próxima conta.
							 * 
							 * Colocado por Raphael Rossiter em 12/03/2012
							 */
							boolean podeRetificarConta = this.verificarNaoRetificacaoConta(conta.getId(), valoresConta);
							
							if (podeRetificarConta){
								
								//[UC0150] - Retificar Conta
								retificarConta(
										new Integer(conta.getReferencia()),
										conta,
										imovel,
										colecaoDebitoCobrado,
										colecaoCreditoRealizado,
										helper.getLigacaoAguaSituacao() != null ? helper
												.getLigacaoAguaSituacao()
												: conta.getLigacaoAguaSituacao(),
										helper.getLigacaoEsgotoSituacao() != null ? helper
												.getLigacaoEsgotoSituacao()
												: conta.getLigacaoEsgotoSituacao(),
										colecaoCategoriaOUSubcategoria, helper
												.getConsumoAgua().toString(),
										volumeEsgoto.toString(), conta
												.getPercentualEsgoto().toString(),
												dataVencimentoConta,
										valoresConta, helper
												.getContaMotivoRetificacao(), null,
										helper.getUsuarioLogado(), conta
												.getConsumoTarifa().getId()
												.toString(), false, null, null,
										false, null, null, null, null, null, true);
							}
						}
					}

				} catch (ErroRepositorioException ex) {
					sessionContext.setRollbackOnly();
					new ControladorException("erro.sistema", ex);
				}
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			System.out
					.println("******* FIM RETIFICAR CONJUNTO CONTA CONSUMOS **********");
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);
			throw new EJBException(ex);
		}

	}

	/**
	 * Método responsável por verificar se existe no banco um determinado ID na
	 * tabela de faturamento_grupo - caso exista o id passado como parâmetro na
	 * tabela, retorna true, caso contrário retorna false
	 * 
	 * @param Integer
	 *            id - id de um FaturamentoGrupo
	 * @return boolean - true para existir o id na tabela, false para não
	 *         existir
	 * @exception ErroRepositorioException
	 */
	public boolean verificarExistenciaIdGrupoFaturamento(Integer id)
			throws ControladorException {
		try {
			return repositorioFaturamento
					.verificarExistenciaIdGrupoFaturamento(id);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0184] - Manter débito a Cobrar
	 * 
	 * @author Hugo Amorim
	 * @date 17/07/2009
	 * 
	 */
	public boolean verificarAutosAssociadosAoDebito(String[] idsDebitosACobrar)
			throws ControladorException {
		try {

			return repositorioFaturamento
					.verificarAutosAssociadosAoDebito(idsDebitosACobrar);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0184] - Manter débito a Cobrar
	 * 
	 * @author Hugo Amorim
	 * @date 17/07/2009
	 * 
	 */
	public void cancelarAutosInfracao(String[] idsDebitosACobrar)
			throws ControladorException {
		try {

			repositorioFaturamento.cancelarAutosInfracao(idsDebitosACobrar);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0896] - Manter Autos de Infração
	 * 
	 * @author Hugo Amorim
	 * @date 17/07/2009
	 * 
	 */
	public boolean validarExistenciaDebitoAtivosAutoInfracao(
			Integer idAutoInfracao) throws ControladorException {
		try {

			return repositorioFaturamento
					.validarExistenciaDebitoAtivosAutoInfracao(idAutoInfracao);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0896] - Manter Autos de Infração
	 * 
	 * @author Hugo Amorim
	 * @date 17/07/2009
	 * 
	 */
	public boolean validarExistenciaDeDebitosAutoInfracao(Integer idAutoInfracao)
			throws ControladorException {
		try {

			return repositorioFaturamento
					.validarExistenciaDeDebitosAutoInfracao(idAutoInfracao);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author bruno
	 * @date 15/06/2009
	 * 
	 * @param colHelper
	 * @param atualizaSituacaoAtualConta -
	 *            Caso seja chamado via a funcionalidade de ISC, não atualiza a
	 *            situação atual da conta que não foi impressa. Caso seja
	 *            chamado via a funcionalidade de consistir, atualiza a situação
	 *            atual da conta.
	 */
	private void atualizarMovimentoCelular( Collection<MovimentoContaPrefaturada> colMovimentoContaPrefaturada , boolean efetuarRateio)
        throws ControladorException{
		
		String matriculaImovel = "";
        try{
			// Obter os dados da tabela movimento conta pré-faturada e suas tabelas dependentes
	        SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
	        
	        for ( MovimentoContaPrefaturada helper : colMovimentoContaPrefaturada ){
	        	
	        	if ( helper.getMovimentoContaPrefaturadaCategorias() != null && 
	        		 helper.getMovimentoContaPrefaturadaCategorias().size() > 0 ){        	
	        		
	        		// Caso o imovel seja o imovel condominio, pulamos
	        		if ( helper.getImovel().getIndicadorImovelCondominio().equals( ConstantesSistema.SIM ) ){
	        			continue;
	        		}
	        		
					// recupera a conta
		            Conta contaAtualizacao = helper.getConta();
		            
		            matriculaImovel = ""+helper.getImovel().getId();
	
		            try {
		            	contaAtualizacao.getConsumoAgua();
					} catch (LazyInitializationException e) {
			            try {
			            	contaAtualizacao = repositorioFaturamento.pesquisarContaPreFaturada(helper.getImovel().getId(),
			            			helper.getAnoMesReferenciaPreFaturamento(), DebitoCreditoSituacao.PRE_FATURADA);
						} catch (ErroRepositorioException ex) {
							throw new ControladorException("erro.sistema", ex);
						}			
		            }
		            
		            LigacaoTipo lt = new LigacaoTipo();
					lt.setId(LigacaoTipo.LIGACAO_AGUA);

					// Pesquisamos o consumo historico do imóvel selecionado
					ConsumoHistorico consumoHistorico = null;
					if(contaAtualizacao != null){
						consumoHistorico = this.getControladorMicromedicao().obterConsumoHistorico(
								contaAtualizacao.getImovel(), lt, contaAtualizacao.getReferencia());
					}

					if (consumoHistorico != null) {
						// 2.1
						if (  (contaAtualizacao.getLigacaoAguaSituacao() != null 
							   && !contaAtualizacao.getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.CORTADO))
						   || (consumoHistorico.getConsumoTipo() != null
							   && consumoHistorico.getConsumoTipo().getId().equals(ConsumoTipo.REAL)
							   && consumoHistorico.getNumeroConsumoFaturadoMes() != null 
							   && consumoHistorico.getNumeroConsumoFaturadoMes() > 0)) {
							
							// Verificamos se o imóvel possui o débito
							BigDecimal valorDebitosConta = repositorioFaturamento.pesquisarValorDebitoConta(contaAtualizacao.getId());

							if (valorDebitosConta != null) {
								contaAtualizacao.setDebitos(valorDebitosConta);
							}
						}
					}
	        		
		            // Para cada imóvel da tabela movimento conta pré-faturada,
		            // realizar os seguintes procedimentos:
		            // O sistema calcula os valores de faturamento para o imóvel
		            // obtendo os valores faturados e de consumo de água e esgoto;
		            // [SB0001 - Determinar Valores para Faturamento de Água e/ou Esgoto].
		            
		            FiltroImovel filtro = new FiltroImovel();
		            filtro.adicionarCaminhoParaCarregamentoEntidade( "quadra.rota.faturamentoGrupo" );
		            filtro.adicionarCaminhoParaCarregamentoEntidade( "ligacaoAguaSituacao" );
		            filtro.adicionarCaminhoParaCarregamentoEntidade( "ligacaoEsgotoSituacao" );
		            filtro.adicionarParametro( new ParametroSimples( FiltroImovel.ID, helper.getImovel()  ) );
		            Collection<Imovel> colImo = Fachada.getInstancia().pesquisar( filtro , Imovel.class.getName() );
		            Imovel imo = ( Imovel ) Util.retonarObjetoDeColecao( colImo );
		            
		            FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();
		            filtroConsumoHistorico.adicionarParametro( new ParametroSimples( FiltroConsumoHistorico.IMOVEL_ID, helper.getImovel().getId() )  );
		            filtroConsumoHistorico.adicionarParametro( new ParametroSimples( FiltroConsumoHistorico.LIGACAO_TIPO_ID, LigacaoTipo.LIGACAO_AGUA  ) );
		            filtroConsumoHistorico.adicionarParametro( new ParametroSimples( FiltroConsumoHistorico.ANO_MES_FATURAMENTO, helper.getFaturamentoGrupo().getAnoMesReferencia() ) );
		            Collection<ConsumoHistorico> colConsumoHistorico = Fachada.getInstancia().pesquisar( filtroConsumoHistorico, ConsumoHistorico.class.getName() );
		            ConsumoHistorico consumoHistoricoAgua = ( ConsumoHistorico ) Util.retonarObjetoDeColecao( colConsumoHistorico );
		            
		            // Consumo de Água
		            // Integer consumoRateioAguaCalculado = consumoHistoricoAgua.getConsumoRateio();
		
		            filtroConsumoHistorico.limparListaParametros();
		            filtroConsumoHistorico.adicionarParametro( new ParametroSimples( FiltroConsumoHistorico.IMOVEL_ID, helper.getImovel().getId()  ) );
		            filtroConsumoHistorico.adicionarParametro( new ParametroSimples( FiltroConsumoHistorico.LIGACAO_TIPO_ID, LigacaoTipo.LIGACAO_ESGOTO  ) );
		            filtroConsumoHistorico.adicionarParametro( new ParametroSimples( FiltroConsumoHistorico.ANO_MES_FATURAMENTO, helper.getFaturamentoGrupo().getAnoMesReferencia() ) );
		            colConsumoHistorico = Fachada.getInstancia().pesquisar( filtroConsumoHistorico, ConsumoHistorico.class.getName() );
		            ConsumoHistorico consumoHistoricoEsgoto = ( ConsumoHistorico ) Util.retonarObjetoDeColecao( colConsumoHistorico );
		            
		            // Consumo de Água
		           // Integer consumoRateioEsgotoCalculado = consumoHistoricoEsgoto.getConsumoRateio();
		            
		            // 1.6 Caso o indicador de tarifa categoria seja igual a 2
		            // ( PARM_ICTARIFACATEGORIA = 2 da tabela SISTEMA_PARAMETROS)
		            // o sistema passa as subcategorias e as repectivas quantidades
		            // de economias do imóvel ( SCAT_ID e IMSB_QTECONOMIA da tabela
		            // IMOVEL_SUBCATEGORIA com IMOV_ID da tabela IMOVEL )
		            // Caso contrátio, categoria(s) do imovel de sua(s) respectiva(s)
		            // quantidade(s) de economia retornada pelo
		            // [UC0108 - Obter Quantidade de Economias por Categoria]
					Collection colecaoCategoriaOUSubcategoria = null;

					// Verificando se a empresa fatura por CATEGORIA ou SUBCATEGORIA
					if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA)) {
						if (contaAtualizacao != null) {
							// PESQUISANDO A PARTIR DA TABELA CONTA_CATEGORIA
							colecaoCategoriaOUSubcategoria = this.getControladorImovel()
								.obterQuantidadeEconomiasContaCategoria(contaAtualizacao);
						} else {
							// PESQUISANDO A PARTIR DA TABELA IMOVEL_SUBCATEGORIA
							colecaoCategoriaOUSubcategoria = this.getControladorImovel()
								.obterQuantidadeEconomiasCategoria(imo);
						}
					} else {
						// [UC0108] - Obter Quantidade de Economias por Subcategoria
						if (contaAtualizacao != null) {
							// PESQUISANDO A PARTIR DA TABELA CONTA_CATEGORIA
							colecaoCategoriaOUSubcategoria = this.getControladorImovel()
								.obterQuantidadeEconomiasContaCategoriaPorSubcategoria(contaAtualizacao.getId());
						} else {

							// PESQUISANDO A PARTIR DA TABELA IMOVEL_SUBCATEGORIA
							colecaoCategoriaOUSubcategoria = this.getControladorImovel()
									.obterQuantidadeEconomiasSubCategoria(imo.getId());
						}
					}
		            
		            // Inicializando o objeto que armazenará as informações que
					// serão utilizadas no cálculo da conta
		            DeterminarValoresFaturamentoAguaEsgotoHelper helperValoresAguaEsgoto = 
		                this.determinarValoresFaturamentoAguaEsgoto(
		                  imo,
		                  helper.getFaturamentoGrupo().getAnoMesReferencia(),
		                  colecaoCategoriaOUSubcategoria,
		                  imo.getQuadra().getRota().getFaturamentoGrupo(),
		                  consumoHistoricoAgua,
		                  consumoHistoricoEsgoto );
		            
		            // [UC0120] - Calcular Valores de Água e/ou Esgoto
		            Collection<CalcularValoresAguaEsgotoHelper> colecaoCalcularValoresAguaEsgotoHelper = 
		            	helperValoresAguaEsgoto.getColecaoCalcularValoresAguaEsgotoHelper();
		            
		            // Valor total de água
		            BigDecimal valorTotalAguaCalculado = this.calcularValorTotalAguaOuEsgotoPorCategoria(
		            		colecaoCalcularValoresAguaEsgotoHelper,ConstantesSistema.CALCULAR_AGUA);  
		            
		            // Valor total de esgoto
		            BigDecimal valorTotalEsgotoCalculado = this.calcularValorTotalAguaOuEsgotoPorCategoria(
		            		colecaoCalcularValoresAguaEsgotoHelper,ConstantesSistema.CALCULAR_ESGOTO);
		            
		            // Consumo de Agua
		            Integer consumoAguaCalculado = null;
	
	            	consumoAguaCalculado = this.calcularConsumoTotalAguaOuEsgotoPorCategoria(
	                        colecaoCalcularValoresAguaEsgotoHelper, ConstantesSistema.CALCULAR_AGUA); 
		            
		            // Consumo de Esgoto
		            Integer consumoEsgotoCalculado = this.calcularConsumoTotalAguaOuEsgotoPorCategoria(
	                        colecaoCalcularValoresAguaEsgotoHelper, ConstantesSistema.CALCULAR_ESGOTO);
	
		            Collection<MovimentoContaPrefaturadaCategoria> colMovimentoContaPrefaturadaCategoria = 
		            		helper.getMovimentoContaPrefaturadaCategorias();
		            
		            BigDecimal valorAgua = new BigDecimal( 0 );
		            BigDecimal valorEsgoto = new BigDecimal( 0 );
		            
		            Integer consumoAgua = 0;
		            Integer consumoEsgoto = 0;
		            Integer consumoRateioAgua = helper.getConsumoRateioAgua();
		            Integer consumoRateioEsgoto = helper.getConsumoRateioEsgoto();
		            BigDecimal valorImposto = new BigDecimal( 0 );
		            
		            for ( MovimentoContaPrefaturadaCategoria helperCategoria : colMovimentoContaPrefaturadaCategoria ){
		                // Somamos os valores
		                valorAgua = valorAgua.add( helperCategoria.getValorFaturadoAgua() );
		                valorEsgoto = valorEsgoto.add( helperCategoria.getValorFaturadoEsgoto() );
		                consumoAgua += helperCategoria.getConsumoFaturadoAgua();
		                consumoEsgoto += helperCategoria.getConsumoFaturadoEsgoto();
		            }
		            
		            BigDecimal diferencaValorAgua = valorAgua.subtract( valorTotalAguaCalculado );
		            BigDecimal diferencaValorEsgoto = valorEsgoto.subtract( valorTotalEsgotoCalculado );
		            
		            if (efetuarRateio && 
		            	( ( diferencaValorAgua.doubleValue() > .01d || diferencaValorAgua.doubleValue() < -.01d ) ||
		            	 ( diferencaValorEsgoto.doubleValue() > .01d || diferencaValorEsgoto.doubleValue() < -.01d ) ||
		                 consumoAguaCalculado.intValue() != consumoAgua.intValue() ||
		                 consumoEsgotoCalculado.intValue() != consumoEsgoto.intValue() )){
		                
		            	FaturamentoImediatoAjuste faturamentoImediatoAjuste = new FaturamentoImediatoAjuste();
		                faturamentoImediatoAjuste.setConta( contaAtualizacao );
		                faturamentoImediatoAjuste.setNumeroConsumoAgua( consumoAgua - consumoAguaCalculado );
		                faturamentoImediatoAjuste.setNumeroConsumoEsgoto( consumoEsgoto - consumoEsgotoCalculado );
		                faturamentoImediatoAjuste.setValorCobradoAgua( valorAgua.subtract( valorTotalAguaCalculado ) );
		                faturamentoImediatoAjuste.setValorCobradoEsgoto( valorEsgoto.subtract( valorTotalEsgotoCalculado ) );
		                faturamentoImediatoAjuste.setUltimaAlteracao( new Date() );
		                // Inserimos os dados
		                this.getControladorBatch().inserirObjetoParaBatch( faturamentoImediatoAjuste );
		                
		                // Atualizando o consumo de acordo com o movimento do celular
		                this.atualizarConsumoMovimentoCelular(contaAtualizacao, consumoAgua, consumoAguaCalculado, 
		                		consumoEsgoto, consumoEsgotoCalculado);
		            }
	
		            if(contaAtualizacao != null){
		            	
		            	helper.setConta(contaAtualizacao);
			            /*
						 * 2. Para cada registro do tipo 2, alterar na tabela CONTA_CATEGORIA o seu correspondente 
						 * (CNTA_ID = Conta do movimento em processamento e CATG_ID = código da categoria do movimento 
						 * e SCAT_ID = código da subcategoria do movimento) , com os seguintes dados
						 */
			            for ( MovimentoContaPrefaturadaCategoria helperCategoria : colMovimentoContaPrefaturadaCategoria ){
			                FiltroContaCategoria filtroContaCategoria = new FiltroContaCategoria();
			                filtroContaCategoria.adicionarParametro(new ParametroSimples(FiltroContaCategoria.CATEGORIA_ID, 
			                		helperCategoria.getComp_id().getCategoria().getId() ) );
			                filtroContaCategoria.adicionarParametro(new ParametroSimples(FiltroContaCategoria.CONTA_ID, 
			                		contaAtualizacao.getId() ) );
			                
			                Integer idSubcategoria = null;
			                if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA)){
			                	idSubcategoria = 0;
			                }else{
			                	idSubcategoria = helperCategoria.getComp_id().getSubcategoria().getId();
			                }
			                filtroContaCategoria.adicionarParametro(new ParametroSimples(FiltroContaCategoria.SUBCATEGORIA_ID, 
			                                idSubcategoria ) );
			                Collection<ContaCategoria> colContaCategoria = this.getControladorUtil().pesquisar( 
			                            filtroContaCategoria, ContaCategoria.class.getName() );
			                
			                ContaCategoria contaCategoria = ( ContaCategoria ) Util.retonarObjetoDeColecao( colContaCategoria );
			                
			                if(contaCategoria != null && !contaCategoria.equals("")){
				                contaCategoria.setValorAgua( helperCategoria.getValorFaturadoAgua() );
				                contaCategoria.setConsumoAgua( helperCategoria.getConsumoFaturadoAgua() );
				                contaCategoria.setValorEsgoto( helperCategoria.getValorFaturadoEsgoto() );
				                contaCategoria.setConsumoEsgoto( helperCategoria.getConsumoFaturadoEsgoto() );
				                contaCategoria.setValorTarifaMinimaAgua( helperCategoria.getValorTarifaMinimaAgua() );
				                contaCategoria.setConsumoMinimoAgua( helperCategoria.getConsumoMinimoAgua() );
				                contaCategoria.setValorTarifaMinimaEsgoto( helperCategoria.getValorTarifaMinimaEsgoto() );
				                contaCategoria.setConsumoMinimoEsgoto( helperCategoria.getConsumoMinimoEsgoto() );
				                contaCategoria.setUltimaAlteracao( new Date() );
				                
				                try{
				                	repositorioFaturamento.atualizarContaCategoriaProcessoMOBILE( contaCategoria );
								} catch (ErroRepositorioException e) {
									throw new ControladorException( "erro.sistema", e );
								}
				                
				                /*
								 * 3. Para cada registro do tipo 3, alterar na tabela CONTA_CATEGORIA_CONSUMO_FAIXA o seu
								 * correspondente (CNTA_ID = Conta do movimento em processamento e CATG_ID = código da
								 * categoria do movimento e SCAT_ID = código da subcategoria do movimento)
								 */
				                FiltroMovimentoContaCategoriaConsumoFaixa filtroMovimentoContaCategoriaConsumoFaixa
				                    = new FiltroMovimentoContaCategoriaConsumoFaixa();
				                
				                filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples( 
				                		FiltroMovimentoContaCategoriaConsumoFaixa.MOVIMENTO_CONTA_PREFATURADA_ID, helper.getId() ) );
				                
				                filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples( 
				                		FiltroMovimentoContaCategoriaConsumoFaixa.CATEGORIA_ID, helperCategoria.getComp_id().getCategoria().getId() ) );
				               
		                        filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples( 
		                        		FiltroMovimentoContaCategoriaConsumoFaixa.SUBCATEGORIA_ID, idSubcategoria ) );
				                
				                Collection<MovimentoContaCategoriaConsumoFaixa> colMovimentoContaCategoriaConsumoFaixa = 
				                    this.getControladorUtil().pesquisar( filtroMovimentoContaCategoriaConsumoFaixa, MovimentoContaCategoriaConsumoFaixa.class.getName() );
				                
				                for ( MovimentoContaCategoriaConsumoFaixa helperMovimentoContaCategoriaConsumoFaixa : colMovimentoContaCategoriaConsumoFaixa ){
				                    
				                    ContaCategoriaConsumoFaixa contaCategoriaConsumoFaixa = new ContaCategoriaConsumoFaixa();
				                    contaCategoriaConsumoFaixa.setCategoria(
				                    		helperMovimentoContaCategoriaConsumoFaixa.getMovimentoContaPrefaturadaCategoria().getComp_id().getCategoria() );
				                    
				                    contaCategoriaConsumoFaixa.setSubcategoria( 
				                    		helperMovimentoContaCategoriaConsumoFaixa.getMovimentoContaPrefaturadaCategoria().getComp_id().getSubcategoria() );
				                    
				                    contaCategoriaConsumoFaixa.setContaCategoria( contaCategoria );
				                    
				                    contaCategoriaConsumoFaixa.setValorAgua( helperMovimentoContaCategoriaConsumoFaixa.getValorFaturadoAguaNaFaixa() );
				                    contaCategoriaConsumoFaixa.setConsumoAgua( helperMovimentoContaCategoriaConsumoFaixa.getConsumoFaturadoAguaNaFaixa() );
				                    contaCategoriaConsumoFaixa.setValorEsgoto( helperMovimentoContaCategoriaConsumoFaixa.getValorFaturadoEsgotoNaFaixa() );
				                    contaCategoriaConsumoFaixa.setConsumoEsgoto( helperMovimentoContaCategoriaConsumoFaixa.getConsumoFaturadoEsgotoNaFaixa() );
				                    contaCategoriaConsumoFaixa.setConsumoFaixaInicio( helperMovimentoContaCategoriaConsumoFaixa.getLimiteInicialConsumoNaFaixa() );
				                    contaCategoriaConsumoFaixa.setConsumoFaixaFim( helperMovimentoContaCategoriaConsumoFaixa.getLimiteFinalConsumoNaFaixa() );
				                    contaCategoriaConsumoFaixa.setValorTarifaFaixa( helperMovimentoContaCategoriaConsumoFaixa.getValorTarifaNaFaixa() );
				                    contaCategoriaConsumoFaixa.setUltimaAlteracao( new Date() );
				                    
				                    this.getControladorBatch().inserirObjetoParaBatch( contaCategoriaConsumoFaixa );                    
				                }
			                }
			            }
			           
			            /*
						 * Para cada registro do tipo 4, alterar na tabela CONTA_IMPOSTOS_DEDUZIDOS o seu correspondente
						 * (CNTA_ID = Conta do movimento em processamento e IMTP_ID = código do imposto do movimento) , 
						 * com os seguintes dados
						 */
			            FiltroMovimentoContaImpostoDeduzido filtroMovimentoContaImpostoDeduzido
			                = new FiltroMovimentoContaImpostoDeduzido();
			        
			            filtroMovimentoContaImpostoDeduzido.adicionarParametro(new ParametroSimples( 
			            		FiltroMovimentoContaImpostoDeduzido.MOVIMENTO_CONTA_PREFATURADA_ID, helper.getId() ) );
			        
			            Collection<MovimentoContaImpostoDeduzido> colMovimentoContaImpostoDeduzido = 
			                this.getControladorUtil().pesquisar( filtroMovimentoContaImpostoDeduzido, MovimentoContaImpostoDeduzido.class.getName() );
			            
			            BigDecimal valorTotalMenosImposto = new BigDecimal(valorAgua.doubleValue() + valorEsgoto.doubleValue() +
								contaAtualizacao.getDebitos().doubleValue() - contaAtualizacao.getValorCreditos().doubleValue() );
			            
			            for ( MovimentoContaImpostoDeduzido helperMovimentoContaImpostoDeduzido : colMovimentoContaImpostoDeduzido ){
			            
			                FiltroContaImpostosDeduzidos filtroContaImpostosDeduzidos = new FiltroContaImpostosDeduzidos();
			                
			                filtroContaImpostosDeduzidos.adicionarParametro(new ParametroSimples( 
			                		FiltroContaImpostosDeduzidos.CONTA_ID, helper.getConta().getId() ) );
			                
			                filtroContaImpostosDeduzidos.adicionarParametro(new ParametroSimples( 
			                		FiltroContaImpostosDeduzidos.IMPOSTO_TIPO, helperMovimentoContaImpostoDeduzido.getImpostoTipo().getId() ) );
			                
			                
			                Collection<ContaImpostosDeduzidos> colContaImpostosDeduzidos = this.getControladorUtil().pesquisar( 
			                        filtroContaImpostosDeduzidos, ContaImpostosDeduzidos.class.getName() );
			                
			                ContaImpostosDeduzidos contaImpostosDeduzidos = ( ContaImpostosDeduzidos ) 
			                		Util.retonarObjetoDeColecao( colContaImpostosDeduzidos );
			                
			                if(contaImpostosDeduzidos != null && !contaImpostosDeduzidos.equals("")){
			                	contaImpostosDeduzidos.setValorImposto( helperMovimentoContaImpostoDeduzido.getValorImposto() );
			                	valorImposto = valorImposto.add(helperMovimentoContaImpostoDeduzido.getValorImposto());
			                
				                contaImpostosDeduzidos.setPercentualAliquota( helperMovimentoContaImpostoDeduzido.getPercentualAliquota() );
				
				                contaImpostosDeduzidos.setValorBaseCalculo( valorTotalMenosImposto );
				                contaImpostosDeduzidos.setUltimaAlteracao( new Date() );                
				                
				                try{
				                	repositorioFaturamento.atualizarContaImpostosDeduzidosProcessoMOBILE( contaImpostosDeduzidos );
								} catch (ErroRepositorioException e) {
									throw new ControladorException( "erro.sistema", e );
								}
			                }
			            }
			            
			            // Atualiza os dados da conta
			            
			            // Consumo de água calculado do movimento.
			            contaAtualizacao.setConsumoAgua( consumoAgua );
			            // Consumo de esgoto calculado do movimento.
			            contaAtualizacao.setConsumoEsgoto( consumoEsgoto );
			            // Rateio do consumo de água calculado do movimento
			            contaAtualizacao.setConsumoRateioAgua( consumoRateioAgua );
			            // Rateio do consumo de esgoto calculado do movimento
			            contaAtualizacao.setConsumoRateioEsgoto( consumoRateioEsgoto );
			            // Somatório do valor total de água
			            contaAtualizacao.setValorAgua( valorAgua );
			            // Somatório do valor total de esgoto
			            contaAtualizacao.setValorEsgoto( valorEsgoto );
			            // Somatório do valor total de imposto
			            contaAtualizacao.setValorImposto( valorImposto );
			            // Data da Leitura do Movimento
			            contaAtualizacao.setDataEmissao( helper.getDataHoraLeitura() );            
			            // Ultima alteração
			            contaAtualizacao.setUltimaAlteracao( new Date() );
			            // Leitura do Hidrômetro
			            contaAtualizacao.setNumeroLeituraAtual( helper.getLeituraFaturamento() );
			            // Leitura Anterior do Hidrômetro
			            contaAtualizacao.setNumeroLeituraAnterior( helper.getLeituraHidrometroAnterior() );
			            // Atualizamos a situação da conta
			            DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
			            
			            /*
						 * Alterado por Raphael Rossiter em conjunto com Sávio e
						 * Eduardo em 29/07/2011
						 * 
						 * Caso a conta não tenha sido impressa (MCPF_ICEMISSAOCONTA=2) e o imóvel não esteja
						 * vinculado com nenhuma outra matrícula (Imóvel micro);
						 * a conta permanece como pre-faturada (DCST_IDADUAL=9)
						 */
			            if(helper.getIndicadorEmissaoConta().shortValue() == ConstantesSistema.NAO.shortValue()){

			            	if (helper.getImovel().getImovelCondominio() != null && !helper.getRota().getLeituraTipo().getId().equals(LeituraTipo.LEITURA_ANDROID)){
			            		debitoCreditoSituacao.setId( DebitoCreditoSituacao.NORMAL );
			            	}else{
			            		debitoCreditoSituacao.setId( DebitoCreditoSituacao.PRE_FATURADA );
			            	}
			            }else{
			            	debitoCreditoSituacao.setId( DebitoCreditoSituacao.NORMAL );
			            }
			            
			            contaAtualizacao.setDebitoCreditoSituacaoAtual( debitoCreditoSituacao );
			            
			            // Só irá atualizar o nitrato na conta caso o mesmo
						// ainda nao tenha sido atualizado
			            FiltroCreditoARealizar filtroCreditoARealizar = new FiltroCreditoARealizar();
			            filtroCreditoARealizar.adicionarParametro( new ParametroSimples( FiltroCreditoARealizar.IMOVEL_ID, helper.getImovel().getId() ) );
			            filtroCreditoARealizar.adicionarParametro( new ParametroSimples( FiltroCreditoARealizar.ANO_MES_REFERENCIA_CREDITO, helper.getAnoMesReferenciaPreFaturamento() ) );
			            filtroCreditoARealizar.adicionarParametro( new ParametroSimples( FiltroCreditoARealizar.CREDITO_TIPO, CreditoTipo.CREDITO_NITRATO ) );
			            
			            Collection<CreditoARealizar> colCreditoARealizar = this.getControladorUtil().pesquisar( filtroCreditoARealizar, CreditoARealizar.class.getName() );
			            
			            if ( colCreditoARealizar != null && colCreditoARealizar.size() > 0 ){			            
			            	CreditoARealizar credito = ( CreditoARealizar ) Util.retonarObjetoDeColecao( colCreditoARealizar );
			            	
			            	if ( credito.getValorCredito() == null || credito.getValorCredito().floatValue() == 0 ){
					            // Verifica se o imóvel possui situação especial de Nitrato
					            BigDecimal valorCreditoNitrato = this.atualizarCreditoARealizarNitrato(helper.getImovel(),
					            		helper.getAnoMesReferenciaPreFaturamento(),valorAgua,helper.getConta());
			            		
					            // Caso o valor do crédito de nitrato esteja diferente de nulo, atualizar o valor do crédito
					            if(valorCreditoNitrato != null){
					            	BigDecimal valorCreditos = contaAtualizacao.getValorCreditos();
					            	valorCreditos = valorCreditos.add(valorCreditoNitrato);
					            	contaAtualizacao.setValorCreditos(valorCreditos);
					            }			            		
			            	}
			            }
			            
			            
			            // Só irá atualizar o crédito do contrato de consumo na conta caso o mesmo
						// ainda nao tenha sido atualizado
			            FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
			            filtroCreditoTipo.adicionarParametro( new ParametroSimples( FiltroCreditoTipo.CODIGO_CONSTANTE, CreditoTipo.CODIGO_CONSTANTE_CONTRATO_DEMANDA ) );
			            Collection<CreditoTipo> colCreditoTipo = (Collection<CreditoTipo>) this.getControladorUtil().pesquisar( filtroCreditoTipo , CreditoTipo.class.getName() );
			            CreditoTipo ctContratoDemanda = (CreditoTipo) Util.retonarObjetoDeColecao( colCreditoTipo );
			            
			            if ( ctContratoDemanda != null ){			            
				            filtroCreditoARealizar = new FiltroCreditoARealizar();
				            filtroCreditoARealizar.adicionarParametro( new ParametroSimples( FiltroCreditoARealizar.IMOVEL_ID, helper.getImovel().getId() ) );
				            filtroCreditoARealizar.adicionarParametro( new ParametroSimples( FiltroCreditoARealizar.ANO_MES_REFERENCIA_CREDITO, helper.getAnoMesReferenciaPreFaturamento() ) );
				            filtroCreditoARealizar.adicionarParametro( new ParametroSimples( FiltroCreditoARealizar.CREDITO_TIPO, ctContratoDemanda.getId() ) );
				            
				            colCreditoARealizar = this.getControladorUtil().pesquisar( filtroCreditoARealizar, CreditoARealizar.class.getName() );
				            
				            if ( colCreditoARealizar != null && colCreditoARealizar.size() > 0 ){			            
				            	CreditoARealizar credito = ( CreditoARealizar ) Util.retonarObjetoDeColecao( colCreditoARealizar );
				            	
				            	if ( credito.getValorCredito() == null || credito.getValorCredito().floatValue() == 0 ){
				            		
				            		// Somamos o valor de agua e o esgoto, que iremos utilizar para dar o crédito
				            		BigDecimal valorAguaEsgoto = valorAgua.add( valorEsgoto );
				            		
				            		BigDecimal valorCreditoContratoDemanda = this.atualizarCreditoARealizarContratoDemanda(
				            				helper.getImovel(), 
				            				helper.getAnoMesReferenciaPreFaturamento(), 
				            				valorAguaEsgoto, 
				            				helper.getConta(),
				            				ctContratoDemanda);
				            		
						            // Caso o valor do crédito do contrato de demanda esteja
									// diferente de nulo,
						            // atualizar o valor do crédito na conta
						            if(valorCreditoContratoDemanda != null){
						            	BigDecimal valorCreditos = contaAtualizacao.getValorCreditos();
						            	valorCreditos = valorCreditos.add(valorCreditoContratoDemanda);
						            	contaAtualizacao.setValorCreditos(valorCreditos);
						            }			            		
				            	}
				            }			            
			            }
			            
			            // verifica se o valor crédito é maior que o valor da conta
			            // caso seja chamar atualizar os creditos a realizar e os creditos realizados
			            BigDecimal valorTotalContaSemCreditos = valorAgua.add(valorEsgoto);
			            valorTotalContaSemCreditos = valorTotalContaSemCreditos.add(contaAtualizacao.getDebitos());
			            valorTotalContaSemCreditos = valorTotalContaSemCreditos.subtract(valorImposto);
			            BigDecimal valorCreditos = contaAtualizacao.getValorCreditos();
			            if(valorCreditos.compareTo(valorTotalContaSemCreditos) == 1){
			            	BigDecimal valorTotalCreditos = this.atualizarCreditoResidual(contaAtualizacao.getImovel(),
			            			                      contaAtualizacao.getId(),
			            			                      helper.getFaturamentoGrupo().getAnoMesReferencia(),
			            			                      valorTotalContaSemCreditos);
			            	contaAtualizacao.setValorCreditos(valorTotalCreditos);
			            }
			            
			            
			            //RM 14869 - 29/10/2015
			            ContaMotivoRevisao contaMotivoRevisao = pesquisaMotivoRevisao (imo,
			            	helper.getConsumoAnormalidade(), helper.getNumeroMesMotivoRevisao());
			            if(contaMotivoRevisao != null){
			            	contaAtualizacao.setDataRevisao(new Date());
			            	contaAtualizacao.setContaMotivoRevisao(contaMotivoRevisao);
			            }
			            
			            try {
							repositorioFaturamento.atualizarContaProcessoMOBILE( contaAtualizacao );
						} catch (ErroRepositorioException e) {
							throw new ControladorException( "erro.sistema", e );
						}
						
						// Verfificar se o imóvel é para ser faturado ou não,
						// caso não seja então deletar a conta.
						boolean faturar = true;
						if(contaAtualizacao.getLigacaoAguaSituacao() != null && 
								contaAtualizacao.getLigacaoAguaSituacao().getIndicadorFaturamentoSituacao().equals(ConstantesSistema.NAO) && 
								contaAtualizacao.getLigacaoEsgotoSituacao() != null && 
								contaAtualizacao.getLigacaoEsgotoSituacao().getIndicadorFaturamentoSituacao().equals(ConstantesSistema.NAO) && 
								contaAtualizacao.getDebitos().compareTo(BigDecimal.ZERO) == 0){
							faturar = false;
						}
			            
			            BigDecimal valorMinimoEmissao = sistemaParametro.getValorMinimoEmissaoConta();
			            // Caso o valor da conta seja menor que o valor mínimo
						// permitido para a deleção da conta, então deleta os dados da conta
			            if(contaAtualizacao.getValorTotal().compareTo(valorMinimoEmissao) < 0 || !faturar){
			            	if(contaAtualizacao.getValorCreditos().compareTo(BigDecimal.ZERO) == 0){
			            		// Objeto que armazenará as informações para deleção das contas
			    				ApagarDadosFaturamentoHelper helperApagarDadosFaturamento = new ApagarDadosFaturamentoHelper();
			    				helperApagarDadosFaturamento.setIdDebitoCreditoSituacaoAtual(DebitoCreditoSituacao.NORMAL);
			    				helperApagarDadosFaturamento.setIdImovel(helper.getImovel().getId());
			    				helperApagarDadosFaturamento.setAnoMesFaturamento(helper.getAnoMesReferenciaPreFaturamento());
								
								this.apagarDadosGeradosFaturarGrupoFaturamento(helperApagarDadosFaturamento, FaturamentoAtividade.FATURAR_GRUPO.intValue());
								
								// pula de imóvel
			            		continue;
			            	}
			            }
			            
			            boolean contaNaoImpressa = false;
			            // Caso o valor da conta seja zero e o imóvel não tenha crédito,
			            // então não coloca em conta_impressão
			            if(contaAtualizacao.getValorTotal().compareTo(BigDecimal.ZERO) == 0 ){
			            	if(contaAtualizacao.getValorCreditos().compareTo(BigDecimal.ZERO) == 0){
			            		contaNaoImpressa = true;
			            	}
			            }
			            
			            // Caso o indicador de emissão de conta seja igual à não emitida
			            if ( helper.getIndicadorEmissaoConta().shortValue() == ConstantesSistema.NAO.shortValue() && !contaNaoImpressa){
			            	 
			            	// FS0002 - Verificar não impressão da conta
							Integer idAnormalidadeLeitura = null;
							try {
								idAnormalidadeLeitura = this.repositorioFaturamento.pesquisarIndicadorNaoImprimirConta(helper.getImovel().getId(),
										helper.getFaturamentoGrupo().getAnoMesReferencia());
							} catch (ErroRepositorioException e) {
								e.printStackTrace();
							}
							
							if (idAnormalidadeLeitura == null){
							
				                ContaImpressao contaImpressao = new ContaImpressao();
				                contaImpressao.setId( helper.getConta().getId() );
				                ContaGeral contaGeral = new ContaGeral();
				                contaGeral.setConta( contaAtualizacao );
				                contaImpressao.setContaGeral( contaGeral );
				                contaImpressao.setReferenciaConta( helper.getFaturamentoGrupo().getAnoMesReferencia() );
				                contaImpressao.setFaturamentoGrupo( helper.getFaturamentoGrupo() );
				                contaImpressao.setIndicadorImpressao( ConstantesSistema.NAO );
				                contaImpressao.setUltimaAlteracao( new Date() );
				                
				                /*
								 * Caso esteja indicado no imóvel que a conta deve ser entregue ao responsável (ICTE_ID da tabela
								 * IMOVEL seja igual a 1 ou 3), e o imóvel não seja débito automático( IMOV_ICDEBITOCONTA da tabela
								 * IMOVEL seja igual a 2), atribuir CLIE_ID da tabela CLIENTE_IMOVEL para IMOV_ID=Id da
								 * matrícula do imóvel e CLIM_DTRELACAOFIM com o valor correspondente a nulo e CRTP_ID com o valor
								 * correspondente a responsável da tabela CLIENTE_RELACAO_TIPO, caso contrário atribuir o
								 * valor nulo.
								 */
				                // CAERN só vai imprimir quando for enviar para o cliente responsavel no final do grupo
				                // helper.getImovel().getImovelContaEnvio().getId() == ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL ||
			                    // helper.getImovel().getImovelContaEnvio().getId() == ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL
				                
				                boolean clienteResponsavel = false;
				        		if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)){
				        			if(helper.getImovel().getImovelContaEnvio() != null && 
				        			   (helper.getImovel().getImovelContaEnvio().getId().equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL_FINAL_GRUPO))){
				        				clienteResponsavel = true;
				        			}
				        		}else{
				        			if(helper.getImovel().getImovelContaEnvio() != null && 
			        				   (helper.getImovel().getImovelContaEnvio().getId().equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL) || 
		        						helper.getImovel().getImovelContaEnvio().getId().equals(ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL)||
		        						helper.getImovel().getImovelContaEnvio().getId().equals(ImovelContaEnvio.ENVIAR_CONTA_BRAILLE_RESPONSAVEL))){
				        					clienteResponsavel = true;
				        				}
				        		}
				        		Integer idClienteResponsavel = null;
				                if (  clienteResponsavel ){
					                try{
					                	idClienteResponsavel = (Integer)repositorioFaturamento.pesquisarClienteResponsavel(helper.getImovel().getId());
					                	
					                	if(idClienteResponsavel != null && !idClienteResponsavel.equals("")){
					                		Cliente cliente = new Cliente();
					                		cliente.setId(idClienteResponsavel);
					                		contaImpressao.setClienteResponsavel( cliente );     
					                	}
									} catch (ErroRepositorioException e) {
										throw new ControladorException( "erro.sistema", e );
									}
				                }
				                
				                /*
								 * 1.Caso id do cliente responsável esteja preenchido (CLIE_IDRESPONSAVEL) atribuir o valor
								 * correspondente a conta de cliente responsável da tabela CONTA_TIPO;
								 * 
								 * 2.Caso imóvel seja débito automático (IMOV_ICDEBITOCONTA da tabela IMOVEL seja igual a
								 * 1), atribuir o valor correspondente a conta débito automático da tabela CONTA_TIPO;
								 * 
								 * Caso nenhuma das condições acima tenha sido verdadeira atribuir o valor correspondente a
								 * conta normal da tabela CONTA_TIPO;
								 */
				                
//				                ContaTipo contaTipo = new ContaTipo();
//				                if ( contaImpressao.getClienteResponsavel() != null ){
//				                	if(helper.getImovel().getIndicadorDebitoConta().equals(ConstantesSistema.SIM )){
//				                		contaTipo.setId( ContaTipo.CONTA_DEBITO_AUTO_COM_CLIENTE_RESP ); 
//				                	}else{
//				                		contaTipo.setId( ContaTipo.CONTA_CLIENTE_RESPONSAVEL ); 
//				                	}
//				                                       
//				                } else if ( helper.getImovel().getIndicadorDebitoConta().equals(ConstantesSistema.SIM )){
//				                    contaTipo.setId( ContaTipo.CONTA_DEBITO_AUTOMATICO );
//				                } else {
//				                    contaTipo.setId( ContaTipo.CONTA_NORMAL );                    
//				                }
//				                contaImpressao.setContaTipo( contaTipo );
				                
				                //RM 14869 - 29/10/2015
				            	contaImpressao.setContaTipo(this.obterContaTipoParaContaImpressao(contaAtualizacao, idClienteResponsavel, helper.getImovel()));
				                
				                /*
								 * Caso a empresa seja a COMPESA e Caso id do cliente responsável esteja preenchido
								 * (CLIE_IDRESPONSAVEL) atribuir o valor nulo, caso contrário atribuir à empresa associada à rota
								 * (EMPR_ID da tabela ROTA);
								 */
				                if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COMPESA)){
					                if ( contaImpressao.getClienteResponsavel() == null ){
					                    contaImpressao.setEmpresa( helper.getImovel().getQuadra().getRota().getEmpresa() );
					                }
				                }else{
				                	/*
									 * Caso contrário, atribuir à empresa associada à rota (EMPR_ID da tabela ROTA);
									 */
				                	if(!sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)){
				                		contaImpressao.setEmpresa( helper.getImovel().getQuadra().getRota().getEmpresa() );
				                	}
				                }
				                /*
								 * Valor total da conta (Soma do valor da água + Soma do valor de esgoto + Valor de débitos da
								 * conta ? Valor de créditos da conta ? Soma do valor dos impostos)
								 */
				                BigDecimal valorTotalConta = BigDecimal.ZERO;
				                if(valorTotalMenosImposto != null){
				                	valorTotalConta = valorTotalMenosImposto.subtract(valorImposto);
				                }
				                contaImpressao.setValorConta( valorTotalConta );
				                
			                    this.getControladorBatch().inserirObjetoParaBatch( contaImpressao );
							}
			            }
			            
			            //Gerar Movimento Debitos Carteira 17
			            this.gerarMovimentoDebitoCarteira17(sistemaParametro, helper.getConta(), helper.getFaturamentoGrupo());
		            }    
		            
		            // Atualiza a forma do documento de cobrança se a conta foi impressa
		            if(helper.getIndicadorEmissaoConta().equals(ConstantesSistema.SIM) && 
		            	helper.getCobrancaDocumento() != null && helper.getCobrancaDocumento().getId() != null){
		            	
		            	repositorioCobranca.atualizarFormaEmissaoCobrancaDocumento(helper.getCobrancaDocumento().getId());
		            }
		        }
	        }
        
        }catch ( Exception e ){
        	System.out.println("MATRICULA IMOVEL QUE DEU ERRO:"+ matriculaImovel);
        	e.printStackTrace();
    		throw new ControladorException("erro.sistema", e);
    	}
    }

	/**
	 * [UC0840] Atualizar Conta Pré-faturada
	 * [SB0006] Pesquisa motivo de revisão
	 * Autor Vivianne Sousa
	 * Data: 28/10/2015
	 */
	public ContaMotivoRevisao pesquisaMotivoRevisao (Imovel imovel, ConsumoAnormalidade consumoAnormalidade, 
			Short numeroMesMotivoRevisao)  throws ControladorException {
		
		ContaMotivoRevisao contaMotivoRevisao = null; 
		if(consumoAnormalidade != null && consumoAnormalidade.getId() != null 
				&& numeroMesMotivoRevisao != null && !numeroMesMotivoRevisao.equals(new Short("0"))){
			
			// Obtém a quantidade de economias por categoria
			Collection colecaoCategoria = getControladorImovel().obterQuantidadeEconomiasCategoria(imovel);
			int maiorQuantidadeEconomia = 0;
			Integer idCategoriaComMaisEconomias = null;
			Iterator colecaoCategoriaIterator = colecaoCategoria.iterator();
			
			if (consumoAnormalidade.getId().equals(ConsumoAnormalidade.ALTO_CONSUMO) ||
					consumoAnormalidade.getId().equals(ConsumoAnormalidade.ESTOURO_CONSUMO)){
				while (colecaoCategoriaIterator.hasNext()) {
					Categoria categoria = (Categoria) colecaoCategoriaIterator.next();
					int qtdEconomias = categoria.getQuantidadeEconomiasCategoria().intValue();
					// Obtém a maior quantidade de economias e a vezes média de estouro
					if (maiorQuantidadeEconomia < qtdEconomias) {
						maiorQuantidadeEconomia = qtdEconomias;
						idCategoriaComMaisEconomias = categoria.getId();
					}
				}
			}
			
			ConsumoAnormalidadeAcao consumoAnormalidadeAcao = getControladorMicromedicao().verificaAcaoASerTomada(
				consumoAnormalidade.getId(), idCategoriaComMaisEconomias, imovel.getImovelPerfil().getId(), null);
			
			if(numeroMesMotivoRevisao.equals(new Short("1"))){
				contaMotivoRevisao = consumoAnormalidadeAcao.getContaMotivoRevisaoMes1();
			}else if(numeroMesMotivoRevisao.equals(new Short("2"))){
				contaMotivoRevisao = consumoAnormalidadeAcao.getContaMotivoRevisaoMes2();
			}else{
				contaMotivoRevisao = consumoAnormalidadeAcao.getContaMotivoRevisaoMes3();
			}
			
			if(contaMotivoRevisao == null){
				contaMotivoRevisao = new ContaMotivoRevisao();
				contaMotivoRevisao.setId(ContaMotivoRevisao.REVISAO_AUTOMATICA_ESTOURO_CONSUMO);
			}
		}
		
		return contaMotivoRevisao;
	}
	
	/**
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * 
	 */
	private Object[] incluirMovimentoContaPreFaturada(BufferedReader buffer, int indicadorAndroid, boolean offline)
			throws ControladorException, MobileComunicationException {

		// Criamos o arquivo de retorno ou então inserimos os dados
		byte[] relatorioRetorno = null;

		Object[] retorno = new Object[3];

		try {
			// Convertemos o arquivo em uma coleção de Helpers
			AtualizarContaPreFaturadaHelper helper = new AtualizarContaPreFaturadaHelper();
			Collection<AtualizarContaPreFaturadaHelper> colecaoAtualizarContaPreFaturadaHelper = new ArrayList();
			AtualizarContaPreFaturadaHelper helperDadosCabecalho = null;

			Collection<AtualizarContaPreFaturadaHelper> colHelper = null;
			
			if (indicadorAndroid==2){
				colHelper = helper.parseHelper(buffer);
			} else {
				colHelper = helper.parseHelperPipe(buffer);
			}

			// 2. O sistema valida os dados.
			// Coletamos todos os erros
			Collection<String> errors = new ArrayList();

			// [FS0008 - Verificar seqüência dos tipos de registro]
			// Para essa validação, precisamos ler todo o arquivo
			errors.addAll(verificarSequenciaTiposRegistros(colHelper));

			// Para as outras, validamos linha a linha para economizar processamento
			// Verificamos que:
			Integer matriculaImovel = null;
			for (AtualizarContaPreFaturadaHelper helperLaco : colHelper) {

				if (helperLaco.getTipoRegistro() == 1
					&& (matriculaImovel == null || !matriculaImovel.equals(helperLaco.getMatriculaImovel()))) {
					matriculaImovel = helperLaco.getMatriculaImovel();
					colecaoAtualizarContaPreFaturadaHelper.add(helperLaco);
					helperDadosCabecalho = helperLaco;
				}

				// [FS0009] - Verificar valor do tipo de registro]
				errors.addAll(verificarValorTipoRegistro(helperLaco));

				// [FS0002 - Verificar existência da matrícula do imóvel]
				if (offline){
					errors.addAll(verificarExistenciaMatriculaImovel(helperLaco));
				}

				// [FS0003] - Verificar tipo de medição
				errors.addAll(verificarTipoMedicao(helperLaco));

				// [FS0001] - Verificar existência do grupo de faturamento de leitura
				errors.addAll(verificarExistenciaFaturamentoGrupo(helperLaco));

				// [FS0005] - Verificar existência do código da anormalidade de leitura
				errors.addAll(verificarExistenciaCodigoAnormalidadeLeitura(helperLaco));

				// [FS0004 - Verificar data e hora da leitura]
				errors.addAll(verificarDataHoraLeitura(helperLaco));

				// [FS0006] - Validar indicador de confirmação de leitura
				errors.addAll(validarIndicadorConfirmacaoLeitura(helperLaco));

				// [FS0012] - Verificar existência do código da anormalidade de consumo
				errors.addAll(verificarExistenciaCodigoAnormalidadeConsumo(helperLaco));

				// [FS0011] - Verificar existência da categoria
				errors.addAll(verificarExistenciaCategoria(helperLaco));

				// [FS0010] - Verificar existência do tipo do imposto
				errors.addAll(verificarExistenciaImpostoTipo(helperLaco));
			}

			if (errors != null && errors.size() > 0 && helperDadosCabecalho != null) {
				
				for (String erro : errors) {
					System.out.println( "Erros encontrador no envio: " + erro );
				}
				
				// [SB0001] - Gera Tela Resumo das leituras e anormalidades da
				// impressão simultânea com Problemas
				relatorioRetorno = geraResumoInconsistenciasLeiturasAnormalidades(
						errors, helperDadosCabecalho);
			} else {
				// [SB0002] - Inclui Dados Movimentos Conta Pré-Faturada
				incluiDadosMovimentosContaPreFaturada(colHelper);
			}

			retorno[0] = relatorioRetorno;
			retorno[1] = colecaoAtualizarContaPreFaturadaHelper;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro de io", e);
		}
		return retorno;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Verificar seqüência dos tipos de registros
	 * 
	 * Não poderá vir um registro do tipo 1 depois de outro tipo 1 para o mesmo
	 * imóvel, deverá retornar uma mensagem "Imóvel: <<número do imóvel>>, do
	 * arquivo, com seqüencial 1 depois de outro seqüencial 1";
	 * 
	 * Não poderá vir um registro do tipo 2 sem que tenha um do tipo 1 para o
	 * mesmo imóvel, deverá retornar uma mensagem "Imóvel: <<número do
	 * imóvel>>, do arquivo, com seqüencial 2 sem seqüencial 1.";
	 * 
	 * Não poderá vir um registro do tipo 3 sem que tenha um do tipo 2 para o
	 * mesmo imóvel, deverá retornar uma mensagem "Imóvel: <<número do imóvel>> ,
	 * do arquivo, com seqüencial 3 sem seqüencial 2.";
	 * 
	 * Não poderá vir um registro do tipo 4 sem que tenha um do tipo 1 para o
	 * mesmo imóvel deverá retornar uma mensagem "Imóvel: <<número do imóvel>>,
	 * do arquivo, com seqüencial 4 sem seqüencial 1.";
	 * 
	 * [FS0008] - Verificar seqüência dos tipos de registros
	 * 
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param colHelper
	 * @throws ControladorException
	 */
	private Collection<String> verificarSequenciaTiposRegistros(
			Collection<AtualizarContaPreFaturadaHelper> colHelper) {

		Integer registroAnterior = null;
		Integer matriculaImovelRegistroTipo1Selecionado = null;
		Integer matriculaImovelRegistroTipo2Selecionado = null;

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		for (AtualizarContaPreFaturadaHelper helperLaco : colHelper) {

			if (helperLaco.getTipoRegistro() == AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1) {
				// Não poderá vir um registro do tipo 1 depois de outro tipo 1
				if (registroAnterior != null
						&& registroAnterior
								.equals(helperLaco.getTipoRegistro())) {
					/*
					 * errors.add( ConstantesAplicacao.get(
					 * "atencao.imovel_movimento_dados_faturamento_registro_tipo_1",
					 * helperLaco.getMatriculaImovel()+"" ) );
					 */
				}

				// Guardamos as informações necessarias ao registro tipo 1
				matriculaImovelRegistroTipo1Selecionado = helperLaco
						.getMatriculaImovel();
			} else if (helperLaco.getTipoRegistro() == AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_2) {
				// Não poderá vir um registro do tipo 2 sem que tenha um do
				// tipo 1 para o mesmo imóvel
				if (matriculaImovelRegistroTipo1Selecionado == null
						|| !matriculaImovelRegistroTipo1Selecionado
								.equals(helperLaco.getMatriculaImovel())) {
					errors
							.add(ConstantesAplicacao
									.get(
											"atencao.imovel_movimento_dados_faturamento_registro_tipo_2",
											helperLaco.getMatriculaImovel()
													+ ""));
				}

				// Guardamos as informações necessarias ao registro tipo 2
				matriculaImovelRegistroTipo2Selecionado = helperLaco
						.getMatriculaImovel();
			} else if (helperLaco.getTipoRegistro() == AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_3) {
				// Não poderá vir um registro do tipo 3 sem que tenha um do
				// tipo 2 para o mesmo imóvel
				if (matriculaImovelRegistroTipo2Selecionado == null
						|| !matriculaImovelRegistroTipo2Selecionado
								.equals(helperLaco.getMatriculaImovel())) {
					errors
							.add(ConstantesAplicacao
									.get(
											"atencao.imovel_movimento_dados_faturamento_registro_tipo_3",
											helperLaco.getMatriculaImovel()
													+ ""));
				}
			} else if (helperLaco.getTipoRegistro() == AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_4) {
				// Não poderá vir um registro do tipo 4 sem que tenha um do
				// tipo 1 para o mesmo imóvel
				if (matriculaImovelRegistroTipo1Selecionado == null
						|| !matriculaImovelRegistroTipo1Selecionado
								.equals(helperLaco.getMatriculaImovel())) {
					errors
							.add(ConstantesAplicacao
									.get(
											"atencao.imovel_movimento_dados_faturamento_registro_tipo_4",
											helperLaco.getMatriculaImovel()
													+ ""));
				}
			}

			registroAnterior = helperLaco.getTipoRegistro();
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o tipo de registro possua valor <> 1, 2, 3 ou 4, gerar no log de
	 * consistência a mensagem "Imóvel: <<número do imóvel>> com Movimento de
	 * pré-faturamento com tipo de registro inválido".
	 * 
	 * [FS0009] - Verificar valor do tipo de registro
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarValorTipoRegistro(
			AtualizarContaPreFaturadaHelper helperLaco) {

		Collection<String> errors = new ArrayList();

		if (!helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)
				&& !helperLaco.getTipoRegistro().equals(
						AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_2)
				&& !helperLaco.getTipoRegistro().equals(
						AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_3)
				&& !helperLaco.getTipoRegistro().equals(
						AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_4)
				&& !helperLaco.getTipoRegistro().equals(
						AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_5)) {
			errors.add(ConstantesAplicacao.get(
					"atencao.imovel_tipo_registros_invalidados", helperLaco
							.getMatriculaImovel()
							+ ""));
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso a matrícula do imóvel não exista na tabela IMOVEL, gerar no log de
	 * consistência a mensagem "Matrícula do imóvel inexistente: <<número do
	 * imóvel>>"
	 * 
	 * [FS0002] - Verificar existência da matrícula do imóvel
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 * @throws ControladorException
	 */
	private Collection<String> verificarExistenciaMatriculaImovel(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {
			// [FS0002] - Verificar existência da matrícula do imóvel
			if (this.getControladorImovel().verificarExistenciaImovel(
					helperLaco.getMatriculaImovel()) == 0) {
				errors.add(ConstantesAplicacao.get(
						"atencao.imovel_matricula_inexistente", helperLaco
								.getMatriculaImovel()
								+ ""));
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o tipo de medição seja diferente de zero e não exista na tabela
	 * MEDICAO_TIPO, gerar no log de consistência a mensagem "Imóvel: <<número
	 * do imóvel>> com Tipo de Medição inexistente <<tipo de medição>>".
	 * 
	 * Caso o tipo de medição corresponda à ligação de água e não exista
	 * hidrômetro instalado para a ligação (LAGU_ID=matrícula do imóvel, HIDI_ID
	 * não preenchido na tabela LIGAÇÃO_AGUA), gerar no log de consistência a
	 * mensagem "Imóvel: <<número do imóvel>> com Movimento para ligação de
	 * água sem hidrômetro".
	 * 
	 * Caso o tipo de medição corresponda a poço e não exista hidrômetro
	 * instalado para o poço (IMOV_NNMATRICULA=matrícula do imóvel, HIDI_ID não
	 * preenchido na tabela IMOVEL), gerar no log de consistência a mensagem
	 * "Imóvel: <<número do imóvel>> com Movimento para poço sem hidrômetro".
	 * 
	 * Caso o tipo de medição seja zero e a leitura seja informada e não exista
	 * hidrômetro instalado para o imóvel (LAGU_ID=matrícula do imóvel, HIDI_ID
	 * não preenchido na tabela LIGAÇÃO_AGUA e IMOV_ID=matrícula do imóvel,
	 * HIDI_ID não preenchido na tabela IMOVEL), gerar no log de consistência a
	 * mensagem "Imóvel: <<número do imóvel>> com Movimento para ligação sem
	 * hidrômetro" e retornar para o passo 3 do fluxo principal. Caso o tipo de
	 * medição seja zero e a anormalidade informada não seja compatível com
	 * ligação sem hidrômetro (LTAN_ICIMOVELSEMHIDROMETRO=2) e não exista
	 * hidrômetro instalado para o imóvel (LAGU_ID=matrícula do imóvel, HIDI_ID
	 * não preenchido na tabela LIGAÇÃO_AGUA e IMOV_ID=matrícula do imóvel,
	 * HIDI_ID não preenchido na tabela IMOVEL), gerar no log de consistência a
	 * mensagem "Imóvel: <<número do imóvel>> com Anormalidade não permitida
	 * para ligação sem hidrômetro".
	 * 
	 * [FS0003] - Verificar tipo de medição
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 * @throws ControladorException
	 */
	private Collection<String> verificarTipoMedicao(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		// Apenas testamos o tipo de medição para registros tipo 1
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			// Coletamos as informações necessárias para as validações
			FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
			filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAgua.ID, helperLaco.getMatriculaImovel()));
			filtroLigacaoAgua.adicionarParametro(new ParametroNulo(
					FiltroLigacaoAgua.ID_HIDROMETRO_INSTALACAO_HISTORICO));
			Collection<LigacaoAgua> colLigacaoAgua = this.getControladorUtil()
					.pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());

			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, helperLaco.getMatriculaImovel()));
			filtroImovel.adicionarParametro(new ParametroNulo(
					FiltroImovel.HIDROMETRO_INSTALACAO_HISTORICO_ID));
			Collection<Imovel> colImovel = this.getControladorUtil().pesquisar(
					filtroImovel, Imovel.class.getName());

			/*
			 * Caso o tipo de medição seja diferente de zero e não exista na
			 * tabela MEDICAO_TIPO, gerar no log de consistência a mensagem
			 * "Imóvel: <<número do imóvel>> com Tipo de Medição inexistente <<tipo
			 * de medição>>".
			 */
			if (!helperLaco.getTipoMedicao().equals(ConstantesSistema.ZERO)) {

				FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();
				filtroMedicaoTipo.adicionarParametro(new ParametroSimples(
						FiltroMedicaoTipo.ID, helperLaco.getTipoMedicao()));
				Collection<MedicaoTipo> colMedicaoTipo = this
						.getControladorUtil().pesquisar(filtroMedicaoTipo,
								MedicaoTipo.class.getName());

				if (colMedicaoTipo.size() == 0) {
					errors.add(ConstantesAplicacao.get(
							"atencao.imovel_tipo_medicao_inexistente",
							helperLaco.getMatriculaImovel() + "", helperLaco
									.getTipoMedicao()
									+ ""));
				} else {
					MedicaoTipo medicaoTipo = (MedicaoTipo) Util
							.retonarObjetoDeColecao(colMedicaoTipo);

					/*
					 * Caso o tipo de medição corresponda à ligação de água e
					 * não exista hidrômetro instalado para a ligação
					 * (LAGU_ID=matrícula do imóvel, HIDI_ID não preenchido na
					 * tabela LIGAÇÃO_AGUA), gerar no log de consistência a
					 * mensagem "Imóvel: <<número do imóvel>> com Movimento
					 * para ligação de água sem hidrômetro".
					 */
					if (medicaoTipo.getId() == MedicaoTipo.LIGACAO_AGUA) {
						if (colLigacaoAgua == null
								|| colLigacaoAgua.size() == 0) {
							errors
									.add(ConstantesAplicacao
											.get(
													"atencao.imovel_movimento_ligacao_agua_sem_hidrometro",
													helperLaco
															.getMatriculaImovel()
															+ ""));
						}
						/*
						 * Caso o tipo de medição corresponda a poço e não
						 * exista hidrômetro instalado para o poço
						 * (IMOV_NNMATRICULA=matrícula do imóvel, HIDI_ID não
						 * preenchido na tabela IMOVEL), gerar no log de
						 * consistência a mensagem "Imóvel: <<número do
						 * imóvel>> com Movimento para poço sem hidrômetro".
						 */
					} else if (medicaoTipo.getId() == MedicaoTipo.POCO) {
						if (colImovel == null || colImovel.size() == 0) {
							errors
									.add(ConstantesAplicacao
											.get(
													"atencao.imovel_movimento_poco_sem_hidrometro",
													helperLaco
															.getMatriculaImovel()
															+ ""));
						}
					}
				}
			} else {
				/*
				 * Caso o tipo de medição seja zero e a leitura seja informada e
				 * não exista hidrômetro instalado para o imóvel
				 * (LAGU_ID=matrícula do imóvel, HIDI_ID não preenchido na
				 * tabela LIGAÇÃO_AGUA e IMOV_ID=matrícula do imóvel, HIDI_ID
				 * não preenchido na tabela IMOVEL), gerar no log de
				 * consistência a mensagem "Imóvel: <<número do imóvel>> com
				 * Movimento para ligação sem hidrômetro".
				 */
				if (helperLaco.getLeituraHidrometro() != null) {
					if ((colLigacaoAgua == null || colLigacaoAgua.size() == 0)
							&& (colImovel == null || colImovel.size() == 0)) {
						errors.add(ConstantesAplicacao.get(
								"atencao.movimento_ligacao_sem_hidrometro",
								helperLaco.getMatriculaImovel() + ""));
					}
				}

				/*
				 * Caso o tipo de medição seja zero e a anormalidade informada
				 * não seja compatível com ligação sem hidrômetro
				 * (LTAN_ICIMOVELSEMHIDROMETRO=2) e não exista hidrômetro
				 * instalado para o imóvel (LAGU_ID=matrícula do imóvel, HIDI_ID
				 * não preenchido na tabela LIGAÇÃO_AGUA e IMOV_ID=matrícula do
				 * imóvel, HIDI_ID não preenchido na tabela IMOVEL), gerar no
				 * log de consistência a mensagem "Imóvel: <<número do imóvel>>
				 * com Anormalidade não permitida para ligação sem hidrômetro".
				 */
				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				filtroLeituraAnormalidade
						.adicionarParametro(new ParametroSimples(
								FiltroLeituraAnormalidade.ID, helperLaco
										.getAnormalidadeLeitura()));
				Collection<LeituraAnormalidade> colAnormalidade = this
						.getControladorUtil().pesquisar(
								filtroLeituraAnormalidade,
								LeituraAnormalidade.class.getName());

				if (colAnormalidade != null && colAnormalidade.size() > 0) {

					LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) Util
							.retonarObjetoDeColecao(colAnormalidade);

					if (leituraAnormalidade.getIndicadorImovelSemHidrometro() == ConstantesSistema.NAO
							&& (colLigacaoAgua == null || colLigacaoAgua.size() == 0)
							&& (colImovel == null || colImovel.size() == 0)) {
						errors
								.add(ConstantesAplicacao
										.get(
												"atencao.imovel_anormalidade_nao_permitida_ligacao_sem_hidrometro",
												helperLaco.getMatriculaImovel()
														+ ""));
					}
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o grupo de faturamento não exista na tabela FATURAMENTO_GRUPO,
	 * exibir a mensagem "Grupo de faturamento inexistente" e cancelar a
	 * operação. Lembrar que só virá um grupo por arquivo.
	 * 
	 * [FS0001] - Verificar existência do grupo de faturamento
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarExistenciaFaturamentoGrupo(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o grupo de faturamento não exista na tabela FATURAMENTO_GRUPO,
		 * exibir a mensagem "Grupo de faturamento inexistente" e cancelar a
		 * operação. Lembrar que só virá um grupo por arquivo.
		 */
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.ID, helperLaco
							.getCodigoGrupoFaturamento()));
			Collection<FaturamentoGrupo> colGrupoFaturamento = this
					.getControladorUtil().pesquisar(filtroFaturamentoGrupo,
							FaturamentoGrupo.class.getName());

			if (colGrupoFaturamento == null || colGrupoFaturamento.size() == 0) {
				errors.add(ConstantesAplicacao
						.get("atencao.grupo_faturamento_inexistente"));
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o código da anormalidade seja informado (diferente de zero e de
	 * espaços em branco) e não exista na tabela LEITURA_ANORMALIDADE, gerar no
	 * log de consistência a mensagem "Imóvel: <<número do imóvel>> com Código
	 * da Anormalidade de Leitura inexistente <<código da anormalidade>>".
	 * 
	 * FS0008 - Verificar existência do código da anormalidade de leitura]
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param colHelper
	 */
	private Collection<String> verificarExistenciaCodigoAnormalidadeLeitura(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o código da anormalidade seja informado (diferente de zero e de
		 * espaços em branco) e não exista na tabela LEITURA_ANORMALIDADE, gerar
		 * no log de consistência a mensagem "Imóvel: <<número do imóvel>> com
		 * Código da Anormalidade de Leitura inexistente <<código da
		 * anormalidade>>".
		 */
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			if (helperLaco.getAnormalidadeLeitura() != null) {
				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				filtroLeituraAnormalidade
						.adicionarParametro(new ParametroSimples(
								FiltroLeituraAnormalidade.ID, helperLaco
										.getAnormalidadeLeitura()));
				Collection<LeituraAnormalidade> colAnormalidade = this
						.getControladorUtil().pesquisar(
								filtroLeituraAnormalidade,
								LeituraAnormalidade.class.getName());

				if (colAnormalidade == null || colAnormalidade.size() == 0) {
					errors.add(ConstantesAplicacao.get(
							"atencao.imovel_codigo_anormalidade_inexistente",
							helperLaco.getMatriculaImovel() + "", helperLaco
									.getAnormalidadeLeitura()
									+ ""));
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso a data e hora de leitura seja inválida ou maior que a data corrente,
	 * gerar no log de consistência a mensagem "Imóvel: <<número do imóvel>>
	 * com Data e hora de leitura inválida <<data da leitura>>".
	 * 
	 * Caso o ano/mês da data de leitura não seja igual ao ano/mês de referência
	 * do faturamento do grupo (FTGR_AMREFERENCIA) e não seja igual ao ano/mês
	 * de referência do faturamento do grupo menos um mês e não seja igual ao
	 * ano/mês de referência do faturamento do grupo mais um mês, gerar no log
	 * de consistência a mensagem "Data de leitura incompatível com o mês/ano de
	 * faturamento".
	 * 
	 * [FS0004 - Verificar data e hora da leitura]
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param colHelper
	 */
	private Collection<String> verificarDataHoraLeitura(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o código da anormalidade seja informado (diferente de zero e de
		 * espaços em branco) e não exista na tabela LEITURA_ANORMALIDADE, gerar
		 * no log de consistência a mensagem "Imóvel: <<número do imóvel>> com
		 * Código da Anormalidade de Leitura inexistente <<código da
		 * anormalidade>>".
		 */
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			if (helperLaco.getDataHoraLeituraHidrometro() != null) {
				Date dataLeituraHidrometro = helperLaco
						.getDataHoraLeituraHidrometro();

				dataLeituraHidrometro = Util.subtrairNumeroDiasDeUmaData(
						dataLeituraHidrometro, 1);

				if (Util.compararData(dataLeituraHidrometro, new Date()) == 1) {
					errors
							.add(ConstantesAplicacao
									.get("atencao.data_leitura_incompativel_mes_ano_faturamento"));
				}

				/*
				 * Caso o ano/mês da data de leitura não seja igual ao ano/mês
				 * de referência do faturamento do grupo (FTGR_AMREFERENCIA) e
				 * não seja igual ao ano/mês de referência do faturamento do
				 * grupo menos um mês e não seja igual ao ano/mês de referência
				 * do faturamento do grupo mais um mês, gerar no log de
				 * consistência a mensagem "Data de leitura incompatível com o
				 * mês/ano de faturamento" e retornar para o passo 3 do fluxo
				 * principal.
				 */

				// Pesquisamos o grupo de faturamento
				FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
				filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
						FiltroFaturamentoGrupo.ID, helperLaco
								.getCodigoGrupoFaturamento()));
				Collection<FaturamentoGrupo> colGrupoFaturamento = this
						.getControladorUtil().pesquisar(filtroFaturamentoGrupo,
								FaturamentoGrupo.class.getName());

				FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util
						.retonarObjetoDeColecao(colGrupoFaturamento);

				Integer anoMesFaturamentoGrupoMaisUmMes = Util
						.somaMesAnoMesReferencia(faturamentoGrupo
								.getAnoMesReferencia(), 1);
				Integer anoMesFaturamentoGrupoMenosUmMes = Util
						.subtrairMesDoAnoMes(faturamentoGrupo
								.getAnoMesReferencia(), 1);

				Integer anoMesDataLeituraHidrometro = Util
						.recuperaAnoMesDaData(dataLeituraHidrometro);

				if (faturamentoGrupo.getAnoMesReferencia().intValue() != anoMesDataLeituraHidrometro
						.intValue()
						&& anoMesFaturamentoGrupoMaisUmMes.intValue() != anoMesDataLeituraHidrometro
								.intValue()
						&& anoMesFaturamentoGrupoMenosUmMes.intValue() != anoMesDataLeituraHidrometro
								.intValue()) {

					errors
							.add(ConstantesAplicacao
									.get("atencao.data_leitura_incompativel_mes_ano_faturamento"));
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o Indicador de confirmação de leitura não seja igual a 0 ou 1, gerar
	 * no log de consistência a mensagem "Imóvel: <<número do imóvel>> com
	 * Indicador de Confirmação de Leitura inválido <<indicador de confirmação
	 * de leitura>>".
	 * 
	 * [FS0006] - Validar indicador de confirmação de leitura
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> validarIndicadorConfirmacaoLeitura(
			AtualizarContaPreFaturadaHelper helperLaco) {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o Indicador de confirmação de leitura não seja igual a 0 ou 1,
		 * gerar no log de consistência a mensagem "Imóvel: <<número do
		 * imóvel>> com Indicador de Confirmação de Leitura inválido <<indicador
		 * de confirmação de leitura>>" e retornar para o passo 3 do fluxo
		 * principal.
		 */
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {
			if (helperLaco.getIndicadorConfirmacaoLeitura() != null) {
				if (helperLaco.getIndicadorConfirmacaoLeitura() != 0
						&& helperLaco.getIndicadorConfirmacaoLeitura() != 1) {
					errors
							.add(ConstantesAplicacao
									.get(
											"atencao.imovel_indicador_confirmacao_leitura_invalido",
											helperLaco.getMatriculaImovel()
													+ "",
											helperLaco
													.getIndicadorConfirmacaoLeitura()
													+ ""));
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o código da anormalidade seja informado (diferente de zero e de
	 * espaços em branco) e não exista na tabela CONSUMO_ANORMALIDADE, gerar no
	 * log de consistência a mensagem "Imóvel: <<número do imóvel>> com Código
	 * da Anormalidade de consumo inexistente <<código da anormalidade>>".
	 * 
	 * [FS0012] - Verificar existência do código da anormalidade de consumo
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarExistenciaCodigoAnormalidadeConsumo(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o Indicador de confirmação de leitura não seja igual a 0 ou 1,
		 * gerar no log de consistência a mensagem "Imóvel: <<número do
		 * imóvel>> com Indicador de Confirmação de Leitura inválido <<indicador
		 * de confirmação de leitura>>" e retornar para o passo 3 do fluxo
		 * principal.
		 */
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {

			if (helperLaco.getAnormalidadeConsumo() != null) {
				FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();
				filtroConsumoAnormalidade
						.adicionarParametro(new ParametroSimples(
								FiltroConsumoAnormalidade.ID, helperLaco
										.getAnormalidadeConsumo()));
				Collection<ConsumoAnormalidade> colConsumoAnormalidade = this
						.getControladorUtil().pesquisar(
								filtroConsumoAnormalidade,
								ConsumoAnormalidade.class.getName());

				if (colConsumoAnormalidade != null
						&& colConsumoAnormalidade.size() == 0) {
					errors
							.add(ConstantesAplicacao
									.get(
											"atencao.imovel_codigo_anormalidade_consumo_inexistente",
											helperLaco.getMatriculaImovel()
													+ "",
											helperLaco
													.getIndicadorConfirmacaoLeitura()
													+ ""));
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o código da categoria não exista na tabela CATEGORIA, gerar no log
	 * de consistência a mensagem "Imóvel: <<número do imóvel>> com Categoria
	 * inexistente <<código da categoria>>".
	 * 
	 * [FS0012] - Verificar existência do código da anormalidade de consumo
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarExistenciaCategoria(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o código da categoria não exista na tabela CATEGORIA, gerar no
		 * log de consistência a mensagem "Imóvel: <<número do imóvel>> com
		 * Categoria inexistente <<código da categoria>>".
		 * 
		 */

		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_2)) {

			if (helperLaco.getCodigoCategoria() != 0) {
				FiltroCategoria filtroCategoria = new FiltroCategoria();
				filtroCategoria
						.adicionarParametro(new ParametroSimples(
								FiltroCategoria.CODIGO, helperLaco
										.getCodigoCategoria()));
				Collection<Categoria> colCategoria = this.getControladorUtil()
						.pesquisar(filtroCategoria,
								ConsumoAnormalidade.class.getName());

				if (colCategoria != null && colCategoria.size() == 0) {
					errors.add(ConstantesAplicacao
							.get("atencao.imovel_categoria_inexistente"));
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * Caso o tipo do imposto não exista na tabela IMPOSTO_TIPO, gerar no log de
	 * consistência a mensagem "Imóvel: <<número do imóvel>> com Tipo do
	 * Imposto inexistente <<tipo do imposto>>".
	 * 
	 * [FS0010] - Verificar existência do tipo do imposto
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param helperLaco
	 */
	private Collection<String> verificarExistenciaImpostoTipo(
			AtualizarContaPreFaturadaHelper helperLaco)
			throws ControladorException {

		Collection<String> errors = new ArrayList();

		// Verificamos que:
		/*
		 * Caso o tipo do imposto não exista na tabela IMPOSTO_TIPO, gerar no
		 * log de consistência a mensagem "Imóvel: <<número do imóvel>> com
		 * Tipo do Imposto inexistente <<tipo do imposto>>".
		 * 
		 */
		if (helperLaco.getTipoRegistro().equals(
				AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_4)) {

			if (helperLaco.getTipoImposto() != 0) {
				FiltroImpostoTipo filtroImpostoTipo = new FiltroImpostoTipo();
				filtroImpostoTipo.adicionarParametro(new ParametroSimples(
						FiltroImpostoTipo.ID, helperLaco.getTipoImposto()));
				Collection<ImpostoTipo> colImpostoTipo = this
						.getControladorUtil().pesquisar(filtroImpostoTipo,
								ImpostoTipo.class.getName());

				if (colImpostoTipo != null && colImpostoTipo.size() == 0) {
					errors.add(ConstantesAplicacao
							.get("atencao.imovel_tipo_imposto_inexistente"));
				}
			}
		}

		return errors;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * 
	 * Caso seja chamado por uma tela, o sistema gera uma tela de acordo com o
	 * movimento, Caso contrário, o sistema gera um relatório e envia, por
	 * e-mail para o operador, registrado com os seguintes campos:
	 * 
	 * No cabeçalho imprimir o grupo de faturamento informado (FTGR_ID), o
	 * código e descrição da empresa (EMPR_ID e EMPR_NMEMPRESA da tabela EMPRESA
	 * com EMPR_ID da tabela ROTA com ROTA_ID da tabela QUADRA com QDRA_ID da
	 * tabela IMOVEL com IMOV_ID=matrícula do imóvel do primeiro registro do
	 * arquivo que exista na tabela IMOVEL), o código da localidade e o título
	 * fixo "MOVIMENTO CELULAR - IMPRESSÃO SIMULTÂNEA" quando processado o
	 * arquivo de movimento;
	 * 
	 * Imprimir o erro correspondente encontrado no processamento do imóvel;
	 * 
	 * Caso seja chamado por uma tela, imprimir um texto "Arquivo processado com
	 * problema e enviado para operação para processar o movimento. Localidade <<Código
	 * da Localidade>>";
	 * 
	 * [SB0001] - Gera Tela Resumo das leituras e anormalidades da impressão
	 * simultânea com Problemas
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param colErrors
	 */
	private byte[] geraResumoInconsistenciasLeiturasAnormalidades(
			Collection<String> colErrors, AtualizarContaPreFaturadaHelper helper)
			throws ControladorException {

		RelatorioErrosMovimentosContaPreFaturadas relatorioErrosMovimentosContaPreFaturadas = new RelatorioErrosMovimentosContaPreFaturadas(
				new Usuario());

		// Adicionamos os parametros
		Map parametros = new HashMap();

		parametros.put("imagem", this.getControladorUtil()
				.pesquisarParametrosDoSistema().getImagemRelatorio());

		// Grupo de faturamento
		parametros.put("grupoFaturamento", helper.getCodigoGrupoFaturamento()
				+ "");

		// Id da localidade
		FiltroRota filtro = new FiltroRota();
		filtro.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtro.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA,
				helper.getCodigoRota()));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroRota.LOCALIDADE_ID, helper.getLocalidade()));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroRota.SETOR_COMERCIAL_CODIGO, helper
						.getCodigoSetorComercial()));
		Collection<Rota> colRota = Fachada.getInstancia().pesquisar(filtro,
				Rota.class.getName());
		Rota rota = (Rota) Util.retonarObjetoDeColecao(colRota);
		parametros.put("idLocalidade", helper.getLocalidade() + "");

		// Código do setor Comercial
		parametros.put("codigoSetorComercial", helper.getCodigoSetorComercial()
				+ "");

		// Id e descrição de empresa
		String descricaoRota = "";
		if (rota != null && !rota.equals("")) {
			descricaoRota = rota.getEmpresa().getId() + " - "
					+ rota.getEmpresa().getDescricao();
		}
		parametros.put("idDescricaoEmpresa", descricaoRota);

		// Código da localidade
		parametros.put("codigoLocalidade", helper.getLocalidade() + "");

		// Criamos agora os beans do relatorio
		List relatorioBeans = new ArrayList();

		for (String erro : colErrors) {

			RelatorioErrosMovimentosContaPreFaturadasBean bean = new RelatorioErrosMovimentosContaPreFaturadasBean();

			bean.setError(erro);

			relatorioBeans.add(bean);
		}

		// Criamos o source
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		return relatorioErrosMovimentosContaPreFaturadas
				.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_ERROS_MOVIMENTOS_CONTA_PRE_FATURADAS,
						parametros, ds, TarefaRelatorio.TIPO_PDF);
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * [SB0002] - Inclui Dados Movimentos Conta Pré-Faturada
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param colErrors
	 */
	private void incluiDadosMovimentosContaPreFaturada(
			Collection<AtualizarContaPreFaturadaHelper> colHelper)
			throws ControladorException, MobileComunicationException {

		String matriculaImovel = "";

		Collection<RotaAtualizacaoSeq> colAtuSeq = new ArrayList();

		// Pesquisamos o ano mes de faturamento do grupo
		Integer anoMesFaturamentoGrupoFaturamento = null;
		Rota rota = null;

		try {
			MovimentoContaPrefaturada movimentoContaPreFaturadaIncluido = null;
			MovimentoContaPrefaturadaCategoria movimentoContaPrefaturadaCategoriaIncluido = null;

			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			boolean jaSelecionouRegistroTipo1 = false;
			Object[] dadosArquivoTextoRoteiroEmpresa = null;

			for (AtualizarContaPreFaturadaHelper helper : colHelper) {
				if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {
					if (rota == null || rota.equals("")) {
						rota = pesquisarRotaImpressaoSimultanea(helper);
						if (rota != null && !rota.equals("")) {
							dadosArquivoTextoRoteiroEmpresa = repositorioFaturamento
									.pesquisarArquivoTextoRoteiroEmpresa(rota.getId(), helper.getAnoMesFaturamento());
							if (dadosArquivoTextoRoteiroEmpresa != null) {
								Integer idSituacaoTransmissaoLeitura = (Integer) dadosArquivoTextoRoteiroEmpresa[1];
								if (!idSituacaoTransmissaoLeitura.equals(SituacaoTransmissaoLeitura.DISPONIVEL)
								 && !idSituacaoTransmissaoLeitura.equals(SituacaoTransmissaoLeitura.LIBERADO)
								 && !idSituacaoTransmissaoLeitura.equals(SituacaoTransmissaoLeitura.EM_CAMPO)
								 && !idSituacaoTransmissaoLeitura.equals(SituacaoTransmissaoLeitura.FINALIZADO_NAO_TRANSMITIDO)) {
									matriculaImovel = "" + helper.getMatriculaImovel();
									throw new MobileComunicationException("atencao.arquivo_ja_finalizado",null);
								}
							}

							if (rota.getFaturamentoGrupo() != null && !rota.getFaturamentoGrupo().equals("")) {
								Integer anoMesGrupo = rota.getFaturamentoGrupo().getAnoMesReferencia();
								if (helper.getAnoMesFaturamento() != null && helper.getAnoMesFaturamento() != 0) {
									if (!anoMesGrupo.equals(helper.getAnoMesFaturamento())) {
										matriculaImovel = "" + helper.getMatriculaImovel();
										throw new MobileComunicationException("atencao.grupo_ja_faturado",null);
									}
								}
							}
						}
					}

					// Limpamos
					this.removerDadosMovimentosContaPreFaturada(helper.getNumeroConta(),
							helper.getMatriculaImovel(),helper.getAnoMesFaturamento());
				}
			}

			for (AtualizarContaPreFaturadaHelper helper : colHelper) {

				// 1. Caso o tipo de registro seja igual a 1.
				if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_1)) {
					// [FS0013 - Verificar existência do movimento conta pré-faturada]
					// verificarExistenciaMovimentoContaPreFaturada(helper.getMatriculaImovel(),helper.getAnoMesFaturamento() );
					MovimentoContaPrefaturada movimentoContaPrefaturada = new MovimentoContaPrefaturada();
					// Ano mes de faturamento
					movimentoContaPrefaturada.setAnoMesReferenciaPreFaturamento(helper.getAnoMesFaturamento());

					// Id do imovel
					Imovel imovel = new Imovel();
					imovel.setId(helper.getMatriculaImovel());
					movimentoContaPrefaturada.setImovel(imovel);

					// Tipo de medicao
					MedicaoTipo medicaoTipo = new MedicaoTipo();
					medicaoTipo.setId(helper.getTipoMedicao());
					movimentoContaPrefaturada.setMedicaoTipo(medicaoTipo);

					// Numero da Conta
					if (helper.getNumeroConta() != 0) {
						Conta conta = new Conta();
						conta.setId(helper.getNumeroConta());
						movimentoContaPrefaturada.setConta(conta);
					}

					// Grupo de Faturamento
					FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
					faturamentoGrupo.setId(helper.getCodigoGrupoFaturamento());
					movimentoContaPrefaturada.setFaturamentoGrupo(faturamentoGrupo);

					// Id da rota
					if (rota == null || rota.equals("")) {
						rota = pesquisarRotaImpressaoSimultanea(helper);
					}
					movimentoContaPrefaturada.setRota(rota);

					// Leitura do hidrometro
					movimentoContaPrefaturada.setLeituraHidrometro(helper
							.getLeituraHidrometro());

					// Anormalidade de Leitura
					if (helper.getAnormalidadeLeitura() != null && !helper.getAnormalidadeLeitura().equals(0)) {
						LeituraAnormalidade anormalidadeLeitura = new LeituraAnormalidade();
						anormalidadeLeitura.setId(helper.getAnormalidadeLeitura());
						movimentoContaPrefaturada.setLeituraAnormalidadeLeitura(anormalidadeLeitura);
					}

					// Data e hora de leitura
					movimentoContaPrefaturada.setDataHoraLeitura(helper.getDataHoraLeituraHidrometro());

					// Indicador de confirmação de leitura
					movimentoContaPrefaturada.setIndicadorSituacaoLeitura(helper.getIndicadorConfirmacaoLeitura());

					// Leitura de Faturamento
					movimentoContaPrefaturada.setLeituraFaturamento(helper.getLeituraFaturamento());

					// Consumo medido do mês
					movimentoContaPrefaturada.setConsumoMedido(helper.getConsumoMedido());

					// Consumo a ser medido no mes
					movimentoContaPrefaturada.setConsumoCobrado(helper.getConsumoASerCobradoMes());

					// Tipo de Consumo
					if (helper.getTipoConsumo() != null) {
						ConsumoTipo consumoTipo = new ConsumoTipo();
						consumoTipo.setId(helper.getTipoConsumo());
						movimentoContaPrefaturada.setConsumoTipo(consumoTipo);
					}

					// Anormalidade Consumo
					if (helper.getAnormalidadeConsumo() != null) {
						ConsumoAnormalidade anormalidadeConsumo = new ConsumoAnormalidade();
						anormalidadeConsumo.setId(helper.getAnormalidadeConsumo());
						movimentoContaPrefaturada.setConsumoAnormalidade(anormalidadeConsumo);
					}

					// Data e Hora da geração do movimento
					movimentoContaPrefaturada.setDataHoraGeracaoMovimento(new Date());

					// Fixo 2
					movimentoContaPrefaturada.setIndicadorAtualizacaoFaturamento(Short.parseShort("2"));

					// Data da ultima alteração
					movimentoContaPrefaturada.setUtlimaAlteracao(new Date());

					// Indicador de emissão de conta
					movimentoContaPrefaturada.setIndicadorEmissaoConta(helper.getIndicacaoEmissaoConta());

					// Rateio de consumo de agua
					movimentoContaPrefaturada.setConsumoRateioAgua(helper.getConsumoRateioAgua());

					// Rateio de consumo de esgoto
					movimentoContaPrefaturada.setConsumoRateioEsgoto(helper.getConsumoRateioEsgoto());

					// Rateio de consumo de esgoto
					movimentoContaPrefaturada.setIndicadorGeracaoConta(helper.getIndicadorGeracaoConta());

					// Anormalidade de Faturamento
					if (helper.getAnormalidadeFaturamento() != null
							&& !helper.getAnormalidadeFaturamento().equals(0)) {
						LeituraAnormalidade anormalidadeLeitura = new LeituraAnormalidade();
						anormalidadeLeitura.setId(helper.getAnormalidadeFaturamento());
						movimentoContaPrefaturada.setLeituraAnormalidadeFaturamento(anormalidadeLeitura);
					}

					// Id Cobrança Documento
					if (helper.getIdCobrancaDocumento() != null
							&& !helper.getIdCobrancaDocumento().equals("")) {
						CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
						cobrancaDocumento.setId(helper.getIdCobrancaDocumento());
						movimentoContaPrefaturada.setCobrancaDocumento(cobrancaDocumento);
					}

					// Leitura do hidrometro Anterior
					if (helper.getLeituraHidrometroAnterior() != null
							&& !helper.getLeituraHidrometroAnterior().equals("")) {
						movimentoContaPrefaturada.setLeituraHidrometroAnterior(helper.getLeituraHidrometroAnterior());
					}
					
					//RM 5635 - Registrar a quantidade de vezes que a conta foi impressa através da ISC
					if (helper.getQntVezesImpressa()!=null
					&& !helper.getQntVezesImpressa().trim().equals("")) {
						movimentoContaPrefaturada.setQntVezesImpressa(Integer.parseInt(helper.getQntVezesImpressa().trim()));
					}
					
					if (helper.getValorAgua() != null) {
						movimentoContaPrefaturada.setValorAgua(helper.getValorAgua());
					}
					
					if (helper.getValorEsgoto() != null) {
						movimentoContaPrefaturada.setValorEsgoto(helper.getValorEsgoto());
					}
					
					if (helper.getValorDebitos() != null) {
						movimentoContaPrefaturada.setValorDebitos(helper.getValorDebitos());
					}
					
					if (helper.getValorCreditos() != null) {
						movimentoContaPrefaturada.setValorCreditos(helper.getValorCreditos());
					}
					
					if (helper.getValorImpostos() != null) {
						movimentoContaPrefaturada.setValorImpostos(helper.getValorImpostos());
					}

					movimentoContaPrefaturada.setIndicadorAlteracao(ConstantesSistema.NAO);

					matriculaImovel = movimentoContaPrefaturada.getImovel().getId() + "";
					
					if ( helper.getNumeroCoordenadaX() != null && !helper.getNumeroCoordenadaX().equals("") ) {
						movimentoContaPrefaturada.setNumeroCoordenadaX(Util.formatarBigDecimalDozeCasasDecimais(helper.getNumeroCoordenadaX()));
					}
					
					if ( helper.getNumeroCoordenadaY() != null && !helper.getNumeroCoordenadaY().equals("") ) {
						movimentoContaPrefaturada.setNumeroCoordenadaY(Util.formatarBigDecimalDozeCasasDecimais(helper.getNumeroCoordenadaY()));
					}
					
					if ( helper.getNumeroMesMotivoRevisao() != null && !helper.getNumeroMesMotivoRevisao().equals("") ) {
						movimentoContaPrefaturada.setNumeroMesMotivoRevisao(new Short(helper.getNumeroMesMotivoRevisao()));
					}else{
						movimentoContaPrefaturada.setNumeroMesMotivoRevisao(new Short("0"));
					}

					// Inserimos
					movimentoContaPrefaturada.setId((Integer) this.getControladorBatch().inserirObjetoParaBatch(movimentoContaPrefaturada));

					// Guardamos o atual
					if (!jaSelecionouRegistroTipo1) {
						movimentoContaPreFaturadaIncluido = movimentoContaPrefaturada;
						jaSelecionouRegistroTipo1 = true;
					} else {
						jaSelecionouRegistroTipo1 = false;
					}
					
					// 2. Caso o tipo de registro seja igual a 2.
				} else if (helper.getTipoRegistro().equals(
						AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_2)) {

					jaSelecionouRegistroTipo1 = false;

					Collection<MovimentoContaPrefaturadaCategoria> colMovimentoContaPrefaturadaCategoria = null;

					if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA)) {
						FiltroMovimentoContaPrefaturadaCategoria filtroMovimentoContaPrefaturadaCategoria = new FiltroMovimentoContaPrefaturadaCategoria();
						filtroMovimentoContaPrefaturadaCategoria.adicionarParametro(new ParametroSimples(
								FiltroMovimentoContaPrefaturadaCategoria.MOVIMENTO_CONTA_PREFATURADA_ID,
								movimentoContaPreFaturadaIncluido.getId()));
						filtroMovimentoContaPrefaturadaCategoria.adicionarParametro(new ParametroSimples(
								FiltroMovimentoContaPrefaturadaCategoria.ID_CATEGORIA,
								helper.getCodigoCategoria()));
						colMovimentoContaPrefaturadaCategoria = this.getControladorUtil()
								.pesquisar(filtroMovimentoContaPrefaturadaCategoria,
								MovimentoContaPrefaturadaCategoria.class.getName());

					}

					MovimentoContaPrefaturadaCategoria movimentoContaPrefaturadaCategoria = null;

					if (colMovimentoContaPrefaturadaCategoria != null && !colMovimentoContaPrefaturadaCategoria.isEmpty()) {
						for (MovimentoContaPrefaturadaCategoria movimentoContaPrefaturadaCategoriaAtualizar : colMovimentoContaPrefaturadaCategoria) {
							// Setmos os valores
							BigDecimal valorFaturadoAgua = movimentoContaPrefaturadaCategoriaAtualizar.getValorFaturadoAgua().add(helper.getValorFaturadoAgua());
							Integer consumoFaturadoAgua = movimentoContaPrefaturadaCategoriaAtualizar.getConsumoFaturadoAgua()+ helper.getConsumoFaturadoAgua();
							BigDecimal valorTarifaMinimaAgua = movimentoContaPrefaturadaCategoriaAtualizar.getValorTarifaMinimaAgua().add(helper.getValorTarifaMinimaAgua());
							Integer consumoMinimoAgua = movimentoContaPrefaturadaCategoriaAtualizar.getConsumoMinimoAgua() + helper.getConsumoMinimoAgua();
							movimentoContaPrefaturadaCategoriaAtualizar.setValorFaturadoAgua(valorFaturadoAgua);
							movimentoContaPrefaturadaCategoriaAtualizar.setConsumoFaturadoAgua(consumoFaturadoAgua);
							movimentoContaPrefaturadaCategoriaAtualizar.setValorTarifaMinimaAgua(valorTarifaMinimaAgua);
							movimentoContaPrefaturadaCategoriaAtualizar.setConsumoMinimoAgua(consumoMinimoAgua);

							// Setmos os valores
							BigDecimal valorFaturadoEsgoto = movimentoContaPrefaturadaCategoriaAtualizar.getValorFaturadoEsgoto().add(helper.getValorFaturadoEsgoto());
							Integer consumoFaturadoEsgoto = movimentoContaPrefaturadaCategoriaAtualizar.getConsumoFaturadoEsgoto() + helper.getConsumoFaturadoEsgoto();
							BigDecimal valorTarifaMinimaEsgoto = movimentoContaPrefaturadaCategoriaAtualizar.getValorTarifaMinimaEsgoto().add(helper.getValorTarifaMinimaEsgoto());
							Integer consumoMinimoEsgoto = movimentoContaPrefaturadaCategoriaAtualizar.getConsumoMinimoEsgoto() + helper.getConsumoMinimoEsgoto();

							movimentoContaPrefaturadaCategoriaAtualizar.setValorFaturadoEsgoto(valorFaturadoEsgoto);
							movimentoContaPrefaturadaCategoriaAtualizar.setConsumoFaturadoEsgoto(consumoFaturadoEsgoto);
							movimentoContaPrefaturadaCategoriaAtualizar.setValorTarifaMinimaEsgoto(valorTarifaMinimaEsgoto);
							movimentoContaPrefaturadaCategoriaAtualizar.setConsumoMinimoEsgoto(consumoMinimoEsgoto);

							movimentoContaPrefaturadaCategoriaAtualizar.setUltimaAlteracao(new Date());

							// Atualizar
							this.getControladorUtil().atualizar(movimentoContaPrefaturadaCategoriaAtualizar);

							movimentoContaPrefaturadaCategoria = movimentoContaPrefaturadaCategoriaAtualizar;
						}
					} else {
						movimentoContaPrefaturadaCategoria = new MovimentoContaPrefaturadaCategoria();

						// Informamos o movimento atual
						MovimentoContaPrefaturadaCategoriaPK pk = new MovimentoContaPrefaturadaCategoriaPK();
						pk.setMovimentoContaPrefaturada(movimentoContaPreFaturadaIncluido);

						// Informamos a categoria
						Categoria categoria = new Categoria();
						categoria.setId(helper.getCodigoCategoria());
						pk.setCategoria(categoria);

						// Informamos a subcategoria
						Subcategoria subcategoria = new Subcategoria();
						subcategoria.setId(helper.getCodigoSubCategoria());
						pk.setSubcategoria(subcategoria);

						// Setamos
						movimentoContaPrefaturadaCategoria.setComp_id(pk);

						// Setmos os valores
						movimentoContaPrefaturadaCategoria.setValorFaturadoAgua(helper.getValorFaturadoAgua());
						movimentoContaPrefaturadaCategoria.setConsumoFaturadoAgua(helper.getConsumoFaturadoAgua());
						movimentoContaPrefaturadaCategoria.setValorTarifaMinimaAgua(helper.getValorTarifaMinimaAgua());
						movimentoContaPrefaturadaCategoria.setConsumoMinimoAgua(helper.getConsumoMinimoAgua());

						movimentoContaPrefaturadaCategoria.setValorFaturadoEsgoto(helper.getValorFaturadoEsgoto());
						movimentoContaPrefaturadaCategoria.setConsumoFaturadoEsgoto(helper.getConsumoFaturadoEsgoto());
						movimentoContaPrefaturadaCategoria.setValorTarifaMinimaEsgoto(helper.getValorTarifaMinimaEsgoto());
						movimentoContaPrefaturadaCategoria.setConsumoMinimoEsgoto(helper.getConsumoMinimoEsgoto());

						movimentoContaPrefaturadaCategoria.setUltimaAlteracao(new Date());

						// Inserimos
						this.getControladorBatch().inserirObjetoParaBatch(movimentoContaPrefaturadaCategoria);
					}

					movimentoContaPrefaturadaCategoriaIncluido = movimentoContaPrefaturadaCategoria;
					
				// 3. Caso o tipo de registro seja igual a 3
				} else if (helper.getTipoRegistro().equals(AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_3)) {

					Collection<MovimentoContaCategoriaConsumoFaixa> colMovimentoContaCategoriaConsumoFaixa = null;

					if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_CATEGORIA)) {

						FiltroMovimentoContaCategoriaConsumoFaixa filtroMovimentoContaCategoriaConsumoFaixa = new FiltroMovimentoContaCategoriaConsumoFaixa();
						filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
										FiltroMovimentoContaCategoriaConsumoFaixa.MOVIMENTO_CONTA_PREFATURADA_ID,
										movimentoContaPreFaturadaIncluido.getId()));

						filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
										FiltroMovimentoContaCategoriaConsumoFaixa.CATEGORIA_ID,
										helper.getCodigoCategoria()));

						filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
										FiltroMovimentoContaCategoriaConsumoFaixa.LIMITE_INICIAL_CONSUMO_FAIXA,
										helper.getLimiteInicialConsumoFaixa()));

						filtroMovimentoContaCategoriaConsumoFaixa.adicionarParametro(new ParametroSimples(
										FiltroMovimentoContaCategoriaConsumoFaixa.LIMITE_FINAL_CONSUMO_FAIXA,
										helper.getLimiteFinalConsumoFaixa()));

						colMovimentoContaCategoriaConsumoFaixa = this.getControladorUtil()
								.pesquisar(filtroMovimentoContaCategoriaConsumoFaixa,
										MovimentoContaCategoriaConsumoFaixa.class.getName());

					}

					if (colMovimentoContaCategoriaConsumoFaixa != null && !colMovimentoContaCategoriaConsumoFaixa.isEmpty()) {

						// int consumoFaturadoAguaNaFaixa = 0;
						// BigDecimal valorFaturadoAguaNaFaixa = BigDecimal.ZERO;
						//	            		
						// int consumoFaturadoEsgotoNaFaixa = 0;
						// BigDecimal valorFaturadoEsgotoNaFaixa = BigDecimal.ZERO;

						for (MovimentoContaCategoriaConsumoFaixa movimentoContaCategoriaConsumoFaixa : colMovimentoContaCategoriaConsumoFaixa) {

							// Setmos os valores
							BigDecimal valorFaturadoAguaFaixa = movimentoContaCategoriaConsumoFaixa
									.getValorFaturadoAguaNaFaixa().add(helper.getValorFaturadoAguaFaixa());

							Integer consumoFaturadoAguaFaixa = movimentoContaCategoriaConsumoFaixa
									.getConsumoFaturadoAguaNaFaixa()
									+ helper.getConsumoFaturadoAguaFaixa();

							movimentoContaCategoriaConsumoFaixa
									.setConsumoFaturadoAguaNaFaixa(consumoFaturadoAguaFaixa);
							movimentoContaCategoriaConsumoFaixa
									.setValorFaturadoAguaNaFaixa(valorFaturadoAguaFaixa);

							// Setmos os valores
							BigDecimal valorFaturadoEsgotoFaixa = movimentoContaCategoriaConsumoFaixa
									.getValorFaturadoEsgotoNaFaixa()
									.add(helper.getValorFaturadoEsgotoFaixa());

							Integer consumoFaturadoEsgotoFaixa = movimentoContaCategoriaConsumoFaixa
									.getConsumoFaturadoEsgotoNaFaixa()
									+ helper.getConsumoFaturadoEsgotoFaixa();

							BigDecimal valorTarifaFaixa = new BigDecimal("0.00");
							if (helper.getValorTarifaAguaFaixa() != null) {
								valorTarifaFaixa = valorTarifaFaixa.add(helper
										.getValorTarifaAguaFaixa());
							} else {
								valorTarifaFaixa = valorTarifaFaixa.add(helper
										.getValorTarifaEsgotoFaixa());
							}

							movimentoContaCategoriaConsumoFaixa
									.setConsumoFaturadoEsgotoNaFaixa(consumoFaturadoEsgotoFaixa);
							movimentoContaCategoriaConsumoFaixa
									.setValorFaturadoEsgotoNaFaixa(valorFaturadoEsgotoFaixa);
							movimentoContaCategoriaConsumoFaixa
									.setValorTarifaNaFaixa(valorTarifaFaixa);
							movimentoContaCategoriaConsumoFaixa
									.setUltimaAlteracao(new Date());

							// Atualizar
							this.getControladorUtil().atualizar(
									movimentoContaCategoriaConsumoFaixa);

						}
					} else {

						// consumoFaturadoAguaNaFaixa +=
						// helper.getConsumoFaturadoAguaFaixa();
						// /valorFaturadoAguaNaFaixa =
						// valorFaturadoAguaNaFaixa.ad

						MovimentoContaCategoriaConsumoFaixa movimentoContaCategoriaConsumoFaixa = new MovimentoContaCategoriaConsumoFaixa();

						movimentoContaCategoriaConsumoFaixa
								.setMovimentoContaPrefaturadaCategoria(movimentoContaPrefaturadaCategoriaIncluido);

						movimentoContaCategoriaConsumoFaixa
								.setConsumoFaturadoAguaNaFaixa(helper
										.getConsumoFaturadoAguaFaixa());
						movimentoContaCategoriaConsumoFaixa
								.setValorFaturadoAguaNaFaixa(helper
										.getValorFaturadoAguaFaixa());

						movimentoContaCategoriaConsumoFaixa
								.setConsumoFaturadoEsgotoNaFaixa(helper
										.getConsumoFaturadoEsgotoFaixa());
						movimentoContaCategoriaConsumoFaixa
								.setValorFaturadoEsgotoNaFaixa(helper
										.getValorFaturadoEsgotoFaixa());

						movimentoContaCategoriaConsumoFaixa
								.setLimiteInicialConsumoNaFaixa(helper
										.getLimiteInicialConsumoFaixa());
						movimentoContaCategoriaConsumoFaixa
								.setLimiteFinalConsumoNaFaixa(helper
										.getLimiteFinalConsumoFaixa());

						BigDecimal valorTarifaFaixa = new BigDecimal("0.00");
						if (helper.getValorTarifaAguaFaixa() != null) {
							valorTarifaFaixa = valorTarifaFaixa.add(helper
									.getValorTarifaAguaFaixa());
						} else {
							valorTarifaFaixa = valorTarifaFaixa.add(helper
									.getValorTarifaEsgotoFaixa());
						}

						movimentoContaCategoriaConsumoFaixa
								.setUltimaAlteracao(new Date());
						movimentoContaCategoriaConsumoFaixa
								.setValorTarifaNaFaixa(valorTarifaFaixa);

						// Inserimos
						this.getControladorBatch().inserirObjetoParaBatch(
								movimentoContaCategoriaConsumoFaixa);
					}

					// 4. Caso o tipo de registro seja igual a 4.
				} else if (helper.getTipoRegistro().equals(
						AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_4)) {
					MovimentoContaImpostoDeduzido movimentoContaImpostoDeduzido = new MovimentoContaImpostoDeduzido();

					movimentoContaImpostoDeduzido
							.setMovimentoContaPrefaturada(movimentoContaPreFaturadaIncluido);

					// Imposto
					ImpostoTipo impostoTipo = new ImpostoTipo();
					impostoTipo.setId(helper.getTipoImposto());
					movimentoContaImpostoDeduzido.setImpostoTipo(impostoTipo);

					movimentoContaImpostoDeduzido.setDescricaoImposto(helper
							.getDescricaoImposto());
					movimentoContaImpostoDeduzido.setPercentualAliquota(helper
							.getPercentualAliquota());
					movimentoContaImpostoDeduzido.setValorImposto(helper
							.getValorImposto());
					movimentoContaImpostoDeduzido
							.setUltimaAlteracao(new Date());

					// Inserimos
					this.getControladorBatch().inserirObjetoParaBatch(
							movimentoContaImpostoDeduzido);
					// 5. Caso o tipo de registro seja 5, ou seja, atualização
					// do sequencial de rota
				} else if (helper.getTipoRegistro().equals(
						AtualizarContaPreFaturadaHelper.REGISTRO_TIPO_5)) {
					RotaAtualizacaoSeq atuSeq = new RotaAtualizacaoSeq();
					Imovel imo = new Imovel(helper.getMatriculaImovel());

					// Pesquisamos o imovel com a rota
					FiltroImovel filtro = new FiltroImovel();
					filtro.adicionarParametro(new ParametroSimples(
							FiltroImovel.ID, helper.getMatriculaImovel()));
					filtro
							.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");
					Collection<Imovel> colImovel = repositorioUtil.pesquisar(
							filtro, Imovel.class.getName());
					Iterator itImovel = colImovel.iterator();

					// Selecionamos o imovel com os dados necessários
					imo = (Imovel) itImovel.next();

					// Limpamos o que não é mais usado
					colImovel = null;
					itImovel = null;
					filtro = null;

					rota = imo.getQuadra().getRota();

					if(helper.getAnoMesFaturamentoGrupo()!=null && !helper.getAnoMesFaturamentoGrupo().equals("") ){
						anoMesFaturamentoGrupoFaturamento =  Integer.parseInt(helper.getAnoMesFaturamentoGrupo());
					}else{
						// Pesquisamos o ano mes de faturamento do grupo
						anoMesFaturamentoGrupoFaturamento = repositorioFaturamento
								.retornaAnoMesFaturamentoGrupo(imo.getId());
					}
					
					// Setamos as informações
					atuSeq.setImovel(imo);
					atuSeq.setRota(rota);
					atuSeq.setAmFaturamento(anoMesFaturamentoGrupoFaturamento);
					atuSeq.setSequencialRota(Integer.parseInt(helper
							.getSequencialRotaMarcacao()));
					atuSeq.setDtUltimaAlteracao(new Date());

					// Guardamos na colecao
					colAtuSeq.add(atuSeq);
				}
			}

			// Verificamos se a rota foi de marcação
			if (colAtuSeq.size() > 0) {
				// Excluimos, caso existam, os dados anteriores
				repositorioMicromedicao.deletarRotaAtualizacaoSequencial(
						anoMesFaturamentoGrupoFaturamento, rota.getId());

				// Inserimos os dados
				this.getControladorBatch()
						.inserirColecaoObjetoParaBatchSemTransacao(colAtuSeq);
			}

		} catch (MobileComunicationException mce) {
			throw mce;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControladorException("atencao.erro_inserindo_imovel", e,
					matriculaImovel);
		}
	}

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * @author Hugo Amorim, Raphael Rossiter
	 * @date 30/07/2009, 14/01/2010
	 * 
	 * @param idArrecadacaoForma
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCartoes(Integer idArrecadacaoForma)
			throws ControladorException {
		Collection<Cliente> retorno = new ArrayList();

		try {

			Collection colecao = repositorioFaturamento
					.pesquisarCartoes(idArrecadacaoForma);

			Iterator colecaoIt = colecao.iterator();

			while (colecaoIt.hasNext()) {
				Object[] obj = (Object[]) colecaoIt.next();

				Cliente cliente = new Cliente();

				cliente.setId((Integer) obj[0]);
				cliente.setNome((String) obj[1]);

				retorno.add(cliente);

			}

			return retorno;

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Atualiza Dados do Parcelamento para Cartão de Crédito
	 * 
	 * @author Hugo Amorim
	 * @date 31/07/2009
	 * 
	 */
	public void confimarParcelamentoCartaoCredito(Parcelamento parcelamento,
			Collection parcelamentoPagamentoCartaoCreditoCollection,
			Collection debitoACobrar,
			Collection<CreditoARealizar> colecaoCreditoAtualizar,
			Usuario usuario) throws ControladorException {
		try {

			Iterator iterator = debitoACobrar.iterator();

			while (iterator.hasNext()) {

				DebitoACobrar dAc = (DebitoACobrar) iterator.next();

				// ------------ REGISTRAR TRANSAÇÃO ----------------
				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
						Operacao.OPERACAO_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO,
						dAc.getId(), dAc.getId(), new UsuarioAcaoUsuarioHelper(
								usuario,
								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

				registradorOperacao.registrarOperacao(dAc);
				// ------------ REGISTRAR TRANSAÇÃO ----------------

				getControladorUtil().atualizar(dAc);

			}

			Iterator iteratorCreditoAtualizar = colecaoCreditoAtualizar
					.iterator();

			while (iteratorCreditoAtualizar.hasNext()) {

				CreditoARealizar credito = (CreditoARealizar) iteratorCreditoAtualizar
						.next();

				// ------------ REGISTRAR TRANSAÇÃO ----------------
				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
						Operacao.OPERACAO_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO,
						credito.getId(), credito.getId(),
						new UsuarioAcaoUsuarioHelper(usuario,
								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

				registradorOperacao.registrarOperacao(credito);
				// ------------ REGISTRAR TRANSAÇÃO ----------------

				getControladorUtil().atualizar(credito);

			}

			Iterator itera = parcelamentoPagamentoCartaoCreditoCollection
					.iterator();

			while (itera.hasNext()) {

				ParcelamentoPagamentoCartaoCredito parcPagamento = (ParcelamentoPagamentoCartaoCredito) itera
						.next();

				this.repositorioUtil.inserir(parcPagamento);

			}

			this.repositorioUtil.atualizar(parcelamento);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0819] Gerar Historico do Encerramento do Faturamento
	 * 
	 * @author Vivianne Sousa
	 * @date 04/08/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsSetorParaGerarHistoricoParaEncerrarFaturamento()
			throws ControladorException {
		try {
			return repositorioFaturamento
					.pesquisarIdsSetorParaGerarHistoricoParaEncerrarFaturamento();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0926] - Gerar Bônus de Tarifa Social
	 * 
	 * @author Hugo Amorim
	 * @date 25/08/2008
	 * 
	 * @param faturamentoGrupo
	 * @param sistemaParametro
	 * @param colecaoRotas
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarBonusTarifaSocial(FaturamentoGrupo faturamentoGrupo,
			SistemaParametro sistemaParametro, Collection<Rota> colecaoRotas,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;
		BigDecimal valorBonusSocial = new BigDecimal("0.0");

		// -------------------------
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.ROTA,
						((Rota) Util.retonarObjetoDeColecao(colecaoRotas))
								.getId());

		try {

			// **********************************
			// Calculo do valor do Bônus Social
			// **********************************
			Collection collectionCosumoTarifaVigente = this
					.obterConsumoTarifaVigenciaCalcularAguaEsgotoPorMesAno(
							ConsumoTarifa.CONSUMO_NORMAL, null, null,
							faturamentoGrupo.getAnoMesReferencia());

			ConsumoTarifaVigencia consumoTarifaVigencia = (ConsumoTarifaVigencia) Util
					.retonarObjetoDeColecao(collectionCosumoTarifaVigente);

			FiltroConsumoTarifaCategoria filtroConsumoTarifaCategoria = new FiltroConsumoTarifaCategoria();

			filtroConsumoTarifaCategoria
					.adicionarParametro(new ParametroSimples(
							FiltroConsumoTarifaCategoria.CATEGORIA_ID,
							Categoria.RESIDENCIAL));
			filtroConsumoTarifaCategoria
					.adicionarParametro(new ParametroSimples(
							FiltroConsumoTarifaCategoria.CONSUMO_VIGENCIA_ID,
							consumoTarifaVigencia.getId()));

			ConsumoTarifaCategoria consumoTarifaCategoria = (ConsumoTarifaCategoria) this
					.getControladorUtil().pesquisar(
							filtroConsumoTarifaCategoria,
							ConsumoTarifaCategoria.class.getName()).iterator()
					.next();

			valorBonusSocial = (consumoTarifaCategoria.getValorTarifaMinima()
					.multiply(sistemaParametro.getPercentualBonusSocial()))
					.divide(new BigDecimal("100.0"));
			// *****************************************************************

			// Seleciona anoMês de referencia anterior ao mês/ano do faturamento
			Integer anoMes = Util.subtraiAteSeisMesesAnoMesReferencia(
					faturamentoGrupo.getAnoMesReferencia(), 1);
			// *****************************************************************

			// Cria Credito Tipo
			FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
			filtroCreditoTipo.adicionarParametro(new ParametroSimples(
					FiltroCreditoTipo.ID, CreditoTipo.DESCONTO_TARIFA_SOCIAL));
			filtroCreditoTipo
					.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");

			Collection colecaoTipoCredito = this.getControladorUtil()
					.pesquisar(filtroCreditoTipo, CreditoTipo.class.getName());

			CreditoTipo creditoTipo = (CreditoTipo) Util
					.retonarObjetoDeColecao(colecaoTipoCredito);
			// Fim da Criação do Credito Tipo

			if (colecaoRotas != null && !colecaoRotas.isEmpty()) {

				Iterator iteratorRotas = colecaoRotas.iterator();

				// LAÇO PARA FATURAR TODAS AS ROTAS
				while (iteratorRotas.hasNext()) {

					Rota rota = (Rota) iteratorRotas.next();

					// Variáveis para a paginação da pesquisa de Imovel por
					// Grupo Faturamento
					// ========================================================================
					boolean flagTerminou = false;
					final int quantidadeRegistros = 500;
					int numeroIndice = 0;
					// ========================================================================

					while (!flagTerminou) {

						Collection colecaoImovel = this
								.pesquisarImovelGrupoFaturamento(rota,
										numeroIndice, quantidadeRegistros,
										false, false);

						if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

							Iterator iteratorImoveis = colecaoImovel.iterator();

							while (iteratorImoveis.hasNext()) {

								Imovel imovel = (Imovel) iteratorImoveis.next();

								// APAGAR DADOS GERADOS PARA A ROTA
								FiltroCreditoARealizar filtroCreditoARealizar = new FiltroCreditoARealizar();

								filtroCreditoARealizar
										.adicionarParametro(new ParametroSimples(
												FiltroCreditoARealizar.ANO_MES_REFERENCIA_CREDITO,
												anoMes));
								filtroCreditoARealizar
										.adicionarParametro(new ParametroSimples(
												FiltroCreditoARealizar.IMOVEL_ID,
												imovel.getId()));
								filtroCreditoARealizar
										.adicionarParametro(new ParametroSimples(
												FiltroCreditoARealizar.ID_CREDITO_TIPO,
												creditoTipo.getId()));

								Collection colecaoCreditoARealizar = (Collection) this
										.getControladorUtil().pesquisar(
												filtroCreditoARealizar,
												CreditoARealizar.class
														.getName());

								// Caso já tenha sido efetuado o crédito
								// passa pro proximo imóvel.
								// Caso contrario deleto as informações do
								// creditoARealizar,creditoARealizarGeral e
								// CreditoARealizarCategoria
								if (colecaoCreditoARealizar != null
										&& !colecaoCreditoARealizar.isEmpty()) {

									CreditoARealizar credito = (CreditoARealizar) colecaoCreditoARealizar
											.iterator().next();

									if (credito.getNumeroPrestacaoRealizada()
											.compareTo(new Short("1")) == 0) {
										continue;
									} else {

										// Pesquisa os CreditoARealizarCategoria
										// para serem excluidos
										FiltroCreditoARealizarCategoria filtroCreditoARealizarCategoria = new FiltroCreditoARealizarCategoria();

										filtroCreditoARealizarCategoria
												.adicionarParametro(new ParametroSimples(
														FiltroCreditoARealizarCategoria.ID_CREDITO_A_REALIZAR,
														credito.getId()));

										Iterator iteratorCreditoARealizarCategoria = this
												.getControladorUtil()
												.pesquisar(
														filtroCreditoARealizarCategoria,
														CreditoARealizarCategoria.class
																.getName())
												.iterator();

										while (iteratorCreditoARealizarCategoria
												.hasNext()) {
											this.getControladorUtil().remover(
													iteratorCreditoARealizarCategoria
															.next());
										}

										// Pesquisa Credito A Realizar Geral,
										// que possuam o ID igual ao
										// CreditoARealizar
										FiltroCreditoARealizarGeral filtro = new FiltroCreditoARealizarGeral();

										filtro
												.adicionarParametro(new ParametroSimples(
														FiltroCreditoARealizarGeral.ID,
														credito.getId()));

										CreditoARealizarGeral creditoARealizarGeral = (CreditoARealizarGeral) this
												.getControladorUtil()
												.pesquisar(
														filtro,
														CreditoARealizarGeral.class
																.getName())
												.iterator().next();

										// Exclui o creditoARealizar
										this.getControladorUtil().remover(
												credito);

										// Exclui o creditoARealizarGeral
										this.getControladorUtil().remover(
												creditoARealizarGeral);
									}
								}
								// ****************APAGAR DADOS GERADOS PARA A
								// ROTA*****************

								// Fluxo 4.1
								// Verifica se o imovel tem perfil de Tarifa
								// Social,
								// Caso não possua passa para o próximo imóvel.
								if (!imovel.getImovelPerfil().getId().equals(
										ImovelPerfil.TARIFA_SOCIAL)) {
									continue;
								}

								Collection collectionConta = this
										.pesquisarVencimentoConta(imovel
												.getId(), anoMes);

								// FS0002
								// Caso não exista conta com anoMês selecionado,
								// segue para o próximo imóvel.
								if (collectionConta == null
										|| collectionConta.isEmpty()) {
									continue;
								}

								// Gerenciamento dos dados retornados do método
								// pesquisarVencimentoConta().
								Object[] conta = (Object[]) collectionConta
										.iterator().next();
								Integer idConta = new Integer(conta[0]
										.toString());
								Date dataVencimento = (Date) conta[1];

								Collection collectionDataPagamento = this
										.pesquisarDataPagamento(idConta);

								// Fluxo 4.3.2
								// Caso não seja localizado o pagamento,
								// segue para o próximo imóvel
								if (collectionDataPagamento == null
										|| collectionDataPagamento.isEmpty()) {
									continue;
								}

								Object arrayDataPagamento = collectionDataPagamento
										.iterator().next();

								// Caso exista pegamento,
								// Verifica quantidade de dias uteis em relação
								// a data de vencimento
								// Fluxo 4.3.3
								Date dataPagamento = (Date) arrayDataPagamento;

								Municipio municipioImovel = (Municipio) this
										.getControladorGeografico()
										.pesquisarMunicipioDoImovel(
												imovel.getId()).iterator()
										.next();

								Integer diasUteis = this
										.getControladorUtil()
										.calcularDiferencaDiasUteisEntreDuasDatas(
												dataVencimento, dataPagamento,
												municipioImovel);

								// Fluxo 4.3.4
								// Verifica se numero dias e maior que
								// "numeroDiasCalculoAcrescimos"
								if (diasUteis
										.compareTo(new Integer(
												sistemaParametro
														.getNumeroDiasCalculoAcrescimos())) > 0) {
									continue;
								}

								// ******************************
								// INICIO DA GERAÇÃO DO CREDITO
								// ******************************

								Date dataAtual = new Date(System
										.currentTimeMillis());

								// Inclusão do CreditoARealizarGeral
								CreditoARealizarGeral creditoARealizarGeral = new CreditoARealizarGeral();
								creditoARealizarGeral
										.setIndicadorHistorico(new Short("2"));
								creditoARealizarGeral
										.setUltimaAlteracao(dataAtual);

								Object idCreditoARealizarGeral = repositorioUtil
										.inserir(creditoARealizarGeral);

								creditoARealizarGeral.setId(new Integer(
										idCreditoARealizarGeral.toString()));
								// *************Fim****************

								// Inclusão do CreditoARealizar
								CreditoARealizar creditoARealizar = new CreditoARealizar();

								creditoARealizar.setId(new Integer(
										idCreditoARealizarGeral.toString()));
								creditoARealizar.setImovel(imovel);
								creditoARealizar.setCreditoTipo(creditoTipo);
								creditoARealizar.setGeracaoCredito(dataAtual);
								creditoARealizar
										.setAnoMesReferenciaCredito(anoMes);
								creditoARealizar
										.setAnoMesCobrancaCredito(sistemaParametro
												.getAnoMesArrecadacao());

								// Seta AnoMesReferenciaContabil, com maior
								// valor entre o ano/Mês
								// da data corrente e o ano/Mês de referencia do
								// faturamento.
								Integer mesDataAtual = Util.getMes(dataAtual);
								Integer mesArreacadao = new Integer(
										sistemaParametro.getAnoMesFaturamento()
												.toString().substring(4));

								if (mesDataAtual.compareTo(mesArreacadao) > 0) {
									creditoARealizar
											.setAnoMesReferenciaContabil(Util
													.recuperaAnoMesDaData(dataAtual));
								} else {
									creditoARealizar
											.setAnoMesReferenciaContabil(sistemaParametro
													.getAnoMesFaturamento());
								}
								// ***************************************************************

								creditoARealizar
										.setValorCredito(valorBonusSocial);
								creditoARealizar
										.setValorResidualMesAnterior(new BigDecimal(
												"0.0"));
								creditoARealizar
										.setNumeroPrestacaoCredito(new Short(
												"1"));
								creditoARealizar
										.setNumeroPrestacaoRealizada(new Short(
												"0"));
								creditoARealizar.setLocalidade(imovel
										.getLocalidade());
								creditoARealizar.setQuadra(imovel.getQuadra());
								creditoARealizar.setCodigoSetorComercial(imovel
										.getSetorComercial().getCodigo());
								creditoARealizar.setNumeroQuadra(imovel
										.getQuadra().getNumeroQuadra());
								creditoARealizar
										.setNumeroLote(imovel.getLote());
								creditoARealizar.setNumeroSubLote(imovel
										.getSubLote());
								creditoARealizar.setRegistroAtendimento(null);
								creditoARealizar.setOrdemServico(null);
								creditoARealizar
										.setLancamentoItemContabil(creditoTipo
												.getLancamentoItemContabil());
								creditoARealizar
										.setDebitoCreditoSituacaoAtual(new DebitoCreditoSituacao(
												DebitoCreditoSituacao.NORMAL));
								creditoARealizar
										.setDebitoCreditoSituacaoAnterior(null);
								creditoARealizar
										.setCreditoARealizarGeral(creditoARealizarGeral);
								creditoARealizar
										.setCreditoOrigem(new CreditoOrigem(
												CreditoOrigem.DEVOLUCAO_JUROS_PARCELAMENTO));
								creditoARealizar.setUltimaAlteracao(dataAtual);
								creditoARealizar
										.setUsuario(Usuario.USUARIO_BATCH);

								Object idCreditoARealizar = repositorioUtil
										.inserir(creditoARealizar);

								creditoARealizar.setId(new Integer(
										idCreditoARealizar.toString()));
								// *************Fim****************

								// Inclusão do CreditoARealizarCategoria
								// UC0108 - Obter Quantidade de Economias por
								// Categoria
								Collection colecaoCategoriasImovel = this
										.getControladorImovel()
										.obterQuantidadeEconomiasCategoria(
												imovel);

								Iterator iteratorColecaoCategoriasImovel = colecaoCategoriasImovel
										.iterator();

								// UC0185 - Obter Valor por Categoria
								Iterator iteratorColecaoValorPorCategoria = getControladorImovel()
										.obterValorPorCategoria(
												colecaoCategoriasImovel,
												valorBonusSocial).iterator();

								while (iteratorColecaoCategoriasImovel
										.hasNext()
										&& iteratorColecaoValorPorCategoria
												.hasNext()) {

									Categoria categoria = (Categoria) iteratorColecaoCategoriasImovel
											.next();

									BigDecimal valor = (BigDecimal) iteratorColecaoValorPorCategoria
											.next();

									CreditoARealizarCategoria creditoARealizarCategoria = new CreditoARealizarCategoria();

									creditoARealizarCategoria
											.setComp_id(new CreditoARealizarCategoriaPK(
													creditoARealizar.getId(),
													categoria.getId()));
									creditoARealizarCategoria
											.setCreditoARealizar(creditoARealizar);
									creditoARealizarCategoria
											.setCategoria(categoria);
									creditoARealizarCategoria
											.setQuantidadeEconomia(categoria
													.getQuantidadeEconomiasCategoria());
									creditoARealizarCategoria
											.setValorCategoria(valor);
									creditoARealizarCategoria
											.setUltimaAlteracao(dataAtual);

									repositorioUtil
											.inserir(creditoARealizarCategoria);
								}
								// *************Fim****************

								// ******************************
								// FIM DA GERAÇÃO DO CREDITO
								// ******************************

							}// Fim da Iteração dos imoveis
						}

						/**
						 * Incrementa o nº do indice da páginação
						 */
						numeroIndice = numeroIndice + quantidadeRegistros;

						/**
						 * Caso a coleção de imoveis retornados for menor que a
						 * quantidade de registros seta a flag indicando que a
						 * paginação terminou.
						 */
						if (colecaoImovel == null
								|| colecaoImovel.size() < quantidadeRegistros) {

							flagTerminou = true;
						}

						if (colecaoImovel != null) {
							colecaoImovel.clear();
							colecaoImovel = null;
						}
					}// FIM DO LOOP DA PAGINAÇÃO
				}
			} else {
				// A LISTA COM AS ROTAS ESTÁ NULA OU VAZIA
				throw new ControladorException(
						"atencao.pesquisa.grupo_rota_vazio");
			}
			// -----------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// -----------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {
			// Este catch serve para interceptar qualquer exceção que o processo
			// batch venha a lançar e garantir que a unidade de processamento do
			// batch será atualizada com o erro ocorrido
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);
		}

	}

	/**
	 * [UC0187] Inserir Guia de Pagamento
	 * 
	 * [FS0020] Imóvel não possui conta para pagamento parcial
	 * 
	 * @author Raphael Rossiter
	 * @date 12/08/2009
	 * 
	 * @param idImovel
	 * @param idDebitoTipo
	 * @throws ControladorException
	 */
	public Collection obterContasParaPagamentoParcial(Integer idImovel,
			Integer idDebitoTipo) throws ControladorException {

		Collection colecaoRetorno = null;

		// Caso o tipo de débito informado seja “Pagamento Antecipado de Conta”.
		if (idDebitoTipo != null
				&& idDebitoTipo.equals(DebitoTipo.PAGAMENTO_PARCIAL_CONTA)) {

			/*
			 * Não será permitido inserir guia de pagamento com tipo de débito
			 * igual a pagamento parcial de conta quando o imóvel não tiver sido
			 * informado.
			 */
			if (idImovel == null) {

				// [FS0023]
				throw new ControladorException(
						"atencao.imovel_obrigatorio_para_pagamento_parcial");
			}

			Imovel imovel = new Imovel();
			imovel.setId(idImovel);

			/*
			 * O sistema deverá exibir as contas do imóvel selecionado que
			 * estejam na situação de normal, incluída ou retificada e não
			 * esteja paga (CNTA_AMREFERENCIACONTA da tabela CONTA com IMOV_ID =
			 * IMOV_ID do imóvel e DCST_IDATUAL igual a 0, 1 ou 2 e CNTA_ID não
			 * existe na tabela PAGAMENTO).
			 */

			// 1º Passo: Selecionado as contas
			Collection colecaoContaParaPagamentoParcial = this
					.obterContasImovelManter(imovel,
							DebitoCreditoSituacao.NORMAL,
							DebitoCreditoSituacao.INCLUIDA,
							DebitoCreditoSituacao.RETIFICADA);

			if (colecaoContaParaPagamentoParcial == null
					|| colecaoContaParaPagamentoParcial.isEmpty()) {

				// [FS0020] Imóvel não possui conta para pagamento parcial
				throw new ControladorException(
						"atencao.imovel_sem_conta_pagamento_parcial");
			} else {

				Iterator it = colecaoContaParaPagamentoParcial.iterator();

				while (it.hasNext()) {

					Conta conta = (Conta) it.next();

					// Verificando a existência de pagamento para a conta
					// selecionada
					Pagamento pagamento = this.getControladorArrecadacao()
							.pesquisarPagamentoDeConta(conta.getId());

					if (pagamento == null) {

						if (colecaoRetorno == null) {

							colecaoRetorno = new ArrayList();
							colecaoRetorno.add(conta);
						} else {
							colecaoRetorno.add(conta);
						}
					}
				}

				if (colecaoContaParaPagamentoParcial == null
						|| colecaoContaParaPagamentoParcial.isEmpty()) {

					// [FS0020] Imóvel não possui conta para pagamento parcial
					throw new ControladorException(
							"atencao.imovel_sem_conta_pagamento_parcial");
				}
			}
		}

		return colecaoRetorno;
	}

	/**
	 * [UC0187] Inserir Guia de Pagamento
	 * 
	 * [FS0021] Valor informado maior ou igual que valor da conta selecionada.
	 * 
	 * @author Raphael Rossiter
	 * @date 13/08/2009
	 * 
	 * @param idConta
	 * @param idDebitoTipo
	 * @param valorTotalServico
	 * @throws ControladorException
	 */
	public void validarValorTotalServicoParaPagamentoParcial(Integer idConta,
			Integer idDebitoTipo, BigDecimal valorTotalServico)
			throws ControladorException {

		if (idConta != null
				&& valorTotalServico != null
				&& (idDebitoTipo != null && idDebitoTipo
						.equals(DebitoTipo.PAGAMENTO_PARCIAL_CONTA))) {

			// Obtendo os dados da conta selecionada
			Collection colecaoConta = this.obterConta(idConta);

			Conta conta = (Conta) Util.retonarObjetoDeColecao(colecaoConta);

			/*
			 * Caso o valor da guia de pagamento informado pelo usuário for
			 * maior que valor da conta ((CNTA_VLAGUA + CNTA_VLESGOTO +
			 * CNTA_VLDEBITO - CNTA_VLCREDITO - CNTA_VLIMPOSTOS da conta
			 * selecionada) menor ou igual que o valor informado) , exibir a
			 * mensagem “O valor informado deve ser menor que o valor da conta <<((CNTA_VLAGUA +
			 * CNTA_VLESGOTO + CNTA_VLDEBITO - CNTA_VLCREDITO -
			 * CNTA_VLIMPOSTOS>>” e retornar para o passo correspondente no
			 * fluxo principal.
			 */
			if (valorTotalServico.compareTo(conta.getValorTotal()) >= 0) {

				// [FS0021] Valor informado maior ou igual que valor da conta
				// selecionada.
				throw new ControladorException(
						"atencao.valor_guia_maior_valor_conta", null, Util
								.formatarMoedaReal(conta.getValorTotal()));
			}
		}
	}

	/**
	 * Este caso de uso gera os débitos a cobrar referentes aos acréscimos por
	 * impontualidade (multa, juros de mora e atualização monetária)
	 * 
	 * [UC0302] - Gerar Débitos a Cobrar de Acréscimos por Impontualidade Autor:
	 * 
	 * @author Fernanda Paiva, Pedro Alexandre, Pedro Aexandre
	 * @date 20/04/2006, 31/08/2006, 23/04/2008
	 * 
	 * @param rotas
	 * @param indicadorGeracaoMulta
	 * @param indicadorGeracaoJuros
	 * @param indicadorGeracaoAtualizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection gerarDebitosACobrarDeAcrescimosPorImpontualidade(
			Collection rotas, Short indicadorGeracaoMulta,
			Short indicadorGeracaoJuros, Short indicadorGeracaoAtualizacao,
			int idFuncionalidadeIniciada, boolean indicadorEncerrandoArrecadacao)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			Rota rota = (Rota) Util.retonarObjetoDeColecao(rotas);

			// -------------------------
			//
			// Registrar o início do processamento da Unidade de
			// Processamento
			// do Batch
			//
			// -------------------------
			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.ROTA, rota.getId());

			// cria uma coleção de imóvel por rota
			Collection imoveisPorRota = null;
			Collection colecaoDebitoACobrarInserir = new ArrayList();
			Collection colecaoDebitoACobrarCategoriaInserir = new ArrayList();

			// recupera todos os imóveis da coleção de rotas
			imoveisPorRota = this
					.pesquisarImovelGerarAcrescimosImpontualidade(rota);

			SistemaParametro sistemaParametros = getControladorUtil()
					.pesquisarParametrosDoSistema();

			Integer anoMesReferenciaArrecadacao = sistemaParametros
					.getAnoMesArrecadacao();

			Integer anoMesReferenciaFaturamento = sistemaParametros
					.getAnoMesFaturamento();

			Short codigoEmpresaFebraban = sistemaParametros
					.getCodigoEmpresaFebraban();

			Iterator imovelPorRotaIterator = imoveisPorRota.iterator();

			// Item 5.1 [UC0306] - Obter Principal Categoria do Imóvel
			Map<Integer, Categoria> mapImovelPrincipalCategoria = this
					.pesquisarPrincipalCategoriaImovelPorRota(
							codigoEmpresaFebraban, rota);

			/**
			 * Item 5.4 Caso o imóvel possua cliente responsável, recupera o
			 * indicador de cobrança de acrécimos do cliente responsável
			 * (CLIE_ICCOBRANCAACRESCIMOS)
			 */
			Map<Integer, Short> mapIndicadorAcrescimoCliente = this
					.obterIndicadorGeracaoAcrescimosClienteImovel(rota);

			while (imovelPorRotaIterator.hasNext()) {
				// cria um array de objetos para pegar os parametros de
				// retorno da pesquisa
				Object[] arrayImoveisPorRota = (Object[]) imovelPorRotaIterator
						.next();

				// instancia um imóvel
				Imovel imovel = new Imovel();
				if (arrayImoveisPorRota[0] != null) {
					// seta o id no imovel
					imovel.setId((Integer) arrayImoveisPorRota[0]);
				}

				if (arrayImoveisPorRota[4] != null) {
					// seta o lote no imovel
					imovel.setLote((Short) arrayImoveisPorRota[4]);
				}

				if (arrayImoveisPorRota[5] != null) {
					// seta o sublote no imovel
					imovel.setSubLote((Short) arrayImoveisPorRota[5]);
				}

				Localidade localidade = new Localidade();
				if (arrayImoveisPorRota[1] != null) {
					// instancia uma localidade para ser setado no imóvel
					localidade.setId((Integer) arrayImoveisPorRota[1]);
					imovel.setLocalidade(localidade);
				}

				Quadra quadra = new Quadra();
				if (arrayImoveisPorRota[3] != null) {
					// instancia uma quadra para ser setado no imóvel
					Integer numeroQuadra = (Integer) arrayImoveisPorRota[3];
					Integer idQuadra = (Integer) arrayImoveisPorRota[7];
					quadra.setId(idQuadra);
					quadra.setNumeroQuadra(numeroQuadra);
					imovel.setQuadra(quadra);
				}

				Integer setorComercial = null;
				if (arrayImoveisPorRota[2] != null) {
					// instancia um setor comercial para ser setado no imóvel
					setorComercial = (Integer) arrayImoveisPorRota[2];
				}

				/*
				 * Colocado por Raphael Rossiter em 31/05/2007
				 */
				if (arrayImoveisPorRota[8] != null) {
					imovel
							.setIndicadorDebitoConta((Short) arrayImoveisPorRota[8]);
				}

				// Item 5.1 [UC0306] - Obter Principal Categoria do Imóvel
				Categoria principalCategoria = mapImovelPrincipalCategoria
						.get(imovel.getId());

				boolean flagProximoImovel = false;

				/**
				 * Item 5.2 Caso a principal categoria do imóvel esteja
				 * indicando que somente deve ser gerado acréscimos por
				 * impontualidade para a categoria
				 * (catg_icgeracaoacrescimos=NAO) da principal categoria do
				 * imóvel, passa para o próximo imóvel.
				 */
				if (principalCategoria.getIndicadorCobrancaAcrescimos().equals(
						ConstantesSistema.NAO)) {
					flagProximoImovel = true;
				}

				/**
				 * Item 5.3 Caso a principal categoria do imóvel esteja
				 * indicando que não deve ser gerado acréscimos por
				 * impontualidade para a categoria
				 * (catg_icgeracaoacrescimos=ENCERRAMENTO_ARRECADACAO) da
				 * principal categoria do imóvel e esteja indicando que não está
				 * sendo encerrada a arrecadação , passa para o próximo imóvel.
				 */
				if ((principalCategoria != null && principalCategoria
						.getIndicadorCobrancaAcrescimos().equals(
								ConstantesSistema.ENCERRAMENTO_ARRECADACAO))
						&& !indicadorEncerrandoArrecadacao) {
					flagProximoImovel = true;
				}

				/**
				 * Item 5.4 Caso o imóvel possua cliente responsável, recupera o
				 * indicador de cobrança de acrécimos do cliente responsável
				 * (CLIE_ICCOBRANCAACRESCIMOS)
				 */
				Short indicadorCobrancaAcrescimos = mapIndicadorAcrescimoCliente
						.get(imovel.getId());

				/**
				 * Item 5.4.1 Caso esteja indicado que não de ve ser gerado
				 * acrécimos por impontualidade para o cliente
				 * (CLIE_ICCOBRANCAACRESCIMOS=NAO) , passar para o próximo
				 * imóvel
				 */
				if (indicadorCobrancaAcrescimos != null
						&& indicadorCobrancaAcrescimos
								.equals(ConstantesSistema.NAO)) {
					flagProximoImovel = true;
				}

				if (indicadorCobrancaAcrescimos != null
						&& (indicadorCobrancaAcrescimos
								.equals(ConstantesSistema.NAO) && !indicadorEncerrandoArrecadacao)) {
					flagProximoImovel = true;
				}

				if (!flagProximoImovel) {

					Date dataAnoMesReferenciaUltimoDia = Util
							.gerarDataApartirAnoMesRefencia(anoMesReferenciaArrecadacao);

					Collection<Integer> colecaoIdsContasAtualizarIndicadorMulta = new ArrayList();

					// cria uma coleção de contas do imovel
					Collection colecaoContaImovel = null;

					/*
					 * Item 5.5 Caso esteja indicado que NÃO esteja sendo
					 * encerrada a arrecadacão seleciona as contas do imóvel com
					 * ano/mês da data de vencimento menor ou igual ao ano/mês
					 * de referência da arrecadação corrente e com situação
					 * atual correspondente a normal, retificada ou incluída e
					 * que não estejam em revisão e que ainda não tiveram
					 * cobrança de multa.
					 */
					if (!indicadorEncerrandoArrecadacao) {
						// recupera todas as contas dos imóveis da coleção
						// de rotas	
						FiltroParametroSistema filtroParametro = new FiltroParametroSistema();
						filtroParametro.adicionarParametro(new ParametroSimples(FiltroParametroSistema.CODIGO_CONSTANTE, ParametroSistema.REF_INICIAL_COBRANCA_MULTA_CONTAS_VENCIDAS));
						
						Collection colecaoParametroSistema = Fachada.getInstancia().pesquisar(filtroParametro, ParametroSistema.class.getName());
						
						if (colecaoParametroSistema != null && !colecaoParametroSistema.isEmpty()) {
							
							ParametroSistema parametroSistema = (ParametroSistema) Util.retonarObjetoDeColecao(colecaoParametroSistema);
							
							if(parametroSistema.getValorParametro() != null){	 					
								colecaoContaImovel = repositorioFaturamento
									.obterContasImovel(imovel.getId(),
											DebitoCreditoSituacao.NORMAL,
											DebitoCreditoSituacao.INCLUIDA,
											DebitoCreditoSituacao.RETIFICADA,
											dataAnoMesReferenciaUltimoDia,
											parametroSistema);
							}
						}
						
					} else {
						// recupera todas as contas dos imóveis da coleção
						// de rotas
						colecaoContaImovel = repositorioFaturamento
								.obterContasImovelComPagamento(imovel.getId(),
										DebitoCreditoSituacao.NORMAL,
										DebitoCreditoSituacao.INCLUIDA,
										DebitoCreditoSituacao.RETIFICADA,
										dataAnoMesReferenciaUltimoDia,
										anoMesReferenciaArrecadacao);
					}

					Map<Integer, Boolean> mapIndicadorExistePagamentoConta = this
							.pesquisarIndicadorPagamentoConta(
									colecaoContaImovel,
									anoMesReferenciaArrecadacao);

					Short numeroPrestacaoDebito = 1;
					Short numeroPrestacaoCobradas = 0;

					if (!Util.isVazioOrNulo(colecaoContaImovel)) {

						Iterator contasIterator = colecaoContaImovel.iterator();

						while (contasIterator.hasNext()) {
							// cria um array de objetos para pegar os
							// parametros de
							// retorno da pesquisa
							Object[] dadosConta = (Object[]) contasIterator
									.next();

							Integer anoMes = Util
									.recuperaAnoMesDaData((Date) dadosConta[2]);

							if (anoMes <= anoMesReferenciaArrecadacao) {

								Integer idConta = (Integer) dadosConta[0];
								Conta conta = new Conta();
								if (dadosConta[0] != null) {
									// seta o id da conta
									conta.setId((Integer) dadosConta[0]);
								}
								if (dadosConta[1] != null) {
									// seta o ano/mes referencia da
									// conta
									conta
											.setReferencia((Integer) dadosConta[1]);
								}
								if (dadosConta[2] != null) {
									// seta a data de vencimento da
									// conta
									conta
											.setDataVencimentoConta((Date) dadosConta[2]);
								}
								if (dadosConta[3] != null) {
									// seta o valor da água
									conta
											.setValorAgua((BigDecimal) dadosConta[3]);
								}
								if (dadosConta[4] != null) {
									// seta o valor do esgoto
									conta
											.setValorEsgoto((BigDecimal) dadosConta[4]);
								}
								if (dadosConta[5] != null) {
									// seta o valor dos debitos
									conta
											.setDebitos((BigDecimal) dadosConta[5]);
								}
								if (dadosConta[6] != null) {
									// seta o valor dos creditos
									conta
											.setValorCreditos((BigDecimal) dadosConta[6]);
								}
								if (dadosConta[7] != null) {
									// seta o indicador de cobranca da
									// multa
									conta
											.setIndicadorCobrancaMulta((Short) dadosConta[7]);
								}

								// cria uma coleção dos pagamentos da
								// conta com menor
								// data de pagamento
								Date pagamentoContasMenorData = null;
								Integer idArrecadacaoForma = null;

								// recupera todos os pagamentos da conta
								// com menor data de pagamento
								Object[] arrayPagamentoContasMenorData = repositorioFaturamento
										.obterArrecadacaoFormaPagamentoContasMenorData(
												idConta, imovel.getId(), conta
														.getReferencia());

								if (arrayPagamentoContasMenorData != null) {
									idArrecadacaoForma = (Integer) arrayPagamentoContasMenorData[0];
									pagamentoContasMenorData = (Date) arrayPagamentoContasMenorData[1];
								}

								boolean indicadorAcrescimosImovelPerfil = true;
								Short indicadorAcrescimos = null;
								
								try {
									
									indicadorAcrescimos = repositorioImovel.obterIndicadorAcrescimosPorImpontualidadeImovelPerfil(conta.getId());
									
									if ( indicadorAcrescimos != null && indicadorAcrescimos.equals(ConstantesSistema.NAO) ) {
										indicadorAcrescimosImovelPerfil = false;
									}
									
								} catch (ErroRepositorioException e) {
									e.printStackTrace();
								}
								
								/*
								 * Colocado por Raphael Rossiter em 19/05/2008
								 * Só irá calcular o acréscimo caso o imovel e o
								 * pagamento não sejam débito automático
								 */
								if (indicadorAcrescimosImovelPerfil && (idArrecadacaoForma == null
										|| (idArrecadacaoForma != null && !idArrecadacaoForma
												.equals(ArrecadacaoForma.DEBITO_AUTOMATICO)))) {

									boolean indicadorExistePagamentoClassificadoConta;
									// caso tenha o id da conta no map
									// então existe pagamento para a conta
									// atual.
									if (mapIndicadorExistePagamentoConta
											.containsKey(idConta)) {
										indicadorExistePagamentoClassificadoConta = true;
									} else {
										indicadorExistePagamentoClassificadoConta = false;
									}

									CalcularAcrescimoPorImpontualidadeHelper calcularAcrescimoPorImpontualidade = new CalcularAcrescimoPorImpontualidadeHelper();

									BigDecimal valorConta = conta
											.getValorAgua().add(
													conta.getValorEsgoto())
											.add(conta.getDebitos()).subtract(
													conta.getValorCreditos());

									// Calcula o valor das multas cobradas
									// para
									// a conta
									BigDecimal valorMultasCobradas = repositorioFaturamento
											.pesquisarValorMultasCobradas(idConta);

									// Item 5.6.2 Calcular os acrescimos por
									// impontualidade
									calcularAcrescimoPorImpontualidade = this
											.getControladorCobranca()
											.calcularAcrescimoPorImpontualidade(
													conta.getReferencia(),
													conta
															.getDataVencimentoConta(),
													pagamentoContasMenorData,
													valorConta,
													valorMultasCobradas,
													conta
															.getIndicadorCobrancaMulta(),
													""
															+ anoMesReferenciaArrecadacao,
													conta.getId(),
													ConstantesSistema.INDICADOR_ARRECADACAO_ATIVO,
													null);

									DebitoTipo debitoTipo = null;

									/**
									 * Item 5.6.3 Caso o indicador de geração de
									 * multa corresponda a sim(1) e o valor da
									 * multa seja maior que que zero. Gera o
									 * débito a cobrar referente a multa.
									 */
									if (indicadorGeracaoMulta
											.equals(ConstantesSistema.SIM)
											&& calcularAcrescimoPorImpontualidade
													.getValorMulta().compareTo(
															BigDecimal.ZERO) == 1) {

										debitoTipo = new DebitoTipo();
										debitoTipo
												.setId(DebitoTipo.MULTA_IMPONTUALIDADE);

										// [SB0001 - Gerar Débito a Cobrar]
										DebitoACobrar debitoACobrar = this
												.gerarDebitoACobrarParaConta(
														anoMesReferenciaArrecadacao,
														anoMesReferenciaFaturamento,
														imovel,
														localidade,
														quadra,
														setorComercial,
														numeroPrestacaoDebito,
														numeroPrestacaoCobradas,
														conta,
														calcularAcrescimoPorImpontualidade
																.getValorMulta(),
														debitoTipo,
														Usuario.USUARIO_BATCH);

										colecaoIdsContasAtualizarIndicadorMulta
												.add(conta.getId());

										colecaoDebitoACobrarInserir
												.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir
												.addAll(this
														.inserirDebitoACobrarCategoriaBatch(
																debitoACobrar,
																debitoACobrar
																		.getImovel()));
									}// if indicador de geração de multa

									/**
									 * Item 5.6.4 Caso o indicador de geração
									 * dos juros de mora corresponda a sim(1) e
									 * o valor dos juros de mora seja maior que
									 * zero Gera o débito a cobrar referente a
									 * juros de mora e exista pagamento para a
									 * conta com data de pagamento diferente de
									 * nulo e ano/mês referência da arrecadação
									 * do pagamento seja menor ou igual ao
									 * ano/mês de arrecadação corente.
									 */
									if (indicadorGeracaoJuros
											.equals(ConstantesSistema.SIM)
											&& calcularAcrescimoPorImpontualidade
													.getValorJurosMora()
													.compareTo(BigDecimal.ZERO) == 1
											&& indicadorExistePagamentoClassificadoConta) {

										debitoTipo = new DebitoTipo();
										debitoTipo.setId(DebitoTipo.JUROS_MORA);

										// [SB0001 - Gerar Débito a Cobrar]
										DebitoACobrar debitoACobrar = this
												.gerarDebitoACobrarParaConta(
														anoMesReferenciaArrecadacao,
														anoMesReferenciaFaturamento,
														imovel,
														localidade,
														quadra,
														setorComercial,
														numeroPrestacaoDebito,
														numeroPrestacaoCobradas,
														conta,
														calcularAcrescimoPorImpontualidade
																.getValorJurosMora(),
														debitoTipo,
														Usuario.USUARIO_BATCH);

										colecaoIdsContasAtualizarIndicadorMulta
												.add(conta.getId());

										colecaoDebitoACobrarInserir
												.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir
												.addAll(this
														.inserirDebitoACobrarCategoriaBatch(
																debitoACobrar,
																debitoACobrar
																		.getImovel()));
									}

									/*
									 * 5.6.5 Caso o indicador de geração de
									 * atualização monetária corresponda a
									 * sim(1) e o valor da atualização monetária
									 * seja maior que zero Gera o débito a
									 * cobrar referente a atualização monetária
									 * e
									 */
									if (indicadorGeracaoAtualizacao
											.equals(ConstantesSistema.SIM)
											&& calcularAcrescimoPorImpontualidade
													.getValorAtualizacaoMonetaria()
													.compareTo(BigDecimal.ZERO) == 1
											&& indicadorExistePagamentoClassificadoConta) {

										debitoTipo = new DebitoTipo();
										debitoTipo
												.setId(DebitoTipo.ATUALIZACAO_MONETARIA);

										// [SB0001 - Gerar Débito a Cobrar]
										DebitoACobrar debitoACobrar = this
												.gerarDebitoACobrarParaConta(
														anoMesReferenciaArrecadacao,
														anoMesReferenciaFaturamento,
														imovel,
														localidade,
														quadra,
														setorComercial,
														numeroPrestacaoDebito,
														numeroPrestacaoCobradas,
														conta,
														calcularAcrescimoPorImpontualidade
																.getValorAtualizacaoMonetaria(),
														debitoTipo,
														Usuario.USUARIO_BATCH);

										colecaoIdsContasAtualizarIndicadorMulta
												.add(conta.getId());

										colecaoDebitoACobrarInserir
												.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir
												.addAll(this
														.inserirDebitoACobrarCategoriaBatch(
																debitoACobrar,
																debitoACobrar
																		.getImovel()));
									}
								} // fim comparacao debito automatico
							} // fim if da comparação da data de pagamento
						} // fim while contas iterator
					} // fim if coleção conta

					/*
					 * Item 5.6.3.2 Atualiza o indicador de que já cobrou multa
					 * da conta com o valor igual a SIM (CNTA_ICCOBRANCAMULTA=1)
					 */
					if (colecaoIdsContasAtualizarIndicadorMulta != null
							&& !colecaoIdsContasAtualizarIndicadorMulta
									.isEmpty()) {
						repositorioFaturamento
								.atualizarIndicadorMultaDeConta(colecaoIdsContasAtualizarIndicadorMulta);
					}

					// cria uma coleção de guias do imóvel
					Collection colecaoGuiasPagamentoImovel = null;

					Collection<Integer> colecaoIdsGuiasPagamentosAtualizarIndicadorMulta = new ArrayList();

					// recupera todas as guias dos imóveis da coleção de rotas
					colecaoGuiasPagamentoImovel = repositorioFaturamento
							.obterGuiasPagamentoImovel(imovel.getId(),
									DebitoCreditoSituacao.NORMAL,
									DebitoCreditoSituacao.INCLUIDA,
									DebitoCreditoSituacao.RETIFICADA,
									anoMesReferenciaArrecadacao);

					if (!Util.isVazioOrNulo(colecaoGuiasPagamentoImovel)) {

						Iterator guiasPagamentoIterator = colecaoGuiasPagamentoImovel
								.iterator();

						while (guiasPagamentoIterator.hasNext()) {
							// cria um array de objetos para pegar os
							// parametros de
							// retorno da pesquisa
							Object[] dadosGuiasPagamento = (Object[]) guiasPagamentoIterator
									.next();

							Integer anoMes = Util
									.recuperaAnoMesDaData((Date) dadosGuiasPagamento[2]);

							if (anoMes <= anoMesReferenciaArrecadacao) {

								GuiaPagamento guiaPagamento = new GuiaPagamento();
								if (dadosGuiasPagamento[0] != null) {
									// seta o id da guia
									guiaPagamento
											.setId((Integer) dadosGuiasPagamento[0]);
								}
								if (dadosGuiasPagamento[1] != null) {
									// seta o ano/mes referencia da guia
									guiaPagamento
											.setAnoMesReferenciaContabil((Integer) dadosGuiasPagamento[1]);
								}
								if (dadosGuiasPagamento[2] != null) {
									// seta a data de vencimento da
									// conta
									guiaPagamento
											.setDataVencimento((Date) dadosGuiasPagamento[2]);
								}
								if (dadosGuiasPagamento[3] != null) {
									// seta o valor dos debitos
									guiaPagamento
											.setValorDebito((BigDecimal) dadosGuiasPagamento[3]);
								}
								if (dadosGuiasPagamento[4] != null) {
									// seta o indicador de cobranca da
									// multa
									guiaPagamento
											.setIndicadoCobrancaMulta((Short) dadosGuiasPagamento[4]);
								}

								DebitoTipo debitoTipoGuiaPagamento = new DebitoTipo();
								if (dadosGuiasPagamento[5] != null) {
									debitoTipoGuiaPagamento
											.setId((Integer) dadosGuiasPagamento[5]);
									guiaPagamento
											.setDebitoTipo(debitoTipoGuiaPagamento);
								}

								Date menorDataPagamento = repositorioCobranca
										.pesquisarMenorDataPagamentoGuiaPagamento(
												guiaPagamento.getId(), imovel
														.getId(), guiaPagamento
														.getDebitoTipo()
														.getId());

								boolean indicadorExistePagamentoClassificadoGuiaPagamento = repositorioFaturamento
										.obterIndicadorPagamentosClassificadosGuiaPagamentoReferenciaMenorIgualAtual(
												guiaPagamento.getId(), imovel
														.getId(), guiaPagamento
														.getDebitoTipo()
														.getId(),
												anoMesReferenciaArrecadacao);

								// [UC0216] Calcular Acréscimos por
								// Impontualidade
								CalcularAcrescimoPorImpontualidadeHelper calcularAcrescimoPorImpontualidade = new CalcularAcrescimoPorImpontualidadeHelper();
								calcularAcrescimoPorImpontualidade = this
										.getControladorCobranca()
										.calcularAcrescimoPorImpontualidade(
												guiaPagamento
														.getAnoMesReferenciaContabil(),
												guiaPagamento
														.getDataVencimento(),
												menorDataPagamento,
												guiaPagamento.getValorDebito(),
												BigDecimal.ZERO,
												guiaPagamento
														.getIndicadoCobrancaMulta(),
												""
														+ anoMesReferenciaArrecadacao,
												null,
												ConstantesSistema.INDICADOR_ARRECADACAO_ATIVO,
												null);

								DebitoTipo debitoTipo = null;

								/*
								 * Item 5.8.3 Caso o indicador de geração de
								 * multa corresponda a sim(1) e o valor da multa
								 * seja maior que que zero. Gera o débito a
								 * cobrar referente a multa.
								 */
								if (indicadorGeracaoMulta
										.equals(ConstantesSistema.SIM)
										&& calcularAcrescimoPorImpontualidade
												.getValorMulta().compareTo(
														BigDecimal.ZERO) == 1) {

									debitoTipo = new DebitoTipo();
									debitoTipo
											.setId(DebitoTipo.MULTA_IMPONTUALIDADE);

									// [SB0001 - Gerar Débito a Cobrar]
									DebitoACobrar debitoACobrar = gerarDebitoACobrarParaGuiaPagamento(
											anoMesReferenciaArrecadacao,
											anoMesReferenciaFaturamento,
											imovel, localidade, quadra,
											setorComercial,
											numeroPrestacaoDebito,
											numeroPrestacaoCobradas,
											guiaPagamento,
											calcularAcrescimoPorImpontualidade
													.getValorMulta(),
											debitoTipo, Usuario.USUARIO_BATCH);

									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta
											.add(guiaPagamento.getId());

									colecaoDebitoACobrarInserir
											.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir
											.addAll(this
													.inserirDebitoACobrarCategoriaBatch(
															debitoACobrar,
															debitoACobrar
																	.getImovel()));
								}

								/*
								 * Item 5.8.4 Caso o indicador de geração dos
								 * juros de mora corresponda a sim(1) e o valor
								 * dos juros de mora seja maior que zero e
								 * exista pagamento para a guia de pagamento com
								 * situação atual igual a classificado. Gera o
								 * débito a cobrar referente a juros de mora.
								 */
								if (indicadorGeracaoJuros
										.equals(ConstantesSistema.SIM)
										&& calcularAcrescimoPorImpontualidade
												.getValorJurosMora().compareTo(
														BigDecimal.ZERO) == 1
										&& indicadorExistePagamentoClassificadoGuiaPagamento) {

									debitoTipo = new DebitoTipo();
									debitoTipo.setId(DebitoTipo.JUROS_MORA);

									// [SB0001 - Gerar Débito a Cobrar]
									DebitoACobrar debitoACobrar = gerarDebitoACobrarParaGuiaPagamento(
											anoMesReferenciaArrecadacao,
											anoMesReferenciaFaturamento,
											imovel, localidade, quadra,
											setorComercial,
											numeroPrestacaoDebito,
											numeroPrestacaoCobradas,
											guiaPagamento,
											calcularAcrescimoPorImpontualidade
													.getValorJurosMora(),
											debitoTipo, Usuario.USUARIO_BATCH);

									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta
											.add(guiaPagamento.getId());

									colecaoDebitoACobrarInserir
											.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir
											.addAll(this
													.inserirDebitoACobrarCategoriaBatch(
															debitoACobrar,
															debitoACobrar
																	.getImovel()));
								}

								/*
								 * Item 5.8.5 Caso o indicador de geração de
								 * atualização monetária corresponda a sim(1) e
								 * o valor da atualização monetária seja maior
								 * que zero e exista pagamento para a guia de
								 * pagamento com situação atual igual a
								 * classificado. Gera o débito a cobrar
								 * referente a atualização monetária.
								 */
								if (indicadorGeracaoAtualizacao
										.equals(ConstantesSistema.SIM)
										&& calcularAcrescimoPorImpontualidade
												.getValorAtualizacaoMonetaria()
												.compareTo(BigDecimal.ZERO) == 1
										&& indicadorExistePagamentoClassificadoGuiaPagamento) {

									debitoTipo = new DebitoTipo();
									debitoTipo
											.setId(DebitoTipo.ATUALIZACAO_MONETARIA);

									// [SB0001 - Gerar Débito a Cobrar]
									DebitoACobrar debitoACobrar = gerarDebitoACobrarParaGuiaPagamento(
											anoMesReferenciaArrecadacao,
											anoMesReferenciaFaturamento,
											imovel,
											localidade,
											quadra,
											setorComercial,
											numeroPrestacaoDebito,
											numeroPrestacaoCobradas,
											guiaPagamento,
											calcularAcrescimoPorImpontualidade
													.getValorAtualizacaoMonetaria(),
											debitoTipo, Usuario.USUARIO_BATCH);

									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta
											.add(guiaPagamento.getId());

									colecaoDebitoACobrarInserir
											.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir
											.addAll(this
													.inserirDebitoACobrarCategoriaBatch(
															debitoACobrar,
															debitoACobrar
																	.getImovel()));
								}
							} // fim if da comparacao da data de
							// pagamento
						} // fim while contasiterator
					} // fim if colecaoguia

					if (colecaoIdsGuiasPagamentosAtualizarIndicadorMulta != null
							&& !colecaoIdsGuiasPagamentosAtualizarIndicadorMulta
									.isEmpty()) {
						repositorioFaturamento
								.atualizarIndicadorMultaDeGuiaPagamento(colecaoIdsGuiasPagamentosAtualizarIndicadorMulta);
					}
				} // fim if flagProximoImovel
			}// fim while imovelporrotaiterator

			// Inserir os débitos a cobrar
			if (colecaoDebitoACobrarInserir != null
					&& !colecaoDebitoACobrarInserir.isEmpty()) {
				this.getControladorBatch().inserirColecaoObjetoParaBatch(
						colecaoDebitoACobrarInserir);
			}

			// Inseri os débitos a cobrar por categoria
			if (colecaoDebitoACobrarCategoriaInserir != null
					&& !colecaoDebitoACobrarCategoriaInserir.isEmpty()) {
				this.getControladorBatch().inserirColecaoObjetoParaBatch(
						colecaoDebitoACobrarCategoriaInserir);
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

			return null;

		} catch (Exception e) {
			// Este catch serve para interceptar qualquer exceção que o processo
			// batch venha a lançar e garantir que a unidade de processamento do
			// batch será atualizada com o erro ocorrido
			e.printStackTrace();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);

			throw new EJBException(e);
		}

	}

	/**
	 * Metodo que retorna os imóveis das quadras pertencentes às rotas
	 * 
	 * Utilizado pelo [UC0302] Gerar Débitos a Cobrar de Acréscimos por
	 * Impontualidade
	 * 
	 * @author Raphael Rossiter
	 * @date 26/08/2009
	 * 
	 * @param idRota
	 * @return Collection
	 */
	public Collection pesquisarImovelGerarAcrescimosImpontualidade(Rota rota)
			throws ControladorException {

		Collection colecaoImoveis = null;

		/*
		 * Caso a rota não esteja com o indicador de rota alternativa ativo; a
		 * pesquisa dos imóveis será feita a partir de sua quadra.
		 */
		if (!rota.getIndicadorRotaAlternativa().equals(ConstantesSistema.SIM)) {

			try {

				colecaoImoveis = repositorioFaturamento
						.pesquisarImoveisDasQuadrasPorRota(rota.getId());

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		/*
		 * Caso contrário; a pesquisa dos imóveis será feita a partir da rota
		 * alternativa que estará associada ao mesmo.
		 */
		else {

			try {

				colecaoImoveis = repositorioFaturamento
						.pesquisarImoveisDasQuadrasPorRotaAlternativa(rota
								.getId());

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}

		return colecaoImoveis;
	}

	/**
	 * Permite gerar os débitos de doações para os imóveis contidos na coleção
	 * [UC0394] Gerar Débitos a Cobrar de Doações
	 * 
	 * @author César Araújo, Raphael Rossiter
	 * @date 05/08/2006, 26/08/2009
	 * 
	 * @param Collection
	 *            <Rota> rotas
	 * @param int
	 *            idFuncionalidadeIniciada
	 * 
	 * @throws ControladorException
	 */
	public void gerarDebitoACobrarDoacao(Collection<Rota> rotas,
			int idFuncionalidadeIniciada) throws ControladorException {

		// -------------------------
		/*
		 * Registrar o início do processamento da Unidade de Processamento do
		 * Batch
		 * 
		 * Colocado por Raphael Rossiter em 11/01/2007
		 */
		// -------------------------
		int idUnidadeIniciada = 0;

		Rota rota = (Rota) Util.retonarObjetoDeColecao(rotas);

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.ROTA,
						((Rota) Util.retonarObjetoDeColecao(rotas)).getId());

		try {

			/** * Declara variáveis locais ** */
			Imovel imovel = null;
			Quadra quadra = null;
			DebitoTipo debitoTipo = null;
			Localidade localidade = null;
			CobrancaForma cobrancaForma = null;
			DebitoACobrar debitoACobrar = null;
			Integer idDebitoACobrarGeral = null;
			FinanciamentoTipo financiamentoTipo = null;
			DebitoACobrarGeral debitoACobrarGeral = null;
			DebitoCreditoSituacao debitoCreditoSituacao = null;
			LancamentoItemContabil lancamentoItemContabil = null;
			Collection<ImovelCobrarDoacaoHelper> colecaoImovelCobrarDoacaoHelper = null;

			/**
			 * Pesquisa Imoveis que tem doação a faturar baseando-se numa
			 * coleção de rotas *
			 */

			// Parte Alterada por Sávio Luiz Data:09/05/2007
			// Parte que remove os débitos a cobrar, do ano mes de faturamento
			// para as rotas recebidas, caso já exista na base.
			/*
			 * SistemaParametro sistemaParametro = getControladorUtil()
			 * .pesquisarParametrosDoSistema();
			 */
			Integer anoMesFaturamentoGrupo = repositorioFaturamento
					.retornaAnoMesFaturamentoGrupoDaRota(rota.getId());

			Collection colecaoIdsDebitoACobrar = repositorioFaturamento
					.pesquisarDebitoACobrarParaRemocao(rota,
							anoMesFaturamentoGrupo);

			if (colecaoIdsDebitoACobrar != null
					&& !colecaoIdsDebitoACobrar.isEmpty()) {

				repositorioFaturamento
						.deletarDebitosACobrarCategoria(colecaoIdsDebitoACobrar);

				getControladorCobranca().removerDocumentosItensDebitoACobrar(
						colecaoIdsDebitoACobrar);

				repositorioFaturamento
						.deletarDebitosACobrar(colecaoIdsDebitoACobrar);

			}

			colecaoImovelCobrarDoacaoHelper = this
					.pesquisarImovelDoacaoPorRota(rota);

			for (ImovelCobrarDoacaoHelper imovelCobrarDoacaoHelper : colecaoImovelCobrarDoacaoHelper) {

				/*
				 * Colocado por Hugo Amorim em 07/04/2010
				 * 
				 * OBJ: Verificar a existência de débito a cobrar de doação
				 * ativo para o imóvel.
				 * 
				 * Caso já exista débito a cobrar de doação ativo para o imóvel
				 * o sistema deverá passar para o próximo imóvel
				 */

				Collection colecaoDebitosACobrarDeDoacaoAtivos = repositorioFaturamento
						.pesquisarDebitoACobrarDeDoacaoAtivos(
								imovelCobrarDoacaoHelper.getIdImovel(),
								anoMesFaturamentoGrupo,
								imovelCobrarDoacaoHelper.getIdDebitoTipo());

				if (colecaoDebitosACobrarDeDoacaoAtivos == null
						|| colecaoDebitosACobrarDeDoacaoAtivos.isEmpty()) {

					if (imovelCobrarDoacaoHelper.getAnoMesReferenciaFinal() != null) {
						this.efetuarCancelamentoImovelParaDoacoes(
								imovelCobrarDoacaoHelper, rota);
					}

					/** * Instancia debitoACobrarGeral ** */
					debitoACobrarGeral = new DebitoACobrarGeral();

					/** * preenche os valores para debitoACobrarGeral ** */
					debitoACobrarGeral
							.setIndicadorHistorico(ImovelCobrarDoacaoHelper.INDICADOR_HISTORICO);
					debitoACobrarGeral.setUltimaAlteracao(new Date());

					/** * insere debitoACobrarGeral na base ** */
					idDebitoACobrarGeral = repositorioFaturamento
							.inserirDebitoACobrarGeral(debitoACobrarGeral);

					debitoACobrarGeral.setId(idDebitoACobrarGeral);

					/** * Instancia debitoACobrar ** */
					debitoACobrar = new DebitoACobrar();

					/** * preenche os valores para debitoACobrar ** */
					debitoACobrar.setId(idDebitoACobrarGeral);
					debitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);

					imovel = new Imovel();
					imovel.setId(imovelCobrarDoacaoHelper.getIdImovel());
					debitoACobrar.setImovel(imovel);

					debitoTipo = new DebitoTipo();
					debitoTipo
							.setId(imovelCobrarDoacaoHelper.getIdDebitoTipo());
					debitoACobrar.setDebitoTipo(debitoTipo);

					debitoACobrar.setGeracaoDebito(new Date());
					debitoACobrar.setAnoMesReferenciaDebito(null);
					debitoACobrar.setAnoMesCobrancaDebito(null);

					// alterado por: Rômulo Aurélio CRC 4662 Data:21/06/2010
					// analista responsavel: Eduardo Borges

					int anoMesReferenciaContabil = anoMesFaturamentoGrupo;

					int anoMesCorrente = Util.getAnoMesComoInt(new Date());

					// anoMesReferenciaContabil recebe o maior valor entre
					// ano/mes da data corrente
					// e o ano/mes de referencia do faturamento
					if (anoMesFaturamentoGrupo < anoMesCorrente) {
						anoMesReferenciaContabil = anoMesCorrente;
					}

					debitoACobrar
							.setAnoMesReferenciaContabil(anoMesReferenciaContabil);

					// Alterado por Rômulo Aurélio
					// Data: 05/10/2010
					// Analista: Eduardo Borges
					debitoACobrar
							.setAnoMesReferenciaDebito(anoMesFaturamentoGrupo);

					/*
					 * Caso exista mês/ano de referência inicial e final da
					 * doação (IMDO_AMREFERENCIAINICAL<> NULL e
					 * IMDO_AMREFERENCIAFINAL<>NULL), calcular a quantidade de
					 * meses entre as referências mais um
					 * (DBAC_NNPRESTACAODEBITO= IMDO_AMREFERENCIAFINAL -
					 * IMDO_AMREFERENCIAINICAL + 1) , caso contrário, mover um
					 * (DBAC_NNPRESTACAODEBITO=1)
					 */
					Short numeroPrestacaoDebito = ImovelCobrarDoacaoHelper.NUMERO_PRESTACAO_DEBITO;

					if (imovelCobrarDoacaoHelper.getAnoMesReferenciaInicial() != null
							&& imovelCobrarDoacaoHelper
									.getAnoMesReferenciaFinal() != null) {
						Integer qtdMeses = Util.retornaQuantidadeMeses(
								imovelCobrarDoacaoHelper
										.getAnoMesReferenciaFinal(),
								imovelCobrarDoacaoHelper
										.getAnoMesReferenciaInicial());
						qtdMeses = qtdMeses + 1;

						numeroPrestacaoDebito = Short.parseShort(qtdMeses
								.toString());
					}

					debitoACobrar
							.setNumeroPrestacaoDebito(numeroPrestacaoDebito);

					debitoACobrar.setValorDebito(imovelCobrarDoacaoHelper
							.getValorDebito().multiply(
									new BigDecimal(numeroPrestacaoDebito)));

					/*
					 * Caso exista mês/ano de referência inicial e final da
					 * doação (IMDO_AMREFERENCIAINICAL<> NULL e
					 * IMDO_AMREFERENCIAFINAL<>NULL), calcular a quantidade de
					 * meses entre a referência inicial e a referencia atual do
					 * grupo de faturamento da rota (DBAC_NNPRESTACAOCOBRADAS=
					 * FTGR_AMREFERENCIA - IMDO_AMREFERENCIAINICAL), caso
					 * contrário, mover zero (DBAC_NNPRESTACAOCOBRADAS=0)
					 */
					Short numeroPrestacaoCobradas = ImovelCobrarDoacaoHelper.NUMERO_PRESTACAO_COBRADA;

					if (imovelCobrarDoacaoHelper.getAnoMesReferenciaInicial() != null
							&& imovelCobrarDoacaoHelper
									.getAnoMesReferenciaFinal() != null) {

						FiltroRota filtroRota = new FiltroRota();

						filtroRota.adicionarParametro(new ParametroSimples(
								FiltroRota.ID_ROTA, rota.getId()));

						filtroRota
								.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);

						FaturamentoGrupo faturamentoGrupo = ((Rota) this
								.getControladorUtil().pesquisar(filtroRota,
										Rota.class.getName()).iterator().next())
								.getFaturamentoGrupo();

						Integer qtdMeses = Util.retornaQuantidadeMeses(
								faturamentoGrupo.getAnoMesReferencia(),
								imovelCobrarDoacaoHelper
										.getAnoMesReferenciaInicial());

						numeroPrestacaoCobradas = Short.parseShort(qtdMeses
								.toString());
					}

					debitoACobrar
							.setNumeroPrestacaoCobradas(numeroPrestacaoCobradas);

					localidade = new Localidade();
					localidade
							.setId(imovelCobrarDoacaoHelper.getIdLocalidade());
					debitoACobrar.setLocalidade(localidade);

					quadra = new Quadra();
					quadra.setId(imovelCobrarDoacaoHelper.getIdQuadra());
					debitoACobrar.setQuadra(quadra);

					debitoACobrar
							.setCodigoSetorComercial(imovelCobrarDoacaoHelper
									.getCodigoSetorComercial());
					debitoACobrar.setNumeroQuadra(imovelCobrarDoacaoHelper
							.getNumeroQuadra());
					debitoACobrar.setNumeroLote(imovelCobrarDoacaoHelper
							.getNumeroLote());
					debitoACobrar.setNumeroSubLote(imovelCobrarDoacaoHelper
							.getNumeroSubLote());
					debitoACobrar
							.setPercentualTaxaJurosFinanciamento(ImovelCobrarDoacaoHelper.TAXA_JURO_FINANCIAMENTO);
					debitoACobrar.setRegistroAtendimento(null);
					debitoACobrar.setOrdemServico(null);

					financiamentoTipo = new FinanciamentoTipo();
					financiamentoTipo.setId(imovelCobrarDoacaoHelper
							.getFinanciamentoTipo());
					debitoACobrar.setFinanciamentoTipo(financiamentoTipo);

					lancamentoItemContabil = new LancamentoItemContabil();
					lancamentoItemContabil.setId(imovelCobrarDoacaoHelper
							.getLancamentoItemContabil());
					debitoACobrar
							.setLancamentoItemContabil(lancamentoItemContabil);

					debitoCreditoSituacao = new DebitoCreditoSituacao();
					debitoCreditoSituacao
							.setId(ImovelCobrarDoacaoHelper.DEBITO_CREDITO_SITUACAO_ATUAL);
					debitoACobrar
							.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
					debitoACobrar.setDebitoCreditoSituacaoAnterior(null);
					debitoACobrar.setParcelamentoGrupo(null);

					cobrancaForma = new CobrancaForma();
					cobrancaForma
							.setId(ImovelCobrarDoacaoHelper.COBRANCA_FORMA);
					debitoACobrar.setCobrancaForma(cobrancaForma);
					debitoACobrar.setUltimaAlteracao(new Date());

					/** * Insere debitoACobrar na base ** */
					repositorioFaturamento.inserirDebitoACobrar(debitoACobrar);

					/** * Insere debitoACobrarCategoria ** */
					this.inserirDebitoACobrarCategoria(debitoACobrar, imovel);
				}
			}

			// --------------------------------------------------------
			// Registrar o fim da execução da Unidade de Processamento
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {

			/*
			 * Este catch serve para interceptar qualquer exceção que o processo
			 * batch venha a lançar e garantir que a unidade de processamento do
			 * batch será atualizada com o erro ocorrido
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);

			throw new EJBException(e);
		}
	}

	/**
	 * Permite pesquisar imóvel doação baseando-se em rotas [UC0394] Gerar
	 * Débitos a Cobrar de Doações
	 * 
	 * @author Raphael Rossiter
	 * @date 26/08/2008
	 * 
	 * @param idRota
	 * @return Collection<ImovelCobrarDoacaoHelper> - Coleção de
	 *         ImovelCobrarDoacaoHelper já com as informações necessárias para
	 *         registro da cobrança
	 * @throws ControladorException
	 */
	public Collection pesquisarImovelDoacaoPorRota(Rota rota)
			throws ControladorException {

		Collection colecaoImoveis = null;

		/*
		 * Caso a rota não esteja com o indicador de rota alternativa ativo; a
		 * pesquisa dos imóveis será feita a partir de sua quadra.
		 */
		if (!rota.getIndicadorRotaAlternativa().equals(ConstantesSistema.SIM)) {

			try {

				colecaoImoveis = repositorioFaturamento
						.pesquisarImovelDoacaoPorRota(rota.getId());

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		/*
		 * Caso contrário; a pesquisa dos imóveis será feita a partir da rota
		 * alternativa que estará associada ao mesmo.
		 */
		else {

			try {

				colecaoImoveis = repositorioFaturamento
						.pesquisarImovelDoacaoPorRotaAlternativa(rota.getId());

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}

		return colecaoImoveis;
	}

	/**
	 * 
	 * @author Hugo Amorim
	 * @date 26/08/2009
	 * @param idImovel
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarVencimentoConta(Integer idImovel,
			Integer anoMesReferencia) throws ControladorException {
		Collection retorno;

		try {

			retorno = this.repositorioFaturamento.pesquisarVencimentoConta(
					idImovel, anoMesReferencia);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * Retorna a qualidade de água associada ao imóvel
	 * 
	 * @author Rafael Corrêa
	 * @date 07/09/2009
	 * @param imovel
	 * @return QualidadeAgua
	 * @throws ControladorException
	 */
	public QualidadeAgua getQualidadeAgua(Imovel imovel, Integer anoMes)
			throws ControladorException {

		QualidadeAgua retorno = null;

		FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();
		filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
				FiltroQualidadeAgua.LOCALIDADE_ID, imovel.getLocalidade()
						.getId()));
		filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
				FiltroQualidadeAgua.SETOR_COMERCIAL_CODIGO, imovel
						.getSetorComercial().getCodigo()));
		filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
				FiltroQualidadeAgua.ANO_MES_REFERENCIA, anoMes));

		Collection colecaoQualidadeAgua = getControladorUtil().pesquisar(
				filtroQualidadeAgua, QualidadeAgua.class.getName());

		if (colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()) {
			retorno = (QualidadeAgua) Util
					.retonarObjetoDeColecao(colecaoQualidadeAgua);
		} else {
			filtroQualidadeAgua.limparListaParametros();
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.LOCALIDADE_ID, imovel.getLocalidade()
							.getId()));
			filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
					FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
					FiltroQualidadeAgua.ANO_MES_REFERENCIA, anoMes));

			colecaoQualidadeAgua = getControladorUtil().pesquisar(
					filtroQualidadeAgua, QualidadeAgua.class.getName());

			if (colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()) {
				retorno = (QualidadeAgua) Util
						.retonarObjetoDeColecao(colecaoQualidadeAgua);
			} else {
				filtroQualidadeAgua.limparListaParametros();
				filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
						FiltroQualidadeAgua.LOCALIDADE_ID));
				filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
						FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
				filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
						FiltroQualidadeAgua.ANO_MES_REFERENCIA, anoMes));

				colecaoQualidadeAgua = getControladorUtil().pesquisar(
						filtroQualidadeAgua, QualidadeAgua.class.getName());

				if (colecaoQualidadeAgua != null
						&& !colecaoQualidadeAgua.isEmpty()) {
					retorno = (QualidadeAgua) Util
							.retonarObjetoDeColecao(colecaoQualidadeAgua);
				}
			}
		}

		return retorno;
	}

	/**
	 * 
	 * @author Hugo Amorim
	 * @date 26/08/2009
	 * @param idConta
	 * @return dataPagamento
	 */
	public Collection pesquisarDataPagamento(Integer idContal)
			throws ControladorException {
		Collection retorno;
		try {

			retorno = this.repositorioFaturamento
					.pesquisarDataPagamento(idContal);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;

	}

	/**
	 * Atualiza um conjunto de leituras e anormalidades bem como seu consumo e
	 * suas contas prefaturadas
	 * 
	 * @author Bruno Barros
	 * @date 09/09/2009
	 * @param buffer -
	 *            BufferedReader com o arquivo selecionado
	 * @return void
	 * @throws ControladorException
	 */
	public RetornoAtualizarFaturamentoMovimentoCelularHelper atualizarFaturamentoMovimentoCelular(
			BufferedReader buffer, String nomeArquivo, boolean offline,
			boolean finalizarArquivo, int indicadorAndroid) throws ControladorException {

		RetornoAtualizarFaturamentoMovimentoCelularHelper retorno = new RetornoAtualizarFaturamentoMovimentoCelularHelper();

		Object[] retornoIncluirMovimento = null;
		Collection<AtualizarContaPreFaturadaHelper> colecaoAtualizarContaPreFaturadaHelper = null;
		byte[] relatorio = null;

		// FS0002 - Verificar existencia de dados no arquivo
		if (buffer == null) {
			throw new ControladorException("atencao.arquivo_sem_dados", null,
					nomeArquivo);
		}

		// <<Inclui>> [UC0923] - Incluir Movimento Conta Pre-faturada
		try {
			// Verificamos se algum erro de comunição previsto aconteceu.
			// Caso positivo, incluimos a mensagem retornada no retorno da
			// função.
			retornoIncluirMovimento = this
					.incluirMovimentoContaPreFaturada(buffer, indicadorAndroid, offline);
		} catch (MobileComunicationException mce) {
			if (offline) {
				throw new ControladorException(mce.getMessage(), mce);
			} else {
				// Setamos e retornamos
				mce.printStackTrace();
				retorno.setMensagemComunicacaoServidorCelular("mensagem="
						+ ConstantesAplicacao.get(mce.getMessage()));
				return retorno;
			}
		}

		try {
			Collection<MovimentoContaPrefaturada> colContaPreFaturada = new ArrayList();
			Collection<Integer> colIdsImoveisAtualizar = new ArrayList();
			
			if (!Util.isVazioOrNulo(retornoIncluirMovimento)) {
				
				relatorio = (byte[]) retornoIncluirMovimento[0];
				colecaoAtualizarContaPreFaturadaHelper = (Collection) retornoIncluirMovimento[1];
				
			} else {
				
				retorno.setRelatorioConsistenciaProcessamento(null);
				retorno.setIndicadorSucessoAtualizacao(true);
				return retorno;
			}
			

			if (relatorio != null) {
				retorno.setRelatorioConsistenciaProcessamento(relatorio);
				retorno.setIndicadorSucessoAtualizacao(false);
				return retorno;
			}
			
			
			

			// Pesquisamos a rota que utilizaremos na consistencia dos dados
			Rota rota = null;

			if (colecaoAtualizarContaPreFaturadaHelper != null
					&& !colecaoAtualizarContaPreFaturadaHelper.equals("")) {

				for (AtualizarContaPreFaturadaHelper helperCabecalho : colecaoAtualizarContaPreFaturadaHelper) {
					// caso ja tenha a rota, então não pesquisa mais
					if (rota == null || rota.equals("")) {
						rota = pesquisarRotaImpressaoSimultanea(helperCabecalho);
					}

					// 2, 3
					// FS0003
					Collection<MovimentoContaPrefaturada> colMovimentoContaPreFaturada = verificarExistenciaListaMovimentoContaPrefaturada(helperCabecalho);
					if (colMovimentoContaPreFaturada != null
							&& !colMovimentoContaPreFaturada.isEmpty()) {
						colContaPreFaturada
								.addAll(colMovimentoContaPreFaturada);

						for (MovimentoContaPrefaturada prefaturada : colMovimentoContaPreFaturada) {
							colIdsImoveisAtualizar.add(prefaturada.getImovel()
									.getId());
						}
					}
				}
				
			}

			this.processarMovimentoContaPrefaturada(rota, colContaPreFaturada, true, false);
			

			// SB0001
			if (offline) {
				relatorio = this
						.geraResumoLeiturasAnormalidadesImpressaoSimultanea(colContaPreFaturada);
				if(relatorio != null){
					retorno.setRelatorioConsistenciaProcessamento(relatorio);
				}
			}

			// Verificamos se já foi enviado algum tipo de mensagem
			// nessa requisição
			if (rota != null) {
				// Caso não seja finalizar a rota, então verifica se tem
				// releitura para a rota
				if (!finalizarArquivo) {
					if (retorno.getMensagemComunicacaoServidorCelular() == null) {
						String releituraImoveis = this
								.verificarSolicitacaoReleituraImovelImpressaoSimultanea(rota
										.getId());

						if (releituraImoveis != null) {
							retorno
									.setMensagemComunicacaoServidorCelular(releituraImoveis);
						}
					}
				}

				// Verificamos se algum imóvel retornado teve sua releitura
				// atendida
				Integer anoMesFaturamentoGrupoRota = this
						.retornaAnoMesFaturamentoGrupoDaRota(rota.getId());
		
				Collection<ReleituraMobile> colReleituraMobile = repositorioFaturamento.pesquisarIDImoveis(colIdsImoveisAtualizar, 
											rota.getId(), anoMesFaturamentoGrupoRota, ConstantesSistema.NAO);

				if (colReleituraMobile != null && colReleituraMobile.size() > 0) {
					for (ReleituraMobile mobile : colReleituraMobile) {

						// Pegamos as leituras atuais
						FiltroMovimentoContaPrefaturada filtroMovimentoContaPrefaturada = new FiltroMovimentoContaPrefaturada();
						filtroMovimentoContaPrefaturada
								.adicionarParametro(new ParametroSimples(
										FiltroMovimentoContaPrefaturada.MATRICULA,
										mobile.getImovel().getId()));
						filtroMovimentoContaPrefaturada
								.adicionarParametro(new ParametroSimples(FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO,
										anoMesFaturamentoGrupoRota));

						Collection<MovimentoContaPrefaturada> colMovimentoContaPrefaturada = getControladorUtil()
								.pesquisar(
										filtroMovimentoContaPrefaturada,
										MovimentoContaPrefaturada.class
												.getName());

						Integer leituraAgua = null;
						Integer leituraPoco = null;

						LeituraAnormalidade leituraAnormalidadeAgua = null;
						LeituraAnormalidade leituraAnormalidadePoco = null;

						for (MovimentoContaPrefaturada prefaturada : colMovimentoContaPrefaturada) {
							if (prefaturada.getMedicaoTipo().getId().intValue() == MedicaoTipo.LIGACAO_AGUA) {
								leituraAgua = prefaturada
										.getLeituraHidrometro();
								leituraAnormalidadeAgua = prefaturada
										.getLeituraAnormalidadeLeitura();
							} else {
								leituraPoco = prefaturada
										.getLeituraHidrometro();
								leituraAnormalidadePoco = prefaturada
										.getLeituraAnormalidadeLeitura();
							}
						}

						mobile.setLeituraAnteriorAgua(mobile
								.getLeituraAtualAgua());
						mobile.setLeituraAnteriorPoco(mobile
								.getLeituraAtualPoco());
						mobile.setLeituraAnormalidadeAnteriorAgua(mobile
								.getLeituraAnormalidadeAtualAgua());
						mobile.setLeituraAnormalidadeAnteriorPoco(mobile
								.getLeituraAnormalidadeAtualPoco());

						mobile.setLeituraAtualAgua(leituraAgua);
						mobile.setLeituraAtualPoco(leituraPoco);
						mobile
								.setLeituraAnormalidadeAtualAgua(leituraAnormalidadeAgua);
						mobile
								.setLeituraAnormalidadeAtualPoco(leituraAnormalidadePoco);

						mobile.setIndicadorReleitura(new Integer(
								ConstantesSistema.SIM));
						mobile.setUltimaAlteracao(new Date());
					}

					this.getControladorBatch().atualizarColecaoObjetoParaBatch(
							colReleituraMobile);
				}
			}

			retorno.setIndicadorSucessoAtualizacao(true);
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/*
	 * Atualiza um conjunto de leituras e anormalidades bem como seu consumo e
	 * suas contas prefaturadas
	 * 
	 * @author Sávio Luiz @date 24/02/2010 @param buffer - BufferedReader com o
	 * arquivo selecionado @return void @throws ControladorException
	 */
	private Rota pesquisarRotaImpressaoSimultanea(
			AtualizarContaPreFaturadaHelper helperCabecalho)
			throws ControladorException {
		Rota rota = null;

		try {
			if (helperCabecalho.getMatriculaImovel() != null
					&& helperCabecalho.getAnoMesFaturamento() != null) {

				FiltroMovimentoRoteiroEmpresa filtroMovimentoRoteiroEmpresa = new FiltroMovimentoRoteiroEmpresa();

				filtroMovimentoRoteiroEmpresa
						.adicionarCaminhoParaCarregamentoEntidade("rota.faturamentoGrupo");
				filtroMovimentoRoteiroEmpresa
						.adicionarCaminhoParaCarregamentoEntidade("rota.leiturista");

				filtroMovimentoRoteiroEmpresa
						.adicionarParametro(new ParametroSimples(
								FiltroMovimentoRoteiroEmpresa.IMOVEL_ID,
								helperCabecalho.getMatriculaImovel()));

				filtroMovimentoRoteiroEmpresa
						.adicionarParametro(new ParametroSimples(
								FiltroMovimentoRoteiroEmpresa.ANO_MES_MOVIMENTO,
								helperCabecalho.getAnoMesFaturamento()));

				Collection<Rota> colMovimmentoRoteiroEmpresa = (Collection<Rota>) repositorioUtil
						.pesquisar(filtroMovimentoRoteiroEmpresa,
								MovimentoRoteiroEmpresa.class.getName());
				MovimentoRoteiroEmpresa movimentoRoteiroEmpresa = (MovimentoRoteiroEmpresa) Util
						.retonarObjetoDeColecao(colMovimmentoRoteiroEmpresa);
				if (movimentoRoteiroEmpresa != null
						&& !movimentoRoteiroEmpresa.equals("")) {
					rota = movimentoRoteiroEmpresa.getRota();
				}
			}
			// caso não tenha a rota em movimento_roteiro_empresa, pesquisar
			// pelo imóvel
			if (rota == null || rota.equals("")) {
				FiltroRota filtroRota = new FiltroRota();
				filtroRota
						.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
				filtroRota
						.adicionarCaminhoParaCarregamentoEntidade("leiturista");
				filtroRota
						.adicionarParametro(new ParametroSimples(
								FiltroRota.CODIGO_ROTA, helperCabecalho
										.getCodigoRota()));

				filtroRota.adicionarParametro(new ParametroSimples(
						FiltroRota.SETOR_COMERCIAL_CODIGO, helperCabecalho
								.getCodigoSetorComercial()));

				filtroRota.adicionarParametro(new ParametroSimples(
						FiltroRota.LOCALIDADE_ID, helperCabecalho
								.getLocalidade()));

				Collection<Rota> colRota = (Collection<Rota>) repositorioUtil
						.pesquisar(filtroRota, Rota.class.getName());
				rota = (Rota) Util.retonarObjetoDeColecao(colRota);
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return rota;
	}

	/**
	 * PesquisarDataPrevistaFaturamentoAtividadeCronograma
	 * 
	 * [SB0004] - Calcular Valor de Água e/ou Esgoto
	 * 
	 * 
	 * @author Hugo Amorim
	 * @date 11/09/2009
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Date pesquisarDataPrevistaFaturamentoAtividadeCronograma(
			Integer idImovel, int quantidadeMeses) throws ControladorException {

		Date dataRealizacao = null;
		try {
			Object[] parmsFaturamentoGrupo = repositorioFaturamento
					.pesquisarParmsFaturamentoGrupo(idImovel);
			Integer idFaturamentoGrupo = null;
			Integer anoMesFaturamentoGrupo = null;
			if (parmsFaturamentoGrupo != null) {
				if (parmsFaturamentoGrupo[0] != null) {
					idFaturamentoGrupo = (Integer) parmsFaturamentoGrupo[0];
				}
				if (parmsFaturamentoGrupo[1] != null) {
					anoMesFaturamentoGrupo = (Integer) parmsFaturamentoGrupo[1];
				}
			}
			if (idFaturamentoGrupo != null && anoMesFaturamentoGrupo != null) {
				// caso a quantidades de meses que quer subitrair seja diferente
				// de 0
				if (quantidadeMeses > 0) {
					anoMesFaturamentoGrupo = Util.subtrairMesDoAnoMes(
							anoMesFaturamentoGrupo, quantidadeMeses);
				}
				Integer idFaturamentoAtividade = FaturamentoAtividade.EFETUAR_LEITURA;
				// pesquisa a data de realização
				dataRealizacao = repositorioFaturamento
						.pesquisarDataPrevistaFaturamentoAtividadeCronograma(
								idFaturamentoGrupo, idFaturamentoAtividade,
								anoMesFaturamentoGrupo);
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return dataRealizacao;

	}

	/**
	 * [UC0764] Gerar Relatorio Contas Canceladas ou Retificadas
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2009
	 * @param RelatorioContasCanceladasRetificadasHelper
	 * @return quantidade de registros
	 */
	public Integer pesquisarQuantidadeContasCanceladasOuRetificadas(
			RelatorioContasCanceladasRetificadasHelper helper, int tipoPesquisa)
			throws ControladorException {

		Integer quantidade = null;

		SistemaParametro sistemaParametro;
		sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

		int anoMes = Util.formatarMesAnoComBarraParaAnoMes(helper.getMesAno());

		// tipo conta = 1 - cancelada / 2 - retificada
		if (anoMes < sistemaParametro.getAnoMesFaturamento()) {

			// cancelada
			if (tipoPesquisa == 1) {
				// faturamento fechado( anoMes < anoMesParametroSistema)
				// pesquisar em conta historico
				quantidade = repositorioFaturamento
						.pesquisarQuantidadeContasCanceladasFaturamentoFechado(helper);

				// retificada
			} else {
				// faturamento Aberto pesquisar em conta
				quantidade = repositorioFaturamento
						.pesquisarQuantidadeContasRetificadasFaturamentoFechado(helper);
			}
		} else {
			// cancelada
			if (tipoPesquisa == 1) {

				// faturamento Aberto pesquisar em conta
				quantidade = repositorioFaturamento
						.pesquisarQuantidadeContasCanceladasFaturamentoAberto(helper);

				// retificada
			} else {
				// faturamento Aberto pesquisar em conta
				quantidade = repositorioFaturamento
						.pesquisarQuantidadeContasRetificadasFaturamentoAberto(helper);

			}

		}

		return quantidade;
	}

	/**
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 14/09/2009
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarDadosRelatorioContasRevisaoCount(
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal,
			Collection colecaoIdsMotivoRevisao, Integer idImovelPerfil,
			Integer referenciaInicial, Integer referenciaFinal,
			Integer idCategoria, Integer idEsferaPoder)
			throws ControladorException {
		try {
			return repositorioFaturamento
					.pesquisarDadosRelatorioContasRevisaoCount(
							idGerenciaRegional, idUnidadeNegocio,
							idLocalidadeInicial, idLocalidadeFinal,
							codigoSetorComercialInicial,
							codigoSetorComercialFinal, colecaoIdsMotivoRevisao,
							idImovelPerfil, referenciaInicial, referenciaFinal,
							idCategoria, idEsferaPoder);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * 
	 * [UC0820] Atualizar Faturamento do Movimento Celular [SB002] Incluir
	 * Medicao
	 * 
	 * @param movimentoContaPreFaturada
	 * @param rota
	 * @throws ErroRepositorioException
	 */
	private void incluirMedicaoHistorico(
			MovimentoContaPrefaturada movimentoContaPreFaturada)
			throws ErroRepositorioException, ControladorException {
		// FS0005 Verificar Existência do histórico de Medição
		// 1.
		MedicaoHistorico medicaoHistorico = this
				.verificarExistenciaHistoricoMedicao(movimentoContaPreFaturada);

		if (medicaoHistorico == null) {

			/*
			 * [UC0595] Gerar Histórico de Medicao
			 * --------------------------------------------------------------------------------------------------------------------------------
			 */
			SistemaParametro sistemaParametro = this.getControladorUtil()
					.pesquisarParametrosDoSistema();

			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua.hidrometroInstalacaoHistorico");

			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, movimentoContaPreFaturada.getImovel()
							.getId()));

			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(this
					.getControladorUtil().pesquisar(filtroImovel,
							Imovel.class.getName()));

			if (movimentoContaPreFaturada.getFaturamentoGrupo() != null) {
				sistemaParametro.setAnoMesFaturamento(movimentoContaPreFaturada
						.getFaturamentoGrupo().getAnoMesReferencia());
			}

			medicaoHistorico = this.getControladorMicromedicao()
					.gerarHistoricoMedicao(
							movimentoContaPreFaturada.getMedicaoTipo(), imovel,
							movimentoContaPreFaturada.getFaturamentoGrupo(),
							sistemaParametro);
			/*
			 * FIM [UC0595] Gerar Histórico de Medicao
			 * --------------------------------------------------------------------------------------------------------------------------------
			 */

			medicaoHistorico
					.setImovel((movimentoContaPreFaturada.getMedicaoTipo()
							.getId().intValue() == MedicaoTipo.POCO) ? movimentoContaPreFaturada
							.getImovel()
							: null);

			// 1.2
			if (movimentoContaPreFaturada.getMedicaoTipo().getId().intValue() == MedicaoTipo.LIGACAO_AGUA) {
				LigacaoAgua ligacaoAgua = new LigacaoAgua();
				ligacaoAgua
						.setId(movimentoContaPreFaturada.getImovel().getId());
				medicaoHistorico.setLigacaoAgua(ligacaoAgua);
			} else {
				medicaoHistorico.setLigacaoAgua(null);
			}

			// 1.3
			medicaoHistorico.setMedicaoTipo(movimentoContaPreFaturada
					.getMedicaoTipo());
			// 1.4
			medicaoHistorico.setAnoMesReferencia(movimentoContaPreFaturada
					.getFaturamentoGrupo().getAnoMesReferencia());
			// 1.5
			medicaoHistorico
					.setNumeroVezesConsecutivasOcorrenciaAnormalidade(null);

			// 1.9
			medicaoHistorico
					.setDataLeituraAtualInformada(movimentoContaPreFaturada
							.getDataHoraLeitura());
			medicaoHistorico.setDataLeituraCampo(movimentoContaPreFaturada
					.getDataHoraLeitura());

			// 1.11
			medicaoHistorico
					.setDataLeituraAtualFaturamento(movimentoContaPreFaturada
							.getDataHoraLeitura());

			// 1.10, 1.12
			if (movimentoContaPreFaturada.getLeituraFaturamento() != null) {
				medicaoHistorico
						.setLeituraAtualFaturamento(movimentoContaPreFaturada
								.getLeituraFaturamento());
			} else {
				medicaoHistorico.setLeituraAtualFaturamento(0);
			}

			if (movimentoContaPreFaturada.getLeituraHidrometro() != null) {
				medicaoHistorico
						.setLeituraAtualInformada(movimentoContaPreFaturada
								.getLeituraHidrometro());
				medicaoHistorico.setLeituraCampo(movimentoContaPreFaturada
						.getLeituraHidrometro());
			} else {
				medicaoHistorico.setLeituraAtualInformada(null);
				medicaoHistorico.setLeituraCampo(null);
			}

			// 1.13
			if (movimentoContaPreFaturada.getConsumoMedido() != null
					&& movimentoContaPreFaturada.getConsumoMedido() > ConstantesSistema.ZERO) {
				medicaoHistorico.setNumeroConsumoMes(movimentoContaPreFaturada
						.getConsumoMedido());
			} else {
				medicaoHistorico.setNumeroConsumoMes(null);
			}

			// Adicionamos o leiturista da rota na medicao historico
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.ID_ROTA, movimentoContaPreFaturada.getRota()
							.getId()));
			Collection<Rota> colRota = this.getControladorUtil().pesquisar(
					filtroRota, Rota.class.getName());
			Rota rota = (Rota) colRota.iterator().next();
			medicaoHistorico.setLeiturista(rota.getLeiturista());

			// 1.15
			medicaoHistorico.setLeituraProcessamentoMovimento(new Date());

			medicaoHistorico.setFuncionario(null);

			// 1.17
			if (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null
					&& !movimentoContaPreFaturada
							.getLeituraAnormalidadeLeitura().equals("")) {
				medicaoHistorico
						.setLeituraAnormalidadeInformada(movimentoContaPreFaturada
								.getLeituraAnormalidadeLeitura());
			} else {
				medicaoHistorico.setLeituraAnormalidadeInformada(null);

			}

			if (movimentoContaPreFaturada.getLeituraAnormalidadeFaturamento() != null
					&& !movimentoContaPreFaturada
							.getLeituraAnormalidadeFaturamento().equals("")) {
				medicaoHistorico
						.setLeituraAnormalidadeFaturamento(movimentoContaPreFaturada
								.getLeituraAnormalidadeFaturamento());
			} else {
				medicaoHistorico.setLeituraAnormalidadeFaturamento(null);

			}

			// 1.19
			LeituraSituacao leituraSituacao = new LeituraSituacao();

			if ( movimentoContaPreFaturada.getLeituraHidrometro() == null )/* || 
				movimentoContaPreFaturada.getLeituraHidrometro() == 0)*/ {
				
				leituraSituacao.setId(LeituraSituacao.NAO_REALIZADA);
			} 
			else if (movimentoContaPreFaturada.getIndicadorSituacaoLeitura() != null &&
					movimentoContaPreFaturada.getIndicadorSituacaoLeitura().equals(ConstantesSistema.INDICADOR_USO_ATIVO)) {
					
				leituraSituacao.setId(LeituraSituacao.CONFIRMADA);
				
			}
			else{
				leituraSituacao.setId(LeituraSituacao.REALIZADA);
			}

			medicaoHistorico.setLeituraSituacaoAtual(leituraSituacao);

			// 1.20 - Ja setado mais acima
			// 1.21
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();

			// Verificar se o devemos salvar assim
			if (movimentoContaPreFaturada.getMedicaoTipo().getId().intValue() == MedicaoTipo.LIGACAO_AGUA) {
				hidrometroInstalacaoHistorico.setId(movimentoContaPreFaturada
						.getImovel().getLigacaoAgua()
						.getHidrometroInstalacaoHistorico().getId());
				medicaoHistorico
						.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
			} else {
				hidrometroInstalacaoHistorico
						.setId(movimentoContaPreFaturada.getImovel()
								.getHidrometroInstalacaoHistorico().getId());
				medicaoHistorico
						.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
			}

			// 1.22
			medicaoHistorico.setConsumoMedioHidrometro(null);
			medicaoHistorico.setIndicadorAnalisado(ConstantesSistema.NAO);
			medicaoHistorico.setUltimaAlteracao(new Date());

			if (medicaoHistorico.getId() != null) {
				// repositorioUtil.atualizar( medicaoHistorico );
				RepositorioMicromedicaoHBM.getInstancia()
						.atualizarMedicaoHistoricoProcessoMOBILE(
								medicaoHistorico);
			} else {
				getControladorBatch().inserirObjetoParaBatchSemTransacao(
						medicaoHistorico);
			}
		}
	}

	/**
	 * [UC0820] Atualizar Faturamento do Movimento Celular
	 * 
	 * FS0005 - Verificar Existência do histórico de Medição
	 * 
	 * 
	 * @param matricula
	 *            matricula do imovel selecionado
	 * @param anoMes
	 *            ano mês do historico a ser consultado
	 */
	private MedicaoHistorico verificarExistenciaHistoricoMedicao(
			MovimentoContaPrefaturada movimentoContaPreFaturada)
			throws ErroRepositorioException, ControladorException {
		FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();

		// Caso o tipo de medição seja água
		if (movimentoContaPreFaturada.getMedicaoTipo().getId().equals(
				MedicaoTipo.LIGACAO_AGUA)) {
			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistorico.LIGACAO_AGUA_ID,
					movimentoContaPreFaturada.getImovel().getId()));
		} else {
			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistorico.IMOVEL_ID, movimentoContaPreFaturada
							.getImovel().getId()));
		}

		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
				FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO,
				movimentoContaPreFaturada.getFaturamentoGrupo()
						.getAnoMesReferencia()));
		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
				FiltroMedicaoHistorico.MEDICAO_TIPO_ID,
				movimentoContaPreFaturada.getMedicaoTipo().getId()));
		Collection<MedicaoHistorico> colMedicaoHistorico = this.repositorioUtil
				.pesquisar(filtroMedicaoHistorico, MedicaoHistorico.class
						.getName());

		MedicaoHistorico medicaoHistorico = (MedicaoHistorico) Util
				.retonarObjetoDeColecao(colMedicaoHistorico);

		if (medicaoHistorico != null) {

			medicaoHistorico.setFuncionario(null);

			if (movimentoContaPreFaturada.getLeituraAnormalidadeLeitura() != null
					&& !movimentoContaPreFaturada
							.getLeituraAnormalidadeLeitura().equals("")) {
				medicaoHistorico
						.setLeituraAnormalidadeInformada(movimentoContaPreFaturada
								.getLeituraAnormalidadeLeitura());
			} else {
				medicaoHistorico.setLeituraAnormalidadeInformada(null);

			}

			if (movimentoContaPreFaturada.getLeituraAnormalidadeFaturamento() != null
					&& !movimentoContaPreFaturada
							.getLeituraAnormalidadeFaturamento().equals("")) {
				medicaoHistorico
						.setLeituraAnormalidadeFaturamento(movimentoContaPreFaturada
								.getLeituraAnormalidadeFaturamento());
			} else {
				medicaoHistorico.setLeituraAnormalidadeFaturamento(null);

			}

			if (movimentoContaPreFaturada.getDataHoraLeitura() != null
					&& !movimentoContaPreFaturada.getDataHoraLeitura().equals(
							"")) {
				medicaoHistorico
						.setDataLeituraAtualInformada(movimentoContaPreFaturada
								.getDataHoraLeitura());
				medicaoHistorico
						.setDataLeituraAtualFaturamento(movimentoContaPreFaturada
								.getDataHoraLeitura());
				medicaoHistorico.setDataLeituraCampo(movimentoContaPreFaturada
						.getDataHoraLeitura());
			}

			// 1.10, 1.12
			if (movimentoContaPreFaturada.getLeituraFaturamento() != null) {
				medicaoHistorico
						.setLeituraAtualFaturamento(movimentoContaPreFaturada
								.getLeituraFaturamento());
			} else {
				medicaoHistorico.setLeituraAtualFaturamento(0);
			}

			if (movimentoContaPreFaturada.getLeituraHidrometro() != null) {
				medicaoHistorico
						.setLeituraAtualInformada(movimentoContaPreFaturada
								.getLeituraHidrometro());
				medicaoHistorico.setLeituraCampo(movimentoContaPreFaturada
						.getLeituraHidrometro());
			} else {
				medicaoHistorico.setLeituraAtualInformada(null);
				medicaoHistorico.setLeituraCampo(null);
			}

			LeituraSituacao leituraSituacao = new LeituraSituacao();

			if (movimentoContaPreFaturada.getLeituraFaturamento() == null
					|| movimentoContaPreFaturada.getLeituraFaturamento() == 0) {
				leituraSituacao.setId(LeituraSituacao.NAO_REALIZADA);
			} else {
				if (movimentoContaPreFaturada.getIndicadorSituacaoLeitura() == ConstantesSistema.INDICADOR_USO_ATIVO) {
					leituraSituacao.setId(LeituraSituacao.CONFIRMADA);
				} else {
					leituraSituacao.setId(LeituraSituacao.REALIZADA);
				}
			}

			medicaoHistorico.setLeituraSituacaoAtual(leituraSituacao);

			// 1.13
			if (movimentoContaPreFaturada.getConsumoMedido() != null
					&& movimentoContaPreFaturada.getConsumoMedido() > ConstantesSistema.ZERO) {
				medicaoHistorico.setNumeroConsumoMes(movimentoContaPreFaturada
						.getConsumoMedido());
			} else {
				medicaoHistorico.setNumeroConsumoMes(null);
			}

			// Adicionamos o leiturista da rota na medicao historico
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.ID_ROTA, movimentoContaPreFaturada.getRota()
							.getId()));
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("leiturista");
			Collection<Rota> colRota = this.getControladorUtil().pesquisar(
					filtroRota, Rota.class.getName());
			Rota rota = (Rota) colRota.iterator().next();
			medicaoHistorico.setLeiturista(rota.getLeiturista());

			repositorioUtil.atualizar(medicaoHistorico);
		}

		return medicaoHistorico;
	}

	/**
	 * 
	 * Selecionamos os movimento para serem processados
	 * 
	 * @param helper
	 *            Helper para pesquisa
	 * @return Coleção com os dados solicitados
	 * 
	 * @throws ControladorException
	 */
	private Collection<MovimentoContaPrefaturada> verificarExistenciaListaMovimentoContaPrefaturada(
			AtualizarContaPreFaturadaHelper helperCabecalho)
			throws ControladorException {
		FiltroMovimentoContaPrefaturada filtroMovimentoContaPrefaturada = new FiltroMovimentoContaPrefaturada();
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("imovel.hidrometroInstalacaoHistorico");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		filtroMovimentoContaPrefaturada
		.adicionarCaminhoParaCarregamentoEntidade("rota");
		filtroMovimentoContaPrefaturada
		.adicionarCaminhoParaCarregamentoEntidade("rota.leituraTipo");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAgua");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeFaturamento");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra.rota");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("conta");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("movimentoContaPrefaturadaCategorias");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("conta.ligacaoAguaSituacao");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("conta.ligacaoEsgotoSituacao");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelCondominio");
		filtroMovimentoContaPrefaturada
				.adicionarCaminhoParaCarregamentoEntidade( "rota.leituraTipo" );
		
		filtroMovimentoContaPrefaturada
				.adicionarParametro(new ParametroSimples(
						FiltroMovimentoContaPrefaturada.INDICADOR_ATUALIZAR_FATURAMENTO,
						ConstantesSistema.NAO));

		filtroMovimentoContaPrefaturada
				.adicionarParametro(new ParametroSimples(
						FiltroMovimentoContaPrefaturada.MATRICULA,
						helperCabecalho.getMatriculaImovel()));

		filtroMovimentoContaPrefaturada
				.adicionarParametro(new ParametroSimples(
						FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO,
						helperCabecalho.getAnoMesFaturamento()));

		Collection<MovimentoContaPrefaturada> colMovimentoContaPrefaturada = null;

		try {
			colMovimentoContaPrefaturada = (Collection<MovimentoContaPrefaturada>) repositorioUtil
					.pesquisar(filtroMovimentoContaPrefaturada,
							MovimentoContaPrefaturada.class.getName());
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return colMovimentoContaPrefaturada;
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [SB0001] - Gera Tela Resumo das leituras e anormalidades da impressão
	 * simultânea registradas
	 * 
	 * @author bruno
	 * @date 21/09/2009
	 */
	private byte[] geraResumoLeiturasAnormalidadesImpressaoSimultanea(
			Collection<MovimentoContaPrefaturada> colRelatorio)
			throws ControladorException {

		Integer qtdRegistrosRecebidos = 0;
		Integer qtdRegistrosLeitura = 0;
		Integer qtdRegistrosAnormalidade = 0;
		Integer qtdRegistrosLeituraAnormalidade = 0;
		Integer qtdRegistrosInvalidos = 0;
		Integer qtdRegistrosContaNaoEmitida = 0;

		MovimentoContaPrefaturada helperCabecalho = null;
		
		byte[] retorno = null;

		Collection<Integer> imoveisJaLidos = new ArrayList();

		// Criamos agora os beans do relatorio
		List relatorioBeans = new ArrayList();
		
		if(colRelatorio != null && !colRelatorio.isEmpty()){
			
			for (MovimentoContaPrefaturada helper : colRelatorio) {
	
				if (!imoveisJaLidos.contains(helper.getImovel().getId())) {
					imoveisJaLidos.add(helper.getImovel().getId());
	
					qtdRegistrosRecebidos++;
	
					if (helperCabecalho == null) {
						helperCabecalho = helper;
					}
	
					if (helper.getLeituraFaturamento() != null
							&& helper.getLeituraFaturamento() != 0) {
						qtdRegistrosLeitura++;
					} else if (helper.getLeituraAnormalidadeFaturamento() != null) {
						qtdRegistrosAnormalidade++;
					} else if (helper.getLeituraFaturamento() != null
							&& helper.getLeituraFaturamento() != 0
							&& helper.getLeituraAnormalidadeFaturamento() != null) {
						qtdRegistrosLeituraAnormalidade++;
					} else {
						qtdRegistrosInvalidos++;
					}
	
					if (helper.getIndicadorEmissaoConta() == ConstantesSistema.NAO) {
						qtdRegistrosContaNaoEmitida++;
					}
	
					RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean bean = new RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean();
	
					if (helper.getLeituraAnormalidadeFaturamento() != null) {
						bean.setCodigoAnormalidade(helper
								.getLeituraAnormalidadeFaturamento().getId()
								+ "");
	
						if (!relatorioBeans.contains(bean)) {
	
							if (helper.getLeituraAnormalidadeFaturamento() != null) {
								bean.setCodigoAnormalidade(helper
										.getLeituraAnormalidadeFaturamento()
										.getId()
										+ "");
								bean.setDescricaoAnormalidade(helper
										.getLeituraAnormalidadeFaturamento()
										.getDescricao());
							}
	
							bean.setQtdAnormalidade(1);
	
							relatorioBeans.add(bean);
						} else {
							int index = relatorioBeans.indexOf(bean);
							bean = (RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean) relatorioBeans
									.get(index);
							bean.setQtdAnormalidade(bean.getQtdAnormalidade() + 1);
						}
					}
				}
			}
		

			if (relatorioBeans.size() == 0) {
				relatorioBeans
						.add(new RelatorioResumoLeiturasAnormalidadesImpressaoSimultaneaBean());
			}
	
			RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea relatorioResumoLeiturasAnormalidadesImpressaoSimultanea = new RelatorioResumoLeiturasAnormalidadesImpressaoSimultanea(
					new Usuario());
	
			// Adicionamos os parametros
			Map parametros = new HashMap();
	
			parametros.put("imagem", this.getControladorUtil()
					.pesquisarParametrosDoSistema().getImagemRelatorio());
	
			// Id da localidade
			FiltroImovel filtro = new FiltroImovel();
			filtro.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.empresa");
			filtro.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
					helperCabecalho.getImovel().getId()));
			Collection<Imovel> colImo = Fachada.getInstancia().pesquisar(filtro,
					Imovel.class.getName());
			Imovel imo = (Imovel) Util.retonarObjetoDeColecao(colImo);
			parametros.put("grupo", helperCabecalho.getFaturamentoGrupo().getId()
					+ "");
			parametros.put("localidade", imo.getLocalidade().getId() + "");
			parametros.put("codigoEmpresa", imo.getQuadra().getRota().getEmpresa()
					.getId());
			parametros.put("empresa", imo.getQuadra().getRota().getEmpresa()
					.getDescricao());
	
			parametros.put("qtdRegistrosRecebidos", qtdRegistrosRecebidos);
			parametros.put("qtdRegistrosLeitura", qtdRegistrosLeitura);
			parametros.put("qtdRegistrosAnormalidade", qtdRegistrosAnormalidade);
			parametros.put("qtdRegistrosInvalidos", qtdRegistrosInvalidos);
			parametros.put("qtdRegistrosLeituraAnormalidade",
					qtdRegistrosLeituraAnormalidade);
			parametros.put("qtdRegistrosSemContaEmitida",
					qtdRegistrosContaNaoEmitida);
	
			// Criamos o source
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
	
			retorno = relatorioResumoLeiturasAnormalidadesImpressaoSimultanea
					.gerarRelatorio(
							ConstantesRelatorios.RELATORIO_RESUMO_LEITURAS_ANORMALIDADE_IMPRESSAO_SIMULTANEA,
							parametros, ds, TarefaRelatorio.TIPO_PDF);
		}
		
		return retorno;
	}

	/**
	 * Gerar quantidade de imoveis
	 * 
	 * @author Arthur Carvalho
	 * @date 23/09/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer gerarRelacaoAcompanhamentoFaturamentoCount(
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
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			int anoMesReferencia) throws ControladorException {

		try {
			return repositorioFaturamento
					.gerarRelacaoAcompanhamentoFaturamentoCount(
							idImovelCondominio, idImovelPrincipal, idNomeConta,
							idSituacaoLigacaoAgua, consumoMinimoInicialAgua,
							consumoMinimoFinalAgua, idSituacaoLigacaoEsgoto,
							consumoMinimoInicialEsgoto,
							consumoMinimoFinalEsgoto,
							intervaloValorPercentualEsgotoInicial,
							intervaloValorPercentualEsgotoFinal,

							intervaloMediaMinimaImovelInicial,
							intervaloMediaMinimaImovelFinal,
							intervaloMediaMinimaHidrometroInicial,
							intervaloMediaMinimaHidrometroFinal,

							idImovelPerfil, idPocoTipo,
							idFaturamentoSituacaoTipo, idCobrancaSituacaoTipo,
							idSituacaoEspecialCobranca, idEloAnormalidade,
							areaConstruidaInicial, areaConstruidaFinal,
							idCadastroOcorrencia, idConsumoTarifa,
							idGerenciaRegional, idLocalidadeInicial,
							idLocalidadeFinal, setorComercialInicial,
							setorComercialFinal, quadraInicial, quadraFinal,
							loteOrigem, loteDestno, cep, logradouro, bairro,
							municipio, idTipoMedicao, indicadorMedicao,
							idSubCategoria, idCategoria,
							quantidadeEconomiasInicial,
							quantidadeEconomiasFinal, diaVencimento, idCliente,
							idClienteTipo, idClienteRelacaoTipo,
							numeroPontosInicial, numeroPontosFinal,
							numeroMoradoresInicial, numeroMoradoresFinal,
							idAreaConstruidaFaixa);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * 
	 * Este caso de uso permite a inserção de dados na tabela movimento conta
	 * pré-faturada.
	 * 
	 * [UC0923] Incluir Movimento Conta Pré-Faturada
	 * 
	 * [SB000X] - Remove todos os dados incluidos
	 * 
	 * @author bruno
	 * @date 30/06/2009
	 * 
	 * @param colErrors
	 */
	private void removerDadosMovimentosContaPreFaturada(Integer idConta,
			Integer idImovel, Integer anoMes) throws ControladorException {

		try {

			FiltroMovimentoContaPrefaturada filtroMovimentoContaPrefaturada = new FiltroMovimentoContaPrefaturada();
			filtroMovimentoContaPrefaturada
					.adicionarParametro(new ParametroSimples(
							FiltroMovimentoContaPrefaturada.MATRICULA, idImovel));
			filtroMovimentoContaPrefaturada
					.adicionarParametro(new ParametroSimples(
							FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO,
							anoMes));

			Collection<MovimentoContaPrefaturada> colMovimento = getControladorUtil()
					.pesquisar(filtroMovimentoContaPrefaturada,
							MovimentoContaPrefaturada.class.getName());

			for (MovimentoContaPrefaturada movimento : colMovimento) {

				// Pesquisamos movimento_conta_prefaturada_categoria
				FiltroMovimentoContaPrefaturadaCategoria filtroMovimentoContaPrefaturadaCategoria = new FiltroMovimentoContaPrefaturadaCategoria();
				filtroMovimentoContaPrefaturadaCategoria
						.adicionarParametro(new ParametroSimples(
								FiltroMovimentoContaPrefaturadaCategoria.MOVIMENTO_CONTA_PREFATURADA_ID,
								movimento.getId()));

				Collection<MovimentoContaPrefaturadaCategoria> colMovimentoContaPrefaturadaCategoria = getControladorUtil()
						.pesquisar(
								filtroMovimentoContaPrefaturadaCategoria,
								MovimentoContaPrefaturadaCategoria.class
										.getName());

				for (MovimentoContaPrefaturadaCategoria movimentoCategoria : colMovimentoContaPrefaturadaCategoria) {

					FiltroMovimentoContaCategoriaConsumoFaixa filtroMovimentoContaCategoriaConsumoFaixa = new FiltroMovimentoContaCategoriaConsumoFaixa();
					filtroMovimentoContaCategoriaConsumoFaixa
							.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaCategoriaConsumoFaixa.MOVIMENTO_CONTA_PREFATURADA_ID,
									movimento.getId()));

					Collection<MovimentoContaCategoriaConsumoFaixa> colMovimentoContaCategoriaConsumoFaixa = getControladorUtil()
							.pesquisar(
									filtroMovimentoContaCategoriaConsumoFaixa,
									MovimentoContaCategoriaConsumoFaixa.class
											.getName());

					// Removemos movimento_conta_categoria_consumo_faixa
					getControladorBatch()
							.removerColecaoObjetoParaBatchSemTransacao(
									colMovimentoContaCategoriaConsumoFaixa);

					getControladorBatch().removerObjetoParaBatchSemTransacao(
							movimentoCategoria);
				}

				// Pesquisamos movimento_conta_imposto_deduzido
				FiltroMovimentoContaImpostoDeduzido filtroMovimentoContaImpostoDeduzido = new FiltroMovimentoContaImpostoDeduzido();
				filtroMovimentoContaImpostoDeduzido
						.adicionarParametro(new ParametroSimples(
								FiltroMovimentoContaImpostoDeduzido.MOVIMENTO_CONTA_PREFATURADA_ID,
								movimento.getId()));

				Collection<MovimentoContaImpostoDeduzido> colMovimentoContaImpostoDeduzido = getControladorUtil()
						.pesquisar(filtroMovimentoContaImpostoDeduzido,
								MovimentoContaImpostoDeduzido.class.getName());

				// Removemos movimento_conta_imposto_deduzidos
				if (colMovimentoContaImpostoDeduzido != null
						&& !colMovimentoContaImpostoDeduzido.isEmpty()) {
					getControladorBatch()
							.removerColecaoObjetoParaBatchSemTransacao(
									colMovimentoContaImpostoDeduzido);
				}

				getControladorBatch().removerObjetoParaBatchSemTransacao(
						movimento);
			}

			FiltroConta filtroConta = new FiltroConta();
			filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID,
					idConta));

			Collection<Conta> colConta = getControladorUtil().pesquisar(
					filtroConta, Conta.class.getName());

			if (colConta == null || colConta.isEmpty()) {

				Conta contaAtualizacao = repositorioFaturamento
						.pesquisarContaPreFaturada(idImovel, anoMes,
								DebitoCreditoSituacao.NORMAL);

				colConta = new ArrayList();

				if (contaAtualizacao != null) {
					
					idConta = contaAtualizacao.getId();
					colConta.add(contaAtualizacao);
					
				}else{
					contaAtualizacao = repositorioFaturamento
					.pesquisarContaPreFaturada(idImovel, anoMes,
							DebitoCreditoSituacao.PRE_FATURADA);
					if (contaAtualizacao != null) {
						idConta = contaAtualizacao.getId();
						colConta.add(contaAtualizacao);
					}
				}

			}

			// Caso o imóvel tenha situação especial de nitrato e exista um
			// crédito com a situação igual a Normal
			// atualiza o valor para 0 e a situação para PRE-FATURADA.
			// pesquisa o crédito a realizar
/*
 * Object[] dadosCreditoARealizarNitrato = repositorioFaturamento
 * .pesquisarCreditoARealizar(idImovel, CreditoTipo.CREDITO_NITRATO,
 * DebitoCreditoSituacao.NORMAL, anoMes);
 */			BigDecimal valorCreditoNitrato = null;

			CreditoRealizado creditoRealizado = null;

			Conta contaCreditos = new Conta();
			contaCreditos.setId( idConta );

			Collection<CreditoRealizado> colCreditos = Fachada.getInstancia().obterCreditosRealizadosConta( contaCreditos );

			for ( CreditoRealizado objeto : colCreditos ) {
				if ( objeto.getCreditoTipo().getId().equals( CreditoTipo.CREDITO_NITRATO ) ){
					creditoRealizado = objeto;
				}
			}						

			Object[] dadosCreditoARealizarNitrato = null;

			if ( creditoRealizado != null ){						
				dadosCreditoARealizarNitrato = 
					repositorioFaturamento.pesquisarCreditoARealizar( creditoRealizado.getCreditoARealizarGeral().getId(), anoMes );
			}

			if (dadosCreditoARealizarNitrato != null
					&& !dadosCreditoARealizarNitrato.equals("")) {
				BigDecimal valorCredito = new BigDecimal("0.00");
				valorCreditoNitrato = new BigDecimal("0.00");
				Integer idCreditoARealizarNitrato = (Integer) dadosCreditoARealizarNitrato[0];
				valorCreditoNitrato = (BigDecimal) dadosCreditoARealizarNitrato[1];
				// atualiza o crédito a realizar com o valor do crédito
				// calculado
				repositorioFaturamento.atualizarValorCreditoARealizar(
						idCreditoARealizarNitrato, valorCredito,
						DebitoCreditoSituacao.PRE_FATURADA);
			}

			// Conta Categoria Consumo Faixa
			FiltroContaCategoriaConsumoFaixa filtroContaCategoriaConsumoFaixa = new FiltroContaCategoriaConsumoFaixa();
			filtroContaCategoriaConsumoFaixa
					.adicionarParametro(new ParametroSimples(
							FiltroContaCategoriaConsumoFaixa.CONTA_ID, idConta));
			Collection<Object> colContaCategoriaConsumoFaixa = getControladorUtil()
					.pesquisar(filtroContaCategoriaConsumoFaixa,
							ContaCategoriaConsumoFaixa.class.getName());

			getControladorBatch().removerColecaoObjetoParaBatchSemTransacao(
					colContaCategoriaConsumoFaixa);

			// Faturamento Imediato Ajuste
			FiltroFaturamentoImediatoAjuste filtroFaturamentoImediatoAjuste = new FiltroFaturamentoImediatoAjuste();
			filtroFaturamentoImediatoAjuste
					.adicionarParametro(new ParametroSimples(
							FiltroFaturamentoImediatoAjuste.ID_CONTA, idConta));

			Collection<Object> colFaturamentoImediatoAjuste = getControladorUtil()
					.pesquisar(filtroFaturamentoImediatoAjuste,
							FaturamentoImediatoAjuste.class.getName());

			getControladorBatch().removerColecaoObjetoParaBatchSemTransacao(
					colFaturamentoImediatoAjuste);

			// Conta Impressao
			FiltroContaImpressao filtroContaImpressao = new FiltroContaImpressao();
			filtroContaImpressao.adicionarParametro(new ParametroSimples(
					FiltroContaImpressao.ID, idConta));

			Collection<Object> colContaImpressao = getControladorUtil()
					.pesquisar(filtroContaImpressao,
							ContaImpressao.class.getName());

			getControladorBatch().removerColecaoObjetoParaBatchSemTransacao(
					colContaImpressao);

			for (Conta conta : colConta) {

				if (conta.getDebitoCreditoSituacaoAtual().getId() != DebitoCreditoSituacao.PRE_FATURADA) {

					DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
					debitoCreditoSituacao
							.setId(DebitoCreditoSituacao.PRE_FATURADA);

					conta.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
					conta.setValorAgua(BigDecimal.ZERO);
					conta.setValorEsgoto(BigDecimal.ZERO);
					conta.setValorImposto(BigDecimal.ZERO);

					// caso já exista valor de crédito
					if (valorCreditoNitrato != null) {
						BigDecimal valorCreditos = conta.getValorCreditos();
						valorCreditos = valorCreditos
								.subtract(valorCreditoNitrato);
						conta.setValorCreditos(valorCreditos);
					}

					try {
						repositorioFaturamento
								.zerarValoresContaPassarDebitoCreditoSituacaoAtualPreFaturadaMOBILE(conta);
					} catch (ErroRepositorioException e) {
						throw new ControladorException("erro.sistema", e);
					}
				}
			}
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0958] - Gerar Relatório de juros, Multas e Débitos Cancelados
	 * 
	 * @since 13/10/2009
	 * @author Marlon Patrick
	 */
	public Collection<RelatorioJurosMultasDebitosCanceladosHelper> pesquisarRelatorioJurosMultasDebitosCancelados(
			FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro)
			throws ControladorException {

		try {
			Collection<Object[]> dadosRelatorio = this.repositorioFaturamento
					.pesquisarRelatorioJurosMultasDebitosCancelados(filtro);

			if (Util.isVazioOrNulo(dadosRelatorio)) {
				throw new ControladorException(
						"atencao.relatorio_juros_multas_debitos_cancelados.nenhuma_conta_retificada",
						null);
			}

			List<RelatorioJurosMultasDebitosCanceladosHelper> colecaoRelatoriosHelper = null;

			colecaoRelatoriosHelper = criarColecaoRelatorioJurosMultasDebitosCanceladosAgrupados(dadosRelatorio);

			for (Iterator<RelatorioJurosMultasDebitosCanceladosHelper> iteratorRelatorioHelper = colecaoRelatoriosHelper
					.iterator(); iteratorRelatorioHelper.hasNext();) {

				RelatorioJurosMultasDebitosCanceladosHelper relatorioHelper = iteratorRelatorioHelper
						.next();

				ContaHistorico contaHistoricoOriginal = obterContaOriginalContaHistorico(relatorioHelper);

				if (contaHistoricoOriginal == null) {

					Conta contaOriginal = obterContaOriginalConta(relatorioHelper);

					if (contaOriginal != null) {

						if (!selecionarApenasDebitosCancelados(relatorioHelper,
								contaOriginal, filtro)) {
							iteratorRelatorioHelper.remove();
						}

					}

				} else {

					if (!selecionarApenasDebitosCancelados(relatorioHelper,
							contaHistoricoOriginal, filtro)) {
						iteratorRelatorioHelper.remove();
					}
				}

			}

			return colecaoRelatoriosHelper;

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0958] - Relatorio de Juros, Multas e Débitos Cancelados<br/> [SB0003] -
	 * Pesquisar juros,multas e débitos cancelados.<br/>
	 * 
	 * Esse método define quais débitos foram cancelados comparando a conta
	 * original e a conta retificada.<br/> Caso seja encontrado algum débito
	 * que NÃO foi cancelado o mesmo é removido da coleção de débitos.<br/>
	 * Caso a conta retificada não possua nenhum débito cancelado o método
	 * retorna FALSE.<br/>
	 * 
	 * Regras:<br/> 0 - Se uma conta possui vários débitos do mesmo tipo, eles
	 * devem ser somados(a retificada já vem assim da consulta, a original é
	 * feito neste método)<br/> 1 - Se não havia nenhum débito na conta
	 * original, então, nenhum débito foi cancelado.<br/> 2 - Se a conta
	 * original tinha débitos e a retificada não, então, todos foram cancelados.<br/>
	 * 3 - Se o débito existe apenas na conta retificada, então, ele não foi
	 * cancelado e sim adicionado.<br/> 4 - Se o débito é maior ou igual na
	 * retificada, então, na verdade houve um aumento do débito ou continuou o
	 * mesmo.<br/> 5 - Se o débito na original é maior que na retificada,
	 * então, o valor cancelado é: valor da original - valor da retificada.<br/>
	 * 6 - Se o débito existe apenas na original, então, ele foi cancelado.<br/>
	 * 
	 * @since 19/10/2009
	 * @author Marlon Patrick
	 */
	private boolean selecionarApenasDebitosCancelados(
			RelatorioJurosMultasDebitosCanceladosHelper relatorioHelper,
			ContaHistorico contaOriginalHistorico,
			FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro)
			throws ControladorException {

		FiltroDebitoCobradoHistorico filtroDebitoCobradoHistorico = new FiltroDebitoCobradoHistorico();
		filtroDebitoCobradoHistorico
				.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoCobradoHistorico.DEBITO_TIPO);
		filtroDebitoCobradoHistorico.adicionarParametro(new ParametroSimples(
				FiltroDebitoCobradoHistorico.CONTA_HISTORICO_ID,
				contaOriginalHistorico.getId()));
		filtroDebitoCobradoHistorico
				.setCampoOrderBy(FiltroDebitoCobradoHistorico.DEBITO_TIPO_DESCRICAO);// ordem
																						// influencia
																						// na
																						// lógica

		Collection<DebitoCobradoHistorico> colecaoDebitosContaOriginal = getControladorUtil()
				.pesquisar(filtroDebitoCobradoHistorico,
						DebitoCobradoHistorico.class.getName());

		if (Util.isVazioOrNulo(colecaoDebitosContaOriginal)) {
			return false;
		}

		DebitoCobradoHistorico debitoCobrado = colecaoDebitosContaOriginal
				.iterator().next();
		Collection<DebitoCobradoHistorico> colecaoDebitosAuxiliar = new ArrayList<DebitoCobradoHistorico>();

		while (!colecaoDebitosAuxiliar.contains(debitoCobrado)) {
			for (Iterator<DebitoCobradoHistorico> iterator = colecaoDebitosContaOriginal
					.iterator(); iterator.hasNext();) {

				DebitoCobradoHistorico debitoCobradoAtual = iterator.next();

				if (colecaoDebitosAuxiliar.contains(debitoCobradoAtual)) {
					continue;
				}

				if (!debitoCobrado.getId().equals(debitoCobradoAtual.getId())
						&& debitoCobrado.getDebitoTipo().getDescricao().equals(
								debitoCobradoAtual.getDebitoTipo()
										.getDescricao())) {

					debitoCobrado.setValorPrestacao(debitoCobrado
							.getValorPrestacao().add(
									debitoCobradoAtual.getValorPrestacao()));

					iterator.remove();
					continue;
				}

				if (!debitoCobrado.getId().toString().equals(
						debitoCobradoAtual.getId().toString())) {
					colecaoDebitosAuxiliar.add(debitoCobrado);
					debitoCobrado = debitoCobradoAtual;
					break;
				}

				if (!iterator.hasNext()) {
					colecaoDebitosAuxiliar.add(debitoCobrado);
				}
			}
		}

		if (Util.isVazioOrNulo(relatorioHelper.getColecaoDebitosCobrados())) {

			relatorioHelper
					.setColecaoDebitosCobrados(new ArrayList<DebitoCobradoAgrupadoHelper>());

			for (DebitoCobradoHistorico debitoContaOriginal : colecaoDebitosContaOriginal) {
				if (Util.isVazioOrNulo(filtro.getColecaoTiposDebito())
						|| filtro.getColecaoTiposDebito().contains(
								debitoContaOriginal.getDebitoTipo().getId())) {
					DebitoCobradoAgrupadoHelper debitoHelper = new DebitoCobradoAgrupadoHelper();

					debitoHelper.setDescricaoDebitoTipo(debitoContaOriginal
							.getDebitoTipo().getDescricao());
					debitoHelper.setValorDebito(debitoContaOriginal
							.getValorPrestacao());

					relatorioHelper.getColecaoDebitosCobrados().add(
							debitoHelper);
				}
			}

			return true;
		}

		for (Iterator<DebitoCobradoAgrupadoHelper> iterator = relatorioHelper
				.getColecaoDebitosCobrados().iterator(); iterator.hasNext();) {

			DebitoCobradoAgrupadoHelper debitoContaRetificada = iterator.next();

			boolean isDebitoApenasContaRetificada = true;

			for (DebitoCobradoHistorico debitoContaOriginal : colecaoDebitosContaOriginal) {
				if (debitoContaRetificada.getDescricaoDebitoTipo().trim()
						.equalsIgnoreCase(
								debitoContaOriginal.getDebitoTipo()
										.getDescricaoAbreviada().trim())) {
					isDebitoApenasContaRetificada = false;
					break;
				}
			}

			if (isDebitoApenasContaRetificada) {
				iterator.remove();
			}
		}

		for (DebitoCobradoHistorico debitoContaOriginal : colecaoDebitosContaOriginal) {

			boolean isDebitoApenasContaOriginal = true;

			for (Iterator<DebitoCobradoAgrupadoHelper> iterator = relatorioHelper
					.getColecaoDebitosCobrados().iterator(); iterator.hasNext();) {
				DebitoCobradoAgrupadoHelper debitoContaRetificada = iterator
						.next();

				if (debitoContaRetificada.getDescricaoDebitoTipo().trim()
						.equalsIgnoreCase(
								debitoContaOriginal.getDebitoTipo()
										.getDescricaoAbreviada().trim())) {

					isDebitoApenasContaOriginal = false;

					if (debitoContaRetificada.getValorDebito().compareTo(
							debitoContaOriginal.getValorPrestacao()) >= 0) {
						iterator.remove();
						continue;
					}

					debitoContaRetificada.setValorDebito(debitoContaOriginal
							.getValorPrestacao().subtract(
									debitoContaRetificada.getValorDebito()));
					break;
				}
			}

			if (isDebitoApenasContaOriginal) {
				if (Util.isVazioOrNulo(filtro.getColecaoTiposDebito())
						|| filtro.getColecaoTiposDebito().contains(
								debitoContaOriginal.getId())) {
					DebitoCobradoAgrupadoHelper debitoHelper = new DebitoCobradoAgrupadoHelper();

					debitoHelper.setDescricaoDebitoTipo(debitoContaOriginal
							.getDebitoTipo().getDescricao());
					debitoHelper.setValorDebito(debitoContaOriginal
							.getValorPrestacao());

					relatorioHelper.getColecaoDebitosCobrados().add(
							debitoHelper);
				}
			}

			if (Util.isVazioOrNulo(relatorioHelper.getColecaoDebitosCobrados())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * [UC0958] - Relatorio de Juros, Multas e Débitos Cancelados<br/> [SB0003] -
	 * Pesquisar juros,multas e débitos cancelados.<br/>
	 * 
	 * Esse método define quais débitos foram cancelados comparando a conta
	 * original e a conta retificada.<br/> Caso seja encontrado algum débito
	 * que NÃO foi cancelado o mesmo é removido da coleção de débitos.<br/>
	 * Caso a conta retificada não possua nenhum débito cancelado o método
	 * retorna FALSE.<br/>
	 * 
	 * Regras:<br/> 0 - Se uma conta possui vários débitos do mesmo tipo, eles
	 * devem ser somados(a retificada já vem assim da consulta, a original é
	 * feito neste método)<br/> 1 - Se não havia nenhum débito na conta
	 * original, então, nenhum débito foi cancelado.<br/> 2 - Se a conta
	 * original tinha débitos e a retificada não, então, todos foram cancelados.<br/>
	 * 3 - Se o débito existe apenas na conta retificada, então, ele não foi
	 * cancelado e sim adicionado.<br/> 4 - Se o débito é maior ou igual na
	 * retificada, então, na verdade houve um aumento do débito ou continuou o
	 * mesmo.<br/> 5 - Se o débito na original é maior que na retificada,
	 * então, o valor cancelado é: valor da original - valor da retificada.<br/>
	 * 6 - Se o débito existe apenas na original, então, ele foi cancelado.<br/>
	 * 
	 * 
	 * @since 19/10/2009
	 * @author Marlon Patrick
	 */
	private boolean selecionarApenasDebitosCancelados(
			RelatorioJurosMultasDebitosCanceladosHelper relatorioHelper,
			Conta contaOriginal,
			FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro)
			throws ControladorException {

		FiltroDebitoCobrado filtroDebitoCobrado = new FiltroDebitoCobrado();
		filtroDebitoCobrado
				.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoCobrado.DEBITO_TIPO);
		filtroDebitoCobrado.adicionarParametro(new ParametroSimples(
				FiltroDebitoCobrado.CONTA_ID, contaOriginal.getId()));

		Collection<DebitoCobrado> colecaoDebitosContaOriginal = getControladorUtil()
				.pesquisar(filtroDebitoCobrado, DebitoCobrado.class.getName());

		if (Util.isVazioOrNulo(colecaoDebitosContaOriginal)) {
			return false;
		}

		DebitoCobrado debitoCobrado = colecaoDebitosContaOriginal.iterator()
				.next();

		while (debitoCobrado != null) {
			for (Iterator<DebitoCobrado> iterator = colecaoDebitosContaOriginal
					.iterator(); iterator.hasNext();) {
				DebitoCobrado debitoCobradoAtual = iterator.next();

				if (!debitoCobrado.getId().equals(debitoCobradoAtual.getId())
						&& debitoCobrado.getDebitoTipo().getDescricao().equals(
								debitoCobradoAtual.getDebitoTipo()
										.getDescricao())) {

					debitoCobrado.setValorPrestacao(debitoCobrado
							.getValorPrestacao().add(
									debitoCobradoAtual.getValorPrestacao()));

					iterator.remove();
					continue;
				}

				debitoCobrado = debitoCobradoAtual;
				break;
			}
		}

		if (Util.isVazioOrNulo(relatorioHelper.getColecaoDebitosCobrados())) {

			relatorioHelper
					.setColecaoDebitosCobrados(new ArrayList<DebitoCobradoAgrupadoHelper>());

			for (DebitoCobrado debitoContaOriginal : colecaoDebitosContaOriginal) {

				if (Util.isVazioOrNulo(filtro.getColecaoTiposDebito())
						|| filtro.getColecaoTiposDebito().contains(
								debitoContaOriginal.getDebitoTipo().getId())) {
					DebitoCobradoAgrupadoHelper debitoHelper = new DebitoCobradoAgrupadoHelper();

					debitoHelper.setDescricaoDebitoTipo(debitoContaOriginal
							.getDebitoTipo().getDescricao());
					debitoHelper.setValorDebito(debitoContaOriginal
							.getValorPrestacao());

					relatorioHelper.getColecaoDebitosCobrados().add(
							debitoHelper);
				}
			}

			return true;
		}

		for (Iterator<DebitoCobradoAgrupadoHelper> iterator = relatorioHelper
				.getColecaoDebitosCobrados().iterator(); iterator.hasNext();) {

			DebitoCobradoAgrupadoHelper debitoContaRetificada = iterator.next();

			boolean isDebitoApenasContaRetificada = true;

			for (DebitoCobrado debitoContaOriginal : colecaoDebitosContaOriginal) {
				if (debitoContaRetificada.getDescricaoDebitoTipo().equals(
						debitoContaOriginal.getDebitoTipo().getDescricao())) {
					isDebitoApenasContaRetificada = false;
					break;
				}
			}

			if (isDebitoApenasContaRetificada) {
				iterator.remove();
			}
		}

		for (DebitoCobrado debitoContaOriginal : colecaoDebitosContaOriginal) {

			boolean isDebitoApenasContaOriginal = true;

			for (Iterator<DebitoCobradoAgrupadoHelper> iterator = relatorioHelper
					.getColecaoDebitosCobrados().iterator(); iterator.hasNext();) {
				DebitoCobradoAgrupadoHelper debitoContaRetificada = iterator
						.next();

				if (debitoContaRetificada.getDescricaoDebitoTipo().equals(
						debitoContaOriginal.getDebitoTipo().getDescricao())) {

					isDebitoApenasContaOriginal = false;

					if (debitoContaRetificada.getValorDebito().compareTo(
							debitoContaOriginal.getValorPrestacao()) >= 0) {

						iterator.remove();
						break;
					}

					debitoContaRetificada.setValorDebito(debitoContaOriginal
							.getValorPrestacao().subtract(
									debitoContaRetificada.getValorDebito()));

					break;
				}

			}

			if (isDebitoApenasContaOriginal) {

				if (Util.isVazioOrNulo(filtro.getColecaoTiposDebito())
						|| filtro.getColecaoTiposDebito().contains(
								debitoContaOriginal.getId())) {
					DebitoCobradoAgrupadoHelper debitoHelper = new DebitoCobradoAgrupadoHelper();

					debitoHelper.setDescricaoDebitoTipo(debitoContaOriginal
							.getDebitoTipo().getDescricao());
					debitoHelper.setValorDebito(debitoContaOriginal
							.getValorPrestacao());

					relatorioHelper.getColecaoDebitosCobrados().add(
							debitoHelper);
				}
			}

			if (Util.isVazioOrNulo(relatorioHelper.getColecaoDebitosCobrados())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * [UC0958] - Relatorio de juros,Multas e Débitos Cancelados<br/> [SB0002] -
	 * Obter conta original<br/>
	 * 
	 * Este método tenta obter a conta original de uma determinada conta
	 * retificada a partir da tabela de conta. O parametro relatorioHelper tem
	 * os dados da conta retificada.
	 * 
	 * @since 19/10/2009
	 * @author Marlon Patrick
	 */
	private Conta obterContaOriginalConta(
			RelatorioJurosMultasDebitosCanceladosHelper relatorioHelper)
			throws ControladorException {

		FiltroConta filtroConta = new FiltroConta();

		filtroConta.adicionarParametro(new ParametroSimples(
				FiltroConta.REFERENCIA, relatorioHelper.getAnoMesReferencia()));
		filtroConta.adicionarParametro(new ParametroSimples(
				FiltroConta.IMOVEL_ID, relatorioHelper.getMatricula()));
		filtroConta.adicionarParametro(new ParametroSimples(
				FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
				DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO));
		filtroConta.adicionarParametro(new ParametroNulo(
				FiltroContaHistorico.DATA_RETIFICACAO));

		Collection<Conta> colecaoConta = getControladorUtil().pesquisar(
				filtroConta, Conta.class.getName());

		if (!Util.isVazioOrNulo(colecaoConta)) {
			return colecaoConta.iterator().next();
		}

		return null;
	}

	/**
	 * [UC0958] - Relatorio de juros,Multas e Débitos Cancelados<br/> [SB0002] -
	 * Obter conta original<br/>
	 * 
	 * Este método tenta obter a conta original de uma determinada conta
	 * retificada a partir da tabela de conta histórico. O parametro
	 * relatorioHelper tem os dados da conta retificada.
	 * 
	 * @since 19/10/2009
	 * @author Marlon Patrick
	 */
	private ContaHistorico obterContaOriginalContaHistorico(
			RelatorioJurosMultasDebitosCanceladosHelper relatorioHelper)
			throws ControladorException {

		FiltroContaHistorico filtroContaHistorico = new FiltroContaHistorico();

		filtroContaHistorico.adicionarParametro(new ParametroSimples(
				FiltroContaHistorico.ANO_MES_REFERENCIA, relatorioHelper
						.getAnoMesReferencia()));
		filtroContaHistorico
				.adicionarParametro(new ParametroSimples(
						FiltroContaHistorico.IMOVEL_ID, relatorioHelper
								.getMatricula()));
		filtroContaHistorico.adicionarParametro(new ParametroSimples(
				FiltroContaHistorico.DEBITO_CREDITO_SITUACAO_ATUAL_ID,
				DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO));
		filtroContaHistorico.adicionarParametro(new ParametroNulo(
				FiltroContaHistorico.DATA_RETIFICACAO));

		Collection<ContaHistorico> colecaoContaHistorico = getControladorUtil()
				.pesquisar(filtroContaHistorico, ContaHistorico.class.getName());

		if (!Util.isVazioOrNulo(colecaoContaHistorico)) {
			return colecaoContaHistorico.iterator().next();
		}

		return null;
	}

	/**
	 * [UC0958] - Relatorio de Juros, Multas e Débitos Cancelados.<br/>
	 * 
	 * Este método cria uma coleção de
	 * RelatorioJurosMultasDebitosCanceladosHelper com base no retorno da
	 * consulta realizada anteriormente. Além disso, ele agrupa as contas pelo
	 * seus débitos (uma conta passa a ter uma coleção de débitos).
	 * 
	 * @since 19/10/2009
	 * @author Marlon Patrick
	 */
	private List<RelatorioJurosMultasDebitosCanceladosHelper> criarColecaoRelatorioJurosMultasDebitosCanceladosAgrupados(
			Collection<Object[]> dadosRelatorio) throws ControladorException {

		List<RelatorioJurosMultasDebitosCanceladosHelper> colecaoRelatoriosHelper = new ArrayList<RelatorioJurosMultasDebitosCanceladosHelper>();

		for (Object[] dadosAtuais : dadosRelatorio) {
			RelatorioJurosMultasDebitosCanceladosHelper relatorioHelper = new RelatorioJurosMultasDebitosCanceladosHelper();

			relatorioHelper.setDataCancelamento((Date) dadosAtuais[0]);

			relatorioHelper.setResponsavel((String) dadosAtuais[1]);

			Imovel imovel = new Imovel();
			imovel.setLocalidade(new Localidade((Integer) dadosAtuais[2]));
			imovel.setSetorComercial(new SetorComercial());
			imovel.getSetorComercial().setCodigo((Integer) dadosAtuais[3]);
			imovel.setQuadra(new Quadra());
			imovel.getQuadra().setNumeroQuadra((Integer) dadosAtuais[4]);
			imovel.setLote((Short) dadosAtuais[5]);
			imovel.setSubLote((Short) dadosAtuais[6]);
			imovel.setId((Integer) dadosAtuais[7]);

			relatorioHelper.setInscricao(imovel.getInscricaoFormatada());
			relatorioHelper.setMatricula(imovel.getId().toString());

			relatorioHelper.setEndereco(this.getControladorEndereco()
					.obterEnderecoAbreviadoImovel(imovel.getId()));

			relatorioHelper.setAnoMesReferencia((Integer) dadosAtuais[8]);

			relatorioHelper
					.setColecaoDebitosCobrados(new ArrayList<DebitoCobradoAgrupadoHelper>());

			if (dadosAtuais[9] != null && dadosAtuais[10] != null) {
				DebitoCobradoAgrupadoHelper debitoCobrado = new DebitoCobradoAgrupadoHelper();
				debitoCobrado.setDescricaoDebitoTipo((String) dadosAtuais[9]);
				debitoCobrado.setValorDebito((BigDecimal) dadosAtuais[10]);
				relatorioHelper.getColecaoDebitosCobrados().add(debitoCobrado);
			}

			relatorioHelper.setTabelaOrigem((String) dadosAtuais[12]);

			if (colecaoRelatoriosHelper.contains(relatorioHelper)) {
				RelatorioJurosMultasDebitosCanceladosHelper helperJaExistente = colecaoRelatoriosHelper
						.get(colecaoRelatoriosHelper.indexOf(relatorioHelper));

				helperJaExistente.getColecaoDebitosCobrados().addAll(
						relatorioHelper.getColecaoDebitosCobrados());

				continue;
			}

			colecaoRelatoriosHelper.add(relatorioHelper);
		}

		return colecaoRelatoriosHelper;
	}

	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 * 
	 * [SB0005] - Gerar os Créditos Realizados
	 * 
	 * @author Sávio Luiz
	 * @date 27/10/2009
	 * 
	 * @param imovel
	 * @param faturamentoGrupo
	 * @param helperValoresAguaEsgoto
	 * @param valorTotalDebitos
	 * @param gerarAtividadeGrupoFaturamento
	 * @return GerarCreditoRealizadoHelper
	 * @throws ControladorException
	 */
	private BigDecimal atualizarCreditoResidual(Imovel imovel,Integer idConta,
			Integer anoMesFaturamento,BigDecimal valorTotalContaSemCredito) throws ControladorException{
		
			
		// Acumula o valor do crédito
		BigDecimal valorTotalCreditos = BigDecimal.ZERO;
		
		try{
		Collection colecaoCreditoRealizado = repositorioFaturamento.pesquisarCreditosRealizados(idConta);
		
		/*
		 * Caso a coleção de creditos a realizar não esteja vazia
		 */
		if (colecaoCreditoRealizado != null && !colecaoCreditoRealizado.isEmpty()) {


			Iterator iteratorColecaoCreditosRealizados = colecaoCreditoRealizado
			.iterator();
			
			boolean deletaCreditoRealizado = false;
			
			CreditoRealizado creditoRealizado = null;
			
			BigDecimal valorTotalACobrar = valorTotalContaSemCredito;
			
			while (iteratorColecaoCreditosRealizados.hasNext()){
				creditoRealizado = (CreditoRealizado) iteratorColecaoCreditosRealizados
				.next();
				
				
				// Pesquisa os créditos a realizar do imóvel
				Collection colecaoCreditosARealizar = this
						.obterCreditoARealizarDadosCreditoRealizado(creditoRealizado.getCreditoARealizarGeral().getId(),anoMesFaturamento);
				
				/*
				 * Caso a coleção de creditos a realizar não esteja vazia
				 */
				if (colecaoCreditosARealizar != null && !colecaoCreditosARealizar.isEmpty()) {

					
					Iterator iteratorColecaoCreditosARealizar = colecaoCreditosARealizar
					.iterator();

					CreditoARealizar creditoARealizar = null;

					/*
					 * Para cada crédito a realizar selecionado e até que o
					 * valor total a cobrar seja igual a zero.
					 * 
					 * LAÇO PARA GERAR OS CREDITOS REALIZADOS
					 */
					while (iteratorColecaoCreditosARealizar.hasNext()) {

						creditoARealizar = (CreditoARealizar) iteratorColecaoCreditosARealizar
						.next();
						
						if(!deletaCreditoRealizado){							
							
							BigDecimal valorCredito = ConstantesSistema.VALOR_ZERO;
							
							valorCredito = creditoRealizado.getValorCredito();							
														
							// Retira o valor de credito do valor total a cobrar
							valorTotalACobrar = valorTotalACobrar.subtract(valorCredito);						
	
							/*
							 * Caso o valor total a cobrar seja menor que zero o
							 * valor residual do mês anterior vai ser igual a
							 * valor total a cobrar vezes -1(menos um) e o valor
							 * do crédito vai ser igual ao valor do crédito
							 * menos valor residual do mês anterior.
							 * 
							 * Valor Total A Cobrar = 0.00
							 * 
							 * Caso contrário o valor residual do mês anterior
							 * vai ser iguala zero.
							 */
							if (valorTotalACobrar.compareTo(ConstantesSistema.VALOR_ZERO) == -1) {
	
								creditoARealizar
										.setValorResidualMesAnterior(valorTotalACobrar
												.multiply(new BigDecimal("-1")));
								
								valorCredito = valorCredito.subtract(creditoARealizar
										.getValorResidualMesAnterior());
								
	
								valorTotalACobrar = ConstantesSistema.VALOR_ZERO;
								
								// atualiza o credito a realizar
								repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);
								
								// atualiza o credito realizado
								creditoRealizado.setValorCredito(valorCredito);
								getControladorUtil().atualizar(creditoRealizado);
								
								// Pesquisa os créditos a realizar categoria
								Collection colecaoCreditoARealizarCategoria = this
										.obterCreditoRealizarCategoria( creditoARealizar.getId() );
								
								Iterator colecaoCreditoARealizarCategoriaIterator = colecaoCreditoARealizarCategoria
								.iterator();

								// Crédito a realizar categoria
								CreditoARealizarCategoria creditoARealizarCategoria = null;

								Collection colecaoCategoriasObterValor = new ArrayList();

								// Laço para recuperar as categorias do crédito
								// a
								// realizar
								while (colecaoCreditoARealizarCategoriaIterator.hasNext()) {
									creditoARealizarCategoria = (CreditoARealizarCategoria) colecaoCreditoARealizarCategoriaIterator.next();
									
									Categoria categoria = new Categoria();							
									categoria.setId(creditoARealizarCategoria.getCategoria().getId());
									categoria.setQuantidadeEconomiasCategoria(creditoARealizarCategoria.getQuantidadeEconomia());
									colecaoCategoriasObterValor.add(categoria);								
								}

								// Obter os valores das categorias por categoria
								// do
								// credito a realizar categoria
								Collection colecaoCategoriasCalculadasValor = getControladorImovel()
									.obterValorPorCategoria(
										colecaoCategoriasObterValor,
										valorCredito);								
								
								repositorioFaturamento
									.atualizarValorCreditoRealizadoCategoria(
										creditoRealizado.getId(),
										colecaoCategoriasObterValor,
										colecaoCategoriasCalculadasValor);
								
							}else{
								creditoARealizar
								.setValorResidualMesAnterior(ConstantesSistema.VALOR_ZERO);
								repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);
							}
							
							// Acumula o valor do crédito
							valorTotalCreditos = valorTotalCreditos.add(valorCredito);
						
					    }else{
					    	// atualiza o credito a realizar
					    	// caso o crédito residual consedido no mês seja
							// diferente de nulo e seja maior que zero
					    	if(creditoARealizar.getValorResidualConcedidoMes() != null && creditoARealizar.getValorResidualConcedidoMes().compareTo(BigDecimal.ZERO) > 0){
					    		// atualiza o valor residual anterior
					    		creditoARealizar.setValorResidualMesAnterior(creditoARealizar.getValorResidualConcedidoMes());
					    	}else{					    		
					    		// Atualiza o nº de prestações realizadas
					    		creditoARealizar.setNumeroPrestacaoRealizada(new Short(
					    				(creditoARealizar.getNumeroPrestacaoRealizada().intValue() - 1)+ ""));
							  
					    		if ( creditoARealizar.getNumeroPrestacaoRealizada().intValue() == 0 ){
					    			// anoMes da prestação será o anaMes de referência da conta
					    			creditoARealizar.setAnoMesReferenciaPrestacao(null);								  
					    		} else {
					    			creditoARealizar.setAnoMesReferenciaPrestacao( Util.subtrairMesDoAnoMes (creditoARealizar.getAnoMesReferenciaPrestacao(), 1 ) );
					    		}
					    		
				    			creditoARealizar.setAnoMesUltimaPrestacao(null);
					    	}
						
						  repositorioFaturamento.atualizarCreditoARealizar(creditoARealizar);
					}
						
					}// fim laço de credito a realizar
					
				}else{
					deletaCreditoRealizado = true;
				}
						
				if (deletaCreditoRealizado) {
				 // deleta o credito realizado categoria
				 repositorioFaturamento
						.deletarCreditoRealizadoCategoria(creditoRealizado
							.getId());

				 // deleta o credito realizado
				 getControladorBatch().removerObjetoParaBatchSemTransacao(creditoRealizado);
				}

				if (valorTotalACobrar
						.compareTo(ConstantesSistema.VALOR_ZERO) == 0) {
					deletaCreditoRealizado = true;
				}
			}
		}
		
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return valorTotalCreditos;
	}
	/**
	 * Obtem os Credito A Realizar do Imovel
	 * 
	 * @param imovelID
	 *            Id do Imovel
	 * @param debitoCreditoSituacaoAtualID
	 *            ID do Debito Credito Situação
	 * @return Coleção de Creditos a Realizar
	 */
    private Collection obterCreditoARealizarDadosCreditoRealizado(Integer IdCreditoARealizar,Integer anoMesFaturamento)
            throws ControladorException {

        // lista de credito a realizar
        Collection creditosARealizar = null;
        Collection colecaoCreditosARealizar = null;
        // Pesquisa créditos a cobrar
        try {
            colecaoCreditosARealizar = repositorioFaturamento
                    .pesquisarCreditoARealizarPeloCreditoRealizado(IdCreditoARealizar,anoMesFaturamento);

        } catch (ErroRepositorioException ex) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema", ex);
        }

        // Verifica se existe débitos a realizar
        if (colecaoCreditosARealizar != null && !colecaoCreditosARealizar.isEmpty()) {

            creditosARealizar = new ArrayList();

            Iterator iteratorColecaoCreditosARealizar = colecaoCreditosARealizar.iterator();
            CreditoARealizar creditoARealizar = null;
            while (iteratorColecaoCreditosARealizar.hasNext()) {

                Object[] arrayCreditosACobrar = (Object[]) iteratorColecaoCreditosARealizar.next();

                creditoARealizar = new CreditoARealizar();
                // id do Credito a Realizar - Item 0
                if (arrayCreditosACobrar[0] != null) {
                    creditoARealizar.setId((Integer) arrayCreditosACobrar[0]);
                }

                // numero de prestacoes realizadas - item 1
                if (arrayCreditosACobrar[1] != null) {
                    creditoARealizar.setNumeroPrestacaoRealizada((Short) arrayCreditosACobrar[1]);
                }

                // numero de prestacoes credito - item 2
                if (arrayCreditosACobrar[2] != null) {
                    creditoARealizar.setNumeroPrestacaoCredito((Short) arrayCreditosACobrar[2]);

                }

                // valor de credito - item 3
                if (arrayCreditosACobrar[3] != null) {
                    creditoARealizar.setValorCredito((BigDecimal) arrayCreditosACobrar[3]);

                }

                // valor residual mes anterior - item 4
                if (arrayCreditosACobrar[4] != null) {
                    creditoARealizar.setValorResidualMesAnterior((BigDecimal) arrayCreditosACobrar[4]);
                }

                // credito tipo - item 5
                if (arrayCreditosACobrar[5] != null) {
                    CreditoTipo creditoTipo = new CreditoTipo();
                    creditoTipo.setId((Integer) arrayCreditosACobrar[5]);
                    creditoARealizar.setCreditoTipo(creditoTipo);

                }

                // lancamento item contabil - item 6
                if (arrayCreditosACobrar[6] != null) {
                    LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
                    lancamentoItemContabil.setId((Integer) arrayCreditosACobrar[6]);
                    creditoARealizar.setLancamentoItemContabil(lancamentoItemContabil);
                }

                // lancamento - item 7
                if (arrayCreditosACobrar[7] != null) {
                    Localidade localidade = new Localidade();
                    localidade.setId((Integer) arrayCreditosACobrar[7]);
                    creditoARealizar.setLocalidade(localidade);
                }

                // quadra - item 8
                if (arrayCreditosACobrar[8] != null) {
                    Quadra quadra = new Quadra();
                    quadra.setId((Integer) arrayCreditosACobrar[8]);
                    creditoARealizar.setQuadra(quadra);
                }

                // codigo setor comercial - item 9
                if (arrayCreditosACobrar[9] != null) {
                    creditoARealizar.setCodigoSetorComercial((Integer) arrayCreditosACobrar[9]);
                }

                // numero quadra - item 10
                if (arrayCreditosACobrar[10] != null) {
                    creditoARealizar.setNumeroQuadra((Integer) arrayCreditosACobrar[10]);
                }

                // numero lote - item 11
                if (arrayCreditosACobrar[11] != null) {
                    creditoARealizar.setNumeroLote((Short) arrayCreditosACobrar[11]);
                }

                // numero sublote - item 12
                if (arrayCreditosACobrar[12] != null) {
                    creditoARealizar.setNumeroSubLote((Short) arrayCreditosACobrar[12]);
                }

                // ano mes referencia credito - item 13
                if (arrayCreditosACobrar[13] != null) {
                    creditoARealizar.setAnoMesReferenciaCredito((Integer) arrayCreditosACobrar[13]);
                }

                // ano mes cobranca credito - item 14
                if (arrayCreditosACobrar[14] != null) {
                    creditoARealizar.setAnoMesCobrancaCredito((Integer) arrayCreditosACobrar[14]);
                }

                // CreditoOrigem - item 15
                if (arrayCreditosACobrar[15] != null) {

                    CreditoOrigem creditoOrigem = new CreditoOrigem();
                    creditoOrigem.setId((Integer) arrayCreditosACobrar[15]);

                    creditoARealizar.setCreditoOrigem(creditoOrigem);
                }
                
                /*
				 * Alterado por Vivianne Sousa em 20/12/2007 - Analista: Adriano
				 * criação do bonus para parcelamento com RD especial
				 */
                // numero de parcelas bonus - item 16
                if (arrayCreditosACobrar[16] != null) {
                    creditoARealizar.setNumeroParcelaBonus((Short) arrayCreditosACobrar[16]);
                }
                
                // valor residual concedido no mês - item 17
                if (arrayCreditosACobrar[17] != null) {
                    creditoARealizar.setValorResidualConcedidoMes((BigDecimal) arrayCreditosACobrar[17]);
                }
                
                // Ano mes de referencia do credito 
                if (arrayCreditosACobrar[18] != null) {
                    creditoARealizar.setAnoMesReferenciaPrestacao( (Integer) arrayCreditosACobrar[18]);
                }
                
                // Ano mes de referencia da ultima prestação - item 18
                if (arrayCreditosACobrar[19] != null) {
                    creditoARealizar.setAnoMesUltimaPrestacao((Integer) arrayCreditosACobrar[19]);
                }
                
                

                creditosARealizar.add(creditoARealizar);
            }
        }

        return creditosARealizar;

    }
	/**
	 * [UC0958] - Count Relatório de juros, Multas e Débitos Cancelados
	 * 
	 * @since 10/12/2009
	 * @author Hugo Amorim
	 */
	public int countRelatorioJurosMultasDebitosCancelados(
			FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro)
			throws ControladorException {

		int retorno = 0;
		Collection<Object[]> dadosRelatorio;
		try {

			dadosRelatorio = this.repositorioFaturamento
					.pesquisarRelatorioJurosMultasDebitosCancelados(filtro);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		retorno = dadosRelatorio.size();

		return retorno;
	}

	/**
	 * 
	 * [UC0972] Gerar TXT das Contas dos Projetos Especiais
	 * 
	 * @author Hugo Amorim
	 * @since 14/12/2009
	 * 
	 */
	public void gerarTxtContasProjetosEspeciais(String anoMes,
			Integer idCliente, Integer idFuncionalidadeIniciada)
			throws ControladorException {
		int idUnidadeIniciada = 0;
		ZipOutputStream zos = null;
		BufferedWriter out = null;

		try {
			// -------------------------
			// Registrar o início do processamento da Unidade de
			// Processamento do Batch
			// -------------------------

			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.FUNCIONALIDADE, 0);

			// Variáveis para a paginação da pesquisa
			// ========================================================================
			boolean flagTerminou = false;
			final int quantidadeRegistros = 5000;
			int numeroIndice = 0;
			// ========================================================================

			Collection colecaoDadosTxt = null;

			String nomeArquivo = "contas_projetos_especiais_" + anoMes;

			// criar o arquivo zip
			File compactado = new File(nomeArquivo + ".zip"); // nomeZip
			zos = new ZipOutputStream(new FileOutputStream(compactado));

			File leitura = new File(nomeArquivo + ".txt");

			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(leitura.getAbsolutePath())));

			while (!flagTerminou) {

				colecaoDadosTxt = repositorioFaturamento
						.pesquisarDadosTxtContasProjetosEspeciais(anoMes,
								idCliente, quantidadeRegistros, numeroIndice);

				if (colecaoDadosTxt != null && !colecaoDadosTxt.isEmpty()) {

					Iterator colecaoDadosTxtIterator = colecaoDadosTxt
							.iterator();

					while (colecaoDadosTxtIterator.hasNext()) {

						GerarArquivoTextoContasProjetosEspeciaisHelper helper = new GerarArquivoTextoContasProjetosEspeciaisHelper();

						StringBuilder arquivoTxt = new StringBuilder();

						// cria um array de objetos para pegar os parametros
						// de retorno da pesquisa
						Object[] arraydadosTxt = (Object[]) colecaoDadosTxtIterator
								.next();

						this.montarDadosArquivoTextoContasProjetosEspeciais(
								arraydadosTxt, helper);

						this.montarArquivoTextoContasProjetosEspeciais(
								arquivoTxt, helper);

						arquivoTxt.append(System.getProperty("line.separator"));

						if (arquivoTxt != null && arquivoTxt.length() != 0) {

							out.write(arquivoTxt.toString());
							out.flush();

						}

						helper = null;
					}
				}

				// Incrementa o nº do indice da páginação
				numeroIndice = numeroIndice + quantidadeRegistros;

				/**
				 * Caso a coleção de dados retornados for menor que a quantidade
				 * de registros seta a flag indicando que a paginação terminou.
				 */
				if (colecaoDadosTxt == null
						|| colecaoDadosTxt.size() < quantidadeRegistros) {

					flagTerminou = true;
				}

				if (colecaoDadosTxt != null) {
					colecaoDadosTxt.clear();
					colecaoDadosTxt = null;
				}
			}

			ZipUtil.adicionarArquivo(zos, leitura);
			out.close();
			leitura.delete();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);

		} catch (IOException e) {
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
		} finally {
			IoUtil.fecharStream(out);
			IoUtil.fecharStream(zos);
		}

	}

	/**
	 * Montar Helper para facilitar na criação do txt das contas do projeto
	 * especial
	 * 
	 * @author: Hugo Amorim
	 * @date: 14/12/2009
	 */
	private void montarDadosArquivoTextoContasProjetosEspeciais(
			Object[] arraydadosTxt,
			GerarArquivoTextoContasProjetosEspeciaisHelper helper) {
		// Id Localidade
		if (arraydadosTxt[0] != null) {
			helper.setIdLocalidade((arraydadosTxt[0]).toString());
		}
		// Nome Localidade
		if (arraydadosTxt[1] != null) {
			helper.setNomeLocalidade((arraydadosTxt[1]).toString());
		}
		// Matricula
		if (arraydadosTxt[2] != null) {
			helper.setMatriculaImovel((arraydadosTxt[2]).toString());
		}
		// Nome Usuario
		if (arraydadosTxt[3] != null) {
			helper.setNomeUsuario((arraydadosTxt[3]).toString());
		}
		// Endereco
		if (helper.getMatriculaImovel() != null) {
			String endereco = "";

			try {
				endereco = this.getControladorEndereco().pesquisarEndereco(
						new Integer(helper.getMatriculaImovel()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ControladorException e) {
				e.printStackTrace();
			}

			helper.setEndereco(endereco);
		}
		// Numero Hidrometro
		if (arraydadosTxt[4] != null) {
			helper.setNumeroHidrometro((arraydadosTxt[4]).toString());
		}
		// Ano Mes Referencia da Conta
		if (arraydadosTxt[5] != null) {
			helper.setAnoMesReferenciaConta((arraydadosTxt[5]).toString());
		}
		// Consumo
		if (arraydadosTxt[6] != null) {
			helper.setConsumoAgua((arraydadosTxt[6]).toString());
		}
		// Valor da Conta
		if (arraydadosTxt[7] != null) {

			BigDecimal valorConta = new BigDecimal(arraydadosTxt[7].toString());

			if (valorConta.intValue() < 0) {
				helper.setValorConta("0.0");
			} else {
				helper.setValorConta((arraydadosTxt[7]).toString());
			}

		}
		// CPF
		if(arraydadosTxt[8] != null){
			helper.setCpf((arraydadosTxt[8]).toString());
		}
		// Setor Comercial
		if(arraydadosTxt[9] != null){
			helper.setSetorComercial((arraydadosTxt[9]).toString());
		}
		// Grupo Faturamento
		if(arraydadosTxt[10] != null){
			helper.setGrupoFaturamento((arraydadosTxt[10]).toString());
		}
	}

	/**
	 * Montar Arquivo txt das contas do projeto especial
	 * 
	 * @author: Hugo Amorim
	 * @date: 14/12/2009
	 */
	private void montarArquivoTextoContasProjetosEspeciais(
			StringBuilder arquivoTxt,
			GerarArquivoTextoContasProjetosEspeciaisHelper helper) {
		// id Localidade tam 03
		if (helper.getIdLocalidade() != null) {
			arquivoTxt.append(Util.truncarString(helper.getIdLocalidade()
					.toString(), 3)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Nome Localidade tam 25
		if (helper.getNomeLocalidade() != null) {
			arquivoTxt.append(Util.truncarString(helper.getNomeLocalidade()
					.toString(), 25)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Matricula tam 9
		if (helper.getMatriculaImovel() != null) {
			arquivoTxt.append(Util
					.truncarString(helper.getMatriculaImovel(), 9)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Nome Usuario tam 30
		if (helper.getNomeUsuario() != null) {
			arquivoTxt.append(Util.truncarString(helper.getNomeUsuario(), 30)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Endereco tam 100
		if (helper.getEndereco() != null) {
			arquivoTxt.append(Util.truncarString(helper.getEndereco(), 100)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Numero do Hidrometro tam 11
		if (helper.getNumeroHidrometro() != null) {
			arquivoTxt.append(Util.truncarString(helper.getNumeroHidrometro(),
					11)
					+ ";");
		} else {
			arquivoTxt.append("S/ HIDROM;");
		}
		// Ano Mes referencia da Conta tam 6
		if (helper.getAnoMesReferenciaConta() != null) {
			arquivoTxt.append(Util.truncarString(helper
					.getAnoMesReferenciaConta(), 6)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Consumo Agua tam 6
		if (helper.getConsumoAgua() != null) {
			arquivoTxt.append(Util.truncarString(helper.getConsumoAgua(), 6)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		// Valor Conta tam 15
		if (helper.getValorConta() != null) {
			arquivoTxt.append(Util.truncarString(helper.getValorConta(), 6)
					+ ";");
		} else {
			arquivoTxt.append(";");
		}
		
		// CPF
		if (helper.getCpf() != null) {
			arquivoTxt.append(Util.truncarString(helper.getCpf(), 11) + ";");
		} else {
			arquivoTxt.append(";");
		}
		// Setor Comercial
		if (helper.getSetorComercial() != null) {
			arquivoTxt.append(Util.truncarString(helper.getSetorComercial(), 4) + ";");
		} else {
			arquivoTxt.append(";");
		} 
		// Grupo Faturamento
		if (helper.getGrupoFaturamento() != null) {
			arquivoTxt.append(Util.truncarString(helper.getGrupoFaturamento(), 4) + ";");
		} else {
			arquivoTxt.append(";");
		} 
	}

	/**
	 * 
	 * [UC0972] count TXT das Contas dos Projetos Especiais
	 * 
	 * @author Hugo Amorim
	 * @since 15/12/2009
	 * 
	 */
	public Integer countTxtContasProjetosEspeciais(String anoMes,
			Integer idCliente) throws ControladorException {

		int retorno = 0;

		try {

			retorno = this.repositorioFaturamento
					.countTxtContasProjetosEspeciais(anoMes, idCliente);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * Este caso de uso permite gerar o resumo de simulação em um conjunto de
	 * rotas de um grupo de faturamento.
	 * 
	 * [UC0980] - Gerar Resumo Simulação do Faturamento
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 19/01/2010
	 * 
	 * @param colecaoFaturamentoAtividadeCronogramaRota
	 * @param faturamentoGrupo
	 * @param anoMesReferenciaFaturamento
	 * @param atividade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoSimulacaoFaturamento(
			Collection colecaoFaturamentoAtividadeCronogramaRota,
			FaturamentoGrupo faturamentoGrupo, int atividade,
			int idFuncionalidadeIniciada) throws ControladorException {

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		int idUnidadeIniciada = 0;
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.ROTA,
						((FaturamentoAtivCronRota) Util
								.retonarObjetoDeColecao(colecaoFaturamentoAtividadeCronogramaRota))
								.getRota().getId());

		try {

			SistemaParametro sistemaParametro = getControladorUtil()
					.pesquisarParametrosDoSistema();

			/*
			 * Caso a coleção de atividade de faturamento de cronograma para
			 * rota não esteja nula para cada rota informada seleciona as
			 * quadras da rota e para cada quadra os imóveis
			 */
			if (colecaoFaturamentoAtividadeCronogramaRota != null
					&& !colecaoFaturamentoAtividadeCronogramaRota.isEmpty()) {

				Iterator iteratorColecaoFaturamentoAtividadeCronogramaRota = colecaoFaturamentoAtividadeCronogramaRota
						.iterator();

				// Objeto que armazenará as informações para deleção das contas
				ApagarDadosFaturamentoHelper helper = new ApagarDadosFaturamentoHelper();

				// LAÇO PARA FATURAR TODAS AS ROTAS
				while (iteratorColecaoFaturamentoAtividadeCronogramaRota
						.hasNext()) {

					FaturamentoAtivCronRota faturamentoAtivCronRota = (FaturamentoAtivCronRota) iteratorColecaoFaturamentoAtividadeCronogramaRota
							.next();

					helper.setRota(faturamentoAtivCronRota.getRota());
					helper.setAnoMesFaturamento(faturamentoGrupo
							.getAnoMesReferencia());
					helper
							.setIdDebitoCreditoSituacaoAtual(DebitoCreditoSituacao.NORMAL);

					// APAGAR DADOS GERADOS DO RESUMO DA SIMULAÇÃO PARA A ROTA
					// NO ANO/MES DE REFERENCIA DO FATURAMENTO
					// =================================================================================================
					this.apagarDadosGeradosResumoFaturamentoSimulacaoDetalhe(
							faturamentoGrupo.getId(), helper);
					this.apagarDadosGeradosResumoFaturamentoSimulacao(
							faturamentoGrupo.getId(), helper);

					/*
					 * Caso o mês de faturamento corresponda ao mês de novembro,
					 * o sistema exclui também os dados do resumo da simulação
					 * do faturamento do mês de dezembro.
					 */
					if (Util.obterMes(faturamentoGrupo.getAnoMesReferencia()) == ConstantesSistema.NOVEMBRO) {

						helper
								.setIdDebitoCreditoSituacaoAtual(DebitoCreditoSituacao.NORMAL);

						// Cria o ano/mês de referência para dezembro do ano
						// informado
						helper.setAnoMesFaturamento(Util
								.somaUmMesAnoMesReferencia(faturamentoGrupo
										.getAnoMesReferencia()));

						// APAGAR DADOS GERADOS DO RESUMO DA SIMULAÇÃO PARA A
						// ROTA NO ANO/MES DE REFERENCIA DO FATURAMENTO
						// =================================================================================================
						this
								.apagarDadosGeradosResumoFaturamentoSimulacaoDetalhe(
										faturamentoGrupo.getId(), helper);
						this.apagarDadosGeradosResumoFaturamentoSimulacao(
								faturamentoGrupo.getId(), helper);

					}

					// Variáveis para a paginação da pesquisa de Imovel por
					// Grupo Faturamento
					// ========================================================================
					boolean flagTerminou = false;
					final int quantidadeRegistros = 500;
					int numeroIndice = 0;
					// ========================================================================

					while (!flagTerminou) {

						//RM3656 - Alterado para pegar os imóveis atraves da conta
						Collection colecaoImoveisResumoFaturamento = 
								this.pesquisarImovelResumoFaturamento(
										faturamentoAtivCronRota.getRota().getId(),
										faturamentoGrupo.getAnoMesReferencia(),
										numeroIndice, quantidadeRegistros);

						// Resumos de faturamento para simulação.
						Collection colecaoResumoFaturamento = new ArrayList();

						/*
						 * Caso exista ids de imóveis para a rota atual
						 * determina o faturamento para cada imóvel retornado.
						 */
						if (colecaoImoveisResumoFaturamento != null && !colecaoImoveisResumoFaturamento.isEmpty()) {

							Iterator iteratorColecaoImoveis = colecaoImoveisResumoFaturamento
									.iterator();

							// LAÇO PARA DETERMINAR O FATURAMENTO DE TODOS OS
							// IMOVEIS DA ROTA ATUAL

							int count = 1;
							Imovel imovel = null;
							while (iteratorColecaoImoveis.hasNext()) {

								imovel = (Imovel) iteratorColecaoImoveis.next();

								// System.out.println("CONTADOR :"+count);

								// Resumo Simulacao Faturamento
								this
										.pesquisarContasGerarResumoSimulacaoFaturamento(
												faturamentoGrupo
														.getAnoMesReferencia(),
												sistemaParametro,
												faturamentoAtivCronRota,
												colecaoResumoFaturamento,
												imovel, faturamentoGrupo, false);

								// Resumo Simulacao Faturamento Antecipado
								this
										.pesquisarContasGerarResumoSimulacaoFaturamentoAntecipado(
												faturamentoGrupo
														.getAnoMesReferencia(),
												sistemaParametro,
												faturamentoAtivCronRota,
												colecaoResumoFaturamento,
												imovel, faturamentoGrupo);

								count++;

							}// FIM DO LOOP DE IMOVEIS

						}// FIM DO LOOP DE IMOVEIS

						/*
						 * Caso a coleção de resumo de faturamento não esteja
						 * vazia ou nula inseri os resumos na base de dados.
						 */
						if (colecaoResumoFaturamento != null
								&& !colecaoResumoFaturamento.isEmpty()) {

							this
									.inserirResumoSimulacaoFaturamento(colecaoResumoFaturamento);

							if (colecaoResumoFaturamento != null) {
								colecaoResumoFaturamento.clear();
								colecaoResumoFaturamento = null;
							}
						}

						/**
						 * Incrementa o nº do indice da páginação
						 */
						numeroIndice = numeroIndice + quantidadeRegistros;

						/**
						 * Caso a coleção de imoveis retornados for menor que a
						 * quantidade de registros seta a flag indicando que a
						 * paginação terminou.
						 */
						if (colecaoImoveisResumoFaturamento == null
								|| colecaoImoveisResumoFaturamento.size() < quantidadeRegistros) {

							flagTerminou = true;
						}

						if (colecaoImoveisResumoFaturamento != null) {
							colecaoImoveisResumoFaturamento.clear();
							colecaoImoveisResumoFaturamento = null;
						}

					}// FIM DO LOOP DA PAGINAÇÃO
				}
			} else {
				// A LISTA COM AS ROTAS ESTÁ NULA OU VAZIA

				throw new ControladorException(
						"atencao.pesquisa.grupo_rota_vazio");
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {

			/*
			 * Este catch serve para interceptar qualquer exceção que o processo
			 * batch venha a lançar e garantir que a unidade de processamento do
			 * batch será atualizada com o erro ocorrido.
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);
		}

	}

	/**
	 * Recupera as contas dos imóveis selecionados que tenham o mês ano de
	 * referência e que estejam com a situação atual igual a normal ou situação
	 * anterior igual a normal. E gera o resumo simulação do faturamento.
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 20/01/2010
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public void pesquisarContasGerarResumoSimulacaoFaturamento(
			Integer anoMesReferencia, SistemaParametro sistemaParametro,
			FaturamentoAtivCronRota faturamentoAtivCronRota,
			Collection colecaoResumoFaturamento, Imovel imovel,
			FaturamentoGrupo faturamentoGrupo, boolean faturamentoAntecipado)
			throws ControladorException {

		GerarResumoSimulacaoFaturamentoHelper gerarResumoSimulacaoFaturamentoHelper = null;

		try {

			Object[] retornoPesquisa = repositorioFaturamento
					.pesquisarContasResumoSimulacaoFaturamento(imovel.getId(),
							anoMesReferencia);

			if (retornoPesquisa != null && retornoPesquisa.length > 0) {

				gerarResumoSimulacaoFaturamentoHelper = new GerarResumoSimulacaoFaturamentoHelper(
						(Integer) retornoPesquisa[0],
						(Integer) retornoPesquisa[1],
						(Integer) retornoPesquisa[2],
						(Integer) retornoPesquisa[3],
						(Integer) retornoPesquisa[4],
						(Integer) retornoPesquisa[5],
						(Integer) retornoPesquisa[6],
						(Integer) retornoPesquisa[7],
						(Integer) retornoPesquisa[8],
						(Integer) retornoPesquisa[9],
						(Integer) retornoPesquisa[10],
						(Integer) retornoPesquisa[11],
						(Integer) retornoPesquisa[12],
						(Integer) retornoPesquisa[13],
						(Short) retornoPesquisa[14],
						(Integer) retornoPesquisa[15],
						(BigDecimal) retornoPesquisa[16],
						(Integer) retornoPesquisa[17],
						(BigDecimal) retornoPesquisa[18],
						(Integer) retornoPesquisa[19],
						(BigDecimal) retornoPesquisa[20],
						(BigDecimal) retornoPesquisa[21],
						(BigDecimal) retornoPesquisa[22]);

				// Valor dos Debitos
				FiltroDebitoCobrado filtroDebitoCobrado = new FiltroDebitoCobrado();

				filtroDebitoCobrado.adicionarParametro(new ParametroSimples(
						FiltroDebitoCobrado.CONTA_ID,
						gerarResumoSimulacaoFaturamentoHelper.getIdConta()));

				filtroDebitoCobrado
						.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoCobrado.DEBITO_TIPO);

				Collection<DebitoCobrado> colecaoDebitos = this
						.getControladorUtil().pesquisar(filtroDebitoCobrado,
								DebitoCobrado.class.getName());

				Map<DebitoTipo, BigDecimal> mapValoresPorTipoDebito = null;

				if (!Util.isVazioOrNulo(colecaoDebitos)) {

					mapValoresPorTipoDebito = new HashMap<DebitoTipo, BigDecimal>();

					for (DebitoCobrado debitoCobrado : colecaoDebitos) {
						if (mapValoresPorTipoDebito.containsKey(debitoCobrado
								.getDebitoTipo())) {
							BigDecimal valor = mapValoresPorTipoDebito
									.get(debitoCobrado.getDebitoTipo());
							mapValoresPorTipoDebito.put(debitoCobrado
									.getDebitoTipo(), Util.somaBigDecimal(
									valor, debitoCobrado.getValorPrestacao()));
						}
						// Caso contrario inseri na coleção
						// primeiro registro do tipo.
						else {
							mapValoresPorTipoDebito.put(debitoCobrado
									.getDebitoTipo(), debitoCobrado
									.getValorPrestacao());
						}
					}
				}

				GerarDebitoCobradoHelper gerarDebitoCobradoHelper = new GerarDebitoCobradoHelper();

				gerarDebitoCobradoHelper
						.setValorTotalDebito(gerarResumoSimulacaoFaturamentoHelper
								.getValorDebitos());

				gerarDebitoCobradoHelper
						.setMapValoresPorTipoDebito(mapValoresPorTipoDebito);

				GerarImpostosDeduzidosContaHelper helperImpostos = new GerarImpostosDeduzidosContaHelper();

				helperImpostos
						.setValorTotalImposto(gerarResumoSimulacaoFaturamentoHelper
								.getValorImpostos());

				gerarDebitoCobradoHelper
						.setGerarImpostosDeduzidosContaHelper(helperImpostos);

				// Valor dos Creditos
				FiltroCreditoRealizado filtroCreditoRealizado = new FiltroCreditoRealizado();

				filtroCreditoRealizado.adicionarParametro(new ParametroSimples(
						FiltroCreditoRealizado.CONTA_ID,
						gerarResumoSimulacaoFaturamentoHelper.getIdConta()));

				filtroCreditoRealizado
						.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoRealizado.CREDITO_TIPO);

				Collection<CreditoRealizado> colecaoCreditos = this
						.getControladorUtil().pesquisar(filtroCreditoRealizado,
								CreditoRealizado.class.getName());

				Map<CreditoTipo, BigDecimal> mapValoresPorTipoCredito = null;

				if (!Util.isVazioOrNulo(colecaoCreditos)) {

					mapValoresPorTipoCredito = new HashMap<CreditoTipo, BigDecimal>();

					for (CreditoRealizado creditoRealizado : colecaoCreditos) {
						if (mapValoresPorTipoCredito
								.containsKey(creditoRealizado.getCreditoTipo())) {
							BigDecimal valor = mapValoresPorTipoCredito
									.get(creditoRealizado.getCreditoTipo());
							mapValoresPorTipoCredito.put(creditoRealizado
									.getCreditoTipo(), Util.somaBigDecimal(
									valor, creditoRealizado.getValorCredito()));
						}
						// Caso contrario inseri na coleção
						// primeiro registro do tipo.
						else {
							mapValoresPorTipoCredito.put(creditoRealizado
									.getCreditoTipo(), creditoRealizado
									.getValorCredito());
						}
					}
				}

				GerarCreditoRealizadoHelper gerarCreditoRealizadoHelper = new GerarCreditoRealizadoHelper();

				gerarCreditoRealizadoHelper
						.setValorTotalCredito(gerarResumoSimulacaoFaturamentoHelper
								.getValorCreditos());

				gerarCreditoRealizadoHelper
						.setMapValoresPorTipoCredito(mapValoresPorTipoCredito);

				// Pesquisa as categorias da conta
				Collection colecaoContaCategoria = repositorioFaturamento
						.pesquisarContaCategoria(gerarResumoSimulacaoFaturamentoHelper
								.getIdConta());

				Conta conta = new Conta();
				conta.setId(gerarResumoSimulacaoFaturamentoHelper.getIdConta());

				Collection colecaoCategoriasConta = this.getControladorImovel()
						.obterQuantidadeEconomiasContaCategoria(conta);

				Collection colecaoCategoriasOrdenado = new ArrayList();

				DeterminarValoresFaturamentoAguaEsgotoHelper helperValoresAguaEsgoto = new DeterminarValoresFaturamentoAguaEsgotoHelper();

				Collection colecaoCalcularValoresAguaEsgotoHelper = new ArrayList();

				if (colecaoContaCategoria != null
						&& !colecaoContaCategoria.isEmpty()) {

					Iterator colecaoContaCategoriaIterator = colecaoContaCategoria
							.iterator();

					while (colecaoContaCategoriaIterator.hasNext()) {

						ContaCategoria contaCategoria = (ContaCategoria) colecaoContaCategoriaIterator
								.next();

						CalcularValoresAguaEsgotoHelper calcularValoresAguaEsgotoHelper = new CalcularValoresAguaEsgotoHelper();

						calcularValoresAguaEsgotoHelper
								.setIdCategoria(contaCategoria.getComp_id()
										.getCategoria().getId());

						calcularValoresAguaEsgotoHelper
								.setConsumoFaturadoAguaCategoria(contaCategoria
										.getConsumoAgua());

						calcularValoresAguaEsgotoHelper
								.setConsumoFaturadoEsgotoCategoria(contaCategoria
										.getConsumoEsgoto());

						calcularValoresAguaEsgotoHelper
								.setValorFaturadoAguaCategoria(contaCategoria
										.getValorAgua());

						calcularValoresAguaEsgotoHelper
								.setValorFaturadoEsgotoCategoria(contaCategoria
										.getValorEsgoto());

						colecaoCalcularValoresAguaEsgotoHelper
								.add(calcularValoresAguaEsgotoHelper);

						// [UC0108] - Obter Quantidade de Economias por
						// Categoria
						if (colecaoCategoriasConta != null
								&& !colecaoCategoriasConta.isEmpty()) {

							Iterator colecaoCategoriaContaIterator = colecaoCategoriasConta
									.iterator();

							while (colecaoCategoriaContaIterator.hasNext()) {
								Categoria categoria = (Categoria) colecaoCategoriaContaIterator
										.next();

								if (categoria.getId().intValue() == contaCategoria
										.getComp_id().getCategoria().getId()
										.intValue()) {

									colecaoCategoriasOrdenado.add(categoria);

									break;
								}
							}
						}
					}

				}

				helperValoresAguaEsgoto
						.setColecaoCalcularValoresAguaEsgotoHelper(colecaoCalcularValoresAguaEsgotoHelper);

				Integer anoMesReferenciaResumoFaturamento = null;
				if (faturamentoAntecipado) {
					anoMesReferenciaResumoFaturamento = anoMesReferencia;
				}

				// [SB0009] - Gerar Resumo da Simulação do Faturamento
				this.gerarResumoFaturamentoSimulacao(colecaoCategoriasOrdenado,
						helperValoresAguaEsgoto
								.getColecaoCalcularValoresAguaEsgotoHelper(),
						gerarDebitoCobradoHelper, gerarCreditoRealizadoHelper,
						colecaoResumoFaturamento, imovel, true,
						faturamentoAtivCronRota, faturamentoGrupo,
						anoMesReferenciaResumoFaturamento, false);

			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Este caso de uso permite gerar resumo simulacao faturamento de um imóvel
	 * de um grupo de faturamento de forma antecipada.
	 * 
	 * 
	 * @author Fernando Fontelles
	 * @date 26/01/2010
	 * 
	 * @param anoMesFaturamento
	 * @param atividade
	 * @param sistemaParametro
	 * @param faturamentoAtivCronRota
	 * @param colecaoResumoFaturamento
	 * @param imovel
	 * @throws ControladorException
	 */
	public void pesquisarContasGerarResumoSimulacaoFaturamentoAntecipado(
			Integer anoMesReferencia, SistemaParametro sistemaParametro,
			FaturamentoAtivCronRota faturamentoAtivCronRota,
			Collection colecaoResumoFaturamento, Imovel imovel,
			FaturamentoGrupo faturamentoGrupo) throws ControladorException {

		/*
		 * Caso o mês de faturamento corresponda ao mês de novembro, o sistema
		 * verifica se haverá faturamento antecipado
		 */
		if (Util.obterMes(anoMesReferencia) == ConstantesSistema.NOVEMBRO
				&& sistemaParametro.getIndicadorFaturamentoAntecipado().equals(
						ConstantesSistema.SIM)) {

			Integer anoMesFaturamentoAntecipado = Util
					.somaUmMesAnoMesReferencia(anoMesReferencia);

			Integer idConsumoHistorico = getControladorMicromedicao()
					.pesquisarConsumoHistoricoAntecipado(imovel.getId(),
							anoMesFaturamentoAntecipado);

			if (idConsumoHistorico != null) {

				this.pesquisarContasGerarResumoSimulacaoFaturamento(
						anoMesFaturamentoAntecipado, sistemaParametro,
						faturamentoAtivCronRota, colecaoResumoFaturamento,
						imovel, faturamentoGrupo, true);
			}
		}
	}

	/**
	 * 
	 * [UC0394] Gerar Débitos a Cobrar de Doações
	 * 
	 * Efetua cancelamento do imovel caso ano/mês final de adesão seja menor ou
	 * igual ano/mês do grupo de faturamento da rota
	 * 
	 * @author Hugo Amorim
	 * @date 02/02/2010
	 * 
	 */
	private void efetuarCancelamentoImovelParaDoacoes(
			ImovelCobrarDoacaoHelper imovelCobrarDoacaoHelper, Rota rota)
			throws ControladorException {

		FiltroImovelDoacao filtroImovelDoacao = new FiltroImovelDoacao();

		filtroImovelDoacao.adicionarParametro(new ParametroSimples(
				FiltroImovelDoacao.ID, imovelCobrarDoacaoHelper
						.getIdImovelDoacao()));

		Collection colecaoImovelDoacao = this.getControladorUtil().pesquisar(
				filtroImovelDoacao, ImovelDoacao.class.getName());

		ImovelDoacao imovelDoacao = (ImovelDoacao) Util
				.retonarObjetoDeColecao(colecaoImovelDoacao);

		if (imovelDoacao != null
				&& imovelDoacao.getAnoMesReferenciaFinal() != null
				&& !imovelDoacao.getAnoMesReferenciaFinal().toString().equals(
						"")) {

			FiltroFaturamentoGrupo filtro = new FiltroFaturamentoGrupo();

			filtro.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.ID, rota.getFaturamentoGrupo()
							.getId()));

			Collection colecaoFaturamentoGrupo = this.getControladorUtil()
					.pesquisar(filtro, FaturamentoGrupo.class.getName());

			FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util
					.retonarObjetoDeColecao(colecaoFaturamentoGrupo);

			// Ano/mês final da adesão do imóvel seja diferente de nulo
			// e que seja o menor ou igual o ano/mês de referência do grupo de
			// faturamento da rota.
			if (imovelDoacao.getAnoMesReferenciaFinal().compareTo(
					faturamentoGrupo.getAnoMesReferencia()) == 0
					|| imovelDoacao.getAnoMesReferenciaFinal().compareTo(
							faturamentoGrupo.getAnoMesReferencia()) < 0) {

				imovelDoacao.setDataCancelamento(new Date());
				imovelDoacao.setUsuarioCancelamento(Usuario.USUARIO_BATCH);

				this.getControladorUtil().atualizar(imovelDoacao);
			}

		}

	}

	/**
	 * Este caso de uso permite enviar email para cliente informando que sua
	 * conta já foi gerada. Retorno do celular
	 * 
	 * @author Raphael Rossiter
	 * @date 03/03/2010
	 * 
	 * @param rota
	 * @param colContaPreFaturada
	 * @param efetuarRateio
	 * @param atualizaSituacaoAtualConta -
	 *            Caso seja chamado via a funcionalidade de ISC, não atualiza a
	 *            situação atual da conta que não foi impressa. Caso seja
	 *            chamado via a funcionalidade de consistir, atualiza a situação
	 *            atual da conta.
	 * @throws ControladorException
	 */
	public void processarMovimentoContaPrefaturada(Rota rota,
			Collection<MovimentoContaPrefaturada> colContaPreFaturada,
			boolean efetuarRateio, boolean vincularConsumo) throws ControladorException {

		try {

			if (colContaPreFaturada != null && !colContaPreFaturada.isEmpty()) {

				Collection<Imovel> colImoveisCondominio = new ArrayList();
				Collection<Imovel> colImoveis = new ArrayList();

				Collection<DadosMovimentacao> colecaoDadosMovimentacao = new ArrayList();

				Long imei = null;
				if (rota != null && rota.getLeiturista() != null
						&& !rota.getLeiturista().equals("")) {
					imei = rota.getLeiturista().getNumeroImei();
				}

				Collection<Integer> colIdImoveis = new ArrayList();

				for (MovimentoContaPrefaturada movimentoContaPreFaturada : colContaPreFaturada) {

					if (!colIdImoveis.contains(movimentoContaPreFaturada
							.getImovel().getId())) {
						colIdImoveis.add(movimentoContaPreFaturada.getImovel()
								.getId());
						colImoveis.add(movimentoContaPreFaturada.getImovel());
					}

					// 3.1
					FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
					filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
							FiltroLigacaoAgua.ID, movimentoContaPreFaturada
									.getImovel().getId()));
					filtroLigacaoAgua.adicionarParametro(new ParametroNaoNulo(
							FiltroLigacaoAgua.HIDROMETRO_INSTALACAO_HISTORICO));
					Collection<LigacaoAgua> colLigacaoAgua = this.repositorioUtil
							.pesquisar(filtroLigacaoAgua, LigacaoAgua.class
									.getName());

					if ((colLigacaoAgua != null && colLigacaoAgua.size() > 0)
							|| movimentoContaPreFaturada.getImovel()
									.getHidrometroInstalacaoHistorico() != null) {

						if ((movimentoContaPreFaturada.getMedicaoTipo().getId() == MedicaoTipo.LIGACAO_AGUA
								.intValue()
								&& colLigacaoAgua != null && colLigacaoAgua
								.size() > 0)
								|| (movimentoContaPreFaturada.getMedicaoTipo()
										.getId() == MedicaoTipo.POCO.intValue() && movimentoContaPreFaturada
										.getImovel()
										.getHidrometroInstalacaoHistorico() != null)) {
							incluirMedicaoHistorico(movimentoContaPreFaturada);
						}
					} else {

						if (movimentoContaPreFaturada
								.getLeituraAnormalidadeLeitura() != null
								&& movimentoContaPreFaturada
										.getLeituraAnormalidadeLeitura()
										.getId() != null
								&& !movimentoContaPreFaturada
										.getLeituraAnormalidadeLeitura()
										.getId().equals("")) {
							Imovel imovel = movimentoContaPreFaturada
									.getImovel();
							imovel
									.setLeituraAnormalidade(movimentoContaPreFaturada
											.getLeituraAnormalidadeLeitura());
							imovel.setUltimaAlteracao(new Date());

							// repositorioUtil.atualizar( imovel );
							RepositorioImovelHBM
									.getInstancia()
									.atualizarImovelLeituraAnormalidadeProcessoMOBILE(
											imovel);
						}
					}

					// Verificamos se o imovel atual é um imovel do tipo imovel
					// condominio
					if (movimentoContaPreFaturada.getImovel()
							.getIndicadorImovelCondominio().equals(
									ConstantesSistema.SIM)) {

						boolean achou = false;

						for (Imovel imovel : colImoveisCondominio) {
							if (imovel.getId() == movimentoContaPreFaturada
									.getImovel().getId()) {
								achou = true;
								break;
							}
						}

						if (!achou) {
							colImoveisCondominio.add(movimentoContaPreFaturada
									.getImovel());
						}
						// imovelCondominio =
						// movimentoContaPreFaturada.getImovel();
					}

					Integer idAnormalidade = null;
					if (movimentoContaPreFaturada
							.getLeituraAnormalidadeLeitura() != null) {
						idAnormalidade = movimentoContaPreFaturada
								.getLeituraAnormalidadeLeitura().getId();
					}

					Byte indicadorConfirmacao = new Byte("0");
					if (movimentoContaPreFaturada.getIndicadorSituacaoLeitura() != null
							&& !movimentoContaPreFaturada
									.getIndicadorSituacaoLeitura().equals("")) {
						indicadorConfirmacao = new Byte(""
								+ movimentoContaPreFaturada
										.getIndicadorSituacaoLeitura());
					}

					Integer idMedicaoTipo = null;
					if ((movimentoContaPreFaturada.getLeituraHidrometro() != null && !movimentoContaPreFaturada
							.getLeituraHidrometro().equals(""))
							|| (movimentoContaPreFaturada
									.getLeituraAnormalidadeLeitura() != null
									&& movimentoContaPreFaturada
											.getLeituraAnormalidadeLeitura()
											.getId() != null && !movimentoContaPreFaturada
									.getLeituraAnormalidadeLeitura().getId()
									.equals(""))) {
						idMedicaoTipo = movimentoContaPreFaturada
								.getMedicaoTipo().getId();
					}

					// Atualiza os dados de movimento Roteiro empresa
					DadosMovimentacao dadosMovimentacao = new DadosMovimentacao(
							movimentoContaPreFaturada.getImovel().getId(),
							movimentoContaPreFaturada.getLeituraHidrometro(),
							idAnormalidade, movimentoContaPreFaturada
									.getDataHoraLeitura(), imei,
							indicadorConfirmacao, idMedicaoTipo);

					colecaoDadosMovimentacao.add(dadosMovimentacao);

				}

				// ataliza movimento roteiro empresa
				if (colecaoDadosMovimentacao != null
						&& !colecaoDadosMovimentacao.isEmpty()) {
					getControladorMicromedicao().atualizarRoteiro(
							colecaoDadosMovimentacao, true);
				}

				// 3.3.1
				if (colIdImoveis != null && !colIdImoveis.isEmpty()) {
					this.getControladorMicromedicao()
							.consistirLeiturasCalcularConsumosImoveis(
									rota.getFaturamentoGrupo(), colIdImoveis);
					
					this.atualizarFaturamentoImoveisCortados(colImoveis, rota
							.getFaturamentoGrupo().getAnoMesReferencia()
							.intValue());
				}
				// 3.3.2
				// Verificamos se devemos efetuar o rateio
				if (colImoveisCondominio != null
						&& colImoveisCondominio.size() > 0 && efetuarRateio) {
					for (Imovel imovelCondominio : colImoveisCondominio) {
						this.getControladorMicromedicao()
								.efetuarRateioDeConsumo(
										imovelCondominio.getId(),
										rota.getFaturamentoGrupo()
												.getAnoMesReferencia());
					}
				}

				// Atualiza o cronograma da faturamento para as atividade
				// Efetuar,Registrar e Consistir.
				for (MovimentoContaPrefaturada movimentoContaPreFaturada : colContaPreFaturada) {
					Date dataLeituraAtual = movimentoContaPreFaturada
							.getDataHoraLeitura();
					if (dataLeituraAtual == null || dataLeituraAtual.equals("")) {
						dataLeituraAtual = new Date();
					}
					getControladorMicromedicao()
							.atualizarDataRealizacaoGronogramaPreFaturamento(
									rota.getFaturamentoGrupo().getId(),
									rota.getFaturamentoGrupo()
											.getAnoMesReferencia(),
									dataLeituraAtual);
					break;
				}

				// 3.3.4
				this.atualizarMovimentoCelular(colContaPreFaturada, efetuarRateio);
				
				/*
				 * Colocado por Raphael Rossiter em 05/02/2014
				 * OBJ: Vincular o consumo do macro com o consumo do micro nos casos onde o rateio tenha sido cobrado em campo mas no
				 * retorno do celular o GSAN não tenha conseguido atualizar o faturamento. Lembrando que essa vinculação só será
				 * feita quando rodar o batch consistir leituras e calcular consumos
				 */
				if (colIdImoveis != null && !colIdImoveis.isEmpty() && rota.getLeituraTipo().getId().intValue() == LeituraTipo.CELULAR_MOBILE_ANDROID ){
					
					if (vincularConsumo){
						
						Integer idImovel = (Integer) Util.retonarObjetoDeColecao(colIdImoveis);
						
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						
						Integer idImovelCondominio = this.getControladorImovel().pesquisarImovelCondominio(idImovel);
						
						if (idImovelCondominio != null){
							
							Imovel imovelCondominio = new Imovel();
							imovelCondominio.setId(idImovelCondominio);
							
							imovel.setImovelCondominio(imovelCondominio);
						}
						
						
						this.getControladorMicromedicao().vincularConsumoImovelMicro(colContaPreFaturada, imovel);
					}
					
					colImoveis = null;
				}

				// 3.3.4
				// Alterado por Raphael Rossiter em conjunto com Sávio e Eduardo
				// em 29/07/2011
				
				// não atualizar o indicador de atualizacao de faturamento caso
				// indicador de emissao de conta seja igual a 2 e o imóvel não
				// esteja vinculado com nenhuma outra matrícula (Imóvel micro)
				// ou venha pela funcionalidade de consistir
				for (MovimentoContaPrefaturada movimentoContaPreFaturada : colContaPreFaturada) {
					
					if (movimentoContaPreFaturada.getMovimentoContaPrefaturadaCategorias() != null && 
						movimentoContaPreFaturada.getMovimentoContaPrefaturadaCategorias().size() > 0) {

						if ((movimentoContaPreFaturada.getIndicadorEmissaoConta().shortValue() == ConstantesSistema.SIM.shortValue()) ||
							(movimentoContaPreFaturada.getIndicadorEmissaoConta().shortValue() == ConstantesSistema.NAO.shortValue() &&
							 movimentoContaPreFaturada.getImovel().getImovelCondominio() != null &&
							 !movimentoContaPreFaturada.getRota().getLeituraTipo().getId().equals(LeituraTipo.LEITURA_ANDROID))){
						
							movimentoContaPreFaturada.setUtlimaAlteracao(new Date());
							movimentoContaPreFaturada.setIndicadorAtualizacaoFaturamento(Short.parseShort("1"));
							
							repositorioFaturamento.atualizarMovimentoContaPrefaturadaProcessoMOBILE(movimentoContaPreFaturada);
						}
					}
				}
			}

		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0994] - Envio de Email da Conta para o Cliente
	 * 
	 * @author Fernando Fontelles
	 * @date 02/03/2010
	 * @param colecaoFaturamentoAtividadeCronogramaRota
	 * @param localidade
	 * @param atividade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void envioEmailContaParaCliente(
			Localidade localidade,
			FaturamentoGrupo faturamentoGrupo,
			int idFuncionalidadeIniciada) throws ControladorException {

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		int idUnidadeIniciada = 0;
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.LOCALIDADE,
						localidade.getId());

		try {

			System.out.println("LOCALIDADE: " + localidade.getId());


			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			
			Collection colecaoImoveisEmails = repositorioFaturamento
					.pesquisarContasImpressasParaEnvioEmail(localidade.getId(), 
							sistemaParametro, faturamentoGrupo);
			
			/*
			 * Collection colecaoImoveisEmails = repositorioFaturamento
			 * .pesquisarContasImpressasParaEnvioEmail(idRota,
			 * faturamentoGrupo.getAnoMesReferencia());
			 */

			Collection colecaoImoveisEmailsPreFaturados = repositorioFaturamento
					.pesquisarContasPrefaturadasParaEnvioEmail(sistemaParametro, localidade.getId(), faturamentoGrupo);

			// Adiciona os imoveis PreFaturados a colecao de imoveis para envio
			// de email
			if (colecaoImoveisEmailsPreFaturados != null
					&& !colecaoImoveisEmailsPreFaturados.isEmpty()) {

				colecaoImoveisEmails.addAll(colecaoImoveisEmailsPreFaturados);

			}

			if (colecaoImoveisEmails != null && !colecaoImoveisEmails.isEmpty()) {

				Iterator colecaoImoveisEmailsIterator = colecaoImoveisEmails.iterator();

				while (colecaoImoveisEmailsIterator.hasNext()) {

					Object[] imoveisEmails = (Object[]) colecaoImoveisEmailsIterator.next();

					Integer idImovel = (Integer) imoveisEmails[0];
					String emailReceptor = (String) imoveisEmails[1];										

					String mesAnoFormatado = Util.formatarAnoMesParaMesAno(faturamentoGrupo.getAnoMesReferencia());

					Conta conta = this.pesquisarContaDigitada(idImovel.toString(), mesAnoFormatado);
					
					try {

						// Envia de Arquivo por email
						EnvioEmail envioEmail = this
								.getControladorCadastro()
								.pesquisarEnvioEmail(EnvioEmail.ENVIO_EMAIL_CONTA_PARA_CLIENTE);

						String emailRemetente = envioEmail.getEmailRemetente();
						String tituloMensagem = envioEmail.getTituloMensagem();
						String corpoMensagem = "A "
											 + sistemaParametro.getNomeEmpresa()
											 + " informa que a conta do imóvel de matrícula "
											 + idImovel
											 + " está disponível para acessá-la na página da internet "
											 + sistemaParametro.getUrlAcessoInternet() +
											 // +sistemaParametro.getUrl2ViaConta()+"&idImovel="+idImovel
											 "/gerarRelatorio2ViaContaAction.do?cobrarTaxaEmissaoConta=N&idConta="
											 + conta.getId();

						ServicosEmail.enviarMensagem(emailRemetente,
								emailReceptor, tituloMensagem, corpoMensagem);
						
						FiltroConta filtroConta = new FiltroConta();
						filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, conta.getId()));
						
						Conta c = (Conta) Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtroConta, Conta.class.getName()));
						c.setDataEnvioEmailConta(new Date());
						c.setUltimaAlteracao(new Date());
						
						this.getControladorUtil().atualizar(c);

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Erro ao enviar email.");
					}

				}

			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {

			/*
			 * Este catch serve para interceptar qualquer exceção que o processo
			 * batch venha a lançar e garantir que a unidade de processamento do
			 * batch será atualizada com o erro ocorrido.
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new EJBException(e);
		}

	}

	/**
	 * 
	 * [UC0993] Consultar Faturamento Imediato Ajuste
	 * 
	 * @author Hugo Leonardo
	 * @throws ControladorException
	 * @throws ControladorException
	 * @data 26/02/2010
	 * 
	 */
	public Collection pesquisarFaturamentoImediatoAjuste(
			FaturamentoImediatoAjusteHelper helper, int qtd)
			throws ControladorException {

		Collection colecaoRetorno = new ArrayList();

		// Pesquisa
		try {
			Collection faturamentoImediatoAjuste = repositorioFaturamento
					.pesquisarFaturamentoImediatoAjuste(helper
							.getMesAnoReferencia().toString(), helper
							.getFaturamentoGrupo() != null ? helper
							.getFaturamentoGrupo().toString() : null, helper
							.getImovelId() != null ? helper.getImovelId()
							.toString() : null,
							helper.getRotaId() != null ? helper.getRotaId()
									.toString() : null, qtd);

			Iterator iterator = faturamentoImediatoAjuste.iterator();

			while (iterator.hasNext()) {

				FaturamentoImediatoAjusteHelper relatorioHelper = new FaturamentoImediatoAjusteHelper();

				Object[] objeto = (Object[]) iterator.next();

				// Matricula do imóvel
				if (objeto[0] != null) {
					Integer imovelMatricula = (Integer) objeto[0];

					relatorioHelper.setImovelId(Util
							.retornaMatriculaImovelFormatada(imovelMatricula));
					relatorioHelper.setInscricao(this.getControladorImovel()
							.pesquisarInscricaoImovelExcluidoOuNao(
									imovelMatricula));
				}

				// Grupo Faturamento
				if (objeto[1] != null) {
					Integer faturamentoGrupo = (Integer) objeto[1];

					relatorioHelper.setFaturamentoGrupo(faturamentoGrupo
							.toString());
				}

				// Rota
				if (objeto[2] != null) {
					Short rota = (Short) objeto[2];

					relatorioHelper.setRota(rota.toString());
				}

				// Dif. Valor da Água
				if (objeto[3] != null) {
					BigDecimal valorAgua = (BigDecimal) objeto[3];

					relatorioHelper.setDifValorAgua(Util
							.formatarMoedaReal(valorAgua));
				} else {
					relatorioHelper.setDifValorAgua("0,00");
				}

				// Dif. Consumo de Água
				if (objeto[4] != null) {
					Integer consumoAgua = (Integer) objeto[4];

					relatorioHelper.setDifConsumoAgua(consumoAgua.toString());
				} else {
					relatorioHelper.setDifConsumoAgua("0");
				}

				// Dif. Valor do Esgoto
				if (objeto[5] != null) {
					BigDecimal valorEsgoto = (BigDecimal) objeto[5];

					relatorioHelper.setDifValorEsgoto(Util
							.formatarMoedaReal(valorEsgoto));
				} else {
					relatorioHelper.setDifValorEsgoto("0,00");
				}

				// Dif. Consumo de Esgoto
				if (objeto[6] != null) {
					Integer consumoEsgoto = (Integer) objeto[6];

					relatorioHelper.setDifConsumoEsgoto(consumoEsgoto
							.toString());
				} else {
					relatorioHelper.setDifConsumoEsgoto("0");
				}

				// anoMes referência
				if (objeto[7] != null) {
					Integer anoMes = (Integer) objeto[7];

					relatorioHelper.setMesAnoReferencia(Util
							.formatarAnoMesParaMesAno(anoMes));
				}

				colecaoRetorno.add(relatorioHelper);
			}

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return colecaoRetorno;
	}

	/**
	 * 
	 * [UC0993] Consultar Faturamento Imediato Ajuste
	 * 
	 * @author Hugo Leonardo
	 * @param form
	 * @throws ControladorException
	 * @throws ControladorException
	 * @data 01/03/2010
	 * 
	 */
	public Integer contarFaturamentoImediatoAjuste(
			FaturamentoImediatoAjusteHelper helper) throws ControladorException {

		try {
			Integer qtdSetores = this.repositorioFaturamento
					.contarFaturamentoImediatoAjuste(helper
							.getMesAnoReferencia().toString(), helper
							.getFaturamentoGrupo() != null ? helper
							.getFaturamentoGrupo().toString() : null, helper
							.getImovelId() != null ? helper.getImovelId()
							.toString() : null,
							helper.getRotaId() != null ? helper.getRotaId()
									.toString() : null);

			return qtdSetores;

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1001] Emitir declaração de quitação anual de débitos
	 * 
	 * Este caso de uso permite a geração de declaração de quitação de débitos.
	 * 
	 * @author Hugo Amorim
	 * @date 17/03/2010
	 */
	public void gerarDadosDeclaracaoQuitacaoAnualDebitos(
			int idFuncionalidadeIniciada, Collection<Integer> anos, Rota rota,
			Short indicadorContaParcelada, Short indicadorCobrancaJudical,
			Date dataVerificacaoPagamentos,SistemaParametro sistemaParametro) throws ControladorException {


		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.ROTA, rota.getId());
		try {

			for (Integer ano : anos) {

				// Variáveis para a paginação da pesquisa
				// ========================================================================
				boolean flagTerminou = false;
				final int quantidadeMaxima = 500;
				int quantidadeInicio = 0;
				// ========================================================================

				while (!flagTerminou) {

					Collection<Integer> colecaoIdsImoveis = this.repositorioFaturamento
							.pesquisarImoveisParaGeracaoDaDeclaracaodeQuitacaoDebitos(
									rota.getId(), quantidadeInicio,
									quantidadeMaxima);

					if (colecaoIdsImoveis != null
							&& !colecaoIdsImoveis.isEmpty()) {

						for (Integer idImovel : colecaoIdsImoveis) {

							// [FS0001] Declaração já gerada para o imovel no
							// ano de referencia
							if (declaracaoJaGeradaParaAnoReferencia(idImovel,
									ano)) {
								continue;
							}

							// O sistema deverá verificar para cada imóvel, se
							// todas as faturas foram pagas
							// no ano de referência nos meses de janeiro a
							// dezembro.
							DeclaracaoQuitacaoAnualDebitosHelper helper = this
									.pesquisarDadosParaGeracaoDaDeclaracaodeQuitacaoDebitos(
											idImovel, ano,
											dataVerificacaoPagamentos,
											indicadorContaParcelada,
											indicadorCobrancaJudical);

							boolean emiteExtratoQuitacao = false;
							
							if (helper.getExtratoQuitacaoItens().size() > 0 && helper.getExtratoQuitacaoItens().size() < 12){
								
								emiteExtratoQuitacao = this.repositorioFaturamento.verificarExistenciaContasNaoPagasDeclaracaoQuitacao(idImovel,
									ano + "%");
								
							} else if (helper.getExtratoQuitacaoItens().size() == 12){
								
								emiteExtratoQuitacao = true;
							}
							
							if (emiteExtratoQuitacao) {

								ExtratoQuitacao extratoQuitacao = new ExtratoQuitacao();

								extratoQuitacao.setImovel(new Imovel(helper
										.getIdImovel()));
								extratoQuitacao.setValorTotalDasContas(helper
										.getValorTotalContas());
								extratoQuitacao
										.setIndicadorImpressao(ConstantesSistema.NAO
												.intValue());
								extratoQuitacao.setAnoReferencia(ano);
								extratoQuitacao.setUltimaAlteracao(new Date());
								extratoQuitacao
										.setAnoMesMensagemConta(sistemaParametro
												.getAnoMesFaturamento());

								Integer idextratoQuitacao = (Integer) this
										.getControladorUtil().inserir(
												extratoQuitacao);

								extratoQuitacao.setId(idextratoQuitacao);

								for (Iterator iterator = helper
										.getExtratoQuitacaoItens().iterator(); iterator
										.hasNext();) {
									DeclaracaoQuitacaoAnualDebitosItemHelper helperItem = (DeclaracaoQuitacaoAnualDebitosItemHelper) iterator
											.next();

									ExtratoQuitacaoItem item = new ExtratoQuitacaoItem();

									item.setExtratoQuitacao(extratoQuitacao);

									ContaGeral contaGeral = new ContaGeral();
									contaGeral.setId(helperItem.getIdConta());

									item.setContaGeral(contaGeral);
									item.setValorConta(helperItem
											.getValorTotalConta());
									item.setDescricaoSituacao(helperItem
											.getDescricaoSituacao());
									item.setDataSituacao(helperItem
											.getDataSituacao());
									item.setUltimaAlteracao(new Date());
									item.setAnoMesReferenciaConta(helperItem
											.getAnoMesReferencia());

									this.getControladorUtil().inserir(item);

								}

							}

						}

					}

					// Incrementa o nº do indice da páginação
					quantidadeInicio = quantidadeInicio + quantidadeMaxima;

					/**
					 * Caso a coleção de dados retornados for menor que a
					 * quantidade de registros seta a flag indicando que a
					 * paginação terminou.
					 */
					if (colecaoIdsImoveis == null
							|| colecaoIdsImoveis.size() < quantidadeMaxima) {

						flagTerminou = true;
					}

					if (colecaoIdsImoveis != null) {
						colecaoIdsImoveis.clear();
						colecaoIdsImoveis = null;
					}

				}

			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {

			e.printStackTrace();

			// Este catch serve para interceptar
			// qualquer exceção que o processo batch
			// venha a lançar e garantir que a unidade
			// de processamento do batch será atualizada
			// com o erro ocorrido
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
		}
	}

	private boolean declaracaoJaGeradaParaAnoReferencia(Integer idImovel,
			Integer ano) throws ControladorException {
		boolean retorno = false;

		FiltroExtratoQuitacao filtro = new FiltroExtratoQuitacao();

		filtro.adicionarParametro(new ParametroSimples(
				FiltroExtratoQuitacao.ID_IMOVEL, idImovel));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroExtratoQuitacao.ANO_REFERENCIA, ano));

		Collection colecaoExtratoQuitacao = this.getControladorUtil()
				.pesquisar(filtro, ExtratoQuitacao.class.getName());

		if (colecaoExtratoQuitacao != null && !colecaoExtratoQuitacao.isEmpty()) {
			retorno = true;
		}

		return retorno;
	}

	/**
	 * [UC1001] Emitir declaração de quitação anual de débitos
	 * 
	 * Pequisa as contas do imovel e verifica se o mesmo esta de acordo com os
	 * parametros do caso de uso, se sim retorno uma coleção de dados para
	 * inserção.
	 * 
	 * @author Hugo Amorim
	 * @param indicadorCobrancaJudical
	 * @param indicadorContaParcelada
	 * @throws ErroRepositorioException
	 * @date 17/03/2010
	 */
	public DeclaracaoQuitacaoAnualDebitosHelper pesquisarDadosParaGeracaoDaDeclaracaodeQuitacaoDebitos(
			Integer idImovel, Integer ano, Date dataVerificacaoPagamentos,
			Short indicadorContaParcelada, Short indicadorCobrancaJudical)
			throws ControladorException {

		DeclaracaoQuitacaoAnualDebitosHelper retorno = new DeclaracaoQuitacaoAnualDebitosHelper(
				idImovel, ano);

		Collection colecaoDadosContasPagas = null;
		Collection colecaoDadosContasCanceladas = null;
		Collection colecaoDadosContasParceladas = null;
		Collection colecaoDadosContasEmCobrancaJudicial = null;

		Collection<DeclaracaoQuitacaoAnualDebitosItemHelper> colecaoItemHelper = new ArrayList<DeclaracaoQuitacaoAnualDebitosItemHelper>();

		DeclaracaoQuitacaoAnualDebitosItemHelper itemHelper = null;

		BigDecimal valorTotalDasContas = new BigDecimal("0.0");

		try {

			// Verifica Contas Pagas
			colecaoDadosContasPagas = this.repositorioFaturamento
					.pesquisarContasPagasGeracaoDeclaracaoQuitacao(idImovel,
							ano + "%", dataVerificacaoPagamentos);

			for (Iterator iteradadosContasPagas = colecaoDadosContasPagas
					.iterator(); iteradadosContasPagas.hasNext();) {
				Object[] dadosContasPagas = (Object[]) iteradadosContasPagas
						.next();

				itemHelper = new DeclaracaoQuitacaoAnualDebitosItemHelper(
						(Integer) dadosContasPagas[1],// Id da Conta
						(Integer) dadosContasPagas[8],// AnoMês Referencia
						(BigDecimal) dadosContasPagas[2],// Valor Agua
						(BigDecimal) dadosContasPagas[3],// Valor Esgoto
						(BigDecimal) dadosContasPagas[4],// Valor Debitos
						(BigDecimal) dadosContasPagas[5],// Valor Creditos
						(BigDecimal) dadosContasPagas[6],// Valor Impostos
						(Date) dadosContasPagas[7],// Data
						(String) dadosContasPagas[9]);// Situacao Debito

				itemHelper.setValorTotalConta(itemHelper.getValorTotal());
				valorTotalDasContas = valorTotalDasContas.add(itemHelper
						.getValorTotal());

				colecaoItemHelper.add(itemHelper);

			}

			// Verifica Contas Canceladas
			colecaoDadosContasCanceladas = this.repositorioFaturamento
					.pesquisarContasCanceladasGeracaoDeclaracaoQuitacao(
							idImovel, ano + "%", dataVerificacaoPagamentos);

			labelCanceladas: for (Iterator iterator = colecaoDadosContasCanceladas
					.iterator(); iterator.hasNext();) {
				Object[] dadosContasCanceladas = (Object[]) iterator.next();

				itemHelper = new DeclaracaoQuitacaoAnualDebitosItemHelper(
						(Integer) dadosContasCanceladas[1],// Id da Conta
						(Integer) dadosContasCanceladas[8],// AnoMês Referencia
						(BigDecimal) dadosContasCanceladas[2],// Valor Agua
						(BigDecimal) dadosContasCanceladas[3],// Valor Esgoto
						(BigDecimal) dadosContasCanceladas[4],// Valor Debitos
						(BigDecimal) dadosContasCanceladas[5],// Valor
																// Creditos
						(BigDecimal) dadosContasCanceladas[6],// Valor
																// Impostos
						(Date) dadosContasCanceladas[7],// Data
						(String) dadosContasCanceladas[9]);// Situacao Debito

				itemHelper.setValorTotalConta(itemHelper.getValorTotal());
				valorTotalDasContas = valorTotalDasContas.add(itemHelper
						.getValorTotal());

				for (DeclaracaoQuitacaoAnualDebitosItemHelper helper : colecaoItemHelper) {

					if (helper.equalsAnoMesConta(itemHelper)) {
						if (helper.getIdConta().compareTo(
								itemHelper.getIdConta()) < 0) {
							colecaoItemHelper.remove(helper);
							colecaoItemHelper.add(itemHelper);
							continue labelCanceladas;
						} else {
							continue labelCanceladas;
						}
					}
				}
				colecaoItemHelper.add(itemHelper);
			}

			// Verifica Contas Parceladas caso indicadorContaParcelada seja
			// igual a sim para verificar contas parceladas.
			if (indicadorContaParcelada.compareTo(ConstantesSistema.SIM) == 0) {
				colecaoDadosContasParceladas = this.repositorioFaturamento
						.pesquisarContasParceladasGeracaoDeclaracaoQuitacao(
								idImovel, ano + "%", dataVerificacaoPagamentos);

				labelParceladas: for (Iterator iterator = colecaoDadosContasParceladas
						.iterator(); iterator.hasNext();) {
					Object[] dadosContasParceladas = (Object[]) iterator.next();

					itemHelper = new DeclaracaoQuitacaoAnualDebitosItemHelper(
							(Integer) dadosContasParceladas[1],// Id da Conta
							(Integer) dadosContasParceladas[8],// AnoMês
																// Referencia
							(BigDecimal) dadosContasParceladas[2],// Valor
																	// Agua
							(BigDecimal) dadosContasParceladas[3],// Valor
																	// Esgoto
							(BigDecimal) dadosContasParceladas[4],// Valor
																	// Debitos
							(BigDecimal) dadosContasParceladas[5],// Valor
																	// Creditos
							(BigDecimal) dadosContasParceladas[6],// Valor
																	// Impostos
							(Date) dadosContasParceladas[7],// Data
							(String) dadosContasParceladas[9]);// Situacao
																// Debito

					itemHelper.setValorTotalConta(itemHelper.getValorTotal());
					valorTotalDasContas = valorTotalDasContas.add(itemHelper
							.getValorTotal());

					for (DeclaracaoQuitacaoAnualDebitosItemHelper helper : colecaoItemHelper) {

						if (helper.equalsAnoMesConta(itemHelper)) {
							if (helper.getIdConta().compareTo(
									itemHelper.getIdConta()) < 0) {
								colecaoItemHelper.remove(helper);
								colecaoItemHelper.add(itemHelper);

								continue labelParceladas;
							} else {
								continue labelParceladas;
							}
						}
					}
					colecaoItemHelper.add(itemHelper);
				}
			}

			// Verifica Contas Em Cobrança Judicial caso indicadorContaParcelada
			// seja
			// igual a sim para verificar Contas Em Cobrança Judicia.
			if (indicadorCobrancaJudical.compareTo(ConstantesSistema.SIM) == 0) {
				colecaoDadosContasEmCobrancaJudicial = this.repositorioFaturamento
						.pesquisarContasEmCobrancaJudicialGeracaoDeclaracaoQuitacao(
								idImovel, ano + "%", dataVerificacaoPagamentos);

				labelJudicial: for (Iterator iterator = colecaoDadosContasEmCobrancaJudicial
						.iterator(); iterator.hasNext();) {
					Object[] dadosContasEmCobrancaJudicial = (Object[]) iterator
							.next();

					itemHelper = new DeclaracaoQuitacaoAnualDebitosItemHelper(
							(Integer) dadosContasEmCobrancaJudicial[1],// Id da
																		// Conta
							(Integer) dadosContasEmCobrancaJudicial[7],// AnoMês
																		// Referencia
							(BigDecimal) dadosContasEmCobrancaJudicial[2],// Valor
																			// Agua
							(BigDecimal) dadosContasEmCobrancaJudicial[3],// Valor
																			// Esgoto
							(BigDecimal) dadosContasEmCobrancaJudicial[4],// Valor
																			// Debitos
							(BigDecimal) dadosContasEmCobrancaJudicial[5],// Valor
																			// Creditos
							(BigDecimal) dadosContasEmCobrancaJudicial[6],// Valor
																			// Impostos
							(Date) dadosContasEmCobrancaJudicial[9],// Data
																	// Revisão
							(String) dadosContasEmCobrancaJudicial[8]);// Situacao
																		// Debito

					itemHelper.setValorTotalConta(itemHelper.getValorTotal());
					valorTotalDasContas = valorTotalDasContas.add(itemHelper
							.getValorTotal());

					for (DeclaracaoQuitacaoAnualDebitosItemHelper helper : colecaoItemHelper) {

						if (helper.equalsAnoMesConta(itemHelper)) {
							if (helper.getIdConta().compareTo(
									itemHelper.getIdConta()) < 0) {
								colecaoItemHelper.remove(helper);
								colecaoItemHelper.add(itemHelper);
								continue labelJudicial;
							} else {
								continue labelJudicial;
							}
						}
					}
					colecaoItemHelper.add(itemHelper);
				}
			}

			List colecaoItemHelperParaOrdenar = (List) colecaoItemHelper;

			// ORDENA COLEÇÃO POR ANO MES DE REFERENCIA DA CONTA
			Collections.sort(colecaoItemHelperParaOrdenar, new Comparator() {
				public int compare(Object left, Object right) {
					DeclaracaoQuitacaoAnualDebitosItemHelper leftKey = (DeclaracaoQuitacaoAnualDebitosItemHelper) left;
					DeclaracaoQuitacaoAnualDebitosItemHelper rightKey = (DeclaracaoQuitacaoAnualDebitosItemHelper) right;
					return leftKey.getAnoMesReferencia().compareTo(
							rightKey.getAnoMesReferencia());
				}
			});

			retorno.setValorTotalContas(valorTotalDasContas);
			retorno.setExtratoQuitacaoItens(colecaoItemHelperParaOrdenar);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * [UC1008] Gerar TXT declaração de quitação anual de débitos
	 * 
	 * Este caso de uso permite a geração do TXT da declaração de quitação de
	 * débitos.
	 * 
	 * @author Hugo Amorim
	 * @date 23/03/2010
	 */
	public Collection<Integer> pesquisarAnosParaGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos()
			throws ControladorException {
		try {

			return repositorioFaturamento
					.pesquisarAnosParaGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos();

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1008] Gerar TXT declaração de quitação anual de débitos
	 * 
	 * Este caso de uso permite a geração do TXT da declaração de quitação de
	 * débitos.
	 * 
	 * @author Hugo Amorim
	 * @date 23/03/2010
	 */
	public void gerarArquivoTextoDeclaracaoQuitacaoAnualDebitos(
			Integer idFuncionalidadeIniciada, Integer idGrupoFaturamento,
			Empresa empresa) throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		// Registrar o início do processamento da Unidade de
		// Processamento do Batch
		// -------------------------

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.EMPRESA, empresa.getId());

		try {

			Collection<Integer> anosParaGeracaoArquivoTexto = this
					.pesquisarAnosParaGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos();

			for (Integer ano : anosParaGeracaoArquivoTexto) {

				// Variáveis para controle das partes dos arquivos de 3000 em
				// 3000 registros
				// ========================================================================
				int parte = 1;
				boolean flagTerminouParte = false;
				int contadorDosTresMil = 0;
				// ========================================================================

				// Variáveis para a paginação da pesquisa
				// ========================================================================
				boolean flagTerminou = false;
				final int quantidadeMaxima = 500;
				// ========================================================================

				Integer sequencial = 0;

				StringBuilder linha = null;

				while (!flagTerminou) {

					// Criação do Arquivo
					// ========================================================================
					Date dataAtual = new Date();
					String nomeZip = null;
					nomeZip = "DECLARACAO_DE_QUITACAO_ANUAL_DEBITOS_G"
							+ idGrupoFaturamento + "_" + ano + "_Emp"
							+ empresa.getId() + "_PARTE_" + parte + "_"
							+ Util.formatarData(dataAtual) + "_"
							+ Util.formatarHoraSemDataSemDoisPontos(dataAtual);
					nomeZip = nomeZip.replace("/", "_");
					File compactado = new File(nomeZip + ".zip");
					File leitura = new File(nomeZip + ".txt");
					ZipOutputStream zos = new ZipOutputStream(
							new FileOutputStream(compactado));
					BufferedWriter out = new BufferedWriter(
							new OutputStreamWriter(new FileOutputStream(leitura
									.getAbsolutePath())));
					// ========================================================================

					flagTerminouParte = false;
					parte++;

					while (!flagTerminouParte) {

						// [SB0001 Verificação para geração do TXT];
						Collection colecaoExtratos = this.repositorioFaturamento
								.pesquisarExtratoQuitacaoParaGeracaoArquivoTexto(
										ano, empresa.getId(), quantidadeMaxima,
										idGrupoFaturamento);

						if (colecaoExtratos != null
								&& !colecaoExtratos.isEmpty()) {

							Iterator<Object[]> itera = colecaoExtratos
									.iterator();

							while (itera.hasNext()) {

								Object[] dados = itera.next();

								DeclaracaoQuitacaoAnualDebitosHelper helper = new DeclaracaoQuitacaoAnualDebitosHelper();

								ExtratoQuitacao extratoQuitacao = new ExtratoQuitacao();
								extratoQuitacao.setId(new Integer(dados[0]
										.toString()));
								extratoQuitacao.setImovel(new Imovel(
										(Integer) dados[1]));
								extratoQuitacao.setAnoReferencia(new Integer(
										dados[2].toString()));
								extratoQuitacao
										.setIndicadorImpressao(new Integer(
												dados[5].toString()));
								extratoQuitacao
										.setUltimaAlteracao((Date) dados[6]);
								extratoQuitacao
										.setValorTotalDasContas(new BigDecimal(
												dados[3].toString()));
								extratoQuitacao
										.setAnoMesMensagemConta(new Integer(
												dados[7].toString()));

								Integer idImovel = new Integer(dados[1]
										.toString());
								Integer anoReferencia = new Integer(dados[2]
										.toString());
								String matriculaFormatada = Util
										.retornaMatriculaImovelFormatada(idImovel);
								String inscricaoImovel = this
										.getControladorImovel()
										.pesquisarInscricaoImovel(idImovel);
								String nomeCliente = this
										.getControladorImovel()
										.consultarClienteUsuarioImovel(idImovel);
								String endereco = this.getControladorEndereco()
										.pesquisarEndereco(idImovel);
								FaturamentoGrupo faturamentoGrupo = this
										.getControladorImovel()
										.pesquisarGrupoImovel(idImovel);
								String[] enderecoDividido = this
										.getControladorEndereco()
										.pesquisarEnderecoFormatadoDividido(
												idImovel);
								Object[] rotaESequencialRotaDoImovel = this
										.getControladorMicromedicao()
										.obterRotaESequencialRotaDoImovelSeparados(
												idImovel);

								Short codigoRota = (Short) rotaESequencialRotaDoImovel[0];
								Integer sequencialRota = (Integer) rotaESequencialRotaDoImovel[1];

								helper.setAnoMesArrecadacao(anoReferencia);
								helper
										.setMatriculaFormatada(matriculaFormatada);
								helper.setInscricaoImovel(inscricaoImovel);
								helper.setNomeClienteUsuario(nomeCliente);
								helper.setEndereco(endereco);
								helper.setFirma(dados[4].toString());
								helper.setIdGrupo(faturamentoGrupo.getId()
										.toString());
								helper
										.setEnderecoDestinatario(enderecoDividido[0]);
								helper.setBairro(enderecoDividido[3]);
								helper.setMunicipio(enderecoDividido[1]);
								helper.setUf(enderecoDividido[2]);
								helper.setCep(enderecoDividido[4]);
								helper
										.setExtratoQuitacaoParaAtualizacao(extratoQuitacao);
								helper.setCodigoRota(codigoRota.toString());
								helper.setSeguencialRota(sequencialRota
										.toString());

								Collection<ExtratoQuitacaoItem> colecaoExtratosItens = this.repositorioFaturamento
										.pesquisarExtratoQuitacaoItensParaGeracaoArquivoTexto(extratoQuitacao
												.getId());

								helper.setFaturas(colecaoExtratosItens);

								sequencial++;

								linha = this.gerarlinhaArquivoExtratoQuitacao(
										helper, sequencial);

								out.write(linha.toString());
								out.flush();

								// O sistema atualiza o campo
								// EXTRATO_QUITACAO.EXQT_ICIMPRESSAO,
								// para os registros em que foram gerados o TXT,
								// para o valor 1;
								ExtratoQuitacao extratoQuitacaoParaAtualizacao = helper
										.getExtratoQuitacaoParaAtualizacao();

								extratoQuitacaoParaAtualizacao
										.setIndicadorImpressao(ConstantesSistema.SIM
												.intValue());

								getControladorBatch().atualizarObjetoParaBatch(
										extratoQuitacaoParaAtualizacao);

								linha = null;

							}

						}

						contadorDosTresMil++;

						/**
						 * Caso a coleção de dados retornados for menor que a
						 * quantidade de registros seta a flag indicando que a
						 * paginação terminou.
						 */
						if (colecaoExtratos == null
								|| colecaoExtratos.size() < quantidadeMaxima) {

							flagTerminou = true;
							flagTerminouParte = true;
						}

						if (colecaoExtratos != null) {
							colecaoExtratos.clear();
							colecaoExtratos = null;
						}

						if (contadorDosTresMil == 4 || flagTerminou) {

							try {
								out.close();
								ZipUtil.adicionarArquivo(zos, leitura);

								// close the stream
								zos.close();
								leitura.delete();
							} catch (IOException e) {
								getControladorBatch()
										.encerrarUnidadeProcessamentoBatch(e,
												idUnidadeIniciada, true);
								throw new EJBException(e);
							}
							contadorDosTresMil = 0;
							flagTerminouParte = true;
						}

					}// Terminou Parte

				}// Terminou
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {

			e.printStackTrace();

			// Este catch serve para interceptar
			// qualquer exceção que o processo batch
			// venha a lançar e garantir que a unidade
			// de processamento do batch será atualizada
			// com o erro ocorrido
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
		}

	}

	private StringBuilder gerarlinhaArquivoExtratoQuitacao(
			DeclaracaoQuitacaoAnualDebitosHelper helper, Integer sequencial) {

		StringBuilder linha = new StringBuilder();

		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getAnoMesArrecadacao().toString(), 4));
		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getNomeClienteUsuario(), 50));
		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getEndereco(), 120));
		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getMatriculaFormatada(), 9));
		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						sequencial.toString(), 50));
		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getInscricaoImovel(), 20));
		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getFirma(), 10));
		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getIdGrupo().toString(), 2));
		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getNomeClienteUsuario(), 50));
		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getEnderecoDestinatario(), 70));
		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getBairro(), 30));
		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getMunicipio(), 30));
		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getUf(), 2));

		if (helper.getCep().length() == 8) {
			linha
					.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									Util.formatarCEP(helper.getCep()), 10));
		} else {
			linha
					.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									helper.getCep(), 10));
		}

		int count = 0;
		
			
		for (ExtratoQuitacaoItem item : helper.getFaturas()) {

			linha
					.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									Util.formatarAnoMesParaMesAno(item
											.getAnoMesReferenciaConta()), 7));
			linha
					.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									item.getDescricaoSituacao(), 30));
			linha
					.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									Util.formatarData(item.getDataSituacao()),
									10));
			linha
					.append(Util
							.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
									Util
											.formatarMoedaReal(item
													.getValorConta()), 14));
			
		}
			
		if(helper.getFaturas().size() < 12){
			while(count <12-helper.getFaturas().size()){
			
			// caso o número  de items seja menor que 12, completa com as strings faltantes
			linha
			.append(Util
					.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("", 61));
			count++;
			}
		}

		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						Util.formatarMoedaReal(helper
								.getExtratoQuitacaoParaAtualizacao()
								.getValorTotalDasContas()), 14));

		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getAnoMesArrecadacao().toString(), 4));
		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getCodigoRota().toString(), 5));
		linha.append(Util
				.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(
						helper.getSeguencialRota().toString(), 5));

		linha.append(System.getProperty("line.separator"));

		return linha;

	}

	/**
	 * [UC1010] Emitir 2ª via de declaração anual de quitação de débitos
	 * 
	 * 
	 * @author Hugo Amorim
	 * @throws ControladorException
	 * @date 23/03/2010
	 */
	public Collection<ExtratoQuitacaoItem> pesquisarExtratoQuitacaoItensParaGeracaoArquivoTexto(
			Integer idExtratoQuitacao) throws ControladorException {
		try {

			return repositorioFaturamento
					.pesquisarExtratoQuitacaoItensParaGeracaoArquivoTexto(idExtratoQuitacao);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * @author Raphael Rossiter
	 * @date 27/04/2010
	 * 
	 * @param idDebitoACobrar
	 * @throws ControladorException
	 */
	public void atualizarSituacaoAtualDebitoACobrar(Integer idDebitoACobrar)
			throws ControladorException {

		try {

			repositorioFaturamento
					.atualizarSituacaoAtualDebitoACobrar(idDebitoACobrar);

		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [SB0002] - Replicar os débitos existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada débito tipo, e retorna uma coleção com
	 * limite de 10 registros.
	 * 
	 * @author Josenildo Neves - Hugo Leonardo
	 * @date 22/02/2010 - 14/04/2010
	 */
	public Collection<DebitoTipoVigencia> pesquisarDebitoTipoVigenciaUltimaVigencia(
			Integer numeroPagina) throws ControladorException {

		Collection<DebitoTipoVigencia> retorno = new ArrayList<DebitoTipoVigencia>();

		try {
			Collection<DebitoTipoVigencia> colecao = this.repositorioFaturamento
					.pesquisarDebitoTipoVigenciaUltimaVigencia(numeroPagina);

			if (colecao != null && !colecao.isEmpty()) {

				Iterator iterator = colecao.iterator();
				while (iterator.hasNext()) {
					Object[] object = (Object[]) iterator.next();

					DebitoTipoVigencia debitoTipoVigencia = new DebitoTipoVigencia();
					debitoTipoVigencia.setId((Integer) object[0]);

					FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
					filtroDebitoTipo.adicionarParametro(new ParametroSimples(
							FiltroDebitoTipo.ID, object[1].toString()));
					Collection<DebitoTipo> collDebitoTipo = this
							.getControladorUtil().pesquisar(filtroDebitoTipo,
									DebitoTipo.class.getName());

					DebitoTipo debitoTipo = collDebitoTipo.iterator().next();

					debitoTipoVigencia.setDebitoTipo(debitoTipo);
					debitoTipoVigencia.setValorDebito((BigDecimal) object[2]);
					debitoTipoVigencia.setDataVigenciaInicial((Date) object[4]);
					debitoTipoVigencia.setDataVigenciaFinal((Date) object[5]);

					retorno.add(debitoTipoVigencia);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

			throw new EJBException(e);
		}

		return retorno;

	}

	/**
	 * [SB0002] - Replicar os débitos existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada tipo débito, e retorna o total.
	 * 
	 * @author Josenildo Neves
	 * @date 22/02/2010
	 */
	public Integer pesquisarDebitoTipoVigenciaUltimaVigenciaTotal()
			throws ControladorException {
		try {
			return repositorioFaturamento
					.pesquisarDebitoTipoVigenciaUltimaVigenciaTotal();
		} catch (Exception e) {
			e.printStackTrace();

			throw new EJBException(e);
		}

	}

	/**
	 * [SB0002] - Replicar os débitos existentes para uma nova vigência e valor.
	 * Pesquisa a última vigência de cada tipo débito, e retorna uma coleção.
	 * 
	 * @author Josenildo Neves - Hugo Leonardo
	 * @date 22/02/2010 - 14/04/2010
	 */
	public Collection<DebitoTipoVigencia> pesquisarDebitoTipoVigenciaUltimaVigenciaSelecionados(
			String[] selecionados) throws ControladorException {

		Collection<DebitoTipoVigencia> retorno = new ArrayList<DebitoTipoVigencia>();

		try {
			Collection<DebitoTipoVigencia> colecao = this.repositorioFaturamento
					.pesquisarDebitoTipoVigenciaUltimaVigenciaSelecionados(selecionados);

			if (colecao != null && !colecao.isEmpty()) {

				Iterator iterator = colecao.iterator();
				while (iterator.hasNext()) {
					Object[] object = (Object[]) iterator.next();

					DebitoTipoVigencia debitoTipoVigencia = new DebitoTipoVigencia();
					debitoTipoVigencia.setId((Integer) object[0]);

					FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
					filtroDebitoTipo.adicionarParametro(new ParametroSimples(
							FiltroDebitoTipo.ID, object[1].toString()));
					Collection<DebitoTipo> collDebitoTipo = this
							.getControladorUtil().pesquisar(filtroDebitoTipo,
									DebitoTipo.class.getName());

					DebitoTipo debitoTipo = collDebitoTipo.iterator().next();

					debitoTipoVigencia.setDebitoTipo(debitoTipo);
					debitoTipoVigencia.setValorDebito((BigDecimal) object[2]);
					debitoTipoVigencia.setDataVigenciaInicial((Date) object[4]);
					debitoTipoVigencia.setDataVigenciaFinal((Date) object[5]);

					retorno.add(debitoTipoVigencia);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

			throw new EJBException(e);
		}

		return retorno;
	}

	/**
	 * [UC0982] Inserir tipo de Débito com Vigência.
	 * 
	 * Verificar se existe vigência já cadastrada para o tipo de débito.
	 * 
	 * @author Hugo Leonardo
	 * @param dataVigenciaInicial
	 * @param dataVigenciaFinal
	 * @param idDebitoTipo
	 * @param opcao
	 * @throws ControladorException
	 * @data 30/04/2010
	 * 
	 * @see Caso a opcao = 1 - verifica as situações de inserir e atualizar
	 *      Débito Tipo Vigência
	 * @see Caso a opcao = 2 - verifica a situação de retificar Débito Tipo
	 *      Vigncia
	 */
	public void verificarExistenciaVigenciaDebito(String dataVigenciaInicial,
			String dataVigenciaFinal, Integer idDebitoTipo, Integer opcao)
			throws ControladorException {

		String retorno = "";

		try {
			retorno = repositorioFaturamento.verificarExistenciaVigenciaDebito(
					dataVigenciaInicial, dataVigenciaFinal, idDebitoTipo);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		// Caso a opcao seja iqual a 1 - verifica as situações de inserir e
		// atualizar Debito Tipo Vigencia
		// 
		// Caso a opcao seja iqual a 2 - verifica a situação de retificar Debito
		// Tipo Vigencia
		//
		if (opcao == 1) {
			if (retorno != null && !retorno.equals("")) {
				throw new ControladorException(
						"atencao.data_vigencia_final_ja_cadastrada", null,
						retorno);
			}
		} else if (opcao == 2) {
			if (retorno != null && !retorno.equals("")) {
				throw new ControladorException(
						"atencao.data_vigencia_final_ja_cadastrada_retificar",
						null, retorno);
			}
		}
	}

	/**
	 * [UC1008] Gerar TXT declaração de quitação anual de débitos
	 * 
	 * Este caso de uso permite a geração do TXT da declaração de quitação de
	 * débitos.
	 * 
	 * @author Hugo Amorim
	 * @date 23/03/2010
	 */
	public Collection<Empresa> pesquisarEmpresasParaGeraracaoExtrato(
			Integer idGrupoFaturamento) throws ControladorException {
		try {

			return repositorioFaturamento
					.pesquisarEmpresasParaGeraracaoExtrato(idGrupoFaturamento);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0391] Inserir valor de cobrança de serviço.
	 * 
	 * Verificar se existe valor de cobrança de serviço já cadastrada.
	 * 
	 * @author Hugo Amorim
	 * @throws ControladorException
	 * @data 07/06/2010
	 * 
	 */
	public Boolean validarVigenciaValorCobrancaServico(
			ServicoCobrancaValor servicoCobrancaValor)
			throws ControladorException {
		try {

			return repositorioFaturamento
					.validarVigenciaValorCobrancaServico(servicoCobrancaValor);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0840] Atualizar Conta Pré-faturada
	 * 
	 * [SB0003] Atualizar Crédito realizado e crédito a realizar
	 * 
	 * @author Sávio Luiz
	 * @date 27/10/2009
	 * 
	 * @param imovel
	 * @param faturamentoGrupo
	 * @param helperValoresAguaEsgoto
	 * @param valorTotalDebitos
	 * @param gerarAtividadeGrupoFaturamento
	 * @return GerarCreditoRealizadoHelper
	 * @throws ControladorException
	 */
	private BigDecimal atualizarCreditoARealizarNitrato(Imovel imovel,
			Integer anoMesFaturamento, BigDecimal valorAgua, Conta conta)
			throws ControladorException {

		BigDecimal valorCredito = null;
		// Caso a situação de faturamento seja igual a Nitrato,
		// e o valor de água é maior que zero
		// Atualiza os créditos a realizar e realizados do nitrato
		if (imovel.getFaturamentoSituacaoTipo() != null
				&& !imovel.getFaturamentoSituacaoTipo().equals("")) {
			if (imovel.getFaturamentoSituacaoTipo().getId().equals(
					FaturamentoSituacaoTipo.NITRATO)
					&& valorAgua.compareTo(BigDecimal.ZERO) == 1) {

				FiltroFaturamentoSituacaoHistorico filtroFaturamentoSituacaoHistorico = new FiltroFaturamentoSituacaoHistorico();
				filtroFaturamentoSituacaoHistorico
						.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoSituacaoHistorico.ID_IMOVEL,
								imovel.getId()));
				filtroFaturamentoSituacaoHistorico
						.adicionarParametro(new ParametroNulo(
								FiltroFaturamentoSituacaoHistorico.ANO_MES_FATURAMENTO_RETIRADA));
				Collection<FaturamentoSituacaoHistorico> colFiltroFaturamentoSituacaoHistorico = this
						.getControladorUtil().pesquisar(
								filtroFaturamentoSituacaoHistorico,
								FaturamentoSituacaoHistorico.class.getName());

				FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = (FaturamentoSituacaoHistorico) Util
						.retonarObjetoDeColecao(colFiltroFaturamentoSituacaoHistorico);

				if (faturamentoSituacaoHistorico != null
						&& anoMesFaturamento >= faturamentoSituacaoHistorico
								.getAnoMesFaturamentoSituacaoInicio() && anoMesFaturamento <= faturamentoSituacaoHistorico
						.getAnoMesFaturamentoSituacaoFim()) {

					// calcula o valor do crédito que será 50% do valor total da
					// água
					valorCredito = valorAgua.divide(new BigDecimal(2), 2,
							BigDecimal.ROUND_DOWN);
					
					try {
						// Pesamos o tipo de credito realizado
						// com credito tipo = Credito de Nitrato
						CreditoRealizado creditoRealizado = null;						
						Collection<CreditoRealizado> colCreditos = Fachada.getInstancia().obterCreditosRealizadosConta( conta );
						
						
						for ( CreditoRealizado objeto : colCreditos ) {
							if ( objeto.getCreditoTipo().getId().equals( CreditoTipo.CREDITO_NITRATO ) ){
								creditoRealizado = objeto;
							}
						}						
						
						Object[] dadosCreditoARealizar = null;
						
						if ( creditoRealizado != null ){						
							dadosCreditoARealizar = 
								repositorioFaturamento.pesquisarCreditoARealizar( creditoRealizado.getCreditoARealizarGeral().getId(), anoMesFaturamento );
						}
						
						// pesquisa o crédito a realizar
/*
 * Object[] dadosCreditoARealizar = repositorioFaturamento
 * .pesquisarCreditoARealizar(imovel.getId(), CreditoTipo.CREDITO_NITRATO,
 * DebitoCreditoSituacao.PRE_FATURADA, anoMesFaturamento);
 */
						// Caso exista o crédito a realizar com a situação
						// pre-fdaturada,
						// então atualiza o valor do crédito e a situação do
						// crédito a realizar,
						// do crédito a realizar categoria, do credito realizado
						// e do credito realizado categoria
						if (dadosCreditoARealizar != null
								&& !dadosCreditoARealizar.equals("")) {

							Integer idCreditoARealizar = (Integer) dadosCreditoARealizar[0];

							// atualiza o crédito a realizar com o valor do
							// crédito calculado
							repositorioFaturamento
									.atualizarValorCreditoARealizar(
											idCreditoARealizar, valorCredito,
											DebitoCreditoSituacao.NORMAL);

							// Pesquisa os créditos a realizar categoria
							Collection colecaoCreditoARealizarCategoria = this
									.obterCreditoRealizarCategoria(idCreditoARealizar);

							Iterator colecaoCreditoARealizarCategoriaIterator = colecaoCreditoARealizarCategoria
									.iterator();

							// Crédito a realizar categoria
							CreditoARealizarCategoria creditoARealizarCategoria = null;

							Collection colecaoCategoriasObterValor = new ArrayList();

							// Laço para recuperar as categorias do crédito a
							// realizar
							while (colecaoCreditoARealizarCategoriaIterator
									.hasNext()) {
								creditoARealizarCategoria = (CreditoARealizarCategoria) colecaoCreditoARealizarCategoriaIterator
										.next();
								Categoria categoria = new Categoria();
								categoria.setId(creditoARealizarCategoria
										.getCategoria().getId());
								categoria
										.setQuantidadeEconomiasCategoria(creditoARealizarCategoria
												.getQuantidadeEconomia());
								colecaoCategoriasObterValor.add(categoria);
							}

							// Obter os valores das categorias por categoria do
							// credito a realizar categoria
							Collection colecaoCategoriasCalculadasValor = getControladorImovel()
									.obterValorPorCategoria(
											colecaoCategoriasObterValor,
											valorCredito);

							// atualiza o crédito a realizar por categoria com o
							// valor do crédito calculado por categoria
							repositorioFaturamento
									.atualizarValorCreditoARealizarCategoria(
											idCreditoARealizar,
											colecaoCategoriasObterValor,
											colecaoCategoriasCalculadasValor);

							// pesquisa o crédito realizado
							Integer idCreditoRealizadoNitrato = repositorioFaturamento
									.pesquisarIdCreditoRealizadoNitrato(conta);

							// caso exista crédito realizado,
							// então atualiza os dados de credito realizado e
							// credito realizado categoria
							if (idCreditoRealizadoNitrato != null
									&& !idCreditoRealizadoNitrato.equals("")) {
								repositorioFaturamento
										.atualizarValorCreditoRealizado(
												idCreditoRealizadoNitrato,
												valorCredito);
								repositorioFaturamento
										.atualizarValorCreditoRealizadoCategoria(
												idCreditoRealizadoNitrato,
												colecaoCategoriasObterValor,
												colecaoCategoriasCalculadasValor);
							}

						}

					} catch (ErroRepositorioException ex) {
						throw new ControladorException("erro.sistema", ex);
					}
				}
			}
		}

		return valorCredito;

	}
	
	/**
	 * 
	 * Atualiza o crédito de contrato de demanda, tanto no
	 * credito a realizar quanto no credito realizado
	 * 
	 * @author Bruno Barro
	 * @date 26/10/2011
	 * 
	 * @param imovel - Imovel a ser atualizado
	 * @param anoMesFaturamento - Ano mes do faturamento
	 * @param valor - Valor a ser informado nos creditos
	 * @param conta - Conta a qual está atribuido o credito
	 * @param ctContratoDemanda - Credito Tipo correspondente ao contrato de demanda
	 * @return
	 * @throws ControladorException
	 */
	private BigDecimal atualizarCreditoARealizarContratoDemanda(
			Imovel imovel,
			Integer anoMesFaturamento, 
			BigDecimal valor, 
			Conta conta,
			CreditoTipo ctContratoDemanda)
			throws ControladorException {

		BigDecimal valorCredito = null;
		int consumoMinimoContratoDemanda = 0;
		int percentualMinimoContratoDemanda = 0;
		
		// Pesquisamos as informações do contrato de demanda
    	// Verificamos a existencia de contrato de demanda
    	Collection<Integer> colConsumoMinimoPercentualEsg =  Fachada.getInstancia().pesquisarContratoDemanda( imovel.getId()+"" );
    	
    	if ( colConsumoMinimoPercentualEsg != null && colConsumoMinimoPercentualEsg.size() > 0 ){
    		
    		Object[] valores = (Object[])Util.retonarObjetoDeColecao( colConsumoMinimoPercentualEsg );
    		
    		consumoMinimoContratoDemanda = (Integer) valores[0]; 
    		percentualMinimoContratoDemanda = (Integer) valores[1];
    	}
    	
		// calcula o valor do crédito pelo percentual de desconto		
		valorCredito = (valor.multiply(new BigDecimal( percentualMinimoContratoDemanda / 100d ))).setScale(2, BigDecimal.ROUND_HALF_UP);
/*
    	
		valorCredito = valor.multiply( new BigDecimal( percentualMinimoContratoDemanda / 100 ) );
		
		valorCredito = Util.arredondar( valor ); //Util.calcularPercentualBigDecimal(valor, new BigDecimal( percentualMinimoContratoDemanda ) );
*/					
		try {
			// Pesamos o tipo de credito realizado
			// com credito tipo = ao informado na função
			CreditoRealizado creditoRealizado = null;						
			Collection<CreditoRealizado> colCreditos = Fachada.getInstancia().obterCreditosRealizadosConta( conta );
			
			
			for ( CreditoRealizado objeto : colCreditos ) {
				if ( objeto.getCreditoTipo().getId().equals( ctContratoDemanda.getId() ) ){
					creditoRealizado = objeto;
				}
			}						
			
			Object[] dadosCreditoARealizar = null;
			
			if ( creditoRealizado != null ){						
				dadosCreditoARealizar = 
					repositorioFaturamento.pesquisarCreditoARealizar( creditoRealizado.getCreditoARealizarGeral().getId(), anoMesFaturamento );
			}
						
			// pesquisa o crédito a realizar

			// Caso exista o crédito a realizar com a situação
			// pre-fdaturada,
			// então atualiza o valor do crédito e a situação do
			// crédito a realizar,
			// do crédito a realizar categoria, do credito realizado
			// e do credito realizado categoria
			if (  dadosCreditoARealizar != null && 
				 !dadosCreditoARealizar.equals("") ) {

				Integer idCreditoARealizar = (Integer) dadosCreditoARealizar[0];

				// atualiza o crédito a realizar com o valor do
				// crédito calculado
				repositorioFaturamento
						.atualizarValorCreditoARealizar(
								idCreditoARealizar, valorCredito,
								DebitoCreditoSituacao.NORMAL);

				// Pesquisa os créditos a realizar categoria
				Collection colecaoCreditoARealizarCategoria = this
						.obterCreditoRealizarCategoria(idCreditoARealizar);

				Iterator colecaoCreditoARealizarCategoriaIterator = colecaoCreditoARealizarCategoria
						.iterator();

				// Crédito a realizar categoria
				CreditoARealizarCategoria creditoARealizarCategoria = null;

				Collection colecaoCategoriasObterValor = new ArrayList();

				// Laço para recuperar as categorias do crédito a
				// realizar
				while (colecaoCreditoARealizarCategoriaIterator.hasNext()) {
					creditoARealizarCategoria = 
							(CreditoARealizarCategoria) colecaoCreditoARealizarCategoriaIterator.next();
					
					Categoria categoria = new Categoria();
					categoria.setId(creditoARealizarCategoria.getCategoria().getId());
					categoria.setQuantidadeEconomiasCategoria(
							creditoARealizarCategoria.getQuantidadeEconomia() )
							;
					colecaoCategoriasObterValor.add(categoria);
				}

				// Obter os valores das categorias por categoria do
				// credito a realizar categoria
				Collection colecaoCategoriasCalculadasValor = getControladorImovel()
						.obterValorPorCategoria(
								colecaoCategoriasObterValor,
								valorCredito);

				// atualiza o crédito a realizar por categoria com o
				// valor do crédito calculado por categoria
				repositorioFaturamento
						.atualizarValorCreditoARealizarCategoria(
								idCreditoARealizar,
								colecaoCategoriasObterValor,
								colecaoCategoriasCalculadasValor);

				// pesquisa o crédito realizado
				Integer idCreditoCrontratoDemanda = repositorioFaturamento
						.pesquisarIdCreditoRealizadoContratoDemanda(conta, ctContratoDemanda);

				// caso exista crédito realizado,
				// então atualiza os dados de credito realizado e
				// credito realizado categoria
				if (idCreditoCrontratoDemanda != null
						&& !idCreditoCrontratoDemanda.equals("")) {
					repositorioFaturamento
							.atualizarValorCreditoRealizado(
									idCreditoCrontratoDemanda,
									valorCredito);
					
					repositorioFaturamento
							.atualizarValorCreditoRealizadoCategoria(
									idCreditoCrontratoDemanda,
									colecaoCategoriasObterValor,
									colecaoCategoriasCalculadasValor);
				}
			}

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		return valorCredito;

	}	

	/**
	 * [UC1041] Gerar Taxa Percentual da Tarifa Mínima para Cortado
	 * 
	 * @author Raphael Rossiter
	 * @date 09/07/2010
	 * 
	 * @param colecaoFaturamentoAtividadeCronogramaRota
	 * @param faturamentoGrupo
	 * @param atividade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarTaxaPercentualTarifaMinimaCortado(
			Collection colecaoFaturamentoAtividadeCronogramaRota,
			FaturamentoGrupo faturamentoGrupo, int atividade,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		// PROCESSO BATCH
		// ------------------------------------------------------------------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.ROTA,
						((FaturamentoAtivCronRota) Util
								.retonarObjetoDeColecao(colecaoFaturamentoAtividadeCronogramaRota))
								.getRota().getId());
		// ---------------------------------------------------------------------------------------------------

		try {

			// PARÂMETROS DO SISTEMA
			SistemaParametro sistemaParametro = getControladorUtil()
					.pesquisarParametrosDoSistema();

			// CARREGANDO O TIPO DO DÉBITO PARA TARIFA DE CORTADO
			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
			filtroDebitoTipo
					.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
			filtroDebitoTipo
					.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");

			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroDebitoTipo.ID, DebitoTipo.TARIFA_CORTADO));

			Collection colecaoDebitoTipo = this.getControladorUtil().pesquisar(
					filtroDebitoTipo, DebitoTipo.class.getName());

			DebitoTipo debitoTipo = (DebitoTipo) Util
					.retonarObjetoDeColecao(colecaoDebitoTipo);

			if (colecaoFaturamentoAtividadeCronogramaRota != null
					&& !colecaoFaturamentoAtividadeCronogramaRota.isEmpty()) {

				Iterator iteratorColecaoFaturamentoAtividadeCronogramaRota = colecaoFaturamentoAtividadeCronogramaRota
						.iterator();

				// LAÇO PARA GERAR DÉBITO PARA TODAS AS ROTAS
				while (iteratorColecaoFaturamentoAtividadeCronogramaRota
						.hasNext()) {

					FaturamentoAtivCronRota faturamentoAtivCronRota = (FaturamentoAtivCronRota) iteratorColecaoFaturamentoAtividadeCronogramaRota
							.next();

					// =================================================================================================
					// Variáveis para a paginação da pesquisa de Imovel por
					// Grupo Faturamento
					// ========================================================================
					boolean flagTerminou = false;
					final int quantidadeRegistros = 500;
					int numeroIndice = 0;
					// ========================================================================

					while (!flagTerminou) {

						Collection colecaoImovel = this
								.pesquisarImovelCortadoSemTarifaSocial(
										faturamentoAtivCronRota.getRota(),
										numeroIndice, quantidadeRegistros);

						/*
						 * Caso exista ids de imóveis para a rota atual
						 * determina a geração do crédito para cada imóvel
						 * retornado.
						 */
						if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

							Iterator iteratorColecaoImoveis = colecaoImovel
									.iterator();

							// LAÇO PARA GERAR O DÉBITO DE TODOS OS IMOVEIS DA
							// ROTA ATUAL
							Imovel imovel = null;
							while (iteratorColecaoImoveis.hasNext()) {

								imovel = (Imovel) iteratorColecaoImoveis.next();

								// GERAÇÃO DÉBITO
								// --------------------------------------------------------------------------------
								this
										.gerarTaxaPercentualTarifaMinimaCortadoPorImovel(
												imovel, faturamentoGrupo,
												debitoTipo, sistemaParametro);
								// --------------------------------------------------------------------------------

							}// FIM DO LOOP DE IMOVEIS

						}// FIM DO LOOP DE IMOVEIS

						/**
						 * Incrementa o nº do indice da páginação
						 */
						numeroIndice = numeroIndice + quantidadeRegistros;

						/**
						 * Caso a coleção de imoveis retornados for menor que a
						 * quantidade de registros seta a flag indicando que a
						 * paginação terminou.
						 */
						if (colecaoImovel == null
								|| colecaoImovel.size() < quantidadeRegistros) {

							flagTerminou = true;
						}

						if (colecaoImovel != null) {
							colecaoImovel.clear();
							colecaoImovel = null;
						}

					}// FIM DO LOOP DA PAGINAÇÃO
				}
			} else {
				// A LISTA COM AS ROTAS ESTÁ NULA OU VAZIA

				throw new ControladorException(
						"atencao.pesquisa.grupo_rota_vazio");
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {

			/*
			 * Este catch serve para interceptar qualquer exceção que o processo
			 * batch venha a lançar e garantir que a unidade de processamento do
			 * batch será atualizada com o erro ocorrido.
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}

	/**
	 * [UC1041] Gerar Taxa Percentual da Tarifa Mínima para Cortado
	 * 
	 * @author Raphael Rossiter
	 * @date 09/07/2010
	 * 
	 * @param rota
	 * @param numeroIndice
	 * @param quantidadeRegistros
	 * @return Collection
	 * @throws ControladorException
	 */
	protected Collection pesquisarImovelCortadoSemTarifaSocial(Rota rota,
			int numeroIndice, int quantidadeRegistros)
			throws ControladorException {

		Collection colecaoImoveis = null;
		Collection imoveis;

		/*
		 * Caso a rota não esteja com o indicador de rota alternativa ativo; a
		 * pesquisa dos imóveis será feita a partir de sua quadra.
		 */
		if (!rota.getIndicadorRotaAlternativa().equals(ConstantesSistema.SIM)) {

			try {

				imoveis = repositorioFaturamento
						.pesquisarImovelCortadoSemTarifaSocialPorRota(rota
								.getId(), numeroIndice, quantidadeRegistros);

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		/*
		 * Caso contrário; a pesquisa dos imóveis será feita a partir da rota
		 * alternativa que estará associada ao mesmo.
		 */
		else {

			try {

				imoveis = repositorioFaturamento
						.pesquisarImovelCortadoSemTarifaSocialPorRotaAlternativa(
								rota.getId(), numeroIndice, quantidadeRegistros);

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}

		// Carregando os dados dos imóveis selecionados
		if (imoveis != null && !imoveis.isEmpty()) {

			Iterator iteratorImoveis = imoveis.iterator();

			colecaoImoveis = new ArrayList();

			Imovel imovel = null;

			while (iteratorImoveis.hasNext()) {

				Object[] arrayImovel = (Object[]) iteratorImoveis.next();

				imovel = new Imovel();

				// ligacaoAguaSituacao.id
				if (arrayImovel[0] != null) {
					LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
					ligacaoAguaSituacao.setId((Integer) arrayImovel[0]);
					ligacaoAguaSituacao
							.setIndicadorFaturamentoSituacao((Short) arrayImovel[11]);
					ligacaoAguaSituacao
							.setConsumoMinimoFaturamento((Integer) arrayImovel[13]);
					imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
				}

				// ligacaoEsgotoSituacao.id
				if (arrayImovel[1] != null) {
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId((Integer) arrayImovel[1]);
					ligacaoEsgotoSituacao
							.setIndicadorFaturamentoSituacao((Short) arrayImovel[12]);
					ligacaoEsgotoSituacao
							.setVolumeMinimoFaturamento((Integer) arrayImovel[14]);
					imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
				}

				// consumoTarifa.id
				if (arrayImovel[2] != null) {
					ConsumoTarifa consumoTarifa = new ConsumoTarifa();
					consumoTarifa.setId((Integer) arrayImovel[2]);
					imovel.setConsumoTarifa(consumoTarifa);
				}

				// localidade.id
				Localidade localidade = null;
				if (arrayImovel[3] != null) {
					localidade = new Localidade();
					localidade.setId((Integer) arrayImovel[3]);
					imovel.setLocalidade(localidade);

				}

				Quadra quadra = null;

				// quadra.id
				if (arrayImovel[4] != null) {
					quadra = new Quadra();
					quadra.setId((Integer) arrayImovel[4]);
					imovel.setQuadra(quadra);
				}

				// quadra.numeroQuadra
				if (arrayImovel[5] != null) {
					quadra.setNumeroQuadra(((Integer) arrayImovel[5])
							.intValue());
					imovel.setQuadra(quadra);
				}

				// imovel.lote
				if (arrayImovel[6] != null) {
					imovel.setLote(((Short) arrayImovel[6]).shortValue());
				}

				// setorComercial.codigo
				SetorComercial setorComercial = null;
				if (arrayImovel[7] != null) {
					setorComercial = new SetorComercial();
					setorComercial.setCodigo(((Integer) arrayImovel[7])
							.intValue());
					imovel.setSetorComercial(setorComercial);

				}

				// imovel.subLote
				if (arrayImovel[8] != null) {
					imovel.setSubLote(((Short) arrayImovel[8]).shortValue());
				}

				// imovel.setorComercial
				if (arrayImovel[9] != null) {
					if (setorComercial == null) {
						setorComercial = new SetorComercial();
					}
					setorComercial.setId((Integer) arrayImovel[9]);
					imovel.setSetorComercial(setorComercial);
				}

				// id do imovel
				if (arrayImovel[10] != null) {
					imovel.setId((Integer) arrayImovel[10]);
				}

				// adiciona o imovel
				colecaoImoveis.add(imovel);
			}
		}

		return colecaoImoveis;
	}

	/**
	 * [UC1041] Gerar Taxa Percentual da Tarifa Mínima para Cortado
	 * 
	 * @author Raphael Rossiter
	 * @date 12/07/2010
	 * 
	 * @param imovel
	 * @param faturamentoGrupo
	 * @param debitoTipo
	 * @param sistemaParametro
	 * @throws ControladorException
	 */
	public void gerarTaxaPercentualTarifaMinimaCortadoPorImovel(Imovel imovel,
			FaturamentoGrupo faturamentoGrupo, DebitoTipo debitoTipo,
			SistemaParametro sistemaParametro) throws ControladorException {

		/*
		 * 2. Para cada imóvel selecionado, o sistema verifica se o imóvel está
		 * cortado há mais de trinta dias:
		 * 
		 * 2.1. Caso a diferença entre a data prevista de leitura no cronograma
		 * (FTAC_DTPREVISTA da tabela FATURAMENTO_ATIVIDADE_CRONOGRAMA com
		 * FTAT_ID com o valor correspondente a efetuar leitura, FTCM_ID=FTCM_ID
		 * da tabela FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL com
		 * FTCM_AMREFERENCIA=(Ano e mês de referência) e FTGR_ID=FTGR_ID da
		 * tabela FATURAMENTO_GRUPO com FTGR_ID=FTGR_ID da tabela ROTA) e a data
		 * do corte (LAGU_DTCORTE da tabela LIGACAO_AGUA com LAGU_ID=IMOV_ID da
		 * tabela IMOVEL) seja menor ou igual a 30 dias o sistema deve passar
		 * para o próximo imóvel selecionado, caso contrário a será gerado o
		 * débito a cobrar, passar para o próximo passo;
		 */
		Date dataPrevistaLeituraCronograma = null;

		try {

			dataPrevistaLeituraCronograma = repositorioFaturamento
					.pesquisarDataPrevistaFaturamentoAtividadeCronograma(
							faturamentoGrupo.getId(),
							FaturamentoAtividade.EFETUAR_LEITURA,
							faturamentoGrupo.getAnoMesReferencia());

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		/*
		 * 0 - Tipo do corte 1 - Data do corte administrativo 2 - Data da
		 * religação 3 - Data do corte 4 - Data da supressão
		 */
		Object[] dadosLigacaoAgua = this.getControladorAtendimentoPublico()
				.pesquisarDadosLigacaoAgua(imovel.getId());
		Date dataCorte = (Date) dadosLigacaoAgua[3];

		if (Util.obterQuantidadeDiasEntreDuasDatas(dataCorte,
				dataPrevistaLeituraCronograma) > 30) {

			/*
			 * 3. O sistema calcula os valores de água e/ou esgoto <<Inclui>>
			 * [UC0120 – Calcular Valores de Água e/ou Esgoto], passando os
			 * seguintes parâmetros:
			 */

			// Inicializando o objeto que armazenará as informações que serão
			// utilizadas no cálculo da conta
			DeterminarValoresFaturamentoAguaEsgotoHelper helperValoresAguaEsgoto = new DeterminarValoresFaturamentoAguaEsgotoHelper();

			/*
			 * 3.1. Ano e mês de referência.
			 * 
			 * 3.2. Situação da ligação de água (LAST_ID da tabela imóvel).
			 * 
			 * 3.3. Situação da ligação de esgoto (LEST_ID da tabela imóvel).
			 * 
			 * 3.4. Indicador de faturamento de água (LAST_ICFATURAMENTO da
			 * tabela LIGACAO_AGUA_SITUACAO com LAST_ID=LAST_ID da tabela
			 * IMOVEL).
			 * 
			 * 3.5. Indicador de faturamento de esgoto (LEST_ICFATURAMENTO da
			 * tabela LIGACAO_ESGOTO_SITUACAO com LEST_ID=LEST_ID da tabela
			 * IMOVEL).
			 * 
			 * 3.6. Caso o indicador de tarifa categoria seja igual a 2
			 * (PARM_ICTARIFACATEGORIA = 2 da tabela de SISTEMA_PARAMETROS) o
			 * sistema passa as subcategorias e as respectivas quantidades de
			 * economias do imóvel (SCAT_ID e IMSB_QTECONOMIA da tabela
			 * IMOVEL_SUBCATEGORIA com IMOV_ID da tabela IMOVEL);
			 * 
			 * 3.7. Caso contrário, categoria(s) do imóvel e sua(s)
			 * respectiva(s) quantidade(s) de economia retornada pelo [UC0108 -
			 * Obter Quantidade de Economias por Categoria];
			 * 
			 * 3.8. Consumo faturado de água do mês (passar o valor zero).
			 * 
			 * 3.9. Consumo faturado de esgoto do mês (passar o valor zero).
			 * 
			 * 3.10. Consumo mínimo da ligação <<Inclui>> [UC0105 – Obter
			 * Consumo Mínimo da Ligação];
			 * 
			 * 3.11. Data de Leitura Anterior (caso DATE (FTAC_TMREALIZACAO) da
			 * tabela FATURAMENTO_ATIVIDADE_CRONOGRAMA com FTAT_ID com o valor
			 * correspondente a efetuar leitura, FTCM_ID=FTCM_ID da tabela
			 * FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL com FTCM_AMREFERENCIA=(Ano e
			 * mês de referência – 1 MÊS) e FTGR_ID=FTGR_ID da tabela
			 * FATURAMENTO_GRUPO com FTGR_ID=FTGR_ID da tabela ROTA seja
			 * diferente de nulo, caso contrário (FTAC_DTPREVISTA) da tabela
			 * FATURAMENTO_ATIVIDADE_CRONOGRAMA com FTAT_ID com o valor
			 * correspondente a efetuar leitura, FTCM_ID=FTCM_ID da tabela
			 * FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL com FTCM_AMREFERENCIA=(Ano e
			 * mês de referência – 1 MÊS) e FTGR_ID=FTGR_ID da tabela
			 * FATURAMENTO_GRUPO com FTGR_ID=FTGR_ID da tabela ROTA seja
			 * diferente de nulo, caso contrário caso contrário data leitura
			 * atual menos 30 dias.
			 * 
			 * 3.12. Data de Leitura Atual (data prevista de leitura no
			 * cronograma (FTAC_DTPREVISTA da tabela
			 * FATURAMENTO_ATIVIDADE_CRONOGRAMA com FTAT_ID com o valor
			 * correspondente a efetuar leitura, FTCM_ID=FTCM_ID da tabela
			 * FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL com FTCM_AMREFERENCIA=(Ano e
			 * mês de referência) e FTGR_ID=FTGR_ID da tabela FATURAMENTO_GRUPO
			 * com FTGR_ID=FTGR_ID da tabela ROTA)).
			 * 
			 * 3.13. Percentual de esgoto (LESG_PCESGOTO da tabela
			 * LIGACAO_ESGOTO com LESG_ID=IMOV_ID da tabela IMOVEL, caso o
			 * imóvel seja ligado de esgoto, caso o imóvel seja ligado ou
			 * cortado de água (LAST_ID = 3 OR 5) caso o percentual de esgoto
			 * alternativo seja diferente de nulo (LESG_PCALTERNATIVO),
			 * verificar se o consumo por economia (Consumo faturado de esgoto
			 * do mês/quantidade de economia) é menor ou igual ao consumo do
			 * percentual alternativo (LESG_NNCONSUMOPCALTERNATIVO), caso seja
			 * verdade, enviar como Percentual de esgoto o menor valor entre o
			 * LESG_PCESGOTO e o LESG_PCALTERNATIVO, caso contrário enviar o
			 * LESG_PCESGOTO; ou zero caso contrário).
			 * 
			 * 3.14. Tarifa para o imóvel (CSTF_ID da tabela IMOVEL).
			 */

			// 3.4 - INDICADOR FATURAMENTO DE ÁGUA
			helperValoresAguaEsgoto
					.setIndicadorFaturamentoAgua(imovel
							.getLigacaoAguaSituacao()
							.getIndicadorFaturamentoSituacao());

			// 3.5 - INDICADOR FATURAMENTO ESGOTO
			helperValoresAguaEsgoto.setIndicadorFaturamentoEsgoto(imovel
					.getLigacaoEsgotoSituacao()
					.getIndicadorFaturamentoSituacao());

			// 3.6 e 3.7 - CATEGORIAS E SUBCATEGORIAS
			Collection colecaoCategorias = null;
			Collection colecaoCategoriaOUSubcategoria = null;

			// [UC0108] - Obter Quantidade de Economias por Categoria
			colecaoCategorias = this.getControladorImovel()
					.obterQuantidadeEconomiasCategoria(imovel);

			// Verificando se a empresa fatura por CATEGORIA ou SUBCATEGORIA
			if (sistemaParametro.getIndicadorTarifaCategoria().equals(
					SistemaParametro.INDICADOR_TARIFA_CATEGORIA)) {
				colecaoCategoriaOUSubcategoria = colecaoCategorias;
			} else {

				// [UC0108] - Obter Quantidade de Economias por Subcategoria
				colecaoCategoriaOUSubcategoria = this.getControladorImovel()
						.obterQuantidadeEconomiasSubCategoria(imovel.getId());
			}

			// OBS: O restante dos parâmetros serão preparados através do
			// [SB0002] do caso de uso [UC0113] - Faturar Grupo de Faturamento

			/*
			 * [UC0113] - Faturar Grupo de Faturamento [SB0002] - Determinar
			 * Valores para Faturamento de Água e/ou Esgoto
			 */
			helperValoresAguaEsgoto = this
					.determinarValoresFaturamentoAguaEsgoto(imovel,
							faturamentoGrupo.getAnoMesReferencia(),
							colecaoCategoriaOUSubcategoria, faturamentoGrupo,
							null, null);

			// GERANDO O DÉBITO
			this.gerarDebitoACobrarDeTaxaPercentualTarifaMinimaCortado(imovel,
					debitoTipo, faturamentoGrupo.getAnoMesReferencia(),
					helperValoresAguaEsgoto.getValorTotalAgua(),
					sistemaParametro);
		}
	}

	/**
	 * [UC1041] Gerar Taxa Percentual da Tarifa Mínima para Cortado
	 * 
	 * @author Raphael Rossiter
	 * @date 12/07/2010
	 * 
	 * @param imovel
	 * @param debitoTipo
	 * @param anoMesFaturamento
	 * @param valorTotalAgua
	 * @param sistemaParametro
	 * @throws ControladorException
	 */
	public void gerarDebitoACobrarDeTaxaPercentualTarifaMinimaCortado(
			Imovel imovel, DebitoTipo debitoTipo, Integer anoMesFaturamento,
			BigDecimal valorTotalAgua, SistemaParametro sistemaParametro)
			throws ControladorException {

		/*
		 * 5. Para cada imóvel selecionado o sistema inclui: [FS0002 – Verifica
		 * a existência de débito a cobrar de Tarifa de Cortado ativo para o
		 * imóvel]
		 */
		Collection colecaoDebitoACobrarTarifaCortado = null;

		try {

			colecaoDebitoACobrarTarifaCortado = repositorioFaturamento
					.pesquisarDebitoACobrarTarifaCortado(imovel.getId(),
							anoMesFaturamento);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoDebitoACobrarTarifaCortado == null
				|| colecaoDebitoACobrarTarifaCortado.isEmpty()) {

			// 5.1. O débito a cobrar na tabela DEBITO_A_COBRAR_GERAL
			DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();

			debitoACobrarGeral.setIndicadorHistorico(ConstantesSistema.NAO);
			debitoACobrarGeral.setUltimaAlteracao(new Date());

			// INSERINDO NO BANCO
			Integer idDebitoGerado = (Integer) getControladorUtil().inserir(
					debitoACobrarGeral);
			debitoACobrarGeral.setId(idDebitoGerado);

			// 5.2. Inclui o débito a cobrar na tabela DEBITO_A_COBRAR
			DebitoACobrar debitoACobrar = new DebitoACobrar();

			debitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);
			debitoACobrar.setId(idDebitoGerado);

			debitoACobrar.setImovel(imovel);
			debitoACobrar.setDebitoTipo(debitoTipo);
			debitoACobrar.setGeracaoDebito(new Date());
			debitoACobrar.setAnoMesReferenciaDebito(anoMesFaturamento);

			/*
			 * Maior valor entre o ano/mes da data corrente e o ano/mes de
			 * referencia do faturamento (PARM_AMREFERENCIAFATURAMENTO da tabela
			 * SISTEMA_PARAMETROS)
			 */
			int anoMesReferenciaContabil = sistemaParametro
					.getAnoMesFaturamento();
			int anoMesCorrente = Util.getAnoMesComoInt(new Date());

			if (sistemaParametro.getAnoMesFaturamento() < anoMesCorrente) {
				anoMesReferenciaContabil = anoMesCorrente;
			}

			debitoACobrar.setAnoMesReferenciaContabil(anoMesReferenciaContabil);

			// Calcula 30% do valor total de água determinado no passo 4
			BigDecimal valorDebito = (valorTotalAgua.multiply(new BigDecimal(
					"0.30"))).setScale(2, BigDecimal.ROUND_HALF_UP);
			debitoACobrar.setValorDebito(valorDebito);

			debitoACobrar.setNumeroPrestacaoDebito(new Short("1"));
			debitoACobrar.setNumeroPrestacaoCobradas(new Short("0"));

			debitoACobrar.setLocalidade(imovel.getLocalidade());
			debitoACobrar.setQuadra(imovel.getQuadra());
			debitoACobrar.setCodigoSetorComercial(imovel.getSetorComercial()
					.getCodigo());
			debitoACobrar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
			debitoACobrar.setNumeroLote(imovel.getLote());
			debitoACobrar.setNumeroSubLote(imovel.getSubLote());

			debitoACobrar.setPercentualTaxaJurosFinanciamento(BigDecimal.ZERO);

			debitoACobrar.setFinanciamentoTipo(debitoTipo
					.getFinanciamentoTipo());
			debitoACobrar.setLancamentoItemContabil(debitoTipo
					.getLancamentoItemContabil());

			DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
			debitoCreditoSituacao.setId(DebitoCreditoSituacao.NORMAL);
			debitoACobrar.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);

			CobrancaForma cobrancaForma = new CobrancaForma();
			cobrancaForma.setId(CobrancaForma.COBRANCA_EM_CONTA);
			debitoACobrar.setCobrancaForma(cobrancaForma);

			debitoACobrar.setUltimaAlteracao(new Date());
			debitoACobrar.setUsuario(Usuario.USUARIO_BATCH);

			// INSERINDO NA BASE
			getControladorUtil().inserir(debitoACobrar);

			/*
			 * 5.3. <<Inclui>> [UC0108 – Obter Quantidade de Economias por
			 * Categoria]. 5.4. <<Inclui>> [UC0185 – Obter Valor por
			 * Categoria]. 5.5. Inclui, na tabela DEBITO_A_COBRAR_CATEGORIA,
			 * a(s) categoria(s) e sua(s) respectiva(s) quantidade(s) de
			 * economia da lista retornada pelo [UC0108] e os valores retornados
			 * pelo [UC0185] para cada categoria:
			 */
			inserirDebitoACobrarCategoria(debitoACobrar, imovel);
		}
	}

	private void excluirDebitosImoveisCortados(Integer idImovel,
			Integer anoMesFaturamento) throws ControladorException {

		try {

			// Atualizando o valor do débito da conta
			Collection<Integer> colIdDebitoACobrar = this.repositorioFaturamento
					.atualizarValorDebitoDaConta(idImovel, anoMesFaturamento);

			if (colIdDebitoACobrar != null && !colIdDebitoACobrar.isEmpty()) {

				// Apagamos o débito cobrado categoria
				this.repositorioFaturamento
						.deletarDebitosCobradosCategoriaImoveisCortados(
								idImovel, anoMesFaturamento);

				// Apagamos o débito cobrado
				this.repositorioFaturamento
						.deletarDebitosCobradosImoveisCortados(idImovel,
								anoMesFaturamento);

				// Apagamos o debito a cobrar categoria
				this.repositorioFaturamento
						.deletarDebitosACobrarCategoria(colIdDebitoACobrar);

				// Apagamos o debito a cobrar/debito a cobrar geral
				this.repositorioFaturamento
						.deletarDebitosACobrar(colIdDebitoACobrar);

			}

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 21/07/2010
	 */
	public Conta pesquisarUltimaContaDoImovel(Integer idImovel)
			throws ControladorException {
		try {
			return repositorioFaturamento
					.pesquisarUltimaContaDoImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1035] Efetivar Alterar Inscrição de Imóvel
	 * 
	 * @author Hugo Amorim
	 * @throws ControladorException
	 * @data 08/07/201
	 */
	public void alterarInscricoesImoveis(Integer idFuncionalidadeIniciada,
			Integer idLocalidade) throws ControladorException {

		int idUnidadeIniciada = 0;

		/*
		 * Registrar o início do processamento da Unidade de Processamento do
		 * Batch
		 */
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.LOCALIDADE, idLocalidade);

		try {

			// Variáveis para a paginação da pesquisa de Imovel por Grupo
			// Faturamento
			// ========================================================================
			boolean flagTerminou = false;
			final int quantidadeRegistros = 1000;
			int numeroIndice = 0;
			// ========================================================================

			while (!flagTerminou) {

				Collection colecaoDados = this.repositorioFaturamento
						.pesquisarImoveisComInscricaoPedenteParaAtualizacao(
								idLocalidade, numeroIndice, quantidadeRegistros);

				if (colecaoDados != null && !colecaoDados.isEmpty()) {

					Iterator dadosIterator = colecaoDados.iterator();

					while (dadosIterator.hasNext()) {

						boolean existeImovelComMesmaIncricao = false;

						ImovelInscricaoAlterada imovelInscricaoAlterada = (ImovelInscricaoAlterada) dadosIterator
								.next();

						Imovel imovelAtualizar = imovelInscricaoAlterada
								.getImovel();
						Localidade localidade = imovelInscricaoAlterada
								.getLocalidadeAtual();
						SetorComercial setorComercial = imovelInscricaoAlterada
								.getSetorComercialAtual();
						Quadra quadra = imovelInscricaoAlterada
								.getQuadraAtual();
						Short lote = imovelInscricaoAlterada.getLoteAtual();
						Short subLote = imovelInscricaoAlterada
								.getSubLoteAtual();

						QuadraFace quadraFace = null;
						if (imovelInscricaoAlterada.getQuadraFaceAtual() != null) {
							quadraFace = imovelInscricaoAlterada
									.getQuadraFaceAtual();
						}

						// Inicio [FS0002] Verificar duplicidade de inscrição
						FiltroImovel filtroImovel = new FiltroImovel();
						filtroImovel
								.adicionarParametro(new ParametroSimples(
										FiltroImovel.LOCALIDADE_ID, localidade
												.getId()));
						filtroImovel.adicionarParametro(new ParametroSimples(
								FiltroImovel.SETOR_COMERCIAL_ID, setorComercial
										.getId()));
						filtroImovel.adicionarParametro(new ParametroSimples(
								FiltroImovel.QUADRA_ID, quadra.getId()));
						filtroImovel.adicionarParametro(new ParametroSimples(
								FiltroImovel.LOTE, lote));
						filtroImovel.adicionarParametro(new ParametroSimples(
								FiltroImovel.SUBLOTE, subLote));

						if (quadraFace != null) {
							filtroImovel
									.adicionarParametro(new ParametroSimples(
											FiltroImovel.QUADRA_FACE_ID,
											quadraFace.getId()));
						}

						Collection colecaoImoveis = getControladorUtil()
								.pesquisar(filtroImovel, Imovel.class.getName());

						if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {

							Imovel imovelMesmaInscricao = (Imovel) Util
									.retonarObjetoDeColecao(colecaoImoveis);

							if (imovelMesmaInscricao != null) {
								existeImovelComMesmaIncricao = true;
							}
						}
						
						
						//2.2.	Caso alteração de inscrição do imóvel (IMIA_ICIMOVELEXCLUIDO da tabela IMOVEL_INSCR_ALTERADA!= 1):
						if ( imovelInscricaoAlterada.getIndicadorImovelExcluido() == null || 
								(imovelInscricaoAlterada.getIndicadorImovelExcluido() != null && !imovelInscricaoAlterada.getIndicadorImovelExcluido().toString().equals("1"))){
						
							// Caso exista não efetua a alteração na tabela imovel.
							if (existeImovelComMesmaIncricao) {
								imovelInscricaoAlterada
										.setIndicadorErroAlteracao(ConstantesSistema.SIM);
								imovelInscricaoAlterada
										.setIndicadorAtualizado(ConstantesSistema.NAO);
							}
							// Caso contrario
							else {
	
								// Atualiza a tabela IMOVEL
								imovelAtualizar.setLocalidade(localidade);
								imovelAtualizar.setSetorComercial(setorComercial);
								imovelAtualizar.setQuadra(quadra);
								imovelAtualizar.setLote(lote);
								imovelAtualizar.setSubLote(subLote);
								imovelAtualizar.setUltimaAlteracao(new Date());
								if (quadraFace != null) {
									imovelAtualizar.setQuadraFace(quadraFace);
								}
								
								
								if (imovelInscricaoAlterada.getUsuarioAlteracao() != null &&
										!imovelInscricaoAlterada.getUsuarioAlteracao().equals("")){
									
									// ------------ <REGISTRAR
									// TRANSAÇÃO>----------------------------
									
									RegistradorOperacao registradorOperacao = new RegistradorOperacao(
											Operacao.OPERACAO_ALTERAR_INSCRICAO, imovelAtualizar.getId(),
											imovelAtualizar.getId(),
											new UsuarioAcaoUsuarioHelper(imovelInscricaoAlterada.getUsuarioAlteracao(),
													UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		
									registradorOperacao.registrarOperacao(imovelAtualizar);
		
									getControladorTransacao().registrarTransacao(imovelAtualizar);
									
									// ------------ </REGISTRAR
									// TRANSAÇÃO>----------------------------
								}
								
								getControladorUtil().atualizar(imovelAtualizar);
	
								imovelInscricaoAlterada
										.setIndicadorErroAlteracao(ConstantesSistema.NAO);
								imovelInscricaoAlterada
										.setIndicadorAtualizado(ConstantesSistema.SIM);
							}
						} else {
							//2.3.	Caso exclusão do imóvel (IMIA_ICIMOVELEXCLUIDO da tabela IMOVEL_INSCR_ALTERADA= 1):
							if (imovelInscricaoAlterada.getUsuarioAlteracao() != null &&
									!imovelInscricaoAlterada.getUsuarioAlteracao().equals("")){
								
								// ------------ <REGISTRAR TRANSAÇÃO>----------------------------
								
								RegistradorOperacao registradorOperacao = new RegistradorOperacao(
										Operacao.OPERACAO_ALTERAR_INSCRICAO, imovelAtualizar.getId(),
										imovelAtualizar.getId(),
										new UsuarioAcaoUsuarioHelper(imovelInscricaoAlterada.getUsuarioAlteracao(),
												UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
	
								registradorOperacao.registrarOperacao(imovelAtualizar);
	
								getControladorTransacao().registrarTransacao(imovelAtualizar);
								
								// ------------ </REGISTRAR TRANSAÇÃO>----------------------------
							}
							// Atualiza a tabela IMOVEL
							imovelAtualizar.setLocalidade(localidade);
							imovelAtualizar.setSetorComercial(setorComercial);
							imovelAtualizar.setQuadra(quadra);
							imovelAtualizar.setLote(lote);
							imovelAtualizar.setSubLote(subLote);
							imovelAtualizar.setUltimaAlteracao(new Date());
							if (quadraFace != null) {
							imovelAtualizar.setQuadraFace(quadraFace);
							}

							
							//2.3.1.	  Atualiza a tabela IMOVEL 
							imovelAtualizar.setIndicadorExclusao(ConstantesSistema.SIM);
							imovelAtualizar.setUltimaAlteracao(new Date());
							
							getControladorUtil().atualizar(imovelAtualizar);
							
							imovelInscricaoAlterada.setIndicadorErroAlteracao(ConstantesSistema.NAO);
							imovelInscricaoAlterada.setIndicadorAtualizado(ConstantesSistema.SIM);
							
						}

						// Atualiza a tabela IMOVEL_INSCR_ALTERADA

						Date dataAtual = new Date();

						imovelInscricaoAlterada
								.setDataAlteracaoBatch(dataAtual);
						imovelInscricaoAlterada.setUltimaAlteracao(dataAtual);
						getControladorUtil().atualizar(imovelInscricaoAlterada);
					}
				}

				/**
				 * Incrementa o nº do indice da páginação
				 */
				numeroIndice = numeroIndice + quantidadeRegistros;

				/**
				 * Caso a coleção de imoveis retornados for menor que a
				 * quantidade de registros seta a flag indicando que a paginação
				 * terminou.
				 */
				if (colecaoDados == null
						|| colecaoDados.size() < quantidadeRegistros) {

					flagTerminou = true;
				}

				if (colecaoDados != null) {
					colecaoDados.clear();
					colecaoDados = null;
				}

			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}

	/**
	 * 
	 * [UC1042] Verificar Farturamento dos Imóveis Cortados
	 * 
	 * Item 2
	 * 
	 * @autor Bruno Barros
	 * @date 13/07/2010
	 * 
	 * @param idRota -
	 *            Id da rota a ser processada
	 * @param anoMesFaturamento -
	 *            Ano mês do a ser faturado
	 * @throws ControladorException
	 */
	private void atualizarFaturamentoImoveisCortados(
			Collection<Imovel> colImoveis, int anoMesFaturamento)
			throws ControladorException {
		try {
			for (Imovel imovel : colImoveis) {

				LigacaoTipo lt = new LigacaoTipo();
				lt.setId(LigacaoTipo.LIGACAO_AGUA);

				// Pesquisamos o consumo historico do imóvel selecionado
				ConsumoHistorico consumoHistorico = this
						.getControladorMicromedicao().obterConsumoHistorico(
								imovel, lt, anoMesFaturamento);

				if (consumoHistorico != null) {
					
					/*
					 * Será necessário pesquisar a situação da ligação de água ao qual foi gerada a conta, caso a conta não seja encontrada será
					 * utilizada a situação da ligação de água atual do imóvel
					 */
					Integer idLigacaoAguaSituacaoConta = repositorioFaturamento.pesquisarLigacaoAguaSituacaoConta(
					imovel.getId(), anoMesFaturamento);
					
					if (idLigacaoAguaSituacaoConta == null && imovel.getLigacaoAguaSituacao() != null){
						
						idLigacaoAguaSituacaoConta = imovel.getLigacaoAguaSituacao().getId();
					}
					
					// 2.1
					if ((idLigacaoAguaSituacaoConta != null && 
						!idLigacaoAguaSituacaoConta.equals(LigacaoAguaSituacao.CORTADO)) 
						|| 
						(consumoHistorico.getConsumoTipo() != null && 
						consumoHistorico.getConsumoTipo().getId().equals(ConsumoTipo.REAL) && 
						consumoHistorico.getNumeroConsumoFaturadoMes() != null && consumoHistorico.getNumeroConsumoFaturadoMes() > 0)) {

						this.excluirDebitosImoveisCortados(imovel.getId(),
								anoMesFaturamento);
						// 2.2
					} else {
						// Verificamos se o imóvel possui o débito
						Object[] debitoACobrar = this.repositorioFaturamento
								.pesquisarDebitoACobrar(imovel.getId(),
										DebitoTipo.TARIFA_CORTADO,
										anoMesFaturamento);

						if (debitoACobrar != null && debitoACobrar[0] != null) {

							this.repositorioFaturamento
									.atualizarIndicadorFaturamentoConsumoHistorico(
											consumoHistorico.getId(),
											ConstantesSistema.NAO.shortValue());
						}
					}
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1042] Verificar Farturamento dos Imóveis Cortados
	 * 
	 * @author Raphael Rossiter
	 * @date 13/07/2010
	 * 
	 * @param colecaoFaturamentoAtividadeCronogramaRota
	 * @param faturamentoGrupo
	 * @param atividade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void verificarFaturamentoImoveisCortados(
			Collection colecaoFaturamentoAtividadeCronogramaRota,
			FaturamentoGrupo faturamentoGrupo, int atividade,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		// PROCESSO BATCH
		// ------------------------------------------------------------------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.ROTA,
						((FaturamentoAtivCronRota) Util
								.retonarObjetoDeColecao(colecaoFaturamentoAtividadeCronogramaRota))
								.getRota().getId());
		// ---------------------------------------------------------------------------------------------------

		try {

			if (colecaoFaturamentoAtividadeCronogramaRota != null
					&& !colecaoFaturamentoAtividadeCronogramaRota.isEmpty()) {

				Iterator iteratorColecaoFaturamentoAtividadeCronogramaRota = colecaoFaturamentoAtividadeCronogramaRota
						.iterator();

				// LAÇO PARA GERAR DÉBITO PARA TODAS AS ROTAS
				while (iteratorColecaoFaturamentoAtividadeCronogramaRota
						.hasNext()) {

					FaturamentoAtivCronRota faturamentoAtivCronRota = (FaturamentoAtivCronRota) iteratorColecaoFaturamentoAtividadeCronogramaRota
							.next();

					// =================================================================================================
					// Variáveis para a paginação da pesquisa de Imovel por
					// Grupo Faturamento
					// ========================================================================
					boolean flagTerminou = false;
					final int quantidadeRegistros = 500;
					int numeroIndice = 0;
					// ========================================================================

					while (!flagTerminou) {

						Collection<Imovel> colecaoImovel = this
								.pesquisarImovelComDebitoTarifaCortado(
										faturamentoAtivCronRota.getRota(),
										faturamentoGrupo.getAnoMesReferencia(),
										numeroIndice, quantidadeRegistros);

						/*
						 * Caso exista ids de imóveis para a rota atual
						 * determina a geração do crédito para cada imóvel
						 * retornado.
						 */
						if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

							// Verificar Farturamento dos Imóveis Cortados
							// --------------------------------------------------------------------------------
							this.atualizarFaturamentoImoveisCortados(
									colecaoImovel, faturamentoGrupo
											.getAnoMesReferencia());
							// --------------------------------------------------------------------------------
						}

						/**
						 * Incrementa o nº do indice da páginação
						 */
						numeroIndice = numeroIndice + quantidadeRegistros;

						/**
						 * Caso a coleção de imoveis retornados for menor que a
						 * quantidade de registros seta a flag indicando que a
						 * paginação terminou.
						 */
						if (colecaoImovel == null
								|| colecaoImovel.size() < quantidadeRegistros) {

							flagTerminou = true;
						}

						if (colecaoImovel != null) {
							colecaoImovel.clear();
							colecaoImovel = null;
						}

					}// FIM DO LOOP DA PAGINAÇÃO
				}
			} else {
				// A LISTA COM AS ROTAS ESTÁ NULA OU VAZIA

				throw new ControladorException(
						"atencao.pesquisa.grupo_rota_vazio");
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {

			/*
			 * Este catch serve para interceptar qualquer exceção que o processo
			 * batch venha a lançar e garantir que a unidade de processamento do
			 * batch será atualizada com o erro ocorrido.
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}

	/**
	 * [UC1042] Verificar Farturamento dos Imóveis Cortados
	 * 
	 * @author Raphael Rossiter
	 * @date 13/07/2010
	 * 
	 * @param rota
	 * @param anoMesfaturamento
	 * @param numeroIndice
	 * @param quantidadeRegistros
	 * @return Collection<Imovel>
	 * @throws ControladorException
	 */
	protected Collection<Imovel> pesquisarImovelComDebitoTarifaCortado(
			Rota rota, Integer anoMesfaturamento, int numeroIndice,
			int quantidadeRegistros) throws ControladorException {

		Collection<Imovel> colecaoImoveis = null;
		Collection imoveis;

		/*
		 * Caso a rota não esteja com o indicador de rota alternativa ativo; a
		 * pesquisa dos imóveis será feita a partir de sua quadra.
		 */
		if (!rota.getIndicadorRotaAlternativa().equals(ConstantesSistema.SIM)) {

			try {

				imoveis = repositorioFaturamento
						.pesquisarImovelComDebitoTarifaCortadoPorRota(rota
								.getId(), anoMesfaturamento, numeroIndice,
								quantidadeRegistros);

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}
		/*
		 * Caso contrário; a pesquisa dos imóveis será feita a partir da rota
		 * alternativa que estará associada ao mesmo.
		 */
		else {

			try {

				imoveis = repositorioFaturamento
						.pesquisarImovelComDebitoTarifaCortadoPorRotaAlternativa(
								rota.getId(), anoMesfaturamento, numeroIndice,
								quantidadeRegistros);

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}

		// Carregando os dados dos imóveis selecionados
		if (imoveis != null && !imoveis.isEmpty()) {

			Iterator iteratorImoveis = imoveis.iterator();

			colecaoImoveis = new ArrayList();

			Imovel imovel = null;

			while (iteratorImoveis.hasNext()) {

				Object[] arrayImovel = (Object[]) iteratorImoveis.next();

				imovel = new Imovel();

				// id do imovel
				if (arrayImovel[0] != null) {
					imovel.setId((Integer) arrayImovel[0]);
				}

				// ligacaoAguaSituacao.id
				if (arrayImovel[1] != null) {
					LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
					ligacaoAguaSituacao.setId((Integer) arrayImovel[1]);

					imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
				}

				// adiciona o imovel
				colecaoImoveis.add(imovel);
			}
		}

		return colecaoImoveis;
	}

	/**
	 * [UC0820] - Atualizar Faturamento do Movimento Celular
	 * 
	 * Verifica se a quantidade de imóveis que chegaram é a esperada.
	 * 
	 * @author bruno
	 * @date 16/08/2010
	 * 
	 * @param idRota -
	 *            Id da rota ser verificada
	 * @param anoMesFaturamento -
	 *            Ano mes de faturamento a ser pesquisado
	 * 
	 * @return Integer
	 * 
	 */
	public Integer pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa(
			Integer idRota, Integer anoMesFaturamento)
			throws ControladorException {

		Integer retorno = null;

		try {
			retorno = this.repositorioFaturamento
					.pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa(
							idRota, anoMesFaturamento);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * @author Rômulo Aurélio
	 * @throws ControladorException
	 * @data 22/06/2010
	 */
	public Integer retornaAnoMesFaturamentoGrupoDaRota(Integer idRota)
			throws ControladorException {

		Integer retorno = null;

		try {
			return this.repositorioFaturamento
					.retornaAnoMesFaturamentoGrupoDaRota(idRota);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * Pesquisar Conta Historico
	 * 
	 * @author Fernando Fontelles
	 * @date 06/08/2010
	 * 
	 * @param idImovel
	 * @param referenciaConta
	 * @return
	 * @throws ControladorException
	 */
	public ContaHistorico pesquisarContaHistoricoDigitada(String idImovel,
			String referenciaConta) throws ControladorException {

		// Variável que vai armazenar a conta pesquisada
		ContaHistorico contaDigitada = null;
		Object[] dadosConta = null;

		// Formata a referência da conta informada para o formato (AAAAMM) sem a
		// barra
		String anoMesConta = Util
				.formatarMesAnoParaAnoMesSemBarra(referenciaConta);

		// Cria o filtro de conta e seta todos os parâmetros para pesquisar a
		// conta do imóvel
		// Pesquisa imovel
		try {
			dadosConta = repositorioFaturamento
					.pesquisarContaHistoricoDigitada(idImovel, anoMesConta);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		if (dadosConta != null) {

			contaDigitada = new ContaHistorico();

			// Id da Conta
			if (dadosConta[0] != null) {
				contaDigitada.setId((Integer) dadosConta[0]);
			}

			// Referência
			if (dadosConta[1] != null) {
				contaDigitada.setAnoMesReferenciaConta((Integer) dadosConta[1]);
			}

			// Valor da Água
			if (dadosConta[2] != null) {
				contaDigitada.setValorAgua((BigDecimal) dadosConta[2]);
			}

			// Valor de Esgoto
			if (dadosConta[3] != null) {
				contaDigitada.setValorEsgoto((BigDecimal) dadosConta[3]);
			}

			// Débitos
			if (dadosConta[4] != null) {
				contaDigitada.setValorDebitos((BigDecimal) dadosConta[4]);
			}

			// Valor Créditos
			if (dadosConta[5] != null) {
				contaDigitada.setValorCreditos((BigDecimal) dadosConta[5]);
			}

			// Valor Imposto
			if (dadosConta[6] != null) {
				contaDigitada.setValorImposto((BigDecimal) dadosConta[6]);
			}

			// Data Vencimento
			if (dadosConta[7] != null) {
				contaDigitada.setDataVencimentoConta((Date) dadosConta[7]);
			}

			// Débito Crédito Situação
			if (dadosConta[8] != null && dadosConta[9] != null) {
				DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
				debitoCreditoSituacao.setId((Integer) dadosConta[8]);
				debitoCreditoSituacao.setDescricaoAbreviada((String) dadosConta[9]);
				contaDigitada.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
			}

			// Localidade
			if (dadosConta[10] != null) {
				Localidade localidade = new Localidade();
				localidade.setId((Integer) dadosConta[10]);
				contaDigitada.setLocalidade(localidade);
			}

		}

		// Retorna a conta encontrada ou nulo se não existir a conta
		return contaDigitada;
	}

	/**
	 * Verifica a Quantidade de Alteracoes no Vencimento da Conta
	 * 
	 * @author Hugo Leonardo
	 * @date 10/08/2010
	 * 
	 * @param idsConta
	 * 
	 * @return void
	 * @throws ControladorException
	 */
	public void verificarQuantidadeAlteracoesVencimentoConta(Collection idsConta)
			throws ControladorException {

		try {
			Iterator iteratorContas = idsConta.iterator();

			// PARÂMETROS DO SISTEMA
			SistemaParametro sistemaParametro = this.getControladorUtil()
					.pesquisarParametrosDoSistema();

			if (sistemaParametro.getIndicadorLimiteAlteracaoVencimento()
					.equals(ConstantesSistema.SIM)) {

				Integer quantidadeMaximaRetificacoes = sistemaParametro
						.getNumeroLimiteAlteracaoVencimento();

				while (iteratorContas.hasNext()) {

					Integer idConta = (Integer) iteratorContas.next();

					Integer quantidadeRetificacoes = this.repositorioFaturamento
							.obterQuantidadeAlteracoesVencimentoConta(idConta);

					if (quantidadeRetificacoes != null
							&& quantidadeMaximaRetificacoes != null
							&& quantidadeMaximaRetificacoes <= quantidadeRetificacoes) {
						throw new ControladorException(
								"atencao.retificacao_conta_nao_permitida",
								null, quantidadeMaximaRetificacoes.toString());
					}
				}
			}

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC1051] Gerar Relatório de Amostragem das Anormalidades Informadas
	 * 
	 * @author Hugo Leonardo
	 * @date 09/08/2010
	 * 
	 * @throws ControladorException
	 */
	public Collection<GerarRelatorioAnormalidadePorAmostragemHelper> pesquisarDadosRelatorioAnormalidadeConsumoPorAmostragem(
			Integer idGrupoFaturamento, Short codigoRota,
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal,
			Integer referencia, Integer idImovelPerfil,
			Integer numOcorConsecutivas, String indicadorOcorrenciasIguais,
			Integer mediaConsumoInicial, Integer mediaConsumoFinal,
			Collection<Integer> colecaoIdsAnormalidadeConsumo,
			Collection<Integer> colecaoIdsAnormalidadeLeitura,
			Collection<Integer> colecaoIdsAnormalidadeLeituraInformada,
			Integer tipoMedicao, Collection<Integer> colecaoIdsEmpresa,
			Integer numeroQuadraInicial, Integer numeroQuadraFinal,
			Integer idCategoria, Integer amostragem)
			throws ControladorException {

		Collection retorno = new ArrayList();

		// Criação das coleções
		Collection colecaoDadosAnormalidadesConsumoPorAmostragem = null;

		Integer totalRelatorio = 0;
		double porcentagem = 0.0;

		try {
			totalRelatorio = this.repositorioFaturamento
					.pesquisarTotalDadosRelatorioAnormalidadeConsumoPorAmostragem(
							idGrupoFaturamento, codigoRota, idGerenciaRegional,
							idUnidadeNegocio, idLocalidadeInicial,
							idLocalidadeFinal, idSetorComercialInicial,
							idSetorComercialFinal, referencia, idImovelPerfil,
							numOcorConsecutivas, indicadorOcorrenciasIguais,
							mediaConsumoInicial, mediaConsumoFinal,
							colecaoIdsAnormalidadeConsumo,
							colecaoIdsAnormalidadeLeitura,
							colecaoIdsAnormalidadeLeituraInformada,
							tipoMedicao, colecaoIdsEmpresa,
							numeroQuadraInicial, numeroQuadraFinal, idCategoria);

			porcentagem = (totalRelatorio * amostragem) / 100;

			totalRelatorio = new Integer((int) Math.round(porcentagem));

			colecaoDadosAnormalidadesConsumoPorAmostragem = this.repositorioFaturamento
					.pesquisarDadosRelatorioAnormalidadeConsumoPorAmostragem(
							idGrupoFaturamento, codigoRota, idGerenciaRegional,
							idUnidadeNegocio, idLocalidadeInicial,
							idLocalidadeFinal, idSetorComercialInicial,
							idSetorComercialFinal, referencia, idImovelPerfil,
							numOcorConsecutivas, indicadorOcorrenciasIguais,
							mediaConsumoInicial, mediaConsumoFinal,
							colecaoIdsAnormalidadeConsumo,
							colecaoIdsAnormalidadeLeitura,
							colecaoIdsAnormalidadeLeituraInformada,
							tipoMedicao, colecaoIdsEmpresa,
							numeroQuadraInicial, numeroQuadraFinal,
							idCategoria, totalRelatorio);

		} catch (ErroRepositorioException ex) {

			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoDadosAnormalidadesConsumoPorAmostragem != null
				&& !colecaoDadosAnormalidadesConsumoPorAmostragem.isEmpty()) {

			Iterator colecaoDadosAnormalidadesConsumoIterator = colecaoDadosAnormalidadesConsumoPorAmostragem
					.iterator();

			while (colecaoDadosAnormalidadesConsumoIterator.hasNext()) {

				// Obtém os dados do débito cobrado
				Object[] dadosAnormalidadesConsumo = (Object[]) colecaoDadosAnormalidadesConsumoIterator
						.next();

				GerarRelatorioAnormalidadePorAmostragemHelper relatorioHelper = new GerarRelatorioAnormalidadePorAmostragemHelper();

				// Id do Grupo de Faturamento
				if (dadosAnormalidadesConsumo[0] != null) {
					relatorioHelper
							.setIdGrupo((Integer) dadosAnormalidadesConsumo[0]);
				}

				// Nome do Grupo de Faturamento
				if (dadosAnormalidadesConsumo[1] != null) {
					relatorioHelper
							.setNomeGrupo((String) dadosAnormalidadesConsumo[1]);
				}

				// Id da Gerência Regional
				if (dadosAnormalidadesConsumo[2] != null) {
					relatorioHelper
							.setIdGerenciaRegional((Integer) dadosAnormalidadesConsumo[2]);
				}

				// Nome da Gerência Regional
				if (dadosAnormalidadesConsumo[3] != null) {
					relatorioHelper
							.setNomeGerenciaRegional((String) dadosAnormalidadesConsumo[3]);
				}

				// Id da Unidade de Negócio
				if (dadosAnormalidadesConsumo[4] != null) {
					relatorioHelper
							.setIdUnidadeNegocio((Integer) dadosAnormalidadesConsumo[4]);
				}

				// Nome da Unidade de Negócio
				if (dadosAnormalidadesConsumo[5] != null) {
					relatorioHelper
							.setNomeUnidadeNegocio((String) dadosAnormalidadesConsumo[5]);
				}

				// Id do Elo
				if (dadosAnormalidadesConsumo[6] != null) {
					relatorioHelper
							.setIdElo((Integer) dadosAnormalidadesConsumo[6]);
				}

				// Nome do Elo
				if (dadosAnormalidadesConsumo[7] != null) {
					relatorioHelper
							.setNomeElo((String) dadosAnormalidadesConsumo[7]);
				}

				// Id da Localidade
				if (dadosAnormalidadesConsumo[8] != null) {
					relatorioHelper
							.setIdLocalidade((Integer) dadosAnormalidadesConsumo[8]);
				}

				// Nome da Localidade
				if (dadosAnormalidadesConsumo[9] != null) {
					relatorioHelper
							.setNomeLocalidade((String) dadosAnormalidadesConsumo[9]);
				}

				// Id do Imóvel
				if (dadosAnormalidadesConsumo[10] != null) {
					relatorioHelper
							.setIdImovel((Integer) dadosAnormalidadesConsumo[10]);
				}

				// Nome do Usuário
				if (dadosAnormalidadesConsumo[11] != null) {
					relatorioHelper
							.setNomeUsuario((String) dadosAnormalidadesConsumo[11]);
				}

				// Id da Situação de Água
				if (dadosAnormalidadesConsumo[12] != null) {
					relatorioHelper
							.setSituacaoLigacaoAgua((Integer) dadosAnormalidadesConsumo[12]);
				}

				// Id da Situação de Esgoto
				if (dadosAnormalidadesConsumo[13] != null) {
					relatorioHelper
							.setSituacaoLigacaoEsgoto((Integer) dadosAnormalidadesConsumo[13]);
				}

				// Indicador de Débito Automático
				if (dadosAnormalidadesConsumo[14] != null) {
					relatorioHelper
							.setIndicadorDebito((Short) dadosAnormalidadesConsumo[14]);
				}

				// Consumo Médio
				if (dadosAnormalidadesConsumo[15] != null) {
					relatorioHelper
							.setConsumoMedio((Integer) dadosAnormalidadesConsumo[15]);
				}

				// Consumo do Mês
				if (dadosAnormalidadesConsumo[16] != null) {
					relatorioHelper
							.setConsumoMes((Integer) dadosAnormalidadesConsumo[16]);
				}

				// Descrição Abreviada da Anormalidade de Consumo
				if (dadosAnormalidadesConsumo[17] != null) {
					relatorioHelper
							.setDescricaoAbrevConsumoAnormalidade((String) dadosAnormalidadesConsumo[17]);
				}

				// Id da Anormalidade de Leitura
				if (dadosAnormalidadesConsumo[18] != null) {
					relatorioHelper
							.setIdLeituraAnormalidade((Integer) dadosAnormalidadesConsumo[18]);
				}

				// Quantidade de Economias
				if (dadosAnormalidadesConsumo[19] != null) {
					relatorioHelper
							.setQuantidadeEconomias((Short) dadosAnormalidadesConsumo[19]);
				}

				// Tipo de Medição
				if (dadosAnormalidadesConsumo[20] != null) {

					if (dadosAnormalidadesConsumo[20].equals(MedicaoTipo.POCO)) {
						relatorioHelper.setTipoMedicao("PC");
					} else {
						relatorioHelper.setTipoMedicao("LA");
					}

				} else {
					relatorioHelper.setTipoMedicao("");
				}

				// Descrição Abreviada da Capacidade do Hidrômetro da
				// Ligação de Água
				if (dadosAnormalidadesConsumo[21] != null) {
					relatorioHelper
							.setCapacidadeHidrometro((String) dadosAnormalidadesConsumo[21]);
				}

				// Descrição Abreviada do Local de Instalação do Hidrômetro
				// da Ligação de Água
				if (dadosAnormalidadesConsumo[22] != null) {
					relatorioHelper
							.setLocalInstalacaoHidrometro((String) dadosAnormalidadesConsumo[22]);
				}

				// Id do Setor Comercial
				if (dadosAnormalidadesConsumo[23] != null) {
					relatorioHelper
							.setIdSetorComercial((Integer) dadosAnormalidadesConsumo[23]);
				}

				// Código do Setor Comercial
				if (dadosAnormalidadesConsumo[24] != null) {
					relatorioHelper
							.setCodigoSetorComercial((Integer) dadosAnormalidadesConsumo[24]);
				}

				// Número leitura atual informada
				if (dadosAnormalidadesConsumo[25] != null) {
					relatorioHelper
							.setNnLeituraAtualInformada((Integer) dadosAnormalidadesConsumo[25]);
				}

				// Id Empresa
				if (dadosAnormalidadesConsumo[26] != null) {
					relatorioHelper
							.setIdEmpresa((Integer) dadosAnormalidadesConsumo[26]);
				}

				// Nome Empresa
				if (dadosAnormalidadesConsumo[27] != null) {
					relatorioHelper
							.setNomeEmpresa((String) dadosAnormalidadesConsumo[27]);
				}

				// inscrição do Imóvel
				if (dadosAnormalidadesConsumo[28] != null) {
					relatorioHelper
							.setInscricaoImovel((String) dadosAnormalidadesConsumo[28]);
				}
				if (dadosAnormalidadesConsumo[29] != null) {
					relatorioHelper
							.setEnderecoImovel((String) dadosAnormalidadesConsumo[29]);
				}

				retorno.add(relatorioHelper);
			}
		}

		return retorno;
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @param idOrdemServico
	 */
	public boolean verificarExistenciaAutosInfracaoPorOS(Integer idOrdemServico)
			throws ControladorException {
		try {
			boolean existeAutosInfracaoPorOs = false;

			Collection autosInfracao = repositorioFaturamento
					.verificarExistenciaAutosInfracaoPorOS(idOrdemServico);

			if (autosInfracao != null && !autosInfracao.isEmpty()) {
				existeAutosInfracaoPorOs = true;
			}

			return existeAutosInfracaoPorOs;

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @param idOrdemServico
	 * @throws ErroRepositorioException
	 */
	public AutosInfracao pesquisarAutosInfracaoPorOS(Integer idOrdemServico)
			throws ControladorException {
		try {
			return repositorioFaturamento
					.pesquisarAutosInfracaoPorOS(idOrdemServico);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscalização
	 * 
	 * @author Vivianne Sousa
	 * @date 24/08/2010
	 * 
	 * @param idAutoInfracao
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisaAutosInfracaoDebitoACobrar(Integer idAutoInfracao)
			throws ControladorException {
		try {
			return repositorioFaturamento
					.pesquisaAutosInfracaoDebitoACobrar(idAutoInfracao);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * 
	 * Remove os imóveis que ja foram enviados para uma determinada rota em
	 * impressão simultanea
	 * 
	 * @autor Bruno Barros.
	 * @date 24/08/2010
	 * 
	 * @param idRota -
	 *            Id da rota a ser pesquisada
	 * @param BufferedReader -
	 *            Buffer com TODOS os imóveis da rota
	 * 
	 * @return BufferedReader Novo buffer apenas com as matriculas que ainda
	 *         precisam ser processadas
	 */

	public BufferedReader removerImoveisJaProcessadosBufferImpressaoSimultanea(
			Integer idRota, BufferedReader reader, short indicadorAndroid) throws ControladorException {

		StringBuffer arquivo = new StringBuffer();

		BufferedReader retorno = null;

		try {

			// Vamos agrupar as informações

			RemoverImovesJaProcessadorImpressaoSimultaneaHelper helper = new RemoverImovesJaProcessadorImpressaoSimultaneaHelper(
					reader, indicadorAndroid);

			System.out.println(helper);

			Integer anoMesFaturamento = retornaAnoMesFaturamentoGrupoDaRota(idRota);

			for (DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea dadosImovel : helper
					.getColDadosFormatados()) {

				boolean alterouAgua = false;

				// Selecionamos os dados dos medidos de agua e dos não medidos

				if (dadosImovel.isMedidoAgua()
						|| (!dadosImovel.isMedidoAgua() && !dadosImovel
								.isMedidoPoco())) {

					alterouAgua = this.reprocessarImovelImpressaoSimultanea(

					anoMesFaturamento,

					dadosImovel.getIdImovel(),

					MedicaoTipo.LIGACAO_AGUA.shortValue(),

					dadosImovel.getLeituraAgua(),

					dadosImovel.getAnormalidadeAgua(),

					dadosImovel.getIndicadorEmissaoConta());

				}

				boolean alterouPoco = false;

				if (dadosImovel.isMedidoPoco()) {

					alterouPoco = this.reprocessarImovelImpressaoSimultanea(

					anoMesFaturamento,

					dadosImovel.getIdImovel(),

					MedicaoTipo.POCO.shortValue(),

					dadosImovel.getLeituraPoco(),

					dadosImovel.getAnormalidadePoco(),

					dadosImovel.getIndicadorEmissaoConta());

				}

				System.out.println(dadosImovel.getIdImovel());

				System.out.println(alterouAgua);

				System.out.println(alterouPoco);

				if (alterouAgua || alterouPoco) {

					String[] linhas = dadosImovel.getLinhas();

					for (String string : linhas) {

						arquivo.append(string + "\n");

					}

				}

			}
        	
        	// Adiciona as linhas referentes a rota de marcação
        	arquivo.append( helper.getRegistrosRotaMarcacao() );
        	
        	if ( arquivo.length()  > 0 ){
        		InputStream is = new ByteArrayInputStream(  arquivo.toString().getBytes() );        	
        		InputStreamReader readerRetorno = new InputStreamReader( is );    		
        		retorno = new BufferedReader(readerRetorno);
        	}
        } catch ( IOException ex1 ){
        	throw new ControladorException( "erro.sistema", ex1 );
        }
        
        return retorno;
	}

	/**
	 * 
	 * Verifica se algum imóvel teve uma solicitação de releitura para uma rota
	 * e anomes
	 * 
	 * @author Bruno Barros
	 * @date 01/09/2010
	 * 
	 * @param idRota -
	 *            Id da rota a ser pesquisada
	 * 
	 * @return String com a mensagem formatada para o celular - Formato da
	 *         mensagem:
	 *         XXXXXXX...&YYYYY=123456,654321,567890&ZZZZZ=123123,123123,123123...
	 * 
	 * Sendo XXXX -> Mensagem a ser apresentada no celular. & -> Separador que
	 * indica que a mensagem acabou. YYYYY - > Nome do parametro = -> Indicador
	 * que o nome do parametro acabou 123456 -> Valor retornado , -> Separador
	 * de valor
	 * 
	 * Caso não haja imóveis para releitura, retorna nulo;
	 * 
	 * @throws ErroRepositorioException
	 */
	private String verificarSolicitacaoReleituraImovelImpressaoSimultanea(
			Integer idRota) throws ControladorException {

		// Verificamos se alguma solicitação de releitura foi feita para essa
		// rota
		Collection<ReleituraMobile> colReleituraMobile;
		StringBuffer retorno = new StringBuffer();

		try {
			Integer anoMesFaturamentoGrupoRota = this
					.retornaAnoMesFaturamentoGrupoDaRota(idRota);

			colReleituraMobile = this.repositorioMicromedicao
					.pesquisarImoveisReleituraMobileSolicitada(idRota,
							anoMesFaturamentoGrupoRota);

			if (colReleituraMobile != null && colReleituraMobile.size() > 0) {

				StringBuffer matriculas = new StringBuffer();
				StringBuffer matriculasFormatadas = new StringBuffer();

				int i = 1;

				for (ReleituraMobile releituraMobile : colReleituraMobile) {

					if (i == colReleituraMobile.size()) {
						matriculas.append(releituraMobile.getImovel().getId());
						matriculasFormatadas.append(releituraMobile.getImovel()
								.getMatriculaFormatada());
					} else {
						matriculas.append(releituraMobile.getImovel().getId()
								+ ",");
						matriculasFormatadas.append(releituraMobile.getImovel()
								.getMatriculaFormatada()
								+ ", ");
					}

					releituraMobile.setIndicadorMensagemRecebida(new Integer(
							ConstantesSistema.SIM));
					releituraMobile.setUltimaAlteracao(new Date());

					++i;
				}

				retorno.append("imoveis=" + matriculas);

				if (colReleituraMobile.size() == 1) {
					// retorno.append( "mensagem=Refazer leitura para o imovel "
					// + matriculasFormatadas.toString() + " . Ir para o imovel
					// ?&imoveis=" + matriculas );
				} else {
					// retorno.append( "mensagem=Refazer leitura para os imoveis
					// " + matriculasFormatadas.toString() + " . Ir para o
					// primeiro imovel ?&imoveis=" + matriculas );
				}

				// Colocamos como enviados
				this.getControladorBatch().atualizarColecaoObjetoParaBatch(
						colReleituraMobile);
				return retorno.toString();
			}
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			new ControladorException("erro.sistema", ex);
		}

		return null;
	}

	/**
	 * [UC1010] Emitir 2ª via de declaração anual de quitação de débitos
	 * 
	 * @Author Daniel Alves
	 * @Date 14/09/2010
	 * 
	 */
	public Collection pesquisarAnoImovelEmissao2ViaDeclaracaoAnualQuitacaoDebitos(
			String idImovel) throws ControladorException {

		Collection colecaoRetorno = new ArrayList();

		// Pesquisa
		try {
			Collection colecaoEmitir2ViaDeclaracaoAnualQuitacaoDebitos = repositorioFaturamento
					.pesquisarAnoImovelEmissao2ViaDeclaracaoAnualQuitacaoDebitos(idImovel);

			Iterator iterator = colecaoEmitir2ViaDeclaracaoAnualQuitacaoDebitos
					.iterator();

			while (iterator.hasNext()) {

				Emitir2ViaDeclaracaoAnualQuitacaoDebitosHelper helper = new Emitir2ViaDeclaracaoAnualQuitacaoDebitosHelper();

				Object[] objeto = (Object[]) iterator.next();

				// id
				if (objeto[0] != null) {
					helper.setId((Integer) objeto[0]);
				}

				// descricao
				if (objeto[1] != null) {
					helper.setDescricao((Integer) objeto[1]);
				}

				colecaoRetorno.add(helper);
			}

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return colecaoRetorno;

	}
	
	/**
	 * [UC1073] – Religar Imóveis Cortados com Consumo Real
	 * 
	 * @author Vivianne Sousa
	 * @date 13/09/2010
	 * 
	 */
	public void religarImovelCortadoComConsumoReal(
			Integer anoMesReferenciaFaturamento, Integer idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.LOCALIDADE, idLocalidade);

		try {

			Collection colecaoMatriculasImoveis = null;

			int mesfaturamento = Util.obterMes(anoMesReferenciaFaturamento);
			int anoFaturamento = Util.obterAno(anoMesReferenciaFaturamento);
			int ultimoDiaMes = new Integer(Util.obterUltimoDiaMes(
					mesfaturamento, anoFaturamento));
			Date data = Util.criarData(ultimoDiaMes, mesfaturamento,
					anoFaturamento);
			Date dataMenos30Dias = Util.subtrairNumeroDiasDeUmaData(data, 30);

			colecaoMatriculasImoveis = repositorioFaturamento
					.pesquisarImoveisCortados(LigacaoAguaSituacao.CORTADO,
							dataMenos30Dias, idLocalidade);

			if (colecaoMatriculasImoveis != null
					&& !colecaoMatriculasImoveis.isEmpty()) {

				Iterator icolecaoMatriculasImoveis = colecaoMatriculasImoveis
						.iterator();

				String idConsumoHistorico = null;
				while (icolecaoMatriculasImoveis.hasNext()) {

					Integer idImovel = (Integer) icolecaoMatriculasImoveis
							.next();

					idConsumoHistorico = repositorioFaturamento
							.pesquisarImoveisConsumoFaturadoReal(idImovel,
									anoMesReferenciaFaturamento,
									ConsumoTipo.REAL, LigacaoTipo.LIGACAO_AGUA);

					if (idConsumoHistorico != null) {

						selecionarAtualizaSituacaoLigacaoAguaImovelREGISTRATRANSACAO(
								idImovel, LigacaoAguaSituacao.LIGADO,
								Usuario.USUARIO_BATCH);

						selecionarAtualizaDataReligacaoAguaREGISTRATRANSACAO(
								idImovel, new Date(), Usuario.USUARIO_BATCH);

						System.out.println("--- IMOVEL --- " + idImovel);

						repositorioFaturamento.religarImovelCortado(idImovel,
								LigacaoAguaSituacao.LIGADO, new Date());

					}

				}
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}

	/**
	 * Registra transacao do imovel
	 * 
	 * @author Vivianne Sousa
	 * @date 14/09/2010
	 * 
	 * @return
	 * @throws ControladorException
	 */
	protected void selecionarAtualizaSituacaoLigacaoAguaImovelREGISTRATRANSACAO(
			Integer idImovel, Integer idLigacaoAguaSituacao,
			Usuario usuarioLogado) throws ControladorException {

		Imovel imovel = new Imovel();
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				idImovel));
		Collection colecaoImovel = this.getControladorUtil().pesquisar(
				filtroImovel, Imovel.class.getName());
		imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

		LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
		ligacaoAguaSituacao.setId(idLigacaoAguaSituacao);

		imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);

		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
		filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAgua.ID, idImovel));
		Collection colecaoLigacaoAgua = this.getControladorUtil().pesquisar(
				filtroLigacaoAgua, LigacaoAgua.class.getName());
		ligacaoAgua = (LigacaoAgua) Util
				.retonarObjetoDeColecao(colecaoLigacaoAgua);

		ligacaoAgua.setDataReligacao(new Date());

		imovel.setLigacaoAgua(ligacaoAgua);

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL,
				idImovel, idImovel, new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(imovel);

		getControladorTransacao().registrarTransacao(imovel);

	}

	/**
	 * Registra transacao do imovel
	 * 
	 * @author Vivianne Sousa
	 * @date 14/09/2010
	 * 
	 * @return
	 * @throws ControladorException
	 */
	protected void selecionarAtualizaDataReligacaoAguaREGISTRATRANSACAO(
			Integer idImovel, Date dataReligacao, Usuario usuarioLogado)
			throws ControladorException {

		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
		filtroLigacaoAgua.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAgua.ID, idImovel));
		Collection colecaoLigacaoAgua = this.getControladorUtil().pesquisar(
				filtroLigacaoAgua, LigacaoAgua.class.getName());
		ligacaoAgua = (LigacaoAgua) Util
				.retonarObjetoDeColecao(colecaoLigacaoAgua);

		ligacaoAgua.setDataReligacao(dataReligacao);

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL,
				ligacaoAgua.getId(), ligacaoAgua.getId(),
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(ligacaoAgua);

		getControladorTransacao().registrarTransacao(ligacaoAgua);

	}

	/**
	 * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
	 * 
	 * Método criado para evitar o if "compesa" ou if "caern". Para todas as
	 * empresas, o próximo arquivo do leiturista é disponibilizado assim que o
	 * arquivo anterior é finalizado. Apenas na compesa, não permite.
	 * 
	 * @author Bruno Barros
	 * @date 05/10/2010
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public boolean liberaProximoArquivoImpressaoSimultaneaOnLine()
			throws ControladorException {
		return true;
	}

	/**
	 * [UC1083] Prescrever Débitos de Imóveis Públicos
	 * 
	 * @author Hugo Leonardo
	 * @date 18/10/2010
	 * 
	 * @param PrescreverDebitosImovelHelper
	 * @throws ControladorException
	 */
	public Integer prescreverDebitosImoveisPublicos(
			PrescreverDebitosImovelHelper helper) throws ControladorException {

		Integer codigoProcessoIniciadoGerado = null;

		// Se Batch Manual - Processo.PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_MANUAL
		if (Util.verificarNaoVazio(helper.getFormaPrescricao())
				&& helper.getFormaPrescricao().equals("0")) {

			String dataFinal = helper.getDataFim();
			String dataInicial = helper.getDataInicio();

			// verifica se existe alguma esfera de poder selecionada.

			if (helper.getIdImovel() == null && helper.getIdCliente() == null) {

				if (helper.getEsferaPoder().equals("")) {

					throw new ActionServletException(
							"atencao.campo_texto.obrigatorio", null,
							"Esfera do Poder");
				}
			}

			// Validar se a Data tem pelo menos 5 anos a menos que a data atual
			Date data = Util.gerarDataApartirAnoMesRefencia(new Integer(dataFinal));
			Date dataAnoMes = Util.gerarDataApartirAnoMesRefencia(new Integer(
					helper.getAnoMesReferencia()));

			if (Util.anosEntreDatas(data, dataAnoMes) < 5) {
				throw new ControladorException(
						"atencao.anomesreferencia.invalida.prescricao", null,
						Util.formatarData(data));
			}

			Map parametros = new HashMap();
			parametros.put("helper", helper);

			codigoProcessoIniciadoGerado = getControladorBatch()
					.inserirProcessoIniciadoParametrosLivres(parametros,
							helper.getIdProcesso(), helper.getUsuarioLogado());
		} else {
			// Se Batch Automatico -
			// Processo.PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_AUTOMATICO
			Prescricao prescricao = new Prescricao();

			Integer idEsferaPoder = null;

			prescricao.setAnoMesReferencia(new Integer(helper
					.getAnoMesReferencia()));

			EsferaPoder esferaPoder1 = new EsferaPoder();
			EsferaPoder esferaPoder2 = new EsferaPoder();

			Collection colecaoEsferaPoder = Util.separarCamposString(",",
					helper.getEsferaPoder());

			Iterator iEsferaPoder = colecaoEsferaPoder.iterator();

			idEsferaPoder = new Integer(iEsferaPoder.next().toString());
			esferaPoder1.setId(idEsferaPoder);

			if (iEsferaPoder.hasNext()) {
				idEsferaPoder = new Integer(iEsferaPoder.next().toString());
				esferaPoder2.setId(idEsferaPoder);
			}

			prescricao.setEsferaPoder1(esferaPoder1);
			prescricao.setEsferaPoder2(esferaPoder2);
			prescricao.setUltimaAlteracao(new Date());

			getControladorUtil().inserir(prescricao);

		}

		return codigoProcessoIniciadoGerado;
	}

	/**
	 * 
	 * [UC1038] Prescrever Débitos de Imóveis Públicos
	 * 
	 * @author Hugo Leonardo
	 * @date 18/10/2010
	 * 
	 */
	public void prescreverDebitosImoveisPublicosManual(
			Integer idFuncionalidadeIniciada, Map parametros)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		PrescreverDebitosImovelHelper helper = (PrescreverDebitosImovelHelper) parametros
				.get("helper");

		// ---------------------------------------------------
		// Registrar o início do processamento da Unidade de
		// Processamento do Batch
		// ---------------------------------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE, 0);

		try {

			this.repositorioFaturamento
					.prescreverDebitosImoveisPublicosManual(helper);

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}

	/**
	 * 
	 * [UC1083] Prescrever Débitos de Imóveis Públicos Automático
	 * 
	 * @author Hugo Leonardo
	 * @date 19/10/2010
	 * 
	 */
	public Collection obterDadosPrescricaoDebitosAutomaticos()
			throws ControladorException {

		try {
			return repositorioFaturamento
					.obterDadosPrescricaoDebitosAutomaticos();

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * 
	 * [UC1083] Prescrever Débitos de Imóveis Públicos Automático
	 * 
	 * @author Hugo Leonardo
	 * @date 19/10/2010
	 * 
	 */
	public void prescreverDebitosImoveisPublicosAutomatico(
			Integer idFuncionalidadeIniciada, Integer anoMesReferencia,
			Integer anoMesPrescricao, Integer usuario, String idsEsferaPoder)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		// ---------------------------------------------------
		// Registrar o início do processamento da Unidade de
		// Processamento do Batch
		// ---------------------------------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE, 0);

		try {

			this.repositorioFaturamento
					.prescreverDebitosImoveisPublicosAutomatico(
							anoMesReferencia, anoMesPrescricao, usuario,
							idsEsferaPoder);

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {
			e.printStackTrace();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}

	/**
	 * Recupera o debitoCreditoSituacaoAtual da Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 22/11/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarDebitoCreditoSituacaoAtualDaConta(Integer idImovel,
			Integer anoMesReferencia) throws ControladorException {

		try {
			return repositorioFaturamento
					.pesquisarDebitoCreditoSituacaoAtualDaConta(idImovel,
							anoMesReferencia);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Mariana Victor
	 * @date 22/10/2010
	 * 
	 * @param idRota
	 * @param anoMesReferencia
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarArquivoTextoRoteiroEmpresa(Integer idRota, Integer anoMesReferencia)
			throws ControladorException {
		try {		
			return repositorioFaturamento.pesquisarArquivoTextoRoteiroEmpresa(
					idRota, anoMesReferencia);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * 
	 * Retificação de um conjunto de contas que foram pagas e que o pagamento
	 * não estava o débito e/ou crédito (Conta paga via Impressão Simultânea)
	 * 
	 * @author Sávio Luiz
	 * @date 27/12/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasPagasSemDebitoCreditoPago(
			Integer amreferencia, Integer idGrupo) throws ControladorException {

		try {
			return repositorioFaturamento
					.pesquisarContasPagasSemDebitoCreditoPago(amreferencia,
							idGrupo);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Metódo responsável por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB00016] Obter Mensagem da Conta em 3 Partes
	 * 
	 * @author Sávio Luiz, Ivan Sergio
	 * @date 24/05/2006, 01/12/2010
	 * @alteracoes: 01/12/2010 - RM247
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public String[] obterMensagemConta(EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro, int tipoConta,
			Collection<NacionalFeriado> colecaoNacionalFeriado)
			throws ControladorException {

		String[] mensagem = new String[7];

		// mensagem da conta para a anormalidade de consumo (Baixo Consumo,Auto
		// Consumo e Estouro de Consumo)

		mensagem = obterMensagemAnormalidadeConsumo(emitirContaHelper);

		if (mensagem == null || mensagem.equals("")) {

			mensagem = new String[7];

			Integer anoMesReferenciaFinal = sistemaParametro
					.getAnoMesFaturamento();
			int anoMesSubtraido = Util.subtrairMesDoAnoMes(
					anoMesReferenciaFinal, 1);
			Integer dataVencimentoFinalInteger = sistemaParametro
					.getAnoMesArrecadacao();
			String anoMesSubtraidoString = ""
					+ Util.subtrairMesDoAnoMes(dataVencimentoFinalInteger, 1);
			int ano = Integer.parseInt(anoMesSubtraidoString.substring(0, 4));
			int mes = Integer.parseInt(anoMesSubtraidoString.substring(4, 6));

			// recupera o ultimo dia do anomes e passa a data como parametro
			Calendar dataVencimentoFinal = GregorianCalendar.getInstance();
			dataVencimentoFinal.set(Calendar.YEAR, ano);
			dataVencimentoFinal.set(Calendar.MONTH, mes - 1);
			dataVencimentoFinal.set(Calendar.DAY_OF_MONTH, dataVencimentoFinal
					.getActualMaximum(Calendar.DAY_OF_MONTH));

			Date dataFinalDate = dataVencimentoFinal.getTime();

			// converte String em data
			Date dataVencimento = Util.converteStringParaDate("01/01/1900");

			ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper = getControladorCobranca()
					.obterDebitoImovelOuCliente(1,
							"" + emitirContaHelper.getIdImovel(), null, null,
							"190001", "" + anoMesSubtraido, dataVencimento,
							dataFinalDate, 1, 2, 2, 2, 2, 1, 2, null,2);

			// se o imovel possua débito(debitoImovelCobrança for diferente de
			// nulo)
			if (debitoImovelClienteHelper != null
					&& ((debitoImovelClienteHelper
							.getColecaoGuiasPagamentoValores() != null && !debitoImovelClienteHelper
							.getColecaoGuiasPagamentoValores().isEmpty()) || (debitoImovelClienteHelper
							.getColecaoContasValores() != null && !debitoImovelClienteHelper
							.getColecaoContasValores().isEmpty()))) {

				String dataVencimentoFinalString = Util
						.formatarData(dataFinalDate);

				mensagem[0] = "AVISO:EM " + dataVencimentoFinalString
						+ " CONSTA DEBITO SUJ.CORT. IGNORE CASO PAGO";

			} else {
				mensagem[0] = "";
			}

			if (tipoConta == 4) {

				StringBuilder msg = new StringBuilder();

				Object[] parmsDebitoAutomatico = null;
				try {
					parmsDebitoAutomatico = repositorioArrecadacao
							.pesquisarParmsDebitoAutomatico(emitirContaHelper
									.getIdImovel());
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

				String codigoAgencia = "";
				String idBanco = "";
				String indentificacaoBanco = "";
				if (parmsDebitoAutomatico != null) {

					// codigo Agencia
					if (parmsDebitoAutomatico[1] != null) {
						codigoAgencia = (String) parmsDebitoAutomatico[1];
					}

					// id do banco
					if (parmsDebitoAutomatico[2] != null) {
						idBanco = ((Integer) parmsDebitoAutomatico[2])
								.toString();
					}

					// indentificacao do banco
					if (parmsDebitoAutomatico[3] != null) {
						indentificacaoBanco = (String) parmsDebitoAutomatico[3];
					}

					msg.append("DEBITAR NO BANCO ");
					msg.append(idBanco);
					msg.append("/");
					msg.append(codigoAgencia);
					msg.append("/");
					msg.append(indentificacaoBanco);

					// Mensagem 2
					mensagem[1] = msg.toString();

					// Mensagem 3
					mensagem[2] = "";
				}
			}

			if (tipoConta == 3) {

				if (emitirContaHelper.getIdClienteResponsavel() != null
						&& !emitirContaHelper.getIdClienteResponsavel().equals(
								"")) {

					StringBuilder msg = new StringBuilder();

					String enderecoClienteResponsavel = null;

					// [UC0085]Obter Endereco
					enderecoClienteResponsavel = getControladorEndereco()
							.pesquisarEnderecoClienteAbreviado(
									new Integer(emitirContaHelper
											.getIdClienteResponsavel()));

					if (enderecoClienteResponsavel != null) {
						msg.append("ENTREGAR EM ");
						msg.append(Util.completaString(
								enderecoClienteResponsavel, 50));

						// Mensagem 2
						mensagem[1] = msg.toString();

						// Mensagem 3
						mensagem[2] = "";

					}
				}
			}

			if (tipoConta == 6) {

				StringBuilder msg2 = new StringBuilder();

				Object[] parmsDebitoAutomatico = null;
				try {
					parmsDebitoAutomatico = repositorioArrecadacao
							.pesquisarParmsDebitoAutomatico(emitirContaHelper
									.getIdImovel());
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}

				String codigoAgencia = "";
				String idBanco = "";
				String indentificacaoBanco = "";
				if (parmsDebitoAutomatico != null) {

					// codigo Agencia
					if (parmsDebitoAutomatico[1] != null) {
						codigoAgencia = (String) parmsDebitoAutomatico[1];
					}

					// id do banco
					if (parmsDebitoAutomatico[2] != null) {
						idBanco = ((Integer) parmsDebitoAutomatico[2])
								.toString();
					}

					// indentificacao do banco
					if (parmsDebitoAutomatico[3] != null) {
						indentificacaoBanco = (String) parmsDebitoAutomatico[3];
					}

					msg2.append("DEBITAR NO BANCO ");
					msg2.append(idBanco);
					msg2.append("/");
					msg2.append(codigoAgencia);
					msg2.append("/");
					msg2.append(indentificacaoBanco);

					if (emitirContaHelper.getIdClienteResponsavel() != null
							&& !emitirContaHelper.getIdClienteResponsavel()
									.equals("")) {

						StringBuilder msg = new StringBuilder();

						String enderecoClienteResponsavel = null;

						// [UC0085]Obter Endereco
						enderecoClienteResponsavel = getControladorEndereco()
								.pesquisarEnderecoClienteAbreviado(
										new Integer(emitirContaHelper
												.getIdClienteResponsavel()));

						if (enderecoClienteResponsavel != null) {
							msg.append("ENTREGAR EM ");
							msg.append(Util.completaString(
									enderecoClienteResponsavel, 50));

							// Mensagem 1
							mensagem[0] = msg2.toString();

							// Mensagem 2
							mensagem[1] = msg.toString();

						}
					} else {
						// Mensagem 2
						mensagem[1] = msg2.toString();

						// Mensagem 3
						mensagem[2] = "";

					}
				}
			}

			Object[] mensagensConta = null;

			// recupera o id do grupo de faturamento da conta
			Integer idFaturamentoGrupo = emitirContaHelper
					.getIdFaturamentoGrupo();
			// recupera o id da gerencia regional da conta
			Integer idGerenciaRegional = emitirContaHelper
					.getIdGerenciaRegional();
			// recupera o id da localidade da conta
			Integer idLocalidade = emitirContaHelper.getIdLocalidade();
			// recupera o id do setor comercial da conta
			Integer idSetorComercial = emitirContaHelper.getIdSetorComercial();

			// caso entre em alguma condição então não entra mais nas outras
			boolean achou = false;

			try {
				// o sistema obtem a mensagem para a conta
				// Caso seja a condição 1
				// (FaturamentoGrupo =null, GerenciaRegional=parmConta,
				// Localidade=parmConta, SetorComercial=parmConta)
				mensagensConta = repositorioFaturamento
						.pesquisarParmsContaMensagem(emitirContaHelper, null,
								idGerenciaRegional, idLocalidade,
								idSetorComercial);

				if (mensagensConta != null) {

					// Mensagem 3
					if (mensagensConta[0] != null) {
						mensagem[2] = (String) mensagensConta[0];
					} else {
						mensagem[2] = "";
					}

					// Mensagem 4
					if (mensagensConta[1] != null) {
						mensagem[3] = (String) mensagensConta[1];
					} else {
						mensagem[3] = "";
					}

					// Mensagem 5
					if (mensagensConta[2] != null) {
						mensagem[4] = (String) mensagensConta[2];
					} else {
						mensagem[4] = "";
					}
					achou = true;
				}

				if (!achou) {

					// Caso seja a condição 2
					// (FaturamentoGrupo =null, GerenciaRegional=parmConta,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper,
									null, idGerenciaRegional, idLocalidade,
									null);

					if (mensagensConta != null) {

						// Mensagem 3
						if (mensagensConta[0] != null) {
							mensagem[2] = (String) mensagensConta[0];
						} else {
							mensagem[2] = "";
						}

						// Mensagem 4
						if (mensagensConta[1] != null) {
							mensagem[3] = (String) mensagensConta[1];
						} else {
							mensagem[3] = "";
						}

						// Mensagem 5
						if (mensagensConta[2] != null) {
							mensagem[4] = (String) mensagensConta[2];
						} else {
							mensagem[4] = "";
						}
						achou = true;
					}
				}
				if (!achou) {
					// Caso seja a condição 3
					// (FaturamentoGrupo =null, GerenciaRegional=parmConta,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper,
									null, idGerenciaRegional, null, null);

					if (mensagensConta != null) {

						// Mensagem 3
						if (mensagensConta[0] != null) {
							mensagem[2] = (String) mensagensConta[0];
						} else {
							mensagem[2] = "";
						}

						// Mensagem 4
						if (mensagensConta[1] != null) {
							mensagem[3] = (String) mensagensConta[1];
						} else {
							mensagem[3] = "";
						}

						// Mensagem 5
						if (mensagensConta[2] != null) {
							mensagem[4] = (String) mensagensConta[2];
						} else {
							mensagem[4] = "";
						}
						achou = true;
					}
				}
				if (!achou) {
					// Caso seja a condição 4
					// (FaturamentoGrupo =parmConta, GerenciaRegional=null,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper,
									idFaturamentoGrupo, null, null, null);

					if (mensagensConta != null) {

						// Mensagem 3
						if (mensagensConta[0] != null) {
							mensagem[2] = (String) mensagensConta[0];
						} else {
							mensagem[2] = "";
						}

						// Mensagem 4
						if (mensagensConta[1] != null) {
							mensagem[3] = (String) mensagensConta[1];
						} else {
							mensagem[3] = "";
						}

						// Mensagem 5
						if (mensagensConta[2] != null) {
							mensagem[4] = (String) mensagensConta[2];
						} else {
							mensagem[4] = "";
						}
						achou = true;
					}
				}
				if (!achou) {
					// Caso seja a condição 5
					// (FaturamentoGrupo =null, GerenciaRegional=null,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper,
									null, null, null, null);
					if (mensagensConta != null) {
						// Mensagem 3
						if (mensagensConta[0] != null) {
							mensagem[2] = (String) mensagensConta[0];
						} else {
							mensagem[2] = "";
						}

						// Mensagem 4
						if (mensagensConta[1] != null) {
							mensagem[3] = (String) mensagensConta[1];
						} else {
							mensagem[3] = "";
						}

						// Mensagem 4
						if (mensagensConta[2] != null) {
							mensagem[4] = (String) mensagensConta[2];
						} else {
							mensagem[4] = "";
						}
						achou = true;
					}
				}
				// caso não tenha entrado em nenhuma das opções acima
				// então completa a string com espaçõs em branco
				if (!achou) {
					mensagem[2] = "";
					mensagem[3] = "";
					mensagem[4] = "";
				}				
				
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
		}
		
		FiltroFaturamentoGrupo filtro = new FiltroFaturamentoGrupo();
		filtro.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, 
				emitirContaHelper.getIdFaturamentoGrupo()));
		Collection<FaturamentoGrupo> colecaoFatGrupo = Fachada.getInstancia().pesquisar(
				filtro, FaturamentoGrupo.class.getName());
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util.retonarObjetoDeColecao(colecaoFatGrupo);
		//Caso o indicador de mensagem de quitaï¿½ï¿½o de dï¿½bito seja igual a SIM
		if(faturamentoGrupo!=null && faturamentoGrupo.getIndicadorMensagemQuitacao().intValue()==1){
			FiltroExtratoQuitacao filtroExtratoQuitacao = new FiltroExtratoQuitacao();
			filtroExtratoQuitacao.adicionarParametro(new ParametroSimples(FiltroExtratoQuitacao.ID_IMOVEL, 
					emitirContaHelper.getIdImovel()));
			filtroExtratoQuitacao.adicionarParametro(new ParametroSimples(FiltroExtratoQuitacao.ANO_MES_MENSAGEM_CONTA, 
					emitirContaHelper.getAmReferencia()));
			Collection<ExtratoQuitacao> colecaoExtratoQuitacao = Fachada.getInstancia().pesquisar(
					filtroExtratoQuitacao, ExtratoQuitacao.class.getName());
			ExtratoQuitacao extratoQuitacao = (ExtratoQuitacao) Util.retonarObjetoDeColecao(colecaoExtratoQuitacao);
			//Caso exista ExtratoQuitacao do imovel pro ano mes de referencia em questao
			if(extratoQuitacao!=null){
				//Exibir msg de quitacao na terceira linha da conta 
				FiltroExtratoQuitacaoItem filtroExtratoQuitacaoItem = new FiltroExtratoQuitacaoItem();
				filtroExtratoQuitacaoItem.adicionarParametro(new ParametroSimples(FiltroExtratoQuitacaoItem.ID_EXTRATO_QUITACAO, 
						extratoQuitacao.getId()));
				Collection<ExtratoQuitacaoItem> colecaoExtratoQuitacaoItem = Fachada.getInstancia().pesquisar(
						filtroExtratoQuitacaoItem, ExtratoQuitacaoItem.class.getName());
				ExtratoQuitacaoItem extratoQuitacaoItem = (ExtratoQuitacaoItem) Util.retonarObjetoDeColecao(colecaoExtratoQuitacaoItem);
				
				if(extratoQuitacaoItem != null){
					int anoQuitacao = Util.obterAno(extratoQuitacaoItem.getAnoMesReferenciaConta());
					mensagem[2] = "DECLARAMOS QUITADOS DÉBITOS DESSA MATRÍCULA NO ANO DE " + anoQuitacao;
					mensagem[3] =  "LEI 12007/09. ";
					
					if ( sistemaParametro.getNomeSiteEmpresa() != null && !sistemaParametro.getNomeSiteEmpresa().equals( "" ) ){
						mensagem[3] += "ACESSE " + sistemaParametro.getNomeSiteEmpresa().toUpperCase() + " E IMPRIMA O EXTRATO ";  
						mensagem[4] =  "EM NOSSA LOJA VIRTUAL.";
					}
				}
			}			
		}		

		return mensagem;
	}

	/**
	 * 
	 * Retificação de um conjunto de contas que foram pagas e que o pagamento
	 * não estava o débito e/ou crédito (Conta paga via Impressão Simultânea)
	 * 
	 * @author Sávio Luiz
	 * @date 27/12/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection retificarContasPagasSemDebitoCredito(
			Collection colecaoContasRetificar, Usuario usuarioLogado)
			throws ControladorException {

		Collection qtdContasRetificadas = new ArrayList();
		try {

			SistemaParametro sistemaParametro = getControladorUtil()
					.pesquisarParametrosDoSistema();

			ContaMotivoRetificacao contaMotivoRetificacao = new ContaMotivoRetificacao();
			contaMotivoRetificacao
					.setId(ContaMotivoRetificacao.VALOR_SERVICO_ERRADO);

			Iterator ite = colecaoContasRetificar.iterator();
			while (ite.hasNext()) {
				Object[] dadosConta = (Object[]) ite.next();
				Integer idConta = null;
				BigDecimal valorConta = null;
				BigDecimal valorpagamento = null;
				Conta conta = new Conta();
				if (dadosConta != null) {
					if (dadosConta[0] != null) {
						idConta = (Integer) dadosConta[0];
						conta.setId(idConta);
					}
					if (dadosConta[1] != null) {
						valorpagamento = (BigDecimal) dadosConta[1];
					}

					if (dadosConta[2] != null) {
						valorConta = (BigDecimal) dadosConta[2];
					}

					Collection colecaoDebitosCobrados = repositorioFaturamento
							.pesquisarValorPrestacaoDebitoCobradoSemreferencia(idConta);
					Collection dadosDebitosACobrar = new ArrayList();
					Collection debitosCobradosDescartados = new ArrayList();

					boolean retificarConta = false;
					if (colecaoDebitosCobrados != null
							&& !colecaoDebitosCobrados.isEmpty()) {
						Iterator itDebitosCobrados = colecaoDebitosCobrados
								.iterator();
						BigDecimal valorDebitosCobrados = new BigDecimal("0.00");
						while (itDebitosCobrados.hasNext()) {
							Object[] dadosDebitosCobrados = (Object[]) itDebitosCobrados
									.next();
							if (dadosDebitosCobrados != null) {
								BigDecimal valorDebitoCobrado = new BigDecimal(
										"0.00");
								if (dadosConta[0] != null) {
									DebitoCobrado debitoCobrado = (DebitoCobrado) dadosDebitosCobrados[0];
									valorDebitoCobrado = debitoCobrado
											.getValorPrestacao();
									valorDebitosCobrados = valorDebitosCobrados
											.add(valorDebitoCobrado);
									debitosCobradosDescartados
											.add(debitoCobrado);
								}
								if (dadosConta[1] != null) {
									Integer idDebitoAcobrar = (Integer) dadosDebitosCobrados[1];
									Object[] dadosDebitoACobrar = new Object[2];
									dadosDebitoACobrar[0] = idDebitoAcobrar;
									dadosDebitoACobrar[1] = valorDebitoCobrado;
									dadosDebitosACobrar.add(dadosDebitoACobrar);
								}
							}
							BigDecimal valorContaMaisDebitoCobrado = valorpagamento
									.add(valorDebitosCobrados);

							if (valorContaMaisDebitoCobrado
									.compareTo(valorConta) == 0) {
								retificarConta = true;
								qtdContasRetificadas.add(idConta);
								break;
							}

						}
					}

					// retificar Conta
					if (retificarConta) {

						Collection colecaoCategoriaOUSubcategoria = null;

						if (sistemaParametro
								.getIndicadorTarifaCategoria()
								.equals(
										SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)) {
							// [UC0108] - Quantidade de economias por categoria
							colecaoCategoriaOUSubcategoria = this
									.getControladorImovel()
									.obterQuantidadeEconomiasContaCategoriaPorSubcategoria(
											conta);

						} else {
							// [UC0108] - Quantidade de economias por categoria
							colecaoCategoriaOUSubcategoria = this
									.getControladorImovel()
									.obterQuantidadeEconomiasContaCategoria(
											conta);

						}

						Collection colecaoCreditoRealizado = this
								.obterCreditosRealizadosConta(conta);

						Collection colecaoDebitoCobrado = this
								.obterDebitosCobradosConta(conta);
						colecaoDebitoCobrado
								.removeAll(debitosCobradosDescartados);

						// [UC0150] - Retificar Conta
						Conta contaParaRetificacao = this
								.pesquisarContaRetificacao(conta.getId());

						Collection<CalcularValoresAguaEsgotoHelper> valoresConta = this
								.calcularValoresConta(

										Util
												.formatarAnoMesParaMesAno(contaParaRetificacao
														.getReferencia()),
										contaParaRetificacao.getImovel()
												.getId().toString(),
										contaParaRetificacao
												.getLigacaoAguaSituacao()
												.getId(), contaParaRetificacao
												.getLigacaoEsgotoSituacao()
												.getId(),
										colecaoCategoriaOUSubcategoria,
										contaParaRetificacao.getConsumoAgua()
												.toString(),
										contaParaRetificacao.getConsumoEsgoto()
												.toString(),
										contaParaRetificacao
												.getPercentualEsgoto()
												.toString(),
										contaParaRetificacao.getConsumoTarifa()
												.getId(), usuarioLogado);

						this
								.retificarConta(contaParaRetificacao
										.getReferencia(), contaParaRetificacao,
										contaParaRetificacao.getImovel(),
										colecaoDebitoCobrado,
										colecaoCreditoRealizado,
										contaParaRetificacao
												.getLigacaoAguaSituacao(),
										contaParaRetificacao
												.getLigacaoEsgotoSituacao(),
										colecaoCategoriaOUSubcategoria,
										contaParaRetificacao.getConsumoAgua()
												.toString(),
										contaParaRetificacao.getConsumoEsgoto()
												.toString(),
										contaParaRetificacao
												.getPercentualEsgoto()
												.toString(),
										contaParaRetificacao
												.getDataVencimentoConta(),
										valoresConta, contaMotivoRetificacao,
										null, usuarioLogado,
										contaParaRetificacao.getConsumoTarifa()
												.getId().toString(), false,
										null, null, false, null, null, null, null, null, true);

						// Inseri o débito a Cobrar e o Débito a Cobrar
						// Categoria
						if (dadosDebitosACobrar != null
								&& !dadosDebitosACobrar.isEmpty()) {
							Iterator itDebitoACobrar = dadosDebitosACobrar
									.iterator();
							while (itDebitoACobrar.hasNext()) {
								Object[] dadosDebitoACobrar = (Object[]) itDebitoACobrar
										.next();

								Integer idDebitoACobrar = (Integer) dadosDebitoACobrar[0];
								BigDecimal valorDebitoACobrar = (BigDecimal) dadosDebitoACobrar[1];

								// Pesquisa os débitos a cobrar
								FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
								filtroDebitoACobrar
										.adicionarParametro(new ParametroSimples(
												FiltroDebitoACobrar.ID,
												idDebitoACobrar));
								filtroDebitoACobrar
										.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarCategorias");
								filtroDebitoACobrar
										.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
								filtroDebitoACobrar
										.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
								filtroDebitoACobrar
										.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
								Collection debitosACobrar = getControladorUtil()
										.pesquisar(filtroDebitoACobrar,
												DebitoACobrar.class.getName());
								DebitoACobrar debitoACobrar = (DebitoACobrar) Util
										.retonarObjetoDeColecao(debitosACobrar);

								if (debitoACobrar != null
										&& !debitoACobrar.equals("")) {
									DebitoACobrar debitoACobrarInserir = new DebitoACobrar(
											new Date(),
											contaParaRetificacao
													.getReferencia(),
											debitoACobrar
													.getAnoMesCobrancaDebito(),
											valorDebitoACobrar,
											new Short("1"),
											new Short("0"),
											debitoACobrar
													.getCodigoSetorComercial(),
											debitoACobrar.getNumeroQuadra(),
											debitoACobrar.getNumeroLote(),
											debitoACobrar.getNumeroSubLote(),
											new Date(),
											Util.formataAnoMes(new Date()),
											debitoACobrar
													.getPercentualTaxaJurosFinanciamento(),
											debitoACobrar.getImovel(),
											debitoACobrar.getDocumentoTipo(),
											debitoACobrar.getParcelamento(),
											debitoACobrar
													.getFinanciamentoTipo(),
											debitoACobrar.getOrdemServico(),
											debitoACobrar.getQuadra(),
											debitoACobrar.getLocalidade(),
											debitoACobrar.getDebitoTipo(),
											debitoACobrar
													.getRegistroAtendimento(),
											debitoACobrar
													.getLancamentoItemContabil(),
											debitoACobrar
													.getDebitoCreditoSituacaoAnterior(),
											debitoACobrar
													.getDebitoCreditoSituacaoAtual(),
											debitoACobrar
													.getParcelamentoGrupo(),
											debitoACobrar.getCobrancaForma(),
											usuarioLogado,
											debitoACobrar
													.getDebitoACobrarCategorias());

									this.inserirDebitoACobrar(1,
											debitoACobrarInserir,
											valorDebitoACobrar,
											contaParaRetificacao.getImovel(),
											null, null, usuarioLogado, true);
								} else {
									// Caso não tenha débito a cobrar então
									// procura o débito a cobrar histórico
									// Pesquisa os débitos a cobrar
									FiltroDebitoACobrarHistorico filtroDebitoACobrarHistorico = new FiltroDebitoACobrarHistorico();
									filtroDebitoACobrarHistorico
											.adicionarParametro(new ParametroSimples(
													FiltroDebitoACobrar.ID,
													idDebitoACobrar));
									filtroDebitoACobrarHistorico
											.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
									filtroDebitoACobrarHistorico
											.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
									filtroDebitoACobrarHistorico
											.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
									Collection debitosACobrarHistorico = getControladorUtil()
											.pesquisar(
													filtroDebitoACobrarHistorico,
													DebitoACobrarHistorico.class
															.getName());
									DebitoACobrarHistorico debitoACobrarHistorico = (DebitoACobrarHistorico) Util
											.retonarObjetoDeColecao(debitosACobrarHistorico);

									if (debitoACobrarHistorico != null
											&& !debitoACobrarHistorico
													.equals("")) {
										DebitoACobrar debitoACobrarInserir = new DebitoACobrar(
												new Date(),
												contaParaRetificacao
														.getReferencia(),
												debitoACobrarHistorico
														.getAnoMesCobrancaDebito(),
												valorDebitoACobrar,
												new Short("1"),
												new Short("0"),
												debitoACobrarHistorico
														.getCodigoSetorComercial(),
												debitoACobrarHistorico
														.getNumeroQuadra(),
												debitoACobrarHistorico
														.getLote(),
												debitoACobrarHistorico
														.getSublote(),
												new Date(),
												Util.formataAnoMes(new Date()),
												debitoACobrarHistorico
														.getPercentualTaxaJurosFinanciamento(),
												debitoACobrarHistorico
														.getImovel(),
												debitoACobrarHistorico
														.getDocumentoTipo(),
												debitoACobrarHistorico
														.getParcelamento(),
												debitoACobrarHistorico
														.getFinanciamentoTipo(),
												debitoACobrarHistorico
														.getOrdemServico(),
												debitoACobrarHistorico
														.getQuadra(),
												debitoACobrarHistorico
														.getLocalidade(),
												debitoACobrarHistorico
														.getDebitoTipo(),
												debitoACobrarHistorico
														.getRegistroAtendimento(),
												debitoACobrarHistorico
														.getLancamentoItemContabil(),
												debitoACobrarHistorico
														.getDebitoCreditoSituacaoAnterior(),
												debitoACobrarHistorico
														.getDebitoCreditoSituacaoAtual(),
												debitoACobrarHistorico
														.getParcelamentoGrupo(),
												debitoACobrarHistorico
														.getCobrancaForma(),
												usuarioLogado, null);

										this.inserirDebitoACobrar(1,
												debitoACobrarInserir,
												valorDebitoACobrar,
												contaParaRetificacao
														.getImovel(), null,
												null, usuarioLogado, true);
									}
								}

							}
						}
					}

				}
			}
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return qtdContasRetificadas;
	}
	
	/**
	 * [UC0651] Inserir Comando de Negativação [FS0031] – Verificar existência
	 * de conta em nome do cliente
	 * 
	 * Pesquisa os relacionamentos entre cliente e conta.
	 * 
	 * @author Vivianne Sousa
	 * @date 29/12/2010
	 * 
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarSeExisteClienteConta(Integer idCliente,Collection colecaoContasIds)
		throws ControladorException {
		try {	
			
			boolean existeClienteConta = false;
			
			Collection colecaoClienteConta =  repositorioFaturamento.
				verificarSeExisteClienteConta(idCliente, colecaoContasIds);
			
			if(colecaoClienteConta != null && !colecaoClienteConta.isEmpty()){
				existeClienteConta = true;
			}

			return existeClienteConta; 
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Inserir Débitos para as contas impressas via Impressão Simultânea de
	 * Contas que sairam com o valor da conta errada (Alguns grupos com tarifa
	 * proporcional que não estava levando em consideração a quantidade de
	 * economias)
	 * 
	 * @author Sávio Luiz
	 * @date 12/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasComValorFaixasErradas(Integer amreferencia)
			throws ControladorException {

		try {
			return repositorioFaturamento
					.pesquisarContasComValorFaixasErradas(amreferencia);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Inserir Débitos para as contas impressas via Impressão Simultânea de
	 * Contas que sairam com o valor da conta errada (Alguns grupos com tarifa
	 * proporcional que não estava levando em consideração a quantidade de
	 * economias)
	 * 
	 * @author Sávio Luiz
	 * @date 12/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void inserirDebitosContasComValorFaixasErradas(Integer amreferencia,
			Usuario usuarioLogado) throws ControladorException {

		SistemaParametro sistemaParametro = getControladorUtil()
				.pesquisarParametrosDoSistema();

		DocumentoTipo documentoTipo = new DocumentoTipo();
		documentoTipo.setId(6);

		FinanciamentoTipo financiamentoTipo = new FinanciamentoTipo();
		financiamentoTipo.setId(1);

		DebitoTipo debitoTipo = new DebitoTipo();
		debitoTipo.setId(22);

		LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
		lancamentoItemContabil.setId(6);

		CobrancaForma cobrancaForma = new CobrancaForma();
		cobrancaForma.setId(1);

		DebitoCreditoSituacao debitoCreditoSituacaoAtual = new DebitoCreditoSituacao();
		debitoCreditoSituacaoAtual.setId(0);

		Collection colecaoContasValorFaixas = null;

		try {

			colecaoContasValorFaixas = repositorioFaturamento
					.pesquisarContasComValorFaixasErradas(amreferencia);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoContasValorFaixas != null
				&& !colecaoContasValorFaixas.isEmpty()) {

			Iterator ite = colecaoContasValorFaixas.iterator();
			while (ite.hasNext()) {
				Object[] dadosConta = (Object[]) ite.next();
				Integer idImovel = null;
				Integer referenciaConta = null;
				BigDecimal valorDiferenca = null;
				if (dadosConta != null) {
					if (dadosConta[0] != null) {
						idImovel = (Integer) dadosConta[0];
					}
					if (dadosConta[1] != null) {
						referenciaConta = (Integer) dadosConta[1];
					}

					if (dadosConta[2] != null) {
						valorDiferenca = (BigDecimal) dadosConta[2];
					}

					System.out
							.println("Matricula do imóvel Faixas:" + idImovel);

					// Pesquisa o imovel na base
					FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel.adicionarParametro(new ParametroSimples(
							FiltroImovel.ID, idImovel));

					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");

					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("localidade");
					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("quadra");

					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");

					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");

					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");

					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

					Collection<Imovel> imovelPesquisado = getControladorUtil()
							.pesquisar(filtroImovel, Imovel.class.getName());

					Imovel imovel = (Imovel) Util
							.retonarObjetoDeColecao(imovelPesquisado);

					if (imovel != null && !imovel.equals("")) {

						DebitoACobrar debitoACobrarInserir = new DebitoACobrar(
								new Date(), referenciaConta, sistemaParametro
										.getAnoMesFaturamento(),
								valorDiferenca, new Short("1"), new Short("0"),
								imovel.getSetorComercial().getCodigo(), imovel
										.getQuadra().getNumeroQuadra(), imovel
										.getLote(), imovel.getSubLote(),
								new Date(), Util.formataAnoMes(new Date()),
								BigDecimal.ZERO, imovel, documentoTipo, null,
								financiamentoTipo, null, imovel.getQuadra(),
								imovel.getLocalidade(), debitoTipo, null,
								lancamentoItemContabil, null,
								debitoCreditoSituacaoAtual, null,
								cobrancaForma, usuarioLogado, null);

						this.inserirDebitoACobrar(1, debitoACobrarInserir,
								valorDiferenca, imovel, null, null,
								usuarioLogado, true);

						// limpa os campos
						imovelPesquisado = null;
						imovel = null;
						debitoACobrarInserir = null;
						filtroImovel = null;

					}
				}

				// Limpa os campos
				dadosConta = null;
			}
		}

		System.out
				.println("FIM DO PROCESSAMENTO DAS CONTAS COM VALOR DAS FAIXAS ERRADAS.");

	}

	/**
	 * [RM-4643 (COMPESA)] Verificamos se o imóvel sofreu alterações depois de
	 * ter sido mandado para o GSAN a primeira vez
	 * 
	 * @author Bruno Barros
	 * @date 14/12/2010
	 * 
	 * @param anoMes
	 * @param idImovel
	 * @param tipoMedicao
	 * @param leitura
	 * @param idAnormalidade
	 * @return
	 * @throws ControladorException
	 */
	public boolean reprocessarImovelImpressaoSimultanea(
			Integer anoMes,
			Integer idImovel,
			Short tipoMedicao,
			Integer leitura,
			Integer idAnormalidade,
			Short icImpresso
			) throws ControladorException{
		try {		
			return repositorioFaturamento.reprocessarImovelImpressaoSimultanea( anoMes, idImovel, tipoMedicao, leitura, idAnormalidade, icImpresso );
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}		
	}
		/**
		 * [UC0366] Inserir Registro de Atendimento
		 * 
		 * [FS0048] – Verificar existência da conta.
		 * 
		 * @author Mariana Victor
		 * @date 27/01/2011
		 * 
		 * @param idImovel
		 * @return
		 * @throws ErroRepositorioException
		 */
	public Conta pesquisarContaAnoMesImovel(Integer idImovel, int anoMesReferencia)
		throws ControladorException {
		try {	
			
			return repositorioFaturamento.
				pesquisarContaAnoMesImovel(idImovel, anoMesReferencia);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0146] Manter Conta [FS0037]-Verificar ocorrências mesmo motivo no ano
	 * 
	 * @author Vivianne Sousa
	 * @date 11/02/2011
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisaQtdeContaEContaHistoricoRetificadaMotivo(
			Integer idMotivo,Integer idImovel)throws ControladorException {
		
		try {	
			Integer retorno = 0;
			// data corrente menos 1 ano
			Date dataLimite = new Date();
			dataLimite = Util.subtrairNumeroAnosDeUmaData(dataLimite,-1);
			
			Integer qtdeConta = repositorioFaturamento.pesquisaQtdeContaRetificadaMotivo
			(idMotivo,idImovel,dataLimite);
			
			Integer qtdeContaHistorico =  repositorioFaturamento.
			pesquisaQtdeContaHistoricoRetificadaMotivo(idMotivo, idImovel, dataLimite);
			
			retorno = qtdeConta + qtdeContaHistorico;
			
			return retorno;

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0146] Manter Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 11/02/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisaTabelaColunaContaMotivoRetificacaoColuna(
			Integer idMotivo)throws ControladorException {
		try {	
			
			return repositorioFaturamento.pesquisaTabelaColunaContaMotivoRetificacaoColuna(idMotivo);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1129] Gerar Relatório Devolução dos Pagamentos em Duplicidade
	 * 
	 * @author Hugo Leonardo
	 * @date 10/03/2011
	 * 
	 * @param FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper
	 * 
	 * @return Collection<RelatorioDevolucaoPagamentosDuplicidadeHelper>
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioDevolucaoPagamentosDuplicidade(
			FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper helper)throws ControladorException {
		
		Collection colecaoRetorno = new ArrayList();
		try {
			
			Collection colecaoSolicitacaoAcesso =  this.repositorioFaturamento.pesquisarRelatorioDevolucaoPagamentosDuplicidade(helper);
			
			Iterator iterator = colecaoSolicitacaoAcesso.iterator();
			
			String idRaAnterior = "";
			HashMap mapValorCreditoARealizar = null;
			
			while (iterator.hasNext()) {
			
				RelatorioDevolucaoPagamentosDuplicidadeHelper relatorioHelper = new RelatorioDevolucaoPagamentosDuplicidadeHelper();
				
				Object[] objeto = (Object[]) iterator.next();
				
				// ID Gerencia
				String idGerencia = "";
				if ( objeto[9] != null ) {
					idGerencia = objeto[9].toString();
				}
				
				relatorioHelper.setIdGerencia(idGerencia);

				// Nome Gerencia
				String nomeGerencia = "";
				if ( objeto[10] != null) {
					nomeGerencia = objeto[10].toString();
				}
				
				relatorioHelper.setNomeGerencia(nomeGerencia);

				// ID Unidade
				String idUnidade = "";
				if ( objeto[11] != null ) {
					idUnidade = objeto[11].toString();
				}
				
				relatorioHelper.setIdUnidade(idUnidade);

				// Nome Unidade
				String nomeUnidade = "";
				if ( objeto[12] != null) {
					nomeUnidade = objeto[12].toString();
				}
				
				relatorioHelper.setNomeUnidade(nomeUnidade);

				// ID Localidade
				String idLocalidade = "";
				if ( objeto[0] != null ) {
					idLocalidade = objeto[0].toString();
				}
				
				relatorioHelper.setIdLocalidade(idLocalidade);
				
				// Nome Localidade
				String nomeLocalidade = "";
				if ( objeto[8] != null) {
					nomeLocalidade += " " + objeto[8].toString();
				}
				
				relatorioHelper.setNomeLocalidade(nomeLocalidade);
			
				// Registro Atendimento
				String RA = "";
				if ( objeto[1] != null ) {
					RA = objeto[1].toString();
				}
				
				relatorioHelper.setNumeroRA(RA);
				
				// Imóvel
				String imovel = "";
				if ( objeto[2] != null ) {
					imovel = Util.retornaMatriculaImovelFormatada((Integer) objeto[2]);
				}
				
				relatorioHelper.setMatricula(imovel);
				
				// AnoMes Referência Pagamento
				String mesAnoReferenciaPagamento = "";
				if ( objeto[3] != null ) {
					mesAnoReferenciaPagamento = Util.formatarAnoMesParaMesAno( objeto[3].toString());
				}
				
				relatorioHelper.setMesAnoPagamentoDuplicidade(mesAnoReferenciaPagamento);
				
				// Valor Pagamento
				BigDecimal valorPagamento = new BigDecimal(0.0);
				if ( objeto[4] != null ) {
					valorPagamento = valorPagamento.add((BigDecimal) objeto[4]);
				}
				
				relatorioHelper.setValorPagamentoDuplicidade(valorPagamento);

				// AnoMes Referência Conta
				String mesAnoReferenciaConta = "";
				if ( objeto[5] != null ) {
					mesAnoReferenciaConta = Util.formatarAnoMesParaMesAno( objeto[5].toString());
				}
				
				relatorioHelper.setMesAnoConta(mesAnoReferenciaConta);

				// Valor Conta Original
				BigDecimal valorContaOriginal = new BigDecimal(0.0);
				if ( objeto[6] != null ) {
					valorContaOriginal = valorContaOriginal.add((BigDecimal) objeto[6]);
				}
				
				relatorioHelper.setValorConta(valorContaOriginal);
				
				// Valor Credito Realizado
				BigDecimal valorCreditoRealizado = new BigDecimal(0.0);
				
				
				if(objeto[2] != null && objeto[3] != null){
					
					Integer anoMes = (Integer) objeto[3];
					
					Collection<CreditoRealizado> colecaoCreditoRealizado = 
						this.repositorioFaturamento.pesquisarCreditosRealizado((Integer)objeto[2],anoMes);
					
					if(colecaoCreditoRealizado != null && !colecaoCreditoRealizado.isEmpty()){
						Iterator itera = colecaoCreditoRealizado.iterator();
						while (itera.hasNext()) {
							CreditoRealizado creditoRealizado = (CreditoRealizado) itera.next();
							
							valorCreditoRealizado = valorCreditoRealizado.add(creditoRealizado.getValorCredito());
						}
					}
					
					Collection<CreditoRealizadoHistorico> colecaoCreditoRealizadoHistorico = 
						this.repositorioFaturamento.pesquisarCreditosRealizadoHistorico((Integer)objeto[2],anoMes);
					
					if(colecaoCreditoRealizadoHistorico != null && !colecaoCreditoRealizadoHistorico.isEmpty()){
						Iterator itera = colecaoCreditoRealizadoHistorico.iterator();
						while (itera.hasNext()) {
							CreditoRealizadoHistorico creditoRealizado = (CreditoRealizadoHistorico) itera.next();
							
							valorCreditoRealizado = valorCreditoRealizado.add(creditoRealizado.getValorCredito());
						}
					}

					if(!idRaAnterior.equals(relatorioHelper.getNumeroRA())){
						mapValorCreditoARealizar = new HashMap();
					}
						
					BigDecimal valorCredito = 
						this.repositorioFaturamento.pesquisarValorCreditosARealizar((Integer)objeto[2],anoMes);

					BigDecimal valorCreditoHistorico = 
						this.repositorioFaturamento.pesquisarValorCreditosARealizarHistorico((Integer)objeto[2],anoMes);

					BigDecimal valorCreditoARealizar = BigDecimal.ZERO;
					
					if(valorCredito != null && valorCreditoHistorico != null){
						valorCreditoARealizar = valorCreditoARealizar.add(valorCredito);
						valorCreditoARealizar = valorCreditoARealizar.add(valorCreditoHistorico);
					}else if(valorCredito != null){
						valorCreditoARealizar = valorCreditoARealizar.add(valorCredito);
					}else if(valorCreditoHistorico != null){
						valorCreditoARealizar = valorCreditoARealizar.add(valorCreditoHistorico);
					}
					
					if(!mapValorCreditoARealizar.containsKey(anoMes)){
						mapValorCreditoARealizar.put(anoMes,valorCreditoARealizar);
					}
					

				}

				idRaAnterior = relatorioHelper.getNumeroRA();
				
				// Credito Realizado
				relatorioHelper.setCreditoRealizado(valorCreditoRealizado);
				
				// Credito a Realizar
				BigDecimal valorCreditoARealizar = BigDecimal.ZERO;
				if(mapValorCreditoARealizar != null && !mapValorCreditoARealizar.isEmpty()){
					Iterator itera = mapValorCreditoARealizar.values().iterator();
					while (itera.hasNext()) {
						BigDecimal valor = (BigDecimal) itera.next();
						valorCreditoARealizar = valorCreditoARealizar.add(valor);
					}
					
				}
				relatorioHelper.setCreditoARealizar(valorCreditoARealizar);
				
				// Data Atualizacao
				String dataAtualizacao = "";
				if ( objeto[7] != null ) {
					dataAtualizacao = Util.formatarData( (Date) objeto[7]);
				}
				relatorioHelper.setDataAtualizacao( dataAtualizacao);
				
				colecaoRetorno.add(relatorioHelper);	
			}
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		return colecaoRetorno;
	}
	
	/**
	 * [UC1157] Seleciona Comando para Retirar Imóvel da Tarifa Social [SB0003]
	 * Excluir Comando Selecionado
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @exception ErroRepositorioException
	 */
	public Integer pesquisarQtdeContaNaoPaga(Collection idContas) throws ControladorException {
		
		try {
			return repositorioFaturamento.pesquisarQtdeContaNaoPaga(idContas);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação
	 * 
	 * @author Raphael Rossiter
	 * @date 15/03/2011
	 * 
	 * @param identificacaoCodigoBarras
	 * @param valorPagamento
	 * @return Conta
	 * @throws ControladorException
	 */
	public Conta pesquisarContaTipoBoleto(Integer identificacaoCodigoBarras, BigDecimal valorPagamento) throws ControladorException{
		
		Conta conta = null;
		
		try {
			
			conta = this.repositorioFaturamento.pesquisarExistenciaContaPorNumeroBoleto(identificacaoCodigoBarras);
			
			if (conta == null){
				
				conta = this.repositorioFaturamento.pesquisarExistenciaContaPorIdentificadorEValor(identificacaoCodigoBarras, valorPagamento);
				
				if (conta == null){
					
					conta = this.repositorioFaturamento.pesquisarExistenciaContaPorIdentificadorTruncadoEValor(identificacaoCodigoBarras, 
					valorPagamento);
				}
			}
			
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
		
		return conta;
	}
	
	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação
	 * 
	 * @author Raphael Rossiter
	 * @date 15/03/2011
	 * 
	 * @param identificacaoCodigoBarras
	 * @param valorPagamento
	 * @return Conta
	 * @throws ControladorException
	 */
	public ContaHistorico pesquisarContaHistoricoTipoBoleto(Integer identificacaoCodigoBarras, BigDecimal valorPagamento) throws ControladorException{
		
		ContaHistorico conta = null;
		
		try {
			
			conta = this.repositorioFaturamento.pesquisarExistenciaContaHistoricoPorNumeroBoleto(identificacaoCodigoBarras);
			
			if (conta == null){
				
				conta = this.repositorioFaturamento.pesquisarExistenciaContaHistoricoPorIdentificadorEValor(identificacaoCodigoBarras, valorPagamento);
				
				if (conta == null){
					
					conta = this.repositorioFaturamento.pesquisarExistenciaContaHistoricoPorIdentificadorTruncadoEValor(identificacaoCodigoBarras, 
					valorPagamento);
				}
			}
			
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
		
		return conta;
	}

	/**
	 * [UC0933] Alterar Leiturista do Arquivo Texto para Leitura
	 * 
	 * Alterar o leiturista da tabela de movimento conta prefaturada
	 * 
	 * @author Bruno Barros
	 * @Data 12/04/2011
	 * 
	 */
	public void alterarLeituristaMovimentoRoteiroEmpresa( Integer idRota, Integer anoMes, Integer idLeituristaNovo ) throws ControladorException{
		try{
			this.repositorioFaturamento.alterarLeituristaMovimentoRoteiroEmpresa( idRota, anoMes, idLeituristaNovo );
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}
	
	/**
	 * [UC0933] Alterar Leiturista do Arquivo Texto para Leitura
	 * 
	 * Alterar o leiturista da tabela de movimento conta prefaturada
	 * 
	 * @author Bruno Barros
	 * @Data 12/04/2011
	 * 
	 */
	public void alterarLeituristaMovimentoRoteiroEmpresa( Collection<Integer> idsImovel, Integer anoMes, Integer idLeituristaNovo ) throws ControladorException{
		try{
			this.repositorioFaturamento.alterarLeituristaMovimentoRoteiroEmpresa( idsImovel, anoMes, idLeituristaNovo );
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}
	
	/**
	 * [UC1166] Gerar txt para impressï¿½o de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void gerarTxtImpressaoContasBraille(int idFuncionalidadeIniciada, FaturamentoGrupo faturamentoGrupo, Integer anoMesFaturamentoGrupo) throws ControladorException {
		
		BufferedWriter out = null;
		ZipOutputStream zos = null;
		File leitura = null;
		Date dataAtual = new Date();
		String nomeZip = null;
		int idUnidadeIniciada = 0;
		
		try{
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE,0);
			
			SistemaParametro sistemaParametro = repositorioUtil.pesquisarParametrosDoSistema();
			Collection colecaoContasBraille = pesquisarContaBraille(anoMesFaturamentoGrupo,faturamentoGrupo);
			
			if(colecaoContasBraille != null && !colecaoContasBraille.isEmpty()){
				
				nomeZip = "CONTAS_BRAILLE_" + Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);
				
				// Definindo arquivo para escrita
				nomeZip = nomeZip.replace("/", "_");
				File compactado = new File(nomeZip + ".zip");
				leitura = new File(nomeZip + ".txt");

			    zos = new ZipOutputStream(new FileOutputStream(compactado));
			    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));
				
			    // pegar o arquivo, zipar pasta e arquivo e escrever no stream
				System.out.println("***************************************");
				System.out.println("INICO DA CRIACAO DO ARQUIVO");
				System.out.println("QTD DE CONTAS:"	+ colecaoContasBraille.size());
				System.out.println("***************************************");
				
				Iterator iterContas = colecaoContasBraille.iterator();
				while (iterContas.hasNext()) {
					
					Object[] dadosConta = (Object[])iterContas.next();
					
					Conta conta = (Conta) dadosConta[0];
					EmitirContaHelper emitirContaHelper = (EmitirContaHelper)dadosConta[1];
					
					Integer idImovel = conta.getImovel().getId();
					
					StringBuilder contaTxt = new StringBuilder();
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(sistemaParametro.getNomeAbreviadoEmpresa(),10));
					contaTxt.append(System.getProperty("line.separator"));
					contaTxt.append(".....................");
					contaTxt.append(System.getProperty("line.separator"));
					
					String nomeClienteUsuario = null;
                    if (emitirContaHelper.getNomeImovel() != null
                        && !emitirContaHelper.getNomeImovel().equals("")) {
                        nomeClienteUsuario = emitirContaHelper.getNomeImovel();
                    } else {
                   	 	nomeClienteUsuario = this.obterNomeCliente(emitirContaHelper.getIdConta());
                    }
                    contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Nome do cliente = " + nomeClienteUsuario,70));
                    contaTxt.append(System.getProperty("line.separator"));
					
					// UC0085 - Obter Endereço
					String enderecoImovel = getControladorEndereco().pesquisarEnderecoFormatado(idImovel);
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Endereço = " + enderecoImovel,130));
					contaTxt.append(System.getProperty("line.separator"));
					
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Matrícula do imóvel = " + idImovel,30));
					contaTxt.append(System.getProperty("line.separator"));
					
					String categoriaEconomia = "Categoria ";
					Object[] dadosCategoria = repositorioFaturamento.pesquisarCategoriaPrincipalImovel(idImovel);
					categoriaEconomia = categoriaEconomia + ((String)dadosCategoria[0]);
					categoriaEconomia = categoriaEconomia + " = " + ((Short)dadosCategoria[1]);

					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(categoriaEconomia,30));
					contaTxt.append(System.getProperty("line.separator"));
					contaTxt.append(".....................");
					contaTxt.append(System.getProperty("line.separator"));
					
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Mês/Ano da conta = " + conta.getReferenciaFormatada(),26));
					contaTxt.append(System.getProperty("line.separator"));
					
					String leituraAnterior = "";
					if(conta.getNumeroLeituraAnterior() != null){
						leituraAnterior = conta.getNumeroLeituraAnterior().toString();
					}
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Leitura anterior = " + leituraAnterior,25));
					String leituraAtual = "";
					if(conta.getNumeroLeituraAtual() != null){
						leituraAtual = conta.getNumeroLeituraAtual().toString();
					}
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Leitura atual = " + leituraAtual,25));
					contaTxt.append(System.getProperty("line.separator"));
					
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Volume faturado de água (m3) = " + conta.getConsumoAgua(),35));
					contaTxt.append(System.getProperty("line.separator"));
					
					// Dias de Consumo
					Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
					Integer tipoMedicao = parmSituacao[1];
					
					Object[] parmsMedicaoHistorico = obterDadosMedicaoConta(emitirContaHelper, tipoMedicao);
					String dataLeituraAnterior = "";
					String dataLeituraAtual = "";
					if (parmsMedicaoHistorico != null) {
						if (parmsMedicaoHistorico[3] != null) {
							dataLeituraAnterior = Util.formatarData((Date) parmsMedicaoHistorico[3]);
						}

						if (parmsMedicaoHistorico[2] != null) {
							dataLeituraAtual = Util.formatarData((Date) parmsMedicaoHistorico[2]);
						}
					}
					String diasConsumo = "";
					if (!dataLeituraAnterior.equals("") && !dataLeituraAtual.equals("")) {
						// calcula a quantidade de dias de consumo que é a quantidade de dias
						// entre a data de leitura anterior e a data de leitura atual
						diasConsumo = "" + Util.obterQuantidadeDiasEntreDuasDatas((Date) parmsMedicaoHistorico[3],(Date) parmsMedicaoHistorico[2]);
					}
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Dias de consumo = " + diasConsumo,20));
					contaTxt.append(System.getProperty("line.separator"));
					
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Valor Água = " + Util.formatarMoedaReal(conta.getValorAgua()),30));
					contaTxt.append(System.getProperty("line.separator"));
					
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Valor Esgoto = " + Util.formatarMoedaReal(conta.getValorEsgoto()),30));
					contaTxt.append(System.getProperty("line.separator"));
					
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Valor Débitos = " + Util.formatarMoedaReal(conta.getDebitos()),30));
					contaTxt.append(System.getProperty("line.separator"));
					
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Valor Créditos = " + Util.formatarMoedaReal(conta.getValorCreditos()),30));
					contaTxt.append(System.getProperty("line.separator"));
					
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Valor Impostos =  " + Util.formatarMoedaReal(conta.getValorImposto()),30));
					contaTxt.append(System.getProperty("line.separator"));
					
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Valor a pagar = " + Util.formatarMoedaReal(conta.getValorTotalContaBigDecimal()),30));
					contaTxt.append(System.getProperty("line.separator"));
					
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando("Data de vencimento = " + Util.formatarData(conta.getDataVencimentoConta()),31));
					contaTxt.append(System.getProperty("line.separator"));
					contaTxt.append(".....................");
					contaTxt.append(System.getProperty("line.separator"));
					
					// SB0005-Obter Mensagem da Conta 
					String msg = obterMensagemContaBraille(emitirContaHelper, sistemaParametro);
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(msg,310));
					contaTxt.append(System.getProperty("line.separator"));
					contaTxt.append(".....................");
					contaTxt.append(System.getProperty("line.separator"));
					
					FiltroParametroSistema filtroParametroSistema = new FiltroParametroSistema();
					filtroParametroSistema.adicionarParametro(new ParametroSimples(FiltroParametroSistema.CODIGO_CONSTANTE, ParametroSistema.DESCRICAO_CONTATO_CONTA_BRAILLE));
					Collection<ParametroSistema> colecaoParametroSistemaCol = getControladorUtil().pesquisar(filtroParametroSistema, ParametroSistema.class.getName());
					String descricaoContato = "";
					if(!colecaoParametroSistemaCol.isEmpty()){
						ParametroSistema parametro = (ParametroSistema) Util.retonarObjetoDeColecao(colecaoParametroSistemaCol);
						descricaoContato = parametro.getValorParametro();
					}
					contaTxt.append(Util.completaStringComEspacoADireitaCondicaoTamanhoMaximoTruncando(descricaoContato,70));
					contaTxt.append(System.getProperty("line.separator"));
				    out.write(contaTxt.toString());		
					
				}
				out.flush();           
			}
	   		
    		getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);	
			System.out.println("***************************************");
			System.out.println("FIM DA CRIACAO DO ARQUIVO");
			System.out.println("***************************************");
		
		} catch (IOException ex) {
			ex.printStackTrace();
            getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
            throw new EJBException(ex);
	   } catch (Exception ex) {
            ex.printStackTrace();
            getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
            throw new EJBException(ex);
		} finally {
			try{
				if(out != null){
					out.close();
					ZipUtil.adicionarArquivo(zos, leitura);
					zos.close();
					leitura.delete();
				}
			} catch (IOException e) {
				getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);
	            throw new EJBException(e);
			}
        }

	}

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2011
	 */
	public Collection pesquisarContaBraille(Integer anoMesFaturamento, FaturamentoGrupo faturamentoGrupo)throws ControladorException{
		
		try {
			Collection colecaoRetorno = null;
			
			Collection colecaoContasBraille = repositorioFaturamento.pesquisarContaBraille(anoMesFaturamento, faturamentoGrupo);
			
			if(colecaoContasBraille != null && !colecaoContasBraille.isEmpty()){
				colecaoRetorno = new ArrayList();
				Iterator iterContas = colecaoContasBraille.iterator();
				
				while (iterContas.hasNext()) {
					Object[] retorno = new Object[2];
					Object[] objeto = (Object[])iterContas.next(); 
					
					// criado helper para pesquisa de msg da conta, nome Cliente,Dias de Consumo
					EmitirContaHelper emitirContaHelper = new EmitirContaHelper();
					
					Integer idConta = (Integer)objeto[0];
					Conta conta = repositorioFaturamento.obterObjetoConta(idConta);
					
					if(objeto[1] != null){
						emitirContaHelper.setNomeImovel((String)objeto[1]);
					}
					if(objeto[2] != null){
						emitirContaHelper.setIdGerenciaRegional((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						emitirContaHelper.setIdSetorComercial((Integer)objeto[3]);
					}
					
					emitirContaHelper.setIdImovel(conta.getImovel().getId());
					emitirContaHelper.setAmReferencia(conta.getReferencia());
					emitirContaHelper.setIdConta(conta.getId());
					emitirContaHelper.setIdLigacaoAguaSituacao(conta.getLigacaoAguaSituacao().getId());
					emitirContaHelper.setIdLigacaoEsgotoSituacao(conta.getLigacaoEsgotoSituacao().getId());
					emitirContaHelper.setIdImovelPerfil(conta.getImovelPerfil().getId());
					emitirContaHelper.setIdFaturamentoGrupo(conta.getFaturamentoGrupo().getId());
					emitirContaHelper.setIdLocalidade(conta.getLocalidade().getId());
					
					retorno[0] = conta;
					retorno[1] = emitirContaHelper;
					colecaoRetorno.add(retorno);
					
				}
			}
		
			return colecaoRetorno;
		} catch (Exception e) {
			e.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}
	
	
	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille [FS0001] –
	 * Verificar Grupos Faturados
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2011
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGrupoFaturamentoGrupoNaoFaturados(
			Integer anoMesReferenciaFaturamento)throws ControladorException{
		
		try {
			return repositorioFaturamento.
				pesquisarGrupoFaturamentoGrupoNaoFaturados(anoMesReferenciaFaturamento);
		
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
		
	}	
	
	/**
	 * Recupera o debitoCreditoSituacaoAtual da Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 22/11/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public boolean pesquisarContaDoImovelDiferentePreFaturada(Integer idImovel,
			Integer anoMesReferencia) throws ControladorException {

		try {
			return repositorioFaturamento
					.pesquisarContaDoImovelDiferentePreFaturada(idImovel,
							anoMesReferencia);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0713] Emitir Ordem de Serviço Seletiva [SB0002] Gerar TXT
	 * 
	 * @author Vivianne Sousa
	 * @date 29/06/2011
	 */
	public Integer pesquisarFaturamentoGrupoImovel(Integer idImovel)throws ControladorException{
		
		try {
			return repositorioFaturamento.pesquisarFaturamentoGrupoImovel(idImovel);
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			throw new EJBException(e);
		}
	}
	
	/**
	 * 
	 * [UC0840] - Atualizar Faturamento do Movimento Celular
	 * 
	 * @author Raphael Rossiter
	 * @date 20/07/2011
	 * 
	 * @param conta
	 * @param consumoAguaMovimentoCelular
	 * @param consumoAguaGSAN
	 * @param consumoEsgotoMovimentoCelular
	 * @param consumoEsgotoGSAN
	 * @throws ControladorException
	 */
	public void atualizarConsumoMovimentoCelular(Conta conta, Integer consumoAguaMovimentoCelular, Integer consumoAguaGSAN,
			Integer consumoEsgotoMovimentoCelular, Integer consumoEsgotoGSAN) throws ControladorException {
		
		if (conta != null && conta.getId() != null){
			
			//Verifica se o imóvel está associado a um imóvel condomínio
			Integer idImovelCondominio = this.getControladorImovel().pesquisarImovelCondominio(conta.getImovel().getId());
			
			// ÁGUA
			if (consumoAguaMovimentoCelular.intValue() != consumoAguaGSAN.intValue()){
				
				MovimentoContaPrefaturada movimentoContaPrefaturadaAgua = null;
				
				try {
					
					movimentoContaPrefaturadaAgua =  
					repositorioFaturamento.pesquisarMovimentoContaPrefaturada(conta.getId(), MedicaoTipo.LIGACAO_AGUA);

				} catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}
				
				if (movimentoContaPrefaturadaAgua != null){
					
					try {
						
						Integer idConsumoHistoricoAguaMacro = null;
						Integer consumoImovelVinculadosCondominioAgua = null;
						
						if (movimentoContaPrefaturadaAgua.getConsumoRateioAgua() != null){
							
							if (idImovelCondominio != null){
								
								Object[] dadosConsumoHistoricoAguaCondominio = this.getControladorMicromedicao().obterConsumoLigacaoAguaOuEsgotoDoImovel
								(idImovelCondominio, conta.getReferencia(), LigacaoTipo.LIGACAO_AGUA);
								
								if (dadosConsumoHistoricoAguaCondominio != null){
									
									//id do consumo historico do imóvel macro
									idConsumoHistoricoAguaMacro = (Integer) dadosConsumoHistoricoAguaCondominio[0];
									
									consumoImovelVinculadosCondominioAgua = movimentoContaPrefaturadaAgua.getConsumoMedido();
									
									if (consumoImovelVinculadosCondominioAgua == null &&
										movimentoContaPrefaturadaAgua.getConsumoCobrado() != null){
									
										consumoImovelVinculadosCondominioAgua = 
										movimentoContaPrefaturadaAgua.getConsumoCobrado() - movimentoContaPrefaturadaAgua.getConsumoRateioAgua(); 
									}
									
									//ATUALIZANDO A MEDIÇÃO E O CONSUMO DO IMÓVEL MACRO
									FiltroMovimentoContaPrefaturada filtroMovimentoContaPrefaturadaAgua = new FiltroMovimentoContaPrefaturada();
									
									filtroMovimentoContaPrefaturadaAgua.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaPrefaturada.MATRICULA, idImovelCondominio));
									
									filtroMovimentoContaPrefaturadaAgua.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO, conta.getReferencia()));
									
									filtroMovimentoContaPrefaturadaAgua.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaPrefaturada.ID_MEDICAO_TIPO, MedicaoTipo.LIGACAO_AGUA));
								
									
									Collection<MovimentoContaPrefaturada> colecaoMovimentoContaPrefaturadaAgua = this
									.getControladorUtil().pesquisar(filtroMovimentoContaPrefaturadaAgua, MovimentoContaPrefaturada.class.getName());
									
									if (colecaoMovimentoContaPrefaturadaAgua != null && !colecaoMovimentoContaPrefaturadaAgua.isEmpty()){
										
										MovimentoContaPrefaturada movimentoContaPrefaturadaAguaMACRO = (MovimentoContaPrefaturada)
										Util.retonarObjetoDeColecao(colecaoMovimentoContaPrefaturadaAgua);
										
										repositorioFaturamento.atualizarMedicaoHistoricoMovimentoCelular(movimentoContaPrefaturadaAguaMACRO);
										
										repositorioFaturamento.atualizarConsumoHistoricoMovimentoCelular(movimentoContaPrefaturadaAguaMACRO, 
											movimentoContaPrefaturadaAguaMACRO.getConsumoCobrado(), null, null, true);
									}
								}
							}
						}
						
						
						repositorioFaturamento.atualizarMedicaoHistoricoMovimentoCelular(movimentoContaPrefaturadaAgua);
						
						repositorioFaturamento.atualizarConsumoHistoricoMovimentoCelular(movimentoContaPrefaturadaAgua, 
								consumoAguaMovimentoCelular, idConsumoHistoricoAguaMacro, consumoImovelVinculadosCondominioAgua, false);

					} catch (ErroRepositorioException ex) {
						ex.printStackTrace();
						throw new ControladorException("erro.sistema", ex);
					}
				}
			}
			
			// ESGOTO
			if (consumoEsgotoMovimentoCelular.intValue() != consumoEsgotoGSAN.intValue()){
				
				MovimentoContaPrefaturada movimentoContaPrefaturadaEsgoto = null;
				
				try {
					
					movimentoContaPrefaturadaEsgoto =  
					repositorioFaturamento.pesquisarMovimentoContaPrefaturada(conta.getId(), MedicaoTipo.POCO);

				} catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}
				
				if (movimentoContaPrefaturadaEsgoto != null){
					
					try {
						
						Integer idConsumoHistoricoEsgotoMacro = null;
						Integer consumoImovelVinculadosCondominioEsgoto = null;
						
						if (movimentoContaPrefaturadaEsgoto.getConsumoRateioEsgoto() != null){
							
							if (idImovelCondominio != null){
								
								Object[] dadosConsumoHistoricoEsgotoCondominio = this.getControladorMicromedicao().obterConsumoLigacaoAguaOuEsgotoDoImovel
								(idImovelCondominio, conta.getReferencia(), LigacaoTipo.LIGACAO_ESGOTO);
								
								if (dadosConsumoHistoricoEsgotoCondominio != null){
									
									//id do consumo historico do imóvel macro
									idConsumoHistoricoEsgotoMacro = (Integer) dadosConsumoHistoricoEsgotoCondominio[0];
									
									consumoImovelVinculadosCondominioEsgoto = movimentoContaPrefaturadaEsgoto.getConsumoMedido();
									
									if (consumoImovelVinculadosCondominioEsgoto == null &&
										movimentoContaPrefaturadaEsgoto.getConsumoCobrado() != null){
									
										consumoImovelVinculadosCondominioEsgoto = 
										movimentoContaPrefaturadaEsgoto.getConsumoCobrado() - movimentoContaPrefaturadaEsgoto.getConsumoRateioEsgoto(); 
									}
									
									//ATUALIZANDO A MEDIÇÃO E O CONSUMO DO IMÓVEL MACRO
									FiltroMovimentoContaPrefaturada filtroMovimentoContaPrefaturadaEsgoto = new FiltroMovimentoContaPrefaturada();
									
									filtroMovimentoContaPrefaturadaEsgoto.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaPrefaturada.MATRICULA, idImovelCondominio));
									
									filtroMovimentoContaPrefaturadaEsgoto.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO, conta.getReferencia()));
									
									filtroMovimentoContaPrefaturadaEsgoto.adicionarParametro(new ParametroSimples(
									FiltroMovimentoContaPrefaturada.ID_MEDICAO_TIPO, MedicaoTipo.POCO));
								
									
									Collection<MovimentoContaPrefaturada> colecaoMovimentoContaPrefaturadaEsgoto = this
									.getControladorUtil().pesquisar(filtroMovimentoContaPrefaturadaEsgoto, MovimentoContaPrefaturada.class.getName());
									
									if (colecaoMovimentoContaPrefaturadaEsgoto != null && !colecaoMovimentoContaPrefaturadaEsgoto.isEmpty()){
										
										MovimentoContaPrefaturada movimentoContaPrefaturadaEsgotoMACRO = (MovimentoContaPrefaturada)
										Util.retonarObjetoDeColecao(colecaoMovimentoContaPrefaturadaEsgoto);
										
										repositorioFaturamento.atualizarMedicaoHistoricoMovimentoCelular(movimentoContaPrefaturadaEsgotoMACRO);
										
										repositorioFaturamento.atualizarConsumoHistoricoMovimentoCelular(movimentoContaPrefaturadaEsgotoMACRO, 
											movimentoContaPrefaturadaEsgotoMACRO.getConsumoCobrado(), null, null, true);
									}
								}
							}
						}
						
						repositorioFaturamento.atualizarMedicaoHistoricoMovimentoCelular(movimentoContaPrefaturadaEsgoto);
						
						repositorioFaturamento.atualizarConsumoHistoricoMovimentoCelular(movimentoContaPrefaturadaEsgoto, 
								consumoEsgotoMovimentoCelular, idConsumoHistoricoEsgotoMacro, consumoImovelVinculadosCondominioEsgoto, false);

					} catch (ErroRepositorioException ex) {
						ex.printStackTrace();
						throw new ControladorException("erro.sistema", ex);
					}
				}
			}
		}
	}
	/**
	 * [UC1194] Consultar Estrutura Tarifária Loja Virtual [SB0001] Pesquisar
	 * Tarifa Social ou Tarifa Mínima
	 * 
	 * Método que vai retornar um Helper que possui o consumo da tarifa mínima e
	 * da tarifa social e seus respectivos valores.
	 * 
	 * @author Arthur Carvalho
	 * @since 20/01/2012
	 * 
	 * @param idCategoria , idSubcategoria
	 * 
	 * @return Collection<ConsultarEstruturaTarifariaPortalCaemaHelper>
	 */
	public ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> pesquisarEstruturaTarifaria(Integer idCategoria, Integer idSubCategoriaMinima, Integer idSubCagetoriaNormal) 
		throws ControladorException {
		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> estruturaTarifaria = new ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper>();

		try{
			Collection<Object[]> dados;
			
			// ------------------- Pesquisa a Tarifa Normal (Mínima) -------------------//
			dados  = this.repositorioFaturamento.pesquisarTarifaSocialOuTarifaMinima(ConsumoTarifa.CONSUMO_NORMAL, idCategoria, idSubCategoriaMinima);
			estruturaTarifaria = this.adicionarEstruturaTarifaria(estruturaTarifaria, dados, false, false);
			
			// ------------------- Pesquisa a Tarifa Normal -------------------//
			if ( idSubCagetoriaNormal != null )  {
				dados = this.repositorioFaturamento.pesquisarTarifaNormal(ConsumoTarifa.CONSUMO_NORMAL, idCategoria, idSubCagetoriaNormal);
				estruturaTarifaria = this.adicionarEstruturaTarifaria(estruturaTarifaria, dados, true, false);
			}
			
		}catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return estruturaTarifaria;
	}
	
	/**
	 * [UC1194] Consultar Estrutura Tarifária Loja Virtual.
	 *  Método auxiliar para montar a estrutura tarifária da loja virtual.
	 * 
	 * @param ConsultarEstruturaTarifariaPortalHelper -
	 *            Helper que vai ser acrescentado novas estruturas
	 * @param Collection
	 *            <Object[]> - Coleção de objetos que retornou da busca no
	 *            repositório
	 * @param tarifaNormal -
	 *            Booleano que vai indicar se a tarifa é normal ou tarifa mínima
	 * @param tarifaSocial -
	 *            Booleano que vai indicar se a tarifa é social (Apenas para a
	 *            categoria de imóvel residencial)
	 * 
	 */
	private ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> adicionarEstruturaTarifaria(
			ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> estrutura, Collection<Object[]> dados, 
			boolean tarifaNormal, boolean tarifaSocial){
		StringBuilder sb = new StringBuilder();
		String valor = null;
		Object[] object;
		// Verifica se a consulta retornou algum registro (Tarifa Social)
		if(!Util.isVazioOrNulo(dados)){
			Iterator<Object[]> iterator = dados.iterator();

			if(tarifaNormal){
				/*
				 * Adiciona todos os registros encontrados na coleção do helper.
				 * A primeira coluna que retorna da consulta é o valor incial do
				 * consumo a segunda coluna é o valor final do consumo e a
				 * terceira coluna é o valor do tarifa. E a terceira a categoria
				 * da tarifa.
				 */
				while(iterator.hasNext()){
					object = iterator.next();
					
					/*
					 * A String é concatenada com o '.000', pois a consulta
					 * retorna o valor em 1000L Ex: Consulta retornou 10 no
					 * valor incial do consumo 10 significa que são 10.000L.
					 */
					
					String valorsubtraido =(String) object[0]; 
					String valorFinal = (String) object[1];
					sb.append("excedente de ");
					sb.append(Util.subtrairBigDecimal(Util.formatarStringParaBigDecimal(valorsubtraido.replace(",", ".")), new BigDecimal(1)));
					
					if(!valorFinal.equals("999999")){
						sb.append(" - ");
						sb.append((String) object[1]);
					}
					
					if(object[2] != null){
						valor = Util.formatarMoedaReal((BigDecimal)object[2]);
					}
					/*
					 * O último parâmetro é um integer (1), pois esse número irá
					 * auxiliar na montagem do relatório da estrutura tarifária.
					 * Esse índice indica que são consumidores medidos. No
					 * ExibirConsultarEstruturaTarifariaPortalAction serão
					 * inicializados os consumidores não medidos cuja costante é
					 * igual à 2.
					 */
					estrutura.add(new ConsultarEstruturaTarifariaPortalCaemaHelper((String) object[3], sb.toString(), valor, 1, null));
					sb = new StringBuilder();
				}
			}else{
				/*
				 * Adiciona todos os registros encontrados na coleção do helper.
				 * A primeira coluna que retorna da consulta é o consumo e a
				 * segunda coluna é o valor do tarifa. E a terceira a categoria
				 * da tarifa.
				 */
				while(iterator.hasNext()){
					object = iterator.next();
					
					/*
					 * A String é concatenada com o '.000', pois a consulta
					 * retorna o valor em 1000L Ex: Consulta retornou 10 no
					 * valor incial do consumo 10 significa que são 10.000L.
					 */
					sb.append("de 00 - ");
					sb.append((String) object[0]);
					if(object[1] != null){
						valor = Util.formatarMoedaReal((BigDecimal)object[1]);
					}
					
					/*
					 * O último parâmetro é um integer (1), pois esse número irá
					 * auxiliar na montagem do relatório da estrutura tarifária.
					 * Esse índice indica que são consumidores medidos. No
					 * ExibirConsultarEstruturaTarifariaPortalAction serão
					 * inicializados os consumidores não medidos cuja costante é
					 * igual à 2.
					 */
					estrutura.add(new ConsultarEstruturaTarifariaPortalCaemaHelper((String) object[2], sb.toString(), valor, 1, (String) object[0]));
				}
			}
		}else{
			estrutura.add(new ConsultarEstruturaTarifariaPortalCaemaHelper("", "", "", null));
		}
		return estrutura;
	}
	
	/**
	 * [UC1194] Consultar Estrutura Tarifária Loja Virtual Método auxiliar para
	 * montar a estrutura tarifária da loja virtual.
	 * 
	 * @param ConsultarEstruturaTarifariaPortalHelper -
	 *            Helper que vai ser acrescentado novas estruturas
	 * @param Collection
	 *            <Object[]> - Coleção de objetos que retornou da busca no
	 *            repositório de imóvel residencial)
	 * 
	 */
	private ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> adicionarEstruturaTarifariaAguaBruta(
			ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> estrutura, Collection<Object[]> dados){
		StringBuilder sb = new StringBuilder();
		String valor = null;
		Object[] object;
		/*
		 * Booleano responsável por verificar se a estrutura tarifaria é a primeira da categoria
		 * Caso seja a primeira, Adicionar a descrição da categoria object[3], caso contrário
		 * adicionar a string vazia para a descrição. 
		 */
		boolean primeiroDaCategoria = true;
		
		// Verifica se a consulta retornou algum registro
		if(!Util.isVazioOrNulo(dados)){
			Iterator<Object[]> iterator = dados.iterator();

				/*
				 * Adiciona todos os registros encontrados na coleção do helper.
				 * A primeira coluna que retorna da consulta é o consumo e a
				 * segunda coluna é o valor do tarifa. E a terceira a categoria
				 * da tarifa.
				 */
				while(iterator.hasNext()){
					object = iterator.next();
					
					sb.append("entre ");
					sb.append((String) object[0]);
					sb.append(".000");
					sb.append(" e ");
					sb.append((String) object[1]);
					sb.append(".000 litros");
					
					if(object[2] != null){
						valor = Util.formatarMoedaReal((BigDecimal)object[2]);
					}
					
					if(primeiroDaCategoria){
						/*
						 * O último parâmetro é um integer (3), pois esse número irá
						 * auxiliar na montagem do relatório da estrutura tarifária.
						 * Esse índice indica que são tarifas do tipo água bruta.
						 */ 
						estrutura.add(new ConsultarEstruturaTarifariaPortalCaemaHelper((String) object[3], sb.toString(), valor, 3));
						primeiroDaCategoria = false;
					}else{
						estrutura.add(new ConsultarEstruturaTarifariaPortalCaemaHelper("", sb.toString(), valor, 3));
					}
					sb = new StringBuilder();
				}
		}else{
			estrutura.add(new ConsultarEstruturaTarifariaPortalCaemaHelper("", "", "", null));
		}
		return estrutura;
	}
	
	/**
	 * UC 8xx - Relatório das Multas de Autos de Infração Pendentes
	 * 
	 * @author Hugo Azevedo
	 * @date 11/06/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	
	public Collection obterColecaoGrupoFaturamento() throws ControladorException{
			Collection colecaoQuery = new ArrayList();
		try{
			colecaoQuery = repositorioFaturamento.obterColecaoGrupoFaturamento();
		}
		
		catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		// Monta os objetos da coleção retornada.
		Collection retorno = new ArrayList();
		Iterator it = colecaoQuery.iterator();
		FaturamentoGrupo fr = null;
		Object[] objGroup = null;
		while(it.hasNext()){
			
			fr = new FaturamentoGrupo();
			objGroup = (Object[])it.next();
			fr.setId((Integer)objGroup[0]);
			fr.setDescricao((String)objGroup[1]);
			
			// Adiciona o objeto ao retorno
			retorno.add(fr);
			
		}
		
		return retorno;
	}
	
	/**
	 * UC1198 - Relatório das Multas de Autos de Infração Pendentes
	 * 
	 * @author Hugo Azevedo
	 * @date 11/06/2011
	 * 
	 * @throws ControladorException
	 */
	
	public Collection pesquisarDadosRelatorioAutoInfracaoPendentes(
			Integer grupo, Integer funcionario, Integer rota, Date periodoAtuacaoInicial, Date periodoAtuacaoFinal) throws ControladorException {
		Collection retornoQuery = new ArrayList();
		
		try {
			retornoQuery = repositorioFaturamento
					.pesquisarDadosRelatorioAutoInfracaoPendentes(grupo,
							funcionario, rota, periodoAtuacaoInicial, periodoAtuacaoFinal);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		Collection<RelatorioMultasAutosInfracaoPendentesBean> colecaoBeans = new ArrayList<RelatorioMultasAutosInfracaoPendentesBean>();
		Iterator it = retornoQuery.iterator();
		
		ArrayList debitosACobrar = new ArrayList();

		while (it.hasNext()) {
			RelatorioMultasAutosInfracaoPendentesBean bean = new RelatorioMultasAutosInfracaoPendentesBean();
			Object[] obj = (Object[]) it.next();
			
			bean.setIdGrupoFaturamento((String) obj[0]);
			bean.setGrupoFaturamento((String) obj[1]);
			bean.setIdFuncionario((String) obj[2]);
			bean.setNomeFuncionario((String) obj[2] + " - " + (String) obj[3]);
			bean.setIdLocalidade((String) obj[4]);
			bean.setNomeLocalidade((String) obj[5]);
			bean.setRota((String) obj[6]);
			bean.setMatriculaImovel(Util.retornaMatriculaImovelFormatada(new Integer(obj[7].toString())));
			bean.setNomeCliente((String) obj[9]);
			bean.setAutoInfracao((String) obj[10]);
			bean.setDescricaoServico((String) obj[11]);
			bean.setDataAutuacao(Util.formatarData((Date) obj[12]));
			String enderecoClienteResponsavel = "";
			enderecoClienteResponsavel = this.getControladorEndereco()
					.pesquisarEnderecoClienteAbreviado(
							new Integer((String) obj[8]));
			bean.setEndereco(enderecoClienteResponsavel);
			
			colecaoBeans.add(bean);
			
			Integer idDebACobrar = (Integer)obj[13];
			boolean verificarIPDC = verificarItensParcelamentos(idDebACobrar);
			if(verificarIPDC)
				debitosACobrar.add(bean);			
		
		}
		return colecaoBeans;			
	}	

	/**
	 * UC1198 - Relatório das Multas de Autos de Infração Pendentes
	 * 
	 * @author Hugo Azevedo
	 * @date 11/06/2011
	 * 
	 * @throws ControladorException
	 */
	private boolean verificarItensParcelamentos(Integer idDebitoACobrar) throws ControladorException{
		boolean retorno = false;
		try {
			
			// Verificar para cada item de parcelamento
			Collection collItensParcelamentosN1 = repositorioFaturamento.
					pesquisarItensParcelamentosNivel1(idDebitoACobrar);
			
			// Foi parcelado
			if(collItensParcelamentosN1 != null && !collItensParcelamentosN1.isEmpty()){
				Iterator it = collItensParcelamentosN1.iterator();
				while(it.hasNext()){
					Integer dadosItensParcelamento = (Integer)it.next();
						
					// Parcelamento não pago
					if(dadosItensParcelamento != null){
						Integer idConta = dadosItensParcelamento;
						
					
						Collection<Object[]> collItensParcelamentosN2 = repositorioFaturamento.
								pesquisarItensParcelamentosNivel2(idConta);
						
						
						// Foi re-parcelado
						if(collItensParcelamentosN2 != null && !collItensParcelamentosN2.isEmpty()){
							Iterator it2 = collItensParcelamentosN2.iterator();
							while(it2.hasNext()){
								Integer dadosItensParcelamentoN2 = (Integer)it2.next();
									
								 // Re-parcelamento não pago
								if(dadosItensParcelamentoN2 != null){
									return true;
								}	
							}
						}
						
						// Não é re-parcelamento
						else
							return true;						
						
					}
				}
			}
			
			// Não é parcelamento
			else
				return true;
		
	
			return retorno;
		
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	
	/**
	 * [UC1216] Suspender Leitura para Imóvel com Hidrômetro Retirado
	 * 
	 * @author Vivianne Sousa
	 * @date 23/08/2011
	 */
	public void suspenderLeituraParaImovelComHidrometroRetirado(Integer idFuncionalidadeIniciada,
			Integer referenciaFaturamento,Integer grupofaturamento,Integer idRota) throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.ROTA, idRota);

			Integer idAnormalidade = LeituraAnormalidade.HIDROMETRO_RETIRADO ; 
			
			//ANO_MES_FINAL = referenciaFaturamento + + ltan_nnmesesleiturasuspensa 
			//(da tabela leitura_anormalidade com ltan_id = 2)
			LeituraAnormalidade ltan = this.repositorioFaturamento.
			obterNumeroMesesLeituraSuspensaLeituraAnormalidade(idAnormalidade);
			Integer anoMesFaturamentoSituacaoFim = Util.somaMesAnoMesReferencia(
					referenciaFaturamento,ltan.getNumeroMesesLeituraSuspensa());
			Integer qtdAnormalidades = ltan.getNumeroVezesSuspendeLeitura();
			
			//FATURAMENTO_SITUACAO_MOTIVO
			FaturamentoSituacaoMotivo faturamentoSituacaoMotivo = new FaturamentoSituacaoMotivo();
			faturamentoSituacaoMotivo.setId(FaturamentoSituacaoMotivo.IMOVEL_MEDIDO_COM_HIDROMETRO_RETIRADO);

			//FATURAMENTO_SITUACAO_TIPO
			FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
			faturamentoSituacaoTipo.setId(FaturamentoSituacaoTipo.PARALISAR_LEITURA_FATURAR_MEDIA); 
			
			//OBSERVAÇÃO
			String observacao = "INCLUIDO ATRAVES DE ROTINA BATCH POR ANORMALIDADE DE LEITURA = HIDROMETRO RETIRADO";
			
			//[SB0001] – Selecionar Imóveis com Hidrômetro Retirado
			Collection idsImovel = this.repositorioFaturamento.
				pesquisarImovelNumeroDeOcorrenciasConsecultivasAnormalidades(
				idAnormalidade, qtdAnormalidades,referenciaFaturamento,grupofaturamento,idRota);
			
			Iterator iterimoveis = idsImovel.iterator();
			while (iterimoveis.hasNext()) {
				//Para cada imóvel selecionado o sistema inclui o imóvel na situação especial de faturamento
				//[SB0002 – Incluir Imóvel na Situação Especial de Faturamento]

				Integer idImovel = (Integer) iterimoveis.next();
				Imovel imovel = new Imovel();
				imovel.setId(idImovel);
				
				FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = new FaturamentoSituacaoHistorico();
				faturamentoSituacaoHistorico.setImovel(imovel);
				faturamentoSituacaoHistorico.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
				faturamentoSituacaoHistorico.setFaturamentoSituacaoMotivo(faturamentoSituacaoMotivo);
				faturamentoSituacaoHistorico.setUsuario(Usuario.USUARIO_BATCH);
				faturamentoSituacaoHistorico.setUsuarioInforma(Usuario.USUARIO_BATCH);
				faturamentoSituacaoHistorico.setObservacaoInforma(observacao);
				faturamentoSituacaoHistorico.setAnoMesFaturamentoSituacaoInicio(referenciaFaturamento);
				faturamentoSituacaoHistorico.setAnoMesFaturamentoSituacaoFim(anoMesFaturamentoSituacaoFim);
				faturamentoSituacaoHistorico.setUltimaAlteracao(new Date());
				
				this.getControladorUtil().inserir(faturamentoSituacaoHistorico);
				this.repositorioImovel.atualizarSituacaoEspecialFaturamentoImovel(idImovel, 
						faturamentoSituacaoTipo.getId(), faturamentoSituacaoMotivo.getId());
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
			throw new EJBException(ex);
		}

	}

	/**
	 * [UC1218] Suspender Leitura para Imóvel com Consumo Real não Superior a 10m3
	 * 
	 * @author Vivianne Sousa
	 * @date 26/08/2011
	 */
	public void suspenderLeituraParaImovelComConsumoRealNaoSuperiorA10(Integer idFuncionalidadeIniciada,
			Integer referenciaFaturamento,Integer grupofaturamento,Integer idRota) throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.ROTA, idRota);
			
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			Integer qtdConsumoRealNaoSuperiorA10 = sistemaParametro.getNumeroVezesSuspendeLeitura();
			Integer numeroMesesReinicioSitEspFaturamento = sistemaParametro.getNumeroMesesReinicioSitEspFaturamento();
			
			//ANO_MES_FINAL = referenciaFaturamento + parm_mesesleiturasuspensa (da tabela sistema_parametros)
			Integer anoMesFaturamentoSituacaoFim = Util.somaMesAnoMesReferencia(
				referenciaFaturamento,sistemaParametro.getNumeroMesesLeituraSuspensa());
			
			//FATURAMENTO_SITUACAO_MOTIVO
			FaturamentoSituacaoMotivo faturamentoSituacaoMotivo = new FaturamentoSituacaoMotivo();
			faturamentoSituacaoMotivo.setId(FaturamentoSituacaoMotivo.IMOVEL_COM_CONSUMO_MENOR_IGUAL_10M3_VARIOS_MESES);

			//FATURAMENTO_SITUACAO_TIPO
			FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
			faturamentoSituacaoTipo.setId(FaturamentoSituacaoTipo.PARALISAR_LEITURA_FATURAR_TAXA_MINIMA); 
			
			//OBSERVAÇÃO
			String observacao = "INCLUIDO ATRAVES DE ROTINA BATCH POR APRESENTAR CONSUMO NÃO SUPERIOR A 10M3";
			
			//[SB0001] – Selecionar Imóveis com Hidrômetro Retirado
			Collection idsImovel = this.repositorioFaturamento.
			pesquisarImovelComConsumoRealNaoSuperiorA10(			
					qtdConsumoRealNaoSuperiorA10, referenciaFaturamento,
					grupofaturamento,idRota, numeroMesesReinicioSitEspFaturamento);
			
			Iterator iterimoveis = idsImovel.iterator();
			while (iterimoveis.hasNext()) {
				//Para cada imóvel selecionado o sistema inclui o imóvel na situação especial de faturamento
				//[SB0004] – Incluir Imóvel na Situação Especial de Faturamento

				Integer idImovel = (Integer) iterimoveis.next();
				Imovel imovel = new Imovel();
				imovel.setId(idImovel);
				
				FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = new FaturamentoSituacaoHistorico();
				faturamentoSituacaoHistorico.setImovel(imovel);
				faturamentoSituacaoHistorico.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
				faturamentoSituacaoHistorico.setFaturamentoSituacaoMotivo(faturamentoSituacaoMotivo);
				faturamentoSituacaoHistorico.setUsuario(Usuario.USUARIO_BATCH);
				faturamentoSituacaoHistorico.setUsuarioInforma(Usuario.USUARIO_BATCH);
				faturamentoSituacaoHistorico.setObservacaoInforma(observacao);
				faturamentoSituacaoHistorico.setAnoMesFaturamentoSituacaoInicio(referenciaFaturamento);
				faturamentoSituacaoHistorico.setAnoMesFaturamentoSituacaoFim(anoMesFaturamentoSituacaoFim);
				faturamentoSituacaoHistorico.setUltimaAlteracao(new Date());
				
				this.getControladorUtil().inserir(faturamentoSituacaoHistorico);
				this.repositorioImovel.atualizarSituacaoEspecialFaturamentoImovel(idImovel, 
						faturamentoSituacaoTipo.getId(), faturamentoSituacaoMotivo.getId());
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
			throw new EJBException(ex);
		}

	}
	
	
	/**
	 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais
	 * [FS0002] – Validar Matrícula
	 * 
	 * Método que verifica se existe um contrato de demanda ativo ou suspenso para o imóvel
	 * passado no parâmetro.
	 * 
	 * @author Diogo Peixoto
	 * @since 20/09/2011
	 * 
	 * @param idImovel
	 * @return boolean - existe contrato de demanda
	 * @throws ControladorException
	 */
	public boolean existeContratoDemandaAtivoSuspenso(Integer idImovel) throws ControladorException{
		Boolean retorno;
		try{
			retorno = this.repositorioFaturamento.existeContratoDemandaAtivoSuspenso(idImovel);
		}catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}
	
	/**
	 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais
	 * [FS0002] – Validar Matrícula
	 * 
	 * Método que Verifica se o imóvel é exclusivamente da categoria residencial
	 * 
	 * @author Diogo Peixoto
	 * @since 20/09/2011
	 * 
	 * @param idImovel
	 * @return boolean - Exclusivo da categoria residencial
	 * @throws ControladorException
	 */
	public boolean imovelExclusivoCategoriaResidencial(Integer idImovel) throws ControladorException{
		Boolean retorno;
		try{
			retorno = this.repositorioFaturamento.imovelExclusivoCategoriaResidencial(idImovel);
		}catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}
	
	/**
	 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais
	 * [FS0000] – Verificar Número Contrato
	 * 
	 * Método que Verifica se já eixste um número de contrato passado no parâmetro.
	 * 
	 * @author Diogo Peixoto
	 * @since 21/09/2011
	 * 
	 * @param idImovel
	 * @return boolean - existe número contrato
	 * @throws ControladorException
	 */
	public boolean existeNumeroContratoDemandaCondominiosResidenciais(String numeroContrato) throws ControladorException{
		Boolean retorno;
		try{
			retorno = this.repositorioFaturamento.existeNumeroContratoDemandaCondominiosResidenciais(numeroContrato);
		}catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}
	
	/**
	 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais
	 * [SB0001] – Incluir Contrato de Demanda
	 * 
	 * Método que vai incluir um contrato de demanda de condomínios
	 * residenciais.
	 * 
	 * @param numeroContrato
	 * @param matriculaImovel
	 * @param dataInicio
	 * @param dataFim
	 * @param idCliente
	 * @param tipoRelacao
	 * @param demandaMinima
	 * @param usuario
	 * @return Id do contrato de demanda inserido.
	 * @throws ControladorException
	 */
	public Integer inserirContratoDemandaCondominiosResidenciais(String numeroContrato, String matriculaImovel, String dataInicio, String dataFim,
			String idCliente, String tipoRelacao, String demandaMinima, Usuario usuario) throws ControladorException{
		
		Integer id = null;
		
		this.validarInserirContratoImovelDemanda(numeroContrato, matriculaImovel, dataInicio, dataFim, idCliente, demandaMinima);
		
		//Insere o contrato de demanda situação ativo.
		FiltroContratoDemandaSituacao filtroContratoDemandaSituacao = new FiltroContratoDemandaSituacao();
		filtroContratoDemandaSituacao.adicionarParametro(new ParametroSimples(FiltroContratoDemandaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		List<ContratoDemandaSituacao> colecaoContratoDemandaSituacao = 
				(List<ContratoDemandaSituacao>) this.getControladorUtil().pesquisar(filtroContratoDemandaSituacao, ContratoDemandaSituacao.class.getName());
		ContratoDemandaSituacao contratoDemandaSituacao = null;
		if(!Util.isVazioOrNulo(colecaoContratoDemandaSituacao)){
			contratoDemandaSituacao = colecaoContratoDemandaSituacao.get(0);
		}else{
			throw new ControladorException("erro.sistema");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fimContrato;
		Date inicioContrato;
		try {
			inicioContrato = sdf.parse(dataInicio);
			fimContrato = sdf.parse(dataFim);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ControladorException(e.getMessage());
		}
		
		ContratoDemandaFaixaConsumo faixaConsumo;
		try {
			faixaConsumo = this.repositorioFaturamento.pesquisarFaixaConsumo(Integer.valueOf(demandaMinima));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new ControladorException(e.getMessage());
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException(e.getMessage());
		}
		
		ContratoDemandaImovel contratoDemandaImovel = new ContratoDemandaImovel(numeroContrato, new Imovel(Integer.valueOf(matriculaImovel)), 
			new Cliente(Integer.valueOf(idCliente)), Short.valueOf(tipoRelacao), inicioContrato, fimContrato, Integer.valueOf(demandaMinima), faixaConsumo, 
			usuario, new Date(), new Date(), contratoDemandaSituacao);
		
		id = (Integer) this.getControladorUtil().inserir(contratoDemandaImovel);
		return id;
	}
	
	/**
	 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais
	 * 
	 * Método que vai auxiliar nas validações dos campos na hora de inserir um contrato de demanda
	 * de condomínios residenciais.
	 * 
	 * @param numeroContrato
	 * @param matriculaImovel
	 * @param dataInicio
	 * @param dataFim
	 * @param idCliente
	 * @param demandaMinima
	 * @throws ControladorException
	 */
	private void validarInserirContratoImovelDemanda(String numeroContrato, String matriculaImovel, String dataInicio, String dataFim, String idCliente, String demandaMinima) 
			throws ControladorException{
		
		//[FS0000] – Verificar Número Contrato
		if(this.existeNumeroContratoDemandaCondominiosResidenciais(numeroContrato)){
			throw new ControladorException("atencao.contrato_demanda_numero_contrato");
		}
		
		//[FS0002] Validar Matrícula 
		this.validarImovelContratoDemandaCondominios(matriculaImovel);
		
		//[FS0003] Intervalo de Meses
		this.validarPeriodoContratoDemandaCondominios(dataInicio, dataFim);
		
		//[FS0006] – Validar Cliente
		this.validarClienteContratoDemandaCondominios(idCliente);
		
		//[FS0005] – Validar Demanda
		ContratoDemandaFaixaConsumo faixaConsumo = this.validarDemandaContratoDemandaCondominios(demandaMinima);
	}
	
	/**
	 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais / [UC1230] Atualizar Contrato de Demanda Condomínios Residenciais
	 * [FS0002] –Validar Matrícula / [FS0004]– Validar Matrícula 
	 * 
	 * @author Diogo Peixoto
	 * 
	 * @param imovel
	 * @return Retorna se o imóvel é valido para inserir um contrato de demanda.
	 * @throws ControladorException
	 */
	public boolean validarImovelContratoDemandaCondominios(String matriculaImovel) throws ControladorException{
		boolean valido = false;
		
		Imovel imovel = null;
		try {
			imovel = this.repositorioImovel.pesquisarImovel(Integer.valueOf(matriculaImovel));
			if(imovel == null){
				throw new ControladorException("atencao.contrato_demanda_imovel_nao_encontrado");
			}else{
				if(imovel.getIndicadorExclusao() == 1){
					throw new ControladorException("atencao.imovel_excluido");
				}else if(this.existeContratoDemandaAtivoSuspenso(imovel.getId())){
					throw new ControladorException("atencao.contrato_ativo_suspenso");
				}else if(imovel.getQuantidadeEconomias() < 20){
					throw new ControladorException("atencao.imovel_quantidade_economias");
				}else if(imovel.getLigacaoAguaSituacao().getId() != 3 && imovel.getLigacaoAguaSituacao().getId() != 5){
					throw new ControladorException("atencao.imovel_situacao_ligacao_agua");
				}else if(!this.imovelExclusivoCategoriaResidencial(Integer.valueOf(imovel.getId()))){
					throw new ControladorException("atencao.imovel_categoria_residencial");
				}else if(!this.getControladorMicromedicao().verificarSituacaoMedicao(Integer.valueOf(imovel.getId()))){
					throw new ControladorException("atencao.imovel_hidrometro_nao_instalado");
				}else if(imovel.getIndicadorImovelCondominio().intValue() == 1){
					throw new ControladorException("atencao.contrato_demanda_imovel_condominio");
				}else if(imovel.getImovelCondominio() != null){
					throw new ControladorException("atencao.contrato_demanda_imovel_vinculado_condominio");
				}else{
					valido = true;
				}
			}
		} catch (NumberFormatException e) {
			throw new ControladorException("atencao.contrato_demanda_matricula_invaliada");
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		} 
		return valido;
	}
	
	/**
	 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais / [UC1230] Atualizar Contrato de Demanda Condomínios Residenciais
	 * [FS0006] Validar Cliente / [FS0007] Validar Cliente
	 * 
	 * @author Diogo Peixoto
	 * @since 27/09/2011
	 * 
	 * @param idCliente
	 * @return
	 * @throws ControladorException
	 */
	public boolean validarClienteContratoDemandaCondominios(String idCliente) throws ControladorException{
		boolean valido = true;
		
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
		Collection<Cliente> colecaoCliente = this.getControladorUtil().pesquisar(filtroCliente, Cliente.class.getName());
		Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
		if(cliente == null){
			throw new ControladorException("atencao.contrato_demanda_cliente_inexistente");
		}else{
			if(cliente.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)){
				throw new ControladorException("atencao.contrato_demanda_cliente_inativo");
			}else if(cliente.getCpf() == null){
				throw new ControladorException("atencao.contrato_demanda_cliente_sem_cpf");
			}
		}
		return valido;
	}
	
	/**
	 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais / [UC1230] Atualizar Contrato de Demanda Condomínios Residenciais
	 * [FS0005] Validar Demanda / [FS0008] Validar Demanda
	 * 
	 * @author Diogo Peixoto
	 * @since 27/09/2011
	 * 
	 * @param demandaMinima
	 * @return
	 * @throws ControladorException
	 */
	private ContratoDemandaFaixaConsumo validarDemandaContratoDemandaCondominios(String demandaMinima) throws ControladorException{
		ContratoDemandaFaixaConsumo faixaConsumo = null;
		try {
			faixaConsumo = this.repositorioFaturamento.pesquisarFaixaConsumo(Integer.valueOf(demandaMinima));
			if(faixaConsumo == null){
				throw new ControladorException("atencao.contrato_demanda_demanda_menor_demada_minima", null, 
					this.repositorioFaturamento.pesquisarMenorConsumoInicialContratoDemandaCondominiosResidenciais().toString());
			}
		} catch (NumberFormatException e) {
			throw new ControladorException("atencao.contrato_demanda_demanda_invalida");
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return faixaConsumo;
	}
	
	/**
	 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais / [UC1230] Atualizar Contrato de Demanda Condomínios Residenciais
	 * [FS0003] Validar Demanda / [FS0005] Validar Período
	 * 
	 * @author Diogo Peixoto
	 * @since 27/09/2011
	 * 
	 * @param strInicio
	 * @param strFim
	 * @return
	 * @throws ControladorException
	 */
	private boolean validarPeriodoContratoDemandaCondominios(String strInicio, String strFim) throws ControladorException{
		boolean valido = true;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date inicioContrato = null;
		Date fimContrato = null;
		try {
			inicioContrato = sdf.parse(strInicio);
			fimContrato = sdf.parse(strFim);
			
			if(fimContrato.before(inicioContrato)){
				throw new ControladorException("atencao.contrato_demanda_data_menor");
			}else if(Util.dataDiff(inicioContrato, fimContrato) < 12){
				throw new ControladorException("atencao.contrato_demanda_diferenca_meses");
			}
		} catch (ParseException e) {
			throw new ControladorException("atencao.contrato_demanda_data_formato_invalido");
		}
		return valido;
	}
	
	/**
	 * [UC1229] - Manter Contrato de Demanda Condomínios Residenciais
	 *
	 * Metódo que recebe um helper e retorna as contratos de demanda condomínios
	 * residenciais que não foram encerrados (cdst_id != 3)
	 * 
	 * @author Diogo Peixoto
	 * @since 23/09/2011
	 * 
	 * @param helper
	 * @return List<ContratoDemandaCondominiosResidenciaisHelper>
	 * @throws ControladorException
	 */
	public List<ContratoDemandaCondominiosResidenciaisHelper> pesquisarContratoDemandaCondominiosResidenciais(FiltrarContratoDemandaCondominiosResidenciaisHelper helper) 
			throws ControladorException{
		
		List<ContratoDemandaCondominiosResidenciaisHelper> contratos = new ArrayList<ContratoDemandaCondominiosResidenciaisHelper>();
		
		try {
			if(helper.getDataInicioContrato() != null && helper.getDataInicioContrato2() != null){
				if(helper.getDataInicioContrato2().before(helper.getDataInicioContrato())){
					throw new ControladorException("atencao.contrato_demanda_periodo_menor");
				}
			}
			
			if(helper.getDataFimContrato() != null && helper.getDataFimContrato2() != null){
				if(helper.getDataFimContrato2().before(helper.getDataFimContrato())){
					throw new ControladorException("atencao.contrato_demanda_periodo_menor");
				}
			}
			
			List<Object[]> resultset = this.repositorioFaturamento.pesquisarContratoDemandaCondominiosResidenciais(helper);
			if(!Util.isVazioOrNulo(resultset)){
				ContratoDemandaCondominiosResidenciaisHelper contratoHelper = null;
				String numeroContrato;
				Integer idImovel;
				Date dataInicio;
				Date dataFim;
				String nomeCliente;
				Integer desconto;
				String situacao;
				
				for (Object[] objects : resultset) {
					
					numeroContrato = objects[0] != null ? (String) objects[0] : "";
					idImovel = objects[1] != null ? (Integer) objects[1] : null;
					dataInicio = objects[2] != null ? (Date) objects[2] : null;
					dataFim = objects[3] != null ? (Date) objects[3] : null;
					nomeCliente = objects[4] != null ? (String) objects[4] : "";
					desconto = objects[5] != null ? (Integer) objects[5] : null;
					situacao = objects[6] != null ? (String) objects[6] : "";
					
					contratos.add(new ContratoDemandaCondominiosResidenciaisHelper(numeroContrato, idImovel, dataInicio, dataFim, nomeCliente, desconto, situacao));
				}
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException(e.getMessage());
		}
		return contratos;
	}
	
	/**
	 * [UC1230] Atualizar Contrato de Demanda Condomínios Residenciais
	 * 
	 * Método que recebe o número do contrato do imóvel e retorna
	 * o contrato.
	 * 
	 * @author Diogo Peixoto
	 * @since 26/09/2011
	 * 
	 * @param numeroContrato
	 * @return ContratoDemandaImovel
	 * @throws ControladorException
	 */
	public ContratoDemandaImovel pesquisarContratoDemandaImovel(String numeroContrato) throws ControladorException{
		ContratoDemandaImovel contratoDemandaImovel = null; 
		try{
			contratoDemandaImovel = this.repositorioFaturamento.pesquisarContratoDemandaImovel(numeroContrato);
		}catch (ErroRepositorioException e) {
			e.printStackTrace();
			new ControladorException(e.getMessage());
		}
		return contratoDemandaImovel;
	}
	
	/**
	 * [UC1230] Atualizar Contrato de Demanda Condomínios Residenciais
	 * 
	 * @author Diogo Peixoto
	 * @since 26/09/2011
	 * 
	 * @return List<ContratoDemandaMotivoEncerramento>
	 * @throws ControladorException
	 */
	public List<ContratoDemandaMotivoEncerramento> pesquisarContratoDemandaMotivosEncerramentoAtivo() throws ControladorException{
		List<ContratoDemandaMotivoEncerramento> motivos = null;
		try{
			motivos = this.repositorioFaturamento.pesquisarContratoDemandaMotivosEncerramentoAtivo();
		}catch (ErroRepositorioException e) {
			e.printStackTrace();
			new ControladorException(e.getMessage());
		}
		return motivos;
	}
	
	/**
	 * [UC1230] Atualizar Contrato de Demanda Condomínios Residenciais
	 * 
	 * @author Diogo Peixoto
	 * @since 27/09/2011
	 * 
	 * @param numeroContrato
	 * @param matriculaImovel
	 * @param strDataInicio
	 * @param strDataFim
	 * @param idCliente
	 * @param tipoRelacao
	 * @param demandaMinima
	 * @param percentualDesconto
	 * @param situacaoContrato
	 * @param idMotivoEncerramento
	 * @param observacaoSuspensao
	 * @param observacaoEncerramento
	 * @throws ControladorException
	 */
	public void atualizarContratoDemandaCondominiosResidenciais(ContratoDemandaImovel contrato, 
			String numeroContrato,  String matriculaImovel, String strDataInicio, String strDataFim, String idCliente, 
			String tipoRelacao, String demandaMinima, String percentualDesconto, String situacaoContrato, String idMotivoEncerramento, 
			String observacaoSuspensao, String observacaoEncerramento, String strDataEncerramento, Usuario usuario) throws ControladorException{
		
		//Caso o número do contrato tenha sido modificado. Validar número.
		if(!contrato.getNumeroContrato().equals(numeroContrato)){
			//[FS0002] – Verificar Número Contrato
			if(this.existeNumeroContratoDemandaCondominiosResidenciais(numeroContrato)){
				throw new ControladorException("atencao.contrato_demanda_numero_contrato");
			}else{
				contrato.setNumeroContrato(numeroContrato.toUpperCase());
			}
		}
		
		//[FS0004] Validar Matrícula
		if(!contrato.getImovel().getId().toString().equals(matriculaImovel)){
			this.validarImovelContratoDemandaCondominios(matriculaImovel);
			Imovel imovel = new Imovel(Integer.valueOf(matriculaImovel));
			contrato.setImovel(imovel);
		}
		
		//[FS0005] Validar Período
		this.validarPeriodoContratoDemandaCondominios(strDataInicio, strDataFim);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataInicio = null;
		Date dataFim = null;
		Date dataEncerramento = null;
		try {
			dataInicio = sdf.parse(strDataInicio);
			dataFim = sdf.parse(strDataFim);
			contrato.setDataInicioContrato(dataInicio);
			contrato.setDataFimContrato(dataFim);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ControladorException("atencao.contrato_demanda_data_formato_invalido");
		}
		
		//[FS0007] Validar Cliente
		if(!contrato.getCliente().getId().toString().equals(idCliente)){
			this.validarClienteContratoDemandaCondominios(idCliente);
			Cliente cliente = new Cliente(idCliente);
			contrato.setCliente(cliente);
		}
		
		//[FS0008] Validar Demanda
		ContratoDemandaFaixaConsumo faixaConsumo = this.validarDemandaContratoDemandaCondominios(demandaMinima);
		contrato.setDemandaMinimaContratada(Integer.valueOf(demandaMinima));
		contrato.setFaixaConsumo(faixaConsumo);
		
		//[FS0009] Validar Dados de Encerramento
		if(situacaoContrato.equals(ConstantesSistema.CONTRATO_DEMANDA_IMOVEL_ENCERRADO)){
			try {
				dataEncerramento = sdf.parse(strDataEncerramento);
				this.validarDataEncerramentoContratoDemandaCondominios(dataInicio, dataFim, dataEncerramento, Integer.valueOf(idMotivoEncerramento));
				contrato.setDataEncerramentoContrato(dataEncerramento);
				
				ContratoDemandaMotivoEncerramento motivoEncerramento = 
						new ContratoDemandaMotivoEncerramento(Integer.valueOf(idMotivoEncerramento));
				
				contrato.setMotivoEcenrramento(motivoEncerramento);
				contrato.setDescricaoObservacaoEncerramento(observacaoEncerramento);
				contrato.setUsuarioEncerrouContrato(usuario);
				
			} catch (ParseException e) {
				e.printStackTrace();
				throw new ControladorException("atencao.contrato_demanda_data_formato_invalido");
			}
		}else if(situacaoContrato.equals(ConstantesSistema.CONTRATO_DEMANDA_IMOVEL_SUSPENSO)){
			contrato.setDataSuspensaoContrato(new Date());
			contrato.setDescricaoObservacaoSuspensao(observacaoSuspensao);
		}
		
		contrato.setRelacaoCliente(Short.valueOf(tipoRelacao));
		contrato.setSituacao(new ContratoDemandaSituacao(Integer.valueOf(situacaoContrato)));
		contrato.setUltimaAlteracao(new Date());
		
		this.getControladorUtil().atualizar(contrato);
	}
	
	/**
	 * [UC1230] Atualizar Contrato de Demanda Condomínios Residenciais
	 * [FS0009] Validar Data Encerramento
	 * 
	 * @author Diogo Peixoto
	 * @since 27/09/2011
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @param dataEncerramento
	 * @param idMotivoEncerramento
	 * @return
	 * @throws ControladorException
	 */
	private boolean validarDataEncerramentoContratoDemandaCondominios(Date dataInicio, Date dataFim, Date dataEncerramento, Integer idMotivoEncerramento) 
			throws ControladorException{
		
		boolean valido = true;
		
		if(idMotivoEncerramento == ConstantesSistema.NUMERO_NAO_INFORMADO){
			throw new ControladorException("atencao.contrato_demanda_motivo_encerramento");
		}
		
		if(dataEncerramento.before(dataInicio)){
			throw new ControladorException("atencao.contrato_demanda_data_encerramento_menor");
		}
		
		if(dataEncerramento.after(dataFim)){
			throw new ControladorException("atencao.contrato_demanda_data_encerramento_maior");
		}
		return valido;
	}
	
	/**
	 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais
	 *
	 * Metódo que recebe uma demanda informada e retorna qual a faixa de consumo
	 * que a demanda informada pertence.
	 * 
	 * @author Diogo Peixoto
	 * @since 21/09/2011
	 * 
	 * @param Integer - Demanda informada
	 * @return Integer - Menor consumo inicial
	 * @throws ControladorException
	 */
	public ContratoDemandaFaixaConsumo pesquisarFaixaConsumo(Integer demanda) throws ControladorException{
		ContratoDemandaFaixaConsumo consumo = null;
		try{
			consumo = this.repositorioFaturamento.pesquisarFaixaConsumo(demanda);
		}catch (ErroRepositorioException e) {
			e.printStackTrace();
			new ControladorException(e.getMessage());
		}
		return consumo;
	}
	
	/**
	 * [UC0119] Efetuar Análise das Exceções de Leituras e Consumos
	 * 
	 * @author Gustavo Amaral
	 * 
	 * @param imovelAtual
	 * @param imovelFiltro
	 * @param codigoImovel
	 * @param mesAnoPesquisa
	 * @param idMedicaoTipo
	 * @return
	 * @throws ControladorException
	 */
	public boolean validarAnaliseExcecoesLeiturasConsumos(Imovel imovelAtual, Imovel imovelFiltro, Integer codigoImovel, Integer mesAnoPesquisa, Integer idMedicaoTipo ) throws ControladorException {
		boolean desabilitaAtualizarImovel = true;
		
		FiltroMovimentoContaPrefaturada filtroMovimentoContaPrefaturada = new FiltroMovimentoContaPrefaturada();
		filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(
				FiltroMovimentoContaPrefaturada.MATRICULA, codigoImovel));
		filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(
				FiltroMovimentoContaPrefaturada.ID_MEDICAO_TIPO, idMedicaoTipo));
		filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(
				FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO, mesAnoPesquisa));
		
		
		Collection movimentoContaPrefaturadaEncontrada = getControladorUtil().pesquisar(
				filtroMovimentoContaPrefaturada, MovimentoContaPrefaturada.class.getName());
		
		//Pesquisa perfil do imovel para liberar acesso a alterar leitura anterior e atual
		ImovelPerfil imovelPerfil = getControladorImovel().recuperaPerfilImovel(new Integer(codigoImovel));
		
		
		boolean existeContaPreFaturada = false;
		Integer idDebitoCreditoSituacaoAtualDaConta = this.pesquisarDebitoCreditoSituacaoAtualDaConta(
			new Integer(codigoImovel), mesAnoPesquisa);
		if(idDebitoCreditoSituacaoAtualDaConta != null){
			existeContaPreFaturada = true;
		}
		
		
		if((imovelAtual.getQuadra().getRota().getLeituraTipo().getId().intValue() == LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.intValue()||
				imovelAtual.getQuadra().getRota().getLeituraTipo().getId().intValue() == LeituraTipo.CELULAR_MOBILE_ANDROID.intValue()||
				imovelAtual.getQuadra().getRota().getLeituraTipo().getId().intValue() == LeituraTipo.LEITURA_ANDROID.intValue())&& 
				imovelPerfil.getIndicadorGerarDadosLeitura().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO.intValue()){

			//Caso o tipo de leitura da rota do imóvel seja igual a LEIT.E ENTR.SIMULTAN 
			//(LTTP_ID = 3 da tabela ROTA), então desabilita os campos de atualização da tela. 
			desabilitaAtualizarImovel = true;
		}
		
		if(existeContaPreFaturada){
			//Caso a conta esteja como pré-faturada (DCST_IDATUAL=9) habilitar os campos para alteração da conta
			desabilitaAtualizarImovel = false;
		
		}
		
		if(!Util.isVazioOrNulo(movimentoContaPrefaturadaEncontrada)){
			//Caso o imóvel não esteja em MOVIMENTO_CONTA_PREFATURADA para o ano/mês de referencia
			//ou MCPF_ICEMISSAOCONTA esteja como 2, habilitar campos para alteração
			
			MovimentoContaPrefaturada mcpf = (MovimentoContaPrefaturada) movimentoContaPrefaturadaEncontrada.iterator().next();
			if(mcpf.getIndicadorEmissaoConta().compareTo(ConstantesSistema.NAO) == 0){
				desabilitaAtualizarImovel = false;
			}
		}else{
			desabilitaAtualizarImovel = false;
		}
		
		
		if(imovelFiltro != null && ( imovelFiltro.getIndicadorImovelCondominio().compareTo(ConstantesSistema.SIM) == 0 
				&&  imovelFiltro.getImovelCondominio() == null)){
			//Caso o imóvel seja condomínio (IMOV_ICIMOVELCONDOMINIO possua valor 1 
			//e IMOV_IDIMOVELCONDOMINIO seja igual NULL), não permitir alterar
			desabilitaAtualizarImovel = true;
		}
		
		//Caso o imovel possua conta com situação diferente de pre-faturada para referencia informada
		//não sera possivel atualizar a medição(desabilita o botao Atualizar). 
		boolean imovelPossuiContaDiferentePreFaturada = this.pesquisarContaDoImovelDiferentePreFaturada(
			new Integer(codigoImovel), mesAnoPesquisa);
		
		//imovelPossuiContaDiferentePreFaturada = true é por que o imovel possui conta diferente de prefaturada.
		if ( imovelPossuiContaDiferentePreFaturada ) {
			desabilitaAtualizarImovel = true;
		}
		return desabilitaAtualizarImovel;
	}
	
	/**
	 * [UC0082] - Registrar Leituras e Anormalidades Autor: Sávio Luiz Data:
	 * 18/01/2006
	 */
	// autor : Sávio Luiz atualiza a data de realização e a data de ultima
	// alteração do faturamento atividade cronograma
	public void atualizarFaturamentoAtividadeCronograma(
			Integer idFaturamentoAtividadeCronograma, Date dataRealizada) throws ControladorException{
		try{
			 this.repositorioFaturamento.atualizarFaturamentoAtividadeCronograma(idFaturamentoAtividadeCronograma, dataRealizada);
		}catch (ErroRepositorioException e) {
			e.printStackTrace();
			new ControladorException(e.getMessage());
		}
	}
	
	/**
	 * [UC0348] Emitir Contas
	 * 
	 * @author Raphael Rossiter
	 * 
	 * @param idConta
	 * @return Cliente
	 * @throws ControladorException
	 */
	public Cliente obterClienteImpressaoConta(Integer idConta) throws ControladorException {
		
		Cliente cliente = null;
		
		try{
			
			cliente = this.repositorioFaturamento.obterClienteConta(idConta);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
		
		return cliente;
	}
	
	/**
	 * 
	 * @author Breno Santos
	 * @date 27/10/2011
	 * 
	 * @param colErrors
	 */
	public void validarVinculoImovelCondomionio(Integer idImovel, String anoMesFaturamento, Integer idRota)
			throws ControladorException, MobileComunicationException {
		
		Integer anoMes = null;
		String matriculaImovel = "";
		
		if(anoMesFaturamento != null){
			anoMes = Integer.parseInt(anoMesFaturamento);
		}
		
		//Validacao para vetar vinculo de imovel durante o ciclo de faturamento
        Object[] dadosArquivoTextoRoteiroEmpresa = null;
        
        try {
			dadosArquivoTextoRoteiroEmpresa = repositorioFaturamento
					.pesquisarArquivoTextoRoteiroEmpresa(idRota, anoMes);
		} catch (ErroRepositorioException e) {
			 throw new ControladorException("erro.sistema",
                 e);
		}
        
		if (dadosArquivoTextoRoteiroEmpresa != null) {
			Integer idSituacaoTransmissaoLeitura = (Integer) dadosArquivoTextoRoteiroEmpresa[1];

			if (idSituacaoTransmissaoLeitura
					.equals(SituacaoTransmissaoLeitura.DISPONIVEL)
					|| idSituacaoTransmissaoLeitura
							.equals(SituacaoTransmissaoLeitura.LIBERADO)
					|| idSituacaoTransmissaoLeitura
							.equals(SituacaoTransmissaoLeitura.EM_CAMPO)) {
				
				matriculaImovel = "" + idImovel;
				throw new ControladorException(
						"atencao.imovel_em_faturamento",
						null);
				
			}
		}
	}
	
	
	
	/**
	 * [UC1236] Gerar Crédito a Realizar Contrato de Demanda Imóvel Residencial
	 * 
	 * @author Rafael Pinto
	 * @date 06/10/2011
	 */
	public void gerarCreditoARealizarContratoDemandaImovelResidencial(Integer idFuncionalidadeIniciada,
		Integer referenciaFaturamento,Integer grupofaturamento,Integer idRota) throws ControladorException {
		
		int idUnidadeIniciada = 0;

		try {
			idUnidadeIniciada = 
					getControladorBatch().iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.ROTA, idRota);
			
			System.out.println("ROTA :"+idRota);
			
			
			// [SB0001] - Selecionar Contratos
			// O sistema pesquisa os contratos de demanda ativos e vigentes no cadastro de contratos
			Collection<Imovel> colecaoImoveis = this.repositorioFaturamento.
				pesquisarImoveisResidenciasContratosDemandasAtivosVigentes(idRota);
			
			if(colecaoImoveis != null && !colecaoImoveis.isEmpty()){
				Iterator iteraImoveis = colecaoImoveis.iterator();
				
				FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
				
				filtroCreditoTipo.adicionarParametro(
					new ParametroSimples(FiltroCreditoTipo.CODIGO_CONSTANTE, 12));
				
				filtroCreditoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");

				Collection colecaoCreditoTipo = getControladorUtil().pesquisar(
					filtroCreditoTipo, CreditoTipo.class.getName());
				
				CreditoTipo creditoTipo = (CreditoTipo) Util.retonarObjetoDeColecao(colecaoCreditoTipo);

				while (iteraImoveis.hasNext()) {
					
					Imovel imovel = (Imovel) iteraImoveis.next();
				
					System.out.println("IMOVEL :"+imovel.getId());
					// [SB0002] - Gerar Credito a Realizar
					
					// Inclusão do CreditoARealizarGeral
					CreditoARealizarGeral creditoARealizarGeral = new CreditoARealizarGeral();
					creditoARealizarGeral.setIndicadorHistorico(ConstantesSistema.INDICADOR_USO_DESATIVO);
					creditoARealizarGeral.setUltimaAlteracao(new Date());

					Integer idCreditoARealizarGeral = (Integer) repositorioUtil.inserir(creditoARealizarGeral);

					creditoARealizarGeral.setId(idCreditoARealizarGeral);

					// Inclusao do CreditoARealizar
					CreditoARealizar creditoARealizar = new CreditoARealizar();

					creditoARealizar.setId(idCreditoARealizarGeral);
					creditoARealizar.setImovel(imovel);
					creditoARealizar.setCreditoTipo(creditoTipo);
					creditoARealizar.setGeracaoCredito(new Date());
					creditoARealizar.setAnoMesReferenciaCredito(referenciaFaturamento);
					creditoARealizar.setAnoMesCobrancaCredito(referenciaFaturamento);
					creditoARealizar.setAnoMesReferenciaContabil(referenciaFaturamento);
					creditoARealizar.setValorCredito(BigDecimal.ZERO);
					creditoARealizar.setValorResidualMesAnterior(BigDecimal.ZERO);
					creditoARealizar.setNumeroPrestacaoCredito(new Short("1"));
					creditoARealizar.setNumeroPrestacaoRealizada(new Short("0"));
					creditoARealizar.setLocalidade(imovel.getLocalidade());
					creditoARealizar.setQuadra(imovel.getQuadra());
					creditoARealizar.setCodigoSetorComercial(imovel.getSetorComercial().getCodigo());
					creditoARealizar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
					creditoARealizar.setNumeroLote(imovel.getLote());
					creditoARealizar.setNumeroSubLote(imovel.getSubLote());
					creditoARealizar.setRegistroAtendimento(null);
					creditoARealizar.setOrdemServico(null);
					creditoARealizar.setLancamentoItemContabil(creditoTipo.getLancamentoItemContabil());
					creditoARealizar.setDebitoCreditoSituacaoAtual(new DebitoCreditoSituacao(DebitoCreditoSituacao.NORMAL));
					creditoARealizar.setDebitoCreditoSituacaoAnterior(null);
					creditoARealizar.setCreditoARealizarGeral(creditoARealizarGeral);
					creditoARealizar.setCreditoOrigem(new CreditoOrigem(CreditoOrigem.VALORES_COBRADOS_INDEVIDAMENTE));
					creditoARealizar.setUltimaAlteracao(new Date());
					creditoARealizar.setUsuario(Usuario.USUARIO_BATCH);

					repositorioUtil.inserir(creditoARealizar);

					// Inclusao do CreditoARealizarCategoria
					CreditoARealizarCategoria creditoARealizarCategoria = new CreditoARealizarCategoria();
					
					creditoARealizarCategoria.setComp_id(
						new CreditoARealizarCategoriaPK(creditoARealizar.getId(),Categoria.RESIDENCIAL));
					creditoARealizarCategoria.setCreditoARealizar(creditoARealizar);
					
					Integer qtdEconomias = null;
					if(imovel.getQuantidadeEconomias() != null){
						qtdEconomias = imovel.getQuantidadeEconomias().intValue();
					}
					creditoARealizarCategoria.setQuantidadeEconomia(qtdEconomias);
					creditoARealizarCategoria.setValorCategoria(BigDecimal.ZERO);
					creditoARealizarCategoria.setUltimaAlteracao(new Date());

					repositorioUtil.inserir(creditoARealizarCategoria);
				}
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
			throw new EJBException(ex);
		}

	}
	
	
	/**
	 * [UC1235] Pesquisar Contrato Demanda
	 *
	 * Metódo que recebe uma matricula de imóvel e retorna o 
	 * consumo mínimo e o percentual de desconto.
	 * 
	 * @author Erivan Sousa
	 * @since 06/10/2011
	 * 
	 * @param String - Matrícula
	 * @return Collection - Consumo mínimo e Percentual de desconto
	 */
	public Collection pesquisarContratoDemanda(String matricula) throws ControladorException{
		Collection dados = null;
		try{
			 dados = this.repositorioFaturamento.pesquisarContratoDemanda(matricula);
		}catch (ErroRepositorioException e) {
			e.printStackTrace();
			new ControladorException(e.getMessage());
		}
		return dados;
	}
	
	/**
	 * Metodo responsavel por pesquisar as informacoes do Grupo faturado
	 * 
	 * @author Arthur Carvalho
	 * @param idGrupoFaturamento
	 * @param anoMes
	 * @return
	 * @throws ControladorException
	 */
	public  Object[] pesquisarInformacoesGrupoFaturado(String idGrupoFaturamento, Integer anoMes) throws ControladorException{
		Object[] dados = null;
		try{
			 dados = this.repositorioFaturamento.pesquisarInformacoesGrupoFaturado(idGrupoFaturamento, anoMes);
		}catch (ErroRepositorioException e) {
			e.printStackTrace();
			new ControladorException(e.getMessage());
		}
		return dados;
	}
	
	/**
	 * UC1242 - Cancelar Faturamento Grupo
	 * 
	 * @author Arthur Carvalho
	 * @date 18/10/2011
	 */
	public void cancelarGrupoFaturamento(Integer idFuncionalidadeIniciada, Integer idFaturamentoGrupo, Integer anoMesReferencia, Integer anoMesReferenciaGrupoMenosUmMes) throws ControladorException {
		
		int idUnidadeIniciada = 0;

		try {
			idUnidadeIniciada = 
					getControladorBatch().iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE, 0);
		
			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
			
			//CANCELAR FATURAMENTO
			//--------------------------------------------------------------------------------
			this.cancelarFaturarGrupo(idFaturamentoGrupo, anoMesReferencia, anoMesReferenciaGrupoMenosUmMes);
			
			//CANCELAR FATURAMENTO ANTECIPADO
			//--------------------------------------------------------------------------------
			if (Util.obterMes(anoMesReferencia) == ConstantesSistema.NOVEMBRO &&
					sistemaParametro.getIndicadorFaturamentoAntecipado().equals(ConstantesSistema.SIM)) {
				
					anoMesReferencia = Util.somaUmMesAnoMesReferencia(anoMesReferencia);
			
					this.cancelarFaturarGrupo(idFaturamentoGrupo, anoMesReferencia, anoMesReferenciaGrupoMenosUmMes);
					
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);

		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
			throw new EJBException(ex);
		}

	}
	
	/**
	 * [UC0091] Alterar Dados para Faturamento
	 * 
	 * @author Raphael Rossiter
	 * 
	 * @param idImovel
	 * @throws ControladorException
	 */
	public void validarAlterarDadosFaturamento(Integer idImovel, Integer anoMesReferencia) throws ControladorException {
		
		/*
		 * Verifica se já existe conta pré faturada para o imóvel solicitado para referência do faturamento, caso exista;
		 * não será permitido alterar os dados do faturamento.
		 */
		Integer idDebitoCreditoSituacaoAtualDaConta = this.pesquisarDebitoCreditoSituacaoAtualDaConta(
				idImovel, anoMesReferencia);
		
		if(idDebitoCreditoSituacaoAtualDaConta != null){
			
			/*
			 * Caso a situação do arquivo da iSC esteja finalizado, permitir que o usuário possa informar
			 * a leitura desejada.
			 */
			Integer arquivoNaoFinalizadoEncontrado = null;
			
			try {

				FiltroConta filtroConta = new FiltroConta();
				
				filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.ROTA);
				filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.IMOVEL_ID, idImovel));
				filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.REFERENCIA, anoMesReferencia));
				filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL_ID, DebitoCreditoSituacao.PRE_FATURADA));
				
				Collection colecaoContaPreFaturada = this.getControladorUtil().pesquisar(filtroConta, Conta.class.getName());
				Conta contaPreFaturada = (Conta) Util.retonarObjetoDeColecao(colecaoContaPreFaturada);
				
				arquivoNaoFinalizadoEncontrado = this.repositorioFaturamento
				.pesquisarArquivoTextoRoteiroEmpresaNaoFinalizado(contaPreFaturada, contaPreFaturada.getRota());

			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
			
			if (arquivoNaoFinalizadoEncontrado != null && arquivoNaoFinalizadoEncontrado.intValue() > 0){
				
				throw new ControladorException("atencao.imovel_com_conta_pre_faturada", null, idImovel.toString(), 
				Util.formatarAnoMesParaMesAno(anoMesReferencia));
			}
		}
	}
	/**
	 * [UC0153] Apresentar Dados Para Análise da Medição e Consumo
	 * @author Arthur Carvalho
	 * @date 10/11/2011
	 * 
	 * @param idImovel
	 * @param dataLeituraAtualFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] verificaImovelPossuiOSFiscalizacao(Integer idImovel, Date dataLeituraAtualFaturamento ) throws ControladorException {
		Object[] object = null;
		try {
			object = repositorioFaturamento.verificaImovelPossuiOSFiscalizacao(idImovel, dataLeituraAtualFaturamento);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		return object;
	}
	
	
	/**
	 * [UC0866] Gerar Comando Contas em Cobrança por Empresa
	 * 
	 * Pesquisa a quantidade de contas por imóvel
	 * 
	 * @author: Mariana Victor
	 * @date: 03/05/2011
	 */
	public Integer pesquisarQuantidadeContasMenorFaixa(
			Integer idEmpresa) throws ControladorException {
		Integer quantidade = null;
		try {
			quantidade = repositorioFaturamento.pesquisarQuantidadeContasMenorFaixa(idEmpresa);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		return quantidade;
	}
	/**
	 * Recupera o id do cliente usuário pela conta 
	 * 
	 * @author Vivianne Sousa
	 * @date 07/12/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdClienteUsuarioConta(Integer idConta)throws ControladorException {
		try{
		  return repositorioFaturamento.pesquisarIdClienteUsuarioConta(idConta);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	/**
	 * [UC1263] Relatório de Contas Não Impressas
	 * 
	 * Pesquisa o Total Registro
	 * 
	 * @author Thúlio Araújo 
	 * @date 22/12/2011
	 * 
	 * @param FiltrarRelatorioContasNaoImpressasHelper
	 * 
	 * @return Collection<RelatorioContasNaoImpressasHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioContasNaoImpressasHelper> pesquisarRelatorioContasNaoImpressas(
			FiltrarRelatorioContasNaoImpressasHelper filtro)
			throws ControladorException {

		Collection<RelatorioContasNaoImpressasHelper> retorno = new ArrayList<RelatorioContasNaoImpressasHelper>();

		Collection<Object[]> colecaoPesquisa = null;

		try {
			colecaoPesquisa = this.repositorioFaturamento
					.pesquisarRelatorioContasNaoImpressas(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			Iterator itera = colecaoPesquisa.iterator();

			while (itera.hasNext()) {
				Object[] objeto = (Object[]) itera.next();

				RelatorioContasNaoImpressasHelper helper = new RelatorioContasNaoImpressasHelper();

				helper.setMesAnoReferencia((Integer) objeto[0]);
				helper.setConta((Integer) objeto[1]);
				helper.setCliente((String) objeto[3]);
				helper.setAnormalidadeLeitura((String) objeto[9]);
				
				Integer idImovel = (Integer) objeto[2];
				Integer localidade = (Integer) objeto[4];
				Integer codigoSetorComercial = (Integer) objeto[5];
				Integer numeroQuadra = (Integer) objeto[6];
				Integer lote = (Integer) objeto[7];
				Integer subLote = (Integer) objeto[8];
				
				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				Localidade local = new Localidade();
				local.setId(localidade);
				imovel.setLocalidade(local);

				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setCodigo(codigoSetorComercial);
				imovel.setSetorComercial(setorComercial);

				Quadra quadra = new Quadra();
				quadra.setNumeroQuadra(numeroQuadra);
				imovel.setQuadra(quadra);

				imovel.setLote(lote.shortValue());
				imovel.setSubLote(subLote.shortValue());

				helper.setImovel(idImovel);
				helper.setInscricaoImovel(imovel.getInscricaoFormatada());

				String endereco = this.getControladorEndereco()
						.obterEnderecoAbreviadoImovel(idImovel);
				helper.setEndereco(endereco);
				
				retorno.add(helper);
			}
		}else{
			throw new ControladorException("atencao.filtro_contas_nao_impressas");
		}

		return retorno;
	}
	
    /**
	 * [UC0000] - XXX
	 *
	 * @author Raphael Rossiter
	 * @date 20/12/2011
	 *
	 * @param rota
	 * @param anoMesFaturamento
	 * @param idFaturamentoGrupo
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void paralisarFaturamento(Rota rota,
			Integer anoMesFaturamento, Integer idFaturamentoGrupo,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		// PROCESSO BATCH
		// ------------------------------------------------------------------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.ROTA, rota.getId());
		// ---------------------------------------------------------------------------------------------------

		try {

			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
			
			repositorioFaturamento.retirarSituacaoEspecialFaturamentoVencida(rota.getId(), anoMesFaturamento, false);
			
			//Dados do grupo de faturamento
			FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
			faturamentoGrupo.setId(idFaturamentoGrupo);
			faturamentoGrupo.setAnoMesReferencia(anoMesFaturamento);
			
			Usuario usuarioBatch = this.getControladorUsuario().pesquisarUsuarioRotinaBatch();
			
			//Obtendo a data prevista da atividade efetuar leitura para o grupo de faturamento
			Date dataPrevista = this.pesquisarFaturamentoAtividadeCronogramaDataPrevista(
			faturamentoGrupo.getId(), FaturamentoAtividade.EFETUAR_LEITURA, faturamentoGrupo.getAnoMesReferencia());
			
			/*
			 * Caso os dados para uma possível geração de uma situação especial de faturamento não estejam cadastrados 
			 * em nossa tabela de parâmetros; nenhum imóvel será excluído do faturamento. 
			 */
			if (sistemaParametro.getFaturamentoSituacaoMotivoParalisacaoMensal() != null &&
				sistemaParametro.getFaturamentoSituacaoTipoParalisacaoEsgoto() != null &&
				sistemaParametro.getFaturamentoSituacaoTipoParalisacaoGeral() != null){
				
				//Variáveis para a paginação da pesquisa de Imovel por Grupo
				// Faturamento
				// ========================================================================
				boolean flagTerminou = false;
				final int quantidadeRegistros = 3000;
				int numeroIndice = 0;
				// ========================================================================

				while (!flagTerminou) {

					Collection colecaoImovel = this
							.pesquisarImovelGrupoFaturamento(rota, numeroIndice,
									quantidadeRegistros, true, false);

					/*
					 * Caso exista ids de imóveis para a rota atual determina se
					 * o mesmo fará parte do faturamento corrente
					 */
					if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

						Iterator iteratorColecaoImoveis = colecaoImovel.iterator();

						// LAÇO PARA DETERMINAR SE O IMÓVEL FARÁ PARTE DO FATURAMENTO DA
						// ROTA ATUAL
						Imovel imovel = null;
						while (iteratorColecaoImoveis.hasNext()) {

							imovel = (Imovel) iteratorColecaoImoveis.next();
							
							/*
							 * Caso o imóvel já esteja com alguma situação especial de faturamento informada OU os dados para uma possível 
							 * geração de uma situação especial de faturamento não estejam cadastrados em nossa tabela de parâmetros;
							 * passar para o próximo imóvel.
							 */
							if (imovel.getFaturamentoSituacaoTipo() == null){
								
								
								//Paralisar faturamento imóvel novo
								boolean faturamentoParalisado = this.paralisarFaturamentoImovelNovo(imovel, faturamentoGrupo, dataPrevista, 
										sistemaParametro, usuarioBatch);
								
								if (!faturamentoParalisado){
									
									/*
									 * Caso o imóvel seja um imóvel micro, e possua ligação de água e a situação da ligação
									 * de água esteja como CORTADO OU SUPRIMIDO.
									 * 
									 */
									if ( imovel.getImovelCondominio() != null &&
										 imovel.getLigacaoAguaSituacao() != null &&
										 imovel.getLigacaoEsgotoSituacao() != null &&
										 imovel.getLigacaoEsgotoSituacao().getIndicadorFaturamentoSituacao().equals(ConstantesSistema.SIM) &&
										 ( imovel.getLigacaoAguaSituacao().getId().equals( LigacaoAguaSituacao.CORTADO ) ||
										   imovel.getLigacaoAguaSituacao().getId().equals( LigacaoAguaSituacao.SUPRIMIDO ) ) ){
										
										// Colocamos como situação especial paralização de faturamento de esgoto
										
										FaturamentoSituacaoMotivo faturamentoSituacaoMotivo = new FaturamentoSituacaoMotivo();
										faturamentoSituacaoMotivo.setId(FaturamentoSituacaoMotivo.MEDICAO_INDIVIDUALIZADA);
										
										//GERANDO A SITUAÇÃO ESPECIAL DE FATURAMENTO
										this.inserirSituacaoEspecialFaturamentoParalisacaoMensal(
												imovel, 
												faturamentoGrupo, 
												faturamentoSituacaoMotivo,
												sistemaParametro.getFaturamentoSituacaoTipoParalisacaoEsgoto(),
												usuarioBatch);
									}
									
								}
							}
							
						}// FIM DO LOOP DE IMOVEIS

					}// FIM DO LOOP DE IMOVEIS

					/**
					 * Incrementa o n do indice da paginação
					 */
					numeroIndice = numeroIndice + quantidadeRegistros;

					/**
					 * Caso a coleção de imoveis retornados for menor que a
					 * quantidade de registros seta a flag indicando que a paginação
					 * terminou.
					 */
					if (colecaoImovel == null
							|| colecaoImovel.size() < quantidadeRegistros) {

						flagTerminou = true;
					}

					if (colecaoImovel != null) {
						colecaoImovel.clear();
						colecaoImovel = null;
					}

				}// FIM DO LOOP DA PAGINAÇÃO
			}
			
			
			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {

			/*
			 * Este catch serve para interceptar qualquer exceção que o processo
			 * batch venha a lançar e garantir que a unidade de processamento do
			 * batch será atualizada com o erro ocorrido.
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);
		}

	}
	
	
	/**
	 * [UC0000] - XXX
	 *
	 * @author Raphael Rossiter
	 * @date 22/12/2011
	 * 
	 * @param imovel
	 * @param faturamentoGrupo
	 * @param dataPrevistaCronogramaEfetuarLeitura
	 * @param sistemaParametro
	 * @param usuarioBatch
	 * @throws ControladorException
	 */
	public boolean paralisarFaturamentoImovelNovo(Imovel imovel, FaturamentoGrupo faturamentoGrupo, 
			Date dataPrevistaCronogramaEfetuarLeitura, SistemaParametro sistemaParametro,
			Usuario usuarioBatch) throws ControladorException {
		
		boolean faturamentoParalisado = false;
		
		//1 - Obtendo a principal categoria do imóvel
		Categoria categoriaPrincipal = this.getControladorImovel().obterPrincipalCategoriaImovel(imovel.getId());
		
		/*
		 * 2 - Verifica se a quantidade de dias para o primeiro faturamento da categoria principal do imóvel está superior à zero,
		 * nesse caso iremos prosseguir com a verificação do imóvel.
		 */
		if (categoriaPrincipal.getQuantidadeDiasPrimeiroFaturamento() != null &&
			categoriaPrincipal.getQuantidadeDiasPrimeiroFaturamento().intValue() > 0){
			
			
			//2.1 - Verificando se o imóvel possui ligação de água
			if (imovel.getLigacaoAgua() != null && imovel.getLigacaoAgua().getDataLigacao() != null){
				
				/*
				 * 2.1.1 - Sendo a quantidade de dias da data da ligação de água até a data da leitura prevista no cronograma superior a 
				 * quantidade de dias parametrizada, o imóvel estará liberado para o faturamento. Caso contrário será gerada uma situação
				 * especial de faturamento. 
				 */
				int numeroDiasDiferecaDatasLigacaoAgua = Util.obterQuantidadeDiasEntreDuasDatas(
				imovel.getLigacaoAgua().getDataLigacao(), dataPrevistaCronogramaEfetuarLeitura);
				
				if (numeroDiasDiferecaDatasLigacaoAgua <= categoriaPrincipal.getQuantidadeDiasPrimeiroFaturamento().intValue()){
					
					//GERANDO A SITUAÇÃO ESPECIAL DE FATURAMENTO
					this.inserirSituacaoEspecialFaturamentoParalisacaoMensal(imovel, faturamentoGrupo, 
							sistemaParametro.getFaturamentoSituacaoMotivoParalisacaoMensal(),
							sistemaParametro.getFaturamentoSituacaoTipoParalisacaoGeral(),
							usuarioBatch);
					
					faturamentoParalisado = true;
				}
			}
			
			/*
			 * 2.2 - Verifica se o imóvel já foi colocado em situação especial de faturamento; Caso o imóvel NÃO tenha sido colocado em situação
			 * especial de faturamento prosseguir com a análise da ligação de esgoto, caso contrário passar para o próximo imóvel.
			 */
			if (!faturamentoParalisado && imovel.getLigacaoEsgoto() != null && imovel.getLigacaoEsgoto().getDataLigacao() != null){
				
				/*
				 * 2.2.1 - Sendo a quantidade de dias da data da ligação de esgoto até a data da leitura prevista no cronograma superior a 
				 * quantidade de dias parametrizada, o imóvel estará liberado para o faturamento. Caso contrário será gerada uma situação
				 * especial de faturamento apenas para ESGOTO. 
				 */
				int numeroDiasDiferecaDatasLigacaoEsgoto = Util.obterQuantidadeDiasEntreDuasDatas(
				imovel.getLigacaoEsgoto().getDataLigacao(), dataPrevistaCronogramaEfetuarLeitura);
				
				if (numeroDiasDiferecaDatasLigacaoEsgoto <= categoriaPrincipal.getQuantidadeDiasPrimeiroFaturamento().intValue()){
					
					//GERANDO A SITUAÇÃO ESPECIAL DE FATURAMENTO
					this.inserirSituacaoEspecialFaturamentoParalisacaoMensal(imovel, faturamentoGrupo, 
							sistemaParametro.getFaturamentoSituacaoMotivoParalisacaoMensal(),
							sistemaParametro.getFaturamentoSituacaoTipoParalisacaoEsgoto(),
							usuarioBatch);
					
					faturamentoParalisado = true;
				}
			}
		}
		
		return faturamentoParalisado;
	}
	
	
	/**
	 * Inserir Situação Especial de Faturamento para paralisação mensal
	 * 
	 * @author Raphael Rossiter
	 * @date 22/12/2011
	 * 
	 * @param imovel
	 * @param faturamentoGrupo
	 * @param faturamentoSituacaoMotivo
	 * @param faturamentoSituacaoTipo
	 * @param usuarioBatch
	 * @throws ControladorException
	 */
	public void inserirSituacaoEspecialFaturamentoParalisacaoMensal(Imovel imovel, FaturamentoGrupo faturamentoGrupo,
			FaturamentoSituacaoMotivo faturamentoSituacaoMotivo, FaturamentoSituacaoTipo faturamentoSituacaoTipo,
			Usuario usuarioBatch) throws ControladorException{
		
		
		FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = new FaturamentoSituacaoHistorico();
			
		//IMOVEL
		faturamentoSituacaoHistorico.setImovel(imovel);
			
		//ANO_MES_INICIAL = ANO_MES_FATURAMENTO do grupo de faturamento
		faturamentoSituacaoHistorico.setAnoMesFaturamentoSituacaoInicio(faturamentoGrupo.getAnoMesReferencia());
			
		//ANO_MES_FINAL = ANO_MES_FATURAMENTO do grupo de faturamento
		faturamentoSituacaoHistorico.setAnoMesFaturamentoSituacaoFim(faturamentoGrupo.getAnoMesReferencia());

		//ANO_MES_FATURAMENTO_RETIRADA = NULL
		faturamentoSituacaoHistorico.setAnoMesFaturamentoRetirada(null);
			
		//FATURAMENTO_SITUACAO_MOTIVO
		faturamentoSituacaoHistorico.setFaturamentoSituacaoMotivo(faturamentoSituacaoMotivo);

		//FATURAMENTO_SITUACAO_TIPO
		faturamentoSituacaoHistorico.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
			
		//USUÁRIO
		faturamentoSituacaoHistorico.setUsuario(usuarioBatch);
			
		//USUÁRIO INFORMA
		faturamentoSituacaoHistorico.setUsuarioInforma(usuarioBatch);
			
		faturamentoSituacaoHistorico.setUltimaAlteracao(new Date());
			
		//INSERINDO
		this.getControladorUtil().inserir(faturamentoSituacaoHistorico);
			
		//ATUALIZANDO IMÓVEL
		this.atualizarImovelSituacaoEspecialFaturamento(imovel, faturamentoSituacaoTipo.getId(), faturamentoSituacaoMotivo.getId());
	}
	
	/**
	 * Inserir Situação Especial de Faturamento para paralisação mensal
	 * 
	 * @author Raphael Rossiter
	 * @date 22/12/2011
	 * 
	 * @param imovel
	 * @param idFaturamentoSituacaoTipo
	 * @param idFaturamentoSituacaoMotivo
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovelSituacaoEspecialFaturamento(Imovel imovel, Integer idFaturamentoSituacaoTipo, 
			Integer idFaturamentoSituacaoMotivo) throws ControladorException{
		
		try{
			 
			repositorioFaturamento.atualizarImovelSituacaoEspecialFaturamento(imovel, idFaturamentoSituacaoTipo,
					idFaturamentoSituacaoMotivo);
		}
		catch (ErroRepositorioException e) {
			e.printStackTrace();
			new ControladorException(e.getMessage());
		}
	}
	
	/**
	 * [UC0091] Alterar Dados para Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 29/12/2011
	 * 
	 * @param imovel
	 * @param anoMesFaturamentoGrupo
	 * @return
	 * @throws ControladorException
	 */
	public Integer verificarProjecaoLeituraAlterarDadosFaturamento(Imovel imovel, Integer anoMesFaturamentoGrupo) throws ControladorException {
		
		Integer anoMesFaturamento = anoMesFaturamentoGrupo;
		
		boolean existeContaFaturada = this.pesquisarContaDoImovelDiferentePreFaturada(imovel.getId(), anoMesFaturamentoGrupo);
		
		if (existeContaFaturada){
					
			anoMesFaturamento = Util.somaUmMesAnoMesReferencia(anoMesFaturamentoGrupo);	
		}
		
		return anoMesFaturamento;
	}
	
	/**
	 * 
	 * @author Rodrigo Cabral
	 * @date 16/02/2012
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsSetorComercial()
			throws ControladorException {
		try {
			return repositorioFaturamento
					.pesquisarIdsSetorComercial();
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}
	
	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Pesquisa a conta do imóvel com a referência informada pelo usuário
	 * 
	 * [FS0012] - Verificar existência da conta
	 * 
	 * @author Pedro Alexandre, Raphael Rossiter
	 * @date 16/02/2006, 05/03/2012
	 * 
	 * @param idImovel
	 * @param referenciaConta
	 * @return Conta
	 * @throws ControladorException
	 */
	public Conta pesquisarContaDigitadaInserirPagamentos(String idImovel, String referenciaConta)
			throws ControladorException {

		// Vari?vel que vai armazenar a conta pesquisada
		Conta contaDigitada = null;
		Object[] dadosConta = null;

		// Formata a refer?ncia da conta informada para o formato (AAAAMM) sem a
		// barra
		String anoMesConta = Util
				.formatarMesAnoParaAnoMesSemBarra(referenciaConta);

		// Cria o filtro de conta e seta todos os par?metros para pesquisar a
		// conta do im?vel
		// Pesquisa imovel
		try {
			dadosConta = repositorioFaturamento.pesquisarContaDigitadaInserirPagamentos(
					idImovel, anoMesConta);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		if (dadosConta != null) {

			contaDigitada = new Conta();

			// Id da Conta
			if (dadosConta[0] != null) {
				contaDigitada.setId((Integer) dadosConta[0]);
			}

			// Refer?ncia
			if (dadosConta[1] != null) {
				contaDigitada.setReferencia((Integer) dadosConta[1]);
			}

			// Valor da ?gua
			if (dadosConta[2] != null) {
				contaDigitada.setValorAgua((BigDecimal) dadosConta[2]);
			}

			// Valor de Esgoto
			if (dadosConta[3] != null) {
				contaDigitada.setValorEsgoto((BigDecimal) dadosConta[3]);
			}

			// D?bitos
			if (dadosConta[4] != null) {
				contaDigitada.setDebitos((BigDecimal) dadosConta[4]);
			}

			// Valor Cr?ditos
			if (dadosConta[5] != null) {
				contaDigitada.setValorCreditos((BigDecimal) dadosConta[5]);
			}

			// Valor Imposto
			if (dadosConta[6] != null) {
				contaDigitada.setValorImposto((BigDecimal) dadosConta[6]);
			}

			// Localidade
			if (dadosConta[7] != null) {
				Localidade localidade = new Localidade();
				localidade.setId((Integer) dadosConta[7]);
				contaDigitada.setLocalidade(localidade);
			}

			// D?bito Cr?dito Situa??o
			if (dadosConta[8] != null 
					&& dadosConta[9] != null) {
				DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
				debitoCreditoSituacao.setId((Integer) dadosConta[8]);
				debitoCreditoSituacao.setDescricaoAbreviada((String) dadosConta[9]);
				contaDigitada.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
			}

			// Valor Imposto
			if (dadosConta[10] != null) {
				contaDigitada.setDataVencimentoConta((Date) dadosConta[10]);
			}

		}

		// Retorna a conta encontrada ou nulo se n?o existir a conta
		return contaDigitada;
	}
	
	/**
	 * Pesquisar Conta Historico
	 * 
	 * @author Fernando Fontelles, Raphael Rossiter
	 * @date 06/08/2010, 05/03/2012
	 * 
	 * @param idImovel
	 * @param referenciaConta
	 * @return ContaHistorico
	 * @throws ControladorException
	 */
	public ContaHistorico pesquisarContaHistoricoDigitadaInserirPagamentos(String idImovel,
			String referenciaConta) throws ControladorException {

		// Variável que vai armazenar a conta pesquisada
		ContaHistorico contaDigitada = null;
		Object[] dadosConta = null;

		// Formata a referência da conta informada para o formato (AAAAMM) sem a
		// barra
		String anoMesConta = Util
				.formatarMesAnoParaAnoMesSemBarra(referenciaConta);

		// Cria o filtro de conta e seta todos os parâmetros para pesquisar a
		// conta do imóvel
		// Pesquisa imovel
		try {
			dadosConta = repositorioFaturamento
					.pesquisarContaHistoricoDigitadaInserirPagamentos(idImovel, anoMesConta);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		if (dadosConta != null) {

			contaDigitada = new ContaHistorico();

			// Id da Conta
			if (dadosConta[0] != null) {
				contaDigitada.setId((Integer) dadosConta[0]);
			}

			// Referência
			if (dadosConta[1] != null) {
				contaDigitada.setAnoMesReferenciaConta((Integer) dadosConta[1]);
			}

			// Valor da Água
			if (dadosConta[2] != null) {
				contaDigitada.setValorAgua((BigDecimal) dadosConta[2]);
			}

			// Valor de Esgoto
			if (dadosConta[3] != null) {
				contaDigitada.setValorEsgoto((BigDecimal) dadosConta[3]);
			}

			// Débitos
			if (dadosConta[4] != null) {
				contaDigitada.setValorDebitos((BigDecimal) dadosConta[4]);
			}

			// Valor Créditos
			if (dadosConta[5] != null) {
				contaDigitada.setValorCreditos((BigDecimal) dadosConta[5]);
			}

			// Valor Imposto
			if (dadosConta[6] != null) {
				contaDigitada.setValorImposto((BigDecimal) dadosConta[6]);
			}

			// Data Vencimento
			if (dadosConta[7] != null) {
				contaDigitada.setDataVencimentoConta((Date) dadosConta[7]);
			}

			// Débito Crédito Situação
			if (dadosConta[8] != null && dadosConta[9] != null) {
				DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
				debitoCreditoSituacao.setId((Integer) dadosConta[8]);
				debitoCreditoSituacao.setDescricaoAbreviada((String) dadosConta[9]);
				contaDigitada.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
			}

			// Localidade
			if (dadosConta[10] != null) {
				Localidade localidade = new Localidade();
				localidade.setId((Integer) dadosConta[10]);
				contaDigitada.setLocalidade(localidade);
			}

		}

		// Retorna a conta encontrada ou nulo se não existir a conta
		return contaDigitada;
	}
	
	/**
	 * [UC0146] Manter Conta
	 * 
	 * [SB0008] Retificar Conjunto Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 12/03/2012
	 * 
	 * @param idConta
	 * @param valoresConta
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean verificarNaoRetificacaoConta(Integer idConta, Collection<CalcularValoresAguaEsgotoHelper> valoresConta) 
		throws ControladorException {
		
		boolean retorno = true;
		
		Conta contaParaRetificacao =  this.pesquisarContaRetificacao(idConta);
		
		//Valor total de água
		BigDecimal valorTotalAgua = this
				.calcularValorTotalAguaOuEsgotoPorCategoria(
						valoresConta,
						ConstantesSistema.CALCULAR_AGUA);

		contaParaRetificacao.setValorAgua(valorTotalAgua);

		// Valor total de esgoto
		BigDecimal valorTotalEsgoto = this
				.calcularValorTotalAguaOuEsgotoPorCategoria(
						valoresConta,
						ConstantesSistema.CALCULAR_ESGOTO);
		
		contaParaRetificacao.setValorEsgoto(valorTotalEsgoto);
		
		if (contaParaRetificacao.getValorTotal().compareTo(ConstantesSistema.VALOR_ZERO) < 0){
			retorno = false;
		}
		
		return retorno;
	}
	
	/**
	 * [UC0000] - Retificar Conjunto de Conta a partir do [UC0146] Manter Conta
	 *
	 * @author Raphael Rossiter
	 * @date 13/03/2012
	 * 
	 * @param colecaoContas
	 * @param identificadores
	 * @param usuarioLogado
	 * @return Collection
	 * @throws ControladorException
	 */
	public void verificarContaComCredito(Collection<Conta> colecaoContas,
			String identificadores, Usuario usuarioLogado) throws ControladorException {

		UC0146ManterConta manterConta = 
		UC0146ManterConta.getInstancia(repositorioFaturamento, sessionContext);
		
		SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
		
		Collection colecaoContaSelecionadas = manterConta.gerarColecaoContaSelecaoParaRetificacao(colecaoContas, identificadores,
		sistemaParametro, usuarioLogado);
		
		String complementoMensagem = "";
		
		if (colecaoContaSelecionadas != null && !colecaoContaSelecionadas.isEmpty()) {

			Iterator colecaoContasIterator = colecaoContaSelecionadas.iterator();

			while (colecaoContasIterator.hasNext()) {
				
				Conta conta = (Conta) colecaoContasIterator.next();
				
				if (conta.getValorCreditos() != null && conta.getValorCreditos().compareTo(ConstantesSistema.VALOR_ZERO) > 0){
					
					if (complementoMensagem.length() > 0){
						
						complementoMensagem = complementoMensagem + ", " + Util.formatarAnoMesParaMesAno(conta.getReferencia());
					}
					else{
						
						complementoMensagem = Util.formatarAnoMesParaMesAno(conta.getReferencia()); 
					}
				}
			}
			
			if (complementoMensagem.length() > 0){
				
				throw new ControladorException("atencao.conta_possui_credito", null, complementoMensagem);
			}
		}
	}
	
	/**
	 * [UC0210] Consultar Débito a Cobrar
	 * [RM1165] Exibir demonstrativo da fórmula de cálculo
	 *  
	 * @author Amelia Pessoa  
	 * @date 17/05/2012
	 * 
	 * @param codCalculoConsumo
	 * @return DemonstrativoFormulaCalculoHelper
	 */
	public DemonstrativoFormulaCalculoHelper obterDemonstrativoFormulaCalculo(
			FiscalizarParametroCalculoDebito fiscalizarParametroCalculoDebito) {
		
		if (fiscalizarParametroCalculoDebito.getCodCalculoConsumo().intValue()!=5 && fiscalizarParametroCalculoDebito.getCodCalculoConsumo().intValue()!=11){
			return null;
		}
		
		DemonstrativoFormulaCalculoHelper retorno = new DemonstrativoFormulaCalculoHelper();
		if (fiscalizarParametroCalculoDebito.getCodCalculoConsumo().intValue()==5){
			//SB0002- Exibir fórmula de cálculo código 5
			
			//Fórmula
			retorno.setFormula("O valor do débito é calculado através do produto entre a soma(valor de água + valor de esgoto) " +
			"e número de meses de fatura cadastrado na tabela de fiscalização situação água. <br/><br/> Caso não tenha " +
			"número de meses de fatura cadastrado, calcular o número de meses fatura da seguinte forma: <br/><br/> " +
			"Para imóveis na situação da ligação de água CORTADO, será considerado o número de meses fatura, " +
			"a diferença em meses  encontrada entre a  data de corte da ligação de água e  data de geração do débito. <br/><br/> Para imóveis na situação da ligação de " +
			"água SUPRIMIDO, será considerado o número de meses fatura, a diferença em meses encontrada entre a data de supressão da ligação de água " +
			"e data de geração do débito. <br/><br/> Obs: O sistema considera o número máximo de meses para sanções, quando o número de meses faturado encontrado através " +
			"da diferença entre as datas for superior ao número  máximo de meses parametrizado. Quando a diferença for igual a 0(zero), o sistema " +
			"atribui valor 1(um) para diferença entre as datas.");
			
			//Soma do valor de água + valor de esgoto
			fiscalizarParametroCalculoDebito.setValorAguar(fiscalizarParametroCalculoDebito.getValorAguar()!=null?
					fiscalizarParametroCalculoDebito.getValorAguar():new BigDecimal("0.00"));
			
			fiscalizarParametroCalculoDebito.setValorEsgoto(fiscalizarParametroCalculoDebito.getValorEsgoto()!=null?
					fiscalizarParametroCalculoDebito.getValorEsgoto():new BigDecimal("0.00"));
			BigDecimal soma = fiscalizarParametroCalculoDebito.getValorAguar().add(
					fiscalizarParametroCalculoDebito.getValorEsgoto());
			retorno.setSomaAguaEsgoto(Util.formatarMoedaReal(soma));
			
			//Número de meses fatura
			if(fiscalizarParametroCalculoDebito.getNumeroMesesFatura()!=null && fiscalizarParametroCalculoDebito.getNumeroMesesFatura().intValue()!=0){
				retorno.setNumeroMesesFatura(fiscalizarParametroCalculoDebito.getNumeroMesesFatura().intValue());
			} else {
				Integer intervalo=0;
				if (fiscalizarParametroCalculoDebito.getLigacaoAguaSituacao()!=null){
					if(fiscalizarParametroCalculoDebito.getLigacaoAguaSituacao().getId().intValue()==5){ //Cortado
						intervalo = Util.dataDiff(fiscalizarParametroCalculoDebito.getDataCorte(), fiscalizarParametroCalculoDebito.getDataGeracaoDebito());
					}else if (fiscalizarParametroCalculoDebito.getLigacaoAguaSituacao().getId().intValue()==6 || 
							fiscalizarParametroCalculoDebito.getLigacaoAguaSituacao().getId().intValue()==7 ||
							fiscalizarParametroCalculoDebito.getLigacaoAguaSituacao().getId().intValue()==8 ){
						intervalo = Util.dataDiff(fiscalizarParametroCalculoDebito.getDataSupressaoAgua(), fiscalizarParametroCalculoDebito.getDataGeracaoDebito());
					}
				}
				
				if (intervalo.intValue()==0) {
					intervalo =1;
				}else if (intervalo.intValue()> fiscalizarParametroCalculoDebito.getNumeroMaximoMesesSancoes().intValue()){
					intervalo = fiscalizarParametroCalculoDebito.getNumeroMaximoMesesSancoes().intValue();
				}
				
				retorno.setNumeroMesesFatura(intervalo.intValue());
			}
			
			//Valor do débito calculado
			retorno.setValorDebitoCalculado(Util.formatarMoedaReal(soma.multiply(new BigDecimal(retorno.getNumeroMesesFatura()))));
						
		} 
		
		else if (fiscalizarParametroCalculoDebito.getCodCalculoConsumo().intValue()==11){
			//SB0003- Exibir fórmula de cálculo código 11
			
			//Fórmula
			retorno.setFormula("O valor do débito é calculado através do produto entre o valor da infração e o fator de reincidência ");
			
			//Valor da infração
			retorno.setValorInfracao("O valor da infração é calculado através do produto entre a quantidade de economias da subcategoria selecionada(limitando em 4) e o valor da tarifa");
			
			//Fator de reincidência
			retorno.setFatorReincidencia("Será aplicado o fator de reincidência associado ao tipo de infração, quando o imóvel for reincidente para o mesmo tipo de infração. ");
			
			//Valor da infração calculado
			BigDecimal vlInfracao = fiscalizarParametroCalculoDebito.getValorTarifaAutoInfracao().multiply(new BigDecimal
					(fiscalizarParametroCalculoDebito.getQtdEconomiaAutoInfracao()));
			retorno.setValorInfracaoCalculado(Util.formatarMoedaReal(vlInfracao));
						
			//Fator de reincidência cadastrado para infração
			retorno.setFatorReincidenciaCadastradoInfracao(fiscalizarParametroCalculoDebito.getFatorReincidencia()); 
				
			//Valor do débito calculado
			retorno.setValorDebitoCalculado(Util.formatarMoedaReal(vlInfracao.multiply
					(retorno.getFatorReincidenciaCadastradoInfracao())));			
		}
	
		return retorno;	
	}
	
	/**
	 * [UC1369] Gerar Itens Receita Liquida Indireta Agua Esgoto
	 * 
	 * @author Anderson Cabral
	 * @throws ControladorException 
	 * @created 09/08/2012
	 */
	public void gerarReceitaLiquidaIndiretaDeAguaEEsgoto(Integer idFuncionalidadeIniciada) throws ControladorException{
		int idUnidadeIniciada = 0;

		// ---------------------------------------------------
		// Registrar o inicio do processamento da Unidade de
		// Processamento do Batch
		// ---------------------------------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE, 0);

		try {
			if(this.repositorioFaturamento.countReceitaLiquidaIndiretaDeAguaEEsgoto() > 0){
				SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
				this.repositorioFaturamento.apagarReceitasLiquidaIndiretaDeAguaEEsgoto(sistemaParametro.getAnoMesFaturamento());
			}
			
			
			
			this.repositorioFaturamento.gerarReceitaLiquidaIndiretaDeEsgoto();
			this.repositorioFaturamento.gerarReceitaLiquidaIndiretaDeAgua();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}
	
	/**
	 * 
	 * @author Rodrigo Cabral
	 * @date 21/08/12
	 * 
	 * @throws ErroRepositorioException
	 */
	public void gerarResumoFaturamentoAnaliticoParte1(
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o início do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
			
				System.out.println("Iniciou Gerar Resumo Faturamento Analitico Parte 1");
				repositorioFaturamento.gerarResumoFaturamentoAnaliticoParte1();
				System.out.println("Finalizou Gerar Resumo Faturamento Analitico Parte 1");
							
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	/**
	 * 
	 * @author Rodrigo Cabral
	 * @date 21/08/12
	 * 
	 * @throws ErroRepositorioException
	 */
	public void gerarResumoFaturamentoAnaliticoParte2(
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o início do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
			
				System.out.println("Iniciou Gerar Resumo Faturamento Analitico Parte 2");
				repositorioFaturamento.gerarResumoFaturamentoAnaliticoParte2();
				System.out.println("Finalizou Gerar Resumo Faturamento Analitico Parte 2");
							
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	/**
	 * 
	 * @author Rodrigo Cabral
	 * @date 21/08/12
	 * 
	 * @throws ErroRepositorioException
	 */
	public void gerarResumoFaturamentoAnaliticoTotais(
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o início do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
			
				System.out.println("Iniciou Gerar Resumo Faturamento Analitico Totais");
				repositorioFaturamento.gerarResumoFaturamentoAnaliticoTotais();
				System.out.println("Finalizou Gerar Resumo Faturamento Analitico Totais");
							
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	/**
	 * 
	 * [UC1367] Registrar Movimento do Programa Especial
	 * [SB0003] Suspender Imóveis em Programa Especial
	 * 
	 * @author Hugo Azevedo
	 * @date 20/08/2012
	 * 
	 */
	public FaturaItem obterFaturaItemImovelAnoMesFaturamento(Integer idImovel, Integer anoMesFaturamento) throws ControladorException{
		try{
			return repositorioFaturamento.obterFaturaItemImovelAnoMesFaturamento(idImovel, anoMesFaturamento);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * 
	 * [UC1367] Registrar Movimento do Programa Especial
	 * [SB0003] Suspender Imóveis em Programa Especial
	 * 
	 * @author Hugo Azevedo
	 * @date 20/08/2012
	 * 
	 */
	public Conta obterContaImovelAnoMesFaturamento(Integer idImovel, Integer anoMesFaturamento)  throws ControladorException{
		try{
			return repositorioFaturamento.obterContaImovelAnoMesFaturamento(idImovel, anoMesFaturamento);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}

	
	/**
	 * [UC1194] Consultar Estrutura Tarifária Loja Virtual [SB0001] Pesquisar
	 * Tarifa Social ou Tarifa Mínima
	 * 
	 * Método que vai retornar um Helper que possui o consumo da tarifa mínima e
	 * da tarifa social e seus respectivos valores.
	 * 
	 * @author Arthur Carvalho
	 * @since 20/01/2012
	 * 
	 * @param idCategoria
	 * 
	 * @return Collection<ConsultarEstruturaTarifariaPortalCaerHelper>
	 */
	public ArrayList<ConsultarEstruturaTarifariaPortalCaerHelper> pesquisarEstruturaTarifariaCaer(Integer idCategoria, Integer idTarifa) 
		throws ControladorException {
		ArrayList<ConsultarEstruturaTarifariaPortalCaerHelper> estruturaTarifaria = new ArrayList<ConsultarEstruturaTarifariaPortalCaerHelper>();

		try{
			Collection<Object[]> dados;
			
			// ------------------- Pesquisa a Tarifa Normal -------------------//
			dados  = this.repositorioFaturamento.pesquisarTarifaSocialOuTarifaMinimaCaer(idTarifa, idCategoria);
			estruturaTarifaria = this.adicionarEstruturaTarifariaCaer(estruturaTarifaria, dados);
			
		}catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return estruturaTarifaria;
	}
	
	/**
	 * [UC1194] Consultar Estrutura Tarifária Loja Virtual.
	 *  Método auxiliar para montar a estrutura tarifária da loja virtual.
	 * 
	 * @param ConsultarEstruturaTarifariaPortalHelper -
	 *            Helper que vai ser acrescentado novas estruturas
	 * @param Collection
	 *            <Object[]> - Coleção de objetos que retornou da busca no
	 *            repositório
	 * @param tarifaNormal -
	 *            Booleano que vai indicar se a tarifa é normal ou tarifa mínima
	 * @param tarifaSocial -
	 *            Booleano que vai indicar se a tarifa é social (Apenas para a
	 *            categoria de imóvel residencial)
	 * 
	 */
	private ArrayList<ConsultarEstruturaTarifariaPortalCaerHelper> adicionarEstruturaTarifariaCaer(
			ArrayList<ConsultarEstruturaTarifariaPortalCaerHelper> estrutura, Collection<Object[]> dados){
		
		StringBuilder sb = new StringBuilder();
		String valor = null;
		Object[] object;
		// Verifica se a consulta retornou algum registro (Tarifa Social)
		if(!Util.isVazioOrNulo(dados)){
			Iterator<Object[]> iterator = dados.iterator();

			/*
			 * Adiciona todos os registros encontrados na coleção do helper.
			 * A primeira coluna que retorna da consulta é o consumo e a
			 * segunda coluna é o valor do tarifa. E a terceira a categoria
			 * da tarifa.
			 */
			while(iterator.hasNext()){
				object = iterator.next();
				
				if(object[0] != null){
					valor = Util.formatarMoedaReal((BigDecimal)object[0]);
				}
				
				/*
				 * O último parâmetro é um integer (1), pois esse número irá
				 * auxiliar na montagem do relatório da estrutura tarifária.
				 * Esse índice indica que são consumidores medidos. No
				 * ExibirConsultarEstruturaTarifariaPortalAction serão
				 * inicializados os consumidores não medidos cuja costante é
				 * igual à 2.
				 */
				estrutura.add(new ConsultarEstruturaTarifariaPortalCaerHelper(valor, 1, (String) object[1]));
			}
		
		}else{
			estrutura.add(new ConsultarEstruturaTarifariaPortalCaerHelper("", null, ""));
		}
		return estrutura;
	}
	
	/**
	 * 
	 * @author Rodrigo Cabral
	 * @date 16/10/12
	 * 
	 * @throws ErroRepositorioException
	 */
	public void gerarCreditoARealizarPagamentoDuplicidadeVivaAgua(
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o início do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
			
				repositorioFaturamento.gerarCreditoARealizarPagamentoDuplicidadeVivaAgua();
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	
	/**
	 * 
	 * 
	 * @author Rodrigo Cabral
	 * @throws ControladorException
	 * @data 01/11/2012
	 * 
	 */
	public Boolean verificarExistenciaContasNaoPagasDeclaracaoQuitacao(
			Integer idImovel, String ano)
			throws ControladorException {
		try {

			return repositorioFaturamento
					.verificarExistenciaContasNaoPagasDeclaracaoQuitacao(idImovel, ano);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}

/**
	 * Automatizar a carga de dados mensal do sistema Pentaho
	 * @author Amélia Pessoa
	 * @date 16/11/2012
	 */
	public void cargaMensalPentaho(Integer idFuncionalidadeIniciada)throws ControladorException {
		
		int idUnidadeIniciada = 0;

		// ------------------------------------------------------
		//
		// Registrar o inicio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// ------------------------------------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE, 0);
		
		try {
			//Parametros do sistema
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			
			//Chama arquivo .sh com rotina da empresa OnCase 
			//para geração da carga mensal do Pentaho
			String[] cmd = {"ssh", "root@"+sistemaParametro.getIpMaquinaPentaho(), "/opt/pentaho/caern/carga.sh"};			
			Runtime.getRuntime().exec(cmd);
			
			
			// --------------------------------------------------------
			//
			// Registrar o fim da execucao da Unidade de Processamento
			//
			// --------------------------------------------------------

			//getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
				//	idUnidadeIniciada, false);
						
		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer excecao que o processo
			// batch venha a lancar e garantir que a unidade de processamento do
			// batch sera atualizada com o erro ocorrido
			ex.printStackTrace();
			// sessionContext.setRollbackOnly();
	
			///getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
			//		idUnidadeIniciada, true);
	
			throw new EJBException(ex);
		}
		
	}
	
	/**
	 * Verificar se a fatura item ja existe
	 * @author Carlos Chaves
	 * @date 07/12/2012
	 * 
	 * @return Integer retorno
	 * @throws ErroRepositorioException
	 */
	public boolean existeFaturaItem(Integer imovId, Integer anoMes)
			throws ControladorException {
		Integer totalRegistros = null;
		try {
			totalRegistros = repositorioFaturamento
					.existeFaturaItem(imovId, anoMes);
			
			if(totalRegistros!=null && totalRegistros>=1){
				return true;
			}else{
				return false;
			}
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}
	
	/**
	 * Retorna o count do resultado da pesquisa de Fatura Item
	 * @author Carlos Chaves
	 * @date 07/12/2012
	 * 
	 * @return Integer retorno
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarFaturaItemCount(Filtro filtro)
			throws ControladorException {
		Integer totalRegistros = null;
		try {
			totalRegistros = repositorioFaturamento
					.pesquisarFaturaItemCount(filtro);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return totalRegistros;
	}
	
	public void faturarImovel(Integer idFaturamentoGrupo, Integer anoMesRef, Integer anoMesReferenciaGrupoMenosUmMes) 
			throws ControladorException, ErroRepositorioException {
		

		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
		/*
		 * Caso o m?s de faturamento corresponda ao m?s de novembro, o sistema 
		 * verifica se haver? faturamento antecipado
		 */
		if (Util.obterMes(anoMesRef) == ConstantesSistema.NOVEMBRO &&
			sistemaParametro.getIndicadorFaturamentoAntecipado().equals(ConstantesSistema.SIM)) {
		
			Integer anoMesReferencia = Util.somaUmMesAnoMesReferencia(anoMesRef);
			
			//[SB0019]- Excluir Contas da Tabela Auxiliar
            repositorioFaturamento.excluirContasTabelaAuxiliar(idFaturamentoGrupo, anoMesReferencia);
		
            //[SB0001]-Gerar Tabela Auxiliar
			repositorioFaturamento.gerarTabelaAuxiliarAPartirDaSelecaoDeContasExcluidas(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0002]- Excluir Conta Categoria Consumo Faixa
			repositorioFaturamento.excluirContaCategoriaConsumoFaixa(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0003]- Excluir Conta Categoria       
			repositorioFaturamento.excluirContaCategoria(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0004]- Excluir Conta Impressão
			repositorioFaturamento.excluirContaImpressao(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0005]- Excluir Cliente Conta
			repositorioFaturamento.excluirClienteConta(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0006]- Excluir Conta Impostos Deduzidos
			repositorioFaturamento.excluirContaImopostosDeduzidos(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0007]- Excluir Débito Automático Movimento
			repositorioFaturamento.excluirDebitoAutomaticoMovimento(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0008]- Atualizar Débito A Cobrar
			repositorioFaturamento.atualizarDebitoACobrar(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0009]- Excluir Débito Cobrado Categoria
			repositorioFaturamento.excluirDebitoCobradoCategoria(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0010]- Excluir Débito Cobrado
			repositorioFaturamento.excluirDebitoCobrado(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0011]- Atualizar Crédito a Realizar
			repositorioFaturamento.atualizarCreditoARealizar(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0012]- Excluir Crédito Realizado Categoria
			repositorioFaturamento.excluirCreditoRealizadoCategoria(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0013]- Excluir Crédito Realizado
			repositorioFaturamento.excluirCreditoRealizado(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0014]- Atualizar Conta Geral
			repositorioFaturamento.atualizarContaGeral(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0015]- Excluir Conta
            repositorioFaturamento.excluirConta(idFaturamentoGrupo, anoMesReferencia);
            
            //[SB0016]- Atualizar Data de Realização Faturamento
            repositorioFaturamento.atualizarDataRealizacaoFaturamento(idFaturamentoGrupo, anoMesReferencia);
            
            //[SB0017]- Atualizar Ano/Mês Referência do Grupo
            repositorioFaturamento.atualizarAnoMesReferenciaGrupo(idFaturamentoGrupo, anoMesReferenciaGrupoMenosUmMes);
            
            //[SB0018]- Excluir Resumo Faturamento Simulação - Excluir os débitos  
            repositorioFaturamento.excluirResumoFaturamentoSimulacaoDebitos(idFaturamentoGrupo, anoMesReferencia);
            //[SB0018]- Excluir Resumo Faturamento Simulação - Excluir os créditos 
            repositorioFaturamento.excluirResumoFaturamentoSimulacaoCreditos(idFaturamentoGrupo, anoMesReferencia);
            //[SB0018]- Excluir Resumo Faturamento Simulação
            repositorioFaturamento.excluirResumoFaturamentoSimulacao(idFaturamentoGrupo, anoMesReferencia);
            
            //[SB0019]- Excluir Contas da Tabela Auxiliar
            repositorioFaturamento.excluirContasTabelaAuxiliar(idFaturamentoGrupo, anoMesReferencia);
				
		}
	}
	
	
	/**
	 * UC1242 - Cancelar Faturamento Grupo
	 * 
	 * @author Rodrigo Cabral
	 * @date 12/11/2012
	 */
	public void cancelarFaturarGrupo(Integer idFaturamentoGrupo, Integer anoMesReferencia, Integer anoMesReferenciaGrupoMenosUmMes) 
			throws ControladorException, ErroRepositorioException {
		

		/*
		 * Caso o m?s de faturamento corresponda ao m?s de novembro, o sistema 
		 * verifica se haver? faturamento antecipado
		 */
			//[SB0019]- Excluir Contas da Tabela Auxiliar
            repositorioFaturamento.excluirContasTabelaAuxiliar(idFaturamentoGrupo, anoMesReferencia);
		
            //[SB0001]-Gerar Tabela Auxiliar
			repositorioFaturamento.gerarTabelaAuxiliarAPartirDaSelecaoDeContasExcluidas(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0002]- Excluir Conta Categoria Consumo Faixa
			repositorioFaturamento.excluirContaCategoriaConsumoFaixa(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0003]- Excluir Conta Categoria       
			repositorioFaturamento.excluirContaCategoria(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0004]- Excluir Conta Impressão
			repositorioFaturamento.excluirContaImpressao(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0005]- Excluir Cliente Conta
			repositorioFaturamento.excluirClienteConta(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0006]- Excluir Conta Impostos Deduzidos
			repositorioFaturamento.excluirContaImopostosDeduzidos(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0007]- Excluir Débito Automático Movimento
			repositorioFaturamento.excluirDebitoAutomaticoMovimento(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0008]- Atualizar Débito A Cobrar
			repositorioFaturamento.atualizarDebitoACobrar(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0009]- Excluir Débito Cobrado Categoria
			repositorioFaturamento.excluirDebitoCobradoCategoria(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0010]- Excluir Débito Cobrado
			repositorioFaturamento.excluirDebitoCobrado(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0011]- Atualizar Crédito a Realizar
			repositorioFaturamento.atualizarCreditoARealizar(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0012]- Excluir Crédito Realizado Categoria
			repositorioFaturamento.excluirCreditoRealizadoCategoria(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0013]- Excluir Crédito Realizado
			repositorioFaturamento.excluirCreditoRealizado(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0014]- Atualizar Conta Geral
			repositorioFaturamento.atualizarContaGeral(idFaturamentoGrupo, anoMesReferencia);
			
			//[SB0015]- Excluir Conta
            repositorioFaturamento.excluirConta(idFaturamentoGrupo, anoMesReferencia);
            
            //[SB0016]- Atualizar Data de Realização Faturamento
            repositorioFaturamento.atualizarDataRealizacaoFaturamento(idFaturamentoGrupo, anoMesReferencia);
            
            //[SB0017]- Atualizar Ano/Mês Referência do Grupo
            repositorioFaturamento.atualizarAnoMesReferenciaGrupo(idFaturamentoGrupo, anoMesReferenciaGrupoMenosUmMes);
            
            //[SB0018]- Excluir Resumo Faturamento Simulação - Excluir os débitos  
            repositorioFaturamento.excluirResumoFaturamentoSimulacaoDebitos(idFaturamentoGrupo, anoMesReferencia);
            //[SB0018]- Excluir Resumo Faturamento Simulação - Excluir os créditos 
            repositorioFaturamento.excluirResumoFaturamentoSimulacaoCreditos(idFaturamentoGrupo, anoMesReferencia);
            //[SB0018]- Excluir Resumo Faturamento Simulação
            repositorioFaturamento.excluirResumoFaturamentoSimulacao(idFaturamentoGrupo, anoMesReferencia);
            
            //[SB0019]- Excluir Contas da Tabela Auxiliar
            repositorioFaturamento.excluirContasTabelaAuxiliar(idFaturamentoGrupo, anoMesReferencia);
				
	}
	
	
	/**
	 * Verificar se existe mais alguma conta associada a 
	 * entrada do parcelamento que não tenha sido paga
	 * 
	 * @author Davi Menezes
	 * @date 14/03/2013
	 */
	public String pesquisarMaiorDataConsumoTarifaVigencia(
			ConsumoTarifa consumoTarifa) throws ControladorException {
		try{
			Collection colecaoData = 
					repositorioFaturamento.pesquisarMaiorDataConsumoTarifaVigencia(consumoTarifa);
			
			String dataVigencia = "";
			
			if(!Util.isVazioOrNulo(colecaoData)){
				Iterator it = colecaoData.iterator();
				while(it.hasNext()){
					Date data = (Date) it.next();
					
					if(data != null){
						dataVigencia = Util.formatarData(data);
					}
				}
			}
			
			return dataVigencia;
		}catch(ErroRepositorioException ex){
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	
	
	public Integer verificarExistenciaContaAssociadaEntradaParcelamentoNaoPaga(
			Integer idParcelamento, Integer idConta, Integer idImovel)
			throws ControladorException {
		try {

			return repositorioFaturamento
					.verificarExistenciaContaAssociadaEntradaParcelamentoNaoPaga(
							idParcelamento, idConta, idImovel);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

	}
	
	/**
	 * [UC0604] -EmitirHistogramaAguaEconomia
	 * 
	 * obter HistogramaAguaEconomiaSemQuadra pelo anoMesRefencia
	 * 
	 * @author Carlos Chaves
	 * @date 26/02/2013
	 * 
	 * @param anoMesReferencia
	 * @throws ControladorException
	 */
	private Collection<Object[]>  pesquisarHistogramaAguaEconomiaSemQuadra(Integer anoMesReferencia)
			throws ControladorException{
		
		try {
			return repositorioFaturamento
					.pesquisarHistogramaAguaEconomiaSemQuadra(anoMesReferencia);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * Atualizar Valor Simulado Histograma Agua Economia Sem Quadra
	 * 
	 * @author Carlos Chaves
	 * @date 27/02/2013
	 * 
	 * @param Integer haesId
	 * @param  BigDecimal valorSimulado
	 * @throws ControladorException
	 */
	private void atualizarValorSimuladoHistogramaAguaEconomiaSemQuadra(Integer haesId, BigDecimal valorSimulado) 
			throws ControladorException{
		
		try {
			repositorioFaturamento
					.atualizarValorSimuladoHistogramaAguaEconomiaSemQuadra( haesId,  valorSimulado);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Cacular Valor Simulado Histograma Agua Economia Sem Quadra
	 * 
	 * @author Carlos Chaves
	 * @date 27/02/2013
	 * 
	 * @param Integer anoMesFaturamento
	 * @throws ControladorException
	 */
	public void calcularValorSimuladoHistogramaAguaEconomiaSemQuadra(Integer anoMesFaturamento) throws ControladorException{
		
		Collection<Object[]>  colecaoHistogramaAguaEconomiaSemQuadra = 
				this.pesquisarHistogramaAguaEconomiaSemQuadra(anoMesFaturamento);

		for (Object[] dadosHAES :colecaoHistogramaAguaEconomiaSemQuadra) {
		
			Integer idHistogramaAgua = (Integer) dadosHAES[0];
			Integer ligacaoSituacaoAguaId = (Integer) dadosHAES[1];
	        Integer subcategoriaId = (Integer) dadosHAES[2];
	        Integer quantidadeEcononia = (Integer) dadosHAES[3];
	        Integer consumoFaturadoAguaMes = (Integer) dadosHAES[4];
	        Integer tarifaImovel = (Integer) dadosHAES[5];
	        Integer idCategoria = (Integer) dadosHAES[6];
	    
	        FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
			//filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroSubCategoria.CATEGORIA);
	        filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID,subcategoriaId));
	        
	        Collection colecaoSubCategoria = 
	        		Fachada.getInstancia().pesquisar(filtroSubCategoria, Subcategoria.class.getName());
	        
	        for (Object obj : colecaoSubCategoria) {  
	        	Subcategoria subCategoria = (Subcategoria) obj;
	        	subCategoria.setQuantidadeEconomias(1);
	        	Categoria categoria = new Categoria();
	        	categoria.setId(idCategoria);
	        	subCategoria.setCategoria(categoria);
	        } 
	        
	  
	        //[SB0025] - Calcular valor de agua com tarifa de simulacao
	        Collection<CalcularValoresAguaEsgotoHelper> valores = this.calcularValoresAguaEsgoto(300001, ligacaoSituacaoAguaId,
					LigacaoEsgotoSituacao.POTENCIAL, ConstantesSistema.SIM,
					ConstantesSistema.NAO, colecaoSubCategoria,
					consumoFaturadoAguaMes, 0,
					0, Util.criarData(1, 1, 3000),
					Util.criarData(30, 1, 3000), ConstantesSistema.VALOR_ZERO,
					tarifaImovel, null,null);
	        
	        CalcularValoresAguaEsgotoHelper valoresAguaEsgoto = (CalcularValoresAguaEsgotoHelper) Util.retonarObjetoDeColecao(valores);
	        
	        if(valoresAguaEsgoto!=null){
	        	this.atualizarValorSimuladoHistogramaAguaEconomiaSemQuadra
	        		(idHistogramaAgua,  valoresAguaEsgoto.getValorFaturadoAguaCategoria().multiply(new BigDecimal(quantidadeEcononia)));
	        }
		}
	}
	
	/**
	 * [UC0606] -EmitirHistogramaEsgotoEconomia
	 * 
	 * obter HistogramaEsgotoEconomiaSemQuadra pelo anoMesRefencia
	 * 
	 * @author Carlos Chaves
	 * @date 26/02/2013
	 * 
	 * @param anoMesReferencia
	 * @throws ControladorException
	 */
	private Collection<Object[]>  pesquisarHistogramaEsgotoEconomiaSemQuadra(Integer anoMesReferencia)
			throws ControladorException{
		
		try {
			return repositorioFaturamento
					.pesquisarHistogramaEsgotoEconomiaSemQuadra(anoMesReferencia);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	
	/**
	 * Atualizar Valor Simulado Histograma Esgoto Economia Sem Quadra
	 * 
	 * @author Carlos Chaves
	 * @date 27/02/2013
	 * 
	 * @param Integer heesId
	 * @param  BigDecimal valorSimulado
	 * @throws ControladorException
	 */
	private void atualizarValorSimuladoHistogramaEsgotoEconomiaSemQuadra(Integer heesId, BigDecimal valorSimulado) 
			throws ControladorException{
		
		try {
			repositorioFaturamento
					.atualizarValorSimuladoHistogramaEsgotoEconomiaSemQuadra( heesId,  valorSimulado);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Cacular Valor Simulado Histograma Esgoto Economia Sem Quadra
	 * 
	 * @author Carlos Chaves
	 * @date 27/02/2013
	 * 
	 * @param Integer anoMesFaturamento
	 * @throws ControladorException
	 */
	public void calcularValorSimuladoHistogramaEsgotoEconomiaSemQuadra(Integer anoMesFaturamento) throws ControladorException{
		
		Collection<Object[]>  colecaoHistogramaEsgotoEconomiaSemQuadra = 
				this.pesquisarHistogramaEsgotoEconomiaSemQuadra(anoMesFaturamento);

		for (Object[] dadosHEES :colecaoHistogramaEsgotoEconomiaSemQuadra) {
		
			Integer idHistogramaPoco = (Integer) dadosHEES[0];
			Integer ligacaoSituacaoEsgotoId = (Integer) dadosHEES[1];
	        Integer subcategoriaId = (Integer) dadosHEES[2];
	        Integer quantidadeEcononia = (Integer) dadosHEES[3];
	        Integer consumoFaturadoEsgotoMes = (Integer) dadosHEES[4];
	        Integer tarifaImovel = (Integer) dadosHEES[5];
	        Integer idCategoria = (Integer) dadosHEES[6];
	        BigDecimal percentualEsgoto = new BigDecimal(dadosHEES[7]+"");
	    
	        FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
			//filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroSubCategoria.CATEGORIA);
	        filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID,subcategoriaId));
	        
	        Collection colecaoSubCategoria = 
	        		Fachada.getInstancia().pesquisar(filtroSubCategoria, Subcategoria.class.getName());
	        
	        for (Object obj : colecaoSubCategoria) {  
	        	Subcategoria subCategoria = (Subcategoria) obj;
	        	subCategoria.setQuantidadeEconomias(quantidadeEcononia);
	        	Categoria categoria = new Categoria();
	        	categoria.setId(idCategoria);
	        	subCategoria.setCategoria(categoria);
	        } 
	        
	  

	        //[SB0034] - Calcular valor de esgoto com tarifa de simulacao
	        Collection<CalcularValoresAguaEsgotoHelper> valores = this.calcularValoresAguaEsgoto
	        		(300001, LigacaoAguaSituacao.POTENCIAL, 
	        		ligacaoSituacaoEsgotoId, ConstantesSistema.NAO, ConstantesSistema.SIM, 
	        		colecaoSubCategoria,0, consumoFaturadoEsgotoMes, 
	        		0, Util.criarData(1, 1, 3000), 
	        		Util.criarData(30, 1, 3000), percentualEsgoto, tarifaImovel, null, null);
	        
	        CalcularValoresAguaEsgotoHelper valoresAguaEsgoto = (CalcularValoresAguaEsgotoHelper) Util.retonarObjetoDeColecao(valores);
	        
	        if(valoresAguaEsgoto!=null){
	        	this.atualizarValorSimuladoHistogramaEsgotoEconomiaSemQuadra
	        		(idHistogramaPoco,  valoresAguaEsgoto.getValorFaturadoEsgotoCategoria());
	        }
		}
	}
	
	
	/**
	 * 
	 * @author Rodrigo Cabral
	 * @date 03/05/2013
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> verificarRotasGerarArquivosComFaturarGrupo(FaturamentoGrupo faturamentoGrupo)
			throws ControladorException {
		try {
			return repositorioFaturamento
					.verificarRotasGerarArquivosComFaturarGrupo(faturamentoGrupo);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}
	
	/**
	 * [UC 1517] - Baixa Automática dos Pagamentos Não Classificados
	 * [SB 0003] - Analisar Impressão Simultânea
	 * 
	 * @author Davi Menezes
	 * @date 09/07/2013
	 * 
	 * @param idConta
	 * @throws ControladorException
	 */
	public Integer pesquisarExistenciaContaMovimentoContaPrefaturada(Integer idConta) throws ControladorException {
		try{
			return repositorioFaturamento.pesquisarExistenciaContaMovimentoContaPrefaturada(idConta);
		}catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC 1517] - Baixa Automática dos Pagamentos Não Classificados
	 * [SB 0005] - Retificar Conta
	 * 
	 * @author Davi Menezes
	 * @date 09/07/2013
	 */
	public Integer pesquisarCreditoTipoPelaConstante(Integer idConstanteCreditoTipo) throws ControladorException {
		try{
			return repositorioFaturamento.pesquisarCreditoTipoPelaConstante(idConstanteCreditoTipo);
		}catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC 1517] - Baixa Automática dos Pagamentos Não Classificados
	 * [SB 0005] - Retificar Conta
	 * 
	 * @author Davi Menezes
	 * @date 09/07/2013
	 */
	public Integer pesquisarDebitoTipoPelaConstante(Integer idConstanteDebitoTipo) throws ControladorException {
		try{
			return repositorioFaturamento.pesquisarDebitoTipoPelaConstante(idConstanteDebitoTipo);
		}catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * @author Davi Menezes
	 * @date 27/07/2013
	 */
	public Collection<ContaValoresHelper> validarContasIndicadorBloqueioMotivoRevisao(Collection<ContaValoresHelper> 
			colecaoContaValoresHelper) throws ControladorException {
		
		try{
			Collection<Integer> colecaoIdsContasRemover = new ArrayList<Integer>();
			
			ContaValoresHelper contaValoresHelper = null;
			Conta conta = null;
			
			Short indicadorBloqueioAlteracaoConta = new Short("2");
			
			Iterator<ContaValoresHelper> it = colecaoContaValoresHelper.iterator();
			while(it.hasNext()){
				contaValoresHelper = (ContaValoresHelper) it.next();
				
				conta = contaValoresHelper.getConta();
				if(conta != null){
					if(conta.getContaMotivoRevisao() != null){
						indicadorBloqueioAlteracaoConta = repositorioFaturamento.pesquisarIndicadorBloqueioAlteracaoContaMotivoRevisao(
								conta.getContaMotivoRevisao().getId());
						
						if(indicadorBloqueioAlteracaoConta != null && indicadorBloqueioAlteracaoConta.equals(ConstantesSistema.SIM)){
							colecaoIdsContasRemover.add(conta.getId());
						}
					}
				}
			}
			
			if(!Util.isVazioOrNulo(colecaoIdsContasRemover)){
				for(Integer idConta : colecaoIdsContasRemover){
					it = colecaoContaValoresHelper.iterator();
					while(it.hasNext()){
						contaValoresHelper = (ContaValoresHelper) it.next();
						
						conta = contaValoresHelper.getConta();
						
						if(idConta.equals(conta.getId())){
							colecaoContaValoresHelper.remove(contaValoresHelper);
							break;
						}
					}
				}
			}
			
			return colecaoContaValoresHelper;
		}catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * @author Davi Menezes
	 * @date 30/07/2013
	 */
	public Short pesquisarIndicadorBloqueioAlteracaoContaMotivoRevisao(Integer idContaMotivoRevisao) 
			throws ControladorException{
		try{
			return repositorioFaturamento.pesquisarIndicadorBloqueioAlteracaoContaMotivoRevisao(idContaMotivoRevisao);
		}catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * @author Davi Menezes
	 * @date 03/10/2013
	 */
	public void atualizarValoresConta(Integer idConta, BigDecimal valorAgua, boolean atualizarAgua, 
			BigDecimal valorEsgoto, boolean atualizarEsgoto, BigDecimal valorImposto, boolean atualizarImposto) throws ControladorException{
		try{
			repositorioFaturamento.atualizarValoresConta(idConta, valorAgua, atualizarAgua, 
					valorEsgoto, atualizarEsgoto, valorImposto, atualizarImposto);
		}catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	public void retificarContasIndevidasCaern(
			Imovel imovel, int idFuncionalidadeIniciada) throws ControladorException {

		// -------------------------
		//
		// Registrar o in?cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		int idUnidadeIniciada = 0;
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.ROTA,imovel.getId());

		try {

			Collection<DebitoCobrado> debito = new ArrayList();
			
			ContaMotivoRetificacao contaMotivo = new ContaMotivoRetificacao();
			contaMotivo.setId(998);
			
			Usuario usuario = new Usuario();
			usuario.setId(1);
			
			String indicador = new String("2");
			
			Collection imoveis = new ArrayList();
			
			imoveis.add(imovel);
				
			this.retificarConjuntoContaAjusteCaern(imoveis, 201402, contaMotivo, debito, usuario, null, null, 201402, indicador);
			
			// --------------------------------------------------------
			//
			// Registrar o fim da execu??o da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {
			
			/*
			 * Este catch serve para interceptar qualquer exce??o que o processo batch venha a lan?ar e 
			 * garantir que a unidade de processamento do batch ser? atualizada com o erro ocorrido.
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new EJBException(e);
		}

	}
	
	/**
	 * Retificar Conjunto de Conta feito para CAERN
	 * 
	 * @author Tiago Moreno
	 * @date 27/08/2007
	 * 
	 * @throws ControladorException
	 */
	public void retificarConjuntoContaAjusteCaern(Collection colecaoImovel,
			Integer anoMes, ContaMotivoRetificacao contaMotivoRetificacao,
			Collection debitosTipoRetirar, Usuario usuarioLogado,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim,
			Integer anoMesFim, String indicadorContaPaga) throws ControladorException {

		Collection colecaoContasManutencao = new ArrayList();
		List colecaoAuxiliar = new ArrayList();

		colecaoAuxiliar.addAll(colecaoImovel);

		int i = 0;
		int cont = 500;

		Collection colecao = new ArrayList();
		while (i <= colecaoImovel.size()) {

			if (colecaoImovel.size() - i >= cont) {
				colecao = colecaoAuxiliar.subList(i, i + cont);
			} else {
				colecao = colecaoAuxiliar.subList(i, colecaoImovel.size());
			}
			i = i + cont;
			try {
				colecaoContasManutencao = repositorioFaturamento
						.obterContasImoveis(anoMes, colecao,
								dataVencimentoContaInicio,
								dataVencimentoContaFim, anoMesFim, indicadorContaPaga);

				if (colecaoContasManutencao != null
						&& !colecaoContasManutencao.isEmpty()) {

					Iterator colecaoContasManutencaoIterator = colecaoContasManutencao
							.iterator();

					while (colecaoContasManutencaoIterator.hasNext()) {

						// Obtï¿½m os dados do crï¿½dito realizado
						Object[] contaArray = (Object[]) colecaoContasManutencaoIterator
								.next();

						Conta conta = (Conta) contaArray[0];

						conta.setUltimaAlteracao(new Date());

						Imovel imovel = (Imovel) contaArray[1];

						Collection colecaoSubCategoria = getControladorImovel()
								.obterQuantidadeEconomiasContaCategoriaPorSubcategoria(
										conta);

						Collection colecaoCreditoRealizado = obterCreditosRealizadosConta(conta);

						Collection colecaoDebitoCobrado = obterDebitosCobradosConta(conta);
						
						BigDecimal valorTotalAgua = new BigDecimal("0");
				        BigDecimal valorTotalEsgoto = new BigDecimal("0");

						Collection<CalcularValoresAguaEsgotoHelper> valoresConta = calcularValoresConta(
								Util.formatarAnoMesParaMesAno(conta
										.getReferencia()), imovel.getId()
										.toString(), conta
										.getLigacaoAguaSituacao().getId(),
								conta.getLigacaoEsgotoSituacao().getId(),
								colecaoSubCategoria, conta.getConsumoAgua()
										.toString(), conta.getConsumoEsgoto()
										.toString(), conta
										.getPercentualEsgoto().toString(),
								conta.getConsumoTarifa().getId(), usuarioLogado);

						boolean achouDebitoRetirar = false;
						
						BigDecimal valorTotalConta = new BigDecimal("0");
				        
						if (valoresConta != null && !valoresConta.isEmpty()){
				        	
				        	Iterator valoresContaIt = valoresConta.iterator();
				        	CalcularValoresAguaEsgotoHelper valoresContaObjeto = null;
				        	
				        	while (valoresContaIt.hasNext()){
				        		
				        		valoresContaObjeto = (CalcularValoresAguaEsgotoHelper) valoresContaIt.next();
				        		
				        		//Valor Faturado de ï¿½gua
				        		if (valoresContaObjeto.getValorFaturadoAguaCategoria() != null){
				        			valorTotalAgua = valorTotalAgua.add(valoresContaObjeto.getValorFaturadoAguaCategoria());
				        		}
				        		
				        		//Valor Faturado de Esgoto
				        		if (valoresContaObjeto.getValorFaturadoEsgotoCategoria() != null){
				        			valorTotalEsgoto = valorTotalEsgoto.add(valoresContaObjeto.getValorFaturadoEsgotoCategoria());
				        		}
				        	} 
				        }
								        
				        valorTotalConta = valorTotalConta.add(valorTotalAgua);
				        valorTotalConta = valorTotalConta.add(valorTotalEsgoto);
						
						if (colecaoDebitoCobrado != null
								&& !colecaoDebitoCobrado.isEmpty()) {
							Iterator colecaoDebitoCobradoIterator = colecaoDebitoCobrado
									.iterator();
							while (colecaoDebitoCobradoIterator.hasNext()) {
								DebitoCobrado debitoCobrado = (DebitoCobrado) colecaoDebitoCobradoIterator
										.next();
								DebitoTipo debitoTipo = debitoCobrado
										.getDebitoTipo();
								if (debitosTipoRetirar.contains(debitoTipo)) {
									achouDebitoRetirar = true;
									colecaoDebitoCobradoIterator.remove();
								}
							}
					        
					        BigDecimal valorTotalDebitosConta = new BigDecimal("0");
					        
					        if (colecaoDebitoCobrado != null && !colecaoDebitoCobrado.isEmpty()){
					        	
					        	Iterator iColecaoDebitoCobrador = colecaoDebitoCobrado
										.iterator();
								while (iColecaoDebitoCobrador.hasNext()){
									DebitoCobrado debitoCobrado = (DebitoCobrado) iColecaoDebitoCobrador
											.next();
									
									valorTotalDebitosConta = valorTotalDebitosConta.add(debitoCobrado.getValorPrestacao());
								}
					        }
						        
					        valorTotalConta = valorTotalConta.add(valorTotalDebitosConta);

						}
						
				        BigDecimal valorTotalCreditosConta = new BigDecimal("0");
				        
				        if (colecaoCreditoRealizado != null && !colecaoCreditoRealizado.isEmpty()){
				        	
				        	Iterator iColecaoCreditoRealizado = colecaoCreditoRealizado
									.iterator();
							while (iColecaoCreditoRealizado.hasNext()){
								CreditoRealizado creditoRealizado = (CreditoRealizado) iColecaoCreditoRealizado
										.next();
								
								valorTotalCreditosConta = valorTotalCreditosConta.add(creditoRealizado.getValorCredito());
							}
				        }
				        
				        valorTotalConta = valorTotalConta.subtract(valorTotalCreditosConta);
						
						if (valorTotalConta.compareTo(new BigDecimal("0.00")) == 1){
				        	
				        	retificarConta(new Integer(conta
								.getReferencia()), conta, imovel,
								colecaoDebitoCobrado,
								colecaoCreditoRealizado, conta
										.getLigacaoAguaSituacao(),
								conta.getLigacaoEsgotoSituacao(),
								colecaoSubCategoria, conta
										.getConsumoAgua().toString(),
								conta.getConsumoEsgoto().toString(),
								conta.getPercentualEsgoto().toString(),
								conta.getDataVencimentoConta(),
								valoresConta, contaMotivoRetificacao,
								null, usuarioLogado, 
								conta.getConsumoTarifa().getId()+"",
								false, null, null, false, null, null, null, null, null, true);
				        }
					}
				}
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				new ControladorException("erro.sistema", ex);
			}
		}
	}
	
	/**
	 * [UC XXXX] - Processar amortização de dívida ativa
	 * 
	 * Pesquisa os itens associados a uma guia de pagamento
	 * 
	 * @author Rafael Corrêa
	 * @date 16/02/2014
	 */
	public Collection<GuiaPagamentoItem> pesquisarItensGuiaPagamento(Integer idGuiaPagamento) throws ControladorException {
		try{
			return repositorioFaturamento.pesquisarItensGuiaPagamento(idGuiaPagamento);
		}catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * UC0120 - Efetuar Parcelamento de Débitos
	 * Author: Vivianne Sousa Data: 18/02/2013
	 * 
	 * @param conta
	 * @return valor total de debitos cobrados em divida ativa de uma conta
	 * @throws ErroRepositorioException
	 */
	public BigDecimal buscarValorDebitosCobradosDividaAtivaConta(Integer contaId)throws ControladorException {
		try{
			return repositorioFaturamento.buscarValorDebitosCobradosDividaAtivaConta(contaId);
		}catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * UC0120 - Efetuar Parcelamento de Débitos
	 * Author: Vivianne Sousa Data: 18/02/2013
	 */
	public boolean verificarContaEhDividaAtiva(Integer contaId)throws ControladorException {
		try{
			
			boolean contaEhDividaAtiva = false;
						
			Integer idDividaAtivaDebito =  repositorioFaturamento.verificarContaDividaAtiva(contaId);
			
			if(idDividaAtivaDebito != null && idDividaAtivaDebito.compareTo(new Integer(0)) != 0){
				contaEhDividaAtiva = true;
			}
			
			return contaEhDividaAtiva;
			
		}catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0020] Calcular Valor de Água e/ou Esgoto
	 * 
	 * @author Vivianne Sousa
	 * @date 20/10/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorNumeroConsumoFaixaInicio()throws ControladorException {
		try{
			return repositorioFaturamento.pesquisarMaiorNumeroConsumoFaixaInicio();
		}catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	
	/**
	 * [UC ] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * 
	 * @author Flávio Leonardo C Cordeiro
	 * @date 09/02/2015
	 * 
	 * @throws ErroRepositorioException 
	 */
	public void inserirContaComunicado(ContaComunicado contaComunicado, String gerenciaRegional, String localidade, 
			String[] grupoFaturamento, String[] setorComercial, String[] rota, String[] quadra) throws ControladorException{
			try {
				
				Integer idComunicado = (Integer)getControladorUtil().inserir(contaComunicado);
				contaComunicado.setId(idComunicado);
				
				if (grupoFaturamento != null && ((grupoFaturamento.length > 0 && !grupoFaturamento[0].isEmpty()) || (grupoFaturamento.length > 1 && grupoFaturamento[0].isEmpty()))){
					FaturamentoGrupo faturamentoGrupo = null;
					FiltroFaturamentoGrupo filtroFaturamentoGrupo = null;
					Collection colecaoFaturamentoGrupo = null;
					ContaComunicadoFaturamentoGrupo contaComFat = null;
					for (int i = 0; i < grupoFaturamento.length; i++) {
						if (!grupoFaturamento[i].equals("") && !grupoFaturamento[i].equals("-1")){
							filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
							filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, grupoFaturamento[i].toString()));
							colecaoFaturamentoGrupo = getControladorUtil().pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
							if(colecaoFaturamentoGrupo != null && !colecaoFaturamentoGrupo.isEmpty()){
								faturamentoGrupo = (FaturamentoGrupo)colecaoFaturamentoGrupo.iterator().next();
								
								contaComFat = new ContaComunicadoFaturamentoGrupo();
								contaComFat.setContaComunicado(contaComunicado);
								contaComFat.setFaturamentoGrupo(faturamentoGrupo);
								contaComFat.setUltimaAlteracao(new Date());
								
								getControladorUtil().inserir(contaComFat);
							}
						}
					}
				}else if(quadra != null && ((quadra.length > 0 && !quadra[0].isEmpty()) || (quadra.length > 1 && quadra[0].isEmpty()))){
					Quadra objQuadra = null;
					FiltroQuadra filtroQuadra = null;
					Collection colecaoQuadra = null;
					ContaComunicadoQuadra contaComQuadra = null;
					for (int i = 0; i < quadra.length; i++) {
						if (!quadra[i].equals("")){
							filtroQuadra = new FiltroQuadra();
							filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, quadra[i].toString()));
							colecaoQuadra = getControladorUtil().pesquisar(filtroQuadra, Quadra.class.getName());
							if(colecaoQuadra != null && !colecaoQuadra.isEmpty()){
								objQuadra = (Quadra)colecaoQuadra.iterator().next();
								
								contaComQuadra = new ContaComunicadoQuadra();
								contaComQuadra.setContaComunicado(contaComunicado);
								contaComQuadra.setQuadra(objQuadra);
								contaComQuadra.setUltimaAlteracao(new Date());
								
								getControladorUtil().inserir(contaComQuadra);
							}
						}
					}
				}else if(rota != null && ((rota.length > 0 && !rota[0].isEmpty()) || (rota.length > 1 && rota[0].isEmpty()))){
					Rota objRota = null;
					FiltroRota filtroRota = null;
					Collection colecaoRota = null;
					ContaComunicadoRota contaComRota = null;
					for (int i = 0; i < rota.length; i++) {
						if (!rota[i].equals("")){
							filtroRota = new FiltroRota();
							filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, rota[i].toString()));
							colecaoRota = getControladorUtil().pesquisar(filtroRota, Rota.class.getName());
							if(colecaoRota != null && !colecaoRota.isEmpty()){
								objRota = (Rota)colecaoRota.iterator().next();
								
								contaComRota = new ContaComunicadoRota();
								contaComRota.setContaComunicado(contaComunicado);
								contaComRota.setRota(objRota);
								contaComRota.setUltimaAlteracao(new Date());
								
								getControladorUtil().inserir(contaComRota);								
							}
						}
					}
				}else if(setorComercial != null && ((setorComercial.length > 0 && !setorComercial[0].isEmpty()) || (setorComercial.length > 1 && setorComercial[0].isEmpty()))){
					SetorComercial objSetor = null;
					FiltroSetorComercial filtroSetor = null;
					Collection colecaoSetor = null;
					ContaComunicadoSetor contaComSetor = null;
					for (int i = 0; i < setorComercial.length; i++) {
						if (!setorComercial[i].equals("")){
							filtroSetor = new FiltroSetorComercial();
							filtroSetor.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID, setorComercial[i].toString()));
							colecaoSetor = getControladorUtil().pesquisar(filtroSetor, SetorComercial.class.getName());
							if(colecaoSetor != null && !colecaoSetor.isEmpty()){
								objSetor = (SetorComercial)colecaoSetor.iterator().next();
								
								contaComSetor = new ContaComunicadoSetor();
								contaComSetor.setContaComunicado(contaComunicado);
								contaComSetor.setSetorComercial(objSetor);
								contaComSetor.setUltimaAlteracao(new Date());
								
								getControladorUtil().inserir(contaComSetor);								
							}
						}
					}
				}else if(localidade != null && !localidade.equalsIgnoreCase("")){
					Localidade loc = new Localidade();
					loc.setId(new Integer(localidade));
					contaComunicado.setLocalidade(loc);
					
					getControladorUtil().atualizar(contaComunicado);
				}else if (gerenciaRegional != null && !gerenciaRegional.equalsIgnoreCase("")){
					GerenciaRegional gr = new GerenciaRegional();
					gr.setId(new Integer(gerenciaRegional));
					contaComunicado.setGerenciaRegional(gr);
					
					getControladorUtil().atualizar(contaComunicado);
				}
				
				
			} catch (ControladorException e) {
				e.printStackTrace();
			}
	}

	
	/**
	 * [UC1669] Atualizar Dados nas Tabelas Resumos Grenciais Faturamento
	 * 
	 * @author Fábio Aguiar
	 * @date 30/01/2015
	 * 
	 * @throws ErroRepositorioException
	 */
	public void gerarResumoFaturamentoAtualizaDados(
			Integer idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o inÃ­cio do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
					
				System.out.println("Iniciou Gerar Resumo Faturamento Dados");
				repositorioFaturamento.gerarResumoFaturamentoAtualizaDados();
				System.out.println("Finalizou Gerar Resumo Faturamento Dados");
						
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	/**
	  * [UC ] Filtrar Conta Comunicado
	 * 
	 * @author Flávio Leonardo C Cordeiro
	 * @date 09/02/2015
	 * 
	 * @throws ErroRepositorioException 
	 */
	public void verificaComunicadoExisteMesmoMesAnoAtualizar(ContaComunicado contaComunicado, String gerenciaRegional, String localidade, 
			String[] grupoFaturamento, String[] setorComercial, String[] rota, String[] quadra) throws ControladorException {
		
		FiltroContaComunicado filtroComunicado = null;
		Collection colecaoResultado = null;
		String[] msg = new String[2];
		boolean colocarParentese = true;

		if (grupoFaturamento != null && ((grupoFaturamento.length > 0 && !grupoFaturamento[0].isEmpty()) || (grupoFaturamento.length > 1 && grupoFaturamento[0].isEmpty()))){
			FiltroContaComunicadoFaturamentoGrupo filtroComunicadoFaturamento = new FiltroContaComunicadoFaturamentoGrupo();
			filtroComunicadoFaturamento.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoFaturamentoGrupo.CONTA_COMUNICADO);
			filtroComunicadoFaturamento.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoFaturamentoGrupo.FATURAMENTO_GRUPO);
			filtroComunicadoFaturamento.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroContaComunicadoFaturamentoGrupo.CONTA_COMUNICADO_ID, contaComunicado.getId()));
			for (int i = 0; i < grupoFaturamento.length; i++) {
				if (!grupoFaturamento[i].equals("") && !grupoFaturamento[i].equals("-1")){
					if(colocarParentese && ((grupoFaturamento[0].isEmpty() && grupoFaturamento.length>2) || (!grupoFaturamento[0].isEmpty() && grupoFaturamento.length>1))){
						filtroComunicadoFaturamento.adicionarParametro(new ParametroSimples(FiltroContaComunicadoFaturamentoGrupo.FATURAMENTO_GRUPO_ID, grupoFaturamento[i].toString(), ConectorOr.CONECTOR_OR, grupoFaturamento.length - i));
						colocarParentese = false;
					}else{
						if(i+1 == grupoFaturamento.length){
							filtroComunicadoFaturamento.adicionarParametro(new ParametroSimples(FiltroContaComunicadoFaturamentoGrupo.FATURAMENTO_GRUPO_ID, grupoFaturamento[i].toString()));
						}else{
							filtroComunicadoFaturamento.adicionarParametro(new ParametroSimples(FiltroContaComunicadoFaturamentoGrupo.FATURAMENTO_GRUPO_ID, grupoFaturamento[i].toString(), ConectorOr.CONECTOR_OR));
						}
					}
				}
			}
			filtroComunicadoFaturamento.adicionarParametro(new ParametroSimples(FiltroContaComunicadoFaturamentoGrupo.CONTA_COMUNICADO_ANO_MES, 
				contaComunicado.getAnoMesReferencia().toString()));
			filtroComunicadoFaturamento.adicionarParametro(new ParametroSimples(FiltroContaComunicadoFaturamentoGrupo.CONTA_COMUNICADO_INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			colecaoResultado = getControladorUtil().pesquisar(filtroComunicadoFaturamento, ContaComunicadoFaturamentoGrupo.class.getName());
			if(!colecaoResultado.isEmpty()){
				//erro
				ContaComunicadoFaturamentoGrupo contaComunicadoGrupoErro = (ContaComunicadoFaturamentoGrupo)colecaoResultado.iterator().next();
				msg[1] = Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia().toString());
				msg[0] = "o grupo de faturamento " + contaComunicadoGrupoErro.getFaturamentoGrupo().getDescricao();
				throw new ControladorException("atencao.conta.comunicado.existente", null, msg);
			}
		}else if(quadra != null && quadra.length > 0 && ((quadra.length > 0 && !quadra[0].isEmpty()) || (quadra.length > 1 && quadra[0].isEmpty()))){
			FiltroContaComunicadoQuadra filtroComunicadoQuadra = new FiltroContaComunicadoQuadra();
			filtroComunicadoQuadra.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoQuadra.CONTA_COMUNICADO);
			filtroComunicadoQuadra.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoQuadra.QUADRA);
			filtroComunicadoQuadra.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroContaComunicadoQuadra.CONTA_COMUNICADO_ID, contaComunicado.getId()));
			for (int i = 0; i < quadra.length; i++) {
				if (!quadra[i].equals("") && !quadra[i].equals("-1")){
					if(colocarParentese  && ((quadra[0].isEmpty() && quadra.length>2) || (!quadra[0].isEmpty() && quadra.length>1))){
						filtroComunicadoQuadra.adicionarParametro(new ParametroSimples(FiltroContaComunicadoQuadra.QUADRA_ID, quadra[i].toString(), ConectorOr.CONECTOR_OR, quadra.length - i));
						colocarParentese = false;
					}else{
						if(i+1 == quadra.length){
							filtroComunicadoQuadra.adicionarParametro(new ParametroSimples(FiltroContaComunicadoQuadra.QUADRA_ID, quadra[i].toString()));
						}else{
							filtroComunicadoQuadra.adicionarParametro(new ParametroSimples(FiltroContaComunicadoQuadra.QUADRA_ID, quadra[i].toString(), ConectorOr.CONECTOR_OR));
						}
					}
				}
			}
			filtroComunicadoQuadra.adicionarParametro(new ParametroSimples(FiltroContaComunicadoQuadra.CONTA_COMUNICADO_ANO_MES, 
				contaComunicado.getAnoMesReferencia().toString()));
			filtroComunicadoQuadra.adicionarParametro(new ParametroSimples(FiltroContaComunicadoQuadra.CONTA_COMUNICADO_INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			colecaoResultado = getControladorUtil().pesquisar(filtroComunicadoQuadra, ContaComunicadoQuadra.class.getName());
			if(!colecaoResultado.isEmpty()){
				//erro
				ContaComunicadoQuadra contaComunicadoQuadraErro = (ContaComunicadoQuadra)colecaoResultado.iterator().next();
				msg[1] = Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia().toString());
				msg[0] = "a quadra " + contaComunicadoQuadraErro.getQuadra().getNumeroQuadra();
				throw new ControladorException("atencao.conta.comunicado.existente", null, msg);
			}
		}else if(rota != null && ((rota.length > 0 && !rota[0].isEmpty()) || (rota.length > 1 && rota[0].isEmpty()))){
			FiltroContaComunicadoRota filtroComunicadoRota = new FiltroContaComunicadoRota();
			filtroComunicadoRota.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoRota.CONTA_COMUNICADO);
			filtroComunicadoRota.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoRota.ROTA);
			filtroComunicadoRota.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroContaComunicadoRota.CONTA_COMUNICADO_ID, contaComunicado.getId()));
			for (int i = 0; i < rota.length; i++) {
				if (!rota[i].equals("") && !rota[i].equals("-1")){
					if(colocarParentese && ((rota[0].isEmpty() && rota.length>2) || (!rota[0].isEmpty() && rota.length>1))){
						filtroComunicadoRota.adicionarParametro(new ParametroSimples(FiltroContaComunicadoRota.ROTA_ID, rota[i].toString(), ConectorOr.CONECTOR_OR, rota.length - i));
						colocarParentese = false;
					}else{
						if(i+1 == rota.length){
							filtroComunicadoRota.adicionarParametro(new ParametroSimples(FiltroContaComunicadoRota.ROTA_ID, rota[i].toString()));
						}else{
							filtroComunicadoRota.adicionarParametro(new ParametroSimples(FiltroContaComunicadoRota.ROTA_ID, rota[i].toString(), ConectorOr.CONECTOR_OR));
						}
					}
				}
			}
			filtroComunicadoRota.adicionarParametro(new ParametroSimples(FiltroContaComunicadoRota.CONTA_COMUNICADO_ANO_MES, 
				contaComunicado.getAnoMesReferencia().toString()));
			filtroComunicadoRota.adicionarParametro(new ParametroSimples(FiltroContaComunicadoRota.CONTA_COMUNICADO_INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			colecaoResultado = getControladorUtil().pesquisar(filtroComunicadoRota, ContaComunicadoRota.class.getName());
			if(!colecaoResultado.isEmpty()){
				//erro
				ContaComunicadoRota contaComunicadoRotaErro = (ContaComunicadoRota)colecaoResultado.iterator().next();
				msg[1] = Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia().toString());
				msg[0] = "a rota " + contaComunicadoRotaErro.getRota().getCodigo();
				throw new ControladorException("atencao.conta.comunicado.existente", null, msg);
			}
		}else if(setorComercial != null && ((setorComercial.length > 0 && !setorComercial[0].isEmpty()) || (setorComercial.length > 1 && setorComercial[0].isEmpty()))){
			FiltroContaComunicadoSetor filtroComunicadoSetor = new FiltroContaComunicadoSetor();
			filtroComunicadoSetor.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoSetor.CONTA_COMUNICADO);
			filtroComunicadoSetor.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoSetor.SETOR_COMERCIAL);
			filtroComunicadoSetor.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroContaComunicadoSetor.CONTA_COMUNICADO_ID, contaComunicado.getId()));
			for (int i = 0; i < setorComercial.length; i++) {
				if (!setorComercial[i].equals("") && !setorComercial[i].equals("-1")){
					if(colocarParentese && ((setorComercial[0].isEmpty() && setorComercial.length>2) || (!setorComercial[0].isEmpty() && setorComercial.length>1))){
						filtroComunicadoSetor.adicionarParametro(new ParametroSimples(FiltroContaComunicadoSetor.SETOR_COMERCIAL_ID, setorComercial[i].toString(), ConectorOr.CONECTOR_OR, setorComercial.length - i));
						colocarParentese = false;
					}else{
						if(i+1 == setorComercial.length){
							filtroComunicadoSetor.adicionarParametro(new ParametroSimples(FiltroContaComunicadoSetor.SETOR_COMERCIAL_ID, setorComercial[i].toString()));
						}else{
							filtroComunicadoSetor.adicionarParametro(new ParametroSimples(FiltroContaComunicadoSetor.SETOR_COMERCIAL_ID, setorComercial[i].toString(), ConectorOr.CONECTOR_OR));
						}
					}
				}
			}
			filtroComunicadoSetor.adicionarParametro(new ParametroSimples(FiltroContaComunicadoSetor.CONTA_COMUNICADO_ANO_MES, 
				contaComunicado.getAnoMesReferencia().toString()));
			filtroComunicadoSetor.adicionarParametro(new ParametroSimples(FiltroContaComunicadoSetor.CONTA_COMUNICADO_INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			colecaoResultado = getControladorUtil().pesquisar(filtroComunicadoSetor, ContaComunicadoSetor.class.getName());
			if(!colecaoResultado.isEmpty()){
				//erro
				ContaComunicadoSetor contaComunicadoSetorErro = (ContaComunicadoSetor)colecaoResultado.iterator().next();
				msg[1] = Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia().toString());
				msg[0] = "o setor comercial " + contaComunicadoSetorErro.getSetorComercial().getCodigo() + " - "+ contaComunicadoSetorErro.getSetorComercial().getDescricao();
				throw new ControladorException("atencao.conta.comunicado.existente", null, msg);
			}
		}else if(localidade != null && !localidade.equalsIgnoreCase("")){
			filtroComunicado = new FiltroContaComunicado();
			filtroComunicado.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicado.LOCALIDADE);
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.LOCALIDADE_ID, localidade));
			filtroComunicado.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroContaComunicado.ID, contaComunicado.getId()));
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.ANO_MES_REFERECIA, 
				contaComunicado.getAnoMesReferencia().toString()));
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoResultado = getControladorUtil().pesquisar(filtroComunicado, ContaComunicado.class.getName());
			if(!colecaoResultado.isEmpty()){
				//erro 
				ContaComunicado contaComunicadoErro = (ContaComunicado)colecaoResultado.iterator().next();
				msg[1] = Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia().toString());
				msg[0] = "a localidade " + contaComunicadoErro.getLocalidade() +" - "+ contaComunicadoErro.getLocalidade().getDescricao() ;
				throw new ControladorException("atencao.conta.comunicado.existente", null, msg);
			}
		}else if (gerenciaRegional != null && !gerenciaRegional.equalsIgnoreCase("")){
			filtroComunicado = new FiltroContaComunicado();
			filtroComunicado.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicado.GERENCIA_REGIONAL);
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.GERENCIA_REGIONAL_ID, gerenciaRegional));
			filtroComunicado.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroContaComunicado.ID, contaComunicado.getId()));
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.ANO_MES_REFERECIA, 
				contaComunicado.getAnoMesReferencia().toString()));
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoResultado = getControladorUtil().pesquisar(filtroComunicado, ContaComunicado.class.getName());
			if(!colecaoResultado.isEmpty()){
				ContaComunicado contaComunicadoErro = (ContaComunicado)colecaoResultado.iterator().next();
				msg[1] = Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia().toString());
				msg[0] = "a gerência regional " + contaComunicadoErro.getGerenciaRegional().getNome();
				throw new ControladorException("atencao.conta.comunicado.existente", null, msg);
			}
		}else{
			filtroComunicado = new FiltroContaComunicado();
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.ANO_MES_REFERECIA, 
				contaComunicado.getAnoMesReferencia().toString()));
			filtroComunicado.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroContaComunicado.ID, contaComunicado.getId()));
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoResultado = getControladorUtil().pesquisar(filtroComunicado, ContaComunicado.class.getName());
			if(!colecaoResultado.isEmpty()){
				ContaComunicado contaComunicadoErro = (ContaComunicado)colecaoResultado.iterator().next();
				msg[1] = Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia().toString());
				msg[0] = "o estado";
				throw new ControladorException("atencao.conta.comunicado.existente", null, msg);
			}
		}
		
	}
	
	/**
	  * [UC ] Filtrar Conta Comunicado
	 * 
	 * @author Flávio Leonardo C Cordeiro
	 * @date 09/02/2015
	 * 
	 * @throws ErroRepositorioException 
	 */
	public void verificaComunicadoExisteMesmoMesAno(ContaComunicado contaComunicado, String gerenciaRegional, String localidade, 
			String[] grupoFaturamento, String[] setorComercial, String[] rota, String[] quadra) throws ControladorException {
		
		FiltroContaComunicado filtroComunicado = null;
		Collection colecaoResultado = null;
		String[] msg = new String[2];
		
		boolean colocarParentese = true;

		if (grupoFaturamento != null && ((grupoFaturamento.length > 0 && !grupoFaturamento[0].isEmpty()) || (grupoFaturamento.length > 1 && grupoFaturamento[0].isEmpty()))){
			FiltroContaComunicadoFaturamentoGrupo filtroComunicadoFaturamento = new FiltroContaComunicadoFaturamentoGrupo();
			filtroComunicadoFaturamento.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoFaturamentoGrupo.CONTA_COMUNICADO);
			filtroComunicadoFaturamento.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoFaturamentoGrupo.FATURAMENTO_GRUPO);
			for (int i = 0; i < grupoFaturamento.length; i++) {
				if (!grupoFaturamento[i].equals("") && !grupoFaturamento[i].equals("-1")){
					if(colocarParentese && ((grupoFaturamento[0].isEmpty() && grupoFaturamento.length>2) || (!grupoFaturamento[0].isEmpty() && grupoFaturamento.length>1))){
						filtroComunicadoFaturamento.adicionarParametro(new ParametroSimples(FiltroContaComunicadoFaturamentoGrupo.FATURAMENTO_GRUPO_ID, grupoFaturamento[i].toString(), ConectorOr.CONECTOR_OR, grupoFaturamento.length - i));
						colocarParentese = false;
					}else{
						if(i+1 == grupoFaturamento.length){
							filtroComunicadoFaturamento.adicionarParametro(new ParametroSimples(FiltroContaComunicadoFaturamentoGrupo.FATURAMENTO_GRUPO_ID, grupoFaturamento[i].toString()));
						}else{
							filtroComunicadoFaturamento.adicionarParametro(new ParametroSimples(FiltroContaComunicadoFaturamentoGrupo.FATURAMENTO_GRUPO_ID, grupoFaturamento[i].toString(), ConectorOr.CONECTOR_OR));
						}
					}
				}
			}
			filtroComunicadoFaturamento.adicionarParametro(new ParametroSimples(FiltroContaComunicadoFaturamentoGrupo.CONTA_COMUNICADO_ANO_MES, 
				contaComunicado.getAnoMesReferencia().toString()));
			filtroComunicadoFaturamento.adicionarParametro(new ParametroSimples(FiltroContaComunicadoFaturamentoGrupo.CONTA_COMUNICADO_INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			colecaoResultado = getControladorUtil().pesquisar(filtroComunicadoFaturamento, ContaComunicadoFaturamentoGrupo.class.getName());
			if(!colecaoResultado.isEmpty()){
				//erro
				ContaComunicadoFaturamentoGrupo contaComunicadoGrupoErro = (ContaComunicadoFaturamentoGrupo)colecaoResultado.iterator().next();
				msg[1] = Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia().toString());
				msg[0] = "o grupo de faturamento " + contaComunicadoGrupoErro.getFaturamentoGrupo().getDescricao();
				throw new ControladorException("atencao.conta.comunicado.existente", null, msg);
			}
		}else if(quadra != null && ((quadra.length > 0 && !quadra[0].isEmpty()) || (quadra.length > 1 && quadra[0].isEmpty()))){
			FiltroContaComunicadoQuadra filtroComunicadoQuadra = new FiltroContaComunicadoQuadra();
			filtroComunicadoQuadra.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoQuadra.CONTA_COMUNICADO);
			filtroComunicadoQuadra.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoQuadra.QUADRA);
			for (int i = 0; i < quadra.length; i++) {
				if (!quadra[i].equals("") && !quadra[i].equals("-1")){
					if(colocarParentese && ((quadra[0].isEmpty() && quadra.length>2) || (!quadra[0].isEmpty() && quadra.length>1))){
						filtroComunicadoQuadra.adicionarParametro(new ParametroSimples(FiltroContaComunicadoQuadra.QUADRA_ID, quadra[i].toString(), ConectorOr.CONECTOR_OR,  quadra.length - i));
						colocarParentese = false;
					}else{
						if(i+1 == quadra.length){
							filtroComunicadoQuadra.adicionarParametro(new ParametroSimples(FiltroContaComunicadoQuadra.QUADRA_ID, quadra[i].toString()));
						}else{
							filtroComunicadoQuadra.adicionarParametro(new ParametroSimples(FiltroContaComunicadoQuadra.QUADRA_ID, quadra[i].toString(), ConectorOr.CONECTOR_OR));
						}
					}
				}
			}
			filtroComunicadoQuadra.adicionarParametro(new ParametroSimples(FiltroContaComunicadoQuadra.CONTA_COMUNICADO_ANO_MES, 
				contaComunicado.getAnoMesReferencia().toString()));
			filtroComunicadoQuadra.adicionarParametro(new ParametroSimples(FiltroContaComunicadoQuadra.CONTA_COMUNICADO_INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			colecaoResultado = getControladorUtil().pesquisar(filtroComunicadoQuadra, ContaComunicadoQuadra.class.getName());
			if(!colecaoResultado.isEmpty()){
				//erro
				ContaComunicadoQuadra contaComunicadoQuadraErro = (ContaComunicadoQuadra)colecaoResultado.iterator().next();
				msg[1] = Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia().toString());
				msg[0] = "a quadra " + contaComunicadoQuadraErro.getQuadra().getNumeroQuadra();
				throw new ControladorException("atencao.conta.comunicado.existente", null, msg);
			}
		}else if(rota != null && ((rota.length > 0 && !rota[0].isEmpty()) || (rota.length > 1 && rota[0].isEmpty()))){
			FiltroContaComunicadoRota filtroComunicadoRota = new FiltroContaComunicadoRota();
			filtroComunicadoRota.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoRota.CONTA_COMUNICADO);
			filtroComunicadoRota.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoRota.ROTA);
			for (int i = 0; i < rota.length; i++) {
				if (!rota[i].equals("") && !rota[i].equals("-1")){
					if(colocarParentese && ((rota[0].isEmpty() && rota.length>2) || (!rota[0].isEmpty() && rota.length>1))){
						filtroComunicadoRota.adicionarParametro(new ParametroSimples(FiltroContaComunicadoRota.ROTA_ID, rota[i].toString(), ConectorOr.CONECTOR_OR,  rota.length - i));
						colocarParentese = false;
					}else{
						if(i+1 == rota.length){
							filtroComunicadoRota.adicionarParametro(new ParametroSimples(FiltroContaComunicadoRota.ROTA_ID, rota[i].toString()));
						}else{
							filtroComunicadoRota.adicionarParametro(new ParametroSimples(FiltroContaComunicadoRota.ROTA_ID, rota[i].toString(), ConectorOr.CONECTOR_OR));
						}
					}
				}
			}
			filtroComunicadoRota.adicionarParametro(new ParametroSimples(FiltroContaComunicadoRota.CONTA_COMUNICADO_ANO_MES, 
				contaComunicado.getAnoMesReferencia().toString()));
			filtroComunicadoRota.adicionarParametro(new ParametroSimples(FiltroContaComunicadoRota.CONTA_COMUNICADO_INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			colecaoResultado = getControladorUtil().pesquisar(filtroComunicadoRota, ContaComunicadoRota.class.getName());
			if(!colecaoResultado.isEmpty()){
				//erro
				ContaComunicadoRota contaComunicadoRotaErro = (ContaComunicadoRota)colecaoResultado.iterator().next();
				msg[1] = Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia().toString());
				msg[0] = "a rota " + contaComunicadoRotaErro.getRota().getCodigo();
				throw new ControladorException("atencao.conta.comunicado.existente", null, msg);
			}
		}else if(setorComercial != null && ((setorComercial.length > 0 && !setorComercial[0].isEmpty()) || (setorComercial.length > 1 && setorComercial[0].isEmpty()))){
			FiltroContaComunicadoSetor filtroComunicadoSetor = new FiltroContaComunicadoSetor();
			filtroComunicadoSetor.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoSetor.CONTA_COMUNICADO);
			filtroComunicadoSetor.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoSetor.SETOR_COMERCIAL);
			for (int i = 0; i < setorComercial.length; i++) {
				if (!setorComercial[i].equals("") && !setorComercial[i].equals("-1")){
					if(colocarParentese && ((setorComercial[0].isEmpty() && setorComercial.length>2) || (!setorComercial[0].isEmpty() && setorComercial.length>1))){
						filtroComunicadoSetor.adicionarParametro(new ParametroSimples(FiltroContaComunicadoSetor.SETOR_COMERCIAL_ID, setorComercial[i].toString(), ConectorOr.CONECTOR_OR, setorComercial.length - i));
						colocarParentese = false;
					}else{
						if(i+1 == setorComercial.length){
							filtroComunicadoSetor.adicionarParametro(new ParametroSimples(FiltroContaComunicadoSetor.SETOR_COMERCIAL_ID, setorComercial[i].toString()));
						}else{
							filtroComunicadoSetor.adicionarParametro(new ParametroSimples(FiltroContaComunicadoSetor.SETOR_COMERCIAL_ID, setorComercial[i].toString(), ConectorOr.CONECTOR_OR));
						}
					}
				}
			}
			filtroComunicadoSetor.adicionarParametro(new ParametroSimples(FiltroContaComunicadoSetor.CONTA_COMUNICADO_ANO_MES, 
				contaComunicado.getAnoMesReferencia().toString()));
			filtroComunicadoSetor.adicionarParametro(new ParametroSimples(FiltroContaComunicadoSetor.CONTA_COMUNICADO_INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			colecaoResultado = getControladorUtil().pesquisar(filtroComunicadoSetor, ContaComunicadoSetor.class.getName());
			if(!colecaoResultado.isEmpty()){
				//erro
				ContaComunicadoSetor contaComunicadoSetorErro = (ContaComunicadoSetor)colecaoResultado.iterator().next();
				msg[1] = Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia().toString());
				msg[0] = "o setor comercial " + contaComunicadoSetorErro.getSetorComercial().getCodigo() + " - "+ contaComunicadoSetorErro.getSetorComercial().getDescricao();
				throw new ControladorException("atencao.conta.comunicado.existente", null, msg);
			}
		}else if(localidade != null && !localidade.equalsIgnoreCase("")){
			filtroComunicado = new FiltroContaComunicado();
			filtroComunicado.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicado.LOCALIDADE);
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.LOCALIDADE_ID, localidade));
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.ANO_MES_REFERECIA, 
				contaComunicado.getAnoMesReferencia().toString()));
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoResultado = getControladorUtil().pesquisar(filtroComunicado, ContaComunicado.class.getName());
			if(!colecaoResultado.isEmpty()){
				//erro 
				ContaComunicado contaComunicadoErro = (ContaComunicado)colecaoResultado.iterator().next();
				msg[1] = Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia().toString());
				msg[0] = "a localidade " + contaComunicadoErro.getLocalidade() +" - "+ contaComunicadoErro.getLocalidade().getDescricao() ;
				throw new ControladorException("atencao.conta.comunicado.existente", null, msg);
			}
		}else if (gerenciaRegional != null && !gerenciaRegional.equalsIgnoreCase("")){
			filtroComunicado = new FiltroContaComunicado();
			filtroComunicado.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicado.GERENCIA_REGIONAL);
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.GERENCIA_REGIONAL_ID, gerenciaRegional));
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.ANO_MES_REFERECIA, 
				contaComunicado.getAnoMesReferencia().toString()));
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoResultado = getControladorUtil().pesquisar(filtroComunicado, ContaComunicado.class.getName());
			if(!colecaoResultado.isEmpty()){
				ContaComunicado contaComunicadoErro = (ContaComunicado)colecaoResultado.iterator().next();
				msg[1] = Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia().toString());
				msg[0] = "a gerência regional " + contaComunicadoErro.getGerenciaRegional().getNome();
				throw new ControladorException("atencao.conta.comunicado.existente", null, msg);
			}
		}else{
			filtroComunicado = new FiltroContaComunicado();
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.ANO_MES_REFERECIA, 
				contaComunicado.getAnoMesReferencia().toString()));
			filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoResultado = getControladorUtil().pesquisar(filtroComunicado, ContaComunicado.class.getName());
			if(!colecaoResultado.isEmpty()){
				ContaComunicado contaComunicadoErro = (ContaComunicado)colecaoResultado.iterator().next();
				msg[1] = Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia().toString());
				msg[0] = "o estado";
				throw new ControladorException("atencao.conta.comunicado.existente", null, msg);
			}
		}
		
	}
	
	/**
	  * [UC ] Filtrar Conta Comunicado
	 * 
	 * @author Flávio Leonardo C Cordeiro
	 * @date 09/02/2015
	 * 
	 * @throws ErroRepositorioException 
	 */
	public Collection<ContaComunicadoHelper> pesquisarContaComando(ContaComunicadoHelper helper) throws ControladorException{
		Collection<ContaComunicadoHelper> retorno = new ArrayList();

		try {
			Collection<Object[]> colecaoResultado = this.repositorioFaturamento.pesquisarContaComando(helper);
			
			Object[] objResultado = null;
			if(colecaoResultado !=null && !colecaoResultado.isEmpty()){
				ContaComunicadoHelper helperComunicado = null;
				Iterator<Object[]> itResultado = colecaoResultado.iterator();
				
				while(itResultado.hasNext()){
					objResultado = itResultado.next();
					helperComunicado = new ContaComunicadoHelper();
					
					helperComunicado.setIdComunicado((Integer)objResultado[0]+"");
					helperComunicado.setReferencia(Util.formatarAnoMesParaMesAno((Integer)objResultado[1]+""));
					helperComunicado.setTitulo((String)objResultado[2]);
					helperComunicado.setComunicado((String)objResultado[3]);
					if(objResultado[4] != null){
						helperComunicado.setGerenciaRegional((Integer)objResultado[4]+"");
						helperComunicado.setGerenciaRegionalDescricao((String)objResultado[5]);
						helperComunicado.setGerenciaRegionalDescricaoAbreviada((String)objResultado[6]);
					}
					if(objResultado[7]!=null){
						helperComunicado.setLocalidade((Integer)objResultado[7]+"");
						helperComunicado.setLocalidadeDescricao((String)objResultado[8]);
					}
					
					retorno.add(helperComunicado);
				}
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	/**
	 * [UC ] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * 
	 * @author Flávio Leonardo C Cordeiro
	 * @date 09/02/2015
	 * 
	 * @throws ErroRepositorioException 
	 */
	public void atualizarContaComunicado(ContaComunicado contaComunicado, String gerenciaRegional, String localidade, 
			String[] grupoFaturamento, String[] setorComercial, String[] rota, String[] quadra) throws ControladorException{
			try {
				
				getControladorUtil().atualizar(contaComunicado);
				
				if (grupoFaturamento != null && ((grupoFaturamento.length > 0 && !grupoFaturamento[0].isEmpty()) || (grupoFaturamento.length > 1 && grupoFaturamento[0].isEmpty()))){
					FaturamentoGrupo faturamentoGrupo = null;
					FiltroFaturamentoGrupo filtroFaturamentoGrupo = null;
					Collection colecaoFaturamentoGrupo = null;
					ContaComunicadoFaturamentoGrupo contaComFat = null;
					for (int i = 0; i < grupoFaturamento.length; i++) {
						if (!grupoFaturamento[i].equals("") && !grupoFaturamento[i].equals("-1")){
							filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
							filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, grupoFaturamento[i].toString()));
							colecaoFaturamentoGrupo = getControladorUtil().pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
							if(colecaoFaturamentoGrupo != null && !colecaoFaturamentoGrupo.isEmpty()){
								faturamentoGrupo = (FaturamentoGrupo)colecaoFaturamentoGrupo.iterator().next();
								
								contaComFat = new ContaComunicadoFaturamentoGrupo();
								contaComFat.setContaComunicado(contaComunicado);
								contaComFat.setFaturamentoGrupo(faturamentoGrupo);
								contaComFat.setUltimaAlteracao(new Date());
								
								getControladorUtil().inserir(contaComFat);
							}
						}
					}
				}else if(quadra != null && ((quadra.length > 0 && !quadra[0].isEmpty()) || (quadra.length > 1 && quadra[0].isEmpty()))){
					Quadra objQuadra = null;
					FiltroQuadra filtroQuadra = null;
					Collection colecaoQuadra = null;
					ContaComunicadoQuadra contaComQuadra = null;
					for (int i = 0; i < quadra.length; i++) {
						if (!quadra[i].equals("")){
							filtroQuadra = new FiltroQuadra();
							filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, quadra[i].toString()));
							colecaoQuadra = getControladorUtil().pesquisar(filtroQuadra, Quadra.class.getName());
							if(colecaoQuadra != null && !colecaoQuadra.isEmpty()){
								objQuadra = (Quadra)colecaoQuadra.iterator().next();
								
								contaComQuadra = new ContaComunicadoQuadra();
								contaComQuadra.setContaComunicado(contaComunicado);
								contaComQuadra.setQuadra(objQuadra);
								contaComQuadra.setUltimaAlteracao(new Date());
								
								getControladorUtil().inserir(contaComQuadra);
							}
						}
					}
				}else if(rota != null && ((rota.length > 0 && !rota[0].isEmpty()) || (rota.length > 1 && rota[0].isEmpty()))){
					Rota objRota = null;
					FiltroRota filtroRota = null;
					Collection colecaoRota = null;
					ContaComunicadoRota contaComRota = null;
					for (int i = 0; i < rota.length; i++) {
						if (!rota[i].equals("")){
							filtroRota = new FiltroRota();
							filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, rota[i].toString()));
							colecaoRota = getControladorUtil().pesquisar(filtroRota, Rota.class.getName());
							if(colecaoRota != null && !colecaoRota.isEmpty()){
								objRota = (Rota)colecaoRota.iterator().next();
								
								contaComRota = new ContaComunicadoRota();
								contaComRota.setContaComunicado(contaComunicado);
								contaComRota.setRota(objRota);
								contaComRota.setUltimaAlteracao(new Date());
								
								getControladorUtil().inserir(contaComRota);								
							}
						}
					}
				}else if(setorComercial != null && ((setorComercial.length > 0 && !setorComercial[0].isEmpty()) || (setorComercial.length > 1 && setorComercial[0].isEmpty()))){
					SetorComercial objSetor = null;
					FiltroSetorComercial filtroSetor = null;
					Collection colecaoSetor = null;
					ContaComunicadoSetor contaComSetor = null;
					for (int i = 0; i < setorComercial.length; i++) {
						if (!setorComercial[i].equals("")){
							filtroSetor = new FiltroSetorComercial();
							filtroSetor.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID, setorComercial[i].toString()));
							colecaoSetor = getControladorUtil().pesquisar(filtroSetor, SetorComercial.class.getName());
							if(colecaoSetor != null && !colecaoSetor.isEmpty()){
								objSetor = (SetorComercial)colecaoSetor.iterator().next();
								
								contaComSetor = new ContaComunicadoSetor();
								contaComSetor.setContaComunicado(contaComunicado);
								contaComSetor.setSetorComercial(objSetor);
								contaComSetor.setUltimaAlteracao(new Date());
								
								getControladorUtil().inserir(contaComSetor);								
							}
						}
					}
				}else if(localidade != null && !localidade.equalsIgnoreCase("")){
					Localidade loc = new Localidade();
					loc.setId(new Integer(localidade));
					contaComunicado.setLocalidade(loc);
					
					getControladorUtil().atualizar(contaComunicado);
				}else if (gerenciaRegional != null && !gerenciaRegional.equalsIgnoreCase("")){
					GerenciaRegional gr = new GerenciaRegional();
					gr.setId(new Integer(gerenciaRegional));
					contaComunicado.setGerenciaRegional(gr);
					
					getControladorUtil().atualizar(contaComunicado);
				}
				
			} catch (ControladorException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Método responsável por<br>
	 * pesquisar conta comunicado<br>
	 * associado ao imovel
	 * @author Jonathan Marcos
	 * @since 13/02/2015
	 * @param idImovel
	 * @return ContaComunicado
	 * @throws ControladorException
	 */
	public ContaComunicado pesquisarContaComunicadoAssociadoAoImovel(Integer idImovel,Integer anoMesFaturamento)
			throws ControladorException {
		ContaComunicado contaComunicado = null;
		Object[] objeto = null;
		try{
			if(repositorioFaturamento.verificarExisteContaComunicadoAnoMes(anoMesFaturamento)){
				Imovel imovel = repositorioImovel.pesquisarImovel(idImovel);
				
				
				objeto = repositorioFaturamento.
						pesquisarContaComunicadoAssociadoQuadra(
								anoMesFaturamento, imovel.getQuadra().getId());
				
				if(objeto==null){
					objeto = repositorioFaturamento.
							pesquisarContaComunicadoAssociadoRota(
									anoMesFaturamento, imovel.getId());
				}
				
				if(objeto==null){
					objeto = repositorioFaturamento.
							pesquisarContaComunicadoAssociadoSetor(
									anoMesFaturamento, imovel.getSetorComercial().getId());
				}
				
				if(objeto==null){
					objeto = repositorioFaturamento.
							pesquisarContaComunicadoAssociadoLocalidade(
									anoMesFaturamento, imovel.getLocalidade().getId());
				}
				
				if(objeto==null){
					objeto = repositorioFaturamento.
							pesquisarContaComunicadoAssociadoGerenciaRegional(
									anoMesFaturamento, imovel.getLocalidade().getId());
				}
				
				if(objeto==null){
					objeto = repositorioFaturamento.
							pesquisarContaComunicadoAssociadoGrupoFaturamento(
									anoMesFaturamento, imovel.getQuadra().getId());
				}
				
				if(objeto!=null){
					contaComunicado = new ContaComunicado();
					contaComunicado.setId((Integer)objeto[0]);
					contaComunicado.setComunicado((String)objeto[1]);
				}
			}
		}catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		return contaComunicado;
	}

	public void removerComunicadoConta(ContaComunicado contaComunicado) throws ControladorException {
		try {
			this.repositorioFaturamento.removerComunicadoConta(contaComunicado);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * [UC0150] - Retificar Conta
	 * 
	 * Verificar se o cliente do imovel e diferente do cliente da conta
	 * 
	 * @author Rafael Corrêa
	 * @date 16/04/2015
	 **/
	public boolean verificarClienteImovelDiferenteClienteConta(Collection<Conta> colecaoContaImovel) throws ControladorException {
        ClienteConta clienteConta 	= null;
        ClienteImovel clienteImovel = null;
        boolean clienteDiferente = false;
        
		if(colecaoContaImovel != null){
			for(Conta conta : colecaoContaImovel){
	            FiltroClienteConta filtroClienteConta = new FiltroClienteConta();  
	            filtroClienteConta.setInitializeLazy(true);
	            filtroClienteConta.adicionarParametro(new ParametroSimples(FiltroClienteConta.CONTA_ID, conta.getId()));
	            filtroClienteConta.adicionarParametro(new ParametroSimples(FiltroClienteConta.INDICADOR_NOME_CONTA, 1));
	            Collection colecaoClienteConta = this.getControladorUtil().pesquisar(filtroClienteConta, ClienteConta.class.getName());
	                  
	            if(colecaoClienteConta != null && colecaoClienteConta.size() > 0){        	
	            	clienteConta = (ClienteConta) colecaoClienteConta.iterator().next();
	            }
	            
	            clienteImovel = this.getControladorCliente().pesquisarClienteImovel(conta.getImovel().getId().toString(), 1);          
	            
	            if(clienteImovel != null &&  clienteConta != null && 
		        		clienteImovel.getCliente().getId().intValue() != clienteConta.getCliente().getId().intValue()){
	            	clienteDiferente = true;
	            	break;
	            }
	        }
		}
		
		return clienteDiferente;
	}

	/**
	 * [UC1684] - Gerar Arquivo Exportação Faturas
	 * 
	 * @author André Miranda
	 * @date 08/06/2015
	 * 
	 * @param anoMes Referência
	 * @param idCliente
	 * @param idClienteSuperior
	 * @param nomeArquivo
	 * @return Arquivo exportação
	 */
	public byte[] gerarArquivoExportacaoFaturasAgrupadas(Integer anoMes, String idCliente, String idClienteSuperior, StringBuilder nomeArquivo) throws ControladorException {
		try {
			ByteArrayOutputStream retorno = new ByteArrayOutputStream();
			Cliente cliente = new Cliente();
			List<Object> listHelper = new ArrayList<Object>();
			Collection<Integer> idsClientes = new ArrayList<Integer>();
			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
			ArquivoExportacaoFaturasAgrupadasRegistro00Helper helper00;

			nomeArquivo.append(sistemaParametro.getNomeAbreviadoEmpresa());
			nomeArquivo.append("_FATURAS_AGRUPADAS_");
			nomeArquivo.append(anoMes);
			if(Util.verificarNaoVazio(idCliente)) {
				nomeArquivo.append("_" + idCliente);
			} else {
				nomeArquivo.append("_" + idClienteSuperior);
			}
			nomeArquivo.append(".txt");

			// Registro Tipo 00 - Identificacao do arquivo
			helper00 = new ArquivoExportacaoFaturasAgrupadasRegistro00Helper(null, new Date(), anoMes, nomeArquivo.toString());
			listHelper.add(helper00);

			if(Util.verificarNaoVazio(idCliente)) {
				cliente.setId(new Integer(idCliente));
			}

			// Consultando clientes associados ao cliente superior
			if (Util.verificarIdNaoVazio(idClienteSuperior)) {
				Integer id = new Integer(idClienteSuperior);
				helper00.setIdCliente(idClienteSuperior);
				idsClientes = getControladorCliente().pesquisarArvoreClientesResponsaveis(id);
			} else {
				helper00.setIdCliente(idCliente);
			}

			// Consultando quantidade de registros
			Integer qtdFaturasSelecionadas =
					pesquisarDadosRelatorioFaturasAgrupadasCount(anoMes, cliente, idsClientes);

			if (qtdFaturasSelecionadas == null || qtdFaturasSelecionadas.intValue() == 0) {
				throw new ControladorException("atencao.pesquisa.nenhumresultado", null, "");
			}

			// Consultando registros
			Collection<RelatorioFaturasAgrupadasBean> colecaoFaturas =
					pesquisarDadosRelatorioFaturasAgrupadas(anoMes, cliente, idsClientes);

			// Agrupa os itens de fatura pelo cliente responsável.
			// Assim como no relatório(relatorioFaturasAgrupadas),
			// o código de barras será do último bean de um responsável. 
			List<ArquivoExportacaoFaturasAgrupadasRegistro03Helper> listItem =
					new ArrayList<ArquivoExportacaoFaturasAgrupadasRegistro03Helper>();

			Integer idResponsavel = null;
			RelatorioFaturasAgrupadasBean ultimoFaturaItem = null;
			for (RelatorioFaturasAgrupadasBean faturaItem : colecaoFaturas) {
				if(faturaItem.getIndicadorContaHist() != 2) continue;

				if(idResponsavel == null || !idResponsavel.equals(faturaItem.getIdResponsavel())) {
					idResponsavel = faturaItem.getIdResponsavel();

					if(ultimoFaturaItem != null) {
						listHelper.add(new ArquivoExportacaoFaturasAgrupadasRegistro01Helper(ultimoFaturaItem));
						listHelper.add(new ArquivoExportacaoFaturasAgrupadasRegistro02Helper(ultimoFaturaItem));

						listHelper.addAll(listItem);
						listHelper.add(new ArquivoExportacaoFaturasAgrupadasRegistro04Helper(listItem));
						listItem.clear();
					}
				}

				listItem.add(new ArquivoExportacaoFaturasAgrupadasRegistro03Helper(faturaItem));

				ultimoFaturaItem = faturaItem;
			}

			if(ultimoFaturaItem != null) {
				listHelper.add(new ArquivoExportacaoFaturasAgrupadasRegistro01Helper(ultimoFaturaItem));
				listHelper.add(new ArquivoExportacaoFaturasAgrupadasRegistro02Helper(ultimoFaturaItem));
				listHelper.addAll(listItem);
				listHelper.add(new ArquivoExportacaoFaturasAgrupadasRegistro04Helper(listItem));
				listItem.clear();
			}

			// Registro Tipo 09 - Fim do arquivo
			listHelper.add(new ArquivoExportacaoFaturasAgrupadasRegistro09Helper(new Date()));

			TarefaSerializar tarefa = new TarefaSerializar();
			tarefa.executar(listHelper, retorno, new SerializadorPipe());

			return retorno.toByteArray();
		} catch (ControladorException e) {
			throw e;
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	

	/**
	 * [UC1687] - Gerar débito a cobrar de depreciação de Hidrômetro
	 * 
	 * Author: Cesar Medeiros
	 * @date: 22/06/2015 	
	 */
	public void gerarDebitoACobrarDepreciacaoHidrometro(Rota rota,
			Integer anoMesCorrente, Integer idGrupoFaturamentoRota,
			SistemaParametro sistemaParametro, Integer idFuncionalidadeIniciada)
			throws ControladorException {
		
		int idUnidadeIniciada = 0;
		try {
			
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(
				idFuncionalidadeIniciada, UnidadeProcessamento.ROTA, rota.getId());	
			
			FiltroFaturamentoGrupo filtro = new FiltroFaturamentoGrupo();
			filtro.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, idGrupoFaturamentoRota));
			FaturamentoGrupo grupoFaturamento = (FaturamentoGrupo) Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtro, FaturamentoGrupo.class.getName()));
						
			Integer mesAnoReferenciaGrupo = Util.formatarMesAnoComBarraParaAnoMes(grupoFaturamento.getMesAno());
			//Deleta o débito a cobrar de depreciação quando o batch for reiniciado.
			repositorioFaturamento.deletarDebitosACobrarDepreciacaoHidrometro(rota.getId(), mesAnoReferenciaGrupo);			
			Collection<Object[]> dadosImovel = this.repositorioFaturamento.pesquisarDadosGerarDebitoACobrarDepreciacaoHidrometro(rota.getId());
			
			if(dadosImovel != null){
				Iterator<Object[]> it = dadosImovel.iterator();

				while(it.hasNext()){					
					Object[] obj = it.next();

					Imovel imovel = new Imovel();

					//Id
					imovel.setId((Integer)obj[0]);

					//Localidade
					Localidade localidade = new Localidade();
					localidade.setId((Integer)obj[1]);
					imovel.setLocalidade(localidade);

					//Id Quadra
					Quadra quadra = new Quadra();
					quadra.setId((Integer)obj[2]);					
					//Numero da quadra
					quadra.setNumeroQuadra((Integer)obj[4]);
					imovel.setQuadra(quadra);

					//Codigo Setor Comercial
					SetorComercial setorComercial = new SetorComercial();
					setorComercial.setCodigo((Integer)obj[3]);
					imovel.setSetorComercial(setorComercial);
					
					//Lote
					imovel.setLote((Short)obj[5]);

					//SubLote
					imovel.setSubLote((Short)obj[6]);
										
					//Valor do debito
					BigDecimal valorDebito = (BigDecimal)obj[7];
					
					//Debito Tipo
					DebitoTipo debitoTipo = new DebitoTipo();
					debitoTipo.setId((Integer)obj[8]);
					
					//Numero de prestação de debitos
					Short numeroPrestacaoDebito = new Short("1");
					
					//Numero de prestaçoes de cobradas
					Short numeroPrestacaoCobradas = new Short("0");
					
					//Id do usuario Batch
					Usuario usuario = Usuario.USUARIO_BATCH;
					
					gerarDebitoACobrarParaDepreciacaoHidrometro(mesAnoReferenciaGrupo,imovel,numeroPrestacaoDebito,numeroPrestacaoCobradas,
					valorDebito,debitoTipo,usuario);
					
				}
			}
				
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
			
		} catch (Exception e) { 
			System.out.println("@@ ERRO NO GERAR DEBITO A COBRAR DEPRECIACAO HIDROMETRO");
			System.out.println("@@ ROTA ----> " + rota.getId());
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}
	

	/**
	 * [UC1687] - Gerar débito a cobrar de depreciação de Hidrômetro
	 * 
	 * @author Cesar Medeiros
	 * @date 23/06/2015
	 */
	protected void gerarDebitoACobrarParaDepreciacaoHidrometro(
			Integer mesAnoReferenciaGrupo,
			Imovel imovel,
			Short numeroPrestacaoDebito, 
			Short numeroPrestacaoCobradas, 
			BigDecimal valorDebito, 
			DebitoTipo debitoTipo, Usuario usuario) throws ControladorException {
		
		DebitoACobrar debitoACobrar;
		DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
		debitoCreditoSituacao.setId(DebitoCreditoSituacao.NORMAL);
		CobrancaForma cobrancaForma = new CobrancaForma();
		cobrancaForma.setId(CobrancaForma.COBRANCA_EM_CONTA);
		Object[] obterDebitoTipo;

		try {
			//obter os dados do tipo de debito informado
			obterDebitoTipo = repositorioFaturamento.obterDebitoTipo(debitoTipo.getId());
	
			//recupera qual o tipo de financiamento
			FinanciamentoTipo financiamentoTipo = new FinanciamentoTipo();
			if (obterDebitoTipo[0] != null) {
				financiamentoTipo.setId((Integer) obterDebitoTipo[0]);
			}
	
			//recupera qual o lançamento item contabil
			LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
			if (obterDebitoTipo[1] != null) {
				lancamentoItemContabil.setId((Integer) obterDebitoTipo[1]);
			}
	
			debitoACobrar = new DebitoACobrar();
			debitoACobrar.setImovel(imovel);
			debitoACobrar.setAnoMesCobrancaDebito(mesAnoReferenciaGrupo);
	        debitoACobrar.setAnoMesReferenciaContabil(mesAnoReferenciaGrupo);			
			debitoACobrar.setNumeroPrestacaoDebito(numeroPrestacaoDebito);
			debitoACobrar.setNumeroPrestacaoCobradas(numeroPrestacaoCobradas);
			debitoACobrar.setLocalidade(imovel.getLocalidade());
			debitoACobrar.setQuadra(imovel.getQuadra());
			debitoACobrar.setCodigoSetorComercial(imovel.getSetorComercial().getCodigo());
			debitoACobrar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
			debitoACobrar.setNumeroLote(imovel.getLote());
			debitoACobrar.setNumeroSubLote(imovel.getSubLote());
			debitoACobrar.setPercentualTaxaJurosFinanciamento(BigDecimal.ZERO);
			debitoACobrar.setRegistroAtendimento(null);
			debitoACobrar.setOrdemServico(null);
			debitoACobrar.setDebitoCreditoSituacaoAnterior(null);			
			debitoACobrar.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
			debitoACobrar.setCobrancaForma(cobrancaForma);
			debitoACobrar.setDebitoTipo(debitoTipo);
			debitoACobrar.setUltimaAlteracao(new Date());
			debitoACobrar.setGeracaoDebito(new Date());
			debitoACobrar.setAnoMesReferenciaDebito(mesAnoReferenciaGrupo);
			debitoACobrar.setFinanciamentoTipo(financiamentoTipo);
			debitoACobrar.setLancamentoItemContabil(lancamentoItemContabil);
			debitoACobrar.setValorDebito(valorDebito);
			debitoACobrar.setUsuario(usuario);
			
			ParcelamentoGrupo parcelamentoGrupo = new ParcelamentoGrupo();
			parcelamentoGrupo.setId(ParcelamentoGrupo.DOCUMENTOS_EMITIDOS);
			debitoACobrar.setParcelamentoGrupo(parcelamentoGrupo);
			
			DocumentoTipo documentoTipo = new DocumentoTipo();
			documentoTipo.setId(DocumentoTipo.DEBITO_A_COBRAR);
			debitoACobrar.setDocumentoTipo(documentoTipo);	
			
			FiltroDebitoACobrar filtro = new FiltroDebitoACobrar();
			filtro.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.IMOVEL_ID, debitoACobrar.getImovel().getId()));
			filtro.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.REFERENCIA_DEBITO, mesAnoReferenciaGrupo));
			filtro.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.DEBITO_TIPO_ID, DebitoACobrar.CONSERVACAO_DE_HIDROMETRO));
			DebitoACobrar debitoACobrarValidacao = (DebitoACobrar) Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtro, DebitoACobrar.class.getName()));
				
			if(debitoACobrarValidacao == null){
				
				// Inser o debito a cobrar geral e recupera o id
				DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
				debitoACobrarGeral.setIndicadorHistorico(ConstantesSistema.NAO);
				debitoACobrarGeral.setUltimaAlteracao(new Date());
				Integer idDebitoACobrarGeral = (Integer) getControladorUtil().inserir(debitoACobrarGeral);
				debitoACobrarGeral.setId(idDebitoACobrarGeral);
		
				debitoACobrar.setId(idDebitoACobrarGeral);
				debitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);
				
				// Recupera Categorias por Im?vel
				Collection<Categoria> colecaoCategoria = this
						.getControladorImovel().obterQuantidadeEconomiasCategoria(
								imovel);
				// Recupera Valores por Categorias
				Collection<BigDecimal> colecaoValoresCategorias = this
						.getControladorImovel().obterValorPorCategoria(
								colecaoCategoria,valorDebito);
				//Insere debito a cobrar
				Fachada.getInstancia().inserir(debitoACobrar);
				
				// Insere d?bito a cobrar por categoria
				inserirDebitoACobrarCategoria(colecaoCategoria,
						colecaoValoresCategorias, debitoACobrar);
			}
			
		} catch (Exception e) {
			throw new EJBException(e);
		}
	
	}    

	/**
	 * [UC1166] Gerar txt para impressão de contas no formato braille
	 * 
	 * @author Vivianne Sousa
	 * @date 02/07/2015
	 */
	public String obterMensagemContaBraille(EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro) throws ControladorException {
		String[] msgConta = obterMensagemConta3Partes(emitirContaHelper,sistemaParametro);
		String  msgContaBraille = "";
		if(!Util.isVazioOrNulo(msgConta)){
			for (String msg : msgConta) {
				if(msg != null){
					msgContaBraille = msgContaBraille + msg;
				}
			}
		}
		return msgContaBraille;
	}
	
	
	/**
	 * [UC0348] Emitir Contas
	 * 
	 * Verifica se motivo de revisão da conta é igual ao motivo de revisão da anormalidade estouro de consumo 
	 * 
	 * @author Vivianne Sousa
	 * @date 21/10/2015
	 */
	public boolean gerarMsgEstouroConsumo (Integer idConta)  throws ControladorException {
		try {
			Integer idContaRevisao = repositorioFaturamento.pesquisarMotivoRevisaoContaAnormalidadeConsumo(idConta);
			if(idContaRevisao != null){
				return true;
			}
			return false;
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * 
	 * @author Rodrigo Cabral
	 * @date 03/11/2015
	 * 
	 * @throws ErroRepositorioException
	 */
	public void gerarResumosFaturamentoPentaho(
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o início do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
					
				String usuarioServidor = repositorioUtil.obterValorParametro(ParametroSistema.USUARIO_SERVIDOR_PENTAHO);
				String ipServidorPentaho = repositorioUtil.obterValorParametro(ParametroSistema.IP_SERVIDOR_PENTAHO);
				String caminhoArquivoCarga = repositorioUtil.obterValorParametro(ParametroSistema.CAMINHO_ARQUIVO_CARGA_PENTAHO);
				
				
				System.out.println("Iniciou Gerar Resumos Faturamento Pentaho");
				
				Runtime.getRuntime().exec("ssh " + usuarioServidor + "@" + ipServidorPentaho + 
						" cd " + caminhoArquivoCarga + 
						" ./carga_faturamento.sh");
				
				System.out.println("Finalizou Gerar Resumos Faturamento Pentaho");
						
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
}
