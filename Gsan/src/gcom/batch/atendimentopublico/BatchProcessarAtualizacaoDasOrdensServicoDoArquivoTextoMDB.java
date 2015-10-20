package gcom.batch.atendimentopublico;

import java.util.Collection;

import gcom.atendimentopublico.ControladorAtendimentoPublicoLocal;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocalHome;
import gcom.atendimentopublico.bean.ProcessarAtualizacaoOSArquivoTextoHelper;
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

/**
 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
 *
 * @author Mariana Victor
 * @created 30/09/2011
 */
public class BatchProcessarAtualizacaoDasOrdensServicoDoArquivoTextoMDB implements MessageDrivenBean,
		MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchProcessarAtualizacaoDasOrdensServicoDoArquivoTextoMDB() {
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
            	
            	this.getControladorAtendimentoPublico().atualizarArquivoTxtOSVisitaCampo(
        			(Integer) ((Object[]) objectMessage.getObject())[0],
        			(Collection<ProcessarAtualizacaoOSArquivoTextoHelper>) 
        				((Object[]) objectMessage.getObject())[1],
        			(Usuario) ((Object[]) objectMessage.getObject())[2]
            	);

            
            } catch (ControladorException e) {
                System.out.println("Erro no MDB");
                e.printStackTrace();
            } catch (JMSException e) {
                System.out.println("Erro no MDB");
                e.printStackTrace();
			}
        }

    }

    
    private ControladorAtendimentoPublicoLocal getControladorAtendimentoPublico() {
		ControladorAtendimentoPublicoLocalHome localHome = null;
		ControladorAtendimentoPublicoLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorAtendimentoPublicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ATENDIMENTO_PUBLICO_SEJB);

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
