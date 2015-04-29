/**
 * 
 */
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
package gsan.gui.util.tabelaauxiliar.faixa;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.ErroRepositorioException;
import gsan.util.filtro.ParametroSimples;
import gsan.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixaReal;
import gsan.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaReal;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author R�mulo Aur�lio
 *
 */
public class InserirTabelaAuxiliarFaixaRealAction extends GcomAction{
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws RemoteException,
			ErroRepositorioException {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Prepara o retorno da A��o
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obt�m o action form
		TabelaAuxiliarFaixaRealActionForm form = (TabelaAuxiliarFaixaRealActionForm) actionForm;

		// Obt�m a fachada
		Fachada fachada = Fachada.getInstancia();

		// Recebe o objeto TabelaAuxiliarAbreviada
		TabelaAuxiliarFaixaReal tabelaAuxiliarFaixaReal = (TabelaAuxiliarFaixaReal) sessao
				.getAttribute("tabela");
		
		BigDecimal volumeMenorFaixa = null;
		
		if (form.getVolumeMenorFaixa() != null
				&& !form.getVolumeMenorFaixa().trim().equalsIgnoreCase("")) {
			
			volumeMenorFaixa = new BigDecimal(0);

			String valorAux = form.getVolumeMenorFaixa().toString().replace(".",
					"");

			valorAux = valorAux.replace(",", ".");

			volumeMenorFaixa = new BigDecimal(valorAux);

			tabelaAuxiliarFaixaReal.setVolumeMenorFaixa(volumeMenorFaixa);

		}
		
		
		if (form.getVolumeMaiorFaixa() != null
				&& !form.getVolumeMaiorFaixa().trim().equalsIgnoreCase("")) {
			BigDecimal volumeMaiorFaixa = new BigDecimal(0);

			String valorAux = form.getVolumeMaiorFaixa().toString().replace(".",
					"");

			valorAux = valorAux.replace(",", ".");

			volumeMaiorFaixa = new BigDecimal(valorAux);

			tabelaAuxiliarFaixaReal.setVolumeMaiorFaixa(volumeMaiorFaixa);
			
			if (volumeMaiorFaixa != null					
					&& volumeMenorFaixa != null) {
				if (volumeMenorFaixa.compareTo(volumeMaiorFaixa) > 0) {
					throw new ActionServletException("atencao.volume_menor_faixa_superior_maior_faixa");
				}
			}

		}


		// Seta a data e hora
		tabelaAuxiliarFaixaReal.setUltimaAlteracao(new Date());

		tabelaAuxiliarFaixaReal
				.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		FiltroTabelaAuxiliarFaixaReal filtroTabelaAuxiliarFaixaReal = new FiltroTabelaAuxiliarFaixaReal();

		filtroTabelaAuxiliarFaixaReal
				.adicionarParametro(new ParametroSimples(
						FiltroTabelaAuxiliarFaixaReal.VOLUME_MENOR_FAIXA,
						tabelaAuxiliarFaixaReal.getVolumeMenorFaixa()));
		
		filtroTabelaAuxiliarFaixaReal
		.adicionarParametro(new ParametroSimples(
				FiltroTabelaAuxiliarFaixaReal.VOLUME_MAIOR_FAIXA,
				tabelaAuxiliarFaixaReal.getVolumeMaiorFaixa()));

		Collection colecaoTabelaAuxiliarFaixaReal = fachada.pesquisar(
				filtroTabelaAuxiliarFaixaReal, TabelaAuxiliarFaixaReal.class
						.getName());

		if (colecaoTabelaAuxiliarFaixaReal != null && !colecaoTabelaAuxiliarFaixaReal.isEmpty()) {
			throw new ActionServletException(
					"atencao.descricao_tabela_auxiliar_ja_existente",
					(String) sessao.getAttribute("titulo"),"");
		}

		// Insere objeto
		fachada.inserirTabelaAuxiliar(tabelaAuxiliarFaixaReal);

		// Monta a p�gina de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(
					httpServletRequest,
					((String) sessao.getAttribute("titulo"))
							+ " inserido(a) com sucesso.",
					"Inserir outro(a) "
							+ ((String) sessao.getAttribute("titulo")),
					((String) sessao
							.getAttribute("funcionalidadeTabelaAuxiliarFaixaRealInserir")));
		}

		// Remove os objetos da sess�o
		sessao.removeAttribute("funcionalidadeTabelaAuxiliarFaixaRealInserir");
		sessao.removeAttribute("titulo");
		sessao.removeAttribute("tabelaAuxiliarAbreviada");

		return retorno;
	}
}