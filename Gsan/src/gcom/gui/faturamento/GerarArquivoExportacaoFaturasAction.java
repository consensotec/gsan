/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.faturamento;

import gcom.cadastro.cliente.Cliente;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.relatorio.RelatorioProcessado;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.FachadaException;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1684] - Gerar Arquivo Exportação Faturas
 * 
 * @author André Miranda
 * @date 10/06/2015
 */
public class GerarArquivoExportacaoFaturasAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessao = getSessao(request);
		GerarArquivoExportacaoFaturasActionForm form = (GerarArquivoExportacaoFaturasActionForm) actionForm;

		Integer anoMes = null;
		if (Util.verificarNaoVazio(form.getMesAno())) {
			anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno());
		}

		String idCliente = form.getIdCliente();
		if (Util.verificarIdNaoVazio(idCliente)) {
			verificarCliente(idCliente);
		}

		String idClienteSuperior = form.getIdClienteSuperior();
		if (Util.verificarIdNaoVazio(idClienteSuperior)) {
			verificarCliente(idClienteSuperior);
		}

		StringBuilder nomeArquivo = new StringBuilder();
		byte[] arquivo = null;
		try {
			arquivo = getFachada().gerarArquivoExportacaoFaturasAgrupadas(anoMes, idCliente, idClienteSuperior, nomeArquivo);
		} catch (FachadaException e) {
			throw e;
		} catch (Exception e) {
			throw new ActionServletException("erro.sistema", e);
		}

		RelatorioProcessado relatorioProcessado = new RelatorioProcessado(arquivo, TarefaRelatorio.TIPO_TXT);
		sessao.setAttribute("nomeArquivo", nomeArquivo.toString());
		sessao.setAttribute("relatorioProcessado", relatorioProcessado);
		sessao.setAttribute("tipoRelatorio", String.valueOf(relatorioProcessado.getTipoRelatorio()));
		request.setAttribute("telaSucessoRelatorio", true);

		montarPaginaSucesso(request, "Arquivo Gerado com Sucesso.",
				"Gerar outro Arquivo de Exportação",
				"exibirGerarArquivoExportacaoFaturasAction.do?menu=sim", "", "");

		return actionMapping.findForward("telaSucesso");
	}

	private void verificarCliente(String idCliente) throws ActionServletException {
		Cliente cliente = getFachada().pesquisarClienteDigitado(new Integer(idCliente));

		if (cliente == null) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Cliente");
		}
	}
}
