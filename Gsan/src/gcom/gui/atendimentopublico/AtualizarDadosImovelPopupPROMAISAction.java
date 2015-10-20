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


import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**

 * Descri��o da classe

 * @author Daniel Alves
 * @date 29/07/2010
 */

public class AtualizarDadosImovelPopupPROMAISAction extends GcomAction {


	/**
	 * 
	 * [UC1049] Atualizar dados Cadadastrais PROMAIS
	 * 
	 * 
	 * @author Daniel Alves
	 * @date 29/07/2010
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

		ActionForward retorno = actionMapping.findForward("telaSucessoPopup");

		ExibirAtualizarDadosClientesPopupActionForm atualizarDadosClientesPopupActionForm = (ExibirAtualizarDadosClientesPopupActionForm) actionForm;
		
		HttpSession sessao = getSessao(httpServletRequest);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		//int idImovel = Integer.valueOf(atualizarDadosClientesPopupActionForm.getMatricula());
		
		//Imovel imovel = (Imovel) getFachada().pesquisarImovel(idImovel);
		
		//Atualiza��o do Cliente
		boolean clienteAtualizado = this.alterarDadosCliente(atualizarDadosClientesPopupActionForm,usuarioLogado,sessao);
		boolean imovelAtualizado = false;
		
		//se for modificado o nome da conta.
		/*if(imovel != null){
			
			if(atualizarDadosClientesPopupActionForm.getNomeConta() != null){
				
				boolean isAlterado = false;
				
				//se o nome da conta for apagado.
				if(imovel.getNomeImovel()!=null 
						&& !imovel.getNomeImovel().equals("")
						&& atualizarDadosClientesPopupActionForm.getNomeConta().trim().equals("")){
					
					FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
					
					filtroClienteImovel.adicionarParametro(
							new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
					filtroClienteImovel.adicionarParametro(
							new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
					filtroClienteImovel.adicionarParametro(
							new ParametroNulo(FiltroClienteImovel.CLIENTE_IMOVEL_FIM_RELACAO_MOTIVO));
					
					filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
						
					Collection colecaoClienteImovel = this.getFachada().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
					
					
					if(!Util.isVazioOrNulo(colecaoClienteImovel)){
						
						ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
						
						atualizarIndicadorClienteImovel(clienteImovel.getCliente().getId().toString());
					}
					
					imovel.setNomeImovel(atualizarDadosClientesPopupActionForm.getNomeConta());
					isAlterado = true;	
				}
				
				if(imovel.getNomeImovel()!=null 
						&& !atualizarDadosClientesPopupActionForm.getNomeConta().trim().equals("")
						&& !imovel.getNomeImovel().equals(atualizarDadosClientesPopupActionForm.getNomeConta().trim())){
					imovel.setNomeImovel(atualizarDadosClientesPopupActionForm.getNomeConta());
					isAlterado = true;	
				}
					
				if(isAlterado){
					//Registrar Transa��o
					//-----------------REGISTRAR TRANSA��O-----------------------
					RegistradorOperacao registradorOperacao = 
						new RegistradorOperacao(
							Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_PROMAIS,
							imovel.getId(), 
							imovel.getId(), 
							new UsuarioAcaoUsuarioHelper( usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
							
					registradorOperacao.registrarOperacao(imovel);		
					//---------------FIM REGISTRAR TRANSA��O---------------------
					
					getFachada().atualizar(imovel);
					imovelAtualizado = true;
				}
			}
		}*/
		
		String mensagem = "";
		boolean semAlteracao = false;
		if(clienteAtualizado && imovelAtualizado){
			mensagem = "Cliente e Im�vel alterado com sucesso";
		}else if(clienteAtualizado){
			mensagem = "Cliente alterado com sucesso";
		}else if(imovelAtualizado) {
			mensagem = "Im�vel alterado com sucesso";
		}else{
			mensagem = "Cliente e Im�vel n�o foi alterado";
			semAlteracao = true;
		}
		
		if(semAlteracao){

			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "atencao.imovel.cliente.sem_alteracao");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}else{
			montarPaginaSucesso(httpServletRequest,	mensagem, "", "");	
		}
		
		
		return retorno;
	}
	
	
	/*
	*//**
	 * @author Daniel Alves
	 * @date 02/09/2010 
	 * @param idClienteImovel
	 *//*
	private void atualizarIndicadorClienteImovel(String codClienteImovel){
		
		int idClienteimovel = Integer.valueOf(codClienteImovel);
		
		getFachada().atualizarIndicadorNomeContaClienteImovel(idClienteimovel);
		
	}*/

	/**
	 * @author Rafael Pinto
	 * @param sessao 
	 * @date 10/09/2010 
	 * @param idClienteImovel
	 */
	private boolean alterarDadosCliente(ExibirAtualizarDadosClientesPopupActionForm form,Usuario usuarioLogado, HttpSession sessao){
		boolean clienteAtualizado = false;
		
		//caso a colecao de ClienteImovel nao esteja vazia
		if(form.getColecaoClienteImovel() != null &&
				!form.getColecaoClienteImovel().isEmpty()){

			//id do cliente imovel a ser altualizado
			String codClienteImovelAtualizado = form.getCodClienteImovel();

			//verifica se foi selecionado algum cliente
			if(codClienteImovelAtualizado != null && !codClienteImovelAtualizado.equals("")){

				Iterator iteratorClienteImovel = form.getColecaoClienteImovel().iterator();
		
				boolean isAlterado = false;
				ClienteImovel clienteImovel = null;
				Cliente cliente = null;

				//Procura o cliente que foi selecionado no popup
				while (iteratorClienteImovel.hasNext()) {
					
					clienteImovel = (ClienteImovel) iteratorClienteImovel.next();

					//se acho o clienteImovel que vai ser alterado na colecao
					if(clienteImovel.getId().toString().equals(codClienteImovelAtualizado)){
						cliente = clienteImovel.getCliente();
						
						//se o nome do cliente n�o estiver vazio.
						if(cliente.getNome() != null && !cliente.getNome().equals("")
								&& form.getNovoNomeCliente()!=null //& !form.getNovoNomeCliente().equals("")
								&& !cliente.getNome().equals(form.getNovoNomeCliente())){							

							//Teste de altera��o do nome do cliente menos que 5 digitos do nome
							Integer dif = StringUtils.getLevenshteinDistance(cliente.getNome() ,form.getNovoNomeCliente());
							
							if(dif > 5){						
								throw new ActionServletException("atencao.alteracao.maxima.caracteres");
							}else if(dif != 0){
								cliente.setNome(form.getNovoNomeCliente());
								isAlterado = true;
							}	
						}
						
						
							
							//Teste da remo��o de telefones do cliente. As demais altera��es do cliente, s�o realizadas no momento que s�o executadas.
							/*Collection colecaoRemoverFone = (Collection)form.getRemoverClienteFone();
							if(colecaoRemoverFone != null && colecaoRemoverFone.size() > 0){
								Iterator iteratorColecaoRemoverFone = colecaoRemoverFone.iterator();
								
								ClienteFone clienteFoneRemover = null;
								
								while(iteratorColecaoRemoverFone.hasNext()){
									clienteFoneRemover = (ClienteFone)iteratorColecaoRemoverFone.next();
									
									//se o telefone a ser removido for do cliente selecionado para atualiza��o (ent�o remove clienteFone)
									if(clienteFoneRemover.getCliente().getId().equals(form.getIdCliente())){
										getFachada().remover(clienteFoneRemover);
									}
																		
								}
								
								form.setRemoverClienteFone(null);
								isAlterado = true;
							}*/
					
					
					//caso n�o tenha informado o cpf/cnpj.
					if(form.getCpfCnpjCliente() != null &&
							form.getCpfCnpjCliente().equals("")){
						
						throw new ActionServletException("atencao.cpf_nao_informado");
						
					} else {
						
						//Pessoa F�sica
						if(cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(ConstantesSistema.SIM)){										
							//se houver alguma mudan�a no cpf
							if(!form.getCpfCnpjCliente().equals(cliente.getCpf())){
								
								String cpf = form.getCpfCnpjCliente();
								
								//valida o cpf
								if(Util.validacaoCPF(cpf)){
									
									//verifica se existe o cliente
									if(!existeClienteCpf(cpf,cliente.getId())){													
										cliente.setCpf(form.getCpfCnpjCliente());
										isAlterado = true;
										
									}
									
								}else{
									throw new ActionServletException("atencao.digito_verificador_cpf_nao_confere");
								}
								
							}										
							

						//Pessoa Jur�dica	
						}else if(cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(ConstantesSistema.NAO)){										
							//se houver alguma mudan�a no cnpj
							if(!form.getCpfCnpjCliente().equals(cliente.getCnpj())){
								
								String cnpj = form.getCpfCnpjCliente();
								
								//valida o cnpj
								if(Util.validacaoCNPJ(cnpj)){
									
									//verifica se existe o cliente
									if(!existeClienteCnpj(cnpj,cliente.getId())){													
										cliente.setCnpj(form.getCpfCnpjCliente());
										isAlterado = true;
										
									}
									
								}else{
									throw new ActionServletException("atencao.digito_verificador_cnpj_nao_confere");
								}											
							}
							
						}
					}	
						
						if(!Util.isVazioOrNulo(cliente.getClienteFones())){
							
							//Remove todos para inserir logo ap�s nova cole��o de telefones
							this.getFachada().removerTodosTelefonesPorCliente(cliente.getId());
							
							for (Iterator iterator = cliente.getClienteFones().iterator(); iterator.hasNext();) {
								ClienteFone clienteFone = (ClienteFone) iterator.next();
								
								getFachada().inserir(clienteFone);	
							}
						}
						
						//Teste de altera��o do telefone padr�o.
						if(sessao.getAttribute("mudouTelefonePadrao")!=null){
							clienteAtualizado = true;
						}
						
					}
				}
				
				//Se foi modificado algum dado do cliente, atualiza o cliente.
				if(isAlterado){	
					
					//Registrar Transa��o
					//-----------------REGISTRAR TRANSA��O-----------------------
					RegistradorOperacao registradorOperacao = new RegistradorOperacao(
							Operacao.OPERACAO_ATUALIZAR_DADOS_CLIENTE_PROMAIS,
							cliente.getId(), cliente.getId(), new UsuarioAcaoUsuarioHelper( usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
							
					registradorOperacao.registrarOperacao(cliente);		
					//---------------FIM REGISTRAR TRANSA��O---------------------
					
					//getFachada().atualizarCliente(cliente,null,null,usuarioLogado);
					
					getFachada().atualizar(cliente);
					clienteAtualizado = true;
					
				}
				
			}
			else{
				throw new ActionServletException("atencao.cliente_nao_selecionado");					
			}
		}
		
		return clienteAtualizado;
	}
	
	/*
	 * @author Daniel Alves
	 * @date 01/09/2010 
	 * @param cpf
	 * @return boolean
	 */
	private boolean existeClienteCpf(String cpf, Integer idCliente){
		
		boolean existeCliente = false;
		
		FiltroCliente filtroCliente = new FiltroCliente();
		
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CPF, cpf));
		filtroCliente.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroCliente.CPF, idCliente));
		
		Collection colecao = (Collection) getFachada().pesquisar(filtroCliente, Cliente.class.getName());
		
		Cliente cliente = (Cliente)Util.retonarObjetoDeColecao(colecao);
		
		if(colecao != null && colecao.size() > 0){
			existeCliente = true;
			throw new ActionServletException("atencao.cpf.cliente.ja_cadastrado", cliente.getId().toString());
		}
		
		return existeCliente;		
	}
	
	
	/*
	 * @author Daniel Alves
	 * @date 01/09/2010 
	 * @param cnpj
	 * @return boolean
	 */
	private boolean existeClienteCnpj(String cnpj, Integer idCliente){
		
		boolean existeCliente = false;
		
		FiltroCliente filtroCliente = new FiltroCliente();
		
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CNPJ, cnpj));
		filtroCliente.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroCliente.CNPJ, idCliente));
		
		Collection colecao = (Collection) getFachada().pesquisar(filtroCliente, Cliente.class.getName());
		
		Cliente cliente = (Cliente)Util.retonarObjetoDeColecao(colecao);
		
		if(colecao != null && colecao.size() > 0){
			existeCliente = true;
			throw new ActionServletException("atencao.cnpj.cliente.ja_cadastrado", cliente.getId().toString());
		}
		
		return existeCliente;		
	}
	
}
