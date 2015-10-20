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
package gcom.gui.relatorio.cobranca;

import java.util.Collection;

import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.FiltroContaMotivoRevisao;
import gcom.gui.cobranca.FiltrarCobrancaSituacaoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.RelatorioManterCobrancaSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
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
 * @author Arthur Carvalho
 * @version 1.0
 */

public class GerarRelatorioCobrancaSituacaoManterAction extends
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

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarCobrancaSituacaoActionForm filtrarCobrancaSituacaoActionForm = (FiltrarCobrancaSituacaoActionForm) actionForm;

		FiltroCobrancaSituacao filtroCobrancaSituacao = (FiltroCobrancaSituacao) sessao
				.getAttribute("filtroCobrancaSituacao");

		// Inicio da parte que vai mandar os parametros para o relat�rio
		Fachada fachada = Fachada.getInstancia();
		
		CobrancaSituacao cobrancaSituacaoParametros = new CobrancaSituacao();

		String id = null;

		String idCobrancaSituacaoPesquisar = (String) filtrarCobrancaSituacaoActionForm.getId();

		if (idCobrancaSituacaoPesquisar != null && !idCobrancaSituacaoPesquisar.equals("")) {
			id = idCobrancaSituacaoPesquisar;
		}
		
		Short indicadorUso = null;
		
		if(filtrarCobrancaSituacaoActionForm.getIndicadorUso()!= null && !filtrarCobrancaSituacaoActionForm.getIndicadorUso().equals("")){
			
			indicadorUso = new Short ("" + filtrarCobrancaSituacaoActionForm.getIndicadorUso());
		}

		Short indicadorExigenciaAdvogado = null;
		
		if(filtrarCobrancaSituacaoActionForm.getIndicadorExigenciaAdvogado()!= null && !filtrarCobrancaSituacaoActionForm.getIndicadorExigenciaAdvogado().equals("")){
			
			indicadorExigenciaAdvogado = new Short ("" + filtrarCobrancaSituacaoActionForm.getIndicadorExigenciaAdvogado());
		}

		Short indicadorBloqueioParcelamento = null;
		
		if(filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioParcelamento()!= null && !filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioParcelamento().equals("")){
			
			indicadorBloqueioParcelamento = new Short ("" + filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioParcelamento());
		}
		
		
		if (filtrarCobrancaSituacaoActionForm.getContaMotivoRevisao() != null && !filtrarCobrancaSituacaoActionForm.getContaMotivoRevisao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
			filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(FiltroContaMotivoRevisao.ID, filtrarCobrancaSituacaoActionForm.getContaMotivoRevisao()));
			
			Collection colecaoCobrancaSituacao = fachada.pesquisar(filtroContaMotivoRevisao, ContaMotivoRevisao.class.getName());
			
			if (colecaoCobrancaSituacao != null && !colecaoCobrancaSituacao.isEmpty()) {
				ContaMotivoRevisao contaMotivoRevisao = (ContaMotivoRevisao) Util.retonarObjetoDeColecao(colecaoCobrancaSituacao);
				cobrancaSituacaoParametros.setContaMotivoRevisao(contaMotivoRevisao);
			}
			
		}
		
		
		// seta os parametros que ser�o mostrados no relat�rio

		cobrancaSituacaoParametros.setId(id == null ? null : new Integer(
				id));
		cobrancaSituacaoParametros.setDescricao(filtrarCobrancaSituacaoActionForm.getDescricao());
		cobrancaSituacaoParametros.setIndicadorExigenciaAdvogado(indicadorExigenciaAdvogado);
		cobrancaSituacaoParametros.setIndicadorBloqueioParcelamento(indicadorBloqueioParcelamento);
		cobrancaSituacaoParametros.setIndicadorUso(indicadorUso);
		// Fim da parte que vai mandar os parametros para o relat�rio

		// cria uma inst�ncia da classe do relat�rio
	
		RelatorioManterCobrancaSituacao relatorioManterCobrancaSituacao = new RelatorioManterCobrancaSituacao(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterCobrancaSituacao.addParametro("filtroCobrancaSituacao",
				filtroCobrancaSituacao);
		relatorioManterCobrancaSituacao.addParametro("cobrancaSituacaoParametros",
				cobrancaSituacaoParametros);

		// chama o met�do de gerar relat�rio passando o c�digo da analise
		// como par�metro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterCobrancaSituacao.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterCobrancaSituacao,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}