package gcom.gui.portal.comum.gateway;

import gcom.gui.portal.comum.gateway.pojo.BraspagPagamento;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Serviço para consultas via webservice com a Braspag.
 * 
 * @author André Miranda
 */
public interface BraspagServiceQuery {
	@GET("/sales/{id}")
	BraspagPagamento getPagamento(@Path("id") String id);
}
