/**
 * 
 */
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
package gcom.faturamento.debito;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os crit�rios de busca de um debito a cobrar
 * @author Rafael Santos
 * @since 30/12/2005
 *
 */
public class FiltroDebitoACobrar extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the FiltroDebitoACobrar object
	 */
	public FiltroDebitoACobrar() {
	}

	/**
	 * Constructor for the FiltroDebitoACobrar object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroDebitoACobrar(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	/**
	 * Description of the Field
	 */
	public final static String ID = "id";
	
	/**
	 * Description of the Field
	 */
	public final static String IMOVEL_ID = "imovel.id";
	
	public final static String IMOVEL = "imovel";
	
	
	/**
	 * Description of the Field
	 */
	public final static String FINANCIAMENTO_TIPO = "financiamentoTipo";
	
	public final static String FINANCIAMENTO_TIPO_ID = "financiamentoTipo.id";
	
	public final static String FINANCIAMENTO_TIPO_ICINCLUSAO = "financiamentoTipo.indicadorInclusao";
	
	/**
	 * Description of the Field
	 */
	public final static String DEBITO_TIPO = "debitoTipo";
	
	/**
	 * 
	 */
	public final static String DEBITO_CREDITO_SITUACAO =  "debitoCreditoSituacaoAtual";

	
	public final static String REFERENCIA_DEBITO = "anoMesReferenciaDebito";
	
	public final static String GERACAO_DEBITO = "geracaoDebito";
	
	public final static String DEBITO_TIPO_ID = "debitoTipo.id";
	
	public final static String DEBITO_CREDITO_SITUACAO_ATUAL_ID =  "debitoCreditoSituacaoAtual.id";
	
	public final static String PARCELAMENTO_ID = "parcelamento.id";
	
	public final static String ID_REGISTRO_ATENDIMENTO = "registroAtendimento.id";
	
	public final static String NUMERO_PRESTACOES_COBRADAS = "numeroPrestacaoCobradas";
	
	public final static String NUMERO_PRESTACOES_DEBITO = "numeroPrestacaoDebito";
	
	public final static String ID_CREDITO_TIPO = "creditoTipo.id";
	
	public final static String LANCAMENTO_ITEM_CONTABIL = "lancamentoItemContabil";
	
	public final static String ORDEM_SERVICO = "ordemServico";
	
	public final static String REGISTRO_ATENDIMENTO = "registroAtendimento";
	
	public final static String PARCELAMENTO = "parcelamento";
	
	public final static String COBRANCA_FORMA = "cobrancaForma";

}
