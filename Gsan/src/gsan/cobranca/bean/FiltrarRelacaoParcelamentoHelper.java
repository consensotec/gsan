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


import gsan.cobranca.parcelamento.Parcelamento;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;


/**
 * [UC0594] Gerar Rela��o de Parcelamento
 * 
 * @author Ana Maria
 *
 * @date 30/05/2007
 */
public class FiltrarRelacaoParcelamentoHelper {
	
	private Parcelamento parcelamento = null;
	private Date dataParcelamentoInicial = null;
	private Date dataParcelamentoFinal = null;
	private Collection<Integer> idsMotivoDesfazimento = null;
	private BigDecimal valorDebitoInicial = null;	
	private BigDecimal valorDebitoFinal = null;
	private Integer idGerencia = null;
	private Integer idElo = null;
	private Integer idUnidadeNegocio = null;
	private Integer idUsuarioResponsavel = null;
	private Collection<Integer> colecaoPerfilImovel = null;
	private Collection<Integer> colecaoMunicipiosAssociados = null;
	private Date dataConfirmacaoInicial = null;
	private Date dataConfirmacaoFinal = null;
	private Date dataConfirmacaoOperadoraInicial = null;
	private Date dataConfirmacaoOperadoraFinal = null;
	private Short indicadorConfirmacaoOperadora = null;
	private Integer idUsuarioConfirmacao = null;
	private Integer idUnidadeOrganizacional = null;
	private Integer anoMesReferenciaContabil = null;
	private String periodoContabil = null;
	
	
	public String getPeriodoContabil() {
		return periodoContabil;
	}


	public void setPeriodoContabil(String periodoContabil) {
		this.periodoContabil = periodoContabil;
	}


	public Integer getAnoMesReferenciaContabil() {
		return anoMesReferenciaContabil;
	}


	public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}


	public Integer getIdUsuarioResponsavel() {
		return idUsuarioResponsavel;
	}


	public void setIdUsuarioResponsavel(Integer idUsuarioResponsavel) {
		this.idUsuarioResponsavel = idUsuarioResponsavel;
	}


	/**
	 * @return Retorna o campo idGerencia.
	 */
	public Integer getIdGerencia() {
		return idGerencia;
	}


	/**
	 * @param idGerencia O idGerencia a ser setado.
	 */
	public void setIdGerencia(Integer idGerencia) {
		this.idGerencia = idGerencia;
	}


	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}


	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}


	public FiltrarRelacaoParcelamentoHelper() {
	}


	/**
	 * @return Retorna o campo dataParcelamentoFinal.
	 */
	public Date getDataParcelamentoFinal() {
		return dataParcelamentoFinal;
	}

	/**
	 * @param dataParcelamentoFinal O dataParcelamentoFinal a ser setado.
	 */
	public void setDataParcelamentoFinal(Date dataParcelamentoFinal) {
		this.dataParcelamentoFinal = dataParcelamentoFinal;
	}

	/**
	 * @return Retorna o campo dataParcelamentoInicial.
	 */
	public Date getDataParcelamentoInicial() {
		return dataParcelamentoInicial;
	}

	/**
	 * @param dataParcelamentoInicial O dataParcelamentoInicial a ser setado.
	 */
	public void setDataParcelamentoInicial(Date dataParcelamentoInicial) {
		this.dataParcelamentoInicial = dataParcelamentoInicial;
	}

	/**
	 * @return Retorna o campo idsMotivoDesfazimento.
	 */
	public Collection<Integer> getIdsMotivoDesfazimento() {
		return idsMotivoDesfazimento;
	}

	/**
	 * @param idsMotivoDesfazimento O idsMotivoDesfazimento a ser setado.
	 */
	public void setIdsMotivoDesfazimento(Collection<Integer> idsMotivoDesfazimento) {
		this.idsMotivoDesfazimento = idsMotivoDesfazimento;
	}

	/**
	 * @return Retorna o campo parcelamento.
	 */
	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	/**
	 * @param parcelamento O parcelamento a ser setado.
	 */
	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	/**
	 * @return Retorna o campo valorDebitoFinal.
	 */
	public BigDecimal getValorDebitoFinal() {
		return valorDebitoFinal;
	}

	/**
	 * @param valorDebitoFinal O valorDebitoFinal a ser setado.
	 */
	public void setValorDebitoFinal(BigDecimal valorDebitoFinal) {
		this.valorDebitoFinal = valorDebitoFinal;
	}

	/**
	 * @return Retorna o campo valorDebitoInicial.
	 */
	public BigDecimal getValorDebitoInicial() {
		return valorDebitoInicial;
	}

	/**
	 * @param valorDebitoInicial O valorDebitoInicial a ser setado.
	 */
	public void setValorDebitoInicial(BigDecimal valorDebitoInicial) {
		this.valorDebitoInicial = valorDebitoInicial;
	}

	public Integer getIdElo() {
		return idElo;
	}

	public void setIdElo(Integer idElo) {
		this.idElo = idElo;
	}

	public Collection<Integer> getColecaoPerfilImovel() {
		return colecaoPerfilImovel;
	}

	public void setColecaoPerfilImovel(Collection<Integer> colecaoPerfilImovel) {
		this.colecaoPerfilImovel = colecaoPerfilImovel;
	}

	public Date getDataConfirmacaoInicial() {
		return dataConfirmacaoInicial;
	}

	public void setDataConfirmacaoInicial(Date dataConfirmacaoInicial) {
		this.dataConfirmacaoInicial = dataConfirmacaoInicial;
	}

	public Date getDataConfirmacaoFinal() {
		return dataConfirmacaoFinal;
	}

	public void setDataConfirmacaoFinal(Date dataConfirmacaoFinal) {
		this.dataConfirmacaoFinal = dataConfirmacaoFinal;
	}

	public Date getDataConfirmacaoOperadoraInicial() {
		return dataConfirmacaoOperadoraInicial;
	}

	public void setDataConfirmacaoOperadoraInicial(
			Date dataConfirmacaoOperadoraInicial) {
		this.dataConfirmacaoOperadoraInicial = dataConfirmacaoOperadoraInicial;
	}

	public Date getDataConfirmacaoOperadoraFinal() {
		return dataConfirmacaoOperadoraFinal;
	}

	public void setDataConfirmacaoOperadoraFinal(Date dataConfirmacaoOperadoraFinal) {
		this.dataConfirmacaoOperadoraFinal = dataConfirmacaoOperadoraFinal;
	}

	public Short getIndicadorConfirmacaoOperadora() {
		return indicadorConfirmacaoOperadora;
	}

	public void setIndicadorConfirmacaoOperadora(Short indicadorConfirmacaoOperadora) {
		this.indicadorConfirmacaoOperadora = indicadorConfirmacaoOperadora;
	}

	public Integer getIdUsuarioConfirmacao() {
		return idUsuarioConfirmacao;
	}

	public void setIdUsuarioConfirmacao(Integer idUsuarioConfirmacao) {
		this.idUsuarioConfirmacao = idUsuarioConfirmacao;
	}

	public Integer getIdUnidadeOrganizacional() {
		return idUnidadeOrganizacional;
	}

	public void setIdUnidadeOrganizacional(Integer idUnidadeOrganizacional) {
		this.idUnidadeOrganizacional = idUnidadeOrganizacional;
	}

	public Collection<Integer> getColecaoMunicipiosAssociados() {
		return colecaoMunicipiosAssociados;
	}

	public void setColecaoMunicipiosAssociados(
			Collection<Integer> colecaoMunicipiosAssociados) {
		this.colecaoMunicipiosAssociados = colecaoMunicipiosAssociados;
	}	
}