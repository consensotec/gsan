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
package gcom.gerencial.faturamento;

import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.cobranca.GDocumentoTipo;
import gcom.gerencial.faturamento.credito.GCreditoOrigem;
import gcom.gerencial.financeiro.GFinanciamentoTipo;
import gcom.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import gcom.gerencial.micromedicao.GRota;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoRefaturamentoAguaEsgoto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private int codigoSetorcomercial;

    /** persistent field */
    private int numeroQuadra;

    /** persistent field */
    private int quantidadeContasRetificadas;

    /** persistent field */
    private BigDecimal valorCanceladoAgua;

    /** persistent field */
    private int quantidadeContasCanceladas;

    /** persistent field */
    private int volumeCanceladoAgua;

    /** persistent field */
    private int quantidadeContasIncluidas;

    /** persistent field */
    private BigDecimal valorCanceladoEsgoto;

    /** persistent field */
    private int volumeCanceladoEsgoto;

    /** persistent field */
    private BigDecimal valorIncluidoAgua;

    /** persistent field */
    private int volumeIncluidoAgua;

    /** persistent field */
    private BigDecimal valorIncluidoEsgoto;

    /** persistent field */
    private int volumeIncluidoEsgoto;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GLocalidade gerLocalidadeElo;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private GQuadra gerQuadra;

    /** persistent field */
    private GRota gerRota;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

    /** persistent field */
    private Set unResumoRefaturamentoOutros;

    /** persistent field */
    private Set unResumoRefaturamentoCreditos;
    
    /** persistent field */
    private BigDecimal valorDocumentosFaturadosCredito;
    
    /** persistent field */
    private Integer quantidadeDocumentosFaturadosCredito;
    
    /** persistent field */
    private BigDecimal valorDocumentosFaturadosOutros;
    
    /** persistent field */
    private Integer quantidadeDocumentosFaturadosOutros;
    
    /** persistent field */
    private GLancamentoItemContabil gerLancamentoItemContabilCredito;
    
    /** persistent field */
    private GLancamentoItemContabil gerLancamentoItemContabilOutros;
    
    /** persistent field */
    private GFinanciamentoTipo gerFinanciamentoTipoOutros;
    
    /** persistent field */
    private GCreditoOrigem gerCreditoOrigem;
    
    /** persistent field */
    private GDocumentoTipo gerDocumentoTipoOutros;

	public UnResumoRefaturamentoAguaEsgoto(int anoMesReferencia,
			GGerenciaRegional gGerenciaRegional, GUnidadeNegocio gUnidadeNegocio,
			GLocalidade gLocalidade, GSetorComercial gSetorComercial, GQuadra gQuadra, 
			int codigoSetorcomercial, int numeroQuadra, GImovelPerfil gImovelPerfil,
			GEsferaPoder gEsferaPoder, GClienteTipo gClienteTipo, GLigacaoAguaSituacao gLigacaoAguaSituacao,
			GLigacaoEsgotoSituacao gLigacaoEsgotoSituacao, GCategoria gCategoria, GSubcategoria gSubcategoria,
			GLigacaoAguaPerfil gLigacaoAguaPerfil, GLigacaoEsgotoPerfil gLigacaoEsgotoPerfil,
			int quantidadeContasRetificadas, int quantidadeContasCanceladas, BigDecimal valorCanceladoAgua, 
			BigDecimal valorCanceladoEsgoto, int volumeCanceladoAgua, int volumeCanceladoEsgoto, 
			int quantidadeContasIncluidas, BigDecimal valorIncluidoAgua, BigDecimal valorIncluidoEsgoto, 
			int volumeIncluidoAgua, int volumeIncluidoEsgoto, 
			Date ultimaAlteracao,
			GLocalidade gerLocalidadeElo, GRota gerRota){ 
		this.anoMesReferencia = anoMesReferencia;
		this.gerGerenciaRegional = gGerenciaRegional;
		this.gerUnidadeNegocio = gUnidadeNegocio;
		this.gerLocalidade = gLocalidade;
		this.gerSetorComercial = gSetorComercial;
		this.gerQuadra = gQuadra;
		this.codigoSetorcomercial = codigoSetorcomercial;
		this.numeroQuadra = numeroQuadra;
		this.gerImovelPerfil = gImovelPerfil;
		this.gerEsferaPoder = gEsferaPoder;
		this.gerClienteTipo = gClienteTipo;
		this.gerLigacaoAguaSituacao = gLigacaoAguaSituacao;
		this.gerLigacaoEsgotoSituacao = gLigacaoEsgotoSituacao;
		this.gerCategoria = gCategoria;
		this.gerSubcategoria = gSubcategoria;
		this.gerLigacaoAguaPerfil = gLigacaoAguaPerfil;
		this.gerLigacaoEsgotoPerfil = gLigacaoEsgotoPerfil;
	    this.quantidadeContasRetificadas = quantidadeContasRetificadas;
	    this.quantidadeContasCanceladas = quantidadeContasCanceladas;
	    this.valorCanceladoAgua = valorCanceladoAgua;
	    this.valorCanceladoEsgoto = valorCanceladoEsgoto;
	    this.volumeCanceladoAgua = volumeCanceladoAgua;
	    this.volumeCanceladoEsgoto = volumeCanceladoEsgoto;
	    this.quantidadeContasIncluidas = quantidadeContasIncluidas;
	    this.valorIncluidoAgua = valorIncluidoAgua;
	    this.valorIncluidoEsgoto = valorIncluidoEsgoto;
	    this.volumeIncluidoAgua = volumeIncluidoAgua;
	    this.volumeIncluidoEsgoto = volumeIncluidoEsgoto;
		this.ultimaAlteracao = ultimaAlteracao;
		this.gerLocalidadeElo = gerLocalidadeElo;
		this.gerRota = gerRota;
	}

    /** full constructor */
    public UnResumoRefaturamentoAguaEsgoto(
    		Integer id, 
    		int anoMesReferencia, 
    		int codigoSetorcomercial, 
    		int numeroQuadra, 
    		int quantidadeContasRetificadas, 
    		BigDecimal valorCanceladoAgua, 
    		int quantidadeContasCanceladas, 
    		int volumeCanceladoAgua, 
    		int quantidadeContasIncluidas, 
    		BigDecimal valorCanceladoEsgoto, 
    		int volumeCanceladoEsgoto, 
    		BigDecimal valorIncluidoAgua, 
    		int volumeIncluidoAgua, 
    		BigDecimal valorIncluidoEsgoto, 
    		int volumeIncluidoEsgoto, 
    		Date ultimaAlteracao, 
    		GGerenciaRegional gerGerenciaRegional, 
    		GSubcategoria gerSubcategoria, 
    		GSetorComercial gerSetorComercial, 
    		GLigacaoAguaPerfil gerLigacaoAguaPerfil, 
    		GClienteTipo gerClienteTipo, 
    		GEsferaPoder gerEsferaPoder, 
    		GUnidadeNegocio gerUnidadeNegocio, 
    		GLigacaoAguaSituacao gerLigacaoAguaSituacao, 
    		GLocalidade gerLocalidade, 
    		GLocalidade gerLocalidadeElo, 
    		GCategoria gerCategoria, 
    		GImovelPerfil gerImovelPerfil, 
    		GQuadra gerQuadra, 
    		GRota gerRota, 
    		GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, 
    		GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil, 
    		Set unResumoRefaturamentoOutros, 
    		Set unResumoRefaturamentoCreditos) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorcomercial = codigoSetorcomercial;
        this.numeroQuadra = numeroQuadra;
        this.quantidadeContasRetificadas = quantidadeContasRetificadas;
        this.valorCanceladoAgua = valorCanceladoAgua;
        this.quantidadeContasCanceladas = quantidadeContasCanceladas;
        this.volumeCanceladoAgua = volumeCanceladoAgua;
        this.quantidadeContasIncluidas = quantidadeContasIncluidas;
        this.valorCanceladoEsgoto = valorCanceladoEsgoto;
        this.volumeCanceladoEsgoto = volumeCanceladoEsgoto;
        this.valorIncluidoAgua = valorIncluidoAgua;
        this.volumeIncluidoAgua = volumeIncluidoAgua;
        this.valorIncluidoEsgoto = valorIncluidoEsgoto;
        this.volumeIncluidoEsgoto = volumeIncluidoEsgoto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSubcategoria = gerSubcategoria;
        this.gerSetorComercial = gerSetorComercial;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerClienteTipo = gerClienteTipo;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
        this.gerLocalidade = gerLocalidade;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerCategoria = gerCategoria;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerQuadra = gerQuadra;
        this.gerRota = gerRota;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
        this.unResumoRefaturamentoOutros = unResumoRefaturamentoOutros;
        this.unResumoRefaturamentoCreditos = unResumoRefaturamentoCreditos;
    }

    /** default constructor */
    public UnResumoRefaturamentoAguaEsgoto() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(int anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public int getCodigoSetorcomercial() {
        return this.codigoSetorcomercial;
    }

    public void setCodigoSetorcomercial(int codigoSetorcomercial) {
        this.codigoSetorcomercial = codigoSetorcomercial;
    }

    public int getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(int numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public int getQuantidadeContasRetificadas() {
        return this.quantidadeContasRetificadas;
    }

    public void setQuantidadeContasRetificadas(int quantidadeContasRetificadas) {
        this.quantidadeContasRetificadas = quantidadeContasRetificadas;
    }

    public BigDecimal getValorCanceladoAgua() {
        return this.valorCanceladoAgua;
    }

    public void setValorCanceladoAgua(BigDecimal valorCanceladoAgua) {
        this.valorCanceladoAgua = valorCanceladoAgua;
    }

    public int getQuantidadeContasCanceladas() {
        return this.quantidadeContasCanceladas;
    }

    public void setQuantidadeContasCanceladas(int quantidadeContasCanceladas) {
        this.quantidadeContasCanceladas = quantidadeContasCanceladas;
    }

    public int getVolumeCanceladoAgua() {
        return this.volumeCanceladoAgua;
    }

    public void setVolumeCanceladoAgua(int volumeCanceladoAgua) {
        this.volumeCanceladoAgua = volumeCanceladoAgua;
    }

    public int getQuantidadeContasIncluidas() {
        return this.quantidadeContasIncluidas;
    }

    public void setQuantidadeContasIncluidas(int quantidadeContasIncluidas) {
        this.quantidadeContasIncluidas = quantidadeContasIncluidas;
    }

    public BigDecimal getValorCanceladoEsgoto() {
        return this.valorCanceladoEsgoto;
    }

    public void setValorCanceladoEsgoto(BigDecimal valorCanceladoEsgoto) {
        this.valorCanceladoEsgoto = valorCanceladoEsgoto;
    }

    public int getVolumeCanceladoEsgoto() {
        return this.volumeCanceladoEsgoto;
    }

    public void setVolumeCanceladoEsgoto(int volumeCanceladoEsgoto) {
        this.volumeCanceladoEsgoto = volumeCanceladoEsgoto;
    }

    public BigDecimal getValorIncluidoAgua() {
        return this.valorIncluidoAgua;
    }

    public void setValorIncluidoAgua(BigDecimal valorIncluidoAgua) {
        this.valorIncluidoAgua = valorIncluidoAgua;
    }

    public int getVolumeIncluidoAgua() {
        return this.volumeIncluidoAgua;
    }

    public void setVolumeIncluidoAgua(int volumeIncluidoAgua) {
        this.volumeIncluidoAgua = volumeIncluidoAgua;
    }

    public BigDecimal getValorIncluidoEsgoto() {
        return this.valorIncluidoEsgoto;
    }

    public void setValorIncluidoEsgoto(BigDecimal valorIncluidoEsgoto) {
        this.valorIncluidoEsgoto = valorIncluidoEsgoto;
    }

    public int getVolumeIncluidoEsgoto() {
        return this.volumeIncluidoEsgoto;
    }

    public void setVolumeIncluidoEsgoto(int volumeIncluidoEsgoto) {
        this.volumeIncluidoEsgoto = volumeIncluidoEsgoto;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GGerenciaRegional getGerGerenciaRegional() {
        return this.gerGerenciaRegional;
    }

    public void setGerGerenciaRegional(GGerenciaRegional gerGerenciaRegional) {
        this.gerGerenciaRegional = gerGerenciaRegional;
    }

    public GSubcategoria getGerSubcategoria() {
        return this.gerSubcategoria;
    }

    public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
        this.gerSubcategoria = gerSubcategoria;
    }

    public GSetorComercial getGerSetorComercial() {
        return this.gerSetorComercial;
    }

    public void setGerSetorComercial(GSetorComercial gerSetorComercial) {
        this.gerSetorComercial = gerSetorComercial;
    }

    public GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
        return this.gerLigacaoAguaPerfil;
    }

    public void setGerLigacaoAguaPerfil(GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
    }

    public GClienteTipo getGerClienteTipo() {
        return this.gerClienteTipo;
    }

    public void setGerClienteTipo(GClienteTipo gerClienteTipo) {
        this.gerClienteTipo = gerClienteTipo;
    }

    public GEsferaPoder getGerEsferaPoder() {
        return this.gerEsferaPoder;
    }

    public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
        this.gerEsferaPoder = gerEsferaPoder;
    }

    public GUnidadeNegocio getGerUnidadeNegocio() {
        return this.gerUnidadeNegocio;
    }

    public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
        this.gerUnidadeNegocio = gerUnidadeNegocio;
    }

    public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
        return this.gerLigacaoAguaSituacao;
    }

    public void setGerLigacaoAguaSituacao(GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
    }

    public GLocalidade getGerLocalidade() {
        return this.gerLocalidade;
    }

    public void setGerLocalidade(GLocalidade gerLocalidade) {
        this.gerLocalidade = gerLocalidade;
    }

    public GLocalidade getGerLocalidadeElo() {
        return this.gerLocalidadeElo;
    }

    public void setGerLocalidadeElo(GLocalidade gerLocalidadeElo) {
        this.gerLocalidadeElo = gerLocalidadeElo;
    }

    public GCategoria getGerCategoria() {
        return this.gerCategoria;
    }

    public void setGerCategoria(GCategoria gerCategoria) {
        this.gerCategoria = gerCategoria;
    }

    public GImovelPerfil getGerImovelPerfil() {
        return this.gerImovelPerfil;
    }

    public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
        this.gerImovelPerfil = gerImovelPerfil;
    }

    public GQuadra getGerQuadra() {
        return this.gerQuadra;
    }

    public void setGerQuadra(GQuadra gerQuadra) {
        this.gerQuadra = gerQuadra;
    }

    public GRota getGerRota() {
        return this.gerRota;
    }

    public void setGerRota(GRota gerRota) {
        this.gerRota = gerRota;
    }

    public GLigacaoEsgotoSituacao getGerLigacaoEsgotoSituacao() {
        return this.gerLigacaoEsgotoSituacao;
    }

    public void setGerLigacaoEsgotoSituacao(GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
    }

    public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
        return this.gerLigacaoEsgotoPerfil;
    }

    public void setGerLigacaoEsgotoPerfil(GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    public Set getUnResumoRefaturamentoOutros() {
        return this.unResumoRefaturamentoOutros;
    }

    public void setUnResumoRefaturamentoOutros(Set unResumoRefaturamentoOutros) {
        this.unResumoRefaturamentoOutros = unResumoRefaturamentoOutros;
    }

    public Set getUnResumoRefaturamentoCreditos() {
        return this.unResumoRefaturamentoCreditos;
    }

    public void setUnResumoRefaturamentoCreditos(Set unResumoRefaturamentoCreditos) {
        this.unResumoRefaturamentoCreditos = unResumoRefaturamentoCreditos;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public GCreditoOrigem getGerCreditoOrigem() {
		return gerCreditoOrigem;
	}

	public void setGerCreditoOrigem(GCreditoOrigem gerCreditoOrigem) {
		this.gerCreditoOrigem = gerCreditoOrigem;
	}

	public GDocumentoTipo getGerDocumentoTipoOutros() {
		return gerDocumentoTipoOutros;
	}

	public void setGerDocumentoTipoOutros(GDocumentoTipo gerDocumentoTipoOutros) {
		this.gerDocumentoTipoOutros = gerDocumentoTipoOutros;
	}

	public GFinanciamentoTipo getGerFinanciamentoTipoOutros() {
		return gerFinanciamentoTipoOutros;
	}

	public void setGerFinanciamentoTipoOutros(
			GFinanciamentoTipo gerFinanciamentoTipoOutros) {
		this.gerFinanciamentoTipoOutros = gerFinanciamentoTipoOutros;
	}

	public GLancamentoItemContabil getGerLancamentoItemContabilCredito() {
		return gerLancamentoItemContabilCredito;
	}

	public void setGerLancamentoItemContabilCredito(
			GLancamentoItemContabil gerLancamentoItemContabilCredito) {
		this.gerLancamentoItemContabilCredito = gerLancamentoItemContabilCredito;
	}

	public GLancamentoItemContabil getGerLancamentoItemContabilOutros() {
		return gerLancamentoItemContabilOutros;
	}

	public void setGerLancamentoItemContabilOutros(
			GLancamentoItemContabil gerLancamentoItemContabilOutros) {
		this.gerLancamentoItemContabilOutros = gerLancamentoItemContabilOutros;
	}

	public Integer getQuantidadeDocumentosFaturadosCredito() {
		return quantidadeDocumentosFaturadosCredito;
	}

	public void setQuantidadeDocumentosFaturadosCredito(
			Integer quantidadeDocumentosFaturadosCredito) {
		this.quantidadeDocumentosFaturadosCredito = quantidadeDocumentosFaturadosCredito;
	}

	public Integer getQuantidadeDocumentosFaturadosOutros() {
		return quantidadeDocumentosFaturadosOutros;
	}

	public void setQuantidadeDocumentosFaturadosOutros(
			Integer quantidadeDocumentosFaturadosOutros) {
		this.quantidadeDocumentosFaturadosOutros = quantidadeDocumentosFaturadosOutros;
	}

	public BigDecimal getValorDocumentosFaturadosCredito() {
		return valorDocumentosFaturadosCredito;
	}

	public void setValorDocumentosFaturadosCredito(
			BigDecimal valorDocumentosFaturadosCredito) {
		this.valorDocumentosFaturadosCredito = valorDocumentosFaturadosCredito;
	}

	public BigDecimal getValorDocumentosFaturadosOutros() {
		return valorDocumentosFaturadosOutros;
	}

	public void setValorDocumentosFaturadosOutros(
			BigDecimal valorDocumentosFaturadosOutros) {
		this.valorDocumentosFaturadosOutros = valorDocumentosFaturadosOutros;
	}

	
}
