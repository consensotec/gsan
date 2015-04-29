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
package gsan.cobranca.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Rafael Corr�a
 * @since 25/09/2006
 *
 */
public class ParcelamentoRelatorioHelper {
	
	/**
	 * Id do Im�vel
	 */
	private Integer idImovel;
	
	/**
	 * Nome do Cliente
	 */
	private String nomeCliente;
	
	/**
	 * Endere�o
	 */
	private String endereco;
	
	/**
	 * CPF/CNPJ
	 */
	private String cpfCnpj;

	/**
	 * Telefone
	 */
	private String telefone;
	
	/**
	 * Data do Parcelamento
	 */
	private Date dataParcelamento;
	
	/**
	 * Valor das Faturas em Aberto
	 */
	private BigDecimal valorFaturasEmAberto;
	
	/**
	 * Valor dos Servi�os a Cobrar
	 */
	private BigDecimal valorServicosACobrar;
	
	/**
	 * Valor de Atualizacao Monetaria
	 */
	private BigDecimal valorAtualizacaoMonetaria;
	
	/**
	 * Valor de Juros/Mora
	 */
	private BigDecimal valorJurosMora;
	
	/**
	 * Valor das Multas
	 */
	private BigDecimal valorMultas;
	
	/**
	 * Valor das Guias de Pagamento
	 */
	private BigDecimal valorGuiaPagamento;

	/**
	 * Valor do Parcelamento a Cobrar
	 */
	private BigDecimal valorParcelamentoACobrar;
	
	/**
	 * Valor Total dos D�bitos
	 */
	private BigDecimal valorTotalDebitos;
	
	/**
	 * Valor dos Descontos de Acr�scimos
	 */
	private BigDecimal valorDescontoAcrescimo;
	
	/**
	 * Valor dos Descontos em Antiguidade
	 */
	private BigDecimal valorDescontoAntiguidade;
	
	/**
	 * Valor dos Descontos de Inatividade
	 */
	private BigDecimal valorDescontoInatividade;
	
	/**
	 * Valor dos Cr�ditos a Realizar
	 */
	private BigDecimal valorCreditosRealizar;
	
	/**
	 * Valor Total dos Descontos
	 */
	private BigDecimal valorTotalDescontos;
	
	/**
	 * Valor a Ser Negociado
	 */
	private BigDecimal valorASerNegociado;
	
	/**
	 * Valor da Entrada
	 */
	private BigDecimal valorEntrada;
	
	/**
	 * N�mero de Parcelas
	 */
	private Short numeroParcelas;
	
	/**
	 * Valor da Parcela
	 */
	private BigDecimal valorParcela;
	
	/**
	 * Valor a Ser Parcelado
	 */
	private BigDecimal valorASerParcelado;
	
	/**
	 * Solicita��o de Restabelecimento
	 */
	private String solicitacaoRestabelecimento;
	
	/**
	 * Nome do Mun�cipio
	 */
	private String nomeMunicipio;
	
	/**
	 * Id do Funcion�rio
	 */
	private Integer idFuncionario;
	
	/**
	 * Unidade do Usu�rio
	 */
	private BigDecimal unidadeUsuario;
	
	/**
	 * Nome do Cliente do Parcelamento
	 */
	private String nomeClienteParcelamento;
	
	/**
	 * Id do Cliente do Parcelamento
	 */
	private Integer idClienteParcelamento;
	
	/**
	 * CPF do Cliente do Parcelamento
	 */
	private String cpfClienteParcelamento;
	
	/**
	 * RG do Cliente 
	 */
	private String rgCliente;
	
	/**
	 * orgao expedidor RG do Cliente 
	 */
	private String descOrgaoExpRgCliente;
	
	/**
	 * unidade federacao RG do Cliente 
	 */
	private String siglaUnidadeFederacaoRgCliente;


	private String mesAnoInicioParcelamento;
	
	private String mesAnoFinalParcelamento;
	
	/**
	 * RG do Cliente do Parcelamento 
	 */
	private String rgClienteParcelamento;
	
	
	private String taxaJuros;
	
	/**
	 * Valor dos Descontos de San��es Regulamentares
	 */
	private BigDecimal valorDescontoSancoesRegulamentares;
    
    
    private BigDecimal valorDescontoTarifaSocial;
    
    private BigDecimal valorDescontoDebitoTotal;
    
    private String nomeDiretorComercial;
    
    private String cpfDiretorComercial;
    
    private String profissao;
    
    private String nomeDevedor;
    
    private String cnpjDevedor;
    
    private String enderecoDevedor;
    
    private String telefoneDevedor;
    
    private Short indicadorPessoaJuridica;
    
    private Integer idDevedor;
    
    private String NomeUsuarioParcelamento;
    
    private String bairro;
    
    private Short codigoRota;
    
    private String localidade;
    
    private String setorComercial;
    
    private String enderecoResponsavel;
    
    private String bairroImovelResponsavel;
    
	/**
	 * Valor Total dos Descontos sem Valor dos Creditos
	 */
	private BigDecimal valorTotalDescontosSemValorCreditos;
	
	private BigDecimal valorDescontoTipoDebito;
	
	private BigDecimal valorDescontoMulta;
	
	private BigDecimal valorDescontoJuros;
	
	private BigDecimal valorDescontoAtualizacaoMonetaria;
	
	private BigDecimal valorDescontoEntradaParc;
	
	private String textoClausula2;
	
    private String paragrafoSegundo;	

	
	public Integer getIdDevedor() {
		return idDevedor;
	}

	public BigDecimal getValorTotalDescontosSemValorCreditos() {
		return valorTotalDescontosSemValorCreditos;
	}

	public void setValorTotalDescontosSemValorCreditos(
			BigDecimal valorTotalDescontosSemValorCreditos) {
		this.valorTotalDescontosSemValorCreditos = valorTotalDescontosSemValorCreditos;
	}

	public void setIdDevedor(Integer idDevedor) {
		this.idDevedor = idDevedor;
	}

	public String getEnderecoResponsavel() {
		return enderecoResponsavel;
	}

	public void setEnderecoResponsavel(String enderecoResponsavel) {
		this.enderecoResponsavel = enderecoResponsavel;
	}
	

	public String getBairroImovelResponsavel() {
		return bairroImovelResponsavel;
	}

	public void setBairroImovelResponsavel(String bairroImovelResponsavel) {
		this.bairroImovelResponsavel = bairroImovelResponsavel;
	}

	public String getCnpjDevedor() {
		return cnpjDevedor;
	}

	public void setCnpjDevedor(String cnpjDevedor) {
		this.cnpjDevedor = cnpjDevedor;
	}

	public String getEnderecoDevedor() {
		return enderecoDevedor;
	}

	public void setEnderecoDevedor(String enderecoDevedor) {
		this.enderecoDevedor = enderecoDevedor;
	}

	public String getNomeDevedor() {
		return nomeDevedor;
	}

	public void setNomeDevedor(String nomeDevedor) {
		this.nomeDevedor = nomeDevedor;
	}

	public String getTelefoneDevedor() {
		return telefoneDevedor;
	}

	public void setTelefoneDevedor(String telefoneDevedor) {
		this.telefoneDevedor = telefoneDevedor;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getCpfDiretorComercial() {
		return cpfDiretorComercial;
	}

	public void setCpfDiretorComercial(String cpfDiretorComercial) {
		this.cpfDiretorComercial = cpfDiretorComercial;
	}

	public String getNomeDiretorComercial() {
		return nomeDiretorComercial;
	}

	public void setNomeDiretorComercial(String nomeDiretorComercial) {
		this.nomeDiretorComercial = nomeDiretorComercial;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Date getDataParcelamento() {
		return dataParcelamento;
	}

	public void setDataParcelamento(Date dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public Short getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Short numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public String getSolicitacaoRestabelecimento() {
		return solicitacaoRestabelecimento;
	}

	public void setSolicitacaoRestabelecimento(String solicitacaoRestabelecimento) {
		this.solicitacaoRestabelecimento = solicitacaoRestabelecimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public BigDecimal getUnidadeUsuario() {
		return unidadeUsuario;
	}

	public void setUnidadeUsuario(BigDecimal unidadeUsuario) {
		this.unidadeUsuario = unidadeUsuario;
	}

	public BigDecimal getValorASerNegociado() {
		return valorASerNegociado;
	}

	public void setValorASerNegociado(BigDecimal valorASerNegociado) {
		this.valorASerNegociado = valorASerNegociado;
	}

	public BigDecimal getValorASerParcelado() {
		return valorASerParcelado;
	}

	public void setValorASerParcelado(BigDecimal valorASerParcelado) {
		this.valorASerParcelado = valorASerParcelado;
	}

	public BigDecimal getValorAtualizacaoMonetaria() {
		return valorAtualizacaoMonetaria;
	}

	public void setValorAtualizacaoMonetaria(BigDecimal valorAtualizacaoMonetaria) {
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
	}

	public BigDecimal getValorCreditosRealizar() {
		return valorCreditosRealizar;
	}

	public void setValorCreditosRealizar(BigDecimal valorCreditosRealizar) {
		this.valorCreditosRealizar = valorCreditosRealizar;
	}

	public BigDecimal getValorDescontoAcrescimo() {
		return valorDescontoAcrescimo;
	}

	public void setValorDescontoAcrescimo(BigDecimal valorDescontoAcrescimo) {
		this.valorDescontoAcrescimo = valorDescontoAcrescimo;
	}

	public BigDecimal getValorDescontoAntiguidade() {
		return valorDescontoAntiguidade;
	}

	public void setValorDescontoAntiguidade(BigDecimal valorDescontoAntiguidade) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
	}

	public BigDecimal getValorDescontoInatividade() {
		return valorDescontoInatividade;
	}

	public void setValorDescontoInatividade(BigDecimal valorDescontoInatividade) {
		this.valorDescontoInatividade = valorDescontoInatividade;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public BigDecimal getValorFaturasEmAberto() {
		return valorFaturasEmAberto;
	}

	public void setValorFaturasEmAberto(BigDecimal valorFaturasEmAberto) {
		this.valorFaturasEmAberto = valorFaturasEmAberto;
	}

	public BigDecimal getValorGuiaPagamento() {
		return valorGuiaPagamento;
	}

	public void setValorGuiaPagamento(BigDecimal valorGuiaPagamento) {
		this.valorGuiaPagamento = valorGuiaPagamento;
	}

	public BigDecimal getValorJurosMora() {
		return valorJurosMora;
	}

	public void setValorJurosMora(BigDecimal valorJurosMora) {
		this.valorJurosMora = valorJurosMora;
	}

	public BigDecimal getValorMultas() {
		return valorMultas;
	}

	public void setValorMultas(BigDecimal valorMultas) {
		this.valorMultas = valorMultas;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public BigDecimal getValorParcelamentoACobrar() {
		return valorParcelamentoACobrar;
	}

	public void setValorParcelamentoACobrar(BigDecimal valorParcelamentoACobrar) {
		this.valorParcelamentoACobrar = valorParcelamentoACobrar;
	}

	public BigDecimal getValorServicosACobrar() {
		return valorServicosACobrar;
	}

	public void setValorServicosACobrar(BigDecimal valorServicosACobrar) {
		this.valorServicosACobrar = valorServicosACobrar;
	}

	public BigDecimal getValorTotalDebitos() {
		return valorTotalDebitos;
	}

	public void setValorTotalDebitos(BigDecimal valorTotalDebitos) {
		this.valorTotalDebitos = valorTotalDebitos;
	}

	public BigDecimal getValorTotalDescontos() {
		return valorTotalDescontos;
	}

	public void setValorTotalDescontos(BigDecimal valorTotalDescontos) {
		this.valorTotalDescontos = valorTotalDescontos;
	}

	/**
	 * @return Retorna o campo cpfClienteParcelamento.
	 */
	public String getCpfClienteParcelamento() {
		return cpfClienteParcelamento;
	}

	/**
	 * @param cpfClienteParcelamento O cpfClienteParcelamento a ser setado.
	 */
	public void setCpfClienteParcelamento(String cpfClienteParcelamento) {
		this.cpfClienteParcelamento = cpfClienteParcelamento;
	}

	/**
	 * @return Retorna o campo nomeClienteParcelamento.
	 */
	public String getNomeClienteParcelamento() {
		return nomeClienteParcelamento;
	}

	/**
	 * @param nomeClienteParcelamento O nomeClienteParcelamento a ser setado.
	 */
	public void setNomeClienteParcelamento(String nomeClienteParcelamento) {
		this.nomeClienteParcelamento = nomeClienteParcelamento;
	}

	public String getRgCliente() {
		return rgCliente;
	}

	public void setRgCliente(String rgCliente) {
		this.rgCliente = rgCliente;
	}



	public String getDescOrgaoExpRgCliente() {
		return descOrgaoExpRgCliente;
	}

	public void setDescOrgaoExpRgCliente(String descOrgaoExpRgCliente) {
		this.descOrgaoExpRgCliente = descOrgaoExpRgCliente;
	}

	public String getSiglaUnidadeFederacaoRgCliente() {
		return siglaUnidadeFederacaoRgCliente;
	}

	public void setSiglaUnidadeFederacaoRgCliente(
			String siglaUnidadeFederacaoRgCliente) {
		this.siglaUnidadeFederacaoRgCliente = siglaUnidadeFederacaoRgCliente;
	}

	public String getMesAnoFinalParcelamento() {
		return mesAnoFinalParcelamento;
	}

	public void setMesAnoFinalParcelamento(String mesAnoFinalParcelamento) {
		this.mesAnoFinalParcelamento = mesAnoFinalParcelamento;
	}

	public String getMesAnoInicioParcelamento() {
		return mesAnoInicioParcelamento;
	}

	public void setMesAnoInicioParcelamento(String mesAnoInicioParcelamento) {
		this.mesAnoInicioParcelamento = mesAnoInicioParcelamento;
	}

	public String getRgClienteParcelamento() {
		return rgClienteParcelamento;
	}

	public void setRgClienteParcelamento(String rgClienteParcelamento) {
		this.rgClienteParcelamento = rgClienteParcelamento;
	}

	public String getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(String taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public BigDecimal getValorDescontoSancoesRegulamentares() {
		return valorDescontoSancoesRegulamentares;
	}

	public void setValorDescontoSancoesRegulamentares(
			BigDecimal valorDescontoSancoesRegulamentares) {
		this.valorDescontoSancoesRegulamentares = valorDescontoSancoesRegulamentares;
	}

    public BigDecimal getValorDescontoTarifaSocial() {
        return valorDescontoTarifaSocial;
    }

    public void setValorDescontoTarifaSocial(BigDecimal valorDescontoTarifaSocial) {
        this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
    }

	public Short getIndicadorPessoaJuridica() {
		return indicadorPessoaJuridica;
	}

	public void setIndicadorPessoaJuridica(Short indicadorPessoaJuridica) {
		this.indicadorPessoaJuridica = indicadorPessoaJuridica;
	}

    public String getNomeUsuarioParcelamento() {
        return NomeUsuarioParcelamento;
    }

    public void setNomeUsuarioParcelamento(String nomeUsuarioParcelamento) {
        NomeUsuarioParcelamento = nomeUsuarioParcelamento;
    }	
    
    public BigDecimal getValorASerNegociadoSemDesconto() {
    	
    	BigDecimal valorASerNegociadoSemDesconto = getValorASerNegociado();
    	
//    	if (getValorTotalDescontosSemValorCreditos() != null &&
//    		!getValorTotalDescontosSemValorCreditos().equals(new BigDecimal("0.00"))){
//    		
//    		valorASerNegociadoSemDesconto = 
//    			valorASerNegociadoSemDesconto.add(getValorTotalDescontosSemValorCreditos());
//    	}
    	
        return valorASerNegociadoSemDesconto;
    }

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	/**
	 * @return Returns the localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade The localidade to set.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Returns the setorComercial.
	 */
	public String getSetorComercial() {
		return setorComercial;
	}

	/**
	 * @param setorComercial The setorComercial to set.
	 */
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public BigDecimal getValorDescontoDebitoTotal() {
		return valorDescontoDebitoTotal;
	}

	public void setValorDescontoDebitoTotal(BigDecimal valorDescontoDebitoTotal) {
		this.valorDescontoDebitoTotal = valorDescontoDebitoTotal;
	}

	public BigDecimal getValorDescontoTipoDebito() {
		return valorDescontoTipoDebito;
	}

	public void setValorDescontoTipoDebito(BigDecimal valorDescontoTipoDebito) {
		this.valorDescontoTipoDebito = valorDescontoTipoDebito;
	}

	public BigDecimal getValorDescontoMulta() {
		return valorDescontoMulta;
	}

	public void setValorDescontoMulta(BigDecimal valorDescontoMulta) {
		this.valorDescontoMulta = valorDescontoMulta;
	}

	public BigDecimal getValorDescontoJuros() {
		return valorDescontoJuros;
	}

	public void setValorDescontoJuros(BigDecimal valorDescontoJuros) {
		this.valorDescontoJuros = valorDescontoJuros;
	}

	public BigDecimal getValorDescontoAtualizacaoMonetaria() {
		return valorDescontoAtualizacaoMonetaria;
	}

	public void setValorDescontoAtualizacaoMonetaria(
			BigDecimal valorDescontoAtualizacaoMonetaria) {
		this.valorDescontoAtualizacaoMonetaria = valorDescontoAtualizacaoMonetaria;
	}

	public BigDecimal getValorDescontoEntradaParc() {
		return valorDescontoEntradaParc;
	}

	public void setValorDescontoEntradaParc(BigDecimal valorDescontoEntradaParc) {
		this.valorDescontoEntradaParc = valorDescontoEntradaParc;
	}

	public Integer getIdClienteParcelamento() {
		return idClienteParcelamento;
	}

	public void setIdClienteParcelamento(Integer idClienteParcelamento) {
		this.idClienteParcelamento = idClienteParcelamento;
	}

	public String getTextoClausula2() {
		return textoClausula2;
	}

	public void setTextoClausula2(String textoClausula2) {
		this.textoClausula2 = textoClausula2;
	}

	public String getParagrafoSegundo() {
		return paragrafoSegundo;
	}

	public void setParagrafoSegundo(String paragrafoSegundo) {
		this.paragrafoSegundo = paragrafoSegundo;
	}
	
	
}
