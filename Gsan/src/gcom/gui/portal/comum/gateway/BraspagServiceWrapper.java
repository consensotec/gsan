package gcom.gui.portal.comum.gateway;

import gcom.gui.portal.comum.gateway.pojo.BraspagCancelamento;
import gcom.gui.portal.comum.gateway.pojo.BraspagErro;
import gcom.util.ControladorException;
import gcom.util.Util;
import retrofit.RetrofitError;

/**
 * Serviço para requisições via webservice com a Braspag.
 * @author André Miranda
 */
public abstract class BraspagServiceWrapper {

	/**
	 * Realiza o cancelamento/estorno de um pagamento.
	 * 
	 * @param id Identificacao do pagamento
	 * @return retorno da operação de cancelamento
	 */
	public static BraspagCancelamento cancelar(String id) throws ControladorException {
		try {
			BraspagService service = BraspagServiceFactory.getService();
			return service.cancelar(id, "");
		} catch (RetrofitError e) {
			BraspagErro[] erros = (BraspagErro[]) e.getBodyAs(BraspagErro[].class);
			String detalhe = "";
			if (!Util.isVazioOrNulo(erros))
				detalhe = erros[0].toString();
			throw new ControladorException("atencao.erro_comunicacao_gateway_braspag", e, detalhe);
		}
	}
}
