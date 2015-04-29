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
package gsan.relatorio.cobranca.spcserasa;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import gsan.relatorio.RelatorioBean;

/**
 * [UC0684]
 * 
 * @author Rodrigo Cabral
 * @created 18/09/2014
 */

public class RelatorioNegativadorMovimentoRegItemBean implements RelatorioBean {
	
	//Contas
	private String anoMesConta;

	private String dataVencimentoConta;
	
	private String valorConta;
	
	private String situacaoConta;
	
	private String valorNegativoConta;
	
	private String situacaoCobrancaContaAteExclusao;
	
	private String dataSitCobrancaContaAteExclusao;
	
	private String situacaoCobrancaContaAposExclusao;
	
	private String dataSitCobrancaContaAposExclusao;
	
	private String situacaoDefinitiva;
	
	private JRBeanCollectionDataSource colecaoNegativadorMovimentoRegItemContasHelper;

	private JRBeanCollectionDataSource colecaoNegativadorMovimentoRegItemGuiasHelper;

	
	public JRBeanCollectionDataSource getColecaoNegativadorMovimentoRegItemContasHelper() {
		return colecaoNegativadorMovimentoRegItemContasHelper;
	}

	public void setColecaoNegativadorMovimentoRegItemContasHelper(
			JRBeanCollectionDataSource colecaoNegativadorMovimentoRegItemContasHelper) {
		this.colecaoNegativadorMovimentoRegItemContasHelper = colecaoNegativadorMovimentoRegItemContasHelper;
	}

	public JRBeanCollectionDataSource getColecaoNegativadorMovimentoRegItemGuiasHelper() {
		return colecaoNegativadorMovimentoRegItemGuiasHelper;
	}

	public void setColecaoNegativadorMovimentoRegItemGuiasHelper(
			JRBeanCollectionDataSource colecaoNegativadorMovimentoRegItemGuiasHelper) {
		this.colecaoNegativadorMovimentoRegItemGuiasHelper = colecaoNegativadorMovimentoRegItemGuiasHelper;
	}

	public String getAnoMesConta() {
		return anoMesConta;
	}

	public void setAnoMesConta(String anoMesConta) {
		this.anoMesConta = anoMesConta;
	}

	public String getDataVencimentoConta() {
		return dataVencimentoConta;
	}

	public void setDataVencimentoConta(String dataVencimentoConta) {
		this.dataVencimentoConta = dataVencimentoConta;
	}

	public String getValorConta() {
		return valorConta;
	}

	public void setValorConta(String valorConta) {
		this.valorConta = valorConta;
	}

	public String getSituacaoConta() {
		return situacaoConta;
	}

	public void setSituacaoConta(String situacaoConta) {
		this.situacaoConta = situacaoConta;
	}

	public String getValorNegativoConta() {
		return valorNegativoConta;
	}

	public void setValorNegativoConta(String valorNegativoConta) {
		this.valorNegativoConta = valorNegativoConta;
	}

	public String getSituacaoCobrancaContaAteExclusao() {
		return situacaoCobrancaContaAteExclusao;
	}

	public void setSituacaoCobrancaContaAteExclusao(String situacaoCobrancaContaAteExclusao) {
		this.situacaoCobrancaContaAteExclusao = situacaoCobrancaContaAteExclusao;
	}

	public String getDataSitCobrancaContaAteExclusao() {
		return dataSitCobrancaContaAteExclusao;
	}

	public void setDataSitCobrancaContaAteExclusao(String dataSitCobrancaContaAteExclusao) {
		this.dataSitCobrancaContaAteExclusao = dataSitCobrancaContaAteExclusao;
	}

	public String getSituacaoCobrancaContaAposExclusao() {
		return situacaoCobrancaContaAposExclusao;
	}

	public void setSituacaoCobrancaContaAposExclusao(String situacaoCobrancaContaAposExclusao) {
		this.situacaoCobrancaContaAposExclusao = situacaoCobrancaContaAposExclusao;
	}

	public String getDataSitCobrancaContaAposExclusao() {
		return dataSitCobrancaContaAposExclusao;
	}

	public void setDataSitCobrancaContaAposExclusao(String dataSitCobrancaContaAposExclusao) {
		this.dataSitCobrancaContaAposExclusao = dataSitCobrancaContaAposExclusao;
	}

	public String getSituacaoDefinitiva() {
		return situacaoDefinitiva;
	}

	public void setSituacaoDefinitiva(String situacaoDefinitiva) {
		this.situacaoDefinitiva = situacaoDefinitiva;
	}

	
	

	
}