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
package gcom.webservice.totem.helpers;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.annotations.SerializedName;

public class RetornoGerarExtratoWebService {
	
	String key;
	Integer error;
	String msg;
	
	@SerializedName("cc")
	Collection<HelperContaGerarExtratoWebService> colContas = new ArrayList<HelperContaGerarExtratoWebService>();
	
	@SerializedName("dd")
	Collection<HelperDebitoGerarExtratoWebService> colDebitos = new ArrayList<HelperDebitoGerarExtratoWebService>();    	
	
	@SerializedName("a")
	String id;
	@SerializedName("b")
	String idImovel;
	@SerializedName("v1")		
	String valorTotal;
	@SerializedName("v2")
	String valorDebito;
	@SerializedName("v3")
	String valorServico;
	@SerializedName("v4")
	String valorTaxa;
	@SerializedName("v5")
	String valorImposto;
	@SerializedName("v6")
	String valorDesconto;
	@SerializedName("v7")
	String valorAcrescimo;
	@SerializedName("c")
	String dataEmissao;
	@SerializedName("d")
	String dataValidade;
	@SerializedName("e")
	String nomeCliente;
	@SerializedName("f")
	String cpfCnpj;
	@SerializedName("g")
	String endereco;
	@SerializedName("h")
	String bairro;
	@SerializedName("i")
	String cidade;
	@SerializedName("j")
	String cep;
	@SerializedName("k")
	String uf;
	@SerializedName("l")
	String inscricao;
	@SerializedName("m")
	String perfilImovel;
	@SerializedName("n")
	String situacaoAgua;
	@SerializedName("o")
	String situacaoEsgoto;
	@SerializedName("p")
	String economias1;
	@SerializedName("q")
	String economias2;
	@SerializedName("r")
	String economias3;
	@SerializedName("s")
	String economias4;
	@SerializedName("t")
	String codigoBarras;
	
	public void setId(String id) {
		this.id = id;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}
	public void setValorServico(String valorServico) {
		this.valorServico = valorServico;
	}
	public void setValorTaxa(String valorTaxa) {
		this.valorTaxa = valorTaxa;
	}
	public void setValorImposto(String valorImposto) {
		this.valorImposto = valorImposto;
	}
	public void setValorDesconto(String valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	public void setValorAcrescimo(String valorAcrescimo) {
		this.valorAcrescimo = valorAcrescimo;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}
	public void setEconomias1(String economias1) {
		this.economias1 = economias1;
	}
	public void setEconomias2(String economias2) {
		this.economias2 = economias2;
	}
	public void setEconomias3(String economias3) {
		this.economias3 = economias3;
	}
	public void setEconomias4(String economias4) {
		this.economias4 = economias4;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setError(Integer error) {
		this.error = error;
	}
	public void addDebito(HelperDebitoGerarExtratoWebService debito) {
		this.colDebitos.add( debito );
	}
	public void addConta(HelperContaGerarExtratoWebService Conta) {
		this.colContas.add( Conta );
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}	