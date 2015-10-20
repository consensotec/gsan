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
* Anderson Italo felinto de Lima
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

package gcom.cobranca;

import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.Rota;
import gcom.util.filtro.Filtro;

import java.util.Date;

@ControleAlteracao()
public class RotaAcaoCriterioHelper extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public static final int TABELA_ID = 56;

	@ControleAlteracao(idTabelaColuna=251)
	private CobrancaAcao cobrancaAcao;
	
	@ControleAlteracao(idTabelaColuna=252)
	private CobrancaCriterio cobrancaCriterio;
	
	@ControleAlteracao(idTabelaColuna=23750)
	private CobrancaGrupo cobrancaGrupo;
	
	@ControleAlteracao(idTabelaColuna=23751)
	private GerenciaRegional gerenciaRegional;
	
	@ControleAlteracao(idTabelaColuna=23752)
	private UnidadeNegocio unidadeNegocio;
	
	@ControleAlteracao(idTabelaColuna=23753)
	private Localidade localidadeInicial;
	
	@ControleAlteracao(idTabelaColuna=23754)
	private Localidade localidadeFinal;
	
	@ControleAlteracao(idTabelaColuna=23755)
	private SetorComercial setorComercialInicial;
	
	@ControleAlteracao(idTabelaColuna=23756)
	private SetorComercial setorComercialFinal;
	
	@ControleAlteracao(idTabelaColuna=23757)
	private Rota rotaInicial;
	
	@ControleAlteracao(idTabelaColuna=23758)
	private Rota rotaFinal;
	
	public RotaAcaoCriterioHelper(){}
	
	public RotaAcaoCriterioHelper(CobrancaAcao cobrancaAcao, CobrancaCriterio cobrancaCriterio, 
								  CobrancaGrupo cobrancaGrupo, GerenciaRegional gerenciaRegional,UnidadeNegocio unidadeNegocio, 
								  Localidade localidadeInicial, Localidade localidadeFinal,
								  SetorComercial setorComercialInicial, 
								  SetorComercial setorComercialFinal, 
			                     Rota rotaInicial, Rota rotaFinal ) {
		super();
		this.cobrancaAcao = cobrancaAcao;
		this.cobrancaCriterio = cobrancaCriterio;
		this.cobrancaGrupo = cobrancaGrupo;
		this.gerenciaRegional = gerenciaRegional;
		this.unidadeNegocio = unidadeNegocio;
		this.localidadeInicial = localidadeInicial;
		this.localidadeFinal = localidadeFinal;
		this.setorComercialInicial = setorComercialInicial;
		this.setorComercialFinal = setorComercialFinal;
		this.rotaInicial = rotaInicial;
		this.rotaFinal = rotaFinal;
	}
	
	@Override
	public Date getUltimaAlteracao() {
		return null;
	}
	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
	}
	
	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}

	@Override
	public Filtro retornaFiltro() {
		return null; 
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String[] labels = {"cobrancaAcao.descricaoCobrancaAcao","cobrancaCriterio.descricaoCobrancaCriterio"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String[] labels = {"A��o de Cobranca", "Criterio de Cobranca"};
		return labels;		
	}
	
	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}

	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	public CobrancaCriterio getCobrancaCriterio() {
		return cobrancaCriterio;
	}

	public void setCobrancaCriterio(CobrancaCriterio cobrancaCriterio) {
		this.cobrancaCriterio = cobrancaCriterio;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public CobrancaGrupo getCobrancaGrupo() {
		return cobrancaGrupo;
	}

	public void setCobrancaGrupo(CobrancaGrupo cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}

	public Localidade getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Localidade localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Localidade getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Localidade localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Rota getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(Rota rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public Rota getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(Rota rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public SetorComercial getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(SetorComercial setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public SetorComercial getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(SetorComercial setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}


}
