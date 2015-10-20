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
package gcom.gui.arrecadacao.aviso;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0611] Movimentar Pagamentos/ Devolu��es entre Avisos Banc�rios 
 * 
 * @author Ana Maria
 *
 * @date 07/06/2007
 */
public class SelecionarPagamentosAvisoBancarioActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
    private String codigoAgenteArrecadadorO;    
    private String dataLancamentoAvisoO;
    private String numeroSequencialAvisoO;
	private String avisoBancarioO;
    private String codigoAgenteArrecadadorD;    
    private String dataLancamentoAvisoD;    
    private String numeroSequencialAvisoD;
	private String avisoBancarioD;
	private String dataDevolucao;
	private String dataPagamento;
	private String idArrecadacaoForma;
	/**
	 * @return Retorna o campo avisoBancarioD.
	 */
	public String getAvisoBancarioD() {
		return avisoBancarioD;
	}
	/**
	 * @param avisoBancarioD O avisoBancarioD a ser setado.
	 */
	public void setAvisoBancarioD(String avisoBancarioD) {
		this.avisoBancarioD = avisoBancarioD;
	}
	/**
	 * @return Retorna o campo avisoBancarioO.
	 */
	public String getAvisoBancarioO() {
		return avisoBancarioO;
	}
	/**
	 * @param avisoBancarioO O avisoBancarioO a ser setado.
	 */
	public void setAvisoBancarioO(String avisoBancarioO) {
		this.avisoBancarioO = avisoBancarioO;
	}
	/**
	 * @return Retorna o campo codigoAgenteArrecadadorD.
	 */
	public String getCodigoAgenteArrecadadorD() {
		return codigoAgenteArrecadadorD;
	}
	/**
	 * @param codigoAgenteArrecadadorD O codigoAgenteArrecadadorD a ser setado.
	 */
	public void setCodigoAgenteArrecadadorD(String codigoAgenteArrecadadorD) {
		this.codigoAgenteArrecadadorD = codigoAgenteArrecadadorD;
	}
	/**
	 * @return Retorna o campo codigoAgenteArrecadadorO.
	 */
	public String getCodigoAgenteArrecadadorO() {
		return codigoAgenteArrecadadorO;
	}
	/**
	 * @param codigoAgenteArrecadadorO O codigoAgenteArrecadadorO a ser setado.
	 */
	public void setCodigoAgenteArrecadadorO(String codigoAgenteArrecadadorO) {
		this.codigoAgenteArrecadadorO = codigoAgenteArrecadadorO;
	}
	/**
	 * @return Retorna o campo dataDevolucao.
	 */
	public String getDataDevolucao() {
		return dataDevolucao;
	}
	/**
	 * @param dataDevolucao O dataDevolucao a ser setado.
	 */
	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	/**
	 * @return Retorna o campo dataLancamentoAvisoD.
	 */
	public String getDataLancamentoAvisoD() {
		return dataLancamentoAvisoD;
	}
	/**
	 * @param dataLancamentoAvisoD O dataLancamentoAvisoD a ser setado.
	 */
	public void setDataLancamentoAvisoD(String dataLancamentoAvisoD) {
		this.dataLancamentoAvisoD = dataLancamentoAvisoD;
	}
	/**
	 * @return Retorna o campo dataLancamentoAvisoO.
	 */
	public String getDataLancamentoAvisoO() {
		return dataLancamentoAvisoO;
	}
	/**
	 * @param dataLancamentoAvisoO O dataLancamentoAvisoO a ser setado.
	 */
	public void setDataLancamentoAvisoO(String dataLancamentoAvisoO) {
		this.dataLancamentoAvisoO = dataLancamentoAvisoO;
	}
	/**
	 * @return Retorna o campo dataPagamento.
	 */
	public String getDataPagamento() {
		return dataPagamento;
	}
	/**
	 * @param dataPagamento O dataPagamento a ser setado.
	 */
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	/**
	 * @return Retorna o campo idArrecadacaoForma.
	 */
	public String getIdArrecadacaoForma() {
		return idArrecadacaoForma;
	}
	/**
	 * @param idArrecadacaoForma O idArrecadacaoForma a ser setado.
	 */
	public void setIdArrecadacaoForma(String idArrecadacaoForma) {
		this.idArrecadacaoForma = idArrecadacaoForma;
	}
	/**
	 * @return Retorna o campo numeroSequencialAvisoD.
	 */
	public String getNumeroSequencialAvisoD() {
		return numeroSequencialAvisoD;
	}
	/**
	 * @param numeroSequencialAvisoD O numeroSequencialAvisoD a ser setado.
	 */
	public void setNumeroSequencialAvisoD(String numeroSequencialAvisoD) {
		this.numeroSequencialAvisoD = numeroSequencialAvisoD;
	}
	/**
	 * @return Retorna o campo numeroSequencialAvisoO.
	 */
	public String getNumeroSequencialAvisoO() {
		return numeroSequencialAvisoO;
	}
	/**
	 * @param numeroSequencialAvisoO O numeroSequencialAvisoO a ser setado.
	 */
	public void setNumeroSequencialAvisoO(String numeroSequencialAvisoO) {
		this.numeroSequencialAvisoO = numeroSequencialAvisoO;
	}

}