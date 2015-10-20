package gcom.batch.atualizacaocadastral;

import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocalHome;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.SistemaException;

import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * [UC0000] - Gerar Dados Financeiros Atualização Cadastral - Sintético
 * 
 * @author Vivianne Sousa
 * @date 27/07/2015
 */
public class BatchGerarResumoDadosFinanceirosAtualizacaoCadastralMDB implements MessageDrivenBean, MessageListener {
	private static final long serialVersionUID = 1L;

	public BatchGerarResumoDadosFinanceirosAtualizacaoCadastralMDB() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException { }

	public void ejbRemove() throws EJBException { }

    public void ejbCreate() { }

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;

			try {
				this.getControladorAtualizacaoCadastral().gerarResumoDadosFinanceirosAtualizacaoCadastral(
						(Integer) ((Object[]) objectMessage.getObject())[0],
						(Integer) ((Object[]) objectMessage.getObject())[1]);
			} catch (JMSException e) {
				System.out.println("ERRO NO MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("ERRO NO MDB");
				e.printStackTrace();
			}
		}
	}

	private ControladorAtualizacaoCadastralLocal getControladorAtualizacaoCadastral() {
		ControladorAtualizacaoCadastralLocalHome localHome = null;
		ControladorAtualizacaoCadastralLocal local = null;
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorAtualizacaoCadastralLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ATUALIZACAO_CADASTRAL_SEJB);

			local = localHome.create();
			return local;
		} catch (Exception e) {
			throw new SistemaException(e);
		}
	}
}
