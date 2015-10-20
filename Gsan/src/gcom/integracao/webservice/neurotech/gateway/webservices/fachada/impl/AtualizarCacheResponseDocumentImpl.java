///*
// * An XML document type.
// * Localname: atualizarCacheResponse
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada.impl;
///**
// * A document containing one atualizarCacheResponse(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public class AtualizarCacheResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument
//{
//    
//    public AtualizarCacheResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName ATUALIZARCACHERESPONSE$0 = 
//        new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "atualizarCacheResponse");
//    
//    
//    /**
//     * Gets the "atualizarCacheResponse" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse getAtualizarCacheResponse()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse)get_store().find_element_user(ATUALIZARCACHERESPONSE$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Sets the "atualizarCacheResponse" element
//     */
//    public void setAtualizarCacheResponse(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse atualizarCacheResponse)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse)get_store().find_element_user(ATUALIZARCACHERESPONSE$0, 0);
//            if (target == null)
//            {
//                target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse)get_store().add_element_user(ATUALIZARCACHERESPONSE$0);
//            }
//            target.set(atualizarCacheResponse);
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty "atualizarCacheResponse" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse addNewAtualizarCacheResponse()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse)get_store().add_element_user(ATUALIZARCACHERESPONSE$0);
//            return target;
//        }
//    }
//    /**
//     * An XML atualizarCacheResponse(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public static class AtualizarCacheResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheResponseDocument.AtualizarCacheResponse
//    {
//        
//        public AtualizarCacheResponseImpl(org.apache.xmlbeans.SchemaType sType)
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
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS getReturn()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS)get_store().find_element_user(RETURN$0, 0);
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
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS)get_store().find_element_user(RETURN$0, 0);
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
//        public void setReturn(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS xreturn)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS)get_store().find_element_user(RETURN$0, 0);
//                if (target == null)
//                {
//                    target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS)get_store().add_element_user(RETURN$0);
//                }
//                target.set(xreturn);
//            }
//        }
//        
//        /**
//         * Appends and returns a new empty "return" element
//         */
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS addNewReturn()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS)get_store().add_element_user(RETURN$0);
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
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS)get_store().find_element_user(RETURN$0, 0);
//                if (target == null)
//                {
//                    target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS)get_store().add_element_user(RETURN$0);
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
