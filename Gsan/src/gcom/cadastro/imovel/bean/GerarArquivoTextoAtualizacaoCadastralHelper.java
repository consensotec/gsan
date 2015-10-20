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
package gcom.cadastro.imovel.bean;

import java.io.Serializable;
import java.util.Collection;

/**
 * Esta classe tem a finalidade de encapsular as informa��es necess�rias para inserir 
 * um arquivo texto atualizacao cadastral
 * 
 * @author Ana Maria
 * @date 22/09/2008
 */
public class GerarArquivoTextoAtualizacaoCadastralHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private String descricao;
	
	private Integer idLeiturista;
	
	private Integer idLocalidade;
	
	private Integer setorComercialCD;
	
	private Integer numeroQuadraInicial;
	
	private Integer numeroQuadraFinal;
	
	private Integer rotaCD;
	
	private Integer situacao;
	
	private Integer qtdImovel;
	
	private Collection colecaoImovel;
	
	
	public GerarArquivoTextoAtualizacaoCadastralHelper(){}


	/**
	 * @return Retorna o campo colecaoImovel.
	 */
	public Collection getColecaoImovel() {
		return colecaoImovel;
	}


	/**
	 * @param colecaoImovel O colecaoImovel a ser setado.
	 */
	public void setColecaoImovel(Collection colecaoImovel) {
		this.colecaoImovel = colecaoImovel;
	}


	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}


	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	/**
	 * @return Retorna o campo idLeiturista.
	 */
	public Integer getIdLeiturista() {
		return idLeiturista;
	}


	/**
	 * @param idLeiturista O idLeiturista a ser setado.
	 */
	public void setIdLeiturista(Integer idLeiturista) {
		this.idLeiturista = idLeiturista;
	}


	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}


	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}


	/**
	 * @return Retorna o campo numeroQuadraFinal.
	 */
	public Integer getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}


	/**
	 * @param numeroQuadraFinal O numeroQuadraFinal a ser setado.
	 */
	public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}


	/**
	 * @return Retorna o campo numeroQuadraInicial.
	 */
	public Integer getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}


	/**
	 * @param numeroQuadraInicial O numeroQuadraInicial a ser setado.
	 */
	public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}


	/**
	 * @return Retorna o campo qtdImovel.
	 */
	public Integer getQtdImovel() {
		return qtdImovel;
	}


	/**
	 * @param qtdImovel O qtdImovel a ser setado.
	 */
	public void setQtdImovel(Integer qtdImovel) {
		this.qtdImovel = qtdImovel;
	}


	/**
	 * @return Retorna o campo rotaCD.
	 */
	public Integer getRotaCD() {
		return rotaCD;
	}


	/**
	 * @param rotaCD O rotaCD a ser setado.
	 */
	public void setRotaCD(Integer rotaCD) {
		this.rotaCD = rotaCD;
	}


	/**
	 * @return Retorna o campo setorComercialCD.
	 */
	public Integer getSetorComercialCD() {
		return setorComercialCD;
	}


	/**
	 * @param setorComercialCD O setorComercialCD a ser setado.
	 */
	public void setSetorComercialCD(Integer setorComercialCD) {
		this.setorComercialCD = setorComercialCD;
	}


	/**
	 * @return Retorna o campo situacao.
	 */
	public Integer getSituacao() {
		return situacao;
	}


	/**
	 * @param situacao O situacao a ser setado.
	 */
	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}
	

}
