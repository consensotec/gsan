package gcom.gui.portal.caern;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirSolicitacaoServicosPortalCaernAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirInserirSolicitacaoServicosPortalCaern");

		HttpSession sessao = httpServletRequest.getSession(false);

		httpServletRequest.setAttribute("voltarServicos", true);

		Fachada fachada = Fachada.getInstancia();

		InserirSolicitacaoServicosPortalCaernActionForm form = (InserirSolicitacaoServicosPortalCaernActionForm) actionForm;
		
		String ip = httpServletRequest.getRemoteAddr(); 
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.OUTROS_SERVICOS, AcessoLojaVirtual.INDICADOR_SERVICO_NAO_EXECUTADO); 

		//Verifica se a tela foi chamada de fora da loja virtual
		if (httpServletRequest.getParameter("solicitacaoTp") != null && httpServletRequest.getParameter("solicitacaoTp").equals("vazamento")) {
			form.reset();
			sessao.setAttribute("solicitacaoTp", "vazamento");
			
			//Seleciona a Solicitacao Tipo 703 - Vazamento Ramal
//			form.setSolicitacaoTipo("703");
		}
		else if(sessao.getAttribute("solicitacaoTp") == null){
			// 2.1.3
			Integer matricula = (Integer) sessao.getAttribute("matricula");
			
			if (matricula == null || !Util.verificarIdNaoVazio(matricula.toString())) {
				httpServletRequest.setAttribute("exception", "Matrícula inválida ou vazia!");
				return retorno;
			}
			
			String enderecoFormatado = null;
			try {
				enderecoFormatado = this.getFachada().pesquisarEnderecoFormatado(matricula);
			} catch (ControladorException e) {
				httpServletRequest.setAttribute("exception", "Erro no sistema!");
				return retorno;
			}
			
			if (enderecoFormatado != null) {
				sessao.setAttribute("enderecoImovel", enderecoFormatado);
			}
		}
		
		if(httpServletRequest.getParameter("pesquisarMatricula") != null){			

			if (Util.verificarIdNaoVazio(form.getMatricula())) {
				
				try {
					Integer matricula = Integer.parseInt(form.getMatricula());
					Integer matriculaExistente = this.getFachada().verificarExistenciaImovel(matricula);
		
					if (matriculaExistente == 1 || this.getFachada().verificarExistenciaClienteVirtual(matricula)) {

						//Endereço do usuário
						String enderecoFormatado = "";
						try {
							enderecoFormatado = this.getFachada().pesquisarEnderecoFormatado(matricula);
						} catch (ControladorException e) {
							httpServletRequest.setAttribute("exception", "Erro no sistema!");
							return retorno;
						}
						if (enderecoFormatado != null) {
							sessao.setAttribute("enderecoImovel", enderecoFormatado);
						}
						
						//Nome do Solicitante
						Cliente cliente = fachada.pesquisarClienteUsuarioImovel(matricula);
						form.setNomeSolicitante(cliente.getNome());
						
					} else {
//						imovelInvalido = true;
						httpServletRequest.setAttribute("imovelInvalido", true);
					}
				} catch (NumberFormatException e) {
					httpServletRequest.setAttribute("nomeCampo", "matricula");
					httpServletRequest.setAttribute("imovelInvalido", true);
				}
			}else 
				if(sessao.getAttribute("solicitacaoTp") != null && sessao.getAttribute("solicitacaoTp").equals("vazamento")){
				form.setNomeSolicitante("");
				sessao.removeAttribute("enderecoImovel");
			}
		}
		
		if (httpServletRequest.getParameter("init") != null && httpServletRequest.getParameter("init").equals("1")) {
			form.reset();
			sessao.removeAttribute("dataPrevista");
		}
		
		sessao.setAttribute("dataSolicitacao", Util.formatarData(new Date()));

		// 2.2.4 Tipo Solicitação
		Collection<Object[]> objectsSolicitacaoTipo = fachada.pesquisarSolicitacaoTipoLojaVirtual();

		Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = new ArrayList<SolicitacaoTipo>();
		
		if (!Util.isVazioOrNulo(objectsSolicitacaoTipo)) {
			if(sessao.getAttribute("solicitacaoTp") == null){
				for (Object[] tipo : objectsSolicitacaoTipo) {
					SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
					solicitacaoTipo.setId(Integer.parseInt(((Short) tipo[0]).toString()));
					solicitacaoTipo.setDescricao((String) tipo[1]);
					
					colecaoSolicitacaoTipo.add(solicitacaoTipo);
				}
			}else{
				for (Object[] tipo : objectsSolicitacaoTipo) {
					if(tipo[0].toString().equals("701") || tipo[0].toString().equals("703")){
						SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
						solicitacaoTipo.setId(Integer.parseInt(((Short) tipo[0]).toString()));
						solicitacaoTipo.setDescricao((String) tipo[1]);
						
						colecaoSolicitacaoTipo.add(solicitacaoTipo);
					}
				}
			}
			
			sessao.setAttribute("colecaoSolicitacaoTipo", colecaoSolicitacaoTipo);
		} else {
			httpServletRequest.setAttribute("exception", "Nenhum tipo de solicitação encontrado!");
			return retorno;
		}
		
		// 2.2.5 Tipo de Especificação
		if (Util.verificarIdNaoVazio(form.getSolicitacaoTipo())) {
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_LOJA_VIRTUAL, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID, new Integer(form.getSolicitacaoTipo())));
			Collection<?> colecaoEspecificacao = fachada.pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			
			if (!Util.isVazioOrNulo(colecaoEspecificacao)) {
				sessao.setAttribute("colecaoEspecificacao", colecaoEspecificacao);
			} else {
				httpServletRequest.setAttribute("exception", "Nenhuma especificação encontrada!");
				return retorno;
			}
		}else{
			form.setEspecificacao(null);
			sessao.removeAttribute("colecaoEspecificacao");
		}
		
		// Calcula a data prevista para o atendimento a solicitação
		Date dataPrevista = null;
		if(Util.verificarIdNaoVazio(form.getEspecificacao())){
			DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper definirDataPrevistaUnidadeDestinoEspecificacaoHelper = fachada
					.definirDataPrevistaUnidadeDestinoEspecificacao(new Date(),
							Integer.parseInt(form.getEspecificacao()));

			dataPrevista = Util
					.formatarDataFinal(definirDataPrevistaUnidadeDestinoEspecificacaoHelper
							.getDataPrevista());
			
			sessao.setAttribute("dataPrevista", Util.formatarData(dataPrevista));
		}else{
			sessao.removeAttribute("dataPrevista");
		}
		
		if (form.getNomeSolicitante() == null)
			form.setNomeSolicitante("");
		if (form.getEmail() == null)
			form.setEmail("");
		if (form.getObservacoes() == null)
			form.setObservacoes("");
		if (form.getPontoReferencia() == null)
			form.setPontoReferencia("");
		
		return retorno;
	}

}
