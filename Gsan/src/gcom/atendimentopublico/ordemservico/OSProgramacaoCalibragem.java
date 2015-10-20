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
package gcom.atendimentopublico.ordemservico;


import gcom.cadastro.endereco.FiltroOSProgramaCalibragem;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * [UC1185] Informar Calibragem
 * 
 * @author Th�lio Ara�jo
 *
 * @date 20/06/2011
 */
@ControleAlteracao()
public class OSProgramacaoCalibragem extends ObjetoTransacao {
	public static final int ATUALIZAR_CALIBRAGEM = 1813;
	private static final long serialVersionUID = 1L;
	
	public static final int ATUALIZAR_IMPORTANCIA_LOGRADOURO = 1816;
	/** identifier field */
	
	
	private Integer id;
	private OSPriorizacaoTipo priorizacaoTipo;
	private Integer grauImportancia;
	private Integer faixaInicial;
	private Integer faixaFinal;
	
	@ControleAlteracao(funcionalidade={ATUALIZAR_CALIBRAGEM})
	private Integer peso;
	@ControleAlteracao(funcionalidade={ATUALIZAR_CALIBRAGEM})
	private Integer fator;
	private Date ultimaAlteracao;
	
	public OSProgramacaoCalibragem(){
	}

	public Integer getFaixaFinal() {
		return faixaFinal;
	}

	public void setFaixaFinal(Integer faixaFinal) {
		this.faixaFinal = faixaFinal;
	}

	public Integer getFaixaInicial() {
		return faixaInicial;
	}

	public void setFaixaInicial(Integer faixaInicial) {
		this.faixaInicial = faixaInicial;
	}

	public Integer getFator() {
		return fator;
	}

	public void setFator(Integer fator) {
		this.fator = fator;
	}

	public Integer getGrauImportancia() {
		return grauImportancia;
	}

	public void setGrauImportancia(Integer grauImportancia) {
		this.grauImportancia = grauImportancia;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

	@Override
	public Filtro retornaFiltro() {
		FiltroOSProgramaCalibragem filtroOSProgramacaoCalibragem = new FiltroOSProgramaCalibragem();
		
		filtroOSProgramacaoCalibragem.adicionarCaminhoParaCarregamentoEntidade("priorizacaoTipo");
		filtroOSProgramacaoCalibragem.adicionarParametro(
				new ParametroSimples(FiltroOSProgramaCalibragem.ID, this.getId()));
		return filtroOSProgramacaoCalibragem; 
	}
	
	public OSPriorizacaoTipo getPriorizacaoTipo() {
		return priorizacaoTipo;
	}

	public void setPriorizacaoTipo(OSPriorizacaoTipo priorizacaoTipo) {
		this.priorizacaoTipo = priorizacaoTipo;
	}

	
	public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
		
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroOSProgramaCalibragem.ID,this.getId()));
		
		return filtro;
	}
		
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
	}
}