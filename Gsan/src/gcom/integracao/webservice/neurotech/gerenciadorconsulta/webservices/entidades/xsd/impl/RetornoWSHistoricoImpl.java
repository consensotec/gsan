///*
// * XML Type:  RetornoWSHistorico
// * Namespace: http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistorico
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl;
///**
// * An XML RetornoWSHistorico(@http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public class RetornoWSHistoricoImpl extends gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl.RetornoWSIdLogImpl implements gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistorico
//{
//    
//    public RetornoWSHistoricoImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName DIASANTIGHISTORICO$0 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "diasAntigHistorico");
//    
//    
//    /**
//     * Gets the "diasAntigHistorico" element
//     */
//    public java.lang.String getDiasAntigHistorico()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DIASANTIGHISTORICO$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target.getStringValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "diasAntigHistorico" element
//     */
//    public org.apache.xmlbeans.XmlString xgetDiasAntigHistorico()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DIASANTIGHISTORICO$0, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil "diasAntigHistorico" element
//     */
//    public boolean isNilDiasAntigHistorico()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DIASANTIGHISTORICO$0, 0);
//            if (target == null) return false;
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * True if has "diasAntigHistorico" element
//     */
//    public boolean isSetDiasAntigHistorico()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(DIASANTIGHISTORICO$0) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "diasAntigHistorico" element
//     */
//    public void setDiasAntigHistorico(java.lang.String diasAntigHistorico)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DIASANTIGHISTORICO$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DIASANTIGHISTORICO$0);
//            }
//            target.setStringValue(diasAntigHistorico);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "diasAntigHistorico" element
//     */
//    public void xsetDiasAntigHistorico(org.apache.xmlbeans.XmlString diasAntigHistorico)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DIASANTIGHISTORICO$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DIASANTIGHISTORICO$0);
//            }
//            target.set(diasAntigHistorico);
//        }
//    }
//    
//    /**
//     * Nils the "diasAntigHistorico" element
//     */
//    public void setNilDiasAntigHistorico()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DIASANTIGHISTORICO$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DIASANTIGHISTORICO$0);
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Unsets the "diasAntigHistorico" element
//     */
//    public void unsetDiasAntigHistorico()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(DIASANTIGHISTORICO$0, 0);
//        }
//    }
//}
