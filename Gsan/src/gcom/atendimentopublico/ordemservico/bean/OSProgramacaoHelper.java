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

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;

import java.util.Collection;
import java.util.Set;


/**
 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
 * 
 * Classe facilitadora para o retorno do filtro a ser usado no manter.
 * 
 * @author Rafael Pinto
 * @date 18/08/2006
 */
public class OSProgramacaoHelper {

	private String situacao;
	private int diasAtrasoCliente;
	private int diasAtrasoAgencia;
	private String endereco;
	private boolean podeRemover;
	private boolean temAlerta;
	private OrdemServicoProgramacao ordemServicoProgramacao;
	private String alertaEquipeDeServicoPerfilTipo;
	private Collection colecaoAlertaEquipeDeLogradouro;
	private Set<Equipe> colecaoEquipeAssociadaOS;
	
	public boolean isPodeRemover() {
		return podeRemover;
	}

	public void setPodeRemover(boolean podeRemover) {
		this.podeRemover = podeRemover;
	}

	public OSProgramacaoHelper(){}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public int getDiasAtrasoAgencia() {
		return diasAtrasoAgencia;
	}

	public void setDiasAtrasoAgencia(int diasAtrasoAgencia) {
		this.diasAtrasoAgencia = diasAtrasoAgencia;
	}

	public int getDiasAtrasoCliente() {
		return diasAtrasoCliente;
	}

	public void setDiasAtrasoCliente(int diasAtrasoCliente) {
		this.diasAtrasoCliente = diasAtrasoCliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public OrdemServicoProgramacao getOrdemServicoProgramacao() {
		return ordemServicoProgramacao;
	}

	public void setOrdemServicoProgramacao(OrdemServicoProgramacao ordemServicoProgramacao) {
		this.ordemServicoProgramacao = ordemServicoProgramacao;
	}

	public boolean isTemAlerta() {
		return temAlerta;
	}

	public void setTemAlerta(boolean temAlerta) {
		this.temAlerta = temAlerta;
	}

	public String getAlertaEquipeDeServicoPerfilTipo() {
		return alertaEquipeDeServicoPerfilTipo;
	}

	public void setAlertaEquipeDeServicoPerfilTipo(
			String alertaEquipeDeServicoPerfilTipo) {
		this.alertaEquipeDeServicoPerfilTipo = alertaEquipeDeServicoPerfilTipo;
	}

	public Collection getColecaoAlertaEquipeDeLogradouro() {
		return colecaoAlertaEquipeDeLogradouro;
	}

	public void setColecaoAlertaEquipeDeLogradouro(
			Collection colecaoAlertaEquipeDeLogradouro) {
		this.colecaoAlertaEquipeDeLogradouro = colecaoAlertaEquipeDeLogradouro;
	}

	public Set<Equipe> getColecaoEquipeAssociadaOS() {
		return colecaoEquipeAssociadaOS;
	}

	public void setColecaoEquipeAssociadaOS(Set<Equipe> colecaoEquipeAssociadaOS) {
		this.colecaoEquipeAssociadaOS = colecaoEquipeAssociadaOS;
	}


}
