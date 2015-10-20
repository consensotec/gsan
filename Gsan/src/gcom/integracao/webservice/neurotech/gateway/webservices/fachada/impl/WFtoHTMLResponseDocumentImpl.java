///*
// * An XML document type.
// * Localname: WFtoHTMLResponse
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada.impl;
///**
// * A document containing one WFtoHTMLResponse(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public class WFtoHTMLResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument
//{
//    
//    public WFtoHTMLResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName WFTOHTMLRESPONSE$0 = 
//        new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "WFtoHTMLResponse");
//    
//    
//    /**
//     * Gets the "WFtoHTMLResponse" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument.WFtoHTMLResponse getWFtoHTMLResponse()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument.WFtoHTMLResponse target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument.WFtoHTMLResponse)get_store().find_element_user(WFTOHTMLRESPONSE$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Sets the "WFtoHTMLResponse" element
//     */
//    public void setWFtoHTMLResponse(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument.WFtoHTMLResponse wFtoHTMLResponse)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument.WFtoHTMLResponse target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument.WFtoHTMLResponse)get_store().find_element_user(WFTOHTMLRESPONSE$0, 0);
//            if (target == null)
//            {
//                target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument.WFtoHTMLResponse)get_store().add_element_user(WFTOHTMLRESPONSE$0);
//            }
//            target.set(wFtoHTMLResponse);
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty "WFtoHTMLResponse" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument.WFtoHTMLResponse addNewWFtoHTMLResponse()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument.WFtoHTMLResponse target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument.WFtoHTMLResponse)get_store().add_element_user(WFTOHTMLRESPONSE$0);
//            return target;
//        }
//    }
//    /**
//     * An XML WFtoHTMLResponse(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public static class WFtoHTMLResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLResponseDocument.WFtoHTMLResponse
//    {
//        
//        public WFtoHTMLResponseImpl(org.apache.xmlbeans.SchemaType sType)
//        {
//            super(sType);
//        }
//        
//        private static final javax.xml.namespace.QName RETURN$0 = 
//            new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "return");
//        
//        
//        /**
//         * Gets the "return" element
//         */
//        public gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML getReturn()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML target = null;
//                target = (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML)get_store().find_element_user(RETURN$0, 0);
//                if (target == null)
//                {
//                    return null;
//                }
//                return target;
//            }
//        }
//        
//        /**
//         * Tests for nil "return" element
//         */
//        public boolean isNilReturn()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML target = null;
//                target = (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML)get_store().find_element_user(RETURN$0, 0);
//                if (target == null) return false;
//                return target.isNil();
//            }
//        }
//        
//        /**
//         * True if has "return" element
//         */
//        public boolean isSetReturn()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                return get_store().count_elements(RETURN$0) != 0;
//            }
//        }
//        
//        /**
//         * Sets the "return" element
//         */
//        public void setReturn(gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML xreturn)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML target = null;
//                target = (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML)get_store().find_element_user(RETURN$0, 0);
//                if (target == null)
//                {
//                    target = (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML)get_store().add_element_user(RETURN$0);
//                }
//                target.set(xreturn);
//            }
//        }
//        
//        /**
//         * Appends and returns a new empty "return" element
//         */
//        public gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML addNewReturn()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML target = null;
//                target = (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML)get_store().add_element_user(RETURN$0);
//                return target;
//            }
//        }
//        
//        /**
//         * Nils the "return" element
//         */
//        public void setNilReturn()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML target = null;
//                target = (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML)get_store().find_element_user(RETURN$0, 0);
//                if (target == null)
//                {
//                    target = (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML)get_store().add_element_user(RETURN$0);
//                }
//                target.setNil();
//            }
//        }
//        
//        /**
//         * Unsets the "return" element
//         */
//        public void unsetReturn()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                get_store().remove_element(RETURN$0, 0);
//            }
//        }
//    }
//}
