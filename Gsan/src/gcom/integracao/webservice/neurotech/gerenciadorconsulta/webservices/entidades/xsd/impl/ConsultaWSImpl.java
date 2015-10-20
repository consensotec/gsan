///*
// * XML Type:  ConsultaWS
// * Namespace: http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl;
///**
// * An XML ConsultaWS(@http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public class ConsultaWSImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS
//{
//    
//    public ConsultaWSImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName IDCONSULTA$0 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "idConsulta");
//    private static final javax.xml.namespace.QName LSPARAMETROS$2 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "lsParametros");
//    
//    
//    /**
//     * Gets the "idConsulta" element
//     */
//    public java.lang.String getIdConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(IDCONSULTA$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target.getStringValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "idConsulta" element
//     */
//    public org.apache.xmlbeans.XmlString xgetIdConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(IDCONSULTA$0, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil "idConsulta" element
//     */
//    public boolean isNilIdConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(IDCONSULTA$0, 0);
//            if (target == null) return false;
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * True if has "idConsulta" element
//     */
//    public boolean isSetIdConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(IDCONSULTA$0) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "idConsulta" element
//     */
//    public void setIdConsulta(java.lang.String idConsulta)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(IDCONSULTA$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(IDCONSULTA$0);
//            }
//            target.setStringValue(idConsulta);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "idConsulta" element
//     */
//    public void xsetIdConsulta(org.apache.xmlbeans.XmlString idConsulta)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(IDCONSULTA$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(IDCONSULTA$0);
//            }
//            target.set(idConsulta);
//        }
//    }
//    
//    /**
//     * Nils the "idConsulta" element
//     */
//    public void setNilIdConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(IDCONSULTA$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(IDCONSULTA$0);
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Unsets the "idConsulta" element
//     */
//    public void unsetIdConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(IDCONSULTA$0, 0);
//        }
//    }
//    
//    /**
//     * Gets array of all "lsParametros" elements
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] getLsParametrosArray()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            java.util.List targetList = new java.util.ArrayList();
//            get_store().find_all_element_users(LSPARAMETROS$2, targetList);
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] result = new gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[targetList.size()];
//            targetList.toArray(result);
//            return result;
//        }
//    }
//    
//    /**
//     * Gets ith "lsParametros" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS getLsParametrosArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSPARAMETROS$2, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil ith "lsParametros" element
//     */
//    public boolean isNilLsParametrosArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSPARAMETROS$2, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * Returns number of "lsParametros" element
//     */
//    public int sizeOfLsParametrosArray()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(LSPARAMETROS$2);
//        }
//    }
//    
//    /**
//     * Sets array of all "lsParametros" element
//     */
//    public void setLsParametrosArray(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] lsParametrosArray)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            arraySetterHelper(lsParametrosArray, LSPARAMETROS$2);
//        }
//    }
//    
//    /**
//     * Sets ith "lsParametros" element
//     */
//    public void setLsParametrosArray(int i, gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS lsParametros)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSPARAMETROS$2, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            target.set(lsParametros);
//        }
//    }
//    
//    /**
//     * Nils the ith "lsParametros" element
//     */
//    public void setNilLsParametrosArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSPARAMETROS$2, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Inserts and returns a new empty value (as xml) as the ith "lsParametros" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS insertNewLsParametros(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().insert_element_user(LSPARAMETROS$2, i);
//            return target;
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty value (as xml) as the last "lsParametros" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS addNewLsParametros()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().add_element_user(LSPARAMETROS$2);
//            return target;
//        }
//    }
//    
//    /**
//     * Removes the ith "lsParametros" element
//     */
//    public void removeLsParametros(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(LSPARAMETROS$2, i);
//        }
//    }
//}
