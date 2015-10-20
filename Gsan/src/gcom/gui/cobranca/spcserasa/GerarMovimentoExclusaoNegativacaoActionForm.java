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
 * Esta classe tem por finalidade gerar o formul�rio que receber� os par�metros
 * para realiza��o da inser��o de um Gera��o da Movimentacao da Negativacao
 * 
 * @author Thiago Toscano 
 * @date 18/12/2006
 */
public class GerarMovimentoExclusaoNegativacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String opcao;
	private String[] negativadores;
	private Collection collNegativadores;

	private String codigoMovimento;
	private String descricaoMovimento;
	private Collection collMovimento;
	
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
	 * @author thiago
	 * @date 19/12/2007
	 *
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);

		
		opcao = "";
		negativadores = new String[0];
		collNegativadores = new ArrayList();

		codigoMovimento = "";
		descricaoMovimento = "";		
		collMovimento= new ArrayList();

	}
	
	/**
	 * @return Retorna o campo collMovimento.
	 */
	public Collection getCollMovimento() {
		return collMovimento;
	}

	/**
	 * @param collMovimento O collMovimento a ser setado.
	 */
	public void setCollMovimento(Collection collMovimento) {
		this.collMovimento = collMovimento;
	}


	/**
	 * @return Retorna o campo codigoMovimento.
	 */
	public String getCodigoMovimento() {
		return codigoMovimento;
	}
	/**
	 * @param codigoMovimento O codigoMovimento a ser setado.
	 */
	public void setCodigoMovimento(String codigoMovimento) {
		this.codigoMovimento = codigoMovimento;
	}
	/**
	 * @return Retorna o campo collNegativadores.
	 */
	public Collection getCollNegativadores() {
		return collNegativadores;
	}
	/**
	 * @param collNegativadores O collNegativadores a ser setado.
	 */
	public void setCollNegativadores(Collection collNegativadores) {
		this.collNegativadores = collNegativadores;
	}
	/**
	 * @return Retorna o campo descricaoMovimento.
	 */
	public String getDescricaoMovimento() {
		return descricaoMovimento;
	}
	/**
	 * @param descricaoMovimento O descricaoMovimento a ser setado.
	 */
	public void setDescricaoMovimento(String descricaoMovimento) {
		this.descricaoMovimento = descricaoMovimento;
	}
	/**
	 * @return Retorna o campo negativadores.
	 */
	public String[] getNegativadores() {
		return negativadores;
	}
	/**
	 * @param negativadores O negativadores a ser setado.
	 */
	public void setNegativadores(String[] negativadores) {
		this.negativadores = negativadores;
	}
	/**
	 * @return Retorna o campo opcao.
	 */
	public String getOpcao() {
		return opcao;
	}
	/**
	 * @param opcao O opcao a ser setado.
	 */
	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}
}