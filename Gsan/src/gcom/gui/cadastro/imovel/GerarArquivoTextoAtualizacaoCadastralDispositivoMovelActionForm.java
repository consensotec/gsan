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

import org.apache.struts.action.ActionForm;

public class GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

    private String localidade;
	private String nomeLocalidade;

	private String setorComercialID;
	private String setorComercialCD;
	private String nomeSetorComercial;

	private String quadraInicial;
	private String idQuadraInicial;
	
	private String quadraFinal;
	private String idQuadraFinal;

	private String idRota;
	private String codigoRota;
	
	private String idImovel;
	private String inscricaoImovel;
	
	private Integer tamanhoColecaoImovel;
	private String descricaoArquivo;
	private String idLeiturista;
	private String nomeLeiturista;
	private String idSituacaoTransmissao;
	
	
	
	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	/**
	 * @param inscricaoImovel O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	/**
	 * @return Retorna o campo idQuadraInicial.
	 */
	public String getIdQuadraInicial() {
		return idQuadraInicial;
	}
	/**
	 * @param idQuadraInicial O idQuadraInicial a ser setado.
	 */
	public void setIdQuadraInicial(String idQuadraInicial) {
		this.idQuadraInicial = idQuadraInicial;
	}
	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}
	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	/**
	 * @return Retorna o campo nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	/**
	 * @param nomeLocalidade O nomeLocalidade a ser setado.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	/**
	 * @return Retorna o campo nomeSetorComercial.
	 */
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}
	/**
	 * @param nomeSetorComercial O nomeSetorComercial a ser setado.
	 */
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}
	/**
	 * @return Retorna o campo quadraInicial.
	 */
	public String getQuadraInicial() {
		return quadraInicial;
	}
	/**
	 * @param quadraInicial O quadraInicial a ser setado.
	 */
	public void setQuadraInicial(String quadraInicial) {
		this.quadraInicial = quadraInicial;
	}

	/**
	 * @return Retorna o campo idQuadraFinal.
	 */
	public String getIdQuadraFinal() {
		return idQuadraFinal;
	}
	/**
	 * @param idQuadraFinal O idQuadraFinal a ser setado.
	 */
	public void setIdQuadraFinal(String idQuadraFinal) {
		this.idQuadraFinal = idQuadraFinal;
	}
	/**
	 * @return Retorna o campo quadraFinal.
	 */
	public String getQuadraFinal() {
		return quadraFinal;
	}
	/**
	 * @param quadraFinal O quadraFinal a ser setado.
	 */
	public void setQuadraFinal(String quadraFinal) {
		this.quadraFinal = quadraFinal;
	}
	/**
	 * @return Retorna o campo setorComercialCD.
	 */
	public String getSetorComercialCD() {
		return setorComercialCD;
	}
	/**
	 * @param setorComercialCD O setorComercialCD a ser setado.
	 */
	public void setSetorComercialCD(String setorComercialCD) {
		this.setorComercialCD = setorComercialCD;
	}
	/**
	 * @return Retorna o campo setorComercialID.
	 */
	public String getSetorComercialID() {
		return setorComercialID;
	}
	/**
	 * @param setorComercialID O setorComercialID a ser setado.
	 */
	public void setSetorComercialID(String setorComercialID) {
		this.setorComercialID = setorComercialID;
	}
	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}
	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	/**
	 * @return Retorna o campo codigoRota.
	 */
	public String getCodigoRota() {
		return codigoRota;
	}
	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}
	/**
	 * @return Retorna o campo idRota.
	 */
	public String getIdRota() {
		return idRota;
	}
	/**
	 * @param idRota O idRota a ser setado.
	 */
	public void setIdRota(String idRota) {
		this.idRota = idRota;
	}
	/**
	 * @return Retorna o campo descricaoArquivo.
	 */
	public String getDescricaoArquivo() {
		return descricaoArquivo;
	}
	/**
	 * @param descricaoArquivo O descricaoArquivo a ser setado.
	 */
	public void setDescricaoArquivo(String descricaoArquivo) {
		this.descricaoArquivo = descricaoArquivo;
	}
	/**
	 * @return Retorna o campo idSituacaoTransmissao.
	 */
	public String getIdSituacaoTransmissao() {
		return idSituacaoTransmissao;
	}
	/**
	 * @param idSituacaoTransmissao O idSituacaoTransmissao a ser setado.
	 */
	public void setIdSituacaoTransmissao(String idSituacaoTransmissao) {
		this.idSituacaoTransmissao = idSituacaoTransmissao;
	}
	/**
	 * @return Retorna o campo tamanhoColecaoImovel.
	 */
	public Integer getTamanhoColecaoImovel() {
		return tamanhoColecaoImovel;
	}
	/**
	 * @param tamanhoColecaoImovel O tamanhoColecaoImovel a ser setado.
	 */
	public void setTamanhoColecaoImovel(Integer tamanhoColecaoImovel) {
		this.tamanhoColecaoImovel = tamanhoColecaoImovel;
	}
	/**
	 * @return Retorna o campo idLeiturista.
	 */
	public String getIdLeiturista() {
		return idLeiturista;
	}
	/**
	 * @param idLeiturista O idLeiturista a ser setado.
	 */
	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}
	/**
	 * @return Retorna o campo nomeLeiturista.
	 */
	public String getNomeLeiturista() {
		return nomeLeiturista;
	}
	/**
	 * @param nomeLeiturista O nomeLeiturista a ser setado.
	 */
	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}



}