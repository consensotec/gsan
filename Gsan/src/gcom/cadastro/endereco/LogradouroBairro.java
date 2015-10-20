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
package gcom.cadastro.endereco;

import gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gcom.cadastro.geografico.Bairro;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class LogradouroBairro extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	public static final int ATUALIZAR_IMPORTANCIA_LOGRADOURO_BAIRRO = 1816;
	
	public Filtro retornaFiltro() {
		FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_BAIRRO, bairro.getId()));
		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO, logradouro.getId()));
		filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade("logradouro");
		filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTitulo");
		filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTipo");
		filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
		return filtroLogradouroBairro;
	}

	/** nullable persistent field */
    private Integer id;
    
    /** nullable persistent field */
    private Logradouro logradouro;

    /** nullable persistent field */
    private Bairro bairro;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    @ControleAlteracao(value=FiltroLogradouroBairro.OS_PROGRAMA_CALIBRAGEM,funcionalidade={ATUALIZAR_IMPORTANCIA_LOGRADOURO_BAIRRO})
    private OSProgramacaoCalibragem programaCalibragem;
    
    /** full constructor */
    public LogradouroBairro(Integer id, Logradouro logradouro, Bairro bairro,
            Date ultimaAlteracao) {
    	this.id = id;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public LogradouroBairro() {
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId())
                .toString();
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof LogradouroBairro))
            return false;
        LogradouroBairro castOther = (LogradouroBairro) other;
        return new EqualsBuilder().append(this.getId(),
                castOther.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public String getDescricaoParaRegistroTransacao(){
		return this.bairro.getNome();
	}

	public OSProgramacaoCalibragem getProgramaCalibragem() {
		return programaCalibragem;
	}

	public void setProgramaCalibragem(OSProgramacaoCalibragem programaCalibragem) {
		this.programaCalibragem = programaCalibragem;
	}
	
	public String getDescricaoFormatada(){
    	
    	String retorno = "";
    	
    	if (this.getLogradouro().getLogradouroTipo() != null){
    		if (this.getLogradouro().getLogradouroTipo().getDescricaoAbreviada() != null)
    		retorno = this.getLogradouro().getLogradouroTipo().getDescricaoAbreviada();
    	}
    	
    	if (this.getLogradouro().getLogradouroTitulo() != null){
    		if (this.getLogradouro().getLogradouroTitulo().getDescricaoAbreviada() != null) {
    			if (retorno.length() > 0) {
    				retorno = retorno + " " + this.getLogradouro().getLogradouroTitulo().getDescricaoAbreviada();
    			}
    			else {
    				retorno = this.getLogradouro().getLogradouroTitulo().getDescricaoAbreviada();
    			}
    		}
    	}
    	
    	if (this.getLogradouro().getNome() != null){
    		if (retorno.length() > 0) {
    			retorno = retorno + " " + this.getLogradouro().getNome();
    		}
    		else {
    			retorno = this.getLogradouro().getNome();
    		}
    	}
    	
    	if (this.getBairro() != null){
    		if (retorno.length() > 0) {
    			retorno = retorno + " " + this.getBairro().getNome();
    		}
    		else {
    			retorno = this.getBairro().getNome();
    		}
    	}
    	
    	return retorno;
    }
	
	@Override
    public void initializeLazy() {
    	getDescricaoFormatada();
    }
    
    @Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID,getId()));
		filtro.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
		return filtro;
	}
}
