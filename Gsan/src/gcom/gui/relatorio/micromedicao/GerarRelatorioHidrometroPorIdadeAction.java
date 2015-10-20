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
package gcom.gui.relatorio.micromedicao;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.micromedicao.bean.FiltroRelatorioHidrometroFaixaIdadeHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.micromedicao.RelatorioHidrometroFaixaIdadeSintetico;
import gcom.relatorio.micromedicao.RelatorioHidrometroPorFaixaIdadeAnalitico;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1682] - Relatório Sintético de Hidrômetros por Faixa de Idade
 * [UC1683] - Relatório Analítico de Hidrômetros por Faixa de Idade
 * @author Cesar Medeiros
 * @date 10/06/2015
 */
public class GerarRelatorioHidrometroPorIdadeAction extends ExibidorProcessamentoTarefaRelatorio{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		GerarRelatorioHidrometroPorIdadeActionForm form = (GerarRelatorioHidrometroPorIdadeActionForm) actionForm;
		FiltroRelatorioHidrometroFaixaIdadeHelper helper = new FiltroRelatorioHidrometroFaixaIdadeHelper();

		SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();
		Integer referenciaFaturamentoMenos1 = Util.subtrairMesDoAnoMes(sistemaParametro.getAnoMesFaturamento(), 1);
		helper.setAnoMes(referenciaFaturamentoMenos1);
		
		helper.setOpcaoTotalizacao(form.getOpcaoTotalizacao());
		//Opção de relatório
		String opcaoRelatorio = form.getOpcaoRelatorio();
		
		//Gerência Regional
		String 	idGerenciaRegional = form.getGerenciaRegionalId();	
		if(idGerenciaRegional != null && !idGerenciaRegional.equals(ConstantesSistema.INVALIDO_ID.toString())){
			helper.setIdGerenciaRegional(Integer.valueOf(idGerenciaRegional));
		}
		
		//Unidade Negócio
		String idUnidadeNegocio = form.getUnidadeNegocioId();
		if(idUnidadeNegocio != null && !idUnidadeNegocio.equals(ConstantesSistema.INVALIDO_ID.toString())){
			helper.setIdUnidadeNegocio(Integer.valueOf(idUnidadeNegocio));
		}
		
		//Localidade
		String idLocalidade = form.getCodigoLocalidade();
		if(idLocalidade != null && Util.verificarNaoVazio(idLocalidade)){
			if(localidadeValida(idLocalidade)){
				helper.setIdLocalidade(Integer.valueOf(idLocalidade));
			}else{
				throw new ActionServletException("atencao.localidade_nao_valida");
			}
		}
		
		//Setor Comercial
		String codigoSetorComercial = form.getIdSetorComercial();
		if(codigoSetorComercial != null && Util.verificarNaoVazio(codigoSetorComercial)){
			if(idLocalidade != null && !idLocalidade.equals(ConstantesSistema.INVALIDO_ID.toString())){
				if(setorComercialValido(codigoSetorComercial,idLocalidade)){
					helper.setCodigoSetorComercial(Integer.valueOf(codigoSetorComercial));
				}else{
					throw new ActionServletException("atencao.setor_invalido_para_localidade_informada");
				}
			}
		}
		
		//Rota
		String idRota = form.getIdRota();
		if(idRota != null && Util.verificarNaoVazio(idRota)){			
			if(codigoSetorComercial != null && Util.verificarNaoVazio(codigoSetorComercial)){
				if(rotaValida(idRota, codigoSetorComercial)){
					helper.setIdRota(Integer.valueOf(idRota));
				}else{
					throw new ActionServletException("atencao.rota_invalida_para_setor_comercial");
				}
			}
		}
		
		//Quadra
		String idQuadra = form.getIdQuadra();
		if(idQuadra != null && Util.verificarNaoVazio(idQuadra)){
			if(codigoSetorComercial != null && Util.verificarNaoVazio(codigoSetorComercial)){
				if(quadraValida(idQuadra, codigoSetorComercial)){
					helper.setNumeroQuadra(Integer.valueOf(idQuadra));
				}else{
					throw new ActionServletException("atencao.quadra_invalida_para_setor_comercial");
				}
			}
		}
		
		//Opção de totalização
		String opcaoTotalizacao = form.getOpcaoTotalizacao();
		if(opcaoTotalizacao != null && Util.verificarNaoVazio(opcaoTotalizacao)){
			if(opcaoTotalizacao.equals("localidade")){	
				if(idLocalidade != null && Util.verificarNaoVazio(idLocalidade)){					
					if(codigoSetorComercial != null && !Util.verificarNaoVazio(codigoSetorComercial)){
						if(opcaoRelatorio.equals(ConstantesSistema.RELATORIO_ANALITICO)){
							throw new ActionServletException("atencao.campo.informado", null ,"Setor Comercial" );			
						}
					}	
				}else{
					throw new ActionServletException("atencao.campo.informado", null ,"Localidade" );
				}
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null ,"Opção de totalização" );	
		}
		
		//Perfil do imovel
		String[] perfilImovel = form.getImovelPerfil();	
		if(perfilImovel != null && perfilImovel.length > 0){
			Collection<Integer> idPerfilImovel = new ArrayList();
			for(int i = 0; i < perfilImovel.length; i++){
				if(!perfilImovel[i].equals("-1")){
					idPerfilImovel.add(Integer.valueOf(perfilImovel[i]));
				}
			}	
			if(idPerfilImovel != null && !idPerfilImovel.isEmpty()){
				helper.setIdPerfilImovel(idPerfilImovel);
			}
		}
		
		//Categoria
		String[] categoria = form.getCategoria();	
		if(categoria != null && categoria.length > 0){
			Collection<Integer> idCategoria =  new ArrayList();
			for(int i = 0; i < categoria.length; i++){
				if(!categoria[i].equals("-1")){
					idCategoria.add(Integer.valueOf(categoria[i]));
				}
			}	
			if(idCategoria != null && !idCategoria.isEmpty()){
				helper.setIdCategoria(idCategoria);
			}
		}
		
		//SubCategoria
		String[] subCategoria = form.getSubCategoria();	
		if(subCategoria != null && subCategoria.length > 0){
			Collection<Integer> idSubCategoria =  new ArrayList();
			for(int i = 0; i < subCategoria.length; i++){
				if(!subCategoria[i].equals("-1")){
					idSubCategoria.add(Integer.valueOf(subCategoria[i]));
				}
			}
			if(idSubCategoria != null && !idSubCategoria.isEmpty()){
				helper.setIdSubcategoria(idSubCategoria);
			}
		}
		
		//Intervalo de Quantidade de Economias
		String qtdEconomiasInicial = form.getQuantidadeEconomiaInicial();
		String qtdEconomiasFinal = form.getQuantidadeEconomiaFinal();
		if(qtdEconomiasInicial != null && Util.verificarNaoVazio(qtdEconomiasInicial)
		   && qtdEconomiasFinal != null && Util.verificarNaoVazio(qtdEconomiasFinal)){
			
			if (!qtdEconomiasInicial.matches("[0-9]+") && !qtdEconomiasFinal.matches("[0-9]+")) {
				throw new ActionServletException("atencao.numero_inteiro_invalido", null,"Quantidade de Economias");
			}else{
				Integer quantidadeEconomiasInicial = Integer.valueOf(qtdEconomiasInicial);
				Integer quantidadeEconomiasFinal = Integer.valueOf(qtdEconomiasFinal);

				if(quantidadeEconomiasInicial <= quantidadeEconomiasFinal){
					helper.setQtdeEconomiasInicial(quantidadeEconomiasInicial);
					helper.setQtdeEconomiasFinal(quantidadeEconomiasFinal);
				}else{
					throw new ActionServletException("atencao.atencao.intervalo_qtd_economias_invalido");
				}
			}
		}
		
		//Capaciadade
		String[] capacidade = form.getCapacidade();	
		if(capacidade != null && capacidade.length > 0){
			Collection<Integer> idCapacidade =  new ArrayList();
			for(int i = 0; i < capacidade.length; i++){
				if(!capacidade[i].equals("-1")){
					idCapacidade.add(Integer.valueOf(capacidade[i]));
				}
			}	
			if(idCapacidade != null && !idCapacidade.isEmpty()){
				helper.setIdCapacidadeHidrometro(idCapacidade);
			}
		}
		
		//Marca
		String[] marca = form.getMarca();	
		if(marca != null && marca.length > 0){
			Collection<Integer> idMarca =  new ArrayList();
			for(int i = 0; i < marca.length; i++){
				if(!marca[i].equals("-1")){
					idMarca.add(Integer.valueOf(marca[i]));
				}
			}	
			if(idMarca != null && !idMarca.isEmpty()){
				helper.setIdMarcaHidrometro(idMarca);
			}
		}
		
		//Anormalidade
		String[] anormalidade = form.getAnormalidade();	
		if(anormalidade != null && anormalidade.length > 0){
			Collection<Integer> idAnormalidade =  new ArrayList();
			for(int i = 0; i < anormalidade.length; i++){
				if(!anormalidade[i].equals("-1")){
					idAnormalidade.add(Integer.valueOf(anormalidade[i]));
				}
			}	
			if(idAnormalidade != null && !idAnormalidade.isEmpty()){
				helper.setIdAnormalidade(idAnormalidade);
			}
		}
		
		//Leitura Mínima
		String leituraMinima = form.getLeituraMinima();
		if(leituraMinima != null && Util.verificarNaoVazio(leituraMinima)){			
			if (!leituraMinima.matches("[0-9]+")) {
				throw new ActionServletException("atencao.numero_inteiro_invalido", null,"Leitura Mínima");
			}else{
				helper.setLeituraMinima(Integer.valueOf(leituraMinima));	
			}			
		}
		
		//Faixa Consumo Mês
		String qtdFaixaConsumoMesInicial = form.getFaixaConsumoMesInical();
		String qtdFaixaConsumoMesFinal = form.getFaixaConsumoMesFinal();
		if(qtdFaixaConsumoMesInicial != null && Util.verificarNaoVazio(qtdFaixaConsumoMesInicial)
				&& qtdFaixaConsumoMesFinal != null && Util.verificarNaoVazio(qtdFaixaConsumoMesFinal)){

			if (!qtdFaixaConsumoMesInicial.matches("[0-9]+") && !qtdFaixaConsumoMesFinal.matches("[0-9]+")) {
				throw new ActionServletException("atencao.numero_inteiro_invalido", null,"Faixa Consumo Mês");
			}else{
				Integer quantidadeFaixaConsumoMesInicial = Integer.valueOf(qtdFaixaConsumoMesInicial);
				Integer quantidadeFaixaConsumoMesFinal = Integer.valueOf(qtdFaixaConsumoMesFinal);
				
				if(quantidadeFaixaConsumoMesInicial <= quantidadeFaixaConsumoMesFinal){
					helper.setFaixaConsumoMesInicial(quantidadeFaixaConsumoMesInicial);
					helper.setFaixaConsumoMesFinal(quantidadeFaixaConsumoMesFinal);
				}else{
					throw new ActionServletException("atencao.intervalo_faixa_consumo_mes_invalido");
				}
			}			
		}
		
		//Faixa Consumo Medio
		String qtdFaixaConsumoMedioInicial = form.getFaixaConsumoMedioInicial();
		String qtdFaixaConsumoMedioFinal = form.getFaixaConsumoMedioFinal();
		if(qtdFaixaConsumoMedioInicial != null && Util.verificarNaoVazio(qtdFaixaConsumoMedioInicial)
				&& qtdFaixaConsumoMedioFinal != null && Util.verificarNaoVazio(qtdFaixaConsumoMedioFinal)){

			if (!qtdFaixaConsumoMedioInicial.matches("[0-9]+") && !qtdFaixaConsumoMedioFinal.matches("[0-9]+")) {
				throw new ActionServletException("atencao.numero_inteiro_invalido", null,"Faixa Consumo Médio");
			}else{			
				Integer quantidadeFaixaConsumoMedioInicial = Integer.valueOf(qtdFaixaConsumoMedioInicial);
				Integer quantidadeFaixaConsumoMedioFinal = Integer.valueOf(qtdFaixaConsumoMedioFinal);

				if(quantidadeFaixaConsumoMedioInicial <= quantidadeFaixaConsumoMedioFinal){
					helper.setFaixaConsumoMedioInicial(quantidadeFaixaConsumoMedioInicial);
					helper.setFaixaConsumoMedioFinal(quantidadeFaixaConsumoMedioFinal);
				}else{
					throw new ActionServletException("atencao.intervalo_faixa_consumo_medio_invalido");
				}
			}
		}

		//Faixas de dias Vencidos
		Collection colecaoHidrometroFaixas =  (Collection) sessao.getAttribute("colecaoHidrometroFaixaIdade");		
		if(colecaoHidrometroFaixas != null && colecaoHidrometroFaixas.size() > 0){
			helper.setFaixas(colecaoHidrometroFaixas);
		}else{
			throw new ActionServletException("atencao.tabela_hidrometro_faixa_idade_invalido");
		}
		
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		
		if(opcaoRelatorio.equals(ConstantesSistema.RELATORIO_ANALITICO)){
			RelatorioHidrometroPorFaixaIdadeAnalitico relatorio = new RelatorioHidrometroPorFaixaIdadeAnalitico(getUsuarioLogado(httpServletRequest));
			relatorio.addParametro("tipoFormatoRelatorio", tipoRelatorio);
			relatorio.addParametro("hidrometroFaixaIdadeHelper", helper);
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		}else{
			RelatorioHidrometroFaixaIdadeSintetico relatorio = new RelatorioHidrometroFaixaIdadeSintetico(getUsuarioLogado(httpServletRequest));
			relatorio.addParametro("tipoFormatoRelatorio", tipoRelatorio);
			relatorio.addParametro("hidrometroFaixaIdadeHelper", helper);
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		}
		
		return retorno;
	}
	
	public boolean rotaValida(String idRota, String codigoSetor){
		
		boolean retorno = false;
		
		FiltroRota filtro = new FiltroRota();
		filtro.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA,idRota));
		filtro.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO, codigoSetor));
		Rota rota = (Rota) Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtro, Rota.class.getName()));
		if(rota != null){
			retorno = true;
		}
		
		return retorno;
	}
	
	public boolean setorComercialValido(String codigoSetor, String idLocalidade){

		boolean retorno = false;

		FiltroSetorComercial filtro = new FiltroSetorComercial();
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, idLocalidade));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,codigoSetor));		
		SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtro, SetorComercial.class.getName()));

		if(setorComercial != null){
			retorno = true;
		}

		return retorno;
	}
	
	public boolean quadraValida(String numeroQuadra, String codigoSetorComercial){

		boolean retorno = false;

		FiltroQuadra filtro = new FiltroQuadra();
		filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA,numeroQuadra));
		filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, codigoSetorComercial));
		Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtro, Quadra.class.getName()));

		if(quadra != null){
			retorno = true;
		}

		return retorno;
	}	
	
	private boolean localidadeValida(String idLocalidade) {
		
		boolean retorno = false;
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, idLocalidade));
		
		Collection pesquisa = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if (pesquisa != null && !pesquisa.isEmpty()) {
			retorno = true;
		}
		
		return retorno;
	}
}
