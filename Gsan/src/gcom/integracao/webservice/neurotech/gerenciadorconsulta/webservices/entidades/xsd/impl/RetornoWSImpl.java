///*
// * XML Type:  RetornoWS
// * Namespace: http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWS
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl;
///**
// * An XML RetornoWS(@http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public class RetornoWSImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWS
//{
//    
//    public RetornoWSImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName CDMENSAGEM$0 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "cdMensagem");
//    private static final javax.xml.namespace.QName DSMENSAGEM$2 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "dsMensagem");
//    private static final javax.xml.namespace.QName IDCONSULTA$4 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "idConsulta");
//    private static final javax.xml.namespace.QName LSRETORNO$6 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "lsRetorno");
//    
//    
//    /**
//     * Gets the "cdMensagem" element
//     */
//    public java.lang.String getCdMensagem()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CDMENSAGEM$0, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target.getStringValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "cdMensagem" element
//     */
//    public org.apache.xmlbeans.XmlString xgetCdMensagem()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CDMENSAGEM$0, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil "cdMensagem" element
//     */
//    public boolean isNilCdMensagem()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CDMENSAGEM$0, 0);
//            if (target == null) return false;
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * True if has "cdMensagem" element
//     */
//    public boolean isSetCdMensagem()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(CDMENSAGEM$0) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "cdMensagem" element
//     */
//    public void setCdMensagem(java.lang.String cdMensagem)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CDMENSAGEM$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CDMENSAGEM$0);
//            }
//            target.setStringValue(cdMensagem);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "cdMensagem" element
//     */
//    public void xsetCdMensagem(org.apache.xmlbeans.XmlString cdMensagem)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CDMENSAGEM$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CDMENSAGEM$0);
//            }
//            target.set(cdMensagem);
//        }
//    }
//    
//    /**
//     * Nils the "cdMensagem" element
//     */
//    public void setNilCdMensagem()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CDMENSAGEM$0, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CDMENSAGEM$0);
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Unsets the "cdMensagem" element
//     */
//    public void unsetCdMensagem()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(CDMENSAGEM$0, 0);
//        }
//    }
//    
//    /**
//     * Gets the "dsMensagem" element
//     */
//    public java.lang.String getDsMensagem()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DSMENSAGEM$2, 0);
//            if (target == null)
//            {
//                return null;
//            }
//            return target.getStringValue();
//        }
//    }
//    
//    /**
//     * Gets (as xml) the "dsMensagem" element
//     */
//    public org.apache.xmlbeans.XmlString xgetDsMensagem()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DSMENSAGEM$2, 0);
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil "dsMensagem" element
//     */
//    public boolean isNilDsMensagem()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DSMENSAGEM$2, 0);
//            if (target == null) return false;
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * True if has "dsMensagem" element
//     */
//    public boolean isSetDsMensagem()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(DSMENSAGEM$2) != 0;
//        }
//    }
//    
//    /**
//     * Sets the "dsMensagem" element
//     */
//    public void setDsMensagem(java.lang.String dsMensagem)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.SimpleValue target = null;
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DSMENSAGEM$2, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DSMENSAGEM$2);
//            }
//            target.setStringValue(dsMensagem);
//        }
//    }
//    
//    /**
//     * Sets (as xml) the "dsMensagem" element
//     */
//    public void xsetDsMensagem(org.apache.xmlbeans.XmlString dsMensagem)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DSMENSAGEM$2, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DSMENSAGEM$2);
//            }
//            target.set(dsMensagem);
//        }
//    }
//    
//    /**
//     * Nils the "dsMensagem" element
//     */
//    public void setNilDsMensagem()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            org.apache.xmlbeans.XmlString target = null;
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DSMENSAGEM$2, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DSMENSAGEM$2);
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Unsets the "dsMensagem" element
//     */
//    public void unsetDsMensagem()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(DSMENSAGEM$2, 0);
//        }
//    }
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
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(IDCONSULTA$4, 0);
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
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(IDCONSULTA$4, 0);
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
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(IDCONSULTA$4, 0);
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
//            return get_store().count_elements(IDCONSULTA$4) != 0;
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
//            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(IDCONSULTA$4, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(IDCONSULTA$4);
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
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(IDCONSULTA$4, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(IDCONSULTA$4);
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
//            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(IDCONSULTA$4, 0);
//            if (target == null)
//            {
//                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(IDCONSULTA$4);
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
//            get_store().remove_element(IDCONSULTA$4, 0);
//        }
//    }
//    
//    /**
//     * Gets array of all "lsRetorno" elements
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] getLsRetornoArray()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            java.util.List targetList = new java.util.ArrayList();
//            get_store().find_all_element_users(LSRETORNO$6, targetList);
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] result = new gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[targetList.size()];
//            targetList.toArray(result);
//            return result;
//        }
//    }
//    
//    /**
//     * Gets ith "lsRetorno" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS getLsRetornoArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSRETORNO$6, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil ith "lsRetorno" element
//     */
//    public boolean isNilLsRetornoArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSRETORNO$6, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * Returns number of "lsRetorno" element
//     */
//    public int sizeOfLsRetornoArray()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(LSRETORNO$6);
//        }
//    }
//    
//    /**
//     * Sets array of all "lsRetorno" element
//     */
//    public void setLsRetornoArray(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] lsRetornoArray)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            arraySetterHelper(lsRetornoArray, LSRETORNO$6);
//        }
//    }
//    
//    /**
//     * Sets ith "lsRetorno" element
//     */
//    public void setLsRetornoArray(int i, gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS lsRetorno)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSRETORNO$6, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            target.set(lsRetorno);
//        }
//    }
//    
//    /**
//     * Nils the ith "lsRetorno" element
//     */
//    public void setNilLsRetornoArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSRETORNO$6, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Inserts and returns a new empty value (as xml) as the ith "lsRetorno" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS insertNewLsRetorno(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().insert_element_user(LSRETORNO$6, i);
//            return target;
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty value (as xml) as the last "lsRetorno" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS addNewLsRetorno()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().add_element_user(LSRETORNO$6);
//            return target;
//        }
//    }
//    
//    /**
//     * Removes the ith "lsRetorno" element
//     */
//    public void removeLsRetorno(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(LSRETORNO$6, i);
//        }
//    }
//}
