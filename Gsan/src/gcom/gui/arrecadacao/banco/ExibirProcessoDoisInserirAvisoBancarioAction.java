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
package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirProcessoDoisInserirAvisoBancarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Declaracao de Variaveis principais
		ActionForward retorno = actionMapping
				.findForward("exibirInserirAvisoBancarioProcessoDois");
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		InserirAvisoBancarioActionForm form = (InserirAvisoBancarioActionForm) actionForm;
		// Declaracao de Variaveis principais

		form.setAvisoBancario(null);
		
		// Inicializacao de variaveis de pesquisa
		String idArrecadador = form.getCodigoArrecadador();
		String dataLancamento = form.getDataLancamento();
		// Inicializacao de variaveis de pesquisa

		Arrecadador arrecadadorVolta = (Arrecadador)sessao.getAttribute("arrecadador");
		String data = (String)sessao.getAttribute("data");
		if((sessao.getAttribute("arrecadador") != null 
				&&  !arrecadadorVolta.getId().toString().equalsIgnoreCase(idArrecadador)) 
				|| (data != null && !data.equalsIgnoreCase("") 
						&&  !data.equalsIgnoreCase(dataLancamento)))
		{
			form.setTipoAviso("");
			form.setNumeroDocumento("");
			form.setDataRealizacao("");
			form.setValorArrecadacao("");
			form.setValorDevolucao("");
			form.setValorDeducoes("");
			form.setValorAviso("");
		}
		
		// Filtro de Arrecadador caso usu�rio n�o tenho pesquisado no primeiro
		// processo
		Arrecadador arrecadador = null;
		Collection<Arrecadador> collectionArrecadador = null;
		if (idArrecadador != null && !idArrecadador.trim().equals("")) {

			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.CODIGO_AGENTE, idArrecadador));
			filtroArrecadador
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			collectionArrecadador = fachada.pesquisar(filtroArrecadador,
					Arrecadador.class.getName());

			if (!collectionArrecadador.isEmpty()) {
				Iterator iterator = collectionArrecadador.iterator();
				if (iterator.hasNext())
					arrecadador = (Arrecadador) iterator.next();
				form.setNomeArrecadador(arrecadador.getCliente().getNome());
				sessao.setAttribute("arrecadador", arrecadador);
			} else {
				throw new ActionServletException(
						"atencao.arrecadador.codigo.inexistente", null,
						idArrecadador);
			}
		}

		// Inicializacao de filtrode aviso bancario
		FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();

		if (idArrecadador != null && !idArrecadador.equals("")) {

			filtroAvisoBancario.adicionarParametro(new ParametroSimples(
					FiltroAvisoBancario.ARRECADADOR_CODIGO_AGENTE,
					idArrecadador));

		}

		Date dataAtual = new Date();

		Date dataLancamentoFormatada = null;

		if (dataLancamento != null && !dataLancamento.equals("")) {

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			try {
				dataLancamentoFormatada = format.parse(dataLancamento);
			} catch (ParseException ex) {
				throw new ActionServletException("erro.sistema");
			}

			if (dataLancamentoFormatada.after(dataAtual)) {

				String dataCorrente = format.format(dataAtual);

				throw new ActionServletException(
						"atencao.data.lancamento.posterior", null, dataCorrente);

			} else {

				filtroAvisoBancario.adicionarParametro(new ParametroSimples(
						FiltroAvisoBancario.DATA_LANCAMENTO,
						Util.formatarDataSemHora(dataLancamentoFormatada)));

			}

		}
		// Pesquisa de aviso bancario
		Collection<AvisoBancario> collectionAvisoBancario = fachada.pesquisar(
				filtroAvisoBancario, AvisoBancario.class.getName());

		String numeroPagina = httpServletRequest.getParameter("numeroPagina");

		if (collectionAvisoBancario == null
				|| collectionAvisoBancario.isEmpty()) {

			if (numeroPagina.equals("3")) {
				retorno = actionMapping
						.findForward("exibirInserirAvisoBancarioProcessoDoisParaUm");
				
			} else {

				retorno = actionMapping
						.findForward("exibirInserirAvisoBancarioProcessoDoisParaTres");
				Short numeroSequencial = fachada
						.pesquisarValorMaximoNumeroSequencial(
								dataLancamentoFormatada, ""
										+ arrecadador.getId());

				if (numeroSequencial != null) {
					numeroSequencial = new Short(""
							+ (numeroSequencial.intValue() + 1));
				} else {
					numeroSequencial = 1;
				}
				form.setNumeroSequencial("" + numeroSequencial);

			}

		} else {

			filtroAvisoBancario.adicionarParametro(new ParametroNulo(
					FiltroAvisoBancario.DATA_REALIZADA));
			// Inicializacao de filtrode aviso bancario

			// Pesquisa de aviso bancario
			filtroAvisoBancario.setCampoOrderBy(FiltroAvisoBancario.NUMERO_SEQUENCIAL);
			collectionAvisoBancario = fachada.pesquisar(filtroAvisoBancario,
					AvisoBancario.class.getName());
			// Pesquisa de aviso bancario

			// Validacao de collection nao nula e nao vazia
			if (collectionAvisoBancario != null
					&& !collectionAvisoBancario.isEmpty()) {
				httpServletRequest.setAttribute("collectionAvisoBancario",
						collectionAvisoBancario);

			} else {
				if (numeroPagina.equals("3")) {
					retorno = actionMapping
							.findForward("exibirInserirAvisoBancarioProcessoDoisParaUm");
				} else {
					retorno = actionMapping
							.findForward("exibirInserirAvisoBancarioProcessoDoisParaTres");
					form.setNumeroSequencial("1");
				}
			}

			// Validacao de collection nao nula e nao vazia

		}
		return retorno;
	}

}
