package gsan.atualizacaocadastral;

import javax.ejb.CreateException;

/**
 * @author Bruno Sá Barreto
 * @date 24/02/2015
 */
public interface ControladorAtualizacaoCadastralLocalHome extends javax.ejb.EJBLocalHome{

	public ControladorAtualizacaoCadastralLocal create() throws CreateException;
	
}
