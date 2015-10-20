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
package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * Bean respons�vel de pegar os parametros que ser�o exibidos na parte de detail
 * do relat�rio.
 * 
 * @author Rafael Corr�a
 * @created 08/08/2006
 */
/**
 * @author Administrador
 *
 */
public class RelatorioConsultarTransferenciasBean implements RelatorioBean {

	private String idImovelOrigem;

	private String idImovelDestino;

	private String dataTransferencia;
	
	private String usuario;

	private String idConta;
	
	private String mesAnoConta;
	
	private String idDebitoACobrar;
	
	private String tipoDebito;
	
	private String idGuiaPagamento;
	
	private String idCreditoARealizar;
	
	private String tipoCredito;
	
	public RelatorioConsultarTransferenciasBean(String idImovelOrigem,
			String idImovelDestino, String dataTransferencia, String usuario,
			String idConta, String mesAnoConta, String idDebitoACobrar, String idGuiaPagamento,
			String tipoDebito, String idCreditoARealizar, String tipoCredito) {
		this.idImovelOrigem = idImovelOrigem;
		this.idImovelDestino = idImovelDestino;
		this.dataTransferencia = dataTransferencia;
		this.usuario = usuario;
		this.idConta = idConta;
		this.mesAnoConta = mesAnoConta;
		this.idDebitoACobrar = idDebitoACobrar;
		this.idGuiaPagamento = idGuiaPagamento;
		this.tipoDebito = tipoDebito;
		this.idCreditoARealizar = idCreditoARealizar;
		this.tipoCredito = tipoCredito;
	}

	/**
	 * @return Retorna o campo dataTransferencia.
	 */
	public String getDataTransferencia() {
		return dataTransferencia;
	}

	/**
	 * @param dataTransferencia O dataTransferencia a ser setado.
	 */
	public void setDataTransferencia(String dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	/**
	 * @return Retorna o campo idConta.
	 */
	public String getIdConta() {
		return idConta;
	}

	/**
	 * @param idConta O idConta a ser setado.
	 */
	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}

	/**
	 * @return Retorna o campo idCreditoARealizar.
	 */
	public String getIdCreditoARealizar() {
		return idCreditoARealizar;
	}

	/**
	 * @param idCreditoARealizar O idCreditoARealizar a ser setado.
	 */
	public void setIdCreditoARealizar(String idCreditoARealizar) {
		this.idCreditoARealizar = idCreditoARealizar;
	}

	/**
	 * @return Retorna o campo idDebitoACobrar.
	 */
	public String getIdDebitoACobrar() {
		return idDebitoACobrar;
	}

	/**
	 * @param idDebitoACobrar O idDebitoACobrar a ser setado.
	 */
	public void setIdDebitoACobrar(String idDebitoACobrar) {
		this.idDebitoACobrar = idDebitoACobrar;
	}

	/**
	 * @return Retorna o campo idGuiaPagamento.
	 */
	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	/**
	 * @param idGuiaPagamento O idGuiaPagamento a ser setado.
	 */
	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}

	/**
	 * @return Retorna o campo idImovelDestino.
	 */
	public String getIdImovelDestino() {
		return idImovelDestino;
	}

	/**
	 * @param idImovelDestino O idImovelDestino a ser setado.
	 */
	public void setIdImovelDestino(String idImovelDestino) {
		this.idImovelDestino = idImovelDestino;
	}

	/**
	 * @return Retorna o campo idImovelOrigem.
	 */
	public String getIdImovelOrigem() {
		return idImovelOrigem;
	}

	/**
	 * @param idImovelOrigem O idImovelOrigem a ser setado.
	 */
	public void setIdImovelOrigem(String idImovelOrigem) {
		this.idImovelOrigem = idImovelOrigem;
	}

	/**
	 * @return Retorna o campo mesAnoConta.
	 */
	public String getMesAnoConta() {
		return mesAnoConta;
	}

	/**
	 * @param mesAnoConta O mesAnoConta a ser setado.
	 */
	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	/**
	 * @return Retorna o campo tipoCredito.
	 */
	public String getTipoCredito() {
		return tipoCredito;
	}

	/**
	 * @param tipoCredito O tipoCredito a ser setado.
	 */
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	/**
	 * @return Retorna o campo tipoDebito.
	 */
	public String getTipoDebito() {
		return tipoDebito;
	}

	/**
	 * @param tipoDebito O tipoDebito a ser setado.
	 */
	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	

}