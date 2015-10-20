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

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.MovimentoCartaoRejeita;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author S�vio Luiz
 * @created 01 de Fevereiro de 2006 [UC0259] - Processar Pagamento com C�digo de
 *          Barras
 */
public class PagamentoHelperCodigoBarras implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public PagamentoHelperCodigoBarras() {
	}

	private String indicadorAceitacaoRegistro;

	private String descricaoOcorrencia;

	private Collection colecaoPagamentos;
	
	private Collection<Devolucao> colecaoDevolucao;
	
	private Collection<ProcessarPagamentoParcialContaHelper> colecaoProcessarPagamentoParcialContaHelper;
	
	//CART�O DE CR�DITO
	private MovimentoCartaoRejeita movimentoCartaoRejeita;

	public Collection<ProcessarPagamentoParcialContaHelper> getColecaoProcessarPagamentoParcialContaHelper() {
		return colecaoProcessarPagamentoParcialContaHelper;
	}

	public void setColecaoProcessarPagamentoParcialContaHelper(
			Collection<ProcessarPagamentoParcialContaHelper> colecaoProcessarPagamentoParcialContaHelper) {
		this.colecaoProcessarPagamentoParcialContaHelper = colecaoProcessarPagamentoParcialContaHelper;
	}

	public Collection getColecaoPagamentos() {
		return colecaoPagamentos;
	}

	public void setColecaoPagamentos(Collection colecaoPagamentos) {
		this.colecaoPagamentos = colecaoPagamentos;
	}

	public String getDescricaoOcorrencia() {
		return descricaoOcorrencia;
	}

	public void setDescricaoOcorrencia(String descricaoOcorrencia) {
		this.descricaoOcorrencia = descricaoOcorrencia;
	}

	public String getIndicadorAceitacaoRegistro() {
		return indicadorAceitacaoRegistro;
	}

	public void setIndicadorAceitacaoRegistro(String indicadorAceitacaoRegistro) {
		this.indicadorAceitacaoRegistro = indicadorAceitacaoRegistro;
	}

	public Collection<Devolucao> getColecaoDevolucao() {
		return colecaoDevolucao;
	}

	public void setColecaoDevolucao(Collection<Devolucao> colecaoDevolucao) {
		this.colecaoDevolucao = colecaoDevolucao;
	}

	public MovimentoCartaoRejeita getMovimentoCartaoRejeita() {
		return movimentoCartaoRejeita;
	}

	public void setMovimentoCartaoRejeita(
			MovimentoCartaoRejeita movimentoCartaoRejeita) {
		this.movimentoCartaoRejeita = movimentoCartaoRejeita;
	}
}
