package gsan.batch.arrecadacao;

import java.util.Collection;

import gsan.arrecadacao.ControladorArrecadacaoLocal;
import gsan.arrecadacao.ControladorArrecadacaoLocalHome;
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

/**
 * [UC1575] Gerar Arquivo Carteira 17
 * 
 * @author Davi Menezes
 * @date 12/11/2013
 *
 */
public class BatchGerarArquivoCarteira17MDB implements MessageDrivenBean,
		MessageListener {

	private static final long serialVersionUID = 1L;
	
	public BatchGerarArquivoCarteira17MDB(){
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {
		
	}

	public void ejbRemove() throws EJBException {
		
	}
	
	public void ejbCreate() {

	}
	
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				this.getControladorArrecadacao().gerarArquivoCarteira17(
						(Integer) ((Object[]) objectMessage.getObject())[0],
						(Collection) ((Object []) objectMessage.getObject())[1],
						(Integer) ((Object[]) objectMessage.getObject())[2],
						(Integer) ((Object[]) objectMessage.getObject())[3]);

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
	 * Retorna a interface remota de ControladorArrecadacao
	 * 
	 * @return A interface remota do controlador de parâmetro
	 */
	private ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

}
