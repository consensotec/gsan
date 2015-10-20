///*
// * XML Type:  RetornoWSHistoricoCF
// * Namespace: http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl;
///**
// * An XML RetornoWSHistoricoCF(@http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public class RetornoWSHistoricoCFImpl extends gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl.RetornoWSHistoricoImpl implements gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF
//{
//    
//    public RetornoWSHistoricoCFImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName LSRETORNOCOMPARACAOFONETICA$0 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "lsRetornoComparacaoFonetica");
//    
//    
//    /**
//     * Gets array of all "lsRetornoComparacaoFonetica" elements
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] getLsRetornoComparacaoFoneticaArray()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            java.util.List targetList = new java.util.ArrayList();
//            get_store().find_all_element_users(LSRETORNOCOMPARACAOFONETICA$0, targetList);
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] result = new gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[targetList.size()];
//            targetList.toArray(result);
//            return result;
//        }
//    }
//    
//    /**
//     * Gets ith "lsRetornoComparacaoFonetica" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS getLsRetornoComparacaoFoneticaArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSRETORNOCOMPARACAOFONETICA$0, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil ith "lsRetornoComparacaoFonetica" element
//     */
//    public boolean isNilLsRetornoComparacaoFoneticaArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSRETORNOCOMPARACAOFONETICA$0, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * Returns number of "lsRetornoComparacaoFonetica" element
//     */
//    public int sizeOfLsRetornoComparacaoFoneticaArray()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(LSRETORNOCOMPARACAOFONETICA$0);
//        }
//    }
//    
//    /**
//     * Sets array of all "lsRetornoComparacaoFonetica" element
//     */
//    public void setLsRetornoComparacaoFoneticaArray(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] lsRetornoComparacaoFoneticaArray)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            arraySetterHelper(lsRetornoComparacaoFoneticaArray, LSRETORNOCOMPARACAOFONETICA$0);
//        }
//    }
//    
//    /**
//     * Sets ith "lsRetornoComparacaoFonetica" element
//     */
//    public void setLsRetornoComparacaoFoneticaArray(int i, gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS lsRetornoComparacaoFonetica)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSRETORNOCOMPARACAOFONETICA$0, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            target.set(lsRetornoComparacaoFonetica);
//        }
//    }
//    
//    /**
//     * Nils the ith "lsRetornoComparacaoFonetica" element
//     */
//    public void setNilLsRetornoComparacaoFoneticaArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSRETORNOCOMPARACAOFONETICA$0, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Inserts and returns a new empty value (as xml) as the ith "lsRetornoComparacaoFonetica" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS insertNewLsRetornoComparacaoFonetica(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().insert_element_user(LSRETORNOCOMPARACAOFONETICA$0, i);
//            return target;
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty value (as xml) as the last "lsRetornoComparacaoFonetica" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS addNewLsRetornoComparacaoFonetica()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().add_element_user(LSRETORNOCOMPARACAOFONETICA$0);
//            return target;
//        }
//    }
//    
//    /**
//     * Removes the ith "lsRetornoComparacaoFonetica" element
//     */
//    public void removeLsRetornoComparacaoFonetica(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(LSRETORNOCOMPARACAOFONETICA$0, i);
//        }
//    }
//}
