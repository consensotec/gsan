/**
 * Classe de exemplo de utilização do WEBSERVICE.
 */

package gsan.integracao.webservice.neurotech.fachada;

import gsan.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub;
import gsan.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.CampoWS;
import gsan.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.ConsultaWS;
import gsan.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.ConsultarGateway;
import gsan.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.ConsultarGatewayResponse;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;

public class Exemplo {
	
	public static void main(String[] args) {
		
		try {
			//Criação do objeto stub
			GATEWAY_WEBSERVICESStub stub = new GATEWAY_WEBSERVICESStub();
			stub._getServiceClient().getOptions().setProperty(HTTPConstants.CHUNKED,
                    false);
			
			Options vOptions = stub._getServiceClient().getOptions();
			
			//Setando o timeout de conexão com o webservice
			vOptions.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(120000));
			vOptions.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(120000));
			
			//Autenticação do contratante
			ConsultarGateway consultarGateway = new ConsultarGateway();
			consultarGateway.setPIdentificador(35);
			consultarGateway.setPLogin("compesa");
			consultarGateway.setPSenha("compesa@neurotech");
			
			ConsultaWS[] consultaWSs = new ConsultaWS[1];
			 
			//Consulta da Receita Federal Pessoa Física
			ConsultaWS consultaWS = new ConsultaWS();
			consultaWS.setIdConsulta("RFPF_IMG");
//			consultaWS.setIdConsulta("RFPJ_IMG");
			CampoWS campoWS = new CampoWS();
			campoWS.setNmCampo("cpf");
//			campoWS.setNmCampo("cnpj");
			campoWS.setVlCampo("07676438446");
//			campoWS.setVlCampo("01854862000144");
			
			
			consultaWS.setLsParametros(new CampoWS[] {campoWS});
			consultaWSs[0] = consultaWS;
			
			consultarGateway.setPConsultas(consultaWSs);		
			ConsultarGatewayResponse consultarGatewayResponse = stub.consultarGateway(consultarGateway);
			if(consultarGatewayResponse.get_return()[0].getLsRetorno() != null){
				for(CampoWS retorno : consultarGatewayResponse.get_return()[0].getLsRetorno()){
					System.out.println(retorno.getNmCampo()+ ":  " +retorno.getVlCampo());
				}
			}
			System.out.println(consultarGatewayResponse.get_return()[0].getDsMensagem());
			System.out.println(consultarGatewayResponse.get_return()[0].getIdConsulta());
			System.out.println(consultarGatewayResponse.get_return()[0].getIdLog());
			System.out.println(consultarGatewayResponse.get_return()[0].getOrigemConsulta());
		
		} catch (AxisFault e) {		
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	}
	

}
