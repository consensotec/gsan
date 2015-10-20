/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.bean.FiltroRelatorioHidrometroFaixaIdadeHelper;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
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
 * [UC1682] - Relatório Sintético de Hidrômetros por Faixa de Idade
 * @author Vivianne Sousa
 * @date 10/06/2015
 */
public class RelatorioHidrometroFaixaIdadeSintetico extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioHidrometroFaixaIdadeSintetico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_HIDROMETRO_FAIXA_IDADE_SINTETICO);
	}

	public Object executar() throws TarefaException {

		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		byte[] retorno = null;
		Fachada fachada = Fachada.getInstancia();
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		FiltroRelatorioHidrometroFaixaIdadeHelper helper = (FiltroRelatorioHidrometroFaixaIdadeHelper) getParametro("hidrometroFaixaIdadeHelper");

		Collection<RelatorioHidrometroFaixaIdadeSinteticoBean> colecaoRelatorioBean = fachada.retornarDadosRelatorioHidrometroFaixaIdadeSintetico(helper);			
		Map parametros = new HashMap();
		montarParametrosFiltro(fachada, helper, parametros);
		parametros.put("numeroRelatorio", "R1682");
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
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
		
		//Se não existir dados para ser exibir, exibe uma relatório em branco.
		if(Util.isVazioOrNulo(colecaoRelatorioBean)){
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			colecaoRelatorioBean = new ArrayList<RelatorioHidrometroFaixaIdadeSinteticoBean>();				
			colecaoRelatorioBean.add(new RelatorioHidrometroFaixaIdadeSinteticoBean());
			RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioHidrometroFaixaIdadeSinteticoBean>) colecaoRelatorioBean);
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,	parametros, ds, tipoFormatoRelatorio);
		}else{
			RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioHidrometroFaixaIdadeSinteticoBean>) colecaoRelatorioBean);
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_HIDROMETRO_FAIXA_IDADE_SINTETICO, parametros, ds, tipoFormatoRelatorio);
		}
		
		try {
			persistirRelatorioConcluido(retorno,
				Relatorio.RELATORIO_HIDROMETRO_FAIXA_IDADE_SINTETICO, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		return retorno;
	}

	private void montarParametrosFiltro(Fachada fachada, FiltroRelatorioHidrometroFaixaIdadeHelper helper, Map parametros) {
		String gerenciaRegional = "";
		String unidadeNegocio = "";
		String localidade = "";
		String setorComercial = "";
		String rota = "";
		String quadra = "";
		String perfilImovel = "";
		String categoria = "";
		String subcategoria = "";
		String marca = "";
		String capacidade = "";
		String anormalidade = "";
		String leituraMinima = "";
		String qtdeEconomias = "";
		String consumoMes = "";
		String consumoMedio = "";
		int qtdeParamentros = 3;

		if(helper.getIdGerenciaRegional() != null){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, helper.getIdGerenciaRegional()));
			Collection<GerenciaRegional> colecaoGerenciaRegional  = fachada.pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());
			GerenciaRegional gerencia = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
			gerenciaRegional = gerencia.getId() + " - " + gerencia.getNome();
		}
		if(helper.getIdUnidadeNegocio() != null){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, helper.getIdUnidadeNegocio()));
			Collection<UnidadeNegocio> colecaoUnidadeNegocio  = fachada.pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());
			UnidadeNegocio unidade = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
			unidadeNegocio = unidade.getId() + " - " + unidade.getNome();
		}
		if(helper.getIdLocalidade() != null){
			FiltroLocalidade filtroLocalidadeInicial = new FiltroLocalidade();
			filtroLocalidadeInicial.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, helper.getIdLocalidade()));
			Collection<Localidade> colecaoLocalidadeInicial  = fachada.pesquisar(filtroLocalidadeInicial,Localidade.class.getName());
			Localidade loca = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeInicial);
			localidade = loca.getId() + " - " + loca.getDescricao();
		}
		if(helper.getCodigoSetorComercial() != null){
			FiltroSetorComercial filtroSetorComercialInicial = new FiltroSetorComercial();
			filtroSetorComercialInicial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, helper.getIdLocalidade()));
			filtroSetorComercialInicial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, helper.getCodigoSetorComercial()));
			Collection<SetorComercial> colecaoSetorComercialInicial  = fachada.pesquisar(filtroSetorComercialInicial,SetorComercial.class.getName());
			SetorComercial setor = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialInicial);
			setorComercial = setor.getCodigo() + " - " + setor.getDescricao();
		}
		if(helper.getNumeroQuadra() != null){
			quadra = helper.getNumeroQuadra().toString();
		}
		if(helper.getIdRota() != null){
			rota = helper.getIdRota().toString();
		}
		if(helper.getIdPerfilImovel() != null){
            int contador = 0;
            for (Integer idImovelPerfil : helper.getIdPerfilImovel()) {                 
                FiltroImovelPerfil filtro = new FiltroImovelPerfil();
                filtro.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, idImovelPerfil));
                ImovelPerfil perfil = (ImovelPerfil)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, ImovelPerfil.class.getName()));
                if(contador == 0){
                	perfilImovel = perfil.getDescricao();
                }else if(contador <= qtdeParamentros){
                    perfilImovel = perfilImovel + "," + perfil.getDescricao();
                }else{ 
                	perfilImovel = perfilImovel + " e outros.";
                    break;
                }
                contador++;
            }    
		}
		
		if(helper.getQtdeEconomiasInicial() != null && helper.getQtdeEconomiasFinal() != null){
			qtdeEconomias = helper.getQtdeEconomiasInicial() + " a " + helper.getQtdeEconomiasFinal() ;
		}else if(helper.getQtdeEconomiasInicial() != null && helper.getQtdeEconomiasFinal() == null){
			qtdeEconomias = helper.getQtdeEconomiasInicial() + " a ";
		}else if(helper.getQtdeEconomiasInicial() == null && helper.getQtdeEconomiasFinal() != null){
			qtdeEconomias = " a " + helper.getQtdeEconomiasFinal() ;
		}	
		if(helper.getIdCapacidadeHidrometro() != null){
			int contador = 0;
            for (Integer idCapacidadeHidrometro: helper.getIdCapacidadeHidrometro()) {                 
                FiltroHidrometroCapacidade filtro = new FiltroHidrometroCapacidade();
                filtro.adicionarParametro(new ParametroSimples(FiltroHidrometroCapacidade.ID, idCapacidadeHidrometro));
                HidrometroCapacidade capacidadeHid = (HidrometroCapacidade)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, HidrometroCapacidade.class.getName()));
                if(contador == 0){
                	capacidade = capacidadeHid.getDescricao();
                }else if(contador <= qtdeParamentros){
                	capacidade = capacidade + "," + capacidadeHid.getDescricao();
                }else{ 
                	capacidade = capacidade + " e outros.";
                    break;
                }
                contador++;
            }    
		}
		if(helper.getIdMarcaHidrometro() != null){
			int contador = 0;
            for (Integer idMarcaHidrometro: helper.getIdMarcaHidrometro()) {                 
                FiltroHidrometroMarca filtro = new FiltroHidrometroMarca();
                filtro.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.ID, idMarcaHidrometro));
                HidrometroMarca marcaHid = (HidrometroMarca)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, HidrometroMarca.class.getName()));
                if(contador == 0){
                	marca = marcaHid.getDescricao();
                }else if(contador <= qtdeParamentros){
                	marca = marca + "," + marcaHid.getDescricao();
                }else{ 
                	marca = marca + " e outros.";
                    break;
                }
                contador++;
            } 
		}
		if(helper.getIdAnormalidade() != null){
			int contador = 0;
            for (Integer idAnormalidade: helper.getIdAnormalidade()) {                 
                FiltroLeituraAnormalidade filtro = new FiltroLeituraAnormalidade();
                filtro.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.ID, idAnormalidade));
                LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, LeituraAnormalidade.class.getName()));
                if(contador == 0){
                	anormalidade = leituraAnormalidade.getDescricao();
                }else if(contador == 1){
                	anormalidade = anormalidade + "," + leituraAnormalidade.getDescricao();
                }else{ 
                	anormalidade = anormalidade + " e outros.";
                    break;
                }
                contador++;
            } 
		}
		if(helper.getLeituraMinima() != null){
			leituraMinima = helper.getLeituraMinima().toString();
		}
		if(helper.getFaixaConsumoMesInicial() != null && helper.getFaixaConsumoMesFinal() != null){
			consumoMes = helper.getFaixaConsumoMesInicial() + " a " + helper.getFaixaConsumoMesFinal();
		}else if(helper.getFaixaConsumoMesInicial() != null && helper.getFaixaConsumoMesFinal() == null){
			consumoMes = helper.getFaixaConsumoMesInicial() + " a ";
		}else if(helper.getFaixaConsumoMesInicial() == null && helper.getFaixaConsumoMesFinal() != null){
			consumoMes = " a " + helper.getFaixaConsumoMesFinal();
		}	
		if(helper.getFaixaConsumoMedioInicial() != null && helper.getFaixaConsumoMedioFinal() != null){
			consumoMedio = helper.getFaixaConsumoMedioInicial() + " a " + helper.getFaixaConsumoMedioFinal();
		}else if(helper.getFaixaConsumoMedioInicial() != null && helper.getFaixaConsumoMedioFinal() == null){
			consumoMedio = helper.getFaixaConsumoMedioInicial() + " a ";
		}else if(helper.getFaixaConsumoMedioInicial() == null && helper.getFaixaConsumoMedioFinal() != null){
			consumoMedio = " a " + helper.getFaixaConsumoMedioFinal();
		}	
		if(helper.getIdCategoria() != null){
			int contador = 0;
            for (Integer idCategoria: helper.getIdCategoria()) {                 
                FiltroCategoria filtro = new FiltroCategoria();
                filtro.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO, idCategoria));
                Categoria categ = (Categoria)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, Categoria.class.getName()));
                if(contador == 0){
                	categoria = categ.getDescricao();
                }else if(contador <= qtdeParamentros){
                	categoria = categoria + "," + categ.getDescricao();
                }else{ 
                	categoria = categoria + " e outros.";
                    break;
                }
                contador++;
            } 
		}
		if(helper.getIdSubcategoria() != null){
			int contador = 0;
            for (Integer idSubCategoria: helper.getIdSubcategoria()) {                 
                FiltroSubCategoria filtro = new FiltroSubCategoria();
                filtro.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, idSubCategoria));
                Subcategoria subcat = (Subcategoria)Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, Subcategoria.class.getName()));
                if(contador == 0){
                	subcategoria = subcat.getDescricao();
//                }else if(contador == 1){
//                	subcategoria = subcategoria + "," + subcat.getDescricao();
                }else{ 
                	subcategoria = subcategoria + " e outros.";
                    break;
                }
                contador++;
            } 
		}
		
		String opcaoTotalizacao = "";
		if(helper.getOpcaoTotalizacao().equals("estado")){
			opcaoTotalizacao = "ESTADO";
		}else if(helper.getOpcaoTotalizacao().equals("estadoGerencia")){
			opcaoTotalizacao = "ESTADO POR GERÊNCIA REGIONAL";
	    }else if(helper.getOpcaoTotalizacao().equals("gerenciaRegional")){
	    	opcaoTotalizacao = "GERÊNCIA REGIONAL";
		}else if(helper.getOpcaoTotalizacao().equals("estadoUnidadeNegocio")) {
			opcaoTotalizacao = "ESTADO POR UNIDADE DE NEGÓCIO";
	    }else if(helper.getOpcaoTotalizacao().equals("unidadeNegocio")){				
	    	opcaoTotalizacao = "UNIDADE NEGÓCIO";
		}else if(helper.getOpcaoTotalizacao().equals("estadoLocalidade")){
			opcaoTotalizacao = "ESTADO POR LOCALIDADE";
	    }else if(helper.getOpcaoTotalizacao().equals("localidade")){
	    	opcaoTotalizacao = "LOCALIDADE";
		}
		
		parametros.put("gerenciaRegional",gerenciaRegional);
		parametros.put("unidadeNegocio",unidadeNegocio);
		parametros.put("localidade",localidade);
		parametros.put("setorComercial",setorComercial);
		parametros.put("rota",rota);
		parametros.put("quadra",quadra);
		parametros.put("perfilImovel",perfilImovel);
		parametros.put("categoria",categoria);
		parametros.put("subcategoria",subcategoria);
		parametros.put("marca",marca);
		parametros.put("capacidade",capacidade);
		parametros.put("anormalidade",anormalidade);
		parametros.put("leituraMinima",leituraMinima);
		parametros.put("qtdeEconomias",qtdeEconomias);
		parametros.put("consumoMes",consumoMes);
		parametros.put("consumoMedio",consumoMedio);
		parametros.put("opcaoTotalizacao",opcaoTotalizacao);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 10;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioHidrometroFaixaIdadeSintetico", this);

	}
}