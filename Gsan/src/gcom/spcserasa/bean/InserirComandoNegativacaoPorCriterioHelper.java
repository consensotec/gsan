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
package gcom.spcserasa.bean;

import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.NegativacaoCriterioCpfTipo;

import java.util.Collection;


/**
 * Classe respons�vel por ajudar o caso de uso 
 * [UC065] Inserir Comando de Negativa��o - Por Crit�rio
 *
 * @author Ana Maria
 * @date 13/12/2007
 */
public class InserirComandoNegativacaoPorCriterioHelper {

	private NegativacaoComando negativacaoComando;
	private NegativacaoCriterio negativacaoCriterio;
	private Collection<NegativacaoCriterioCpfTipo> colecaoNegativacaoCriterioCpfTipo;

	private String[] idsCobrancaSituacaoTipo;
	private String[] idsCobrancaSituacao;
	private String[] idsLigacaoAguaSituacao;
	private String[] idsLigacaoEsgotoSituacao;
	private String[] idsSubcategoria;
	private String[] idsPerfilImovel;
	private String[] idsTipoCliente;
	private String[] idsCobrancaGrupo;
	private String[] idsGerenciaRegional;
	private String[] idsUnidadeNegocio;
	private String[] idsEloPolo;
	private String indicadorBaixaRenda;
	private String[] idsMotivoRetorno;
	

	public String getIndicadorBaixaRenda() {
		return indicadorBaixaRenda;
	}


	public void setIndicadorBaixaRenda(String indicadorBaixaRenda) {
		this.indicadorBaixaRenda = indicadorBaixaRenda;
	}


	/**
	 * @return Retorna o campo colecaoNegativacaoCriterioCpfTipo.
	 */
	public Collection<NegativacaoCriterioCpfTipo> getColecaoNegativacaoCriterioCpfTipo() {
		return colecaoNegativacaoCriterioCpfTipo;
	}


	/**
	 * @param colecaoNegativacaoCriterioCpfTipo O colecaoNegativacaoCriterioCpfTipo a ser setado.
	 */
	public void setColecaoNegativacaoCriterioCpfTipo(
			Collection<NegativacaoCriterioCpfTipo> colecaoNegativacaoCriterioCpfTipo) {
		this.colecaoNegativacaoCriterioCpfTipo = colecaoNegativacaoCriterioCpfTipo;
	}


	/**
	 * @return Retorna o campo idsCobrancaGrupo.
	 */
	public String[] getIdsCobrancaGrupo() {
		return idsCobrancaGrupo;
	}


	/**
	 * @param idsCobrancaGrupo O idsCobrancaGrupo a ser setado.
	 */
	public void setIdsCobrancaGrupo(String[] idsCobrancaGrupo) {
		this.idsCobrancaGrupo = idsCobrancaGrupo;
	}


	/**
	 * @return Retorna o campo idsEloPolo.
	 */
	public String[] getIdsEloPolo() {
		return idsEloPolo;
	}


	/**
	 * @param idsEloPolo O idsEloPolo a ser setado.
	 */
	public void setIdsEloPolo(String[] idsEloPolo) {
		this.idsEloPolo = idsEloPolo;
	}


	/**
	 * @return Retorna o campo idsGerenciaRegional.
	 */
	public String[] getIdsGerenciaRegional() {
		return idsGerenciaRegional;
	}


	/**
	 * @param idsGerenciaRegional O idsGerenciaRegional a ser setado.
	 */
	public void setIdsGerenciaRegional(String[] idsGerenciaRegional) {
		this.idsGerenciaRegional = idsGerenciaRegional;
	}


	/**
	 * @return Retorna o campo idsPerfilImovel.
	 */
	public String[] getIdsPerfilImovel() {
		return idsPerfilImovel;
	}


	/**
	 * @param idsPerfilImovel O idsPerfilImovel a ser setado.
	 */
	public void setIdsPerfilImovel(String[] idsPerfilImovel) {
		this.idsPerfilImovel = idsPerfilImovel;
	}


	/**
	 * @return Retorna o campo idsSubcategoria.
	 */
	public String[] getIdsSubcategoria() {
		return idsSubcategoria;
	}


	/**
	 * @param idsSubcategoria O idsSubcategoria a ser setado.
	 */
	public void setIdsSubcategoria(String[] idsSubcategoria) {
		this.idsSubcategoria = idsSubcategoria;
	}


	/**
	 * @return Retorna o campo idsTipoCliente.
	 */
	public String[] getIdsTipoCliente() {
		return idsTipoCliente;
	}


	/**
	 * @param idsTipoCliente O idsTipoCliente a ser setado.
	 */
	public void setIdsTipoCliente(String[] idsTipoCliente) {
		this.idsTipoCliente = idsTipoCliente;
	}


	/**
	 * @return Retorna o campo idsUnidadeNegocio.
	 */
	public String[] getIdsUnidadeNegocio() {
		return idsUnidadeNegocio;
	}


	/**
	 * @param idsUnidadeNegocio O idsUnidadeNegocio a ser setado.
	 */
	public void setIdsUnidadeNegocio(String[] idsUnidadeNegocio) {
		this.idsUnidadeNegocio = idsUnidadeNegocio;
	}


	/**
	 * @return Retorna o campo negativacaoComando.
	 */
	public NegativacaoComando getNegativacaoComando() {
		return negativacaoComando;
	}


	/**
	 * @param negativacaoComando O negativacaoComando a ser setado.
	 */
	public void setNegativacaoComando(NegativacaoComando negativacaoComando) {
		this.negativacaoComando = negativacaoComando;
	}


	/**
	 * @return Retorna o campo negativacaoCriterio.
	 */
	public NegativacaoCriterio getNegativacaoCriterio() {
		return negativacaoCriterio;
	}


	/**
	 * @param negativacaoCriterio O negativacaoCriterio a ser setado.
	 */
	public void setNegativacaoCriterio(NegativacaoCriterio negativacaoCriterio) {
		this.negativacaoCriterio = negativacaoCriterio;
	}


	/**
	 * @return Retorna o campo idsLigacaoAguaSituacao.
	 */
	public String[] getIdsLigacaoAguaSituacao() {
		return idsLigacaoAguaSituacao;
	}


	/**
	 * @param idsLigacaoAguaSituacao O idsLigacaoAguaSituacao a ser setado.
	 */
	public void setIdsLigacaoAguaSituacao(String[] idsLigacaoAguaSituacao) {
		this.idsLigacaoAguaSituacao = idsLigacaoAguaSituacao;
	}


	/**
	 * @return Retorna o campo idsLigacaoEsgotoSituacao.
	 */
	public String[] getIdsLigacaoEsgotoSituacao() {
		return idsLigacaoEsgotoSituacao;
	}


	/**
	 * @param idsLigacaoEsgotoSituacao O idsLigacaoEsgotoSituacao a ser setado.
	 */
	public void setIdsLigacaoEsgotoSituacao(String[] idsLigacaoEsgotoSituacao) {
		this.idsLigacaoEsgotoSituacao = idsLigacaoEsgotoSituacao;
	}


	public String[] getIdsMotivoRetorno() {
		return idsMotivoRetorno;
	}


	public void setIdsMotivoRetorno(String[] idsMotivoRetorno) {
		this.idsMotivoRetorno = idsMotivoRetorno;
	}


	public String[] getIdsCobrancaSituacao() {
		return idsCobrancaSituacao;
	}


	public void setIdsCobrancaSituacao(String[] idsCobrancaSituacao) {
		this.idsCobrancaSituacao = idsCobrancaSituacao;
	}


	public String[] getIdsCobrancaSituacaoTipo() {
		return idsCobrancaSituacaoTipo;
	}


	public void setIdsCobrancaSituacaoTipo(String[] idsCobrancaSituacaoTipo) {
		this.idsCobrancaSituacaoTipo = idsCobrancaSituacaoTipo;
	}



}