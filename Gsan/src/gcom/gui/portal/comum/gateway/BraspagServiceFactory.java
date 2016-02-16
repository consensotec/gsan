package gcom.gui.portal.comum.gateway;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

/**
 * Classe respons�vel pela cria��o dos servi�os para integra��o via webservice
 * REST com a Braspag.
 * 
 * @author Andr� Miranda
 */
public class BraspagServiceFactory {
	private static final String ID = "9CA90E3B-C8B3-486A-9D2B-B46F56139F00";
	private static final String KEY = "ZPtcoWqasyVEYfcx65Fy6zqXN8DCqjgE7W85tKfS";

	public static BraspagService getService() {
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setRequestInterceptor(new RequestInterceptor() {
				public void intercept(RequestFacade request) {
					request.addHeader("MerchantId", ID);
					request.addHeader("MerchantKey", KEY);
				}
			})
			.setEndpoint("https://apihomolog.braspag.com.br/v2")
			.setLogLevel(LogLevel.FULL)
			.build();

		return restAdapter.create(BraspagService.class);
	}

	public static BraspagServiceQuery getServiceQuery() {
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setRequestInterceptor(new RequestInterceptor() {
				public void intercept(RequestFacade request) {
					request.addHeader("MerchantId", ID);
					request.addHeader("MerchantKey", KEY);
				}
			})
			.setEndpoint("https://apiqueryhomolog.braspag.com.br/v2")
			.build();

		return restAdapter.create(BraspagServiceQuery.class);
	}
}
