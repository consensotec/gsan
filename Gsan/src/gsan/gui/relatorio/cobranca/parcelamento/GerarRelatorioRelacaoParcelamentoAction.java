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
package gsan.gui.relatorio.cobranca.parcelamento;

import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.cobranca.bean.FiltrarRelacaoParcelamentoHelper;
import gsan.cobranca.parcelamento.Parcelamento;
import gsan.cobranca.parcelamento.ParcelamentoSituacao;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.cobranca.parcelamento.GerarRelatorioRelacaoParcelamentoActionForm;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.RelatorioVazioException;
import gsan.relatorio.cobranca.parcelamento.RelacaoParcelamentoRelatorioHelper;
import gsan.relatorio.cobranca.parcelamento.RelatorioRelacaoParcelamento;
import gsan.relatorio.cobranca.parcelamento.RelatorioRelacaoParcelamentoAnalitico;
import gsan.relatorio.cobranca.parcelamento.RelatorioRelacaoParcelamentoAnaliticoBean;
import gsan.relatorio.cobranca.parcelamento.RelatorioRelacaoParcelamentoCartaoCredito;
import gsan.relatorio.cobranca.parcelamento.RelatorioRelacaoParcelamentoCartaoCreditoBean;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.SistemaException;
import gsan.util.Util;
import gsan.util.filtro.ConectorOr;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0594] Gerar Rela��o de Parcelamento
 * 
 * @author Ana Maria, Diogo Peixoto
 *
 * @date 30/05/2007, 29/04/2011
 */
public class GerarRelatorioRelacaoParcelamentoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
        ActionForward retorno = null;

		Fachada fachada =  Fachada.getInstancia();
        
        httpServletRequest.setAttribute( "telaSucessoRelatorio", "sim" );
		
		 //HttpSession sessao = httpServletRequest.getSession(false);    
		
		// cria uma inst�ncia da classe do relat�rio
		RelatorioRelacaoParcelamento relatorioRelacaoParcelamento = 
			new RelatorioRelacaoParcelamento((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));		
		RelatorioRelacaoParcelamentoAnalitico relatorioRelacaoParcelamentoAnalitico = 
			new RelatorioRelacaoParcelamentoAnalitico((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		RelatorioRelacaoParcelamentoCartaoCredito relatorioRelacaoParcelamentoCartaoCredito = 
			new RelatorioRelacaoParcelamentoCartaoCredito((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		
		GerarRelatorioRelacaoParcelamentoActionForm form = (GerarRelatorioRelacaoParcelamentoActionForm) actionForm;

		String idLocalidade     = form.getIdLocalidade();
		String codigoSetorComercial = form.getIdSetorComercial();
		String nnQuadra 		= form.getIdQuadra();	
		String idSituacaoParcelamento =  form.getIdSituacaoParcelamento();
		 
		Parcelamento parcelamento = new Parcelamento();
		FiltrarRelacaoParcelamentoHelper filtroParcelamento = new FiltrarRelacaoParcelamentoHelper();
		
		boolean peloMenosUmParametroInformado = false;

		// Unidade Organizacional
		if (form.getIdUnidadeOrganizacional() != null && !form.getIdUnidadeOrganizacional().trim().equals("") ){

			peloMenosUmParametroInformado = true;
			filtroParcelamento.setIdUnidadeOrganizacional(
					new Integer(form.getIdUnidadeOrganizacional()));
		}
		
		
		// Insere os par�metros informados no filtro
		Localidade localidade = new Localidade();
		if (idLocalidade != null && !idLocalidade.trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			if(colecaoLocalidade != null && !colecaoLocalidade.isEmpty()){
				peloMenosUmParametroInformado = true;
				localidade.setId(new Integer(idLocalidade));
				parcelamento.setLocalidade(localidade);
			}
			
			if(codigoSetorComercial != null && !codigoSetorComercial.trim().equals("")){
			  parcelamento.setCodigoSetorComercial(new Integer(codigoSetorComercial));
			}
			if(nnQuadra != null && !nnQuadra.trim().equals("")){
			  parcelamento.setNumeroQuadra(new Integer(nnQuadra));
			}
		}

		// Munic�pios Associados � Localidade
		if (form.getMunicipiosAssociados() != null && form.getMunicipiosAssociados().length > 0) {
			Collection<Integer> colecao = new ArrayList();
			String[] array = form.getMunicipiosAssociados();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					peloMenosUmParametroInformado = true;
					colecao.add(new Integer(array[i]));
				}
			}
			filtroParcelamento.setColecaoMunicipiosAssociados(colecao);
		}
		
		// Perfil do Imovel
		if (form.getPerfilImovel() != null && form.getPerfilImovel().length > 0) {

			Collection<Integer> colecao = new ArrayList();

			String[] array = form.getPerfilImovel();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					peloMenosUmParametroInformado = true;
					colecao.add(new Integer(array[i]));
				}
			}
			filtroParcelamento.setColecaoPerfilImovel(colecao);
		}
		
		Integer idGerencia   = null;
		if(form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;
			idGerencia = new Integer(form.getIdGerenciaRegional());
			
		}
		
		Integer idUnidadeNegocio   = null;
		if(form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			peloMenosUmParametroInformado = true;
			idUnidadeNegocio = new Integer(form.getIdUnidadeNegocio());
			
		}

		if(idSituacaoParcelamento != null && !idSituacaoParcelamento.trim().equals("")){
			ParcelamentoSituacao parcelamentoSituacao = new ParcelamentoSituacao();
			parcelamentoSituacao.setId(new Integer(idSituacaoParcelamento));
			parcelamento.setParcelamentoSituacao(parcelamentoSituacao);
			
			
		}
		
		Collection<Integer> idsMotivoDesfazimento = new ArrayList();
		if (form.getIdsMotivoDesfazimento() != null && form.getIdsMotivoDesfazimento().length > 0) {
			String[] motivoDesfazimento = form.getIdsMotivoDesfazimento();
			for (int i = 0; i < motivoDesfazimento.length; i++) {
				if (new Integer(motivoDesfazimento[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					idsMotivoDesfazimento.add(new Integer(motivoDesfazimento[i]));
					// passar a cole��o de especifica��o por par�metro		
					peloMenosUmParametroInformado = true;
				}
			}
		}
		
		Date dataParcelamentoInicial	=  null;
		Date dataParcelamentoFinal	=  null;
		
		if (form.getDataParcelamentoInicial() != null && !form.getDataParcelamentoInicial().equals("")) {
			
			dataParcelamentoInicial = Util.converteStringParaDate(form.getDataParcelamentoInicial());
			dataParcelamentoInicial = Util.formatarDataInicial(dataParcelamentoInicial);
			
			dataParcelamentoFinal = null;
			if (form.getDataParcelamentoFinal() != null && !form.getDataParcelamentoFinal().equals("")) {
				dataParcelamentoFinal = Util.converteStringParaDate(form.getDataParcelamentoFinal());
				dataParcelamentoFinal = Util.adaptarDataFinalComparacaoBetween(dataParcelamentoFinal);
			} else {
				dataParcelamentoFinal = new Date();
				dataParcelamentoFinal = Util.formatarDataFinal(dataParcelamentoFinal);
			}
			//[FS005] Verificar data final menor que data inicial
			int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(dataParcelamentoInicial, dataParcelamentoFinal);
			if (qtdeDias < 0) {
				throw new ActionServletException("atencao.filtrar_data_final_maior_que_inicial");
			}
			
			relatorioRelacaoParcelamento.addParametro("dataParcelamentoInicial", form.getDataParcelamentoInicial());
			relatorioRelacaoParcelamento.addParametro("dataParcelamentoFinal", form.getDataParcelamentoFinal());
			// passar as datas de atendimento por par�metro
			peloMenosUmParametroInformado = true;
		}
		
		Integer anoMesReferenciaContabil = null;
		
		if (form.getAnoMesReferenciaContabil() != null && !form.getAnoMesReferenciaContabil().equals("")){
			
			anoMesReferenciaContabil = new Integer(Util.formatarMesAnoParaAnoMesSemBarra(form.getAnoMesReferenciaContabil()));
			
			relatorioRelacaoParcelamento.addParametro("anoMesReferenciaContabil", form.getAnoMesReferenciaContabil());
			
			peloMenosUmParametroInformado = true;
		}
		
		String periodoContabil = null;
		
		if (form.getPeriodoContabil() != null && !form.getPeriodoContabil().equals("")){
			
			periodoContabil = form.getPeriodoContabil()+"%";
			
			peloMenosUmParametroInformado = true;
		}
		
		String valorDebitoInicial	=  form.getValorDebitoInicial();
		String valorDebitoFinal	=  form.getValorDebitoFinal();
		BigDecimal valorInicial = null;
		BigDecimal valorFinal = null;
			
		// Verifica se o campo valorDebitoInicial e valorDebitoFinal foram informados
		if (valorDebitoInicial != null && !valorDebitoInicial.trim().equalsIgnoreCase("") &&
				valorDebitoFinal != null && !valorDebitoFinal.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;	
			
			String valorSemPontosInicial = valorDebitoInicial.replace(".", "");
			valorDebitoInicial = valorSemPontosInicial.replace(",", ".");	

			String valorSemPontosFinal = valorDebitoFinal.replace(".", "");
			valorDebitoFinal = valorSemPontosFinal.replace(",", ".");

		   valorInicial = new BigDecimal(valorDebitoInicial);
		   valorFinal = new BigDecimal(valorDebitoFinal);
		   Integer resultado = valorInicial.compareTo(valorFinal);

		   if (resultado == 1) {
			throw new ActionServletException(
					"atencao.valor_servico_final_menor_valor_servico_inicial");
		   }
		   
		}
		
		// Verificamos se o elo foi informado
		Integer idElo = null;
		
		if ( form.getIdEloPolo() != null && 
			 !form.getIdEloPolo().equals( "" ) ){
			peloMenosUmParametroInformado = true;
			
			FiltroLocalidade filtro = new FiltroLocalidade();
			filtro.adicionarParametro( 
					new ParametroSimples( FiltroLocalidade.ID, form.getIdLocalidade() ) );			
			Collection<Localidade> colLocalidade = fachada.pesquisar( filtro, Usuario.class.getName() );
			
			if ( colLocalidade != null && colLocalidade.size() == 0 ){
				throw new ActionServletException(
				"atencao.elo_invalido", null, form.getIdEloPolo() );				
			}
			
			idElo = Integer.parseInt( form.getIdEloPolo() );
			
		}		
		
		// Verificamos se o usu�rio responsavel foi informado
		Integer idUsuarioResponsavel = null;
		
		if ( form.getIdUsuarioResponsavel() != null && 
			 !form.getIdUsuarioResponsavel().equals( "" ) ){
			peloMenosUmParametroInformado = true;
			
			FiltroUsuario filtro = new FiltroUsuario();
			filtro.adicionarParametro( 
					new ParametroSimples( FiltroUsuario.ID, form.getIdUsuarioResponsavel() ) );			
			Collection<Usuario> colUsuario = fachada.pesquisar( filtro, Usuario.class.getName() );
			
			if ( colUsuario != null && colUsuario.size() == 0 ){
				throw new ActionServletException(
				"atencao.usuario_responsavel_invalido", null, form.getIdUsuarioResponsavel() );				
			}
			
			idUsuarioResponsavel = Integer.parseInt( form.getIdUsuarioResponsavel() );
		}
		
		// Verificamos data de confirma��o parcelamento
		Date dataConfirmacaoInicial	=  null;
		Date dataConfirmacaoFinal	=  null;
		
		if (form.getDataConfirmacaoInicial() != null && !form.getDataConfirmacaoInicial().equals("")
				&& form.getDataConfirmacaoFinal() != null && !form.getDataConfirmacaoFinal().equals("")) {
			
			dataConfirmacaoInicial = Util.converteStringParaDate(form.getDataConfirmacaoInicial());
			dataConfirmacaoInicial = Util.formatarDataInicial(dataConfirmacaoInicial);
			dataConfirmacaoFinal = Util.converteStringParaDate(form.getDataConfirmacaoFinal());
			dataConfirmacaoFinal = Util.adaptarDataFinalComparacaoBetween(dataConfirmacaoFinal);

			//[FS005] Verificar data final menor que data inicial
			int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(dataConfirmacaoInicial, dataConfirmacaoFinal);
			if (qtdeDias < 0) {
				throw new ActionServletException("atencao.filtrar_data_final_maior_que_inicial");
			}
			
			// passar as datas de atendimento por par�metro
			peloMenosUmParametroInformado = true;
		}
		
		// Verificamos se o usu�rio confirma��o foi informado
		Integer idUsuarioConfirmacao = null;
		
		if ( form.getIdUsuarioConfirmacao() != null && 
			 !form.getIdUsuarioConfirmacao().equals( "" )){
			peloMenosUmParametroInformado = true;
			
			FiltroUsuario filtro = new FiltroUsuario();
		    filtro.adicionarParametro( 
					new ParametroSimples( FiltroUsuario.ID, form.getIdUsuarioConfirmacao() ) );			
			Collection<Usuario> colUsuario = fachada.pesquisar( filtro, Usuario.class.getName() );
			
			if ( colUsuario != null && colUsuario.size() == 0 ){
				throw new ActionServletException(
				"atencao.usuario_confirmacao_invalido", null, form.getIdUsuarioConfirmacao() );				
			}
			
			idUsuarioConfirmacao = Integer.parseInt(form.getIdUsuarioConfirmacao());
		}
		
		// Verificamos indicador de confirma��o da operadora
		Short indicadorConfirmacaoOperadora = null;
		Date dataConfirmacaoOperadoraInicial	=  null;
		Date dataConfirmacaoOperadoraFinal	=  null;
		if(form.getIndicadorConfirmacaoOperadora()!=null
				&& !form.getIndicadorConfirmacaoOperadora().equals("")){
			
			peloMenosUmParametroInformado = true;
			
			indicadorConfirmacaoOperadora = new Short(form.getIndicadorConfirmacaoOperadora());
			
			if(form.getIndicadorConfirmacaoOperadora().equals(ConstantesSistema.SIM.toString())){
				if(form.getDataConfirmacaoOperadoraInicial()!=null && !form.getDataConfirmacaoOperadoraInicial().equals("")){
					dataConfirmacaoOperadoraInicial = Util.converteStringParaDate(form.getDataConfirmacaoOperadoraInicial());
					dataConfirmacaoOperadoraInicial = Util.formatarDataInicial(dataConfirmacaoOperadoraInicial);
				}
				if(form.getDataConfirmacaoOperadoraFinal()!=null && !form.getDataConfirmacaoOperadoraFinal().equals("")){
					dataConfirmacaoOperadoraFinal = Util.converteStringParaDate(form.getDataConfirmacaoOperadoraFinal());
					dataConfirmacaoOperadoraFinal = Util.formatarDataInicial(dataConfirmacaoOperadoraFinal);
				}
			}		
		}
		
		
		
		Collection<RelacaoParcelamentoRelatorioHelper> colecaoRelacaoParcelamento = null;
		Collection<RelatorioRelacaoParcelamentoAnaliticoBean> colecaoRelacaoParcelamentoAnalitico = null;
		Collection<RelatorioRelacaoParcelamentoCartaoCreditoBean> colecaoRelacaoParcelamentoCartaoCredito = null;

		if (peloMenosUmParametroInformado) {
			
			colecaoRelacaoParcelamento = new ArrayList();
			
			filtroParcelamento.setParcelamento(parcelamento);
			filtroParcelamento.setDataParcelamentoInicial(dataParcelamentoInicial);
			filtroParcelamento.setDataParcelamentoFinal(dataParcelamentoFinal);
			filtroParcelamento.setIdsMotivoDesfazimento(idsMotivoDesfazimento);
			filtroParcelamento.setValorDebitoInicial(valorInicial);
			filtroParcelamento.setValorDebitoFinal(valorFinal);
			filtroParcelamento.setIdGerencia(idGerencia);
			filtroParcelamento.setIdUnidadeNegocio(idUnidadeNegocio);
			filtroParcelamento.setIdUsuarioResponsavel( idUsuarioResponsavel );
			filtroParcelamento.setIdElo( idElo );
			filtroParcelamento.setDataConfirmacaoInicial(dataConfirmacaoInicial);
			filtroParcelamento.setDataConfirmacaoFinal(dataConfirmacaoFinal);
			filtroParcelamento.setIdUsuarioConfirmacao(idUsuarioConfirmacao);
			filtroParcelamento.setIndicadorConfirmacaoOperadora(indicadorConfirmacaoOperadora);
			filtroParcelamento.setDataConfirmacaoOperadoraInicial(dataConfirmacaoOperadoraInicial);
			filtroParcelamento.setDataConfirmacaoOperadoraFinal(dataConfirmacaoOperadoraFinal);
			filtroParcelamento.setAnoMesReferenciaContabil(anoMesReferenciaContabil);
			filtroParcelamento.setPeriodoContabil(periodoContabil);
			
			
			//MARKETING ATIVO
			if ( form.getIdVisaoRelatorio().equals( "1" ) ) {			
				colecaoRelacaoParcelamento = fachada.filtrarRelacaoParcelamento(filtroParcelamento);
			//ANALITICO
			} else 	if ( form.getIdVisaoRelatorio().equals( "2" ) ) {
				colecaoRelacaoParcelamentoAnalitico = fachada.filtrarRelacaoParcelamentoAnalitico(filtroParcelamento);
			//CARTAO CREDITO
			} else 	if ( form.getIdVisaoRelatorio().equals( "3" ) ) {
				colecaoRelacaoParcelamentoCartaoCredito = fachada.filtrarRelacaoParcelamentoCartaoCredito(filtroParcelamento);
			}
			
		} else {
			throw new ActionServletException("atencao.filtrar_informar_um_filtro");
		}
		
		// Gerencia Regional
		String parametroGerencia = null;
		if ( filtroParcelamento.getIdGerencia() != null && filtroParcelamento.getIdGerencia().toString() != "" ){
			FiltroGerenciaRegional filtro = new FiltroGerenciaRegional();
			filtro.adicionarParametro( new ParametroSimples( FiltroGerenciaRegional.ID, filtroParcelamento.getIdGerencia() ) );
			Collection<GerenciaRegional> colGerencia = fachada.pesquisar( filtro, GerenciaRegional.class.getName() );
			
			if ( colGerencia != null && colGerencia.size() > 0 ){
				GerenciaRegional gerencia = (GerenciaRegional) colGerencia.iterator().next();
				
				parametroGerencia = new String();
				parametroGerencia = "Ger�ncia Regional - " + filtroParcelamento.getIdGerencia() + " " + gerencia.getNome() + " ";
			}
		}
		
		// Unidade Organizacional
		String parametroUnidadeOrganizacional = null;
		if ( filtroParcelamento.getIdUnidadeOrganizacional() != null && !filtroParcelamento.getIdUnidadeOrganizacional().toString().equals("") ){
			
			FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
			filtro.adicionarParametro( new ParametroSimples( 
					FiltroUnidadeOrganizacional.ID, filtroParcelamento.getIdUnidadeOrganizacional() ) );
			
			Collection<UnidadeOrganizacional> colUnidade = fachada.pesquisar( filtro, UnidadeOrganizacional.class.getName() );
			
			if ( colUnidade != null && colUnidade.size() > 0 ){
				UnidadeOrganizacional unidade = (UnidadeOrganizacional) colUnidade.iterator().next();
				
				parametroUnidadeOrganizacional = new String();
				parametroUnidadeOrganizacional = "Unidade Organizacional - " + filtroParcelamento.getIdUnidadeOrganizacional() + " " + unidade.getDescricao() + " ";
			}
		}
		
		// Unidade Negocio
		String parametroUnidadeNegocio = null;
		if ( filtroParcelamento.getIdUnidadeNegocio() != null && filtroParcelamento.getIdUnidadeNegocio().toString() != "" ){
			FiltroUnidadeNegocio filtro = new FiltroUnidadeNegocio();
			filtro.adicionarParametro( new ParametroSimples( FiltroUnidadeNegocio.ID, filtroParcelamento.getIdUnidadeNegocio() ) );
			Collection<UnidadeNegocio> colUnidadeNegocio = fachada.pesquisar( filtro, UnidadeNegocio.class.getName() );
			
			if ( colUnidadeNegocio != null && colUnidadeNegocio.size() > 0 ){
				UnidadeNegocio unidadeNegocio = (UnidadeNegocio) colUnidadeNegocio.iterator().next();
				
				parametroUnidadeNegocio = new String();
				
				parametroUnidadeNegocio = "Unidade de Neg�cio - " + filtroParcelamento.getIdUnidadeNegocio() + " " + unidadeNegocio.getNome() + " ";
			}
		}
		
		// Elo
		String parametroElo = null;
		if ( filtroParcelamento.getIdElo() != null && filtroParcelamento.getIdElo().toString() != "" ){
			FiltroLocalidade filtro = new FiltroLocalidade();
			filtro.adicionarParametro( new ParametroSimples( FiltroLocalidade.ID_ELO, filtroParcelamento.getIdElo() ) );
			Collection<Localidade> colElo = fachada.pesquisar( filtro, Localidade.class.getName() );
			
			if ( colElo != null && colElo.size() > 0 ){
				Localidade elo = (Localidade) colElo.iterator().next();
				
				parametroElo = new String();
				
				parametroElo = "Elo - " + filtroParcelamento.getIdElo() + " " + elo.getDescricao() + " ";
			}
		}
		
		// Periodo Parcelamento
		String parametroPeriodo = null;
		if ( ( filtroParcelamento.getDataParcelamentoFinal() != null && 
			  !filtroParcelamento.getDataParcelamentoFinal().equals( "" ) ) ||
			 ( filtroParcelamento.getDataParcelamentoInicial() != null && 
			  !filtroParcelamento.getDataParcelamentoInicial().equals( "" ) ) ){
			
			parametroPeriodo = new String();
			
			parametroPeriodo = "Per�odo - de " + 
				Util.formatarData( 
						filtroParcelamento.getDataParcelamentoInicial() ) + 
				" at� " + Util.formatarData( 
						filtroParcelamento.getDataParcelamentoFinal() ) + "  ";
		}
		
		if (filtroParcelamento.getAnoMesReferenciaContabil() != null &&
				!filtroParcelamento.getAnoMesReferenciaContabil().equals("")){
			
			parametroPeriodo = new String();
			
			parametroPeriodo = "Per�odo: " + Util.formatarAnoMesParaMesAno(
				filtroParcelamento.getAnoMesReferenciaContabil());
		}
		
		if (filtroParcelamento.getPeriodoContabil() != null &&
				!filtroParcelamento.getPeriodoContabil().equals("")){
			
			parametroPeriodo = new String();
			
			parametroPeriodo = "Per�odo: " + filtroParcelamento.getPeriodoContabil().substring(0,4);
			
		}
		
		// Usuario Responsavel
		String parametroUsuario = null;
		if ( filtroParcelamento.getIdUsuarioResponsavel() != null && filtroParcelamento.getIdUsuarioResponsavel().toString() != "" ){
			FiltroUsuario filtro = new FiltroUsuario();
			filtro.adicionarParametro( new ParametroSimples( FiltroUsuario.ID, filtroParcelamento.getIdUsuarioResponsavel() ) );
			Collection<Usuario> colUsuario = fachada.pesquisar( filtro, Usuario.class.getName() );
			
			if ( colUsuario != null && colUsuario.size() > 0 ){
				Usuario usuario = (Usuario) colUsuario.iterator().next();
				
				parametroUsuario = new String();
				parametroUsuario = "Usu�rio Respons�vel - " + filtroParcelamento.getIdUsuarioResponsavel() + " " + usuario.getNomeUsuario() + " ";
			}
		}
		
		String parametroPerfilImovel = null;
		String parametroValor = null;
		if(filtroParcelamento.getColecaoPerfilImovel() !=null 
				&& !filtroParcelamento.getColecaoPerfilImovel().isEmpty()){
			
			Collection idsPerfilImovel = filtroParcelamento.getColecaoPerfilImovel();
			
			Iterator iteratorPerfilImovel = idsPerfilImovel.iterator();
			
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			
			while (iteratorPerfilImovel.hasNext()) {

				Integer idPerfil = (Integer) iteratorPerfilImovel
						.next();

				if (!iteratorPerfilImovel.hasNext()) {

					filtroImovelPerfil
							.adicionarParametro(new ParametroSimples(
									FiltroImovelPerfil.ID, idPerfil));
				} else {

					filtroImovelPerfil
							.adicionarParametro(new ParametroSimples(
									FiltroImovelPerfil.ID, idPerfil,
									ConectorOr.CONECTOR_OR));

				}

			}
			
			Collection colecaoImovelPerfil = fachada.pesquisar(
					filtroImovelPerfil,ImovelPerfil.class.getName());
			
			// Perfil Imovel
			if(colecaoImovelPerfil !=null && !colecaoImovelPerfil.isEmpty()){
				
				parametroPerfilImovel = new String();
				parametroPerfilImovel = "Peril do Im�vel - ";	
				
				Iterator iteratorImovelPerfil = colecaoImovelPerfil.iterator();
				
				while (iteratorImovelPerfil.hasNext()){
				
					ImovelPerfil imovelPerfil = (ImovelPerfil) iteratorImovelPerfil.next();
					
					parametroPerfilImovel += imovelPerfil.getDescricao() + "  , ";
				
				}
				
				parametroPerfilImovel = Util.removerUltimosCaracteres(parametroPerfilImovel,2);
			}
			
			// Valor
			if (form.getValorDebitoInicial()!= null && !form.getValorDebitoInicial().trim().equalsIgnoreCase("") &&
					form.getValorDebitoFinal() != null && !form.getValorDebitoFinal().trim().equalsIgnoreCase("")) {
				
				parametroValor = new String();
				parametroValor = "Valor - de " + form.getValorDebitoInicial() +  " a " + form.getValorDebitoFinal() + "  ";
				
				
			}
		}
		
		String parametroMuncipio = null;
		if(filtroParcelamento.getColecaoMunicipiosAssociados() !=null 
				&& !filtroParcelamento.getColecaoMunicipiosAssociados().isEmpty()){

			Collection idsMunicipios = filtroParcelamento.getColecaoMunicipiosAssociados();

			Iterator iteratorMuncipios = idsMunicipios.iterator();

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			while (iteratorMuncipios.hasNext()) {

				Integer idMuncipio = (Integer) iteratorMuncipios.next();

				if (!iteratorMuncipios.hasNext()) {
					filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMuncipio));
				} else {
					filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMuncipio, ConectorOr.CONECTOR_OR));
				}
			}

			Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio,Municipio.class.getName());

			// Perfil Imovel
			if(colecaoMunicipio != null && !colecaoMunicipio.isEmpty()){

				parametroMuncipio = new String();
				parametroMuncipio = "Munic�pio - ";	

				Iterator iteratorMunicipio = colecaoMunicipio.iterator();

				while (iteratorMunicipio.hasNext()){

					Municipio municipio = (Municipio) iteratorMunicipio.next();

					parametroMuncipio += municipio.getNome() + "  , ";

				}

				parametroMuncipio = Util.removerUltimosCaracteres(parametroMuncipio,2);
			}
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}		
		
		if ( form.getIdVisaoRelatorio().equals( "1" ) ){
			if(colecaoRelacaoParcelamento == null || colecaoRelacaoParcelamento.isEmpty()){
			  throw new ActionServletException("atencao.pesquisa.nenhumresultado");	
			}else{
				  relatorioRelacaoParcelamento.addParametro("colecaoRelacaoParcelamento",colecaoRelacaoParcelamento);
			}
			
			Integer idSituacao = new Integer(form.getIdSituacaoParcelamento());	
			String situacao = null;
			if(idSituacao.equals(ParcelamentoSituacao.NORMAL)){
				situacao = "NORMAL";
			}else{
				situacao = "DESFEITO";
			}
			
			String faixaValores = null;
			if(!valorDebitoInicial.equals("") && !valorDebitoFinal.equals("")){
				faixaValores = Util.formatarMoedaReal(valorInicial) + " a " + Util.formatarMoedaReal(valorFinal);
			}
			
			String periodo = null;
			if(dataParcelamentoInicial != null && dataParcelamentoFinal != null){
				periodo = Util.formatarData(dataParcelamentoInicial) + " a " + Util.formatarData(dataParcelamentoFinal);
			}
			
			String cabecalho = "RELA��O DE PARCELAMENTOS - " + "SITUA��O: " +situacao+ " - MARKETING ATIVO";
			
			//relatorioRelacaoParcelamento.addParametro( "parametros", parametros );
			
			relatorioRelacaoParcelamento.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			relatorioRelacaoParcelamento.addParametro("cabecalho", cabecalho);
			relatorioRelacaoParcelamento.addParametro("faixaValores", faixaValores);
			relatorioRelacaoParcelamento.addParametro("periodo", periodo);
			
			relatorioRelacaoParcelamento.addParametro( "parametrosGerencia", parametroGerencia );
			relatorioRelacaoParcelamento.addParametro( "parametroUnidadeOrganizacional", parametroUnidadeOrganizacional );
			relatorioRelacaoParcelamento.addParametro( "parametroUnidadeNegocio", parametroUnidadeNegocio );
			relatorioRelacaoParcelamento.addParametro( "parametroElo", parametroElo );
			relatorioRelacaoParcelamento.addParametro( "parametroPeriodo", parametroPeriodo );
			relatorioRelacaoParcelamento.addParametro( "parametroUsuario", parametroUsuario );
			relatorioRelacaoParcelamento.addParametro( "parametroPerfilImovel", parametroPerfilImovel );
			relatorioRelacaoParcelamento.addParametro( "parametroValor", parametroValor );
			relatorioRelacaoParcelamento.addParametro( "parametroMuncipio", parametroMuncipio );
			
		} else if ( form.getIdVisaoRelatorio().equals( "2" ) ){
			if(colecaoRelacaoParcelamentoAnalitico == null || colecaoRelacaoParcelamentoAnalitico.isEmpty()){
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");	
			}else{
				relatorioRelacaoParcelamentoAnalitico.addParametro("colecaoRelacaoParcelamentoAnalitico",colecaoRelacaoParcelamentoAnalitico);
				relatorioRelacaoParcelamentoAnalitico.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
				String cabecalho = "RELA��O DE PARCELAMENTOS - ANAL�TICO";
				relatorioRelacaoParcelamentoAnalitico.addParametro("cabecalho", cabecalho);
				
				//relatorioRelacaoParcelamentoAnalitico.addParametro( "parametros", parametros );
				
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametrosGerencia", parametroGerencia );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroUnidadeOrganizacional", parametroUnidadeOrganizacional );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroUnidadeNegocio", parametroUnidadeNegocio );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroElo", parametroElo );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroPeriodo", parametroPeriodo );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroUsuario", parametroUsuario );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroPerfilImovel", parametroPerfilImovel );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroValor", parametroValor );
				relatorioRelacaoParcelamentoAnalitico.addParametro( "parametroMuncipio", parametroMuncipio );
			}
		} else if ( form.getIdVisaoRelatorio().equals( "3" ) ){
			relatorioRelacaoParcelamentoCartaoCredito.addParametro("colecaoRelacaoParcelamentoCartaoCredito",colecaoRelacaoParcelamentoCartaoCredito);
			relatorioRelacaoParcelamentoCartaoCredito.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			String cabecalho = "RELA��O DE PARCELAMENTOS - CART�O DE CR�DITO";
			relatorioRelacaoParcelamentoCartaoCredito.addParametro("cabecalho", cabecalho);
			
			//relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametros", parametros );
			
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametrosGerencia", parametroGerencia );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroUnidadeOrganizacional", parametroUnidadeOrganizacional );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroUnidadeNegocio", parametroUnidadeNegocio );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroElo", parametroElo );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroPeriodo", parametroPeriodo );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroUsuario", parametroUsuario );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroPerfilImovel", parametroPerfilImovel );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroValor", parametroValor );
			relatorioRelacaoParcelamentoCartaoCredito.addParametro( "parametroMuncipio", parametroMuncipio );
		}
		relatorioRelacaoParcelamento.addParametro("tipoRelatorio", tipoRelatorio);
		try {
			
			if ( form.getIdVisaoRelatorio().equals( "1" ) ){
				retorno = processarExibicaoRelatorio(relatorioRelacaoParcelamento,
						tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);				
			} else if ( form.getIdVisaoRelatorio().equals( "2" ) ){
				retorno = processarExibicaoRelatorio(relatorioRelacaoParcelamentoAnalitico,
						tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);			
			}else if ( form.getIdVisaoRelatorio().equals( "3" ) ){
				retorno = processarExibicaoRelatorio(relatorioRelacaoParcelamentoCartaoCredito,
						tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);			
			}

		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}	
        
        return retorno;
	}
}
