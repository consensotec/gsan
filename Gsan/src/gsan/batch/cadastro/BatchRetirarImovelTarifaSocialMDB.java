package gsan.batch.cadastro;

import gsan.cadastro.ControladorCadastroLocal;
import gsan.cadastro.ControladorCadastroLocalHome;
import gsan.cadastro.tarifasocial.TarifaSocialComandoCarta;
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
 * @author Vivianne Sousa
 * @date 01/04/2011
 */
public class BatchRetirarImovelTarifaSocialMDB implements
		MessageListener, MessageDrivenBean {

	private static final long serialVersionUID = 1L;

	public BatchRetirarImovelTarifaSocialMDB() {
		super();
	}

	public void ejbRemove() throws EJBException {

	}

	public void setMessageDrivenContext(MessageDrivenContext arg0)
			throws EJBException {

	}


	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			
			try {
				
				Integer rotina = (Integer) ((Object[]) objectMessage.getObject())[2];
				
				if(rotina.equals(new Integer(2))){
					//rotina Mensal
					this.getControladorCadastro().retirarImovelTarifaSocial(
							(Integer) ((Object[]) objectMessage.getObject())[0],
							(Integer) ((Object[]) objectMessage.getObject())[1]);
				}else{
					this.getControladorCadastro().retirarImovelTarifaSocial(
							(TarifaSocialComandoCarta) ((Object[]) objectMessage.getObject())[0],
							(Integer) ((Object[]) objectMessage.getObject())[1]);
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
	
	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() {

	}
	
	/**
	 * Retorna o controladorCadastro
	 * 
	 * @author Thiago Tenório
	 * @date 18/08/2006
	 * 
	 */
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
}
