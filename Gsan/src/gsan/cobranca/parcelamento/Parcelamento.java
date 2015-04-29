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

import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.funcionario.Funcionario;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cobranca.CobrancaForma;
import gsan.cobranca.ResolucaoDiretoria;
import gsan.interceptor.ObjetoTransacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Parcelamento extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Vari�veis para os c�lculos de Parcelamento do [UC0214]
	 */
	public final static int CASAS_DECIMAIS = 2;
	public final static int TIPO_ARREDONDAMENTO = BigDecimal.ROUND_HALF_DOWN;
	
	
	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date parcelamento;

    /** nullable persistent field */
    private Integer anoMesReferenciaFaturamento;

    /** nullable persistent field */
    private BigDecimal valorConta;

    /** nullable persistent field */
    private BigDecimal valorServicosACobrar;

    /** nullable persistent field */
    private BigDecimal valorAtualizacaoMonetaria;

    /** nullable persistent field */
    private BigDecimal valorJurosMora;

    /** nullable persistent field */
    private BigDecimal valorMulta;

    /** nullable persistent field */
    private BigDecimal valorDebitoAtualizado;

    /** nullable persistent field */
    private BigDecimal valorDescontoAcrescimos;

    /** nullable persistent field */
    private BigDecimal valorEntrada;

    /** nullable persistent field */
    private BigDecimal valorJurosParcelamento;

    /** nullable persistent field */
    private Short numeroPrestacoes;

    /** nullable persistent field */
    private BigDecimal valorPrestacao;

    /** nullable persistent field */
    private BigDecimal valorDescontoAntiguidade;

    /** nullable persistent field */
    private Short indicadorDebitoACobrar;
    
    /** nullable persistent field */
    private BigDecimal valorDescontoInatividade;

    /** nullable persistent field */
    private BigDecimal percentualDescontoAcrescimos;

    /** nullable persistent field */
    private Short indicadorAcrescimosImpontualdade;

    /** nullable persistent field */
    private BigDecimal valorGuiaPapagamento;

    /** nullable persistent field */
    private BigDecimal percentualDescontoAntiguidade;

    /** nullable persistent field */
    private BigDecimal percentualDescontoInatividade;

    /** nullable persistent field */
    private Integer codigoSetorComercial;

    /** nullable persistent field */
    private Integer numeroQuadra;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private BigDecimal valorCreditoARealizar;

    /** nullable persistent field */
    private BigDecimal valorParcelamentosACobrar;

    /** nullable persistent field */
    private Short indicadorRestabelecimento;

    /** nullable persistent field */
    private Short indicadorContasRevisao;

    /** nullable persistent field */
    private Short indicadorGuiasPagamento;

    /** nullable persistent field */
    private Short indicadorCreditoARealizar;

    /** nullable persistent field */
    private BigDecimal taxaJuros;

    private BigDecimal valorDescontoSancao;
    
    private Short numeroParcelasPagasConsecutivas;
    
    private BigDecimal valorDescontoDebitoTotal;
    
    /** persistent field */
    private gsan.cobranca.parcelamento.ParcelamentoTipo parcelamentoTipo;

    /** persistent field */
    private gsan.cobranca.parcelamento.ParcelamentoSituacao parcelamentoSituacao;

    /** persistent field */
    private RegistroAtendimento registroAtendimento;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** persistent field */
    private gsan.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private CobrancaForma cobrancaForma;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private ParcelamentoMotivoDesfazer parcelamentoMotivoDesfazer;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** persistent field */
    private Funcionario funcionario;

    private Short indicadorConfirmacaoParcelamento;
    
    private Cliente cliente;

    private Usuario usuario;
    
    private Usuario usuarioDesfez;
    
    private ResolucaoDiretoria resolucaoDiretoria;
    
    /** nullable persistent field */
    private BigDecimal valorDescontoTarifaSocial;
    
    /** nullable persistent field */
    private BigDecimal valorDescontoMulta;
	
    /** nullable persistent field */
    private BigDecimal valorDescontoJuros;
	
    /** nullable persistent field */
    private BigDecimal valorDescontoAtualizacaoMonetaria;
    
    /** nullable persistent field */
    private BigDecimal valorDescontoTipoDebito;

    //criado para exibi��o na tela de consultar parcelamento
    private BigDecimal valorEntradaComDesconto;
    
    private BigDecimal valorDescontoEntrada;
    
    private String textoClausula2;
    private String paragrafoSegundo;
    
	/** full constructor */
    public Parcelamento(Date parcelamento, Integer anoMesReferenciaFaturamento, BigDecimal valorConta, BigDecimal valorServicosACobrar, BigDecimal valorAtualizacaoMonetaria, BigDecimal valorJurosMora, BigDecimal valorMulta, BigDecimal valorDebitoAtualizado, BigDecimal valorDescontoAcrescimos, BigDecimal valorEntrada, BigDecimal valorJurosParcelamento, Short numeroPrestacoes, BigDecimal valorPrestacao, BigDecimal valorDescontoAntiguidade, Short indicadorDebitoACobrar, BigDecimal valorDescontoInatividade, BigDecimal percentualDescontoAcrescimos, Short indicadorAcrescimosImpontualdade, BigDecimal valorGuiaPapagamento, BigDecimal percentualDescontoAntiguidade, BigDecimal percentualDescontoInatividade, Integer codigoSetorComercial, Integer numeroQuadra, Date ultimaAlteracao, BigDecimal valorCreditoARealizar, BigDecimal valorParcelamentosACobrar, Short indicadorRestabelecimento, Short indicadorContasRevisao, Short indicadorGuiasPagamento, Short indicadorCreditoARealizar, BigDecimal taxaJuros, gsan.cobranca.parcelamento.ParcelamentoTipo parcelamentoTipo, gsan.cobranca.parcelamento.ParcelamentoSituacao parcelamentoSituacao, RegistroAtendimento registroAtendimento, Imovel imovel, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, gsan.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil, ImovelPerfil imovelPerfil, CobrancaForma cobrancaForma, Quadra quadra, Localidade localidade, ParcelamentoMotivoDesfazer parcelamentoMotivoDesfazer, LigacaoAguaSituacao ligacaoAguaSituacao, Funcionario funcionario,Short indicadorConfirmacaoParcelamento,Cliente cliente,Usuario usuario,ResolucaoDiretoria resolucaoDiretoria,BigDecimal valorDescontoSancao,String textoClausula2, String paragrafoSegundo) {
        this.parcelamento = parcelamento;
        this.anoMesReferenciaFaturamento = anoMesReferenciaFaturamento;
        this.valorConta = valorConta;
        this.valorServicosACobrar = valorServicosACobrar;
        this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
        this.valorJurosMora = valorJurosMora;
        this.valorMulta = valorMulta;
        this.valorDebitoAtualizado = valorDebitoAtualizado;
        this.valorDescontoAcrescimos = valorDescontoAcrescimos;
        this.valorEntrada = valorEntrada;
        this.valorJurosParcelamento = valorJurosParcelamento;
        this.numeroPrestacoes = numeroPrestacoes;
        this.valorPrestacao = valorPrestacao;
        this.valorDescontoAntiguidade = valorDescontoAntiguidade;
        this.indicadorDebitoACobrar = indicadorDebitoACobrar;
        this.valorDescontoInatividade = valorDescontoInatividade;
        this.percentualDescontoAcrescimos = percentualDescontoAcrescimos;
        this.indicadorAcrescimosImpontualdade = indicadorAcrescimosImpontualdade;
        this.valorGuiaPapagamento = valorGuiaPapagamento;
        this.percentualDescontoAntiguidade = percentualDescontoAntiguidade;
        this.percentualDescontoInatividade = percentualDescontoInatividade;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.ultimaAlteracao = ultimaAlteracao;
        this.valorCreditoARealizar = valorCreditoARealizar;
        this.valorParcelamentosACobrar = valorParcelamentosACobrar;
        this.indicadorRestabelecimento = indicadorRestabelecimento;
        this.indicadorContasRevisao = indicadorContasRevisao;
        this.indicadorGuiasPagamento = indicadorGuiasPagamento;
        this.indicadorCreditoARealizar = indicadorCreditoARealizar;
        this.taxaJuros = taxaJuros;
        this.parcelamentoTipo = parcelamentoTipo;
        this.parcelamentoSituacao = parcelamentoSituacao;
        this.registroAtendimento = registroAtendimento;
        this.imovel = imovel;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.parcelamentoPerfil = parcelamentoPerfil;
        this.imovelPerfil = imovelPerfil;
        this.cobrancaForma = cobrancaForma;
        this.quadra = quadra;
        this.localidade = localidade;
        this.parcelamentoMotivoDesfazer = parcelamentoMotivoDesfazer;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.funcionario = funcionario;
        this.indicadorConfirmacaoParcelamento = indicadorConfirmacaoParcelamento;
        this.cliente = cliente;
        this.usuario = usuario;
        this.resolucaoDiretoria = resolucaoDiretoria;
        this.valorDescontoSancao = valorDescontoSancao;
        this.textoClausula2 = textoClausula2;
        this.paragrafoSegundo = paragrafoSegundo;
    }

    /** default constructor */
    public Parcelamento() {
    }

    /** minimal constructor */
    public Parcelamento(gsan.cobranca.parcelamento.ParcelamentoTipo parcelamentoTipo, gsan.cobranca.parcelamento.ParcelamentoSituacao parcelamentoSituacao, RegistroAtendimento registroAtendimento, Imovel imovel, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, gsan.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil, ImovelPerfil imovelPerfil, CobrancaForma cobrancaForma, Quadra quadra, Localidade localidade, ParcelamentoMotivoDesfazer parcelamentoMotivoDesfazer, LigacaoAguaSituacao ligacaoAguaSituacao, Funcionario funcionario,Short indicadorConfirmacaoParcelamento) {
        this.parcelamentoTipo = parcelamentoTipo;
        this.parcelamentoSituacao = parcelamentoSituacao;
        this.registroAtendimento = registroAtendimento;
        this.imovel = imovel;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.parcelamentoPerfil = parcelamentoPerfil;
        this.imovelPerfil = imovelPerfil;
        this.cobrancaForma = cobrancaForma;
        this.quadra = quadra;
        this.localidade = localidade;
        this.parcelamentoMotivoDesfazer = parcelamentoMotivoDesfazer;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.indicadorConfirmacaoParcelamento = indicadorConfirmacaoParcelamento;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getParcelamento() {
        return this.parcelamento;
    }

    public void setParcelamento(Date parcelamento) {
        this.parcelamento = parcelamento;
    }

    public Integer getAnoMesReferenciaFaturamento() {
        return this.anoMesReferenciaFaturamento;
    }

    public void setAnoMesReferenciaFaturamento(Integer anoMesReferenciaFaturamento) {
        this.anoMesReferenciaFaturamento = anoMesReferenciaFaturamento;
    }

    public BigDecimal getValorConta() {
        return this.valorConta;
    }

    public void setValorConta(BigDecimal valorConta) {
        this.valorConta = valorConta;
    }

    public BigDecimal getValorServicosACobrar() {
        return this.valorServicosACobrar;
    }

    public void setValorServicosACobrar(BigDecimal valorServicosACobrar) {
        this.valorServicosACobrar = valorServicosACobrar;
    }

    public BigDecimal getValorAtualizacaoMonetaria() {
        return this.valorAtualizacaoMonetaria;
    }

    public void setValorAtualizacaoMonetaria(BigDecimal valorAtualizacaoMonetaria) {
        this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
    }

    public BigDecimal getValorJurosMora() {
        return this.valorJurosMora;
    }

    public void setValorJurosMora(BigDecimal valorJurosMora) {
        this.valorJurosMora = valorJurosMora;
    }

    public BigDecimal getValorMulta() {
        return this.valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public BigDecimal getValorDebitoAtualizado() {
        return this.valorDebitoAtualizado;
    }

    public void setValorDebitoAtualizado(BigDecimal valorDebitoAtualizado) {
        this.valorDebitoAtualizado = valorDebitoAtualizado;
    }

    public BigDecimal getValorDescontoAcrescimos() {
        return this.valorDescontoAcrescimos;
    }

    public void setValorDescontoAcrescimos(BigDecimal valorDescontoAcrescimos) {
        this.valorDescontoAcrescimos = valorDescontoAcrescimos;
    }

    public BigDecimal getValorEntrada() {
        return this.valorEntrada;
    }

    public void setValorEntrada(BigDecimal valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public BigDecimal getValorJurosParcelamento() {
        return this.valorJurosParcelamento;
    }

    public void setValorJurosParcelamento(BigDecimal valorJurosParcelamento) {
        this.valorJurosParcelamento = valorJurosParcelamento;
    }

    public Short getNumeroPrestacoes() {
        return this.numeroPrestacoes;
    }

    public void setNumeroPrestacoes(Short numeroPrestacoes) {
        this.numeroPrestacoes = numeroPrestacoes;
    }

    public BigDecimal getValorPrestacao() {
        return this.valorPrestacao;
    }

    public void setValorPrestacao(BigDecimal valorPrestacao) {
        this.valorPrestacao = valorPrestacao;
    }

    public BigDecimal getValorDescontoAntiguidade() {
        return this.valorDescontoAntiguidade;
    }

    public void setValorDescontoAntiguidade(BigDecimal valorDescontoAntiguidade) {
        this.valorDescontoAntiguidade = valorDescontoAntiguidade;
    }

    public Short getIndicadorDebitoACobrar() {
        return this.indicadorDebitoACobrar;
    }

    public void setIndicadorDebitoACobrar(Short indicadorDebitoACobrar) {
        this.indicadorDebitoACobrar = indicadorDebitoACobrar;
    }

    public BigDecimal getValorDescontoInatividade() {
        return this.valorDescontoInatividade;
    }

    public void setValorDescontoInatividade(BigDecimal valorDescontoInatividade) {
        this.valorDescontoInatividade = valorDescontoInatividade;
    }

    public BigDecimal getPercentualDescontoAcrescimos() {
    	return this.percentualDescontoAcrescimos;
    }

    public void setPercentualDescontoAcrescimos(BigDecimal percentualDescontoAcrescimos) {
        this.percentualDescontoAcrescimos = percentualDescontoAcrescimos;
    }

    public Short getIndicadorAcrescimosImpontualdade() {
        return this.indicadorAcrescimosImpontualdade;
    }

    public void setIndicadorAcrescimosImpontualdade(Short indicadorAcrescimosImpontualdade) {
        this.indicadorAcrescimosImpontualdade = indicadorAcrescimosImpontualdade;
    }

    public BigDecimal getValorGuiaPapagamento() {
        return this.valorGuiaPapagamento;
    }

    public void setValorGuiaPapagamento(BigDecimal valorGuiaPapagamento) {
        this.valorGuiaPapagamento = valorGuiaPapagamento;
    }

    public BigDecimal getPercentualDescontoAntiguidade() {
        return this.percentualDescontoAntiguidade;
    }

    public void setPercentualDescontoAntiguidade(BigDecimal percentualDescontoAntiguidade) {
        this.percentualDescontoAntiguidade = percentualDescontoAntiguidade;
    }

    public BigDecimal getPercentualDescontoInatividade() {
        return this.percentualDescontoInatividade;
    }

    public void setPercentualDescontoInatividade(BigDecimal percentualDescontoInatividade) {
        this.percentualDescontoInatividade = percentualDescontoInatividade;
    }

    public Integer getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(Integer codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public Integer getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(Integer numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public BigDecimal getValorCreditoARealizar() {
        return this.valorCreditoARealizar;
    }

    public void setValorCreditoARealizar(BigDecimal valorCreditoARealizar) {
        this.valorCreditoARealizar = valorCreditoARealizar;
    }

    public BigDecimal getValorParcelamentosACobrar() {
        return this.valorParcelamentosACobrar;
    }

    public void setValorParcelamentosACobrar(BigDecimal valorParcelamentosACobrar) {
        this.valorParcelamentosACobrar = valorParcelamentosACobrar;
    }

    public Short getIndicadorRestabelecimento() {
        return this.indicadorRestabelecimento;
    }

    public void setIndicadorRestabelecimento(Short indicadorRestabelecimento) {
        this.indicadorRestabelecimento = indicadorRestabelecimento;
    }

    public Short getIndicadorContasRevisao() {
        return this.indicadorContasRevisao;
    }

    public void setIndicadorContasRevisao(Short indicadorContasRevisao) {
        this.indicadorContasRevisao = indicadorContasRevisao;
    }

    public Short getIndicadorGuiasPagamento() {
        return this.indicadorGuiasPagamento;
    }

    public void setIndicadorGuiasPagamento(Short indicadorGuiasPagamento) {
        this.indicadorGuiasPagamento = indicadorGuiasPagamento;
    }

    public Short getIndicadorCreditoARealizar() {
        return this.indicadorCreditoARealizar;
    }

    public void setIndicadorCreditoARealizar(Short indicadorCreditoARealizar) {
        this.indicadorCreditoARealizar = indicadorCreditoARealizar;
    }

    public BigDecimal getTaxaJuros() {
        return this.taxaJuros;
    }

    public void setTaxaJuros(BigDecimal taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public gsan.cobranca.parcelamento.ParcelamentoTipo getParcelamentoTipo() {
        return this.parcelamentoTipo;
    }

    public void setParcelamentoTipo(gsan.cobranca.parcelamento.ParcelamentoTipo parcelamentoTipo) {
        this.parcelamentoTipo = parcelamentoTipo;
    }

    public gsan.cobranca.parcelamento.ParcelamentoSituacao getParcelamentoSituacao() {
        return this.parcelamentoSituacao;
    }

    public void setParcelamentoSituacao(gsan.cobranca.parcelamento.ParcelamentoSituacao parcelamentoSituacao) {
        this.parcelamentoSituacao = parcelamentoSituacao;
    }

    public RegistroAtendimento getRegistroAtendimento() {
        return this.registroAtendimento;
    }

    public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
        this.registroAtendimento = registroAtendimento;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
        return this.ligacaoEsgotoSituacao;
    }

    public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
    }

    public gsan.cobranca.parcelamento.ParcelamentoPerfil getParcelamentoPerfil() {
        return this.parcelamentoPerfil;
    }

    public void setParcelamentoPerfil(gsan.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil) {
        this.parcelamentoPerfil = parcelamentoPerfil;
    }

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
    }

    public CobrancaForma getCobrancaForma() {
        return this.cobrancaForma;
    }

    public void setCobrancaForma(CobrancaForma cobrancaForma) {
        this.cobrancaForma = cobrancaForma;
    }

    public Quadra getQuadra() {
        return this.quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public ParcelamentoMotivoDesfazer getParcelamentoMotivoDesfazer() {
        return this.parcelamentoMotivoDesfazer;
    }

    public void setParcelamentoMotivoDesfazer(ParcelamentoMotivoDesfazer parcelamentoMotivoDesfazer) {
        this.parcelamentoMotivoDesfazer = parcelamentoMotivoDesfazer;
    }

    public LigacaoAguaSituacao getLigacaoAguaSituacao() {
        return this.ligacaoAguaSituacao;
    }

    public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    /* Metodo que calcula o valor do desconto concedido no parcelamento - Fernanda Paiva */
    public BigDecimal getValorDesconto() {

		BigDecimal retorno = new BigDecimal("0.00");
		BigDecimal retornoSoma = new BigDecimal("0.00");
		
		retornoSoma = this.valorDescontoAcrescimos.add(this.valorDescontoAntiguidade);

		retorno = this.valorDescontoInatividade.add(retornoSoma);
		
		if(this.valorDescontoSancao != null){
			retorno = this.valorDescontoSancao.add(retorno);
		}
        
        if(this.valorDescontoTarifaSocial != null){
            retorno = this.valorDescontoTarifaSocial.add(retorno);
        }
        
        if( this.getValorDescontoEntrada() != null && 
        	this.getValorDescontoEntrada().floatValue() != 0 ){
        	retorno = this.valorDescontoEntrada.add(retorno);
        }
        		
		retorno = retorno.setScale(2, BigDecimal.ROUND_HALF_UP);

		return retorno;
	}
    
    /* Metodo que calcula o valor do acrescimo por impontualidade - Fernanda Paiva */
    public BigDecimal getValorAcrescimoImpontualidade() {

		BigDecimal retorno = new BigDecimal("0.00");
		BigDecimal retornoSoma = new BigDecimal("0.00");
		
		retornoSoma = this.valorMulta.add(this.valorJurosMora);

		retorno = this.valorAtualizacaoMonetaria.add(retornoSoma);

		retorno = retorno.setScale(2, BigDecimal.ROUND_HALF_UP);

		return retorno;
	}

    /* Metodo que calcula o valor do negociado - Fernanda Paiva */
    public BigDecimal getValorNegociado() {

		BigDecimal retorno = new BigDecimal("0.00");
		
		retorno = this.valorDebitoAtualizado.subtract(getValorDesconto());

		retorno = retorno.setScale(2, BigDecimal.ROUND_HALF_UP);

		return retorno;
	}

    /* Metodo que calcula o valor do parcelado - Fernanda Paiva */
    public BigDecimal getValorParcelado() {

		BigDecimal retorno = new BigDecimal("0.00");
		BigDecimal retornoSubtracao = new BigDecimal("0.00");
		
		retornoSubtracao = getValorNegociado().subtract( ( this.valorEntradaComDesconto != null && this.getValorEntradaComDesconto().floatValue() != 0 ? this.valorEntradaComDesconto : valorEntrada ) );
		
		retorno = retornoSubtracao.add(this.valorJurosParcelamento);

		retorno = retorno.setScale(2, BigDecimal.ROUND_HALF_UP);

		return retorno;
	}

    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
    
    public Short getIndicadorConfirmacaoParcelamento() {
		return indicadorConfirmacaoParcelamento;
	}

	public void setIndicadorConfirmacaoParcelamento(
			Short indicadorConfirmacaoParcelamento) {
		this.indicadorConfirmacaoParcelamento = indicadorConfirmacaoParcelamento;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ResolucaoDiretoria getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}

	public void setResolucaoDiretoria(ResolucaoDiretoria resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}

	public BigDecimal getValorDescontoSancao() {
		return valorDescontoSancao;
	}

	public void setValorDescontoSancao(BigDecimal valorDescontoSancao) {
		this.valorDescontoSancao = valorDescontoSancao;
	}

	public BigDecimal getValorDescontoTarifaSocial() {
		return valorDescontoTarifaSocial;
	}

	public void setValorDescontoTarifaSocial(BigDecimal valorDescontoTarifaSocial) {
		this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
	}

    public Short getNumeroParcelasPagasConsecutivas() {
        return numeroParcelasPagasConsecutivas;
    }

    public void setNumeroParcelasPagasConsecutivas(Short numeroParcelasPagasConsecutivas) {
        this.numeroParcelasPagasConsecutivas = numeroParcelasPagasConsecutivas;
    }

	public Usuario getUsuarioDesfez() {
		return usuarioDesfez;
	}

	public void setUsuarioDesfez(Usuario usuarioDesfez) {
		this.usuarioDesfez = usuarioDesfez;
	}
	
	public BigDecimal getValorDescontoDebitoTotal() {
		return valorDescontoDebitoTotal;
	}

	public void setValorDescontoDebitoTotal(BigDecimal valorDescontoDebitoTotal) {
		this.valorDescontoDebitoTotal = valorDescontoDebitoTotal;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroParcelamento filtro = new FiltroParcelamento();

		filtro.adicionarParametro(new ParametroSimples(FiltroParcelamento.ID,this.getId()));
		
		return filtro;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
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

	public BigDecimal getValorDescontoTipoDebito() {
		return valorDescontoTipoDebito;
	}

	public void setValorDescontoTipoDebito(BigDecimal valorDescontoTipoDebito) {
		this.valorDescontoTipoDebito = valorDescontoTipoDebito;
	}

	public BigDecimal getValorEntradaComDesconto() {
		return valorEntradaComDesconto;
	}

	public void setValorEntradaComDesconto(BigDecimal valorEntradaComDesconto) {
		this.valorEntradaComDesconto = valorEntradaComDesconto;
	}
	
	public BigDecimal getValorDescontoEntrada() {
		return ( valorDescontoEntrada == null ? new BigDecimal(0) : valorDescontoEntrada);
	}

	public void setValorDescontoEntrada(BigDecimal valorDescontoEntrada) {
		this.valorDescontoEntrada = valorDescontoEntrada;
	}
	
}
