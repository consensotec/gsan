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
package gsan.batch.cobranca;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cobranca.CobrancaAcao;
import gsan.cobranca.CobrancaAcaoAtividadeComando;
import gsan.cobranca.CobrancaAcaoAtividadeCronograma;
import gsan.cobranca.CobrancaAtividade;
import gsan.cobranca.CobrancaCriterio;
import gsan.cobranca.ControladorCobrancaLocal;
import gsan.cobranca.ControladorCobrancaLocalHome;
import gsan.micromedicao.Rota;
import gsan.util.ConstantesJNDI;
import gsan.util.ControladorException;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;

import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class BatchGerarAtividadeAcaoCobrancaMDB
		implements
			MessageDrivenBean,
			MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchGerarAtividadeAcaoCobrancaMDB() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {

	}

	public void ejbRemove() throws EJBException {

	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			try {

				/*
				 * gerarAtividadeAcaoCobranca( CobrancaGrupo grupoCobranca, int
				 * anoMesReferenciaCicloCobranca, Integer
				 * idCronogramaAtividadeAcaoCobranca, Integer
				 * idComandoAtividadeAcaoCobranca, Collection<Rota> rotas,
				 * CobrancaAcao acaoCobranca, CobrancaAtividade
				 * atividadeCobranca, Integer indicadorCriterio,
				 * CobrancaCriterio criterioCobranca, Cliente cliente,
				 * ClienteRelacaoTipo relacaoClienteImovel, String
				 * anoMesReferenciaInicial, String anoMesReferenciaFinal, Date
				 * dataVencimentoInicial, Date dataVencimentoFinal)
				 */

//				this.getControladorCobranca().gerarAtividadeAcaoCobranca(
//								(CobrancaGrupo) ((Object[]) objectMessage.getObject())[0],
//								(Integer) ((Object[]) objectMessage.getObject())[1],
//								(CobrancaAcaoAtividadeCronograma) ((Object[]) objectMessage.getObject())[2],
//								(CobrancaAcaoAtividadeComando) ((Object[]) objectMessage.getObject())[3],
//								(Collection) ((Object[]) objectMessage.getObject())[4],
//								(CobrancaAcao) ((Object[]) objectMessage.getObject())[5],
//								(CobrancaAtividade) ((Object[]) objectMessage.getObject())[6],
//								(Integer) ((Object[]) objectMessage.getObject())[7],
//								(CobrancaCriterio) ((Object[]) objectMessage.getObject())[8],
//								(Cliente) ((Object[]) objectMessage.getObject())[9],
//								(ClienteRelacaoTipo) ((Object[]) objectMessage.getObject())[10],
//								(String) ((Object[]) objectMessage.getObject())[11],
//								(String) ((Object[]) objectMessage.getObject())[12],
//								(Date) ((Object[]) objectMessage.getObject())[13],
//								(Date) ((Object[]) objectMessage.getObject())[14],
//								(Date) ((Object[]) objectMessage.getObject())[15],
//								(Integer) ((Object[]) objectMessage.getObject())[16],
//								(Cliente) ((Object[]) objectMessage.getObject())[17]
//
//						);

				
				this.getControladorCobranca().gerarAtividadeAcaoCobranca(
						(CobrancaAcaoAtividadeCronograma) ((Object[]) objectMessage.getObject())[0],
						(CobrancaAcaoAtividadeComando) ((Object[]) objectMessage.getObject())[1],
						(Rota) ((Object[]) objectMessage.getObject())[2],
						(CobrancaAcao) ((Object[]) objectMessage.getObject())[3],
						(CobrancaAtividade) ((Object[]) objectMessage.getObject())[4],
						(Integer) ((Object[]) objectMessage.getObject())[5],
						(CobrancaCriterio) ((Object[]) objectMessage.getObject())[6],
						(Cliente) ((Object[]) objectMessage.getObject())[7],
						(ClienteRelacaoTipo) ((Object[]) objectMessage.getObject())[8],
						(String) ((Object[]) objectMessage.getObject())[9],
						(String) ((Object[]) objectMessage.getObject())[10],
						(Date) ((Object[]) objectMessage.getObject())[11],
						(Date) ((Object[]) objectMessage.getObject())[12],
						(Date) ((Object[]) objectMessage.getObject())[13],
						(Integer) ((Object[]) objectMessage.getObject())[14],
						(Cliente) ((Object[]) objectMessage.getObject())[15],
						(Integer) ((Object[]) objectMessage.getObject())[16],
						(Integer) ((Object[]) objectMessage.getObject())[17]

				);
				
				

				
				
				
				
			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
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

	// this.enviarMensagemControladorBatch(
	// MetodosBatch.ENDERECO_INSERIR_CEP_IMPORTADOS,
	// ConstantesJNDI.QUEUE_CONTROLADOR_FATURAMENTO_MDB,
	// new Object[] { cepsImportados });

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() {

	}
}
