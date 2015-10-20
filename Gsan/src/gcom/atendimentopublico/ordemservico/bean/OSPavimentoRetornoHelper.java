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
package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;

import java.io.Serializable;
import java.util.Collection;


/**
 * [UC0639] Filtrar Ordens de Processo de Repavimenta��o
 * 
 * Classe facilitadora para o retorno do filtro a ser usado no manter.
 * 
 * @author Yara Taciane
 * @date 02/06/2008
 */
public class OSPavimentoRetornoHelper  implements Serializable{
	
	private static final long serialVersionUID = 1L;

   
	private OrdemServicoPavimento ordemServicoPavimento; 	
    private OrdemServico ordemServico; 	
	private String endereco;	
	private String motivo;
	
	private Collection collOSTipoPavimentoHelper_Rua;
	private Collection collOSTipoPavimentoHelper_RuaRet;
	
	private String hint1;
	
    /**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna o campo ordemServico.
	 */
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	/**
	 * @param ordemServico O ordemServico a ser setado.
	 */
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	/**
	 * @return Retorna o campo ordemServicoPavimento.
	 */
	public OrdemServicoPavimento getOrdemServicoPavimento() {
		return ordemServicoPavimento;
	}

	/**
	 * @param ordemServicoPavimento O ordemServicoPavimento a ser setado.
	 */
	public void setOrdemServicoPavimento(OrdemServicoPavimento ordemServicoPavimento) {
		this.ordemServicoPavimento = ordemServicoPavimento;
	}

	/**
	 * @return Retorna o campo collOSTipoPavimentoHelper_Rua.
	 */
	public Collection getCollOSTipoPavimentoHelper_Rua() {
		return collOSTipoPavimentoHelper_Rua;
	}

	/**
	 * @param collOSTipoPavimentoHelper_Rua O collOSTipoPavimentoHelper_Rua a ser setado.
	 */
	public void setCollOSTipoPavimentoHelper_Rua(
			Collection collOSTipoPavimentoHelper_Rua) {
		this.collOSTipoPavimentoHelper_Rua = collOSTipoPavimentoHelper_Rua;
	}

	/**
	 * @return Retorna o campo collOSTipoPavimentoHelper_RuaRet.
	 */
	public Collection getCollOSTipoPavimentoHelper_RuaRet() {
		return collOSTipoPavimentoHelper_RuaRet;
	}

	/**
	 * @param collOSTipoPavimentoHelper_RuaRet O collOSTipoPavimentoHelper_RuaRet a ser setado.
	 */
	public void setCollOSTipoPavimentoHelper_RuaRet(
			Collection collOSTipoPavimentoHelper_RuaRet) {
		this.collOSTipoPavimentoHelper_RuaRet = collOSTipoPavimentoHelper_RuaRet;
	}

	/**
	 * @return Returns the motivo.
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo The motivo to set.
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getHint1() {
		return hint1;
	}

	public void setHint1(String hint1) {
		this.hint1 = hint1;
	}

}
