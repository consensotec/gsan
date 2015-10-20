package gcom.relatorio.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.ExtratoQuitacao;
import gcom.faturamento.ExtratoQuitacaoItem;
import gcom.faturamento.FiltroExtratoQuitacao;
import gcom.gui.ActionServletException;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RelatorioDeclaracaoAnualQuitacaoDebitos extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioDeclaracaoAnualQuitacaoDebitos(Usuario usuario,String nomeRelatorio) {
		
		super(usuario, nomeRelatorio);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		
		
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDeclaracaoAnualQuitacaoDebitos", this);

	}

	@Override
	public Object executar() throws TarefaException {


		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String ano = (String) getParametro("ano");
		String matricula = (String) getParametro("matricula");
		String idGrupo = (String) getParametro("idGrupo");
		Boolean permissaoEmitirPorGrupo = (Boolean) getParametro("permissaoEmitirPorGrupo");
		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		FiltroExtratoQuitacao filtro = new FiltroExtratoQuitacao();
		
		/**/
		if(matricula != null && !matricula.equals("")){
			if(idGrupo == null || idGrupo.equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
				filtro.adicionarParametro(new ParametroSimples(
					FiltroExtratoQuitacao.ID_IMOVEL,matricula));
			}else{
				throw new ActionServletException("atencao.informe_apenas_grupo_ou_imovel");
			}
		}else{
			if(idGrupo != null && !idGrupo.equals("")){
				filtro.adicionarParametro(new ParametroSimples(
					FiltroExtratoQuitacao.FATURAMENTO_GRUPO_ID, idGrupo));
			}
		}
		
		filtro.adicionarParametro(new ParametroSimples(
				FiltroExtratoQuitacao.ANO_REFERENCIA,ano));
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		String nomeEmpresa = sistemaParametro.getNomeAbreviadoEmpresa();
		
		/* Ordena conforme a opção selecionada na tela
		 * opcaoTotalizacao = 1 - Ordena por rota
		 * opcaoTotalizacao = 2 - Ordena por Inscrição
		 */
		if (opcaoTotalizacao.equalsIgnoreCase("1")){
			filtro.setCampoOrderBy("imovel.localidade.id", "imovel.setorComercial.codigo", "imovel.quadra.rota.codigo", "imovel.numeroSequencialRota");
		} else {
			filtro.setCampoOrderBy("imovel.localidade.id", "imovel.setorComercial.codigo", "imovel.quadra.numeroQuadra", "imovel.lote", "imovel.subLote");
		}
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroExtratoQuitacao.IMOVEL);
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		
		Collection <ExtratoQuitacao>colecaoExtratoQuitacao =
			fachada.pesquisar(filtro, ExtratoQuitacao.class.getName());
		
		if(colecaoExtratoQuitacao == null || colecaoExtratoQuitacao.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		// coleção de beans do relatório
		ArrayList relatorioBeans = new ArrayList();
		RelatorioDeclaracaoAnualQuitacaoDebitosPaginaBean pagina;
		
		for(ExtratoQuitacao extrato: colecaoExtratoQuitacao){
			
			String cliente = fachada.consultarClienteUsuarioImovel(extrato.getImovel().getId());
			String endereco = fachada.pesquisarEndereco(extrato.getImovel().getId());
			String matriculaFormatada = Util.retornaMatriculaImovelFormatada(extrato.getImovel().getId());
			
			//cria um nova pagina com os atributos
			pagina = new RelatorioDeclaracaoAnualQuitacaoDebitosPaginaBean(cliente, endereco, matriculaFormatada);
			
			pagina.setInscricao(extrato.getImovel().getInscricaoFormatada());
			
			Collection<ExtratoQuitacaoItem> colecaoExtratosItens 
			= fachada.pesquisarExtratoQuitacaoItensParaGeracaoArquivoTexto(extrato.getId());
			
			
			RelatorioDeclaracaoAnualQuitacaoDebitosBean bean = null;
			BigDecimal valorTotal = new BigDecimal("0.0");
			
			//Cria uma colecao de itens da pagina
			ArrayList relatorioItenBeans = new ArrayList();
			
			for (ExtratoQuitacaoItem extratoQuitacaoItem : colecaoExtratosItens) {
				bean = new RelatorioDeclaracaoAnualQuitacaoDebitosBean(
					extrato.getAnoReferencia().toString(), 
					Util.formatarAnoMesParaMesAno(extratoQuitacaoItem.getAnoMesReferenciaConta()),
					extratoQuitacaoItem.getDescricaoSituacao(), 
					Util.formatarData(extratoQuitacaoItem.getDataSituacao()),
					Util.formatarMoedaReal(extratoQuitacaoItem.getValorConta()));
				
				valorTotal = valorTotal.add(extratoQuitacaoItem.getValorConta());
				
				//adiciona um item à colecao de itens da pagina
				relatorioItenBeans.add(bean);
				
			}
			
			bean = new RelatorioDeclaracaoAnualQuitacaoDebitosBean();
			
			bean.setAnoMes("TOTAL");
			bean.setValor(Util.formatarMoedaReal(valorTotal));
			relatorioItenBeans.add(bean);
			
			//adiciona a colecao de itens 
			pagina.setColecaoExtratoQuitacaoItem(relatorioItenBeans);
			
			// adiciona os parâmetros do relatório
			// adiciona o laudo da análise
			
			
			String seguencial = "";
			
			String idImovel = "";
			
			if(matricula != null && !matricula.equals("")){
				idImovel = matricula;
			}else{
				idImovel = extrato.getImovel().getId().toString();
			}
			if(nomeEmpresa.equalsIgnoreCase("CAERN")){
				
				Object[] idLocalidadeCodigoSetor =
					fachada.pesquisarLocalidadeCodigoSetorImovel(new Integer(idImovel));
				
				Object[] rotaESequencialRotaDoImovel =
					fachada.obterRotaESequencialRotaDoImovelSeparados(new Integer(idImovel));
				
				seguencial = idLocalidadeCodigoSetor[0]+" / "
					+idLocalidadeCodigoSetor[1]+" / "
					+rotaESequencialRotaDoImovel[0]+" / "
					+rotaESequencialRotaDoImovel[1];
				
			}else if(nomeEmpresa.equalsIgnoreCase("CAEMA")){
				
				String cnpjInscricao = "CNPJ: ";
				
				cnpjInscricao += Util.formatarCnpj(sistemaParametro.getCnpjEmpresa())+" Inscrição Estadual: ";
				cnpjInscricao += sistemaParametro.getInscricaoEstadual();
				
				pagina.setCnpjInscricao(cnpjInscricao);
				
				pagina.setNomeCompleto(sistemaParametro.getNomeEmpresa());
				
				pagina.setNomeAbreviado(sistemaParametro.getNomeAbreviadoEmpresa());
				
				pagina.setDataEmissao(Util.formatarData(new Date()));
					
				seguencial = extrato.getId().toString();
				
			}else{
				
				seguencial = extrato.getId().toString();

			}

			pagina.setAno(extrato.getAnoReferencia().toString());
			pagina.setSequencial(seguencial);
			
			//adiciona a pagina
			relatorioBeans.add(pagina);
		
		}//Fim do primeiro for
		
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(this.getNomeRelatorio(), parametros, ds,tipoFormatoRelatorio);
		
		// retorna o relatório gerado
		return retorno;
	}
}
