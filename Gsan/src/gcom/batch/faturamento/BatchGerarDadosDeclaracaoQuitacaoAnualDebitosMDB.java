package gcom.batch.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

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
 * [UC1001] Emitir declara��o de quita��o anual de d�bitos
 * 
 * 	Este caso de uso permite a gera��o de declara��o de quita��o de d�bitos.
 * 
 * @author Hugo Amorim
 * @date 17/03/2010
 */
public class BatchGerarDadosDeclaracaoQuitacaoAnualDebitosMDB implements
		MessageDrivenBean, MessageListener {


	private static final long serialVersionUID = 1L;
	
	public BatchGerarDadosDeclaracaoQuitacaoAnualDebitosMDB() {
		super();
	}	
	
	public void onMessage(Message message) {
		
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			
			try {
								
						this.getControladorFaturamento()
								.gerarDadosDeclaracaoQuitacaoAnualDebitos(
									(Integer) ((Object[]) objectMessage.getObject())[0], //ID FUNCIONALIDADE INICIADA
									(Collection<Integer>) ((Object[]) objectMessage.getObject())[1], //ANO
									(Rota) ((Object[]) objectMessage.getObject())[2],//ROTA
									(Short) ((Object[]) objectMessage.getObject())[3],//INDICADOR CONTA PARCELADA
									(Short) ((Object[]) objectMessage.getObject())[4],//INDICADOR COBRANCA JUDICIAL
									(Date) ((Object[]) objectMessage.getObject())[5],//DATA VERIFICACAO PAGAMENTOS
									(SistemaParametro) ((Object[]) objectMessage.getObject())[6]);				
				

			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}	
	}

	public void ejbRemove() throws EJBException {
	}

	public void setMessageDrivenContext(MessageDrivenContext arg0)
			throws EJBException {

	}
	/**
	 * Retorna o valor de controladorFaturamento
	 * 
	 * @return O valor de controladorFaturamento
	 */
	protected ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
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
	
	public void ejbCreate() {

	}

}
