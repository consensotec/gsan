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
package gsan.gui.faturamento.conta;

import org.apache.struts.action.ActionForm;

public class FiltrarImovelContaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoCliente;	
	private String nomeCliente;
	private String tipoRelacao; 
	private String codigoClienteSuperior;
	private String nomeClienteSuperior;
	
	private String localidadeOrigemID;
	private String nomeLocalidadeOrigem;
	private String setorComercialOrigemCD;
	private String setorComercialOrigemID;
	private String nomeSetorComercialOrigem;
	private String quadraOrigemNM;
	private String quadraOrigemID;
	private String quadraMensagemOrigem;
	private String loteOrigem;
	private String subLoteOrigem;
	private String tipoPesquisa;
	
	private String localidadeDestinoID;
	private String nomeLocalidadeDestino;
	private String setorComercialDestinoCD;
	private String setorComercialDestinoID;
	private String nomeSetorComercialDestino;
	private String quadraDestinoNM;
	private String quadraDestinoID;
	private String quadraMensagemDestino;
	private String loteDestino;
	private String subLoteDestino;
	
	private String codigoRotaOrigem;
	private String codigoRotaDestino;
	
	private String sequencialRotaOrigem;
	private String sequencialRotaDestino;
	
	private String idFaturamentoGrupo;
	private String[] banco;
	
	
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}

	public void setIdFaturamentoGrupo(String idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}

	public String getCodigoRotaDestino() {
		return codigoRotaDestino;
	}

	public void setCodigoRotaDestino(String codigoRotaDestino) {
		this.codigoRotaDestino = codigoRotaDestino;
	}

	public String getCodigoRotaOrigem() {
		return codigoRotaOrigem;
	}

	public void setCodigoRotaOrigem(String codigoRotaOrigem) {
		this.codigoRotaOrigem = codigoRotaOrigem;
	}

	public String getSequencialRotaDestino() {
		return sequencialRotaDestino;
	}

	public void setSequencialRotaDestino(String sequencialRotaDestino) {
		this.sequencialRotaDestino = sequencialRotaDestino;
	}

	public String getSequencialRotaOrigem() {
		return sequencialRotaOrigem;
	}

	public void setSequencialRotaOrigem(String sequencialRotaOrigem) {
		this.sequencialRotaOrigem = sequencialRotaOrigem;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Retorna o campo localidadeDestinoID.
	 */
	public String getLocalidadeDestinoID() {
		return localidadeDestinoID;
	}

	/**
	 * @param localidadeDestinoID O localidadeDestinoID a ser setado.
	 */
	public void setLocalidadeDestinoID(String localidadeDestinoID) {
		this.localidadeDestinoID = localidadeDestinoID;
	}

	/**
	 * @return Retorna o campo localidadeOrigemID.
	 */
	public String getLocalidadeOrigemID() {
		return localidadeOrigemID;
	}

	/**
	 * @param localidadeOrigemID O localidadeOrigemID a ser setado.
	 */
	public void setLocalidadeOrigemID(String localidadeOrigemID) {
		this.localidadeOrigemID = localidadeOrigemID;
	}

	/**
	 * @return Retorna o campo loteDestino.
	 */
	public String getLoteDestino() {
		return loteDestino;
	}

	/**
	 * @param loteDestino O loteDestino a ser setado.
	 */
	public void setLoteDestino(String loteDestino) {
		this.loteDestino = loteDestino;
	}

	/**
	 * @return Retorna o campo loteOrigem.
	 */
	public String getLoteOrigem() {
		return loteOrigem;
	}

	/**
	 * @param loteOrigem O loteOrigem a ser setado.
	 */
	public void setLoteOrigem(String loteOrigem) {
		this.loteOrigem = loteOrigem;
	}

	/**
	 * @return Retorna o campo nomeLocalidadeDestino.
	 */
	public String getNomeLocalidadeDestino() {
		return nomeLocalidadeDestino;
	}

	/**
	 * @param nomeLocalidadeDestino O nomeLocalidadeDestino a ser setado.
	 */
	public void setNomeLocalidadeDestino(String nomeLocalidadeDestino) {
		this.nomeLocalidadeDestino = nomeLocalidadeDestino;
	}

	/**
	 * @return Retorna o campo nomeLocalidadeOrigem.
	 */
	public String getNomeLocalidadeOrigem() {
		return nomeLocalidadeOrigem;
	}

	/**
	 * @param nomeLocalidadeOrigem O nomeLocalidadeOrigem a ser setado.
	 */
	public void setNomeLocalidadeOrigem(String nomeLocalidadeOrigem) {
		this.nomeLocalidadeOrigem = nomeLocalidadeOrigem;
	}

	/**
	 * @return Retorna o campo nomeSetorComercialDestino.
	 */
	public String getNomeSetorComercialDestino() {
		return nomeSetorComercialDestino;
	}

	/**
	 * @param nomeSetorComercialDestino O nomeSetorComercialDestino a ser setado.
	 */
	public void setNomeSetorComercialDestino(String nomeSetorComercialDestino) {
		this.nomeSetorComercialDestino = nomeSetorComercialDestino;
	}

	/**
	 * @return Retorna o campo nomeSetorComercialOrigem.
	 */
	public String getNomeSetorComercialOrigem() {
		return nomeSetorComercialOrigem;
	}

	/**
	 * @param nomeSetorComercialOrigem O nomeSetorComercialOrigem a ser setado.
	 */
	public void setNomeSetorComercialOrigem(String nomeSetorComercialOrigem) {
		this.nomeSetorComercialOrigem = nomeSetorComercialOrigem;
	}

	/**
	 * @return Retorna o campo quadraDestinoID.
	 */
	public String getQuadraDestinoID() {
		return quadraDestinoID;
	}

	/**
	 * @param quadraDestinoID O quadraDestinoID a ser setado.
	 */
	public void setQuadraDestinoID(String quadraDestinoID) {
		this.quadraDestinoID = quadraDestinoID;
	}

	/**
	 * @return Retorna o campo quadraDestinoNM.
	 */
	public String getQuadraDestinoNM() {
		return quadraDestinoNM;
	}

	/**
	 * @param quadraDestinoNM O quadraDestinoNM a ser setado.
	 */
	public void setQuadraDestinoNM(String quadraDestinoNM) {
		this.quadraDestinoNM = quadraDestinoNM;
	}

	/**
	 * @return Retorna o campo quadraMensagemDestino.
	 */
	public String getQuadraMensagemDestino() {
		return quadraMensagemDestino;
	}

	/**
	 * @param quadraMensagemDestino O quadraMensagemDestino a ser setado.
	 */
	public void setQuadraMensagemDestino(String quadraMensagemDestino) {
		this.quadraMensagemDestino = quadraMensagemDestino;
	}

	/**
	 * @return Retorna o campo quadraMensagemOrigem.
	 */
	public String getQuadraMensagemOrigem() {
		return quadraMensagemOrigem;
	}

	/**
	 * @param quadraMensagemOrigem O quadraMensagemOrigem a ser setado.
	 */
	public void setQuadraMensagemOrigem(String quadraMensagemOrigem) {
		this.quadraMensagemOrigem = quadraMensagemOrigem;
	}

	/**
	 * @return Retorna o campo quadraOrigemID.
	 */
	public String getQuadraOrigemID() {
		return quadraOrigemID;
	}

	/**
	 * @param quadraOrigemID O quadraOrigemID a ser setado.
	 */
	public void setQuadraOrigemID(String quadraOrigemID) {
		this.quadraOrigemID = quadraOrigemID;
	}

	/**
	 * @return Retorna o campo quadraOrigemNM.
	 */
	public String getQuadraOrigemNM() {
		return quadraOrigemNM;
	}

	/**
	 * @param quadraOrigemNM O quadraOrigemNM a ser setado.
	 */
	public void setQuadraOrigemNM(String quadraOrigemNM) {
		this.quadraOrigemNM = quadraOrigemNM;
	}

	/**
	 * @return Retorna o campo setorComercialDestinoCD.
	 */
	public String getSetorComercialDestinoCD() {
		return setorComercialDestinoCD;
	}

	/**
	 * @param setorComercialDestinoCD O setorComercialDestinoCD a ser setado.
	 */
	public void setSetorComercialDestinoCD(String setorComercialDestinoCD) {
		this.setorComercialDestinoCD = setorComercialDestinoCD;
	}

	/**
	 * @return Retorna o campo setorComercialDestinoID.
	 */
	public String getSetorComercialDestinoID() {
		return setorComercialDestinoID;
	}

	/**
	 * @param setorComercialDestinoID O setorComercialDestinoID a ser setado.
	 */
	public void setSetorComercialDestinoID(String setorComercialDestinoID) {
		this.setorComercialDestinoID = setorComercialDestinoID;
	}

	/**
	 * @return Retorna o campo setorComercialOrigemCD.
	 */
	public String getSetorComercialOrigemCD() {
		return setorComercialOrigemCD;
	}

	/**
	 * @param setorComercialOrigemCD O setorComercialOrigemCD a ser setado.
	 */
	public void setSetorComercialOrigemCD(String setorComercialOrigemCD) {
		this.setorComercialOrigemCD = setorComercialOrigemCD;
	}

	/**
	 * @return Retorna o campo setorComercialOrigemID.
	 */
	public String getSetorComercialOrigemID() {
		return setorComercialOrigemID;
	}

	/**
	 * @param setorComercialOrigemID O setorComercialOrigemID a ser setado.
	 */
	public void setSetorComercialOrigemID(String setorComercialOrigemID) {
		this.setorComercialOrigemID = setorComercialOrigemID;
	}

	/**
	 * @return Retorna o campo subLoteDestino.
	 */
	public String getSubLoteDestino() {
		return subLoteDestino;
	}

	/**
	 * @param subLoteDestino O subLoteDestino a ser setado.
	 */
	public void setSubLoteDestino(String subLoteDestino) {
		this.subLoteDestino = subLoteDestino;
	}

	/**
	 * @return Retorna o campo subLoteOrige.
	 */
	public String getSubLoteOrigem() {
		return subLoteOrigem;
	}

	/**
	 * @param subLoteOrige O subLoteOrige a ser setado.
	 */
	public void setSubLoteOrigem(String subLoteOrigem) {
		this.subLoteOrigem = subLoteOrigem;
	}

	/**
	 * @return Retorna o campo tipoRelacao.
	 */
	public String getTipoRelacao() {
		return tipoRelacao;
	}

	/**
	 * @param tipoRelacao O tipoRelacao a ser setado.
	 */
	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	/**
	 * @return Retorna o campo codigoClienteSuperior.
	 */
	public String getCodigoClienteSuperior() {
		return codigoClienteSuperior;
	}

	/**
	 * @param codigoClienteSuperior O codigoClienteSuperior a ser setado.
	 */
	public void setCodigoClienteSuperior(String codigoClienteSuperior) {
		this.codigoClienteSuperior = codigoClienteSuperior;
	}

	/**
	 * @return Retorna o campo nomeClienteSuperior.
	 */
	public String getNomeClienteSuperior() {
		return nomeClienteSuperior;
	}

	/**
	 * @param nomeClienteSuperior O nomeClienteSuperior a ser setado.
	 */
	public void setNomeClienteSuperior(String nomeClienteSuperior) {
		this.nomeClienteSuperior = nomeClienteSuperior;
	}

	public String[] getBanco() {
		return banco;
	}

	public void setBanco(String banco[]) {
		this.banco = banco;
	}

}
