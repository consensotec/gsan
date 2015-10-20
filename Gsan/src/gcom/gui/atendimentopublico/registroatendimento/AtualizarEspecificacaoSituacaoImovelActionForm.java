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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;

public class AtualizarEspecificacaoSituacaoImovelActionForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;

	// Dados da Especificao Situacao Imovel
	private String idEspecificacao;
	private String descricaoEspecificacao;
    
    // Especificacao Imovel Situacao Criterior
    private Collection<EspecificacaoImovSitCriterio> especificacaoImovelSituacaoCriterio 
    	= new ArrayList<EspecificacaoImovSitCriterio>();
    
    private Collection<EspecificacaoImovSitCriterio> especificacaoImovelSituacaoCriterioRemovidas = 
    	new ArrayList<EspecificacaoImovSitCriterio>();
    
    // Usado na Tela de Atualizar
    private String idAtualizar;
    private String idEspecificacaoCriterio;
    private String indicadorHidrometroLigacaoAgua;
    private String indicadorHidrometroPoco;
    private String situacaoLigacaoAgua;
    private String situacaoLigacaoEsgoto;

    
    private String tamanhoColecao = "0";
    private String deleteSituacaoCriterioImovel;

	
	public String getDeleteSituacaoCriterioImovel() {
		return deleteSituacaoCriterioImovel;
	}
	public void setDeleteSituacaoCriterioImovel(String deleteSituacaoCriterioImovel) {
		this.deleteSituacaoCriterioImovel = deleteSituacaoCriterioImovel;
	}
	public String getTamanhoColecao() {
		return tamanhoColecao;
	}
	public void setTamanhoColecao(String tamanhoColecao) {
		this.tamanhoColecao = tamanhoColecao;
	}
	public String getDescricaoEspecificacao() {
		return descricaoEspecificacao;
	}
	public void setDescricaoEspecificacao(String descricaoEspecificacao) {
		this.descricaoEspecificacao = descricaoEspecificacao;
	}
	public Collection<EspecificacaoImovSitCriterio> getEspecificacaoImovelSituacaoCriterio() {
		return especificacaoImovelSituacaoCriterio;
	}
	public void setEspecificacaoImovelSituacaoCriterio(
			Collection<EspecificacaoImovSitCriterio> especificacaoImovelSituacaoCriterio) {
		this.especificacaoImovelSituacaoCriterio = especificacaoImovelSituacaoCriterio;
	}
	public String getIdEspecificacao() {
		return idEspecificacao;
	}
	public void setIdEspecificacao(String idEspecificacao) {
		this.idEspecificacao = idEspecificacao;
	}
	public String getIndicadorHidrometroLigacaoAgua() {
		return indicadorHidrometroLigacaoAgua;
	}
	public void setIndicadorHidrometroLigacaoAgua(
			String indicadorHidrometroLigacaoAgua) {
		this.indicadorHidrometroLigacaoAgua = indicadorHidrometroLigacaoAgua;
	}
	public String getIndicadorHidrometroPoco() {
		return indicadorHidrometroPoco;
	}
	public void setIndicadorHidrometroPoco(String indicadorHidrometroPoco) {
		this.indicadorHidrometroPoco = indicadorHidrometroPoco;
	}
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	public String getIdEspecificacaoCriterio() {
		return idEspecificacaoCriterio;
	}
	public void setIdEspecificacaoCriterio(String idEspecificacaoCriterio) {
		this.idEspecificacaoCriterio = idEspecificacaoCriterio;
	}
	public String getIdAtualizar() {
		return idAtualizar;
	}
	public void setIdAtualizar(String idAtualizar) {
		this.idAtualizar = idAtualizar;
	}
	public Collection<EspecificacaoImovSitCriterio> getEspecificacaoImovelSituacaoCriterioRemovidas() {
		return especificacaoImovelSituacaoCriterioRemovidas;
	}
	public void setEspecificacaoImovelSituacaoCriterioRemovidas(
			Collection<EspecificacaoImovSitCriterio> especificacaoImovelSituacaoCriterioRemovidas) {
		this.especificacaoImovelSituacaoCriterioRemovidas = especificacaoImovelSituacaoCriterioRemovidas;
	}
}