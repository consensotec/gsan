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
package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0217] Inserir Resolu��o de Diretoria
 * @author Rafael Corr�a
 * @since 30/03/2006
 */
public class InserirResolucaoDiretoriaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String numero;
	
	private String assunto;
	
	private String dataInicio;
	
	private String dataFim;
	
    private String indicadorParcelamentoUnico;
    
    private String indicadorUtilizacaoLivre;
    
    private String indicadorDescontoSancoes;	
    
    private String indicadorParcelasEmAtraso;
    private String idParcelasEmAtraso;
    private String indicadorParcelamentoEmAndamento;
    private String idParcelamentoEmAndamento;
    private String indicadorNegociacaoSoAVista;
    private String indicadorDescontoSoEmContaAVista;
	private String indicadorParcelamentoLojaVirtual;
	private String indicadorParcelamentoCartaoCredito;
	
	public String getIndicadorDescontoSoEmContaAVista() {
		return indicadorDescontoSoEmContaAVista;
	}

	public void setIndicadorDescontoSoEmContaAVista(
			String indicadorDescontoSoEmContaAVista) {
		this.indicadorDescontoSoEmContaAVista = indicadorDescontoSoEmContaAVista;
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {

		numero = null;
		assunto = null;
		dataInicio = null;
		dataFim = null;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getIndicadorDescontoSancoes() {
		return indicadorDescontoSancoes;
	}

	public void setIndicadorDescontoSancoes(String indicadorDescontoSancoes) {
		this.indicadorDescontoSancoes = indicadorDescontoSancoes;
	}

	public String getIndicadorParcelamentoUnico() {
		return indicadorParcelamentoUnico;
	}

	public void setIndicadorParcelamentoUnico(String indicadorParcelamentoUnico) {
		this.indicadorParcelamentoUnico = indicadorParcelamentoUnico;
	}

	public String getIndicadorUtilizacaoLivre() {
		return indicadorUtilizacaoLivre;
	}

	public void setIndicadorUtilizacaoLivre(String indicadorUtilizacaoLivre) {
		this.indicadorUtilizacaoLivre = indicadorUtilizacaoLivre;
	}

	public String getIdParcelamentoEmAndamento() {
		return idParcelamentoEmAndamento;
	}

	public void setIdParcelamentoEmAndamento(String idParcelamentoEmAndamento) {
		this.idParcelamentoEmAndamento = idParcelamentoEmAndamento;
	}

	public String getIdParcelasEmAtraso() {
		return idParcelasEmAtraso;
	}

	public void setIdParcelasEmAtraso(String idParcelasEmAtraso) {
		this.idParcelasEmAtraso = idParcelasEmAtraso;
	}

	public String getIndicadorParcelamentoEmAndamento() {
		return indicadorParcelamentoEmAndamento;
	}

	public void setIndicadorParcelamentoEmAndamento(
			String indicadorParcelamentoEmAndamento) {
		this.indicadorParcelamentoEmAndamento = indicadorParcelamentoEmAndamento;
	}

	public String getIndicadorParcelasEmAtraso() {
		return indicadorParcelasEmAtraso;
	}

	public void setIndicadorParcelasEmAtraso(String indicadorParcelasEmAtraso) {
		this.indicadorParcelasEmAtraso = indicadorParcelasEmAtraso;
	}

	public String getIndicadorNegociacaoSoAVista() {
		return indicadorNegociacaoSoAVista;
	}

	public void setIndicadorNegociacaoSoAVista(String indicadorNegociacaoSoAVista) {
		this.indicadorNegociacaoSoAVista = indicadorNegociacaoSoAVista;
	}

	public String getIndicadorParcelamentoLojaVirtual() {
		return indicadorParcelamentoLojaVirtual;
	}

	public void setIndicadorParcelamentoLojaVirtual(
			String indicadorParcelamentoLojaVirtual) {
		this.indicadorParcelamentoLojaVirtual = indicadorParcelamentoLojaVirtual;
	}
	
	public String getIndicadorParcelamentoCartaoCredito() {
		return indicadorParcelamentoCartaoCredito;
	}

	public void setIndicadorParcelamentoCartaoCredito(
			String indicadorParcelamentoCartaoCredito) {
		this.indicadorParcelamentoCartaoCredito = indicadorParcelamentoCartaoCredito;
	}
	
}