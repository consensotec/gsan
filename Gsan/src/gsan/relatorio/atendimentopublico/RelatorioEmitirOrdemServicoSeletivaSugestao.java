/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio Virginio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.relatorio.atendimentopublico;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.batch.Relatorio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gsan.micromedicao.hidrometro.HidrometroCapacidade;
import gsan.micromedicao.leitura.FiltroLeituraAnormalidade;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.atendimentopublico.bean.ImovelEmissaoOrdensSeletivasHelper;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * [UC0711] - Emitir Ordem de Servico Seletiva
 * 
 * @author Ivan S�rgio, Raphael Rossiter
 * @date 06/11/2007, 16/04/2009
 */
public class RelatorioEmitirOrdemServicoSeletivaSugestao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioEmitirOrdemServicoSeletivaSugestao
	 */
	public RelatorioEmitirOrdemServicoSeletivaSugestao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA_SUGESTAO);
	}
	
	@Deprecated
	public RelatorioEmitirOrdemServicoSeletivaSugestao() {
		super(null, "");
	}

	public Object executar() throws TarefaException {
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		ImovelEmissaoOrdensSeletivasHelper helper = gerarObjetoHelper();
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		Fachada fachada = Fachada.getInstancia();
		
		//[UC0711] Filtro para Emissao de Ordens Seletivas
		Collection colecaoDadosRelatorio = fachada.filtrarImovelEmissaoOrdensSeletivas(helper);
		
		String totalSelecionados = "0";
		List relatorioBeans = new ArrayList();
		
		RelatorioEmitirOrdemServicoSeletivaSugestaoBean relatorioEmitirOrdemServicoSeletivaSugestaoBean = 
			new RelatorioEmitirOrdemServicoSeletivaSugestaoBean();
		
		if (colecaoDadosRelatorio != null && !colecaoDadosRelatorio.isEmpty()) {
			totalSelecionados = Util.converterObjetoParaString(colecaoDadosRelatorio.size());
		}
		
		if ( helper.getTipoEmissao().equals("SINTETICO") &&
			(colecaoDadosRelatorio != null && !colecaoDadosRelatorio.isEmpty() ) &&			
			(( helper.getLocalidadeInicial() == null && helper.getLocalidadeFinal() == null ) ||
			 ( helper.getLocalidadeInicial().equalsIgnoreCase("") && helper.getLocalidadeFinal().equalsIgnoreCase("") ) ||
			 ( helper.getLocalidadeInicial() != null && !helper.getLocalidadeFinal().equals(helper.getLocalidadeInicial()) ) ||
			 ( helper.getLocalidadeInicial() != null && helper.getLocalidadeFinal().equals(helper.getLocalidadeInicial()) )) 
			){
			
			//Montando o BEAN agrupados pela localidade ou setor
			relatorioBeans = this.gerarRelatorioBeanAgrupadoLocalidadeOuSetor(helper, colecaoDadosRelatorio);
		}else {
			//Montando o BEAN a partir dos par�metros passados
			relatorioEmitirOrdemServicoSeletivaSugestaoBean = this.gerarRelatorioBean(helper, totalSelecionados);
			relatorioBeans.add(relatorioEmitirOrdemServicoSeletivaSugestaoBean);
		}
		
		
		// PAR�METROS PARA O RELAT�RIO
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Map parametros = new HashMap();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		
		// GERANDO O RELAT�RIO
		
		
		
		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);
		
		byte[] retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA_SUGESTAO,
		parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA_SUGESTAO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------
		// retorna o relat�rio gerado

		return retorno;
	}

	private ImovelEmissaoOrdensSeletivasHelper gerarObjetoHelper() {
		ImovelEmissaoOrdensSeletivasHelper helper = new ImovelEmissaoOrdensSeletivasHelper();
		
		// PAR�METROS
		helper.setTipoOrdem((String) getParametro("tipoOrdem"));
		helper.setFirma((String) getParametro("firma"));
		helper.setNomeFirma((String) getParametro("nomeFirma"));
		helper.setQuantidadeMaxima((String) getParametro("quantidadeMaxima"));
		helper.setTipoEmissao((String) getParametro("tipoEmissao"));
		
		
		helper.setIdImovel((String) getParametro("idImovel"));
		helper.setElo((String) getParametro("elo"));
		helper.setNomeElo((String) getParametro("nomeElo"));
		helper.setGerenciaRegional((String) getParametro("gerenciaRegional"));
		helper.setNomeGerenciaRegional((String) getParametro("nomeGerenciaRegional"));
		helper.setUnidadeNegocio((String) getParametro("unidadeNegocio"));
		helper.setNomeUnidadeNegocio((String) getParametro("nomeUnidadeNegocio"));
		
		helper.setLocalidadeInicial((String) getParametro("localidadeInicial"));
		helper.setNomeLocalidadeInicial((String) getParametro("nomeLocalidadeInicial"));
		
		helper.setLocalidadeFinal((String) getParametro("localidadeFinal"));
		helper.setNomeLocalidadeFinal((String) getParametro("nomeLocalidadeFinal"));
		
		helper.setSetorComercialInicial((String) getParametro("setorComercialInicial"));
		helper.setCodigoSetorComercialInicial((String) getParametro("codigoSetorComercialInicial"));
		helper.setSetorComercialFinal((String) getParametro("setorComercialFinal"));
		helper.setCodigoSetorComercialFinal((String) getParametro("codigoSetorComercialFinal"));
		
		helper.setQuadraInicial((String) getParametro("quadraInicial"));
		helper.setQuadraFinal((String) getParametro("quadraFinal"));
		helper.setRotaInicial((String) getParametro("rotaInicial"));
		helper.setRotaFinal((String) getParametro("rotaFinal"));
		helper.setRotaSequenciaInicial((String) getParametro("rotaSequenciaInicial"));
		helper.setRotaSequenciaFinal((String) getParametro("rotaSequenciaFinal"));
		
		helper.setLogradouro((String) getParametro("logradouro"));
		helper.setDescricaoLogradouro((String) getParametro("descricaoLogradouro"));
		
		// CARACTER�STICAS
		helper.setPerfilImovel((String) getParametro("perfilImovel"));
		helper.setCategoria((String) getParametro("categoria"));
		helper.setSubCategoria((String) getParametro("subCategoria"));
		
		helper.setQuantidadeEconomiasInicial((String) getParametro("intervaloQuantidadeEconomiasInicial"));
		helper.setQuantidadeEconomiasFinal((String) getParametro("intervaloQuantidadeEconomiasFinal"));
		helper.setQuantidadeDocumentosInicial((String) getParametro("intervaloQuantidadeDocumentosInicial"));
		helper.setQuantidadeDocumentosFinal((String) getParametro("intervaloQuantidadeDocumentosFinal"));
		
		helper.setNumeroMoradoresInicial((String) getParametro("intervaloNumeroMoradoresInicial"));
		helper.setNumeroMoradoresFinal((String) getParametro("intervaloNumeroMoradoresFinal"));
		helper.setAreaConstruidaInicial((String) getParametro("intervaloAreaConstruidaInicial"));
		helper.setAreaConstruidaFinal((String) getParametro("intervaloAreaConstruidaFinal"));
		
		helper.setSituacaoLigacaoAgua((String[])getParametro("situacaoLigacaoAgua"));
		helper.setSituacaoLigacaoAguaDescricao((String)getParametro("situacaoLigacaoAguaDescricao"));
		
		String imovelCondominio = "";
		if(getParametro("imovelCondominio") != null){
			imovelCondominio = (String) getParametro("imovelCondominio");
		}
		helper.setImovelCondominio(imovelCondominio);
		
		helper.setMediaImovel((String) getParametro("mediaImovel"));
		helper.setConsumoPorEconomia((String) getParametro("consumoPorEconomia"));
		helper.setConsumoPorEconomiaFinal((String) getParametro("consumoPorEconomiaFinal"));
		
		String tipoMedicao = "";
		if(getParametro("tipoMedicao") != null){
			tipoMedicao = (String) getParametro("tipoMedicao");
		}
		helper.setTipoMedicao(tipoMedicao);
		
		// HIDR�METRO
		helper.setCapacidadeHidrometro((String[]) getParametro("capacidade"));
		helper.setMarcaHidrometro((String) getParametro("marca"));
		helper.setAnormalidadeHidrometro((String[]) getParametro("anormalidadeHidrometro"));
		helper.setNumeroOcorrenciasAnormalidade((String) getParametro("numeroOcorrenciasConsecutivas"));
		//helper.setMesAnoInstalacaoHidrometro((String) getParametro("mesAnoInstalacao"));
		helper.setMesAnoInstalacaoInicialHidrometro((String) getParametro("mesAnoInstalacaoInicial"));
		helper.setMesAnoInstalacaoFinalHidrometro((String) getParametro("mesAnoInstalacaoFinal"));
		
		if(getParametro("localInstalacao") != null){
			helper.setLocalInstalacaoHidrometro((String) getParametro("localInstalacao"));
		}
		
		// SUGEST�O
		helper.setDescricaoPerfilImovel((String) getParametro("perfilImovelDescricao"));
		helper.setDescricaoCategoria((String) getParametro("categoriaDescricao"));
		helper.setDescricaoSubcategoria((String) getParametro("subCategoriaDescricao"));
		helper.setQuantidadeEconomia((String) getParametro("quantidadeEconomia"));
		helper.setQuantidadeDocumentos((String) getParametro("quantidadeDocumentos"));
		helper.setNumeroMoradores((String) getParametro("numeroMoradores"));
		helper.setAreaConstruida((String) getParametro("areaConstruida"));
		helper.setConsumoEconomia((String) getParametro("consumoEconomia"));
		helper.setDescricaoTipoMedicao((String) getParametro("tipoMedicaoDescricao"));
		helper.setDescricaoCapacidade((String) getParametro("capacidadeDescricao"));
		helper.setDescricaoMarcaHidrometro((String) getParametro("marcaDescricao"));
		helper.setDescricaoLocalInstalacaoHidrometro((String) getParametro("localInstalacaoDescricao"));
		return helper;
	}
	
	/**
	 * Montando o BEAN a partir dos par�metros passados
	 *
	 * @author Raphael Rossiter
	 * @date 17/04/2009
	 *
	 * @param helper
	 * @param totalSelecionados
	 * @return RelatorioEmitirOrdemServicoSeletivaSugestaoBean
	 */
	private RelatorioEmitirOrdemServicoSeletivaSugestaoBean gerarRelatorioBean(
			ImovelEmissaoOrdensSeletivasHelper helper, String totalSelecionados){
		
		RelatorioEmitirOrdemServicoSeletivaSugestaoBean relatorioEmitirOrdemServicoSeletivaSugestaoBean = 
		new RelatorioEmitirOrdemServicoSeletivaSugestaoBean();
		
		Fachada fachada = Fachada.getInstancia();
		
		// Imprimir pagina com os parametros informados e a quantidade de imoveis selecionados.
		
		//Preenche o BEAN do Relatorio
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setDescricaoTipoServico(helper.getTipoOrdem());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setTotalSelecionados(totalSelecionados);
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setFirma(helper.getFirma());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeFirma(helper.getNomeFirma());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeElo(helper.getNomeElo());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setTipoOrdem(helper.getTipoOrdem());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuantidadeMaxima(helper.getQuantidadeMaxima());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeLocalidadeInicial(helper.getNomeLocalidadeInicial());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeLocalidadeFinal(helper.getNomeLocalidadeFinal());
		
		// Foi Solicitado o Codigo em vez do Nome
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeSetorComercialInicial(helper.getCodigoSetorComercialInicial());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeSetorComercialFinal(helper.getCodigoSetorComercialFinal());
		
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuadraInicial(helper.getQuadraInicial());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuadraFinal(helper.getQuadraFinal());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setPerfilImovelDescricao(helper.getDescricaoPerfilImovel());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setCategoriaDescricao(helper.getDescricaoCategoria());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setSubCategoriaDescricao(helper.getDescricaoSubcategoria());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuantidadeEconomia(helper.getQuantidadeEconomia());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuantidadeDocumentos(helper.getQuantidadeDocumentos());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNumeroMoradores(helper.getNumeroMoradores());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setAreaConstruida(helper.getAreaConstruida());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setImovelCondominio(helper.getImovelCondominio());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMediaImovel(helper.getMediaImovel());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setConsumoEconomia(helper.getConsumoEconomia());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setTipoMedicaoDescricao(helper.getDescricaoTipoMedicao());
		//relatorioEmitirOrdemServicoSeletivaSugestaoBean.setCapacidadeDescricao(helper.getDescricaoCapacidade());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMarcaDescricao(helper.getDescricaoMarcaHidrometro());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setLocalInstalacaoDescricao(helper.getDescricaoLocalInstalacaoHidrometro());
		
		if (helper.getMesAnoInstalacaoInicialHidrometro() != null && !helper.getMesAnoInstalacaoInicialHidrometro().equals("")) {
			
			String mesAnoInstalacaoInicialHidrometro = helper.getMesAnoInstalacaoInicialHidrometro().substring(4, 6)
			+ "/" + helper.getMesAnoInstalacaoInicialHidrometro().substring(0, 4);
			
			helper.setMesAnoInstalacaoInicialHidrometro(mesAnoInstalacaoInicialHidrometro);
		}
		
		if (helper.getMesAnoInstalacaoFinalHidrometro() != null && !helper.getMesAnoInstalacaoFinalHidrometro().equals("")) {
			
			String mesAnoInstalacaoFinalHidrometro = helper.getMesAnoInstalacaoFinalHidrometro().substring(4, 6)
			+ "/" + helper.getMesAnoInstalacaoFinalHidrometro().substring(0, 4);
			
			helper.setMesAnoInstalacaoFinalHidrometro(mesAnoInstalacaoFinalHidrometro);
		}
		
		//relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMesAnoInstalacao(helper.getMesAnoInstalacaoHidrometro());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMesAnoInstalacaoInicial(helper.getMesAnoInstalacaoInicialHidrometro());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMesAnoInstalacaoFinal(helper.getMesAnoInstalacaoFinalHidrometro());
		
		String anormalidadeHidrometro = new String();
	
		if (helper.getAnormalidadeHidrometro() != null){
			for ( int i = 0; i < helper.getAnormalidadeHidrometro().length; i++ ){
				
				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				
				filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
						FiltroLeituraAnormalidade.ID, helper.getAnormalidadeHidrometro()[i]));
				
				Collection collectionHidrometroAnormalidade = fachada.pesquisar(filtroLeituraAnormalidade,
						LeituraAnormalidade.class.getName());
				
				LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) Util.retonarObjetoDeColecao(collectionHidrometroAnormalidade);
				
				anormalidadeHidrometro = anormalidadeHidrometro + leituraAnormalidade.getDescricao()+"\n";
				
			}
		}
		//String situacaoLigacaoAguaDescricao = "--";
		String situacaoLigacaoAguaDescricao = new String();
		if (helper.getSituacaoLigacaoAgua()!= null){
			for ( int i = 0; i < helper.getSituacaoLigacaoAgua().length; i++ ){
				
				FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
				filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
						FiltroLigacaoAguaSituacao.ID, helper.getSituacaoLigacaoAgua()[i]));
				
				Collection colecaoLigacaoAguaSituacao = Fachada.getInstancia()
							.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
				
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
				
				situacaoLigacaoAguaDescricao = situacaoLigacaoAguaDescricao + ligacaoAguaSituacao.getDescricao()+"\n";
				
			}
		}
		
		String capacidadeDescricao = new String();
		
		if (helper.getCapacidadeHidrometro() != null){
			for ( int i = 0; i < helper.getCapacidadeHidrometro().length; i++ ){
				
				FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
				
				filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
						FiltroHidrometroCapacidade.ID, helper.getCapacidadeHidrometro()[i]));
				
				Collection collectionHidrometroCapacidade = fachada.pesquisar(filtroHidrometroCapacidade,
						HidrometroCapacidade.class.getName());
				
				HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) Util.retonarObjetoDeColecao(collectionHidrometroCapacidade);
				
				capacidadeDescricao = capacidadeDescricao + hidrometroCapacidade.getDescricao()+"\n";
				
			}
		}
		
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setSituacaoLigacaoAguaDescricao(helper.getSituacaoLigacaoAguaDescricao());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setCapacidadeDescricao(capacidadeDescricao);
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setAnormalidadeHidrometro(anormalidadeHidrometro);
		
		return relatorioEmitirOrdemServicoSeletivaSugestaoBean;
	}
	
	/**
	 * Montando o BEAN agrupados pela localidade
	 *
	 * @author Rodrigo Cabral
	 * @date 30/03/2011
	 *
	 * @param helper
	 * @param colecaoDadosRelatorio
	 * @return RelatorioEmitirOrdemServicoSeletivaSugestaoBean
	 */
	private List gerarRelatorioBeanAgrupadoLocalidadeOuSetor(
			ImovelEmissaoOrdensSeletivasHelper helper, Collection colecaoDadosRelatorio){
		
		
		Fachada fachada = Fachada.getInstancia();
		
		List colecaoBean = new ArrayList();
		Collection colecaoImoveis = new ArrayList();
		String totalSelecionados = "0";
		
		Iterator iColecaoDadosRelatorio = colecaoDadosRelatorio.iterator();
		
		while (iColecaoDadosRelatorio.hasNext()){
		
			RelatorioEmitirOrdemServicoSeletivaSugestaoBean relatorioEmitirOrdemServicoSeletivaSugestaoBean = 
				new RelatorioEmitirOrdemServicoSeletivaSugestaoBean();
			
			Map colecaoDados = (HashMap) iColecaoDadosRelatorio.next();
			
			colecaoImoveis = (Collection) colecaoDados.get("colecaoImoveis");
			totalSelecionados = Util.converterObjetoParaString(colecaoImoveis.size());
			
		// Imprimir pagina com os parametros informados e a quantidade de imoveis selecionados.
		
		//Preenche o BEAN do Relatorio
		
		if ((helper.getLocalidadeInicial() == null && helper.getLocalidadeFinal() == null) ||
			(helper.getLocalidadeInicial().equalsIgnoreCase("") && helper.getLocalidadeFinal().equalsIgnoreCase("")) ||
			(helper.getLocalidadeInicial() != null && (!helper.getLocalidadeFinal().equals(helper.getLocalidadeInicial())))){
				
			relatorioEmitirOrdemServicoSeletivaSugestaoBean.setIdLocalidade(colecaoDados.get("idLocalidade").toString());
			relatorioEmitirOrdemServicoSeletivaSugestaoBean.setDesLocalidade(colecaoDados.get("desLocalidade").toString());
		
		} else if (helper.getLocalidadeInicial() != null && (helper.getLocalidadeFinal().equals(helper.getLocalidadeInicial()))) {
			
			relatorioEmitirOrdemServicoSeletivaSugestaoBean.setIdSetorComercial(colecaoDados.get("idSetorComercial").toString());
			relatorioEmitirOrdemServicoSeletivaSugestaoBean.setDesSetorComercial(colecaoDados.get("desSetorComercial").toString());
		}
		
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setDescricaoTipoServico(helper.getTipoOrdem());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setTotalSelecionados(totalSelecionados);
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setFirma(helper.getFirma());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeFirma(helper.getNomeFirma());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeElo(helper.getNomeElo());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setTipoOrdem(helper.getTipoOrdem());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuantidadeMaxima(helper.getQuantidadeMaxima());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeLocalidadeInicial(helper.getNomeLocalidadeInicial());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeLocalidadeFinal(helper.getNomeLocalidadeFinal());
		
		// Foi Solicitado o Codigo em vez do Nome
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeSetorComercialInicial(helper.getCodigoSetorComercialInicial());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNomeSetorComercialFinal(helper.getCodigoSetorComercialFinal());
		
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuadraInicial(helper.getQuadraInicial());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuadraFinal(helper.getQuadraFinal());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setPerfilImovelDescricao(helper.getDescricaoPerfilImovel());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setCategoriaDescricao(helper.getDescricaoCategoria());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setSubCategoriaDescricao(helper.getDescricaoSubcategoria());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuantidadeEconomia(helper.getQuantidadeEconomia());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setQuantidadeDocumentos(helper.getQuantidadeDocumentos());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setNumeroMoradores(helper.getNumeroMoradores());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setAreaConstruida(helper.getAreaConstruida());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setImovelCondominio(helper.getImovelCondominio());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMediaImovel(helper.getMediaImovel());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setConsumoEconomia(helper.getConsumoEconomia());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setTipoMedicaoDescricao(helper.getDescricaoTipoMedicao());
		//relatorioEmitirOrdemServicoSeletivaSugestaoBean.setCapacidadeDescricao(helper.getDescricaoCapacidade());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMarcaDescricao(helper.getDescricaoMarcaHidrometro());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setLocalInstalacaoDescricao(helper.getDescricaoLocalInstalacaoHidrometro());
		
		if (helper.getMesAnoInstalacaoInicialHidrometro() != null && !helper.getMesAnoInstalacaoInicialHidrometro().equals("")) {
			
			String mesAnoInstalacaoInicialHidrometro = helper.getMesAnoInstalacaoInicialHidrometro().substring(4, 6)
			+ "/" + helper.getMesAnoInstalacaoInicialHidrometro().substring(0, 4);
			
			helper.setMesAnoInstalacaoInicialHidrometro(mesAnoInstalacaoInicialHidrometro);
		}
		
		if (helper.getMesAnoInstalacaoFinalHidrometro() != null && !helper.getMesAnoInstalacaoFinalHidrometro().equals("")) {
			
			String mesAnoInstalacaoFinalHidrometro = helper.getMesAnoInstalacaoFinalHidrometro().substring(4, 6)
			+ "/" + helper.getMesAnoInstalacaoFinalHidrometro().substring(0, 4);
			
			helper.setMesAnoInstalacaoFinalHidrometro(mesAnoInstalacaoFinalHidrometro);
		}
		
		//relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMesAnoInstalacao(helper.getMesAnoInstalacaoHidrometro());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMesAnoInstalacaoInicial(helper.getMesAnoInstalacaoInicialHidrometro());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setMesAnoInstalacaoFinal(helper.getMesAnoInstalacaoFinalHidrometro());
		
		String anormalidadeHidrometro = new String();
	
		if (helper.getAnormalidadeHidrometro() != null){
			for ( int i = 0; i < helper.getAnormalidadeHidrometro().length; i++ ){
				
				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				
				filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
						FiltroLeituraAnormalidade.ID, helper.getAnormalidadeHidrometro()[i]));
				
				Collection collectionHidrometroAnormalidade = fachada.pesquisar(filtroLeituraAnormalidade,
						LeituraAnormalidade.class.getName());
				
				LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) Util.retonarObjetoDeColecao(collectionHidrometroAnormalidade);
				
				anormalidadeHidrometro = anormalidadeHidrometro + leituraAnormalidade.getDescricao()+"\n";
				
			}
		}
		//String situacaoLigacaoAguaDescricao = "--";
		String situacaoLigacaoAguaDescricao = new String();
		if (helper.getSituacaoLigacaoAgua()!= null){
			for ( int i = 0; i < helper.getSituacaoLigacaoAgua().length; i++ ){
				
				FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
				filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
						FiltroLigacaoAguaSituacao.ID, helper.getSituacaoLigacaoAgua()[i]));
				
				Collection colecaoLigacaoAguaSituacao = Fachada.getInstancia()
							.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
				
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
				
				situacaoLigacaoAguaDescricao = situacaoLigacaoAguaDescricao + ligacaoAguaSituacao.getDescricao()+"\n";
				
			}
		}
		
		String capacidadeDescricao = new String();
		
		if (helper.getCapacidadeHidrometro() != null){
			for ( int i = 0; i < helper.getCapacidadeHidrometro().length; i++ ){
				
				FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
				
				filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
						FiltroHidrometroCapacidade.ID, helper.getCapacidadeHidrometro()[i]));
				
				Collection collectionHidrometroCapacidade = fachada.pesquisar(filtroHidrometroCapacidade,
						HidrometroCapacidade.class.getName());
				
				HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) Util.retonarObjetoDeColecao(collectionHidrometroCapacidade);
				
				capacidadeDescricao = capacidadeDescricao + hidrometroCapacidade.getDescricao()+"\n";
				
			}
		}
		
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setSituacaoLigacaoAguaDescricao(helper.getSituacaoLigacaoAguaDescricao());
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setCapacidadeDescricao(capacidadeDescricao);
		relatorioEmitirOrdemServicoSeletivaSugestaoBean.setAnormalidadeHidrometro(anormalidadeHidrometro);
		
		colecaoBean.add(relatorioEmitirOrdemServicoSeletivaSugestaoBean);
		
		}
		
		return colecaoBean;
	}
	
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		retorno = Fachada
				.getInstancia()
				.filtrarImovelEmissaoOrdensSeletivasCount(gerarObjetoHelper());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioEmitirOrdemServicoSeletivaSugestao", this);
	}
}
