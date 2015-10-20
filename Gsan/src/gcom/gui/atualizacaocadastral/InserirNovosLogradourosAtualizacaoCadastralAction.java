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

package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.CepAtlzCadDM;
import gcom.atualizacaocadastral.FiltroImovelAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ImovelAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.LogradouroAtlzCadDM;
import gcom.atualizacaocadastral.LogradouroBairroAtlzCadDM;
import gcom.atualizacaocadastral.LogradouroCepAtlzCadDM;
import gcom.atualizacaocadastral.bean.AtualizacaoCadastralLogradouroHelper;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.CepTipo;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.FiltroLogradouroCep;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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

/**
 * UC1442 - InserirNovosLogradouros - AtualizacaoCadastra
 * 
 * Action responsavel por inserir os logradouros adicionados pelo tablet
 * 
 * @author Anderson Cabral
 * @author Bruno Sá Barreto
 * 
 * @date 11/03/2012
 */
public class InserirNovosLogradourosAtualizacaoCadastralAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
	
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		InserirNovosLogradourosAtualizacaoCadastralActionForm inserirNovosLogradourosActionForm = (InserirNovosLogradourosAtualizacaoCadastralActionForm) actionForm;

		boolean atualizarLogradouro = Boolean.valueOf(httpServletRequest.getParameter("atualizarLogradouro")); 
		if(!atualizarLogradouro){
			atualizarLogradouro = (Boolean) sessao.getAttribute("atualizarLogradouro");
			sessao.setAttribute("atualizarLogradouro", false);
		}
		
		//Atualizar Logradouros
		if(atualizarLogradouro){			
			ArrayList<AtualizacaoCadastralLogradouroHelper> colecaoLogradouroHelper = (ArrayList<AtualizacaoCadastralLogradouroHelper>) sessao.getAttribute("colecaoLogradouroHelper");

			
			//IT006 - Inserir Logradouro.
			String[] idsRegistrosAtualizar  = inserirNovosLogradourosActionForm.getIdsRegistros();			
			if(idsRegistrosAtualizar != null && idsRegistrosAtualizar.length > 0){
				for(String id : idsRegistrosAtualizar) {
					Logradouro logradouro;
					LogradouroAtlzCadDM logradouroAtlzCad = null;

					//1 - Inserir Logradouro
					AtualizacaoCadastralLogradouroHelper logradouroHelper = pesquisarLogradouroAtlzCad(colecaoLogradouroHelper, id);

					if (logradouroHelper == null ||
							(logradouroHelper.getIdSubstituirLogra() != null && !logradouroHelper.getIdSubstituirLogra().isEmpty())) {
						continue;
					}

					logradouroAtlzCad = logradouroHelper.getLogradouroAtlzCad();

					// [FS000X] Verificar se o arquivo do roteiro foi finalizado
					fachada.verificarArquivoRoteiroFinalizado(logradouroAtlzCad.getCodigo(), logradouroAtlzCad.getDescricaoFormatada());
					
            		// [FS0002] Verificar CEP Associado a Logradouro
					//esta verficação só será feita se o municipio não possuir cep único
					if(fachada.verificarMunicipioComCepPorLogradouro(logradouroAtlzCad.getMunicipio())){
						//verifica se algum dos ceps associados aos logradouros que foram
						//marcados para atualização estão associados a algum outro logradouro
						//que já foi cadastrado.
						for (LogradouroCepAtlzCadDM logradouroCep : logradouroHelper.getColecaoLogradouroCepAtlzCad()) {
							Integer codCep = logradouroCep.getCepAtlzCad().getCodigoCep();

							if (fachada.verificaSeExisteAssociacaoCepLogradouro(codCep)) {
								//verificando se o cep encontrado é o cep inicial do município
								boolean confirmouAtualizacao = verificarSeConfirmouAtualizacaoLogradouro(logradouroAtlzCad.getId(), sessao, httpServletRequest);
								if(codCep.equals(logradouroAtlzCad.getMunicipio().getCepInicio()) 
										&& !confirmouAtualizacao){
									sessao.setAttribute("atualizarLogradouro"+logradouroAtlzCad.getId(), true);
									sessao.setAttribute("atualizarLogradouro", true);
									httpServletRequest.setAttribute("voltarPara", "/gsan/exibirInserirNovosLogradourosAtualizacaoCadastralAction.do?pesquisarLogradouros=true");
									return montarPaginaConfirmacao(
											"atencao.logradouro_com_cep_inicial_associado",
											httpServletRequest, actionMapping, logradouroAtlzCad.getDescricaoFormatada());
								}
								
								if(!confirmouAtualizacao){
									//caso não seja o cep inicial do municipio, o erro será mostrado na tela
									throw new ActionServletException("atencao.logradouro_com_cep_ja_associado",
											logradouroAtlzCad.getDescricaoFormatada(), codCep.toString());
								}
							}
						}
					}
					//1.1 - Verifica se o logradouro ja foi incluido no gsan
					if(logradouroAtlzCad.getLogradouro() == null || logradouroAtlzCad.getLogradouro().getId() == null){
						logradouro = new Logradouro();
						
						logradouro.setNome(logradouroAtlzCad.getNome());
						logradouro.setNomePopular(logradouroAtlzCad.getNomePopular());
						logradouro.setMunicipio(logradouroAtlzCad.getMunicipio());
						logradouro.setLogradouroTitulo(logradouroAtlzCad.getLogradouroTitulo());
						logradouro.setLogradouroTipo(logradouroAtlzCad.getLogradouroTipo());
						logradouro.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
//							logradouro.setIndicadorEnvioAtualizacaoCadastral(ConstantesSistema.NAO);
						logradouro.setUltimaAlteracao(new Date());
						
							Integer idLogradouro = (Integer) fachada.inserir(logradouro);
							logradouro.setId(idLogradouro);
							logradouroAtlzCad.setLogradouro(logradouro);
							fachada.atualizar(logradouroAtlzCad);
						
					}else{
						logradouro = new Logradouro();
						logradouro.setId(logradouroAtlzCad.getLogradouro().getId());
					}
					
					
					//4 - Inseri LogradouroBairro
					ArrayList<LogradouroBairroAtlzCadDM> colecaoLogradouroBairroAtlzCad = pesquisarLogradouroBairroAtlzCad(colecaoLogradouroHelper, id);
					ArrayList<LogradouroBairro> colecaoLogradouroBairro = null;
					
					if(colecaoLogradouroBairroAtlzCad != null){
						colecaoLogradouroBairro = new ArrayList<LogradouroBairro>();
						for(LogradouroBairroAtlzCadDM logradouroBairroAtlzCad : colecaoLogradouroBairroAtlzCad){
							LogradouroBairro logradouroBairro = new LogradouroBairro();
							logradouroBairro.setLogradouro(logradouro);
							logradouroBairro.setBairro(logradouroBairroAtlzCad.getBairro());
							logradouroBairro.setUltimaAlteracao(new Date());
							
							
							//verifica se existe logradouro bairro
							FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
							filtroLogradouroBairro.adicionarParametro( new ParametroSimples( FiltroLogradouroBairro.ID_BAIRRO, logradouroBairroAtlzCad.getBairro().getId()));
							filtroLogradouroBairro.adicionarParametro( new ParametroSimples( FiltroLogradouroBairro.ID_LOGRADOURO, logradouro.getId()));
							filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.BAIRRO);
							
							Collection<LogradouroBairro> colecaoLogBairro = fachada.pesquisar(filtroLogradouroBairro, LogradouroBairro.class.getName());
							
							if ( colecaoLogBairro == null || colecaoLogBairro.isEmpty() ) {
									Integer idLogradouroBairro = (Integer) fachada.inserir(logradouroBairro);
									logradouroBairro.setId(idLogradouroBairro);
							}
							
							colecaoLogradouroBairro.add(logradouroBairro);
						}
					}
					
					//2 - Inseri Cep
					ArrayList<CepAtlzCadDM> colecaoCepAtlzCad = pesquisarCepAtlzCad(colecaoLogradouroHelper, id);
					ArrayList<LogradouroCep> colecaoLogradouroCep = null;
					
					if(colecaoCepAtlzCad != null){
						colecaoLogradouroCep = new ArrayList<LogradouroCep>();
						for(CepAtlzCadDM cepAtlzCad : colecaoCepAtlzCad){
							/************verificar se o cep ja existe no gsan **********/
							FiltroCep filtroCep = new FiltroCep();
							filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CODIGO,
									cepAtlzCad.getCodigoCep()));

							ArrayList<Cep> colecaoCep = (ArrayList<Cep>) fachada.pesquisar(filtroCep, Cep.class.getName());
							Cep cep = (Cep) Util.retonarObjetoDeColecao(colecaoCep);
							
							if(cep == null){
								cep = new Cep();
								cep.setCodigo(cepAtlzCad.getCodigoCep());
								
								if(logradouroAtlzCad != null && logradouroAtlzCad.getLogradouro() != null){
									cep.setLogradouro(logradouroAtlzCad.getLogradouro().getNome());
								}
								
								if(logradouroAtlzCad != null && logradouroAtlzCad.getLogradouro() != null 
										&& logradouroAtlzCad.getLogradouro().getMunicipio() != null){
									
									cep.setMunicipio(logradouroAtlzCad.getLogradouro().getMunicipio().getNome());
								}
								
								if(logradouroAtlzCad != null && logradouroAtlzCad.getLogradouro() != null 
										&& logradouroAtlzCad.getLogradouro().getMunicipio() != null && logradouroAtlzCad.getLogradouro().getMunicipio().getUnidadeFederacao() != null){
									cep.setSigla(logradouroAtlzCad.getLogradouro().getMunicipio().getUnidadeFederacao().getSigla());
								}
								
								if(colecaoLogradouroBairro != null && !colecaoLogradouroBairro.isEmpty()
										&& colecaoLogradouroBairro.get(0).getBairro() != null){
									cep.setBairro(colecaoLogradouroBairro.get(0).getBairro().getNome());
								}
								
								if (logradouroAtlzCad != null && logradouroAtlzCad.getLogradouroTipo() != null 
										&& logradouroAtlzCad.getLogradouroTipo().getDescricao() != null){
									
									cep.setDescricaoTipoLogradouro(logradouroAtlzCad.getLogradouroTipo().getDescricao());
								}
									
								cep.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
								cep.setUltimaAlteracao(new Date());
								
									Integer idCep = (Integer) fachada.inserir(cep);
									cep.setCepId(idCep);
							}
							
							//3 - Inseri LogradouroCep
							LogradouroCep logradouroCep = new LogradouroCep();
							logradouroCep.setLogradouro(logradouro);
							logradouroCep.setCep(cep);							
							logradouroCep.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
							logradouroCep.setUltimaAlteracao(new Date());
							
							
							//verifica se existe logradouro bairro
							FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
							filtroLogradouroCep.adicionarParametro( new ParametroSimples( FiltroLogradouroCep.ID_CEP, cep.getCepId() ) );
							filtroLogradouroCep.adicionarParametro( new ParametroSimples( FiltroLogradouroCep.ID_LOGRADOURO, logradouro.getId() ) );
							
							Collection<LogradouroCep> colecaoLogCep = fachada.pesquisar(filtroLogradouroCep, LogradouroCep.class.getName());
							
							if ( colecaoLogCep == null || colecaoLogCep.isEmpty() ) {
									Integer idLogradouroCep = (Integer) fachada.inserir(logradouroCep);
									logradouroCep.setId(idLogradouroCep);
							}

							colecaoLogradouroCep.add(logradouroCep);							
						}
					}
					
					//5 - Atualiza ImovelAtlzCad
					ArrayList<ImovelAtualizacaoCadastralDM> colecaoImovelAtlzCad = pesquisarImovelAtlzCad(logradouroAtlzCad.getCodigo(), fachada);
					ArrayList<ImovelAtualizacaoCadastralDM> imoveisNaoAtualizados = new ArrayList<ImovelAtualizacaoCadastralDM>();
					if(colecaoImovelAtlzCad != null){
						for(int i=0; i<colecaoImovelAtlzCad.size(); i++){
							
							ImovelAtualizacaoCadastralDM imovelAtlzCad = colecaoImovelAtlzCad.get(i); 
							imovelAtlzCad.setIdLogradouro(Long.valueOf(logradouro.getId()));
							
							imovelAtlzCad.setCodigoLogradouroCep(null);
							imovelAtlzCad.setCodigoLogradouroBairro(null);
							
							boolean flagAtualizouCep=false, flagAtualizouBairro=false;
							//pesquisa o logradouroCep do imovel e insere o novo id
							if(colecaoLogradouroCep != null && imovelAtlzCad.getCodigoCep() != null){
								for(LogradouroCep logradouroCep : colecaoLogradouroCep){
									if(logradouroCep.getCep().getCodigo().equals(imovelAtlzCad.getCodigoCep())){
										imovelAtlzCad.setCodigoLogradouroCep(logradouroCep.getId());
										imovelAtlzCad.setCodigoCep(logradouroCep.getCep().getCodigo());
										flagAtualizouCep=true;
										break;
									}
								}
							}
							
							//pesquisa o logradouroBairro do imovel e insere o novo id
							if(colecaoLogradouroBairro != null && imovelAtlzCad.getIdBairro() != null){
								for(LogradouroBairro logradouroBairro : colecaoLogradouroBairro){
									if(logradouroBairro.getBairro().getId().equals(imovelAtlzCad.getIdBairro())){
										imovelAtlzCad.setCodigoLogradouroBairro(logradouroBairro.getId());
										imovelAtlzCad.setNomeBairro(logradouroBairro.getBairro().getNome());
										imovelAtlzCad.setIdBairro(logradouroBairro.getBairro().getId());
										flagAtualizouBairro=true;
										break;
									}
								}
							}
							
							imovelAtlzCad.setUltimaAlteracao(new Date());
							fachada.atualizar(imovelAtlzCad);
							
							//caso ambos os casos tenham sido atualizados, o objeto será removido da lista
							//os objetos remanescentes na lista irão ser atualizados com a primeira ocorrência 
							//de cep ou bairro.
							if(!(flagAtualizouCep && flagAtualizouBairro)){
								imoveisNaoAtualizados.add(imovelAtlzCad);
							}
							
						}
						
						//fazendo associações de lograouroCeps e logradouroBairros que não foram encontrados
						//na lista com a primeira posição da lista.
						if(imoveisNaoAtualizados.size()>0){
							for(ImovelAtualizacaoCadastralDM imovelAtlzCad : imoveisNaoAtualizados){
								if(imovelAtlzCad.getCodigoLogradouroBairro()==null){
									imovelAtlzCad.setCodigoLogradouroBairro(colecaoLogradouroBairro.get(0).getId());
									imovelAtlzCad.setNomeBairro(colecaoLogradouroBairro.get(0).getBairro().getNome());
									imovelAtlzCad.setIdBairro(colecaoLogradouroBairro.get(0).getBairro().getId());
								}
								if(imovelAtlzCad.getCodigoLogradouroCep()==null){
									imovelAtlzCad.setCodigoLogradouroCep(colecaoLogradouroCep.get(0).getId());
									imovelAtlzCad.setCodigoCep(colecaoLogradouroCep.get(0).getCep().getCodigo());
								}
								fachada.atualizar(imovelAtlzCad);
							}
						}
					}
					
					//6 - Atualiza LogradouroAtlzCad
					logradouroAtlzCad.setIndicadorAtualizado(ConstantesSistema.SIM);
					logradouroAtlzCad.setUltimaAlteracao(new Date());
						fachada.atualizar(logradouroAtlzCad);
				}
			}
			
			//IT005 - Substituir Logradouro do Imovel Atualizacao Cadastral
			ArrayList<String> idsRegistrosSubstituir = new ArrayList<String>();
			ArrayList<AtualizacaoCadastralLogradouroHelper> colecaoLogradouroHelperSubstituir = new ArrayList<AtualizacaoCadastralLogradouroHelper>();
			
			for(AtualizacaoCadastralLogradouroHelper logradouroHelper : colecaoLogradouroHelper){
				if(logradouroHelper.getIdSubstituirLogra() != null){
					colecaoLogradouroHelperSubstituir.add(logradouroHelper);
				}
			}
			
			if(colecaoLogradouroHelperSubstituir != null){
				for(AtualizacaoCadastralLogradouroHelper logradouroHelperSubstituir : colecaoLogradouroHelperSubstituir){
					
					// [FS000X] Verificar se o arquivo do roteiro foi finalizado
					fachada.verificarArquivoRoteiroFinalizado(logradouroHelperSubstituir.getLogradouroAtlzCad().getCodigo(), logradouroHelperSubstituir.getLogradouroAtlzCad().getDescricaoFormatada());
					
					ArrayList<ImovelAtualizacaoCadastralDM> colecaoImoveisAtlzCad = pesquisarImovelAtlzCad(logradouroHelperSubstituir.getLogradouroAtlzCad().getCodigo().toString(), fachada);
				
					if(colecaoImoveisAtlzCad != null){
						for(ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastral : colecaoImoveisAtlzCad){
							imovelAtualizacaoCadastral.setIdLogradouro(Long.valueOf(logradouroHelperSubstituir.getIdSubstituirLogra()));
							
							///Pesquisa o LogradouroBairro do logradouro substituto
							FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
							filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_LOGRADOURO,
									logradouroHelperSubstituir.getIdSubstituirLogra()));
							filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.BAIRRO);
							
							ArrayList<LogradouroBairro> colecaoLogradouroBairro = (ArrayList<LogradouroBairro>) fachada.pesquisar(filtroLogradouroBairro, LogradouroBairro.class.getName());
							
							//Verifica se existe logradouroBairro com o bairro do imovel, 
							//caso tenha, inseri no imovel o id do logradouroBairro encontrado.
							//caso não tenha, insere no imovel o id do primeiro logradouroBairro e Bairro encontrado
							if(colecaoLogradouroBairro != null){
								boolean existeBairro = false;
								for(LogradouroBairro logradouroBairro : colecaoLogradouroBairro){
									if(logradouroBairro.getBairro().getId().equals(imovelAtualizacaoCadastral.getIdBairro())){
										imovelAtualizacaoCadastral.setCodigoLogradouroBairro(logradouroBairro.getId());
										existeBairro = true;
									}
								}
								
								if(!existeBairro){
									imovelAtualizacaoCadastral.setCodigoLogradouroBairro(colecaoLogradouroBairro.get(0).getId());
									imovelAtualizacaoCadastral.setIdBairro(colecaoLogradouroBairro.get(0).getBairro().getId());
								}
							}
							
							//Pesquisa o LogradouroCep do logradouro substituto
							FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
							filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO,
									logradouroHelperSubstituir.getIdSubstituirLogra()));
							filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.CODIGO_CEP,
									imovelAtualizacaoCadastral.getCodigoCep()));
							
							ArrayList<LogradouroCep> colecaoLogradouroCep = (ArrayList<LogradouroCep>) fachada.pesquisar(filtroLogradouroCep, LogradouroCep.class.getName());				
							LogradouroCep logradouroCep = (LogradouroCep) Util.retonarObjetoDeColecao(colecaoLogradouroCep);
							
							if(logradouroCep != null){
								imovelAtualizacaoCadastral.setCodigoLogradouroCep(logradouroCep.getId());
							}else{
								
								//inseri CEP
								/************verificar se o cep ja existe no gsan **********/
								FiltroCep filtroCep = new FiltroCep();
								filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CODIGO,
										imovelAtualizacaoCadastral.getCodigoCep()));
	
								ArrayList<Cep> colecaoCep = (ArrayList<Cep>) fachada.pesquisar(filtroCep, Cep.class.getName());
								Cep cep = (Cep) Util.retonarObjetoDeColecao(colecaoCep);
								
								// logradouro bairro substituto
								LogradouroBairro logrBairroSubstituto = fachada.pesquisarLogradouroBairroPorId(Integer.valueOf(logradouroHelperSubstituir.getIdSubstituirLogra()));
//								
								if(cep == null){
									cep = new Cep();
									cep.setCodigo(imovelAtualizacaoCadastral.getCodigoCep());
									
									//nomeLogradouro
									if(logrBairroSubstituto != null && logrBairroSubstituto.getLogradouro()!= null){
										cep.setLogradouro(logrBairroSubstituto.getLogradouro().getNome());
									}
								
									//nomeMunicipio
									if(logrBairroSubstituto != null && logrBairroSubstituto.getLogradouro() != null && logrBairroSubstituto.getLogradouro().getMunicipio()!= null){
										cep.setMunicipio(logrBairroSubstituto.getLogradouro().getMunicipio().getNome());
									}
									//ufSigla
									if(logrBairroSubstituto != null && logrBairroSubstituto.getLogradouro() != null && logrBairroSubstituto.getLogradouro().getMunicipio()!= null && logrBairroSubstituto.getLogradouro().getMunicipio().getUnidadeFederacao() != null){
										cep.setSigla(logrBairroSubstituto.getLogradouro().getMunicipio().getUnidadeFederacao().getSigla());
									}
									//nomeBairro
									if(logrBairroSubstituto != null && logrBairroSubstituto.getBairro() != null){
										cep.setBairro(logrBairroSubstituto.getBairro().getNome());
									}
									
									if (logrBairroSubstituto != null && logrBairroSubstituto.getLogradouro() != null &&
											logrBairroSubstituto.getLogradouro().getLogradouroTipo() != null && logrBairroSubstituto.getLogradouro().getLogradouroTipo().getDescricao() != null){
										
										cep.setDescricaoTipoLogradouro(logrBairroSubstituto.getLogradouro().getLogradouroTipo().getDescricao());
									}
									
									
									CepTipo cepTipo = new CepTipo();
									cepTipo.setId(0);
									
									cep.setCepTipo(cepTipo);
									cep.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
									cep.setUltimaAlteracao(new Date());
									
									Integer idCep = (Integer) fachada.inserir(cep);
									cep.setCepId(idCep);
								}
								
								//inseri LogradouroCep
								logradouroCep = new LogradouroCep();
								Logradouro logradouroSubstituto = new Logradouro();
								logradouroSubstituto.setId(Integer.parseInt(logradouroHelperSubstituir.getIdSubstituirLogra()));
								logradouroCep.setLogradouro(logradouroSubstituto);
								logradouroCep.setCep(cep);
								logradouroCep.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
								logradouroCep.setUltimaAlteracao(new Date());
								
								Integer idLogradouroCep = (Integer) fachada.inserir(logradouroCep);
								logradouroCep.setId(idLogradouroCep);
								
								imovelAtualizacaoCadastral.setCodigoLogradouroCep(logradouroCep.getId());
							}
							imovelAtualizacaoCadastral.setUltimaAlteracao(new Date());
							fachada.atualizar(imovelAtualizacaoCadastral);							
						}
					}
					
					//2 - Atualiza LogradouroAtlzCad
					Logradouro logradouro = new Logradouro();
					logradouro.setId(Integer.parseInt(logradouroHelperSubstituir.getIdSubstituirLogra()));
					logradouroHelperSubstituir.getLogradouroAtlzCad().setLogradouro(logradouro);
					logradouroHelperSubstituir.getLogradouroAtlzCad().setIndicadorAtualizado(ConstantesSistema.SIM);
					logradouroHelperSubstituir.getLogradouroAtlzCad().setUltimaAlteracao(new Date());
					fachada.atualizar(logradouroHelperSubstituir.getLogradouroAtlzCad());
				}
			}
		}

		// Limpar sessão
		sessao.removeAttribute("logradourosAtualizados");
		sessao.removeAttribute("atualizarLogradouro");

		// montando pagina de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Logradouro(s) Atualizado(s) com sucesso.", 
			"Voltar",
			"/exibirInserirNovosLogradourosAtualizacaoCadastralAction.do?menu=sim");

		return retorno;
	}

	/** Quando é exibida a mensagem de confirmação de insersão de cep inicial
	 * no logradouro é armazenado na sessão um objeto que visa identificar que
	 * o logradouro "x" foi submetido à confirmação. Caso o usuário não confirme
	 * o objeto não é encontrado na sessão. caso contrário o objeto é encontrado
	 * e removido na mesma hora.
	 * 
	 * @author Bruno Sá Barreto
	 * @date 06/02/2015
	 *
	 * @param id
	 * @param sessao
	 * @return boolean result
	 */
	private boolean verificarSeConfirmouAtualizacaoLogradouro(Integer id,
			HttpSession sessao, HttpServletRequest req) {
		
		String confirmado = req.getParameter("confirmado");
		
		String atualizados = (String) sessao.getAttribute("logradourosAtualizados");
		if(atualizados == null) atualizados = "";
		if(atualizados.contains(id+","))return true;
		
		Boolean confirmou = (Boolean) sessao.getAttribute("atualizarLogradouro"+id);
		if(confirmou!=null && confirmou && "ok".equals(confirmado)){
			sessao.removeAttribute("atualizarLogradouro"+id);
			sessao.setAttribute("logradourosAtualizados", atualizados+id+",");
			return true;
		}
		
		return false;
	}

	//Retorna o AtualizacaoCadastralLogradouroHelper da colecao
	private AtualizacaoCadastralLogradouroHelper pesquisarLogradouroAtlzCad(ArrayList<AtualizacaoCadastralLogradouroHelper>colecaoLogradouroHelper, String id){
		AtualizacaoCadastralLogradouroHelper logradouroHelperRetorno = null;
		for(AtualizacaoCadastralLogradouroHelper logradouroHelper : colecaoLogradouroHelper){
			if(logradouroHelper.getLogradouroAtlzCad().getId().toString().equals(id)){
				logradouroHelperRetorno = logradouroHelper;
			}
		}
		
		return logradouroHelperRetorno;		
	}
	
	
	/**
	 * Retorna os LogradouroCepAtlzCad (da colecao que esta na sessao) do logradouro informado
	 * @author Anderson Cabral
	 * @since 13/03/2013
	 * **/
	private ArrayList<LogradouroBairroAtlzCadDM> pesquisarLogradouroBairroAtlzCad(ArrayList<AtualizacaoCadastralLogradouroHelper>colecaoLogradouroHelper, String id){
		ArrayList<LogradouroBairroAtlzCadDM> logradouroBairroAtlzCad = null;
		
		for(AtualizacaoCadastralLogradouroHelper logradouroHelper : colecaoLogradouroHelper){
			if(logradouroHelper.getLogradouroAtlzCad().getId().toString().equals(id)){
				logradouroBairroAtlzCad = logradouroHelper.getColecaoLogardouroBairroAtlzCad();
			}
		}		
		return logradouroBairroAtlzCad;		
	}
	
	/**
	 * Retorna os LogradouroCepAtlzCad (da colecao que esta na sessao) do logradouro informado
	 * @author Anderson Cabral
	 * @since 13/03/2013
	 * **/
	private ArrayList<LogradouroCepAtlzCadDM> pesquisarLogradouroCepAtlzCad(ArrayList<AtualizacaoCadastralLogradouroHelper>colecaoLogradouroHelper, String id){
		ArrayList<LogradouroCepAtlzCadDM> logradouroCepAtlzCad = null;
		
		for(AtualizacaoCadastralLogradouroHelper logradouroHelper : colecaoLogradouroHelper){
			if(logradouroHelper.getLogradouroAtlzCad().getId().toString().equals(id)){
				logradouroCepAtlzCad = logradouroHelper.getColecaoLogradouroCepAtlzCad();
			}
		}		
		return logradouroCepAtlzCad;		
	}
	
	/**
	 * Retorna os CepAtlzCad (da colecao que esta na sessao) do logradouro informado
	 * @author Anderson Cabral
	 * @since 13/03/2013
	 * **/
	private ArrayList<CepAtlzCadDM> pesquisarCepAtlzCad(ArrayList<AtualizacaoCadastralLogradouroHelper>colecaoLogradouroHelper, String id){
		ArrayList<CepAtlzCadDM> colecaoCepAtlzCad = null;
		
		ArrayList<LogradouroCepAtlzCadDM> colecaoLogradouroCepAtlzCad = pesquisarLogradouroCepAtlzCad(colecaoLogradouroHelper, id);
		
		if(colecaoLogradouroCepAtlzCad != null){
			colecaoCepAtlzCad = new ArrayList<CepAtlzCadDM>();
			for(LogradouroCepAtlzCadDM logradouroCepAtlzCad : colecaoLogradouroCepAtlzCad){
				colecaoCepAtlzCad.add(logradouroCepAtlzCad.getCepAtlzCad());
			}
		}
		
		return colecaoCepAtlzCad;
	}
	
	//Retorna os ImovelAtualizacaoCadastral do logradouro informado
	private ArrayList<ImovelAtualizacaoCadastralDM> pesquisarImovelAtlzCad(String idLogradouro, Fachada fachada){
		FiltroImovelAtualizacaoCadastralDM filtroImovelAtualizacaoCadastral = new FiltroImovelAtualizacaoCadastralDM();
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastralDM.ID_LOGRADOURO,
				idLogradouro));
		
		ArrayList<ImovelAtualizacaoCadastralDM> colecaoImovelAtualizacaoCadastral = (ArrayList<ImovelAtualizacaoCadastralDM>) fachada.pesquisar(filtroImovelAtualizacaoCadastral, ImovelAtualizacaoCadastralDM.class.getName());
		
		return colecaoImovelAtualizacaoCadastral;
	}
}
