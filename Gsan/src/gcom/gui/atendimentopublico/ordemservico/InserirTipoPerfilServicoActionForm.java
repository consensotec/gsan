/**
 * 
 */
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
package gcom.gui.atendimentopublico.ordemservico;


import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.util.ConstantesSistema;
import java.util.Date;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * @author Ana Maria
 * @date 31/07/2006
 * 
 */
public class InserirTipoPerfilServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricaoPerfil;
	
	private String abreviaturaPerfil;

	private String quantidadeComponente;
	
	private String equipamentosEspeciais;
	
	private String descricaoEquipamentoEspecial;

	private String veiculoProprio;
	
	/**
	 * @return Retorna o campo descricaoPerfil.
	 */
	public String getDescricaoPerfil() {
		return descricaoPerfil;
	}

	/**
	 * @param descricaoPerfil a ser setado.
	 */
	public void setDescricaoPerfil(String descricaoPerfil) {
		this.descricaoPerfil = descricaoPerfil;
	}

	/**
	 * @return Retorna o campo abreviaturaPerfil.
	 */
	public String getAbreviaturaPerfil() {
		return abreviaturaPerfil;
	}

	/**
	 * @param abreviaturaPerfil a ser setado.
	 */
	public void setAbreviaturaPerfil(String abreviaturaPerfil) {
		this.abreviaturaPerfil = abreviaturaPerfil;
	}
	
	/**
	 * @return Retorna o campo quantidadeComponente.
	 */
	public String getQuantidadeComponente() {
		return quantidadeComponente;
	}

	/**
	 * @param quantidadeComponente a ser setado.
	 */
	public void setQuantidadeComponente(String quantidadeComponente) {
		this.quantidadeComponente = quantidadeComponente;
	}
	
	/**
	 * @return Retorna o campo equipamentoEspecial.
	 */
	public String getEquipamentosEspeciais() {
		return equipamentosEspeciais;
	}

	/**
	 * @param equipamentoEspecial a ser setado.
	 */
	public void setEquipamentosEspeciais(String equipamentosEspeciais) {
		this.equipamentosEspeciais = equipamentosEspeciais;
	}
	
	/**
	 * @return Retorna o campo veiculoProprio.
	 */
	public String getVeiculoProprio() {
		return veiculoProprio;
	}

	/**
	 * @param veiculoProprio a ser setado.
	 */
	public void setVeiculoProprio(String veiculoProprio) {
		this.veiculoProprio = veiculoProprio;
	}
	
	public ServicoPerfilTipo setFormValues(ServicoPerfilTipo servicoPerfilTipo) {
		
		/*
		 * Campos obrigat�rios
		 */
		
		//Descri��o
		servicoPerfilTipo.setDescricao(getDescricaoPerfil());
		servicoPerfilTipo.setDescricaoAbreviada(getAbreviaturaPerfil());		
		servicoPerfilTipo.setComponentesEquipe(Short.parseShort(getQuantidadeComponente()));
		servicoPerfilTipo.setIndicadorVeiculoProprio(Short.parseShort(getVeiculoProprio()));
		servicoPerfilTipo.setUltimaAlteracao(new Date());
		if(getEquipamentosEspeciais() != null && !getEquipamentosEspeciais().equals("")){
		  //Equipamento Especial
		  EquipamentosEspeciais equipamentosEspeciais = new EquipamentosEspeciais();
		  equipamentosEspeciais.setId(Integer.parseInt(getEquipamentosEspeciais()));
		  servicoPerfilTipo.setEquipamentosEspeciais(equipamentosEspeciais);
		}
		/*
		 * Campos opcionais 
		 */

		//data da retirada
		servicoPerfilTipo.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		
		return servicoPerfilTipo;
	}

	public String getDescricaoEquipamentoEspecial() {
		return descricaoEquipamentoEspecial;
	}

	public void setDescricaoEquipamentoEspecial(String descricaoEquipamentoEspecial) {
		this.descricaoEquipamentoEspecial = descricaoEquipamentoEspecial;
	}
}
