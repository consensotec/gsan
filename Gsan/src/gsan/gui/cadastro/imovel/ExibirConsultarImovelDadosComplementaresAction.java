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
package gsan.gui.cadastro.imovel;

import gsan.arrecadacao.ContratoDemanda;
import gsan.arrecadacao.bean.ContratoDemandaHelper;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.FiltroImovelCadastroOcorrencia;
import gsan.cadastro.imovel.FiltroImovelEloAnormalidade;
import gsan.cadastro.imovel.FiltroImovelRamoAtividade;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelCadastroOcorrencia;
import gsan.cadastro.imovel.ImovelEloAnormalidade;
import gsan.cadastro.imovel.ImovelRamoAtividade;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.FiltroContratoDemanda;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0473] Consultar Imovel
 * 
 * @author Rafael Santos
 * @since 07/09/2006
 * 
 */
public class ExibirConsultarImovelDadosComplementaresAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		if (isLimparDadosTela(httpServletRequest)) {

			httpServletRequest.removeAttribute("idImovelDadosComplementaresNaoEncontrado");
			limparFormSessao(consultarImovelActionForm, sessao);
						
        }else if( isImovelInformadoTelaDadosAdicionais(consultarImovelActionForm)
        			|| isImovelInformadoOutraTela(sessao) ){

        	consultarImovelActionForm.setIdImovelDadosComplementares(
        			definirIdImovelASerPesquisado(consultarImovelActionForm, sessao, httpServletRequest));
        	
        	Imovel imovel = obterImovelASerPesquisado(consultarImovelActionForm, sessao);
        	
			boolean isNovoImovelPesquisado = isNovoImovelPesquisado(consultarImovelActionForm, sessao);

			if (imovel != null) {
				
				
				sessao.setAttribute("imovelDadosComplementares", imovel);
				sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());
				
				consultarImovelActionForm.setIdImovelDadosComplementares(imovel.getId().toString());
				
                if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}
                
				if (isNovoImovelPesquisado) {

					httpServletRequest.removeAttribute("idImovelDadosComplementaresNaoEncontrado");

					setarDadosNoFormESessao(consultarImovelActionForm, imovel,sessao, fachada);
				}
			} else {
				limparFormSessao(consultarImovelActionForm, sessao);

				httpServletRequest.setAttribute("idImovelDadosComplementaresNaoEncontrado", "true");				
				consultarImovelActionForm.setMatriculaImovelDadosComplementares("IMÓVEL INEXISTENTE");				
			}

		} else {
			
			String idAux = consultarImovelActionForm.getIdImovelDadosComplementares();
			
			httpServletRequest.removeAttribute("idImovelDadosComplementaresNaoEncontrado");
			
			limparFormSessao(consultarImovelActionForm, sessao);
			
			consultarImovelActionForm.setIdImovelDadosComplementares(idAux);			
			
		}

		if(httpServletRequest.getParameter("pesquisarHistorico") != null && httpServletRequest.getParameter("pesquisarHistorico").equals("OK")){
			pesquisarHistoricoImovel(httpServletRequest, consultarImovelActionForm);
		}

		return actionMapping.findForward("consultarImovelDadosComplementares");
	}

	/**
	 * Esse método seta os dados necessários do Imovel
	 * no form e alguns na sessão (coleções).
	 * 
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private void setarDadosNoFormESessao(ConsultarImovelActionForm consultarImovelActionForm, 
			Imovel imovel,HttpSession sessao, Fachada fachada) {
		
		// pesquisar os parâmetros do sistema
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		//Indicador Valida CPF/CNPJ
		if(sistemaParametro.getIndicadorValidaCpfCnpj() != null){
			consultarImovelActionForm.setIndicadorValidaCPFCNPJ(String.valueOf(sistemaParametro.getIndicadorValidaCpfCnpj()));
		}

		// seta na tela a inscrição do imovel
		consultarImovelActionForm.setMatriculaImovelDadosComplementares(
			this.getFachada().pesquisarInscricaoImovelExcluidoOuNao(new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));

		// seta a situação de agua
		consultarImovelActionForm.setSituacaoAguaDadosComplementares(
			imovel.getLigacaoAguaSituacao().getDescricao());
		
		// seta a situação de esgoto
		consultarImovelActionForm.setSituacaoEsgotoDadosComplementares(
			imovel.getLigacaoEsgotoSituacao().getDescricao());

		// consumo tarifa
		if (imovel.getConsumoTarifa() != null) {
			consultarImovelActionForm.setTarifaConsumoDadosComplementares(
				imovel.getConsumoTarifa().getDescricao());
		}
		
		// numero retificacao
		if (imovel.getNumeroRetificacao() != null) {
			consultarImovelActionForm.setQuantidadeRetificacoesDadosComplementares(
				imovel.getNumeroRetificacao().toString());
		}
		
		// numero parcelamento
		if (imovel.getNumeroParcelamento() != null) {
			consultarImovelActionForm.setQuantidadeParcelamentosDadosComplementares(
				imovel.getNumeroParcelamento().toString());
		}
		
		// numero reparcelamento
		if (imovel.getNumeroReparcelamento() != null) {
			consultarImovelActionForm.setQuantidadeReparcelamentoDadosComplementares(
				imovel.getNumeroReparcelamento().toString());
		}
		
		// numero reparcelamento consecutivos
		if (imovel.getNumeroReparcelamentoConsecutivos() != null) {
			consultarImovelActionForm.setQuantidadeReparcelamentoConsecutivosDadosComplementares(
				imovel.getNumeroReparcelamentoConsecutivos().toString());
		}
		
		// cobranca situacao
//		if (imovel.getCobrancaSituacao() != null) {
//			consultarImovelActionForm.setSituacaoCobrancaDadosComplementares(
//				imovel.getCobrancaSituacao().getDescricao());
//		}else{
//			consultarImovelActionForm.setSituacaoCobrancaDadosComplementares(null);
//		}
		
		// funcionario
		if (imovel.getFuncionario() != null) {
			consultarImovelActionForm.setIdFuncionario(""+imovel.getFuncionario().getId());
			consultarImovelActionForm.setNomeFuncionario(imovel.getFuncionario().getNome());
		} else {
			consultarImovelActionForm.setIdFuncionario("");
			consultarImovelActionForm.setNomeFuncionario("");
		}
		
		// cadastro ocorrencia
		if (imovel.getCadastroOcorrencia() != null) {
			consultarImovelActionForm.setDescricaoOcorrenciaDadosComplementares(
				imovel.getCadastroOcorrencia().getDescricao());
		}
		
		// elo anormalidade
		if (imovel.getEloAnormalidade() != null) {
			consultarImovelActionForm.setDescricaoAnormalidadeDadosComplementares(
				imovel.getEloAnormalidade().getDescricao());
		}
		
		//descricao das informacoes complementares
		if(imovel.getInformacoesComplementares() != null){
			consultarImovelActionForm.setInformacoesComplementares(imovel.getInformacoesComplementares());
		} else {
			consultarImovelActionForm.setInformacoesComplementares("");
		}

		sessao.setAttribute("colecaoVencimentosAlternativos",
				this.getFachada().pesquisarVencimentoAlternativoImovel(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));

		sessao.setAttribute("colecaoDebitosAutomaticos",
				this.getFachada().pesquisarDebitosAutomaticosImovel(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
		
		sessao.setAttribute("colecaoFaturamentosSituacaoHistorico",
				this.getFachada().pesquisarFaturamentosSituacaoHistorico(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
		
		sessao.setAttribute("colecaoCobrancasSituacaoHistorico",
				this.getFachada().pesquisarCobrancasSituacaoHistorico(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
		
		Cliente clienteUsuario = fachada.pesquisarClienteUsuarioImovelExcluidoOuNao( imovel.getId() );
		
		if(clienteUsuario != null){
			consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado(String.valueOf(clienteUsuario.getIndicadorValidaCpfCnpj()));
		}else{
			consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado("2");
		}
		
		setarColecaoCadastroOcorrenciaSessao(consultarImovelActionForm, sessao);
		
		setarColecaoEloAnormalidadeSessao(consultarImovelActionForm, sessao);
		
    	setarColecaoImovelRamoAtividadeSessao(imovel, sessao);	
    	
    	setarColecaoDadosImovelCobrancaSituacaoSessao(consultarImovelActionForm,sessao);

    	setarColecaoDadosNegativadorMovimentoRegSessao(consultarImovelActionForm,sessao);

    	setarColecaoContratoDemandaHelper(imovel,sessao);
    	
    	setarColecaoMatriculasAssociadas(imovel,sessao);
    	
	}

	// UC12879 - Consultar Dados Complementares Imovel.
	private void pesquisarHistoricoImovel(HttpServletRequest httpServletRequest,ConsultarImovelActionForm consultarImovelActionForm) {
		Integer idUsuarioAcao = null;
		String[] idOperacoes = null;
		String idUsuario = null;
		Date dataInicial = null;
		Date dataFinal = null;
		Date horaInicial = null;
		Date horaFinal = null;
		Integer idImovel = null;
		String unidadeNegocio = null;

		Hashtable<String, String> argumentos = new Hashtable<String, String>();
		argumentos.put("", consultarImovelActionForm.getIdImovelDadosComplementares());

		Collection colecaoAlteracaoImovel = Fachada.getInstancia().pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao,
						idOperacoes, idUsuario, dataInicial, dataFinal,
						horaInicial, horaFinal, argumentos, idImovel, null,
						unidadeNegocio, true);

		httpServletRequest.setAttribute("colecaoAlteracaoImovel", colecaoAlteracaoImovel);
	}

	/**
	 * Esse método consulta e seta na sessão 
	 * uma coleção de elo anormalidade do imovel.
	 *
	 *@since 05/10/2009
	 *@author Marlon Patrick
	 */
	private void setarColecaoImovelRamoAtividadeSessao(Imovel imovel,
			HttpSession sessao) {
		
		FiltroImovelRamoAtividade filtroImovelRamoAtividade = new FiltroImovelRamoAtividade();
        filtroImovelRamoAtividade.adicionarCaminhoParaCarregamentoEntidade("comp_id.imovel");
        filtroImovelRamoAtividade.adicionarCaminhoParaCarregamentoEntidade("comp_id.ramo_atividade");
        
        filtroImovelRamoAtividade.adicionarParametro(new ParametroSimples(
                FiltroImovelRamoAtividade.IMOVEL_ID, new Integer(imovel.getId())));
        
        sessao.setAttribute("colecaoImovelRamosAtividade", 
        		this.getFachada().pesquisar(filtroImovelRamoAtividade,
        				ImovelRamoAtividade.class.getName()));
	}

	/**
	 * Esse método consulta e seta na sessão 
	 * uma coleção de elo anormalidade do imovel.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private void setarColecaoEloAnormalidadeSessao(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		FiltroImovelEloAnormalidade filtroImovelEloAnormalidade = new FiltroImovelEloAnormalidade();
		filtroImovelEloAnormalidade.adicionarParametro(
			new ParametroSimples(FiltroImovelEloAnormalidade.IMOVEL_ID, 
					consultarImovelActionForm.getIdImovelDadosComplementares().trim()));
		
		filtroImovelEloAnormalidade.setCampoOrderBy(FiltroImovelEloAnormalidade.DATA_ANORMALIDADE);
		filtroImovelEloAnormalidade.adicionarCaminhoParaCarregamentoEntidade("eloAnormalidade");
		
		sessao.setAttribute("colecaoImovelEloAnormalidade",
				this.getFachada().pesquisar(filtroImovelEloAnormalidade, 
						ImovelEloAnormalidade.class.getName()));
	}

	/**
	 * Esse método consulta e seta na sessão 
	 * uma coleção de cadastro ocorrencia do imovel.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private void setarColecaoCadastroOcorrenciaSessao(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		FiltroImovelCadastroOcorrencia filtroImovelCadastroOcorrencia = 
			new FiltroImovelCadastroOcorrencia();
		
		filtroImovelCadastroOcorrencia.adicionarParametro(
			new ParametroSimples(FiltroImovelCadastroOcorrencia.IMOVEL_ID, 
					consultarImovelActionForm.getIdImovelDadosComplementares().trim()));
		
		filtroImovelCadastroOcorrencia.setCampoOrderBy(FiltroImovelCadastroOcorrencia.DATA_OCORRENCIA);
		filtroImovelCadastroOcorrencia.adicionarCaminhoParaCarregamentoEntidade("cadastroOcorrencia");
		
		sessao.setAttribute("colecaoImovelCadastroOcorrencia",
				this.getFachada().pesquisar(filtroImovelCadastroOcorrencia, 
						ImovelCadastroOcorrencia.class.getName()));
	}

	/**
	 *Esse método limpa todos os atributos do form
	 *e os atributos na sesssão 
	 *que são usados pelo action e/ou jsp.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private void limparFormSessao(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		sessao.removeAttribute("imovelDadosComplementares");
		sessao.removeAttribute("colecaoVencimentosAlternativos");
		sessao.removeAttribute("colecaoDebitosAutomaticos");
		sessao.removeAttribute("colecaoFaturamentosSituacaoHistorico");
		sessao.removeAttribute("colecaoCobrancasSituacaoHistorico");
		sessao.removeAttribute("idImovelPrincipalAba");			
		sessao.removeAttribute("colecaoImovelCadastroOcorrencia");
		sessao.removeAttribute("colecaoImovelEloAnormalidade");
		sessao.removeAttribute("colecaoImovelRamosAtividade");
		sessao.removeAttribute("contratoDemandaHelper");
		sessao.removeAttribute("colecaoMatriculasAssociadas");

		consultarImovelActionForm.setIdImovelDadosComplementares(null);
		consultarImovelActionForm.setIdImovelDadosCadastrais(null);
		consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
		consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
		consultarImovelActionForm.setIdImovelDebitos(null);
		consultarImovelActionForm.setIdImovelPagamentos(null);
		consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
		consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
		consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
		consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
		consultarImovelActionForm.setImovIdAnt(null);
		
		consultarImovelActionForm.setMatriculaImovelDadosComplementares(null);
		consultarImovelActionForm.setSituacaoAguaDadosComplementares(null);
		consultarImovelActionForm.setSituacaoEsgotoDadosComplementares(null);
		consultarImovelActionForm.setTarifaConsumoDadosComplementares(null);
		consultarImovelActionForm.setQuantidadeRetificacoesDadosComplementares(null);
		consultarImovelActionForm.setQuantidadeParcelamentosDadosComplementares(null);
		consultarImovelActionForm.setQuantidadeReparcelamentoDadosComplementares(null);
		consultarImovelActionForm.setQuantidadeReparcelamentoConsecutivosDadosComplementares(null);
//		consultarImovelActionForm.setSituacaoCobrancaDadosComplementares(null);
		consultarImovelActionForm.setDescricaoOcorrenciaDadosComplementares(null);
		consultarImovelActionForm.setIdFuncionario(null);
		consultarImovelActionForm.setNomeFuncionario(null);
		consultarImovelActionForm.setDescricaoAnormalidadeDadosComplementares(null);
		consultarImovelActionForm.setInformacoesComplementares(null);
		consultarImovelActionForm.setIndicadorValidaCPFCNPJ("2");
		consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado("2");
	}

	/**
	 * Caso o usuário tenha clicado no botão de limpar
	 * esse método retornará true.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isLimparDadosTela(HttpServletRequest httpServletRequest) {
		return Util.verificarNaoVazio(httpServletRequest.getParameter("limparForm"));
	}

	/**
	 * Esse método verifica se já foi informado um imóvel em outra tela.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoOutraTela(HttpSession sessao) {
		return Util.verificarNaoVazio((String)sessao.getAttribute("idImovelPrincipalAba"));
	}

	/**
	 * Esse método verifica se o imóvel foi informado na tela
	 * de Dados Adicionais
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoTelaDadosAdicionais(ConsultarImovelActionForm consultarImovelActionForm) {
		return Util.verificarNaoVazio(consultarImovelActionForm.getIdImovelDadosComplementares());
	}
	
	/**
	 * Esse método retorna o id do imóvel a ser pesquisado,
	 * verificando se é o id possivelmente informado pelo usuário na tela 
	 * de dados adicionais ou se o id já informado em uma outra tela.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private String definirIdImovelASerPesquisado(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao, HttpServletRequest httpServletRequest) {
		
		String idImovelPrincipalAba = (String)sessao.getAttribute("idImovelPrincipalAba");
		
		if( isImovelInformadoTelaDadosAdicionais(consultarImovelActionForm)
				&& isImovelInformadoOutraTela(sessao)){
		
			if( !Util.verificarNaoVazio(httpServletRequest.getParameter("indicadorNovo")) ){        				
					return idImovelPrincipalAba;            		
			}

		}else if(isImovelInformadoOutraTela(sessao)){
				return idImovelPrincipalAba;            		
		}

		return consultarImovelActionForm.getIdImovelDadosComplementares();
	}

	/**
	 * Consulta o Imovel com todas as informações necessárias,
	 * ou simplesmetne pega o Imovel da sessão caso o mesmo já
	 * tenha sido pesquisado.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private Imovel obterImovelASerPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		Imovel imovel = null;

		if(sessao.getAttribute("imovelDadosComplementares") == null){
			imovel = Fachada.getInstancia().consultarImovelDadosComplementares(new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim()));

		}else{
			imovel = (Imovel) sessao.getAttribute("imovelDadosComplementares");
			
			if( !imovel.getId().toString().equals(consultarImovelActionForm.getIdImovelDadosComplementares().trim()) ){
				imovel = Fachada.getInstancia().consultarImovelDadosComplementares(new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim()));
			}
		}
		return imovel;
	}

	/**
	 * Esse método retorna true se foi necessário consultar um novo imovel.
	 * Caso o imóvel seja o mesmo já consultado anteriormente ele retorna false.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isNovoImovelPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		if(sessao.getAttribute("imovelDadosComplementares") == null){
			return true;
		}
		
		Imovel imovelAux = (Imovel) sessao.getAttribute("imovelDadosComplementares");
		
		if( !imovelAux.getId().toString().equals(consultarImovelActionForm.getIdImovelDadosComplementares().trim()) ){
			return true;
		}

		return false;
	}
	
	/**
	 *@since 03/05/2010
	 *@author Vivianne Sousa
	 */
	private void setarColecaoDadosImovelCobrancaSituacaoSessao(
			ConsultarImovelActionForm consultarImovelActionForm,HttpSession sessao) {		
		sessao.setAttribute("colecaoDadosImovelCobrancaSituacao",this.getFachada().pesquisarDadosImovelCobrancaSituacaoPorImovel(
			new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
	}

	/**
	 *@since 03/05/2010
	 *@author Vivianne Sousa
	 */
	private void setarColecaoDadosNegativadorMovimentoRegSessao(
			ConsultarImovelActionForm consultarImovelActionForm,HttpSession sessao) {
		
		sessao.setAttribute("colecaoDadosNegativadorMovimentoReg",
				this.getFachada().consultarDadosNegativadorMovimentoReg(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
	}
	

	/**
	 *@since 06/01/2011
	 *@author Mariana Victor
	 */
	private void setarColecaoContratoDemandaHelper(Imovel imovel,HttpSession sessao) {
		ContratoDemandaHelper contratoDemandaHelper = null;
		
		FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();
		filtroContratoDemanda.adicionarParametro(
				new ParametroSimples(FiltroContratoDemanda.IMOVEL, imovel.getId()));
		filtroContratoDemanda.adicionarParametro(
				new ParametroNulo(FiltroContratoDemanda.DATACONTRATOENCERRAMENTO));
		Collection colecaoContratoDemanda = this.getFachada().pesquisar(
				filtroContratoDemanda, ContratoDemanda.class.getName());
		
		if (colecaoContratoDemanda != null && !colecaoContratoDemanda.isEmpty()) {
			Object[] consumoContratado = this.getFachada().consultarConsumoCadastrado(imovel.getId());
			if (consumoContratado != null) {
				contratoDemandaHelper = new ContratoDemandaHelper();
				contratoDemandaHelper.setContratoDemanda((ContratoDemanda) Util.retonarObjetoDeColecao(colecaoContratoDemanda));
				contratoDemandaHelper.setConsumoContratado(consumoContratado[0].toString());
				contratoDemandaHelper.setValorTarifa(Util.formatarBigDecimalParaStringComVirgula(
						(BigDecimal) consumoContratado[1]));
			}
		}
		
		if (contratoDemandaHelper != null) {
			sessao.setAttribute("contratoDemandaHelper", contratoDemandaHelper);
		} else {
			sessao.removeAttribute("contratoDemandaHelper");
		}
	}
	

	/**
	 *@since 06/01/2011
	 *@author Mariana Victor
	 */
	private void setarColecaoMatriculasAssociadas(Imovel imovel,HttpSession sessao) {
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(
				new ParametroSimples(FiltroImovel.ID, imovel.getId()));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(
				FiltroImovel.CONSUMO_TARIFA);
		Collection colecaoImovel = this.getFachada().pesquisar(
				filtroImovel, Imovel.class.getName());
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
			Imovel imovelRetornado = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
			Collection colecaoMatriculasAssociadas = this.getFachada().consultarMatriculasAssociadas(
					imovelRetornado.getConsumoTarifa().getId(), imovelRetornado.getId());
			if (colecaoMatriculasAssociadas != null && !colecaoMatriculasAssociadas.isEmpty()) {
				sessao.setAttribute("colecaoMatriculasAssociadas", colecaoMatriculasAssociadas);
			} else {
				sessao.removeAttribute("colecaoMatriculasAssociadas");
			}
		}
		
	}
	
}