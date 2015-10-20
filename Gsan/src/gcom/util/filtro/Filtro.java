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
package gcom.util.filtro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/**
 * Representa o filtro b�sico do sistema
 * 
 * @author rodrigo
 */
public abstract class Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Cole��o dos par�metros que fazem parte do filtro
	 */
	protected Collection parametros = new ArrayList();

	/**
	 * Description of the Field
	 */
	protected Collection colecaoCaminhosParaCarregamentoEntidades = new TreeSet();

	/**
	 * Description of the Field
	 */
	protected boolean consultaSemLimites = false;
	
	protected boolean initializeLazy = false;
	
	protected boolean ordenarDecrescente = false;

	/**
	 * Ordena��o do filtro
	 */
	protected List<String> camposOrderBy = new ArrayList();

	protected String campoOrderBy;

	protected String campoDistinct;

	/**
	 * Retorna o valor de parametros
	 * 
	 * @return O valor de parametros
	 */
	public Collection getParametros() {
		return parametros;
	}

	/**
	 * M�todo para adicionar par�metros no filtro
	 * 
	 * @param filtroParametro
	 *            Descri��o do par�metro
	 */
	public void adicionarParametro(FiltroParametro filtroParametro) {
		this.parametros.add(filtroParametro);
	}

	/**
	 * M�todo para adicionar um campo de cole��o � lista das cole��es que ser�o
	 * carregadas pelo sistema
	 * 
	 * @param campoColecao
	 *            nome do campo
	 */
	public void adicionarCaminhoParaCarregamentoEntidade(String campoColecao) {
		this.colecaoCaminhosParaCarregamentoEntidades.add(campoColecao);
	}

	/**
	 * Seta o valor de parametros
	 * 
	 * @param parametros
	 *            O novo valor de parametros
	 */
	public void setParametros(Collection parametros) {
		this.parametros = parametros;
	}

	/**
	 * Retorna o valor de campoOrderBy
	 * 
	 * @return O valor de campoOrderBy
	 */
	public List<String> getCamposOrderBy() {
		ArrayList<String> list = new ArrayList();
		list.addAll(camposOrderBy);
		if (campoOrderBy != null && !campoOrderBy.trim().equals("")) {
			list.add(campoOrderBy);
		}
		return list;
	}

	/**
	 * Seta o valor de campoOrderBy
	 * 
	 * @param campoOrderBy
	 *            O novo valor de campoOrderBy
	 */
	public void setCampoOrderBy(String... campoOrderBy) {
		camposOrderBy.addAll(Arrays.asList(campoOrderBy));
	}

	public void setCampoOrderBy(String campoOrderBy) {
		camposOrderBy.add(campoOrderBy);
	}

	public void limparCamposOrderBy() {
		campoOrderBy = null;
		camposOrderBy.clear();

	}
	
	public void limparColecaoCaminhosParaCarregamentoEntidades() {
		colecaoCaminhosParaCarregamentoEntidades.clear();

	}

	/**
	 * @return Returns the colecaoCaminhosParaCarregamentoEntidades.
	 */
	public Collection getColecaoCaminhosParaCarregamentoEntidades() {
		Collection colecao = new TreeSet();
		colecao.addAll(colecaoCaminhosParaCarregamentoEntidades);
		return colecao;
	}
	
	public void setColecaoCaminhosParaCarregamentoEntidades(Collection colecaoCaminhosParaCarregamentoEntidades) {
		this.colecaoCaminhosParaCarregamentoEntidades.addAll(colecaoCaminhosParaCarregamentoEntidades);
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void limparListaParametros() {
		parametros.clear();
	}

	/**
	 * Retorna o valor de consultaSemLimites
	 * 
	 * @return O valor de consultaSemLimites
	 */
	public boolean isConsultaSemLimites() {
		return consultaSemLimites;
	}

	/**
	 * Seta o valor de consultaSemLimites
	 * 
	 * @param consultaSemLimites
	 *            O novo valor de consultaSemLimites
	 */
	public void setConsultaSemLimites(boolean consultaSemLimites) {
		this.consultaSemLimites = consultaSemLimites;
	}

	public String getCampoDistinct() {
		return campoDistinct;
	}

	public void setCampoDistinct(String campoDistinct) {
		this.campoDistinct = campoDistinct;
	}

	public boolean isInitializeLazy() {
		return initializeLazy;
	}

	public void setInitializeLazy(boolean initializeLazy) {
		this.initializeLazy = initializeLazy;
	}

	public boolean isOrdenarDecrescente() {
		return ordenarDecrescente;
	}

	public void setOrdenarDecrescente(boolean ordenarDecrescente) {
		this.ordenarDecrescente = ordenarDecrescente;
	}
}
