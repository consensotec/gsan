package gcom.gui.portal.comum.gateway;

import gcom.gui.portal.comum.gateway.pojo.BraspagCancelamento;
import retrofit.http.Body;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Serviço para requisições via webservice com a Braspag.
 * 
 * @author André Miranda
 */
public interface BraspagService {
	public static final Integer STATUS_NAO_CONCLUIDO = 0;
	public static final Integer STATUS_AUTORIZADO = 1;
	public static final Integer STATUS_PGTO_CONFIRMADO = 2;
	public static final Integer STATUS_NEGADO = 3;
	public static final Integer STATUS_CANCELADO = 10;
	public static final Integer STATUS_REEMBOLSADO = 11;
	public static final Integer STATUS_PENDENTE = 12;
	public static final Integer STATUS_ABORTADO = 13;
	public static final Integer STATUS_AGENDADO = 20;

	public static final Integer RETORNO_SUCESSO = 0;

	/**
	 * Realiza o cancelamento/estorno de um pagamento.
	 * 
	 * @param id Identificacao do pagamento
	 * @param body
	 * @return retorno da operação de cancelamento
	 */
	@PUT("/sales/{id}/void")
	BraspagCancelamento cancelar(@Path("id") String id, @Body String body);
}
