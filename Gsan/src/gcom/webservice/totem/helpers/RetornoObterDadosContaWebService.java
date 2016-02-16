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

public class RetornoObterDadosContaWebService{
	
	String key;
	Integer error;
	String msg;
	
	@SerializedName("itens")
	private Collection<HelperDetalheContaObterDadosContaWebService> colDetalhes = new ArrayList<HelperDetalheContaObterDadosContaWebService>();
	
	public void setKey(String key) {
		this.key = key;
	}
	public void setError(Integer error) {
		this.error = error;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@SerializedName("a")
	String id;
	
	@SerializedName("b")
	String idImovel;
	
	@SerializedName("c")
	String referencia;
	
	@SerializedName("d")
	String dataVencimento;
	
	@SerializedName("e")
	String valorTotal;
	
	@SerializedName("f")
	String volumeFaturado;
	
	@SerializedName("g")
	String dataPagamento;
	
	@SerializedName("h")
	String valorAgua;
	
	@SerializedName("i")
	String valorEsgoto;
	
	@SerializedName("j")
	String valorDebitos;
	
	@SerializedName("k")
	String valorCreditos;
	
	@SerializedName("l")
	String valorImpostos;
	
	@SerializedName("m")
	String percEsgoto;
	
	@SerializedName("n")
	String inscricao;
	
	@SerializedName("o")
	String grupo;
	
	@SerializedName("p")
	String rota;
	
	@SerializedName("q")
	String seqRota;
	
	@SerializedName("r")
	String categoria;
	
	@SerializedName("s")
	String economias;
	
	@SerializedName("t")    	
	String idCliente;
	
	@SerializedName("u")
	String nomeCliente;
	
	@SerializedName("v")
	String dataLeituraAtual;
	
	@SerializedName("w")
	String dataLeituraAnterior;
	
	@SerializedName("x")
	String diasConsumo;
	
	@SerializedName("y")
	String leituraAtual;
	
	@SerializedName("z")
	String leituraAnterior;
	
	@SerializedName("aa")
	String mediaMes;
	
	@SerializedName("ba")
	String consumoFaturado;
	
	@SerializedName("ca")
	String consumoMedido;
	
	@SerializedName("da")
	String descricaoConsumo;
	
	@SerializedName("ea")
	String codigoBarras;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}	

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setVolumeFaturado(String volumeFaturado) {
		this.volumeFaturado = volumeFaturado;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public void setValorAgua(String valorAgua) {
		this.valorAgua = valorAgua;
	}

	public void setValorEsgoto(String valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public void setValorDebitos(String valorDebitos) {
		this.valorDebitos = valorDebitos;
	}

	public void setValorCreditos(String valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	public void setValorImpostos(String valorImpostos) {
		this.valorImpostos = valorImpostos;
	}

	public void setPercEsgoto(String percEsgoto) {
		this.percEsgoto = percEsgoto;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public void setSeqRota(String seqRota) {
		this.seqRota = seqRota;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setEconomias(String economias) {
		this.economias = economias;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public void setDiasConsumo(String diasConsumo) {
		this.diasConsumo = diasConsumo;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public void setMediaMes(String mediaMes) {
		this.mediaMes = mediaMes;
	}

	public void setConsumoFaturado(String consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}

	public void setConsumoMedido(String consumoMedido) {
		this.consumoMedido = consumoMedido;
	}

	public void setDescricaoConsumo(String descricaoConsumo) {
		this.descricaoConsumo = descricaoConsumo;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	
	public void addConta( HelperDetalheContaObterDadosContaWebService conta ){
		this.colDetalhes.add( conta );
	}
}	