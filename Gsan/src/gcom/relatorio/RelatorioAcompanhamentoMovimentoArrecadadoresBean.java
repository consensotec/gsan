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
package gcom.relatorio;

import gcom.relatorio.RelatorioBean;

/**
 * Bean respons�vel de pegar os parametros que ser�o exibidos na parte de detail
 * do relat�rio.
 * 
 * @author S�vio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioAcompanhamentoMovimentoArrecadadoresBean implements RelatorioBean {

	private String banco;

	private String formaArrecadacao;

	private String dia;

	private String valorDia;

	private String qtdePagamentos;
	
	private String qtdeDocumentos;
	
	private String valorAteDia;
	
	private String qtdePagamentosAteDia;
	
	private String qtdeDocumentosAteDia;

	/**
	 * Construtor da classe RelatorioAcompanhamentoMovimentoArrecadadoresBean
	 */
	public RelatorioAcompanhamentoMovimentoArrecadadoresBean(String banco,
			String formaArrecadacao, String dia, String valorDia,
			String qtdePagamentos, String qtdeDocumentos, String valorAteDia,
			String qtdePagamentosAteDia, String qtdeDocumentosAteDia) {
		
		this.banco = banco;
		this.formaArrecadacao = formaArrecadacao;
		this.dia = dia;
		this.valorDia = valorDia;
		this.qtdePagamentos = qtdePagamentos;
		this.qtdeDocumentos = qtdeDocumentos;
		this.valorAteDia = valorAteDia;
		this.qtdePagamentosAteDia = qtdePagamentosAteDia;
		this.qtdeDocumentosAteDia = qtdeDocumentosAteDia;

	}

	/**
	 * @return Retorna o campo banco.
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @param banco O banco a ser setado.
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * @return Retorna o campo dia.
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * @param dia O dia a ser setado.
	 */
	public void setDia(String dia) {
		this.dia = dia;
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

	/**
	 * @return Retorna o campo qtdeDocumentos.
	 */
	public String getQtdeDocumentos() {
		return qtdeDocumentos;
	}

	/**
	 * @param qtdeDocumentos O qtdeDocumentos a ser setado.
	 */
	public void setQtdeDocumentos(String qtdeDocumentos) {
		this.qtdeDocumentos = qtdeDocumentos;
	}

	/**
	 * @return Retorna o campo qtdeDocumentosAteDia.
	 */
	public String getQtdeDocumentosAteDia() {
		return qtdeDocumentosAteDia;
	}

	/**
	 * @param qtdeDocumentosAteDia O qtdeDocumentosAteDia a ser setado.
	 */
	public void setQtdeDocumentosAteDia(String qtdeDocumentosAteDia) {
		this.qtdeDocumentosAteDia = qtdeDocumentosAteDia;
	}

	/**
	 * @return Retorna o campo qtdePagamentos.
	 */
	public String getQtdePagamentos() {
		return qtdePagamentos;
	}

	/**
	 * @param qtdePagamentos O qtdePagamentos a ser setado.
	 */
	public void setQtdePagamentos(String qtdePagamentos) {
		this.qtdePagamentos = qtdePagamentos;
	}

	/**
	 * @return Retorna o campo qtdePagamentosAteDia.
	 */
	public String getQtdePagamentosAteDia() {
		return qtdePagamentosAteDia;
	}

	/**
	 * @param qtdePagamentosAteDia O qtdePagamentosAteDia a ser setado.
	 */
	public void setQtdePagamentosAteDia(String qtdePagamentosAteDia) {
		this.qtdePagamentosAteDia = qtdePagamentosAteDia;
	}

	/**
	 * @return Retorna o campo valorAteDia.
	 */
	public String getValorAteDia() {
		return valorAteDia;
	}

	/**
	 * @param valorAteDia O valorAteDia a ser setado.
	 */
	public void setValorAteDia(String valorAteDia) {
		this.valorAteDia = valorAteDia;
	}

	/**
	 * @return Retorna o campo valorDia.
	 */
	public String getValorDia() {
		return valorDia;
	}

	/**
	 * @param valorDia O valorDia a ser setado.
	 */
	public void setValorDia(String valorDia) {
		this.valorDia = valorDia;
	}


}
