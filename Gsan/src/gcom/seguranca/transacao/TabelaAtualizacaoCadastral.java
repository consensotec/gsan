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


import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.OperacaoEfetuada;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/** @author Hibernate CodeGenerator */
public class TabelaAtualizacaoCadastral implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer idRegistroAlterado;
	
	private Short indicadorPrincipal;
	
    private Date ultimaAlteracao;
	
	private Tabela tabela;
	
	private OperacaoEfetuada operacaoEfetuada;
	
	private Leiturista leiturista;
	
	private AlteracaoTipo alteracaoTipo;
	
	private ArquivoTextoAtualizacaoCadastral arquivoTextoAtualizacaoCadastral;
	
    private Short indicadorAutorizado;
    
    private Integer codigoImovel;
    
    private Integer codigoCliente;
    
    private Date dataProcessamento;
    
    private TabelaAtualizacaoCadastralSituacao tabelaAtualizacaoCadastralSituacao;
    
    private ImovelAtualizacaoCadastral imovelAtualizacaoCadastral;

	public TabelaAtualizacaoCadastral(Integer id, Integer idRegistroAlterado, Short indicadorPrincipal, Date ultimaAlteracao, Tabela tabela, OperacaoEfetuada operacaoEfetuada, Leiturista leiturista, AlteracaoTipo alteracaoTipo, ArquivoTextoAtualizacaoCadastral arquivoTextoAtualizacaoCadastral, Short indicadorAutorizado, Integer codigoImovel, Integer codigoCliente) {
		this.id = id;
		this.idRegistroAlterado = idRegistroAlterado;
		this.indicadorPrincipal = indicadorPrincipal;
		this.ultimaAlteracao = ultimaAlteracao;
		this.tabela = tabela;
		this.operacaoEfetuada = operacaoEfetuada;
		this.leiturista = leiturista;
		this.alteracaoTipo = alteracaoTipo;
		this.arquivoTextoAtualizacaoCadastral = arquivoTextoAtualizacaoCadastral;
		this.indicadorAutorizado = indicadorAutorizado;
		this.codigoImovel = codigoImovel;
		this.codigoCliente = codigoCliente;
	}

	/** default constructor */
    public TabelaAtualizacaoCadastral() {
    }

 
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (!(other instanceof TabelaAtualizacaoCadastral))
			return false;
		TabelaAtualizacaoCadastral castOther = (TabelaAtualizacaoCadastral) other;
		return new EqualsBuilder().append(this.getId(),
				castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}
	
	 public String[] retornaCamposChavePrimaria(){
			String[] retorno = new String[1];
			retorno[0] = "id";
			return retorno;
	}

	public AlteracaoTipo getAlteracaoTipo() {
		return alteracaoTipo;
	}

	public void setAlteracaoTipo(AlteracaoTipo alteracaoTipo) {
		this.alteracaoTipo = alteracaoTipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdRegistroAlterado() {
		return idRegistroAlterado;
	}

	public void setIdRegistroAlterado(Integer idRegistroAlterado) {
		this.idRegistroAlterado = idRegistroAlterado;
	}

	public Short getIndicadorPrincipal() {
		return indicadorPrincipal;
	}

	public void setIndicadorPrincipal(Short indicadorPrincipal) {
		this.indicadorPrincipal = indicadorPrincipal;
	}

	public Leiturista getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(Leiturista leiturista) {
		this.leiturista = leiturista;
	}

	public OperacaoEfetuada getOperacaoEfetuada() {
		return operacaoEfetuada;
	}

	public void setOperacaoEfetuada(OperacaoEfetuada operacaoEfetuada) {
		this.operacaoEfetuada = operacaoEfetuada;
	}

	public Tabela getTabela() {
		return tabela;
	}

	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ArquivoTextoAtualizacaoCadastral getArquivoTextoAtualizacaoCadastral() {
		return arquivoTextoAtualizacaoCadastral;
	}

	public void setArquivoTextoAtualizacaoCadastral(
			ArquivoTextoAtualizacaoCadastral arquivoTextoAtualizacaoCadastral) {
		this.arquivoTextoAtualizacaoCadastral = arquivoTextoAtualizacaoCadastral;
	}

	public Short getIndicadorAutorizado() {
		return indicadorAutorizado;
	}

	public void setIndicadorAutorizado(Short indicadorAutorizado) {
		this.indicadorAutorizado = indicadorAutorizado;
	}
	
	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Integer getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(Integer codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public TabelaAtualizacaoCadastralSituacao getTabelaAtualizacaoCadastralSituacao() {
		return tabelaAtualizacaoCadastralSituacao;
	}

	public void setTabelaAtualizacaoCadastralSituacao(
			TabelaAtualizacaoCadastralSituacao tabelaAtualizacaoCadastralSituacao) {
		this.tabelaAtualizacaoCadastralSituacao = tabelaAtualizacaoCadastralSituacao;
	}

	public ImovelAtualizacaoCadastral getImovelAtualizacaoCadastral() {
		return imovelAtualizacaoCadastral;
	}

	public void setImovelAtualizacaoCadastral(
			ImovelAtualizacaoCadastral imovelAtualizacaoCadastral) {
		this.imovelAtualizacaoCadastral = imovelAtualizacaoCadastral;
	}

}
