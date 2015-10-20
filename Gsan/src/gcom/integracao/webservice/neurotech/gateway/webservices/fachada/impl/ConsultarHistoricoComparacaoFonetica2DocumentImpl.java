///*
// * An XML document type.
// * Localname: consultarHistoricoComparacaoFonetica2
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada.impl;
///**
// * A document containing one consultarHistoricoComparacaoFonetica2(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public class ConsultarHistoricoComparacaoFonetica2DocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document
//{
//    
//    public ConsultarHistoricoComparacaoFonetica2DocumentImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName CONSULTARHISTORICOCOMPARACAOFONETICA2$0 = 
//        new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "consultarHistoricoComparacaoFonetica2");
//    
//    
//    /**
//     * Gets the "consultarHistoricoComparacaoFonetica2" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2 getConsultarHistoricoComparacaoFonetica2()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2 target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2)get_store().find_element_user(CONSULTARHISTORICOCOMPARACAOFONETICA2$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Sets the "consultarHistoricoComparacaoFonetica2" element
//     */
//    public void setConsultarHistoricoComparacaoFonetica2(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2 consultarHistoricoComparacaoFonetica2)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2 target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2)get_store().find_element_user(CONSULTARHISTORICOCOMPARACAOFONETICA2$0, 0);
//            if (target == null)
//            {
//                target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2)get_store().add_element_user(CONSULTARHISTORICOCOMPARACAOFONETICA2$0);
//            }
//            target.set(consultarHistoricoComparacaoFonetica2);
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty "consultarHistoricoComparacaoFonetica2" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2 addNewConsultarHistoricoComparacaoFonetica2()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2 target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2)get_store().add_element_user(CONSULTARHISTORICOCOMPARACAOFONETICA2$0);
//            return target;
//        }
//    }
//    /**
//     * An XML consultarHistoricoComparacaoFonetica2(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public static class ConsultarHistoricoComparacaoFonetica2Impl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2
//    {
//        
//        public ConsultarHistoricoComparacaoFonetica2Impl(org.apache.xmlbeans.SchemaType sType)
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
//        private static final javax.xml.namespace.QName PCONSULTA$6 = 
//            new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "pConsulta");
//        private static final javax.xml.namespace.QName PALGORITMO$8 = 
//            new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "pAlgoritmo");
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
//         * Gets the "pConsulta" element
//         */
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica getPConsulta()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica)get_store().find_element_user(PCONSULTA$6, 0);
//                if (target == null)
//                {
//                    return null;
//                }
//                return target;
//            }
//        }
//        
//        /**
//         * Tests for nil "pConsulta" element
//         */
//        public boolean isNilPConsulta()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica)get_store().find_element_user(PCONSULTA$6, 0);
//                if (target == null) return false;
//                return target.isNil();
//            }
//        }
//        
//        /**
//         * True if has "pConsulta" element
//         */
//        public boolean isSetPConsulta()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                return get_store().count_elements(PCONSULTA$6) != 0;
//            }
//        }
//        
//        /**
//         * Sets the "pConsulta" element
//         */
//        public void setPConsulta(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica pConsulta)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica)get_store().find_element_user(PCONSULTA$6, 0);
//                if (target == null)
//                {
//                    target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica)get_store().add_element_user(PCONSULTA$6);
//                }
//                target.set(pConsulta);
//            }
//        }
//        
//        /**
//         * Appends and returns a new empty "pConsulta" element
//         */
//        public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica addNewPConsulta()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica)get_store().add_element_user(PCONSULTA$6);
//                return target;
//            }
//        }
//        
//        /**
//         * Nils the "pConsulta" element
//         */
//        public void setNilPConsulta()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica target = null;
//                target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica)get_store().find_element_user(PCONSULTA$6, 0);
//                if (target == null)
//                {
//                    target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica)get_store().add_element_user(PCONSULTA$6);
//                }
//                target.setNil();
//            }
//        }
//        
//        /**
//         * Unsets the "pConsulta" element
//         */
//        public void unsetPConsulta()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                get_store().remove_element(PCONSULTA$6, 0);
//            }
//        }
//        
//        /**
//         * Gets the "pAlgoritmo" element
//         */
//        public java.lang.String getPAlgoritmo()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.SimpleValue target = null;
//                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PALGORITMO$8, 0);
//                if (target == null)
//                {
//                    return null;
//                }
//                return target.getStringValue();
//            }
//        }
//        
//        /**
//         * Gets (as xml) the "pAlgoritmo" element
//         */
//        public org.apache.xmlbeans.XmlString xgetPAlgoritmo()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.XmlString target = null;
//                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PALGORITMO$8, 0);
//                return target;
//            }
//        }
//        
//        /**
//         * Tests for nil "pAlgoritmo" element
//         */
//        public boolean isNilPAlgoritmo()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.XmlString target = null;
//                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PALGORITMO$8, 0);
//                if (target == null) return false;
//                return target.isNil();
//            }
//        }
//        
//        /**
//         * True if has "pAlgoritmo" element
//         */
//        public boolean isSetPAlgoritmo()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                return get_store().count_elements(PALGORITMO$8) != 0;
//            }
//        }
//        
//        /**
//         * Sets the "pAlgoritmo" element
//         */
//        public void setPAlgoritmo(java.lang.String pAlgoritmo)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.SimpleValue target = null;
//                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PALGORITMO$8, 0);
//                if (target == null)
//                {
//                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PALGORITMO$8);
//                }
//                target.setStringValue(pAlgoritmo);
//            }
//        }
//        
//        /**
//         * Sets (as xml) the "pAlgoritmo" element
//         */
//        public void xsetPAlgoritmo(org.apache.xmlbeans.XmlString pAlgoritmo)
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.XmlString target = null;
//                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PALGORITMO$8, 0);
//                if (target == null)
//                {
//                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PALGORITMO$8);
//                }
//                target.set(pAlgoritmo);
//            }
//        }
//        
//        /**
//         * Nils the "pAlgoritmo" element
//         */
//        public void setNilPAlgoritmo()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                org.apache.xmlbeans.XmlString target = null;
//                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PALGORITMO$8, 0);
//                if (target == null)
//                {
//                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PALGORITMO$8);
//                }
//                target.setNil();
//            }
//        }
//        
//        /**
//         * Unsets the "pAlgoritmo" element
//         */
//        public void unsetPAlgoritmo()
//        {
//            synchronized (monitor())
//            {
//                check_orphaned();
//                get_store().remove_element(PALGORITMO$8, 0);
//            }
//        }
//    }
//}
