///*
// * XML Type:  RetornoWSComparacaoFoneticaU
// * Namespace: http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFoneticaU
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl;
///**
// * An XML RetornoWSComparacaoFoneticaU(@http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public class RetornoWSComparacaoFoneticaUImpl extends gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl.RetornoWSComparacaoFoneticaImpl implements gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFoneticaU
//{
//    
//    public RetornoWSComparacaoFoneticaUImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName DIASVALIDADERESTANTES$0 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "diasValidadeRestantes");
//    
//    
//    /**
//     * Gets the "diasValidadeRestantes" element
//     */
//    public int getDiasValidadeRestantes()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DIASVALIDADERESTANTES$0, 0);
//            if (target == null)
//            {
//                return 0;
//            }
//            return target.getIntValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "diasValidadeRestantes" element
//     */
//    public org.apache.xmlbeans.XmlInt xgetDiasValidadeRestantes()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlInt target = null;
//            target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(DIASVALIDADERESTANTES$0, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * True if has "diasValidadeRestantes" element
//     */
//    public boolean isSetDiasValidadeRestantes()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(DIASVALIDADERESTANTES$0) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "diasValidadeRestantes" element
//     */
//    public void setDiasValidadeRestantes(int diasValidadeRestantes)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DIASVALIDADERESTANTES$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DIASVALIDADERESTANTES$0);
//            }
//            target.setIntValue(diasValidadeRestantes);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "diasValidadeRestantes" element
//     */
//    public void xsetDiasValidadeRestantes(org.apache.xmlbeans.XmlInt diasValidadeRestantes)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlInt target = null;
//            target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(DIASVALIDADERESTANTES$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlInt)get_store().add_element_user(DIASVALIDADERESTANTES$0);
//            }
//            target.set(diasValidadeRestantes);
//        }
//    }
//    
//    /**
//     * Unsets the "diasValidadeRestantes" element
//     */
//    public void unsetDiasValidadeRestantes()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(DIASVALIDADERESTANTES$0, 0);
//        }
//    }
//}
