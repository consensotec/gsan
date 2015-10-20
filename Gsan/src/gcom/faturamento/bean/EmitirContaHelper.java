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
package gcom.faturamento.bean;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * [UC ] 
 * @author Vivianne Sousa
 * @date 28/06/2007
 */
public class EmitirContaHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public final static BigDecimal VALOR_LIMITE_FICHA_COMPENSACAO = new BigDecimal("1000");
	
	private Integer idConta;
	
	private Integer idCliente;
	private String nomeCliente;
	private String cpf;
	private String cnpj;
	private Date dataVencimentoConta;
	private int amReferencia;
	private short digitoVerificadorConta;
	private Integer codigoSetorComercialConta;
	private Integer idQuadraConta;
	private Short loteConta;
	private Short subLoteConta;
	private Integer consumoAgua;
	private Integer consumoEsgoto;
	private BigDecimal valorAgua;
	private BigDecimal valorEsgoto;
	private BigDecimal debitos;
	private BigDecimal valorCreditos;
	private BigDecimal valorImpostos;
	private Date dataValidadeConta;
	private Integer idImovel;
	private Integer idLocalidade;
	private String descricaoLocalidade;
	private Integer idGerenciaRegional;
	private String nomeGerenciaRegional;
	private Integer idLigacaoAguaSituacao;
	private Integer idLigacaoEsgotoSituacao;
	private String descricaoLigacaoAguaSituacao;
	private String descricaoLigacaoEsgotoSituacao;
	private Integer idImovelPerfil;
	private Integer idSetorComercial;
	private Integer idFaturamentoGrupo;
	private Integer idEmpresa;
	private BigDecimal percentualEsgotoConta;
	
	private BigDecimal valorConta;
	
	private String enderecoImovel;
	private String inscricaoImovel;
	private String idClienteResponsavel;
	private String enderecoClienteResponsavel;
	private String dadosConsumoMes1;
	private String dadosConsumoMes2;
	private String dadosConsumoMes3;
	private String dadosConsumoMes4;
	private String dadosConsumoMes5;
	private String dadosConsumoMes6;
	private String consumoFaturamento;
	private String consumoMedioDiario;
	private String leituraAnterior;
	private String leituraAtual;
	private String diasConsumo;
	private String quantidadeEconomiaConta;
	private String consumoEconomia;
	private String codigoAuxiliarString;
	private String mensagemConsumoString;
	private Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper;
	private String valorContaString;
	private String primeiraParte;
	private String segundaParte;
	private String terceiraParte;
	private String mesAnoFormatado;
	private String numeroIndiceTurbidez;
	private String numeroCloroResidual;
	private String numeroNitrato;
	private String representacaoNumericaCodBarraFormatada;
	private String representacaoNumericaCodBarraSemDigito;
	private String dataValidade;
	private String dataLeituraAnterior;
	private String dataLeituraAtual;
	private Short codigoRota;
	private Integer numeroSequencialRota;
	private Integer idImovelContaEnvio;
	private String consumoMedio;
	private String coliformesTermotolerantes;
	private String numeroPh;
	private String indiceDurezaTotal;
	private String indiceNumeroCor;
	private String corPadrao;
	private String turbidezPadrao;
	private String phPadrao;
	private String durezaPadrao;
	private String cloroPadrao;
	private String cloriformePadrao;
	
	private String categoriaImovel;
	private String descricaoAnormalidadeConsumo;
	private String descricaoTipoConsumo;
	private String numeroHidrometro;
	private String nomeImovel;
	private String contaSemCodigoBarras;
	private Integer debitoCreditoSituacaoAtualConta;
	private String contaPaga;
	private Integer idRota;
	private Integer idOrigem;
	private Integer idFuncionario;
	private String nomeFuncionario;
	private Integer contaTipo;
	
	private Integer numeroQuadraEntrega;
	private Integer idRotaEntrega;
	private Integer numeroSequencialRotaEntrega;

	
	//---------------------------------------------------------
	//utilizado no Emitir Segunda Via de Conta Tipo 2 (CAER)
	//Vivianne Sousa
	//20/08/2007
	private String msgConta;
	private String msgLinha1Conta;
	private String msgLinha2Conta;
	private String msgLinha3Conta;
	private String msgLinha4Conta;
	private String msgLinha5Conta;
	private String valorMedioTurbidez;
	private String padraoTurbidez;
	private String valorMedioPh;
	private String valorMedioCor;
	private String valorMedioCloro;
	private String valorMedioFluor;
	private String valorMedioFerro;
	private String valorMedioColiformesTotais;
	private String valorMedioColiformesfecais;
	private String padraoPh;
	private String padraoCor;
	private String padraoCloro;
	private String padraoFluor;
	private String padraoFerro;
	private String padraoColiformesTotais;
	private String padraoColiformesfecais;
	private String enderecoLinha2;
	private String enderecoLinha3; 
	private String datasVencimento;
	//---------------------------------------------------------

	//---------------------------------------------------------
	//utilizado quando for boleto bancario
	//Vivianne Sousa 19/09/2008
	private String nossoNumero;
	//---------------------------------------------------------
	
	private Integer codigoDebitoAutomatico;
	
	//---------------------------------------------------------
	//Utilizado para pesquisar mensagem de quita��o
	//Hugo Amorim 28/06/2010
	private Integer anoMesFaturamentoGrupo;
	//---------------------------------------------------------
	
	private Categoria categoriaPrincipalImovel;
	private Subcategoria subcategoriaPrincipalImovel;
	private ConsumoTarifa consumoTarifa;
	
	// N�mero da carteira
	// Davi Menezes 14/11/2013
	private String numeroCarteira;
	
	private String quantidadeCloroExigidas;
	private String quantidadeCloroAnalisadas;
	private String quantidadeCloroConforme;
	private String quantidadeTurbidezExigidas;
	private String quantidadeTurbidezAnalisadas;
	private String quantidadeTurbidezConforme;
	private String quantidadeCorExigidas;
	private String quantidadeCorAnalisadas;
	private String quantidadeCorConforme;
	private String quantidadeColiformesTotaisExigidas;
	private String quantidadeColiformesTotaisAnalisadas;
	private String quantidadeColiformesTotaisConforme;
	private String quantidadeColiformesTermotolerantesExigidas;
	private String quantidadeColiformesTermotolerantesAnalisadas;
	private String quantidadeColiformesTermotolerantesConforme;
	
	public EmitirContaHelper() {
	}
	
	//utilizado no Emitir Segunda Via de Conta Compesa
	//repositorioFaturamento.pesquisarConta
	//Vivianne Sousa
	public EmitirContaHelper(
			Integer idConta, 
			String nomeCliente,
			Date dataVencimentoConta,
			int amReferencia,
			short digitoVerificadorConta,
			Integer codigoSetorComercialConta,
			Integer idQuadraConta, 
			Short loteConta, 
			Short subLoteConta,
			Integer consumoAgua, 
			Integer consumoEsgoto, 
			BigDecimal valorAgua,
			BigDecimal valorEsgoto, 
			BigDecimal debitos,
			BigDecimal valorCreditos, 
			BigDecimal valorImpostos,
			Date dataValidadeConta, 
			Integer idImovel, 
			Integer idLocalidade,
			Integer idGerenciaRegional, 
			String nomeGerenciaRegional, 
			Integer idLigacaoAguaSituacao, 
			Integer idLigacaoEsgotoSituacao,
			Integer idImovelPerfil, 
			Integer idSetorComercial,
			Integer idFaturamentoGrupo, 
			Integer idEmpresa,
			String descricaoLocalidade, 
			String descricaoLigacaoAguaSituacao,
			String descricaoLigacaoEsgotoSituacao,
			Integer idImovelContaEnvio,
			BigDecimal percentualEsgotoConta,
			String nomeImovel){
		
		this.idConta = idConta;
		this.nomeCliente = nomeCliente;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
		this.idImovelContaEnvio = idImovelContaEnvio;
		this.nomeImovel = nomeImovel;
	}
	
	//utilizado no Emitir Segunda Via de Conta Tipo 2 (CAER e CAERN)
	//repositorioFaturamento.pesquisarContaERota
	//Vivianne Sousa
	public EmitirContaHelper(Integer idConta, 
			String nomeCliente,
			Date dataVencimentoConta,
			int amReferencia,
			short digitoVerificadorConta, 
			Integer codigoSetorComercialConta,
			Integer idQuadraConta, 
			Short loteConta, 
			Short subLoteConta,
			Integer consumoAgua, 
			Integer consumoEsgoto, 
			BigDecimal valorAgua,
			BigDecimal valorEsgoto,
			BigDecimal debitos,
			BigDecimal valorCreditos, 
			BigDecimal valorImpostos,
			Date dataValidadeConta, 
			Integer idImovel,
			Integer idLocalidade,
			Integer idGerenciaRegional,
			String nomeGerenciaRegional,
			Integer idLigacaoAguaSituacao,
			Integer idLigacaoEsgotoSituacao,
			Integer idImovelPerfil, 
			Integer idSetorComercial,
			Integer idFaturamentoGrupo, 
			Integer idEmpresa,
			String descricaoLocalidade,
			String descricaoLigacaoAguaSituacao,
			String descricaoLigacaoEsgotoSituacao,
			BigDecimal percentualEsgotoConta,
			Short codigoRota,
			Integer numeroSequencialRota,
			String numeroHidrometro,
			Integer debitoCreditoSituacaoAtualConta,
			String nomeImovel) {
		this.idConta = idConta;
		this.nomeCliente = nomeCliente;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
		this.codigoRota = codigoRota;
		this.numeroSequencialRota = numeroSequencialRota;
		this.numeroHidrometro = numeroHidrometro;
		this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
		this.nomeImovel = nomeImovel;
	}
	
	//utilizado no Emitir Segunda Via de Conta Tipo 2 (CAER e CAERN)
	//repositorioFaturamento.pesquisarContaERota 
	//foi adcionado cpf,cnpj
	//Arthur
	public EmitirContaHelper(Integer idConta, 
			String nomeCliente,
			String cpf,
			String cnpj,
			Date dataVencimentoConta,
			int amReferencia,
			short digitoVerificadorConta, 
			Integer codigoSetorComercialConta,
			Integer idQuadraConta, 
			Short loteConta, 
			Short subLoteConta,
			Integer consumoAgua, 
			Integer consumoEsgoto, 
			BigDecimal valorAgua,
			BigDecimal valorEsgoto,
			BigDecimal debitos,
			BigDecimal valorCreditos, 
			BigDecimal valorImpostos,
			Date dataValidadeConta, 
			Integer idImovel,
			Integer idLocalidade,
			Integer idGerenciaRegional,
			String nomeGerenciaRegional,
			Integer idLigacaoAguaSituacao,
			Integer idLigacaoEsgotoSituacao,
			Integer idImovelPerfil, 
			Integer idSetorComercial,
			Integer idFaturamentoGrupo, 
			Integer idEmpresa,
			String descricaoLocalidade,
			String descricaoLigacaoAguaSituacao,
			String descricaoLigacaoEsgotoSituacao,
			BigDecimal percentualEsgotoConta,
			Short codigoRota,
			Integer numeroSequencialRota,
			String numeroHidrometro,
			Integer debitoCreditoSituacaoAtualConta,
			String nomeImovel) {
		
		this.idConta = idConta;
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.cnpj= cnpj;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
		this.codigoRota = codigoRota;
		this.numeroSequencialRota = numeroSequencialRota;
		this.numeroHidrometro = numeroHidrometro;
		this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
		this.nomeImovel = nomeImovel;
	}
		
	
	//repositorioFaturamento.pesquisarContasDebitoAutomatico
	//repositorioFaturamento.pesquisarContasNormais
	public EmitirContaHelper(Integer idConta,
			String nomeCliente,
			Date dataVencimentoConta,
			int amReferencia,
			short digitoVerificadorConta,
			Integer codigoSetorComercialConta,
			Integer idQuadraConta, 
			Short loteConta, 
			Short subLoteConta,
			Integer consumoAgua,
			Integer consumoEsgoto,
			BigDecimal valorAgua,
			BigDecimal valorEsgoto, 
			BigDecimal debitos,
			BigDecimal valorCreditos,
			BigDecimal valorImpostos,
			Date dataValidadeConta,
			Integer idImovel, 
			Integer idLocalidade,
			Integer idGerenciaRegional,
			String nomeGerenciaRegional,
			Integer idLigacaoAguaSituacao, 
			Integer idLigacaoEsgotoSituacao,
			Integer idImovelPerfil, 
			Integer idSetorComercial,
			Integer idFaturamentoGrupo,
			Integer idEmpresa,
			String descricaoLocalidade,
			String descricaoLigacaoAguaSituacao,
			String descricaoLigacaoEsgotoSituacao,
			BigDecimal percentualEsgotoConta) {
		this.idConta = idConta;
		this.nomeCliente = nomeCliente;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
	}
	
	//utilizado no Emitir Segunda Via de Conta Compesa
	//repositorioFaturamento.pesquisarConta
	//Arthur Carvalho
	public EmitirContaHelper(
			Integer idConta, 
			String nomeCliente,
			String cpf,
			String cnpj,
			Date dataVencimentoConta,
			int amReferencia,
			short digitoVerificadorConta,
			Integer codigoSetorComercialConta,
			Integer idQuadraConta, 
			Short loteConta, 
			Short subLoteConta,
			Integer consumoAgua, 
			Integer consumoEsgoto, 
			BigDecimal valorAgua,
			BigDecimal valorEsgoto, 
			BigDecimal debitos,
			BigDecimal valorCreditos, 
			BigDecimal valorImpostos,
			Date dataValidadeConta, 
			Integer idImovel, 
			Integer idLocalidade,
			Integer idGerenciaRegional, 
			String nomeGerenciaRegional, 
			Integer idLigacaoAguaSituacao, 
			Integer idLigacaoEsgotoSituacao,
			Integer idImovelPerfil, 
			Integer idSetorComercial,
			Integer idFaturamentoGrupo, 
			Integer idEmpresa,
			String descricaoLocalidade, 
			String descricaoLigacaoAguaSituacao,
			String descricaoLigacaoEsgotoSituacao,
			Integer idImovelContaEnvio,
			BigDecimal percentualEsgotoConta,
			String nomeImovel){
		
		this.idConta = idConta;
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
		this.idImovelContaEnvio = idImovelContaEnvio;
		this.nomeImovel = nomeImovel;
	
	}
	
	//utilizado no Emitir Segunda Via de Conta Compesa
	//repositorioFaturamento.pesquisarConta
	//
	//Autor: Hugo Amorim
	//Informa��o: Inclus�o do numero do debito automatico
	//Data: 15/03/2010
	public EmitirContaHelper(
			Integer idConta, 
			String nomeCliente,
			String cpf,
			String cnpj,
			Date dataVencimentoConta,
			int amReferencia,
			short digitoVerificadorConta,
			Integer codigoSetorComercialConta,
			Integer idQuadraConta, 
			Short loteConta, 
			Short subLoteConta,
			Integer consumoAgua, 
			Integer consumoEsgoto, 
			BigDecimal valorAgua,
			BigDecimal valorEsgoto, 
			BigDecimal debitos,
			BigDecimal valorCreditos, 
			BigDecimal valorImpostos,
			Date dataValidadeConta, 
			Integer idImovel, 
			Integer idLocalidade,
			Integer idGerenciaRegional, 
			String nomeGerenciaRegional, 
			Integer idLigacaoAguaSituacao, 
			Integer idLigacaoEsgotoSituacao,
			Integer idImovelPerfil, 
			Integer idSetorComercial,
			Integer idFaturamentoGrupo, 
			Integer idEmpresa,
			String descricaoLocalidade, 
			String descricaoLigacaoAguaSituacao,
			String descricaoLigacaoEsgotoSituacao,
			Integer idImovelContaEnvio,
			BigDecimal percentualEsgotoConta,
			String nomeImovel,
			Integer codDebitoAutomatico,
			Integer anoMesFaturamentoGrupo){		
		this.anoMesFaturamentoGrupo = anoMesFaturamentoGrupo;
		this.idConta = idConta;
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
		this.idImovelContaEnvio = idImovelContaEnvio;
		this.nomeImovel = nomeImovel;
		this.codigoDebitoAutomatico = codDebitoAutomatico;
	}
	

	/*
	 * Autor: Anderson Italo
	 * Descri��o: Dados da conta historico p emitir a 2� via [UC0482]Emitir 2�
	 * Via de Conta.(utilizado no repositorioFaturamento.pesquisarContaHistorico(Integer idConta)).
	 * Data: 04/05/2010*/
	public EmitirContaHelper(
			Integer idContaHistorico, 
			String nomeCliente,
			Date dataVencimentoConta,
			int amReferencia,
			short digitoVerificadorConta,
			Integer codigoSetorComercialConta,
			Integer idQuadraConta, 
			Short loteConta, 
			Short subLoteConta,
			Integer consumoAgua, 
			Integer consumoEsgoto, 
			BigDecimal valorAgua,
			BigDecimal valorEsgoto, 
			BigDecimal debitos,
			BigDecimal valorCreditos, 
			BigDecimal valorImpostos,
			Date dataValidadeConta, 
			Integer idImovel, 
			Integer idLocalidade,
			Integer idGerenciaRegional, 
			String nomeGerenciaRegional, 
			Integer idLigacaoAguaSituacao, 
			Integer idLigacaoEsgotoSituacao,
			Integer idImovelPerfil, 
			Integer idSetorComercial,
			Integer idFaturamentoGrupo, 
			Integer idEmpresa,
			String descricaoLocalidade, 
			String descricaoLigacaoAguaSituacao,
			String descricaoLigacaoEsgotoSituacao,
			Integer idImovelContaEnvio,
			BigDecimal percentualEsgotoConta,
			String nomeImovel,
			Integer codDebitoAutomatico,
			Integer anoMesFaturamentoGrupo){
		this.anoMesFaturamentoGrupo = anoMesFaturamentoGrupo;
		this.idConta = idContaHistorico;
		this.nomeCliente = nomeCliente;
		this.dataVencimentoConta = dataVencimentoConta;
		this.amReferencia = amReferencia;
		this.digitoVerificadorConta = digitoVerificadorConta;
		this.codigoSetorComercialConta = codigoSetorComercialConta;
		this.idQuadraConta = idQuadraConta;
		this.loteConta = loteConta;
		this.subLoteConta = subLoteConta;
		this.consumoAgua = consumoAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.valorImpostos = valorImpostos;
		this.dataValidadeConta = dataValidadeConta;
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idSetorComercial = idSetorComercial;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		this.idEmpresa = idEmpresa;
		this.descricaoLocalidade = descricaoLocalidade;
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
		this.percentualEsgotoConta = percentualEsgotoConta;
		this.idImovelContaEnvio = idImovelContaEnvio;
		this.nomeImovel = nomeImovel;
		this.codigoDebitoAutomatico = codDebitoAutomatico;
	}
	
	public Integer getIdOrigem() {
		return idOrigem;
	}

	public void setIdOrigem(Integer idOrigem) {
		this.idOrigem = idOrigem;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	public int getAmReferencia() {
		return amReferencia;
	}

	public void setAmReferencia(int amReferencia) {
		this.amReferencia = amReferencia;
	}

	public Integer getCodigoSetorComercialConta() {
		return codigoSetorComercialConta;
	}

	public void setCodigoSetorComercialConta(Integer codigoSetorComercialConta) {
		this.codigoSetorComercialConta = codigoSetorComercialConta;
	}

	public Integer getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public Integer getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(Integer consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	public Date getDataValidadeConta() {
		return dataValidadeConta;
	}

	public void setDataValidadeConta(Date dataValidadeConta) {
		this.dataValidadeConta = dataValidadeConta;
	}

	public Date getDataVencimentoConta() {
		return dataVencimentoConta;
	}

	public void setDataVencimentoConta(Date dataVencimentoConta) {
		this.dataVencimentoConta = dataVencimentoConta;
	}

	public BigDecimal getDebitos() {
		return debitos;
	}

	public void setDebitos(BigDecimal debitos) {
		this.debitos = debitos;
	}

	public short getDigitoVerificadorConta() {
		return digitoVerificadorConta;
	}

	public void setDigitoVerificadorConta(short digitoVerificadorConta) {
		this.digitoVerificadorConta = digitoVerificadorConta;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}

	public void setIdFaturamentoGrupo(Integer idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdImovelPerfil() {
		return idImovelPerfil;
	}

	public void setIdImovelPerfil(Integer idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}

	public Integer getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}

	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

	public Integer getIdLigacaoEsgotoSituacao() {
		return idLigacaoEsgotoSituacao;
	}

	public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdQuadraConta() {
		return idQuadraConta;
	}

	public void setIdQuadraConta(Integer idQuadraConta) {
		this.idQuadraConta = idQuadraConta;
	}

	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public Short getLoteConta() {
		return loteConta;
	}

	public void setLoteConta(Short loteConta) {
		this.loteConta = loteConta;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public Short getSubLoteConta() {
		return subLoteConta;
	}

	public void setSubLoteConta(Short subLoteConta) {
		this.subLoteConta = subLoteConta;
	}

	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	public BigDecimal getValorCreditos() {
		return valorCreditos;
	}

	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public BigDecimal getValorImpostos() {
		return valorImpostos;
	}

	public void setValorImpostos(BigDecimal valorImpostos) {
		this.valorImpostos = valorImpostos;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoLigacaoAguaSituacao() {
		return descricaoLigacaoAguaSituacao;
	}

	public void setDescricaoLigacaoAguaSituacao(
			String descricaoLigacaoAguaSituacao) {
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
	}

	public String getDescricaoLigacaoEsgotoSituacao() {
		return descricaoLigacaoEsgotoSituacao;
	}

	public void setDescricaoLigacaoEsgotoSituacao(
			String descricaoLigacaoEsgotoSituacao) {
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
	}

	public BigDecimal getPercentualEsgotoConta() {
		return percentualEsgotoConta;
	}

	public void setPercentualEsgotoConta(BigDecimal percentualEsgotoConta) {
		this.percentualEsgotoConta = percentualEsgotoConta;
	}

	
	public String getMatriculaImovelFormatada(){
		
		String matriculaImovelFormatada = Util.adicionarZerosEsquedaNumero(9, 
				"" + getIdImovel());
		matriculaImovelFormatada = matriculaImovelFormatada.substring(0, 8)
				+ "." + matriculaImovelFormatada.substring(8, 9);
		return matriculaImovelFormatada;
	}

	
	public String getFatura(){
		//M�s/Ano refer�ncia da conta digito verificador
		String mesAnoReferencia = Util
				.formatarAnoMesParaMesAno(getAmReferencia());
		//D�gito verificador da conta
		String digitoVerificador = "" + getDigitoVerificadorConta();
		String fatura = mesAnoReferencia + "-"+ digitoVerificador;
		return fatura;
	}
	
	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getEnderecoClienteResponsavel() {
		return enderecoClienteResponsavel;
	}

	public void setEnderecoClienteResponsavel(String enderecoClienteResponsavel) {
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
	}

	public String getIdClienteResponsavel() {
		return idClienteResponsavel;
	}

	public void setIdClienteResponsavel(String idClienteResponsavel) {
		this.idClienteResponsavel = idClienteResponsavel;
	}

	public String getDadosConsumoMes1() {
		return dadosConsumoMes1;
	}

	public void setDadosConsumoMes1(String dadosConsumoMes1) {
		this.dadosConsumoMes1 = dadosConsumoMes1;
	}

	public String getDadosConsumoMes2() {
		return dadosConsumoMes2;
	}

	public void setDadosConsumoMes2(String dadosConsumoMes2) {
		this.dadosConsumoMes2 = dadosConsumoMes2;
	}

	public String getDadosConsumoMes3() {
		return dadosConsumoMes3;
	}

	public void setDadosConsumoMes3(String dadosConsumoMes3) {
		this.dadosConsumoMes3 = dadosConsumoMes3;
	}

	public String getDadosConsumoMes4() {
		return dadosConsumoMes4;
	}

	public void setDadosConsumoMes4(String dadosConsumoMes4) {
		this.dadosConsumoMes4 = dadosConsumoMes4;
	}

	public String getDadosConsumoMes5() {
		return dadosConsumoMes5;
	}

	public void setDadosConsumoMes5(String dadosConsumoMes5) {
		this.dadosConsumoMes5 = dadosConsumoMes5;
	}

	public String getDadosConsumoMes6() {
		return dadosConsumoMes6;
	}

	public void setDadosConsumoMes6(String dadosConsumoMes6) {
		this.dadosConsumoMes6 = dadosConsumoMes6;
	}

	public String getConsumoFaturamento() {
		return consumoFaturamento;
	}

	public void setConsumoFaturamento(String consumoFaturamento) {
		this.consumoFaturamento = consumoFaturamento;
	}

	public String getConsumoMedioDiario() {
		return consumoMedioDiario;
	}

	public void setConsumoMedioDiario(String consumoMedioDiario) {
		this.consumoMedioDiario = consumoMedioDiario;
	}

	public String getDiasConsumo() {
		return diasConsumo;
	}

	public void setDiasConsumo(String diasConsumo) {
		this.diasConsumo = diasConsumo;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public String getDescricaoAnormalidadeConsumo() {
		return descricaoAnormalidadeConsumo;
	}

	public void setDescricaoAnormalidadeConsumo(String descricaoAnormalidadeConsumo) {
		this.descricaoAnormalidadeConsumo = descricaoAnormalidadeConsumo;
	}

	public String getDescricaoTipoConsumo() {
		return descricaoTipoConsumo;
	}

	public void setDescricaoTipoConsumo(String descricaoTipoConsumo) {
		this.descricaoTipoConsumo = descricaoTipoConsumo;
	}

	public String getCodigoAuxiliarString() {
		return codigoAuxiliarString;
	}

	public void setCodigoAuxiliarString(String codigoAuxiliarString) {
		this.codigoAuxiliarString = codigoAuxiliarString;
	}

	public String getConsumoEconomia() {
		return consumoEconomia;
	}

	public void setConsumoEconomia(String consumoEconomia) {
		this.consumoEconomia = consumoEconomia;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Collection getColecaoContaLinhasDescricaoServicosTarifasTotalHelper() {
		return colecaoContaLinhasDescricaoServicosTarifasTotalHelper;
	}

	public void setColecaoContaLinhasDescricaoServicosTarifasTotalHelper(
			Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper) {
		this.colecaoContaLinhasDescricaoServicosTarifasTotalHelper = colecaoContaLinhasDescricaoServicosTarifasTotalHelper;
	}

	public String getMensagemConsumoString() {
		return mensagemConsumoString;
	}

	public void setMensagemConsumoString(String mensagemConsumoString) {
		this.mensagemConsumoString = mensagemConsumoString;
	}

	public String getMesAnoFormatado() {
		return mesAnoFormatado;
	}

	public void setMesAnoFormatado(String mesAnoFormatado) {
		this.mesAnoFormatado = mesAnoFormatado;
	}

	public String getNumeroCloroResidual() {
		return numeroCloroResidual;
	}

	public void setNumeroCloroResidual(String numeroCloroResidual) {
		this.numeroCloroResidual = numeroCloroResidual;
	}

	public String getNumeroIndiceTurbidez() {
		return numeroIndiceTurbidez;
	}

	public void setNumeroIndiceTurbidez(String numeroIndiceTurbidez) {
		this.numeroIndiceTurbidez = numeroIndiceTurbidez;
	}

	public String getPrimeiraParte() {
		return primeiraParte;
	}

	public void setPrimeiraParte(String primeiraParte) {
		this.primeiraParte = primeiraParte;
	}

	public String getQuantidadeEconomiaConta() {
		return quantidadeEconomiaConta;
	}

	public void setQuantidadeEconomiaConta(String quantidadeEconomiaConta) {
		this.quantidadeEconomiaConta = quantidadeEconomiaConta;
	}

	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}

	public void setRepresentacaoNumericaCodBarraFormatada(
			String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito(
			String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	public String getSegundaParte() {
		return segundaParte;
	}

	public void setSegundaParte(String segundaParte) {
		this.segundaParte = segundaParte;
	}

	public String getTerceiraParte() {
		return terceiraParte;
	}

	public void setTerceiraParte(String terceiraParte) {
		this.terceiraParte = terceiraParte;
	}

	public String getValorContaString() {
		return valorContaString;
	}

	public void setValorContaString(String valorContaString) {
		this.valorContaString = valorContaString;
	}

	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getNumeroSequencialRota() {
		return numeroSequencialRota;
	}

	public void setNumeroSequencialRota(Integer numeroSequencialRota) {
		this.numeroSequencialRota = numeroSequencialRota;
	}
	
	
	public String getRotaEntrega() {
		String rotaEntrega = "";
		
		if (getCodigoRota()!= null){
			rotaEntrega = Util.adicionarZerosEsquedaNumero(2,getCodigoRota().toString());
		}
			
		if (getNumeroSequencialRota()!= null){
			rotaEntrega = rotaEntrega + "." + Util.adicionarZerosEsquedaNumero(4,getNumeroSequencialRota().toString());
		}
		
		return rotaEntrega;
	}

	public Integer getIdImovelContaEnvio() {
		return idImovelContaEnvio;
	}

	public void setIdImovelContaEnvio(Integer idImovelContaEnvio) {
		this.idImovelContaEnvio = idImovelContaEnvio;
	}
	public String getNomeImovel() {
		return nomeImovel;
	}
	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}
	
	//utilizado no tipo de conta 2
//	public String getDatasVencimento(){
//		String datasVencimento = "";
//		
//		Integer mesAnoReferencia = getAmReferencia();
//		mesAnoReferencia = Util.somaUmMesAnoMesReferencia(mesAnoReferencia);
//		
//		String mesAnoFormatado = Util.formatarAnoMesParaMesAno(mesAnoReferencia);
//		
//		datasVencimento = "04/" + mesAnoFormatado;
//		datasVencimento = datasVencimento + " - " + "05/" + mesAnoFormatado;
//		datasVencimento = datasVencimento + " - " + "08/" + mesAnoFormatado;
//		datasVencimento = datasVencimento + " - " + "10/" + mesAnoFormatado;
//		datasVencimento = datasVencimento + " - " + "12/" + mesAnoFormatado;
//		datasVencimento = datasVencimento + " -" + "15/" + mesAnoFormatado;
//		
//		return datasVencimento;
//	}
	public String getConsumoMedio() {
		return consumoMedio;
	}
	public void setConsumoMedio(String consumoMedio) {
		this.consumoMedio = consumoMedio;
	}
	
	public String getColiformesTermotolerantes() {
		return coliformesTermotolerantes;
	}

	public void setColiformesTermotolerantes(String coliformesTermotolerantes) {
		this.coliformesTermotolerantes = coliformesTermotolerantes;
	}

	public String getNumeroPh() {
		return numeroPh;
	}

	public void setNumeroPh(String numeroPh) {
		this.numeroPh = numeroPh;
	}

	public String getIndiceDurezaTotal() {
		return indiceDurezaTotal;
	}

	public void setIndiceDurezaTotal(String indiceDurezaTotal) {
		this.indiceDurezaTotal = indiceDurezaTotal;
	}

	public String getIndiceNumeroCor() {
		return indiceNumeroCor;
	}

	public void setIndiceNumeroCor(String indiceNumeroCor) {
		this.indiceNumeroCor = indiceNumeroCor;
	}

	public String get1DataVencimento(){
		String dataVencimento = "";
		Integer mesAnoReferencia = getAmReferencia();
		mesAnoReferencia = Util.somaUmMesAnoMesReferencia(mesAnoReferencia);
		
		String mesAnoFormatado = Util.formatarAnoMesParaMesAno(mesAnoReferencia);
		
		dataVencimento = "04/" + mesAnoFormatado;
		
		return dataVencimento;
	}
	
	
	public String getNumeroHidrometro() {
		
		if(numeroHidrometro != null){
			return numeroHidrometro;
		}else{
			return "";
		}
		
	}
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	public String getCategoriaImovel() {
		return categoriaImovel;
	}
	public void setCategoriaImovel(String categoriaImovel) {
		this.categoriaImovel = categoriaImovel;
	}

	public String getContaSemCodigoBarras() {
		return contaSemCodigoBarras;
	}

	public void setContaSemCodigoBarras(String contaSemCodigoBarras) {
		this.contaSemCodigoBarras = contaSemCodigoBarras;
	}

	public Integer getDebitoCreditoSituacaoAtualConta() {
		return debitoCreditoSituacaoAtualConta;
	}

	public void setDebitoCreditoSituacaoAtualConta(
			Integer debitoCreditoSituacaoAtualConta) {
		this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
	}

	public String getContaPaga() {
		return contaPaga;
	}

	public void setContaPaga(String contaPaga) {
		this.contaPaga = contaPaga;
	}

	public String getMsgConta() {
		return msgConta;
	}

	public void setMsgConta(String msgConta) {
		this.msgConta = msgConta;
	}

	public String getMsgLinha1Conta() {
		return msgLinha1Conta;
	}

	public void setMsgLinha1Conta(String msgLinha1Conta) {
		this.msgLinha1Conta = msgLinha1Conta;
	}

	public String getMsgLinha2Conta() {
		return msgLinha2Conta;
	}

	public void setMsgLinha2Conta(String msgLinha2Conta) {
		this.msgLinha2Conta = msgLinha2Conta;
	}

	public String getMsgLinha3Conta() {
		return msgLinha3Conta;
	}

	public void setMsgLinha3Conta(String msgLinha3Conta) {
		this.msgLinha3Conta = msgLinha3Conta;
	}

	public String getMsgLinha4Conta() {
		return msgLinha4Conta;
	}

	public void setMsgLinha4Conta(String msgLinha4Conta) {
		this.msgLinha4Conta = msgLinha4Conta;
	}

	public String getMsgLinha5Conta() {
		return msgLinha5Conta;
	}

	public void setMsgLinha5Conta(String msgLinha5Conta) {
		this.msgLinha5Conta = msgLinha5Conta;
	}

	public String getPadraoCloro() {
		return padraoCloro;
	}

	public void setPadraoCloro(String padraoCloro) {
		this.padraoCloro = padraoCloro;
	}

	public String getPadraoColiformesfecais() {
		return padraoColiformesfecais;
	}

	public void setPadraoColiformesfecais(String padraoColiformesfecais) {
		this.padraoColiformesfecais = padraoColiformesfecais;
	}

	public String getPadraoColiformesTotais() {
		return padraoColiformesTotais;
	}

	public void setPadraoColiformesTotais(String padraoColiformesTotais) {
		this.padraoColiformesTotais = padraoColiformesTotais;
	}

	public String getPadraoCor() {
		return padraoCor;
	}

	public void setPadraoCor(String padraoCor) {
		this.padraoCor = padraoCor;
	}

	public String getPadraoFerro() {
		return padraoFerro;
	}

	public void setPadraoFerro(String padraoFerro) {
		this.padraoFerro = padraoFerro;
	}

	public String getPadraoFluor() {
		return padraoFluor;
	}

	public void setPadraoFluor(String padraoFluor) {
		this.padraoFluor = padraoFluor;
	}

	public String getPadraoPh() {
		return padraoPh;
	}

	public void setPadraoPh(String padraoPh) {
		this.padraoPh = padraoPh;
	}

	public String getPadraoTurbidez() {
		return padraoTurbidez;
	}

	public void setPadraoTurbidez(String padraoTurbidez) {
		this.padraoTurbidez = padraoTurbidez;
	}

	public String getValorMedioCloro() {
		return valorMedioCloro;
	}

	public void setValorMedioCloro(String valorMedioCloro) {
		this.valorMedioCloro = valorMedioCloro;
	}

	public String getValorMedioColiformesfecais() {
		return valorMedioColiformesfecais;
	}

	public void setValorMedioColiformesfecais(String valorMedioColiformesfecais) {
		this.valorMedioColiformesfecais = valorMedioColiformesfecais;
	}

	public String getValorMedioColiformesTotais() {
		return valorMedioColiformesTotais;
	}

	public void setValorMedioColiformesTotais(String valorMedioColiformesTotais) {
		this.valorMedioColiformesTotais = valorMedioColiformesTotais;
	}

	public String getValorMedioCor() {
		return valorMedioCor;
	}

	public void setValorMedioCor(String valorMedioCor) {
		this.valorMedioCor = valorMedioCor;
	}

	public String getValorMedioFerro() {
		return valorMedioFerro;
	}

	public void setValorMedioFerro(String valorMedioFerro) {
		this.valorMedioFerro = valorMedioFerro;
	}

	public String getValorMedioFluor() {
		return valorMedioFluor;
	}

	public void setValorMedioFluor(String valorMedioFluor) {
		this.valorMedioFluor = valorMedioFluor;
	}

	public String getValorMedioPh() {
		return valorMedioPh;
	}

	public void setValorMedioPh(String valorMedioPh) {
		this.valorMedioPh = valorMedioPh;
	}

	public String getValorMedioTurbidez() {
		return valorMedioTurbidez;
	}

	public void setValorMedioTurbidez(String valorMedioTurbidez) {
		this.valorMedioTurbidez = valorMedioTurbidez;
	}

	public String getEnderecoLinha2() {
		return enderecoLinha2;
	}

	public void setEnderecoLinha2(String enderecoLinha2) {
		this.enderecoLinha2 = enderecoLinha2;
	}

	public String getEnderecoLinha3() {
		return enderecoLinha3;
	}

	public void setEnderecoLinha3(String enderecoLinha3) {
		this.enderecoLinha3 = enderecoLinha3;
	}

	public String getDatasVencimento() {
		return datasVencimento;
	}

	public void setDatasVencimento(String datasVencimento) {
		this.datasVencimento = datasVencimento;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public Integer getContaTipo() {
		return contaTipo;
	}

	public void setContaTipo(Integer contaTipo) {
		this.contaTipo = contaTipo;
	}

	public Integer getIdRotaEntrega() {
		return idRotaEntrega;
	}

	public void setIdRotaEntrega(Integer idRotaEntrega) {
		this.idRotaEntrega = idRotaEntrega;
	}

	public Integer getNumeroSequencialRotaEntrega() {
		return numeroSequencialRotaEntrega;
	}

	public void setNumeroSequencialRotaEntrega(Integer numeroSequencialRotaEntrega) {
		this.numeroSequencialRotaEntrega = numeroSequencialRotaEntrega;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getCodigoDebitoAutomatico() {
		return codigoDebitoAutomatico;
	}

	public void setCodigoDebitoAutomatico(Integer codigoDebitoAutomatico) {
		this.codigoDebitoAutomatico = codigoDebitoAutomatico;
	}
	
	public String getCodigoDebitoAutomaticoFormatado() {
		String codigoDebitoAutomaticoFormatado = Util.adicionarZerosEsquedaNumero(9, 
				"" + getCodigoDebitoAutomatico());
		codigoDebitoAutomaticoFormatado = codigoDebitoAutomaticoFormatado.substring(0, 8)
				+ "." + codigoDebitoAutomaticoFormatado.substring(8, 9);
		return codigoDebitoAutomaticoFormatado;
	}

	public Integer getAnoMesFaturamentoGrupo() {
		return anoMesFaturamentoGrupo;
	}

	public void setAnoMesFaturamentoGrupo(Integer anoMesFaturamentoGrupo) {
		this.anoMesFaturamentoGrupo = anoMesFaturamentoGrupo;
	}

	public String getNumeroNitrato() {
		return numeroNitrato;
	}

	public void setNumeroNitrato(String numeroNitrato) {
		this.numeroNitrato = numeroNitrato;
	}

	public Integer getNumeroQuadraEntrega() {
		return numeroQuadraEntrega;
	}

	public void setNumeroQuadraEntrega(Integer numeroQuadraEntrega) {
		this.numeroQuadraEntrega = numeroQuadraEntrega;
	}

	public Categoria getCategoriaPrincipalImovel() {
		return categoriaPrincipalImovel;
	}

	public void setCategoriaPrincipalImovel(Categoria categoriaPrincipalImovel) {
		this.categoriaPrincipalImovel = categoriaPrincipalImovel;
	}

	public Subcategoria getSubcategoriaPrincipalImovel() {
		return subcategoriaPrincipalImovel;
	}

	public void setSubcategoriaPrincipalImovel(
			Subcategoria subcategoriaPrincipalImovel) {
		this.subcategoriaPrincipalImovel = subcategoriaPrincipalImovel;
	}

	public ConsumoTarifa getConsumoTarifa() {
		return consumoTarifa;
	}

	public void setConsumoTarifa(ConsumoTarifa consumoTarifa) {
		this.consumoTarifa = consumoTarifa;
	}

	public String getNumeroCarteira() {
		return numeroCarteira;
	}

	public void setNumeroCarteira(String numeroCarteira) {
		this.numeroCarteira = numeroCarteira;
	}

	public String getQuantidadeCloroExigidas() {
		return quantidadeCloroExigidas;
	}

	public void setQuantidadeCloroExigidas(String quantidadeCloroExigidas) {
		this.quantidadeCloroExigidas = quantidadeCloroExigidas;
	}

	public String getQuantidadeCloroAnalisadas() {
		return quantidadeCloroAnalisadas;
	}

	public void setQuantidadeCloroAnalisadas(String quantidadeCloroAnalisadas) {
		this.quantidadeCloroAnalisadas = quantidadeCloroAnalisadas;
	}

	public String getQuantidadeCloroConforme() {
		return quantidadeCloroConforme;
	}

	public void setQuantidadeCloroConforme(String quantidadeCloroConforme) {
		this.quantidadeCloroConforme = quantidadeCloroConforme;
	}

	public String getQuantidadeTurbidezExigidas() {
		return quantidadeTurbidezExigidas;
	}

	public void setQuantidadeTurbidezExigidas(String quantidadeTurbidezExigidas) {
		this.quantidadeTurbidezExigidas = quantidadeTurbidezExigidas;
	}

	public String getQuantidadeTurbidezAnalisadas() {
		return quantidadeTurbidezAnalisadas;
	}

	public void setQuantidadeTurbidezAnalisadas(String quantidadeTurbidezAnalisadas) {
		this.quantidadeTurbidezAnalisadas = quantidadeTurbidezAnalisadas;
	}

	public String getQuantidadeTurbidezConforme() {
		return quantidadeTurbidezConforme;
	}

	public void setQuantidadeTurbidezConforme(String quantidadeTurbidezConforme) {
		this.quantidadeTurbidezConforme = quantidadeTurbidezConforme;
	}

	public String getQuantidadeCorExigidas() {
		return quantidadeCorExigidas;
	}

	public void setQuantidadeCorExigidas(String quantidadeCorExigidas) {
		this.quantidadeCorExigidas = quantidadeCorExigidas;
	}

	public String getQuantidadeCorAnalisadas() {
		return quantidadeCorAnalisadas;
	}

	public void setQuantidadeCorAnalisadas(String quantidadeCorAnalisadas) {
		this.quantidadeCorAnalisadas = quantidadeCorAnalisadas;
	}

	public String getQuantidadeCorConforme() {
		return quantidadeCorConforme;
	}

	public void setQuantidadeCorConforme(String quantidadeCorConforme) {
		this.quantidadeCorConforme = quantidadeCorConforme;
	}

	public String getQuantidadeColiformesTotaisExigidas() {
		return quantidadeColiformesTotaisExigidas;
	}

	public void setQuantidadeColiformesTotaisExigidas(String quantidadeColiformesTotaisExigidas) {
		this.quantidadeColiformesTotaisExigidas = quantidadeColiformesTotaisExigidas;
	}

	public String getQuantidadeColiformesTotaisAnalisadas() {
		return quantidadeColiformesTotaisAnalisadas;
	}

	public void setQuantidadeColiformesTotaisAnalisadas(String quantidadeColiformesTotaisAnalisadas) {
		this.quantidadeColiformesTotaisAnalisadas = quantidadeColiformesTotaisAnalisadas;
	}

	public String getQuantidadeColiformesTotaisConforme() {
		return quantidadeColiformesTotaisConforme;
	}

	public void setQuantidadeColiformesTotaisConforme(String quantidadeColiformesTotaisConforme) {
		this.quantidadeColiformesTotaisConforme = quantidadeColiformesTotaisConforme;
	}

	public String getQuantidadeColiformesTermotolerantesExigidas() {
		return quantidadeColiformesTermotolerantesExigidas;
	}

	public void setQuantidadeColiformesTermotolerantesExigidas(String quantidadeColiformesTermotolerantesExigidas) {
		this.quantidadeColiformesTermotolerantesExigidas = quantidadeColiformesTermotolerantesExigidas;
	}

	public String getQuantidadeColiformesTermotolerantesAnalisadas() {
		return quantidadeColiformesTermotolerantesAnalisadas;
	}

	public void setQuantidadeColiformesTermotolerantesAnalisadas(String quantidadeColiformesTermotolerantesAnalisadas) {
		this.quantidadeColiformesTermotolerantesAnalisadas = quantidadeColiformesTermotolerantesAnalisadas;
	}

	public String getQuantidadeColiformesTermotolerantesConforme() {
		return quantidadeColiformesTermotolerantesConforme;
	}

	public void setQuantidadeColiformesTermotolerantesConforme(String quantidadeColiformesTermotolerantesConforme) {
		this.quantidadeColiformesTermotolerantesConforme = quantidadeColiformesTermotolerantesConforme;
	}

	public String getCorPadrao() {
		return corPadrao;
	}

	public void setCorPadrao(String corPadrao) {
		this.corPadrao = corPadrao;
	}

	public String getTurbidezPadrao() {
		return turbidezPadrao;
	}

	public void setTurbidezPadrao(String turbidezPadrao) {
		this.turbidezPadrao = turbidezPadrao;
	}

	public String getPhPadrao() {
		return phPadrao;
	}

	public void setPhPadrao(String phPadrao) {
		this.phPadrao = phPadrao;
	}

	public String getDurezaPadrao() {
		return durezaPadrao;
	}

	public void setDurezaPadrao(String durezaPadrao) {
		this.durezaPadrao = durezaPadrao;
	}

	public String getCloroPadrao() {
		return cloroPadrao;
	}

	public void setCloroPadrao(String cloroPadrao) {
		this.cloroPadrao = cloroPadrao;
	}

	public String getCloriformePadrao() {
		return cloriformePadrao;
	}

	public void setCloriformePadrao(String cloriformePadrao) {
		this.cloriformePadrao = cloriformePadrao;
	}

	public BigDecimal getValorBaseCalculo() {
		BigDecimal baseCalculo = BigDecimal.ZERO;

		if(valorAgua != null) {
			baseCalculo = baseCalculo.add(valorAgua);
		}
		if(valorEsgoto != null) {
			baseCalculo = baseCalculo.add(valorEsgoto);
		}

		return baseCalculo;
	}
}