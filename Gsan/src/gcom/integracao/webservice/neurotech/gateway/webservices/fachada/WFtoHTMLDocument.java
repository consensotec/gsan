///*
// * An XML document type.
// * Localname: WFtoHTML
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada;
//
//
///**
// * A document containing one WFtoHTML(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public interface WFtoHTMLDocument extends org.apache.xmlbeans.XmlObject
//{
//    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
//        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(WFtoHTMLDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("wftohtmldd50doctype");
//    
//    /**
//     * Gets the "WFtoHTML" element
//     */
//    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML getWFtoHTML();
//    
//    /**
//     * Sets the "WFtoHTML" element
//     */
//    void setWFtoHTML(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML wFtoHTML);
//    
//    /**
//     * Appends and returns a new empty "WFtoHTML" element
//     */
//    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML addNewWFtoHTML();
//    
//    /**
//     * An XML WFtoHTML(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public interface WFtoHTML extends org.apache.xmlbeans.XmlObject
//    {
//        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
//            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(WFtoHTML.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("wftohtmlca5felemtype");
//        
//        /**
//         * Gets the "pOperation" element
//         */
//        gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation getPOperation();
//        
//        /**
//         * Tests for nil "pOperation" element
//         */
//        boolean isNilPOperation();
//        
//        /**
//         * True if has "pOperation" element
//         */
//        boolean isSetPOperation();
//        
//        /**
//         * Sets the "pOperation" element
//         */
//        void setPOperation(gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation pOperation);
//        
//        /**
//         * Appends and returns a new empty "pOperation" element
//         */
//        gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation addNewPOperation();
//        
//        /**
//         * Nils the "pOperation" element
//         */
//        void setNilPOperation();
//        
//        /**
//         * Unsets the "pOperation" element
//         */
//        void unsetPOperation();
//        
//        /**
//         * A factory class with static methods for creating instances
//         * of this type.
//         */
//        
//        public static final class Factory
//        {
//            public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML newInstance() {
//              return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
//            
//            public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML newInstance(org.apache.xmlbeans.XmlOptions options) {
//              return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument.WFtoHTML) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
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
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument newInstance() {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
//        
//        /** @param xmlAsString the string value to parse */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
//        
//        /** @param file the file from which to load an xml document */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.WFtoHTMLDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
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
