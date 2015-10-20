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

import gcom.relatorio.RelatorioBean;
import gcom.util.serializacao.Campo;

import java.math.BigDecimal;

/**
 * [UCXXXX] - Gerar Relatório Consultar Debitos
 * 
 * @author Rafael Corrêa
 * @date 07/03/2007
 */
public class RelatorioConsultarDebitosClienteBean implements RelatorioBean {
	private Integer idConta;
	
	private String mesAnoConta;

	private String vencimentoConta;

	private BigDecimal valorAgua;
	
	private String consumoAgua;

	private BigDecimal valorEsgoto;
	
	private String consumoEsgoto;

	private BigDecimal valorDebitos;

	private BigDecimal valorCreditos;

	private BigDecimal valorConta;

	private BigDecimal acrescimoImpontualidade;

	private String situacaoConta;
	
	private Integer idDebitoACobrar;

	private String tipoDebitoDebitoACobrar;

	private String mesAnoReferenciaDebito;

	private String mesAnoCobrancaDebito;

	private String parcelasACobrarDebito;

	private BigDecimal valorACobrarDebito;
	
	private Integer idCreditoARealizar;
	
	private String tipoCredito;

	private String mesAnoReferenciaCredito;

	private String mesAnoCobrancaCredito;

	private String parcelasACreditar;

	private BigDecimal valorACreditar;
	
	private Integer idGuiaPagamento;

	private String tipoDebitoGuia;

	private String dataEmissaoGuia;
	
	private String dataVencimentoGuia;

	private BigDecimal valorGuia;
	
	private String valorTotalDebitos;
	
	private String valorTotalDebitosAtualizado;
	
	private Integer idImovel;
	
	private String endereco;
	
	private String nomeUsuario;
	
	private String imprimirEndereco;
	
	private String cpfCnpjUsuario;
	
	private BigDecimal valorMulta;
	
	private BigDecimal valorJurosMora;
	
	private BigDecimal valorAtualizacaoMonetaria;
	
	private BigDecimal valorImpostos;
	
	private String idUsuario;
	
	private String idResponsavel;
	
	private String nomeResponsavel;	
	
	private String cpfCnpjResponsavel;	
	
	private String enderecoResponsavel;
	
	private Integer anoMes;

	private String idInformado;

	private String nomeInformado;
	
	public RelatorioConsultarDebitosClienteBean(String mesAnoConta,
			String vencimentoConta, BigDecimal valorAgua, String consumoAgua,
			BigDecimal valorEsgoto, String consumoEsgoto, BigDecimal valorDebitos,
			BigDecimal valorCreditos, BigDecimal valorConta,
			BigDecimal acrescimoImpontualidade, String situacaoConta,
			Integer idDebitoACobrar, String tipoDebitoDebitoACobrar,
			String mesAnoReferenciaDebito, String mesAnoCobrancaDebito,
			String parcelasACobrarDebito, BigDecimal valorACobrarDebito,
			Integer idCreditoARealizar, String tipoCredito,
			String mesAnoReferenciaCredito, String mesAnoCobrancaCredito,
			String parcelasACreditar, BigDecimal valorACreditar,
			Integer idGuiaPagamento, String tipoDebitoGuia,
			String dataEmissaoGuia, String dataVencimentoGuia,
			BigDecimal valorGuia, String valorTotalDebitos,
			String valorTotalDebitosAtualizado, Integer anoMes,
			String idInformado, String nomeInformado) {

		this.mesAnoConta = mesAnoConta;
		this.vencimentoConta = vencimentoConta;
		this.valorAgua = valorAgua;
		this.consumoAgua = consumoAgua;
		this.valorEsgoto = valorEsgoto;
		this.consumoEsgoto = consumoEsgoto;
		this.valorDebitos = valorDebitos;
		this.valorCreditos = valorCreditos;
		this.valorConta = valorConta;
		this.acrescimoImpontualidade = acrescimoImpontualidade;
		this.situacaoConta = situacaoConta;
		this.idDebitoACobrar = idDebitoACobrar;
		this.tipoDebitoDebitoACobrar = tipoDebitoDebitoACobrar;
		this.mesAnoReferenciaDebito = mesAnoReferenciaDebito;
		this.mesAnoCobrancaDebito = mesAnoCobrancaDebito;
		this.parcelasACobrarDebito = parcelasACobrarDebito;
		this.valorACobrarDebito = valorACobrarDebito;
		this.idCreditoARealizar = idCreditoARealizar;
		this.tipoCredito = tipoCredito;
		this.mesAnoReferenciaCredito = mesAnoReferenciaCredito;
		this.mesAnoCobrancaCredito = mesAnoCobrancaCredito;
		this.parcelasACreditar = parcelasACreditar;
		this.valorACreditar = valorACreditar;
		this.idGuiaPagamento = idGuiaPagamento;
		this.tipoDebitoGuia = tipoDebitoGuia;
		this.dataEmissaoGuia = dataEmissaoGuia;
		this.dataVencimentoGuia = dataVencimentoGuia;
		this.valorGuia = valorGuia;
		this.valorTotalDebitos = valorTotalDebitos;
		this.valorTotalDebitosAtualizado = valorTotalDebitosAtualizado;
		this.anoMes = anoMes;
		this.idInformado = idInformado;  
		this.nomeInformado = nomeInformado;
	}

	public RelatorioConsultarDebitosClienteBean(Integer idConta, String mesAnoConta,
			String vencimentoConta, BigDecimal valorAgua, String consumoAgua,
			BigDecimal valorEsgoto, String consumoEsgoto, BigDecimal valorDebitos,
			BigDecimal valorCreditos, BigDecimal valorConta,
			BigDecimal acrescimoImpontualidade, String situacaoConta,
			Integer idDebitoACobrar, String tipoDebitoDebitoACobrar,
			String mesAnoReferenciaDebito, String mesAnoCobrancaDebito,
			String parcelasACobrarDebito, BigDecimal valorACobrarDebito,
			Integer idCreditoARealizar, String tipoCredito,
			String mesAnoReferenciaCredito, String mesAnoCobrancaCredito,
			String parcelasACreditar, BigDecimal valorACreditar,
			Integer idGuiaPagamento, String tipoDebitoGuia,
			String dataEmissaoGuia, String dataVencimentoGuia,
			BigDecimal valorGuia, String valorTotalDebitos,
			String valorTotalDebitosAtualizado, Integer idImovel, Integer anoMes,
			String idInformado, String nomeInformado) {
		
		this.idConta = idConta;
		this.mesAnoConta = mesAnoConta;
		this.vencimentoConta = vencimentoConta;
		this.valorAgua = valorAgua;
		this.consumoAgua = consumoAgua;
		this.valorEsgoto = valorEsgoto;
		this.consumoEsgoto = consumoEsgoto;
		this.valorDebitos = valorDebitos;
		this.valorCreditos = valorCreditos;
		this.valorConta = valorConta;
		this.acrescimoImpontualidade = acrescimoImpontualidade;
		this.situacaoConta = situacaoConta;
		this.idDebitoACobrar = idDebitoACobrar;
		this.tipoDebitoDebitoACobrar = tipoDebitoDebitoACobrar;
		this.mesAnoReferenciaDebito = mesAnoReferenciaDebito;
		this.mesAnoCobrancaDebito = mesAnoCobrancaDebito;
		this.parcelasACobrarDebito = parcelasACobrarDebito;
		this.valorACobrarDebito = valorACobrarDebito;
		this.idCreditoARealizar = idCreditoARealizar;
		this.tipoCredito = tipoCredito;
		this.mesAnoReferenciaCredito = mesAnoReferenciaCredito;
		this.mesAnoCobrancaCredito = mesAnoCobrancaCredito;
		this.parcelasACreditar = parcelasACreditar;
		this.valorACreditar = valorACreditar;
		this.idGuiaPagamento = idGuiaPagamento;
		this.tipoDebitoGuia = tipoDebitoGuia;
		this.dataEmissaoGuia = dataEmissaoGuia;
		this.dataVencimentoGuia = dataVencimentoGuia;
		this.valorGuia = valorGuia;
		this.valorTotalDebitos = valorTotalDebitos;
		this.valorTotalDebitosAtualizado = valorTotalDebitosAtualizado;
		this.idImovel = idImovel;
		this.anoMes = anoMes;
		this.idInformado = idInformado;  
		this.nomeInformado = nomeInformado;
	}
	
	public RelatorioConsultarDebitosClienteBean(Integer idConta, String mesAnoConta,
			String vencimentoConta, BigDecimal valorAgua, String consumoAgua,
			BigDecimal valorEsgoto, String consumoEsgoto, BigDecimal valorDebitos,
			BigDecimal valorCreditos, BigDecimal valorConta,
			BigDecimal acrescimoImpontualidade, String situacaoConta,
			Integer idDebitoACobrar, String tipoDebitoDebitoACobrar,
			String mesAnoReferenciaDebito, String mesAnoCobrancaDebito,
			String parcelasACobrarDebito, BigDecimal valorACobrarDebito,
			Integer idCreditoARealizar, String tipoCredito,
			String mesAnoReferenciaCredito, String mesAnoCobrancaCredito,
			String parcelasACreditar, BigDecimal valorACreditar,
			Integer idGuiaPagamento, String tipoDebitoGuia,
			String dataEmissaoGuia, String dataVencimentoGuia,
			BigDecimal valorGuia, String valorTotalDebitos,
			String valorTotalDebitosAtualizado, Integer idImovel, String endereco, 
			String nomeUsuario, String imprimirEndereco, String cpfCnpjUsuario, String idUsuario, Integer anoMes,
			String idInformado, String nomeInformado) {
		
		this.idConta = idConta;
		this.mesAnoConta = mesAnoConta;
		this.vencimentoConta = vencimentoConta;
		this.valorAgua = valorAgua;
		this.consumoAgua = consumoAgua;
		this.valorEsgoto = valorEsgoto;
		this.consumoEsgoto = consumoEsgoto;
		this.valorDebitos = valorDebitos;
		this.valorCreditos = valorCreditos;
		this.valorConta = valorConta;
		this.acrescimoImpontualidade = acrescimoImpontualidade;
		this.situacaoConta = situacaoConta;
		this.idDebitoACobrar = idDebitoACobrar;
		this.tipoDebitoDebitoACobrar = tipoDebitoDebitoACobrar;
		this.mesAnoReferenciaDebito = mesAnoReferenciaDebito;
		this.mesAnoCobrancaDebito = mesAnoCobrancaDebito;
		this.parcelasACobrarDebito = parcelasACobrarDebito;
		this.valorACobrarDebito = valorACobrarDebito;
		this.idCreditoARealizar = idCreditoARealizar;
		this.tipoCredito = tipoCredito;
		this.mesAnoReferenciaCredito = mesAnoReferenciaCredito;
		this.mesAnoCobrancaCredito = mesAnoCobrancaCredito;
		this.parcelasACreditar = parcelasACreditar;
		this.valorACreditar = valorACreditar;
		this.idGuiaPagamento = idGuiaPagamento;
		this.tipoDebitoGuia = tipoDebitoGuia;
		this.dataEmissaoGuia = dataEmissaoGuia;
		this.dataVencimentoGuia = dataVencimentoGuia;
		this.valorGuia = valorGuia;
		this.valorTotalDebitos = valorTotalDebitos;
		this.valorTotalDebitosAtualizado = valorTotalDebitosAtualizado;
		this.idImovel = idImovel;
		this.endereco = endereco;
		this.nomeUsuario = nomeUsuario;
		this.imprimirEndereco = imprimirEndereco;
		this.cpfCnpjUsuario = cpfCnpjUsuario;
		this.idUsuario = idUsuario;
		this.anoMes = anoMes; 
		this.idInformado = idInformado;  
		this.nomeInformado = nomeInformado;
	}
	
	@Campo(posicao=1, nome="TIPO DEBITO")
	public String getTipoDebito() {
		if(idConta != null) return "CONTA";
		if(idDebitoACobrar != null) return "DEBITO";
		if(idCreditoARealizar != null) return "CREDITO";
		if(idGuiaPagamento != null) return "GUIA PAGAMENTO";
		return "";
	}

	@Campo(posicao=2, nome="CODIGO CLIENTE INFORMADO")
	public String getIdInformado() {
		return idInformado;
	}

	public void setIdInformado(String idInformado) {
		this.idInformado = idInformado;
	}

	@Campo(posicao=3, nome="NOME CLIENTE INFORMADO")
	public String getNomeInformado() {
		return nomeInformado;
	}

	public void setNomeInformado(String nomeInformado) {
		this.nomeInformado = nomeInformado;
	}
	
	@Campo(posicao=4, nome="CODIGO CLIENTE RESPONSAVEL")
	public String getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(String idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	@Campo(posicao=5, nome="NOME CLIENTE RESPONSAVEL")
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}
	
	@Campo(posicao=6, nome="CODIGO CLIENTE USUARIO")
	public String getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	@Campo(posicao=7, nome="NOME CLIENTE USUARIO")
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	@Campo(posicao=8, nome="MATRICULA IMOVEL")
	public Integer getIdImovel() {
		return idImovel;
	}
	
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	
	@Campo(posicao=9, nome="REFERENCIA CONTA")
	public String getMesAnoConta() {
		return mesAnoConta;
	}

	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	@Campo(posicao=10, nome="VENCIMENTO CONTA")
	public String getVencimentoConta() {
		return vencimentoConta;
	}
	
	public void setVencimentoConta(String vencimentoConta) {
		this.vencimentoConta = vencimentoConta;
	}
	
	@Campo(posicao=11, nome="VALOR AGUA", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorAgua() {
		if(valorAgua == null) {
			valorAgua = BigDecimal.ZERO;
		}
		return valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}
	
	@Campo(posicao=12, nome="VALOR ESGOTO", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorEsgoto() {
		if(valorEsgoto == null) {
			valorEsgoto = BigDecimal.ZERO;
		}
		return valorEsgoto;
	}
	
	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}
	
	@Campo(posicao=13, nome="VALOR DEBITOS", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorDebitos() {
		if(valorDebitos == null) {
			valorDebitos = BigDecimal.ZERO;
		}
		return valorDebitos;
	}

	public void setValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = valorDebitos;
	}

	@Campo(posicao=14, nome="VALOR CREDITOS", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorCreditos() {
		if(valorCreditos == null) {
			valorCreditos = BigDecimal.ZERO;
		}
		return valorCreditos;
	}

	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}
	
	@Campo(posicao=15, nome="VALOR IMPOSTOS", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorImpostos() {
		if(valorImpostos == null) {
			valorImpostos = BigDecimal.ZERO;
		}
		return valorImpostos;
	}

	public void setValorImpostos(BigDecimal valorImpostos) {
		this.valorImpostos = valorImpostos;
	}

	@Campo(posicao=16, nome="VALOR CONTA", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorConta() {
		if(valorConta == null) {
			valorConta = BigDecimal.ZERO;
		}
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}
	
	@Campo(posicao=17, nome="VALOR MULTA", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorMulta() {
		if(valorMulta == null) {
			valorMulta = BigDecimal.ZERO;
		}
		return valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	@Campo(posicao=18, nome="VALOR JUROS", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorJurosMora() {
		if(valorJurosMora == null) {
			valorJurosMora = BigDecimal.ZERO;
		}
		return valorJurosMora;
	}

	public void setValorJurosMora(BigDecimal valorJurosMora) {
		this.valorJurosMora = valorJurosMora;
	}

	@Campo(posicao=19, nome="VALOR ATUALIZACAO MONETARIA", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorAtualizacaoMonetaria() {
		if(valorAtualizacaoMonetaria == null) {
			valorAtualizacaoMonetaria = BigDecimal.ZERO;
		}
		return valorAtualizacaoMonetaria;
	}

	public void setValorAtualizacaoMonetaria(BigDecimal valorAtualizacaoMonetaria) {
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
	}
	
	@Campo(posicao=20, nome="TOTAL DE ACRESCIMO", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorTotalAcrescimos() {
		return getValorMulta().add(getValorJurosMora()).add(getValorAtualizacaoMonetaria());
	}
	
	@Campo(posicao=21, nome="TIPO DEBITO A COBRAR")
	public String getTipoDebitoDebitoACobrar() {
		return tipoDebitoDebitoACobrar;
	}

	public void setTipoDebitoDebitoACobrar(String tipoDebitoDebitoACobrar) {
		this.tipoDebitoDebitoACobrar = tipoDebitoDebitoACobrar;
	}

	@Campo(posicao=22, nome="REFERENCIA DEBITO")
	public String getMesAnoReferenciaDebito() {
		return mesAnoReferenciaDebito;
	}

	public void setMesAnoReferenciaDebito(String mesAnoReferenciaDebito) {
		this.mesAnoReferenciaDebito = mesAnoReferenciaDebito;
	}

	@Campo(posicao=23, nome="REFERENCIA COBRANCA")
	public String getMesAnoCobrancaDebito() {
		return mesAnoCobrancaDebito;
	}

	public void setMesAnoCobrancaDebito(String mesAnoCobrancaDebito) {
		this.mesAnoCobrancaDebito = mesAnoCobrancaDebito;
	}

	@Campo(posicao=24, nome="PARCELAS A COBRAR")
	public String getParcelasACobrarDebito() {
		return parcelasACobrarDebito;
	}

	public void setParcelasACobrarDebito(String parcelasACobrarDebito) {
		this.parcelasACobrarDebito = parcelasACobrarDebito;
	}

	@Campo(posicao=25, nome="VALOR A COBRAR", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorACobrarDebito() {
		if(valorACobrarDebito == null) {
			valorACobrarDebito = BigDecimal.ZERO;
		}
		return valorACobrarDebito;
	}

	public void setValorACobrarDebito(BigDecimal valorACobrarDebito) {
		this.valorACobrarDebito = valorACobrarDebito;
	}

	@Campo(posicao=26, nome="TIPO CREDITO A REALIZAR")
	public String getTipoCredito() {
		return tipoCredito;
	}

	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	@Campo(posicao=27, nome="REFERENCIA CREDITO")
	public String getMesAnoReferenciaCredito() {
		return mesAnoReferenciaCredito;
	}

	public void setMesAnoReferenciaCredito(String mesAnoReferenciaCredito) {
		this.mesAnoReferenciaCredito = mesAnoReferenciaCredito;
	}

	@Campo(posicao=28, nome="REFERENCIA COBRANCA CREDITO")
	public String getMesAnoCobrancaCredito() {
		return mesAnoCobrancaCredito;
	}

	public void setMesAnoCobrancaCredito(String mesAnoCobrancaCredito) {
		this.mesAnoCobrancaCredito = mesAnoCobrancaCredito;
	}

	@Campo(posicao=29, nome="PARCELAS A CREDITAR")
	public String getParcelasACreditar() {
		return parcelasACreditar;
	}

	public void setParcelasACreditar(String parcelasACreditar) {
		this.parcelasACreditar = parcelasACreditar;
	}

	@Campo(posicao=30, nome="VALOR A CREDITAR", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorACreditar() {
		if(valorACreditar == null) {
			valorACreditar = BigDecimal.ZERO;
		}
		return valorACreditar;
	}

	public void setValorACreditar(BigDecimal valorACreditar) {
		this.valorACreditar = valorACreditar;
	}
	
	@Campo(posicao=31, nome="TIPO DEBITO GUIA PAGAMENTO")
	public String getTipoDebitoGuia() {
		return tipoDebitoGuia;
	}

	public void setTipoDebitoGuia(String tipoDebitoGuia) {
		this.tipoDebitoGuia = tipoDebitoGuia;
	}

	@Campo(posicao=32, nome="DATA EMISSAO GUIA PAGAMENTO")
	public String getDataEmissaoGuia() {
		return dataEmissaoGuia;
	}

	public void setDataEmissaoGuia(String dataEmissaoGuia) {
		this.dataEmissaoGuia = dataEmissaoGuia;
	}

	@Campo(posicao=33, nome="DATA VENCIMENTO GUIA PAGAMENTO")
	public String getDataVencimentoGuia() {
		return dataVencimentoGuia;
	}

	public void setDataVencimentoGuia(String dataVencimentoGuia) {
		this.dataVencimentoGuia = dataVencimentoGuia;
	}

	@Campo(posicao=34, nome="VALOR GUIA PAGAMENTO", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorGuia() {
		if(valorGuia == null) {
			valorGuia = BigDecimal.ZERO;
		}
		return valorGuia;
	}

	public void setValorGuia(BigDecimal valorGuia) {
		this.valorGuia = valorGuia;
	}
	
	public String getValorTotalDebitos() {
		return valorTotalDebitos;
	}

	public void setValorTotalDebitos(String valorTotalDebitos) {
		this.valorTotalDebitos = valorTotalDebitos;
	}

	public String getValorTotalDebitosAtualizado() {
		return valorTotalDebitosAtualizado;
	}

	public void setValorTotalDebitosAtualizado(String valorTotalDebitosAtualizado) {
		this.valorTotalDebitosAtualizado = valorTotalDebitosAtualizado;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getImprimirEndereco() {
		return imprimirEndereco;
	}

	public void setImprimirEndereco(String imprimirEndereco) {
		this.imprimirEndereco = imprimirEndereco;
	}

	public String getCpfCnpjUsuario() {
		return cpfCnpjUsuario;
	}

	public void setCpfCnpjUsuario(String cpfCnpjUsuario) {
		this.cpfCnpjUsuario = cpfCnpjUsuario;
	}

	public String getCpfCnpjResponsavel() {
		return cpfCnpjResponsavel;
	}

	public void setCpfCnpjResponsavel(String cpfCnpjResponsavel) {
		this.cpfCnpjResponsavel = cpfCnpjResponsavel;
	}

	public String getEnderecoResponsavel() {
		return enderecoResponsavel;
	}

	public void setEnderecoResponsavel(String enderecoResponsavel) {
		this.enderecoResponsavel = enderecoResponsavel;
	}

	public Integer getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(Integer anoMes) {
		this.anoMes = anoMes;
	}
	
	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public String getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(String consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public String getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(String consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}
	
	public BigDecimal getAcrescimoImpontualidade() {
		if(acrescimoImpontualidade == null) {
			acrescimoImpontualidade = BigDecimal.ZERO;
		}
		return acrescimoImpontualidade;
	}

	public void setAcrescimoImpontualidade(BigDecimal acrescimoImpontualidade) {
		this.acrescimoImpontualidade = acrescimoImpontualidade;
	}

	public String getSituacaoConta() {
		return situacaoConta;
	}

	public void setSituacaoConta(String situacaoConta) {
		this.situacaoConta = situacaoConta;
	}

	public Integer getIdDebitoACobrar() {
		return idDebitoACobrar;
	}

	public void setIdDebitoACobrar(Integer idDebitoACobrar) {
		this.idDebitoACobrar = idDebitoACobrar;
	}
	
	public Integer getIdCreditoARealizar() {
		return idCreditoARealizar;
	}

	public void setIdCreditoARealizar(Integer idCreditoARealizar) {
		this.idCreditoARealizar = idCreditoARealizar;
	}
	
	public Integer getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	public void setIdGuiaPagamento(Integer idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}
}
