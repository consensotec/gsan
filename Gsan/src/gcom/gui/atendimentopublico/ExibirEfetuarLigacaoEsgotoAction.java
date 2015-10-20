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
package gcom.gui.atendimentopublico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.FiltroLigacaoOrigem;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ligacaoagua.FiltroDiametroLigacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroDiametroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoMaterialEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.parametrosistema.FiltroParametroSistema;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

/**
 * Action respons�vel pela pre-exibi��o da pagina de inserir bairro
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class ExibirEfetuarLigacaoEsgotoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = 
			actionMapping.findForward("efetuarLigacaoEsgoto");

		EfetuarLigacaoEsgotoActionForm ligacaoEsgotoActionForm = (EfetuarLigacaoEsgotoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		this.consultaSelectObrigatorio(this.getSessao(httpServletRequest));
		
		String funcionalidade = (String) httpServletRequest.getParameter("funcionalidade");
		
		if (funcionalidade == null){
			funcionalidade = (String) this.getSessao(httpServletRequest).getAttribute("funcionalidade");
		} else {
			ligacaoEsgotoActionForm.limpar();
		}
		
		verificarPermissaoPercentualAlternativoEsgoto(this.getSessao(httpServletRequest));
		
		this.getSessao(httpServletRequest).setAttribute(
			"funcionalidade", funcionalidade);

		Boolean veioEncerrarOS = null;
		
		ligacaoEsgotoActionForm.setIndicadorCaixaGordura("2");
		ligacaoEsgotoActionForm.setIndicadorLigacao("1");
		
		if (funcionalidade != null && funcionalidade.equals("semRA")){
			
			httpServletRequest.getSession().removeAttribute("encerrarOS");
			
			String matriculaImovel = ligacaoEsgotoActionForm.getMatriculaImovel();

			if (matriculaImovel != null && !matriculaImovel.trim().equals("")) {

				String mensagemImovel = fachada.validarMatriculaImovel(new Integer(matriculaImovel));

				if (mensagemImovel == null) {
					
					Imovel imovel = fachada.pesquisarDadosImovel(Integer.parseInt(matriculaImovel));
					
					/*------------Inicio do codigo carregar * dados do Imov�l--------------------*/
					// Matricula Im�vel
					ligacaoEsgotoActionForm.setMatriculaImovel(matriculaImovel);

					// Inscri��o Im�vel
					String inscricaoImovel = fachada.pesquisarInscricaoImovel(new Integer(matriculaImovel));

					ligacaoEsgotoActionForm.setInscricaoImovel(inscricaoImovel);

					// Situa��o da Liga��o de Agua
					LigacaoAguaSituacao ligacaoAguaSituacao = fachada.pesquisarLigacaoAguaSituacao(new Integer(matriculaImovel));
					String situacaoLigacaoAgua = "";
					if (ligacaoAguaSituacao != null) {
						situacaoLigacaoAgua = ligacaoAguaSituacao.getDescricao();
					}

					ligacaoEsgotoActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situa��o da Liga��o de Esgoto
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = fachada.pesquisarLigacaoEsgotoSituacao(new Integer(matriculaImovel));
					String situacaoLigacaoEsgoto = "";
					if (ligacaoEsgotoSituacao != null) {
						situacaoLigacaoEsgoto = ligacaoEsgotoSituacao.getDescricao();
						if ((!ligacaoEsgotoSituacao.getId().equals(LigacaoEsgotoSituacao.POTENCIAL)) 
								&& (!ligacaoEsgotoSituacao.getId().equals(LigacaoEsgotoSituacao.FACTIVEL))
								&& (!ligacaoEsgotoSituacao.getId().equals(LigacaoEsgotoSituacao.EM_FISCALIZACAO))) {
							 //[FS0002] - Validar Situa��o de Liga��o de Esgoto do Im�vel
							throw new ActionServletException("atencao.situacao_validar_ligacao_esgoto_invalida_exibir",	null, matriculaImovel);
						}
					}
					ligacaoEsgotoActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					this.pesquisarCliente(ligacaoEsgotoActionForm,	new Integer(matriculaImovel));
					
					// [FS0017] Verificar N�vel para instala��o de esgoto
					if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER) && 
							imovel.getIndicadorNivelInstalacaoEsgoto() != null && imovel.getIndicadorNivelInstalacaoEsgoto().equals(ConstantesSistema.NAO)){
						throw new ActionServletException("atencao.imovel_sem_nivel_instalacao_esgoto", null, matriculaImovel);
					}
					
					// -----------------------------------------------------------
					// Verificar permiss�o especial
					
					this.validarPermissaoEspecial(ligacaoEsgotoActionForm,null,httpServletRequest,false);

				} else {
					ligacaoEsgotoActionForm.setInscricaoImovel(mensagemImovel);
					ligacaoEsgotoActionForm.setMatriculaImovel("");
					httpServletRequest.setAttribute("matriculaImovelInexistente",true);
				}

			} else {

				httpServletRequest.setAttribute("nomeCampo", "matriculaImovel");

				ligacaoEsgotoActionForm.setMatriculaImovel("");
				ligacaoEsgotoActionForm.setInscricaoImovel("");
				ligacaoEsgotoActionForm.setClienteUsuario("");
				ligacaoEsgotoActionForm.setCpfCnpjCliente("");
				ligacaoEsgotoActionForm.setSituacaoLigacaoAgua("");
				ligacaoEsgotoActionForm.setSituacaoLigacaoEsgoto("");
				ligacaoEsgotoActionForm.setIdTipoDebito("");
				ligacaoEsgotoActionForm.setDescricaoTipoDebito("");
				ligacaoEsgotoActionForm.setQuantidadeParcelas("");
				ligacaoEsgotoActionForm.setValorParcelas("");
				ligacaoEsgotoActionForm.setPercentualCobranca("-1");
				ligacaoEsgotoActionForm.setMotivoNaoCobranca("-1");
			}
			
		} else if (funcionalidade == null || funcionalidade.equals("comRA")){

			if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
				
				httpServletRequest.getSession().setAttribute("encerrarOS", true);
				
				veioEncerrarOS = Boolean.TRUE;
			} else {
				if (ligacaoEsgotoActionForm.getVeioEncerrarOS() != null && 
					ligacaoEsgotoActionForm.getVeioEncerrarOS().equalsIgnoreCase("TRUE")) {
					veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = Boolean.FALSE;
				}
			}
	
			// Permissao Especial Efetuar Ligacao de Esgoto sem RA
			boolean efetuarLigacaoEsgotoSemRA = 
				this.getFachada().verificarPermissaoEspecial(
					PermissaoEspecial.EFETUAR_LIGACAO_DE_ESGOTO_SEM_RA,
						this.getUsuarioLogado(httpServletRequest));
	
			ligacaoEsgotoActionForm.setPermissaoAlterarLigacaoEsgotosemRA("false");
	
			if (!veioEncerrarOS) {
	
				httpServletRequest.setAttribute("efetuarLigacaoEsgotoSemRA",efetuarLigacaoEsgotoSemRA);
	
				if (efetuarLigacaoEsgotoSemRA) {
					ligacaoEsgotoActionForm.setPermissaoAlterarLigacaoEsgotosemRA("true");
				}
			}
	
			String idImovel = ligacaoEsgotoActionForm.getIdImovel();
	
			if (idImovel != null && !idImovel.trim().equals("")) {
				
				// Pesquisa o imovel na base
				String inscricaoImovelEncontrado = this.getFachada().pesquisarInscricaoImovel(new Integer(idImovel));
				
				if (inscricaoImovelEncontrado != null && !inscricaoImovelEncontrado.equalsIgnoreCase("")) {
	
					ligacaoEsgotoActionForm.setMatriculaImovel(idImovel);
					ligacaoEsgotoActionForm.setInscricaoImovel(inscricaoImovelEncontrado);
	
					Imovel imovel = (Imovel) this.getFachada().pesquisarDadosImovel(new Integer(idImovel));
	
					// [FS0002] Validar Situa��o de �gua do Im�vel.
					if (imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.POTENCIAL.intValue() && 
						imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.FACTIVEL.intValue() && 
						imovel.getLigacaoEsgotoSituacao().getId().intValue() != LigacaoEsgotoSituacao.EM_FISCALIZACAO.intValue()) {
	
						throw new ActionServletException(
							"atencao.situacao_validar_ligacao_esgoto_invalida_exibir",
							null, 
							imovel.getLigacaoAguaSituacao().getDescricao());
					}
	
					/*
					 * [FS0008] Verificar Situa��o Rede de �gua na Quadra
					 * 
					 * Integra��o com o conceito de face da quadra
					 * Raphael Rossiter em 21/05/2009
					 */
					IntegracaoQuadraFaceHelper integracao = fachada.integracaoQuadraFace(imovel.getId());
					
					if ((integracao.getIndicadorRedeEsgoto()).equals(Quadra.SEM_REDE)) {
						
						throw new ActionServletException("atencao.percentual_rede_esgoto_quadra", 
						null, imovel.getId() + "");
					}
	
					// [FS0006] Verificar Situa��o do Imovel
					if (imovel.getIndicadorExclusao() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
						
						throw new ActionServletException(
							"situacao_imovel_indicador_exclusao_esgoto", 
							null,
							imovel.getId() + "");
					}
					
					// [FS0017] Verificar N�vel para instala��o de esgoto
					if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER) && 
							imovel.getIndicadorNivelInstalacaoEsgoto() != null && imovel.getIndicadorNivelInstalacaoEsgoto().equals(ConstantesSistema.NAO)){
						throw new ActionServletException("atencao.imovel_associado_os_sem_nivel_instalacao_esgoto", null, idImovel);
					}
	
					// Matricula Im�vel
					ligacaoEsgotoActionForm.setMatriculaImovel(imovel.getId().toString());
	
					// Situa��o da Liga��o de Agua
					String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
					ligacaoEsgotoActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);
	
					// Situa��o da Liga��o de Esgoto
					String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
					ligacaoEsgotoActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);
	
					this.pesquisarCliente(ligacaoEsgotoActionForm, imovel.getId());
	
					if (ligacaoEsgotoActionForm.getPerfilLigacao() != null && 
						!ligacaoEsgotoActionForm.getPerfilLigacao().equals("-1")) {
	
						FiltroLigacaoEsgotoPerfil filtroLigacaoPercentualEsgoto = new FiltroLigacaoEsgotoPerfil();
						filtroLigacaoPercentualEsgoto.adicionarParametro(
							new ParametroSimples(
								FiltroLigacaoEsgotoPerfil.ID,
								ligacaoEsgotoActionForm.getPerfilLigacao()));
	
						Collection colecaoPercentualEsgoto = 
							this.getFachada().pesquisar(
								filtroLigacaoPercentualEsgoto,
								LigacaoEsgotoPerfil.class.getName());
	
						if (colecaoPercentualEsgoto != null && 
							!colecaoPercentualEsgoto.isEmpty()) {
	
							LigacaoEsgotoPerfil percentualEsgotoPerfil = 
								(LigacaoEsgotoPerfil) colecaoPercentualEsgoto.iterator().next();
	
							String percentualFormatado = 
								percentualEsgotoPerfil.getPercentualEsgotoConsumidaColetada().toString().replace(".", ",");
	
							ligacaoEsgotoActionForm.setPercentualEsgoto(percentualFormatado);
						}
					}
					
					this.validarPermissaoEspecial(
							ligacaoEsgotoActionForm,null,httpServletRequest,false);
	
				} else {
	
					httpServletRequest.setAttribute("corImovel", "exception");
					ligacaoEsgotoActionForm.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
				}
	
			}
			
			ligacaoEsgotoActionForm.setIndicadorCaixaGordura("2");
			ligacaoEsgotoActionForm.setVeioEncerrarOS("" + veioEncerrarOS);
			ligacaoEsgotoActionForm.setIndicadorLigacao("1");
	
			// Variavel responsav�l pelo preenchimento do imovel no formul�rio
			String idOrdemServico = null;
			if (ligacaoEsgotoActionForm.getIdOrdemServico() != null) {
				idOrdemServico = ligacaoEsgotoActionForm.getIdOrdemServico();
			} else {
				
				idOrdemServico = (String) httpServletRequest.getAttribute("veioEncerrarOS");
				ligacaoEsgotoActionForm.setDataLigacao((String) httpServletRequest.getAttribute("dataEncerramento"));
				
				this.getSessao(httpServletRequest).setAttribute("caminhoRetornoIntegracaoComercial",
					httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
			}
	
			if (httpServletRequest.getAttribute("semMenu") != null) {
				this.getSessao(httpServletRequest).setAttribute("semMenu", "SIM");
			} else {
				this.getSessao(httpServletRequest).removeAttribute("semMenu");
			}
	
			if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
	
				FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
				filtroOrdemServico.adicionarParametro(
					new ParametroSimples(
					FiltroOrdemServico.ID, 
					idOrdemServico));
	
				OrdemServico ordemServico = 
					this.getFachada().recuperaOSPorId(new Integer(idOrdemServico));
	
				if (ordemServico != null) {
	
					this.getFachada().validarLigacaoEsgotoExibir(ordemServico,veioEncerrarOS);
					this.getSessao(httpServletRequest).setAttribute("ordemServico", ordemServico);
	
					ligacaoEsgotoActionForm.setIdOrdemServico(idOrdemServico);
					ligacaoEsgotoActionForm.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
	
					Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
					this.getSessao(httpServletRequest).setAttribute("imovel", imovel);
	
					String matriculaImovel = imovel.getId().toString();
	
					if (imovel != null) {
	
						// Matricula Im�vel
						ligacaoEsgotoActionForm.setMatriculaImovel(imovel.getId().toString());
	
						// Inscri��o Im�vel
						String inscricaoImovel = 
							this.getFachada().pesquisarInscricaoImovel(imovel.getId());
	
						ligacaoEsgotoActionForm.setInscricaoImovel(inscricaoImovel);
	
						// Situa��o da Liga��o de Agua
						String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
						ligacaoEsgotoActionForm.setSituacaoLigacaoAgua(situacaoLigacaoAgua);
	
						// Situa��o da Liga��o de Esgoto
						String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
						ligacaoEsgotoActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);
	
						this.pesquisarCliente(ligacaoEsgotoActionForm, new Integer(matriculaImovel));
						
						this.validarPermissaoEspecial(
							ligacaoEsgotoActionForm,null,httpServletRequest,false);
					}
	
					if (ordemServico.getServicoTipo() != null && 
						ordemServico.getServicoTipo().getDebitoTipo() != null) {
						
						ligacaoEsgotoActionForm.setIdTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getId().toString());
						ligacaoEsgotoActionForm.setDescricaoTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getDescricao());
					} else {
						ligacaoEsgotoActionForm.setIdTipoDebito("");
						ligacaoEsgotoActionForm.setDescricaoTipoDebito("");
					}
	
					// [FS0013] - Altera��o de Valor
					this.permitirAlteracaoValor(ordemServico.getServicoTipo(),ligacaoEsgotoActionForm);
					String calculaValores = httpServletRequest.getParameter("calculaValores");
	
					Integer qtdeParcelas = null;
					BigDecimal valorDebito = new BigDecimal(0);
	
					if (calculaValores != null && calculaValores.equals("S")) {
	
						// [UC0186] - Calcular Presta��o
						BigDecimal taxaJurosFinanciamento = null;
						qtdeParcelas = new Integer(ligacaoEsgotoActionForm.getQuantidadeParcelas());
	
						if (ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
							qtdeParcelas.intValue() != 1) {
	
							taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
						} else {
							taxaJurosFinanciamento = new BigDecimal(0);
						}
	
						BigDecimal valorPrestacao = null;
						if (taxaJurosFinanciamento != null) {
	
							valorDebito = 
								new BigDecimal(ligacaoEsgotoActionForm.getValorDebito().replace(",", "."));
	
							String percentualCobranca = ligacaoEsgotoActionForm.getPercentualCobranca();
	
							if (percentualCobranca.equals("70")) {
								valorDebito = valorDebito.multiply(new BigDecimal(0.7));
							} else if (percentualCobranca.equals("50")) {
								valorDebito = valorDebito.multiply(new BigDecimal(0.5));
							}
	
							valorPrestacao = 
								this.getFachada().calcularPrestacao(
									taxaJurosFinanciamento, 
									qtdeParcelas,
									valorDebito, 
									new BigDecimal("0.00"));
	
							valorPrestacao.setScale(2, BigDecimal.ROUND_HALF_UP);
						}
	
						if (valorPrestacao != null) {
							String valorPrestacaoComVirgula = 
								Util.formataBigDecimal(valorPrestacao, 2, true);
							
							ligacaoEsgotoActionForm.setValorParcelas(valorPrestacaoComVirgula);
						} else {
							ligacaoEsgotoActionForm.setValorParcelas("0,00");
						}
	
					} else {
	
						valorDebito = 
							this.getFachada().obterValorDebito(
								ordemServico.getServicoTipo().getId(), 
								imovel.getId(),
								new Short(LigacaoTipo.LIGACAO_AGUA + ""));
						
						ligacaoEsgotoActionForm.setValorDebito(Util.formataBigDecimal(valorDebito, 2, true));
					}
	
					if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
						ligacaoEsgotoActionForm.setMotivoNaoCobranca(
							ordemServico.getServicoNaoCobrancaMotivo().getId().toString());
					}
	
					if (ordemServico.getPercentualCobranca() != null) {
						ligacaoEsgotoActionForm.setPercentualCobranca(
							ordemServico.getPercentualCobranca().toString());
					}
	
					if (ordemServico.getDataEncerramento() != null) {
						ligacaoEsgotoActionForm.setDataLigacao(
							Util.formatarData(ordemServico.getDataEncerramento()));
					}
	
					// Inscri��o do Imov�l
					String inscricaoImovel = imovel.getInscricaoFormatada();
	
					ligacaoEsgotoActionForm.setMatriculaImovel(matriculaImovel);
					ligacaoEsgotoActionForm.setInscricaoImovel(inscricaoImovel);
	
					// Situa��o da Liga��o de Agua
					String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao()
							.getDescricao();
					ligacaoEsgotoActionForm
							.setSituacaoLigacaoAgua(situacaoLigacaoAgua);
	
					this.getSessao(httpServletRequest).setAttribute(
						"ligacaoAguaSituacao", imovel.getLigacaoAguaSituacao());
	
					// Situa��o da Liga��o de Esgoto
					String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
					ligacaoEsgotoActionForm.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);
	
					this.pesquisarCliente(ligacaoEsgotoActionForm, new Integer(matriculaImovel));
	
					this.validarPermissaoEspecial(ligacaoEsgotoActionForm,valorDebito,httpServletRequest,true);
	
				} else {
					ligacaoEsgotoActionForm.setNomeOrdemServico("Ordem de Servi�o inexistente");
					ligacaoEsgotoActionForm.setIdOrdemServico("");
					httpServletRequest.setAttribute("OrdemServioInexistente", true);
				}
	
			}
		
		}
		
		/*-------------------- Dados da Liga��o ----------------------------*/

		this.consultaSelectObrigatorio(this.getSessao(httpServletRequest));

		// Carregando campo Percentual de Esgoto
		// Item 4.6
		if (ligacaoEsgotoActionForm.getPerfilLigacao() != null && 
			!ligacaoEsgotoActionForm.getPerfilLigacao().equals("")) {

			FiltroLigacaoEsgotoPerfil filtroLigacaoPercentualEsgoto = new FiltroLigacaoEsgotoPerfil();

			filtroLigacaoPercentualEsgoto.adicionarParametro(
				new ParametroSimples(
					FiltroLigacaoEsgotoPerfil.ID,
					ligacaoEsgotoActionForm.getPerfilLigacao()));

			Collection colecaoPercentualEsgoto = 
				this.getFachada().pesquisar(
					filtroLigacaoPercentualEsgoto,
					LigacaoEsgotoPerfil.class.getName());

			if (colecaoPercentualEsgoto != null && 
				!colecaoPercentualEsgoto.isEmpty()) {

				LigacaoEsgotoPerfil percentualEsgotoPerfil = 
					(LigacaoEsgotoPerfil) colecaoPercentualEsgoto.iterator().next();

				String percentualFormatado = 
					percentualEsgotoPerfil.getPercentualEsgotoConsumidaColetada().toString().replace(".", ",");

				ligacaoEsgotoActionForm.setPercentualEsgoto(percentualFormatado);
			}
		}
		
		return retorno;
	}
	
	/**
	 * 
	 * M�todo respons�vel por<br>
	 * verificar a permiss�o<br>
	 * percentual alternativo de<br>
	 * esgoto
	 * 
	 * @author Jonathan Marcos
	 * @since 19/06/2015
	 * 
	 * @param httpSession
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void verificarPermissaoPercentualAlternativoEsgoto(HttpSession httpSession){
		
		FiltroParametroSistema filtroParametroSistema = new FiltroParametroSistema();
		
		filtroParametroSistema.adicionarParametro(new ParametroSimples(FiltroParametroSistema.CODIGO_CONSTANTE, 
				ParametroSistema.INDICADOR_PERCENTUAL_ALTERNATIVO_ESGOTO));
		filtroParametroSistema.adicionarParametro(new ParametroSimples(FiltroParametroSistema.INDICADOR_USO, ConstantesSistema.SIM));
		
		ParametroSistema parametroSistema = (ParametroSistema) Util.retonarObjetoDeColecao(
				getFachada().pesquisar(filtroParametroSistema, ParametroSistema.class.getName()));
		
		if(parametroSistema!=null && parametroSistema.getValorParametro().equals("1"))
		{
			httpSession.setAttribute("indicadorPercentualAlternativoEsgoto", true);
		}
		else
		{
			httpSession.removeAttribute("indicadorPercentualAlternativoEsgoto");
		}
	}
	
	/*
	 * Validar os campos apatir da permiss�o especial
	 * 
	 * autor: Rafael Pinto
	 */
	private void validarPermissaoEspecial(EfetuarLigacaoEsgotoActionForm form,
		BigDecimal valorDebito,HttpServletRequest httpServletRequest,boolean validaMotivoNaoCobranca){

		boolean alterarPercentualColetaEsgoto = 
			this.getFachada().verificarPermissaoEspecial(
				PermissaoEspecial.ALTERAR_PERCENTUAL_COLETA_ESGOTO,
				this.getUsuarioLogado(httpServletRequest));

		if (alterarPercentualColetaEsgoto) {
			httpServletRequest.setAttribute("alterarPercentualColetaEsgoto",alterarPercentualColetaEsgoto);
		} 
			
		if(!alterarPercentualColetaEsgoto || form.getPercentualColeta() == null || form.getPercentualColeta().equals("") ){
			form.setPercentualColeta("100,00");
		}
		
		if(validaMotivoNaoCobranca){
			boolean temPermissaoMotivoNaoCobranca = 
				this.getFachada().verificarPermissaoInformarMotivoNaoCobranca(
					this.getUsuarioLogado(httpServletRequest));

			if (temPermissaoMotivoNaoCobranca) {
				httpServletRequest.setAttribute(
					"permissaoMotivoNaoCobranca",temPermissaoMotivoNaoCobranca);
			} else {
				form.setPercentualCobranca("100");
				form.setQuantidadeParcelas("1");
				form.setValorParcelas(
					Util.formataBigDecimal(valorDebito, 2, true));
			}

			if (temPermissaoMotivoNaoCobranca) {
				form.setPermissaoMotivoNaoCobranca("true");
			}
		}

	}
	

	/*
	 * [FS0013 - Altera��o de Valor]
	 * 
	 * autor: Raphael Rossiter data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo,
			EfetuarLigacaoEsgotoActionForm form) {

		if (servicoTipo.getIndicadorPermiteAlterarValor() == ConstantesSistema.INDICADOR_USO_ATIVO
				.shortValue()) {

			form.setAlteracaoValor("OK");
		} else {
			form.setAlteracaoValor("");
		}

	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 */
	private void pesquisarCliente(
			EfetuarLigacaoEsgotoActionForm ligacaoEsgotoActionForm,
			Integer matriculaImovel) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, matriculaImovel));

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
			ligacaoEsgotoActionForm.setClienteUsuario(cliente.getNome());
			ligacaoEsgotoActionForm.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}

	/**
	 * Monta os select�s obrigatorios
	 * 
	 * @author Rafael Pinto
	 * @date 22/08/2006
	 */
	private void consultaSelectObrigatorio(HttpSession sessao) {


		// Filtro para o campo Diametro Liga��o �gua
		Collection colecaoDiametroLigacao = (Collection) sessao
				.getAttribute("colecaoDiametroLigacaoAgua");

		if (colecaoDiametroLigacao == null) {

			FiltroDiametroLigacaoEsgoto filtroDiametroLigacaoEsgoto = new FiltroDiametroLigacaoEsgoto();

			filtroDiametroLigacaoEsgoto
					.adicionarParametro(new ParametroSimples(
							FiltroDiametroLigacao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDiametroLigacaoEsgoto
					.setCampoOrderBy(FiltroDiametroLigacao.DESCRICAO);

			colecaoDiametroLigacao = this.getFachada().pesquisar(
					filtroDiametroLigacaoEsgoto, LigacaoEsgotoDiametro.class
							.getName());

			if (colecaoDiametroLigacao != null
					&& !colecaoDiametroLigacao.isEmpty()) {
				sessao.setAttribute("colecaoDiametroLigacao",
						colecaoDiametroLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Diametro da Liga��o");
			}
		}

		// Filtro para o campo Material da Liga��o
		Collection colecaoMaterialLigacao = (Collection) sessao
				.getAttribute("colecaoMaterialLigacao");

		if (colecaoMaterialLigacao == null) {

			FiltroLigacaoMaterialEsgoto filtroLigacaoMaterialEsgoto = new FiltroLigacaoMaterialEsgoto();
			filtroLigacaoMaterialEsgoto
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoMaterialEsgoto.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoMaterialEsgoto
					.setCampoOrderBy(FiltroLigacaoMaterialEsgoto.DESCRICAO);

			colecaoMaterialLigacao = this.getFachada().pesquisar(
					filtroLigacaoMaterialEsgoto, LigacaoEsgotoMaterial.class
							.getName());

			if (colecaoMaterialLigacao != null
					&& !colecaoMaterialLigacao.isEmpty()) {
				sessao.setAttribute("colecaoMaterialLigacao",
						colecaoMaterialLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Material da Liga��o");
			}
		}

		// Filtro para o campo Perfil da Liga��o
		Collection colecaoPerfilLigacao = (Collection) sessao
				.getAttribute("colecaoPerfilLigacao");

		if (colecaoPerfilLigacao == null) {

			FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();
			filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoPerfil.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoEsgotoPerfil
					.setCampoOrderBy(FiltroLigacaoEsgotoPerfil.DESCRICAO);

			colecaoPerfilLigacao = this.getFachada().pesquisar(filtroLigacaoEsgotoPerfil,
					LigacaoEsgotoPerfil.class.getName());

			if (colecaoPerfilLigacao != null && !colecaoPerfilLigacao.isEmpty()) {
				sessao.setAttribute("colecaoPerfilLigacao",
						colecaoPerfilLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Perfil de Liga��o");
			}
		}

		// Filtro para o campo Motivo nao cobranca
		Collection colecaoNaoCobranca = (Collection) sessao
				.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo
					.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = this.getFachada().pesquisar(
					filtroServicoNaoCobrancaMotivo,
					ServicoNaoCobrancaMotivo.class.getName());

			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca", colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo da N�o Cobran�a");
			}
		}

		// Filtro para o campo liga��o esgotamento
		Collection colecaoLigacaoEsgotoEsgotamento = (Collection) sessao
				.getAttribute("colecaoLigacaoEsgotoEsgotamento");

		if (colecaoLigacaoEsgotoEsgotamento == null) {

			FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = new FiltroLigacaoEsgotoEsgotamento();
			filtroLigacaoEsgotoEsgotamento
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoEsgotoEsgotamento.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoEsgotoEsgotamento
					.setCampoOrderBy(FiltroLigacaoEsgotoEsgotamento.DESCRICAO);

			colecaoLigacaoEsgotoEsgotamento = this.getFachada().pesquisar(
					filtroLigacaoEsgotoEsgotamento,
					LigacaoEsgotoEsgotamento.class.getName());

			if (colecaoLigacaoEsgotoEsgotamento != null
					&& !colecaoLigacaoEsgotoEsgotamento.isEmpty()) {
				sessao.setAttribute("colecaoLigacaoEsgotoEsgotamento",
						colecaoLigacaoEsgotoEsgotamento);
			}
		}

		// Filtro para o campo destino dos dejetos
		Collection colecaoDestinoDejetos = (Collection) sessao
				.getAttribute("colecaoDestinoDejetos");

		if (colecaoDestinoDejetos == null) {

			FiltroLigacaoEsgotoDestinoDejetos filtroDestinoDejetos = new FiltroLigacaoEsgotoDestinoDejetos();
			filtroDestinoDejetos.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoDestinoDejetos.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDestinoDejetos
					.setCampoOrderBy(FiltroLigacaoEsgotoDestinoDejetos.DESCRICAO);

			colecaoDestinoDejetos = this.getFachada().pesquisar(filtroDestinoDejetos,
					LigacaoEsgotoDestinoDejetos.class.getName());

			if (colecaoDestinoDejetos != null
					&& !colecaoDestinoDejetos.isEmpty()) {
				sessao.setAttribute("colecaoDestinoDejetos",
						colecaoDestinoDejetos);
			}
		}

		// Filtro para o campo caixa de inspe��o
		Collection colecaoSituacaoCaixaInspecao = (Collection) sessao
				.getAttribute("colecaoSituacaoCaixaInspecao");

		if (colecaoSituacaoCaixaInspecao == null) {

			FiltroLigacaoEsgotoCaixaInspecao filtroSituacaoCaixaInspecao = new FiltroLigacaoEsgotoCaixaInspecao();
			filtroSituacaoCaixaInspecao
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoEsgotoCaixaInspecao.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroSituacaoCaixaInspecao
					.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);

			colecaoSituacaoCaixaInspecao = this.getFachada().pesquisar(
					filtroSituacaoCaixaInspecao,
					LigacaoEsgotoCaixaInspecao.class.getName());

			if (colecaoSituacaoCaixaInspecao != null
					&& !colecaoSituacaoCaixaInspecao.isEmpty()) {
				sessao.setAttribute("colecaoSituacaoCaixaInspecao",
						colecaoSituacaoCaixaInspecao);
			}
		}

		// Filtro para o campo destino caixas pluviais
		Collection colecaoDestinoAguasPluviais = (Collection) sessao
				.getAttribute("colecaoDestinoAguasPluviais");

		if (colecaoDestinoAguasPluviais == null) {

			FiltroLigacaoEsgotoDestinoAguasPluviais filtroDestinoAguasPluviais = new FiltroLigacaoEsgotoDestinoAguasPluviais();
			filtroDestinoAguasPluviais.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoDestinoAguasPluviais.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDestinoAguasPluviais
					.setCampoOrderBy(FiltroLigacaoEsgotoDestinoAguasPluviais.DESCRICAO);

			colecaoDestinoAguasPluviais = this.getFachada().pesquisar(
					filtroDestinoAguasPluviais,
					LigacaoEsgotoDestinoAguasPluviais.class.getName());

			if (colecaoDestinoAguasPluviais != null
					&& !colecaoDestinoAguasPluviais.isEmpty()) {
				sessao.setAttribute("colecaoDestinoAguasPluviais",
						colecaoDestinoAguasPluviais);
			}
		}

		// Filtro para o campo Ligacao origem
		Collection colecaoLigacaoOrigem = (Collection) sessao
				.getAttribute("colecaoLigacaoOrigem");

		if (colecaoLigacaoOrigem == null) {

			FiltroLigacaoOrigem filtroLigacaoOrigem = new FiltroLigacaoOrigem();

			filtroLigacaoOrigem.adicionarParametro(new ParametroSimples(
					FiltroLigacaoOrigem.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoOrigem.setCampoOrderBy(FiltroLigacaoOrigem.DESCRICAO);

			colecaoLigacaoOrigem = this.getFachada().pesquisar(filtroLigacaoOrigem,
					LigacaoOrigem.class.getName());

			if (colecaoLigacaoOrigem != null && !colecaoLigacaoOrigem.isEmpty()) {
				sessao.setAttribute("colecaoLigacaoOrigem",
						colecaoLigacaoOrigem);
			} else {
				sessao.setAttribute("colecaoLigacaoOrigem", new ArrayList());
			}
		}

	}
}