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
package gcom.cobranca.bean;

import java.io.Serializable;
import java.util.Collection;

/**
 * [UC ] 
 * @author Vivianne Sousa
 * @date 26/07/2007
 */
public class ReavisoDeDebitoHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Collection colecaoReavisoDeDebitoLinhasDescricaoDebitosHelper;
	private String numero;
	private String nomeCliente;
	private String endereco;
	private String enderecoLinha2;
	private String enderecoLinha3;
	private String dataEmissao;
	private String rotaGrupo;
	private String inscricao;
	private String codigoAuxiliar;
	private String numeroHidrometro;
	private String vencimento;
	private String totalAPagar;
	private String representacaoNumericaCodBarraFormatada;
	private String representacaoNumericaCodBarraSemDigito;
	private String descricaoSituacaoLigacaoAgua;
	private String descricaoSituacaoLigacaoEsgoto;
	private String descricaoCategoriaPrincipal;
	

	public ReavisoDeDebitoHelper() {
	}
	
	public ReavisoDeDebitoHelper(
			Collection colecaoReavisoDeDebitoLinhasDescricaoDebitosHelper,
			String numero, String nomeCliente, String endereco,
			String endereco1Linha2, String endereco1Linha3, 
			String dataEmissao, String rotaGrupo,
			String inscricao, String codigoAuxiliar, String numeroHidrometro,
			String vencimento, String totalAPagar,
			String representacaoNumericaCodBarraFormatada,
			String representacaoNumericaCodBarraSemDigito,
			String descricaoSituacaoLigacaoAgua,
			String descricaoSituacaoLigacaoEsgoto,
			String descricaoCategoriaPrincipal) {
		
		this.colecaoReavisoDeDebitoLinhasDescricaoDebitosHelper =colecaoReavisoDeDebitoLinhasDescricaoDebitosHelper; 
		this.numero = numero;
		this.nomeCliente = nomeCliente;
		this.endereco = endereco;
		this.enderecoLinha2 = endereco1Linha2;
		this.enderecoLinha3 = endereco1Linha3;
		this.dataEmissao = dataEmissao;
		this.rotaGrupo = rotaGrupo;
		this.inscricao = inscricao;
		this.codigoAuxiliar = codigoAuxiliar;
		this.numeroHidrometro = numeroHidrometro;
		this.vencimento = vencimento;
		this.totalAPagar = totalAPagar;
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
		this.descricaoSituacaoLigacaoAgua = descricaoSituacaoLigacaoAgua;
		this.descricaoSituacaoLigacaoEsgoto = descricaoSituacaoLigacaoEsgoto;
		this.descricaoCategoriaPrincipal = descricaoCategoriaPrincipal;
	}


	public String getEnderecoLinha2() {
		return enderecoLinha2;
	}

	public void setEnderecoLinha2(String enderecoLinha2) {
		this.enderecoLinha2 = enderecoLinha2;
	}

	public String getEnderecoLinha3() {
		return enderecoLinha3;
	}

	public void setEnderecoLinha3(String enderecoLinha3) {
		this.enderecoLinha3 = enderecoLinha3;
	}

	public String getCodigoAuxiliar() {
		return codigoAuxiliar;
	}

	public void setCodigoAuxiliar(String codigoAuxiliar) {
		this.codigoAuxiliar = codigoAuxiliar;
	}

	public Collection getColecaoReavisoDeDebitoLinhasDescricaoDebitosHelper() {
		return colecaoReavisoDeDebitoLinhasDescricaoDebitosHelper;
	}

	public void setColecaoReavisoDeDebitoLinhasDescricaoDebitosHelper(
			Collection colecaoReavisoDeDebitoLinhasDescricaoDebitosHelper) {
		this.colecaoReavisoDeDebitoLinhasDescricaoDebitosHelper = colecaoReavisoDeDebitoLinhasDescricaoDebitosHelper;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}

	public void setRepresentacaoNumericaCodBarraFormatada(
			String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito(
			String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	public String getRotaGrupo() {
		return rotaGrupo;
	}

	public void setRotaGrupo(String rotaGrupo) {
		this.rotaGrupo = rotaGrupo;
	}

	public String getTotalAPagar() {
		return totalAPagar;
	}

	public void setTotalAPagar(String totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public String getDescricaoSituacaoLigacaoAgua() {
		return descricaoSituacaoLigacaoAgua;
	}

	public void setDescricaoSituacaoLigacaoAgua(String descricaoSituacaoLigacaoAgua) {
		this.descricaoSituacaoLigacaoAgua = descricaoSituacaoLigacaoAgua;
	}

	public String getDescricaoSituacaoLigacaoCategoriaPrincipal() {
		return descricaoCategoriaPrincipal;
	}

	public void setDescricaoCategoriaPrincipal(
			String descricaoCategoriaPrincipal) {
		this.descricaoCategoriaPrincipal = descricaoCategoriaPrincipal;
	}

	public String getDescricaoSituacaoLigacaoEsgoto() {
		return descricaoSituacaoLigacaoEsgoto;
	}

	public void setDescricaoSituacaoLigacaoEsgoto(
			String descricaoSituacaoLigacaoEsgoto) {
		this.descricaoSituacaoLigacaoEsgoto = descricaoSituacaoLigacaoEsgoto;
	}
	
		
}
