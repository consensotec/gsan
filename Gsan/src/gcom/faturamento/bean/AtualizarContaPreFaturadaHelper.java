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
package gcom.faturamento.bean;

import gcom.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class AtualizarContaPreFaturadaHelper {
    
    public static final Integer REGISTRO_TIPO_1 = 1;
    public static final Integer REGISTRO_TIPO_2 = 2;
    public static final Integer REGISTRO_TIPO_3 = 3;
    public static final Integer REGISTRO_TIPO_4 = 4;
    public static final Integer REGISTRO_TIPO_5 = 5;
    
    // Constantes de campos comuns a todos os tipos
    private static final int REGISTRO_TIPO = 1;
    private static final int MATRICULA_IMOVEL = 9;
    
    // Constantes do registro tipo 1    
    private static final int REGISTRO_TIPO_1_TIPO_MEDICAO = 1;
    private static final int REGISTRO_TIPO_1_ANO_MES_FATURAMENTO = 6;
    private static final int REGISTRO_TIPO_1_NUMERO_CONTA = 9;
    private static final int REGISTRO_TIPO_1_CODIGO_GRUPO_FATURAMENTO = 3;
    private static final int REGISTRO_TIPO_1_CODIGO_ROTA = 7;
    private static final int REGISTRO_TIPO_1_LEITURA_HIDROMETRO = 7;
    private static final int REGISTRO_TIPO_1_ANORMALIDADE_LEITURA = 2;
    private static final int REGISTRO_TIPO_1_DATA_HORA_LEITURA = 26;
    private static final int REGISTRO_TIPO_1_INDICADOR_CONFIRMACAO_LEITURA = 1;
    private static final int REGISTRO_TIPO_1_LEITURA_FATURAMENTO = 7;
    private static final int REGISTRO_TIPO_1_CONSUMO_MEDIDO = 6;
    private static final int REGISTRO_TIPO_1_CONSUMO_A_SER_COBRADO_MES = 6;
    private static final int REGISTRO_TIPO_1_CONSUMO_RATEIO_AGUA = 6;
    private static final int REGISTRO_TIPO_1_CONSUMO_RATEIO_ESGOTO = 6;
    private static final int REGISTRO_TIPO_1_TIPO_CONSUMO = 2;
    private static final int REGISTRO_TIPO_1_ANORMALIDADE_CONSUMO = 2;
    private static final int REGISTRO_TIPO_1_INDICACAO_EMISSAO_CONTA = 1;
    private static final int REGISTRO_TIPO_1_LOCALIDADE = 3;
    private static final int REGISTRO_TIPO_1_CODIGO_SETOR_COMERCIAL = 3;
    private static final int REGISTRO_TIPO_1_NUMERO_QUADRA = 4;
    private static final int REGISTRO_TIPO_1_NUMERO_LOTE = 4;
    private static final int REGISTRO_TIPO_1_NUMERO_SUB_LOTE = 2;
    private static final int REGISTRO_TIPO_1_INDICADOR_GERACAO_CONTA = 1;
    private static final int REGISTRO_TIPO_1_CONSUMO_IMOVEIS_VINCULADOS = 6;
    private static final int REGISTRO_TIPO_1_ANORMALIDADE_FATURAMENTO = 2;
    private static final int REGISTRO_TIPO_1_COBRANCA_DOCUMENTO = 9;
    private static final int REGISTRO_TIPO_1_LEITURA_HIDROMETRO_ANTERIOR = 7;
    
    // Constantes do registro tipo 2
    private static final int REGISTRO_TIPO_2_CODIGO_CATEGORIA = 1;
    private static final int REGISTRO_TIPO_2_CODIGO_SUBCATEGORIA = 3;
    private static final int REGISTRO_TIPO_2_VALOR_FATURADO_AGUA = 16;
    private static final int REGISTRO_TIPO_2_CONSUMO_FATURADO_AGUA = 6;
    private static final int REGISTRO_TIPO_2_VALOR_TARIFA_MINIMA_AGUA = 16;
    private static final int REGISTRO_TIPO_2_CONSUMO_MINIMO_AGUA = 6;
    private static final int REGISTRO_TIPO_2_VALOR_FATURADO_ESGOTO = 16;
    private static final int REGISTRO_TIPO_2_CONSUMO_FATURADO_ESGOTO = 6;
    private static final int REGISTRO_TIPO_2_VALOR_TARIFA_MINIMA_ESGOTO = 16;
    private static final int REGISTRO_TIPO_2_CONSUMO_MINIMO_ESGOTO = 6;
    
    // Constantes do registro tipo 3
    private static final int REGISTRO_TIPO_3_CODIGO_CATEGORIA = 1;
    private static final int REGISTRO_TIPO_3_CODIGO_SUBCATEGORIA = 3;
    private static final int REGISTRO_TIPO_3_CONSUMO_FATURADO_AGUA_FAIXA = 6;
    private static final int REGISTRO_TIPO_3_VALOR_FATURADO_AGUA_FAIXA = 16;
    private static final int REGISTRO_TIPO_3_LIMITE_INICIAL_CONSUMO_FAIXA = 6;
    private static final int REGISTRO_TIPO_3_LIMITE_FINAL_CONSUMO_FAIXA = 6;
    private static final int REGISTRO_TIPO_3_VALOR_TARIFA_AGUA_FAIXA = 16;
    private static final int REGISTRO_TIPO_3_VALOR_TARIFA_ESGOTO_FAIXA = 16;
    private static final int REGISTRO_TIPO_3_CONSUMO_FATURADO_ESGOTO_FAIXA = 6;
    private static final int REGISTRO_TIPO_3_VALOR_FATURADO_ESGOTO_FAIXA = 16;
    
    // Constantes do registro tipo 4
    private static final int REGISTRO_TIPO_4_TIPO_IMPOSTO = 1;
    private static final int REGISTRO_TIPO_4_DESCRICAO_IMPOSTO = 15;
    private static final int REGISTRO_TIPO_4_PERCENTUAL_ALIQUOTA = 6;
    private static final int REGISTRO_TIPO_4_VALOR_IMPOSTO = 16;
    
    // Constantes do registro tipo 5
    private static final int REGISTRO_TIPO_5_SEQUENCIAL_IMOVEL = 4;
    

    
    // Campos comuns
    private String tipoRegistro;
    private String matriculaImovel;
    
    // Tipo de registro 1
    private String tipoMedicao;
    private String anoMesFaturamento;
    private String numeroConta;
    private String codigoGrupoFaturamento;
    private String codigoRota;
    private String leituraHidrometro;
    private String anormalidadeLeitura;
    private String dataHoraLeituraHidrometro;
    private String indicadorConfirmacaoLeitura;
    private String leituraFaturamento;
    private String consumoMedido;
    private String consumoASerCobradoMes;
    private String consumoRateioAgua;
    private String consumoRateioEsgoto;
    private String tipoConsumo;
    private String anormalidadeConsumo;
    private String indicacaoEmissaoConta;
    private String localidade;
    private String codigoSetorComercial;
    private String numeroQuadra;
    private String numeroLote;
    private String numeroSubLote;
    private String indicadorGeracaoConta;
    private String consumoImoveisVinculados;
    private String anormalidadeFaturamento;
    private String idCobrancaDocumento;
    private String leituraHidrometroAnterior;
    private String qntVezesImpressa;
    private String valorAgua;
    private String valorEsgoto;
    private String valorCreditos;
    private String valorDebitos;
    private String valorImpostos;
    private String numeroCoordenadaX;
    private String numeroCoordenadaY;
    private String numeroMesMotivoRevisao;
    
    // Tipo de registro 2
    private String codigoCategoria;
    private String codigoSubCategoria;
    private String valorFaturadoAgua;
    private String consumoFaturadoAgua;
    private String valorTarifaMinimaAgua;
    private String consumoMinimoAgua;
    private String valorFaturadoEsgoto;
    private String consumoFaturadoEsgoto;
    private String valorTarifaMinimaEsgoto;
    private String consumoMinimoEsgoto;
    
    // Tipo de registro 3
    private String consumoFaturadoAguaFaixa;
    private String valorFaturadoAguaFaixa;
    private String limiteInicialConsumoFaixa;
    private String limiteFinalConsumoFaixa;
    private String valorTarifaAguaFaixa;
    private String valorTarifaEsgotoFaixa;
    private String consumoFaturadoEsgotoFaixa;
    private String valorFaturadoEsgotoFaixa;
    
    // Tipo de registro 4
    private String tipoImposto;
    private String descricaoImposto;
    private String percentualAliquota;
    private String valorImposto;
    
    // Tipo de registro 5
    private String sequencialRotaMarcacao;
    private String anoMesFaturamentoGrupo;
    
    
    /**
     * 
     * [UC0840] - Atualizar Conta Pre-Faturada
     * 
     * Converte o arquivo numa coleção de helpers
     * 
     * @author bruno
     * @date 15/06/2009
     *
     * @param linha
     * @return
     */    
    public Collection<AtualizarContaPreFaturadaHelper> 
        parseHelper( BufferedReader buffer ) throws IOException{
        
        Collection<AtualizarContaPreFaturadaHelper> retorno = new ArrayList(); 
        String linha = "";
        AtualizarContaPreFaturadaHelper helper = null;
        
        do {    
        	
        	helper = null;
            linha = buffer.readLine();
            
            if ( linha != null ){
            	this.tipoRegistro = linha.charAt( 0 )+"";
            } else {
            	this.tipoRegistro = "-1";
            }
        
            if ( Integer.parseInt( this.tipoRegistro ) == REGISTRO_TIPO_1 ){
                helper = this.parserRegistroTipo1( linha );                    
            } else if ( Integer.parseInt( this.tipoRegistro ) == REGISTRO_TIPO_2 ){
                helper = this.parserRegistroTipo2( linha );
            } else if ( Integer.parseInt( this.tipoRegistro ) == REGISTRO_TIPO_3 ){
                helper = this.parserRegistroTipo3( linha );
            } else if ( Integer.parseInt( this.tipoRegistro ) == REGISTRO_TIPO_4 ){
                helper = this.parserRegistroTipo4( linha );
            } else if ( Integer.parseInt( this.tipoRegistro ) == REGISTRO_TIPO_5 ){
                helper = this.parserRegistroTipo5( linha );
            }
            
            if ( helper != null ){
            	retorno.add( helper );
            }                
        } while ( linha != null && linha.length() > 0 );        
        
        return retorno;           
   }
    
    /**
     * [UC0840] - Atualizar Conta Pre-Faturada
     * Arquivo no formato usado no Android (separação com Pipes)
     * Converte o arquivo numa coleção de helpers
     * @author bruno / Amelia Pessoa
     * @date 15/06/2009 / 08.03.2012
     * @param linha
     * @return
     */    
    public Collection<AtualizarContaPreFaturadaHelper> 
        parseHelperPipe( BufferedReader buffer ) throws IOException{
        
        Collection<AtualizarContaPreFaturadaHelper> retorno = new ArrayList(); 
        String linha = "";
        AtualizarContaPreFaturadaHelper helper = null;
        
        do {    
        	
        	helper = null;
            linha = buffer.readLine();
            
            if ( linha != null ){
            	this.tipoRegistro = linha.charAt( 0 )+"";
            } else {
            	this.tipoRegistro = "-1";
            }
        
            if ( Integer.parseInt( this.tipoRegistro ) == REGISTRO_TIPO_1 ){
                helper = this.parserRegistroTipo1Pipe( linha );                    
            } else if ( Integer.parseInt( this.tipoRegistro ) == REGISTRO_TIPO_2 ){
                helper = this.parserRegistroTipo2Pipe( linha );
            } else if ( Integer.parseInt( this.tipoRegistro ) == REGISTRO_TIPO_3 ){
                helper = this.parserRegistroTipo3Pipe( linha );
            } else if ( Integer.parseInt( this.tipoRegistro ) == REGISTRO_TIPO_4 ){
                helper = this.parserRegistroTipo4Pipe( linha );
            } else if ( Integer.parseInt( this.tipoRegistro ) == REGISTRO_TIPO_5 ){
                helper = this.parserRegistroTipo5Pipe( linha );
            }
            
            if ( helper != null ){
            	retorno.add( helper );
            }                
        } while ( linha != null && linha.length() > 0 );        
        
        return retorno;           
   }
    
   /**
    * 
    * [UC0840] - Atualizar Conta Pre-Faturada
    * 
    * Le o registro informado, TIPO 1.
    * 
    * @author bruno
    * @date 15/06/2009
    *
    * @param linha
    * @return
    */
   private AtualizarContaPreFaturadaHelper parserRegistroTipo1( String linha ){
       AtualizarContaPreFaturadaHelper retorno = 
           new AtualizarContaPreFaturadaHelper();
       
       Integer index = 0;
       
       // Tipo de registro
       retorno.tipoRegistro = linha.substring( index, index+REGISTRO_TIPO );
       index+=REGISTRO_TIPO;
       System.out.println( "Tipo de Retorno: " + retorno.tipoRegistro );
       
       // Matricula do imovel
       retorno.matriculaImovel = linha.substring( index, index+MATRICULA_IMOVEL );
       index+=MATRICULA_IMOVEL;
       System.out.println( "Matricula do Imovel: " + retorno.matriculaImovel );
       
       // Tipo de medição
       retorno.tipoMedicao = linha.substring( index, index+REGISTRO_TIPO_1_TIPO_MEDICAO );
       index+=REGISTRO_TIPO_1_TIPO_MEDICAO;
       
       // Ano e mes do faturamento
       retorno.anoMesFaturamento = Util.formatarMesAnoParaAnoMes( linha.substring( index, index+REGISTRO_TIPO_1_ANO_MES_FATURAMENTO ) );
       index+=REGISTRO_TIPO_1_ANO_MES_FATURAMENTO;
       
       // Numero da conta
       retorno.numeroConta = linha.substring( index, index+REGISTRO_TIPO_1_NUMERO_CONTA );
       index+=REGISTRO_TIPO_1_NUMERO_CONTA;      
       
       // Codigo do Grupo de faturamento
       retorno.codigoGrupoFaturamento = linha.substring( index, index+REGISTRO_TIPO_1_CODIGO_GRUPO_FATURAMENTO );
       index+=REGISTRO_TIPO_1_CODIGO_GRUPO_FATURAMENTO;
       
       // Codigo da rota
       retorno.codigoRota = linha.substring( index, index+REGISTRO_TIPO_1_CODIGO_ROTA );
       index+=REGISTRO_TIPO_1_CODIGO_ROTA;
       
       // Codigo da leitura do hidrometro
       retorno.leituraHidrometro = linha.substring( index, index+REGISTRO_TIPO_1_LEITURA_HIDROMETRO );
       index+=REGISTRO_TIPO_1_LEITURA_HIDROMETRO;
  
       // Anormalidade de Leitura
       retorno.anormalidadeLeitura = linha.substring( index, index+REGISTRO_TIPO_1_ANORMALIDADE_LEITURA );
       index+=REGISTRO_TIPO_1_ANORMALIDADE_LEITURA;       
       
       // Data e Hora Leitura
       retorno.dataHoraLeituraHidrometro = linha.substring( index, index+REGISTRO_TIPO_1_DATA_HORA_LEITURA );
       index+=REGISTRO_TIPO_1_DATA_HORA_LEITURA;
       
       // Indicador de Confirmacao
       retorno.indicadorConfirmacaoLeitura= linha.substring( index, index+REGISTRO_TIPO_1_INDICADOR_CONFIRMACAO_LEITURA );
       index+=REGISTRO_TIPO_1_INDICADOR_CONFIRMACAO_LEITURA;      
       
       // Leitura do Faturamento
       retorno.leituraFaturamento = linha.substring( index, index+REGISTRO_TIPO_1_LEITURA_FATURAMENTO );
       index+=REGISTRO_TIPO_1_LEITURA_FATURAMENTO;
       
       // Consumo Medido no mes
       retorno.consumoMedido = linha.substring( index, index+REGISTRO_TIPO_1_CONSUMO_MEDIDO );
       index+=REGISTRO_TIPO_1_CONSUMO_MEDIDO;
       
       // Consumo a ser cobrado
       retorno.consumoASerCobradoMes = linha.substring( index, index+REGISTRO_TIPO_1_CONSUMO_A_SER_COBRADO_MES );
       index+=REGISTRO_TIPO_1_CONSUMO_A_SER_COBRADO_MES;
       
       // Consumo rateio agua
       retorno.consumoRateioAgua = linha.substring( index, index+REGISTRO_TIPO_1_CONSUMO_RATEIO_AGUA );
       index+=REGISTRO_TIPO_1_CONSUMO_RATEIO_AGUA;
       
       // Consumo rateio esgoto
       retorno.consumoRateioEsgoto = linha.substring( index, index+REGISTRO_TIPO_1_CONSUMO_RATEIO_ESGOTO );
       index+=REGISTRO_TIPO_1_CONSUMO_RATEIO_ESGOTO;
       
       // Tipo de consumo
       retorno.tipoConsumo = linha.substring( index, index+REGISTRO_TIPO_1_TIPO_CONSUMO );
       index+=REGISTRO_TIPO_1_TIPO_CONSUMO;
       
       // Anormalidade de consumo
       retorno.anormalidadeConsumo = linha.substring( index, index+REGISTRO_TIPO_1_ANORMALIDADE_CONSUMO );
       index+=REGISTRO_TIPO_1_ANORMALIDADE_CONSUMO;
       
       // Indicador de emissao de conta
       retorno.indicacaoEmissaoConta = linha.substring( index, index+REGISTRO_TIPO_1_INDICACAO_EMISSAO_CONTA );
       index+=REGISTRO_TIPO_1_INDICACAO_EMISSAO_CONTA;
       
       // Localidade
       retorno.localidade = linha.substring( index, index+REGISTRO_TIPO_1_LOCALIDADE );
       index+=REGISTRO_TIPO_1_LOCALIDADE;
       
       // Código do Setor Comercial
       retorno.codigoSetorComercial = linha.substring( index, index+REGISTRO_TIPO_1_CODIGO_SETOR_COMERCIAL );
       index+=REGISTRO_TIPO_1_CODIGO_SETOR_COMERCIAL;
       
       // Numero Quadra
       retorno.numeroQuadra = linha.substring( index, index+REGISTRO_TIPO_1_NUMERO_QUADRA );
       index+=REGISTRO_TIPO_1_NUMERO_QUADRA;      
       
       // Numero Lote
       retorno.numeroLote = linha.substring( index, index+REGISTRO_TIPO_1_NUMERO_LOTE );
       index+=REGISTRO_TIPO_1_NUMERO_LOTE;
       
       // Numero do Sublote
       retorno.numeroSubLote = linha.substring( index, index+REGISTRO_TIPO_1_NUMERO_SUB_LOTE );
       index+=REGISTRO_TIPO_1_NUMERO_SUB_LOTE;       
       
       // Indicador Geração da conta
       retorno.indicadorGeracaoConta = linha.substring( index, index+REGISTRO_TIPO_1_INDICADOR_GERACAO_CONTA );
       index+=REGISTRO_TIPO_1_INDICADOR_GERACAO_CONTA;       
       
       // consumo imóveis vinculados
       retorno.consumoImoveisVinculados = linha.substring( index, index+REGISTRO_TIPO_1_CONSUMO_IMOVEIS_VINCULADOS);
       index+=REGISTRO_TIPO_1_CONSUMO_IMOVEIS_VINCULADOS;       
              
       // anormalidade de faturamento
       retorno.anormalidadeFaturamento = linha.substring( index, index+REGISTRO_TIPO_1_ANORMALIDADE_FATURAMENTO );
       index+=REGISTRO_TIPO_1_ANORMALIDADE_FATURAMENTO;
       
       // Id Cobrança Documento
       retorno.idCobrancaDocumento = linha.substring( index, index+REGISTRO_TIPO_1_COBRANCA_DOCUMENTO );
       index+=REGISTRO_TIPO_1_NUMERO_CONTA;
       
       // Codigo da leitura do hidrometro anterior
       retorno.leituraHidrometroAnterior = linha.substring( index, index+REGISTRO_TIPO_1_LEITURA_HIDROMETRO_ANTERIOR );
       index+=REGISTRO_TIPO_1_LEITURA_HIDROMETRO_ANTERIOR;
       
       return retorno;       
   }
   
   /**
    * 
    * [UC0840] - Atualizar Conta Pre-Faturada
    * Arquivo no formato usado no Android (separação com Pipes)
    * Le o registro informado, TIPO 1.
    * @author bruno / Amelia Pessoa / Carlos Chaves
    * @date 15/06/2009 / 08.3.2012 / 15.08.2012
    *
    * @param linha
    * @return
    */
   private AtualizarContaPreFaturadaHelper parserRegistroTipo1Pipe(String linha){ 
       AtualizarContaPreFaturadaHelper retorno = 
           new AtualizarContaPreFaturadaHelper();

       ArrayList<String> dados = Util.split(linha);
              
       // 1 - Tipo de registro
       retorno.tipoRegistro = dados.get(0);
       System.out.println( "Tipo de Retorno: " + retorno.tipoRegistro );
       
       // 2 - Matricula do imovel
       retorno.matriculaImovel = dados.get(1);
       System.out.println( "Matricula do Imovel: " + retorno.matriculaImovel );
       
       // 3-  Tipo de medição
       retorno.tipoMedicao = dados.get(2);
       
       // 4 - Ano e mes do faturamento
       retorno.anoMesFaturamento = Util.formatarMesAnoParaAnoMes( dados.get(3) );
       
       // 5 - Numero da conta
       retorno.numeroConta = dados.get(4);     
       
       // 6 - Codigo do Grupo de faturamento
       retorno.codigoGrupoFaturamento = dados.get(5);
       
       // 7 - Codigo da rota
       retorno.codigoRota = dados.get(6);
       
       // 8 - Codigo da leitura do hidrometro
       retorno.leituraHidrometro = dados.get(7);
  
       // 9 - Anormalidade de Leitura
       retorno.anormalidadeLeitura = dados.get(8);     
       
       // 10 - Data e Hora Leitura
       retorno.dataHoraLeituraHidrometro = dados.get(9);
       
       // 11 - Indicador de sintuacao da leitura
       retorno.indicadorConfirmacaoLeitura= dados.get(10);      
       
       // 12 -  Leitura do Faturamento
       retorno.leituraFaturamento = dados.get(11);
       
       // 13 - Consumo Medido no mes
       retorno.consumoMedido = dados.get(12);
       
       // 14 - Consumo a ser cobrado
       retorno.consumoASerCobradoMes = dados.get(13);
       
       // 15 - Consumo rateio agua
       retorno.consumoRateioAgua = dados.get(14);
       
       // 16 - Consumo rateio esgoto
       retorno.consumoRateioEsgoto = dados.get(15);
       
       // 17 - Tipo de consumo
       retorno.tipoConsumo = dados.get(16);
       
       // 18 - Anormalidade de consumo
       retorno.anormalidadeConsumo = dados.get(17);
       
       // 19 - Indicador de emissao de conta
       retorno.indicacaoEmissaoConta = dados.get(18);
       
       // 20 - Inscricao do Imovel
       String inscricao = dados.get(19);
       
       // Localidade
       retorno.localidade = inscricao.substring(0,3) ;
       
       // Código do Setor Comercial
       retorno.codigoSetorComercial = inscricao.substring(3,6);
       
       // Numero Quadra
       retorno.numeroQuadra = inscricao.substring(6,10);      
       
       // Numero Lote
       retorno.numeroLote = inscricao.substring(10, 14) ;
       
       // Numero do Sublote
       retorno.numeroSubLote = inscricao.substring(14,16);      
       
       
       // 21 - Indicador Geração da conta
       retorno.indicadorGeracaoConta = dados.get(20);
       
       //22 - Consumo Imoveis Micro Sem Rateio
       retorno.consumoImoveisVinculados = dados.get(21);
       
       // 23 - anormalidade de faturamento
       retorno.anormalidadeFaturamento = dados.get(22);
       
       // 24 - Id Cobrança Documento
       retorno.idCobrancaDocumento = dados.get(23);
       
       // 25 - Codigo da leitura anteriorFaturamento
       retorno.leituraHidrometroAnterior = dados.get(24);
       
       // 26 - Quantidade de vezes que a conta foi impressa em campo
       /* RM 5635 - Registrar a quantidade de vezes que a conta foi impressa através da ISC
		Amelia Pessoa */
       retorno.setQntVezesImpressa(dados.get(25));
       
       // 27 - Valor de Agua
       retorno.valorAgua = dados.get(26);
       
       // 28 - Valor de esgoto
       retorno.valorEsgoto = dados.get(27);
       
       // 29 - Valor de debitos
       retorno.valorDebitos = dados.get(28);
       
       // 30 -  Valor de credtios
       retorno.valorCreditos = dados.get(29);
       
       // 31 -  Valor de impostos
       retorno.valorImpostos = dados.get(30);
       
       // 32 - Numero Coordenada X 
       retorno.numeroCoordenadaX = dados.get(31);
       
       // 33 - Numero Coordenada Y
       retorno.numeroCoordenadaY = dados.get(32);
       
	   // TODO tirar if quando próxima versão evolutiva entrar
       if(dados.size() == 34){
           // 34 - Numero do Mês do Motivo de Revisão
           retorno.numeroMesMotivoRevisao = dados.get(33);  
       }
       return retorno;              
   }
   
   /**
    * 
    * [UC0840] - Atualizar Conta Pre-Faturada
    * Arquivo no formato usado no Android (separação com Pipes)
    * Le o registro informado, TIPO 2.
    * 
    * @author bruno / Amelia Pessoa / Carlos Chaves	
    * @date 15/06/2009 / 08.03.2012 / 15.08.2012
    *
    * @param linha
    * @return
    */
   private AtualizarContaPreFaturadaHelper parserRegistroTipo2Pipe(String linha ){
       AtualizarContaPreFaturadaHelper retorno = new AtualizarContaPreFaturadaHelper();
       
        ArrayList<String> dados = Util.split(linha);
       
       // 1 - Tipo de registro
       retorno.tipoRegistro = dados.get(0);
       System.out.println( "Tipo de Retorno: " + retorno.tipoRegistro );
       
       // 2 - Matricula do imovel
       retorno.matriculaImovel = dados.get(1);
       System.out.println( "Matricula do Imovel: " + retorno.matriculaImovel );
       
       // 3 - Codigo da Categoria
       retorno.codigoCategoria = dados.get(2);
       
       // 4 - Codigo da Subcategoria
       retorno.codigoSubCategoria = dados.get(3);
       
       // 5 - Valor faturado agua 
       retorno.valorFaturadoAgua = dados.get(4);
       
       // 6 - Consumo faturado de agua
       retorno.consumoFaturadoAgua= dados.get(5);
       
       // 7 - Valor tarifa minima de agua
       retorno.valorTarifaMinimaAgua = dados.get(6);
       
       // 8 - Consumo Minimo de Agua
       retorno.consumoMinimoAgua = dados.get(7);
       
       // 9 - Valor faturado esgoto
       retorno.valorFaturadoEsgoto = dados.get(8);
       
       // 10 - Consumo faturado de esgoto
       retorno.consumoFaturadoEsgoto= dados.get(9);
       
       // 11 - Valor tarifa minima de esgoto
       retorno.valorTarifaMinimaEsgoto = dados.get(10);
       
       // 12 Consumo Minimo de esgoto
       retorno.consumoMinimoEsgoto = dados.get(11);
       
       return retorno;       
   }
   
   /**
    * 
    * [UC0840] - Atualizar Conta Pre-Faturada
    * Arquivo no formato usado no Android (separação com Pipes)
    * Le o registro informado, TIPO 3.
    * @author bruno / Amelia Pessoa / Carlos Chaves
    * @date 15/06/2009 / 08.03.2012 / 15.08.2012
    *
    * @param linha
    * @return
    */
   private AtualizarContaPreFaturadaHelper parserRegistroTipo3Pipe( String linha ){
       AtualizarContaPreFaturadaHelper retorno = 
           new AtualizarContaPreFaturadaHelper();
       
       ArrayList<String> dados = Util.split(linha);
       
       // Tipo de registro
       retorno.tipoRegistro = dados.get(0);
       System.out.println( "Tipo de Retorno: " + retorno.tipoRegistro );
       
       // Matricula do imovel
       retorno.matriculaImovel = dados.get(1);
       System.out.println( "Matricula: " + retorno.matriculaImovel );
       
       // Codigo da Categoria
       retorno.codigoCategoria = dados.get(2);
       
       // Codigo da Subcategoria
       retorno.codigoSubCategoria = dados.get(3);

       // Consumo faturado de agua
       retorno.consumoFaturadoAguaFaixa = dados.get(4);
       
       // Valor tarifa minima de agua
       retorno.valorFaturadoAguaFaixa = dados.get(5);
       
       // Limite Inicial do Consumo na Faixa
       retorno.limiteInicialConsumoFaixa = dados.get(6);
       
       // Limite Final do consumo na Faixa
       retorno.limiteFinalConsumoFaixa = dados.get(7);
       
       // Valor da Tarifa Agua na Faixa
       retorno.valorTarifaAguaFaixa = dados.get(8);
       
       // Valor da Tarifa Esgoto na Faixa
       retorno.valorTarifaEsgotoFaixa = dados.get(9);
       
       // Consumo faturado de esgoto
       retorno.consumoFaturadoEsgotoFaixa = dados.get(10);
       
       // Valor tarifa minima de agua
       retorno.valorFaturadoEsgotoFaixa = dados.get(11);
     
       
       return retorno;       
   }   
   
   /**
    * 
    * [UC0840] - Atualizar Conta Pre-Faturada
    * Arquivo no formato usado no Android (separação com Pipes)
    * Le o registro informado, TIPO 3.
    * @author bruno / Amelia Pessoa / Carlos Chaves
    * @date 15/06/2009 / 08.03.2012 / 15.08.2012
    *
    * @param linha
    * @return
    */
   private AtualizarContaPreFaturadaHelper parserRegistroTipo4Pipe( String linha ){
       AtualizarContaPreFaturadaHelper retorno = 
           new AtualizarContaPreFaturadaHelper();
       
       ArrayList<String> dados = Util.split(linha);
       
       // Tipo de registro
       retorno.tipoRegistro = dados.get(0);
       
       // Matricula do imovel
       retorno.matriculaImovel = dados.get(1);
       
       // Tipo do imposto
       retorno.tipoImposto = dados.get(2);
       
       // Descrição do imposto
       retorno.descricaoImposto = dados.get(3);      

       // Percentual da aliquota
       retorno.percentualAliquota = dados.get(4);
       
       // Valor do imposoto
       retorno.valorImposto = dados.get(5);

       return retorno;       
   }    
   
   /**
    * 
    * [UC0840] - Atualizar Conta Pre-Faturada
    * Arquivo no formato usado no Android (separação com Pipes)
    * Le o registro informado, TIPO 5.
    * @author bruno / Amelia Pessoa / Carlos Chaves
    * @date 09/08/2010 / 08.03.2012 / 15.08.2012
    *
    * @param linha
    * @return
    */
   private AtualizarContaPreFaturadaHelper parserRegistroTipo5Pipe( String linha ){
       AtualizarContaPreFaturadaHelper retorno = 
           new AtualizarContaPreFaturadaHelper();
       
       ArrayList<String> dados = Util.split(linha);
       
       // Tipo de registro
       retorno.tipoRegistro = dados.get(0);
       
       // Matricula do imovel
       retorno.matriculaImovel = dados.get(1);
              
       // Valor do imposoto
       retorno.sequencialRotaMarcacao = dados.get(2);
       
       // Ano Mes Referencia
       retorno.anoMesFaturamentoGrupo = dados.get(3);


       return retorno;       
   }   
   
   /**
    * 
    * [UC0840] - Atualizar Conta Pre-Faturada
    * 
    * Le o registro informado, TIPO 2.
    * 
    * @author bruno
    * @date 15/06/2009
    *
    * @param linha
    * @return
    */
   private AtualizarContaPreFaturadaHelper parserRegistroTipo2( String linha ){
       AtualizarContaPreFaturadaHelper retorno = 
           new AtualizarContaPreFaturadaHelper();
       
       Integer index = 0;
       
       // Tipo de registro
       retorno.tipoRegistro = linha.substring( index, index+REGISTRO_TIPO );
       index+=REGISTRO_TIPO;
       System.out.println( "Tipo de Retorno: " + retorno.tipoRegistro );
       
       // Matricula do imovel
       retorno.matriculaImovel = linha.substring( index, index+MATRICULA_IMOVEL );
       index+=MATRICULA_IMOVEL;
       System.out.println( "Matricula do Imovel: " + retorno.matriculaImovel );
       
       // Codigo da Categoria
       retorno.codigoCategoria = linha.substring( index, index+REGISTRO_TIPO_2_CODIGO_CATEGORIA );
       index+=REGISTRO_TIPO_2_CODIGO_CATEGORIA;
       
       // Codigo da Subcategoria
       retorno.codigoSubCategoria = linha.substring( index, index+REGISTRO_TIPO_2_CODIGO_SUBCATEGORIA );
       index+=REGISTRO_TIPO_2_CODIGO_SUBCATEGORIA;
       
       // Valor faturado agua 
       retorno.valorFaturadoAgua = linha.substring( index, index+REGISTRO_TIPO_2_VALOR_FATURADO_AGUA );
       index+=REGISTRO_TIPO_2_VALOR_FATURADO_AGUA;
       
       // Consumo faturado de agua
       retorno.consumoFaturadoAgua= linha.substring( index, index+REGISTRO_TIPO_2_CONSUMO_FATURADO_AGUA );
       index+=REGISTRO_TIPO_2_CONSUMO_FATURADO_AGUA;
       
       // Valor tarifa minima de agua
       retorno.valorTarifaMinimaAgua = linha.substring( index, index+REGISTRO_TIPO_2_VALOR_TARIFA_MINIMA_AGUA );
       index+=REGISTRO_TIPO_2_VALOR_TARIFA_MINIMA_AGUA;
       
       // Consumo Minimo de Agua
       retorno.consumoMinimoAgua = linha.substring( index, index+REGISTRO_TIPO_2_CONSUMO_MINIMO_AGUA );
       index+=REGISTRO_TIPO_2_CONSUMO_MINIMO_AGUA;
       
       // Valor faturado esgoto
       retorno.valorFaturadoEsgoto = linha.substring( index, index+REGISTRO_TIPO_2_VALOR_FATURADO_ESGOTO );
       index+=REGISTRO_TIPO_2_VALOR_FATURADO_ESGOTO;
       
       // Consumo faturado de esgoto
       retorno.consumoFaturadoEsgoto= linha.substring( index, index+REGISTRO_TIPO_2_CONSUMO_FATURADO_ESGOTO );
       index+=REGISTRO_TIPO_2_CONSUMO_FATURADO_ESGOTO;
       
       // Valor tarifa minima de esgoto
       retorno.valorTarifaMinimaEsgoto = linha.substring( index, index+REGISTRO_TIPO_2_VALOR_TARIFA_MINIMA_ESGOTO );
       index+=REGISTRO_TIPO_2_VALOR_TARIFA_MINIMA_ESGOTO;
       
       // Consumo Minimo de esgoto
       retorno.consumoMinimoEsgoto = linha.substring( index, index+REGISTRO_TIPO_2_CONSUMO_MINIMO_ESGOTO );
       index+=REGISTRO_TIPO_2_CONSUMO_MINIMO_ESGOTO;
       
       return retorno;       
   }
   
   /**
    * 
    * [UC0840] - Atualizar Conta Pre-Faturada
    * 
    * Le o registro informado, TIPO 3.
    * 
    * @author bruno
    * @date 15/06/2009
    *
    * @param linha
    * @return
    */
   private AtualizarContaPreFaturadaHelper parserRegistroTipo3( String linha ){
       AtualizarContaPreFaturadaHelper retorno = 
           new AtualizarContaPreFaturadaHelper();
       
       Integer index = 0;
       
       // Tipo de registro
       retorno.tipoRegistro = linha.substring( index, index+REGISTRO_TIPO );
       index+=REGISTRO_TIPO;
       System.out.println( "Tipo de Retorno: " + retorno.tipoRegistro );
       
       // Matricula do imovel
       retorno.matriculaImovel = linha.substring( index, index+MATRICULA_IMOVEL );
       index+=MATRICULA_IMOVEL;
       System.out.println( "Matricula: " + retorno.matriculaImovel );
       
       // Codigo da Categoria
       retorno.codigoCategoria = linha.substring( index, index+REGISTRO_TIPO_3_CODIGO_CATEGORIA );
       index+=REGISTRO_TIPO_3_CODIGO_CATEGORIA;
       
       // Codigo da Subcategoria
       retorno.codigoSubCategoria = linha.substring( index, index+REGISTRO_TIPO_3_CODIGO_SUBCATEGORIA );
       index+=REGISTRO_TIPO_3_CODIGO_SUBCATEGORIA;

       // Consumo faturado de agua
       retorno.consumoFaturadoAguaFaixa = linha.substring( index, index+REGISTRO_TIPO_3_CONSUMO_FATURADO_AGUA_FAIXA );
       index+=REGISTRO_TIPO_3_CONSUMO_FATURADO_AGUA_FAIXA;
       
       // Valor tarifa minima de agua
       retorno.valorFaturadoAguaFaixa = linha.substring( index, index+REGISTRO_TIPO_3_VALOR_FATURADO_AGUA_FAIXA );
       index+=REGISTRO_TIPO_3_VALOR_FATURADO_AGUA_FAIXA;
       
       // Limite Inicial do Consumo na Faixa
       retorno.limiteInicialConsumoFaixa = linha.substring( index, index+REGISTRO_TIPO_3_LIMITE_INICIAL_CONSUMO_FAIXA );
       index+=REGISTRO_TIPO_3_LIMITE_INICIAL_CONSUMO_FAIXA;
       
       // Limite Final do consumo na Faixa
       retorno.limiteFinalConsumoFaixa = linha.substring( index, index+REGISTRO_TIPO_3_LIMITE_FINAL_CONSUMO_FAIXA );
       index+=REGISTRO_TIPO_3_LIMITE_FINAL_CONSUMO_FAIXA;
       
       // Valor da Tarifa Agua na Faixa
       retorno.valorTarifaAguaFaixa = linha.substring( index, index+REGISTRO_TIPO_3_VALOR_TARIFA_AGUA_FAIXA );
       index+=REGISTRO_TIPO_3_VALOR_TARIFA_AGUA_FAIXA;
       
       // Valor da Tarifa Esgoto na Faixa
       retorno.valorTarifaEsgotoFaixa = linha.substring( index, index+REGISTRO_TIPO_3_VALOR_TARIFA_ESGOTO_FAIXA );
       index+=REGISTRO_TIPO_3_VALOR_TARIFA_ESGOTO_FAIXA;
       
       // Consumo faturado de esgoto
       retorno.consumoFaturadoEsgotoFaixa = linha.substring( index, index+REGISTRO_TIPO_3_CONSUMO_FATURADO_ESGOTO_FAIXA );
       index+=REGISTRO_TIPO_3_CONSUMO_FATURADO_ESGOTO_FAIXA;
       
       // Valor tarifa minima de agua
       retorno.valorFaturadoEsgotoFaixa = linha.substring( index, index+REGISTRO_TIPO_3_VALOR_FATURADO_ESGOTO_FAIXA );
       index+=REGISTRO_TIPO_3_VALOR_FATURADO_ESGOTO_FAIXA;
     
       
       return retorno;       
   }   
   
   /**
    * 
    * [UC0840] - Atualizar Conta Pre-Faturada
    * 
    * Le o registro informado, TIPO 3.
    * 
    * @author bruno
    * @date 15/06/2009
    *
    * @param linha
    * @return
    */
   private AtualizarContaPreFaturadaHelper parserRegistroTipo4( String linha ){
       AtualizarContaPreFaturadaHelper retorno = 
           new AtualizarContaPreFaturadaHelper();
       
       Integer index = 0;
       
       // Tipo de registro
       retorno.tipoRegistro = linha.substring( index, index+REGISTRO_TIPO );
       index+=REGISTRO_TIPO;
       
       // Matricula do imovel
       retorno.matriculaImovel = linha.substring( index, index+MATRICULA_IMOVEL );
       index+=MATRICULA_IMOVEL;
       
       // Tipo do imposto
       retorno.tipoImposto = linha.substring( index, index+REGISTRO_TIPO_4_TIPO_IMPOSTO );
       index+=REGISTRO_TIPO_4_TIPO_IMPOSTO;
       
       // Descrição do imposto
       retorno.descricaoImposto = linha.substring( index, index+REGISTRO_TIPO_4_DESCRICAO_IMPOSTO );
       index+=REGISTRO_TIPO_4_DESCRICAO_IMPOSTO;       

       // Percentual da aliquota
       retorno.percentualAliquota = linha.substring( index, index+REGISTRO_TIPO_4_PERCENTUAL_ALIQUOTA );
       index+=REGISTRO_TIPO_4_PERCENTUAL_ALIQUOTA;
       
       // Valor do imposoto
       retorno.valorImposto = linha.substring( index, index+REGISTRO_TIPO_4_VALOR_IMPOSTO );
       index+=REGISTRO_TIPO_4_VALOR_IMPOSTO;

       return retorno;       
   }    
   
   /**
    * 
    * [UC0840] - Atualizar Conta Pre-Faturada
    * 
    * Le o registro informado, TIPO 5.
    * 
    * @author bruno
    * @date 09/08/2010
    *
    * @param linha
    * @return
    */
   private AtualizarContaPreFaturadaHelper parserRegistroTipo5( String linha ){
       AtualizarContaPreFaturadaHelper retorno = 
           new AtualizarContaPreFaturadaHelper();
       
       Integer index = 0;
       
       // Tipo de registro
       retorno.tipoRegistro = linha.substring( index, index+REGISTRO_TIPO );
       index+=REGISTRO_TIPO;
       
       // Matricula do imovel
       retorno.matriculaImovel = linha.substring( index, index+MATRICULA_IMOVEL );
       index+=MATRICULA_IMOVEL;
              
       // Valor do imposoto
       retorno.sequencialRotaMarcacao = linha.substring( index, index+REGISTRO_TIPO_5_SEQUENCIAL_IMOVEL );
       index+=REGISTRO_TIPO_5_SEQUENCIAL_IMOVEL;

       return retorno;       
   }    
   


    public Integer getAnoMesFaturamento() {
    	
        return verificarInteger( anoMesFaturamento );
    }
    
    
    public Integer getAnormalidadeLeitura() {
        return verificarInteger( anormalidadeLeitura );
    }
    
    
    public Integer getCodigoCategoria() {
        return verificarInteger( codigoCategoria );
    }
    
    
    public Integer getCodigoGrupoFaturamento() {
        return verificarInteger( codigoGrupoFaturamento );
    }
    
    
    public Integer getCodigoRota() {
        return verificarInteger( codigoRota );
    }
    
    
    public Integer getCodigoSubCategoria() {
        return verificarInteger( codigoSubCategoria );
    }
    
    
    public Integer getConsumoASerCobradoMes() {
        return verificarInteger( consumoASerCobradoMes );
    }
    
    
    public Integer getConsumoFaturadoAgua() {
        return verificarInteger( consumoFaturadoAgua );
    }
    
    
    public Integer getConsumoFaturadoAguaFaixa() {
        return verificarInteger( consumoFaturadoAguaFaixa );
    }
    
    
    public Integer getConsumoFaturadoEsgoto() {
        return verificarInteger( consumoFaturadoEsgoto );
    }
    
    
    public Integer getConsumoFaturadoEsgotoFaixa() {
        return verificarInteger( consumoFaturadoEsgotoFaixa );
    }
    
    
    public Integer getConsumoMedido() {
        return verificarInteger( consumoMedido );
    }
    
    
    public Integer getConsumoMinimoAgua() {
        return verificarInteger( consumoMinimoAgua );
    }
    
    
    public Integer getConsumoMinimoEsgoto() {
        return verificarInteger( consumoMinimoEsgoto );
    }
    
    
    public Integer getConsumoRateioAgua() {
        return verificarInteger( consumoRateioAgua );
    }
    
    
    public Integer getConsumoRateioEsgoto() {
        return verificarInteger( consumoRateioEsgoto );
    }
    
    
    public Date getDataHoraLeituraHidrometro() {
        return Util.converteStringParaDateHora( dataHoraLeituraHidrometro, "yyyy-MM-dd HH:mm:ss" );
    }
    
    
    public String getDescricaoImposto() {
        return descricaoImposto;
    }
    
    
    public Short getIndicacaoEmissaoConta() {
        return verificarShort( indicacaoEmissaoConta );
    }
    
    
    public Short getIndicadorConfirmacaoLeitura() {
        return verificarShort( indicadorConfirmacaoLeitura );
    }
    
    
    public Integer getLeituraFaturamento() {
        return verificarInteger( leituraFaturamento );
    }
    
    
    public Integer getLeituraHidrometro() {
    	return verificarInteger( leituraHidrometro );
    }
    
    public Integer getLeituraHidrometroAnterior() {
    	return verificarInteger( leituraHidrometroAnterior );
    }
    
    
    public Integer getLimiteFinalConsumoFaixa() {
        return verificarInteger( limiteFinalConsumoFaixa );
    }
    
    
    public Integer getLimiteInicialConsumoFaixa() {
        return verificarInteger( limiteInicialConsumoFaixa );
    }
    
    
    public Integer getMatriculaImovel() {
        return verificarInteger( matriculaImovel );
    }
    
    
    public Integer getNumeroConta() {
        return verificarInteger( numeroConta );
    }
    
    
    public BigDecimal getPercentualAliquota() {
        return verificarBigDecimal(  percentualAliquota );
    }
    
    
    public Integer getTipoConsumo() {
        return verificarInteger( tipoConsumo );
    }
    
    
    public Integer getTipoImposto() {
        return verificarInteger( tipoImposto );
    }
    
    
    public Integer getTipoMedicao() {
        return verificarInteger( tipoMedicao );
    }
    
    
    public Integer getTipoRegistro() {
        return verificarInteger( tipoRegistro );
    }
    
    
    public BigDecimal getValorFaturadoAgua() {
        return verificarBigDecimal(  valorFaturadoAgua );
    }
    
    
    public BigDecimal getValorFaturadoAguaFaixa() {
        return verificarBigDecimal(  valorFaturadoAguaFaixa );
    }
    
    
    public BigDecimal getValorFaturadoEsgotoFaixa() {
        return verificarBigDecimal(  valorFaturadoEsgotoFaixa );
    }
    
    
    public BigDecimal getValorFaturadoEsgoto() {
        return verificarBigDecimal(  valorFaturadoEsgoto );
    }
    
    
    public BigDecimal getValorImposto() {
        return verificarBigDecimal(  valorImposto );
    }
    
    
    public BigDecimal getValorTarifaAguaFaixa() {
        return verificarBigDecimal(  valorTarifaAguaFaixa );
    }
    
    
    public BigDecimal getValorTarifaMinimaAgua() {
        return verificarBigDecimal(  valorTarifaMinimaAgua );
    }
    
    
    public BigDecimal getValorTarifaMinimaEsgoto() {
        return verificarBigDecimal(  valorTarifaMinimaEsgoto );
    }

    
    public Integer getAnormalidadeConsumo() {
        return verificarInteger( anormalidadeConsumo );
    }

    
    public Integer getCodigoSetorComercial() {
        return verificarInteger( codigoSetorComercial );
    }

    
    public Integer getLocalidade() {
        return verificarInteger( localidade );
    }

    
    public Integer getNumeroLote() {
        return verificarInteger( numeroLote );
    }

    
    public Integer getNumeroQuadra() {
        return verificarInteger( numeroQuadra );
    }

    
    public Integer getNumeroSubLote() {
        return verificarInteger( numeroSubLote );
    }

	public BigDecimal getValorTarifaEsgotoFaixa() {
		return verificarBigDecimal(  valorTarifaEsgotoFaixa );
	}
	
	private Integer verificarInteger( String string ){
    	if ( string == null || string.trim().equals( "" ) ){
    		return null;
    	} else {
    		return Integer.parseInt( string.trim() );
    	}
	}
	
	private Short verificarShort( String string ){
    	if ( string == null || string.trim().equals( "" ) ){
    		return null;
    	} else {
    		return Short.parseShort( string.trim() );
    	}
	}
	
	private BigDecimal verificarBigDecimal( String string ){
    	if ( string == null || string.trim().equals( "" ) ){
    		return null;
    	} else {
    		return Util.formatarMoedaRealparaBigDecimal( string.trim() );
    	}
	}

	public Short getIndicadorGeracaoConta() {
		return verificarShort( indicadorGeracaoConta );
	}

	public void setIndicadorGeracaoConta(String indicadorGeracaoConta) {
		this.indicadorGeracaoConta = indicadorGeracaoConta;
	}	
	
    public Integer getConsumoImoveisVinculados() {
        return verificarInteger( consumoImoveisVinculados );
   }
    
    public Integer getAnormalidadeFaturamento() {
        return verificarInteger( anormalidadeFaturamento );
    }

    public String getSequencialRotaMarcacao() {
        return sequencialRotaMarcacao;
    }

    public Integer getIdCobrancaDocumento() {
        return verificarInteger( idCobrancaDocumento );
    }
    
	public String getQntVezesImpressa() {
		return qntVezesImpressa;
	}

	public void setQntVezesImpressa(String qntVezesImpressa) {
		this.qntVezesImpressa = qntVezesImpressa;
	}

	public BigDecimal getValorAgua() {
		return verificarBigDecimal(valorAgua);
	}

	public BigDecimal getValorEsgoto() {
		return verificarBigDecimal(valorEsgoto);
	}

	public BigDecimal getValorCreditos() {
		return  verificarBigDecimal(valorCreditos);
	}

	public BigDecimal getValorDebitos() {
		return  verificarBigDecimal(valorDebitos);
	}

	public BigDecimal getValorImpostos() {
		return verificarBigDecimal(valorImpostos);
	}

	public String getNumeroCoordenadaX() {
		return numeroCoordenadaX;
	}

	public void setNumeroCoordenadaX(String numeroCoordenadaX) {
		this.numeroCoordenadaX = numeroCoordenadaX;
	}

	public String getNumeroCoordenadaY() {
		return numeroCoordenadaY;
	}

	public void setNumeroCoordenadaY(String numeroCoordenadaY) {
		this.numeroCoordenadaY = numeroCoordenadaY;
	}
	
	public String getAnoMesFaturamentoGrupo() {
		return anoMesFaturamentoGrupo;
	}

	public void setAnoMesFaturamentoGrupo(String anoMesFaturamentoGrupo) {
		this.anoMesFaturamentoGrupo = anoMesFaturamentoGrupo;
	}

	public String getNumeroMesMotivoRevisao() {
		return numeroMesMotivoRevisao;
	}

	public void setNumeroMesMotivoRevisao(String numeroMesMotivoRevisao) {
		this.numeroMesMotivoRevisao = numeroMesMotivoRevisao;
	}
    
}
