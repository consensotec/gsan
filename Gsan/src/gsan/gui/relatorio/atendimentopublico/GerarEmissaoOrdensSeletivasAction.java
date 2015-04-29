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
package gsan.gui.relatorio.atendimentopublico;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.cadastro.endereco.FiltroLogradouro;
import gsan.cadastro.endereco.Logradouro;
import gsan.cadastro.imovel.AreaConstruidaFaixa;
import gsan.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.atendimentopublico.ordemservico.ImovelEmissaoOrdensSeletivasActionForm;
import gsan.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gsan.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.atendimentopublico.RelatorioEmitirOrdemServicoSeletiva;
import gsan.relatorio.atendimentopublico.RelatorioEmitirOrdemServicoSeletivaAnalitico;
import gsan.relatorio.atendimentopublico.RelatorioEmitirOrdemServicoSeletivaSugestao;
import gsan.relatorio.atendimentopublico.bean.ImovelEmissaoOrdensSeletivasHelper;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.Intervalo;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe 
 *
 * @author Ivan S�rgio, Raphael Rossiter
 * @date 06/11/2007, 17/04/2009
 */
public class GerarEmissaoOrdensSeletivasAction extends ExibidorProcessamentoTarefaRelatorio  {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// cria a vari�vel de retorno
		ActionForward retorno = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ImovelEmissaoOrdensSeletivasActionForm form =
			(ImovelEmissaoOrdensSeletivasActionForm) actionForm;

		if (form.getPerfilImovel() != null && form.
				getPerfilImovel().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			form.setPerfilImovel(null);
		}
		
		if (form.getCategoria() != null && form.
				getCategoria().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			form.setCategoria(null);
		}
		
		if (form.getSubCategoria() != null && form.
				getSubCategoria().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			form.setSubCategoria(null);
		}
		
		if (form.getIntervaloAreaConstruidaInicial() != null &&
				!form.getIntervaloAreaConstruidaInicial().equals("") &&
				form.getIntervaloAreaConstruidaFinal() != null &&
				!form.getIntervaloAreaConstruidaFinal().equals("")) {
			
			String areaInicial = form.getIntervaloAreaConstruidaInicial();
			String areaFinal = form.getIntervaloAreaConstruidaFinal();
			
			//@@
			if (form.getNumeroOcorrenciasConsecutivas() != null && !form.getNumeroOcorrenciasConsecutivas().equals("1")) {
				areaInicial = areaInicial.replace(".", "");
				areaInicial = areaInicial.replace(",", ".");
				areaFinal = areaFinal.replace(".", "");
				areaFinal = areaFinal.replace(",", ".");
			}
			
			form.setIntervaloAreaConstruidaInicial(areaInicial);
			form.setIntervaloAreaConstruidaFinal(areaFinal);
		}
		
		if (form.getIntervaloAreaConstruidaPredefinida() != null) {
			if (form.getIntervaloAreaConstruidaPredefinida().trim().
					equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				form.setIntervaloAreaConstruidaPredefinida(null);
			}
			
			if (form.getIntervaloAreaConstruidaPredefinida() != null) {
				// Verifica se o usuario informou o Intervalo de Area Construida Pre definida
				FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = new FiltroAreaConstruidaFaixa();	
				
				filtroAreaConstruidaFaixa.adicionarParametro(new ParametroSimples(
						FiltroAreaConstruidaFaixa.CODIGO, form.
						getIntervaloAreaConstruidaPredefinida()));

				Collection<AreaConstruidaFaixa> colecaoAreaConstruida = fachada.pesquisar(filtroAreaConstruidaFaixa,
						AreaConstruidaFaixa.class.getName());
				
				if(colecaoAreaConstruida != null & !colecaoAreaConstruida.isEmpty()) {
					AreaConstruidaFaixa faixa = colecaoAreaConstruida.iterator().next();
					
					// Seta os Intervalos
					form.setIntervaloAreaConstruidaInicial(faixa.getMenorFaixa().toString());
					form.setIntervaloAreaConstruidaFinal(faixa.getMaiorFaixa().toString());
				}
			}
		}
		
		if (form.getImovelCondominio() == null) {
			form.setImovelCondominio("2");
		}
		
		if (form.getTipoMedicao() != null && form.
				getTipoMedicao().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			form.setTipoMedicao(null);
		}else {
			if (form.getTipoMedicao() == null) {
				form.setTipoMedicao(""+MedicaoTipo.LIGACAO_AGUA);
			}
		}
		
/*		if (form.getCapacidade() != null && form.
				getCapacidade().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			form.setCapacidade(null);
		}
*/		
		// Local Instala��o Hidrometro
		if(form.getLocalInstalacao() != null && 
				form.getLocalInstalacao().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			form.setLocalInstalacao(null);
		}
		
		if (form.getMarca() != null && form.
				getMarca().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			form.setMarca(null);
		}
		
		if (form.getMesAnoInstalacaoInicial() != null) {
			if (!form.getMesAnoInstalacaoInicial().equals("")) {
				if (form.getMesAnoInstalacaoInicial().contains("/")) {
					String anoMesInicial = form.getMesAnoInstalacaoInicial();
					anoMesInicial = anoMesInicial.replace("/", "");
					anoMesInicial = anoMesInicial.substring(2, 6) + anoMesInicial.substring(0, 2);
					
					form.setMesAnoInstalacaoInicial(anoMesInicial);
				}else{
					String anoMesInicial = form.getMesAnoInstalacaoInicial();
					form.setMesAnoInstalacaoInicial(anoMesInicial);
				}
			}else {
				form.setMesAnoInstalacaoInicial(null);
			}
		}
		
		if (form.getMesAnoInstalacaoFinal() != null) {
			if (!form.getMesAnoInstalacaoFinal().equals("")) {
				if (form.getMesAnoInstalacaoFinal().contains("/")) {
					
					String anoMesFinal = form.getMesAnoInstalacaoFinal();
					
					anoMesFinal = anoMesFinal.replace("/", "");
					anoMesFinal = anoMesFinal.substring(2, 6) + anoMesFinal.substring(0, 2);
					
					form.setMesAnoInstalacaoFinal(anoMesFinal);
				}else{
					String anoMesFinal = form.getMesAnoInstalacaoFinal();
					form.setMesAnoInstalacaoFinal(anoMesFinal);
				}
			}else {
				form.setMesAnoInstalacaoFinal(null);
			}
		}
		
		//IMOVEL
		
		//validar Existencia do Im�vel Informado
		Integer idOsGerada = null;
		Boolean validouImovel = false;
		if (form.getIdImovel() != null 
				&& !form.getIdImovel().equals("")){
				
				Integer idImovel = new Integer(form.getIdImovel());
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel ));
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO, 
						ConstantesSistema.INDICADOR_USO_DESATIVO ));
				
				Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
				
				if (colecaoImovel != null && !colecaoImovel.isEmpty()){
					Imovel imovel = (Imovel)Util.retonarObjetoDeColecao(colecaoImovel);
					form.setInscricaoImovel(imovel.getInscricaoFormatada());
					validouImovel = true;
					limpaDemaisCampos(form);
				}else{
					throw new ActionServletException("atencao.imovel.inexistente", null, "");
				}
		}else{
			form.setIdImovel(null);
		}
		
		String tipo = (String) sessao.getAttribute("tipoRelatorio");
		
		// Verifica se o usuario selecionou sugestao
		if (!form.getSugestao().equals("1")) {			
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			RelatorioEmitirOrdemServicoSeletiva relatorioEmitirOrdemServicoSeletiva =
			new RelatorioEmitirOrdemServicoSeletiva( (Usuario)(httpServletRequest.getSession(false)).
			getAttribute("usuarioLogado"));
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("tipoOrdem",
			form.getTipoOrdem());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("usuarioLogado",usuarioLogado);
			
			relatorioEmitirOrdemServicoSeletiva.addParametro("sugestao",
			form.getSugestao());
			
			relatorioEmitirOrdemServicoSeletiva.addParametro("firma",
			form.getFirma());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("nomeFirma",
			form.getNomeFirma());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("quantidadeMaxima",
			form.getQuantidadeMaxima());
			
			relatorioEmitirOrdemServicoSeletiva.addParametro("descricaoComando", form.getDescricaoComando());
			
			//GERENCIA REGIONAL
			if (form.getGerenciaRegional() != null && !form.
				getGerenciaRegional().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				GerenciaRegional gerenciaRegional = this.pesquisaGerenciaRegional(
				Integer.parseInt(form.getGerenciaRegional()));
				
				relatorioEmitirOrdemServicoSeletiva.addParametro("gerenciaRegional", 
				gerenciaRegional.getId().toString());
				
				relatorioEmitirOrdemServicoSeletiva.addParametro("nomeGerenciaRegional", 
				gerenciaRegional.getNome());
			}
			
			//UNIDADE NEGOCIO
			if (form.getUnidadeNegocio() != null && !form.
				getUnidadeNegocio().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				UnidadeNegocio unidadeNegocio = this.pesquisaUnidadeNegocio(
				Integer.parseInt(form.getUnidadeNegocio()));
						
				relatorioEmitirOrdemServicoSeletiva.addParametro("unidadeNegocio", 
				unidadeNegocio.getId().toString());
						
				relatorioEmitirOrdemServicoSeletiva.addParametro("nomeUnidadeNegocio", 
				unidadeNegocio.getNome());
			}
			
			// Valida o Elo e recupera a Descricao
			if (form.getElo() != null && !form.getElo().equals("")) {
				Localidade localidadeElo = pesquisaElo(Integer.decode(form.getElo()));
				
				if (localidadeElo != null) {
					relatorioEmitirOrdemServicoSeletiva.addParametro("elo",
							localidadeElo.getLocalidade().getId().toString());
							
					relatorioEmitirOrdemServicoSeletiva.addParametro("nomeElo",
							localidadeElo.getLocalidade().getDescricao());
				}else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Elo");
				}
			}else {
				relatorioEmitirOrdemServicoSeletiva.addParametro("elo", "");
				relatorioEmitirOrdemServicoSeletiva.addParametro("nomeElo", "");
			}
			
			//LOGRADOURO
			if (form.getLogradouro() != null && 
				!form.getLogradouro().equals("")) {
				
				Logradouro logradouro = pesquisarLogradouro(Integer.decode(form.getLogradouro()));
				
				if (logradouro != null) {
					
					relatorioEmitirOrdemServicoSeletiva.addParametro("logradouro",
					logradouro.getId().toString());
							
					relatorioEmitirOrdemServicoSeletiva.addParametro("descricaoLogradouro",
					logradouro.getDescricaoFormatada());
				}
				else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Logradouro");
				}
			}
			else {
				relatorioEmitirOrdemServicoSeletiva.addParametro("logradouro", "");
				relatorioEmitirOrdemServicoSeletiva.addParametro("descricaoLogradouro", "");
			}
			
			relatorioEmitirOrdemServicoSeletiva.addParametro("inscricaoTipo",
			form.getInscricaoTipo());
				
			// Valida a Localidade e recupera a Descricao
			if (form.getLocalidadeInicial() != null &&
					!form.getLocalidadeInicial().equals("") &&
					form.getLocalidadeFinal() != null &&
					!form.getLocalidadeInicial().equals("")) {
				
				Localidade localidade = pesquisaLocalidade(Integer.decode(
						form.getLocalidadeInicial()));
				
				if (localidade != null) {
					relatorioEmitirOrdemServicoSeletiva.addParametro("localidadeInicial",
							localidade.getId().toString());
							
					relatorioEmitirOrdemServicoSeletiva.addParametro("nomeLocalidadeInicial",
							localidade.getDescricao());
					
					// Verifica se eh a mesma localidade
					if (form.getLocalidadeInicial().equals(
							form.getLocalidadeFinal())) {
						
						relatorioEmitirOrdemServicoSeletiva.addParametro("localidadeFinal",
								localidade.getId().toString());
								
						relatorioEmitirOrdemServicoSeletiva.addParametro("nomeLocalidadeFinal",
								localidade.getDescricao());
					}else {
						Localidade localidadeFinal = pesquisaLocalidade(Integer.decode(
								form.getLocalidadeFinal()));
						
						if (localidadeFinal != null) {
							relatorioEmitirOrdemServicoSeletiva.addParametro("localidadeFinal",
									localidadeFinal.getId().toString());
									
							relatorioEmitirOrdemServicoSeletiva.addParametro("nomeLocalidadeFinal",
									localidadeFinal.getDescricao());
						}else {
							throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade");
						}
					}
				}else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade");
				}
			}else {
				relatorioEmitirOrdemServicoSeletiva.addParametro("localidadeInicial", "");
				relatorioEmitirOrdemServicoSeletiva.addParametro("nomeLocalidadeInicial", "");
				relatorioEmitirOrdemServicoSeletiva.addParametro("localidadeFinal", "");
				relatorioEmitirOrdemServicoSeletiva.addParametro("nomeLocalidadeFinal", "");
			}
			
			// Valida o Setor Comercial e recupera a Descricao
			if (form.getCodigoSetorComercialInicial() != null &&
					!form.getCodigoSetorComercialInicial().equals("") &&
					form.getCodigoSetorComercialFinal() != null &&
					!form.getCodigoSetorComercialFinal().equals("")) {
				
				if (form.getLocalidadeInicial() != null &&
						!form.getLocalidadeInicial().equals("")) {
					
					SetorComercial setorComercial = pesquisaSetorComercial(
							Integer.decode(form.getCodigoSetorComercialInicial()),
							Integer.decode(form.getLocalidadeInicial()),
							Integer.decode(form.getLocalidadeFinal()));
					
					if (setorComercial != null) {
						relatorioEmitirOrdemServicoSeletiva.addParametro("setorComercialInicial",
								setorComercial.getId().toString());
								
						relatorioEmitirOrdemServicoSeletiva.addParametro("codigoSetorComercialInicial",
								Util.converterObjetoParaString(setorComercial.getCodigo()));
								
						relatorioEmitirOrdemServicoSeletiva.addParametro("nomeSetorComercialInicial",
								setorComercial.getDescricao());
						
						// Verifica se eh o mesmo Setor
						if (form.getCodigoSetorComercialInicial().equals(
								form.getCodigoSetorComercialFinal())) {
							relatorioEmitirOrdemServicoSeletiva.addParametro("setorComercialFinal",
									setorComercial.getId().toString());
								
							relatorioEmitirOrdemServicoSeletiva.addParametro("codigoSetorComercialFinal",
									Util.converterObjetoParaString(setorComercial.getCodigo()));
									
							relatorioEmitirOrdemServicoSeletiva.addParametro("nomeSetorComercialFinal",
									setorComercial.getDescricao());
						}else {
							SetorComercial setorComercialFinal = pesquisaSetorComercial(
									Integer.decode(form.getCodigoSetorComercialFinal()),
									Integer.decode(form.getLocalidadeInicial()),
									Integer.decode(form.getLocalidadeFinal()));
							
							if (setorComercialFinal != null) {
								relatorioEmitirOrdemServicoSeletiva.addParametro("setorComercialFinal",
										setorComercialFinal.getId().toString());
									
								relatorioEmitirOrdemServicoSeletiva.addParametro("codigoSetorComercialFinal",
										Util.converterObjetoParaString(setorComercialFinal.getCodigo()));
										
								relatorioEmitirOrdemServicoSeletiva.addParametro("nomeSetorComercialFinal",
										setorComercialFinal.getDescricao());
							}else {
								throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
							}
						}
					}else {
						throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
					}
				}else {
					throw new ActionServletException("atencao.localidade_nao_informada");
				}
			}else {
				relatorioEmitirOrdemServicoSeletiva.addParametro("setorComercialInicial", "");
				relatorioEmitirOrdemServicoSeletiva.addParametro("codigoSetorComercialInicial", "");
				relatorioEmitirOrdemServicoSeletiva.addParametro("nomeSetorComercialInicial", "");
				relatorioEmitirOrdemServicoSeletiva.addParametro("setorComercialFinal", "");
				relatorioEmitirOrdemServicoSeletiva.addParametro("codigoSetorComercialFinal", "");
				relatorioEmitirOrdemServicoSeletiva.addParametro("nomeSetorComercialFinal", "");
			}
			
			// Valida a Quadra
			if (form.getQuadraInicial() != null &&
					!form.getQuadraInicial().equals("") &&
					form.getQuadraFinal() != null &&
					!form.getQuadraFinal().equals("")) {
				
				if (form.getLocalidadeInicial() != null &&
						!form.getLocalidadeInicial().equals("")) {
					
					if (form.getCodigoSetorComercialInicial() != null &&
							!form.getCodigoSetorComercialInicial().equals("")) {
						Quadra quadra = pesquisaQuadra(
								Integer.decode(form.getQuadraInicial()),
								Integer.decode(form.getLocalidadeInicial()),
								Integer.decode(form.getLocalidadeFinal()),
								Integer.decode(form.getCodigoSetorComercialInicial()),
								Integer.decode(form.getCodigoSetorComercialFinal()));
						
						if (quadra != null) {
							relatorioEmitirOrdemServicoSeletiva.addParametro("quadraInicial",
									""+quadra.getNumeroQuadra());
									
							relatorioEmitirOrdemServicoSeletiva.addParametro("idQuadraInicial",
									quadra.getId().toString());
							
							// Verifica se eh a mesma Quadra
							if (form.getQuadraInicial().equals(
									form.getQuadraFinal())) {
								
								relatorioEmitirOrdemServicoSeletiva.addParametro("quadraFinal",
										""+quadra.getNumeroQuadra());
										
								relatorioEmitirOrdemServicoSeletiva.addParametro("idQuadraFinal",
										quadra.getId().toString());
								
							}else {
								quadra = pesquisaQuadra(
										Integer.decode(form.getQuadraFinal()),
										Integer.decode(form.getLocalidadeInicial()),
										Integer.decode(form.getLocalidadeFinal()),
										Integer.decode(form.getCodigoSetorComercialInicial()),
										Integer.decode(form.getCodigoSetorComercialFinal()));
								
								if (quadra != null) {
									relatorioEmitirOrdemServicoSeletiva.addParametro("quadraFinal",
											quadra.getNumeroQuadra());
											
									relatorioEmitirOrdemServicoSeletiva.addParametro("idQuadraFinal",
											quadra.getId().toString());
								}else {
									throw new ActionServletException("atencao.pesquisa_inexistente", null, "Quadra");
								}
							}
						}else {
							throw new ActionServletException("atencao.pesquisa_inexistente", null, "Quadra");
						}
					}else {
						throw new ActionServletException("atencao.setor_comercial_nao_informado");
					}
					
				}else {
					throw new ActionServletException("atencao.localidade_nao_informada");
				}
			}else {
				relatorioEmitirOrdemServicoSeletiva.addParametro("quadraInicial", "");
				relatorioEmitirOrdemServicoSeletiva.addParametro("idQuadraInicial", "");
				relatorioEmitirOrdemServicoSeletiva.addParametro("quadraFinal", "");
				relatorioEmitirOrdemServicoSeletiva.addParametro("idQuadraFinal", "");
			}
			
			relatorioEmitirOrdemServicoSeletiva.addParametro("rotaInicial",
				form.getRotaInicial());
			
			relatorioEmitirOrdemServicoSeletiva.addParametro("rotaSequenciaInicial",
				form.getRotaSequenciaInicial());
	
			relatorioEmitirOrdemServicoSeletiva.addParametro("rotaFinal",
				form.getRotaFinal());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("rotaSequenciaFinal",
				form.getRotaSequenciaFinal());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("perfilImovel",
				form.getPerfilImovel());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("categoria",
				form.getCategoria());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("subCategoria",
				form.getSubCategoria());
			
			relatorioEmitirOrdemServicoSeletiva.addParametro("situacaoLigacaoAgua", form.getSituacaoLigacaoAgua());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("intervaloQuantidadeEconomiasInicial",
				form.getIntervaloQuantidadeEconomiasInicial());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("intervaloQuantidadeEconomiasFinal",
				form.getIntervaloQuantidadeEconomiasFinal());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("intervaloQuantidadeDocumentosInicial",
				form.getIntervaloQuantidadeDocumentosInicial());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("intervaloQuantidadeDocumentosFinal",
				form.getIntervaloQuantidadeDocumentosFinal());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("intervaloNumeroMoradoresInicial",
				form.getIntervaloNumeroMoradoresInicial());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("intervaloNumeroMoradoresFinal",
				form.getIntervaloNumeroMoradoresFinal());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("intervaloAreaConstruidaInicial",
				form.getIntervaloAreaConstruidaInicial());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("intervaloAreaConstruidaFinal",
				form.getIntervaloAreaConstruidaFinal());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("intervaloAreaConstruidaPredefinida",
				form.getIntervaloAreaConstruidaPredefinida());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("imovelCondominio",
				form.getImovelCondominio());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("mediaImovel",
				form.getMediaImovel());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("consumoPorEconomia",
				form.getConsumoPorEconomia());

			relatorioEmitirOrdemServicoSeletiva.addParametro("consumoPorEconomiaFinal",
					form.getConsumoPorEconomiaFinal());
			
			relatorioEmitirOrdemServicoSeletiva.addParametro("tipoMedicao",
				form.getTipoMedicao());
			
			relatorioEmitirOrdemServicoSeletiva.addParametro("capacidade",
				form.getCapacidade());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("marca",
				form.getMarca());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("anormalidadeHidrometro",
				form.getAnormalidadeHidrometro());
			
			relatorioEmitirOrdemServicoSeletiva.addParametro("numeroOcorrenciasConsecutivas",
				form.getNumeroOcorrenciasConsecutivas());
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("mesAnoInstalacaoInicial",
				form.getMesAnoInstalacaoInicial());
			
			relatorioEmitirOrdemServicoSeletiva.addParametro("mesAnoInstalacaoFinal",
					form.getMesAnoInstalacaoFinal());
			
			if(form.getIdProjeto()!=null 
					&& !form.getIdProjeto().equals("")){
				relatorioEmitirOrdemServicoSeletiva.addParametro("idProjeto",
						form.getIdProjeto());
			}
			
			// Fim da parte que vai mandar os parametros para o relat�rio
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
				
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
				
			relatorioEmitirOrdemServicoSeletiva.addParametro("tipoFormatoRelatorio",
					Integer.parseInt(tipoRelatorio));
			
			relatorioEmitirOrdemServicoSeletiva.addParametro("idImovel",
					form.getIdImovel());
			
			// Local Instala��o Hidrometro
			if(form.getLocalInstalacao() != null && !form.getLocalInstalacao().equals("")){
				relatorioEmitirOrdemServicoSeletiva.addParametro("localInstalacao", form.getLocalInstalacao());
			}
			Integer idImovel = null;	
			
			relatorioEmitirOrdemServicoSeletiva.addParametro("tipoEmissao", tipo);
			
			if (validouImovel){
				ImovelEmissaoOrdensSeletivasHelper helper = gerarObjetoHelper(relatorioEmitirOrdemServicoSeletiva);				
				
				//[UC0711] Filtro para Emissao de Ordens Seletivas
				List<Integer[]> colecaoDadosRelatorio = (List<Integer[]>) fachada.filtrarImovelEmissaoOrdensSeletivas(helper);
				
				if (colecaoDadosRelatorio != null && !colecaoDadosRelatorio.isEmpty()) {
					Integer constante =0;
					Integer idEmpresa = 0;
					int indice = 0;
					
					if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO)) {
						constante = ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO;
					}else if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_SUBSTITUICAO)){
						constante = ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO;
					}else if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_INSPECAO_ANORMALIDADE)){
						constante = ServicoTipo.TIPO_INSPECAO_ANORMALIDADE;
					}else if (helper.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_SUBSTITUICAO_COM_REMOCAO)){
						constante = ServicoTipo.TIPO_SUBSTITUICAO_COM_REMOCAO;
					}else {
						constante = ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO;
					}
					
					ServicoTipo servTipo = this.pesquisaServicoTipo(constante, fachada);
					
					idEmpresa = Util.converterStringParaInteger(helper.getFirma());
					//caso exista apenas um im�vel na lista, exibir a menssagem " Num. da OS gerada
					//<<num. da ordem de servi�o gerada>>. Encerrar Caso de Uso.
					if (colecaoDadosRelatorio.size() == 1){
						
						Integer[] dadosImovel = colecaoDadosRelatorio.get(indice); 
						
						idImovel = dadosImovel[0];
						Integer idAnormalidade = dadosImovel[1];
						
						//adicionado por Vivianne Sousa - 27/06/2011 - analista:Claudio Lira
						//[SB0001]-Gerar Comando. 
						Integer idComandoOrdemSeletiva = 
							this.getFachada().gerarComando(helper,
								colecaoDadosRelatorio.size(),
								usuarioLogado);

						// [UC0430] - Gerar Ordem de Servico
						idOsGerada = 
							geraOrdemServico(servTipo, 
								idEmpresa, 
								idImovel,
								idComandoOrdemSeletiva,
								idAnormalidade);
					}
					
				}else{
					throw new ActionServletException("atencao.pesquisa.nenhumresultado");
				}
			}
		
			if (idImovel == null){
				try {
					retorno = processarExibicaoRelatorio(
							relatorioEmitirOrdemServicoSeletiva,
							tipoRelatorio, httpServletRequest,
							httpServletResponse, actionMapping);
					
				} catch (TarefaException e) {
					if(e.getParametroMensagem()!=null){
						throw new ActionServletException(e.getMessage(),e.getParametroMensagem());
					}else{
						throw new ActionServletException(e.getMessage());
					}
				}
			}else{
				retorno = actionMapping.findForward("telaSucesso");

				 montarPaginaSucesso(httpServletRequest, "Ordem de Servi�o " + idOsGerada
				 + " emitida com sucesso.", "Emitir outra Ordem de Servi�o Seletiva",
				 "exibirFiltrarImovelEmissaoOrdensSeletivas.do?menu=sim");
			}
			
		    
		  	
			
		}else {
			
			
			//[CRC3256]
			// Analista: Rosana Carvalho 
			// Programador: Hugo Amorim
			// Data: 03/03/2010
			//  
			// Inclus�o de mais um tipo de relatorio 
			// no step que gera as OS Seletivas
			//
			// Relatorio Analitico
			 if(tipo.equals("ANALITICO")){
				
				
				RelatorioEmitirOrdemServicoSeletivaAnalitico 
					relatorioAnalitico =
						new RelatorioEmitirOrdemServicoSeletivaAnalitico(
							(Usuario)(httpServletRequest.getSession(false))
								.getAttribute("usuarioLogado"));
				
				// Adiciona os parametros do filtro no objeto
				// do relatorio do tipo analitico
				this.adicionarParametrosRelatorioAnalitico(form,relatorioAnalitico, tipo);

				String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");			
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
						
				relatorioAnalitico.addParametro("tipoFormatoRelatorio",
						Integer.parseInt(tipoRelatorio));
				
				retorno = processarExibicaoRelatorio(
						relatorioAnalitico,
						tipoRelatorio, httpServletRequest,
						httpServletResponse, actionMapping);
				
				
			}else{
			
			RelatorioEmitirOrdemServicoSeletivaSugestao relatorioEmitirOrdemServicoSeletivaSugestao =
			new RelatorioEmitirOrdemServicoSeletivaSugestao(
			(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("tipoOrdem",
				form.getTipoOrdem());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("sugestao",
				form.getSugestao());
			
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("firma",
				form.getFirma());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeFirma",
				form.getNomeFirma());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("quantidadeMaxima",
				form.getQuantidadeMaxima());
			
			//IMOVEL
			
			//validar Existencia do Im�vel Informado
			if (form.getIdImovel() != null 
					&& !form.getIdImovel().equals("")){
					
					Integer idImovel = new Integer(form.getIdImovel());
					FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
					filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
					filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel ));
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO, 
							ConstantesSistema.INDICADOR_USO_DESATIVO ));
					
					Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
					
					if (colecaoImovel != null && !colecaoImovel.isEmpty()){
						Imovel imovel = (Imovel)Util.retonarObjetoDeColecao(colecaoImovel);
						form.setInscricaoImovel(imovel.getInscricaoFormatada());
						
					}else{
						throw new ActionServletException("atencao.imovel.inexistente", null, "");
					}
			}else{
				form.setIdImovel(null);
			}
			
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("idImovel",
					form.getIdImovel());
			
			//GERENCIA REGIONAL
			if (form.getGerenciaRegional() != null && !form.
				getGerenciaRegional().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				GerenciaRegional gerenciaRegional = this.pesquisaGerenciaRegional(
				Integer.parseInt(form.getGerenciaRegional()));
				
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("gerenciaRegional", 
				gerenciaRegional.getId().toString());
				
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeGerenciaRegional", 
				gerenciaRegional.getNome());
			}
			
			//UNIDADE NEGOCIO
			if (form.getUnidadeNegocio() != null && !form.
				getUnidadeNegocio().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				UnidadeNegocio unidadeNegocio = this.pesquisaUnidadeNegocio(
				Integer.parseInt(form.getUnidadeNegocio()));
						
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("unidadeNegocio", 
				unidadeNegocio.getId().toString());
						
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeUnidadeNegocio", 
				unidadeNegocio.getNome());
			}
				
			// Valida o Elo e recupera a Descricao
			if (form.getElo() != null && !form.getElo().equals("")) {
				Localidade localidadeElo = pesquisaElo(Integer.decode(form.getElo()));
				
				if (localidadeElo != null) {
					relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("elo",
							localidadeElo.getLocalidade().getId().toString());
							
					relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeElo",
							localidadeElo.getLocalidade().getDescricao());
				}else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Elo");
				}
			}else {
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("elo", "");
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeElo", "");
			}
			
			//LOGRADOURO
			if (form.getLogradouro() != null && 
				!form.getLogradouro().equals("")) {
				
				Logradouro logradouro = pesquisarLogradouro(Integer.decode(form.getLogradouro()));
				
				if (logradouro != null) {
					
					relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("logradouro",
					logradouro.getId().toString());
							
					relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("descricaoLogradouro",
					logradouro.getDescricaoFormatada());
				}
				else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Logradouro");
				}
			}
			else {
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("logradouro", "");
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("descricaoLogradouro", "");
			}
			
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("inscricaoTipo",
				form.getInscricaoTipo());
				
			// Valida a Localidade e recupera a Descricao
			if (form.getLocalidadeInicial() != null &&
					!form.getLocalidadeInicial().equals("") &&
					form.getLocalidadeFinal() != null &&
					!form.getLocalidadeInicial().equals("")) {
				
				Localidade localidade = pesquisaLocalidade(Integer.decode(
						form.getLocalidadeInicial()));
				
				if (localidade != null) {
					relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("localidadeInicial",
							localidade.getId().toString());
							
					relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeLocalidadeInicial",
							localidade.getDescricao());
					
					// Verifica se eh a mesma localidade
					if (form.getLocalidadeInicial().equals(
							form.getLocalidadeFinal())) {
						
						relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("localidadeFinal",
								localidade.getId().toString());
								
						relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeLocalidadeFinal",
								localidade.getDescricao());
					}else {
						Localidade localidadeFinal = pesquisaLocalidade(Integer.decode(
								form.getLocalidadeFinal()));
						
						if (localidadeFinal != null) {
							relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("localidadeFinal",
									localidadeFinal.getId().toString());
									
							relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeLocalidadeFinal",
									localidadeFinal.getDescricao());
						}else {
							throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade");
						}
					}
				}else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade");
				}
			}else {
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("localidadeInicial", "");
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeLocalidadeInicial", "");
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("localidadeFinal", "");
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeLocalidadeFinal", "");
			}
				
			// Valida o Setor Comercial e recupera a Descricao
			if (form.getCodigoSetorComercialInicial() != null &&
					!form.getCodigoSetorComercialInicial().equals("") &&
					form.getCodigoSetorComercialFinal() != null &&
					!form.getCodigoSetorComercialFinal().equals("")) {
				
				if (form.getLocalidadeInicial() != null &&
						!form.getLocalidadeInicial().equals("")) {
					
					SetorComercial setorComercial = pesquisaSetorComercial(
							Integer.decode(form.getCodigoSetorComercialInicial()),
							Integer.decode(form.getLocalidadeInicial()),
							Integer.decode(form.getLocalidadeFinal()));
					
					if (setorComercial != null) {
						relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("setorComercialInicial",
								setorComercial.getId().toString());
								
						relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("codigoSetorComercialInicial",
								Util.converterObjetoParaString(setorComercial.getCodigo()));
								
						relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeSetorComercialInicial",
								setorComercial.getDescricao());
						
						// Verifica se eh o mesmo Setor
						if (form.getCodigoSetorComercialInicial().equals(
								form.getCodigoSetorComercialFinal())) {
							relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("setorComercialFinal",
									setorComercial.getId().toString());
								
							relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("codigoSetorComercialFinal",
									Util.converterObjetoParaString(setorComercial.getCodigo()));
									
							relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeSetorComercialFinal",
									setorComercial.getDescricao());
						}else {
							SetorComercial setorComercialFinal = pesquisaSetorComercial(
									Integer.decode(form.getCodigoSetorComercialFinal()),
									Integer.decode(form.getLocalidadeInicial()),
									Integer.decode(form.getLocalidadeFinal()));
							
							if (setorComercialFinal != null) {
								relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("setorComercialFinal",
										setorComercialFinal.getId().toString());
									
								relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("codigoSetorComercialFinal",
										Util.converterObjetoParaString(setorComercialFinal.getCodigo()));
										
								relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeSetorComercialFinal",
										setorComercialFinal.getDescricao());
							}else {
								throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
							}
						}
					}else {
						throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
					}
				}else {
					throw new ActionServletException("atencao.localidade_nao_informada");
				}
				
			}else {
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("setorComercialInicial", "");
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("codigoSetorComercialInicial", "");
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeSetorComercialInicial", "");
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("setorComercialFinal", "");
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("codigoSetorComercialFinal", "");
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("nomeSetorComercialFinal", "");
			}
			
			// Valida a Quadra
			if (form.getQuadraInicial() != null &&
					!form.getQuadraInicial().equals("") &&
					form.getQuadraFinal() != null &&
					!form.getQuadraFinal().equals("")) {
				
				if (form.getLocalidadeInicial() != null &&
						!form.getLocalidadeInicial().equals("")) {
					
					if (form.getCodigoSetorComercialInicial() != null &&
							!form.getCodigoSetorComercialInicial().equals("")) {
						
						Quadra quadra = pesquisaQuadra(
								Integer.decode(form.getQuadraInicial()),
								Integer.decode(form.getLocalidadeInicial()),
								Integer.decode(form.getLocalidadeFinal()),
								Integer.decode(form.getCodigoSetorComercialInicial()),
								Integer.decode(form.getCodigoSetorComercialFinal()));
						
						if (quadra != null) {
							relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("quadraInicial",
									""+quadra.getNumeroQuadra());
									
							relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("idQuadraInicial",
									quadra.getId().toString());
							
							// Verifica se eh a mesma Quadra
							if (form.getQuadraInicial().equals(
									form.getQuadraFinal())) {
								
								relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("quadraFinal",
										""+quadra.getNumeroQuadra());
										
								relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("idQuadraFinal",
										quadra.getId().toString());
								
							}else {
								quadra = pesquisaQuadra(
										Integer.decode(form.getQuadraFinal()),
										Integer.decode(form.getLocalidadeInicial()),
										Integer.decode(form.getLocalidadeFinal()),
										Integer.decode(form.getCodigoSetorComercialInicial()),
										Integer.decode(form.getCodigoSetorComercialFinal()));
								
								if (quadra != null) {
									relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("quadraFinal",
											""+quadra.getNumeroQuadra());
											
									relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("idQuadraFinal",
											quadra.getId().toString());
								}else {
									throw new ActionServletException("atencao.pesquisa_inexistente", null, "Quadra");
								}
							}
						}else {
							throw new ActionServletException("atencao.pesquisa_inexistente", null, "Quadra");
						}
					}else {
						throw new ActionServletException("atencao.setor_comercial_nao_informado");
					}
					
				}else {
					throw new ActionServletException("atencao.localidade_nao_informada");
				}
				
			}else {
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("quadraInicial", "");
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("idQuadraInicial", "");
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("quadraFinal", "");
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("idQuadraFinal", "");
			}
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("rotaInicial",
				form.getRotaInicial());
			
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("rotaSequenciaInicial",
				form.getRotaSequenciaInicial());
	
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("rotaFinal",
				form.getRotaFinal());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("rotaSequenciaFinal",
				form.getRotaSequenciaFinal());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("perfilImovel",
				form.getPerfilImovel());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("categoria",
				form.getCategoria());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("subCategoria",
				form.getSubCategoria());
			
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("situacaoLigacaoAgua", 
					form.getSituacaoLigacaoAgua());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("intervaloQuantidadeEconomiasInicial",
				form.getIntervaloQuantidadeEconomiasInicial());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("intervaloQuantidadeEconomiasFinal",
				form.getIntervaloQuantidadeEconomiasFinal());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("intervaloQuantidadeDocumentosInicial",
				form.getIntervaloQuantidadeDocumentosInicial());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("intervaloQuantidadeDocumentosFinal",
				form.getIntervaloQuantidadeDocumentosFinal());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("intervaloNumeroMoradoresInicial",
				form.getIntervaloNumeroMoradoresInicial());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("intervaloNumeroMoradoresFinal",
				form.getIntervaloNumeroMoradoresFinal());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("intervaloAreaConstruidaInicial",
				form.getIntervaloAreaConstruidaInicial());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("intervaloAreaConstruidaFinal",
				form.getIntervaloAreaConstruidaFinal());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("intervaloAreaConstruidaPredefinida",
				form.getIntervaloAreaConstruidaPredefinida());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("imovelCondominio",
				form.getImovelCondominio());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("mediaImovel",
				form.getMediaImovel());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("consumoPorEconomia",
				form.getConsumoPorEconomia());

			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("consumoPorEconomiaFinal",
					form.getConsumoPorEconomiaFinal());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("tipoMedicao",
				form.getTipoMedicao());
			
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("capacidade",
				form.getCapacidade());
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("marca",
				form.getMarca());
			
			// Local Instala��o do Hidrometro
			if(form.getLocalInstalacao() != null){
				relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("localInstalacao", form.getLocalInstalacao());
			}
			
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("anormalidadeHidrometro",
				form.getAnormalidadeHidrometro());
			
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("numeroOcorrenciasConsecutivas",
				form.getNumeroOcorrenciasConsecutivas());
				
//			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("mesAnoInstalacao",
//				form.getMesAnoInstalacaoInicial());
			
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("mesAnoInstalacaoInicial",
					form.getMesAnoInstalacaoInicial());
			
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("mesAnoInstalacaoFinal", 
					form.getMesAnoInstalacaoFinal());
			
			/***************************************************************************
			 * Usado para as Descricoes
			 **************************************************************************/
			String perfilImovelDescricao = "--";
			if (form.getPerfilImovel() != null) {
				if (!form.getPerfilImovel().equals("-1")) {
					perfilImovelDescricao = form.getPerfilImovelDescricao();
				}
			}
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("perfilImovelDescricao",
					perfilImovelDescricao);
			
			String categoriaDescricao = "--";
			if (form.getCategoria() != null) {
				if (!form.getCategoria().equals("-1")) {
					categoriaDescricao = form.getCategoriaDescricao();
				}
			}
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("categoriaDescricao",
					categoriaDescricao);
			
			String subCategoriaDescricao = "--";
			if (form.getSubCategoria() != null) {
				if (!form.getSubCategoria().equals("-1")) {
					subCategoriaDescricao = form.getSubCategoriaDescricao();
				}
			}
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("subCategoriaDescricao",
					subCategoriaDescricao);
			
			//Descri��o Situa��o Liga��o de �gua
			
			String situacaoLigacaoAguaDescricao = new String();
			if (form.getSituacaoLigacaoAgua()!= null){
				for ( int i = 0; i < form.getSituacaoLigacaoAgua().length; i++ ){
					
					FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
					filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
							FiltroLigacaoAguaSituacao.ID, form.getSituacaoLigacaoAgua()[i]));
					
					Collection colecaoLigacaoAguaSituacao = Fachada.getInstancia()
								.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
					
					LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
					
					situacaoLigacaoAguaDescricao = situacaoLigacaoAguaDescricao + ligacaoAguaSituacao.getDescricao()+"\n";
					
				}
			}
			
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("situacaoLigacaoAguaDescricao",
					situacaoLigacaoAguaDescricao);
			
			String quantidadeEconomia = "--";
			if (form.getIntervaloQuantidadeEconomiasInicial() != null &&
					form.getIntervaloQuantidadeEconomiasFinal() != null) {
				if (!form.getIntervaloQuantidadeEconomiasInicial().equals("") &&
						!form.getIntervaloQuantidadeEconomiasFinal().equals("")) {
					quantidadeEconomia = form.getIntervaloQuantidadeEconomiasInicial() +
								" � " + form.getIntervaloQuantidadeEconomiasFinal();
				}
			}
			
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("quantidadeEconomia",
					quantidadeEconomia);
			
			String quantidadeDocumentos = "--";
			if (form.getIntervaloQuantidadeDocumentosInicial() != null &&
					form.getIntervaloQuantidadeDocumentosInicial() != null) {
				if (!form.getIntervaloQuantidadeDocumentosInicial().equals("") &&
						!form.getIntervaloQuantidadeDocumentosInicial().equals("")) {
					quantidadeDocumentos = form.getIntervaloQuantidadeDocumentosInicial() +
								" � " + form.getIntervaloQuantidadeDocumentosFinal();
				}
			}
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("quantidadeDocumentos",
					quantidadeDocumentos);
			
			String numeroMoradores = "--";
			if (form.getIntervaloNumeroMoradoresInicial() != null &&
					form.getIntervaloNumeroMoradoresFinal() != null) {
				if (!form.getIntervaloNumeroMoradoresInicial().equals("") &&
						!form.getIntervaloNumeroMoradoresFinal().equals("")) {
					numeroMoradores = form.getIntervaloNumeroMoradoresInicial() +
								" � " + form.getIntervaloNumeroMoradoresFinal();
				}
			}
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("numeroMoradores",
					numeroMoradores);
			
			String areaConstruida = "--";
			if (form.getIntervaloAreaConstruidaInicial() != null &&
					form.getIntervaloAreaConstruidaFinal() != null) {
				if (!form.getIntervaloAreaConstruidaInicial().equals("") &&
						!form.getIntervaloAreaConstruidaFinal().equals("")) {
					areaConstruida = form.getIntervaloAreaConstruidaInicial() +
								" � " + form.getIntervaloAreaConstruidaFinal();
				}
			}
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("areaConstruida",
					areaConstruida);

			
			String consumoEconomia = "--";
			if (form.getConsumoPorEconomia() != null &&
					form.getConsumoPorEconomiaFinal() != null) {
				if (!form.getConsumoPorEconomia().equals("") &&
						!form.getConsumoPorEconomiaFinal().equals("")) {
					consumoEconomia = form.getConsumoPorEconomia() +
								" � " + form.getConsumoPorEconomiaFinal();
				}
			}
			
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("consumoEconomia",consumoEconomia);
			
			
			
			
			String tipoMedicaoDescricao = "--";
			if (form.getTipoMedicao() != null) {
				if (!form.getTipoMedicao().equals("-1")) {
					tipoMedicaoDescricao = form.getTipoMedicaoDescricao();
				}
			}
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("tipoMedicaoDescricao",
					tipoMedicaoDescricao);
			
			String capacidadeDescricao = "--";
			if (form.getCapacidade() != null) {
				if (!form.getCapacidade().equals("-1")) {
					capacidadeDescricao = form.getCapacidadeDescricao();
				}
			}
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("capacidadeDescricao",
					capacidadeDescricao);
			
			String marcaDescricao = "--";
			if (form.getMarca() != null) {
				if (!form.getMarca().equals("-1")) {
					marcaDescricao = form.getMarcaDescricao();
				}
			}
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("marcaDescricao",
					marcaDescricao);
			
			String localInstalacaoDescricao = "--";
			if(form.getLocalInstalacao() != null){
				if(!form.getLocalInstalacao().equals("-1")){
					localInstalacaoDescricao = form.getLocalInstalacao();
					
					FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = 
						new FiltroHidrometroLocalInstalacao();
					
					filtroHidrometroLocalInstalacao.adicionarParametro(new ParametroSimples(
							FiltroHidrometroLocalInstalacao.ID, localInstalacaoDescricao));
					
					Collection colecaoHidrometroLocalInstalacao = Fachada.getInstancia()
								.pesquisar(filtroHidrometroLocalInstalacao, HidrometroLocalInstalacao.class.getName());
					
					HidrometroLocalInstalacao hidrometroLocalInstalacao = (HidrometroLocalInstalacao) 
						Util.retonarObjetoDeColecao(colecaoHidrometroLocalInstalacao);
					
					localInstalacaoDescricao = hidrometroLocalInstalacao.getDescricao().toString();
				}
			}
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("localInstalacaoDescricao", 
					localInstalacaoDescricao);
			
			String imovelCondominio = "--";
			if (form.getImovelCondominio() != null) {
				if (form.getImovelCondominio().equals("1")) {
					imovelCondominio = "SIM";
				}else {
					imovelCondominio = "N�O";
				}
			}
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("imovelCondominio",
					imovelCondominio);
			
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("tipoEmissao",
					tipo);
			
			// Fim da parte que vai mandar os parametros para o relat�rio
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
				
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
				
			relatorioEmitirOrdemServicoSeletivaSugestao.addParametro("tipoFormatoRelatorio",
					Integer.parseInt(tipoRelatorio));
			
			
				
			retorno = processarExibicaoRelatorio(
						relatorioEmitirOrdemServicoSeletivaSugestao,
						tipoRelatorio, httpServletRequest,
						httpServletResponse, actionMapping);
			}
		}	 
		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}
	/**
	 * Valida dados do form vindos do filtro,
	 * e adiciona os mesmo no objeto RelatorioEmitirOrdemServicoSeletivaAnalitico
	 * 
	 * @param ImovelEmissaoOrdensSeletivasActionForm,RelatorioEmitirOrdemServicoSeletivaAnalitico
	 * 
	 */
	private void adicionarParametrosRelatorioAnalitico(
			ImovelEmissaoOrdensSeletivasActionForm form,
			RelatorioEmitirOrdemServicoSeletivaAnalitico relatorioAnalitico, String tipo) {

		relatorioAnalitico.addParametro("tipoOrdem", form.getTipoOrdem());
		
		relatorioAnalitico.addParametro("tipoEmissao", tipo);

		relatorioAnalitico.addParametro("sugestao", form.getSugestao());

		relatorioAnalitico.addParametro("firma", form.getFirma());

		relatorioAnalitico.addParametro("nomeFirma", form.getNomeFirma());

		relatorioAnalitico.addParametro("quantidadeMaxima", form.getQuantidadeMaxima());
		
		relatorioAnalitico.addParametro("descricaoComando", form.getDescricaoComando());

		// GERENCIA REGIONAL
		if (form.getGerenciaRegional() != null
				&& !form.getGerenciaRegional().trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			GerenciaRegional gerenciaRegional = this
					.pesquisaGerenciaRegional(Integer.parseInt(form
							.getGerenciaRegional()));

			relatorioAnalitico.addParametro("gerenciaRegional",
					gerenciaRegional.getId().toString());

			relatorioAnalitico.addParametro("nomeGerenciaRegional",
					gerenciaRegional.getNome());
		}

		// UNIDADE NEGOCIO
		if (form.getUnidadeNegocio() != null
				&& !form.getUnidadeNegocio().trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			UnidadeNegocio unidadeNegocio = this.pesquisaUnidadeNegocio(Integer
					.parseInt(form.getUnidadeNegocio()));

			relatorioAnalitico.addParametro("unidadeNegocio", unidadeNegocio
					.getId().toString());

			relatorioAnalitico.addParametro("nomeUnidadeNegocio",
					unidadeNegocio.getNome());
		}

		// ELO
		if (form.getElo() != null && !form.getElo().equals("")) {
			Localidade localidadeElo = pesquisaElo(Integer
					.decode(form.getElo()));

			if (localidadeElo != null) {
				relatorioAnalitico.addParametro("elo", localidadeElo
						.getLocalidade().getId().toString());

				relatorioAnalitico.addParametro("nomeElo", localidadeElo
						.getLocalidade().getDescricao());
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Elo");
			}
		} else {
			relatorioAnalitico.addParametro("elo", "");
			relatorioAnalitico.addParametro("nomeElo", "");
		}

		// LOGRADOURO
		if (form.getLogradouro() != null && !form.getLogradouro().equals("")) {

			Logradouro logradouro = pesquisarLogradouro(Integer.decode(form
					.getLogradouro()));

			if (logradouro != null) {

				relatorioAnalitico.addParametro("logradouro", logradouro
						.getId().toString());

				relatorioAnalitico.addParametro("descricaoLogradouro",
						logradouro.getDescricaoFormatada());
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Logradouro");
			}
		} else {
			relatorioAnalitico.addParametro("logradouro", "");
			relatorioAnalitico.addParametro("descricaoLogradouro", "");
		}

		relatorioAnalitico.addParametro("inscricaoTipo", form
				.getInscricaoTipo());

		// LOCALIDADE
		if (form.getLocalidadeInicial() != null
				&& !form.getLocalidadeInicial().equals("")
				&& form.getLocalidadeFinal() != null
				&& !form.getLocalidadeInicial().equals("")) {

			Localidade localidade = pesquisaLocalidade(Integer.decode(form
					.getLocalidadeInicial()));

			if (localidade != null) {
				relatorioAnalitico.addParametro("localidadeInicial", localidade
						.getId().toString());

				relatorioAnalitico.addParametro("nomeLocalidadeInicial",
						localidade.getDescricao());

				// Verifica se eh a mesma localidade
				if (form.getLocalidadeInicial().equals(
						form.getLocalidadeFinal())) {

					relatorioAnalitico.addParametro("localidadeFinal",
							localidade.getId().toString());

					relatorioAnalitico.addParametro("nomeLocalidadeFinal",
							localidade.getDescricao());
				} else {
					Localidade localidadeFinal = pesquisaLocalidade(Integer
							.decode(form.getLocalidadeFinal()));

					if (localidadeFinal != null) {
						relatorioAnalitico.addParametro("localidadeFinal",
								localidadeFinal.getId().toString());

						relatorioAnalitico.addParametro("nomeLocalidadeFinal",
								localidadeFinal.getDescricao());
					} else {
						throw new ActionServletException(
								"atencao.pesquisa_inexistente", null,
								"Localidade");
					}
				}
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Localidade");
			}
		} else {
			relatorioAnalitico.addParametro("localidadeInicial", "");
			relatorioAnalitico.addParametro("nomeLocalidadeInicial", "");
			relatorioAnalitico.addParametro("localidadeFinal", "");
			relatorioAnalitico.addParametro("nomeLocalidadeFinal", "");
		}

		// SETOR COMERCIAL
		if (form.getCodigoSetorComercialInicial() != null
				&& !form.getCodigoSetorComercialInicial().equals("")
				&& form.getCodigoSetorComercialFinal() != null
				&& !form.getCodigoSetorComercialFinal().equals("")) {

			if (form.getLocalidadeInicial() != null
					&& !form.getLocalidadeInicial().equals("")) {

				SetorComercial setorComercial = pesquisaSetorComercial(Integer
						.decode(form.getCodigoSetorComercialInicial()), Integer
						.decode(form.getLocalidadeInicial()), Integer
						.decode(form.getLocalidadeFinal()));

				if (setorComercial != null) {
					relatorioAnalitico.addParametro("setorComercialInicial",
							setorComercial.getId().toString());

					relatorioAnalitico.addParametro(
							"codigoSetorComercialInicial", Util
									.converterObjetoParaString(setorComercial
											.getCodigo()));

					relatorioAnalitico.addParametro(
							"nomeSetorComercialInicial", setorComercial
									.getDescricao());

					// Verifica se eh o mesmo Setor
					if (form.getCodigoSetorComercialInicial().equals(
							form.getCodigoSetorComercialFinal())) {
						relatorioAnalitico.addParametro("setorComercialFinal",
								setorComercial.getId().toString());

						relatorioAnalitico.addParametro(
								"codigoSetorComercialFinal",
								Util.converterObjetoParaString(setorComercial
										.getCodigo()));

						relatorioAnalitico.addParametro(
								"nomeSetorComercialFinal", setorComercial
										.getDescricao());
					} else {
						SetorComercial setorComercialFinal = pesquisaSetorComercial(
								Integer.decode(form
										.getCodigoSetorComercialFinal()),
								Integer.decode(form.getLocalidadeInicial()),
								Integer.decode(form.getLocalidadeFinal()));

						if (setorComercialFinal != null) {
							relatorioAnalitico.addParametro(
									"setorComercialFinal", setorComercialFinal
											.getId().toString());

							relatorioAnalitico
									.addParametro(
											"codigoSetorComercialFinal",
											Util
													.converterObjetoParaString(setorComercialFinal
															.getCodigo()));

							relatorioAnalitico.addParametro(
									"nomeSetorComercialFinal",
									setorComercialFinal.getDescricao());
						} else {
							throw new ActionServletException(
									"atencao.pesquisa_inexistente", null,
									"Setor Comercial");
						}
					}
				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Setor Comercial");
				}
			} else {
				throw new ActionServletException(
						"atencao.localidade_nao_informada");
			}
		} else {
			relatorioAnalitico.addParametro("setorComercialInicial", "");
			relatorioAnalitico.addParametro("codigoSetorComercialInicial", "");
			relatorioAnalitico.addParametro("nomeSetorComercialInicial", "");
			relatorioAnalitico.addParametro("setorComercialFinal", "");
			relatorioAnalitico.addParametro("codigoSetorComercialFinal", "");
			relatorioAnalitico.addParametro("nomeSetorComercialFinal", "");
		}

		// QUADRA
		if (form.getQuadraInicial() != null
				&& !form.getQuadraInicial().equals("")
				&& form.getQuadraFinal() != null
				&& !form.getQuadraFinal().equals("")) {

			if (form.getLocalidadeInicial() != null
					&& !form.getLocalidadeInicial().equals("")) {

				if (form.getCodigoSetorComercialInicial() != null
						&& !form.getCodigoSetorComercialInicial().equals("")) {
					Quadra quadra = pesquisaQuadra(Integer.decode(form
							.getQuadraInicial()), Integer.decode(form
							.getLocalidadeInicial()), Integer.decode(form
							.getLocalidadeFinal()), Integer.decode(form
							.getCodigoSetorComercialInicial()), Integer
							.decode(form.getCodigoSetorComercialFinal()));

					if (quadra != null) {
						relatorioAnalitico.addParametro("quadraInicial", ""
								+ quadra.getNumeroQuadra());

						relatorioAnalitico.addParametro("idQuadraInicial",
								quadra.getId().toString());

						// Verifica se eh a mesma Quadra
						if (form.getQuadraInicial().equals(
								form.getQuadraFinal())) {

							relatorioAnalitico.addParametro("quadraFinal", ""
									+ quadra.getNumeroQuadra());

							relatorioAnalitico.addParametro("idQuadraFinal",
									quadra.getId().toString());

						} else {
							quadra = pesquisaQuadra(Integer.decode(form
									.getQuadraFinal()), Integer.decode(form
									.getLocalidadeInicial()), Integer
									.decode(form.getLocalidadeFinal()), Integer
									.decode(form
											.getCodigoSetorComercialInicial()),
									Integer.decode(form
											.getCodigoSetorComercialFinal()));

							if (quadra != null) {
								relatorioAnalitico.addParametro("quadraFinal",
										quadra.getNumeroQuadra());

								relatorioAnalitico.addParametro(
										"idQuadraFinal", quadra.getId());
							} else {
								throw new ActionServletException(
										"atencao.pesquisa_inexistente", null,
										"Quadra");
							}
						}
					} else {
						throw new ActionServletException(
								"atencao.pesquisa_inexistente", null, "Quadra");
					}
				} else {
					throw new ActionServletException(
							"atencao.setor_comercial_nao_informado");
				}

			} else {
				throw new ActionServletException(
						"atencao.localidade_nao_informada");
			}
		} else {
			relatorioAnalitico.addParametro("quadraInicial", "");
			relatorioAnalitico.addParametro("idQuadraInicial", "");
			relatorioAnalitico.addParametro("quadraFinal", "");
			relatorioAnalitico.addParametro("idQuadraFinal", "");
		}

		relatorioAnalitico.addParametro("rotaInicial", form.getRotaInicial());

		relatorioAnalitico.addParametro("rotaSequenciaInicial",
				form.getRotaSequenciaInicial());

		relatorioAnalitico.addParametro("rotaFinal", form.getRotaFinal());

		relatorioAnalitico.addParametro("rotaSequenciaFinal",
				form.getRotaSequenciaFinal());

		relatorioAnalitico.addParametro("perfilImovel", form.getPerfilImovel());

		relatorioAnalitico.addParametro("categoria", form.getCategoria());

		relatorioAnalitico.addParametro("subCategoria", form.getSubCategoria());

		relatorioAnalitico.addParametro("situacaoLigacaoAgua",
				form.getSituacaoLigacaoAgua());

		relatorioAnalitico.addParametro("intervaloQuantidadeEconomiasInicial",
				form.getIntervaloQuantidadeEconomiasInicial());

		relatorioAnalitico.addParametro("intervaloQuantidadeEconomiasFinal",
				form.getIntervaloQuantidadeEconomiasFinal());

		relatorioAnalitico.addParametro("intervaloQuantidadeDocumentosInicial",
				form.getIntervaloQuantidadeDocumentosInicial());

		relatorioAnalitico.addParametro("intervaloQuantidadeDocumentosFinal",
				form.getIntervaloQuantidadeDocumentosFinal());

		relatorioAnalitico.addParametro("intervaloNumeroMoradoresInicial", 
				form.getIntervaloNumeroMoradoresInicial());

		relatorioAnalitico.addParametro("intervaloNumeroMoradoresFinal", 
				form.getIntervaloNumeroMoradoresFinal());

		relatorioAnalitico.addParametro("intervaloAreaConstruidaInicial", 
				form.getIntervaloAreaConstruidaInicial());

		relatorioAnalitico.addParametro("intervaloAreaConstruidaFinal", 
				form.getIntervaloAreaConstruidaFinal());

		relatorioAnalitico.addParametro("intervaloAreaConstruidaPredefinida",
				form.getIntervaloAreaConstruidaPredefinida());

		relatorioAnalitico.addParametro("imovelCondominio", 
				form.getImovelCondominio());

		relatorioAnalitico.addParametro("mediaImovel", 
				form.getMediaImovel());

		relatorioAnalitico.addParametro("consumoPorEconomia", 
				form.getConsumoPorEconomia());

		relatorioAnalitico.addParametro("consumoPorEconomiaFinal",
				form.getConsumoPorEconomiaFinal());

		relatorioAnalitico.addParametro("tipoMedicao", form.getTipoMedicao());

		relatorioAnalitico.addParametro("capacidade", form.getCapacidade());

		relatorioAnalitico.addParametro("marca", form.getMarca());

		relatorioAnalitico.addParametro("anormalidadeHidrometro", 
				form.getAnormalidadeHidrometro());

		relatorioAnalitico.addParametro("numeroOcorrenciasConsecutivas",
				form.getNumeroOcorrenciasConsecutivas());

		relatorioAnalitico.addParametro("mesAnoInstalacaoInicial", 
				form.getMesAnoInstalacaoInicial());

		relatorioAnalitico.addParametro("mesAnoInstalacaoFinal",
				form.getMesAnoInstalacaoFinal());

		if (form.getIdProjeto() != null && !form.getIdProjeto().equals("")) {
			relatorioAnalitico.addParametro("idProjeto", form.getIdProjeto());
		}
		relatorioAnalitico.addParametro("idImovel", form.getIdImovel());
		
		if(form.getLocalInstalacao() != null && !form.getLocalInstalacao().equals("")){
			relatorioAnalitico.addParametro("localInstalacao", form.getLocalInstalacao());
		}
	}
	
	/**
	 * Pesquisa o Elo da Localidade
	 * @param elo
	 * @return
	 */
	private Localidade pesquisaElo(Integer elo) {
		Fachada fachada = Fachada.getInstancia();
		Localidade localidade = null;
		
		FiltroLocalidade filtro = new FiltroLocalidade();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID_ELO, elo));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtro, Localidade.class.getName());
		localidade = colecaoLocalidade.iterator().next();
		
		return localidade;
	}
	
	/**
	 * Pesquisa a Localidade
	 * @param elo
	 * @return
	 */
	private Localidade pesquisaLocalidade(Integer idLocalidade) {
		Fachada fachada = Fachada.getInstancia();
		Localidade localidade = null;
		
		FiltroLocalidade filtro = new FiltroLocalidade();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, idLocalidade));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtro, Localidade.class.getName());
		localidade = colecaoLocalidade.iterator().next();
		
		return localidade;
	}
	
	/**
	 * Pesquisa o Setor Comercial
	 * @param elo
	 * @return
	 */
	private SetorComercial pesquisaSetorComercial(
			Integer codigoSetorComercial,
			Integer idLocalidadeInicial,
			Integer idLocalidadeFinal) {
		
		Fachada fachada = Fachada.getInstancia();
		SetorComercial setorComercial = null;
		
		FiltroSetorComercial filtro = new FiltroSetorComercial();
		filtro.adicionarParametro(new Intervalo(
				FiltroSetorComercial.ID_LOCALIDADE, idLocalidadeInicial, idLocalidadeFinal));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<SetorComercial> colecaoSetor = fachada.pesquisar(filtro, SetorComercial.class.getName());
		setorComercial = colecaoSetor.iterator().next();
		
		return setorComercial;
	}
	
	/**
	 * 
	 * @param numeroQuadra
	 * @param idLocalidadeInicial
	 * @param idLocalidadeFinal
	 * @param codigoSetorComercialInicial
	 * @param codigoSetorComercialFinal
	 * @return
	 */
	private Quadra pesquisaQuadra(
			Integer numeroQuadra,
			Integer idLocalidadeInicial,
			Integer idLocalidadeFinal,
			Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal) {
		
		Fachada fachada = Fachada.getInstancia();
		Quadra quadra = null;
		
		FiltroQuadra filtro = new FiltroQuadra();
		filtro.adicionarParametro(new Intervalo(
				FiltroQuadra.ID_LOCALIDADE, idLocalidadeInicial, idLocalidadeFinal));
		filtro.adicionarParametro(new Intervalo(
				FiltroQuadra.CODIGO_SETORCOMERCIAL, codigoSetorComercialInicial, codigoSetorComercialFinal));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroQuadra.NUMERO_QUADRA, numeroQuadra));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<Quadra> colecaoQuadra = fachada.pesquisar(filtro, Quadra.class.getName());
		
		if(colecaoQuadra != null && !colecaoQuadra.isEmpty()){
			quadra = colecaoQuadra.iterator().next();	
		}
		
		return quadra;
	}
	
	/**
	 * Pesquisar a Gerencia Regional selecionada
	 *
	 * @author Raphael Rossiter
	 * @date 16/04/2009
	 *
	 * @param idGerenciaRegional
	 * @return GerenciaRegional
	 */
	private GerenciaRegional pesquisaGerenciaRegional(Integer idGerenciaRegional) {
		
		Fachada fachada = Fachada.getInstancia();
		GerenciaRegional gerenciaRegional = null;
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
		FiltroGerenciaRegional.ID, idGerenciaRegional));
			
		Collection<GerenciaRegional> colecaoGerenciaRegional = 
		fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
		
		gerenciaRegional = colecaoGerenciaRegional.iterator().next();
		
		return gerenciaRegional;
	}
	
	/**
	 * Pesquisar a Unidade Neg�cio selecionada
	 *
	 * @author Raphael Rossiter
	 * @date 16/04/2009
	 *
	 * @param idUnidadeNegocio
	 * @return UnidadeNegocio
	 */
	private UnidadeNegocio pesquisaUnidadeNegocio(Integer idUnidadeNegocio) {
		
		Fachada fachada = Fachada.getInstancia();
		UnidadeNegocio unidadeNegocio = null;
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
		FiltroUnidadeNegocio.ID, idUnidadeNegocio));
			
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
		fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		
		unidadeNegocio = colecaoUnidadeNegocio.iterator().next();
		
		return unidadeNegocio;
	}
	
	private Logradouro pesquisarLogradouro(Integer idLogradouro) {

		Fachada fachada = Fachada.getInstancia();
		Logradouro logradouro = null;
		
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");

		filtroLogradouro.adicionarParametro(new ParametroSimples(
		FiltroLogradouro.ID, idLogradouro));
		
		Collection<Logradouro> colecaoLogradouro = 
		fachada.pesquisar(filtroLogradouro, Logradouro.class.getName());
			
		logradouro = colecaoLogradouro.iterator().next();
			
		return logradouro;
	}
	
	/**
	 * autor: Anderson Italo
	 * data: 13/08/2009
	 * Caso o usuario tenha informado o imovel desabilita/limpa demais campos, Caso seja
	 * ordem de servico(sugestao=2) alem do imovel, tambem deixa habilitado a firma(empresa)
	 * @param form
	 */
	private void limpaDemaisCampos(ImovelEmissaoOrdensSeletivasActionForm form){
		
		//haba parametros
		if (form.getSugestao()!=null && !form.getSugestao().equals("2")){
			form.setFirma(null);
		}
		form.setQuantidadeMaxima(null);
		form.setCapacidade(null);
		form.setGerenciaRegional(null);
		form.setUnidadeNegocio(null);
		form.setElo(null);
		form.setLogradouro(null);
		form.setLocalidadeInicial(null);
		form.setSetorComercialInicial(null);
		form.setQuadraInicial(null);
		form.setRotaInicial(null);
		form.setRotaSequenciaInicial(null);
		form.setLocalidadeFinal(null);
		form.setSetorComercialFinal(null);
		form.setQuadraFinal(null);
		form.setRotaFinal(null);
		form.setRotaSequenciaFinal(null);
		//haba Hidrometro
		form.setCapacidade(null);
		form.setMarca(null);
		form.setAnormalidadeHidrometro(null);
		form.setNumeroOcorrenciasConsecutivas(null);
		form.setMesAnoInstalacaoInicial(null);
		form.setMesAnoInstalacaoFinal(null);
		//haba caractesristica
		form.setPerfilImovel(null);
		form.setCategoria(null);
		form.setSubCategoria(null);
		form.setIntervaloQuantidadeEconomiasInicial(null);
		form.setIntervaloQuantidadeEconomiasFinal(null);
		form.setIntervaloQuantidadeDocumentosInicial(null);
		form.setIntervaloQuantidadeDocumentosFinal(null);
		form.setIntervaloAreaConstruidaInicial(null);
		form.setIntervaloAreaConstruidaFinal(null);
		form.setIntervaloAreaConstruidaPredefinida(null);
		form.setImovelCondominio("2");
		form.setMediaImovel(null);
		form.setConsumoPorEconomia(null);
		form.setConsumoPorEconomiaFinal(null);
		form.setSituacaoLigacaoAgua(null);
		
	}
	
	
	private ImovelEmissaoOrdensSeletivasHelper gerarObjetoHelper(RelatorioEmitirOrdemServicoSeletiva relatorioEmitirOrdemServicoSeletiva) {
		ImovelEmissaoOrdensSeletivasHelper helper = new ImovelEmissaoOrdensSeletivasHelper();
		
		// PAR�METROS
		helper.setTipoEmissao((String) relatorioEmitirOrdemServicoSeletiva.getParametro("tipo"));
		helper.setTipoOrdem((String) relatorioEmitirOrdemServicoSeletiva.getParametro("tipoOrdem"));
		helper.setFirma((String) relatorioEmitirOrdemServicoSeletiva.getParametro("firma"));
		helper.setNomeFirma((String) relatorioEmitirOrdemServicoSeletiva.getParametro("nomeFirma"));
		helper.setQuantidadeMaxima((String) relatorioEmitirOrdemServicoSeletiva.getParametro("quantidadeMaxima"));
		helper.setIdImovel((String) relatorioEmitirOrdemServicoSeletiva.getParametro("idImovel"));
		helper.setElo((String) relatorioEmitirOrdemServicoSeletiva.getParametro("elo"));
		helper.setNomeElo((String) relatorioEmitirOrdemServicoSeletiva.getParametro("nomeElo"));
		helper.setGerenciaRegional((String) relatorioEmitirOrdemServicoSeletiva.getParametro("gerenciaRegional"));
		helper.setNomeGerenciaRegional((String) relatorioEmitirOrdemServicoSeletiva.getParametro("nomeGerenciaRegional"));
		helper.setUnidadeNegocio((String) relatorioEmitirOrdemServicoSeletiva.getParametro("unidadeNegocio"));
		helper.setNomeUnidadeNegocio((String) relatorioEmitirOrdemServicoSeletiva.getParametro("nomeUnidadeNegocio"));
		helper.setLocalidadeInicial((String) relatorioEmitirOrdemServicoSeletiva.getParametro("localidadeInicial"));
		helper.setNomeLocalidadeInicial((String) relatorioEmitirOrdemServicoSeletiva.getParametro("nomeLocalidadeInicial"));
		helper.setLocalidadeFinal((String) relatorioEmitirOrdemServicoSeletiva.getParametro("localidadeFinal"));
		helper.setNomeLocalidadeFinal((String) relatorioEmitirOrdemServicoSeletiva.getParametro("nomeLocalidadeFinal"));
		helper.setSetorComercialInicial((String) relatorioEmitirOrdemServicoSeletiva.getParametro("setorComercialInicial"));
		helper.setCodigoSetorComercialInicial((String) relatorioEmitirOrdemServicoSeletiva.getParametro("codigoSetorComercialInicial"));
		helper.setSetorComercialFinal((String) relatorioEmitirOrdemServicoSeletiva.getParametro("setorComercialFinal"));
		helper.setCodigoSetorComercialFinal((String) relatorioEmitirOrdemServicoSeletiva.getParametro("codigoSetorComercialFinal"));
		helper.setQuadraInicial((String) relatorioEmitirOrdemServicoSeletiva.getParametro("quadraInicial"));
		helper.setQuadraFinal((String) relatorioEmitirOrdemServicoSeletiva.getParametro("quadraFinal"));
		helper.setRotaInicial((String) relatorioEmitirOrdemServicoSeletiva.getParametro("rotaInicial"));
		helper.setRotaFinal((String) relatorioEmitirOrdemServicoSeletiva.getParametro("rotaFinal"));
		helper.setRotaSequenciaInicial((String) relatorioEmitirOrdemServicoSeletiva.getParametro("rotaSequenciaInicial"));
		helper.setRotaSequenciaFinal((String) relatorioEmitirOrdemServicoSeletiva.getParametro("rotaSequenciaFinal"));
		helper.setLogradouro((String) relatorioEmitirOrdemServicoSeletiva.getParametro("logradouro"));
		helper.setDescricaoLogradouro((String) relatorioEmitirOrdemServicoSeletiva.getParametro("descricaoLogradouro"));
		helper.setDescricaoComando((String)relatorioEmitirOrdemServicoSeletiva.getParametro("descricaoComando"));
		
		helper.setTipoEmissao((String) relatorioEmitirOrdemServicoSeletiva.getParametro("sugestao"));

		// CARACTER�STICAS
		helper.setPerfilImovel((String) relatorioEmitirOrdemServicoSeletiva.getParametro("perfilImovel"));
		helper.setCategoria((String) relatorioEmitirOrdemServicoSeletiva.getParametro("categoria"));
		helper.setSubCategoria((String) relatorioEmitirOrdemServicoSeletiva.getParametro("subCategoria"));
		helper.setQuantidadeEconomiasInicial((String) relatorioEmitirOrdemServicoSeletiva.getParametro("intervaloQuantidadeEconomiasInicial"));
		helper.setQuantidadeEconomiasFinal((String) relatorioEmitirOrdemServicoSeletiva.getParametro("intervaloQuantidadeEconomiasFinal"));
		helper.setQuantidadeDocumentosInicial((String) relatorioEmitirOrdemServicoSeletiva.getParametro("intervaloQuantidadeDocumentosInicial"));
		helper.setQuantidadeDocumentosFinal((String) relatorioEmitirOrdemServicoSeletiva.getParametro("intervaloQuantidadeDocumentosFinal"));
		helper.setNumeroMoradoresInicial((String) relatorioEmitirOrdemServicoSeletiva.getParametro("intervaloNumeroMoradoresInicial"));
		helper.setNumeroMoradoresFinal((String) relatorioEmitirOrdemServicoSeletiva.getParametro("intervaloNumeroMoradoresFinal"));
		helper.setAreaConstruidaInicial((String) relatorioEmitirOrdemServicoSeletiva.getParametro("intervaloAreaConstruidaInicial"));
		helper.setAreaConstruidaFinal((String) relatorioEmitirOrdemServicoSeletiva.getParametro("intervaloAreaConstruidaFinal"));
		helper.setImovelCondominio((String) relatorioEmitirOrdemServicoSeletiva.getParametro("imovelCondominio"));
		helper.setMediaImovel((String) relatorioEmitirOrdemServicoSeletiva.getParametro("mediaImovel"));
		helper.setConsumoPorEconomia((String) relatorioEmitirOrdemServicoSeletiva.getParametro("consumoPorEconomia"));
		helper.setConsumoPorEconomiaFinal((String) relatorioEmitirOrdemServicoSeletiva.getParametro("consumoPorEconomiaFinal"));		
		
		helper.setTipoMedicao((String) relatorioEmitirOrdemServicoSeletiva.getParametro("tipoMedicao"));
		
		helper.setSituacaoLigacaoAgua((String[]) relatorioEmitirOrdemServicoSeletiva.getParametro("situacaoLigacaoAgua"));
		
		// HIDR�METRO
		helper.setCapacidadeHidrometro((String[]) relatorioEmitirOrdemServicoSeletiva.getParametro("capacidade"));
		helper.setMarcaHidrometro((String) relatorioEmitirOrdemServicoSeletiva.getParametro("marca"));
		helper.setAnormalidadeHidrometro((String[]) relatorioEmitirOrdemServicoSeletiva.getParametro("anormalidadeHidrometro"));
		helper.setNumeroOcorrenciasAnormalidade((String) relatorioEmitirOrdemServicoSeletiva.getParametro("numeroOcorrenciasConsecutivas"));
		helper.setMesAnoInstalacaoInicialHidrometro((String) relatorioEmitirOrdemServicoSeletiva.getParametro("mesAnoInstalacaoInicial"));
		helper.setMesAnoInstalacaoFinalHidrometro((String) relatorioEmitirOrdemServicoSeletiva.getParametro("mesAnoInstalacaoFinal"));
		helper.setLocalInstalacaoHidrometro((String) relatorioEmitirOrdemServicoSeletiva.getParametro("localInstalacao"));
		
		return helper;
	}
	
	private Integer geraOrdemServico(ServicoTipo tipoServico, 
		Integer idEmpresa, 
		Integer idImovel,
		Integer idComandoOrdemSeletiva,
		Integer idAnormalidadeLeitura) {
		
		OrdemServico ordemServico = null;
		Empresa empresa = null;
		Integer retorno = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		// [UC0430] - Gerar Ordem de Servico
		
		ordemServico = new OrdemServico();
		ordemServico.setServicoTipo(tipoServico);
		
		Imovel imovel = new Imovel();
		imovel.setId(idImovel);
		ordemServico.setImovel(imovel);
		
		if(idComandoOrdemSeletiva != null){
			ComandoOrdemSeletiva comandoOrdemSeletiva = new ComandoOrdemSeletiva();
			comandoOrdemSeletiva.setId(idComandoOrdemSeletiva);
			ordemServico.setComandoOrdemSeletiva(comandoOrdemSeletiva);
		}
		
		if(idAnormalidadeLeitura != null){
			LeituraAnormalidade leituraAnormalidade = new LeituraAnormalidade();
			leituraAnormalidade.setId(idAnormalidadeLeitura);
			
			ordemServico.setLeituraAnormalidade(leituraAnormalidade);
		}
		
		// Alterado por Victor Cisneiros - 05/11/2008
		// Verificar se a empresa � a principal
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
		Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException("atencao.empresa_nao_encontra", null, idEmpresa.toString());
		}
		
		empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
		
		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
		
		// Caso a empresa seja principal (indicadorEmpresaPrincipal = 1) 
		// obter a unidade a partir da unidade que representa a localidade do imovel
		if (empresa.getIndicadorEmpresaPrincipal() != null && 
			empresa.getIndicadorEmpresaPrincipal().equals(Empresa.INDICADOR_EMPRESA_PRINCIPAL)) {
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
			Collection<Imovel> pesquisaImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			if (pesquisaImovel == null || pesquisaImovel.isEmpty()) {
				throw new ActionServletException("atencao.imovel.inexistente");
			}
			
			Imovel im = (Imovel) Util.retonarObjetoDeColecao(pesquisaImovel);
			
			Integer idLocalidade = im.getLocalidade().getId();
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.EMPRESA, idEmpresa));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_LOCALIDADE, idLocalidade));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
			
		} else {
			
			// Recupera a Unidade Organizacional da Empresa
			unidadeOrganizacional.setEmpresa(empresa);
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.EMPRESA, empresa));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional = 
				fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
				
				//[FS0011]-Verificar exist�ncia de mais de uma unidade correspondente � empresa
				if(colecaoUnidadeOrganizacional.size() > 1){
					throw new ActionServletException("atencao.unidade_organizacional_dupla_correspondente_empresa");
				}
				
				unidadeOrganizacional = colecaoUnidadeOrganizacional.iterator().next();
			}else {
				throw new ActionServletException("atencao.unidade_organizacional_nao_encontrada_empresa", null, empresa.getDescricao());
			}
		}
		
		retorno = fachada.gerarOrdemServicoSeletiva(
				ordemServico, 
				unidadeOrganizacional, 
				imovel, 
				Usuario.USUARIO_BATCH);
		
		return retorno;
	}
	
	/**
	 * Retorna o servico tipo passando o numero da constante
	 * 
	 * @author Erivan Sousa
	 * @date 09/10/2012
	 * 
	 * @param numeroConstanteServicoTipo
	 * @param fachada
	 * @return
	 */
	public ServicoTipo pesquisaServicoTipo(int numeroConstanteServicoTipo, Fachada fachada){
		ServicoTipo servicoTipo = null;
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		filtroServicoTipo.adicionarParametro( new ParametroSimples(
				FiltroServicoTipo.CONSTANTE_FUNCIONALIDADE_TIPO_SERVICO, numeroConstanteServicoTipo) );
		
		Collection<ServicoTipo> colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName() );
		
		if(colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()){
			servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
		}
		
		return servicoTipo;
	}
}