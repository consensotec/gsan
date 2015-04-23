/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.spcserasa.bean;


/**
 * [UC0684] 
 * 
 * Classe responsável pelo helper para montar a coleção de guias do Relatório de Consultar Negativador MovimentoRegItem
 * 
 * @author Rodrigo Cabral
 * @date 22/09/2014
 */
public class ConsultarNegativadorMovimentoRegItemGuiasHelper {

	private String tipoDebito;

	private String dataEmissaoGuia;
	
	private String dataVencimentoGuia;
	
	private String valorGuia;
	
	private String situacaoGuia;
	
	private String valorNegativoGuia;
	
	private String situacaoCobrancaGuiaAteExclusao;
	
	private String dataSitCobrancaGuiaAteExclusao;
	
	private String situacaoCobrancaGuiaAposExclusao;
	
	private String dataSitCobrancaGuiaAposExclusao;
	
	private String situacaoDefinitiva;
	
	private String totalQtdGuias;
	
	private String totalValorGuia;
	
	private String totalValorNegativadoGuia;

	
	public String getTotalQtdGuias() {
		return totalQtdGuias;
	}

	public void setTotalQtdGuias(String totalQtdGuias) {
		this.totalQtdGuias = totalQtdGuias;
	}

	public String getTotalValorGuia() {
		return totalValorGuia;
	}

	public void setTotalValorGuia(String totalValorGuia) {
		this.totalValorGuia = totalValorGuia;
	}

	public String getTotalValorNegativadoGuia() {
		return totalValorNegativadoGuia;
	}

	public void setTotalValorNegativadoGuia(String totalValorNegativadoGuia) {
		this.totalValorNegativadoGuia = totalValorNegativadoGuia;
	}

	public String getDataEmissaoGuia() {
		return dataEmissaoGuia;
	}

	public void setDataEmissaoGuia(String dataEmissaoGuia) {
		this.dataEmissaoGuia = dataEmissaoGuia;
	}

	public String getTipoDebito() {
		return tipoDebito;
	}

	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}

	public String getDataVencimentoGuia() {
		return dataVencimentoGuia;
	}

	public void setDataVencimentoGuia(String dataVencimentoGuia) {
		this.dataVencimentoGuia = dataVencimentoGuia;
	}

	public String getValorGuia() {
		return valorGuia;
	}

	public void setValorGuia(String valorGuia) {
		this.valorGuia = valorGuia;
	}

	public String getSituacaoGuia() {
		return situacaoGuia;
	}

	public void setSituacaoGuia(String situacaoGuia) {
		this.situacaoGuia = situacaoGuia;
	}

	public String getValorNegativoGuia() {
		return valorNegativoGuia;
	}

	public void setValorNegativoGuia(String valorNegativoGuia) {
		this.valorNegativoGuia = valorNegativoGuia;
	}

	public String getSituacaoCobrancaGuiaAteExclusao() {
		return situacaoCobrancaGuiaAteExclusao;
	}

	public void setSituacaoCobrancaGuiaAteExclusao(String situacaoCobrancaGuiaAteExclusao) {
		this.situacaoCobrancaGuiaAteExclusao = situacaoCobrancaGuiaAteExclusao;
	}

	public String getDataSitCobrancaGuiaAteExclusao() {
		return dataSitCobrancaGuiaAteExclusao;
	}

	public void setDataSitCobrancaGuiaAteExclusao(String dataSitCobrancaGuiaAteExclusao) {
		this.dataSitCobrancaGuiaAteExclusao = dataSitCobrancaGuiaAteExclusao;
	}

	public String getSituacaoCobrancaGuiaAposExclusao() {
		return situacaoCobrancaGuiaAposExclusao;
	}

	public void setSituacaoCobrancaGuiaAposExclusao(String situacaoCobrancaGuiaAposExclusao) {
		this.situacaoCobrancaGuiaAposExclusao = situacaoCobrancaGuiaAposExclusao;
	}

	public String getDataSitCobrancaGuiaAposExclusao() {
		return dataSitCobrancaGuiaAposExclusao;
	}

	public void setDataSitCobrancaGuiaAposExclusao(String dataSitCobrancaGuiaAposExclusao) {
		this.dataSitCobrancaGuiaAposExclusao = dataSitCobrancaGuiaAposExclusao;
	}

	public String getSituacaoDefinitiva() {
		return situacaoDefinitiva;
	}

	public void setSituacaoDefinitiva(String situacaoDefinitiva) {
		this.situacaoDefinitiva = situacaoDefinitiva;
	}

	

}
