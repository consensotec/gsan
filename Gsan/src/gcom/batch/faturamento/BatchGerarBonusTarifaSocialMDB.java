package gcom.batch.faturamento;

import java.util.Collection;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.FaturamentoGrupo;
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
 * UC0926 - Gerar B�nus de Tarifa Social
 * 
 * @author Hugo Amorim
 * @created 25/08/2009
 */
public class BatchGerarBonusTarifaSocialMDB implements MessageDrivenBean,
		MessageListener {
	
	private static final long serialVersionUID = 1L;
	
	public BatchGerarBonusTarifaSocialMDB() {
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
						.getControladorFaturamento()
						.gerarBonusTarifaSocial(
								(FaturamentoGrupo) ((Object[]) objectMessage
										.getObject())[0],
								(SistemaParametro) ((Object[]) objectMessage
										.getObject())[1],
								(Collection) ((Object[]) objectMessage
										.getObject())[2],
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
	
	protected ControladorFaturamentoLocal getControladorFaturamento() {

		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

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
	
	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() {

	}

}
