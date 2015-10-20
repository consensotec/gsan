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
package gcom.gui.arrecadacao;

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * Esta classe tem por finalidade gerar o formul�rio que receber� os par�metros para realiza��o
 * da filtragem dos movimentos dos arrecadadores 
 *
 * @author Raphael Rossiter
 * @date 23/02/2006
 */
public class FiltrarMovimentoArrecadadoresActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String banco;
	private String descricaoBanco;
	private String remessa;
	private String identificacaoServico;
	private String nsa;
	private String dataGeracaoMovimentoInicio;
	private String dataGeracaoMovimentoFim;
	private String dataProcessamentoMovimentoInicio;
	private String dataProcessamentoMovimentoFim;
	private String movimentoItemOcorrencia;
	private String movimentoItemAceito;
	private String movimentoAbertoFechado;
	private String formaArrecadacao;
	private String indicadorRelatorio;
  
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getDataGeracaoMovimentoFim() {
		return dataGeracaoMovimentoFim;
	}
	public void setDataGeracaoMovimentoFim(String dataGeracaoMovimentoFim) {
		this.dataGeracaoMovimentoFim = dataGeracaoMovimentoFim;
	}
	public String getDataGeracaoMovimentoInicio() {
		return dataGeracaoMovimentoInicio;
	}
	public void setDataGeracaoMovimentoInicio(String dataGeracaoMovimentoInicio) {
		this.dataGeracaoMovimentoInicio = dataGeracaoMovimentoInicio;
	}
	public String getDataProcessamentoMovimentoFim() {
		return dataProcessamentoMovimentoFim;
	}
	public void setDataProcessamentoMovimentoFim(
			String dataProcessamentoMovimentoFim) {
		this.dataProcessamentoMovimentoFim = dataProcessamentoMovimentoFim;
	}
	public String getDataProcessamentoMovimentoInicio() {
		return dataProcessamentoMovimentoInicio;
	}
	public void setDataProcessamentoMovimentoInicio(
			String dataProcessamentoMovimentoInicio) {
		this.dataProcessamentoMovimentoInicio = dataProcessamentoMovimentoInicio;
	}
	public String getIdentificacaoServico() {
		return identificacaoServico;
	}
	public void setIdentificacaoServico(String identificacaoServico) {
		this.identificacaoServico = identificacaoServico;
	}
	public String getMovimentoAbertoFechado() {
		return movimentoAbertoFechado;
	}
	public void setMovimentoAbertoFechado(String movimentoAbertoFechado) {
		this.movimentoAbertoFechado = movimentoAbertoFechado;
	}
	public String getMovimentoItemAceito() {
		return movimentoItemAceito;
	}
	public void setMovimentoItemAceito(String movimentoItemAceito) {
		this.movimentoItemAceito = movimentoItemAceito;
	}
	public String getMovimentoItemOcorrencia() {
		return movimentoItemOcorrencia;
	}
	public void setMovimentoItemOcorrencia(String movimentoItemOcorrencia) {
		this.movimentoItemOcorrencia = movimentoItemOcorrencia;
	}
	public String getNsa() {
		return nsa;
	}
	public void setNsa(String nsa) {
		this.nsa = nsa;
	}
	public String getRemessa() {
		return remessa;
	}
	public void setRemessa(String remessa) {
		this.remessa = remessa;
	}
	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	    /**@todo: finish this method, this is just the skeleton.*/
	    return null;
	}
	
	public String getDescricaoBanco() {
		return descricaoBanco;
	}
	public void setDescricaoBanco(String descricaoBanco) {
		this.descricaoBanco = descricaoBanco;
	}
	/**
	 * @return Retorna o campo formaArrecadacao.
	 */
	public String getFormaArrecadacao() {
		return formaArrecadacao;
	}
	/**
	 * @param formaArrecadacao O formaArrecadacao a ser setado.
	 */
	public void setFormaArrecadacao(String formaArrecadacao) {
		this.formaArrecadacao = formaArrecadacao;
	}
	public String getIndicadorRelatorio() {
		return indicadorRelatorio;
	}
	public void setIndicadorRelatorio(String indicadorRelatorio) {
		this.indicadorRelatorio = indicadorRelatorio;
	}
	
}
