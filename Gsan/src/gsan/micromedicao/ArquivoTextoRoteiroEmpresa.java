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
package gsan.micromedicao;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.localidade.Localidade;
import gsan.faturamento.FaturamentoGrupo;
import gsan.micromedicao.bean.ArquivoTextoRoteiroEmpresaFotoHelper;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ArquivoTextoRoteiroEmpresa implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Integer anoMesReferencia;

    /** persistent field */
    private Integer codigoSetorComercial1;

    /** persistent field */
    private Integer numeroQuadraInicial1;

    /** persistent field */
    private Integer numeroQuadraFinal1;

    /** nullable persistent field */
    private Integer codigoSetorComercial2;

    /** nullable persistent field */
    private Integer numeroQuadraInicial2;

    /** nullable persistent field */
    private Integer numeroQuadraFinal2;

    /** nullable persistent field */
    private Integer codigoSetorComercial3;

    /** nullable persistent field */
    private Integer numeroQuadraInicial3;

    /** nullable persistent field */
    private Integer numeroQuadraFinal3;

    /** persistent field */
    private Integer quantidadeImovel;

    /** persistent field */
    private String nomeArquivo;

    /** persistent field */
    private String codigoLeiturista;

    /** persistent field */
    private String numeroFoneLeiturista;

    /** nullable persistent field */
    private Date tempoEnvioEmpresa;

    /** persistent field */
    private byte[] arquivoTexto;
    
    /** persistent field */
    private byte[] arquivoTextoNaoRecebido;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private FaturamentoGrupo faturamentoGrupo;

    /** persistent field */
    private gsan.micromedicao.Leiturista leiturista;

    /** persistent field */
    private gsan.micromedicao.RoteiroEmpresa roteiroEmpresa;

    /** persistent field */
    private Empresa empresa;
    
    /** persistent field */
    private Long numeroImei;
    
    /** persistent field */
    private Rota rota;

    /** persistent field */
    private SituacaoTransmissaoLeitura situacaoTransmissaoLeitura;
    
    private Integer numeroSequenciaLeitura;
    
    private ServicoTipoCelular servicoTipoCelular;
    
    private String motivoFinalizacao;
    
    private Integer leiturasRealizas;
    
    private Integer qtdArquivosSubdivididos;
    
    private Collection<ArquivoTextoRoteiroEmpresaFotoHelper> colecaoHelper;
    
    public Integer getQtdArquivosSubdivididos() {
		return qtdArquivosSubdivididos;
	}

	public void setQtdArquivosSubdivididos(Integer qtdArquivosSubdivididos) {
		this.qtdArquivosSubdivididos = qtdArquivosSubdivididos;
	}

	/**
     * Description of the Field
     */
    public final static int ARQUIVO_TEXTO_LIBERADO = 2;
    public final static int ARQUIVO_TEXTO_EM_CAMPO = 3;
    public final static int ARQUIVO_TEXTO_FINALIZADO = 4;
    
    public ArquivoTextoRoteiroEmpresa(Integer id, SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
    	this.id = id;
    	this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
    }

    /** full constructor */
    public ArquivoTextoRoteiroEmpresa(Integer id, Integer anoMesReferencia, Integer codigoSetorComercial1, Integer numeroQuadraInicial1, Integer numeroQuadraFinal1, Integer codigoSetorComercial2, Integer numeroQuadraInicial2, Integer numeroQuadraFinal2, Integer codigoSetorComercial3, Integer numeroQuadraInicial3, Integer numeroQuadraFinal3, Integer quantidadeImovel, String nomeArquivo, String codigoLeiturista, String numeroFoneLeiturista, Date tempoEnvioEmpresa, byte[] arquivoTexto, Date ultimaAlteracao, Localidade localidade, FaturamentoGrupo faturamentoGrupo, gsan.micromedicao.Leiturista leiturista, gsan.micromedicao.RoteiroEmpresa roteiroEmpresa, Empresa empresa, SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorComercial1 = codigoSetorComercial1;
        this.numeroQuadraInicial1 = numeroQuadraInicial1;
        this.numeroQuadraFinal1 = numeroQuadraFinal1;
        this.codigoSetorComercial2 = codigoSetorComercial2;
        this.numeroQuadraInicial2 = numeroQuadraInicial2;
        this.numeroQuadraFinal2 = numeroQuadraFinal2;
        this.codigoSetorComercial3 = codigoSetorComercial3;
        this.numeroQuadraInicial3 = numeroQuadraInicial3;
        this.numeroQuadraFinal3 = numeroQuadraFinal3;
        this.quantidadeImovel = quantidadeImovel;
        this.nomeArquivo = nomeArquivo;
        this.codigoLeiturista = codigoLeiturista;
        this.numeroFoneLeiturista = numeroFoneLeiturista;
        this.tempoEnvioEmpresa = tempoEnvioEmpresa;
        this.arquivoTexto = arquivoTexto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.localidade = localidade;
        this.faturamentoGrupo = faturamentoGrupo;
        this.leiturista = leiturista;
        this.roteiroEmpresa = roteiroEmpresa;
        this.empresa = empresa;
        this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
    }

    /** default constructor */
    public ArquivoTextoRoteiroEmpresa() {
    }

    /** minimal constructor */
    public ArquivoTextoRoteiroEmpresa(Integer id, Integer anoMesReferencia, Integer codigoSetorComercial1, Integer numeroQuadraInicial1, Integer numeroQuadraFinal1, Integer quantidadeImovel, String nomeArquivo, String codigoLeiturista, String numeroFoneLeiturista, byte[] arquivoTexto, Date ultimaAlteracao, Localidade localidade, FaturamentoGrupo faturamentoGrupo, gsan.micromedicao.Leiturista leiturista, gsan.micromedicao.RoteiroEmpresa roteiroEmpresa, Empresa empresa, SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.codigoSetorComercial1 = codigoSetorComercial1;
        this.numeroQuadraInicial1 = numeroQuadraInicial1;
        this.numeroQuadraFinal1 = numeroQuadraFinal1;
        this.quantidadeImovel = quantidadeImovel;
        this.nomeArquivo = nomeArquivo;
        this.codigoLeiturista = codigoLeiturista;
        this.numeroFoneLeiturista = numeroFoneLeiturista;
        this.arquivoTexto = arquivoTexto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.localidade = localidade;
        this.faturamentoGrupo = faturamentoGrupo;
        this.leiturista = leiturista;
        this.roteiroEmpresa = roteiroEmpresa;
        this.empresa = empresa;
        this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public Integer getCodigoSetorComercial1() {
        return this.codigoSetorComercial1;
    }

    public void setCodigoSetorComercial1(Integer codigoSetorComercial1) {
        this.codigoSetorComercial1 = codigoSetorComercial1;
    }

    public Integer getNumeroQuadraInicial1() {
        return this.numeroQuadraInicial1;
    }

    public void setNumeroQuadraInicial1(Integer numeroQuadraInicial1) {
        this.numeroQuadraInicial1 = numeroQuadraInicial1;
    }

    public Integer getNumeroQuadraFinal1() {
        return this.numeroQuadraFinal1;
    }

    public void setNumeroQuadraFinal1(Integer numeroQuadraFinal1) {
        this.numeroQuadraFinal1 = numeroQuadraFinal1;
    }

    public Integer getCodigoSetorComercial2() {
        return this.codigoSetorComercial2;
    }

    public void setCodigoSetorComercial2(Integer codigoSetorComercial2) {
        this.codigoSetorComercial2 = codigoSetorComercial2;
    }

    public Integer getNumeroQuadraInicial2() {
        return this.numeroQuadraInicial2;
    }

    public void setNumeroQuadraInicial2(Integer numeroQuadraInicial2) {
        this.numeroQuadraInicial2 = numeroQuadraInicial2;
    }

    public Integer getNumeroQuadraFinal2() {
        return this.numeroQuadraFinal2;
    }

    public void setNumeroQuadraFinal2(Integer numeroQuadraFinal2) {
        this.numeroQuadraFinal2 = numeroQuadraFinal2;
    }

    public Integer getCodigoSetorComercial3() {
        return this.codigoSetorComercial3;
    }

    public void setCodigoSetorComercial3(Integer codigoSetorComercial3) {
        this.codigoSetorComercial3 = codigoSetorComercial3;
    }

    public Integer getNumeroQuadraInicial3() {
        return this.numeroQuadraInicial3;
    }

    public void setNumeroQuadraInicial3(Integer numeroQuadraInicial3) {
        this.numeroQuadraInicial3 = numeroQuadraInicial3;
    }

    public Integer getNumeroQuadraFinal3() {
        return this.numeroQuadraFinal3;
    }

    public void setNumeroQuadraFinal3(Integer numeroQuadraFinal3) {
        this.numeroQuadraFinal3 = numeroQuadraFinal3;
    }

    public Integer getQuantidadeImovel() {
        return this.quantidadeImovel;
    }

    public void setQuantidadeImovel(Integer quantidadeImovel) {
        this.quantidadeImovel = quantidadeImovel;
    }

    public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getCodigoLeiturista() {
        return this.codigoLeiturista;
    }

    public void setCodigoLeiturista(String codigoLeiturista) {
        this.codigoLeiturista = codigoLeiturista;
    }

    public String getNumeroFoneLeiturista() {
        return this.numeroFoneLeiturista;
    }

    public void setNumeroFoneLeiturista(String numeroFoneLeiturista) {
        this.numeroFoneLeiturista = numeroFoneLeiturista;
    }

    public Date getTempoEnvioEmpresa() {
        return this.tempoEnvioEmpresa;
    }

    public void setTempoEnvioEmpresa(Date tempoEnvioEmpresa) {
        this.tempoEnvioEmpresa = tempoEnvioEmpresa;
    }

    public byte[] getArquivoTexto() {
        return this.arquivoTexto;
    }

    public void setArquivoTexto(byte[] arquivoTexto) {
        this.arquivoTexto = arquivoTexto;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }

    public gsan.micromedicao.Leiturista getLeiturista() {
        return this.leiturista;
    }

    public void setLeiturista(gsan.micromedicao.Leiturista leiturista) {
        this.leiturista = leiturista;
    }

    public gsan.micromedicao.RoteiroEmpresa getRoteiroEmpresa() {
        return this.roteiroEmpresa;
    }

    public void setRoteiroEmpresa(gsan.micromedicao.RoteiroEmpresa roteiroEmpresa) {
        this.roteiroEmpresa = roteiroEmpresa;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public SituacaoTransmissaoLeitura getSituacaoTransmissaoLeitura() {
        return this.situacaoTransmissaoLeitura;
    }

    public void setSituacaoTransmissaoLeitura(SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
        this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Long getNumeroImei() {
		return numeroImei;
	}

	public void setNumeroImei(Long numeroImei) {
		this.numeroImei = numeroImei;
	}

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	/**
	 * @return Returns the leituraSequencia.
	 */
	public Integer getNumeroSequenciaLeitura() {
		return numeroSequenciaLeitura;
	}

	/**
	 * @param leituraSequencia The leituraSequencia to set.
	 */
	public void setNumeroSequenciaLeitura(Integer leituraSequencia) {
		this.numeroSequenciaLeitura = leituraSequencia;
	}

	public ServicoTipoCelular getServicoTipoCelular() {
		return servicoTipoCelular;
	}

	public void setServicoTipoCelular(ServicoTipoCelular servicoTipoCelular) {
		this.servicoTipoCelular = servicoTipoCelular;
	}

	public String getMotivoFinalizacao() {
		return motivoFinalizacao;
	}

	public void setMotivoFinalizacao(String motivoFinalizacao) {
		this.motivoFinalizacao = motivoFinalizacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getLeiturasRealizas() {
		return leiturasRealizas;
	}

	public void setLeiturasRealizas(Integer leiturasRealizas) {
		this.leiturasRealizas = leiturasRealizas;
	}

	public byte[] getArquivoTextoNaoRecebido() {
		return arquivoTextoNaoRecebido;
	}

	public void setArquivoTextoNaoRecebido(byte[] arquivoTextoNaoRecebido) {
		this.arquivoTextoNaoRecebido = arquivoTextoNaoRecebido;
	}

	public Collection<ArquivoTextoRoteiroEmpresaFotoHelper> getColecaoHelper() {
		return colecaoHelper;
	}

	public void setColecaoHelper(Collection<ArquivoTextoRoteiroEmpresaFotoHelper> colecaoHelper) {
		this.colecaoHelper = colecaoHelper;
	}
	
}
