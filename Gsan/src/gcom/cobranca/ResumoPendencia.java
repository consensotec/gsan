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
package gcom.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.financeiro.FinanciamentoTipo;
import gcom.micromedicao.Rota;

import java.math.BigDecimal;
import java.util.Date;

public class ResumoPendencia {

	private Integer id; 

    private Date ultimaAlteracao;

    private Integer anoMesReferencia;
    
    private Integer codigoSetorComercial;
    
    private Integer numeroQuadra;
    
    private Short indicadorHidrometro;

    private Integer anoMesReferenciaDocumento;
    
    private Integer quantidadeLigacoes;
    
    private Integer quantidadeDocumentos;

    private BigDecimal valorDebito;

    private GerenciaRegional gerenciaRegional;

    private Localidade localidade;

    private SetorComercial setorComercial;
    
    private Rota rota;
    
    private Quadra quadra;

    private ImovelPerfil imovelPerfil;

    private LigacaoAguaSituacao ligacaoAguaSituacao;

    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    private Categoria categoria;
    
    private EsferaPoder esferaPoder;
    
    private DocumentoTipo documentoTipo;

    private FinanciamentoTipo financiamentoTipo;
    
    private Short indicadorVencido;

    public ResumoPendencia(Integer id, Date ultimaAlteracao, Integer anoMesReferencia, Integer codigoSetorComercial, 
    		Integer numeroQuadra, Short indicadorHidrometro, Integer anoMesReferenciaDocumento, Integer quantidadeLigacoes, 
    		Integer quantidadeDocumentos, BigDecimal valorDebito, GerenciaRegional gerenciaRegional, 
    		Localidade localidade, SetorComercial setorComercial, Rota rota, Quadra quadra, ImovelPerfil imovelPerfil, 
    		LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, 
    		Categoria categoria, EsferaPoder esferaPoder, DocumentoTipo documentoTipo, FinanciamentoTipo financiamentoTipo) {
		this.id = id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.anoMesReferencia = anoMesReferencia;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.indicadorHidrometro = indicadorHidrometro;
		this.anoMesReferenciaDocumento = anoMesReferenciaDocumento;
		this.quantidadeLigacoes = quantidadeLigacoes;
		this.quantidadeDocumentos = quantidadeDocumentos;
		this.valorDebito = valorDebito;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.rota = rota;
		this.quadra = quadra;
		this.imovelPerfil = imovelPerfil;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.categoria = categoria;
		this.esferaPoder = esferaPoder;
		this.documentoTipo = documentoTipo;
		this.financiamentoTipo = financiamentoTipo;
	}

	public ResumoPendencia() {
    	
    }

	/**
	 * @return Retorna o campo anoMesReferencia.
	 */
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	/**
	 * @param anoMesReferencia O anoMesReferencia a ser setado.
	 */
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	/**
	 * @return Retorna o campo anoMesReferenciaDocumento.
	 */
	public Integer getAnoMesReferenciaDocumento() {
		return anoMesReferenciaDocumento;
	}

	/**
	 * @param anoMesReferenciaDocumento O anoMesReferenciaDocumento a ser setado.
	 */
	public void setAnoMesReferenciaDocumento(Integer anoMesReferenciaDocumento) {
		this.anoMesReferenciaDocumento = anoMesReferenciaDocumento;
	}

	/**
	 * @return Retorna o campo categoria.
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo documentoTipo.
	 */
	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	/**
	 * @param documentoTipo O documentoTipo a ser setado.
	 */
	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	/**
	 * @return Retorna o campo esferaPoder.
	 */
	public EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}

	/**
	 * @param esferaPoder O esferaPoder a ser setado.
	 */
	public void setEsferaPoder(EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	/**
	 * @return Retorna o campo financiamentoTipo.
	 */
	public FinanciamentoTipo getFinanciamentoTipo() {
		return financiamentoTipo;
	}

	/**
	 * @param financiamentoTipo O financiamentoTipo a ser setado.
	 */
	public void setFinanciamentoTipo(FinanciamentoTipo financiamentoTipo) {
		this.financiamentoTipo = financiamentoTipo;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo imovelPerfil.
	 */
	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	/**
	 * @param imovelPerfil O imovelPerfil a ser setado.
	 */
	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	/**
	 * @return Retorna o campo indicadorHidrometro.
	 */
	public Short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	/**
	 * @param indicadorHidrometro O indicadorHidrometro a ser setado.
	 */
	public void setIndicadorHidrometro(Short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	/**
	 * @return Retorna o campo ligacaoAguaSituacao.
	 */
	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	/**
	 * @param ligacaoAguaSituacao O ligacaoAguaSituacao a ser setado.
	 */
	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	/**
	 * @return Retorna o campo ligacaoEsgotoSituacao.
	 */
	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	/**
	 * @param ligacaoEsgotoSituacao O ligacaoEsgotoSituacao a ser setado.
	 */
	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo numeroQuadra.
	 */
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	/**
	 * @param numeroQuadra O numeroQuadra a ser setado.
	 */
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	/**
	 * @return Retorna o campo quadra.
	 */
	public Quadra getQuadra() {
		return quadra;
	}

	/**
	 * @param quadra O quadra a ser setado.
	 */
	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	/**
	 * @return Retorna o campo quantidadeDocumentos.
	 */
	public Integer getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	/**
	 * @param quantidadeDocumentos O quantidadeDocumentos a ser setado.
	 */
	public void setQuantidadeDocumentos(Integer quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	/**
	 * @return Retorna o campo quantidadeLigacoes.
	 */
	public Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}

	/**
	 * @param quantidadeLigacoes O quantidadeLigacoes a ser setado.
	 */
	public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}

	/**
	 * @return Retorna o campo rota.
	 */
	public Rota getRota() {
		return rota;
	}

	/**
	 * @param rota O rota a ser setado.
	 */
	public void setRota(Rota rota) {
		this.rota = rota;
	}

	/**
	 * @return Retorna o campo setorComercial.
	 */
	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	/**
	 * @param setorComercial O setorComercial a ser setado.
	 */
	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo valorDebito.
	 */
	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	/**
	 * @param valorDebito O valorDebito a ser setado.
	 */
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	/**
	 * @return Retorna o campo indicadorVencido.
	 */
	public Short getIndicadorVencido() {
		return indicadorVencido;
	}

	/**
	 * @param indicadorVencido O indicadorVencido a ser setado.
	 */
	public void setIndicadorVencido(Short indicadorVencido) {
		this.indicadorVencido = indicadorVencido;
	}
}