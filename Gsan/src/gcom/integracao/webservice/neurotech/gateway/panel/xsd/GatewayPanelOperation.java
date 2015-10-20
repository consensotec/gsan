///*
// * XML Type:  GatewayPanelOperation
// * Namespace: http://panel.gateway.neurotech.com.br/xsd
// * Java type: gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.panel.xsd;
//
//
///**
// * An XML GatewayPanelOperation(@http://panel.gateway.neurotech.com.br/xsd).
// *
// * This is a complex type.
// */
//public interface GatewayPanelOperation extends org.apache.xmlbeans.XmlObject
//{
//    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
//        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(GatewayPanelOperation.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("gatewaypaneloperation7558type");
//    
//    /**
//     * Gets the "login" element
//     */
//    java.lang.String getLogin();
//    
//    /**
//     * Gets (as xml) the "login" element
//     */
//    org.apache.xmlbeans.XmlString xgetLogin();
//    
//    /**
//     * Tests for nil "login" element
//     */
//    boolean isNilLogin();
//    
//    /**
//     * True if has "login" element
//     */
//    boolean isSetLogin();
//    
//    /**
//     * Sets the "login" element
//     */
//    void setLogin(java.lang.String login);
//    
//    /**
//     * Sets (as xml) the "login" element
//     */
//    void xsetLogin(org.apache.xmlbeans.XmlString login);
//    
//    /**
//     * Nils the "login" element
//     */
//    void setNilLogin();
//    
//    /**
//     * Unsets the "login" element
//     */
//    void unsetLogin();
//    
//    /**
//     * Gets the "operationID" element
//     */
//    int getOperationID();
//    
//    /**
//     * Gets (as xml) the "operationID" element
//     */
//    org.apache.xmlbeans.XmlInt xgetOperationID();
//    
//    /**
//     * True if has "operationID" element
//     */
//    boolean isSetOperationID();
//    
//    /**
//     * Sets the "operationID" element
//     */
//    void setOperationID(int operationID);
//    
//    /**
//     * Sets (as xml) the "operationID" element
//     */
//    void xsetOperationID(org.apache.xmlbeans.XmlInt operationID);
//    
//    /**
//     * Unsets the "operationID" element
//     */
//    void unsetOperationID();
//    
//    /**
//     * Gets the "operationXML" element
//     */
//    java.lang.String getOperationXML();
//    
//    /**
//     * Gets (as xml) the "operationXML" element
//     */
//    org.apache.xmlbeans.XmlString xgetOperationXML();
//    
//    /**
//     * Tests for nil "operationXML" element
//     */
//    boolean isNilOperationXML();
//    
//    /**
//     * True if has "operationXML" element
//     */
//    boolean isSetOperationXML();
//    
//    /**
//     * Sets the "operationXML" element
//     */
//    void setOperationXML(java.lang.String operationXML);
//    
//    /**
//     * Sets (as xml) the "operationXML" element
//     */
//    void xsetOperationXML(org.apache.xmlbeans.XmlString operationXML);
//    
//    /**
//     * Nils the "operationXML" element
//     */
//    void setNilOperationXML();
//    
//    /**
//     * Unsets the "operationXML" element
//     */
//    void unsetOperationXML();
//    
//    /**
//     * A factory class with static methods for creating instances
//     * of this type.
//     */
//    
//    public static final class Factory
//    {
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation newInstance() {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation newInstance(org.apache.xmlbeans.XmlOptions options) {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
//        
//        /** @param xmlAsString the string value to parse */
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
//        
//        /** @param file the file from which to load an xml document */
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return (gcom.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
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
