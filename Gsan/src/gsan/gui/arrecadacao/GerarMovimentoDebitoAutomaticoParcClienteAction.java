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
package gsan.gui.arrecadacao;

import gsan.arrecadacao.ArrecadadorMovimento;
import gsan.arrecadacao.FiltroArrecadadorMovimento;
import gsan.arrecadacao.banco.Banco;
import gsan.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gsan.arrecadacao.debitoautomatico.DebitoAutomaticoMovimentoParcelamentoCliente;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

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
 * 
 * [UC1349] Gerar Movimento de Débito Automático de Parcelamento por Cliente
 * 
 * @author Hugo Azevedo
 * @date 27/06/2012
 */
public class GerarMovimentoDebitoAutomaticoParcClienteAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		GerarMovimentoDebitoAutomaticoParcClienteActionForm form = 
				(GerarMovimentoDebitoAutomaticoParcClienteActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		//Data do início da operação
		Date horaInicioOperacao = (Date)sessao.getAttribute("horaInicioOperacao");

		// caso a opção do movimento de debito automatico seja diferente de
		// nulo
		if (form
				.getOpcaoMovimentoDebitoAutomatico() != null
				&& !form.getOpcaoMovimentoDebitoAutomatico().equals("")) {
			// Se a opção seja Gerar Movimento de Débito Automático
			
			//[SB0001] - Gerar Movimento para Débito Automático
			if (form.getOpcaoMovimentoDebitoAutomatico().equals("1")) {
				// recupera o Map<Banco, Collection<DebitoAutomaticoMovimentoParcelamentoCliente>>
				// da sessao
				Map<Banco, Collection<DebitoAutomaticoMovimentoParcelamentoCliente>> debitosAutomaticoBancosMap = 
						(Map<Banco, Collection<DebitoAutomaticoMovimentoParcelamentoCliente>>) sessao.getAttribute("debitosAutomaticoBancosMap");
				String[] idsBancos = form.getIdsCodigosBancos();
				// cria um Map<Banco, Collection<DebitoAutomaticoMovimento>>
				// novo para inserir só os que foram escolhidos
				if (debitosAutomaticoBancosMap != null
						&& debitosAutomaticoBancosMap.size() != idsBancos.length) {
					Map<Banco, Collection<DebitoAutomaticoMovimentoParcelamentoCliente>> debitosAutomaticoBancosMapNovo = new HashMap();

					for (int i = 0; i < idsBancos.length; i++) {
						Integer idBanco = new Integer(idsBancos[i]);
						Iterator debitosAutomaticoBancosIterator = debitosAutomaticoBancosMap
								.keySet().iterator();
						while (debitosAutomaticoBancosIterator.hasNext()) {
							Banco banco = (Banco) debitosAutomaticoBancosIterator
									.next();
							if (banco.getId().equals(idBanco)) {
								Collection<DebitoAutomaticoMovimentoParcelamentoCliente> debitoAutomaticoMovimento = 
										debitosAutomaticoBancosMap.get(banco);
								
								debitosAutomaticoBancosMapNovo.put(banco,debitoAutomaticoMovimento);
							}

						}
					}
					
					validarMovimento(debitosAutomaticoBancosMapNovo);
					
					// manda os debitos automáticos selecionados
					fachada.gerarMovimentoDebitoAutomaticoParcCliente(
							debitosAutomaticoBancosMapNovo,
							(Usuario) (httpServletRequest.getSession(false))
									.getAttribute("usuarioLogado"));

				} else {
					
					validarMovimento(debitosAutomaticoBancosMap);
					
					fachada.gerarMovimentoDebitoAutomaticoParcCliente(
							debitosAutomaticoBancosMap,
							(Usuario) (httpServletRequest.getSession(false))
									.getAttribute("usuarioLogado"));
				}
			} 
			else {
				//3.2. [SB0003] - Regerar arquivo TXT para um movimento de débito automático gerado anteriormente
				String codigoRemessa = form.getCodigoRemessaMovimento();
				String identificacaoServicoMovimento = form.getIdentificacaoServicoMovimento();
				if (codigoRemessa.equals("1")
						&& identificacaoServicoMovimento
								.equals(ConstantesSistema.DEBITO_AUTOMATICO)) {
					Integer idArrecadadorMovimento = new Integer(form.getIdArrecadadorMovimento());
					FiltroArrecadadorMovimento filtroArrecadadorMovimento = new FiltroArrecadadorMovimento();
					filtroArrecadadorMovimento.
									adicionarParametro(new ParametroSimples(FiltroArrecadadorMovimento.ID,idArrecadadorMovimento));
					Collection colecaoArrecadadorMovimento = fachada.pesquisar(filtroArrecadadorMovimento,ArrecadadorMovimento.class.getName());
					if (colecaoArrecadadorMovimento != null
							&& !colecaoArrecadadorMovimento.isEmpty()) {
						ArrecadadorMovimento arrecadadorMovimento = (ArrecadadorMovimento) Util
								.retonarObjetoDeColecao(colecaoArrecadadorMovimento);

						String enviarBanco = form.getOpcaoEnvioParaBanco();
						
						//5. O sistema gera o arquivo TXT para envio ao banco 
						//   [SB0004] - Gerar Arquivo TXT para Envio ao Banco
						//   passando o movimento do arrecadador 
						//   e os itens associados
						fachada.regerarArquivoTxtMovimentoDebitoAutomaticoParcelamentoCliente(
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
		form.setDataAtual(new Date());
		sessao.removeAttribute("colecaogerarMovimentoDebitoAutomatico");

		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Movimento Débito Automatico Enviado para Processamento", "Voltar",
				"exibirGerarMovimentoDebitoAutomaticoParcClienteAction.do?menu=sim");

		return retorno;
	}
	
	

	private void validarMovimento(
			Map<Banco, Collection<DebitoAutomaticoMovimentoParcelamentoCliente>> debitosAutomaticoBancosMapNovo) {
		
		// cria uma coleção com a a chave(Banco) do Map
		Iterator debitosAutomaticoParcClienteIterator = 
				debitosAutomaticoBancosMapNovo.keySet().iterator();
		
		while (debitosAutomaticoParcClienteIterator.hasNext()) {
			
			Banco banco = (Banco) debitosAutomaticoParcClienteIterator.next();
			
			// recupera a coleção de valores(DebitoAutomaticoMovimento) pelo
			// valor, no map
			Collection<DebitoAutomaticoMovimentoParcelamentoCliente> colecaoDebitoAutomaticoMovimento = 
					debitosAutomaticoBancosMapNovo.get(banco);
			
			Iterator debitoAutomaticoMovimentoIterator = 
					colecaoDebitoAutomaticoMovimento.iterator();
		
			while (debitoAutomaticoMovimentoIterator.hasNext()) {
				DebitoAutomaticoMovimentoParcelamentoCliente debitoAutomaticoMovimentoParcelamentoCliente = 
					(DebitoAutomaticoMovimentoParcelamentoCliente) debitoAutomaticoMovimentoIterator.next();
			
				DebitoAutomaticoMovimentoParcelamentoCliente debitoAtual = Fachada.getInstancia().obterDebitoAutomaticoMovParcCliente(debitoAutomaticoMovimentoParcelamentoCliente.getId());
				
				
				//Verifica se já foi atualizado por outra transação
				if(Util.compararDataTime(debitoAutomaticoMovimentoParcelamentoCliente.getUltimaAlteracao(), debitoAtual.getUltimaAlteracao()) != 0){
					throw new ActionServletException("atencao.movimento_gerado_outro_operador");
				}
			}
		}
		
	}
}
