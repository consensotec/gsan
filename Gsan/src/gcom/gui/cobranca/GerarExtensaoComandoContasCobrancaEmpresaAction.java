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
 * R�mulo Aur�lio de Melo Souza Filho
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

package gcom.gui.cobranca;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaExtensao;
import gcom.cobranca.GerarExtensaoComandoContasCobrancaEmpresaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0879] Gerar Arquivo Texto das Contas em Cobran�a por Empresa
 * 
 * @author R�mulo Aur�lio
 * @since 02/01/2009
 */

public class GerarExtensaoComandoContasCobrancaEmpresaAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		GerarExtensaoComandoContasCobrancaEmpresaActionForm form = (GerarExtensaoComandoContasCobrancaEmpresaActionForm) actionForm;
		// Obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String[] idsRegistros = form.getIdRegistros();

		String idEmpresa = form.getIdEmpresa();

		Collection colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper = (Collection) sessao
				.getAttribute("colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper");

		Collection colecaoComandoEmpresaCobrancaContaExtensao = new ArrayList();

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		ComandoEmpresaCobrancaContaExtensao comandoEmpresaCobrancaContaExtensao = null;

		int count = 0;

		if (idEmpresa != null && !idEmpresa.trim().equals("")) {

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idEmpresa));

			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());

			if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
				throw new ActionServletException("atencao.empresa.inexistente ");

			} else {

				if (idsRegistros != null && idsRegistros.length != 0) {

					for (int i = 0; i < idsRegistros.length; i++) {

						comandoEmpresaCobrancaContaExtensao = new ComandoEmpresaCobrancaContaExtensao();

						ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta = new ComandoEmpresaCobrancaConta();

						Integer id = Integer.parseInt(idsRegistros[i]);

						// -----------------------------------------------
						Iterator it = colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper
								.iterator();

						String dataInicial;
						String dataFinal;
						String qtdDiasVencimento;

						while (it.hasNext()) {
							GerarExtensaoComandoContasCobrancaEmpresaHelper helper = (GerarExtensaoComandoContasCobrancaEmpresaHelper) it
									.next();

							if (helper
									.getGerarArquivoTextoContasCobrancaEmpresaHelper()
									.getIdComandoEmpresaCobrancaConta()
									.toString().equals(id.toString())) {

								dataInicial = (String) httpServletRequest
										.getParameter("a"
												+ helper
														.getGerarArquivoTextoContasCobrancaEmpresaHelper()
														.getIdComandoEmpresaCobrancaConta()
														.toString());
								if (dataInicial == null
										|| dataInicial.equals("")) {
									throw new ActionServletException(
											"atencao.Informe_entidade", null,
											"Refer�ncia Inicial da extens�o do comando");
								}
								
								if(!this.validarMesAno(dataInicial)){
									throw new ActionServletException("atencao.invalid",null,"Refer�ncia");
								}

								Integer anoMesInicialReferencia = Util
										.formatarMesAnoComBarraParaAnoMes(dataInicial);
								

								dataFinal = (String) httpServletRequest
										.getParameter("b"
												+ helper
														.getGerarArquivoTextoContasCobrancaEmpresaHelper()
														.getIdComandoEmpresaCobrancaConta()
														.toString());

								if (dataFinal == null || dataFinal.equals("")) {

									throw new ActionServletException(
											"atencao.Informe_entidade", null,
											"Refer�ncia Final da extens�o do comando");
								}
								
								if(!this.validarMesAno(dataFinal)){
									throw new ActionServletException("atencao.invalid",null,"Refer�ncia");
								}

								Integer anoMesFinalReferencia = Util
										.formatarMesAnoComBarraParaAnoMes(dataFinal);

								
								
								if (anoMesInicialReferencia != null
										&& anoMesFinalReferencia != null
										&& Util.compararAnoMesReferencia(
												anoMesInicialReferencia,
												anoMesFinalReferencia, ">")) {
									throw new ActionServletException(
											"atencao.referencia_final_anterior_referencia_inicial");
								}
								
								
								qtdDiasVencimento = (String) httpServletRequest
										.getParameter("c"
												+ helper
														.getGerarArquivoTextoContasCobrancaEmpresaHelper()
														.getIdComandoEmpresaCobrancaConta()
														.toString());
								
								
								if(qtdDiasVencimento != null && !qtdDiasVencimento.equals(""))
									comandoEmpresaCobrancaContaExtensao.setNumeroDiasVencimentoContas(Integer.parseInt(qtdDiasVencimento));
								
								comandoEmpresaCobrancaContaExtensao
										.setReferenciaContaInicial(anoMesInicialReferencia);

								comandoEmpresaCobrancaContaExtensao
										.setReferenciaContaFinal(anoMesFinalReferencia);
								
								
								

								// -----------------------------------------

								comandoEmpresaCobrancaConta.setId(id);

								comandoEmpresaCobrancaContaExtensao
										.setComandoEmpresaCobrancaConta(comandoEmpresaCobrancaConta);

								comandoEmpresaCobrancaConta
										.setEmpresa((Empresa) Util
												.retonarObjetoDeColecao(colecaoEmpresa));

								comandoEmpresaCobrancaContaExtensao
										.setUsuario(usuarioLogado);

								
								colecaoComandoEmpresaCobrancaContaExtensao
										.add(comandoEmpresaCobrancaContaExtensao);

								fachada
										.inserirExtensaoComandoContasCobrancaEmpresa(
												comandoEmpresaCobrancaContaExtensao,
												colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper);
								count = count + 1;

							}
						}

					}

				}

			}
		} else {
			throw new ActionServletException("atencao.Informe_entidade", null,
					"Empresa");
		}

		// montando p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Foram geradas " + count
				+ " extens�es de comandos de contas em cobran�a com sucesso.",
				"Gerar outra Extens�o de Comando de Contas por Empresa",
				"exibirGerarExtensaoComandoContasCobrancaEmpresaAction.do?menu=sim");

		return retorno;
	}
	
	
	private boolean validarMesAno(String mesAnoReferencia) {
		boolean mesAnoValido = true;

		if (mesAnoReferencia.length() == 7) {
			String mes = mesAnoReferencia.substring(0, 2);
			String barra = mesAnoReferencia.substring(2, 3);
			// String ano = mesAnoReferencia.substring(3, 7);

			if(barra.equals("/")){
			
				try {
					int mesInt = Integer.parseInt(mes);
					// int anoInt = Integer.parseInt(ano);
	
					if (mesInt > 12) {
						mesAnoValido = false;
					}
				} catch (NumberFormatException e) {
					mesAnoValido = false;
				}
				
			}
			else{
				mesAnoValido = false;
			}

		} else {
			mesAnoValido = false;
		}

		return mesAnoValido;
	}

}
