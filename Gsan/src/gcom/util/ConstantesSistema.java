  
package gcom.util;

import java.math.BigDecimal;

/**
 * Constantes do sistema
 * 
 * @author rodrigo
 */
public interface ConstantesSistema {

	String COLECAO_UNIDADES_PROCESSAMENTO_BATCH = "ColecaoBatch";
	
	Short SET_ZERO = new Short("0");
	Short ZERO = new Short("0");
	
	BigDecimal VALOR_ZERO = new BigDecimal("0.00");

	Integer INVALIDO_ID = -1;
	
	int INDICADOR_SIM = 1;
	int INDICADOR_NAO = 2;
	
	String IMOVEL_PERFIL_CANCELADOEXCLUIDO = "CANCEL/INEXISTENTE";

	int NUMERO_NAO_INFORMADO = -1;
	int NUMERO_MAXIMO_REGISTROS_MANUTENCAO = 50;
	int NUMERO_MAXIMO_CONSUMO_MINIMO_FIXADO = 100;
	int NUMERO_MAXIMO_REGISTROS_PESQUISA = 50;

	Short INDICADOR_USO_ATIVO = new Short("1");
	Short INDICADOR_USO_DESATIVO = new Short("2");
	
	Short INDICADOR_ATUALIZACAO_CADASTRAL_ATIVO = new Short("1");
	Short INDICADOR_ATUALIZACAO_CADASTRAL_DESATIVO = new Short("2");

	Short TIPO_PESQUISA_INICIAL = new Short("1");
	Short TIPO_PESQUISA_COMPLETA = new Short("2");

	int INDICADOR_IMOVEL_EXCLUIDO = 1;
	int INDICADOR_IMOVEL_ATIVO = 2;

	Short INDICADOR_PESSOA_FISICA = new Short("1");
	Short INDICADOR_PESSOA_JURIDICA = new Short("2");
	
	Integer INDICADOR_REGISTRO_CODIGO_W = new Integer(15);

	String CODIGO_INEXISTENTE = new String("C�digo Inexistente");
	String CODIGO_LOCAL_ARMAZENAGEM_INEXISTENTE = new String("Local de Armazenagem Inexistente");
	String CODIGO_LOCALIDADE_INEXISTENTE = new String("Localidade Inexistente");
	String CODIGO_IMOVEL_INEXISTENTE = new String("Matr�cula Inexistente");
	String CODIGO_CLIENTE_INEXISTENTE = new String("Cliente Inexistente");
	String CODIGO_GUIA_DEVOLUCAO_INEXISTENTE = new String("Guia de Devolu��o Inexistente");
	String CODIGO_TIPO_DEBITO_INEXISTENTE = new String("Tipo de D�bito Inexistente");
	String SITUACAO_TAMPONADO = new String("TAMPONADO");
	String SITUACAO_LIGADO_FORA_DE_USO = new String("LIGADO FORA DE USO");
	String SITUACAO_LIGADO_RESTABELECIMENTO = new String("LIGADO RESTABELECIMENTO");
	String SITUACAO_LIGADO_REATIVACAO = new String("LIGADO REATIVA��O");

	Integer CLIENTE_IMOVEL_TIPO_RESPONSAVEL = new Integer(3);
	Integer CLIENTE_IMOVEL_TIPO_USUARIO = new Integer(2);
	Integer CLIENTE_IMOVEL_TIPO_PROPIETARIO = new Integer(1);

	Short INDICADOR_ENDERECO_CORRESPONDENCIA = new Short("1");
	Short INDICADOR_NAO_ENDERECO_CORRESPONDENCIA = new Short("2");
	Short INDICADOR_TELEFONE_PRINCIPAL = new Short("1");
	Short INDICADOR_NAO_TELEFONE_PRINCIPAL = new Short("2");

	String IMOVEL_ENVIO_CONTA = "RESPONSAVEL";
	Short INDICADOR_QUADRA_REDE_AGUA_INEXISTENTE = new Short("1");

	String DESCRICAO_QUADRA_REDE_AGUA_INEXISTENTE = "1";
	Short INDICADOR_QUADRA_REDE_AGUA_EXISTENTE = new Short("2");

	String DESCRICAO_QUADRA_REDE_AGUA_EXISTENTE = "2";
	Short INDICADOR_QUADRA_REDE_AGUA_PARCIAL_EXISTENTE = new Short("3");

	String DESCRICAO_QUADRA_REDE_AGUA_PARCIAL_EXISTENTE = "1";
	String DESCRICAO_QUADRA_REDE_AGUA_PARCIAL_EXISTENTE_2 = "2";

	Short INDICADOR_QUADRA_REDE_ESGOTO_INEXISTENTE = new Short("1");
	String DESCRICAO_QUADRA_REDE_ESGOTO_INEXISTENTE = "1";

	Short INDICADOR_QUADRA_REDE_ESGOTO_EXISTENTE = new Short("2");
	String DESCRICAO_QUADRA_REDE_ESGOTO_EXISTENTE = "2";

	Short INDICADOR_QUADRA_REDE_ESGOTO_PARCIAL_EXISTENTE = new Short("3");
	String DESCRICAO_QUADRA_REDE_ESGOTO_PARCIAL_EXISTENTE = "1";

	String DESCRICAO_QUADRA_REDE_ESGOTO_PARCIAL_EXISTENTE_2 = "2";

	Short SIM = new Short("1");
	Short NAO = new Short("2");
	Short TODOS = new Short("3");
	int VALOR_QUATRO = 4;
	String VALOR_TRES = "3";

	String ANORMALIDADE_LEITURA_INFORMADA = "1";
	String ANORMALIDADE_LEITURA_FATURADA = "2";
	String ANORMALIDADE_CONSUMO = "3";

	Integer INDICADOR_TARIFA_SOCIAL = new Integer("4");
	Integer INDICADOR_CATEGORIA_RESIDENCIA = new Integer("1");
	Integer NOVEMBRO = new Integer("11");

	boolean SENHA_ESPECIAL = true;
	boolean NAO_SENHA_ESPECIAL = false;

	String CALCULAR_AGUA = "AGUA";
	String CALCULAR_ESGOTO = "ESGOTO";

	String CONFIRMADA = "1";
	String NAO_CONFIRMADA = "2";

	Short INDICADOR_CREDITO = new Short("1");
	Short INDICADOR_DEBITO = new Short("2");

	String DEBITO_AUTOMATICO = "DEBITO AUTOMATICO";
	String CODIGO_BARRAS = "CODIGO BARRAS";
	String CODIGO_DE_BARRAS = "CODIGO DE BARRAS";
	String FICHA_DE_COMPENSACAO = "FICHA DE COMPENSA";
	String CARTAO_DEBITO = "CARTAO DE DEBITO";
	String CARTEIRA_17 = "CARTEIRA 17";

	int COM_ITENS = 2;
	int SEM_ITENS = 1;
	int MOVIMENTO_ABERTO = 1;
	int MOVIMENTO_FECHADO = 2;

	String ABERTO = "ABERTO";
	String FECHADO = "FECHADO";
	String ENVIO = "Envio";
	String RETORNO = "Retorno";
	String OK = "OK";
	Short REGISTROS_NAO_ACEITOS = new Short("2");

	int CODIGO_ENVIO = 1;
	int CODIGO_RETORNO = 2;

	String CLIENTE_RELACAO_TIPO_USUARIO_EXTENSO = "USUARIO";
	String CLIENTE_RELACAO_TIPO_PROPRIETARIO_EXTENSO = "PROPRIETARIO";

	Integer ANO_LIMITE = 1985;

	String DATA_LIMITE = "31/12/2010";

	// Tipo de Pagamento
	Integer CODIGO_TIPO_PAGAMENTO_CONTA = 3;
	
	//Tipo de Pagamento
	Integer CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAERN = 0;
	
	//TIPO_PAGAMENTO - CONTA CAER
	String CODIGO_TIPO_PAGAMENTO_CONTA_CAER = "01";
	//Integer CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAER = 1;
	
	//TIPO_PAGAMENTO - FATURA CAER
	String CODIGO_TIPO_PAGAMENTO_FATURA_CAER = "02";
	Integer CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAER = 2;
	
	String TIPO_PAGAMENTO_FATURA_CAER = "FATURA";
	
	//TIPO_PAGAMENTO - REAVISO CAER
	String CODIGO_TIPO_PAGAMENTO_REAVISO_CAER = "04";
	
	String TIPO_PAGAMENTO_REAVISO_CAER = "REAVISO DE DEBITOS";
	
	//TIPO_PAGAMENTO - GUIA PAGAMENTO CLIENTE CAER
	String CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER = "06";
	String TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER = "GUIA PAGAMENTO CLIENTE";
	
	//TIPO_PAGAMENTO - GUIA PAGAMENTO IMOVEL CAER
	//Integer CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_LEGADO_CAER = 9;
	String CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER = "09";
	String TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER = "GUIA PAGAMENTO IMOVEL";
	
	//TIPO_PAGAMENTO - DOCUMENTO COBRANï¿½A CLIENTE RESPONSAVEL CAER
	String CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER = "08";
	String TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER = "DOCUMENTO COBRANCA CLIENTE";
	
	//TIPO_PAGAMENTO - FATURA CLIENTE RESPONSAVEL CAER
	String CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER = "07";
	String TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER = "FATURA CLIENTE RESP";
	
	//TIPO_PAGAMENTO - DOCUMENTO COBRANCA IMOVEL CAER
	String CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER = "10";
	String TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER = "DOCUMENTO COBRANCA IMOVEL";
	
	String TIPO_PAGAMENTO_DEBITO_A_COBRAR = "DEBITO A COBRAR";
	String TIPO_PAGAMENTO_CONTA = "CONTA";
	String TIPO_PAGAMENTO_CONTA_LEGADO = "CONTA LEGADO";
	
	Integer CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO = 4;
	Integer CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_COM_IDENTIFICACAO_MATRICULA = 1;
	Integer CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_COM_IDENTIFICACAO_CLIENTE = 9;
	
	String TIPO_PAGAMENTO_GUIA_PAGAMENTO = "GUIA DE PAGAMENTO";
	String TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO = "GUIA DE PAGAMENTO LEGADO";

	Integer CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA = 5;
	String TIPO_PAGAMENTO_DOCUMENTO_COBRANCA = "DOCUMENTO DE COBRAN�A";
	String TIPO_PAGAMENTO_DOCUMENTO_COBRANCA_LEGADO = "DOCUMENTO DE COBRAN�A LEGADO";
	
	Integer CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE = 6;

	Integer CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL = 7;
	String TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL = "FATURA DO CLIENTE RESPONS�VEL";
	String TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_LEGADO = "FATURA DO CLIENTE RESPONS�VEL LEGADO";
	
	Integer CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA_NOVO = 8;
	
	String CODIGO_TIPO_PAGAMENTO_NEGOCIACAO_A_VISTA_LEGADO_CAEMA = "0";
	String TIPO_PAGAMENTO_NEGOCIACAO_A_VISTA_LEGADO_CAEMA = "NEGOCIA��O �� VISTA";
	
	String CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAEMA = "06";
	Integer CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAEMA_INTEGER = 6;
	
	String CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAEMA = "07";
	Integer CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAEMA_INTEGER = 7;
	
	String CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_CAEMA = "08";
	Integer CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_CAEMA_INTEGER = 8;
	
	String CODIGO_TIPO_PAGAMENTO_CAMPANHA_AVISO_DEBITO_LEGADO_CAEMA = "99";
	String TIPO_PAGAMENTO_AVISO_DEBITO_LEGADO_CAEMA = "AVISO DE DɉBITO";
	String TIPO_PAGAMENTO_CAMPANHA_LEGADO_CAEMA = "CAMPANHA";
	
	Integer CODIGO_TIPO_PAGAMENTO_LEGADO_COSANPA = 2;
	Integer CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_COSANPA = 1;
	
	Integer CODIGO_TIPO_DOCUMENTO_EXTRATO_LEGADO_COSANPA = 2;
	String TIPO_PAGAMENTO_EXTRATO_LEGADO = "EXTRATO LEGADO";
	
	Integer CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_COSANPA = 3;
	Integer CODIGO_TIPO_DOCUMENTO_DOCUMENTO_COBRANCA_LEGADO_COSANPA = 4;
	Integer CODIGO_TIPO_DOCUMENTO_FATURA_LEGADO_COSANPA = 5;
	
	Integer CODIGO_TIPO_PAGAMENTO_LEGADO_JUAZEIRO = 0;
	Integer CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_JUAZEIRO = 0;
	Integer CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_JUAZEIRO = 1;
	
	Integer CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_COSAMA = 1;
	Integer CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_COSAMA = 0;
	
	Integer CODIGO_TIPO_PAGAMENTO_EXTRATO_LEGADO_COSAMA = 2;
	Integer CODIGO_TIPO_DOCUMENTO_EXTRATO_LEGADO_COSAMA = 2;
	
	Integer CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA = 3;
	Integer CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA = 30;

	Integer CODIGO_MOVIMENTO_EXCLUSAO = 1;
	String DESCRICAO_MOVIMENTO_EXCLUSAO = "EXCLUSÃO";

	Integer CODIGO_MOVIMENTO_INCLUSAO = 2;
	String DESCRICAO_MOVIMENTO_INCLUSAO = "INCLUSÃO";

	Integer CODIGO_MOVIMENTO_DEBITO_NORMAL = 0;
	String DESCRICAO_MOVIMENTO_DEBITO_NORMAL = "DɉBITO NORMAL";

	Integer CODIGO_MOVIMENTO_CANCELAMENTO = 1;
	String DESCRICAO_MOVIMENTO_CANCELAMENTO = "CANCELAMENTO";

	String CODIGO_SITUACAO_AGENCIA_ATIVO = "A";
	String DESCRICAO_SITUACAO_AGENCIA_ATIVO = "ATIVO";

	String CODIGO_SITUACAO_AGENCIA_EM_REGIME_ENCERRAMENTO = "B";
	String DESCRICAO_SITUACAO_AGENCIA_EM_REGIME_ENCERRAMENTO = "EM REGIME DE ENCERRAMENTO";
	

	/**
	 * Permite efetuar o parcelamento dos débitos de um imóvel
	 * 
	 * [UC0214] Efetuar Parcelamento de débitos
	 * 
	 * Variáveis para definir tamanho das tabelas
	 * 
	 * @author Roberta Costa
	 * @date 22/03/2006
	 */
	Integer NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO = 4;
	Integer NUMERO_MAXIMO_REGISTROS_GUIA_PAGAMENTO = 4;
	Integer NUMERO_MAXIMO_REGISTROS_OPCAO_PARCELAMENTO = 4;

	/**
	 * Variáveis para definir tamanho míniximo das tabelas que precisam do scroll
	 * 
	 * @author Fernanda Paiva
	 * @date 31/03/2006
	 */
	Integer NUMERO_MAXIMO_REGISTROS_PARA_SCROLL = 6;

	/**
	 * Informa os dados para geraçã de relatório ou consulta
	 * 
	 * [UC0304] Informar Dados para Geração de Relatório ou Consulta
	 * 
	 * Variáveis para definir opções de totalização
	 * 
	 * @author Raphael Rossiter
	 * @date 17/05/2006
	 */

	int CODIGO_ESTADO = 1;
	String ESTADO = "ESTADO";

	int CODIGO_ESTADO_GRUPO_FATURAMENTO = 2;
	String ESTADO_GRUPO_FATURAMENTO = "ESTADO POR GRUPO DE FATURAMENTO";

	int CODIGO_ESTADO_GERENCIA_REGIONAL = 3;
	String ESTADO_GERENCIA_REGIONAL = "ESTADO POR GERʊNCIA REGIONAL";

	int CODIGO_ESTADO_ELO_POLO = 4;
	String ESTADO_ELO_POLO = "ESTADO POR ELO POLO";

	int CODIGO_ESTADO_LOCALIDADE = 5;
	String ESTADO_LOCALIDADE = "ESTADO POR LOCALIDADE";

	int CODIGO_GRUPO_FATURAMENTO = 6;
	String GRUPO_FATURAMENTO = "GRUPO DE FATURAMENTO";

	int CODIGO_GERENCIA_REGIONAL = 7;
	String GERENCIA_REGIONAL = "GER�NCIA REGIONAL";

	int CODIGO_GERENCIA_REGIONAL_ELO_POLO = 8;
	String GERENCIA_REGIONAL_ELO_POLO = "GER�NCIA REGIONAL POR ELO PO�LO";

	int CODIGO_GERENCIA_REGIONAL_LOCALIDADE = 9;
	String GERENCIA_REGIONAL_LOCALIDADE = "GERʊNCIA REGIONAL POR LOCALIDADE";

	int CODIGO_ELO_POLO = 10;
	String ELO_POLO = "ELO PO�LO";

	int CODIGO_ELO_POLO_LOCALIDADE = 11;
	String ELO_POLO_LOCALIDADE = "ELO PO�LO POR LOCALIDADE";

	int CODIGO_ELO_POLO_SETOR_COMERCIAL = 12;
	String ELO_POLO_SETOR_COMERCIAL = "ELO PO�LO POR SETOR COMERCIAL";

	int CODIGO_LOCALIDADE = 13;
	String LOCALIDADE = "LOCALIDADE";

	int CODIGO_LOCALIDADE_SETOR_COMERCIAL = 14;
	String LOCALIDADE_SETOR_COMERCIAL = "LOCALIDADE POR SETOR COMERCIAL";

	int CODIGO_LOCALIDADE_QUADRA = 15;
	String LOCALIDADE_QUADRA = "LOCALIDADE POR QUADRA";

	int CODIGO_SETOR_COMERCIAL = 16;
	String SETOR_COMERCIAL = "SETOR_COMERCIAL";

	int CODIGO_SETOR_COMERCIAL_QUADRA = 17;
	String SETOR_COMERCIAL_QUADRA = "SETOR_COMERCIAL POR QUADRA";
	
	int CODIGO_SETOR_COMERCIAL_ROTA = 28;
	String SETOR_COMERCIAL_ROTA = "SETOR_COMERCIAL POR ROTA";

	int CODIGO_QUADRA = 18;
	String QUADRA = "QUADRA";
	
	int CODIGO_ESTADO_GRUPO_COBRANCA = 19;
	String ESTADO_GRUPO_COBRANCA = "ESTADO POR GRUPO DE COBRAN�A";

	int CODIGO_GRUPO_COBRANCA = 20;
	String GRUPO_COBRANCA = "GRUPO DE COBRAN�A";
	
	int CODIGO_UNIDADE_NEGOCIO = 21;
	String UNIDADE_NEGOCIO = "UNIDADE DE NEG�CIO";
	
	int CODIGO_ESTADO_UNIDADE_NEGOCIO = 22;
	String ESTADO_UNIDADE_NEGOCIO = "ESTADO POR UNIDADE DE NEGӓCIO";
	
	int CODIGO_GERENCIA_REGIONAL_UNIDADE_NEGOCIO = 23;
	String GERENCIA_REGIONAL_UNIDADE_NEGOCIO = "GER�NCIA REGIONAL POR UNIDADE DE NEGӓCIO";
	
	int CODIGO_UNIDADE_NEGOCIO_LOCALIDADE = 24;
	String UNIDADE_NEGOCIO_LOCALIDADE = "UNIDADE DE NEG�CIO POR LOCALIDADE";

	int CODIGO_UNIDADE_NEGOCIO_SETOR_COMERCIAL = 25;
	String UNIDADE_NEGOCIO_SETOR_COMERCIAL = "UNIDADE DE NEG�CIO POR SETOR COMERCIAL";

	int CODIGO_ESTADO_MUNICIPIO = 26;
	String ESTADO_MUNICIPIO = "ESTADO POR MUNIC�PIO";
	
	int CODIGO_MUNICIPIO = 27;
	String MUNICIPIO = "MUNIC�PIO";
	
	String USUARIO_INEXISTENTE = new String("Usu�rio Inexistente");

	/**
	 * Permite a inclusão de um novo perfil de parcelamento
	 * 
	 * [UC0220] Inserir Perfil de Parcelamento
	 * 
	 * Variáveis para definir tamanho das tabelas
	 * 
	 * @author Vivianne Sousa
	 * @date 11/08/2006
	 */
	Integer NUMERO_MAXIMO_REGISTROS_PERFIL_PARCELAMENTO = 4;

	/**
	 * Comandos para a diferenï¿½a dos relatorios de [UC0164] Filtrar Imovel
	 * Outros Criterios
	 * 
	 */

	public static Integer GERAR_RELATORIO_DADOS_ECONOMIA_IMOVEL = new Integer(1);
	public static Integer GERAR_RELATORIO_DADOS_TARIFA_SOCIAL = new Integer(2);
	public static Integer GERAR_RELATORIO_IMOVEL = new Integer(3);
	public static Integer GERAR_RELATORIO_IMOVEIS_EXCLUIDOS_TARIFA_SOCIAL = new Integer(4);
	public static Integer GERAR_RELATORIO_DEBITOS = new Integer(5);

	short ATUALIZACAO_AUTOMATICA = 1;
	short ATUALIZACAO_NENHUMA = 2;
	short ATUALIZACAO_POSTERIOR = 3;

	int SITUACAO_REFERENCIA_PENDENTE = 1;
	int SITUACAO_REFERENCIA_ENCERRADA = 2;
	int SITUACAO_REFERENCIA_PENDENTE_AGUARDANDO_RETORNO_OS_REFERENCIA = 4;

	short INDICADOR_NOVO_SOLICITANTE = 2;
	short INDICADOR_INSERIR_SOLICITANTE_RA = 1;

	// Constante para o caso de uso [UC0440] Consultar Programação de
	// Abastecimento e Manutenção
	// Romulo Aurélio - 07/09/2006

	Integer SITUACAO_ABASTECIMENTO_MANUTENCAO = 1;
	Integer SITUACAO_MANUTENCAO = 2;
	Integer SITUACAO_ABASTECIMENTO = 3;
	Integer SITUACAO_ABERTO = 4;
	Integer EM_ABERTO = 1;
	Integer CONCLUIDA = 2;

	// Constante para o caso de uso [UC0455] Exibir Calendário para Elaboração
	// ou Acompanhamento de Roteiro
	// Romulo Aurélio - 22/09/2006

	Integer SITUACAO_ROTEIRO_ELABORACAO_FECHAMENTO_PREENCHIDO = 1;
	Integer SITUACAO_ROTEIRO_ELABORACAO_FECHAMENTO_NULO = 2;
	Integer SITUACAO_ROTEIRO_ELABORACAO_ABERTO = 3;
	Integer SITUACAO_ROTEIRO_ACOMPANHAMENTO_FECHAMENTO_PREENCHIDO = 4;
	Integer SITUACAO_ROTEIRO_ACOMPANHAMENTO_FECHAMENTO_NULO = 5;
	Integer SITUACAO_ROTEIRO_ACOMPANHAMENTO_ABERTO = 6;

	/**
	 * Permite a consulta de pagamentos e/ ou pagamentos histórico de um imóvel
	 * ou de um cliente ou de um aviso bancário ou de um movimento arrecadador.
	 * Permite também geração do relatório dos pagamentos de um intervalo de
	 * localidades
	 * 
	 * [UC0247] Consultar Pagamentos
	 * 
	 * Variáveis para definir tamanho das tabelas
	 * 
	 * @author Vivianne Sousa
	 * @date 16/10/2006
	 */
	Integer NUMERO_MAXIMO_REGISTROS_CONSULTA_PAGAMENTO = 10;

	/**
	  Permite a consulta de pagamentos e/ ou pagamentos histórico de um imóvel
	 * ou de um cliente ou de um aviso bancï¿½rio ou de um movimento arrecadador.
	 * Permite também geração do relatório dos pagamentos de um intervalo de
	 * localidades
	 *  
	 * Consultar Devoluções
	 * 
	 * Variáveis para definir tamanho das tabelas
	 * 
	 * @author Vivianne Sousa
	 * @date 17/10/2006
	 */
	Integer NUMERO_MAXIMO_REGISTROS_CONSULTA_DEVOLUCAO = 10;

	/**
	 * Permite que o usuário seleciona qual a origem dos serviço das OS da
	 * pesquisa, podendo ser Solicitados ou Seletivos
	 * 
	 * [UC0492] - Gerar Relatório Acompanhamento Execulção da OS
	 * 
	 * Veráves para definir a origem dos Serviços
	 * 
	 * @author Rafael Corrêa
	 * @date 30/10/2006
	 */
	Integer ORIGEM_SERVICO_SOLICITADO = 1;
	Integer ORIGEM_SERVICO_SELETIVO = 2;
	
	
	//[UC0494] Gerar Numeraïção de RA Manual Autor: Raphael Rossiter
	Integer LIMITE_QUANTIDADE_GERACAO_MANUAL = 90;
	
	//[UC0302] Gerar Débitos a Cobrar de Acéscimos por Impontualidade Autor: Pedro Alexandre
	Short ENCERRAMENTO_ARRECADACAO = new Short("3");
	
//	 Iniciando criação de constantes para [UC00538] Filtrar RAs na Agencia Reguladora  
	// Author: Kassia Albuquerque Data:02/05/2007

	Integer SITUACAO_AGENCIA_TODOS = -1;
	Integer SITUACAO_AGENCIA_PENDENTE = 1;
	Integer SITUACAO_AGENCIA_ENCERRADO = 2;
	Integer SITUACAO_RA_AGENCIA_TODOS = -1;
	Integer SITUACAO_RA_AGENCIA_PENDENTE = 1;
	Integer SITUACAO_RA_AGENCIA_ENCERRADO = 2;
	Integer SITUACAO_RA_AGENCIA_BLOQUEADO = 3;
	
	// Finalizando criaçaõ de constantes para [UC00538] Filtrar RAs na Agencia Reguladora  
	// Author: Kassia Albuquerque Data:02/05/2007
    
    
    /**
     * Permite a inclusão de um novo perfil de parcelamento
     * 
     * [UC0541] Emitir 2a via Conta Emitida
     * 
     * Constante para definir tamanho das tabelas
     * 
     * @author Vivianne Sousa
     * @date 05/10/2007
     */
    Integer NUMERO_MAXIMO_REGISTROS_CONTA = 3;

    
    //Tipos de Débitos
    Integer DEBITO_A_COBRAR = 1;
    Integer CREDITO_A_REALIZAR = 2;
    Integer CONTA = 3;
    Integer ACRESCIMO = 4;
    Integer GUIA_PAGAMENTO = 5;
    Integer PARCELAMENTO_FORA_CONTA = 6;
    
    //Constantes usadas no caso de uso [UC0656] Inserir Negativador
    //Author: Thiago Vieira
    //Date: 20/12/2007
    
    String CODIGO_AGENTE_JA_CADASTRADO = "CӓDIGO DO AGENTE J�� CADASTRADO";
    //fim se constantes para caso de uso [UC0656] Inserir Negativador 
    
//  Constantes usadas no caso de uso [UC0658] Filtrar Negativador
    //Author: Thiago Vieira
    //Date: 22/12/2007
    
    String CODIGO_AGENTE_NAO_CADASTRADO = "CӁDIGO DO AGENTE NÃO CADASTRADO";
    //fim se constantes para caso de uso [UC0656] Inserir Negativador
    
    //Constantes usadas no caso de uso [UC0655] Filtrar Comando de Negativação
    //Author: Thiago Vieira
    //Date: 27/12/2007
    
    String COMANDO_SIMULADO_SIM = "1";
    String COMANDO_SIMULADO_NAO = "2";
    String COMANDO_SIMULADO_TODOS = "3";
    
    //fim de constantes para caso de uso [UC0655] Filtrar Comando de Negativação
    
    //Constantes usadas no caso de uso [UC0662] Inserir motivo de Retorno do Registro do Negativador
    //Author: Thiago Vieira
    //Date: 03/01/2008
    
    Short INDICADOR_REGISTRO_ACEITO = new Short("1");
    Short INDICADOR_REGISTRO_NAO_ACEITO = new Short("2");
    String CODIGO_MOTIVO_JA_CADASTRADO = "CӓDIGO DO MOTIVO J�� CADASTRADO";
    String CODIGO_MOTIVO_INEXISTENTE = "CӁDIGO DO MOTIVO INEXISTENTE";
    //fim de Constantes usadas no caso de uso [UC0662] Inserir motivo de Retorno do Registro do Negativador
    
    // Constantes utilizadas no caso de uso [UC0654] Consultar Comandos de Negativação Por Critério
    // Author: Thiago Vieira
    // Date: 09/01/2007
    Short TIPO_COMANDO_POR_CRITERIO = new Short("1");
    Short TIPO_COMANDO_POR_MATRICULA_IMOVEIS = new Short("2");
    
    Short TIPO_COMANDO_POR_GUIA_PAGAMENTO = new Short("3");
    // Fim de Constantes utilizadas no caso de uso [UC0654] Consultar Comandos de Negativa��o por Crit�rio
    
    // Constantes utilizadas no caso de uso [UC0691] Consultar Comandos de Negativação
    // Author: Thiago Vieira
    // Date: 09/01/2007
    String CODIGO_SETOR_COMERCIAL_INEXISTENTE = "CӓDIGO DO SETOR COMERCIAL INEXISTENTE";
    // Fim de Constantes utilizadas no caso de uso [UC0691] Consultar Comandos de Negativação
    
    
    //Constantes usadas no caso de uso [UC0352] Emitir Contas e Cartas
    //Author: Vivianne Sousa
    //Date: 28/01/2008
    String CARTEIRA_17_FICHA_COMPENSACAO = "17";
    String CARTEIRA_FICHA_COMPENSACAO = "18";
    String CODIGO_BANCO_FICHA_COMPENSACAO = "001";
    String CODIGO_MOEDA_FICHA_COMPENSACAO = "9";
    
    //Fim de Constantes utilizadas no caso de uso [UC0352] Emitir Contas e Cartas
    
    //Constantes usadas no caso de uso [UC0677] Informar Dados para Consulta da Negativação
    //Author: Thiago Vieira
    //Date: 28/01/2008
    String CODIGO_ELO_POLO_INEXISTENTE = "C�DIGO DO ELO PO�LO INEXISTENTE";
    String CODIGO_QUADRA_INEXISTENTE = "CӓDIGO DA QUADRA INEXISTENTE";
    
    //Fim de Constantes utilizadas no caso de uso [UC0352] Emitir Contas e Cartas
    
    /*
     * [UC0216] Calcular Acrescimo por Impontualidade 
	 * 
	 * @Author: Raphael Rossiter
	 * @date 18/02/2008
     */
    Short INDICADOR_ARRECADACAO_ATIVO = new Short("1");
    Short INDICADOR_ARRECADACAO_DESATIVO = new Short("2");

    //Constantes utilizadas no caso de uso [UC0682] Filtrar Movimento de Negativador 
    //Author: Yara Taciane
    //Date: 22/01/2008
    Short COM_RETORNO = new Short("1");
    Short SEM_RETORNO = new Short("2");
    
    //Constantes utilizadas no caso de uso [UC0682] Filtrar Movimento de Negativador 
    
    
    //Constantes utilizadas no caso de uso [UC0675] Excluir Negativação OnLine
    //Author: Yara Taciane
    //Date: 05/03/2008
    Short ACEITO = new Short("1");
    Short NAO_ACEITO = new Short("2");
    
    //Constantes utilizadas no caso de uso [UC0675] Excluir Negativação OnLine 
    
    //Constantes utilizadas no caso de uso [UC0682] Filtrar Movimento de Negativador 
    //Author: Yara Taciane
    //Date: 22/01/2008
    Short CORRIGIDO = new Short("1");
    Short NAO_CORRIGIDO = new Short("2");
    
    //Constantes utilizadas no caso de uso [UC0682] Filtrar Movimento de Negativador 
    
    
    //Constantes utilizadas no caso de uso [UC0726] Gerar Relatóirio das Contas Baixadas Contabilmente
    //Author: Vivianne Sousa
    //Date: 07/04/2008
    Short ANALITICO = new Short("1");
    Short SINTETICO = new Short("2");
    Short MENSAL = new Short("1");
    Short ACUMULADO = new Short("2");
    Integer FAIXA_1 = 1;
    Integer FAIXA_2 = 2;
    Integer FAIXA_3 = 3;
    String MENSAGEM_FAIXA_1 = "Valor Total At� R$5.000,00 ";
    String MENSAGEM_FAIXA_2 = "Valor Total de R$5.000,01 a R$30.000,00 ";
    String MENSAGEM_FAIXA_3 = "Valor Total acima de R$30.000,01 ";
    BigDecimal FAIXA_VALOR_1 = new BigDecimal("5000.00");
    BigDecimal FAIXA_VALOR_1_MAIS_1 = new BigDecimal("5000.01");
    BigDecimal FAIXA_VALOR_2 = new BigDecimal("30000.00");
    BigDecimal FAIXA_VALOR_3 = new BigDecimal("30000.01");
    //Fim Constantes utilizadas no caso de uso [UC0726] Gerar Relatório das Contas Baixadas Contabilmente

	String PARAMETROS_BATCH = "parametros_batch";
    
    //[UC0745] - Gerar Arquivo Texto para Faturamento - Autor: Raphael Rossiter
    Short GERAR_FAIXA_FALSA_ATIVO = new Short("1");
    Short GERAR_FAIXA_FALSA_DESATIVO = new Short("2");
    Short GERAR_FAIXA_FALSA_ROTA = new Short("3");
    
    //[UC0330] - Inserir Mensagem Conta - Autor: Raphael Rossiter
    int TURBIDEZ = 1;
    int CLORO = 2;
    int PH = 3;
    int COR = 4;
    int FLUOR = 5;
    int FERRO = 6;
    int COLIFORMES_TOTAIS = 7;
    int COLIFORMES_FECAIS = 8;
    int NITRATO = 9;
    
    String ORDENAR_POR_DESCRICAO = "1";
    String ORDENAR_POR_CODIGO = "2";
    
    String INDICADOR_RELATIVO_HIDROMETRO ="1";
    
    String SMTP_INVALIDO = "9.9.9.9";
    
    Short DOCUMENTO_ENTREGUE_AUTO_INFRACAO = new Short("2");
    Short DOCUMENTO_ENTREGUE_SOLICITACAO_COMPARECIMENTO = new Short("1");
    
    BigDecimal CEM = new BigDecimal("100.00");
    
    //Indicador de Diferenca de valores movimento e pagamento
    Short SEM_DIFERENCA = new Short("1");
    Short COM_DIFERENCA = new Short("2");
    
    //Idade mínima que um funcionário terá que ter para poder ser cadastrado no GSAN
    Short IDADE_MINIMA_FUNCIONARIO = new Short("14");
    
//  Idade mínima que um usuário terá que ter para poder ser cadastrado no GSAN
    Short IDADE_MINIMA_USUARIO = new Short("15");
    
    //Indicador de Tipo de Pagamento a vista
    Short INDICADOR_PAGAMENTO_A_VISTA = new Short("1");
//  Indicador de Tipo de Pagamento parcelado
    Short INDICADOR_PAGAMENTO_PARCELADO = new Short("2");
    
    String CONTENT_TYPE_JPEG = "image/jpeg";
    String EXTENSAO_JPG = "JPG";
    String CONTENT_TYPE_PDF = "application/pdf";
    String EXTENSAO_PDF = "PDF";
    String CONTENT_TYPE_MSWORD = "application/msword";
    String EXTENSAO_DOC = "DOC";
    String CONTENT_TYPE_GENERICO = "text/plain";
    
    String EXTENSAO_XLS = "XLS";
    String CONTENT_TYPE_XLS = "application/vnd.ms-excel";
    String EXTENSAO_XLSX = "XLSX";
    String CONTENT_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    String EXTENSAO_DOCX = "DOCX";
    String CONTENT_TYPE_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    
    Short NAO_LOCALIDADE = 3;
    
    Short INDICADOR_CALCULAR_TAXA_SALARIO_MINIMO = new Short("1");
    Short INDICADOR_CALCULAR_TAXA_SEGUNDA_VIA = new Short("3");
    
    Short MODALIDADE_CARTAO_CREDITO = new Short("1");
    Short MODALIDADE_CARTAO_DEBITO = new Short("2");
    
    String ERRO_SQL_CONVERSSAO_ALFANUMERICO_PARA_NUMERICO = "22P02";
    String CARACTERES_ALFANUMERICOS="abcdefghijklmnopqrstuvxzwABCDEFGHIJKLMNOPQRSTUVXZW/";
    
    String SITUACAO_PENDENTE = "1";
    
    Short MODULO_VERIFICADOR_10 = new Short("10");
	Short MODULO_VERIFICADOR_11 = new Short("11");
	
	String PESQUISA_PELO_CEP = "CEP";
	String PESQUISA_PELO_BAIRRO = "BAIRRO";
	String PESQUISA_PELO_IMOVEL = "IMOVEL";
	
	Short INDICADOR_SUPERINTENDENCIA = new Short("1");

	Short INDICADOR_LEITURA_1 = new Short("1");
	Short INDICADOR_LEITURA_2 = new Short("2");
    Short INDICADOR_EXECUCAO = new Short("1");
    Short INDICADOR_NAO_EXECUCAO = new Short("2");
    
    Short INDICADOR_OS_NAO_ENCERRADA = new Short("1");

	String ORDEM_SERVICO_INEXISTENTE = new String("Ordem de Servi�o inexistente");
	
	Integer DEBITO_CREDITO_SITUACAO_NORMAL = new Integer("0");
	Integer DEBITO_CREDITO_SITUACAO_RETIFICADA = new Integer("1");
	Integer DEBITO_CREDITO_SITUACAO_INCLUIDA = new Integer("2");
	
	
	Integer PENDENTE = new Integer(1);
	Integer PAGO = new Integer(2);
	Integer PARCELADO = new Integer(3);
	Integer CANCELADO = new Integer(4);
	
	Integer NUMERO_MAXIMO_REGISTROS_EMITIR_OS_COBRANCA_POR_RESULTADO = 6;
	
	String OITO_DIGITOS = "0";
	String NOVE_DIGITOS = "1";
	
	Integer EXTRATO_ENTRADA_PARCELAMENTO = 37;
	
	Integer MOTIVO_REVISAO_ENTRADA_PARCELAMENTO = 101;
	

	
	String CONTRATO_DEMANDA_IMOVEL_ATIVO = "1";
	String CONTRATO_DEMANDA_IMOVEL_SUSPENSO = "2";
	String CONTRATO_DEMANDA_IMOVEL_ENCERRADO = "3"; 
	
	String BRASIL_55 =  "55";
	Integer CODIGO_RETORNO_GERADO = 0;
	Integer CODIGO_RETORNO_ERRO_TRANSMISSAO = 1;
	Integer CODIGO_RETORNO_TRANSMITIDO = 2;
	Integer CODIGO_RETORNO_ERRO_OPERADORA = 3;
	Integer CODIGO_RETORNO_ENVIADO  = 4;
	
	/**
	 * [UC1272] GerarArquivoEFD-PIS-Confins
	 * 
	 * Constantes para deficir os tipos de arquivo que podem ser gerados;
	 * @author Erivan Sousa
	 * @date 30/01/2012
	 */
	
	public static int TIPO_C600 = 1;
	public static int TIPO_C601 = 2;
	public static int TIPO_C605 = 3;
	public static int TIPO_F100 = 4;
	public static int TIPO_F600 = 5;
	public static int TIPO_M230 = 6;
	public static int TIPO_M630 = 7;

	public static int ATENDIMENTO_OPERACIONAL = 1;
	public static int ATENDIMENTO_COMERCIAL = 2;
	public static int ATENDIMENTO_REITERACAO = 3;
	public static int ATENDIMENTO_INFORMACAO = 4;

	public static Short PERIODO_MANHA = 1;
	public static Short PERIODO_TARDE = 2;
	public static Short PERIODO_NOITE = 3;
	
	public static Integer CODIGO_BANCO_BRASIL = 1;
	
	public static int QUEBRA_MOVIMENTO_ARRECADADORES = 200;	
	
	public static int QUANTIDADE_CARACTERE_CODIGO_DE_BARRAS = 57;

	public static Short INDICADOR_LIBERADO = new Short((short) 1);
	public static Short INDICADOR_SUSPENSO = new Short((short) 2);
	
	public static String DIRETORIO_GSANEOS = "gsaneos";
	public static String DIRETORIO_ATUALIZACAO_CADASTRAL_BIN = "atualizacao-cadastral";
	public static String DIRETORIO_MAPAS_KML_TEMPORARIO = DIRETORIO_ATUALIZACAO_CADASTRAL_BIN+"/mapas"; 
	public static String NOME_BANCO_SQLITE_ATUALIZACAO_CADASTRAL = "banco_gsanac";

	public static final BigDecimal PERCENTUAL_PIS = new BigDecimal("0.0165");
	public static final BigDecimal PERCENTUAL_CONFINS = new BigDecimal("0.076");
	public static final String PERCENTUAL_PIS_TEXTO = "1,65";
	public static final String PERCENTUAL_CONFINS_TEXTO = "7,6";
	
	public static String RELATORIO_ANALITICO = "1";
	public static String RELATORIO_SINTETICO = "2";

	public static final String URL_HELP = "http://conhecimento.consensotec.com.br/doku.php";
	
	/*
	 * Constantes referentes
	 * se a foto pertence a uma
	 * anormalidade de leitura ou
	 * anormalidade de consumo
	 */
	public static final Integer FOTO_TIPO_LEITURA_ANORMALIDADE = 1;
	
	public static final Integer FOTO_TIPO_CONSUMO_ANORMALIDADE = 2;
	
	public static final String KEY_WEBSERVICE_CAER = "skQcH2g7fvQ5A3nOo3P2fNPIVFvMm_Qj3VRkMaJzghVEoXS0Dzi6l0C-BuD-JunU";
}
