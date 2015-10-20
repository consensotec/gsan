///*
// * XML Type:  ConsultaWSComparacaoFonetica
// * Namespace: http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl;
///**
// * An XML ConsultaWSComparacaoFonetica(@http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public class ConsultaWSComparacaoFoneticaImpl extends gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.impl.ConsultaWSImpl implements gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica
//{
//    
//    public ConsultaWSComparacaoFoneticaImpl(org.apache.xmlbeans.SchemaType sType)
//    {
//        super(sType);
//    }
//    
//    private static final javax.xml.namespace.QName LSCAMPOSCOMPARACAOFONETICA$0 = 
//        new javax.xml.namespace.QName("http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd", "lsCamposComparacaoFonetica");
//    
//    
//    /**
//     * Gets array of all "lsCamposComparacaoFonetica" elements
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] getLsCamposComparacaoFoneticaArray()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            java.util.List targetList = new java.util.ArrayList();
//            get_store().find_all_element_users(LSCAMPOSCOMPARACAOFONETICA$0, targetList);
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] result = new gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[targetList.size()];
//            targetList.toArray(result);
//            return result;
//        }
//    }
//    
//    /**
//     * Gets ith "lsCamposComparacaoFonetica" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS getLsCamposComparacaoFoneticaArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSCAMPOSCOMPARACAOFONETICA$0, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            return target;
//        }
//    }
//    
//    /**
//     * Tests for nil ith "lsCamposComparacaoFonetica" element
//     */
//    public boolean isNilLsCamposComparacaoFoneticaArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSCAMPOSCOMPARACAOFONETICA$0, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            return target.isNil();
//        }
//    }
//    
//    /**
//     * Returns number of "lsCamposComparacaoFonetica" element
//     */
//    public int sizeOfLsCamposComparacaoFoneticaArray()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            return get_store().count_elements(LSCAMPOSCOMPARACAOFONETICA$0);
//        }
//    }
//    
//    /**
//     * Sets array of all "lsCamposComparacaoFonetica" element
//     */
//    public void setLsCamposComparacaoFoneticaArray(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] lsCamposComparacaoFoneticaArray)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            arraySetterHelper(lsCamposComparacaoFoneticaArray, LSCAMPOSCOMPARACAOFONETICA$0);
//        }
//    }
//    
//    /**
//     * Sets ith "lsCamposComparacaoFonetica" element
//     */
//    public void setLsCamposComparacaoFoneticaArray(int i, gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS lsCamposComparacaoFonetica)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSCAMPOSCOMPARACAOFONETICA$0, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            target.set(lsCamposComparacaoFonetica);
//        }
//    }
//    
//    /**
//     * Nils the ith "lsCamposComparacaoFonetica" element
//     */
//    public void setNilLsCamposComparacaoFoneticaArray(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().find_element_user(LSCAMPOSCOMPARACAOFONETICA$0, i);
//            if (target == null)
//            {
//                throw new IndexOutOfBoundsException();
//            }
//            target.setNil();
//        }
//    }
//    
//    /**
//     * Inserts and returns a new empty value (as xml) as the ith "lsCamposComparacaoFonetica" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS insertNewLsCamposComparacaoFonetica(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().insert_element_user(LSCAMPOSCOMPARACAOFONETICA$0, i);
//            return target;
//        }
//    }
//    
//    /**
//     * Appends and returns a new empty value (as xml) as the last "lsCamposComparacaoFonetica" element
//     */
//    public gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS addNewLsCamposComparacaoFonetica()
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS target = null;
//            target = (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS)get_store().add_element_user(LSCAMPOSCOMPARACAOFONETICA$0);
//            return target;
//        }
//    }
//    
//    /**
//     * Removes the ith "lsCamposComparacaoFonetica" element
//     */
//    public void removeLsCamposComparacaoFonetica(int i)
//    {
//        synchronized (monitor())
//        {
//            check_orphaned();
//            get_store().remove_element(LSCAMPOSCOMPARACAOFONETICA$0, i);
//        }
//    }
//}
