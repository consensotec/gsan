///*
// * An XML document type.
// * Localname: consultarGatewayComparacaoFoneticaResponse
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada.impl;
///**
// * A document containing one consultarGatewayComparacaoFoneticaResponse(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public class ConsultarGatewayComparacaoFoneticaResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument
//{
//    
//    public ConsultarGatewayComparacaoFoneticaResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName CONSULTARGATEWAYCOMPARACAOFONETICARESPONSE$0 = 
//        new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "consultarGatewayComparacaoFoneticaResponse");
//    
//    
//    /**
//     * Gets the "consultarGatewayComparacaoFoneticaResponse" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse getConsultarGatewayComparacaoFoneticaResponse()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse)get_store().find_element_user(CONSULTARGATEWAYCOMPARACAOFONETICARESPONSE$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Sets the "consultarGatewayComparacaoFoneticaResponse" element
//     */
//    public void setConsultarGatewayComparacaoFoneticaResponse(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse consultarGatewayComparacaoFoneticaResponse)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse)get_store().find_element_user(CONSULTARGATEWAYCOMPARACAOFONETICARESPONSE$0, 0);
//            if (target == null)
//            {
//                target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse)get_store().add_element_user(CONSULTARGATEWAYCOMPARACAOFONETICARESPONSE$0);
//            }
//            target.set(consultarGatewayComparacaoFoneticaResponse);
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty "consultarGatewayComparacaoFoneticaResponse" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse addNewConsultarGatewayComparacaoFoneticaResponse()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse)get_store().add_element_user(CONSULTARGATEWAYCOMPARACAOFONETICARESPONSE$0);
//            return target;
//        }
//    }
//    /**
//     * An XML consultarGatewayComparacaoFoneticaResponse(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public static class ConsultarGatewayComparacaoFoneticaResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse
//    {
//        
//        public ConsultarGatewayComparacaoFoneticaResponseImpl(org.apache.xmlbeans.SchemaType sType)
//        {
//            super(sType);
//        }
//        
//        private static final javax.xml.namespace.QName RETURN$0 = 
//            new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "return");
//        
//        
//        /**
//         * Gets array of all "return" elements
//         */
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica[] getReturnArray()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                java.util.List targetList = new java.util.ArrayList();
//                get_store().find_all_element_users(RETURN$0, targetList);
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica[] result = new gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica[targetList.size()];
//                targetList.toArray(result);
//                return result;
//            }
//        }
//        
//        /**
//         * Gets ith "return" element
//         */
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica getReturnArray(int i)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica)get_store().find_element_user(RETURN$0, i);
//                if (target == null)
//                {
//                    throw new IndexOutOfBoundsException();
//                }
//                return target;
//            }
//        }
//        
//        /**
//         * Tests for nil ith "return" element
//         */
//        public boolean isNilReturnArray(int i)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica)get_store().find_element_user(RETURN$0, i);
//                if (target == null)
//                {
//                    throw new IndexOutOfBoundsException();
//                }
//                return target.isNil();
//            }
//        }
//        
//        /**
//         * Returns number of "return" element
//         */
//        public int sizeOfReturnArray()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                return get_store().count_elements(RETURN$0);
//            }
//        }
//        
//        /**
//         * Sets array of all "return" element
//         */
//        public void setReturnArray(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica[] xreturnArray)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                arraySetterHelper(xreturnArray, RETURN$0);
//            }
//        }
//        
//        /**
//         * Sets ith "return" element
//         */
//        public void setReturnArray(int i, gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica xreturn)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica)get_store().find_element_user(RETURN$0, i);
//                if (target == null)
//                {
//                    throw new IndexOutOfBoundsException();
//                }
//                target.set(xreturn);
//            }
//        }
//        
//        /**
//         * Nils the ith "return" element
//         */
//        public void setNilReturnArray(int i)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica)get_store().find_element_user(RETURN$0, i);
//                if (target == null)
//                {
//                    throw new IndexOutOfBoundsException();
//                }
//                target.setNil();
//            }
//        }
//        
//        /**
//         * Inserts and returns a new empty value (as xml) as the ith "return" element
//         */
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica insertNewReturn(int i)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica)get_store().insert_element_user(RETURN$0, i);
//                return target;
//            }
//        }
//        
//        /**
//         * Appends and returns a new empty value (as xml) as the last "return" element
//         */
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica addNewReturn()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica)get_store().add_element_user(RETURN$0);
//                return target;
//            }
//        }
//        
//        /**
//         * Removes the ith "return" element
//         */
//        public void removeReturn(int i)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                get_store().remove_element(RETURN$0, i);
//            }
//        }
//    }
//}
