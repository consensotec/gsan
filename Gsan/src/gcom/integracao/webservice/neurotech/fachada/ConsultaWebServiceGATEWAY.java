//package gcom.integracao.webservice.neurotech.fachada;
//
//
//public class ConsultaWebServiceGATEWAY {
//
//	public ConsultaWebServiceGATEWAY() {
//	};
//
//////	public ConsultarReceitaFederal consultarPessoaFisica(String cpf, Usuario usuario, Cliente cliente, ClienteFone fonePrincipal, ClienteEndereco endereco)
//////			throws RemoteException, AxisFault {
//////		
//////		ConsultarReceitaFederal consultaRF = null;
//////		// Criação do objeto stub
//////		GATEWAY_WEBSERVICESStub stub = new GATEWAY_WEBSERVICESStub();
//////		stub._getServiceClient().getOptions()
//////				.setProperty(HTTPConstants.CHUNKED, false);
//////
//////		Options vOptions = stub._getServiceClient().getOptions();
//////
//////		// Setando o timeout de conexão com o webservice
//////		vOptions.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(120000));
//////		vOptions.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(
//////				120000));
//////
//////		// Autenticação do contratante
//////		ConsultarGateway consultarGateway = new ConsultarGateway();
//////		consultarGateway.setPIdentificador(35);
//////		consultarGateway.setPLogin("compesa");
//////		consultarGateway.setPSenha("compesa@neurotech");
//////
//////		ConsultaWS[] consultaWSs = new ConsultaWS[1];
//////
//////		// Consulta da Receita Federal Pessoa Física
//////		ConsultaWS consultaWS = new ConsultaWS();
//////		consultaWS.setIdConsulta("RFPF_IMG");
//////		CampoWS campoWS = new CampoWS();
//////		campoWS.setNmCampo("cpf");
//////		campoWS.setVlCampo(cpf);
//////
//////		consultaWS.setLsParametros(new CampoWS[] { campoWS });
//////		consultaWSs[0] = consultaWS;
//////
//////		consultarGateway.setPConsultas(consultaWSs);
//////		ConsultarGatewayResponse consultarGatewayResponse = stub
//////				.consultarGateway(consultarGateway);
//////
//////		if (consultarGatewayResponse.get_return().length == 1
//////				&& consultarGatewayResponse.get_return()[0]
//////						.getCdMensagem()
//////						.equals(MensagemRetornoReceitaFederal.CONSULTA_REALIZADA_COM_SUCESSO)) {
//////			String dataConsulta = consultarGatewayResponse.get_return()[0]
//////					.getLsRetorno()[0].getVlCampo(); 
//////			String situacaoCadastral = consultarGatewayResponse.get_return()[0]
//////					.getLsRetorno()[1].getVlCampo();
//////			String nomePessoaFisica = consultarGatewayResponse.get_return()[0]
//////					.getLsRetorno()[2].getVlCampo(); 
//////			
//////			if(nomePessoaFisica.length() > 50){
//////				nomePessoaFisica = nomePessoaFisica.substring(0, 49);
//////			}
//////			
//////			String comprovanteAutenticidade = consultarGatewayResponse
//////					.get_return()[0].getLsRetorno()[3].getVlCampo(); 
//////			String horaConsulta = consultarGatewayResponse.get_return()[0]
//////					.getLsRetorno()[4].getVlCampo(); 
//////			String origemConsulta = consultarGatewayResponse.get_return()[0]
//////					.getOrigemConsulta();
//////			String idLog = consultarGatewayResponse.get_return()[0].getIdLog();
//////
//////			consultaRF = new ConsultarReceitaFederal();
//////			if (idLog != null) {
//////				consultaRF.setIdLogConsulta(Integer.parseInt(idLog));
//////			} else {
//////				consultaRF.setIdLogConsulta(0);
//////			}
//////
//////			if(origemConsulta != null && origemConsulta.equals("Bureau Externo")){
//////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("0"));
//////			}else if(origemConsulta != null && origemConsulta.equals("Neurotech")){
//////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("1"));
//////			}else{
//////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("2"));
//////			}
//////
//////			MensagemRetornoReceitaFederal mensagemRF = new MensagemRetornoReceitaFederal();
//////			mensagemRF
//////					.setId(MensagemRetornoReceitaFederal.ID_CONSULTA_REALIZADA_COM_SUCESSO);
//////			mensagemRF.setCodigoMensagemRetorno(100);
//////			mensagemRF.setDescricaoMensagemRetorno(consultarGatewayResponse.get_return()[0].getDsMensagem());
//////			consultaRF.setMensagemRetornoReceitaFederal(mensagemRF);
//////
//////			if (fonePrincipal != null
//////					&& fonePrincipal.getFoneTipo().getId() == FoneTipo.COMERCIAL) {
//////				consultaRF.setCodigoDDDComercial(fonePrincipal.getDdd());
//////				consultaRF.setTelefoneComercialCliente(fonePrincipal
//////						.getTelefone());
//////			} else if (fonePrincipal != null
//////					&& fonePrincipal.getFoneTipo().getId() == FoneTipo.FAX) {
//////				consultaRF.setCodigoDDDComercial(fonePrincipal.getDdd());
//////				consultaRF.setTelefoneComercialCliente(fonePrincipal
//////						.getTelefone());
//////			} else if (fonePrincipal != null
//////					&& fonePrincipal.getFoneTipo().getId() == FoneTipo.RESIDENCIAL) {
//////				consultaRF.setCodigoDDDTelefoneResidencialCliente(fonePrincipal
//////						.getDdd());
//////				consultaRF.setNumeroTelefoneResidencialCliente(fonePrincipal
//////						.getTelefone());
//////			} else if (fonePrincipal != null
//////					&& fonePrincipal.getFoneTipo().getId() == FoneTipo.CELULAR) {
//////				consultaRF.setCodigoDDDCelularCliente(fonePrincipal.getDdd());
//////				consultaRF.setNumeroCelularCliente(fonePrincipal.getTelefone());
//////			}
//////
//////			if (endereco != null) {
//////				if (endereco.getLogradouroCep() != null) {
//////					consultaRF.setLogradouroCliente(endereco.getLogradouroCep()
//////							.getLogradouro().getNome());
//////					consultaRF.setCepCliente(endereco.getLogradouroCep()
//////							.getCep().getCodigo().intValue());
//////				}
//////
//////				if (endereco.getNumero() != null) {
//////					consultaRF.setNumeroEnderecoCliente(endereco.getNumero());
//////				}
//////
//////				if (endereco.getComplemento() != null) {
//////					consultaRF.setComplementoEnderecoCliente(endereco
//////							.getComplemento());
//////				}
//////
//////				if (endereco.getLogradouroBairro() != null) {
//////					consultaRF.setBairroCliente(endereco.getLogradouroBairro()
//////							.getBairro().getNome());
//////					consultaRF.setCidadeCliente(endereco.getLogradouroBairro()
//////							.getBairro().getMunicipio().getNome());
//////					consultaRF.setUfCliente(endereco.getLogradouroBairro()
//////							.getBairro().getMunicipio().getUnidadeFederacao()
//////							.getSigla());
//////				}
//////			}
//////
//////			if (usuario != null) {
//////				if(usuario.getCpf() != null){
//////					consultaRF.setCpfUsuario(usuario.getCpf());
//////				}
//////				consultaRF.setLoginUsuario(usuario.getLogin());
//////				consultaRF.setUsuarioSolicitante(usuario);
//////			}
//////
//////			if (cliente != null) {
//////				consultaRF.setCliente(cliente);
//////
//////				consultaRF.setDataNascimento(cliente.getDataNascimento());
//////				Integer idade = Util.getIdade(cliente.getDataNascimento());
//////				if(idade.intValue() != 0){
//////					consultaRF.setIdade(idade);
//////				}
//////
//////				consultaRF.setNomeDaMaeCliente(cliente.getNomeMae());
//////				if(cliente.getRg() != null && !cliente.getRg().equals("")){
//////					consultaRF.setNumeroRGCliente(cliente.getRg());
//////				}
//////
//////				consultaRF.setSexoCliente(cliente.getPessoaSexo()
//////						.getDescricao());
//////			}
//////			
//////			if(situacaoCadastral != null && !situacaoCadastral.equals("")){
//////				consultaRF.setSituacaoCPF(situacaoCadastral);
//////			}
//////
//////			if (usuario.getFuncionario() != null) {
//////				consultaRF.setFuncionario(usuario.getFuncionario());
//////			}
//////
//////			consultaRF.setCpfCliente(cpf);
//////			consultaRF.setDataUltimaAlteracaoConsulta(new Date());
//////			
//////			//Variaveis Retornadas pelo WebService não persistidas no Banco
//////			if (dataConsulta != null && !dataConsulta.equals("")) {
//////				consultaRF.setDataConsulta(dataConsulta);
//////			}
//////			if (nomePessoaFisica != null && !nomePessoaFisica.equals("")) {
//////				consultaRF.setNomePessoaFisica(nomePessoaFisica);
//////				consultaRF.setNomeCliente(nomePessoaFisica);
//////			}
//////			if (comprovanteAutenticidade != null && !comprovanteAutenticidade.equals("")) {
//////				consultaRF.setComprovanteAutenticidade(comprovanteAutenticidade);
//////			}
//////			if (horaConsulta != null && !horaConsulta.equals("")) {
//////				consultaRF.setHoraConsulta(horaConsulta);
//////			}
//////
//////		} else if (consultarGatewayResponse.get_return().length == 1
//////				&& consultarGatewayResponse.get_return()[0]
//////						.getCdMensagem()
//////						.equals(MensagemRetornoReceitaFederal.PARAMETRO_INVALIDO)) {
//////			
//////			consultaRF = new ConsultarReceitaFederal();
//////			
//////			String origemConsulta = consultarGatewayResponse.get_return()[0].getOrigemConsulta();
//////			if(origemConsulta != null && origemConsulta.equals("Bureau Externo")){
//////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("0"));
//////			}else if(origemConsulta != null && origemConsulta.equals("Neurotech")){
//////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("1"));
//////			}else{
//////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("2"));
//////			}
//////			
//////			MensagemRetornoReceitaFederal mensagemRF = new MensagemRetornoReceitaFederal();
//////			mensagemRF.setId(MensagemRetornoReceitaFederal.ID_PARAMETRO_INVALIDO);
//////			mensagemRF.setDescricaoMensagemRetorno(consultarGatewayResponse.get_return()[0].getDsMensagem());
//////			mensagemRF.setCodigoMensagemRetorno(221);
//////			consultaRF.setMensagemRetornoReceitaFederal(mensagemRF);
//////			
//////
//////		} else if (consultarGatewayResponse.get_return().length == 1
//////				&& consultarGatewayResponse.get_return()[0]
//////						.getCdMensagem()
//////						.equals(MensagemRetornoReceitaFederal.INFORMACAO_NAO_ENCONTRADA)) {
//////			consultaRF = new ConsultarReceitaFederal();
//////			MensagemRetornoReceitaFederal mensagemRF = new MensagemRetornoReceitaFederal();
//////			mensagemRF.setId(MensagemRetornoReceitaFederal.ID_INFORMACAO_NAO_ENCONTRADA);
//////			mensagemRF.setDescricaoMensagemRetorno(consultarGatewayResponse.get_return()[0] .getDsMensagem());
//////			mensagemRF.setCodigoMensagemRetorno(222);
//////			consultaRF.setMensagemRetornoReceitaFederal(mensagemRF);
//////			
//////			String origemConsulta = consultarGatewayResponse.get_return()[0].getOrigemConsulta();
//////			if(origemConsulta != null && origemConsulta.equals("Bureau Externo")){
//////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("0"));
//////			}else if(origemConsulta != null && origemConsulta.equals("Neurotech")){
//////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("1"));
//////			}else{
//////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("2"));
//////			}
//////			
//////			String idLog = consultarGatewayResponse.get_return()[0].getIdLog();
//////			if (idLog != null) {
//////				consultaRF.setIdLogConsulta(Integer.parseInt(idLog));
//////			} else {
//////				consultaRF.setIdLogConsulta(0);
//////			}
//////			
//////
//////		} else if (consultarGatewayResponse.get_return().length == 1
//////				&& consultarGatewayResponse.get_return()[0]
//////						.getCdMensagem()
//////						.equals(MensagemRetornoReceitaFederal.FALHA_NA_CONSULTA)) {
//////			
//////			consultaRF = new ConsultarReceitaFederal();
//////			MensagemRetornoReceitaFederal mensagemRF = new MensagemRetornoReceitaFederal();
//////			mensagemRF.setId(MensagemRetornoReceitaFederal.ID_FALHA_NA_CONSULTA);
//////			mensagemRF.setDescricaoMensagemRetorno(consultarGatewayResponse.get_return()[0].getDsMensagem());
//////			mensagemRF.setCodigoMensagemRetorno(199);
//////			consultaRF.setMensagemRetornoReceitaFederal(mensagemRF);
//////			
//////		
//////		}
//////
//////		return consultaRF;
//////	}
////
////	public ConsultarReceitaFederal consultaPessoaJuridica(String cnpj,
////			Usuario usuario, Cliente cliente, ClienteFone fonePrincipal, ClienteEndereco endereco) throws RemoteException, ParseException {
////		
////		ConsultarReceitaFederal consultaRF = null;
////		// Criação do objeto stub
////		GATEWAY_WEBSERVICESStub stub = new GATEWAY_WEBSERVICESStub();
////		stub._getServiceClient().getOptions()
////				.setProperty(HTTPConstants.CHUNKED, false);
////
////		Options vOptions = stub._getServiceClient().getOptions();
////
////		// Setando o timeout de conexão com o webservice
////		vOptions.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(120000));
////		vOptions.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(
////				120000));
////
////		// Autenticação do contratante
////		ConsultarGateway consultarGateway = new ConsultarGateway();
////		consultarGateway.setPIdentificador(35);
////		consultarGateway.setPLogin("compesa");
////		consultarGateway.setPSenha("compesa@neurotech");
////
////		ConsultaWS[] consultaWSs = new ConsultaWS[1];
////
////		// Consulta da Receita Federal Pessoa Jurídica
////		ConsultaWS consultaWS = new ConsultaWS();
////		consultaWS.setIdConsulta("RFPJ_IMG");
////		CampoWS campoWS = new CampoWS();
////		campoWS.setNmCampo("cnpj");
////		campoWS.setVlCampo(cnpj);
////
////		consultaWS.setLsParametros(new CampoWS[] { campoWS });
////		consultaWSs[0] = consultaWS;
////
////		consultarGateway.setPConsultas(consultaWSs);
////		ConsultarGatewayResponse consultarGatewayResponse = stub
////				.consultarGateway(consultarGateway);
////
////		if (consultarGatewayResponse.get_return().length == 1
////				&& consultarGatewayResponse.get_return()[0]
////						.getCdMensagem()
////						.equals(MensagemRetornoReceitaFederal.CONSULTA_REALIZADA_COM_SUCESSO)) {
////
////			String dataAbertura = consultarGatewayResponse.get_return()[0]
////					.getLsRetorno()[0].getVlCampo();
////			String nomePessoaJuridica = consultarGatewayResponse.get_return()[0]
////					.getLsRetorno()[1].getVlCampo();
////			
////			if(nomePessoaJuridica.length() > 50){
////				nomePessoaJuridica = nomePessoaJuridica.substring(0, 49);
////			}
////			
////			String nomeFantasia = consultarGatewayResponse.get_return()[0]
////					.getLsRetorno()[2].getVlCampo();
////			
////			if(nomeFantasia.length() > 50){
////				nomeFantasia = nomeFantasia.substring(0, 49);
////			}
////			
////			String situacaoCadastral = consultarGatewayResponse.get_return()[0]
////					.getLsRetorno()[3].getVlCampo();
////			String situacaoEspecial = consultarGatewayResponse.get_return()[0]
////					.getLsRetorno()[4].getVlCampo();
////			String logradouro = consultarGatewayResponse.get_return()[0]
////					.getLsRetorno()[5].getVlCampo();
////			String numero = consultarGatewayResponse.get_return()[0]
////					.getLsRetorno()[6].getVlCampo();
////			String complemento = consultarGatewayResponse.get_return()[0]
////					.getLsRetorno()[7].getVlCampo();
////			String bairro = consultarGatewayResponse.get_return()[0]
////					.getLsRetorno()[8].getVlCampo();
////			String cidade = consultarGatewayResponse.get_return()[0]
////					.getLsRetorno()[9].getVlCampo();
////			String uf = consultarGatewayResponse.get_return()[0].getLsRetorno()[10]
////					.getVlCampo();
////			String cep = consultarGatewayResponse.get_return()[0]
////					.getLsRetorno()[11].getVlCampo();
////			String quadroSocietario = consultarGatewayResponse.get_return()[0]
////					.getLsRetorno()[12].getVlCampo();
////			String ramoDeAtividade = consultarGatewayResponse.get_return()[0]
////					.getLsRetorno()[13].getVlCampo();
////			
////			if(ramoDeAtividade.length() > 50){
////				ramoDeAtividade = ramoDeAtividade.substring(0, 49);
////			}
////			
////			String naturezaJuridica = consultarGatewayResponse.get_return()[0]
////					.getLsRetorno()[14].getVlCampo();
////			
////			if(naturezaJuridica.length() > 50){
////				naturezaJuridica = naturezaJuridica.substring(0, 49);
////			}
////
////			String origemConsulta = consultarGatewayResponse.get_return()[0]
////					.getOrigemConsulta();
////			String idLog = consultarGatewayResponse.get_return()[0].getIdLog();
////			
////			consultaRF = new ConsultarReceitaFederal();
////			if (idLog != null) {
////				consultaRF.setIdLogConsulta(Integer.parseInt(idLog));
////			} else {
////				consultaRF.setIdLogConsulta(0);
////			}
////
////			if(origemConsulta != null && origemConsulta.equals("Bureau Externo")){
////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("0"));
////			}else if(origemConsulta != null && origemConsulta.equals("Neurotech")){
////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("1"));
////			}else{
////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("2"));
////			}
////			
////			MensagemRetornoReceitaFederal mensagemRF = new MensagemRetornoReceitaFederal();
////			mensagemRF
////					.setId(MensagemRetornoReceitaFederal.ID_CONSULTA_REALIZADA_COM_SUCESSO);
////			mensagemRF.setCodigoMensagemRetorno(100);
////			mensagemRF.setDescricaoMensagemRetorno(consultarGatewayResponse.get_return()[0].getDsMensagem());
////			consultaRF.setMensagemRetornoReceitaFederal(mensagemRF);
////
////			if (fonePrincipal != null
////					&& fonePrincipal.getFoneTipo().getId() == FoneTipo.COMERCIAL) {
////				consultaRF.setCodigoDDDComercial(fonePrincipal.getDdd());
////				consultaRF.setTelefoneComercialCliente(fonePrincipal
////						.getTelefone());
////			} else if (fonePrincipal != null
////					&& fonePrincipal.getFoneTipo().getId() == FoneTipo.FAX) {
////				consultaRF.setCodigoDDDComercial(fonePrincipal.getDdd());
////				consultaRF.setTelefoneComercialCliente(fonePrincipal
////						.getTelefone());
////			} else if (fonePrincipal != null
////					&& fonePrincipal.getFoneTipo().getId() == FoneTipo.RESIDENCIAL) {
////				consultaRF.setCodigoDDDTelefoneResidencialCliente(fonePrincipal
////						.getDdd());
////				consultaRF.setNumeroTelefoneResidencialCliente(fonePrincipal
////						.getTelefone());
////			} else if (fonePrincipal != null
////					&& fonePrincipal.getFoneTipo().getId() == FoneTipo.CELULAR) {
////				consultaRF.setCodigoDDDCelularCliente(fonePrincipal.getDdd());
////				consultaRF.setNumeroCelularCliente(fonePrincipal.getTelefone());
////			}
////
////			if (logradouro != null && !logradouro.equals("")) {
////				consultaRF.setLogradouroCliente(logradouro);
////			} else {
////				if (endereco != null && endereco.getLogradouroCep() != null) {
////					consultaRF.setLogradouroCliente(endereco.getLogradouroCep()
////							.getLogradouro().getNome());
////				}
////			}
////
////			if (numero != null && !numero.equals("")) {
////				consultaRF.setNumeroEnderecoCliente(numero);
////			} else {
////				if (endereco != null && endereco.getNumero() != null) {
////					consultaRF.setNumeroEnderecoCliente(endereco.getNumero());
////				}
////			}
////
////			if (complemento != null && !complemento.equals("")) {
////				consultaRF.setComplementoEnderecoCliente(complemento);
////			} else {
////				if (endereco != null && endereco.getComplemento() != null) {
////					consultaRF.setComplementoEnderecoCliente(endereco
////							.getComplemento());
////				}
////			}
////
////			if (bairro != null && !bairro.equals("")) {
////				consultaRF.setBairroCliente(bairro);
////			} else {
////				if (endereco != null && endereco.getLogradouroBairro() != null) {
////					consultaRF.setBairroCliente(endereco.getLogradouroBairro()
////							.getBairro().getNome());
////				}
////			}
////
////			if (cidade != null && !cidade.equals("")) {
////				consultaRF.setCidadeCliente(cidade);
////			} else {
////				if (endereco != null
////						&& endereco.getLogradouroBairro() != null
////						&& endereco.getLogradouroBairro().getBairro()
////								.getMunicipio() != null) {
////					consultaRF.setCidadeCliente(endereco.getLogradouroBairro()
////							.getBairro().getMunicipio().getNome());
////				}
////			}
////
////			if (cep != null && !cep.equals("")) {
////				consultaRF.setCepCliente(Integer.parseInt(cep));
////			} else {
////				if (endereco != null && endereco.getLogradouroCep() != null) {
////					consultaRF.setCepCliente(endereco.getLogradouroCep()
////							.getCep().getCodigo().intValue());
////				}
////			}
////
////			if (uf != null && !uf.equals("")) {
////				consultaRF.setUfCliente(uf);
////			} else {
////				if (endereco != null
////						&& endereco.getLogradouroBairro() != null
////						&& endereco.getLogradouroBairro().getBairro()
////								.getMunicipio() != null) {
////					consultaRF.setUfCliente(endereco.getLogradouroBairro()
////							.getBairro().getMunicipio().getUnidadeFederacao()
////							.getSigla());
////				}
////			}
////
////			if (usuario != null) {
////				consultaRF.setCpfUsuario(usuario.getCpf());
////				consultaRF.setLoginUsuario(usuario.getLogin());
////				consultaRF.setUsuarioSolicitante(usuario);
////			}
////
////			if (cliente != null) {
////				consultaRF.setCliente(cliente);
////			}
////
////			if (usuario.getFuncionario() != null) {
////				consultaRF.setFuncionario(usuario.getFuncionario());
////			}
////
////			if (naturezaJuridica != null && !naturezaJuridica.equals("")) {
////				consultaRF.setNaturezaJuridica(naturezaJuridica);
////			}
////
////			if (dataAbertura != null && !dataAbertura.equals("")) {
////				Date data = new SimpleDateFormat("ddMMyyyy", new Locale("pt",
////						"BR")).parse(dataAbertura);
////				consultaRF.setDataFundacao(data);
////			}
////
////			if (nomeFantasia != null && !nomeFantasia.equals("")
////					&& !nomeFantasia.equals("********")) {
////				consultaRF.setNomeComercial(nomeFantasia);
////			}
////
////			if (ramoDeAtividade != null && !ramoDeAtividade.equals("")) {
////				consultaRF.setRamoAtividade(ramoDeAtividade);
////			}else{
////				consultaRF.setRamoAtividade(cliente.getRamoAtividade().getDescricao());
////			}
////
////			if (nomePessoaJuridica != null && !nomePessoaJuridica.equals("")) {
////				consultaRF.setRazaoSocial(nomePessoaJuridica);
////				consultaRF.setNomeCliente(nomePessoaJuridica);
////			}
////			
////			if(situacaoCadastral != null && !situacaoCadastral.equals("")){
////				consultaRF.setSituacaoCNPJ(situacaoCadastral);
////			}
////			
////			if(situacaoEspecial != null && !situacaoEspecial.equals("")){
////				consultaRF.setSituacaoEspecial(situacaoEspecial);
////			}
////
////			consultaRF.setCnpjCliente(cnpj);
////			consultaRF.setDataUltimaAlteracaoConsulta(new Date());
////			
////			//Variaveis Retornadas pelo WebService não persistidas no Banco
////			if (quadroSocietario != null && !quadroSocietario.equals("")) {
////				consultaRF.setQuadroSocietario(quadroSocietario);
////			}
////
////		} else if (consultarGatewayResponse.get_return().length == 1
////				&& consultarGatewayResponse.get_return()[0]
////						.getCdMensagem()
////						.equals(MensagemRetornoReceitaFederal.PARAMETRO_INVALIDO)) {
////			
////			consultaRF = new ConsultarReceitaFederal();
////			
////			String origemConsulta = consultarGatewayResponse.get_return()[0].getOrigemConsulta();
////			if(origemConsulta != null && origemConsulta.equals("Bureau Externo")){
////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("0"));
////			}else if(origemConsulta != null && origemConsulta.equals("Neurotech")){
////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("1"));
////			}else{
////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("2"));
////			}
////			
////			MensagemRetornoReceitaFederal mensagemRF = new MensagemRetornoReceitaFederal();
////			mensagemRF.setId(MensagemRetornoReceitaFederal.ID_PARAMETRO_INVALIDO);
////			mensagemRF.setCodigoMensagemRetorno(221);
////			mensagemRF.setDescricaoMensagemRetorno(consultarGatewayResponse.get_return()[0].getDsMensagem());
////			consultaRF.setMensagemRetornoReceitaFederal(mensagemRF);
////			
////		} else if (consultarGatewayResponse.get_return().length == 1
////				&& consultarGatewayResponse.get_return()[0]
////						.getCdMensagem()
////						.equals(MensagemRetornoReceitaFederal.INFORMACAO_NAO_ENCONTRADA)) {
////			
////			consultaRF = new ConsultarReceitaFederal();
////			
////			MensagemRetornoReceitaFederal mensagemRF = new MensagemRetornoReceitaFederal();
////			mensagemRF.setId(MensagemRetornoReceitaFederal.ID_INFORMACAO_NAO_ENCONTRADA);
////			mensagemRF.setCodigoMensagemRetorno(222);
////			mensagemRF.setDescricaoMensagemRetorno(consultarGatewayResponse.get_return()[0] .getDsMensagem());
////			consultaRF.setMensagemRetornoReceitaFederal(mensagemRF);
////			
////			String origemConsulta = consultarGatewayResponse.get_return()[0].getOrigemConsulta();
////			if(origemConsulta != null && origemConsulta.equals("Bureau Externo")){
////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("0"));
////			}else if(origemConsulta != null && origemConsulta.equals("Neurotech")){
////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("1"));
////			}else{
////				consultaRF.setCodigoOrigemConsulta(Short.parseShort("2"));
////			}
////
////			String idLog = consultarGatewayResponse.get_return()[0].getIdLog();
////			if (idLog != null) {
////				consultaRF.setIdLogConsulta(Integer.parseInt(idLog));
////			} else {
////				consultaRF.setIdLogConsulta(0);
////			}
////
////		} else if (consultarGatewayResponse.get_return().length == 1
////				&& consultarGatewayResponse.get_return()[0]
////						.getCdMensagem()
////						.equals(MensagemRetornoReceitaFederal.FALHA_NA_CONSULTA)) {
////			consultaRF = new ConsultarReceitaFederal();
////			MensagemRetornoReceitaFederal mensagemRF = new MensagemRetornoReceitaFederal();
////			mensagemRF.setId(MensagemRetornoReceitaFederal.ID_FALHA_NA_CONSULTA);
////			mensagemRF.setDescricaoMensagemRetorno(consultarGatewayResponse.get_return()[0].getDsMensagem());
////			mensagemRF.setCodigoMensagemRetorno(199);
////			consultaRF.setMensagemRetornoReceitaFederal(mensagemRF);
////		}
////
////		return consultaRF;
////	}
//	
//}