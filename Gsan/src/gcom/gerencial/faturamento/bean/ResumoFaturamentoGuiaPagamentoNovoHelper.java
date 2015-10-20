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
package gcom.gerencial.faturamento.bean;


import java.math.BigDecimal;import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Classe responsável por ajudar o caso de uso [UC0572] Gerar Resumo ReFaturamento Novo
 *
 * @author Fernando Fontelles
 * @date 01/07/2010
 */
public class ResumoFaturamentoGuiaPagamentoNovoHelper {
	private Integer idImovel;
	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idLocalidade;
	private Integer idElo;
	private Integer idSetorComercial;	
	private Integer codigoSetorComercial;	
	private Integer idPerfilImovel;
	private Integer idEsfera;
	private Integer idTipoClienteResponsavel = 0;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idCategoria;
	private Integer idSubCategoria;
	private Integer idPerfilLigacaoAgua;
	private Integer idPerfilLigacaoEsgoto;
	private Integer anoMesReferencia;
	private Integer idConta;
	private Integer consumoAgua = 0;
	private Integer consumoEsgoto = 0;
	private BigDecimal valorAgua = new BigDecimal(0);
	private BigDecimal valorEsgoto = new BigDecimal(0);	
	private Integer idFinanciamentoTipo;
	private Integer idDocumentoTipo;
	private Integer debitoTipo = 0;
	private Short indicadorHidrometro;
	private Integer consumoTarifa = 0;		private Integer creditoOrigem = null;		private Integer creditoTipo;		private Integer qtdContasRetificadas = 0;		private Integer qtdContasCanceladas = 0;		private Integer qtdContasIncluidas = 0;		private BigDecimal vlCanceladoAgua = new BigDecimal("0");		private BigDecimal vlCanceladoEsgoto = new BigDecimal("0");		private BigDecimal vlCanceladoOutros = new BigDecimal("0");		private BigDecimal vlCanceladoCredito = new BigDecimal("0");		private BigDecimal vlImpostosCancelados = new BigDecimal("0");		private BigDecimal voCanceladoAgua = new BigDecimal("0");		private BigDecimal voCanceladoEsgoto = new BigDecimal("0");		private BigDecimal vlIncluidoAgua = new BigDecimal("0");		private BigDecimal vlIncluidoEsgoto = new BigDecimal("0");		private BigDecimal vlIncluidoOutros = new BigDecimal("0");		private BigDecimal vlIncluidoCredito = new BigDecimal("0");		private BigDecimal vlImpostosIncluidos = new BigDecimal("0");		private BigDecimal voIncluidoAgua = new BigDecimal("0");		private BigDecimal voIncluidoEsgoto = new BigDecimal("0");		private BigDecimal vlGuiasCanceladas = new BigDecimal("0");		private Integer qtdGuiasCanceladas = 0;		private Integer lancamentoItemContabil;		private Integer anoMesReferenciaEmissao;		private Integer idBairro = null;		private Integer idTipoArea = null;		private Integer idMunicipio = null;
	public Integer getLancamentoItemContabil() {		return lancamentoItemContabil;	}	public void setLancamentoItemContabil(Integer lancamentoItemContabil) {		this.lancamentoItemContabil = lancamentoItemContabil;	}	public Integer getQtdContasCanceladas() {		return qtdContasCanceladas;	}	public void setQtdContasCanceladas(Integer qtdContasCanceladas) {		this.qtdContasCanceladas = qtdContasCanceladas;	}	public Integer getQtdContasIncluidas() {		return qtdContasIncluidas;	}	public void setQtdContasIncluidas(Integer qtdContasIncluidas) {		this.qtdContasIncluidas = qtdContasIncluidas;	}	public Integer getQtdContasRetificadas() {		return qtdContasRetificadas;	}	public void setQtdContasRetificadas(Integer qtdContasRetificadas) {		this.qtdContasRetificadas = qtdContasRetificadas;	}	public Integer getQtdGuiasCanceladas() {		return qtdGuiasCanceladas;	}	public void setQtdGuiasCanceladas(Integer qtdGuiasCanceladas) {		this.qtdGuiasCanceladas = qtdGuiasCanceladas;	}	public BigDecimal getVlCanceladoAgua() {		return vlCanceladoAgua;	}	public void setVlCanceladoAgua(BigDecimal vlCanceladoAgua) {		this.vlCanceladoAgua = vlCanceladoAgua;	}	public BigDecimal getVlCanceladoCredito() {		return vlCanceladoCredito;	}	public void setVlCanceladoCredito(BigDecimal vlCanceladoCredito) {		this.vlCanceladoCredito = vlCanceladoCredito;	}	public BigDecimal getVlCanceladoEsgoto() {		return vlCanceladoEsgoto;	}	public void setVlCanceladoEsgoto(BigDecimal vlCanceladoEsgoto) {		this.vlCanceladoEsgoto = vlCanceladoEsgoto;	}	public BigDecimal getVlCanceladoOutros() {		return vlCanceladoOutros;	}	public void setVlCanceladoOutros(BigDecimal vlCanceladoOutros) {		this.vlCanceladoOutros = vlCanceladoOutros;	}	public BigDecimal getVlGuiasCanceladas() {		return vlGuiasCanceladas;	}	public void setVlGuiasCanceladas(BigDecimal vlGuiasCanceladas) {		this.vlGuiasCanceladas = vlGuiasCanceladas;	}	public BigDecimal getVlImpostosCancelados() {		return vlImpostosCancelados;	}	public void setVlImpostosCancelados(BigDecimal vlImpostosCancelados) {		this.vlImpostosCancelados = vlImpostosCancelados;	}	public BigDecimal getVlImpostosIncluidos() {		return vlImpostosIncluidos;	}	public void setVlImpostosIncluidos(BigDecimal vlImpostosIncluidos) {		this.vlImpostosIncluidos = vlImpostosIncluidos;	}	public BigDecimal getVlIncluidoAgua() {		return vlIncluidoAgua;	}	public void setVlIncluidoAgua(BigDecimal vlIncluidoAgua) {		this.vlIncluidoAgua = vlIncluidoAgua;	}	public BigDecimal getVlIncluidoCredito() {		return vlIncluidoCredito;	}	public void setVlIncluidoCredito(BigDecimal vlIncluidoCredito) {		this.vlIncluidoCredito = vlIncluidoCredito;	}	public BigDecimal getVlIncluidoEsgoto() {		return vlIncluidoEsgoto;	}	public void setVlIncluidoEsgoto(BigDecimal vlIncluidoEsgoto) {		this.vlIncluidoEsgoto = vlIncluidoEsgoto;	}	public BigDecimal getVlIncluidoOutros() {		return vlIncluidoOutros;	}	public void setVlIncluidoOutros(BigDecimal vlIncluidoOutros) {		this.vlIncluidoOutros = vlIncluidoOutros;	}	public BigDecimal getVoCanceladoAgua() {		return voCanceladoAgua;	}	public void setVoCanceladoAgua(BigDecimal voCanceladoAgua) {		this.voCanceladoAgua = voCanceladoAgua;	}	public BigDecimal getVoCanceladoEsgoto() {		return voCanceladoEsgoto;	}	public void setVoCanceladoEsgoto(BigDecimal voCanceladoEsgoto) {		this.voCanceladoEsgoto = voCanceladoEsgoto;	}	public BigDecimal getVoIncluidoAgua() {		return voIncluidoAgua;	}	public void setVoIncluidoAgua(BigDecimal voIncluidoAgua) {		this.voIncluidoAgua = voIncluidoAgua;	}	public BigDecimal getVoIncluidoEsgoto() {		return voIncluidoEsgoto;	}	public void setVoIncluidoEsgoto(BigDecimal voIncluidoEsgoto) {		this.voIncluidoEsgoto = voIncluidoEsgoto;	}	public Integer getCreditoTipo() {		return creditoTipo;	}	public void setCreditoTipo(Integer creditoTipo) {		this.creditoTipo = creditoTipo;	}	public Integer getCreditoOrigem() {		return creditoOrigem;	}	public void setCreditoOrigem(Integer creditoOrigem) {		this.creditoOrigem = creditoOrigem;	}
	public Integer getConsumoTarifa() {		return consumoTarifa;	}
	public void setConsumoTarifa(Integer consumoTarifa) {		this.consumoTarifa = consumoTarifa;	}
	public Short getIndicadorHidrometro() {		return indicadorHidrometro;	}
	public void setIndicadorHidrometro(Short indicadorHidrometro) {		this.indicadorHidrometro = indicadorHidrometro;	}
	public Integer getDebitoTipo() {		return debitoTipo;	}
	public void setDebitoTipo(Integer debitoTipo) {		this.debitoTipo = debitoTipo;	}
	public Integer getIdFinanciamentoTipo() {		return idFinanciamentoTipo;	}
	public void setIdFinanciamentoTipo(Integer idFinanciamentoTipo) {		this.idFinanciamentoTipo = idFinanciamentoTipo;	}
	public Integer getConsumoAgua() {		return consumoAgua;	}
	public void setConsumoAgua(Integer consumoAgua) {		this.consumoAgua = consumoAgua;	}	
	public Integer getConsumoEsgoto() {		return consumoEsgoto;	}
	public void setConsumoEsgoto(Integer consumoEsgoto) {		this.consumoEsgoto = consumoEsgoto;	}    /**     * Compara dois objetos levando em consideracao apenas as propriedades     * que identificam o agrupamento     * @param obj Objeto a ser comparado com a instancia deste objeto      */	
	public boolean equals(Object obj) {        if (this == obj) {            return true;        }
        if (!(obj instanceof ResumoFaturamentoGuiaPagamentoNovoHelper)) {            return false;        }
        ResumoFaturamentoGuiaPagamentoNovoHelper resumoTemp = (ResumoFaturamentoGuiaPagamentoNovoHelper) obj;        
        return new EqualsBuilder()        .append(this.getIdGerenciaRegional(), resumoTemp.getIdGerenciaRegional())        .append(this.getIdUnidadeNegocio(), resumoTemp.getIdUnidadeNegocio())        .append(this.getIdElo(), resumoTemp.getIdElo())        .append(this.getIdLocalidade(), resumoTemp.getIdLocalidade())        .append(this.getIdSetorComercial(), resumoTemp.getIdSetorComercial())        .append(this.getCodigoSetorComercial(), resumoTemp.getCodigoSetorComercial())        .append(this.getIdPerfilImovel(), resumoTemp.getIdPerfilImovel())                .append(this.getIdSituacaoLigacaoAgua(), resumoTemp.getIdSituacaoLigacaoAgua())        .append(this.getIdSituacaoLigacaoEsgoto(), resumoTemp.getIdSituacaoLigacaoEsgoto())                .append(this.getIdCategoria(), resumoTemp.getIdCategoria())        .append(this.getIdSubCategoria(), resumoTemp.getIdSubCategoria())        .append(this.getIdEsfera(), resumoTemp.getIdEsfera())        .append(this.getIdTipoClienteResponsavel(), resumoTemp.getIdTipoClienteResponsavel())        .append(this.getIdPerfilLigacaoAgua(), resumoTemp.getIdPerfilLigacaoAgua())        .append(this.getIdPerfilLigacaoEsgoto(), resumoTemp.getIdPerfilLigacaoEsgoto())                .append(this.getConsumoTarifa(), resumoTemp.getConsumoTarifa())                .append(this.getIndicadorHidrometro(), resumoTemp.getIndicadorHidrometro())                .append(this.getCreditoOrigem(), resumoTemp.getCreditoOrigem())                .append(this.getLancamentoItemContabil(), resumoTemp.getLancamentoItemContabil())        .append(this.getIdFinanciamentoTipo(), resumoTemp.getIdFinanciamentoTipo())        .append(this.getDebitoTipo(), resumoTemp.getDebitoTipo())                .append(this.getCreditoTipo(), resumoTemp.getCreditoTipo())        .append(this.getIdDocumentoTipo(), resumoTemp.getIdDocumentoTipo())        .append(this.getIdMunicipio(), resumoTemp.getIdMunicipio())                .append(this.getIdBairro(), resumoTemp.getIdBairro())                .append(this.getIdTipoArea(), resumoTemp.getIdTipoArea())        .isEquals();
    }
	public Integer getCodigoSetorComercial() {		return codigoSetorComercial;	}
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {		this.codigoSetorComercial = codigoSetorComercial;	}
	public Integer getIdCategoria() {		return idCategoria;	}
	public void setIdCategoria(Integer idCategoria) {		this.idCategoria = idCategoria;	}
	public Integer getIdEsfera() {		return idEsfera;	}
	public void setIdEsfera(Integer idEsfera) {		this.idEsfera = idEsfera;	}
	public Integer getIdGerenciaRegional() {		return idGerenciaRegional;	}
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {		this.idGerenciaRegional = idGerenciaRegional;	}
	public Integer getIdImovel() {		return idImovel;	}
	public void setIdImovel(Integer idImovel) {		this.idImovel = idImovel;	}
	public Integer getIdLocalidade() {		return idLocalidade;	}
	public void setIdLocalidade(Integer idLocalidade) {		this.idLocalidade = idLocalidade;	}
	public Integer getIdPerfilImovel() {		return idPerfilImovel;	}
	public void setIdPerfilImovel(Integer idPerfilImovel) {		this.idPerfilImovel = idPerfilImovel;	}
	public Integer getIdSetorComercial() {		return idSetorComercial;	}
	public void setIdSetorComercial(Integer idSetorComercial) {		this.idSetorComercial = idSetorComercial;	}
	public Integer getIdSituacaoLigacaoAgua() {		return idSituacaoLigacaoAgua;	}
	public void setIdSituacaoLigacaoAgua(Integer idSituacaoLigacaoAgua) {		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;	}
	public Integer getIdSituacaoLigacaoEsgoto() {		return idSituacaoLigacaoEsgoto;	}
	public void setIdSituacaoLigacaoEsgoto(Integer idSituacaoLigacaoEsgoto) {		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;	}
	public int hashCode() {		String retorno =  		this.getIdGerenciaRegional() + "sdf" +		this.getIdUnidadeNegocio() + "sdf" +		this.getIdLocalidade() + "sdf" +		this.getIdElo() + "sdf" +		this.getIdSetorComercial() + "sdf" +		this.getCodigoSetorComercial() + "sdf" +		this.getIdPerfilImovel() + "sdf" +		this.getIdSituacaoLigacaoAgua() + "sdf" +		this.getIdSituacaoLigacaoEsgoto() + "sdf" +				this.getIdCategoria() + "sdf" +				this.getIdSubCategoria() + "sdf" +				this.getIdEsfera() + "sdf" +				this.getIdTipoClienteResponsavel() + "sdf" +		this.getIdPerfilLigacaoAgua() + "sdf" +		this.getIdPerfilLigacaoEsgoto() + "sdf" +		this.getConsumoTarifa() + "sdf" +				this.getIndicadorHidrometro() + "sdf" +				this.getCreditoOrigem() + "sdf" +				this.getLancamentoItemContabil() + "sdf" +				this.getDebitoTipo() + "sdf" +				this.getCreditoTipo() + "sdf" +				this.getIdDocumentoTipo() + "sdf" +				this.getIdMunicipio() + "sdf" +				this.getIdBairro() + "sdf" +				this.getIdTipoArea() + "sdf";				return retorno.hashCode();	}	
	public ResumoFaturamentoGuiaPagamentoNovoHelper(Integer idImovel,Integer idGerenciaRegional, Integer idUnidadeNegocio, 			Integer idElo, Integer idLocalidade,Integer idSetorComercial,Integer codigoSetorComercial,Integer idPerfilImovel,			Integer idSituacaoLigacaoAgua,Integer idSituacaoLigacaoEsgoto, Integer idPerfilLigacaoAgua, Integer idPerfilLigacaoEsgoto,						Integer idMunicipio, Integer idBairro, Integer idTipoArea) {
		super();		this.idImovel = idImovel;		this.idGerenciaRegional = idGerenciaRegional;		this.idUnidadeNegocio = idUnidadeNegocio;		this.idElo = idElo;		this.idLocalidade = idLocalidade;		this.idSetorComercial = idSetorComercial;		this.codigoSetorComercial = codigoSetorComercial;		this.idPerfilImovel = idPerfilImovel;		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;		this.idPerfilLigacaoAgua = idPerfilLigacaoAgua;		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;				this.idMunicipio = idMunicipio;				this.idBairro = idBairro;				this.idTipoArea = idTipoArea;	}
	public ResumoFaturamentoGuiaPagamentoNovoHelper(Integer idImovel,Integer idGerenciaRegional, Integer idUnidadeNegocio,			Integer idElo, Integer idLocalidade,Integer idSetorComercial,Integer codigoSetorComercial, Integer idPerfilImovel, 			Integer idSituacaoLigacaoAgua,Integer idSituacaoLigacaoEsgoto, Integer idPerfilLigacaoAgua, Integer idPerfilLigacaoEsgoto, 			Integer anoMesReferencia) {
		super();		this.idImovel = idImovel;		this.idGerenciaRegional = idGerenciaRegional;		this.idUnidadeNegocio = idUnidadeNegocio;		this.idElo = idElo;		this.idLocalidade = idLocalidade;		this.idSetorComercial = idSetorComercial;		this.codigoSetorComercial = codigoSetorComercial;		this.idPerfilImovel = idPerfilImovel;		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;		this.idPerfilLigacaoAgua = idPerfilLigacaoAgua;		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;		this.anoMesReferencia = anoMesReferencia;	}
	public ResumoFaturamentoGuiaPagamentoNovoHelper(Integer idImovel, Integer idGerenciaRegional, Integer idLocalidade, 			Integer idSetorComercial, Integer codigoSetorComercial, Integer idPerfilImovel, Integer idSituacaoLigacaoAgua,			Integer idSituacaoLigacaoEsgoto, Integer idEsfera) {
		super();		this.idImovel = idImovel;		this.idGerenciaRegional = idGerenciaRegional;		this.idLocalidade = idLocalidade;		this.idSetorComercial = idSetorComercial;		this.codigoSetorComercial = codigoSetorComercial;		this.idPerfilImovel = idPerfilImovel;		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;		
		if (idEsfera != null && idEsfera.intValue() != 0){			this.idEsfera = idEsfera;		}
	}
	public Integer getIdUnidadeNegocio() {		return idUnidadeNegocio;	}
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {		this.idUnidadeNegocio = idUnidadeNegocio;	}
	public Integer getAnoMesReferencia() {		return anoMesReferencia;	}
	public void setAnoMesReferencia(Integer anoMesReferencia) {		this.anoMesReferencia = anoMesReferencia;	}
	public Integer getIdSubCategoria() {		return idSubCategoria;	}
	public void setIdSubCategoria(Integer idSubCategoria) {		this.idSubCategoria = idSubCategoria;	}
	public BigDecimal getValorAgua() {		return valorAgua;	}
	public void setValorAgua(BigDecimal valorAgua) {		this.valorAgua = valorAgua;	}
	public BigDecimal getValorEsgoto() {		return valorEsgoto;	}
	public void setValorEsgoto(BigDecimal valorEsgoto) {		this.valorEsgoto = valorEsgoto;	}
	public Integer getIdElo() {		return idElo;	}
	public void setIdElo(Integer idElo) {		this.idElo = idElo;	}
	public Integer getIdTipoClienteResponsavel() {		return idTipoClienteResponsavel;	}
	public void setIdTipoClienteResponsavel(Integer idTipoClienteResponsavel) {		this.idTipoClienteResponsavel = idTipoClienteResponsavel;	}
	public Integer getIdPerfilLigacaoAgua() {		return idPerfilLigacaoAgua;	}
	public void setIdPerfilLigacaoAgua(Integer idPerfilLigacaoAgua) {		this.idPerfilLigacaoAgua = idPerfilLigacaoAgua;	}
	public Integer getIdPerfilLigacaoEsgoto() {		return idPerfilLigacaoEsgoto;	}
	public void setIdPerfilLigacaoEsgoto(Integer idPerfilLigacaoEsgoto) {		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;	}
	public Integer getIdConta() {		return idConta;	}
	public void setIdConta(Integer idConta) {		this.idConta = idConta;	}
	public Integer getIdDocumentoTipo() {		return idDocumentoTipo;	
	}
	public void setIdDocumentoTipo(Integer idDocumentoTipo) {		this.idDocumentoTipo = idDocumentoTipo;	}		public Integer getAnoMesReferenciaEmissao() {		return anoMesReferenciaEmissao;	}	public void setAnoMesReferenciaEmissao(Integer anoMesReferenciaEmissao) {		this.anoMesReferenciaEmissao = anoMesReferenciaEmissao;	}	public Integer getIdBairro() {		return idBairro;	}	public void setIdBairro(Integer idBairro) {		this.idBairro = idBairro;	}	public Integer getIdMunicipio() {		return idMunicipio;	}	public void setIdMunicipio(Integer idMunicipio) {		this.idMunicipio = idMunicipio;	}	public Integer getIdTipoArea() {		return idTipoArea;	}	public void setIdTipoArea(Integer idTipoArea) {		this.idTipoArea = idTipoArea;	}
}