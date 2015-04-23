package gsan.gui.atualizacaocadastral;

import gsan.atualizacaocadastral.FiltroMapaAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.MapaAtualizacaoCadastralDM;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.io.File;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * [UC1646] Informar Mapa da Quadra
 * [SB0001] Importar Arquivo
 * 
 * @author André Miranda
 * @date 04/12/2014
 */
public class UploadMapaQuadraAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		ExibirInformarMapaQuadraActionForm form = (ExibirInformarMapaQuadraActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		String confirmado = httpServletRequest.getParameter("confirmado");

		Localidade localidade = pesquisarLocalidade(form);
 		SetorComercial setor = pesquisarSetorComercial(form);
 		Quadra quadra = pesquisarQuadra(form);

		File mapFile = null;
		File kmlFile = null;

		try {
			FiltroMapaAtualizacaoCadastralDM filtro = new FiltroMapaAtualizacaoCadastralDM();
			filtro.adicionarParametro(new ParametroSimples(FiltroMapaAtualizacaoCadastralDM.ID_LOCALIDADE, localidade.getId()));
			filtro.adicionarParametro(new ParametroSimples(FiltroMapaAtualizacaoCadastralDM.ID_SETOR_COMERCIAL, setor.getId()));
			filtro.adicionarParametro(new ParametroSimples(FiltroMapaAtualizacaoCadastralDM.ID_QUADRA, quadra.getId()));
	 		Collection colecaoMapa = Fachada.getInstancia().pesquisar(filtro, MapaAtualizacaoCadastralDM.class.getName());
	 		MapaAtualizacaoCadastralDM mapa = (MapaAtualizacaoCadastralDM) Util.retonarObjetoDeColecao(colecaoMapa);

	 		if (mapa != null && !"ok".equals(confirmado)) {
				return montarPaginaConfirmacao("atencao.mapa_quadra_existente_substituir", httpServletRequest, actionMapping); 
	 		}

	 		if (mapa == null) {
				mapa = new MapaAtualizacaoCadastralDM();
			}

	 		long timestamp = System.currentTimeMillis();
	 		FormFile kmzFile = form.getFormFile();
			mapFile = new File(timestamp + ".map");
			kmlFile = new File(timestamp + ".kml");
			StringBuilder json = new StringBuilder();

			double[] coords = Fachada.getInstancia().converterArquivoMap(kmzFile.getInputStream(), mapFile, kmlFile,
					json);

			mapa.setLocalidade(localidade);
			mapa.setSetorComercial(setor);
			mapa.setQuadra(quadra);
			mapa.setArquivoKml(Util.converterFileParaArrayByte(kmlFile));
			mapa.setArquivoMap(Util.converterFileParaArrayByte(mapFile));
			mapa.setArquivoJson(json.toString().getBytes());
			mapa.setCoordenadaY(coords[0]); // latitude
			mapa.setCoordenadaX(coords[1]); // longitude
			mapa.setUsuario((Usuario) sessao.getAttribute("usuarioLogado"));
			mapa.setUltimaAlteracao(new Date());

			if (mapa.getId() == null) {
				Fachada.getInstancia().inserir(mapa);
			} else {
				Fachada.getInstancia().atualizar(mapa);
			}

			form.setNumeroQuadra("");
			form.setDescricaoQuadra("");
			montarPaginaSucesso(httpServletRequest,
					"O arquivo " + kmzFile.getFileName() + " foi processado com sucesso.",
					"Informar outro mapa de quadra", "exibirInformarMapaQuadraAction.do");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.falha_processamento_arquivo_kmz");
		} finally {
			FileUtils.deleteQuietly(kmlFile);
			FileUtils.deleteQuietly(mapFile);
		}

		return retorno;
	}

	private Localidade pesquisarLocalidade(ExibirInformarMapaQuadraActionForm form) {
		FiltroLocalidade filtro = new FiltroLocalidade();
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colLocalidade = Fachada.getInstancia().pesquisar(filtro, Localidade.class.getName());
		if(Util.isVazioOrNulo(colLocalidade)){
			throw new ActionServletException("atencao.localidade.inexistente");
		}

		return (Localidade) Util.retonarObjetoDeColecao(colLocalidade);
	}

	private SetorComercial pesquisarSetorComercial(ExibirInformarMapaQuadraActionForm form) {
		FiltroSetorComercial filtro = new FiltroSetorComercial();
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colSetorComercial = Fachada.getInstancia().pesquisar(filtro, SetorComercial.class.getName());
		if(Util.isVazioOrNulo(colSetorComercial)){
			throw new ActionServletException("atencao.setor_comercial.inexistente");
		}

		return (SetorComercial) Util.retonarObjetoDeColecao(colSetorComercial);
	}

	private Quadra pesquisarQuadra(ExibirInformarMapaQuadraActionForm form) {
		FiltroQuadra filtro = new FiltroQuadra();
		filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA,	form.getNumeroQuadra()));
		filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercial()));
		filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getIdLocalidade()));
		filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colQuadra = Fachada.getInstancia().pesquisar(filtro, Quadra.class.getName());
		if (Util.isVazioOrNulo(colQuadra)) {
			throw new ActionServletException("atencao.quadra.inexistente");
		}

		return (Quadra) Util.retonarObjetoDeColecao(colQuadra);
	}
}
