///*
// * XML Type:  ConsultaWSComparacaoFonetica
// * Namespace: http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd;
//
//
///**
// * An XML ConsultaWSComparacaoFonetica(@http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public interface ConsultaWSComparacaoFonetica extends gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS
//{
//    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
//        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ConsultaWSComparacaoFonetica.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("consultawscomparacaofoneticad186type");
//    
//    /**
//     * Gets array of all "lsCamposComparacaoFonetica" elements
//     */
//    gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] getLsCamposComparacaoFoneticaArray();
//    
//    /**
//     * Gets ith "lsCamposComparacaoFonetica" element
//     */
//    gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS getLsCamposComparacaoFoneticaArray(int i);
//    
//    /**
//     * Tests for nil ith "lsCamposComparacaoFonetica" element
//     */
//    boolean isNilLsCamposComparacaoFoneticaArray(int i);
//    
//    /**
//     * Returns number of "lsCamposComparacaoFonetica" element
//     */
//    int sizeOfLsCamposComparacaoFoneticaArray();
//    
//    /**
//     * Sets array of all "lsCamposComparacaoFonetica" element
//     */
//    void setLsCamposComparacaoFoneticaArray(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS[] lsCamposComparacaoFoneticaArray);
//    
//    /**
//     * Sets ith "lsCamposComparacaoFonetica" element
//     */
//    void setLsCamposComparacaoFoneticaArray(int i, gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS lsCamposComparacaoFonetica);
//    
//    /**
//     * Nils the ith "lsCamposComparacaoFonetica" element
//     */
//    void setNilLsCamposComparacaoFoneticaArray(int i);
//    
//    /**
//     * Inserts and returns a new empty value (as xml) as the ith "lsCamposComparacaoFonetica" element
//     */
//    gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS insertNewLsCamposComparacaoFonetica(int i);
//    
//    /**
//     * Appends and returns a new empty value (as xml) as the last "lsCamposComparacaoFonetica" element
//     */
//    gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS addNewLsCamposComparacaoFonetica();
//    
//    /**
//     * Removes the ith "lsCamposComparacaoFonetica" element
//     */
//    void removeLsCamposComparacaoFonetica(int i);
//    
//    /**
//     * A factory class with static methods for creating instances
//     * of this type.
//     */
//    
//    public static final class Factory
//    {
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica newInstance() {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica newInstance(org.apache.xmlbeans.XmlOptions options) {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
//        
//        /** @param xmlAsString the string value to parse */
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
//        
//        /** @param file the file from which to load an xml document */
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return (gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
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
