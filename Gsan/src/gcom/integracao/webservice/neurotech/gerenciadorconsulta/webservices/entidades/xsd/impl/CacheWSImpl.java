///*
// * XML Type:  CacheWS
// * Namespace: http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl;
///**
// * An XML CacheWS(@http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public class CacheWSImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS
//{
//    
//    public CacheWSImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName ATUALIZADO$0 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "atualizado");
//    
//    
//    /**
//     * Gets the "atualizado" element
//     */
//    public boolean getAtualizado()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ATUALIZADO$0, 0);
//            if (target == null)
//            {
//                return false;
//            }
//            return target.getBooleanValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "atualizado" element
//     */
//    public org.apache.xmlbeans.XmlBoolean xgetAtualizado()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlBoolean target = null;
//            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(ATUALIZADO$0, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * True if has "atualizado" element
//     */
//    public boolean isSetAtualizado()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(ATUALIZADO$0) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "atualizado" element
//     */
//    public void setAtualizado(boolean atualizado)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ATUALIZADO$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ATUALIZADO$0);
//            }
//            target.setBooleanValue(atualizado);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "atualizado" element
//     */
//    public void xsetAtualizado(org.apache.xmlbeans.XmlBoolean atualizado)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlBoolean target = null;
//            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_element_user(ATUALIZADO$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlBoolean)get_store().add_element_user(ATUALIZADO$0);
//            }
//            target.set(atualizado);
//        }
//    }
//    
//    /**
//     * Unsets the "atualizado" element
//     */
//    public void unsetAtualizado()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(ATUALIZADO$0, 0);
//        }
//    }
//}
