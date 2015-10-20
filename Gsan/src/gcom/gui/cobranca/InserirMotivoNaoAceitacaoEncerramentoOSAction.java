package gcom.gui.cobranca;

import gcom.cobranca.MotivoNaoAceitacaoEncerramentoOS;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1171] Inserir Motivos de N�o Aceita��o de Encerramento de OS
 * 
 * Classe respons�vel por inserir na base de dados o motivo de n�o
 * aceita��o do encerramento da OS.
 * 
 * @author Diogo Peixoto
 * @since 20/05/2011
 *
 */

public class InserirMotivoNaoAceitacaoEncerramentoOSAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		InserirMotivoNaoAceitacaoEncerramentoOSActionForm form = (InserirMotivoNaoAceitacaoEncerramentoOSActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		//Recuperando os dados do formul�rio
		String descricao = form.getDescricao();
		String servicoDescontarCorteSupressao = form.getMultiplicadorValorServicoDescontarCorteSupressao();
		String servicoDescontarNaoExecutados = form.getMultiplicadorValorServicoDescontarNaoExecutados();
		String percentualMultaAplicar = form.getPercentualMultaAplicar();
		
		//Configurando os valores do objeto para ser inserido na base de dados
		MotivoNaoAceitacaoEncerramentoOS motivoNaoAceitacao = new MotivoNaoAceitacaoEncerramentoOS();
		
		if(Util.verificarNaoVazio(descricao)){
			if(descricao.length() <= 40){
				motivoNaoAceitacao.setDescricaoMotivoNaoAceitacaoEncerramentoOS(descricao);
			}else{
				throw new ActionServletException("atencao.execedeu_limit_observacao", "Descri��o", "40");
			}
		}else{
			throw new ActionServletException("atencao.campo.informada", null ,"Descri��o " );	
		}
		
		Short multiplicadorServicoDescontarCorteSupressao = null;
		if(servicoDescontarCorteSupressao != null && !servicoDescontarCorteSupressao.equals("")){
			try{
				multiplicadorServicoDescontarCorteSupressao = Short.valueOf(servicoDescontarCorteSupressao);
				motivoNaoAceitacao.setMultiplicadorValorServicoDescontarCorteSupressao(multiplicadorServicoDescontarCorteSupressao);
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.gsan.campo_formato_invalido", null ,
						"Multiplicador do valor de servi�o a ser descontado por servi�os n�o efetivamente executados" );	
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null ,
					"Multiplicador do valor de servi�o a ser descontado por corte ou supress�o" );	
		}
		
		Short multiplicadorServicoDescontarNaoExecutados;
		if(servicoDescontarNaoExecutados != null && !servicoDescontarNaoExecutados.equals("")){
			try{
				multiplicadorServicoDescontarNaoExecutados = Short.valueOf(servicoDescontarNaoExecutados);
				motivoNaoAceitacao.setMultiplicadorValorServicoDescontarNaoExecutados(multiplicadorServicoDescontarNaoExecutados);
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.gsan.campo_formato_invalido", null ,
						"Multiplicador do valor de servi�o a ser descontado por servi�os n�o efetivamente executados" );		
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null ,
					"Multiplicador do valor de servi�o a ser descontado por servi�os n�o efetivamente executados" );	
		}
		
		BigDecimal percentualMultaAplicarServicos = null;
		if(percentualMultaAplicar != null && !percentualMultaAplicar.equals("")){
			try{
				percentualMultaAplicarServicos = Util.formatarMoedaRealparaBigDecimal(percentualMultaAplicar);
				motivoNaoAceitacao.setPercentualMultaAplicar(percentualMultaAplicarServicos);
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.gsan.campo_formato_invalido", null ,
						"Percentual da multa a ser aplicada por servi�os n�o efetivamente executados" );	
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null , 
					"Percentual da multa a ser aplicada por servi�os n�o efetivamente executados");
		}
		
		// [UC0107] Registrar Transa��o
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_MOTIVO_NAO_ACEITACAO_ENCERRAMENTO_OS, motivoNaoAceitacao.getId(), motivoNaoAceitacao.getId(),
				new UsuarioAcaoUsuarioHelper(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		
		registradorOperacao.registrarOperacao(motivoNaoAceitacao);
		// [UC0107] Fim Registrar Transa��o 
		
		//FS0007 - Inserindo na base
		Integer id = (Integer) fachada.inserir(motivoNaoAceitacao);
			
		montarPaginaSucesso(httpServletRequest, "Motivo de n�o aceita��o do encerramento de OS "+ id + " inserido com sucesso.",
				"Inserir outro motivo de n�o aceita��o",
				"exibirInserirMotivoNaoAceitacaoEncerramentoOSAction.do?limpar=OK",
				"exibirAtualizarMotivoNaoAceitacaoEncerramentoOSAction.do?idRegistroAtualizar=" + id+"&primeiraVez=Ok",
				"Atualizar motivo de n�o aceita��o inserido");
		return retorno;
	}
}