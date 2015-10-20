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
package gcom.relatorio.cobranca.cobrancaporresultado;


import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * [UC1239] Gerar Relat�rio de Penalidades por �ndice de Atua��o e Sucesso Financeiro
 * 
 * @author Hugo Azevedo
 * @date 27/10/2011
 */
public class RelatorioPenalidadesIndiceAtuacaoSucessoFinanceiroBean implements RelatorioBean {	

	private String percentualIA;
	private String percentualMultaIA;
	private BigDecimal valorMultaCicloIA;
	private String percentualIF;
	private String percentualMultaIF;
	private BigDecimal valorMultaCicloIF;
	private BigDecimal valorMedidoCiclo;
	private String nomeEmpresa;
	private String numeroContrato;
	
	
	public String getPercentualIA() {
		return percentualIA;
	}
	public void setPercentualIA(String percentualIA) {
		this.percentualIA = percentualIA;
	}
	public String getPercentualMultaIA() {
		return percentualMultaIA;
	}
	public void setPercentualMultaIA(String percentualMultaIA) {
		this.percentualMultaIA = percentualMultaIA;
	}
	public BigDecimal getValorMultaCicloIA() {
		return valorMultaCicloIA;
	}
	public void setValorMultaCicloIA(BigDecimal valorMultaCicloIA) {
		this.valorMultaCicloIA = valorMultaCicloIA;
	}
	public String getPercentualIF() {
		return percentualIF;
	}
	public void setPercentualIF(String percentualIF) {
		this.percentualIF = percentualIF;
	}
	public String getPercentualMultaIF() {
		return percentualMultaIF;
	}
	public void setPercentualMultaIF(String percentualMultaIF) {
		this.percentualMultaIF = percentualMultaIF;
	}
	public BigDecimal getValorMultaCicloIF() {
		return valorMultaCicloIF;
	}
	public void setValorMultaCicloIF(BigDecimal valorMultaCicloIF) {
		this.valorMultaCicloIF = valorMultaCicloIF;
	}
	public BigDecimal getValorMedidoCiclo() {
		return valorMedidoCiclo;
	}
	public void setValorMedidoCiclo(BigDecimal valorMedidoCiclo) {
		this.valorMedidoCiclo = valorMedidoCiclo;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	
	
		
	
	
}
