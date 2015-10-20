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
* Thiago Vieira de Melo
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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ConsultarDadosInclusoesNegativacaoComandoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idSelecionado;
	
	private String negativador;
	
	private String quantidadeInclusoes;
	
	private String valorTotalDebito;
	
	private String quantidadeItensIncluidos;
	
	private Collection collNegativadorMovimentoReg;

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	
    	this.idSelecionado = "";
    	this.negativador = "";
    	this.quantidadeInclusoes = "";
    	this.valorTotalDebito = "";
    	this.quantidadeItensIncluidos = "";
    	this.collNegativadorMovimentoReg = new ArrayList();
    }

	/**
	 * @return Retorna o campo idSelecionado.
	 */
	public String getIdSelecionado() {
		return idSelecionado;
	}

	/**
	 * @param idSelecionado O idSelecionado a ser setado.
	 */
	public void setIdSelecionado(String idSelecionado) {
		this.idSelecionado = idSelecionado;
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

	/**
	 * @return Retorna o campo quantidadeInclusoes.
	 */
	public String getQuantidadeInclusoes() {
		return quantidadeInclusoes;
	}

	/**
	 * @param quantidadeInclusoes O quantidadeInclusoes a ser setado.
	 */
	public void setQuantidadeInclusoes(String quantidadeInclusoes) {
		this.quantidadeInclusoes = quantidadeInclusoes;
	}

	/**
	 * @return Retorna o campo quantidadeItensIncluidos.
	 */
	public String getQuantidadeItensIncluidos() {
		return quantidadeItensIncluidos;
	}

	/**
	 * @param quantidadeItensIncluidos O quantidadeItensIncluidos a ser setado.
	 */
	public void setQuantidadeItensIncluidos(String quantidadeItensIncluidos) {
		this.quantidadeItensIncluidos = quantidadeItensIncluidos;
	}

	/**
	 * @return Retorna o campo valorTotalDebito.
	 */
	public String getValorTotalDebito() {
		return valorTotalDebito;
	}

	/**
	 * @param valorTotalDebito O valorTotalDebito a ser setado.
	 */
	public void setValorTotalDebito(String valorTotalDebito) {
		this.valorTotalDebito = valorTotalDebito;
	}

	/**
	 * @return Retorna o campo collNegativadorMovimentoReg.
	 */
	public Collection getCollNegativadorMovimentoReg() {
		return collNegativadorMovimentoReg;
	}

	/**
	 * @param collNegativadorMovimentoReg O collNegativadorMovimentoReg a ser setado.
	 */
	public void setCollNegativadorMovimentoReg(
			Collection collNegativadorMovimentoReg) {
		this.collNegativadorMovimentoReg = collNegativadorMovimentoReg;
	}


}