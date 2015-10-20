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

import org.apache.struts.action.ActionForm;

/**
 * Descri��o da classe 
 *
 * @author Rafael Corr�a
 * @date 18/09/2008
 */
public class ConsultarSituacaoEspecialFaturamentoPopupActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idImovel;
	private String tipo;
	private String consumoFixoNaoMedido;	
	private String consumoFixoMedido;
	private String volumeFixoNaoMedido;	
	private String volumeFixoMedido;
	private String motivo;
	private String mesAnoReferenciaFaturamentoInicial;
	private String mesAnoReferenciaFaturamentoFinal;
	private String mesAnoReferenciaRetirada;
	private String observacaoInforma;
	private String observacaoRetira;
	
	/**
	 * @return Retorna o campo consumoFixoMedido.
	 */
	public String getConsumoFixoMedido() {
		return consumoFixoMedido;
	}
	/**
	 * @param consumoFixoMedido O consumoFixoMedido a ser setado.
	 */
	public void setConsumoFixoMedido(String consumoFixoMedido) {
		this.consumoFixoMedido = consumoFixoMedido;
	}
	/**
	 * @return Retorna o campo consumoFixoNaoMedido.
	 */
	public String getConsumoFixoNaoMedido() {
		return consumoFixoNaoMedido;
	}
	/**
	 * @param consumoFixoNaoMedido O consumoFixoNaoMedido a ser setado.
	 */
	public void setConsumoFixoNaoMedido(String consumoFixoNaoMedido) {
		this.consumoFixoNaoMedido = consumoFixoNaoMedido;
	}
	/**
	 * @return Retorna o campo mesAnoReferenciaFaturamentoFinal.
	 */
	public String getMesAnoReferenciaFaturamentoFinal() {
		return mesAnoReferenciaFaturamentoFinal;
	}
	/**
	 * @param mesAnoReferenciaFaturamentoFinal O mesAnoReferenciaFaturamentoFinal a ser setado.
	 */
	public void setMesAnoReferenciaFaturamentoFinal(
			String mesAnoReferenciaFaturamentoFinal) {
		this.mesAnoReferenciaFaturamentoFinal = mesAnoReferenciaFaturamentoFinal;
	}
	/**
	 * @return Retorna o campo mesAnoReferenciaFaturamentoInicial.
	 */
	public String getMesAnoReferenciaFaturamentoInicial() {
		return mesAnoReferenciaFaturamentoInicial;
	}
	/**
	 * @param mesAnoReferenciaFaturamentoInicial O mesAnoReferenciaFaturamentoInicial a ser setado.
	 */
	public void setMesAnoReferenciaFaturamentoInicial(
			String mesAnoReferenciaFaturamentoInicial) {
		this.mesAnoReferenciaFaturamentoInicial = mesAnoReferenciaFaturamentoInicial;
	}
	/**
	 * @return Retorna o campo motivo.
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo O motivo a ser setado.
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * @return Retorna o campo observacao.
	 */
	public String getObservacaoInforma() {
		return observacaoInforma;
	}
	/**
	 * @param observacao O observacao a ser setado.
	 */
	public void setObservacaoInforma(String observacaoInforma) {
		this.observacaoInforma = observacaoInforma;
	}
	/**
	 * @return Retorna o campo observacao.
	 */
	public String getObservacaoRetira() {
		return observacaoRetira;
	}
	/**
	 * @param observacao O observacao a ser setado.
	 */
	public void setObservacaoRetira(String observacaoRetira) {
		this.observacaoRetira = observacaoRetira;
	}
	/**
	 * @return Retorna o campo tipo.
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo O tipo a ser setado.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return Retorna o campo volumeFixoMedido.
	 */
	public String getVolumeFixoMedido() {
		return volumeFixoMedido;
	}
	/**
	 * @param volumeFixoMedido O volumeFixoMedido a ser setado.
	 */
	public void setVolumeFixoMedido(String volumeFixoMedido) {
		this.volumeFixoMedido = volumeFixoMedido;
	}
	/**
	 * @return Retorna o campo volumeFixoNaoMedido.
	 */
	public String getVolumeFixoNaoMedido() {
		return volumeFixoNaoMedido;
	}
	/**
	 * @param volumeFixoNaoMedido O volumeFixoNaoMedido a ser setado.
	 */
	public void setVolumeFixoNaoMedido(String volumeFixoNaoMedido) {
		this.volumeFixoNaoMedido = volumeFixoNaoMedido;
	}
	/**
	 * @return Retorna o campo mesAnoReferenciaRetirada.
	 */
	public String getMesAnoReferenciaRetirada() {
		return mesAnoReferenciaRetirada;
	}
	/**
	 * @param mesAnoReferenciaRetirada O mesAnoReferenciaRetirada a ser setado.
	 */
	public void setMesAnoReferenciaRetirada(String mesAnoReferenciaRetirada) {
		this.mesAnoReferenciaRetirada = mesAnoReferenciaRetirada;
	}
	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}
	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

}
