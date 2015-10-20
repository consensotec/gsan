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
package gcom.arrecadacao.bean;

import java.util.Collection;


/**
 * Classe que ir� auxiliar no formato do retorno da pesquisa dos itens de um
 * determinado movimento do arrecadador
 *
 * @author Raphael Rossiter
 * @date 20/03/2006
 */
public class ArrecadadorMovimentoItemHelper {
	
	private Integer id;
	private String codigoRegistro;
	private String identificacao;
	private String tipoPagamento;
	private String ocorrencia;
	private Short indicadorAceitacao;
	private String descricaoIndicadorAceitacao;
	private RegistroHelperCodigoB registroHelperCodigoB;
	private RegistroHelperCodigoC registroHelperCodigoC;
	private RegistroHelperCodigoE registroHelperCodigoE;
	private RegistroHelperCodigoF registroHelperCodigoF;
	private RegistroHelperCodigoG registroHelperCodigoG;
	
	private Collection colecaoArrecadadorMovimentoItemHelper;
	private String vlMovimento;
	private String vlPagamento;
	private String matriculaImovel;
	
	
	public RegistroHelperCodigoB getRegistroHelperCodigoB() {
		return registroHelperCodigoB;
	}


	public void setRegistroHelperCodigoB(RegistroHelperCodigoB registroHelperCodigoB) {
		this.registroHelperCodigoB = registroHelperCodigoB;
	}


	public RegistroHelperCodigoC getRegistroHelperCodigoC() {
		return registroHelperCodigoC;
	}


	public void setRegistroHelperCodigoC(RegistroHelperCodigoC registroHelperCodigoC) {
		this.registroHelperCodigoC = registroHelperCodigoC;
	}


	public RegistroHelperCodigoE getRegistroHelperCodigoE() {
		return registroHelperCodigoE;
	}


	public void setRegistroHelperCodigoE(RegistroHelperCodigoE registroHelperCodigoE) {
		this.registroHelperCodigoE = registroHelperCodigoE;
	}


	public RegistroHelperCodigoF getRegistroHelperCodigoF() {
		return registroHelperCodigoF;
	}


	public void setRegistroHelperCodigoF(RegistroHelperCodigoF registroHelperCodigoF) {
		this.registroHelperCodigoF = registroHelperCodigoF;
	}


	public RegistroHelperCodigoG getRegistroHelperCodigoG() {
		return registroHelperCodigoG;
	}


	public void setRegistroHelperCodigoG(RegistroHelperCodigoG registroHelperCodigoG) {
		this.registroHelperCodigoG = registroHelperCodigoG;
	}


	public ArrecadadorMovimentoItemHelper() {}
	
	
	public ArrecadadorMovimentoItemHelper(String codigoRegistro, String identificacao, String tipoPagamento, 
			String ocorrencia, Short indicadorAceitacao, String descricaoIndicadorAceitacao) {
		
		this.codigoRegistro = codigoRegistro;
		this.identificacao = identificacao;
		this.tipoPagamento = tipoPagamento;
		this.ocorrencia = ocorrencia;
		this.indicadorAceitacao = indicadorAceitacao;
		this.descricaoIndicadorAceitacao = descricaoIndicadorAceitacao;
	}
	
	public String getCodigoRegistro() {
		return codigoRegistro;
	}
	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}
	public String getDescricaoIndicadorAceitacao() {
		return descricaoIndicadorAceitacao;
	}
	public void setDescricaoIndicadorAceitacao(String descricaoIndicadorAceitacao) {
		this.descricaoIndicadorAceitacao = descricaoIndicadorAceitacao;
	}
	public String getIdentificacao() {
		return identificacao;
	}
	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}
	public Short getIndicadorAceitacao() {
		return indicadorAceitacao;
	}
	public void setIndicadorAceitacao(Short indicadorAceitacao) {
		this.indicadorAceitacao = indicadorAceitacao;
	}
	public String getOcorrencia() {
		return ocorrencia;
	}
	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}


	public String getTipoPagamento() {
		return tipoPagamento;
	}


	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getVlMovimento() {
		return vlMovimento;
	}


	public void setVlMovimento(String vlMovimento) {
		this.vlMovimento = vlMovimento;
	}


	public String getVlPagamento() {
		return vlPagamento;
	}


	public void setVlPagamento(String vlPagamento) {
		this.vlPagamento = vlPagamento;
	}


	public Collection getColecaoArrecadadorMovimentoItemHelper() {
		return colecaoArrecadadorMovimentoItemHelper;
	}


	public void setColecaoArrecadadorMovimentoItemHelper(
			Collection colecaoArrecadadorMovimentoItemHelper) {
		this.colecaoArrecadadorMovimentoItemHelper = colecaoArrecadadorMovimentoItemHelper;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

}
