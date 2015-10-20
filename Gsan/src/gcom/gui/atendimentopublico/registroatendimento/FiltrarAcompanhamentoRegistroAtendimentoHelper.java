/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.gui.atendimentopublico.registroatendimento;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


/**
 * [UC1056] ? Gerar Relat�rio de Acompanhamento dos Registros de Atendimento
 * 
 * Classe que ir� auxiliar no formato de entrada da pesquisa 
 * do relatorio de Acompanhamento dos Registros de Atendimento.
 * 
 * @author Hugo Leonardo
 * @date 28/09/2010
 */

public class FiltrarAcompanhamentoRegistroAtendimentoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String situacaoRA;
	private String situacaoRAAbertos;
	private Date periodoAtendimentoInicial;
	private Date periodoAtendimentoFinal;
	private Date periodoEncerramentoInicial;
	private Date periodoEncerramentoFinal;
	private String idUnidadeAtendimento;
	private Collection<Integer> idsMotivoEncerramentoSelecionados;
	private Collection<Integer> municipiosAssociados;
	
	public String getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}
	
	public void setIdUnidadeAtendimento(String idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}
	
	public String getSituacaoRA() {
		return situacaoRA;
	}
	
	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}
	
	public String getSituacaoRAAbertos() {
		return situacaoRAAbertos;
	}
	
	public void setSituacaoRAAbertos(String situacaoRAAbertos) {
		this.situacaoRAAbertos = situacaoRAAbertos;
	}

	public Date getPeriodoAtendimentoFinal() {
		return periodoAtendimentoFinal;
	}

	public void setPeriodoAtendimentoFinal(Date periodoAtendimentoFinal) {
		this.periodoAtendimentoFinal = periodoAtendimentoFinal;
	}

	public Date getPeriodoAtendimentoInicial() {
		return periodoAtendimentoInicial;
	}

	public void setPeriodoAtendimentoInicial(Date periodoAtendimentoInicial) {
		this.periodoAtendimentoInicial = periodoAtendimentoInicial;
	}

	public Date getPeriodoEncerramentoFinal() {
		return periodoEncerramentoFinal;
	}

	public void setPeriodoEncerramentoFinal(Date periodoEncerramentoFinal) {
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
	}

	public Date getPeriodoEncerramentoInicial() {
		return periodoEncerramentoInicial;
	}

	public void setPeriodoEncerramentoInicial(Date periodoEncerramentoInicial) {
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
	}

	public Collection<Integer> getIdsMotivoEncerramentoSelecionados() {
		return idsMotivoEncerramentoSelecionados;
	}

	public void setIdsMotivoEncerramentoSelecionados(
			Collection<Integer> idsMotivoEncerramentoSelecionados) {
		this.idsMotivoEncerramentoSelecionados = idsMotivoEncerramentoSelecionados;
	}

	public Collection<Integer> getMunicipiosAssociados() {
		return municipiosAssociados;
	}

	public void setMunicipiosAssociados(Collection<Integer> municipiosAssociados) {
		this.municipiosAssociados = municipiosAssociados;
	}
}