//
///**
// * GATEWAY_WEBSERVICESCallbackHandler.java
// *
// * This file was auto-generated from WSDL
// * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
// */
//
//    package gcom.integracao.webservice.neurotech.gateway;
//
//    /**
//     *  GATEWAY_WEBSERVICESCallbackHandler Callback class, Users can extend this class and implement
//     *  their own receiveResult and receiveError methods.
//     */
//    public abstract class GATEWAY_WEBSERVICESCallbackHandler{
//
//
//
//    protected Object clientData;
//
//    /**
//    * User can pass in any object that needs to be accessed once the NonBlocking
//    * Web service call is finished and appropriate method of this CallBack is called.
//    * @param clientData Object mechanism by which the user can pass in user data
//    * that will be avilable at the time this callback is called.
//    */
//    public GATEWAY_WEBSERVICESCallbackHandler(Object clientData){
//        this.clientData = clientData;
//    }
//
//    /**
//    * Please use this constructor if you don't want to set any clientData
//    */
//    public GATEWAY_WEBSERVICESCallbackHandler(){
//        this.clientData = null;
//    }
//
//    /**
//     * Get the client data
//     */
//
//     public Object getClientData() {
//        return clientData;
//     }
//
//        
//           /**
//            * auto generated Axis2 call back method for consultarHistoricoComparacaoFonetica method
//            * override this method for handling normal response from consultarHistoricoComparacaoFonetica operation
//            */
//           public void receiveResultconsultarHistoricoComparacaoFonetica(
//                    gcom.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.ConsultarHistoricoComparacaoFoneticaResponse result
//                        ) {
//           }
//
//          /**
//           * auto generated Axis2 Error handler
//           * override this method for handling error response from consultarHistoricoComparacaoFonetica operation
//           */
//            public void receiveErrorconsultarHistoricoComparacaoFonetica(java.lang.Exception e) {
//            }
//                
//           /**
//            * auto generated Axis2 call back method for wFtoHTML method
//            * override this method for handling normal response from wFtoHTML operation
//            */
//           public void receiveResultwFtoHTML(
//                    gcom.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.WFtoHTMLResponse result
//                        ) {
//           }
//
//          /**
//           * auto generated Axis2 Error handler
//           * override this method for handling error response from wFtoHTML operation
//           */
//            public void receiveErrorwFtoHTML(java.lang.Exception e) {
//            }
//                
//           /**
//            * auto generated Axis2 call back method for consultarGatewayComparacaoFoneticaU method
//            * override this method for handling normal response from consultarGatewayComparacaoFoneticaU operation
//            */
//           public void receiveResultconsultarGatewayComparacaoFoneticaU(
//                    gcom.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.ConsultarGatewayComparacaoFoneticaUResponse result
//                        ) {
//           }
//
//          /**
//           * auto generated Axis2 Error handler
//           * override this method for handling error response from consultarGatewayComparacaoFoneticaU operation
//           */
//            public void receiveErrorconsultarGatewayComparacaoFoneticaU(java.lang.Exception e) {
//            }
//                
//           /**
//            * auto generated Axis2 call back method for consultarHistoricoComparacaoFonetica2 method
//            * override this method for handling normal response from consultarHistoricoComparacaoFonetica2 operation
//            */
//           public void receiveResultconsultarHistoricoComparacaoFonetica2(
//                    gcom.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.ConsultarHistoricoComparacaoFonetica2Response result
//                        ) {
//           }
//
//          /**
//           * auto generated Axis2 Error handler
//           * override this method for handling error response from consultarHistoricoComparacaoFonetica2 operation
//           */
//            public void receiveErrorconsultarHistoricoComparacaoFonetica2(java.lang.Exception e) {
//            }
//                
//           /**
//            * auto generated Axis2 call back method for atualizarCache method
//            * override this method for handling normal response from atualizarCache operation
//            */
//           public void receiveResultatualizarCache(
//                    gcom.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.AtualizarCacheResponse result
//                        ) {
//           }
//
//          /**
//           * auto generated Axis2 Error handler
//           * override this method for handling error response from atualizarCache operation
//           */
//            public void receiveErroratualizarCache(java.lang.Exception e) {
//            }
//                
//           /**
//            * auto generated Axis2 call back method for consultar method
//            * override this method for handling normal response from consultar operation
//            */
//           public void receiveResultconsultar(
//                    gcom.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.ConsultarResponse result
//                        ) {
//           }
//
//          /**
//           * auto generated Axis2 Error handler
//           * override this method for handling error response from consultar operation
//           */
//            public void receiveErrorconsultar(java.lang.Exception e) {
//            }
//                
//           /**
//            * auto generated Axis2 call back method for consultarGatewayComparacaoFonetica2 method
//            * override this method for handling normal response from consultarGatewayComparacaoFonetica2 operation
//            */
//           public void receiveResultconsultarGatewayComparacaoFonetica2(
//                    gcom.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.ConsultarGatewayComparacaoFonetica2Response result
//                        ) {
//           }
//
//          /**
//           * auto generated Axis2 Error handler
//           * override this method for handling error response from consultarGatewayComparacaoFonetica2 operation
//           */
//            public void receiveErrorconsultarGatewayComparacaoFonetica2(java.lang.Exception e) {
//            }
//                
//           /**
//            * auto generated Axis2 call back method for consultarLog method
//            * override this method for handling normal response from consultarLog operation
//            */
//           public void receiveResultconsultarLog(
//                    gcom.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.ConsultarLogResponse result
//                        ) {
//           }
//
//          /**
//           * auto generated Axis2 Error handler
//           * override this method for handling error response from consultarLog operation
//           */
//            public void receiveErrorconsultarLog(java.lang.Exception e) {
//            }
//                
//           /**
//            * auto generated Axis2 call back method for consultarHistorico method
//            * override this method for handling normal response from consultarHistorico operation
//            */
//           public void receiveResultconsultarHistorico(
//                    gcom.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.ConsultarHistoricoResponse result
//                        ) {
//           }
//
//          /**
//           * auto generated Axis2 Error handler
//           * override this method for handling error response from consultarHistorico operation
//           */
//            public void receiveErrorconsultarHistorico(java.lang.Exception e) {
//            }
//                
//           /**
//            * auto generated Axis2 call back method for consultarGateway method
//            * override this method for handling normal response from consultarGateway operation
//            */
//           public void receiveResultconsultarGateway(
//                    gcom.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.ConsultarGatewayResponse result
//                        ) {
//           }
//
//          /**
//           * auto generated Axis2 Error handler
//           * override this method for handling error response from consultarGateway operation
//           */
//            public void receiveErrorconsultarGateway(java.lang.Exception e) {
//            }
//                
//           /**
//            * auto generated Axis2 call back method for consultarGatewayComparacaoFonetica method
//            * override this method for handling normal response from consultarGatewayComparacaoFonetica operation
//            */
//           public void receiveResultconsultarGatewayComparacaoFonetica(
//                    gcom.integracao.webservice.neurotech.gateway.GATEWAY_WEBSERVICESStub.ConsultarGatewayComparacaoFoneticaResponse result
//                        ) {
//           }
//
//          /**
//           * auto generated Axis2 Error handler
//           * override this method for handling error response from consultarGatewayComparacaoFonetica operation
//           */
//            public void receiveErrorconsultarGatewayComparacaoFonetica(java.lang.Exception e) {
//            }
//                
//
//
//    }
//    