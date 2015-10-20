package gcom.batch.cadastro;

import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class BatchTransmitirSMSEmLoteCobrancaFaturamento implements MessageDrivenBean,
MessageListener {

private static final long serialVersionUID = 1L;

public BatchTransmitirSMSEmLoteCobrancaFaturamento() {
	super();
}

public void ejbRemove() throws EJBException {
}

public void setMessageDrivenContext(MessageDrivenContext arg0)
		throws EJBException {
}

/**
 * Default create method
 * 
 * @throws CreateException
 */
public void ejbCreate() {
}

public void onMessage(Message message) {
    if (message instanceof ObjectMessage) {

        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            
			this.getControladorCadastro().
			batchTransmitirSMSEmLoteCobrancaFaturamento((Integer) ((Object[]) objectMessage.getObject())[0]);
							
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
 * Retorna o valor de controladorCadastro
 * 
 * @return O valor de controladorCadastro
 */
private ControladorCadastroLocal getControladorCadastro() {
    ControladorCadastroLocalHome localHome = null;
    ControladorCadastroLocal local = null;

	    // pega a instância do ServiceLocator.
	    ServiceLocator locator = null;
	
	    try {
	        locator = ServiceLocator.getInstancia();
	
	        localHome = (ControladorCadastroLocalHome) locator
	                .getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);
	        // guarda a referencia de um objeto capaz de fazer chamadas à
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
