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
package gcom.cadastro.tarifasocial;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ObjetoGcom;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
public class TarifaSocialDado extends ObjetoGcom implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * M�todo que retorna o filtro com as chaves primarias preenchidas
	 *
	 * @return - Filtro
	public Filtro retornaFiltro(){
		FiltroTarifaSocialDado filtro = new FiltroTarifaSocialDado();
		filtro.adicionarParametro(new ParametroSimples(FiltroTarifaSocialDado.ID,this.getId()));
		return filtro;
	}
	/**
	 * M�todo que retorna os campos das chaves primarias
	 *
	 * @return
	
	public String[] retornaCamposChavePrimaria(){
		String[] array = {"id"};
		return array;
	}
	 */

    /**
     * identifier field
     */
    private Integer id;

    /**
     * nullable persistent field
     */
    private Date dataImplantacao;

    /**
     * nullable persistent field
     */
    private Date dataExclusao;

    /**
     * nullable persistent field
     */
    private Short quantidadeRecadastramento;

    /**
     * nullable persistent field
     */
    private Date dataRecadastramento;

    /**
     * nullable persistent field
     */
    private Date ultimaAlteracao;

    /**
     * nullable persistent field
     */
    private Imovel imovel;

    /**
     * persistent field
     */
    private gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo;

    private Set tarifaSocialDadoEconomias;

    /**
     * full constructor
     * 
     * @param dataImplantacao
     *            Descri��o do par�metro
     * @param dataExclusao
     *            Descri��o do par�metro
     * @param quantidadeRecadastramento
     *            Descri��o do par�metro
     * @param dataRecadastramento
     *            Descri��o do par�metro
     * @param ultimaAlteracao
     *            Descri��o do par�metro
     * @param imovel
     *            Descri��o do par�metro
     * @param tarifaSocialExclusaoMotivo
     *            Descri��o do par�metro
     */
    public TarifaSocialDado(
            Date dataImplantacao,
            Date dataExclusao,
            Short quantidadeRecadastramento,
            Date dataRecadastramento,
            Date ultimaAlteracao,
            Imovel imovel,
            gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo) {
        this.dataImplantacao = dataImplantacao;
        this.dataExclusao = dataExclusao;
        this.quantidadeRecadastramento = quantidadeRecadastramento;
        this.dataRecadastramento = dataRecadastramento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.imovel = imovel;
        this.tarifaSocialExclusaoMotivo = tarifaSocialExclusaoMotivo;
    }

    /**
     * default constructor
     */
    public TarifaSocialDado() {
    }

    /**
     * minimal constructor
     * 
     * @param tarifaSocialExclusaoMotivo
     *            Descri��o do par�metro
     */
    public TarifaSocialDado(
            gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo) {
        this.tarifaSocialExclusaoMotivo = tarifaSocialExclusaoMotivo;
    }

    /**
     * Retorna o valor de id
     * 
     * @return O valor de id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Seta o valor de id
     * 
     * @param id
     *            O novo valor de id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o valor de dataImplantacao
     * 
     * @return O valor de dataImplantacao
     */
    public Date getDataImplantacao() {
        return this.dataImplantacao;
    }

    /**
     * Seta o valor de dataImplantacao
     * 
     * @param dataImplantacao
     *            O novo valor de dataImplantacao
     */
    public void setDataImplantacao(Date dataImplantacao) {
        this.dataImplantacao = dataImplantacao;
    }

    /**
     * Retorna o valor de dataExclusao
     * 
     * @return O valor de dataExclusao
     */
    public Date getDataExclusao() {
        return this.dataExclusao;
    }

    /**
     * Seta o valor de dataExclusao
     * 
     * @param dataExclusao
     *            O novo valor de dataExclusao
     */
    public void setDataExclusao(Date dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    /**
     * Retorna o valor de quantidadeRecadastramento
     * 
     * @return O valor de quantidadeRecadastramento
     */
    public Short getQuantidadeRecadastramento() {
        return this.quantidadeRecadastramento;
    }

    /**
     * Seta o valor de quantidadeRecadastramento
     * 
     * @param quantidadeRecadastramento
     *            O novo valor de quantidadeRecadastramento
     */
    public void setQuantidadeRecadastramento(Short quantidadeRecadastramento) {
        this.quantidadeRecadastramento = quantidadeRecadastramento;
    }

    /**
     * Retorna o valor de dataRecadastramento
     * 
     * @return O valor de dataRecadastramento
     */
    public Date getDataRecadastramento() {
        return this.dataRecadastramento;
    }

    /**
     * Seta o valor de dataRecadastramento
     * 
     * @param dataRecadastramento
     *            O novo valor de dataRecadastramento
     */
    public void setDataRecadastramento(Date dataRecadastramento) {
        this.dataRecadastramento = dataRecadastramento;
    }

    /**
     * Retorna o valor de ultimaAlteracao
     * 
     * @return O valor de ultimaAlteracao
     */
    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    /**
     * Seta o valor de ultimaAlteracao
     * 
     * @param ultimaAlteracao
     *            O novo valor de ultimaAlteracao
     */
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * Retorna o valor de imovel
     * 
     * @return O valor de imovel
     */
    public Imovel getImovel() {
        return this.imovel;
    }

    /**
     * Seta o valor de imovel
     * 
     * @param imovel
     *            O novo valor de imovel
     */
    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    /**
     * Retorna o valor de tarifaSocialExclusaoMotivo
     * 
     * @return O valor de tarifaSocialExclusaoMotivo
     */
    public gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo getTarifaSocialExclusaoMotivo() {
        return this.tarifaSocialExclusaoMotivo;
    }

    /**
     * Seta o valor de tarifaSocialExclusaoMotivo
     * 
     * @param tarifaSocialExclusaoMotivo
     *            O novo valor de tarifaSocialExclusaoMotivo
     */
    public void setTarifaSocialExclusaoMotivo(
            gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo) {
        this.tarifaSocialExclusaoMotivo = tarifaSocialExclusaoMotivo;
    }

    /**
     * < <Descri��o do m�todo>>
     * 
     * @return Descri��o do retorno
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    /**
     * Retorna o valor de tarifaSocialDadoEconomias
     * 
     * @return O valor de tarifaSocialDadoEconomias
     */
    public Set getTarifaSocialDadoEconomias() {
        return tarifaSocialDadoEconomias;
    }

    /**
     * Seta o valor de tarifaSocialDadoEconomias
     * 
     * @param tarifaSocialDadoEconomias
     *            O novo valor de tarifaSocialDadoEconomias
     */
    public void setTarifaSocialDadoEconomias(Set tarifaSocialDadoEconomias) {
        this.tarifaSocialDadoEconomias = tarifaSocialDadoEconomias;
    }
    public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
}
