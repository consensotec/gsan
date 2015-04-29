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
package gsan.gui.cobranca.parcelamento;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0594] Gerar Rela��o de Parcelamento
 * 
 * @author Ana Maria
 *
 * @date 30/05/2007
 */
public class GerarRelatorioRelacaoParcelamentoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idVisaoRelatorio;
	private String idGerenciaRegional;
	private String idUnidadeNegocio;
	private String idEloPolo;
	private String descricaoElo;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String idSetorComercial;
	private String descricaoSetorComercial;
	private String idQuadra;
	private String idSituacaoParcelamento;
	private String dataParcelamentoInicial;
	private String dataParcelamentoFinal;
	private String[] idsMotivoDesfazimento;
	private String valorDebitoInicial;
	private String valorDebitoFinal;
	private String idUsuarioResponsavel;
	private String descricaoUsuarioResponsavel;
	private String dataConfirmacaoInicial;
	private String dataConfirmacaoFinal;
	private String idUsuarioConfirmacao;
	private String descricaoUsuarioConfirmacao;
	private String indicadorConfirmacaoOperadora;
	private String dataConfirmacaoOperadoraInicial;
	private String dataConfirmacaoOperadoraFinal;
	private String idUnidadeOrganizacional;
	private String descricaoUnidadeOrganizacional;
	
	private String[] perfilImovel;
    private String[] municipiosAssociados;
    
    private String anoMesReferenciaContabil;
    private String periodoContabil;
	
    
	public String getPeriodoContabil() {
		return periodoContabil;
	}

	public void setPeriodoContabil(String periodoContabil) {
		this.periodoContabil = periodoContabil;
	}

	public String getAnoMesReferenciaContabil() {
		return anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(String anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest ){

    }
    
	/**
	 * @return Retorna o campo dataParcelamentoFinal.
	 */
	public String getDataParcelamentoFinal() {
		return dataParcelamentoFinal;
	}
	/**
	 * @param dataParcelamentoFinal O dataParcelamentoFinal a ser setado.
	 */
	public void setDataParcelamentoFinal(String dataParcelamentoFinal) {
		this.dataParcelamentoFinal = dataParcelamentoFinal;
	}
	/**
	 * @return Retorna o campo dataParcelamentoInicial.
	 */
	public String getDataParcelamentoInicial() {
		return dataParcelamentoInicial;
	}
	/**
	 * @param dataParcelamentoInicial O dataParcelamentoInicial a ser setado.
	 */
	public void setDataParcelamentoInicial(String dataParcelamentoInicial) {
		this.dataParcelamentoInicial = dataParcelamentoInicial;
	}
	/**
	 * @return Retorna o campo descricaoElo.
	 */
	public String getDescricaoElo() {
		return descricaoElo;
	}
	/**
	 * @param descricaoElo O descricaoElo a ser setado.
	 */
	public void setDescricaoElo(String descricaoElo) {
		this.descricaoElo = descricaoElo;
	}
	/**
	 * @return Retorna o campo descricaoLocalidade.
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	/**
	 * @param descricaoLocalidade O descricaoLocalidade a ser setado.
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	/**
	 * @return Retorna o campo descricaoSetorComercial.
	 */
	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}
	/**
	 * @param descricaoSetorComercial O descricaoSetorComercial a ser setado.
	 */
	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}
	/**
	 * @return Retorna o campo descricaoUsuarioResponsavel.
	 */
	public String getDescricaoUsuarioResponsavel() {
		return descricaoUsuarioResponsavel;
	}
	/**
	 * @param descricaoUsuarioResponsavel O descricaoUsuarioResponsavel a ser setado.
	 */
	public void setDescricaoUsuarioResponsavel(String descricaoUsuarioResponsavel) {
		this.descricaoUsuarioResponsavel = descricaoUsuarioResponsavel;
	}
	/**
	 * @return Retorna o campo idEloPolo.
	 */
	public String getIdEloPolo() {
		return idEloPolo;
	}
	/**
	 * @param idEloPolo O idEloPolo a ser setado.
	 */
	public void setIdEloPolo(String idEloPolo) {
		this.idEloPolo = idEloPolo;
	}
	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}
	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	/**
	 * @return Retorna o campo idQuadra.
	 */
	public String getIdQuadra() {
		return idQuadra;
	}
	/**
	 * @param idQuadra O idQuadra a ser setado.
	 */
	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}
	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	/**
	 * @return Retorna o campo idSituacaoParcelamento.
	 */
	public String getIdSituacaoParcelamento() {
		return idSituacaoParcelamento;
	}
	/**
	 * @param idSituacaoParcelamento O idSituacaoParcelamento a ser setado.
	 */
	public void setIdSituacaoParcelamento(String idSituacaoParcelamento) {
		this.idSituacaoParcelamento = idSituacaoParcelamento;
	}
	/**
	 * @return Retorna o campo idsMotivoDesfazimento.
	 */
	public String[] getIdsMotivoDesfazimento() {
		return idsMotivoDesfazimento;
	}
	/**
	 * @param idsMotivoDesfazimento O idsMotivoDesfazimento a ser setado.
	 */
	public void setIdsMotivoDesfazimento(String[] idsMotivoDesfazimento) {
		this.idsMotivoDesfazimento = idsMotivoDesfazimento;
	}
	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	/**
	 * @return Retorna o campo idUsuarioResponsavel.
	 */
	public String getIdUsuarioResponsavel() {
		return idUsuarioResponsavel;
	}
	/**
	 * @param idUsuarioResponsavel O idUsuarioResponsavel a ser setado.
	 */
	public void setIdUsuarioResponsavel(String idUsuarioResponsavel) {
		this.idUsuarioResponsavel = idUsuarioResponsavel;
	}
	/**
	 * @return Retorna o campo idVisaoRelatorio.
	 */
	public String getIdVisaoRelatorio() {
		return idVisaoRelatorio;
	}
	/**
	 * @param idVisaoRelatorio O idVisaoRelatorio a ser setado.
	 */
	public void setIdVisaoRelatorio(String idVisaoRelatorio) {
		this.idVisaoRelatorio = idVisaoRelatorio;
	}
	/**
	 * @return Retorna o campo valorDebitoFinal.
	 */
	public String getValorDebitoFinal() {
		return valorDebitoFinal;
	}
	/**
	 * @param valorDebitoFinal O valorDebitoFinal a ser setado.
	 */
	public void setValorDebitoFinal(String valorDebitoFinal) {
		this.valorDebitoFinal = valorDebitoFinal;
	}
	/**
	 * @return Retorna o campo valorDebitoInicial.
	 */
	public String getValorDebitoInicial() {
		return valorDebitoInicial;
	}
	/**
	 * @param valorDebitoInicial O valorDebitoInicial a ser setado.
	 */
	public void setValorDebitoInicial(String valorDebitoInicial) {
		this.valorDebitoInicial = valorDebitoInicial;
	}

	public String getDataConfirmacaoInicial() {
		return dataConfirmacaoInicial;
	}

	public void setDataConfirmacaoInicial(String dataConfirmacaoInicial) {
		this.dataConfirmacaoInicial = dataConfirmacaoInicial;
	}

	public String getDataConfirmacaoFinal() {
		return dataConfirmacaoFinal;
	}

	public void setDataConfirmacaoFinal(String dataConfirmacaoFinal) {
		this.dataConfirmacaoFinal = dataConfirmacaoFinal;
	}

	public String getIdUsuarioConfirmacao() {
		return idUsuarioConfirmacao;
	}

	public void setIdUsuarioConfirmacao(String idUsuarioConfirmacao) {
		this.idUsuarioConfirmacao = idUsuarioConfirmacao;
	}

	public String getDescricaoUsuarioConfirmacao() {
		return descricaoUsuarioConfirmacao;
	}

	public void setDescricaoUsuarioConfirmacao(String descricaoUsuarioConfirmacao) {
		this.descricaoUsuarioConfirmacao = descricaoUsuarioConfirmacao;
	}

	public String getIndicadorConfirmacaoOperadora() {
		return indicadorConfirmacaoOperadora;
	}

	public void setIndicadorConfirmacaoOperadora(
			String indicadorConfirmacaoOperadora) {
		this.indicadorConfirmacaoOperadora = indicadorConfirmacaoOperadora;
	}

	public String getDataConfirmacaoOperadoraInicial() {
		return dataConfirmacaoOperadoraInicial;
	}

	public void setDataConfirmacaoOperadoraInicial(
			String dataConfirmacaoOperadoraInicial) {
		this.dataConfirmacaoOperadoraInicial = dataConfirmacaoOperadoraInicial;
	}

	public String getDataConfirmacaoOperadoraFinal() {
		return dataConfirmacaoOperadoraFinal;
	}

	public void setDataConfirmacaoOperadoraFinal(
			String dataConfirmacaoOperadoraFinal) {
		this.dataConfirmacaoOperadoraFinal = dataConfirmacaoOperadoraFinal;
	}

	public String getDescricaoUnidadeOrganizacional() {
		return descricaoUnidadeOrganizacional;
	}

	public void setDescricaoUnidadeOrganizacional(
			String descricaoUnidadeOrganizacional) {
		this.descricaoUnidadeOrganizacional = descricaoUnidadeOrganizacional;
	}

	public String getIdUnidadeOrganizacional() {
		return idUnidadeOrganizacional;
	}

	public void setIdUnidadeOrganizacional(String idUnidadeOrganizacional) {
		this.idUnidadeOrganizacional = idUnidadeOrganizacional;
	}

	public String[] getMunicipiosAssociados() {
		return municipiosAssociados;
	}

	public void setMunicipiosAssociados(String[] municipiosAssociados) {
		this.municipiosAssociados = municipiosAssociados;
	}
	
    public String[] getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String[] perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
}