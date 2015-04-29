/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio Virginio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.gui.cadastro.atualizacaocadastral;

import gsan.batch.Processo;
import gsan.cadastro.atualizacaocadastral.FiltroImovelAtualizacaoCadastral;
import gsan.cadastro.imovel.ImovelAtualizacaoCadastral;
import gsan.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarImovelGeracaoTabelasTemporariasCadastroAction extends GcomAction {

	/**
	 * [UC0830] - Filtro para Geracao de Tabelas Temporarias para Atualizacao Cadastral
	 * @author: Vinicius Medeiros *alterado por Arthur Carvalho no dia 22/09/2009
	 * @date: 05/08/2008
	 * 
	 */
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		ImovelGeracaoTabelasTemporariasCadastroHelper imovelGeracaoTabelasTemporariasCadastroHelper =
			new ImovelGeracaoTabelasTemporariasCadastroHelper();
		
		String line = null;
		Collection colecaoMatriculas = new ArrayList();
		
		try {
			//Classe utilizada para processamento de uploads de arquivos
			DiskFileUpload upload = new DiskFileUpload();
	
			// Parse the request
			List items = upload.parseRequest(httpServletRequest);
	
			FileItem item = null;
			
			// pega uma lista de itens do form
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				item = (FileItem) iter.next();
				
				//Matricula do imovel
				if( item.getFieldName().equals("matricula") && !item.getString().equals("") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setMatricula(item.getString());
					
					FiltroImovelAtualizacaoCadastral filtro = new FiltroImovelAtualizacaoCadastral();
					filtro.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.ID_IMOVEL, item.getString()));
					filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelAtualizacaoCadastral.IMOVEL);
					filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.situacaoAtualizacaoCadastral");
					
					Collection<?> colImovelAtuCad = fachada.pesquisar(filtro, ImovelAtualizacaoCadastral.class.getName());
					if(!Util.isVazioOrNulo(colImovelAtuCad)){
						ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = (ImovelAtualizacaoCadastral)
								Util.retonarObjetoDeColecao(colImovelAtuCad);
						
						if(imovelAtualizacaoCadastral.getImovel() != null){
							if(imovelAtualizacaoCadastral.getImovel().getIndicadorExclusao() != ConstantesSistema.SIM &&
									imovelAtualizacaoCadastral.getImovel().getSituacaoAtualizacaoCadastral() != null &&
									imovelAtualizacaoCadastral.getImovel().getSituacaoAtualizacaoCadastral().getId() != 0){
								throw new ActionServletException("atencao.imovel_existente_tabela_atualizacao_cadastral");
							}
						}
						
					}
					
				}
				
				//Cliente
				if ( item.getFieldName().equals("cliente") && !item.getString().equals("") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setCliente(item.getString());
				}
				
				//Sugestao
				if ( item.getFieldName().equals("sugestao") && !item.getString().equals("") ) {
						imovelGeracaoTabelasTemporariasCadastroHelper.setSugestao(item.getString());
				}
				
				//Firma(empresa)
				if ( item.getFieldName().equals("firma") && !item.getString().equals("-1") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setFirma( item.getString() );
				} 
				
				//Quantidade Maxima
				if ( item.getFieldName().equals("quantidadeMaxima") && !item.getString().equals("") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setQuantidadeMaxima( new Integer( item.getString() ));
				}
				
				//Agencia
				if ( item.getFieldName().equals("elo") && !item.getString().equals("") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setElo(item.getString());
				}
				
				//Localidade Inicial
				if ( item.getFieldName().equals("localidadeInicial") && !item.getString().equals("") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeInicial( item.getString() );
					pesquisarLocalidade("origem", imovelGeracaoTabelasTemporariasCadastroHelper, 
							fachada, httpServletRequest);
				}
				
				//Setor Comercial Inicial
				if ( item.getFieldName().equals("codigoSetorComercialInicial") && !item.getString().equals("") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialInicial(item.getString() ) ;
					
					if(imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeInicial() != null){

						FiltroSetorComercial filtroSetorComercialInicial = new FiltroSetorComercial();
						filtroSetorComercialInicial.adicionarParametro(
							new ParametroSimples(
								FiltroSetorComercial.ID_LOCALIDADE, 
								imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeInicial()));		
						
						filtroSetorComercialInicial.adicionarParametro(
							new ParametroSimples(
								FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, 
								imovelGeracaoTabelasTemporariasCadastroHelper.getCodigoSetorComercialInicial()));		

						Collection colecaoSetorComercialInicial = 
							fachada.pesquisar(filtroSetorComercialInicial, 
								SetorComercial.class.getName());		
						
						if(colecaoSetorComercialInicial != null && !colecaoSetorComercialInicial.isEmpty()){
							SetorComercial setorComercialInicial = 
								(SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialInicial);

							
							imovelGeracaoTabelasTemporariasCadastroHelper.setSetorComercialInicial(
								setorComercialInicial.getId().toString());
							
							pesquisarSetorComercial("origem", 
									imovelGeracaoTabelasTemporariasCadastroHelper, 
									fachada, httpServletRequest);

						}
					}
				}

				//Quadra Inicial
				if ( item.getFieldName().equals("quadraInicial") && !item.getString().equals("") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraInicial(item.getString());
					pesquisarQuadra("origem", imovelGeracaoTabelasTemporariasCadastroHelper,
							fachada, httpServletRequest);
				}
				
				//Localidade Final
				if ( item.getFieldName().equals("localidadeFinal") && !item.getString().equals("") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeFinal( item.getString() );
					pesquisarLocalidade("destino", imovelGeracaoTabelasTemporariasCadastroHelper, 
							fachada, httpServletRequest);
				}
				
				//Setor Comercial Final
				if ( item.getFieldName().equals("codigoSetorComercialFinal") && !item.getString().equals("") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialFinal(item.getString() ) ;
					
					if(imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeFinal() != null){

						FiltroSetorComercial filtroSetorComercialFinal = new FiltroSetorComercial();
						filtroSetorComercialFinal.adicionarParametro(
							new ParametroSimples(
								FiltroSetorComercial.ID_LOCALIDADE, 
								imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeFinal()));		
						
						filtroSetorComercialFinal.adicionarParametro(
							new ParametroSimples(
								FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, 
								imovelGeracaoTabelasTemporariasCadastroHelper.getCodigoSetorComercialFinal()));		

						Collection colecaoSetorComercialFinal = 
							fachada.pesquisar(filtroSetorComercialFinal, 
								SetorComercial.class.getName());		
						
						if(colecaoSetorComercialFinal != null && !colecaoSetorComercialFinal.isEmpty()){
							SetorComercial setorComercialFinal = 
								(SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialFinal);

							
							imovelGeracaoTabelasTemporariasCadastroHelper.setSetorComercialFinal(
									setorComercialFinal.getId().toString());
							
							pesquisarSetorComercial("destino", 
									imovelGeracaoTabelasTemporariasCadastroHelper,
									fachada, 
									httpServletRequest);
						}
					}
				}
				
				//Quadra Final
				if ( item.getFieldName().equals("quadraFinal") && !item.getString().equals("") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraFinal(item.getString());
					
					pesquisarQuadra("destino", imovelGeracaoTabelasTemporariasCadastroHelper,
							fachada, httpServletRequest);
					
				}
				
				//Rota Inicial
				if ( item.getFieldName().equals("rotaInicial") && !item.getString().equals("") ) {
					
					imovelGeracaoTabelasTemporariasCadastroHelper.setRotaInicial(new Short(item.getString()));
					
					if(imovelGeracaoTabelasTemporariasCadastroHelper.getSetorComercialInicial() != null){

						FiltroRota filtroRotaInicial = new FiltroRota();
						filtroRotaInicial.adicionarParametro(
							new ParametroSimples(
								FiltroRota.SETOR_COMERCIAL_ID, 
								imovelGeracaoTabelasTemporariasCadastroHelper.getSetorComercialInicial()));		
						
						filtroRotaInicial.adicionarParametro(
							new ParametroSimples(
								FiltroRota.CODIGO_ROTA, 
								imovelGeracaoTabelasTemporariasCadastroHelper.getRotaInicial()));		

						Collection colecaoRotaInicial = 
							fachada.pesquisar(filtroRotaInicial, 
								Rota.class.getName());		
						
						if(colecaoRotaInicial != null && !colecaoRotaInicial.isEmpty()){
							Rota rota = 
								(Rota) Util.retonarObjetoDeColecao(colecaoRotaInicial);
							
							imovelGeracaoTabelasTemporariasCadastroHelper.setRotaInicial(rota.getCodigo());
							
						}					
					}
				}
				//Sequencial Rota Inicial
				if ( item.getFieldName().equals("rotaSequenciaInicial") && !item.getString().equals("") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setRotaSequenciaInicial(new Integer(item.getString()));
				}
				
				//Rota Final
				if ( item.getFieldName().equals("rotaFinal") && !item.getString().equals("") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setRotaFinal(new Short(item.getString()));
					
					if(imovelGeracaoTabelasTemporariasCadastroHelper.getSetorComercialFinal() != null){

						FiltroRota filtroRotaFinal = new FiltroRota();
						filtroRotaFinal.adicionarParametro(
							new ParametroSimples(
								FiltroRota.SETOR_COMERCIAL_ID, 
								imovelGeracaoTabelasTemporariasCadastroHelper.getSetorComercialFinal()));		
						
						filtroRotaFinal.adicionarParametro(
							new ParametroSimples(
								FiltroRota.CODIGO_ROTA, 
								imovelGeracaoTabelasTemporariasCadastroHelper.getRotaFinal()));		

						Collection colecaoRotaFinal = 
							fachada.pesquisar(filtroRotaFinal, 
								Rota.class.getName());		
						
						if(colecaoRotaFinal != null && !colecaoRotaFinal.isEmpty()){
							Rota rota = 
								(Rota) Util.retonarObjetoDeColecao(colecaoRotaFinal);
							
							imovelGeracaoTabelasTemporariasCadastroHelper.setRotaFinal(rota.getCodigo());
							
						}					
					}
					
				}
				
				//Sequencial Rota Final
				if ( item.getFieldName().equals("rotaSequenciaFinal") && !item.getString().equals("") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setRotaSequenciaFinal(new Integer(item.getString()));
				}
				
				//Perfil do Imovel
				if ( item.getFieldName().equals("perfilImovel") && !item.getString().equals("-1") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setPerfilImovel(item.getString());
				} 
				
				//Categoria 
				if ( item.getFieldName().equals("categoria") && !item.getString().equals("-1") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setCategoria(item.getString());
				} 
				
				//Subcategoria
				if ( item.getFieldName().equals("subCategoria") && !item.getString().equals("-1") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setSubCategoria(item.getString());
				} 
				
				//Situacao Ligacao Agua
				if ( item.getFieldName().equals("idSituacaoLigacaoAgua") && !item.getString().equals("-1") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setIdSituacaoLigacaoAgua(item.getString());
				}
				
				//Situacao do Imovel
				if ( item.getFieldName().equals("imovelSituacao") && !item.getString().equals("") ) {
					imovelGeracaoTabelasTemporariasCadastroHelper.setImovelSituacao(item.getString());
				}
				
				//INICIO ARQUIVO TEXTO - COLECAO DE MATRICULAS -
				
				// verifica se n�o � diretorio
				if (!item.isFormField()) {
					
					// coloca o nome do item para maiusculo
					String nomeItem = item.getName().toUpperCase();
					if (nomeItem.endsWith(".TXT")) {
						
						//abre o arquivo
						InputStreamReader reader = new InputStreamReader(item.getInputStream());
						BufferedReader buffer = new BufferedReader(reader);
						while ((line = buffer.readLine()) != null) {
							
							// pega a linha do arquivo
							String linhaLida = line;

							//se for a ultima linha do arquivo
							if (linhaLida != null && linhaLida.length() > 0) {
								colecaoMatriculas.add(new Integer(linhaLida));
							} else {
								break;
							}
						}
					}
				}	
				//FIM - ARQUIVO TEXTO -
			}
		
		} catch (IOException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		} catch (NumberFormatException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		} catch (FileUploadException e) {
			throw new ActionServletException("erro.sistema", e);
		}	
		
		//Colecao de matriculas no txt
		if ( colecaoMatriculas != null && colecaoMatriculas.size() > 0 ) {
			imovelGeracaoTabelasTemporariasCadastroHelper.setColecaoMatriculas(colecaoMatriculas);
		}
			
		//Informa a Quantidade de Imoveis ou vai para o batch
		if(imovelGeracaoTabelasTemporariasCadastroHelper.getSugestao().equals("1")){
			
			if ( colecaoMatriculas != null && colecaoMatriculas.size() > 0 ) {
			
				throw new ActionServletException("atencao_quantidade_imoveis_sugestao_sim", null, 
							"" + colecaoMatriculas.size());
			
			} else {
				
				Collection colecaoIdsImovel = fachada.obterIdsImovelGeracaoTabelasTemporarias(
							imovelGeracaoTabelasTemporariasCadastroHelper);
				
				Integer totalImoveis = null;
				totalImoveis = colecaoIdsImovel.size();
				throw new ActionServletException("atencao_quantidade_imoveis_sugestao_sim", null, totalImoveis.toString());		

			}
        }else{
		
        	//USU�RIO LOGADO
        	imovelGeracaoTabelasTemporariasCadastroHelper.setUsuario(this.getUsuarioLogado(httpServletRequest));
        	
        	Map parametros = new HashMap();
			parametros.put("imovelGeracaoTabelasTemporariasCadastroHelper",imovelGeracaoTabelasTemporariasCadastroHelper);
	
			Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
			             		Processo.GERAR_TABELAS_TEMP_ATU_CADASTRAL ,
			             		this.getUsuarioLogado(httpServletRequest));
			
			montarPaginaSucesso(httpServletRequest, "Gera��o de tabelas encaminhada para Batch.", "", "");
        }

		return retorno;
	}
	
	/***
	 * 
	 * @param inscricaoTipo
	 * @param imovelCurvaAbcDebitosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	
	private void pesquisarLocalidade(
			String inscricaoTipo,
			ImovelGeracaoTabelasTemporariasCadastroHelper imovelGeracaoTabelasTemporariasCadastroHelper,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			//FALTA CORRGIR
			//imovelGeracaoTabelasTemporariasCadastroActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			String localidadeID = imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeInicial().toString();
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Retorna localidade
			Collection colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException("atencao.localidade.inexistente", null, "Localidade");					
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeInicial(objetoLocalidade.getId().toString());
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialInicial");
				
				//destino
				imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeFinal(objetoLocalidade.getId().toString());
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formul�rio.
			String localidadeID = imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeFinal().toString();
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Retorna localidade
			Collection colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				throw new ActionServletException("atencao.localidade.inexistente", null, "Localidade");
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				
				imovelGeracaoTabelasTemporariasCadastroHelper.setLocalidadeFinal(objetoLocalidade.getId().toString());
				
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
			}
		}
		

	}
	
	/***
	 * 
	 * @param inscricaoTipo
	 * @param imovelCurvaAbcDebitosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarSetorComercial(
			String inscricaoTipo,
			ImovelGeracaoTabelasTemporariasCadastroHelper imovelGeracaoTabelasTemporariasCadastroHelper,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		
		String setorComercialCD = imovelGeracaoTabelasTemporariasCadastroHelper.getCodigoSetorComercialInicial();
		
		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			String localidadeID = imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeInicial().toString();

			// O campo localidadeOrigemID ser� obrigat�rio
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {
				setorComercialCD = imovelGeracaoTabelasTemporariasCadastroHelper.getCodigoSetorComercialInicial();
				
				// Adiciona o id da localidade que est� no formul�rio para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));
				
				// Adiciona o c�digo do setor comercial que esta no formul�rio
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				// Retorna setorComercial
				Collection colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					//Setor Comercial nao encontrado
					throw new ActionServletException("atencao.setor_comercial.inexistente", null, "Localidade");	

				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					//setorComercialOrigem
					imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialInicial(
							String.valueOf(objetoSetorComercial.getCodigo()));

					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formul�rio
				httpServletRequest.setAttribute("codigoSetorComercialInicial", "");
				imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialInicial("");
				imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSetorComercialInicial(
						"Informe a localidade da inscri��o de origem.");
				
				httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
			}
		} else {
			//imovelGeracaoTabelasTemporariasCadastro.setInscricaoTipo("destino");
			
			// Recebe o valor do campo localidadeDestinoID do formul�rio.
			String localidadeID = imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeFinal().toString();

			// O campo localidadeOrigem ser� obrigat�rio
			if (localidadeID != null && !localidadeID.trim().equalsIgnoreCase("")) {
				setorComercialCD = imovelGeracaoTabelasTemporariasCadastroHelper.getCodigoSetorComercialFinal();

				// Adiciona o id da localidade que est� no formul�rio para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o c�digo do setor comercial que esta no formul�rio
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				Collection colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					//Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formul�rio
					imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialFinal("");
					imovelGeracaoTabelasTemporariasCadastroHelper.setSetorComercialFinal("");
					imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSetorComercialFinal("Setor comercial inexistente.");
					
					httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
					
					throw new ActionServletException("atencao.setor_comercial.inexistente", null, "Localidade");
					
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					
					imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialFinal(
							String.valueOf(objetoSetorComercial.getCodigo()));

					
					httpServletRequest.setAttribute("corSetorComercialDestino", "valor");
					//httpServletRequest.setAttribute("nomeCampo","quadraDestinoNM");
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formul�rio
				httpServletRequest.setAttribute("codigoSetorComercialFinal", "");
				imovelGeracaoTabelasTemporariasCadastroHelper.setCodigoSetorComercialFinal("");
				imovelGeracaoTabelasTemporariasCadastroHelper.setNomeSetorComercialFinal("Informe a localidade da inscri��o de destino.");
				
				httpServletRequest.setAttribute("corSetorComercialDestino", "exception");
			}
		}
	}
	
	private void pesquisarQuadra(
			String inscricaoTipo,
			ImovelGeracaoTabelasTemporariasCadastroHelper imovelGeracaoTabelasTemporariasCadastroHelper,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		String setorComercialCD = imovelGeracaoTabelasTemporariasCadastroHelper.getCodigoSetorComercialInicial();
		String setorComercialID = imovelGeracaoTabelasTemporariasCadastroHelper.getSetorComercialInicial().toString();
		//QUADRA
		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			
			//imovelGeracaoTabelasTemporariasCadastro.setInscricaoTipo("origem");
			
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formul�rio.
			//setorComercialCD = (String) imovelGeracaoTabelasTemporariasCadastro.getCodigoSetorComercialInicial();
			//setorComercialID = (String) imovelGeracaoTabelasTemporariasCadastro.getSetorComercialInicial();
			
			String idLocalidadeInicial = imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeInicial().toString();
			
			// Os campos setorComercialOrigemCD e setorComercialID ser�o
			// obrigat�rios
			if (setorComercialCD != null && !setorComercialCD.trim().equalsIgnoreCase("") &&
					setorComercialID != null && !setorComercialID.trim().equalsIgnoreCase("")) {
				
				String quadraNM = (String) imovelGeracaoTabelasTemporariasCadastroHelper.getQuadraInicial();
				
				// coloca parametro no filtro
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeInicial)));
				
				// Adiciona o id do setor comercial que est� no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o n�mero da quadra que esta no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				Collection colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException("atencao.quadra.inexistente", null, "Localidade");
/*					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formul�rio
					imovelGeracaoTabelasTemporariasCadastro.setQuadraInicial("");
					imovelGeracaoTabelasTemporariasCadastro.setIdQuadraInicial("");
					// Mensagem de tela
					httpServletRequest.setAttribute("msgQuadraInicial", "QUADRA INEXISTENTE");
					//imovelOutrosCriteriosActionForm
						//	.setQuadraMensagemOrigem("Quadra inexistente.");
					httpServletRequest.setAttribute("corQuadraOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo","quadraInicial");
					
					//destino
					
					//imovelGeracaoTabelasTemporariasCadastro.setQuadraFinal("");
					//imovelGeracaoTabelasTemporariasCadastro.setIdQuadraFinal("");
*/				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraInicial(
							String.valueOf(objetoQuadra.getNumeroQuadra()));
					imovelGeracaoTabelasTemporariasCadastroHelper.setIdQuadraInicial(
							objetoQuadra.getId().toString());
					//imovelGeracaoTabelasTemporariasCadastro.setQuadraFinal(
					//		String.valueOf(objetoQuadra.getNumeroQuadra()));
					//imovelGeracaoTabelasTemporariasCadastro.setIdQuadraFinal(
					//		String.valueOf(objetoQuadra.getId()));
					
					httpServletRequest.setAttribute("corQuadraOrigem", null);
					httpServletRequest.setAttribute("nomeCampo","loteOrigem");
				}
			} else {
				// Limpa o campo quadraOrigemNM do formul�rio
				imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraInicial("");
				//imovelGeracaoTabelasTemporariasCadastro.setQuadraMensagemOrigem("Informe o setor comercial da inscri��o de origem.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
			}
		} else {//QUADRA FINAL
			
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formul�rio.
			setorComercialCD = (String) imovelGeracaoTabelasTemporariasCadastroHelper.getCodigoSetorComercialFinal();
			setorComercialID = (String) imovelGeracaoTabelasTemporariasCadastroHelper.getSetorComercialFinal();

			String idLocalidadeFinal = imovelGeracaoTabelasTemporariasCadastroHelper.getLocalidadeFinal().toString();			
			
			// Os campos setorComercialOrigemCD e setorComercialID ser�o
			// obrigat�rios
			if (setorComercialCD != null && !setorComercialCD.trim().equalsIgnoreCase("") &&
					setorComercialID != null && !setorComercialID.trim().equalsIgnoreCase("")) {
				
				String quadraNM = (String) imovelGeracaoTabelasTemporariasCadastroHelper.getQuadraFinal();

				// coloca parametro no filtro
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeFinal)));
				
				// Adiciona o id do setor comercial que est� no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o n�mero da quadra que esta no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				// Retorna quadra
				Collection colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					//Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formul�rio
					imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraFinal("");
					imovelGeracaoTabelasTemporariasCadastroHelper.setIdQuadraFinal("");
					//Mensagem de tela
					//imovelOutrosCriteriosActionForm
						//	.setQuadraMensagemDestino("Quadra inexistente.");
					httpServletRequest.setAttribute("msgQuadraFinal", "QUADRA INEXISTENTE");					
					httpServletRequest.setAttribute("corQuadraDestino", "exception");
					httpServletRequest.setAttribute("nomeCampo","quadraDestinoNM");
					
					throw new ActionServletException("atencao.quadra.inexistente", null, "Localidade");
					
				} else {
					Quadra objetoQuadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
					imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraFinal(
							String.valueOf(objetoQuadra.getNumeroQuadra()));
					imovelGeracaoTabelasTemporariasCadastroHelper.setIdQuadraFinal(
							objetoQuadra.getId().toString());
					httpServletRequest.setAttribute("corQuadraDestino", null);
					//httpServletRequest.setAttribute("nomeCampo","loteDestino");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formul�rio
				imovelGeracaoTabelasTemporariasCadastroHelper.setQuadraFinal("");
				// Mensagem de tela
				//imovelEmissaoOrdensSeletivas.setQuadraMensagemDestino("Informe o setor comercial da inscri��o.");
				httpServletRequest.setAttribute("corQuadraDestino", "exception");
			}
		}

	}
}
