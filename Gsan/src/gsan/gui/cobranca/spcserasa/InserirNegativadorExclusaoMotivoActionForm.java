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
 * Yara Taciane de Souza
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
package gsan.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;


import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form respons�vel pelas propiedades do caso de uso de inserir o
 * motivo da exclus�o do negativador.
 * 
 * @author Yara Taciane de Souza
 * @date 02/01/2008
 */
public class InserirNegativadorExclusaoMotivoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String idNegativadorExclusaoMotivo;

	private String descricaoExclusaoMotivo;

	private String codigoMotivo;

	private String indicadorUso;

	private String ultimaAlteracao;

	private String idNegativador;

	private Collection collNegativador;

	private String idCobrancaDebitoSituacao;

	private Collection colecaoCobrancaDebitoSituacao;


	// private Set negativadorMovimentoReg;

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descri��o sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descri��o sobre o fluxo secund�rio>
	 * 
	 * <Identificador e nome do fluxo secund�rio>
	 * 
	 * @author Yara Taciane
	 * @date 02/01/2008
	 * 
	 * @param arg0
	 * @param arg1
	 */
	@Override
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		// TODO Auto-generated method stub
		super.reset(arg0, arg1);

		this.idNegativadorExclusaoMotivo = "";
		this.codigoMotivo = "";
		this.descricaoExclusaoMotivo = "";
		this.indicadorUso = "";
		this.ultimaAlteracao = "";
		this.idNegativador = "";
		this.collNegativador = new ArrayList();
		this.idCobrancaDebitoSituacao = "";
		this.colecaoCobrancaDebitoSituacao = new ArrayList();
		this.ultimaAlteracao = "";

	}

	

	/**
	 * @return Retorna o campo colecaoCobrancaDebitoSituacao.
	 */
	public Collection getColecaoCobrancaDebitoSituacao() {
		return colecaoCobrancaDebitoSituacao;
	}



	/**
	 * @param colecaoCobrancaDebitoSituacao O colecaoCobrancaDebitoSituacao a ser setado.
	 */
	public void setColecaoCobrancaDebitoSituacao(
			Collection colecaoCobrancaDebitoSituacao) {
		this.colecaoCobrancaDebitoSituacao = colecaoCobrancaDebitoSituacao;
	}



	/**
	 * @return Retorna o campo collNegativador.
	 */
	public Collection getCollNegativador() {
		return collNegativador;
	}

	/**
	 * @param collNegativador
	 *            O collNegativador a ser setado.
	 */
	public void setCollNegativador(Collection collNegativador) {
		this.collNegativador = collNegativador;
	}

	/**
	 * @return Retorna o campo descricaoExclusaoMotivo.
	 */
	public String getDescricaoExclusaoMotivo() {
		return descricaoExclusaoMotivo;
	}

	/**
	 * @param descricaoExclusaoMotivo
	 *            O descricaoExclusaoMotivo a ser setado.
	 */
	public void setDescricaoExclusaoMotivo(String descricaoExclusaoMotivo) {
		this.descricaoExclusaoMotivo = descricaoExclusaoMotivo;
	}

	/**
	 * @return Retorna o campo idCobrancaDebitoSituacao.
	 */
	public String getIdCobrancaDebitoSituacao() {
		return idCobrancaDebitoSituacao;
	}

	/**
	 * @param idCobrancaDebitoSituacao
	 *            O idCobrancaDebitoSituacao a ser setado.
	 */
	public void setIdCobrancaDebitoSituacao(String idCobrancaDebitoSituacao) {
		this.idCobrancaDebitoSituacao = idCobrancaDebitoSituacao;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador
	 *            O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Retorna o campo idNegativadorExclusaoMotivo.
	 */
	public String getIdNegativadorExclusaoMotivo() {
		return idNegativadorExclusaoMotivo;
	}

	/**
	 * @param idNegativadorExclusaoMotivo
	 *            O idNegativadorExclusaoMotivo a ser setado.
	 */
	public void setIdNegativadorExclusaoMotivo(
			String idNegativadorExclusaoMotivo) {
		this.idNegativadorExclusaoMotivo = idNegativadorExclusaoMotivo;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso
	 *            O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao
	 *            O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo codigoMotivo.
	 */
	public String getCodigoMotivo() {
		return codigoMotivo;
	}

	/**
	 * @param codigoMotivo O codigoMotivo a ser setado.
	 */
	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}
	
	

}