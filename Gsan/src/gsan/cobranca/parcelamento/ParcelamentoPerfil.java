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
package gsan.cobranca.parcelamento;

import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.imovel.ImovelSituacaoTipo;
import gsan.cadastro.imovel.Subcategoria;
import gsan.cobranca.ResolucaoDiretoria;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ParcelamentoPerfil extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private BigDecimal percentualDescontoAcrescimo;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Subcategoria subcategoria;

    /** persistent field */
    private ImovelSituacaoTipo imovelSituacaoTipo;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private ResolucaoDiretoria resolucaoDiretoria;
    
    /** persistent field */
    private BigDecimal  percentualTarifaMinimaPrestacao;
    
    /** persistent field */
    private Integer  numeroConsumoMinimo;
    
    /** persistent field */
    private BigDecimal  percentualVariacaoConsumoMedio;
    
    /** persistent field */
    private Short  indicadorChequeDevolvido;
    
    /** persistent field */
    private Short  indicadorSancoesUnicaConta;
    
    private Short  indicadorRetroativoTarifaSocial;
    
    private Integer anoMesReferenciaLimiteInferior;
    
    private Integer anoMesReferenciaLimiteSuperior;
    
    private BigDecimal percentualDescontoAVista;
    
    private Integer parcelaQuantidadeMinimaFatura;
    
    private Short  indicadorAlertaParcelaMinima;
    
    private Integer numeroConsumoEconomia;
    
    private BigDecimal numeroAreaConstruida;
    
    private Categoria categoria;
    
    private BigDecimal percentualDescontoSancao;
    
    private Integer quantidadeEconomias;
   
    private Short capacidadeHidrometro;
   
    private Short indicadorEntradaMinima;
    
    private Integer  quantidadeMaximaReparcelamento;
    
    private BigDecimal percentualDescontoPagamentoAVista;
    
    private Date dataLimiteDescontoPagamentoAVista;
    
    private BigDecimal percentualDescontoDebitoPagamentoAVista;
    
    private BigDecimal percentualDescontoDebitoPagamentoParcelado;
    
    private Date dataLimiteVencimentoContaAVista;
    
    private Date dataLimiteVencimentoContaParcelado;
    
    private BigDecimal percentualDescontoMulta;
    private BigDecimal percentualDescontoJuros;
    private BigDecimal percentualDescontoAtualizacaoMonetaria;
    private BigDecimal percentualDescontoPagamentoAVistaMulta;
    private BigDecimal percentualDescontoPagamentoAVistaJuros;
    private BigDecimal percentualDescontoPagamentoAVistaAtuMonetaria;
	
    private Integer anoMesReferenciaLimiteContaPagamentoAVista;
    private Integer anoMesReferenciaLimiteContaParcelada;
    private BigDecimal percentualValorContaConsumoMedioPrestacao;
	
    
    
    public Short getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	public void setCapacidadeHidrometro(Short capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}

	/**
	 * @return Retorna o campo quantidadeEconomias.
	 */
	public Integer getQuantidadeEconomias() {
		return quantidadeEconomias;
	}
	
	/**
	 * @param quantidadeEconomias O quantidadeEconomias a ser setado.
	 */
	public void setQuantidadeEconomias(Integer quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	/**
	 * @return Retorna o campo percentualTarifaMinimaPrestacao.
	 */
	public BigDecimal getPercentualTarifaMinimaPrestacao() {
		return percentualTarifaMinimaPrestacao;
	}

	/**
	 * @param percentualTarifaMinimaPrestacao O percentualTarifaMinimaPrestacao a ser setado.
	 */
	public void setPercentualTarifaMinimaPrestacao(
			BigDecimal percentualTarifaMinimaPrestacao) {
		this.percentualTarifaMinimaPrestacao = percentualTarifaMinimaPrestacao;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroParcelamentoPerfil filtroParcelamentoPerfil = new FiltroParcelamentoPerfil();
		
		filtroParcelamentoPerfil. adicionarCaminhoParaCarregamentoEntidade("subcategoria");
		filtroParcelamentoPerfil. adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
		filtroParcelamentoPerfil. adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		filtroParcelamentoPerfil. adicionarCaminhoParaCarregamentoEntidade("resolucaoDiretoria");
		filtroParcelamentoPerfil. adicionarParametro(
				new ParametroSimples(FiltroParcelamentoPerfil.ID, this.getId()));
		return filtroParcelamentoPerfil; 
	}
    
    /** full constructor */
    public ParcelamentoPerfil(BigDecimal percentualDescontoAcrescimo, Date ultimaAlteracao, Subcategoria subcategoria, ImovelSituacaoTipo imovelSituacaoTipo, ImovelPerfil imovelPerfil, ResolucaoDiretoria resolucaoDiretoria) {
        this.percentualDescontoAcrescimo = percentualDescontoAcrescimo;
        this.ultimaAlteracao = ultimaAlteracao;
        this.subcategoria = subcategoria;
        this.imovelSituacaoTipo = imovelSituacaoTipo;
        this.imovelPerfil = imovelPerfil;
        this.resolucaoDiretoria = resolucaoDiretoria;
    }

    /** default constructor */
    public ParcelamentoPerfil() {
    }

    /** minimal constructor */
    public ParcelamentoPerfil(Subcategoria subcategoria, ImovelSituacaoTipo imovelSituacaoTipo, ImovelPerfil imovelPerfil, ResolucaoDiretoria resolucaoDiretoria) {
        this.subcategoria = subcategoria;
        this.imovelSituacaoTipo = imovelSituacaoTipo;
        this.imovelPerfil = imovelPerfil;
        this.resolucaoDiretoria = resolucaoDiretoria;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPercentualDescontoAcrescimo() {
        return this.percentualDescontoAcrescimo;
    }

    public void setPercentualDescontoAcrescimo(BigDecimal percentualDescontoAcrescimo) {
        this.percentualDescontoAcrescimo = percentualDescontoAcrescimo;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Subcategoria getSubcategoria() {
        return this.subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public ImovelSituacaoTipo getImovelSituacaoTipo() {
        return this.imovelSituacaoTipo;
    }

    public void setImovelSituacaoTipo(ImovelSituacaoTipo imovelSituacaoTipo) {
        this.imovelSituacaoTipo = imovelSituacaoTipo;
    }

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
    }

    public ResolucaoDiretoria getResolucaoDiretoria() {
        return this.resolucaoDiretoria;
    }

    public void setResolucaoDiretoria(ResolucaoDiretoria resolucaoDiretoria) {
        this.resolucaoDiretoria = resolucaoDiretoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Short getIndicadorChequeDevolvido() {
		return indicadorChequeDevolvido;
	}

	public void setIndicadorChequeDevolvido(Short indicadorChequeDevolvido) {
		this.indicadorChequeDevolvido = indicadorChequeDevolvido;
	}

	public Short getIndicadorSancoesUnicaConta() {
		return indicadorSancoesUnicaConta;
	}

	public void setIndicadorSancoesUnicaConta(Short indicadorSancoesUnicaConta) {
		this.indicadorSancoesUnicaConta = indicadorSancoesUnicaConta;
	}

	public Integer getNumeroConsumoMinimo() {
		return numeroConsumoMinimo;
	}

	public void setNumeroConsumoMinimo(Integer numeroConsumoMinimo) {
		this.numeroConsumoMinimo = numeroConsumoMinimo;
	}

	public BigDecimal getPercentualVariacaoConsumoMedio() {
		return percentualVariacaoConsumoMedio;
	}

	public void setPercentualVariacaoConsumoMedio(
			BigDecimal percentualVariacaoConsumoMedio) {
		this.percentualVariacaoConsumoMedio = percentualVariacaoConsumoMedio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getNumeroAreaConstruida() {
		return numeroAreaConstruida;
	}

	public void setNumeroAreaConstruida(BigDecimal numeroAreaConstruida) {
		this.numeroAreaConstruida = numeroAreaConstruida;
	}

	public Integer getNumeroConsumoEconomia() {
		return numeroConsumoEconomia;
	}

	public void setNumeroConsumoEconomia(Integer numeroConsumoEconomia) {
		this.numeroConsumoEconomia = numeroConsumoEconomia;
	}

	public Integer getAnoMesReferenciaLimiteInferior() {
		return anoMesReferenciaLimiteInferior;
	}

	public void setAnoMesReferenciaLimiteInferior(
			Integer anoMesReferenciaLimiteInferior) {
		this.anoMesReferenciaLimiteInferior = anoMesReferenciaLimiteInferior;
	}

	public Integer getAnoMesReferenciaLimiteSuperior() {
		return anoMesReferenciaLimiteSuperior;
	}

	public void setAnoMesReferenciaLimiteSuperior(
			Integer anoMesReferenciaLimiteSuperior) {
		this.anoMesReferenciaLimiteSuperior = anoMesReferenciaLimiteSuperior;
	}

	public Short getIndicadorAlertaParcelaMinima() {
		return indicadorAlertaParcelaMinima;
	}

	public void setIndicadorAlertaParcelaMinima(Short indicadorAlertaParcelaMinima) {
		this.indicadorAlertaParcelaMinima = indicadorAlertaParcelaMinima;
	}

	public Short getIndicadorRetroativoTarifaSocial() {
		return indicadorRetroativoTarifaSocial;
	}

	public void setIndicadorRetroativoTarifaSocial(
			Short indicadorRetroativoTarifaSocial) {
		this.indicadorRetroativoTarifaSocial = indicadorRetroativoTarifaSocial;
	}

	public Integer getParcelaQuantidadeMinimaFatura() {
		return parcelaQuantidadeMinimaFatura;
	}

	public void setParcelaQuantidadeMinimaFatura(
			Integer parcelaQuantidadeMinimaFatura) {
		this.parcelaQuantidadeMinimaFatura = parcelaQuantidadeMinimaFatura;
	}

	/**
	 * @return Retorna o campo percentualDescontoAVista.
	 */
	public BigDecimal getPercentualDescontoAVista() {
		return percentualDescontoAVista;
	}

	/**
	 * @param percentualDescontoAVista O percentualDescontoAVista a ser setado.
	 */
	public void setPercentualDescontoAVista(BigDecimal percentualDescontoAVista) {
		this.percentualDescontoAVista = percentualDescontoAVista;
	}

	/**
	 * @return Retorna o campo percentualDescontoSancao.
	 */
	public BigDecimal getPercentualDescontoSancao() {
		return percentualDescontoSancao;
	}

	/**
	 * @param percentualDescontoSancao O percentualDescontoSancao a ser setado.
	 */
	public void setPercentualDescontoSancao(BigDecimal percentualDescontoSancao) {
		this.percentualDescontoSancao = percentualDescontoSancao;
	}

	public Short getIndicadorEntradaMinima() {
		return indicadorEntradaMinima;
	}

	public void setIndicadorEntradaMinima(Short indicadorEntradaMinima) {
		this.indicadorEntradaMinima = indicadorEntradaMinima;
	}

	public Integer getQuantidadeMaximaReparcelamento() {
		return quantidadeMaximaReparcelamento;
	}

	public void setQuantidadeMaximaReparcelamento(
			Integer quantidadeMaximaReparcelamento) {
		this.quantidadeMaximaReparcelamento = quantidadeMaximaReparcelamento;
	}

	public BigDecimal getPercentualDescontoPagamentoAVista() {
		return percentualDescontoPagamentoAVista;
	}

	public void setPercentualDescontoPagamentoAVista(
			BigDecimal percentualDescontoPagamentoAVista) {
		this.percentualDescontoPagamentoAVista = percentualDescontoPagamentoAVista;
	}

	public Date getDataLimiteDescontoPagamentoAVista() {
		return dataLimiteDescontoPagamentoAVista;
	}

	public void setDataLimiteDescontoPagamentoAVista(
			Date dataLimiteDescontoPagamentoAVista) {
		this.dataLimiteDescontoPagamentoAVista = dataLimiteDescontoPagamentoAVista;
	}

	public BigDecimal getPercentualDescontoDebitoPagamentoAVista() {
		return percentualDescontoDebitoPagamentoAVista;
	}

	public void setPercentualDescontoDebitoPagamentoAVista(
			BigDecimal percentualDescontoDebitoPagamentoAVista) {
		this.percentualDescontoDebitoPagamentoAVista = percentualDescontoDebitoPagamentoAVista;
	}

	public BigDecimal getPercentualDescontoDebitoPagamentoParcelado() {
		return percentualDescontoDebitoPagamentoParcelado;
	}

	public void setPercentualDescontoDebitoPagamentoParcelado(
			BigDecimal percentualDescontoDebitoPagamentoParcelado) {
		this.percentualDescontoDebitoPagamentoParcelado = percentualDescontoDebitoPagamentoParcelado;
	}

	public Date getDataLimiteVencimentoContaAVista() {
		return dataLimiteVencimentoContaAVista;
	}

	public void setDataLimiteVencimentoContaAVista(
			Date dataLimiteVencimentoContaAVista) {
		this.dataLimiteVencimentoContaAVista = dataLimiteVencimentoContaAVista;
	}

	public Date getDataLimiteVencimentoContaParcelado() {
		return dataLimiteVencimentoContaParcelado;
	}

	public void setDataLimiteVencimentoContaParcelado(
			Date dataLimiteVencimentoContaParcelado) {
		this.dataLimiteVencimentoContaParcelado = dataLimiteVencimentoContaParcelado;
	}

	public BigDecimal getPercentualDescontoMulta() {
		return percentualDescontoMulta;
	}

	public void setPercentualDescontoMulta(BigDecimal percentualDescontoMulta) {
		this.percentualDescontoMulta = percentualDescontoMulta;
	}

	public BigDecimal getPercentualDescontoJuros() {
		return percentualDescontoJuros;
	}

	public void setPercentualDescontoJuros(BigDecimal percentualDescontoJuros) {
		this.percentualDescontoJuros = percentualDescontoJuros;
	}

	public BigDecimal getPercentualDescontoAtualizacaoMonetaria() {
		return percentualDescontoAtualizacaoMonetaria;
	}

	public void setPercentualDescontoAtualizacaoMonetaria(
			BigDecimal percentualDescontoAtualizacaoMonetaria) {
		this.percentualDescontoAtualizacaoMonetaria = percentualDescontoAtualizacaoMonetaria;
	}

	public BigDecimal getPercentualDescontoPagamentoAVistaMulta() {
		return percentualDescontoPagamentoAVistaMulta;
	}

	public void setPercentualDescontoPagamentoAVistaMulta(
			BigDecimal percentualDescontoPagamentoAVistaMulta) {
		this.percentualDescontoPagamentoAVistaMulta = percentualDescontoPagamentoAVistaMulta;
	}

	public BigDecimal getPercentualDescontoPagamentoAVistaJuros() {
		return percentualDescontoPagamentoAVistaJuros;
	}

	public void setPercentualDescontoPagamentoAVistaJuros(
			BigDecimal percentualDescontoPagamentoAVistaJuros) {
		this.percentualDescontoPagamentoAVistaJuros = percentualDescontoPagamentoAVistaJuros;
	}

	public BigDecimal getPercentualDescontoPagamentoAVistaAtuMonetaria() {
		return percentualDescontoPagamentoAVistaAtuMonetaria;
	}

	public void setPercentualDescontoPagamentoAVistaAtuMonetaria(
			BigDecimal percentualDescontoPagamentoAVistaAtuMonetaria) {
		this.percentualDescontoPagamentoAVistaAtuMonetaria = percentualDescontoPagamentoAVistaAtuMonetaria;
	}

	public Integer getAnoMesReferenciaLimiteContaPagamentoAVista() {
		return anoMesReferenciaLimiteContaPagamentoAVista;
	}

	public void setAnoMesReferenciaLimiteContaPagamentoAVista(
			Integer anoMesReferenciaLimiteContaPagamentoAVista) {
		this.anoMesReferenciaLimiteContaPagamentoAVista = anoMesReferenciaLimiteContaPagamentoAVista;
	}

	public Integer getAnoMesReferenciaLimiteContaParcelada() {
		return anoMesReferenciaLimiteContaParcelada;
	}

	public void setAnoMesReferenciaLimiteContaParcelada(
			Integer anoMesReferenciaLimiteContaParcelada) {
		this.anoMesReferenciaLimiteContaParcelada = anoMesReferenciaLimiteContaParcelada;
	}

	public BigDecimal getPercentualValorContaConsumoMedioPrestacao() {
		return percentualValorContaConsumoMedioPrestacao;
	}

	public void setPercentualValorContaConsumoMedioPrestacao(
			BigDecimal percentualValorContaConsumoMedioPrestacao) {
		this.percentualValorContaConsumoMedioPrestacao = percentualValorContaConsumoMedioPrestacao;
	}
	
}
