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
package gsan.util.tabelaauxiliar;

import gsan.util.AtualizacaoInvalidaException;
import gsan.util.ControladorException;
import gsan.util.ErroRepositorioException;
import gsan.util.IRepositorioUtil;
import gsan.util.RepositorioUtilHBM;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;
import gsan.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;
import gsan.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;
import gsan.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixa;
import gsan.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixa;
import gsan.util.tabelaauxiliar.tipo.FiltroTabelaAuxiliarTipo;
import gsan.util.tabelaauxiliar.tipo.TabelaAuxiliarTipo;

import java.util.Collection;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ControladorTabelaAuxiliarSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;
	SessionContext sessionContext;

	private IRepositorioUtil repositorioUtil = null;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @exception CreateException
	 *                Descri��o da exce��o
	 */
	public void ejbCreate() throws CreateException {
		repositorioUtil = RepositorioUtilHBM.getInstancia();
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param tabelaAuxiliarAbstrata
	 *            Descri��o do par�metro
	 * @throws ControladorException
	 */
	public void atualizarTabelaAuxiliar(
			TabelaAuxiliarAbstrata tabelaAuxiliarAbstrata)
			throws ControladorException {

		try {

			// -----VALIDA��O DOS TIMESTAMP PARA ATUALIZA��O DE CADASTRO

			// Valida��o para Tabela Auxiliar
			if (tabelaAuxiliarAbstrata instanceof TabelaAuxiliar) {
				// Cria o objeto
				TabelaAuxiliar tabelaAuxiliar = null;

				// Faz o casting
				tabelaAuxiliar = (TabelaAuxiliar) tabelaAuxiliarAbstrata;

				// Cria o filtro
				FiltroTabelaAuxiliar filtroTabelaAuxiliar = new FiltroTabelaAuxiliar();
				// Pega o nome do pacote do objeto
				String nomePacoteObjeto = tabelaAuxiliar.getClass().getName();

				// Seta os parametros do filtro
				filtroTabelaAuxiliar.adicionarParametro(new ParametroSimples(
						FiltroTabelaAuxiliar.ID, tabelaAuxiliar.getId()));

				// Pesquisa a cole��o de acordo com o filtro passado
				Collection tabelasAuxiliares = repositorioUtil.pesquisar(
						filtroTabelaAuxiliar, nomePacoteObjeto);
				TabelaAuxiliar tabelaAuxiliarNaBase = (TabelaAuxiliar) tabelasAuxiliares
						.iterator().next();

				// Verifica se a data de altera��o do objeto gravado na base �
				// maior que a na instancia
				if ((tabelaAuxiliarNaBase.getUltimaAlteracao()
						.after(tabelaAuxiliar.getUltimaAlteracao()))) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.atualizacao.timestamp");
				}
				// Faz uma referencia ao objeto
				tabelaAuxiliarAbstrata = tabelaAuxiliar;
			}

			// Valida��o para Tabela Auxiliar Abreviada
			if (tabelaAuxiliarAbstrata instanceof TabelaAuxiliarAbreviada) {
				// Cria o objeto
				TabelaAuxiliarAbreviada tabelaAuxiliarAbreviada = null;

				// Faz o casting
				tabelaAuxiliarAbreviada = (TabelaAuxiliarAbreviada) tabelaAuxiliarAbstrata;

				// Cria o filtro
				FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();
				// Pega o nome do pacote do objeto
				String nomePacoteObjeto = tabelaAuxiliarAbreviada.getClass()
						.getName();

				// Seta os parametros do filtro
				filtroTabelaAuxiliarAbreviada
						.adicionarParametro(new ParametroSimples(
								FiltroTabelaAuxiliarAbreviada.ID,
								tabelaAuxiliarAbreviada.getId()));

				// Pesquisa a cole��o de acordo com o filtro passado
				Collection tabelasAuxiliaresAbreviadas = repositorioUtil
						.pesquisar(filtroTabelaAuxiliarAbreviada,
								nomePacoteObjeto);
				TabelaAuxiliar tabelaAuxiliarAbreviadaNaBase = (TabelaAuxiliar) tabelasAuxiliaresAbreviadas
						.iterator().next();

				// Verifica se a data de altera��o do objeto gravado na base �
				// maior que a na instancia
				if ((tabelaAuxiliarAbreviadaNaBase.getUltimaAlteracao()
						.after(tabelaAuxiliarAbreviada.getUltimaAlteracao()))) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.atualizacao.timestamp");
				}
				// Faz uma referencia ao objeto
				tabelaAuxiliarAbstrata = tabelaAuxiliarAbreviada;
			}

			// Valida��o para Tabela Auxiliar Faixa
			if (tabelaAuxiliarAbstrata instanceof TabelaAuxiliarFaixa) {
				// Cria o objeto
				TabelaAuxiliarFaixa tabelaAuxiliarFaixa = null;

				// Faz o casting
				tabelaAuxiliarFaixa = (TabelaAuxiliarFaixa) tabelaAuxiliarAbstrata;

				// Cria o filtro
				FiltroTabelaAuxiliarFaixa filtroTabelaAuxiliarFaixa = new FiltroTabelaAuxiliarFaixa();
				// Pega o nome do pacote do objeto
				String nomePacoteObjeto = tabelaAuxiliarFaixa.getClass()
						.getName();

				// Seta os parametros do filtro
				filtroTabelaAuxiliarFaixa
						.adicionarParametro(new ParametroSimples(
								FiltroTabelaAuxiliarFaixa.ID,
								tabelaAuxiliarFaixa.getId()));

				// Pesquisa a cole��o de acordo com o filtro passado
				Collection tabelasAuxiliaresFaixas = repositorioUtil.pesquisar(
						filtroTabelaAuxiliarFaixa, nomePacoteObjeto);
				TabelaAuxiliarFaixa tabelaAuxiliarFaixaNaBase = (TabelaAuxiliarFaixa) tabelasAuxiliaresFaixas
						.iterator().next();

				// Verifica se a data de altera��o do objeto gravado na base �
				// maior que a na instancia
				if ((tabelaAuxiliarFaixaNaBase.getUltimaAlteracao()
						.after(tabelaAuxiliarFaixa.getUltimaAlteracao()))) {
					sessionContext.setRollbackOnly();
					throw new AtualizacaoInvalidaException();
				}
				// Faz uma referencia ao objeto
				tabelaAuxiliarAbstrata = tabelaAuxiliarFaixa;
			}

			// Valida��o para Tabela Auxiliar
			if (tabelaAuxiliarAbstrata instanceof TabelaAuxiliarTipo) {
				// Cria o objeto
				TabelaAuxiliarTipo tabelaAuxiliarTipo = null;

				// Faz o casting
				tabelaAuxiliarTipo = (TabelaAuxiliarTipo) tabelaAuxiliarAbstrata;

				// Cria o filtro
				FiltroTabelaAuxiliarTipo filtroTabelaAuxiliarTipo = new FiltroTabelaAuxiliarTipo();
				// Pega o nome do pacote do objeto
				String nomePacoteObjeto = tabelaAuxiliarTipo.getClass()
						.getName();

				// Seta os parametros do filtro
				filtroTabelaAuxiliarTipo
						.adicionarParametro(new ParametroSimples(
								FiltroTabelaAuxiliarTipo.ID, tabelaAuxiliarTipo
										.getId()));

				// Pesquisa a cole��o de acordo com o filtro passado
				Collection tabelasAuxiliaresTipos = repositorioUtil.pesquisar(
						filtroTabelaAuxiliarTipo, nomePacoteObjeto);
				TabelaAuxiliarTipo tabelaAuxiliarTipoNaBase = (TabelaAuxiliarTipo) tabelasAuxiliaresTipos
						.iterator().next();

				// Verifica se a data de altera��o do objeto gravado na base �
				// maior que a na instancia
				if ((tabelaAuxiliarTipoNaBase.getUltimaAlteracao()
						.after(tabelaAuxiliarTipo.getUltimaAlteracao()))) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.atualizacao.timestamp");
				}
				// Faz uma referencia ao objeto
				tabelaAuxiliarAbstrata = tabelaAuxiliarTipo;
			}

			// Seta a data/hora
			tabelaAuxiliarAbstrata.setUltimaAlteracao(new Date());
			// Atualiza objeto

			repositorioUtil.atualizar(tabelaAuxiliarAbstrata);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param objetoTeste
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @throws ControladorException
	 */
	public Object inserirTeste(Object objetoTeste) throws ControladorException {
		try {
			return repositorioUtil.inserir(objetoTeste);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param filtroTeste
	 *            Descri��o do par�metro
	 * @param nomePacoteObjeto
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @throws ControladorException
	 */
	public Collection pesquisarTeste(Filtro filtroTeste, String nomePacoteObjeto)
			throws ControladorException {
		try {
			return repositorioUtil.pesquisar(filtroTeste, nomePacoteObjeto);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

}
