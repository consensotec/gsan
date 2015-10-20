///*
// * XML Type:  GatewayPanelOperation
// * Namespace: http://panel.gateway.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.panel.xsd.impl;
///**
// * An XML GatewayPanelOperation(@http://panel.gateway.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public class GatewayPanelOperationImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation
//{
//    
//    public GatewayPanelOperationImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName LOGIN$0 = 
//        new javax.xml.namespace.QName("http://panel.gateway.neurotech.com.br/xsd", "login");
//    private static final javax.xml.namespace.QName OPERATIONID$2 = 
//        new javax.xml.namespace.QName("http://panel.gateway.neurotech.com.br/xsd", "operationID");
//    private static final javax.xml.namespace.QName OPERATIONXML$4 = 
//        new javax.xml.namespace.QName("http://panel.gateway.neurotech.com.br/xsd", "operationXML");
//    
//    
//    /**
//     * Gets the "login" element
//     */
//    public java.lang.String getLogin()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LOGIN$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target.getStringValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "login" element
//     */
//    public org.apache.xmlbeans.XmlString xgetLogin()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(LOGIN$0, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil "login" element
//     */
//    public boolean isNilLogin()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(LOGIN$0, 0);
//            if (target == null) return false;
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * True if has "login" element
//     */
//    public boolean isSetLogin()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(LOGIN$0) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "login" element
//     */
//    public void setLogin(java.lang.String login)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LOGIN$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(LOGIN$0);
//            }
//            target.setStringValue(login);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "login" element
//     */
//    public void xsetLogin(org.apache.xmlbeans.XmlString login)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(LOGIN$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(LOGIN$0);
//            }
//            target.set(login);
//        }
//    }
//    
//    /**
//     * Nils the "login" element
//     */
//    public void setNilLogin()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(LOGIN$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(LOGIN$0);
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Unsets the "login" element
//     */
//    public void unsetLogin()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(LOGIN$0, 0);
//        }
//    }
//    
//    /**
//     * Gets the "operationID" element
//     */
//    public int getOperationID()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(OPERATIONID$2, 0);
//            if (target == null)
//            {
//                return 0;
//            }
//            return target.getIntValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "operationID" element
//     */
//    public org.apache.xmlbeans.XmlInt xgetOperationID()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlInt target = null;
//            target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(OPERATIONID$2, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * True if has "operationID" element
//     */
//    public boolean isSetOperationID()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(OPERATIONID$2) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "operationID" element
//     */
//    public void setOperationID(int operationID)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(OPERATIONID$2, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(OPERATIONID$2);
//            }
//            target.setIntValue(operationID);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "operationID" element
//     */
//    public void xsetOperationID(org.apache.xmlbeans.XmlInt operationID)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlInt target = null;
//            target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(OPERATIONID$2, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlInt)get_store().add_element_user(OPERATIONID$2);
//            }
//            target.set(operationID);
//        }
//    }
//    
//    /**
//     * Unsets the "operationID" element
//     */
//    public void unsetOperationID()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(OPERATIONID$2, 0);
//        }
//    }
//    
//    /**
//     * Gets the "operationXML" element
//     */
//    public java.lang.String getOperationXML()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(OPERATIONXML$4, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target.getStringValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "operationXML" element
//     */
//    public org.apache.xmlbeans.XmlString xgetOperationXML()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(OPERATIONXML$4, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil "operationXML" element
//     */
//    public boolean isNilOperationXML()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(OPERATIONXML$4, 0);
//            if (target == null) return false;
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * True if has "operationXML" element
//     */
//    public boolean isSetOperationXML()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(OPERATIONXML$4) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "operationXML" element
//     */
//    public void setOperationXML(java.lang.String operationXML)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(OPERATIONXML$4, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(OPERATIONXML$4);
//            }
//            target.setStringValue(operationXML);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "operationXML" element
//     */
//    public void xsetOperationXML(org.apache.xmlbeans.XmlString operationXML)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(OPERATIONXML$4, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(OPERATIONXML$4);
//            }
//            target.set(operationXML);
//        }
//    }
//    
//    /**
//     * Nils the "operationXML" element
//     */
//    public void setNilOperationXML()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(OPERATIONXML$4, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(OPERATIONXML$4);
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Unsets the "operationXML" element
//     */
//    public void unsetOperationXML()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(OPERATIONXML$4, 0);
//        }
//    }
//}
