///*
// * XML Type:  RetornoWSIdLog
// * Namespace: http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl;
///**
// * An XML RetornoWSIdLog(@http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public class RetornoWSIdLogImpl extends gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl.RetornoWSImpl implements gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog
//{
//    
//    public RetornoWSIdLogImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName DATAREALIZACAOCONSULTA$0 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "dataRealizacaoConsulta");
//    private static final javax.xml.namespace.QName IDLOG$2 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "idLog");
//    private static final javax.xml.namespace.QName LSRETORNOIDLOG$4 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "lsRetornoIdLog");
//    private static final javax.xml.namespace.QName ORIGEMCONSULTA$6 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "origemConsulta");
//    
//    
//    /**
//     * Gets the "dataRealizacaoConsulta" element
//     */
//    public java.lang.String getDataRealizacaoConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DATAREALIZACAOCONSULTA$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target.getStringValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "dataRealizacaoConsulta" element
//     */
//    public org.apache.xmlbeans.XmlString xgetDataRealizacaoConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DATAREALIZACAOCONSULTA$0, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil "dataRealizacaoConsulta" element
//     */
//    public boolean isNilDataRealizacaoConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DATAREALIZACAOCONSULTA$0, 0);
//            if (target == null) return false;
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * True if has "dataRealizacaoConsulta" element
//     */
//    public boolean isSetDataRealizacaoConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(DATAREALIZACAOCONSULTA$0) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "dataRealizacaoConsulta" element
//     */
//    public void setDataRealizacaoConsulta(java.lang.String dataRealizacaoConsulta)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DATAREALIZACAOCONSULTA$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DATAREALIZACAOCONSULTA$0);
//            }
//            target.setStringValue(dataRealizacaoConsulta);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "dataRealizacaoConsulta" element
//     */
//    public void xsetDataRealizacaoConsulta(org.apache.xmlbeans.XmlString dataRealizacaoConsulta)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DATAREALIZACAOCONSULTA$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DATAREALIZACAOCONSULTA$0);
//            }
//            target.set(dataRealizacaoConsulta);
//        }
//    }
//    
//    /**
//     * Nils the "dataRealizacaoConsulta" element
//     */
//    public void setNilDataRealizacaoConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DATAREALIZACAOCONSULTA$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DATAREALIZACAOCONSULTA$0);
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Unsets the "dataRealizacaoConsulta" element
//     */
//    public void unsetDataRealizacaoConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(DATAREALIZACAOCONSULTA$0, 0);
//        }
//    }
//    
//    /**
//     * Gets the "idLog" element
//     */
//    public java.lang.String getIdLog()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(IDLOG$2, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target.getStringValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "idLog" element
//     */
//    public org.apache.xmlbeans.XmlString xgetIdLog()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(IDLOG$2, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil "idLog" element
//     */
//    public boolean isNilIdLog()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(IDLOG$2, 0);
//            if (target == null) return false;
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * True if has "idLog" element
//     */
//    public boolean isSetIdLog()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(IDLOG$2) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "idLog" element
//     */
//    public void setIdLog(java.lang.String idLog)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(IDLOG$2, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(IDLOG$2);
//            }
//            target.setStringValue(idLog);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "idLog" element
//     */
//    public void xsetIdLog(org.apache.xmlbeans.XmlString idLog)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(IDLOG$2, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(IDLOG$2);
//            }
//            target.set(idLog);
//        }
//    }
//    
//    /**
//     * Nils the "idLog" element
//     */
//    public void setNilIdLog()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(IDLOG$2, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(IDLOG$2);
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Unsets the "idLog" element
//     */
//    public void unsetIdLog()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(IDLOG$2, 0);
//        }
//    }
//    
//    /**
//     * Gets array of all "lsRetornoIdLog" elements
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog[] getLsRetornoIdLogArray()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            java.util.List targetList = new java.util.ArrayList();
//            get_store().find_all_element_users(LSRETORNOIDLOG$4, targetList);
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog[] result = new gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog[targetList.size()];
//            targetList.toArray(result);
//            return result;
//        }
//    }
//    
//    /**
//     * Gets ith "lsRetornoIdLog" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog getLsRetornoIdLogArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog)get_store().find_element_user(LSRETORNOIDLOG$4, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil ith "lsRetornoIdLog" element
//     */
//    public boolean isNilLsRetornoIdLogArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog)get_store().find_element_user(LSRETORNOIDLOG$4, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * Returns number of "lsRetornoIdLog" element
//     */
//    public int sizeOfLsRetornoIdLogArray()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(LSRETORNOIDLOG$4);
//        }
//    }
//    
//    /**
//     * Sets array of all "lsRetornoIdLog" element
//     */
//    public void setLsRetornoIdLogArray(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog[] lsRetornoIdLogArray)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            arraySetterHelper(lsRetornoIdLogArray, LSRETORNOIDLOG$4);
//        }
//    }
//    
//    /**
//     * Sets ith "lsRetornoIdLog" element
//     */
//    public void setLsRetornoIdLogArray(int i, gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog lsRetornoIdLog)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog)get_store().find_element_user(LSRETORNOIDLOG$4, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            target.set(lsRetornoIdLog);
//        }
//    }
//    
//    /**
//     * Nils the ith "lsRetornoIdLog" element
//     */
//    public void setNilLsRetornoIdLogArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog)get_store().find_element_user(LSRETORNOIDLOG$4, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Inserts and returns a new empty value (as xml) as the ith "lsRetornoIdLog" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog insertNewLsRetornoIdLog(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog)get_store().insert_element_user(LSRETORNOIDLOG$4, i);
//            return target;
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty value (as xml) as the last "lsRetornoIdLog" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog addNewLsRetornoIdLog()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog)get_store().add_element_user(LSRETORNOIDLOG$4);
//            return target;
//        }
//    }
//    
//    /**
//     * Removes the ith "lsRetornoIdLog" element
//     */
//    public void removeLsRetornoIdLog(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(LSRETORNOIDLOG$4, i);
//        }
//    }
//    
//    /**
//     * Gets the "origemConsulta" element
//     */
//    public java.lang.String getOrigemConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ORIGEMCONSULTA$6, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target.getStringValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "origemConsulta" element
//     */
//    public org.apache.xmlbeans.XmlString xgetOrigemConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ORIGEMCONSULTA$6, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil "origemConsulta" element
//     */
//    public boolean isNilOrigemConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ORIGEMCONSULTA$6, 0);
//            if (target == null) return false;
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * True if has "origemConsulta" element
//     */
//    public boolean isSetOrigemConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(ORIGEMCONSULTA$6) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "origemConsulta" element
//     */
//    public void setOrigemConsulta(java.lang.String origemConsulta)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ORIGEMCONSULTA$6, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ORIGEMCONSULTA$6);
//            }
//            target.setStringValue(origemConsulta);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "origemConsulta" element
//     */
//    public void xsetOrigemConsulta(org.apache.xmlbeans.XmlString origemConsulta)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ORIGEMCONSULTA$6, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ORIGEMCONSULTA$6);
//            }
//            target.set(origemConsulta);
//        }
//    }
//    
//    /**
//     * Nils the "origemConsulta" element
//     */
//    public void setNilOrigemConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ORIGEMCONSULTA$6, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ORIGEMCONSULTA$6);
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Unsets the "origemConsulta" element
//     */
//    public void unsetOrigemConsulta()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(ORIGEMCONSULTA$6, 0);
//        }
//    }
//}
