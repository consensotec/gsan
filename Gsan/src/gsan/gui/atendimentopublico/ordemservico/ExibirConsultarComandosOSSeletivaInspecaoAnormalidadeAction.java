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
package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gsan.atendimentopublico.ordemservico.ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.batch.Processo;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
 * 
 * @author Vivianne Sousa, Raimundo Martins
 * @since 11/07/2011
 */
public class ExibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarComandosOSSeletivaInspecaoAnormalidade");
		
		
		
		
		
		String pagina = httpServletRequest.getParameter("page.offset");
	
		ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm form = (ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm) actionForm;
	
		Fachada fachada = Fachada.getInstancia();
	
		HttpSession sessao = httpServletRequest.getSession(false);
		
		this.pesquisarCampoEnter(httpServletRequest, form, fachada);
	
		if (httpServletRequest.getParameter("limpar") != null 
				&& httpServletRequest.getParameter("limpar").equalsIgnoreCase("sim")) {
	
			sessao.removeAttribute("colecaoConsultarComandosOSHelper");
			sessao.removeAttribute("totalRegistros");
			
		}
	
		if (httpServletRequest.getParameter("selecionarComandos") != null || pagina!=null) {
			if(pagina==null){
				pagina = "0";
			}
			
			
			retorno = this.pesquisarComandosOS(httpServletRequest, form, fachada,
					sessao,pagina,retorno);
		}
	
		if (httpServletRequest.getParameter("acao") != null 
				&& httpServletRequest.getParameter("acao").equalsIgnoreCase("gerarTxtComando")) {
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			String reg;
			String reqReg = httpServletRequest.getParameter("idRegistro");
			String actReg = form.getIdRegistro();
			if(actReg !=null && !actReg.trim().equals("")){
				reg = actReg;
			}
			else if(reqReg !=null && !reqReg.trim().equals("")){
				reg = reqReg;
			}
			else{
				throw new ActionServletException("atencao.selecione_comando");
			}
			ComandoOrdemSeletiva  comandoOrdemSeletiva = fachada.pesquisarComandoOSSeletiva(new Integer(reg));
			if (comandoOrdemSeletiva.getDataEncerramento() != null) {
				throw new ActionServletException("atencao.comando.ja_encerrado", 
						null, "Comandos de OS Seletiva de Inspeção de Anormalidade");
			} 
			
			Integer qtdeOsNaoPendenteFazParteComando = fachada.pesquisaOrdemServicoNaoPendenteFazParteComando(new Integer(form.getIdRegistro()));
			if(qtdeOsNaoPendenteFazParteComando != null && qtdeOsNaoPendenteFazParteComando.intValue() > 0){
				throw new ActionServletException("atencao.nao_e_possivel_gerar_txt_comando");
			}
			
			Map<String, Integer> parametros = new HashMap<String, Integer>();
			parametros.put("idComandoOrdemSeletiva",new Integer(form.getIdRegistro()));
			parametros.put("qtdAnormalidadesConsecutivas",comandoOrdemSeletiva.getQuantidadeConsecutivaAnormalidade());
			Fachada.getInstancia().inserirProcessoIniciadoParametrosLivresAguardandoAutorizacao(
					parametros, 
	         		Processo.GERAR_TXT_OS_INSPECAO_ANORMALIDADE ,
	         		usuarioLogado);
				
			retorno = actionMapping.findForward("telaSucesso");
			//montando página de sucesso
			montarPaginaSucesso(httpServletRequest,
				"Geração de Txt do Comando  de OS Seletiva de Inspeção de Anormalidade Enviado para Processamento", 
				"Voltar",
				"exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?menu=sim");
		}
		
		return retorno;
	}
	
	private void pesquisarCampoEnter(HttpServletRequest httpServletRequest,
			ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm form,
			Fachada fachada) {
	
		String idEmpresa = form.getIdEmpresa();
		String localidade = form.getLocalidade();
		String localidadeFinal = form.getLocalidadeFinal();
		String setorComercialInicial = form.getCodigoSetorComercialOrigem();
		String setorComercialFinal = form.getCodigoSetorComercialDestino();
		String imovel = form.getIdImovel();
		String ordemServico = form.getOrdemServico();
		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {
	
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idEmpresa));
	
			Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());
	
			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
				Empresa empresa = (Empresa) Util
						.retonarObjetoDeColecao(colecaoEmpresa);
				form.setIdEmpresa(empresa.getId().toString());
				form.setNomeEmpresa(empresa.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
			} else {
				form.setIdEmpresa("");
				form.setNomeEmpresa("EMPRESA INEXISTENTE");
	
				httpServletRequest.setAttribute("empresaInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
			}
	
		} else {
			form.setNomeEmpresa("");
		}
		if(localidade !=null && !localidade.trim().equals("") && !Util.verificaSeNumeroNatural(localidade)){
			throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", "Localidade Inicial");
		}
		if(localidadeFinal !=null && !localidadeFinal.trim().equals("") && !Util.verificaSeNumeroNatural(localidadeFinal)){
			throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", "Localidade Final");
		}
		if(localidade !=null && !localidade.trim().equals("")){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidade));

			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade local = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

				form.setLocalidade(local.getId().toString());
				form.setNomeLocalidade(local.getDescricao());
				form.setLocalidadeFinal(local.getId().toString());
				form.setNomeLocalidadeFinal(local.getDescricao());
				
			} else {
				form.setLocalidade("");
				form.setLocalidadeFinal("");
				form.setNomeLocalidade("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "localidade");
			}
		}
		
		if(localidadeFinal !=null && !localidadeFinal.trim().equals("")){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeFinal));

			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade local = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				
				form.setLocalidadeFinal(local.getId().toString());
				form.setNomeLocalidadeFinal(local.getDescricao());
				
			} else {
				form.setLocalidadeFinal("");
				form.setNomeLocalidadeFinal("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeFinalInexistente", true);				
			}
		}
		if(setorComercialInicial !=null && !setorComercialInicial.trim().equals("") && !Util.verificaSeNumeroNatural(setorComercialInicial)){
			throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", "Setor Inicial");
		}
		if(setorComercialFinal !=null && !setorComercialFinal.trim().equals("") && !Util.verificaSeNumeroNatural(setorComercialFinal)){
			throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", "Setor Final");
		}		
		if(setorComercialInicial !=null && !setorComercialInicial.trim().equals("")){
			if(localidade !=null && !localidade.trim().equals("")){
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, Integer.parseInt(localidade)));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,setorComercialInicial));
				
				Collection<SetorComercial> colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
					
					SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
							
					form.setCodigoSetorComercialOrigem(""+ setorComercial.getCodigo());
					form.setDescricaoSetorComercialOrigem(setorComercial.getDescricao());
					form.setCodigoSetorComercialDestino(""+ setorComercial.getCodigo());
					form.setDescricaoSetorComercialDestino(setorComercial.getDescricao());
					
				} else {
					form.setCodigoSetorComercialOrigem("");
					form.setDescricaoSetorComercialOrigem("SETOR COMERCIAL INEXISTENTE");
					form.setCodigoSetorComercialDestino("");
					httpServletRequest.setAttribute("setorComercialOrigemInexistente", true);				
				}
			}
			else{
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Localidade Inicial e Final");
			}
		}
		
		if(setorComercialFinal !=null && !setorComercialFinal.trim().equals("")){
			if(localidade !=null && !localidade.trim().equals("")){
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, Integer.parseInt(localidade)));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,setorComercialFinal));
				
				Collection<SetorComercial> colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
					
					SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
					
					form.setCodigoSetorComercialDestino(""+ setorComercial.getCodigo());
					form.setDescricaoSetorComercialDestino(setorComercial.getDescricao());
					
				} else {
					form.setCodigoSetorComercialDestino("");
					form.setDescricaoSetorComercialDestino("SETOR COMERCIAL INEXISTENTE");
					httpServletRequest.setAttribute("setorComercialDestinoInexistente", true);				
				}
			}
			else{
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Localidade Inicial e Final");
			}
		}
		
		if(imovel !=null && !imovel.trim().equals("")){
			Imovel imov = fachada.pesquisarImovel(Integer.parseInt(imovel));
			if(imov !=null){				
				form.setInscricaoImovel(imov.getInscricaoFormatada());
			}
			else{				
				form.setInscricaoImovel("IMÓVEL INEXISTENTE");
				httpServletRequest.setAttribute("imovelInexistente", true);
			}
		}
		if(ordemServico !=null &&!ordemServico.trim().equals("")){
			OrdemServico os = Fachada.getInstancia().recuperaOSPorId(Integer.parseInt(ordemServico));
			if(os !=null){
				form.setDescricaoOrdemServico(os.getServicoTipo().getDescricao());
			}
			else{
				form.setDescricaoOrdemServico("ORDEM DE SERVIÇO INEXISTENTE");
				httpServletRequest.setAttribute("ordemServicoNaoEncontrada", true);
			}
		}
	
	}
	
	private ActionForward pesquisarComandosOS(HttpServletRequest httpServletRequest,
			ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm form,
			Fachada fachada, HttpSession sessao, String pagina, ActionForward retorno) {

		String idEmpresa;
		String reqEmp = httpServletRequest.getParameter("idEmpresa");
		String actEmp = form.getIdEmpresa();
		
		if(actEmp !=null && !actEmp.trim().equals("")){
			idEmpresa = actEmp;
		}
		else{
			idEmpresa = reqEmp;
		}
		
		ActionForward retorno2 = new ActionForward();

		String periodoExecucaoInicial = form.getPeriodoExecucaoInicial();

		String periodoExecucaoFinal = form.getPeriodoExecucaoFinal();

		Date execucaoInicial = null;

		Date execucaoFinal = null;
		
		String localidade = httpServletRequest.getParameter("localidade");		
		String localidadeFinal = httpServletRequest.getParameter("localidadeFinal");
		String setorComercialInicial = httpServletRequest.getParameter("codigoSetorComercialOrigem");
		String setorComercialFinal = httpServletRequest.getParameter("codigoSetorComercialDestino");
		String imovel = form.getIdImovel();
		String ordemServico = form.getOrdemServico();
		
		Integer idLocalidadeInicial = null;
		Integer idLocalidadeFinal = null;
		Integer idSetorInicial = null;
		Integer idSetorFinal = null;
		Integer matriculaImovel = null;
		Integer idOrdemServico = null;
		
		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));

			Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa,Empresa.class.getName());

			if (Util.isVazioOrNulo(colecaoEmpresa)) {
				throw new ActionServletException("atencao.empresa.inexistente");
			}
		}else{
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Empresa");
		}
		if((periodoExecucaoInicial == null || periodoExecucaoInicial.equals("")) && 
			(periodoExecucaoFinal !=null && !periodoExecucaoFinal.equals(""))){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "o Período Inicial");
		}
		if((periodoExecucaoFinal == null || periodoExecucaoFinal.equals("")) && 
				(periodoExecucaoInicial !=null && !periodoExecucaoInicial.equals(""))){
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "o Período Final");
		}
		
		if (periodoExecucaoFinal != null && !periodoExecucaoFinal.equals("")
				&& periodoExecucaoInicial != null && !periodoExecucaoInicial.equals("")) {
								
				Boolean b1 = Util.verificaSeDataValida(periodoExecucaoInicial, "dd/MM/yyyy");
				
				if(b1){
					execucaoInicial = Util.converteStringParaDate(periodoExecucaoInicial);
					
					b1 = Util.verificaSeDataValida(periodoExecucaoFinal, "dd/MM/yyyy");
					if(b1){
						execucaoFinal = Util.converteStringParaDate(periodoExecucaoFinal);
					}
					else{
						throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Período Final Válido");
					}
				}
				else{
					throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Período Inicial Válido");
				}

				if (execucaoInicial !=null && execucaoFinal!=null && execucaoInicial.compareTo(execucaoFinal) > 0) {
					throw new ActionServletException("atencao.data_inicial_periodo_execucao.posterior.data_final_periodo_execucao");
				}

			}
		if(localidade !=null && !localidade.trim().equals("") && !Util.verificaSeNumeroNatural(localidade)){
			throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", "Localidade Inicial");
		}
		if(localidadeFinal !=null && !localidadeFinal.trim().equals("") && !Util.verificaSeNumeroNatural(localidadeFinal)){
			throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", "Localidade Final");
		}
		
		if((localidade == null || localidade.trim().equals("")) && (localidadeFinal !=null &&!localidadeFinal.trim().equals(""))){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Localidade Inicial");
		}		
		if((localidadeFinal == null || localidadeFinal.trim().equals("")) && (localidade !=null &&!localidade.trim().equals(""))){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Localidade Final");
		}		
		if(localidade !=null && !localidade.trim().equals("")){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidade));

			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {				
				throw new ActionServletException("atencao.pesquisa_inexistente", "Localidade Inicial");
			}
			else{
				Localidade local = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				idLocalidadeInicial = local.getId();
			}
		}
		
		if(localidadeFinal !=null && !localidadeFinal.trim().equals("")){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeFinal));

			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", "Localidade Final");				
			} 
			else{
				Localidade local = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				idLocalidadeFinal = local.getId();
			}
		}
		
		
		if(localidade !=null && !localidade.trim().equals("") &&
		   localidadeFinal !=null && !localidadeFinal.trim().equals("")){
			
			if(idLocalidadeInicial.compareTo(idLocalidadeFinal) > 0){
				throw new ActionServletException("atencao.localidade.inicial.maior.localidade.final");
			}
		}
		
		if(setorComercialInicial !=null && !setorComercialInicial.trim().equals("") && !Util.verificaSeNumeroNatural(setorComercialInicial)){
			throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", "Setor Inicial");
		}
		if(setorComercialFinal !=null && !setorComercialFinal.trim().equals("") && !Util.verificaSeNumeroNatural(setorComercialFinal)){
			throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", "Setor Final");
		}
		
		if((setorComercialInicial ==null || setorComercialInicial.trim().equals("")) && 
				(setorComercialFinal !=null && !setorComercialFinal.trim().equals(""))){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Setor Inicial");
		}
		if((setorComercialFinal ==null || setorComercialFinal.trim().equals("")) && 
				(setorComercialInicial !=null && !setorComercialInicial.trim().equals(""))){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Setor Final");
		}
		
		if(setorComercialInicial !=null && !setorComercialInicial.trim().equals("")){
			if(localidade !=null && !localidade.trim().equals("")){
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, Integer.parseInt(localidade)));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,setorComercialInicial));
				
				Collection<SetorComercial> colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {			
					throw new ActionServletException("atencao.pesquisa_inexistente", "Setor Inicial");
				}
				else{
					SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
					idSetorInicial = setorComercial.getCodigo();
				}
			}		
			else{
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Localidade Inicial e Final");
			}	
		}
		
		if(setorComercialFinal !=null && !setorComercialFinal.trim().equals("")){
			if(localidade !=null && !localidade.trim().equals("")){
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, Integer.parseInt(localidade)));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,setorComercialFinal));
				
				Collection<SetorComercial> colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if (colecaoSetorComercial == null || colecaoSetorComercial.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa_inexistente", "Setor Final");
				}
				else{
					SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
					idSetorFinal = setorComercial.getCodigo();
				}
			}else{
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Localidade Inicial e Final");
			}	
		}
		
		if(setorComercialInicial !=null && !setorComercialInicial.trim().equals("") &&
			setorComercialFinal !=null && !setorComercialFinal.trim().equals("")){
				
				if(idSetorInicial.compareTo(idSetorFinal) > 0){
					throw new ActionServletException("atencao.setor.comercial.final.maior.setor.comercial.inicial");
				}
		}
		if(imovel !=null && !imovel.trim().equals("")){
			Imovel imov = fachada.pesquisarImovel(Integer.parseInt(imovel));
			if(imov ==null){				
				throw new ActionServletException("atencao.pesquisa_inexistente", "Imóvel");
			}
			else{
				matriculaImovel = imov.getId();
			}
			
		}
		if(ordemServico !=null &&!ordemServico.trim().equals("")){
			OrdemServico os = Fachada.getInstancia().recuperaOSPorId(Integer.parseInt(ordemServico));
			if(os ==null){
				throw new ActionServletException("atencao.pesquisa_inexistente", "Ordem de Serviço");
			}
			else{
				idOrdemServico = os.getId();
			}
		}
		
		
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		int quantidadeRegistros = 10;
		/*Integer totalRegistros = fachada.pesquisarDadosComandoOSSeletivaCount(
				new Integer(idEmpresa), execucaoInicial, execucaoFinal);*/
		Integer totalRegistros = fachada.pesquisarDadosComandoOSSeletivaCount(new Integer(idEmpresa), execucaoInicial, execucaoFinal, idLocalidadeInicial, 
			idLocalidadeFinal, idSetorInicial, idSetorFinal, matriculaImovel, idOrdemServico);

		retorno2 = this.controlarPaginacao(httpServletRequest, retorno,	totalRegistros);
		
		/*Collection<ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper> colecaoConsultarComandosOSHelper = fachada
		.pesquisarDadosComandoOSSeletivaResumido(new Integer(idEmpresa),execucaoInicial, execucaoFinal,
				(Integer)httpServletRequest.getAttribute("numeroPaginasPesquisa"),quantidadeRegistros,
				sistemaParametro.getQtdeDiasValidadeOSAnormalidadeFiscalizacao(), false);*/
		
		Collection<ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper> colecaoConsultarComandosOSHelper = fachada
				.pesquisarDadosComandoOSSeletivaResumido(new Integer(idEmpresa),execucaoInicial, execucaoFinal,
			(Integer)httpServletRequest.getAttribute("numeroPaginasPesquisa"),quantidadeRegistros,
			sistemaParametro.getQtdeDiasValidadeOSAnormalidadeFiscalizacao(), false, idLocalidadeInicial, 
			idLocalidadeFinal, idSetorInicial, idSetorFinal, matriculaImovel, idOrdemServico);
				
		
		if(colecaoConsultarComandosOSHelper != null && !colecaoConsultarComandosOSHelper.isEmpty()){
			sessao.setAttribute("dataInicial",execucaoInicial);	
			sessao.setAttribute("dataFinal",execucaoFinal);	
			sessao.setAttribute("colecaoConsultarComandosOSHelper",	colecaoConsultarComandosOSHelper);
		}else{
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno2;
	}
}
