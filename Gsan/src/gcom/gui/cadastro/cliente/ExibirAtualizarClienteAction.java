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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteVirtual;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteFone;
import gcom.cadastro.cliente.FiltroClienteVirtual;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action para a pré-exibição da página de Atualizar Cliente
 * 
 * @author rodrigo
 */
public class ExibirAtualizarClienteAction extends GcomAction {

	/**
	 * Método que é executado primeiro
	 * 
	 * @param actionMapping
	 *            Mapeamento do Struts
	 * @param actionForm
	 *            Form do Funcionário
	 * @param httpServletRequest
	 *            Request Atual
	 * @param httpServletResponse
	 *            Response Atual
	 * @return Retorno do Mapeamento do Struts
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// ------------Parte que inicializa o processo de
		// atualização----------------------------------

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("atualizarClienteNomeTipo");

		// obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		String codigoCliente = null;

		StatusWizard statusWizard = null;
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//Cadastro de cliente virtual
		ClienteVirtual clienteVirtual = null;
		ClienteImovel clienteImovel = null;
		if ( httpServletRequest.getParameter("atualizarClienteVirtual") != null && !httpServletRequest.getParameter("atualizarClienteVirtual").toString().equals("") ) {
			
			FiltroClienteVirtual filtroClienteVirtual = new FiltroClienteVirtual();
			filtroClienteVirtual.adicionarParametro( new ParametroSimples(FiltroClienteVirtual.ID,  httpServletRequest.getParameter("atualizarClienteVirtual")));
			filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.ID_IMOVEL);
			filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.FONE_TIPO);
			filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.ORGAO_EXPEDIDOR);
			filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.PESSOA_SEXO);
			filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.PROFISSAO);
			filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.RAMO_ATIVIDADE);
			filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.UNIDADE_FEDERACAO);
			
			Collection<ClienteVirtual> colecaoClienteVirtual = fachada.pesquisar(filtroClienteVirtual, ClienteVirtual.class.getName());
			clienteVirtual = (ClienteVirtual) Util.retonarObjetoDeColecao(colecaoClienteVirtual);
			
			FiltroCliente filtroCliente = new FiltroCliente();
			if ( clienteVirtual.getCpf() != null && !clienteVirtual.getCpf().equals("") ) {
				filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CPF, clienteVirtual.getCpf()));
			} else if ( clienteVirtual.getCnpj() != null && !clienteVirtual.getCnpj().equals("") ){
				filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CNPJ, clienteVirtual.getCnpj()));
			}
			
			Collection<Cliente> colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			Cliente clienteAtualizar = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
			codigoCliente = clienteAtualizar.getId().toString();
			
		}
		//Fim cadastro cliente virtual
		
		
		if (httpServletRequest.getParameter("desfazer") == null) {

			if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				codigoCliente = (String) httpServletRequest
						.getParameter("idRegistroAtualizacao");
			} else if (httpServletRequest.getAttribute("idRegistroAtualizacao") != null) {
				codigoCliente = (String) httpServletRequest
						.getAttribute("idRegistroAtualizacao");
			}
			

			//[UC1049] - Atualizar Dados Cadastrais PROMAIS
			//link para tela de sucesso
			String linkSucesso = (String) httpServletRequest.getParameter("linkSucesso");
			if(linkSucesso != null && !linkSucesso.equals("")){			
				
				sessao.setAttribute("linkSucesso", linkSucesso);						
			}


			/**
			 * Autor: Fernanda Almeida
			 * Data: 18/12/2011
			 * [RM6015]
			 * [UC0009] Manter Cliente
			 */
			Collection<?> imoveis = fachada.obterImovelCorporativo(new Integer(codigoCliente));
			
			if(imoveis.size() > 0){
				
				boolean possuiPermissaoManterClienteResponsavelImoveisPublicos = 
		    			fachada.verificarPermissaoEspecialAtiva(
		    					PermissaoEspecial.MANTER_IMOVEL_PERFIL_CORPORATIVO,usuarioLogado);
		    		
		    		if(!possuiPermissaoManterClienteResponsavelImoveisPublicos){
		    			throw new ActionServletException(
		    					"atencao.usuario_permissao_corporativo");
		    		}
				
			}
			
			
			
			// Verifica se chegou no atualizar cliente atraves da tela de
			// filtrar devido a um unico registro
			// ou atraves da lista de imoveis no manter cliente
			if (sessao.getAttribute("atualizar") != null
					|| httpServletRequest.getParameter("sucesso") != null ) {
				statusWizard = new StatusWizard(
						"atualizarClienteWizardAction",
						"atualizarClienteAction",
						"cancelarAtualizarClienteAction",
						"exibirFiltrarClienteAction",
						"exibirAtualizarClienteAction.do", 
						codigoCliente);
				
				sessao.removeAttribute("atualizar");
			
			} else if(httpServletRequest.getParameter("promais") != null){
				statusWizard = new StatusWizard(
						"atualizarClienteWizardAction",
						"atualizarClienteAction",
						"cancelarAtualizarClienteAction",
						"exibirConsultarImovelAction",
						"exibirAtualizarClienteAction.do", 
						codigoCliente);

			} else {
				statusWizard = new StatusWizard(
						"atualizarClienteWizardAction",
						"atualizarClienteAction",
						"cancelarAtualizarClienteAction",
						"exibirManterClienteAction",
						"exibirAtualizarClienteAction.do", 
						codigoCliente);
			}

			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							1, "ClienteNomeTipoA.gif", "ClienteNomeTipoD.gif",
							"exibirAtualizarClienteNomeTipoAction",
							"atualizarClienteNomeTipoAction"));
			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							2, "ClientePessoaA.gif", "ClientePessoaD.gif",
							"exibirAtualizarClientePessoaAction",
							"atualizarClientePessoaAction"));
			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							3, "ClienteEnderecoA.gif", "ClienteEnderecoD.gif",
							"exibirAtualizarClienteEnderecoAction",
							"atualizarClienteEnderecoAction"));
			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							4, "ClienteTelefoneA.gif", "ClienteTelefoneD.gif",
							"exibirAtualizarClienteTelefoneAction",
							"atualizarClienteTelefoneAction"));

			// manda o statusWizard para a sessao
			sessao.setAttribute("statusWizard", statusWizard);

		} else {
			statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");

			codigoCliente = statusWizard.getId();
		}

		// limpa a sessão
		sessao.removeAttribute("colecaoClienteFone");
		
		//**********************************************************************
		// Autor: Ivan Sergio
		// Data: 23/07/2009
		// CRC2103
		// Guarda o endereco do Imovel para o caso em que o Inserir/Manter
		// cliente é invocado pelo Inserir/Manter Imovel como PopUp
		//**********************************************************************
		if (sessao.getAttribute("colecaoEnderecos") != null 
				&& !((Collection) sessao.getAttribute("colecaoEnderecos")).isEmpty()) {
			Collection colecaoEndereco = (Collection) sessao.getAttribute("colecaoEnderecos");
			Object obj = colecaoEndereco.iterator().next();
			if (obj instanceof Imovel) {
				sessao.setAttribute("colecaoEnderecosImovel", sessao.getAttribute("colecaoEnderecos"));
			}
		}
		sessao.removeAttribute("colecaoEnderecos");
		//**********************************************************************
		sessao.removeAttribute("foneTipos");
		sessao.removeAttribute("municipios");
		sessao.removeAttribute("colecaoResponsavelSuperiores");
		sessao.removeAttribute("InserirEnderecoActionForm");
		
		// Retira o actionForm da sessão para criar um novo mais abaixo na linha 192
		sessao.removeAttribute("ClienteActionForm");
		
		sessao.removeAttribute("tipoPesquisaRetorno");
		sessao.removeAttribute("clienteAtualizacao");

		// Cria o filtro para cliente
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
				codigoCliente));
		filtroCliente
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_RESPONSAVEL);
		filtroCliente
		.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");

		// pesquisa a coleçao de cliente(s)
		Collection<Cliente> clientes = fachada.pesquisar(filtroCliente, Cliente.class
				.getName());
		
		// Caso a coleção esteja vazia, indica erro inesperado
		if (clientes == null || clientes.isEmpty()) {
			throw new ActionServletException("erro.sistema");
		} else {
			// O cliente que será atualizado
			Cliente cliente = (Cliente) ((List) clientes).get(0);

			if (cliente.getId() != null && !cliente.getId().equals("")) {
				statusWizard.adicionarItemHint("Código:", cliente.getId()
						.toString());
			}
			if (cliente.getNome() != null && !cliente.getNome().equals("")) {
				statusWizard.adicionarItemHint("Nome:", cliente.getNome());
			}
			if (cliente.getCnpj() != null && !cliente.getCnpj().equals("")) {
				statusWizard.adicionarItemHint("CNPJ:", cliente
						.getCnpjFormatado());
			} else if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				statusWizard.adicionarItemHint("CPF:", cliente
						.getCpfFormatado());
			}

			Collection colecaoClienteCadastradoTarifaSocial = fachada.verificarClienteUsuarioCadastradoTarifaSocial(cliente.getId());
			
			if (colecaoClienteCadastradoTarifaSocial != null && !colecaoClienteCadastradoTarifaSocial.isEmpty()) {
						
				if (!fachada.verificarPermissaoAtualizarUsuarioTarifaSocial(usuarioLogado)) {
					throw new ActionServletException("atencao.usuario.sem.permissao.atualizar.usuario.tarifa_social");
				}
			}
			
			/**
			 * @author Hugo Leonardo
			 * @data 20/09/2010
			 * @crc 4763
			 * @analista Ana Cristina
			 * 
			 * [UC0009] Manter Cliente
			 * 		[FS0008] - Verificar permissão especial para cliente de imóvel público
			 * 
			 */
			
			if(Util.verificarNaoVazio(codigoCliente)){
				
				if( fachada.verificarExistenciaImovelPublico(cliente.getId())){
					
					boolean possuiPermissaoAtualizarClienteImoveisPublicos = 
		    			fachada.verificarPermissaoEspecialAtiva(
		    					PermissaoEspecial.ALTERAR_CLIENTE_PARA_IMOVEIS_PUBLICOS, usuarioLogado);
		    		
		    		if(!possuiPermissaoAtualizarClienteImoveisPublicos){
		    			throw new ActionServletException(
		    					"atencao.nao_usuario_nao_possui_permissao_alterar_cliente");
		    		}
				}
			}
			
			
			
			
			// Manda o cliente na sessão
			sessao.setAttribute("clienteAtualizacao", cliente);

			// Formato para a conversão de datas
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

			/******************************************************************************************/
			/** Código para criar um actionForm totalmente novo - Esta foi a solução encontrada para **/
			/** a passagem do inserir direto para o atualizar, fazendo as substituição dos dados do  **/
			/** form de mesmo nome corretamente, é preciso pegar o form pelo httpServletRequest no   **/
			/** exibir da primeira aba (neste caso ExibirAtualizarClienteNomeTipoAction)             **/
			/******************************************************************************************/
			
			ModuleConfig module = actionMapping.getModuleConfig();
			FormBeanConfig formBeanConfig = module
					.findFormBeanConfig("ClienteActionForm");
			DynaActionFormClass dynaClass = DynaActionFormClass
					.createDynaActionFormClass(formBeanConfig);
			DynaValidatorForm clienteActionForm = null;
			try {
				clienteActionForm = (DynaValidatorForm) dynaClass.newInstance();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
			/******************************************************************************************/
			
			clienteActionForm.set("indicadorValidaCpfCnpj", cliente.getIndicadorValidaCpfCnpj() + "");
			
			clienteActionForm.set("codigoCliente", cliente.getId().toString());
			clienteActionForm.set("nome", formatarResultado(cliente.getNome()));
			clienteActionForm.set("nomeAbreviado", formatarResultado(cliente
					.getNomeAbreviado()));
			
			clienteActionForm.set("indicadorExibicaoNomeConta", formatarResultado(cliente.getIndicadorUsoNomeFantasiaConta() + ""));
			
			clienteActionForm.set("email",
					formatarResultado(cliente.getEmail()));
            
            if(cliente.getDataVencimento() != null){
                clienteActionForm.set("diaVencimento", cliente.getDataVencimento().toString());
            }else{
                clienteActionForm.set("diaVencimento", "");
            }
            
            clienteActionForm.set("indicadorPessoaFisicaJuridica", cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().toString());
			clienteActionForm.set("tipoPessoa", new Short(""
					+ cliente.getClienteTipo().getId()));
			// Criado para comparação
			clienteActionForm.set("tipoPessoaAntes", new Short(""
					+ cliente.getClienteTipo().getId()));
			clienteActionForm.set("cpf", formatarResultado(""
					+ cliente.getCpf()));
			clienteActionForm
					.set("rg", formatarResultado("" + cliente.getRg()));
			if (cliente.getDataEmissaoRg() != null) {
				clienteActionForm.set("dataEmissao", formatoData.format(cliente
						.getDataEmissaoRg()));
			} else {
				clienteActionForm.set("dataEmissao", "");

			}
			clienteActionForm.set("idOrgaoExpedidor", formatarResultado(cliente
					.getOrgaoExpedidorRg()));
			clienteActionForm.set("idUnidadeFederacao",
					formatarResultado(cliente.getUnidadeFederacao()));

			if (cliente.getDataNascimento() != null) {
				clienteActionForm.set("dataNascimento", formatoData
						.format(cliente.getDataNascimento()));
			} else {
				clienteActionForm.set("dataNascimento", "");

			}

			clienteActionForm.set("idProfissao", formatarResultado(cliente
					.getProfissao()));
			clienteActionForm.set("idPessoaSexo", formatarResultado(cliente
					.getPessoaSexo()));
			clienteActionForm.set("nomeMae", formatarResultado(cliente.getNomeMae()));			
			if (cliente.getCnpj() != null) {
				clienteActionForm.set("cnpj", cliente.getCnpj().toString());
			}
			clienteActionForm.set("idRamoAtividade", formatarResultado(cliente
					.getRamoAtividade()));
			if (cliente.getCliente() != null) {
				clienteActionForm.set("codigoClienteResponsavel",
						formatarResultado(cliente.getCliente().getId()
								.toString()));
				clienteActionForm.set("nomeClienteResponsavel",
						formatarResultado(cliente.getCliente().getNome()));
			}
			
			if(cliente.getIndicadorAcaoCobranca() != null){
			
				clienteActionForm.set("indicadorAcaoCobranca","" + cliente.getIndicadorAcaoCobranca());
			}else{
				clienteActionForm.set("indicadorAcaoCobranca","" + ConstantesSistema.INDICADOR_USO_ATIVO);
			}
			
			
			
			
			// Seta a coleção de endereços do usuário
			FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
			filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.CLIENTE_ID, cliente.getId()));
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
			
			Collection<ClienteEndereco> enderecosCliente = fachada.pesquisar(
					filtroClienteEndereco, ClienteEndereco.class.getName());
			Iterator iterator = null;

			if (enderecosCliente != null) {
				iterator = enderecosCliente.iterator();

				// Percorrer a coleção dos endereços para setar no form qual o
				// endereço do cliente que
				// foi selecionado como o de correspondência
				while (iterator.hasNext()) {
					ClienteEndereco clienteEndereco = (ClienteEndereco) iterator
							.next();
					if(clienteEndereco.getLogradouroBairro()!=null)

					if (clienteEndereco
							.getIndicadorEnderecoCorrespondencia()
							.equals(
									ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA)) {
						clienteActionForm
								.set(
										"enderecoCorrespondenciaSelecao",
										new Long(
												obterTimestampIdObjeto(clienteEndereco)));
						break;
					}
				}
				sessao.setAttribute("colecaoEnderecos", enderecosCliente);
			}

			// Seta a coleção de telefones do usuário
			FiltroClienteFone filtroClienteFone = new FiltroClienteFone();
			filtroClienteFone.adicionarParametro(new ParametroSimples(
					FiltroClienteFone.CLIENTE_ID, cliente.getId()));

			/*filtroClienteFone
					.adicionarCaminhoParaCarregamentoEntidade("foneTipo.descricao");*/
			
			filtroClienteFone.adicionarCaminhoParaCarregamentoEntidade("foneTipo");

			Collection<ClienteFone> telefonesCliente = fachada.pesquisar(filtroClienteFone,
					ClienteFone.class.getName());

			if (telefonesCliente != null) {

				iterator = telefonesCliente.iterator();

				// Percorrer a coleção dos telefones para setar no form qual o
				// telefone do cliente que
				// foi selecionado como o principal
				while (iterator.hasNext()) {
					ClienteFone clienteFone = (ClienteFone) iterator.next();

					if (clienteFone != null) {
						if (clienteFone.getIndicadorTelefonePadrao() != null
								&& clienteFone
										.getIndicadorTelefonePadrao()
										.equals(
												ConstantesSistema.INDICADOR_TELEFONE_PRINCIPAL)) {
							clienteActionForm
									.set(
											"indicadorTelefonePadrao",
											new Long(
													obterTimestampIdObjeto(clienteFone)));
						}
						
						
						if (clienteFone.getIndicadorTelefoneSMS() != null
								&& clienteFone
										.getIndicadorTelefoneSMS()
										.equals(
												ConstantesSistema.SIM)) {
							
							clienteActionForm
							.set(
									"indicadorTelefoneSMS",
									new Long(
											obterTimestampIdObjeto(clienteFone)));
						}
						
						
						
					}
				}
			}

			// Verifica o indicador de uso
			clienteActionForm.set("indicadorUso", cliente.getIndicadorUso()
					.toString());
			
			clienteActionForm.set("indicadorAcrescimos", cliente.getIndicadorAcrescimos()
					.toString());
			
			clienteActionForm.set("indicadorGeraFaturaAntecipada", cliente.getIndicadorGeraFaturaAntecipada().toString());
			if (cliente.getDataVencimento() != null) {
				clienteActionForm.set("indicadorVencimentoMesSeguinte", cliente.getIndicadorVencimentoMesSeguinte().toString());
			}
			
			if (cliente.getIndicadorPermiteNegativacao() != null){
			
				if (cliente.getIndicadorPermiteNegativacao().equals(ConstantesSistema.SIM)){
					clienteActionForm.set("indicadorPermiteNegativacao", ConstantesSistema.NAO.toString());
				} else {
					clienteActionForm.set("indicadorPermiteNegativacao", ConstantesSistema.SIM.toString());
				}
			}
			
			if (cliente.getIndicadorNegativacaoPeriodo() != null){
				
				if (cliente.getIndicadorNegativacaoPeriodo().equals(ConstantesSistema.SIM)){
					clienteActionForm.set("indicadorNegativacaoPeriodo", ConstantesSistema.SIM.toString());
				} else {
					clienteActionForm.set("indicadorNegativacaoPeriodo", ConstantesSistema.NAO.toString());
				}
			}
			
			if(cliente.getIndicadorEnviarEmail() != null){
				
				if(cliente.getIndicadorEnviarEmail().equals(ConstantesSistema.SIM)){
					clienteActionForm.set("indicadorEnviarEmail", ConstantesSistema.SIM);
				}else{
					clienteActionForm.set("indicadorEnviarEmail", ConstantesSistema.NAO);
				}
			}
			
			if(cliente.getIndicadorEnviarSms() != null){
				
				if(cliente.getIndicadorEnviarSms().equals(ConstantesSistema.SIM)){
					clienteActionForm.set("indicadorEnviarSms", ConstantesSistema.SIM);
				}else{
					clienteActionForm.set("indicadorEnviarSms", ConstantesSistema.NAO);
				}
			}
			
			
			//Cadastro de clientes virtuais
			if ( clienteVirtual != null ) {
				
				clienteActionForm.set("idClienteVirtual", clienteVirtual.getId());
				clienteActionForm.set("nome", formatarResultado(clienteVirtual.getNome()));
				
				clienteActionForm.set("email",formatarResultado(clienteVirtual.getEmail()));
	            
	            clienteActionForm.set("indicadorPessoaFisicaJuridica", clienteVirtual.getIndicadorPessoaFisicaJuridica().toString());
				
				clienteActionForm.set("cpf", formatarResultado(""
						+ clienteVirtual.getCpf()));
				clienteActionForm
						.set("rg", formatarResultado("" + clienteVirtual.getRg()));
				if (clienteVirtual.getDataEmissaoRg() != null) {
					clienteActionForm.set("dataEmissao", formatoData.format(clienteVirtual
							.getDataEmissaoRg()));
				} else {
					clienteActionForm.set("dataEmissao", "");
				}
				clienteActionForm.set("idOrgaoExpedidor", formatarResultado(clienteVirtual
						.getOrgaoExpedidorRg()));
				clienteActionForm.set("idUnidadeFederacao",
						formatarResultado(clienteVirtual.getUnidadeFederacao()));

				if (clienteVirtual.getDataNascimento() != null) {
					clienteActionForm.set("dataNascimento", formatoData
							.format(clienteVirtual.getDataNascimento()));
				} else {
					clienteActionForm.set("dataNascimento", "");

				}

				clienteActionForm.set("idProfissao", formatarResultado(clienteVirtual
						.getProfissao()));
				clienteActionForm.set("idPessoaSexo", formatarResultado(clienteVirtual
						.getPessoaSexo()));
				clienteActionForm.set("nomeMae", formatarResultado(clienteVirtual.getNomeMae()));			
				if (clienteVirtual.getCnpj() != null) {
					clienteActionForm.set("cnpj", clienteVirtual.getCnpj().toString());
				}
				clienteActionForm.set("idRamoAtividade", formatarResultado(clienteVirtual
						.getRamoAtividade()));
				
				ClienteFone clienteFone = new ClienteFone();
				clienteFone.setCliente(cliente);
				clienteFone.setDdd(clienteVirtual.getDdd());
				clienteFone.setFoneTipo(clienteVirtual.getFoneTipo());
				clienteFone.setContato(clienteVirtual.getNomeContato());
				clienteFone.setRamal(clienteVirtual.getNumeroFoneRamal());
				clienteFone.setTelefone(clienteVirtual.getNumeroTelefone());
				clienteFone.setUltimaAlteracao(new Date());
				
				Iterator<ClienteFone> iteratorTelefonesCliente = telefonesCliente.iterator();
				ClienteFone clienteAux = null;
				boolean possuiTelefone = true;
				while ( iteratorTelefonesCliente.hasNext() ) {
					clienteAux = (ClienteFone) iteratorTelefonesCliente.next();
					
					if ( clienteAux.getFoneTipo().getId().toString().equals(clienteFone.getFoneTipo().getId().toString()) ) {
						clienteAux.setDdd(clienteVirtual.getDdd());
						clienteAux.setFoneTipo(clienteVirtual.getFoneTipo());
						clienteAux.setContato(clienteVirtual.getNomeContato());
						clienteAux.setRamal(clienteVirtual.getNumeroFoneRamal());
						clienteAux.setTelefone(clienteVirtual.getNumeroTelefone());
						clienteAux.setUltimaAlteracao(new Date());
						possuiTelefone = false;
						break;
					}
				}
				
				if ( possuiTelefone  ) {
					telefonesCliente.add(clienteFone);
				}
				
				ClienteEndereco clienteEnderecoImovel = fachada.pesquisarClienteEnderecoAPartirDoEnderecoDoImovel(clienteVirtual.getImovel().getId());
				boolean possuiClienteEndereco = true;
				ClienteEndereco clienteEndereco;
				Iterator iteratorEnderecosCliente = enderecosCliente.iterator();
				while( iteratorEnderecosCliente.hasNext() ) {
					
					clienteEndereco = (ClienteEndereco) iteratorEnderecosCliente.next();
						
					if ( clienteEnderecoImovel.getLogradouroBairro() != null ) {
						clienteEndereco.setLogradouroBairro(clienteEnderecoImovel.getLogradouroBairro());
					}
					if (clienteEnderecoImovel.getLogradouroCep() != null ) {
						clienteEndereco.setLogradouroCep(clienteEnderecoImovel.getLogradouroCep());
					}
					if (clienteEnderecoImovel.getComplemento() != null ) {
						clienteEndereco.setComplemento(clienteEnderecoImovel.getComplemento());
					}
					if ( clienteEnderecoImovel.getPerimetroFinal() != null ) {
						clienteEndereco.setPerimetroFinal(clienteEnderecoImovel.getPerimetroFinal());
					}
					if ( clienteEnderecoImovel.getPerimetroInicial() != null ) {
						clienteEndereco.setPerimetroInicial(clienteEnderecoImovel.getPerimetroInicial());
					}
					if ( clienteEnderecoImovel.getEnderecoReferencia() != null ) {
						clienteEndereco.setEnderecoReferencia(clienteEnderecoImovel.getEnderecoReferencia());
					}
					if ( clienteEnderecoImovel.getIndicadorEnderecoCorrespondencia() != null ) {
						clienteEndereco.setIndicadorEnderecoCorrespondencia(clienteEnderecoImovel.getIndicadorEnderecoCorrespondencia());
					}
					if ( clienteEnderecoImovel.getNumero() != null ) {
						clienteEndereco.setNumero(clienteEnderecoImovel.getNumero());
					}
					
					possuiClienteEndereco = false;
					break;
					
				}
				if ( possuiClienteEndereco  ) {
					enderecosCliente.add(clienteEnderecoImovel);
				}
				sessao.setAttribute("colecaoEnderecos", enderecosCliente);
				
			}
			
			// Seta a coleção de telefones do usuário
			sessao.setAttribute("colecaoClienteFone", telefonesCliente);

			// Seta o form na sessão
			// sessao.setAttribute("ClienteActionForm", clienteActionForm);

			// Manda o form para a primeira página do processo para que depois
			// ela seja colocada na sessão
			httpServletRequest.setAttribute("ClienteActionForm", clienteActionForm);

			/**
			 * Autor: Paulo Diniz
			 * Data: 11/07/2011
			 * [RR2011061059]
			 * [UC0009] Manter Cliente
			 * [FS0013] Verificar permissão especial alterar cliente inativo
			 */
			if(cliente.getIndicadorUso() != null && cliente.getIndicadorUso().intValue() == 2 
					&& getSistemaParametro().getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_CAER)){
				sessao.setAttribute("desabilitarCampos", true);
			}else{
				sessao.setAttribute("desabilitarCampos", false);
			}
		}
		
		if(httpServletRequest.getParameter("promais")!=null){
        	sessao.setAttribute("promais",httpServletRequest.getParameter("promais"));
        	sessao.setAttribute("idImovel",httpServletRequest.getParameter("idImovel"));
        	sessao.setAttribute("caminhoVoltarPromais", statusWizard.getCaminhoActionVoltarFiltro());
        }
		
		
		// devolve o mapeamento de retorno
		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param parametro
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	private String formatarResultado(String parametro) {
		if (parametro != null && !parametro.trim().equals("")
				&& !parametro.trim().equals("null")) {
			return parametro.trim();
		} else {
			return "";
		}
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param parametro
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	private Integer formatarResultado(Object parametro) {
		if (parametro != null) {
			try {
				return (Integer) parametro.getClass().getMethod("getId",
						(Class[]) null).invoke(parametro, (Object[]) null);
			} catch (SecurityException ex) {
				return null;
			} catch (NoSuchMethodException ex) {
				return null;
			} catch (InvocationTargetException ex) {
				return null;
			} catch (IllegalArgumentException ex) {
				return null;
			} catch (IllegalAccessException ex) {
				return null;
			}
		} else {
			return new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO);
		}
	}

}