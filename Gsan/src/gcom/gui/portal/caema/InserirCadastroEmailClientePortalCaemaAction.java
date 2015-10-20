package gcom.gui.portal.caema;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.EmailClienteAlterado;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.FiltroEmailClienteAlterado;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [RM1906] Inserir Cadastro Email Cliente - Loja Virtual da CAEMA
 * 
 * @author Arthur Carvalho/Rafael Pinto
 * 
 * @date 17/02/2012
 */
public class InserirCadastroEmailClientePortalCaemaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("validarDados");

		InserirCadastroEmailClientePortalCaemaActionForm form = 
				(InserirCadastroEmailClientePortalCaemaActionForm) actionForm;
		
		EmailClienteAlterado emailClienteAlterado = new EmailClienteAlterado();
		
		httpServletRequest.setAttribute("voltarServicos", true);
		
		//Acessando direto pelo link, sem ser pelo link do email
		if (httpServletRequest.getParameter("confirmar") == null || 
			!httpServletRequest.getParameter("confirmar").equals("sim")) {
			
			//Valida o CPF/CNPJ do Solicitante
			if (form.getCpfSolicitante() != null && 
				!form.getCpfSolicitante().equals("")) {
				
				boolean valido = false;
				
				if (form.getCpfSolicitante().length() == 11) {
					valido = Util.validacaoCPF(form.getCpfSolicitante());
				} else if (form.getCpfSolicitante().length() == 14) {
					valido = Util.validacaoCNPJ(form.getCpfSolicitante());
				}
				
				if (!valido) {
					httpServletRequest.setAttribute("clienteCpfCnpjInvalido", true);
					
					return retorno;
				}

			} else {
				httpServletRequest.setAttribute("cpfCnpjObrigatorio", true);
				
				return retorno;
			}
			
			//Valida o email
			if (form.getEmail() != null && !form.getEmail().equals("")) {
				
				String email = form.getEmail();
				
				if (!email.contains("@") || email.contains(" ")) {
					httpServletRequest.setAttribute("emailInvalido", true);
					
					return retorno;
				}
			} else {
				httpServletRequest.setAttribute("usuarioEmailNulo", true);
				
				return retorno;
			}
			
			//Preparar dados para armazenar na tabela
			//sessao.setAttribute("matricula", matricula);
			Integer matriculaImovel = new Integer(form.getMatricula());
			
			Cliente clienteUsuario = this.getFachada().pesquisarClienteUsuarioImovel(matriculaImovel);
			
			String nomeSolicitante = form.getNomeSolicitante().toUpperCase();
			String cpfSolicitante = null;
			String cnpjSolicitante = null;
			String emailAtual = form.getEmail();
			
			if (form.getCpfSolicitante().length() == 11) {
				cpfSolicitante = form.getCpfSolicitante();
			}else if (form.getCpfSolicitante().length() == 14) {
				cnpjSolicitante = form.getCpfSolicitante();
			}
			
			emailClienteAlterado = 
				new EmailClienteAlterado(
					clienteUsuario, 
					nomeSolicitante, 
					cpfSolicitante, 
					emailAtual, 
					new Date());
			
			emailClienteAlterado.setCnpjSolicitante(cnpjSolicitante);
			
			//Seta o telefone para contato se o mesmo estiver preenchido
			if (form.getTelefoneContato() != null && !form.getTelefoneContato().equals("")) {
				
				String telefone = form.getTelefoneContato().trim();
				
				telefone = telefone.replace("(", "");
				telefone = telefone.replace(")", "");
				telefone = telefone.replace("-", "");
				telefone = telefone.replace(" ", "");
				
				emailClienteAlterado.setTelefoneContato(Util.converterStringParaInteger(telefone));
			}
			
			emailClienteAlterado.setUltimaAlteracao(new Date());
			
			if (httpServletRequest.getParameter("enviarEmail") == null || 
				!httpServletRequest.getParameter("enviarEmail").equals("sim")) {
				
				httpServletRequest.setAttribute("enviarConfirmacao", "OK");
			}
			
			if (httpServletRequest.getParameter("enviarEmail") != null && 
				httpServletRequest.getParameter("enviarEmail").equals("sim")) {
				
				retorno = actionMapping.findForward("emailCadastradoComSucesso");
				
				Integer idIncluido = (Integer)this.getFachada().inserir(emailClienteAlterado);
				
				EnvioEmail envioEmail = 
					this.getFachada().pesquisarEnvioEmail(EnvioEmail.INSERIR_CADASTRO_EMAIL_CLIENTE);
				
				String emailRemetente = envioEmail.getEmailRemetente();
				
				String tituloMensagem = "Cadastro de email para recebimento da fatura";
				String emailReceptor = form.getEmail();
				String mensagem = envioEmail.getCorpoMensagem();
				
				SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
				
				String urlAcessoInternet = sistemaParametro.getUrlAcessoInternet();
				
				String url = urlAcessoInternet 
					+ "/inserirCadastroEmailClientePortalCaemaAction.do?id=" 
					+ idIncluido
				    + "&confirmar=sim&idImovel=" 
					+ form.getMatricula();
				
				mensagem = mensagem
					+ " - Você acessou o site da Caema para cadastrar seu email e/ou alterar os dados cadastrais ? Caso positivo clique no link abaixo:  \n \n \n"
					+ url
					+ "\n\n\n Esse é um e-mail automático. Não é necessário respondê-lo.";

				try {
					
					ServicosEmail.enviarMensagem(emailRemetente, 
						emailReceptor, 
						tituloMensagem, 
						mensagem);
					
					httpServletRequest.setAttribute("emailCadastradoComSucesso", true);
					httpServletRequest.setAttribute("respostaCliente", "Você receberá um e-mail para confirmação dos dados alterados.");
							
				} catch (ErroEmailException erroEnviarEmail) {
					erroEnviarEmail.printStackTrace();
				}
				
			}
		}
		
		//Quando o usuário clicar no link enviado por email
		if (httpServletRequest.getParameter("confirmar") != null && 
			httpServletRequest.getParameter("confirmar").equals("sim")) {
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			if (httpServletRequest.getParameter("id") != null) {
				
				Integer idEmailConfirmar = 
					Util.converterStringParaInteger(httpServletRequest.getParameter("id"));
				
				FiltroEmailClienteAlterado filtroEmailCliente = new FiltroEmailClienteAlterado();
				filtroEmailCliente.adicionarParametro(
					new ParametroSimples(FiltroEmailClienteAlterado.ID, 
						idEmailConfirmar));
				
				Collection dadosCliente = 
					this.getFachada().pesquisar(filtroEmailCliente, 
						EmailClienteAlterado.class.getName());
				
				if (dadosCliente != null && !dadosCliente.isEmpty()) {
					
					EmailClienteAlterado dadoCliente = 
							(EmailClienteAlterado) Util.retonarObjetoDeColecao(dadosCliente);
					
					FiltroCliente filtroCliente = new FiltroCliente();
					filtroCliente.adicionarParametro(
						new ParametroSimples(FiltroCliente.ID, 
							dadoCliente.getIdCliente()));
					
					Collection clientes = 
						this.getFachada().pesquisar(filtroCliente, Cliente.class.getName());
					
					if (clientes != null && !clientes.isEmpty()) {
						
						Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(clientes);
						
						cliente.setEmail(dadoCliente.getEmailAtual());
						
						//Atualiza os dados do cliente que foram alterados
						this.getFachada().atualizar(cliente);
						
						FiltroImovel filtroImovel = new FiltroImovel();
						
						filtroImovel.adicionarParametro(
							new ParametroSimples(FiltroImovel.ID,
								httpServletRequest.getParameter("idImovel")));
						
						Collection imoveis = 
							this.getFachada().pesquisar(filtroImovel, Imovel.class.getName());
						
						if (imoveis != null && !imoveis.isEmpty()) {
							
							ImovelContaEnvio imovelContaEnvio = new ImovelContaEnvio();
							imovelContaEnvio.setId(ImovelContaEnvio.ENVIAR_PARA_IMOVEL_E_PARA_EMAIL);

							Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(imoveis);
							imovel.setImovelContaEnvio(imovelContaEnvio);
							
							this.getFachada().atualizar(imovel);
						}
						
						//Atualiza a hr da confirmacao e a ultima alteracao
						dadoCliente.setConfirmacaoOnline(new Date());
						dadoCliente.setUltimaAlteracao(new Date());
						
						this.getFachada().atualizar(dadoCliente);
						
						String mensagemSucesso = "Os dados do cliente "
							+ cliente.getNome().toUpperCase()
							+ " foram atualizados com sucesso.";

						sessao.setAttribute("mensagemSucesso", mensagemSucesso);
					}
				}
			}
		}
		// Contabiliza os servicos utilizados
		if (httpServletRequest.getParameter("lojaVirtual") != null) {
			String ip = httpServletRequest.getRemoteAddr();
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.RECEBIMENTO_FATURA_EMAIL, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		}

		return retorno;
	}
}