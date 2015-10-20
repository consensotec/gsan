///*
// * An XML document type.
// * Localname: consultarLogResponse
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada.impl;
///**
// * A document containing one consultarLogResponse(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public class ConsultarLogResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument
//{
//    
//    public ConsultarLogResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName CONSULTARLOGRESPONSE$0 = 
//        new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "consultarLogResponse");
//    
//    
////    /**
////     * Gets the "consultarLogResponse" element
////     */
////    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument.ConsultarLogResponse getConsultarLogResponse()
////    {
////        synchronized (monitor())
////        {
////            check_orphaned();
////            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument.ConsultarLogResponse target = null;
////            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument.ConsultarLogResponse)get_store().find_element_user(CONSULTARLOGRESPONSE$0, 0);
////            if (target == null)
////            {
////                return null;
////            }
////            return target;
////        }
////    }
//    
//    /**
//     * Sets the "consultarLogResponse" element
//     */
//    public void setConsultarLogResponse(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument.ConsultarLogResponse consultarLogResponse)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument.ConsultarLogResponse target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument.ConsultarLogResponse)get_store().find_element_user(CONSULTARLOGRESPONSE$0, 0);
//            if (target == null)
//            {
//                target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument.ConsultarLogResponse)get_store().add_element_user(CONSULTARLOGRESPONSE$0);
//            }
//            target.set(consultarLogResponse);
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty "consultarLogResponse" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument.ConsultarLogResponse addNewConsultarLogResponse()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument.ConsultarLogResponse target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument.ConsultarLogResponse)get_store().add_element_user(CONSULTARLOGRESPONSE$0);
//            return target;
//        }
//    }
//    /**
//     * An XML consultarLogResponse(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public static class ConsultarLogResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogResponseDocument.ConsultarLogResponse
//    {
//        
//        public ConsultarLogResponseImpl(org.apache.xmlbeans.SchemaType sType)
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
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog[] getReturnArray()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                java.util.List targetList = new java.util.ArrayList();
//                get_store().find_all_element_users(RETURN$0, targetList);
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog[] result = new gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog[targetList.size()];
//                targetList.toArray(result);
//                return result;
//            }
//        }
//        
//        /**
//         * Gets ith "return" element
//         */
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog getReturnArray(int i)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog)get_store().find_element_user(RETURN$0, i);
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
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog)get_store().find_element_user(RETURN$0, i);
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
//        public void setReturnArray(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog[] xreturnArray)
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
//        public void setReturnArray(int i, gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog xreturn)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog)get_store().find_element_user(RETURN$0, i);
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
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog)get_store().find_element_user(RETURN$0, i);
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
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog insertNewReturn(int i)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog)get_store().insert_element_user(RETURN$0, i);
//                return target;
//            }
//        }
//        
//        /**
//         * Appends and returns a new empty value (as xml) as the last "return" element
//         */
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog addNewReturn()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog)get_store().add_element_user(RETURN$0);
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
