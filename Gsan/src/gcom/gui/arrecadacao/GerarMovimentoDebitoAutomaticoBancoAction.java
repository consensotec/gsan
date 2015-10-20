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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.FiltroArrecadadorMovimento;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * processamento para gerar movimento de d�bito autom�tico para o banco
 * 
 * @author S�vio Luiz
 * @date 20/04/2006
 */
public class GerarMovimentoDebitoAutomaticoBancoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		GerarMovimentoDebitoAutomaticoBancoActionForm gerarMovimentoDebitoAutomaticoBancoActionForm = (GerarMovimentoDebitoAutomaticoBancoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// caso a op��o do movimento de debito automatico seja diferente de
		// nulo
		if (gerarMovimentoDebitoAutomaticoBancoActionForm
				.getOpcaoMovimentoDebitoAutomatico() != null
				&& !gerarMovimentoDebitoAutomaticoBancoActionForm
						.getOpcaoMovimentoDebitoAutomatico().equals("")) {
			// Se a op��o seja Gerar Movimento de D�bito Autom�tico
			if (gerarMovimentoDebitoAutomaticoBancoActionForm
					.getOpcaoMovimentoDebitoAutomatico().equals("1")) {
				// recupera o Map<Banco, Collection<DebitoAutomaticoMovimento>>
				// da sessao
				Map<Banco, Collection<DebitoAutomaticoMovimento>> debitosAutomaticoBancosMap = (Map<Banco, Collection<DebitoAutomaticoMovimento>>) sessao
						.getAttribute("debitosAutomaticoBancosMap");
				String[] idsBancos = gerarMovimentoDebitoAutomaticoBancoActionForm
						.getIdsCodigosBancos();
				// cria um Map<Banco, Collection<DebitoAutomaticoMovimento>>
				// novo para inserir s� os que foram escolhidos
				if (debitosAutomaticoBancosMap != null
						&& debitosAutomaticoBancosMap.size() != idsBancos.length) {
					Map<Banco, Collection<DebitoAutomaticoMovimento>> debitosAutomaticoBancosMapNovo = new HashMap();

					for (int i = 0; i < idsBancos.length; i++) {
						Integer idBanco = new Integer(idsBancos[i]);
						Iterator debitosAutomaticoBancosIterator = debitosAutomaticoBancosMap
								.keySet().iterator();
						while (debitosAutomaticoBancosIterator.hasNext()) {
							Banco banco = (Banco) debitosAutomaticoBancosIterator
									.next();
							if (banco.getId().equals(idBanco)) {
								Collection<DebitoAutomaticoMovimento> debitoAutomaticoMovimento = debitosAutomaticoBancosMap
										.get(banco);
								debitosAutomaticoBancosMapNovo.put(banco,
										debitoAutomaticoMovimento);
							}

						}
					}
					// manda os debitos autom�ticos selecionados
					fachada.gerarMovimentoDebitoAutomaticoBanco(
							debitosAutomaticoBancosMapNovo,
							(Usuario) (httpServletRequest.getSession(false))
									.getAttribute("usuarioLogado"));

				} else {
					// caso todos os debitos autom�ticos forem selecionados
					fachada.gerarMovimentoDebitoAutomaticoBanco(
							debitosAutomaticoBancosMap,
							(Usuario) (httpServletRequest.getSession(false))
									.getAttribute("usuarioLogado"));
				}
			} else {
				// no caso da op��o ser Regerar Arquivo TXT do Movimento de
				// D�bito Autom�tico
				String codigoRemessa = gerarMovimentoDebitoAutomaticoBancoActionForm
						.getCodigoRemessaMovimento();
				String identificacaoServicoMovimento = gerarMovimentoDebitoAutomaticoBancoActionForm
						.getIdentificacaoServicoMovimento();
				if (codigoRemessa.equals("1")
						&& identificacaoServicoMovimento
								.equals(ConstantesSistema.DEBITO_AUTOMATICO)) {
					Integer idArrecadadorMovimento = new Integer(
							gerarMovimentoDebitoAutomaticoBancoActionForm
									.getIdArrecadadorMovimento());
					FiltroArrecadadorMovimento filtroArrecadadorMovimento = new FiltroArrecadadorMovimento();
					filtroArrecadadorMovimento
							.adicionarParametro(new ParametroSimples(
									FiltroArrecadadorMovimento.ID,
									idArrecadadorMovimento));
					Collection colecaoArrecadadorMovimento = fachada.pesquisar(
							filtroArrecadadorMovimento,
							ArrecadadorMovimento.class.getName());
					if (colecaoArrecadadorMovimento != null
							&& !colecaoArrecadadorMovimento.isEmpty()) {
						ArrecadadorMovimento arrecadadorMovimento = (ArrecadadorMovimento) Util
								.retonarObjetoDeColecao(colecaoArrecadadorMovimento);

						String enviarBanco = gerarMovimentoDebitoAutomaticoBancoActionForm
								.getOpcaoEnvioParaBanco();

						fachada
								.regerarArquivoTxtMovimentoDebitoAutomatico(
										arrecadadorMovimento, enviarBanco,
										(Usuario) (httpServletRequest.getSession(false))
										.getAttribute("usuarioLogado"));
						
					}
				} else {
					throw new ActionServletException(
							"atencao.movimento.nao.envio");
				}
			}

		}
		gerarMovimentoDebitoAutomaticoBancoActionForm.setDataAtual(new Date());
		sessao.removeAttribute("colecaogerarMovimentoDebitoAutomatico");

		// montando p�gina de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Movimento D�bito Automatico Enviado para Processamento", "Voltar",
				"/exibirGerarMovimentoDebitoAutomaticoBancoAction.do");

		return retorno;
	}
}
