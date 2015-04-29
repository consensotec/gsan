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
package gsan.cobranca;

import gsan.interceptor.ObjetoTransacao;
import gsan.micromedicao.Rota;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;


/** @author Hibernate CodeGenerator */
public class RotaAcaoCriterio extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private RotaAcaoCriterioPK comp_id;

    /** nullable persistent field */
    private Rota rota;

    /** nullable persistent field */
    private CobrancaAcao cobrancaAcao;

    /** nullable persistent field */
    private CobrancaCriterio cobrancaCriterio;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;

    public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
    public RotaAcaoCriterio() {

    }

	public RotaAcaoCriterio(RotaAcaoCriterioPK comp_id, Rota rota, CobrancaAcao cobrancaAcao, CobrancaCriterio cobrancaCriterio) {
		super();
		// TODO Auto-generated constructor stub
		this.comp_id = comp_id;
		this.rota = rota;
		this.cobrancaAcao = cobrancaAcao;
		this.cobrancaCriterio = cobrancaCriterio;
	}

	/**
	 * @return Retorna o campo cobrancaAcao.
	 */
	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}


	/**
	 * @param cobrancaAcao O cobrancaAcao a ser setado.
	 */
	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}


	/**
	 * @return Retorna o campo cobrancaCriterio.
	 */
	public CobrancaCriterio getCobrancaCriterio() {
		return cobrancaCriterio;
	}


	/**
	 * @param cobrancaCriterio O cobrancaCriterio a ser setado.
	 */
	public void setCobrancaCriterio(CobrancaCriterio cobrancaCriterio) {
		this.cobrancaCriterio = cobrancaCriterio;
	}


	/**
	 * @return Retorna o campo comp_id.
	 */
	public RotaAcaoCriterioPK getComp_id() {
		return comp_id;
	}


	/**
	 * @param comp_id O comp_id a ser setado.
	 */
	public void setComp_id(RotaAcaoCriterioPK comp_id) {
		this.comp_id = comp_id;
	}


	/**
	 * @return Retorna o campo rota.
	 */
	public Rota getRota() {
		return rota;
	}


	/**
	 * @param rota O rota a ser setado.
	 */
	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];

		retorno[0] = "comp_id";
		
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroRotaAcaoCriterio filtroRotaAcaoCriterio = new FiltroRotaAcaoCriterio();
		
		filtroRotaAcaoCriterio. adicionarCaminhoParaCarregamentoEntidade("rota");
		filtroRotaAcaoCriterio. adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao");
		filtroRotaAcaoCriterio. adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
		filtroRotaAcaoCriterio. adicionarCaminhoParaCarregamentoEntidade("comp_id");

		filtroRotaAcaoCriterio. adicionarParametro(
				new ParametroSimples(FiltroRotaAcaoCriterio.ROTA_ID, rota.getId()));
		filtroRotaAcaoCriterio. adicionarParametro(
				new ParametroSimples(FiltroRotaAcaoCriterio.COBRANCA_ACAO_ID, cobrancaAcao.getId()));
		return filtroRotaAcaoCriterio; 
	}

}