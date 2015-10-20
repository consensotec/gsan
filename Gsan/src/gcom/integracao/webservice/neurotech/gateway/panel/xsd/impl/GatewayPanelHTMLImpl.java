///*
// * XML Type:  GatewayPanelHTML
// * Namespace: http://panel.gateway.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.panel.xsd.impl;
///**
// * An XML GatewayPanelHTML(@http://panel.gateway.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public class GatewayPanelHTMLImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML
//{
//    
//    public GatewayPanelHTMLImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName HTML$0 = 
//        new javax.xml.namespace.QName("http://panel.gateway.neurotech.com.br/xsd", "HTML");
//    
//    
//    /**
//     * Gets the "HTML" element
//     */
//    public java.lang.String getHTML()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HTML$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target.getStringValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "HTML" element
//     */
//    public org.apache.xmlbeans.XmlString xgetHTML()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(HTML$0, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil "HTML" element
//     */
//    public boolean isNilHTML()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(HTML$0, 0);
//            if (target == null) return false;
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * True if has "HTML" element
//     */
//    public boolean isSetHTML()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(HTML$0) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "HTML" element
//     */
//    public void setHTML(java.lang.String html)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HTML$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(HTML$0);
//            }
//            target.setStringValue(html);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "HTML" element
//     */
//    public void xsetHTML(org.apache.xmlbeans.XmlString html)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(HTML$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(HTML$0);
//            }
//            target.set(html);
//        }
//    }
//    
//    /**
//     * Nils the "HTML" element
//     */
//    public void setNilHTML()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(HTML$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(HTML$0);
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Unsets the "HTML" element
//     */
//    public void unsetHTML()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(HTML$0, 0);
//        }
//    }
//}
