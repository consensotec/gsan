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
package gcom.seguranca.transacao;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;



/** @author Hibernate CodeGenerator */
public class TabelaColunaAtualizacaoCadastral extends ObjetoGcom {
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String colunaValorAnterior;

    /** nullable persistent field */
    private String colunaValorAtual;

    /** nullable persistent field */
    
    private Short indicadorAutorizado;
    
    /** nullable persistent field */
    private Date dataProcessamento;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral;
    
    private TabelaColuna tabelaColuna;

    public TabelaColunaAtualizacaoCadastral(Integer id, String colunaValorAnterior, String colunaValorAtual, Short indicadorAutorizado, 
    		Date dataProcessamento, Date ultimaAlteracao, TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral, TabelaColuna tabelaColuna) {

		this.id = id;
		this.colunaValorAnterior = colunaValorAnterior;
		this.colunaValorAtual = colunaValorAtual;
		this.indicadorAutorizado = indicadorAutorizado;
		this.dataProcessamento = dataProcessamento;
		this.ultimaAlteracao = ultimaAlteracao;
		this.tabelaAtualizacaoCadastral = tabelaAtualizacaoCadastral;
		this.tabelaColuna = tabelaColuna;
	}

	/** default constructor */
    public TabelaColunaAtualizacaoCadastral() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
	public String getColunaValorAnterior() {
		return colunaValorAnterior;
	}

	public void setColunaValorAnterior(String colunaValorAnterior) {
		this.colunaValorAnterior = colunaValorAnterior;
	}

	public String getColunaValorAtual() {
		return colunaValorAtual;
	}

	public void setColunaValorAtual(String colunaValorAtual) {
		this.colunaValorAtual = colunaValorAtual;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public Short getIndicadorAutorizado() {
		return indicadorAutorizado;
	}

	public void setIndicadorAutorizado(Short indicadorAutorizado) {
		this.indicadorAutorizado = indicadorAutorizado;
	}

	public TabelaAtualizacaoCadastral getTabelaAtualizacaoCadastral() {
		return tabelaAtualizacaoCadastral;
	}

	public void setTabelaAtualizacaoCadastral(
			TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral) {
		this.tabelaAtualizacaoCadastral = tabelaAtualizacaoCadastral;
	}

	public TabelaColuna getTabelaColuna() {
		return tabelaColuna;
	}

	public void setTabelaColuna(TabelaColuna tabelaColuna) {
		this.tabelaColuna = tabelaColuna;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
}