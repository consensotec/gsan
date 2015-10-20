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
package gcom.faturamento.conta;

import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.faturamento.FaturamentoGrupo;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

/**
 * b�sica 
 *
 * @author Thiago Toscano 
 * @date 02/05/2006
 */
public class ContaMensagem extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	private Integer id;

	private Integer anoMesRreferenciaFaturamento;

	private String descricaoContaMensagem01;

	private String descricaoContaMensagem02;

	private String descricaoContaMensagem03;

	private Date ultimaAlteracao;
        	
	private FaturamentoGrupo faturamentoGrupo;
	
	private GerenciaRegional gerenciaRegional;
	
	private Localidade localidade;
	
	private SetorComercial setorComercial;
	
	private Quadra quadra;

	public ContaMensagem(Integer id, Integer anoMesRreferenciaFaturamento, String descricaoContaMensagem, Date ultimaAlteracao, FaturamentoGrupo faturamentoGrupo, GerenciaRegional gerenciaRegional, Localidade localidade, SetorComercial setorComercial) {
		super();
		this.id = id;
		this.anoMesRreferenciaFaturamento = anoMesRreferenciaFaturamento;
		this.descricaoContaMensagem01 = descricaoContaMensagem;
		this.ultimaAlteracao = ultimaAlteracao;
		this.faturamentoGrupo = faturamentoGrupo;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
	}

	public ContaMensagem(Integer id, Integer anoMesRreferenciaFaturamento, String descricaoContaMensagem, Date ultimaAlteracao, FaturamentoGrupo faturamentoGrupo, GerenciaRegional gerenciaRegional, Localidade localidade, SetorComercial setorComercial, String descricaoContaMensagem02, String descricaoContaMensagem03 ) {
		super();
		this.id = id;
		this.anoMesRreferenciaFaturamento = anoMesRreferenciaFaturamento;
		this.descricaoContaMensagem01 = descricaoContaMensagem;
		this.ultimaAlteracao = ultimaAlteracao;
		this.faturamentoGrupo = faturamentoGrupo;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.descricaoContaMensagem02 = descricaoContaMensagem02;
		this.descricaoContaMensagem03 = descricaoContaMensagem03;
	}

	public ContaMensagem () {
		
	}
	
	public ContaMensagem (Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo anoMesRreferenciaFaturamento.
	 */
	public Integer getAnoMesRreferenciaFaturamento() {
		return anoMesRreferenciaFaturamento;
	}

	/**
	 * @param anoMesRreferenciaFaturamento O anoMesRreferenciaFaturamento a ser setado.
	 */
	public void setAnoMesRreferenciaFaturamento(Integer anoMesRreferenciaFaturamento) {
		this.anoMesRreferenciaFaturamento = anoMesRreferenciaFaturamento;
	}


	/**
	 * @return Retorna o campo descricaoContaMensagem01.
	 */
	public String getDescricaoContaMensagem01() {
		return descricaoContaMensagem01;
	}

	/**
	 * @param descricaoContaMensagem01 O descricaoContaMensagem01 a ser setado.
	 */
	public void setDescricaoContaMensagem01(String descricaoContaMensagem01) {
		this.descricaoContaMensagem01 = descricaoContaMensagem01;
	}

	/**
	 * @return Retorna o campo descricaoContaMensagem02.
	 */
	public String getDescricaoContaMensagem02() {
		return descricaoContaMensagem02;
	}

	/**
	 * @param descricaoContaMensagem02 O descricaoContaMensagem02 a ser setado.
	 */
	public void setDescricaoContaMensagem02(String descricaoContaMensagem02) {
		this.descricaoContaMensagem02 = descricaoContaMensagem02;
	}

	/**
	 * @return Retorna o campo descricaoContaMensagem03.
	 */
	public String getDescricaoContaMensagem03() {
		return descricaoContaMensagem03;
	}

	/**
	 * @param descricaoContaMensagem03 O descricaoContaMensagem03 a ser setado.
	 */
	public void setDescricaoContaMensagem03(String descricaoContaMensagem03) {
		this.descricaoContaMensagem03 = descricaoContaMensagem03;
	}

	/**
	 * @return Retorna o campo faturamentoGrupo.
	 */
	public FaturamentoGrupo getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	/**
	 * @param faturamentoGrupo O faturamentoGrupo a ser setado.
	 */
	public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo setorComercial.
	 */
	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	/**
	 * @param setorComercial O setorComercial a ser setado.
	 */
	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Filtro retornaFiltro() {
		
		FiltroContaMensagem filtroContaMensagem = new FiltroContaMensagem();
		filtroContaMensagem.adicionarParametro(new ParametroSimples(FiltroContaMensagem.ID,this.getId()));
		filtroContaMensagem.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroContaMensagem.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroContaMensagem.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroContaMensagem.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		
		return filtroContaMensagem;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	/**
	 * @return Retorna o campo quadra.
	 */
	public Quadra getQuadra() {
		return quadra;
	}

	/**
	 * @param quadra O quadra a ser setado.
	 */
	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}
}