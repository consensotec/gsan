package gcom.batch.faturamento;

import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
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

public class BatchGerarArquivoTextoContasProjetosEspeciaisMDB
	implements MessageListener, MessageDrivenBean{
	
	private static final long serialVersionUID = 1L;

	public BatchGerarArquivoTextoContasProjetosEspeciaisMDB() {
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
				
				String anoMes = (String) ((Object[]) objectMessage.getObject()) [0];
				
				Integer idCliente = (Integer)((Object[]) objectMessage.getObject()) [1];
				
				
				int idFuncionalidadeIniciada = (Integer)((Object[]) objectMessage.getObject()) [2];
				
				
													
					this.getControladorFaturamento()
						.gerarTxtContasProjetosEspeciais(
								anoMes,idCliente,idFuncionalidadeIniciada);
									

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
	 * Retorna o valor de controladorLocalidade
	 * 
	 * @return O valor de controladorLocalidade
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a instancia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �?
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
