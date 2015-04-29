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
package gsan.faturamento;

import gsan.cadastro.ControladorCadastroLocal;
import gsan.cadastro.ControladorCadastroLocalHome;
import gsan.cadastro.EnvioEmail;
import gsan.cadastro.endereco.ControladorEnderecoLocal;
import gsan.cadastro.endereco.ControladorEnderecoLocalHome;
import gsan.cobranca.ControladorCobrancaLocal;
import gsan.cobranca.ControladorCobrancaLocalHome;
import gsan.faturamento.conta.ContaMotivoCancelamento;
import gsan.faturamento.conta.ContaMotivoRetificacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesJNDI;
import gsan.util.ControladorException;
import gsan.util.MetodosBatch;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;
import gsan.util.email.ErroEmailException;
import gsan.util.email.ServicosEmail;

import java.util.Collection;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * @ejb.bean name="ControladorBatchFaturamento" display-name="Name for
 *           ControladorBatchFaturamento" description="Description for
 *           ControladorBatchFaturamento" destination-type="javax.jms.Queue"
 *           acknowledge-mode="Auto-acknowledge"
 */
public class ControladorBatchFaturamento implements MessageDrivenBean,
		MessageListener {
	private static final long serialVersionUID = 1L;

	public ControladorBatchFaturamento() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {

	}

	public void ejbRemove() throws EJBException {
	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			Usuario usuarioLogado = null;
			String mensagemEmail = "";
			String assuntoEmail = "";
			
			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				switch (objectMessage.getIntProperty("nomeMetodo")) {
				case (MetodosBatch.ENDERECO_INSERIR_CEP_IMPORTADOS): {
					this.getControladorEndereco()
							.inserirCepImportados(
									(Collection) ((Object[]) objectMessage
											.getObject())[0]);
					break;

				}
				case (MetodosBatch.GERAR_RELATORIO_MAPA_CONTROLE_CONTA): {
					this.getControladorFaturamento()
							.filtrarMapaControleContaRelatorio(
									(Integer) ((Object[]) objectMessage.getObject())[0],
									(String) ((Object[]) objectMessage.getObject())[1],
									(Usuario) ((Object[]) objectMessage.getObject())[2],
									(String) ((Object[]) objectMessage.getObject())[3],
									(String) ((Object[]) objectMessage.getObject())[4]);
					break;

				}
				case (MetodosBatch.GERAR_ARQUIVO_TEXTO_FATURAMENTO): {
					this.getControladorFaturamento().chamarGerarArquivoTextoFaturamento(
									(Integer) ((Object[]) objectMessage
									.getObject())[0],(String) ((Object[]) objectMessage.getObject())[1],
									(Collection) ((Object[]) objectMessage.getObject())[2]);
					break;
				}
				
				case (MetodosBatch.ALTERAR_VENCIMENTO_CONJUNTO_CONTA_CLIENTE): {
					this.getControladorFaturamento().alterarVencimentoConjuntoContaCliente(
									(Integer) ((Object[]) objectMessage	.getObject())[0],
									(Short) ((Object[]) objectMessage.getObject())[1],
									(Date) ((Object[]) objectMessage.getObject())[2],
									(Integer) ((Object[]) objectMessage.getObject())[3],
									(Date) ((Object[]) objectMessage.getObject())[4],
									(Date) ((Object[]) objectMessage.getObject())[5],
									(Integer) ((Object[]) objectMessage.getObject())[6],
									(Usuario) ((Object[]) objectMessage.getObject())[7],
                                    (Integer) ((Object[]) objectMessage .getObject())[8]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[7];
					assuntoEmail = 	"ALTERAR VENCIMENTO DE CONJUNTO DE CONTAS";			
					break;
				}
				
				case (MetodosBatch.ALTERAR_VENCIMENTO_CONJUNTO_CONTA): {
					this.getControladorFaturamento().alterarVencimentoConjuntoConta(
									(Integer) ((Object[]) objectMessage	.getObject())[0],
									(Date) ((Object[]) objectMessage.getObject())[1],
									(Integer) ((Object[]) objectMessage.getObject())[2],
									(Integer) ((Object[]) objectMessage.getObject())[3],
									(Date) ((Object[]) objectMessage.getObject())[4],
									(Date) ((Object[]) objectMessage.getObject())[5],
									(Usuario) ((Object[]) objectMessage.getObject())[6]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[6];
					assuntoEmail = 	"ALTERAR VENCIMENTO DE CONJUNTO DE CONTAS";								
					break;
				}
				
				case (MetodosBatch.ALTERAR_VENCIMENTO_CONJUNTO_CONTA_COLECAO): {
					this.getControladorFaturamento().alterarVencimentoConjuntoConta(
									(Collection) ((Object[]) objectMessage	.getObject())[0],
									(Date) ((Object[]) objectMessage.getObject())[1],
									(Integer) ((Object[]) objectMessage.getObject())[2],
									(Date) ((Object[]) objectMessage.getObject())[3],
									(Date) ((Object[]) objectMessage.getObject())[4],
									(Integer) ((Object[]) objectMessage.getObject())[5],
									(Usuario) ((Object[]) objectMessage.getObject())[6],
									(String) ((Object[]) objectMessage.getObject())[7],
									(String[]) ((Object[]) objectMessage.getObject())[8]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[6];
					assuntoEmail = 	"ALTERAR VENCIMENTO DE CONJUNTO DE CONTAS";	
					break;
				}
				case (MetodosBatch.CANCELAR_CONJUNTO_CONTA): {
					this.getControladorFaturamento().cancelarConjuntoConta(
							(Collection) ((Object[]) objectMessage.getObject())[0],
							(ContaMotivoCancelamento) ((Object[]) objectMessage.getObject())[1],
							(Integer) ((Object[]) objectMessage.getObject())[2],
							(Date) ((Object[]) objectMessage.getObject())[3], 
							(Date) ((Object[]) objectMessage.getObject())[4],
							(Integer) ((Object[]) objectMessage.getObject())[5],
							(Usuario) ((Object[]) objectMessage.getObject())[6],
							(String) ((Object[]) objectMessage.getObject())[7]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[6];
					assuntoEmail = 	"CANCELAR CONJUNTO DE CONTAS";
					break;
				}
				case (MetodosBatch.CANCELAR_CONJUNTO_CONTA_CLIENTE): {
					this.getControladorFaturamento().cancelarConjuntoContaCliente(
							(Integer) ((Object[]) objectMessage.getObject())[0],
							(Short) ((Object[]) objectMessage.getObject())[1],
							(ContaMotivoCancelamento) ((Object[]) objectMessage.getObject())[2],
							(Integer) ((Object[]) objectMessage.getObject())[3],
							(Date) ((Object[]) objectMessage.getObject())[4], 
							(Date) ((Object[]) objectMessage.getObject())[5],
							(Integer) ((Object[]) objectMessage.getObject())[6],
							(Usuario) ((Object[]) objectMessage.getObject())[7]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[7];
					assuntoEmail = 	"CANCELAR CONJUNTO DE CONTAS";
					break;
				}
				case (MetodosBatch.CANCELAR_CONJUNTO_CONTA_POR_GRUPO_FATURAMENTO): {
				
					this.getControladorFaturamento().cancelarConjuntoConta(
							(Integer) ((Object[]) objectMessage.getObject())[0],
							(ContaMotivoCancelamento) ((Object[]) objectMessage.getObject())[1],
							(Integer) ((Object[]) objectMessage.getObject())[2],
							(Date) ((Object[]) objectMessage.getObject())[3], 
							(Date) ((Object[]) objectMessage.getObject())[4],
							(Integer) ((Object[]) objectMessage.getObject())[5],
							(Usuario) ((Object[]) objectMessage.getObject())[6]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[6];
					assuntoEmail = 	"CANCELAR CONJUNTO DE CONTAS";
					break;
				}
				case (MetodosBatch.RETIFICAR_CONJUNTO_CONTA_POR_GRUPO_FATURAMENTO):{
					this.getControladorFaturamento().retificarConjuntoConta(
							(Integer) ((Object[]) objectMessage.getObject())[0],
							(Integer) ((Object[]) objectMessage.getObject())[1],
							(ContaMotivoRetificacao) ((Object[]) objectMessage.getObject())[2],
							(Collection) ((Object[]) objectMessage.getObject())[3], 
							(Usuario) ((Object[]) objectMessage.getObject())[4],
							(Date) ((Object[]) objectMessage.getObject())[5], 
							(Date) ((Object[]) objectMessage.getObject())[6],
							(Integer) ((Object[]) objectMessage.getObject())[7]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[4];
					assuntoEmail = 	"RETIFICAR CONJUNTO DE CONTAS";
					break;
				}
				case (MetodosBatch.RETIFICAR_CONJUNTO_CONTA_COLECAO):{
					this.getControladorFaturamento().retificarConjuntoConta(
							(Collection) ((Object[]) objectMessage.getObject())[0],
							(Integer) ((Object[]) objectMessage.getObject())[1],
							(ContaMotivoRetificacao) ((Object[]) objectMessage.getObject())[2],
							(Collection) ((Object[]) objectMessage.getObject())[3], 
							(Usuario) ((Object[]) objectMessage.getObject())[4],
							(Date) ((Object[]) objectMessage.getObject())[5], 
							(Date) ((Object[]) objectMessage.getObject())[6],
							(Integer) ((Object[]) objectMessage.getObject())[7],
							(String) ((Object[]) objectMessage.getObject())[8]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[4];
					assuntoEmail = 	"RETIFICAR CONJUNTO DE CONTAS";
					break;
				}
				case (MetodosBatch.INSERIR_DEBITOS_PARA_CONTAS_VALOR_DIFERENTE):{
					this.getControladorFaturamento().inserirDebitosContasComValorFaixasErradas(
							(Integer) ((Object[]) objectMessage.getObject())[0],
							(Usuario) ((Object[]) objectMessage.getObject())[1]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[1];
					assuntoEmail = 	"RETIFICAR CONJUNTO DE CONTAS";
					break;
				}
				case (MetodosBatch.GERAR_BOLETIM_MEDICAO):{
					this.getControladorCobranca().gerarBoletimMedicao(
							(Integer) ((Object[]) objectMessage.getObject())[0],
							(Integer) ((Object[]) objectMessage.getObject())[1],
							(Usuario) ((Object[]) objectMessage.getObject())[2],
							(Integer) ((Object[]) objectMessage.getObject())[3]);
					usuarioLogado = null;
					assuntoEmail = 	"GERAR BOLETIM DE MEDI��O";
					break;
				}
				
				//[UC1350] Gerar Arquivo Texto das Faturas Agrupadas
				case (MetodosBatch.GERAR_ARQUIVO_TEXTO_FATURAS_AGRUPADAS):{
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[3];
					
					this.getControladorCobranca().gerarArquivoTextoFaturasAgrupadas(
							(Integer) ((Object[]) objectMessage.getObject())[0],
							(Integer) ((Object[]) objectMessage.getObject())[1],
							(Integer[]) ((Object[]) objectMessage.getObject())[2]);
					
					assuntoEmail = 	"GERAR ARQUIVO TEXTO DE FATURAS AGRUPADAS";
					break;
				}
				
				}
				mensagemEmail = "ALTERADO COM SUCESSO.";
			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				mensagemEmail = "ALTERA��O N�O FOI REALIZADA COM SUCESSO.";
				e.printStackTrace();
			} finally {
				if(usuarioLogado != null){
					try {
						EnvioEmail envioEmail = getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.AVISO_CONCLUSAO_BATCH_AVULSO);
						try {
							ServicosEmail.enviarMensagem(envioEmail.getEmailRemetente(), usuarioLogado
									.getDescricaoEmail(), assuntoEmail, mensagemEmail);
						} catch (ErroEmailException e) {
//							throw new ControladorException("erro.envio.mensagem");
						}
					} catch (ControladorException e) {
						e.printStackTrace();
					}
				}									
			}
		}

	}

	/**
	 * Retorna o valor de controladorEndereco
	 * 
	 * @return O valor de controladorEndereco
	 */
	private ControladorEnderecoLocal getControladorEndereco() {
		ControladorEnderecoLocalHome localHome = null;
		ControladorEnderecoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorEnderecoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	/**
	 * Retorna o valor de controladorLocalidade
	 * 
	 * @return O valor de controladorLocalidade
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() {
	}
	
	private ControladorCadastroLocal getControladorCadastro() {
		ControladorCadastroLocalHome localHome = null;
		ControladorCadastroLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCadastroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Author: Rafael Santos Data: 04/01/2006
	 * 
	 * Retorna o valor do Controlador de Cobranca
	 * 
	 * @return O valor de controladorCobrancaLocal
	 */
	private ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
}
