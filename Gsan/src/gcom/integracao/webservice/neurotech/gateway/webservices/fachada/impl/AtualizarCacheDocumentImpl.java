///*
// * An XML document type.
// * Localname: atualizarCache
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada.impl;
///**
// * A document containing one atualizarCache(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public class AtualizarCacheDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument
//{
//    
//    public AtualizarCacheDocumentImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName ATUALIZARCACHE$0 = 
//        new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "atualizarCache");
//    
//    
//    /**
//     * Gets the "atualizarCache" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.AtualizarCache getAtualizarCache()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.AtualizarCache target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.AtualizarCache)get_store().find_element_user(ATUALIZARCACHE$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Sets the "atualizarCache" element
//     */
//    public void setAtualizarCache(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.AtualizarCache atualizarCache)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.AtualizarCache target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.AtualizarCache)get_store().find_element_user(ATUALIZARCACHE$0, 0);
//            if (target == null)
//            {
//                target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.AtualizarCache)get_store().add_element_user(ATUALIZARCACHE$0);
//            }
//            target.set(atualizarCache);
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty "atualizarCache" element
//     */
//    public gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.AtualizarCache addNewAtualizarCache()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.AtualizarCache target = null;
//            target = (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.AtualizarCache)get_store().add_element_user(ATUALIZARCACHE$0);
//            return target;
//        }
//    }
//    /**
//     * An XML atualizarCache(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public static class AtualizarCacheImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.webservices.fachada.AtualizarCacheDocument.AtualizarCache
//    {
//        
//        public AtualizarCacheImpl(org.apache.xmlbeans.SchemaType sType)
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
//    }
//}
