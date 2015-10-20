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
package gcom.relatorio.faturamento.conta;

import gcom.relatorio.RelatorioBean;

/**
 * [UC] 
 * @author Vivianne Sousa
 * @date 27/01/2007
 */
public class RelatorioContasEmitidasBean implements RelatorioBean {
	
	private String codigoResponsavel;
	private String dataVencimento;
	private String endereco;
	private String esferaPoderDesc;
	private String esferaPoderId;
	private String grupoFaturamentoId;
	private String inscricao;
	private String localidade;
	private String matricula;
	private String mesAnoReferencia;
	private String nomeResponsavel;
	private String nomeUsuario;
	private String valor;
	private String numeroContasPorEsfera;
	private String valorTotalPorEsfera;
	
	public RelatorioContasEmitidasBean( String codigoResponsavel,
			String dataVencimento,String endereco,
			String esferaPoderDesc,String esferaPoderId,
			String grupoFaturamentoId,String inscricao,
			String localidade,String matricula,
			String mesAnoReferencia,String nomeResponsavel,
			String nomeUsuario,String valor,
			String numeroContasPorEsfera,
			String valorTotalPorEsfera) {
		
		this.codigoResponsavel = codigoResponsavel;
		this.dataVencimento = dataVencimento;
		this.endereco = endereco;
		this.esferaPoderDesc = esferaPoderDesc;
		this.esferaPoderId = esferaPoderId;
		this.grupoFaturamentoId = grupoFaturamentoId;
		this.inscricao = inscricao;
		this.localidade = localidade;
		this.matricula = matricula;
		this.mesAnoReferencia = mesAnoReferencia;
		this.nomeResponsavel = nomeResponsavel;
		this.nomeUsuario = nomeUsuario;
		this.valor = valor;
		this.numeroContasPorEsfera = numeroContasPorEsfera;
		this.valorTotalPorEsfera = valorTotalPorEsfera;
		
	}

	public String getCodigoResponsavel() {
		return codigoResponsavel;
	}

	public void setCodigoResponsavel(String codigoResponsavel) {
		this.codigoResponsavel = codigoResponsavel;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEsferaPoderDesc() {
		return esferaPoderDesc;
	}

	public void setEsferaPoderDesc(String esferaPoderDesc) {
		this.esferaPoderDesc = esferaPoderDesc;
	}

	public String getEsferaPoderId() {
		return esferaPoderId;
	}

	public void setEsferaPoderId(String esferaPoderId) {
		this.esferaPoderId = esferaPoderId;
	}

	public String getGrupoFaturamentoId() {
		return grupoFaturamentoId;
	}

	public void setGrupoFaturamentoId(String grupoFaturamentoId) {
		this.grupoFaturamentoId = grupoFaturamentoId;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getNumeroContasPorEsfera() {
		return numeroContasPorEsfera;
	}

	public void setNumeroContasPorEsfera(String numeroContasPorEsfera) {
		this.numeroContasPorEsfera = numeroContasPorEsfera;
	}

	public String getValorTotalPorEsfera() {
		return valorTotalPorEsfera;
	}

	public void setValorTotalPorEsfera(String valorTotalPorEsfera) {
		this.valorTotalPorEsfera = valorTotalPorEsfera;
	}

	

}
