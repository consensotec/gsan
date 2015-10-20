/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Anderson Cabral do Nascimento
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.atualizacaocadastral;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC1447] - Consultar Imóveis no Ambiente Pré-GSAN
 * 
 * @author Arthur Carvalho
 * @date 14/03/2012
 */
public class ConsultarImoveisPreGsanActionForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;
	

	private String indicadorValidarCpfCnpjRF;
	private String idEmpresa;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String idSetorComercial;
	private String descricaoSetorComercial;
	private String idCadastroOcorrencia;
	private String indicadorTipoSelecao;
	private String indicadorSituacaoTodosHabilitado;
	private String cadastrador;
	 
	private Integer[] idQuadra;
	private Integer[] idQuadraSelecionados;
	
	private String imoveisParaAtualizar; 
	private String matriculasGsanAssociadas;
	
	public String getImoveisParaAtualizar() {
		return imoveisParaAtualizar;
	}

	public void setImoveisParaAtualizar(String imoveisParaAtualizar) {
		this.imoveisParaAtualizar = imoveisParaAtualizar;
	}

	
	public void limpar(){
		setDescricaoLocalidade("");
		setDescricaoSetorComercial("");
		setIdEmpresa("-1");
		setIdLocalidade("");
		setIdSetorComercial("");
		setIdQuadra(null);
		setIdQuadraSelecionados(null);
		setIndicadorValidarCpfCnpjRF(null);
		setIdCadastroOcorrencia("-1");
		setImoveisParaAtualizar(null);
		setMatriculasGsanAssociadas(null);
	}
	
	
	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public String getIdCadastroOcorrencia() {
		return idCadastroOcorrencia;
	}

	public void setIdCadastroOcorrencia(String idCadastroOcorrencia) {
		this.idCadastroOcorrencia = idCadastroOcorrencia;
	}

	public String getIndicadorTipoSelecao() {
		return indicadorTipoSelecao;
	}

	public void setIndicadorTipoSelecao(String indicadorTipoSelecao) {
		this.indicadorTipoSelecao = indicadorTipoSelecao;
	}

	public Integer[] getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(Integer[] idQuadra) {
		this.idQuadra = idQuadra;
	}

	public Integer[] getIdQuadraSelecionados() {
		return idQuadraSelecionados;
	}

	public void setIdQuadraSelecionados(Integer[] idQuadraSelecionados) {
		this.idQuadraSelecionados = idQuadraSelecionados;
	}


	public String getIndicadorValidarCpfCnpjRF() {
		return indicadorValidarCpfCnpjRF;
	}


	public void setIndicadorValidarCpfCnpjRF(String indicadorValidarCpfCnpjRF) {
		this.indicadorValidarCpfCnpjRF = indicadorValidarCpfCnpjRF;
	}


	public String getIndicadorSituacaoTodosHabilitado() {
		return indicadorSituacaoTodosHabilitado;
	}


	public void setIndicadorSituacaoTodosHabilitado(
			String indicadorSituacaoTodosHabilitado) {
		this.indicadorSituacaoTodosHabilitado = indicadorSituacaoTodosHabilitado;
	}

	public String getCadastrador() {
		return cadastrador;
	}


	public void setCadastrador(String cadastrador) {
		this.cadastrador = cadastrador;
	}

	public String getMatriculasGsanAssociadas() {
		return matriculasGsanAssociadas;
	}

	public void setMatriculasGsanAssociadas(String matriculasGsanAssociadas) {
		this.matriculasGsanAssociadas = matriculasGsanAssociadas;
	}
	
}

