package gsan.batch.faturamento;

import java.util.Collection;

import gsan.arrecadacao.ControladorArrecadacaoLocal;
import gsan.arrecadacao.ControladorArrecadacaoLocalHome;
import gsan.faturamento.ControladorFaturamentoLocal;
import gsan.faturamento.ControladorFaturamentoLocalHome;
import gsan.relatorio.faturamento.conta.RelatorioConta;
import gsan.util.ConstantesJNDI;
import gsan.util.ControladorException;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class BatchGerarContasMDB implements MessageDrivenBean,MessageListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BatchGerarContasMDB(){
		super();
	}

	public void onMessage(Message message) {
		
		if(message instanceof ObjectMessage){
			
			ObjectMessage objectMessage = (ObjectMessage) message;
			
			try {
				getControladorFaturamento().gerarContas((RelatorioConta) ((Object[]) objectMessage.getObject())[0],
					(Integer) ((Object[]) objectMessage.getObject())[1]);
			} catch (ControladorException e) {				
				e.printStackTrace();
			} catch (JMSException e) {				
				e.printStackTrace();
			}			
		}		
	}

	public void ejbRemove() throws EJBException {
		// TODO Auto-generated method stub
		
	}

	public void setMessageDrivenContext(MessageDrivenContext arg0) throws EJBException {}
	
	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() {

	}
	
	/**
	 * Retorna a interface remota de ControladorArrecadacao
	 * 
	 * @return A interface remota do controlador de parâmetro
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

}
