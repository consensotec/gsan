package gsan.gui.relatorio;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.imovel.Imovel;
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.gui.ActionServletException;
import gsan.gui.micromedicao.ConsultarArquivoTextoLeituraActionForm;
import gsan.gui.relatorio.micromedicao.FiltroRelatorioLeituraConsultarArquivosTextoHelper;
import gsan.micromedicao.FiltroLeiturista;
import gsan.micromedicao.FiltroSituacaoTransmissaoLeitura;
import gsan.micromedicao.Leiturista;
import gsan.micromedicao.SituacaoTransmissaoLeitura;
import gsan.micromedicao.bean.ConsultarArquivoTextoRoteiroEmpresaHelper;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.RelatorioVazioException;
import gsan.relatorio.micromedicao.RelatorioLeituraConsultarArquivoTextos;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.SistemaException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioLeituraConsultarArquivosTextoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	@Override
	public ActionForward execute(ActionMapping actionMapping, 
		ActionForm actionForm, HttpServletRequest request, 
		HttpServletResponse response) throws Exception {
		
		//	 cria a vari�vel de retorno
		ActionForward retorno = null;

		//form que vem do arquivo_texto_Leitura_consultar.jsp
		ConsultarArquivoTextoLeituraActionForm form = (ConsultarArquivoTextoLeituraActionForm) actionForm;
		
		request.setAttribute("telaSucessoRelatorio",true);
		
		FiltroRelatorioLeituraConsultarArquivosTextoHelper helper = new FiltroRelatorioLeituraConsultarArquivosTextoHelper();
		ConsultarArquivoTextoRoteiroEmpresaHelper consultaArquivoTexto = new  ConsultarArquivoTextoRoteiroEmpresaHelper();
		

		boolean peloMenosUmParametroInformado = false;

		//Parte que pega as variaveis do form passado como par�metro e armazena localmente
		String empresaID = form.getEmpresaID();
		String grupoFaturamentoID = form.getGrupoFaturamentoID();
		String mesAno = form.getMesAno();
		String situaTransmLeitura = form.getSituaTransmLeitura();
		String leiturista = form.getLeituristaID();
		String servicoTipoCelular = form.getServicoTipoCelular();
		String localidade = form.getIdLocalidade();

		Usuario usuarioLogado = (Usuario) this.getSessao(request).getAttribute("usuarioLogado");
		//fim da parte que pega as variaveis do form passado como par�metro e armazena localmente
		
		this.getSessao(request).removeAttribute("permissao");
		if (usuarioLogado.getEmpresa().getIndicadorEmpresaPrincipal().equals(
				new Short("1"))) {
			this.getSessao(request).setAttribute("permissao", "1");
		} else {
			this.getSessao(request).setAttribute("permissao", "2");
		}
		
		//verifica se o campo empresa Id foi informado 
		if (empresaID != null && 
			!empresaID.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;
			helper.setIdEmpresa(empresaID);
		}

		//verifica se o campo empresa Id foi informado 
		if (localidade != null && 
			!localidade.equals("")&& 
			!localidade.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;
			helper.setIdLocalidade(localidade);
		}
		
		
		// Verifica se o campo Grupo de Faturamento foi informado

		if (grupoFaturamentoID != null
				&& !grupoFaturamentoID.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;
			helper.setIdGrupoFaturamento(grupoFaturamentoID);

		}

		// // M�s de Refer�ncia
		if (mesAno != null && !mesAno.trim().equals("")) {

			peloMenosUmParametroInformado = true;
			helper.setAnoMes(Util.formatarMesAnoComBarraParaAnoMes(mesAno).toString());
		}

		if (leiturista != null && !leiturista.equals("")
				&& !leiturista.equals("-1")) {

			peloMenosUmParametroInformado = true;
			helper.setIdLeiturista(leiturista);

		}

		// Verifica se o campo Situa��o Texto para Leitura foi informado
		if (situaTransmLeitura != null
				&& !situaTransmLeitura.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			helper.setIdSituacaoTransmissaoLeitura(situaTransmLeitura);

		}
		if(servicoTipoCelular != null && 
		   !servicoTipoCelular.trim().equalsIgnoreCase("")){
			
			peloMenosUmParametroInformado = true;
			helper.setIdServicoTipoCelular(servicoTipoCelular);			
		}

		// Erro caso o usu�rio mandou Pesquisar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		Integer quantidade = this.getFachada().consultarCountRelatorioLeituraConsultarArquivosTexto(helper); 
		
		if (quantidade == null || quantidade.intValue() == 0) {
			throw new ActionServletException(
					"atencao.nenhum_arquivo_texto_roteiro_empresa", null,
					"Arquivo Texto Leitura");

		}
		

		this.getSessao(request).removeAttribute("grupoFaturamentoID");
		
		// cria uma inst�ncia da classe do relat�rio
		RelatorioLeituraConsultarArquivoTextos relatorioArquivosTextos = new RelatorioLeituraConsultarArquivoTextos(
				(Usuario)(request.getSession(false)).getAttribute("usuarioLogado"));

		
		relatorioArquivosTextos.addParametro("mesAno",form.getMesAno());
		relatorioArquivosTextos.addParametro("tamanhoColecaoArquivosText",quantidade);
		relatorioArquivosTextos.addParametro("helper",helper);
		
		if(situaTransmLeitura != null && !situaTransmLeitura.equals("") && !situaTransmLeitura.equals("-1")){
			
			FiltroSituacaoTransmissaoLeitura filtroSituacaoTransmissaoLeitura = 
				new FiltroSituacaoTransmissaoLeitura();
			filtroSituacaoTransmissaoLeitura.adicionarParametro( 
				new ParametroSimples( FiltroSituacaoTransmissaoLeitura.ID, situaTransmLeitura));
			
			Collection colecaoRetorno = this.getFachada().pesquisar(filtroSituacaoTransmissaoLeitura, SituacaoTransmissaoLeitura.class.getName());
			
			SituacaoTransmissaoLeitura situacaoTransmissaoLeitura = (SituacaoTransmissaoLeitura) Util.retonarObjetoDeColecao(colecaoRetorno);
			
			relatorioArquivosTextos.addParametro("situacaoTextoLeitura",situacaoTransmissaoLeitura.getDescricaoSituacao());	
		}else{
			relatorioArquivosTextos.addParametro("situacaoTextoLeitura","TODOS");
		}
		
		Collection colecaoFaturamentoGrupo = (Collection)
			this.getSessao(request).getAttribute("colecaoFaturamentoGrupo");
		
		//esse for procura pelo faturamento grupo selecionado no form de consulta
		for (Iterator iter = colecaoFaturamentoGrupo.iterator(); iter.hasNext();) {
			FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) iter.next();
			
			if(faturamentoGrupo.getId().intValue() == Integer.parseInt(form.getGrupoFaturamentoID())){
				relatorioArquivosTextos.addParametro("nomeGrupoFaturamento",faturamentoGrupo.getDescricao());
			}
		}
		
		Collection colecaoEmpresa = (Collection) this.getSessao(request).getAttribute("colecaoEmpresa");
		Empresa empresa = null;
		
		for (Iterator iter = colecaoEmpresa.iterator(); iter.hasNext();) {
			empresa = (Empresa) iter.next();
			
			if(empresa.getId() == Integer.parseInt(form.getEmpresaID())){
				break;
			}
		}
				
		if(empresa != null){
			relatorioArquivosTextos.addParametro("nomeEmpresa",empresa.getDescricaoAbreviada());	
		}
		
		if(leiturista != null && !leiturista.equals("") && !leiturista.equals("-1")){
			
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
			filtroLeiturista.adicionarParametro( new ParametroSimples( FiltroLeiturista.ID, leiturista));
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			
			Collection colecaoLeiturista = this.getFachada().pesquisar(filtroLeiturista, Leiturista.class.getName());
			
			Leiturista leitu = (Leiturista) Util.retonarObjetoDeColecao(colecaoLeiturista);
			
			if(leitu != null){
				
				if( leitu.getFuncionario() != null ) {
					relatorioArquivosTextos.addParametro("nomeLeiturista",leitu.getFuncionario().getNome());
				} else {
					relatorioArquivosTextos.addParametro("nomeLeiturista",leitu.getCliente().getNome());
				}
			}			
		}	
		
		
		String tipoRelatorio = request.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		

		relatorioArquivosTextos.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioArquivosTextos,
					TarefaRelatorio.TIPO_PDF, request, response,
					actionMapping);

		} catch (SistemaException ex) {
			reportarErros(request, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(request, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
		
		return retorno;
	}

}
