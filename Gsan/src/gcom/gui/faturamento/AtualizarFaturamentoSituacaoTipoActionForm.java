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
package gcom.gui.faturamento;



import org.apache.struts.validator.ValidatorActionForm;


/**
 *  
 * @author Arthur Carvalho
 * @date  	21/08/2008
 */

public class AtualizarFaturamentoSituacaoTipoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String descricao;
	private String indicadorParalisacaoFaturamento;
	private String indicadorParalisacaoLeitura;
	private String indicadorValidoAgua;
	private String indicadorValidoEsgoto;
	private String indicadorInformarConsumo;
	private String indicadorInformarVolume;
	private String indicadorUso;
	private String leituraAnormalidadeLeituraComLeitura;
	private String leituraAnormalidadeLeituraSemLeitura;
	private String leituraAnormalidadeConsumoComLeitura;
	private String leituraAnormalidadeConsumoSemLeitura;
	private String indicadorParalisaFatEsgoto;
	private String indicadorParalisaFatAgua;
	

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getIndicadorInformarConsumo() {
		return indicadorInformarConsumo;
	}
	public void setIndicadorInformarConsumo(String indicadorInformarConsumo) {
		this.indicadorInformarConsumo = indicadorInformarConsumo;
	}
	public String getIndicadorInformarVolume() {
		return indicadorInformarVolume;
	}
	public void setIndicadorInformarVolume(String indicadorInformarVolume) {
		this.indicadorInformarVolume = indicadorInformarVolume;
	}
	public String getIndicadorParalisacaoFaturamento() {
		return indicadorParalisacaoFaturamento;
	}
	public void setIndicadorParalisacaoFaturamento(
			String indicadorParalisacaoFaturamento) {
		this.indicadorParalisacaoFaturamento = indicadorParalisacaoFaturamento;
	}
	public String getIndicadorParalisacaoLeitura() {
		return indicadorParalisacaoLeitura;
	}
	public void setIndicadorParalisacaoLeitura(String indicadorParalisacaoLeitura) {
		this.indicadorParalisacaoLeitura = indicadorParalisacaoLeitura;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getIndicadorValidoAgua() {
		return indicadorValidoAgua;
	}
	public void setIndicadorValidoAgua(String indicadorValidoAgua) {
		this.indicadorValidoAgua = indicadorValidoAgua;
	}
	public String getIndicadorValidoEsgoto() {
		return indicadorValidoEsgoto;
	}
	public void setIndicadorValidoEsgoto(String indicadorValidoEsgoto) {
		this.indicadorValidoEsgoto = indicadorValidoEsgoto;
	}
	public String getLeituraAnormalidadeConsumoComLeitura() {
		return leituraAnormalidadeConsumoComLeitura;
	}
	public void setLeituraAnormalidadeConsumoComLeitura(
			String leituraAnormalidadeConsumoComLeitura) {
		this.leituraAnormalidadeConsumoComLeitura = leituraAnormalidadeConsumoComLeitura;
	}
	public String getLeituraAnormalidadeConsumoSemLeitura() {
		return leituraAnormalidadeConsumoSemLeitura;
	}
	public void setLeituraAnormalidadeConsumoSemLeitura(
			String leituraAnormalidadeConsumoSemLeitura) {
		this.leituraAnormalidadeConsumoSemLeitura = leituraAnormalidadeConsumoSemLeitura;
	}
	public String getLeituraAnormalidadeLeituraComLeitura() {
		return leituraAnormalidadeLeituraComLeitura;
	}
	public void setLeituraAnormalidadeLeituraComLeitura(
			String leituraAnormalidadeLeituraComLeitura) {
		this.leituraAnormalidadeLeituraComLeitura = leituraAnormalidadeLeituraComLeitura;
	}
	public String getLeituraAnormalidadeLeituraSemLeitura() {
		return leituraAnormalidadeLeituraSemLeitura;
	}
	public void setLeituraAnormalidadeLeituraSemLeitura(
			String leituraAnormalidadeLeituraSemLeitura) {
		this.leituraAnormalidadeLeituraSemLeitura = leituraAnormalidadeLeituraSemLeitura;
	}
	public String getIndicadorParalisaFatEsgoto() {
		return indicadorParalisaFatEsgoto;
	}
	public void setIndicadorParalisaFatEsgoto(String indicadorParalisaFatEsgoto) {
		this.indicadorParalisaFatEsgoto = indicadorParalisaFatEsgoto;
	}
	public String getIndicadorParalisaFatAgua() {
		return indicadorParalisaFatAgua;
	}
	public void setIndicadorParalisaFatAgua(String indicadorParalisaFatAgua) {
		this.indicadorParalisaFatAgua = indicadorParalisaFatAgua;
	}
		
}
