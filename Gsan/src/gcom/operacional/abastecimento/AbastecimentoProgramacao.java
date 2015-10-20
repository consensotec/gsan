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
package gcom.operacional.abastecimento;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.Municipio;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AbastecimentoProgramacao extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private Date dataInicio;

    /** persistent field */
    private Date dataFim;

    /** persistent field */
    private Date horaInicio;

    /** persistent field */
    private Date horaFim;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.operacional.SistemaAbastecimento sistemaAbastecimento;

    /** persistent field */
    private Municipio municipio;

    /** persistent field */
    private Bairro bairro;

    /** persistent field */
    private gcom.operacional.SetorAbastecimento setorAbastecimento;

    /** persistent field */
    private gcom.operacional.DistritoOperacional distritoOperacional;

    /** persistent field */
    private gcom.operacional.ZonaAbastecimento zonaAbastecimento;

    /** persistent field */
    private BairroArea bairroArea;

    /** full constructor */
    public AbastecimentoProgramacao(int anoMesReferencia, Date dataInicio, Date dataFim, Date horaInicio, Date horaFim, Date ultimaAlteracao, gcom.operacional.SistemaAbastecimento sistemaAbastecimento, Municipio municipio, Bairro bairro, gcom.operacional.SetorAbastecimento setorAbastecimento, gcom.operacional.DistritoOperacional distritoOperacional, gcom.operacional.ZonaAbastecimento zonaAbastecimento, BairroArea bairroArea) {
        this.anoMesReferencia = anoMesReferencia;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.ultimaAlteracao = ultimaAlteracao;
        this.sistemaAbastecimento = sistemaAbastecimento;
        this.municipio = municipio;
        this.bairro = bairro;
        this.setorAbastecimento = setorAbastecimento;
        this.distritoOperacional = distritoOperacional;
        this.zonaAbastecimento = zonaAbastecimento;
        this.bairroArea = bairroArea;
    }

    /** default constructor */
    public AbastecimentoProgramacao() {
    }

    /** minimal constructor */
    public AbastecimentoProgramacao(int anoMesReferencia, Date dataInicio, Date dataFim, Date horaInicio, Date horaFim, Date ultimaAlteracao, Municipio municipio, Bairro bairro, gcom.operacional.SetorAbastecimento setorAbastecimento, gcom.operacional.DistritoOperacional distritoOperacional, gcom.operacional.ZonaAbastecimento zonaAbastecimento, BairroArea bairroArea) {
        this.anoMesReferencia = anoMesReferencia;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.ultimaAlteracao = ultimaAlteracao;
        this.municipio = municipio;
        this.bairro = bairro;
        this.setorAbastecimento = setorAbastecimento;
        this.distritoOperacional = distritoOperacional;
        this.zonaAbastecimento = zonaAbastecimento;
        this.bairroArea = bairroArea;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(int anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public Date getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getHoraInicio() {
        return this.horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return this.horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.operacional.SistemaAbastecimento getSistemaAbastecimento() {
        return this.sistemaAbastecimento;
    }

    public void setSistemaAbastecimento(gcom.operacional.SistemaAbastecimento sistemaAbastecimento) {
        this.sistemaAbastecimento = sistemaAbastecimento;
    }

    public Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Bairro getBairro() {
        return this.bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public gcom.operacional.SetorAbastecimento getSetorAbastecimento() {
        return this.setorAbastecimento;
    }

    public void setSetorAbastecimento(gcom.operacional.SetorAbastecimento setorAbastecimento) {
        this.setorAbastecimento = setorAbastecimento;
    }

    public gcom.operacional.DistritoOperacional getDistritoOperacional() {
        return this.distritoOperacional;
    }

    public void setDistritoOperacional(gcom.operacional.DistritoOperacional distritoOperacional) {
        this.distritoOperacional = distritoOperacional;
    }

    public gcom.operacional.ZonaAbastecimento getZonaAbastecimento() {
        return this.zonaAbastecimento;
    }

    public void setZonaAbastecimento(gcom.operacional.ZonaAbastecimento zonaAbastecimento) {
        this.zonaAbastecimento = zonaAbastecimento;
    }

    public BairroArea getBairroArea() {
        return this.bairroArea;
    }

    public void setBairroArea(BairroArea bairroArea) {
        this.bairroArea = bairroArea;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroAbastecimentoProgramacao filtro = new FiltroAbastecimentoProgramacao();
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroAbastecimentoProgramacao.ID, this.getId()));
		
		filtro.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
		filtro.adicionarCaminhoParaCarregamentoEntidade("setorAbastecimento");
		filtro.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtro.adicionarCaminhoParaCarregamentoEntidade("zonaAbastecimento");
		filtro.adicionarCaminhoParaCarregamentoEntidade("municipio");
		filtro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtro.adicionarCaminhoParaCarregamentoEntidade("bairroArea");
		
		
		return filtro; 
	}    

}
