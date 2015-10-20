package gcom.relatorio.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.Relatorio;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.PerfilAlteracaoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.cadastro.imovel.GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionHelper;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.consumo.FiltroConsumoTipo;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioEnquadramentoDesenquadramentoGrandeCliente extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioEnquadramentoDesenquadramentoGrandeCliente(
			Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ENQUADRAMENTO_DESENQUADRAMENTO_GRANDE_CLIENTE_CORPORATIVO);		
	}

	@Override
	public Object executar() throws TarefaException {		

		GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionHelper  filtro = 
				new GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionHelper();
		
		//adicionar os dados do filtro
		filtro = this.informarDadosFiltroRelatorio(filtro);		
		
		byte[] retorno = null;
		
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoRelatorioEnquadramentoDesenquadramentoGrandeClienteHelper = 
				fachada.pesquisarDadosRelatorioEnquadramentoDesenquadramentoGrandeCliente(filtro);
		
		if(Util.isVazioOrNulo(colecaoRelatorioEnquadramentoDesenquadramentoGrandeClienteHelper)){			
			throw new ActionServletException("atencao.relatorio.vazio");						
		}
		
		relatorioBeans = (List) colecaoRelatorioEnquadramentoDesenquadramentoGrandeClienteHelper;		
		
		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		this.informarParametrosRelatorio(parametros, filtro);		
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("tipoFormatoRelatorio", "R1686");
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);
		
		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_ENQUADRAMENTO_DESENQUADRAMENTO_GRANDE_CLIENTE_CORPORATIVO, 
				parametros, ds, filtro.getTipoRelatorio());
		
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ACOMPANHAMENTO_GRANDES_CORPORATIVO, filtro.getIdFuncionalidadeIniciada());
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		
		// retorna o relatório gerado		
		return retorno;
	}
	
	private GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionHelper informarDadosFiltroRelatorio(
			GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionHelper filtro){
		
		filtro.setIdMunicipio((String) getParametro("municipio"));
		filtro.setGerenciaRegional((String) getParametro("gerenciaRegional"));
		filtro.setUnidadeNegocio((String) getParametro("unidadeNegocio"));
		filtro.setIdLocalidade((String) getParametro("localidade"));
		filtro.setCodigoSetorComercial((String) getParametro("setorComercial"));
		filtro.setIdQuadra((String) getParametro("quadra"));
		filtro.setRota((String) getParametro("rota"));		
		filtro.setImovelPerfilOrigem((String)getParametro("imovelPerfilOrigem"));
		filtro.setImovelPerfilDestino((String)getParametro("imovelPerfilDestino"));
		filtro.setEnquadramentoTipo((String) getParametro("enquadramentoTipo"));
		filtro.setEnquadramentoDataInicial((String) getParametro("dataEnquadramentoInicial"));
		filtro.setEnquadramentoDataFinal((String) getParametro("dataEnquadramentoFinal"));
		filtro.setDesenquadramentoDataInicial((String) getParametro("dataDesenquadramentoInicial"));
		filtro.setDesenquadramentoDataFinal((String) getParametro("dataDesenquadramentoFinal"));
		filtro.setCategoria((String[]) getParametro("categoria"));
		filtro.setSubcategoria((String[]) getParametro("subcategoria"));
		filtro.setSituacaoLigacaoAgua((String) getParametro("situacaoLigacaoAgua"));
		filtro.setSituacaoLigacaoEsgoto((String) getParametro("situacaoLigacaoEsgoto"));		
		filtro.setValorFaturamentoIntervaloInicial((String) getParametro("intervaloValorFaturamentoInicial"));
		filtro.setValorFaturamentoIntervaloFinal((String) getParametro("intervaloValorFaturamentoFinal"));
		filtro.setEsferaPoder((String) getParametro("esferaPoder"));
		filtro.setHidrometroCapacidade((String[]) getParametro("hidrometroCapacidade"));
		filtro.setConsumoTarifa((String[]) getParametro("consumoTarifa"));
		filtro.setTipoRelatorio(Integer.parseInt((String) getParametro("tipoRelatorio")));
		filtro.setIdFuncionalidadeIniciada(this.getIdFuncionalidadeIniciada());
		
		return filtro;
	}
	
	private void informarParametrosRelatorio(Map parametros, 
			GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionHelper filtro){
		
		Fachada fachada = Fachada.getInstancia();
		
		//Municipio
		parametros.put("municipioFiltro", "");
		if(Util.verificarNaoVazio(filtro.getIdMunicipio())){
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, filtro.getIdMunicipio()));
			Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoMunicipio)){
				Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
				parametros.put("municipioFiltro", municipio.getNome());
			}
		}
		
		//Gerencia Regional
		parametros.put("gerenciaRegionalFiltro", "");
		parametros.put("gerenciaRegional", "");
		if(Util.verificarNaoVazio(filtro.getGerenciaRegional())){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, filtro.getGerenciaRegional()));
			Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoGerenciaRegional)){
				GerenciaRegional gerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
				String gerenciaFiltro = gerenciaRegional.getId().toString() + " - " + gerenciaRegional.getNome(); 
				parametros.put("gerenciaRegionalFiltro", gerenciaFiltro);				
				parametros.put("gerenciaRegional", gerenciaRegional.getNome());
			}
		}
		
		//Unidade de Negocio
		parametros.put("unidadeNegocioFiltro", "");
		parametros.put("unidadeNegocio", "");
		if(Util.verificarNaoVazio(filtro.getUnidadeNegocio())){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, filtro.getUnidadeNegocio()));
			Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoUnidadeNegocio)){
				UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
				String unidadeFiltro = unidadeNegocio.getId().toString() + " - " + unidadeNegocio.getNome();
				parametros.put("unidadeNegocioFiltro", unidadeFiltro);
				parametros.put("unidadeNegocio", unidadeNegocio.getNome());
			}
		}
		
		//Localidade
		parametros.put("localidadeFiltro", "");
		parametros.put("localidade", "");		
		Localidade localidade = null;
		if(Util.verificarNaoVazio(filtro.getIdLocalidade())){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, filtro.getIdLocalidade()));
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoLocalidade)){
				localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				String localidadeFiltro = localidade.getId().toString() + " - " + localidade.getDescricao();
				parametros.put("localidadeFiltro", localidadeFiltro);
				parametros.put("localidade", localidade.getDescricao());
			}
		}
		
		//Setor Comercial
		parametros.put("setorComercialFiltro", "");
		parametros.put("setorComercial", "");
		SetorComercial setorComercial = null;
		if(Util.verificarNaoVazio(filtro.getCodigoSetorComercial())){
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, filtro.getIdLocalidade()));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, filtro.getCodigoSetorComercial()));
			Collection colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoSetorComercial)){
				setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
				String setorFiltro = setorComercial.getCodigo() + " - " + setorComercial.getDescricao();
				parametros.put("setorComercialFiltro", setorFiltro);
				parametros.put("setorComercial", setorComercial.getDescricao());				
			}
		}
		
		//Quadra
		parametros.put("quadraFiltro", "");
		if(Util.verificarNaoVazio(filtro.getIdQuadra())){
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercial.getId()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, filtro.getIdQuadra()));
			Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoQuadra)){
				Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);
				Integer numeroQuadra = quadra.getNumeroQuadra();
				parametros.put("quadraFiltro", numeroQuadra.toString());
			}
		}
		
		//Rota
		parametros.put("rotaFiltro", "");
		if(Util.verificarNaoVazio(filtro.getRota())){
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, filtro.getRota()));
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_ID, setorComercial.getId()));
			Collection colecaoRota = fachada.pesquisar(filtroRota, Rota.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoRota)){
				Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
				parametros.put("rotaFiltro", rota.getCodigo().toString());
			}
		}
		
		//Imovel Perfil Origem
		parametros.put("imovelPerfilOrigemFiltro", "");
		if(Util.verificarNaoVazio(filtro.getImovelPerfilOrigem())){
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, filtro.getImovelPerfilOrigem()));
			Collection colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoImovelPerfil)){
				ImovelPerfil imovelPerfil = (ImovelPerfil) Util.retonarObjetoDeColecao(colecaoImovelPerfil);
				parametros.put("imovelPerfilOrigemFiltro", imovelPerfil.getDescricao());
			}
		}
		
		//Imovel Perfil Destino
		parametros.put("imovelPerfilDestinoFiltro", "");
		if(Util.verificarNaoVazio(filtro.getImovelPerfilDestino())){
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, filtro.getImovelPerfilDestino()));
			Collection colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoImovelPerfil)){
				ImovelPerfil imovelPerfil = (ImovelPerfil) Util.retonarObjetoDeColecao(colecaoImovelPerfil);
				parametros.put("imovelPerfilDestinoFiltro", imovelPerfil.getDescricao());
			}
		}
		
		//Tipo Enquadramento
		parametros.put("enquadramentoTipoFiltro", "");
		if(Util.verificarNaoVazio(filtro.getEnquadramentoTipo())){
			if(filtro.getEnquadramentoTipo().equals(PerfilAlteracaoTipo.MANUAL.toString())){
				parametros.put("enquadramentoTipoFiltro", "MANUAL");
			}else{
				parametros.put("enquadramentoTipoFiltro", "AUTOMATICO");
			}			
		}
		
		//Data do Enquadramento no Perfil
		parametros.put("enquadramentoFiltro", "");
		if(Util.verificarNaoVazio(filtro.getEnquadramentoDataInicial()) 
		|| Util.verificarNaoVazio(filtro.getEnquadramentoDataFinal())){
			parametros.put("enquadramentoFiltro", filtro.getEnquadramentoDataInicial() + " a " + filtro.getEnquadramentoDataFinal());
		}
		
		//Data do Desenquadramento no Perfil
		parametros.put("desenquadramentoFiltro", "");
		if(Util.verificarNaoVazio(filtro.getDesenquadramentoDataInicial())
		|| Util.verificarNaoVazio(filtro.getDesenquadramentoDataFinal())){
			parametros.put("desenquadramentoFiltro", filtro.getDesenquadramentoDataInicial() + " a " + filtro.getDesenquadramentoDataFinal());			
		}
		
		//Categoria
		parametros.put("categoriaFiltro", "");
		StringBuilder categorias = new StringBuilder();
		if(!Util.isVazioOrNulo(filtro.getCategoria())){			
				
			for (String categoriaFiltro : filtro.getCategoria()) {
				FiltroCategoria filtroCategoria = new FiltroCategoria();
				filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO, categoriaFiltro));
				Collection colecaoCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
				
				if(!Util.isVazioOrNulo(colecaoCategoria)){
					Categoria categoria = (Categoria) Util.retonarObjetoDeColecao(colecaoCategoria);
					categorias.append(categoria.getDescricao() + " ");
				}				
			}
			parametros.put("categoriaFiltro", categorias.toString());			
		
		}		
		
		//Subcategoria
		parametros.put("subcategoriaFiltro", "");
		StringBuilder subcategorias = new StringBuilder();
		if(!Util.isVazioOrNulo(filtro.getSubcategoria())){
			
			for(String subcategoriaFiltro : filtro.getSubcategoria()){
				FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
				filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, subcategoriaFiltro));
				Collection colecaoSubcategoria = fachada.pesquisar(filtroSubCategoria, Subcategoria.class.getName());
				
				if(!Util.isVazioOrNulo(colecaoSubcategoria)){
					Subcategoria subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colecaoSubcategoria);
					subcategorias.append(subcategoria.getDescricao() + " ");
				}
			}
			parametros.put("subcategoriaFiltro", subcategorias.toString());
		}				

		//Ligacao Esgoto Situacao
		parametros.put("situacaoLigacaoEsgotoFiltro", "");
		if(Util.verificarNaoVazio(filtro.getSituacaoLigacaoEsgoto())){
			FiltroLigacaoEsgotoSituacao filtroSituacaoLigacaoEsgoto = new FiltroLigacaoEsgotoSituacao();
			filtroSituacaoLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, filtro.getSituacaoLigacaoEsgoto()));
			Collection colecaoLigacaoSituacaoEsgoto = fachada.pesquisar(filtroSituacaoLigacaoEsgoto, LigacaoEsgotoSituacao.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoLigacaoSituacaoEsgoto)){
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoSituacaoEsgoto);
				parametros.put("situacaoLigacaoEsgotoFiltro", ligacaoEsgotoSituacao.getDescricao());
			}
		}
		
		//Ligacao Agua Situacao
		parametros.put("situcaoLigacaoAguaFiltro", "");
		if(Util.verificarNaoVazio(filtro.getSituacaoLigacaoAgua())){
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, filtro.getSituacaoLigacaoAgua()));
			Collection colecaoLigacaoSituacaoAgua = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoLigacaoSituacaoAgua)){
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoSituacaoAgua);
				parametros.put("situcaoLigacaoAguaFiltro", ligacaoAguaSituacao.getDescricao());
			}
		}
		
		// Tipo de Consumo
		parametros.put("tipoConsumoFiltro", "");
		if(Util.verificarNaoVazio(filtro.getConsumoTipo())){
			FiltroConsumoTipo filtroConsumoTipo = new FiltroConsumoTipo();
			filtroConsumoTipo.adicionarParametro(new ParametroSimples(FiltroConsumoTipo.CODIGO, filtro.getConsumoTipo()));
			Collection colecaoConsumoTipo = fachada.pesquisar(filtroConsumoTipo, ConsumoTipo.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoConsumoTipo)){
				ConsumoTipo consumoTipo = (ConsumoTipo) Util.retonarObjetoDeColecao(colecaoConsumoTipo);
				parametros.put("tipoConsumoFiltro", consumoTipo.getDescricao());
			}
		}
		
		//Intervalo do Consumo de Agua
		parametros.put("intervaloConsumoAguaFiltro", "");
		if(Util.verificarNaoVazio(filtro.getConsumoAguaIntervaloInicial())
		|| Util.verificarNaoVazio(filtro.getConsumoAguaIntervaloFinal())){
			parametros.put("intervaloConsumoAguaFiltro", filtro.getConsumoAguaIntervaloInicial() + " a " + filtro.getConsumoAguaIntervaloFinal());
		}
		
		//Intervalo Valor Faturamento
		parametros.put("intervaloValorFaturamentoFiltro", "");
		if(Util.verificarNaoVazio(filtro.getValorFaturamentoIntervaloInicial())
		|| Util.verificarNaoVazio(filtro.getValorFaturamentoIntervaloFinal())){
			parametros.put("intervaloValorFaturamentoFiltro", filtro.getValorFaturamentoIntervaloInicial() + " a " + filtro.getValorFaturamentoIntervaloFinal());
		}
		
		//Esfera Poder
		parametros.put("esferaPoderFiltro", "");
		if(Util.verificarNaoVazio(filtro.getEsferaPoder())){
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
			filtroEsferaPoder.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.ID, filtro.getEsferaPoder()));
			Collection colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoEsferaPoder)){
				EsferaPoder esferaPoder = (EsferaPoder) Util.retonarObjetoDeColecao(colecaoEsferaPoder);
				parametros.put("esferaPoderFiltro", esferaPoder.getDescricao());
			}
		}
		
		//Capacidade Hidrometro
		parametros.put("capacidadeHidrometroFiltro", "");
		StringBuilder hidrometroCapacidades = new StringBuilder();
		if(!Util.isVazioOrNulo(filtro.getHidrometroCapacidade())){
			
			for(String hidrometroCapacidadeFiltro : filtro.getHidrometroCapacidade()){
				FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
				filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(FiltroHidrometroCapacidade.ID, hidrometroCapacidadeFiltro));
				Collection colecaoHidrometroCapacidade = fachada.pesquisar(filtroHidrometroCapacidade, HidrometroCapacidade.class.getName());
				
				if(!Util.isVazioOrNulo(colecaoHidrometroCapacidade)){
					HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) Util.retonarObjetoDeColecao(colecaoHidrometroCapacidade);
					hidrometroCapacidades.append(hidrometroCapacidade.getDescricao() + " ");
				}
			}
			parametros.put("capacidadeHidrometroFiltro", hidrometroCapacidades.toString());
		}
		
		//Tarifa de Consumo
		parametros.put("tarifaConsumoFiltro", "");
		StringBuilder tarifaConsumos = new StringBuilder();
		if(!Util.isVazioOrNulo(filtro.getConsumoTarifa())){
			
			for(String tarifaConsumoFiltro : filtro.getConsumoTarifa()){
				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
				filtroConsumoTarifa.adicionarParametro(new ParametroSimples(FiltroConsumoTarifa.ID, tarifaConsumoFiltro));
				Collection colecaoTarifaConsumo = fachada.pesquisar(filtroConsumoTarifa, ConsumoTarifa.class.getName());
				
				if(!Util.isVazioOrNulo(colecaoTarifaConsumo)){
					ConsumoTarifa consumoTarifa = (ConsumoTarifa) Util.retonarObjetoDeColecao(colecaoTarifaConsumo);
					tarifaConsumos.append(consumoTarifa.getDescricao() + " ");
				}
			}
			parametros.put("tarifaConsumoFiltro", tarifaConsumos.toString());			
		}
	}	
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionHelper  filtro = 
				new GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionHelper();
		
		filtro = this.informarDadosFiltroRelatorio(filtro);		
		
		return Fachada.getInstancia().pesquisarQuantidadeDadosRelatorioEnquadramentoDesenquadramentoGrandeCliente(filtro);
	}	

	@Override
	public void agendarTarefaBatch() {
	}
}
