package gcom.batch.arrecadacao;

import java.util.Collection;

import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.seguranca.acesso.usuario.Usuario;
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


public class BatchRegistrarMovimentoArrecadadoresMDB implements MessageDrivenBean,
		MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchRegistrarMovimentoArrecadadoresMDB() {
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
                this
                        .getControladorArrecadacao()
                        .registrarMovimentoArrecadadores
                        	(
                        		(StringBuilder) ((Object[]) objectMessage.getObject())[0],
                                (Short) ((Object[]) objectMessage.getObject())[1],
                                (String) ((Object[]) objectMessage.getObject())[2],
                                (String) ((Object[]) objectMessage.getObject())[3],
                                (Integer) ((Object[]) objectMessage.getObject())[4],
                                (Usuario) ((Object[]) objectMessage.getObject())[5],
                                (Integer) ((Object[]) objectMessage.getObject())[6],
                                (ArrecadadorContrato) ((Object[]) objectMessage.getObject())[7],
                                (Collection) ((Object[]) objectMessage.getObject())[8],
                                (Integer) ((Object[]) objectMessage.getObject())[9]
                                
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

    
    private ControladorArrecadacaoLocal getControladorArrecadacao() {
        ControladorArrecadacaoLocalHome localHome = null;
        ControladorArrecadacaoLocal local = null;

        // pega a instância do ServiceLocator.

        ServiceLocator locator = null;

        try {
            locator = ServiceLocator.getInstancia();

            localHome = (ControladorArrecadacaoLocalHome) locator
                    .getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
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
}
