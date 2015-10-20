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
package gcom.relatorio.seguranca;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rodrigo Cabral
 * @version 1.0
 */

public class RelatorioResultadoPesquisaConsultaCadastroCdlBean implements RelatorioBean {
	
	private String periodoAcessoInicial;
	
	private String periodoAcessoFinal;
	
	private String loginUsuario;
	
	private String codigoCliente;
	
	private String cpfCliente; 
	
	private String cnpjCliente;
	
	private String nomeCliente;
	
	private String acaoOperador;
	
	private String logradouroCliente;
	
	private String numeroImovelCliente;
	
	private String bairroCliente;
	
	private String complementoEnderecoCliente;
	
	private String cidadeCliente;
	
	private String uf;
	
	private String telefone;

	public RelatorioResultadoPesquisaConsultaCadastroCdlBean(
			String loginUsuario, String codigoCliente, String cpfCliente,
			String cnpjCliente, String nomeCliente, String acaoOperador,
			String logradouroCliente, String numeroImovelCliente,
			String bairroCliente, String complementoEnderecoCliente,
			String cidadeCliente, String uf, String telefone) {
		super();
		this.loginUsuario = loginUsuario;
		this.codigoCliente = codigoCliente;
		this.cpfCliente = cpfCliente;
		this.cnpjCliente = cnpjCliente;
		this.nomeCliente = nomeCliente;
		this.acaoOperador = acaoOperador;
		this.logradouroCliente = logradouroCliente;
		this.numeroImovelCliente = numeroImovelCliente;
		this.bairroCliente = bairroCliente;
		this.complementoEnderecoCliente = complementoEnderecoCliente;
		this.cidadeCliente = cidadeCliente;
		this.uf = uf;
		this.telefone = telefone;
	}
	
	
	public RelatorioResultadoPesquisaConsultaCadastroCdlBean(
			String periodoAcessoInicial, String periodoAcessoFinal,
			String loginUsuario, String codigoCliente, String cpfCliente,
			String cnpjCliente, String nomeCliente, String acaoOperador,
			String logradouroCliente, String numeroImovelCliente,
			String bairroCliente, String complementoEnderecoCliente,
			String cidadeCliente, String uf, String telefone) {
		super();
		this.periodoAcessoInicial = periodoAcessoInicial;
		this.periodoAcessoFinal = periodoAcessoFinal;
		this.loginUsuario = loginUsuario;
		this.codigoCliente = codigoCliente;
		this.cpfCliente = cpfCliente;
		this.cnpjCliente = cnpjCliente;
		this.nomeCliente = nomeCliente;
		this.acaoOperador = acaoOperador;
		this.logradouroCliente = logradouroCliente;
		this.numeroImovelCliente = numeroImovelCliente;
		this.bairroCliente = bairroCliente;
		this.complementoEnderecoCliente = complementoEnderecoCliente;
		this.cidadeCliente = cidadeCliente;
		this.uf = uf;
		this.telefone = telefone;
	}

	public String getPeriodoAcessoInicial() {
		return periodoAcessoInicial;
	}

	public void setPeriodoAcessoInicial(String periodoAcessoInicial) {
		this.periodoAcessoInicial = periodoAcessoInicial;
	}

	public String getPeriodoAcessoFinal() {
		return periodoAcessoFinal;
	}

	public void setPeriodoAcessoFinal(String periodoAcessoFinal) {
		this.periodoAcessoFinal = periodoAcessoFinal;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getAcaoOperador() {
		return acaoOperador;
	}

	public void setAcaoOperador(String acaoOperador) {
		this.acaoOperador = acaoOperador;
	}

	public String getLogradouroCliente() {
		return logradouroCliente;
	}

	public void setLogradouroCliente(String logradouroCliente) {
		this.logradouroCliente = logradouroCliente;
	}

	public String getNumeroImovelCliente() {
		return numeroImovelCliente;
	}

	public void setNumeroImovelCliente(String numeroImovelCliente) {
		this.numeroImovelCliente = numeroImovelCliente;
	}

	public String getBairroCliente() {
		return bairroCliente;
	}

	public void setBairroCliente(String bairroCliente) {
		this.bairroCliente = bairroCliente;
	}

	public String getComplementoEnderecoCliente() {
		return complementoEnderecoCliente;
	}

	public void setComplementoEnderecoCliente(String complementoEnderecoCliente) {
		this.complementoEnderecoCliente = complementoEnderecoCliente;
	}

	public String getCidadeCliente() {
		return cidadeCliente;
	}

	public void setCidadeCliente(String cidadeCliente) {
		this.cidadeCliente = cidadeCliente;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	


}