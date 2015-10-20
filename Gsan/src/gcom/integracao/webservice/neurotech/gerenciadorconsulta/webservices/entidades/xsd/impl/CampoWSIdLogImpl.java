///*
// * XML Type:  CampoWSIdLog
// * Namespace: http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl;
///**
// * An XML CampoWSIdLog(@http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public class CampoWSIdLogImpl extends gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl.CampoWSImpl implements gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog
//{
//    
//    public CampoWSIdLogImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName TPCAMPO$0 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "tpCampo");
//    
//    
//    /**
//     * Gets the "tpCampo" element
//     */
//    public java.lang.String getTpCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TPCAMPO$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target.getStringValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "tpCampo" element
//     */
//    public org.apache.xmlbeans.XmlString xgetTpCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TPCAMPO$0, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil "tpCampo" element
//     */
//    public boolean isNilTpCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TPCAMPO$0, 0);
//            if (target == null) return false;
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * True if has "tpCampo" element
//     */
//    public boolean isSetTpCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(TPCAMPO$0) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "tpCampo" element
//     */
//    public void setTpCampo(java.lang.String tpCampo)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TPCAMPO$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TPCAMPO$0);
//            }
//            target.setStringValue(tpCampo);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "tpCampo" element
//     */
//    public void xsetTpCampo(org.apache.xmlbeans.XmlString tpCampo)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TPCAMPO$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TPCAMPO$0);
//            }
//            target.set(tpCampo);
//        }
//    }
//    
//    /**
//     * Nils the "tpCampo" element
//     */
//    public void setNilTpCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(TPCAMPO$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(TPCAMPO$0);
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Unsets the "tpCampo" element
//     */
//    public void unsetTpCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(TPCAMPO$0, 0);
//        }
//    }
//}
