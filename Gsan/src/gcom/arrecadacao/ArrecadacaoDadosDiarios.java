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
package gcom.arrecadacao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.DocumentoTipo;
import gcom.micromedicao.Rota;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ArrecadacaoDadosDiarios implements Serializable {

    /**
	 * @since 16/05/2007
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer anoMesReferenciaArrecadacao;

    /** persistent field */
    private Integer codigoSetorComercial;

    /** persistent field */
    private Integer numeroQuadra;

    /** nullable persistent field */
    private Short indicadorHidrometro;

    /** nullable persistent field */
    private Date dataPagamento;

    /** nullable persistent field */
    private Integer quantidadePagamentos = 0;

    /** nullable persistent field */
    private BigDecimal valorPagamentos = new BigDecimal("0.0");

    /** persistent field */
    private Rota rota;

    /** persistent field */
    private Arrecadador arrecadador;

    /** persistent field */
    private SetorComercial setorComercial;

    /** persistent field */
    private ArrecadacaoForma arrecadacaoForma;

    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** persistent field */
    private DocumentoTipo documentoTipo;

    /** persistent field */
    private EsferaPoder esferaPoder;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** persistent field */
    private Categoria categoria;
    
    /** persistent field */
    private UnidadeNegocio unidadeNegocio;
    
    private Integer quantidadeDocumentos = 0;
    
    private DocumentoTipo documentoTipoAgregador;
    
    private BigDecimal valorUnitarioTarifa = new BigDecimal("0.0");
    
    private Short numeroDiasFloat;
    
    private BigDecimal valorTotalTarifas;
    
    private Date ultimaAlteracao;
    
    public Short getNumeroDiasFloat() {
		return numeroDiasFloat;
	}

	public void setNumeroDiasFloat(Short numeroDiasFloat) {
		this.numeroDiasFloat = numeroDiasFloat;
	}

	public BigDecimal getValorUnitarioTarifa() {
		return valorUnitarioTarifa;
	}

	public void setValorUnitarioTarifa(BigDecimal valorUnitarioTarifa) {		
		this.valorUnitarioTarifa = valorUnitarioTarifa;
	}

	public BigDecimal getValorTotalTarifas() {
		return valorTotalTarifas;
	}

	public void setValorTotalTarifas(BigDecimal valorTotalTarifas) {
		this.valorTotalTarifas = valorTotalTarifas;
	}

	public DocumentoTipo getDocumentoTipoAgregador() {
		return documentoTipoAgregador;
	}

	public void setDocumentoTipoAgregador(DocumentoTipo documentoTipoAgregador) {
		this.documentoTipoAgregador = documentoTipoAgregador;
	}

	public Integer getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	public void setQuantidadeDocumentos(Integer quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	/** full constructor */
    public ArrecadacaoDadosDiarios(Integer id, Integer anoMesReferenciaArrecadacao, Integer codigoSetorComercial, Integer numeroQuadra, Short indicadorHidrometro, Date dataPagamento, Integer quantidadePagamentos, BigDecimal valorPagamentos, Rota rota, Arrecadador arrecadador, SetorComercial setorComercial, ArrecadacaoForma arrecadacaoForma, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, DocumentoTipo documentoTipo, EsferaPoder esferaPoder, ImovelPerfil imovelPerfil, Quadra quadra, GerenciaRegional gerenciaRegional, Localidade localidade, LigacaoAguaSituacao ligacaoAguaSituacao, Categoria categoria, UnidadeNegocio unidadeNegocio) {
        this.id = id;
        this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.indicadorHidrometro = indicadorHidrometro;
        this.dataPagamento = dataPagamento;
        this.quantidadePagamentos = quantidadePagamentos;
        this.valorPagamentos = valorPagamentos;
        this.rota = rota;
        this.arrecadador = arrecadador;
        this.setorComercial = setorComercial;
        this.arrecadacaoForma = arrecadacaoForma;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.documentoTipo = documentoTipo;
        this.esferaPoder = esferaPoder;
        this.imovelPerfil = imovelPerfil;
        this.quadra = quadra;
        this.gerenciaRegional = gerenciaRegional;
        this.localidade = localidade;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.categoria = categoria;
        this.unidadeNegocio = unidadeNegocio;
    }

    /** default constructor */
    public ArrecadacaoDadosDiarios() {
    }

    /** minimal constructor */
    public ArrecadacaoDadosDiarios(Integer id, Integer codigoSetorComercial, Integer numeroQuadra, Rota rota, Arrecadador arrecadador, SetorComercial setorComercial, ArrecadacaoForma arrecadacaoForma, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, DocumentoTipo documentoTipo, EsferaPoder esferaPoder, ImovelPerfil imovelPerfil, Quadra quadra, GerenciaRegional gerenciaRegional, Localidade localidade, LigacaoAguaSituacao ligacaoAguaSituacao, Categoria categoria, UnidadeNegocio unidadeNegocio) {
        this.id = id;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.rota = rota;
        this.arrecadador = arrecadador;
        this.setorComercial = setorComercial;
        this.arrecadacaoForma = arrecadacaoForma;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.documentoTipo = documentoTipo;
        this.esferaPoder = esferaPoder;
        this.imovelPerfil = imovelPerfil;
        this.quadra = quadra;
        this.gerenciaRegional = gerenciaRegional;
        this.localidade = localidade;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.categoria = categoria;
        this.unidadeNegocio = unidadeNegocio;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferenciaArrecadacao() {
        return this.anoMesReferenciaArrecadacao;
    }

    public void setAnoMesReferenciaArrecadacao(Integer anoMesReferenciaArrecadacao) {
        this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
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

    public Short getIndicadorHidrometro() {
        return this.indicadorHidrometro;
    }

    public void setIndicadorHidrometro(Short indicadorHidrometro) {
        this.indicadorHidrometro = indicadorHidrometro;
    }

    public Date getDataPagamento() {
        return this.dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Integer getQuantidadePagamentos() {
        return this.quantidadePagamentos;
    }

    public void setQuantidadePagamentos(Integer quantidadePagamentos) {
        this.quantidadePagamentos = quantidadePagamentos;
    }

    public BigDecimal getValorPagamentos() {
        return this.valorPagamentos;
    }

    public void setValorPagamentos(BigDecimal valorPagamentos) {
        this.valorPagamentos = valorPagamentos;
    }

    public Rota getRota() {
        return this.rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public Arrecadador getArrecadador() {
        return this.arrecadador;
    }

    public void setArrecadador(Arrecadador arrecadador) {
        this.arrecadador = arrecadador;
    }

    public SetorComercial getSetorComercial() {
        return this.setorComercial;
    }

    public void setSetorComercial(SetorComercial setorComercial) {
        this.setorComercial = setorComercial;
    }

    public ArrecadacaoForma getArrecadacaoForma() {
        return this.arrecadacaoForma;
    }

    public void setArrecadacaoForma(ArrecadacaoForma arrecadacaoForma) {
        this.arrecadacaoForma = arrecadacaoForma;
    }

    public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
        return this.ligacaoEsgotoSituacao;
    }

    public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
    }

    public DocumentoTipo getDocumentoTipo() {
        return this.documentoTipo;
    }

    public void setDocumentoTipo(DocumentoTipo documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public EsferaPoder getEsferaPoder() {
        return this.esferaPoder;
    }

    public void setEsferaPoder(EsferaPoder esferaPoder) {
        this.esferaPoder = esferaPoder;
    }

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
    }

    public Quadra getQuadra() {
        return this.quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public GerenciaRegional getGerenciaRegional() {
        return this.gerenciaRegional;
    }

    public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
        this.gerenciaRegional = gerenciaRegional;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public LigacaoAguaSituacao getLigacaoAguaSituacao() {
        return this.ligacaoAguaSituacao;
    }

    public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}
