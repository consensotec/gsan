///*
// * XML Type:  RetornoWSHistoricoCF
// * Namespace: http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd;
//
//
///**
// * An XML RetornoWSHistoricoCF(@http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public interface RetornoWSHistoricoCF extends gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistorico
//{
//    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
//        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(RetornoWSHistoricoCF.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("retornowshistoricocf4244type");
//    
//    /**
//     * Gets array of all "lsRetornoComparacaoFonetica" elements
//     */
//    gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] getLsRetornoComparacaoFoneticaArray();
//    
//    /**
//     * Gets ith "lsRetornoComparacaoFonetica" element
//     */
//    gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS getLsRetornoComparacaoFoneticaArray(int i);
//    
//    /**
//     * Tests for nil ith "lsRetornoComparacaoFonetica" element
//     */
//    boolean isNilLsRetornoComparacaoFoneticaArray(int i);
//    
//    /**
//     * Returns number of "lsRetornoComparacaoFonetica" element
//     */
//    int sizeOfLsRetornoComparacaoFoneticaArray();
//    
//    /**
//     * Sets array of all "lsRetornoComparacaoFonetica" element
//     */
//    void setLsRetornoComparacaoFoneticaArray(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] lsRetornoComparacaoFoneticaArray);
//    
//    /**
//     * Sets ith "lsRetornoComparacaoFonetica" element
//     */
//    void setLsRetornoComparacaoFoneticaArray(int i, gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS lsRetornoComparacaoFonetica);
//    
//    /**
//     * Nils the ith "lsRetornoComparacaoFonetica" element
//     */
//    void setNilLsRetornoComparacaoFoneticaArray(int i);
//    
//    /**
//     * Inserts and returns a new empty value (as xml) as the ith "lsRetornoComparacaoFonetica" element
//     */
//    gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS insertNewLsRetornoComparacaoFonetica(int i);
//    
//    /**
//     * Appends and returns a new empty value (as xml) as the last "lsRetornoComparacaoFonetica" element
//     */
//    gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS addNewLsRetornoComparacaoFonetica();
//    
//    /**
//     * Removes the ith "lsRetornoComparacaoFonetica" element
//     */
//    void removeLsRetornoComparacaoFonetica(int i);
//    
//    /**
//     * A factory class with static methods for creating instances
//     * of this type.
//     */
//    
//    public static final class Factory
//    {
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF newInstance() {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF newInstance(org.apache.xmlbeans.XmlOptions options) {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
//        
//        /** @param xmlAsString the string value to parse */
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
//        
//        /** @param file the file from which to load an xml document */
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
//        
//        private Factory() { } // No instance of this class allowed
//    }
//}
