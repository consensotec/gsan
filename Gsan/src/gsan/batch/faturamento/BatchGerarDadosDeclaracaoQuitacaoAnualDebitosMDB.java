package gsan.batch.faturamento;

import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.faturamento.ControladorFaturamentoLocal;
import gsan.faturamento.ControladorFaturamentoLocalHome;
import gsan.micromedicao.Rota;
import gsan.util.ConstantesJNDI;
import gsan.util.ControladorException;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;

import java.util.Collection;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * [UC1001] Emitir declaração de quitação anual de débitos
 * 
 * 	Este caso de uso permite a geração de declaração de quitação de débitos.
 * 
 * @author Hugo Amorim
 * @date 17/03/2010
 */
public class BatchGerarDadosDeclaracaoQuitacaoAnualDebitosMDB implements
		MessageDrivenBean, MessageListener {


	private static final long serialVersionUID = 1L;
	
	public BatchGerarDadosDeclaracaoQuitacaoAnualDebitosMDB() {
		super();
	}	
	
	public void onMessage(Message message) {
		
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			
			try {
								
						this.getControladorFaturamento()
								.gerarDadosDeclaracaoQuitacaoAnualDebitos(
									(Integer) ((Object[]) objectMessage.getObject())[0], //ID FUNCIONALIDADE INICIADA
									(Collection<Integer>) ((Object[]) objectMessage.getObject())[1], //ANO
									(Rota) ((Object[]) objectMessage.getObject())[2],//ROTA
									(Short) ((Object[]) objectMessage.getObject())[3],//INDICADOR CONTA PARCELADA
									(Short) ((Object[]) objectMessage.getObject())[4],//INDICADOR COBRANCA JUDICIAL
									(Date) ((Object[]) objectMessage.getObject())[5],//DATA VERIFICACAO PAGAMENTOS
									(SistemaParametro) ((Object[]) objectMessage.getObject())[6]);				
				

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
	}

	public void setMessageDrivenContext(MessageDrivenContext arg0)
			throws EJBException {

	}
	/**
	 * Retorna o valor de controladorFaturamento
	 * 
	 * @return O valor de controladorFaturamento
	 */
	protected ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
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
	
	public void ejbCreate() {

	}

}
