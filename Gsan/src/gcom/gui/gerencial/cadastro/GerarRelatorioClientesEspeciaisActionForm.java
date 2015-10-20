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
package gcom.gui.gerencial.cadastro;

import org.apache.struts.action.ActionForm;

/**
 * Classe respons�vel por ajudar o caso de uso [UC0269] Resumo de Ligacoes Economias 
 *
 * @author Thiago Toscano
 * @date 20/04/2006 
 */
public class GerarRelatorioClientesEspeciaisActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	private String idUnidadeNegocio;
	private String idGerenciaRegional;
	private String idLocalidadeInicial;
	private String nomeLocalidadeInicial;
	private String idLocalidadeFinal;
	private String nomeLocalidadeFinal;
	private String[] idsImovelPerfil;
	private String[] idsCategoria;
	private String[] idsSubCategoria;
	private String idSituacaoLigacaoAgua;
	private String idSituacaoLigacaoEsgoto;
	private String intervaloQtdEcoInicial;
	private String intervaloQtdEcoFinal;
	private String intervaloConsumoAguaInicial;
	private String intervaloConsumoAguaFinal;
	private String intervaloConsumoEsgotoInicial;
	private String intervaloConsumoEsgotoFinal;
	private String intervaloConsumoResponsavelInicial;
	private String intervaloConsumoResponsavelFinal;
	private String idClienteResponsavel;
	private String nomeClienteResponsavel;
	private String dataInstalacaoHidrometroInicial;
	private String dataInstalacaoHidrometroFinal;
	private String[] idsCapacidadeHidrometro;
	private String[] idsTarifaConsumo;
	private String idLeituraAnormalidade;
	private String descricaoLeituraAnormalidade;
	private String idConsumoAnormalidade;
	private String leituraAnormalidade;
	private String consumoAnormalidade;

	private String idSetorComercialInicial;
	private String idSetorComercialFinal;

	private String codigoSetorComercialInicial;
	private String codigoSetorComercialFinal;

	private String nomeSetorComercialInicial;
	private String nomeSetorComercialFinal;

	private String codigoRotaInicial;
	private String codigoRotaFinal;

	/**
	 * @return Retorna o campo descricaoLeituraAnormalidade.
	 */
	public String getDescricaoLeituraAnormalidade() {
		return descricaoLeituraAnormalidade;
	}
	/**
	 * @param descricaoLeituraAnormalidade O descricaoLeituraAnormalidade a ser setado.
	 */
	public void setDescricaoLeituraAnormalidade(String descricaoLeituraAnormalidade) {
		this.descricaoLeituraAnormalidade = descricaoLeituraAnormalidade;
	}
	/**
	 * @return Retorna o campo idConsumoAnormalidade.
	 */
	public String getIdConsumoAnormalidade() {
		return idConsumoAnormalidade;
	}
	/**
	 * @param idConsumoAnormalidade O idConsumoAnormalidade a ser setado.
	 */
	public void setIdConsumoAnormalidade(String idConsumoAnormalidade) {
		this.idConsumoAnormalidade = idConsumoAnormalidade;
	}
	/**
	 * @return Retorna o campo idLeituraAnormalidade.
	 */
	public String getIdLeituraAnormalidade() {
		return idLeituraAnormalidade;
	}
	/**
	 * @param idLeituraAnormalidade O idLeituraAnormalidade a ser setado.
	 */
	public void setIdLeituraAnormalidade(String idLeituraAnormalidade) {
		this.idLeituraAnormalidade = idLeituraAnormalidade;
	}
	/**
	 * @return Retorna o campo consumoAnormalidade.
	 */
	public String getConsumoAnormalidade() {
		return consumoAnormalidade;
	}
	/**
	 * @param consumoAnormalidade O consumoAnormalidade a ser setado.
	 */
	public void setConsumoAnormalidade(String consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}
	/**
	 * @return Retorna o campo leituraAnormalidade.
	 */
	public String getLeituraAnormalidade() {
		return leituraAnormalidade;
	}
	/**
	 * @param leituraAnormalidade O leituraAnormalidade a ser setado.
	 */
	public void setLeituraAnormalidade(String leituraAnormalidade) {
		this.leituraAnormalidade = leituraAnormalidade;
	}
	/**
	 * @return Retorna o campo dataInstalacaoHidrometroFinal.
	 */
	public String getDataInstalacaoHidrometroFinal() {
		return dataInstalacaoHidrometroFinal;
	}
	/**
	 * @param dataInstalacaoHidrometroFinal O dataInstalacaoHidrometroFinal a ser setado.
	 */
	public void setDataInstalacaoHidrometroFinal(
			String dataInstalacaoHidrometroFinal) {
		this.dataInstalacaoHidrometroFinal = dataInstalacaoHidrometroFinal;
	}
	/**
	 * @return Retorna o campo dataInstalacaoHidrometroInicial.
	 */
	public String getDataInstalacaoHidrometroInicial() {
		return dataInstalacaoHidrometroInicial;
	}
	/**
	 * @param dataInstalacaoHidrometroInicial O dataInstalacaoHidrometroInicial a ser setado.
	 */
	public void setDataInstalacaoHidrometroInicial(
			String dataInstalacaoHidrometroInicial) {
		this.dataInstalacaoHidrometroInicial = dataInstalacaoHidrometroInicial;
	}
	/**
	 * @return Retorna o campo idClienteResponsavel.
	 */
	public String getIdClienteResponsavel() {
		return idClienteResponsavel;
	}
	/**
	 * @param idClienteResponsavel O idClienteResponsavel a ser setado.
	 */
	public void setIdClienteResponsavel(String idClienteResponsavel) {
		this.idClienteResponsavel = idClienteResponsavel;
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
	 * @return Retorna o campo idLocalidadeFinal.
	 */
	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	/**
	 * @param idLocalidadeFinal O idLocalidadeFinal a ser setado.
	 */
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	/**
	 * @return Retorna o campo idLocalidadeInicial.
	 */
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	/**
	 * @param idLocalidadeInicial O idLocalidadeInicial a ser setado.
	 */
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	/**
	 * @return Retorna o campo idsCapacidadeHidrometro.
	 */
	public String[] getIdsCapacidadeHidrometro() {
		return idsCapacidadeHidrometro;
	}
	/**
	 * @param idsCapacidadeHidrometro O idsCapacidadeHidrometro a ser setado.
	 */
	public void setIdsCapacidadeHidrometro(String[] idsCapacidadeHidrometro) {
		this.idsCapacidadeHidrometro = idsCapacidadeHidrometro;
	}
	/**
	 * @return Retorna o campo idsCategoria.
	 */
	public String[] getIdsCategoria() {
		return idsCategoria;
	}
	/**
	 * @param idsCategoria O idsCategoria a ser setado.
	 */
	public void setIdsCategoria(String[] idsCategoria) {
		this.idsCategoria = idsCategoria;
	}
	/**
	 * @return Retorna o campo idsImovelPerfil.
	 */
	public String[] getIdsImovelPerfil() {
		return idsImovelPerfil;
	}
	/**
	 * @param idsImovelPerfil O idsImovelPerfil a ser setado.
	 */
	public void setIdsImovelPerfil(String[] idsImovelPerfil) {
		this.idsImovelPerfil = idsImovelPerfil;
	}
	/**
	 * @return Retorna o campo idSituacaoLigacaoAgua.
	 */
	public String getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}
	/**
	 * @param idSituacaoLigacaoAgua O idSituacaoLigacaoAgua a ser setado.
	 */
	public void setIdSituacaoLigacaoAgua(String idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}
	/**
	 * @return Retorna o campo idSituacaoLigacaoEsgoto.
	 */
	public String getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}
	/**
	 * @param idSituacaoLigacaoEsgoto O idSituacaoLigacaoEsgoto a ser setado.
	 */
	public void setIdSituacaoLigacaoEsgoto(String idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}
	/**
	 * @return Retorna o campo idsSubCategoria.
	 */
	public String[] getIdsSubCategoria() {
		return idsSubCategoria;
	}
	/**
	 * @param idsSubCategoria O idsSubCategoria a ser setado.
	 */
	public void setIdsSubCategoria(String[] idsSubCategoria) {
		this.idsSubCategoria = idsSubCategoria;
	}
	/**
	 * @return Retorna o campo idsTarifaConsumo.
	 */
	public String[] getIdsTarifaConsumo() {
		return idsTarifaConsumo;
	}
	/**
	 * @param idsTarifaConsumo O idsTarifaConsumo a ser setado.
	 */
	public void setIdsTarifaConsumo(String[] idsTarifaConsumo) {
		this.idsTarifaConsumo = idsTarifaConsumo;
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
	 * @return Retorna o campo intervaloConsumoAguaFinal.
	 */
	public String getIntervaloConsumoAguaFinal() {
		return intervaloConsumoAguaFinal;
	}
	/**
	 * @param intervaloConsumoAguaFinal O intervaloConsumoAguaFinal a ser setado.
	 */
	public void setIntervaloConsumoAguaFinal(String intervaloConsumoAguaFinal) {
		this.intervaloConsumoAguaFinal = intervaloConsumoAguaFinal;
	}
	/**
	 * @return Retorna o campo intervaloConsumoAguaInicial.
	 */
	public String getIntervaloConsumoAguaInicial() {
		return intervaloConsumoAguaInicial;
	}
	/**
	 * @param intervaloConsumoAguaInicial O intervaloConsumoAguaInicial a ser setado.
	 */
	public void setIntervaloConsumoAguaInicial(String intervaloConsumoAguaInicial) {
		this.intervaloConsumoAguaInicial = intervaloConsumoAguaInicial;
	}
	/**
	 * @return Retorna o campo intervaloConsumoEsgotoFinal.
	 */
	public String getIntervaloConsumoEsgotoFinal() {
		return intervaloConsumoEsgotoFinal;
	}
	/**
	 * @param intervaloConsumoEsgotoFinal O intervaloConsumoEsgotoFinal a ser setado.
	 */
	public void setIntervaloConsumoEsgotoFinal(String intervaloConsumoEsgotoFinal) {
		this.intervaloConsumoEsgotoFinal = intervaloConsumoEsgotoFinal;
	}
	/**
	 * @return Retorna o campo intervaloConsumoEsgotoInicial.
	 */
	public String getIntervaloConsumoEsgotoInicial() {
		return intervaloConsumoEsgotoInicial;
	}
	/**
	 * @param intervaloConsumoEsgotoInicial O intervaloConsumoEsgotoInicial a ser setado.
	 */
	public void setIntervaloConsumoEsgotoInicial(
			String intervaloConsumoEsgotoInicial) {
		this.intervaloConsumoEsgotoInicial = intervaloConsumoEsgotoInicial;
	}
	/**
	 * @return Retorna o campo intervaloConsumoResponsavelFinal.
	 */
	public String getIntervaloConsumoResponsavelFinal() {
		return intervaloConsumoResponsavelFinal;
	}
	/**
	 * @param intervaloConsumoResponsavelFinal O intervaloConsumoResponsavelFinal a ser setado.
	 */
	public void setIntervaloConsumoResponsavelFinal(
			String intervaloConsumoResponsavelFinal) {
		this.intervaloConsumoResponsavelFinal = intervaloConsumoResponsavelFinal;
	}
	/**
	 * @return Retorna o campo intervaloConsumoResponsavelInicial.
	 */
	public String getIntervaloConsumoResponsavelInicial() {
		return intervaloConsumoResponsavelInicial;
	}
	/**
	 * @param intervaloConsumoResponsavelInicial O intervaloConsumoResponsavelInicial a ser setado.
	 */
	public void setIntervaloConsumoResponsavelInicial(
			String intervaloConsumoResponsavelInicial) {
		this.intervaloConsumoResponsavelInicial = intervaloConsumoResponsavelInicial;
	}
	/**
	 * @return Retorna o campo intervaloQtdEcoFinal.
	 */
	public String getIntervaloQtdEcoFinal() {
		return intervaloQtdEcoFinal;
	}
	/**
	 * @param intervaloQtdEcoFinal O intervaloQtdEcoFinal a ser setado.
	 */
	public void setIntervaloQtdEcoFinal(String intervaloQtdEcoFinal) {
		this.intervaloQtdEcoFinal = intervaloQtdEcoFinal;
	}
	/**
	 * @return Retorna o campo intervaloQtdEcoInicial.
	 */
	public String getIntervaloQtdEcoInicial() {
		return intervaloQtdEcoInicial;
	}
	/**
	 * @param intervaloQtdEcoInicial O intervaloQtdEcoInicial a ser setado.
	 */
	public void setIntervaloQtdEcoInicial(String intervaloQtdEcoInicial) {
		this.intervaloQtdEcoInicial = intervaloQtdEcoInicial;
	}
	/**
	 * @return Retorna o campo nomeClienteResponsavel.
	 */
	public String getNomeClienteResponsavel() {
		return nomeClienteResponsavel;
	}
	/**
	 * @param nomeClienteResponsavel O nomeClienteResponsavel a ser setado.
	 */
	public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
		this.nomeClienteResponsavel = nomeClienteResponsavel;
	}
	/**
	 * @return Retorna o campo nomeLocalidadeFinal.
	 */
	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}
	/**
	 * @param nomeLocalidadeFinal O nomeLocalidadeFinal a ser setado.
	 */
	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}
	/**
	 * @return Retorna o campo nomeLocalidadeInicial.
	 */
	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}
	/**
	 * @param nomeLocalidadeInicial O nomeLocalidadeInicial a ser setado.
	 */
	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}
	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}
	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}
	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}
	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}
	public String getCodigoRotaInicial() {
		return codigoRotaInicial;
	}
	public void setCodigoRotaInicial(String codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
	}
	public String getCodigoRotaFinal() {
		return codigoRotaFinal;
	}
	public void setCodigoRotaFinal(String codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}
	public String getIdSetorComercialInicial() {
		return idSetorComercialInicial;
	}
	public void setIdSetorComercialInicial(String idSetorComercialInicial) {
		this.idSetorComercialInicial = idSetorComercialInicial;
	}
	public String getIdSetorComercialFinal() {
		return idSetorComercialFinal;
	}
	public void setIdSetorComercialFinal(String idSetorComercialFinal) {
		this.idSetorComercialFinal = idSetorComercialFinal;
	}
	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}
	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}
	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}
	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}
}