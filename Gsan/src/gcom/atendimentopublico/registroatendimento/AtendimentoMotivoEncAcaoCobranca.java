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
package gcom.atendimentopublico.registroatendimento;

import gcom.cobranca.CobrancaAcao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

/** @author Hibernate CodeGenerator */
public class AtendimentoMotivoEncAcaoCobranca extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;

	private AtendimentoMotivoEncAcaoCobrancaPK comp_id;

	/** persistent field */
	private Short indicadorGeraPagamento;

	/** persistent field */
	private Short indicadorGeraSucessor;

	/** persistent field */
	private Short indicadorExibeDocumento;
	
	/** persistent field */
	private Date ultimaAlteracao;
	
	private AtendimentoMotivoEncerramento atendimentoMotivoEncerramento;
	
	private CobrancaAcao cobrancaAcao;

	public AtendimentoMotivoEncAcaoCobranca() {
		super();
	}

	public AtendimentoMotivoEncAcaoCobranca(
			AtendimentoMotivoEncAcaoCobrancaPK comp_id, Short indicadorGeraPagamento, Short indicadorGeraSucessor,
			Short indicadorExibeDocumento, Date ultimaAlteracao,
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento, CobrancaAcao cobrancaAcao) {
		super();
		this.comp_id = comp_id;
		this.indicadorGeraPagamento = indicadorGeraPagamento;
		this.indicadorGeraSucessor = indicadorGeraSucessor;
		this.indicadorExibeDocumento = indicadorExibeDocumento;
		this.ultimaAlteracao = ultimaAlteracao;
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
		this.cobrancaAcao = cobrancaAcao;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}    

	public Filtro retornaFiltro() {
		FiltroAtendimentoMotivoEncAcaoCobranca filtroAtendimentoMotivoEncAcaoCobranca = new FiltroAtendimentoMotivoEncAcaoCobranca();
		
		filtroAtendimentoMotivoEncAcaoCobranca.adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroAtendimentoMotivoEncAcaoCobranca.adicionarCaminhoParaCarregamentoEntidade("atendimentoMotivoEncerramento");
		filtroAtendimentoMotivoEncAcaoCobranca.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao");

		
		filtroAtendimentoMotivoEncAcaoCobranca.adicionarParametro(
						new ParametroSimples(FiltroAtendimentoMotivoEncAcaoCobranca.COMP_ID_ATENDIMENTO_MOTIVO_ENCERRAMENTO_ID, 
							this.getAtendimentoMotivoEncerramento().getId()));
		
		filtroAtendimentoMotivoEncAcaoCobranca.adicionarParametro(
				new ParametroSimples(FiltroAtendimentoMotivoEncAcaoCobranca.COMP_ID_COBRANCA_ACAO_ID, 
					this.getCobrancaAcao().getId()));
		
		return filtroAtendimentoMotivoEncAcaoCobranca; 
	}

	public AtendimentoMotivoEncAcaoCobrancaPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(AtendimentoMotivoEncAcaoCobrancaPK comp_id) {
		this.comp_id = comp_id;
	}

	public Short getIndicadorGeraPagamento() {
		return indicadorGeraPagamento;
	}

	public void setIndicadorGeraPagamento(Short indicadorGeraPagamento) {
		this.indicadorGeraPagamento = indicadorGeraPagamento;
	}

	public Short getIndicadorGeraSucessor() {
		return indicadorGeraSucessor;
	}

	public void setIndicadorGeraSucessor(Short indicadorGeraSucessor) {
		this.indicadorGeraSucessor = indicadorGeraSucessor;
	}

	public Short getIndicadorExibeDocumento() {
		return indicadorExibeDocumento;
	}

	public void setIndicadorExibeDocumento(Short indicadorExibeDocumento) {
		this.indicadorExibeDocumento = indicadorExibeDocumento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public AtendimentoMotivoEncerramento getAtendimentoMotivoEncerramento() {
		return atendimentoMotivoEncerramento;
	}

	public void setAtendimentoMotivoEncerramento(AtendimentoMotivoEncerramento atendimentoMotivoEncerramento) {
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
	}

	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}

	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}
	
	
}
