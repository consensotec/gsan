///*
// * An XML document type.
// * Localname: consultarLog
// * Namespace: http://fachada.webservices.gateway.neurotech.com.br
// * Java type: gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument
// *
// * Automatically generated - do not modify.
// */
//package gcom.integracao.webservice.neurotech.gateway.webservices.fachada;
//
//
///**
// * A document containing one consultarLog(@http://fachada.webservices.gateway.neurotech.com.br) element.
// *
// * This is a complex type.
// */
//public interface ConsultarLogDocument extends org.apache.xmlbeans.XmlObject
//{
//    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
//        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ConsultarLogDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("consultarlogd482doctype");
//    
//    /**
//     * Gets the "consultarLog" element
//     */
//    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog getConsultarLog();
//    
//    /**
//     * Sets the "consultarLog" element
//     */
//    void setConsultarLog(gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog consultarLog);
//    
//    /**
//     * Appends and returns a new empty "consultarLog" element
//     */
//    gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog addNewConsultarLog();
//    
//    /**
//     * An XML consultarLog(@http://fachada.webservices.gateway.neurotech.com.br).
//     *
//     * This is a complex type.
//     */
//    public interface ConsultarLog extends org.apache.xmlbeans.XmlObject
//    {
//        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
//            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ConsultarLog.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("consultarlogf81felemtype");
//        
//        /**
//         * Gets the "pIdentificador" element
//         */
//        int getPIdentificador();
//        
//        /**
//         * Gets (as xml) the "pIdentificador" element
//         */
//        org.apache.xmlbeans.XmlInt xgetPIdentificador();
//        
//        /**
//         * True if has "pIdentificador" element
//         */
//        boolean isSetPIdentificador();
//        
//        /**
//         * Sets the "pIdentificador" element
//         */
//        void setPIdentificador(int pIdentificador);
//        
//        /**
//         * Sets (as xml) the "pIdentificador" element
//         */
//        void xsetPIdentificador(org.apache.xmlbeans.XmlInt pIdentificador);
//        
//        /**
//         * Unsets the "pIdentificador" element
//         */
//        void unsetPIdentificador();
//        
//        /**
//         * Gets the "pLogin" element
//         */
//        java.lang.String getPLogin();
//        
//        /**
//         * Gets (as xml) the "pLogin" element
//         */
//        org.apache.xmlbeans.XmlString xgetPLogin();
//        
//        /**
//         * Tests for nil "pLogin" element
//         */
//        boolean isNilPLogin();
//        
//        /**
//         * True if has "pLogin" element
//         */
//        boolean isSetPLogin();
//        
//        /**
//         * Sets the "pLogin" element
//         */
//        void setPLogin(java.lang.String pLogin);
//        
//        /**
//         * Sets (as xml) the "pLogin" element
//         */
//        void xsetPLogin(org.apache.xmlbeans.XmlString pLogin);
//        
//        /**
//         * Nils the "pLogin" element
//         */
//        void setNilPLogin();
//        
//        /**
//         * Unsets the "pLogin" element
//         */
//        void unsetPLogin();
//        
//        /**
//         * Gets the "pSenha" element
//         */
//        java.lang.String getPSenha();
//        
//        /**
//         * Gets (as xml) the "pSenha" element
//         */
//        org.apache.xmlbeans.XmlString xgetPSenha();
//        
//        /**
//         * Tests for nil "pSenha" element
//         */
//        boolean isNilPSenha();
//        
//        /**
//         * True if has "pSenha" element
//         */
//        boolean isSetPSenha();
//        
//        /**
//         * Sets the "pSenha" element
//         */
//        void setPSenha(java.lang.String pSenha);
//        
//        /**
//         * Sets (as xml) the "pSenha" element
//         */
//        void xsetPSenha(org.apache.xmlbeans.XmlString pSenha);
//        
//        /**
//         * Nils the "pSenha" element
//         */
//        void setNilPSenha();
//        
//        /**
//         * Unsets the "pSenha" element
//         */
//        void unsetPSenha();
//        
//        /**
//         * Gets array of all "pLogIds" elements
//         */
//        java.lang.String[] getPLogIdsArray();
//        
//        /**
//         * Gets ith "pLogIds" element
//         */
//        java.lang.String getPLogIdsArray(int i);
//        
//        /**
//         * Gets (as xml) array of all "pLogIds" elements
//         */
//        org.apache.xmlbeans.XmlString[] xgetPLogIdsArray();
//        
//        /**
//         * Gets (as xml) ith "pLogIds" element
//         */
//        org.apache.xmlbeans.XmlString xgetPLogIdsArray(int i);
//        
//        /**
//         * Tests for nil ith "pLogIds" element
//         */
//        boolean isNilPLogIdsArray(int i);
//        
//        /**
//         * Returns number of "pLogIds" element
//         */
//        int sizeOfPLogIdsArray();
//        
//        /**
//         * Sets array of all "pLogIds" element
//         */
//        void setPLogIdsArray(java.lang.String[] pLogIdsArray);
//        
//        /**
//         * Sets ith "pLogIds" element
//         */
//        void setPLogIdsArray(int i, java.lang.String pLogIds);
//        
//        /**
//         * Sets (as xml) array of all "pLogIds" element
//         */
//        void xsetPLogIdsArray(org.apache.xmlbeans.XmlString[] pLogIdsArray);
//        
//        /**
//         * Sets (as xml) ith "pLogIds" element
//         */
//        void xsetPLogIdsArray(int i, org.apache.xmlbeans.XmlString pLogIds);
//        
//        /**
//         * Nils the ith "pLogIds" element
//         */
//        void setNilPLogIdsArray(int i);
//        
//        /**
//         * Inserts the value as the ith "pLogIds" element
//         */
//        void insertPLogIds(int i, java.lang.String pLogIds);
//        
//        /**
//         * Appends the value as the last "pLogIds" element
//         */
//        void addPLogIds(java.lang.String pLogIds);
//        
//        /**
//         * Inserts and returns a new empty value (as xml) as the ith "pLogIds" element
//         */
//        org.apache.xmlbeans.XmlString insertNewPLogIds(int i);
//        
//        /**
//         * Appends and returns a new empty value (as xml) as the last "pLogIds" element
//         */
//        org.apache.xmlbeans.XmlString addNewPLogIds();
//        
//        /**
//         * Removes the ith "pLogIds" element
//         */
//        void removePLogIds(int i);
//        
//        /**
//         * A factory class with static methods for creating instances
//         * of this type.
//         */
//        
//        public static final class Factory
//        {
//            public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog newInstance() {
//              return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
//            
//            public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog newInstance(org.apache.xmlbeans.XmlOptions options) {
//              return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
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
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument newInstance() {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
//        
//        /** @param xmlAsString the string value to parse */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
//        
//        /** @param file the file from which to load an xml document */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
//        
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
//        
//        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
//        public static gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
//          return (gcom.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
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
