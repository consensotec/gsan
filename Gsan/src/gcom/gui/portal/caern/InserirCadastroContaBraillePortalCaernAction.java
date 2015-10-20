package gcom.gui.portal.caern;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.ContaBraile;
import gcom.cadastro.ContaBraileHelper;
import gcom.cadastro.cliente.FiltroOrgaoExpedidorRg;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1128] Solicitar Conta Braille 
 *  
 * @author Cesar Medeiros
 * @date 25/05/2015
 */
public class InserirCadastroContaBraillePortalCaernAction extends GcomAction {
	
	private static final String EXCEPTION = "exception";
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("validarDados");
		
		Fachada fachada = Fachada.getInstancia();
		
		httpServletRequest.setAttribute("voltarServicos", true);
		
		InserirCadastroContaBraillePortalCaernActionForm form = (InserirCadastroContaBraillePortalCaernActionForm) actionForm;
	
		ContaBraileHelper contaBraileHelper = new ContaBraileHelper();
		
		FiltroOrgaoExpedidorRg filtroOrgaoExpedidor = new FiltroOrgaoExpedidorRg(FiltroOrgaoExpedidorRg.DESCRICAO_ABREVIADA);	
		filtroOrgaoExpedidor.adicionarParametro(new ParametroSimples(FiltroOrgaoExpedidorRg.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection orgaosExpedidores = fachada.pesquisar(filtroOrgaoExpedidor, OrgaoExpedidorRg.class.getName());
		httpServletRequest.setAttribute("orgaosExpedidores", orgaosExpedidores);
		
		FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao(FiltroUnidadeFederacao.SIGLA);
		Collection unidadesFederacao = fachada.pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class.getName());		
		httpServletRequest.setAttribute("unidadesFederacao", unidadesFederacao);
		
		if(httpServletRequest.getAttribute("cpfInvalido") != null){
			httpServletRequest.removeAttribute("cpfInvalido");
		}
		if(httpServletRequest.getAttribute("cpfJaExiste") != null){
			httpServletRequest.removeAttribute("cpfJaExiste");
		}
		if(httpServletRequest.getAttribute("cpfObrigatorio") != null){
			httpServletRequest.removeAttribute("cpfObrigatorio");
		}
		
		//Acessando direto pelo link, sem ser pelo link do email
		if (httpServletRequest.getParameter("confirmar") == null
				|| !httpServletRequest.getParameter("confirmar").equals("sim")) {
			
			//	Valida o email			
			if (form.getEmail() != null && !form.getEmail().equals("")) {	
				String email = form.getEmail();			
				if (!email.matches("[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+")) {
					httpServletRequest.setAttribute(EXCEPTION, "E-mail inválido!");
					return retorno;
				}
			} else {
				httpServletRequest.setAttribute(EXCEPTION, "E-mail é obrigatório!");
				return retorno;
			}
			
			if ( httpServletRequest.getParameter("possuiDocumento") != null &&
					(!httpServletRequest.getParameter("possuiDocumento").equals("true")
							|| httpServletRequest.getParameter("possuiDocumento").equals("false"))){

				if( form.getConfirmarCpfCnpjCliente() == null ||
						!form.getConfirmarCpfCnpjCliente().equals("confirmado") ){
					httpServletRequest.setAttribute(EXCEPTION, "É necessário confirma o CPF/CNPJ do cliente!");
					return retorno;
				}
			}

			// Valida o CPF do Solicitante
			if (Util.verificarNaoVazio(form.getCpfSolicitante())) {
				if (form.getCpfSolicitante().length() < 11) {
					httpServletRequest.setAttribute(EXCEPTION, "CPF/CNPJ não é válido!");
					return retorno;
				}
				//É um CPF
				boolean valido = false;

				if(form.getCpfSolicitante().length() == 11){
					valido = Util.validacaoCPF(form.getCpfSolicitante());
				}else{
					valido = Util.validacaoCNPJ(form.getCpfSolicitante());
				}
				
				if (!valido) {	
					httpServletRequest.setAttribute(EXCEPTION, "CPF/CNPJ não é válido!");
					return retorno;
				}
			} else {
				httpServletRequest.setAttribute(EXCEPTION, "CPF/CNPJ é obrigatório!");
				return retorno;
			}
					
			// Montar Helper
			contaBraileHelper.setMatricula(form.getMatricula());
			contaBraileHelper.setCpfCnpjCliente(form.getCpfCnpjCliente());
			contaBraileHelper.setCpfSolicitante(form.getCpfSolicitante());
			contaBraileHelper.setEmail(form.getEmail());
			contaBraileHelper.setNomeCliente(form.getNomeCliente());
			contaBraileHelper.setNomeSolicitante(form.getNomeSolicitante());
			contaBraileHelper.setOrgaoExpeditor(form.getOrgaoExpeditor());
			contaBraileHelper.setRg(form.getRg());
			
			if(form.getTelefoneContato() != null && form.getTelefoneContato().length() == 14){
				String telefone = form.getTelefoneContato().trim();
				telefone = telefone.replace("(", "");
				telefone = telefone.replace(")", "");
				telefone = telefone.replace("-", "");
				telefone = telefone.replace(" ", "");
				contaBraileHelper.setTelefoneContato(telefone);
			}else{
				httpServletRequest.setAttribute(EXCEPTION, "Numero de telefone inválido.");
				return retorno;
			}
			
			Date dataExpedicao = null;
			if(form.getDataExpedicao() != null && Util.validarDataValida(form.getDataExpedicao(), "dd/MM/yyyy")){				
				dataExpedicao = Util.converteStringParaDate(form.getDataExpedicao());
				if(Util.compararDataTime(dataExpedicao,new Date()) == ContaBraile.DATA_EXPEDICAO_MAIOR_QUE_A_VIRGENTE){		
					httpServletRequest.setAttribute(EXCEPTION, "Data de expedicão inválida.");
					return retorno;
				}else{				
					contaBraileHelper.setDataExpedicao(Util.formatarData(dataExpedicao));
				}				
			}else{
				httpServletRequest.setAttribute(EXCEPTION, "Data de expedicão inválida.");
				return retorno;
			}		
			
			contaBraileHelper.setUnidadeFederacao(form.getUnidadeFederacao());
			contaBraileHelper.setIndicadorCpf(form.isIndicadorCpf());
			contaBraileHelper.setIndicadorCnpj(form.isIndicadorCnpj());
			
			String protocolo = this.getFachada().obterProtocoloAtendimento();
			contaBraileHelper.setProtocoloAtendimento(protocolo);
			
			this.getFachada().inserirSolicitacaoContaBraile(contaBraileHelper);
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			httpServletRequest.setAttribute("contaBrailleSolicitadaComSucesso", true);
			
			String mensagem = "";
			
			if(sistemaParametro.getMensagemContaBraile() != null){
				mensagem = sistemaParametro.getMensagemContaBraile() + ". ";
			}
			
			httpServletRequest.setAttribute("mensagemBrailleSolicitadaComSucesso", mensagem
					+ "Registro Atendimento: " + protocolo + ".");
			
			retorno = actionMapping.findForward("contaBrailleSolicitadaComSucesso");	
		}
		
		//Contabiliza os servicos utilizados
		if(httpServletRequest.getParameter("lojaVirtual") != null){
			String ip = httpServletRequest.getRemoteAddr();
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.SOLICITAR_CONTA_BRAILLE, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		}		
		return retorno;
	}
}

