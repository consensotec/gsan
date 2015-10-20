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
package gcom.cadastro.cliente.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class ClienteImovelEconomiaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public ClienteImovelEconomiaHelper(){}
	
	private String clienteNome;
	
	private String relacaoTipo;
	
	private String cpf;
	
	private String cnpj;
	
	private Date relacaoDataInicio;
	
	private Date relacaoDataFim;
	
	private String motivoFimRelacao;
	
	private String matriculaFormatadaImovel;
	
	private String categoria;

	private String subCategoria;

	private String complementoEndereco;

	private Short numeroMoradores;
	
	private Short numeroPontosUtilizacao;

	private BigDecimal numeroIptu;

	private String areaConstruida;

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMotivoFimRelacao() {
		return motivoFimRelacao;
	}

	public void setMotivoFimRelacao(String motivoFimRelacao) {
		this.motivoFimRelacao = motivoFimRelacao;
	}

	public Date getRelacaoDataFim() {
		return relacaoDataFim;
	}

	public void setRelacaoDataFim(Date relacaoDataFim) {
		this.relacaoDataFim = relacaoDataFim;
	}

	public Date getRelacaoDataInicio() {
		return relacaoDataInicio;
	}

	public void setRelacaoDataInicio(Date relacaoDataInicio) {
		this.relacaoDataInicio = relacaoDataInicio;
	}

	public String getRelacaoTipo() {
		return relacaoTipo;
	}

	public void setRelacaoTipo(String relacaoTipo) {
		this.relacaoTipo = relacaoTipo;
	}

	public String getMatriculaFormatadaImovel() {
		return matriculaFormatadaImovel;
	}

	public void setMatriculaFormatadaImovel(String matriculaFormatadaImovel) {
		this.matriculaFormatadaImovel = matriculaFormatadaImovel;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public Short getNumeroMoradores() {
		return numeroMoradores;
	}

	public void setNumeroMoradores(Short numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}

	public Short getNumeroPontosUtilizacao() {
		return numeroPontosUtilizacao;
	}

	public void setNumeroPontosUtilizacao(Short numeroPontosUtilizacao) {
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
	}

	public BigDecimal getNumeroIptu() {
		return numeroIptu;
	}

	public void setNumeroIptu(BigDecimal numeroIptu) {
		this.numeroIptu = numeroIptu;
	}

	public String getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}
}
