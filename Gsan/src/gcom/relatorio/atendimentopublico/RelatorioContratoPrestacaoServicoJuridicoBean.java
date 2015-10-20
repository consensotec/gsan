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
package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * Bean respons�vel de pegar os parametros que ser�o exibidos na parte de detail
 * do relat�rio.
 * 
 * @author Rafael Corr�a
 * @created 08/08/2006
 */
/**
 * @author Administrador
 *
 */
public class RelatorioContratoPrestacaoServicoJuridicoBean implements RelatorioBean {

	public String nomePresidente;
	public String cpfPresidente;
	public String rgPresidente;
	public String profissaoPresidente;
	public String nomeDiretor;
	public String cpfDiretor;
	public String rgDiretor;
	public String profissaoDiretor;
	public String nomeUsuario;
	public String cnpjUsuario;
	public String enderecoImovel;
	public String matriculaImovel;
	public String nomeRepresentante;
	public String cpfRepresentante;
	public String rgRepresentante;
	public String municipio;
	public String data;
	public String numeroPagina;
	public String esferaPoder;
	public String numeroContrato;

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(String esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public String getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(String numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public String getCnpjUsuario() {
		return cnpjUsuario;
	}

	public void setCnpjUsuario(String cnpjUsuario) {
		this.cnpjUsuario = cnpjUsuario;
	}

	public String getCpfDiretor() {
		return cpfDiretor;
	}

	public void setCpfDiretor(String cpfDiretor) {
		this.cpfDiretor = cpfDiretor;
	}

	public String getCpfPresidente() {
		return cpfPresidente;
	}

	public void setCpfPresidente(String cpfPresidente) {
		this.cpfPresidente = cpfPresidente;
	}

	public String getCpfRepresentante() {
		return cpfRepresentante;
	}

	public void setCpfRepresentante(String cpfRepresentante) {
		this.cpfRepresentante = cpfRepresentante;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getNomeDiretor() {
		return nomeDiretor;
	}

	public void setNomeDiretor(String nomeDiretor) {
		this.nomeDiretor = nomeDiretor;
	}

	public String getNomePresidente() {
		return nomePresidente;
	}

	public void setNomePresidente(String nomePresidente) {
		this.nomePresidente = nomePresidente;
	}

	public String getNomeRepresentante() {
		return nomeRepresentante;
	}

	public void setNomeRepresentante(String nomeRepresentante) {
		this.nomeRepresentante = nomeRepresentante;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getProfissaoDiretor() {
		return profissaoDiretor;
	}

	public void setProfissaoDiretor(String profissaoDiretor) {
		this.profissaoDiretor = profissaoDiretor;
	}

	public String getProfissaoPresidente() {
		return profissaoPresidente;
	}

	public void setProfissaoPresidente(String profissaoPresidente) {
		this.profissaoPresidente = profissaoPresidente;
	}

	public String getRgDiretor() {
		return rgDiretor;
	}

	public void setRgDiretor(String rgDiretor) {
		this.rgDiretor = rgDiretor;
	}

	public String getRgPresidente() {
		return rgPresidente;
	}

	public void setRgPresidente(String rgPresidente) {
		this.rgPresidente = rgPresidente;
	}

	public String getRgRepresentante() {
		return rgRepresentante;
	}

	public void setRgRepresentante(String rgRepresentante) {
		this.rgRepresentante = rgRepresentante;
	}

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioContratoPrestacaoServicoJuridicoBean(
			String nomePresidente,
			String cpfPresidente,
			String rgPresidente,
			String profissaoPresidente,
			String nomeDiretor,
			String cpfDiretor,
			String rgDiretor,
			String profissaoDiretor,
			String nomeUsuario,
			String cnpjUsuario,
			String enderecoImovel,
			String matriculaImovel,
			String nomeRepresentante,
			String cpfRepresentante,
			String rgRepresentante,
			String municipio,
			String data, 
			String esferaPoder,
			String numeroContrato){
		
		this.nomePresidente = nomePresidente;
		this.cpfPresidente = cpfPresidente;
		this.rgPresidente = rgPresidente;
		this.profissaoPresidente = profissaoPresidente;
		this.nomeDiretor = nomeDiretor;
		this.cpfDiretor = cpfDiretor;
		this.rgDiretor = rgDiretor;
		this.profissaoDiretor = profissaoDiretor;
		this.nomeUsuario = nomeUsuario;
		this.cnpjUsuario = cnpjUsuario;
		this.enderecoImovel = enderecoImovel;
		this.matriculaImovel = matriculaImovel;
		this.nomeRepresentante = nomeRepresentante;
		this.cpfRepresentante = cpfRepresentante;
		this.rgRepresentante  = rgRepresentante;
		this.municipio = municipio;
		this.data = data;
		this.esferaPoder = esferaPoder;
		this.numeroContrato = numeroContrato;
	}
	
	public RelatorioContratoPrestacaoServicoJuridicoBean(String numeroPagina){
		this.numeroPagina = numeroPagina;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
}
