package gcom.gui.relatorio.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
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
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarRelatorioEnquadramentoDesenquadramentoGrandeClienteAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("gerarRelatorioEnquadramentoDesenquadramentoGrandeCliente");		
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm form = 
				(GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm) actionForm;		
		
		if (Util.verificarNaoVazio((String) httpServletRequest.getParameter("menu"))){
			
			this.setarDadosIniciais(sessao);
			this.removerSessao(sessao);			
			form.limparForm();			
		
		}else{			
			
			//sempre que ele não informar o codigo do setor ele irá desabilitar a rota
			if(!Util.verificarNaoVazio(form.getCodigoSetorComercial())){
				sessao.removeAttribute("colecaoRota");
			}				
			
			//Pesquisar a Rota apartir do Setor Comercial
			if(Util.verificarNaoVazio(form.getCodigoSetorComercial())){
				this.pesquisarRotaSetorComercial(sessao, form);				
			}
			
			if(Util.verificarNaoVazio(form.getIdMunicipio())){
				this.pesquisarMunicipio(form, sessao);
			}
			
			if(Util.verificarNaoVazio(form.getIdLocalidade())){
				this.pesquisarLocalidade(form, sessao);
			}
			
			if(Util.verificarNaoVazio(form.getCodigoSetorComercial())){
				this.pesquisarSetorComercial(form, sessao);
			}
			
			if(Util.verificarNaoVazio(form.getIdQuadra())){
				this.pesquisarQuadra(form, sessao);
			}
		}
		
		return retorno;
	}
	
	private void removerSessao(HttpSession sessao){
		sessao.removeAttribute("colecaoRota");
		sessao.removeAttribute("setorComercialInexistente");
		sessao.removeAttribute("localidadeFinalInexistente");
		sessao.removeAttribute("MunicipioInexistente");		
	}
	
	private void pesquisarQuadra(GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm form, HttpSession sessao){
		
		if(!Util.verificarNaoVazio(form.getIdLocalidade())){
			throw new ActionServletException("atencao.naoinformado", null, "Localidade");
		}
		
		if(!Util.verificarIdNaoVazio(form.getCodigoSetorComercial())){
			throw new ActionServletException("atencao.naoinformado", null, "Setor Comercial");
		}
		
		FiltroSetorComercial filtroSetorComercial =  new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, form.getIdLocalidade()));
		Collection colecaoSetor = getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoSetor)){
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetor);
			
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercial.getId()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, form.getIdQuadra()));			
			Collection colecaoQuadra = getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
			
			if(Util.isVazioOrNulo(colecaoQuadra)){
				form.setIdQuadra("");
				throw new ActionServletException("atencao.processo.quadraNaoCadastrada");			
			}
		}		
	}
	
	private void pesquisarSetorComercial(GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm form, HttpSession sessao){
		
		if(!Util.verificarNaoVazio(form.getIdLocalidade())){
			throw new ActionServletException("atencao.naoinformado", null, "Localidade");
		}
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, 
			Integer.parseInt(form.getCodigoSetorComercial())));
		
		
		if(Util.verificarNaoVazio(form.getIdLocalidade())){
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, 
				form.getIdLocalidade()));
		}
		
		filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.LOCALIDADE);
		Collection colecaoSetorComercial = getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoSetorComercial)){
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			form.setNomeSetorComercial(setorComercial.getDescricao());
			sessao.setAttribute("setorComercialInexistente", true);
			this.pesquisarRotaSetorComercial(sessao, form);
		}else{
			form.setNomeSetorComercial("Setor Comercial Inexistente");
			form.setCodigoSetorComercial("");
			sessao.removeAttribute("setorComercialInexistente");
		}
	}
	
	private void pesquisarLocalidade(GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm form, HttpSession sessao){
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, Integer.parseInt(form.getIdLocalidade())));
		Collection colecaoLocalidade = getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoLocalidade)){
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			form.setNomeLocalidade(localidade.getDescricao());
			sessao.setAttribute("localidadeFinalInexistente", true);
		}else{
			form.setNomeLocalidade("Localidade Inexistente");
			form.setIdLocalidade("");
			sessao.removeAttribute("localidadeFinalInexistente");			
		}
	}
	
	private void pesquisarMunicipio(GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm form, HttpSession sessao){
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, Integer.parseInt(form.getIdMunicipio())));
		Collection colecaoMunicipio = getFachada().pesquisar(filtroMunicipio, Municipio.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoMunicipio)){
			Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
			form.setNomeMunicipio(municipio.getNome());
			sessao.setAttribute("MunicipioInexistente", true);			
		}else{
			form.setNomeMunicipio("Município Inexistente");
			form.setIdMunicipio("");
			sessao.removeAttribute("MunicipioInexistente");
		}
	}
	
	private void pesquisarRotaSetorComercial(HttpSession sessao, GerarRelatorioEnquadramentoDesenquadramentoGrandeClienteActionForm form){
		
		if(!Util.verificarNaoVazio(form.getIdLocalidade())){
			throw new ActionServletException("atencao.naoinformado", null, "Localidade");
		}		
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, form.getIdLocalidade()));
		filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.LOCALIDADE);
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.SIM));
		Collection colecaoSetorComercial = getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoSetorComercial)){
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
			
			FiltroRota filtroRota = new FiltroRota(FiltroRota.CODIGO_ROTA);
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_ID, setorComercial.getId()));
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO, ConstantesSistema.SIM));
			
			Collection colecaoRota = getFachada().pesquisar(filtroRota, Rota.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoRota)){						
				sessao.setAttribute("colecaoRota", colecaoRota);
			}
		}				
	}
	
	private void setarDadosIniciais(HttpSession sessao){		
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.SIM));
		Collection colecaoGerenciaRegional = getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoGerenciaRegional)){
			sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		}else{
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Gerência Regional");
		}		
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio(FiltroUnidadeNegocio.NOME);
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.SIM));
		Collection colecaoUnidadeNegocio = getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoUnidadeNegocio)){
			sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		}else{
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Unidade de Negócio");
		}		
		
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao(FiltroLigacaoAguaSituacao.DESCRICAO);
		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.SIM));
		Collection colecaoSitucaoLigacaoAgua = getFachada().pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoSitucaoLigacaoAgua)){
			sessao.setAttribute("colecaoSitucaoLigacaoAgua", colecaoSitucaoLigacaoAgua);
		}
		
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao(FiltroLigacaoEsgotoSituacao.DESCRICAO);
		filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.INDICADOR_USO, ConstantesSistema.SIM));
		Collection colecaoSitucaoLigacaoEsgoto = getFachada().pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoSitucaoLigacaoEsgoto)){
			sessao.setAttribute("colecaoSitucaoLigacaoEsgoto", colecaoSitucaoLigacaoEsgoto);
		}
		
		FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder(FiltroEsferaPoder.DESCRICAO);
		filtroEsferaPoder.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO, ConstantesSistema.SIM));
		Collection colecaoEsferaPoder = getFachada().pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoEsferaPoder)){
			sessao.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
		}else{
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Esfera Poder");
		}
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, ConstantesSistema.SIM));
		Collection colecaoCategoria = getFachada().pesquisar(filtroCategoria, Categoria.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoCategoria)){
			sessao.setAttribute("colecaoCategoria", colecaoCategoria);			
		}else{
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Categoria");
		}	
		
		FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
		filtroSubcategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.INDICADOR_USO, ConstantesSistema.SIM));
		Collection colecaoSubcategoria = getFachada().pesquisar(filtroSubcategoria, Subcategoria.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoSubcategoria)){
			sessao.setAttribute("colecaoSubcategoria", colecaoSubcategoria);
		}else{
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Subcategoria");
		}	
		
		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(FiltroHidrometroCapacidade.INDICADOR_USO, ConstantesSistema.SIM));
		Collection colecaoHidrometroCapacidade = getFachada().pesquisar(filtroHidrometroCapacidade, HidrometroCapacidade.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoHidrometroCapacidade)){
			sessao.setAttribute("colecaoHidrometroCapacidade", colecaoHidrometroCapacidade);
		}else{
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Hidrometro Capacidade");
		}
		
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		filtroConsumoTarifa.adicionarParametro(new ParametroSimples(FiltroConsumoTarifa.INDICADOR_USO, ConstantesSistema.SIM));
		Collection colecaoConsumoTarifa = getFachada().pesquisar(filtroConsumoTarifa, ConsumoTarifa.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoConsumoTarifa)){
			sessao.setAttribute("colecaoConsumoTarifa", colecaoConsumoTarifa);
		}else{
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Tarifa de Consumo");
		}
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.SIM));
		Collection colecaoImovelPerfil = getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoImovelPerfil)){
			sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);			
		}
	}	
}