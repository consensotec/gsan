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
package gsan.gui.faturamento.credito;

import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.atendimentopublico.ordemservico.FiltroOrdemServico;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.FiltroImovelCobrancaSituacao;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelCobrancaSituacao;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaSituacao;
import gsan.fachada.Fachada;
import gsan.faturamento.credito.CreditoOrigem;
import gsan.faturamento.credito.CreditoTipo;
import gsan.faturamento.credito.FiltroCreditoOrigem;
import gsan.faturamento.credito.FiltroCreditoTipo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0194] Inserir Cr�dito a Realizar Permite inserir um cr�dito a realizar
 * 
 * @author Roberta Costa
 * @since 12/01/2006
 */
public class ExibirInserirCreditoARealizarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInserirCreditoARealizar");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// Cole��o de Tipo de Cr�dito
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
		filtroCreditoTipo.adicionarParametro(new ParametroSimples(
			FiltroCreditoTipo.INDICADOR_GERACAO_AUTOMATICO, ConstantesSistema.NAO_ACEITO));
		Collection<CreditoTipo> collectionCreditoTipo = fachada.pesquisar(
				filtroCreditoTipo, CreditoTipo.class.getName());

		httpServletRequest.setAttribute("collectionCreditoTipo",
				collectionCreditoTipo);

		// Cole��o de Origem do Cr�dito
		FiltroCreditoOrigem filtroCreditoOrigem = new FiltroCreditoOrigem();
		Collection<CreditoOrigem> collectionCreditoOrigem = fachada.pesquisar(
				filtroCreditoOrigem, CreditoOrigem.class.getName());

		httpServletRequest.setAttribute("collectionCreditoOrigem",
				collectionCreditoOrigem);

		// Valida��es do Formul�rio
		InserirCreditoARealizarActionForm inserirCreditoARealizarActionForm = (InserirCreditoARealizarActionForm) actionForm;

		String limparForm = (String) httpServletRequest
				.getParameter("limparForm");
		String idRegistroAtendimento = inserirCreditoARealizarActionForm.getRegistroAtendimento();
		String idOrdemSerico = inserirCreditoARealizarActionForm.getOrdemServico();
		
		/////------------------
			
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		int anoMesReferencialContabil = sistemaParametro.getAnoMesFaturamento();
		int anoMesCorrente = Util.getAnoMesComoInt(new Date());
		
		if(sistemaParametro.getAnoMesFaturamento() < anoMesCorrente){
			anoMesReferencialContabil = anoMesCorrente;
		}
		
		inserirCreditoARealizarActionForm.setValorMinimoMesAnoInicialDoCredito(Util.formatarAnoMesParaMesAno(anoMesReferencialContabil));
		inserirCreditoARealizarActionForm.setMesAnoInicialDoCredito(Util.formatarAnoMesParaMesAno(anoMesReferencialContabil));
	
		
		//String codigoImovel = inserirCreditoARealizarActionForm
			//	.getMatriculaImovel();
		String idImovel = null;
		
	    if (httpServletRequest.getParameter("objetoConsulta") != null
                && httpServletRequest.getParameter("objetoConsulta").equalsIgnoreCase("2")) {
			  
			 //pesquisa o imovel pelo registro de atendimento 
			if(idRegistroAtendimento != null && !idRegistroAtendimento.trim().equals("")){
				FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
				filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID,idRegistroAtendimento));
				filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.IMOVEL);
				filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.ATENDIMENTO_MOTIVO_ENCERRAMENTO);
				filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.SOLICITACAO_TIPO_ESPECIFICACAO);
				
				Collection colecaoRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento,RegistroAtendimento.class.getName());
				if(colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()){
				
					RegistroAtendimento registroAtendimento = (RegistroAtendimento) colecaoRegistroAtendimento.iterator().next();
					
					//Registro de Atendimento n�o est� associado a um im�vel
					if(registroAtendimento.getImovel() == null){
						//FS0005 - Validar Registro de Atendimento
						throw new ActionServletException(
							"atencao.registro_atendimento.nao.associado.imovel");
					}
					
					//Registro de Atendimento est� encerrado
					if(registroAtendimento.getAtendimentoMotivoEncerramento() != null){
						//FS0005 - Validar Registro de Atendimento
						throw new ActionServletException(
							"atencao.registro_atendimento.esta.encerrado");
					}

					//Especifica��o do Tipo de Solicita��o do Registro de Atendimento n�o permite gera��o de credito
					if(registroAtendimento.getSolicitacaoTipoEspecificacao().getIndicadorGeracaoCredito() == 2){
						//FS0005 - Validar Registro de Atendimento
						throw new ActionServletException(
							"atencao.registro_atendimento.nao.permite.geracao.credito");
					}

					//caso tenha o imovel
					idImovel = registroAtendimento.getImovel().getId().toString();
					
					inserirCreditoARealizarActionForm.setRegistroAtendimento(registroAtendimento.getId().toString());
					inserirCreditoARealizarActionForm.setNomeRegistroAtendimento(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
					
					inserirCreditoARealizarActionForm.setMatriculaImovel(idImovel);
					
					httpServletRequest.setAttribute("corRegistroAtendimento",
					"valor");
					httpServletRequest.setAttribute("nomeCampo",
					"ordemServico");
					
					sessao.setAttribute("travarRegistroAtendimento",
					"nao");
					sessao.setAttribute("travarOrdemServico",
					null);
					inserirCreditoARealizarActionForm.setOrdemServico("");
					inserirCreditoARealizarActionForm.setNomeOrdemServico("");
											
					sessao.setAttribute("travarTipoCredito",
					"nao");

					
					//n�o encontrou a RA
				}else{
					//FS0004-Validar Registro de Atendimento
					inserirCreditoARealizarActionForm.setMatriculaImovel("");
					inserirCreditoARealizarActionForm.setInscricaoImovel("");
					inserirCreditoARealizarActionForm.setNomeCliente("");
					inserirCreditoARealizarActionForm.setSituacaoAgua("");
					inserirCreditoARealizarActionForm.setSituacaoEsgoto("");
					inserirCreditoARealizarActionForm.setNomeRegistroAtendimento("RA inexistente");
					httpServletRequest.setAttribute("corRegistroAtendimento",
					"exception");
					httpServletRequest.setAttribute("nomeCampo",
					"registroAtendimento");
					sessao.setAttribute("travarRegistroAtendimento",
							"nao");
				}
			}
	    }else if (httpServletRequest.getParameter("objetoConsulta") != null
		       && httpServletRequest.getParameter("objetoConsulta").equalsIgnoreCase("3")) {
			
			//pesquisa o imovel pela ordem de servi�o 
			if(idOrdemSerico != null && !idOrdemSerico.trim().equals("")){
				FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
				filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID,idOrdemSerico));
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.IMOVEL);
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.ATENDIMENTO_MOTIVO_ENCERRAMENTO);
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.REGISTRO_ATENDIMENTO);
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.CREDITO_TIPO);
				filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.SOLICITACAO_TIPO_ESPECIFICACAO);
				
				Collection colecaoOrdemServico = fachada.pesquisar(filtroOrdemServico,OrdemServico.class.getName());
				if(colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()){
				
					OrdemServico ordemServico = (OrdemServico) colecaoOrdemServico.iterator().next();
					
					//Ordem de Servi�o n�o est� associada a um Registro de Atendimento
					if(ordemServico.getRegistroAtendimento() == null){
						//FS0005 - Validar Registro de Atendimento
						throw new ActionServletException(
							"atencao.ordem_servico.nao.esta.associado.registro_atendimento");
					}

					//Registro de Atendimento da Ordem de Servi�o n�o est� associado a um im�vel
					if(ordemServico.getRegistroAtendimento().getImovel() == null){
						//FS0007 - Validar Ordem de Servi�o
						throw new ActionServletException(
							"atencao.ordem_servico.imovel.registro_atendimento.nao.associado");
					}
					
					//caso tenha o imovel
					if(ordemServico.getImovel() != null){
						idImovel = ordemServico.getImovel().getId().toString();	
					}
					
					//seta a RA
					inserirCreditoARealizarActionForm.setOrdemServico(ordemServico.getId().toString());
					inserirCreditoARealizarActionForm.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
					
					inserirCreditoARealizarActionForm.setRegistroAtendimento(ordemServico.getRegistroAtendimento().getId().toString());
					inserirCreditoARealizarActionForm.setNomeRegistroAtendimento(ordemServico.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getDescricao());
					inserirCreditoARealizarActionForm.setMatriculaImovel(idImovel);

					httpServletRequest.setAttribute("corRegistroAtendimento",
					"valor");
					sessao.setAttribute("travarRegistroAtendimento",
							null);
					
					httpServletRequest.setAttribute("corNomeOrdemServico",
					"valor");
					sessao.setAttribute("nomeCampo",
					"tipoCredito");
					                                 
					sessao.setAttribute("travarOrdemServico",
					"nao");
					
					//validar credito tipo
					if(ordemServico.getServicoTipo().getCreditoTipo() != null){
						inserirCreditoARealizarActionForm.setTipoCredito(ordemServico.getServicoTipo().getCreditoTipo().getId().toString());
						//inserirCreditoARealizarActionForm.setTipoCreditoHidden(ordemServico.getServicoTipo().getDebitoTipo().getId().toString());
						sessao.setAttribute("travarTipoCredito",
								null);
					}else{
						sessao.setAttribute("travarTipoCredito",
						"valor");
					}
					
					//n�o encontrou a RA
				}else{
					//FS0004-Validar Registro de Atendimento
					inserirCreditoARealizarActionForm.setMatriculaImovel("");
					inserirCreditoARealizarActionForm.setInscricaoImovel("");
					inserirCreditoARealizarActionForm.setNomeCliente("");
					inserirCreditoARealizarActionForm.setSituacaoAgua("");
					inserirCreditoARealizarActionForm.setSituacaoEsgoto("");
					inserirCreditoARealizarActionForm.setNomeOrdemServico("OS inexistente");
					httpServletRequest.setAttribute("corNomeOrdemServico",
					"exception");
					httpServletRequest.setAttribute("nomeCampo",
					"ordemServico");
					sessao.setAttribute("travarOrdemServico",
					"nao");
					sessao.setAttribute("travarTipoCredito",
					"nao");
				}
			}
		}else{
			sessao.setAttribute("travarRegistroAtendimento",
			"valor");
			sessao.setAttribute("travarOrdemServico",
			"valor");
			sessao.setAttribute("travarTipoCredito",
			"valor");
		}

		if (idImovel != null && !idImovel.trim().equals("")) {
			// Pesquisa o imovel na base
			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));

			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("quadra");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
			/*filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao.id");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao.id");*/
			

			Collection<Imovel> imovelPesquisado = fachada.pesquisar(
					filtroImovel, Imovel.class.getName());

			// [FS0001 - Verificar exist�ncioa da matr�cula do im�vel] Imovel
			// Inxistente
			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				sessao.setAttribute("corImovel","exception");
           		inserirCreditoARealizarActionForm
           			.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
           		
           		inserirCreditoARealizarActionForm.setNomeCliente("");
           		inserirCreditoARealizarActionForm.setSituacaoAgua("");
           		inserirCreditoARealizarActionForm.setSituacaoEsgoto("");
			}

			// [FS0001 - Verificar exist�ncioa da matr�cula do im�vel] Imovel
			// Excluido
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				if (imovel.getIndicadorExclusao() == Imovel.IMOVEL_EXCLUIDO) {
					throw new ActionServletException(
							"atencao.pesquisa.imovel.excluido");
				}
			}

			// [FS0002 - Verificar usu�rio com d�bito em cobran�a administrativa
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				FiltroImovelCobrancaSituacao filtroImovelCobrancaSituacao = new FiltroImovelCobrancaSituacao();

				filtroImovelCobrancaSituacao
						.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
				filtroImovelCobrancaSituacao
					.adicionarParametro(new ParametroSimples(FiltroImovelCobrancaSituacao.ID_COBRANCA_SITUACAO,CobrancaSituacao.COBRANCA_ADMINISTRATIVA));

				filtroImovelCobrancaSituacao
					.adicionarParametro(new ParametroNulo(FiltroImovelCobrancaSituacao.DATA_RETIRADA_COBRANCA));
				
				filtroImovelCobrancaSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroImovelCobrancaSituacao.IMOVEL_ID, imovel
										.getId()));

				Collection imovelCobrancaSituacaoEncontrada = fachada
						.pesquisar(filtroImovelCobrancaSituacao,
								ImovelCobrancaSituacao.class.getName());

				// Verifica se o im�vel tem d�bito em cobran�a administrativa
				if (imovelCobrancaSituacaoEncontrada != null
						&& !imovelCobrancaSituacaoEncontrada.isEmpty()) {
							throw new ActionServletException(
									"atencao.pesquisa.imovel.cobranca_administrativa");
				}
			}

			// [FS0003 - Verificar situa��o liga��o de �giua e esgoto]
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				if ((imovel.getLigacaoAguaSituacao() != null)
						&& ((imovel.getLigacaoAguaSituacao().getId() == LigacaoAguaSituacao.POTENCIAL) || (imovel
								.getLigacaoEsgotoSituacao().getId() == LigacaoAguaSituacao.FACTIVEL))
						&& (imovel.getLigacaoEsgotoSituacao().getId() != LigacaoEsgotoSituacao.LIGADO)) {
					throw new ActionServletException(
							"atencao.pesquisa.imovel.inativo");
				}
			}

			// Obtem o cliente imovel do imovel pesquisado
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {

				Imovel imovel = imovelPesquisado.iterator().next();

				sessao.setAttribute("imovelPesquisado", imovel);

				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, idImovel));
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
						ClienteRelacaoTipo.USUARIO));
				/*Adicionado por Erivan, evita que seja exibido o 
				 * cliente que n�o tenha mais rela��o com o imovel
				 */
				filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));

				Collection<ClienteImovel> clienteImovelPesquisado = fachada
						.pesquisar(filtroClienteImovel, ClienteImovel.class
								.getName());

				if (clienteImovelPesquisado != null
						&& !clienteImovelPesquisado.isEmpty()) {
					ClienteImovel clienteImovel = clienteImovelPesquisado
							.iterator().next();
					if (clienteImovel.getCliente() != null) {
						inserirCreditoARealizarActionForm
								.setNomeCliente(clienteImovel.getCliente()
										.getNome());
					}
				}
				if (imovel.getLigacaoAguaSituacao() != null) {
					inserirCreditoARealizarActionForm.setSituacaoAgua(imovel
							.getLigacaoAguaSituacao().getDescricao());
				}

				if (imovel.getLigacaoEsgotoSituacao() != null) {
					inserirCreditoARealizarActionForm.setSituacaoEsgoto(imovel
							.getLigacaoEsgotoSituacao().getDescricao());
				}
				inserirCreditoARealizarActionForm
						.setMatriculaImovel(idImovel);

				inserirCreditoARealizarActionForm.setInscricaoImovel(imovel
						.getInscricaoFormatada());
			}
		}

		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {

			inserirCreditoARealizarActionForm.reset(actionMapping,
				httpServletRequest);

			if (sessao.getAttribute("imovelPesquisado") != null) {
				sessao.removeAttribute("imovelPesquisado");
			}


		}

		return retorno;
	}
}