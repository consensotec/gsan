package gcom.batch.atualizacaocadastral;

import java.util.Collection;

import gcom.atualizacaocadastral.bean.ComandoAtualizacaoCadastralDMHelper;
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

/**
 * [UC 1391] - Gerar Roteiro Dispositivo Movel Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 23/11/2012
 *
 */
public class BatchGerarRoteiroDispositivoMovelMDB
		implements MessageDrivenBean, MessageListener {

	private static final long serialVersionUID = 1L;
	
	public BatchGerarRoteiroDispositivoMovelMDB(){
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {
		
	}

	public void ejbRemove() throws EJBException {
		
	}
	
	public void onMessage(Message message) {
		if(message instanceof ObjectMessage){
			ObjectMessage objectMessage = (ObjectMessage) message;
			
			try{
				this.getControladorCadastro()				
						.gerarRoteiroDispositivoMovel(
							(Integer) ((Object []) objectMessage.getObject())[0], 
							(Collection<String>) ((Object []) objectMessage.getObject())[1],
							(ComandoAtualizacaoCadastralDMHelper) ((Object []) objectMessage.getObject())[2]);
				
			}catch (JMSException e) {
				System.out.println("ERRO NO MDB");
				e.printStackTrace();
			}catch (ControladorException e){
				System.out.println("ERRO NO MDB");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Retorna o valor de ControladorCadastro
	 * 
	 * @return ControladorCadastro
	 */
	private ControladorCadastroLocal getControladorCadastro(){
		ControladorCadastroLocalHome localHome = null;
		ControladorCadastroLocal local = null;
		
		ServiceLocator locator = null;
		try{
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCadastroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);
			
			local = localHome.create();
			
			return local;
		}catch (CreateException ce) {
			throw new SistemaException(ce);
		}catch (ServiceLocatorException e){
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
