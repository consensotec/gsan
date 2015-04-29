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
package gsan.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class ConsultarDebitoClienteActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoCliente;
	
	private String nomeCliente;
	
	private String enderecoCliente;
	
	private String clienteFone;
	
	private String cpfCnpj;
	
	private String tipoRelacao;

	private String referenciaInicial;

	private String referenciaFinal;

	private String dataVencimentoInicial;

	private String dataVencimentoFinal;
	
	private String ramoAtividade;
	
	private String profissao;
	
	private String logradouro;

	private String logradouroTipo;
	
	private String logradouroTitulo;
	
	private String enderecoReferencia;
	
	private String bairro;
	
	private String bairroMunicipio;
	
	private String municipioUnidadeFederacao;
	
	private String cep;
	
	private String cepMunicipio;
	
	private String cepSigla;
	
	private String clienteTipo;
	
	private String descricaoTipoRelacao;
	
	private String responsavel;
	
	private String codigoClienteSuperior;
	
	private String nomeClienteSuperior;
	
	private String indicadorIncluirAcrescimosImpontualidade;
	
	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public String getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}

	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}

	public String getReferenciaFinal() {
		return referenciaFinal;
	}

	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}

	public String getReferenciaInicial() {
		return referenciaInicial;
	}

	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public String getClienteFone() {
		return clienteFone;
	}

	public void setClienteFone(String clienteFone) {
		this.clienteFone = clienteFone;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getLogradouroTipo() {
		return logradouroTipo;
	}

	public void setLogradouroTipo(String logradouroTipo) {
		this.logradouroTipo = logradouroTipo;
	}

	public String getLogradouroTitulo() {
		return logradouroTitulo;
	}

	public void setLogradouroTitulo(String logradouroTitulo) {
		this.logradouroTitulo = logradouroTitulo;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getBairroMunicipio() {
		return bairroMunicipio;
	}

	public void setBairroMunicipio(String bairroMunicipio) {
		this.bairroMunicipio = bairroMunicipio;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCepMunicipio() {
		return cepMunicipio;
	}

	public void setCepMunicipio(String cepMunicipio) {
		this.cepMunicipio = cepMunicipio;
	}

	public String getCepSigla() {
		return cepSigla;
	}

	public void setCepSigla(String cepSigla) {
		this.cepSigla = cepSigla;
	}

	public String getEnderecoReferencia() {
		return enderecoReferencia;
	}

	public void setEnderecoReferencia(String enderecoReferencia) {
		this.enderecoReferencia = enderecoReferencia;
	}

	public String getMunicipioUnidadeFederacao() {
		return municipioUnidadeFederacao;
	}

	public void setMunicipioUnidadeFederacao(String municipioUnidadeFederacao) {
		this.municipioUnidadeFederacao = municipioUnidadeFederacao;
	}

	public String getClienteTipo() {
		return clienteTipo;
	}

	public void setClienteTipo(String clienteTipo) {
		this.clienteTipo = clienteTipo;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getDescricaoTipoRelacao() {
		return descricaoTipoRelacao;
	}

	public void setDescricaoTipoRelacao(String descricaoTipoRelacao) {
		this.descricaoTipoRelacao = descricaoTipoRelacao;
	}

	/**
	 * @return Retorna o campo responsavel.
	 */
	public String getResponsavel() {
		return responsavel;
	}

	/**
	 * @param responsavel O responsavel a ser setado.
	 */
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	/**
	 * @return Retorna o campo codigoClienteSuperior.
	 */
	public String getCodigoClienteSuperior() {
		return codigoClienteSuperior;
	}

	/**
	 * @param codigoClienteSuperior O codigoClienteSuperior a ser setado.
	 */
	public void setCodigoClienteSuperior(String codigoClienteSuperior) {
		this.codigoClienteSuperior = codigoClienteSuperior;
	}

	/**
	 * @return Retorna o campo nomeClienteSuperior.
	 */
	public String getNomeClienteSuperior() {
		return nomeClienteSuperior;
	}

	/**
	 * @param nomeClienteSuperior O nomeClienteSuperior a ser setado.
	 */
	public void setNomeClienteSuperior(String nomeClienteSuperior) {
		this.nomeClienteSuperior = nomeClienteSuperior;
	}

	public String getIndicadorIncluirAcrescimosImpontualidade() {
		return indicadorIncluirAcrescimosImpontualidade;
	}

	public void setIndicadorIncluirAcrescimosImpontualidade(
			String indicadorIncluirAcrescimosImpontualidade) {
		this.indicadorIncluirAcrescimosImpontualidade = indicadorIncluirAcrescimosImpontualidade;
	}
	
}
