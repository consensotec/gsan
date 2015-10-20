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
package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * Objeto que pode ser um dos 4 SF desses dependendo do tipo de pagamento
 * 
 * @author S�vio Luiz
 * @created 30 de Janeiro de 2006 
 * [UC0264] - Distribuir Dados do C�digo de Barras. 
 * [SF0001] - Distribuir Pagamento de Conta 
 * [SF0002] - Distribuir Pagamento de Guia de Pagamento 
 * [SF0003] - Distribuir Pagamento de Documento de Cobram�a 
 * [SF0004] - Distribuir Pagamento de Fatura do Cliente Respons�vel
 */
public class RegistroHelperCodigoBarrasTipoPagamento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigoBarrasTipoPagamento() {
	}

	// campos do idPagamento que pode ser de um dos 4 SF,como podem ser campos
	// diferentes
	// dependendo do SF ent�o tem esses n�meros
	private String idPagamento1;

	private String idPagamento2;

	private String idPagamento3;

	private String idPagamento4;

	private String idPagamento5;

	private String idPagamento6;
	
	/*---------------------------------------------------------------------------------------------------------------
	 * [UC0264] - Distribuir Dados do C�digo de Barras
	 * - Caso o tipo de pagamento(campo G05.8) corresponda a Conta(valor = 3).
	 *   .idPagamento1 = C�digo da Localidade.
	 *   .idPagamento2 = M�tricula do Im�vel.
	 *   .idPagamento3 = N�o utilizado.
	 *   .idPagamento4 = M�s e Ano de Refer�ncia da Conta (MMAAAA).
	 *   .idPagamento5 = Digito Verificador da Conta no modulo 10.
	 *   .idPagamento6 = N�o Utilizado.
	 *   
	 * - Caso o tipo de pagamento(campo G05.8) corresponda a Guia Pagamento(valor = 4)
	 *   .idPagamento1 = C�digo da Localidade.
	 *   .idPagamento2 = M�tricula do Im�vel.
	 *   .idPagamento3 = N�o utilizado.
	 *   .idPagamento4 = C�digo do tipo do d�bito(DBTP_ID).
	 *   .idPagamento5 = Ano da Emiss�o da Guia de Pagamento(AAAA).
	 *   .idPagamento6 = N�o Utilizado.
	 * 
	 * - Caso o tipo de pagamento(campo G05.8) corresponda a Documento de Cobran�a(valor = 5)
	 *   .idPagamento1 = C�digo da Localidade.
	 *   .idPagamento2 = M�tricula do Im�vel.
	 *   .idPagamento3 = Sequencia do Documento de Cobran�a.
	 *   .idPagamento4 = C�digo do tipo de Documento(DOTP_ID).
	 *   .idPagamento5 = N�o Utilizado.
	 * 
	 * - Caso o tipo de pagamento(campo G05.8) corresponda a Fatura do Cliente Respons�vel (valor = 7)
	 *   .idPagamento1 = N�o Utilizado.
	 *   .idPagamento2 = C�digo do Cliente Respons�vel(CLIE_ID).
	 *   .idPagamento3 = N�o Utilizado.
	 *   .idPagamento4 = M�s e Ano de Refer�ncia da Conta (MMAAAA).
	 *   .idPagamento5 = Digito Verificador da Conta no modulo 10.
	 *   .idPagamento6 = Sequencial da Fatura do Cliente Respons�vel.
	 *
	 * - Identifica��o da Empresa for CAER (campo G05.6 = 0004)
	 * 
	 * 		.idPagamento1 = Tipo do Documento (G05.7.1).
	 * 		.idPagamento2 = Identifica��o.
	 *
	 * 		- Caso o tipo de pagamento(campo G05.7.1) corresponda a Conta e Segunda Via (valor = 1)
	 *   		.idPagamento3 = Matricula do Imovel (IMOV_ID).
	 *   		.idPagamento4 = Ano e M�s de Refer�ncia da Conta (AAAAMM).
	 *   		.idPagamento5 = Codigo Origem da Conta.
	 *   		.idPagamento6 = Numero do Documento + campo G05.8.  
	 * 		
	 * 		- Caso o tipo de pagamento(campo G05.7.1) corresponda a Fatura (valor = 2)
	 *   		.idPagamento3 = Qualifica��o.
	 *   		.idPagamento4 = Ano e M�s de Refer�ncia da Conta (AAAAMM).
	 *   		.idPagamento5 = Numero do Documento + campo G05.8.   
	 * 		
	 * 		- Caso o tipo de pagamento(campo G05.7.1) corresponda a Reaviso de Debito (valor = 3)
	 *   		.idPagamento3 = Matricula do Imovel (IMOV_ID).
	 *   		.idPagamento4 = Numero do Documento + campo G05.8. 
	 *   		    
	 *   
	 -----------------------------------------------------------------------------------------------------------------*/

	public String getIdPagamento1() {
		return idPagamento1;
	}

	public void setIdPagamento1(String idPagamento1) {
		this.idPagamento1 = idPagamento1;
	}

	public String getIdPagamento2() {
		return idPagamento2;
	}

	public void setIdPagamento2(String idPagamento2) {
		this.idPagamento2 = idPagamento2;
	}

	public String getIdPagamento3() {
		return idPagamento3;
	}

	public void setIdPagamento3(String idPagamento3) {
		this.idPagamento3 = idPagamento3;
	}

	public String getIdPagamento4() {
		return idPagamento4;
	}

	public void setIdPagamento4(String idPagamento4) {
		this.idPagamento4 = idPagamento4;
	}

	public String getIdPagamento5() {
		return idPagamento5;
	}

	public void setIdPagamento5(String idPagamento5) {
		this.idPagamento5 = idPagamento5;
	}

	public String getIdPagamento6() {
		return idPagamento6;
	}

	public void setIdPagamento6(String idPagamento6) {
		this.idPagamento6 = idPagamento6;
	}

}
