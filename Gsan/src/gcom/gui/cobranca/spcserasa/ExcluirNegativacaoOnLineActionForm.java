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
* Thiago Silva Toscano de Brito
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
package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * @author Thiago Toscano 
 * @date 18/12/2006
 */
public class ExcluirNegativacaoOnLineActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	
	private Collection collNegativador = new ArrayList();
	private String negativador = "";
	private String negativadorAnterior = "";
	private String idImovel  = "";
	private String idImovelAnterior  = "";
	private String inscricaoImovel  = "";
	private String existeImovel = "";
	private String dataHoje = "";
	private String dataEnvio = "";
	
	
	private Collection collMotivoExclusao = new ArrayList();
	private String motivoExclusao = "";
	private String dataExclusao = "";
	private String idUsuario = "";
	private String nomeUsuario = "";
	private String usuarioNaoEncontrada = "";

	/**
	 *
	 * @author Thiago Toscano
	 * @date 22/01/2008
	 *
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);

		collNegativador = new ArrayList();
		negativador = "";
		idImovel  = "";
		inscricaoImovel  = "";
		existeImovel = "";
		idImovelAnterior = "";
		negativadorAnterior = "";
		dataHoje = "";
		dataEnvio = "";

		collMotivoExclusao = new ArrayList();
		motivoExclusao = "";
		dataExclusao = "";
		idUsuario = "";
		nomeUsuario = "";
		usuarioNaoEncontrada = "";
	}
	
	public String getNegativadorAnterior() {
		return negativadorAnterior;
	}

	public void setNegativadorAnterior(String negativadorAnterior) {
		this.negativadorAnterior = negativadorAnterior;
	}

	/**
	 * @return Retorna o campo dataEnvio.
	 */
	public String getDataEnvio() {
		return dataEnvio;
	}

	/**
	 * @param dataEnvio O dataEnvio a ser setado.
	 */
	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	/**
	 * @return Retorna o campo dataHoje.
	 */
	public String getDataHoje() {
		return dataHoje;
	}

	/**
	 * @param dataHoje O dataHoje a ser setado.
	 */
	public void setDataHoje(String dataHoje) {
		this.dataHoje = dataHoje;
	}



	/**
	 * @return Retorna o campo idImovelAnterior.
	 */
	public String getIdImovelAnterior() {
		return idImovelAnterior;
	}

	/**
	 * @param idImovelAnterior O idImovelAnterior a ser setado.
	 */
	public void setIdImovelAnterior(String idImovelAnterior) {
		this.idImovelAnterior = idImovelAnterior;
	}

	/**
	 * @return Retorna o campo collMotivoExclusao.
	 */
	public Collection getCollMotivoExclusao() {
		return collMotivoExclusao;
	}

	/**
	 * @param collMotivoExclusao O collMotivoExclusao a ser setado.
	 */
	public void setCollMotivoExclusao(Collection collMotivoExclusao) {
		this.collMotivoExclusao = collMotivoExclusao;
	}

	/**
	 * @return Retorna o campo dataExclusao.
	 */
	public String getDataExclusao() {
		return dataExclusao;
	}

	/**
	 * @param dataExclusao O dataExclusao a ser setado.
	 */
	public void setDataExclusao(String dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	/**
	 * @return Retorna o campo idUsuario.
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario O idUsuario a ser setado.
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}



	/**
	 * @return Retorna o campo motivoExclusao.
	 */
	public String getMotivoExclusao() {
		return motivoExclusao;
	}



	/**
	 * @param motivoExclusao O motivoExclusao a ser setado.
	 */
	public void setMotivoExclusao(String motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}



	/**
	 * @return Retorna o campo nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}



	/**
	 * @param nomeUsuario O nomeUsuario a ser setado.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}



	/**
	 * @return Retorna o campo usuarioNaoEncontrada.
	 */
	public String getUsuarioNaoEncontrada() {
		return usuarioNaoEncontrada;
	}



	/**
	 * @param usuarioNaoEncontrada O usuarioNaoEncontrada a ser setado.
	 */
	public void setUsuarioNaoEncontrada(String usuarioNaoEncontrada) {
		this.usuarioNaoEncontrada = usuarioNaoEncontrada;
	}

	/**
	 * @return Retorna o campo collNegativador.
	 */
	public Collection getCollNegativador() {
		return collNegativador;
	}

	/**
	 * @param collNegativador O collNegativador a ser setado.
	 */
	public void setCollNegativador(Collection collNegativador) {
		this.collNegativador = collNegativador;
	}

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
	 * @return Retorna o campo existeImovel.
	 */
	public String getExisteImovel() {
		return existeImovel;
	}

	/**
	 * @param existeImovel O existeImovel a ser setado.
	 */
	public void setExisteImovel(String existeImovel) {
		this.existeImovel = existeImovel;
	}

	/**
	 * @return Retorna o campo negativador.
	 */
	public String getNegativador() {
		return negativador;
	}

	/**
	 * @param negativador O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}
}