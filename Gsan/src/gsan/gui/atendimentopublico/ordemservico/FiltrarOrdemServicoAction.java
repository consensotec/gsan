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
package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.bean.OSFiltroHelper;
import gsan.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gsan.atendimentopublico.ordemservico.bean.PesquisarOrdemServicoHelper;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.cadastro.geografico.Bairro;
import gsan.cadastro.geografico.FiltroBairro;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarOrdemServicoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("resultadoPesquisa");
		
		// Sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		// Form
		FiltrarOrdemServicoActionForm filtrarOrdemServicoActionForm 
			= (FiltrarOrdemServicoActionForm) actionForm;
		
		boolean parametroInformado = false;
		
		if (httpServletRequest.getParameter("idRa") != null) {
			filtrarOrdemServicoActionForm.setNumeroRA(
					httpServletRequest.getParameter("idRa").toString());
			
			Integer[] tipoServicoSelecionados = new Integer[1];
			tipoServicoSelecionados[0] = ConstantesSistema.NUMERO_NAO_INFORMADO;
			filtrarOrdemServicoActionForm.setTipoServicoSelecionados(tipoServicoSelecionados);
			
			filtrarOrdemServicoActionForm.setOrigemOrdemServico(OrdemServico.TODAS);
		}

		// Valida��o solicitada por Leonardo Vieira sem caso de uso.
		// Executor: Marcio Roberto
        
        // quando for informado numero do RA, documento de cobran�a, matricula do imovel ou 
        // codigo do cliente n�o deve haver esta restri��o
        // Vivianne Sousa analista:Aryed Lins
		if ((filtrarOrdemServicoActionForm.getNumeroOS() == null || filtrarOrdemServicoActionForm.getNumeroOS().equals(""))&&
            (filtrarOrdemServicoActionForm.getNumeroRA() == null || filtrarOrdemServicoActionForm.getNumeroRA().equals(""))&&
            (filtrarOrdemServicoActionForm.getMatriculaImovel() == null || filtrarOrdemServicoActionForm.getMatriculaImovel().equals(""))&&
            (filtrarOrdemServicoActionForm.getDocumentoCobranca() == null || filtrarOrdemServicoActionForm.getDocumentoCobranca().equals(""))&&
            (filtrarOrdemServicoActionForm.getCodigoCliente() == null || filtrarOrdemServicoActionForm.getCodigoCliente().equals(""))) {

			String dtAtendimentoIni = filtrarOrdemServicoActionForm.getPeriodoAtendimentoInicial();
			String dtAtendimentoFinal = filtrarOrdemServicoActionForm.getPeriodoAtendimentoFinal();

			String dtGeracaoIni = filtrarOrdemServicoActionForm.getPeriodoGeracaoInicial();
			String dtGeracaoFinal = filtrarOrdemServicoActionForm.getPeriodoGeracaoFinal();

			String dtProgramacaoIni = filtrarOrdemServicoActionForm.getPeriodoProgramacaoInicial();
			String dtProgramacaoFinal = filtrarOrdemServicoActionForm.getPeriodoProgramacaoFinal();

			String dtEncerramentoIni = filtrarOrdemServicoActionForm.getPeriodoEncerramentoInicial();
			String dtEncerramentoFinal = filtrarOrdemServicoActionForm.getPeriodoEncerramentoFinal();
			
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
					throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"gera��o");
				}else{
					Date ini = Util.converteStringParaDate(dtGeracaoIni);
					Calendar calendario = new GregorianCalendar();
					calendario.setTime(ini);
					Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
					numeroDias = new Integer(numeroDias-1);
					Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtGeracaoFinal),numeroDias); 
					if(dataLimite.after(ini)){
						throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"gera��o");
					}
				}
			}
			
			if(dtProgramacaoIni != null && !dtProgramacaoIni.equals("")){
				if(dtProgramacaoFinal==null || dtProgramacaoFinal.equals("")){
					throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"programa��o");
				}else{
					Date ini = Util.converteStringParaDate(dtProgramacaoIni);
					Calendar calendario = new GregorianCalendar();
					calendario.setTime(ini);
					Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
					numeroDias = new Integer(numeroDias-1);
					Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtProgramacaoFinal),numeroDias); 
					if(dataLimite.after(ini)){
						throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"tramita��o");
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
		
		//Origem da OS
		String origemOS = null;
		if (filtrarOrdemServicoActionForm.getOrigemOrdemServico() != null &&
			!filtrarOrdemServicoActionForm.getOrigemOrdemServico().equals("")) {
			
			origemOS = filtrarOrdemServicoActionForm.getOrigemOrdemServico();
//			parametroInformado = true;
		}
		
		
		// Numero OS
		Integer numeroOS = null;
		if (filtrarOrdemServicoActionForm.getNumeroOS() != null &&
			!filtrarOrdemServicoActionForm.getNumeroOS().equals("")) {
			
			numeroOS = new Integer(filtrarOrdemServicoActionForm.getNumeroOS());
			parametroInformado = true;
		}
		
		// Numero RA
		Integer numeroRA = null;
		if (filtrarOrdemServicoActionForm.getNumeroRA() != null &&
			!filtrarOrdemServicoActionForm.getNumeroRA().equals("")) {
			
			numeroRA = new Integer(filtrarOrdemServicoActionForm.getNumeroRA());
			parametroInformado = true;
		}
		
		// Documento Cobran�a
		Integer idDocumentoCobranca = null;
		if (filtrarOrdemServicoActionForm.getDocumentoCobranca() != null &&
			!filtrarOrdemServicoActionForm.getDocumentoCobranca().equals("")) {
			
			idDocumentoCobranca = new Integer(filtrarOrdemServicoActionForm.getDocumentoCobranca());
			parametroInformado = true;
		}
		
		// Situacao da Ordem de Servico
		short situacaoOrdemServico = ConstantesSistema.NUMERO_NAO_INFORMADO;
		
		if (filtrarOrdemServicoActionForm.getSituacaoOrdemServico() != null &&
			!filtrarOrdemServicoActionForm.getSituacaoOrdemServico().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			situacaoOrdemServico = 
				new Short(filtrarOrdemServicoActionForm.getSituacaoOrdemServico()).shortValue();	

			parametroInformado = true;
		}

		// Situacao da Programa��o
		short situacaoProgramacao = ConstantesSistema.NUMERO_NAO_INFORMADO;
		if (filtrarOrdemServicoActionForm.getSituacaoProgramacao() != null) {
			
			//Informou todos
			if(!filtrarOrdemServicoActionForm.getSituacaoProgramacao().equals("0")){
				situacaoProgramacao = 
					new Short(filtrarOrdemServicoActionForm.getSituacaoProgramacao()).shortValue();
			}

		}
		
		//Tipo Servico
		Integer[] idsTipoServicoSelecionado = 
			(Integer[]) filtrarOrdemServicoActionForm.getTipoServicoSelecionados();
		
		if(idsTipoServicoSelecionado.length > 0){
			if(idsTipoServicoSelecionado[0].intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO){
				idsTipoServicoSelecionado = null;
			}else{
				parametroInformado = true;
			}
		}
		
		// Perfil Imovel
		Collection<Integer> colecaoPerfilImovel = null;
		if (filtrarOrdemServicoActionForm.getColecaoPerfilImovel() != null) {
			colecaoPerfilImovel = new ArrayList<Integer>();
			
			for (String id : filtrarOrdemServicoActionForm.getColecaoPerfilImovel()) {
				if (!id.equals("-1")) {
					parametroInformado = true;
					colecaoPerfilImovel.add(new Integer(id));
				}
			}
			if (colecaoPerfilImovel.size() == 0) colecaoPerfilImovel = null;
		}
		
		// Imovel
		Integer matriculaImovel = null;
		if (filtrarOrdemServicoActionForm.getMatriculaImovel() != null &&
			!filtrarOrdemServicoActionForm.getMatriculaImovel().equals("")) {
			
			matriculaImovel = new Integer(filtrarOrdemServicoActionForm.getMatriculaImovel());
			
			parametroInformado = true;
		}

		// Cliente
		Integer codigoCliente = null;
		if (filtrarOrdemServicoActionForm.getCodigoCliente() != null &&
			!filtrarOrdemServicoActionForm.getCodigoCliente().equals("")) {
			
			codigoCliente = new Integer(filtrarOrdemServicoActionForm.getCodigoCliente());
			
			parametroInformado = true;
		}

		// Unidade de Gera��o
		Integer unidadeGeracao = null;
		
		if (filtrarOrdemServicoActionForm.getUnidadeGeracao() != null &&
			!filtrarOrdemServicoActionForm.getUnidadeGeracao().equals("")) {
			
			unidadeGeracao= new Integer(filtrarOrdemServicoActionForm.getUnidadeGeracao());

			parametroInformado = true;
		}

		// Unidade Atual
		Integer unidadeAtual = null;

		if (filtrarOrdemServicoActionForm.getUnidadeAtual() != null &&
			!filtrarOrdemServicoActionForm.getUnidadeAtual().equals("")) {
			
			unidadeAtual = new Integer(filtrarOrdemServicoActionForm.getUnidadeAtual());
			
			parametroInformado = true;
		}

		// Unidade Superior
		Integer unidadeSuperior = null;

		if (filtrarOrdemServicoActionForm.getUnidadeSuperior() != null &&
			!filtrarOrdemServicoActionForm.getUnidadeSuperior().equals("")) {

			unidadeSuperior = new Integer(filtrarOrdemServicoActionForm.getUnidadeSuperior());
			
			parametroInformado = true;
		}

		// Data de Atendimento
		Date dataAtendimentoInicial = null;
		Date dataAtendimentoFinal = null;
		
		if (filtrarOrdemServicoActionForm.getPeriodoAtendimentoInicial() != null &&
			!filtrarOrdemServicoActionForm.getPeriodoAtendimentoInicial().equals("")) {
			
			dataAtendimentoInicial = 
				Util.converteStringParaDate(filtrarOrdemServicoActionForm.getPeriodoAtendimentoInicial());
			
			dataAtendimentoFinal = null;
			if (filtrarOrdemServicoActionForm.getPeriodoAtendimentoFinal() != null &&
				!filtrarOrdemServicoActionForm.getPeriodoAtendimentoFinal().equals("")) {
				
				dataAtendimentoFinal = 
					Util.converteStringParaDate(filtrarOrdemServicoActionForm.getPeriodoAtendimentoFinal());
			} else {
				dataAtendimentoFinal = new Date();
			}

			parametroInformado = true;
		}
		
		// Atendimento Motivo Encerramento
		Collection<Integer> colecaoAtendimentoMotivoEncerramento = null;
		if (filtrarOrdemServicoActionForm.getColecaoAtendimentoMotivoEncerramento() != null) {
			colecaoAtendimentoMotivoEncerramento = new ArrayList<Integer>();
			
			for (String id : filtrarOrdemServicoActionForm.getColecaoAtendimentoMotivoEncerramento()) {
				if (!id.equals("-1")) {
					parametroInformado = true;
					colecaoAtendimentoMotivoEncerramento.add(new Integer(id));
				}
			}
			if (colecaoAtendimentoMotivoEncerramento.size() == 0) colecaoAtendimentoMotivoEncerramento = null;
		}

		// Data de Gera��o
		Date dataGeracaoInicial = null;
		Date dataGeracaoFinal = null;
		
		if (filtrarOrdemServicoActionForm.getPeriodoGeracaoInicial() != null &&
			!filtrarOrdemServicoActionForm.getPeriodoGeracaoInicial().equals("")){
			
			dataGeracaoInicial = 
				Util.converteStringParaDate(filtrarOrdemServicoActionForm.getPeriodoGeracaoInicial());
			
			dataGeracaoFinal = null;
			
			if (filtrarOrdemServicoActionForm.getPeriodoGeracaoFinal() != null &&
				!filtrarOrdemServicoActionForm.getPeriodoGeracaoFinal().equals("") ) {
				
				dataGeracaoFinal = 
					Util.converteStringParaDate(filtrarOrdemServicoActionForm.getPeriodoGeracaoFinal());
				
			} else {
				dataGeracaoFinal = new Date();
			}

			parametroInformado = true;
		}

		// Data de Programa��o
		Date dataProgramacaoInicial = null;
		Date dataProgramacaoFinal = null;
		
		if (filtrarOrdemServicoActionForm.getPeriodoProgramacaoInicial() != null &&
			!filtrarOrdemServicoActionForm.getPeriodoProgramacaoInicial().equals("")){
			
			dataProgramacaoInicial = 
				Util.converteStringParaDate(filtrarOrdemServicoActionForm.getPeriodoProgramacaoInicial());
			
			dataProgramacaoFinal = null;
			
			if (filtrarOrdemServicoActionForm.getPeriodoProgramacaoFinal() != null &&
				!filtrarOrdemServicoActionForm.getPeriodoProgramacaoFinal().equals("") ) {
				
				dataProgramacaoFinal = 
					Util.converteStringParaDate(filtrarOrdemServicoActionForm.getPeriodoProgramacaoFinal());
				
			} else {
				dataProgramacaoFinal = new Date();
			}

			parametroInformado = true;
		}

		
		// Data de Encerramento
		Date dataEncerramentoInicial = null;
		Date dataEncerramentoFinal = null;
		
		if (filtrarOrdemServicoActionForm.getPeriodoEncerramentoInicial() != null &&
			!filtrarOrdemServicoActionForm.getPeriodoEncerramentoInicial().equals("")){
			
			dataEncerramentoInicial = 
				Util.converteStringParaDate(filtrarOrdemServicoActionForm.getPeriodoEncerramentoInicial());
		
			dataEncerramentoFinal = null;
			
			if (filtrarOrdemServicoActionForm.getPeriodoEncerramentoFinal() != null &&
				!filtrarOrdemServicoActionForm.getPeriodoEncerramentoFinal().equals("") ) {
				
				dataEncerramentoFinal = 
					Util.converteStringParaDate(filtrarOrdemServicoActionForm.getPeriodoEncerramentoFinal());
				
			} else {
				dataEncerramentoFinal = new Date();
			}

			parametroInformado = true;
		}

		// Munic�pio
		Integer idMunicipio = null;
		
		if (filtrarOrdemServicoActionForm.getMunicipio() != null &&
			!filtrarOrdemServicoActionForm.getMunicipio().equals("")) {
			
			idMunicipio = new Integer(filtrarOrdemServicoActionForm.getMunicipio()); 
			
			parametroInformado = true;
		}

		// Bairro
		Integer idBairro = null;
		
		if (filtrarOrdemServicoActionForm.getCodigoBairro() != null && 
			!filtrarOrdemServicoActionForm.getCodigoBairro().equals("")) {
			
			idBairro = this.pesquisarBairro(filtrarOrdemServicoActionForm);
			
			parametroInformado = true;
		}

		// Bairro �rea
		Integer idAreaBairro = null;
		if (filtrarOrdemServicoActionForm.getAreaBairro() != null && 
				new Integer(filtrarOrdemServicoActionForm.getAreaBairro()).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

			idAreaBairro = new Integer(filtrarOrdemServicoActionForm.getAreaBairro());
			
			parametroInformado = true;
		}
		
		// Logradouro
		Integer idLogradouro = null;
		
		if (filtrarOrdemServicoActionForm.getLogradouro() != null &&
			!filtrarOrdemServicoActionForm.getLogradouro().equals("")) {
		
			idLogradouro = new Integer(filtrarOrdemServicoActionForm.getLogradouro());
			
			parametroInformado = true;
		}
		
		
		/*
		 * Colocado por Raphael Rossiter em 15/10/2009
		 * 
		 * Permitir efetuar a pesquisa das ordens de servi�o pelo projeto
		 */
		Integer idProjeto = null;
		
		if (filtrarOrdemServicoActionForm.getProjeto() != null &&
			!filtrarOrdemServicoActionForm.getProjeto().equals("") &&
			!filtrarOrdemServicoActionForm.getProjeto().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			
			idProjeto = new Integer(filtrarOrdemServicoActionForm.getProjeto());
				
			parametroInformado = true;
		}
		
		Integer idLocalidade = null;
		Integer idSetorComercial = null;
		Integer numQuadra = null;
		
		
		if(Util.verificarIdNaoVazio(filtrarOrdemServicoActionForm.getIdLocalidade())){
			parametroInformado = true;
			
			idLocalidade = new Integer(filtrarOrdemServicoActionForm.getIdLocalidade());
			Localidade loc = this.getFachada().pesquisarLocalidadeDigitada(idLocalidade);
			
			if(loc != null){
				if(Util.verificarIdNaoVazio(filtrarOrdemServicoActionForm.getIdSetorComercial())){
					
					SetorComercial sc = this.getFachada().obterSetorComercialLocalidade(
							filtrarOrdemServicoActionForm.getIdLocalidade(), 
							filtrarOrdemServicoActionForm.getIdSetorComercial());
					
					if(sc != null){
					
						idSetorComercial = sc.getId();
						
						if(Util.verificarIdNaoVazio(filtrarOrdemServicoActionForm.getNumeroQuadra())){
							
							Quadra q = this.getFachada().obterQuadraSetorComercial(sc.getId(), Integer.parseInt(filtrarOrdemServicoActionForm.getNumeroQuadra()));
							
							if(q != null){
							
								numQuadra = q.getId();
							
							}
							else{
								throw new ActionServletException("atencao.pesquisa_inexistente",null,"Quadra");
							}
						}
					}
					else{
						throw new ActionServletException("atencao.pesquisa_inexistente",null,"Setor Comercial");
					}
				}
			}
			
			else{
				throw new ActionServletException("atencao.pesquisa_inexistente",null,"Localidade");
			}
		}
		
		Integer numRota = null;
		if(Util.verificarIdNaoVazio(filtrarOrdemServicoActionForm.getCodigoRota())){
			numRota = new Integer(filtrarOrdemServicoActionForm.getCodigoRota());
			parametroInformado = true;
		}
		
		PesquisarOrdemServicoHelper pesquisarOrdemServicoHelper = 
			new PesquisarOrdemServicoHelper();
		pesquisarOrdemServicoHelper.setOrigemOrdemServico(origemOS);
		pesquisarOrdemServicoHelper.setNumeroOS(numeroOS);
		pesquisarOrdemServicoHelper.setNumeroRA(numeroRA);
		pesquisarOrdemServicoHelper.setDocumentoCobranca(idDocumentoCobranca);
		pesquisarOrdemServicoHelper.setSituacaoOrdemServico(situacaoOrdemServico);
		pesquisarOrdemServicoHelper.setSituacaoProgramacao(situacaoProgramacao);
		pesquisarOrdemServicoHelper.setTipoServicos(idsTipoServicoSelecionado);

		pesquisarOrdemServicoHelper.setMatriculaImovel(matriculaImovel);
		pesquisarOrdemServicoHelper.setCodigoCliente(codigoCliente);
		
		pesquisarOrdemServicoHelper.setUnidadeGeracao(unidadeGeracao);
		pesquisarOrdemServicoHelper.setUnidadeAtual(unidadeAtual);
		pesquisarOrdemServicoHelper.setUnidadeSuperior(unidadeSuperior);
		
		pesquisarOrdemServicoHelper.setColecaoAtendimentoMotivoEncerramento(colecaoAtendimentoMotivoEncerramento);
		 
		pesquisarOrdemServicoHelper.setDataAtendimentoInicial(dataAtendimentoInicial);
		pesquisarOrdemServicoHelper.setDataAtendimentoFinal(dataAtendimentoFinal);
		pesquisarOrdemServicoHelper.setDataGeracaoInicial(dataGeracaoInicial);
		pesquisarOrdemServicoHelper.setDataGeracaoFinal(dataGeracaoFinal);
		pesquisarOrdemServicoHelper.setDataProgramacaoInicial(dataProgramacaoInicial);
		pesquisarOrdemServicoHelper.setDataProgramacaoFinal(dataProgramacaoFinal);
		pesquisarOrdemServicoHelper.setDataEncerramentoInicial(dataEncerramentoInicial);
		pesquisarOrdemServicoHelper.setDataEncerramentoFinal(dataEncerramentoFinal);

		pesquisarOrdemServicoHelper.setMunicipio(idMunicipio);
		pesquisarOrdemServicoHelper.setBairro(idBairro);
		pesquisarOrdemServicoHelper.setAreaBairro(idAreaBairro);
		pesquisarOrdemServicoHelper.setLogradouro(idLogradouro);
		pesquisarOrdemServicoHelper.setIdLocalidade(idLocalidade);
		pesquisarOrdemServicoHelper.setCodSetorComercial(idSetorComercial);
		pesquisarOrdemServicoHelper.setNumeroQuadra(numQuadra);
		pesquisarOrdemServicoHelper.setNumeroRota(numRota);
		
		pesquisarOrdemServicoHelper.setColecaoPerfilImovel(colecaoPerfilImovel);
		
		pesquisarOrdemServicoHelper.setIdProjeto(idProjeto);
		
		sessao.setAttribute("pesquisarOrdemServicoHelper", pesquisarOrdemServicoHelper);
		
		String indicadorTipoServico = filtrarOrdemServicoActionForm.getIndicadorTipoServico();
		
		if(indicadorTipoServico != null && !indicadorTipoServico.equals("")){
			parametroInformado = true;
			if(indicadorTipoServico.equals("terceirizado")){
				pesquisarOrdemServicoHelper.setIndicadorTerceirizado(ConstantesSistema.SIM);
			}else if(indicadorTipoServico.equals("pavimento")){
				pesquisarOrdemServicoHelper.setIndicadorPavimento(ConstantesSistema.SIM);
			} else if(indicadorTipoServico.equals("vistoria")){
				pesquisarOrdemServicoHelper.setIndicadorVistoria(ConstantesSistema.SIM);
			}else if(indicadorTipoServico.equals("fiscalizacao")){
				pesquisarOrdemServicoHelper.setIndicadorFiscalizacao(ConstantesSistema.SIM);
			}
		}

		
		
		
		
		// Pesquisar Ordem Servico
		if(sessao.getAttribute("parametroInformado") != null){
			parametroInformado = ((Boolean) sessao.getAttribute("parametroInformado")).booleanValue();
		}
		
		if(httpServletRequest.getParameter("voltar") != null){
			
			if(pesquisarOrdemServicoHelper.getNumeroOS() != null){
				pesquisarOrdemServicoHelper.setNumeroOS(null);
				pesquisarOrdemServicoHelper.setNumeroRA(null);
				
			}
			
		}
		
		if (parametroInformado) {

			int totalRegistros = ConstantesSistema.NUMERO_NAO_INFORMADO;

			Integer tamanho = null;

			if(httpServletRequest.getParameter("page.offset") != null){
				tamanho = (Integer) sessao.getAttribute("totalRegistros");
			}else{
				tamanho = Fachada.getInstancia().pesquisarOrdemServicoTamanho(pesquisarOrdemServicoHelper);
			}
			
			if (tamanho == null || tamanho == 0) {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			
			} else if(tamanho.intValue() == 1){

				Collection<OrdemServico> colecaoOrdemServico = Fachada.getInstancia().pesquisarOrdemServico(pesquisarOrdemServicoHelper);
				
				OrdemServico os = (OrdemServico) Util.retonarObjetoDeColecao(colecaoOrdemServico);
				
				httpServletRequest.setAttribute("numeroOS", os.getId());
				retorno = actionMapping.findForward("manterOrdemServico");
				
//				sessao.removeAttribute("caminhoRetornoOS");				
				sessao.removeAttribute("manterOs");
			} else {
				sessao.setAttribute("manterOs", "N�o");				
//				sessao.setAttribute( "caminhoRetornoOS", "filtrarOrdemServicoAction.do" );
				
				totalRegistros = tamanho.intValue();
				
				retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);				
				
				int numeroPaginasPesquisa = 
					((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa")).intValue();
				
				pesquisarOrdemServicoHelper.setNumeroPaginasPesquisa(numeroPaginasPesquisa);
				
				Collection<OrdemServico> colecaoOrdemServico = Fachada.getInstancia().pesquisarOrdemServico(pesquisarOrdemServicoHelper);
				
				Collection colecaoOSHelper = loadColecaoOSHelper(colecaoOrdemServico);
				sessao.setAttribute("numeroPaginasPesquisa", numeroPaginasPesquisa);
				sessao.setAttribute("page.offset", httpServletRequest.getAttribute("page.offset")); 
				sessao.setAttribute("colecaoOSHelper", colecaoOSHelper);
				sessao.setAttribute("parametroInformado", new Boolean(parametroInformado));
				
			}
		} else {
			throw new ActionServletException("atencao.filtrar_informar_um_filtro");
		}
		
		if (sessao.getAttribute("importarMovimentoACQUAGIS") != null &&
				sessao.getAttribute("importarMovimentoACQUAGIS").equals("sim")) {
			sessao.setAttribute("caminhoRetornoOS", "filtrarRegistroAtendimentoTramitacaoAction.do?importarMovimentoACQUAGIS=sim");
		}

		return retorno;
	}

	/**
	 * Carrega cole��o de ordem de servico, situa��o da unidade atual no 
	 * objeto facilitador 
	 *
	 * @author Rafael Pinto, Pedro Alexandre
	 * @date 18/08/2006, 14/02/2008
	 *
	 * @param colecaoRegistroAtendimento
	 * @return
	 */
	private Collection loadColecaoOSHelper(Collection<OrdemServico> colecaoOrdemServico) {
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoOSHelper = new ArrayList();
		UnidadeOrganizacional unidadeAtual = null;
		ObterDescricaoSituacaoOSHelper situacao = null;
		Imovel imovel = null;
		OSFiltroHelper helper = null;
		
		for (Iterator iter = colecaoOrdemServico.iterator(); iter.hasNext();) {
			
			OrdemServico ordemServico = (OrdemServico) iter.next();
			
			situacao = fachada.obterDescricaoSituacaoOS(ordemServico.getId());
			
			if(ordemServico.getRegistroAtendimento() != null) {
				unidadeAtual = fachada.obterUnidadeAtualRA(ordemServico.getRegistroAtendimento().getId());
				imovel = ordemServico.getRegistroAtendimento().getImovel();
			}else if(ordemServico.getCobrancaDocumento() != null){
				imovel = ordemServico.getCobrancaDocumento().getImovel();
			}
			
			helper = new OSFiltroHelper();
			
			helper.setUnidadeAtual(ordemServico.getUnidadeAtual());
			helper.setOrdemServico(ordemServico);
			helper.setImovel(imovel);
			helper.setUnidadeAtual(unidadeAtual);
			helper.setSituacao(situacao.getDescricaoAbreviadaSituacao());
			
			if(ordemServico.getImovel() != null){
				helper.setPerfilImovel(ordemServico.getImovel().getImovelPerfil());				
			}			
			
			//Caso o RegistroAtendimento for urgente, indicadorUrgencia = 1, senao = 2.
//			if( ordemServico.getRegistroAtendimento() != null && 
//					this.getFachada().verificarRegistroAtendimentoUrgencia(ordemServico.getRegistroAtendimento().getId()) > 0){
//				helper.setIndicadorUrgencia(1);
//			}else{
//				helper.setIndicadorUrgencia(2);
//			}
			verificarUrgencia(helper,ordemServico.getRegistroAtendimento());
			
			colecaoOSHelper.add(helper);
		}
		
		return colecaoOSHelper;
	}	
	
	/**
	 * Pesquisa Bairro 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private Integer pesquisarBairro(FiltrarOrdemServicoActionForm form) {
		
		//[FS0013] - Verificar informa��o do mun�cipio
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

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoBairro = Fachada.getInstancia()
				.pesquisar(filtroBairro,Bairro.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Bairro bairro = 
				(Bairro) Util.retonarObjetoDeColecao(colecaoBairro);
			
			retorno = bairro.getId();
		} else {
			throw new ActionServletException("atencao.bairro.inexistente");
		}
		
		return retorno;
	}
	
	/**
	 * @author Vivianne Sousa
	 * @date 18/05/2011
	 */
	private void verificarUrgencia(OSFiltroHelper helper,RegistroAtendimento registroAtendimento) {
		
		Integer indicadorUrgencia = 2;
		
		if(registroAtendimento != null){
			
			Integer qtdeRAUrgencia = this.getFachada().verificarRegistroAtendimentoUrgencia(registroAtendimento.getId());
			Short qtdeReiteracoes = this.getFachada().pesquisarQtdeReiteracaoRA(registroAtendimento.getId());
			
			StringBuilder hint1 = new StringBuilder();
			
			if(qtdeRAUrgencia.intValue() > 0 && qtdeReiteracoes != null &&  qtdeReiteracoes.intValue() > 0){
				indicadorUrgencia = 1;
				hint1.append("Registro de Atendimento em car�ter de urg�ncia");
				hint1.append(" <br> Registro de Atendimento reiterado");
			}else if(qtdeRAUrgencia.intValue() > 0){
				indicadorUrgencia = 1;
				hint1.append("Registro de Atendimento em car�ter de urg�ncia");
			}else if(qtdeReiteracoes != null && qtdeReiteracoes.intValue() > 0){
				indicadorUrgencia = 1;
				hint1.append("Registro de Atendimento reiterado");
			}
			helper.setHint1(hint1.toString());
		}
		helper.setIndicadorUrgencia(indicadorUrgencia);
	}
}