package gcom.gui.portal.saae;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesAplicacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 *[RM2923] Inserir Solicitação de Servicos - Loja Virtual da Saae *
 *[UC1189] Inserir Registro de Atendimento Loja Virtual
 * 
 * Gerar Registro de Atendimento 
 * 
 * @author Cesar Medeiros
 * @date 28/09/2015
 * 
 */
public class InserirSolicitacaoServicosPortalSaaeAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("validarDados");

		Fachada fachada = Fachada.getInstancia();

		InserirSolicitacaoServicosPortalSaaeActionForm form = (InserirSolicitacaoServicosPortalSaaeActionForm) actionForm;
		
		Imovel imovel = null;
		if(Util.verificarIdNaoVazio(form.getMatricula())){
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getMatricula()));
			
			Collection<?> colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			if(!Util.isVazioOrNulo(colecaoImoveis)){
				imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
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
		
		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.IMOVEL_ID, form.getMatricula()));
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID_SOLICITACAO_TIPO_ESPECIFICACAO, form.getEspecificacao()));
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.CODIGO_SITUACAO, RegistroAtendimento.SITUACAO_PENDENTE));
		Collection<?> colecaoRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName());

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
			
			Integer idUnidadeOrganizacional = null;
			if(!Util.isVazioOrNulo(colecaoUnidadeOrganizacional)){
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
				idUnidadeOrganizacional = unidadeOrganizacional.getId();
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
			Imovel imovelEndereco = fachada.pesquisarImovelParaEndereco(imovel.getId());
			colecaoEndereco.add(imovelEndereco);
			
			Date date = new Date();
			
			String observacao = form.getObservacoes().replace("\r\n", "\n");
			if(observacao.length() > 400){
				observacao = observacao.substring(0, 400);
			}				
			
			String pontoReferencia = form.getPontoReferencia().replace("\r\n", " ");
			if(pontoReferencia.length() > 60){
				pontoReferencia = pontoReferencia.substring(0, 60);
			}
			
			try {
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imovel.getId()));
				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, 2));
				filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
				
				ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName()));
				
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
				
				
				this.getFachada().validarCamposObrigatoriosRA_2ABA(
					""+imovel.getId(),	//Id do imóvel 
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
					idUnidadeOrganizacional.toString(),	//Id da unidade
					null,		//Descrição da unidade
					null, 		//Id local de ocorrencia
					null, 		//Id pavimento rua
					null, 		//Id pavimento calcada
					null, 		//Descrição local ocorrencia
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
				
				
				this.getFachada().inserirRegistroAtendimento(ConstantesSistema.SIM, //indicadorAtendimentoOnLine
					   Util.formatarData(date), //dataAtendimento
					   Util.formatarHoraSemData(date), //horaAtendimento
					   null, //tempoEsperaInicial
					   null, //tempoEsperaFinal
					   MeioSolicitacao.INTERNET, //idMeioSolicitacao
					   new Integer(form.getEspecificacao()), //idSolicitacaoTipoEspecificacao
					   Util.formatarData(dataPrevista), //dataPrevista 
					   observacao, //observacao
					   imovel.getId(), //idImovel
					   null, //descricaoLocalOcorrencia
					   new Integer(form.getSolicitacaoTipo()), //idSolicitacaoTipo
					   colecaoEndereco, //colecaoEndereco
					   null, //pontoReferenciaLocalOcorrencia
					   null, //idBairroArea
					   imovel.getLocalidade().getId(), //idLocalidade 
					   imovel.getSetorComercial().getId(), //idSetorComercial
					   imovel.getQuadra().getId(), //idQuadra 
					   null, //idDivisaoEsgoto
					   null, //idLocalOcorrencia
					   null, //idPavimentoRua 
					   null, //idPavimentoCalcada
					   idUnidadeOrganizacional, //idUnidadeAtendimento 
					   idUsuarioLogado, //idUsuarioLogado 
					   clienteImovel.getCliente().getId(), //idCliente
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
		
		// Contabiliza os servicos utilizados
		if (httpServletRequest.getParameter("lojaVirtual") != null) {
			String ip = httpServletRequest.getRemoteAddr();
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.OUTROS_SERVICOS, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		}
		
		return retorno;
	}
}