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
package gsan.gui.cadastro.cliente;

import gsan.cadastro.EnvioEmail;
import gsan.cadastro.MensagemRetornoReceitaFederal;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteEndereco;
import gsan.cadastro.cliente.ClienteFone;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.ClienteTipo;
import gsan.cadastro.cliente.ClienteVirtual;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.cliente.FiltroClienteEndereco;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cadastro.cliente.FiltroClienteTipo;
import gsan.cadastro.cliente.FiltroClienteVirtual;
import gsan.cadastro.cliente.OrgaoExpedidorRg;
import gsan.cadastro.cliente.PessoaSexo;
import gsan.cadastro.cliente.Profissao;
import gsan.cadastro.cliente.RamoAtividade;
import gsan.cadastro.descricaogenerica.DescricaoGenerica;
import gsan.cadastro.descricaogenerica.FiltroDescricaoGenerica;
import gsan.cadastro.geografico.UnidadeFederacao;
import gsan.cadastro.imovel.FiltroImovelProgramaEspecial;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.imovel.ImovelProgramaEspecial;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.integracao.webservice.neurotech.fachada.ConsultaWebServiceGATEWAY;
import gsan.seguranca.AtributoGrupo;
import gsan.seguranca.ConsultarReceitaFederal;
import gsan.seguranca.acesso.FiltroPermissaoEspecial;
import gsan.seguranca.acesso.OperacaoEfetuada;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.email.ErroEmailException;
import gsan.util.email.ServicosEmail;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class AtualizarClienteAction extends GcomAction {

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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega o form do cliente
		DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		       
		Fachada fachada = Fachada.getInstancia();

		Short tipoPessoa = (Short) clienteActionForm.get("tipoPessoa");
		
		String tipoPessoaForm = tipoPessoa.toString();
		
		FiltroCliente filtroCliente =  new FiltroCliente();

		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

		filtroClienteTipo.adicionarParametro(new ParametroSimples(
				FiltroClienteTipo.ID, tipoPessoaForm));
		tipoPessoa = ((ClienteTipo) fachada.pesquisar(filtroClienteTipo,
				ClienteTipo.class.getName()).iterator().next())
				.getIndicadorPessoaFisicaJuridica();

		Short indicadorUsoNomeFantasiaConta = ConstantesSistema.NAO;
		String indicadorValidarCpfCnpj = (String) clienteActionForm.get("indicadorValidaCpfCnpj");
		
		if (clienteActionForm.get("indicadorExibicaoNomeConta") != null) {
			
			String indicadorExibicaoNomeConta = null;
			indicadorExibicaoNomeConta = (String) clienteActionForm.get(
					"indicadorExibicaoNomeConta").toString();

			if (indicadorExibicaoNomeConta
					.equals(Cliente.INDICADOR_NOME_FANTASIA.toString())) {

				indicadorUsoNomeFantasiaConta = ConstantesSistema.SIM;
			}
		}
		

		// Verifica o destino porque se o usuário tentar concluir o processo
		// nesta página, não é necessário verificar a tela de confirmação
		// if (destinoPagina != null && !destinoPagina.trim().equals("")) {
		if (tipoPessoa != null
				&& tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_JURIDICA)) {
			// Vai para Pessoa Juridica mas tem dados existentes em pessoa fisica
			String cpf = (String) clienteActionForm.get("cpf");
			String rg = (String) clienteActionForm.get("rg");
			String dataEmissao = (String) clienteActionForm.get("dataEmissao");
			Integer idOrgaoExpedidor = (Integer) clienteActionForm.get("idOrgaoExpedidor");
			Integer idUnidadeFederacao = (Integer) clienteActionForm.get("idUnidadeFederacao");
			String dataNascimento = (String) clienteActionForm.get("dataNascimento");
			Integer idProfissao = (Integer) clienteActionForm.get("idProfissao");
			Integer idPessoaSexo = (Integer) clienteActionForm.get("idPessoaSexo");
			

			if( ( idPessoaSexo != null && idPessoaSexo != ConstantesSistema.NUMERO_NAO_INFORMADO )
				|| ( cpf != null && !cpf.trim().equalsIgnoreCase("") )
					|| ( rg != null && !rg.trim().equalsIgnoreCase("") )
						|| ( dataEmissao != null && !dataEmissao.trim().equalsIgnoreCase("") )
							|| ( dataNascimento != null && !dataNascimento.trim().equalsIgnoreCase("") )
								|| ( idOrgaoExpedidor != null && idOrgaoExpedidor != ConstantesSistema.NUMERO_NAO_INFORMADO )
									|| ( idUnidadeFederacao != null && idUnidadeFederacao != ConstantesSistema.NUMERO_NAO_INFORMADO )
										|| ( idProfissao != null && idProfissao != ConstantesSistema.NUMERO_NAO_INFORMADO ) ){

				// Limpar todo o conteúdo da página de pessoa física
				clienteActionForm.set("cpf", "");
				clienteActionForm.set("rg", "");
				clienteActionForm.set("dataEmissao", "");
				clienteActionForm.set("idOrgaoExpedidor", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
				clienteActionForm.set("idUnidadeFederacao", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
				clienteActionForm.set("dataNascimento", "");
				clienteActionForm.set("idProfissao", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
				clienteActionForm.set("idPessoaSexo", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
			}
		}else if (tipoPessoa != null
			&& tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {
			// Vai para Pessoa Fisica mas tem dados existentes em pessoa juridica

			String cnpj = (String) clienteActionForm.get("cnpj");
			Integer idRamoAtividade = (Integer) clienteActionForm.get("idRamoAtividade");
			String codigoClienteResponsavel = (String) clienteActionForm.get("codigoClienteResponsavel");

			if( (cnpj != null && !cnpj.trim().equalsIgnoreCase("") )
					|| (idRamoAtividade != null && idRamoAtividade != ConstantesSistema.NUMERO_NAO_INFORMADO)
						|| (codigoClienteResponsavel != null && !codigoClienteResponsavel.trim().equalsIgnoreCase(""))) {
				// Limpa os dados da página de pessoa jurídica
				clienteActionForm.set("cnpj", "");
				clienteActionForm.set("idRamoAtividade", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
				clienteActionForm.set("codigoClienteResponsavel", "");
				clienteActionForm.set("nomeClienteResponsavel", "");
			}
		}

		// Pega o cliente que foi selecionado para atualização
		Cliente clienteAtualizacao = (Cliente) sessao
				.getAttribute("clienteAtualizacao");

		// Pega a coleção de endereços do cliente
		Collection colecaoEnderecos = (Collection) sessao
				.getAttribute("colecaoEnderecos");

		// Pega a coleção de telefones do cliente
		Collection colecaoFones = (Collection) sessao
				.getAttribute("colecaoClienteFone");

		// Cria o objeto do cliente para ser inserido
		String nome = ((String) clienteActionForm.get("nome")).toUpperCase();		

		/**
		 * @author Flávio Leonardo
		 * @date 19/08/2014
		 * 
		 * @param idCliente, usuario
		 * @return boolean
		 * @throws ControladorException
		 * [FS0018  Verificar permissão especial para alteração de cliente negativado]
		 */	
		if(clienteAtualizacao != null && fachada.verificarSeClienteEstaNegativado(clienteAtualizacao.getId(), usuario)){
			throw new ActionServletException(
					"atencao.usuario_nao_possui_permissao_alterar_cliente_negativado", clienteAtualizacao.getId().toString());
		}
		
		/**
		 * @author Diogo Luiz
		 * @date 10/10/2014
		 * 
		 * @param clienteAtualizacao, usuario
		 * @return boolean
		 * @throws ControladorException
		 * [FS0020] - Verificar permissão especial para alterar nome de Cliente com CPFCNPJ validado
		 */
		if(!nome.equals(clienteAtualizacao.getNome())){
			if(!fachada.verificarPermissaoEspecialClienteAlterarNomeCPFCNPJValidado(clienteAtualizacao, usuario)){
				if(clienteAtualizacao.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(ConstantesSistema.SIM)){
					throw new ActionServletException(
						"atencao.usuario_sem_permissao_alterar_cliente_nome_cpf_cnpj_validado", null, "CPF");
				}else{
					throw new ActionServletException(
						"atencao.usuario_sem_permissao_alterar_cliente_nome_cpf_cnpj_validado", null, "CNPJ");
				}
			}
		}
		
		
		/**
		 * Autor: Paulo Diniz
		 * Data: 11/07/2011
		 * [RR2011061059]
		 * [UC0009]
		 */
		if(clienteAtualizacao.getIndicadorUso() != null && clienteAtualizacao.getIndicadorUso().intValue() == 2 
				&& getSistemaParametro().getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_CAER)){
			//[FS0013] Verificar permissÃ£o especial alterar cliente inativo
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.ALTERAR_CLIENTE_INATIVO));
			
			Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				throw new ActionServletException(
					"atencao.usuario.sem.permissao.para.alteracao.cliente.inativo");
			}
			
		}
		

		/**
		 * Autor: Mariana Victor
		 * Data:  28/12/2010
		 * RM_3320 - [FS0010] Verificar Duplicidade de cliente
		 */
		if (this.getSistemaParametro().getIndicadorDuplicidadeCliente().toString()
				.equals(ConstantesSistema.SIM.toString())) {
			
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_CLIENTE_COM_MESMO_NOME_E_ENDERECO));
									
			Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			
			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
				filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.NOME, nome.toUpperCase()));

				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("cliente");
				
				Collection<ClienteEndereco> colecaoClienteEndereco = fachada.pesquisar(filtroClienteEndereco, ClienteEndereco.class.getName());
				
				if (colecaoClienteEndereco != null && !colecaoClienteEndereco.isEmpty()){
					Iterator iterator = colecaoClienteEndereco.iterator();
					
					while (iterator.hasNext()) {
						ClienteEndereco clienteEnderecoIterator = (ClienteEndereco) iterator.next();
						
						Iterator iteratorEnderecos = colecaoEnderecos.iterator();
						while (iteratorEnderecos.hasNext()) {
							ClienteEndereco clienteEndereco = (ClienteEndereco) iteratorEnderecos
									.next();
							
							if (clienteEndereco.getEnderecoFormatado().equals(
									clienteEnderecoIterator.getEnderecoFormatado())
									&& !clienteAtualizacao.getId().equals(
											clienteEnderecoIterator.getCliente().getId())) {
								throw new ActionServletException("atencao.duplicidade.cliente", null,
									"Cliente");
							}
						}
					}
				}	
				
			}
			
		}
		
		/**
		 * Autor: Mariana Victor
		 * Data:  28/12/2010
		 * RM_3320 - [FS0011] Verificar Nome de Cliente com menos de 10 posições
		 */
		if (this.getSistemaParametro().getIndicadorNomeMenorDez().toString()
				.equals(ConstantesSistema.NAO.toString())) {
			
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_NOMES_COM_MENOS_DE_10_CARACTERES));
									
			Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			
			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				String nomeFormatado= nome.replaceAll(" ", "");
				if (nomeFormatado.length() < 10) {
					throw new ActionServletException("atencao.nome.sobrenome.cliente.menos.dez.posicoes",
							null, nome);
				}
			}
			
		}

		/**
		 * Autor: Mariana Victor
		 * Data:  28/12/2010
		 * RM_3320 - [FS0012] Verificar Nome de Cliente com Descrição Genérica
		 */
		if (this.getSistemaParametro().getIndicadorNomeClienteGenerico().toString()
				.equals(ConstantesSistema.NAO.toString())) {
			
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_NOME_CLIENTE_GENERICO));
									
			Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			
			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				FiltroDescricaoGenerica filtroDescricaoGenerica = new FiltroDescricaoGenerica();
				Collection colecaoDescricaoGenerica = fachada.pesquisar(filtroDescricaoGenerica, DescricaoGenerica.class.getName());
				
				if (colecaoDescricaoGenerica != null || !colecaoDescricaoGenerica.isEmpty()) {
					String nomeFormatado= nome.replaceAll(" ", "");
					Iterator iteratorDescricaoGenerica = colecaoDescricaoGenerica.iterator();
					
					while (iteratorDescricaoGenerica.hasNext()) {
						DescricaoGenerica descricaoGenerica = (DescricaoGenerica) iteratorDescricaoGenerica.next();
						String nomeGenerico = descricaoGenerica.getNomeGenerico();
						String nomeGenericoFormatado = nomeGenerico.replaceAll(" ", "");
						
						if (nomeGenerico.equalsIgnoreCase(nome)
								|| nomeGenericoFormatado.equalsIgnoreCase(nome)
								|| nomeGenerico.equalsIgnoreCase(nomeFormatado)
								|| nomeGenericoFormatado.equalsIgnoreCase(nomeFormatado)) {
							throw new ActionServletException("atencao.nome.cliente.descricao.generica",
									null, "Nome do Cliente");		
						}
					}
					
				}
				
			}
			
		}
		
		String nomeAbreviado = ((String) clienteActionForm.get("nomeAbreviado")).toUpperCase();
		String rg = (String) clienteActionForm.get("rg");
		String cpf = (String) clienteActionForm.get("cpf");
		String dataEmissao = (String) clienteActionForm.get("dataEmissao");
		String dataNascimento = (String) clienteActionForm.get("dataNascimento");
		String cnpj = (String) clienteActionForm.get("cnpj");
		
		/**
		 * 	[UC0009] [FS0016] - Verificar se o cliente está negativado
		 *	Usuário negativado não pode ter o CPF alterado.
		 * 	Anderson Cabral
		 **/		
		if(clienteAtualizacao != null){
			String cpfAntigo = clienteAtualizacao.getCpf();
			if(cpfAntigo == null){
				cpfAntigo = new String("");
			}
			
			String cnpjAntigo = clienteAtualizacao.getCnpj();
			if(cnpjAntigo == null){
				cnpjAntigo = new String("");
			}
			
			if(!cpfAntigo.trim().equalsIgnoreCase(cpf) || !cnpjAntigo.trim().equalsIgnoreCase(cnpj)){
				if(fachada.verificarExistenciaNegativacaoCliente(clienteAtualizacao.getId())){
					throw new ActionServletException("atencao.cliente.negativado.cpf.modificado");
				}
			}
		}
		
		/**
		 * [FS0017] - Verificar permissão especial para cliente relacionado ao programa Viva Água
		 * Só deixar atualizar usuario relacionado com o programa viva água se tiver permissão especial
		 * 
		 * @author Diogo Luiz
		 * @date 11/06/2014
		 */	
		if(this.getSistemaParametro().getPerfilProgramaEspecial() != null){
		
			FiltroClienteImovel filtroClienteImovelViva = new FiltroClienteImovel();
			filtroClienteImovelViva.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_ID, clienteAtualizacao.getId()));
			filtroClienteImovelViva.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");
			filtroClienteImovelViva.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroClienteImovelViva.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
			Collection<ClienteImovel> colecaoClienteImovelViva = fachada.pesquisar(filtroClienteImovelViva, ClienteImovel.class.getName());
			
			Boolean imovelVivaAgua = false;
			if(!Util.isVazioOrNulo(colecaoClienteImovelViva)){
				
				Iterator it = colecaoClienteImovelViva.iterator();
				
				while(it.hasNext()){
				
					ClienteImovel clienteImovelViva = (ClienteImovel) it.next();
					Imovel imovel = clienteImovelViva.getImovel();
					ImovelPerfil imovelPerfil = imovel.getImovelPerfil();						
					
					if(imovelPerfil.getId().equals(this.getSistemaParametro().getPerfilProgramaEspecial().getId())){
						imovelVivaAgua = true;
						
						Boolean permissaoEspecialVivaAgua = false;			
						FiltroUsuarioPemissaoEspecial filtroPermissaoEspecial = new FiltroUsuarioPemissaoEspecial();
						filtroPermissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
						filtroPermissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID,
							PermissaoEspecial.ALTERAR_IMOVEL_CLIENTE_PROGRAMA_VIVA_AGUA));
						Collection<UsuarioPermissaoEspecial> colecaoPermissaoEspecial = fachada.pesquisar(filtroPermissaoEspecial, 
							UsuarioPermissaoEspecial.class.getName());
						
						if(!Util.isVazioOrNulo(colecaoPermissaoEspecial)){
							permissaoEspecialVivaAgua = true;
						}				
						
						if(imovelVivaAgua && !permissaoEspecialVivaAgua){
							throw new ActionServletException("atencao.usuario_sem_permissao_viva_agua");
						}
					}
				}								
			}
		}
		
		if(cpf != null && cpf.trim().equals("")){
			cpf = null;
		}
		if(cnpj != null && cnpj.trim().equals("")){
			cnpj = null;
		}
		String indicadorAcaoCobranca =  (String)clienteActionForm.get("indicadorAcaoCobranca");

		String email = (String) clienteActionForm.get("email");
		
		Short indicadorUso = null;
		
		if(clienteActionForm.get("indicadorUso") != null){
			indicadorUso = new Short((String) clienteActionForm
					.get("indicadorUso"));
		}else{
			indicadorUso = new Short("1");	
		}
		
		Short indicadorAcrescimos = null;
		if(clienteActionForm.get("indicadorAcrescimos") != null){
			indicadorAcrescimos = new Short((String)clienteActionForm
					.get("indicadorAcrescimos"));
		} else {
			indicadorAcrescimos = new Short("1");
		}

		// Verificar se o usuário digitou os 4 campos relacionados com o RG de
		// pessoa física ou se ele não digitou nenhum

		Integer idOrgaoExpedidor = (Integer) clienteActionForm
				.get("idOrgaoExpedidor");
		Integer idUnidadeFederacao = (Integer) clienteActionForm
				.get("idUnidadeFederacao");

		if( ! ( ( (rg != null && !rg.trim().equalsIgnoreCase(""))
					&& (idOrgaoExpedidor != null && !idOrgaoExpedidor.equals(ConstantesSistema.NUMERO_NAO_INFORMADO))) 
						&& (idUnidadeFederacao != null && !idUnidadeFederacao.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) 
							|| ((rg != null && rg.trim().equalsIgnoreCase(""))
									&& (idOrgaoExpedidor != null && idOrgaoExpedidor.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) 
										&& (idUnidadeFederacao != null && idUnidadeFederacao.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)))) ){
			throw new ActionServletException(
					"atencao.rg_campos_relacionados.nao_preenchidos");
		}

		OrgaoExpedidorRg orgaoExpedidorRg = null;
		if (clienteActionForm.get("idOrgaoExpedidor") != null
				&& ((Integer) clienteActionForm.get("idOrgaoExpedidor")).intValue() > 0) {
			orgaoExpedidorRg = new OrgaoExpedidorRg();
			orgaoExpedidorRg.setId((Integer) clienteActionForm
					.get("idOrgaoExpedidor"));
		}

		PessoaSexo pessoaSexo = null;
		if (clienteActionForm.get("idPessoaSexo") != null
				&& ((Integer) clienteActionForm.get("idPessoaSexo")).intValue() > 0) {
			pessoaSexo = new PessoaSexo();
			pessoaSexo.setId((Integer) clienteActionForm.get("idPessoaSexo"));
		}else{
			if(tipoPessoa != null && tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_FISICA)){
				throw new ActionServletException("atencao.campo.informado", "Sexo");
			}
		}

		Profissao profissao = null;
		if (clienteActionForm.get("idProfissao") != null
				&& ((Integer) clienteActionForm.get("idProfissao")).intValue() > 0) {
			profissao = new Profissao();
			profissao.setId((Integer) clienteActionForm.get("idProfissao"));
		}

		UnidadeFederacao unidadeFederacao = null;
		if (clienteActionForm.get("idUnidadeFederacao") != null
				&& ((Integer) clienteActionForm.get("idUnidadeFederacao")).intValue() > 0) {
			unidadeFederacao = new UnidadeFederacao();
			unidadeFederacao.setId((Integer) clienteActionForm.get("idUnidadeFederacao"));
		}

		ClienteTipo clienteTipo = new ClienteTipo();
		clienteTipo.setId(new Integer(((Short) clienteActionForm.get("tipoPessoa")).intValue()));

		RamoAtividade ramoAtividade = null;
		if (clienteActionForm.get("idRamoAtividade") != null
				&& ((Integer) clienteActionForm.get("idRamoAtividade")).intValue() > 0) {
			ramoAtividade = new RamoAtividade();
			ramoAtividade.setId((Integer) clienteActionForm
					.get("idRamoAtividade"));
		}
 
		Cliente clienteResponsavel = null;
		if (clienteActionForm.get("codigoClienteResponsavel") != null
				&& !((String) clienteActionForm.get("codigoClienteResponsavel")).trim().equalsIgnoreCase("")) {
			// Cria o objeto do cliente responsável
			clienteResponsavel = new Cliente();
			clienteResponsavel.setId(new Integer((String) clienteActionForm
					.get("codigoClienteResponsavel")));
		}

		// Verifica se o usuário adicionou um endereço de correspondência
		Long enderecoCorrespondenciaSelecao = (Long) clienteActionForm
				.get("enderecoCorrespondenciaSelecao");

		if (enderecoCorrespondenciaSelecao == null
				|| enderecoCorrespondenciaSelecao == 0) {
			throw new ActionServletException(
					"atencao.endereco_correspondencia.nao_selecionado");
		}

		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		//String mensagemRetornoReceita = null;
		try {
			
			if(cpf != null && cpf.equals("")){
				cpf = null;
			}

			if(cnpj != null && cnpj.equals("")){
				cnpj = null;
			}
			
			Cliente cliente = new Cliente(
					// Nome
					nome,
					
					// Nome Abreviado
					nomeAbreviado,
					
					// CPF
					cpf,
					
					// RG
					rg,
					
					// Data de Emissão do RG
					dataEmissao != null && !dataEmissao.trim().equalsIgnoreCase("") 
						? formatoData.parse(dataEmissao): null,
								
					// Data de Nascimento
					dataNascimento != null && !dataNascimento.trim().equalsIgnoreCase("") 
						? formatoData.parse(dataNascimento) : null, 
					
					// CNPJ
					cnpj, 
					
					// Email
					email, 
					
					// Indicador Uso
					indicadorUso,
					
					// Indicador Acrescimos
					indicadorAcrescimos,
					
					// Data da Última Alteração
					clienteAtualizacao.getUltimaAlteracao(),
					
					// Órgão Expedidor RG
					orgaoExpedidorRg,
					
					// Cliente Responsável
					clienteResponsavel, 
					
					// Sexo
					pessoaSexo,
					
					// Profissão
					profissao,
					
					// Unidade Federação
					unidadeFederacao, 
					
					// Tipo do Cliente
					clienteTipo,
					
					// Ramo de Atividade
					ramoAtividade,
					indicadorUsoNomeFantasiaConta);

			// Seta o id do cliente atualizado para ser identificado no BD na atualização
			cliente.setId(clienteAtualizacao.getId());
			
			
			cliente.setIndicadorAcaoCobranca(new Integer (indicadorAcaoCobranca).shortValue());
			
			
			cliente.setIndicadorGeraArquivoTexto(clienteAtualizacao.getIndicadorGeraArquivoTexto());
			
			cliente.setDiaVencimento(clienteAtualizacao.getDiaVencimento());

			
//			 Permissao Especial Validar Acrescimos Impontualidade

			boolean validarAcrescimoImpontualidade = Fachada.getInstancia()
			.verificarPermissaoValAcrescimosImpontualidade(usuario);
			
			httpServletRequest.setAttribute("validarAcrescimoImpontualidade",validarAcrescimoImpontualidade);

            
            if (clienteActionForm.get("diaVencimento") != null
                    && !(clienteActionForm.get("diaVencimento").equals(""))){
                String diaVencimento = (String)clienteActionForm.get("diaVencimento"); 
                cliente.setDataVencimento( new Short(diaVencimento));
            }else{
                cliente.setDataVencimento(null);
            }
        
        	//Nome da Mãe	
            if (clienteActionForm.get("nomeMae") != null
                        && (!(clienteActionForm.get("nomeMae").equals("")))) {
            	cliente.setNomeMae(((String)clienteActionForm.get("nomeMae")).toUpperCase());
             }
            
			if (clienteActionForm.get("indicadorGeraFaturaAntecipada") != null && !clienteActionForm.get("indicadorGeraFaturaAntecipada").equals("")) {
				cliente.setIndicadorGeraFaturaAntecipada(new Short((String) clienteActionForm.get("indicadorGeraFaturaAntecipada")));
			} else {
				cliente.setIndicadorGeraFaturaAntecipada(ConstantesSistema.NAO);
			}
			
			if (clienteActionForm.get("diaVencimento") != null && !(clienteActionForm.get("diaVencimento").equals("")) && 
			   (clienteActionForm.get("indicadorVencimentoMesSeguinte") != null && !clienteActionForm.get("indicadorVencimentoMesSeguinte").equals(""))) {
				cliente.setIndicadorVencimentoMesSeguinte(new Short((String) clienteActionForm.get("indicadorVencimentoMesSeguinte")));
			} else {
				cliente.setIndicadorVencimentoMesSeguinte(ConstantesSistema.NAO);
			}
			
			 if (clienteActionForm.get("indicadorAcaoCobranca") != null
	                    && !(clienteActionForm.get("indicadorAcaoCobranca").equals(""))){
				 cliente.setIndicadorAcaoCobranca(new Integer ((String)clienteActionForm.get("indicadorAcaoCobranca")).shortValue());
			 }

			 if (clienteActionForm.get("indicadorPermiteNegativacao") != null
						&& clienteActionForm.get("indicadorPermiteNegativacao").equals(ConstantesSistema.SIM.toString())){
					
					cliente.setIndicadorPermiteNegativacao(ConstantesSistema.NAO);
				} else {
					cliente.setIndicadorPermiteNegativacao(ConstantesSistema.SIM);
				}
			
			 	// Autor: Nathalia Santos 
				// Data: 06/12/2011
				// RM 6364
				// Inclusão de indicador para informar se o cliente está negativação por período
			 
			 if (clienteActionForm.get("indicadorNegativacaoPeriodo") != null
						&& clienteActionForm.get("indicadorNegativacaoPeriodo").equals(ConstantesSistema.SIM.toString())){
					cliente.setIndicadorNegativacaoPeriodo(ConstantesSistema.SIM);
				} else {
					cliente.setIndicadorNegativacaoPeriodo(ConstantesSistema.NAO);
				}
			
			
			//*************************************************************************
			// Autor: Ivan Sergio
			// Data: 06/08/2009
			// CRC2103
			// Verifica se a funcionalidade esta sendo executada dentro de um popup
			//*************************************************************************
			if (sessao.getAttribute("POPUP") != null) {
				if (sessao.getAttribute("POPUP").equals("true")) {
					Integer idImovel = null;
					if (sessao.getAttribute("idImovel") != null && 
							!sessao.getAttribute("idImovel").equals("")) {
						idImovel = new Integer(sessao.getAttribute("idImovel").toString());
					}else if (sessao.getAttribute("imovelAtualizacao") != null) {
						Imovel imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");
						idImovel = new Integer(imovel.getId());
					}
					
					if (idImovel == null) {
						cliente.setId2(-1);
						colecaoEnderecos = this.setaId2ClienteEnderecos(colecaoEnderecos, -1);
						colecaoFones = this.setaId2ClienteFones(colecaoFones, -1);
					} else {
						//Integer idImovel = new Integer(sessao.getAttribute("idImovel").toString());
						cliente.setId2(idImovel);
						colecaoEnderecos = this.setaId2ClienteEnderecos(colecaoEnderecos, idImovel);
						colecaoFones = this.setaId2ClienteFones(colecaoFones, idImovel);
						
						// Recupera o Tipo de Relacao do Cliente
						FiltroClienteImovel filtro = new FiltroClienteImovel();
						filtro.adicionarCaminhoParaCarregamentoEntidade(
								FiltroClienteImovel.CLIENTE_RELACAO_TIPO);
						filtro.adicionarParametro(new ParametroSimples(
								FiltroClienteImovel.CLIENTE_ID, cliente.getId()));
						filtro.adicionarParametro(new ParametroSimples(
								FiltroClienteImovel.IMOVEL_ID, idImovel));
						
						ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(
								fachada.pesquisar(filtro, ClienteImovel.class.getName()));
						
						if (clienteImovel != null) {
							if (clienteImovel.getClienteRelacaoTipo() != null) {
								Integer idAtributoGrupo = null;
								switch (clienteImovel.getClienteRelacaoTipo().getId()) {
								case 1:
									idAtributoGrupo = AtributoGrupo.ATRIBUTOS_DO_PROPRIETARIO;
									break;
								case 2:
									idAtributoGrupo = AtributoGrupo.ATRIBUTOS_DO_USUARIO;
									break;
								}
								
								if (idAtributoGrupo != null) {
									AtributoGrupo atributoGrupo = new AtributoGrupo();
									atributoGrupo.setId(idAtributoGrupo);
									
									OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
									operacaoEfetuada.setAtributoGrupo(atributoGrupo);
									
									cliente.setOperacaoEfetuada(operacaoEfetuada);
								}
							}
						}
					}
				}
			}
			
			/**
			 * Autor: Rodrigo Cabral, Paulo Diniz
			 * Data: 20/10/2010, 28/09/2011
			 * CRC4476
			 */
			ConsultarReceitaFederal consultaRF = null;
			
			String confirmado = null;
			if ( httpServletRequest.getParameter("confirmado") != null  ) {
				confirmado = httpServletRequest.getParameter("confirmado");
			}
			
			Short indicadorConsultaDocumentoReceita = this.getSistemaParametro().getIndicadorConsultaDocumentoReceita();
			
			if(cpf == null && cnpj == null){
				indicadorConsultaDocumentoReceita = ConstantesSistema.NAO;
			}
			
			if (confirmado == null && 
				indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString())){
				
				ConsultaWebServiceGATEWAY consultaWebService = new ConsultaWebServiceGATEWAY();
				
				try {
					ClienteFone clienteFone = this.getFonePrincipalDoCliente((Collection<ClienteFone>)colecaoFones);
					ClienteEndereco clienteEndereco = this.getEnderecoPrincipalDoCliente((Collection<ClienteEndereco>)colecaoEnderecos);
					
					if (cpf != null){
						ConsultarReceitaFederal consultaGSAN = fachada.pesquisarDadosReceitaFederalPessoaFisicaJahCadastrada(cpf);
						if(consultaGSAN == null){
							consultaRF = consultaWebService.consultarPessoaFisica(cpf, usuario, cliente, clienteFone, clienteEndereco);
							System.out.println("CONSULTA GATEWAY INSERIR CLIENTE CPF: "+cpf);
							sessao.setAttribute("clienteCadastradoNaReceita", consultaRF);
							sessao.setAttribute("consultaGSAN", false);
							clienteActionForm.set("nomeClienteReceitaFederal" , consultaRF.getNomePessoaFisica());
						}else{
							System.out.println("CONSULTA GATEWAY INSERIR CLIENTE CPF: "+cpf);
							sessao.setAttribute("clienteCadastradoNaReceita", consultaGSAN);
							sessao.setAttribute("consultaGSAN", true);
							clienteActionForm.set("nomeClienteReceitaFederal" , consultaGSAN.getNomePessoaFisica());
						}
					}else if (cnpj != null){
						ConsultarReceitaFederal consultaGSAN = fachada.pesquisarDadosReceitaFederalPessoaJuridicaJahCadastrada(cnpj);
						if(consultaGSAN == null){
							consultaRF = consultaWebService.consultaPessoaJuridica(cnpj, usuario, cliente, clienteFone, clienteEndereco);
							System.out.println("CONSULTA GATEWAY INSERIR CLIENTE CNPJ: "+cnpj);
							sessao.setAttribute("clienteCadastradoNaReceita", consultaRF);
							sessao.setAttribute("consultaGSAN", false);
							clienteActionForm.set("nomeClienteReceitaFederal" , consultaRF.getRazaoSocial());
						}else{
							System.out.println("CONSULTA GATEWAY INSERIR CLIENTE CNPJ: "+cnpj);
							sessao.setAttribute("clienteCadastradoNaReceita", consultaGSAN);
							sessao.setAttribute("consultaGSAN", true);
							clienteActionForm.set("nomeClienteReceitaFederal" , consultaGSAN.getRazaoSocial());
						}
					}
				} catch (Exception e) {
					if(consultaRF != null && consultaRF.getMensagemRetornoReceitaFederal() != null){
						throw new ActionServletException("atencao.falha_webservice_gateway",
								 consultaRF.getMensagemRetornoReceitaFederal().getCodigoMensagemRetorno() + " - " 
								+ consultaRF.getMensagemRetornoReceitaFederal().getDescricaoMensagemRetorno()+ ". Os dados do cliente não podem ser atualizados");
					}else{
						throw new ActionServletException("atencao.falha_webservice_gateway", "0199" + " - " + "Falha na consulta" + ". Os dados do cliente não podem ser atualizados");
					}
				}
				
				consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
				if(consultaRF.getMensagemRetornoReceitaFederal() != null && consultaRF.getMensagemRetornoReceitaFederal().getId().intValue() != MensagemRetornoReceitaFederal.ID_CONSULTA_REALIZADA_COM_SUCESSO){
					throw new ActionServletException("atencao.falha_webservice_gateway",
							consultaRF.getMensagemRetornoReceitaFederal().getCodigoMensagemRetorno() + " - " 
							+ consultaRF.getMensagemRetornoReceitaFederal().getDescricaoMensagemRetorno() +  ". Os dados do cliente não podem ser atualizados");
				}
				
			}

			boolean atualizaImovel = true;
			
			String nomeClienteReceita = "";
			
			if(indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString())){
				consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
				if(consultaRF.getCpfCliente() != null && !consultaRF.getCpfCliente().equals("")){
					nomeClienteReceita = consultaRF.getNomePessoaFisica();
					if(consultaRF.getNomePessoaFisica() == null){
						nomeClienteReceita = consultaRF.getNomeCliente();
					}
				}else if(consultaRF.getCnpjCliente() != null && !consultaRF.getCnpjCliente().equals("")){
					nomeClienteReceita = consultaRF.getRazaoSocial();
					if(consultaRF.getRazaoSocial() == null){
						nomeClienteReceita = consultaRF.getNomeCliente();
					}
				}
			}else{
				nomeClienteReceita = cliente.getNome();
			}
			
			if ( confirmado == null && 
					nomeClienteReceita != null && !nomeClienteReceita.equals("") &&
					!nomeClienteReceita.equals(nome) && indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString())) {
				
				httpServletRequest.setAttribute("nomeBotao1", "Aceitar");
				httpServletRequest.setAttribute("nomeBotao3", "Rejeitar");
		
				
				return montarPaginaConfirmacaoWizard("atencao.confirmacao_nome_receita_federal",
							httpServletRequest, 
							actionMapping, 
							nome, 
							nomeClienteReceita);
					
			}else if( confirmado == null && 
					nomeClienteReceita != null && !nomeClienteReceita.equals("") &&
					nomeClienteReceita.equals(nome) && indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString())){
				
				consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
				consultaRF.setAcaoUsuario(Short.parseShort("3"));
				sessao.setAttribute("clienteCadastradoNaReceita",consultaRF);
				
			}else if ( confirmado != null && confirmado.trim().equalsIgnoreCase("ok") && indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString())) {
				
				consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
				if(consultaRF.getCpfCliente() != null && !consultaRF.getCpfCliente().equals("")){
					if(consultaRF.getNomePessoaFisica() == null || consultaRF.getNomePessoaFisica().equals("")){
						cliente.setNome(consultaRF.getNomeCliente());
					}else{
						cliente.setNome(consultaRF.getNomePessoaFisica());
					}
				}else if(consultaRF.getCnpjCliente() != null && !consultaRF.getCnpjCliente().equals("")){
					cliente.setNome(consultaRF.getRazaoSocial());
				}
				
				atualizaImovel = true;
				
				consultaRF.setAcaoUsuario(Short.parseShort("1"));
				sessao.setAttribute("clienteCadastradoNaReceita",consultaRF);
				
			}else if(indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString()) && (consultaRF.getMensagemRetornoReceitaFederal() == null || 
					consultaRF.getMensagemRetornoReceitaFederal().getId().intValue() != MensagemRetornoReceitaFederal.ID_CONSULTA_REALIZADA_COM_SUCESSO) && 
					(confirmado != null)){
				
				consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
				
				consultaRF.setAcaoUsuario(Short.parseShort("2"));
				sessao.setAttribute("clienteCadastradoNaReceita",consultaRF);
				atualizaImovel = false;
				
				httpServletRequest.setAttribute("naoExibirBotaoVoltarTelaAtencao",true);
				reportarErros(httpServletRequest, "atencao.cliente_nao_foi_atualizado");
				
				retorno = actionMapping.findForward("telaAtencao");
				
			}  else if(confirmado != null && confirmado.equals("nao") && indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString())){
				
				consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
				
				consultaRF.setAcaoUsuario(Short.parseShort("2"));
				sessao.setAttribute("clienteCadastradoNaReceita",consultaRF);
				atualizaImovel = false;
				
				httpServletRequest.setAttribute("naoExibirBotaoVoltarTelaAtencao",true);
				reportarErros(httpServletRequest, "atencao.cliente_nao_foi_atualizado");
				
				retorno = actionMapping.findForward("telaAtencao");
				
			}			
			
			/**
			 * [UC0009] - Adicionar indicador para determinar se o  documento de CPF/CNPJ do cliente já foi validado;
			 * @author Diogo Luiz
			 * @Date 16/08/2013
			 * 
			 */	
			boolean permissaoEspecial;
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, clienteAtualizacao.getId()));
			Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			Cliente checkIndicadorCpfCnpj = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);			
			
			if(!indicadorValidarCpfCnpj.equals("1")){
			
				if(checkIndicadorCpfCnpj.getIndicadorValidaCpfCnpj().equals(1) && 
						indicadorValidarCpfCnpj.equals("2")){
				
					permissaoEspecial = fachada.verificarPermissaoEspecialAtiva(PermissaoEspecial.MODIFICAR_VALIDACAO_CPF_E_CNPJ, 
							usuario);
					if(permissaoEspecial){
						
						cliente.setIndicadorValidaCpfCnpj(Integer.parseInt(indicadorValidarCpfCnpj));
					}else{
						throw new ActionServletException("atencao.usuario_sem_permissao_indicador_valida_cpfCnpj");
					}				
				}else{
					cliente.setIndicadorValidaCpfCnpj(Integer.parseInt(indicadorValidarCpfCnpj));
				}
			
			}else if(tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_FISICA) && (clienteActionForm.get("cpf").equals("") || 
					clienteActionForm.get("cpf") == null)){
				throw new ActionServletException("atencao.cpf_cnpj_nao_informado", null, "CPF");
				
			}else if(tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_JURIDICA) && (clienteActionForm.get("cnpj").equals("") ||
					clienteActionForm.get("cnpj") == null)){
				throw new ActionServletException("atencao.cpf_cnpj_nao_informado", null, "CNPJ");
			}else{
				cliente.setIndicadorValidaCpfCnpj(Integer.parseInt(indicadorValidarCpfCnpj));
			}	
			
			
			/**
			 * #10981 - ROTINA PARA ENVIAR E-MAIL E SMS PARA DEVEDORES.
			 * @author Diogo Luiz
			 * @date 02/07/2014
			 * 
			 * RM 11417 - [UC0007] - Inserir Cliente e [UC0009] - Manter Cliente
			 */
			Short indicadorEnviarEmail = (Short) clienteActionForm.get("indicadorEnviarEmail");
			Short indicadorEnviarSms = (Short) clienteActionForm.get("indicadorEnviarSms");
			
			if(indicadorEnviarEmail != null && indicadorEnviarEmail.equals(ConstantesSistema.SIM)){
				cliente.setIndicadorEnviarEmail(ConstantesSistema.SIM);
			}else{
				cliente.setIndicadorEnviarEmail(ConstantesSistema.NAO);
			}
			
			if(indicadorEnviarSms != null && indicadorEnviarSms.equals(ConstantesSistema.SIM)){
				cliente.setIndicadorEnviarSms(ConstantesSistema.SIM);
			}else{
				cliente.setIndicadorEnviarSms(ConstantesSistema.NAO);
			}
			
			/**
			 * fim
			 */
			
			if(atualizaImovel){
				// Atualiza o cliente
				this.getFachada().atualizarCliente(cliente, 
					colecaoFones,
					colecaoEnderecos, 
					usuario);
			}
			
			consultaRF = (ConsultarReceitaFederal) sessao.getAttribute("clienteCadastradoNaReceita");
			Boolean resultadoGSAN = (Boolean) sessao.getAttribute("consultaGSAN");
			if ((resultadoGSAN != null && resultadoGSAN.booleanValue() == false) 
					&& ((confirmado == null || confirmado.equals("ok")))
					&& (consultaRF.getAcaoUsuario() != null && (consultaRF.getAcaoUsuario().intValue() == 3 || consultaRF.getAcaoUsuario().intValue() == 1))){
					this.getFachada().inserir(consultaRF);
			}else if((resultadoGSAN != null && resultadoGSAN.booleanValue() == false) 
					&& ((confirmado == null || confirmado.equals("nao")))
					&& (consultaRF.getAcaoUsuario() != null && (consultaRF.getAcaoUsuario().intValue() == 2))){
					this.getFachada().inserir(consultaRF);
			}else if((resultadoGSAN != null && resultadoGSAN.booleanValue() == true) ){
				this.getFachada().atualizar(consultaRF);
			}
			

			// limpa a sessão
			sessao.removeAttribute("clienteCadastradoNaReceita");
			sessao.removeAttribute("colecaoClienteFone");
			sessao.removeAttribute("colecaoEnderecos");
			sessao.removeAttribute("foneTipos");
			sessao.removeAttribute("municipios");
			sessao.removeAttribute("colecaoResponsavelSuperiores");
			sessao.removeAttribute("InserirEnderecoActionForm");
			sessao.removeAttribute("ClienteActionForm");
			sessao.removeAttribute("tipoPesquisaRetorno");
			sessao.removeAttribute("clienteAtualizacao");

		} catch (ParseException ex) {
			// Erro no hibernate
			reportarErros(httpServletRequest, "erro.sistema", ex);
			// Atribui o mapeamento de retorno para a tela de erro
			retorno = actionMapping.findForward("telaErro");
		}

		// Verifica se a funcionalidade esta sendo executada dentro de um popup
		boolean exibirTelaSucesso = true;
		if (sessao.getAttribute("POPUP") != null) {
			if (sessao.getAttribute("POPUP").equals("true")) {
				// Verifica o action de retorno
				// action = inserirClienteNomeTipo
				retorno = actionMapping.findForward("atualizarClientePopUp");
				sessao.setAttribute("codigoCliente", clienteAtualizacao.getId());
				sessao.setAttribute("nomeCliente", nome);
				if (cpf != null) {
					sessao.setAttribute("cpfCnpjCliente", Util.formatarCpf(cpf));
				}else if (cnpj != null) {
					sessao.setAttribute("cpfCnpjCliente", Util.formatarCnpj(cnpj));
				}
				
				httpServletRequest.setAttribute("colecaoTipoPessoa", null);
				exibirTelaSucesso = false;
			}
		}
		
		//Atualiza o cliente virtual
		if ( clienteActionForm.get("idClienteVirtual") != null && !clienteActionForm.get("idClienteVirtual").equals("") ) {
			Integer idClienteVirtual = (Integer) clienteActionForm.get("idClienteVirtual");
			FiltroClienteVirtual filtroClienteVirtual = new FiltroClienteVirtual();
			filtroClienteVirtual.adicionarParametro(new ParametroSimples(FiltroClienteVirtual.ID, idClienteVirtual));
			filtroClienteVirtual.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteVirtual.ID_IMOVEL);
			Collection<ClienteVirtual> colecaoClienteVirtual = Fachada.getInstancia().pesquisar(filtroClienteVirtual, ClienteVirtual.class.getName());
			
			ClienteVirtual clienteVirtual = (ClienteVirtual) Util.retonarObjetoDeColecao(colecaoClienteVirtual);
			
			
			
			
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
			filtroClienteImovel.adicionarParametro( new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,  clienteVirtual.getImovel().getId()));			
			filtroClienteImovel.adicionarParametro( new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID,  ClienteRelacaoTipo.USUARIO));
			filtroClienteImovel.adicionarParametro( new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
			
			Collection<ClienteImovel> colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
			if ( colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty() ) {
				
				ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
			
				if ( !clienteImovel.getCliente().getId().toString().equals(clienteAtualizacao.getId().toString()) ) {
					
					clienteImovel.setDataFimRelacao(new Date());
					ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
					clienteImovelFimRelacaoMotivo.setId(ClienteImovelFimRelacaoMotivo.ATU_CADASTRAL);
					clienteImovel.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);
					
					fachada.atualizar(clienteImovel);
				
					
					ClienteImovel clienteImovelInserir = new ClienteImovel();
					clienteImovelInserir.setDataInicioRelacao(new Date());
					clienteImovelInserir.setCliente(clienteAtualizacao);
					
					ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
					clienteRelacaoTipo.setId(ClienteRelacaoTipo.USUARIO.intValue());
					clienteImovelInserir.setClienteRelacaoTipo(clienteRelacaoTipo);
					
					clienteImovelInserir.setUltimaAlteracao(new Date());
					clienteImovelInserir.setIndicadorNomeConta(clienteImovel.getIndicadorNomeConta());
					clienteImovelInserir.setImovel(clienteImovel.getImovel());
					fachada.inserir(clienteImovelInserir);
				}
			}
			clienteVirtual.setIndicadorAtualizado(ConstantesSistema.SIM);
			Fachada.getInstancia().atualizar(clienteVirtual);
			
			 // Envia de Arquivo por email
            EnvioEmail envioEmail = fachada
                    .pesquisarEnvioEmail(
                        EnvioEmail.ENVIAR_QUESTIONARIO_SATISFACAO_CLIENTE);

                String emailRemetente = envioEmail.getEmailRemetente();
               
            try {
				ServicosEmail.enviarMensagem(emailRemetente, clienteAtualizacao.getEmail(), "Cadastro COMPESA", "O cadastro realizado através da loja virtual foi efetivado na COMPESA.");
			} catch (ErroEmailException e) {
				e.printStackTrace();
			}
			
		
			
			sessao.setAttribute("inserirClienteVirtual", true);

			retorno = actionMapping.findForward("telaSucessoPopup");
			
			montarPaginaSucesso(httpServletRequest, "Cliente " + clienteVirtual.getNome()
	                    + " atualizado com sucesso.", "", "");
		}
		
		if (exibirTelaSucesso) {
			
			// Monta a página de sucesso
			if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				
				String linkSucesso = (String)sessao.getAttribute("linkSucesso");
				String mensagemSucesso = "Cliente de código " + clienteAtualizacao.getId() + " atualizado com sucesso.";	
				
//				if(mensagemRetornoReceita != null && !mensagemRetornoReceita.equals("")){
//					mensagemSucesso = mensagemSucesso +"\n"+ mensagemRetornoReceita;
//				}
				
				if(linkSucesso != null && !linkSucesso.equals("")){
					
					montarPaginaSucesso(httpServletRequest, 
						mensagemSucesso,
						"Realizar outra Manutenção de Cliente", "exibirManterClienteAction.do?menu=sim",
						linkSucesso,
						"Retornar ao Consultar Imóvel.");
					
					sessao.removeAttribute("linkSucesso");
					
				}else if(sessao.getAttribute("caminhoVoltarPromais")!=null){
					
					montarPaginaSucesso(httpServletRequest, 
						mensagemSucesso,
						"Realizar outra Manutenção de Cliente", "exibirManterClienteAction.do?menu=sim",
						(String)sessao.getAttribute("caminhoVoltarPromais")+".do?promais=nao","Retornar ao Consultar Imóvel.");
					
					sessao.setAttribute("promaisExecutado", "sim");
					sessao.setAttribute("idImovelPromaisExecutado", Integer.parseInt((String)sessao.getAttribute("idImovel")));
					sessao.removeAttribute("idImovel");
					
					sessao.removeAttribute("caminhoVoltarPromais");
					
				}else{
					montarPaginaSucesso(httpServletRequest, 
						mensagemSucesso,
						"Realizar outra Manutenção de Cliente", 
						"exibirManterClienteAction.do?menu=sim");
				}
			}
		}

		return retorno;
	}
	
	/***
	 * @author Ivan Sergio
	 * @date: 11/08/2009
	 * 
	 * @param colecaoEnderecos
	 * @param id2
	 * @return
	 */
	private Collection setaId2ClienteEnderecos(Collection colecaoEnderecos, Integer id2) {
		Collection retorno = null;
		
		if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
			retorno = new ArrayList();
			Iterator iColecaoEnderecos = colecaoEnderecos.iterator();
			
			while (iColecaoEnderecos.hasNext()) {
				ClienteEndereco endereco = (ClienteEndereco) iColecaoEnderecos.next();
				endereco.setId2(id2);
				retorno.add(endereco);
			}
		}else {
			retorno = colecaoEnderecos;
		}
		
		return retorno;
	}
	
	/**
	 * @author Ivan Sergio
	 * @date: 11/08/2009
	 * 
	 * @param colecaoFones
	 * @param id2
	 * @return
	 */
	private Collection setaId2ClienteFones(Collection colecaoFones, Integer id2) {
		Collection retorno = null;
		
		if (colecaoFones != null && !colecaoFones.isEmpty()) {
			retorno = new ArrayList();
			Iterator iColecaoFones = colecaoFones.iterator();
			
			while (iColecaoFones.hasNext()) {
				ClienteFone fone = (ClienteFone) iColecaoFones.next();
				fone.setId2(id2);
				retorno.add(fone);
			}
		}else {
			retorno = colecaoFones;
		}
		
		return retorno;
	}
	
	/**
	 * @author Rafael Pinto
	 * @date: 09/01/2011
	 * 
	 * @param actionMapping ActionMapping
	 * @param httpServletRequest httpServletRequest
	 * @return ActionForward
	 */
	private ActionForward montaTelaAtencao(ActionMapping actionMapping, 
		HttpServletRequest httpServletRequest,String chave,
		boolean naoExibirBotaoVoltarTelaAtencao){
				
		httpServletRequest.setAttribute("naoExibirBotaoVoltarTelaAtencao",naoExibirBotaoVoltarTelaAtencao);
		reportarErros(httpServletRequest, chave);
		
		return actionMapping.findForward("telaAtencao");
	} 
	
	/**
	 * @author Paulo Diniz
	 * @date: 09/10/2011
	 * 
	 * Metodo privado que retorna o TelefonaPrincipal da lista de telefones a serem atualizados
	 */
	private ClienteFone getFonePrincipalDoCliente(Collection<ClienteFone> colecaoClienteFone){
		ClienteFone retorno = null;
		
		if(colecaoClienteFone != null){
				for (ClienteFone clienteFone : colecaoClienteFone) {
					if(clienteFone.getIndicadorTelefonePadrao().intValue() == ClienteFone.INDICADOR_FONE_PADRAO.intValue()){
						retorno = clienteFone;
					}
				}
		}
		
		return retorno;
	}
	
	/**
	 * @author Paulo Diniz
	 * @date: 09/10/2011
	 * 
	 * Metodo privado que retorna o Endereco Principal da lista de enderecos a serem atualizados
	 */
	private ClienteEndereco getEnderecoPrincipalDoCliente(Collection<ClienteEndereco> colecaoClienteEndereco){
		ClienteEndereco retorno = null;
		
		if(colecaoClienteEndereco != null){
			for (ClienteEndereco endereco : colecaoClienteEndereco) {
				if(endereco.getIndicadorEnderecoCorrespondencia().intValue() == ClienteEndereco.INDICADOR_ENDERECO_CORRESPONDENCIA.intValue()){
					retorno = endereco;
				}
			}
		}
		
		return retorno;
	}
	

}