package gsan.gui.portal.caern;

import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.atendimentopublico.portal.AcessoLojaVirtual;
import gsan.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gsan.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.MeioSolicitacao;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gsan.cadastro.cliente.ClienteFone;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cadastro.cliente.FoneTipo;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesAplicacao;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * <p>
 * <b>[RM2923]</b> Inserir Solicitação de Servicos - Loja Virtual da Caern
 * </p>
 * <p>
 * <b>[UC1189]</b> Inserir Registro de Atendimento Loja Virtual</b>
 * </p>
 * <p>
 * Gerar Registro de Atendimento
 * </p>
 * 
 * @author Rafael Pinto
 * @date 22/07/2013
 * 
 */
public class InserirSolicitacaoServicosPortalCaernAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("validarDados");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		InserirSolicitacaoServicosPortalCaernActionForm form = (InserirSolicitacaoServicosPortalCaernActionForm) actionForm;
		
		Imovel imovel = null;
		Integer idLocalidade = null;
		Integer idSetorComercial = null;
		Integer idQuadra = null;
		Integer matricula = null;
		String matriculaString = "";
		Integer idPavimentoRua = null;
		Integer idPavimentoCalcada = null;
		String idPavimentoRuaString = "";
		String idPavimentoCalcadaString = "";
		if(form.getMatricula() != null && Util.verificarIdNaoVazio(form.getMatricula())){
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getMatricula()));
			
			Collection<?> colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			if(!Util.isVazioOrNulo(colecaoImoveis)){
				imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
				matricula		 = imovel.getId(); 
				matriculaString  = imovel.getId().toString();
				idLocalidade 	 = imovel.getLocalidade().getId();
				idSetorComercial = imovel.getSetorComercial().getId();
				idQuadra 		 = imovel.getQuadra().getId();
				
				if(imovel.getPavimentoRua() != null && imovel.getPavimentoRua().getId() != null){
					idPavimentoRua 	 = imovel.getPavimentoRua().getId();
					idPavimentoRuaString = imovel.getPavimentoRua().getId().toString();
				}
				
				if(imovel.getPavimentoCalcada() != null && imovel.getPavimentoCalcada().getId() != null){
					idPavimentoCalcada 	 = imovel.getPavimentoCalcada().getId();
					idPavimentoCalcadaString = imovel.getPavimentoCalcada().getId().toString();
				}
				
			}
		}
		
		if(sessao.getAttribute("solicitacaoTp") != null && sessao.getAttribute("solicitacaoTp").equals("vazamento")){
			
			//Matricula ou LocalOcorrencia
			if (!Util.verificarIdNaoVazio(form.getMatricula()) && !Util.verificarIdNaoVazio(form.getLocalOcorrencia())){
				httpServletRequest.setAttribute("exception", "Selecione o imóvel ou local de ocorrência.");
				return retorno;
			}	
		}
		
		// 2.2.1
		if (!Util.verificarNaoVazio(form.getNomeSolicitante())) {
			httpServletRequest.setAttribute("exception", "O nome do solicitante é obrigatório!");
			return retorno;
		}
		
		// 2.2.2
		if(!Util.verificarNaoVazio(form.getTelefoneContato())){
			httpServletRequest.setAttribute("exception", "O telefone de contato é obrigatório!");
			return retorno;
		}
		
		// 2.2.4
		if(!Util.verificarIdNaoVazio(form.getSolicitacaoTipo())){
			httpServletRequest.setAttribute("exception", "O tipo de solicitação é obrigatório!");
			return retorno;
		}
		
		if(!Util.verificarNaoVazio(form.getPontoReferencia())){
			httpServletRequest.setAttribute("exception", "O ponto de referência é obrigatório!");
			return retorno;
		}
		
		// 2.2.5
		Date dataPrevista = null;
		if (Util.verificarIdNaoVazio(form.getEspecificacao())) {
			DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper definirDataPrevistaUnidadeDestinoEspecificacaoHelper = fachada
					.definirDataPrevistaUnidadeDestinoEspecificacao(new Date(),
							Integer.parseInt(form.getEspecificacao()));

			dataPrevista = Util
					.formatarDataFinal(definirDataPrevistaUnidadeDestinoEspecificacaoHelper
							.getDataPrevista());
		} else {
			httpServletRequest.setAttribute("exception", "A especificação é obrigatória!");
			return retorno;
		}
		
		// 2.2.6
		if (!Util.verificarNaoVazio(form.getEmail())) {
			httpServletRequest.setAttribute("exception", "O e-mail do solicitante é obrigatório!");
			return retorno;
		}
		
		// 2.2.7
		if(form.getPontoReferencia() != null && !form.getPontoReferencia().equals("")){
			if(form.getPontoReferencia().replace("\r\n", "\n").length() > 60){
				httpServletRequest.setAttribute("exception", "Ponto de Referência deve ter no máximo 60 caracteres");
				return retorno;
			}
		}
		
		// 2.2.8
		if(form.getObservacoes() != null && !form.getObservacoes().equals("")){
			if(form.getObservacoes().replace("\r\n", "\n").length() > 400){
				httpServletRequest.setAttribute("exception", "Observações deve ter no máximo 400 caracteres");
				return retorno;
			}
		}
		
		Collection<?> colecaoRegistroAtendimento = null;		
		if(matricula != null){
			FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.IMOVEL_ID, matricula));
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID_SOLICITACAO_TIPO_ESPECIFICACAO, form.getEspecificacao()));
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.CODIGO_SITUACAO, RegistroAtendimento.SITUACAO_PENDENTE));
			colecaoRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName());
		}
		
		Usuario usuarioLogado = null;
		if (Util.isVazioOrNulo(colecaoRegistroAtendimento)) {

			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.INDICADOR_USUARIO_INTERNET, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
			
			usuarioLogado = 
				(Usuario) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroUsuario, Usuario.class.getName()));
			
			Integer idUsuarioLogado = (usuarioLogado != null)? usuarioLogado.getId() : null;
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(FiltroUnidadeOrganizacional.CODIGO_CONSTANTE, 
					1));
			
			Collection<?> colecaoUnidadeOrganizacional = 
				fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			String idUnidadeOrganizacionalString = "";
			Integer idUnidadeOrganizacional = null;
			if(!Util.isVazioOrNulo(colecaoUnidadeOrganizacional)){
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
				if(unidadeOrganizacional.getId() != null){
					idUnidadeOrganizacionalString = unidadeOrganizacional.getId().toString();
					idUnidadeOrganizacional = unidadeOrganizacional.getId();
				}
			}
			
			Collection<ClienteFone> colecaoTelefone = null;
			if(form.getTelefoneContato() != null && !form.getTelefoneContato().trim().equals("")){
				colecaoTelefone = new ArrayList<ClienteFone>();
				
				FoneTipo foneTipo = new FoneTipo();
				foneTipo.setId(FoneTipo.RESIDENCIAL);
				
				String telefone = form.getTelefoneContato().trim();
				telefone = telefone.replace("(", "");
				telefone = telefone.replace(")", "");
				telefone = telefone.replace("-", "");
				telefone = telefone.replace(" ", "");
			
				String ddd = telefone.substring(0, 2);
				String numeroTelefone = telefone.substring(2);
				
				ClienteFone clienteFone = new ClienteFone();
				clienteFone.setFoneTipo(foneTipo);
				clienteFone.setDdd(ddd);
				clienteFone.setTelefone(numeroTelefone);
				
				colecaoTelefone.add(clienteFone);
			}
			
			Collection<Imovel> colecaoEndereco = new ArrayList<Imovel>();
			
			if(matricula != null){
				Imovel imovelEndereco = fachada.pesquisarImovelParaEndereco(matricula);
				colecaoEndereco.add(imovelEndereco);
			}
			
			Date date = new Date();
			
			String observacao = form.getObservacoes().replace("\r\n", "\n");
			if(observacao.length() > 400){
				observacao = observacao.substring(0, 400);
			}				
			
			String pontoReferencia = form.getPontoReferencia().replace("\r\n", " ");
			if(pontoReferencia.length() > 60){
				pontoReferencia = pontoReferencia.substring(0, 60);
			}
			
			String localOcorrencia = "";
			if ( form.getLocalOcorrencia() != null ) {
				localOcorrencia = form.getLocalOcorrencia().toUpperCase();
			}
			
			try {
				
				Integer idCliente  = null;
				if(matricula != null){
					FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
					filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, matricula));
					filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, 2));
					filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
					
					ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName()));
					idCliente = clienteImovel.getCliente().getId();
				}
				
				String protocolo = this.getFachada().obterProtocoloAtendimento();
				
				
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
				filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,  new Integer(form.getEspecificacao())));
				filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO);
				Collection<SolicitacaoTipoEspecificacao> colecaoEspecificacao = fachada.pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
				
				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoEspecificacao);
				ServicoTipo servicoTipo = solicitacaoTipoEspecificacao.getServicoTipo();
				
				Integer idServicoTipo = null;
				if ( servicoTipo != null && servicoTipo.getId() != null ) {
					idServicoTipo = servicoTipo.getId();
				} 
				
				if(sessao.getAttribute("solicitacaoTp") == null){
					
					this.getFachada().validarCamposObrigatoriosRA_2ABA(
						matriculaString,	//Id do imóvel 
						pontoReferencia,//Ponto de referência
						null, 			//Id municipio
						null,			//Descricação municipio 
						null,			//Código do bairro 
						null,			//Decrição do bairro
						null, 			//Id area bairro
						null, 			//Id localidade
						null,			//Descrição localidade
						null, 			//Código Setor
						null, 			//Descrição Setor
						null, 			//Numero Quadra
						null, 			//Id Divisão esgoto
						idUnidadeOrganizacionalString,	//Id da unidade
						null,		//Descrição da unidade
						null, 		//Id local de ocorrencia
						idPavimentoRuaString, //Id pavimento rua
						idPavimentoCalcadaString, //Id pavimento calcada
						localOcorrencia, //Descrição local ocorrencia
						null, 		//Imóvel obrigatório
						null, 		//Pavimento rua obrigatório
						null,		//Pavimento calcada obrigatorio
						null,		//solicitacaoTipoRelativoFaltaAgua
						null, 		//solicitacaoTipoRelativoAreaEsgoto
						null,		//desabilitarMunicipioBairro
						null, 		//indRuaLocalOcorrencia
						null, 		//indCalcadaLocalOcorrencia
						solicitacaoTipoEspecificacao.getId(),//Id da especificação 
						null, 							//idRAAtualizacao
						null,							//Colecao endereços
						solicitacaoTipoEspecificacao,	//Especificação
						null,							//Colecao de pagamentos
						usuarioLogado);					
				}
				
				
				this.getFachada().inserirRegistroAtendimento(ConstantesSistema.SIM, //indicadorAtendimentoOnLine
					   Util.formatarData(date), //dataAtendimento
					   Util.formatarHoraSemData(date), //horaAtendimento
					   null, //tempoEsperaInicial
					   null, //tempoEsperaFinal
					   MeioSolicitacao.INTERNET, //idMeioSolicitacao
					   new Integer(form.getEspecificacao()), //idSolicitacaoTipoEspecificacao
					   Util.formatarData(dataPrevista), //dataPrevista 
					   observacao, //observacao
					   matricula, //idImovel
					   localOcorrencia, //descricaoLocalOcorrencia
					   new Integer(form.getSolicitacaoTipo()), //idSolicitacaoTipo
					   colecaoEndereco, //colecaoEndereco
					   null, //pontoReferenciaLocalOcorrencia
					   null, //idBairroArea
					   idLocalidade, //idLocalidade 
					   idSetorComercial, //idSetorComercial
					   idQuadra, //idQuadra 
					   null, //idDivisaoEsgoto
					   null, //idLocalOcorrencia
					   idPavimentoRua, //idPavimentoRua 
					   idPavimentoCalcada, //idPavimentoCalcada
					   idUnidadeOrganizacional, //idUnidadeAtendimento 
					   idUsuarioLogado, //idUsuarioLogado 
					   idCliente, //idCliente
					   pontoReferencia, //pontoReferenciaSolicitante 
					   form.getNomeSolicitante(), //nomeSolicitante
					   false, //novoSolicitante
					   null, //idUnidadeSolicitante
					   null, //idFuncionario
					   colecaoTelefone, //colecaoFone
					   null, //colecaoEnderecoSolicitante 
					   null, //idUnidadeDestino
					   null, //parecerUnidadeDestino 
					   idServicoTipo, //idServicoTipo
					   null, //numeroRAManual 
					   null, //idRAJAGerado 
					   null, //nnCoordenadaNorte
					   null, //nnCoordenadaLeste 
					   ConstantesSistema.NAO, //indicCoordenadaSemLogradouro
					   null, //colecaoRegistroAtendimentoAnexo
					   protocolo, //protocoloAtendimento
					   null, //colecaoContas
					   observacao, //observacaoOS
					   null, //colecaoPagamentos
					   null, //habilitarCampoSatisfacaoEmail 
					   null, //enviarEmailSatisfacao 
					   form.getEmail(), //enderecoEmail
					   null);
				
				httpServletRequest.setAttribute("RASolicitadaComSucesso", true);
				httpServletRequest.setAttribute("mensagemRA", protocolo);
				httpServletRequest.setAttribute("dataPrevistaAtendimentoRA", Util.formatarData(dataPrevista));
				retorno = actionMapping.findForward("RASolicitadaComSucesso");
				
			} catch (Exception e) {
				String mensagem = "ERRO DE SISTEMA";
				
				if(e.getMessage() != null){
					mensagem = ConstantesAplicacao.get(e.getMessage());	
				}
				
				
				httpServletRequest.setAttribute("exception", "Não foi possível inserir a solicitação: \n" + mensagem);
				e.printStackTrace();
			}
		} else {
			RegistroAtendimento registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
			
			FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimentoSolicitante = new FiltroRegistroAtendimentoSolicitante();
			filtroRegistroAtendimentoSolicitante.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));
			
			RegistroAtendimentoSolicitante registroAtendimentoSolicitante = (RegistroAtendimentoSolicitante) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroRegistroAtendimentoSolicitante, RegistroAtendimentoSolicitante.class.getName()));
			
			httpServletRequest.setAttribute("voltarServicos", true);
			httpServletRequest.setAttribute("RAJaSolicitada", true);
			httpServletRequest.setAttribute("mensagemRA", registroAtendimentoSolicitante.getNumeroProtocoloAtendimento());
			httpServletRequest.setAttribute("dataPrevistaAtendimentoRA", Util.formatarData(registroAtendimento.getDataPrevistaAtual()));
		}
		
		//Contabiliza os servicos utilizados
		if(httpServletRequest.getParameter("lojaVirtual") != null){
			String ip = httpServletRequest.getRemoteAddr(); 
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.OUTROS_SERVICOS, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO); 
		}
		
		return retorno;
	}
}