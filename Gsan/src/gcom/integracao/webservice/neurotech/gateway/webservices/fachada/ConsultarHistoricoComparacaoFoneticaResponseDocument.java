///*
// * An XML document type.
// * Localname: consultarHistoricoComparacaoFoneticaResponse
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada;
//
//
///**
// * A document containing one consultarHistoricoComparacaoFoneticaResponse(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public interface ConsultarHistoricoComparacaoFoneticaResponseDocument extends org.apache.xmlbeans.XmlObject
//{
//    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
//        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ConsultarHistoricoComparacaoFoneticaResponseDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("consultarhistoricocomparacaofoneticaresponse3bf2doctype");
//    
//    /**
//     * Gets the "consultarHistoricoComparacaoFoneticaResponse" element
//     */
//    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse getConsultarHistoricoComparacaoFoneticaResponse();
//    
//    /**
//     * Sets the "consultarHistoricoComparacaoFoneticaResponse" element
//     */
//    void setConsultarHistoricoComparacaoFoneticaResponse(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse consultarHistoricoComparacaoFoneticaResponse);
//    
//    /**
//     * Appends and returns a new empty "consultarHistoricoComparacaoFoneticaResponse" element
//     */
//    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse addNewConsultarHistoricoComparacaoFoneticaResponse();
//    
//    /**
//     * An XML consultarHistoricoComparacaoFoneticaResponse(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public interface ConsultarHistoricoComparacaoFoneticaResponse extends org.apache.xmlbeans.XmlObject
//    {
//        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
//            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ConsultarHistoricoComparacaoFoneticaResponse.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("consultarhistoricocomparacaofoneticaresponsef21felemtype");
//        
//        /**
//         * Gets the "return" element
//         */
//        gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF getReturn();
//        
//        /**
//         * Tests for nil "return" element
//         */
//        boolean isNilReturn();
//        
//        /**
//         * True if has "return" element
//         */
//        boolean isSetReturn();
//        
//        /**
//         * Sets the "return" element
//         */
//        void setReturn(gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF xreturn);
//        
//        /**
//         * Appends and returns a new empty "return" element
//         */
//        gcom.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF addNewReturn();
//        
//        /**
//         * Nils the "return" element
//         */
//        void setNilReturn();
//        
//        /**
//         * Unsets the "return" element
//         */
//        void unsetReturn();
//        
//        /**
//         * A factory class with static methods for creating instances
//         * of this type.
//         */
//        
//        public static final class Factory
//        {
//            public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse newInstance() {
//              return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
//            
//            public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse newInstance(org.apache.xmlbeans.XmlOptions options) {
//              return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument.ConsultarHistoricoComparacaoFoneticaResponse) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
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
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument newInstance() {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
//        
//        /** @param xmlAsString the string value to parse */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
//        
//        /** @param file the file from which to load an xml document */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFoneticaResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
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
