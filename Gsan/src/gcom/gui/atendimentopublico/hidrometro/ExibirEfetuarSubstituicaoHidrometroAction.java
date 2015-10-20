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

import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gcom.micromedicao.hidrometro.FiltroHidrometroSituacao;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de efetuar substitui��o de
 * hidr�metro
 * 
 * @author Ana Maria
 * @date 19/07/2006
 */
public class ExibirEfetuarSubstituicaoHidrometroAction extends GcomAction {
	/**
	 * Este caso de uso permite efetuar a substitui��o de hidr�metro, sendo
	 * chamado pela funcionalidade que encerra a execu��o da ordem de servi�o ou
	 * chamada diretamente do Menu.
	 * 
	 * [UC0364] Efetuar Substitui��o de Hidr�metro
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("efetuarSubstituicaoHidrometro");

		EfetuarSubstituicaoHidrometroActionForm form = (EfetuarSubstituicaoHidrometroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		
		Boolean veioEncerrarOS = null;
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (form.getVeioEncerrarOS() != null
					&& !form
							.getVeioEncerrarOS().equals("")) {
				if (form.getVeioEncerrarOS()
						.toLowerCase().equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}

		this.pesquisarObjetosObrigatorios(httpServletRequest);

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
        // Colocado por Vivianne Sousa em 05/12/2007
        // ------------------------------------------------------------
        if(form.getIndicadorTrocaProtecao() == null){
            form.setIndicadorTrocaProtecao(ConstantesSistema.NAO.toString());
        }
        
        if(form.getIndicadorTrocaRegistro() == null){
            form.setIndicadorTrocaRegistro(ConstantesSistema.NAO.toString());
        }
        // ------------------------------------------------------------
        
		String idOrdemServico = null;
		
		if (form.getIdOrdemServico() != null) {
		
			idOrdemServico = form.getIdOrdemServico();
		
		} 
		else {
			
			idOrdemServico = (String) httpServletRequest
			.getAttribute("veioEncerrarOS");
			
			form.setDataInstalacao((String) httpServletRequest
			.getAttribute("dataEncerramento"));
			
			form.setDataRetirada((String) httpServletRequest
			.getAttribute("dataEncerramento"));

			sessao.setAttribute("caminhoRetornoIntegracaoComercial",
			httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}

		if (httpServletRequest.getAttribute("semMenu") != null || sessao.getAttribute("semMenu") != null ) {
		
			sessao.setAttribute("semMenu", "SIM");
			
			form.setIndicadorSemMenu("sim");
			
			if (httpServletRequest.getParameter("idCampoEnviarDados") != null ) {
				
				form.setNumeroHidrometro( "" + httpServletRequest.getParameter("idCampoEnviarDados"));
				
			}
			
			
		} 
		else {
			
			sessao.removeAttribute("semMenu");
			form.setIndicadorSemMenu("nao");
		}
		
		
		OrdemServico ordemServico = null;
		Imovel imovel = null;
		Imovel imovelComLocalidade = null;
		
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
			
			ordemServico = fachada
			.recuperaOSPorId(new Integer(idOrdemServico));
			
			if (ordemServico != null){
				
				//[FS0013] - Altera��o de Valor
				this.permitirAlteracaoValor(ordemServico.getServicoTipo(),
						form);

				// Colocado por Raphael Rossiter em 04/05/2007 (Analista:
				// Rosana Carvalho)
				BigDecimal valorDebito = this.calcularValores(
				httpServletRequest, ordemServico, form);
			
				// -----------------------------------------------------------
				// Verificar permiss�o especial
				boolean temPermissaoMotivoNaoCobranca = fachada
						.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
				// -----------------------------------------------------------

				if (temPermissaoMotivoNaoCobranca) {
					
					httpServletRequest.setAttribute(
							"permissaoMotivoNaoCobranca",
							temPermissaoMotivoNaoCobranca);
				} else {
					
					form.setPercentualCobranca("100");
					form.setQuantidadeParcelas("1");
					form.setValorParcelas(Util.formataBigDecimal(
					valorDebito, 2, true));
				}
				
				if(ordemServico.getImovel() != null){
                    imovel = ordemServico.getImovel();
                }else{
                    imovel = ordemServico.getRegistroAtendimento()
                    .getImovel();
                }
				
				if (imovel != null) {
					FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovel.getId()));
					
					Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
					
					imovelComLocalidade = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
					
					if (imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null) {
						form.setIdLocalArmazenagem(imovelComLocalidade
										.getLocalidade()
										.getHidrometroLocalArmazenagem()
										.getId().toString());
					}
				}
				
			}
		}
		
		
		if (httpServletRequest.getParameter("pesquisaHidrometro") != null
				&& !httpServletRequest.getParameter("pesquisaHidrometro")
						.equals("")) {
			String numeroHidrometro = form.getNumeroHidrometro();

			// Verificar se o n�mero do hidr�metro n�o est� cadastrado
			if (numeroHidrometro != null && !numeroHidrometro.trim().equals("")) {

				// Filtro para descobrir id do Hidrometro
				FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
				filtroHidrometro.adicionarParametro(new ParametroSimples(
						FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));

				Collection colecaoHidrometro = fachada.pesquisar(filtroHidrometro, Hidrometro.class.getName());

				if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
					form.setNumeroHidrometro("");
					throw new ActionServletException(
							"atencao.numero_hidrometro_inexistente", null, numeroHidrometro);
				} else {
					Hidrometro hidrometro = (Hidrometro) Util.retonarObjetoDeColecao(colecaoHidrometro);
					
					if (imovelComLocalidade != null && imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null &&
						!hidrometro.getHidrometroLocalArmazenagem().getId().equals(imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem().getId())) {
						throw new ActionServletException("atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
					}
				}
			}
			
			validarFinalidadeHidrometro(numeroHidrometro, form.getTipoMedicao());
			
			httpServletRequest.setAttribute("nomeCampo", "localInstalacao");
			
		} else {

			if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

				if (ordemServico != null) {

					httpServletRequest.setAttribute("ordemServicoEncontrado",
							"true");

					fachada.validarExibirSubstituicaoHidrometro(ordemServico,
							veioEncerrarOS);

					sessao.setAttribute("ordemServico", ordemServico);

					form.setIdOrdemServico(idOrdemServico);
					form.setNomeOrdemServico(ordemServico.getServicoTipo()
							.getDescricao());

					form.setVeioEncerrarOS("" + veioEncerrarOS);

					if (ordemServico.getDataEncerramento() != null
							&& !ordemServico.getDataEncerramento().equals("")) {
						form.setDataRetirada(Util.formatarData(ordemServico
								.getDataEncerramento()));
					}
                    
                    if(ordemServico.getImovel() != null){
                        imovel = ordemServico.getImovel();
                    }else{
                        imovel = ordemServico.getRegistroAtendimento()
                        .getImovel();
                    }
                    
					String matriculaImovel = null;

					// Matricula Im�vel
					matriculaImovel = imovel.getId().toString();
					form.setMatriculaImovel(matriculaImovel);

					// Inscri��o Im�vel
					String inscricaoImovel = fachada
							.pesquisarInscricaoImovel(imovel.getId());
					form.setInscricaoImovel(inscricaoImovel);

					// Situa��o da Liga��o de Agua
					String situacaoLigacaoAgua = imovel
							.getLigacaoAguaSituacao().getDescricao();
					form.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situa��o da Liga��o de Esgoto
					String situacaoLigacaoEsgoto = imovel
							.getLigacaoEsgotoSituacao().getDescricao();
					form.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					// Cliente
					this.pesquisarCliente(form);

					FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();
					filtroMedicaoHistorico
							.setCampoOrderBy(FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO);

					HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;

					// Tipo medi��o - Liga��o �gua
					if (ordemServico.getRegistroAtendimento() == null ||
                        ordemServico.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getIndicadorLigacaoAgua().equals(ConstantesSistema.SIM)) {
						
						LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();
						hidrometroInstalacaoHistorico = ligacaoAgua
								.getHidrometroInstalacaoHistorico();
						form
								.setTipoMedicao(MedicaoTipo.LIGACAO_AGUA
										.toString());
						Integer numeroLeitura = fachada
								.pesquisarNumeroLeituraRetiradaLigacaoAgua(ligacaoAgua
										.getId());
						
						if (numeroLeitura != null) {
							form.setNumeroLeitura(numeroLeitura.toString());
						} else {
							form.setNumeroLeitura(null);
						}
						sessao.setAttribute("hidrometroSubstituicaoHistorico",
								ligacaoAgua.getHidrometroInstalacaoHistorico());


					} 
					// Tipo medi��o- Po�o					
					else if (ordemServico.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getIndicadorPoco().equals(ConstantesSistema.SIM)){
						
						hidrometroInstalacaoHistorico = imovel
								.getHidrometroInstalacaoHistorico();
						form.setTipoMedicao(MedicaoTipo.POCO.toString());
						Integer numeroLeitura = fachada
								.pesquisarNumeroLeituraRetiradaImovel(imovel
										.getId());
						
						if (numeroLeitura != null) {
							form.setNumeroLeitura(numeroLeitura.toString());
						} else {
							form.setNumeroLeitura(null);
						}
						
						sessao.setAttribute("hidrometroSubstituicaoHistorico", imovel.getHidrometroInstalacaoHistorico());
					}
					// Tipo Ligacao Esgoto 
				    else if (ordemServico.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getIndicadorLigacaoEsgoto().equals(ConstantesSistema.SIM)) {
						
				    	hidrometroInstalacaoHistorico = imovel.getHidrometroInstalacaoHistorico();
						form.setTipoMedicao(MedicaoTipo.LIGACAO_ESGOTO.toString());
						Integer numeroLeitura = fachada.pesquisarNumeroLeituraRetiradaImovel(imovel.getId());	
						
						if (numeroLeitura != null) {
							form.setNumeroLeitura(numeroLeitura.toString());
						} else {
							form.setNumeroLeitura(null);
						}
						
						sessao.setAttribute("hidrometroSubstituicaoHistorico", imovel.getHidrometroInstalacaoHistorico());
				    }
					
					validarFinalidadeHidrometro(form.getNumeroHidrometro(), form.getTipoMedicao());
					
					if (hidrometroInstalacaoHistorico.getMedicaoTipo().getId().equals(MedicaoTipo.LIGACAO_AGUA)) {
						form.setTipoMedicaoAtual(hidrometroInstalacaoHistorico.getMedicaoTipo().getDescricao());
					} else {
						if (hidrometroInstalacaoHistorico.getHidrometro().getIndicadorFinalidade().equals(new Short("1"))) {
							form.setTipoMedicaoAtual("POCO");
						} else {
							form.setTipoMedicaoAtual("LIGACAO DE ESGOTO");
						}
					}
					
					form.setNumeroHidrometroAtual(hidrometroInstalacaoHistorico.getHidrometro().getNumero());
					
					//Flag que verifica se a veio do reload do combo de situacao de hidrometro
					if(httpServletRequest.getParameter("comboSituacaoHidrometro") == null){
						if (hidrometroInstalacaoHistorico.getHidrometro()
								.getHidrometroSituacao() != null) {
							form
									.setSituacaoHidrometro(hidrometroInstalacaoHistorico
											.getHidrometro()
											.getHidrometroSituacao().getId()
											.toString());
	
						}
					}
					
					
					// Data recibida da execu��o da OS
					Date dataInstalacao = ordemServico.getDataEncerramento();
					if (dataInstalacao != null && !dataInstalacao.equals("")) {
						form.setDataInstalacao(Util
								.formatarData(dataInstalacao));
					}

					// Preencher dados da Gera��o
					// this.pesquisarDadosGeracao(efetuarSubstituicaoHidrometroActionForm,
					// ordemServico);
					// Tipo D�bito
					if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
						form.setIdTipoDebito(ordemServico.getServicoTipo()
								.getDebitoTipo().getId()
								+ "");
						form.setDescricaoTipoDebito(ordemServico
								.getServicoTipo().getDebitoTipo()
								.getDescricao()
								+ "");
					} else {
						form.setIdTipoDebito("");
						form.setDescricaoTipoDebito("");
					}

					

					SistemaParametro sistemaParametro = Fachada.getInstancia()
							.pesquisarParametrosDoSistema();
					form.setQtdeMaxParcelas(sistemaParametro
							.getNumeroMaximoParcelasFinanciamento()
							+ "");
					if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
						form.setMotivoNaoCobranca(ordemServico
								.getServicoNaoCobrancaMotivo().getId()
								.toString());
					}
					if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
						form.setPercentualCobranca(ordemServico
								.getPercentualCobranca().toString());
					}

				} else {
					httpServletRequest.setAttribute("ordemServicoEncontrado",
							"exception");
					form.setNomeOrdemServico("Ordem de Servi�o inexistente");
					form.setIdOrdemServico("");
				}
			} else {

				httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");

				form.setIdLocalArmazenagem("");
				form.setIdOrdemServico("");
				form.setMatriculaImovel("");
				form.setInscricaoImovel("");
				form.setClienteUsuario("");
				form.setCpfCnpjCliente("");
				form.setSituacaoLigacaoAgua("");
				form.setSituacaoLigacaoEsgoto("");
				form.setNomeOrdemServico("");
				form.setIdTipoDebito("");
				form.setDescricaoTipoDebito("");
				form.setQuantidadeParcelas("");
				form.setValorParcelas("");
				form.setPercentualCobranca("-1");
				form.setMotivoNaoCobranca("-1");
				form.setIdTipoDebito("");
				form.setDescricaoTipoDebito("");
				form.setValorDebito("");
			}

		}
		
//		 Parte em que � Verificado se o hidrometro esta extraviado.
		String idSituacaoHidrometro = (String) form.getHidrometroExtraviado();
		if(idSituacaoHidrometro == null || idSituacaoHidrometro.equals("") || 
				idSituacaoHidrometro.equalsIgnoreCase("nao") || idSituacaoHidrometro.equalsIgnoreCase("sim") ){
			idSituacaoHidrometro = form.getSituacaoHidrometro();
		}
				
				if(idSituacaoHidrometro!= null && !idSituacaoHidrometro.equals("")
						&& !idSituacaoHidrometro.equals("-1")){
					
					HidrometroSituacao hidrometroSituacao = new HidrometroSituacao(); 
					FiltroHidrometroSituacao filtroHidrometroSituacao = new FiltroHidrometroSituacao();
					
					filtroHidrometroSituacao.adicionarParametro(new ParametroSimples(FiltroHidrometroSituacao.ID,idSituacaoHidrometro));
					
					Collection colecaoHidrometroSituacao =  fachada.pesquisar(filtroHidrometroSituacao,
							HidrometroSituacao.class.getName());
					
					hidrometroSituacao = (HidrometroSituacao) colecaoHidrometroSituacao
					.iterator().next();
					
					Integer hidrometroExtraviado = null;
					if(hidrometroSituacao != null){
					hidrometroExtraviado = new Integer(hidrometroSituacao.getExtraviado().toString()); 
					}
					
					// hidrometroExtraviado==1, significa que o hidrometro esta extraviado,
					// como mostra o campo "hist_ichidrometroextraviado" da tabela "micromedicao.hidrometro_situacao".
					if(hidrometroExtraviado.intValue() == HidrometroSituacao.EXTRAVIADO.intValue()){
						httpServletRequest.setAttribute("hidrometroExtravido","sim");
						sessao.setAttribute("hidrometroExtravido","sim");
						form.setHidrometroExtraviado("sim");
					}
					else{
						sessao.removeAttribute("hidrometroExtravido");
						form.setHidrometroExtraviado("nao");
					}
				}
//				 Fim da Verificacao se o hidrometro esta extraviado.
				

		return retorno;
	}
	
	
	/*
	 * [FS0013 - Altera��o de Valor]
	 * 
	 * autor: Raphael Rossiter
	 * data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarSubstituicaoHidrometroActionForm form){
		
		if (servicoTipo.getIndicadorPermiteAlterarValor() == 
			ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			
			form.setAlteracaoValor("OK");
		}
		else{
			form.setAlteracaoValor("");
		}
		
	}
	
	
	/*
	 * Calcular valor da presta��o com juros
	 * 
	 * return: Retorna o valor total do d�bito
	 * 
	 * autor: Raphael Rossiter
	 * data: 04/05/2007
	 */
	private BigDecimal calcularValores(HttpServletRequest httpServletRequest, OrdemServico ordemServico,
			EfetuarSubstituicaoHidrometroActionForm form){
		
		String calculaValores = httpServletRequest.getParameter("calculaValores");
		
		BigDecimal valorDebito = new BigDecimal(0);
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		Integer qtdeParcelas = null;
		
		if(calculaValores != null && calculaValores.equals("S")){
			
			//[UC0186] - Calcular Presta��o
			BigDecimal  taxaJurosFinanciamento = null; 
			qtdeParcelas = new Integer(form.getQuantidadeParcelas());
			
			if(ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
					qtdeParcelas.intValue() > 1){
				
				taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
			}else{
				taxaJurosFinanciamento = new BigDecimal(0);
				qtdeParcelas = 1;
			}
			
			BigDecimal valorPrestacao = null;
			if(taxaJurosFinanciamento != null){
				
				valorDebito = new BigDecimal(form.getValorDebito().replace(",","."));
				
				String percentualCobranca = form.getPercentualCobranca();
				
				if(percentualCobranca.equals("70")){
					valorDebito = valorDebito.multiply(new BigDecimal(0.7));
				}else if (percentualCobranca.equals("50")){
					valorDebito = valorDebito.multiply(new BigDecimal(0.5));
				}
				
				valorPrestacao =
					this.getFachada().calcularPrestacao(
						taxaJurosFinanciamento,
						qtdeParcelas, 
						valorDebito, 
						new BigDecimal("0.00"));
				
				valorPrestacao.setScale(2,BigDecimal.ROUND_HALF_UP);
			}
			
			if (valorPrestacao != null) {
				String valorPrestacaoComVirgula = Util.formataBigDecimal(valorPrestacao,2,true);
				form.setValorParcelas(valorPrestacaoComVirgula);
			} else {
				form.setValorParcelas("0,00");
			}						
			
		}else if(ordemServico.getRegistroAtendimento() != null){
			
			valorDebito = Fachada.getInstancia().obterValorDebito(ordemServico.getServicoTipo().getId(),
			ordemServico.getRegistroAtendimento().getImovel().getId(),
			new Short(LigacaoTipo.LIGACAO_AGUA + ""));
			
			if (valorDebito != null) {
				form.setValorDebito(Util.formataBigDecimal(valorDebito, 2, true));
			} else {
				form.setValorDebito("0");
			}
		}
		
		
		return valorDebito;
	}
	

	/**
	 * Pesquisa o local de instalacao do hidrometro Pesquisa a protecao do
	 * hidrometro Pesquisa a situacao do hidrometro
	 */
	private void pesquisarObjetosObrigatorios(
			HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pesquisando local de instala��o
		FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = new FiltroHidrometroLocalInstalacao();

		filtroHidrometroLocalInstalacao
				.setCampoOrderBy(FiltroHidrometroLocalInstalacao.DESCRICAO);
		filtroHidrometroLocalInstalacao
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroLocalInstalacao.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoHidrometroLocalInstalacao = Fachada.getInstancia()
				.pesquisar(filtroHidrometroLocalInstalacao,
						HidrometroLocalInstalacao.class.getName());

		if (colecaoHidrometroLocalInstalacao == null
				|| colecaoHidrometroLocalInstalacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Hidr�metro local de instala��o");
		}

		httpServletRequest.setAttribute("colecaoHidrometroLocalInstalacao",
				colecaoHidrometroLocalInstalacao);

		// Pesquisando prote��o
		FiltroHidrometroProtecao filtroHidrometroProtecao = new FiltroHidrometroProtecao();

		filtroHidrometroProtecao
				.setCampoOrderBy(FiltroHidrometroProtecao.DESCRICAO);
		filtroHidrometroProtecao.adicionarParametro(new ParametroSimples(
				FiltroHidrometroProtecao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoHidrometroProtecao = Fachada.getInstancia()
				.pesquisar(filtroHidrometroProtecao,
						HidrometroProtecao.class.getName());

		if (colecaoHidrometroProtecao == null
				|| colecaoHidrometroProtecao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Hidr�metro prote��o");
		}

		httpServletRequest.setAttribute("colecaoHidrometroProtecao",
				colecaoHidrometroProtecao);

		// Pesquisando situa��o do hidr�metro
		FiltroHidrometroSituacao filtroHidrometroSituacao = new FiltroHidrometroSituacao();
		filtroHidrometroSituacao
				.setCampoOrderBy(FiltroHidrometroSituacao.DESCRICAO);

		Collection colecaoHidrometroSituacao = Fachada.getInstancia()
				.pesquisar(filtroHidrometroSituacao,
						HidrometroSituacao.class.getName());

		httpServletRequest.setAttribute("colecaoHidrometroSituacao",
				colecaoHidrometroSituacao);

		// Pesquisando hidrometro local armazenagem
		FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();

		filtroHidrometroLocalArmazenagem
				.setCampoOrderBy(FiltroHidrometroLocalArmazenagem.DESCRICAO);
		filtroHidrometroLocalArmazenagem
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroLocalArmazenagem.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoHidrometroLocalArmazenagem = Fachada.getInstancia()
				.pesquisar(filtroHidrometroLocalArmazenagem,
						HidrometroLocalArmazenagem.class.getName());

		if (colecaoHidrometroLocalArmazenagem == null
				|| colecaoHidrometroLocalArmazenagem.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Local de Armazenagem do Hidr�metro");
		}

		httpServletRequest.setAttribute("colecaoHidrometroLocalArmazenagem",
				colecaoHidrometroLocalArmazenagem);

		// Filtro para o campo Tpo Debito
		Collection colecaoNaoCobranca = (Collection) sessao
				.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo
					.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = fachada.pesquisar(
					filtroServicoNaoCobrancaMotivo,
					ServicoNaoCobrancaMotivo.class.getName());

			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca", colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo da N�o Cobran�a");
			}
		}

	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 25/08/2006
	 */
	private void pesquisarCliente(
			EfetuarSubstituicaoHidrometroActionForm efetuarSubstituicaoHidrometroActionForm) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID,
				efetuarSubstituicaoHidrometroActionForm.getMatriculaImovel()));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoClienteImovel = Fachada.getInstancia().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());

		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
					.iterator().next();

			Cliente cliente = clienteImovel.getCliente();

			String documento = "";

			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			// Cliente Nome/CPF-CNPJ
			efetuarSubstituicaoHidrometroActionForm.setClienteUsuario(cliente
					.getNome());
			efetuarSubstituicaoHidrometroActionForm
					.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}
	
	private void validarFinalidadeHidrometro(String numeroHidrometro, String tipoMedicao) {
	
		if (numeroHidrometro != null && !numeroHidrometro.trim().equals("") &&
			tipoMedicao != null && !tipoMedicao.trim().equals("")) {
			
			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
			filtroHidrometro.adicionarParametro(new ParametroSimples(
			FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));
					
			Collection<Hidrometro> colecaoHidrometro = Fachada.getInstancia().pesquisar(filtroHidrometro, Hidrometro.class.getName());

			if (colecaoHidrometro != null && !colecaoHidrometro.isEmpty()) {
				Hidrometro hidrometro = (Hidrometro) colecaoHidrometro.iterator().next();
		
				if ((tipoMedicao.equals(MedicaoTipo.LIGACAO_AGUA.toString()) || tipoMedicao.equals(MedicaoTipo.POCO.toString())) &&
					hidrometro.getIndicadorFinalidade() == 2) {
					throw new ActionServletException("atencao.nao_e_possivel_adicionar_esgoto_para_medir_agua");
				} else if (tipoMedicao.equals(MedicaoTipo.LIGACAO_ESGOTO.toString()) && hidrometro.getIndicadorFinalidade() == 1) {
					throw new ActionServletException("atencao.nao_e_possivel_adicionar_agua_para_medir_esgoto");
				}
			}
		}
		
	}
	
}
