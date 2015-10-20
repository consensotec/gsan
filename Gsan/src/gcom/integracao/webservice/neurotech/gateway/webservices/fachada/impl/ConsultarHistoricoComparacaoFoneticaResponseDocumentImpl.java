///*
// * An XML document type.
// * Localname: consultarHistoricoComparacaoFoneticaResponse
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada.impl;
///**
// * A document containing one consultarHistoricoComparacaoFoneticaResponse(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public class ConsultarHistoricoComparacaoFoneticaResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument
//{
//    
//    public ConsultarHistoricoComparacaoFoneticaResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName CONSULTARHISTORICOCOMPARACAOFONETICARESPONSE$0 = 
//        new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "consultarHistoricoComparacaoFoneticaResponse");
//    
//    
//    /**
//     * Gets the "consultarHistoricoComparacaoFoneticaResponse" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse getConsultarHistoricoComparacaoFoneticaResponse()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse)get_store().find_element_user(CONSULTARHISTORICOCOMPARACAOFONETICARESPONSE$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Sets the "consultarHistoricoComparacaoFoneticaResponse" element
//     */
//    public void setConsultarHistoricoComparacaoFoneticaResponse(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse consultarHistoricoComparacaoFoneticaResponse)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse)get_store().find_element_user(CONSULTARHISTORICOCOMPARACAOFONETICARESPONSE$0, 0);
//            if (target == null)
//            {
//                target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse)get_store().add_element_user(CONSULTARHISTORICOCOMPARACAOFONETICARESPONSE$0);
//            }
//            target.set(consultarHistoricoComparacaoFoneticaResponse);
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty "consultarHistoricoComparacaoFoneticaResponse" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse addNewConsultarHistoricoComparacaoFoneticaResponse()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse)get_store().add_element_user(CONSULTARHISTORICOCOMPARACAOFONETICARESPONSE$0);
//            return target;
//        }
//    }
//    /**
//     * An XML consultarHistoricoComparacaoFoneticaResponse(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public static class ConsultarHistoricoComparacaoFoneticaResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse
//    {
//        
//        public ConsultarHistoricoComparacaoFoneticaResponseImpl(org.apache.xmlbeans.SchemaType sType)
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
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF getReturn()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF)get_store().find_element_user(RETURN$0, 0);
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
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF)get_store().find_element_user(RETURN$0, 0);
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
//        public void setReturn(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF xreturn)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF)get_store().find_element_user(RETURN$0, 0);
//                if (target == null)
//                {
//                    target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF)get_store().add_element_user(RETURN$0);
//                }
//                target.set(xreturn);
//            }
//        }
//        
//        /**
//         * Appends and returns a new empty "return" element
//         */
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF addNewReturn()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF)get_store().add_element_user(RETURN$0);
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
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF)get_store().find_element_user(RETURN$0, 0);
//                if (target == null)
//                {
//                    target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF)get_store().add_element_user(RETURN$0);
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
