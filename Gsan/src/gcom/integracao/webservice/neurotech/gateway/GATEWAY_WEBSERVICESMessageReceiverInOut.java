//
///**
// * GATEWAY_WEBSERVICESMessageReceiverInOut.java
// *
// * This file was auto-generated from WSDL
// * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
// */
//        package gcom.integracao.webservice.neurotech.gateway;
//
//        /**
//        *  GATEWAY_WEBSERVICESMessageReceiverInOut message receiver
//        */
//
//        public class GATEWAY_WEBSERVICESMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{
//
//
//        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
//        throws org.apache.axis2.AxisFault{
//
//        try {
//
//        // get the implementation class for the Web Service
//        Object obj = getTheImplementationObject(msgContext);
//
//        GATEWAY_WEBSERVICESSkeleton skel = (GATEWAY_WEBSERVICESSkeleton)obj;
//        //Out Envelop
//        org.apache.axiom.soap.SOAPEnvelope envelope = null;
//        //Find the axisOperation that has been set by the Dispatch phase.
//        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
//        if (op == null) {
//        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
//        }
//
//        java.lang.String methodName;
//        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){
//
//
//        
//
//            if("consultarHistoricoComparacaoFonetica".equals(methodName)){
//                
//                gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument consultarHistoricoComparacaoFoneticaResponse1 = null;
//	                        gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaDocument wrappedParam =
//                                                             (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaDocument)fromOM(
//                                    msgContext.getEnvelope().getBody().getFirstElement(),
//                                    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaDocument.class,
//                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
//                                                
//                                               consultarHistoricoComparacaoFoneticaResponse1 =
//                                                   
//                                                   
//                                                         skel.consultarHistoricoComparacaoFonetica(wrappedParam)
//                                                    ;
//                                            
//                                        envelope = toEnvelope(getSOAPFactory(msgContext), consultarHistoricoComparacaoFoneticaResponse1, false, new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br",
//                                                    "consultarHistoricoComparacaoFonetica"));
//                                    } else 
//
//            if("wFtoHTML".equals(methodName)){
//                
//                gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument wFtoHTMLResponse3 = null;
//	                        gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument wrappedParam =
//                                                             (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument)fromOM(
//                                    msgContext.getEnvelope().getBody().getFirstElement(),
//                                    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.class,
//                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
//                                                
//                                               wFtoHTMLResponse3 =
//                                                   
//                                                   
//                                                         skel.wFtoHTML(wrappedParam)
//                                                    ;
//                                            
//                                        envelope = toEnvelope(getSOAPFactory(msgContext), wFtoHTMLResponse3, false, new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br",
//                                                    "wFtoHTML"));
//                                    } else 
//
//            if("consultarGatewayComparacaoFoneticaU".equals(methodName)){
//                
//                gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUResponseDocument consultarGatewayComparacaoFoneticaUResponse5 = null;
//	                        gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUDocument wrappedParam =
//                                                             (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUDocument)fromOM(
//                                    msgContext.getEnvelope().getBody().getFirstElement(),
//                                    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUDocument.class,
//                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
//                                                
//                                               consultarGatewayComparacaoFoneticaUResponse5 =
//                                                   
//                                                   
//                                                         skel.consultarGatewayComparacaoFoneticaU(wrappedParam)
//                                                    ;
//                                            
//                                        envelope = toEnvelope(getSOAPFactory(msgContext), consultarGatewayComparacaoFoneticaUResponse5, false, new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br",
//                                                    "consultarGatewayComparacaoFoneticaU"));
//                                    } else 
//
//            if("consultarHistoricoComparacaoFonetica2".equals(methodName)){
//                
//                gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument consultarHistoricoComparacaoFonetica2Response7 = null;
//	                        gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document wrappedParam =
//                                                             (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document)fromOM(
//                                    msgContext.getEnvelope().getBody().getFirstElement(),
//                                    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.class,
//                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
//                                                
//                                               consultarHistoricoComparacaoFonetica2Response7 =
//                                                   
//                                                   
//                                                         skel.consultarHistoricoComparacaoFonetica2(wrappedParam)
//                                                    ;
//                                            
//                                        envelope = toEnvelope(getSOAPFactory(msgContext), consultarHistoricoComparacaoFonetica2Response7, false, new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br",
//                                                    "consultarHistoricoComparacaoFonetica2"));
//                                    } else 
//
//            if("atualizarCache".equals(methodName)){
//                
//                gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument atualizarCacheResponse9 = null;
//	                        gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument wrappedParam =
//                                                             (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument)fromOM(
//                                    msgContext.getEnvelope().getBody().getFirstElement(),
//                                    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.class,
//                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
//                                                
//                                               atualizarCacheResponse9 =
//                                                   
//                                                   
//                                                         skel.atualizarCache(wrappedParam)
//                                                    ;
//                                            
//                                        envelope = toEnvelope(getSOAPFactory(msgContext), atualizarCacheResponse9, false, new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br",
//                                                    "atualizarCache"));
//                                    } else 
//
//            if("consultar".equals(methodName)){
//                
//                gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarResponseDocument consultarResponse11 = null;
//	                        gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarDocument wrappedParam =
//                                                             (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarDocument)fromOM(
//                                    msgContext.getEnvelope().getBody().getFirstElement(),
//                                    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarDocument.class,
//                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
//                                                
//                                               consultarResponse11 =
//                                                   
//                                                   
//                                                         skel.consultar(wrappedParam)
//                                                    ;
//                                            
//                                        envelope = toEnvelope(getSOAPFactory(msgContext), consultarResponse11, false, new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br",
//                                                    "consultar"));
//                                    } else 
//
//            if("consultarGatewayComparacaoFonetica2".equals(methodName)){
//                
//                gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument consultarGatewayComparacaoFonetica2Response13 = null;
//	                        gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2Document wrappedParam =
//                                                             (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2Document)fromOM(
//                                    msgContext.getEnvelope().getBody().getFirstElement(),
//                                    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2Document.class,
//                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
//                                                
//                                               consultarGatewayComparacaoFonetica2Response13 =
//                                                   
//                                                   
//                                                         skel.consultarGatewayComparacaoFonetica2(wrappedParam)
//                                                    ;
//                                            
//                                        envelope = toEnvelope(getSOAPFactory(msgContext), consultarGatewayComparacaoFonetica2Response13, false, new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br",
//                                                    "consultarGatewayComparacaoFonetica2"));
//                                    } else 
//
//            if("consultarLog".equals(methodName)){
//                
//                gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument consultarLogResponse15 = null;
//	                        gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument wrappedParam =
//                                                             (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument)fromOM(
//                                    msgContext.getEnvelope().getBody().getFirstElement(),
//                                    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.class,
//                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
//                                                
//                                               consultarLogResponse15 =
//                                                   
//                                                   
//                                                         skel.consultarLog(wrappedParam)
//                                                    ;
//                                            
//                                        envelope = toEnvelope(getSOAPFactory(msgContext), consultarLogResponse15, false, new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br",
//                                                    "consultarLog"));
//                                    } else 
//
//            if("consultarHistorico".equals(methodName)){
//                
//                gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoResponseDocument consultarHistoricoResponse17 = null;
//	                        gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoDocument wrappedParam =
//                                                             (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoDocument)fromOM(
//                                    msgContext.getEnvelope().getBody().getFirstElement(),
//                                    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoDocument.class,
//                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
//                                                
//                                               consultarHistoricoResponse17 =
//                                                   
//                                                   
//                                                         skel.consultarHistorico(wrappedParam)
//                                                    ;
//                                            
//                                        envelope = toEnvelope(getSOAPFactory(msgContext), consultarHistoricoResponse17, false, new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br",
//                                                    "consultarHistorico"));
//                                    } else 
//
//            if("consultarGateway".equals(methodName)){
//                
//                gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayResponseDocument consultarGatewayResponse19 = null;
//	                        gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument wrappedParam =
//                                                             (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument)fromOM(
//                                    msgContext.getEnvelope().getBody().getFirstElement(),
//                                    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.class,
//                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
//                                                
//                                               consultarGatewayResponse19 =
//                                                   
//                                                   
//                                                         skel.consultarGateway(wrappedParam)
//                                                    ;
//                                            
//                                        envelope = toEnvelope(getSOAPFactory(msgContext), consultarGatewayResponse19, false, new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br",
//                                                    "consultarGateway"));
//                                    } else 
//
//            if("consultarGatewayComparacaoFonetica".equals(methodName)){
//                
//                gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument consultarGatewayComparacaoFoneticaResponse21 = null;
//	                        gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaDocument wrappedParam =
//                                                             (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaDocument)fromOM(
//                                    msgContext.getEnvelope().getBody().getFirstElement(),
//                                    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaDocument.class,
//                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
//                                                
//                                               consultarGatewayComparacaoFoneticaResponse21 =
//                                                   
//                                                   
//                                                         skel.consultarGatewayComparacaoFonetica(wrappedParam)
//                                                    ;
//                                            
//                                        envelope = toEnvelope(getSOAPFactory(msgContext), consultarGatewayComparacaoFoneticaResponse21, false, new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br",
//                                                    "consultarGatewayComparacaoFonetica"));
//                                    
//            } else {
//              throw new java.lang.RuntimeException("method not found");
//            }
//        
//
//        newMsgContext.setEnvelope(envelope);
//        }
//        }
//        catch (java.lang.Exception e) {
//        throw org.apache.axis2.AxisFault.makeFault(e);
//        }
//        }
//        
//        //
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
////            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument param)
////                    throws org.apache.axis2.AxisFault {
////
////                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
////                xmlOptions.setSaveNoXmlDecl();
////                xmlOptions.setSaveAggressiveNamespaces();
////                xmlOptions.setSaveNamespacesFirst();
////                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
////                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
////                try {
////                    return builder.getDocumentElement(true);
////                } catch (java.lang.Exception e) {
////                    throw org.apache.axis2.AxisFault.makeFault(e);
////                }
////            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUResponseDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUResponseDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarResponseDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarResponseDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2Document param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2Document param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoResponseDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoResponseDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayResponseDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayResponseDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//
//            private  org.apache.axiom.om.OMElement  toOM(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument param, boolean optimizeContent)
//            throws org.apache.axis2.AxisFault{
//
//            
//                    return toOM(param);
//                
//
//            }
//
//            private org.apache.axiom.om.OMElement toOM(final gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument param)
//                    throws org.apache.axis2.AxisFault {
//
//                org.apache.xmlbeans.XmlOptions xmlOptions = new org.apache.xmlbeans.XmlOptions();
//                xmlOptions.setSaveNoXmlDecl();
//                xmlOptions.setSaveAggressiveNamespaces();
//                xmlOptions.setSaveNamespacesFirst();
//                org.apache.axiom.om.OMXMLParserWrapper builder = org.apache.axiom.om.OMXMLBuilderFactory.createOMBuilder(
//                        new javax.xml.transform.sax.SAXSource(new org.apache.axis2.xmlbeans.XmlBeansXMLReader(param, xmlOptions), new org.xml.sax.InputSource()));
//                try {
//                    return builder.getDocumentElement(true);
//                } catch (java.lang.Exception e) {
//                    throw org.apache.axis2.AxisFault.makeFault(e);
//                }
//            }
//        
//                            private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
//                            throws org.apache.axis2.AxisFault {
//                            org.apache.axiom.soap.SOAPEnvelope envelope = factory.getDefaultEnvelope();
//                            if (param != null){
//                            envelope.getBody().addChild(toOM(param, optimizeContent));
//                            }
//                            return envelope;
//                            }
//                        
//                            private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
//                            throws org.apache.axis2.AxisFault {
//                            org.apache.axiom.soap.SOAPEnvelope envelope = factory.getDefaultEnvelope();
//                            if (param != null){
//                            envelope.getBody().addChild(toOM(param, optimizeContent));
//                            }
//                            return envelope;
//                            }
//                        
//                            private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUResponseDocument param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
//                            throws org.apache.axis2.AxisFault {
//                            org.apache.axiom.soap.SOAPEnvelope envelope = factory.getDefaultEnvelope();
//                            if (param != null){
//                            envelope.getBody().addChild(toOM(param, optimizeContent));
//                            }
//                            return envelope;
//                            }
//                        
//                            private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
//                            throws org.apache.axis2.AxisFault {
//                            org.apache.axiom.soap.SOAPEnvelope envelope = factory.getDefaultEnvelope();
//                            if (param != null){
//                            envelope.getBody().addChild(toOM(param, optimizeContent));
//                            }
//                            return envelope;
//                            }
//                        
//                            private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
//                            throws org.apache.axis2.AxisFault {
//                            org.apache.axiom.soap.SOAPEnvelope envelope = factory.getDefaultEnvelope();
//                            if (param != null){
//                            envelope.getBody().addChild(toOM(param, optimizeContent));
//                            }
//                            return envelope;
//                            }
//                        
//                            private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarResponseDocument param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
//                            throws org.apache.axis2.AxisFault {
//                            org.apache.axiom.soap.SOAPEnvelope envelope = factory.getDefaultEnvelope();
//                            if (param != null){
//                            envelope.getBody().addChild(toOM(param, optimizeContent));
//                            }
//                            return envelope;
//                            }
//                        
//                            private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
//                            throws org.apache.axis2.AxisFault {
//                            org.apache.axiom.soap.SOAPEnvelope envelope = factory.getDefaultEnvelope();
//                            if (param != null){
//                            envelope.getBody().addChild(toOM(param, optimizeContent));
//                            }
//                            return envelope;
//                            }
//                        
//                            private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
//                            throws org.apache.axis2.AxisFault {
//                            org.apache.axiom.soap.SOAPEnvelope envelope = factory.getDefaultEnvelope();
//                            if (param != null){
//                            envelope.getBody().addChild(toOM(param, optimizeContent));
//                            }
//                            return envelope;
//                            }
//                        
//                            private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoResponseDocument param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
//                            throws org.apache.axis2.AxisFault {
//                            org.apache.axiom.soap.SOAPEnvelope envelope = factory.getDefaultEnvelope();
//                            if (param != null){
//                            envelope.getBody().addChild(toOM(param, optimizeContent));
//                            }
//                            return envelope;
//                            }
//                        
//                            private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayResponseDocument param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
//                            throws org.apache.axis2.AxisFault {
//                            org.apache.axiom.soap.SOAPEnvelope envelope = factory.getDefaultEnvelope();
//                            if (param != null){
//                            envelope.getBody().addChild(toOM(param, optimizeContent));
//                            }
//                            return envelope;
//                            }
//                        
//                            private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
//                            throws org.apache.axis2.AxisFault {
//                            org.apache.axiom.soap.SOAPEnvelope envelope = factory.getDefaultEnvelope();
//                            if (param != null){
//                            envelope.getBody().addChild(toOM(param, optimizeContent));
//                            }
//                            return envelope;
//                            }
//                        
//
//
//        /**
//        *  get the default envelope
//        */
//        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
//        return factory.getDefaultEnvelope();
//        }
//
//        public org.apache.xmlbeans.XmlObject fromOM(
//        org.apache.axiom.om.OMElement param,
//        java.lang.Class type,
//        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{
//        try{
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUResponseDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaUResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarResponseDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2Document.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2Document.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2Document.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoResponseDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayResponseDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//
//            if (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.class.equals(type)){
//            if (extraNamespaces!=null){
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching(),
//            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
//            }else{
//            return gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.Factory.parse(
//            param.getXMLStreamReaderWithoutCaching());
//            }
//            }
//
//        
//        }catch(java.lang.Exception e){
//        throw org.apache.axis2.AxisFault.makeFault(e);
//        }
//        return null;
//        }
//
//        
//        
//
//        /**
//        *  A utility method that copies the namepaces from the SOAPEnvelope
//        */
//        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
//        java.util.Map returnMap = new java.util.HashMap();
//        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
//        while (namespaceIterator.hasNext()) {
//        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
//        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
//        }
//        return returnMap;
//        }
//
//        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
//        org.apache.axis2.AxisFault f;
//        Throwable cause = e.getCause();
//        if (cause != null) {
//            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
//        } else {
//            f = new org.apache.axis2.AxisFault(e.getMessage());
//        }
//
//        return f;
//    }
//
//        }//end of class
//    