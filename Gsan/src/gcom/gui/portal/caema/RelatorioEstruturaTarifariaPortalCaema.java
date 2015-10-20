//package gcom.gui.portal.caema;
//
//import gcom.batch.Relatorio;
//import gcom.cadastro.imovel.Categoria;
//import gcom.cadastro.sistemaparametro.SistemaParametro;
//import gcom.fachada.Fachada;
//import gcom.faturamento.debito.DebitoTipo;
//import gcom.faturamento.debito.FiltroDebitoTipo;
//import gcom.relatorio.ConstantesRelatorios;
//import gcom.relatorio.RelatorioDataSource;
//import gcom.seguranca.acesso.usuario.Usuario;
//import gcom.tarefa.TarefaException;
//import gcom.tarefa.TarefaRelatorio;
//import gcom.util.ControladorException;
//import gcom.util.Util;
//import gcom.util.filtro.ParametroSimples;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * [UC1194] Consultar Estrutura Tarifária Loja Virtual
// *
// * Classe responsável por gerar o relatório relatorioEstruturaTarifariaLojaVirtual.jasper
// * 
// * @author Diogo Peixoto
// * @since 18/07/2011
// *
// */
//public class RelatorioEstruturaTarifariaPortalCaema extends TarefaRelatorio {
//
//	public RelatorioEstruturaTarifariaPortalCaema(Usuario usuario) {
//		super(usuario, ConstantesRelatorios.RELATORIO_ESTRUTURA_TARIFARIA_LOJA_VIRTUAL);
//	}
//
//	private static final long serialVersionUID = 1L;
//
//	@Override
//	public int calcularTotalRegistrosRelatorio() {
//		return 0;
//	}
//
//	@Override
//	public Object executar() throws TarefaException {
//
//		// ------------------------------------
//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
//		// ------------------------------------
//		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
//		SistemaParametro sistemaParametro = (SistemaParametro) getParametro("sistemaParametro");
//		List<ConsultarEstruturaTarifariaPortalCaemaHelper> estruturaTarifariaHelper =  (List<ConsultarEstruturaTarifariaPortalCaemaHelper>) getParametro("estruturaTarifariaBeans");
//		List<RelatorioEstruturaTarifariaPortalCaemaBean> beans = new ArrayList<RelatorioEstruturaTarifariaPortalCaemaBean>();
//		RelatorioEstruturaTarifariaPortalCaemaBean bean = null;
//		
//		for(ConsultarEstruturaTarifariaPortalCaemaHelper helper : estruturaTarifariaHelper){
//			//Se for um consumo medido
//			if(helper.getIndiceConsumo() == 1){
//				bean = new RelatorioEstruturaTarifariaPortalCaemaBean(helper.getCategoria(), helper.getQuantidade(), helper.getValor(), 1);
//			//Se for um consumo não medido.	
//			}else if(helper.getIndiceConsumo() == 2){
//				bean = new RelatorioEstruturaTarifariaPortalCaemaBean(helper.getCategoria(), "", helper.getValor() + " " + helper.getQuantidade(), 2);
//			}else if(helper.getIndiceConsumo() == 3){
//				bean = new RelatorioEstruturaTarifariaPortalCaemaBean(helper.getCategoria(), helper.getQuantidade(), helper.getValor() + " por 1.000L", 3);
//			}
//			beans.add(bean);
//		}
//		
//		Map<String, String> parametros = new HashMap<String, String>();
//		
//		parametros.put("imagemConta", sistemaParametro.getImagemRelatorio());
//		
//		//Consumos Não medidos
//		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> consumosNaoMedidos = new ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper>();
//		ConsultarEstruturaTarifariaPortalCaemaHelper consumoNaoMedido = null;
//		
//		//Consumos Medidos Residencial
//		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperResidencial = 
//			Fachada.getInstancia().pesquisarEstruturaTarifaria(Categoria.RESIDENCIAL);
//		if(!Util.isVazioOrNulo(helperResidencial)){
//			consumoNaoMedido = helperResidencial.get(0); 
//			if(consumoNaoMedido != null){
//				parametros.put("residencialTarifaSocialNaoMedido", consumoNaoMedido.getValor() + " por mês");
//				parametros.put("tarifaSocial", consumoNaoMedido.getValor() );
//			}
//			
//			consumoNaoMedido = helperResidencial.get(1);
//			if(consumoNaoMedido != null){
//				parametros.put("residencialTarifaNormalNaoMedido", consumoNaoMedido.getValor() + " por mês");
//				parametros.put("tarifaNormal", consumoNaoMedido.getValor() );
//			}
//			
//			consumoNaoMedido = helperResidencial.get(2);
//			if(consumoNaoMedido != null){
//				parametros.put("tarifaResidencial11a20milLitros", consumoNaoMedido.getValor() + " por 1.000 l");
//			}
//			
//			consumoNaoMedido = helperResidencial.get(3);
//			if(consumoNaoMedido != null){
//				parametros.put("tarifaResidencial21a30milLitros", consumoNaoMedido.getValor() + " por 1.000 l");
//			}
//			
//			consumoNaoMedido = helperResidencial.get(4);
//			if(consumoNaoMedido != null){
//				parametros.put("tarifaResidencial31a50milLitros", consumoNaoMedido.getValor() + " por 1.000 l" );
//			}
//			
//			consumoNaoMedido = helperResidencial.get(5);
//			if(consumoNaoMedido != null){
//				parametros.put("tarifaResidencial51a90milLitros", consumoNaoMedido.getValor() + " por 1.000 l");
//			}
//			
//			consumoNaoMedido = helperResidencial.get(6);
//			if(consumoNaoMedido != null){
//				parametros.put("tarifaResidencialSuperior91milLitros", consumoNaoMedido.getValor() + " por 1.000 l");
//			}
//		}
//		
//		//Consumos Medidos Comercial
//		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperComercial = 
//				Fachada.getInstancia().pesquisarEstruturaTarifaria(Categoria.COMERCIAL);
//		if(!Util.isVazioOrNulo(helperComercial)){
//		
//			consumoNaoMedido = helperComercial.get(0); 
//			if(consumoNaoMedido != null){
//				parametros.put("comercialTarifaMinimaNaoMedido", consumoNaoMedido.getValor() + " por mês");
//				parametros.put("comercialTarifaMinima", consumoNaoMedido.getValor() );
//			}
//			
//			consumoNaoMedido = helperComercial.get(1); 
//			if(consumoNaoMedido != null){
//				parametros.put("comercialTarifaMinimaSuperior10MilLitros", consumoNaoMedido.getValor() + " por 1.000 l");
//			}
//		}
//		
//		//Consumos Medidos Industrial
//		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperIndustrial = 
//				Fachada.getInstancia().pesquisarEstruturaTarifaria(Categoria.INDUSTRIAL);
//		if(!Util.isVazioOrNulo(helperIndustrial)){
//			consumoNaoMedido = helperIndustrial.get(0); 
//			if(consumoNaoMedido != null){
//				parametros.put("industrialTarifaMinimaNaoMedido", consumoNaoMedido.getValor() + " por mês");
//				parametros.put("industrialTarifaMinima", consumoNaoMedido.getValor() );
//			}
//			
//			consumoNaoMedido = helperIndustrial.get(1); 
//			if(consumoNaoMedido != null){
//				parametros.put("industrialTarifaMinimaSuperior10MilLitros", consumoNaoMedido.getValor() + " por 1.000 l");
//			}
//			
//		}
//		
//		//Consumos Medidos Pública
//		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperPublica = 
//				Fachada.getInstancia().pesquisarEstruturaTarifaria(Categoria.PUBLICO);
//		if(!Util.isVazioOrNulo(helperPublica)){
//			
//			consumoNaoMedido = helperPublica.get(0); 
//			if(consumoNaoMedido != null){
//				parametros.put("publicoTarifaMinimaNaoMedido", consumoNaoMedido.getValor() + " por mês");
//				parametros.put("publicaTarifaMinima", consumoNaoMedido.getValor());
//			}
//			consumoNaoMedido = helperPublica.get(1); 
//			if(consumoNaoMedido != null){
//				parametros.put("publicaTarifaMinimaSuperior10MilLitros", consumoNaoMedido.getValor() +" por 1.000 l");
//			}
//		}
//		
//		//------------------------- Carregando os consumos não medidos  -------------------------//
//		FiltroDebitoTipo filtroDebitoTipo  = new FiltroDebitoTipo();
//		filtroDebitoTipo.adicionarParametro( new ParametroSimples(FiltroDebitoTipo.ID, new Integer(103)));
//		
//		Collection<DebitoTipo> colecaoDebitoTipo = Fachada.getInstancia().pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
//		String valorSugerido = "";
//		if ( colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty() ) {
//			DebitoTipo debitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
//			if (debitoTipo.getValorSugerido() != null ) {
//				valorSugerido = debitoTipo.getValorSugerido().toString().replace(".", ",") + " por 1.000L";
//			}
//		}
//		parametros.put("fornecimentoCarroPipa", valorSugerido );
//		ConsultarEstruturaTarifariaPortalCaemaHelper  helperChafariz = Fachada.getInstancia().pesquisarEstruturaTarifariaChafarizPublico();
//		parametros.put("fornecimentoCarroPipaOrgaoPublico", helperChafariz.getValor() +  " por 1.000L");
//		parametros.put("chafarizPublico", helperChafariz.getValor() +  " por 1.000L");
//		
//		
//		ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper> helperAguaBruta = new ArrayList<ConsultarEstruturaTarifariaPortalCaemaHelper>();
//		helperAguaBruta = Fachada.getInstancia().pesquisarEstruturaTarifariaAguaBruta(Categoria.RESIDENCIAL);
//		if(!Util.isVazioOrNulo(helperAguaBruta)){
//			
//			consumoNaoMedido = helperAguaBruta.get(0); 
//			if(consumoNaoMedido != null){
//				parametros.put("aguaBrutaResidencial", consumoNaoMedido.getValor() + " por 1.000 l");
//			}
//		}
//		helperAguaBruta.clear();
//		helperAguaBruta.addAll(Fachada.getInstancia().pesquisarEstruturaTarifariaAguaBruta(Categoria.COMERCIAL));
//		if(!Util.isVazioOrNulo(helperAguaBruta)){
//			
//			consumoNaoMedido = helperAguaBruta.get(0); 
//			if(consumoNaoMedido != null){
//				parametros.put("aguaBrutaComercialIndustrialSuperior51", consumoNaoMedido.getValor() + " por 1.000 l");
//			}
//			consumoNaoMedido = helperAguaBruta.get(1); 
//			if(consumoNaoMedido != null){
//				parametros.put("aguaBrutaComercialIndustrialSuperior5Mil", consumoNaoMedido.getValor() + " por 1.000 l");
//			}
//			consumoNaoMedido = helperAguaBruta.get(2); 
//			if(consumoNaoMedido != null){
//				parametros.put("aguaBrutaComercialIndustrialSuperior20Mil", consumoNaoMedido.getValor() + " por 1.000 l");
//			}
//		}
//		
//		byte[] retorno = null;
//		
//		RelatorioDataSource ds = new RelatorioDataSource(beans);
//		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ESTRUTURA_TARIFARIA_LOJA_VIRTUAL, parametros, ds, tipoFormatoRelatorio);
//
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ESTRUTURA_TARIFARIA_LOJA_VIRTUAL, idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relatório no sistema", e);
//		}
//		return retorno;
//	}
//
//	@Override
//	public void agendarTarefaBatch() {
//
//	}
//}