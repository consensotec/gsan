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
package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o da pagina de inserir bairro
 * 
 * @author Thiago Ten�rio
 * @created 28 de Junho de 2004
 */
public class ExibirAtualizarEfetuarRetiradaHidrometroAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("efetuarRetiradaHidrometro");
		EfetuarRetiradaHidrometroActionForm retiradaActionForm = (EfetuarRetiradaHidrometroActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel responsav�l pelo preenchimento do imovel no formul�rio
		String idOrdemServico = retiradaActionForm.getIdOrdemServico();

		OrdemServico ordemServico = null;

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
			filtroOrdemServico.adicionarParametro(new ParametroSimples(
					FiltroOrdemServico.ID, idOrdemServico));

			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.ligacaoAguaSituacao");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.ligacaoEsgotoSituacao");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.localidade");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.setorComercial");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.quadra");
			filtroOrdemServico
					.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.cliente");

			Collection colecaoOrdemServico = fachada.pesquisar(
					filtroOrdemServico, OrdemServico.class.getName());
			if (colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()) {

				ordemServico = (OrdemServico) colecaoOrdemServico.iterator()
						.next();

				// Inicio de codigo * Dados do Imov�l
				if (ordemServico.getRegistroAtendimento().getImovel() != null) {
					sessao.setAttribute("imovel", ordemServico
							.getRegistroAtendimento().getImovel());
					String matriculaImovel = ordemServico
							.getRegistroAtendimento().getImovel().getId()
							.toString();
					retiradaActionForm.setMatriculaImovel("" + matriculaImovel);

					// Inscri��o do Imov�l

					String inscricaoImovel = ordemServico
							.getRegistroAtendimento().getImovel()
							.getInscricaoFormatada();

					retiradaActionForm.setMatriculaImovel(matriculaImovel);
					retiradaActionForm.setInscricaoImovel(inscricaoImovel);

				} else {
					retiradaActionForm.setMatriculaImovel("");
					retiradaActionForm.setInscricaoImovel("");
				}
				// Cliente Usu�rio

				/*if (ordemServico.getRegistroAtendimento().getCliente() != null) {
					String clienteUsuario = ordemServico
							.getRegistroAtendimento().getCliente().getNome();
					retiradaActionForm.setClienteUsuario(clienteUsuario);
				} else {
					retiradaActionForm.setClienteUsuario("");
				}*/

				// CPF & CNPJ
				/*if (ordemServico.getRegistroAtendimento().getCliente() != null
						&& ordemServico.getRegistroAtendimento().getCliente()
								.getCpfFormatado() != "") {

					String cpfCnpj = Util.formatarCPFApresentacao(ordemServico
							.getRegistroAtendimento().getCliente()
							.getCpfFormatado());
					retiradaActionForm.setCpfCnpjCliente(cpfCnpj);
					retiradaActionForm.setCpfCnpjCliente(cpfCnpj);
				} else {
					if (ordemServico.getRegistroAtendimento().getCliente() != null
							&& ordemServico.getRegistroAtendimento()
									.getCliente().getCnpjFormatado() != "") {
						String cpfCnpj = Util
								.formatarCPFApresentacao(ordemServico
										.getRegistroAtendimento().getCliente()
										.getCnpjFormatado());
						retiradaActionForm.setCpfCnpjCliente(cpfCnpj);
					} else {
						retiradaActionForm.setCpfCnpjCliente("");
					}
				}*/

				if (ordemServico.getRegistroAtendimento().getImovel() != null
						&& ordemServico.getRegistroAtendimento().getImovel()
								.getLigacaoAguaSituacao() != null) {
					// Situa��o da Liga��o de Agua

					String situacaoLigacaoAgua = ordemServico
							.getRegistroAtendimento().getImovel()
							.getLigacaoAguaSituacao().getDescricao();
					retiradaActionForm
							.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

				} else {
					retiradaActionForm.setSituacaoLigacaoAgua("");
				}
				// Situa��o da Liga��o de Esgoto

				if (ordemServico.getRegistroAtendimento().getImovel() != null
						&& ordemServico.getRegistroAtendimento().getImovel()
								.getLigacaoEsgotoSituacao() != null) {
					String situacaoLigacaoEsgoto = ordemServico
							.getRegistroAtendimento().getImovel()
							.getLigacaoEsgotoSituacao().getDescricao();
					retiradaActionForm
							.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);
					//
				} else {
					retiradaActionForm.setSituacaoLigacaoEsgoto("");
				}

			} else {
				httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
				retiradaActionForm.setIdOrdemServico("");
				retiradaActionForm
						.setNomeOrdemServico("ORDEM DE SERVI�O INEXISTENTE");
			}
			// -------Fim da Parte que trata do c�digo quando o usu�rio tecla
			// enter

			if (ordemServico.getRegistroAtendimento().getImovel() != null) {

				String idImovel = ordemServico.getRegistroAtendimento()
						.getImovel().getId().toString();

				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovel));

				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.hidrometro");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.tipoMedicao");

				Collection colecaoImovel = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());
				if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

					Imovel imovel = (Imovel) colecaoImovel.iterator().next();

					// Inicio de codigo * Dados do Imov�l
					if (imovel.getHidrometroInstalacaoHistorico() != null) {
						String hidrometro = imovel
								.getHidrometroInstalacaoHistorico()
								.getHidrometro().getNumero();
						retiradaActionForm.setHidrometro(hidrometro);

						// Tipo Medi��o

						if (imovel.getHidrometroInstalacaoHistorico()
								.getMedicaoTipo() != null) {
							String medicaoTipo = imovel
									.getHidrometroInstalacaoHistorico()
									.getMedicaoTipo().getDescricao();
							retiradaActionForm.setMedicaoTipo(medicaoTipo);
						} else {
							retiradaActionForm.setMedicaoTipo("");
						}
						if (imovel.getHidrometroInstalacaoHistorico()
								.getDataRetirada() != null) {
							Date dataRetirada = imovel
									.getHidrometroInstalacaoHistorico()
									.getDataRetirada();
							retiradaActionForm.setDataRetirada(Util
									.formatarData(dataRetirada));
						} else {
							retiradaActionForm.setDataRetirada("");
						}

						if (imovel.getHidrometroInstalacaoHistorico()
								.getNumeroLeituraRetirada() == 0) {
							Integer numeroLeituraRetirada = imovel
									.getHidrometroInstalacaoHistorico()
									.getNumeroLeituraRetirada();
							retiradaActionForm
									.setNumeroLeitura(numeroLeituraRetirada
											.toString());
						} else {
							retiradaActionForm.setNumeroLeitura("");
						}

					}
				}

			}
		}

		return retorno;
	}

}
