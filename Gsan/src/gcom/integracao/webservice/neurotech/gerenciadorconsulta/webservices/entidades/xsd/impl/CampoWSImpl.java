///*
// * XML Type:  CampoWS
// * Namespace: http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl;
///**
// * An XML CampoWS(@http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public class CampoWSImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS
//{
//    
//    public CampoWSImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName NMCAMPO$0 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "nmCampo");
//    private static final javax.xml.namespace.QName VLCAMPO$2 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "vlCampo");
//    
//    
//    /**
//     * Gets the "nmCampo" element
//     */
//    public java.lang.String getNmCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NMCAMPO$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target.getStringValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "nmCampo" element
//     */
//    public org.apache.xmlbeans.XmlString xgetNmCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NMCAMPO$0, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil "nmCampo" element
//     */
//    public boolean isNilNmCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NMCAMPO$0, 0);
//            if (target == null) return false;
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * True if has "nmCampo" element
//     */
//    public boolean isSetNmCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(NMCAMPO$0) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "nmCampo" element
//     */
//    public void setNmCampo(java.lang.String nmCampo)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NMCAMPO$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NMCAMPO$0);
//            }
//            target.setStringValue(nmCampo);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "nmCampo" element
//     */
//    public void xsetNmCampo(org.apache.xmlbeans.XmlString nmCampo)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NMCAMPO$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(NMCAMPO$0);
//            }
//            target.set(nmCampo);
//        }
//    }
//    
//    /**
//     * Nils the "nmCampo" element
//     */
//    public void setNilNmCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NMCAMPO$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(NMCAMPO$0);
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Unsets the "nmCampo" element
//     */
//    public void unsetNmCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(NMCAMPO$0, 0);
//        }
//    }
//    
//    /**
//     * Gets the "vlCampo" element
//     */
//    public java.lang.String getVlCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VLCAMPO$2, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target.getStringValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "vlCampo" element
//     */
//    public org.apache.xmlbeans.XmlString xgetVlCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VLCAMPO$2, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil "vlCampo" element
//     */
//    public boolean isNilVlCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VLCAMPO$2, 0);
//            if (target == null) return false;
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * True if has "vlCampo" element
//     */
//    public boolean isSetVlCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(VLCAMPO$2) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "vlCampo" element
//     */
//    public void setVlCampo(java.lang.String vlCampo)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VLCAMPO$2, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(VLCAMPO$2);
//            }
//            target.setStringValue(vlCampo);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "vlCampo" element
//     */
//    public void xsetVlCampo(org.apache.xmlbeans.XmlString vlCampo)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VLCAMPO$2, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(VLCAMPO$2);
//            }
//            target.set(vlCampo);
//        }
//    }
//    
//    /**
//     * Nils the "vlCampo" element
//     */
//    public void setNilVlCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VLCAMPO$2, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(VLCAMPO$2);
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Unsets the "vlCampo" element
//     */
//    public void unsetVlCampo()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(VLCAMPO$2, 0);
//        }
//    }
//}
