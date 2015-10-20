/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.gui.relatorio.cadastro.imovel;


import org.apache.struts.action.ActionForm;

/**
 * [UC0979] Gerar Relat�rio de Im�veis em Programas Especiais
 * 
 * @author Hugo Leonardo
 *
 * @date 14/01/2010
 */

public class GerarRelatorioImoveisProgramasEspeciaisActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String mesAnoReferencia;
	private String perfilImovel;
	private String tipo;
	private String opcaoTotalizacao;
	private String regiaoDesenvolvimento;
	private String nomeRegiaoDesenvolvimento;
	//private String unidadeNegocio;
	//private String nomeUnidadeNegocio;
	private String idLocalidade;
	private String nomeLocalidade;

	public void reset(){
		
		this.mesAnoReferencia = null;
		this.perfilImovel = null;
		this.tipo = null;
		this.opcaoTotalizacao = null;
		//this.unidadeNegocio = null;
		//this.nomeUnidadeNegocio = null;
		this.idLocalidade = null;
		this.nomeLocalidade = null;
	}

	/**
	 * @return Returns the idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade The idLocalidade to set.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Returns the mesAnoReferencia.
	 */
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	/**
	 * @param mesAnoReferencia The mesAnoReferencia to set.
	 */
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	/**
	 * @return Returns the nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	/**
	 * @param nomeLocalidade The nomeLocalidade to set.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	/**
	 * @return Returns the opcaoTotalizacao.
	 */
	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	/**
	 * @param opcaoTotalizacao The opcaoTotalizacao to set.
	 */
	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	/**
	 * @return Returns the perfilImovel.
	 */
	public String getPerfilImovel() {
		return perfilImovel;
	}

	/**
	 * @param perfilImovel The perfilImovel to set.
	 */
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}


	/**
	 * @return Returns the tipo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo The tipo to set.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomeRegiaoDesenvolvimento() {
		return nomeRegiaoDesenvolvimento;
	}

	public void setNomeRegiaoDesenvolvimento(String nomeRegiaoDesenvolvimento) {
		this.nomeRegiaoDesenvolvimento = nomeRegiaoDesenvolvimento;
	}

	public String getRegiaoDesenvolvimento() {
		return regiaoDesenvolvimento;
	}

	public void setRegiaoDesenvolvimento(String regiaoDesenvolvimento) {
		this.regiaoDesenvolvimento = regiaoDesenvolvimento;
	}
	
	
}