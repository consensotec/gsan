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
package gcom.cadastro.geografico;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Municipio extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String nome;

    /** nullable persistent field */
    private Integer cepInicio;

    /** nullable persistent field */
    private Integer cepFim;

    /** nullable persistent field */
    private Short ddd;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cadastro.geografico.Microrregiao microrregiao;

    /** persistent field */
    private gcom.cadastro.geografico.RegiaoDesenvolvimento regiaoDesenvolvimento;

    /** persistent field */
    private gcom.cadastro.geografico.UnidadeFederacao unidadeFederacao;

    private Date dataConcessaoInicio;
    
    private Date dataConcessaoFim;
    
    private String codigoIbge;
    
    /** nullable persistent field */
    private Short indicadorRelacaoQuadraBairro;
    
    private Short contratoAdesao;
    
    public Date getDataConcessaoFim() {
        return dataConcessaoFim;
    }

    public void setDataConcessaoFim(Date dataConcessaoFim) {
        this.dataConcessaoFim = dataConcessaoFim;
    }

    public Date getDataConcessaoInicio() {
        return dataConcessaoInicio;
    }

    public void setDataConcessaoInicio(Date dataConcessaoInicio) {
        this.dataConcessaoInicio = dataConcessaoInicio;
    }

    /** full constructor */
    public Municipio(
            String nome,
            Integer cepInicio,
            Integer cepFim,
            Short ddd,
            Short indicadorUso,
            Date ultimaAlteracao,
            gcom.cadastro.geografico.Microrregiao microrregiao,
            gcom.cadastro.geografico.RegiaoDesenvolvimento regiaoDesenvolvimento,
            gcom.cadastro.geografico.UnidadeFederacao unidadeFederacao, String codigoIbge) {
        this.nome = nome;
        this.cepInicio = cepInicio;
        this.cepFim = cepFim;
        this.ddd = ddd;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.microrregiao = microrregiao;
        this.regiaoDesenvolvimento = regiaoDesenvolvimento;
        this.unidadeFederacao = unidadeFederacao;
        this.codigoIbge = codigoIbge;
    }

    /** default constructor */
    public Municipio() {
    }

    /** minimal constructor */
    public Municipio(
            gcom.cadastro.geografico.Microrregiao microrregiao,
            gcom.cadastro.geografico.RegiaoDesenvolvimento regiaoDesenvolvimento,
            gcom.cadastro.geografico.UnidadeFederacao unidadeFederacao) {
        this.microrregiao = microrregiao;
        this.regiaoDesenvolvimento = regiaoDesenvolvimento;
        this.unidadeFederacao = unidadeFederacao;
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

    public Integer getCepInicio() {
        return this.cepInicio;
    }

    public void setCepInicio(Integer cepInicio) {
        this.cepInicio = cepInicio;
    }

    public Integer getCepFim() {
        return this.cepFim;
    }

    public void setCepFim(Integer cepFim) {
        this.cepFim = cepFim;
    }

    public Short getDdd() {
        return this.ddd;
    }

    public void setDdd(Short ddd) {
        this.ddd = ddd;
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

    public gcom.cadastro.geografico.Microrregiao getMicrorregiao() {
        return this.microrregiao;
    }

    public void setMicrorregiao(
            gcom.cadastro.geografico.Microrregiao microrregiao) {
        this.microrregiao = microrregiao;
    }

    public gcom.cadastro.geografico.RegiaoDesenvolvimento getRegiaoDesenvolvimento() {
        return this.regiaoDesenvolvimento;
    }

    public void setRegiaoDesenvolvimento(
            gcom.cadastro.geografico.RegiaoDesenvolvimento regiaoDesenvolvimento) {
        this.regiaoDesenvolvimento = regiaoDesenvolvimento;
    }

    public gcom.cadastro.geografico.UnidadeFederacao getUnidadeFederacao() {
        return this.unidadeFederacao;
    }

    public void setUnidadeFederacao(
            gcom.cadastro.geografico.UnidadeFederacao unidadeFederacao) {
        this.unidadeFederacao = unidadeFederacao;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
    
    
    public Filtro retornaFiltro(){
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID,this.getId()));
		filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("microrregiao");
		filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("regiaoDesenvolvimento");
		filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
		
		return filtroMunicipio;
	}

	public String getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public Short getIndicadorRelacaoQuadraBairro() {
		return indicadorRelacaoQuadraBairro;
	}

	public void setIndicadorRelacaoQuadraBairro(Short indicadorRelacaoQuadraBairro) {
		this.indicadorRelacaoQuadraBairro = indicadorRelacaoQuadraBairro;
	}
	
	public Short getContratoAdesao() {
		return contratoAdesao;
	}

	public void setContratoAdesao(Short contratoAdesao) {
		this.contratoAdesao = contratoAdesao;
	}
}
