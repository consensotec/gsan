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
package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.debitoautomatico.FiltroDebitoAutomatico;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.arrecadacao.FiltrarDebitoAutomaticoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioManterDebitoAutomatico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corr�a
 * @version 1.0
 */

public class GerarRelatorioDebitoAutomaticoManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

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
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarDebitoAutomaticoActionForm filtrarDebitoAutomaticoActionForm = (FiltrarDebitoAutomaticoActionForm) actionForm;

		FiltroDebitoAutomatico filtroDebitoAutomatico = (FiltroDebitoAutomatico) sessao.getAttribute("filtroDebitoAutomatico");

		DebitoAutomatico debitoAutomaticoParametros = new DebitoAutomatico();
		
		// pega os par�metros informados na tela de consulta de d�bitos autom�ticos
		
		if(Util.verificarNaoVazio(filtrarDebitoAutomaticoActionForm.getMatricula())){
			debitoAutomaticoParametros.setImovel(new Imovel(Integer.parseInt(filtrarDebitoAutomaticoActionForm.getMatricula().trim())));
		}

		if(Util.verificarNaoVazio(filtrarDebitoAutomaticoActionForm.getAgenciaCodigo())){

			FiltroAgencia filtroAgencia = new FiltroAgencia();
			filtroAgencia.adicionarParametro(new ParametroSimples(FiltroAgencia.CODIGO_AGENCIA,filtrarDebitoAutomaticoActionForm.getAgenciaCodigo().trim()));
			filtroAgencia.adicionarParametro(new ParametroSimples(FiltroAgencia.BANCO_ID,Integer.parseInt(filtrarDebitoAutomaticoActionForm.getBancoID().trim())));

			Agencia agencia = (Agencia) Fachada.getInstancia().pesquisar(filtroAgencia,Agencia.class.getName()).iterator().next();
			debitoAutomaticoParametros.setAgencia(agencia);
		}

		if(Util.verificarNaoVazio(filtrarDebitoAutomaticoActionForm.getBancoID())){

			if(debitoAutomaticoParametros.getAgencia() == null){
				debitoAutomaticoParametros.setAgencia(new Agencia());				
			}

			FiltroBanco filtroBanco = new FiltroBanco();
			filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID,Integer.parseInt(filtrarDebitoAutomaticoActionForm.getBancoID().trim())));

			Banco banco = (Banco) Fachada.getInstancia().pesquisar(filtroBanco,Banco.class.getName()).iterator().next();
			debitoAutomaticoParametros.getAgencia().setBanco(banco);
		}

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		RelatorioManterDebitoAutomatico relatorioManterDebitoAutomatico = new RelatorioManterDebitoAutomatico((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterDebitoAutomatico.addParametro("filtroDebitoAutomatico",filtroDebitoAutomatico);
		relatorioManterDebitoAutomatico.addParametro("debitoAutomaticoParametros",debitoAutomaticoParametros);
		relatorioManterDebitoAutomatico.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		try {
			retorno = processarExibicaoRelatorio(relatorioManterDebitoAutomatico,
					tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

		} catch (SistemaException ex) {
			reportarErros(httpServletRequest, "erro.sistema");
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			reportarErros(httpServletRequest, "erro.relatorio.vazio");
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}

}