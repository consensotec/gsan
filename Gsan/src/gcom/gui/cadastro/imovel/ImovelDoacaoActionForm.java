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
package gcom.gui.cadastro.imovel;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 22 de Dezembro de 2005
 */
public class ImovelDoacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idImovelDoacao;

	private String idImovel;

	private String idEntidadeBeneficente;

	private String valorDoacao;

	private String dataAdesao;

	private String dataCancelamento;

	private String idUsuarioAdesao;

	private String idUsuarioCancelamento;

	private String dataHoraUltimaAlteracao;
	
	private String inscricaoImovel;
	
	private String quantidadeParcela;
	
	private String quantidadeParcelaMaxima;

	/**
	 * @return Returns the idImovelDoacao.
	 */
	public String getIdImovelDoacao() {
		return idImovelDoacao;
	}

	/**
	 * @param consumoAlto
	 *            the idImovelDoacao to set.
	 */
	public void setIdImovelDoacao(String idImovelDoacao) {
		this.idImovelDoacao = idImovelDoacao;
	}

	/**
	 * @return Returns the idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param consumoEstouro
	 *            the idImovel to set.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Returns the idEntidadeBeneficente.
	 */
	public String getIdEntidadeBeneficente() {
		return idEntidadeBeneficente;
	}

	/**
	 * @param consumoMinimo
	 *            the idEntidadeBeneficente to set.
	 */
	public void setIdEntidadeBeneficente(String idEntidadeBeneficente) {
		this.idEntidadeBeneficente = idEntidadeBeneficente;
	}

	/**
	 * @return Returns the valorDoacao.
	 */
	public String getValorDoacao() {
		return valorDoacao;
	}

	/**
	 * @param descricao
	 *            the valorDoacao to set.
	 */
	public void setValorDoacao(String valorDoacao) {
		this.valorDoacao = valorDoacao;
	}

	/**
	 * @return Returns the dataAdesao.
	 */
	public String getDataAdesao() {
		return dataAdesao;
	}

	/**
	 * @param descricaoAbreviada
	 *            the dataAdesao to set.
	 */
	public void setDataAdesao(String dataAdesao) {
		this.dataAdesao = dataAdesao;
	}

	/**
	 * @return Returns the dataCancelamento.
	 */
	public String getDataCancelamento() {
		return dataCancelamento;
	}

	/**
	 * @param idCategoria
	 *            the dataCancelamento to set.
	 */
	public void setDataCancelamento(String dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	/**
	 * @return Returns the idUsuarioAdesao.
	 */
	public String getIdUsuarioAdesao() {
		return idUsuarioAdesao;
	}

	/**
	 * @param indicadorUso
	 *            the idUsuarioAdesao to set.
	 */
	public void setIdUsuarioAdesao(String idUsuarioAdesao) {
		this.idUsuarioAdesao = idUsuarioAdesao;
	}

	/**
	 * @return Returns the idUsuarioCancelamento.
	 */
	public String getIdUsuarioCancelamento() {
		return idUsuarioCancelamento;
	}

	/**
	 * @param mediaBaixoConsumo
	 *            the idUsuarioCancelamento to set.
	 */
	public void setIdUsuarioCancelamento(String idUsuarioCancelamento) {
		this.idUsuarioCancelamento = idUsuarioCancelamento;
	}

	/**
	 * @return Returns the dataHoraUltimaAlteracao.
	 */
	public String getDataHoraUltimaAlteracao() {
		return dataHoraUltimaAlteracao;
	}

	/**
	 * @param porcentagemMediaBaixoConsumo
	 *            the dataHoraUltimaAlteracao to set.
	 */
	public void setDataHoraUltimaAlteracao(String dataHoraUltimaAlteracao) {
		this.dataHoraUltimaAlteracao = dataHoraUltimaAlteracao;
	}
	
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Returns the quantidadeParcela.
	 */
	public String getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(String quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	
	
	/**
	 * @return Returns the quantidadeParcelaMaxima.
	 */
	public String getQuantidadeParcelaMaxima() {
		return quantidadeParcelaMaxima;
	}

	/**
	 * @param quantidadeParcelaMaxima The quantidadeParcelaMaxima to set.
	 */
	public void setQuantidadeParcelaMaxima(String quantidadeParcelaMaxima) {
		this.quantidadeParcelaMaxima = quantidadeParcelaMaxima;
	}

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 */
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		idImovelDoacao = null;
		idImovel = null;
		idEntidadeBeneficente = null;
		valorDoacao = null;
		dataAdesao = null;
		dataCancelamento = null;
		idUsuarioAdesao = null;
		idUsuarioCancelamento = null;
		dataHoraUltimaAlteracao = null;
		inscricaoImovel = null;
		quantidadeParcela = null;
		quantidadeParcelaMaxima = null;
	}

}