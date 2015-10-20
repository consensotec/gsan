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
package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroCorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o da pagina de efetuar corte de liga��o de �gua
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 * 
 * Refeito
 * @author Leonardo Regis
 * @date 23/09/2006
 */
public class ExibirEfetuarCorteLigacaoAguaAction extends GcomAction {
	
	/**
	 * [UC0355] Efetuar Corte de Liga��o de �gua
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("efetuarCorteLigacaoAgua");
		HttpSession sessao = httpServletRequest.getSession(false);
		EfetuarCorteLigacaoAguaActionForm form = (EfetuarCorteLigacaoAguaActionForm) actionForm;
				
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Veio de Encerrar OS
		Boolean veioEncerrarOS = null;
		if(httpServletRequest.getAttribute("veioEncerrarOS") != null){
			veioEncerrarOS = Boolean.TRUE;
		}else{
			if (form.getVeioEncerrarOS() != null && 
				!form.getVeioEncerrarOS().equals("")) {
				
				if (form.getVeioEncerrarOS().toLowerCase().equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}
		
		// Seta Cole��es
		getMotivoCorteCollection(sessao);
		getTipoCorteCollection(sessao);
		getMotivoNaoCobrancaCollection(sessao);
		
		String idOrdemServico = null;
		if(form.getIdOrdemServico() != null){
			idOrdemServico = form.getIdOrdemServico();
		}else{
			idOrdemServico = (String)httpServletRequest.getAttribute("veioEncerrarOS");
			form.setDataCorte((String) httpServletRequest.getAttribute("dataEncerramento"));
			sessao.setAttribute("caminhoRetornoIntegracaoComercial",httpServletRequest.getAttribute("caminhoRetornoIntegracaoComercial"));
		}
		
		if(httpServletRequest.getAttribute("semMenu") != null){
			sessao.setAttribute("semMenu", "SIM");
		}else{
			sessao.removeAttribute("semMenu");
		}
		
		// Testa OS
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
			OrdemServico ordemServico = this.getFachada().recuperaOSPorId(new Integer(idOrdemServico));
			if (ordemServico != null) {
				
				sessao.setAttribute("ordemServico", ordemServico);
				
				// Valida Exibi��o de Corte de Liga��o de �gua
				this.getFachada().validarExibirCorteLigacaoAgua(ordemServico,veioEncerrarOS);
				form.setVeioEncerrarOS(""+veioEncerrarOS);
				
				// OS
				form.setIdOrdemServico(ordemServico.getId()+"");
				form.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
				
				// Preencher dados do imovel
				this.preencherDadosImovel(form, ordemServico);
				
				// Preencher dados do Corte da Liga��o
				this.pesquisarDadosCorteLigacao(sessao, form, ordemServico);
				
				// Preencher dados da Gera��o
				// Tipo D�bito
				if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
					form.setIdTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getId()+"");
					form.setDescricaoTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getDescricao()+"");
				}else{
					form.setIdTipoDebito("");
					form.setDescricaoTipoDebito("");			
				}
				
				
				//[FS0013] - Altera��o de Valor
				this.permitirAlteracaoValor(ordemServico.getServicoTipo(), form);
				
				String calculaValores = httpServletRequest.getParameter("calculaValores");
				
				BigDecimal valorDebito = new BigDecimal(0);
				SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
				Integer qtdeParcelas = null;
				
				if(calculaValores != null && calculaValores.equals("S")){
					
					//[UC0186] - Calcular Presta��o
					BigDecimal  taxaJurosFinanciamento = null; 
					qtdeParcelas = new Integer(form.getQuantidadeParcelas());
					
					if(ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
						qtdeParcelas.intValue() != 1){
						
						taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
					}else{
						taxaJurosFinanciamento = new BigDecimal(0);
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
					
				}else{
				
					valorDebito = 
						this.getFachada().obterValorDebito(ordemServico.getServicoTipo().getId(), 
							ordemServico.getImovel().getId(), 
							new Short(LigacaoTipo.LIGACAO_AGUA+""));
					
					if (valorDebito != null) {
						String valorDebitoComVirgula = valorDebito+"";
						form.setValorDebito(valorDebitoComVirgula.replace(".",","));
					} else {
						form.setValorDebito("0,00");
					}
				}
				
				form.setQtdeMaxParcelas(sistemaParametro.getNumeroMaximoParcelasFinanciamento()+"");
				if(ordemServico.getServicoNaoCobrancaMotivo() != null){
					form.setMotivoNaoCobranca(ordemServico.getServicoNaoCobrancaMotivo().getId().toString());
				}
				if(ordemServico.getServicoNaoCobrancaMotivo() != null){
					form.setPercentualCobranca(ordemServico.getPercentualCobranca().toString());
				}
				// Verificar permiss�o especial
				boolean temPermissaoMotivoNaoCobranca = 
					this.getFachada().verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
				
				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
				}else{
					form.setPercentualCobranca("100");
					form.setQuantidadeParcelas("1");
					form.setValorParcelas(Util.formataBigDecimal(valorDebito,2,true));
				}

				sessao.setAttribute("osEncontrada", "true");
			} else {
				sessao.removeAttribute("osEncontrada");
				sessao.removeAttribute("ordemServico");
				form.setNomeOrdemServico("Ordem de Servi�o inexistente");
				form.setIdOrdemServico("");
			}

		} else {
			httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
			form.reset();
		}
		
		return retorno;
	}
	
	
	/*
	 * [FS0013 - Altera��o de Valor]
	 * 
	 * autor: Raphael Rossiter
	 * data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarCorteLigacaoAguaActionForm form){
		
		if (servicoTipo.getIndicadorPermiteAlterarValor() == 
			ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			
			form.setAlteracaoValor("OK");
		}
		else{
			form.setAlteracaoValor("");
		}
		
	}

	/**
	 * Preencher dados do corte da liga��o
	 * 
	 * @author Leonardo Regis
	 * @date 23/09/2006
	 *
	 * @param sessao
	 * @param form
	 * @param os
	 */
	private void pesquisarDadosCorteLigacao(HttpSession sessao, EfetuarCorteLigacaoAguaActionForm form, OrdemServico ordemServico) {
		//Data Encerramento
		if(ordemServico.getDataEncerramento() != null && !ordemServico.getDataEncerramento().equals("")){
		 form.setDataCorte(Util.formatarData(ordemServico.getDataEncerramento()));
		}
		
		//Comentado por Raphael Rossiter em 28/02/2007
		// Motivo do Corte
		/*if(ordemServico.getRegistroAtendimento().getImovel().getLigacaoAgua().getMotivoCorte() != null){
			form.setMotivoCorte(""+ordemServico.getRegistroAtendimento().getImovel().getLigacaoAgua().getMotivoCorte().getId());	
		}
		// Tipo do Corte
		if(ordemServico.getRegistroAtendimento().getImovel().getLigacaoAgua().getCorteTipo() != null) {
			form.setTipoCorte(""+ordemServico.getRegistroAtendimento().getImovel().getLigacaoAgua().getCorteTipo().getId());
		}
		// Leitura do Corte
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = 
			ordemServico.getRegistroAtendimento().getImovel().getLigacaoAgua().getHidrometroInstalacaoHistorico();
		if(hidrometroInstalacaoHistorico != null && 
		   hidrometroInstalacaoHistorico.getNumeroLeituraCorte() != null){
			form.setNumLeituraCorte(""+hidrometroInstalacaoHistorico.getNumeroLeituraCorte());
		}
		// N�mero do Selo do Corte
		if(ordemServico.getRegistroAtendimento().getImovel().getLigacaoAgua().getNumeroSeloCorte() != null){
			form.setNumSeloCorte(""+ordemServico.getRegistroAtendimento().getImovel().getLigacaoAgua().getNumeroSeloCorte());	
		}*/
		
		
		if(ordemServico.getImovel().getLigacaoAgua().getMotivoCorte() != null){
			form.setMotivoCorte(""+ordemServico.getImovel().getLigacaoAgua().getMotivoCorte().getId());	
		}
		// Tipo do Corte
		if(ordemServico.getImovel().getLigacaoAgua().getCorteTipo() != null) {
			form.setTipoCorte(""+ordemServico.getImovel().getLigacaoAgua().getCorteTipo().getId());
		}
		// Leitura do Corte
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = 
			ordemServico.getImovel().getLigacaoAgua().getHidrometroInstalacaoHistorico();
		if(hidrometroInstalacaoHistorico != null && 
		   hidrometroInstalacaoHistorico.getNumeroLeituraCorte() != null){
			form.setNumLeituraCorte(""+hidrometroInstalacaoHistorico.getNumeroLeituraCorte());
		}
		// N�mero do Selo do Corte
		if(ordemServico.getImovel().getLigacaoAgua().getNumeroSeloCorte() != null){
			form.setNumSeloCorte(""+ordemServico.getImovel().getLigacaoAgua().getNumeroSeloCorte());	
		}
	}
	
	/**
	 * Preencher dados do im�vel
	 * 
	 * @author Leonardo Regis
	 * @date 23/09/2006
	 * 
	 * @param form
	 * @param os
	 */
	private void preencherDadosImovel(EfetuarCorteLigacaoAguaActionForm form, OrdemServico ordemServico) {
		
		//Comentado por Raphael Rossiter em 28/02/2007
		//Imovel imovel= ordemServico.getRegistroAtendimento().getImovel();
		Imovel imovel= ordemServico.getImovel();
		
		// Matricula Im�vel
		form.setMatriculaImovel(imovel.getId().toString());
		// Inscri��o Im�vel
		String inscricaoImovel = this.getFachada().pesquisarInscricaoImovel(imovel.getId());
		form.setInscricaoImovel(inscricaoImovel);
		// Situa��o da Liga��o de Agua
		String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
		form.setSituacaoLigacaoAgua(situacaoLigacaoAgua);
		// Situa��o da Liga��o de Esgoto
		String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
		form.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);
		// Cliente
		this.pesquisarCliente(form, ordemServico);
 	}		
	
	/**
	 * Carrega cole��o de motivo do corte.
	 *
	 * @author Leonardo Regis
	 * @date 23/09/2006
	 *
	 * @param sessao
	 */
	private void getMotivoNaoCobrancaCollection(HttpSession sessao) {
		// Filtra Motivo da N�o Cobran�a
		FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();
		filtroServicoNaoCobrancaMotivo.setCampoOrderBy(FiltroServicoNaoCobrancaMotivo.DESCRICAO);

		Collection colecaoServicoNaoCobrancaMotivo = this.getFachada().pesquisar( filtroServicoNaoCobrancaMotivo, ServicoNaoCobrancaMotivo.class.getName());
		if (colecaoServicoNaoCobrancaMotivo != null && !colecaoServicoNaoCobrancaMotivo.isEmpty()) {
			sessao.setAttribute("colecaoMotivoNaoCobranca",	colecaoServicoNaoCobrancaMotivo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Motivo N�o Cobran�a");
		}
	}
	
	/**
	 * Pesquisa Cliente
	 * 
	 * @author Leonardo Regis
	 * @date 23/09/2006
	 * 
	 * @param form
	 * @param os
	 */
	private void pesquisarCliente(EfetuarCorteLigacaoAguaActionForm form, OrdemServico ordemServico) {
		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		
		//Comentado por Raphael Rossiter em 28/02/2007
		//filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, ordemServico.getRegistroAtendimento().getImovel().getId()));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, ordemServico.getImovel().getId()));
		
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
		
		Collection colecaoClienteImovel = this.getFachada().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel.iterator().next();
			Cliente cliente = clienteImovel.getCliente();

			String documento = "";
			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			// Cliente Nome/CPF-CNPJ
			form.setClienteUsuario(cliente.getNome());
			form.setCpfCnpjCliente(documento);
		}else {
			throw new ActionServletException("atencao.naocadastrado", null, "Cliente");
		}
	}
	
	/**
	 * Carrega cole��o de motivo do corte.
	 *
	 * @author Leonardo Regis
	 * @date 23/09/2006
	 *
	 * @param sessao
	 */
	private void getMotivoCorteCollection(HttpSession sessao) {
		// Filtro para o campo Motivo do Corte
		FiltroMotivoCorte filtroMotivoCorteLigacaoAgua = new FiltroMotivoCorte();
		filtroMotivoCorteLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroMotivoCorte.INDICADOR_USO,
														ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroMotivoCorteLigacaoAgua.setCampoOrderBy(FiltroMotivoCorte.DESCRICAO);

		Collection colecaoMotivoCorteLigacaoAgua = this.getFachada().pesquisar(filtroMotivoCorteLigacaoAgua, MotivoCorte.class.getName());
		if (colecaoMotivoCorteLigacaoAgua != null && !colecaoMotivoCorteLigacaoAgua.isEmpty()) {
			sessao.setAttribute("colecaoMotivoCorteLigacaoAgua",colecaoMotivoCorteLigacaoAgua);
		} else {
			throw new ActionServletException("atencao.naocadastrado",null, "Motivo do Corte");
		}
	}
	
	/**
	 * Carrega cole��o de tipo do corte.
	 *
	 * @author Leonardo Regis
	 * @date 25/09/2006
	 *
	 * @param sessao
	 */
	private void getTipoCorteCollection(HttpSession sessao) {
		// Filtro para o campo Motivo do Corte
		FiltroCorteTipo filtroTipoCorteLigacaoAgua = new FiltroCorteTipo();
		filtroTipoCorteLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroCorteTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroTipoCorteLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroCorteTipo.INDICADOR_CORTE_ADMINISTRATIVO, ConstantesSistema.NAO));
		filtroTipoCorteLigacaoAgua.setCampoOrderBy(FiltroCorteTipo.DESCRICAO);

		Collection colecaoTipoCorteLigacaoAgua = 
			this.getFachada().pesquisar(filtroTipoCorteLigacaoAgua, CorteTipo.class.getName());
		
		if (colecaoTipoCorteLigacaoAgua != null && !colecaoTipoCorteLigacaoAgua.isEmpty()) {
			sessao.setAttribute("colecaoTipoCorteLigacaoAgua",colecaoTipoCorteLigacaoAgua);
		} else {
			throw new ActionServletException("atencao.naocadastrado",null, "Tipo do Corte");
		}
	}	
}