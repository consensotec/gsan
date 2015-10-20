///*
// * An XML document type.
// * Localname: consultarGatewayComparacaoFoneticaResponse
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada;
//
//
///**
// * A document containing one consultarGatewayComparacaoFoneticaResponse(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public interface ConsultarGatewayComparacaoFoneticaResponseDocument extends org.apache.xmlbeans.XmlObject
//{
//    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
//        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ConsultarGatewayComparacaoFoneticaResponseDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("consultargatewaycomparacaofoneticaresponse5646doctype");
//    
//    /**
//     * Gets the "consultarGatewayComparacaoFoneticaResponse" element
//     */
//    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse getConsultarGatewayComparacaoFoneticaResponse();
//    
//    /**
//     * Sets the "consultarGatewayComparacaoFoneticaResponse" element
//     */
//    void setConsultarGatewayComparacaoFoneticaResponse(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse consultarGatewayComparacaoFoneticaResponse);
//    
//    /**
//     * Appends and returns a new empty "consultarGatewayComparacaoFoneticaResponse" element
//     */
//    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse addNewConsultarGatewayComparacaoFoneticaResponse();
//    
//    /**
//     * An XML consultarGatewayComparacaoFoneticaResponse(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public interface ConsultarGatewayComparacaoFoneticaResponse extends org.apache.xmlbeans.XmlObject
//    {
//        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
//            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ConsultarGatewayComparacaoFoneticaResponse.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("consultargatewaycomparacaofoneticaresponseee1felemtype");
//        
//        /**
//         * Gets array of all "return" elements
//         */
//        gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica[] getReturnArray();
//        
//        /**
//         * Gets ith "return" element
//         */
//        gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica getReturnArray(int i);
//        
//        /**
//         * Tests for nil ith "return" element
//         */
//        boolean isNilReturnArray(int i);
//        
//        /**
//         * Returns number of "return" element
//         */
//        int sizeOfReturnArray();
//        
//        /**
//         * Sets array of all "return" element
//         */
//        void setReturnArray(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica[] xreturnArray);
//        
//        /**
//         * Sets ith "return" element
//         */
//        void setReturnArray(int i, gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica xreturn);
//        
//        /**
//         * Nils the ith "return" element
//         */
//        void setNilReturnArray(int i);
//        
//        /**
//         * Inserts and returns a new empty value (as xml) as the ith "return" element
//         */
//        gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica insertNewReturn(int i);
//        
//        /**
//         * Appends and returns a new empty value (as xml) as the last "return" element
//         */
//        gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica addNewReturn();
//        
//        /**
//         * Removes the ith "return" element
//         */
//        void removeReturn(int i);
//        
//        /**
//         * A factory class with static methods for creating instances
//         * of this type.
//         */
//        
//        public static final class Factory
//        {
//            public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse newInstance() {
//              return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
//            
//            public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse newInstance(org.apache.xmlbeans.XmlOptions options) {
//              return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument.ConsultarGatewayComparacaoFoneticaResponse) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
//            
//            private Factory() { } // No instance of this class allowed
//        }
//    }
//    
//    /**
//     * A factory class with static methods for creating instances
//     * of this type.
//     */
//    
//    public static final class Factory
//    {
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument newInstance() {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
//        
//        /** @param xmlAsString the string value to parse */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
//        
//        /** @param file the file from which to load an xml document */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarGatewayComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
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
