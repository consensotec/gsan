package gsan.batch.cobranca.cobrancaporresultado;

import gsan.cobranca.ControladorCobrancaLocal;
import gsan.cobranca.ControladorCobrancaLocalHome;
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
 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
 * 
 * @author Raimundo Martins
 * @date 14/12/2011
 * */
public class BatchRetirarImoveisContasEmpresaCobrancaMDB implements MessageDrivenBean,
MessageListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BatchRetirarImoveisContasEmpresaCobrancaMDB(){
		super();
	}

	public void onMessage(Message message) {
		 if (message instanceof ObjectMessage) {
			 ObjectMessage objectMessage = (ObjectMessage) message;
			 try{
				 Integer[] comandos = (Integer[]) ((Object[]) objectMessage.getObject())[2];
				 for(Integer i : comandos){
					 this.getControladorCobranca().retirarImoveisContasEmpresaCobranca(
						 (Integer) ((Object[]) objectMessage.getObject())[0], 
						 (Usuario) ((Object[]) objectMessage.getObject())[1],
						 i
						 );
				 }
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
		// TODO Auto-generated method stub
		
	}

	public void setMessageDrivenContext(MessageDrivenContext arg0) throws EJBException {
		// TODO Auto-generated method stub
		
	}
	
	 /**
     * Default create method
     * 
     * @throws CreateException
     */
    public void ejbCreate() {

    }
    
    /**
     * Retorna o valor do Controlador de Cobranca
	 * 
	 * @return O valor de controladorCobrancaLocal
	 */
	private ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a instância do ServiceLocator.

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
