///*
// * An XML document type.
// * Localname: consultarHistoricoComparacaoFonetica2Response
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada.impl;
///**
// * A document containing one consultarHistoricoComparacaoFonetica2Response(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public class ConsultarHistoricoComparacaoFonetica2ResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument
//{
//    
//    public ConsultarHistoricoComparacaoFonetica2ResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName CONSULTARHISTORICOCOMPARACAOFONETICA2RESPONSE$0 = 
//        new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "consultarHistoricoComparacaoFonetica2Response");
//    
//    
//    /**
//     * Gets the "consultarHistoricoComparacaoFonetica2Response" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument.ConsultarHistoricoComparacaoFonetica2Response getConsultarHistoricoComparacaoFonetica2Response()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument.ConsultarHistoricoComparacaoFonetica2Response target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument.ConsultarHistoricoComparacaoFonetica2Response)get_store().find_element_user(CONSULTARHISTORICOCOMPARACAOFONETICA2RESPONSE$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Sets the "consultarHistoricoComparacaoFonetica2Response" element
//     */
//    public void setConsultarHistoricoComparacaoFonetica2Response(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument.ConsultarHistoricoComparacaoFonetica2Response consultarHistoricoComparacaoFonetica2Response)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument.ConsultarHistoricoComparacaoFonetica2Response target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument.ConsultarHistoricoComparacaoFonetica2Response)get_store().find_element_user(CONSULTARHISTORICOCOMPARACAOFONETICA2RESPONSE$0, 0);
//            if (target == null)
//            {
//                target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument.ConsultarHistoricoComparacaoFonetica2Response)get_store().add_element_user(CONSULTARHISTORICOCOMPARACAOFONETICA2RESPONSE$0);
//            }
//            target.set(consultarHistoricoComparacaoFonetica2Response);
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty "consultarHistoricoComparacaoFonetica2Response" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument.ConsultarHistoricoComparacaoFonetica2Response addNewConsultarHistoricoComparacaoFonetica2Response()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument.ConsultarHistoricoComparacaoFonetica2Response target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument.ConsultarHistoricoComparacaoFonetica2Response)get_store().add_element_user(CONSULTARHISTORICOCOMPARACAOFONETICA2RESPONSE$0);
//            return target;
//        }
//    }
//    /**
//     * An XML consultarHistoricoComparacaoFonetica2Response(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public static class ConsultarHistoricoComparacaoFonetica2ResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2ResponseDocument.ConsultarHistoricoComparacaoFonetica2Response
//    {
//        
//        public ConsultarHistoricoComparacaoFonetica2ResponseImpl(org.apache.xmlbeans.SchemaType sType)
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
