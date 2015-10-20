/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.relatorio.micromedicao;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.bean.FiltroRelatorioHidrometroFaixaIdadeHelper;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeLeitura;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * [UC1683] - Relatório Analítico de Hidrômetros por Faixa de Idade
 * @author Cesar Medeiros
 * @date 10/06/2015
 */

public class RelatorioHidrometroPorFaixaIdadeAnalitico extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioHidrometroPorFaixaIdadeAnalitico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_HIDROMETRO_POR_FAIXA_IDADE_ANALITICO);
	}
		
	public RelatorioHidrometroPorFaixaIdadeAnalitico() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {
		
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		int	tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");		
		FiltroRelatorioHidrometroFaixaIdadeHelper helper =  (FiltroRelatorioHidrometroFaixaIdadeHelper)getParametro("hidrometroFaixaIdadeHelper");
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		Collection<RelatorioHidrometroPorFaixaIdadeAnaliticoBean> colecaoBean = new ArrayList<RelatorioHidrometroPorFaixaIdadeAnaliticoBean>();

		try {
			colecaoBean = Fachada.getInstancia().obterDadosRelatorioHidrometroPorFaixaIdadeAnalitico(helper);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao obter dados do relatório", e);
		}	

		relatorioBeans = (List) colecaoBean;
		
		// Parâmetros do relatório
		Map parametros = montarParametros(helper);		
	
		//Se não existir dados para ser exibir, exibe uma relatório em branco.
		if(Util.isVazioOrNulo(colecaoBean)){
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			colecaoBean = new ArrayList<RelatorioHidrometroPorFaixaIdadeAnaliticoBean>();				
			colecaoBean.add(new RelatorioHidrometroPorFaixaIdadeAnaliticoBean());
			RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioHidrometroPorFaixaIdadeAnaliticoBean>) colecaoBean);
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,	parametros, ds, tipoFormatoRelatorio);
		}else{
			RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioHidrometroPorFaixaIdadeAnaliticoBean>) colecaoBean);
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_HIDROMETRO_POR_FAIXA_IDADE_ANALITICO, parametros, ds, tipoFormatoRelatorio);
		}
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
	
		try {
			persistirRelatorioConcluido(retorno,
				Relatorio.RELATORIO_HIDROMETRO_FAIXA_IDADE_ANALITICO, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		
		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 10;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioHidrometroPorFaixaIdadeAnalitico", this);
	}
	
	public Map montarParametros(FiltroRelatorioHidrometroFaixaIdadeHelper helper){
		
		Map parametros = new HashMap();
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia()
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1683");	
		parametros.put("nomeEmpresa", sistemaParametro.getNomeAbreviadoEmpresa());
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}	
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		Usuario usuario = this.getUsuario();
		String nomeUsuario = usuario.getNomeUsuario();
		parametros.put("nomeUsuario", nomeUsuario);
		parametros.put("anoMes", Util.formatarAnoMesParaMesAno(helper.getAnoMes().intValue()));
		
		
		if(helper.getIdLocalidade() != null){
			FiltroLocalidade filtro = new FiltroLocalidade();
			filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, helper.getIdLocalidade().toString()));
			Localidade localidade = (Localidade)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, Localidade.class.getName()));
			parametros.put("localidade", localidade.getDescricao());
		}else{
			parametros.put("localidade", "");
		}
		
		
		if(helper.getCodigoSetorComercial() != null){			
			FiltroSetorComercial filtro = new FiltroSetorComercial();
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, helper.getCodigoSetorComercial()));
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, helper.getIdLocalidade()));
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, SetorComercial.class.getName()));
			parametros.put("setorComercial", setorComercial.getDescricao());
		}else{
			parametros.put("setorComercial", "");
		}
		
	
	    if(helper.getIdCapacidadeHidrometro() != null){
	    	String capacidade = "";
	    	int contador = 0;
	    	for (Integer idCapacidade : helper.getIdCapacidadeHidrometro()) {				 
				 FiltroHidrometroCapacidade filtro = new FiltroHidrometroCapacidade();
				 filtro.adicionarParametro(new ParametroSimples(FiltroHidrometroCapacidade.ID, idCapacidade));
				 HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, HidrometroCapacidade.class.getName()));
				 
				 if(contador == 0){
					 capacidade = hidrometroCapacidade.getDescricao();
				 }else if(contador <= 2){
					 capacidade = capacidade+ ", " + hidrometroCapacidade.getDescricao();
				 }else if(contador > 2){
					 capacidade = capacidade + " e outras.";
	    			 break;
	    		 }
				 contador++;
			}		    	
	    	parametros.put("capacidade", capacidade);
	    	
	    }else{
	    	parametros.put("capacidade", "");
	    }
		
	    if(helper.getIdMarcaHidrometro() != null){
	    	String marca = "";
	    	int contador = 0;
	    	for (Integer idMarca : helper.getIdMarcaHidrometro()) {				 
				 FiltroHidrometroMarca filtro = new FiltroHidrometroMarca();
				 filtro.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.ID, idMarca));
				 HidrometroMarca hidrometroMarca = (HidrometroMarca)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, HidrometroMarca.class.getName()));
				 
				 if(contador == 0){
					 marca = hidrometroMarca.getDescricao();
				 }else if(contador <= 3){
					 marca = marca+ ", " + hidrometroMarca.getDescricao();
				 }else if(contador > 3){
					 marca = marca + " e outras.";
	    			 break;
	    		 }
				 contador++;
			}		    	
	    	parametros.put("marca", marca);
	    	
	    }else{
	    	parametros.put("marca", "");
	    }	    
	    
	    parametros.put("opcaoTotalizacao", "Localidade");	    
		parametros.put("mesAno", Util.formatarAnoMesParaMesAno(helper.getAnoMes()));
		
		if(helper.getIdPerfilImovel() != null){
			String perfilImovel = "";
			int contador = 0;
			for (Integer idPerfilImovel : helper.getIdPerfilImovel()) {				 
				FiltroImovelPerfil filtro = new FiltroImovelPerfil();
				filtro.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, idPerfilImovel));
				ImovelPerfil imovelPerfil = (ImovelPerfil)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, ImovelPerfil.class.getName()));

				if(contador == 0){
					perfilImovel = imovelPerfil.getDescricao();
				}else if(contador <= 3){
					perfilImovel = perfilImovel+ ", " + imovelPerfil.getDescricao();
				}else if(contador > 3){
					perfilImovel = perfilImovel + " e outros.";
					break;
				}
				contador++;
			}		    	
			parametros.put("perfilImovel", perfilImovel);

		}else{
			parametros.put("perfilImovel", "");
		}
	
		if(helper.getIdCategoria() != null){
			String categoriaParametro = "";
			int contador = 0;
			for (Integer idCategoria : helper.getIdCategoria()) {				 
				FiltroCategoria filtro = new FiltroCategoria();
				filtro.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO, idCategoria));
				Categoria categoria = (Categoria)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, Categoria.class.getName()));

				if(contador == 0){
					categoriaParametro = categoria.getDescricao();
				}else if(contador <= 1){
					categoriaParametro = categoriaParametro + ", " + categoria.getDescricao();
				}else if(contador > 3){
					categoriaParametro = categoriaParametro + " e outras.";
					break;
				}
				
				contador++;
			}		    	
			parametros.put("categoria", categoriaParametro);

		}else{
			parametros.put("categoria", "");
		}		
		
		if(helper.getIdSubcategoria() != null){
			String subcategoriaParametro = "";
			int contador = 0;
			for (Integer idSubCategoria : helper.getIdSubcategoria()) {				 
				FiltroSubCategoria filtro = new FiltroSubCategoria();
				filtro.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, idSubCategoria));
				Subcategoria subcategoria = (Subcategoria)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, Subcategoria.class.getName()));

				if(contador == 0){
					subcategoriaParametro = subcategoria.getDescricao();
				}else if(contador <= 2){
					subcategoriaParametro = subcategoriaParametro + " e outras.";
					break;
				}
				contador++;
			}		    	
			parametros.put("subcategoria", subcategoriaParametro);

		}else{
			parametros.put("subcategoria", "");
		}
	
		if(helper.getIdAnormalidade() != null){
			String anormalidade = "";
			int contador = 0;
			for (Integer idAnormalidade: helper.getIdAnormalidade()) {				 
				FiltroLeituraAnormalidade filtro = new FiltroLeituraAnormalidade();
				filtro.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidadeLeitura.ID, idAnormalidade));
				LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, LeituraAnormalidade.class.getName()));

				if(contador == 0){
					anormalidade = leituraAnormalidade.getDescricao();
				}else if(contador <= 2){
					anormalidade = anormalidade + ", " + leituraAnormalidade.getDescricao();
					break;
				}else if(contador > 3){
					anormalidade = anormalidade + " e outras.";
					break;
				}
				
				contador++;
			}		    	
			parametros.put("anormalidade", anormalidade);

		}else{
			parametros.put("anormalidade", "");
		}
		
		String quantidadeEconomias = "";
		
		if(helper.getQtdeEconomiasInicial() != null){
			quantidadeEconomias = helper.getQtdeEconomiasInicial().toString();
		}else{
			parametros.put("qtdEconomias", quantidadeEconomias);
		}
		
		String qtdeEconomias = ""; 
		if(helper.getQtdeEconomiasInicial() != null && helper.getQtdeEconomiasFinal() != null){
			qtdeEconomias = helper.getQtdeEconomiasInicial() + " a " + helper.getQtdeEconomiasFinal() ;
		}else if(helper.getQtdeEconomiasInicial() != null && helper.getQtdeEconomiasFinal() == null){
			qtdeEconomias = helper.getQtdeEconomiasInicial() + " a ";
		}else if(helper.getQtdeEconomiasInicial() == null && helper.getQtdeEconomiasFinal() != null){
			qtdeEconomias = " a " + helper.getQtdeEconomiasFinal() ;
		}	
		parametros.put("qtdEconomias", qtdeEconomias);		
		
		if(helper.getLeituraMinima() != null){
			parametros.put("leituraMinima", helper.getLeituraMinima().toString());
		}	
	
		String consumoMes = "";
		if(helper.getFaixaConsumoMesInicial() != null && helper.getFaixaConsumoMesFinal() != null){
			consumoMes = helper.getFaixaConsumoMesInicial() + " a " + helper.getFaixaConsumoMesFinal();
		}else if(helper.getFaixaConsumoMesInicial() != null && helper.getFaixaConsumoMesFinal() == null){
			consumoMes = helper.getFaixaConsumoMesInicial() + " a ";
		}else if(helper.getFaixaConsumoMesInicial() == null && helper.getFaixaConsumoMesFinal() != null){
			consumoMes = " a " + helper.getFaixaConsumoMesFinal();
		}	
		parametros.put("consumoMes", consumoMes);
		
		String consumoMedio ="";
		if(helper.getFaixaConsumoMedioInicial() != null && helper.getFaixaConsumoMedioFinal() != null){
			consumoMedio = helper.getFaixaConsumoMedioInicial() + " a " + helper.getFaixaConsumoMedioFinal();
		}else if(helper.getFaixaConsumoMedioInicial() != null && helper.getFaixaConsumoMedioFinal() == null){
			consumoMedio = helper.getFaixaConsumoMedioInicial() + " a ";
		}else if(helper.getFaixaConsumoMedioInicial() == null && helper.getFaixaConsumoMedioFinal() != null){
			consumoMedio = " a " + helper.getFaixaConsumoMedioFinal();
		}	
		parametros.put("consumoMedio", consumoMedio);
		
		if(helper.getNumeroQuadra() != null){
			parametros.put("quadra", helper.getNumeroQuadra().toString());
		}
		if(helper.getIdRota() != null){
			parametros.put("rota", helper.getIdRota().toString());
		}
		
		return parametros;
	}

}