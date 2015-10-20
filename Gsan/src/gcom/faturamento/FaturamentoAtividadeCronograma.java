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
package gcom.faturamento;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FaturamentoAtividadeCronograma extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date dataPrevista;

    /** nullable persistent field */
    private Date dataRealizacao;

    /** nullable persistent field */
    private Date comando;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.faturamento.FaturamentoAtividade faturamentoAtividade;

    /** persistent field */
    private gcom.faturamento.FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal;
    
    /** persistent field */
    private Usuario usuario;
    
    private Set faturamentoAtividadeCronogramaRotas;
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();
		filtroFaturamentoAtividadeCronograma. adicionarCaminhoParaCarregamentoEntidade("faturamentoAtividade");
		filtroFaturamentoAtividadeCronograma. adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal");
		filtroFaturamentoAtividadeCronograma. adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtroFaturamentoAtividadeCronograma. adicionarParametro(
				new ParametroSimples(FiltroFaturamentoAtividadeCronograma .ID, this.getId()));
		return filtroFaturamentoAtividadeCronograma; 
	}

    /** full constructor */
    public FaturamentoAtividadeCronograma(Date dataPrevista, Date dataRealizacao, Date comando, Date ultimaAlteracao, gcom.faturamento.FaturamentoAtividade faturamentoAtividade, gcom.faturamento.FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal, Usuario usuario) {
        this.dataPrevista = dataPrevista;
        this.dataRealizacao = dataRealizacao;
        this.comando = comando;
        this.ultimaAlteracao = ultimaAlteracao;
        this.faturamentoAtividade = faturamentoAtividade;
        this.faturamentoGrupoCronogramaMensal = faturamentoGrupoCronogramaMensal;
        this.usuario = usuario;
    }

    /** default constructor */
    public FaturamentoAtividadeCronograma() {
    }

    /** minimal constructor */
    public FaturamentoAtividadeCronograma(gcom.faturamento.FaturamentoAtividade faturamentoAtividade, gcom.faturamento.FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal) {
        this.faturamentoAtividade = faturamentoAtividade;
        this.faturamentoGrupoCronogramaMensal = faturamentoGrupoCronogramaMensal;
    }
    
    /**
     * Construtor usado no repositorio de faturamento   
     */
    public FaturamentoAtividadeCronograma(Integer id, Date dataPrevista, Date dataRealizacao, Date comando, Date ultimaAlteracao, gcom.faturamento.FaturamentoAtividade faturamentoAtividade, gcom.faturamento.FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal) {
    	this.id = id;
    	this.dataPrevista = dataPrevista;
        this.dataRealizacao = dataRealizacao;
        this.comando = comando;
        this.ultimaAlteracao = ultimaAlteracao;
        this.faturamentoAtividade = faturamentoAtividade;
        this.faturamentoGrupoCronogramaMensal = faturamentoGrupoCronogramaMensal;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataPrevista() {
        return this.dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Date getDataRealizacao() {
        return this.dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Date getComando() {
        return this.comando;
    }

    public void setComando(Date comando) {
        this.comando = comando;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.FaturamentoAtividade getFaturamentoAtividade() {
        return this.faturamentoAtividade;
    }

    public void setFaturamentoAtividade(gcom.faturamento.FaturamentoAtividade faturamentoAtividade) {
        this.faturamentoAtividade = faturamentoAtividade;
    }

    public gcom.faturamento.FaturamentoGrupoCronogramaMensal getFaturamentoGrupoCronogramaMensal() {
        return this.faturamentoGrupoCronogramaMensal;
    }

    public void setFaturamentoGrupoCronogramaMensal(gcom.faturamento.FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal) {
        this.faturamentoGrupoCronogramaMensal = faturamentoGrupoCronogramaMensal;
    }

    /**
     * Retorna o valor de diaAnoMesReferencia
     * 
     * @return O valor de diaAnoMesReferencia
     */
    public String getDiaAnoMesPrevista() {
        String dataReferencia = null;
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");

        dataReferencia = data.format(this.dataPrevista);
        return dataReferencia;
    }
    
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Set getFaturamentoAtividadeCronogramaRotas() {
		return faturamentoAtividadeCronogramaRotas;
	}

	public void setFaturamentoAtividadeCronogramaRotas(
			Set faturamentoAtividadeCronogramaRotas) {
		this.faturamentoAtividadeCronogramaRotas = faturamentoAtividadeCronogramaRotas;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
