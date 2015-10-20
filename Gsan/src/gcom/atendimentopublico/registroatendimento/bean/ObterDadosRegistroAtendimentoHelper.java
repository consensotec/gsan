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
package gcom.atendimentopublico.registroatendimento.bean;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.util.Collection;

/**
 * Esta classe tem por finalidade facilitar a forma como ser� retornado o resultado do 
 * [UC0452] Obter Dados do Registro de Atendimento
 * 
 * @author Ana Maria
 * @date 14/08/2006
 * 
 */
public class ObterDadosRegistroAtendimentoHelper {
	
	private RegistroAtendimento registroAtendimento;
	
	private String descricaoSituacaoRA;
	
	private String enderecoOcorrencia;
	
	private UnidadeOrganizacional unidadeAtendimento;
	
	private UnidadeOrganizacional unidadeAtual;
	
	private RegistroAtendimentoSolicitante solicitante;
	
	private Short codigoExistenciaRAAssociado;
	
	private RegistroAtendimento RAAssociado;
	
	private String tituloNumeroRAAssociado;
	
	private String tituloSituacaoRAAssociado;
	
	private String descricaoSituacaoRAAssociado;
	
	private Short codigoRota;
	
	private Integer sequencialRota;
	
	private Collection colecaoRegistroAtendimentoAnexo;
	
	/**
	 * @return Retorna o campo codigoRota.
	 */
	public Short getCodigoRota() {
		return codigoRota;
	}

	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	/**
	 * @return Retorna o campo sequencialRota.
	 */
	public Integer getSequencialRota() {
		return sequencialRota;
	}

	/**
	 * @param sequencialRota O sequencialRota a ser setado.
	 */
	public void setSequencialRota(Integer sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	public String getDescricaoSituacaoRAAssociado() {
		return descricaoSituacaoRAAssociado;
	}

	public void setDescricaoSituacaoRAAssociado(String descricaoSituacaoRAAssociado) {
		this.descricaoSituacaoRAAssociado = descricaoSituacaoRAAssociado;
	}

	public ObterDadosRegistroAtendimentoHelper(){}	

	public UnidadeOrganizacional getUnidadeAtendimento() {
		return unidadeAtendimento;
	}

	public void setUnidadeAtendimento(UnidadeOrganizacional unidadeAtendimento) {
		this.unidadeAtendimento = unidadeAtendimento;
	}

	public UnidadeOrganizacional getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(UnidadeOrganizacional unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public String getEnderecoOcorrencia() {
		return enderecoOcorrencia;
	}

	public void setEnderecoOcorrencia(String enderecoOcorrencia) {
		this.enderecoOcorrencia = enderecoOcorrencia;
	}

	public Short getCodigoExistenciaRAAssociado() {
		return codigoExistenciaRAAssociado;
	}

	public void setCodigoExistenciaRAAssociado(Short codigoExistenciaRAAssociado) {
		this.codigoExistenciaRAAssociado = codigoExistenciaRAAssociado;
	}

	public String getDescricaoSituacaoRA() {
		return descricaoSituacaoRA;
	}

	public void setDescricaoSituacaoRA(String descricaoSituacaoRA) {
		this.descricaoSituacaoRA = descricaoSituacaoRA;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public RegistroAtendimento getRAAssociado() {
		return RAAssociado;
	}

	public void setRAAssociado(RegistroAtendimento associado) {
		RAAssociado = associado;
	}

	public String getTituloNumeroRAAssociado() {
		return tituloNumeroRAAssociado;
	}

	public void setTituloNumeroRAAssociado(String tituloNumeroRAAssociado) {
		this.tituloNumeroRAAssociado = tituloNumeroRAAssociado;
	}

	public String getTituloSituacaoRAAssociado() {
		return tituloSituacaoRAAssociado;
	}

	public void setTituloSituacaoRAAssociado(String tituloSituacaoRAAssociado) {
		this.tituloSituacaoRAAssociado = tituloSituacaoRAAssociado;
	}

	public RegistroAtendimentoSolicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(RegistroAtendimentoSolicitante solicitante) {
		this.solicitante = solicitante;
	}

	public Collection getColecaoRegistroAtendimentoAnexo() {
		return colecaoRegistroAtendimentoAnexo;
	}

	public void setColecaoRegistroAtendimentoAnexo(
			Collection colecaoRegistroAtendimentoAnexo) {
		this.colecaoRegistroAtendimentoAnexo = colecaoRegistroAtendimentoAnexo;
	}	
}
