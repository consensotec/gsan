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
package gcom.cadastro;

import java.io.Serializable;


/**
 * Classe que ir� auxiliar no formato de entrada da pesquisa 
 * do relatorio de imoveis por Tipo de Consumo 
 *
 * @author Bruno Barros
 * @date 10/01/2008
 */
public class StringArquivoTextoDadosCadastraisHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String inscricaoImovel;
	private String matriculaImovel;
	private String nomeCliente;
	private String numeroImovel;
	private String complementoEndereco;
	private String situacaoAgua;
	private String situacaoImovel;
	private String situacaoEsgoto;
	private String codigoSubcategoria01;
	private String quantidadeSubcategoria01;
	private String codigoSubcategoria02;
	private String quantidadeSubcategoria02;
	private String codigoSubcategoria03;
	private String quantidadeSubcategoria03;
	private String codigoSubcategoria04;
	private String quantidadeSubcategoria04;
	private String codigoSubcategoria05;
	private String quantidadeSubcategoria05;
	private String tipoLogradouro;
	private String tituloLogradouro;
	private String nomeLogradouro;
	private String nomeBairro;
	private String sexoClienteUsuario;
	
	
	public String getCodigoSubcategoria01() {
		return codigoSubcategoria01;
	}
	public void setCodigoSubcategoria01(String codigoSubcategoria01) {
		this.codigoSubcategoria01 = codigoSubcategoria01;
	}
	public String getCodigoSubcategoria02() {
		return codigoSubcategoria02;
	}
	public void setCodigoSubcategoria02(String codigoSubcategoria02) {
		this.codigoSubcategoria02 = codigoSubcategoria02;
	}
	public String getCodigoSubcategoria03() {
		return codigoSubcategoria03;
	}
	public void setCodigoSubcategoria03(String codigoSubcategoria03) {
		this.codigoSubcategoria03 = codigoSubcategoria03;
	}
	public String getCodigoSubcategoria04() {
		return codigoSubcategoria04;
	}
	public void setCodigoSubcategoria04(String codigoSubcategoria04) {
		this.codigoSubcategoria04 = codigoSubcategoria04;
	}
	public String getCodigoSubcategoria05() {
		return codigoSubcategoria05;
	}
	public void setCodigoSubcategoria05(String codigoSubcategoria05) {
		this.codigoSubcategoria05 = codigoSubcategoria05;
	}
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getNomeBairro() {
		return nomeBairro;
	}
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeLogradouro() {
		return nomeLogradouro;
	}
	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}
	public String getNumeroImovel() {
		return numeroImovel;
	}
	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}
	public String getQuantidadeSubcategoria01() {
		return quantidadeSubcategoria01;
	}
	public void setQuantidadeSubcategoria01(String quantidadeSubcategoria01) {
		this.quantidadeSubcategoria01 = quantidadeSubcategoria01;
	}
	public String getQuantidadeSubcategoria02() {
		return quantidadeSubcategoria02;
	}
	public void setQuantidadeSubcategoria02(String quantidadeSubcategoria02) {
		this.quantidadeSubcategoria02 = quantidadeSubcategoria02;
	}
	public String getQuantidadeSubcategoria03() {
		return quantidadeSubcategoria03;
	}
	public void setQuantidadeSubcategoria03(String quantidadeSubcategoria03) {
		this.quantidadeSubcategoria03 = quantidadeSubcategoria03;
	}
	public String getQuantidadeSubcategoria04() {
		return quantidadeSubcategoria04;
	}
	public void setQuantidadeSubcategoria04(String quantidadeSubcategoria04) {
		this.quantidadeSubcategoria04 = quantidadeSubcategoria04;
	}
	public String getQuantidadeSubcategoria05() {
		return quantidadeSubcategoria05;
	}
	public void setQuantidadeSubcategoria05(String quantidadeSubcategoria05) {
		this.quantidadeSubcategoria05 = quantidadeSubcategoria05;
	}
	public String getSituacaoAgua() {
		return situacaoAgua;
	}
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}
	public String getSituacaoImovel() {
		return situacaoImovel;
	}
	public void setSituacaoImovel(String situacaoImovel) {
		this.situacaoImovel = situacaoImovel;
	}
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	public String getTituloLogradouro() {
		return tituloLogradouro;
	}
	public void setTituloLogradouro(String tituloLogradouro) {
		this.tituloLogradouro = tituloLogradouro;
	}
	public String getSexoClienteUsuario() {
		return sexoClienteUsuario;
	}
	public void setSexoClienteUsuario(String sexoClienteUsuario) {
		this.sexoClienteUsuario = sexoClienteUsuario;
	}
	
	
}