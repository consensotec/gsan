package gcom.gui.atualizacaocadastral;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atualizacaocadastral.ClienteAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ClienteFoneAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.FiltroLogradouroAtlzCadDM;
import gcom.atualizacaocadastral.HidrometroInstalacaoHistoricoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ImovelAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ImovelFotoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ImovelSubcategoriaAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.LogradouroAtlzCadDM;
import gcom.cadastro.cliente.FiltroFoneTipo;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.FiltroEnderecoReferencia;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarDadosImovelAmbienteVirtualPopupAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("consultarDadosImovelAmbienteVirtualPopup");

		Fachada fachada = this.getFachada();
		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarDadosImovelAmbienteVirtualPopupActionForm form =
			(ConsultarDadosImovelAmbienteVirtualPopupActionForm) actionForm;

		// RECUPERA  DADOS DOS POPUPS
		if (httpServletRequest.getParameter("idCampoEnviarDados") != null) {
			String idImovel = httpServletRequest.getParameter("idCampoEnviarDados");

			Imovel imovel = fachada.pesquisarImovel(Integer.valueOf(idImovel));

			if (imovel != null) {
				form.setIdImovel(String.valueOf(imovel.getId()));
				form.setInscricaoImovel(imovel.getInscricaoFormatada());
			} else {
				httpServletRequest.setAttribute("imovelInexistente", true);
				form.setIdImovel("");
				form.setInscricaoImovel("IMOVEL INEXISTENTE");
			}
		} else if (httpServletRequest.getParameter("objetoConsulta") != null) {
			Imovel imovel = fachada.pesquisarImovel(Integer.valueOf(form.getIdImovel()));

			if (imovel != null) {
				form.setIdImovel(String.valueOf(imovel.getId()));
				form.setInscricaoImovel(imovel.getInscricaoFormatada());
			} else {
				httpServletRequest.setAttribute("imovelInexistente", true);
				form.setIdImovel("");
				form.setInscricaoImovel("IMOVEL INEXISTENTE");
			}
		} else {
			form.limpar();
			sessao.removeAttribute("colecaoFoto");
		}

		if ( httpServletRequest.getParameter("idImovelAtualizacaoCadastral") != null || form.getIdImovelAtualizacaoCadastral() != null ) {
			if(httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("pesquisarImovel") ) {
				form.setIndicadorPesquisarImovel("1");
				if(httpServletRequest.getParameter("icPregsan")!=null){
					httpServletRequest.setAttribute("icPregsan", true);
				}
				
			} else if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("desabilitar") ) {
				form.setIndicadorPesquisarImovel("2");
			}

			Integer idImovel = null;
			if ( httpServletRequest.getParameter("idImovelAtualizacaoCadastral") != null ) {
				if(httpServletRequest.getParameter("idImovelAtualizacaoCadastral").contains(".do")){
					idImovel =Integer.parseInt(httpServletRequest.getParameter("idImovelAtualizacaoCadastral").replace(".do", ""));
					form.setIdImovelAtualizacaoCadastral(String.valueOf(idImovel));
					form.setIndicadorPesquisarImovel("1");
				}else{					
					idImovel = Integer.parseInt(httpServletRequest.getParameter("idImovelAtualizacaoCadastral"));
					form.setIdImovelAtualizacaoCadastral(String.valueOf(idImovel));
				}
			} else {
				idImovel = Integer.valueOf(form.getIdImovelAtualizacaoCadastral());
			}

			List<CadastroOcorrencia> listOcorrencia = fachada.obterOcorrenciasImovelAtualizacaoCadastral(idImovel);
			if (listOcorrencia.isEmpty()) {
				httpServletRequest.getSession().removeAttribute("listaCadastroOcorrencia");
			} else {
				httpServletRequest.getSession().setAttribute("listaCadastroOcorrencia", listOcorrencia);
				form.setDescricaoOcorrencia(listOcorrencia.get(0).getDescricao());
			}

			ImovelAtualizacaoCadastralDM imovel = fachada.pesquisarImovelAtualizacaoCadastralDM(idImovel);
			this.pesquisarFotos(idImovel, sessao);
			List<ClienteAtualizacaoCadastralDM> listCliente = fachada.pesquisarClienteAtualizacaoCadastralDM(idImovel);

			if(listCliente != null){
				for (ClienteAtualizacaoCadastralDM cliente : listCliente) {
					String telefones = "";

					List<ClienteFoneAtualizacaoCadastralDM> listFone = fachada.obterDadosClienteFoneAtualizacaoCadastralDM(cliente.getId());

					if(listFone != null && !listFone.isEmpty()) {
						ClienteFoneAtualizacaoCadastralDM fone = (ClienteFoneAtualizacaoCadastralDM)
								Util.retonarObjetoDeColecao(listFone);

						// Caso seja usuário, insere os dados no form, para ser exibido no relatorio
						if(cliente.getClienteRelacaoTipo() != null && cliente.getClienteRelacaoTipo().getId() == 2) {
							for(ClienteFoneAtualizacaoCadastralDM foneAtlzCad : listFone) {
								FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();
								filtroFoneTipo.adicionarParametro( new ParametroSimples(FiltroFoneTipo.ID,
										foneAtlzCad.getFoneTipo().getId()));

								FoneTipo foneTipo = (FoneTipo) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroFoneTipo, FoneTipo.class.getName()));

								if(foneTipo != null){
									telefones = telefones + foneAtlzCad.getTelefoneFormatado() + " " + foneTipo.getDescricao() + ", ";
								}
							}
						}
					}
					if(telefones.isEmpty()) {
						cliente.setTelefoneFormatadoCliente("");
					} else {
						telefones = telefones.trim();
						cliente.setTelefoneFormatadoCliente(telefones.substring(0, telefones.length() - 1));
					}
					
					//adicionando informações do cliente no form
					if(cliente.getNomeCliente()!=null)form.setNomeCliente(cliente.getNomeCliente());
					if(cliente.getTelefoneFormatadoCliente()!=null)form.setTelefones(cliente.getTelefoneFormatadoCliente());
					if(cliente.getOrgaoExpedidorRG()!=null)form.setOrgaoExpedidor(cliente.getOrgaoExpedidorRG().getDescricaoAbreviada());
					if(cliente.getClienteTipo()!=null)form.setTipoCliente(cliente.getClienteTipo().getDescricao());
					if(cliente.getRgFormatado()!=null)form.setNumeroRg(cliente.getRgFormatado());
					if(cliente.getUnidadeFederacao()!=null)form.setUf(cliente.getUnidadeFederacao().getSigla());
					if(cliente.getSexoFormatado()!=null)form.setSexo(cliente.getSexoFormatado());
					if(cliente.getCpfCnpj()!=null)form.setCpfcnpj(cliente.getCpfCnpj());
					form.setCliente(cliente);
				}

				httpServletRequest.setAttribute("colecaoCliente", listCliente);
			}

			List<ImovelSubcategoriaAtualizacaoCadastralDM> colecaoSubCategoria =
					fachada.pesquisarSubCategoriaAtualizacaoCadastralDM(idImovel);

			if(colecaoSubCategoria != null && !colecaoSubCategoria.isEmpty()) {
				sessao.setAttribute("colecaoSubCategoria", colecaoSubCategoria);
			} else {
				sessao.removeAttribute("colecaoSubCategoria");
			}

			List<HidrometroInstalacaoHistoricoAtualizacaoCadastralDM> colecaoHidrometroInstHistAtlzCad = fachada.
					pesquisarHidrometroInstalacaoHistoricoAtualizacaoCadastralDM(idImovel, null);

			if(colecaoHidrometroInstHistAtlzCad != null && !colecaoHidrometroInstHistAtlzCad.isEmpty()) {
				httpServletRequest.setAttribute("colecaoHidrometro", colecaoHidrometroInstHistAtlzCad);
				form.setMedicaoTipo(colecaoHidrometroInstHistAtlzCad.get(0).getMedicaoTipo().getDescricao());
			} else {
				httpServletRequest.removeAttribute("colecaoHidrometro");
			}

			if(imovel != null) {
				form.setMatricula(getMatriculaFormatada(imovel.getIdImovel()));
				form.setDataVisita(Util.formatarData(imovel.getDataVisita()));
				if(ConstantesSistema.SIM.equals(imovel.getIndicadorImovelNovo())) {
					httpServletRequest.setAttribute("imovelNovo", true);
				}

				// Cadastrador
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				filtroUsuario.adicionarParametro( new ParametroSimples(FiltroUsuario.LOGIN,imovel.getLogin(), ConectorOr.CONECTOR_OR, 2));
				filtroUsuario.adicionarParametro( new ParametroSimples(FiltroUsuario.CPF,imovel.getLogin()));

				String nomeCadastrador = imovel.getLogin();
				Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
				if(colecaoUsuario != null && !colecaoUsuario.isEmpty()){
					Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
					nomeCadastrador = usuario.getNomeUsuario();
				}
				form.setCadastrador(nomeCadastrador);

				// Endereço Referência
				EnderecoReferencia enderecoReferencia = imovel.getEnderecoReferencia();
				if(enderecoReferencia != null && enderecoReferencia.getId() != null) {
					FiltroEnderecoReferencia filtroEnderecoReferencia = new FiltroEnderecoReferencia();
					filtroEnderecoReferencia.adicionarParametro(new ParametroSimples(FiltroEnderecoReferencia.ID, enderecoReferencia.getId()));

					enderecoReferencia = (EnderecoReferencia) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroEnderecoReferencia, EnderecoReferencia.class.getName()));

					form.setMatriculaReferencia("");
					if(enderecoReferencia.getId() != null){
						form.setMatriculaReferencia(String.valueOf(enderecoReferencia.getId()));
					}

					form.setDescricaoReferencia("");
					if(enderecoReferencia.getDescricao() != null){
						form.setDescricaoReferencia(enderecoReferencia.getDescricao());
					}
				}

				// LOCALIDADE
				form.setIdLocalidade("");
				form.setLocalidade("");
				form.setDescricaoSetorComercial("");

				Localidade localidade = imovel.getLocalidade();
				if(localidade != null && localidade.getId() != null) {
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidade.getId()));

					localidade = (Localidade) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroLocalidade, Localidade.class.getName()));

					if (localidade.getId() != null) {
						form.setIdLocalidade(localidade.getId().toString());
					}

					if (localidade.getDescricao() != null) {
						form.setLocalidade(localidade.getDescricao());
					}
				}

				// Setor, Quadra, Lote e Sublote
				form.setSetorComercial(String.valueOf(imovel.getCodigoSetorComercial()));
				form.setQuadra(String.valueOf(imovel.getNumeroQuadra()));
				form.setLote(String.valueOf(imovel.getNumeroLote()));
				form.setSubLote(String.valueOf(imovel.getNumeroSubLote()));

				if (imovel.getCodigoSetorComercial() != null && localidade != null && localidade.getId() != null) {
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, localidade.getId()));
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, imovel.getCodigoSetorComercial()));

					SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName()));

					if (setorComercial != null) {
						form.setDescricaoSetorComercial(setorComercial.getDescricao());
					}
				}

				// ENDEREÇO
				form.setTipoLogradouro("");
				form.setTituloLogradouro("");
				form.setDescricaoLogradouro("");
				form.setNumeroImovel("");
				form.setComplementoLogradouro("");
				form.setBairro("");
				form.setCep("");
				form.setMunicipio("");
				form.setUf("");

				Logradouro logradouro = fachada.pesquisarLogradouroImovelAtualizacaoCadastralDM(imovel.getIdLogradouro());
				if(logradouro != null) {
					LogradouroTipo tipo = logradouro.getLogradouroTipo();
					if(tipo != null && tipo.getDescricao() != null) {
						form.setTipoLogradouro(tipo.getDescricao());
					}

					LogradouroTitulo titulo = logradouro.getLogradouroTitulo();
					if(titulo != null && titulo.getDescricao() != null){
						form.setTituloLogradouro(titulo.getDescricao());
					}

					if(logradouro.getNome() != null){
						form.setDescricaoLogradouro(logradouro.getNome());
					}
				}else{
					FiltroLogradouroAtlzCadDM filtroLogradouroAtlzCad = new FiltroLogradouroAtlzCadDM();
					filtroLogradouroAtlzCad.adicionarParametro( new ParametroSimples(FiltroLogradouroAtlzCadDM.CODIGO, imovel.getIdLogradouro()));
					filtroLogradouroAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroAtlzCadDM.LOGRADOUROTIPO);
					filtroLogradouroAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroAtlzCadDM.LOGRADOUROTITULO);

					Collection<LogradouroAtlzCadDM> colecaoLogradouroAtlzCad = fachada.pesquisar(filtroLogradouroAtlzCad, LogradouroAtlzCadDM.class.getName());

					if ( colecaoLogradouroAtlzCad != null && !colecaoLogradouroAtlzCad.isEmpty() ) {
						LogradouroAtlzCadDM logradouroAtlzCad = (LogradouroAtlzCadDM) Util.retonarObjetoDeColecao(colecaoLogradouroAtlzCad);

						if (logradouroAtlzCad.getLogradouroTipo() != null) {
							form.setTipoLogradouro(logradouroAtlzCad.getLogradouroTipo().getDescricao());
						}

						if (logradouroAtlzCad.getLogradouroTitulo() != null) {
							form.setTituloLogradouro(logradouroAtlzCad.getLogradouroTitulo().getDescricao());
						}

						form.setDescricaoLogradouro(logradouroAtlzCad.getNome());
					}
				}

				if (imovel.getNumeroImovel() != null) {
					form.setNumeroImovel(imovel.getNumeroImovel());
				}

				if (imovel.getComplementoEndereco() != null) {
					form.setComplementoLogradouro(imovel.getComplementoEndereco());
				}

				if (imovel.getIdBairro() != null) {
					FiltroBairro filtroBairro = new FiltroBairro();
					filtroBairro.adicionarParametro( new ParametroSimples(FiltroBairro.ID, imovel.getIdBairro()));
					Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroBairro, Bairro.class.getName()));

					form.setBairro(bairro.getNome());
				}

				if(imovel.getCodigoCep() != null && imovel.getCodigoCep() > 0){
					form.setCep(Util.formatarCEP(String.valueOf(imovel.getCodigoCep())));
				}

				if(imovel.getMunicipio() != null && imovel.getMunicipio().getId() != null) {
					FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
					filtroMunicipio.adicionarParametro( new ParametroSimples(FiltroMunicipio.ID, imovel.getMunicipio().getId()));
					filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade(FiltroMunicipio.UNIDADE_FEDERACAO);
					Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroMunicipio, Municipio.class.getName()));

					form.setMunicipio(municipio.getNome());
				}

				// CARACTERÍSTICAS
				form.setImovelPerfil("");
				form.setNumeroMoradores("");
				form.setNumeroMedidorEnergia("");
				form.setIdPavimentoCalcada("");
				form.setPavimentoCalcada("");
				form.setIdPavimentoRua("");
				form.setPavimentoRua("");
				form.setFonteAbastecimento("");

				ImovelPerfil perfil = imovel.getImovelPerfil();
				if(perfil != null && perfil.getId() != null) {
					FiltroImovelPerfil filtroPerfil = new FiltroImovelPerfil();
					filtroPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, perfil.getId()));
					perfil = (ImovelPerfil) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroPerfil, ImovelPerfil.class.getName()));

					form.setIdImovelPerfil(perfil.getId().toString());
					form.setImovelPerfil(perfil.getDescricao());
				}

				if(imovel.getNumeroMorador() != null){
					form.setNumeroMoradores(String.valueOf(imovel.getNumeroMorador()));
				}

				if(imovel.getNumeroMedidorEnergia() != null) {
					form.setNumeroMedidorEnergia(imovel.getNumeroMedidorEnergia());
				}

				PavimentoCalcada pavimentoCalcada = imovel.getPavimentoCalcada();
				if(pavimentoCalcada != null && pavimentoCalcada.getId() != null) {
					FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
					filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(FiltroPavimentoCalcada.ID, pavimentoCalcada.getId()));

					pavimentoCalcada = (PavimentoCalcada) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroPavimentoCalcada, PavimentoCalcada.class.getName()));

					form.setIdFonteAbastecimento("");
					if (pavimentoCalcada.getId() != null) {
						form.setIdPavimentoCalcada(pavimentoCalcada.getId().toString());
					}

					if (pavimentoCalcada.getDescricao() != null) {
						form.setPavimentoCalcada(pavimentoCalcada.getDescricao());
					}
				}

				PavimentoRua pavimentoRua = imovel.getPavimentoRua();
				if(pavimentoRua != null && pavimentoRua.getId() != null) {
					FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
					filtroPavimentoRua.adicionarParametro(new ParametroSimples(FiltroPavimentoRua.ID, pavimentoRua.getId()));

					pavimentoRua = (PavimentoRua) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroPavimentoRua, PavimentoRua.class.getName()));

					if (pavimentoRua.getId() != null) {
						form.setIdPavimentoRua(pavimentoRua.getId().toString());
					}

					if (pavimentoRua.getDescricao() != null) {
						form.setPavimentoRua(pavimentoRua.getDescricao());
					}
				}

				FonteAbastecimento fonteAbastecimento = imovel.getFonteAbastecimento();
				if(fonteAbastecimento != null && fonteAbastecimento.getId() != null) {
					FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
					filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(FiltroFonteAbastecimento.ID, fonteAbastecimento.getId()));

					fonteAbastecimento = (FonteAbastecimento) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroFonteAbastecimento, FonteAbastecimento.class.getName()));

					if (fonteAbastecimento.getId() != null) {
						form.setIdFonteAbastecimento(fonteAbastecimento.getId().toString());
					}

					if (fonteAbastecimento.getDescricao() != null) {
						form.setFonteAbastecimento(fonteAbastecimento.getDescricao());
					}
				}

				// LIGAÇÃO
				form.setSituacaoLigacaoAgua("");
				form.setSituacaoLigacaoEsgoto("");
				form.setObservacao("");

				LigacaoAguaSituacao ligacaoAguaSituacao = imovel.getLigacaoAguaSituacao();
				if(ligacaoAguaSituacao != null && ligacaoAguaSituacao.getId() != null) {
					FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
					filtroLigacaoAguaSituacao.adicionarParametro( new ParametroSimples(FiltroLigacaoAguaSituacao.ID, ligacaoAguaSituacao.getId()));
					ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName()));

					form.setIdSituacaoLigacaoAgua(ligacaoAguaSituacao.getId().toString());
					form.setSituacaoLigacaoAgua(ligacaoAguaSituacao.getDescricao());
				}

				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = imovel.getLigacaoEsgotoSituacao();
				if(ligacaoEsgotoSituacao != null && ligacaoEsgotoSituacao.getId() != null) {
					FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
					filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, ligacaoEsgotoSituacao.getId()));
					ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName()));

					form.setIdSituacaoLigacaoEsgoto(ligacaoEsgotoSituacao.getId().toString());
					form.setSituacaoLigacaoEsgoto(ligacaoEsgotoSituacao.getDescricao());
				}

				if(imovel.getObservacao() != null) {
					form.setObservacao(imovel.getObservacao());
				} 
				
				LeituraAnormalidade leituraAnormalidade = imovel.getOcorrenciaHidrometro();
				if(leituraAnormalidade != null && leituraAnormalidade.getId()!=null){
					FiltroLeituraAnormalidade filtro = new FiltroLeituraAnormalidade();
					filtro.adicionarParametro( new ParametroSimples(FiltroLeituraAnormalidade.ID, leituraAnormalidade.getId()));
					leituraAnormalidade = (LeituraAnormalidade) Util.retonarObjetoDeColecao(fachada.pesquisar(filtro, LeituraAnormalidade.class.getName()));
					form.setDescricaoOcorrenciaHidrometro(leituraAnormalidade.getDescricao());
				}
				
				if(colecaoHidrometroInstHistAtlzCad != null && colecaoHidrometroInstHistAtlzCad.size()>0 ){
					HidrometroInstalacaoHistoricoAtualizacaoCadastralDM hidrometro = colecaoHidrometroInstHistAtlzCad.get(0);
					if(hidrometro.getDataInstalacaoHidrometro()!=null)form.setDataInstalacaoHidrometroFormatada
																			(hidrometro.getDataInstalacaoHidrometroFormatada());
					if(hidrometro.getNumeroHidrometro()!=null)form.setNumeroHidrometro(hidrometro.getNumeroHidrometro());
					if(hidrometro.getHidrometroLocalInstalacao()!=null){
						form.setIdLocalInstalacao(hidrometro.getHidrometroLocalInstalacao().getId().toString());
						form.setLocalInstalacao(hidrometro.getHidrometroLocalInstalacao().getDescricao());
					}
					
					if(hidrometro.getHidrometroProtecao()!=null){
						form.setTipoProtecao(hidrometro.getHidrometroProtecao().getDescricao());
						form.setIdTipoProtecao(hidrometro.getHidrometroProtecao().getId().toString());
					}
					
					if(hidrometro.getNumeroInstalacaoHidrometro()>0){
						form.setLeitura(hidrometro.getNumeroInstalacaoHidrometro().toString());
					}
				}
				
				//COORDENADAS
				if(imovel.getCoordenadaX()!=null && imovel.getCoordenadaY()!=null){
					form.setCoordenadaX(imovel.getCoordenadaX().toString());
					form.setCoordenadaY(imovel.getCoordenadaY().toString());
				}
				
			}
		}

		return retorno;
	}

	public String getMatriculaFormatada(Integer matricula) {
		String mat = "" + matricula;
		int qtdCaracteres = mat.length();

		mat = mat.substring(0, qtdCaracteres - 1) + "." +
			  mat.substring(qtdCaracteres - 1, qtdCaracteres);

		return mat;
	}

	public void pesquisarFotos(Integer idImovel, HttpSession sessao) {
		List<ImovelFotoAtualizacaoCadastralDM> fotos =
				Fachada.getInstancia().pesquisarImovelFotoAtualizacaoCadastralDM(idImovel, null);
		if (Util.isVazioOrNulo(fotos)){
			sessao.removeAttribute("colecaoFoto");
		} else {
			sessao.setAttribute("colecaoFoto", fotos);
		}
	}
}
