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

/*
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */  
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.atendimentopublico.ordemservico.bean.PesquisarFiscalizarOSAcompanhamentoHelper;
import gcom.atendimentopublico.ordemservico.bean.PesquisarFiscalizarOSEncerradaAcompanhamentoHelper;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1199] – Acompanhar Arquivos de Roteiro
 * 
 * @author Thúlio Araújo
 *
 * @date 15/07/2011
 */
public class FiltrarOSEncerradaAcompanhamentoServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirFiltrarFiscalizarOSAcompanhamentoServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();		


		// Form
		FiltrarFiscalizarOSAcompanhamentoServicoActionForm filtrarFiscalizarOSAcompanhamentoServicoActionForm = 
			(FiltrarFiscalizarOSAcompanhamentoServicoActionForm) actionForm;
		
		String limparSessao = httpServletRequest.getParameter("limparSessao");

		String metodo = httpServletRequest.getParameter("metodo");
		
				
		if (Util.verificarNaoVazio(metodo) && metodo.equals("visualizarFotos")){
			Integer idRA = Integer.valueOf(httpServletRequest.getParameter("idRA"));
			
			Collection<OrdemServicoFoto> fotos = (Collection<OrdemServicoFoto>) fachada.pesquisarFotosRA(idRA, true);
			if (!Util.isVazioOrNulo(fotos)){
				ArrayList<OrdemServicoFoto> fotoArray = new ArrayList<OrdemServicoFoto>(fotos);
				sessao.setAttribute("colecaoFotoOS", fotoArray);
				sessao.setAttribute("numeroFotos", fotoArray.size());
				sessao.setAttribute("idFoto", fotoArray.get(0).getId().intValue());
			} else {
				throw new ActionServletException("atencao.ordem.servico.nao.possui.foto");
			}
			retorno = actionMapping.findForward("fotos");
				
		
		} else {
//			Collection<?> colecaoAcompanhamentoArquivosRoteiro = fachada.pesquisarAcompanhamentoArquivosRoteiro(
//					fiscalizarOSAcompanhamentoServicoActionForm.getDataProgramacao(),
//					fiscalizarOSAcompanhamentoServicoActionForm.getIdEmpresa(),
//					fiscalizarOSAcompanhamentoServicoActionForm.getIdSituacao(),
//					idUnidadeLotacao);
			if ((filtrarFiscalizarOSAcompanhamentoServicoActionForm.getNumeroOS() == null || filtrarFiscalizarOSAcompanhamentoServicoActionForm.getNumeroOS().equals(""))&&
		            (filtrarFiscalizarOSAcompanhamentoServicoActionForm.getMatriculaImovel() == null || filtrarFiscalizarOSAcompanhamentoServicoActionForm.getMatriculaImovel().equals(""))) {

					String dtAtendimentoIni = filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoAtendimentoInicial();
					String dtAtendimentoFinal = filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoAtendimentoFinal();

					String dtGeracaoIni = filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoGeracaoInicial();
					String dtGeracaoFinal = filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoGeracaoFinal();

					String dtProgramacaoIni = filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoProgramacaoInicial();
					String dtProgramacaoFinal = filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoProgramacaoFinal();

					String dtEncerramentoIni = filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoEncerramentoInicial();
					String dtEncerramentoFinal = filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoEncerramentoFinal();
					
					if((dtAtendimentoIni==null || dtAtendimentoIni.equals("")) 
							&& (dtAtendimentoFinal==null || dtAtendimentoFinal.equals(""))){
						if((dtGeracaoIni==null || dtGeracaoIni.equals(""))
								&& (dtGeracaoFinal==null || dtGeracaoFinal.equals(""))){
							if((dtProgramacaoIni==null || dtProgramacaoIni.equals("")) 
									&& (dtProgramacaoFinal==null || dtProgramacaoFinal.equals(""))){
								if((dtEncerramentoIni==null || dtEncerramentoIni.equals("")) 
										&& (dtEncerramentoFinal==null || dtEncerramentoFinal.equals(""))){
									throw new ActionServletException("atencao.filtrar_intervalo_datas_obrigatorio");
								}
							}
						}
					}
							
					if(dtAtendimentoIni != null && !dtAtendimentoIni.equals("")){
						if(dtAtendimentoFinal==null || dtAtendimentoFinal.equals("")){
							throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"atendimento");
						}else{
							Date ini = Util.converteStringParaDate(dtAtendimentoIni);
							Calendar calendario = new GregorianCalendar();
							calendario.setTime(ini);
							Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
							numeroDias = new Integer(numeroDias-1);
							Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtAtendimentoFinal),numeroDias); 
							if(dataLimite.after(ini)){
								throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"atendimento");
							}
						}
					}

					if(dtGeracaoIni != null && !dtGeracaoIni.equals("")){
						if(dtGeracaoFinal==null || dtGeracaoFinal.equals("")){
							throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"geração");
						}else{
							Date ini = Util.converteStringParaDate(dtGeracaoIni);
							Calendar calendario = new GregorianCalendar();
							calendario.setTime(ini);
							Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
							numeroDias = new Integer(numeroDias-1);
							Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtGeracaoFinal),numeroDias); 
							if(dataLimite.after(ini)){
								throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"geração");
							}
						}
					}
					
					if(dtProgramacaoIni != null && !dtProgramacaoIni.equals("")){
						if(dtProgramacaoFinal==null || dtProgramacaoFinal.equals("")){
							throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"programação");
						}else{
							Date ini = Util.converteStringParaDate(dtProgramacaoIni);
							Calendar calendario = new GregorianCalendar();
							calendario.setTime(ini);
							Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
							numeroDias = new Integer(numeroDias-1);
							Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtProgramacaoFinal),numeroDias); 
							if(dataLimite.after(ini)){
								throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"tramitação");
							}
						}
					}
					
					if(dtEncerramentoIni != null && !dtEncerramentoIni.equals("")){
						if(dtEncerramentoFinal==null || dtEncerramentoFinal.equals("")){
							throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"encerramento");
						}else{
							Date ini = Util.converteStringParaDate(dtEncerramentoIni);
							Calendar calendario = new GregorianCalendar();
							calendario.setTime(ini);
							Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
							numeroDias = new Integer(numeroDias-1);
							Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtEncerramentoFinal),numeroDias); 
							if(dataLimite.after(ini)){
								throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"encerramento");
							}
						}
					}
				}
				
							
				
				// Numero OS
				Integer numeroOS = null;
				if (filtrarFiscalizarOSAcompanhamentoServicoActionForm.getNumeroOS() != null &&
					!filtrarFiscalizarOSAcompanhamentoServicoActionForm.getNumeroOS().equals("")) {
					
					numeroOS = new Integer(filtrarFiscalizarOSAcompanhamentoServicoActionForm.getNumeroOS());
				}
				
				
				// Imovel
				Integer matriculaImovel = null;
				if (filtrarFiscalizarOSAcompanhamentoServicoActionForm.getMatriculaImovel() != null &&
					!filtrarFiscalizarOSAcompanhamentoServicoActionForm.getMatriculaImovel().equals("")) {
					
					matriculaImovel = new Integer(filtrarFiscalizarOSAcompanhamentoServicoActionForm.getMatriculaImovel());
					
				}

				// Data de Atendimento
				Date dataAtendimentoInicial = null;
				Date dataAtendimentoFinal = null;
				
				if (filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoAtendimentoInicial() != null &&
					!filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoAtendimentoInicial().equals("")) {
					
					dataAtendimentoInicial = 
						Util.converteStringParaDate(filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoAtendimentoInicial());
					
					dataAtendimentoFinal = null;
					if (filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoAtendimentoFinal() != null &&
						!filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoAtendimentoFinal().equals("")) {
						
						dataAtendimentoFinal = 
							Util.converteStringParaDate(filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoAtendimentoFinal());
					} else {
						dataAtendimentoFinal = new Date();
					}

				}
						
				// Data de Geração
				Date dataGeracaoInicial = null;
				Date dataGeracaoFinal = null;
				
				if (filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoGeracaoInicial() != null &&
					!filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoGeracaoInicial().equals("")){
					
					dataGeracaoInicial = 
						Util.converteStringParaDate(filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoGeracaoInicial());
					
					dataGeracaoFinal = null;
					
					if (filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoGeracaoFinal() != null &&
						!filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoGeracaoFinal().equals("") ) {
						
						dataGeracaoFinal = 
							Util.converteStringParaDate(filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoGeracaoFinal());
						
					} else {
						dataGeracaoFinal = new Date();
					}

				}

			

				
				// Data de Encerramento
				Date dataEncerramentoInicial = null;
				Date dataEncerramentoFinal = null;
				
				if (filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoEncerramentoInicial() != null &&
					!filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoEncerramentoInicial().equals("")){
					
					dataEncerramentoInicial = 
						Util.converteStringParaDate(filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoEncerramentoInicial());
				
					dataEncerramentoFinal = null;
					
					if (filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoEncerramentoFinal() != null &&
						!filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoEncerramentoFinal().equals("") ) {
						
						dataEncerramentoFinal = 
							Util.converteStringParaDate(filtrarFiscalizarOSAcompanhamentoServicoActionForm.getPeriodoEncerramentoFinal());
						
					} else {
						dataEncerramentoFinal = new Date();
					}

				}
				
				// Bairro
				Integer idBairro = null;
				
				if (filtrarFiscalizarOSAcompanhamentoServicoActionForm.getCodigoBairro() != null && 
					!filtrarFiscalizarOSAcompanhamentoServicoActionForm.getCodigoBairro().equals("")) {
					
					idBairro = this.pesquisarBairro(filtrarFiscalizarOSAcompanhamentoServicoActionForm);
					
				}

				// Município
				Integer idMunicipio = null;
				
				if (filtrarFiscalizarOSAcompanhamentoServicoActionForm.getMunicipio() != null &&
					!filtrarFiscalizarOSAcompanhamentoServicoActionForm.getMunicipio().equals("")) {
					
					idMunicipio = new Integer(filtrarFiscalizarOSAcompanhamentoServicoActionForm.getMunicipio()); 
					
				}

				

				// Bairro Área
				Integer idAreaBairro = null;
				if (filtrarFiscalizarOSAcompanhamentoServicoActionForm.getAreaBairro() != null && 
						new Integer(filtrarFiscalizarOSAcompanhamentoServicoActionForm.getAreaBairro()).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

					idAreaBairro = new Integer(filtrarFiscalizarOSAcompanhamentoServicoActionForm.getAreaBairro());
					
				}
				
				// Logradouro
				Integer idLogradouro = null;
				
				if (filtrarFiscalizarOSAcompanhamentoServicoActionForm.getLogradouro() != null &&
					!filtrarFiscalizarOSAcompanhamentoServicoActionForm.getLogradouro().equals("")) {
				
					idLogradouro = new Integer(filtrarFiscalizarOSAcompanhamentoServicoActionForm.getLogradouro());
					
				}
				
				
				/*
				 * Colocado por Raphael Rossiter em 15/10/2009
				 * 
				 * Permitir efetuar a pesquisa das ordens de serviço pelo projeto
				 */
			
				PesquisarFiscalizarOSAcompanhamentoHelper pesquisarFiscalizarOSAcompanhamentoHelper = 
						new PesquisarFiscalizarOSAcompanhamentoHelper();
					pesquisarFiscalizarOSAcompanhamentoHelper.setNumeroOS(numeroOS);

					pesquisarFiscalizarOSAcompanhamentoHelper.setMatriculaImovel(matriculaImovel);
									 
					pesquisarFiscalizarOSAcompanhamentoHelper.setDataAtendimentoInicial(dataAtendimentoInicial);
					pesquisarFiscalizarOSAcompanhamentoHelper.setDataAtendimentoFinal(dataAtendimentoFinal);
					pesquisarFiscalizarOSAcompanhamentoHelper.setDataGeracaoInicial(dataGeracaoInicial);
					pesquisarFiscalizarOSAcompanhamentoHelper.setDataGeracaoFinal(dataGeracaoFinal);
					pesquisarFiscalizarOSAcompanhamentoHelper.setDataEncerramentoInicial(dataEncerramentoInicial);
					pesquisarFiscalizarOSAcompanhamentoHelper.setDataEncerramentoFinal(dataEncerramentoFinal);

					pesquisarFiscalizarOSAcompanhamentoHelper.setMunicipio(idMunicipio);
					pesquisarFiscalizarOSAcompanhamentoHelper.setBairro(idBairro);
					pesquisarFiscalizarOSAcompanhamentoHelper.setAreaBairro(idAreaBairro);
					pesquisarFiscalizarOSAcompanhamentoHelper.setLogradouro(idLogradouro);
																	
				Collection<PesquisarFiscalizarOSEncerradaAcompanhamentoHelper> colecaoFiscalizarOSAcompanhamento = fachada.pesquisarFiscalizarOSAcompanhamento(pesquisarFiscalizarOSAcompanhamentoHelper);
				
				sessao.setAttribute("pesquisarFiscalizarOSAcompanhamentoHelper", pesquisarFiscalizarOSAcompanhamentoHelper);				

				
			if (colecaoFiscalizarOSAcompanhamento!=null){
				sessao.setAttribute("achou","1");
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			
			sessao.setAttribute("colecaoFiscalizarOSAcompanhamento", colecaoFiscalizarOSAcompanhamento);
		}
		
		
		if (limparSessao != null && !limparSessao.equals("")){
			sessao.removeAttribute("dataRoteiroInformarSituacao");
			sessao.removeAttribute("chaveOsInformarSituacao");
			sessao.removeAttribute("chaveArquivoInformarSituacao");
		}
				
		
		httpServletRequest.setAttribute("fecharPopup", "true");
		
		return retorno;
	}
	
	/**
	 * Pesquisa Bairro 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private Integer pesquisarBairro(FiltrarFiscalizarOSAcompanhamentoServicoActionForm form) {
		
		//[FS0013] - Verificar informação do munícipio
		String codigoMunicipio = form.getMunicipio();
		if(codigoMunicipio == null || codigoMunicipio.equals("")){
			throw new ActionServletException("atencao.filtrar_informar_municipio");	
		}
		
		Integer retorno = null;

		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.CODIGO, 
			new Integer(form.getCodigoBairro())));

		filtroBairro.adicionarParametro(
				new ParametroSimples(FiltroBairro.MUNICIPIO_ID,new Integer(codigoMunicipio)));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection<?> colecaoBairro = Fachada.getInstancia()
				.pesquisar(filtroBairro,Bairro.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Bairro bairro = 
				(Bairro) Util.retonarObjetoDeColecao(colecaoBairro);
			
			retorno = bairro.getId();
		} else {
			throw new ActionServletException("atencao.bairro.inexistente");
		}
		
		return retorno;
	}
	
	
}