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
* Ivan S�rgio da Silva J�nior
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
package gsan.gui.relatorio.cadastro.imovel;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.ClienteTipo;
import gsan.cadastro.cliente.EsferaPoder;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.cliente.FiltroClienteTipo;
import gsan.cadastro.cliente.FiltroEsferaPoder;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.FiltroCategoria;
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cobranca.CobrancaGrupo;
import gsan.cobranca.CobrancaSituacao;
import gsan.cobranca.FiltroCobrancaGrupo;
import gsan.cobranca.FiltroCobrancaSituacao;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasAtrasoHelper;
import gsan.relatorio.cadastro.imovel.RelatorioImoveisFaturasAtrasoAgrupadasCliente;
import gsan.relatorio.cadastro.imovel.RelatorioImoveisFaturasAtrasoAgrupadasLocalizacao;
import gsan.relatorio.cadastro.imovel.RelatorioImoveisFaturasAtrasoDescritasCliente;
import gsan.relatorio.cadastro.imovel.RelatorioImoveisFaturasAtrasoDescritasLocalizacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00725] Gerar Relat�rio de Im�veis por Situa��o da Liga��o de �gua
 * 
 * @author Rafael Pinto
 *
 * @date 28/11/2007
 */

public class GerarRelatorioImoveisFaturasAtrasoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		GerarRelatorioImoveisFaturasAtrasoActionForm form = 
			(GerarRelatorioImoveisFaturasAtrasoActionForm) actionForm;
		
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro = criarFiltro(form);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		TarefaRelatorio relatorio = null;
		
		relatorio = definirTipoRelatorio(form, getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("filtrarRelatorioImoveisFaturasAtrasoHelper", filtro);
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		configurarParametrosRelatorio(relatorio, form);

		return 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);
	}

	/**
	 * Esse m�todo instancia e retorna o tipo de relatorio correto de acordo com os filtros
	 * informados pelo usu�rio.
	 *
	 *@since 02/09/2009
	 *@author Marlon Patrick
	 */
	private TarefaRelatorio definirTipoRelatorio(GerarRelatorioImoveisFaturasAtrasoActionForm form,
			Usuario usuarioLogado) {
		
		boolean isCriterioFiltroLocalidade = form.getCriterioFiltro().equals("1");
		boolean isConsultaAgrupada = form.getTipo().equals("A");

		if(isConsultaAgrupada){
			
			if(isCriterioFiltroLocalidade){
				return
					new RelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(usuarioLogado);				
			}
			
			return new RelatorioImoveisFaturasAtrasoAgrupadasCliente(usuarioLogado);			
		}
		
		if(isCriterioFiltroLocalidade){
			return 
				new RelatorioImoveisFaturasAtrasoDescritasLocalizacao(usuarioLogado);
		}
		
		return new RelatorioImoveisFaturasAtrasoDescritasCliente(usuarioLogado);

	}

	/**
	 * Este m�todo cria e configura o filtro necess�rio a gera��o do relat�rio.
	 * 
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private FiltrarRelatorioImoveisFaturasAtrasoHelper criarFiltro(
			GerarRelatorioImoveisFaturasAtrasoActionForm form) {
		
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro = 
			new FiltrarRelatorioImoveisFaturasAtrasoHelper();

		if (Util.verificarNaoVazio(form.getCriterioFiltro())) {			
			filtro.setCriterioFiltro(new Integer(form.getCriterioFiltro()));
		}

		if (Util.verificarNaoVazio(form.getGerenciaRegional())
				&& !form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			filtro.setGerenciaRegional(new Integer(form.getGerenciaRegional()));
		}
		
		if (Util.verificarNaoVazio(form.getUnidadeNegocio())
				&& !form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			filtro.setUnidadeNegocio(new Integer(form.getUnidadeNegocio()));
		}
			
		if (Util.verificarNaoVazio(form.getLocalidadeInicial())) {
			if(pesquisarLocalidade(form, true)){
				filtro.setLocalidadeInicial(new Integer(form.getLocalidadeInicial()));
			}
		}
		
		if (Util.verificarNaoVazio(form.getSetorComercialInicial())) {
			if(pesquisarSetorComercial(form, true)){
				filtro.setSetorComercialInicial(new Integer(form.getSetorComercialInicial()));
			}
		}
		
		if (Util.verificarNaoVazio(form.getQuadraInicial())) {
			if(pesquisarQuadra(form, true)){
				filtro.setQuadraInicial(new Integer(form.getQuadraInicial()));
			}
		}
		
		if (Util.verificarNaoVazio(form.getRotaInicial())) {
			if(pesquisarRota(form, true)){
				filtro.setRotaInicial(new Short(form.getRotaInicial()));
			}
		}

		if (Util.verificarNaoVazio(form.getSequencialRotaInicial())) {
			filtro.setSequencialRotalInicial(new Integer(form.getSequencialRotaInicial()));
		}

		if (Util.verificarNaoVazio(form.getLocalidadeFinal())) {
			if(pesquisarLocalidade(form, false)){
				filtro.setLocalidadeFinal(new Integer(form.getLocalidadeFinal()));
			}
		}

		if (Util.verificarNaoVazio(form.getSetorComercialFinal())) {
			if(pesquisarSetorComercial(form, false)){
				filtro.setSetorComercialFinal(new Integer(form.getSetorComercialFinal()));
			}
		}
		
		if (Util.verificarNaoVazio(form.getQuadraFinal())) {
			if(pesquisarQuadra(form, false)){
				filtro.setQuadraFinal(new Integer(form.getQuadraFinal()));
			}
		}
		
		if (Util.verificarNaoVazio(form.getRotaFinal())) {
			if(pesquisarRota(form, false)){
				filtro.setRotaFinal(new Short(form.getRotaFinal()));
			}
		}
		
		if (Util.verificarNaoVazio(form.getSequencialRotaFinal())) {
			filtro.setSequencialRotalFinal(new Integer(form.getSequencialRotaFinal()));
		}
		
		if (Util.verificarNaoVazio(form.getCodigoCliente())) {
			if(pesquisarCliente(form, false)){
				filtro.setCliente(new Integer(form.getCodigoCliente()));
			}
		}

		if (Util.verificarNaoVazio(form.getCodigoClienteSuperior())) {
			if(pesquisarCliente(form, true)){
				filtro.setClienteSuperior(new Integer(form.getCodigoClienteSuperior()));
			}
		}

		if (Util.verificarNaoVazio(form.getResponsavel())) {
			filtro.setResponsavel(new Integer(form.getResponsavel()));
		}

		if (Util.verificarNaoVazio(form.getTipoRelacao())
				&& !form.getTipoRelacao().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			filtro.setTipoRelacao(new Integer(form.getTipoRelacao()));
		}
		
		if (Util.verificarNaoVazio(form.getHidrometro()) && !form.getHidrometro().equals("0")){
			if(form.getHidrometro().equals("1")){
				filtro.setHidrometro("S");
			}else if(form.getHidrometro().equals("2")){
				filtro.setHidrometro("N");
			}
		}

		if ( !Util.isVazioOrNulo(form.getSituacaoLigacaoAgua())) {
			
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			String[] array = form.getSituacaoLigacaoAgua();
			
			for(String situacao: array){
				if( !situacao.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecao.add(new Integer(situacao));					
				}
			}
			if(!colecao.isEmpty()){
				filtro.setSituacaoLigacaoAgua(colecao);
			}			
		}
		
		if ( !Util.isVazioOrNulo(form.getCategorias())) {
			
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			String[] array = form.getCategorias();

			for(String categoria: array){
				if( !categoria.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecao.add(new Integer(categoria));					
				}
			}
			if(!colecao.isEmpty()){
				filtro.setCategorias(colecao);
			}
		}
		
		if ( !Util.isVazioOrNulo(form.getPerfilImovel())) {
			
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			String[] array = form.getPerfilImovel();

			for(String perfilImovel: array){
				if( !perfilImovel.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecao.add(new Integer(perfilImovel));					
				}
			}
			if(!colecao.isEmpty()){
				filtro.setPerfisImovel(colecao);
			}
		}
		
		
           if ( !Util.isVazioOrNulo(form.getTiposCliente())) {
			
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			String[] array = form.getTiposCliente();

			for(String tiposCliente: array){
				if( !tiposCliente.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecao.add(new Integer(tiposCliente));					
				}
			}
			if(!colecao.isEmpty()){
				filtro.setTiposCliente(colecao);
			}
		}
		
		
		if (Util.verificarNaoVazio(form.getSituacaoCobranca())
				&& !form.getSituacaoCobranca().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			filtro.setSituacaoCobranca(new Integer(form.getSituacaoCobranca()));
		}
		
		if(Util.verificarNaoVazio(form.getGrupoCobranca())
				&& !form.getGrupoCobranca().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			filtro.setGrupoCobranca(new Integer(form.getGrupoCobranca()));
		}

		if (Util.verificarNaoVazio(form.getQuantidadeFaturasAtrasoInicial())) {
			filtro.setQuantidadeFaturasAtrasoInicial(new Integer(form.getQuantidadeFaturasAtrasoInicial()));
		}
		
		if ( Util.verificarNaoVazio(form.getQuantidadeFaturasAtrasoFinal())) {
			filtro.setQuantidadeFaturasAtrasoFinal(new Integer(form.getQuantidadeFaturasAtrasoFinal()));
		}
		
		if (Util.verificarNaoVazio(form.getValorFaturasAtrasoInicial())) {
			Float valorFloat = Util.formatarMoedaRealparaFloat(form.getValorFaturasAtrasoInicial().replace(",", "."));
			filtro.setValorFaturasAtrasoInicial(valorFloat);
		}
		
		if (Util.verificarNaoVazio(form.getValorFaturasAtrasoFinal())) {
			Float valorFloat = Util.formatarMoedaRealparaFloat(form.getValorFaturasAtrasoFinal().replace(",", "."));
			filtro.setValorFaturasAtrasoFinal(valorFloat);
		}

		if (Util.verificarNaoVazio(form.getReferenciaFaturasAtrasoInicial())) {
			filtro.setReferenciaFaturasAtrasoInicial( Util.formatarMesAnoComBarraParaAnoMes( form.getReferenciaFaturasAtrasoInicial() ) );
		}
		
		if (Util.verificarNaoVazio(form.getReferenciaFaturasAtrasoFinal())) {
			filtro.setReferenciaFaturasAtrasoFinal( Util.formatarMesAnoComBarraParaAnoMes( form.getReferenciaFaturasAtrasoFinal() ) );
		}	
		
		if( !Util.isVazioOrNulo(form.getEsferaPoder())){
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			String[] array = form.getEsferaPoder();

			for(String esferaPoder : array){
				if( !esferaPoder.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecao.add(new Integer(esferaPoder));					
				}
			}
			if(!colecao.isEmpty()){
				filtro.setEsferaPoder(colecao);
			}
		}
		return filtro;
	}
	
	/**
	 * Esse m�todo adiciona os parametros no objeto TarefaRelatorio
	 * para que os mesmos sejam exibidos quando o relat�rio for gerado.
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private void configurarParametrosRelatorio(TarefaRelatorio relatorio, GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		if(form.getGerenciaRegional() != null && !form.getGerenciaRegional().equalsIgnoreCase(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
			relatorio.addParametro("gerRegionalFiltro", form.getGerenciaRegional());
		}
		if(form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equalsIgnoreCase(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
			relatorio.addParametro("uniNegocioFiltro", form.getUnidadeNegocio());
		}
		if(form.getLocalidadeInicial() != null && !form.getLocalidadeInicial().equals("")){
			relatorio.addParametro("localidadeFiltro", form.getLocalidadeInicial() + " a " + form.getLocalidadeFinal());
		}
		if(form.getSetorComercialInicial() != null && !form.getSetorComercialInicial().equals("")){
			relatorio.addParametro("setorFiltro", form.getSetorComercialInicial() + " a " + form.getSetorComercialFinal());
		}
		if(form.getRotaInicial() != null && !form.getRotaInicial().equals("")){
			relatorio.addParametro("rotaFiltro", form.getRotaInicial() + " a " + form.getRotaFinal());
		}
		
		if(form.getQuadraInicial() != null && !form.getQuadraFinal().equals("")){
			relatorio.addParametro("quadraFiltro", form.getQuadraInicial() + " a " + form.getQuadraFinal());
		}
		
		if(form.getSequencialRotaInicial() != null && !form.getSequencialRotaInicial().equals("")){
			relatorio.addParametro("seqRotaFiltro", form.getSequencialRotaInicial() + " a " + form.getSequencialRotaFinal());
		}
		if(form.getValorFaturasAtrasoInicial() != null && !form.getValorFaturasAtrasoInicial().equals("")){
			relatorio.addParametro("valorFaturasFiltro", form.getValorFaturasAtrasoInicial() + " a " + form.getValorFaturasAtrasoFinal());
		}
		if(form.getQuantidadeFaturasAtrasoInicial() != null && !form.getQuantidadeFaturasAtrasoInicial().equals("")){
			relatorio.addParametro("qtdFaturasFiltro", form.getQuantidadeFaturasAtrasoInicial() + " a " + form.getQuantidadeFaturasAtrasoFinal());
		}
		if(form.getReferenciaFaturasAtrasoInicial() != null && !form.getReferenciaFaturasAtrasoInicial().equals("")){
			relatorio.addParametro("refFaturasFiltro", form.getReferenciaFaturasAtrasoInicial() + " a " + form.getReferenciaFaturasAtrasoFinal());
		}

		if (Util.verificarNaoVazio(form.getCodigoClienteSuperior())) {
			if(Util.verificarNaoVazio(form.getNomeClienteSuperior())){
				relatorio.addParametro("clienteSuperiorFiltro",form.getCodigoClienteSuperior() + " - " + form.getNomeClienteSuperior());
			}else{
				Cliente cliente = Fachada.getInstancia().pesquisarClienteDigitado(new Integer(form.getCodigoClienteSuperior()));
				relatorio.addParametro("clienteSuperiorFiltro",cliente.getId() + " - " + cliente.getNome());
			}
		}
		
		if (Util.verificarNaoVazio(form.getCodigoCliente())) {						
			
			if(Util.verificarNaoVazio(form.getNomeCliente())){
				relatorio.addParametro("clienteFiltro",form.getCodigoCliente() + " - " + form.getNomeCliente());
			}else{
				Cliente cliente = Fachada.getInstancia().consultarCliente(new Integer(form.getCodigoCliente()));
				relatorio.addParametro("clienteFiltro",cliente.getId() + " - " + cliente.getNome());
			}

			
			if (Util.verificarNaoVazio(form.getTipoRelacao())
					&& !form.getTipoRelacao().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
				
				if(form.getTipoRelacao().equals(ClienteRelacaoTipo.USUARIO.toString())){
					relatorio.addParametro("tipoRelacaoFiltro",ClienteRelacaoTipo.USUARIO.toString());
				
				}else if(form.getTipoRelacao().equals(ClienteRelacaoTipo.PROPRIETARIO.toString())){
					relatorio.addParametro("tipoRelacaoFiltro",ClienteRelacaoTipo.PROPRIETARIO.toString());
					
				}else if(form.getTipoRelacao().equals(ClienteRelacaoTipo.RESPONSAVEL.toString())){
					relatorio.addParametro("tipoRelacaoFiltro",ClienteRelacaoTipo.RESPONSAVEL.toString());
				}
			}

			if (Util.verificarNaoVazio(form.getResponsavel())) {
				if(form.getResponsavel().equals("0")){
					relatorio.addParametro("responsavelFiltro","Indicado na Conta");				
				
				}else if(form.getResponsavel().equals("1")){
					relatorio.addParametro("responsavelFiltro","Atual do Im�vel");				
				
				}else if(form.getResponsavel().equals("2")){
					relatorio.addParametro("responsavelFiltro","Todos");				
				}
			}

		}
		
		if(Util.verificarNaoVazio(form.getHidrometro())) {
			if(form.getHidrometro().equals("0")){
				relatorio.addParametro("hidrometro", "TODOS");
			}else if(form.getHidrometro().equals("1")){
				relatorio.addParametro("hidrometro", "Com Hidr�metro");
			}else if(form.getHidrometro().equals("2")){
				relatorio.addParametro("hidrometro", "Sem Hidr�metro");
			}
		}

		if (Util.verificarNaoVazio(form.getSituacaoCobranca())
				&& !form.getSituacaoCobranca().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			FiltroCobrancaSituacao filtroSituacaoCobranca = new FiltroCobrancaSituacao();
			filtroSituacaoCobranca.adicionarParametro(
					new ParametroSimples(FiltroCobrancaSituacao.ID,form.getSituacaoCobranca()));

			Collection<CobrancaSituacao> colecaoSituacoesCobranca = 
				this.getFachada().pesquisar(filtroSituacaoCobranca, CobrancaSituacao.class.getName());

			relatorio.addParametro("situacaoCobrancaFiltro",colecaoSituacoesCobranca.iterator().next().getDescricao());
		}
		
		if (Util.verificarNaoVazio(form.getGrupoCobranca()) 
				&& !form.getGrupoCobranca().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
			filtroCobrancaGrupo.adicionarParametro(
					new ParametroSimples(FiltroCobrancaGrupo.ID,form.getGrupoCobranca()));

			Collection<CobrancaGrupo> colecaoGruposCobranca = 
				this.getFachada().pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());

			relatorio.addParametro("grupoCobrancaFiltro",colecaoGruposCobranca.iterator().next().getDescricao());
		}

		if( !Util.isVazioOrNulo(form.getEsferaPoder())){
			String esfera = "";
			String[] array = form.getEsferaPoder();
			
			for(String esferaTemp : array){
				if (!esferaTemp.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
					FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
					filtroEsferaPoder.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.ID, esferaTemp));
					Collection<EsferaPoder> colecao = Fachada.getInstancia().pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
					esfera += colecao.iterator().next().getDescricao() + " ";
				}				
			}
			relatorio.addParametro("esfPoderFiltro", esfera);
		}
		
		if( !Util.isVazioOrNulo(form.getSituacaoLigacaoAgua())){
			String ligacaoAgua = "";
			String[] array = form.getSituacaoLigacaoAgua();
			
			for(String ligacaoAguaTemp : array){
				if (!ligacaoAguaTemp.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
					FiltroLigacaoAguaSituacao filtro = new FiltroLigacaoAguaSituacao();
					filtro.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, ligacaoAguaTemp));
					Collection<LigacaoAguaSituacao> colecao = Fachada.getInstancia().pesquisar(filtro, LigacaoAguaSituacao.class.getName());
					ligacaoAgua += colecao.iterator().next().getDescricaoAbreviado() + " ";
				}				
			}
			relatorio.addParametro("LigAguaFiltro", ligacaoAgua);
		}
		
		if( !Util.isVazioOrNulo(form.getCategorias())){
			String categoria = "";
			String[] array = form.getCategorias();
			
			for(String categoriaTemp:array){
				if (!categoriaTemp.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
					FiltroCategoria filtro = new FiltroCategoria();
					filtro.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO, categoriaTemp));
					Collection<Categoria> colecao = Fachada.getInstancia().pesquisar(filtro, Categoria.class.getName());
					categoria += colecao.iterator().next().getDescricaoAbreviada() + " ";
				}				
			}
			relatorio.addParametro("categoriaFiltro", categoria );
		}
		
		if( !Util.isVazioOrNulo(form.getPerfilImovel())){
			String perfilImovel = "";
			String[] array = form.getPerfilImovel();
			
			for(String perfilImovelTemp : array){
				if (!perfilImovelTemp.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
					FiltroImovelPerfil filtro = new FiltroImovelPerfil();
					filtro.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, perfilImovelTemp));
					Collection<ImovelPerfil> colecao = Fachada.getInstancia().pesquisar(filtro, ImovelPerfil.class.getName());
					perfilImovel += colecao.iterator().next().getId() + " ";
				}				
			}
			relatorio.addParametro("perfisImovelFiltro", perfilImovel );
		}
		
		
		if( !Util.isVazioOrNulo(form.getTiposCliente())){
			String tiposCliente = "";
			String[] array = form.getTiposCliente();
			
			for(String tipoClienteTemp : array){
				if (!tipoClienteTemp.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
					FiltroClienteTipo filtro = new FiltroClienteTipo();
					filtro.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID, tipoClienteTemp));
					Collection<ClienteTipo> colecao = Fachada.getInstancia().pesquisar(filtro, ClienteTipo.class.getName());
					tiposCliente += colecao.iterator().next().getId() + " ";
				}				
			}
			relatorio.addParametro("tiposCliente", tiposCliente );
		}
	}
	
	private boolean pesquisarLocalidade(GerarRelatorioImoveisFaturasAtrasoActionForm form, boolean isConsultarLocalidadeInicial){		
		String idLocalidade = form.getLocalidadeFinal();
		
		if(isConsultarLocalidadeInicial){
			idLocalidade = form.getLocalidadeInicial();
		}
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,idLocalidade));
		
		Collection<Localidade> colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoLocalidade)) {
			if(isConsultarLocalidadeInicial){
				throw new ActionServletException("atencao.pesquisa.localidade_inicial_inexistente");
			}else{
				throw new ActionServletException("atencao.pesquisa.localidade_final_inexistente");
			}
		}		
		return true;
	}
	
	private boolean pesquisarSetorComercial(GerarRelatorioImoveisFaturasAtrasoActionForm form,
			boolean isConsultarSetorComercialInicial) {

			String idLocalidade = form.getLocalidadeFinal();
			String idSetorComercial = form.getSetorComercialFinal();
			
			if(isConsultarSetorComercialInicial){
				idLocalidade = form.getLocalidadeInicial();
				idSetorComercial = form.getSetorComercialInicial();
			}

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,idSetorComercial));
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.LOCALIDADE,idLocalidade));
			

			Collection<SetorComercial> colecaoSetorComercial = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
			if ( Util.isVazioOrNulo(colecaoSetorComercial)) {
				if(isConsultarSetorComercialInicial){
					throw new ActionServletException("atencao.pesquisa.setor_inicial_inexistente");
				}else{
					throw new ActionServletException("atencao.pesquisa.setor_final_inexistente");
				}
			}
			
			return true;
	}
	
	private boolean pesquisarRota(GerarRelatorioImoveisFaturasAtrasoActionForm form,
			boolean isRotaInicial) {

			String idRota = form.getRotaFinal();
			
			if(isRotaInicial){
				idRota = form.getRotaInicial();
			}

			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(
				new ParametroSimples(FiltroRota.CODIGO_ROTA,idRota));			

			Collection<Rota> colecaoRota = this.getFachada().pesquisar(filtroRota, Rota.class.getName());
		
			if ( Util.isVazioOrNulo(colecaoRota)) {
				if(isRotaInicial){
					throw new ActionServletException("atencao.pesquisa.rota_inicial_inexistente");
				}else{
					throw new ActionServletException("atencao.pesquisa.rota_final_inexistente");
				}
			}
			
			return true;
	}
	
	private boolean pesquisarCliente(GerarRelatorioImoveisFaturasAtrasoActionForm form,
			boolean isClienteSuperior){
		
		String idCliente = form.getCodigoCliente();
		
		if(isClienteSuperior){	
			idCliente = form.getCodigoClienteSuperior();
		}
		
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<Cliente> colCliente = this.getFachada().pesquisarCliente(filtroCliente);
		
		if(Util.isVazioOrNulo(colCliente)){
			if(isClienteSuperior){
				throw new ActionServletException("atencao.pesquisa.cliente.inexistente", "Superior");
			}else{
				throw new ActionServletException("atencao.cliente.inexistente");
			}
		}
			
		return true;
	}
	
	private boolean pesquisarQuadra(GerarRelatorioImoveisFaturasAtrasoActionForm form,boolean consultarQuadraInicial) {
	
		String idLocalidade = form.getLocalidadeFinal();
		String codigoSetorComercial = form.getSetorComercialFinal();
		String numeroQuadra = form.getQuadraFinal();
		
			
		
		if(consultarQuadraInicial){
			idLocalidade = form.getLocalidadeInicial();
			codigoSetorComercial = form.getSetorComercialInicial();
			numeroQuadra = form.getQuadraInicial();
		}

		
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, idLocalidade));
	    filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, codigoSetorComercial));
	    filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, numeroQuadra));
	    
	    Collection<Quadra> colecao = this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
	    
	    if(Util.isVazioOrNulo(colecao)){
				throw new ActionServletException("atencao.quadra.inexistente");
		}
		
		return true;
	}


}