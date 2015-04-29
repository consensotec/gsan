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
import gsan.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixaInteiro;
import gsan.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaInteiro;

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
 * @author gsan
 *
 */
public class InserirTabelaAuxiliarFaixaInteiroAction extends GcomAction{
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
		TabelaAuxiliarFaixaInteiroActionForm form = (TabelaAuxiliarFaixaInteiroActionForm) actionForm;

		// Obt�m a fachada
		Fachada fachada = Fachada.getInstancia();

		// Recebe o objeto TabelaAuxiliarAbreviada
		TabelaAuxiliarFaixaInteiro tabelaAuxiliarFaixaInteiro = (TabelaAuxiliarFaixaInteiro) sessao
				.getAttribute("tabela");
		
		if (form.getMenorFaixa() != null
				&& !form.getMenorFaixa().trim().equalsIgnoreCase("")) {
			

			tabelaAuxiliarFaixaInteiro.setMenorFaixa(new Integer(form.getMenorFaixa() ));

		}
		
		
		if (form.getMaiorFaixa() != null
				&& !form.getMaiorFaixa().trim().equalsIgnoreCase("")) {
			

			tabelaAuxiliarFaixaInteiro.setMaiorFaixa(new Integer(form.getMaiorFaixa() ));

		}
		
		if (form.getMenorFaixa() != null
				&& !form.getMenorFaixa().trim().equalsIgnoreCase("")
				&& form.getMaiorFaixa()  != null
				&& !form.getMaiorFaixa() .trim().equalsIgnoreCase("")) {
			if (new Integer(form.getMenorFaixa()).intValue() > new Integer(
					form.getMaiorFaixa() )) {
				throw new ActionServletException("atencao.menor_faixa_superior_maior_faixa");
			}
		}


		// Seta a data e hora
		tabelaAuxiliarFaixaInteiro.setUltimaAlteracao(new Date());

		tabelaAuxiliarFaixaInteiro
				.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		FiltroTabelaAuxiliarFaixaInteiro filtroTabelaAuxiliarFaixaInteiro = new FiltroTabelaAuxiliarFaixaInteiro();

		filtroTabelaAuxiliarFaixaInteiro
				.adicionarParametro(new ParametroSimples(
						FiltroTabelaAuxiliarFaixaInteiro.MENOR_FAIXA,
						tabelaAuxiliarFaixaInteiro.getMenorFaixa()));
		
		filtroTabelaAuxiliarFaixaInteiro
		.adicionarParametro(new ParametroSimples(
				FiltroTabelaAuxiliarFaixaInteiro.MAIOR_FAIXA,
				tabelaAuxiliarFaixaInteiro.getMaiorFaixa()));

		Collection colecaoTabelaAuxiliarFaixaInteiro = fachada.pesquisar(
				filtroTabelaAuxiliarFaixaInteiro, TabelaAuxiliarFaixaInteiro.class
						.getName());

		if (colecaoTabelaAuxiliarFaixaInteiro != null && !colecaoTabelaAuxiliarFaixaInteiro.isEmpty()) {
			throw new ActionServletException(
					"atencao.descricao_tabela_auxiliar_ja_existente",
					(String) sessao.getAttribute("titulo"),"");
		}

		// Insere objeto
		fachada.inserirTabelaAuxiliar(tabelaAuxiliarFaixaInteiro);

		// Monta a p�gina de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(
					httpServletRequest,
					((String) sessao.getAttribute("titulo"))
							+ " inserido(a) com sucesso.",
					"Inserir outro(a) "
							+ ((String) sessao.getAttribute("titulo")),
					((String) sessao
							.getAttribute("funcionalidadeTabelaAuxiliarFaixaInteiroInserir")));
		}

		// Remove os objetos da sess�o
		sessao.removeAttribute("funcionalidadeTabelaAuxiliarFaixaInteiroInserir");
		sessao.removeAttribute("titulo");
		sessao.removeAttribute("tabelaAuxiliarAbreviada");

		return retorno;
	}
}