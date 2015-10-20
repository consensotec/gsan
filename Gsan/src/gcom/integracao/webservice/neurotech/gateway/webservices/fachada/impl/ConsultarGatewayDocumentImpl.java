///*
// * An XML document type.
// * Localname: consultarGateway
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada.impl;
///**
// * A document containing one consultarGateway(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public class ConsultarGatewayDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument
//{
//    
//    public ConsultarGatewayDocumentImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName CONSULTARGATEWAY$0 = 
//        new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "consultarGateway");
//    
//    
//    /**
//     * Gets the "consultarGateway" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.ConsultarGateway getConsultarGateway()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.ConsultarGateway target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.ConsultarGateway)get_store().find_element_user(CONSULTARGATEWAY$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Sets the "consultarGateway" element
//     */
//    public void setConsultarGateway(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.ConsultarGateway consultarGateway)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.ConsultarGateway target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.ConsultarGateway)get_store().find_element_user(CONSULTARGATEWAY$0, 0);
//            if (target == null)
//            {
//                target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.ConsultarGateway)get_store().add_element_user(CONSULTARGATEWAY$0);
//            }
//            target.set(consultarGateway);
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty "consultarGateway" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.ConsultarGateway addNewConsultarGateway()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.ConsultarGateway target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.ConsultarGateway)get_store().add_element_user(CONSULTARGATEWAY$0);
//            return target;
//        }
//    }
//    /**
//     * An XML consultarGateway(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public static class ConsultarGatewayImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayDocument.ConsultarGateway
//    {
//        
//        public ConsultarGatewayImpl(org.apache.xmlbeans.SchemaType sType)
//        {
//            super(sType);
//        }
//        
//        private static final javax.xml.namespace.QName PIDENTIFICADOR$0 = 
//            new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "pIdentificador");
//        private static final javax.xml.namespace.QName PLOGIN$2 = 
//            new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "pLogin");
//        private static final javax.xml.namespace.QName PSENHA$4 = 
//            new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "pSenha");
//        private static final javax.xml.namespace.QName PCONSULTAS$6 = 
//            new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "pConsultas");
//        
//        
//        /**
//         * Gets the "pIdentificador" element
//         */
//        public int getPIdentificador()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.SimpleValue target = null;
//                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PIDENTIFICADOR$0, 0);
//                if (target == null)
//                {
//                    return 0;
//                }
//                return target.getIntValue();
//            }
//        }
//        
//        /**
//         * Gets (as xml) the "pIdentificador" element
//         */
//        public org.apache.xmlbeans.XmlInt xgetPIdentificador()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.XmlInt target = null;
//                target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(PIDENTIFICADOR$0, 0);
//                return target;
//            }
//        }
//        
//        /**
//         * True if has "pIdentificador" element
//         */
//        public boolean isSetPIdentificador()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                return get_store().count_elements(PIDENTIFICADOR$0) != 0;
//            }
//        }
//        
//        /**
//         * Sets the "pIdentificador" element
//         */
//        public void setPIdentificador(int pIdentificador)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.SimpleValue target = null;
//                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PIDENTIFICADOR$0, 0);
//                if (target == null)
//                {
//                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PIDENTIFICADOR$0);
//                }
//                target.setIntValue(pIdentificador);
//            }
//        }
//        
//        /**
//         * Sets (as xml) the "pIdentificador" element
//         */
//        public void xsetPIdentificador(org.apache.xmlbeans.XmlInt pIdentificador)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.XmlInt target = null;
//                target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(PIDENTIFICADOR$0, 0);
//                if (target == null)
//                {
//                    target = (org.apache.xmlbeans.XmlInt)get_store().add_element_user(PIDENTIFICADOR$0);
//                }
//                target.set(pIdentificador);
//            }
//        }
//        
//        /**
//         * Unsets the "pIdentificador" element
//         */
//        public void unsetPIdentificador()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                get_store().remove_element(PIDENTIFICADOR$0, 0);
//            }
//        }
//        
//        /**
//         * Gets the "pLogin" element
//         */
//        public java.lang.String getPLogin()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.SimpleValue target = null;
//                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PLOGIN$2, 0);
//                if (target == null)
//                {
//                    return null;
//                }
//                return target.getStringValue();
//            }
//        }
//        
//        /**
//         * Gets (as xml) the "pLogin" element
//         */
//        public org.apache.xmlbeans.XmlString xgetPLogin()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.XmlString target = null;
//                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PLOGIN$2, 0);
//                return target;
//            }
//        }
//        
//        /**
//         * Tests for nil "pLogin" element
//         */
//        public boolean isNilPLogin()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.XmlString target = null;
//                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PLOGIN$2, 0);
//                if (target == null) return false;
//                return target.isNil();
//            }
//        }
//        
//        /**
//         * True if has "pLogin" element
//         */
//        public boolean isSetPLogin()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                return get_store().count_elements(PLOGIN$2) != 0;
//            }
//        }
//        
//        /**
//         * Sets the "pLogin" element
//         */
//        public void setPLogin(java.lang.String pLogin)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.SimpleValue target = null;
//                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PLOGIN$2, 0);
//                if (target == null)
//                {
//                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PLOGIN$2);
//                }
//                target.setStringValue(pLogin);
//            }
//        }
//        
//        /**
//         * Sets (as xml) the "pLogin" element
//         */
//        public void xsetPLogin(org.apache.xmlbeans.XmlString pLogin)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.XmlString target = null;
//                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PLOGIN$2, 0);
//                if (target == null)
//                {
//                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PLOGIN$2);
//                }
//                target.set(pLogin);
//            }
//        }
//        
//        /**
//         * Nils the "pLogin" element
//         */
//        public void setNilPLogin()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.XmlString target = null;
//                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PLOGIN$2, 0);
//                if (target == null)
//                {
//                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PLOGIN$2);
//                }
//                target.setNil();
//            }
//        }
//        
//        /**
//         * Unsets the "pLogin" element
//         */
//        public void unsetPLogin()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                get_store().remove_element(PLOGIN$2, 0);
//            }
//        }
//        
//        /**
//         * Gets the "pSenha" element
//         */
//        public java.lang.String getPSenha()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.SimpleValue target = null;
//                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PSENHA$4, 0);
//                if (target == null)
//                {
//                    return null;
//                }
//                return target.getStringValue();
//            }
//        }
//        
//        /**
//         * Gets (as xml) the "pSenha" element
//         */
//        public org.apache.xmlbeans.XmlString xgetPSenha()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.XmlString target = null;
//                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PSENHA$4, 0);
//                return target;
//            }
//        }
//        
//        /**
//         * Tests for nil "pSenha" element
//         */
//        public boolean isNilPSenha()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.XmlString target = null;
//                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PSENHA$4, 0);
//                if (target == null) return false;
//                return target.isNil();
//            }
//        }
//        
//        /**
//         * True if has "pSenha" element
//         */
//        public boolean isSetPSenha()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                return get_store().count_elements(PSENHA$4) != 0;
//            }
//        }
//        
//        /**
//         * Sets the "pSenha" element
//         */
//        public void setPSenha(java.lang.String pSenha)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.SimpleValue target = null;
//                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PSENHA$4, 0);
//                if (target == null)
//                {
//                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PSENHA$4);
//                }
//                target.setStringValue(pSenha);
//            }
//        }
//        
//        /**
//         * Sets (as xml) the "pSenha" element
//         */
//        public void xsetPSenha(org.apache.xmlbeans.XmlString pSenha)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.XmlString target = null;
//                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PSENHA$4, 0);
//                if (target == null)
//                {
//                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PSENHA$4);
//                }
//                target.set(pSenha);
//            }
//        }
//        
//        /**
//         * Nils the "pSenha" element
//         */
//        public void setNilPSenha()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.XmlString target = null;
//                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PSENHA$4, 0);
//                if (target == null)
//                {
//                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PSENHA$4);
//                }
//                target.setNil();
//            }
//        }
//        
//        /**
//         * Unsets the "pSenha" element
//         */
//        public void unsetPSenha()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                get_store().remove_element(PSENHA$4, 0);
//            }
//        }
//        
//        /**
//         * Gets array of all "pConsultas" elements
//         */
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS[] getPConsultasArray()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                java.util.List targetList = new java.util.ArrayList();
//                get_store().find_all_element_users(PCONSULTAS$6, targetList);
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS[] result = new gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS[targetList.size()];
//                targetList.toArray(result);
//                return result;
//            }
//        }
//        
//        /**
//         * Gets ith "pConsultas" element
//         */
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS getPConsultasArray(int i)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS)get_store().find_element_user(PCONSULTAS$6, i);
//                if (target == null)
//                {
//                    throw new IndexOutOfBoundsException();
//                }
//                return target;
//            }
//        }
//        
//        /**
//         * Tests for nil ith "pConsultas" element
//         */
//        public boolean isNilPConsultasArray(int i)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS)get_store().find_element_user(PCONSULTAS$6, i);
//                if (target == null)
//                {
//                    throw new IndexOutOfBoundsException();
//                }
//                return target.isNil();
//            }
//        }
//        
//        /**
//         * Returns number of "pConsultas" element
//         */
//        public int sizeOfPConsultasArray()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                return get_store().count_elements(PCONSULTAS$6);
//            }
//        }
//        
//        /**
//         * Sets array of all "pConsultas" element
//         */
//        public void setPConsultasArray(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS[] pConsultasArray)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                arraySetterHelper(pConsultasArray, PCONSULTAS$6);
//            }
//        }
//        
//        /**
//         * Sets ith "pConsultas" element
//         */
//        public void setPConsultasArray(int i, gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS pConsultas)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS)get_store().find_element_user(PCONSULTAS$6, i);
//                if (target == null)
//                {
//                    throw new IndexOutOfBoundsException();
//                }
//                target.set(pConsultas);
//            }
//        }
//        
//        /**
//         * Nils the ith "pConsultas" element
//         */
//        public void setNilPConsultasArray(int i)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS)get_store().find_element_user(PCONSULTAS$6, i);
//                if (target == null)
//                {
//                    throw new IndexOutOfBoundsException();
//                }
//                target.setNil();
//            }
//        }
//        
//        /**
//         * Inserts and returns a new empty value (as xml) as the ith "pConsultas" element
//         */
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS insertNewPConsultas(int i)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS)get_store().insert_element_user(PCONSULTAS$6, i);
//                return target;
//            }
//        }
//        
//        /**
//         * Appends and returns a new empty value (as xml) as the last "pConsultas" element
//         */
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS addNewPConsultas()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS)get_store().add_element_user(PCONSULTAS$6);
//                return target;
//            }
//        }
//        
//        /**
//         * Removes the ith "pConsultas" element
//         */
//        public void removePConsultas(int i)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                get_store().remove_element(PCONSULTAS$6, i);
//            }
//        }
//    }
//}
