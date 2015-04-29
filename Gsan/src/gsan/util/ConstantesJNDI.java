
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
package gsan.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * ConstantesJDNI e uma classe que contem os nomes contidos no JNDI
 * 
 * @author Administrador
 */

public class ConstantesJNDI {

	/**
	 * Description of the Field
	 */

	public final static String NOME_ARQUIVO_PROPRIEDADES = "constantes_jndi.properties";

	// UTIL

	/**
	 * Description of the Field
	 */

	public static String FUNCOES_EJB = "";

	/**
	 * Description of the Field
	 */

	public static String CONTROLADOR_TABELA_AUXILIAR_SEJB = "";

	/**
	 * Description of the Field
	 */
	public static String CONTROLADOR_UTIL_SEJB = "";

	/**
	 * Description of the Field
	 */
	public static String CONTROLADOR_ENDERECO_SEJB = "";

	/**
	 * Description of the Field
	 */
	public static String CONTROLADOR_CLIENTE_SEJB = "";

	/**
	 * Description of the Field
	 */
	public static String CONTROLADOR_IMOVEL_SEJB = "";
	
	public static String CONTROLADOR_ATUALIZACAO_CADASTRAL_SEJB = "";

	public static String CONTROLADOR_MICROMEDICAO_SEJB = "";
	
	public static String CONTROLADOR_COBRANCA_SEJB = "";
	
	public static String CONTROLADOR_CONTRATO_PARCELAMENTO_SEJB = "";

	public static String CONTROLADOR_FINANCEIRO_SEJB = "";

	public static String CONTROLADOR_LOCALIDADE_SEJB = "";

	public static String CONTROLADOR_GEOGRAFICO_SEJB = "";

	public static String CONTROLADOR_FATURAMENTO_SEJB = "";

	public static String CONTROLADOR_TARIFA_SOCIAL_SEJB = "";

	public static String QUEUE_CONTROLADOR_FATURAMENTO_MDB = "";

	public static String QUEUE_CONTROLADOR_ARRECADACAO_MDB = "";

	public static String QUEUE_CONTROLADOR_MICROMEDICAO_MDB = "";

	public static String CONTROLADOR_ARRECADACAO_SEJB = "";

	public static String CONTROLADOR_ACESSO_SEJB = "";

	public static String CONTROLADOR_TRANSACAO_SEJB = "";

	public static String CONTROLADOR_GERENCIAL_CADASTRO_SEJB = "";

	public static String CONTROLADOR_GERENCIAL_FATURAMENTO_SEJB = "";

	public static String CONTROLADOR_GERENCIAL_COBRANCA_SEJB = "";

	public static String CONTROLADOR_USUARIO_SEJB = "";

	public static String CONTROLADOR_GERENCIAL_SEJB = "";

	public static String CONTROLADOR_GERENCIAL_MICROMEDICAO_SEJB = "";

	public static String CONTROLADOR_GERENCIAL_ARRECADACAO_SEJB = "";

	public static String CONTROLADOR_ATENDIMENTO_PUBLICO_SEJB = "";

	public static String CONTROLADOR_UNIDADE_SEJB = "";

	public static String CONTROLADOR_REGISTRO_ATENDIMENTO_SEJB = "";

	public static String CONTROLADOR_ORDEM_SERVICO_SEJB = "";

	public static String CONSISTIR_LEITURAS_CALCULAR_CONSUMOS_MDB = "";

	public static String CONTROLADOR_BATCH_SEJB = "";

	public static String CONTROLADOR_LIGACAO_ESGOTO_SEJB = "";

	public static String CONTROLADOR_LIGACAO_AGUA_SEJB = "";

	public static String CONTROLADOR_PERMISSAO_ESPECIAL_SEJB = "";

	public static String CONTROLADOR_OPERACIONAL_SEJB = "";
	
	public static String CONTROLADOR_SPC_SERASA_SEJB = "";

	public static String BATCH_CONSISTIR_LEITURAS_CALCULAR_CONSUMOS_MDB = "";

	public static String BATCH_GERAR_DADOS_PARA_LEITURA_MDB = "";

	public static String BATCH_EFETUAR_RATEIO_CONSUMO_MDB = "";

	public static String BATCH_FATURAR_GRUPO_FATURAMENTO_MDB = "";

	public static String BATCH_EMITIR_CONTAS_MDB = "";

	public static String BATCH_GERAR_DEBITO_A_COBRAR_DE_ACRESCIMOS_POR_IMPONTUALIDADE_MDB = "";

	public static String BATCH_GERAR_TAXA_ENTREGA_OUTRO_ENDERECO_MDB = "";

	public static String BATCH_GERAR_DADOS_DIARIOS_ARRECADACAO_MDB = "";

	public static String BATCH_GERAR_ATIVIDADE_ACAO_COBRANCA_MDB = "";

	public static String BATCH_EMITIR_BOLETIM_CADASTRO_MDB = "";

	public static String CONTROLADOR_CADASTRO_SEJB = "";

	public static String BATCH_CLASSIFICAR_PAGAMENTOS_DEVOLUCOES_MDB = "";

	public static String BATCH_ENCERRAR_ARRECADACAO_MES_MDB = "";

	public static String BATCH_GERAR_DEBITOS_COBRAR_DOACAO_MDB = "";

	public static String BATCH_ENCERRAR_FATURAMENTO_MES_MDB = "";

	public static String BATCH_GERAR_RESUMO_SITUACAO_ESPECIAL_FATURAMENTO_MDB = "";

	public static String BATCH_GERAR_RESUMO_LIGACOES_ECONOMIAS_MDB = "";

	public static String BATCH_GERAR_RESUMO_ACOES_COBRANCA_CRONOGRAMA_MDB = "";

	public static String BATCH_INSERIR_RESUMO_ACAO_COBRANCA_CRONOGRAMA_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_ACAO_COBRANCA_CRONOGRAMA_ENCERRAR_OS_MDB = "";
	
	public static String BATCH_INSERIR_RESUMO_ACAO_COBRANCA_EVENTUAL_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_ACOES_COBRANCA_EVENTUAL_MDB = "";
	
	public static String BATCH_EMITIR_CONTAS_ORGAO_PUBLICO = "";

	public static String BATCH_GERAR_RESUMO_ANORMALIDADES_MDB = "";

	public static String BATCH_GERAR_RESUMO_SITUACAO_ESPECIAL_COBRANCA_MDB = "";

	public static String BATCH_EMITIR_EXTRATO_CONSUMO_IMOVEL_CONDOMINIO_MDB = "";

	public static String BATCH_GERAR_FATURA_CLIENTE_RESPONSAVEL_MDB = "";

	public static String BATCH_GERAR_HISTORICO_PARA_ENCERRAR_ARRECADACAO_MES_MDB = "";

	public static String BATCH_GERAR_HISTORICO_PARA_ENCERRAR_FATURAMENTO_MES_MDB = "";

	public static String BATCH_DESFAZER_PARCELAMENTO_POR_ENTRADA_NAO_PAGA_MDB = "";

	public static String BATCH_GERAR_HISTORICO_CONTA_MDB = "";

	public static String BATCH_GERAR_RESUMO_INSTALACOES_HIDROMETROS_MDB = "";

	public static String BATCH_GERAR_RESUMO_LEITURA_ANORMALIDADE_MDB = "";

	public static String BATCH_GERAR_RESUMO_ARRECADACAO_MDB = "";

	public static String BATCH_GERAR_RESUMO_PARCELAMENTO_MDB = "";

	public static String CONTROLADOR_GERENCIAL_IMOVEL_SEJB = "";

	public static String BATCH_GERAR_LANCAMENTOS_CONTABEIS_FATURAMENTO_MDB = "";

	public static String BATCH_GERAR_LANCAMENTOS_CONTABEIS_ARRECADACAO_MDB = "";
	
	public static String BATCH_GERAR_LANCAMENTOS_CONTABEIS_AVISOS_BANCARIOS_MDB = "";

	public static String BATCH_GERAR_RESUMO_FATURAMENTO_AGUA_ESGOTO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_REFATURAMENTO_MDB = "";
    
    public static String BATCH_GERAR_RESUMO_REFATURAMENTO_OLAP_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_DEVEDORES_DUVIDOSOS_MDB = "";
	
	public static String BATCH_GERAR_LANCAMENTOS_CONTABEIS_DEVEDORES_DUVIDOSOS_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_PENDENCIA = "";
	
	public static String BATCH_GERAR_ARQUIVO_TEXTO_PARA_LEITURISTA = "";
	
	public static String BATCH_EXECUTAR_COMANDO_NEGATIVACAO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_DIARIO_NEGATIVACAO_MDB = "";

	public static String BATCH_ATUALIZAR_LIGACAO_AGUA_LIGADO_ANALISE_PARA_LIGADO_MDB = "";
	
	public static String BATCH_ATUALIZAR_NUMERO_EXECUCAO_RESUMO_NEGATIVACAO_MDB = "";
	
	public static String BATCH_GERAR_MOVIMENTO_EXCLUSAO_NEGATIVACAO_MDB = "";
	
	public static String BATCH_GERAR_MOVIMENTO_RETORNO_NEGATIVACAO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_FATURAMENTO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_NEGATIVACAO_MDB = "";
	
	public static String BATCH_ACOMPANHAR_PAGAMENTO_DO_PARCELAMENTO_MDB = "";
	
	public static String BATCH_GERAR_CARTAS_DE_FINAL_DE_ANO_MDB = "";
	
	public static String BATCH_EMITIR_CARTAS_DE_FINAL_DE_ANO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_RECEITA = "";
	
	public static String BATCH_GERAR_GUIA_PAGAMENTO_POR_CLIENTE_RESUMO_PENDENCIA = "";
	
	public static String BATCH_GERAR_BOLETIM_COBRANCA_POR_RESULTADO = "";
	
	public static String BATCH_GERAR_CREDITO_A_REALIZAR_CONTRATO_DEMANDA_RESIDENCIAL_MDB = "";
	
	public static String BATCH_GERAR_DEBITO_A_COBRAR_CONTRATO_DEMANDA_RESIDENCIAL_MDB = "";
	
	public static String BATCH_GERAR_CONTAS_A_RECEBER_CONTABIL_ANALITICO_TABELA_AUXILIAR = "";
	
	public static String BATCH_GERAR_CONTAS_A_RECEBER_CONTABIL_ANALITICO = "";
	
	public static String BATCH_GERAR_CONTAS_A_RECEBER_CONTABIL_SINTETICO = "";

	// ESQUEMA DOS CONTROLADORES
	// =====================================================================================================
	public static String CONTROLADOR_FATURAMENTO_COMPESA_SEJB = "";

	public static String CONTROLADOR_FATURAMENTO_CAERN_SEJB = "";

	public static String CONTROLADOR_FATURAMENTO_CAER_SEJB = "";
	
	public static String CONTROLADOR_FATURAMENTO_CAEMA_SEJB = "";
	
	public static String CONTROLADOR_FATURAMENTO_COSANPA_SEJB = "";
	
	public static String CONTROLADOR_FATURAMENTO_JUAZEIRO_SEJB = "";
	
	public static String CONTROLADOR_FATURAMENTO_COSAMA_SEJB = "";

	// =====================================================================================================

	// ESQUEMA DOS CONTROLADORES
	// =====================================================================================================
	public static String CONTROLADOR_MICROMEDICAO_COMPESA_SEJB = "";

	public static String CONTROLADOR_MICROMEDICAO_CAERN_SEJB = "";

	public static String CONTROLADOR_MICROMEDICAO_CAER_SEJB = "";
	
	public static String CONTROLADOR_MICROMEDICAO_CAEMA_SEJB = "";
	
	public static String CONTROLADOR_MICROMEDICAO_JUAZEIRO_SEJB = "";
	
	public static String CONTROLADOR_MICROMEDICAO_COSAMA_SEJB = "";

	// =====================================================================================================

	// ESQUEMA DOS CONTROLADORES
	// =====================================================================================================
	public static String CONTROLADOR_ARRECADACAO_COMPESA_SEJB = "";

	public static String CONTROLADOR_ARRECADACAO_CAERN_SEJB = "";

	public static String CONTROLADOR_ARRECADACAO_CAER_SEJB = "";
	
	public static String CONTROLADOR_ARRECADACAO_CAEMA_SEJB = "";
	
	public static String CONTROLADOR_ARRECADACAO_COSANPA_SEJB = "";
	
	public static String CONTROLADOR_ARRECADACAO_JUAZEIRO_SEJB = "";
	
	public static String CONTROLADOR_ARRECADACAO_COSAMA_SEJB = "";

	// =====================================================================================================
	
	//ESQUEMA DOS CONTROLADORES
	// =====================================================================================================
	public static String CONTROLADOR_COBRANCA_COMPESA_SEJB = "";

	public static String CONTROLADOR_COBRANCA_CAERN_SEJB = "";

	public static String CONTROLADOR_COBRANCA_CAER_SEJB = "";
	
	public static String CONTROLADOR_COBRANCA_CAEMA_SEJB = "";
	
	public static String CONTROLADOR_COBRANCA_COSANPA_SEJB = "";
	
	public static String CONTROLADOR_COBRANCA_JUAZEIRO_SEJB = "";
	
	public static String CONTROLADOR_COBRANCA_COSAMA_SEJB = "";

	// =====================================================================================================
	
	//ESQUEMA DOS CONTROLADORES
	// =====================================================================================================
	public static String CONTROLADOR_FINANCEIRO_COMPESA_SEJB = "";

	public static String CONTROLADOR_FINANCEIRO_CAERN_SEJB = "";

	public static String CONTROLADOR_FINANCEIRO_CAER_SEJB = "";
	
	public static String CONTROLADOR_FINANCEIRO_CAEMA_SEJB = "";
	
	public static String CONTROLADOR_FINANCEIRO_COSANPA_SEJB = "";
	
	public static String CONTROLADOR_FINANCEIRO_JUAZEIRO_SEJB = "";
	
	public static String CONTROLADOR_FINANCEIRO_COSAMA_SEJB = "";

	// =====================================================================================================
	
	//	ESQUEMA DOS CONTROLADORES
	// =====================================================================================================
	public static String CONTROLADOR_CADASTRO_COMPESA_SEJB = "";

	public static String CONTROLADOR_CADASTRO_CAERN_SEJB = "";

	public static String CONTROLADOR_CADASTRO_CAER_SEJB = "";
	
	public static String CONTROLADOR_CADASTRO_CAEMA_SEJB = "";
	
	public static String CONTROLADOR_CADASTRO_COSANPA_SEJB = "";
	
	public static String CONTROLADOR_CADASTRO_JUAZEIRO_SEJB = "";
	
	public static String CONTROLADOR_CADASTRO_COSAMA_SEJB = "";

	// =====================================================================================================
	
	public static String BATCH_GERAR_RESUMO_CONSUMO_AGUA_MDB = "";

	public static String BATCH_GERAR_RESUMO_HIDROMETRO_MDB = "";

	public static String BATCH_GERAR_RESUMO_HISTOGRAMA_AGUA_ESGOTO = "";

	public static String BATCH_GERAR_RESUMO_REGISTRO_ATENDIMENTO_MDB = "";

	public static String CONTROLADOR_RELATORIO_FATURAMENTO_SEJB = "";
	
	public static String BATCH_GERAR_RESUMO_METAS_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_METAS_ACUMULADO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_COLETA_ESGOTO_MDB = "";
	
	public static String BATCH_GERAR_CONTAS_A_RECEBER_CONTABIL_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_DOCUMENTOS_A_RECEBER_MDB = "";
    
    public static String BATCH_ATUALIZA_QUANTIDADE_PARCELA_PAGA_CONSECUTIVA_PARCELA_BONUS_MDB = "";
	
	public static String CONTROLADOR_INTEGRACAO_SEJB = "";

	
	public static String BATCH_EXECUTAR_COMANDO_ENCERRAMENTO_RA_MDB = "";
	
	public static String BATCH_GERAR_VALOR_VOLUMES_CONSUMIDOS_NAO_FATURADOS_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_INDICADORES_COMERCIALIZACAO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_INDICADORES_MICROMEDICAO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_INDICADORES_FATURAMENTO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_INDICADORES_COBRANCA_MDB = "";
	
	public static String BATCH_RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE = "";
	
	public static String BATCH_INCLUIR_DEBITO_A_COBRAR_ENTRADA_PARCELAMENTO_NAO_PAGA_MDB = "";
	
	public static String BATCH_ATUALIZAR_DIFERENCA_ACUMULADA_NO_MES = "";
	
	public static String BATCH_ATUALIZAR_PAGAMENTOS_CONTAS_COBRANCA = "";
	
	public static String BATCH_GERAR_MOVIMENTO_CONTAS_COBRANCA_POR_EMPRESA = "";
	
	public static String BATCH_GERAR_ARQUIVO_TEXTO_CONTAS_COBRANCA_POR_EMPRESA = "";
	
	public static String BATCH_GERAR_RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_POR_EMPRESA = "";
	
	public static String BATCH_GERAR_CREDITO_SITUACAO_ESPECIAL_FATURAMENTO_MDB = "";
	
	public static String BATCH_GERAR_MOVIMENTO_EXTENSAO_CONTAS_COBRANCA_POR_EMPRESA = "";
	
	public static String BATCH_ATUALIZAR_AUTOS_INFRACAO_PRAZO_RECURSO_VENCIDO_MDB = "";
	
	public static String BATCH_GERAR_MOVIMENTO_HIDROMETRO = "";

	public static String BATCH_ATUALIZAR_CONJUNTO_HIDROMETRO_MDB = "";
	
	public static String BATCH_GERAR_CARTAS_CAMPANHA_SOLIDARIEDADE_CRIANCA_PARA_NEGOCIACAO_A_VISTA_MDB = "";
	
	public static String BATCH_EMITIR_CARTAS_CAMPANHA_SOLIDARIEDADE_CRIANCA_PARA_NEGOCIACAO_A_VISTA_MDB = "";
	
	public static String BATCH_GERAR_TABELAS_TEMPORARIAS_ATUALIZACAO_CADASTRAL_MDB = "";
	
	public static String BATCH_GERAR_ARQUIVO_TEXTO_ATUALIZACAO_CADASTRAL_MDB = "";
	
	public static String BATCH_EMITIR_BOLETOS_MDB = "";
	
	public static String BATCH_RETIFICAR_CONJUNTO_CONTA_CONSUMOS_MDB = "";
	
	public static String BATCH_GERAR_BONUS_TARIFA_SOCIAL = "";
	
	public static String  BATCH_EXCLUIR_IMOVEIS_DA_TARIFA_SOCIAL = "";
	
	public static String BATCH_GERAR_ARQUIVO_TEXTO_PAGAMENTOS_CONTAS_COBRANCA_POR_EMPRESA = "";


	
	public static String BATCH_GERAR_ARQUIVO_TEXTO_CONTAS_PROJETOS_ESPECIAIS = "";
	
	public static String BATCH_INSERIR_PAGAMENTOS_FATURAS_ESPECIAIS = "";
	
	public static String BATCH_SUSPENDER_IMOVEL_EM_PROGRAMA_ESPECIAL = "";

	public static String BATCH_RESUMO_SIMULACAO_FATURAMENTO = "";
	
	public static String BATCH_DETERMINAR_CONFIRMACAO_DA_NEGATIVACAO = "";
	
	public static String BATCH_EMITIR_ORDEM_FISCALIZACAO = "";
	
	public static String BATCH_GERAR_ARQUIVO_ORDEM_FISCALIZACAO = "";
	
	public static String BATCH_ENVIO_EMAIL_CONTA_PARA_CLIENTE = "";
	
	public static String BATCH_ATUALIZAR_COMANDO_ATIVIDADE_ACAO_COBRANCA = "";
	
	public static String BATCH_EMITIR_DOCUMENTO_COBRANCA = "";
	
	public static String BATCH_GERAR_RESUMO_PENDENCIA_POR_ANO = "";
	
	public static String BATCH_ATUALIZAR_CODIGO_DEBITO_AUTOMATICO = "";
	

	public static String BATCH_GERAR_DECLARACAO_QUITACAO_ANUAL_DEBITOS = "";
	
	public static String BATCH_GERAR_ARQUIVO_TEXTO_DECLARACAO_QUITACAO_ANUAL_DEBITOS = "";
	
	public static String BATCH_GERAR_RESUMO_LIGACOES_ECONOMIAS_POR_ANO = "";
	
	public static String BATCH_GERAR_RESUMO_FATURAMENTO_POR_ANO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_INDICADORES_MICROMEDICAO_POR_ANO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_CONSUMO_AGUA_POR_ANO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_ARRECADACAO_POR_ANO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_COLETA_ESGOTO_POR_ANO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_REGISTRO_ATENDIMENTO_POR_ANO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_INSTALACOES_HIDROMETROS_POR_ANO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_PARCELAMENTO_POR_ANO_MDB = "";
	
	public static String BATCH_GERAR_TAXA_PERCENTUAL_TARIFA_MINIMA_CORTADO_MDB = "";

	public static String BATCH_GERAR_RESUMO_REFATURAMENTO_NOVO_MDB = "";

	public static String BATCH_ALTERAR_INSCRICOES_IMOVEIS_MDB = "";
	
	public static String BATCH_GERAR_PRESCREVER_DEBITOS_DE_IMOVEIS_MDB = "";
	
	public static String BATCH_VERIFICAR_FATURAMENTO_IMOVEIS_CORTADOS_MDB = "";
	
	public static String BATCH_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL_MDB = "";

	public static String BATCH_APAGAR_RESUMO_DEVEDORES_DUVIDOSOS_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_HISTOGRAMA_AGUA_ESGOTO_SEM_QUADRA = "";
	
	public static String BATCH_ATUALIZAR_RESUMO_DEVEDORES_DUVIDOSOS = "";

	public static String BATCH_PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_MANUAL_MDB = "";
	
	public static String BATCH_PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_AUTOMATICO_MDB = "";
	
	public static String BATCH_PROCESSAR_ENCERRAMENTO_ORDEM_SERVICO_ACAO_COBRANCA_MDB = "";
	
	public static String BATCH_AUTOMATIZAR_PERFIS_DE_GRANDES_CONSUMIDORES_MDB = "";
	
	public static String BATCH_GERAR_RA_OS_ANORMALIDADE_CONSUMO = "";
	
	public static String BATCH_PROCESSAR_COMANDO_GERADO = "";
	
	public static String BATCH_GERAR_CARTA_TARIFA_SOCIAL = "";
	
	public static String BATCH_RETIRAR_IMOVEL_TARIFA_SOCIAL = "";
	
	public static String BATCH_GERAR_TXT_IMPRESSAO_CONTAS_BRAILLE = "";
	
	public static String BATCH_ENCERRAR_COMANDO_DE_COBRANCA_POR_EMPRESA = "";
	
	public static String BATCH_GERAR_TXT_OS_INSPECAO_ANORMALIDADE = "";

	public static String BATCH_PROCESSAR_ARQUIVO_TXT_ENCERRAMENTO_OS_COBRANCA = "";
	
	public static String BATCH_GERAR_ARQUIVO_TXT_OS_CONTAS_PAGAS_PARCELADAS = "";
	
	public static String BATCH_ENCERRAR_COMANDO_OS_SELETIVA_INSPECAO_ANORMALIDADE = "";
	
	public static String BATCH_GERAR_DADOS_ARQUIVO_ACOMPANHAMENTO_SERVICO_MDB = "";
	
	public static String BATCH_PROGRAMACAO_AUTO_ROTEIRO_ACOMP_SERVICO = "";
	
	public static String BATCH_PROCESSAR_ENCERRAMENTO_OS_FISCALIZACAO_DECURSO_PRAZO_MDB = "";
	
	public static String BATCH_SUSPENDER_LEITURA_PARA_IMOVEL_COM_HIDROMETRO_RETIRADO_MDB = "";
	
	public static String BATCH_SUSPENDER_LEITURA_PARA_IMOVEL_COM_CONSUMO_REAL_NAO_SUPERIOR_A_10_MDB = "";
	
	public static String BATCH_ENCERRAR_OS_VISITA_COBRANCA_MDB = "";
	
	public static String BATCH_PROCESSAR_ATUAL_ORDENS_SERVICO_ARQ_TXT_MDB = "";
	
	public static String BATCH_CANCELAR_GRUPO_FATURAMENTO_MDB = "";
	
	public static String BATCH_GERAR_LANCAMENTO_CONTABEIS_VOLUMES_CONSUMIDOS_NAO_FATURADOS_MDB = "";
	
	public static String BATCH_RETIRAR_IMOVEIS_CONTAS_EMPRESA_COBRANCA_MDB = "";

	public static String BATCH_INCLUIR_CONTAS_EM_COBRANCA_MDB = "";

	public static String BATCH_GERAR_FATURAMENTO_BRUTO_LIQUIDO = "";

	public static String BATCH_PARALISAR_FATURAMENTO_MDB = "";
	
	public static String BATCH_GERAR_RESUMO_ARRECADACAO_ANALITICO_DADOS = "";
	
	public static String BATCH_GERAR_RESUMO_ARRECADACAO_ANALITICO_TOTAIS_1 = "";
	
	public static String BATCH_GERAR_RESUMO_ARRECADACAO_ANALITICO_TOTAIS_2 = "";
	
	public static String BATCH_EXECUTAR_CARGA_ARRECADACAO_GERENCIAL_POR_ANO = "";
	
	public static String BATCH_EXECUTAR_CARGA_REGISTRO_ATENDIMENTO_POR_ANO = "";

	public static String BATCH_EXECUTAR_CARGA_RESUMO_FATURAMENTO_POR_ANO = "";
	
	public static String BATCH_EXECUTAR_CARGA_INDICADORES_DESEMPENHO_FATURAMENTO_POR_ANO = "";
	
	public static String BATCH_EXECUTAR_CARGA_INDICADORES_DESEMPENHO_FATURAMENTO_VARIOS_ANOS = "";
	
	public static String BATCH_EXECUTAR_CARGA_COLETA_ESGOTO_POR_ANO = "";
	
	public static String BATCH_EXECUTAR_CARGA_CONSUMO_AGUA_POR_ANO = "";
	
	public static String BATCH_EXECUTAR_CARGA_LEITURA_ANORMALIDADE_POR_ANO = "";
	
	public static String BATCH_EXECUTAR_CARGA_INDICADORES_DESEMPENHO_MICROMEDICAO_VARIOS_ANOS = "";
	
	public static String BATCH_EXECUTAR_CARGA_INDICADORES_DESEMPENHO_MICROMEDICAO_POR_ANO = "";
	
	public static String BATCH_EXECUTAR_CARGA_LIGACOES_ECONOMIAS_POR_ANO = "";
	
	public static String BATCH_EXECUTAR_CARGA_INDICADOR_LIGACAO_ECONOMIA_POR_ANO = "";
	
	public static String BATCH_EXECUTAR_CARGA_RESUMO_PARCELAMENTO_POR_ANO = "";
	
	public static String BATCH_EXECUTAR_CARGA_RESUMO_PENDENCIA_POR_ANO = "";
	
	public static String BATCH_EXECUTAR_CARGA_PENDENCIA_SEM_QUADRA_VARIOS_ANOS = "";
	
	public static String BATCH_GERAR_RESUMO_PENDENCIA_BD = "";
	
	public static String BATCH_RETIRAR_CONTAS_REVISAO_EXTRATO_ENTRADA_PARCELAMENTO = "";

	public static String BATCH_GERAR_FATURA_PROGRAMA_ESPECIAL = "";
	
	public static String BATCH_RECEITA_LIQUIDA_INDIRETA_DE_AGUA_E_ESGOTO = "";
	
	public static String BATCH_GERAR_RESUMO_FATURAMENTO_ANALITICO_PARTE_1 = "";
	
	public static String BATCH_GERAR_RESUMO_FATURAMENTO_ANALITICO_PARTE_2 = "";
	
	public static String BATCH_GERAR_RESUMO_FATURAMENTO_ANALITICO_TOTAIS = "";
	
	public static String BATCH_GERAR_CREDITO_REALIZAR_PAGAMENTO_DUPLICIDADE_VIVA_AGUA = "";
	
	public static String AUTOMATIZAR_CARGA_MENSAL_PENTAHO = "";
	
	public static String BATCH_GERAR_UN_RESUMO_ARRECADACAO_BD = "";
	
	public static String BATCH_BAIXA_AUTOMATICA_PAGAMENTOS_NAO_CLASSIFICADOS = "";
	
	public static String BATCH_GERAR_ARQUIVO_CARTEIRA_17 = "";
	
	public static String BATCH_REGISTRAR_MOVIMENTOS_ARRECADADORES = "";
	
	public static String BATCH_RETIFICAR_CONTAS_INDEVIDAS_CAERN = "";
	
	public static String BATCH_PROCESSAR_AMORTIZACAO_DIVIDA_ATIVA_ARRECADACAO = "";
	
	public static String BATCH_PROCESSAR_AMORTIZACAO_DIVIDA_ATIVA_FATURAMENTO = "";
	
	public static String BATCH_GERAR_DIVIDA_ATIVA = "";
	
	public static String BATCH_GERAR_RESUMO_DIVIDA_ATIVA_ANUAL = "";
	
	public static String BATCH_ENVIAR_EMAIL_COBRANCA_FATURAMENTO = "";
	
	public static String BATCH_PREPARAR_DADOS_SMS_EMAIL_COBRANCA_CONTA = "";
	
	public static String BATCH_GERAR_CONTAS = "";
	
	public static String BATCH_RETORNO_SMS_EM_LOTE = "";
	
	public static String BATCH_TRANSMITIR_SMS_EM_LOTE_COBRANCA_FATURAMENTO = "";
	
	public static String BATCH_PREPARAR_DADOS_SMS_EMAIL_ACAO_COBRANCA = "";
	
	public static String BATCH_GERAR_ROTEIRO_DISPOSITIVO_MOVEL_MDB = "";
	
	public static String BATCH_ATUALIZAR_DADOS_CADASTRAIS_IMOVEL_RECADASTRAMENTO_MDB = "";
	
	public static String BATCH_RESUMO_ARRECADACAO_ATUALIZA_DADOS = "";
	
	public static String BATCH_GERAR_RESUMO_COBRANCA_ATUALIZA_DADOS = "";
	
	public static String BATCH_GERAR_RESUMO_FATURAMENTO_ATUALIZA_DADOS = "";
	
	public static String BATCH_GERAR_RESUMO_MICROMEDICAO_ATUALIZA_DADOS = "";

	public static String BATCH_GERAR_RESUMO_ATENDIMENTO_PUBLICO_ATUALIZA_DADOS = "";	

	public static String BATCH_GERAR_RESUMO_PARCELAMENTO_ATUALIZA_DADOS = "";	

	public static String BATCH_GERAR_RESUMO_CADASTRO_ATUALIZA_DADOS = "";		
	
	public static String BATCH_RELIGAR_IMOVEIS_CORTADOS_COM_LEITURA_REAL_CONSUMO = "";		

	static {

		inicializarPropriedades();

	}

	/**
	 * Inicializa as propriedades da classes ConstantesJNDI
	 */

	public static void inicializarPropriedades() {

		Properties propriedades = new Properties();

		InputStream stream;

		try {

			ClassLoader classLoader = ClassLoader.getSystemClassLoader();

			stream = classLoader.getResourceAsStream(NOME_ARQUIVO_PROPRIEDADES);

			// if system class loader not found try the this class classLoader

			if (stream == null) {

				stream = gsan.util.ConstantesJNDI.class.getClassLoader()
						.getResourceAsStream(NOME_ARQUIVO_PROPRIEDADES);

			}

			if (stream == null) {

				stream = gsan.util.ConstantesJNDI.class
						.getResourceAsStream(NOME_ARQUIVO_PROPRIEDADES);

			}

			propriedades.load(stream);
			// UTIL

			// FUNCOES_EJB = propriedades.getProperty("funcoesEJB");

			// Gerencail Cadastro
			CONTROLADOR_USUARIO_SEJB = propriedades
					.getProperty("ControladorUsuario");

			// Gerencail Cadastro
			CONTROLADOR_GERENCIAL_CADASTRO_SEJB = propriedades
					.getProperty("ControladorGerencialCadastro");

			// Gerencail Cobranca
			CONTROLADOR_GERENCIAL_COBRANCA_SEJB = propriedades
					.getProperty("ControladorGerencialCobranca");

			// Gerencail Faturamento
			CONTROLADOR_GERENCIAL_FATURAMENTO_SEJB = propriedades
					.getProperty("ControladorGerencialFaturamento");

			// Gerencail Im�vel
			CONTROLADOR_GERENCIAL_IMOVEL_SEJB = propriedades
					.getProperty("ControladorGerencialImovel");
			
			// Cobranca
			CONTROLADOR_COBRANCA_SEJB = propriedades
					.getProperty("ControladorCobranca");
			
			//ContratoParcelamento
			CONTROLADOR_CONTRATO_PARCELAMENTO_SEJB = propriedades
					.getProperty("ControladorContratoParcelamento");
			
			// Financeiro
			CONTROLADOR_FINANCEIRO_SEJB = propriedades
					.getProperty("ControladorFinanceiro");

			// Gerencial
			CONTROLADOR_GERENCIAL_SEJB = propriedades
					.getProperty("ControladorGerencial");

			CONTROLADOR_ACESSO_SEJB = propriedades
					.getProperty("ControladorAcesso");

			CONTROLADOR_TRANSACAO_SEJB = propriedades
					.getProperty("ControladorTransacao");

			CONTROLADOR_TABELA_AUXILIAR_SEJB = propriedades
					.getProperty("ControladorTabelaAuxiliar");

			CONTROLADOR_UTIL_SEJB = propriedades.getProperty("ControladorUtil");

			CONTROLADOR_ENDERECO_SEJB = propriedades
					.getProperty("ControladorEndereco");

			CONTROLADOR_CLIENTE_SEJB = propriedades
					.getProperty("ControladorCliente");

			CONTROLADOR_IMOVEL_SEJB = propriedades
					.getProperty("ControladorImovel");

			CONTROLADOR_MICROMEDICAO_SEJB = propriedades
					.getProperty("ControladorMicromedicao");

			CONTROLADOR_LOCALIDADE_SEJB = propriedades
					.getProperty("ControladorLocalidade");

			CONTROLADOR_GEOGRAFICO_SEJB = propriedades
					.getProperty("ControladorGeografico");

			CONTROLADOR_FATURAMENTO_SEJB = propriedades
					.getProperty("ControladorFaturamento");

			CONTROLADOR_TARIFA_SOCIAL_SEJB = propriedades
					.getProperty("ControladorTarifaSocial");

			QUEUE_CONTROLADOR_FATURAMENTO_MDB = propriedades
					.getProperty("QueueControladorFaturamento");

			QUEUE_CONTROLADOR_ARRECADACAO_MDB = propriedades
					.getProperty("QueueControladorArrecadacao");

			QUEUE_CONTROLADOR_MICROMEDICAO_MDB = propriedades
					.getProperty("QueueControladorMicromedicao");

			CONTROLADOR_ARRECADACAO_SEJB = propriedades
					.getProperty("ControladorArrecadacao");

			CONTROLADOR_GERENCIAL_MICROMEDICAO_SEJB = propriedades
					.getProperty("ControladorGerencialMicromedicao");

			CONTROLADOR_GERENCIAL_ARRECADACAO_SEJB = propriedades
					.getProperty("ControladorGerencialArrecadacao");

			CONTROLADOR_ATENDIMENTO_PUBLICO_SEJB = propriedades
					.getProperty("ControladorAtendimentoPublico");

			CONTROLADOR_BATCH_SEJB = propriedades
					.getProperty("ControladorBatch");

			CONTROLADOR_UNIDADE_SEJB = propriedades
					.getProperty("ControladorUnidade");

			CONTROLADOR_REGISTRO_ATENDIMENTO_SEJB = propriedades
					.getProperty("ControladorRegistroAtendimento");

			CONTROLADOR_ORDEM_SERVICO_SEJB = propriedades
					.getProperty("ControladorOrdemServico");

			CONTROLADOR_LIGACAO_ESGOTO_SEJB = propriedades
					.getProperty("ControladorLigacaoEsgoto");

			CONTROLADOR_LIGACAO_AGUA_SEJB = propriedades
					.getProperty("ControladorLigacaoAgua");
			
			CONTROLADOR_SPC_SERASA_SEJB = propriedades
					.getProperty("ControladorSpcSerasa");

			BATCH_CONSISTIR_LEITURAS_CALCULAR_CONSUMOS_MDB = propriedades
					.getProperty("QueueBatchConsistirLeiturasCalcularConsumosMDB");

			BATCH_GERAR_DADOS_PARA_LEITURA_MDB = propriedades
					.getProperty("QueueBatchGerarDadosParaLeituraMDB");

			BATCH_FATURAR_GRUPO_FATURAMENTO_MDB = propriedades
					.getProperty("QueueBatchFaturarGrupoFaturamentoMDB");

			BATCH_EFETUAR_RATEIO_CONSUMO_MDB = propriedades
					.getProperty("QueueBatchEfetuarRateioConsumoMDB");

			BATCH_GERAR_DEBITO_A_COBRAR_DE_ACRESCIMOS_POR_IMPONTUALIDADE_MDB = propriedades
					.getProperty("QueueBatchGerarDebitoACobrarDeAcrescimoPorImpontualidadeMDB");

			BATCH_GERAR_TAXA_ENTREGA_OUTRO_ENDERECO_MDB = propriedades
					.getProperty("QueueBatchGerarTaxaEntregaOutroEnderecoMDB");

			BATCH_GERAR_DADOS_DIARIOS_ARRECADACAO_MDB = propriedades
					.getProperty("QueueBatchGerarDadosDiariosArrecadacaoMDB");

			BATCH_GERAR_ATIVIDADE_ACAO_COBRANCA_MDB = propriedades
					.getProperty("QueueBatchGerarAtividadeAcaoCobrancaMDB");

			BATCH_EMITIR_BOLETIM_CADASTRO_MDB = propriedades
					.getProperty("QueueBatchEmitirBoletimCadastroMDB");

			BATCH_EMITIR_CONTAS_MDB = propriedades
					.getProperty("QueueBatchEmitirContasMDB");

			CONTROLADOR_BATCH_SEJB = propriedades
					.getProperty("ControladorBatch");

			CONTROLADOR_PERMISSAO_ESPECIAL_SEJB = propriedades
					.getProperty("ControladorPermissaoEspecial");

			CONTROLADOR_OPERACIONAL_SEJB = propriedades
					.getProperty("ControladorOperacional");

			CONTROLADOR_CADASTRO_SEJB = propriedades
					.getProperty("ControladorCadastro");

			BATCH_CLASSIFICAR_PAGAMENTOS_DEVOLUCOES_MDB = propriedades
					.getProperty("QueueBatchClassificarPagamentosDevolucoesMDB");

			BATCH_ENCERRAR_ARRECADACAO_MES_MDB = propriedades
					.getProperty("QueueBatchEncerrarArrecadacaoMesMDB");

			BATCH_GERAR_DEBITOS_COBRAR_DOACAO_MDB = propriedades
					.getProperty("QueueBatchGerarDebitoACobrarDoacaoMDB");

			BATCH_ENCERRAR_FATURAMENTO_MES_MDB = propriedades
					.getProperty("QueueBatchEncerrarFaturamentoMesMDB");

			BATCH_GERAR_RESUMO_LIGACOES_ECONOMIAS_MDB = propriedades
					.getProperty("QueueBatchGerarResumoLigacoesEconomiasMDB");

			BATCH_GERAR_RESUMO_SITUACAO_ESPECIAL_FATURAMENTO_MDB = propriedades
					.getProperty("QueueBatchGerarResumoSituacaoEspecialFaturamentoMDB");

			BATCH_GERAR_RESUMO_ACOES_COBRANCA_CRONOGRAMA_MDB = propriedades
					.getProperty("QueueBatchGerarResumoAcoesCobrancaCronogramaMDB");

			BATCH_GERAR_RESUMO_ACAO_COBRANCA_CRONOGRAMA_ENCERRAR_OS_MDB = propriedades
				.getProperty("QueueBatchGerarResumoAcoesCobrancaCronogramaEncerrarOSMDB");

			BATCH_INSERIR_RESUMO_ACAO_COBRANCA_CRONOGRAMA_MDB = propriedades
					.getProperty("QueueBatchInserirResumoAcoesCobrancaCronogramaMDB");
			
			BATCH_INSERIR_RESUMO_ACAO_COBRANCA_EVENTUAL_MDB = propriedades
			.getProperty("QueueBatchInserirResumoAcoesCobrancaEventualMDB");
			
			BATCH_GERAR_RESUMO_ACOES_COBRANCA_EVENTUAL_MDB = propriedades
			.getProperty("QueueBatchGerarResumoAcoesCobrancaEventualMDB");

			BATCH_GERAR_RESUMO_ANORMALIDADES_MDB = propriedades
					.getProperty("QueueBatchGerarResumoAnormalidadesMDB");

			BATCH_GERAR_RESUMO_CONSUMO_AGUA_MDB = propriedades
					.getProperty("QueueBatchGerarResumoConsumoAguaMDB");

			BATCH_GERAR_RESUMO_HIDROMETRO_MDB = propriedades
			.getProperty("QueueBatchGerarResumoHidrometroMDB");

			BATCH_GERAR_RESUMO_HISTOGRAMA_AGUA_ESGOTO = propriedades
					.getProperty("QueueBatchGerarResumoHistogramaAguaEsgotoMDB");

			BATCH_GERAR_RESUMO_SITUACAO_ESPECIAL_COBRANCA_MDB = propriedades
					.getProperty("QueueBatchGerarResumoSituacaoEspecialCobrancaMDB");

			BATCH_EMITIR_EXTRATO_CONSUMO_IMOVEL_CONDOMINIO_MDB = propriedades
					.getProperty("QueueBatchEmitirExtratoConsumoImovelCondominioMDB");

			BATCH_GERAR_FATURA_CLIENTE_RESPONSAVEL_MDB = propriedades
					.getProperty("QueueBatchGerarFaturaClienteResponsavelMDB");

			BATCH_GERAR_HISTORICO_PARA_ENCERRAR_ARRECADACAO_MES_MDB = propriedades
					.getProperty("QueueBatchGerarHistoricoParaEncerrarArrecadacaoMesMDB");

			BATCH_GERAR_HISTORICO_PARA_ENCERRAR_FATURAMENTO_MES_MDB = propriedades
					.getProperty("QueueBatchGerarHistoricoParaEncerrarFaturamentoMesMDB");

			BATCH_DESFAZER_PARCELAMENTO_POR_ENTRADA_NAO_PAGA_MDB = propriedades
					.getProperty("QueueBatchDesfazerParcelamentoPorEntradaNaoPagaMDB");

			BATCH_GERAR_HISTORICO_CONTA_MDB = propriedades
					.getProperty("QueueBatchGerarHistoricoContaMDB");

			BATCH_GERAR_RESUMO_INSTALACOES_HIDROMETROS_MDB = propriedades
					.getProperty("QueueBatchGerarResumoInstalacoesHidrometrosMDB");

			BATCH_GERAR_RESUMO_LEITURA_ANORMALIDADE_MDB = propriedades

			.getProperty("QueueBatchGerarResumoLeituraAnormalidadeMDB");

			BATCH_GERAR_RESUMO_ARRECADACAO_MDB = propriedades
					.getProperty("QueueBatchGerarResumoArrecadacaoMDB");

			BATCH_GERAR_RESUMO_PARCELAMENTO_MDB = propriedades
					.getProperty("QueueBatchGerarResumoParcelamentoMDB");

			BATCH_GERAR_LANCAMENTOS_CONTABEIS_FATURAMENTO_MDB = propriedades

			.getProperty("QueueBatchGerarLancamentosContabeisFaturamentoMDB");

			BATCH_GERAR_LANCAMENTOS_CONTABEIS_ARRECADACAO_MDB = propriedades
					.getProperty("QueueBatchGerarLancamentosContabeisArrecadacaoMDB");
			
			BATCH_GERAR_LANCAMENTOS_CONTABEIS_AVISOS_BANCARIOS_MDB = propriedades
			.getProperty("QueueBatchGerarLancamentosContabeisAvisosBancariosMDB");

			BATCH_GERAR_RESUMO_FATURAMENTO_AGUA_ESGOTO_MDB = propriedades
					.getProperty("QueueBatchGerarResumoFaturamentoAguaEsgotoMDB");
            
            BATCH_GERAR_RESUMO_FATURAMENTO_MDB = propriedades
            .getProperty("QueueBatchGerarResumoFaturamentoMDB");            

			BATCH_GERAR_RESUMO_REFATURAMENTO_MDB = propriedades
			.getProperty("QueueBatchGerarResumoReFaturamentoMDB");
            
            BATCH_GERAR_RESUMO_REFATURAMENTO_OLAP_MDB = propriedades
            .getProperty("QueueBatchGerarResumoReFaturamentoOlapMDB");            

			BATCH_GERAR_RESUMO_REGISTRO_ATENDIMENTO_MDB = propriedades
					.getProperty("QueueBatchGerarResumoRegistroAtendimentoMDB");
			
			BATCH_EMITIR_CONTAS_ORGAO_PUBLICO = 
				propriedades.getProperty("QueueBatchEmitirContasOrgaoPublicoMDB");
			
			BATCH_GERAR_RESUMO_METAS_MDB = propriedades
			.getProperty("QueueBatchGerarResumoMetasMDB");
			
			BATCH_GERAR_RESUMO_METAS_ACUMULADO_MDB = propriedades
			.getProperty("QueueBatchGerarResumoMetasAcumuladoMDB");
			
			BATCH_APAGAR_RESUMO_DEVEDORES_DUVIDOSOS_MDB = propriedades.getProperty("QueueBatchApagarResumoDevedoresDuvidososMDB");
			
			BATCH_GERAR_RESUMO_DEVEDORES_DUVIDOSOS_MDB = propriedades.getProperty("QueueBatchGerarResumoDevedoresDuvidososMDB");
			
			BATCH_ATUALIZAR_RESUMO_DEVEDORES_DUVIDOSOS = propriedades.getProperty("QueueBatchAtualizarResumoDevedoresDuvidososMDB");
			
			BATCH_GERAR_LANCAMENTOS_CONTABEIS_DEVEDORES_DUVIDOSOS_MDB = propriedades.getProperty("QueueBatchGerarLancamentosContabeisDevedoresDuvidososMDB");
			
			BATCH_GERAR_RESUMO_PENDENCIA = propriedades.getProperty("QueueBatchGerarResumoPendenciaMDB");
			
			BATCH_GERAR_GUIA_PAGAMENTO_POR_CLIENTE_RESUMO_PENDENCIA = propriedades.getProperty("QueueBatchGerarGuiaPagamentoPorClienteResumoPendenciaMDB");
			
			BATCH_GERAR_ARQUIVO_TEXTO_PARA_LEITURISTA = propriedades.getProperty("QueueBatchGerarArquivoTextoParaLeituristaMDB");
			
			BATCH_GERAR_RESUMO_COLETA_ESGOTO_MDB = propriedades
					.getProperty("QueueBatchGerarResumoColetaEsgotoMDB");

			BATCH_GERAR_CONTAS_A_RECEBER_CONTABIL_MDB = propriedades
					.getProperty("QueueBatchGerarContasAReceberContabilMDB");
			
			BATCH_GERAR_RESUMO_DOCUMENTOS_A_RECEBER_MDB = propriedades
			.getProperty("QueueBatchGerarResumoDocumentosAReceberMDB");

			BATCH_EXECUTAR_COMANDO_ENCERRAMENTO_RA_MDB = propriedades
					.getProperty("QueueBatchExecutarComandoEncerramentoRAMDB");
			
			BATCH_GERAR_VALOR_VOLUMES_CONSUMIDOS_NAO_FATURADOS_MDB = propriedades
					.getProperty("QueueBatchGerarValorVolumesConsumidosNaoFaturadosMDB");

			BATCH_GERAR_RESUMO_INDICADORES_COMERCIALIZACAO_MDB = propriedades
					.getProperty("QueueBatchGerarResumoIndicadoresComercializacaoMDB");

			BATCH_GERAR_RESUMO_INDICADORES_MICROMEDICAO_MDB = propriedades
					.getProperty("QueueBatchGerarResumoIndicadoresMicromedicaoMDB");
			
			BATCH_GERAR_RESUMO_INDICADORES_FATURAMENTO_MDB = propriedades
					.getProperty("QueueBatchGerarResumoIndicadoresFaturamentoMDB");
			
			BATCH_GERAR_RESUMO_INDICADORES_COBRANCA_MDB = propriedades
					.getProperty("QueueBatchGerarResumoIndicadoresCobrancaMDB");

            BATCH_ATUALIZA_QUANTIDADE_PARCELA_PAGA_CONSECUTIVA_PARCELA_BONUS_MDB = propriedades
            .getProperty("QueueBatchAtualizaQuantidadeParcelaPagaConsecutivaParcelaBonusMDB");
            
            BATCH_RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE = propriedades
            .getProperty("QueueBatchRelatorioContasBaixadasContabilmenteMDB");
            
			//BATCH_GERAR_RESUMO_FATURAMENTO_AGUA_ESGOTO_MDB = propriedades
			//.getProperty("QueueBatchGerarResumoFaturamentoMDB");

			// =====================================================================================================
			
			BATCH_EXECUTAR_COMANDO_NEGATIVACAO_MDB = propriedades.getProperty("QueueBatchExecutarComandoNegativacaoMDB");
			
			BATCH_GERAR_RESUMO_DIARIO_NEGATIVACAO_MDB = propriedades.getProperty("QueueBatchGerarResumoDiarioNegativacaoMDB");
			
			BATCH_ATUALIZAR_LIGACAO_AGUA_LIGADO_ANALISE_PARA_LIGADO_MDB = propriedades.getProperty("QueueBatchAtualizarLigacaoAguaLigadoAnaliseParaLigadoMDB");
			
			BATCH_ATUALIZAR_NUMERO_EXECUCAO_RESUMO_NEGATIVACAO_MDB = propriedades.getProperty("QueueBatchAtualizarNumeroExecucaoResumoNegativacaoMDB");
			
			BATCH_GERAR_MOVIMENTO_EXCLUSAO_NEGATIVACAO_MDB = propriedades.getProperty("QueueBatchGerarMovimentoExclusaoNegativacaoMDB");
			
			BATCH_GERAR_MOVIMENTO_RETORNO_NEGATIVACAO_MDB = propriedades.getProperty("QueueBatchGerarMovimentoRetornoNegativacaoMDB");
			
			BATCH_ATUALIZAR_DIFERENCA_ACUMULADA_NO_MES = propriedades.getProperty("QueueBatchAtualizarDiferencaAcumuladaNoMesMDB");

			BATCH_INCLUIR_DEBITO_A_COBRAR_ENTRADA_PARCELAMENTO_NAO_PAGA_MDB = propriedades
			.getProperty("QueueBatchIncluirDebitoACobrarEntradaParcelamentoNaoPagaMDB");
			
			BATCH_ATUALIZAR_PAGAMENTOS_CONTAS_COBRANCA = propriedades
			.getProperty("QueueBatchAtualizarPagamentosContasCobrancaMDB");
			
			BATCH_GERAR_MOVIMENTO_CONTAS_COBRANCA_POR_EMPRESA = propriedades
			.getProperty("QueueBatchGerarMovimentoContasCobrancaPorEmpresaMDB");
			
			BATCH_GERAR_ARQUIVO_TEXTO_CONTAS_COBRANCA_POR_EMPRESA = propriedades
			.getProperty("QueueBatchGerarArquivoTextoContasCobrancaMDB");
			
			BATCH_GERAR_MOVIMENTO_EXTENSAO_CONTAS_COBRANCA_POR_EMPRESA = propriedades
			.getProperty("QueueBatchGerarMovimentoExtensaoContasCobrancaPorEmpresaMDB");
			
			BATCH_ATUALIZAR_AUTOS_INFRACAO_PRAZO_RECURSO_VENCIDO_MDB = propriedades
			.getProperty("QueueBatchAtualizarAutosInfracaoPrazoRecursoVencidoMDB");
			
			BATCH_ATUALIZAR_CONJUNTO_HIDROMETRO_MDB = propriedades.getProperty("QueueBatchAtualizarConjuntoHidrometroMDB");
			
			BATCH_GERAR_CARTAS_CAMPANHA_SOLIDARIEDADE_CRIANCA_PARA_NEGOCIACAO_A_VISTA_MDB = propriedades
			.getProperty("QueueBatchGerarCartasCampanhaSolidariedadeCriancaParaNegociacaoAVistaMDB");
			
			BATCH_EMITIR_CARTAS_CAMPANHA_SOLIDARIEDADE_CRIANCA_PARA_NEGOCIACAO_A_VISTA_MDB = propriedades
			.getProperty("QueueBatchEmitirCartasCampanhaSolidariedadeCriancaParaNegociacaoAVistaMDB");

			BATCH_GERAR_TABELAS_TEMPORARIAS_ATUALIZACAO_CADASTRAL_MDB = propriedades
			.getProperty("QueueBatchGerarTabelasTemporariasAtualizacaoCadastralMDB");
			
			BATCH_GERAR_ARQUIVO_TEXTO_ATUALIZACAO_CADASTRAL_MDB = propriedades
			.getProperty("QueueBatchGerarArquivoTextoAtualizacaoCadastralMDB");			
			
			BATCH_EMITIR_BOLETOS_MDB = propriedades
			.getProperty("QueueBatchEmitirBoletosMDB");	
			
			BATCH_RETIFICAR_CONJUNTO_CONTA_CONSUMOS_MDB = propriedades
			.getProperty("QueueBatchRetificarConjuntoContaConsumosMDB");	

			// ESQUEMA DOS CONTROLADORES
			// =====================================================================================================
			CONTROLADOR_FATURAMENTO_COMPESA_SEJB = propriedades
					.getProperty("ControladorFaturamentoCOMPESA");

			CONTROLADOR_FATURAMENTO_CAER_SEJB = propriedades
					.getProperty("ControladorFaturamentoCAER");

			CONTROLADOR_FATURAMENTO_CAERN_SEJB = propriedades
					.getProperty("ControladorFaturamentoCAERN");
			
			CONTROLADOR_FATURAMENTO_CAEMA_SEJB = propriedades
					.getProperty("ControladorFaturamentoCAEMA");
			
			CONTROLADOR_FATURAMENTO_COSANPA_SEJB = propriedades
					.getProperty("ControladorFaturamentoCOSANPA");
			
			CONTROLADOR_FATURAMENTO_JUAZEIRO_SEJB = propriedades
					.getProperty("ControladorFaturamentoJUAZEIRO");
			
			CONTROLADOR_FATURAMENTO_COSAMA_SEJB = propriedades
			.getProperty("ControladorFaturamentoCOSAMA");
			// =====================================================================================================

			// ESQUEMA DOS CONTROLADORES
			// =====================================================================================================
			CONTROLADOR_MICROMEDICAO_COMPESA_SEJB = propriedades
					.getProperty("ControladorMicromedicaoCOMPESA");

			CONTROLADOR_MICROMEDICAO_CAER_SEJB = propriedades
					.getProperty("ControladorMicromedicaoCAER");

			CONTROLADOR_MICROMEDICAO_CAERN_SEJB = propriedades
					.getProperty("ControladorMicromedicaoCAERN");
			
			CONTROLADOR_MICROMEDICAO_CAEMA_SEJB = propriedades
					.getProperty("ControladorMicromedicaoCAEMA");
			
			CONTROLADOR_MICROMEDICAO_JUAZEIRO_SEJB = propriedades
					.getProperty("ControladorMicromedicaoJUAZEIRO");
			
			CONTROLADOR_MICROMEDICAO_COSAMA_SEJB = propriedades
			.getProperty("ControladorMicromedicaoCOSAMA");
			// =====================================================================================================

			// ESQUEMA DOS CONTROLADORES
			// =====================================================================================================
			CONTROLADOR_ARRECADACAO_COMPESA_SEJB = propriedades
					.getProperty("ControladorArrecadacaoCOMPESA");

			CONTROLADOR_ARRECADACAO_CAER_SEJB = propriedades
					.getProperty("ControladorArrecadacaoCAER");

			CONTROLADOR_ARRECADACAO_CAERN_SEJB = propriedades
					.getProperty("ControladorArrecadacaoCAERN");
			
			CONTROLADOR_ARRECADACAO_CAEMA_SEJB = propriedades
			        .getProperty("ControladorArrecadacaoCAEMA");
			
			CONTROLADOR_ARRECADACAO_COSANPA_SEJB = propriedades
	        		.getProperty("ControladorArrecadacaoCOSANPA");
			
			CONTROLADOR_ARRECADACAO_JUAZEIRO_SEJB = propriedades
    				.getProperty("ControladorArrecadacaoJUAZEIRO");
			
			CONTROLADOR_ARRECADACAO_COSAMA_SEJB = propriedades
			.getProperty("ControladorArrecadacaoCOSAMA");
			// =====================================================================================================
			
			//ESQUEMA DOS CONTROLADORES
			// =====================================================================================================
			CONTROLADOR_COBRANCA_COMPESA_SEJB = propriedades
					.getProperty("ControladorCobrancaCOMPESA");

			CONTROLADOR_COBRANCA_CAER_SEJB = propriedades
					.getProperty("ControladorCobrancaCAER");

			CONTROLADOR_COBRANCA_CAERN_SEJB = propriedades
					.getProperty("ControladorCobrancaCAERN");
			
			CONTROLADOR_COBRANCA_CAEMA_SEJB = propriedades
					.getProperty("ControladorCobrancaCAEMA");
			
			CONTROLADOR_COBRANCA_COSANPA_SEJB = propriedades
					.getProperty("ControladorCobrancaCOSANPA");
			
			CONTROLADOR_COBRANCA_JUAZEIRO_SEJB = propriedades
					.getProperty("ControladorCobrancaJUAZEIRO");
			
			CONTROLADOR_COBRANCA_COSAMA_SEJB = propriedades
			.getProperty("ControladorCobrancaCOSAMA");
			// =====================================================================================================
			
			// ESQUEMA DOS CONTROLADORES
			// =====================================================================================================
			CONTROLADOR_CADASTRO_COMPESA_SEJB = propriedades
					.getProperty("ControladorCadastroCOMPESA");

			CONTROLADOR_CADASTRO_CAER_SEJB = propriedades
					.getProperty("ControladorCadastroCAER");

			CONTROLADOR_CADASTRO_CAERN_SEJB = propriedades
					.getProperty("ControladorCadastroCAERN");
			
			CONTROLADOR_CADASTRO_CAEMA_SEJB = propriedades
					.getProperty("ControladorCadastroCAEMA");
			
			CONTROLADOR_CADASTRO_COSANPA_SEJB = propriedades
					.getProperty("ControladorCadastroCOSANPA");
			
			CONTROLADOR_CADASTRO_JUAZEIRO_SEJB = propriedades
					.getProperty("ControladorCadastroJUAZEIRO");
			
			CONTROLADOR_CADASTRO_COSAMA_SEJB = propriedades
			.getProperty("ControladorCadastroCOSAMA");
			// =====================================================================================================
			
			//ESQUEMA DOS CONTROLADORES
			// =====================================================================================================
			CONTROLADOR_FINANCEIRO_COMPESA_SEJB = propriedades
					.getProperty("ControladorFinanceiroCOMPESA");

			CONTROLADOR_FINANCEIRO_CAER_SEJB = propriedades
					.getProperty("ControladorFinanceiroCAER");

			CONTROLADOR_FINANCEIRO_CAERN_SEJB = propriedades
					.getProperty("ControladorFinanceiroCAERN");
			
			CONTROLADOR_FINANCEIRO_CAEMA_SEJB = propriedades
					.getProperty("ControladorFinanceiroCAEMA");
			
			CONTROLADOR_FINANCEIRO_COSANPA_SEJB = propriedades
					.getProperty("ControladorFinanceiroCOSANPA");
			
			CONTROLADOR_FINANCEIRO_JUAZEIRO_SEJB = propriedades
					.getProperty("ControladorFinanceiroJUAZEIRO");
			
			CONTROLADOR_FINANCEIRO_COSAMA_SEJB = propriedades
			.getProperty("ControladorFinanceiroCOSAMA");
			// =====================================================================================================
			
			CONTROLADOR_RELATORIO_FATURAMENTO_SEJB = 
				propriedades.getProperty("ControladorRelatorioFaturamento");
			
			CONTROLADOR_INTEGRACAO_SEJB = propriedades.getProperty("ControladorIntegracao");
			
			BATCH_GERAR_CREDITO_SITUACAO_ESPECIAL_FATURAMENTO_MDB = propriedades
			.getProperty("QueueBatchGerarCreditoSituacaoEspecialFaturamentoMDB");
			
			BATCH_GERAR_MOVIMENTO_HIDROMETRO = propriedades.getProperty("QueueBatchGerarMovimentoHidrometroMDB");
			
			BATCH_GERAR_BONUS_TARIFA_SOCIAL = propriedades.getProperty("QueueBatchGerarBonusTarifaSocialMDB");
			
			BATCH_EXCLUIR_IMOVEIS_DA_TARIFA_SOCIAL = propriedades.getProperty("QueueBatchExcluirImoveisDaTarifaSocialMDB");
			
			BATCH_GERAR_ARQUIVO_TEXTO_PAGAMENTOS_CONTAS_COBRANCA_POR_EMPRESA = propriedades.getProperty("QueueBatchGerarArquivoTextoPagamentosContasCobrancaEmpresaMDB");
			
			
			BATCH_GERAR_RESUMO_NEGATIVACAO_MDB = propriedades
			.getProperty("QueueBatchGerarResumoNegativacaoMDB");
			
			BATCH_ACOMPANHAR_PAGAMENTO_DO_PARCELAMENTO_MDB = propriedades
			.getProperty("QueueBatchAcompanharPagamentoDoParcelamentoMDB");
			
			BATCH_GERAR_CARTAS_DE_FINAL_DE_ANO_MDB = propriedades
			.getProperty("QueueBatchGerarCartasDeFinalDeAnoMDB");
			
			BATCH_EMITIR_CARTAS_DE_FINAL_DE_ANO_MDB = propriedades
			.getProperty("QueueBatchEmitirCartasDeFinalDeAnoMDB");
			
			BATCH_GERAR_ARQUIVO_TEXTO_CONTAS_PROJETOS_ESPECIAIS = propriedades
			.getProperty("QueueBatchGerarArquivoTextoContasProjetosEspeciaisMDB");		
			
			BATCH_INSERIR_PAGAMENTOS_FATURAS_ESPECIAIS = propriedades
			.getProperty("QueueBatchInserirPagamentosFaturasEspeciaisMDB");
			
			BATCH_SUSPENDER_IMOVEL_EM_PROGRAMA_ESPECIAL = propriedades
			.getProperty("QueueBatchSuspenderImovelEmProgramaEspecialMDB");
			
			BATCH_RESUMO_SIMULACAO_FATURAMENTO = propriedades
			.getProperty("QueueBatchGerarResumoSimulacaoFaturamentoMDB");
			
			BATCH_DETERMINAR_CONFIRMACAO_DA_NEGATIVACAO = propriedades
			.getProperty("QueueBatchDeterminarConfirmacaoDaNegativacaoMDB");
			
			BATCH_EMITIR_ORDEM_FISCALIZACAO = propriedades
			.getProperty("QueueBatchEmitirOrdemDeFiscalizacaoMDB");
			
			BATCH_GERAR_ARQUIVO_ORDEM_FISCALIZACAO  = propriedades
			.getProperty("QueueBatchGerarArquivoOrdemDeFiscalizacaoMDB");
			
			BATCH_ENVIO_EMAIL_CONTA_PARA_CLIENTE = propriedades
			.getProperty("QueueBatchEnvioEmailContaParaClienteMDB");
			
			BATCH_GERAR_DECLARACAO_QUITACAO_ANUAL_DEBITOS = propriedades
			.getProperty("QueueBatchGerarDadosDeclaracaoQuitacaoAnualDebitosMDB");
			
			BATCH_GERAR_ARQUIVO_TEXTO_DECLARACAO_QUITACAO_ANUAL_DEBITOS = propriedades
			.getProperty("QueueBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitosMDB");
			
			BATCH_GERAR_RESUMO_PENDENCIA_POR_ANO = propriedades
			.getProperty("QueueBatchGerarResumoPendenciaPorAnoMDB");
			
			BATCH_ATUALIZAR_CODIGO_DEBITO_AUTOMATICO = propriedades
			.getProperty("QueueBatchAtualizarCodigoDebitoAutomaticoMDB");
			
			BATCH_ATUALIZAR_COMANDO_ATIVIDADE_ACAO_COBRANCA = propriedades
			.getProperty("QueueBatchAtualizarComandoAtividadeAcaoCobrancaMDB");
			
			BATCH_EMITIR_DOCUMENTO_COBRANCA = propriedades
			.getProperty("QueueBatchEmitirDocumentoCobrancaMDB");
			
			BATCH_GERAR_RESUMO_LIGACOES_ECONOMIAS_POR_ANO = propriedades
			.getProperty("QueueBatchGerarResumoLigacoesEconomiasPorAnoMDB");
			
			BATCH_GERAR_RESUMO_INDICADORES_MICROMEDICAO_POR_ANO_MDB = propriedades
			.getProperty("QueueBatchGerarResumoIndicadoresMicromedicaoPorAnoMDB");
			
			BATCH_GERAR_RESUMO_CONSUMO_AGUA_POR_ANO_MDB = propriedades
			.getProperty("QueueBatchGerarResumoConsumoAguaPorAnoMDB");
			
			BATCH_GERAR_RESUMO_RECEITA = propriedades
			.getProperty("QueueBatchGerarResumoReceitaMDB");
			
			BATCH_GERAR_RESUMO_FATURAMENTO_POR_ANO_MDB = propriedades
            .getProperty("QueueBatchGerarResumoFaturamentoPorAnoMDB"); 
			
			BATCH_GERAR_RESUMO_ARRECADACAO_POR_ANO_MDB = propriedades
			.getProperty("QueueBatchGerarResumoArrecadacaoPorAnoMDB");
			
			BATCH_GERAR_RESUMO_COLETA_ESGOTO_POR_ANO_MDB = propriedades
			.getProperty("QueueBatchGerarResumoColetaEsgotoPorAnoMDB");
			
			BATCH_GERAR_RESUMO_REGISTRO_ATENDIMENTO_POR_ANO_MDB = propriedades
			.getProperty("QueueBatchGerarResumoRegistroAtendimentoPorAnoMDB");
			
			BATCH_GERAR_RESUMO_INSTALACOES_HIDROMETROS_POR_ANO_MDB = propriedades
			.getProperty("QueueBatchGerarResumoInstalacoesHidrometrosPorAnoMDB");
			
			BATCH_GERAR_RESUMO_PARCELAMENTO_POR_ANO_MDB = propriedades
			.getProperty("QueueBatchGerarResumoParcelamentoPorAnoMDB");
			
			BATCH_GERAR_TAXA_PERCENTUAL_TARIFA_MINIMA_CORTADO_MDB = propriedades
			.getProperty("QueueBatchGerarTaxaPercentualTarifaMinimaCortadoMDB");
			
			BATCH_GERAR_RESUMO_REFATURAMENTO_NOVO_MDB = propriedades
			.getProperty("QueueBatchGerarResumoReFaturamentoNovoMDB");
			
			BATCH_ALTERAR_INSCRICOES_IMOVEIS_MDB = propriedades
			.getProperty("QueueBatchAlterarInscricaoImovelMDB");
			
			BATCH_GERAR_PRESCREVER_DEBITOS_DE_IMOVEIS_MDB = propriedades
			.getProperty("QueueBatchGerarPrescreverDebitosDeImoveisMDB");
			
			BATCH_VERIFICAR_FATURAMENTO_IMOVEIS_CORTADOS_MDB = propriedades
			.getProperty("QueueBatchVerificarFaturamentoImoveisCortadosMDB");
			
			BATCH_GERAR_RESUMO_HISTOGRAMA_AGUA_ESGOTO_SEM_QUADRA = propriedades
			.getProperty("QueueBatchGerarResumoHistogramaAguaEsgotoSemQuadraMDB");

			BATCH_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL_MDB = propriedades
			.getProperty("QueueBatchReligarImoveisCortadosComConsumoRealMDB");
			
			BATCH_PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_MANUAL_MDB = propriedades
			.getProperty("QueueBatchPrescreverDebitosImoveisPublicosManualMDB");
			
			BATCH_PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_AUTOMATICO_MDB = propriedades
			.getProperty("QueueBatchPrescreverDebitosImoveisPublicosAutomaticoMDB");
			
			BATCH_PROCESSAR_ENCERRAMENTO_ORDEM_SERVICO_ACAO_COBRANCA_MDB = propriedades
			.getProperty("QueueBatchProcessarEncerramentoOSAcaoCobrancaMDB");
			
			BATCH_AUTOMATIZAR_PERFIS_DE_GRANDES_CONSUMIDORES_MDB = propriedades
			.getProperty("QueueBatchAutomatizarPerfisDeGrandesConsumidoresMDB");
			
			BATCH_GERAR_RA_OS_ANORMALIDADE_CONSUMO = propriedades
			.getProperty("QueueBatchGerarRAOSAnormalidadeConsumoMDB");
			
			BATCH_PROCESSAR_COMANDO_GERADO  = propriedades
			.getProperty("QueueBatchProcessarComandoGeradoMDB");
			
			BATCH_GERAR_CARTA_TARIFA_SOCIAL   = propriedades
			.getProperty("QueueBatchGerarCartaTarifaSocialMDB");
			
			BATCH_RETIRAR_IMOVEL_TARIFA_SOCIAL = propriedades
			.getProperty("QueueBatchRetirarImovelTarifaSocialMDB");
			
			BATCH_GERAR_TXT_IMPRESSAO_CONTAS_BRAILLE = propriedades
			.getProperty("QueueBatchGerarTxtImpressaoContasBrailleMDB");
			
			BATCH_ENCERRAR_COMANDO_DE_COBRANCA_POR_EMPRESA = propriedades
			.getProperty("QueueBatchEncerrarComandosDeCobrancaPorEmpresaMDB");
			
			BATCH_GERAR_TXT_OS_INSPECAO_ANORMALIDADE = propriedades
			.getProperty("QueueBatchGerarTxtOsInspecaoAnormalidadeMDB");
			
			BATCH_PROCESSAR_ARQUIVO_TXT_ENCERRAMENTO_OS_COBRANCA = propriedades
			.getProperty("QueueBatchProcessarArquivoTxtEncerramentoOSCobrancaMDB");
			
			BATCH_GERAR_ARQUIVO_TXT_OS_CONTAS_PAGAS_PARCELADAS = propriedades
			.getProperty("QueueBatchGerarArquivoTextoOSContasPagasParceladasMDB");
			
			BATCH_ENCERRAR_COMANDO_OS_SELETIVA_INSPECAO_ANORMALIDADE = propriedades
			.getProperty("QueueBatchEncerrarComandoOSSeletivaInspecaoAnormalidadeMDB");
			
			BATCH_GERAR_DADOS_ARQUIVO_ACOMPANHAMENTO_SERVICO_MDB = propriedades
			.getProperty("QueueBatchGerarDadosArquivoAcompanhamentoServicoMDB");
			
			BATCH_PROGRAMACAO_AUTO_ROTEIRO_ACOMP_SERVICO = propriedades
			.getProperty("QueueBatchProgramacaoAutoRoteiroAcompServicoMDB");
			
			BATCH_PROCESSAR_ENCERRAMENTO_OS_FISCALIZACAO_DECURSO_PRAZO_MDB = propriedades
			.getProperty("QueueBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazoMDB");		

			BATCH_SUSPENDER_LEITURA_PARA_IMOVEL_COM_HIDROMETRO_RETIRADO_MDB = propriedades
			.getProperty("QueueBatchSuspenderLeituraParaImovelComHidrometroRetiradoMDB");
			
			BATCH_SUSPENDER_LEITURA_PARA_IMOVEL_COM_CONSUMO_REAL_NAO_SUPERIOR_A_10_MDB = propriedades
			.getProperty("QueueBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10MDB");

			BATCH_ENCERRAR_OS_VISITA_COBRANCA_MDB = propriedades
			.getProperty("QueueBatchEncerrarOSVisitaCobrancaMDB");	
			
			BATCH_PROCESSAR_ATUAL_ORDENS_SERVICO_ARQ_TXT_MDB = propriedades
			.getProperty("QueueBatchProcessarAtualizacaoDasOrdensServicoDoArquivoTextoMDB");			

			BATCH_GERAR_BOLETIM_COBRANCA_POR_RESULTADO = propriedades
					.getProperty("QueueBatchGerarBoletimCobrancaPorResultadoMDB");

			BATCH_GERAR_CREDITO_A_REALIZAR_CONTRATO_DEMANDA_RESIDENCIAL_MDB = propriedades
			.getProperty("QueueBatchGerarCreditoARealizarContratoDemandaImovelResidencialMDB");
			
			BATCH_GERAR_DEBITO_A_COBRAR_CONTRATO_DEMANDA_RESIDENCIAL_MDB = propriedades
			.getProperty("QueueBatchGerarDebitoACobrarContratoDemandaImovelResidencialMDB");
			
			BATCH_CANCELAR_GRUPO_FATURAMENTO_MDB = propriedades
					.getProperty("QueueBatchCancelarGrupoFaturamentoMDB");		
			
			BATCH_GERAR_LANCAMENTO_CONTABEIS_VOLUMES_CONSUMIDOS_NAO_FATURADOS_MDB = propriedades
					.getProperty("QueueBatchGerarLancamentosContabeisVolumesConsumidosNaoFaturadosMDB");

			BATCH_GERAR_FATURAMENTO_BRUTO_LIQUIDO = propriedades
					.getProperty("QueueBatchGerarFaturamentoBrutoLiquidoMDB");

			BATCH_RETIRAR_IMOVEIS_CONTAS_EMPRESA_COBRANCA_MDB = propriedades.getProperty("QueueBatchRetirarImoveisContasEmpresaCobrancaMDB");
			
			BATCH_INCLUIR_CONTAS_EM_COBRANCA_MDB = propriedades
					.getProperty("QueueBatchIncluirContasEmCobrancaMDB");
			BATCH_PARALISAR_FATURAMENTO_MDB = propriedades
					.getProperty("QueueBatchParalisarFaturamentoMDB");

			BATCH_GERAR_RESUMO_ARRECADACAO_ANALITICO_DADOS = propriedades
					.getProperty("QueueBatchGerarResumoArrecadacaoAnaliticoDadosMDB");
			
			BATCH_GERAR_RESUMO_ARRECADACAO_ANALITICO_TOTAIS_1 = propriedades
			.getProperty("QueueBatchGerarResumoArrecadacaoAnaliticoTotais1MDB");
			
			BATCH_GERAR_RESUMO_ARRECADACAO_ANALITICO_TOTAIS_2 = propriedades
					.getProperty("QueueBatchGerarResumoArrecadacaoAnaliticoTotais2MDB");
			
			BATCH_EXECUTAR_CARGA_ARRECADACAO_GERENCIAL_POR_ANO = propriedades
					.getProperty("QueueBatchExecutarCargaArrecadacaoGerencalAnoMDB");
			
			BATCH_EXECUTAR_CARGA_REGISTRO_ATENDIMENTO_POR_ANO = propriedades
					.getProperty("QueueBatchExecutarCargaRegistroAtendimentoPorAnoMDB");
			
			BATCH_EXECUTAR_CARGA_RESUMO_FATURAMENTO_POR_ANO = propriedades
					.getProperty("QueueBatchExecutarCargaResumoFaturamentoPorAnoMDB");
			
			BATCH_EXECUTAR_CARGA_INDICADORES_DESEMPENHO_FATURAMENTO_POR_ANO = propriedades
					.getProperty("QueueBatchExecutarCargaIndicadoresDesempenhoFaturamentoPorAnoMDB");
			
			BATCH_EXECUTAR_CARGA_INDICADORES_DESEMPENHO_FATURAMENTO_VARIOS_ANOS = propriedades
					.getProperty("QueueBatchExecutarCargaIndicadoresDesempenhoFaturamentoVariosAnosMDB");
			
			BATCH_EXECUTAR_CARGA_COLETA_ESGOTO_POR_ANO = propriedades
					.getProperty("QueueBatchExecutarCargaColetaEsgotoPorAnoMDB");
			
			BATCH_EXECUTAR_CARGA_CONSUMO_AGUA_POR_ANO = propriedades
					.getProperty("QueueBatchExecutarCargaConsumoAguaPorAnoMDB");
			
			BATCH_EXECUTAR_CARGA_LEITURA_ANORMALIDADE_POR_ANO = propriedades
					.getProperty("QueueBatchExecutarCargaLeituraAnormalidadePorAnoMDB");
			
			BATCH_EXECUTAR_CARGA_INDICADORES_DESEMPENHO_MICROMEDICAO_VARIOS_ANOS = propriedades
					.getProperty("QueueBatchExecutarCargaIndicadoresDesempenhoMicromedicaoVariosAnosMDB");
			
			BATCH_EXECUTAR_CARGA_INDICADORES_DESEMPENHO_MICROMEDICAO_POR_ANO = propriedades
					.getProperty("QueueBatchExecutarCargaIndicadoresDesempenhoMicromedicaoPorAnoMDB");
			
			BATCH_EXECUTAR_CARGA_LIGACOES_ECONOMIAS_POR_ANO = propriedades
					.getProperty("QueueBatchExecutarCargaLigacoesEconomiasPorAnoMDB");
			
			BATCH_EXECUTAR_CARGA_INDICADOR_LIGACAO_ECONOMIA_POR_ANO = propriedades
					.getProperty("QueueBatchExecutarCargaIndicadorLigacaoEconomiaPorAnoMDB");
			
			BATCH_EXECUTAR_CARGA_RESUMO_PARCELAMENTO_POR_ANO = propriedades
					.getProperty("QueueBatchExecutarCargaResumoParcelamentoPorAnoMDB");
			
			BATCH_EXECUTAR_CARGA_RESUMO_PENDENCIA_POR_ANO = propriedades
					.getProperty("QueueBatchExecutarCargaResumoPendenciaPorAnoMDB");
			
			BATCH_EXECUTAR_CARGA_PENDENCIA_SEM_QUADRA_VARIOS_ANOS = propriedades
					.getProperty("QueueBatchExecutarCargaPendenciaSemQuadraVariosAnosMDB");
			
			BATCH_GERAR_RESUMO_PENDENCIA_BD = propriedades
					.getProperty("QueueBatchGerarResumoPendenciaBDMDB");
			
			BATCH_RETIRAR_CONTAS_REVISAO_EXTRATO_ENTRADA_PARCELAMENTO = propriedades
					.getProperty("QueueBatchRetirarContasRevisaoExtratoEntradaParcelamentoMDB");
			
			BATCH_GERAR_FATURA_PROGRAMA_ESPECIAL = propriedades
					.getProperty("QueueBatchGerarFaturaProgramaEspecialMDB");
			
			BATCH_RECEITA_LIQUIDA_INDIRETA_DE_AGUA_E_ESGOTO = propriedades
					.getProperty("QueueBatchReceitaLiquidaIndiretaDeAguaEEsgotoMDB");
			
			BATCH_GERAR_RESUMO_FATURAMENTO_ANALITICO_PARTE_1 = propriedades
					.getProperty("QueueBatchGerarResumoFaturamentoAnaliticoParte1MDB");
			
			BATCH_GERAR_RESUMO_FATURAMENTO_ANALITICO_PARTE_2 = propriedades
			.getProperty("QueueBatchGerarResumoFaturamentoAnaliticoParte2MDB");
			
			BATCH_GERAR_RESUMO_FATURAMENTO_ANALITICO_TOTAIS = propriedades
					.getProperty("QueueBatchGerarResumoFaturamentoAnaliticoTotaisMDB");
			
			BATCH_GERAR_CREDITO_REALIZAR_PAGAMENTO_DUPLICIDADE_VIVA_AGUA = propriedades
					.getProperty("QueueBatchGerarCreditoARealizarPagamentoDuplicidadeVivaAguaMDB");
			
			AUTOMATIZAR_CARGA_MENSAL_PENTAHO = propriedades
					.getProperty("QueueBatchAutomatizarCargaPentahoMDB");
			
			BATCH_GERAR_CONTAS_A_RECEBER_CONTABIL_ANALITICO_TABELA_AUXILIAR = propriedades
					.getProperty("QueueBatchGerarContasAReceberContabilAnaliticoTabelaAuxiliarMDB");
		
			BATCH_GERAR_CONTAS_A_RECEBER_CONTABIL_ANALITICO = propriedades
					.getProperty("QueueBatchGerarContasAReceberContabilAnaliticoMDB");
			
			BATCH_GERAR_CONTAS_A_RECEBER_CONTABIL_SINTETICO = propriedades
					.getProperty("QueueBatchGerarContasAReceberContabilSinteticoMDB");			
			
			BATCH_GERAR_UN_RESUMO_ARRECADACAO_BD = propriedades
					.getProperty("QueueBatchGerarUnResumoArrecadacaoBDMDB");
			
			BATCH_BAIXA_AUTOMATICA_PAGAMENTOS_NAO_CLASSIFICADOS = propriedades
					.getProperty("QueueBatchBaixaAutomaticaPagamentosNaoClassificadosMDB");
			
			BATCH_GERAR_ARQUIVO_CARTEIRA_17 = propriedades
					.getProperty("QueueBatchGerarArquivoCarteira17MDB");
			
			BATCH_REGISTRAR_MOVIMENTOS_ARRECADADORES = propriedades
					.getProperty("QueueBatchRegistrarMovimentoArrecadadoresMDB");
			
			BATCH_RETIFICAR_CONTAS_INDEVIDAS_CAERN = propriedades
					.getProperty("QueueBatchRetificarContasCaernMDB");
			
			BATCH_PROCESSAR_AMORTIZACAO_DIVIDA_ATIVA_ARRECADACAO = propriedades
					.getProperty("QueueBatchProcessarAmortizacaoDividaAtivaArrecadacaoMDB");
			
			BATCH_PROCESSAR_AMORTIZACAO_DIVIDA_ATIVA_FATURAMENTO = propriedades
					.getProperty("QueueBatchProcessarAmortizacaoDividaAtivaFaturamentoMDB");
			
			BATCH_GERAR_DIVIDA_ATIVA = propriedades
					.getProperty("QueueBatchGerarDividaAtivaMDB");
			
			BATCH_GERAR_RESUMO_DIVIDA_ATIVA_ANUAL = propriedades
					.getProperty("QueueBatchGerarResumoDividaAtivaAnualMDB");
			
			BATCH_ENVIAR_EMAIL_COBRANCA_FATURAMENTO = propriedades.
					getProperty("QueueBatchEnviarEMAILCobrancaFaturamentoMDB");

			BATCH_PREPARAR_DADOS_SMS_EMAIL_COBRANCA_CONTA = propriedades
					.getProperty("QueueBatchPrepararDadosSMSEmailCobrancaContaMDB");
			
			BATCH_GERAR_CONTAS = propriedades.getProperty("QueueBatchGerarContasMDB");
			

			BATCH_RETORNO_SMS_EM_LOTE = propriedades
					.getProperty("QueueBatchRetornoSMSEmLoteMDB");
			
			BATCH_TRANSMITIR_SMS_EM_LOTE_COBRANCA_FATURAMENTO = propriedades.
					getProperty("QueueBatchTransmitirSMSEmLoteCobrancaFaturamento");
			
			BATCH_PREPARAR_DADOS_SMS_EMAIL_ACAO_COBRANCA = propriedades
					.getProperty("QueueBatchPrepararDadosSMSEmailAcaoCobrancaMDB");
			
			BATCH_GERAR_ROTEIRO_DISPOSITIVO_MOVEL_MDB = propriedades
					.getProperty("QueueBatchGerarRoteiroDispositivoMovelMDB");
			
			BATCH_ATUALIZAR_DADOS_CADASTRAIS_IMOVEL_RECADASTRAMENTO_MDB = propriedades
					.getProperty("QueueBatchAtualizarDadosCadastraisImovelRecadastramentoMDB");

			BATCH_RESUMO_ARRECADACAO_ATUALIZA_DADOS = propriedades
					.getProperty("QueueBatchResumoArrecadacaoAtualizaDadosMDB");

			BATCH_GERAR_RESUMO_COBRANCA_ATUALIZA_DADOS = propriedades
					.getProperty("QueueBatchGerarResumoCobrancaAtualizaDadosMDB");

			BATCH_GERAR_RESUMO_FATURAMENTO_ATUALIZA_DADOS = propriedades
					.getProperty("QueueBatchGerarResumoFaturamentoAtualizaDadosMDB");
			
			BATCH_GERAR_RESUMO_MICROMEDICAO_ATUALIZA_DADOS = propriedades
					.getProperty("QueueBatchGerarResumoMicromedicaoAtualizaDadosMDB");

			BATCH_GERAR_RESUMO_ATENDIMENTO_PUBLICO_ATUALIZA_DADOS = propriedades
					.getProperty("QueueBatchGerarResumoAtendimentoPublicoAtualizaDadosMDB");

			BATCH_GERAR_RESUMO_PARCELAMENTO_ATUALIZA_DADOS = propriedades
					.getProperty("QueueBatchGerarResumoParcelamentoAtualizaDadosMDB");
			
			BATCH_GERAR_RESUMO_CADASTRO_ATUALIZA_DADOS = propriedades
					.getProperty("QueueBatchGerarResumoCadastroAtualizaDadosMDB");
			
			CONTROLADOR_ATUALIZACAO_CADASTRAL_SEJB = propriedades
					.getProperty("ControladorAtualizacaoCadastral");
			
			BATCH_RELIGAR_IMOVEIS_CORTADOS_COM_LEITURA_REAL_CONSUMO = propriedades
					.getProperty("QueueBatchReligarImoveisCortadosComLeituraRealConsumoMDB");
			
		
			stream.close();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

}