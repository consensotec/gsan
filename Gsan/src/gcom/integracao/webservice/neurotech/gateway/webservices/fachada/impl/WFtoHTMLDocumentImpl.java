///*
// * An XML document type.
// * Localname: WFtoHTML
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada.impl;
///**
// * A document containing one WFtoHTML(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public class WFtoHTMLDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument
//{
//    
//    public WFtoHTMLDocumentImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName WFTOHTML$0 = 
//        new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "WFtoHTML");
//    
//    
//    /**
//     * Gets the "WFtoHTML" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML getWFtoHTML()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML)get_store().find_element_user(WFTOHTML$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Sets the "WFtoHTML" element
//     */
//    public void setWFtoHTML(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML wFtoHTML)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML)get_store().find_element_user(WFTOHTML$0, 0);
//            if (target == null)
//            {
//                target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML)get_store().add_element_user(WFTOHTML$0);
//            }
//            target.set(wFtoHTML);
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty "WFtoHTML" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML addNewWFtoHTML()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML)get_store().add_element_user(WFTOHTML$0);
//            return target;
//        }
//    }
//    /**
//     * An XML WFtoHTML(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public static class WFtoHTMLImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML
//    {
//        
//        public WFtoHTMLImpl(org.apache.xmlbeans.SchemaType sType)
//        {
//            super(sType);
//        }
//        
//        private static final javax.xml.namespace.QName POPERATION$0 = 
//            new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "pOperation");
//        
//        
//        /**
//         * Gets the "pOperation" element
//         */
//        public gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation getPOperation()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation target = null;
//                target = (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation)get_store().find_element_user(POPERATION$0, 0);
//                if (target == null)
//                {
//                    return null;
//                }
//                return target;
//            }
//        }
//        
//        /**
//         * Tests for nil "pOperation" element
//         */
//        public boolean isNilPOperation()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation target = null;
//                target = (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation)get_store().find_element_user(POPERATION$0, 0);
//                if (target == null) return false;
//                return target.isNil();
//            }
//        }
//        
//        /**
//         * True if has "pOperation" element
//         */
//        public boolean isSetPOperation()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                return get_store().count_elements(POPERATION$0) != 0;
//            }
//        }
//        
//        /**
//         * Sets the "pOperation" element
//         */
//        public void setPOperation(gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation pOperation)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation target = null;
//                target = (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation)get_store().find_element_user(POPERATION$0, 0);
//                if (target == null)
//                {
//                    target = (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation)get_store().add_element_user(POPERATION$0);
//                }
//                target.set(pOperation);
//            }
//        }
//        
//        /**
//         * Appends and returns a new empty "pOperation" element
//         */
//        public gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation addNewPOperation()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation target = null;
//                target = (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation)get_store().add_element_user(POPERATION$0);
//                return target;
//            }
//        }
//        
//        /**
//         * Nils the "pOperation" element
//         */
//        public void setNilPOperation()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation target = null;
//                target = (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation)get_store().find_element_user(POPERATION$0, 0);
//                if (target == null)
//                {
//                    target = (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation)get_store().add_element_user(POPERATION$0);
//                }
//                target.setNil();
//            }
//        }
//        
//        /**
//         * Unsets the "pOperation" element
//         */
//        public void unsetPOperation()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                get_store().remove_element(POPERATION$0, 0);
//            }
//        }
//    }
//}
