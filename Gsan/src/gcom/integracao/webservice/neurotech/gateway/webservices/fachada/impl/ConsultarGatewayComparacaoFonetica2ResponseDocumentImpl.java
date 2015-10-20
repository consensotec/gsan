///*
// * An XML document type.
// * Localname: consultarGatewayComparacaoFonetica2Response
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada.impl;
///**
// * A document containing one consultarGatewayComparacaoFonetica2Response(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public class ConsultarGatewayComparacaoFonetica2ResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument
//{
//    
//    public ConsultarGatewayComparacaoFonetica2ResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName CONSULTARGATEWAYCOMPARACAOFONETICA2RESPONSE$0 = 
//        new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "consultarGatewayComparacaoFonetica2Response");
//    
//    
//    /**
//     * Gets the "consultarGatewayComparacaoFonetica2Response" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument.ConsultarGatewayComparacaoFonetica2Response getConsultarGatewayComparacaoFonetica2Response()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument.ConsultarGatewayComparacaoFonetica2Response target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument.ConsultarGatewayComparacaoFonetica2Response)get_store().find_element_user(CONSULTARGATEWAYCOMPARACAOFONETICA2RESPONSE$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Sets the "consultarGatewayComparacaoFonetica2Response" element
//     */
//    public void setConsultarGatewayComparacaoFonetica2Response(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument.ConsultarGatewayComparacaoFonetica2Response consultarGatewayComparacaoFonetica2Response)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument.ConsultarGatewayComparacaoFonetica2Response target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument.ConsultarGatewayComparacaoFonetica2Response)get_store().find_element_user(CONSULTARGATEWAYCOMPARACAOFONETICA2RESPONSE$0, 0);
//            if (target == null)
//            {
//                target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument.ConsultarGatewayComparacaoFonetica2Response)get_store().add_element_user(CONSULTARGATEWAYCOMPARACAOFONETICA2RESPONSE$0);
//            }
//            target.set(consultarGatewayComparacaoFonetica2Response);
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty "consultarGatewayComparacaoFonetica2Response" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument.ConsultarGatewayComparacaoFonetica2Response addNewConsultarGatewayComparacaoFonetica2Response()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument.ConsultarGatewayComparacaoFonetica2Response target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument.ConsultarGatewayComparacaoFonetica2Response)get_store().add_element_user(CONSULTARGATEWAYCOMPARACAOFONETICA2RESPONSE$0);
//            return target;
//        }
//    }
//    /**
//     * An XML consultarGatewayComparacaoFonetica2Response(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public static class ConsultarGatewayComparacaoFonetica2ResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFonetica2ResponseDocument.ConsultarGatewayComparacaoFonetica2Response
//    {
//        
//        public ConsultarGatewayComparacaoFonetica2ResponseImpl(org.apache.xmlbeans.SchemaType sType)
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
