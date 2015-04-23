
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:23:23 CEST)
 */

        
            package gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd".equals(namespaceURI) &&
                  "RetornoWSComparacaoFonetica".equals(typeName)){
                   
                            return  gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFonetica.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd".equals(namespaceURI) &&
                  "RetornoWSHistoricoCF".equals(typeName)){
                   
                            return  gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistoricoCF.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd".equals(namespaceURI) &&
                  "ConsultaWS".equals(typeName)){
                   
                            return  gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWS.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://panel.gateway.neurotech.com.br/xsd".equals(namespaceURI) &&
                  "GatewayPanelOperation".equals(typeName)){
                   
                            return  gsan.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelOperation.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://panel.gateway.neurotech.com.br/xsd".equals(namespaceURI) &&
                  "GatewayPanelHTML".equals(typeName)){
                   
                            return  gsan.integracao.webservice.neurotech.gateway.panel.xsd.GatewayPanelHTML.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd".equals(namespaceURI) &&
                  "CampoWSIdLog".equals(typeName)){
                   
                            return  gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWSIdLog.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd".equals(namespaceURI) &&
                  "CampoWS".equals(typeName)){
                   
                            return  gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CampoWS.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd".equals(namespaceURI) &&
                  "RetornoWS".equals(typeName)){
                   
                            return  gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWS.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd".equals(namespaceURI) &&
                  "RetornoWSComparacaoFoneticaU".equals(typeName)){
                   
                            return  gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSComparacaoFoneticaU.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd".equals(namespaceURI) &&
                  "RetornoWSHistorico".equals(typeName)){
                   
                            return  gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSHistorico.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd".equals(namespaceURI) &&
                  "RetornoWSIdLog".equals(typeName)){
                   
                            return  gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.RetornoWSIdLog.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd".equals(namespaceURI) &&
                  "ConsultaWSComparacaoFonetica".equals(typeName)){
                   
                            return  gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://entidades.webservices.gerenciadorconsulta.neurotech.com.br/xsd".equals(namespaceURI) &&
                  "CacheWS".equals(typeName)){
                   
                            return  gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.CacheWS.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    