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
* Ivan S�rgio da Silva J�nior
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
package gsan.gui.relatorio.faturamento.conta;

import gsan.atendimentopublico.portal.AcessoLojaVirtual;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.conta.ContaHistorico;
import gsan.faturamento.conta.ContaMotivoRevisao;
import gsan.faturamento.conta.FiltroConta;
import gsan.faturamento.conta.FiltroContaHistorico;
import gsan.gui.ActionServletException;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.RelatorioVazioException;
import gsan.relatorio.faturamento.conta.Relatorio2ViaConta;
import gsan.relatorio.faturamento.conta.Relatorio2ViaContaCaern;
import gsan.relatorio.faturamento.conta.Relatorio2ViaContaTipo2;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0482] Emitir 2� Via de Conta
 * @author Vivianne Sousa
 * @date 15/09/2006
 */

public class GerarRelatorio2ViaContaAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection idsConta = new ArrayList();
		Integer idContaHistorico = null;
				
		boolean conjuntoContaRevisao = false;
		
		if (sessao.getAttribute("idContaHistorico") != null && !sessao.getAttribute("idContaHistorico").equals("")){
			//Consultar Imovel (aba Faturamento)
			idContaHistorico = new Integer("" + sessao.getAttribute("idContaHistorico"));
			idsConta.add(idContaHistorico);
		}else if (sessao.getAttribute("idConta") != null &&	!sessao.getAttribute("idConta").equals("")){
			//popup de conta
			idsConta.add(new Integer("" + sessao.getAttribute("idConta")));
			if (sessao.getAttribute("conta") != null){
				Conta conta = (Conta)sessao.getAttribute("conta");
				conta.getValorTotalConta();
			}
			
		}else if(httpServletRequest.getParameter("idConta") != null && !httpServletRequest.getParameter("idConta").equals("")){
			idsConta.add(new Integer("" + httpServletRequest.getParameter("idConta")));
		}else if (sessao.getAttribute("idsContaEP") != null){
			//Parcelamento
			idsConta = (Collection)sessao.getAttribute("idsContaEP");
		}else if(sessao.getAttribute("colecaoImovel") != null){
			//Manter Conjunto Conta
			Integer anoMes = 0;
			if(httpServletRequest.getParameter("mesAno") != null){
			  anoMes = Util.formatarMesAnoComBarraParaAnoMes(httpServletRequest.getParameter("mesAno"));
			  sessao.setAttribute("anoMes", anoMes);
			}else{
			  anoMes = new Integer("" + sessao.getAttribute("anoMes"));
			  sessao.removeAttribute("anoMes");
			}
			
			Integer anoMesFim = anoMes;
			if(httpServletRequest.getParameter("mesAnoFim") != null){
			  anoMesFim = Util.formatarMesAnoComBarraParaAnoMes(httpServletRequest.getParameter("mesAnoFim"));
			  sessao.setAttribute("anoMesFim", anoMesFim);
			}else if (sessao.getAttribute("anoMesFim") != null){
			  anoMesFim = new Integer("" + sessao.getAttribute("anoMesFim"));
			  sessao.removeAttribute("anoMesFim");
			}
			
			Date dataVencimentoContaInicio = null;
			Date dataVencimentoContaFim = null;
			String indicadorContaPaga = null;			
			Integer idGrupoFaturamento = null;
			
			String dataVencimentoContaInicioParam = httpServletRequest.getParameter("dataVencimentoContaInicial");
			if (dataVencimentoContaInicioParam != null &&
				!dataVencimentoContaInicioParam.equals("")){
				
				dataVencimentoContaInicio = Util.converteStringParaDate(httpServletRequest.getParameter("dataVencimentoContaInicial"));
				sessao.setAttribute("dataVencimentoContaInicial", dataVencimentoContaInicio);
			}else if (sessao.getAttribute("dataVencimentoContaInicial") != null){				  
				dataVencimentoContaInicio = Util.converteStringParaDate("" + sessao.getAttribute("dataVencimentoContaInicial"));
				  sessao.removeAttribute("dataVencimentoContaInicial");
			}
			
			String dataVencimentoContaFimParam = httpServletRequest.getParameter("dataVencimentoContaFinal");
			if (dataVencimentoContaFimParam != null &&
				!dataVencimentoContaFimParam.equals("")){
				
				dataVencimentoContaFim = Util.converteStringParaDate(httpServletRequest.getParameter("dataVencimentoContaFinal"));
				sessao.setAttribute("dataVencimentoContaFinal", dataVencimentoContaFim);
			}else if (sessao.getAttribute("dataVencimentoContaFinal") != null){				  
				dataVencimentoContaFim = Util.converteStringParaDate("" + sessao.getAttribute("dataVencimentoContaFinal"));
				sessao.removeAttribute("dataVencimentoContaFinal");
			}
			
			if (httpServletRequest.getParameter("indicadorContaPaga") != null){
				
				indicadorContaPaga = httpServletRequest.getParameter("indicadorContaPaga");
				sessao.setAttribute("indicadorContaPaga", indicadorContaPaga);
			}else if(sessao.getAttribute("indicadorContaPaga") != null){
				indicadorContaPaga = (String)sessao.getAttribute("indicadorContaPaga");
				sessao.removeAttribute("indicadorContaPaga");				
			}
			
			String idGrupoFaturamentoParam = httpServletRequest.getParameter("idGrupoFaturamento");
			if (idGrupoFaturamentoParam != null &&
				!idGrupoFaturamentoParam.equals("")){
				
				idGrupoFaturamento = new Integer((String) httpServletRequest.getParameter("idGrupoFaturamento"));
				sessao.setAttribute("idGrupoFaturamento", idGrupoFaturamento);
			}else if (sessao.getAttribute("idGrupoFaturamento") != null){				  
				idGrupoFaturamento = (Integer)sessao.getAttribute("idGrupoFaturamento");
				sessao.removeAttribute("idGrupoFaturamento");
			}       	
			
			Collection colecaoImovel = (Collection)sessao.getAttribute("colecaoImovel");
			
			if (idGrupoFaturamento != null){
        		
        		idsConta = fachada.pesquisarConjuntoContaEmitir2Via(idGrupoFaturamento,anoMes, 
          			  dataVencimentoContaInicio, dataVencimentoContaFim, anoMesFim);
        	}
        	else{
        	  
        		idsConta = fachada.pesquisarConjuntoContaEmitir2Via(colecaoImovel,anoMes, 
        			  dataVencimentoContaInicio, dataVencimentoContaFim, anoMesFim, indicadorContaPaga);	
        	}
			
			/**
			 * [UC0407] Filtrar Im�veis para Inserir ou Manter Conta
			 * 3. Caso o indicador de bloqueio de contas vinculadas a contrato de parcelamento no manter contas esteja ativo
			 *   retirar da lista de contas selecionadas as contas vinculadas a algum contrato de parcelamento ativo
			 *    
			 * RM 1887 - Contrato Parcelamento por Cliente
			 * Adicionado por: Mariana Victor
			 * Data: 14/07/2011
			 *  
			 * */
			
			if (sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta() != null
					&& sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta().equals(ConstantesSistema.SIM)) {
				idsConta = fachada.obterColecaoSemContasEmContratoParcelamentoIDs(
						idsConta);
			}
			/**
			 * FIM DA ALTERA��O
			 * */
			
			
			if(sessao.getAttribute("contaRevisao") != null){
				conjuntoContaRevisao = true;
			}
						
		}else{
			// a partir do Manter Conta
	        String contaSelected = httpServletRequest.getParameter("conta");
	        String[] arrayIdentificadores = contaSelected.split(",");
	       
	    	for (int i = 0; i < arrayIdentificadores.length; i++) {
	    		String dadosConta = arrayIdentificadores[i];
				String[] idContaArray = dadosConta.split("-");
				Integer idConta = new Integer (idContaArray[0]);
				idsConta.add(idConta);
				
	    	}	
		
		}
		
		
		if(idsConta != null && !conjuntoContaRevisao){
			Collection colecaoContaDataRevisao = null;
			colecaoContaDataRevisao = fachada.pesquisarDataRevisaoConta(idsConta);
			
			 if (sessao.getAttribute("idsContaEP") == null &&
					 colecaoContaDataRevisao != null && !colecaoContaDataRevisao.isEmpty()){
				 
				 Iterator colecaoContaDataRevisaoIterator = colecaoContaDataRevisao.iterator();
				 
				 while (colecaoContaDataRevisaoIterator.hasNext()) {
					 Conta conta = (Conta) colecaoContaDataRevisaoIterator.next();
					 
					 if (conta.getContaMotivoRevisao() != null && !conta.getContaMotivoRevisao().getId().equals(ContaMotivoRevisao.REVISAO_ENTRADA_DE_PARCELAMENTO)) {
						 throw new ActionServletException("atencao.nao_permite_emitir_conta_em_revisao");
					 }
				 }
			}
		}
		
		
		
		Short contaSemCodigoBarras = ConstantesSistema.NAO;
		if (httpServletRequest.getParameter("contaSemCodigoBarras")!= null
				&& httpServletRequest.getParameter("contaSemCodigoBarras").equals("1")){
			contaSemCodigoBarras = ConstantesSistema.SIM;
			sessao.setAttribute("contaSemCodigoBarras", contaSemCodigoBarras);
		}else if(sessao.getAttribute("contaSemCodigoBarras") != null){
			contaSemCodigoBarras = (Short)sessao.getAttribute("contaSemCodigoBarras");	
		}
		
		
		boolean cobrarTaxaEmissaoConta = true;
		
		if(httpServletRequest.getParameter("cobrarTaxaEmissaoConta")!= null
				&& httpServletRequest.getParameter("cobrarTaxaEmissaoConta").equals("N")){
			cobrarTaxaEmissaoConta = false;
			sessao.setAttribute("cobrarTaxaEmissaoConta", cobrarTaxaEmissaoConta);
		}else if(sessao.getAttribute("cobrarTaxaEmissaoConta") != null){
			cobrarTaxaEmissaoConta = (Boolean)sessao.getAttribute("cobrarTaxaEmissaoConta");
		}
		
		Integer qtdeContas = new Integer("0");
		
		if (httpServletRequest.getParameter("qtdeContas") != null) {
			qtdeContas = new Integer(httpServletRequest.getParameter("qtdeContas"));
			sessao.setAttribute("qtdeContas", qtdeContas);
		} else if (sessao.getAttribute("qtdeContas") != null) {
			qtdeContas = (Integer) sessao.getAttribute("qtdeContas");
		}
		
		 
		String nomeEmpresa = sistemaParametro.getNomeAbreviadoEmpresa();
		
		
		/*
		 * Colocado por Raphael Rossiter em 24/10/2008 - Analista: Rosana Carvalho
		 * 
		 * [FS0002] - Cliente sem documento
		 */
		Integer idConta = (Integer) idsConta.iterator().next();
		
		FiltroConta filtroConta = new FiltroConta();
		filtroConta.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.IMOVEL_PERFIL);
		filtroConta.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");
		filtroConta.adicionarCaminhoParaCarregamentoEntidade("imovel.cobrancaSituacaoTipo");
		
		filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, idConta));
		Collection colecaoConta = fachada.pesquisar(filtroConta, Conta.class.getName());
		
		Imovel imovel = null;
		
		if (colecaoConta != null && !colecaoConta.isEmpty()){
			Conta conta = (Conta) colecaoConta.iterator().next();
			imovel = conta.getImovel();
			
			/*
			 * 	Caso o indicador de gera��o de d�bito de segunda via de conta associado ao perfil do im�vel da conta 
			 * esteja ativo (IPER_ICGERARDEBITOSEGUNDAVIACONTA=1 da tabela IMOVEL_PERFIL com IPER_ID=IPER_ID da tabela CONTA), 
			 * o sistema dever� gerar um d�bito a cobrar referente a taxa de emiss�o da segunda via da conta.
			 * 
			 *  Desenvolvedor Hugo Amorim
			 *  Analista Nelson Carvalho
			 *  Data: 17/05/2010
			 */
			if(conta.getImovel().getImovelPerfil()!=null && 
					conta.getImovel().getImovelPerfil().getIndicadorGeraDebitoSegundaViaConta()!=null 
						&& cobrarTaxaEmissaoConta){
					
				if(conta.getImovelPerfil().getIndicadorGeraDebitoSegundaViaConta().compareTo(ConstantesSistema.NAO)==0){
					cobrarTaxaEmissaoConta = false;
				}
			}else{
				
				if(imovel.getImovelPerfil()!=null && 
						imovel.getImovelPerfil().getIndicadorGeraDebitoSegundaViaConta()!=null 
							&& cobrarTaxaEmissaoConta){
						
					if(imovel.getImovelPerfil().getIndicadorGeraDebitoSegundaViaConta().compareTo(ConstantesSistema.NAO)==0){
						cobrarTaxaEmissaoConta = false;
					}
				}
				
			}
		
			
			/*
			 * Nao gerar codigo de barras caso conta fa�a parte do programa viva agua 
			 * Desenvolvedor Carlos Chaves
			 * Data 21/06/2012
			 *
			 */
			
			if(sistemaParametro.getPerfilProgramaEspecial() != null
					&& sistemaParametro.getPerfilProgramaEspecial().getId() != null){
				
				if(conta.getImovelPerfil().getId().equals(sistemaParametro.getPerfilProgramaEspecial().getId())){
					contaSemCodigoBarras = ConstantesSistema.SIM;
				}
				
			}
			
			
		}
		else{
			
			FiltroContaHistorico filtroContaHistorico = new FiltroContaHistorico();
			filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel");
			filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroContaHistorico.IMOVEL_PERFIL);
			filtroContaHistorico.adicionarParametro(new ParametroSimples(FiltroContaHistorico.ID, idConta));
			filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");
			filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.cobrancaSituacaoTipo");
			colecaoConta = fachada.pesquisar(filtroContaHistorico, ContaHistorico.class.getName());
			
			
			ContaHistorico contaHistorico = (ContaHistorico) colecaoConta.iterator().next();
			imovel = contaHistorico.getImovel();
			/*
			 * 	Caso o indicador de gera��o de d�bito de segunda via de conta associado ao perfil do im�vel da conta 
			 * esteja ativo (IPER_ICGERARDEBITOSEGUNDAVIACONTA=1 da tabela IMOVEL_PERFIL com IPER_ID=IPER_ID da tabela CONTA), 
			 * o sistema dever� gerar um d�bito a cobrar referente a taxa de emiss�o da segunda via da conta.
			 * 
			 *  Desenvolvedor Hugo Amorim
			 *  Analista Nelson Carvalho
			 *  Data: 17/05/2010
			 */
			if(contaHistorico.getImovelPerfil()!=null &&
					contaHistorico.getImovelPerfil().getIndicadorGeraDebitoSegundaViaConta()!=null &&
						cobrarTaxaEmissaoConta){
				
				if(contaHistorico.getImovelPerfil().getIndicadorGeraDebitoSegundaViaConta().compareTo(ConstantesSistema.NAO)==0){
					cobrarTaxaEmissaoConta = false;
				}
			}else{
				if(imovel.getImovelPerfil()!=null &&
						imovel.getImovelPerfil().getIndicadorGeraDebitoSegundaViaConta()!=null &&
							cobrarTaxaEmissaoConta){
					
					if(imovel.getImovelPerfil().getIndicadorGeraDebitoSegundaViaConta().compareTo(ConstantesSistema.NAO)==0){
						cobrarTaxaEmissaoConta = false;
					}
				}
			}
		}
		/*
		 * --Erivan Sousa--
		 * Impede que a segunda via seja emitida caso o indicador cbsp_icemitedoccobranca 
		 * da tabela cobranca.cobranca_situacao_tipo seja igual a 2
		 */
		
		if(imovel != null && imovel.getCobrancaSituacaoTipo() != null){
			if(imovel.getCobrancaSituacaoTipo().getIndicadorEmitirDocumentoCobranca().equals(ConstantesSistema.NAO)){
				throw new  ActionServletException("atencao.segundavianaoemitido_imovel_situacaoespecial");
			}
		}
		
		
		
		
		
		 
		fachada.verificarClienteSemDocumento(imovel.getId(),
		(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		
		
		if(sistemaParametro.getCodigoEmpresaFebraban()
				.equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)){
			//Parte que vai mandar o relat�rio para a tela
			// cria uma inst�ncia da classe do relat�rio
			Relatorio2ViaContaTipo2 relatorio2ViaContaTipo2 = new Relatorio2ViaContaTipo2((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			//relatorio2ViaContaTipo2.addParametro("colecaoEmitirContaHelper",colecaoEmitirContaHelper);
			relatorio2ViaContaTipo2.addParametro("idsConta", idsConta);
			relatorio2ViaContaTipo2.addParametro("cobrarTaxaEmissaoConta", cobrarTaxaEmissaoConta);
			relatorio2ViaContaTipo2.addParametro("contaSemCodigoBarrasPesquisa", contaSemCodigoBarras);
			relatorio2ViaContaTipo2.addParametro("idContaHistorico",idContaHistorico);
			relatorio2ViaContaTipo2.addParametro("qtdeContas", qtdeContas);
			//String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			//if (tipoRelatorio == null) {
			String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			//}

			relatorio2ViaContaTipo2.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			relatorio2ViaContaTipo2.addParametro("nomeEmpresa",nomeEmpresa);
			
			try {
				retorno = processarExibicaoRelatorio(relatorio2ViaContaTipo2,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);

			} catch (RelatorioVazioException ex) {
				// manda o erro para a p�gina no request atual
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

				// seta o mapeamento de retorno para a tela de aten��o de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}
			
		} else {
			
			Relatorio2ViaConta relatorio2ViaConta = new Relatorio2ViaConta((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			if("CAERN".equals(nomeEmpresa))				
				relatorio2ViaConta = new Relatorio2ViaContaCaern((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
							
			// Parte que vai mandar o relat�rio para a tela
			// cria uma inst�ncia da classe do relat�rio
			
			relatorio2ViaConta.addParametro("idsConta", idsConta);
			relatorio2ViaConta.addParametro("cobrarTaxaEmissaoConta", cobrarTaxaEmissaoConta);
			relatorio2ViaConta.addParametro("contaSemCodigoBarras", contaSemCodigoBarras);

			relatorio2ViaConta.addParametro("qtdeContas", qtdeContas);
			String tipoRelatorio = TarefaRelatorio.TIPO_PDF +"";


			relatorio2ViaConta.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			relatorio2ViaConta.addParametro("nomeEmpresa",nomeEmpresa);
			
			relatorio2ViaConta.addParametro("idContaHistorico",idContaHistorico);
			
			try {
				retorno = processarExibicaoRelatorio(relatorio2ViaConta,
					tipoRelatorio, httpServletRequest, httpServletResponse,	actionMapping);

			} catch (RelatorioVazioException ex) {
				// manda o erro para a p�gina no request atual
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

				// seta o mapeamento de retorno para a tela de aten��o de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}
		}
		
		if(httpServletRequest.getParameter("lojaVirtual") != null){
			String ip = httpServletRequest.getRemoteAddr(); 
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.SEGUNDA_VIA_CONTA, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO); 
		}
		return retorno;
	}

}
