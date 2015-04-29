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
package gsan.cadastro.endereco;

import gsan.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gsan.cadastro.geografico.Bairro;
import gsan.cadastro.geografico.Municipio;
import gsan.interceptor.ControleAlteracao;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class LogradouroImportanciaBairro extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	//Retirado conforme RM 6141
	public static final int ATUALIZAR_IMPORTANCIA_LOGRADOURO = 1816;
	
	public Filtro retornaFiltro() {
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID,this.getId()));
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("municipio");
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
		
		//Retirado conforme RM 6141
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
		
		return filtroLogradouro;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}


    /** identifier field */
    private Integer id;

    /** persistent field */
    private String nome;
    
    /** persistent field */
    private String nomePopular;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Municipio municipio;

    /** persistent field */
    private gsan.cadastro.endereco.LogradouroTitulo logradouroTitulo;

    /** persistent field */
    private gsan.cadastro.endereco.LogradouroTipo logradouroTipo;
    
    /** persistent field */
    private Bairro bairro;
    
    /** persistent field */
    private Cep cep;
    
    /** persistent field */
    @ControleAlteracao(value=FiltroLogradouro.OS_PROGRAMA_CALIBRAGEM,funcionalidade={ATUALIZAR_IMPORTANCIA_LOGRADOURO})
    private OSProgramacaoCalibragem programaCalibragem;

    
    
    public OSProgramacaoCalibragem getProgramaCalibragem() {
		return programaCalibragem;
	}

	public void setProgramaCalibragem(OSProgramacaoCalibragem programaCalibragem) {
		this.programaCalibragem = programaCalibragem;
	}

	/** full constructor */
    public LogradouroImportanciaBairro(String nome,String nomePopular, Short indicadorUso, Date ultimaAlteracao,
            Municipio municipio,
            gsan.cadastro.endereco.LogradouroTitulo logradouroTitulo,
            gsan.cadastro.endereco.LogradouroTipo logradouroTipo) {
        this.nome = nome;
        this.nomePopular = nomePopular;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.municipio = municipio;
        this.logradouroTitulo = logradouroTitulo;
        this.logradouroTipo = logradouroTipo;
    }

    /** full constructor */
    public LogradouroImportanciaBairro(String nome, Short indicadorUso, Date ultimaAlteracao,
            Municipio municipio,
            gsan.cadastro.endereco.LogradouroTitulo logradouroTitulo,
            gsan.cadastro.endereco.LogradouroTipo logradouroTipo) {
        this.nome = nome;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.municipio = municipio;
        this.logradouroTitulo = logradouroTitulo;
        this.logradouroTipo = logradouroTipo;
    }
    
    

    public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cep getCep() {
		return cep;
	}

	public void setCep(Cep cep) {
		this.cep = cep;
	}

	/**
	 * @return Retorna o campo nomePopular.
	 */
	public String getNomePopular() {
		return nomePopular;
	}

	/**
	 * @param nomePopular O nomePopular a ser setado.
	 */
	public void setNomePopular(String nomePopular) {
		this.nomePopular = nomePopular;
	}

	/** default constructor */
    public LogradouroImportanciaBairro() {
    }

    /** minimal constructor */
    public LogradouroImportanciaBairro(String nome, Municipio municipio,
            gsan.cadastro.endereco.LogradouroTitulo logradouroTitulo,
            gsan.cadastro.endereco.LogradouroTipo logradouroTipo) {
        this.nome = nome;
        this.municipio = municipio;
        this.logradouroTitulo = logradouroTitulo;
        this.logradouroTipo = logradouroTipo;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public gsan.cadastro.endereco.LogradouroTitulo getLogradouroTitulo() {
        return this.logradouroTitulo;
    }

    public void setLogradouroTitulo(
            gsan.cadastro.endereco.LogradouroTitulo logradouroTitulo) {
        this.logradouroTitulo = logradouroTitulo;
    }

    public gsan.cadastro.endereco.LogradouroTipo getLogradouroTipo() {
        return this.logradouroTipo;
    }

    public void setLogradouroTipo(
            gsan.cadastro.endereco.LogradouroTipo logradouroTipo) {
        this.logradouroTipo = logradouroTipo;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    /**
     * Author: Raphael Rossiter
     * Data: 04/02/2006
     * @param logradouro
     * @return Descri��o completa do logradouro (Tipo + Titulo + Nome)
     */
    public String getDescricaoFormatada(){
    	
    	String retorno = "";
    	
    	if (this.getLogradouroTipo() != null){
    		if (this.getLogradouroTipo().getDescricaoAbreviada() != null)
    		retorno = this.getLogradouroTipo().getDescricaoAbreviada();
    	}
    	
    	if (this.getLogradouroTitulo() != null){
    		if (this.getLogradouroTitulo().getDescricaoAbreviada() != null) {
    			if (retorno.length() > 0) {
    				retorno = retorno + " " + this.getLogradouroTitulo().getDescricaoAbreviada();
    			}
    			else {
    				retorno = this.getLogradouroTitulo().getDescricaoAbreviada();
    			}
    		}
    	}
    	
    	if (this.getNome() != null){
    		if (retorno.length() > 0) {
    			retorno = retorno + " " + this.getNome();
    		}
    		else {
    			retorno = this.getNome();
    		}
    	}
    	
    	return retorno;
    }

    @Override
    public void initializeLazy() {
    	getDescricaoFormatada();
    }
    
//    @Override
//    public String getDescricaoParaRegistroTransacao() {
//    	return getId() + " " + getDescricaoFormatada();
//    }
    
    @Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID,getId()));
		filtro.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
		return filtro;
	}


    
}



