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
package gsan.gui.faturamento;

import gsan.arrecadacao.pagamento.FiltroGuiaPagamento;
import gsan.arrecadacao.pagamento.GuiaPagamento;
import gsan.arrecadacao.pagamento.GuiaPagamentoItem;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.ClienteTipo;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.FiltroImovelCobrancaSituacao;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelCobrancaSituacao;
import gsan.cobranca.CobrancaSituacao;
import gsan.fachada.Fachada;
import gsan.faturamento.debito.DebitoCreditoSituacao;
import gsan.faturamento.debito.DebitoTipo;
import gsan.faturamento.debito.FiltroDebitoTipo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesDiferenteDe;
import gsan.util.filtro.ParametroSimplesIn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirGuiaPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("inserirGuiaPagamento");

		InserirGuiaPagamentoActionForm inserirGuiaPagamentoActionForm = (InserirGuiaPagamentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Verifica se o usu�rio tem permiss�o especial para inserir Guia de Pagamento sem RA(38)
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		boolean inserirGuiaPagamentoSemRa = Fachada.getInstancia().
			verificarPermissaoEspecial(PermissaoEspecial.INSERIR_GUIA_DE_PAGAMENTO_SEM_RA, usuarioLogado);
		httpServletRequest.setAttribute("inserirGuiaPagamentoSemRa", inserirGuiaPagamentoSemRa);
		
		
		if (httpServletRequest.getParameter("menu")!=null && !httpServletRequest.getParameter("menu").equals("")){
			inserirGuiaPagamentoActionForm.setNumeroTotalPrestacao(""+1);
			inserirGuiaPagamentoActionForm.setIndicadorEmitirObservacao(ConstantesSistema.NAO.toString());
		}
		
		if(httpServletRequest.getParameter("limparDebitoTipo") != null){
			sessao.removeAttribute("colecaoGuiaDebitoTipo");
		}
		
		if (httpServletRequest.getParameter("limparOS") != null) {
			inserirGuiaPagamentoActionForm.setOrdemServico("");
			inserirGuiaPagamentoActionForm.setDescricaoOrdemServico("");
		}
		
		
		// Carregar a data corrente do sistema
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();

		// Data Corrente
		httpServletRequest.setAttribute("dataAtual", formatoData.format(dataCorrente.getTime()));

		// Data Corrente + 60 dias
		dataCorrente.add(Calendar.DATE, 60);
		httpServletRequest.setAttribute("dataAtual60", formatoData.format(dataCorrente.getTime()));

		// C�digo do Cliente
		String codigoDigitadoClienteEnter = inserirGuiaPagamentoActionForm.getCodigoCliente();

		// Matr�cula do Im�vel
		String codigoDigitadoImovelEnter = inserirGuiaPagamentoActionForm.getIdImovel();

		// Verifica se o c�digo do im�vel foi digitado
		if (codigoDigitadoImovelEnter != null && !codigoDigitadoImovelEnter.trim().equals("") && 
			Integer.parseInt(codigoDigitadoImovelEnter) > 0) {
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

			filtroImovel.adicionarParametro(
				new ParametroSimples(FiltroImovel.ID, codigoDigitadoImovelEnter));

			Collection imovelEncontrado = fachada.pesquisar(filtroImovel,Imovel.class.getName());

			if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) {

				// O imovel foi encontrado
				Imovel imovel = (Imovel) imovelEncontrado.iterator().next();

				FiltroImovelCobrancaSituacao filtroImovelCobrancaSituacao = new FiltroImovelCobrancaSituacao();

				filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
				filtroImovelCobrancaSituacao.adicionarParametro(
					new ParametroSimples(FiltroImovelCobrancaSituacao.IMOVEL_ID, imovel.getId()));

				Collection imovelCobrancaSituacaoEncontrada = 
					fachada.pesquisar(filtroImovelCobrancaSituacao,ImovelCobrancaSituacao.class.getName());

				if (imovel.getIndicadorExclusao() == null ? false : imovel.getIndicadorExclusao().equals(Imovel.IMOVEL_EXCLUIDO)) {
					throw new ActionServletException("atencao.imovel.excluido");
				}

				// Verifica se o im�vel tem d�bito em cobran�a administrativa
				if (imovelCobrancaSituacaoEncontrada != null && !imovelCobrancaSituacaoEncontrada.isEmpty()) {
					
					ImovelCobrancaSituacao imovelCobrancaSituacao =
						((ImovelCobrancaSituacao) ((List) imovelCobrancaSituacaoEncontrada).get(0)); 
					
						if (imovelCobrancaSituacao.getCobrancaSituacao() != null) {
						
						if (imovelCobrancaSituacao.getCobrancaSituacao().getId().equals(CobrancaSituacao.COBRANCA_ADMINISTRATIVA) && 
							imovelCobrancaSituacao.getDataRetiradaCobranca() == null) {
							
							throw new ActionServletException("atencao.pesquisa.imovel.cobranca_administrativa");
						}
					}
				}

				inserirGuiaPagamentoActionForm.setIdImovel(""+ ((Imovel) ((List) imovelEncontrado).get(0)).getId());
				inserirGuiaPagamentoActionForm.setInscricaoImovel(((Imovel) ((List) imovelEncontrado).get(0)).getInscricaoFormatada());
				inserirGuiaPagamentoActionForm.setSituacaoAgua(((Imovel) ((List) imovelEncontrado)
					.get(0)).getLigacaoAguaSituacao().getDescricao());
				
				inserirGuiaPagamentoActionForm.setSituacaoEsgoto(((Imovel) ((List) imovelEncontrado)
					.get(0)).getLigacaoEsgotoSituacao().getDescricao());
				
				inserirGuiaPagamentoActionForm.setLocalidade(""+ ((Imovel) ((List) imovelEncontrado).get(0))
						.getLocalidade().getId());
				
				httpServletRequest.setAttribute("nomeCampo", "registroAtendimento");

				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
				filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
				
				filtroClienteImovel.adicionarParametro(
					new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,codigoDigitadoImovelEnter));
				
				Collection clientesImovelEncontrado = 
					fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
				
				ClienteImovel clienteImovel = null;

				if (clientesImovelEncontrado != null && !clientesImovelEncontrado.isEmpty()) {

					// O cliente imovel foi encontrado
					Iterator clienteImovelEncontradoIterator = clientesImovelEncontrado.iterator();

					while (clienteImovelEncontradoIterator.hasNext()) {
						
						clienteImovel = (ClienteImovel) clienteImovelEncontradoIterator.next();

						if (clienteImovel.getClienteRelacaoTipo().getId().equals(ClienteRelacaoTipo.USUARIO)) {
							break;
						}
					}
					inserirGuiaPagamentoActionForm.setNomeClienteUsuario(clienteImovel.getCliente().getNome());
				}

			} else {
				inserirGuiaPagamentoActionForm.setIdImovel("");
				throw new ActionServletException("atencao.pesquisa.imovel.inexistente.guia");
			}
			
			
		}

		// Verifica se o do cliente c�digo foi digitado
		if (codigoDigitadoClienteEnter != null && !codigoDigitadoClienteEnter.trim().equals("") && 
			Integer.parseInt(codigoDigitadoClienteEnter) > 0) {

			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade("profissao");
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");

			filtroCliente.adicionarParametro(
				new ParametroSimples(FiltroCliente.ID, codigoDigitadoClienteEnter));

			Collection clienteEncontrado = 
				fachada.pesquisar(filtroCliente,Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {

				// O Cliente foi encontrado
				Cliente cliente = ((Cliente) ((List) clienteEncontrado).get(0));
				
				if (cliente.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.cliente.inativo",null,""+cliente.getId());
				}

				inserirGuiaPagamentoActionForm.setNomeCliente(cliente.getNome());
				httpServletRequest.setAttribute("nomeCampo", "idTipoDebito");

				if (cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {

					if (cliente.getCpfFormatado() == null || 
						cliente.getCpfFormatado().equalsIgnoreCase("null")) {
						
						inserirGuiaPagamentoActionForm.setCpf("");
					} else {
						inserirGuiaPagamentoActionForm.setCpf(cliente.getCpfFormatado());
					}

					inserirGuiaPagamentoActionForm.setProfissao(
						cliente.getProfissao() == null ? "" : cliente.getProfissao().getDescricao());

				} else {

					if (cliente.getCnpjFormatado() == null ||
						cliente.getCnpjFormatado().equalsIgnoreCase("null")) {
						
						inserirGuiaPagamentoActionForm.setCpf("");

					} else {
						inserirGuiaPagamentoActionForm.setCpf(cliente.getCnpjFormatado());
					}

					inserirGuiaPagamentoActionForm.setRamoAtividade(
						cliente.getRamoAtividade() == null ? "": cliente.getRamoAtividade().getDescricao());
				}

			} else {

				inserirGuiaPagamentoActionForm.setCodigoCliente("");
				throw new ActionServletException("atencao.pesquisa.cliente.inexistente.guia");

			}
			
			httpServletRequest.setAttribute("nomeCampo", "registroAtendimento");
		}
		
		
		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String  idOrdemServico = inserirGuiaPagamentoActionForm.getOrdemServico();
		String  idRegistroAtendimento = inserirGuiaPagamentoActionForm.getRegistroAtendimento();
		String descOrdemServico = inserirGuiaPagamentoActionForm.getDescricaoOrdemServico();
		String descRA = inserirGuiaPagamentoActionForm.getNomeRegistroAtendimento();
		
		if(descRA != null && !descRA.equals("") && sessao.getAttribute("idRA") != null && !((String)sessao.getAttribute("idRA")).trim().equals("")){
			inserirGuiaPagamentoActionForm.setRegistroAtendimento((String)sessao.getAttribute("idRA"));
		}
		
		if(idOrdemServico == null || idOrdemServico.equals("")){
			sessao.removeAttribute("desabilitaPorOS");
		}
		
		// Pesquisar Ordem Servico
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")
				&& (descOrdemServico == null || descOrdemServico.equals(""))) {
			// Faz a consulta de Ordem Servico
			this.pesquisarOrdemServico(inserirGuiaPagamentoActionForm,httpServletRequest, sessao);
		}

		// Pesquisar Registro Atendimento
		if (
				idRegistroAtendimento != null && 
				!idRegistroAtendimento.trim().equals("") && 
				( httpServletRequest.getParameter("objetoConsulta") != null && 
				  httpServletRequest.getParameter("objetoConsulta").equals( "1" ) ) ) {
//				&& (descRA == null || descRA.equals(""))) {
			// Faz a consulta de Registro Atendimento
			this.pesquisarRegistroAtendimento(inserirGuiaPagamentoActionForm,httpServletRequest);
		}
		
		//Seta os request�s encontrados
		this.setaRequest(httpServletRequest,inserirGuiaPagamentoActionForm);
		
         // c�digo para checar ou naum o Atualizar
        String primeiraVez = httpServletRequest.getParameter("menu");
        if (primeiraVez != null && !primeiraVez.equals("")) {
            inserirGuiaPagamentoActionForm.setQtdeDiasVencimento("30");
        }
		
        boolean alterarValorSugeridoEmTipoDebito = Fachada
				.getInstancia()
				.verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_VALOR_SUGERIDO_EM_TIPO_DEBITO,
						usuarioLogado);

		httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
				alterarValorSugeridoEmTipoDebito);

		if (inserirGuiaPagamentoActionForm.getValorDebito() == null
				|| inserirGuiaPagamentoActionForm.getValorDebito()
						.equals("")) {

			httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
					true);

		}
        
        
        if(httpServletRequest.getParameter("idGuiaPagamentoItem") != null){
        	removerGuiaPagamentoItem(httpServletRequest.getParameter("idGuiaPagamentoItem"), sessao);
        }
        
       
        
        if(inserirGuiaPagamentoActionForm.getRegistroAtendimento() != null && !inserirGuiaPagamentoActionForm.getRegistroAtendimento().equals("")){
          	 FiltroGuiaPagamento filtroGuiaPagamento = null;
          	 
          	Collection colecaoGuiaPagamentoItem = (Collection)sessao.getAttribute("colecaoGuiaDebitoTipo");
            
            if(colecaoGuiaPagamentoItem != null && !colecaoGuiaPagamentoItem.isEmpty()){
            	Iterator iteratorGuiaPagamentoItem = colecaoGuiaPagamentoItem.iterator();
            	
            	//[UC0187]InserirGuiaPagamento
            	//[FS0008] - Verificar exist�ncia de guia de pagamento para o registro de atendimento
            	while(iteratorGuiaPagamentoItem.hasNext()){
            		GuiaPagamentoItem guiaPagamentoItem = (GuiaPagamentoItem)iteratorGuiaPagamentoItem.next();
            		
            		filtroGuiaPagamento = new FiltroGuiaPagamento();
                	
            		filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_TIPO_ID, 
            			guiaPagamentoItem.getDebitoTipo().getId()));
            		filtroGuiaPagamento.adicionarParametro(new ParametroSimples(
            			FiltroGuiaPagamento.REGISTRO_ATENDIMENTO, inserirGuiaPagamentoActionForm.getRegistroAtendimento().trim()));
            		filtroGuiaPagamento.adicionarParametro(new ParametroSimplesDiferenteDe(
            			FiltroGuiaPagamento.DEBITO_CREDITO_SITUACAO_ATUAL, DebitoCreditoSituacao.CANCELADA));
            		filtroGuiaPagamento.adicionarParametro(new ParametroSimplesDiferenteDe(
            			FiltroGuiaPagamento.DEBITO_CREDITO_SITUACAO_ATUAL, DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO));
            		
            		Collection<GuiaPagamento> colecaoGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
            		
            		if(colecaoGuiaPagamento != null && !colecaoGuiaPagamento.isEmpty()){
            			iteratorGuiaPagamentoItem.remove();
            			sessao.setAttribute("colecaoGuiaDebitoTipo", colecaoGuiaPagamentoItem);
            			throw new ActionServletException("atencao.guia_pagamento_ja_existe");
            		}
            	}
            }
          }

		return retorno;
	}
	
	/**
	 * Pesquisa Registro Atendimento 
	 *
	 * @author Rafael Pinto
	 * @date 02/11/2006
	 */
	private void pesquisarRegistroAtendimento(InserirGuiaPagamentoActionForm form,HttpServletRequest httpServletRequest) {

		// Pesquisa de acordo com os par�metros informados no filtro
		ObterDadosRegistroAtendimentoHelper obterDadosRegistroAtendimentoHelper = 
			Fachada.getInstancia().obterDadosRegistroAtendimento(new Integer(form.getRegistroAtendimento()));
		
		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (obterDadosRegistroAtendimentoHelper != null && 
			obterDadosRegistroAtendimentoHelper.getRegistroAtendimento() != null) {

			// Obt�m o objeto da cole��o pesquisada
			RegistroAtendimento registroAtendimento = obterDadosRegistroAtendimentoHelper.getRegistroAtendimento();
			
			if ( registroAtendimento.getCodigoSituacao() != RegistroAtendimento.SITUACAO_PENDENTE ){
				throw new ActionServletException( "atencao.registro_atendimento.nao.pendente" );
			}
			
			Integer idImovel = null;
			if(form.getIdImovel() != null && !form.getIdImovel().trim().equals("") ){
				idImovel = new Integer(form.getIdImovel());
			}
			
			Integer idCliente = null;
			if(form.getCodigoCliente() != null && !form.getCodigoCliente().trim().equals("") ){
				idCliente = new Integer(form.getCodigoCliente());
				form.setLocalidade("" + registroAtendimento.getLocalidade().getId());
			}
			
			Fachada.getInstancia().validarExibirInserirGuiaPagamento(registroAtendimento,null,idImovel,idCliente);
			
			form.setRegistroAtendimento(registroAtendimento.getId().toString());
			form.setNomeRegistroAtendimento(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
			

		} else {
			httpServletRequest.setAttribute("nomeCampo", "registroAtendimento");
			form.setRegistroAtendimento("");
			form.setNomeRegistroAtendimento("Registro Atendimento inexistente");
		}
	}
	
	/**
	 * Pesquisa Ordem Servi�o 
	 *
	 * @author Rafael Pinto
	 * @date 02/11/2006
	 */
	private void pesquisarOrdemServico(InserirGuiaPagamentoActionForm form,HttpServletRequest httpServletRequest,
			HttpSession sessao) {
		
		// Pesquisa de acordo com os par�metros informados no filtro
		OrdemServico ordemServico = 
			Fachada.getInstancia().recuperaOSPorId(new Integer(form.getOrdemServico()));

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (ordemServico != null) {

			Integer idImovel = null;
			if(form.getIdImovel() != null && !form.getIdImovel().trim().equals("") ){
				idImovel = new Integer(form.getIdImovel());
			}
			
			Integer idCliente = null;
			if(form.getCodigoCliente() != null && !form.getCodigoCliente().trim().equals("") ){
				idCliente = new Integer(form.getCodigoCliente());
			}

			Fachada.getInstancia().validarExibirInserirGuiaPagamento(null,ordemServico,idImovel,idCliente);

			form.setOrdemServico(""+ordemServico.getId());
			form.setDescricaoOrdemServico(ordemServico.getServicoTipo().getDescricao());
			
			RegistroAtendimento registroAtendimento = ordemServico.getRegistroAtendimento();
				
			form.setRegistroAtendimento(""+registroAtendimento.getId());
			form.setNomeRegistroAtendimento(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
			
			sessao.setAttribute("idRA", ""+registroAtendimento.getId());
			
			if(ordemServico.getServicoTipo().getDebitoTipo() != null){
				
				form.setHabilitaTipoDebito("false");
				
				
				
				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
				filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
				filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, ordemServico.getServicoTipo().getDebitoTipo().getId()));
				Collection colecaoFiltro = Fachada.getInstancia().pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());

				
				if(!colecaoFiltro.isEmpty()){
					DebitoTipo debitoTipo = (DebitoTipo)colecaoFiltro.iterator().next();
					Collection colecaoGuiaItem = new ArrayList();					
					GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
					guiaPagamentoItem.setDebitoTipo(debitoTipo);
					guiaPagamentoItem.setValorDebito(debitoTipo.getValorSugerido());
					colecaoGuiaItem.add(guiaPagamentoItem);
					
					sessao.setAttribute("colecaoGuiaDebitoTipo", colecaoGuiaItem);
					
					sessao.setAttribute("desabilitaPorOS", "sim");
				}			
			}

		} else {
			httpServletRequest.setAttribute("nomeCampo", "ordemServico");
			form.setOrdemServico("");
			form.setDescricaoOrdemServico("Ordem de servi�o inexistente");
			
		}
	}

	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 02/11/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,InserirGuiaPagamentoActionForm form){
		
		//Registro Atendimento
		if(form.getRegistroAtendimento() != null && !form.getRegistroAtendimento().equals("") && 
			form.getNomeRegistroAtendimento() != null && !form.getNomeRegistroAtendimento().equals("")){
					
			httpServletRequest.setAttribute("numeroRAEncontrada","true");
		}
		
		//Documento Cobran�a
		if(form.getOrdemServico() != null && !form.getOrdemServico().equals("") && 
			form.getDescricaoOrdemServico() != null && !form.getDescricaoOrdemServico().equals("")){
					
			httpServletRequest.setAttribute("ordemServicoEncontrada","true");
		}
	}
	
	private void removerGuiaPagamentoItem(String idGuiaPagamentoItem, HttpSession sessao){
		Collection colecaoSessao = (Collection)sessao.getAttribute("colecaoGuiaDebitoTipo");
		
		Iterator iterator = colecaoSessao.iterator();
		while(iterator.hasNext()){
			Integer idGuiaRemover = new Integer(idGuiaPagamentoItem);
			GuiaPagamentoItem guiaPagamentoItem = (GuiaPagamentoItem)iterator.next();
			if(guiaPagamentoItem.getDebitoTipo().getId().equals(idGuiaRemover)){
				iterator.remove();
			}
		}
		sessao.setAttribute("colecaoGuiaDebitoTipo", colecaoSessao);
	}
	
	
}
