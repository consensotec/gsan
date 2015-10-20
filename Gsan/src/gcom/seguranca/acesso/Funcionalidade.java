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
package gcom.seguranca.acesso;

import gcom.util.filtro.Filtro;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Funcionalidade extends TabelaAuxiliarAbreviada {
	private static final long serialVersionUID = 1L;
	public static final int CONSISTIR_LEITURAS_E_CALCULAR_CONSUMOS = 52;

	public static final int GERAR_DADOS_PARA_LEITURA = 185;

	public static final int FATURAR_GRUPO_FATURAMENTO = 63;

	public static final int EFETUAR_RATEIO_CONSUMO = 186;

	public static final int GERAR_TAXA_ENTREGA_CONTA_OUTRO_ENDERECO = 187;

	public static final int GERAR_DEBITOS_A_COBRAR_ACRESCIMOS_IMPONTUALIDADE = 188;

	public static final int GERAR_DADOS_DIARIOS_ARRECADACAO = 457;

	public static final int GERAR_ATIVIDADE_ACAO_COBRANCA = 539;

	public static final int ENCERRAR_ATIVIDADE_ACAO_COBRANCA = 457; // AJEITAR

	public static final int EMITIR_CONTAS = 64;

	public static final int GERAR_DEBITO_COBRAR_DOACAO = 547;

	public static final int CLASSIFICAR_PAGAMENTOS_DEVOLUCOES = 545;

	public static final int ENCERRAR_ARRECADACAO_MES = 546;

	public static final int ENCERRAR_FATURAMENTO_MES = 552;

	public static final int GERAR_RESUMO_LIGACOES_ECONOMIAS = 570;

	public static final int GERAR_RESUMO_SITUACAO_ESPECIAL_FATURAMENTO = 573;

	public static final int GERAR_RESUMO_ACOES_COBRANCA_CRONOGRAMA = 576;

	public static final int GERAR_RESUMO_ACOES_COBRANCA_CRONOGRAMA_ENCERRAR_OS = 1056;

	public static final int GERAR_RESUMO_ACOES_COBRANCA_EVENTUAL = 750;

	public static final int EMITIR_CONTAS_ORGAO_PUBLICO = 794;

	public static final int INSERIR_RESUMO_ACOES_COBRANCA_CRONOGRAMA = 672;

	public static final int INSERIR_RESUMO_ACOES_COBRANCA_EVENTUAL = 751;

	public static final int GERAR_RESUMO_PENDENCIA = 582;

	public static final int GERAR_RESUMO_ANORMALIDADES = 592;

	public static final int GERAR_RESUMO_SITUACAO_ESPECIAL_COBRANCA = 598;

	public static final int EMITIR_EXTRATO_CONSUMO_IMOVEL_CONDOMINIO = 611;

	public static final int GERAR_FATURA_CLIENTE_RESPONSAVEL = 629;

	public static final int GERAR_HISTORICO_PARA_ENCERRAR_ARRECADACAO_MES = 642;

	public static final int GERAR_HISTORICO_PARA_ENCERRAR_FATURAMENTO_MES = 647;

	public static final int DESFAZER_PARCELAMENTO_POR_ENTRADA_NAO_PAGA = 652;

	public static final int GERAR_HISTORICO_CONTA = 653;

	public static final int GERAR_RESUMO_INSTALACOES_HIDROMETROS = 670;

	public static final int GERAR_RESUMO_CONSUMO_AGUA = 671;

	public static final int GERAR_RESUMO_LEITURA_ANORMALIDADE = 673;

	public static final int GERAR_RESUMO_ARRECADACAO = 694;

	public static final int GERAR_RESUMO_PARCELAMENTO = 703;

	public static final int GERAR_RESUMO_FATURAMENTO_AGUA_ESGOTO = 695;

	public static final int GERAR_RESUMO_REFATURAMENTO = 910;

	public static final int GERAR_LANCAMENTOS_CONTABEIS_FATURAMENTO = 681;

	public static final int GERAR_LANCAMENTOS_CONTABEIS_ARRECADACAO = 700;

	public static final int GERAR_RESUMO_HISTOGRAMA_AGUA_ESGOTO = 706;

	public static final int GERAR_RESUMO_HIDROMETRO = 685;

	public static final int GERAR_RESUMO_REGISTRO_ATENDIMENTO = 720;

	public static final int EMITIR_BOLETIM_CADASTRO = 696;

	public static final int GERAR_RESUMO_DEVEDORES_DUVIDOSOS = 714;

	public static final int GERAR_LANCAMENTOS_CONTABEIS_DEVEDORES_DUVIDOSOS = 755;

	public static final int GERAR_RESUMO_METAS = 783;

	public static final int GERAR_RESUMO_METAS_ACUMULADO = 786;

	public static final int GERAR_ARQUIVO_TEXTO_PARA_LEITURISTA = 804;

	public static final int GERAR_RESUMO_COLETA_ESGOTO = 820;

	public static final int GERAR_CONTAS_A_RECEBER_CONTABIL = 847;

	public static final int GERAR_MOVIMENTO_EXCLUSAO_NEGATIVACAO_ONLINE = 854;

	public static final int GERAR_RESUMO_DIARIO_NEGATIVACAO = 875;

	public static final int EXECUTAR_COMANDO_DE_NEGATIVACAO = 876;

	public static final int EXECUTAR_COMANDO_DE_ENCERRAMENTO_RA = 894;

	public static final int GERAR_VALOR_VOLUMES_CONSUMIDOS_NAO_FATURADOS = 898;

	public static final int GERAR_RESUMO_INDICADORES_COMERCIALIZACAO = 901;

	public static final int GERAR_RESUMO_INDICADORES_MICROMEDICAO = 902;

	public static final int GERAR_RESUMO_INDICADORES_FATURAMENTO = 906;

	public static final int GERAR_RESUMO_INDICADORES_COBRANCA = 907;

	public static final int ATUALIZA_QUANTIDADE_PARCELA_PAGA_CONSECUTIVA_PARCELA_BONUS = 895;

	public static final int PRE_FATURAR_GRUPO_FATURAMENTO = 0;

	public static final int GERAR_RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE = 916;

	public static final int ATUALIZAR_LIGACAO_AGUA_LIGADO_ANALISE_PARA_LIGADO = 1082;

	public static final int GERAR_TALELAS_TEMPORARIAS_ATUALIZACAO_CADASTRAL = 1088;

	public static final int GERAR_ARQUIVO_TEXTO_ATUALIZACAO_CADASTRAL = 1152;

	public static final int GERAR_DIFERENCA_ARQUIVO_TEXTO_ATUALIZACAO_CADASTRAL = 1207;

	public static final int INSERIR_REGISTRO_ATENDIMENTO = 214;

	public static final int REATIVAR_REGISTRO_ATENDIMENTO = 403;

	public static final int GERAR_RESUMO_FATURAMENTO = 1103;

	public static final int INCLUIR_DEBITO_A_COBRAR_ENTRADA_PARCELAMENTO_NAO_PAGA = 1118;

	public static final int ATUALIZAR_DIFERENCA_ACUMULADA_NO_MES = 1119;

	public static final int ATUALIZAR_PAGAMENTOS_CONTAS_COBRANCA = 1154;

	public static final int GERAR_MOVIMENTO_CONTAS_COBRANCA_POR_EMPRESA = 1158;

	public static final int ATUALIZAR_NUMERO_EXECUCAO_RESUMO_NEGATIVACAO = 1168;

	public static final int GERAR_MOVIMENTO_EXCLUSAO_NEGATIVACAO = 1169;

	public static final int GERAR_RESUMO_REFATURAMENTO_OLAP = 1164;

	public static final int GERAR_MOVIMENTO_RETORNO_NEGATIVACAO = 892;

	public static final int GERAR_CREDITO_SITUACAO_ESPECIAL_FATURAMENTO = 1183;

	public static final int GERAR_MOVIMENTO_EXTENSAO_CONTAS_COBRANCA_POR_EMPRESA = 1189;

	public static final int ATUALIZAR_AUTOS_INFRACAO_PRAZO_RECURSO_VENCIDO = 1208;

	public static final int MOVIMENTO_HIDROMETRO = 145;

	public static final int GERAR_CARTAS_CAMPANHA_SOLIDARIEDADE_CRIANCA_PARA_NEGOCIACAO_A_VISTA = 1216;

	public static final int EMITIR_BOLETOS = 1219;

	public static final int RETIFICAR_CONJUNTO_CONTA = 1160;

	public static final int GERAR_BONUS_TARIFA_SOCIAL = 1234;

	public static final int EXCLUIR_IMOVEIS_DA_TARIFA_SOCIAL = 1236;

	public static final int GERAR_RESUMO_NEGATIVACAO = 1253;

	public static final int ACOMPANHAR_PAGAMENTO_DO_PARCELAMENTO = 1254;

	public static final int GERAR_CARTAS_DE_FINAL_DE_ANO = 1255;

	public static final int GERAR_RESUMO_PENDENCIA_SEM_QUADRA = 1257;

	public static final int SUPENDER_IMOVEL_EM_PROGRAMA_ESPECIAL = 1264;

	public static final int INSERIR_PAGAMENTOS_FATURAS_ESPECIAIS = 1265;

	public static final int GERAR_RESUMO_SIMULACAO_FATURAMENTO = 1273;

	public static final int GERAR_RESUMO_RECEITA = 1310;

	public static final int GERAR_LANCAMENTOS_CONTABEIS_AVISO_BANCARIO = 1291;

	public static final int GERAR_RESUMO_DOCUMENTOS_A_RECEBER = 1298;

	public static final int DETERMINAR_CONFIRMACAO_DA_NEGATIVACAO = 1302;

	public static final int BATCH_EMITIR_ORDEM_FISCALIZAO = 1295;

	public static final int BATCH_GERAR_ARQUIVO_ORDEM_FISCALIZAO = 1299;

	public static final int ENVIO_EMAIL_CONTA_PARA_CLIENTE = 1297;

	public static final int GERAR_RESUMO_PENDENCIA_POR_ANO = 1304;

	public static final int BATCH_ATUALIZAR_CODIGO_DEBITO_AUTOMATICO = 1308;

	public static final int GERAR_DECLARACAO_QUITACAO_ANUAL_DEBITOS = 1303;

	public static final int GERAR_ARQUIVO_TEXTO_DECLARACAO_QUITACAO_ANUAL_DEBITOS = 1305;

	public static final int ATUALIZAR_COMANDO_ATIVIDADE_ACAO_COBRANCA = 1311;

	public static final int EMITIR_DOCUMENTO_COBRANCA = 1312;

	public static final int GERAR_RESUMO_LIGACOES_ECONOMIAS_POR_ANO = 1317;

	public static final int GERAR_RESUMO_FATURAMENTO_POR_ANO = 1328;

	public static final int GERAR_RESUMO_INDICADORES_MICROMEDICAO_POR_ANO = 1322;

	public static final int GERAR_RESUMO_CONSUMO_AGUA_POR_ANO = 1327;

	public static final int GERAR_RESUMO_ARRECADACAO_POR_ANO = 1329;

	public static final int GERAR_RESUMO_COLETA_ESGOTO_POR_ANO = 1338;

	public static final int GERAR_RESUMO_REGISTRO_ATENDIMENTO_POR_ANO = 1339;

	public static final int GERAR_RESUMO_INSTALACOES_HIDROMETROS_POR_ANO = 1342;

	public static final int GERAR_RESUMO_PARCELAMENTO_POR_ANO = 1343;

	public static final int GERAR_TAXA_PERCENTUAL_TARIFA_MINIMA_CORTADO = 1354;

	public static final int GERAR_RESUMO_REFATURAMENTO_NOVO = 1346;

	public static final int GERAR_PRESCREVER_DEBITOS_DE_IMOVEIS = 1350;

	public static final int ALTERAR_INSCRICOES_IMOVEIS = 1352;

	public static final int VERIFICAR_FATURAMENTO_IMOVEIS_CORTADOS = 1357;

	public static final int GERAR_GUIA_PAGAMENTO_POR_CLIENTE_RESUMO_PENDENCIA = 1397;

	public static final int ATUALIZAR_DADOS_CLIENTES_PROMAIS = 1368;

	public static final int GERAR_RESUMO_HISTOGRAMA_AGUA_ESGOTO_SEM_QUADRA = 1383;

	public static final int INSERIR_IMOVEL = 6;

	public static final int MANTER_IMOVEL = 7;

	public static final int RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL = 1387;

	public static final int APAGAR_RESUMO_DEVEDORES_DUVIDOSOS = 1406;

	public static final int PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS = 1404;

	public static final int ATUALIZAR_RESUMO_DEVEDORES_DUVIDOSOS = 1407;

	public static final int ENCERRAR_ORDENS_SERVICO_ACAO_COBRANCA = 1428;

	public static final int AUTOMATIZAR_PERFIS_DE_GRANDES_CONSUMIDORES = 1444;

	public static final int GERAR_RA_OS_ANORMALIDADE_CONSUMO = 1446;

	public static final int PROCESSAR_COMANDO_GERADO = 1456;

	public static final int GERAR_CARTA_TARIFA_SOCIAL = 1457;

	public static final int SELECIONAR_COMANDO_RETIRAR_IMOVEL_TARIFA_SOCIAL = 1459;

	public static final int GERAR_TXT_IMPRESSAO_CONTAS_FORMATO_BRAILLE = 1470;

	public static final int INSERIR_CONTRATO_PARCELAMENTO_POR_CLIENTE = 1460;

	public static final int MANTER_CONTRATO_PARCELAMENTO_POR_CLIENTE = 1471;

	public static final int ENCERRAR_COMANDO_DE_COBRANCA_POR_EMPRESA = 1474;

	public static final int MOVIMENTAR_ORDENS_DE_SERVICO_DE_COBRANCA_POR_RESULTADO = 1476;

	public static final int ATUALIZAR_CONTRATO_PARCELAMENTO_POR_CLIENTE = 1493;

	public static final int EMISSAO_ORDENS_SELETIVAS = 827;

	public static final int RECEPCIONAR_ARQUIVO_TXT_ENCERRAMENTO_OS_COBRANCA = 1495;

	public static final int GERAR_ARQUIVO_TXT_OS_CONTAS_PAGAS_PARCELADAS = 1500;

	public static final int MOVIMENTAR_OS_SELETIVA_INSPECAO_ANORMALIDADE = 1508;

	public static final int ENCERRAR_COMANDO_OS_SELETIVA_INSPECAO_ANORMALIDADE = 1512;

	public static final int PROGRAMACAO_AUTO_ROTEIRO_ACOMPANHAMENTO_OS = 1515;

	public static final int GERAR_DADOS_ARQUIVO_ACOMPANHAMENTO_SERVICO = 1518;

	public static final int PROCESSAR_ENCERRAMENTO_OS_FISCALIZACAO_DECURSO_PRAZO = 1522;

	public static final int SUSPENDER_LEITURA_PARA_IMOVEL_COM_HIDROMETRO_RETIRADO = 1529;

	public static final int SUSPENDER_LEITURA_PARA_IMOVEL_COM_CONSUMO_REAL_NAO_SUPERIOR_A_10 = 1530;

	public static final int ENCERRAR_OS_VISITA_COBRANCA = 1541;

	public static final int CONSULTAR_ORDENS_DE_SERVICO_DO_ARQUIVO_TEXTO = 1536;

	public static final int GERAR_BOLETIM_COBRANCA_POR_RESULTADO = 1554;

	public static final int GERAR_CREDITO_A_REALIZAR_CONTRATO_DEMANDA_RESIDENCIAL = 1545;

	public static final int CANCELAR_GRUPO_FATURAMENTO = 1546;

	public static final int GERAR_DEBITO_A_COBRAR_CONTRATO_DEMANDA_RESIDENCIAL = 1559;

	public static final int GERAR_LANCAMENTOS_CONTABEIS_VOLUMES_CONSUMIDOS_NAO_FATURADOS = 1561;

	public static final int RETIRAR_IMOVEIS_CONTAS_EMPRESAS_COBRANCA = 1563;

	public static final int PARALISAR_FATURAMENTO = 1569;

	public static final int BATCH_GERAR_FATURAMENTO_BRUTO_LIQUIDO = 1570;

	public static final int INCLUIR_CONTAS_EM_COBRANCA = 1571;

	public static final int BATCH_GERAR_RESUMO_ARRECADACAO_ANALITICO_DADOS = 1580;

	public static final int BATCH_GERAR_RESUMO_ARRECADACAO_ANALITICO_TOTAIS_1 = 1587;

	public static final int BATCH_GERAR_RESUMO_ARRECADACAO_ANALITICO_TOTAIS_2 = 1588;

	public static final int BATCH_EXECUTAR_CARGA_ARRECADACAO_GERENCIAL_POR_ANO = 1611;

	public static final int EXECUTAR_CARGA_REGISTRO_ATENDIMENTO_POR_ANO = 1612;

	public static final int EXECUTAR_CARGA_RESUMO_FATURAMENTO_POR_ANO = 1615;

	public static final int EXECUTAR_CARGA_INDICADORES_DESEMPENHO_FATURAMENTO_POR_ANO = 1616;

	public static final int EXECUTAR_CARGA_COLETA_ESGOTO_POR_ANO = 1617;

	public static final int EXECUTAR_CARGA_INDICADORES_DESEMPENHO_FATURAMENTO_VARIOS_ANOS = 1618;

	public static final int EXECUTAR_CARGA_CONSUMO_AGUA_POR_ANO = 1619;

	public static final int EXECUTAR_CARGA_LEITURA_ANORMALIDADE_POR_ANO = 1620;

	public static final int EXECUTAR_CARGA_INDICADORES_DESEMPENHO_MICROMEDICAO_VARIOS_ANOS = 1621;

	public static final int EXECUTAR_CARGA_INDICADORES_DESEMPENHO_MICROMEDICAO_POR_ANO = 1622;

	public static final int EXECUTAR_CARGA_LIGACOES_ECONOMIAS_POR_ANO = 1623;

	public static final int EXECUTAR_CARGA_INDICADOR_LIGACAO_ECONOMIA_POR_ANO = 1624;

	public static final int EXECUTAR_CARGA_RESUMO_PARCELAMENTO_POR_ANO = 1626;

	public static final int EXECUTAR_CARGA_RESUMO_PENDENCIA_POR_ANO = 1627;

	public static final int EXECUTAR_CARGA_PENDENCIA_SEM_QUADRA_VARIOS_ANOS = 1628;

	public static final int GERAR_RESUMO_PENDENCIA_BD = 1658;

	public static final int RETIRAR_CONTAS_REVISAO_EXTRATO_ENTRADA_PARCELAMENTO = 1665;

	public static final int GERAR_FATURA_PROGRAMA_ESPECIAL = 1676;

	public static final int RECEITA_LIQUIDA_INDIRETA_DE_AGUA_E_ESGOTO = 1671;

	public static final int BATCH_GERAR_RESUMO_FATURAMENTO_ANALITICO_PARTE_1 = 1605;

	public static final int BATCH_GERAR_RESUMO_FATURAMENTO_ANALITICO_PARTE_2 = 1606;

	public static final int BATCH_GERAR_RESUMO_FATURAMENTO_ANALITICO_TOTAIS = 1607;

	public static final int BATCH_GERAR_CREDITO_REALIZAR_PAGAMENTO_DUPLICIDADE_VIVA_AGUA = 1687;

	public static final int GERAR_RELATORIO_IMPOSTOS_CLIENTE_RESPONSAVEL = 1217;

	public static final int AUTOMATIZAR_CARGA_MENSAL_PENTAHO = 1702;

	public static final int GERAR_ROTEIRO_DISPOSITIVO_MOVEL = 1703;

	public static final int BATCH_GERAR_CONTAS_A_RECEBER_CONTABIL_ANALITICO_TABELA_AUXILIAR = 1712;

	public static final int BATCH_GERAR_CONTAS_A_RECEBER_CONTABIL_ANALITICO = 1713;

	public static final int BATCH_GERAR_CONTAS_A_RECEBER_CONTABIL_SINTETICO = 1714;

	public static final int GERAR_UN_RESUMO_ARRECADACAO_BD = 1749;

	public static final int BATCH_BAIXA_AUTOMATICA_PAGAMENTOS_NAO_CLASSIFICADOS = 1781;

	public static final int GERAR_ARQUIVO_BANCOS_CARTEIRA_17 = 1817;

	public static final int REGISTRAR_MOVIMENTO_ARRECADADORES = 1822;

	public static final int RETIFICAR_CONTAS_INDEVIDAS_CAERN = 9999;

	public static final int BATCH_PROCESSAR_AMORTIZACAO_DIVIDA_ATIVA_ARRECADACAO = 1829;

	public static final int BATCH_PROCESSAR_AMORTIZACAO_DIVIDA_ATIVA_FATURAMENTO = 1830;

	public static final int GERAR_DIVIDA_ATIVA = 1828;

	public static final int GERAR_RESUMO_DIVIDA_ATIVA_ANUAL = 1832;

	public static final int PREPARAR_DADOS_SMS_EMAIL_COBRANCA_CONTA = 1852;

	public static final int GERAR_RELATORIO_CONTAS = 1855;

	public static final int RETORNO_SMS_EM_LOTE = 1856;

	public static final int BATCH_ENVIAR_EMAIL_COBRANCA_FATURAMENTO = 1853;

	public static final int BATCH_TRANSMITIR_SMS_EM_LOTE_COBRANCA_FATURAMENTO = 1854;

	public static final int PREPARAR_DADOS_SMS_EMAIL_ACAO_COBRANCA = 1857;

	public static final int BATCH_ATUALIZAR_DADOS_CADASTRAIS_IMOVEL_RECADASTRAMENTO = 1592;

	public static final int BATCH_RESUMO_ARRECADACAO_ATUALIZA_DADOS = 1879;
	
	public static final int BATCH_RESUMO_COBRANCA_ATUALIZA_DADOS = 1880;
	
	public static final int BATCH_RESUMO_FATURAMENTO_ATUALIZA_DADOS = 1881;
	
	public static final int BATCH_RESUMO_MICROMEDICAO_ATUALIZA_DADOS = 1882;
	
	public static final int BATCH_RESUMO_ATENDIMENTO_PUBLICO_ATUALIZA_DADOS = 1883;
	
	public static final int BATCH_RESUMO_PARCELAMENTO_ATUALIZA_DADOS = 1884;
	
	public static final int BATCH_RESUMO_CADASTRO_ATUALIZA_DADOS = 1885;
	
	public static final int BATCH_RELIGAR_IMOVEIS_CORTADOS_LEITURA_REAL_CONSUMO = 1890;
	
	public static final int BATCH_ATUALIZAR_PERFIL_IMOVEL_LOTE = 1900;

	public static final int BATCH_GERAR_DEBITO_A_COBRAR_DEPRECIACAO_HIDROMETRO = 1904;

	public static final int BATCH_GERAR_DADOS_FINANCEIRO_ATUALIZACAO_CADASTRAL = 1905;
	
	public static final int BATCH_GERAR_RESUMO_DADOS_FINANCEIROS_ATUALIZACAO_CADASTRAL = 1906;
	

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private String caminhoMenu;

	/** nullable persistent field */
	private String caminhoUrl;

	/** nullable persistent field */
	private Short indicadorPontoEntrada;

	private Long numeroOrdemMenu;

	private Short indicadorOlap;

	private Short indicadorNovaJanela;

	/** persistent field */
	private Modulo modulo;

	private FuncionalidadeCategoria funcionalidadeCategoria;

	private String caminhoUrlHelp;

	private Set funcionalidadeDependencias = null;

	private Set funcionalidadeDependenciasByFncdIddependencia = null;

	private Set operacoes = null;

	/** full constructor */
	public Funcionalidade(String descricao, String descricaoAbreviada,
			Date ultimaAlteracao, String caminhoMenu, String caminhoUrl,
			Short indicadorPontoEntrada, gcom.seguranca.acesso.Modulo modulo) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.ultimaAlteracao = ultimaAlteracao;
		this.caminhoMenu = caminhoMenu;
		this.caminhoUrl = caminhoUrl;
		this.indicadorPontoEntrada = indicadorPontoEntrada;
		this.modulo = modulo;
	}

	/** full constructor */
	public Funcionalidade(String descricao, String descricaoAbreviada,
			Date ultimaAlteracao, String caminhoMenu, String caminhoUrl,
			Short indicadorPontoEntrada, gcom.seguranca.acesso.Modulo modulo,
			Set funcionalidadeDependencias,
			Set funcionalidadeDependenciasByFncdIddependencia, Set operacoes) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.ultimaAlteracao = ultimaAlteracao;
		this.caminhoMenu = caminhoMenu;
		this.caminhoUrl = caminhoUrl;
		this.indicadorPontoEntrada = indicadorPontoEntrada;
		this.modulo = modulo;
		this.funcionalidadeDependencias = funcionalidadeDependencias;
		this.funcionalidadeDependenciasByFncdIddependencia = funcionalidadeDependenciasByFncdIddependencia;
		this.operacoes = operacoes;
	}

	/** default constructor */
	public Funcionalidade() {
	}

	/**
	 * @return Retorna o campo operacoes.
	 */
	public Set getOperacoes() {
		return operacoes;
	}

	/**
	 * @param operacoes
	 *            O operacoes a ser setado.
	 */
	public void setOperacoes(Set operacoes) {
		this.operacoes = operacoes;
	}

	/** minimal constructor */
	public Funcionalidade(gcom.seguranca.acesso.Modulo modulo) {
		this.modulo = modulo;
	}

	/**
	 * @return Retorna o campo funcionalidadeDependencias.
	 */
	public Set getFuncionalidadeDependencias() {
		return funcionalidadeDependencias;
	}

	/**
	 * @param funcionalidadeDependencias
	 *            O funcionalidadeDependencias a ser setado.
	 */
	public void setFuncionalidadeDependencias(Set funcionalidadeDependencias) {
		this.funcionalidadeDependencias = funcionalidadeDependencias;
	}

	/**
	 * @return Retorna o campo funcionalidadeDependenciasByFncdIddependencia.
	 */
	public Set getFuncionalidadeDependenciasByFncdIddependencia() {
		return funcionalidadeDependenciasByFncdIddependencia;
	}

	/**
	 * @param funcionalidadeDependenciasByFncdIddependencia
	 *            O funcionalidadeDependenciasByFncdIddependencia a ser setado.
	 */
	public void setFuncionalidadeDependenciasByFncdIddependencia(
			Set funcionalidadeDependenciasByFncdIddependencia) {
		this.funcionalidadeDependenciasByFncdIddependencia = funcionalidadeDependenciasByFncdIddependencia;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getCaminhoMenu() {
		return this.caminhoMenu;
	}

	public void setCaminhoMenu(String caminhoMenu) {
		this.caminhoMenu = caminhoMenu;
	}

	public String getCaminhoUrl() {
		return this.caminhoUrl;
	}

	public void setCaminhoUrl(String caminhoUrl) {
		this.caminhoUrl = caminhoUrl;
	}

	public Short getIndicadorPontoEntrada() {
		return this.indicadorPontoEntrada;
	}

	public void setIndicadorPontoEntrada(Short indicadorPontoEntrada) {
		this.indicadorPontoEntrada = indicadorPontoEntrada;
	}

	public gcom.seguranca.acesso.Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(gcom.seguranca.acesso.Modulo modulo) {
		this.modulo = modulo;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Long getNumeroOrdemMenu() {
		return numeroOrdemMenu;
	}

	public void setNumeroOrdemMenu(Long numeroOrdemMenu) {
		this.numeroOrdemMenu = numeroOrdemMenu;
	}

	public Short getIndicadorNovaJanela() {
		return indicadorNovaJanela;
	}

	public void setIndicadorNovaJanela(Short indicadorNovaJanela) {
		this.indicadorNovaJanela = indicadorNovaJanela;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	public Short getIndicadorOlap() {
		return indicadorOlap;
	}

	public void setIndicadorOlap(Short indicadorOlap) {
		this.indicadorOlap = indicadorOlap;
	}

	public FuncionalidadeCategoria getFuncionalidadeCategoria() {
		return funcionalidadeCategoria;
	}

	public void setFuncionalidadeCategoria(
			FuncionalidadeCategoria funcionalidadeCategoria) {
		this.funcionalidadeCategoria = funcionalidadeCategoria;
	}

	public String getCaminhoUrlHelp() {
		return caminhoUrlHelp;
	}

	public void setCaminhoUrlHelp(String caminhoUrlHelp) {
		this.caminhoUrlHelp = caminhoUrlHelp;
	}
}
