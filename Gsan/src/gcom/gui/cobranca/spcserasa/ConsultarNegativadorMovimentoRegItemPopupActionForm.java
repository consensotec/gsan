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
package gcom.gui.cobranca.spcserasa;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0674] Pesquisar Movimento Negativador
 * 
 * @author Yara Taciane
 * @date 27/12/2007
 */
public class ConsultarNegativadorMovimentoRegItemPopupActionForm extends
		ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String idNegativadorMovimentoRegItem;

	private String negativador;
	private String matriculaImovel;
	private String inscricao;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	
	/**
	 * @return Retorna o campo idNegativadorMovimentoRegItem.
	 */
	public String getIdNegativadorMovimentoRegItem() {
		return idNegativadorMovimentoRegItem;
	}

	/**
	 * @param idNegativadorMovimentoRegItem
	 *            O idNegativadorMovimentoRegItem a ser setado.
	 */
	public void setIdNegativadorMovimentoRegItem(
			String idNegativadorMovimentoRegItem) {
		this.idNegativadorMovimentoRegItem = idNegativadorMovimentoRegItem;
	}

	/**
	 * @return Retorna o campo negativador.
	 */
	public String getNegativador() {
		return negativador;
	}

	/**
	 * @param negativador
	 *            O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}

	
	
	
	/**
	 * @return Retorna o campo inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}

	/**
	 * @param inscricao O inscricao a ser setado.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	/**
	 * @return Retorna o campo matriculaImovel.
	 */
	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	/**
	 * @param matriculaImovel O matriculaImovel a ser setado.
	 */
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	/**
	 * @return Retorna o campo situacaoLigacaoAgua.
	 */
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	/**
	 * @param situacaoLigacaoAgua O situacaoLigacaoAgua a ser setado.
	 */
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	/**
	 * @return Retorna o campo situacaoLigacaoEsgoto.
	 */
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	/**
	 * @param situacaoLigacaoEsgoto O situacaoLigacaoEsgoto a ser setado.
	 */
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	@Override
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		super.reset(arg0, arg1);

		this.idNegativadorMovimentoRegItem = "";
		this.negativador = "";
		this.matriculaImovel= "";
		this.inscricao= "";
		this.situacaoLigacaoAgua= "";
		this.situacaoLigacaoEsgoto= "";

	}

}
