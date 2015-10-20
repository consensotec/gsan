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
* Genival Soares Barbosa Filho
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
package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * [UCXXXX] - Gerar Relat�rio Analisar Metas Ciclo
 * 
 * @author Genival Barbosa
 * @date 21/07/2009
 */
public class RelatorioAnalisarMetasCicloBean implements RelatorioBean {

	private String idAcaoCobranca;
	
	private String anoMesCobranca;
	
	// P - Principal
	// G - Gerencia regional
	// U - Unidade de negocio
	// L - localidade
	
	private String nomeGerencia;
	
	private String idGerencia;
	
	private String nomeUnidade;
	
	private String idUnidade;
	
	private String nomeLocalidade;
	
	private String idLocalidade;
	
	
	
	private String metaOriginal;
	
	private String metaAtual;
	
	private String qtdImoveisSituacao;
		
	private String qtdTotalRealizada;
		
	private String valorTotalRealizada;
		
	private String qtdTotalRestante;
		
	private String valorTotalRestante;

	public RelatorioAnalisarMetasCicloBean(String nomeGerencia, String idGerencia, 
			String nomeUnidade, String idUnidade, String nomeLocalidade, String idLocalidade, 
			String metaOriginal, String metaAtual, 
			String qtdImoveisSituacao, String qtdTotalRealizada, 
			String valorTotalRealizada, String qtdTotalRestante, String valorTotalRestante, 
			String idAcaoCobranca, String anoMesCobranca) {
		
		
		this.nomeGerencia = nomeGerencia;
		this.idGerencia = idGerencia;
		this.nomeUnidade = nomeUnidade;
		this.idUnidade = idUnidade;
		this.nomeLocalidade = nomeLocalidade;
		this.metaOriginal = metaOriginal;
		this.metaAtual = metaAtual;
		this.qtdImoveisSituacao = qtdImoveisSituacao;
		this.qtdTotalRealizada = qtdTotalRealizada;
		this.valorTotalRealizada = valorTotalRealizada;
		this.qtdTotalRestante = qtdTotalRestante;
		this.valorTotalRestante = valorTotalRestante;
		this.idAcaoCobranca = idAcaoCobranca;
		this.anoMesCobranca = anoMesCobranca;
	}

	public String getMetaAtual() {
		return metaAtual;
	}

	public void setMetaAtual(String metaAtual) {
		this.metaAtual = metaAtual;
	}

	public String getMetaOriginal() {
		return metaOriginal;
	}

	public void setMetaOriginal(String metaOriginal) {
		this.metaOriginal = metaOriginal;
	}

	public String getNomeGerencia() {
		return nomeGerencia;
	}

	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public String getQtdImoveisSituacao() {
		return qtdImoveisSituacao;
	}

	public void setQtdImoveisSituacao(String qtdImoveisSituacao) {
		this.qtdImoveisSituacao = qtdImoveisSituacao;
	}

	public String getQtdTotalRealizada() {
		return qtdTotalRealizada;
	}

	public void setQtdTotalRealizada(String qtdTotalRealizada) {
		this.qtdTotalRealizada = qtdTotalRealizada;
	}

	public String getQtdTotalRestante() {
		return qtdTotalRestante;
	}

	public void setQtdTotalRestante(String qtdTotalRestante) {
		this.qtdTotalRestante = qtdTotalRestante;
	}

	public String getValorTotalRealizada() {
		return valorTotalRealizada;
	}

	public void setValorTotalRealizada(String valorTotalRealizada) {
		this.valorTotalRealizada = valorTotalRealizada;
	}

	public String getValorTotalRestante() {
		return valorTotalRestante;
	}

	public void setValorTotalRestante(String valorTotalRestante) {
		this.valorTotalRestante = valorTotalRestante;
	}

	public String getIdGerencia() {
		return idGerencia;
	}

	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getAnoMesCobranca() {
		return anoMesCobranca;
	}

	public void setAnoMesCobranca(String anoMesCobranca) {
		this.anoMesCobranca = anoMesCobranca;
	}

	public String getIdAcaoCobranca() {
		return idAcaoCobranca;
	}

	public void setIdAcaoCobranca(String idAcaoCobranca) {
		this.idAcaoCobranca = idAcaoCobranca;
	}
	
}
