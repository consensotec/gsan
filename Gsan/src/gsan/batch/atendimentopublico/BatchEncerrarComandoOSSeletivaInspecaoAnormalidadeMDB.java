package gsan.batch.atendimentopublico;

import gsan.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gsan.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gsan.seguranca.acesso.usuario.Usuario;
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
 * [UC1197] Encerrar Comandos de OS Seletiva de Inspeção de Anormalidade
 *
 * @author Vivianne Sousa
 * @created 21/07/2011
 */
public class BatchEncerrarComandoOSSeletivaInspecaoAnormalidadeMDB implements MessageDrivenBean,
		MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchEncerrarComandoOSSeletivaInspecaoAnormalidadeMDB() {
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
            	
            	this.getControladorOrdemServico().encerrarComandoOSSeletivaInspecaoAnormalidade(
            			(Integer) ((Object[]) objectMessage.getObject())[0],
            			(Usuario) ((Object[]) objectMessage.getObject())[1],
            			(Integer) ((Object[]) objectMessage.getObject())[2],
            			(Short)((Object[]) objectMessage.getObject())[3]);

            } catch (JMSException e) {
                System.out.println("Erro no MDB");
                e.printStackTrace();
            } catch (ControladorException e) {
                System.out.println("Erro no MDB");
                e.printStackTrace();
            }
        }

    }

    
    private ControladorOrdemServicoLocal getControladorOrdemServico() {
		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorOrdemServicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);

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
